/*
 * JIFIncidencia.java
 *
 * Created on 7 de noviembre de 2007, 06:47 PM
 */

package tms_incidencias;

import DialogosIncidencias.JDlgAceptar;
import DialogosIncidencias.JDlgCalendario;
import DialogosIncidencias.JDlgDatosAdicionales;
import DialogosIncidencias.JDlgIncDetalle;
import DialogosIncidencias.JDlgOperador;
import DialogosIncidencias.JDlgSiNo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import tms_TextFields.JDateTextField;
import tms_TextFields.JHourTextField;
import tms_TextFields.JNumberTextField;
import tms_incidencias.entidad.TmsOperadorIncidenciasTbl;
import tms_incidencias.rol.JDlgComentarios;
import tms_incidencias.rol.JDlgPMT;

/**
 *
 * @author  ocruz  
 */  
public class JIFIncidencia extends javax.swing.JInternalFrame {
private Connection jasperConnection;
private int[] diasPorMes = {    31, 28, 31, 30, 31, 30,
                                31, 31, 30, 31, 30, 31
                            };    
private Hashtable htIncidencias= null;  // guarda las descripciones de todas incidencias
private Hashtable htValorGuartdia= null; //  Valores de las para las guardias que dependen del servicio
private String ClaveIncidencia="";
private boolean ftrace = true;


    /** Creates new form JIFIncidencia */
    public JIFIncidencia(Vector datosIniciales) throws Exception {
        InicioGral = true;
        this.USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString());        
        this.USUARIO_NUMERO = datosIniciales.get(1).toString();
        this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
        this.SESION_ID = Long.valueOf(datosIniciales.get(3).toString());
        this.MENU_ID = Long.valueOf(datosIniciales.get(4).toString());
        sesionVenta = new SesionVenta();
        int error = sesionVenta.proceso(MENU_ID, 
                datosIniciales.get(5).toString(),
                Integer.valueOf(datosIniciales.get(6).toString()));       
        switch(error){
            case -1: DialogoAceptar = new JDlgAceptar("¡No existe una conexión válida con la Base de Datos!","Contacte al administrador del sistema.",Color.RED);
            break;
            case 1: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Terminal incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 2: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Incidencias).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 3: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Servicios).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 31: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Empresas).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 4: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Estados).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 41: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Operadores).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 5: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Parametros).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 10: DialogoAceptar = new JDlgAceptar("¡Configuracion de DBLink Central incorrecta!", "Contacte al administrador del sistema.", Color.RED);
            break;
        }
        if(error != 0){
            InicioGral=false;
            return;
        }
        initComponents();
        
        htIncidencias= sesionVenta.getAllkindIncidencia();
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        jPanelDetalle.setLayout(new java.awt.BorderLayout());
        JPanelDetalle jpdetalle = new JPanelDetalle(sesionVenta);
        jPanelDetalle.add(jpdetalle, java.awt.BorderLayout.CENTER);

        //Se agrega el panel de excepciones
        panelExcepciones = new JPanelExcepciones(sesionVenta,this.USUARIO_ID );
        jPanelExcepciones.add(panelExcepciones, java.awt.BorderLayout.CENTER);
        //Se valida si el usuario tiene permisos para visualizar la pestaña
        //de Excepciones de Incidencia
        jTabbedPane1.setEnabledAt(2,sesionVenta.tienePermisosUsuario(USUARIO_ID, "5611"));
        inhabilitarF10();
        fillDates();
        int i;
        jCboClaveIncidencia.addItem("TODOS");
        
        for(i=0; i<sesionVenta.getTmsIncidenciasTbl().size(); i++)
            jCboClaveIncidencia.addItem(sesionVenta.getTmsIncidenciasTbl().get(i).getActividadClave());
        
        jCboClaveIncidencia.setSelectedIndex(0);
        jCboServicio.addItem("TODOS");
        for(i=0; i<sesionVenta.getTmsServiciosTbl().size(); i++)
            jCboServicio.addItem(sesionVenta.getTmsServiciosTbl().get(i).getServicioNombre());
        this.transaccion = TXBUSQ;
        // BRA  Asegura de tomar la hora actual 
         TimeZone.setDefault(sesionVenta.getTimeZone());
       
         // Trae la coneccion para generar los reportes en jasper  BRA
         try {
         jasperConnection = obtenConexion();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
         
         

    }
    
    private void inhabilitarF10() {
        this.jTxtNumeroOperador.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboServicio.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboClaveIncidencia.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
       /* this.jTxtValorIncidencia.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);*/
        this.jTxtFechaInicial.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFechaFinal.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
         this.jTxtHoraInicial.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtHoraFinal.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboEstado.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblDetalle.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
    }
    
    public boolean getInicioGral(){ return this.InicioGral; }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelIncidencias = new javax.swing.JPanel();
        jLblBarraEstado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtNumeroOperador = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtNombreOperador = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTxtNombreIncidencia = new javax.swing.JTextField();
        jTxtFechaInicial = new javax.swing.JTextField();
        jTxtFechaInicial = new JDateTextField();
        jLabel9 = new javax.swing.JLabel();
        jTxtFechaFinal = new javax.swing.JTextField();
        jTxtFechaFinal = new JDateTextField();
        jLabel10 = new javax.swing.JLabel();
        jCboClaveIncidencia = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jCboServicio = new javax.swing.JComboBox();
        jCboEstado = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtObservacion = new javax.swing.JTextField();
        jLabelHoraInicial = new javax.swing.JLabel();
        jTxtHoraInicial = new javax.swing.JTextField();
        jTxtHoraInicial = new JHourTextField();
        jLabelHoraFinal = new javax.swing.JLabel();
        jTxtHoraFinal = new javax.swing.JTextField();
        jTxtHoraFinal = new JHourTextField();
        jPanel2 = new javax.swing.JPanel();
        jScpDetalle = new javax.swing.JScrollPane();
        jTblDetalle = new javax.swing.JTable();
        jPanelDetalle = new javax.swing.JPanel();
        jLblBarraEstado1 = new javax.swing.JLabel();
        jPanelExcepciones = new javax.swing.JPanel();

        setIconifiable(true);
        setTitle("Incidencias");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        jPanelIncidencias.setLayout(new java.awt.BorderLayout());

        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblBarraEstado.setText("<html><font color=ff0000>IZQ/DER</font> Ingrese Criterio de Busqueda | <font color=ff0000>ENTER</font> Buscar <font color=ff0000>IZQ/DER</font> Ingrese Criterio de Busqueda |</html>");
        jLblBarraEstado.setPreferredSize(new java.awt.Dimension(531, 50));
        jPanelIncidencias.add(jLblBarraEstado, java.awt.BorderLayout.SOUTH);

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 150));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Operador");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Numero");

        jTxtNumeroOperador.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtNumeroOperador.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtNumeroOperador.setText("%");
        jTxtNumeroOperador.setFocusTraversalKeysEnabled(false);
        jTxtNumeroOperador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtNumeroOperadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtNumeroOperadorKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Nombre");

        jTxtNombreOperador.setEditable(false);
        jTxtNombreOperador.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtNombreOperador.setFocusable(false);
        jTxtNombreOperador.setFocusTraversalKeysEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setForeground(java.awt.Color.red);
        jLabel6.setText("Clave");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setForeground(java.awt.Color.red);
        jLabel7.setText("Incidencia");

        jTxtNombreIncidencia.setEditable(false);
        jTxtNombreIncidencia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtNombreIncidencia.setFocusable(false);
        jTxtNombreIncidencia.setFocusTraversalKeysEnabled(false);

        jTxtFechaInicial.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtFechaInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFechaInicial.setFocusTraversalKeysEnabled(false);
        jTxtFechaInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFechaInicialKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("Fecha Inicial");

        jTxtFechaFinal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtFechaFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFechaFinal.setFocusTraversalKeysEnabled(false);
        jTxtFechaFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFechaFinalKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setForeground(java.awt.Color.red);
        jLabel10.setText("Fecha Final");

        jCboClaveIncidencia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboClaveIncidencia.setFocusTraversalKeysEnabled(false);
        jCboClaveIncidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboClaveIncidenciaActionPerformed(evt);
            }
        });
        jCboClaveIncidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboClaveIncidenciaKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setForeground(java.awt.Color.red);
        jLabel11.setText("Estado");

        jCboServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboServicio.setFocusTraversalKeysEnabled(false);
        jCboServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboServicioActionPerformed(evt);
            }
        });
        jCboServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboServicioKeyPressed(evt);
            }
        });

        jCboEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "AUTORIZADA", "NO AUTORIZADA", "CANCELADA" }));
        jCboEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboEstadoKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Servicio");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Rev. 190614");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Observaciones:");

        jTxtObservacion.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtObservacion.setEnabled(false);
        jTxtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtObservacionKeyPressed(evt);
            }
        });

        jLabelHoraInicial.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelHoraInicial.setForeground(java.awt.Color.red);
        jLabelHoraInicial.setText("Hora Inicial");

        jTxtHoraInicial.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHoraInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFechaInicial.setFocusTraversalKeysEnabled(false);
        jTxtHoraInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtHoraInicialKeyPressed(evt);
            }
        });

        jLabelHoraFinal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelHoraFinal.setForeground(java.awt.Color.red);
        jLabelHoraFinal.setText("Hora Final");

        jTxtHoraFinal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHoraFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFechaInicial.setFocusTraversalKeysEnabled(false);
        jTxtHoraFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtHoraFinalKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 679, Short.MAX_VALUE)
                        .add(jLabel1))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6)
                            .add(jCboClaveIncidencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel7)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(1, 1, 1)
                                .add(jTxtNombreIncidencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 192, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel5))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(20, 20, 20)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel9)
                                    .add(jTxtFechaInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(3, 3, 3)
                                        .add(jLabelHoraInicial))
                                    .add(jTxtHoraInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTxtFechaFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel10)
                                        .add(38, 38, 38)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jTxtHoraFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabelHoraFinal))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel11)
                                    .add(jCboEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jTxtObservacion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jTxtNumeroOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel4)
                                .add(440, 440, 440))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jTxtNombreOperador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel12)
                                .add(151, 151, 151))
                            .add(jCboServicio, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jLabel4)
                    .add(jLabel12))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTxtNumeroOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtNombreOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jLabel6)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jCboClaveIncidencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jLabel7)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jTxtNombreIncidencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel10)
                                .add(jLabelHoraFinal))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jTxtFechaFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel9)
                                .add(jLabelHoraInicial))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jTxtFechaInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jTxtHoraInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jCboEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTxtHoraFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jTxtObservacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelIncidencias.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScpDetalle.setPreferredSize(new java.awt.Dimension(452, 390));

        jTblDetalle.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblDetalle.setModel(xTabla);
        jTblDetalle.setFocusTraversalKeysEnabled(false);
        jTblDetalle.getTableHeader().setReorderingAllowed(false);
        jTblDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTblDetalleKeyReleased(evt);
            }
        });
        jScpDetalle.setViewportView(jTblDetalle);

        jPanel2.add(jScpDetalle, java.awt.BorderLayout.CENTER);

        jPanelIncidencias.add(jPanel2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Incidencias", jPanelIncidencias);

        jPanelDetalle.setLayout(new java.awt.BorderLayout());

        jLblBarraEstado1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblBarraEstado1.setText("<html><font color=ff0000>IZQ/DER</font> Ingrese Criterio de Busqueda | <font color=ff0000>ENTER</font> Buscar <font color=ff0000>IZQ/DER</font> Ingrese Criterio de Busqueda |</html>");
        jPanelDetalle.add(jLblBarraEstado1, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Detalle", jPanelDetalle);

        jPanelExcepciones.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Excepciones", jPanelExcepciones);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCboServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboServicioActionPerformed
// TODO add your handling code here:
       
       
    }//GEN-LAST:event_jCboServicioActionPerformed
private void UpdateValorMedGuardia()
{
    ClaveIncidencia=(String)jCboClaveIncidencia.getSelectedItem();
     //jTxtValorIncidencia.setText("0");
   // System.out.println("Incidencia "+ClaveIncidencia+"  Servicio  "+jCboServicio.getSelectedIndex());
      if(ClaveIncidencia.equalsIgnoreCase("MEDGUA") && jCboServicio.getSelectedIndex() > 0)
          {
              String valor = getDatosMedGuardia
                             (String.valueOf(sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId()));
              // jTxtValorIncidencia.setText( (valor != null && valor.length()> 0? valor:"0"));
          }   
}

    private void jTxtNumeroOperadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNumeroOperadorKeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_jTxtNumeroOperadorKeyReleased

    
    private void jCboClaveIncidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboClaveIncidenciaActionPerformed

      ClaveIncidencia=jCboClaveIncidencia.getSelectedItem().toString();
      String DescIncidencia="";
      DescIncidencia= (String)htIncidencias.get(ClaveIncidencia);
      
      jTxtNombreIncidencia.setText(DescIncidencia);
      UpdateValorMedGuardia();  
    }//GEN-LAST:event_jCboClaveIncidenciaActionPerformed

    private void jTxtObservacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtObservacionKeyPressed
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                    //jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
                    String f1=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString();
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux1=f1.split(" ");
                    String[] aux2=f2.split(" ");
                    jTxtFechaInicial.setText(aux1[0]);
                    jTxtFechaFinal.setText(aux2[0]);
                     jTxtHoraInicial.setText(aux1[1]);
                    jTxtHoraFinal.setText(aux2[1]);

                    jTxtFechaInicial.setEnabled(false);
                    jTxtFechaFinal.setEnabled(false);
                    jTxtHoraFinal.setEnabled(false);
                    jTxtHoraInicial.setEnabled(false);
                    jTxtObservacion.setEnabled(false);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7:  registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                if(transaccion == TXMOD){
                    /*if(jTxtValorIncidencia.isEnabled()){
                        jTxtValorIncidencia.selectAll();
                        jTxtValorIncidencia.requestFocusInWindow();
                    }
                    else{*/
                        jTxtFechaInicial.selectAll();
                        jTxtFechaInicial.requestFocusInWindow();
                    //}
                    return;
                }
                jTxtNumeroOperador.selectAll();
                jTxtNumeroOperador.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                if(transaccion == TXREG){
                    jCboEstado.requestFocusInWindow();
                    return;
                }
                jTxtFechaFinal.selectAll();
                jTxtFechaFinal.requestFocusInWindow();
                break;
       
        }
    }//GEN-LAST:event_jTxtObservacionKeyPressed

    private void jTblDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblDetalleKeyReleased
    sesionVenta.setFilaIncidenciaSeleccionada(jTblDetalle.getSelectedRow());
        switch(evt.getKeyCode()){
            case KeyEvent.VK_UP: case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_DOWN: case KeyEvent.VK_PAGE_DOWN: encabezado(); break;
        }
    }//GEN-LAST:event_jTblDetalleKeyReleased

    private void jTblDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblDetalleKeyPressed
   //   trace("Techa presionda en la tabla "+evt.getKeyCode());
        sesionVenta.setFilaIncidenciaSeleccionada(jTblDetalle.getSelectedRow());
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_F6: modificarIncidencia(); break;
            case 118: modificarIncidencia(); break;
            case KeyEvent.VK_F9: autorizarIncidencia(); break;
            case 82: autorizarIncidencia(); break;
            case KeyEvent.VK_F12: cancelarIncidencia(); break;
        //    case 17: cancelarIncidencia(); break;
            case KeyEvent.VK_F11: generarReporte(); break;
            case 70: generarReporte(); break;
            case KeyEvent.VK_ESCAPE: limpieza(); break;
         }
    }//GEN-LAST:event_jTblDetalleKeyPressed

    private void jCboEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboEstadoKeyPressed
    switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                   // jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());


                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux2=f2.split(" ");
                    jTxtFechaFinal.setText(aux2[0]);
                   jTxtHoraFinal.setText(aux2[1]);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3));
                    jTblDetalle.requestFocusInWindow();
                   // jTxtValorIncidencia.setEnabled(false);  // BRA
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7: registrarIncidencia(); break;
             case 90: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                if(transaccion == TXREG){
                    jTxtObservacion.selectAll();
                    jTxtObservacion.requestFocusInWindow();
                    return;
                }
                jTxtNumeroOperador.selectAll();
                jTxtNumeroOperador.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jTxtHoraFinal.selectAll();
                jTxtHoraFinal.requestFocusInWindow();
                break;
        }
      
    }//GEN-LAST:event_jCboEstadoKeyPressed

    private void jCboServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboServicioKeyPressed
// TODO add your handling code here:
        UpdateValorMedGuardia();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                trace(" KeyEvent.VK_U ");  
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                    //jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
                   
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();                  
                    String[] aux2=f2.split(" ");    
                    jTxtFechaFinal.setText(aux2[0]); 
                    jTxtHoraFinal.setText(aux2[1]);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                   trace(" KeyEvent.VK_U ");  
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7: registrarIncidencia(); break;
            case 90: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                jCboClaveIncidencia.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jTxtNumeroOperador.selectAll();
                jTxtNumeroOperador.requestFocusInWindow();
                break;
              
        }
    }//GEN-LAST:event_jCboServicioKeyPressed

    private void jTxtFechaFinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFechaFinalKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                    //jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
                    String f1=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString();
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux1=f1.split(" ");
                    String[] aux2=f2.split(" ");
                    jTxtFechaInicial.setText(aux1[0]);
                    jTxtFechaFinal.setText(aux2[0]);
                     jTxtHoraInicial.setText(aux1[1]);
                    jTxtHoraFinal.setText(aux2[1]);

                    jTxtFechaInicial.setEnabled(false);
                    jTxtFechaFinal.setEnabled(false);
                    jTxtHoraFinal.setEnabled(false);
                    jTxtHoraInicial.setEnabled(false);
                    jTxtObservacion.setEnabled(false);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7:
                if(transaccion == TXMOD) registrarIncidencia(); break;
            case 90: if(transaccion == TXMOD) registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                if(transaccion == TXMOD){
                    jTxtHoraFinal.selectAll();
                    jTxtHoraFinal.requestFocusInWindow();
                    return;
                }
                jTxtHoraFinal.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jTxtHoraInicial.selectAll();
                jTxtHoraInicial.requestFocusInWindow();
                break;
        }
        
    }//GEN-LAST:event_jTxtFechaFinalKeyPressed

    private void jTxtFechaInicialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFechaInicialKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                     this.transaccion = TXBUSQ;
                   // jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
                    String f1=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString();
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux1=f1.split(" ");
                    String[] aux2=f2.split(" ");
                    jTxtFechaInicial.setText(aux1[0]);
                    jTxtFechaFinal.setText(aux2[0]);
                     jTxtHoraInicial.setText(aux1[1]);
                    jTxtHoraFinal.setText(aux2[1]);
                    jTxtFechaInicial.setEnabled(false);
                    jTxtFechaFinal.setEnabled(false);
                    jTxtHoraFinal.setEnabled(false);
                    jTxtHoraInicial.setEnabled(false);
                    jTxtObservacion.setEnabled(false);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7: 
                if(transaccion == TXMOD) registrarIncidencia(); break;
            case 90: 
                if(transaccion == TXMOD) registrarIncidencia(); break;
                
            case KeyEvent.VK_RIGHT:
                jTxtHoraInicial.selectAll();
                jTxtHoraInicial.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                if(transaccion == TXMOD){
                    /*if(jTxtValorIncidencia.isEnabled()){
                        jTxtValorIncidencia.selectAll();
                        jTxtValorIncidencia.requestFocusInWindow();
                    }
                    else{*/
                        jTxtObservacion.selectAll();
                        jTxtObservacion.requestFocusInWindow();
                    //}
                    return;
                }
                jCboClaveIncidencia.requestFocusInWindow();
                break;
                
           
        }
    }//GEN-LAST:event_jTxtFechaInicialKeyPressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
     AnchoColumnas();
     jLblBarraEstado.setText(mensajes.getMensajeComun(1));
     jTxtNumeroOperador.selectAll();
      setFoco();
    }//GEN-LAST:event_formComponentShown

    public void setFoco(){
        if(jTblDetalle.getRowCount()>0){
            jTblDetalle.requestFocusInWindow();
            return;
        }
        jTxtNumeroOperador.requestFocusInWindow();
    }
    
    private void jCboClaveIncidenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboClaveIncidenciaKeyPressed

        UpdateValorMedGuardia();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                  //  jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
                  
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();                  
                    String[] aux2=f2.split(" ");                    
                    jTxtFechaFinal.setText(aux2[0]);                   
                    jTxtHoraFinal.setText(aux2[1]);

                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7: registrarIncidencia(); break;
            case 90: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                muestraIncidencia(true);
                break;
            case KeyEvent.VK_LEFT:
                muestraIncidencia(false);
                break;
                
        }
    }//GEN-LAST:event_jCboClaveIncidenciaKeyPressed

    private void jTxtNumeroOperadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNumeroOperadorKeyPressed

        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                    //jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
                       String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux2=f2.split(" ");
                    jTxtFechaFinal.setText(aux2[0]);
                    jTxtHoraFinal.setText(aux2[1]);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8:        
                                 PMT(); break;
            case 89 :       
                                 PMT(); break;          
            //case KeyEvent.VK_F10: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                jCboServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jCboEstado.requestFocusInWindow();
                break;
            case KeyEvent.VK_D:
                if(evt.isControlDown()){
                    JDlgIncDetalle jdlDetalle = new JDlgIncDetalle();
                    jdlDetalle.setVisible(true);
                }
                break;     
        }
    }//GEN-LAST:event_jTxtNumeroOperadorKeyPressed

    private void jTxtHoraInicialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtHoraInicialKeyPressed
        // TODO add your handling code here:
                switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); }
                if(transaccion==TXMOD){
                     this.transaccion = TXBUSQ;
                   // jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
                    String f1=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString();
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux1=f1.split(" ");
                    String[] aux2=f2.split(" ");
                    jTxtFechaInicial.setText(aux1[0]);
                    jTxtFechaFinal.setText(aux2[0]);
                     jTxtHoraInicial.setText(aux1[1]);
                    jTxtHoraFinal.setText(aux2[1]);
                    jTxtFechaInicial.setEnabled(false);
                    jTxtFechaFinal.setEnabled(false);
                    jTxtHoraFinal.setEnabled(false);
                    jTxtHoraInicial.setEnabled(false);
                    jTxtObservacion.setEnabled(false);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3));
                    jTblDetalle.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown())
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7:
                if(transaccion == TXMOD) registrarIncidencia(); break;
            case 90:
                if(transaccion == TXMOD) registrarIncidencia(); break;

            case KeyEvent.VK_RIGHT:
                jTxtFechaFinal.selectAll();
                jTxtFechaFinal.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                if(transaccion == TXMOD){
                    /*if(jTxtValorIncidencia.isEnabled()){
                        jTxtValorIncidencia.selectAll();
                        jTxtValorIncidencia.requestFocusInWindow();
                    }
                    else{*/
                        jTxtFechaInicial.selectAll();
                        jTxtFechaInicial.requestFocusInWindow();
                    //}
                    return;
                }
                jCboClaveIncidencia.requestFocusInWindow();
                break;


        }
    }//GEN-LAST:event_jTxtHoraInicialKeyPressed

    private void jTxtHoraFinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtHoraFinalKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); }
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;

                    String f1=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString();
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux1=f1.split(" ");
                    String[] aux2=f2.split(" ");
                    jTxtFechaInicial.setText(aux1[0]);
                    jTxtFechaFinal.setText(aux2[0]);
                     jTxtHoraInicial.setText(aux1[1]);
                    jTxtHoraFinal.setText(aux2[1]);
                    jTxtFechaInicial.setEnabled(false);
                    jTxtFechaFinal.setEnabled(false);
                    jTxtHoraFinal.setEnabled(false);
                    jTxtHoraInicial.setEnabled(false);

                    jTxtObservacion.setEnabled(false);
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3));
                    jTblDetalle.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown())
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case 123: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F8: PMT(); break;
            case KeyEvent.VK_F7:
                if(transaccion == TXMOD) registrarIncidencia(); break;
            case 90: if(transaccion == TXMOD) registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                if(transaccion == TXMOD){
                    jTxtObservacion.selectAll();
                    jTxtObservacion.requestFocusInWindow();
                    return;
                }
                jCboEstado.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jTxtFechaFinal.selectAll();
                jTxtFechaFinal.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jTxtHoraFinalKeyPressed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
        panelExcepciones.restablecer();
    }//GEN-LAST:event_jTabbedPane1FocusGained
    
    private void CerrarVentana(){
        if(transaccion!=TXBUSQ) return;
        DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Desea cerrar la aplicación?");
        boolean r = DialogoSiNo.getResultado();
        if(!r) return;
        this.dispose();
    }

    private void seleccionarOperador() {
        if(transaccion!=TXREG) return;
        JDlgOperador jDlgOperador = new JDlgOperador(sesionVenta);
        jDlgOperador.setVisible(true);
        if(!jDlgOperador.getEvento()) return;
        jTxtNumeroOperador.setText(sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getClaveOperador());
        jTxtNombreOperador.setText(sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getOperadorNombreCompleto());
        jTxtObservacion.setEnabled(true);
        jTxtNumeroOperador.requestFocusInWindow();
    }

    private void muestraIncidencia(boolean der) {
        if(jCboClaveIncidencia.getSelectedIndex()==0){
            if(der){
                jTxtFechaInicial.selectAll();
                jTxtFechaInicial.requestFocusInWindow();
            }
            else jCboServicio.requestFocusInWindow();
            return;
        }
        //jTxtValorIncidencia.setEditable(true);
        jTxtNombreIncidencia.setText(sesionVenta.getAccionNombre(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getAccionId()));
        if(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getConfigurable().equals("N")){
            String vp = valorParamIncidencia();
            /*if(vp.equals("")) jTxtValorIncidencia.setText(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getIncidenciaValor1());
            else jTxtValorIncidencia.setText(vp);
            jTxtValorIncidencia.setEditable(false);*/
            if(der){
                jTxtFechaInicial.selectAll();
                jTxtFechaInicial.requestFocusInWindow();
            }
            else jCboServicio.requestFocusInWindow();
        }
        else{
            String vp = valorParamIncidencia();
           /* if(vp.equals("")) jTxtValorIncidencia.setText(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getIncidenciaValor1());
            else jTxtValorIncidencia.setText(vp);*/
         
            if(der){
                /*if(jTxtValorIncidencia.isEnabled()) jTxtValorIncidencia.requestFocusInWindow();
                else{*/
                    jTxtFechaInicial.selectAll();
                    jTxtFechaInicial.requestFocusInWindow();
                //}
            }
            else jCboServicio.requestFocusInWindow();
        }
    }

    private void registrarIncidencia() {
        if(transaccion==TXBUSQ) return; 
        if(!validaDatosIngresados()) return;
        trace("Si paso la validaDatosIngresados ");
        if(!sesionVenta.actAdicId(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getActividadClave())){
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar incidencia!", "<html>No existe un registro de esta incidencia<br>como actividad adicional.</html>", Color.RED);
            return;
        }
        if(transaccion==TXMOD){
            DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Realmente desea modificar la incidencia?");
            if(!DialogoSiNo.getResultado()) return;
            modificarIncidenciaX();
            return;
        }
        // NUEVA, POR LO TANTO NO SE PERMITE ESTADO CANCELADA
        if(jCboEstado.getSelectedItem().equals("CANCELADA")){
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar incidencia!", "Estado inválido.", Color.RED);
            jCboEstado.requestFocusInWindow();
            return;
        }
        // TIENE TARJETAS DE VIAJE PENDIENTES
   
        if(jCboEstado.getSelectedItem().equals("AUTORIZADA") && jCboClaveIncidencia.getSelectedItem().equals("VAC")){
            String nTV=null;
            nTV =  sesionVenta.tieneTVPR(jTxtNumeroOperador.getText());
            if(nTV==null){
                DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "Contacte al administrador del sistema.", Color.RED);
                return;
            }
         /*   if(!nTV.equals("0")){
                DialogoAceptar = new JDlgAceptar("¡No es posible autorizar vacaciones!", "<html>Este operador tiene "+nTV+" tarjetas de viaje<br>pendientes por " +
                        "dar.</html>", Color.RED);
                return;
            }  */  // BRA Se eleimo por que si se pueden capturar incidencia aunqu tenga tarjetas de viaje pendientes
        }
        if(validarExcepcionIncidencia()){            
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar incidencia!", "Verifique las Excepciones registradas de incidencias.", Color.RED);
            return;
        }
        
        if(operadorTieneIncidencias()){
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar incidencia!", "Este operador tiene otra incidencia en estas fechas.", Color.RED);
            return;
        }
        if(jCboEstado.getSelectedItem().equals("AUTORIZADA") && jTxtFechaFinal.getText().equals("")){
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar y autorizar la incidencia!", "Fecha final nula.", Color.RED);
            return;
        }
       
        String obsParte1, obsParte2="";
     /* obsParte1 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(0,30) : jTxtObservacion.getText());
        obsParte2 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(30,jTxtObservacion.getText().length()) : "");*/
        obsParte1 = jTxtObservacion.getText().trim();
        Timestamp tmActual = new Timestamp(new Date().getTime());
        TmsOperadorIncidenciasTbl tmsOperadorIncidenciasTbl = new TmsOperadorIncidenciasTbl();
        tmsOperadorIncidenciasTbl.setOperadoresId(sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getOperadorId());
        tmsOperadorIncidenciasTbl.setIncidenciaId(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getTipoActividadAdicionalId());
        tmsOperadorIncidenciasTbl.setFechaInicial(tmFechaYHora1);
        tmsOperadorIncidenciasTbl.setFechaFinal(tmFechaYHora2);
        tmsOperadorIncidenciasTbl.setAdicional1(obsParte1);
     // BRA   tmsOperadorIncidenciasTbl.setAdicional2(obsParte2);
        //tmsOperadorIncidenciasTbl.setIncidenciaValor(jTxtValorIncidencia.getText());
        tmsOperadorIncidenciasTbl.setIncidenciaAutorizada(jCboEstado.getSelectedItem().equals("AUTORIZADA") ? "S" : "N");
        tmsOperadorIncidenciasTbl.setServicioId(sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId());
        tmsOperadorIncidenciasTbl.setCreadoPor(this.USUARIO_ID);
        tmsOperadorIncidenciasTbl.setFechaCreacion(tmActual);
        tmsOperadorIncidenciasTbl.setUltimaActualizacionPor(this.USUARIO_ID);
        tmsOperadorIncidenciasTbl.setUltimaFechaActualizacion(tmActual);
        
       //*********************** 
        // Registrandio Incidencia
        if(!sesionVenta.registrarIncidencia(tmsOperadorIncidenciasTbl))
             DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "<html>No se registro la incidencia.<br>Contacte al administrador del sistema.</html>", Color.RED);
          else
              DialogoAceptar = new JDlgAceptar("¡Incidencia registrada!", "Incidencia registrada correctamente a operador.", Color.BLUE);
       // Si es Utorizada y requiere Aplicar en Recauda
        if(tmsOperadorIncidenciasTbl.getIncidenciaAutorizada().equals("S"))    
        {// if(tmsOperadorIncidenciasTbl.getIncidenciaAutorizada().equals("S")){  INCIDENCIA AUTORIZADA
           
           DialogoAceptar = new JDlgAceptar("¡Autorizacion realizada correctamente!", "Presione Enter para continuar...", Color.BLUE);
             if(!sesionVenta.modificaEstadoOperador(this.USUARIO_ID, tmsOperadorIncidenciasTbl.getOperadorId(),
                             sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getClaveOperador(),
                             tmsOperadorIncidenciasTbl.getIncidenciaValor()))
                 DialogoAceptar = new JDlgAceptar("Estado de operador.", "</html>No fue posible cambiar el estado del operador<br>" +
                                         "a fuera del rol causado por incidencia.</html>.", Color.RED);
                         
                
            }
        limpieza();
    }

   
    
    private boolean validaDatosIngresados(){
        if(jTxtNumeroOperador.getText().equals("%") || jTxtNumeroOperador.getText().equals("")){
            jTxtNumeroOperador.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Operador invalido!", "Seleccione un operador.", Color.RED);
            jTxtNumeroOperador.requestFocusInWindow();
            return false;
        }
        
        if(jTxtNombreOperador.getText().equals("")){
            jTxtNumeroOperador.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Operador invalido!", "Seleccione un operador.", Color.RED);
            jTxtNumeroOperador.requestFocusInWindow();
            return false;
        }
        
        /*if(jTxtValorIncidencia.getText().equals("")){
            DialogoAceptar = new JDlgAceptar("¡Valor incorrecto!", "Ingrese valor de incidencia.", Color.RED);
            jTxtValorIncidencia.requestFocusInWindow();
            return false;
        }*/
        if(jTxtFechaInicial.getText().equals("") || jTxtFechaInicial.getText().length()<10){
            jTxtFechaInicial.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Fecha inicial es obligatoria!", "Ingrese fecha.", Color.RED);
            jTxtFechaInicial.requestFocusInWindow();
            return false;
        }
        
        if(jTxtFechaFinal.getText().equals("") || jTxtFechaFinal.getText().length()<10){
            jTxtFechaFinal.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "Ingrese fecha.", Color.RED);
            jTxtFechaFinal.requestFocusInWindow();
            return false;
        }
         if(jTxtHoraFinal.getText().equals("") || jTxtHoraFinal.getText().length()<5){
            jTxtHoraFinal.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Hora final invalida!", "Ingrese hora.", Color.RED);
            jTxtHoraFinal.requestFocusInWindow();
            return false;
        }
        if(jTxtHoraInicial.getText().equals("") || jTxtHoraInicial.getText().length()<5){
            jTxtHoraInicial.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Hora inicial invalida!", "Ingrese hora.", Color.RED);
            jTxtHoraInicial.requestFocusInWindow();
            return false;
        }
        try {
            tmFechaYHora1 = new Timestamp(formatoFechaHora.parse(jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()+":00").getTime());
        } catch (ParseException ex) {
            DialogoAceptar = new JDlgAceptar("¡Fecha inicial invalida!", "Ingrese fecha y hora  correcta.", Color.RED);
            return false;
        }
        try {
            tmFechaYHora2 = new Timestamp(formatoFechaHora.parse(jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()+":00").getTime());
        } catch (ParseException ex) {
            DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "Ingrese fecha y hora correcta.", Color.RED);
            return false;
        }
        if(tmFechaYHora1.getTime()>tmFechaYHora2.getTime()){
            DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "La fecha final no debe ser menor a la fecha inicial.", Color.RED);
            return false;
        }
        
        if(jCboServicio.getSelectedItem().equals("TODOS")){
            DialogoAceptar = new JDlgAceptar("¡Servicio invalido!", "Seleccione un servicio de la lista.", Color.RED);
            jCboServicio.requestFocusInWindow();
            return false;
        }
        
        if(jCboClaveIncidencia.getSelectedItem().equals("TODOS")){
            DialogoAceptar = new JDlgAceptar("¡Incidencia invalida!", "Seleccione una clave de incidencia de la lista.", Color.RED);
            jCboClaveIncidencia.requestFocusInWindow();
            return false;
        }
        
        if(jCboEstado.getSelectedItem().equals("TODOS")){
            DialogoAceptar = new JDlgAceptar("¡Estado invalido!", "Seleccione un estado de la lista.", Color.RED);
            jCboEstado.requestFocusInWindow();
            return false;
        }
          return true;
    }
    
    private boolean validaDatosIngresadosBusqueda(){
        if(jTxtFechaInicial.getText().length()>1 && jTxtFechaInicial.getText().length()<10){
            jTxtFechaInicial.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Fecha inicial invalida!", "Ingrese fecha completa o deje el campo vacio", Color.RED);
            jTxtFechaInicial.requestFocusInWindow();
            return false;
        }

        if(jTxtHoraInicial.getText().length()>1 && jTxtHoraInicial.getText().length()<5){
            jTxtHoraInicial.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Hora inicial invalida!", "Ingrese hora y minutos o deje el campo vacio", Color.RED);
            jTxtHoraInicial.requestFocusInWindow();
            return false;
        }

        
        if(!jTxtFechaInicial.getText().equals("")){
            try {
                tmFechaYHora1 = new Timestamp(formatoFecha.parse(jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()+":00").getTime());
            } catch (ParseException ex) {
                DialogoAceptar = new JDlgAceptar("¡Fecha inicial invalida!", "Ingrese fecha correcta.", Color.RED);
                return false;
            }
        }

        
        if(jTxtHoraFinal.getText().length()>1 && jTxtFechaFinal.getText().length()<5){
            jTxtHoraFinal.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Hora final invalida!", "Ingrese hora y minutos o deje el campo vacio", Color.RED);
            jTxtHoraFinal.requestFocusInWindow();
            return false;
        }
        
        if(!jTxtFechaFinal.getText().equals("")){
            try {
                tmFechaYHora2 = new Timestamp(formatoFechaHora.parse(jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()+":00").getTime());
            } catch (ParseException ex) {
                DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "Ingrese fecha correcta.", Color.RED);
                return false;
            }



            if(tmFechaYHora1.getTime()>tmFechaYHora2.getTime()){
                DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "La fecha final no debe ser menor a la fecha inicial.", Color.RED);
                return false;
            }
        }
        
        return true;
    }
    
    private void limpieza() {
        transaccion = TXBUSQ;
        jTxtNumeroOperador.setEnabled(true);
        jCboServicio.setEnabled(true);
        jCboClaveIncidencia.setEnabled(true);
        jTxtFechaInicial.setEnabled(true);
        jTxtFechaFinal.setEnabled(true);
        jTxtHoraInicial.setEnabled(true);
        jTxtHoraFinal.setEnabled(true);
        jTxtObservacion.setEnabled(false);
        jCboEstado.setEnabled(true);
      //  jTxtValorIncidencia.setEnabled(false);  // BRA
        jTxtNombreOperador.setText("");
        jTxtNumeroOperador.setText("%");
        jCboClaveIncidencia.setSelectedIndex(0);
        jCboServicio.setSelectedIndex(0);
        jCboEstado.setSelectedIndex(0);
        jTxtFechaInicial.setText("");
        jTxtFechaFinal.setText("");
        jTxtHoraInicial.setText("");
        jTxtHoraFinal.setText("");
        jTxtObservacion.setText("");
       // jTxtValorIncidencia.setText("0");
        xTabla.setDataVector(null, encabezado);
        AnchoColumnas();
        jTxtNumeroOperador.selectAll();
        jLblBarraEstado.setText(mensajes.getMensajeComun(1));
        jTxtNumeroOperador.requestFocusInWindow();
      
        fillDates();
    }
    
    private void limpiezaRegistrar() {
        jTxtNumeroOperador.setEnabled(true);
        jCboServicio.setEnabled(true);
        jTxtFechaInicial.setEnabled(true);
        jTxtFechaFinal.setEnabled(true);
        jTxtHoraInicial.setEnabled(true);
        jTxtHoraFinal.setEnabled(true);
        jTxtObservacion.setEnabled(true);
        jCboEstado.setEnabled(true);
        jTxtNumeroOperador.setText("");
        jCboClaveIncidencia.setSelectedIndex(1);
        jTxtNombreIncidencia.setText(sesionVenta.getAccionNombre(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getAccionId()));
        /*if(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getConfigurable().equals("N")){
            jTxtValorIncidencia.setText(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getIncidenciaValor1());
            jTxtValorIncidencia.setEditable(false);
        }
        else jTxtValorIncidencia.setEditable(true);*/
        jTxtFechaInicial.setText("");
        jTxtFechaFinal.setText("");
         jTxtHoraFinal.setText("");
         jTxtHoraInicial.setText("");
        jTxtObservacion.setText("");
        // jTxtValorIncidencia.setText("0");
        jTxtNumeroOperador.selectAll();
        jLblBarraEstado.setText(mensajes.getMensajeComun(2)); 
        jTxtNumeroOperador.requestFocusInWindow();
        fillDates();
       
    }
    
    private void limpiezaMod() {
        // BRA
        if ( !jTblDetalle.getModel().getValueAt(jTblDetalle.getSelectedRow(),1).toString().trim().equalsIgnoreCase("GUA"))
         {
            encabezado1(); 
            //*******
            jTxtNumeroOperador.setEnabled(false);
            jCboServicio.setEnabled(false);
            jCboClaveIncidencia.setEnabled(false);
            jTxtFechaInicial.setEnabled(true);
            jTxtFechaFinal.setEnabled(true);
            jTxtHoraInicial.setEnabled(true);
            jTxtHoraFinal.setEnabled(true);
            jTxtObservacion.setEnabled(true);
            jCboEstado.setEnabled(false);
             //jTxtValorIncidencia.setText("0");
             //jTxtValorIncidencia.setEditable(true);
        }
        else
              DialogoAceptar = new JDlgAceptar("¡Incidencia a modificar!", "  Esta incidencia no se puede moficiar en esta aplicaión.", Color.RED); 
    }

    private void ejecutaConsulta() {
        if(transaccion!=TXBUSQ) return;
        if(ValidaFechas())
            { 
            Object[][] resultado = sesionVenta.busqueda(jTxtNumeroOperador.getText(), jCboServicio.getSelectedItem().toString(),
            jCboClaveIncidencia.getSelectedItem().toString(), jTxtFechaInicial.getText(), jTxtFechaFinal.getText(), jCboEstado.getSelectedItem().toString());
            if(resultado == null){
                DialogoAceptar = new JDlgAceptar("¡No existen incidencias!", "El criterio de busqueda no genero resultados.", Color.RED);
                limpieza();
                return;   
            } 
            jTxtNumeroOperador.setEnabled(false);
            jCboServicio.setEnabled(false);
            jCboClaveIncidencia.setEnabled(false);
            jTxtFechaInicial.setEnabled(false);
            jTxtFechaFinal.setEnabled(false);
            jTxtHoraInicial.setEnabled(false);
            jTxtHoraFinal.setEnabled(false);
            jTxtObservacion.setEnabled(false);
            jCboEstado.setEnabled(false);
            xTabla.setDataVector(resultado, encabezado);
            AnchoColumnas();
            jTblDetalle.setRowSelectionInterval(0,0);
            encabezado();
            jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
            jTblDetalle.requestFocusInWindow();
            }
    }
    
    private void AnchoColumnas() {
        int anchoContenedor = jScpDetalle.getWidth();
        TableColumn column;
        for (int i = 0; i < jTblDetalle.getColumnCount(); i++) {
            column = jTblDetalle.getColumnModel().getColumn(i);
            switch (i) {
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); break;               
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.25)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
            }
        }
    }

    private void encabezado() {
     /*   System.out.println("encabezado "+encabezado);
        sesionVenta.setFilaIncidenciaSeleccionada(jTblDetalle.getSelectedRow());
        jTxtNumeroOperador.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getClaveOperador());
        jTxtNombreOperador.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperadorNombreCompleto());
        jCboServicio.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getServicioNombre());
        jCboClaveIncidencia.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaClave());
        jTxtNombreIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaNombre());
        jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getConfigurable().equals("N")) jTxtValorIncidencia.setEditable(false);
        else jTxtValorIncidencia.setEditable(true);
        jTxtFechaInicial.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString());
        jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
        jCboEstado.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada());
        jTxtObservacion.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getObservacion());
      */
    }

       private void encabezado1() {
        sesionVenta.setFilaIncidenciaSeleccionada(jTblDetalle.getSelectedRow());
        
        jTxtNumeroOperador.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getClaveOperador());
        jTxtNombreOperador.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperadorNombreCompleto());
        jCboServicio.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getServicioNombre());
       // BRA jCboClaveIncidencia.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaClave());
      
        jCboClaveIncidencia.setSelectedItem(jTblDetalle.getModel().getValueAt(jTblDetalle.getSelectedRow(),1) );
       
        jTxtNombreIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaNombre());
       // jTxtValorIncidencia.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaValor());
        //if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getConfigurable().equals("N")) jTxtValorIncidencia.setEditable(false);
       
                    String f1=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString();
                    String f2=xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString();
                    String[] aux1=f1.split(" ");
                    String[] aux2=f2.split(" ");
                    jTxtFechaInicial.setText(aux1[0]);
                    jTxtFechaFinal.setText(aux2[0]);
                     jTxtHoraInicial.setText(aux1[1]);
                    jTxtHoraFinal.setText(aux2[1]);
                    
        jCboEstado.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada());
        jTxtObservacion.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getObservacion());
      
        trace("Clave seleccionada "+jCboClaveIncidencia.getSelectedItem().toString());
    } 
    
    private void modificarIncidencia() {
        
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada().equals("AUTORIZADA")
           ||sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada().equals("CANCELADA")){
            DialogoAceptar = new JDlgAceptar("¡Modificacion cancelada!", "La incidencia esta autorizada o cancelada.", Color.RED);
            return;
        }
        transaccion = TXMOD;
        limpiezaMod();
        jLblBarraEstado.setText(mensajes.getMensajeComun(4)); 
        jTxtFechaInicial.requestFocusInWindow();
    }

    private void autorizarIncidencia() {
      if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada().equals("AUTORIZADA")){
            DialogoAceptar = new JDlgAceptar("¡Autorizacion cancelada!", "La incidencia ya esta autorizada.", Color.RED);
            return;
        }
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getFechaFinal()==null){
            DialogoAceptar = new JDlgAceptar("¡Autorizacion cancelada!", "La incidencia aun no tiene fecha final.", Color.RED);
            return;
        }
        // TIENE TARJETAS DE VIAJE PENDIENTES?
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaClave().equals("VAC")){
            String nTV=null;
            nTV =  sesionVenta.tieneTVPR(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getClaveOperador());
            if(nTV==null){
                DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "Contacte al administrador del sistema.", Color.RED);
                return;
            }
         /*   if(!nTV.equals("0")){
                DialogoAceptar = new JDlgAceptar("¡No es posible autorizar vacaciones!", "<html>Este operador tiene "+nTV+" tarjetas de viaje<br>pendientes por recaudar.</html>", Color.RED);
                return;
            }*/  // BRA  Se elimino esto por requerimiento de  usuarios
        }
        //
        DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Realmente desea autorizar la incidencia?");
        if(!DialogoSiNo.getResultado()) return;     
            if(!sesionVenta.autorizarIncidencia(this.USUARIO_ID))
                DialogoAceptar = new JDlgAceptar("¡No se autorizo la incidencia!", "<html>No existe conexion a la base de datos.<br>Contacte al administrador del sistema.</html>", Color.RED);
            else{                
                   DialogoAceptar = new JDlgAceptar("¡Autorizacion realizada correctamente!", "Presione Enter para continuar...", Color.BLUE);
                if(!sesionVenta.modificaEstadoOperador(this.USUARIO_ID, sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperadorId(), sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getClaveOperador(),
                    sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaClave()))
                    DialogoAceptar = new JDlgAceptar("Estado de operador.", "</html>No fue posible cambiar el estado del operador<br>" +
                            "a fuera del rol causado por incidencia.</html>.", Color.RED);
            }
      
       
        limpieza();
    }
    
    private void cancelarIncidencia() {
     
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada().equals("AUTORIZADA")){
            DialogoAceptar = new JDlgAceptar("Incidencias.", "La incidencia ya esta autorizada.", Color.RED);
            return;
        }
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada().equals("CANCELADA")){
            DialogoAceptar = new JDlgAceptar("Incidencias.", "La incidencia ya esta cancelada.", Color.RED);
            return;
        }
        DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Realmente desea cancelar la incidencia?");
        if(!DialogoSiNo.getResultado()) return;
        
        if(!sesionVenta.cancelaIncidencia(this.USUARIO_ID))
            DialogoAceptar = new JDlgAceptar("¡No se cancelo la incidencia!", "Contacte al administrador del sistema.", Color.RED);
        else
            DialogoAceptar = new JDlgAceptar("Incidencia Cancelada.", "Cancelacion realizada correctamente.", Color.RED);
        limpieza();
    }  //  cancelarIncidencia

    private void modificarIncidenciaX() {
        String obsParte1, obsParte2="";
     /* obsParte1 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(0,30) : jTxtObservacion.getText());
        obsParte2 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(30,jTxtObservacion.getText().length()) : "");*/
        obsParte1 = jTxtObservacion.getText();
       /* if(!sesionVenta.modificaIncidencia(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperIncidenciaId(),
                jTxtValorIncidencia.getText(), tmFecha, tmFecha2, obsParte1, obsParte2, this.USUARIO_ID))*/

             if(!sesionVenta.modificaIncidencia(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperIncidenciaId(),
                "", tmFechaYHora1, tmFechaYHora2, obsParte1, obsParte2, this.USUARIO_ID))
            DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "<html>No se modifico la incidencia.<br>Contacte al administrador del sistema.</html>", Color.RED);
        else{
            DialogoAceptar = new JDlgAceptar("¡Incidencia modificada!", "Incidencia modificada correctamente.", Color.BLUE);
        }
        limpieza();
    }
    
    private String valorParamIncidencia(){
        if(jCboServicio.getSelectedIndex()<=0) return "";
        String p = sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getCodigoParametro();
        if(p==null || p.equals("")) return "";
        return sesionVenta.obtieneValorParamIncidencia(sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getTipoActividadAdicionalId(), sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId());
    }

    private boolean operadorTieneIncidencias() {
        String f1=jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()+":00";
        String f2=jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()+":00";
        return sesionVenta.tieneIncidencia(jTxtNumeroOperador.getText(), f1, f2);
    }
    private boolean validarExcepcionIncidencia() {
        String f1=jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()+":00";
        String f2=jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()+":00";
        return sesionVenta.validarExcepcionIncidencia(jTxtNumeroOperador.getText(), f1, f2,sesionVenta.getTmsIncidenciasTbl().get(jCboClaveIncidencia.getSelectedIndex()-1).getTipoActividadAdicionalId());
    }
    private String operadorTieneTVPR() {
        return sesionVenta.tieneTVPR(jTxtNumeroOperador.getText());
    }
    
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }

    private void PMT() {
     //   System.out.println("PMT.transaccion "+transaccion +"   TXBUSQ "+TXBUSQ);
        if(transaccion!=TXBUSQ) return;
        JDlgPMT pmt = new JDlgPMT(sesionVenta);
        pmt.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCboClaveIncidencia;
    private javax.swing.JComboBox jCboEstado;
    private javax.swing.JComboBox jCboServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHoraFinal;
    private javax.swing.JLabel jLabelHoraInicial;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JLabel jLblBarraEstado1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDetalle;
    private javax.swing.JPanel jPanelExcepciones;
    private javax.swing.JPanel jPanelIncidencias;
    private javax.swing.JScrollPane jScpDetalle;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTblDetalle;
    private javax.swing.JTextField jTxtFechaFinal;
    private javax.swing.JTextField jTxtFechaInicial;
    private javax.swing.JTextField jTxtHoraFinal;
    private javax.swing.JTextField jTxtHoraInicial;
    private javax.swing.JTextField jTxtNombreIncidencia;
    private javax.swing.JTextField jTxtNombreOperador;
    private javax.swing.JTextField jTxtNumeroOperador;
    private javax.swing.JTextField jTxtObservacion;
    // End of variables declaration//GEN-END:variables
    private long MENU_ID;
    private long USUARIO_ID;
    private String USUARIO_NUMERO;
    private String USUARIO_NOMBRE;
    private long SESION_ID;
    private boolean InicioGral;
    private SesionVenta sesionVenta;
    private JDlgAceptar DialogoAceptar;
    private JDlgSiNo DialogoSiNo;
    private SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private Timestamp tmFecha, tmFecha2,tmFechaYHora1,tmFechaYHora2;
    private final long dia = 86400000;
    
    private final int TXBUSQ=0;
    private final int TXREG=1;
    private final int TXMOD=2;
    private int transaccion=TXBUSQ;
    private JPanelExcepciones panelExcepciones;
    
    private Object[] encabezado = {"Operador", "Incidencia", "Fecha Inicial", "Fecha Final", "Servicio", "Estado"};
    private DefaultTableModel xTabla = new DefaultTableModel(null,encabezado){
        public boolean isCellEditable(int row, int col){
            return false;
        }
    };
    
    Mensajes mensajes = new Mensajes();
    private KeyEvent eventoTeclado;
    

// BRA muestra el mes en curso
    public void fillDates()
    {
     //Date hoy = new Date();
        try {
            
        
             SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
           
               String  hoy= sesionVenta.getToDate();

              int  mes = Integer.valueOf(hoy.substring(3,5));    
              int  agno = Integer.valueOf(hoy.substring(6,10));
              String strmes="";
              if(String.valueOf(mes).length()==1)
                  strmes="0"+mes;
              else strmes=hoy.substring(3,5);
              jTxtFechaInicial.setText("01/"+strmes+"/"+agno);
              jTxtFechaFinal.setText(diasPorMes[mes-1]+"/"+strmes+"/"+agno);
              jTxtHoraInicial.setText("00:00");
              jTxtHoraFinal.setText("23:59");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener fecha del servidor "+e.getMessage());
        }
    }
    
    
// BRA Se agrego este metodo para generar el reporte de dicha consulta 08/2009
    public void generarReporte()
    {
        if(xTabla.getRowCount() >0)
          imprimir_ReporteJasper(); 
    }
    
    public void imprimir_ReporteJasper()
  {
     try {
            System.out.println("*****Entrando a generar reporte de jasper");
            String clave_operador=jTxtNumeroOperador.getText()!= null && jTxtNumeroOperador.getText().trim().length()> 0 && !jTxtNumeroOperador.getText().trim().equalsIgnoreCase("TODOS") ? jTxtNumeroOperador.getText().trim():""; ;
            String servicio=jCboServicio.getSelectedItem()!= null && jCboServicio.getSelectedItem().toString().length()> 0 && !jCboServicio.getSelectedItem().toString().equalsIgnoreCase("TODOS")  ? jCboServicio.getSelectedItem().toString().trim():null;
            String edo_incidencia=jCboEstado.getSelectedItem()!= null && jCboEstado.getSelectedItem().toString().length() > 0 && !jCboEstado.getSelectedItem().toString().equalsIgnoreCase("TODOS") ? jCboEstado.getSelectedItem().toString().trim():null;
            String actividad=jCboClaveIncidencia.getSelectedItem()!= null && jCboClaveIncidencia.getSelectedItem().toString().length() > 0 && !jCboClaveIncidencia.getSelectedItem().toString().equalsIgnoreCase("TODOS") ? jCboClaveIncidencia.getSelectedItem().toString().trim() :null;
            String fecha_ini=jTxtFechaInicial.getText();
            String fecha_fin=jTxtFechaFinal.getText();
           // 
            Map param = new HashMap();
            InputStream input =  getClass().getClassLoader().getResourceAsStream("Reportes/incidencias.jasper");
            InputStream imagenLogo = getClass().getClassLoader().getResourceAsStream("Reportes/logo.jpg");
            param.put("imagen",imagenLogo);
        
            param.put("clave_operador",(!clave_operador.equals("%")?clave_operador:""));
            param.put("servicio",servicio);
            param.put("clave_actividad",actividad);
            param.put("estado",edo_incidencia);  
            System.out.println("clave_operador"+jTxtNumeroOperador.getText().trim()+"  imagenLogo "+imagenLogo.toString());  
            param.put("fecha_ini",fecha_ini); 
            param.put("fecha_fin",fecha_fin);  
            System.out.println("fecha_ini "+jTxtFechaInicial.getText());  
            System.out.println("fecha_fin "+jTxtFechaFinal.getText());  
            //  
         
            JasperPrint print = null; 
            System.out.println("reporte : "+ input.toString());
            
            System.out.println("param : "+ param);
            
             
             if(edo_incidencia != null && edo_incidencia.equalsIgnoreCase("AUTORIZADA"))
                 edo_incidencia ="S";  
             else if(edo_incidencia != null && edo_incidencia.equalsIgnoreCase("NO AUTORIZADA"))
                 edo_incidencia ="N";
             else if(edo_incidencia != null && edo_incidencia.equalsIgnoreCase("CANCELADA"))
                 edo_incidencia ="C";
            
            System.out.println("clave_operador "+clave_operador);
            System.out.println("servicio "+servicio);
            System.out.println("edo_incidencia "+edo_incidencia);
            System.out.println("actividad "+actividad);
            System.out.println("fecha_ini "+fecha_ini);
            System.out.println("fecha_fin "+fecha_fin);
            
            // Genera Query para la consulta
             String query ="SELECT   O.CLAVE_OPERADOR, O.OPERADOR_NOMBRE_COMPLETO AS NOMBRE, "+
                          " I.ACCION,"+
                          " TO_CHAR(OI.FECHA_INICIAL,'DD/MM/RRRR') AS FECHA_INICIAL, "+ 
                          " TO_CHAR(OI.FECHA_FINAL,'DD/MM/RRRR') AS FECHA_FINAL , "+
                          " S.SERVICIO_NOMBRE AS SERVICIO, OI.INCIDENCIA_VALOR, "+
                          " DECODE(OI.INCIDENCIA_AUTORIZADA, 'S', 'AUTORIZADA','N','NO AUTORIZADA','C','CANCELADA') AS AUTORIZACION,"+
                          " OI.ADICIONAL1 || OI.ADICIONAL2 AS OBSERVACION "+
                          " FROM TMS_OPERADOR_INCIDENCIAS_TBL OI  "+
                          " ,TMS_OPERADORES_TBL O     "+
                          ",TMS_ACCIONES_TBL I  "+
                          ",TMS_ACT_ADICIONALES_TBL AA   "+
                          " ,TMS_SERVICIOS_TBL S  "+
                          " WHERE OI.OPERADOR_ID = O.OPERADOR_ID    "+
                          " AND   OI.INCIDENCIA_ID = AA.TIPO_ACTIVIDAD_ADICIONAL_ID    "+
                          " AND   I.ACCION_ID = AA.ACCION_ID  "+
                          " AND   OI.SERVICIO_ID = S.SERVICIO_ID  "+
                          " AND   O.CLAVE_OPERADOR LIKE NVL('"+clave_operador+"', O.CLAVE_OPERADOR)";
                     if(servicio !=null)  // 
                         query += " AND   S.SERVICIO_NOMBRE = NVL('"+servicio+"', S.SERVICIO_NOMBRE)  ";
                     if(edo_incidencia != null)
                          query += " AND   OI.INCIDENCIA_AUTORIZADA = NVL('"+edo_incidencia+"', OI.INCIDENCIA_AUTORIZADA) ";
                     if(actividad != null)             
                         query += " AND   AA.ACTIVIDAD_CLAVE = NVL('"+actividad+"', AA.ACTIVIDAD_CLAVE)  ";
             
                         query += "AND ( (  TRUNC(NVL(TO_DATE('"+fecha_ini+"','DD/MM/YYYY'), OI.FECHA_INICIAL))<=TRUNC(OI.FECHA_INICIAL) "+
                          " AND  TRUNC(NVL(TO_DATE('"+fecha_fin+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE)))>=TRUNC(NVL(OI.FECHA_FINAL,SYSDATE))  )"+
                          " OR    TRUNC(OI.FECHA_INICIAL) BETWEEN TO_DATE('"+fecha_ini+"','DD/MM/YYYY') AND TRUNC(LAST_DAY(TO_DATE('"+fecha_ini+"','DD/MM/YYYY')))  )"+
                          " AND  OI.INCIDENCIA_AUTORIZADA = NVL('',OI.INCIDENCIA_AUTORIZADA) "+
                          " ORDER BY O.CLAVE_OPERADOR, OI.FECHA_INICIAL DESC   ";
            // 
            System.out.println("QUERY "+query); 
            param.put("CONSULTA_SQL",query);  
            print=JasperFillManager.fillReport(input,param,jasperConnection);//new JREmptyDataSource()
            JRViewer er = new JRViewer(print);
            Dimension FrameSize = this.getSize();
            er.setBounds(0,0,735,463);
            JasperViewer.viewReport(print,false);
                                
            // 
            } catch(Exception e) {  // 
                e.printStackTrace();  // 
                System.out.println("Error al mandar el reporte "+e.getMessage());
            }        
          
     
  }   // 
    
    private Connection obtenConexion() throws NamingException {
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
    
    public boolean ValidaFechas()
    {
     SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
     boolean f_fechasval=false;
     try {
             if(jTxtFechaInicial.getText().trim().length() <=0)
                  DialogoAceptar = new JDlgAceptar("¡Verifique criterios de busqueda!", "<html>Proprocione Fecha Inicial.</html>", Color.RED);
             else if(jTxtFechaFinal.getText().trim().length() <=0)
                  DialogoAceptar = new JDlgAceptar("¡Verifique criterios de busqueda!", "<html>Proprocione Fecha Final.</html>", Color.RED);
             else if(jTxtHoraInicial.getText().trim().length() <=0)
                  DialogoAceptar = new JDlgAceptar("¡Verifique criterios de busqueda!", "<html>Proprocione Hora Inicial.</html>", Color.RED);
             else if(jTxtHoraFinal.getText().trim().length() <=0)
                  DialogoAceptar = new JDlgAceptar("¡Verifique criterios de busqueda!", "<html>Proprocione Hora Final.</html>", Color.RED);
             else{
                Date fec_ini= formatoDelTexto.parse(jTxtFechaInicial.getText());
                Date fec_fin= formatoDelTexto.parse(jTxtFechaFinal.getText());
               
                if(fec_ini.after(fec_fin))
                    DialogoAceptar = new JDlgAceptar("¡Verifique criterios de busqueda!", "<html>La Fecha Inicial no puede ser mayor a la Fecha Final.</html>", Color.RED);
                else
                    f_fechasval=true;
             }  // else 
         return f_fechasval;
         } catch (Exception e) {
          return false;   
         }
    }// ValidaFechas

    
     public String getDatosMedGuardia(String ServicioId){
        Hashtable hMedGuar= sesionVenta.getValoresMedGuardia();
        String valor=(String)hMedGuar.get(ServicioId);
         return valor;
    }

     private void trace(String msj)
     {
         if (ftrace)
           System.out.println(msj);
     }
}
