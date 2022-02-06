
/*
 * JIFGuardia.java
 *
 * Created on 7 de noviembre de 2007, 06:47 PM
 */

package tms_guardias;

import Dialogos.JDlgAceptar;
import Dialogos.JDlgCalendario;
import Dialogos.JDlgDatosAdicionales;
import Dialogos.JDlgOperador;
import Dialogos.JDlgSiNo;
import com.sun.xml.wss.impl.callback.TimestampValidationCallback.TimestampValidationException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_TextFields.JDateTextField;
import tms_TextFields.JHourTextField;
import tms_TextFields.JNumberTextField;
import tms_guardias.entidad.TmsOperadorIncidenciasTbl;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author  ocruz
 */
public class JIFGuardia extends javax.swing.JInternalFrame {
    /**
     * Creates new form JIFGuardia
     */
    public JIFGuardia(Vector datosIniciales) {
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
            case 2: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Datos Guardia).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 3: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Servicios).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 4: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Estados).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 5: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Configuracion incorrecta (Duracion Guardia).<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 6: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Carga de Terminales incorrecta.<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
            case 7: DialogoAceptar = new JDlgAceptar("¡No es posible iniciar la sesion!","<html>Impresora de TICKETS no instalada.<br>Contacte al administrador del sistema.</html>",Color.RED);
            break;
        }
        if(error != 0){
            InicioGral=false;
            return;
        }
        initComponents();
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        inhabilitarF10();
        this.nombreImpresora =  sesionVenta.getNombreImpresora();
        this.puerto = sesionVenta.getPuerto();
        this.nombreFormat = sesionVenta.getNombreFormato();
        System.out.println("TimeZone(Antes): " + TimeZone.getDefault());
        TimeZone.setDefault(sesionVenta.getTimeZone());
        System.out.println("TimeZone(Despues): " + TimeZone.getDefault());

        sesionVenta. FuncionesDeUsuario(this.MENU_ID, this.USUARIO_NUMERO);
        jcmbxTerminal.removeAllItems();
        String[] tok =sesionVenta.getTerminales().split(",");
        for(int y = 0; y<tok.length ;y++)
            jcmbxTerminal.addItem(tok[y]);
        jcmbxTerminal.setSelectedIndex(0);
        int i;
        jTxtValorIncidencia.setVisible(false);
        jTxtValorIncidencia.removeAll();
        jTxtCalculo.setVisible(false);
        jLabel7.setVisible(false);
        jTblDetalle.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        jCboServicio.addItem("TODOS");
        for(i=0; i<sesionVenta.getTmsServiciosTbl().size(); i++)
            jCboServicio.addItem(sesionVenta.getTmsServiciosTbl().get(i).getServicioNombre());
        
       
    }
    
    private void inhabilitarF10() {
        this.jTxtNumeroOperador.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jcmbxTerminal.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboServicio.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFechaInicial.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtHoraInicial.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFechaFinal.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtHoraFinal.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboEstado.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblDetalle.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblDetalle.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), JComponent.WHEN_FOCUSED);

    }
    
    public boolean getInicioGral(){ return this.InicioGral; }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtNumeroOperador = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtNombreOperador = new javax.swing.JTextField();
        jTxtValorIncidencia = new javax.swing.JTextField();
        new JNumberTextField();
        jLabel8 = new javax.swing.JLabel();
        jTxtFechaInicial = new javax.swing.JTextField();
        jTxtFechaInicial = new JDateTextField();
        jLabel9 = new javax.swing.JLabel();
        jTxtFechaFinal = new javax.swing.JTextField();
        jTxtFechaFinal = new JDateTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jCboServicio = new javax.swing.JComboBox();
        jCboEstado = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jTxtDuracionGuardia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTxtHoraInicial = new javax.swing.JTextField();
        jTxtHoraInicial = new JHourTextField();
        jTxtHoraFinal = new javax.swing.JTextField();
        jTxtHoraFinal = new JHourTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtCalculo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTxtObservacion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jcmbxTerminal = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jtxtViajes = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScpDetalle = new javax.swing.JScrollPane();
        jTblDetalle = new javax.swing.JTable();
        jLblBarraEstado = new javax.swing.JLabel();

        setIconifiable(true);
        setTitle("Guardias");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

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
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Nombre");

        jTxtNombreOperador.setEditable(false);
        jTxtNombreOperador.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtNombreOperador.setFocusable(false);
        jTxtNombreOperador.setFocusTraversalKeysEnabled(false);

        jTxtValorIncidencia.setEditable(false);
        jTxtValorIncidencia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtValorIncidencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtValorIncidencia.setFocusTraversalKeysEnabled(false);
        jTxtValorIncidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtValorIncidenciaKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setForeground(java.awt.Color.red);
        jLabel8.setText("Terminal");

        jTxtFechaInicial.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtFechaInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFechaInicial.setFocusTraversalKeysEnabled(false);
        jTxtFechaInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtFechaInicialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtFechaInicialFocusLost(evt);
            }
        });
        jTxtFechaInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFechaInicialKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("Fecha hora inicial");

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
        jLabel10.setText("Fecha hora final");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setForeground(java.awt.Color.red);
        jLabel11.setText("Estado Guardia");

        jCboServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboServicio.setFocusTraversalKeysEnabled(false);
        jCboServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboServicioKeyPressed(evt);
            }
        });

        jCboEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "AUTORIZADA", "NO AUTORIZADA" }));
        jCboEstado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboEstadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboEstadoFocusLost(evt);
            }
        });
        jCboEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboEstadoKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Servicio");

        jTxtDuracionGuardia.setEditable(false);
        jTxtDuracionGuardia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtDuracionGuardia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Guardia");

        jTxtHoraInicial.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHoraInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtHoraInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtHoraInicialActionPerformed(evt);
            }
        });
        jTxtHoraInicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtHoraInicialFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtHoraInicialFocusLost(evt);
            }
        });
        jTxtHoraInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtHoraInicialKeyPressed(evt);
            }
        });

        jTxtHoraFinal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHoraFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtHoraFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtHoraFinalKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Rev.220714");

        jTxtCalculo.setEditable(false);
        jTxtCalculo.setFont(new java.awt.Font("Tahoma", 1, 11));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setForeground(java.awt.Color.red);
        jLabel7.setText("Total $:");

        jTxtObservacion.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtObservacion.setEnabled(false);
        jTxtObservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtObservacionActionPerformed(evt);
            }
        });
        jTxtObservacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtObservacionFocusGained(evt);
            }
        });
        jTxtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtObservacionKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Observación:");

        jcmbxTerminal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbxTerminal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmbxTerminalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbxTerminalFocusLost(evt);
            }
        });
        jcmbxTerminal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbxTerminalKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setForeground(java.awt.Color.red);
        jLabel14.setText("Viajes Realizados");

        jtxtViajes.setEditable(false);
        jtxtViajes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(jTxtNumeroOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel4)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 513, Short.MAX_VALUE))
                                    .add(jTxtNombreOperador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel8)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jTxtValorIncidencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(jcmbxTerminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTxtObservacion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(jTxtFechaInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jTxtHoraInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel10)
                                            .add(jTxtFechaFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTxtHoraFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(65, 65, 65)))
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(5, 5, 5)
                                        .add(jLabel1))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTxtDuracionGuardia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(192, 192, 192)
                                .add(jLabel6))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jCboServicio, 0, 358, Short.MAX_VALUE)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabel12)
                                            .add(jTxtCalculo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabel7))
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(56, 56, 56)
                                                .add(jLabel11)
                                                .add(43, 43, 43)
                                                .add(jLabel14))
                                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jCboEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(18, 18, 18)
                                                .add(jtxtViajes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))))
                        .add(52, 52, 52))
                    .add(jLabel13)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jLabel4)
                    .add(jLabel12))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTxtNumeroOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtNombreOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(26, 26, 26)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel11)
                            .add(jLabel7)
                            .add(jLabel9)
                            .add(jLabel1)
                            .add(jLabel8)
                            .add(jLabel14))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTxtValorIncidencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTxtCalculo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jCboEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTxtFechaInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTxtDuracionGuardia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTxtHoraInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jcmbxTerminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jtxtViajes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel10)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTxtFechaFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTxtHoraFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(jTxtObservacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblBarraEstado.setText("<html><font color=ff0000>IZQ/DER</font> Ingrese Criterio de Busqueda | <font color=ff0000>ENTER</font> Buscar <font color=ff0000>IZQ/DER</font> Ingrese Criterio de Busqueda |</html>");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtObservacionActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jTxtObservacionActionPerformed

    private void jTxtObservacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtObservacionKeyPressed
// TODO add your handling code here:
            switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;  // 
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_F2: verRegistros(); break;
            case KeyEvent.VK_F3: verViajesRealizados(); break;

            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F7:  registrarIncidencia(); break; //if(transaccion==TXMOD)
     
            case KeyEvent.VK_RIGHT:  
                if(transaccion==TXMOD){
                    jTxtFechaFinal.selectAll();
                    jTxtFechaFinal.requestFocusInWindow();
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
                jTxtHoraFinal.selectAll();
                jTxtHoraFinal.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jTxtObservacionKeyPressed

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
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
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
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F7: if(transaccion==TXMOD) registrarIncidencia(); break;
            case 82: if(transaccion==TXMOD) registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                calculoDuracionGuardia();
                if(transaccion==TXMOD){
                    jTxtObservacion.selectAll();
                    jTxtObservacion.requestFocusInWindow();
                    return;
                }
                jCboEstado.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                calculoDuracionGuardia();
                jTxtFechaFinal.selectAll();
                jTxtFechaFinal.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jTxtHoraFinalKeyPressed

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
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_F2: verRegistros(); break;
            case KeyEvent.VK_F3: verViajesRealizados(); break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            //case KeyEvent.VK_F7: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                /*jTxtFechaFinal.selectAll();
                jTxtFechaFinal.requestFocusInWindow();
                break;*/
                calculoDuracionGuardia();
                if(transaccion==TXMOD){
                    jTxtObservacion.selectAll();
                    jTxtObservacion.requestFocusInWindow();
                    return;
                }
                if(jTxtFechaFinal.isEnabled())
                    jTxtFechaFinal.requestFocusInWindow();
                else
                    jCboEstado.requestFocusInWindow();
                break;
            
            case KeyEvent.VK_LEFT:
                jTxtFechaInicial.selectAll();
                jTxtFechaInicial.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jTxtHoraInicialKeyPressed

    private void jTblDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblDetalleKeyReleased
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_UP: case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_DOWN: case KeyEvent.VK_PAGE_DOWN: encabezado(); break;
        }
    }//GEN-LAST:event_jTblDetalleKeyReleased

    private void jTblDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblDetalleKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            //case KeyEvent.VK_F6: modificarIncidencia(); break;
            case KeyEvent.VK_F9: autorizarIncidencia(); break;
            case KeyEvent.VK_F2: verRegistros(); break;
            case KeyEvent.VK_F3: verViajesRealizados(); break;
            case KeyEvent.VK_F8: vistaPreviaImpresionRecibo(true); break;
            case KeyEvent.VK_ESCAPE: limpieza(); break;
        }
    }//GEN-LAST:event_jTblDetalleKeyPressed

    private void jCboEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboEstadoKeyPressed
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
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3));
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_F2: verRegistros(); break;
            case KeyEvent.VK_F3: verViajesRealizados(); break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F7: registrarIncidencia(); break;
            case 82: registrarIncidencia(); break;
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
                /*jTxtHoraFinal.selectAll();
                jTxtHoraFinal.requestFocusInWindow();*/
                jTxtHoraInicial.selectAll();
                jTxtHoraInicial.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jCboEstadoKeyPressed

    private void jCboServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboServicioKeyPressed
      
        switch(evt.getKeyCode()){
            case KeyEvent.VK_U:
                 trace("KeyEvent.VK_U     ");
                if(evt.isControlDown()){
                    JDlgCalendario uCalendario = new JDlgCalendario();
                    uCalendario.setVisible(true);
                }
                break;
            case KeyEvent.VK_ESCAPE: if(transaccion==TXREG){ this.transaccion = TXBUSQ;  limpieza(); } 
                if(transaccion==TXMOD){
                    this.transaccion = TXBUSQ;
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_1:   trace("KeyEvent.VK_1");
                if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: trace("KeyEvent.VK_2");   if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case KeyEvent.VK_ENTER:   muestraValor(true);  ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            //case KeyEvent.VK_F7: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT: 
            muestraValor(true);
            break;
            case KeyEvent.VK_LEFT: muestraValor(false); break;
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
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
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
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            case KeyEvent.VK_F7: if(transaccion==TXMOD) registrarIncidencia(); break;
            case 82: if(transaccion==TXMOD) registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                calculoDuracionGuardia();
                jTxtHoraFinal.selectAll();
                jTxtHoraFinal.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                calculoDuracionGuardia();
                if(transaccion==TXMOD){
                    jTxtObservacion.selectAll();
                    jTxtObservacion.requestFocusInWindow();
                    return;
                }
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
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
                    jTblDetalle.requestFocusInWindow();
                } 
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_F2: verRegistros(); break;
            case KeyEvent.VK_F3: verViajesRealizados(); break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            //case KeyEvent.VK_F10: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                jTxtHoraInicial.selectAll();
                jTxtHoraInicial.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jcmbxTerminal.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jTxtFechaInicialKeyPressed

    private void jTxtValorIncidenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtValorIncidenciaKeyPressed
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
                    
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
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
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            //case KeyEvent.VK_F10: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                if(transaccion == TXMOD){
                    jTxtFechaFinal.selectAll();
                    jTxtFechaFinal.requestFocusInWindow();
                    return;
                }
                jTxtFechaInicial.selectAll();
                jTxtFechaInicial.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                if(transaccion == TXMOD){
                    jTxtFechaFinal.requestFocusInWindow();
                    return;
                }
                jCboServicio.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jTxtValorIncidenciaKeyPressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
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
    
    private void jTxtNumeroOperadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNumeroOperadorKeyPressed
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
                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3));
                    jTxtFechaFinal.setEnabled(true);
                    jTxtHoraFinal.setEnabled(true);
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
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            //case KeyEvent.VK_F7: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                jCboServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jCboEstado.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jTxtNumeroOperadorKeyPressed

    private void jcmbxTerminalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbxTerminalKeyPressed
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

                    jTxtFechaFinal.setText(xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString());
                    jLblBarraEstado.setText(mensajes.getMensajeComun(3));
                    jTblDetalle.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_F1: if(transaccion==TXBUSQ){ transaccion=TXREG; limpiezaRegistrar(); } break;
            case KeyEvent.VK_F2: verRegistros(); break;
            case KeyEvent.VK_F3: verViajesRealizados(); break;
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown())
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4: CerrarVentana(); break;
            case KeyEvent.VK_ENTER: ejecutaConsulta(); break;
            case KeyEvent.VK_F5: seleccionarOperador(); break;
            //case KeyEvent.VK_F10: registrarIncidencia(); break;
            case KeyEvent.VK_RIGHT:
                jTxtFechaInicial.selectAll();
                jTxtFechaInicial.requestFocusInWindow();
                break;
            case KeyEvent.VK_LEFT:
                jCboServicio.requestFocusInWindow();
                break;
        }
    }//GEN-LAST:event_jcmbxTerminalKeyPressed

    private void jTxtFechaInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFechaInicialFocusGained
        jLblBarraEstado.setText(mensajes.getMensajeComun(5)); 
    }//GEN-LAST:event_jTxtFechaInicialFocusGained

    private void jTxtFechaInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFechaInicialFocusLost
        jLblBarraEstado.setText(mensajes.getMensajeComun(1)); 
    }//GEN-LAST:event_jTxtFechaInicialFocusLost

    private void jTxtHoraInicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtHoraInicialFocusGained
        jLblBarraEstado.setText(mensajes.getMensajeComun(5)); 
    }//GEN-LAST:event_jTxtHoraInicialFocusGained

    private void jTxtHoraInicialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtHoraInicialFocusLost
        jLblBarraEstado.setText(mensajes.getMensajeComun(1)); 
    }//GEN-LAST:event_jTxtHoraInicialFocusLost

    private void jcmbxTerminalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbxTerminalFocusGained
         jLblBarraEstado.setText(mensajes.getMensajeComun(5)); 
    }//GEN-LAST:event_jcmbxTerminalFocusGained

    private void jcmbxTerminalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbxTerminalFocusLost
        jLblBarraEstado.setText(mensajes.getMensajeComun(1)); 
    }//GEN-LAST:event_jcmbxTerminalFocusLost

    private void jCboEstadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboEstadoFocusGained
        jLblBarraEstado.setText(mensajes.getMensajeComun(5)); 
    }//GEN-LAST:event_jCboEstadoFocusGained

    private void jCboEstadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboEstadoFocusLost
        jLblBarraEstado.setText(mensajes.getMensajeComun(1)); 
    }//GEN-LAST:event_jCboEstadoFocusLost

    private void jTxtHoraInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtHoraInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtHoraInicialActionPerformed

    private void jTxtObservacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtObservacionFocusGained
        jLblBarraEstado.setText(mensajes.getMensajeComun(5));
    }//GEN-LAST:event_jTxtObservacionFocusGained
    
    private void CerrarVentana(){
        if(transaccion!=TXBUSQ) return;
        DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Desea cerrar la aplicacion?");
        boolean r = DialogoSiNo.getResultado();
        if(!r) return;
        this.dispose();
    }

    private void seleccionarOperador() {
         tmActual = null;
        if(transaccion!=TXREG) return;
        JDlgOperador jDlgOperador = new JDlgOperador(sesionVenta);
        jDlgOperador.setVisible(true);
        if(!jDlgOperador.getEvento()) return;
        jTxtNumeroOperador.setText(sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getClaveOperador());
        jTxtNombreOperador.setText(sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getOperadorNombreCompleto());
        jTxtObservacion.setEnabled(true);
        jTxtNumeroOperador.requestFocusInWindow();
    }

    private void muestraValor(boolean der) {
        if(der){
            //jTxtFechaInicial.selectAll();
            //jTxtFechaInicial.requestFocusInWindow();
            jcmbxTerminal.requestFocusInWindow();
        }
        else{
            jTxtNumeroOperador.selectAll();
            jTxtNumeroOperador.requestFocusInWindow();
        }
        if(jCboServicio.getSelectedIndex()==0) return;
        jTxtValorIncidencia.setText(sesionVenta.getDatosGuardia(String.valueOf(sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId())));
    }

    private void registrarIncidencia() {
        //if(!this.USUARIO_NUMERO.equals("8485"))
        if(jCboEstado.getSelectedItem().toString().equals("AUTORIZADA"))
        {
            if(!sesionVenta.ValidaFuncionUsuario("5610"))
            {
                if(!sesionVenta.isGuardiaValida(jTxtNumeroOperador.getText(),jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText(), jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText(),sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId()) && jCboEstado.getSelectedItem().toString().equals("AUTORIZADA"))
                {
                    DialogoAceptar = new JDlgAceptar("¡Imposible registrar guardia!", "<html>El operador no cumplio con las checadas minimas de <br> Biometrico Necesarias para el pago de la guardia.</html>", Color.RED);
                    return;
                }
            }
        }
        if(transaccion==TXBUSQ) return;
        if(!validaDatosIngresados()) return;
        if(!sesionVenta.actAdicId("GUA")){
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar guardia!", "No existe un registro de guardia como actividad adicional.", Color.RED);
            return;
        }
        if(transaccion==TXMOD){
            DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Realmente desea modificar la guardia?");
            if(!DialogoSiNo.getResultado()) return;
            modificarIncidenciaX();
            return;
        }

         trace("registrarIncidencia --->  Checando si el operador tiene Guardias");
        if(sesionVenta.tieneGuardiasCruzadas(jTxtNumeroOperador.getText(),jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText(),jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()))
        {
           DialogoAceptar = new JDlgAceptar("¡Imposible registrar guardia!", "Este operador tiene otra guardia en estas fechas/horas.", Color.RED);
            return;
        }
       //Vector vguardias=operadorTieneGuardias();
           // DialogoAceptar = new JDlgAceptar("¡Imposible registrar guardia!", "Este operador tiene otra guardia en estas fechas/horas.", Color.RED);
            //return;  // Change Ahora el se pueden capturar mas de una guardia en el dia para el operador
       
        //if(vguardias!= null && vguardias.size()>0 &&  !AseguaraAgregarGuardias(vguardias))
          //  return;
        soloAutorizar = false;
        //if(jCboEstado.getSelectedItem().equals("AUTORIZADA") && (jTxtFechaFinal.getText().equals("") || jTxtHoraFinal.getText().equals(""))){
        if((jTxtFechaFinal.getText().equals("") || jTxtHoraFinal.getText().equals(""))){
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar y autorizar la guardia!", "Fecha hora final nula.", Color.RED);
            return;
        }else{
            if(jCboEstado.getSelectedItem().equals("AUTORIZADA")){
                if(sesionVenta.getCtdGuardia()==0){
                    DialogoSiNo = new JDlgSiNo("¡El operador no ha completado guardia!", "<html>¿Desea autorizar la guardia<br>sin pre-ingreso de pago en recaudacion?</html>");
                    if(!DialogoSiNo.getResultado()) return;
                    soloAutorizar = true;
                }
            }
        }
        String obsParte1, obsParte2;
        obsParte1 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(0,30) : jTxtObservacion.getText());
        obsParte2 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(30,jTxtObservacion.getText().length()) : "");
        //Timestamp tmActual = new Timestamp(new Date().getTime());
        tmActual = sesionVenta.getFechaServidor();
        TmsOperadorIncidenciasTbl tmsOperadorIncidenciasTbl = new TmsOperadorIncidenciasTbl();
        tmsOperadorIncidenciasTbl.setOperadoresId(sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getOperadorId());
        tmsOperadorIncidenciasTbl.setIncidenciaId(sesionVenta.actAdicionalId);
        System.out.println("(1)Guardia Fecha Inicial: "+tmFecha);
        System.out.println("(1)Guardia Fecha Final: "+tmFecha2);
        tmsOperadorIncidenciasTbl.setFechaInicial(tmFecha);
        tmsOperadorIncidenciasTbl.setFechaFinal(tmFecha2);
        tmsOperadorIncidenciasTbl.setAdicional1(obsParte1);
        tmsOperadorIncidenciasTbl.setAdicional2(obsParte2);
        tmsOperadorIncidenciasTbl.setAdicional3(jcmbxTerminal.getSelectedItem().toString());
        tmsOperadorIncidenciasTbl.setAdicional4(jtxtViajes.getText().equals("")?"0":jtxtViajes.getText());
        tmsOperadorIncidenciasTbl.setIncidenciaValor(jTxtValorIncidencia.getText());
        tmsOperadorIncidenciasTbl.setIncidenciaAutorizada(jCboEstado.getSelectedItem().equals("AUTORIZADA") ? "S" : "N");
        tmsOperadorIncidenciasTbl.setServicioId(sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId());
        tmsOperadorIncidenciasTbl.setCreadoPor(this.USUARIO_ID);
        tmsOperadorIncidenciasTbl.setFechaCreacion(tmActual);
        tmsOperadorIncidenciasTbl.setUltimaActualizacionPor(this.USUARIO_ID);
        tmsOperadorIncidenciasTbl.setUltimaFechaActualizacion(tmActual);
        
    //********  Registra los datos los datos si la incidencia es autorizada y no se oprimio esc en esa pantalla
      boolean fdooper = true;
      boolean incid_aplicarecauda = sesionVenta.isRecauda("GUA");
      System.out.println("isRecauda: "+sesionVenta.isRecauda("GUA"));
      /*
      if(tmsOperadorIncidenciasTbl.getIncidenciaAutorizada().equals("S") && incid_aplicarecauda) 
      { 
           System.out.println("Si es autorizada y requiere ser recaudada");
           JDlgDatosAdicionales jDlgDatosAdicionales = null;
           sesionVenta.setOperIncidencia(jTxtNumeroOperador.getText().trim(),"GUA");
           
           boolean resultado = sesionVenta.busquedaEspecial(); 
           boolean fdatosAdiciEsp = sesionVenta.obtenerDatosAdicionalesEspeciales("GUA");
              System.out.println(" Resultado  Busqueda obtenerDatosAdicionalesEspeciales  "+fdatosAdiciEsp);    
             if (fdatosAdiciEsp){
                        jDlgDatosAdicionales = new JDlgDatosAdicionales (sesionVenta);
                        jDlgDatosAdicionales.setVisible(true);
                         fdooper= jDlgDatosAdicionales.FDoOperacion;
                      }             
        }
       *
       */
     //********

      /**** Imprime el Ticket ********/
      if(jCboEstado.getSelectedItem().toString().equals("AUTORIZADA"))
        vistaPreviaImpresionRecibo(false);
      else
       accept = true;
      
      if(!accept)
        {
            System.out.println("NO continua con el registro de la guardia...");
            return;
        }
      /**** Imprime el Ticket Fin ********/

      
    System.out.println("SI continua con el registro de la guardia...");
     boolean registraoper = false;
     //****** Si la incidencia no es Autorizada
        if(!tmsOperadorIncidenciasTbl.getIncidenciaAutorizada().equals("S")  )
        {   
           registraoper = sesionVenta.registrarIncidencia(tmsOperadorIncidenciasTbl,
                sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getOperadorId(),
                sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getClaveOperador()) ;
          if (!registraoper)
                DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "<html>No se registro la guardia.<br>Contacte al administrador del sistema.</html>", Color.RED);
          else 
               DialogoAceptar = new JDlgAceptar("¡Guardia registrada!", "Guardia registrada correctamente a operador.", Color.BLUE);
         limpieza();  
         return; 
             
      }  // Si la incidencia es autorizada
       else if(tmsOperadorIncidenciasTbl.getIncidenciaAutorizada().equals("S") && fdooper) {
                registraoper = sesionVenta.registrarIncidencia(tmsOperadorIncidenciasTbl,
                      sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getOperadorId(),
                      sesionVenta.getTmsEstadoOperadoresV().get(sesionVenta.getFilaOperadorSeleccionado()).getClaveOperador());
                     
                      boolean resultado = sesionVenta.busquedaEspecial();
                      boolean fdatosAdiciEsp = sesionVenta.obtenerDatosAdicionalesEspeciales("GUA");
                     
                      System.out.println("this.USUARIO_ID  "+this.USUARIO_ID);        
                      if (registraoper) {
                            if( soloAutorizar){
                                  System.out.println("sesionVenta.getEspecial().getOperIncidenciaId() "+sesionVenta.getOperIncId());
                                    
                                  //if(!sesionVenta.autorizaIncidencia(sesionVenta.getEspecial().getOperIncidenciaId(), this.USUARIO_ID))
                                  if(!sesionVenta.autorizaIncidencia(sesionVenta.getOperIncId(), this.USUARIO_ID,(tmActual==null?sesionVenta.getFechaServidor():tmActual)))
                                    DialogoAceptar = new JDlgAceptar("¡No se autorizo la guardia!", "<html>No existe conexion a la base de datos.<br>Contacte al administrador del sistema.</html>", Color.RED);
                            }
                            else{
                                if(tmsOperadorIncidenciasTbl.getIncidenciaAutorizada().equals("S")){
                                   if(resultado){
                                              sesionVenta.setMonto(Double.valueOf(jTxtValorIncidencia.getText()), Double.valueOf(jTxtDuracionGuardia.getText()));
                                             // if(incid_aplicarecauda){
                                                   System.out.println("Si es recaudada y llamando a ingresarPreIngresoEspecial");
                                              if(!sesionVenta.ingresarPreIngresoEspecial(this.USUARIO_ID,(tmActual==null?sesionVenta.getFechaServidor():tmActual) ))
                                                    DialogoAceptar = new JDlgAceptar("¡No se autorizo la guardia!", "<html>No existe conexion a la base de datos.<br>Contacte al administrador del sistema.</html>", Color.RED);
                                                else{
                                                    DialogoAceptar = new JDlgAceptar("¡Autorizacion realizada correctamente!", "<html>Pago registrado en recaudacion. <br>El pago se reflejara en la nomina del operador</html>", Color.BLUE);
                                                }
                                             // }  // if(incid_aplicarecauda)
                                       } // if resultado
                                    }
                                    else{
                                        DialogoAceptar = new JDlgAceptar("¡El pago no pudo ser preingresado en recaudacion.","Contacte al administrador del sistema.", Color.RED);
                                    }
                               }
                              DialogoAceptar = new JDlgAceptar("¡Guardia registrada!", "Guardia registrada correctamente a operador.", Color.BLUE);
                       }   // if (registraoper)  
                      else
                            DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "<html>No se registro la guardia.<br>Contacte al administrador del sistema.</html>", Color.RED);
             }
        limpieza();
    }

      public void vistaPreviaImpresionRecibo(boolean reimpresion)
    {
          if(reimpresion)
          {
              if(jTblDetalle.getValueAt(jTblDetalle.getSelectedRow(), 6).toString().equals("NO AUTORIZADA"))
              {
                DialogoAceptar = new JDlgAceptar("¡No reimprimio el Ticket!", "<html>No es posible reimprimir el Ticket. <br> !La Guardia debe de estar Autorizada!</html>", Color.RED);
                return;
              }
          }

      /**** Imprime el Ticket ********/
          String fechaRec = "";
          if(reimpresion)
              fechaRec  = jTblDetalle.getValueAt(jTblDetalle.getSelectedRow(), 7).toString();
                vista_pago_impresion v = new vista_pago_impresion(reimpresion,fechaRec);
                v.setVisible(true);
      /**** Imprime el Ticket Fin ********/

    }

    private boolean AseguaraAgregarGuardias(Vector vguardias)
    {
        boolean fagregrar=false;
        if( vguardias!= null && vguardias.size() >0 ){
           // DialogoAceptar = new JDlgAceptar("¡Imposible registrar guardia!", "Este operador tiene otra guardia en estas fechas/horas.", Color.RED);
            //return;  // Change Ahora el se pueden capturar mas de una guardia en el dia para el operador
          trace("EL operador tienen las siguienets guardias"+ vguardias);  
          String strmsj="¡El operador tiene otra(s) guardia(s) en el sig horario! <br><br>";
          for (int i = 0; i < vguardias.size(); i++) {
              strmsj += ((Vector)vguardias.elementAt(i)).elementAt(9).toString()+ "   -   " +
                       ((Vector)vguardias.elementAt(i)).elementAt(10).toString()+"<br>";
          }  
           strmsj+="  ¿Desea agregar otra guardia?";
            DialogoSiNo = new JDlgSiNo("¡El operador con guardias!", "<html>"+strmsj+"</html>");
            trace("Resultado "+DialogoSiNo.getResultado());
            if(DialogoSiNo.getResultado()) fagregrar=true;
        }  // if
        return fagregrar;
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
        
        if(jTxtValorIncidencia.getText().trim().equals("") ){  // BRA|| jTxtValorIncidencia.getText().equals("0")){
            DialogoAceptar = new JDlgAceptar("¡Valor incorrecto!", "Falta valor de guardia.", Color.RED);
            jCboServicio.requestFocusInWindow();
            return false;
        }
        if(jTxtFechaInicial.getText().equals("") || jTxtFechaInicial.getText().length()<10){
            jTxtFechaInicial.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Fecha inicial es obligatoria!", "Ingrese fecha.", Color.RED);
            jTxtFechaInicial.requestFocusInWindow();
            return false;
        }
        
        if(jTxtHoraInicial.getText().equals("") || jTxtHoraInicial.getText().length()<5){
            jTxtHoraInicial.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Hora inicial es obligatoria!", "Ingrese hora.", Color.RED);
            jTxtHoraInicial.requestFocusInWindow();
            return false;
        }
        
        if(jTxtHoraFinal.getText().length()>1 && jTxtHoraFinal.getText().length()<5){
            jTxtHoraFinal.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Hora final invalida!", "Ingrese hora completa o deje el campo vacio.", Color.RED);
            jTxtHoraFinal.requestFocusInWindow();
            return false;
        }
        
        if(jTxtFechaFinal.getText().length()>1 && jTxtFechaFinal.getText().length()<10){
            jTxtFechaFinal.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "Ingrese fecha completa o deje el campo vacio.", Color.RED);
            jTxtFechaFinal.requestFocusInWindow();
            return false;
        }
        try {
            //tmFecha = new Timestamp(formatoFechaHora.parse(jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()).getTime());
              String f[] = jTxtFechaInicial.getText().split("/");
              tmFecha = Timestamp.valueOf(""+f[2]+"-"+f[1]+"-"+f[0]+" "+jTxtHoraInicial.getText()+":00");
            System.out.println("parse tmFecha: "+jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText());
        } catch (Exception ex) {
            DialogoAceptar = new JDlgAceptar("¡Fecha inicial invalida!", "Ingrese fecha correcta.", Color.RED);
            return false;
        }
        if(!jTxtFechaFinal.getText().equals("") && !jTxtHoraFinal.getText().equals("")){
            System.out.println("parse tmFecha2: "+jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText());

            try {
                  //tmFecha2 = new Timestamp(formatoFechaHora.parse(jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()).getTime());
                  String f[] = jTxtFechaFinal.getText().split("/");
                  tmFecha2 = Timestamp.valueOf(""+f[2]+"-"+f[1]+"-"+f[0]+" "+jTxtHoraFinal.getText()+":00");
            } catch (Exception ex) {
                DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "Ingrese fecha correcta.", Color.RED);
                return false;
            }
            if(tmFecha.getTime()>tmFecha2.getTime()){
                DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "La fecha final no debe ser menor a la fecha inicial.", Color.RED);
                return false;
            }
        }
        else
            tmFecha2 = null;

        System.out.println("(0)Guardia Fecha Inicial: "+tmFecha);
        System.out.println("(0)Guardia Fecha Final: "+tmFecha2);



        if(jcmbxTerminal.getSelectedItem().equals("TODAS")){
            DialogoAceptar = new JDlgAceptar("¡Terminal invalida!", "Seleccione una terminal de la lista.", Color.RED);
            jcmbxTerminal.requestFocusInWindow();
            return false;
        }

        if(jCboServicio.getSelectedItem().equals("TODOS")){
            DialogoAceptar = new JDlgAceptar("¡Servicio invalido!", "Seleccione un servicio de la lista.", Color.RED);
            jCboServicio.requestFocusInWindow();
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
            DialogoAceptar = new JDlgAceptar("¡Hora inicial invalida!", "Ingrese hora completa o deje el campo vacio.", Color.RED);
            jTxtHoraInicial.requestFocusInWindow();
            return false;
        }
        
        if(!jTxtFechaInicial.getText().equals("")){
            try {
                tmFecha = new Timestamp(formatoFechaHora.parse(jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()).getTime());
            } catch (ParseException ex) {
                DialogoAceptar = new JDlgAceptar("¡Fecha inicial invalida!", "Ingrese fecha correcta.", Color.RED);
                return false;
            }
        }
        
        if(jTxtFechaFinal.getText().length()>1 && jTxtFechaFinal.getText().length()<10){
            jTxtFechaFinal.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "Ingrese fecha completa o deje el campo vacio", Color.RED);
            jTxtFechaFinal.requestFocusInWindow();
            return false;
        }
        
        if(jTxtHoraFinal.getText().length()>1 && jTxtHoraFinal.getText().length()<5){
            jTxtHoraFinal.selectAll();
            DialogoAceptar = new JDlgAceptar("¡Hora final invalida!", "Ingrese hora completa o deje el campo vacio.", Color.RED);
            jTxtHoraFinal.requestFocusInWindow();
            return false;
        }
        
        if(!jTxtFechaFinal.getText().equals("")){
            try {
                //tmFecha2 = new Timestamp(formatoFechaHora.parse(jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()).getTime());
                  String f[] = jTxtFechaFinal.getText().split("/");
                  tmFecha2 = Timestamp.valueOf(""+f[2]+"-"+f[1]+"-"+f[0]+" "+jTxtHoraFinal.getText()+":00");

            } catch (Exception ex) {
                DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "Ingrese fecha correcta.", Color.RED);
                return false;
            }
            if(tmFecha.getTime()>tmFecha2.getTime()){
                DialogoAceptar = new JDlgAceptar("¡Fecha final invalida!", "La fecha final no debe ser menor a la fecha inicial.", Color.RED);
                return false;
            }
        }
        
        return true;
    }
    
    private void limpieza() {
        tmActual = null;
        transaccion = TXBUSQ;
        jTxtNumeroOperador.setEnabled(true);
        jCboServicio.setEnabled(true);
        jTxtFechaInicial.setEnabled(true);
        jTxtHoraInicial.setEnabled(true);
        jTxtObservacion.setEnabled(false);
        jTxtHoraFinal.setEnabled(true);
        jCboEstado.setEnabled(true);
        jcmbxTerminal.setEnabled(true);
        jcmbxTerminal.setSelectedIndex(0);
        jTxtNombreOperador.setText("");
        jTxtNumeroOperador.setText("%");
        jCboServicio.setSelectedIndex(0);
        jCboEstado.setSelectedIndex(0);
        jTxtFechaInicial.setText("");
        jTxtFechaFinal.setText("");
        jTxtObservacion.setText("");
        jTxtHoraFinal.setText("");
        jTxtHoraInicial.setText("");
        jTxtValorIncidencia.setText("");
        jTxtDuracionGuardia.setText("");
        jTxtCalculo.setText("");
                            jTxtFechaFinal.setEnabled(true);
                    jTxtHoraFinal.setEnabled(true);

        
        xTabla.setDataVector(null, encabezado);
        AnchoColumnas();
        jTxtNumeroOperador.selectAll();
        jLblBarraEstado.setText(mensajes.getMensajeComun(1));
        jTxtNumeroOperador.requestFocusInWindow();
    }
    
    private void limpiezaRegistrar() {
        tmActual = null;
        jTxtNumeroOperador.setEnabled(true);
        jCboServicio.setEnabled(true);
        jTxtFechaInicial.setEnabled(true);
        jTxtHoraInicial.setEnabled(true);
        jTxtFechaFinal.setEnabled(true);
        jTxtHoraFinal.setEnabled(true);
        jTxtObservacion.setEnabled(true);
        jCboEstado.setEnabled(true);
        jcmbxTerminal.setEnabled(true);
        jcmbxTerminal.setSelectedIndex(0);
        jTxtNumeroOperador.setText("");
        jTxtValorIncidencia.setEditable(false);
        jTxtFechaInicial.setText("");
        jTxtFechaFinal.setText("");
        jTxtObservacion.setText("");
        jTxtHoraInicial.setText("");
        jTxtHoraFinal.setText("");
        jTxtDuracionGuardia.setText("");
        jTxtValorIncidencia.setText("");
        jTxtCalculo.setText("");
        jTxtNumeroOperador.selectAll();
                            jTxtFechaFinal.setEnabled(false);
                    jTxtHoraFinal.setEnabled(false);

        jLblBarraEstado.setText(mensajes.getMensajeComun(2)); 
        jTxtNumeroOperador.requestFocusInWindow();
    }
    
    private void limpiezaMod() {
         tmActual = null;
        jTxtNumeroOperador.setEnabled(false);
        jCboServicio.setEnabled(false);
        jTxtFechaInicial.setEnabled(false);
        jTxtHoraInicial.setEnabled(false);
        jCboEstado.setEnabled(false);
        jcmbxTerminal.setEnabled(false);

        jLblBarraEstado.setText(mensajes.getMensajeComun(4)); 
    }

    private void ejecutaConsulta() {
         tmActual = null;
        if(transaccion!=TXBUSQ) return;
        if(jCboServicio.getSelectedIndex()!=0)
            jTxtValorIncidencia.setText(sesionVenta.getDatosGuardia(String.valueOf(sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId())));
        Object[][] resultado = sesionVenta.busqueda(jTxtNumeroOperador.getText(), jCboServicio.getSelectedItem().toString(),
                sesionVenta.getGuardiaClave(), jTxtFechaInicial.getText(), jTxtFechaFinal.getText(), jCboEstado.getSelectedItem().toString(),jcmbxTerminal.getSelectedItem().toString());
        if(resultado == null){
            DialogoAceptar = new JDlgAceptar("¡No existen guardias!", "El criterio de busqueda no genero resultados.", Color.RED);
            limpieza();
            return;
        }
        jTxtNumeroOperador.setEnabled(false);
        jCboServicio.setEnabled(false);
        jTxtFechaInicial.setEnabled(false);
        jTxtHoraInicial.setEnabled(false);
        jTxtFechaFinal.setEnabled(false);
        jTxtHoraFinal.setEnabled(false);
        jTxtObservacion.setEnabled(false);
        jcmbxTerminal.setEnabled(false);
        jCboEstado.setEnabled(false);
        xTabla.setDataVector(resultado, encabezado);
        AnchoColumnas();
        jTblDetalle.setRowSelectionInterval(0,0);
        encabezado();
        jLblBarraEstado.setText(mensajes.getMensajeComun(3)); 
        jTblDetalle.requestFocusInWindow();
    }
    
    private void AnchoColumnas() {
        int anchoContenedor = jScpDetalle.getWidth();
        TableColumn column;
        for (int i = 0; i < jTblDetalle.getColumnCount(); i++) {
            column = jTblDetalle.getColumnModel().getColumn(i);
            switch (i) {
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.25)); break;
                case 6: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 7: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); break;
            }
        }
    }

    private void encabezado() {
        sesionVenta.setFilaIncidenciaSeleccionada(jTblDetalle.getSelectedRow());
        jTxtNumeroOperador.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getClaveOperador());
        jTxtNombreOperador.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperadorNombreCompleto());
        jCboServicio.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getServicioNombre());
        jcmbxTerminal.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getTerminal());
        jtxtViajes.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getViajes());
        jTxtValorIncidencia.setText(sesionVenta.getDatosGuardia(String.valueOf(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getServicioId())));
        cadenaFechaHora = xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),2).toString().split(" ");
            jTxtFechaInicial.setText(cadenaFechaHora[0]);
            if(cadenaFechaHora.length>1) jTxtHoraInicial.setText(cadenaFechaHora[1]);
            else jTxtHoraInicial.setText("");
        cadenaFechaHora = xTabla.getValueAt(sesionVenta.getFilaIncidenciaSeleccionada(),3).toString().split(" ");
            jTxtFechaFinal.setText(cadenaFechaHora[0]);
            if(cadenaFechaHora.length>1) jTxtHoraFinal.setText(cadenaFechaHora[1]);
            else jTxtHoraFinal.setText("");
        jCboEstado.setSelectedItem(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada());
        calculoDuracionGuardia();
        jTxtObservacion.setText(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getObservacion());
    }

    private void modificarIncidencia() {
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada().equals("AUTORIZADA")){
            DialogoAceptar = new JDlgAceptar("¡Modificacion cancelada!", "La guardia ya esta autorizada.", Color.RED);
            return;
        }
        transaccion = TXMOD;
        jTxtObservacion.setEnabled(true);
        jLblBarraEstado.setText(mensajes.getMensajeComun(4)); 
        jTxtFechaFinal.requestFocusInWindow();
    }

    private void autorizarIncidencia() {
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getIncidenciaAutorizada().equals("AUTORIZADA")){
            DialogoAceptar = new JDlgAceptar("¡Autorizacion cancelada!", "La guardia ya esta autorizada.", Color.RED);
            return;
        }
        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getFechaFinal()==null){
            DialogoAceptar = new JDlgAceptar("¡Autorizacion cancelada!", "La guardia aun no tiene fecha final.", Color.RED);
            return;
        }
        if(!sesionVenta.actAdicId("GUA")){
            DialogoAceptar = new JDlgAceptar("¡Imposible registrar guardia!", "No existe un registro de guardia como actividad adicional.", Color.RED);
            return;
        }
        //if(!this.USUARIO_NUMERO.equals("8485"))
            if(!sesionVenta.ValidaFuncionUsuario("5610"))
            {
                if(!sesionVenta.isGuardiaValida(jTxtNumeroOperador.getText(),jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText(), jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText(),sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId()) )
                {
                    DialogoAceptar = new JDlgAceptar("¡Imposible registrar guardia!", "<html>El operador no cumplio con las checadas minimas de <br> Biometrico Necesarias para el pago de la guardia.</html>", Color.RED);
                    return;
                }
            }


        soloAutorizar = false;
        calculoDuracionGuardia();
        if(sesionVenta.getCtdGuardia()==0){
            DialogoSiNo = new JDlgSiNo("¡El operador no ha completado guardia!", "<html>¿Desea autorizar la guardia<br>sin pre-ingreso de pago en recaudacion?</html>");
            if(!DialogoSiNo.getResultado()) return;
            soloAutorizar = true;
        }
        System.out.println("soloAutorizar: "+soloAutorizar);
        DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Realmente desea autorizar la guardia?");
        if(!DialogoSiNo.getResultado()) return;
        /*JDlgDatosAdicionales jDlgDatosAdicionales  =  null;
        if(sesionVenta.obtenerDatosAdicionales()){
            jDlgDatosAdicionales = new JDlgDatosAdicionales (sesionVenta);
            jDlgDatosAdicionales.setVisible(true);
        }*/
         //System.out.println("AL autorizar unicamenrte "+jDlgDatosAdicionales.FDoOperacion);
        //if( jDlgDatosAdicionales != null && jDlgDatosAdicionales.FDoOperacion)
        if( true)
        {
                if(soloAutorizar){
                    System.out.println("Entra a solo autoriza es TRUE");
                    if(!sesionVenta.autorizaIncidencia(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperIncidenciaId(), this.USUARIO_ID,(tmActual==null?sesionVenta.getFechaServidor():tmActual)))
                            DialogoAceptar = new JDlgAceptar("¡No se autorizo la guardia!", "<html>No existe conexion a la base de datos.<br>Contacte al administrador del sistema.</html>", Color.RED);
                }
                else{
                    System.out.println("entra a soloautoriza es FALSE");
                    sesionVenta.setMonto(Double.valueOf(jTxtValorIncidencia.getText()), Double.valueOf(jTxtDuracionGuardia.getText()));
                    System.out.println("(0)getAplicaRecaudacion("+sesionVenta.getFilaIncidenciaSeleccionada()+"): "+sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getAplicaRecaudacion());
                    if(!sesionVenta.ingresarPreIngreso(this.USUARIO_ID,(tmActual==null?sesionVenta.getFechaServidor():tmActual)))
                        DialogoAceptar = new JDlgAceptar("¡No se autorizo la guardia!", "<html>No existe conexion a la base de datos.<br>Contacte al administrador del sistema.</html>", Color.RED);
                    else{
                        System.out.println("(2)getAplicaRecaudacion("+sesionVenta.getFilaIncidenciaSeleccionada()+"): "+sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getAplicaRecaudacion());
                        if(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getAplicaRecaudacion().equals("S"))
                            DialogoAceptar = new JDlgAceptar("¡Autorizacion realizada correctamente!", "<html>Pago registrado en recaudacion. <br>El pago se reflejara en la nomina del operador</html>", Color.BLUE);
                        else DialogoAceptar = new JDlgAceptar("¡Autorizacion realizada correctamente!", "Continue...", Color.RED);
                    }
                }
        }  //   if( jDlgDatosAdicionales != null && jDlgDatosAdicionales.FDoOperacion)
        limpieza();
    }

    private void modificarIncidenciaX() {
        String obsParte1, obsParte2;
        obsParte1 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(0,30) : jTxtObservacion.getText());
        obsParte2 = (jTxtObservacion.getText().length()>30 ? jTxtObservacion.getText().substring(30,jTxtObservacion.getText().length()) : "");
        if(!sesionVenta.modificaIncidencia(sesionVenta.getVwIncOper().get(sesionVenta.getFilaIncidenciaSeleccionada()).getOperIncidenciaId(),
                jTxtValorIncidencia.getText(), tmFecha2, obsParte1, obsParte2, this.USUARIO_ID))
            DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "<html>No se modifico la guardia.<br>Contacte al administrador del sistema.</html>", Color.RED);
        else{
            DialogoAceptar = new JDlgAceptar("¡Guardia modificada!", "Guardia modificada correctamente.", Color.BLUE);
        }
        limpieza();
    }  

    private Vector operadorTieneGuardias() { 
         return sesionVenta.tieneGuardia(jTxtNumeroOperador.getText(),jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText(),jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText());
    }

    private void calculoDuracionGuardia() {
        if((jTxtFechaInicial.getText().equals("") || jTxtFechaInicial.getText().length()<10) && 
                (jTxtHoraInicial.getText().equals("") || jTxtHoraInicial.getText().length()<5)) return;
        //duracion de la guardia
        try{
         Timestamp fechaTmp = new Timestamp(formatoFechaHora.parse(jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()).getTime());
         //long fechaLong = fechaTmp.getTime()+ Long.valueOf(""+sesionVenta.getUnaGuardiaHoras());
         //long fechaLong = fechaTmp.getTime()+ Long.valueOf(""+sesionVenta.duracionGuardiaServicio(sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getServicioId()));
         if(jCboServicio.getSelectedItem().toString().equals("TODOS"))
         {
             DialogoAceptar = new JDlgAceptar("¡Servicio invalido!", "Debe seleccionar un servicio.", Color.RED);
             return;
         }
         long fechaLong = fechaTmp.getTime()+ (sesionVenta.getTmsServiciosTbl().get(jCboServicio.getSelectedIndex()-1).getDuracionGuardia() * 3600000);
         Timestamp fechaTmpFinal = new Timestamp(fechaLong);
         jTxtFechaFinal.setText(formatoFecha.format(fechaTmpFinal));
         jTxtHoraFinal.setText(formatoHora.format(fechaTmpFinal));

      } catch (ParseException ex) {
            return;
        }         
        //Asignamos la hora final
        if((jTxtFechaFinal.getText().equals("") || jTxtFechaFinal.getText().length()<10) && 
                (jTxtHoraFinal.getText().equals("") || jTxtHoraFinal.getText().length()<5)) return;
        
        try{
            //tmFecha = new Timestamp(formatoFechaHora.parse(jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()).getTime());
              String f[] = jTxtFechaInicial.getText().split("/");
              tmFecha = Timestamp.valueOf(""+f[2]+"-"+f[1]+"-"+f[0]+" "+jTxtHoraInicial.getText()+":00");

        } catch (Exception ex) {
            return;
        }
        try {
            //tmFecha2 = new Timestamp(formatoFechaHora.parse(jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()).getTime());
            String f[] = jTxtFechaFinal.getText().split("/");
            tmFecha2 = Timestamp.valueOf(""+f[2]+"-"+f[1]+"-"+f[0]+" "+jTxtHoraFinal.getText()+":00");

        } catch (Exception ex) {
            return;
        }
        //buscamos el numero de Viajes realizados
            jtxtViajes.setText(sesionVenta.getNumTarOper(jTxtNumeroOperador.getText(), jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText(), jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()));
        long calcGuardia = tmFecha2.getTime()-tmFecha.getTime();
        sesionVenta.setTiempoGuardia(calcGuardia);
        sesionVenta.setCtdGuardia();
        //jTxtDuracionGuardia.setText(String.valueOf(sesionVenta.getCtdGuardia()));
        jTxtDuracionGuardia.setText("1");
        double a, b;
        a = (jTxtValorIncidencia.getText()==null || jTxtValorIncidencia.getText().equals("") ? 0 : Double.valueOf(jTxtValorIncidencia.getText()));
        b = (jTxtDuracionGuardia.getText()==null || jTxtDuracionGuardia.getText().equals("") ? 0 : Double.valueOf(jTxtDuracionGuardia.getText()));
        jTxtCalculo.setText(String.valueOf(a*b));
    }
    
    
    
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
        
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }

    public void verRegistros()
    {
        if((jTxtFechaInicial.getText().equals("") || jTxtFechaInicial.getText().length()<10) &&
                (jTxtHoraInicial.getText().equals("") || jTxtHoraInicial.getText().length()<5)) return;
        JdlgChecadasBiometrico ver = new JdlgChecadasBiometrico(null, false, sesionVenta.getRegistrosOper(jTxtNumeroOperador.getText(), jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText(), jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()),jTxtNumeroOperador.getText()+"-"+jTxtNombreOperador.getText());
        ver.setLocationRelativeTo(this);
        ver.setVisible(true);
    }

    public void verViajesRealizados()
    {
        if((jTxtFechaInicial.getText().equals("") || jTxtFechaInicial.getText().length()<10) &&
                (jTxtHoraInicial.getText().equals("") || jTxtHoraInicial.getText().length()<5)) return;
        JdlgViajesRealizados ver = new JdlgViajesRealizados(null, false, sesionVenta.getTarjetasOper(jTxtNumeroOperador.getText(), jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText(), jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()),jTxtNumeroOperador.getText()+"-"+jTxtNombreOperador.getText(),"VIAJES REALIZADOS ENTRE EL  "+jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()+"  Y  "+jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText());
        ver.setLocationRelativeTo(this);
        ver.setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCboEstado;
    private javax.swing.JComboBox jCboServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScpDetalle;
    private javax.swing.JTable jTblDetalle;
    private javax.swing.JTextField jTxtCalculo;
    private javax.swing.JTextField jTxtDuracionGuardia;
    private javax.swing.JTextField jTxtFechaFinal;
    private javax.swing.JTextField jTxtFechaInicial;
    private javax.swing.JTextField jTxtHoraFinal;
    private javax.swing.JTextField jTxtHoraInicial;
    private javax.swing.JTextField jTxtNombreOperador;
    private javax.swing.JTextField jTxtNumeroOperador;
    private javax.swing.JTextField jTxtObservacion;
    private javax.swing.JTextField jTxtValorIncidencia;
    private javax.swing.JComboBox jcmbxTerminal;
    private javax.swing.JTextField jtxtViajes;
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
    private SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
    private Timestamp tmFecha, tmFecha2;
    private final long dia = 86400000;
    
    private final int TXBUSQ=0;
    private final int TXREG=1;
    private final int TXMOD=2;
    private int transaccion=TXBUSQ;
    private boolean reimpresion = false;
    private String formato = "TICKETS";
    private String puerto = "";
    private String nombreFormat = "";
    private String nombreImpresora = "";
    private boolean accept = false;

    private Object[] encabezado = {"Operador", "Guardia", "Fecha Inicial", "Fecha Final", "Terminal", "Servicio", "Estado","Fecha Recaudacion"};
    private DefaultTableModel xTabla = new DefaultTableModel(null,encabezado){
        public boolean isCellEditable(int row, int col){
            return false;
        }
    };
    Mensajes mensajes = new Mensajes();
    private boolean soloAutorizar;
    private String[] cadenaFechaHora;
    private KeyEvent eventoTeclado;
    
    private boolean ftrace=true;
    
    private Timestamp tmActual= null;

    public void trace(String msj)
    {
        if(ftrace)
           System.out.println(msj);
    }

 public void imprimir_recibo(boolean reimpresionr,String pfechaRecaudada,String phoraRecaudada){
     String fechaRecaudada=pfechaRecaudada;
     String horaRecaudada=phoraRecaudada;
//        imprimir_recibo_capacitacion imp = new imprimir_recibo_capacitacion(nref);
//        if(formatos.indexOf("TICKETS")>=0)
//            imp.ImprimeDatos();
//        else
//        {
//            new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no sera impreso"," El numero de referencia del pago es: "+nref,"Impresora no encontrada").setVisible(true);
//            return;
//        }
//        Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante",nref);
//        p.setVisible(true);
//        if(reimpresion)
//            imprimir_recibo(nref);
        if(formato.equals("TICKETS"))
        {
            //String puerto = puertos.get(index).toString();
               if(puerto.equals("LPT1") ||puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2")  || puerto.equals("ARCHIVO") )
               {
                 if(!imprimir_recibo_LPT("",puerto,reimpresionr,nombreFormat,fechaRecaudada,horaRecaudada))
                     new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!","","Error de impresión").setVisible(true);
               }
               if(puerto.equals("RED") || puerto.equals("USB"))
               {
                     if(!imprimir_recibo_LPT("",nombreImpresora,reimpresionr,nombreFormat,fechaRecaudada,horaRecaudada))
                         new jdlg_error("¡No se pudo imprimir el ticket por error de la impresora!","","Error de impresión").setVisible(true);
                 //imprimir_recibo_rec(datos,(datos[0]));
               }
        }
        else
        {
            new jdlg_advertencia("¡La impresora no esta instalada!. El ticket no será impreso",""," Impresora no encontrada").setVisible(true);
            return;
        }
        Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante","");
        p.setVisible(true);
        if(reimpresion)
            imprimir_recibo(reimpresionr,fechaRecaudada,horaRecaudada);
    }


    class vista_pago_impresion extends JDialog{

        private JTextArea jTextArea1 = new JTextArea();
        private JLabel jlbl_mensajeVPI = new JLabel();
        private JLabel jlbl_barraEstadoVPI = new JLabel();
        private ImageIcon imageHelp = new ImageIcon(vista_pago_impresion.class.getResource("pregunta.gif"));
        private JLabel jLabel2 = new JLabel();
        private int alto = 0;
        private boolean reimpresionv;
        private String fechaRecaudada = "";
        private String horaRecaudada = "";

        public vista_pago_impresion(boolean reimpresion, String fechaRecauda) {
            reimpresionv = reimpresion;
            if(reimpresionv)
            {
                this.setTitle("Vista de Reimpresion de Ticket");
                if(!fechaRecauda.equals(""))
                {
                    fechaRecaudada =fechaRecauda.substring(0,10);
                    horaRecaudada = fechaRecauda.substring(11);
                }
            }
            else
                this.setTitle("Vista de Impresion de Ticket");
            this.setDefaultLookAndFeelDecorated(true);
            this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.setAlwaysOnTop(true);
            this.setModal(true);

            try {
                jbInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.jTextArea1.requestFocus();
        }

        private void jbInit() throws Exception {
            this.setSize(new Dimension(400, 450+alto));
            this.setLayout(null);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
            this.setResizable(false);
            jlbl_barraEstadoVPI.setFont(new java.awt.Font("Tahoma", 1, 12));
            jlbl_barraEstadoVPI.setForeground(new java.awt.Color(153, 153, 153));
            jlbl_barraEstadoVPI.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            jlbl_barraEstadoVPI.setBounds(new Rectangle(0, 395+alto, 400, 25));
            jlbl_barraEstadoVPI.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
            jlbl_barraEstadoVPI.setHorizontalAlignment(JTextField.CENTER);
            jTextArea1.setBounds(new Rectangle(10, 10, 370, 300+alto));
            jTextArea1.setEditable(false);
            //jTextArea1.setFocusable(false);
            jTextArea1.setFont(new Font("Dialog", 0, 12));
            jTextArea1.setSize(new Dimension(375, 335+alto));
            jlbl_mensajeVPI.setText("¿Seguro que desea pagar la Actividad?");
            jlbl_mensajeVPI.setBounds(new Rectangle(105, 365+alto, 250, 15));
            jlbl_mensajeVPI.setFont(new Font("Arial", 1, 12));
            jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1_KeyPressed(evt);
            }
            });
             jTextArea1.setFocusTraversalKeysEnabled(false);
             jLabel2.setBounds(new Rectangle(60, 350+alto, 35, 45));
             jLabel2.setIcon(imageHelp);
             //jTextArea1.append(" REF.: "+jlbl_folio.getText()+ "\n\n");
             if(reimpresionv)
                jTextArea1.append("          REIMPRESION DE TICKET\n\n");
             jTextArea1.append(" Actividad            :   GUARDIA\n");
             jTextArea1.append(" Servicio             :   "+jCboServicio.getSelectedItem().toString() + "\n");
             jTextArea1.append(" Operador             :   "+jTxtNumeroOperador.getText()+"-"+jTxtNombreOperador.getText()+ "\n");
             jTextArea1.append(" Terminal             :   "+jcmbxTerminal.getSelectedItem()+ "\n");
             jTextArea1.append(" Fecha Inicio Guardia :   "+jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()+"\n");
             jTextArea1.append(" Fecha Fin Guardia    :   "+jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()+"\n");
             //jTextArea1.append(" Sueldo               :   "+jtxt_monto.getText()+ "\n");
             //jTextArea1.append(" Retencion         :   "+jtxt_retencion.getText()+ "\n");
             //float total = (Float.valueOf(jtxt_monto.getText()))- (Float.valueOf(jtxt_retencion.getText()));
             //long total2 = Float.valueOf(total).longValue();
             //jTextArea1.append(" Total                  :   "+total+ "\n");
             //jTextArea1.append("              ( "+new cantidad_a_letras().toLetras((long) total2) + " Pesos 00/M.N.  )\n");
             jTextArea1.append(" Recaudador      :   "+USUARIO_NUMERO+"-"+USUARIO_NOMBRE+ "\n");
             SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
             SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
             Timestamp fecha_servidor =(tmActual==null?sesionVenta.getFechaServidor():tmActual);
             if(reimpresionv)
             {
                 jTextArea1.append(" Fecha de Recaudacion :  "+fechaRecaudada + "\n");
                 jTextArea1.append(" Hora de Recaudacion   :  "+ horaRecaudada+ "\n");
                 jTextArea1.append(" Fecha de Reimpresion :  "+formatf.format(fecha_servidor.getTime())  + "\n");
                 jTextArea1.append(" Hora de Reimpresion   :  "+ formath.format(fecha_servidor.getTime())+ "\n\n");
              }
             else
             {
                jTextArea1.append(" Fecha de Recaudacion:  "+formatf.format(fecha_servidor.getTime())  + "\n");
                jTextArea1.append(" Hora de Recaudacion  : "+ formath.format(fecha_servidor.getTime())+ "\n\n");
              }
             jTextArea1.append(" Firma: ________________________ \n");
             jLabel2.setFocusable(false);
             jlbl_mensajeVPI.setFocusable(false);
             this.add(jLabel2, null);
             this.add(jTextArea1, null);
             this.add(jlbl_barraEstadoVPI,null);
             this.add(jlbl_mensajeVPI, null);
             this.jTextArea1.requestFocus();
        }



    //--------------------Eventos del teclado --------------//
      private void jTextArea1_KeyPressed(java.awt.event.KeyEvent evt) {
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          accept = false;
          this.dispose();
      }
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
        accept = true;
        this.dispose();
        imprimir_recibo(reimpresionv, fechaRecaudada, horaRecaudada);
      }

    }

}

   private boolean imprimir_recibo_LPT(String ref,String pSalidaImpresion,boolean reimpresioni,String pnombreFormato,String fechaRecaudada,String horaRecaudada){
            String SalidaImpresion = pSalidaImpresion;
            String nombreFormato = pnombreFormato;
            String sCad="\n";
            sCad = sCad+"    AUTOBUSES MEX-PUE ESTRELLA ROJA";
            //sCad =sCad+"\n";
            //sCad = sCad+"REF. : "+ref;
            if(reimpresioni)
            {
                sCad =sCad+"\n";
                sCad = sCad+"        REIMPRESION DE TICKET";
            }

            sCad =sCad+"\n";
            sCad =sCad+"\n";
            sCad = sCad+"ACTIVIDAD   :  GUARDIA";
            sCad =sCad+"\n";
            sCad = sCad+"SERVICIO    :  "+jCboServicio.getSelectedItem().toString();
                    sCad =sCad+"\n";
                    sCad = sCad+"OPERADOR  :  "+jTxtNumeroOperador.getText()+"-"+jTxtNombreOperador.getText();
                    sCad =sCad+"\n";
                    sCad = sCad+"TERMINAL  :  "+jcmbxTerminal.getSelectedItem();
                    sCad =sCad+"\n";
                    sCad = sCad+"FECHA INICIO GUARDIA  :  "+jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText();
                    sCad =sCad+"\n";
                    sCad = sCad+"FECHA FIN GUARDIA  :  "+jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText();
            sCad =sCad+"\n";
            /*sCad =sCad+"\n";
            sCad = sCad+"SUELDO      :  "+jtxt_monto.getText();
            sCad =sCad+"\n";
            sCad = sCad+"RETENCION   :  "+jtxt_retencion.getText();
            sCad =sCad+"\n";
             float total = (Float.valueOf(jtxt_monto.getText()))- (Float.valueOf(jtxt_retencion.getText()));
             long total2 = Float.valueOf(total).longValue();
            sCad = sCad+"TOTAL       :  "+total;
            sCad =sCad+"\n";
            sCad =sCad+"\n";
            sCad = sCad+"     ( "+new cantidad_a_letras().toLetras((long) total2) + " Pesos 00/M.N.  )";
            sCad =sCad+"\n";
            sCad =sCad+"\n";
             *
             */
            sCad = sCad+"RECAUDADOR  :  "+USUARIO_NUMERO+"-"+USUARIO_NOMBRE;
            SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
            SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
            Timestamp fecha_servidor = (tmActual==null?sesionVenta.getFechaServidor():tmActual);
            sCad = sCad+"\n";
            if(reimpresioni)
            {
                sCad = sCad+"FECHA DE RECAUDACION: "+formatf.format(new Date(fecha_servidor.getTime()));
                sCad = sCad+"\n";
                sCad = sCad+"HORA DE RECAUDACION : "+formath.format(new Date(fecha_servidor.getTime()));
            }
            else
            {
                sCad = sCad+"FECHA DE RECAUDACION: "+fechaRecaudada;
                sCad = sCad+"\n";
                sCad = sCad+"HORA DE RECAUDACION : "+horaRecaudada;
                sCad = sCad+"\n";
                sCad = sCad+"FECHA DE REIMPRESION: "+formatf.format(new Date(fecha_servidor.getTime()));
                sCad = sCad+"\n";
                sCad = sCad+"HORA DE REIMPRESION : "+formath.format(new Date(fecha_servidor.getTime()));
            }
            sCad = sCad+"\n";
            sCad = sCad+"\n";
            sCad = sCad+"FIRMA ________________________";
            sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
            sCad = sCad+"         .";

            String  ticketTermico = "Q200,0\r\n"
                    + "q831\r\n"
                    + "rN\r\n"
                    + "S4\r\n"
                    + "D7\r\n"
                    + "ZT\r\n"
                    + "JB\r\n"
                    + "O\r\n"
                    + "R124,0\r\n"
                    + "f100\r\n"
                    + "N\r\n";
                    if(reimpresioni)
                     ticketTermico = ticketTermico+"A23,550,0,4,1,1,N,\"Fecha Reimpresion:\"\r\n";
                    ticketTermico = ticketTermico+ "A21,476,0,4,1,1,N,\"Fecha Recaudacion:\"\r\n"
                    + "A60,286,0,4,1,1,N,\"Terminal:\"\r\n";
                    if(reimpresioni)
                        ticketTermico = ticketTermico+"A39,589,0,4,1,1,N,\"Hora Reimpresion:\"\r\n";
                    ticketTermico = ticketTermico+ "A37,516,0,4,1,1,N,\"Hora Recaudacion:\"\r\n"
                    + "A20,379,0,4,1,1,N,\"   Fecha Fin\"\r\n"
                    + "A20,401,0,4,1,1,N,\"    Guardia:\"\r\n"
                    + "A14,322,0,4,1,1,N,\"Fecha Inicio\"\r\n"
                    + "A14,344,0,4,1,1,N,\"    Guardia:\"\r\n"
                    + "A59,251,0,4,1,1,N,\"Operador:\"\r\n"
                    + "A213,251,0,3,1,1,N,\""+jTxtNumeroOperador.getText()+"-"+jTxtNombreOperador.getText()+"\"\r\n";
                    if(reimpresioni)
                    {
                        ticketTermico = ticketTermico+"A323,549,0,4,1,1,N,\""+formatf.format(new Date(fecha_servidor.getTime()))+"\"\r\n";
                        ticketTermico = ticketTermico+ "A321,476,0,4,1,1,N,\""+fechaRecaudada+"\"\r\n";
                    }
                    else
                        ticketTermico = ticketTermico+ "A321,476,0,4,1,1,N,\""+formatf.format(new Date(fecha_servidor.getTime()))+"\"\r\n";
                    ticketTermico = ticketTermico+ "A213,286,0,4,1,1,N,\""+jcmbxTerminal.getSelectedItem()+"\"\r\n";
                    if(reimpresioni)
                    {
                       ticketTermico = ticketTermico+"A411,589,0,3,1,1,N,\"Horas\"\r\n";
                        ticketTermico = ticketTermico+"A323,589,0,4,1,1,N,\""+formath.format(new Date(fecha_servidor.getTime()))+"\"\r\n";                        
                    }
                    ticketTermico = ticketTermico+ "A410,516,0,3,1,1,N,\"Horas\"\r\n";
                    if(reimpresioni)
                        ticketTermico = ticketTermico + "A322,516,0,4,1,1,N,\""+horaRecaudada+"\"\r\n";
                    else
                        ticketTermico = ticketTermico + "A322,516,0,4,1,1,N,\""+formath.format(new Date(fecha_servidor.getTime()))+"\"\r\n";
                    ticketTermico = ticketTermico + "A219,388,0,4,1,1,N,\""+jTxtFechaFinal.getText()+" "+jTxtHoraFinal.getText()+"\"\r\n"
                    + "A213,330,0,4,1,1,N,\""+jTxtFechaInicial.getText()+" "+jTxtHoraInicial.getText()+"\"\r\n"
                    + "A223,443,0,2,1,1,N,\""+USUARIO_NUMERO+"-"+USUARIO_NOMBRE+"\"\r\n";
                    if(!reimpresioni)
                        ticketTermico = ticketTermico+ "A246,610,0,4,1,1,N,\"Firma\"\r\n";
                    ticketTermico = ticketTermico+ "A38,440,0,4,1,1,N,\"Recaudador:\"\r\n"
                    + "A211,177,0,4,1,1,N,\"GUARDIA\"\r\n"
                    + "A213,211,0,4,1,1,N,\""+jCboServicio.getSelectedItem().toString()+"\"\r\n"
                    + "A42,175,0,4,1,1,N,\"Actividad:\"\r\n"
                    + "A57,213,0,4,1,1,N,\"Servicio:\"\r\n"
                    + "A134,96,0,4,1,1,N,\" "+(reimpresioni?"REIMPRESION DE TICKET":"IMPRESION DE TICKET")+"\"\r\n"
                    + "A40,63,0,4,1,1,N,\"AUTOBUSES MEX-PUE ESTRELLA ROJA\"\r\n";
                    if(!reimpresioni)
                            ticketTermico = ticketTermico+ "LO155,601,256,1\r\n";
                    ticketTermico = ticketTermico+ "LO88,128,407,3\r\n"
                    + "P1\r\n";


          try{
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = "C:\\TICKET_PAGO_ACT_ADICIONAL_"+jTxtNumeroOperador.getText()+".TXT";

            FileDescriptor fd = new FileDescriptor();
            FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
            PrintStream ps = new PrintStream(os);
            if(nombreFormato.equals("TICKET_TERMICO"))
                ps.print(ticketTermico);
            else
                ps.print(sCad); // CADENA A IMPRIMIR
            ps.flush();
            os.close();
        }catch(java.io.FileNotFoundException fsctex){
            fsctex.printStackTrace();
            return false;
        }catch(Exception sctex){
            sctex.printStackTrace();
            return false;
        }
      return true;

   }


class Jdlg_Pregunta extends JDialog {
                            private JLabel jLabel1 = new JLabel();
                            private JLabel jLabel2 = new JLabel();
                            private JLabel jLabel3 = new JLabel();
                            private JLabel jlbl_barraEstadoP = new JLabel();
                            private ImageIcon imagen_pregunta = new ImageIcon(vista_pago_impresion.class.getResource("pregunta.gif"));
                            private String numref;

                            public Jdlg_Pregunta(String title, String nref) {
                                this.setTitle(title);
                                this.setModal(true);
                                this.setDefaultLookAndFeelDecorated(true);
                                numref = nref;
                                this.setUndecorated(true);
                                this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
                                this.setAlwaysOnTop(true);
                                try {
                                    jbInit();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                jLabel3.requestFocus();
                                this.addComponentListener(new ComponentAdapter() {
                                            public void componentShown(ComponentEvent ce) {
                                                jLabel3.requestFocusInWindow();
                                            }
                                        });
                            }

                            private void jbInit() throws Exception {
                                this.setSize(new Dimension(333, 128));
                                this.getContentPane().setLayout( null );
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                Dimension DilaogSize = this.getSize();
                                if (DilaogSize.height > screenSize.height) {
                                    DilaogSize.height = screenSize.height;
                                }
                                if (DilaogSize.width > screenSize.width) {
                                    DilaogSize.width = screenSize.width;
                                }
                                this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
                                jLabel1.setText("¿Se imprimio correctamente el comprobante?");
                                jLabel1.setBounds(new Rectangle(45, 35, 265, 15));
                                jLabel1.setFont(new Font("Arial", 1, 12));
                                jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    jLabel1_KeyPressed(evt);
                                }
                                });
                                jLabel3.setFocusTraversalKeysEnabled(false);
                                jLabel2.setBounds(new Rectangle(5, 5, 35, 45));
                                jLabel2.setSize(new Dimension(35, 45));
                                jLabel2.setIcon(imagen_pregunta);

                                jLabel3.setBounds(new Rectangle(45, 10, 285, 15));
                                jLabel3.setFont(new Font("Arial", 1, 12));
                                jLabel3.setText("Referencia de Pago: "+numref);
                                jlbl_barraEstadoP.setFont(new java.awt.Font("Tahoma", 1, 12));
                                jlbl_barraEstadoP.setForeground(new java.awt.Color(153, 153, 153));
                                jlbl_barraEstadoP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                                jlbl_barraEstadoP.setBounds(new Rectangle(0, 69, 333, 25));
                                jlbl_barraEstadoP.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
                                jlbl_barraEstadoP.setHorizontalAlignment(JTextField.CENTER);
                                this.add(jLabel3, null);
                                this.add(jLabel2, null);
                                this.add(jLabel1, null);
                                this.add(jlbl_barraEstadoP,null);
                                jLabel1.setFocusable(false);
                                jLabel2.setFocusable(false);
                                jLabel3.setFocusable(true);
                                jLabel3.requestFocus();

                            }

                     private void jLabel1_KeyPressed(java.awt.event.KeyEvent evt) {
                          if(evt.getKeyCode() == evt.VK_ESCAPE)
                          {
                                reimpresion = true;
                                this.dispose();
                          }
                          if(evt.getKeyCode() == evt.VK_ENTER)
                          {
                            reimpresion = false;
                            this.dispose();
                          }
                        }
    }


    class CustomTableCellRenderer extends DefaultTableCellRenderer{

    public Component getTableCellRendererComponent (JTable table,Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        //System.out.println("Row("+row+") ServSinRet: "+((Guia)FacturasPendientesTableModel.guias.get(row)).getServicioSinRet());
        if (isSelected) {
            cell.setBackground(new Color(0,175,255));
            cell.setForeground(Color.WHITE);
        }
        else {
            cell.setForeground(Color.BLACK);
            //if (getText().equals("AUTORIZADA"))
            if(table.getValueAt(row, 6).equals("NO AUTORIZADA"))
            {
                //cell.setBackground(Color.RED);
                 cell.setBackground(new Color(255,102,102));
                 //cell.setForeground(Color.WHITE);
            }
            else
            {
                //cell.setBackground(Color.GREEN);
                cell.setBackground(new Color(130,255,130));
                //cell.setForeground(Color.WHITE);
            }

            /*
            if (row % 2 == 0) {
                cell.setBackground(new Color(217,229,241));
            }
            else {
                cell.setBackground(Color.WHITE);
            }
             *
             */
        }

            setHorizontalAlignment(JTextField.LEFT);

        return cell;
    }
}

}
