package tmsrecaudacion;

import TmsRecaudacion.entidad.ActividadNoEncontradoException;
import TmsRecaudacion.entidad.TmsActividadesSesionTbl;
import TmsRecaudacion.entidad.TmsAuditoriaTbl;
import TmsRecaudacion.entidad.TmsCajasTbl;
import TmsRecaudacion.entidad.TmsCortesTbl;
import TmsRecaudacion.entidad.TmsEmpresasTbl;
import TmsRecaudacion.entidad.TmsOperadoresTbl;
import TmsRecaudacion.entidad.TmsRecaudacionGastosTbl;
import TmsRecaudacion.entidad.TmsRecaudacionLineasTbl;
import TmsRecaudacion.entidad.TmsRecaudacionTbl;
import TmsRecaudacion.entidad.TmsRecoleccionesTbl;
import TmsRecaudacion.entidad.TmsRutasTbl;
import TmsRecaudacion.entidad.TmsServiciosGastosTbl;
import TmsRecaudacion.entidad.TmsSesionActividadesTbl;
import TmsRecaudacion.entidad.TmsTarjetasViajeTbl;
import TmsRecaudacion.entidad.TmsUsuariosTbl;
import TmsRecaudacion.entidad.TmsVtaTiposPagoTbl;
import TmsRecaudacion.solicitud.TmsActividadesSesionTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsAuditoriaTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsCajasRecTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsCortesTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsOperadoresTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecaudacionGastosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecaudacionLineasTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecaudacionTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRecoleccionesTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsRutasTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsServiciosGastosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsSesionActividadesTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsTarjetasViajeTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsUsuariosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsVariosFacadeRemote;
import TmsRecaudacion.solicitud.TmsVtaTiposPagoTblFacadeRemote;
import TmsRecaudacion.solicitud.UsuarioNoEncontradoException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import tms_TextFields.JCuantityTextField;
import tms_TextFields.JNumberTextField;
import tms_TextFields.JTextTextField;
import tms_encriptacion.EncriptarMD5;
import tmsparamgloblalrecauda.util.PcInfo;
import tmsparamgloblalrecauda.util.buscarDireccionMAC;
import tmsparamgloblalrecauda.util.cantidad_a_letras;
import tmsparamgloblalrecaudacion.tmscargaimpresorasrecauda;
import tmsparamgloblalrecaudacion.tmscargaparamglobrecauda;

public class JIFRecaudacion extends JInternalFrame
{
  private KeyEvent eventoTeclado;
  private Vector VBusOrdis = new Vector();
  private Vector VBusDirect = new Vector();
  private Vector VBusPeaje = new Vector();

  private String[] sBusOrdis = { "2014", "2033", "2034" };
  private String[] sBusDirect = { "2001", "2002", "2003", "2004", "2005", "2008", "2009", "2011", "2012", "2013", "2015", "2016", "2017", "2023", "2024", "2025", "2027" };
  private String[] sBusPeaje = { "2014", "2033", "2034", "2001", "2002", "2003", "2004", "2005", "2008", "2009", "2011", "2012", "2013", "2015", "2016", "2017", "2023", "2024", "2025", "2027" };
  private float cantidadViaticos = 0.0F;
  private boolean otraEmpresa = false;

  private int itsRowSelect = 0;
  private boolean tarifaVieja = false;
  private String hoteles = "";
  private String lavadas = "";
  private String alimentos = "";

  private long fechaAltamil = 0L;
  private JLabel jLabel1;
  private JLabel jLabel10;
  private JLabel jLabel11;
  private JLabel jLabel12;
  private JLabel jLabel13;
  private JLabel jLabel14;
  private JLabel jLabel16;
  private JLabel jLabel17;
  private JLabel jLabel2;
  private JLabel jLabel20;
  private JLabel jLabel21;
  private JLabel jLabel22;
  private JLabel jLabel23;
  private JLabel jLabel24;
  private JLabel jLabel25;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JLabel jLabel8;
  private JLabel jLabel9;
  private JPanel jPanel4;
  private JPanel jPanel5;
  private JPanel jPanel6;
  private JScrollPane jScrollPane1;
  private JScrollPane jScrollPane2;
  private JScrollPane jScrollPane3;
  private JLabel jlbl_anticipos;
  private JLabel jlbl_barraEstado;
  private JLabel jlbl_boletosAbordo;
  private JLabel jlbl_boletosManual;
  private JLabel jlbl_boletosSistema;
  private JLabel jlbl_cupo;
  private JLabel jlbl_folioPago;
  private JLabel jlbl_kilometros;
  private JLabel jlbl_letrero_saldos;
  private JLabel jlbl_nombreRuta;
  private JLabel jlbl_numeroCorrida;
  private JLabel jlbl_ocupacion;
  private JLabel jlbl_pagoPorOperador;
  private JLabel jlbl_retencion;
  private JLabel jlbl_saldoOperador;
  private JLabel jlbl_saldo_Anterior;
  private JLabel jlbl_sueldoOperador;
  private JLabel jlbl_total_pagar;
  private JLabel jlbl_ventaAbordo;
  private JLabel jlbl_ventaManual;
  private JLabel jlbl_ventaSistema;
  private JPanel jpnl_datosOperador;
  private JPanel jpnl_tajetasPendientes;
  private JPanel jpnl_tajetasPendientes2;
  private JPanel jpnl_tarjetaDescripcion;
  private JPanel jpnl_tarjetaDescripcion2;
  private JTable jtbl_gastos_empresa;
  private JTable jtbl_gastos_operador;
  private JTable jtbl_tarjetas_pendientes;
  private JTextTextField jtxt_autobus;
  private JTextTextField jtxt_claveOperador;
  private JTextField jtxt_nombreOperador;
  private TmsCajasTbl caja;
  private Vector datosIniciales;
  private String nombre_recaudador = "";
  private long sesionId = 1L;
  private boolean inicioGral = true;
  private tmscargaparamglobrecauda cargaparametros;
  private tmscargaimpresorasrecauda cargaimpresoras;
  private String[][] tarjetas_recaudadas;
  private String[][] gastos_tarjetas;
  private Vector vgastos_tarjetas;
  private Vector vgastos_tarjetasEmpre;
  private Vector vgastos_una_tarjeta;
  private Vector vgastos_una_tarjetaEmpre;
  private int ntarjetas_recaudadas = 0;
  private Vector indiceTarjetas = null;
  private Vector codigos = null;
  private Vector valores = null;
  private Vector formatos = null;
  private Vector impresoras = null;
  private Vector puertos = null;
  private Vector vgastos;
  private Vector vgastosCarga;
  private Vector vgastosId;
  private boolean gastosnocom = false;
  private boolean gastosComprobados = false;
  private SimpleDateFormat ffechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  private SimpleDateFormat ffecha = new SimpleDateFormat("dd/MM/yyyy");
  private SimpleDateFormat fhora = new SimpleDateFormat("HH:mm");
  private SimpleDateFormat fdia = new SimpleDateFormat("EEEE");
  private Vector vtajetasPendientes = new Vector();
  private String nombreoperador = "";
  private String aplicaretencion = "N";
  private TmsRecaudaManagedBean busquedas = new TmsRecaudaManagedBean();
  private Object[] encabezadotp = { "No Tarjeta", "Servicio", "Origen", "Destino", "Autobus", "Fecha", "Hora", "Ingreso", "Contrato", "Orden" };
  private Object[] encabezadogastos = { "Concepto", "Valor", "descontable", "id_gasto", "aumenta_recoleccion" };
  private DefaultTableModel defaultmodelo = new DefaultTableModel((Object[][])null, this.encabezadotp)
  {
    public boolean isCellEditable(int row, int column)
    {
      return column == 20;
    }
  };

  private DefaultTableModel gopermodelo = new DefaultTableModel((Object[][])null, this.encabezadogastos)
  {
    public boolean isCellEditable(int row, int column)
    {
      return column == 4;
    }
  };

  private DefaultTableModel gempremodelo = new DefaultTableModel((Object[][])null, this.encabezadogastos)
  {
    public boolean isCellEditable(int row, int column)
    {
      return column == 4;
    }
  };
  private String idTerminal;
  private Vector funciones;
  private Vector fauditables;
  private long idusuario = 999L;
  private long idmenu = 0L;
  private long corteId = 0L;
  private int nrowactual = 0;
  private boolean insertar = true;
  private boolean reimpresion = false;
  private Timestamp fecha_servidor = null;
  private double acumulado = 0.0D;
  private double vtaAbordoManual = 0.0D;
  private float montorecoleccion = Float.valueOf("5000.00").floatValue();
  private float toperecoleccion = Float.valueOf("7000.00").floatValue();
  private boolean respuestaSN = true;
  private String nombre_equipo = "";
  private jdlgDatosSupervisor dlgSupervisor;
  private TmsCortesTbl corteActual;
  private buscarDireccionMAC bMac;
  private String autorizado = "";
  private Vector vgastosComprobados = new Vector();
  private String[] ArregloBoletos = new String[1000];
  private Vector indices_ventas;
  private String[][] vtas = { { "0.0", "0.0", "0.0", "0.0", "0.0" }, { "0", "0", "0", "0", "0" }, { "0.0", "0.0", "0.0", "0.0", "0.0" }, { "0", "0", "0", "0", "0" } };
  private boolean buscavtabordo = false;
  private boolean buscavtasistema = false;

  private boolean noRecauda = false;
  private String ipAS;
  private int portAS;
  private Vector tarRecAnt = new Vector();
  private boolean imprimeDiesel = false;
  private long folioDiesel = 0L;
  private float montoDiesel = 0.0F;
  private boolean encuentraTarifa = false;

  public JIFRecaudacion(Vector pDatosIniciales)
  {
    this.datosIniciales = pDatosIniciales;
    setIdmenu(Long.valueOf(this.datosIniciales.get(4).toString()).longValue());
    setIdusuario(Long.valueOf(this.datosIniciales.get(0).toString()).longValue());
    this.ipAS = this.datosIniciales.get(5).toString();
    this.portAS = Integer.valueOf(this.datosIniciales.get(6).toString()).intValue();
    this.nombre_recaudador = this.datosIniciales.get(2).toString();
    setSesionId(this.datosIniciales.get(3).toString());
    cargarValores();

    BuscaCorte();
    setInicioGral(this.respuestaSN);
    if (this.respuestaSN)
    {
      initComponents();
      f10_Enable();

      for (int i = 0; i < this.sBusOrdis.length; i++)
        this.VBusOrdis.add(this.sBusOrdis[i]);
      for (int i = 0; i < this.sBusDirect.length; i++)
        this.VBusDirect.add(this.sBusDirect[i]);
      for (int i = 0; i < this.sBusPeaje.length; i++) {
        this.VBusPeaje.add(this.sBusPeaje[i]);
      }

      this.jtbl_tarjetas_pendientes.setModel(this.defaultmodelo);
      this.jtbl_tarjetas_pendientes.getInputMap(1).put(KeyStroke.getKeyStroke(10, 0), "none");
      this.jtbl_gastos_empresa.setModel(this.gempremodelo);
      this.jtbl_gastos_empresa.setSelectionMode(0);
      this.jtbl_gastos_operador.setModel(this.gopermodelo);
      this.jtbl_gastos_operador.setSelectionMode(0);
      this.jtbl_gastos_operador.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
      resizeColumnasGastos();
      this.tarjetas_recaudadas = new String[10][23];
      this.indiceTarjetas = new Vector();
      limpiarDescripcion();
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Recaudacion | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Cancelar Pago | <font color=FF0000>F12</font> Recoleccion  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");

      this.cargaparametros = new tmscargaparamglobrecauda();
      this.codigos = this.cargaparametros.getCodigos();
      this.valores = this.cargaparametros.getValores();
      this.cargaimpresoras = new tmscargaimpresorasrecauda();

      int index1 = this.codigos.indexOf("P_R_RECNRE");
      if (index1 >= 0)
        this.toperecoleccion = Float.valueOf(this.valores.get(index1).toString()).floatValue();
      int index2 = this.codigos.indexOf("P_R_RECLIM");
      if (index2 >= 0)
        this.montorecoleccion = Float.valueOf(this.valores.get(index2).toString()).floatValue();
      setNombre_equipo(this.cargaimpresoras.getNombre_equipo());
      setTitle("TMSRecauda Rev10.11.14");
      this.formatos = this.cargaimpresoras.getFormatos();
      this.jtxt_claveOperador.setFocusTraversalKeysEnabled(false);
      this.jtxt_autobus.setFocusTraversalKeysEnabled(false);
      this.jlbl_folioPago.setFocusTraversalKeysEnabled(false);
      this.impresoras = this.cargaimpresoras.getImpresoras();
      this.puertos = this.cargaimpresoras.getPuertos();
      this.jpnl_tarjetaDescripcion.setVisible(false);
      this.jpnl_tajetasPendientes.setVisible(false);
      this.jtbl_tarjetas_pendientes.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
      this.jtbl_tarjetas_pendientes.setRowHeight(23);
      this.jtxt_claveOperador.requestFocus();
    }
  }

  private void BuscaCorte() {
    if (!abreSocketAS()) {
      new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
      this.respuestaSN = false;
      return;
    }
    System.out.println("TimeZone(Antes): " + TimeZone.getDefault());
    TimeZone.setDefault(this.busquedas.variosFacadeRemote.getTimeZone());
    System.out.println("TimeZone(Despues): " + TimeZone.getDefault());
    Vector corte = (Vector)this.busquedas.variosFacadeRemote.buscaCortePorUsuario("R_AMPERSA", getIdusuario());
    TmsActividadesSesionTbl actividasInicioSesion = this.busquedas.actividadesSesionTblFacadeRemote.buscarActidadPorClave("INISES");
    System.out.println("la sesion actividad es: " + actividasInicioSesion.getNombreActividad());
    PcInfo pc = new PcInfo();
    Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
    this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
    try {
      this.caja = this.busquedas.cajasTblFacadeRemote.buscarCajaPorEquipo(pc.getHostName().toString().toUpperCase());
    } catch (Exception e) {
      new jdlg_error("¡No esta configurado este equipo como caja! ", "Favor de contactar al administrador del sistema", "Equipo no configurado").setVisible(true);
      System.exit(0);
    }
    if (this.caja == null) {
      new jdlg_error("¡No esta configurado este equipo como caja! ", "Favor de contactar al administrador del sistema", "Equipo no configurado").setVisible(true);
      System.exit(0);
    }

    String ubicacion = this.caja.getUbicacionId().toString().trim();
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
    Vector vedotar = (Vector)this.busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje("RECAUDADA");
    if (vedotar.size() == 0)
    {
      new jdlg_error("¡Los estados de las tarjetas estan mal configurados! ", "Favor de contactar al administrador del sistema", "Error de configuracion").setVisible(true);
      this.respuestaSN = false;
      return;
    } 

    //Vector vedotar2 = (Vector)this.busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje("ABIERTA");
    Vector vedotar2 = (Vector)this.busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje("CONFIRMADA");
    if (vedotar2.size() == 0)
    {
      new jdlg_error("¡Los estados de las tarjetas estan mal configurados! ", "Favor de contactar al administrador del sistema", "Error de configuracion").setVisible(true);
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
      TmsCortesTbl corten = this.busquedas.cortesFacateRemote.insertarCorte(cortenvo, this.idTerminal);
      setCorteid(corten.getCorteId().longValue());
      setCorteActual(corten);
      System.out.println("El id del corte Nuevo es: " + getCorteid());
      TmsSesionActividadesTbl inicioSesion = new TmsSesionActividadesTbl();
      inicioSesion.setCorteId(corten);
      inicioSesion.setCajaId(this.caja);
      inicioSesion.setActividadSesionId(actividasInicioSesion);
      inicioSesion.setValorActividad(this.nombre_recaudador + "-" + this.caja.getNombreEquipo() + "-Recaudacion");
      inicioSesion.setFechaHoraActividad(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setCreadoPor(this.idusuario);
      inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setUltimaActualizacionPor(this.idusuario);
      inicioSesion.setUltimaFechaActualizacion(new Date(this.fecha_servidor.getTime()));
      this.busquedas.sesionActFacateRemote.create(inicioSesion, this.idTerminal);
      System.out.println("Se registro un inicio de sesion en la BD");
    }
    else
    {
      jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Corte pendiente", "No se ha realizado su corte. ¿Desea Asignarse Nuevamente a la Recaudación?");
      psn.setVisible(true);
      if (this.respuestaSN)
      {
        System.out.println("El id del corte pendiente es: " + corte.get(0));
        Vector crt = (Vector)corte.get(0);
        setCorteid(Long.valueOf(crt.get(0).toString()).longValue());
        TmsCortesTbl corteBuscado = this.busquedas.cortesFacateRemote.find(BigDecimal.valueOf(getCorteid()));
        setCorteActual(corteBuscado);
        TmsSesionActividadesTbl inicioSesion = new TmsSesionActividadesTbl();
        inicioSesion.setCorteId(corteBuscado);
        inicioSesion.setCajaId(this.caja);
        inicioSesion.setActividadSesionId(actividasInicioSesion);
        inicioSesion.setValorActividad(this.nombre_recaudador + "-" + this.caja.getNombreEquipo());
        inicioSesion.setFechaHoraActividad(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setCreadoPor(this.idusuario);
        inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
        inicioSesion.setUltimaActualizacionPor(this.idusuario);
        inicioSesion.setUltimaFechaActualizacion(new Date(this.fecha_servidor.getTime()));
        this.busquedas.sesionActFacateRemote.create(inicioSesion, this.idTerminal);
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

  private void salirAplicacion() {
    jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Confirmacion de salida", "¿Seguro que desea salir de Recaudación?");
    psn.setVisible(true);
    if (this.respuestaSN)
    {
      if (!abreSocketAS()) {
        new jdlg_error_conexionBD_NET("En este momento no es posible cerrar la sesion.", "El sistema se cerrará completamente.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
        System.exit(0);
      }
      TmsActividadesSesionTbl actividasInicioSesion = this.busquedas.actividadesSesionTblFacadeRemote.buscarActidadPorClave("FINSES");
      System.out.println("la sesion actividad es: " + actividasInicioSesion.getNombreActividad());
      PcInfo pc = new PcInfo();
      Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
      this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
      TmsSesionActividadesTbl inicioSesion = new TmsSesionActividadesTbl();
      inicioSesion.setCorteId(getCorteActual());
      inicioSesion.setCajaId(this.caja);
      inicioSesion.setActividadSesionId(actividasInicioSesion);
      inicioSesion.setValorActividad(this.nombre_recaudador + "-" + this.caja.getNombreEquipo() + "-Recaudacion");
      inicioSesion.setFechaHoraActividad(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setCreadoPor(this.idusuario);
      inicioSesion.setFechaCreacion(new Date(this.fecha_servidor.getTime()));
      inicioSesion.setUltimaActualizacionPor(this.idusuario);
      inicioSesion.setUltimaFechaActualizacion(new Date(this.fecha_servidor.getTime()));
      this.busquedas.sesionActFacateRemote.create(inicioSesion, this.idTerminal);
      System.out.println("Se registro un fin de sesion de Recaudacion en la BD");

      dispose();
    }
    else {
      return;
    }
  }

  private double Round(double Rval, int Rpl) {
    double p = Math.pow(10.0D, Rpl);
    Rval *= p;
    double tmp = Math.round(Rval);
    return tmp / p;
  }

  public void imprimir_recibo(String[][] datos, String nref, int nt) {
    datos[0][1] = nref;

    int index = this.formatos.indexOf("TICKETS");
    if (index >= 0)
    {
      String puerto = this.puertos.get(index).toString();
      if ((puerto.equals("LPT1")) || (puerto.equals("LPT2")) || (puerto.equals("LPT3")) || (puerto.equals("LPT4")) || (puerto.equals("COM1")) || (puerto.equals("COM2")) || (puerto.equals("ARCHIVO")))
      {
        if (!imprimir_recibo_LPT(datos, puerto, nt))
          new jdlg_error("¡No se pudo imprimir el ticket de pago por error de la impresora!", "", "Error de impresión").setVisible(true);
        if (this.imprimeDiesel)
        {
          if (!imprimir_recibo_diesel_LPT(datos, puerto, nt, "BOMBAS DIESEL"))
            new jdlg_error("¡No se pudo imprimir el ticket de Diesel por error de la impresora!", "", "Error de impresión").setVisible(true);
          if (!imprimir_recibo_diesel_LPT(datos, puerto, nt, "CONTABILIDAD"))
            new jdlg_error("¡No se pudo imprimir el ticket de Diesel por error de la impresora!", "", "Error de impresión").setVisible(true);
        }
      }
      if ((puerto.equals("RED")) || (puerto.equals("USB")))
      {
        int indeximp = this.formatos.indexOf("TICKETS");

        if (!imprimir_recibo_LPT(datos, this.impresoras.get(indeximp).toString(), nt))
          new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresión").setVisible(true);
        if (this.imprimeDiesel)
        {
          if (!imprimir_recibo_diesel_LPT(datos, puerto, nt, "BOMBAS DIESEL"))
            new jdlg_error("¡No se pudo imprimir el ticket de Diesel por error de la impresora!", "", "Error de impresión").setVisible(true);
          if (!imprimir_recibo_diesel_LPT(datos, puerto, nt, "CONTABILIDAD")) {
            new jdlg_error("¡No se pudo imprimir el ticket de Diesel por error de la impresora!", "", "Error de impresión").setVisible(true);
          }
        }

      }

    }
    else
    {
      new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no será impreso", " El numero de referencia del pago es: " + nref, "Impresora no encontrada").setVisible(true);
      return;
    }
    Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante", nref);
    p.setVisible(true);
    if (this.reimpresion) {
      imprimir_recibo(datos, nref, nt);
    }
    else {
      this.jtbl_tarjetas_pendientes.requestFocus();
      if (this.jtbl_tarjetas_pendientes.getRowCount() != 0) {
        this.jtbl_tarjetas_pendientes.setRowSelectionInterval(0, 0);
      }
      else {
        new jdlg_advertencia("Todas las tarjetas se han recaudado", "", "¡No hay mas tarjetas pendientes!").setVisible(true);
        habilitarDatosOperador();
        this.jpnl_tajetasPendientes.setVisible(false);
        this.jpnl_tajetasPendientes2.setVisible(true);
        this.defaultmodelo.setDataVector((Object[][])null, this.encabezadotp);
        this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Recaudacion | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Cancelar Pago | <font color=FF0000>F12</font> Recoleccion  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
        resizeColumnasTP();
        limpiarDescripcion();
        this.jtxt_claveOperador.requestFocus();
      }
    }
  }

  public void imprimir_recibo_rec(String[] datos, String npq)
  {
    int index = this.formatos.indexOf("TICKETS");
    if (index >= 0)
    {
      String puerto = this.puertos.get(index).toString();
      if ((puerto.equals("LPT1")) || (puerto.equals("LPT2")) || (puerto.equals("LPT3")) || (puerto.equals("LPT4")) || (puerto.equals("COM1")) || (puerto.equals("COM2")) || (puerto.equals("ARCHIVO")))
      {
        if (!imprimir_recibo_rec_LPT(datos, puerto))
          new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresión").setVisible(true);
      }
      if ((puerto.equals("RED")) || (puerto.equals("USB")))
      {
        int indeximp = this.formatos.indexOf("TICKETS");

        if (!imprimir_recibo_rec_LPT(datos, this.impresoras.get(indeximp).toString())) {
          new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresión").setVisible(true);
        }

      }

    }
    else
    {
      new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no será impreso", " El numero de paquete de la recoleccion es: " + npq, "Impresora no encontrada").setVisible(true);
      return;
    }
    Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante", npq);
    p.setVisible(true);
    if (this.reimpresion)
      imprimir_recibo_rec(datos, npq);
    else
      verificarAumulado();
  }

  private void recolecciones()
  {
    if (!abreSocketAS()) {
      new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
      return;
    }
    Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
    String estado = vestado.get(0).toString();
    if (estado.equals("CERRADA")) {
      new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
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
    else
    {
      if (!abreSocketAS()) {
        new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
        return;
      }
      if (validarDatosSupervisor("6009"))
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
  }

  private void f10_Enable()
  {
    this.jtxt_claveOperador.registerKeyboardAction(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    , KeyStroke.getKeyStroke(121, 0), 0);
    this.jtxt_claveOperador.registerKeyboardAction(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    , KeyStroke.getKeyStroke(86, 17), 0);
    this.jtxt_autobus.registerKeyboardAction(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    , KeyStroke.getKeyStroke(121, 0), 0);
    this.jtxt_nombreOperador.registerKeyboardAction(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    , KeyStroke.getKeyStroke(121, 0), 0);
    this.jlbl_folioPago.registerKeyboardAction(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    , KeyStroke.getKeyStroke(121, 0), 0);
  }

  private void initComponents()
  {
    this.jpnl_datosOperador = new JPanel();
    this.jLabel1 = new JLabel();
    this.jtxt_claveOperador = new JTextTextField();
    this.jLabel2 = new JLabel();
    this.jtxt_autobus = new JTextTextField();
    this.jLabel4 = new JLabel();
    this.jtxt_nombreOperador = new JTextField();
    this.jpnl_tajetasPendientes = new JPanel();
    this.jScrollPane1 = new JScrollPane();
    this.jtbl_tarjetas_pendientes = new JTable(this.defaultmodelo);
    this.jtbl_tarjetas_pendientes.setSelectionMode(0);
    this.jtbl_tarjetas_pendientes = new JTable();
    this.jpnl_tarjetaDescripcion = new JPanel();
    this.jLabel5 = new JLabel();
    this.jlbl_folioPago = new JLabel();
    this.jlbl_folioPago.setFocusTraversalKeysEnabled(false);
    this.jLabel17 = new JLabel();
    this.jlbl_numeroCorrida = new JLabel();
    this.jLabel23 = new JLabel();
    this.jlbl_kilometros = new JLabel();
    this.jLabel7 = new JLabel();
    this.jlbl_cupo = new JLabel();
    this.jLabel9 = new JLabel();
    this.jlbl_ocupacion = new JLabel();
    this.jLabel11 = new JLabel();
    this.jLabel12 = new JLabel();
    this.jlbl_sueldoOperador = new JLabel();
    this.jLabel14 = new JLabel();
    this.jlbl_retencion = new JLabel();
    this.jLabel16 = new JLabel();
    this.jlbl_anticipos = new JLabel();
    this.jPanel6 = new JPanel();
    this.jPanel4 = new JPanel();
    this.jScrollPane2 = new JScrollPane();
    this.jtbl_gastos_operador = new JTable();
    this.jPanel5 = new JPanel();
    this.jScrollPane3 = new JScrollPane();
    this.jtbl_gastos_empresa = new JTable();
    this.jLabel20 = new JLabel();
    this.jLabel21 = new JLabel();
    this.jLabel22 = new JLabel();
    this.jLabel24 = new JLabel();
    this.jLabel25 = new JLabel();
    this.jlbl_boletosSistema = new JLabel();
    this.jlbl_boletosAbordo = new JLabel();
    this.jlbl_boletosManual = new JLabel();
    this.jlbl_ventaSistema = new JLabel();
    this.jlbl_ventaAbordo = new JLabel();
    this.jlbl_ventaManual = new JLabel();
    this.jlbl_pagoPorOperador = new JLabel();
    this.jlbl_saldoOperador = new JLabel();
    this.jLabel3 = new JLabel();
    this.jlbl_nombreRuta = new JLabel();
    this.jLabel6 = new JLabel();
    this.jLabel8 = new JLabel();
    this.jlbl_total_pagar = new JLabel();
    this.jLabel13 = new JLabel();
    this.jlbl_letrero_saldos = new JLabel();
    this.jlbl_saldo_Anterior = new JLabel();
    this.jlbl_barraEstado = new JLabel();
    this.jpnl_tarjetaDescripcion2 = new JPanel();
    this.jpnl_tajetasPendientes2 = new JPanel();
    this.jLabel10 = new JLabel();

    setDefaultCloseOperation(2);
    setIconifiable(true);
    addComponentListener(new ComponentAdapter() {
      public void componentShown(ComponentEvent evt) {
        JIFRecaudacion.this.formComponentShown(evt);
      }
    });
    this.jpnl_datosOperador.setBorder(BorderFactory.createTitledBorder(null, "Datos Operador", 0, 0, new Font("Tahoma", 1, 12)));
    this.jLabel1.setFont(new Font("Tahoma", 1, 11));
    this.jLabel1.setText("Clave:");

    this.jtxt_claveOperador.setFont(new Font("Tahoma", 1, 12));
    this.jtxt_claveOperador.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent evt) {
        JIFRecaudacion.this.jtxt_claveOperadorFocusGained(evt);
      }
      public void focusLost(FocusEvent evt) {
        JIFRecaudacion.this.jtxt_claveOperadorFocusLost(evt);
      }
    });
    this.jtxt_claveOperador.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFRecaudacion.this.jtxt_claveOperadorKeyPressed(evt);
      }
    });
    this.jLabel2.setFont(new Font("Tahoma", 1, 11));
    this.jLabel2.setText("Autobus:");

    this.jtxt_autobus.setFont(new Font("Tahoma", 1, 12));
    this.jtxt_autobus.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent evt) {
        JIFRecaudacion.this.jtxt_autobusFocusGained(evt);
      }
      public void focusLost(FocusEvent evt) {
        JIFRecaudacion.this.jtxt_autobusFocusLost(evt);
      }
    });
    this.jtxt_autobus.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFRecaudacion.this.jtxt_autobusKeyPressed(evt);
      }
    });
    this.jLabel4.setFont(new Font("Tahoma", 1, 11));
    this.jLabel4.setText("Nombre:");

    this.jtxt_nombreOperador.setEditable(false);
    this.jtxt_nombreOperador.setFont(new Font("Tahoma", 1, 12));
    this.jtxt_nombreOperador.setFocusable(false);

    GroupLayout jpnl_datosOperadorLayout = new GroupLayout(this.jpnl_datosOperador);
    this.jpnl_datosOperador.setLayout(jpnl_datosOperadorLayout);
    jpnl_datosOperadorLayout.setHorizontalGroup(jpnl_datosOperadorLayout.createParallelGroup(1).add(jpnl_datosOperadorLayout.createSequentialGroup().add(19, 19, 19).add(this.jLabel1).addPreferredGap(0).add(this.jtxt_claveOperador, -2, 140, -2).addPreferredGap(0, 66, 32767).add(this.jLabel2).addPreferredGap(0).add(this.jtxt_autobus, -2, 109, -2).add(24, 24, 24).add(this.jLabel4).addPreferredGap(0).add(this.jtxt_nombreOperador, -2, 362, -2).add(79, 79, 79)));

    jpnl_datosOperadorLayout.setVerticalGroup(jpnl_datosOperadorLayout.createParallelGroup(1).add(jpnl_datosOperadorLayout.createSequentialGroup().add(jpnl_datosOperadorLayout.createParallelGroup(3).add(this.jLabel1).add(this.jLabel2).add(this.jtxt_autobus, -2, 19, -2).add(this.jtxt_claveOperador, -2, -1, -2).add(this.jLabel4).add(this.jtxt_nombreOperador, -2, -1, -2)).addContainerGap(-1, 32767)));

    this.jpnl_tajetasPendientes.setBorder(BorderFactory.createTitledBorder(null, "Tarjetas Pendientes", 0, 0, new Font("Tahoma", 1, 12)));
    this.jtbl_tarjetas_pendientes.setFont(new Font("Tahoma", 1, 12));
    this.jtbl_tarjetas_pendientes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));

    this.jtbl_tarjetas_pendientes.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFRecaudacion.this.jtbl_tarjetas_pendientesKeyPressed(evt);
      }
    });
    this.jScrollPane1.setViewportView(this.jtbl_tarjetas_pendientes);

    GroupLayout jpnl_tajetasPendientesLayout = new GroupLayout(this.jpnl_tajetasPendientes);
    this.jpnl_tajetasPendientes.setLayout(jpnl_tajetasPendientesLayout);
    jpnl_tajetasPendientesLayout.setHorizontalGroup(jpnl_tajetasPendientesLayout.createParallelGroup(1).add(this.jScrollPane1, -1, 942, 32767));

    jpnl_tajetasPendientesLayout.setVerticalGroup(jpnl_tajetasPendientesLayout.createParallelGroup(1).add(this.jScrollPane1, -1, 94, 32767));

    this.jpnl_tarjetaDescripcion.setBorder(BorderFactory.createTitledBorder(null, "Descripcion", 0, 0, new Font("Tahoma", 1, 12)));
    this.jLabel5.setFont(new Font("Tahoma", 1, 12));
    this.jLabel5.setText("Folio de Pago:");

    this.jlbl_folioPago.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_folioPago.setText("12345678910111213");
    this.jlbl_folioPago.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFRecaudacion.this.jlbl_folioPagoKeyPressed(evt);
      }
    });
    this.jLabel17.setFont(new Font("Tahoma", 1, 12));
    this.jLabel17.setText("No. Corrida:");

    this.jlbl_numeroCorrida.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_numeroCorrida.setText("1050ECON123456891011");

    this.jLabel23.setFont(new Font("Tahoma", 1, 12));
    this.jLabel23.setText("Kilometros:");

    this.jlbl_kilometros.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_kilometros.setText("12345678");

    this.jLabel7.setFont(new Font("Tahoma", 1, 12));
    this.jLabel7.setText("Cupo:");

    this.jlbl_cupo.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_cupo.setText("1234");

    this.jLabel9.setFont(new Font("Tahoma", 1, 12));
    this.jLabel9.setText("Ocupación:");

    this.jlbl_ocupacion.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_ocupacion.setText("100");

    this.jLabel11.setFont(new Font("Tahoma", 1, 12));
    this.jLabel11.setText("%");

    this.jLabel12.setFont(new Font("Tahoma", 1, 12));
    this.jLabel12.setText("Sueldo Operador:");

    this.jlbl_sueldoOperador.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_sueldoOperador.setHorizontalAlignment(2);
    this.jlbl_sueldoOperador.setText("522");

    this.jLabel14.setFont(new Font("Tahoma", 1, 12));
    this.jLabel14.setText("Retencion:");

    this.jlbl_retencion.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_retencion.setHorizontalAlignment(2);
    this.jlbl_retencion.setText("123456");

    this.jLabel16.setFont(new Font("Tahoma", 1, 12));
    this.jLabel16.setText("Anticipos:");

    this.jlbl_anticipos.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_anticipos.setHorizontalAlignment(2);
    this.jlbl_anticipos.setText("1500");

    this.jPanel6.setBorder(BorderFactory.createTitledBorder(null, "GASTOS", 2, 0, new Font("Tahoma", 1, 11)));
    this.jPanel4.setBorder(BorderFactory.createTitledBorder(null, "Operador", 0, 0, new Font("Tahoma", 1, 11)));
    this.jtbl_gastos_operador.setBackground(new Color(238, 238, 239));
    this.jtbl_gastos_operador.setFont(new Font("Tahoma", 0, 10));
    this.jtbl_gastos_operador.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, { null, null } }, new String[] { "Concepto", "Valor" }));

    this.jtbl_gastos_operador.setEnabled(false);
    this.jtbl_gastos_operador.setFocusable(false);
    this.jtbl_gastos_operador.setGridColor(new Color(238, 238, 238));
    this.jScrollPane2.setViewportView(this.jtbl_gastos_operador);

    GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
    this.jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(1).add(jPanel4Layout.createSequentialGroup().add(this.jScrollPane2, -1, 266, 32767).addContainerGap()));

    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(1).add(this.jScrollPane2, -1, 163, 32767));

    this.jPanel5.setBorder(BorderFactory.createTitledBorder(null, "Empresa", 3, 0, new Font("Tahoma", 1, 11)));
    this.jtbl_gastos_empresa.setBackground(new Color(238, 238, 238));
    this.jtbl_gastos_empresa.setFont(new Font("Tahoma", 0, 10));
    this.jtbl_gastos_empresa.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, { null, null } }, new String[] { "Concepto", "Valor" }));

    this.jtbl_gastos_empresa.setEnabled(false);
    this.jtbl_gastos_empresa.setFocusable(false);
    this.jtbl_gastos_empresa.setGridColor(new Color(238, 238, 238));
    this.jScrollPane3.setViewportView(this.jtbl_gastos_empresa);

    GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
    this.jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(1).add(jPanel5Layout.createSequentialGroup().addContainerGap().add(this.jScrollPane3, -1, 259, 32767)));

    jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(1).add(this.jScrollPane3, -1, 163, 32767));

    GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
    this.jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(1).add(jPanel6Layout.createSequentialGroup().add(this.jPanel4, -2, -1, -2).addPreferredGap(0).add(this.jPanel5, -1, -1, 32767)));

    jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(1).add(this.jPanel4, -1, -1, 32767).add(this.jPanel5, -1, -1, 32767));

    this.jLabel20.setFont(new Font("Tahoma", 1, 12));
    this.jLabel20.setText("Boletos");

    this.jLabel21.setFont(new Font("Tahoma", 1, 12));
    this.jLabel21.setText("Venta");

    this.jLabel22.setFont(new Font("Tahoma", 1, 12));
    this.jLabel22.setText("Venta Sistema:");

    this.jLabel24.setFont(new Font("Tahoma", 1, 12));
    this.jLabel24.setText("Venta Abordo:");

    this.jLabel25.setFont(new Font("Tahoma", 1, 12));
    this.jLabel25.setText("Venta Manual:");

    this.jlbl_boletosSistema.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_boletosSistema.setText("1000");

    this.jlbl_boletosAbordo.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_boletosAbordo.setText("1000");

    this.jlbl_boletosManual.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_boletosManual.setText("1000");

    this.jlbl_ventaSistema.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_ventaSistema.setHorizontalAlignment(4);
    this.jlbl_ventaSistema.setText("120");

    this.jlbl_ventaAbordo.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_ventaAbordo.setHorizontalAlignment(4);
    this.jlbl_ventaAbordo.setText("120");

    this.jlbl_ventaManual.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_ventaManual.setHorizontalAlignment(4);
    this.jlbl_ventaManual.setText("150");

    this.jlbl_pagoPorOperador.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_pagoPorOperador.setHorizontalAlignment(4);
    this.jlbl_pagoPorOperador.setText("15080.0");

    this.jlbl_saldoOperador.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_saldoOperador.setHorizontalAlignment(4);
    this.jlbl_saldoOperador.setText("1234567");

    this.jLabel3.setFont(new Font("Tahoma", 1, 12));
    this.jLabel3.setText("Ruta:");

    this.jlbl_nombreRuta.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_nombreRuta.setText("CAPU-RIOFRIO FEDERAL");

    this.jLabel6.setFont(new Font("Tahoma", 1, 12));
    this.jLabel6.setText("Total a Pagar por Operador:");

    this.jLabel8.setFont(new Font("Tahoma", 1, 12));
    this.jLabel8.setText("Pago por Operador:");

    this.jlbl_total_pagar.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_total_pagar.setHorizontalAlignment(4);
    this.jlbl_total_pagar.setText("000000");

    this.jLabel13.setFont(new Font("Tahoma", 1, 12));
    this.jLabel13.setText("Saldo Operador:");

    this.jlbl_letrero_saldos.setFont(new Font("Tahoma", 1, 12));
    this.jlbl_letrero_saldos.setText("Saldo Anterior:");

    this.jlbl_saldo_Anterior.setFont(new Font("Tahoma", 0, 12));
    this.jlbl_saldo_Anterior.setText("-3000");

    GroupLayout jpnl_tarjetaDescripcionLayout = new GroupLayout(this.jpnl_tarjetaDescripcion);
    this.jpnl_tarjetaDescripcion.setLayout(jpnl_tarjetaDescripcionLayout);
    jpnl_tarjetaDescripcionLayout.setHorizontalGroup(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(28, 28, 28).add(this.jPanel6, -2, -1, -2).add(42, 42, 42).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(2, this.jLabel6).add(2, this.jLabel13).add(2, this.jLabel8)).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(2).add(this.jlbl_pagoPorOperador, -2, 68, -2).add(this.jlbl_saldoOperador, -2, 76, -2).add(this.jlbl_total_pagar, -2, 61, -2))).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().addContainerGap().add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(this.jLabel22).add(this.jLabel24).add(this.jLabel25).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(jpnl_tarjetaDescripcionLayout.createParallelGroup(2).add(this.jLabel5).add(this.jLabel12)).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(this.jlbl_sueldoOperador, -2, 60, -2).add(this.jlbl_folioPago, -2, 115, -2).add(this.jLabel20).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(10, 10, 10).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(this.jlbl_boletosAbordo).add(this.jlbl_boletosSistema).add(this.jlbl_boletosManual)))))).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1, false).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(jpnl_tarjetaDescripcionLayout.createParallelGroup(2).add(this.jLabel14).add(this.jLabel17)).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(this.jlbl_retencion, -2, 53, -2).add(this.jlbl_numeroCorrida, -2, 170, -2)).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(2).add(this.jLabel23).add(this.jLabel16))).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(jpnl_tarjetaDescripcionLayout.createParallelGroup(2, false).add(1, this.jlbl_ventaManual, -1, -1, 32767).add(1, this.jlbl_ventaAbordo, -1, -1, 32767).add(1, this.jlbl_ventaSistema, -1, -1, 32767).add(1, this.jLabel21)).addPreferredGap(0, -1, 32767).add(this.jlbl_letrero_saldos))).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1, false).add(this.jlbl_saldo_Anterior, -1, -1, 32767).add(this.jlbl_kilometros, -1, 73, 32767).add(this.jlbl_anticipos, -2, 45, -2)).add(17, 17, 17).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(this.jLabel3).addPreferredGap(0).add(this.jlbl_nombreRuta, -1, 229, 32767)).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(this.jLabel7).addPreferredGap(0).add(this.jlbl_cupo, -2, 28, -2).add(23, 23, 23).add(this.jLabel9).addPreferredGap(0).add(this.jlbl_ocupacion).addPreferredGap(0).add(this.jLabel11))))).addContainerGap()));

    jpnl_tarjetaDescripcionLayout.setVerticalGroup(jpnl_tarjetaDescripcionLayout.createParallelGroup(1).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(12, 12, 12).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3, false).add(this.jLabel17).add(this.jLabel23).add(this.jLabel3).add(this.jlbl_nombreRuta).add(this.jlbl_numeroCorrida).add(this.jlbl_kilometros).add(this.jlbl_folioPago).add(this.jLabel5)).add(14, 14, 14).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel12).add(this.jLabel7).add(this.jlbl_cupo).add(this.jLabel9).add(this.jlbl_ocupacion).add(this.jlbl_retencion).add(this.jLabel11).add(this.jlbl_anticipos).add(this.jLabel14).add(this.jLabel16).add(this.jlbl_sueldoOperador)).add(15, 15, 15).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel20).add(this.jLabel21).add(this.jlbl_letrero_saldos).add(this.jlbl_saldo_Anterior)).add(7, 7, 7).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel22).add(this.jlbl_boletosSistema).add(this.jlbl_ventaSistema)).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel24).add(this.jlbl_boletosAbordo).add(this.jlbl_ventaAbordo)).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel25).add(this.jlbl_boletosManual).add(this.jlbl_ventaManual)).addPreferredGap(0).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(2).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel8).add(this.jlbl_pagoPorOperador)).add(13, 13, 13).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel13).add(this.jlbl_saldoOperador)).add(33, 33, 33).add(jpnl_tarjetaDescripcionLayout.createParallelGroup(3).add(this.jLabel6).add(this.jlbl_total_pagar)).add(71, 71, 71)).add(jpnl_tarjetaDescripcionLayout.createSequentialGroup().add(this.jPanel6, -1, -1, 32767).addContainerGap()))));

    this.jlbl_barraEstado.setFont(new Font("Tahoma", 1, 11));
    this.jlbl_barraEstado.setText("  ESC: Termina     ENTER: Selecciona     F4: Venta Abordo     F5: Venta Manual     F10: Pagar");
    this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

    GroupLayout jpnl_tarjetaDescripcion2Layout = new GroupLayout(this.jpnl_tarjetaDescripcion2);
    this.jpnl_tarjetaDescripcion2.setLayout(jpnl_tarjetaDescripcion2Layout);
    jpnl_tarjetaDescripcion2Layout.setHorizontalGroup(jpnl_tarjetaDescripcion2Layout.createParallelGroup(1).add(0, 958, 32767));

    jpnl_tarjetaDescripcion2Layout.setVerticalGroup(jpnl_tarjetaDescripcion2Layout.createParallelGroup(1).add(0, 124, 32767));

    GroupLayout jpnl_tajetasPendientes2Layout = new GroupLayout(this.jpnl_tajetasPendientes2);
    this.jpnl_tajetasPendientes2.setLayout(jpnl_tajetasPendientes2Layout);
    jpnl_tajetasPendientes2Layout.setHorizontalGroup(jpnl_tajetasPendientes2Layout.createParallelGroup(1).add(0, 958, 32767));

    jpnl_tajetasPendientes2Layout.setVerticalGroup(jpnl_tajetasPendientes2Layout.createParallelGroup(1).add(0, 78, 32767));

    this.jLabel10.setFont(new Font("Tahoma", 1, 12));
    this.jLabel10.setText("Rev10.11.2014");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(2, layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2).add(1, this.jpnl_tajetasPendientes2, -1, -1, 32767).add(1, this.jpnl_datosOperador, -1, -1, 32767).add(1, this.jpnl_tajetasPendientes, -1, -1, 32767).add(1, this.jpnl_tarjetaDescripcion2, -1, -1, 32767).add(1, this.jpnl_tarjetaDescripcion, -1, -1, 32767)).addContainerGap()).add(this.jlbl_barraEstado, -1, 978, 32767).add(2, layout.createSequentialGroup().addContainerGap(873, 32767).add(this.jLabel10).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jLabel10).add(7, 7, 7).add(this.jpnl_datosOperador, -2, -1, -2).addPreferredGap(0).add(this.jpnl_tajetasPendientes, -1, -1, 32767).addPreferredGap(0).add(this.jpnl_tajetasPendientes2, -1, -1, 32767).addPreferredGap(0).add(this.jpnl_tarjetaDescripcion2, -1, -1, 32767).addPreferredGap(0).add(this.jpnl_tarjetaDescripcion, -1, -1, 32767).addPreferredGap(0).add(this.jlbl_barraEstado, -2, 44, -2)));

    pack();
  }

  private void jtxt_claveOperadorFocusLost(FocusEvent evt) {
    this.jtxt_claveOperador.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
  }

  private void jtxt_autobusFocusLost(FocusEvent evt) {
    this.jtxt_autobus.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
  }

  private void jtxt_autobusFocusGained(FocusEvent evt) {
    this.jtxt_autobus.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 88, 236)));
  }

  private void jtxt_claveOperadorFocusGained(FocusEvent evt) {
    this.jtxt_claveOperador.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 88, 236)));
  }

  private void formComponentShown(ComponentEvent evt) {
    this.jtxt_claveOperador.requestFocusInWindow();
  }

  private void jlbl_folioPagoKeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 27)
    {
      limpiarDescripcion();
      this.jpnl_tarjetaDescripcion.setVisible(false);
      this.jpnl_tarjetaDescripcion2.setVisible(true);
      this.jtbl_tarjetas_pendientes.setEnabled(true);
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>ENTER</font> Selecciona Tarjeta(s)</html>");
      this.jtbl_tarjetas_pendientes.setRowSelectionInterval(0, 0);
      this.jtbl_tarjetas_pendientes.setColumnSelectionInterval(0, 0);
      this.jtbl_tarjetas_pendientes.requestFocus();
    }

    if (evt.getKeyCode() == 114)
    {
      if ((this.gastosnocom) && (!this.gastosComprobados))
      {
        double saldo = Double.valueOf(this.jlbl_saldo_Anterior.getText()).doubleValue();
        double ant = Double.valueOf(this.jlbl_anticipos.getText()).doubleValue();
        double totAnt = 0.0D;
        double sobranteSaldo = 0.0D;
        if (saldo <= 0.0D) {
          totAnt = ant + saldo * -1.0D;
        }
        else {
          if (saldo >= ant)
          {
            totAnt = 0.0D;
            sobranteSaldo = saldo - ant;
          }

          if (ant > saldo)
          {
            sobranteSaldo = 0.0D;
            totAnt = ant - saldo;
          }
        }
        new jdlg_ComprobarGatosDentro(this.vgastos, this.vgastosId, this.vgastosCarga, Double.valueOf(totAnt), Double.valueOf(sobranteSaldo)).setVisible(true);
      }

    }

    if (evt.getKeyCode() == 119)
    {
      new jdlg_ventas0("Venta Manual", "MANUAL").setVisible(true);
    }

    if (evt.getKeyCode() == 121)
    {
      if (!abreSocketAS()) {
        new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
        return;
      }
      Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
      String estado = vestado.get(0).toString();
      if (estado.equals("CERRADA")) {
        new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
        System.exit(0);
      }
      if (!this.encuentraTarifa) {
        new jdlg_error("!El sistema no tiene configurado un pago o cobro para esta tarjeta de viaje!", "Favor de contactar al administardor del sistema", "¡Imposible recaudar!").setVisible(true);
        return;
      }
      actualizaAcumulado();
      verificarAumulado();
      if (!this.noRecauda) {
        return;
      }
      double vtaSis = Double.valueOf(this.jlbl_ventaSistema.getText()).doubleValue();
      double vtaAbo = Double.valueOf(this.jlbl_ventaAbordo.getText()).doubleValue();
      if ((this.buscavtasistema) && (vtaSis == 0.0D))
      {
        int indexfun = this.funciones.indexOf("6017");
        if (indexfun == -1)
        {
          new jdlg_advertencia("¡Las tarjetas con venta por sistema no pueden tener venta en cero!", "La recaudacion debe ser autorizada por un Supervisor", "Tarjeta con Venta por Sistema").setVisible(true);
          if (!validarDatosSupervisor("6017")) {
            return;
          }
        }
      }
      if ((this.buscavtabordo) && (vtaAbo == 0.0D))
      {
        int nbva = Integer.valueOf(this.jlbl_boletosAbordo.getText()).intValue();
        double mva = Double.valueOf(this.jlbl_ventaAbordo.getText()).doubleValue();
        new jdlg_advertencia("¡Las Tarjetas con Venta Abordo no pueden tener venta en cero!", "La Recaudacion debe ser autorizada por un Supervisor", "Tarjeta con Venta Abordo").setVisible(true);
        if ((nbva == 0) && (mva == 0.0D))
        {
          int indexfun = this.funciones.indexOf("6016");
          if (indexfun == -1)
          {
            if (!abreSocketAS()) {
              new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
              return;
            }
            if (validarDatosSupervisor("6016"))
              new jdlg_ventas0("Venta Abodo", "ABORDO").setVisible(true);
            else {
              return;
            }
          }
        }
      }

      Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
      this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
      String[][] data = new String[20][15];
      for (int i = 1; i <= this.ntarjetas_recaudadas; i++)
      {
        data[0][i] = this.tarjetas_recaudadas[i][0];
        data[1][i] = this.tarjetas_recaudadas[i][4];
        data[2][i] = this.tarjetas_recaudadas[i][2];
        data[3][i] = this.tarjetas_recaudadas[i][3];
        data[4][i] = this.tarjetas_recaudadas[i][5];
        data[5][i] = this.tarjetas_recaudadas[i][6];
        data[6][i] = this.tarjetas_recaudadas[i][7];
        data[7][i] = this.tarjetas_recaudadas[i][8];
        data[8][i] = this.tarjetas_recaudadas[i][9];
        System.out.println("tarjetas_recaudadas[i][8]= " + this.tarjetas_recaudadas[i][8]);
        System.out.println("tarjetas_recaudadas[i][9]= " + this.tarjetas_recaudadas[i][9]);
        try {
          data[9][i] = ("" + roundNum(Double.valueOf(new StringBuilder().append("").append(Float.valueOf(this.tarjetas_recaudadas[i][8]).floatValue() - Float.valueOf(this.tarjetas_recaudadas[i][9]).floatValue()).toString()).doubleValue(), 100));
        } catch (NumberFormatException ex) {
          ex.printStackTrace();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        data[10][i] = this.nombre_recaudador;
        data[11][i] = this.tarjetas_recaudadas[i][15];
        data[12][i] = this.tarjetas_recaudadas[i][14];
        data[13][i] = this.tarjetas_recaudadas[i][13];

        int indice = this.indices_ventas.indexOf(this.tarjetas_recaudadas[i][0]);
        if (indice < 0)
        {
          data[14][i] = "0";
          data[15][i] = "0.0";
        }
        else
        {
          data[14][i] = this.vtas[0][indice];
          data[15][i] = this.vtas[1][indice];
        }

      }

      vista_pago_impresion v = new vista_pago_impresion(data, this.ntarjetas_recaudadas);
      v.setVisible(true);
    }
  }

  private void jtbl_tarjetas_pendientesKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      if (!abreSocketAS()) {
        new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
        return;
      }
      this.gastosComprobados = false;
      int nselecciones = this.jtbl_tarjetas_pendientes.getSelectedRowCount();
      int[] selecciones = this.jtbl_tarjetas_pendientes.getSelectedRows();
      boolean igual = true;
      String vserv = "";
      String nserv = "";
      String nruta = "";
      this.imprimeDiesel = false;
      this.montoDiesel = 0.0F;
      this.cantidadViaticos = 0.0F;
      this.otraEmpresa = false;
      this.encuentraTarifa = false;
      for (int i = 0; i < nselecciones; i++)
      {
        vserv = this.jtbl_tarjetas_pendientes.getValueAt(selecciones[i], 1).toString();
        Vector tarjetav = (Vector)this.vtajetasPendientes.get(selecciones[i]);
        nserv = tarjetav.get(11).toString();
        nruta = tarjetav.get(13).toString();
        for (int j = 0; j < nselecciones; j++)
        {
          if (i == j)
            continue;
          if (vserv.equals(this.jtbl_tarjetas_pendientes.getValueAt(selecciones[j], 1).toString()))
          {
            System.out.println("Tarjeta seleccionada: " + tarjetav);
          }
          else
          {
            igual = false;
            break;
          }

        }

      }

      if (igual)
      {
        int nummax = 3;
        int indexst = this.codigos.indexOf("P_R_MAXTAR-S" + nserv);
        System.out.println("Busca : P_R_MAXTAR-S" + nserv);
        if (indexst >= 0) {
          nummax = Integer.parseInt(this.valores.get(indexst).toString());
        }
        else {
          int indexrt = this.codigos.indexOf("P_R_MAXTAR-R" + nruta);
          System.out.println("Busca P_R_MAXTAR-R" + nruta);
          if (indexrt >= 0)
            nummax = Integer.parseInt(this.valores.get(indexrt).toString());
          else {
            new jdlg_error("¡El parametro P_R_MAXTAR esta mal configurado  ", " Favor de contactar al Administrador del sistema!", "Error de configuracioon de Parametro").setVisible(true);
          }
        }
        if (this.jtbl_tarjetas_pendientes.getSelectedRowCount() > nummax) {
          new jdlg_error("¡No puedes recaudar mas de " + nummax + " tarjetas al mismo tiempo! ", "", "Demasiadas tarjetas seleccionadas").setVisible(true);
        }
        else {
          this.buscavtabordo = false;
          this.buscavtasistema = false;
          System.out.println("Bsuca Venta Abordo: P_R_VTABORDO-S" + nserv);
          int indicebvb = this.codigos.indexOf("P_R_VTABORDO-S" + nserv);
          if (indicebvb >= 0)
          {
            if (this.valores.get(indicebvb).toString().equals("S"))
            {
              this.buscavtabordo = true;
              if (this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1)
              {
                new jdlg_advertencia("¡El pago de la tarjeta de los servicios con Venta Abordo se recaudan una por una!  ", "", "Recaudacion por tarjeta").setVisible(true);
                return;
              }
            }
            Vector vestadoTarjetaVixer = (Vector)this.busquedas.variosFacadeRemote.getEstadoEstadoTarjetaViaxer(this.jtbl_tarjetas_pendientes.getValueAt(this.jtbl_tarjetas_pendientes.getSelectedRow(), 0).toString());
            String estado = vestadoTarjetaVixer.get(0).toString();
            String mensaje = vestadoTarjetaVixer.get(1).toString();
            if (estado.equals("CERRADO"))
            {
                Vector vVtaAbordo = (Vector)this.busquedas.variosFacadeRemote.buscarVentaAbordo(this.jtbl_tarjetas_pendientes.getValueAt(this.jtbl_tarjetas_pendientes.getSelectedRow(), 0).toString());
                Vector VtaAbordo = (Vector)vVtaAbordo.get(0);
                this.jlbl_boletosAbordo.setText(VtaAbordo.get(0).toString());
                this.jlbl_ventaAbordo.setText(VtaAbordo.get(1).toString());
            }
            else
            {
                new jdlg_advertencia(mensaje, "", "Venta Abordo").setVisible(true);
                this.jlbl_boletosAbordo.setText("0");
                this.jlbl_ventaAbordo.setText("0.0");
            }

          }
          int indicebvs = this.codigos.indexOf("P_R_VTASIS-S" + nserv);
          if (indicebvs >= 0)
          {
            if (this.valores.get(indicebvs).toString().equals("S")) {
              this.buscavtasistema = true;
            }
          }
          this.indices_ventas = new Vector();
          int[] selecccionadas = this.jtbl_tarjetas_pendientes.getSelectedRows();
          for (int ns = 0; ns < selecccionadas.length; ns++)
            this.indices_ventas.add(this.jtbl_tarjetas_pendientes.getValueAt(selecccionadas[ns], 0).toString());
          for (int i = 0; i <= 4; i++) { this.vtas[0][i] = "0.0"; this.vtas[1][i] = "0"; this.vtas[2][i] = "0.0"; this.vtas[3][i] = "0";
          }
          float montodebe = 0.0F;
          this.gastosnocom = false;
          this.vgastos_tarjetas = new Vector();
          this.vgastos_tarjetas.add(null);
          this.vgastos_tarjetasEmpre = new Vector();
          this.vgastos_tarjetasEmpre.add(null);
          this.ntarjetas_recaudadas = 0;
          this.tarjetas_recaudadas = new String[10][23];
          this.indiceTarjetas = new Vector();
          this.indiceTarjetas.add("inicial");
          Vector gb = new Vector(20);
          System.out.println("Se resetea el vector gb...");
          for (int nt = 0; nt < nselecciones; nt++)
          {
            this.vgastos_una_tarjeta = new Vector();
            this.vgastos_una_tarjetaEmpre = new Vector();
            this.vgastos = new Vector();
            this.vgastosId = new Vector();
            this.vgastosCarga = new Vector();
            int ngastos = 0;
            int ngastosEmpre = 0;
            String[][] camposGastos = new String[20][4];
            String[][] camposGastosEmpre = new String[20][4];
            this.ntarjetas_recaudadas += 1;
            float valor = 0.0F;
            float sueldoOp = 0.0F;
            this.hoteles = "0.0";
            this.lavadas = "0.0";
            this.alimentos = "0.0";
            Vector tarjeta = (Vector)this.vtajetasPendientes.get(selecciones[nt]);
            String autobusExcp = tarjeta.get(4).toString();
            this.indiceTarjetas.add(tarjeta.get(0).toString());
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][0] = this.jlbl_folioPago.getText();
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][1] = tarjeta.get(16).toString();
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][2] = tarjeta.get(0).toString();
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][3] = tarjeta.get(1).toString();
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][4] = tarjeta.get(4).toString();
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][5] = this.jtbl_tarjetas_pendientes.getValueAt(selecciones[nt], 5).toString();
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][6] = this.jtbl_tarjetas_pendientes.getValueAt(selecciones[nt], 6).toString();
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][7] = (this.jtxt_claveOperador.getText() + "-" + this.jtxt_nombreOperador.getText());
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][8] = "0.0";
            this.tarjetas_recaudadas[this.ntarjetas_recaudadas][9] = "0.0";
            if (this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1)
              this.jlbl_numeroCorrida.setText("Varias Tarjetas");
            else
              this.jlbl_numeroCorrida.setText(tarjeta.get(9).toString());
            if (this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1)
              this.jlbl_cupo.setText("N/A");
            else
              this.jlbl_cupo.setText(tarjeta.get(10).toString());
            TmsRutasTbl laruta = this.busquedas.rutasTblFacadeRemote.find(tarjeta.get(13));
            if (laruta != null)
            {
              if (this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1)
                this.jlbl_nombreRuta.setText("Varias Rutas");
              else
                this.jlbl_nombreRuta.setText(laruta.getRutaNumero() + "-" + laruta.getRutaNombre());
              float nk = Float.valueOf(this.jlbl_kilometros.getText()).floatValue();
              nk += laruta.getDistanciaRecorrido().floatValue();
              this.jlbl_kilometros.setText("" + nk);
            }
            else
            {
              new jdlg_advertencia("¡La ruta de la tarjeta " + tarjeta.get(0).toString() + " no esta dada de alta en el sistema  ", " Favor de reportarlo al administrador del sistema!", " Ruta mal configurada").setVisible(true);
              return;
            }

            System.out.println("(P_R_PGOTAR) Busca el monto del pago con los datos: Servicio: " + tarjeta.get(11) + "  Ruta: " + tarjeta.get(13) + "  Empresa: " + tarjeta.get(12));

            float msop = Float.valueOf(this.jlbl_sueldoOperador.getText()).floatValue();
            System.out.println("el valor de msop es:" + msop);

            if (tarjeta.get(13).toString().equals("41"))
            {
              Float dsop = Float.valueOf(0.0F);
              if (tarjeta.get(15) != null)
              {
                StringTokenizer tk = new StringTokenizer(tarjeta.get(15).toString(), ",");
                this.encuentraTarifa = true;
                dsop = Float.valueOf(tk.nextToken());
                this.hoteles = tk.nextToken();
                this.lavadas = tk.nextToken();
                if (tk.hasMoreTokens())
                  this.alimentos = tk.nextToken();
              }
              else
              {
                if (tarjeta.get(18) != null)
                  dsop = Float.valueOf(tarjeta.get(18).toString());
                this.encuentraTarifa = true;
              }

              msop += dsop.floatValue();
              sueldoOp = dsop.floatValue();
              this.jlbl_sueldoOperador.setText("" + msop);
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][8] = ("" + dsop);
            }
            else
            {

              if (((this.VBusDirect.indexOf(tarjeta.get(4).toString()) >= 0) && (tarjeta.get(1).toString().equals("DIRECTO TTP"))) || ((this.VBusDirect.indexOf(tarjeta.get(4).toString()) >= 0) && (tarjeta.get(1).toString().equals("DIRECTO ECONOMICO"))))
              {
                msop += Float.valueOf("30").floatValue();
                sueldoOp = Float.valueOf("30").floatValue();
                System.out.println("el valor de la corrida con autobus " + tarjeta.get(4).toString() + " y servicio " + tarjeta.get(1).toString() + " es: 30 pesos");
                this.encuentraTarifa = true;
              }
              else
              {
                Timestamp tdt = null;
                tdt = Timestamp.valueOf(tarjeta.get(5).toString());
                long timeTarjeta = tdt.getTime();

                String fechaHoraTarjeta = this.ffechaHora.format(Long.valueOf(tdt.getTime()));
                String diaSemanaTarjeta = this.fdia.format(Long.valueOf(tdt.getTime()));
                System.out.println("Fecha de la Tarjeta: " + fechaHoraTarjeta);
                System.out.println("Dia de la Tarjeta: " + diaSemanaTarjeta); 
                float tarifa = this.busquedas.variosFacadeRemote.buscaTarifaTarjeta("P_R_PGOTAR", tarjeta.get(11).toString(), tarjeta.get(13).toString(), tarjeta.get(12).toString(), fechaHoraTarjeta, diaSemanaTarjeta);
                System.out.println("Tarifa encontrada: " + tarifa);

                System.out.println("P_R_PCTDESCOP-R" + tarjeta.get(13) + " =" + this.codigos.indexOf(new StringBuilder().append("P_R_PCTDESCOP-R").append(tarjeta.get(13)).toString()));
                System.out.println("P_R_FECHOPALTA-R" + tarjeta.get(13) + " =" + this.codigos.indexOf(new StringBuilder().append("P_R_FECHOPALTA-R").append(tarjeta.get(13)).toString()));
                int indiced = this.codigos.indexOf("P_R_PCTDESCOP-R" + tarjeta.get(13));
                int indicef = this.codigos.indexOf("P_R_FECHOPALTA-R" + tarjeta.get(13));
                if ((indiced >= 0) && (indicef >= 0))
                {
                  if (this.valores.get(indicef).toString().length() < 10) {
                    new jdlg_advertencia("¡El parametro P_R_PCTDESCOP-R" + tarjeta.get(13) + " esta mal configurado!  ", " Contacte al administrador del sistema", "Error de configuracion de parametro por Ruta").setVisible(true);
                  }
                  String[] str = this.valores.get(indicef).toString().split("/");
                  Timestamp fl = null;
                  fl = Timestamp.valueOf(str[2] + "-" + str[1] + "-" + str[0] + " 00:00:00");
                  System.out.println("fecha de la corrida: " + timeTarjeta);
                  System.out.println("fecha Alta de operador: " + this.fechaAltamil);
                  System.out.println("fecha limite de la rura(" + tarjeta.get(13) + "): " + fl);
                  System.out.println("fecha limite de la rura(" + tarjeta.get(13) + "): " + fl.getTime());
                  if ((this.fechaAltamil >= fl.getTime()) && (timeTarjeta >= fl.getTime()))
                  {
                    float pct = Float.valueOf(this.valores.get(indiced).toString()).floatValue();
                    pct /= 100.0F;
                    tarifa -= tarifa * pct;
                  }
                }

                System.out.println("Tarifa encontrada menos descuento de fecha Alta: " + tarifa);
                if ((tarifa > 0.0F) || (tarjeta.get(13).toString().equals("167")) || (tarjeta.get(13).toString().equals("168"))) {
                  this.encuentraTarifa = true;
                }

                msop += tarifa;

                sueldoOp = tarifa;
              }
              this.jlbl_sueldoOperador.setText("" + msop);
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][8] = ("" + sueldoOp);
            }

            String pretencion = "0";
            int indice = this.codigos.indexOf("P_R_PORRET" + tarjeta.get(12));
            if (indice < 0)
            {
              new jdlg_advertencia("¡El porcentaje de retencion esta mal configurado!  ", " Contacte al administrador del sistema", "Error de configuracion de parametro por Empresa").setVisible(true);
              return;
            }

            pretencion = this.valores.get(indice).toString();
            try {
              if ((this.aplicaretencion.equals("S")) || (this.aplicaretencion.equals("s")))
              {
                double cantidad = Double.valueOf("" + sueldoOp).doubleValue();
                double retencion = roundNum(cantidad * Double.valueOf("." + pretencion).doubleValue(), 100);
                double total = cantidad - retencion;
                double mrop = Double.valueOf(this.jlbl_retencion.getText()).doubleValue();
                mrop += retencion;
                this.jlbl_retencion.setText("" + mrop);
                this.tarjetas_recaudadas[this.ntarjetas_recaudadas][9] = ("" + retencion);
              }
              else
              {
                this.jlbl_retencion.setText("0");
                this.tarjetas_recaudadas[this.ntarjetas_recaudadas][9] = "0";
              }
            } catch (Exception e1) {
              e1.printStackTrace();
            }
            int indicesaldos = this.codigos.indexOf("P_R_BUSALACUM-S" + nserv);
            if (indicesaldos >= 0)
            {
              Vector vsaldos = this.busquedas.variosFacadeRemote.buscaSaldoOperador(this.jtxt_claveOperador.getText());
              if (vsaldos.size() == 0) {
                this.jlbl_saldo_Anterior.setText("0");
              }
              else {
                Vector saldo = (Vector)vsaldos.get(0);
                this.jlbl_saldo_Anterior.setText(saldo.get(0).toString());
              }
              this.jlbl_saldo_Anterior.setVisible(true);
              this.jlbl_letrero_saldos.setVisible(true);
            }
            else
            {
              this.jlbl_saldo_Anterior.setText("0");
              this.jlbl_saldo_Anterior.setVisible(false);
              this.jlbl_letrero_saldos.setVisible(false);
            }

            float bolvendidos = 0.0F;

            Vector vimpfun = (Vector)this.busquedas.variosFacadeRemote.consultaImportePorCorrida(Long.valueOf(tarjeta.get(17).toString()).longValue());
            Vector vocupfun = (Vector)this.busquedas.variosFacadeRemote.consultaOcupacionPorCorrida(Long.valueOf(tarjeta.get(17).toString()).longValue());
            if (vimpfun.size() > 0) {
              double montobol = 0.0D;

              montobol = Double.valueOf(vimpfun.get(0).toString()).doubleValue();

              int nbs = Integer.parseInt(this.jlbl_boletosSistema.getText());

              nbs += Integer.parseInt(vocupfun.get(0).toString());
              this.jlbl_boletosSistema.setText("" + nbs);
              double mvs = Double.valueOf(this.jlbl_ventaSistema.getText()).doubleValue();
              mvs += montobol;
              this.jlbl_ventaSistema.setText("" + mvs);
              bolvendidos = Float.valueOf(vocupfun.get(0).toString()).floatValue();
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][10] = ("" + Integer.parseInt(vocupfun.get(0).toString()));
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][11] = ("" + montobol);
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][12] = "0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][13] = "0.0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][14] = "0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][15] = "0.0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][17] = "0.0";
            }
            else
            {
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][10] = "0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][11] = "0.0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][12] = "0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][13] = "0.0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][21] = "0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][22] = "0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][14] = "0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][15] = "0.0";
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][17] = "0.0";
            }
            if (tarjeta.get(14) != null)
            {
              double mat = Double.valueOf(this.jlbl_anticipos.getText()).doubleValue();
              mat += Double.valueOf(tarjeta.get(14).toString()).doubleValue();
              this.jlbl_anticipos.setText("" + mat);
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][16] = tarjeta.get(14).toString();

              double saldo = Double.valueOf(this.jlbl_saldo_Anterior.getText()).doubleValue();
              double ant = Double.valueOf(this.jlbl_anticipos.getText()).doubleValue();
              double totAnt = 0.0D;
              double sobranteSaldo = 0.0D;
              if (saldo <= 0.0D)
                this.jlbl_saldoOperador.setText("" + (ant + saldo * -1.0D) * -1.0D);
              else {
                this.jlbl_saldoOperador.setText("" + (saldo - ant));
              }

            }
            else
            {
              this.tarjetas_recaudadas[this.ntarjetas_recaudadas][16] = "0";
              this.jlbl_saldoOperador.setText(this.jlbl_saldo_Anterior.getText());
            }
            try
            {
              if (this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1)
                this.jlbl_ocupacion.setText("0.0");
              else
                this.jlbl_ocupacion.setText("" + roundNum(Float.valueOf(new StringBuilder().append("").append(bolvendidos / Float.valueOf(tarjeta.get(10).toString()).floatValue() * 100.0F).toString()).floatValue(), 10));
            } catch (NumberFormatException ex) {
              ex.printStackTrace();
            } catch (Exception ex) {
              ex.printStackTrace();
            }

            Vector goperador = new Vector();
            Vector gempresa = new Vector();
            List listaServicioGastos = this.busquedas.serviciosGastosTblFacadeRemote.buscaGastosPorServicio(BigDecimal.valueOf(Long.valueOf(tarjeta.get(11).toString()).longValue()));
            if (listaServicioGastos.size() > 0)
            {
              Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
              this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
              System.out.println("El numero de gastos del Servicio " + tarjeta.get(11).toString() + ": " + listaServicioGastos.size());
              int conteo = 0;
              this.tarifaVieja = false;
              for (int i = 0; i < listaServicioGastos.size(); i++)
              {
                TmsServiciosGastosTbl serviciosGastos = (TmsServiciosGastosTbl)listaServicioGastos.get(i);
                String sgServicio = "" + serviciosGastos.getServicioId();
                if (serviciosGastos.getEmpresaId() == null)
                {
                  new jdlg_advertencia("¡El gasto por servicio esta mal configurado!  ", " Contacte al administrador del sistema", "Error de configuracion de gasto por Servicio").setVisible(true);
                  return;
                }
                String sgEmpresa = "" + serviciosGastos.getEmpresaId().getEmpresaId();

                if ((!sgServicio.equals(tarjeta.get(11).toString())) || (!sgEmpresa.equals(tarjeta.get(12).toString())))
                  continue;
                TmsRecaudacionGastosTbl gasto = serviciosGastos.getRecaudacionGastoId();
                System.out.println("RecaudacionGastoCar  ga = " + gasto.getRecaudacionGastoCarga());
                Object[] campos = new Object[6];
                if ((gasto.getRecaudacionGastoAfectaCaja().equals("S")) && (gasto.getRecaudacionGastoComprobable().equals("N")))
                {
                  if (serviciosGastos.getCodigoParametro() != null)
                  {
                    if (serviciosGastos.getCodigoParametro().equals("P_R_COBTAR"))
                    {
                      if (this.VBusOrdis.indexOf(autobusExcp) >= 0)
                      {
                        montodebe += Float.valueOf("36.00").floatValue();
                        serviciosGastos.setGastoValor("36");
                        this.tarifaVieja = true;
                        this.otraEmpresa = true;
                        this.encuentraTarifa = true;
                      }
                      else
                      {
                        this.otraEmpresa = false;

                        System.out.println("(P_R_COBTAR) Busca el monto del pago con los datos: Servicio: " + tarjeta.get(11) + "  Ruta: " + tarjeta.get(13) + "  Empresa: " + tarjeta.get(12));
                        Timestamp tdt = null;
                        tdt = Timestamp.valueOf(tarjeta.get(5).toString());

                        String fechaHoraTarjeta = this.ffechaHora.format(Long.valueOf(tdt.getTime()));
                        String diaSemanaTarjeta = this.fdia.format(Long.valueOf(tdt.getTime()));
                        System.out.println("Fecha de la Tarjeta: " + fechaHoraTarjeta);
                        System.out.println("Dia de la Tarjeta: " + diaSemanaTarjeta);
                        float tarifa = this.busquedas.variosFacadeRemote.buscaTarifaTarjeta("P_R_COBTAR", tarjeta.get(11).toString(), tarjeta.get(13).toString(), tarjeta.get(12).toString(), fechaHoraTarjeta, diaSemanaTarjeta);
                        System.out.println("Tarifa encontrada: " + tarifa);
                        if (tarifa > 0.0F)
                          this.encuentraTarifa = true;
                        montodebe += Float.valueOf(tarifa).floatValue();
                        serviciosGastos.setGastoValor("" + tarifa);
                      }
                    }

                  }
                  else
                  {
                    if (serviciosGastos.getRecaudacionGastoId().getRecaudacionGastoNombre().equals("SUPER_DIESEL"))
                    {
                      jdlg_VentaDiesel pvd = new jdlg_VentaDiesel("¿Desea comprar DIESEL?", " Introduzca la cantidad a comprar", "Venta DIESEL");
                      pvd.setVisible(true);
                      if (pvd.isComprarDiesel())
                      {
                        serviciosGastos.setGastoValor("" + pvd.getMontoDiesel());
                        this.imprimeDiesel = true;
                        this.montoDiesel += pvd.getMontoDiesel();
                      }
                      else
                      {
                        serviciosGastos.setGastoValor("0");
                      }

                    }

                    if (serviciosGastos.getRecaudacionGastoId().getRecaudacionGastoNombre().equals("SUPER_PEAJE"))
                    {
                      if (this.VBusPeaje.indexOf(autobusExcp) >= 0) {
                        serviciosGastos.setGastoValor("0");
                      }
                    }
                    montodebe += Float.valueOf(serviciosGastos.getGastoValor()).floatValue();
                  }

                  if (gasto.getRecaudacionGastoAplica().equals("D")) {
                    Vector Vvnrpd = (Vector)this.busquedas.variosFacadeRemote.buscaRegistroPagoPorDia(gasto.getRecaudacionGastoId().longValue(), this.jtxt_claveOperador.getText(), this.ffecha.format(Long.valueOf(this.fecha_servidor.getTime())));
                    Vector vnrpd = (Vector)Vvnrpd.get(0);
                    int nrpd = Integer.valueOf(vnrpd.get(0).toString()).intValue();
                    if (nrpd > 0)
                    {
                      serviciosGastos.setGastoValor("0.0");

                      montodebe = 0.0F;
                    }
                    else
                    {
                      System.out.println("Verifica si se registro el gasto " + gasto.getRecaudacionGastoNombre() + " en el vetor gb");
                      int indexgb = gb.indexOf(gasto.getRecaudacionGastoNombre());
                      if (indexgb >= 0)
                      {
                        serviciosGastos.setGastoValor("0.0");

                        montodebe = 0.0F;
                      }
                      else
                      {
                        jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Gasto por día sin pagar", "¡No se ha pagado el gasto " + gasto.getRecaudacionGastoNombre() + "! ¿Desea Pagar el gasto en este momento?   ");
                        psn.setVisible(true);
                        if (this.respuestaSN)
                        {
                          gb.add(gasto.getRecaudacionGastoNombre());
                          System.out.println("Se agrega el gasto " + gasto.getRecaudacionGastoNombre() + " en el vetor gb");
                          this.jlbl_pagoPorOperador.setText("0");
                        }
                        else
                        {
                          serviciosGastos.setGastoValor("0.0");

                          montodebe = 0.0F;
                        }
                      }
                    }
                  }
                  campos[0] = gasto.getRecaudacionGastoNombre();
                  campos[1] = serviciosGastos.getGastoValor();
                  campos[2] = "N";
                  campos[3] = ("" + gasto.getRecaudacionGastoId());
                  campos[4] = "S";
                }

                if (((gasto.getRecaudacionGastoAfectaCaja().equals("N")) && (gasto.getRecaudacionGastoComprobable().equals("S"))) || ((gasto.getRecaudacionGastoAfectaCaja().equals("S")) && (gasto.getRecaudacionGastoComprobable().equals("S"))))
                {
                  this.vgastos.add(gasto.getRecaudacionGastoNombre());
                  this.vgastosId.add("" + gasto.getRecaudacionGastoId());
                  this.vgastosCarga.add("" + gasto.getRecaudacionGastoCarga());
                  System.out.println("Agrega el nombre: " + gasto.getRecaudacionGastoNombre());
                  System.out.println("Agrega el Valor: " + serviciosGastos.getGastoValor());
                  System.out.println("Agrega el GastoId: " + gasto.getRecaudacionGastoId());
                  System.out.println("Agrega el Cargo a: " + gasto.getRecaudacionGastoCarga());

                  if (gasto.getRecaudacionGastoAplica().equals("D")) {
                    Vector Vvnrpd = (Vector)this.busquedas.variosFacadeRemote.buscaRegistroPagoPorDia(gasto.getRecaudacionGastoId().longValue(), this.jtxt_claveOperador.getText(), this.ffecha.format(Long.valueOf(this.fecha_servidor.getTime())));
                    Vector vnrpd = (Vector)Vvnrpd.get(0);
                    int nrpd = Integer.valueOf(vnrpd.get(0).toString()).intValue();
                    if (nrpd > 0)
                    {
                      serviciosGastos.setGastoValor("0.0");
                      montodebe = 0.0F;
                    }
                    else
                    {
                      System.out.println("Verifica se se registro el gasto " + gasto.getRecaudacionGastoNombre() + " en el vetor gb");
                      int indexgb = gb.indexOf(gasto.getRecaudacionGastoNombre());
                      if (indexgb >= 0)
                      {
                        serviciosGastos.setGastoValor("0.0");
                        montodebe = 0.0F;
                      }
                      else
                      {
                        jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Gasto por día sin pagar", "¡No se ha pagado el gasto " + gasto.getRecaudacionGastoNombre() + "!. ¿Desea Pagar el gasto en este momento?");
                        psn.setVisible(true);
                        if (this.respuestaSN)
                        {
                          gb.add(gasto.getRecaudacionGastoNombre());
                          System.out.println("Se agrega el gasto " + gasto.getRecaudacionGastoNombre() + " en el vetor gb");
                          this.jlbl_pagoPorOperador.setText("0");
                        }
                        else
                        {
                          serviciosGastos.setGastoValor("0.0");
                          montodebe = 0.0F;
                        }
                      }
                    }
                  }
                  campos[0] = gasto.getRecaudacionGastoNombre();
                  campos[1] = "Comprobable";
                  campos[2] = "N";
                  campos[3] = ("" + gasto.getRecaudacionGastoId());
                  campos[4] = "N";
                  campos[5] = gasto.getRecaudacionGastoCarga();
                  this.gastosnocom = true;
                  if (this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1)
                  {
                    new jdlg_error("¡Las tarjetas con gastos comprobables se recaudan una por una!  ", "", "Tarjeta con gastos comprobables").setVisible(true);
                    return;
                  }
                }
                boolean agregarViaticos = true;
                if ((gasto.getRecaudacionGastoAfectaCaja().equals("N")) && (gasto.getRecaudacionGastoComprobable().equals("N")))
                {
                  if (gasto.getRecaudacionGastoAplica().equals("D")) {
                    Vector Vvnrpd = (Vector)this.busquedas.variosFacadeRemote.buscaRegistroPagoPorDia(gasto.getRecaudacionGastoId().longValue(), this.jtxt_claveOperador.getText(), this.ffecha.format(Long.valueOf(this.fecha_servidor.getTime())));
                    Vector vnrpd = (Vector)Vvnrpd.get(0);
                    int nrpd = Integer.valueOf(vnrpd.get(0).toString()).intValue();
                    if (nrpd > 0)
                    {
                      serviciosGastos.setGastoValor("0.0");
                      montodebe = 0.0F;
                    }
                    else
                    {
                      System.out.println("Verifica si se registro el gasto " + gasto.getRecaudacionGastoNombre() + " en el vetor gb");
                      int indexgb = gb.indexOf(gasto.getRecaudacionGastoNombre());
                      if (indexgb >= 0)
                      {
                        serviciosGastos.setGastoValor("0.0");
                        montodebe = 0.0F;
                      }
                      else
                      {
                        jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Gasto por día sin pagar", "¡No se ha pagado el gasto " + gasto.getRecaudacionGastoNombre() + "!. ¿Desea Pagar el gasto en este momento?");
                        psn.setVisible(true);
                        if (this.respuestaSN)
                        {
                          gb.add(gasto.getRecaudacionGastoNombre());
                          System.out.println("Se agrega el gasto " + gasto.getRecaudacionGastoNombre() + " en el vetor gb");
                          this.jlbl_pagoPorOperador.setText("0");
                        }
                        else
                        {
                          serviciosGastos.setGastoValor("0.0");
                          montodebe = 0.0F;
                        }
                      }
                    }
                  }

                  if (gasto.getRecaudacionGastoNombre().equals("Casetas"))
                  {
                    Vector casetas = (Vector)this.busquedas.variosFacadeRemote.BuscaCasetasTramo(tarjeta.get(1).toString(), tarjeta.get(2).toString(), tarjeta.get(3).toString());
                    if (casetas.size() == 0)
                    {
                      campos[0] = gasto.getRecaudacionGastoNombre();
                      campos[1] = "0";
                      campos[2] = "N";
                      campos[3] = ("" + gasto.getRecaudacionGastoId());
                      campos[4] = "N";
                    }
                    else
                    {
                      String numeros = "";
                      float costos = 0.0F;
                      for (int m = 0; m < casetas.size(); m++)
                      {
                        Vector cas = (Vector)casetas.get(m);
                        if (m == 0)
                          numeros = numeros + cas.get(0).toString();
                        else
                          numeros = numeros + " , " + cas.get(0).toString();
                        costos += Float.valueOf(cas.get(1).toString()).floatValue();
                      }
                      campos[0] = (gasto.getRecaudacionGastoNombre() + ": " + numeros);
                      campos[1] = Float.valueOf(costos);
                      campos[2] = "N";
                      campos[3] = ("" + gasto.getRecaudacionGastoId());
                      campos[4] = "N";
                    }
                  }
                  else
                  {
                    this.cantidadViaticos = 0.0F;
                    if (gasto.getRecaudacionGastoNombre().equals("Viaticos_Setex"))
                    {
                      if (this.VBusDirect.indexOf(autobusExcp) >= 0)
                      {
                        this.otraEmpresa = true;

                        float cv = this.busquedas.variosFacadeRemote.buscaViaticos(this.jtxt_claveOperador.getText());
                        System.out.println("Los viaticos del operador " + this.jtxt_claveOperador.getText() + " con el autobus " + autobusExcp + " son de: " + cv);
                        if (cv >= 50.0F)
                          agregarViaticos = false;
                        else
                          this.cantidadViaticos = Float.valueOf(serviciosGastos.getGastoValor()).floatValue();
                      }
                      else
                      {
                        agregarViaticos = false;
                        this.otraEmpresa = false;
                      }

                    }

                    if (agregarViaticos)
                    {
                      if (gasto.getRecaudacionGastoNombre().equals("Turismo_Hotel"))
                        serviciosGastos.setGastoValor(this.hoteles);
                      if (gasto.getRecaudacionGastoNombre().equals("Turismo_Lavadas"))
                        serviciosGastos.setGastoValor(this.lavadas);
                      if (gasto.getRecaudacionGastoNombre().equals("Turismo_Alimentos"))
                        serviciosGastos.setGastoValor(this.alimentos);
                      campos[0] = gasto.getRecaudacionGastoNombre();
                      campos[1] = serviciosGastos.getGastoValor();
                      campos[2] = "N";
                      campos[3] = ("" + gasto.getRecaudacionGastoId());
                      campos[4] = "N";
                    }
                  }
                }

                if (!agregarViaticos)
                  continue;
                if (gasto.getRecaudacionGastoCarga().equals("O"))
                {
                  this.gastos_tarjetas[this.ntarjetas_recaudadas][0] = campos[3].toString();
                  this.gastos_tarjetas[this.ntarjetas_recaudadas][1] = campos[1].toString();
                  this.gastos_tarjetas[this.ntarjetas_recaudadas][2] = "0";
                  camposGastos[ngastos][0] = campos[3].toString();
                  camposGastos[ngastos][1] = campos[1].toString();
                  camposGastos[ngastos][2] = "0";
                  ngastos++;
                  if (this.jtbl_gastos_operador.getRowCount() > 0)
                  {
                    boolean encuentra = false;
                    for (int kl = 0; kl < this.jtbl_gastos_operador.getRowCount(); kl++)
                    {
                      if (!this.jtbl_gastos_operador.getValueAt(kl, 3).toString().equals(campos[3].toString()))
                        continue;
                      String nombre = campos[0].toString();
                      if (nombre.indexOf("Casetas") >= 0)
                      {
                        StringTokenizer stoken = new StringTokenizer(nombre);
                        String token = stoken.nextToken();
                        token = "" + this.jtbl_gastos_operador.getValueAt(kl, 0).toString() + " , " + stoken.nextToken();
                        this.jtbl_gastos_operador.setValueAt("" + token, kl, 0);
                      }
                      if (!this.jtbl_gastos_operador.getValueAt(kl, 1).toString().equals("Comprobable"))
                        this.jtbl_gastos_operador.setValueAt("" + (Double.valueOf(this.jtbl_gastos_operador.getValueAt(kl, 1).toString()).doubleValue() + Double.valueOf(campos[1].toString()).doubleValue()), kl, 1);
                      encuentra = true;
                      break;
                    }

                    if (!encuentra)
                      this.gopermodelo.addRow(campos);
                  }
                  else {
                    this.gopermodelo.addRow(campos);
                  }System.out.println("Operador = " + gasto.getRecaudacionGastoNombre());
                  if ((gasto.getRecaudacionGastoNombre().equals("SUPER_TARIFA_TARJETA")) && (this.tarifaVieja))
                  {
                    System.out.println("Ya encontro el gasto en el renglon: " + conteo);
                    this.itsRowSelect = conteo;
                  }
                  conteo++;
                }

                if (!gasto.getRecaudacionGastoCarga().equals("E"))
                  continue;
                camposGastosEmpre[ngastosEmpre][0] = campos[3].toString();
                camposGastosEmpre[ngastosEmpre][1] = campos[1].toString();
                camposGastosEmpre[ngastosEmpre][2] = "0";
                ngastosEmpre++;
                if (this.jtbl_gastos_empresa.getRowCount() > 0)
                {
                  boolean encuentra = false;
                  for (int kl = 0; kl < this.jtbl_gastos_empresa.getRowCount(); kl++)
                  {
                    if (!this.jtbl_gastos_empresa.getValueAt(kl, 3).toString().equals(campos[3].toString()))
                      continue;
                    String nombre = campos[0].toString();
                    if (nombre.indexOf("Casetas") >= 0)
                    {
                      StringTokenizer stoken = new StringTokenizer(nombre);
                      String token = stoken.nextToken();
                      token = "";
                      while (stoken.hasMoreTokens())
                        token = token + stoken.nextToken();
                      token = "" + this.jtbl_gastos_empresa.getValueAt(kl, 0).toString() + " , " + token;
                      this.jtbl_gastos_empresa.setValueAt("" + token, kl, 0);
                    }
                    if (!this.jtbl_gastos_empresa.getValueAt(kl, 1).toString().equals("Comprobable"))
                      this.jtbl_gastos_empresa.setValueAt("" + (Double.valueOf(this.jtbl_gastos_empresa.getValueAt(kl, 1).toString()).doubleValue() + Double.valueOf(campos[1].toString()).doubleValue()), kl, 1);
                    encuentra = true;
                    break;
                  }

                  if (!encuentra)
                    this.gempremodelo.addRow(campos);
                }
                else {
                  this.gempremodelo.addRow(campos);
                }System.out.println("Empresa = " + gasto.getRecaudacionGastoNombre());
              }

              this.vgastos_una_tarjeta.add(camposGastos);
              this.vgastos_una_tarjetaEmpre.add(camposGastosEmpre);
            }
            this.jtbl_gastos_operador.repaint();
            System.out.println("Se agrega la tarjeta: " + this.ntarjetas_recaudadas);
            System.out.println("tamaño antes: " + this.vgastos_tarjetas.size());
            this.vgastos_tarjetas.add(this.vgastos_una_tarjeta);
            this.vgastos_tarjetasEmpre.add(this.vgastos_una_tarjetaEmpre);
            System.out.println("tamaño despues: " + this.vgastos_tarjetas.size());
            System.out.println("Gastos Operador del Servicio " + tarjeta.get(11).toString() + ":" + goperador);
            System.out.println("Gastos Empresa  del Servicio " + tarjeta.get(11).toString() + ":" + gempresa);

            if (this.gastosnocom)
              this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>F3</font> Comprobar Gastos  | <font color=FF0000>F8</font> Venta Manual | <font color=FF0000>F10</font> Pagar Tarjeta</html>");
            else
              this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>F8</font> Venta Manual | <font color=FF0000>F10</font> Pagar Tarjeta</html>");
            this.jpnl_tarjetaDescripcion.setVisible(true);
            this.jpnl_tarjetaDescripcion2.setVisible(false);
            this.jlbl_folioPago.requestFocus();
          }

          float totalp = 0.0F;
          for (int i = 0; i < this.jtbl_gastos_operador.getRowCount(); i++)
          {
            if (this.jtbl_gastos_operador.getValueAt(i, 4).toString().equals("S"))
              totalp += Float.valueOf(this.jtbl_gastos_operador.getValueAt(i, 1).toString()).floatValue();
          }
          this.jlbl_pagoPorOperador.setText("" + totalp);

          this.jlbl_total_pagar.setText("" + totalp);
          this.jtbl_tarjetas_pendientes.setEnabled(false);
        }

      }
      else
      {
        new jdlg_error("¡No puedes recaudar tarjetas de diferentes servicios!  ", "", "Error de seleccion de tarjetas").setVisible(true);
      }
    }

    if (evt.getKeyCode() == 27)
    {
      habilitarDatosOperador();
      this.jpnl_tajetasPendientes.setVisible(false);
      this.jpnl_tajetasPendientes2.setVisible(true);
      this.defaultmodelo.setDataVector((Object[][])null, this.encabezadotp);
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Recaudacion | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Cancelar Pago | <font color=FF0000>F12</font> Recoleccion  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
      resizeColumnasTP();
      limpiarDescripcion();
      this.jtxt_claveOperador.requestFocus();
    }
  }

  private void jtxt_autobusKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      int indexfun = this.funciones.indexOf("6001");
      if (indexfun >= 0) {
        tarjetasPendientes();
      }
      else {
        if (!abreSocketAS()) {
          new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
          return;
        }
        if (validarDatosSupervisor("6001")) {
          tarjetasPendientes();
        }
      }
    }
    if ((evt.getKeyCode() == 37) || (evt.getKeyCode() == 39))
    {
      this.jtxt_claveOperador.requestFocus();
      this.jtxt_claveOperador.selectAll();
    }

    if (evt.getKeyCode() == 122)
    {
      if (!abreSocketAS()) {
        new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
        return;
      }
      Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
      String estado = vestado.get(0).toString();
      if (estado.equals("CERRADA")) {
        new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
        System.exit(0);
      }
      new cancelar_tarjeta(getIdusuario(), this.busquedas, this.funciones, getSesionId(), getCorteid(), this);
    }
    if (evt.getKeyCode() == 123)
    {
      Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
      String estado = vestado.get(0).toString();
      if (estado.equals("CERRADA")) {
        new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
        System.exit(0);
      }
      recolecciones();
    }

    if (evt.getKeyCode() == 115) {
      salirAplicacion();
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

  private void jtxt_claveOperadorKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      int indexfun = this.funciones.indexOf("6001");
      if (indexfun >= 0) {
        tarjetasPendientes();
      }
      else {
        if (!abreSocketAS()) {
          new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
          return;
        }
        if (validarDatosSupervisor("6001")) {
          tarjetasPendientes();
        }
      }
    }
    if ((evt.getKeyCode() == 37) || (evt.getKeyCode() == 39))
    {
      this.jtxt_autobus.requestFocus();
      this.jtxt_autobus.selectAll();
    }

    if (evt.getKeyCode() == 122)
    {
      if (!abreSocketAS()) {
        new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
        return;
      }
      Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
      String estado = vestado.get(0).toString();
      if (estado.equals("CERRADA")) {
        new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
        System.exit(0);
      }
      new cancelar_tarjeta(getIdusuario(), this.busquedas, this.funciones, getSesionId(), getCorteid(), this);
    }

    if (evt.getKeyCode() == 123)
    {
      Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
      String estado = vestado.get(0).toString();
      if (estado.equals("CERRADA")) {
        new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
        System.exit(0);
      }
      recolecciones();
    }

    if (evt.getKeyCode() == 115) {
      salirAplicacion();
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

  private void deshabilitarDatosOperador()
  {
    this.jLabel1.setEnabled(false);
    this.jtxt_claveOperador.setEnabled(false);
    this.jLabel2.setEnabled(false);
    this.jtxt_autobus.setEnabled(false);
    this.jLabel4.setEnabled(false);
    this.jpnl_datosOperador.setEnabled(false);
  }

  private void habilitarDatosOperador() {
    this.jLabel1.setEnabled(true);
    this.jtxt_claveOperador.setEnabled(true);
    this.jtxt_claveOperador.setText("");
    this.jLabel2.setEnabled(true);
    this.jtxt_autobus.setEnabled(true);
    this.jtxt_autobus.setText("");
    this.jLabel4.setEnabled(true);
    this.jtxt_nombreOperador.setText("");
    this.jpnl_datosOperador.setEnabled(true);
  }

  private void limpiarDescripcion() {
    Vector vnfol = (Vector)this.busquedas.variosFacadeRemote.queryBuscaValorActualPago();
    int nfol = Integer.valueOf(vnfol.get(0).toString()).intValue();
    this.jlbl_folioPago.setText("" + (nfol + 1));

    this.jlbl_numeroCorrida.setText("");
    this.jlbl_nombreRuta.setText("");
    this.jlbl_kilometros.setText("0");
    this.jlbl_cupo.setText("0");
    this.jlbl_ocupacion.setText("0");
    this.jlbl_sueldoOperador.setText("0");
    this.jlbl_retencion.setText("0");
    this.jlbl_anticipos.setText("0");
    this.jlbl_boletosSistema.setText("0");
    this.jlbl_ventaSistema.setText("0");
    this.jlbl_boletosAbordo.setText("0");
    this.jlbl_ventaAbordo.setText("0.0");
    this.jlbl_boletosManual.setText("0");
    this.jlbl_ventaManual.setText("0.0");
    this.jlbl_pagoPorOperador.setText("0");
    this.jlbl_total_pagar.setText("0");
    this.jlbl_saldoOperador.setText("0");
    this.tarjetas_recaudadas = new String[10][23];
    this.gastos_tarjetas = new String[10][5];
    this.vgastos_tarjetas = new Vector();
    this.vgastos_tarjetasEmpre = new Vector();
    this.indiceTarjetas = new Vector();
    this.ntarjetas_recaudadas = 0;
    this.gempremodelo.setDataVector((Object[][])null, this.encabezadogastos);
    this.gopermodelo.setDataVector((Object[][])null, this.encabezadogastos);
    this.vgastosComprobados = new Vector();
    resizeColumnasGastos();
  }

  private void resizeColumnasTP()
  {
    TableColumn columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(0); columinv.setMinWidth(180); columinv.setMaxWidth(180); columinv.setPreferredWidth(180);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(1); columinv.setMinWidth(200); columinv.setMaxWidth(200); columinv.setPreferredWidth(200);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(2); columinv.setMinWidth(110); columinv.setMaxWidth(110); columinv.setPreferredWidth(110);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(3); columinv.setMinWidth(110); columinv.setMaxWidth(110); columinv.setPreferredWidth(100);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(4); columinv.setMinWidth(60); columinv.setMaxWidth(60); columinv.setPreferredWidth(60);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(5); columinv.setMinWidth(100); columinv.setMaxWidth(100); columinv.setPreferredWidth(100);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(6); columinv.setMinWidth(50); columinv.setMaxWidth(50); columinv.setPreferredWidth(50);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(7); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);

    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(8); columinv.setMinWidth(80); columinv.setMaxWidth(80); columinv.setPreferredWidth(80);
    columinv = this.jtbl_tarjetas_pendientes.getColumnModel().getColumn(9); columinv.setMinWidth(70); columinv.setMaxWidth(70); columinv.setPreferredWidth(70);
  }

  private void resizeColumnasGastos()
  {
    TableColumn columinv = this.jtbl_gastos_operador.getColumnModel().getColumn(0); columinv.setMinWidth(151); columinv.setMaxWidth(151); columinv.setPreferredWidth(151);
    columinv = this.jtbl_gastos_operador.getColumnModel().getColumn(1); columinv.setMinWidth(110); columinv.setMaxWidth(110); columinv.setPreferredWidth(110);

    columinv = this.jtbl_gastos_operador.getColumnModel().getColumn(2); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);
    columinv = this.jtbl_gastos_operador.getColumnModel().getColumn(3); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);
    columinv = this.jtbl_gastos_operador.getColumnModel().getColumn(4); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);

    columinv = this.jtbl_gastos_empresa.getColumnModel().getColumn(0); columinv.setMinWidth(150); columinv.setMaxWidth(150); columinv.setPreferredWidth(150);
    columinv = this.jtbl_gastos_empresa.getColumnModel().getColumn(1); columinv.setMinWidth(105); columinv.setMaxWidth(105); columinv.setPreferredWidth(105);
    TableCellRenderer centerRenderero2 = new CenterRenderer();
    columinv.setCellRenderer(centerRenderero2);
    columinv = this.jtbl_gastos_empresa.getColumnModel().getColumn(2); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);
    columinv = this.jtbl_gastos_empresa.getColumnModel().getColumn(3); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);
    columinv = this.jtbl_gastos_empresa.getColumnModel().getColumn(4); columinv.setMinWidth(0); columinv.setMaxWidth(0); columinv.setPreferredWidth(0);
  }

  private void tarjetasPendientes() {
    if (!abreSocketAS()) {
      new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
      return;
    }
    verificarAumulado();
    if (!this.noRecauda)
    {
      habilitarDatosOperador();
      this.jpnl_tajetasPendientes.setVisible(false);
      this.jpnl_tajetasPendientes2.setVisible(true);
      this.defaultmodelo.setDataVector((Object[][])null, this.encabezadotp);
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Recaudacion | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Cancelar Pago | <font color=FF0000>F12</font> Recoleccion  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
      resizeColumnasTP();
      limpiarDescripcion();
      this.jtxt_claveOperador.requestFocus();
      return;
    }
    resizeColumnasTP();
    System.out.println("Sesion ID= " + getSesionId());
    Vector vestado = (Vector)this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(getSesionId());
    String estado = vestado.get(0).toString();
    if (estado.equals("CERRADA")) {
      new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
      System.exit(0);
    }
    if (this.jtxt_claveOperador.getText().equals(""))
    {
      new jdlg_error("¡Debes ingresar la clave del operador!  ", "", " Campos vacios").setVisible(true);
    }
    else {
      List listaOperador = this.busquedas.operadoresTblFacadeRemote.BuscarOperadorPorClave(this.jtxt_claveOperador.getText());
      if (listaOperador.size() == 0)
      {
        new jdlg_error("¡El operador con clave " + this.jtxt_claveOperador.getText() + " no esta registrado en la Base de Datos!  ", "", " El operador no existe").setVisible(true);
        this.jtxt_claveOperador.setText("");
        this.jtxt_claveOperador.requestFocus();
      }
      else
      {
        TmsOperadoresTbl operador = (TmsOperadoresTbl)listaOperador.get(0);

        System.out.println("Fecha de alta del operador: " + operador.getFechaAlta());
        System.out.println("Fecha de alta del operador: " + operador.getFechaAlta().getTime());
        this.fechaAltamil = operador.getFechaAlta().getTime();

        this.nombreoperador = operador.getOperadorNombreCompleto();
        this.aplicaretencion = operador.getAplicaRetencion();
        this.vtajetasPendientes = ((Vector)this.busquedas.variosFacadeRemote.BuscaTarjetasPendientes(this.jtxt_claveOperador.getText(), this.jtxt_autobus.getText()));

        if (this.vtajetasPendientes.size() == 0)
        {
          if (this.jtxt_autobus.getText().equals(""))
          {
            new jdlg_advertencia("¡El operador " + this.nombreoperador + " no tiene tarjetas pendientes!  ", "", "No hay tarjetas pendientes").setVisible(true);
            habilitarDatosOperador();
            this.jpnl_tajetasPendientes.setVisible(false);
            this.jpnl_tajetasPendientes2.setVisible(true);
            this.defaultmodelo.setDataVector((Object[][])null, this.encabezadotp);
            this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Recaudacion | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Cancelar Pago | <font color=FF0000>F12</font> Recoleccion  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
            resizeColumnasTP();
            limpiarDescripcion();
            this.jtxt_claveOperador.requestFocus();
          }
          else
          {
            new jdlg_advertencia("¡El operador " + this.nombreoperador + " no tiene tarjetas pendientes con el autobus " + this.jtxt_autobus.getText() + "!  ", "", "No hay tarjetas pendientes").setVisible(true);
            habilitarDatosOperador();
          }
          this.jtxt_autobus.setText("");
          this.jtxt_claveOperador.setText("");
          this.jtxt_claveOperador.requestFocus();
        }
        else
        {
          this.jtxt_nombreOperador.setText(this.nombreoperador);
          Object[][] tajetasP = new Object[this.vtajetasPendientes.size()][15];
          for (int i = 0; i < this.vtajetasPendientes.size(); i++)
          {
            Vector tarjeta = (Vector)this.vtajetasPendientes.get(i);
            tajetasP[i][0] = tarjeta.get(0).toString();
            tajetasP[i][1] = tarjeta.get(1).toString();
            tajetasP[i][2] = tarjeta.get(2).toString();
            tajetasP[i][3] = tarjeta.get(3).toString();
            tajetasP[i][4] = tarjeta.get(4).toString();
            Timestamp fecha_hora = null;
            fecha_hora = Timestamp.valueOf(tarjeta.get(5).toString());
            tajetasP[i][5] = this.ffecha.format(Long.valueOf(fecha_hora.getTime()));
            tajetasP[i][6] = this.fhora.format(Long.valueOf(fecha_hora.getTime()));
            if (tarjeta.get(6) != null)
              tajetasP[i][7] = tarjeta.get(6).toString();
            else
              tajetasP[i][7] = "0";
            if (tarjeta.get(7) != null)
              tajetasP[i][8] = tarjeta.get(7).toString();
            else
              tajetasP[i][8] = "";
            if (tarjeta.get(8) != null)
              tajetasP[i][9] = tarjeta.get(8).toString();
            else {
              tajetasP[i][9] = "";
            }
          }
          this.defaultmodelo.setDataVector(tajetasP, this.encabezadotp);
          resizeColumnasTP();
          deshabilitarDatosOperador();
          this.jpnl_tajetasPendientes.setVisible(true);
          this.jpnl_tajetasPendientes2.setVisible(false);
          this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>ENTER</font> Selecciona Tarjeta(s)</html>");
          this.jtbl_tarjetas_pendientes.setRowSelectionInterval(0, 0);
          this.jtbl_tarjetas_pendientes.setColumnSelectionInterval(0, 0);
          this.jtbl_tarjetas_pendientes.requestFocus();
        }
      }
    }
  }

  public void cargarValores()
  {
    if (!abreSocketAS()) {
      new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
      this.respuestaSN = false;
      return;
    }
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
        new jdlg_error("¡Numero de usuario o Contraseña invalidos!   ", " Favor de intentar nuevamente", "Datos incorrector").setVisible(true);
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

  public static double roundNum(double num, int ndec)
    throws Exception
  {
    double valor = 0.0D;

    valor = num;

    valor *= ndec;
    valor = Math.round(valor);
    valor /= ndec;

    return valor;
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
    this.noRecauda = true;
    if ((this.acumulado >= this.montorecoleccion) && (this.acumulado < this.toperecoleccion)) {
      new jdlg_advertencia("¡Solicita al supervisor una recoleccion!    ", "", "Recoleccion Pendiente").setVisible(true);
    }
    else {
      System.out.println("compara  " + this.acumulado + " >= " + this.toperecoleccion);
      if (this.acumulado >= this.toperecoleccion)
      {
        new jdlg_error("¡En este momento ya no puedes recaudar mas efectivo!   ", "          ¡Realiza una recoleccion!", "Recoleccion Necesaria").setVisible(true);
        this.noRecauda = false;
      }
    }
  }

  public void setIdusuario(long id) {
    this.idusuario = id;
  }

  public long getIdusuario()
  {
    return this.idusuario;
  }

  public void setCorteid(long corid) {
    this.corteId = corid;
  }

  public long getCorteid() {
    return this.corteId;
  }

  public void sumaAcumulado(double acumula) {
    this.acumulado += acumula;
  }

  public void restaAcumulado(double acumula) {
    this.acumulado -= acumula;
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

  public String getNombre_equipo() {
    return this.nombre_equipo;
  }

  public void setNombre_equipo(String nombre_equipo) {
    this.nombre_equipo = nombre_equipo;
  }

  public boolean imprimir_recibo_diesel_LPT(String[][] datos_recibidos, String pSalidaImpresion, int pnt, String ptexto)
  {
    String[][] datos_imprimir = datos_recibidos;
    int numtarjetas = pnt;
    String texto = ptexto;
    String SalidaImpresion = pSalidaImpresion;
    String autobus = "";
    double sueldo = 0.0D;
    double retencion = 0.0D;
    double totaltarjeta = 0.0D;
    double vta_manual = 0.0D;
    double vta_abordo = 0.0D;
    Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
    this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
    String sCad = "\n";
    sCad = sCad + "      TERMINAL LAS TORRES PUEBLA";
    sCad = sCad + "\n";
    sCad = sCad + "              VALE DIESEL";
    sCad = sCad + "\n";
    sCad = sCad + "             " + texto;
    sCad = sCad + "\n\n";
    sCad = sCad + "FOLIO. : " + this.folioDiesel;
    sCad = sCad + "\n";
    autobus = datos_imprimir[1][1];
    sueldo = 0.0D;
    vta_manual = Double.valueOf(this.jlbl_ventaManual.getText()).doubleValue();
    sCad = sCad + "\n";
    sCad = sCad + "OPERADOR:   " + datos_imprimir[6][1];
    sCad = sCad + "\n";
    sCad = sCad + "\n";
    sCad = sCad + "VALIDO POR:   " + this.montoDiesel;
    sCad = sCad + "\n";
    sCad = sCad + "( " + new cantidad_a_letras().toLetras(Double.valueOf(new StringBuilder().append("").append(this.montoDiesel).toString()).longValue()) + "Pesos 00/M.N. )";
    sCad = sCad + "\n"; sCad = sCad + "\n";
    sCad = sCad + "RECAUDADOR: " + this.nombre_recaudador;
    SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
    sCad = sCad + "\n";
    sCad = sCad + "FECHA DE RECAUDACION: " + formatf.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n";
    sCad = sCad + "HORA DE RECAUDACION : " + formath.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n"; sCad = sCad + "\n";
    sCad = sCad + "FIRMA RECAUDADOR: _____________________";
    sCad = sCad + "\n\n\n";
    sCad = sCad + "FIRMA OPERADOR  : _____________________";
    sCad = sCad + "\n\n";
    sCad = sCad + " Este documento no es transferible ni\n";
    sCad = sCad + "cancelable y tiene vigencia de 24 Hrs\n";
    sCad = sCad + "  a partir de su fecha de emision.\n";
    sCad = sCad + "\n\n\n\n\n\n\n";
    sCad = sCad + "         .";
    try {
      if (SalidaImpresion.equals("ARCHIVO")) {
        SalidaImpresion = "C:\\TICKET_DIESEL_" + this.folioDiesel + ".TXT";
      }
      FileDescriptor fd = new FileDescriptor();
      FileOutputStream os = new FileOutputStream(SalidaImpresion);
      PrintStream ps = new PrintStream(os);
      ps.print(sCad);
      ps.flush();
      os.close();
    } catch (FileNotFoundException fsctex) {
      fsctex.printStackTrace();
      return false;
    } catch (Exception sctex) {
      sctex.printStackTrace();
      return false;
    }
    return true;
  }

  public boolean imprimir_recibo_LPT(String[][] datos_recibidos, String pSalidaImpresion, int pnt) {
    String[][] datos_imprimir = datos_recibidos;
    int numtarjetas = pnt;
    String SalidaImpresion = pSalidaImpresion;
    String autobus = "";
    double sueldo = 0.0D;
    double retencion = 0.0D;
    double totaltarjeta = 0.0D;
    double vta_manual = 0.0D;
    double vta_abordo = 0.0D;
    Vector x = (Vector)this.busquedas.variosFacadeRemote.fechaServidor();
    this.fecha_servidor = Timestamp.valueOf(x.get(0).toString());
    String sCad = "\n";
    if (this.otraEmpresa)
      sCad = sCad + "     SUPER EXPRESOS DE TEXMELUCAN";
    else
      sCad = sCad + "    AUTOBUSES MEX-PUE ESTRELLA ROJA";
    sCad = sCad + "\n\n";
    sCad = sCad + "REF. : " + datos_imprimir[0][1];
    sCad = sCad + "\n";
    autobus = datos_imprimir[1][1];
    sueldo = 0.0D;
    for (int nt = 1; nt <= numtarjetas; nt++)
    {
      if (this.tarRecAnt.indexOf(datos_imprimir[2][nt]) != -1) {
        continue;
      }
      if ((autobus.equals(datos_imprimir[1][nt])) && (nt > 1)) {
        autobus = datos_imprimir[1][1];
      }
      else {
        sCad = sCad + "\n";
        sCad = sCad + "AUTOBUS:       " + datos_imprimir[1][nt];
      }
      sCad = sCad + "\n";
      sCad = sCad + "FOLIO TARJETA: ";
      String folioCompleto = datos_imprimir[2][nt];
      String inicio = folioCompleto.substring(0, 10);
      String fin = folioCompleto.substring(10);
      sCad = sCad + "" + inicio;
      sCad = sCad + "\n";
      sCad = sCad + "               " + fin;
      sCad = sCad + "\n";
      sCad = sCad + "SERVICIO:       " + datos_imprimir[3][nt];
      sCad = sCad + "\n";
      sCad = sCad + "FECHA VIAJE:    " + datos_imprimir[4][nt];
      sCad = sCad + "\n";
      sCad = sCad + "HORA VIAJE:     " + datos_imprimir[5][nt];
      sueldo += Double.valueOf(datos_imprimir[7][nt]).doubleValue();
      retencion += Double.valueOf(datos_imprimir[8][nt]).doubleValue();
      totaltarjeta += Double.valueOf(datos_imprimir[9][nt]).doubleValue();
      if (Double.valueOf(datos_imprimir[11][nt]).doubleValue() > 0.0D)
      {
        sCad = sCad + "\n";
        sCad = sCad + "VTA ABORDO:     " + datos_imprimir[11][nt];
      }
      if (Double.valueOf(datos_imprimir[13][nt]).doubleValue() > 0.0D)
      {
        sCad = sCad + "\n";
        sCad = sCad + "VTA MANUAL:     " + datos_imprimir[13][nt];
      }
      vta_abordo += Double.valueOf(this.jlbl_ventaAbordo.getText()).doubleValue();
    }

    vta_manual = Double.valueOf(this.jlbl_ventaManual.getText()).doubleValue();
    sCad = sCad + "\n";
    sCad = sCad + "OPERADOR:   " + datos_imprimir[6][1];
    sCad = sCad + "\n";
    double turismo = 0.0D;
    if (this.jtbl_gastos_empresa.getRowCount() > 1)
    {
      for (int xng = 0; xng < this.jtbl_gastos_empresa.getRowCount(); xng++)
      {
        if ((this.jtbl_gastos_empresa.getValueAt(xng, 0).toString().equals("Turismo_Hotel")) || (this.jtbl_gastos_empresa.getValueAt(xng, 0).toString().equals("Turismo_Lavadas")) || (this.jtbl_gastos_empresa.getValueAt(xng, 0).toString().equals("Turismo_Alimentos"))) {
          turismo += Double.valueOf(this.jtbl_gastos_empresa.getValueAt(xng, 1).toString()).doubleValue();
        }
      }
    }
    sCad = sCad + "SUELDO:     " + Round(sueldo + turismo, 2);
    System.out.println("Sueldo Ticket: " + sueldo);
    sCad = sCad + "\n";
    sCad = sCad + "RETENCION:  " + Round(retencion, 2);
    System.out.println("Retencion Ticket: " + retencion);
    sCad = sCad + "\n";
    sCad = sCad + "TOTAL:      " + Round(totaltarjeta + turismo, 2);
    System.out.println("Total: " + totaltarjeta + turismo);
    double pagOper = Double.valueOf(this.jlbl_pagoPorOperador.getText()).doubleValue();
    sCad = sCad + "\n"; sCad = sCad + "\n";
    if ((totaltarjeta == 0.0D) && (pagOper > 0.0D))
      sCad = sCad + "( " + new cantidad_a_letras().toLetras((long)pagOper) + "Pesos 00/M.N. )";
    else
      sCad = sCad + "( " + new cantidad_a_letras().toLetras((long)Round(totaltarjeta + turismo, 2)) + "Pesos 00/M.N. )";
    sCad = sCad + "\n"; sCad = sCad + "\n";
    if (vta_abordo > 0.0D)
    {
      sCad = sCad + "VTA ABORDO:  " + vta_abordo;
      sCad = sCad + "\n";
    }
    if (vta_manual > 0.0D)
    {
      sCad = sCad + "VTA MANUAL:  " + vta_manual;
      sCad = sCad + "\n";
    }
    if (pagOper > 0.0D)
    {
      sCad = sCad + "MONTO PAGADO: " + pagOper;
      sCad = sCad + "\n";
    }
    double saldoOper = Double.valueOf(this.jlbl_saldoOperador.getText()).doubleValue();
    if (saldoOper != 0.0D)
    {
      sCad = sCad + "SALDO OPERADOR: " + saldoOper;
      sCad = sCad + "\n";
    }
    if (this.cantidadViaticos != 0.0F)
    {
      sCad = sCad + "VIATICOS OPERADOR: " + this.cantidadViaticos;
      sCad = sCad + "\n";
    }

    sCad = sCad + "RECAUDADOR: " + this.nombre_recaudador;
    SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
    sCad = sCad + "\n";
    sCad = sCad + "FECHA DE RECAUDACION: " + formatf.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n";
    sCad = sCad + "HORA DE RECAUDACION : " + formath.format(new Date(this.fecha_servidor.getTime()));
    sCad = sCad + "\n"; sCad = sCad + "\n";
    sCad = sCad + "FIRMA ________________________";
    sCad = sCad + "\n\n\n\n\n\n\n\n\n\n\n\n";
    sCad = sCad + "         .";
    try {
      if (SalidaImpresion.equals("ARCHIVO")) {
        SalidaImpresion = "C:\\TICKET_PAGO_" + datos_imprimir[0][1].toString() + ".TXT";
      }
      FileDescriptor fd = new FileDescriptor();
      FileOutputStream os = new FileOutputStream(SalidaImpresion);
      PrintStream ps = new PrintStream(os);
      ps.print(sCad);
      ps.flush();
      os.close();
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
      FileDescriptor fd = new FileDescriptor();
      FileOutputStream os = new FileOutputStream(SalidaImpresion);
      PrintStream ps = new PrintStream(os);
      ps.print(sCad);
      ps.flush();
      os.close();
    } catch (FileNotFoundException fsctex) {
      fsctex.printStackTrace();
      return false;
    } catch (Exception sctex) {
      sctex.printStackTrace();
      return false;
    }

    return true;
  }

  public Vector getFunciones()
  {
    return this.funciones;
  }

  public void setFunciones(Vector funciones) {
    this.funciones = funciones;
  }

  public Vector getFauditables() {
    return this.fauditables;
  }

  public void setFauditables(Vector fauditables) {
    this.fauditables = fauditables;
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

  public void setSesionId(String sesionId) {
    this.sesionId = Long.valueOf(sesionId).longValue();
  }

  public boolean getInicioGral() {
    return this.inicioGral;
  }

  public void setInicioGral(boolean inicioGral) {
    this.inicioGral = inicioGral;
  }

  private void setCorteActual(TmsCortesTbl pcorten) {
    this.corteActual = pcorten;
  }

  private TmsCortesTbl getCorteActual() {
    return this.corteActual;
  }

  public boolean abreSocketAS()
  {
    Socket s = null;
    try {
        System.out.println("(this.ipAS  "+this.ipAS+" this.portAS "+this.portAS);
      s = new Socket(this.ipAS, this.portAS);
      return true;
    } catch (IOException e) {
        e.printStackTrace();
      return false; } catch (Exception err) {
    }
    return false;
  }

  public void setFoco()
  {
    this.jtxt_claveOperador.requestFocus();
  }

  public KeyEvent getEventoTeclado() {
    return this.eventoTeclado;
  }
  public void setEventoTeclado(KeyEvent evento) { this.eventoTeclado = evento; }

  class AttributiveCellRenderer extends JLabel implements TableCellRenderer
  {
    public AttributiveCellRenderer()
    {
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      if ((value == null) || (value.equals("")))
        setText(null);
      else
        setText(String.valueOf(value));
      setFont(new Font("Tahoma", 0, 10));
      if ((row == JIFRecaudacion.this.itsRowSelect) && (column == 1) && (JIFRecaudacion.this.tarifaVieja))
        setBackground(new Color(254, 250, 160));
      else
        setBackground(new Color(238, 238, 239));
      setHorizontalAlignment(4);
      return this;
    }
  }

  public class CustomTableCellRenderer extends DefaultTableCellRenderer
  {
    public CustomTableCellRenderer()
    {
    }

    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column)
    {
      Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
      if (isSelected) {
        cell.setBackground(new Color(0, 175, 255));
        cell.setForeground(Color.WHITE);
      }
      else {
        cell.setForeground(Color.BLACK);
        if (row % 2 == 0) {
          cell.setBackground(new Color(217, 229, 241));
        }
        else {
          cell.setBackground(Color.WHITE);
        }
      }
      if (column == 7)
        setHorizontalAlignment(4);
      else
        setHorizontalAlignment(2);
      return cell;
    }
  }

  class CenterRenderer extends DefaultTableCellRenderer
  {
    public CenterRenderer()
    {
      setHorizontalAlignment(4);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      return this;
    }
  }

  class jdlg_ComprobarGatosDentro extends JDialog
  {
    private JTable Tablagastos;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JScrollPane jScrollPane1;
    private JCuantityTextField jbtxt_cantidad;
    private JCuantityTextField jbtxt_totalgastos;
    private JComboBox jcmb_listaimpuesto;
    private JLabel jlbl_barraEstado;
    private JLabel jlbl_importe;
    private JCuantityTextField jtxt_totalanticipos;
    private JComboBox listaconceptos;
    private double totalanticipos = 0.0D;
    private double favor = 0.0D;
    private double pagos = 0.0D;
    private double contra = 0.0D;
    private DefaultTableModel defaultmodelo = new DefaultTableModel() {
      public boolean isCellEditable(int row, int column) { return column == 20;
      }
    };
    private Vector vgastos2;
    private Double anticipos;
    private Double sobranteSaldo;
    private Vector vgastosId2;
    private Vector vgastosCarga2;

    public jdlg_ComprobarGatosDentro(Vector pvgastos, Vector pvgastosId, Vector pvgastosCarga, Double panticipos, Double psobranteSaldo)
    {
      this.vgastos2 = pvgastos;
      this.vgastosId2 = pvgastosId;
      this.vgastosCarga2 = pvgastosCarga;
      this.anticipos = panticipos;
      this.sobranteSaldo = psobranteSaldo;
      initComponents();
      setTitle("Comprobacion de Gastos");
      setModal(true);
      setDefaultCloseOperation(0);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = getSize();
      if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
      }
      setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
      this.listaconceptos.removeAllItems();
      for (int i = 0; i < JIFRecaudacion.this.vgastos.size(); i++)
        this.listaconceptos.addItem(JIFRecaudacion.this.vgastos.get(i).toString());
      this.Tablagastos.setModel(this.defaultmodelo);
      this.defaultmodelo.addColumn("Concepto");
      this.defaultmodelo.addColumn("%");
      this.defaultmodelo.addColumn("Subtotal");
      this.defaultmodelo.addColumn("Impuesto");
      this.defaultmodelo.addColumn("Total");
      this.defaultmodelo.addColumn("id");
      this.defaultmodelo.addColumn("Cargar");
      reziseColumnas();
      this.jtxt_totalanticipos.setText("" + this.anticipos);
      this.totalanticipos = this.anticipos.doubleValue();
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESCAPE</font> Salir | <font color=FF0000>« »</font> Moverse entre Campos | <font color=FF0000>ENTER</font> Agregar Comprobación | <font color=FF0000>F10</font> Registrar Comprobaciones  </html>");
      this.jbtxt_cantidad.requestFocus();
      this.contra = this.totalanticipos;
    }

    private void reziseColumnas() {
      TableColumn column = new TableColumn();
      int n = 210;
      column = this.Tablagastos.getColumnModel().getColumn(0);
      column.setResizable(false);
      column.setMinWidth(n);
      column.setMaxWidth(n);
      column.setPreferredWidth(n);
      column.setResizable(false);
      n = 30;
      column = this.Tablagastos.getColumnModel().getColumn(1);
      column.setResizable(false);
      column.setMinWidth(n);
      column.setMaxWidth(n);
      column.setPreferredWidth(n);
      column.setResizable(false);

      n = 70;
      column = this.Tablagastos.getColumnModel().getColumn(2);
      column.setResizable(false);
      column.setMinWidth(n);
      column.setMaxWidth(n);
      column.setPreferredWidth(n);
      column.setResizable(false);
      TableCellRenderer centerRenderero = new JIFRecaudacion.CenterRenderer();
      column.setCellRenderer(centerRenderero);
      column = this.Tablagastos.getColumnModel().getColumn(3);
      column.setResizable(false);
      column.setMinWidth(n);
      column.setMaxWidth(n);
      column.setPreferredWidth(n);
      column.setResizable(false);
      centerRenderero = new JIFRecaudacion.CenterRenderer();
      column.setCellRenderer(centerRenderero);
      column = this.Tablagastos.getColumnModel().getColumn(4);
      column.setResizable(false);
      column.setMinWidth(n);
      column.setMaxWidth(n);
      column.setPreferredWidth(n);
      column.setResizable(false);
      centerRenderero = new JIFRecaudacion.CenterRenderer();
      column.setCellRenderer(centerRenderero);
      n = 0;
      column = this.Tablagastos.getColumnModel().getColumn(5);
      column.setResizable(false);
      column.setMinWidth(n);
      column.setMaxWidth(n);
      column.setPreferredWidth(n);
      column.setResizable(false);
      n = 30;
      column = this.Tablagastos.getColumnModel().getColumn(6);
      column.setResizable(false);
      column.setMinWidth(n);
      column.setMaxWidth(n);
      column.setPreferredWidth(n);
      column.setResizable(false);
    }

    private void initComponents()
    {
      this.jlbl_importe = new JLabel();
      this.jbtxt_cantidad = new JCuantityTextField();
      this.jLabel2 = new JLabel();
      this.jcmb_listaimpuesto = new JComboBox();
      this.jLabel3 = new JLabel();
      this.jScrollPane1 = new JScrollPane();
      this.Tablagastos = new JTable();
      this.jLabel4 = new JLabel();
      this.jbtxt_totalgastos = new JCuantityTextField();
      this.jLabel5 = new JLabel();
      this.jtxt_totalanticipos = new JCuantityTextField();
      this.jlbl_barraEstado = new JLabel();
      this.listaconceptos = new JComboBox();

      setDefaultCloseOperation(2);
      this.jlbl_importe.setFont(new Font("Tahoma", 1, 12));
      this.jlbl_importe.setText("Importe Total:");
      this.jlbl_importe.addFocusListener(new FocusAdapter() {
        public void focusGained(FocusEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.jlbl_importeFocusGained(evt);
        }
      });
      this.jlbl_importe.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.jlbl_importeKeyPressed(evt);
        }
      });
      this.jbtxt_cantidad.setHorizontalAlignment(4);
      this.jbtxt_cantidad.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.jbtxt_cantidadKeyPressed(evt);
        }
      });
      this.jbtxt_cantidad.addFocusListener(new FocusAdapter() {
        public void focusGained(FocusEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.jbtxt_cantidadFocusGained(evt);
        }
        public void focusLost(FocusEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.jbtxt_cantidadFocusLost(evt);
        }
      });
      this.jLabel2.setFont(new Font("Tahoma", 1, 12));
      this.jLabel2.setText("Impuesto:");

      this.jcmb_listaimpuesto.setModel(new DefaultComboBoxModel(new String[] { "17", "15", "10", "0" }));
      this.jcmb_listaimpuesto.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.jcmb_listaimpuestoKeyPressed(evt);
        }
      });
      this.jLabel3.setFont(new Font("Tahoma", 1, 12));
      this.jLabel3.setText("%");

      this.Tablagastos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4", "Title 5" }));

      this.Tablagastos.addFocusListener(new FocusAdapter() {
        public void focusGained(FocusEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.TablagastosFocusGained(evt);
        }
      });
      this.Tablagastos.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.TablagastosFocusLost(evt);
        }
      });
      this.Tablagastos.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.TablagastosKeyPressed(evt);
        }
      });
      this.jScrollPane1.setViewportView(this.Tablagastos);

      this.jLabel4.setFont(new Font("Tahoma", 1, 12));
      this.jLabel4.setText("Total Gastos:");

      this.jbtxt_totalgastos.setEditable(false);
      this.jbtxt_totalgastos.setHorizontalAlignment(4);
      this.jbtxt_totalgastos.setText("0.0");

      this.jLabel5.setFont(new Font("Tahoma", 1, 12));
      this.jLabel5.setText("Total Anticipos:");

      this.jtxt_totalanticipos.setEditable(false);
      this.jtxt_totalanticipos.setHorizontalAlignment(4);
      this.jtxt_totalanticipos.setText("0.0");

      this.jlbl_barraEstado.setFont(new Font("Tahoma", 1, 10));
      this.jlbl_barraEstado.setForeground(new Color(0, 0, 0));
      this.jlbl_barraEstado.setText("jLabel6");
      this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

      this.listaconceptos.setModel(new DefaultComboBoxModel(new String[] { "Gastos Comprobables" }));
      this.listaconceptos.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.jdlg_ComprobarGatosDentro.this.listaconceptosKeyPressed(evt);
        }
      });
      GroupLayout layout = new GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(20, 20, 20).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(layout.createParallelGroup(2).add(this.jLabel2).add(this.jlbl_importe)).addPreferredGap(0).add(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jbtxt_cantidad, -2, 69, -2).add(26, 26, 26).add(this.listaconceptos, 0, 264, 32767)).add(layout.createSequentialGroup().add(this.jcmb_listaimpuesto, -2, 45, -2).addPreferredGap(0).add(this.jLabel3)))).add(layout.createParallelGroup(2, false).add(1, layout.createSequentialGroup().add(this.jLabel4).addPreferredGap(0).add(this.jbtxt_totalgastos, -2, 75, -2).addPreferredGap(0, -1, 32767).add(this.jLabel5).addPreferredGap(0).add(this.jtxt_totalanticipos, -2, 75, -2)).add(1, this.jScrollPane1, -2, 452, -2))).add(21, 21, 21)).add(this.jlbl_barraEstado, -1, 493, 32767));

      layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(3).add(this.jlbl_importe).add(this.jbtxt_cantidad, -2, -1, -2).add(this.listaconceptos, -2, -1, -2)).add(17, 17, 17).add(layout.createParallelGroup(3).add(this.jLabel2).add(this.jcmb_listaimpuesto, -2, -1, -2).add(this.jLabel3)).add(18, 18, 18).add(this.jScrollPane1, -2, 171, -2).add(15, 15, 15).add(layout.createParallelGroup(3).add(this.jLabel4).add(this.jbtxt_totalgastos, -2, -1, -2).add(this.jtxt_totalanticipos, -2, -1, -2).add(this.jLabel5)).addPreferredGap(0, 21, 32767).add(this.jlbl_barraEstado, -2, 44, -2)));

      pack();
    }

    private void jbtxt_cantidadFocusGained(FocusEvent evt) {
      this.jbtxt_cantidad.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 88, 236)));
    }

    private void jbtxt_cantidadFocusLost(FocusEvent evt) {
      this.jbtxt_cantidad.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
    }

    private void TablagastosFocusLost(FocusEvent evt) {
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESCAPE</font> Salir | <font color=FF0000>« »</font> Moverse entre Campos | <font color=FF0000>ENTER</font> Agregar Comprobación | <font color=FF0000>F10</font> Registrar Comprobaciones  </html>");
    }

    private void TablagastosFocusGained(FocusEvent evt) {
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESCAPE</font> Salir | <font color=FF0000>SUPR</font> Eliminar Comprobación | <font color=FF0000>F10</font> Registrar Comprobaciones  </html>");
    }

    private void jlbl_importeFocusGained(FocusEvent evt)
    {
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESCAPE</font> Salir | <font color=FF0000>« »</font> Moverse entre Campos | <font color=FF0000>ENTER</font> Agregar Comprobación | <font color=FF0000>F10</font> Registrar Comprobaciones  </html>");
    }

    private void jlbl_importeKeyPressed(KeyEvent evt)
    {
    }

    private void TablagastosKeyPressed(KeyEvent evt) {
      if ((evt.getKeyCode() == 39) || (evt.getKeyCode() == 9))
      {
        this.jbtxt_cantidad.selectAll();
        this.jbtxt_cantidad.requestFocus();
      }
      if (evt.getKeyCode() == 37) {
        this.jcmb_listaimpuesto.requestFocus();
      }
      if (evt.getKeyCode() == 127) {
        quitar();
      }
      if (evt.getKeyCode() == 121) {
        registrar();
      }
      if (evt.getKeyCode() == 27)
        dispose();
    }

    private void jcmb_listaimpuestoKeyPressed(KeyEvent evt)
    {
      if (evt.getKeyCode() == 39)
      {
        if (this.Tablagastos.getRowCount() > 0)
        {
          this.Tablagastos.setRowSelectionInterval(0, 0);
          this.Tablagastos.setColumnSelectionInterval(0, 0);
          this.Tablagastos.requestFocus();
        }
        else
        {
          this.jbtxt_cantidad.selectAll();
          this.jbtxt_cantidad.requestFocus();
        }
      }
      if (evt.getKeyCode() == 37) {
        this.listaconceptos.requestFocus();
      }
      if (evt.getKeyCode() == 10) {
        agregar();
      }
      if (evt.getKeyCode() == 121) {
        registrar();
      }
      if (evt.getKeyCode() == 27)
        dispose();
    }

    private void jbtxt_cantidadKeyPressed(KeyEvent evt) {
      if (evt.getKeyCode() == 37)
      {
        if (this.Tablagastos.getRowCount() > 0)
        {
          this.Tablagastos.setRowSelectionInterval(0, 0);
          this.Tablagastos.setColumnSelectionInterval(0, 0);
          this.Tablagastos.requestFocus();
        }
        else {
          this.jcmb_listaimpuesto.requestFocus();
        }
      }
      if (evt.getKeyCode() == 39) {
        this.listaconceptos.requestFocus();
      }
      if (evt.getKeyCode() == 10) {
        agregar();
      }
      if (evt.getKeyCode() == 121) {
        registrar();
      }
      if (evt.getKeyCode() == 27)
        dispose();
    }

    private void listaconceptosKeyPressed(KeyEvent evt)
    {
      if (evt.getKeyCode() == 37)
        this.jbtxt_cantidad.requestFocus();
      if (evt.getKeyCode() == 39)
        this.jcmb_listaimpuesto.requestFocus();
      if (evt.getKeyCode() == 10)
        agregar();
      if (evt.getKeyCode() == 121)
        registrar();
      if (evt.getKeyCode() == 27)
        dispose();
    }

    private void registrar() {
      double pagoper = Double.valueOf(jlbl_pagoPorOperador.getText()).doubleValue();
      if (pagoper < this.pagos)
      {
        String nomgasto = "";
        for (int i = 0; i < jtbl_gastos_empresa.getRowCount(); i++)
        {
          if (!jtbl_gastos_empresa.getValueAt(i, 1).toString().equals("Comprobable"))
            continue;
          nomgasto = jtbl_gastos_empresa.getValueAt(i, 0).toString();
          break;
        }

        new jdlg_error("¡El monto del gasto " + nomgasto + " debe ser menor o igual al monto del Pago por operador! ", "", "").setVisible(true);
        return;
      }
      jdlg_pregunta_SN psn = new jdlg_pregunta_SN( "Registrar Comprobacion de gastos", "¿Desea Registrar la comprobacion de los gastos?");
      psn.setVisible(true);
      if (respuestaSN)
      {
        System.out.println("Favor: " + this.favor);
        System.out.println("Contra: " + this.contra);
        System.out.println("pagos: " + this.pagos);
        //JIFRecaudacion.access$8202(JIFRecaudacion.this, this.defaultmodelo.getDataVector());
        vgastosComprobados = defaultmodelo.getDataVector();
        System.out.println("vgastosComprobados= " + vgastosComprobados);

        if (this.favor >= this.contra)
        {
          this.favor += this.sobranteSaldo.doubleValue();
          jlbl_saldoOperador.setText("" + this.favor);
        }
        else
        {
          jlbl_saldoOperador.setText("" + this.contra * -1.0D);
        }

        if (pagoper >= this.pagos)
        {
          double npagoper = pagoper - this.pagos;
          jlbl_pagoPorOperador.setText("" + npagoper);
          jlbl_total_pagar.setText("" + npagoper);
        }

        //JIFRecaudacion.access$10402(JIFRecaudacion.this, true);
        gastosComprobados = true;
        dispose();
      }
    }

    private void agregar() {
      if ((this.jbtxt_cantidad.getText().equals("")) || (Double.valueOf(this.jbtxt_cantidad.getText()).doubleValue() <= 0.0D))
      {
        new jdlg_error("¡Debes introducir la cantidad!", "", "¡Eror de datos!").setVisible(true);
        this.jbtxt_cantidad.requestFocus();
      }
      else
      {
        String[] datos = new String[7];
        String carga = "";
        datos[0] = this.listaconceptos.getSelectedItem().toString();
        datos[1] = this.jcmb_listaimpuesto.getSelectedItem().toString();
        try {
          datos[2] = ("" + roundNum(Float.parseFloat(this.jbtxt_cantidad.getText()) / (1.0F + Float.parseFloat(this.jcmb_listaimpuesto.getSelectedItem().toString()) / 100.0F)));
          datos[3] = ("" + roundNum(Float.parseFloat(this.jbtxt_cantidad.getText()) - roundNum(Float.parseFloat(this.jbtxt_cantidad.getText()) / (1.0F + Float.parseFloat(this.jcmb_listaimpuesto.getSelectedItem().toString()) / 100.0F))));
          datos[4] = this.jbtxt_cantidad.getText();

          datos[5] = ("" + this.vgastosId2.get(this.listaconceptos.getSelectedIndex()));
          carga = "" + this.vgastosCarga2.get(this.listaconceptos.getSelectedIndex());
          datos[6] = carga; } catch (Exception e1) {
          e1.printStackTrace();
        }float tg = Float.parseFloat(this.jbtxt_totalgastos.getText()) + Float.parseFloat(datos[4]);
        try {
          tg = roundNum(tg); } catch (Exception e1) {
          e1.printStackTrace();
        }
        this.jbtxt_totalgastos.setText("" + tg);
        float tg2 = 0.0F;
        for (int i = 0; i < this.Tablagastos.getRowCount(); i++)
        {
          if (this.Tablagastos.getValueAt(i, 6).toString().equals("O"))
            tg2 += Float.valueOf(this.Tablagastos.getValueAt(i, 4).toString()).floatValue();
        }
        tg2 += Float.parseFloat(datos[4]);
        if (carga.equals("O"))
        {
          if (tg2 <= this.totalanticipos)
          {
            this.favor = 0.0D;
            double ta = this.totalanticipos - tg2;
            this.contra = ta;
          }
          else
          {
            this.contra = 0.0D;
            double ta = tg2 - this.totalanticipos;
            this.favor = ta;
          }
        }

        if (carga.equals("E"))
          this.pagos += Double.valueOf(datos[4]).doubleValue();
        this.defaultmodelo.addRow(datos);
        this.jbtxt_cantidad.setText("");
        this.jbtxt_cantidad.requestFocus();
      }
    }

    private void quitar() {
      if (this.Tablagastos.getSelectedRow() != -1)
      {
        try
        {
          float tg = Float.parseFloat(this.jbtxt_totalgastos.getText()) - Float.parseFloat((String)this.Tablagastos.getValueAt(this.Tablagastos.getSelectedRow(), 4));
          tg = roundNum(tg);
          this.jbtxt_totalgastos.setText("" + tg);
          if (this.contra == 0.0D)
          {
            double vf = this.favor;
            double vq = Double.parseDouble((String)this.Tablagastos.getValueAt(this.Tablagastos.getSelectedRow(), 4));
            double vr = 0.0D;
            if (vf > vq)
            {
              vr = vf - vq;
              this.favor = vr;
            }
            else if (vf < vq)
            {
              vr = vq - vf;
              this.favor = 0.0D;
              this.contra = vr;
            }
            else if (vf == vq) {
              this.favor = 0.0D;
              this.contra = this.totalanticipos;
              vr = 0.0D;
            }

          }
          else
          {
            double vc = this.contra;
            this.contra = (vc + Double.parseDouble((String)this.Tablagastos.getValueAt(this.Tablagastos.getSelectedRow(), 4)));
          }
        } catch (Exception e1) {
          e1.printStackTrace();
        }this.defaultmodelo.removeRow(this.Tablagastos.getSelectedRow());
      }
    }

    private void nuevoGasto() {
      this.jlbl_importe.setEnabled(true);
      this.jLabel2.setEnabled(true);
      this.jLabel3.setEnabled(true);
      this.jLabel4.setEnabled(true);
      this.jLabel5.setEnabled(true);
      this.listaconceptos.setEnabled(true);
      this.jbtxt_cantidad.setEditable(true);
      this.jcmb_listaimpuesto.setEnabled(true);
      this.jbtxt_cantidad.setText("");
      this.Tablagastos.setEnabled(true);
      this.jlbl_importe.requestFocus();
    }

    public float roundNum(float num) throws Exception {
      float valor = 0.0F;

      valor = num;

      valor *= 100.0F;
      valor = Math.round(valor);
      valor /= 100.0F;

      return valor;
    }

    public double roundNumd(double num) throws Exception
    {
      double valor = 0.0D;
      valor = num;
      valor *= 1.0D;
      valor = Math.round(valor);
      valor /= 1.0D;
      return valor;
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

      setResizable(false);
      this.jlbl_mensaje.setText(pregunta);
      this.jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si                 <font color=FF0000>             ESC: </font> No  </html>");
      this.jlbl_barraEstado.setHorizontalAlignment(0);
      int nletras = pregunta.length();
      setSize(nletras * 6 + 80, 155);
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
      this.jlbl_barraEstado.setForeground(new Color(0, 0, 0));
      this.jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
      this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

      this.jlbl_mensaje.setFont(new Font("Arial", 1, 12));
      this.jlbl_mensaje.setText("sdfsdsdfsdfsdfsdfsdf");
      this.jlbl_mensaje.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.jdlg_pregunta_SN.this.jlbl_mensajeKeyPressed(evt);
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
        //JIFRecaudacion.access$9202(JIFRecaudacion.this, false);
        respuestaSN  = false;
        dispose();
      }
      if (evt.getKeyCode() == 10)
      {
        //JIFRecaudacion.access$9202(JIFRecaudacion.this, true);
        respuestaSN  = true;
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
    private ImageIcon imagen_pregunta = new ImageIcon(JIFRecaudacion.vista_pago_impresion.class.getResource("pregunta.gif"));
    private String numref;

    public Jdlg_Pregunta(String title, String nref)
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
          JIFRecaudacion.Jdlg_Pregunta.this.jLabel3.requestFocusInWindow();
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
      this.jLabel3.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.Jdlg_Pregunta.this.jLabel1_KeyPressed(evt);
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
      this.jlbl_barraEstadoP.setForeground(new Color(0, 0, 0));
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
        //JIFRecaudacion.access$9002(JIFRecaudacion.this, true);
        reimpresion = true;
        dispose();
      }
      if (evt.getKeyCode() == 10)
      {
        //JIFRecaudacion.access$9002(JIFRecaudacion.this, false);
        reimpresion = false;
        dispose();
      }
    }
  }

  class imprimir_recibo_tarjeta
    implements Printable
  {
    private double PIXELES_POR_PULGADA = 72.0D;
    private double ANCHO = 3.625D;
    private double ALTO = 12.0D;
    private String[][] datos_imprimir;
    private int numtarjetas;

    public imprimir_recibo_tarjeta(String[][] datos_recibidos, int nt)
    {
      this.datos_imprimir = datos_recibidos;
      this.numtarjetas = nt;
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
      if (page > 0) {
        return 1;
      }
      String autobus = "";
      double sueldo = 0.0D;
      double retencion = 0.0D;
      double totaltarjeta = 0.0D;
      double vta_manual = 0.0D;
      double vta_abordo = 0.0D;
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pf.getImageableX(), pf.getImageableY());
      Font fontVar = new Font("Arial", 0, 9);
      g.setFont(fontVar);
      //Vector x = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.fechaServidor();
      //JIFRecaudacion.access$4702(JIFRecaudacion.this, Timestamp.valueOf(x.get(0).toString()));
      Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
      fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
      
      int lineaNueva = 11; int i = 1;
      if (JIFRecaudacion.this.otraEmpresa)
        g.drawString(" SUPER EXPRESOS DE TEXMELUCAN", 10, lineaNueva);
      else
        g.drawString("AUTOBUSES MEX-PUE ESTRELLA ROJA", 10, lineaNueva);
      i++; i++;
      g.drawString("REF. : ", 0, lineaNueva * i);
      g.drawString(this.datos_imprimir[0][1], 40, lineaNueva * i);
      i++;
      autobus = this.datos_imprimir[1][1];
      sueldo = 0.0D;
      for (int nt = 1; nt <= this.numtarjetas; nt++)
      {
        if (JIFRecaudacion.this.tarRecAnt.indexOf(this.datos_imprimir[2][nt]) != -1) {
          continue;
        }
        if ((autobus.equals(this.datos_imprimir[1][nt])) && (nt > 1)) {
          autobus = this.datos_imprimir[1][1];
        }
        else {
          i++;
          g.drawString("AUTOBUS", 0, lineaNueva * i);
          g.drawString(":", 73, lineaNueva * i);
          g.drawString(this.datos_imprimir[1][nt], 85, lineaNueva * i);
        }
        i++;
        g.drawString("FOLIO TARJETA", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        String folioCompleto = this.datos_imprimir[2][nt];
        String inicio = folioCompleto.substring(0, 10);
        String fin = folioCompleto.substring(10);
        g.drawString(inicio, 85, lineaNueva * i);
        i++;
        g.drawString(fin, 85, lineaNueva * i);
        i++;
        g.drawString("SERVICIO", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(this.datos_imprimir[3][nt], 85, lineaNueva * i);
        i++;
        g.drawString("FECHA VIAJE", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(this.datos_imprimir[4][nt], 85, lineaNueva * i);
        i++;
        g.drawString("HORA VIAJE", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString(this.datos_imprimir[5][nt], 85, lineaNueva * i);
        sueldo += Double.valueOf(this.datos_imprimir[7][nt]).doubleValue();
        retencion += Double.valueOf(this.datos_imprimir[8][nt]).doubleValue();
        totaltarjeta += Double.valueOf(this.datos_imprimir[9][nt]).doubleValue();
        if (Double.valueOf(this.datos_imprimir[11][nt]).doubleValue() > 0.0D)
        {
          i++;
          g.drawString("VTA ABORDO", 0, lineaNueva * i);
          g.drawString(":", 73, lineaNueva * i);
          g.drawString(this.datos_imprimir[11][nt], 85, lineaNueva * i);
        }
        if (Double.valueOf(this.datos_imprimir[13][nt]).doubleValue() > 0.0D)
        {
          i++;
          g.drawString("VTA MANUAL", 0, lineaNueva * i);
          g.drawString(":", 73, lineaNueva * i);
          g.drawString(this.datos_imprimir[13][nt], 85, lineaNueva * i);
        }
        vta_abordo += Double.valueOf(JIFRecaudacion.this.jlbl_ventaAbordo.getText()).doubleValue();
      }

      vta_manual = Double.valueOf(JIFRecaudacion.this.jlbl_ventaManual.getText()).doubleValue();
      i++;
      g.drawString("OPERADOR", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(this.datos_imprimir[6][1], 85, lineaNueva * i);
      i++;
      g.drawString("SUELDO", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString("" + sueldo, 85, lineaNueva * i);
      System.out.println("Sueldo Ticket: " + sueldo);
      i++;
      g.drawString("RETENCION", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString("" + retencion, 85, lineaNueva * i);
      System.out.println("Retencion Ticket: " + retencion);
      i++;
      g.drawString("TOTAL", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString("" + totaltarjeta, 85, lineaNueva * i);
      System.out.println("Total: " + totaltarjeta);
      i++; i++;
      g.drawString("( " + new cantidad_a_letras().toLetras((long)totaltarjeta) + "Pesos 00/M.N. )", 0, lineaNueva * i);
      i++; i++;
      if (vta_abordo > 0.0D)
      {
        g.drawString("VTA ABORDO", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString("" + vta_abordo, 85, lineaNueva * i);
        i++;
      }
      if (vta_manual > 0.0D)
      {
        g.drawString("VTA MANUAL", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString("" + vta_manual, 85, lineaNueva * i);
        i++;
      }
      double pagOper = Double.valueOf(JIFRecaudacion.this.jlbl_pagoPorOperador.getText()).doubleValue();
      if (pagOper > 0.0D)
      {
        g.drawString("MONTO A PAGAR", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString("" + pagOper, 85, lineaNueva * i);
        i++;
      }
      double saldoOper = Double.valueOf(JIFRecaudacion.this.jlbl_saldoOperador.getText()).doubleValue();
      if (saldoOper != 0.0D)
      {
        g.drawString("SALDO OPERADOR", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString("" + saldoOper, 85, lineaNueva * i);
        i++;
      }
      if (JIFRecaudacion.this.cantidadViaticos != 0.0F)
      {
        g.drawString("VIATICOS OPERADOR", 0, lineaNueva * i);
        g.drawString(":", 73, lineaNueva * i);
        g.drawString("" + JIFRecaudacion.this.cantidadViaticos, 85, lineaNueva * i);
        i++;
      }
      g.drawString("RECAUDADOR", 0, lineaNueva * i);
      g.drawString(":", 73, lineaNueva * i);
      g.drawString(JIFRecaudacion.this.nombre_recaudador, 85, lineaNueva * i);
      SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
      i++;
      g.drawString("FECHA DE RECAUDACION: ", 0, lineaNueva * i);
      g.drawString("" + formatf.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++;
      g.drawString("HORA DE RECAUDACION : ", 0, lineaNueva * i);
      g.drawString("" + formath.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())), 125, lineaNueva * i);
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
      try {
        int indeximp = JIFRecaudacion.this.formatos.indexOf("TICKETS");
        PrintService impresora = (PrintService)JIFRecaudacion.this.impresoras.get(indeximp);
        job.setPrintService(impresora);
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

  class vista_pago_impresion extends JDialog
  {
    private JTextArea jTextArea1 = new JTextArea();
    private JLabel jlbl_mensajeVPI = new JLabel();
    private JLabel jlbl_barraEstadoVPI = new JLabel();
    private ImageIcon imageHelp = new ImageIcon(vista_pago_impresion.class.getResource("pregunta.gif"));
    private JLabel jLabel2 = new JLabel();
    private int alto = 0;
    private String[][] datos;
    private int ntarjetas = 0;
    private String autobus = "";
    private double sueldo = 0.0D;
    private double retencion = 0.0D;
    private double totaltarjeta = 0.0D;
    private double vta_manual = 0.0D;
    private double vta_abordo = 0.0D;

    public vista_pago_impresion(String[][] data, int n) {
      setTitle("Vista de Impresion de Ticket");
      setDefaultLookAndFeelDecorated(true);
      setUndecorated(true);
      getRootPane().setWindowDecorationStyle(7);

      setAlwaysOnTop(true);
      setModal(true);
      this.datos = data;
      this.ntarjetas = n;
      this.alto = (40 * n);
      try {
        jbInit();
      } catch (Exception e) {
        e.printStackTrace();
      }
      this.jTextArea1.requestFocus();
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
      setResizable(false);
      this.jlbl_barraEstadoVPI.setFont(new Font("Tahoma", 1, 12));
      this.jlbl_barraEstadoVPI.setForeground(new Color(0, 0, 0));
      this.jlbl_barraEstadoVPI.setBorder(BorderFactory.createBevelBorder(1));
      this.jlbl_barraEstadoVPI.setBounds(new Rectangle(0, 395 + this.alto, 400, 25));
      this.jlbl_barraEstadoVPI.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
      this.jlbl_barraEstadoVPI.setHorizontalAlignment(0);
      this.jTextArea1.setBounds(new Rectangle(10, 10, 370, 300 + this.alto));
      this.jTextArea1.setEditable(false);

      this.jTextArea1.setFont(new Font("Dialog", 0, 12));
      this.jTextArea1.setSize(new Dimension(375, 335 + this.alto));
      this.jlbl_mensajeVPI.setText("¿Seguro que desea pagar la tarjeta?");
      this.jlbl_mensajeVPI.setBounds(new Rectangle(105, 365 + this.alto, 250, 15));
      this.jlbl_mensajeVPI.setFont(new Font("Arial", 1, 12));
      this.jTextArea1.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.vista_pago_impresion.this.jTextArea1_KeyPressed(evt);
        }
      });
      this.jTextArea1.setFocusTraversalKeysEnabled(false);
      this.jLabel2.setBounds(new Rectangle(60, 350 + this.alto, 35, 45));
      this.jLabel2.setIcon(this.imageHelp);
      this.jTextArea1.append(" REF.: " + this.datos[0][1] + "\n\n");
      this.autobus = this.datos[1][1];
      this.sueldo = 0.0D;
      for (int i = 1; i <= this.ntarjetas; i++)
      {
        if ((this.autobus.equals(this.datos[1][i])) && (i > 1))
          this.autobus = this.datos[1][1];
        else
          this.jTextArea1.append(" Autobus : " + this.datos[1][i] + "\n");
        //Vector x = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.fechaServidor();
        //JIFRecaudacion.access$4702(JIFRecaudacion.this, Timestamp.valueOf(x.get(0).toString()));
         Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
         fecha_servidor = fecha_servidor.valueOf(x.get(0).toString()); 
        this.jTextArea1.append(" Folio de Tarjeta :   " + this.datos[2][i] + "\n");
        this.jTextArea1.append(" Servicio               :   " + this.datos[3][i] + "\n");
        this.jTextArea1.append(" Fecha de Viaje  :   " + this.datos[4][i] + "\n");
        this.jTextArea1.append(" Hora de Viaje    :   " + this.datos[5][i] + "\n");
        this.sueldo += Double.valueOf(this.datos[7][i]).doubleValue();
        this.retencion += Double.valueOf(this.datos[8][i]).doubleValue();
        this.totaltarjeta += Double.valueOf(this.datos[9][i]).doubleValue();

        this.vta_abordo += Double.valueOf(JIFRecaudacion.this.jlbl_ventaAbordo.getText()).doubleValue();
      }
      double turismo = 0.0D;
      if (JIFRecaudacion.this.jtbl_gastos_empresa.getRowCount() > 1)
      {
        for (int x = 0; x < JIFRecaudacion.this.jtbl_gastos_empresa.getRowCount(); x++)
        {
          if ((JIFRecaudacion.this.jtbl_gastos_empresa.getValueAt(x, 0).toString().equals("Turismo_Hotel")) || (JIFRecaudacion.this.jtbl_gastos_empresa.getValueAt(x, 0).toString().equals("Turismo_Lavadas")) || (JIFRecaudacion.this.jtbl_gastos_empresa.getValueAt(x, 0).toString().equals("Turismo_Alimentos")))
            turismo += Double.valueOf(JIFRecaudacion.this.jtbl_gastos_empresa.getValueAt(x, 1).toString()).doubleValue();
        }
      }
      System.out.println("el total de turismo es: " + turismo);
      this.vta_manual += Double.valueOf(JIFRecaudacion.this.jlbl_ventaManual.getText()).doubleValue();
      double pagoOper = Double.valueOf(JIFRecaudacion.this.jlbl_pagoPorOperador.getText()).doubleValue();
      double saldo = Double.valueOf(JIFRecaudacion.this.jlbl_saldoOperador.getText()).doubleValue();
      this.jTextArea1.append(" Operador           :   " + this.datos[6][1] + "\n");
      this.jTextArea1.append(" Sueldo               :   " + JIFRecaudacion.this.Round(this.sueldo + turismo, 2) + "\n");
      this.jTextArea1.append(" Retencion         :   " + this.retencion + "\n");
      this.jTextArea1.append(" Total                  :   " + JIFRecaudacion.this.Round(this.totaltarjeta + turismo, 2) + "\n");
      if (pagoOper > 0.0D)
        this.jTextArea1.append(" Monto Pagado     :   " + pagoOper + "\n");
      if (saldo != 0.0D)
        this.jTextArea1.append(" Saldo Operador  :   " + saldo + "\n");
      if ((this.totaltarjeta == 0.0D) && (pagoOper > 0.0D))
        this.jTextArea1.append("              ( " + new cantidad_a_letras().toLetras((long)pagoOper) + " Pesos 00/M.N.)\n");
      else
        this.jTextArea1.append("              ( " + new cantidad_a_letras().toLetras((long)JIFRecaudacion.this.Round(this.totaltarjeta + turismo, 2)) + " Pesos 00/M.N.)\n");
      if (this.vta_abordo > 0.0D)
        this.jTextArea1.append(" Venta Abordo    :   " + this.vta_abordo + "\n");
      if (this.vta_manual > 0.0D)
        this.jTextArea1.append(" Venta Manual    :   " + this.vta_manual + "\n");
      if (JIFRecaudacion.this.cantidadViaticos != 0.0F)
        this.jTextArea1.append(" Viaticos Operador    :   " + JIFRecaudacion.this.cantidadViaticos + "\n");
      this.jTextArea1.append(" Recaudador      :   " + this.datos[10][1] + "\n");
      SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
      this.jTextArea1.append(" Fecha de Recaudacion:  " + formatf.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())) + "\n");
      this.jTextArea1.append(" Hora de Recaudacion  : " + formath.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())) + "\n");
      this.jTextArea1.append(" Firma: ________________________ \n");
      this.jLabel2.setFocusable(false);
      this.jlbl_mensajeVPI.setFocusable(false);
      add(this.jLabel2, null);
      add(this.jTextArea1, null);
      add(this.jlbl_barraEstadoVPI, null);
      add(this.jlbl_mensajeVPI, null);
      this.jTextArea1.requestFocus();
    }

    private void jTextArea1_KeyPressed(KeyEvent evt)
    {
      if (evt.getKeyCode() == 27)
      {
        dispose();
      }
      if (evt.getKeyCode() == 10)
      {
        if (!JIFRecaudacion.this.abreSocketAS()) {
          new jdlg_error_conexionBD_NET("En este momento no es posible realizar la operación.", "Intentelo mas tarde.", "¡No existe una conexión con la Base de Datos!").setVisible(true);
          dispose();
          return;
        }
        long nref = 0L;
        Vector vestado = (Vector)JIFRecaudacion.this.busquedas.cajasTblFacadeRemote.buscarEstadoSesion(JIFRecaudacion.this.getSesionId());
        String estado = vestado.get(0).toString();
        if (estado.equals("CERRADA")) {
          new jdlg_error("¡La sesion es invaida!", "El Sistema se cerrará automáticamente", "La sesion fue cerrada").setVisible(true);
          System.exit(0);
        }
        Vector x = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.fechaServidor();
        //JIFRecaudacion.access$4702(JIFRecaudacion.this, Timestamp.valueOf(x.get(0).toString()));
        fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
        Vector TER = (Vector)JIFRecaudacion.this.busquedas.cajasTblFacadeRemote.queryBuscaTerminalId();
        BigInteger terminal = BigInteger.valueOf(Long.valueOf(TER.get(0).toString()).longValue());
        TmsRecaudacionTbl recaudacionNueva = null;
        System.out.println("El tamaño del vector es: " + JIFRecaudacion.this.vgastos_tarjetas.size());
        System.out.println("El contenido del vector es: " + JIFRecaudacion.this.vgastos_tarjetas);

        for (int i = 1; i <= this.ntarjetas; i++)
        {
          Vector vgastosUnaTarjeta = (Vector)JIFRecaudacion.this.vgastos_tarjetas.get(i);
          String[][] camposGastos = new String[20][4];
          camposGastos = (String[][])(String[][])vgastosUnaTarjeta.get(0);
          System.out.println("Gasto0  : " + camposGastos[0][0] + " - " + camposGastos[0][1] + " - " + camposGastos[0][2]);
          System.out.println("Gasto1  : " + camposGastos[1][0] + " - " + camposGastos[1][1] + " - " + camposGastos[1][2]);
          System.out.println("Gasto2  : " + camposGastos[2][0] + " - " + camposGastos[2][1] + " - " + camposGastos[2][2]);
          System.out.println("Gasto3  : " + camposGastos[3][0] + " - " + camposGastos[3][1] + " - " + camposGastos[3][2]);
        }
        System.out.println("El tamaño del vector Empresas es: " + JIFRecaudacion.this.vgastos_tarjetasEmpre.size());
        System.out.println("El contenido del vector Empresas es: " + JIFRecaudacion.this.vgastos_tarjetasEmpre);

        for (int i = 1; i <= this.ntarjetas; i++)
        {
          Vector vgastosUnaTarjeta = (Vector)JIFRecaudacion.this.vgastos_tarjetasEmpre.get(i);
          String[][] camposGastos = new String[20][4];
          camposGastos = (String[][])(String[][])vgastosUnaTarjeta.get(0);
          System.out.println("GastoEmpre0  : " + camposGastos[0][0] + " - " + camposGastos[0][1] + " - " + camposGastos[0][2]);
          System.out.println("GastoEmpre1  : " + camposGastos[1][0] + " - " + camposGastos[1][1] + " - " + camposGastos[1][2]);
          System.out.println("GastoEmpre2  : " + camposGastos[2][0] + " - " + camposGastos[2][1] + " - " + camposGastos[2][2]);
          System.out.println("GastoEmpre3  : " + camposGastos[3][0] + " - " + camposGastos[3][1] + " - " + camposGastos[3][2]);
        }
        BigInteger nr = BigInteger.valueOf(Long.valueOf("1").longValue());
        boolean primera = true;
        int numRefGen = Integer.valueOf(this.datos[0][1].toString()).intValue();
        //JIFRecaudacion.access$6402(JIFRecaudacion.this, new Vector());
        tarRecAnt = new Vector();
        int ticket = 1;
        boolean exp2 = false;
        for (int i = 1; i <= this.ntarjetas; i++)
        {
          TmsRecaudacionTbl recaudacion = new TmsRecaudacionTbl();
          recaudacion.setTarjetaViajeId(BigInteger.valueOf(Long.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][1]).longValue()));
          recaudacion.setCorteId(JIFRecaudacion.this.corteActual);
          recaudacion.setRecaudacionReferencia(BigInteger.valueOf(Long.valueOf("1").longValue()));
          recaudacion.setImporteVentaSistema(BigDecimal.valueOf(Double.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][11]).doubleValue()));
          recaudacion.setBoletosVentaSistema(BigInteger.valueOf(Long.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][10]).longValue()));
          recaudacion.setImporteVentaAbordo(BigDecimal.valueOf(Double.valueOf(JIFRecaudacion.this.jlbl_ventaAbordo.getText()).doubleValue()));
          recaudacion.setBoletosVentaAbordo(BigInteger.valueOf(Long.valueOf(JIFRecaudacion.this.jlbl_boletosAbordo.getText()).longValue()));

          recaudacion.setImporteVentaManual(BigDecimal.valueOf(Double.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][13]).doubleValue()));
          recaudacion.setBoletosVentaManual(BigInteger.valueOf(Long.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][12]).longValue()));
          recaudacion.setAdicional4(JIFRecaudacion.this.tarjetas_recaudadas[i][21] == null ? "" : JIFRecaudacion.this.tarjetas_recaudadas[i][21]);
          recaudacion.setAdicional5("" + (JIFRecaudacion.this.tarjetas_recaudadas[i][22] == null ? "" : JIFRecaudacion.this.tarjetas_recaudadas[i][22]));

          recaudacion.setFechaHoraRecaudacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
          recaudacion.setSueldoOperador(BigDecimal.valueOf(Double.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][8]).doubleValue()));
          recaudacion.setRetencion(BigDecimal.valueOf(Double.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][9]).doubleValue()));
          recaudacion.setAnticipos(BigDecimal.valueOf(Double.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][16]).doubleValue()));
          double saldo = Double.valueOf(JIFRecaudacion.this.jlbl_saldoOperador.getText()).doubleValue();

          recaudacion.setSaldo(BigDecimal.valueOf(saldo));

          recaudacion.setEstadoRecaudacion("R");
          recaudacion.setRecaudadorId(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
          recaudacion.setCiudadRecaudacionId(terminal);
          recaudacion.setCreadoPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
          recaudacion.setFechaCreacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
          recaudacion.setUltimaActualizacionPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
          recaudacion.setUltimaFechaActualizacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
          boolean exp = false;
          try {
            recaudacionNueva = JIFRecaudacion.this.busquedas.recaudacionTblFacadeRemote.create(recaudacion, JIFRecaudacion.this.idTerminal, JIFRecaudacion.this.imprimeDiesel);
            if (recaudacionNueva.getAdicional3() != null)
                folioDiesel = Long.valueOf(recaudacionNueva.getAdicional3());
                //JIFRecaudacion.access$6602(JIFRecaudacion.this, Long.valueOf(recaudacionNueva.getAdicional3()).longValue());
          }
          catch (EJBException ex) {
            Vector vnticket = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.buscaTicket(Long.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][1]).longValue());
            Vector ntic = (Vector)vnticket.get(0);
            new jdlg_error("!La tarjeta " + JIFRecaudacion.this.tarjetas_recaudadas[i][2] + " ya fue recaudada ", "con el Ticket " + ntic.get(0) + " en " + ntic.get(1) + "!", "Tarjeta Recaudada").setVisible(true);
            System.out.println("Excpcion con ntarjetas= " + this.ntarjetas + "  y con i= " + i + "   en la tabla hay " + JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRowCount() + " tarjetas seleccionadas");
            exp = true;
            exp2 = true;
            ticket = Integer.valueOf(ntic.get(0).toString()).intValue();
            System.out.println("numRefGen: " + numRefGen + "   " + ticket);
            if (numRefGen != ticket)
              JIFRecaudacion.this.tarRecAnt.add(JIFRecaudacion.this.tarjetas_recaudadas[i][2]);
          }
          if ((primera) && (!exp))
          {
            nr = recaudacionNueva.getRecaudacionReferencia();
            numRefGen = nr.intValue();
            primera = false;
          }
          else if (!exp)
          {
            recaudacionNueva.setRecaudacionReferencia(nr);
            JIFRecaudacion.this.busquedas.recaudacionTblFacadeRemote.edit(recaudacionNueva);
          }

          TmsTarjetasViajeTbl tarjeta = JIFRecaudacion.this.busquedas.tarjetasViajeTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(JIFRecaudacion.this.tarjetas_recaudadas[i][1]).longValue()));
          Vector vedotar = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje("RECAUDADA");
          Vector edotar = (Vector)vedotar.get(0);
          Vector vedotarcan = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje("CANCELADA");
          Vector edotarcan = (Vector)vedotarcan.get(0);
          System.out.println("Estado: " + tarjeta.getEstadoTarjetaId());
          System.out.println("Operador: " + tarjeta.getOperador());
          System.out.println("Autobus: " + tarjeta.getAutobus());

          if (tarjeta.getEstadoTarjetaId().longValue() == Long.valueOf(edotarcan.get(0).toString()).longValue())
          {
            new jdlg_error("¡La tarjeta ha sido cancelada!", "", "Tarjeta cancelada").setVisible(true);
            dispose();
            JIFRecaudacion.this.jtbl_tarjetas_pendientes.setEnabled(true);
            JIFRecaudacion.this.jpnl_tarjetaDescripcion.setVisible(false);
            JIFRecaudacion.this.jpnl_tarjetaDescripcion2.setVisible(true);
            JIFRecaudacion.this.jtbl_tarjetas_pendientes.setEnabled(true);
//            JIFRecaudacion.access$6902(JIFRecaudacion.this, false);
//            JIFRecaudacion.access$7002(JIFRecaudacion.this, false);
            buscavtabordo = false;
            buscavtasistema = false;
            JIFRecaudacion.this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>ENTER</font> Selecciona Tarjeta(s)</html>");
            JIFRecaudacion.this.defaultmodelo.setDataVector((Object[][])null, JIFRecaudacion.this.encabezadotp);
            JIFRecaudacion.this.tarjetasPendientes();
            return;
          }
          if (!tarjeta.getOperador().equals(JIFRecaudacion.this.jtxt_claveOperador.getText()))
          {
            new jdlg_error("¡El operador de la tarjeta ha cambiado!", "", "Cambios en tarjeta").setVisible(true);
            dispose();
            JIFRecaudacion.this.jtbl_tarjetas_pendientes.setEnabled(true);
            JIFRecaudacion.this.jpnl_tarjetaDescripcion.setVisible(false);
            JIFRecaudacion.this.jpnl_tarjetaDescripcion2.setVisible(true);
            JIFRecaudacion.this.jtbl_tarjetas_pendientes.setEnabled(true);
            //JIFRecaudacion.access$6902(JIFRecaudacion.this, false);
            //JIFRecaudacion.access$7002(JIFRecaudacion.this, false);
            buscavtabordo = false;
            buscavtasistema = false;
            JIFRecaudacion.this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>ENTER</font> Selecciona Tarjeta(s)</html>");
            JIFRecaudacion.this.defaultmodelo.setDataVector((Object[][])null, JIFRecaudacion.this.encabezadotp);
            JIFRecaudacion.this.tarjetasPendientes();
            JIFRecaudacion.this.habilitarDatosOperador();
            JIFRecaudacion.this.jpnl_tajetasPendientes.setVisible(false);
            JIFRecaudacion.this.jpnl_tajetasPendientes2.setVisible(true);
            JIFRecaudacion.this.defaultmodelo.setDataVector((Object[][])null, JIFRecaudacion.this.encabezadotp);
            JIFRecaudacion.this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Recaudacion | <font color=FF0000>ENTER</font> Busca Tarjetas | <font color=FF0000> « » </font> Moverse entre Campos | <font color=FF0000>F11</font> Cancelar Pago | <font color=FF0000>F12</font> Recoleccion  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
            JIFRecaudacion.this.resizeColumnasTP();
            JIFRecaudacion.this.limpiarDescripcion();
            JIFRecaudacion.this.jtxt_claveOperador.requestFocus();
            return;
          }

          tarjeta.setEstadoTarjetaId(BigInteger.valueOf(Long.valueOf(edotar.get(0).toString()).longValue()));
          tarjeta.setUltimaActualizacionPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
          tarjeta.setUltimaFechaActualizacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
          tarjeta.setReplicacionOrigen("CENTRAL");
          JIFRecaudacion.this.busquedas.tarjetasViajeTblFacadeRemote.edit(tarjeta);

          if (exp)
            continue;
          Vector vgastosUnaTarjeta = (Vector)JIFRecaudacion.this.vgastos_tarjetas.get(i);
          String[][] camposGastos = new String[20][4];
          camposGastos = (String[][])(String[][])vgastosUnaTarjeta.get(0);

          Vector vgastosUnaTarjetaEmpre = (Vector)JIFRecaudacion.this.vgastos_tarjetasEmpre.get(i);
          String[][] camposGastosEmpre = new String[20][4];
          camposGastosEmpre = (String[][])(String[][])vgastosUnaTarjetaEmpre.get(0);

          for (int j = 0; j < JIFRecaudacion.this.jtbl_gastos_operador.getRowCount(); j++)
          {
            String valor = camposGastos[j][1];
            String valorv = "";
            boolean guardar = true;
            if (!valor.equals("Comprobable")) {
              double val = 0.0D;
              try {
                val = Double.valueOf(valor).doubleValue();
                if (val <= 0.0D)
                  guardar = false;
                else
                  valorv = "" + val;
              } catch (NumberFormatException ex) {
                valorv = "" + val;
                guardar = true;
              }
              if (!guardar)
                continue;
              TmsRecaudacionLineasTbl gasto = new TmsRecaudacionLineasTbl();
              TmsRecaudacionGastosTbl gastoId = JIFRecaudacion.this.busquedas.recaudacionGastosTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(JIFRecaudacion.this.jtbl_gastos_operador.getValueAt(j, 3).toString()).longValue()));

              gasto.setRecaudacionId(recaudacionNueva);
              gasto.setRecaudacionGastoId(gastoId);
              gasto.setTipoMovimiento("GASTO");
              gasto.setRecaudacionValor("" + valorv);
              gasto.setGastoImpuesto("0");
              gasto.setCreadoPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
              gasto.setFechaCreacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
              gasto.setUltimaActualizacionPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
              gasto.setUltimaFechaActualizacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
              JIFRecaudacion.this.busquedas.recaudacionLineasTblFacadeRemote.create(gasto, JIFRecaudacion.this.idTerminal);
            }

          }

          for (int j = 0; j < JIFRecaudacion.this.jtbl_gastos_empresa.getRowCount(); j++)
          {
            String valor = camposGastosEmpre[j][1];
            String valorv = "";
            boolean guardar = true;
            if (!valor.equals("Comprobable")) {
              double val = 0.0D;
              try {
                val = Double.valueOf(valor).doubleValue();
                if (val <= 0.0D)
                  guardar = false;
                else
                  valorv = "" + val;
              } catch (NumberFormatException ex) {
                valorv = "" + val;
                guardar = true;
              }
              if (!guardar)
                continue;
              TmsRecaudacionLineasTbl gasto = new TmsRecaudacionLineasTbl();
              TmsRecaudacionGastosTbl gastoId = JIFRecaudacion.this.busquedas.recaudacionGastosTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(JIFRecaudacion.this.jtbl_gastos_empresa.getValueAt(j, 3).toString()).longValue()));
              gasto.setRecaudacionId(recaudacionNueva);
              gasto.setRecaudacionGastoId(gastoId);
              gasto.setTipoMovimiento("GASTO");
              gasto.setRecaudacionValor("" + valorv);
              gasto.setGastoImpuesto("0");
              gasto.setCreadoPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
              gasto.setFechaCreacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
              gasto.setUltimaActualizacionPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
              gasto.setUltimaFechaActualizacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
              JIFRecaudacion.this.busquedas.recaudacionLineasTblFacadeRemote.create(gasto, JIFRecaudacion.this.idTerminal);
            }

          }

          System.out.println("vgastosComprobados:");
          System.out.println("" + JIFRecaudacion.this.vgastosComprobados);
          if (JIFRecaudacion.this.vgastosComprobados.size() > 0)
          {
            for (int j = 0; j < JIFRecaudacion.this.vgastosComprobados.size(); j++)
            {
              Vector gastoComprobado = (Vector)JIFRecaudacion.this.vgastosComprobados.get(j);
              TmsRecaudacionLineasTbl gasto = new TmsRecaudacionLineasTbl();
              TmsRecaudacionGastosTbl gastoId = JIFRecaudacion.this.busquedas.recaudacionGastosTblFacadeRemote.find(BigDecimal.valueOf(Long.valueOf(gastoComprobado.get(5).toString()).longValue()));
              gasto.setRecaudacionId(recaudacionNueva);
              gasto.setRecaudacionGastoId(gastoId);
              gasto.setTipoMovimiento("COMPROBADO");
              gasto.setRecaudacionValor(gastoComprobado.get(4).toString());
              gasto.setGastoImpuesto(gastoComprobado.get(1).toString());
              gasto.setCreadoPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
              gasto.setFechaCreacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
              gasto.setUltimaActualizacionPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
              gasto.setUltimaFechaActualizacion(new Date(JIFRecaudacion.this.fecha_servidor.getTime()));
              JIFRecaudacion.this.busquedas.recaudacionLineasTblFacadeRemote.create(gasto, JIFRecaudacion.this.idTerminal);
            }

          }

          int indexfun = JIFRecaudacion.this.funciones.indexOf("6001");
          if (indexfun < 0)
            continue;
          String auditable = JIFRecaudacion.this.fauditables.get(indexfun).toString().toUpperCase();
          if ((!auditable.equals("S")) && (!auditable.equals("Y")))
            continue;
          TmsAuditoriaTbl auditoria = new TmsAuditoriaTbl();
          auditoria.setUsuarioId(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
          auditoria.setNombreEquipo(JIFRecaudacion.this.getNombre_equipo());
          auditoria.setFuncionNumero(BigInteger.valueOf(6001L));
          auditoria.setFecha(JIFRecaudacion.this.fecha_servidor);
          auditoria.setEstadoProceso("EXITO");
          auditoria.setDescripcionProceso("Se Recaudó la tarjeta de viaje " + JIFRecaudacion.this.tarjetas_recaudadas[i][2]);
          JIFRecaudacion.this.busquedas.auditoriaTblFacadeRemote.create(auditoria, JIFRecaudacion.this.idTerminal);
        }

        dispose();
        JIFRecaudacion.this.jtbl_tarjetas_pendientes.setEnabled(true);

        JIFRecaudacion.this.sumaAcumulado(Double.valueOf(JIFRecaudacion.this.jlbl_total_pagar.getText()).doubleValue());
        JIFRecaudacion.this.sumaAcumulado(JIFRecaudacion.this.vtaAbordoManual);
        //JIFRecaudacion.access$1802(JIFRecaudacion.this, 0.0D);
        vtaAbordoManual = 0;
        System.out.println("ntarjetas: " + this.ntarjetas + "   tarRecAnt: " + JIFRecaudacion.this.tarRecAnt.size());
        if (this.ntarjetas > JIFRecaudacion.this.tarRecAnt.size())
        {
          if ((this.ntarjetas == 1) && (exp2))
            JIFRecaudacion.this.imprimir_recibo(this.datos, "" + ticket, this.ntarjetas);
          else {
            JIFRecaudacion.this.imprimir_recibo(this.datos, "" + nr, this.ntarjetas);
          }
        }
        JIFRecaudacion.this.limpiarDescripcion();
        JIFRecaudacion.this.jpnl_tarjetaDescripcion.setVisible(false);
        JIFRecaudacion.this.jpnl_tarjetaDescripcion2.setVisible(true);
        JIFRecaudacion.this.jtbl_tarjetas_pendientes.setEnabled(true);
        //JIFRecaudacion.access$6902(JIFRecaudacion.this, false);
        //JIFRecaudacion.access$7002(JIFRecaudacion.this, false);
        buscavtabordo = false;
        buscavtasistema = false;
        JIFRecaudacion.this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>ENTER</font> Selecciona Tarjeta(s)</html>");

        JIFRecaudacion.this.defaultmodelo.setDataVector((Object[][])null, JIFRecaudacion.this.encabezadotp);
        JIFRecaudacion.this.tarjetasPendientes();
      }
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
      Vector x = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.fechaServidor();
      //JIFRecaudacion.access$4702(JIFRecaudacion.this, Timestamp.valueOf(x.get(0).toString()));
      fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pf.getImageableX(), pf.getImageableY());
      Font fontVar = new Font("Arial", 0, 9);
      g.setFont(fontVar);
      int lineaNueva = 11; int i = 1;
      g.drawString("AUTOBUSES MEX-PUE ESTRELLA ROJA", 10, lineaNueva);
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
      g.drawString("" + formatf.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())), 125, lineaNueva * i);
      i++;
      g.drawString("HORA DE RECOLECCION : ", 0, lineaNueva * i);
      g.drawString("" + formath.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())), 125, lineaNueva * i);
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
      try {
        int indeximp = JIFRecaudacion.this.formatos.indexOf("TICKETS");
        PrintService impresora = (PrintService)JIFRecaudacion.this.impresoras.get(indeximp);
        job.setPrintService(impresora);

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
      } catch (Exception e) {
        e.printStackTrace();
      }
      setModal(true);
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
          JIFRecaudacion.vista_pago_impresion_recolecciones.this.jbtn_si_actionPerformed(e);
        }
      });
      this.jbtn_si.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          JIFRecaudacion.vista_pago_impresion_recolecciones.this.jbtn_si_keyPressed(e);
        }
      });
      this.jbtn_no.setText("No");
      this.jbtn_no.setBounds(new Rectangle(205, 365 + this.alto, 50, 25));
      this.jbtn_no.setMnemonic('n');
      this.jbtn_no.setFont(new Font("Arial", 1, 12));
      this.jbtn_no.setHorizontalTextPosition(0);
      this.jbtn_no.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JIFRecaudacion.vista_pago_impresion_recolecciones.this.jbtn_no_actionPerformed(e);
        }
      });
      this.jbtn_no.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          JIFRecaudacion.vista_pago_impresion_recolecciones.this.jbtn_no_keyPressed(e);
        }
      });
      Vector x = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.fechaServidor();
      //JIFRecaudacion.access$4702(JIFRecaudacion.this, Timestamp.valueOf(x.get(0).toString()));
      fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
      this.jLabel2.setBounds(new Rectangle(60, 315 + this.alto, 35, 45));
      this.jLabel2.setIcon(this.imagen_pregunta);
      this.jTextArea1.append(" \nNo Paquete.: " + this.datos[0] + "\n\n");
      this.jTextArea1.append(" Concepto             :   " + this.datos[2] + "\n");
      this.jTextArea1.append(" Importe                 :   " + this.datos[3] + "\n\n");
      String total_letras = "    ( " + new cantidad_a_letras().toLetras(Double.valueOf(this.datos[3]).longValue()) + "Pesos 00/M.N. )";
      this.datos[7] = total_letras;
      this.jTextArea1.append(total_letras + "\n\n");

      //this.datos[8] = JIFRecaudacion.access$4800(JIFRecaudacion.this).get(1).toString();
      datos[8] = datosIniciales.get(1).toString();
      this.jTextArea1.append(" Recaudador       :   " + this.datos[8] + "-" + this.datos[1] + "\n");
      //this.datos[6] = JIFRecaudacion.access$4800(JIFRecaudacion.this).get(2).toString();
      datos[6] = datosIniciales.get(2).toString();///lu.get(0).getDescripcion();

      this.jTextArea1.append(" Supervisor          :   " + this.datos[5] + "-" + this.datos[6] + "\n");
      SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
      this.jTextArea1.append(" Fecha de Recoleccion:  " + formatf.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())) + "\n");
      this.jTextArea1.append(" Hora de Recoleccion   : " + formath.format(new Date(JIFRecaudacion.this.fecha_servidor.getTime())) + "\n\n");
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
      boolean recol = ingresarRecoleccion(JIFRecaudacion.this.montorecoleccion, JIFRecaudacion.this.autorizado);
      if (recol)
      {
        dispose();

        this.datos[0] = this.numpaquete;
        JIFRecaudacion.this.imprimir_recibo_rec(this.datos, this.datos[0]);
      }
      else
      {
        JOptionPane.showMessageDialog(this, "¡No se registro la recoleccion! ", "Recolecciona no registrada", 0);
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
    private boolean ingresarRecoleccion(float recoCantidad, String numeroSup) { System.out.println("El supervisor es: " + numeroSup);
      BigInteger idSupervisor = BigInteger.valueOf(Long.valueOf(numeroSup).longValue());
      Vector x = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.fechaServidor();
      fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
      //JIFRecaudacion.access$4702(JIFRecaudacion.this, Timestamp.valueOf(x.get(0).toString()));

      Timestamp tmp = JIFRecaudacion.this.fecha_servidor;
      TmsActividadesSesionTbl actRecoleccion;
      try { actRecoleccion = JIFRecaudacion.this.busquedas.actividadesSesionTblFacadeRemote.getActividadPorClave("RECOL");
      } catch (ActividadNoEncontradoException ex) {
        System.out.println(ex.getMessage());
        return false;
      }

      TmsSesionActividadesTbl sesionActividad = new TmsSesionActividadesTbl();
      sesionActividad.setCorteId(JIFRecaudacion.this.corteActual);
      sesionActividad.setActividadSesionId(actRecoleccion);

      Vector npaq = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.queryBuscaNumeroPaqueteActual();
      System.out.println("Esta es el numero actual del paquete: " + npaq.get(0).toString());
      sesionActividad.setValorActividad("" + (Integer.valueOf(npaq.get(0).toString()).intValue() + 1));
      sesionActividad.setFechaHoraActividad(tmp);
      sesionActividad.setAutorizadoPor(idSupervisor);

      Vector vide = (Vector)JIFRecaudacion.this.busquedas.variosFacadeRemote.queryBuscaIdEmpresaByNombre("AMPERSA");
      long empresaid = Long.valueOf(vide.get(0).toString()).longValue();
      TmsEmpresasTbl empre = JIFRecaudacion.this.busquedas.variosFacadeRemote.queryBuscaEmpresaPorId(BigDecimal.valueOf(empresaid));
      empre = new TmsEmpresasTbl();
      empre.setEmpresaId(BigDecimal.valueOf(empresaid));
      sesionActividad.setEmpresaId(empre);
      sesionActividad.setCreadoPor(JIFRecaudacion.this.getIdusuario());
      sesionActividad.setFechaCreacion(tmp);
      sesionActividad.setUltimaActualizacionPor(JIFRecaudacion.this.getIdusuario());
      sesionActividad.setUltimaFechaActualizacion(tmp);
      sesionActividad = JIFRecaudacion.this.busquedas.sesionActFacateRemote.insertarSesionActividad(sesionActividad, JIFRecaudacion.this.idTerminal);
      this.numpaquete = sesionActividad.getValorActividad();
      System.out.println("RECOLECCION:" + sesionActividad.getValorActividad());

      TmsVtaTiposPagoTbl tipoPago = JIFRecaudacion.this.busquedas.vtaTiposPagoTblFacadeRemote.obtenerPagoPorClave("EFE");

      TmsRecoleccionesTbl recoleccion = new TmsRecoleccionesTbl();
      recoleccion.setSesionActividadId(sesionActividad.getSesionActividadId().toBigInteger());
      recoleccion.setTipoPagoId(tipoPago);
      recoleccion.setCantidad(new BigInteger("1"));
      recoleccion.setMonto(new BigDecimal(recoCantidad));
      recoleccion.setReferencia(tipoPago.getClave());
      recoleccion.setAutorizadoPor(idSupervisor);
      recoleccion.setCreadoPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
      recoleccion.setFechaCreacion(tmp);
      recoleccion.setUltimaActualizacionPor(BigInteger.valueOf(JIFRecaudacion.this.getIdusuario()));
      recoleccion.setUltimaFechaActualizacion(tmp);
      JIFRecaudacion.this.busquedas.recoleccionFacateRemote.create(recoleccion, JIFRecaudacion.this.idTerminal);

      System.out.println("recoleccion:" + recoleccion.getReferencia());
      double mr = Double.valueOf("" + JIFRecaudacion.this.montorecoleccion).doubleValue();
      if (JIFRecaudacion.this.acumulado >= mr) {
        JIFRecaudacion.this.setAcumulado(JIFRecaudacion.this.acumulado - mr);
      }

      return true;
    }
  }

  class jdlg_ventas0 extends JDialog
  {
    private JScrollPane jScrollPane1;
    private JLabel jlbl_barraestadovta;
    private JTable jtbl_ventas;
    private Object[] encabezadovtas = { "FOLIO TAR", "ORIGEN", "DESTINO", "CANTIDAD", "FOL. INICIAL", "FOL. FINAL", "TOTAL" };
    private DefaultTableModel modelovtas = new DefaultTableModel((Object[][])null, this.encabezadovtas)
    {
      public boolean isCellEditable(int row, int column)
      {
        return (column != 1) && (column != 2);
      }
    };

    private String tipo = "";

    public jdlg_ventas0(String titulo, String ptipo)
    {
      setTitle(titulo);
      initComponents();
      setModal(true);

      setAlwaysOnTop(true);
      setResizable(false);
      this.tipo = ptipo;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = getSize();
      setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
      Object[][] valoresv = new Object[1][5];
      this.jtbl_ventas.setModel(this.modelovtas);

      this.modelovtas.setDataVector(valoresv, this.encabezadovtas);
      this.jlbl_barraestadovta.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> ENTER</font> Siguiente | <font color=FF0000>INSERT</font> Nueva Venta | <font color=FF0000>DEL</font> Eliminar Venta | <font color=FF0000>F10</font> Registrar Venta </html>");
      if (JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRowCount() == 1)
      {
        this.jtbl_ventas.setValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRow(), 0), 0, 0);
        this.jtbl_ventas.setValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRow(), 2), 0, 1);
        this.jtbl_ventas.setValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRow(), 3), 0, 2);
        this.jtbl_ventas.setValueAt("", 0, 3);
        this.jtbl_ventas.setValueAt("", 0, 4);
        this.jtbl_ventas.setValueAt("", 0, 5);
        this.jtbl_ventas.setValueAt("", 0, 6);
        this.jtbl_ventas.setRowSelectionInterval(0, 0);
        this.jtbl_ventas.setColumnSelectionInterval(3, 3);
        this.jlbl_barraestadovta.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> ENTER</font> Siguiente | <font color=FF0000>INSERT</font> Nueva Venta | <font color=FF0000>DEL</font> Eliminar Venta |  | <font color=FF0000>F5</font> Detallado  <font color=FF0000>F10</font> Registrar Venta </html>");
        //JIFRecaudacion.access$1002(JIFRecaudacion.this, false);
        insertar = false;
        resizeColumnasVenta();
      }
      else
      {
        //JIFRecaudacion.access$1002(JIFRecaudacion.this, true);
        insertar = true;
        this.jtbl_ventas.setValueAt("", 0, 0);
        this.jtbl_ventas.setValueAt("", 0, 1);
        this.jtbl_ventas.setValueAt("", 0, 2);
        this.jtbl_ventas.setValueAt("", 0, 3);
        this.jtbl_ventas.setValueAt("", 0, 4);
        this.jtbl_ventas.setValueAt("", 0, 5);
        this.jtbl_ventas.setValueAt("", 0, 6);
        this.jtbl_ventas.setRowSelectionInterval(0, 0);
        this.jtbl_ventas.setColumnSelectionInterval(0, 0);
        resizeColumnasVenta();
      }
    }

    private void resizeColumnasVenta()
    {
      TableColumn columinv = this.jtbl_ventas.getColumnModel().getColumn(0); columinv.setMinWidth(200); columinv.setMaxWidth(200); columinv.setPreferredWidth(200);
    }

    private void initComponents()
    {
      this.jScrollPane1 = new JScrollPane();
      this.jtbl_ventas = new JTable();

      this.jtbl_ventas.getInputMap(1).put(KeyStroke.getKeyStroke(39, 0), "none");
      this.jtbl_ventas.getInputMap(1).put(KeyStroke.getKeyStroke(37, 0), "none");

      this.jtbl_ventas.setCellSelectionEnabled(false);
      this.jlbl_barraestadovta = new JLabel();

      setDefaultCloseOperation(2);
      this.jtbl_ventas.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));

      this.jtbl_ventas.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFRecaudacion.jdlg_ventas0.this.jtbl_ventasKeyPressed(evt);
        }
        public void keyReleased(KeyEvent evt) {
          JIFRecaudacion.jdlg_ventas0.this.jtbl_ventasKeyReleased(evt);
        }
      });
      this.jScrollPane1.setViewportView(this.jtbl_ventas);

      this.jlbl_barraestadovta.setFont(new Font("Tahoma", 1, 12));
      this.jlbl_barraestadovta.setForeground(new Color(102, 102, 102));

      this.jlbl_barraestadovta.setBorder(BorderFactory.createBevelBorder(1));

      GroupLayout layout = new GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(1).add(this.jScrollPane1, -1, 691, 32767).add(this.jlbl_barraestadovta, -1, 691, 32767));

      layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(this.jScrollPane1, -2, 184, -2).addPreferredGap(0, 52, 32767).add(this.jlbl_barraestadovta, -2, 35, -2)));

      pack();
    }

    private void jtbl_ventasKeyReleased(KeyEvent evt) {
      if (evt.getKeyCode() == 10)
      {
        if (this.jtbl_ventas.getRowCount() > 1)
        {
          int mas = 1;
          if (this.jtbl_ventas.getRowCount() == JIFRecaudacion.this.nrowactual + 1)
            mas = 0;
          int ncolumns = this.jtbl_ventas.getColumnCount();
          if (this.jtbl_ventas.getSelectedColumn() < ncolumns - 1)
          {
            this.jtbl_ventas.setRowSelectionInterval(JIFRecaudacion.this.nrowactual, JIFRecaudacion.this.nrowactual);
            this.jtbl_ventas.setColumnSelectionInterval(this.jtbl_ventas.getSelectedColumn() + mas, this.jtbl_ventas.getSelectedColumn() + mas);
          }
          else if (this.jtbl_ventas.getRowCount() == JIFRecaudacion.this.nrowactual + 1) {
            this.jtbl_ventas.setRowSelectionInterval(JIFRecaudacion.this.nrowactual, JIFRecaudacion.this.nrowactual);
          }
          else {
            this.jtbl_ventas.setRowSelectionInterval(JIFRecaudacion.this.nrowactual, JIFRecaudacion.this.nrowactual);
            this.jtbl_ventas.setColumnSelectionInterval(0, 0);
          }

        }

        if (this.jtbl_ventas.getSelectedColumn() == 1)
        {
          boolean noexiste = true;
          boolean listada = false;
          String valor0 = (String)this.jtbl_ventas.getValueAt(this.jtbl_ventas.getSelectedRow(), 0);
          for (int i = 0; i < this.jtbl_ventas.getRowCount(); i++)
          {
            if ((!this.jtbl_ventas.getValueAt(i, 0).toString().equals(valor0)) || (this.jtbl_ventas.getSelectedRow() == i))
              continue;
            listada = true;
            break;
          }

          if (!valor0.equals(""))
          {
            int nselecciones = JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRowCount();
            int[] selecciones = JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRows();
            for (int k = 0; k < nselecciones; k++)
            {
              if (!valor0.equals(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getValueAt(selecciones[k], 0).toString()))
                continue;
              this.jtbl_ventas.setValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getValueAt(selecciones[k], 2), this.jtbl_ventas.getSelectedRow(), 1);
              this.jtbl_ventas.setValueAt(JIFRecaudacion.this.jtbl_tarjetas_pendientes.getValueAt(selecciones[k], 3), this.jtbl_ventas.getSelectedRow(), 2);
              this.jtbl_ventas.setRowSelectionInterval(this.jtbl_ventas.getSelectedRow(), this.jtbl_ventas.getSelectedRow());
              this.jtbl_ventas.setColumnSelectionInterval(3, 3);
              this.jlbl_barraestadovta.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> ENTER</font> Siguiente | <font color=FF0000>INSERT</font> Nueva Venta | <font color=FF0000>DEL</font> Eliminar Venta | <font color=FF0000>F5</font> Detallado  <font color=FF0000>F10</font> Registrar Venta </html>");
              noexiste = false;
            }

            if ((noexiste) || (listada))
            {
              this.jtbl_ventas.setValueAt("", this.jtbl_ventas.getSelectedRow(), 0);
              this.jtbl_ventas.setValueAt("", this.jtbl_ventas.getSelectedRow(), 1);
              this.jtbl_ventas.setValueAt("", this.jtbl_ventas.getSelectedRow(), 2);
              this.jtbl_ventas.setValueAt("", this.jtbl_ventas.getSelectedRow(), 3);
              this.jtbl_ventas.setValueAt("", this.jtbl_ventas.getSelectedRow(), 4);
              this.jtbl_ventas.setValueAt("", this.jtbl_ventas.getSelectedRow(), 5);
              this.jtbl_ventas.setValueAt("", this.jtbl_ventas.getSelectedRow(), 6);
              this.jtbl_ventas.setRowSelectionInterval(this.jtbl_ventas.getSelectedRow(), this.jtbl_ventas.getSelectedRow());
              this.jtbl_ventas.setColumnSelectionInterval(0, 0);
            }
          }
        }
      }

      if ((evt.getKeyCode() == 38) || (evt.getKeyCode() == 40))
      {
        if (this.jtbl_ventas.getRowCount() > 0) {
            nrowactual = jtbl_ventas.getSelectedRow();
            //JIFRecaudacion.access$1302(JIFRecaudacion.this, this.jtbl_ventas.getSelectedRow());
        }
      }
      if (evt.getKeyCode() == 121)
      {
        if (this.tipo.equals("MANUAL"))
        {
          if ((this.jtbl_ventas.getValueAt(0, 3).toString().equals("")) || (this.jtbl_ventas.getValueAt(0, 6).toString().equals("")))
          {
            new jdlg_error("¡Debes introducir el monto!", "", "Error de datos").setVisible(true);
            return;
          }
          if ((!this.jtbl_ventas.getValueAt(0, 3).toString().equals("")) && (!this.jtbl_ventas.getValueAt(0, 6).toString().equals("")) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 3).toString()).floatValue() > 0.0F) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 6).toString()).floatValue() == 0.0F))
          {
            new jdlg_error("¡Debes introducir el monto total de los boletos!", "", "Error de datos").setVisible(true);
            return;
          }

          if ((!this.jtbl_ventas.getValueAt(0, 3).toString().equals("")) && (!this.jtbl_ventas.getValueAt(0, 6).toString().equals("")) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 3).toString()).floatValue() == 0.0F) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 6).toString()).floatValue() > 0.0F))
          {
            new jdlg_error("¡Debes introducir la cantidad de boletos!", "", "Error de datos").setVisible(true);
            return;
          }

          long nbol = 0L;
          float monto = 0.0F;
          for (int i = 0; i < this.jtbl_ventas.getRowCount(); i++)
          {
            nbol += Long.valueOf(this.jtbl_ventas.getValueAt(i, 3).toString()).longValue();
            monto += Float.valueOf(this.jtbl_ventas.getValueAt(i, 6).toString()).floatValue();
            for (int nt = 1; nt <= JIFRecaudacion.this.ntarjetas_recaudadas; nt++)
            {
              if (!this.jtbl_ventas.getValueAt(i, 0).toString().equals(JIFRecaudacion.this.tarjetas_recaudadas[nt][2]))
                continue;
              JIFRecaudacion.this.tarjetas_recaudadas[nt][12] = ("" + this.jtbl_ventas.getValueAt(i, 3).toString());
              JIFRecaudacion.this.tarjetas_recaudadas[nt][13] = ("" + this.jtbl_ventas.getValueAt(i, 6).toString());

              break;
            }
          }

          JIFRecaudacion.this.jlbl_boletosManual.setText("" + nbol);
          JIFRecaudacion.this.jlbl_ventaManual.setText("" + monto);
        }
        if (this.tipo.equals("ABORDO"))
        {
          if (this.jtbl_ventas.getValueAt(0, 6).toString().equals(""))
          {
            new jdlg_error("¡Debes introducir el monto!", "", "Error de datos").setVisible(true);
            return;
          }
          if ((this.jtbl_ventas.getValueAt(0, 5).toString().equals("")) || (this.jtbl_ventas.getValueAt(0, 4).toString().equals("")))
          {
            new jdlg_error("¡Debes introducir el Folio inicial y el Folio Final!", "", "Error de datos").setVisible(true);
            return;
          }
          int foli = Integer.valueOf(this.jtbl_ventas.getValueAt(0, 4).toString()).intValue();
          int folf = Integer.valueOf(this.jtbl_ventas.getValueAt(0, 5).toString()).intValue();
          if (folf < foli)
          {
            new jdlg_error("¡El Folio Final debe ser mayor o igual al Folio Inicial!", "", "Error de datos").setVisible(true);
            return;
          }
          int can = Integer.valueOf(this.jtbl_ventas.getValueAt(this.jtbl_ventas.getSelectedRow(), 3).toString()).intValue();
          int folios = folf - foli + 1;
          if (folios != can)
          {
            new jdlg_error("¡La cantidad de boletos debe coincidir con los Folios!", "", "Error de datos").setVisible(true);
            return;
          }
          if ((!this.jtbl_ventas.getValueAt(0, 3).toString().equals("")) && (!this.jtbl_ventas.getValueAt(0, 6).toString().equals("")) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 3).toString()).floatValue() > 0.0F) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 6).toString()).floatValue() == 0.0F))
          {
            new jdlg_error("¡Debes introducir el monto total de los boletos!", "", "Error de datos").setVisible(true);
            return;
          }

          if ((!this.jtbl_ventas.getValueAt(0, 3).toString().equals("")) && (!this.jtbl_ventas.getValueAt(0, 6).toString().equals("")) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 3).toString()).floatValue() == 0.0F) && (Float.valueOf(this.jtbl_ventas.getValueAt(0, 6).toString()).floatValue() > 0.0F))
          {
            new jdlg_error("¡Debes introducir la cantidad de boletos!", "", "Error de datos").setVisible(true);
            return;
          }
          //JIFRecaudacion.access$1802(JIFRecaudacion.this, Double.valueOf(this.jtbl_ventas.getValueAt(0, 6).toString()).doubleValue());
          vtaAbordoManual = Double.valueOf(jtbl_ventas.getValueAt(0,6).toString());
          JIFRecaudacion.this.jlbl_boletosAbordo.setText("" + (folf - foli + 1));
          JIFRecaudacion.this.jlbl_ventaAbordo.setText(this.jtbl_ventas.getValueAt(0, 6).toString());
          JIFRecaudacion.this.tarjetas_recaudadas[1][21] = ("" + this.jtbl_ventas.getValueAt(0, 4).toString());
          JIFRecaudacion.this.tarjetas_recaudadas[1][22] = ("" + this.jtbl_ventas.getValueAt(0, 5).toString());
        }

        dispose();
      }
    }

    private void jtbl_ventasKeyPressed(KeyEvent evt) {
      if (evt.getKeyCode() == 10)
      {
        if (this.jtbl_ventas.getSelectedColumn() + 1 == 3) {
          this.jlbl_barraestadovta.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> ENTER</font> Siguiente | <font color=FF0000>INSERT</font> Nueva Venta | <font color=FF0000>DEL</font> Eliminar Venta | <font color=FF0000>F5</font> Detallado  <font color=FF0000>F10</font> Registrar Venta </html>");
        }
        else if (this.jtbl_ventas.getSelectedColumn() + 1 == 6)
          this.jlbl_barraestadovta.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> ENTER</font> Siguiente | <font color=FF0000>INSERT</font> Nueva Venta | <font color=FF0000>DEL</font> Eliminar Venta | <font color=FF0000>F5</font> Sumadora  | <font color=FF0000>F10</font> Registrar Venta </html>");
        else {
          this.jlbl_barraestadovta.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000> ENTER</font> Siguiente | <font color=FF0000>INSERT</font> Nueva Venta | <font color=FF0000>DEL</font> Eliminar Venta | <font color=FF0000>F10</font> Registrar Venta </html>");
        }
      }

      if (evt.getKeyCode() == 27) {
        dispose();
      }
      if ((evt.getKeyCode() == 116) && (this.jtbl_ventas.getSelectedColumn() == 6))
      {
        Dialog_Manual_Sumadora sumadora = null;
        if ((this.jtbl_ventas.getValueAt(this.jtbl_ventas.getSelectedRow(), 3).equals("")) || (Integer.valueOf(this.jtbl_ventas.getValueAt(this.jtbl_ventas.getSelectedRow(), 3).toString()).intValue() == 0)) {
          this.jlbl_barraestadovta.setText(" Debes introducir la cantidad de boletos");
        }
        else {
          this.jtbl_ventas.editCellAt(0, 5);
          setAlwaysOnTop(false);
          sumadora = new Dialog_Manual_Sumadora(Integer.valueOf(this.jtbl_ventas.getValueAt(this.jtbl_ventas.getSelectedRow(), 3).toString()).intValue());
          sumadora.setVisible(true);
          this.jtbl_ventas.editCellAt(0, 6);
          setAlwaysOnTop(true);
        }

      }

      if ((evt.getKeyCode() == 116) && (this.jtbl_ventas.getSelectedColumn() == 3))
      {
        Dialog_Manual_SuperRapidos dm = new Dialog_Manual_SuperRapidos("Captura de Venta Manual", this.jtbl_ventas.getValueAt(this.jtbl_ventas.getSelectedRow(), 0).toString(), this.jtbl_ventas.getValueAt(this.jtbl_ventas.getSelectedRow(), 1).toString());
        dm.setVisible(true);
      }

      if (evt.getKeyCode() == 155)
      {
        boolean llenos = true;
        for (int i = 0; i < this.jtbl_ventas.getRowCount(); i++)
        {
          if ((!this.jtbl_ventas.getValueAt(i, 0).toString().equals("")) && (!this.jtbl_ventas.getValueAt(i, 3).toString().equals("")) && (!this.jtbl_ventas.getValueAt(i, 6).toString().equals("")))
            continue;
          llenos = false;
          break;
        }

        if ((JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1) && (this.jtbl_ventas.getRowCount() < JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRowCount()) && (llenos))
        {
          int nr = this.jtbl_ventas.getRowCount();
          String[] row = { "", "", "", "", "", "", "" };
          this.modelovtas.addRow(row);
          if (this.jtbl_ventas.getRowCount() > 1)
          {
            this.jtbl_ventas.setRowSelectionInterval(this.jtbl_ventas.getRowCount() - 2, this.jtbl_ventas.getRowCount() - 2);
            this.jtbl_ventas.setColumnSelectionInterval(0, 0);
          }
          else
          {
            this.jtbl_ventas.setRowSelectionInterval(this.jtbl_ventas.getRowCount() - 1, this.jtbl_ventas.getRowCount() - 1);
            this.jtbl_ventas.setColumnSelectionInterval(0, 0);
          }
        }
        resizeColumnasVenta();
      }

      if (evt.getKeyCode() == 127)
      {
        if ((this.jtbl_ventas.getSelectedRow() >= 0) && (JIFRecaudacion.this.jtbl_tarjetas_pendientes.getSelectedRowCount() > 1))
        {
          this.modelovtas.removeRow(this.jtbl_ventas.getSelectedRow());
          if (this.jtbl_ventas.getRowCount() > 1)
          {
            this.jtbl_ventas.setRowSelectionInterval(this.jtbl_ventas.getRowCount() - 1, this.jtbl_ventas.getRowCount() - 1);
            this.jtbl_ventas.setColumnSelectionInterval(0, 0);
          }
        }
        resizeColumnasVenta();
      }
    }

    class Dialog_Manual_SuperRapidos extends JDialog
    {
      private JScrollPane jScrollPane1 = new JScrollPane();
      private boolean editable = false;
      private DefaultTableModel modelo_manual = new DefaultTableModel()
      {
        public boolean isCellEditable(int row, int column) {
          return JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.editable;
        }
      };

      private JTable jtbl_manual = new JTable(this.modelo_manual);
      private JButton jbtn_aceptar = new JButton();
      private JButton jbtn_cancelar = new JButton();
      private String Cadenaleida = "";
      private String foltar = "";
      private String origentar = "";
      private JButton jbtn_manual = new JButton();
      private JNumberTextField jtxt_capturacodigo = new JNumberTextField();
      private JButton jbtn_borrar = new JButton();
      private boolean respuesta = false;
      private JLabel jlbl_barraestado_captura = new JLabel();
      private JLabel jLabel1 = new JLabel();

      public Dialog_Manual_SuperRapidos(String t, String ft, String or)
      {
        setTitle(t);
        setModal(true);
        setAlwaysOnTop(true);
        this.origentar = or;
        this.foltar = ft;
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
          jbInit();
          this.jlbl_barraestado_captura.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>F2</font> Capturar con codigo de Barras | <font color=FF0000>DEL</font> Borra Boleto | <font color=FF0000>INSERT</font> Agregar Boletos | <font color=FF0000>F10</font> Registrar Boletos</html>");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      private void jbInit() throws Exception {
        getContentPane().setLayout(null);
        setSize(new Dimension(700, 288));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        this.jScrollPane1.setBounds(new Rectangle(0, 0, 690, 190));
        this.jbtn_aceptar.setText("Aceptar");
        this.jbtn_aceptar.setBounds(new Rectangle(355, 195, 80, 25));
        this.jbtn_aceptar.setMnemonic('A');
        this.jbtn_aceptar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_aceptar_actionPerformed(e);
          }
        });
        this.jbtn_aceptar.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_aceptar_keyPressed(e);
          }
        });
        this.jbtn_cancelar.setText("Cancelar");
        this.jbtn_cancelar.setBounds(new Rectangle(445, 195, 85, 25));
        this.jbtn_cancelar.setMnemonic('C');
        this.jbtn_cancelar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_cancelar_actionPerformed(e);
          }
        });
        this.jbtn_cancelar.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_cancelar_keyPressed(e);
          }
        });
        this.modelo_manual.addColumn("FOLIO BOL");
        this.modelo_manual.addColumn("ORIGEN");
        this.modelo_manual.addColumn("DESTINO");
        this.modelo_manual.addColumn("TIPO");
        this.modelo_manual.addColumn("IMPORTE");
        this.jtbl_manual.setFocusable(false);
        this.jtbl_manual.getInputMap(1).put(KeyStroke.getKeyStroke(27, 0), "none");
        this.jtbl_manual.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jtbl_manual_keyPressed(e);
          }
        });
        this.jtbl_manual.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jtbl_manual_focusGained(e);
          }
        });
        this.jtbl_manual.setSelectionMode(0);
        JNumberTextField celdao = new JNumberTextField();
        TableColumn HourColumno = this.jtbl_manual.getColumnModel().getColumn(0);
        HourColumno.setCellEditor(new DefaultCellEditor(celdao));
        HourColumno = this.jtbl_manual.getColumnModel().getColumn(4);
        HourColumno.setCellEditor(new DefaultCellEditor(celdao));
        this.jLabel1.setText("Codigo de Barras:");
        this.jLabel1.setBounds(new Rectangle(5, 195, 110, 25));
        this.jLabel1.setFont(new Font("Dialog", 1, 12));
        this.jlbl_barraestado_captura.setText(" Pasa el Lector por el Codigo de Barras del Boleto");
        this.jlbl_barraestado_captura.setBounds(new Rectangle(0, 225, 695, 35));
        this.jlbl_barraestado_captura.setBorder(BorderFactory.createBevelBorder(1));
        this.jlbl_barraestado_captura.setBackground(new Color(148, 148, 148));
        this.jlbl_barraestado_captura.setFont(new Font("Dialog", 1, 12));
        this.jlbl_barraestado_captura.setForeground(new Color(115, 115, 115));
        this.jbtn_manual.setText("Manual");
        this.jbtn_manual.setBounds(new Rectangle(620, 195, 75, 25));
        this.jbtn_manual.setToolTipText("null");
        this.jbtn_manual.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_manual_actionPerformed(e);
          }
        });
        this.jbtn_manual.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_manual_keyPressed(e);
          }
        });
        this.jtxt_capturacodigo.setBounds(new Rectangle(115, 195, 200, 25));
        this.jtxt_capturacodigo.setSize(new Dimension(120, 25));
        this.jtxt_capturacodigo.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jtxt_capturacodigo_keyPressed(e);
          }
        });
        this.jtxt_capturacodigo.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jtxt_capturacodigo_focusGained(e);
          }
        });
        this.jbtn_borrar.setText("Borrar");
        this.jbtn_borrar.setBounds(new Rectangle(540, 195, 71, 25));
        this.jbtn_borrar.setSize(new Dimension(71, 25));
        this.jbtn_borrar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_borrar_actionPerformed(e);
          }
        });
        this.jbtn_borrar.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this.jbtn_borrar_keyPressed(e);
          }
        });
        this.jScrollPane1.getViewport().add(this.jtbl_manual, null);
        getContentPane().add(this.jLabel1, null);
        getContentPane().add(this.jlbl_barraestado_captura, null);
        getContentPane().add(this.jbtn_borrar, null);
        getContentPane().add(this.jbtn_manual, null);
        getContentPane().add(this.jbtn_cancelar, null);
        getContentPane().add(this.jbtn_aceptar, null);
        getContentPane().add(this.jScrollPane1, null);
        getContentPane().add(this.jtxt_capturacodigo, null);
        this.jtxt_capturacodigo.requestFocus();
      }

      private void jbtn_aceptar_actionPerformed(ActionEvent e)
      {
        int boletosmanual = 0;
        int ventaamanual = 0;
        for (int i = 0; i < this.jtbl_manual.getRowCount(); i++) {
          if (this.jtbl_manual.getValueAt(i, 4) != null) {
            ventaamanual += Integer.parseInt((String)this.jtbl_manual.getValueAt(i, 4));

            boletosmanual++;
          }
        }

        JIFRecaudacion.jdlg_ventas0.this.jtbl_ventas.setValueAt("" + boletosmanual, JIFRecaudacion.jdlg_ventas0.this.jtbl_ventas.getSelectedRow(), 3);
        JIFRecaudacion.jdlg_ventas0.this.jtbl_ventas.setValueAt("" + ventaamanual, JIFRecaudacion.jdlg_ventas0.this.jtbl_ventas.getSelectedRow(), 6);

        dispose();
      }

      private void jbtn_cancelar_actionPerformed(ActionEvent e) {
        dispose();
      }

      private void jbtn_manual_actionPerformed(ActionEvent e) {
        this.jlbl_barraestado_captura.setText("Introduce un numero entero y presiona Enter");
        String dato = JOptionPane.showInputDialog(this, "¿Numero de Boletos a capturar?", "Captura de Boletos Manualmente", 3);

        if (dato != null)
          try {
            int nrenglones = Integer.parseInt(dato);
            if (nrenglones > 0) {
              this.editable = true;
              this.jtxt_capturacodigo.setEnabled(false);
              this.jtbl_manual.setFocusable(true);
              String[] newrow = new String[6];
              for (int i = 1; i <= nrenglones; i++)
                this.modelo_manual.addRow(newrow);
              this.modelo_manual.fireTableRowsInserted(this.jtbl_manual.getRowCount() - nrenglones, this.jtbl_manual.getRowCount() - 1);
              this.jlbl_barraestado_captura.setText(" Captura los datos y presiona ENTER despues de cada Renglon   ESC: Salir");
              this.jtbl_manual.requestFocus();
              this.jtbl_manual.setRowSelectionInterval(this.jtbl_manual.getRowCount() - nrenglones, this.jtbl_manual.getRowCount() - nrenglones);

              this.jtbl_manual.setColumnSelectionInterval(0, 0);
            }
            else {
              new jdlg_error("¡Debes Introducir un numero mayor a 0!   ", "", "¡Eror de datos!").setVisible(true);
              if (this.jtxt_capturacodigo.isEnabled())
                this.jlbl_barraestado_captura.setText(" Pasa el Lector por el Codigo de Barras del Boleto");
              else
                this.jlbl_barraestado_captura.setText("Terminar: Presiona Aceptar       Borar: Secciona una fila y presiona Borrar");
            }
          }
          catch (NumberFormatException en) {
            new jdlg_error("¡Debes Introducir un numero entero!   ", "", "¡Eror de datos!").setVisible(true);
            if (this.jtxt_capturacodigo.isEnabled())
              this.jlbl_barraestado_captura.setText(" Pasa el Lector por el Codigo de Barras del Boleto");
            else
              this.jlbl_barraestado_captura.setText("Terminar: Presiona Aceptar       Borar: Secciona una fila y presiona Borrar");
          }
        else
          this.jlbl_barraestado_captura.setText(" Pasa el Lector por el Codigo de Barras del Boleto");
      }

      private void jbtn_borrar_actionPerformed(ActionEvent e) {
        if (this.jtbl_manual.getSelectedRow() >= 0)
        {
          String folio;
          if (this.jtbl_manual.getValueAt(this.jtbl_manual.getSelectedRow(), 0) == null)
            folio = "nada";
          else
            folio = "" + this.jtbl_manual.getValueAt(this.jtbl_manual.getSelectedRow(), 0);
          Jdlg_Pregunta_Borrar np = new Jdlg_Pregunta_Borrar("Borrar Boleto", "" + (this.jtbl_manual.getSelectedRow() + 1), folio);
          np.setVisible(true);
          if (this.respuesta)
          {
            if (this.jtbl_manual.getRowCount() == 1)
            {
              this.jtbl_manual.setFocusable(false);
              this.jtxt_capturacodigo.setEnabled(true);
            }
            int nrd = this.jtbl_manual.getSelectedRow();
            this.modelo_manual.removeRow(nrd);
            this.modelo_manual.fireTableRowsDeleted(nrd, nrd);
            if (this.jtbl_manual.getRowCount() > 0)
            {
              this.jtbl_manual.setRowSelectionInterval(0, 0);
              this.jtbl_manual.setColumnSelectionInterval(0, 0);
            }
          }

        }
        else if (this.jtbl_manual.getRowCount() > 0)
        {
          new jdlg_error("¡Debes Seleccionar un renglon!   ", "", "¡Ningun renglon Seleccionado!").setVisible(true);
        }
        else
        {
          new jdlg_error("¡Debes capturar boletos primero!   ", "", "¡No hay boletos capturados!").setVisible(true);
        }
      }

      private void jtxt_capturacodigo_keyPressed(KeyEvent e)
      {
        if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
          this.jtbl_manual.requestFocus();
        if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
        {
          this.jlbl_barraestado_captura.setText("Terminar: Presiona Aceptar       Borar: Secciona una fila y presiona Borrar"); this.jbtn_aceptar.requestFocus();
          this.jbtn_aceptar.requestFocus();
        }
        if (e.getKeyCode() == 10) {
          this.jtbl_manual.setFocusable(true);
          this.Cadenaleida = this.jtxt_capturacodigo.getText();
          this.jtxt_capturacodigo.setText("");
          if (this.Cadenaleida.length() < 13)
          {
            new jdlg_error("Codigo incorrecto   ", "", "¡Eror de datos!").setVisible(true);
            this.Cadenaleida = "";
            this.jtxt_capturacodigo.requestFocus();
          } else {
            int nrow = this.jtbl_manual.getRowCount();
            boolean repetido = false;
            for (int i = 0; i < nrow; i++) {
              if (!this.Cadenaleida.substring(3, 9).equals(this.jtbl_manual.getValueAt(i, 0))) {
                continue;
              }
              repetido = true;
              break;
            }

            if (repetido)
            {
              new jdlg_error("Folio Duplicado   ", "", "¡Eror de datos!").setVisible(true);
              this.Cadenaleida = "";
              repetido = false;
              this.jtxt_capturacodigo.requestFocus();
            } else {
              String[] r = new String[6];
              this.modelo_manual.addRow(r);
              this.jtbl_manual.setValueAt(this.Cadenaleida.substring(3, 9), this.jtbl_manual.getRowCount() - 1, 0);

              this.jtbl_manual.setValueAt(this.origentar, this.jtbl_manual.getRowCount() - 1, 1);

              this.jtbl_manual.setValueAt(this.Cadenaleida.substring(9, 12), this.jtbl_manual.getRowCount() - 1, 2);

              this.jtbl_manual.setValueAt(this.Cadenaleida.substring(12), this.jtbl_manual.getRowCount() - 1, 4);

              System.out.println("Agrega el Renglon");
              this.Cadenaleida = "";
            }
          }
        }
      }

      private void jtbl_manual_keyPressed(KeyEvent e)
      {
        if ((e.getKeyCode() == 27) || (e.getKeyCode() == 9))
        {
          if (this.jtxt_capturacodigo.isEnabled()) {
            this.jtxt_capturacodigo.requestFocus();
          }
          else
            this.jbtn_aceptar.requestFocus();
        }
      }

      private void jbtn_aceptar_keyPressed(KeyEvent e)
      {
        if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38)) {
          if (this.jtxt_capturacodigo.isEnabled())
            this.jtxt_capturacodigo.requestFocus();
          else
            this.jtbl_manual.requestFocus();
        }
        if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
          this.jbtn_cancelar.requestFocus();
        if (e.getKeyCode() == 10)
          this.jbtn_aceptar.doClick();
      }

      private void jbtn_cancelar_keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
          this.jbtn_aceptar.requestFocus();
        if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
          this.jbtn_borrar.requestFocus();
        if (e.getKeyCode() == 10)
          this.jbtn_cancelar.doClick();
      }

      private void jbtn_borrar_keyPressed(KeyEvent e)
      {
        if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
          this.jbtn_cancelar.requestFocus();
        if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
          this.jbtn_manual.requestFocus();
        if (e.getKeyCode() == 10)
          this.jbtn_borrar.doClick();
      }

      private void jbtn_manual_keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
          this.jbtn_borrar.requestFocus();
        if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
          this.jtbl_manual.requestFocus();
        if (e.getKeyCode() == 10)
          this.jbtn_manual.doClick();
      }

      private void jtxt_capturacodigo_focusGained(FocusEvent e)
      {
        this.jlbl_barraestado_captura.setText("<html>  <font color=FF0000>   ESC</font> Termina | <font color=FF0000>F2</font> Capturar con codigo de Barras | <font color=FF0000>DEL</font> Borra Boleto | <font color=FF0000>INSERT</font> Agregar Boletos | <font color=FF0000>F10</font> Registrar Boletos</html>");
      }

      private void jtbl_manual_focusGained(FocusEvent e) {
        this.jlbl_barraestado_captura.setText(" Captura los datos y presiona ENTER despues de cada Renglon   ESC: Salir");
        this.jtbl_manual.setRowSelectionInterval(0, 0);
        this.jtbl_manual.setColumnSelectionInterval(0, 0); } 
      class Jdlg_Pregunta_Borrar extends JDialog { private JLabel jLabel1 = new JLabel();
        private JLabel jLabel2 = new JLabel();
        private ImageIcon imagen_pregunta = new ImageIcon(JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.class.getResource("pregunta.gif"));
        private JButton jbtn_respuesta_si = new JButton();
        private JButton jbtn_respuesta_no = new JButton();
        private JLabel jLabel3 = new JLabel();
        private String cadenafolio;
        private String nrow;

        public Jdlg_Pregunta_Borrar(String title, String nr, String nf) { setTitle(title);
          setModal(true);
          setDefaultLookAndFeelDecorated(true);
          this.nrow = nr;
          if (nf.equals("nada"))
            this.cadenafolio = "que no contiene informacion de boleto?";
          else
            this.cadenafolio = ("que contiene el boleto con folio: " + nf + "?");
          setUndecorated(true);
          getRootPane().setWindowDecorationStyle(7);
          setAlwaysOnTop(true);
          try {
            jbInit();
          } catch (Exception e) {
            e.printStackTrace();
          } }

        private void jbInit() throws Exception
        {
          setSize(new Dimension(313, 133));
          getContentPane().setLayout(null);
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Dimension frameSize = getSize();
          setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

          this.jLabel1.setText("¿Seguro que desea eliminar el renglon " + this.nrow);
          this.jLabel1.setBounds(new Rectangle(50, 10, 255, 20));
          this.jLabel1.setFont(new Font("Arial", 1, 12));
          this.jLabel1.setSize(new Dimension(265, 20));
          this.jLabel2.setBounds(new Rectangle(5, 5, 35, 45));
          this.jLabel2.setSize(new Dimension(35, 45));
          this.jLabel2.setIcon(this.imagen_pregunta);
          this.jbtn_respuesta_si.setText("Si");
          this.jbtn_respuesta_si.setBounds(new Rectangle(105, 65, 50, 25));
          this.jbtn_respuesta_si.setMnemonic('s');
          this.jbtn_respuesta_si.setFont(new Font("Arial", 1, 12));
          this.jbtn_respuesta_si.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.Jdlg_Pregunta_Borrar.this.jbtn_respuesta_si_actionPerformed(e);
            }
          });
          this.jbtn_respuesta_si.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
              JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.Jdlg_Pregunta_Borrar.this.jbtn_respuesta_si_keyPressed(e);
            }
          });
          this.jbtn_respuesta_no.setText("No");
          this.jbtn_respuesta_no.setBounds(new Rectangle(165, 65, 50, 25));
          this.jbtn_respuesta_no.setMnemonic('N');
          this.jbtn_respuesta_no.setFont(new Font("Arial", 1, 12));
          this.jbtn_respuesta_no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.Jdlg_Pregunta_Borrar.this.jbtn_respuesta_no_actionPerformed(e);
            }
          });
          this.jbtn_respuesta_no.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
              JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.Jdlg_Pregunta_Borrar.this.jbtn_respuesta_no_keyPressed(e);
            }
          });
          this.jLabel3.setText(this.cadenafolio);
          this.jLabel3.setFont(new Font("Arial", 1, 12));
          this.jLabel3.setSize(new Dimension(265, 20));
          this.jLabel3.setBounds(new Rectangle(50, 35, 250, 20));
          getContentPane().add(this.jLabel3, null);
          add(this.jbtn_respuesta_no, null);
          add(this.jbtn_respuesta_si, null);
          add(this.jLabel2, null);
          add(this.jLabel1, null);
        }

        private void jbtn_respuesta_si_actionPerformed(ActionEvent e) {
          //JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.access$4102(JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this, true);
          respuesta = true;
          dispose();
        }

        private void jbtn_respuesta_no_actionPerformed(ActionEvent e) {
          //JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.access$4102(JIFRecaudacion.jdlg_ventas0.Dialog_Manual_SuperRapidos.this, false);
          respuesta = false;
          dispose();
        }

        private void jbtn_respuesta_si_keyPressed(KeyEvent e) {
          if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
            this.jbtn_respuesta_no.requestFocus();
          if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
            this.jbtn_respuesta_no.requestFocus();
          if (e.getKeyCode() == 10)
            this.jbtn_respuesta_si.doClick();
        }

        private void jbtn_respuesta_no_keyPressed(KeyEvent e) {
          if ((e.getKeyCode() == 37) || (e.getKeyCode() == 38))
            this.jbtn_respuesta_si.requestFocus();
          if ((e.getKeyCode() == 39) || (e.getKeyCode() == 40))
            this.jbtn_respuesta_si.requestFocus();
          if (e.getKeyCode() == 10)
            this.jbtn_respuesta_no.doClick();
        }
      }
    }

    class Dialog_Manual_Sumadora extends JDialog
    {
      private JScrollPane jScrollPane1 = new JScrollPane();
      private int numero_boletos = 0;
      private DefaultTableModel modelo = new DefaultTableModel();
      private JTable jtbl_sumadora = new JTable(this.modelo)
      {
        public boolean isCellEditable(int row, int column)
        {
          return column == 1;
        }

        public Component prepareEditor(TableCellEditor editor, int row, int column)
        {
          Component c = super.prepareEditor(editor, row, column);
          if ((c instanceof JTextComponent))
          {
            ((JTextField)c).selectAll();
          }
          return c;
        }
      };

      private JTextField jtxt_cantidad = new JTextField();
      private JTextField jtxt_monto = new JTextField();
      private JLabel jLabel1 = new JLabel();

      public Dialog_Manual_Sumadora(int n) {
        setTitle("SUMADORA");
        setModal(true);
        setDefaultCloseOperation(0);
        setAlwaysOnTop(true);
        this.numero_boletos = n;
        try {
          jbInit();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      private void jbInit() throws Exception {
        setSize(new Dimension(243, 267));
        getContentPane().setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        this.jScrollPane1.setBounds(new Rectangle(0, 5, 235, 180));
        this.jScrollPane1.getViewport().add(this.jtbl_sumadora, null);
        getContentPane().add(this.jLabel1, null);
        getContentPane().add(this.jtxt_monto, null);
        getContentPane().add(this.jtxt_cantidad, null);
        getContentPane().add(this.jScrollPane1, null);
        this.modelo.addColumn("Cuantos");
        this.modelo.addColumn("Precio Unitario");
        String[] r = new String[4];
        for (int i = 1; i <= this.numero_boletos; i++)
          this.modelo.addRow(r);
        JCuantityTextField celdap = new JCuantityTextField();
        TableColumn Columnp = this.jtbl_sumadora.getColumnModel().getColumn(1);
        TableCellRenderer centerRenderer = new JIFRecaudacion.CenterRenderer();
        Columnp.setCellEditor(new DefaultCellEditor(celdap));
        Columnp.setCellRenderer(centerRenderer);
        TableColumn Columnn = this.jtbl_sumadora.getColumnModel().getColumn(0);
        Columnn.setCellRenderer(centerRenderer);
        this.jLabel1.setText("  Presiona ENTER despues de cada valor");
        this.jLabel1.setBounds(new Rectangle(0, 210, 240, 25));
        this.jLabel1.setFont(new Font("Dialog", 1, 12));
        this.jLabel1.setForeground(new Color(115, 115, 115));
        this.jLabel1.setBorder(BorderFactory.createBevelBorder(1));
        this.jLabel1.setSize(new Dimension(240, 25));
        this.jtxt_cantidad.setBounds(new Rectangle(0, 185, 115, 20));
        this.jtxt_cantidad.setEditable(false);
        this.jtxt_cantidad.setHorizontalAlignment(0);
        this.jtxt_monto.setBounds(new Rectangle(115, 185, 120, 20));
        this.jtxt_monto.setEditable(false);
        this.jtxt_monto.setHorizontalAlignment(0);
        this.jtbl_sumadora.setSelectionMode(0);
        this.jtbl_sumadora.setBackground(new Color(238, 238, 238));
        this.jtbl_sumadora.setGridColor(new Color(238, 238, 238));
        this.jtbl_sumadora.setRowSelectionInterval(0, 0);
        this.jtbl_sumadora.setColumnSelectionInterval(1, 1);
        this.jtbl_sumadora.setCellSelectionEnabled(true);
        this.jtbl_sumadora.setValueAt("1", 0, 0);
        this.jtbl_sumadora.getInputMap(1).put(KeyStroke.getKeyStroke(37, 0), "none");
        this.jtbl_sumadora.getInputMap(1).put(KeyStroke.getKeyStroke(39, 0), "none");
        this.jtbl_sumadora.getInputMap(1).put(KeyStroke.getKeyStroke(40, 0), "none");
        this.jtbl_sumadora.setSurrendersFocusOnKeystroke(true);
        this.jtxt_cantidad.setText("0");
        this.jtbl_sumadora.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_Sumadora.this.jtbl_sumadora_keyPressed(e);
          }

          public void keyReleased(KeyEvent e) {
            JIFRecaudacion.jdlg_ventas0.Dialog_Manual_Sumadora.this.jtbl_sumadora_keyReleased(e);
          } } );
      }

      private void jtbl_sumadora_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10)
        {
          String nada = "";

          if (this.jtbl_sumadora.getSelectedRow() + 1 == this.numero_boletos)
          {
            if ((this.jtbl_sumadora.getValueAt(this.jtbl_sumadora.getSelectedRow(), 1) == null) || (this.jtbl_sumadora.getValueAt(this.jtbl_sumadora.getSelectedRow(), 1).equals("")))
            {
              this.jtbl_sumadora.setValueAt(this.jtbl_sumadora.getValueAt(this.jtbl_sumadora.getSelectedRow() - 1, 1), this.jtbl_sumadora.getSelectedRow(), 1);
              this.jtbl_sumadora.setValueAt("1", this.jtbl_sumadora.getSelectedRow(), 0);
            }
            else {
              this.jtbl_sumadora.setValueAt("1", this.jtbl_sumadora.getSelectedRow(), 0);
            }boolean completo = true;
            for (int i = 0; i < this.numero_boletos; i++)
            {
              if (this.jtbl_sumadora.getValueAt(i, 1) != null)
              {
                if (this.jtbl_sumadora.getValueAt(i, 1).equals("")) {
                  try {
                    this.jtbl_sumadora.setRowSelectionInterval(i - 1, i - 1); } catch (IllegalArgumentException ex) {
                  }completo = false;
                  break;
                }

                nada = "nada";
              }
              else {
                try {
                  this.jtbl_sumadora.setRowSelectionInterval(i - 1, i - 1); } catch (IllegalArgumentException ex) {
                }completo = false;
                break;
              }
            }
            if (completo)
            {
              float total = 0.0F; int cantidad = 0;
              for (int i = 0; i < this.numero_boletos; i++)
              {
                if (this.jtbl_sumadora.getValueAt(i, 1) != null)
                  if (this.jtbl_sumadora.getValueAt(i, 1).equals("")) { nada = "nada";
                  } else
                  {
                    total += Float.valueOf(this.jtbl_sumadora.getValueAt(i, 1).toString()).floatValue();
                    cantidad += Integer.valueOf(this.jtbl_sumadora.getValueAt(i, 0).toString()).intValue();
                  }
              }
              this.jtxt_monto.setText("" + total);
              this.jtxt_cantidad.setText("" + cantidad);
              System.out.println("Cantidad: " + this.jtxt_cantidad.getText());
              System.out.println("Total: " + this.jtxt_monto.getText());
              JIFRecaudacion.jdlg_ventas0.this.jtbl_ventas.setValueAt(this.jtxt_monto.getText(), JIFRecaudacion.jdlg_ventas0.this.jtbl_ventas.getSelectedRow(), 6);
              dispose();
            }

          }
          else if (this.jtbl_sumadora.getSelectedRow() != 0)
          {
            if ((this.jtbl_sumadora.getValueAt(this.jtbl_sumadora.getSelectedRow(), 1) == null) || (this.jtbl_sumadora.getValueAt(this.jtbl_sumadora.getSelectedRow(), 1).equals("")))
            {
              this.jtbl_sumadora.setValueAt(this.jtbl_sumadora.getValueAt(this.jtbl_sumadora.getSelectedRow() - 1, 1), this.jtbl_sumadora.getSelectedRow(), 1);
              this.jtbl_sumadora.setValueAt("1", this.jtbl_sumadora.getSelectedRow(), 0);
            }
            else {
              this.jtbl_sumadora.setValueAt("1", this.jtbl_sumadora.getSelectedRow(), 0);
            }
          }
        }
      }

      private void jtbl_sumadora_keyReleased(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          String nada = "";
          float total = 0.0F;
          float cantidad = 0.0F;
          for (int i = 0; i < this.numero_boletos; i++)
          {
            if ((this.jtbl_sumadora.getValueAt(i, 1) == null) || (this.jtbl_sumadora.getValueAt(i, 0) == null))
              continue;
            if ((this.jtbl_sumadora.getValueAt(i, 1).equals("")) || (this.jtbl_sumadora.getValueAt(i, 0).equals(""))) {
              nada = "nada";
            }
            else {
              total += Float.valueOf(this.jtbl_sumadora.getValueAt(i, 1).toString()).floatValue();
              cantidad += Float.valueOf(this.jtbl_sumadora.getValueAt(i, 0).toString()).floatValue();
            }

          }

          this.jtxt_monto.setText("" + total);
          this.jtxt_cantidad.setText("" + cantidad);
        }
      }
    }
  }
}