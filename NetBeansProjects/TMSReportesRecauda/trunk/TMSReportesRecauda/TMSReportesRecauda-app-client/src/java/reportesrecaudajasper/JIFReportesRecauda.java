package reportesrecaudajasper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import recursos.ImpresionMonitorThread;
import recursos.JDlgAceptar;
import tmsReportesRecauda.TmsSesionActividadesTbl;
import tmsReportesRecauda.solicitud.TmsUsuariosTblFacadeRemote;

public class JIFReportesRecauda extends JInternalFrame
{
  private JasperPrint print;
  private InputStream input;
  private InputStream imagen;
  private String referenciaEFE ="";
  private Map param;
  private JRViewer er = null;
  private Vector datos;
  private KeyEvent eventoTeclado;
  private JPanel jPanel1;
  private JComboBox jcmbx_reportes;
  private JLabel jlbl_barraEstado;
  private JPanel jpnlReportes;
  private JPanel jpnl_back;
  private String nombreReporte = "";
  private boolean respuestaSN = false;
  private TmsUsuariosTblFacadeRemote usuariosTblFacadeRemote;
  private String servicioRG = "";
  private String empresaRG = "";
  private SimpleDateFormat formatoFecha= new SimpleDateFormat("ddMMyyyy");
  private JDlgAceptar DialogoAceptar=new JDlgAceptar();

  public JIFReportesRecauda()
  {
    initComponents();
    this.jcmbx_reportes.removeAllItems();
    this.jcmbx_reportes.addItem("Corte Parcial");
    this.jcmbx_reportes.addItem("Corte fin de día desglosado");
    this.jcmbx_reportes.addItem("Tarjetas de Viaje");
    this.jcmbx_reportes.addItem("Anticipo de gastos");
    this.jcmbx_reportes.addItem("Sueldo de operadores");
    this.jcmbx_reportes.addItem("Depositos Diarios");
    this.jcmbx_reportes.addItem("Pago de Nomina de Operadores");
    this.jcmbx_reportes.addItem("Pago de Nomina de Operadores por Incidencia");
    this.jcmbx_reportes.addItem("Reporte General de Recauda");
    this.jcmbx_reportes.addItem("Reporte Cartera Vencida");
    this.jcmbx_reportes.addItem("Reporte Cartera Vencida Detallado");
    this.jcmbx_reportes.addItem("Reporte Vales Diesel");
    this.jcmbx_reportes.addItem("Corte fin de día normal");
    this.usuariosTblFacadeRemote = lookupTmsUsuariosTblFacadeRemote();
    this.jpnl_back.setVisible(true);
    this.jpnlReportes.setVisible(false);

    setTitle("TMSReportesRecauda Rev 06.07.15");
    this.jcmbx_reportes.requestFocus();
  }

  private void initComponents()
  {
    this.jpnlReportes = new JPanel();
    this.jlbl_barraEstado = new JLabel();
    this.jPanel1 = new JPanel();
    this.jcmbx_reportes = new JComboBox();
    this.jpnl_back = new JPanel();

    setDefaultCloseOperation(2);
    setIconifiable(true);
    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent evt) {
        JIFReportesRecauda.this.formComponentResized(evt);
      }
      public void componentShown(ComponentEvent evt) {
        JIFReportesRecauda.this.formComponentShown(evt);
      }
    });
    this.jpnlReportes.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent evt) {
        JIFReportesRecauda.this.jpnlReportesFocusGained(evt);
      }
    });
    GroupLayout jpnlReportesLayout = new GroupLayout(this.jpnlReportes);
    this.jpnlReportes.setLayout(jpnlReportesLayout);
    jpnlReportesLayout.setHorizontalGroup(jpnlReportesLayout.createParallelGroup(1).add(0, 735, 32767));

    jpnlReportesLayout.setVerticalGroup(jpnlReportesLayout.createParallelGroup(1).add(0, 186, 32767));

    this.jlbl_barraEstado.setFont(new Font("Tahoma", 1, 11));
    this.jlbl_barraEstado.setText("jLabel1");
    this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

    this.jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Reportes Recaudacion", 0, 0, new Font("Tahoma", 1, 12)));
    this.jcmbx_reportes.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    this.jcmbx_reportes.addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent evt) {
        JIFReportesRecauda.this.jcmbx_reportesFocusGained(evt);
      }
      public void focusLost(FocusEvent evt) {
        JIFReportesRecauda.this.jcmbx_reportesFocusLost(evt);
      }
    });
    this.jcmbx_reportes.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent evt) {
        JIFReportesRecauda.this.jcmbx_reportesKeyPressed(evt);
      }
    });
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jcmbx_reportes, -2, 491, -2).addContainerGap(198, 32767)));

    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(this.jcmbx_reportes, -2, -1, -2).addContainerGap(-1, 32767)));

    GroupLayout jpnl_backLayout = new GroupLayout(this.jpnl_back);
    this.jpnl_back.setLayout(jpnl_backLayout);
    jpnl_backLayout.setHorizontalGroup(jpnl_backLayout.createParallelGroup(1).add(0, 735, 32767));

    jpnl_backLayout.setVerticalGroup(jpnl_backLayout.createParallelGroup(1).add(0, 302, 32767));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(this.jpnl_back, -1, -1, 32767).add(this.jpnlReportes, -1, -1, 32767).add(this.jlbl_barraEstado, -1, 735, 32767).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(22, 22, 22).add(this.jPanel1, -2, -1, -2).add(15, 15, 15).add(this.jpnlReportes, -1, -1, 32767).addPreferredGap(0).add(this.jpnl_back, -1, -1, 32767).addPreferredGap(0).add(this.jlbl_barraEstado, -2, 55, -2)));

    pack();
  }

  private void formComponentShown(ComponentEvent evt) {
    this.jcmbx_reportes.requestFocusInWindow();
  }

  private void formComponentResized(ComponentEvent evt) {
    if (this.jpnlReportes.getComponentCount() > 0)
    {
      this.jpnlReportes.removeAll();
      this.jpnlReportes.setVisible(false);
      this.jpnlReportes.setLayout(null);
      this.er.setBounds(0, 0, this.jpnlReportes.getWidth(), this.jpnlReportes.getHeight());
      this.jpnlReportes.add(this.er);
      getContentPane().repaint();
      this.jpnlReportes.setVisible(true);
      this.jpnl_back.setVisible(false);
      this.jpnlReportes.requestFocus();
    }
  }

  private void jpnlReportesFocusGained(FocusEvent evt)
  {
    this.jpnlReportes.setVisible(true);
    this.jpnl_back.setVisible(false);
  }

  private void jcmbx_reportesFocusLost(FocusEvent evt) {
    this.jlbl_barraEstado.setText("");
  }

  private void jcmbx_reportesFocusGained(FocusEvent evt)
  {
    this.jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Reportes Recaudacion | <font color=FF0000>ENTER</font> Seleccionar Reporte | <font color=FF0000> Arriba/Abajo </font> Seleccionar Reporte   | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
    this.jpnl_back.setVisible(true);
    this.jpnlReportes.setVisible(false);
  }

  private void jcmbx_reportesKeyPressed(KeyEvent evt)
  {
    if (evt.getKeyCode() == 10)
    {
      if (this.jcmbx_reportes.getSelectedIndex() == 0)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, false, true, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = jdp.getRecaudan();
        String estado = "";
        long usuario = jdp.getUsuario();
        String nombre = "Reportes/ReportePrevioUnificado.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 1)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, false, false, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        Timestamp tfant = null;
        tfant = Timestamp.valueOf(jdp.getFechaCorte() + " 00:00:00");
        SimpleDateFormat formatfcn = new SimpleDateFormat("dd/MM/yyyy");
        //fi = formatfcn.format(Long.valueOf(tfant.getTime() - 8640000L));
        System.out.println("Fecha inicial: " + fi);
        System.out.println("Fecha final: " + ff);
        System.out.println("Fecha anterior para corte de finde dia es: " + fi);
       /* if (this.usuariosTblFacadeRemote.buscaCortesPendientes(fi, ff) > 0)
        {
          new jdlg_error("¡Hay cortes pendientes de este dia!", "Realice los cortes pendientes", "Cortes Pendientes").setVisible(true);
          return;
        }*/
        String rec = jdp.getRecaudan();
        String estado = "";
        long usuarioId = 1;
        long usuario = 0L;
        /*
        String ref = fi.replace("/", "")+"RECA";
        System.out.println("ref: "+ref);
        this.referenciaEFE = fi.replace("/", "")+"RECAT"+calculaDV_Alg35(ref+"T");
        */
        Vector datosTerminal =  usuariosTblFacadeRemote.buscaDatosTerminal();
        //GENERA CORTE DUMMY PARA CORTE FIN DE DIA


        //REGISTRAR REFERENCIAS
        Timestamp fechaAct = Timestamp.valueOf(usuariosTblFacadeRemote._ObtieneFechaHoraBD());
        boolean resp = usuariosTblFacadeRemote.corteDummyFinDia(usuarioId);
        if(!resp){
            DialogoAceptar.mostrarDialogo("¡El corte de fin de dia no se efectuo!", "<html>Error al generar el Corte de Fin de Dia.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            this.dispose();
            return;
        }

        System.out.println("SESION CORTE FIN DE DIA ");
        Object SesionActCorteId = ((Vector) usuariosTblFacadeRemote.queryActividadSesionCorteFinDiaGetId()).get(0);
        if(SesionActCorteId == null){
            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No esta configurada la actividad sesion CORTE FIN DE DIA.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            System.out.println("No encontro el ID del Estado de la sesion de CORTE FIN DE DIA ");
            this.dispose();
            return;
        }
        long sesionDeCorteFinDia = Long.valueOf(SesionActCorteId.toString());

        TmsSesionActividadesTbl tmsSesionActividadesTbl = new TmsSesionActividadesTbl();
        tmsSesionActividadesTbl.setCorteId(Long.valueOf("1"));
        tmsSesionActividadesTbl.setActividadSesionId(sesionDeCorteFinDia);
        tmsSesionActividadesTbl.setEmpresaId((long)1);
        //SimpleDateFormat formatoFecha= new SimpleDateFormat("ddMMyyyy");
        String termminal = datosTerminal.get(1).toString();
        String refer = fi.replace("/","")+termminal+"R";
        tmsSesionActividadesTbl.setValorActividad(refer);
        tmsSesionActividadesTbl.setFechaHoraActividad(fechaAct);
        tmsSesionActividadesTbl.setAutorizadoPor(usuarioId);
        tmsSesionActividadesTbl.setCreadoPor(usuarioId);
        tmsSesionActividadesTbl.setFechaCreacion(fechaAct);
        tmsSesionActividadesTbl.setUltimaActualizacionPor(usuarioId);
        tmsSesionActividadesTbl.setUltimaFechaActualizacion(fechaAct);
        usuariosTblFacadeRemote.addSesionCorteFinDia(datosTerminal.get(0).toString(), tmsSesionActividadesTbl, ff);
        String nombre = "Reportes/ReporteCorteFindeDiaUnificado.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,refer));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 2)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, true, true, false, true, "Despachadas", "Recaudadas", "Canceladas");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = jdp.getRecaudan();
        String estado = jdp.getEstado();
        long usuario = 0L;
        String nombre = "Reportes/ReporteTarjetasPendientes.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 3)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, true, false, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = "";
        String estado = "";
        long usuario = 0L;
        String nombre = "Reportes/AnticipoGastosPorOperador.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 4)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, true, false, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = "";
        String estado = "";
        long usuario = 0L;
        String nombre = "Reportes/SueldoDesglosadoDeOperadores.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 5)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, false, false, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada")) {
          return;
        }
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        Timestamp tfant = null;
        tfant = Timestamp.valueOf(jdp.getFechaCorte() + " 00:00:00");
        SimpleDateFormat formatfcn = new SimpleDateFormat("dd/MM/yyyy");
        fi = formatfcn.format(Long.valueOf(tfant.getTime() - 8640000L));
        System.out.println("Fecha inicial: " + fi);
        System.out.println("Fecha final: " + ff);
        System.out.println("Fecha anterior para corte de finde dia es: " + fi);
        String rec = jdp.getRecaudan();
        String estado = "";
        long usuario = 0L;
        String nombre = "Reportes/depositosBancarios.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 6)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, true, false, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = "";
        String estado = "";
        long usuario = 0L;
        String nombre = "Reportes/ReportePagoNominaOperadores.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 7)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, true, false, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = "";
        String estado = "";
        long usuario = 0L;
        String nombre = "Reportes/ReportePagoNomIncidOper.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 8)
      {
        Vector vs = this.usuariosTblFacadeRemote.buscaServicios();
        Vector ve = this.usuariosTblFacadeRemote.buscaEmpresas();
        jdlg_ParametrosReportes_RecGral jdp = new jdlg_ParametrosReportes_RecGral(new JFrame(), true, false, true, false, true, "Por Recaudación", "Por Corrida", "", vs, ve);
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde(); 
        String ff = jdp.getFechaHasta();
        String rec = "";
        String estado = jdp.getEstado();
        this.servicioRG = jdp.getServicio();
        this.empresaRG = jdp.getEmpresa();
        long usuario = 0L;
        String nombre = "Reportes/ReporteRecaudacionGeneral2.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 9)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, true, false, true, "Por Recaudación", "Por Corrida", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = "";
        String estado = jdp.getEstado();
        long usuario = 0L;
        String nombre = "Reportes/ReporteRecaudaCarteraVencida.jrxml.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 10)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, true, false, true, "Por Recaudación", "Por Corrida", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        String rec = "";
        String estado = jdp.getEstado();
        long usuario = 0L;
        String nombre = "Reportes/ReporteRecaudaCarteraVencidaDetallado.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 11)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, true, false, true, "General", "Por Terminal", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada")) {
          return;
        }
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        Timestamp tfant = null; 
        tfant = Timestamp.valueOf(jdp.getFechaCorte2() + " 00:00:00");
        SimpleDateFormat formatfcn = new SimpleDateFormat("dd/MM/yyyy");
        fi = formatfcn.format(Long.valueOf(tfant.getTime() - 8640000L));
        System.out.println("Fecha inicial: " + fi);
        System.out.println("Fecha final: " + ff);
        System.out.println("Fecha anterior para corte de finde dia es: " + fi);

        String rec = "";
        String estado = jdp.getEstado();
        long usuario = 0L;
        String nombre = "";
        if (estado.equals("ABIERTA") ||estado.equals("CONFIRMADA"))
          nombre = "Reportes/ReporteValesDiesel.jasper";
        else
          nombre = "Reportes/ReporteValesDieselPorTerminal.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

      if (this.jcmbx_reportes.getSelectedIndex() == 12)
      {
        jdlg_ParametrosReportes jdp = new jdlg_ParametrosReportes(new JFrame(), true, false, false, false, false, "", "", "");
        jdp.setVisible(true);
        if (jdp.getFechaDesde().equals("nada"))
          return;
        String fi = jdp.getFechaDesde();
        String ff = jdp.getFechaHasta();
        Timestamp tfant = null;
        tfant = Timestamp.valueOf(jdp.getFechaCorte() + " 00:00:00");
        SimpleDateFormat formatfcn = new SimpleDateFormat("dd/MM/yyyy");
        //fi = formatfcn.format(Long.valueOf(tfant.getTime() - 8640000L));
        System.out.println("Fecha inicial: " + fi);
        System.out.println("Fecha final: " + ff);
        System.out.println("Fecha anterior para corte de finde dia es: " + fi);
        if (this.usuariosTblFacadeRemote.buscaCortesPendientes(fi, ff) > 0)
        {
          new jdlg_error("¡Hay cortes pendientes de este dia!", "Realice los cortes pendientes", "Cortes Pendientes").setVisible(true);
          return;
        }
        String rec = jdp.getRecaudan();
        String estado = "";
        long usuario = 0L;
        String nombre = "Reportes/ReporteCorteFindeDiaUnificado_SinDesglose.jasper";
        Thread tim = new Thread(new ImpresionMonitorThread(this, fi, ff, rec, usuario, nombre, estado,""));
        tim.start();
      }

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

  private void salirAplicacion()
  {
    jdlg_pregunta_SN psn = new jdlg_pregunta_SN("Salir de Reportes", "¿Seguro que desea salir de la Aplicacion de Reportes?");
    psn.setVisible(true);
    if (this.respuestaSN)
    {
      dispose();
    }
  }

  public void generarReporte(String fi, String ff, String rec, long usr, String nombreReporte, String estado, String ref)
  {
      /*
    String qryRecuadaGral = "select  tmstar.FOLIO_TARJETA folio , to_char(tmsrec.CORTE_ID) corte   , to_char(tmsrec.RECAUDACION_REFERENCIA) ticket   , tmscv.SERVICIO servicio   , tmscv.empresa empresa   , tmscv.ORIGEN origen   , tmscv.DESTINO destino   ,tmstar.OPERADOR operador   , tmstar.AUTOBUS autobus    ,to_char(tmscv.FECHA_HORA_CORRIDA,'DD/MM/RRRR') fecha_corrida    , to_char(tmscv.FECHA_HORA_CORRIDA,'HH24:MI') hora_corrida   , tmsrut.DISTANCIA_RECORRIDO kilometros   ,tmsRec.BOLETOS_VENTA_SISTEMA PASAJEROS_VENTA_SISTEMA  ,tmsrec.IMPORTE_VENTA_SISTEMA VENTA_TAQUILLA   ,tmsRec.BOLETOS_VENTA_ABORDO PASAJEROS_VENTA_ABORDO  ,tmsrec.IMPORTE_VENTA_ABORDO VENTA_ABORDO   ,tmsRec.BOLETOS_VENTA_MANUAL PASAJEROS_VENTA_MANUAL  ,tmsrec.IMPORTE_VENTA_MANUAL VENTA_MANUAL  ,tmsrec.SUELDO_OPERADOR SUELDO    ,tmsrec.RETENCION  ,(tmsrec.SUELDO_OPERADOR - tmsrec.RETENCION) Total_Pagar    ,tmsct.NUMERO_CONTRATO   ,tmsct.NUMERO_ORDEN   ,tmsct.MONTO_ANTICIPOS    ,tmsrec.saldo SALDO   ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('SUPER_SUELDO_OPERADOR',tmsRec.recaudacion_id)) SUELDO_SUPER   ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('SUPER_TARIFA_TARJETA',tmsRec.recaudacion_id)) COSTO_TARJETA_SUPER   ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('SUPER_PEAJE',tmsRec.recaudacion_id)) PEAJE_SUPER   ,( Xer_Tms_Pkg2.TMS_SUM_VAL_GASTO_RECAUDA_FNC('CARTERA_VENCIDA',tmsRec.recaudacion_id)) CARTERA_VENCIDA_BOL   ,( Xer_Tms_Pkg2.TMS_SUM_VAL_GASTO_RECAUDA_FNC('PAGO_TARJETA',tmsRec.recaudacion_id)) PAGO_TARJETA_BOL  ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('Turismo_Hotel',tmsRec.recaudacion_id)) Hotel ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('Turismo_Lavadas',tmsRec.recaudacion_id)) Lavadas ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('Turismo_Alimentos',tmsRec.recaudacion_id)) Alimentos  ,( Xer_TMS_Pkg2.TMS_PARAMETRO_VALOR_INT_FNC(tmscv.CORRIDA_ID, tmscv.FECHA_HORA_CORRIDA , 'P_R_COBTAR') ) COBTAR      ,NVL(tmsrec.ESTADO_RECAUDACION,'A') estado    ,tmsedorec.ESTADO_NOMBRE lugar_Recaudacion   , to_char(tmsrec.FECHA_CREACION,'DD/MM/RRRR') fecha_recaudacion   , to_char(tmsrec.FECHA_CREACION,'HH24:MI') hora_recaudacion   ,tmsusr.USUARIO_NUMERO||'-'||tmsusr.USUARIO_NOMBRE recaudador   from TMS_recaudacion_tbl tmsrec   ,TMS_tarjetas_viaje_tbl tmstar    ,TMS_corridas_venta_tbl tmscv    ,TMS_corridas_tbl tmsct   ,TMS_rutas_tbl tmsrut   ,TMS_usuarios_tbl tmsusr   ,TMS_estados_tbl tmsedorec  WHERE  DECODE($P{TIPO_REPORTE},'RECAUDA',tmsrec.FECHA_CREACION ,'CORRIDA',tmscv.FECHA_HORA_CORRIDA) between to_date(''||$P{FECHAI}||' 00:00','DD/MM/RRRR HH24:MI') and to_date(''||$P{FECHAF}||' 23:59','DD/MM/RRRR HH24:MI') and tmsct.CORRIDA_ID = tmscv.CORRIDA_ID   and tmstar.CORRIDA_ID = tmsct.CORRIDA_ID    ";

    if ((this.jcmbx_reportes.getSelectedIndex() == 8) && (!this.servicioRG.equals("nada")) && (!this.servicioRG.equals("Todos")))
      qryRecuadaGral = qryRecuadaGral + "and tmscv.SERVICIO = $P{SERVICIO} ";
    if ((this.jcmbx_reportes.getSelectedIndex() == 8) && (!this.empresaRG.equals("nada")) && (!this.empresaRG.equals("Todas")))
      qryRecuadaGral = qryRecuadaGral + "and tmscv.EMPRESA = $P{EMPRESA} ";
    qryRecuadaGral = qryRecuadaGral + "and tmsrec.TARJETA_VIAJE_ID(+) = tmstar.TARJETA_VIAJE_ID   " + "and tmsrut.ruta_id = tmsct.RUTA_ID   " + "and tmsusr.USUARIO_ID(+) = tmsrec.RECAUDADOR_ID   " + "and tmsedorec.ESTADO_ID(+)= tmsrec.CIUDAD_RECAUDACION_ID   " + "order by tmscv.FECHA_HORA_CORRIDA   " + ",tmscv.servicio\t\t";
*/
  String qryRecuadaGral = "select  tmstar.FOLIO_TARJETA folio , to_char(tmsrec.CORTE_ID) corte   , to_char(tmsrec.RECAUDACION_REFERENCIA) ticket   , tmscv.SERVICIO servicio   , tmscv.empresa empresa   , tmscv.ORIGEN origen   , tmscv.DESTINO destino   ,tmstar.OPERADOR operador   , tmstar.AUTOBUS autobus    ,to_char(tmscv.FECHA_HORA_CORRIDA,'DD/MM/RRRR') fecha_corrida    , to_char(tmscv.FECHA_HORA_CORRIDA,'HH24:MI') hora_corrida   , tmsrut.DISTANCIA_RECORRIDO kilometros   ,tmsRec.BOLETOS_VENTA_SISTEMA PASAJEROS_VENTA_SISTEMA  ,tmsrec.IMPORTE_VENTA_SISTEMA VENTA_TAQUILLA   ,tmsRec.BOLETOS_VENTA_ABORDO PASAJEROS_VENTA_ABORDO  ,tmsrec.IMPORTE_VENTA_ABORDO VENTA_ABORDO   ,tmsRec.BOLETOS_VENTA_MANUAL PASAJEROS_VENTA_MANUAL  ,tmsrec.IMPORTE_VENTA_MANUAL VENTA_MANUAL  ,tmsrec.SUELDO_OPERADOR SUELDO    ,tmsrec.RETENCION  ,(tmsrec.SUELDO_OPERADOR - tmsrec.RETENCION) Total_Pagar    ,tmsct.NUMERO_CONTRATO   ,tmsct.NUMERO_ORDEN   ,tmsct.MONTO_ANTICIPOS    ,tmsrec.saldo SALDO   ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('SUPER_SUELDO_OPERADOR',tmsRec.recaudacion_id)) SUELDO_SUPER   ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('SUPER_TARIFA_TARJETA',tmsRec.recaudacion_id)) COSTO_TARJETA_SUPER   ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('SUPER_PEAJE',tmsRec.recaudacion_id)) PEAJE_SUPER   ,( Xer_Tms_Pkg2.TMS_SUM_VAL_GASTO_RECAUDA_FNC('CARTERA_VENCIDA',tmsRec.recaudacion_id)) CARTERA_VENCIDA_BOL   ,( Xer_Tms_Pkg2.TMS_SUM_VAL_GASTO_RECAUDA_FNC('PAGO_TARJETA',tmsRec.recaudacion_id)) PAGO_TARJETA_BOL  ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('Turismo_Hotel',tmsRec.recaudacion_id)) Hotel ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('Turismo_Lavadas',tmsRec.recaudacion_id)) Lavadas ,( Xer_Tms_Pkg2.TMS_VALOR_GASTO_RECAUDA_FNC('Turismo_Alimentos',tmsRec.recaudacion_id)) Alimentos  ,( Xer_TMS_Pkg2.TMS_PARAMETRO_VALOR_INT_FNC(tmscv.CORRIDA_ID, tmscv.FECHA_HORA_CORRIDA , 'P_R_COBTAR') ) COBTAR      ,NVL(tmsrec.ESTADO_RECAUDACION,'A') estado    ,tmsedorec.ESTADO_NOMBRE lugar_Recaudacion   , to_char(tmsrec.FECHA_HORA_RECAUDACION,'DD/MM/RRRR') fecha_recaudacion   , to_char(tmsrec.FECHA_HORA_RECAUDACION,'HH24:MI') hora_recaudacion   ,tmsusr.USUARIO_NUMERO||'-'||tmsusr.USUARIO_NOMBRE recaudador   from TMS_recaudacion_tbl tmsrec   ,TMS_tarjetas_viaje_tbl tmstar    ,TMS_corridas_venta_tbl tmscv    ,TMS_corridas_tbl tmsct   ,TMS_rutas_tbl tmsrut   ,TMS_usuarios_tbl tmsusr   ,TMS_estados_tbl tmsedorec  WHERE  DECODE($P{TIPO_REPORTE},'RECAUDA',tmsrec.FECHA_HORA_RECAUDACION ,'CORRIDA',tmscv.FECHA_HORA_CORRIDA) between to_date(''||$P{FECHAI}||' 00:00:00','DD/MM/RRRR HH24:MI:SS') and to_date(''||$P{FECHAF}||' 23:59:59','DD/MM/RRRR HH24:MI:SS') and tmsct.CORRIDA_ID = tmscv.CORRIDA_ID   and tmstar.CORRIDA_ID = tmsct.CORRIDA_ID    ";

    if ((this.jcmbx_reportes.getSelectedIndex() == 8) && (!this.servicioRG.equals("nada")) && (!this.servicioRG.equals("Todos")))
      qryRecuadaGral = qryRecuadaGral + "and tmscv.SERVICIO = $P{SERVICIO} ";
    if ((this.jcmbx_reportes.getSelectedIndex() == 8) && (!this.empresaRG.equals("nada")) && (!this.empresaRG.equals("Todas")))
      qryRecuadaGral = qryRecuadaGral + "and tmscv.EMPRESA = $P{EMPRESA} ";
    qryRecuadaGral = qryRecuadaGral + "and tmsrec.TARJETA_VIAJE_ID(+) = tmstar.TARJETA_VIAJE_ID   " + "and tmsrut.ruta_id = tmsct.RUTA_ID   " + "and tmsusr.USUARIO_ID(+) = tmsrec.RECAUDADOR_ID   " + "and tmsedorec.ESTADO_ID(+)= tmsrec.CIUDAD_RECAUDACION_ID   " + "order by tmscv.FECHA_HORA_CORRIDA   " + ",tmscv.servicio\t\t";

    String qryCVRecaudacion = "select tar.OPERADOR OPERADOR ,oper.OPERADOR_NOMBRE_COMPLETO \t    ,(select (sum(cor2.MONTO_ANTICIPOS) * -1)  from tms_CORRIDAS_TBL cor2 ,tms_tarjetas_viaje_TBL tar2 where tar2.OPERADOR = tar.OPERADOR and   cor2.corrida_id = tar2.CORRIDA_ID )saldo_total  ,sum(to_number(case gasto.RECAUDACION_GASTO_NOMBRE when 'CARTERA_VENCIDA' then reclines.RECAUDACION_VALOR else '0' end ,'999999.99'))  comprobaciones ,nvl((select r.SALDO  from tms_recaudacion_tbl r ,tms_tarjetas_viaje_tbl t where  r.FECHA_CREACION = ( select max(r2.FECHA_CREACION)  from tms_tarjetas_viaje_tbl t2 ,tms_recaudacion_tbl r2 where t2.OPERADOR = tar.OPERADOR and r2.ESTADO_RECAUDACION = 'R' and   r2.TARJETA_VIAJE_ID = t2.TARJETA_VIAJE_ID) AND t.OPERADOR = tar.OPERADOR AND R.TARJETA_VIAJE_ID = T.TARJETA_VIAJE_ID  AND r.SALDO <> 0 ),0) saldo_actual from tms_tarjetas_viaje_tbl tar  ,tms_CORRIDAS_TBL cor ,tms_estados_tarjeta_viaje_tbl edo ,tms_estados_tbl origen ,tms_estados_tbl destino ,tms_SERVICIOS_TBL serv ,tms_RECAUDACION_TBL rec ,tms_RECAUDACION_LINEAS_TBL reclines ,tms_RECAUDACION_GASTOS_TBL gasto    ,tms_OPERADORES_TBL oper where cor.CORRIDA_ID = tar.CORRIDA_ID and edo.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID and origen.ESTADO_ID  = cor.ORIGEN_ID and destino.ESTADO_ID = cor.DESTINO_ID and serv.SERVICIO_ID = cor.SERVICIO_ID and rec.TARJETA_VIAJE_ID = TAR.TARJETA_VIAJE_ID and reclines.RECAUDACION_ID = rec.RECAUDACION_ID  and gasto.RECAUDACION_GASTO_ID = reclines.RECAUDACION_GASTO_ID and gasto.RECAUDACION_GASTO_NOMBRE <> 'Casetas' and rec.ESTADO_RECAUDACION = 'R' and oper.CLAVE_OPERADOR = tar.OPERADOR and (gasto.RECAUDACION_GASTO_NOMBRE = 'CARTERA_VENCIDA' or cor.monto_anticipos > 0) \t    and rec.fecha_creacion BETWEEN to_date(''||$P{FECHAI}||' 00:00','DD/MM/RRRR HH24:MI') AND to_date(''||$P{FECHAF}||' 23:59','DD/MM/RRRR HH24:MI')  group by tar.OPERADOR,oper.OPERADOR_NOMBRE_COMPLETO  ORDER BY tar.OPERADOR  asc";

    String qryCVCorrida = "select tar.OPERADOR OPERADOR ,oper.OPERADOR_NOMBRE_COMPLETO \t    ,(select (sum(cor2.MONTO_ANTICIPOS) * -1)  from tms_CORRIDAS_TBL cor2 ,tms_tarjetas_viaje_TBL tar2 where tar2.OPERADOR = tar.OPERADOR and   cor2.corrida_id = tar2.CORRIDA_ID )saldo_total  ,sum(to_number(case gasto.RECAUDACION_GASTO_NOMBRE when 'CARTERA_VENCIDA' then reclines.RECAUDACION_VALOR else '0' end ,'999999.99'))  comprobaciones ,nvl((select r.SALDO  from tms_recaudacion_tbl r ,tms_tarjetas_viaje_tbl t where  r.FECHA_CREACION = ( select max(r2.FECHA_CREACION)  from tms_tarjetas_viaje_tbl t2 ,tms_recaudacion_tbl r2 where t2.OPERADOR = tar.OPERADOR and r2.ESTADO_RECAUDACION = 'R' and   r2.TARJETA_VIAJE_ID = t2.TARJETA_VIAJE_ID) AND t.OPERADOR = tar.OPERADOR AND R.TARJETA_VIAJE_ID = T.TARJETA_VIAJE_ID  AND r.SALDO <> 0 ),0) saldo_actual from tms_tarjetas_viaje_tbl tar  ,tms_CORRIDAS_TBL cor ,tms_estados_tarjeta_viaje_tbl edo ,tms_estados_tbl origen ,tms_estados_tbl destino ,tms_SERVICIOS_TBL serv ,tms_RECAUDACION_TBL rec ,tms_RECAUDACION_LINEAS_TBL reclines ,tms_RECAUDACION_GASTOS_TBL gasto    ,tms_OPERADORES_TBL oper where cor.CORRIDA_ID = tar.CORRIDA_ID and edo.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID and origen.ESTADO_ID  = cor.ORIGEN_ID and destino.ESTADO_ID = cor.DESTINO_ID and serv.SERVICIO_ID = cor.SERVICIO_ID and rec.TARJETA_VIAJE_ID = TAR.TARJETA_VIAJE_ID and reclines.RECAUDACION_ID = rec.RECAUDACION_ID  and gasto.RECAUDACION_GASTO_ID = reclines.RECAUDACION_GASTO_ID and gasto.RECAUDACION_GASTO_NOMBRE <> 'Casetas' and rec.ESTADO_RECAUDACION = 'R' and oper.CLAVE_OPERADOR = tar.OPERADOR and (gasto.RECAUDACION_GASTO_NOMBRE = 'CARTERA_VENCIDA' or cor.monto_anticipos > 0) \t    and cor.FECHA_HORA_CORRIDA BETWEEN to_date(''||$P{FECHAI}||' 00:00','DD/MM/RRRR HH24:MI') AND to_date(''||$P{FECHAF}||' 23:59','DD/MM/RRRR HH24:MI')  group by tar.OPERADOR,oper.OPERADOR_NOMBRE_COMPLETO  ORDER BY tar.OPERADOR  asc";

    String qryCVDRecaudacion = "select tar.FOLIO_TARJETA FOLIO_TARJETA ,tar.OPERADOR OPERADOR ,gasto.RECAUDACION_GASTO_NOMBRE ,to_number(reclines.RECAUDACION_VALOR ,'999999.99') valor ,( nvl((cor.MONTO_ANTICIPOS * -1),0) +  nvl((select r.SALDO  from tms_recaudacion_tbl r ,tms_tarjetas_viaje_tbl t where  r.FECHA_CREACION = ( select max(r2.FECHA_CREACION)  from tms_tarjetas_viaje_tbl t2 ,tms_recaudacion_tbl r2 ,tms_recaudacion_lineas_tbl rl2  ,tms_RECAUDACION_GASTOS_TBL gasto2 where t2.OPERADOR = tar.OPERADOR and r2.ESTADO_RECAUDACION = 'R' and r2.FECHA_CREACION < rec.FECHA_CREACION and rl2.RECAUDACION_ID = r2.RECAUDACION_ID and gasto2.RECAUDACION_GASTO_ID = rl2.RECAUDACION_GASTO_ID and gasto2.RECAUDACION_GASTO_NOMBRE <> 'Casetas'  and   r2.TARJETA_VIAJE_ID = t2.TARJETA_VIAJE_ID) AND t.OPERADOR = tar.OPERADOR  AND r.TARJETA_VIAJE_ID = t.TARJETA_VIAJE_ID  AND r.SALDO <> 0 ),0) ) saldo_anterior ,rec.SALDO SALDO_ACTUAL  ,rec.FECHA_CREACION ,rec.ESTADO_RECAUDACION from tms_tarjetas_viaje_tbl tar ,tms_CORRIDAS_TBL cor ,tms_estados_tarjeta_viaje_tbl edo ,tms_estados_tbl origen ,tms_estados_tbl destino ,tms_SERVICIOS_TBL serv ,tms_RECAUDACION_TBL rec ,tms_RECAUDACION_LINEAS_TBL reclines ,tms_RECAUDACION_GASTOS_TBL gasto  where cor.CORRIDA_ID = tar.CORRIDA_ID and edo.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID and origen.ESTADO_ID  = cor.ORIGEN_ID and destino.ESTADO_ID = cor.DESTINO_ID and serv.SERVICIO_ID = cor.SERVICIO_ID  and rec.TARJETA_VIAJE_ID = TAR.TARJETA_VIAJE_ID and reclines.RECAUDACION_ID = rec.RECAUDACION_ID  and gasto.RECAUDACION_GASTO_ID = reclines.RECAUDACION_GASTO_ID and gasto.RECAUDACION_GASTO_NOMBRE <> 'Casetas' and rec.fecha_creacion BETWEEN to_date(''||$P{FECHAI}||' 00:00','DD/MM/RRRR HH24:MI') AND to_date(''||$P{FECHAF}||' 23:59','DD/MM/RRRR HH24:MI') group by tar.OPERADOR, gasto.RECAUDACION_GASTO_NOMBRE, reclines.RECAUDACION_VALOR, gasto.RECAUDACION_GASTO_COMPROBABLE, tar.FOLIO_TARJETA,rec.FECHA_CREACION,rec.SALDO,cor.MONTO_ANTICIPOS,rec.ESTADO_RECAUDACION ORDER BY tar.OPERADOR, rec.FECHA_CREACION asc ";

    String qryCVDCorrida = "select tar.FOLIO_TARJETA FOLIO_TARJETA ,tar.OPERADOR OPERADOR ,gasto.RECAUDACION_GASTO_NOMBRE ,to_number(reclines.RECAUDACION_VALOR ,'999999.99') valor ,( nvl((cor.MONTO_ANTICIPOS * -1),0) +  nvl((select r.SALDO  from tms_recaudacion_tbl r ,tms_tarjetas_viaje_tbl t where  r.FECHA_CREACION = ( select max(r2.FECHA_CREACION)  from tms_tarjetas_viaje_tbl t2 ,tms_recaudacion_tbl r2 ,tms_recaudacion_lineas_tbl rl2  ,tms_RECAUDACION_GASTOS_TBL gasto2 where t2.OPERADOR = tar.OPERADOR and r2.ESTADO_RECAUDACION = 'R' and r2.FECHA_CREACION < rec.FECHA_CREACION and rl2.RECAUDACION_ID = r2.RECAUDACION_ID and gasto2.RECAUDACION_GASTO_ID = rl2.RECAUDACION_GASTO_ID and gasto2.RECAUDACION_GASTO_NOMBRE <> 'Casetas'  and   r2.TARJETA_VIAJE_ID = t2.TARJETA_VIAJE_ID) AND t.OPERADOR = tar.OPERADOR  AND R.TARJETA_VIAJE_ID = T.TARJETA_VIAJE_ID  AND r.SALDO <> 0 ),0) ) saldo_anterior ,rec.SALDO SALDO_ACTUAL  ,rec.FECHA_CREACION ,rec.ESTADO_RECAUDACION from tms_tarjetas_viaje_tbl tar ,tms_CORRIDAS_TBL cor ,tms_estados_tarjeta_viaje_tbl edo ,tms_estados_tbl origen ,tms_estados_tbl destino ,tms_SERVICIOS_TBL serv ,tms_RECAUDACION_TBL rec ,tms_RECAUDACION_LINEAS_TBL reclines ,tms_RECAUDACION_GASTOS_TBL gasto  where cor.CORRIDA_ID = tar.CORRIDA_ID and edo.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID and origen.ESTADO_ID  = cor.ORIGEN_ID and destino.ESTADO_ID = cor.DESTINO_ID and serv.SERVICIO_ID = cor.SERVICIO_ID  and rec.TARJETA_VIAJE_ID = TAR.TARJETA_VIAJE_ID and reclines.RECAUDACION_ID = rec.RECAUDACION_ID  and gasto.RECAUDACION_GASTO_ID = reclines.RECAUDACION_GASTO_ID and gasto.RECAUDACION_GASTO_NOMBRE <> 'Casetas' and cor.FECHA_HORA_CORRIDA BETWEEN to_date(''||$P{FECHAI}||' 00:00','DD/MM/RRRR HH24:MI') AND to_date(''||$P{FECHAF}||' 23:59','DD/MM/RRRR HH24:MI') group by tar.OPERADOR, gasto.RECAUDACION_GASTO_NOMBRE, reclines.RECAUDACION_VALOR, gasto.RECAUDACION_GASTO_COMPROBABLE, tar.FOLIO_TARJETA,rec.FECHA_CREACION,rec.SALDO,cor.MONTO_ANTICIPOS,rec.ESTADO_RECAUDACION ORDER BY tar.OPERADOR, rec.FECHA_CREACION asc ";

    System.out.println("Recaudan " + rec);
    System.out.println("Estado " + estado);
    try
    {
      this.param = new HashMap();
      this.imagen = getClass().getClassLoader().getResourceAsStream("Reportes/logito.JPG");
      this.param.put("FECHAI", fi);
      this.param.put("FECHAF", ff);
      this.param.put("P_REFERENCIA_T",ref);
      this.param.put("IMAGENLOGO", this.imagen);
      this.param.put("RECAUDAN", rec);
      this.param.put("NumUsuario", Long.valueOf(usr));
      this.param.put("Estado", estado);

      if ( (estado.equals("ABIERTA") ||estado.equals("CONFIRMADA") ) && (this.jcmbx_reportes.getSelectedIndex() == 8))
      {
        System.out.println("manda Reporte General por Recaudacion:  \n" + qryRecuadaGral);
        this.param.put("CONSULTA_SQL", qryRecuadaGral);
        this.param.put("TIPO_REPORTE", "RECAUDA");
        this.param.put("SERVICIO", this.servicioRG);
        this.param.put("EMPRESA", this.empresaRG);
      }

      if ((estado.equals("RECAUDADA")) && (this.jcmbx_reportes.getSelectedIndex() == 8))
      {
        System.out.println("manda Reporte General por Corrida: \n" + qryRecuadaGral);
        this.param.put("CONSULTA_SQL", qryRecuadaGral);
        this.param.put("TIPO_REPORTE", "CORRIDA");
        this.param.put("SERVICIO", this.servicioRG);
        this.param.put("EMPRESA", this.empresaRG);
      }

      if ((estado.equals("ABIERTA") ||estado.equals("CONFIRMADA") ) && (this.jcmbx_reportes.getSelectedIndex() == 9))
      {
        System.out.println("Reporte Cartera vencida por Recaudacion");
        this.param.put("CONSULTA_SQL", qryCVRecaudacion);
      }
      if ((estado.equals("RECAUDADA")) && (this.jcmbx_reportes.getSelectedIndex() == 9))
      {
        System.out.println("manda Reporte Cartera vencida por Corrida");
        this.param.put("CONSULTA_SQL", qryCVCorrida);
      }
      if ((estado.equals("ABIERTA") ||estado.equals("CONFIRMADA") ) && (this.jcmbx_reportes.getSelectedIndex() == 10))
      {
        System.out.println("manda  Reporte Cartera vencida Detallado por Recaudacion");
        this.param.put("CONSULTA_SQL", qryCVDRecaudacion);
      }
      if ((estado.equals("RECAUDADA")) && (this.jcmbx_reportes.getSelectedIndex() == 10))
      {
        System.out.println("manda  Reporte Cartera vencida Detallado por Corrida");
        this.param.put("CONSULTA_SQL", qryCVDCorrida);
      }
      if (this.jcmbx_reportes.getSelectedIndex() == 1)
      {
        String rep_arch = "Reportes/ReporteCorteFDSubRepVtaAbordo.jasper";
        InputStream entradaDetalleVtaAbordo = getClass().getClassLoader().getResourceAsStream(rep_arch);

        JasperReport jasperReportDetallVtaAbordo = (JasperReport)JRLoader.loadObject(entradaDetalleVtaAbordo);
        this.param.put("paSubReportDirVtaAbordo", jasperReportDetallVtaAbordo);

        String rep_archr = "Reportes/CORTE_DIA_REFERENCIAS_RECAUDA.jasper";
        InputStream entradaReferencia = getClass().getClassLoader().getResourceAsStream(rep_archr);
        JasperReport jasperReportReferencia = (JasperReport)JRLoader.loadObject(entradaReferencia);
        this.param.put("paSubReportReferencia", jasperReportReferencia);
      }

      this.input = getClass().getClassLoader().getResourceAsStream(nombreReporte);
      this.print = null;
      this.print = JasperFillManager.fillReport(this.input, this.param, conection());
      System.out.println("nombre  : " + nombreReporte);
      System.out.println("print   : " + this.print);
      System.out.println("input : " + this.input);
      System.out.println("imagen : " + this.imagen);

      jdlgPreguntaExportVista tipoSalida = new jdlgPreguntaExportVista();
      tipoSalida.setVisible(true);
      if (tipoSalida.getTipo().equals("Vista"))
      {
        this.jpnlReportes.removeAll();
        this.jpnlReportes.setVisible(false);
        this.er = new JRViewer(this.print);
        this.jpnlReportes.setLayout(null);
        Dimension FrameSize = getSize();
        this.er.setBounds(0, 0, FrameSize.width - 10, FrameSize.height - 190);
        this.jpnlReportes.add(this.er);
        getContentPane().repaint();
        this.jpnlReportes.setVisible(true);
        this.jpnl_back.setVisible(false);
        this.jpnlReportes.requestFocus();
      }

      if (tipoSalida.getTipo().equals("Excel"))
      {
        boolean ext = true;
        while (ext)
        {
          File b = null;
          String nombreArchivoExcel = "";
          jdl_nombreArchivoExcel dnom = new jdl_nombreArchivoExcel(new JDialog(), true);
          dnom.setVisible(true);
          if (dnom.getNombre().equals("salida")) {
            return;
          }
          nombreArchivoExcel = dnom.getNombre();

          b = new File("C:\\" + nombreArchivoExcel + ".xls");
          if (b.exists())
          {
            new jdlg_error("¡El archivo existe!", " Favor de introducir otro nombre", " Archivo existente").setVisible(true);
            System.out.println("El archivo existe...");

            ext = true;
          }
          else
          {
            ext = false;
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, this.print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "c:\\" + nombreArchivoExcel + ".xls");
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.exportReport();
            jdlg_pregunta_SN psn2 = new jdlg_pregunta_SN("Exportacion completada", "Los datos se exportaron correctamente ¿Desea ver el archivo?");
            psn2.setVisible(true);
            if (this.respuestaSN)
            {
              try
              {
                Process p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL C://"+nombreArchivoExcel+".xls"); //prueba.xls");
              }
              catch (Throwable exc4)
              {
                Process p;
                System.out.println("No se puede abrir el archivo de Excel.\n");
                exc4.printStackTrace();

              }
            }
          }
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Connection conection() throws ClassNotFoundException, SQLException, NamingException
  {
    Connection conn = null;
    try {
      Context initContext = new InitialContext();
      DataSource ds = (DataSource)initContext.lookup("TMS_CENTRAL_DB");
      conn = ds.getConnection();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return conn;
  }

//  private TmsUsuariosTblFacadeRemote lookupTmsUsuariosTblFacadeRemote()
//  {
//    try
//    {
//      Context c = new InitialContext();
//      return (TmsUsuariosTblFacadeRemote)c.lookup("tmsReportesRecauda.solicitud.TmsUsuariosTblFacadeRemote");
//    }
//    catch (NamingException ne) {
//      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
//    }throw new RuntimeException(ne);
//  }
  
    private TmsUsuariosTblFacadeRemote lookupTmsUsuariosTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsReportesRecauda.solicitud.TmsUsuariosTblFacadeRemote) c.lookup("tmsReportesRecauda.solicitud.TmsUsuariosTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }  

  public void setFoco()
  {
    this.jcmbx_reportes.requestFocus();
  }

  public KeyEvent getEventoTeclado() {
    return this.eventoTeclado;
  }
  public void setEventoTeclado(KeyEvent evento) { this.eventoTeclado = evento;
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
      this.jlbl_barraEstado.setForeground(new Color(0, 0, 0));
      this.jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
      this.jlbl_barraEstado.setBorder(BorderFactory.createBevelBorder(1));

      this.jlbl_mensaje.setFont(new Font("Arial", 1, 12));
      this.jlbl_mensaje.setText("sdfsdsdfsdfsdfsdfsdf");
      this.jlbl_mensaje.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
          JIFReportesRecauda.jdlg_pregunta_SN.this.jlbl_mensajeKeyPressed(evt);
        }
      });
      GroupLayout layout = new GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jLabel1).add(14, 14, 14).add(this.jlbl_mensaje, -1, 321, 32767).add(424, 424, 424)).add(this.jlbl_barraEstado, -1, 800, 32767));

      layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(21, 21, 21).add(layout.createParallelGroup(3).add(this.jLabel1, -2, 42, -2).add(this.jlbl_mensaje)).addPreferredGap(0, 59, 32767).add(this.jlbl_barraEstado, -2, 27, -2)));

      pack();
    }

    private void jlbl_mensajeKeyPressed(KeyEvent evt) {
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          respuestaSN = false;
          this.dispose();
      }
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
          respuestaSN = true;
          this.dispose();
      }
    }
  }
 private int calculaDV_Alg35(String nr){
  Vector<Integer> inicial= new Vector<Integer>();
  Vector<Integer> segundo= new Vector<Integer>();
  Vector<String> letras= new Vector<String>();
  Vector<String> numeros= new Vector<String>();

  int n = 0;
  //int A = 1, B = 2,C = 3,D = 4,E = 5,F= 6, G = 7,H = 8,I = 9,J = 10,K = 11, L = 12, M = 13,N = 14, O = 15, P = 16, Q = 17, R=18,S = 19,T = 20, U = 21, V = 22, W = 23,X = 24, Y = 25,Z = 26;
  letras.add("");
  letras.add("A");letras.add("B");letras.add("C");letras.add("D");letras.add("E");letras.add("F");letras.add("G");letras.add("H");
  letras.add("I");letras.add("J");letras.add("K");letras.add("L");letras.add("M");letras.add("N");letras.add("O");letras.add("P");
  letras.add("Q");letras.add("R");letras.add("S");letras.add("T");letras.add("U");letras.add("V");letras.add("W");letras.add("X");
  letras.add("Y");letras.add("Z");
  numeros.add("0");numeros.add("1");numeros.add("2");numeros.add("3");numeros.add("4");numeros.add("5");numeros.add("6");numeros.add("7");numeros.add("8");numeros.add("9");
  char[] car = nr.toCharArray();
  for(int i=0; i<car.length; i++)
  {
      char c = car[i];
      int index = letras.indexOf(""+c);
      if(index>=0)
          inicial.add(index);
      else
          inicial.add(numeros.indexOf(""+c));
  }
 //System.out.println("Paso1: "+inicial);
  for(int i = 0; i<inicial.size(); i++){
      for(int j = 1; j<=3; j++)
      {
          //System.out.println("i = "+i);
          if(i > (inicial.size() - 1))
              break;
          if(j==1)segundo.add(regresaUno(""+(((int)inicial.get(i))*4)));
          if(j==2)segundo.add(regresaUno(""+(((int)inicial.get(i))*3)));
          if(j==3)segundo.add(regresaUno(""+(((int)inicial.get(i))*8)));
          i++;
      }
      i--;
  }
  //System.out.println("Paso2: "+segundo);
  int sum = 0;
  for(int i=0; i<segundo.size(); i++)
      sum = sum + ((int) segundo.get(i));
  //System.out.println("sumaPaso2: "+sum);
  int prox = regresaProximaDecena(""+sum);
  //System.out.println("Resta: "+prox +" - "+sum);
  n = prox - sum;
  return n;
  }

 private int regresaUno(String num){
    //System.out.println("llamo regresaUno con: "+num);
     int unNum = 0;
     if(num.length()==1)
         return Integer.valueOf(num);
     else
     {

         while(num.length()>1)
         {
             String n = ""+num;
             char[] cn = n.toCharArray();
             int nn = 0;
             for(int i=0; i<cn.length; i++)
              nn = nn+ Integer.valueOf(""+cn[i]);
             num = ""+nn;
           // System.out.println("  "+num);
         }
         unNum = Integer.valueOf(num);
     }
     return unNum;
 }

 private int regresaProximaDecena(String num){
    //System.out.println("Decena: "+ num);
     //int decena = 0;
     //char[] cd = num.toCharArray();
     char[] cdp = num.toCharArray();
     char numDecena = cdp[cdp.length -2];
     String s = "";
     //System.out.println("cdp[cdp.length -1] = "+cdp[cdp.length -1]);
     if((""+cdp[cdp.length -1]).equals("0") )
        s = ""+ numDecena;
     else
        s = ""+(Integer.valueOf(""+numDecena) + 1);
     cdp[cdp.length -2] = (s.toCharArray())[0];
     cdp[cdp.length -1] = '0';
     //int decenaProxima = 0;
     String dp = "";
     for(int i=0; i<cdp.length; i++)
         dp = dp +""+cdp[i];
     //System.out.println("Decena Proxima: "+ dp);
     return Integer.valueOf(dp);
 }

}