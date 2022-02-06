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
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import tms_TextFields.JCuantityTextField;
import tms_TextFields.JDateTextField;
import tms_TextFields.JHourTextField;
import tms_psd.*;
import tms_psd.util.JDlgAceptar;
import tms_psd.util.JDlgSiNo;
import tms_psd.util.JDlgSiNoCancelar;
import tms_psd.util.JNumberTextFieldMaxDig;
import tms_psd.util.TmsCorridasVentaV;

/**
 *
 * @author  ocruz
 */
public class JDlgCEx extends JDialog implements KeyListener{
    
    /**
     * Creates new form JDlgCorridasExtras
     */
    public JDlgCEx(SesionVenta pSesionVenta, TmsCorridasVentaV pCorridaExtra, int pNumeroCE, int pCrearCE, String pHora) {
        this.sesionVenta = pSesionVenta;
        this.corridaExtra = new TmsCorridasVentaV();
        this.corridaExtra = pCorridaExtra;
        this.crearCE = pCrearCE;
        this.numeroCE = pNumeroCE;
        initComponents();
        this.setModal(true);
        if(pCrearCE==1) this.setTitle("Creacion de Corrida Extra #"+this.numeroCE);
        else {
            if(pCrearCE==2) this.setTitle("Modificacion de Corrida Extra #"+this.numeroCE);
            else{
                this.setTitle("Copiar Corrida Extra #"+this.numeroCE);
                jLblBarraEstado.setText("<html><font color=ff0000>ENTER</font> Crear corridas siguientes a partir de esta | <font color=ff0000>ESC</font> Cancelar</html>");
            }
        }
        jTxtHora.setText(pHora);
        String hoyEs = formatoFecha.format(new Date());
        jTxtFecha.setText(hoyEs);
        MesAnho=hoyEs.substring(3);
        Anho=hoyEs.substring(6);
        centrarJDialog();
        inhabilitarF10();
        this.jPnlFondo.requestFocusInWindow();
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
    }
    
    public TmsCorridasVentaV getCorridaExtra(){ return this.corridaExtraX; }
    
    public int getCapturaCE(){ return this.capturaCE; }
    
    public String getLetraEstado(){ return this.letraEstado; }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPnlFondo = new javax.swing.JPanel();
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
        jLblBarraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Creacion de Corrida Extra #1");
        setName("69");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPnlFondo.setName("69");
        jPnlFondo.addKeyListener(this);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corrida Extra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray));
        jPanel1.setFocusable(false);
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

        jTxtFecha.setEditable(false);
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
        jTxtEstudiante.setName("10");
        jTxtEstudiante.setFocusTraversalKeysEnabled(false);
        jTxtEstudiante.addKeyListener(this);

        jLabel9.setText("Profesores");

        jTxtProfesor.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtProfesor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtProfesor.setName("11");
        jTxtProfesor.setFocusTraversalKeysEnabled(false);
        jTxtProfesor.addKeyListener(this);

        jLabel10.setText("Senectud");

        jTxtSenectud.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtSenectud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtSenectud.setName("12");
        jTxtSenectud.setFocusTraversalKeysEnabled(false);
        jTxtSenectud.addKeyListener(this);

        jLabel11.setText("Especial");

        jTxtCortesia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtCortesia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtCortesia.setName("13");
        jTxtCortesia.setFocusTraversalKeysEnabled(false);
        jTxtCortesia.addKeyListener(this);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Hora:");

        jTxtHora.setEditable(false);
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

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlDatosAdicionales, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel1Layout.createSequentialGroup()
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
            .add(jPanel1Layout.createSequentialGroup()
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
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLblEmpresa)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jCboEmpresa, 0, 181, Short.MAX_VALUE))
                    .add(jTxtRutaNombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
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
                    .add(jCboEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
        jLblBarraEstado.setText("<html><font color=ff0000>&#171; &#187;</font> Ingresar datos | <font color=ff0000>F5</font> Aceptar captura | <font color=ff0000>ESC</font> Cancelar captura</html>");
        jLblBarraEstado.setName("69");

        org.jdesktop.layout.GroupLayout jPnlFondoLayout = new org.jdesktop.layout.GroupLayout(jPnlFondo);
        jPnlFondo.setLayout(jPnlFondoLayout);
        jPnlFondoLayout.setHorizontalGroup(
            jPnlFondoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
            .add(jPnlFondoLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPnlFondoLayout.setVerticalGroup(
            jPnlFondoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlFondoLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLblBarraEstado))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlFondo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlFondo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
// TODO add your handling code here:
        
    }//GEN-LAST:event_formInternalFrameClosing
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        this.strServicioAnterior="nada";
        this.strRutaAnterior="nada";
        if(this.crearCE == 0){
            CargaEncabezado();
            jCboServicio.setEnabled(false);
            jCboEmpresa.setEnabled(false);
            jCboRuta.setEnabled(false);
            jCboOrigenTramo.setEnabled(false);
            jCboDestinoTramo.setEnabled(false);
            jCboEstado.setEnabled(false);
            jTxtEstudiante.setEnabled(false);
            jTxtProfesor.setEnabled(false);
            jTxtSenectud.setEnabled(false);
            jTxtCortesia.setEnabled(false);
            jTxtSueldoOperador.setEnabled(false);
            jTxtAnticipo.setEnabled(false);
            jTxtContrato.setEnabled(false);
            jTxtOrden.setEnabled(false);
            jPnlFondo.requestFocusInWindow();
        }else{
            if(this.corridaExtra!=null)
                CargaEncabezado();
            else{
                jCboServicio.setSelectedIndex(0);
                cargaParametros();
            }
            strServicioAnterior="nada";//jCboServicio.getSelectedItem().toString();
            jLblBarraEstado.setFocusable(false);
            jCboServicio.requestFocusInWindow();
            return;
        }
        jPnlFondo.requestFocusInWindow();
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
            case KeyEvent.VK_ENTER: 
                if(crearCE==0){
                    this.capturaCE = 1;
                    this.dispose();
                    return;
                }
                if(numeroComponente==1){
                    //if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                        cargaParametros();
                        sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                        auxCargaRelacionesServicio();
                        strServicioAnterior=jCboServicio.getSelectedItem().toString();
                    //}
                    jCboRuta.requestFocusInWindow(); break;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                this.capturaCE = 0;
                this.dispose();
                return;
            // CASOS ESPECIALES
            case KeyEvent.VK_F5: // EXITO
                if(crearCE==0) return;
                //cargaParametros();
                sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                auxCargaRelacionesServicio();
                strServicioAnterior=jCboServicio.getSelectedItem().toString();
                if(!AutoCompletaFecha()) return;
                if(!validaDatosEntrada()) return;
                if(!validaCupoTipos()) return;
                int error = agregarLinea();
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
                this.capturaCE = 1;
                if(crearCE==1)
                    DialogoAceptar = new JDlgAceptar("¡Corrida extra #"+numeroCE+" creada!","El simbolo EXTRA+ simboliza que la corrida esta creada.", colorDialogoActivo);
                else
                    if(crearCE==2) DialogoAceptar = new JDlgAceptar("¡Corrida extra #"+numeroCE+" modificada!","El simbolo EXTRA+ simboliza que la corrida esta creada.", colorDialogoActivo);
                this.dispose();
                return;
            case KeyEvent.VK_RIGHT:
                if(crearCE==0) return;
                if(jPnlDatosAdicionales.isVisible()){
                    switch(numeroComponente){
                        case 1: if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                                    cargaParametros();
                                    sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                                    auxCargaRelacionesServicio();
                                    strServicioAnterior=jCboServicio.getSelectedItem().toString();
                                }
                                jCboRuta.requestFocusInWindow(); break;
                        case 2: jCboOrigenTramo.requestFocusInWindow(); break;
                        case 3: jCboDestinoTramo.requestFocusInWindow(); break;
                        case 4: jCboEmpresa.requestFocusInWindow(); break;
                        case 5: jCboEstado.requestFocusInWindow(); break;
                        case 8: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
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
                    case 1: if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                                    cargaParametros();
                                    sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                                    auxCargaRelacionesServicio();
                                    strServicioAnterior=jCboServicio.getSelectedItem().toString();
                            }
                            jCboRuta.requestFocusInWindow(); break;
                    case 2: jCboOrigenTramo.requestFocusInWindow(); break;
                    case 3: jCboDestinoTramo.requestFocusInWindow(); break;
                    case 4: jCboEmpresa.requestFocusInWindow(); break;
                    case 5: jCboEstado.requestFocusInWindow(); break;
                    case 8: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                    case 10: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                    case 11: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                    case 12: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                    case 13: jCboServicio.requestFocusInWindow(); break;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(numeroComponente==-9) return;
                if(jPnlDatosAdicionales.isVisible()){
                    switch(numeroComponente){
                        case 3: jCboRuta.requestFocusInWindow(); break;
                        case 4: jCboOrigenTramo.requestFocusInWindow(); break;
                        case 5: jCboDestinoTramo.requestFocusInWindow(); break;
                        case 8: jCboEmpresa.requestFocusInWindow(); break;
                        case 10: jCboEstado.requestFocusInWindow(); break;
                        case 11: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                        case 12: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                        case 13: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                        case 14: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                        case 15: jTxtSueldoOperador.selectAll(); jTxtSueldoOperador.requestFocusInWindow(); break;
                        case 16: jTxtContrato.selectAll(); jTxtContrato.requestFocusInWindow(); break;
                        case 17: jTxtOrden.selectAll(); jTxtOrden.requestFocusInWindow(); break;
                        case 1: 
                            if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                                cargaParametros();
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
                    case 3: jCboRuta.requestFocusInWindow(); break;
                    case 4: jCboOrigenTramo.requestFocusInWindow(); break;
                    case 5: jCboDestinoTramo.requestFocusInWindow(); break;
                    case 8: jCboEmpresa.requestFocusInWindow(); break;
                    case 10: jCboEstado.requestFocusInWindow(); break;
                    case 11: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                    case 12: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                    case 13: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                    case 1: 
                        if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                            cargaParametros();
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

    private boolean validaCupoTipos(){
        //obtiene plantilla
        String sId="";
        if(crearCE==1) sId=jCboRuta.getSelectedItem().toString();
        else sId=corridaExtra.getRutaNumero();
        /*long sId=0;
        if(crearCE==1) sId=sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString());
        else sId=sesionVenta.getServicioId(corridaExtra.getServicio());*/
        long[] z = sesionVenta.obtienePlantilla(sId);
        /*if(z==null){
            DialogoAceptar = new JDlgAceptar("¡No existe conexion a la base de datos!", "Contacte al administrador del sistema.", Color.RED);
            this.dispose();
            return false;
        }*/
        plantillaId = z[0];
        cupo = z[1];
        if(plantillaId==-1){
            DialogoAceptar = new JDlgAceptar("¡Error de configuracion!", "El servicio no tiene relacionada una plantilla.", Color.RED);
            this.dispose();
            return false;
        }
        int suma=0;
        /*suma = (Integer.valueOf(jTxtEstudiante.getText())) + (Integer.valueOf(jTxtProfesor.getText()) +
                (Integer.valueOf(jTxtSenectud.getText()))+ (Integer.valueOf(jTxtCortesia.getText())));
        if(suma>cupo){
            DialogoAceptar = new JDlgAceptar("¡Limites de tipos pasajeros no permitido!","Ingrese valores correctos.", Color.RED);
            jTxtEstudiante.requestFocusInWindow();
            return false;
        }*/
        
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
    
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                    if(numeroComponente==2 && jCboRuta.getItemCount()>0){ // ruta
                        if(strRutaAnterior.equals(jCboRuta.getSelectedItem().toString())) return;
                        jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
                        sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
                        auxCargaRelacionesRuta();
                        strRutaAnterior=jCboRuta.getSelectedItem().toString();
                    }
                    else{
                        if(numeroComponente==1){ // servicio
                            //if(strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())) return;
                            sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                            auxCargaRelacionesServicio();
                        }
                    }
                break;
            case KeyEvent.VK_DOWN:
                    if(numeroComponente==2 && jCboRuta.getItemCount()>0){
                        if(strRutaAnterior.equals(jCboRuta.getSelectedItem().toString())) return;
                        jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
                        sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
                        auxCargaRelacionesRuta();
                        strRutaAnterior=jCboRuta.getSelectedItem().toString();
                    }
                    else{
                        if(numeroComponente==1){ // servicio
                            //if(strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())) return;
                            sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                            auxCargaRelacionesServicio();
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
    
    private void CargaEncabezado(){
        jCboServicio.setSelectedItem(corridaExtra.getServicio());
        jCboRuta.setSelectedItem(corridaExtra.getRutaNumero());
        jTxtRutaNombre.setText(corridaExtra.getRutaNombre());
        auxOrigenes=new Vector();
        auxOrigenes.add(corridaExtra.getOrigen());
        mCboOrigenes = new DefaultComboBoxModel(auxOrigenes);
        jCboOrigenTramo.setModel(mCboOrigenes);
        auxDestinos=new Vector();
        auxDestinos.add(corridaExtra.getDestino());
        mCboDestinos = new DefaultComboBoxModel(auxDestinos);
        jCboDestinoTramo.setModel(mCboDestinos);
        jCboEmpresa.setSelectedItem(corridaExtra.getEmpresa());
        jCboEstado.setSelectedItem(sesionVenta.getEstadoCorrida(corridaExtra.getEstadoCorrida()));
        jTxtEstudiante.setText(String.valueOf(corridaExtra.getEstudiantesCorrida()));
        jTxtProfesor.setText(String.valueOf(corridaExtra.getProfesoresCorrida()));
        jTxtSenectud.setText(String.valueOf(corridaExtra.getSenectudCorrida()));
        jTxtCortesia.setText(String.valueOf(corridaExtra.getCortesiasCorrida()));
        jTxtSueldoOperador.setText(""+(corridaExtra.getSueldoOperador()==null ? "" : corridaExtra.getSueldoOperador()));
        jTxtContrato.setText(corridaExtra.getNumeroContrato());
        jTxtOrden.setText(corridaExtra.getNumeroOrden());
        jTxtAnticipo.setText(""+(corridaExtra.getMontoAnticipos()==null ? "" : corridaExtra.getMontoAnticipos()));
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
            jCboOrigenTramo.requestFocusInWindow();
            break;
        }
        
        if(error!=0) return false;
        
        if(!sesionVenta.validaServicioEmpresa(jCboServicio.getSelectedItem().toString(), jCboRuta.getSelectedItem().toString(), jCboEmpresa.getSelectedItem().toString())){
            DialogoAceptar = new JDlgAceptar("PSD.","<html>El servicio, ruta y empresa seleccionados no corresponden<br>a una configuracion valida.</html>", Color.RED);
            return false;
        }
        
        return true;
    }

    private int agregarLinea(){
        Timestamp hora;
        long tiempo;
        String strFecha;
            corridaExtraX = new TmsCorridasVentaV();
            corridaExtraX.setEmpresaId(sesionVenta.getEmpresaId(jCboEmpresa.getSelectedItem().toString()));
            corridaExtraX.setEmpresa(jCboEmpresa.getSelectedItem().toString());
            corridaExtraX.setServicioId(sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()));
            corridaExtraX.setServicio(jCboServicio.getSelectedItem().toString());
            corridaExtraX.setOrigenId(sesionVenta.getEstadoId(jCboOrigenTramo.getSelectedItem().toString()));
            corridaExtraX.setOrigen(jCboOrigenTramo.getSelectedItem().toString());
            corridaExtraX.setDestinoId(sesionVenta.getEstadoId(jCboDestinoTramo.getSelectedItem().toString()));
            corridaExtraX.setDestino(jCboDestinoTramo.getSelectedItem().toString());
            corridaExtraX.setRutaNumero(jCboRuta.getSelectedItem().toString());
            corridaExtraX.setRutaNombre(jTxtRutaNombre.getText());
            corridaExtraX.setRutaId(sesionVenta.getRutaId(jCboRuta.getSelectedItem().toString()));
            //corridaExtraX.setAutobusId((long)-1);
            //corridaExtraX.setAutobus("999999");
            //corridaExtraX.setAutobusOriginalId((long)-1);
            //corridaExtraX.setAutobusOriginal("999999");
            //corridaExtraX.setOperadorId((long)1178942);
            //corridaExtraX.setOperador("999999");
            //corridaExtraX.setOperadorOriginalId((long)1178942);
            //corridaExtraX.setOperadorOriginal("999999");
            //corridaExtraX.setOperadorAdicional("999999");
            corridaExtraX.setTipoCorrida("E");
            corridaExtraX.setEstadoCorridaId(sesionVenta.getEstadoCorridaId(jCboEstado.getSelectedItem().toString()));
            corridaExtraX.setEstadoCorrida(sesionVenta.getEstadoCorridaLetra(jCboEstado.getSelectedItem().toString()));
            this.letraEstado=corridaExtraX.getEstadoCorrida();
            corridaExtraX.setPlantillaId(plantillaId);
            corridaExtraX.setSenectudCorrida(Long.valueOf(jTxtSenectud.getText().toString()));
            corridaExtraX.setEstudiantesCorrida(Long.valueOf(jTxtEstudiante.getText().toString()));
            corridaExtraX.setProfesoresCorrida(Long.valueOf(jTxtProfesor.getText().toString()));
            corridaExtraX.setCortesiasCorrida(Long.valueOf(jTxtCortesia.getText().toString()));
            corridaExtraX.setNumeroContrato(jTxtContrato.getText());
            corridaExtraX.setNumeroOrden(jTxtOrden.getText());
            corridaExtraX.setSueldoOperador((jTxtSueldoOperador.getText().equals("") ? null : Double.valueOf(jTxtSueldoOperador.getText())));
            corridaExtraX.setMontoAnticipos((jTxtAnticipo.getText().equals("") ? null : Double.valueOf(jTxtAnticipo.getText())));
            try {
                corridaExtraX.setHoraCorrida(new Timestamp(formatoHora.parse(jTxtHora.getText()).getTime()));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            strFecha = jTxtFecha.getText()+" "+jTxtHora.getText();
            try {
                hora = new Timestamp(formatoFechaHora.parse(strFecha).getTime());
            } catch (ParseException ex) {
                return 2;
            }
            corridaExtraX.setFechaHoraCorrida(hora);
        return 0;
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
    private javax.swing.JPanel jPnlFondo;
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
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
    
    private SesionVenta sesionVenta=null;
    private DefaultComboBoxModel mCboServicios;
    private DefaultComboBoxModel mCboEmpresas;
    private DefaultComboBoxModel mCboRutas;
    private DefaultComboBoxModel mCboOrigenes;
    private DefaultComboBoxModel mCboDestinos;
    
    private JDlgAceptar DialogoAceptar;
    private JDlgSiNo DialogoSiNo;
    private JDlgSiNoCancelar DialogoSiNoCancelar;
    
    private int numeroComponente=-2;
    private Vector auxOrigenes;
    private Vector auxDestinos;
    
    private String strServicioAnterior = "";
    private String strRutaAnterior = "";
    private TmsCorridasVentaV corridaExtra=null;
    private TmsCorridasVentaV corridaExtraX=null;
    private Color colorDialogoActivo = new Color(0,83,255);
    private boolean temporal=false;
    private String MesAnho;
    private String Anho;
    
    private int capturaCE=0;
    private int numeroCE;
    private int crearCE;
    private String letraEstado;
    private long plantillaId;
    private long cupo;
}