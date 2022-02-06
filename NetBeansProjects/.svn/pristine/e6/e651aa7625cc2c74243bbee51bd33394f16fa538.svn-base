/*
 * JPnlPsdX.java
 *
 * Created on 11 de octubre de 2007, 08:23 PM
 */

package tms_psd;

import com.estrellaroja.confirmacionviajes.forms.JdlgConfirmacionViajes;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_TextFields.JDateTextField;
import tms_TextFields.JHourTextField;
import tms_accesoubicacionesX.JIFAccesoUbicaciones;
import tms_asigsust.JDlgAsignacionAutobus;
import tms_asigsust.JDlgAsignacionOperador;
import tms_asigsust.JDlgSustOperador;
import tms_asigsust.JDlgSustitucion;
import tms_cambiarHorarios.JDlgCH;
import tms_corridaextra.JDlgACHorarios;
import tms_corridaextra.JDlgCorridasExtras;
import tms_estadosoperador.JIFEstadosOperador;
import tms_psd.util.BarraMensajes;
import tms_psd.util.JDlgAceptar;
import tms_psd.util.JDlgSiNo;
import tms_psd.util.JDlgSiNoCancelar;
import tms_psd.util.jDlgAutorizaSupervisor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author  ocruz
 */
public class JPnlPsdX extends javax.swing.JPanel{
    
    /** Creates new form JPnlPsdX */
    public JPnlPsdX(SesionVenta pSesionVenta, JIFPSD pJIF,Vector pdatosIniciales) {
        this.datosIniciales = pdatosIniciales;
        this.sesionVenta = pSesionVenta;
        this.JIF = pJIF;
        initComponents();
        inhabilitarF10();
        jTblMaestro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        System.out.println("Revision 13/08/2014");
    }
    
    private void inhabilitarF10() {
        this.jTxtAutobus.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtOperador.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFecha.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtHora.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFecha2.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtHora2.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboServicio.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboTerminal.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboDestino.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboEmpresa.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbCorridaExtra.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblMaestro.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
    }
    
    public void setComponentes(JIFPSD pJPnlPSD) {
        this.jPnlPSD = pJPnlPSD;
    }

    private void AnchoColumnasMaestro(){
        TableColumn column = null;
        int anchoContenedor = jScpDetalle.getWidth(); //(int)(jScpDetalle.getWidth()*0.80);
        for (int i = 0; i < 11; i++) {
            column = jTblMaestro.getColumnModel().getColumn(i);
            switch(i){
//"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora"
                case 0:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.14)); break;
                case 1:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.015)); break;
                case 2:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.015)); break;
                case 3:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.18)); break;
                case 4:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
                case 5:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 6:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.08)); break;
                case 7:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.08)); break;
                case 8:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 9:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.09)); break;
                case 10:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.11)); break;
            }
            column.setResizable(false);
        }
        /*jVwpMaestro.setView(jTblMaestro);
        jVwpMaestro.setPreferredSize(jTblMaestro.getPreferredSize());
        jScpDetalle.setRowHeaderView(jVwpMaestro);
        jScpDetalle.setCorner(JScrollPane.UPPER_LEFT_CORNER, jTblMaestro.getTableHeader());*/
    }
    
    private void AnchoColumnas(){
        TableColumn column = null;
        //int anchoContenedor = (int)(jScpA.getWidth()*0.80);
        int anchoContenedor = (int)jScpA.getWidth();
        for (int i = 0; i < 10; i++) {
            column = jTblA.getColumnModel().getColumn(i);
            switch(i){
//"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora", "Ocup"
                case 0:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 1:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.03)); break;
                case 2:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.03)); break;
                case 3:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 4:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 5:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.12)); break;
                case 6:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.11)); break;
                case 7:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.11)); break;
                case 8:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 9:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
            }
            column.setResizable(false);
        }
    }
    
    private void AnchoColumnasB(){
        TableColumn column = null;
        //int anchoContenedor = (int)(jScpB.getWidth()*0.80);
        int anchoContenedor = jScpB.getWidth();
        for (int i = 0; i < 10; i++) {
            column = jTblB.getColumnModel().getColumn(i);
            switch(i){
//"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora", "Ocup"
                case 0:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 1:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.03)); break;
                case 2:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.03)); break;
                case 3:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 4:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 5:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.12)); break;
                case 6:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.11)); break;
                case 7:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.11)); break;
                case 8:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 9:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
            }
            column.setResizable(false);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCboServicio = new javax.swing.JComboBox(sesionVenta.getVectorServicios());
        jLabel2 = new javax.swing.JLabel();
        jCboEmpresa = new javax.swing.JComboBox(sesionVenta.getVectorEmpresas());
        jLabel3 = new javax.swing.JLabel();
        jTxtAutobus = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtOperador = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtFecha = new JDateTextField();
        jTxtHora = new JHourTextField();
        jCkbCorridaExtra = new javax.swing.JCheckBox();
        jLblTerminal = new javax.swing.JLabel();
        jCboTerminal = new javax.swing.JComboBox();
        jCboTerminal = new JComboBox(sesionVenta.getOrigenesDBLink());
        jCboTerminal.setSelectedItem(sesionVenta.getTerminalNombre());
        jLabel8 = new javax.swing.JLabel();
        jTxtFecha2 = new JDateTextField();
        jTxtHora2 = new JHourTextField();
        jLblTerminal1 = new javax.swing.JLabel();
        jCboDestino = new javax.swing.JComboBox();
        jCboDestino = new JComboBox(sesionVenta.getDestinos());
        jCboDestino.setSelectedItem("TODOS");
        jScpDetalle = new javax.swing.JScrollPane();
        jTblMaestro = new javax.swing.JTable();
        jPnlAgrupacion = new javax.swing.JPanel();
        jScpA = new javax.swing.JScrollPane();
        jTblA = new javax.swing.JTable();
        jScpB = new javax.swing.JScrollPane();
        jTblB = new javax.swing.JTable();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Servicio:");

        jCboServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboServicio.setFocusTraversalKeysEnabled(false);
        jCboServicio.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jCboServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboServicioKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Empresa:");

        jCboEmpresa.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboEmpresa.setFocusTraversalKeysEnabled(false);
        jCboEmpresa.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jCboEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboEmpresaKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Autobus:");

        jTxtAutobus.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtAutobus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtAutobus.setFocusTraversalKeysEnabled(false);
        jTxtAutobus.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jTxtAutobus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtAutobusKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Operador:");

        jTxtOperador.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtOperador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtOperador.setFocusTraversalKeysEnabled(false);
        jTxtOperador.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jTxtOperador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtOperadorKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Desde:");

        jTxtFecha.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFecha.setFocusTraversalKeysEnabled(false);
        jTxtFecha.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jTxtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFechaKeyPressed(evt);
            }
        });

        jTxtHora.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtHora.setFocusTraversalKeysEnabled(false);
        jTxtHora.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jTxtHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtHoraKeyPressed(evt);
            }
        });

        jCkbCorridaExtra.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbCorridaExtra.setText("Solo Corridas Extras");
        jCkbCorridaExtra.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbCorridaExtra.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbCorridaExtra.setFocusTraversalKeysEnabled(false);
        jCkbCorridaExtra.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jCkbCorridaExtra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCkbCorridaExtraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCkbCorridaExtraFocusLost(evt);
            }
        });
        jCkbCorridaExtra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCkbCorridaExtraKeyPressed(evt);
            }
        });

        jLblTerminal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblTerminal.setText("Origen:");

        jCboTerminal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCboTerminal.setFocusTraversalKeysEnabled(false);
        jCboTerminal.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jCboTerminal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboTerminalKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Hasta:");

        jTxtFecha2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtFecha2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFecha2.setFocusTraversalKeysEnabled(false);
        jTxtFecha2.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jTxtFecha2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtFecha2KeyPressed(evt);
            }
        });

        jTxtHora2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHora2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtHora2.setFocusTraversalKeysEnabled(false);
        jTxtHora2.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jTxtHora2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtHora2KeyPressed(evt);
            }
        });

        jLblTerminal1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblTerminal1.setText("Destino:");

        jCboDestino.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboDestino.setFocusTraversalKeysEnabled(false);
        jCboDestino.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jCboDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCboDestinoKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel5)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jTxtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTxtHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel8))
                    .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 176, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jTxtFecha2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTxtHora2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jCkbCorridaExtra))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jCboEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(14, 14, 14)
                        .add(jLblTerminal)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTxtAutobus)
                    .add(jCboTerminal, 0, 103, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLblTerminal1)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTxtOperador)
                    .add(jCboDestino, 0, 102, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTxtOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jTxtAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jCboEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jTxtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtFecha2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtHora2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblTerminal1)
                    .add(jCboDestino, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCboTerminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblTerminal)
                    .add(jCkbCorridaExtra))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScpDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corrida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray)); // NOI18N
        jScpDetalle.setFocusTraversalKeysEnabled(false);

        jTblMaestro.setFont(new java.awt.Font("Tahoma", 1, 13));
        jTblMaestro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblMaestro.setModel(xMaestro);
        jTblMaestro.setName("-1"); // NOI18N
        jTblMaestro.setShowHorizontalLines(false);
        jTblMaestro.setFocusTraversalKeysEnabled(false);
        jTblMaestro.getTableHeader().setReorderingAllowed(false);
        jTblMaestro.setDefaultRenderer(Object.class, new lineasColorRenderer());
        jTblMaestro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblMaestroKeyPressed(evt);
            }
        });
        jScpDetalle.setViewportView(jTblMaestro);

        jPnlAgrupacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agrupacion de corridas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPnlAgrupacion.setFocusable(false);

        jScpA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corrida original:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScpA.setFocusable(false);

        jTblA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblA.setModel(xA);
        jTblA.setFocusable(false);
        jScpA.setViewportView(jTblA);

        jScpB.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agruparla en esta corrida:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jScpB.setFocusable(false);

        jTblB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblB.setModel(xB);
        jTblB.setFocusable(false);
        jScpB.setViewportView(jTblB);

        org.jdesktop.layout.GroupLayout jPnlAgrupacionLayout = new org.jdesktop.layout.GroupLayout(jPnlAgrupacion);
        jPnlAgrupacion.setLayout(jPnlAgrupacionLayout);
        jPnlAgrupacionLayout.setHorizontalGroup(
            jPnlAgrupacionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
            .add(jScpB, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
        );
        jPnlAgrupacionLayout.setVerticalGroup(
            jPnlAgrupacionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlAgrupacionLayout.createSequentialGroup()
                .add(jScpA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlAgrupacion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
            .add(jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPnlAgrupacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCkbCorridaExtraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCkbCorridaExtraFocusLost
// TODO add your handling code here:
        jCkbCorridaExtra.setOpaque(false);
    }//GEN-LAST:event_jCkbCorridaExtraFocusLost

    private void jCkbCorridaExtraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCkbCorridaExtraFocusGained
// TODO add your handling code here:
        jCkbCorridaExtra.setOpaque(true);
        jCkbCorridaExtra.setBackground(xorColorFondo);
    }//GEN-LAST:event_jCkbCorridaExtraFocusGained

    private void jCboDestinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboDestinoKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: jCboTerminal.requestFocusInWindow(); break;
            case KeyEvent.VK_RIGHT: jCboServicio.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jCboDestinoKeyPressed

    private void jTxtHora2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtHora2KeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT:
                if(jTxtHora2.getText().length()!=0 && jTxtHora2.getText().length()!=5){
                    DialogoAceptar = new JDlgAceptar("¡Valor de hora incorrecta!", "Ingrese un valor como 15:30 o deje vacio el campo.", Color.RED);
                    jTxtHora2.selectAll();
                    jTxtHora2.requestFocusInWindow();
                    return;
                }
                if(evt.getKeyCode() == KeyEvent.VK_LEFT){ jTxtFecha2.selectAll(); jTxtFecha2.requestFocusInWindow(); }
                if(evt.getKeyCode() == KeyEvent.VK_RIGHT){ jCkbCorridaExtra.requestFocusInWindow(); }
                break;
        }
    }//GEN-LAST:event_jTxtHora2KeyPressed

    private void jTxtFecha2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFecha2KeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT:
                if(jTxtFecha2.getText().length()==1 || jTxtFecha2.getText().length()==4 || (jTxtFecha2.getText().length()>6 &&  jTxtFecha2.getText().length()<10)){
                    DialogoAceptar = new JDlgAceptar("¡Valor de fecha incorrecta!", "Ingrese un valor como 16/09/2007 o deje vacio el campo.", Color.RED);
                    jTxtFecha2.setText("");
                    jTxtFecha2.requestFocusInWindow();
                    return;
                }

                if(jTxtFecha2.getText().length()==2 || jTxtFecha2.getText().length()==3){
                    String dtStrFecha = formatoFecha.format(new Date()).substring(3);
                    if(jTxtFecha2.getText().length()==2) jTxtFecha2.setText(jTxtFecha2.getText() +"/"+dtStrFecha);
                    else jTxtFecha2.setText(jTxtFecha2.getText()+dtStrFecha);
                }

                if(jTxtFecha2.getText().length()==5 || jTxtFecha2.getText().length()==6){
                    String dtStrFecha = formatoFecha.format(new Date()).substring(6);
                    if(jTxtFecha2.getText().length()==5) jTxtFecha2.setText(jTxtFecha2.getText() +"/"+dtStrFecha);
                    else jTxtFecha2.setText(jTxtFecha2.getText()+dtStrFecha);
                }
                
                if(evt.getKeyCode() == KeyEvent.VK_LEFT){ jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); }
                if(evt.getKeyCode() == KeyEvent.VK_RIGHT){ jTxtHora2.selectAll(); jTxtHora2.requestFocusInWindow(); }
             break;
        }
    }//GEN-LAST:event_jTxtFecha2KeyPressed

    private void jCboTerminalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboTerminalKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: 
                sesionVenta.getDBLinkClaveOrigen(jCboTerminal.getSelectedItem().toString());
                if(jCboTerminal.getSelectedItem().toString().equals(sesionVenta.getTerminalNombre())) transaccionRemota = false;
                else transaccionRemota = true;
                jCkbCorridaExtra.requestFocusInWindow(); break;
            case KeyEvent.VK_RIGHT:
                sesionVenta.getDBLinkClaveOrigen(jCboTerminal.getSelectedItem().toString());
                if(jCboTerminal.getSelectedItem().toString().equals(sesionVenta.getTerminalNombre())) transaccionRemota = false;
                else transaccionRemota = true;
                jCboDestino.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jCboTerminalKeyPressed

    private void jTxtFechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFechaKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT:
                if(jTxtFecha.getText().length()==1 || jTxtFecha.getText().length()==4 || (jTxtFecha.getText().length()>6 &&  jTxtFecha.getText().length()<10)){
                    DialogoAceptar = new JDlgAceptar("¡Valor de fecha incorrecta!", "Ingrese un valor como 16/09/2007 o deje vacio el campo.", Color.RED);
                    jTxtFecha.setText("");
                    jTxtFecha.requestFocusInWindow();
                    return;
                }

                if(jTxtFecha.getText().length()==2 || jTxtFecha.getText().length()==3){
                    String dtStrFecha = formatoFecha.format(new Date()).substring(3);
                    if(jTxtFecha.getText().length()==2) jTxtFecha.setText(jTxtFecha.getText() +"/"+dtStrFecha);
                    else jTxtFecha.setText(jTxtFecha.getText()+dtStrFecha);
                }

                if(jTxtFecha.getText().length()==5 || jTxtFecha.getText().length()==6){
                    String dtStrFecha = formatoFecha.format(new Date()).substring(6);
                    if(jTxtFecha.getText().length()==5) jTxtFecha.setText(jTxtFecha.getText() +"/"+dtStrFecha);
                    else jTxtFecha.setText(jTxtFecha.getText()+dtStrFecha);
                }
                
                if(evt.getKeyCode() == KeyEvent.VK_LEFT){ jTxtOperador.selectAll(); jTxtOperador.requestFocusInWindow(); }
                if(evt.getKeyCode() == KeyEvent.VK_RIGHT){ jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); }
             break;
        }
    }//GEN-LAST:event_jTxtFechaKeyPressed

    private void jTxtOperadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtOperadorKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: jTxtAutobus.selectAll(); jTxtAutobus.requestFocusInWindow(); break;
            case KeyEvent.VK_RIGHT: jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jTxtOperadorKeyPressed

    private void jTxtAutobusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtAutobusKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: jCboEmpresa.requestFocusInWindow(); break;
            case KeyEvent.VK_RIGHT: jTxtOperador.selectAll(); jTxtOperador.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jTxtAutobusKeyPressed

    private void jCboEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboEmpresaKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: jCboServicio.requestFocusInWindow(); break;
            case KeyEvent.VK_RIGHT: jTxtAutobus.selectAll(); jTxtAutobus.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jCboEmpresaKeyPressed

    private void jCboServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCboServicioKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: jCboDestino.requestFocusInWindow(); break;
            case KeyEvent.VK_RIGHT: jCboEmpresa.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jCboServicioKeyPressed

    private void jTxtHoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtHoraKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT:
                if(jTxtHora.getText().length()!=0 && jTxtHora.getText().length()!=5){
                    DialogoAceptar = new JDlgAceptar("¡Valor de hora incorrecta!", "Ingrese un valor como 15:30 o deje vacio el campo.", Color.RED);
                    jTxtHora.selectAll();
                    jTxtHora.requestFocusInWindow();
                    return;
                }
                if(evt.getKeyCode() == KeyEvent.VK_LEFT){ jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow(); }
                if(evt.getKeyCode() == KeyEvent.VK_RIGHT){ jTxtFecha2.selectAll(); jTxtFecha2.requestFocusInWindow(); }
                break;
        }
    }//GEN-LAST:event_jTxtHoraKeyPressed

    private void jTblMaestroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblMaestroKeyPressed
// TODO add your handling code here:        
        if(jTblMaestro.getRowCount() == 0 || jTblMaestro.getSelectedRow() == -1) return;
        fila = jTblMaestro.getSelectedRow();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                if(jPnlAgrupacion.isVisible()){
                    xA.setDataVector(null, encA);
                    AnchoColumnas();
                    xB.setDataVector(null, encA);
                    AnchoColumnas();
                    jPnlAgrupacion.setVisible(false);
                } 
                limpiar(); jCboServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_F5:
                if(!jPnlAgrupacion.isVisible() && !evt.isControlDown()) agruparCorridas();
                if(evt.isControlDown())    confrimarViaje() ;
                break;
            case KeyEvent.VK_F6: if(!jPnlAgrupacion.isVisible()){ 
                                    if(evt.isAltDown()) asigAutobus();
                                    else sustAutobus();
                                }
                break;
            case KeyEvent.VK_F7: if(!jPnlAgrupacion.isVisible()){
                                    if(evt.isAltDown()) asigOperador();
                                    else sustOperador();
                                 }
                break;
            case KeyEvent.VK_F9: if(jPnlAgrupacion.isVisible())  EjecutaAgrupacion(); break;
            case KeyEvent.VK_F11: if(jPnlAgrupacion.isVisible()) SeleccionaA(); break;
            case KeyEvent.VK_F12: if(jPnlAgrupacion.isVisible()) SeleccionaB(); break;
            
            case KeyEvent.VK_F1: if(!jPnlAgrupacion.isVisible()){
                                    if(!evt.isControlDown()) abrirCorrida();
                                    else abrirCorridaPorLotes();
                                }
                                break;
            case KeyEvent.VK_F2: if(!jPnlAgrupacion.isVisible()){
                                    if(!evt.isAltDown()){
                                        if(!evt.isControlDown()) cerrarCorrida();
                                        else cerrarCorridaPorLotes();
                                    }
                                    else{
                                        if(!evt.isControlDown()) borrarCorrida();
                                    }
                                }
                break;
            case KeyEvent.VK_F3: if(!jPnlAgrupacion.isVisible()){
                                    if(!evt.isAltDown()){
                                        if(!evt.isControlDown())
                                            verDetalle();
                                        else
                                            borrarCorridaPorLote();
                                    }
                                    else{
                                        if(!evt.isControlDown())
                                            ActualizacionesVarias();
                                    }       
                                 }
                                break;
               case KeyEvent.VK_F4: if(!jPnlAgrupacion.isVisible()){
                        if(evt.isControlDown())
                            ActualizacionesVariasPorLotes();
                 }
                break;
            }
    }//GEN-LAST:event_jTblMaestroKeyPressed

    private void jCkbCorridaExtraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCkbCorridaExtraKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_1: if(evt.isControlDown()){
                                    this.eventoTeclado=evt;
                                    try {this.JIF.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(evt.isControlDown()) 
                    try {this.JIF.setIcon(true);
                    } catch (PropertyVetoException ex) {
                        ;
                    } break;
            case KeyEvent.VK_F4: if(!evt.isAltDown()) CerrarIFrame(); break;
            case KeyEvent.VK_F3: 
                if(evt.isAltDown())
                    estadosOperador();
                else
                    accesoUbicaciones();
                break;
            case KeyEvent.VK_F8: crearCorridasExtra(); break;
            case KeyEvent.VK_F9: cambiarHorarios(); break;//case KeyEvent.VK_F9: rolCadena(); break;
            //case KeyEvent.VK_F1: jPnlPSD.setTab(1); break;
            case KeyEvent.VK_ENTER: busqueda(); break;
            case KeyEvent.VK_LEFT: jTxtHora2.selectAll(); jTxtHora2.requestFocusInWindow(); break;
            case KeyEvent.VK_RIGHT: jCboTerminal.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jCkbCorridaExtraKeyPressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        if(jTblMaestro.getRowCount()>0){
            jTblMaestro.requestFocusInWindow();
            return;
        }
        jPnlAgrupacion.setVisible(false);
        xMaestro.setDataVector(null,maestro);
        //xDetalle.setDataVector(null,detalle);
        AnchoColumnasMaestro();
        //AnchoColumnasDetalle();
        jPnlPSD.setMensaje(barraMensajes.getMsgComun(0));
        jTxtFecha.setText(formatoFecha.format(new Date()));
        this.setFoco();
    }//GEN-LAST:event_formComponentShown

    public void setFoco(){
        if(jTblMaestro.getRowCount()>0){
            jTblMaestro.requestFocusInWindow();
            return;
        }
        jCboServicio.requestFocusInWindow();
    }
    
    // METODOS
    private void limpiar(){
        jCboServicio.setSelectedIndex(0);
        jCboTerminal.setSelectedItem(sesionVenta.getTerminalNombre());
        sesionVenta.getDBLinkClaveOrigen(jCboTerminal.getSelectedItem().toString());
        transaccionRemota = false;
        jCboEmpresa.setSelectedIndex(0);
        jTxtAutobus.setText("");
        jTxtOperador.setText("");
        jTxtFecha.setText(formatoFecha.format(new Date()));
        jTxtHora.setText("");
        jTxtFecha2.setText("");
        jTxtHora2.setText("");
        jCkbCorridaExtra.setSelected(false);
        //xDetalle.setDataVector(null, detalle);
        xMaestro.setDataVector(null, maestro);
        AnchoColumnasMaestro();
        //AnchoColumnasDetalle();
        jPnlPSD.setMensaje(barraMensajes.getMsgComun(0));
    }
    
    private boolean busqueda(){
        sesionVenta.getDBLinkClaveOrigen(jCboTerminal.getSelectedItem().toString());
        if(jCboTerminal.getSelectedItem().toString().equals(sesionVenta.getTerminalNombre())){
            transaccionRemota = false;
            limProc=500;
        }
        else{
            transaccionRemota = true;
            limProc=300;
        }
        
        if(!validaFechas()) return false;
        
        if(jCboTerminal.getSelectedItem().toString().equals(jCboDestino.getSelectedItem().toString())){
            DialogoAceptar = new JDlgAceptar("PSD.", "Origen es igual a destino.", Color.RED);
            return false;
        }
        
        int edoConsulta=sesionVenta.busqueda(jCboServicio.getSelectedItem().toString(), jCboEmpresa.getSelectedItem().toString(),
                jTxtAutobus.getText(), jTxtOperador.getText(), jCkbCorridaExtra.isSelected(),
                jTxtFecha.getText(), jTxtHora.getText(), jTxtFecha2.getText(), jTxtHora2.getText(),
                jCboDestino.getSelectedItem().toString());
        
        if(edoConsulta!=1){
            if(edoConsulta==-21){
                this.getToolkit().beep();
                DialogoAceptar = new JDlgAceptar("En este momento no es posible realizar la operacion.","<html>Por favor intentelo mas tarde o<br>contacte al administrador del sistema.</html>", Color.RED);
                return false;
            }
            xMaestro.setDataVector(null, maestro);
            //xDetalle.setDataVector(null, detalle);
            AnchoColumnasMaestro();
            //AnchoColumnasDetalle();
            DialogoAceptar = new JDlgAceptar("¡No existen corridas con estos criterios!", "Presione ENTER para continuar...", Color.RED);
            return false;
        }
        System.out.println("#corridas recuperadas por consulta: "+sesionVenta.getMaestro().length);
        xMaestro.setDataVector(sesionVenta.getMaestro(), maestro);
        //xDetalle.setDataVector(sesionVenta.getDetalle(), detalle);
        AnchoColumnasMaestro();
        //AnchoColumnasDetalle();
        jPnlPSD.setMensaje(barraMensajes.getMsgComun(1));
        jTblMaestro.setColumnSelectionInterval(0, 0);
        jTblMaestro.setRowSelectionInterval(0, 0);
        jTblMaestro.requestFocusInWindow();
        return true;
    }
    
    private boolean sustAutobus(){
        if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))){
            DialogoAceptar = new JDlgAceptar("¡Imposible sustituir autobus!", "La corrida debe estar abierta.", Color.RED);
            return false;
        }
        String edoActual="", edoSig="";
        if(sesionVenta.getTmsMonitorCorridaV().get(fila).getAutobusOriginalId()==null){
            DialogoAceptar = new JDlgAceptar("¡Imposible sustituir autobus!", "La corrida no tiene asignado un autobus original.", Color.RED);
            return false;
        }
        if(sesionVenta.getTmsMonitorCorridaV().get(fila).getAutobusId()!=null){
            String[] edosBus = sesionVenta.estadoAutobusActualSiguiente(sesionVenta.getTmsMonitorCorridaV().get(fila).getAutobus());
            if(edosBus!=null){
                edoActual = edosBus[0];
                edoSig = edosBus[1];
            }
        }
        String numEco=sesionVenta.getTmsMonitorCorridaV().get(fila).getAutobus();
        String strFlotillaAutobus=sesionVenta.flotillaDeAutobus(numEco);
        if(strFlotillaAutobus.equals("")){
            DialogoAceptar = new JDlgAceptar("¡Autobus sin flotilla relacionada!", "El autobus "+numEco+" no tiene flotilla.", Color.RED);
            return false;
        }
        JDlgSustitucion jDlgSustitucion = new JDlgSustitucion(sesionVenta, fila, edoActual, edoSig, numEco, strFlotillaAutobus);
        if(!jDlgSustitucion.getInicioGral()) return false;
        jDlgSustitucion.setVisible(true);
        switch(jDlgSustitucion.getResultado()){
            case 2: int regs = jDlgSustitucion.getRegSust();
                    //System.out.println("REG A SUST ::: "+regs);
                    if(regs>jTblMaestro.getRowCount()) regs=jTblMaestro.getRowCount();
                    String busOrig = (xMaestro.getValueAt(fila, 9)==null ? "" : xMaestro.getValueAt(fila, 9).toString());
                    
                    if(sesionVenta.getTmsMonitorCorridaV().get(fila).getAutobusOriginal()==null){
                        //xMaestro.setValueAt(jDlgSustitucion.getNumeroEconomicoSust(), fila, 9);
                        sesionVenta.getDetalle()[fila][0]=jDlgSustitucion.getNumeroEconomicoSust();
                        sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobusOriginal(jDlgSustitucion.getNumeroEconomicoSust());
                        sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreFlotilla(jDlgSustitucion.getFlotilla());
                        sesionVenta.getTmsMonitorCorridaV().get(fila).setFlotillaId(jDlgSustitucion.getFlotillaId());
                        sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobusOriginalId(jDlgSustitucion.getNumeroEconomicoSustId());
                    }
                    xMaestro.setValueAt(jDlgSustitucion.getNumeroEconomicoSust(), fila, 9);
                    xMaestro.setValueAt(jDlgSustitucion.getEmpresa(), fila, 4);
                    sesionVenta.getDetalle()[fila][1]=jDlgSustitucion.getNumeroEconomicoSust();
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobus(jDlgSustitucion.getNumeroEconomicoSust());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobusId(jDlgSustitucion.getNumeroEconomicoSustId());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreFlotilla(jDlgSustitucion.getFlotilla());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setFlotillaId(jDlgSustitucion.getFlotillaId());
                        
                    int filaNueva = fila+1, i, j;
                    for(i=1; i<regs; i++){
                        for(j=filaNueva; j<jTblMaestro.getRowCount(); j++){
                            if(xMaestro.getValueAt(j, 9)==null) continue;
                            if(busOrig.equals(xMaestro.getValueAt(j, 9).toString())){
                                xMaestro.setValueAt(jDlgSustitucion.getNumeroEconomicoSust(), j, 9);
                                xMaestro.setValueAt(jDlgSustitucion.getEmpresa(), j, 4);
                                sesionVenta.getDetalle()[fila][1]=jDlgSustitucion.getNumeroEconomicoSust();
                                sesionVenta.getTmsMonitorCorridaV().get(j).setAutobus(jDlgSustitucion.getNumeroEconomicoSust());
                                sesionVenta.getTmsMonitorCorridaV().get(j).setAutobusId(jDlgSustitucion.getNumeroEconomicoSustId());
                                sesionVenta.getTmsMonitorCorridaV().get(j).setNombreFlotilla(jDlgSustitucion.getFlotilla());
                                sesionVenta.getTmsMonitorCorridaV().get(j).setFlotillaId(jDlgSustitucion.getFlotillaId());
                                filaNueva=j+1;
                                break;
                            }
                        }
                    }
                    jTblMaestro.setColumnSelectionInterval(9,9);
                    jTblMaestro.setRowSelectionInterval(fila,fila);
                    jTblMaestro.requestFocusInWindow();
                break;
            case 3: xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("CANCELADA"), fila, 2); 
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setEstadoCorrida(xMaestro.getValueAt(fila, 2).toString());
                break;
        }
        return true;
    }
    
    private boolean sustOperador(){
        if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))){
            DialogoAceptar = new JDlgAceptar("¡Imposible sustituir operador!", "La corrida debe estar abierta.", Color.RED);
            return false;
        }
        if(sesionVenta.getTmsMonitorCorridaV().get(fila).getOperadorOriginalId()==null){
            DialogoAceptar = new JDlgAceptar("¡Imposible sustituir operador!", "La corrida no tiene asignado operador original.", Color.RED);
            return false;
        }
        String edoActual="", edoSig="";
        if(sesionVenta.getTmsMonitorCorridaV().get(fila).getOperador()!=null){
            String[] edosOper = sesionVenta.estadoOperadorActualSiguiente(sesionVenta.getTmsMonitorCorridaV().get(fila).getOperador());
            if(edosOper!=null){
                edoActual = edosOper[0];
                edoSig = edosOper[1];
            }
        }
        String strOperadorId = (sesionVenta.getTmsMonitorCorridaV().get(fila).getOperador() == null ? "" : sesionVenta.getTmsMonitorCorridaV().get(fila).getOperador().toString());
        JDlgSustOperador jDlgSustitucion = new JDlgSustOperador(sesionVenta, fila, edoActual, edoSig, strOperadorId);
        if(!jDlgSustitucion.getInicioGral()) return false;
        jDlgSustitucion.setVisible(true);
        switch(jDlgSustitucion.getResultado()){
            case 2: int regs = jDlgSustitucion.getRegSust();
                    if(regs>jTblMaestro.getRowCount()) regs=jTblMaestro.getRowCount();
                    String busOrig = (xMaestro.getValueAt(fila, 10)==null ? "" : xMaestro.getValueAt(fila, 10).toString());
                    if(sesionVenta.getTmsMonitorCorridaV().get(fila).getOperadorOriginal()==null){
                        sesionVenta.getDetalle()[fila][2]=jDlgSustitucion.getNumeroOperadorSust();
                        sesionVenta.getDetalle()[fila][3]=jDlgSustitucion.getNombreOperadorSust();
                        sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorOriginal(jDlgSustitucion.getNumeroOperadorSust());
                        sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreOperadorOriginal(jDlgSustitucion.getNombreOperadorSust());
                        sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorOriginalId(jDlgSustitucion.getNumeroOperadorSustId());
                    }
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setOperador(jDlgSustitucion.getNumeroOperadorSust());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreOperadorSust(jDlgSustitucion.getNombreOperadorSust());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorId(jDlgSustitucion.getNumeroOperadorSustId());
                    xMaestro.setValueAt(jDlgSustitucion.getNumeroOperadorSust(), fila, 10);
                    sesionVenta.getDetalle()[fila][4]=jDlgSustitucion.getNumeroOperadorSust();
                    sesionVenta.getDetalle()[fila][5]=jDlgSustitucion.getNombreOperadorSust();
                    
                    int filaNueva = fila+1, i, j;
                    for(i=1; i<regs; i++){
                        for(j=filaNueva; j<jTblMaestro.getRowCount(); j++){
                            if(xMaestro.getValueAt(j, 10)==null) continue;
                            if(busOrig.equals(xMaestro.getValueAt(j, 10).toString())){
                                xMaestro.setValueAt(jDlgSustitucion.getNumeroOperadorSust(), j, 10);
                                sesionVenta.getDetalle()[fila][4]=jDlgSustitucion.getNumeroOperadorSust();
                                sesionVenta.getDetalle()[fila][5]=jDlgSustitucion.getNombreOperadorSust();
                                sesionVenta.getTmsMonitorCorridaV().get(j).setOperador(jDlgSustitucion.getNumeroOperadorSust());
                                sesionVenta.getTmsMonitorCorridaV().get(j).setNombreOperadorSust(jDlgSustitucion.getNombreOperadorSust());
                                sesionVenta.getTmsMonitorCorridaV().get(j).setOperadorId(jDlgSustitucion.getNumeroOperadorSustId());
                                filaNueva=j+1;
                                break;
                            }
                        }
                    }
                    jTblMaestro.setColumnSelectionInterval(10, 10);
                    jTblMaestro.setRowSelectionInterval(fila,fila);
                    jTblMaestro.requestFocusInWindow();
                break;
            case 3: xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("CANCELADA"), fila, 2); break;
        }
        return true;
    }
    
    private boolean agruparCorridas(){
        this.jPnlAgrupacion.setVisible(true);
        jPnlPSD.setMensaje(barraMensajes.getMsgComun(4));
        return true;
    }
    
    private boolean accesoUbicaciones(){
        JIFAccesoUbicaciones jDlgAccesoUbicaciones = new JIFAccesoUbicaciones(sesionVenta);
        jDlgAccesoUbicaciones.setVisible(true);
        return true;
    }
    
    private boolean estadosOperador(){
        JIFEstadosOperador jDlgEstadosOperador = new JIFEstadosOperador(sesionVenta);
        jDlgEstadosOperador.setVisible(true);
        return true;
    }
    
    private boolean crearCorridasExtra(){
        sesionVenta.getDBLinkClaveOrigen(jCboTerminal.getSelectedItem().toString());
        if(jCboTerminal.getSelectedItem().toString().equals(sesionVenta.getTerminalNombre())) transaccionRemota = false;
        else transaccionRemota = true;
        /*if(transaccionRemota){
            DialogoAceptar = new JDlgAceptar("Crear Corrida Extra.", "Esta transaccion solo puede hacerse localmente.", Color.RED);
            return false;
        }*/
        //System.out.println("TERMINAL "+sesionVenta.esquemaPropio);
        if(!sesionVenta.RutasV(sesionVenta.getEsquemaPropio())) return false;
        JDlgCorridasExtras jDlgCorridasExtras = new JDlgCorridasExtras(sesionVenta);
        jDlgCorridasExtras.setVisible(true);
        return true;
    }
    
    private boolean rolCadena(){
        if(1==1) return true;
        if(jCboTerminal.getSelectedItem().toString().equals(sesionVenta.getTerminalNombre())) transaccionRemota = false;
        else transaccionRemota = true;
        if(transaccionRemota){
            DialogoAceptar = new JDlgAceptar("Sustituir Horarios.", "Esta transaccion solo puede hacerse localmente.", Color.RED);
            return false;
        }
        sesionVenta.getDBLinkClaveOrigen(jCboTerminal.getSelectedItem().toString());
        JDlgACHorarios jDlgRolCadena = new JDlgACHorarios(sesionVenta);
        jDlgRolCadena.setVisible(true);
        return true;
    }
    
    private void CerrarIFrame(){
        DialogoSiNo = new JDlgSiNo("!Confirme¡", "¿Realmente desea salir del programa?");
        if(!DialogoSiNo.getResultado()) return;
        jPnlPSD.dispose();
        return;
    }

    private void EjecutaAgrupacion() {
        if(jTblA.getRowCount()==0){
            DialogoAceptar = new JDlgAceptar("¡No ha seleccionado una corrida original!", "<html>Primero debe seleccionar una corrida original<br>y posteriormente, seleccione la corrida que la agrupara.</html>", Color.RED);
            return;
        }
        
        if(jTblB.getRowCount()==0){
            DialogoAceptar = new JDlgAceptar("¡No ha seleccionado una corrida que agrupe!", "Seleccione la corrida que agrupara a la corrida original.", Color.RED);
            return;
        }
        
        ocupacionA = sesionVenta.getOcupacion(this.filaA);
        if(ocupacionA==-1){
            DialogoAceptar = new JDlgAceptar("¡Esta corrida no es valida!", "Posiblemente la corrida ya no esta abierta.", Color.RED);
            return;
        }
        
        int ocupacion = sesionVenta.getOcupacion(this.filaB);
        if(ocupacion==-1){
            DialogoAceptar = new JDlgAceptar("¡Esta corrida no es valida!", "Posiblemente la corrida ya no esta abierta", Color.RED);
            return;
        }
        int capacidadAsientos = sesionVenta.getCapacidadAsientos(this.filaB);                    
        if((capacidadAsientos-ocupacion)<ocupacionA){
            xB.setValueAt(ocupacion, this.fila, 9);
            DialogoAceptar = new JDlgAceptar("¡La ocupacion de la corrida destino ha cambiado!", "<html>La ocupacion es inferior a la ocupacion de la corrida original.<br>Seleccione otra corrida o cancele.</html>", Color.RED);
            return;
        }
        int error=0;
        error=sesionVenta.CorridasEnProceso(filaA, filaB);
        if(error!=0){
            if(error==1)
                DialogoAceptar = new JDlgAceptar("¡No fue posible cambiar el estado de la corrida original!", "Contacte al administrador del sistema.", Color.RED);
            else
                DialogoAceptar = new JDlgAceptar("¡No fue posible cambiar el estado de la corrida destino!", "Contacte al administrador del sistema.", Color.RED);
        }
        else{
            error=0;
            
            if(error!=0){
                if(error==1)
                    DialogoAceptar = new JDlgAceptar("¡No fue posible transferir los boletos vendidos!", "Contacte al administrador del sistema.", Color.RED);
                else
                    DialogoAceptar = new JDlgAceptar("¡No fue posible trasnferir las reservaciones!", "Contacte al administrador del sistema.", Color.RED);
            }
            else{
                error=sesionVenta.EstadoCorridaAgrupada(filaA, filaB);
                if(error!=0){
                    if(error==1)
                        DialogoAceptar = new JDlgAceptar("¡No fue posible cambiar el estado de la corrida original!", "<html>Posiblemente quedo en proceso.<br>Contacte al administrador del sistema.</html>", Color.RED);
                    else
                        DialogoAceptar = new JDlgAceptar("¡No fue posible cambiar el estado de la corrida destino!", "<html>Posiblemente quedo en proceso.<br>Contacte al administrador del sistema.</html>", Color.RED);
                }
                else
                    DialogoAceptar = new JDlgAceptar("¡Agrupacion terminada correctamente!", "Si desea puede consultar las corridas en el monitor.", Color.RED);
            }
        }
        xA.setDataVector(null, encA);
        AnchoColumnas();
        xB.setDataVector(null, encA);
        AnchoColumnas();
        jPnlAgrupacion.setVisible(false);
        if(error==0){
            xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("AGRUPADA"), filaA,2);
            xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("ABIERTA"), filaB,2);
        }
        jPnlPSD.setMensaje(barraMensajes.getMsgComun(1));
    }

    private void SeleccionaA() {
        if(xMaestro.getValueAt(this.fila, 2).toString().equals("A")){
            String r = sesionVenta.estadoTarjetaViaje(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
            if(!r.equals("SIN TARJETA") && !r.equals("CANCELADA")){
                if(r.equals("ERROR"))
                    DialogoAceptar = new JDlgAceptar("¡No existe una conexion a la base de datos central!", "Contacte al administrador del sistema.", Color.RED);
                else{
                    if(r.equals("RECAUDADA"))
                        DialogoAceptar = new JDlgAceptar("¡Corrida invalida!", "La tarjeta de viaje para esta corrida esta recaudada.", Color.RED);
                    else
                        DialogoAceptar = new JDlgAceptar("¡Corrida invalida!", "La tarjeta de viaje para esta corrida esta abierta.", Color.RED);
                }   
                return;
            }
            ocupacionA = sesionVenta.getOcupacion(this.fila);
            if(ocupacionA==-1){
                DialogoAceptar = new JDlgAceptar("¡Esta corrida no es valida!", "Posiblemente la corrida ya no esta abierta", Color.RED);
                return;
            }
            
            Object[][] objA = new Object[1][10];
            for(int i=0; i<9; i++)
                objA[0][i] = xMaestro.getValueAt(this.fila, i);
            objA[0][9] = ocupacionA;
            xA.setDataVector(objA, encA);
            AnchoColumnas();
            filaA = this.fila;
        }
        else
            DialogoAceptar = new JDlgAceptar("¡Corrida invalida!", "Seleccione una corrida abierta.", Color.RED);
    }

    private void SeleccionaB() {
        if(jTblA.getRowCount()==0){
            DialogoAceptar = new JDlgAceptar("¡No ha seleccionado una corrida original!", "<html>Primero debe seleccionar una corrida original<br>y posteriormente, seleccione la corrida que la agrupara.</html>", Color.RED);
            return;
        }
        
        if(filaA==this.fila){
            DialogoAceptar = new JDlgAceptar("¡Corrida invalida!", "Seleccione una corrida diferente a la original.", Color.RED);
            return;
        }
        
        if(!xA.getValueAt(0, 3).toString().equals(xMaestro.getValueAt(this.fila, 3).toString())){
            DialogoAceptar = new JDlgAceptar("¡Servicios diferentes!", "Seleccione una corrida del mismo servicio.", Color.RED);
            return;
        }
        
        if(xMaestro.getValueAt(this.fila, 2).toString().equals("A")){
            String r = sesionVenta.estadoTarjetaViaje(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
            if(!r.equals("SIN TARJETA") && !r.equals("CANCELADA")){
                if(r.equals("ERROR"))
                    DialogoAceptar = new JDlgAceptar("¡No existe una conexion a la base de datos central!", "Contacte al administrador del sistema.", Color.RED);
                else{
                    if(r.equals("RECAUDADA"))
                        DialogoAceptar = new JDlgAceptar("¡Corrida invalida!", "La tarjeta de viaje para esta corrida esta recaudada.", Color.RED);
                    else
                        DialogoAceptar = new JDlgAceptar("¡Corrida invalida!", "La tarjeta de viaje para esta corrida esta abierta.", Color.RED);
                }   
                return;
            }
            int ocupacion = sesionVenta.getOcupacion(this.fila);
            if(ocupacion==-1){
                DialogoAceptar = new JDlgAceptar("¡Esta corrida no es valida!", "Posiblemente la corrida ya no esta abierta", Color.RED);
                return;
            }
            int capacidadAsientos = sesionVenta.getCapacidadAsientos(this.fila);                    
            if((capacidadAsientos-ocupacion)<ocupacionA){
                DialogoAceptar = new JDlgAceptar("¡Ocupacion inferior a la corrida original!", "<html>No es posible agrupar la corrida original en esta corrida.<br>Seleccione otra corrida o cancele.</html>", Color.RED);
                return;
            }
            
            Object[][] objB = new Object[1][10];
            for(int i=0; i<9; i++)
                objB[0][i] = xMaestro.getValueAt(this.fila, i);
            objB[0][9] = ocupacion;
            
            xB.setDataVector(objB, encA);
            AnchoColumnasB();
            filaB = this.fila;
        }
        else
            DialogoAceptar = new JDlgAceptar("¡Corrida invalida!", "Seleccione una corrida abierta.", Color.RED);
    }

    private void abrirCorrida() {
        String estadoCorrida=sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida());
        if(estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("CERRADA")) || estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("DESPACHADA"))){
            int r=-1;
            if(estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("DESPACHADA"))){
                //VAGL13/02/2009
                //r=sesionVenta.esCorridaDePaso(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida());
                r = 1;
                if(r!=1){
                    if(r==-1)
                        DialogoAceptar = new JDlgAceptar("¡No existe una conexion a la base de datos!", "Contacte al administrador del sistema.", Color.RED);
                    else
                        DialogoAceptar = new JDlgAceptar("¡Imposible abrir la corrida!", "<html>Esta corrida ya fue despachada<br>y no es de paso.</html>", Color.RED);
                    return;
                }
            }
            r = sesionVenta.tarjViajeEstaRecaudada(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
            if(r!=1){
                if(r==-1)
                    DialogoAceptar = new JDlgAceptar("¡No existe una conexion a la base de datos!", "Contacte al administrador del sistema.", Color.RED);
                else
                    DialogoAceptar = new JDlgAceptar("¡Imposible abrir la corrida!", "La tarjeta de viaje para esta corrida esta recaudada.", Color.RED);
                return;
            }
            if(!sesionVenta.abreCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId()))
                DialogoAceptar = new JDlgAceptar("¡No fue posible abrir la corrida!", "Contacte al administrador del sistema.", Color.RED);
            else{
                xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("ABIERTA"), fila,2);
                sesionVenta.getTmsMonitorCorridaV().get(fila).setEstadoCorrida(xMaestro.getValueAt(fila,2).toString());
                DialogoAceptar = new JDlgAceptar("¡Corrida abierta exitosamente!", "El estado de la corrida es Abierta.", colorDialogoActivo);
            }
        }
        else
            DialogoAceptar = new JDlgAceptar("¡Imposible abrir la corrida!", "La corrida debe estar Cerrada o Despachada.", Color.RED);
    }

    private void verDetalle(){
        JDlgDetalleCorrida jDlgDetalleCorrida = new JDlgDetalleCorrida (sesionVenta.getDetalle(), fila, sesionVenta.getMaestro()[fila][0].toString());
        jDlgDetalleCorrida.setVisible(true);
    }

    private void confrimarViaje(){
        if(!sesionVenta.getMaestro()[fila][2].toString().equals("D"))
        {
            DialogoAceptar = new JDlgAceptar("¡Imposible abrir la confirmacion de corrida!", "La corrida debe estar Despachada.", Color.RED);
          return;
        }

        Object[][] pObject =  sesionVenta.getDetalle();

        if(pObject[fila][8]==null)
        {
            DialogoAceptar = new JDlgAceptar("¡Imposible abrir la confirmacion de corrida!", "No se encontro Tarjeta de Viaje.", Color.RED);
          return;
        }
        /*Vector datosIniciales = new Vector();
        datosIniciales.add(6);
        datosIniciales.add("8485");//9092 //8485
        datosIniciales.add("Otilio Cruz Leon");
        datosIniciales.add(150211292);
        datosIniciales.add(7);
        datosIniciales.add("192.168.16.13");
        datosIniciales.add(3700);*/
        JdlgConfirmacionViajes vjs = new JdlgConfirmacionViajes(this.datosIniciales,pObject[fila][8].toString(),jCboTerminal.getSelectedItem().toString());
        vjs.setLocationRelativeTo(this);
        vjs.setVisible(true);
    }
    
    private void cerrarCorrida() {
        if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))){
            DialogoAceptar = new JDlgAceptar("¡Imposible cerrar la corrida!", "La corrida debe estar abierta.", Color.RED);
            return;
        }
        String r = sesionVenta.estadoTarjetaViaje(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
        if(!r.equals("SIN TARJETA") && !r.equals("CANCELADA")){
            if(r.equals("ERROR"))
                DialogoAceptar = new JDlgAceptar("¡No existe una conexion a la base de datos central!", "Contacte al administrador del sistema.", Color.RED);
            else{
                if(r.equals("RECAUDADA"))
                    DialogoAceptar = new JDlgAceptar("¡Imposible cerrar la corrida!", "La tarjeta de viaje para esta corrida esta recaudada.", Color.RED);
                else
                    DialogoAceptar = new JDlgAceptar("¡Imposible cerrar la corrida!", "La tarjeta de viaje para esta corrida esta abierta.", Color.RED);
            }   
            return;
        }
        int cupo = sesionVenta.getOcupacion(fila);
        if(cupo==-1){
            DialogoAceptar = new JDlgAceptar("¡No fue posible cerrar la corrida!", "Contacte al administrador del sistema.", Color.RED);
            return;
        }
        if(cupo==0){
            if(!sesionVenta.cierraCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId()))
                DialogoAceptar = new JDlgAceptar("¡No fue posible cerrar la corrida!", "Contacte al administrador del sistema.", Color.RED);
            else{
                xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("CERRADA"), fila,2);
                sesionVenta.getTmsMonitorCorridaV().get(fila).setEstadoCorrida(xMaestro.getValueAt(fila,2).toString());
                DialogoAceptar = new JDlgAceptar("¡Corrida cerrada exitosamente!", "El estado de la corrida es Cerrada.", colorDialogoActivo);
            }
        }
        else
            DialogoAceptar = new JDlgAceptar("¡Imposible cerrar la corrida!", "Corrida con ocupacion vigente.", colorDialogoActivo);
    }
    
    private void borrarCorrida() {
        String estadoCorrida=sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida());
        if(estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("AGRUPADA"))){
            DialogoAceptar = new JDlgAceptar("¡Imposible borrar la corrida!", "La corrida no debe estar agrupada.", Color.RED);
            return;
        }
        if(estadoCorrida.equals("")){
            DialogoAceptar = new JDlgAceptar("¡Imposible borrar la corrida!", "La corrida no existe en Corridas_Venta_Tbl.", Color.RED);
            return;
        }
        /*int r = sesionVenta.tarjViajeEstaRecaudada(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
        if(r!=1){
            if(r==-1)
                DialogoAceptar = new JDlgAceptar("¡No existe una conexion a la base de datos central!", "Contacte al administrador del sistema.", Color.RED);
            else
                DialogoAceptar = new JDlgAceptar("¡Imposible borrar la corrida!", "La tarjeta de viaje para esta corrida esta recaudada.", Color.RED);
            return;
        }*/
        String r = sesionVenta.estadoTarjetaViaje(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
        if(!r.equals("SIN TARJETA") && !r.equals("CANCELADA")){
            if(r.equals("ERROR"))
                DialogoAceptar = new JDlgAceptar("¡No existe una conexion a la base de datos central!", "Contacte al administrador del sistema.", Color.RED);
            else{
                if(r.equals("RECAUDADA"))
                    DialogoAceptar = new JDlgAceptar("¡Imposible borrar la corrida!", "La tarjeta de viaje para esta corrida esta recaudada.", Color.RED);
                else
                    DialogoAceptar = new JDlgAceptar("¡Imposible borrar la corrida!", "La tarjeta de viaje para esta corrida esta abierta.", Color.RED);
            }   
            return;
        }
        int cupo = sesionVenta.getOcupacion(fila);
        if(cupo==-1){
            DialogoAceptar = new JDlgAceptar("¡No fue posible borrar la corrida!", "<html>Error al calcular la ocupacion de la corrida<br>Contacte al administrador del sistema.</html>", Color.RED);
            return;
        }
        if(cupo==0){
            DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea borrar la corrida seleccionada?");
            if(!DialogoSiNo.getResultado()) return;
            long lCorridaId=sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId();
            if(!sesionVenta.borraCorrida(lCorridaId))
                DialogoAceptar = new JDlgAceptar("¡No fue posible borrar la corrida!", "Contacte al administrador del sistema.", Color.RED);
            else{
                xMaestro.removeRow(fila);
                sesionVenta.getTmsMonitorCorridaV().remove(fila);
                /*if(!sesionVenta.borraCorridaCentral(lCorridaId))
                    DialogoAceptar = new JDlgAceptar("¡No fue posible borrar la corrida en BD Central!", "<html>Solo se borró en esta terminal.<br>Contacte al administrador del sistema.</html>", Color.RED);
                else*/
                    DialogoAceptar = new JDlgAceptar("¡Corrida borrada exitosamente!", "Presione Enter para continuar.", colorDialogoActivo);
                fila--;
                if(jTblMaestro.getRowCount()<1){
                    limpiar(); jCboServicio.requestFocusInWindow();
                    return;
                }
                if(fila<0) fila=0;
                jTblMaestro.setRowSelectionInterval(fila, fila);
                jTblMaestro.setColumnSelectionInterval(0,0);
                jTblMaestro.requestFocusInWindow();
            }
        }
        else
            DialogoAceptar = new JDlgAceptar("¡Imposible borrar la corrida!", "Corrida con ocupacion vigente.", colorDialogoActivo);
    }

    private void asigOperador() {
        if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))){
            DialogoAceptar = new JDlgAceptar("¡Imposible asignar operador!", "La corrida debe estar abierta.", Color.RED);
            return;
        }
        Object objeto = xMaestro.getValueAt(fila, 10);
        if(objeto != null && !objeto.toString().equals("")){
            DialogoAceptar = new JDlgAceptar("¡No es posible asignar operador!", "Esta corrida ya tiene asignado un operador.", Color.RED);
            return;
        }
        if(!sesionVenta.validarClaveUsuarioConFuncion("5603")){
            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5603", "Asignacion de Operador");
            dlg.setVisible(true);
            if(!dlg.getRespuesta()) return;
        }
        String fecha = xMaestro.getValueAt(fila,5).toString()+" "+xMaestro.getValueAt(fila,8).toString();
        JDlgAsignacionOperador jDlgAsigOper = new JDlgAsignacionOperador(sesionVenta, fila,fecha);
        jDlgAsigOper.setVisible(true);
        if(jDlgAsigOper.getResultado()){
                xMaestro.setValueAt(jDlgAsigOper.getNumeroOperadorAsig(), fila, 10);
                //xMaestro.setValueAt(jDlgAsigOper.getNumeroOperadorAsig(), fila, 2);
                //xDetalle.setValueAt(jDlgAsigOper.getNombreOperadorAsig(), fila, 3);
                sesionVenta.getDetalle()[fila][2]=jDlgAsigOper.getNumeroOperadorAsig();
                sesionVenta.getDetalle()[fila][3]=jDlgAsigOper.getNombreOperadorAsig();
                sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorOriginal(jDlgAsigOper.getNumeroOperadorAsig());
                sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreOperadorOriginal(jDlgAsigOper.getNombreOperadorAsig());
                sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorOriginalId(jDlgAsigOper.getNumeroOperadorAsigId());
                //sesionVenta.getTmsMonitorCorridaV().get(fila).setOperador(jDlgAsigOper.getNumeroOperadorAsig());
                //sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreOperadorSust(jDlgAsigOper.getNombreOperadorAsig());
                //sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorId(jDlgAsigOper.getNumeroOperadorAsigId());
                //xDetalle.setValueAt(jDlgAsigOper.getNumeroOperadorAsig(), fila, 4);
                //xDetalle.setValueAt(jDlgAsigOper.getNombreOperadorAsig(), fila, 5);
        }
    }

    private void asigAutobus() {
        if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))){
            DialogoAceptar = new JDlgAceptar("¡Imposible asignar autobus!", "La corrida debe estar abierta.", Color.RED);
            return;
        }
        Object objeto = xMaestro.getValueAt(fila, 9); // 1
        if(objeto != null && !objeto.toString().equals("")){
            DialogoAceptar = new JDlgAceptar("¡No es posible asignar autobus!", "Esta corrida ya tiene asignado autobus.", Color.RED);
            return;
        }
        if(!sesionVenta.validarClaveUsuarioConFuncion("5602")){
            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5602", "Asignacion de Autobus");
            dlg.setVisible(true);
            if(!dlg.getRespuesta()) return;
        }
        String fecha = xMaestro.getValueAt(fila,5).toString()+" "+xMaestro.getValueAt(fila,8).toString();
        JDlgAsignacionAutobus jDlgAsigBus = new JDlgAsignacionAutobus(sesionVenta, fila, xMaestro.getValueAt(fila, 10),fecha);
        jDlgAsigBus.setVisible(true);
        if(jDlgAsigBus.getResultado()!=0){
            xMaestro.setValueAt(jDlgAsigBus.getNumeroEconomicoAsig(), fila, 9);
            xMaestro.setValueAt(jDlgAsigBus.getEmpresa(), fila, 4);
            // que onda xMaestro.setValueAt(jDlgAsigBus.getNumeroEconomicoAsig(), fila, 0);
            //xDetalle.setValueAt(jD    lgAsigBus.getNumeroEconomicoAsig(), fila, 1);
            sesionVenta.getDetalle()[fila][0]=jDlgAsigBus.getNumeroEconomicoAsig();
            sesionVenta.getDetalle()[fila][1]=jDlgAsigBus.getNumeroEconomicoAsig();
            sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobusOriginal(jDlgAsigBus.getNumeroEconomicoAsig());
            sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobusOriginalId(jDlgAsigBus.getNumeroEconomicoAsigId());
            sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobus(jDlgAsigBus.getNumeroEconomicoAsig());
            sesionVenta.getTmsMonitorCorridaV().get(fila).setAutobusId(jDlgAsigBus.getNumeroEconomicoAsigId());
            sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreFlotilla(jDlgAsigBus.getFlotilla());
            if(!jDlgAsigBus.getFlotillaId().equals("")) sesionVenta.getTmsMonitorCorridaV().get(fila).setFlotillaId(Long.valueOf(jDlgAsigBus.getFlotillaId()));
            if(jDlgAsigBus.getResultado()==2){
                    xMaestro.setValueAt(jDlgAsigBus.getNumeroOperadorAsig(), fila, 10);
                    sesionVenta.getDetalle()[fila][2]=jDlgAsigBus.getNumeroOperadorAsig();
                    sesionVenta.getDetalle()[fila][3]=jDlgAsigBus.getNombreOperadorAsig();
                    sesionVenta.getDetalle()[fila][4]=jDlgAsigBus.getNumeroOperadorAsig();//
                    sesionVenta.getDetalle()[fila][5]=jDlgAsigBus.getNombreOperadorAsig();//
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorOriginal(jDlgAsigBus.getNumeroOperadorAsig());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreOperadorOriginal(jDlgAsigBus.getNombreOperadorAsig());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorOriginalId(jDlgAsigBus.getNumeroOperadorAsigId());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setOperador(jDlgAsigBus.getNumeroOperadorAsig());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setNombreOperadorSust(jDlgAsigBus.getNombreOperadorAsig());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setOperadorId(jDlgAsigBus.getNumeroOperadorAsigId());
            }
        }
    }

    private void ActualizacionesVarias() {
        if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))){
            DialogoAceptar = new JDlgAceptar("¡No es posible actualizar corrida!", "La corrida debe estar abierta.", Color.RED);
            return;
        }
        JDlgActualizacionesVarias jDlgActualizacionesVarias = new JDlgActualizacionesVarias(sesionVenta, fila);
        jDlgActualizacionesVarias.setVisible(true);
        if(!jDlgActualizacionesVarias.isQueOnda()) return;
        if(jDlgActualizacionesVarias.getCampo().equals("Plantilla")){
            ;
        }
        else{
            if(jDlgActualizacionesVarias.getCampo().equals("Empresa")){
                xMaestro.setValueAt(jDlgActualizacionesVarias.getNuevoValor(), fila, 4);
                sesionVenta.getTmsMonitorCorridaV().get(fila).setEmpresaId(jDlgActualizacionesVarias.getNuevoValorId());
                sesionVenta.getTmsMonitorCorridaV().get(fila).setEmpresa(jDlgActualizacionesVarias.getNuevoValor());
            }
            if(jDlgActualizacionesVarias.getCampo().equals("Ruta")){
               // xMaestro.setValueAt(jDlgActualizacionesVarias.getNuevoValor(), fila, 4);
                sesionVenta.getTmsMonitorCorridaV().get(fila).setRutaId(jDlgActualizacionesVarias.getNuevoValorId());
                //sesionVenta.getTmsMonitorCorridaV().get(fila).set(jDlgActualizacionesVarias.getNuevoValor());
            }
        }
    }

    // transacciones por lotes
    private void cerrarCorridaPorLotes() {
        if(!sesionVenta.abreSocketAS()) return;
        
        if(jTblMaestro.getRowCount()>limProc)
            DialogoAceptar = new JDlgAceptar("Aviso.", "<html>Es posible que esta transaccion por lotes<br>tarde unos segundos mas.</html>", Color.RED);
        
        String loteCorridasIds="";
        int contador=0;
        int cupo;
        Vector vIndices = new Vector();
        String r;
        for(int i=0; i<jTblMaestro.getRowCount(); i++){
            fila=i;
            if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))){
                continue;
            }
            r = sesionVenta.estadoTarjetaViaje(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
            if(!r.equals("SIN TARJETA") && !r.equals("CANCELADA")) continue;
            cupo = sesionVenta.getOcupacion(fila);
            if(cupo==0){
                contador++;
                loteCorridasIds = loteCorridasIds + sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId() + ", ";
                vIndices.add(fila);
            }
        }
        if(contador==0){
            DialogoAceptar = new JDlgAceptar("¡No es posible cerrar corridas!", "<html>Las corridas seleccionadas no cumplen todos<br>los requisito para poder ser cerradas.</html>", Color.RED);
            return;
        }
        
        if(contador>999){
            DialogoAceptar = new JDlgAceptar("Restriccion del sistema.", "<html>Se excedio el numero maximo (1000) de corridas<br>en un lote. Realice otra consulta.</html>", Color.RED);
            return;
        }
        
        DialogoSiNo = new JDlgSiNo("¡Confirme!", "<html>Existen "+contador+" corridas que puede cerrar de un total de "+jTblMaestro.getRowCount()+",<br>¿Desea realizar la transaccion?</html>");
        if(!DialogoSiNo.getResultado()) return;
        loteCorridasIds = loteCorridasIds.substring(0,loteCorridasIds.length()-2);
        if(!sesionVenta.cierraCorridaPorLotes(loteCorridasIds))
            DialogoAceptar = new JDlgAceptar("¡No fue posible cerrar el lote de corridas!", "Contacte al administrador del sistema.", Color.RED);
        else{
            System.out.println("fila2"+vIndices.size());
            for(int i=0; i<vIndices.size(); i++){
                contador = Integer.valueOf(vIndices.get(i).toString());
                xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("CERRADA"), contador,2);
                sesionVenta.getTmsMonitorCorridaV().get(fila).setEstadoCorrida(xMaestro.getValueAt(contador,2).toString());
            }
            DialogoAceptar = new JDlgAceptar("¡Lote de Corridas cerrado exitosamente!", "El estado de las corridas es Cerrada.", colorDialogoActivo);
        }
    }
    
    private void abrirCorridaPorLotes() {
        if(!sesionVenta.abreSocketAS()) return;
        
        if(jTblMaestro.getRowCount()>limProc)
            DialogoAceptar = new JDlgAceptar("Aviso.", "<html>Es posible que esta transaccion por lotes<br>tarde unos segundos mas.</html>", Color.RED);
        
        String loteCorridasIds="";
        int contador=0;
        int r;
        String estadoCorrida;
        Vector vIndices = new Vector();
        for(int i=0; i<jTblMaestro.getRowCount(); i++){
            fila=i;
            estadoCorrida=sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida());
            if(estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("CERRADA")) || estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("DESPACHADA")) || estadoCorrida.equals("")){
                if(estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("DESPACHADA"))){
                    //VAGL13/02/2009
                    //r=sesionVenta.esCorridaDePaso(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida());
                    r=1;
                    if(r!=1) continue;
                }
                r = sesionVenta.tarjViajeEstaRecaudada(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
                if(r!=1) continue;
                contador++;
                loteCorridasIds = loteCorridasIds + sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId() + ", ";
                vIndices.add(fila);
            }
        }
        if(contador==0){
            DialogoAceptar = new JDlgAceptar("¡No es posible abrir corridas!", "<html>Las corridas seleccionadas no cumplen todos<br>los requisito para poder ser abiertas.</html>", Color.RED);
            return;
        }
        
        if(contador>999){
            DialogoAceptar = new JDlgAceptar("Restriccion del sistema.", "<html>Se excedio el numero maximo (1000) de corridas<br>en un lote. Realice otra consulta.</html>", Color.RED);
            return;
        }
        
        DialogoSiNo = new JDlgSiNo("¡Confirme!", "<html>Existen "+contador+" corridas que puede abrir de un total de "+jTblMaestro.getRowCount()+",<br>¿Desea realizar la transaccion?</html>");
        if(!DialogoSiNo.getResultado()) return;
        loteCorridasIds = loteCorridasIds.substring(0,loteCorridasIds.length()-2);
        if(!sesionVenta.abreCorridaPorLotes(loteCorridasIds))
            DialogoAceptar = new JDlgAceptar("¡No fue posible abrir el lote de corridas!", "Contacte al administrador del sistema.", Color.RED);
        else{
            for(int i=0; i<vIndices.size(); i++){
                contador = Integer.valueOf(vIndices.get(i).toString());
                xMaestro.setValueAt(sesionVenta.getEstadoCorridaLetra("ABIERTA"), contador,2);
                sesionVenta.getTmsMonitorCorridaV().get(contador).setEstadoCorrida(xMaestro.getValueAt(contador,2).toString());
            }
            DialogoAceptar = new JDlgAceptar("¡Lote de Corridas abierto exitosamente!", "El estado de las corridas es Abierto.", colorDialogoActivo);
        }
    }
    
    private void borrarCorridaPorLote() {
        if(!sesionVenta.abreSocketAS()) return;
        
        if(jTblMaestro.getRowCount()>limProc)
            DialogoAceptar = new JDlgAceptar("Aviso.", "<html>Es posible que esta transaccion por lotes<br>tarde unos segundos mas.</html>", Color.RED);
        
        String loteCorridasIds="";
        int contador=0;
        Vector vIndices = new Vector();
        int cupo;
        String r;
        String estadoCorrida;
        for(int i=0; i<jTblMaestro.getRowCount(); i++){
            fila=i;
            estadoCorrida=sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida());
            if(estadoCorrida.equals(sesionVenta.getEstadoCorridaLetra("AGRUPADA")) || estadoCorrida.equals("")) continue;
            
            r = sesionVenta.estadoTarjetaViaje(sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId());
            if(!r.equals("SIN TARJETA") && !r.equals("CANCELADA")) continue;
                
            cupo = sesionVenta.getOcupacion(fila);
            if(cupo==0){
                contador++;
                loteCorridasIds = loteCorridasIds + sesionVenta.getTmsMonitorCorridaV().get(fila).getCorridaId() + ", ";
                vIndices.add(fila);
            }
        }
        if(contador==0){
            DialogoAceptar = new JDlgAceptar("¡No es posible borrar el lote de corridas!", "<html>Las corridas seleccionadas no cumplen todos<br>los requisito para poder ser borradas.</html>", Color.RED);
            return;
        }
        
        if(contador>999){
            DialogoAceptar = new JDlgAceptar("Restriccion del sistema.", "<html>Se excedio el numero maximo (1000) de corridas<br>en un lote. Realice otra consulta.</html>", Color.RED);
            return;
        }
        
        DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea borrar el lote de corridas?");
        if(!DialogoSiNo.getResultado()) return;
        loteCorridasIds = loteCorridasIds.substring(0,loteCorridasIds.length()-2);
        if(!sesionVenta.borraCorridaPorLotes(loteCorridasIds))
            DialogoAceptar = new JDlgAceptar("¡No fue posible borrar el lote de corridas!", "Contacte al administrador del sistema.", Color.RED);
        else{
            if(vIndices.size()==jTblMaestro.getRowCount()){
                xMaestro.setDataVector(null,maestro);
                AnchoColumnasMaestro();
            }else{
                for(int i=vIndices.size()-1; i>=0; i--){
                    contador = Integer.valueOf(vIndices.get(i).toString());
                    xMaestro.removeRow(contador);
                    sesionVenta.getTmsMonitorCorridaV().remove(contador);
                }
            }
            /*if(!sesionVenta.borraCorridaPorLotesCentral(loteCorridasIds))
                DialogoAceptar = new JDlgAceptar("¡No fue posible borrar el lote de corridas en BD Central!", "<html>Solo se borró en esta terminal.<br>Contacte al administrador del sistema.</html>", Color.RED);
            else*/
                DialogoAceptar = new JDlgAceptar("¡Lote de corridas borrado exitosamente!", "Presione Enter para continuar.", colorDialogoActivo);
            
            if(jTblMaestro.getRowCount()<1){
                limpiar(); jCboServicio.requestFocusInWindow();
                return;
            }
            fila = 0;
            jTblMaestro.setRowSelectionInterval(0, 0);
            jTblMaestro.setColumnSelectionInterval(0,0);
            jTblMaestro.requestFocusInWindow();
        }
    }

    private void ActualizacionesVariasPorLotes() {
        if(jTblMaestro.getRowCount()>limProc)
            DialogoAceptar = new JDlgAceptar("Aviso.", "<html>Es posible que esta transaccion por lotes<br>tarde unos segundos mas.</html>", Color.RED);
        
        int contador=0;
        int cupo;
        Vector vIndices = new Vector();
        
        for(int i=0; i<jTblMaestro.getRowCount(); i++){
            fila=i;
            if(!sesionVenta.consultarEstadoCorrida(sesionVenta.getTmsMonitorCorridaV().get(fila).getClaveCorrida()).equals(sesionVenta.getEstadoCorridaLetra("ABIERTA"))) continue;
            contador++;
            vIndices.add(fila);
        }
        if(contador==0){
            DialogoAceptar = new JDlgAceptar("¡No es posible modificar el lote de corridas!", "<html>Las corridas seleccionadas no cumplen todos<br>los requisito para poder ser modificadas.</html>", Color.RED);
            return;
        }
        DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Desea modificar el lote de corridas?");
        if(!DialogoSiNo.getResultado()) return;
        
        JDlgActualizacionesVarias jDlgActualizacionesVarias = new JDlgActualizacionesVarias(sesionVenta, vIndices);
        jDlgActualizacionesVarias.setVisible(true);
        if(!jDlgActualizacionesVarias.isQueOnda()) return;
        if(jDlgActualizacionesVarias.getCampo().equals("Plantilla")){
            ;
        }
        else{
            if(jDlgActualizacionesVarias.getCampo().equals("Empresa")){
                vIndices=jDlgActualizacionesVarias.getVIndices();
                for(int i=0; i<vIndices.size(); i++){
                    fila = Integer.valueOf(vIndices.get(i).toString());
                    xMaestro.setValueAt(jDlgActualizacionesVarias.getNuevoValor(), fila, 4);
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setEmpresaId(jDlgActualizacionesVarias.getNuevoValorId());
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setEmpresa(jDlgActualizacionesVarias.getNuevoValor());
                }
            }
            if(jDlgActualizacionesVarias.getCampo().equals("Ruta")){
                vIndices=jDlgActualizacionesVarias.getVIndices();
                for(int i=0; i<vIndices.size(); i++){
                    fila = Integer.valueOf(vIndices.get(i).toString());
                    //xMaestro.setValueAt(jDlgActualizacionesVarias.getNuevoValor(), fila, 4);
                    sesionVenta.getTmsMonitorCorridaV().get(fila).setRutaId(jDlgActualizacionesVarias.getNuevoValorId());
                   // sesionVenta.getTmsMonitorCorridaV().get(fila).setEmpresa(jDlgActualizacionesVarias.getNuevoValor());
                }
            }        
        }
    }
    
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado = null; }

    private boolean validaFechas() {
        if(jTxtHora.getText().length()!=0 && jTxtHora.getText().length()!=5){
            DialogoAceptar = new JDlgAceptar("¡Valor de hora incorrecta!", "Ingrese un valor como 15:30 o deje vacio el campo.", Color.RED);
            jTxtHora.selectAll();
            jTxtHora.requestFocusInWindow();
            return false;
        }
        
        if(jTxtFecha.getText().length()==0 || jTxtFecha.getText().length()==1 || jTxtFecha.getText().length()==4 || (jTxtFecha.getText().length()>6 &&  jTxtFecha.getText().length()<10)){
            DialogoAceptar = new JDlgAceptar("¡Valor de fecha incorrecta!", "Ingrese un valor como 16/09/2007.", Color.RED);
            jTxtFecha.setText("");
            jTxtFecha.requestFocusInWindow();
            return false;
        }

        if(jTxtFecha.getText().length()==2 || jTxtFecha.getText().length()==3){
            String dtStrFecha = formatoFecha.format(new Date()).substring(3);
            if(jTxtFecha.getText().length()==2) jTxtFecha.setText(jTxtFecha.getText() +"/"+dtStrFecha);
            else jTxtFecha.setText(jTxtFecha.getText()+dtStrFecha);
        }

        if(jTxtFecha.getText().length()==5 || jTxtFecha.getText().length()==6){
            String dtStrFecha = formatoFecha.format(new Date()).substring(6);
            if(jTxtFecha.getText().length()==5) jTxtFecha.setText(jTxtFecha.getText() +"/"+dtStrFecha);
            else jTxtFecha.setText(jTxtFecha.getText()+dtStrFecha);
        }
        
        if(jTxtHora2.getText().length()!=0 && jTxtHora2.getText().length()!=5){
            DialogoAceptar = new JDlgAceptar("¡Valor de hora incorrecta!", "Ingrese un valor como 15:30 o deje vacio el campo.", Color.RED);
            jTxtHora2.selectAll();
            jTxtHora2.requestFocusInWindow();
            return false;
        }
        
        if(jTxtFecha2.getText().length()==2 || jTxtFecha2.getText().length()==3){
            String dtStrFecha = formatoFecha.format(new Date()).substring(3);
            if(jTxtFecha2.getText().length()==2) jTxtFecha2.setText(jTxtFecha2.getText() +"/"+dtStrFecha);
            else jTxtFecha2.setText(jTxtFecha2.getText()+dtStrFecha);
        }

        if(jTxtFecha2.getText().length()==5 || jTxtFecha2.getText().length()==6){
            String dtStrFecha = formatoFecha.format(new Date()).substring(6);
            if(jTxtFecha2.getText().length()==5) jTxtFecha2.setText(jTxtFecha2.getText() +"/"+dtStrFecha);
            else jTxtFecha2.setText(jTxtFecha2.getText()+dtStrFecha);
        }
        return true;
    }
    
    public class lineasColorRenderer extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent (JTable table,Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        if (isSelected) {
            cell.setBackground(new Color(0,175,255));
            cell.setForeground(Color.WHITE);
        } 
        else {
            cell.setForeground(Color.BLACK);
            if (row % 2 == 0) {
                cell.setBackground(Azul);
            }
            else {
                cell.setBackground(Color.WHITE);
            }
        }
        if(column == 7)
            setHorizontalAlignment(JTextField.CENTER);
        else
            setHorizontalAlignment(JTextField.LEFT);
        return cell;
    }}
 
    
    public void cambiarHorarios()
     {
        JDlgCH jdch = new JDlgCH(jCboServicio.getModel(), jCboEmpresa.getModel(), jCboTerminal.getModel(), jCboDestino.getModel(), this.sesionVenta);
        jdch.setVisible(true);
     }
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCboDestino;
    private javax.swing.JComboBox jCboEmpresa;
    private javax.swing.JComboBox jCboServicio;
    private javax.swing.JComboBox jCboTerminal;
    private javax.swing.JCheckBox jCkbCorridaExtra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLblTerminal;
    private javax.swing.JLabel jLblTerminal1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnlAgrupacion;
    private javax.swing.JScrollPane jScpA;
    private javax.swing.JScrollPane jScpB;
    private javax.swing.JScrollPane jScpDetalle;
    private javax.swing.JTable jTblA;
    private javax.swing.JTable jTblB;
    private javax.swing.JTable jTblMaestro;
    private javax.swing.JTextField jTxtAutobus;
    private javax.swing.JTextField jTxtFecha;
    private javax.swing.JTextField jTxtFecha2;
    private javax.swing.JTextField jTxtHora;
    private javax.swing.JTextField jTxtHora2;
    private javax.swing.JTextField jTxtOperador;
    // End of variables declaration//GEN-END:variables
    private SesionVenta sesionVenta=null;
    
    private int txBusq = 0;
    private int txResul = 1;
    private int txRegistrar = 2;
    private int txModificarAlta = 3;
    private int txEliminar = 4;
    private int txModificar = 5;
    
    private Object[][] maestroDatos = {{"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora"},
            {"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora"},
            {"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora"},
            {"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora"}};
    private Object[] maestro = {"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora", "Autobus", "Operador"};
    private DefaultTableModel xMaestro = new DefaultTableModel(null, maestro){
            public boolean isCellEditable(int row, int col) {
                return false;
            }   
    };
    
    private Object[] encA = {"Corrida", "T", "E", "Servicio", "Empresa", "Fecha", "Origen", "Destino", "Hora", "Ocup"};
    private DefaultTableModel xA = new DefaultTableModel(null, encA){
            public boolean isCellEditable(int row, int col) {
                return false;
            }   
    };
    private DefaultTableModel xB = new DefaultTableModel(null, encA){
            public boolean isCellEditable(int row, int col) {
                return false;
            }   
    };
    
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
    
    private int fila=-1;
    private BarraMensajes barraMensajes=new BarraMensajes();
    private int transaccion;
    
    private JDlgAceptar DialogoAceptar;
    private JDlgSiNo DialogoSiNo;
    private JDlgSiNoCancelar DialogoSiNoCancelar;
    
    private Object[][] datos;
    private int numeroComponente=-2;
    private Color colorDialogoActivo = new Color(0,83,255);
    private JIFPSD jPnlPSD;
    private JViewport jVwpMaestro;
    private int ocupacionA;
    private int filaA, filaB;
    private boolean transaccionRemota = false;
    private KeyEvent eventoTeclado;
    private JIFPSD JIF = null;
    private int limProc;
    private Color Azul = new Color(217,229,241);
    private Color xorColorFondo = new Color(156, 179, 201);
    private Vector datosIniciales;
}