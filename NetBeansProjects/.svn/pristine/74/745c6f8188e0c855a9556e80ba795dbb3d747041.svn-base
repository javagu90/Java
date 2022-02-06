/*
 * JDlgCorridasExtras.java
 *
 * Created on 15 de agosto de 2007, 12:16 PM
 */

package tms_corridaextra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_TextFields.JCuantityTextField;
import tms_TextFields.JDateTextField;
import tms_TextFields.JHourTextField;
import tms_psd.*;
import tms_psd.util.BarraMensajes;
import tms_psd.util.JDlgAceptar;
import tms_psd.util.JDlgSiNo;
import tms_psd.util.JDlgSiNoCancelar;
import tms_psd.util.JNumberTextFieldMaxDig;
import tms_psd.util.TmsCorridasVentaV;

/**
 *
 * @author  ocruz
 */
public class JDlgCorridasExtras extends JDialog implements KeyListener{
    
    /**
     * Creates new form JDlgCorridasExtras
     */
    public JDlgCorridasExtras(SesionVenta pSesionVenta) {
        this.sesionVenta = pSesionVenta;
        tmsCorridasExtraV=null;
        initComponents();
        this.setModal(true);
        String hoyEs = formatoFecha.format(new Date());
        jTxtFecha.setText(hoyEs);
        MesAnho=hoyEs.substring(3);
        Anho=hoyEs.substring(6);
        centrarJDialog();
        inhabilitarF10();
    }
    
    private void inhabilitarF10() {
        this.jCboServicio.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboRuta.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboOrigenTramo.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboDestinoTramo.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboEmpresa.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFecha.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtHora.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboEstado.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCboTipo.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtEstudiante.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtProfesor.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtSenectud.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtCortesia.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtSueldoOperador.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtContrato.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtOrden.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtAnticipo.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblDetalle.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
    }
    
    private void AnchoColumnas(){
        TableColumn column = null;
        int anchoContenedor = jScpDetalle.getWidth();
        for (int i = 0; i < 7; i++) {
            column = jTblDetalle.getColumnModel().getColumn(i);
            switch(i){
                case 0:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 1:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.07)); break;
                case 2:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 3:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 4:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.23)); break;
                case 5:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.11)); break;
                case 6:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.11)); break;
            }
            column.setResizable(false);
        }
    }
    
    /* This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScpDetalle = new javax.swing.JScrollPane();
        jTblDetalle = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLblServicio = new javax.swing.JLabel();
        mCboServicios = new DefaultComboBoxModel(sesionVenta.getVectorServicio());
        jCboServicio = new javax.swing.JComboBox(mCboServicios);
        jLblRuta = new javax.swing.JLabel();
        mCboRutas = new DefaultComboBoxModel(sesionVenta.getVectorRutas());
        jCboRuta = new javax.swing.JComboBox(mCboRutas);
        jLabel3 = new javax.swing.JLabel();
        jTxtRutaNombre = new javax.swing.JTextField();
        jLblTramoOrigen = new javax.swing.JLabel();
        jCboOrigenTramo = new javax.swing.JComboBox();
        jLblTramoDestino = new javax.swing.JLabel();
        jCboDestinoTramo = new javax.swing.JComboBox();
        jLblEmpresa = new javax.swing.JLabel();
        mCboEmpresas = new DefaultComboBoxModel(sesionVenta.getVectorEmpresa());
        jCboEmpresa = new javax.swing.JComboBox(mCboEmpresas);
        jLblHorario = new javax.swing.JLabel();
        jTxtFecha = new JDateTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTxtEstudiante = new JNumberTextFieldMaxDig(2);
        jLabel9 = new javax.swing.JLabel();
        jTxtProfesor = new JNumberTextFieldMaxDig(2);
        jLabel10 = new javax.swing.JLabel();
        jTxtSenectud = new JNumberTextFieldMaxDig(2);
        jLabel11 = new javax.swing.JLabel();
        jTxtCortesia = new JNumberTextFieldMaxDig(2);
        jLabel1 = new javax.swing.JLabel();
        jTxtHora = new JHourTextField();
        jLabel2 = new javax.swing.JLabel();
        jCboEstado = new javax.swing.JComboBox();
        jPnlDatosAdicionales = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTxtSueldoOperador = new JCuantityTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtContrato = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTxtOrden = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTxtAnticipo = new JCuantityTextField();
        jLabel7 = new javax.swing.JLabel();
        jCboTipo = new javax.swing.JComboBox();
        jLblBarraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Creacion de Corridas Extra");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jScpDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray));
        jScpDetalle.setFocusTraversalKeysEnabled(false);
        jTblDetalle.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblDetalle.setModel(xListado);
        jTblDetalle.setName("-1");
        jTblDetalle.addKeyListener(this);
        jTblDetalle.setFocusTraversalKeysEnabled(false);
        jTblDetalle.getTableHeader().setReorderingAllowed(false);
        jScpDetalle.setViewportView(jTblDetalle);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corrida Extra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray));
        jLblServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblServicio.setText("Aplica a Servicio:");

        jCboServicio.setBackground(new java.awt.Color(255, 255, 204));
        jCboServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboServicio.setName("1");
        jCboServicio.setFocusTraversalKeysEnabled(false);
        jCboServicio.addKeyListener(this);

        jLblRuta.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblRuta.setText("Ruta:");

        jCboRuta.setBackground(new java.awt.Color(255, 255, 204));
        jCboRuta.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboRuta.setName("2");
        jCboRuta.setSelectedIndex(0);
        jCboRuta.setFocusTraversalKeysEnabled(false);
        jCboRuta.addKeyListener(this);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Nombre Ruta:");

        jTxtRutaNombre.setEditable(false);
        jTxtRutaNombre.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtRutaNombre.setFocusable(false);
        jTxtRutaNombre.setFocusTraversalKeysEnabled(false);
        jTxtRutaNombre.addKeyListener(this);

        jLblTramoOrigen.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblTramoOrigen.setText("Origen:");

        jCboOrigenTramo.setBackground(new java.awt.Color(255, 255, 204));
        jCboOrigenTramo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboOrigenTramo.setName("3");
        jCboOrigenTramo.setFocusTraversalKeysEnabled(false);
        jCboOrigenTramo.addKeyListener(this);

        jLblTramoDestino.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblTramoDestino.setText("Destino:");

        jCboDestinoTramo.setBackground(new java.awt.Color(255, 255, 204));
        jCboDestinoTramo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboDestinoTramo.setName("4");
        jCboDestinoTramo.setFocusTraversalKeysEnabled(false);
        jCboDestinoTramo.addKeyListener(this);

        jLblEmpresa.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblEmpresa.setText("Empresa:");

        jCboEmpresa.setBackground(new java.awt.Color(255, 255, 204));
        jCboEmpresa.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboEmpresa.setName("5");
        jCboEmpresa.setFocusTraversalKeysEnabled(false);
        if(sesionVenta.empresaPrincipal.equals("SE"))
        jCboEmpresa.setSelectedIndex(0);
        else
        jCboEmpresa.setSelectedItem(sesionVenta.empresaPrincipal);
        jCboEmpresa.addKeyListener(this);

        jLblHorario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblHorario.setText("Fecha:");

        jTxtFecha.setBackground(new java.awt.Color(255, 255, 204));
        jTxtFecha.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtFecha.setName("6");
        jTxtFecha.setFocusTraversalKeysEnabled(false);
        jTxtFecha.addKeyListener(this);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Limites Tipos de Pasajeros:");

        jLabel8.setText("Estudiantes");

        jTxtEstudiante.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtEstudiante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtEstudiante.setText("10");
        jTxtEstudiante.setName("10");
        jTxtEstudiante.setFocusTraversalKeysEnabled(false);
        jTxtEstudiante.addKeyListener(this);

        jLabel9.setText("Profesores");

        jTxtProfesor.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtProfesor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtProfesor.setText("10");
        jTxtProfesor.setName("11");
        jTxtProfesor.setFocusTraversalKeysEnabled(false);
        jTxtProfesor.addKeyListener(this);

        jLabel10.setText("Senectud");

        jTxtSenectud.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtSenectud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtSenectud.setText("10");
        jTxtSenectud.setName("12");
        jTxtSenectud.setFocusTraversalKeysEnabled(false);
        jTxtSenectud.addKeyListener(this);

        jLabel11.setText("Especial");

        jTxtCortesia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtCortesia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtCortesia.setText("10");
        jTxtCortesia.setName("13");
        jTxtCortesia.setFocusTraversalKeysEnabled(false);
        jTxtCortesia.addKeyListener(this);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Hora:");

        jTxtHora.setBackground(new java.awt.Color(255, 255, 204));
        jTxtHora.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtHora.setName("7");
        jTxtHora.setFocusTraversalKeysEnabled(false);
        jTxtHora.addKeyListener(this);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Estado Corrida:");

        jCboEstado.setBackground(new java.awt.Color(255, 255, 204));
        jCboEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ABIERTA", "CERRADA" }));
        jCboEstado.setSelectedIndex(1);
        jCboEstado.setName("8");
        jCboEstado.setFocusTraversalKeysEnabled(false);
        jCboEstado.addKeyListener(this);

        jPnlDatosAdicionales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Adicionales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.black));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Sueldo Operador:");

        jTxtSueldoOperador.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtSueldoOperador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtSueldoOperador.setName("14");
        jTxtSueldoOperador.setFocusTraversalKeysEnabled(false);
        jTxtSueldoOperador.addKeyListener(this);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Numero Contrato:");

        jTxtContrato.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtContrato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtContrato.setName("15");
        jTxtContrato.setFocusTraversalKeysEnabled(false);
        jTxtContrato.addKeyListener(this);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Numero Orden:");

        jTxtOrden.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtOrden.setName("16");
        jTxtOrden.setFocusTraversalKeysEnabled(false);
        jTxtOrden.addKeyListener(this);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Monto Anticipos:");

        jTxtAnticipo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtAnticipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtAnticipo.setName("17");
        jTxtAnticipo.setFocusTraversalKeysEnabled(false);
        jTxtAnticipo.addKeyListener(this);

        org.jdesktop.layout.GroupLayout jPnlDatosAdicionalesLayout = new org.jdesktop.layout.GroupLayout(jPnlDatosAdicionales);
        jPnlDatosAdicionales.setLayout(jPnlDatosAdicionalesLayout);
        jPnlDatosAdicionalesLayout.setHorizontalGroup(
            jPnlDatosAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlDatosAdicionalesLayout.createSequentialGroup()
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTxtSueldoOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTxtContrato, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel12)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTxtOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTxtAnticipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlDatosAdicionalesLayout.setVerticalGroup(
            jPnlDatosAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlDatosAdicionalesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel4)
                .add(jLabel5)
                .add(jTxtContrato, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel12)
                .add(jTxtOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabel13)
                .add(jTxtAnticipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jTxtSueldoOperador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Tipo Corrida:");

        jCboTipo.setBackground(new java.awt.Color(255, 255, 204));
        jCboTipo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ROL", "EXTRA" }));
        jCboTipo.setName("9");
        jCboTipo.setFocusTraversalKeysEnabled(false);
        jCboTipo.addKeyListener(this);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPnlDatosAdicionales, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel6)
                        .add(89, 89, 89)
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTxtEstudiante, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel9)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTxtProfesor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel10)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTxtSenectud, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTxtCortesia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                .add(jLblServicio)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(13, 13, 13)
                                .add(jLblRuta)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jCboRuta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(13, 13, 13)
                                .add(jLabel3))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLblTramoOrigen)
                                    .add(jLblHorario))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .add(jTxtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jLabel1))
                                    .add(jCboOrigenTramo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(12, 12, 12)
                                        .add(jLblTramoDestino))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTxtHora)))
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(14, 14, 14)
                                        .add(jLabel2)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCboEstado, 0, 109, Short.MAX_VALUE))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(4, 4, 4)
                                        .add(jCboDestinoTramo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(jLblEmpresa)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jCboEmpresa, 0, 181, Short.MAX_VALUE))
                                .add(jTxtRutaNombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel7)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jCboTipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .add(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLblServicio)
                    .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblRuta)
                    .add(jCboRuta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jTxtRutaNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLblTramoOrigen)
                    .add(jLblTramoDestino)
                    .add(jCboOrigenTramo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCboDestinoTramo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblEmpresa)
                    .add(jCboEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLblHorario)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jTxtHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCboEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7)
                    .add(jCboTipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jLabel9)
                    .add(jTxtEstudiante, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8)
                    .add(jTxtProfesor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10)
                    .add(jTxtCortesia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11)
                    .add(jTxtSenectud, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPnlDatosAdicionales, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblBarraEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLblBarraEstado.setText("<html><font color=ff0000>&#171; &#187;</font> Ingresar criterios | <font color=ff0000>ENTER</font> Buscar Ofertas | <font color=ff0000>F1</font> Nueva oferta | <font color=ff0000>F4</font> Cerrar aplicacion</html>");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblBarraEstado))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
// TODO add your handling code here:
        /*if(transaccion==txGuardado){
            DialogoAceptar = new JDlgAceptar("¡No puede cerrar la ventana!", "<html>Es importante que guarde o cancele<br>la transaccion que esta realizando</html>", Color.RED);
            return;
        }*/
        DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Realmente desea salir?");
        if(!DialogoSiNo.getResultado()) return;
        this.dispose();
        return;
    }//GEN-LAST:event_formInternalFrameClosing
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        xListado.setDataVector(null,encabezado);
        AnchoColumnas();
        transaccion=txRegistrar;
        this.strServicioAnterior="";
        this.strRutaAnterior=jCboRuta.getSelectedItem().toString();
        jCboServicio.setSelectedIndex(0);
        jLblBarraEstado.setText(barraMensajes.getMsg(1));
        jCboServicio.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    private void centrarJDialog(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e){
        if(e.getComponent().getName()==null) return;
        numeroComponente = Integer.valueOf(e.getComponent().getName());
        switch(e.getKeyCode()){
            case KeyEvent.VK_F7:
                if(numeroComponente==-1 && (transaccion==txRegistrar)){
                    if(transaccion==txRegistrar){ // modificar del JTable
                        transaccion = txModificarAlta;
                        temporal=false;
                    }
                    primerRegistro = false;
                    jLblBarraEstado.setText(barraMensajes.getMsg(4));
                    jCboRuta.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_F9:
                if(transaccion==txModificarAlta){
                    if(!temporal){
                        if(!validaDatosEntrada()) return;
                        if(!validaCupoTipos()) return;
                        if(!ActualizaLinea(false)) return;
                        transaccion = txRegistrar;
                        jLblBarraEstado.setText(barraMensajes.getMsg(2));
                    }
                    temporal=false;
                    jTblDetalle.setColumnSelectionInterval(0, 0);
                    jTblDetalle.setRowSelectionInterval(fila, fila);
                    jTblDetalle.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_F8:
                if(numeroComponente==-1 && (transaccion==txRegistrar)){
                    if(jTblDetalle.getRowCount()==0) return;
                    if(transaccion==txRegistrar){ // eliminar del JTable
                        if(e.isAltDown()){ // todo
                            fila=-1;
                            jLblBarraEstado.setText(barraMensajes.getMsg(1));
                            transaccion = txRegistrar;
                            primerRegistro=true;
                            tmsCorridasExtraV = null;
                            xListado.setDataVector(null, encabezado);
                            AnchoColumnas();
                            jCboServicio.requestFocusInWindow();
                            return;
                        }
                        tmsCorridasExtraV.remove(jTblDetalle.getSelectedRow());
                        xListado.removeRow(jTblDetalle.getSelectedRow());
                        if(jTblDetalle.getRowCount()==0){
                            fila=-1;
                            jLblBarraEstado.setText(barraMensajes.getMsg(1));
                            transaccion = txRegistrar;
                            primerRegistro=true;
                            tmsCorridasExtraV = null;
                            jCboServicio.requestFocusInWindow();
                        }
                        else{
                            jTblDetalle.setColumnSelectionInterval(0, 0);
                            jTblDetalle.setRowSelectionInterval(0, 0);
                            jTblDetalle.requestFocusInWindow();
                        }
                    }
                }
                break;
            case KeyEvent.VK_F2:
                if(transaccion==txRegistrar && jTblDetalle.getRowCount()>0){
                    jLblBarraEstado.setText(barraMensajes.getMsg(3));
                    fila=0;
                    CargaEncabezado(false);
                    jTblDetalle.setColumnSelectionInterval(0, 0);
                    jTblDetalle.setRowSelectionInterval(0, 0);
                    jTblDetalle.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if(transaccion==txRegistrar){
                    /*if(transaccion==txGuardado){
                        DialogoAceptar = new JDlgAceptar("¡No puede cerrar la ventana!", "<html>Es importante que guarde o cancele<br>la transaccion que esta realizando</html>", Color.RED);
                        return;
                    }*/
                    DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Realmente desea salir?");
                    if(!DialogoSiNo.getResultado()) return;
                    this.dispose();
                    return;
                }
                if(transaccion==txModificarAlta){
                    CargaEncabezado(temporal);
                    if(transaccion==txModificarAlta){
                        transaccion = txRegistrar;
                        jLblBarraEstado.setText(barraMensajes.getMsg(1));
                    }
                    jTblDetalle.setColumnSelectionInterval(0, 0);
                    jTblDetalle.setRowSelectionInterval(fila, fila);
                    jTblDetalle.requestFocusInWindow();
                    return;
                }
                break;
            case KeyEvent.VK_F1:
                if(transaccion!=txRegistrar) return;
                fila=-1;
                jLblBarraEstado.setText(barraMensajes.getMsg(1));
                transaccion = txRegistrar;
                primerRegistro=true;
                jCboServicio.requestFocusInWindow();
                break;
            // CASOS ESPECIALES
            case KeyEvent.VK_F5:
                if(jTblDetalle.isFocusOwner()){
                    jLblBarraEstado.setText(barraMensajes.getMsg(1));
                    jCboRuta.requestFocusInWindow();
                    return;
                }
                
                    if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                        sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                        auxCargaRelacionesServicio();
                        strServicioAnterior=jCboServicio.getSelectedItem().toString();
                    }
                    //cargaParametros();
                    
                if(!AutoCompletaFecha()) return;
                if(!validaDatosEntrada()) return;
                if(!validaHoras()) return;
                if(!validaCupoTipos()) return;
                int error = agregarLinea(1, 0);
                if(error!=0){
                    if(error==1){
                        DialogoAceptar = new JDlgAceptar("¡El valor del campo hora es incorrecto!","Ingrese el valor correcto.", Color.RED);
                        jTxtHora.requestFocusInWindow();
                    }
                    if(error==2){
                        DialogoAceptar = new JDlgAceptar("¡El valor del campo fecha es incorrecto!","Ingrese el valor correcto.", Color.RED);
                        jTxtFecha.requestFocusInWindow();
                    }
                    return;
                }
                primerRegistro=false;
                jCboRuta.requestFocusInWindow();
                break;
            case KeyEvent.VK_F6:
                if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                    sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                    auxCargaRelacionesServicio();
                    strServicioAnterior=jCboServicio.getSelectedItem().toString();
                }
                //cargaParametros();
                if(!validaDatosEntrada()) return;
                if(!validaCupoTipos()) return;
                if(jTblDetalle.isFocusOwner()) return;
                Timestamp hora = null;
                Timestamp horaX = null;
                try {
                    hora = new Timestamp(formatoFechaHora.parse(jTxtFecha.getText()+" "+jTxtHora.getText()).getTime());
                    horaX = new Timestamp(formatoHora.parse(jTxtHora.getText()).getTime());
                } catch (ParseException ex) {
                    DialogoAceptar = new JDlgAceptar("¡El valor del campo hora es incorrecto!","Ingrese el valor correcto.", Color.RED);
                    jTxtHora.requestFocusInWindow();
                    return;
                }
                JDlgGeneracionAuto genAuto = new JDlgGeneracionAuto(horaX);
                genAuto.setVisible(true);
                int salidas = genAuto.getSalidas();
                if(salidas==-1) return;
                try {
                    horaX=new Timestamp(formatoFechaHora.parse(jTxtFecha.getText()+" "+genAuto.getStrHoraFinal()).getTime());
                } catch (ParseException ex) {
                    return;
                }
                if(!validaHorasGenAuto(hora, horaX, genAuto.getFrecuencia(), salidas)) return;
                agregarLinea(salidas, genAuto.getFrecuencia());
                primerRegistro=false;
                jCboRuta.requestFocusInWindow();
                break;
            //
            case KeyEvent.VK_F10: // guardar ofertas en base de datos
                if(jTblDetalle.getRowCount()==0){
                    DialogoAceptar = new JDlgAceptar("¡Aviso!", "¡No existen corridas para ser guardadas!", Color.RED);
                    return;
                }
                    
                DialogoSiNoCancelar = new JDlgSiNoCancelar("¡Confirme!","<html>¿Desea crear la(s) corrida(s)?</html>");
                switch(DialogoSiNoCancelar.getResultado()){
                    case 3: return;
                    case 2: limpieza(); return;
                }
                if(jTblDetalle.getRowCount()>15){
                    DialogoAceptar = new JDlgAceptar("¡Importante!","<html>Es posible que el proceso de crear<br><font color=ff0000>"+
                            jTblDetalle.getRowCount()+"</font>corridas tarde varios segundos.</html>", Color.ORANGE);
                }
                String[] res = sesionVenta.guardarCorridaExtra(tmsCorridasExtraV);
                int r = Integer.valueOf(res[0]);
                if(r!=1){
                    switch(r){
                        case 0: DialogoAceptar = new JDlgAceptar("¡No fue posible crear la(s) corrida(s)!","Contacte al administrador del sistema.", Color.RED); break;
                        case -1: DialogoAceptar = new JDlgAceptar("Corrida extra "+res[1]+" no creada.","<html>Corrida creada solo en TMS_CORRIDAS_TBL.<br>Contacte al administrador del sistema.</html>", Color.RED); break;
                        case -2: DialogoAceptar = new JDlgAceptar("¡No fue posible crear la(s) corrida(s)!","<html>Fecha hora del servidor incorrecta.<br>Contacte al administrador del sistema.</html>", Color.RED); break;
                    }
                }
                else
                    DialogoAceptar = new JDlgAceptar("¡La(s) corrida(s) se creo correctamente!","Puede consultarla en el monitor de corridas..", colorDialogoActivo);
                limpieza();
                break;    
            case KeyEvent.VK_ENTER:
                if(numeroComponente==1){
                    if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                        sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                        auxCargaRelacionesServicio();
                        strServicioAnterior=jCboServicio.getSelectedItem().toString();
                    }
                    cargaParametros();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(jPnlDatosAdicionales.isVisible()){
                    switch(numeroComponente){
                        case 1: 
                            if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                                sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                                auxCargaRelacionesServicio();
                                strServicioAnterior=jCboServicio.getSelectedItem().toString();
                            }
                            cargaParametros(); jCboRuta.requestFocusInWindow(); break;
                        case 2: jCboOrigenTramo.requestFocusInWindow(); break;
                        case 3: jCboDestinoTramo.requestFocusInWindow(); break;
                        case 4: jCboEmpresa.requestFocusInWindow(); break;
                        case 5: jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow(); break;
                        case 6: if(AutoCompletaFecha()){ jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); } break;
                        case 7: jCboEstado.requestFocusInWindow(); break;
                        case 8: jCboTipo.requestFocusInWindow(); break;
                        case 9: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                        case 10: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                        case 11: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                        case 12: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                        case 13: jTxtSueldoOperador.selectAll(); jTxtSueldoOperador.requestFocusInWindow(); break;
                        case 14: jTxtContrato.selectAll(); jTxtContrato.requestFocusInWindow(); break;
                        case 15: jTxtOrden.selectAll(); jTxtOrden.requestFocusInWindow(); break;
                        case 16: jTxtAnticipo.selectAll(); jTxtAnticipo.requestFocusInWindow(); break;
                        case 17: jCboServicio.requestFocusInWindow(); break;
                    }
                    return;
                }
                switch(numeroComponente){
                    case 1: 
                        if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                            sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                            auxCargaRelacionesServicio();
                            strServicioAnterior=jCboServicio.getSelectedItem().toString();
                        }
                        cargaParametros(); jCboRuta.requestFocusInWindow(); break;
                    case 2: jCboOrigenTramo.requestFocusInWindow(); break;
                    case 3: jCboDestinoTramo.requestFocusInWindow(); break;
                    case 4: jCboEmpresa.requestFocusInWindow(); break;
                    case 5: jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow(); break;
                    case 6: if(AutoCompletaFecha()){ jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); } break;
                    case 7: jCboEstado.requestFocusInWindow(); break;
                    case 8: jCboTipo.requestFocusInWindow(); break;
                    case 9: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                    case 10: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                    case 11: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                    case 12: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                    case 13: jCboServicio.requestFocusInWindow(); break;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(jPnlDatosAdicionales.isVisible()){
                    switch(numeroComponente){
                        case 3: cargaParametros(); jCboRuta.requestFocusInWindow(); break;
                        case 4: jCboOrigenTramo.requestFocusInWindow(); break;
                        case 5: jCboDestinoTramo.requestFocusInWindow(); break;
                        case 6: if(AutoCompletaFecha()) jCboEmpresa.requestFocusInWindow(); break;
                        case 7: jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow(); break;
                        case 8: jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); break;
                        case 9: jCboEstado.requestFocusInWindow(); break;
                        case 10: jCboTipo.requestFocusInWindow(); break;
                        case 11: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                        case 12: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                        case 13: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                        case 14: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                        case 15: jTxtSueldoOperador.selectAll(); jTxtSueldoOperador.requestFocusInWindow(); break;
                        case 16: jTxtContrato.selectAll(); jTxtContrato.requestFocusInWindow(); break;
                        case 17: jTxtOrden.selectAll(); jTxtOrden.requestFocusInWindow(); break;
                        case 1: 
                            if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                                sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                                auxCargaRelacionesServicio();
                                strServicioAnterior=jCboServicio.getSelectedItem().toString();
                            }
                            jTxtAnticipo.selectAll(); jTxtAnticipo.requestFocusInWindow(); break;
                        case 2: jCboServicio.requestFocusInWindow(); break;
                    }
                    return;
                }
                switch(numeroComponente){
                    case 3: cargaParametros(); jCboRuta.requestFocusInWindow(); break;
                    case 4: jCboOrigenTramo.requestFocusInWindow(); break;
                    case 5: jCboDestinoTramo.requestFocusInWindow(); break;
                    case 6: if(AutoCompletaFecha()) jCboEmpresa.requestFocusInWindow(); break;
                    case 7: jTxtFecha.selectAll(); jTxtFecha.requestFocusInWindow(); break;
                    case 8: jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); break;
                    case 9: jCboEstado.requestFocusInWindow(); break;
                    case 10: jCboTipo.requestFocusInWindow(); break;
                    case 11: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                    case 12: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                    case 13: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                    case 1: 
                        if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                            sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                            auxCargaRelacionesServicio();
                            strServicioAnterior=jCboServicio.getSelectedItem().toString();
                        }
                        jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                    case 2: jCboServicio.requestFocusInWindow(); break;
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: case KeyEvent.VK_PAGE_UP:
                if(numeroComponente==-1)
                    fila = jTblDetalle.getSelectedRow();
                else{
                    if(numeroComponente==2 && jCboRuta.getItemCount()>0){ // ruta
                        if(strRutaAnterior.equals(jCboRuta.getSelectedItem().toString())) return;
                        jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
                        sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
                        auxCargaRelacionesRuta();
                        strRutaAnterior=jCboRuta.getSelectedItem().toString();
                    }
                    else{
                        if(numeroComponente==1){ // servicio
                            if(strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())) return;
                            sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                            auxCargaRelacionesServicio();
                            strServicioAnterior=jCboServicio.getSelectedItem().toString();
                        }
                    }
                }
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_PAGE_DOWN:
                if(numeroComponente==-1)
                    fila = jTblDetalle.getSelectedRow();
                else{
                    if(numeroComponente==2 && jCboRuta.getItemCount()>0){
                        if(strRutaAnterior.equals(jCboRuta.getSelectedItem().toString())) return;
                        jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
                        sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
                        auxCargaRelacionesRuta();
                        strRutaAnterior=jCboRuta.getSelectedItem().toString();
                    }
                    else{
                        if(numeroComponente==1){ // servicio
                            if(strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())) return;
                            sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                            auxCargaRelacionesServicio();
                            strServicioAnterior=jCboServicio.getSelectedItem().toString();
                        }
                    }
                }
                break;
        }
    }
    
    private void auxCargaRelacionesServicioModificacion(boolean datosBD){
            mCboRutas = new DefaultComboBoxModel(sesionVenta.getRutasV());
            jCboRuta.setModel(mCboRutas);
            jCboRuta.setSelectedItem(0);
            jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
            sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
            mCboOrigenes = new DefaultComboBoxModel(sesionVenta.getVectorOrigenes());
            jCboOrigenTramo.setModel(mCboOrigenes);
            mCboDestinos = new DefaultComboBoxModel(sesionVenta.getVectorDestinos());
            jCboDestinoTramo.setModel(mCboDestinos);
            jCboOrigenTramo.setSelectedItem(0);
            jCboDestinoTramo.setSelectedItem(0);
    }
    
    private void auxCargaRelacionesServicio(){
        if(sesionVenta.getRutasV().size()!=0){
            mCboRutas = new DefaultComboBoxModel(sesionVenta.getRutasV());
            jCboRuta.setModel(mCboRutas);
            jCboRuta.setSelectedIndex(0);
            jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
            sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
            mCboOrigenes = new DefaultComboBoxModel(sesionVenta.getVectorOrigenes());
            jCboOrigenTramo.setModel(mCboOrigenes);
            mCboDestinos = new DefaultComboBoxModel(sesionVenta.getVectorDestinos());
            jCboDestinoTramo.setModel(mCboDestinos);
            jCboOrigenTramo.setSelectedIndex(0);
            jCboDestinoTramo.setSelectedIndex(0);
        }
        else{
            jTxtRutaNombre.setText("");
            mCboRutas = new DefaultComboBoxModel();
            jCboRuta.setModel(mCboRutas);
            mCboOrigenes = new DefaultComboBoxModel();
            jCboOrigenTramo.setModel(mCboOrigenes);
            mCboDestinos = new DefaultComboBoxModel();
            jCboDestinoTramo.setModel(mCboDestinos);
        }
    }
    
    private void auxCargaRelacionesRuta(){
        if(sesionVenta.getVectorOrigenes().size()==0) return;
        mCboOrigenes = new DefaultComboBoxModel(sesionVenta.getVectorOrigenes());
        jCboOrigenTramo.setModel(mCboOrigenes);
        mCboDestinos = new DefaultComboBoxModel(sesionVenta.getVectorDestinos());
        jCboDestinoTramo.setModel(mCboDestinos);
        jCboOrigenTramo.setSelectedIndex(0);
        jCboDestinoTramo.setSelectedIndex(0);
    }
    
    private void limpieza(){
        xListado.setDataVector(null, encabezado);
        AnchoColumnas();
        jLblBarraEstado.setText(barraMensajes.getMsg(1));
        primerRegistro=true;
        this.jCboServicio.setSelectedIndex(0);
        tmsCorridasExtraV=null;
        transaccion = txRegistrar;
        if(sesionVenta.empresaPrincipal.equals("SE"))
            jCboEmpresa.setSelectedIndex(0);
        else
            jCboEmpresa.setSelectedItem(sesionVenta.empresaPrincipal);
        jCboServicio.requestFocusInWindow();
    }
    
    private void CargaEncabezado(boolean infoBD){
        if(fila==-1) return;
        jCboServicio.setSelectedItem(tmsCorridasExtraV.get(fila).getServicio());
        jCboRuta.setSelectedItem(tmsCorridasExtraV.get(fila).getRutaNumero());
        jTxtRutaNombre.setText(tmsCorridasExtraV.get(fila).getRutaNombre());
        auxOrigenes=new Vector();
        auxOrigenes.add(tmsCorridasExtraV.get(fila).getOrigen());
        mCboOrigenes = new DefaultComboBoxModel(auxOrigenes);
        jCboOrigenTramo.setModel(mCboOrigenes);
        auxDestinos=new Vector();
        auxDestinos.add(tmsCorridasExtraV.get(fila).getDestino());
        mCboDestinos = new DefaultComboBoxModel(auxDestinos);
        jCboDestinoTramo.setModel(mCboDestinos);
        jCboEmpresa.setSelectedItem(tmsCorridasExtraV.get(fila).getEmpresa());
        jTxtFecha.setText(formatoFecha.format(tmsCorridasExtraV.get(fila).getFechaHoraCorrida()));
        jTxtHora.setText(formatoHora.format(tmsCorridasExtraV.get(fila).getHoraCorrida()));
        jCboEstado.setSelectedItem(sesionVenta.getEstadoCorrida(tmsCorridasExtraV.get(fila).getEstadoCorrida()));
        jCboTipo.setSelectedIndex(0);
        jTxtEstudiante.setText(String.valueOf(tmsCorridasExtraV.get(fila).getEstudiantesCorrida()));
        jTxtProfesor.setText(String.valueOf(tmsCorridasExtraV.get(fila).getProfesoresCorrida()));
        jTxtSenectud.setText(String.valueOf(tmsCorridasExtraV.get(fila).getSenectudCorrida()));
        jTxtCortesia.setText(String.valueOf(tmsCorridasExtraV.get(fila).getCortesiasCorrida()));
        jTxtSueldoOperador.setText(""+(tmsCorridasExtraV.get(fila).getSueldoOperador()==null?"":tmsCorridasExtraV.get(fila).getSueldoOperador()));
        jTxtContrato.setText(tmsCorridasExtraV.get(fila).getNumeroContrato());
        jTxtOrden.setText(tmsCorridasExtraV.get(fila).getNumeroOrden());
        jTxtAnticipo.setText(""+(tmsCorridasExtraV.get(fila).getMontoAnticipos()==null?"":tmsCorridasExtraV.get(fila).getMontoAnticipos()));
    }
    
    private boolean validaHoras(){
        Timestamp hora, hi;
        try {
            hi = new Timestamp(formatoFechaHora.parse(jTxtFecha.getText()+" "+jTxtHora.getText()).getTime());
        } catch (ParseException ex) {
            return false;
        }
        if(jTblDetalle.getRowCount()==0){
            System.out.println("ENTRE 1");
            if(!sesionVenta.validaHoraModificada(hi, sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()),
                    sesionVenta.getRutaId(jCboRuta.getSelectedItem().toString()), sesionVenta.getEmpresaId(jCboEmpresa.getSelectedItem().toString()))){
                    DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", "Hay horarios que coinciden.", Color.RED);
                    return false;
            }
            return true;
        }
        for(int i=0; i<jTblDetalle.getRowCount(); i++){
            try {
                hora=new Timestamp(formatoFechaHora.parse(xListado.getValueAt(i,5).toString()+" "+xListado.getValueAt(i,6).toString()).getTime());
            } catch (ParseException ex) {
                return false;
            }
            if(hi.getTime()==hora.getTime()){
                System.out.println("ENTRE 2");
                if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                   jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                    if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                        jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                            jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                        DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", "Hay horarios que coinciden.", Color.RED);
                        return false;
                    }
                }
            }
            if(!sesionVenta.validaHoraModificada(hi, sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()),
                    sesionVenta.getRutaId(jCboRuta.getSelectedItem().toString()), sesionVenta.getEmpresaId(jCboEmpresa.getSelectedItem().toString()))){
                    System.out.println("ENTRE 3");
                    DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", "Hay horarios que coinciden.", Color.RED);
                    return false;
            }
        }
        
        return true;
    }
    
    private boolean validaHorasGenAuto(Timestamp hi, Timestamp hf, long iFrecuencia, int iSalidas){
        Timestamp hora;
        int i=0;
        for(i=0; i<jTblDetalle.getRowCount(); i++){
            try {
                hora=new Timestamp(formatoFechaHora.parse(xListado.getValueAt(i,5).toString()+" "+xListado.getValueAt(i,6).toString()).getTime());
            } catch (ParseException ex) {
                return false;
            }
            if(hi.getTime()<=hora.getTime() && hora.getTime()<=hf.getTime()){
                if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                   jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                    if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                        jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                            jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                        DialogoAceptar = new JDlgAceptar("¡No se pueden generar ofertas automaticas!", "Hay horarios que coinciden.", Color.RED);
                        return false;
                    }
                }
            }

            if(!sesionVenta.validaHoraModificada(hora, sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()),
                    sesionVenta.getRutaId(jCboRuta.getSelectedItem().toString()), sesionVenta.getEmpresaId(jCboEmpresa.getSelectedItem().toString()))){
                    DialogoAceptar = new JDlgAceptar("¡No se pueden generar ofertas automaticas!", "Hay horarios que coinciden.", Color.RED);
                    return false;
            }
        }
        if(jTblDetalle.getRowCount()==0){
            i=0;
            do{
                hora = new Timestamp(hi.getTime()+(iFrecuencia*i));
                if(!sesionVenta.validaHoraModificada(hora, sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()),
                    sesionVenta.getRutaId(jCboRuta.getSelectedItem().toString()), sesionVenta.getEmpresaId(jCboEmpresa.getSelectedItem().toString()))){
                        DialogoAceptar = new JDlgAceptar("¡No se pueden generar ofertas automaticas!", "Hay horarios que coinciden.", Color.RED);
                        return false;
                }
                i++;
            }while(i<iSalidas);
        }
        return true;
    }
    
    private boolean validaDatosEntrada(){
        Date tiempo;
        int error=0;
        if(jTxtFecha.getText().equals("")) error = -1;
            try {
                tiempo = formatoFecha.parse(jTxtFecha.getText());
            } catch (ParseException ex) {
                error=-1;
            }
        if(error==0){
            if(jTxtHora.getText().equals("") || jTxtHora.getText().length()!=5) error = -2;
            try {
                tiempo = formatoHora.parse(jTxtHora.getText());
            } catch (ParseException ex) {
                error=-2;
            }
            if(error==0){
                if(jTxtEstudiante.getText().equals("")) error = -4;
                else
                if(jTxtProfesor.getText().equals("")) error = -5;
                else
                if(jTxtSenectud.getText().equals("")) error = -6;
                else
                if(jTxtCortesia.getText().equals("")) error = -7;
                else
                if(jCboRuta.getSelectedItem()==null) error = -8;
                else
                if(jCboOrigenTramo.getSelectedItem()==null) error = -9;
                else
                if(jCboDestinoTramo.getSelectedItem()==null) error = -10;
                else
                if(jCboEmpresa.getSelectedItem()==null) error = -11;
                else
                if(jCboServicio.getSelectedItem()==null) error = -12;
                else
                if(jCboOrigenTramo.getSelectedItem().equals(jCboDestinoTramo.getSelectedItem().toString())) error = -13;
            }
        }
        
        switch(error){
            case -1:
            DialogoAceptar = new JDlgAceptar("¡Ingrese Fecha correcta!","Ejemplo 16/09/2007", Color.RED);
            jTxtHora.requestFocusInWindow();
            break;
            case -2:
            DialogoAceptar = new JDlgAceptar("¡Ingrese hora correcta!","Ejemplo 09:30", Color.RED);
            jTxtHora.requestFocusInWindow();
            break;
            case -4:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de estudiantes permitidos!","Valor requerido.", Color.RED);
            jTxtEstudiante.requestFocusInWindow();
            break;
            case -5:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de profesores permitidos!","Valor requerido.", Color.RED);
            jTxtProfesor.requestFocusInWindow();
            break;
            case -6:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de senectud permitidos!","Valor requerido.", Color.RED);
            jTxtSenectud.requestFocusInWindow();
            break;
            case -7:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de especial permitidas!","Valor requerido.", Color.RED);
            jTxtCortesia.requestFocusInWindow();
            break;
            case -8:
            DialogoAceptar = new JDlgAceptar("¡Ingrese numero de ruta!","Valor requerido.", Color.RED);
            jCboRuta.requestFocusInWindow();
            break;
            case -9:
            DialogoAceptar = new JDlgAceptar("¡Ingrese origen!","Valor requerido.", Color.RED);
            jCboOrigenTramo.requestFocusInWindow();
            break;
            case -10:
            DialogoAceptar = new JDlgAceptar("¡Ingrese destino!","Valor requerido.", Color.RED);
            jCboDestinoTramo.requestFocusInWindow();
            break;
            case -11:
            DialogoAceptar = new JDlgAceptar("¡Ingrese empresa!","Valor requerido.", Color.RED);
            jCboEmpresa.requestFocusInWindow();
            break;
            case -12:
            DialogoAceptar = new JDlgAceptar("¡Ingrese servicio!","Valor requerido.", Color.RED);
            jCboServicio.requestFocusInWindow();
            break;
            case -13:
            DialogoAceptar = new JDlgAceptar("¡Origen destino iguales!","Ingrese valores diferentes.", Color.RED);
            jCboServicio.requestFocusInWindow();
            break;
        }
        
        if(error!=0) return false;
        
        if(!sesionVenta.validaServicioEmpresa(jCboServicio.getSelectedItem().toString(), jCboRuta.getSelectedItem().toString(), jCboEmpresa.getSelectedItem().toString())){
            DialogoAceptar = new JDlgAceptar("PSD.","<html>El servicio, ruta y empresa seleccionados no corresponden<br>a una configuracion valida.</html>", Color.RED);
            return false;
        }
        
        return true;
    }
    
    private boolean ActualizaLinea(boolean infoBD){
        Timestamp hora1, hora2;
        try {
            hora1 = new Timestamp(formatoHora.parse(jTxtHora.getText()).getTime());
        } catch (ParseException ex) {
            DialogoAceptar = new JDlgAceptar("¡El valor del campo hora es incorrecto!","Ingrese un valor correcto.", Color.RED);
            jTxtHora.requestFocusInWindow();
            return false;
        }
        
        for(int i=0; i<jTblDetalle.getRowCount(); i++)
            if(i!=fila){
                try {
                    hora2=new Timestamp(formatoHora.parse(xListado.getValueAt(i,6).toString()).getTime());
                } catch (ParseException ex) {
                    return false;
                }
                if(hora1.getTime()==hora2.getTime()){
                    if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                        jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                        if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                            jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                                jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                                DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", "Hay horarios que coinciden.", Color.RED);
                                jTxtHora.requestFocusInWindow();
                                return false;
                        }
                    }
                }
            }
        actualizaEstructuras(infoBD);
        return true;
    }
    
    private void actualizaEstructuras(boolean infoBD){
        Timestamp hora = null;
        xListado.setValueAt(jCboServicio.getSelectedItem().toString(), fila, 0);
        xListado.setValueAt(jCboRuta.getSelectedItem().toString(), fila, 1);
        xListado.setValueAt(jCboOrigenTramo.getSelectedItem().toString(), fila, 2);
        xListado.setValueAt(jCboDestinoTramo.getSelectedItem().toString(), fila, 3);
        xListado.setValueAt(jCboEmpresa.getSelectedItem().toString(), fila, 4);
        xListado.setValueAt(jTxtFecha.getText(), fila, 5);
        xListado.setValueAt(jTxtHora.getText(), fila, 6);
        
        tmsCorridasExtraV.get(fila).setServicio(jCboServicio.getSelectedItem().toString());
        tmsCorridasExtraV.get(fila).setServicioId(sesionVenta.getServicioId(tmsCorridasExtraV.get(fila).getServicio()));
        tmsCorridasExtraV.get(fila).setRutaNumero(jCboRuta.getSelectedItem().toString());
        tmsCorridasExtraV.get(fila).setRutaNombre(jTxtRutaNombre.getText());
        tmsCorridasExtraV.get(fila).setRutaId(sesionVenta.getRutaId(jCboRuta.getSelectedItem().toString()));
        tmsCorridasExtraV.get(fila).setOrigen(xListado.getValueAt(fila,2).toString());
        tmsCorridasExtraV.get(fila).setOrigenId(sesionVenta.getEstadoId(tmsCorridasExtraV.get(fila).getOrigen()));
        tmsCorridasExtraV.get(fila).setDestino(xListado.getValueAt(fila,3).toString());
        tmsCorridasExtraV.get(fila).setDestinoId(sesionVenta.getEstadoId(tmsCorridasExtraV.get(fila).getDestino()));
        tmsCorridasExtraV.get(fila).setEmpresa(xListado.getValueAt(fila,4).toString());
        tmsCorridasExtraV.get(fila).setEmpresaId(sesionVenta.getEmpresaId(tmsCorridasExtraV.get(fila).getEmpresa()));
        tmsCorridasExtraV.get(fila).setEstadoCorridaId(sesionVenta.getEstadoCorridaId(jCboEstado.getSelectedItem().toString()));
        tmsCorridasExtraV.get(fila).setEstadoCorrida(sesionVenta.getEstadoCorridaLetra(jCboEstado.getSelectedItem().toString()));
        tmsCorridasExtraV.get(fila).setSenectudCorrida(Long.valueOf(jTxtSenectud.getText()));
        tmsCorridasExtraV.get(fila).setProfesoresCorrida(Long.valueOf(jTxtProfesor.getText()));
        tmsCorridasExtraV.get(fila).setEstudiantesCorrida(Long.valueOf(jTxtEstudiante.getText()));
        tmsCorridasExtraV.get(fila).setCortesiasCorrida(Long.valueOf(jTxtCortesia.getText()));
        tmsCorridasExtraV.get(fila).setNumeroContrato(jTxtContrato.getText());
        tmsCorridasExtraV.get(fila).setNumeroOrden(jTxtOrden.getText());
        tmsCorridasExtraV.get(fila).setSueldoOperador((jTxtSueldoOperador.getText().equals("") ? null : Double.valueOf(jTxtSueldoOperador.getText())));
        tmsCorridasExtraV.get(fila).setMontoAnticipos((jTxtAnticipo.getText().equals("") ? null : Double.valueOf(jTxtAnticipo.getText())));
        
        try {    
            hora = new Timestamp(formatoHora.parse(xListado.getValueAt(fila, 6).toString()).getTime());
        } catch (ParseException ex) {
            ;
        }
        tmsCorridasExtraV.get(fila).setHoraCorrida(hora);
        String strFecha = jTxtFecha.getText()+" "+formatoHora.format(hora);
        try {
            hora = new Timestamp(formatoFechaHora.parse(strFecha).getTime());
        } catch (ParseException ex) {
            ;
        }
        tmsCorridasExtraV.get(fila).setFechaHoraCorrida(hora);
    }
 
    private int agregarLinea(int noSalidas, long Frecuencia){
        TmsCorridasVentaV corridaExtra;
        Object[] filaDatos;
        Timestamp hora;
        long tiempo;
        String strFecha;
        if(tmsCorridasExtraV==null) tmsCorridasExtraV = new ArrayList<TmsCorridasVentaV>();
        for(int i=0; i<noSalidas; i++){
            filaDatos = new Object[7];
            filaDatos[0] = jCboServicio.getSelectedItem().toString();
            filaDatos[1] = jCboRuta.getSelectedItem().toString();
            filaDatos[2] = jCboOrigenTramo.getSelectedItem().toString();
            filaDatos[3] = jCboDestinoTramo.getSelectedItem().toString();
            filaDatos[4] = jCboEmpresa.getSelectedItem().toString();
            filaDatos[5] = jTxtFecha.getText();
            
            corridaExtra = new TmsCorridasVentaV();
            corridaExtra.setEmpresaId(sesionVenta.getEmpresaId(jCboEmpresa.getSelectedItem().toString()));
            corridaExtra.setEmpresa(jCboEmpresa.getSelectedItem().toString());
            corridaExtra.setServicioId(sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()));
            corridaExtra.setServicio(jCboServicio.getSelectedItem().toString());
            corridaExtra.setOrigenId(sesionVenta.getEstadoId(jCboOrigenTramo.getSelectedItem().toString()));
            corridaExtra.setOrigen(jCboOrigenTramo.getSelectedItem().toString());
            corridaExtra.setDestinoId(sesionVenta.getEstadoId(jCboDestinoTramo.getSelectedItem().toString()));
            corridaExtra.setDestino(jCboDestinoTramo.getSelectedItem().toString());
            corridaExtra.setRutaNumero(jCboRuta.getSelectedItem().toString());
            corridaExtra.setRutaNombre(jTxtRutaNombre.getText());
            corridaExtra.setRutaId(sesionVenta.getRutaId(jCboRuta.getSelectedItem().toString()));
            /*corridaExtra.setAutobusId((long)-1);
            corridaExtra.setAutobus("999999");
            corridaExtra.setAutobusOriginalId((long)-1);
            corridaExtra.setAutobusOriginal("999999");
            corridaExtra.setOperadorId((long)1178942);
            corridaExtra.setOperador("999999");
            corridaExtra.setOperadorOriginalId((long)1178942);
            corridaExtra.setOperadorOriginal("999999");
            corridaExtra.setOperadorAdicional("999999");*/
            if(jCboTipo.getSelectedItem().toString().equals("ROL"))
                corridaExtra.setTipoCorrida("N");
            else
                corridaExtra.setTipoCorrida("E");
            corridaExtra.setEstadoCorridaId(sesionVenta.getEstadoCorridaId(jCboEstado.getSelectedItem().toString()));
            corridaExtra.setEstadoCorrida(sesionVenta.getEstadoCorridaLetra(jCboEstado.getSelectedItem().toString()));
            corridaExtra.setPlantillaId(plantillaId);
            corridaExtra.setSenectudCorrida(Long.valueOf(jTxtSenectud.getText().toString()));
            corridaExtra.setEstudiantesCorrida(Long.valueOf(jTxtEstudiante.getText().toString()));
            corridaExtra.setProfesoresCorrida(Long.valueOf(jTxtProfesor.getText().toString()));
            corridaExtra.setCortesiasCorrida(Long.valueOf(jTxtCortesia.getText().toString()));
            corridaExtra.setNumeroContrato(jTxtContrato.getText());
            corridaExtra.setNumeroOrden(jTxtOrden.getText());
            corridaExtra.setSueldoOperador((jTxtSueldoOperador.getText().equals("") ? null : Double.valueOf(jTxtSueldoOperador.getText())));
            corridaExtra.setMontoAnticipos((jTxtAnticipo.getText().equals("") ? null : Double.valueOf(jTxtAnticipo.getText())));
            
            try {
                tiempo = formatoHora.parse(jTxtHora.getText()).getTime()+(Frecuencia*i);
            } catch (ParseException ex) {
                return 1;
            }
            hora = new Timestamp(tiempo);
            corridaExtra.setHoraCorrida(hora);
            
            strFecha = jTxtFecha.getText()+" "+formatoHora.format(hora);
            //System.out.println(strFecha);
            try {
                tiempo = formatoFechaHora.parse(strFecha).getTime();
            } catch (ParseException ex) {
                return 2;
            }
            hora = new Timestamp(tiempo);
            corridaExtra.setFechaHoraCorrida(hora);

            tmsCorridasExtraV.add(corridaExtra);
            
            filaDatos[6] = formatoHora.format(corridaExtra.getHoraCorrida());
            xListado.addRow(filaDatos);
            AnchoColumnas();
        }
        return 0;
    }

    private boolean validaCupoTipos(){
        //obtiene plantilla
        long[] z = sesionVenta.obtienePlantilla(jCboRuta.getSelectedItem().toString());
        //long[] z = sesionVenta.obtienePlantilla(sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()));
        if(z==null){
            DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "Contacte al administrador del sistema.", Color.RED);
            this.dispose();
            return false;
        }
        plantillaId = z[0];
        cupo = z[1];
        if(plantillaId==-1){
            DialogoAceptar = new JDlgAceptar("¡Error de configuracion!", "La ruta no tiene relacionada una plantilla.", Color.RED);
            this.dispose();
            return false;
        }
        int suma=0;
        /*suma = (Integer.valueOf(jTxtEstudiante.getText())) + (Integer.valueOf(jTxtProfesor.getText()) +
                (Integer.valueOf(jTxtSenectud.getText()))+ (Integer.valueOf(jTxtCortesia.getText())));*/
        suma=Integer.valueOf(jTxtEstudiante.getText());
        if(suma>cupo){
            DialogoAceptar = new JDlgAceptar("¡Cantidad de estudiantes no permitida!","Ingrese valores correctos.", Color.RED);
            jTxtEstudiante.requestFocusInWindow();
            return false;
        }
        
        suma=Integer.valueOf(jTxtProfesor.getText());
        if(suma>cupo){
            DialogoAceptar = new JDlgAceptar("¡Cantidad de profesores no permitida!","Ingrese valores correctos.", Color.RED);
            jTxtProfesor.requestFocusInWindow();
            return false;
        }
        
        suma=Integer.valueOf(jTxtSenectud.getText());
        if(suma>cupo){
            DialogoAceptar = new JDlgAceptar("¡Cantidad de senectud no permitida!","Ingrese valores correctos.", Color.RED);
            jTxtSenectud.requestFocusInWindow();
            return false;
        }
        
        suma=Integer.valueOf(jTxtCortesia.getText());
        if(suma>cupo){
            DialogoAceptar = new JDlgAceptar("¡Cantidad de especiales no permitida!","Ingrese valores correctos.", Color.RED);
            jTxtCortesia.requestFocusInWindow();
            return false;
        }
        
        return true;
    }
    
    private void cargaParametros() {
        jTxtEstudiante.setText(sesionVenta.getValorParametro("P_LIMESTCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));
        jTxtProfesor.setText(sesionVenta.getValorParametro("P_LIMPROCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));
        jTxtSenectud.setText(sesionVenta.getValorParametro("P_LIMSENCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));
        jTxtCortesia.setText(sesionVenta.getValorParametro("P_LIMCORCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));
    }
    
    private boolean AutoCompletaFecha(){
        if(jTxtFecha.getText().length()==3) jTxtFecha.setText(jTxtFecha.getText()+MesAnho);
        if(jTxtFecha.getText().length()==6) jTxtFecha.setText(jTxtFecha.getText()+Anho);
        
        if(jTxtFecha.getText().length()>0 && jTxtFecha.getText().length()<10){
            jTxtFecha.selectAll();
            jTxtFecha.requestFocusInWindow();
            return false;
        }
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCboDestinoTramo;
    private javax.swing.JComboBox jCboEmpresa;
    private javax.swing.JComboBox jCboEstado;
    private javax.swing.JComboBox jCboOrigenTramo;
    private javax.swing.JComboBox jCboRuta;
    private javax.swing.JComboBox jCboServicio;
    private javax.swing.JComboBox jCboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JLabel jLblEmpresa;
    private javax.swing.JLabel jLblHorario;
    private javax.swing.JLabel jLblRuta;
    private javax.swing.JLabel jLblServicio;
    private javax.swing.JLabel jLblTramoDestino;
    private javax.swing.JLabel jLblTramoOrigen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnlDatosAdicionales;
    private javax.swing.JScrollPane jScpDetalle;
    private javax.swing.JTable jTblDetalle;
    private javax.swing.JTextField jTxtAnticipo;
    private javax.swing.JTextField jTxtContrato;
    private javax.swing.JTextField jTxtCortesia;
    private javax.swing.JTextField jTxtEstudiante;
    private javax.swing.JTextField jTxtFecha;
    private javax.swing.JTextField jTxtHora;
    private javax.swing.JTextField jTxtOrden;
    private javax.swing.JTextField jTxtProfesor;
    private javax.swing.JTextField jTxtRutaNombre;
    private javax.swing.JTextField jTxtSenectud;
    private javax.swing.JTextField jTxtSueldoOperador;
    // End of variables declaration//GEN-END:variables
    private Vector datosIniciales = new Vector();
    private long MENU_ID;
    private long USUARIO_ID;
    private String USUARIO_NUMERO;
    private String USUARIO_NOMBRE;
    private long SESION_ID;
    private int txRegistrar = 2;
    private int txModificarAlta = 3;
    private int txEliminar = 4;
    private int txGuardado = 5;
    private Object[] encabezado = {"Servicio", "Ruta", "Origen", "Destino", "Empresa", "Fecha", "Hora"};
    private DefaultTableModel xListado = new DefaultTableModel(null, encabezado){
            public boolean isCellEditable(int row, int col) {
                return false;
            }   
    };
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
    private int fila=-1;
    private Object[][] frmPago;
    private Object[][] listado;
    
    private SesionVenta sesionVenta=null;
    private DefaultComboBoxModel mCboServicios;
    private DefaultComboBoxModel mCboEmpresas;
    private DefaultComboBoxModel mCboRutas;
    private DefaultComboBoxModel mCboOrigenes;
    private DefaultComboBoxModel mCboDestinos;
    private BarraMensajes barraMensajes=new BarraMensajes();
    private boolean primerRegistro = true;
    private int transaccion;
    
    private JDlgAceptar DialogoAceptar;
    private JDlgSiNo DialogoSiNo;
    private JDlgSiNoCancelar DialogoSiNoCancelar;
    
    private Object[][] datos;
    
    private int numeroComponente=-2;
    private Vector auxOrigenes;
    private Vector auxDestinos;
    
    private String strServicioAnterior = "";
    private String strRutaAnterior = "";
    private List<TmsCorridasVentaV> tmsCorridasExtraV=null;
    private Color colorDialogoActivo = new Color(0,83,255);
    private boolean temporal=false;
    private String MesAnho;
    private String Anho;
    private long plantillaId;
    private long cupo;

    //private String[] limTipoPasaje;
}