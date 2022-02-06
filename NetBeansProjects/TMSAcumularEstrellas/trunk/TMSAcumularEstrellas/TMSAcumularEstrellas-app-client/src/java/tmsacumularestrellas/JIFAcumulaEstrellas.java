package tmsacumularestrellas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.List;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.jdesktop.layout.GroupLayout;
import soliciutdAcumularEstrellas.TMSAcumularCentralFacadeRemote;
import soliciutdAcumularEstrellas.TMSAcumularEstrellasFacadeRemote;
import subProcesosAcum.JClsSintaxisAcum;
import subProcesosAcum.JTxtFieldFoliosAcum;
import subProcesosAcum.PcInfo;
import tms_TextFields.JCuantityTextField;
import tms_calendario.JDateChooser;
import wsLealtad.OperacionesResponse;
import xer_emv_dll.SwingWorker;

public class JIFAcumulaEstrellas extends JInternalFrame
{
  private Context cont = null;
  private TMSAcumularEstrellasFacadeRemote busqueda;
  private TMSAcumularCentralFacadeRemote busquedaCentral;
  private JDlgAceptar DialogoAceptar;
  private JDlgSiNo DialogoSiNo;
  private boolean activoF8 = false;
  private JDlgBProgresoLealtad jProgreso = null;
  private SwingWorker worker = null;
  private long USUARIO_ID;
  private String USUARIO_NUMERO;
  private String USUARIO_NOMBRE;
  private sesionVenta sesionventa;
  private Color colorDialogoActivo = new Color(0, 83, 255);
  private KeyEvent eventoTeclado;
  private JClsSintaxisAcum jClsSintaxis = null;
  private Vector tiposPasajeros = null;
  private Vector preciosFolios = null;
  private String CadenaFolios = "";
  private String CadenaTiposPasajero = "";
  private Vector<Boleto> vectorBoletos;
  private Vector<tipoPasajero> vectorTiposPasajero;
  private SimpleDateFormat formatoDateServer2 = new SimpleDateFormat("yyyy-MM-dd");
  private PcInfo estaCaja;
  private JLabel jLabel1;
  private JLabel jLabel10;
  private JLabel jLabel11;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JLabel jLabel8;
  private JLabel jLabel9;
  private JPanel jPanel1;
  private JComboBox jcmb_destino;
  private JComboBox jcmb_empresa;
  private JComboBox jcmb_origen;
  private JComboBox jcmb_servicio;
  private JComboBox jcmb_tipoBoleto;
  private JComboBox jcmb_transaccion;
  private JLabel jlbl_barraEstado;
  private JDateChooser jtxt_fecha;
  private JTextField jtxt_folioBoleto;
  private JCuantityTextField jtxt_monto;
  private JTxtTipoPasajeAcum jtxt_tipoPasajero;

  public JIFAcumulaEstrellas(Vector datosIniciales)
  {
    iniciarConexion();
    initComponents();
    inicializarComponentes();
    setTitle("Acumulacion/Cancelacion de Estrellas");
    this.jLabel11.setText("Rev04.11.11");
    ((BasicInternalFrameUI)getUI()).setNorthPane(null);
    this.USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString()).longValue();
    this.USUARIO_NUMERO = datosIniciales.get(1).toString();
    this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
    this.sesionventa = new sesionVenta(this.busqueda);
    this.sesionventa.setUsuarioNum(this.USUARIO_NUMERO);
    this.sesionventa.setUsuarioId(this.USUARIO_ID);
    String tl = this.busqueda.getTerminalLocal();
    this.sesionventa.setUsarWSLelatad(this.busqueda.usarWSLealtad());
    System.out.println("usar WS de Lealtad: "+ this.sesionventa.isUsarWSLelatad());
    this.estaCaja = new PcInfo();
    tl = tl.replace('[', ' ');
    tl = tl.replace(']', ' ');
    for (int j = 0; j < this.jcmb_origen.getItemCount(); j++)
    {
      Terminal t = (Terminal)this.jcmb_origen.getItemAt(j);
      if (!("" + t.getId()).equals(tl.trim()))
        continue;
      this.sesionventa.setTerminalLocal(t);
      break;
    }

    System.out.println("Terminal Local: " + this.sesionventa.getTerminalLocal().getId());
    this.sesionventa.setTiposPagoValidos(this.busqueda.getTipoPagosValidoslealtad());
    System.out.println("TiposPagoValidos" + this.sesionventa.getTiposPagoValidos());
    Vector vc = this.busqueda.buscaCajaId(this.estaCaja.getHostName().toUpperCase());
    if (vc.size() == 0)
      this.sesionventa.setCajaId("-1");
    else {
      this.sesionventa.setCajaId(((Vector)vc.get(0)).get(0).toString());
    }
    this.sesionventa.setParametros(this.busqueda.getParametrosTermial(this.sesionventa.getTerminalLocal().getId()));
    this.DialogoAceptar = new JDlgAceptar();
    this.jtxt_fecha.dateEditor.addKeyListener1(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jtxt_fechaKeyPressed(evt);
      }
    });
    this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Acumulación de Estrellas | <font color=FF0000>F8</font> Acumular/Cancelar Estrellas | <font color=FF0000> Izquierda/Derecha </font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");
  }

  private void iniciarConexion()
  {
    try {
      this.cont = new InitialContext(System.getProperties());
      this.busqueda = ((TMSAcumularEstrellasFacadeRemote)this.cont.lookup(TMSAcumularEstrellasFacadeRemote.class.getName()));
      this.busquedaCentral = ((TMSAcumularCentralFacadeRemote)this.cont.lookup(TMSAcumularCentralFacadeRemote.class.getName()));
    } catch (NamingException ex) {
      ex.printStackTrace();
    }
  }

  private void inicializarComponentes() {
    Vector temp = this.busqueda.buscarOrigenesDestinos();
    for (int k = 0; k < temp.size(); k++) {
      Terminal t = new Terminal((Vector)temp.get(k));
      this.jcmb_origen.addItem(t);
      this.jcmb_destino.addItem(t);
    }
    temp = this.busqueda.buscarServicios();
    for (int k = 0; k < temp.size(); k++) {
      Servicio s = new Servicio((Vector)temp.get(k));
      this.jcmb_servicio.addItem(s);
    }
    this.jcmb_transaccion.addItem("ACUMULACIÓN");
    this.jcmb_transaccion.addItem("CANCELACIÓN");
    this.jcmb_tipoBoleto.addItem("SISTEMA");
    this.jcmb_tipoBoleto.addItem("MANUAL");

    temp = this.busqueda.buscarEmpresas();
    for (int k = 0; k < temp.size(); k++) {
      Empresa e = new Empresa((Vector)temp.get(k));
      this.jcmb_empresa.addItem(e);
    }

    temp = this.busqueda.buscarTiposPasajero();
    this.vectorTiposPasajero = new Vector();
    for (int k = 0; k < temp.size(); k++) {
      tipoPasajero e = new tipoPasajero((Vector)temp.get(k));
      this.vectorTiposPasajero.add(e);
    }
    this.jClsSintaxis = new JClsSintaxisAcum();
    this.jtxt_tipoPasajero.setTipos(temp);
  }

  private void initComponents()
  {
    this.jlbl_barraEstado = new JLabel();
    this.jPanel1 = new JPanel();
    this.jLabel10 = new JLabel();
    this.jcmb_empresa = new JComboBox();
    this.jLabel2 = new JLabel();
    this.jcmb_servicio = new JComboBox();
    this.jLabel3 = new JLabel();
    this.jcmb_origen = new JComboBox();
    this.jLabel4 = new JLabel();
    this.jcmb_destino = new JComboBox();
    this.jLabel5 = new JLabel();
    this.jcmb_transaccion = new JComboBox();
    this.jLabel6 = new JLabel();
    this.jcmb_tipoBoleto = new JComboBox();
    this.jLabel1 = new JLabel();
    this.jtxt_fecha = new JDateChooser();
    this.jLabel8 = new JLabel();
    this.jLabel7 = new JLabel();
    this.jLabel9 = new JLabel();
    this.jtxt_monto = new JCuantityTextField();
    this.jtxt_folioBoleto = new JTxtFieldFoliosAcum();
    this.jtxt_tipoPasajero = new JTxtTipoPasajeAcum();
    this.jLabel11 = new JLabel();

    setDefaultCloseOperation(2);
    setIconifiable(true);
    addComponentListener(new ComponentAdapter() {
      public void componentShown(ComponentEvent evt) {
        JIFAcumulaEstrellas.this.formComponentShown(evt);
      }
    });
    this.jlbl_barraEstado.setText("jLabel10");

    this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Acumulación de Estrellas", 1, 0, new Font("Tahoma", 1, 12)));
    this.jLabel10.setFont(new Font("Tahoma", 1, 12));
    this.jLabel10.setHorizontalAlignment(4);
    this.jLabel10.setText("Empresa:");

    this.jcmb_empresa.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jcmb_empresaKeyPressed(evt);
      }
    });
    this.jLabel2.setFont(new Font("Tahoma", 1, 12));
    this.jLabel2.setHorizontalAlignment(4);
    this.jLabel2.setText("Servicio:");

    this.jcmb_servicio.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jcmb_servicioKeyPressed(evt);
      }
    });
    this.jLabel3.setFont(new Font("Tahoma", 1, 12));
    this.jLabel3.setHorizontalAlignment(4);
    this.jLabel3.setText("Origen:");

    this.jcmb_origen.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jcmb_origenKeyPressed(evt);
      }
    });
    this.jLabel4.setFont(new Font("Tahoma", 1, 12));
    this.jLabel4.setHorizontalAlignment(4);
    this.jLabel4.setText("Destino:");

    this.jcmb_destino.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jcmb_destinoKeyPressed(evt);
      }
    });
    this.jLabel5.setFont(new Font("Tahoma", 1, 12));
    this.jLabel5.setHorizontalAlignment(4);
    this.jLabel5.setText("Tipo Transacción:");

    this.jcmb_transaccion.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jcmb_transaccionKeyPressed(evt);
      }
    });
    this.jLabel6.setFont(new Font("Tahoma", 1, 12));
    this.jLabel6.setHorizontalAlignment(4);
    this.jLabel6.setText("Tipo Boleto:");

    this.jcmb_tipoBoleto.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jcmb_tipoBoletoKeyPressed(evt);
      }
    });
    this.jLabel1.setFont(new Font("Tahoma", 1, 12));
    this.jLabel1.setHorizontalAlignment(4);
    this.jLabel1.setText("Fecha Venta:");

    this.jLabel8.setFont(new Font("Tahoma", 1, 12));
    this.jLabel8.setHorizontalAlignment(4);
    this.jLabel8.setText("Tipo Pasajero:");

    this.jLabel7.setFont(new Font("Tahoma", 1, 12));
    this.jLabel7.setHorizontalAlignment(4);
    this.jLabel7.setText("Folio Boleto:");

    this.jLabel9.setFont(new Font("Tahoma", 1, 12));
    this.jLabel9.setHorizontalAlignment(4);
    this.jLabel9.setText("Monto:");

    this.jtxt_monto.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jtxt_montoKeyPressed(evt);
      }
    });
    this.jtxt_folioBoleto.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jtxt_folioBoletoKeyPressed(evt);
      }
    });
    this.jtxt_tipoPasajero.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFAcumulaEstrellas.this.jtxt_tipoPasajeroKeyPressed(evt);
      }
    });
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(30, 30, 30).add(jPanel1Layout.createParallelGroup(2).add(this.jLabel7, -2, 105, -2).add(jPanel1Layout.createParallelGroup(1, false).add(2, this.jLabel1, -1, -1, 32767).add(2, this.jLabel5, -1, -1, 32767).add(2, this.jLabel10, -1, 105, 32767).add(2, this.jLabel3, -1, 105, 32767))).add(12, 12, 12).add(jPanel1Layout.createParallelGroup(1).add(2, jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(2, false).add(this.jtxt_folioBoleto).add(this.jtxt_fecha, -1, 174, 32767)).add(49, 49, 49).add(jPanel1Layout.createParallelGroup(1).add(this.jLabel8, -1, 86, 32767).add(2, this.jLabel9, -2, 86, -2)).addPreferredGap(0)).add(2, jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createParallelGroup(2, false).add(1, this.jcmb_transaccion, 0, -1, 32767).add(1, this.jcmb_origen, 0, 174, 32767)).add(this.jcmb_empresa, -2, 174, -2)).addPreferredGap(0, 49, 32767).add(jPanel1Layout.createParallelGroup(1, false).add(this.jLabel2, -1, -1, 32767).add(2, this.jLabel4, -1, 86, 32767).add(2, this.jLabel6, -1, -1, 32767)))).add(jPanel1Layout.createParallelGroup(2).add(jPanel1Layout.createSequentialGroup().addPreferredGap(0).add(this.jtxt_monto, -2, 203, -2)).add(jPanel1Layout.createSequentialGroup().add(14, 14, 14).add(jPanel1Layout.createParallelGroup(2).add(this.jcmb_tipoBoleto, 0, 203, 32767).add(this.jcmb_destino, 0, 203, 32767).add(1, this.jcmb_servicio, 0, 203, 32767).add(this.jtxt_tipoPasajero, -1, 203, 32767)))).add(28, 28, 28)));

    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(23, 23, 23).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel10).add(this.jcmb_empresa, -2, -1, -2).add(this.jcmb_servicio, -2, -1, -2).add(this.jLabel2)).add(23, 23, 23).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.jcmb_origen, -2, -1, -2).add(this.jLabel4).add(this.jcmb_destino, -2, -1, -2)).add(25, 25, 25).add(jPanel1Layout.createParallelGroup(3).add(this.jcmb_transaccion, -2, -1, -2).add(this.jcmb_tipoBoleto, -2, -1, -2).add(this.jLabel5).add(this.jLabel6)).add(27, 27, 27).add(jPanel1Layout.createParallelGroup(1).add(this.jtxt_fecha, -2, -1, -2).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel8).add(this.jtxt_tipoPasajero, -2, -1, -2)).add(this.jLabel1)).add(27, 27, 27).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel9).add(this.jtxt_monto, -2, -1, -2).add(this.jLabel7).add(this.jtxt_folioBoleto, -2, -1, -2)).addContainerGap(36, 32767)));

    this.jLabel11.setFont(new Font("Tahoma", 1, 12));
    this.jLabel11.setText("Rev03.03.11");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(this.jlbl_barraEstado, -1, 746, 32767).add(layout.createSequentialGroup().add(19, 19, 19).add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)).add(2, layout.createSequentialGroup().addContainerGap(639, 32767).add(this.jLabel11).add(28, 28, 28)));

    layout.setVerticalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(this.jLabel11).addPreferredGap(0).add(this.jPanel1, -2, -1, -2).addPreferredGap(0, -1, 32767).add(this.jlbl_barraEstado, -2, 57, -2)));

    pack();
  }

  private void jtxt_tipoPasajeroKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jtxt_fecha.dateEditor.setSeleccionaTexto();
      this.jtxt_fecha.dateEditor.setFoco();
    }

    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jtxt_folioBoleto.selectAll();
      this.jtxt_folioBoleto.requestFocus();
    }

    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jcmb_empresaKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jtxt_monto.selectAll();
      this.jtxt_monto.requestFocus();
    }

    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jcmb_servicio.requestFocus();
    }
    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jcmb_servicioKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jcmb_empresa.requestFocus();
    }
    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jcmb_origen.requestFocus();
    }
    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jcmb_origenKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jcmb_servicio.requestFocus();
    }
    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jcmb_destino.requestFocus();
    }
    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jcmb_destinoKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jcmb_origen.requestFocus();
    }
    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jcmb_transaccion.requestFocus();
    }
    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jcmb_transaccionKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jcmb_destino.requestFocus();
    }
    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jcmb_tipoBoleto.requestFocus();
    }
    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jcmb_tipoBoletoKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jcmb_transaccion.requestFocus();
    }
    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jtxt_fecha.dateEditor.setSeleccionaTexto();
      this.jtxt_fecha.dateEditor.setFoco();
    }

    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jtxt_montoKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jtxt_folioBoleto.selectAll();
      this.jtxt_folioBoleto.requestFocus();
    }

    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jcmb_empresa.requestFocus();
    }
    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void jtxt_folioBoletoKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jtxt_tipoPasajero.requestFocus();
    }
    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jtxt_monto.selectAll();
      this.jtxt_monto.requestFocus();
    }

    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115) {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void formComponentShown(ComponentEvent evt) {
    this.jcmb_empresa.requestFocusInWindow();
  }

  private void jtxt_fechaKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 37) {
      this.jcmb_tipoBoleto.requestFocus();
    }
    if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 10)) {
      this.jtxt_tipoPasajero.requestFocus();
    }
    if (evt.getKeyCode() == 119) {
      ejecutarAccion();
    }
    if (evt.getKeyCode() == 115)
    {
      this.DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea cerrar la Aplicación TMS Acumulacion de Estrellas?", false);
      if (this.DialogoSiNo.getResultado()) {
        dispose();
      }
    }
    if ((evt.getKeyCode() == 50) || (evt.getKeyCode() == 49))
      controlVentana(evt);
  }

  private void ejecutarAccion()
  {
    if (!validacion())
      return;
    boolean sistema;  
    if (this.jcmb_tipoBoleto.getSelectedItem().toString().equals("SISTEMA"))
      sistema = true;
    else sistema = false;
    boolean encontroFolio = false;
    Vector vf = buscaFolio();
    if ((vf.size() == 0) && (sistema))
    {
      this.DialogoAceptar.mostrarDialogo("¡Error al buscar información!", "El folio ingresado no se encontro registrado por sistema.", Color.RED);
      return;
    }
    if ((vf.size() > 0) && (!sistema))
    {
      this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "El folio ingresado esta registrado por sistema.", Color.RED);
      return;
    }
    System.out.println(" Vector1(" + vf.size() + "): " + vf);
    String pCVta = "";
    for (int i = 0; i < vf.size(); i++)
    {
      Vector v = (Vector)vf.get(i);
      if (i == 0) {
        pCVta = v.get(8).toString();
      }
      else if (!pCVta.equals(v.get(8).toString()))
      {
        this.DialogoAceptar.mostrarDialogo("¡Transacciones Diferentes!", "La ciudad de venta de los boletos debe ser la misma.", Color.RED);
        return;
      }

      if (v.get(13).toString().equals("N"))
      {
        this.DialogoAceptar.mostrarDialogo("¡Fecha de compra inválida!", "La fecha de compra del boleto " + v.get(12).toString() + "excedió la fecha limite para acumular.", Color.RED);
        return;
      }
      if (v.get(11) != null) {
        continue;
      }
      Vector vr = this.busqueda.buscaRutaBoleto("" + ((Servicio)this.jcmb_servicio.getSelectedItem()).getServicioId(), "" + ((Empresa)this.jcmb_empresa.getSelectedItem()).getEmpresaId(), "" + ((Terminal)this.jcmb_origen.getSelectedItem()).getId(), "" + ((Terminal)this.jcmb_destino.getSelectedItem()).getId());
      if (vr.size() == 0)
      {
        this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "La ruta del boleto seleccionado no esta configurada.", Color.RED);
        return;
      }
      Vector r = (Vector)vr.get(0);
      ((Vector)vf.get(i)).set(11, r.get(0).toString());
    }

    System.out.println(" Vector2(" + vf.size() + "): " + vf);
    Vector f = null;
    if (sistema)
    {
      f = (Vector)vf.get(0);
      if (!validaDatosSistema(vf)) return;

    }

    Vector excluidos = new Vector();
    Vector folAcum = new Vector();
    Vector tarAcum = new Vector();
    Vector folMonto = new Vector();
    Vector folTipo = new Vector();

    for (int i = 0; i < this.jClsSintaxis.getVectorFoliosBoletos().size(); i++)
    {
      System.out.println("Folio: " + this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString());
      Vector ac = buscaAcumulados(this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString());
      Vector ca = buscaCancelados(this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString());
      long acumulados = ac.size();
      long cancelados = ca.size();
      System.out.println("acumulados: " + acumulados + " == > " + ac);
      System.out.println("cancelados: " + cancelados + " == > " + ca);
      if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN")) {
        if (acumulados != cancelados) {
          excluidos.add(this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString());
          this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "Al boleto " + this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString() + " ya se le acumularon estrellas.", Color.RED);
        }

      }
      else if (acumulados <= cancelados)
      {
        excluidos.add(this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString());
        this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "Al boleto " + this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString() + " ya se le cancelaron las estrellas.", Color.RED);
      }
      else
      {
        folAcum.add(this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString());
        tarAcum.add(((Vector)ac.get(0)).get(0).toString());
        folMonto.add(((Vector)ac.get(0)).get(1).toString());
        folTipo.add(((Vector)ac.get(0)).get(2).toString());
      }

    }

    System.out.println("Folio Excluidos: " + excluidos);
    System.out.println("folAcum: " + folAcum);
    System.out.println("tarAcum: " + tarAcum);
    System.out.println("folMonto: " + folMonto);
    System.out.println("folTipo: " + folTipo);
    if (excluidos.size() == this.jClsSintaxis.getVectorFoliosBoletos().size())
    {
      limpiar();
      return;
    }
    this.vectorBoletos = new Vector();
    if (sistema)
    {
      for (int b = 0; b < vf.size(); b++)
      {
        Vector v = (Vector)vf.get(b);

        if (excluidos.indexOf(v.get(12).toString()) != -1)
          continue;
        Boleto bol = new Boleto();
        bol.setFolio(v.get(12).toString());
        bol.setTipoPasajeroLetra(v.get(4).toString());
        bol.setPrecio(Float.valueOf(v.get(5).toString()).floatValue());
        bol.setNumeroAsiento(sistema ?((v.get(6) == null ?"_" :v.get(6).toString())) :"_");
        bol.setFechaVenta(v.get(10).toString());
        bol.setNombrePasajero(v.get(7).toString());
        bol.setNoTarAcum(folAcum.indexOf(v.get(12).toString()) >= 0 ? tarAcum.get(folAcum.indexOf(v.get(12).toString())).toString() : "");
        bol.setAbierto(v.get(14).toString());
        for (int n = 0; n < this.vectorTiposPasajero.size(); n++)
        {
          tipoPasajero tipo = (tipoPasajero)this.vectorTiposPasajero.get(n);
          if (!tipo.getLetra().equals(v.get(4).toString()))
            continue;
          bol.setTipoPasajeroId(tipo.getTipoId());
          break;
        }

        this.vectorBoletos.add(bol);
      }

    }
    else
    {
      for (int b = 0; b < this.jClsSintaxis.getVectorFoliosBoletos().size(); b++)
      {
        if (excluidos.indexOf(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString()) != -1)
          continue;
        Boleto bol = new Boleto();
        bol.setFolio(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString());
        if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN"))
          bol.setTipoPasajeroLetra(this.tiposPasajeros.get(b).toString());
        else
          bol.setTipoPasajeroLetra(folAcum.indexOf(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString()) >= 0 ? folTipo.get(folAcum.indexOf(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString())).toString() : "A");
        bol.setPrecio(Float.valueOf(folAcum.indexOf(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString()) >= 0 ? folMonto.get(folAcum.indexOf(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString())).toString() : "0").floatValue());
        bol.setNumeroAsiento("");
        bol.setFechaVenta(this.formatoDateServer2.format(this.jtxt_fecha.getDate()));
        bol.setNombrePasajero("");
        bol.setNoTarAcum(folAcum.indexOf(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString()) >= 0 ? tarAcum.get(folAcum.indexOf(this.jClsSintaxis.getVectorFoliosBoletos().get(b).toString())).toString() : "");
        bol.setAbierto("N");
        for (int n = 0; n < this.vectorTiposPasajero.size(); n++)
        {
          tipoPasajero tipo = (tipoPasajero)this.vectorTiposPasajero.get(n);
          if (!tipo.getLetra().equals(this.tiposPasajeros.get(b).toString()))
            continue;
          bol.setTipoPasajeroId(tipo.getTipoId());
          break;
        }

        this.vectorBoletos.add(bol);
      }
    }

    System.out.println("Vector de Boletos: " + this.vectorBoletos);

    String tipoOperacion = "";
    boolean aplica = true;
    String diaAplica = "";
    Timestamp fech = null;
    Vector vd = null;
    if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN"))
    {
      tipoOperacion = "A";
      if (sistema)
      {
        vd = this.busqueda.buscaDatosLealtadBoleto(f.get(11).toString());
        System.out.println("Datos lealtad(sistema)(" + f.get(11).toString() + "): " + vd);
        Vector vr = this.busqueda.buscaODRuta(f.get(11).toString());
        this.sesionventa.setOrigenId("" + Long.valueOf(vr.get(0).toString()));
        this.sesionventa.setDestinoId("" + Long.valueOf(vr.get(1).toString()));
      }
      else
      {
        Vector vr = this.busqueda.buscaRutaBoleto("" + ((Servicio)this.jcmb_servicio.getSelectedItem()).getServicioId(), "" + ((Empresa)this.jcmb_empresa.getSelectedItem()).getEmpresaId(), "" + ((Terminal)this.jcmb_origen.getSelectedItem()).getId(), "" + ((Terminal)this.jcmb_destino.getSelectedItem()).getId());
        if (vr.size() == 0)
        {
          this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "La ruta del boleto seleccionado no esta configurada.", Color.RED);
          return;
        }

        Vector r = (Vector)vr.get(0);
        this.sesionventa.setOrigenId("" + Long.valueOf(r.get(1).toString()));
        this.sesionventa.setDestinoId("" + Long.valueOf(r.get(2).toString()));

        float tarifa = this.busqueda.getTarifaRuta(this.jtxt_fecha.dateEditor.getTexto(), Long.valueOf(r.get(0).toString()).longValue(), Long.valueOf(r.get(1).toString()).longValue(), Long.valueOf(r.get(2).toString()).longValue());
        System.out.println("tarifa(manual): " + tarifa);
        if (tarifa == 0.0F)
        {
          this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "La tarifa para la ruta y fecha ingresada no esta configurada.", Color.RED);
          return;
        }

        vd = this.busqueda.buscaDatosLealtadBoleto(r.get(0).toString());
        System.out.println("Datos lealtad(manual)(" + r.get(0).toString() + "): " + vd);
        float importeTota = 0.0F;
        for (int vb = 0; vb < this.vectorBoletos.size(); vb++)
        {
          Boleto bol = (Boleto)this.vectorBoletos.get(vb);
          for (int nd = 0; nd < vd.size(); nd++) {
            Vector vl = (Vector)vd.get(nd);
            if (bol.getTipoPasajeroId() != Long.valueOf(vl.get(0).toString()).longValue())
              continue;
            float prc = Float.valueOf(vl.get(4).toString()).floatValue();
            float imp = tarifa - prc / 100.0F * tarifa;
            System.out.println("imp: "+imp);
            System.out.println("Redondeo?: "+vl.get(5));
            String redondeo = (vl.get(5) == null?"S":vl.get(5).toString());
            /*Se agrega el redondeo*/
                if(redondeo.equals("S"))
                    imp = (float)Math.ceil(imp);
                else
                {
                    System.out.println("checa si procede ceil: "+imp+" - "+ (Double.valueOf(""+imp).longValue()) + " = "+(imp - (Double.valueOf(""+imp).longValue()) ));
                    double d = (imp - (Double.valueOf(""+imp).longValue()) );
                    if(d > 0 && d != .50)
                        imp =   (float) Math.ceil(imp);
                }
            /***********************/
            System.out.println("imp(Red): "+imp);
            ((Boleto)this.vectorBoletos.get(vb)).setPrecio(imp);
            importeTota += imp;
            break;
          }

        }

        if (importeTota != Float.valueOf(this.jtxt_monto.getText()).floatValue())
        {
          System.out.println("7(manual)");
          this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "El monto ingresado no coincide con la tarifa de la ruta encontrada.", Color.RED);
          this.jtxt_monto.selectAll();
          this.jtxt_monto.requestFocus();
          return;
        }
      }
      if (vd.size() == 0)
      {
        this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "Los tipos de pasajero de la ruta del boleto seleccionado no estan configurados.", Color.RED);
        return;
      }
      List listado = new ArrayList();
      for (int i = 0; i < vd.size(); i++) {
        Vector d = (Vector)vd.get(i);
        listado.add(new TmsTiposPasajeroTbl(d));
      }

      for (int ib = 0; ib < this.vectorBoletos.size(); ib++)
      {
        Boleto bol = (Boleto)this.vectorBoletos.get(ib);
        if (sistema)
        {
          fech = Timestamp.valueOf(bol.getFechaVenta() + " 00:00:00");
          diaAplica = new SimpleDateFormat("EEEE").format(Long.valueOf(fech.getTime())).toUpperCase();
        }
        else {
          diaAplica = new SimpleDateFormat("EEEE").format(Long.valueOf(this.jtxt_fecha.dateEditor.getDate().getTime())).toUpperCase();
        }
        System.out.println("Dia Venta: " + diaAplica);
        for (int j = 0; j < listado.size(); j++)
        {
          if (bol.getTipoPasajeroId() != ((TmsTiposPasajeroTbl)listado.get(j)).getTipoPasajeroId().longValue())
            continue;
          if (((TmsTiposPasajeroTbl)listado.get(j)).getAplicaLealtad().equals("S"))
          {
            if ((diaAplica.equals("LUNES")) && (((TmsTiposPasajeroTbl)listado.get(j)).getL_LUNES().equals("S")))
            {
              aplica = true;
              ((Boleto)this.vectorBoletos.get(ib)).setAplica("APLICA");
            }
            if ((diaAplica.equals("MARTES")) && (((TmsTiposPasajeroTbl)listado.get(j)).getL_MARTES().equals("S")))
            {
              aplica = true;
              ((Boleto)this.vectorBoletos.get(ib)).setAplica("APLICA");
            }
            if ((diaAplica.equals("MIÉRCOLES")) && (((TmsTiposPasajeroTbl)listado.get(j)).getL_MIERCOLES().equals("S")))
            {
              aplica = true;
              ((Boleto)this.vectorBoletos.get(ib)).setAplica("APLICA");
            }
            if ((diaAplica.equals("JUEVES")) && (((TmsTiposPasajeroTbl)listado.get(j)).getL_JUEVES().equals("S")))
            {
              aplica = true;
              ((Boleto)this.vectorBoletos.get(ib)).setAplica("APLICA");
            }
            aplica = true;
            if ((diaAplica.equals("VIERNES")) && (((TmsTiposPasajeroTbl)listado.get(j)).getL_VIERNES().equals("S")))
            {
              aplica = true;
              ((Boleto)this.vectorBoletos.get(ib)).setAplica("APLICA");
            }
            if ((diaAplica.equals("SÁBADO")) && (((TmsTiposPasajeroTbl)listado.get(j)).getL_SABADO().equals("S")))
            {
              aplica = true;
              ((Boleto)this.vectorBoletos.get(ib)).setAplica("APLICA");
            }
            if ((!diaAplica.equals("DOMINGO")) || (!((TmsTiposPasajeroTbl)listado.get(j)).getL_DOMINGO().equals("S")))
              continue;
            aplica = true;
            ((Boleto)this.vectorBoletos.get(ib)).setAplica("APLICA");
          }
          else
          {
            ((Boleto)this.vectorBoletos.get(ib)).setAplica("NO APLICA");
          }
        }
      }

    }
    else
    {
      tipoOperacion = "C";
      if (sistema)
      {
        Vector vr = this.busqueda.buscaODRuta(f.get(11).toString());
        this.sesionventa.setOrigenId("" + Long.valueOf(vr.get(0).toString()));
        this.sesionventa.setDestinoId("" + Long.valueOf(vr.get(1).toString()));
      }
      else
      {
        Vector vr = this.busqueda.buscaRutaBoleto("" + ((Servicio)this.jcmb_servicio.getSelectedItem()).getServicioId(), "" + ((Empresa)this.jcmb_empresa.getSelectedItem()).getEmpresaId(), "" + ((Terminal)this.jcmb_origen.getSelectedItem()).getId(), "" + ((Terminal)this.jcmb_destino.getSelectedItem()).getId());
        if (vr.size() == 0)
        {
          this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "La ruta del boleto seleccionado no esta configurada.", Color.RED);
          return;
        }

        Vector r = (Vector)vr.get(0);
        this.sesionventa.setOrigenId("" + Long.valueOf(r.get(1).toString()));
        this.sesionventa.setDestinoId("" + Long.valueOf(r.get(2).toString()));
      }

    }

    if (!aplica)
    {
      this.DialogoAceptar.mostrarDialogo("¡No Aplica!", "La promoción no Aplica para la ruta o el tipo de Pasajero.", Color.RED);
      return;
    }

    Vector insertados = null;

    Object[][] Boletos = new Object[this.vectorBoletos.size()][35];
    for (int ib = 0; ib < this.vectorBoletos.size(); ib++)
    {
      Boleto bol = (Boleto)this.vectorBoletos.get(ib);

      Boletos[ib][0] = "";
      Boletos[ib][1] = bol.getNumeroAsiento();
      Boletos[ib][2] = bol.getTipoPasajeroLetra();
      Boletos[ib][3] = bol.getNombrePasajero();
      Boletos[ib][4] = "";
      Boletos[ib][5] = Float.valueOf(bol.getPrecio());
      Boletos[ib][6] = "S";
      Boletos[ib][7] = ((Empresa)this.jcmb_empresa.getSelectedItem()).getNombre();
      Boletos[ib][8] = ((Servicio)this.jcmb_servicio.getSelectedItem()).getNombre();
      Boletos[ib][9] = "";
      Boletos[ib][10] = "";
      Boletos[ib][11] = "";
      Boletos[ib][12] = "";
      Boletos[ib][13] = null;
      Boletos[ib][14] = null;
      Boletos[ib][15] = "";
      Boletos[ib][16] = null;
      Boletos[ib][17] = null;
      Boletos[ib][18] = null;
      Boletos[ib][19] = bol.getFolio();
      Boletos[ib][20] = "";
      Boletos[ib][21] = "";
      Boletos[ib][22] = "";
      Boletos[ib][23] = "L";
      Boletos[ib][24] = "";
      Boletos[ib][25] = "";
      Boletos[ib][26] = "";
      Boletos[ib][27] = "";
      Boletos[ib][28] = "N";
      Boletos[ib][29] = "N";
      Boletos[ib][30] = "";
      Boletos[ib][31] = "S";
    }
    this.sesionventa.setEmpresId("" + ((Empresa)this.jcmb_empresa.getSelectedItem()).getEmpresaId());
    this.sesionventa.setEmpresaNombre("" + ((Empresa)this.jcmb_empresa.getSelectedItem()).getNombre());
    this.sesionventa.setServicioId("" + ((Servicio)this.jcmb_servicio.getSelectedItem()).getServicioId());

    this.sesionventa.setBoletosLealtad(Boletos);
    if (sistema)
    {
      for (int j = 0; j < this.jcmb_origen.getItemCount(); j++)
      {
        Terminal t = (Terminal)this.jcmb_origen.getItemAt(j);
        if (!t.getNombre().equals(f.get(8).toString()))
          continue;
        this.sesionventa.setTerminalVenta(t);
        break;
      }
    }
    else
    {
      this.sesionventa.setTerminalVenta(this.sesionventa.getTerminalLocal());
    }
    JDlgAcumulaEstrellas acumula = null;
    boolean continua = true;
    boolean limpiar = false;
    if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN"))
    {
      System.out.println("Entra a pedir tarjeta de lealtad(Extemporaneo)...");
      acumula = new JDlgAcumulaEstrellas(new JDialog(), true, Boletos, this.sesionventa, this.vectorBoletos, true, "");
      if (acumula.isCompletado()) {
        continua = true;
        limpiar = true;
        insertados = acumula.getRegistros();
        System.out.println("se insertara(A): " + insertados);
      } else {
        continua = false;
      }
    }
    else {
      continua = true;
      limpiar = true;

      insertados = constuyeCancelado(this.jcmb_empresa.getSelectedItem().toString().trim());
      System.out.println("se insertara(C): " + insertados);
    }
    if (continua) {
      String numeroOperacion = "";
      for (int k = 0; k < insertados.size(); k++)
      {
        Vector v = (Vector)insertados.get(k);

        numeroOperacion = v.get(3).toString();
        if (sistema)
          this.sesionventa.insertaRegistroLealtad(Integer.valueOf(v.get(0).toString()).intValue(), v.get(1).toString(), v.get(2).toString(), v.get(3).toString(), v.get(4).toString(), tipoOperacion, f.get(9).toString());
        else {
          this.sesionventa.insertaRegistroLealtad(Integer.valueOf(v.get(0).toString()).intValue(), v.get(1).toString(), v.get(2).toString(), v.get(3).toString(), v.get(4).toString(), tipoOperacion, this.jtxt_fecha.dateEditor.getTexto());
        }
      }
      if (this.sesionventa.getTerminalVenta().getNombre().equals(this.sesionventa.getTerminalLocal().getNombre()))
      {
        try {
         if(this.sesionventa.isUsarWSLelatad())
         {
              OperacionesResponse result = this.sesionventa.getWSPort().getOperacion(numeroOperacion);
              System.out.println("Result(getOperacion(" + tipoOperacion + ")(Fuera de tiempo)(" + numeroOperacion + ")) = " + result.getStatus().isSuccess());
              if (result.getStatus().isSuccess()) {
                if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN"))
                {
                  System.out.println("Puntos Otorgados: " + result.getPuntos().getPuntosOtorgados());
                  System.out.println("           Saldo: " + result.getPuntos().getSaldoPuntos());
                  this.DialogoAceptar.mostrarDialogo("¡Acumulación de Estrellas exitosa!", "<html>Se acumularon:  <Font size=16>" + result.getPuntos().getPuntosOtorgados() + "</font> Estrellas  <br>        Saldo Actual:  <Font size=16>" + result.getPuntos().getSaldoPuntos() + "</font> Estrellas</html>", this.colorDialogoActivo);
                }
                else {
                  this.DialogoAceptar.mostrarDialogo("¡Cancelación de Estrellas exitosa!", "<html>Las Estrellas se cancelaron exitosamente</html>", this.colorDialogoActivo);
                }
              } else this.DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.", "<html>" + result.getStatus().getMessage() + "</html>", Color.RED); 
         }
         else
         {
                try{
                   List<String> respuestaLealtad = this.busqueda.Registra_Transaccion_Lealtad(numeroOperacion);
                   System.out.println("Respueesta(procedimiento0): "+respuestaLealtad.get(0));
                   System.out.println("Respueesta(procedimiento1): "+respuestaLealtad.get(1));
                   if(Boolean.parseBoolean(respuestaLealtad.get(0)))
                   {
                       if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN"))
                           DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+respuestaLealtad.get(1).replaceAll("\\\\n\\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);
                       else
                           this.DialogoAceptar.mostrarDialogo("¡Cancelación de Estrellas exitosa!", "<html>Las Estrellas se cancelaron exitosamente</html>", this.colorDialogoActivo);
                   }
                   else DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+respuestaLealtad.get(1).replaceAll("\\\\n\\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);
                } catch (Exception ex) {
                    System.out.println("Excepcion al llamar getOperacion(C)");
                    ex.printStackTrace();
                }
             
         }
        }
        catch (Exception ex) {
          System.out.println("Excepcion al llamar getOperacion del WS (Tarjeta Credito)");
          ex.printStackTrace();
          if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN"))
          {
            this.DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.", "<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
          }
          else this.DialogoAceptar.mostrarDialogo("¡Cancelación de Estrellas exitosa!", "<html>Las Estrellas se cancelaron exitosamente</html>", this.colorDialogoActivo);
        }
        this.sesionventa.detenerWS();
      }
      else if (this.jcmb_transaccion.getSelectedItem().toString().equals("ACUMULACIÓN")) {
        this.DialogoAceptar.mostrarDialogo("¡Acumulación de Estrellas exitosa!", "<html>Las Estrellas se acumularon exitosamente</html>", this.colorDialogoActivo);
      } else {
        this.DialogoAceptar.mostrarDialogo("¡Cancelación de Estrellas exitosa!", "<html>Las Estrellas se cancelaron exitosamente</html>", this.colorDialogoActivo);
      }
    }
    else {
      acumula = null;
    }if (limpiar)
      limpiar();
  }

  private boolean validaDatosSistema(Vector vf)
  {
    double importe = 0.0D;
    for (int i = 0; i < vf.size(); i++)
    {
      Vector f = (Vector)vf.get(i);
      importe += Double.valueOf(f.get(5).toString()).doubleValue();
    }
    System.out.println("Importe de Boletos: " + importe);
    for (int i = 0; i < vf.size(); i++)
    {
      Vector f = (Vector)vf.get(i);

      if (!f.get(0).toString().trim().equals(this.jcmb_empresa.getSelectedItem().toString()))
      {
        System.out.println("1");
        this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "La empresa seleccionada no coincide con la del boleto " + f.get(12).toString() + ".", Color.RED);
        this.jcmb_empresa.requestFocus();
        return false;
      }
      if (!f.get(1).toString().trim().equals(this.jcmb_servicio.getSelectedItem().toString()))
      {
        System.out.println("2");
        this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "El servicio seleccionado no coincide con el del boleto " + f.get(12).toString() + ".", Color.RED);
        this.jcmb_servicio.requestFocus();
        return false;
      }
      String origen = "";
      String destino = "";
      if (f.get(14).equals("S"))
      {
        origen = this.jcmb_destino.getSelectedItem().toString();
        destino = this.jcmb_origen.getSelectedItem().toString();
      }
      else
      {
        destino = this.jcmb_destino.getSelectedItem().toString();
        origen = this.jcmb_origen.getSelectedItem().toString();
      }

      if (!f.get(3).toString().trim().equals(destino))
      {
        if (f.get(14).equals("S"))
        {
          if (!f.get(3).toString().trim().equals(origen))
          {
            System.out.println("3");
            this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "El destino seleccionado no coincide con el del boleto " + f.get(12).toString() + ".", Color.RED);
            this.jcmb_destino.requestFocus();
            return false;
          }
        }
      }

      if (!f.get(2).toString().trim().equals(origen))
      {
        if (f.get(14).equals("S"))
        {
          if (!f.get(2).toString().trim().equals(destino))
          {
            System.out.println("4");
            this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "El origen seleccionado no coincide con el del boleto " + f.get(12).toString() + ".", Color.RED);
            this.jcmb_origen.requestFocus();
            return false;
          }

        }

      }

      if (!f.get(9).toString().trim().equals(this.jtxt_fecha.dateEditor.getTexto()))
      {
        System.out.println("6");
        this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "La fecha ingresada no coincide con la del boleto " + f.get(12).toString() + ".", Color.RED);
        this.jtxt_fecha.dateEditor.setFoco();
        return false;
      }

      if (importe == Double.valueOf(this.jtxt_monto.getText()).doubleValue())
        continue;
      System.out.println("7");
      this.DialogoAceptar.mostrarDialogo("¡Error en datos!", "El monto ingresado no coincide con el de los boletos encontrados.", Color.RED);
      this.jtxt_monto.selectAll();
      this.jtxt_monto.requestFocus();
      return false;
    }

    return true;
  }

  private Vector buscaFolio()
  {
    String dlink = "";
    Terminal t = (Terminal)this.jcmb_origen.getSelectedItem();
    if (this.sesionventa.getTerminalLocal().getId() != t.getId())
      dlink = t.getDbLink();
    Vector vf = this.busqueda.buscaFolio(this.CadenaFolios, this.sesionventa.getTiposPagoValidos(), dlink);

    return vf;
  }

  private Vector buscaAcumulados(String folio)
  {
    Vector ac = this.busqueda.buscaRegistroLealtad("A", folio);
    Vector acc = this.busquedaCentral.buscaRegistroLealtad("A", folio);
    System.out.println("ac: " + ac);
    System.out.println("acc: " + acc);
    if (ac.size() > acc.size()) {
      return ac;
    }
    return acc;
  }

  private Vector buscaCancelados(String folio) {
    Vector ca = this.busqueda.buscaRegistroLealtad("C", folio);
    Vector cac = this.busquedaCentral.buscaRegistroLealtad("C", folio);
    System.out.println("ca: " + ca);
    System.out.println("cac: " + cac);
    if (ca.size() > cac.size()) {
      return ca;
    }
    return cac;
  }

  private Vector constuyeCancelado(String empresa)
  {
    String referencia = "" + this.sesionventa.getFechaHoraSistemaVentaLealtad();
    referencia = referencia.replace('[', ' ');
    referencia = referencia.replace(']', ' ');
    referencia = referencia.trim();
    referencia = this.sesionventa.getTerminalVenta().getId() + "" + this.sesionventa.getCajaId() + "" + referencia;
    Vector v = new Vector();
    for (int vb = 0; vb < this.vectorBoletos.size(); vb++)
    {
      Boleto bol = (Boleto)this.vectorBoletos.get(vb);
      String origen = "";
      String destino = "";
      if (bol.getAbierto().equals("S"))
      {
        origen = this.sesionventa.getDestinoId();
        destino = this.sesionventa.getOrigenId();
      }
      else {
        origen = this.sesionventa.getOrigenId();
        destino = this.sesionventa.getDestinoId();
      }

      String producto = this.sesionventa.getEmpresId() + "" + this.sesionventa.getServicioId() + "" + origen + "" + destino + "" + bol.getTipoPasajeroId();

      producto = producto + "0";
      System.out.println("producto(" + vb + ") = " + producto);
      System.out.println("producto(" + vb + ") = " + this.sesionventa.getEmpresId() + "-" + this.sesionventa.getServicioId() + "-" + origen + "-" + destino + "-" + bol.getTipoPasajeroId() + "-0");

      Vector vec = new Vector();
      vec.add(Integer.valueOf(vb));
      vec.add(producto);
      vec.add(bol.getNoTarAcum());
      vec.add(referencia);
      String unidad = "";
      if ((empresa.equals("Terminal Las Torres Puebla")) || (empresa.equals("Autobuses Unidos"))) {
        unidad = "TP";
      }
      else if ((empresa.equals("Contactos Terrestres")) || (empresa.equals("Contactos Terrestres 1")))
        unidad = "CT";
      else {
        unidad = "AM";
      }
      vec.add(unidad);
      v.add(vec);
    }
    return v;
  }

  private void accion()
  {
  }

  private boolean validacion()
  {
    if (this.sesionventa.getCajaId().equals("-1"))
    {
      this.DialogoAceptar.mostrarDialogo("¡Error en Caja!", "El nombre de la caja esta mal configurado.", Color.RED);
      return false;
    }

    if (this.jtxt_fecha.dateEditor.getTexto().length() < 10)
    {
      this.DialogoAceptar.mostrarDialogo("¡Error en Formato de fecha!", "Introduzca una fecha válida.", Color.RED);
      this.jtxt_fecha.dateEditor.setSeleccionaTexto();
      this.jtxt_fecha.dateEditor.setFoco();
      return false;
    }
    Timestamp fechaLimite = null;
    fechaLimite = Timestamp.valueOf("2010-03-22 00:00:00");
    System.out.println("fechaLimite: " + fechaLimite);
    System.out.println("fechaLimite: " + fechaLimite.getTime());
    System.out.println("fecha ingresada: " + this.jtxt_fecha.getDate().getTime());
    if (this.jtxt_fecha.getDate().getTime() < fechaLimite.getTime())
    {
      this.DialogoAceptar.mostrarDialogo("¡Error en fecha!", "Solo participan boletos vendidos a partir del 22 de marzo del 2010.", Color.RED);
      this.jtxt_fecha.dateEditor.setSeleccionaTexto();
      this.jtxt_fecha.dateEditor.setFoco();
      return false;
    }

    if (this.jtxt_folioBoleto.getText().trim().equals(""))
    {
      this.DialogoAceptar.mostrarDialogo("¡Error en Formato de Folio!", "Introduzca un Folio del Boleteo válido.", Color.RED);
      this.jtxt_folioBoleto.selectAll();
      this.jtxt_folioBoleto.requestFocus();
      return false;
    }

    if (this.jtxt_monto.getText().trim().equals(""))
    {
      this.jtxt_monto.selectAll();
      this.jtxt_monto.requestFocus();
      this.DialogoAceptar.mostrarDialogo("¡Error en Formato de Monto!", "Introduzca un Momto del Boleteo.", Color.RED);
      return false;
    }

    if (!valMultiTx(this.jtxt_folioBoleto.getText())) {
      return false;
    }
    if (!ValidaTipoPasajero())
    {
      this.DialogoAceptar.mostrarDialogo("Tipos de pasejro incorrectos", "<html>¡ Ingrese correctamento los tipos de pasajero !</html>", Color.RED);
      return false;
    }
    System.out.println("Folios: " + this.jClsSintaxis.getVectorFoliosBoletos());
    System.out.println("tipos Pasajero: " + this.tiposPasajeros);

    for (int i = 0; i < this.jClsSintaxis.getVectorFoliosBoletos().size(); i++) {
      if (i == 0)
        this.CadenaFolios = ("'" + this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString() + "'");
      else
        this.CadenaFolios = (this.CadenaFolios + ",'" + this.jClsSintaxis.getVectorFoliosBoletos().get(i).toString() + "'");
    }
    System.out.println("Cadena Folios: " + this.CadenaFolios);

    this.CadenaTiposPasajero = "";
    for (int i = 0; i < this.tiposPasajeros.size(); i++) {
      if (i == 0)
        this.CadenaTiposPasajero = ("'" + this.tiposPasajeros.get(i).toString() + "'");
      else
        this.CadenaTiposPasajero = (this.CadenaTiposPasajero + ",'" + this.tiposPasajeros.get(i).toString() + "'");
    }
    System.out.println("Cadena tipos Pasajero: " + this.CadenaTiposPasajero);

    return true;
  }

  private void limpiar()
  {
    this.jcmb_empresa.setSelectedIndex(0);
    this.jcmb_servicio.setSelectedIndex(0);
    this.jcmb_origen.setSelectedIndex(0);
    this.jcmb_destino.setSelectedIndex(0);
    this.jcmb_transaccion.setSelectedIndex(0);
    this.jcmb_tipoBoleto.setSelectedIndex(0);
    this.jtxt_tipoPasajero.setText("");
    this.jtxt_fecha.dateEditor.setTexto("");
    this.jtxt_folioBoleto.setText("");
    this.jtxt_monto.setText("");
    this.jcmb_empresa.requestFocus();
  }

  private void controlVentana(KeyEvent evt)
  {
    switch (evt.getKeyCode()) { case 49:
      if (!evt.isControlDown()) break;
      this.eventoTeclado = evt;
      try { setIcon(true); } catch (PropertyVetoException ex) {
      }
    case 50:
      if (!evt.isControlDown()) break; try {
        setIcon(true); } catch (PropertyVetoException ex) {
      } }
  }

  public KeyEvent getEventoTeclado() {
    return this.eventoTeclado;
  }
  public void setEventoTeclado(KeyEvent evento) { this.eventoTeclado = evento; }

  private boolean valMultiTx(String folios)
  {
    int r = this.jClsSintaxis.Valfolios(folios);
    if (r != 0) {
      if (r == -1) this.DialogoAceptar.mostrarDialogo("Folios incorrectos", "<html>Ingrese correctamente los folios.<br>Ejemplo, 21448001-21448005 21448009.</html>", Color.RED);
      else if (r == 1) this.DialogoAceptar.mostrarDialogo("Folios incorrectos", "<html>Algun folio tiene mas de 15 digitos.</html>", Color.RED);
      else
        this.DialogoAceptar.mostrarDialogo("Folios incorrectos", "<html>Folio inicial no puede ser<br>mayor o igual que folio final</html>", Color.RED);
      this.jtxt_folioBoleto.selectAll();
      this.jtxt_folioBoleto.requestFocusInWindow();
      return false;
    }

    return true;
  }

  private boolean ValidaTipoPasajero()
  {
    if (!obtenerTipos()) {
      this.jtxt_tipoPasajero.requestFocusInWindow();
      return false;
    }
    return true;
  }

  private boolean obtenerTipos() {
    this.tiposPasajeros = new Vector();
    String aux = this.jtxt_tipoPasajero.getText();
    boolean salida = true;

    int iTotalast = 0;

    if (aux.length() != 0)
    {
      String[] strTipoAsiento = aux.split(" ");
      long[] ctdAsientos = new long[strTipoAsiento.length];
      char[] cTipo = new char[strTipoAsiento.length];
      for (int i = 0; i < strTipoAsiento.length; i++) {
        cTipo[i] = strTipoAsiento[i].charAt(strTipoAsiento[i].length() - 1);
        if (strTipoAsiento[i].substring(0, strTipoAsiento[i].length() - 1).equals("")) ctdAsientos[i] = 1L; else
          ctdAsientos[i] = Long.parseLong(strTipoAsiento[i].substring(0, strTipoAsiento[i].length() - 1));
        iTotalast += (int)ctdAsientos[i];
      }
      if (iTotalast > this.jClsSintaxis.getVectorFoliosBoletos().size())
        salida = false;
      else {
        for (int n = 0; n < ctdAsientos.length; n++) {
          for (int t = 0; t < ctdAsientos[n]; t++) {
            this.tiposPasajeros.addElement(Character.valueOf(cTipo[n]));
          }
        }
      }
      if (this.tiposPasajeros.size() < 1) {
        this.jtxt_tipoPasajero.setText("");
        return false;
      }
      for (int n = 0; n < this.tiposPasajeros.size(); n++) {
        if (Character.isDigit(this.tiposPasajeros.get(n).toString().charAt(0))) {
          this.jtxt_tipoPasajero.setText("");
          return false;
        }
      }
    }
    if (iTotalast < this.jClsSintaxis.getVectorFoliosBoletos().size()) {
      char defaultt = 'A';
      for (int i = iTotalast; i < this.jClsSintaxis.getVectorFoliosBoletos().size(); i++) {
        this.tiposPasajeros.addElement(Character.valueOf(defaultt));
      }
    }
    if (!salida) this.jtxt_tipoPasajero.setText("");
    return salida;
  }
}