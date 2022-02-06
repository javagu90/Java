/*
 * JIFLecturaViaxer.java
 *
 * Created on 10 de noviembre de 2007, 09:10 PM
 */

package tmslecturaviaxer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyVetoException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.layout.GroupLayout;
import tms_encriptacion.EncriptarMD5;
import tmslecturaviaxer.entidad.TmsActividadesSesionTbl;
import tmslecturaviaxer.entidad.TmsBoletosBoleteraTbl;
import tmslecturaviaxer.entidad.TmsCortesTbl;
import tmslecturaviaxer.entidad.TmsEmpresasTbl;
import tmslecturaviaxer.entidad.TmsRecoleccionesTbl;
import tmslecturaviaxer.entidad.TmsSesionActividadesTbl;
import tmslecturaviaxer.entidad.TmsUsuariosTbl;
import tmslecturaviaxer.entidad.TmsVtaTiposPagoTbl;
import tmslecturaviaxer.entidad.TmsCajasTbl;
import tmslecturaviaxer.solicitud.ActividadNoEncontradoException;
import tmslecturaviaxer.solicitud.UsuarioNoEncontradoException;
import tmslecturaviaxer.util.PcInfo;
import tmslecturaviaxer.util.buscarDireccionMAC;
import tmslecturaviaxer.util.cantidad_a_letras;

/**
 *
 * @author  vgonzalez
 */
public class JIFLecturaViaxer extends javax.swing.JInternalFrame {//javax.swing.JFrame { //

  private KeyEvent eventoTeclado;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JProgressBar jProgressBar1;
  private JScrollPane jScrollPane1;
  private JLabel jlbl_barraEstado;
  private JLabel jlbl_totalRecaudar;
  private JTable jtbl_turnos;
  private JTextField jtxt_clave;
  private JTextField jtxt_nombre;
  private Object[] encabezadoPartidas = { "Partida", "Turno", "Hora Inicio", "Hora Fin", "Boletos", "Monto", "Folio Tarjeta", "Asignacion de Tarjeta", "Adicional" };
  private DefaultTableModel defaultmodelo = new DefaultTableModel((Object[][])null, this.encabezadoPartidas) {
    public boolean isCellEditable(int row, int column) { return column == 20;
    }
  };
  private boolean leer_datafare;
  private int narchivos = 0;
  private File Fboletera = new File("c:\\boleteraTMS\\SYSTEM\\BOLETERA");

  private boolean guardar_datafare = false;
  private TmsLecturaViaxerManagedBean busquedas = new TmsLecturaViaxerManagedBean();
  private Timestamp fecha_servidor = null;
  private String[] ArregloBoletos = new String[3000];
  private Vector datosIniciales;
  private String nombre_recaudador = "";
  private boolean inicioGral = true;
  private long sesionId = 1L;
  private long idusuario = 999L;
  private long idmenu = 0L;
  private long corteId = 0L;
  private String resultadoCorridas = "";
  private boolean respuestaSN = true;
  private boolean reimpresion = false;
  private boolean existeimp = false;
  private long cajaId = 0L;
  private PrintService impresoraEncontrada = null;
  private String impresoraTicket = "";
  private String puerto = "";
  private TmsCortesTbl corteActual;
  private TmsCajasTbl caja;
  private buscarDireccionMAC bMac;
  private String idTerminal;
  private float montorecoleccion = Float.valueOf("5000.00").floatValue();
  private float toperecoleccion = Float.valueOf("7000.00").floatValue();
  private double acumulado = 0.0D;
  private Vector funciones;
  private Vector fauditables;
  private String autorizado = "";
  private jdlgDatosSupervisor dlgSupervisor;
  private String operador = "";
  private String empresa = "Terminal Las Torres Puebla";
  private String fecha = "";
  private boolean pendientes = false;
  private String fechaHoraBoleto = "";
  private String hora_Inicial = "";
  private String hora_Final = "";
  private String turno = "";
  private String monto_Total = "";
  private String ruta = "";
  private String tarjeta = "";
  private String direccion = "";
  private String fileName = "";
  private String bolOrigen = "";
  private String bolDestino = "";
  private String bolHora = "";
  private String bolClase = "Adulto";
  private String bolCategoria = "Efectivo";
  private String bolCantidad = "0";
  private String bolSin_uso = "";
  private String bolCard_num = "";
  private int aumentaTamano = 0;
  private TmsBoletosBoleteraTbl boleto = new TmsBoletosBoleteraTbl();
  private int nboletos = 0;
  private double totalImporte = 0.0D;
  private String folio = "";
  private String nombreservicio = "";
  private String letraservicio = "";
  private String servicio = "0";
  private String origent = "";
  private String destinot = "";
  private String origenSolo = "";
  private String bhora = "";
  private String borigen = "";
  private String bfecha_bol = "";
  private String basignacion = "";
  private String badicional = "";
  private String btipo = "";
  private String bdestino = "";
  private String bprecio = "";
  private String linea = "";
  private File archivoActivo;
  private boolean pasoRecol = false;
  private boolean imprimirTiket = true;
  private boolean buscaOperador = false;
    private boolean datosPendientes;

  public JIFLecturaViaxer(Vector pDatosIniciales)
  {
    this.datosIniciales = pDatosIniciales;
    setIdmenu(Long.valueOf(this.datosIniciales.get(4).toString()).longValue());
    setIdusuario(Long.valueOf(this.datosIniciales.get(0).toString()).longValue());
    this.nombre_recaudador = this.datosIniciales.get(2).toString();
    setSesionId(this.datosIniciales.get(3).toString());
    cargarValores();
    BuscaCorte();
    setInicioGral(this.respuestaSN);
    if (this.respuestaSN)
    {
      buscaImpresora();
      initComponents();
      setTitle("TMSRecauda  Rev10.11.15");
      this.jtbl_turnos.setModel(this.defaultmodelo);
      resizeColumnasPartidas();

      Vector vlim = (Vector)this.busquedas.variosFacadeRemote.buscaMontoRecoleccion();
      if (vlim.size() > 0)
      {
        Vector lim = (Vector)vlim.get(0);
        this.montorecoleccion = Float.valueOf(lim.get(0).toString()).floatValue();
      }
      Vector vtop = (Vector)this.busquedas.variosFacadeRemote.buscaTopeRecoleccion();
      if (vtop.size() > 0)
      {
        Vector top = (Vector)vtop.get(0);
        this.toperecoleccion = Float.valueOf(top.get(0).toString()).floatValue();
      }
      //this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Lectura de VIAXER | <font color=FF0000>F6</font> Leer Datos VIAXER | <font color=FF0000> F11 </font> Ver Turnos Pendientes  | <font color=FF0000> F12 </font> Recoleccion   | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
        this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Lectura de VIAXER | <font color=FF0000>ENTER</font> Buscar Operador | <font color=FF0000> F11 </font> Ver Turnos Pendientes | <font color=FF0000> F12 </font> Recoleccion   | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
      this.jlbl_barraEstado.setFocusTraversalKeysEnabled(false);

      this.jlbl_barraEstado.registerKeyboardAction(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
        }
      }
      , KeyStroke.getKeyStroke(121, 0), 0);
      this.jlbl_barraEstado.requestFocus();
    }
  }

  private void BuscaCorte()
  {
    Vector corte = (Vector)this.busquedas.variosFacadeRemote.buscaCortePorUsuario("R_AMPERSA", getIdusuario());
    TmsActividadesSesionTbl actividasInicioSesion = this.busquedas.actividadesSesionTblFacadeRemote.buscarActidadPorClave("INISES");
    System.out.println("la sesion actividad es: " + actividasInicioSesion.getNombreActividad());
    PcInfo pc = new PcInfo();
    Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
    this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
    try {
      this.caja = this.busquedas.cajasTblFacadeRemote.buscarCajaPorEquipo(pc.getHostName().toString().toUpperCase());
    } catch (Exception e) {
      new jdlg_error("¡No esta configurada este equipo como caja! ", "Favor de contactar al administrador del sistema", "Equipo no configurado").setVisible(true);
      System.exit(0);
    }
    String ubicacion = this.caja.getUbicacionId().toString();
    this.cajaId = this.caja.getCajaId().longValue();
    Vector vterlocal = (Vector)this.busquedas.cajasTblFacadeRemote.queryBuscaTerminalLocal();
    String terminalLocal = vterlocal.get(0).toString().trim();
    System.out.println("Terminal local = " + terminalLocal + " ==> Ubicacion= " + ubicacion);
    if (!terminalLocal.equals(ubicacion))
    {
      new jdlg_error("¡La caja no es valida en esta terminal! ", "Favor de  contactar al Administrador del Sistema", "Acceso invalido").setVisible(true);
      this.respuestaSN = false;
      return;
    }

    try
    {
      this.caja = this.busquedas.cajasTblFacadeRemote.buscarCajaPorEquipo(pc.getHostName().toUpperCase());
      if (this.caja == null)
      {
        new jdlg_error("¡No esta configurado este equipo como caja! ", "Favor de contactar al administrador del sistema", "Equipo no configurado").setVisible(true);

        this.respuestaSN = false;
        return;
      }
      try {
        this.bMac = new buscarDireccionMAC();
      } catch (IOException ex) {
        ex.printStackTrace();
      }

      String macr = this.caja.getDireccionMac();
      System.out.println("La mac registrada: " + macr);
      if (!this.bMac.buscarMac(macr))
      {
        new jdlg_error("¡No esta configurado este equipo como caja, o esta mal configurado! ", "Favor de contactar al administrador del sistema", "Equipo no configurado").setVisible(true);

        this.respuestaSN = false;
        return;
      }
    } catch (Exception e) {
      new jdlg_error("¡No esta configurado este equipo como caja! ", "Favor de contactar al administrador del sistema", "Equipo no configurado").setVisible(true);

      this.respuestaSN = false;
      return;
    }
    Vector TER = (Vector)this.busquedas.variosFacadeRemote.queryBuscaIdTerminal();
    this.idTerminal = TER.get(0).toString();
    String te = this.idTerminal;
    if (this.idTerminal.length() < 3)
    {
      for (int i = this.idTerminal.length(); i < 3; i++)
        te = te + "0";
    }
    if (this.idTerminal.length() >= 3) {
      te = this.idTerminal.substring(0, 2);
    }
    this.idTerminal = te;

    if (corte.size() == 0)
    {
      System.out.println("No hay cortes pendientes...........");
      TmsCortesTbl cortenvo = new TmsCortesTbl();
      TmsUsuariosTbl usuarioEncontrado = new TmsUsuariosTbl();
      usuarioEncontrado = this.busquedas.usuariosTblFacadeRemote.find(BigDecimal.valueOf(getIdusuario()));

      cortenvo.setNombreSesion("R_AMPERSA");
      cortenvo.setUsuarioId(usuarioEncontrado);
      cortenvo.setEstadoCorte("P");
      cortenvo.setAutorizadoPor(usuarioEncontrado.getUsuarioId().toBigInteger());
      cortenvo.setCreadoPor(usuarioEncontrado.getUsuarioId().intValue());
      cortenvo.setFechaCreacion(this.fecha_servidor);
      cortenvo.setUltimaActualizacionPor(usuarioEncontrado.getUsuarioId().intValue());
      cortenvo.setUltimaFechaActualizacion(this.fecha_servidor);
      TmsCortesTbl corten = this.busquedas.cortesTblFacadeRemote.insertarCorte(cortenvo, this.idTerminal);
      setCorteid(corten.getCorteId().longValue());
      setCorteActual(corten);
      System.out.println("El id del corte Nuevo es: " + getCorteid());
      TmsSesionActividadesTbl inicioSesion = new TmsSesionActividadesTbl();
      inicioSesion.setCorteId(corten);
      inicioSesion.setCajaId(this.caja);
      inicioSesion.setActividadSesionId(actividasInicioSesion);
      inicioSesion.setValorActividad(this.nombre_recaudador + "-" + this.caja.getNombreEquipo());
      inicioSesion.setFechaHoraActividad(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setCreadoPor(this.idusuario);
      inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setUltimaActualizacionPor(this.idusuario);
      inicioSesion.setUltimaFechaActualizacion(new Date(this.fecha_servidor.getTime()));
      this.busquedas.sesionActividadesTblFacadeRemote.create(inicioSesion, this.idTerminal);
      System.out.println("Se registro un inicio de sesion en la BD");
    }
    else
    {
      jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Corte pendiente", "No se ha realizado su corte. ¿Desea Asignarse Nuevamente a la Recaudacion?");
      psn.setVisible(true);
      if (this.respuestaSN)
      {
        Vector crt = (Vector)corte.get(0);
        System.out.println("El id del corte pendiente es: " + crt);
        setCorteid(Long.valueOf(crt.get(0).toString()).longValue());
        TmsCortesTbl corteBuscado = this.busquedas.cortesTblFacadeRemote.find(BigDecimal.valueOf(getCorteid()));
        setCorteActual(corteBuscado);
        TmsSesionActividadesTbl inicioSesion = new TmsSesionActividadesTbl();
        inicioSesion.setCorteId(corteBuscado);
        inicioSesion.setCajaId(this.caja);
        inicioSesion.setActividadSesionId(actividasInicioSesion);
        inicioSesion.setValorActividad(this.nombre_recaudador + "-" + this.caja.getNombreEquipo() + "-Datafare");
        inicioSesion.setFechaHoraActividad(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setCreadoPor(this.idusuario);
        inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setUltimaActualizacionPor(this.idusuario);
        inicioSesion.setUltimaFechaActualizacion(new Date(this.fecha_servidor.getTime()));
        this.busquedas.sesionActividadesTblFacadeRemote.create(inicioSesion, this.idTerminal);
        System.out.println("Se registro un inicio de sesion en la BD");

        Vector vmnbol = (Vector)this.busquedas.variosFacadeRemote.buscaBoletosDATAFARERecolectados(Long.valueOf(crt.get(0).toString()).longValue());
        System.out.println("Monto Boletos Boletera" + vmnbol);
        double mnbol = Double.valueOf(vmnbol.get(0).toString()).doubleValue();
        Vector vmnmab = (Vector)this.busquedas.variosFacadeRemote.buscaVentaAbordoManual(Long.valueOf(crt.get(0).toString()).longValue());
        System.out.println("Monto Venta Abordo Manual" + vmnmab);
        double mnmab = Double.valueOf(vmnmab.get(0).toString()).doubleValue();

        Vector vmnac = (Vector)this.busquedas.variosFacadeRemote.buscaMontoAcumulado(Long.valueOf(crt.get(0).toString()).longValue());
        Vector vmnacres = (Vector)this.busquedas.variosFacadeRemote.buscaMontoAcumuladoParaRestar(Long.valueOf(crt.get(0).toString()).longValue());
        System.out.println("Monto Acumulado" + vmnac);
        System.out.println("Monto Acumulado para Restar" + vmnacres);

        double mac0 = Double.valueOf(vmnac.get(0).toString()).doubleValue() - Double.valueOf(vmnacres.get(0).toString()).doubleValue();
        double mnac = mac0 + mnbol + mnmab;
        Vector vmnrec = (Vector)this.busquedas.variosFacadeRemote.buscaMontoRecolecciones(Long.valueOf(crt.get(0).toString()).longValue(), Long.valueOf(this.datosIniciales.get(1).toString()).longValue());
        System.out.println("Monto Recolecciones" + vmnrec);
        double mnrec = Double.valueOf(vmnrec.get(0).toString()).doubleValue();
        if (mnrec > mnac)
          setAcumulado(0.0D);
        if (mnrec <= mnac)
          setAcumulado(mnac - mnrec);
      }
      else {
        dispose();
      }
    }
  }

  private void buscaImpresora() {
    Vector vimpre = (Vector)this.busquedas.cajasTblFacadeRemote.queryBuscaImpresoraTikets(this.cajaId);
    if (vimpre.size() != 0)
    {
      Vector impre = (Vector)vimpre.get(0);

      System.out.println("Se encontraron lassiguientes impresoras para tickets: " + vimpre);
      this.impresoraTicket = impre.get(0).toString();
      this.puerto = impre.get(1).toString();
    }
    else {
      new jdlg_advertencia("¡No se encontro impresora de Tickets relacionada con esta caja!", "", "No hay impresora de Tickets").setVisible(true);
    }
  }

  private void resizeColumnasPartidas() {
    TableColumn columinv = this.jtbl_turnos.getColumnModel().getColumn(0); columinv.setMinWidth(70); columinv.setMaxWidth(70); columinv.setPreferredWidth(70);
    columinv = this.jtbl_turnos.getColumnModel().getColumn(1); columinv.setMinWidth(70); columinv.setMaxWidth(70); columinv.setPreferredWidth(70);
    columinv = this.jtbl_turnos.getColumnModel().getColumn(2); columinv.setMinWidth(70); columinv.setMaxWidth(70); columinv.setPreferredWidth(70);
    TableCellRenderer centerRenderer = new CenterRenderer();
    columinv.setCellRenderer(centerRenderer);
    columinv = this.jtbl_turnos.getColumnModel().getColumn(3); columinv.setMinWidth(70); columinv.setMaxWidth(70); columinv.setPreferredWidth(70);
    centerRenderer = new CenterRenderer();
    columinv.setCellRenderer(centerRenderer);
    columinv = this.jtbl_turnos.getColumnModel().getColumn(4); columinv.setMinWidth(70); columinv.setMaxWidth(70); columinv.setPreferredWidth(70);
    columinv = this.jtbl_turnos.getColumnModel().getColumn(5); columinv.setMinWidth(70); columinv.setMaxWidth(70); columinv.setPreferredWidth(70);
    TableCellRenderer rightRenderer = new RightRenderer();
    columinv.setCellRenderer(rightRenderer);
    columinv = this.jtbl_turnos.getColumnModel().getColumn(6); columinv.setMinWidth(170); columinv.setMaxWidth(170); columinv.setPreferredWidth(170);
    columinv = this.jtbl_turnos.getColumnModel().getColumn(8); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);
  }

  public void imprimir_recibo_rec(String[] datos, String npq)
  {
    if (!this.impresoraTicket.equals(""))
    {
      if ((this.puerto.equals("LPT1")) || (this.puerto.equals("LPT2")) || (this.puerto.equals("LPT3")) || (this.puerto.equals("LPT4")) || (this.puerto.equals("COM1")) || (this.puerto.equals("COM2")) || (this.puerto.equals("ARCHIVO")))
      {
        if (!imprimir_recibo_rec_LPT(datos, this.puerto))
          new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
      }
      if ((this.puerto.equals("RED")) || (this.puerto.equals("USB")))
      {
        if (!imprimir_recibo_rec_LPT(datos, this.impresoraTicket))
          new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
      }
    }
    else
    {
      new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no sera impreso", " El numero de paquete de la recoleccion es: " + npq, "Impresora no encontrada").setVisible(true);
      return;
    }
    Jdlg_Pregunta2 p = new Jdlg_Pregunta2("Reimpresion de comprobante", npq);
    p.setVisible(true);
    if (this.reimpresion)
      imprimir_recibo_rec(datos, npq);
    else
      verificarAumulado();
  }

  private void recolecciones() {
    Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
    String estado = vestado.get(0).toString();
    if (estado.equals("CERRADA")) {
      new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrara automaticamente", "La sesion fue cerrada").setVisible(true);
      System.exit(0);
    }
    String[] data = new String[10];
    int indexfun = this.funciones.indexOf("6009");
    if (indexfun >= 0)
    {
      this.autorizado = ("" + getIdusuario());
      Vector npaq = (Vector)this.busquedas.variosFacadeRemote.queryBuscaNumeroPaqueteActual();
      data[0] = ("" + (Integer.valueOf(npaq.get(0).toString()).intValue() + 1));
      data[1] = this.datosIniciales.get(2).toString();
      data[2] = "Recoleccion Parcial";
      data[3] = ("" + this.montorecoleccion);
      data[4] = this.datosIniciales.get(1).toString();
      data[5] = ("" + this.autorizado);
      vista_pago_impresion_recolecciones v = new vista_pago_impresion_recolecciones(data, this.autorizado);
      v.setVisible(true);
    }
    else if (validarDatosSupervisor("6009"))
    {
      Vector npaq = (Vector)this.busquedas.variosFacadeRemote.queryBuscaNumeroPaqueteActual();
      data[0] = ("" + (Integer.valueOf(npaq.get(0).toString()).intValue() + 1));
      data[1] = this.datosIniciales.get(2).toString();
      data[2] = "Recoleccion Parcial";
      data[3] = ("" + this.montorecoleccion);
      data[4] = this.datosIniciales.get(1).toString();
      data[5] = ("" + this.autorizado);
      vista_pago_impresion_recolecciones v = new vista_pago_impresion_recolecciones(data, this.autorizado);
      v.setVisible(true);
    }
  }

  public void actualizaAcumulado()
  {
    Vector vmnbol = (Vector)this.busquedas.variosFacadeRemote.buscaBoletosDATAFARERecolectados(getCorteid());
    System.out.println("Monto Boletos Boletera" + vmnbol);
    double mnbol = Double.valueOf(vmnbol.get(0).toString()).doubleValue();
    Vector vmnmab = (Vector)this.busquedas.variosFacadeRemote.buscaVentaAbordoManual(getCorteid());
    System.out.println("Monto Venta Abordo Manual" + vmnmab);
    double mnmab = Double.valueOf(vmnmab.get(0).toString()).doubleValue();

    Vector vmnac = (Vector)this.busquedas.variosFacadeRemote.buscaMontoAcumulado(getCorteid());
    Vector vmnacres = (Vector)this.busquedas.variosFacadeRemote.buscaMontoAcumuladoParaRestar(getCorteid());
    System.out.println("Monto Acumulado" + vmnac);
    System.out.println("Monto Acumulado para Restar" + vmnacres);

    double mac0 = Double.valueOf(vmnac.get(0).toString()).doubleValue() - Double.valueOf(vmnacres.get(0).toString()).doubleValue();
    double mnac = mac0 + mnbol + mnmab;
    Vector vmnrec = (Vector)this.busquedas.variosFacadeRemote.buscaMontoRecolecciones(getCorteid(), Long.valueOf(this.datosIniciales.get(1).toString()).longValue());
    System.out.println("Monto Recolecciones" + vmnrec);
    double mnrec = Double.valueOf(vmnrec.get(0).toString()).doubleValue();
    if (mnrec > mnac)
      setAcumulado(0.0D);
    if (mnrec <= mnac)
      setAcumulado(mnac - mnrec);
  }

  public void verificarAumulado() {
    this.pasoRecol = true;
    System.out.println("Acumulado       :  " + this.acumulado);
    System.out.println("MontoRecoleccion:  " + this.montorecoleccion);
    System.out.println("TopeRecoleccion :  " + this.toperecoleccion);
    if ((this.acumulado >= this.montorecoleccion) && (this.acumulado < this.toperecoleccion)) {
      new jdlg_advertencia("¡Solicita al supervisor una recoleccion!    ", "", "Recoleccion Pendiente").setVisible(true);
    }
    else {
      System.out.println("compara  " + this.acumulado + " >= " + this.toperecoleccion);
      if (this.acumulado >= this.toperecoleccion)
      {
        new jdlg_error("¡En este momento ya no puedes recaudar mas efectivo!   ", "          ¡Realiza una recoleccion!", "Recoleccion Necesaria").setVisible(true);
        this.pasoRecol = false;
      }
    }
  }

  public void cargarValores() {
    Vector vFunciones = (Vector)this.busquedas.variosFacadeRemote.buscarFuncionesPorMenuId(getIdmenu());
    System.out.println("vFunciones= " + vFunciones);
    this.funciones = new Vector();
    this.fauditables = new Vector();
    for (int i = 0; i < vFunciones.size(); i++)
    {
      Vector fun = (Vector)vFunciones.get(i);
      this.funciones.add(fun.get(0).toString());
      this.fauditables.add(fun.get(1).toString());
    }
  }

  private void mostrarDialogoSupervisor(String fun) {
    this.dlgSupervisor = new jdlgDatosSupervisor(true, "TMS - Validar Supervisor         Funcion: " + fun);
    this.dlgSupervisor.centrarDialogo();
    this.dlgSupervisor.setVisible(true);
  }

  private boolean validarDatosSupervisor(String nfuncion) {
    EncriptarMD5 pwdEnc = new EncriptarMD5();
    mostrarDialogoSupervisor(nfuncion);
    String numeroUsr = this.dlgSupervisor.getNumeroUsuario();
    String claveSuper = this.dlgSupervisor.getContrasenaUsuario();
    boolean valido = false;
    String respuesta = "";
    if ((numeroUsr.equals("nada")) && (claveSuper.equals("nada"))) {
      valido = false;
    }
    else {
      try {
        respuesta = this.busquedas.usuariosTblFacadeRemote.esUsuarioSupervisor(this.dlgSupervisor.getNumeroUsuario(), pwdEnc.encriptar(this.dlgSupervisor.getContrasenaUsuario()), nfuncion);
      } catch (UsuarioNoEncontradoException exu) {
        System.out.println(exu.getMessage());
        return false;
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
        return false;
      }
      if (respuesta.equals("no encontrado"))
      {
        valido = false;
        new jdlg_error("¡Numero de usuario o Contrasenia invalidos!   ", " Favor de intentar nuevamente", "Datos incorrector").setVisible(true);
      }
      else if (respuesta.equals("invalido"))
      {
        valido = false;
        new jdlg_error("¡Tu perfil de usuario no te permite realizar esta funcion!   ", " Favor de contactar al Administrador del Sistema", "Permiso Denegado").setVisible(true);
      }
      else
      {
        valido = true;
        this.autorizado = respuesta;
      }

    }

    return valido;
  }

  private void initComponents()
  {
    this.jPanel1 = new JPanel();
    this.jScrollPane1 = new JScrollPane();
    this.jtbl_turnos = new JTable();
    this.jlbl_barraEstado = new JLabel();
    this.jPanel2 = new JPanel();
    this.jLabel1 = new JLabel();
    this.jtxt_clave = new JTextField();
    this.jLabel2 = new JLabel();
    this.jtxt_nombre = new JTextField();
    this.jLabel3 = new JLabel();
    this.jProgressBar1 = new JProgressBar();
    this.jLabel4 = new JLabel();
    this.jlbl_totalRecaudar = new JLabel();

    setDefaultCloseOperation(2);
    setIconifiable(true);
    addComponentListener(new ComponentAdapter() {
      public void componentShown(ComponentEvent evt) {
        JIFLecturaViaxer.this.formComponentShown(evt);
      }
    });
    this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Lector de Datos VIAXER", 2, 0, new Font("Tahoma", 1, 14)));
    this.jScrollPane1.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent evt) {
        JIFLecturaViaxer.this.jScrollPane1FocusGained(evt);
      }
    });
    this.jtbl_turnos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));

    this.jtbl_turnos.setEnabled(false);
    this.jtbl_turnos.setFocusable(false);
    this.jtbl_turnos.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent evt) {
        JIFLecturaViaxer.this.jtbl_turnosFocusGained(evt);
      }
      public void focusLost(FocusEvent evt) {
        JIFLecturaViaxer.this.jtbl_turnosFocusLost(evt);
      }
    });
    this.jtbl_turnos.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFLecturaViaxer.this.jtbl_turnosKeyPressed(evt);
      }
    });
    this.jScrollPane1.setViewportView(this.jtbl_turnos);

    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(2, jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jScrollPane1, -1, 853, 32767).addContainerGap()));

    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(2, jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jScrollPane1, -1, 452, 32767)));

    this.jlbl_barraEstado.setFont(new Font("Tahoma", 1, 11));
    this.jlbl_barraEstado.setText("jLabel1");
    this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));
    this.jlbl_barraEstado.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFLecturaViaxer.this.jlbl_barraEstadoKeyPressed(evt);
      }
    });
    this.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Datos del Operador", 0, 0, new Font("Tahoma", 1, 12)));
    this.jLabel1.setFont(new Font("Tahoma", 1, 12));
    this.jLabel1.setText("Clave:");
    this.jLabel1.setFocusable(false);

    //this.jtxt_clave.setEditable(false);
    //this.jtxt_clave.setFocusable(false);
    this.jtxt_clave.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
       jtxt_claveKeyPressed(evt);
      }
    });

    this.jLabel2.setFont(new Font("Tahoma", 1, 12));
    this.jLabel2.setText("Nombre:");
    this.jLabel2.setFocusable(false);

    this.jtxt_nombre.setEditable(false);
    this.jtxt_nombre.setFocusable(false);

    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(this.jLabel1).addPreferredGap(0).add(this.jtxt_clave, -2, 82, -2).add(19, 19, 19).add(this.jLabel2).addPreferredGap(0).add(this.jtxt_nombre, -1, 657, 32767).addContainerGap()));

    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(jPanel2Layout.createParallelGroup(3).add(this.jLabel1).add(this.jtxt_clave, -2, -1, -2).add(this.jtxt_nombre, -2, -1, -2).add(this.jLabel2)).addContainerGap(20, 32767)));

    this.jLabel3.setFont(new Font("Tahoma", 1, 11));
    this.jLabel3.setText("Rev10.11.14");

    this.jLabel4.setFont(new Font("Tahoma", 1, 14));
    this.jLabel4.setForeground(Color.blue);
    this.jLabel4.setText("Total a recaudar: ");

    this.jlbl_totalRecaudar.setFont(new Font("Tahoma", 1, 14));
    this.jlbl_totalRecaudar.setForeground(Color.blue);
    this.jlbl_totalRecaudar.setHorizontalAlignment(4);
    this.jlbl_totalRecaudar.setText("0.0");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel2, -1, -1, 32767).addContainerGap()).add(this.jProgressBar1, -1, 909, 32767).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()).add(layout.createSequentialGroup().add(31, 31, 31).add(this.jLabel4).addPreferredGap(0).add(this.jlbl_totalRecaudar, -2, 76, -2).addContainerGap(675, 32767)).add(2, layout.createSequentialGroup().addContainerGap(831, 32767).add(this.jLabel3, -2, 78, -2)).add(this.jlbl_barraEstado, -1, 909, 32767));

    layout.setVerticalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().add(this.jLabel3).addPreferredGap(0).add(this.jPanel2, -2, -1, -2).addPreferredGap(0).add(this.jPanel1, -1, -1, 32767).add(14, 14, 14).add(layout.createParallelGroup(3).add(this.jLabel4).add(this.jlbl_totalRecaudar)).add(15, 15, 15).add(this.jProgressBar1, -2, 26, -2).addPreferredGap(0).add(this.jlbl_barraEstado, -2, 51, -2)));

    pack();
  }

  private void jtbl_turnosFocusLost(FocusEvent evt) {
    this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Lectura de VIAXER | <font color=FF0000>F6</font> Leer Datos VIAXER | <font color=FF0000> F11 </font> Ver Turnos Pendientes  | <font color=FF0000> F12 </font> Recoleccion   | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
  }

  private void jtbl_turnosFocusGained(FocusEvent evt)
  {
    this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Lectura de VIAXER | <font color=FF0000>F6</font> Leer Datos VIAXER | <font color=FF0000> F9 </font> Asignar otro Folio | <font color=FF0000> F11 </font> Salir de turnos pendientes</html>");
  }

  private void jScrollPane1FocusGained(FocusEvent evt)
  {
  }

  private void jtxt_claveKeyPressed(KeyEvent evt) {
      if (evt.getKeyCode() == evt.VK_ENTER)
      {
        this.operador=this.jtxt_clave.getText();
        Vector voper = (Vector)this.busquedas.variosFacadeRemote.queryBuscaOperadorPorClave(this.operador);
            if (voper.size() == 0) {
              this.jtxt_nombre.setText("Operador Desconocido");
              this.buscaOperador = false;
              this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Lectura de VIAXER | <font color=FF0000>ENTER</font> Buscar Operador | <font color=FF0000> F11 </font> Ver Turnos Pendientes | <font color=FF0000> F12 </font> Recoleccion   | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
              this.jtxt_clave.requestFocusInWindow();
            }
            else {
              Vector voper1 = (Vector)voper.get(0);
              if ((voper1.get(1) != null) && (voper1.get(1).toString().indexOf("OPERADOR") == -1))
                this.jtxt_nombre.setText(voper1.get(0) + " " + voper1.get(1) + " " + voper1.get(2));
              else
                this.jtxt_nombre.setText(voper1.get(0) + " " + voper1.get(2));
              this.buscaOperador = true;
              buscaGuardaDatosViaxer();
              this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Lectura de VIAXER | <font color=FF0000>F6</font> Leer Datos VIAXER | <font color=FF0000> F11 </font> Ver Turnos Pendientes  | <font color=FF0000> F12 </font> Recoleccion   | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
              this.jlbl_barraEstado.requestFocusInWindow();

            }
      }
    if (evt.getKeyCode() == 117) //VK_F6
    {
      actualizaAcumulado();
      verificarAumulado();
      if (!this.pasoRecol)
        return;
      if(this.buscaOperador)
        buscaGuardaDatosViaxer();
      
    }



    if (evt.getKeyCode() == 123)//VK_F12
    {
      Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
      String estado = vestado.get(0).toString();
      if (estado.equals("CERRADA")) {
        new jdlg_error("¡La sesion es invalida!", "El Sistema se cerrara automaticamente", "La sesion fue cerrada").setVisible(true);
        System.exit(0);
      }
      recolecciones();
    }

    if (evt.getKeyCode() == 122) //VK_F11
    {
      System.out.println("Se presiono F11...");
      if (this.jtbl_turnos.getRowCount() > 0)
      {
        new jdlg_error("¡La tabla de turnos debe estar vacia!", "", "existen datos por guardar").setVisible(true);
        return;
      }
      Vector vTurnosPendientes = (Vector)this.busquedas.variosFacadeRemote.buscaTurnosPendientesViaxer();
      System.out.println("Turnos Pendientes: " + vTurnosPendientes);
      if (vTurnosPendientes.size() > 0)
      {
        this.pendientes = true;
        this.defaultmodelo.setDataVector((Object[][])null, this.encabezadoPartidas);
        for (int i = 0; i < vTurnosPendientes.size(); i++)
        {
          Vector turnoPendiente = (Vector)vTurnosPendientes.get(i);
          System.out.println("Procesa: " + turnoPendiente);
          try
          {
            String turnop = turnoPendiente.get(0).toString();
            String numbol = turnoPendiente.get(1).toString();
            String impbol = turnoPendiente.get(2).toString();

            StringTokenizer lineaTurno = new StringTokenizer(turnop, "-");
            String toperador = lineaTurno.nextToken();
            String tturno = lineaTurno.nextToken();
            String tmonto = lineaTurno.nextToken();
            tmonto = impbol;
            String tfecha = lineaTurno.nextToken();
            String thoraInicial = lineaTurno.nextToken();
            String thoraFinal = lineaTurno.nextToken();
            //22-OCT-2015 ABa
            thoraFinal = this.busquedas.variosFacadeRemote.getHoraFinal(turnop);
            System.out.println("Adicional2- 2:" + thoraFinal);
            String[] nr = new String[9];
            nr[0] = ("00" + (i + 1));
            nr[1] = tturno;
            nr[2] = thoraInicial;
            nr[3] = thoraFinal;
            nr[4] = numbol;
            nr[5] = ("" + Double.valueOf(tmonto));
            nr[6] = "PENDIENTE";
            nr[7] = "Manual";
            nr[8] = turnop;
            this.defaultmodelo.addRow(nr);
          } catch (NoSuchElementException ex) {
            System.out.println("El turno pendiente " + turnoPendiente + " para relacionar esta mal guardado");
          }
        }
        this.jtbl_turnos.setEnabled(true);
        this.jtbl_turnos.setFocusable(true);
        this.jtbl_turnos.setRowSelectionInterval(0, 0);
        this.jtbl_turnos.setColumnSelectionInterval(6, 6);
        this.jtbl_turnos.requestFocus();
      }
      else {
        new jdlg_advertencia("¡No se encontraron turnos pendientes por relacionar!", "", "No hay Turno pendientes").setVisible(true);
      }
    }
    if ((evt.getKeyCode() == 49) && (evt.isControlDown())) {
      setEventoTeclado(evt);
      try { setIcon(true); } catch (PropertyVetoException ex) {
      }return;
    }

    if ((evt.getKeyCode() == 50) && (evt.isControlDown())) {
      try { setIcon(true); } catch (PropertyVetoException ex) {
      }return;
    }

}

  private void jtbl_turnosKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 120)
    {
      if (this.pendientes)
      {
        StringTokenizer lineaTurno = new StringTokenizer(this.jtbl_turnos.getValueAt(this.jtbl_turnos.getSelectedRow(), 8).toString(), "-");
        String toperador = lineaTurno.nextToken();
        this.operador = toperador;
      }

      System.out.println("Operador para buscar tarjetas:" + this.operador);
      String folioAnterior = this.jtbl_turnos.getValueAt(this.jtbl_turnos.getSelectedRow(), 6).toString();
      String adic = this.jtbl_turnos.getValueAt(this.jtbl_turnos.getSelectedRow(), 8).toString();

      Vector vTarjetasOperador = (Vector)this.busquedas.variosFacadeRemote.BuscaTarjetasPendientes(this.operador);
      System.out.println("Modificar tarjeta: Tarjetas Pendientes por operador= " + vTarjetasOperador);

      JdlgTarjetasPendOper dtp = new JdlgTarjetasPendOper(this.busquedas, vTarjetasOperador);
      dtp.setVisible(true);
      String folioNuevo = dtp.getFolioTarjeta();

      System.out.println("Folio Anterior: " + folioAnterior + "  " + adic);
      System.out.println("Folio Nuevo: " + folioNuevo);
      if (!folioNuevo.equals("nada"))
      {
        if (folioAnterior.equals("PENDIENTE"))
        {
          Vector nums = new Vector();
          for (int j = 0; j <= this.nboletos - 1; j++)
          {
            String linea = this.ArregloBoletos[j];
            if (linea.indexOf(adic) < 0)
              continue;
            nums.add(Integer.valueOf(j));
            System.out.println("guarda el: " + j);
          }

          for (int k = 0; k <= nums.size() - 1; k++)
          {
            int n = Integer.valueOf(nums.get(k).toString()).intValue();
            String linea = this.ArregloBoletos[n];
            String lineaNueva = "";
            String lineaNueva2 = "";
            lineaNueva = linea.replaceAll(folioAnterior, folioNuevo);
            lineaNueva2 = lineaNueva.replaceAll("Automatica", "Manual");
            this.ArregloBoletos[n] = lineaNueva2;
          }
          this.jtbl_turnos.setValueAt(folioNuevo, this.jtbl_turnos.getSelectedRow(), 6);
          this.jtbl_turnos.setValueAt("Manual", this.jtbl_turnos.getSelectedRow(), 7);
        }
        else
        {
          String adicion = "";
          if (folioNuevo.equals("PENDIENTE"))
            adicion = this.jtxt_clave.getText() + "-" + this.jtbl_turnos.getValueAt(this.jtbl_turnos.getSelectedRow(), 1).toString() + "-" + this.jtbl_turnos.getValueAt(this.jtbl_turnos.getSelectedRow(), 5).toString() + "-" + this.fecha + "-" + this.jtbl_turnos.getValueAt(this.jtbl_turnos.getSelectedRow(), 2).toString() + "-" + this.jtbl_turnos.getValueAt(this.jtbl_turnos.getSelectedRow(), 3).toString();
          Vector nums = new Vector();
          for (int j = 0; j <= this.nboletos - 1; j++)
          {
            String linea = this.ArregloBoletos[j];
            if (linea.indexOf(folioAnterior) < 0)
              continue;
            nums.add(Integer.valueOf(j));
            System.out.println("guarda el: " + j);
          }

          for (int k = 0; k <= nums.size() - 1; k++)
          {
            int n = Integer.valueOf(nums.get(k).toString()).intValue();
            String linea = this.ArregloBoletos[n];
            String lineaNueva = "";
            String lineaNueva2 = "";
            lineaNueva = linea.replaceAll(folioAnterior, folioNuevo);
            lineaNueva2 = lineaNueva.replaceAll("Automatica", "Manual");
            if (folioNuevo.equals("PENDIENTE"))
              lineaNueva2 = lineaNueva2 + " " + adicion;
            this.ArregloBoletos[n] = lineaNueva2;
          }
          this.jtbl_turnos.setValueAt(folioNuevo, this.jtbl_turnos.getSelectedRow(), 6);
          this.jtbl_turnos.setValueAt("Manual", this.jtbl_turnos.getSelectedRow(), 7);
          if (folioNuevo.equals("PENDIENTE"))
            this.jtbl_turnos.setValueAt(adicion, this.jtbl_turnos.getSelectedRow(), 8);
        }
        if ((!folioNuevo.equals("PENDIENTE")) && (this.pendientes))
        {
          int nr = this.jtbl_turnos.getSelectedRow();

          this.busquedas.variosFacadeRemote.actualizarBoletosViaxer(folioNuevo, adic, getIdusuario());
          imprimir_recibo_ya_no_es_pendientes();
          this.defaultmodelo.removeRow(nr);
          this.jtbl_turnos.repaint();
          if (this.jtbl_turnos.getRowCount() == 0)
          {
            this.jtbl_turnos.setEnabled(false);
            this.jtbl_turnos.setFocusable(false);
            this.jlbl_barraEstado.requestFocus();
          }
        }
      }

    }

    if (evt.getKeyCode() == 121)
    {
      if (this.pendientes)
        this.jtbl_turnos.requestFocus();
      jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Confirmacion de Informacion Correcta", "¿La informacion es Correcta?");
      psn.setVisible(true);
      if (this.respuestaSN)
      {
        guardarBoletos();
        System.out.println("El archivo fue eliminado: " + this.archivoActivo.getName());

        Copy("c:\\boleteraTMS\\Usedata\\" + this.archivoActivo.getName(), this.archivoActivo.getName());
        this.archivoActivo.delete();
        this.archivoActivo = null;
        imprimir_recibo();
        imprimir_recibo_pendientes();
        if (this.imprimirTiket)
          new jdlg_informacion("¡La informacion se guardo satisfactoriamente!", "", " Informacion guardada en la Base de Datos").setVisible(true);
        this.leer_datafare = false;
        this.guardar_datafare = false;
        this.jtxt_clave.setText("");
        this.jtxt_nombre.setText("");
        this.jlbl_totalRecaudar.setText("0.0");
        this.defaultmodelo.setDataVector((Object[][])null, this.encabezadoPartidas);
        resizeColumnasPartidas();
        this.jlbl_barraEstado.requestFocus();
        this.jProgressBar1.setValue(0);
        this.jProgressBar1.setStringPainted(true);
        this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
        this.jtbl_turnos.setEnabled(false);
        this.jtbl_turnos.setFocusable(false);
        recorreArchivos();
      }
      else
      {
        this.jtbl_turnos.setEnabled(true);
        this.jtbl_turnos.setFocusable(true);
        this.jtbl_turnos.setRowSelectionInterval(0, 0);
        this.jtbl_turnos.setColumnSelectionInterval(6, 6);
        this.jtbl_turnos.requestFocus();
      }
    }

    if (evt.getKeyCode() == 122)
    {
      this.pendientes = false;
      this.leer_datafare = false;
      this.guardar_datafare = false;
      this.jtxt_clave.setText("");
      this.jtxt_nombre.setText("");
      this.jlbl_totalRecaudar.setText("0.0");
      this.defaultmodelo.setDataVector((Object[][])null, this.encabezadoPartidas);
      resizeColumnasPartidas();
      this.jlbl_barraEstado.requestFocus();
      this.jProgressBar1.setValue(0);
      this.jProgressBar1.setStringPainted(true);
      this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
      this.jtbl_turnos.setEnabled(false);
      this.jtbl_turnos.setFocusable(false);
    }
  }

  public void imprimir_recibo()
  {
    System.out.println("imprimirTiket: " + this.imprimirTiket);
    if (!this.imprimirTiket)
    {
      System.out.println("Dice que los datos ya fueron registrados previamente");
      jdlg_error dlg = new jdlg_error("¡Los datos ya fueron registrados previamente!", "", "Datos Duplicados");
      dlg.setVisible(true);
    }

    int numAtom = 0;
    for (int i = 0; i < this.jtbl_turnos.getRowCount(); i++)
    {
      if (!this.jtbl_turnos.getValueAt(i, 6).toString().equals("PENDIENTE"))
        numAtom++;
    }
    if (numAtom > 0)
    {
      imprimir_recibo_Atomaticos imp = new imprimir_recibo_Atomaticos();

      if (!this.impresoraTicket.equals(""))
      {
        if ((this.puerto.equals("LPT1")) || (this.puerto.equals("LPT2")) || (this.puerto.equals("LPT3")) || (this.puerto.equals("LPT4")) || (this.puerto.equals("COM1")) || (this.puerto.equals("COM2")) || (this.puerto.equals("ARCHIVO")))
        {
          if (!imprimir_recibo_Atomaticos_LPT(this.puerto))
            new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
        }
        if ((this.puerto.equals("RED")) || (this.puerto.equals("USB")))
        {
          if (!imprimir_recibo_Atomaticos_LPT(this.impresoraTicket))
            new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
        }
      }
      else
      {
        double monto = 0.0D;
        for (int i = 0; i < this.jtbl_turnos.getRowCount(); i++)
          monto += Double.valueOf(this.jtbl_turnos.getValueAt(i, 5).toString()).doubleValue();
        System.out.println("Suma desde no hay Impresora : " + monto);
        sumaAcumulado(monto);
        new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no sera impreso", " El monto a recaudar es: " + monto, "Impresora no encontrada").setVisible(true);

        verificarAumulado();
        return;
      }
      Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante");
      p.setVisible(true);
      if (this.reimpresion) {
        imprimir_recibo();
      }
      else {
        double monto = 0.0D;
        for (int i = 0; i < this.jtbl_turnos.getRowCount(); i++)
          monto += Double.valueOf(this.jtbl_turnos.getValueAt(i, 5).toString()).doubleValue();
        System.out.println("Suma desde Impresion : " + monto);
        sumaAcumulado(monto);

        verificarAumulado();
      }
    }
    else
    {
      double monto = 0.0D;
      for (int i = 0; i < this.jtbl_turnos.getRowCount(); i++)
        monto += Double.valueOf(this.jtbl_turnos.getValueAt(i, 5).toString()).doubleValue();
      System.out.println("Suma desde no hay Impresora : " + monto);
      sumaAcumulado(monto);
    }
    actualizaAcumulado();
  }

  public void imprimir_recibo_pendientes()
  {
    if (!this.imprimirTiket)
    {
      new jdlg_error("¡Los datos ya fueron registrados previamente!", "", "Datos Duplicados").setVisible(true);
      return;
    }
    for (int k = 0; k <= this.jtbl_turnos.getRowCount() - 1; k++) {
      if (!this.jtbl_turnos.getValueAt(k, 6).toString().equals("PENDIENTE"))
        continue;
      if (!this.impresoraTicket.equals(""))
      {
        if ((this.puerto.equals("LPT1")) || (this.puerto.equals("LPT2")) || (this.puerto.equals("LPT3")) || (this.puerto.equals("LPT4")) || (this.puerto.equals("COM1")) || (this.puerto.equals("COM2")) || (this.puerto.equals("ARCHIVO")))
        {
          if (!imprimir_recibo_Pendientes_LPT(k, this.puerto))
            new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
        }
        if ((this.puerto.equals("RED")) || (this.puerto.equals("USB")))
        {
          if (!imprimir_recibo_Pendientes_LPT(k, this.impresoraTicket))
            new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
        }
      }
      else
      {
        new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no sera impreso", " ", "Impresora no encontrada").setVisible(true);
        return;
      }

      Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante");
      p.setVisible(true);
      if (this.reimpresion)
        imprimir_recibo_pendientes();
    }
  }

  public void imprimir_recibo_ya_no_es_pendientes()
  {
    if (!this.impresoraTicket.equals(""))
    {
      if ((this.puerto.equals("LPT1")) || (this.puerto.equals("LPT2")) || (this.puerto.equals("LPT3")) || (this.puerto.equals("LPT4")) || (this.puerto.equals("COM1")) || (this.puerto.equals("COM2")) || (this.puerto.equals("ARCHIVO")))
      {
        if (!imprimir_recibo_Pendientes_LPT(this.jtbl_turnos.getSelectedRow(), this.puerto))
          new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
      }
      if ((this.puerto.equals("RED")) || (this.puerto.equals("USB")))
      {
        if (!imprimir_recibo_Pendientes_LPT(this.jtbl_turnos.getSelectedRow(), this.impresoraTicket))
          new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresion").setVisible(true);
      }
    }
    else
    {
      new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no sera impreso", " ", "Impresora no encontrada").setVisible(true);
      return;
    }

    Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante");
    p.setVisible(true);
    if (this.reimpresion) {
      imprimir_recibo_ya_no_es_pendientes();
    }
    else {
      double monto = 0.0D;
      for (int i = 0; i < this.jtbl_turnos.getRowCount(); i++)
        monto += Double.valueOf(this.jtbl_turnos.getValueAt(i, 5).toString()).doubleValue();
    }
  }

  private void jlbl_barraEstadoKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 115)  //VK_F4
    {
      /*int nar = 0;
      File b = new File("c:\\boleteraTMS\\Usedata\\");
      if (b.isDirectory())
      {
        System.out.println("Si es directorio...");
        File[] listaArchivos = b.listFiles();
        System.out.println("y tiene " + listaArchivos.length + " Archivos");
        nar = listaArchivos.length;
      }

      if ((this.leer_datafare) && (!this.guardar_datafare) && (nar > 0))
      {
        new jdlg_error("Â¡Debes guardar los datos con F7! ", "", "Â¡No se han guardado los datos! ").setVisible(true);
        return;
      }*/

      jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Confirmacion de salida", "¿Seguro que desea salir de Recaudacion de VIAXER?");
      psn.setVisible(true);
      if (this.respuestaSN)
      {
        /*if ((this.leer_datafare) && (this.guardar_datafare))
        {
          this.leer_datafare = false;
          this.guardar_datafare = false;

          if (this.jtbl_turnos.getRowCount() > 0)
          {
            guardarBoletos();
            System.out.println("El archivo fue eliminado: " + this.archivoActivo.getName());

            Copy("c:\\boleteraTMS\\Usedata\\" + this.archivoActivo.getName(), this.archivoActivo.getName());
            this.archivoActivo.delete();
            this.archivoActivo = null;
            imprimir_recibo();
            imprimir_recibo_pendientes();
          }
        }
         * 
         */

        TmsActividadesSesionTbl actividasInicioSesion = this.busquedas.actividadesSesionTblFacadeRemote.buscarActidadPorClave("FINSES");
        System.out.println("la sesion actividad es: " + actividasInicioSesion.getNombreActividad());
        PcInfo pc = new PcInfo();
        Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
        this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
        TmsSesionActividadesTbl inicioSesion = new TmsSesionActividadesTbl();
        inicioSesion.setCorteId(getCorteActual());
        inicioSesion.setCajaId(this.caja);
        inicioSesion.setActividadSesionId(actividasInicioSesion);
        inicioSesion.setValorActividad(this.nombre_recaudador + "-" + this.caja.getNombreEquipo() + "-Datafare");
        inicioSesion.setFechaHoraActividad(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setCreadoPor(this.idusuario);
        inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setUltimaActualizacionPor(this.idusuario);
        inicioSesion.setUltimaFechaActualizacion(new Date(this.fecha_servidor.getTime()));
        this.busquedas.sesionActividadesTblFacadeRemote.create(inicioSesion, this.idTerminal);
        System.out.println("Se registro un fin de sesion Datafare en la BD");
        dispose();
      }

    }

    if (evt.getKeyCode() == 117) //VK_F6
    {
      actualizaAcumulado();
      verificarAumulado();
      if (!this.pasoRecol)
        return;
      if(this.buscaOperador)
        buscaGuardaDatosViaxer();
      /*
      this.narchivos = 0;
      try
      {
        File b = new File("c:\\boleteraTMS\\Usedata\\");
        if (b.isDirectory())
        {
          System.out.println("Si es directorio...");
          File[] listaArchivos = b.listFiles();
          System.out.println("y tiene " + listaArchivos.length + " Archivos");
          if (listaArchivos.length > 0)
          {
            this.fileName = listaArchivos[0].getName();
            jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Datos pendientes por guardar", "Hay datos pendientes por guardar en la Base de Datos. Â¿Desea guardarlos en este momento?");
            psn.setVisible(true);
            if (this.respuestaSN)
            {
              guardarDatosBD();
              return;
            }

            return;
          }
        }
        Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL C://boleteraTMS//Boletera.bat");
        this.leer_datafare = true;
      }
      catch (Throwable exc4)
      {
        new jdlg_error("Â¡Error al leer los boletos descargados! ", "Favor de contactar al administrador del sistema", "Error lectura Datafare").setVisible(true);
        System.out.println("No se puede iniciar la boletera.\n");
        exc4.printStackTrace();
      }
      
       * 
       */      
    }

    /*if (evt.getKeyCode() == 118) //VK_F7
    {
      if (this.leer_datafare)
      {
        guardarDatosBD();
      }

    }
     * 
     */

    if (evt.getKeyCode() == 123)//VK_F12
    {
      Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
      String estado = vestado.get(0).toString();
      if (estado.equals("CERRADA")) {
        new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrara automaticamente", "La sesion fue cerrada").setVisible(true);
        System.exit(0);
      }
      recolecciones();
    }

    if (evt.getKeyCode() == 122) //VK_F11
    {
      System.out.println("Se presiono F11...");
      if (this.jtbl_turnos.getRowCount() > 0)
      {
        new jdlg_error("¡La tabla de turnos debe estar vacia!", "", "existen datos por guardar").setVisible(true);
        return;
      }
      Vector vTurnosPendientes = (Vector)this.busquedas.variosFacadeRemote.buscaTurnosPendientesViaxer();
      System.out.println("Turnos Pendientes: " + vTurnosPendientes);
      if (vTurnosPendientes.size() > 0)
      {
        this.pendientes = true;
        this.defaultmodelo.setDataVector((Object[][])null, this.encabezadoPartidas);
        for (int i = 0; i < vTurnosPendientes.size(); i++)
        {
          Vector turnoPendiente = (Vector)vTurnosPendientes.get(i);
          System.out.println("Procesa: " + turnoPendiente);
          try
          {
            String turnop = turnoPendiente.get(0).toString();
            String numbol = turnoPendiente.get(1).toString();
            String impbol = turnoPendiente.get(2).toString();

            StringTokenizer lineaTurno = new StringTokenizer(turnop, "-");
            String toperador = lineaTurno.nextToken();
            String tturno = lineaTurno.nextToken();
            String tmonto = lineaTurno.nextToken();
            tmonto = impbol;
            String tfecha = lineaTurno.nextToken();
            String thoraInicial = lineaTurno.nextToken();
            String thoraFinal = lineaTurno.nextToken();
            //22-OCT-2015 ABa
            thoraFinal = this.busquedas.variosFacadeRemote.getHoraFinal(turnop);
            System.out.println("Adicional2- 3:" + thoraFinal);
            String[] nr = new String[9];
            nr[0] = ("00" + (i + 1));
            nr[1] = tturno;
            nr[2] = thoraInicial;
            nr[3] = thoraFinal;
            nr[4] = numbol;
            nr[5] = ("" + Double.valueOf(tmonto));
            nr[6] = "PENDIENTE";
            nr[7] = "Manual";
            nr[8] = turnop;
            this.defaultmodelo.addRow(nr);
          } catch (NoSuchElementException ex) {
            System.out.println("El turno pendiente " + turnoPendiente + " para relacionar esta mal guardado");
          }
        }
        this.jtbl_turnos.setEnabled(true);
        this.jtbl_turnos.setFocusable(true);
        this.jtbl_turnos.setRowSelectionInterval(0, 0);
        this.jtbl_turnos.setColumnSelectionInterval(6, 6);
        this.jtbl_turnos.requestFocus();
      }
      else {
        new jdlg_advertencia("¡No se encontraron turnos pendientes por relacionar!", "", "No hay Turno pendientes").setVisible(true);
      }
    }
    if ((evt.getKeyCode() == 49) && (evt.isControlDown())) {
      setEventoTeclado(evt);
      try { setIcon(true); } catch (PropertyVetoException ex) {
      }return;
    }

    if ((evt.getKeyCode() == 50) && (evt.isControlDown())) {
      try { setIcon(true); } catch (PropertyVetoException ex) {
      }return;
    }
  }

  private void formComponentShown(ComponentEvent evt)
  {
    //this.jlbl_barraEstado.requestFocusInWindow();
    jtxt_clave.requestFocusInWindow();
  }

  public void setIdusuario(long id)
  {
    this.idusuario = id;
  }

  public long getIdusuario() {
    return this.idusuario;
  }

  public void setCorteid(long corid) {
    this.corteId = corid;
  }

  public long getCorteid() {
    return this.corteId;
  }

  public long getIdmenu() {
    return this.idmenu;
  }

  public void setIdmenu(long idmenu) {
    this.idmenu = idmenu;
  }

  public long getSesionId() {
    return this.sesionId;
  }

  public boolean getInicioGral() {
    return this.inicioGral;
  }

  public void setInicioGral(boolean inicioGral) {
    this.inicioGral = inicioGral;
  }

  public void setSesionId(String sesionId) {
    this.sesionId = Long.valueOf(sesionId).longValue();
  }

  private void setCorteActual(TmsCortesTbl pcorten) {
    this.corteActual = pcorten;
  }

  private TmsCortesTbl getCorteActual() {
    return this.corteActual;
  }

  public void buscaGuardaDatosViaxer()
  {
      String asignacion = "Automatica";
      Vector vTurnosPendientes = (Vector)this.busquedas.variosFacadeRemote.buscaTurnosPendientesOperadorViaxer(this.operador);
      System.out.println("Datos Pendientes: " + vTurnosPendientes);
      float totalRecaudar = 0.0F;
      if (vTurnosPendientes.size() > 0)
      {
        this.datosPendientes = true;
        this.defaultmodelo.setDataVector((Object[][])null, this.encabezadoPartidas);
        for (int i = 0; i < vTurnosPendientes.size(); i++)
        {
          Vector turnoPendiente = (Vector)vTurnosPendientes.get(i);
          System.out.println("Procesa: " + turnoPendiente);
          try
          {
            String turnop = turnoPendiente.get(0).toString();
            String numbol = turnoPendiente.get(1).toString();
            String impbol = turnoPendiente.get(2).toString();
            String folioTarjeta = turnoPendiente.get(3).toString();
            this.origent = turnoPendiente.get(4).toString();
            this.origenSolo = turnoPendiente.get(4).toString();
            this.destinot = turnoPendiente.get(5).toString();
            this.servicio = turnoPendiente.get(6).toString();
            this.monto_Total = impbol;

              System.out.println("Busca el servicio por id: " + this.servicio);
              Vector losn = (Vector)this.busquedas.variosFacadeRemote.queryBuscaDatosServicioPorId(this.servicio);

                Vector solo = (Vector)losn.get(0);
                this.nombreservicio = solo.get(0).toString();
                this.letraservicio = solo.get(1).toString();

                StringTokenizer lineaTurno = new StringTokenizer(turnop, "-");
            String toperador = lineaTurno.nextToken();
            String tturno = lineaTurno.nextToken();
            String tmonto = lineaTurno.nextToken();
            tmonto = impbol;
            String tfecha = lineaTurno.nextToken();
            String thoraInicial = lineaTurno.nextToken();
            //String thoraFinal = lineaTurno.nextToken();
            //22-OCT-2015 ABa
            System.out.println("Turnop " + turnop);
            String thoraFinal = busquedas.variosFacadeRemote.getHoraFinal(turnop);
            System.out.println("Adicional2- 1: " + thoraFinal);
            String[] nr = new String[9];
            nr[0] = ("00" + (i + 1));
            nr[1] = tturno;
            nr[2] = thoraInicial;
            nr[3] = thoraFinal;
            nr[4] = numbol;
            nr[5] = ("" + Double.valueOf(tmonto));
            nr[6] = ((folioTarjeta.equals("PENDIENTE"))?"PENDIENTE":folioTarjeta);
            nr[7] = ((folioTarjeta.equals("PENDIENTE"))?"Manual":"Automatica");
            nr[8] = turnop;
            this.defaultmodelo.addRow(nr);





            String otmp = this.origent;
            if (this.origent.length() < 4)
            {
              otmp = this.origent;
              for (int jk = this.origent.length(); jk < 4; jk++)
                otmp = otmp + "_";
            }
            this.origent = otmp;
            if (this.origent.length() > 4)
            {
              otmp = "";
              char[] list = this.origent.toCharArray();
              for (int jk = 0; jk < 4; jk++)
                otmp = otmp + list[jk];
            }
            this.origent = otmp;

            //long folion = Long.valueOf(this.tarjeta).longValue();
            //System.out.println("Folio de Tarjeta:" + folion + "  Ruta:" + this.ruta + "  Direccion:" + this.direccion + " Origen: " + this.origent + " OrigenSolo: " + this.origenSolo + " Destino: " + this.destinot + " Folo: " + folion + " Operador: " + this.operador);
            //Vector vTarjetas = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTarjetaViaje(this.origent + this.letraservicio, "" + folion, this.operador, this.origenSolo, this.destinot, this.fecha);
            //System.out.println("Tarjeta= " + vTarjetas);
            //if (vTarjetas.size() == 0)
            if(folioTarjeta.equals("PENDIENTE"))
            {
              Vector vTarjetasOperador = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTarjetasViajePorOperador(this.origent + this.letraservicio, this.operador);
              System.out.println("Tarjetas por operador= " + vTarjetasOperador);
              if (vTarjetasOperador.size() == 0)
              {
                Vector vTodasTarjetas = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTodasTarjetaViaje(this.origent + this.letraservicio);
                System.out.println("Todas las tarjeta(origen) s= " + vTodasTarjetas);
                if (vTodasTarjetas.size() == 0)
                {
                  Vector vTarjetasServicios = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTarjetasViajePorServicio(this.nombreservicio);
                  if (vTarjetasServicios.size() == 0) {
                    new jdlg_advertencia("¡No existe ninguna tarjeta con que relacionar la venta! ", "La venta se relacionara con una tarjeta DESCONOCIDA", "No hay tarjetas para relacionar").setVisible(true);
                    this.folio = "DESCONOCIDA";
                  }
                  else
                  {
                    System.out.println("Busqueda de Tarjetas por Servicio");
                    new jdlg_seleccionarTarjetaAdentro(vTarjetasServicios);
                    this.folio = this.resultadoCorridas;
                  }

                }
                else
                {
                  System.out.println("Busqueda de Tarjetas por Origen");
                  new jdlg_seleccionarTarjetaAdentro(vTodasTarjetas);
                  this.folio = this.resultadoCorridas;
                }

              }
              else
              {
                System.out.println("Busqueda de Tarjetas por Operador");
                new jdlg_seleccionarTarjetaAdentro(vTarjetasOperador);
                this.folio = this.resultadoCorridas;
              }
              asignacion = "Manual";
                this.jtbl_turnos.setValueAt(this.folio, i , 6);
            }
            /*else
            {
              Vector tarj = (Vector)vTarjetas.get(0);
              this.folio = tarj.get(0).toString();
            }
             * 
             */


            System.out.println("Folio de Tarjeta:" + this.folio + "  Servicio:" + this.nombreservicio + "  Origen:" + this.origent + " OrigenSolo: " + this.origenSolo + "  Letra Servicio: " + this.letraservicio);
            totalRecaudar += Float.valueOf("" + this.monto_Total).floatValue();
            this.jlbl_totalRecaudar.setText("" + totalRecaudar);

		System.out.println("El tamanio fina es: " + this.aumentaTamano);
        this.jProgressBar1.setValue(this.aumentaTamano - 1);
        this.jProgressBar1.setStringPainted(true);
        this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
          } catch (NoSuchElementException ex) {
            System.out.println("El turno pendiente " + turnoPendiente + " para relacionar esta mal guardado");
          }
        }

        jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Confirmacion de Informacion", "¿Los datos son correctos?");
        psn.setVisible(true);
        if (this.respuestaSN)
        {
          //guardarBoletos();
          guardarBoletosViaxer();
          imprimir_recibo();
          imprimir_recibo_pendientes();
          if (this.imprimirTiket)
            new jdlg_informacion("¡La informacion se guardo satisfactoriamente!", "", " Informacion guardada en la Base de Datos").setVisible(true);
          this.leer_datafare = false;
          this.guardar_datafare = false;

          this.jtxt_clave.setText("");
          this.jtxt_nombre.setText("");
          this.jlbl_totalRecaudar.setText("0.0");
          this.defaultmodelo.setDataVector((Object[][])null, this.encabezadoPartidas);
          resizeColumnasPartidas();
          this.jlbl_barraEstado.requestFocus();
          this.jProgressBar1.setValue(0);
          this.jProgressBar1.setStringPainted(true);
          this.jProgressBar1.paint(this.jProgressBar1.getGraphics());

        }
        else
        {
          if (this.jtbl_turnos.getRowCount() <= 0)
           return;
          this.jtbl_turnos.setEnabled(true);
          this.jtbl_turnos.setFocusable(true);
          this.jtbl_turnos.setRowSelectionInterval(0, 0);
          this.jtbl_turnos.setColumnSelectionInterval(6, 6);
          this.jtbl_turnos.requestFocus();
          //break;
        }

        /*this.jtbl_turnos.setEnabled(true);
        this.jtbl_turnos.setFocusable(true);
        this.jtbl_turnos.setRowSelectionInterval(0, 0);
        this.jtbl_turnos.setColumnSelectionInterval(6, 6);
        this.jtbl_turnos.requestFocus();
         * 
         */
        jtxt_clave.requestFocusInWindow();
        
      }
      else {
        new jdlg_advertencia("¡No se encontraron datos pendientes para este operador!", "", "No hay Turnos pendientes de cobrar").setVisible(true);
      }

  }
  
  public void guardarDatosBD()
  {
    this.ArregloBoletos = new String[100000];
    recorreArchivos();
  }
  private void recorreArchivos() {
    this.linea = "";
    InputStream in = null;
    OutputStream out = null;
    long tamanoArchivo = 0L;
    try
    {
      File b = new File("c:\\boleteraTMS\\Usedata\\");
      File[] listaArchivos = b.listFiles();
      System.out.println("y tiene " + listaArchivos.length + " Archivos");

      for (int na = 0; na < listaArchivos.length; na++)
      {
        this.fileName = listaArchivos[na].getName();
        File archivo = new File("C://boleteraTMS//Usedata//" + this.fileName);
        FileInputStream miFicheroSt = new FileInputStream(archivo);

        System.out.println("Tamanio del Archivo: " + archivo.length());
        tamanoArchivo = archivo.length();
        this.jProgressBar1.setValue(0);
        this.jProgressBar1.setMinimum(0);
        this.jProgressBar1.setMaximum(Integer.parseInt("" + archivo.length()));
        int numRead = 0;
        int i = 0;
        String hex1 = "0";
        byte[] soloUno = new byte[1];
        byte[] priLectura = new byte[5];
        byte[] shift = new byte[23];
        byte[] trip = new byte[13];

        byte[] mascara = new byte[1];
        byte[] origen = new byte[2];
        byte[] destino = new byte[2];
        byte[] hora = new byte[2];
        byte[] clase = new byte[1];
        byte[] categoria = new byte[1];
        byte[] cantidad = new byte[3];
        byte[] sin_uso = new byte[1];
        byte[] card_num = new byte[4];
        this.aumentaTamano = 0;
        numRead = miFicheroSt.read(priLectura);
        this.aumentaTamano += 5;
        this.jProgressBar1.setValue(this.aumentaTamano);
        this.jProgressBar1.setStringPainted(true);
        this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
        int numPartida = 0;
        this.nboletos = 0;
        int numLinea = 0;
        float totalRecaudar = 0.0F;
        String nuevoreg = "";
        String verificaRuta = "";
        boolean esRuta = false;
        while (numRead != -1) {
          String asignacion = "Automatica";

          this.bolCard_num = "";
          numPartida++;
          numRead = miFicheroSt.read(shift);
          numRead = miFicheroSt.read(soloUno);
          this.aumentaTamano += 24;
          this.jProgressBar1.setValue(this.aumentaTamano);
          this.jProgressBar1.setStringPainted(true);
          this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
          this.operador = (HexB(new StringBuilder().append("").append(shift[3]).toString()) + HexB(new StringBuilder().append("").append(shift[4]).toString()) + HexB(new StringBuilder().append("").append(shift[5]).toString()) + HexB(new StringBuilder().append("").append(shift[6]).toString()));
          System.out.println("Operador: " + this.operador);
          char[] opaux = this.operador.toCharArray();
          String op2 = "";
          boolean encontrouno = false;
          for (int u = 0; u < opaux.length; u++)
          {
            String uno = "" + opaux[u];
            if (uno.equals("1") && u > 0)
              encontrouno = true;
            if (encontrouno) {
              op2 = op2 + "" + uno;
            }
          }
          this.operador = op2;
          System.out.println("Operadior sin 1 o 2: " + this.operador);
          this.turno = (HexB(new StringBuilder().append("").append(shift[7]).toString()) + HexB(new StringBuilder().append("").append(shift[8]).toString()));
          System.out.println("Turno: " + this.turno);

          this.fecha = (HexB(new StringBuilder().append("").append(shift[12]).toString()) + "/" + HexB(new StringBuilder().append("").append(shift[11]).toString()) + "/" + HexB(new StringBuilder().append("").append(shift[10]).toString()));
          this.fechaHoraBoleto = (HexB(new StringBuilder().append("").append(shift[10]).toString()) + "-" + HexB(new StringBuilder().append("").append(shift[11]).toString()) + "-" + HexB(new StringBuilder().append("").append(shift[12]).toString()));
          System.out.println("Fecha: " + this.fecha);

          this.hora_Inicial = (HexB(new StringBuilder().append("").append(shift[13]).toString()) + ":" + HexB(new StringBuilder().append("").append(shift[14]).toString()));
          System.out.println("Hora Inicial: " + this.hora_Inicial);

          this.hora_Final = (HexB(new StringBuilder().append("").append(shift[15]).toString()) + ":" + HexB(new StringBuilder().append("").append(shift[16]).toString()));
          System.out.println("Hora Final: " + this.hora_Final);
          this.monto_Total = (HexB(new StringBuilder().append("").append(shift[17]).toString()) + HexB(new StringBuilder().append("").append(shift[18]).toString()) + HexB(new StringBuilder().append("").append(shift[19]).toString()) + HexB(new StringBuilder().append(".").append(shift[20]).toString()));
          System.out.println("Monto Total: " + this.monto_Total);
          float monto = Float.valueOf(this.monto_Total).floatValue();
          System.out.println("Monto Float: " + monto);
          System.out.println("nuevoreg: " + nuevoreg);
          String ts1 = "" + shift[21];
          String ts2 = "" + shift[22];
          long tamShift = Long.valueOf(ts1.trim()).longValue() + Long.valueOf(ts2.trim()).longValue();

          System.out.println("Tamanio del Shift: " + shift[21] + " " + shift[22]);
          System.out.println("Tamanio2 del Shift: " + HexB(new StringBuilder().append("").append(shift[21]).toString()) + " " + HexB(new StringBuilder().append("").append(shift[22]).toString()));

          boolean sp = false;
          if ((nuevoreg.equals("")) && (monto <= 0.0F) && (tamShift > 0L))
            sp = true;
          if ((monto > 0.0F) || (nuevoreg.equals("a2")))
            sp = true;
          if (tamShift == 0L) {
            sp = false;
          }
          if (sp)
          {
            boolean trip0 = true;
            while (trip0)
            {
              long t0 = 40L;
              numRead = miFicheroSt.read(trip);
              this.ruta = ("" + (char)trip[0] + "" + (char)trip[1] + "" + (char)trip[2] + "" + (char)trip[3]);
              System.out.println("Ruta: " + this.ruta);

              this.tarjeta = (HexB(new StringBuilder().append("").append(trip[4]).toString()) + HexB(new StringBuilder().append("").append(trip[5]).toString()));
              System.out.println("tarjeta: " + this.tarjeta);

              this.direccion = HexB("" + trip[6]);
              System.out.println("Direccion: " + this.direccion);
              numRead = miFicheroSt.read(soloUno);
              this.aumentaTamano += 14;
              this.jProgressBar1.setValue(this.aumentaTamano);
              this.jProgressBar1.setStringPainted(true);
              this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
              System.out.println("Tamanio del Trip: " + HexB(new StringBuilder().append("").append(trip[11]).toString()) + " " + HexB(new StringBuilder().append("").append(trip[12]).toString()));
              String tp1 = "" + trip[11];
              String tp2 = "" + trip[12];

              t0 = Long.valueOf(tp1.trim()).longValue() + Long.valueOf(tp2.trim()).longValue();
              System.out.println("El tamanio2 del trip es: " + t0);
              if ((t0 > 0L) || (t0 < 0L) || (tamanoArchivo < this.aumentaTamano - 3) || (monto == 0.0F))
              {
                trip0 = false;
              }

            }

            if (nuevoreg.equals("b1"))
            {
              numRead = miFicheroSt.read(trip);
              this.ruta = ("" + (char)trip[0] + "" + (char)trip[1] + "" + (char)trip[2] + "" + (char)trip[3]);
              System.out.println("Ruta: " + this.ruta);

              this.tarjeta = (HexB(new StringBuilder().append("").append(trip[4]).toString()) + HexB(new StringBuilder().append("").append(trip[5]).toString()));
              System.out.println("tarjeta: " + this.tarjeta);

              this.direccion = HexB("" + trip[6]);
              System.out.println("Direccion: " + this.direccion);
              numRead = miFicheroSt.read(soloUno);
              this.aumentaTamano += 14;
              System.out.println("El tamanio medio2 es: " + this.aumentaTamano);
              this.jProgressBar1.setValue(this.aumentaTamano);
              this.jProgressBar1.setStringPainted(true);
              this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
            }
            nuevoreg = "" + HexB(new StringBuilder().append("").append(soloUno[0]).toString());

            long oc = Long.valueOf(this.operador).longValue();
            String tmp = "" + oc;
            String tmp2 = tmp.substring(1);
            this.operador = tmp2;
            this.jtxt_clave.setText("" + this.operador);
            Vector voper = (Vector)this.busquedas.variosFacadeRemote.queryBuscaOperadorPorClave(this.operador);
            if (voper.size() == 0) {
              this.jtxt_nombre.setText("Operador Desconocido");
            }
            else {
              Vector voper1 = (Vector)voper.get(0);
              if ((voper1.get(1) != null) && (voper1.get(1).toString().indexOf("OPERADOR") == -1))
                this.jtxt_nombre.setText(voper1.get(0) + " " + voper1.get(1) + " " + voper1.get(2));
              else
                this.jtxt_nombre.setText(voper1.get(0) + " " + voper1.get(2));
            }
            String[] nr = new String[9];
            nr[0] = ("00" + numPartida);
            nr[1] = this.turno;
            nr[2] = this.hora_Inicial;
            nr[3] = this.hora_Final;
            nr[4] = "";
            nr[5] = ("" + monto);
            nr[6] = "";
            nr[7] = "";
            nr[8] = "";
            this.defaultmodelo.addRow(nr);

            if (this.ruta.equals("SCOM"))
              this.servicio = "104";
            if (this.ruta.equals("SOR6"))
              this.servicio = "106";
            if (this.ruta.equals("SORD"))
              this.servicio = "101";
            if (this.ruta.equals("PIST"))
              this.servicio = "615";
            if ((this.direccion.equals("01")) || (this.direccion.equals("1")))
            {
              System.out.println("Busca el destino por el numero(01): " + this.servicio);
              Vector losn = (Vector)this.busquedas.variosFacadeRemote.queryBuscaOrigenServicioPorNumero(this.servicio);
              System.out.println("Nos regresa el vector: " + losn);
              if (losn.size() == 0) {
                this.origent = "Desconocido";
              }
              else {
                Vector solo = (Vector)losn.get(0);
                this.nombreservicio = solo.get(0).toString();
                this.origent = solo.get(1).toString();
                this.origenSolo = solo.get(1).toString();
                this.letraservicio = solo.get(2).toString();
                this.destinot = solo.get(3).toString();
              }
            }
            if ((this.direccion.equals("02")) || (this.direccion.equals("2")))
            {
              System.out.println("Busca el destino por el numero(02): " + this.servicio);
              Vector losn = (Vector)this.busquedas.variosFacadeRemote.queryBuscaDestinoServicioPorNumero(this.servicio);
              System.out.println("Nos regresa el vector: " + losn);
              if (losn.size() == 0) {
                this.origent = "Desconocido";
              }
              else {
                Vector solo = (Vector)losn.get(0);
                this.nombreservicio = solo.get(0).toString();
                this.origent = solo.get(1).toString();
                this.origenSolo = solo.get(1).toString();
                this.letraservicio = solo.get(2).toString();
                this.destinot = solo.get(3).toString();
              }
            }
            String otmp = this.origent;
            if (this.origent.length() < 4)
            {
              otmp = this.origent;
              for (int jk = this.origent.length(); jk < 4; jk++)
                otmp = otmp + "_";
            }
            this.origent = otmp;
            if (this.origent.length() > 4)
            {
              otmp = "";
              char[] list = this.origent.toCharArray();
              for (int jk = 0; jk < 4; jk++)
                otmp = otmp + list[jk];
            }
            this.origent = otmp;

            long folion = Long.valueOf(this.tarjeta).longValue();
            System.out.println("Folio de Tarjeta:" + folion + "  Ruta:" + this.ruta + "  Direccion:" + this.direccion + " Origen: " + this.origent + " OrigenSolo: " + this.origenSolo + " Destino: " + this.destinot + " Folo: " + folion + " Operador: " + this.operador);
            Vector vTarjetas = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTarjetaViaje(this.origent + this.letraservicio, "" + folion, this.operador, this.origenSolo, this.destinot, this.fecha);
            System.out.println("Tarjeta= " + vTarjetas);
            if (vTarjetas.size() == 0)
            {
              Vector vTarjetasOperador = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTarjetasViajePorOperador(this.origent + this.letraservicio, this.operador);
              System.out.println("Tarjetas por operador= " + vTarjetasOperador);
              if (vTarjetasOperador.size() == 0)
              {
                Vector vTodasTarjetas = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTodasTarjetaViaje(this.origent + this.letraservicio);
                System.out.println("Todas las tarjeta(origen) s= " + vTodasTarjetas);
                if (vTodasTarjetas.size() == 0)
                {
                  Vector vTarjetasServicios = (Vector)this.busquedas.variosFacadeRemote.queryBuscaTarjetasViajePorServicio(this.nombreservicio);
                  if (vTarjetasServicios.size() == 0) {
                    new jdlg_advertencia("¡No existe ninguna tarjeta con que relacionar la venta! ", "La venta se relacionara con una tarjeta DESCONOCIDA", "No hay tarjetas para relacionar").setVisible(true);
                    this.folio = "DESCONOCIDA";
                  }
                  else
                  {
                    System.out.println("Busqueda de Tarjetas por Servicio");
                    new jdlg_seleccionarTarjetaAdentro(vTarjetasServicios);
                    this.folio = this.resultadoCorridas;
                  }

                }
                else
                {
                  System.out.println("Busqueda de Tarjetas por Origen");
                  new jdlg_seleccionarTarjetaAdentro(vTodasTarjetas);
                  this.folio = this.resultadoCorridas;
                }

              }
              else
              {
                System.out.println("Busqueda de Tarjetas por Operador");
                new jdlg_seleccionarTarjetaAdentro(vTarjetasOperador);
                this.folio = this.resultadoCorridas;
              }
              asignacion = "Manual";
            }
            else
            {
              Vector tarj = (Vector)vTarjetas.get(0);
              this.folio = tarj.get(0).toString();
            }
            String adicional = "";
            String adicional2 = "";
            if (this.folio.equals("PENDIENTE"))
              adicional = this.operador + "-" + this.turno + "-" + this.monto_Total + "-" + this.fecha + "-" + this.hora_Inicial + "-" + this.hora_Final;
            adicional2 = this.operador + "-" + this.turno + "-" + this.monto_Total + "-" + this.fecha + "-" + this.hora_Inicial + "-" + this.hora_Final + "-" + this.fileName;
            System.out.println("Adicional : " + adicional);
            System.out.println("Adicional2 : " + adicional2);
            System.out.println("Folio de Tarjeta:" + this.folio + "  Ruta:" + this.ruta + "  Direccion:" + this.direccion);
            System.out.println("Folio de Tarjeta:" + this.folio + "  Servicio:" + this.nombreservicio + "  Origen:" + this.origent + " OrigenSolo: " + this.origenSolo + "  Letra Servicio: " + this.letraservicio);

            int numBol = 0;
            float total = 0.0F;
            boolean sigue = true;
            if (nuevoreg.equals("a2"))
              sigue = false;
            else {
              sigue = true;
            }
            System.out.println("Verifica0 si es nuevo registro: " + nuevoreg);
            if (nuevoreg.equals("b1"))
            {
              boolean esa2 = true;
              while (esa2)
              {
                numRead = miFicheroSt.read(trip);
                this.ruta = ("" + (char)trip[0] + "" + (char)trip[1] + "" + (char)trip[2] + "" + (char)trip[3]);
                System.out.println("Ruta: " + this.ruta);

                this.tarjeta = (HexB(new StringBuilder().append("").append(trip[4]).toString()) + HexB(new StringBuilder().append("").append(trip[5]).toString()));
                System.out.println("tarjeta: " + this.tarjeta);

                this.direccion = HexB("" + trip[6]);
                System.out.println("Direccion: " + this.direccion);
                numRead = miFicheroSt.read(soloUno);
                this.aumentaTamano += 14;
                this.jProgressBar1.setValue(this.aumentaTamano);
                this.jProgressBar1.setStringPainted(true);
                this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                nuevoreg = "" + HexB(new StringBuilder().append("").append(soloUno[0]).toString());
                System.out.println("El tamanio medio3 es: " + this.aumentaTamano);
                System.out.println("Verifica2 si es nuevo registro: " + nuevoreg);
                if ((nuevoreg.equals("e0")) || (tamanoArchivo < this.aumentaTamano - 3) || (nuevoreg.equals("e3")) || (nuevoreg.equals("a2")))
                  esa2 = false;
              }
            }
            sigue = true;
            if ((nuevoreg.equals("a2")) || (monto <= 0.0F))
              sigue = false;
            if (nuevoreg.equals("e3"))
              sigue = true;
            while (sigue) {
              System.out.println("Empieza a leer boleto...");
              numRead = miFicheroSt.read(mascara);
              this.aumentaTamano += 1;
              this.jProgressBar1.setValue(this.aumentaTamano);
              this.jProgressBar1.setStringPainted(true);
              this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
              System.out.println("Mascara: " + mascara[0]);
              Byte N = Byte.valueOf(mascara[0]);
              int[] binario = new int[8];
              binario[0] = (N.byteValue() >> 7);
              binario[1] = ((N.byteValue() & 0x40) >> 6);
              binario[2] = ((N.byteValue() & 0x20) >> 5);
              binario[3] = ((N.byteValue() & 0x10) >> 4);
              binario[4] = ((N.byteValue() & 0x8) >> 3);
              binario[5] = ((N.byteValue() & 0x4) >> 2);
              binario[6] = ((N.byteValue() & 0x2) >> 1);
              binario[7] = (N.byteValue() & 0x1);
              System.out.println("Resultado Mascara: " + binario[0] + " " + binario[1] + " " + binario[2] + " " + binario[3] + " " + binario[4] + " " + binario[5] + " " + binario[6] + " " + binario[7]);
              for (int j = 7; j >= 0; j--)
              {
                if (binario[j] == 0)
                  continue;
                System.out.println("Entra al If en " + j);
                switch (j)
                {
                case 7:
                  numRead = miFicheroSt.read(origen);
                  this.bolOrigen = (HexB(new StringBuilder().append("").append(origen[0]).toString()) + HexB(new StringBuilder().append("").append(origen[1]).toString()));
                  this.aumentaTamano += 2;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());

                  break;
                case 6:
                  numRead = miFicheroSt.read(destino);
                  this.bolDestino = (HexB(new StringBuilder().append("").append(destino[0]).toString()) + HexB(new StringBuilder().append("").append(destino[1]).toString()));
                  this.aumentaTamano += 2;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                  break;
                case 5:
                  numRead = miFicheroSt.read(hora);
                  this.bolHora = (HexB(new StringBuilder().append("").append(hora[0]).toString()) + ":" + HexB(new StringBuilder().append("").append(hora[1]).toString()));
                  this.aumentaTamano += 2;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                  break;
                case 4:
                  numRead = miFicheroSt.read(clase);
                  System.out.println("saca clase de: " + clase[0]);
                  if (HexB("" + clase[0]).equals("01"))
                    this.bolClase = "Adulto";
                  if (HexB("" + clase[0]).equals("02"))
                    this.bolClase = "50%";
                  if (HexB("" + clase[0]).equals("08")) {
                    this.bolClase = "Maestro";
                  }

                  this.aumentaTamano += 1;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                  break;
                case 3:
                  numRead = miFicheroSt.read(categoria);
                  if (HexB("" + categoria[0]).equals("00"))
                    this.bolCategoria = "Efectivo";
                  else
                    this.bolCategoria = HexB("" + categoria[0]);
                  System.out.println("Categoria(Hex): " + categoria[0] + " => " + numRead);
                  System.out.println("Categoria: " + this.bolCategoria);
                  this.aumentaTamano += 1;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                  break;
                case 2:
                  numRead = miFicheroSt.read(cantidad);
                  this.bolCantidad = (HexB(new StringBuilder().append("").append(cantidad[0]).toString()) + HexB(new StringBuilder().append("").append(cantidad[1]).toString()) + "." + HexB(new StringBuilder().append("").append(cantidad[2]).toString()));
                  this.aumentaTamano += 3;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                  break;
                case 1:
                  numRead = miFicheroSt.read(sin_uso);
                  this.bolSin_uso = HexB("" + sin_uso[0]);
                  this.aumentaTamano += 1;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                  break;
                case 0:
                  numRead = miFicheroSt.read(card_num);
                  this.bolCard_num = HexB("" + card_num[0]);
                  this.aumentaTamano += 4;
                  this.jProgressBar1.setValue(this.aumentaTamano);
                  this.jProgressBar1.setStringPainted(true);
                  this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                  break;
                default:
                  System.out.println("No encontro el numero: " + binario[j]);
                }

              }

              numBol += 1;
              if (nuevoreg.equals("e3"))
              {
                this.bolCantidad = "0";
                this.bolClase = "Inspeccion";
              }
              System.out.println("Num Bol : " + numBol);
              System.out.println("Origen: " + this.bolOrigen);
              System.out.println("Destino: " + this.bolDestino);
              System.out.println("Hora: " + this.bolHora);
              System.out.println("Clase: " + this.bolClase);
              System.out.println("Categoria: " + this.bolCategoria);
              System.out.println("cantidad: " + this.bolCantidad);
              System.out.println("Sin_uso: " + this.bolSin_uso);
              System.out.println("Card_Num: " + this.bolCard_num);
              String fecha_bol = this.fechaHoraBoleto + " " + this.bolHora + ":00";
              this.linea = (adicional2 + " " + this.bolHora + " " + this.bolOrigen + " " + this.bolClase + " " + this.bolDestino + " " + this.bolCantidad + " " + this.folio + " " + fecha_bol + " " + asignacion + " " + adicional);
              this.ArregloBoletos[numLinea] = this.linea;
              numLinea += 1;
              total += Float.valueOf(this.bolCantidad).floatValue();
              numRead = miFicheroSt.read(soloUno);
              this.aumentaTamano += 1;
              this.jProgressBar1.setValue(this.aumentaTamano);
              this.jProgressBar1.setStringPainted(true);
              this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
              System.out.println("viejo registro:" + nuevoreg);
              nuevoreg = "" + HexB(new StringBuilder().append("").append(soloUno[0]).toString());
              System.out.println("nuevo registro:" + nuevoreg);
              System.out.println("Monto Actual: " + total);
              System.out.println("Verifica1 si es nuevo registro: " + nuevoreg);
              if (nuevoreg.equals("b1"))
              {
                numRead = miFicheroSt.read(trip);
                this.ruta = ("" + (char)trip[0] + "" + (char)trip[1] + "" + (char)trip[2] + "" + (char)trip[3]);
                System.out.println("Ruta: " + this.ruta);

                this.tarjeta = (HexB(new StringBuilder().append("").append(trip[4]).toString()) + HexB(new StringBuilder().append("").append(trip[5]).toString()));
                System.out.println("tarjeta: " + this.tarjeta);

                this.direccion = HexB("" + trip[6]);
                System.out.println("Direccion: " + this.direccion);
                numRead = miFicheroSt.read(soloUno);
                this.aumentaTamano += 14;
                this.jProgressBar1.setValue(this.aumentaTamano);
                this.jProgressBar1.setStringPainted(true);
                this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
                nuevoreg = "" + HexB(new StringBuilder().append("").append(soloUno[0]).toString());
                System.out.println("El tamanio medio3 es: " + this.aumentaTamano);
                System.out.println("Verifica2 si es nuevo registro: " + nuevoreg);
              }
              if ((!nuevoreg.equals("a2")) && (monto > 0.0F) && (total < monto))
                sigue = true;
              else {
                sigue = false;
              }
              if (nuevoreg.equals("a2")) {
                sigue = false;
              }
              System.out.println("El tamanio actual es: " + this.aumentaTamano + " y el Tamanio del archivo es: " + tamanoArchivo);
              if (((nuevoreg.equals("e3")) && (this.aumentaTamano < tamanoArchivo - 5L)) || ((nuevoreg.equals("e0")) && (this.aumentaTamano < tamanoArchivo - 5L))) {
                sigue = true;
              }
            }
            this.nboletos += numBol;
            nuevoreg = "" + HexB(new StringBuilder().append("").append(soloUno[0]).toString());

            System.out.println("Partida(exp): " + (numPartida - 1));
            this.jtbl_turnos.setValueAt("" + numBol, numPartida - 1, 4);

            this.jtbl_turnos.setValueAt(this.folio, numPartida - 1, 6);
            this.jtbl_turnos.setValueAt(asignacion, numPartida - 1, 7);
            this.jtbl_turnos.setValueAt(adicional, numPartida - 1, 8);
            totalRecaudar += Float.valueOf("" + this.monto_Total).floatValue();
            this.jlbl_totalRecaudar.setText("" + totalRecaudar);
          }
          else {
            numPartida--;
          }
        }
        miFicheroSt.close();
        System.out.println("El tamanio fina es: " + this.aumentaTamano);
        this.jProgressBar1.setValue(this.aumentaTamano - 1);
        this.jProgressBar1.setStringPainted(true);
        this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
        jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Confirmacion de Informacion", "¿Los datos son correctos?");
        psn.setVisible(true);
        if (this.respuestaSN)
        {
          guardarBoletos();
          System.out.println("El archivo fue eliminado: " + archivo.getName());

          Copy("c:\\boleteraTMS\\Usedata\\" + archivo.getName(), archivo.getName());
          archivo.delete();
          archivo = null;
          imprimir_recibo();
          imprimir_recibo_pendientes();
          if (this.imprimirTiket)
            new jdlg_informacion("¡La informacion se guardo satisfactoriamente!", "", " Informacion guardada en la Base de Datos").setVisible(true);
          this.leer_datafare = false;
          this.guardar_datafare = false;

          this.jtxt_clave.setText("");
          this.jtxt_nombre.setText("");
          this.jlbl_totalRecaudar.setText("0.0");
          this.defaultmodelo.setDataVector((Object[][])null, this.encabezadoPartidas);
          resizeColumnasPartidas();
          this.jlbl_barraEstado.requestFocus();
          this.jProgressBar1.setValue(0);
          this.jProgressBar1.setStringPainted(true);
          this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
        }
        else
        {
          if (this.jtbl_turnos.getRowCount() <= 0)
            break;
          this.jtbl_turnos.setEnabled(true);
          this.jtbl_turnos.setFocusable(true);
          this.jtbl_turnos.setRowSelectionInterval(0, 0);
          this.jtbl_turnos.setColumnSelectionInterval(6, 6);
          this.jtbl_turnos.requestFocus();
          this.archivoActivo = archivo;
          break;
        }
      }
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }

    public boolean Copy(String localFileName,String name) {
        OutputStream out = null;
        URLConnection conn = null;
        InputStream  in = null;
        String nameUseData = name;

        try {
            int n=1;
            boolean existe = true;
            while(existe)
            {
                File b = new File("c:\\boleteraTMS\\Respaldos\\"+name);
                if(b.exists())
                {
                    n++;
                    name = nameUseData +""+n;
                }
                else
                    existe = false;
            }
                        
            FileWriter fw = new FileWriter("c:\\boleteraTMS\\Respaldos\\"+name);
            
            URL url = new URL("file:\\c:\\boleteraTMS\\Usedata\\"+nameUseData);
            //file:///C:/dira/dirb/file.xyz

            out = new BufferedOutputStream(new FileOutputStream("c:\\boleteraTMS\\Respaldos\\"+name));//localFileName));
            conn = url.openConnection();
            in = conn.getInputStream();
//            boolean siguiente = true;
//            int num=1;
//            File b = null;
//            while(siguiente)
//            {
//             b = new File("c:\\boleteraTMS\\Respaldos\\"+name);
//             if(b.exists())
//               siguiente = true;
//             else
//             {
//                 num++;
//                 name = name+num;
//                 siguiente = false;
//             }
//                 
//             
//            }
//            in = new BufferedInputStream(new FileInputStream(b));//"c:\\boleteraTMS\\Respaldos\\"+name));
            byte[] buffer = new byte[1024];
            int numRead;
            long numWritten = 0;
            while ((numRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, numRead);
                    numWritten += numRead;
        }
            System.out.println(localFileName + "\t" + numWritten);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            try {
                if (in != null) {
                        in.close();
                }
                if (out != null) {
                        out.close();
                }
            } catch (IOException ioe) {
            }

        }

    }
  

  private String HexB(String valor)
  {
    String resul = "";
    int num = 0;
    try {
      int numero = Integer.valueOf(valor).intValue();
      if (numero >= 0) {
        num = Integer.parseInt(Integer.toHexString(Integer.parseInt(valor)));
      }
      else {
        int numeroDOs = numero + 128 + 128;
        num = -1;
        return Integer.toHexString(Integer.parseInt("" + numeroDOs));
      }
    }
    catch (NumberFormatException ex) {
      return valor;
    }
    if (num < 10)
      resul = "0" + num;
    else
      resul = "" + num;
    return resul;
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        Vector datos = new Vector();
        datos.add("60");
        datos.add("6000");
        datos.add("algun usuario");
        datos.add("354");
        datos.add("309");
        new JIFLecturaViaxer(datos).setVisible(true);
      }
    });
  }

  public void sumaAcumulado(double acumula)
  {
    this.acumulado += acumula;
  }

  public void setAcumulado(double ma) {
    System.out.println("EL monto acumulado es de: " + ma);
    this.acumulado = ma;
  }

  public void setMontoRecoleccion(float monto) {
    System.out.println("EL monto de recoleccion es de: " + monto);
    this.montorecoleccion = monto;
  }

  public void setTopeRecoleccion(float monto) {
    System.out.println("EL tope de recoleccion es de: " + monto);
    this.toperecoleccion = monto;
  }

    public void guardarBoletosViaxer()
  {
    System.out.println("Entra a guardar a la BD  los boletos de Viaxer");
    for(int i= 0; i< this.jtbl_turnos.getRowCount(); i++)
        this.busquedas.variosFacadeRemote.copiaBoletosCobradosViaxer(this.operador,this.jtbl_turnos.getValueAt( i, 8).toString(),""+this.getIdusuario(), ""+getCorteid(),this.jtbl_turnos.getValueAt( i, 6).toString());
    this.imprimirTiket = true;
    }

  public void guardarBoletos()
  {
    System.out.println("Entra a guardar a la BD  con nboletos = " + this.nboletos + "  ArregloBoletos[]= " + this.ArregloBoletos.length);
    this.imprimirTiket = true;
    Vector ciudades = new Vector();
    Vector indCiudades = new Vector();
    Vector indIds = new Vector();
    Vector lciu = (Vector)this.busquedas.variosFacadeRemote.queryBuscaCiudades();
    Vector TER = (Vector)this.busquedas.cajasTblFacadeRemote.queryBuscaTerminalId();
    BigInteger terminal = BigInteger.valueOf(Long.valueOf(TER.get(0).toString()).longValue());

    for (int i = 0; i < lciu.size(); i++)
    {
      Vector unaciudad = (Vector)lciu.get(i);
      ciudades.add(unaciudad.get(0).toString());
      indCiudades.add(unaciudad.get(1).toString());
      indIds.add(unaciudad.get(2).toString());
    }
    this.jProgressBar1.setValue(0);
    this.jProgressBar1.setMinimum(0);
    this.jProgressBar1.setMaximum(this.nboletos);
    String folioTarjeta = "";
    String adicional2 = "";
    for (int k = 0; k <= this.nboletos - 1; k++) {
      System.out.println(k + " --" + this.ArregloBoletos[k]);
      StringTokenizer lineaBoleto = new StringTokenizer(this.ArregloBoletos[k]);
      adicional2 = lineaBoleto.nextToken();
      this.bhora = lineaBoleto.nextToken();
      this.borigen = lineaBoleto.nextToken();
      this.btipo = lineaBoleto.nextToken();
      this.bdestino = lineaBoleto.nextToken();
      this.bprecio = lineaBoleto.nextToken();
      folioTarjeta = lineaBoleto.nextToken();
      this.bfecha_bol = lineaBoleto.nextToken();
      this.bfecha_bol = (this.bfecha_bol + " " + lineaBoleto.nextToken());
      this.basignacion = lineaBoleto.nextToken();
      this.badicional = "";
      if (lineaBoleto.hasMoreElements())
        this.badicional = lineaBoleto.nextToken();
      this.boleto.setFolioTarjeta(folioTarjeta);
      this.boleto.setCorteId(BigInteger.valueOf(getCorteid()));
      this.boleto.setAdicional1(this.badicional);

      this.boleto.setOrigenTarjeta(this.origent);
      Timestamp fecha_hora = null;

      System.out.println("timestamp: " + this.bfecha_bol);
      fecha_hora = Timestamp.valueOf("20" + this.bfecha_bol);
      this.boleto.setFechaHoraBoleto(new Date(fecha_hora.getTime()));

      int indor = indCiudades.indexOf("" + Integer.parseInt(this.borigen));

      if (indor < 0)
        this.boleto.setOrigenBoletoId(BigInteger.valueOf(Long.valueOf(this.borigen).longValue()));
      else
        this.boleto.setOrigenBoletoId(BigInteger.valueOf(Long.valueOf(indIds.get(indor).toString()).longValue()));
      int inddes = indCiudades.indexOf("" + Integer.parseInt(this.bdestino));

      if (inddes < 0)
        this.boleto.setDestinoBoletoId(BigInteger.valueOf(Long.valueOf(this.bdestino).longValue()));
      else
        this.boleto.setDestinoBoletoId(BigInteger.valueOf(Long.valueOf(indIds.get(inddes).toString()).longValue()));
      this.boleto.setTipoPasajero(this.btipo);

      this.boleto.setTipoVenta(this.basignacion);

      double impor = Double.valueOf(this.bprecio).doubleValue();
      this.totalImporte += impor;
      this.boleto.setImporteBoleto(BigDecimal.valueOf(impor));
      this.boleto.setAdicional2(adicional2);
      this.boleto.setAdicional3("" + (k + 1));
      this.boleto.setCreadoPor(BigInteger.valueOf(getIdusuario()));
      this.boleto.setUltimaActualizacionPor(BigInteger.valueOf(getIdusuario()));
      this.boleto.setFechaCreacion(new Timestamp(new Date().getTime()));
      this.boleto.setUltimaFechaActualizacion(new Timestamp(new Date().getTime()));

      this.boleto.setFolioBoleto(BigInteger.valueOf(Long.valueOf("-1").longValue()));
      this.boleto.setCiudadRecaudacionId(terminal);
      System.out.println("Desde GuaradarBoletos ==> Hora:" + this.bhora + "  Origen:" + this.borigen + "  Tipo:" + this.btipo + "  Destino:" + this.bdestino + "  Precio:" + this.bprecio);
      try {
        this.busquedas.boletosBoleteraTblFacadeRemote.create(this.boleto, this.idTerminal);
      } catch (EJBException ex) {
        this.imprimirTiket = false;
        System.out.println("Ya existe el boleto(" + this.boleto.getAdicional3() + "): " + this.boleto.toString());
      }

      this.jProgressBar1.setValue(k);
      this.jProgressBar1.setStringPainted(true);
      this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
    }
    this.jProgressBar1.setValue(0);
    this.jProgressBar1.setStringPainted(true);
    this.jProgressBar1.paint(this.jProgressBar1.getGraphics());
  }

  public void setFoco()
  {
    this.jlbl_barraEstado.requestFocus();
  }
  public KeyEvent getEventoTeclado() {
    return this.eventoTeclado;
  }
  public void setEventoTeclado(KeyEvent evento) { this.eventoTeclado = evento; }

  private boolean imprimir_recibo_Atomaticos_LPT(String pSalidaImpresion) {
    String SalidaImpresion = pSalidaImpresion;
    SimpleDateFormat formatf2 = new SimpleDateFormat("dd/MM/yyyy");
    Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
    this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
    String sCad = "\n";
    sCad = sCad + "    " + this.empresa.toUpperCase();
    sCad = sCad + "\n";
    sCad = sCad + "\n";
    sCad = sCad + "       RECAUDACION VIAXER ";
    sCad = sCad + "\n";
    sCad = sCad + "         DEL DIA " + formatf2.format(new Date(this.fecha_servidor.getTime())); //this.fecha;
    sCad = sCad + "\n"; sCad = sCad + "\n";
    double monto = 0.0D;
    for (int j = 0; j < this.jtbl_turnos.getRowCount(); j++)
    {
      if (this.jtbl_turnos.getValueAt(j, 6).toString().equals("PENDIENTE"))
        continue;
      sCad = sCad + "PARTIDA:    " + this.jtbl_turnos.getValueAt(j, 0).toString();
      sCad = sCad + "\n";
      String folioCompleto = this.jtbl_turnos.getValueAt(j, 6).toString();
      String inicio = folioCompleto.substring(0, 10);
      String fin = folioCompleto.substring(10);
      sCad = sCad + "FOLIO TAR:  " + inicio;
      sCad = sCad + "\n";
      sCad = sCad + "            " + fin;
      sCad = sCad + "\n";
      sCad = sCad + "TURNO:      " + this.jtbl_turnos.getValueAt(j, 1).toString();
      sCad = sCad + "\n";
      sCad = sCad + "HORA INICIO:" + this.jtbl_turnos.getValueAt(j, 2).toString();
      sCad = sCad + "\n";
      sCad = sCad + "HORA FIN:   " + this.jtbl_turnos.getValueAt(j, 3).toString();
      sCad = sCad + "\n";
      sCad = sCad + "BOLETOS:    " + this.jtbl_turnos.getValueAt(j, 4).toString();
      sCad = sCad + "\n";
      sCad = sCad + "MONTO:      " + this.jtbl_turnos.getValueAt(j, 5).toString();
      sCad = sCad + "\n";
      monto += Double.valueOf(this.jtbl_turnos.getValueAt(j, 5).toString()).doubleValue();
    }

    sCad = sCad + "IMPORTE TOTAL:" + monto;
    sCad = sCad + "\n"; sCad = sCad + "\n";
    String mt = "" + monto;
    if (mt.indexOf(".") >= 0)
    {
      StringTokenizer tmt = new StringTokenizer(mt, ".");
      String centavos = tmt.nextToken();
      centavos = tmt.nextToken();
      if (centavos.length() == 1)
        centavos = centavos + "0";
      sCad = sCad + " (" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos " + centavos + "/100 M.N.)";
    }
    else {
      sCad = sCad + " (" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos 00/100 M.N. )";
    }sCad = sCad + "\n"; sCad = sCad + "\n";
    sCad = sCad + "RECAUDADOR: " + this.datosIniciales.get(1) + "-" + this.datosIniciales.get(2);
    SimpleDateFormat formatf = new SimpleDateFormat("dd_MM_yyyy");

    SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
    sCad = sCad + "\n";
    sCad = sCad + "FECHA DE RECAUDACION: " + formatf2.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n";
    sCad = sCad + "HORA DE RECAUDACION : " + formath.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n"; sCad = sCad + "\n";
    sCad = sCad + "FIRMA ________________________";
    sCad = sCad + "\n\n\n\n\n\n\n\n\n\n\n\n";
    sCad = sCad + "         .";
    try {
      if (SalidaImpresion.equals("ARCHIVO")) {
        SalidaImpresion = "C:\\TICKET_DATAFARE_" + formatf.format(new Date(this.fecha_servidor.getTime())).replace('/','_') + "" + formath.format(new Date(this.fecha_servidor.getTime())).replace('/','_').replace(":","").replace(" ","") + ".TXT";
        System.out.println("SalidaImpresion: "+SalidaImpresion);
      }
      FileOutputStream os = new FileOutputStream(SalidaImpresion);
      PrintStream ps = new PrintStream(os);
      ps.print(sCad);
      ps.flush();
      os.close();
      ps.close();
    } catch (FileNotFoundException fsctex) {
      fsctex.printStackTrace();
      return false;
    } catch (Exception sctex) {
      sctex.printStackTrace();
      return false;
    }
    return true;
  }

  private boolean imprimir_recibo_Pendientes_LPT(int pnr, String pSalidaImpresion)
  {
    String SalidaImpresion = pSalidaImpresion;
    int j = pnr;
    Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
    this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
    SimpleDateFormat formatf2 = new SimpleDateFormat("dd/MM/yyyy");
    String sCad = "\n";
    sCad = sCad + "    " + this.empresa.toUpperCase();
    sCad = sCad + "\n";
    sCad = sCad + "\n";
    sCad = sCad + "       RECAUDACION VIAXER ";
    sCad = sCad + "\n";
    sCad = sCad + "         DEL DIA " + formatf2.format(new Date(this.fecha_servidor.getTime()));//this.fecha;
    sCad = sCad + "\n"; sCad = sCad + "\n";
    double monto = 0.0D;
    if (!this.pendientes)
    {
      sCad = sCad + "OPERADOR:  " + this.jtxt_clave.getText() + "-" + this.jtxt_nombre.getText();
      sCad = sCad + "\n";
    }
    String folioCompleto = this.jtbl_turnos.getValueAt(j, 6).toString();
    if (folioCompleto.equals("PENDIENTE"))
    {
      sCad = sCad + "FOLIO TAR:  " + folioCompleto;
      sCad = sCad + "\n";
    }
    else
    {
      String inicio = folioCompleto.substring(0, 10);
      String fin = folioCompleto.substring(10);
      sCad = sCad + "FOLIO TAR:  " + inicio;
      sCad = sCad + "\n";
      sCad = sCad + "            " + fin;
      sCad = sCad + "\n";
    }
    sCad = sCad + "TURNO:      " + this.jtbl_turnos.getValueAt(j, 1).toString();
    sCad = sCad + "\n";
    sCad = sCad + "HORA INICIO:" + this.jtbl_turnos.getValueAt(j, 2).toString();
    sCad = sCad + "\n";
    sCad = sCad + "HORA FIN:   " + this.jtbl_turnos.getValueAt(j, 3).toString();
    sCad = sCad + "\n";
    sCad = sCad + "BOLETOS:    " + this.jtbl_turnos.getValueAt(j, 4).toString();
    sCad = sCad + "\n";
    sCad = sCad + "MONTO:      " + this.jtbl_turnos.getValueAt(j, 5).toString();
    sCad = sCad + "\n";
    monto += Double.valueOf(this.jtbl_turnos.getValueAt(j, 5).toString()).doubleValue();

    sCad = sCad + "IMPORTE TOTAL:   " + monto;
    sCad = sCad + "\n"; sCad = sCad + "\n";
    String mt = "" + monto;
    if (mt.indexOf(".") >= 0)
    {
      StringTokenizer tmt = new StringTokenizer(mt, ".");
      String centavos = tmt.nextToken();
      centavos = tmt.nextToken();
      if (centavos.length() == 1)
        centavos = centavos + "0";
      sCad = sCad + " (" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos " + centavos + "/100 M.N.)";
    }
    else {
      sCad = sCad + " (" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos 00/100 M.N. )";
    }sCad = sCad + "\n"; sCad = sCad + "\n";
    sCad = sCad + "RECAUDADOR:      " + this.datosIniciales.get(1) + "-" + this.datosIniciales.get(2);
    SimpleDateFormat formatf = new SimpleDateFormat("dd_MM_yyyy");
    SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
    sCad = sCad + "\n";
    sCad = sCad + "FECHA DE RECAUDACION: " + formatf2.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n";
    sCad = sCad + "HORA DE RECAUDACION : " + formath.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n"; sCad = sCad + "\n";
    sCad = sCad + "FIRMA ________________________";
    sCad = sCad + "\n\n\n\n\n\n\n\n\n\n\n\n";
    sCad = sCad + "         .";
    try {
      if (SalidaImpresion.equals("ARCHIVO")) {
        SalidaImpresion = "C:\\TICKET_DATAFARE_PENDIENTE_" + formatf.format(new Date(this.fecha_servidor.getTime())).replace('/','_') + "" + formath.format(new Date(this.fecha_servidor.getTime())).replace('/','_').replace(":", "").replace(" ", "")  + ".TXT";
      }
      FileOutputStream os = new FileOutputStream(SalidaImpresion);
      PrintStream ps = new PrintStream(os);
      ps.print(sCad);
      ps.flush();
      os.close();
      ps.close();
    } catch (FileNotFoundException fsctex) {
      fsctex.printStackTrace();
      return false;
    } catch (Exception sctex) {
      sctex.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean imprimir_recibo_rec_LPT(String[] datos, String pSalidaImpresion)
  {
    System.out.println("Legan los datos a imprimir ticket con: " + pSalidaImpresion);
    String[] datos_imprimir = datos;
    String SalidaImpresion = pSalidaImpresion;
    Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
    this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
    String sCad = "\n";
    sCad = sCad + "AUTOBUSES MEX-PUE ESTRELLA ROJA";
    sCad = sCad + "\n\n";
    sCad = sCad + "No. PAQUETE:   " + datos_imprimir[0];
    sCad = sCad + "\n\n";
    sCad = sCad + "CONCEPTO:  " + datos_imprimir[2];
    sCad = sCad + "\n";
    sCad = sCad + "IMPORTE:   " + datos_imprimir[3];
    sCad = sCad + "\n\n";
    sCad = sCad + "     " + datos_imprimir[7];
    sCad = sCad + "\n\n";
    sCad = sCad + "RECAUDADOR: " + datos_imprimir[8] + "-" + datos_imprimir[1];
    sCad = sCad + "\n";
    sCad = sCad + "SUPERVISOR: " + datos_imprimir[5] + "-" + datos_imprimir[6];
    SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
    sCad = sCad + "\n";
    sCad = sCad + "FECHA DE RECOLECCION: " + formatf.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n";
    sCad = sCad + "HORA DE RECOLECCION:  " + formath.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n\n";
    sCad = sCad + "FIRMA ________________________";
    sCad = sCad + "\n\n\n\n\n\n\n\n\n\n\n\n";
    sCad = sCad + "         .";
    try {
      if (SalidaImpresion.equals("ARCHIVO")) {
        SalidaImpresion = "C:\\TICKET_RECOLECCION_" + datos_imprimir[0] + ".TXT";
      }

      FileOutputStream os = new FileOutputStream(SalidaImpresion);
      PrintStream ps = new PrintStream(os);
      ps.print(sCad);
      ps.flush();
      os.close();
      ps.close();
    } catch (FileNotFoundException fsctex) {
      fsctex.printStackTrace();
      return false;
    } catch (Exception sctex) {
      sctex.printStackTrace();
      return false;
    }
    return true;
  }

  class CenterRenderer extends DefaultTableCellRenderer
  {
    public CenterRenderer()
    {
      setHorizontalAlignment(0);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      return this;
    }
  }

  class RightRenderer extends DefaultTableCellRenderer
  {
    public RightRenderer()
    {
      setHorizontalAlignment(4);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      return this;
    }
  }

  class imprimir_recibo_recoleccion
    implements Printable
  {
    private double PIXELES_POR_PULGADA = 72.0D;
    private double ANCHO = 3.625D;
    private double ALTO = 12.0D;
    private String[] datos_imprimir;

    public imprimir_recibo_recoleccion(String[] datos_recibidos)
    {
      this.datos_imprimir = datos_recibidos;
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
      if (page > 0) {
        return 1;
      }
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pf.getImageableX(), pf.getImageableY());
      Font fontVar = new Font("Arial", 0, 9);
      g.setFont(fontVar);
      int lineaNueva = 11; int i = 1;

      g.drawString(JIFLecturaViaxer.this.empresa.toUpperCase(), 10, lineaNueva);
      i++; i++;
      g.drawString("No. PAQUETE ", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(this.datos_imprimir[0], 85, lineaNueva * i);
      i++; i++;
      g.drawString("CONCEPTO", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(this.datos_imprimir[2], 85, lineaNueva * i);
      i++;
      g.drawString("IMPORTE", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(this.datos_imprimir[3], 85, lineaNueva * i);
      i++; i++;
      g.drawString(" ", 0, lineaNueva * i);
      g.drawString(this.datos_imprimir[7], 0, lineaNueva * i);
      i++; i++;
      g.drawString("RECAUDADOR", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(this.datos_imprimir[8] + "-" + this.datos_imprimir[1], 85, lineaNueva * i);
      i++;
      g.drawString("SUPERVISOR", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(this.datos_imprimir[5] + "-" + this.datos_imprimir[6], 85, lineaNueva * i);
      SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
      i++;
      g.drawString("FECHA DE RECOLECCION: ", 0, lineaNueva * i);
      g.drawString("" + formatf.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++;
      g.drawString("HORA DE RECOLECCION : ", 0, lineaNueva * i);
      g.drawString("" + formath.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++; i++;
      g.drawString("FIRMA ________________________", 0, lineaNueva * i);
      i++; i++; i++; i++;
      g.drawString("         .", 150, lineaNueva * i);
      return 0;
    }

    public boolean ImprimeDatos()
    {
      return setPrint();
    }

    private boolean setPrint() {
      PageFormat formatoPagina = new PageFormat();
      double papelAncho = this.ANCHO * this.PIXELES_POR_PULGADA;
      double papelAlto = this.ALTO * this.PIXELES_POR_PULGADA;
      double papelImaginableX = this.PIXELES_POR_PULGADA * 0.5D;
      double papelImaginableY = this.PIXELES_POR_PULGADA * 0.0625D;
      double papelImaginableAncho = papelAncho - this.PIXELES_POR_PULGADA * 0.5D;
      double papelImaginableAlto = papelAlto - this.PIXELES_POR_PULGADA;

      Paper papel = new Paper();
      papel.setSize(papelAncho, papelAlto);
      papel.setImageableArea(papelImaginableX, papelImaginableY, papelImaginableAncho, papelImaginableAlto);
      formatoPagina.setPaper(papel);
      formatoPagina.setOrientation(1);

      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(this, formatoPagina);

      PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
      try
      {
        job.setPrintService(JIFLecturaViaxer.this.impresoraEncontrada);

        job.print();
      } catch (PrinterException ex) {
        return false;
      }
      return true;
    }

    String customFormat(String pattern, float value) {
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      return myFormatter.format(value);
    }
  }

  class vista_pago_impresion_recolecciones extends JDialog
  {
    private JTextArea jTextArea1 = new JTextArea();
    private JLabel jLabel1 = new JLabel();
    private JButton jbtn_si = new JButton();
    private JButton jbtn_no = new JButton();
    private ImageIcon imagen_pregunta = new ImageIcon(vista_pago_impresion_recolecciones.class.getResource("pregunta.gif"));
    private JLabel jLabel2 = new JLabel();
    private int alto = 0;
    private String[] datos;
    private String idsupervisor = "1";
    private String numpaquete = "0";

    public vista_pago_impresion_recolecciones(String[] data, String ids) {
      setTitle("Vista de Impresion de Ticket");
      setDefaultLookAndFeelDecorated(true);
      setUndecorated(true);
      getRootPane().setWindowDecorationStyle(7);
      setAlwaysOnTop(true);
      this.datos = data;
      this.idsupervisor = ids;
      try {
        jbInit();
        jtxt_clave.requestFocusInWindow();
      } catch (Exception e) {
        e.printStackTrace();
      }
      setModal(true);
      jtxt_clave.requestFocusInWindow();
    }

    private void jbInit() throws Exception {
      setSize(new Dimension(400, 450 + this.alto));
      setLayout(null);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension DilaogSize = getSize();
      if (DilaogSize.height > screenSize.height) {
        DilaogSize.height = screenSize.height;
      }
      if (DilaogSize.width > screenSize.width) {
        DilaogSize.width = screenSize.width;
      }
      setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2);
      this.jTextArea1.setBounds(new Rectangle(10, 10, 375, 300 + this.alto));
      this.jTextArea1.setEditable(false);
      this.jTextArea1.setFocusable(false);
      this.jTextArea1.setFont(new Font("Dialog", 0, 12));
      this.jTextArea1.setSize(new Dimension(375, 300 + this.alto));
      this.jLabel1.setText("¿Seguro que desea hacer la Recoleccion?");
      this.jLabel1.setBounds(new Rectangle(105, 330 + this.alto, 250, 15));
      this.jLabel1.setFont(new Font("Arial", 1, 12));
      this.jbtn_si.setText("Si");
      this.jbtn_si.setBounds(new Rectangle(145, 365 + this.alto, 50, 25));
      this.jbtn_si.setFont(new Font("Arial", 1, 12));
      this.jbtn_si.setMnemonic('s');
      this.jbtn_si.setHorizontalTextPosition(0);
      this.jbtn_si.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JIFLecturaViaxer.vista_pago_impresion_recolecciones.this.jbtn_si_actionPerformed(e);
        }
      });
      this.jbtn_si.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          JIFLecturaViaxer.vista_pago_impresion_recolecciones.this.jbtn_si_keyPressed(e);
        }
      });
      this.jbtn_no.setText("No");
      this.jbtn_no.setBounds(new Rectangle(205, 365 + this.alto, 50, 25));
      this.jbtn_no.setMnemonic('n');
      this.jbtn_no.setFont(new Font("Arial", 1, 12));
      this.jbtn_no.setHorizontalTextPosition(0);
      this.jbtn_no.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JIFLecturaViaxer.vista_pago_impresion_recolecciones.this.jbtn_no_actionPerformed(e);
        }
      });
      this.jbtn_no.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          JIFLecturaViaxer.vista_pago_impresion_recolecciones.this.jbtn_no_keyPressed(e);
        }
      });
      this.jLabel2.setBounds(new Rectangle(60, 315 + this.alto, 35, 45));
      this.jLabel2.setIcon(this.imagen_pregunta);
      this.jTextArea1.append(" \nNo Paquete.: " + this.datos[0] + "\n\n");
      this.jTextArea1.append(" Concepto             :   " + this.datos[2] + "\n");
      this.jTextArea1.append(" Importe                 :   " + this.datos[3] + "\n\n");
      String total_letras = "    ( " + new cantidad_a_letras().toLetras(Double.valueOf(this.datos[3]).longValue()) + "Pesos 00/M.N. )";
      this.datos[7] = total_letras;
      this.jTextArea1.append(total_letras + "\n\n");

      this.datos[8] = datosIniciales.get(1).toString();//lu.get(0).getUsuarioNumero();
      this.jTextArea1.append(" Recaudador       :   " + this.datos[8] + "-" + this.datos[1] + "\n");

      this.datos[6] = datosIniciales.get(2).toString();///lu.get(0).getDescripcion();
      this.jTextArea1.append(" Supervisor          :   " + this.datos[5] + "-" + this.datos[6] + "\n");
      SimpleDateFormat formatf = new SimpleDateFormat("dd/mm/yyyy");
      SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
      this.jTextArea1.append(" Fecha de Recoleccion:  " + formatf.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())) + "\n");
      this.jTextArea1.append(" Hora de Recoleccion   : " + formath.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())) + "\n\n");
      this.jTextArea1.append(" Firma: ________________________ \n");
      add(this.jLabel2, null);
      add(this.jbtn_no, null);
      add(this.jbtn_si, null);
      add(this.jLabel1, null);
      add(this.jTextArea1, null);
      this.jbtn_si.requestFocus();
    }

    private void jbtn_si_actionPerformed(ActionEvent e)
    {
      boolean recol = ingresarRecoleccion(JIFLecturaViaxer.this.montorecoleccion, JIFLecturaViaxer.this.autorizado);
      if (recol)
      {
        dispose();

        this.datos[0] = this.numpaquete;
        JIFLecturaViaxer.this.imprimir_recibo_rec(this.datos, this.datos[0]);
      }
      else
      {
        new jdlg_error("¡No se registro la recoleccion! ", "", "Recolecciona no registrada").setVisible(true);
      }
    }

    private void jbtn_no_actionPerformed(ActionEvent e) {
      dispose();
    }

    private void jbtn_si_keyPressed(KeyEvent e)
    {
      if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
        this.jbtn_no.requestFocus();
      if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
        this.jbtn_no.requestFocus();
      if (e.getKeyCode() == 10)
        this.jbtn_si.doClick();
    }

    private void jbtn_no_keyPressed(KeyEvent e) {
      if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
        this.jbtn_si.requestFocus();
      if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
        this.jbtn_si.requestFocus();
      if (e.getKeyCode() == 10)
        this.jbtn_no.doClick(); 
    }
    private boolean ingresarRecoleccion(float recoCantidad, String numeroSup) {
        System.out.println("El supervisor es: " + numeroSup);
      BigInteger idSupervisor = BigInteger.valueOf(Long.valueOf(numeroSup).longValue());
      Vector x = (Vector)JIFLecturaViaxer.this.busquedas.variosFacadeRemote.fechaServidor();
      fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
      Timestamp tmp = fecha_servidor;
      TmsActividadesSesionTbl actRecoleccion;
      try { actRecoleccion = JIFLecturaViaxer.this.busquedas.actividadesSesionTblFacadeRemote.getActividadPorClave("RECOL");
      } catch (ActividadNoEncontradoException ex) {
        System.out.println(ex.getMessage());
        return false;
      }

      TmsSesionActividadesTbl sesionActividad = new TmsSesionActividadesTbl();
      sesionActividad.setCorteId(JIFLecturaViaxer.this.corteActual);
      sesionActividad.setActividadSesionId(actRecoleccion);

      Vector npaq = (Vector)JIFLecturaViaxer.this.busquedas.variosFacadeRemote.queryBuscaNumeroPaqueteActual();
      System.out.println("Esta es el numero actual del paquete: " + npaq.get(0).toString());
      sesionActividad.setValorActividad("" + (Integer.valueOf(npaq.get(0).toString()).intValue() + 1));
      sesionActividad.setFechaHoraActividad(tmp);
      sesionActividad.setAutorizadoPor(idSupervisor);

      Vector vide = (Vector)JIFLecturaViaxer.this.busquedas.variosFacadeRemote.queryBuscaIdEmpresaByNombre("AMPERSA");
      long empresaid = Long.valueOf(vide.get(0).toString()).longValue();
      TmsEmpresasTbl empre = JIFLecturaViaxer.this.busquedas.variosFacadeRemote.queryBuscaEmpresaPorId(BigDecimal.valueOf(empresaid));
      empre = new TmsEmpresasTbl();
      empre.setEmpresaId(BigDecimal.valueOf(empresaid));
      sesionActividad.setEmpresaId(empre);
      sesionActividad.setCreadoPor(JIFLecturaViaxer.this.getIdusuario());
      sesionActividad.setFechaCreacion(tmp);
      sesionActividad.setUltimaActualizacionPor(JIFLecturaViaxer.this.getIdusuario());
      sesionActividad.setUltimaFechaActualizacion(tmp);
      sesionActividad = JIFLecturaViaxer.this.busquedas.sesionActividadesTblFacadeRemote.insertarSesionActividad(sesionActividad, JIFLecturaViaxer.this.idTerminal);
      this.numpaquete = sesionActividad.getValorActividad();
      System.out.println("RECOLECCION:" + sesionActividad.getValorActividad());

      TmsVtaTiposPagoTbl tipoPago = JIFLecturaViaxer.this.busquedas.vtaTiposPagoTblFacadeRemote.obtenerPagoPorClave("EFE");

      TmsRecoleccionesTbl recoleccion = new TmsRecoleccionesTbl();
      recoleccion.setSesionActividadId(sesionActividad.getSesionActividadId().toBigInteger());
      recoleccion.setTipoPagoId(tipoPago);
      recoleccion.setCantidad(new BigInteger("1"));
      recoleccion.setMonto(new BigDecimal(recoCantidad));
      recoleccion.setReferencia(tipoPago.getClave());
      recoleccion.setAutorizadoPor(idSupervisor);
      recoleccion.setCreadoPor(BigInteger.valueOf(JIFLecturaViaxer.this.getIdusuario()));
      recoleccion.setFechaCreacion(tmp);
      recoleccion.setUltimaActualizacionPor(BigInteger.valueOf(JIFLecturaViaxer.this.getIdusuario()));
      recoleccion.setUltimaFechaActualizacion(tmp);
      JIFLecturaViaxer.this.busquedas.recoleccionFacateRemote.create(recoleccion, JIFLecturaViaxer.this.idTerminal);

      System.out.println("recoleccion:" + recoleccion.getReferencia());
      double mr = Double.valueOf("" + JIFLecturaViaxer.this.montorecoleccion).doubleValue();
      if (JIFLecturaViaxer.this.acumulado >= mr) {
        JIFLecturaViaxer.this.setAcumulado(JIFLecturaViaxer.this.acumulado - mr);
      }

      return true;
    }
  }

  class imprimir_recibo_Pendientes
    implements Printable
  {
    private double PIXELES_POR_PULGADA = 72.0D;
    private double ANCHO = 3.625D;
    private double ALTO = 12.0D;
    private int nr;

    public imprimir_recibo_Pendientes()
    {
    }

    public int print(Graphics g, PageFormat pf, int page)
      throws PrinterException
    {
      if (page > 0) {
        return 1;
      }
      Vector x = (Vector)JIFLecturaViaxer.this.busquedas.variosFacadeRemote.fechaServidor();
      //JIFLecturaViaxer.access$1602(JIFLecturaViaxer.this, Timestamp.valueOf(x.get(0).toString()));
      fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pf.getImageableX(), pf.getImageableY());
      Font fontVar = new Font("Arial", 0, 9);
      g.setFont(fontVar);
      int lineaNueva = 11; int i = 1;

      g.drawString(JIFLecturaViaxer.this.empresa.toUpperCase(), 10, lineaNueva);
      i++;
      g.drawString("RECAUDACION DATAFARE ", 35, lineaNueva * i);
      i++;
      g.drawString("DEL DIA " + JIFLecturaViaxer.this.fecha, 40, lineaNueva * i);
      i++; i++;
      double monto = 0.0D;

      int j = this.nr;
      if (!JIFLecturaViaxer.this.pendientes)
      {
        g.drawString("OPERADOR: ", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(JIFLecturaViaxer.this.jtxt_clave.getText() + "-" + JIFLecturaViaxer.this.jtxt_nombre.getText(), 85, lineaNueva * i);
        i++;
      }
      g.drawString("FOLIO TAR", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      String folioCompleto = JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 6).toString();
      if (folioCompleto.equals("PENDIENTE"))
      {
        g.drawString(folioCompleto, 85, lineaNueva * i);
        i++;
      }
      else
      {
        String inicio = folioCompleto.substring(0, 10);
        String fin = folioCompleto.substring(10);
        g.drawString(inicio, 85, lineaNueva * i);
        i++;
        g.drawString(fin, 85, lineaNueva * i);
        i++;
      }
      g.drawString("TURNO", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 1).toString(), 85, lineaNueva * i);
      i++;
      g.drawString("HORA INICIO", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 2).toString(), 85, lineaNueva * i);
      i++;
      g.drawString("HORA FIN", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 3).toString(), 85, lineaNueva * i);
      i++;
      g.drawString("BOLETOS", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 4).toString(), 85, lineaNueva * i);
      i++;
      g.drawString("MONTO", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 5).toString(), 85, lineaNueva * i);
      i++;
      monto += Double.valueOf(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 5).toString()).doubleValue();

      g.drawString("IMPORTE TOTAL", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString("" + monto, 85, lineaNueva * i);
      i++; i++;
      g.drawString(" ", 0, lineaNueva * i);
      String mt = "" + monto;
      if (mt.indexOf(".") >= 0)
      {
        StringTokenizer tmt = new StringTokenizer(mt, ".");
        String centavos = tmt.nextToken();
        centavos = tmt.nextToken();
        if (centavos.length() == 1)
          centavos = centavos + "0";
        g.drawString("(" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos " + centavos + "/100 M.N.)", 0, lineaNueva * i);
      }
      else {
        g.drawString("(" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos 00/100 M.N. )", 0, lineaNueva * i);
      }i++; i++;
      g.drawString("RECAUDADOR", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFLecturaViaxer.this.datosIniciales.get(1) + "-" + JIFLecturaViaxer.this.datosIniciales.get(2), 85, lineaNueva * i);
      SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
      i++;
      g.drawString("FECHA DE RECAUDACION: ", 0, lineaNueva * i);
      g.drawString("" + formatf.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++;
      g.drawString("HORA DE RECAUDACION : ", 0, lineaNueva * i);
      g.drawString("" + formath.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++; i++;
      g.drawString("FIRMA ________________________", 0, lineaNueva * i);
      i++; i++; i++; i++;
      g.drawString("         .", 150, lineaNueva * i);

      return 0;
    }

    public boolean ImprimeDatos(int pnr) {
      this.nr = pnr;
      return setPrint();
    }

    private boolean setPrint() {
      PageFormat formatoPagina = new PageFormat();
      double papelAncho = this.ANCHO * this.PIXELES_POR_PULGADA;
      double papelAlto = this.ALTO * this.PIXELES_POR_PULGADA;
      double papelImaginableX = this.PIXELES_POR_PULGADA * 0.5D;
      double papelImaginableY = this.PIXELES_POR_PULGADA * 0.0625D;
      double papelImaginableAncho = papelAncho - this.PIXELES_POR_PULGADA * 0.5D;
      double papelImaginableAlto = papelAlto - this.PIXELES_POR_PULGADA;

      Paper papel = new Paper();
      papel.setSize(papelAncho, papelAlto);
      papel.setImageableArea(papelImaginableX, papelImaginableY, papelImaginableAncho, papelImaginableAlto);
      formatoPagina.setPaper(papel);
      formatoPagina.setOrientation(1);

      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(this, formatoPagina);

      PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
      try
      {
        job.setPrintService(JIFLecturaViaxer.this.impresoraEncontrada);

        job.print();
      } catch (PrinterException ex) {
        return false;
      }
      return true;
    }

    String customFormat(String pattern, float value) {
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      return myFormatter.format(value);
    }
  }

  class imprimir_recibo_Atomaticos
    implements Printable
  {
    private double PIXELES_POR_PULGADA = 72.0D;
    private double ANCHO = 3.625D;
    private double ALTO = 12.0D;

    public imprimir_recibo_Atomaticos()
    {
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
      if (page > 0) {
        return 1;
      }
      Vector x = (Vector)JIFLecturaViaxer.this.busquedas.variosFacadeRemote.fechaServidor();
      //JIFLecturaViaxer.access$1602(JIFLecturaViaxer.this, Timestamp.valueOf(x.get(0).toString()));
      fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pf.getImageableX(), pf.getImageableY());
      Font fontVar = new Font("Arial", 0, 9);
      g.setFont(fontVar);
      int lineaNueva = 11; int i = 1;

      g.drawString(JIFLecturaViaxer.this.empresa.toUpperCase(), 10, lineaNueva);
      i++;
      g.drawString("RECAUDACION DATAFARE ", 35, lineaNueva * i);
      i++;
      g.drawString("DEL DIA " + JIFLecturaViaxer.this.fecha, 40, lineaNueva * i);
      i++; i++;
      double monto = 0.0D;
      for (int j = 0; j < JIFLecturaViaxer.this.jtbl_turnos.getRowCount(); j++)
      {
        if (JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 6).toString().equals("PENDIENTE"))
          continue;
        g.drawString("PARTIDA", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 0).toString(), 85, lineaNueva * i);
        i++;
        g.drawString("FOLIO TAR", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        String folioCompleto = JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 6).toString();
        String inicio = folioCompleto.substring(0, 10);
        String fin = folioCompleto.substring(10);
        g.drawString(inicio, 85, lineaNueva * i);
        i++;
        g.drawString(fin, 85, lineaNueva * i);
        i++;
        g.drawString("TURNO", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 1).toString(), 85, lineaNueva * i);
        i++;
        g.drawString("HORA INICIO", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 2).toString(), 85, lineaNueva * i);
        i++;
        g.drawString("HORA FIN", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 3).toString(), 85, lineaNueva * i);
        i++;
        g.drawString("BOLETOS", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 4).toString(), 85, lineaNueva * i);
        i++;
        g.drawString("MONTO", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 5).toString(), 85, lineaNueva * i);
        i++;
        monto += Double.valueOf(JIFLecturaViaxer.this.jtbl_turnos.getValueAt(j, 5).toString()).doubleValue();
      }

      g.drawString("IMPORTE TOTAL", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString("" + monto, 85, lineaNueva * i);
      i++; i++;
      g.drawString(" ", 0, lineaNueva * i);
      String mt = "" + monto;
      if (mt.indexOf(".") >= 0)
      {
        StringTokenizer tmt = new StringTokenizer(mt, ".");
        String centavos = tmt.nextToken();
        centavos = tmt.nextToken();
        if (centavos.length() == 1)
          centavos = centavos + "0";
        g.drawString("(" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos " + centavos + "/100 M.N.)", 0, lineaNueva * i);
      }
      else {
        g.drawString("(" + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(monto).toString()).longValue()) + "Pesos 00/100 M.N. )", 0, lineaNueva * i);
      }i++; i++;
      g.drawString("RECAUDADOR", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFLecturaViaxer.this.datosIniciales.get(1) + "-" + JIFLecturaViaxer.this.datosIniciales.get(2), 85, lineaNueva * i);
      SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
      i++;
      g.drawString("FECHA DE RECAUDACION: ", 0, lineaNueva * i);
      g.drawString("" + formatf.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++;
      g.drawString("HORA DE RECAUDACION : ", 0, lineaNueva * i);
      g.drawString("" + formath.format(new Date(JIFLecturaViaxer.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++; i++;
      g.drawString("FIRMA ________________________", 0, lineaNueva * i);
      i++; i++; i++; i++;
      g.drawString("         .", 150, lineaNueva * i);
      return 0;
    }
  }

  class Jdlg_Pregunta2 extends JDialog
  {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jlbl_barraEstadoP = new JLabel();
    private ImageIcon imagen_pregunta = new ImageIcon(JIFLecturaViaxer.Jdlg_Pregunta.class.getResource("pregunta.gif"));
    private String numref;

    public Jdlg_Pregunta2(String title, String nref)
    {
      setTitle(title);
      setModal(true);
      setDefaultLookAndFeelDecorated(true);
      this.numref = nref;
      setUndecorated(true);
      getRootPane().setWindowDecorationStyle(7);
      setAlwaysOnTop(true);
      try {
        jbInit();
      } catch (Exception e) {
        e.printStackTrace();
      }
      this.jLabel3.requestFocus();
     this.addComponentListener(new ComponentAdapter() {
                public void componentShown(ComponentEvent ce) {
                    jLabel3.requestFocusInWindow();
                }
            });      
    }

    private void jbInit() throws Exception {
      setSize(new Dimension(333, 128));
      getContentPane().setLayout(null);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension DilaogSize = getSize();
      if (DilaogSize.height > screenSize.height) {
        DilaogSize.height = screenSize.height;
      }
      if (DilaogSize.width > screenSize.width) {
        DilaogSize.width = screenSize.width;
      }
      setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2);
      this.jLabel1.setText("¿Se imprimio correctamente el comprobante?");
      this.jLabel1.setBounds(new Rectangle(45, 35, 265, 15));
      this.jLabel1.setFont(new Font("Arial", 1, 12));
      this.jLabel3.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFLecturaViaxer.Jdlg_Pregunta2.this.jLabel1_KeyPressed(evt);
        }
      });
      this.jLabel3.setFocusTraversalKeysEnabled(false);
      this.jLabel2.setBounds(new Rectangle(5, 5, 35, 45));
      this.jLabel2.setSize(new Dimension(35, 45));
      this.jLabel2.setIcon(this.imagen_pregunta);

      this.jLabel3.setBounds(new Rectangle(45, 10, 285, 15));
      this.jLabel3.setFont(new Font("Arial", 1, 12));
      this.jLabel3.setText("Referencia de Pago: " + this.numref);
      this.jlbl_barraEstadoP.setFont(new Font("Tahoma", 1, 12));
      this.jlbl_barraEstadoP.setForeground(new Color(153, 153, 153));
      this.jlbl_barraEstadoP.setBorder(BorderFactory.createBevelBorder(1));
      this.jlbl_barraEstadoP.setBounds(new Rectangle(0, 69, 333, 25));
      this.jlbl_barraEstadoP.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
      this.jlbl_barraEstadoP.setHorizontalAlignment(0);
      add(this.jLabel3, null);
      add(this.jLabel2, null);
      add(this.jLabel1, null);
      add(this.jlbl_barraEstadoP, null);
      this.jLabel1.setFocusable(false);
      this.jLabel2.setFocusable(false);
      this.jLabel3.setFocusable(true);
      this.jLabel3.requestFocus();
    }

    private void jLabel1_KeyPressed(KeyEvent evt)
    {
      if (evt.getKeyCode() == 27)
      {
        reimpresion = true;
        dispose();
      }
      if (evt.getKeyCode() == 10)
      {
        reimpresion = false;
        dispose();
      }
    }
  }

  class Jdlg_Pregunta extends JDialog
  {
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jlbl_barraEstadoP = new JLabel();
    private ImageIcon imagen_pregunta = new ImageIcon(Jdlg_Pregunta.class.getResource("pregunta.gif"));
    private String numref;

    public Jdlg_Pregunta(String title)
    {
      setTitle(title);
      setModal(true);
      setDefaultLookAndFeelDecorated(true);
      setUndecorated(true);
      getRootPane().setWindowDecorationStyle(7);
      setAlwaysOnTop(true);
      try {
        jbInit();
      } catch (Exception e) {
        e.printStackTrace();
      }
      this.jLabel3.requestFocus();
      addComponentListener(new ComponentAdapter() {
        public void componentShown(ComponentEvent ce) {
          JIFLecturaViaxer.Jdlg_Pregunta.this.jLabel3.requestFocusInWindow();
        } } );
    }

    private void jbInit() throws Exception {
      setSize(new Dimension(333, 128));
      getContentPane().setLayout(null);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension DilaogSize = getSize();
      if (DilaogSize.height > screenSize.height) {
        DilaogSize.height = screenSize.height;
      }
      if (DilaogSize.width > screenSize.width) {
        DilaogSize.width = screenSize.width;
      }
      setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2);
      this.jLabel1.setText("¿Se imprimio correctamente el comprobante?");
      this.jLabel1.setBounds(new Rectangle(45, 35, 265, 15));
      this.jLabel1.setFont(new Font("Arial", 1, 12));
      this.jLabel3.addKeyListener(new KeyAdapter()
      {
        public void keyReleased(KeyEvent evt) {
          JIFLecturaViaxer.Jdlg_Pregunta.this.jLabel1_KeyReleased(evt);
        }
      });
      this.jLabel3.setFocusTraversalKeysEnabled(false);
      this.jLabel2.setBounds(new Rectangle(5, 5, 35, 45));
      this.jLabel2.setSize(new Dimension(35, 45));
      this.jLabel2.setIcon(this.imagen_pregunta);

      this.jLabel3.setBounds(new Rectangle(45, 10, 285, 15));
      this.jLabel3.setFont(new Font("Arial", 1, 12));
      this.jLabel3.setText("");
      this.jlbl_barraEstadoP.setFont(new Font("Tahoma", 1, 12));
      this.jlbl_barraEstadoP.setForeground(new Color(153, 153, 153));
      this.jlbl_barraEstadoP.setBorder(BorderFactory.createBevelBorder(1));
      this.jlbl_barraEstadoP.setBounds(new Rectangle(0, 69, 333, 25));
      this.jlbl_barraEstadoP.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
      this.jlbl_barraEstadoP.setHorizontalAlignment(0);
      add(this.jLabel3, null);
      add(this.jLabel2, null);
      add(this.jLabel1, null);
      add(this.jlbl_barraEstadoP, null);
      this.jLabel1.setFocusable(false);
      this.jLabel2.setFocusable(false);
      this.jLabel3.setFocusable(true);
      this.jLabel3.requestFocus();
    }

    private void jLabel1_KeyReleased(KeyEvent evt)
    {
      if (evt.getKeyCode() == 27)
      {
        reimpresion = true;
        dispose();
      }
      if (evt.getKeyCode() == 10)
      {
        reimpresion = false;
        dispose();
      }
    }
  }

  class jdlg_pregunta_SN extends JDialog
  {
    private JLabel jLabel1;
    private JLabel jlbl_barraEstado;
    private JLabel jlbl_mensaje;

    public jdlg_pregunta_SN(String titulo, String pregunta)
    {
      setTitle(titulo);
      setModal(true);
      setDefaultLookAndFeelDecorated(true);
      setUndecorated(true);
      getRootPane().setWindowDecorationStyle(7);
      setAlwaysOnTop(true);
      initComponents();
      setDefaultCloseOperation(0);
      setResizable(false);
      this.jlbl_mensaje.setText(pregunta);
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si                 <font color=FF0000>             ESC: </font> No  </html>");
      this.jlbl_barraEstado.setHorizontalAlignment(0);
      int nletras = pregunta.length();
      setSize(nletras * 6 + 80, 150);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension DilaogSize = getSize();
      if (DilaogSize.height > screenSize.height) {
        DilaogSize.height = screenSize.height;
      }
      if (DilaogSize.width > screenSize.width) {
        DilaogSize.width = screenSize.width;
      }
      setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2); setDefaultLookAndFeelDecorated(true);

      getRootPane().setWindowDecorationStyle(7);
      this.jlbl_mensaje.requestFocus();
    }

    private void initComponents()
    {
      this.jLabel1 = new JLabel();
      this.jlbl_barraEstado = new JLabel();
      this.jlbl_mensaje = new JLabel();
      this.jlbl_mensaje.setFocusTraversalKeysEnabled(false);

      setDefaultCloseOperation(2);
      this.jLabel1.setIcon(new ImageIcon("C:\\NetBeansProyects\\TMSRecaudacion\\TMSRecaudacion-app-client\\src\\java\\tmsrecaudacion\\images\\pregunta.gif"));

      this.jlbl_barraEstado.setFont(new Font("Tahoma", 1, 12));
      this.jlbl_barraEstado.setForeground(new Color(153, 153, 153));
      this.jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
      this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

      this.jlbl_mensaje.setFont(new Font("Arial", 1, 12));
      this.jlbl_mensaje.setText("sdfsdsdfsdfsdfsdfsdf");
      this.jlbl_mensaje.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFLecturaViaxer.jdlg_pregunta_SN.this.jlbl_mensajeKeyPressed(evt);
        }
      });
      GroupLayout layout = new GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jLabel1).add(14, 14, 14).add(this.jlbl_mensaje, -1, 321, 32767).add(424, 424, 424)).add(this.jlbl_barraEstado, -1, 800, 32767));

      layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(21, 21, 21).add(layout.createParallelGroup(3).add(this.jLabel1, -2, 42, -2).add(this.jlbl_mensaje)).addPreferredGap(0, 59, 32767).add(this.jlbl_barraEstado, -2, 27, -2)));

      pack();
    }

    private void jlbl_mensajeKeyPressed(KeyEvent evt) {
      if (evt.getKeyCode() == 27)
      {
        respuestaSN = false;
        dispose();
      }
      if (evt.getKeyCode() == 10)
      {
        respuestaSN = true;
        dispose();
      }
    }
  }

  class jdlg_seleccionarTarjetaAdentro extends JDialog
  {
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JLabel jlbl_barraEstado2;
    private JList jlist_tarjetas;
    private DefaultListModel modelolist;

    public jdlg_seleccionarTarjetaAdentro(Vector corridas)
    {
      setTitle("Seleccion de Tarjeta");
      setModal(true);
      setDefaultLookAndFeelDecorated(true);
      setAlwaysOnTop(true);
      initComponents();
      setDefaultCloseOperation(0);
      setResizable(false);
      this.modelolist = new DefaultListModel();
      for (int i = 0; i < corridas.size(); i++)
      {
        Vector unacorrida = (Vector)corridas.get(i);
        this.modelolist.addElement(unacorrida.get(0).toString());
      }

      this.jlist_tarjetas.setModel(this.modelolist);
      this.jlist_tarjetas.setSelectionMode(0);
      this.jlist_tarjetas.setSelectedIndex(0);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension DilaogSize = getSize();
      if (DilaogSize.height > screenSize.height) {
        DilaogSize.height = screenSize.height;
      }
      if (DilaogSize.width > screenSize.width) {
        DilaogSize.width = screenSize.width;
      }
      setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2); setDefaultLookAndFeelDecorated(true);

      this.jlbl_barraEstado2.setText("<html><font color=FF0000>ENTER</font> Seleccionar Tarjeta  | <font color=FF0000> F1 </font> Buscar Tarjeta  | <font color=FF0000> F2 </font> Dejar Pendiente</html>");
      setVisible(true);
    }

    private void initComponents()
    {
      this.jScrollPane1 = new JScrollPane();
      this.jlist_tarjetas = new JList();
      this.jlbl_barraEstado2 = new JLabel();
      this.jLabel2 = new JLabel();
      this.jLabel3 = new JLabel();

      setDefaultCloseOperation(2);
      this.jlist_tarjetas.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFLecturaViaxer.jdlg_seleccionarTarjetaAdentro.this.jlist_tarjetasKeyPressed(evt);
        }
      });
      this.jScrollPane1.setViewportView(this.jlist_tarjetas);

      this.jlbl_barraEstado2.setFont(new Font("Tahoma", 1, 12));
      this.jlbl_barraEstado2.setForeground(Color.black);
      this.jlbl_barraEstado2.setText("jLabel1");
      this.jlbl_barraEstado2.setBorder(BorderFactory.createBevelBorder(1));

      this.jLabel2.setFont(new Font("Tahoma", 1, 12));
      this.jLabel2.setHorizontalAlignment(0);
      this.jLabel2.setText("Selecciona una Tarjeta para relacionar");

      this.jLabel3.setFont(new Font("Tahoma", 1, 12));
      this.jLabel3.setHorizontalAlignment(0);
      this.jLabel3.setText("la Venta Abordo");

      GroupLayout layout = new GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(1).add(this.jlbl_barraEstado2, -1, 253, 32767).add(this.jLabel2, -1, 253, 32767).add(this.jLabel3, -1, 253, 32767).add(layout.createSequentialGroup().add(46, 46, 46).add(this.jScrollPane1, -2, 157, -2).addContainerGap(50, 32767)));

      layout.setVerticalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(this.jLabel2).addPreferredGap(0).add(this.jLabel3).addPreferredGap(0).add(this.jScrollPane1, -2, 280, -2).addPreferredGap(0, 15, 32767).add(this.jlbl_barraEstado2, -2, 40, -2)));

      pack();
    }

    private void jlist_tarjetasKeyPressed(KeyEvent evt) {
      if (evt.getKeyCode() == 10)
      {
        jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Relacionar Tarjeta", "¿Seguro que desea asignar la venta a la tarjeta "+(String)jlist_tarjetas.getSelectedValue()+"?");
        psn.setVisible(true);
        if (JIFLecturaViaxer.this.respuestaSN)
        {
          System.out.println("La tarjeta seleccionada es: " + (String)this.jlist_tarjetas.getSelectedValue());
          resultadoCorridas = (String)jlist_tarjetas.getSelectedValue();
          dispose();
        }
      }

      if (evt.getKeyCode() == 113)
      {
        jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Dejar Turno Pendiente", "¿Seguro que desea dejar el turno pendiente por relacionar?");        psn.setVisible(true);
        if (JIFLecturaViaxer.this.respuestaSN)
        {
          System.out.println("El turno esta pendiente...");
          resultadoCorridas = "PENDIENTE";
          dispose();
        }
      }

      if (evt.getKeyCode() == 112) {
        setAlwaysOnTop(false);
        JDialogBuscarTarjeta dbt = new JDialogBuscarTarjeta(JIFLecturaViaxer.this.busquedas);
        dbt.setVisible(true);
        setAlwaysOnTop(true);
        if (!dbt.getFolioTar().equals("nada"))
        {
          this.jlist_tarjetas.removeAll();
          this.modelolist.clear();
          this.modelolist.addElement(dbt.getFolioTar());
          this.jlist_tarjetas.setModel(this.modelolist);
          this.jlist_tarjetas.repaint();
          this.jlist_tarjetas.setSelectedIndex(0);
        }
      }
    }
  }
}