/*
 * JIFOfertasServicios.java
 *
 * Created on 15 de agosto de 2007, 12:16 PM
 */

package tms_ofertasservicios;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_TextFields.JHourTextField;
import tms_ofertasservicios.util.BarraMensajes;
import tms_ofertasservicios.util.JDlgAceptar;
import tms_ofertasservicios.util.JDlgSiNo;
import tms_ofertasservicios.util.JDlgSiNoCancelar;
import tms_ofertasservicios.util.JNumberTextFieldMaxDig;
import tms_ofertasservicios.util.JTextTextFieldMaxChar;
import xertms.entidad.TmsOfertasServicioV;

/**
 *
 * @author  ocruz
 */
public class JIFOfertasServicios extends JInternalFrame implements KeyListener{
    
    /**
     * Creates new form JIFOfertasServicios
     */
    public JIFOfertasServicios(Vector datosIniciales) {
        this.datosIniciales = datosIniciales;
        this.USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString());
        this.USUARIO_NUMERO = datosIniciales.get(1).toString();
        this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
        this.SESION_ID = Long.valueOf(datosIniciales.get(3).toString());
        this.MENU_ID = Long.valueOf(datosIniciales.get(4).toString());
        System.out.println("SISTEMA INICIADO");
        sesionVenta = new SesionVenta(this.USUARIO_ID, this.MENU_ID,
                datosIniciales.get(5).toString(), Integer.valueOf(datosIniciales.get(6).toString()));
        int inicio = sesionVenta.procesoInicial();
        if(inicio==0) inicio = sesionVenta.procesoSecundario();
        switch(inicio){
            case -1: DialogoAceptar = new JDlgAceptar("¡No existe conexion con la base de datos!", "Contacte al administrador del sistema.", Color.RED); break;
            case 1: DialogoAceptar = new JDlgAceptar("¡Configuracion de rutas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 2: DialogoAceptar = new JDlgAceptar("¡Configuracion de empresas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 3: DialogoAceptar = new JDlgAceptar("¡Configuracion de servicios incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 4: DialogoAceptar = new JDlgAceptar("¡Configuracion de origenes/destinos incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 5: DialogoAceptar = new JDlgAceptar("¡Configuracion de parametros incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 6: DialogoAceptar = new JDlgAceptar("¡Configuracion de plantilla/ruta incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
            case 7: DialogoAceptar = new JDlgAceptar("¡Configuracion de servicios/empresas incorrecta!", "Contacte al administrador del sistema.", Color.RED); break;
        }
        if(inicio!=0) {
            inicioGral=false;
            return;
        }
        initComponents();
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        jTblDetalle.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"none");
        inhabilitarF10();
        inicioGral = true;
    }
    
    public boolean getInicioGral(){ return this.inicioGral; }
    
    private void AnchoColumnas(){
        TableColumn column = null;
        int anchoContenedor = jScpDetalle.getWidth();
        for (int i = 0; i < 6; i++) {
            column = jTblDetalle.getColumnModel().getColumn(i);
            switch(i){
                case 0:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 1:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.07)); break;
                case 2:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 3:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.16)); break;
                case 4:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.23)); break;
                case 5:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.22)); break;
            }
            column.setResizable(false);
        }
    }
    
    private void CerrarIFrame(){
        if(transaccion!=txBusq && transaccion != txResul){
            DialogoAceptar = new JDlgAceptar("¡No puede cerrar el programa!", "<html>Es importante que guarde o cancele<br>la transaccion que esta realizando</html>", Color.RED);
            return;
        }
        DialogoSiNo = new JDlgSiNo("!Confirme¡", "¿Realmente desea salir del programa?");
        if(!DialogoSiNo.getResultado()) return;
        this.dispose();
        return;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScpDetalle = new javax.swing.JScrollPane();
        jTblDetalle = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTxtNombreOfertaServicio = new JTextTextFieldMaxChar(29);
        jCkbLunes = new javax.swing.JCheckBox();
        jLblNombreOfertaServicio = new javax.swing.JLabel();
        jLblServicio = new javax.swing.JLabel();
        mCboServicios = new DefaultComboBoxModel(sesionVenta.getVectorServicios());
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
        mCboEmpresas = new DefaultComboBoxModel(sesionVenta.getVectorEmpresas());
        jCboEmpresa = new javax.swing.JComboBox(mCboEmpresas);
        jLblHorario = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtHora = new JHourTextField();
        jLabel4 = new javax.swing.JLabel();
        jCkbMartes = new javax.swing.JCheckBox();
        jCkbMiercoles = new javax.swing.JCheckBox();
        jCkbJueves = new javax.swing.JCheckBox();
        jCkbViernes = new javax.swing.JCheckBox();
        jCkbSabado = new javax.swing.JCheckBox();
        jCkbDomingo = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTxtEstudiante = new JNumberTextFieldMaxDig(2);
        jLabel9 = new javax.swing.JLabel();
        jTxtProfesor = new JNumberTextFieldMaxDig(2);
        jLabel10 = new javax.swing.JLabel();
        jTxtSenectud = new JNumberTextFieldMaxDig(2);
        jLabel11 = new javax.swing.JLabel();
        jTxtCortesia = new JNumberTextFieldMaxDig(2);
        jLblBarraEstado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setTitle("Ofertas de servicio");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Oferta de Servicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray));
        jTxtNombreOfertaServicio.setBackground(new java.awt.Color(255, 255, 204));
        jTxtNombreOfertaServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtNombreOfertaServicio.setMinimumSize(new java.awt.Dimension(23, 18));
        jTxtNombreOfertaServicio.setName("0");
        jTxtNombreOfertaServicio.setFocusTraversalKeysEnabled(false);
        jTxtNombreOfertaServicio.addKeyListener(this);

        jCkbLunes.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbLunes.setSelected(true);
        jCkbLunes.setText("Lun");
        jCkbLunes.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbLunes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbLunes.setName("7");
        jCkbLunes.setFocusTraversalKeysEnabled(false);
        jCkbLunes.addKeyListener(this);
        jCkbLunes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCkbLunesItemStateChanged(evt);
            }
        });

        jLblNombreOfertaServicio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblNombreOfertaServicio.setText("Nombre:");

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
        jCboEmpresa.setSelectedIndex(0);
        jCboEmpresa.addKeyListener(this);

        jLblHorario.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblHorario.setText("Horario:");

        jLabel5.setText("Hora");

        jTxtHora.setBackground(new java.awt.Color(255, 255, 204));
        jTxtHora.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtHora.setName("6");
        jTxtHora.setFocusTraversalKeysEnabled(false);
        jTxtHora.addKeyListener(this);

        jLabel4.setText("Dias");

        jCkbMartes.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbMartes.setText("Mar");
        jCkbMartes.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbMartes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbMartes.setName("8");
        jCkbMartes.setFocusTraversalKeysEnabled(false);
        jCkbMartes.addKeyListener(this);

        jCkbMiercoles.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbMiercoles.setText("Mie");
        jCkbMiercoles.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbMiercoles.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbMiercoles.setName("9");
        jCkbMiercoles.setFocusTraversalKeysEnabled(false);
        jCkbMiercoles.addKeyListener(this);

        jCkbJueves.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbJueves.setText("Jue");
        jCkbJueves.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbJueves.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbJueves.setName("10");
        jCkbJueves.setFocusTraversalKeysEnabled(false);
        jCkbJueves.addKeyListener(this);

        jCkbViernes.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbViernes.setSelected(true);
        jCkbViernes.setText("Vie");
        jCkbViernes.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbViernes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbViernes.setName("11");
        jCkbViernes.setFocusTraversalKeysEnabled(false);
        jCkbViernes.addKeyListener(this);

        jCkbSabado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbSabado.setText("Sab");
        jCkbSabado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbSabado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbSabado.setName("12");
        jCkbSabado.setFocusTraversalKeysEnabled(false);
        jCkbSabado.addKeyListener(this);

        jCkbDomingo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbDomingo.setText("Dom");
        jCkbDomingo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbDomingo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbDomingo.setName("13");
        jCkbDomingo.setFocusTraversalKeysEnabled(false);
        jCkbDomingo.addKeyListener(this);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Limites Tipos de Pasajeros:");

        jLabel8.setText("Estudiantes");

        jTxtEstudiante.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtEstudiante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtEstudiante.setText("0");
        jTxtEstudiante.setName("15");
        jTxtEstudiante.setFocusTraversalKeysEnabled(false);
        jTxtEstudiante.addKeyListener(this);

        jLabel9.setText("Profesores");

        jTxtProfesor.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtProfesor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtProfesor.setText("0");
        jTxtProfesor.setName("16");
        jTxtProfesor.setFocusTraversalKeysEnabled(false);
        jTxtProfesor.addKeyListener(this);

        jLabel10.setText("Senectud");

        jTxtSenectud.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtSenectud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtSenectud.setText("0");
        jTxtSenectud.setName("17");
        jTxtSenectud.setFocusTraversalKeysEnabled(false);
        jTxtSenectud.addKeyListener(this);

        jLabel11.setText("Especial");

        jTxtCortesia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtCortesia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTxtCortesia.setText("0");
        jTxtCortesia.setName("18");
        jTxtCortesia.setFocusTraversalKeysEnabled(false);
        jTxtCortesia.addKeyListener(this);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLblNombreOfertaServicio)
                            .add(jLblRuta)
                            .add(jLblEmpresa))
                        .add(12, 12, 12)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jTxtNombreOfertaServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 400, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                                .add(jLblServicio)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 165, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jCboRuta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(12, 12, 12)
                                        .add(jLabel3)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTxtRutaNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 169, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jCboEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(12, 12, 12)
                                        .add(jLblHorario)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel5)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTxtHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                        .add(jLabel4)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCkbLunes)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCkbMartes)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCkbMiercoles)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                        .add(jLblTramoOrigen)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCboOrigenTramo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(6, 6, 6)
                                        .add(jCkbJueves)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCkbViernes)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCkbSabado)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCkbDomingo))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLblTramoDestino)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jCboDestinoTramo, 0, 122, Short.MAX_VALUE))))))
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
                        .add(jTxtCortesia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTxtNombreOfertaServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLblNombreOfertaServicio)
                        .add(jLblServicio)
                        .add(jCboServicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLblRuta)
                    .add(jLblTramoOrigen)
                    .add(jLblTramoDestino)
                    .add(jCboOrigenTramo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jCboRuta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtRutaNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCboDestinoTramo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jCboEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLblHorario)
                    .add(jLabel5)
                    .add(jLabel4)
                    .add(jCkbLunes)
                    .add(jCkbMartes)
                    .add(jCkbMiercoles)
                    .add(jCkbJueves)
                    .add(jCkbViernes)
                    .add(jCkbSabado)
                    .add(jCkbDomingo)
                    .add(jLblEmpresa)
                    .add(jTxtHora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jLabel10)
                    .add(jTxtProfesor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9)
                    .add(jTxtEstudiante, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8)
                    .add(jTxtCortesia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11)
                    .add(jTxtSenectud, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblBarraEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLblBarraEstado.setText("<html><font color=ff0000>&#171; &#187;</font> Ingresar criterios | <font color=ff0000>ENTER</font> Buscar Ofertas | <font color=ff0000>F1</font> Nueva oferta | <font color=ff0000>F2</font> Cargar oferta | <font color=ff0000>F4</font> Cerrar aplicacion</html>");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Rev.07.08.08");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(703, Short.MAX_VALUE)
                .add(jLabel1)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblBarraEstado))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
// TODO add your handling code here:
        CerrarIFrame();
    }//GEN-LAST:event_formInternalFrameClosing
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        xListado.setDataVector(null,encabezado);
        AnchoColumnas();
        desHabilPorBusq();
        transaccion=txBusq;
        this.jTxtNombreOfertaServicio.setText("%");
        this.strServicioAnterior="";
        this.strRutaAnterior="";
        jLblBarraEstado.setText(barraMensajes.getMsg(0));
        this.setFoco();
    }//GEN-LAST:event_formComponentShown

    public void setFoco(){
        switch(transaccion){
            case txBusq: this.jTxtNombreOfertaServicio.requestFocusInWindow(); break;
            case txRegistrar: this.jCboRuta.requestFocusInWindow(); break;
            default:
                if(jTblDetalle.getRowCount()>0) jTblDetalle.requestFocusInWindow();
        }
    }
    
    private void jCkbLunesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCkbLunesItemStateChanged
// TODO add your handling code here:
    }//GEN-LAST:event_jCkbLunesItemStateChanged
    
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e){
        if(e.getComponent().getName()==null) return;
        numeroComponente = Integer.valueOf(e.getComponent().getName());
        switch(e.getKeyCode()){
            case KeyEvent.VK_F7:
                if(numeroComponente==-1 && (transaccion==txRegistrar || transaccion==txResul)){
                    if(transaccion==txRegistrar){ // modificar del JTable
                        transaccion = txModificarAlta;
                        temporal=false;
                    }
                    else{
                        transaccion = txModificar;
                        temporal=true;
                    }
                    HabilPorModificacion(temporal);
                    
                    primerRegistro = false;
                    jLblBarraEstado.setText(barraMensajes.getMsg(4));
                    jCboRuta.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_F9:
                if(transaccion==txModificarAlta || transaccion==txModificar){
                    if(temporal){
                        if(!validaDatosEntrada()) return;
                        if(!validaCupoTipos()) return;
                        if(!ActualizaLinea(true)) return;
                        if(!sesionVenta.afectaRolBaseLinea(sesionVenta.getResultadosBusquedas().get(jTblDetalle.getSelectedRow()).getOfertaServicioId())){
                            DialogoSiNo = new JDlgSiNo("Posible afectación de rol base.", "<html>La modificación de esta oferta afectara un rol base<br>previamente guardado. ¿Desea modificarla?</html>");
                            if(!DialogoSiNo.getResultado()) return;
                        }
                        if(!sesionVenta.actualizaOfertas(fila)){
                            DialogoAceptar = new JDlgAceptar("¡La oferta no se pudo actualizar!","Contacte al administrador del sistema.", Color.RED);
                            desHabilPorBusq();
                            transaccion = txBusq;
                            xListado.setDataVector(null, encabezado);
                            AnchoColumnas();
                            tmsOfertasServicioV=null;
                            jTxtNombreOfertaServicio.requestFocusInWindow();
                            return;
                        }
                        DialogoAceptar = new JDlgAceptar("¡La oferta se actualizo correctamente!","Presione ENTER para continuar....", colorDialogoActivo);
                        transaccion = txResul;
                        jLblBarraEstado.setText(barraMensajes.getMsg(2));
                    }
                    else{
                        if(!ActualizaLinea(false)) return;
                        transaccion = txRegistrar;
                        jLblBarraEstado.setText(barraMensajes.getMsg(3));
                    }
                    temporal=false;
                    jTblDetalle.setColumnSelectionInterval(0, 0);
                    jTblDetalle.setRowSelectionInterval(fila, fila);
                    jTblDetalle.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_F8:
                if(numeroComponente==-1 && (transaccion==txRegistrar || transaccion==txResul)){
                    if(jTblDetalle.getRowCount()==0) return;
                    DialogoSiNo = new JDlgSiNo("¡Confirme!", "¿Realmente desea eliminar?");
                    if(!DialogoSiNo.getResultado()) return;
                    if(transaccion==txRegistrar){ // eliminar del JTable
                        if(e.isAltDown()){ // todo
                            fila=-1;
                            HabilPorAlta(true);
                            jLblBarraEstado.setText(barraMensajes.getMsg(1));
                            transaccion = txRegistrar;
                            primerRegistro=true;
                            tmsOfertasServicioV = new ArrayList<TmsOfertasServicioV>();
                            xListado.setDataVector(null, encabezado);
                            AnchoColumnas();
                            jTxtNombreOfertaServicio.requestFocusInWindow();
                            return;
                        }
                        tmsOfertasServicioV.remove(jTblDetalle.getSelectedRow());
                        xListado.removeRow(jTblDetalle.getSelectedRow());
                        if(jTblDetalle.getRowCount()==0){
                            fila=-1;
                            HabilPorAlta(true);
                            jLblBarraEstado.setText(barraMensajes.getMsg(1));
                            transaccion = txRegistrar;
                            primerRegistro=true;
                            tmsOfertasServicioV = new ArrayList<TmsOfertasServicioV>();
                            jTxtNombreOfertaServicio.requestFocusInWindow();
                        }
                        else{
                            jTblDetalle.setColumnSelectionInterval(0, 0);
                            jTblDetalle.setRowSelectionInterval(0, 0);
                            jTblDetalle.requestFocusInWindow();
                        }
                    }
                    else{ // eliminar de la base de datos
                        if(e.isAltDown()){ // todo
                            boolean bandera = false;
                            for(int i=0; i<jTblDetalle.getRowCount(); i++){
                                if(!sesionVenta.afectaRolBaseLinea(sesionVenta.getResultadosBusquedas().get(i).getOfertaServicioId())){
                                    String strHora = formatoHora.format(sesionVenta.getResultadosBusquedas().get(i).getHoraCorrida().getTime());
                                    DialogoSiNo = new JDlgSiNo("Oferta de: "+strHora+" hrs.", "<html>La eliminación de esta oferta afectara un rol base<br>previamente guardado. ¿Desea eliminarla?</html>");
                                    if(!DialogoSiNo.getResultado()) continue;
                                }
                                bandera = true;
                                if(!sesionVenta.eliminarOferta(sesionVenta.getResultadosBusquedas().get(i).getOfertaServicioId())){
                                    DialogoAceptar = new JDlgAceptar("¡No se pudo eliminar la oferta!","Contacte al administrador del sistema.", Color.RED);
                                    break;
                                }
                            }
                            if(!bandera) return;
                            desHabilPorBusq();
                            transaccion = txBusq;
                            tmsOfertasServicioV=null;
                            xListado.setDataVector(null, encabezado);
                            AnchoColumnas();
                            DialogoAceptar = new JDlgAceptar("¡Oferta eliminada correctamente!","Presione ENTER para continuar.", colorDialogoActivo);
                            jTxtNombreOfertaServicio.requestFocusInWindow();
                            return;
                        }
                        if(!sesionVenta.afectaRolBaseLinea(sesionVenta.getResultadosBusquedas().get(jTblDetalle.getSelectedRow()).getOfertaServicioId())){
                            DialogoSiNo = new JDlgSiNo("Posible afectación de rol base.", "<html>La eliminación de esta oferta afectara un rol base<br>previamente guardado. ¿Desea eliminarla?</html>");
                            if(!DialogoSiNo.getResultado()) return;
                        }
                        if(!sesionVenta.eliminarOferta(sesionVenta.getResultadosBusquedas().get(jTblDetalle.getSelectedRow()).getOfertaServicioId())){
                            DialogoAceptar = new JDlgAceptar("¡No se pudo eliminar la oferta!","Contacte al administrador del sistema.", Color.RED);
                            return;
                        }
                        else{
                            DialogoAceptar = new JDlgAceptar("¡Oferta eliminada correctamente!","Presione ENTER para continuar.", colorDialogoActivo);
                            sesionVenta.getResultadosBusquedas().remove(jTblDetalle.getSelectedRow());
                            xListado.removeRow(jTblDetalle.getSelectedRow());
                            if(jTblDetalle.getRowCount()==0){
                                desHabilPorBusq();
                                transaccion = txBusq;
                                xListado.setDataVector(null, encabezado);
                                AnchoColumnas();
                                tmsOfertasServicioV=null;
                                jTxtNombreOfertaServicio.requestFocusInWindow();
                                return;
                            }
                            else{
                                jTblDetalle.setColumnSelectionInterval(0, 0);
                                jTblDetalle.setRowSelectionInterval(0, 0);
                                jTblDetalle.requestFocusInWindow();
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_1: if(transaccion!=txBusq) return;
                                  if(e.isControlDown()){
                                    this.eventoTeclado=e;
                                    try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                                  }
                                  break;
            case KeyEvent.VK_2: if(transaccion!=txBusq) return;
                                if(e.isControlDown()) 
                                try {this.setIcon(true);
                                } catch (PropertyVetoException ex) { ; } break;
            case KeyEvent.VK_F4:
                if(!e.isAltDown()){
                    if(transaccion==txBusq) CerrarIFrame();
                }
                break;
            case KeyEvent.VK_F2:
                if(transaccion==txBusq ){
                    jLblBarraEstado.setText(barraMensajes.getMsg(5));
                    if(!sesionVenta.muestraOfertasGuardadas()){
                        DialogoAceptar = new JDlgAceptar("No existen ofertas de servicio.", "No es posible cargar ninguna oferta.", Color.RED);
                        jLblBarraEstado.setText(barraMensajes.getMsg(0));
                        return;
                    }
                    jDlgCargaOferta = new JDlgCargaOferta(sesionVenta.getOfertasGuardadas());
                    jDlgCargaOferta.setVisible(true);
                    if(!jDlgCargaOferta.isResultado()){
                        jLblBarraEstado.setText(barraMensajes.getMsg(0));
                        return;
                    }
                    avisameSINuevaLinea = false;
                    jLblBarraEstado.setText(barraMensajes.getMsg(0));
                    // cargo
                    ofertaServicio_Nombre = jDlgCargaOferta.CargarNombreOfertaServicio();
                    cargarOfertaDeServicio();
                    return;
                }
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
                if(transaccion==txResul && numeroComponente==-1){
                    desHabilPorBusq();
                    transaccion = txBusq;
                    xListado.setDataVector(null, encabezado);
                    AnchoColumnas();
                    tmsOfertasServicioV=null;
                    jLblBarraEstado.setText(barraMensajes.getMsg(0));
                    jTxtNombreOfertaServicio.requestFocusInWindow();
                    return;
                }
                if(transaccion==txBusq) return;
                if(transaccion==txModificarAlta || transaccion==txModificar){
                    CargaEncabezado(temporal);
                    if(transaccion==txModificarAlta){
                        transaccion = txRegistrar;
                        jLblBarraEstado.setText(barraMensajes.getMsg(1));
                    }
                    else{
                        transaccion = txResul;
                        jLblBarraEstado.setText(barraMensajes.getMsg(2));
                    }
                    desHabilTodo();
                    jTblDetalle.setColumnSelectionInterval(0, 0);
                    jTblDetalle.setRowSelectionInterval(fila, fila);
                    jTblDetalle.requestFocusInWindow();
                    return;
                }
                if(transaccion==txRegistrarNuevasLineas){
                    if(avisameSINuevaLinea){
                        DialogoSiNo = new JDlgSiNo("¡No ha guardado la oferta!","¿Realmente desea cancelar la operacion?");
                        if(!DialogoSiNo.getResultado()) return;
                    }
                    cargarOfertaDeServicio();
                    return;
                }
                DialogoSiNo = new JDlgSiNo("¡No ha guardado la oferta!","¿Realmente desea cancelar la operacion?");
                if(!DialogoSiNo.getResultado()) return;
                desHabilPorBusq();
                xListado.setDataVector(null, encabezado);
                AnchoColumnas();
                jLblBarraEstado.setText(barraMensajes.getMsg(0));
                transaccion = txBusq;
                primerRegistro=true;
                this.jTxtNombreOfertaServicio.setText("%");
                tmsOfertasServicioV=null;
                jTxtNombreOfertaServicio.requestFocusInWindow();
                break;
            case KeyEvent.VK_F1:
                if(transaccion!=txBusq && transaccion!=txResul) return;
                if(transaccion==txResul){
                    xListado.setDataVector(null, encabezado);
                    AnchoColumnas();
                }
                transaccion=txRegistrar;
                fila=-1;
                HabilPorAlta(true);
                jLblBarraEstado.setText(barraMensajes.getMsg(1));
                primerRegistro=true;
                tmsOfertasServicioV = new ArrayList<TmsOfertasServicioV>();
                jTxtNombreOfertaServicio.requestFocusInWindow();
                break;
            // CASOS ESPECIALES
            case KeyEvent.VK_F5:
                if(transaccion==txBusq || transaccion==txResul || transaccion==txModificar || transaccion==txModificarAlta) return;
                if(jTblDetalle.isFocusOwner()){
                    HabilPorAlta(false);
                    jLblBarraEstado.setText(barraMensajes.getMsg(1));
                    jCboRuta.requestFocusInWindow();
                    return;
                }
                if(!validaDatosEntrada()) return;
                if(!validaHoras()) return;
                if(!validaCupoTipos()) return;
                if(primerRegistro){
                    if(sesionVenta.existeNombreOfertaServicio(jTxtNombreOfertaServicio.getText())){
                        DialogoAceptar = new JDlgAceptar("¡Ya existe el nombre de la oferta!", "Ingrese otro nombre.", Color.RED);
                        jTxtNombreOfertaServicio.requestFocusInWindow();
                        return;
                    }
                }
                if(!agregarLinea(1, 0)){
                    DialogoAceptar = new JDlgAceptar("¡El valor del campo hora es incorrecto!","Presione ENTER para continuar", Color.RED);
                    jTxtHora.requestFocusInWindow();
                    return;
                }
                if(this.transaccion==txRegistrarNuevasLineas){
                    avisameSINuevaLinea = true;
                    jTblDetalle.setRowSelectionInterval(jTblDetalle.getRowCount()-1,jTblDetalle.getRowCount()-1);
                    jTblDetalle.setColumnSelectionInterval(0,0);
                    //jTblDetalle.requestFocusInWindow();
                    jCboRuta.requestFocusInWindow();
                    return;
                }
                if(primerRegistro) HabilPorAlta(false);
                primerRegistro=false;
                jCboRuta.requestFocusInWindow();
                break;
            case KeyEvent.VK_F6:
                if(transaccion==txBusq || transaccion==txResul || transaccion==txModificar || transaccion==txModificarAlta) return;
                if(!validaDatosEntrada()) return;
                if(!validaHoras()) return;
                if(!validaCupoTipos()) return;
                if(jTblDetalle.isFocusOwner()) return;
                Timestamp hora = null;
                try {
                    hora = new Timestamp(formatoHora.parse(jTxtHora.getText()).getTime());
                } catch (ParseException ex) {
                    DialogoAceptar = new JDlgAceptar("¡El valor del campo hora es incorrecto!","Presione ENTER para continuar", Color.RED);
                    jTxtHora.requestFocusInWindow();
                    return;
                }
                if(primerRegistro){
                    if(sesionVenta.existeNombreOfertaServicio(jTxtNombreOfertaServicio.getText())){
                        DialogoAceptar = new JDlgAceptar("¡Ya existe el nombre de la oferta!", "Ingrese otro nombre.", Color.RED);
                        jTxtNombreOfertaServicio.requestFocusInWindow();
                        return;
                    }
                }
                JDlgGeneracionAuto genAuto = new JDlgGeneracionAuto(hora);
                genAuto.setVisible(true);
                int salidas = genAuto.getSalidas();
                if(salidas==-1) return;
                if(!validaHorasGenAuto(hora, genAuto.getHoraFinal(), genAuto.getFrecuencia())) return;
                agregarLinea(salidas, genAuto.getFrecuencia());
                if(this.transaccion==txRegistrarNuevasLineas){
                    jTblDetalle.setRowSelectionInterval(jTblDetalle.getRowCount()-1,jTblDetalle.getRowCount()-1);
                    jTblDetalle.setColumnSelectionInterval(0,0);
                    //jTblDetalle.requestFocusInWindow();
                    jCboRuta.requestFocusInWindow();
                    return;
                }
                if(primerRegistro) HabilPorAlta(false);
                primerRegistro=false;
                jCboRuta.requestFocusInWindow();
                break;
            //
            case KeyEvent.VK_F10: // guardar ofertas en base de datos
                if(transaccion==txModificarAlta || transaccion==txModificar || transaccion==txBusq || transaccion==txResul) return;
                if(jTblDetalle.getRowCount()==0){
                    DialogoAceptar = new JDlgAceptar("¡Aviso!", "¡No existen ofertas para ser guardadas!", Color.RED);
                    return;
                }
                
                DialogoSiNoCancelar = new JDlgSiNoCancelar("¡Confirme!","<html>¿Desea guardar la oferta <br><font color=ff0000>"+jTxtNombreOfertaServicio.getText()+"</font>?</html>");
                switch(DialogoSiNoCancelar.getResultado()){
                    case 3: return;
                    case 2: avisameSINuevaLinea = false; limpieza(); return;
                }
                if(jTblDetalle.getRowCount()>15){
                    DialogoAceptar = new JDlgAceptar("¡Importante!","<html>Es posible que el proceso de guardar<br><font color=ff0000>"+
                            jTblDetalle.getRowCount()+"</font> ofertas tarde varios segundos.</html>", Color.ORANGE);
                }
                if(!sesionVenta.guardarOfertas(tmsOfertasServicioV))
                    DialogoAceptar = new JDlgAceptar("¡No fue posible guardar la oferta!","Contacte al administrador del sistem.", Color.RED);
                else
                    DialogoAceptar = new JDlgAceptar("¡La oferta se guardo correctamente!","Presione ENTER para continuar.", colorDialogoActivo);
                avisameSINuevaLinea = false;
                limpieza();
                break;    
            case KeyEvent.VK_ENTER:
                if(transaccion!=txBusq) return;
                if(jTxtNombreOfertaServicio.getText().equals("")){
                    DialogoAceptar = new JDlgAceptar("¡Criterio invalido!", "<html>Ingrese nombre de oferta o simbolo %<br>para buscar todas las ofertas.</html>", Color.RED);
                    return;
                }
                if(jTxtNombreOfertaServicio.getText().equals("%")){
                    DialogoSiNo = new JDlgSiNo("Consulta general.","<html>Esta consulta puede tardar unos segundos,<br>¿desea ejecutarla?</html>");
                    if(!DialogoSiNo.getResultado()) return;
                }
                jLblBarraEstado.setText("...");
                if((datos=sesionVenta.busqueda(jTxtNombreOfertaServicio.getText(), jCboEmpresa.getSelectedItem().toString(), jCboRuta.getSelectedItem().toString()))==null){
                    DialogoAceptar = new JDlgAceptar("¡La busqueda no genero resultados!", "Presione ENTER para continuar", Color.RED);
                    jLblBarraEstado.setText(barraMensajes.getMsg(0));
                    return;
                }
                ofertaServicio_Nombre = jTxtNombreOfertaServicio.getText();
                jLblBarraEstado.setText(barraMensajes.getMsg(2));
                xListado.setDataVector(datos, encabezado);
                AnchoColumnas();
                fila=0;
                CargaEncabezado(true);
                desHabilTodo();
                transaccion=txResul;
                jTblDetalle.setRowSelectionInterval(0,0);
                jTblDetalle.setColumnSelectionInterval(0,0);
                jTblDetalle.requestFocusInWindow();
                break;
            case KeyEvent.VK_RIGHT:
                    if(!jCboOrigenTramo.isEnabled()){
                        switch(numeroComponente){
                            case 0: jCboRuta.requestFocusInWindow(); break;
                            case 2: jCboEmpresa.requestFocusInWindow(); break;
                            case 5: jTxtNombreOfertaServicio.selectAll(); jTxtNombreOfertaServicio.requestFocusInWindow(); break;
                        }
                        return;
                    }
                    if(!jCboServicio.isEnabled()){
                        switch(numeroComponente){
                            case 2: jCboOrigenTramo.requestFocusInWindow(); break;
                            case 3: jCboDestinoTramo.requestFocusInWindow(); break;
                            case 4: jCboEmpresa.requestFocusInWindow(); break;
                            case 5: 
                                if(!jTxtHora.isEnabled()){
                                    jCkbLunes.requestFocusInWindow();
                                }
                                else{
                                    jTxtHora.selectAll(); jTxtHora.requestFocusInWindow();
                                }
                                break;
                            case 6: jCkbLunes.requestFocusInWindow(); break;
                            case 7: jCkbMartes.requestFocusInWindow(); break;
                            case 8: jCkbMiercoles.requestFocusInWindow(); break;
                            case 9: jCkbJueves.requestFocusInWindow(); break;
                            case 10: jCkbViernes.requestFocusInWindow(); break;
                            case 11: jCkbSabado.requestFocusInWindow(); break;
                            case 12: jCkbDomingo.requestFocusInWindow(); break;
                            case 13: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                            case 15: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                            case 16: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                            case 17: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                            case 18: jCboRuta.requestFocusInWindow(); break;
                        }
                        return;
                    }
                    switch(numeroComponente){
                        case 0: jCboServicio.requestFocusInWindow(); break;
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
                        case 5: jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); break;
                        case 6: jCkbLunes.requestFocusInWindow(); break;
                        case 7: jCkbMartes.requestFocusInWindow(); break;
                        case 8: jCkbMiercoles.requestFocusInWindow(); break;
                        case 9: jCkbJueves.requestFocusInWindow(); break;
                        case 10: jCkbViernes.requestFocusInWindow(); break;
                        case 11: jCkbSabado.requestFocusInWindow(); break;
                        case 12: jCkbDomingo.requestFocusInWindow(); break;
                        case 13: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                        case 15: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                        case 16: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                        case 17: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                        case 18: jTxtNombreOfertaServicio.selectAll(); jTxtNombreOfertaServicio.requestFocusInWindow(); break;
                    }
                break;
            case KeyEvent.VK_LEFT:
                    if(!jCboOrigenTramo.isEnabled()){
                        switch(numeroComponente){
                            case 0: jCboEmpresa.requestFocusInWindow(); break;
                            case 2: jTxtNombreOfertaServicio.selectAll(); jTxtNombreOfertaServicio.requestFocusInWindow(); break;
                            case 5: jCboRuta.requestFocusInWindow(); break;
                        }
                        return;
                    }
                    if(!primerRegistro){
                        switch(numeroComponente){
                            case 2: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                            case 3: jCboRuta.requestFocusInWindow(); break;
                            case 4: jCboOrigenTramo.requestFocusInWindow(); break;
                            case 5: jCboDestinoTramo.requestFocusInWindow(); break;
                            case 6: jCboEmpresa.requestFocusInWindow(); break;
                            case 7: 
                                if(!jTxtHora.isEnabled()){
                                    jCboEmpresa.requestFocusInWindow();
                                }
                                else{
                                    jTxtHora.selectAll(); jTxtHora.requestFocusInWindow();
                                }
                                break;
                            case 8: jCkbLunes.requestFocusInWindow(); break;
                            case 9: jCkbMartes.requestFocusInWindow(); break;
                            case 10: jCkbMiercoles.requestFocusInWindow(); break;
                            case 11: jCkbJueves.requestFocusInWindow(); break;
                            case 12: jCkbViernes.requestFocusInWindow(); break;
                            case 13: jCkbSabado.requestFocusInWindow(); break;
                            case 15: jCkbDomingo.requestFocusInWindow(); break;
                            case 16: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                            case 17: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                            case 18: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                        }
                        return;
                    }
                    switch(numeroComponente){
                        case 0: jTxtCortesia.selectAll(); jTxtCortesia.requestFocusInWindow(); break;
                        case 1: 
                            if(!strServicioAnterior.equals(jCboServicio.getSelectedItem().toString())){
                                sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
                                auxCargaRelacionesServicio();
                                strServicioAnterior=jCboServicio.getSelectedItem().toString();
                            }
                            cargaParametros(); jTxtNombreOfertaServicio.selectAll(); jTxtNombreOfertaServicio.requestFocusInWindow(); break;
                        case 2: jCboServicio.requestFocusInWindow(); break;
                        case 3: jCboRuta.requestFocusInWindow(); break;
                        case 4: jCboOrigenTramo.requestFocusInWindow(); break;
                        case 5: jCboDestinoTramo.requestFocusInWindow(); break;
                        case 6: jCboEmpresa.requestFocusInWindow(); break;
                        case 7: jTxtHora.selectAll(); jTxtHora.requestFocusInWindow(); break;
                        case 8: jCkbLunes.requestFocusInWindow(); break;
                        case 9: jCkbMartes.requestFocusInWindow(); break;
                        case 10: jCkbMiercoles.requestFocusInWindow(); break;
                        case 11: jCkbJueves.requestFocusInWindow(); break;
                        case 12: jCkbViernes.requestFocusInWindow(); break;
                        case 13: jCkbSabado.requestFocusInWindow(); break;
                        case 15: jCkbDomingo.requestFocusInWindow(); break;
                        case 16: jTxtEstudiante.selectAll(); jTxtEstudiante.requestFocusInWindow(); break;
                        case 17: jTxtProfesor.selectAll(); jTxtProfesor.requestFocusInWindow(); break;
                        case 18: jTxtSenectud.selectAll(); jTxtSenectud.requestFocusInWindow(); break;
                    }
                break;
            case KeyEvent.VK_F12:
                if(transaccion==txResul){
                    if(ofertaServicio_Nombre.contains("%")){
                        DialogoAceptar = new JDlgAceptar("No se permite esta operación.", "<html>Existen varias ofertas con nombre distinto en<br>este listado. Cargue solo un nombre de oferta.</html>", Color.RED);
                        return;
                    }
                    avisameSINuevaLinea = false;
                    jLblBarraEstado.setText(barraMensajes.getMsg(6));
                    transaccion=txRegistrarNuevasLineas;
                    fila=-1;
                    primerRegistro=false;
                    tmsOfertasServicioV = new ArrayList<TmsOfertasServicioV>();
                    HabilPorAnhadir();
                    return;
                }
                avisameSINuevaLinea = false;
                guardarNuevaLineaOferta();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: case KeyEvent.VK_PAGE_UP:
                if(numeroComponente==-1){
                    fila = jTblDetalle.getSelectedRow();
                    if(transaccion==txResul) CargaEncabezado(true);
                    else{
                        if(transaccion==txModificar) CargaEncabezado(temporal);
                    }
                }
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
                if(numeroComponente==-1){
                    fila = jTblDetalle.getSelectedRow();
                    if(transaccion==txResul) CargaEncabezado(true);
                    else{
                        if(transaccion==txModificar) CargaEncabezado(temporal);
                    }
                }
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
    
    private void desHabilPorBusq(){
        jTxtNombreOfertaServicio.setEnabled(true);
        jTxtNombreOfertaServicio.setText("%");
        jCboServicio.setEnabled(false);
        jCboServicio.setSelectedIndex(0);
        mCboRutas = new DefaultComboBoxModel(sesionVenta.getVectorRutas());
        jCboRuta.setModel(mCboRutas);
        jCboRuta.setEnabled(true);
        jCboRuta.setSelectedIndex(0);
        jCboOrigenTramo.setEnabled(false);
        jCboDestinoTramo.setEnabled(false);
        mCboEmpresas = new DefaultComboBoxModel(sesionVenta.getVectorEmpresas());
        jCboEmpresa.setModel(mCboEmpresas);
        jCboEmpresa.setEnabled(true);
        jCboEmpresa.setSelectedIndex(0);
        jTxtHora.setEnabled(false);
        jTxtHora.setText("");
        jCkbLunes.setEnabled(false); jCkbMartes.setEnabled(false); jCkbMiercoles.setEnabled(false); jCkbJueves.setEnabled(false);
        jCkbViernes.setEnabled(false); jCkbSabado.setEnabled(false); jCkbDomingo.setEnabled(false);
        jCkbLunes.setSelected(false); jCkbMartes.setSelected(false); jCkbMiercoles.setSelected(false); jCkbJueves.setSelected(false);
        jCkbViernes.setSelected(false); jCkbSabado.setSelected(false); jCkbDomingo.setSelected(false);
        jTxtEstudiante.setEnabled(false); jTxtProfesor.setEnabled(false);
        jTxtSenectud.setEnabled(false); jTxtCortesia.setEnabled(false);
        jTxtEstudiante.setText(""); jTxtProfesor.setText("");
        jTxtSenectud.setText(""); jTxtCortesia.setText("");
    }
    
    private void auxCargaRelacionesServicioModificacion(boolean datosBD){
        if(datosBD){
            mCboRutas = new DefaultComboBoxModel(sesionVenta.getRutasV());
            jCboRuta.setModel(mCboRutas);
            jCboRuta.setSelectedItem(sesionVenta.getResultadosBusquedas().get(fila).getRutaNumero());
            jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
            sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
            mCboOrigenes = new DefaultComboBoxModel(sesionVenta.getVectorOrigenes());
            jCboOrigenTramo.setModel(mCboOrigenes);
            mCboDestinos = new DefaultComboBoxModel(sesionVenta.getVectorDestinos());
            jCboDestinoTramo.setModel(mCboDestinos);
            jCboOrigenTramo.setSelectedItem(sesionVenta.getResultadosBusquedas().get(fila).getOrigen());
            jCboDestinoTramo.setSelectedItem(sesionVenta.getResultadosBusquedas().get(fila).getDestino());
        }
        else{
            mCboRutas = new DefaultComboBoxModel(sesionVenta.getRutasV());
            jCboRuta.setModel(mCboRutas);
            jCboRuta.setSelectedItem(tmsOfertasServicioV.get(fila).getRutaNumero());
            jTxtRutaNombre.setText(sesionVenta.cargaNombreRuta(jCboRuta.getSelectedItem().toString()));
            sesionVenta.cargaRelacionRutaOrigenesDestinos(jCboRuta.getSelectedItem().toString());
            mCboOrigenes = new DefaultComboBoxModel(sesionVenta.getVectorOrigenes());
            jCboOrigenTramo.setModel(mCboOrigenes);
            mCboDestinos = new DefaultComboBoxModel(sesionVenta.getVectorDestinos());
            jCboDestinoTramo.setModel(mCboDestinos);
            jCboOrigenTramo.setSelectedItem(tmsOfertasServicioV.get(fila).getOrigen());
            jCboDestinoTramo.setSelectedItem(tmsOfertasServicioV.get(fila).getDestino());
        }
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
    
    private void HabilPorAlta(boolean nuevo){
        if(nuevo){
            jTxtNombreOfertaServicio.setEnabled(true);
            jTxtNombreOfertaServicio.setText("");
            mCboEmpresas = new DefaultComboBoxModel(sesionVenta.getEmpresasV());
            jCboEmpresa.setModel(mCboEmpresas);
            jCboServicio.setEnabled(true);
            jCboServicio.setSelectedIndex(0);
            sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
            auxCargaRelacionesServicio();
        }
        else{
            jTxtNombreOfertaServicio.setEnabled(false);
            jCboServicio.setEnabled(false);
        }
        jCboOrigenTramo.setEnabled(true);
        jCboDestinoTramo.setEnabled(true);
        jCboEmpresa.setSelectedIndex(0);
        jTxtHora.setEnabled(true); jTxtHora.setText("");
        jCkbLunes.setEnabled(true); jCkbMartes.setEnabled(true); jCkbMiercoles.setEnabled(true); jCkbJueves.setEnabled(true);
        jCkbViernes.setEnabled(true); jCkbSabado.setEnabled(true); jCkbDomingo.setEnabled(true);
        jCkbLunes.setSelected(false); jCkbMartes.setSelected(false); jCkbMiercoles.setSelected(false); jCkbJueves.setSelected(false);
        jCkbViernes.setSelected(false); jCkbSabado.setSelected(false); jCkbDomingo.setSelected(false);
        jTxtEstudiante.setEnabled(true); jTxtProfesor.setEnabled(true);
        jTxtSenectud.setEnabled(true); jTxtCortesia.setEnabled(true);
        if(limTiposPasaje==null){
            jTxtEstudiante.setText("0"); jTxtProfesor.setText("0");
            jTxtSenectud.setText("0"); jTxtCortesia.setText("0");
        }
        else{
            jTxtEstudiante.setText(limTiposPasaje[1]); jTxtProfesor.setText(limTiposPasaje[2]);
            jTxtSenectud.setText(limTiposPasaje[3]); jTxtCortesia.setText(limTiposPasaje[4]);
        }
    }
    
    private void HabilPorAnhadir(){
        jTxtNombreOfertaServicio.setEnabled(false);
        jCboServicio.setEnabled(false);
        jCboOrigenTramo.setEnabled(true);
        jCboDestinoTramo.setEnabled(true);
        jCboRuta.setEnabled(true);
        jCboEmpresa.setEnabled(true);
        jCboEmpresa.setSelectedIndex(0);
        jTxtHora.setEnabled(true); jTxtHora.setText("");
        jCkbLunes.setEnabled(true); jCkbMartes.setEnabled(true); jCkbMiercoles.setEnabled(true); jCkbJueves.setEnabled(true);
        jCkbViernes.setEnabled(true); jCkbSabado.setEnabled(true); jCkbDomingo.setEnabled(true);
        jCkbLunes.setSelected(false); jCkbMartes.setSelected(false); jCkbMiercoles.setSelected(false); jCkbJueves.setSelected(false);
        jCkbViernes.setSelected(false); jCkbSabado.setSelected(false); jCkbDomingo.setSelected(false);
        jTxtEstudiante.setEnabled(true); jTxtProfesor.setEnabled(true);
        jTxtSenectud.setEnabled(true); jTxtCortesia.setEnabled(true);
        if(limTiposPasaje==null){
            jTxtEstudiante.setText("0"); jTxtProfesor.setText("0");
            jTxtSenectud.setText("0"); jTxtCortesia.setText("0");
        }
        else{
            jTxtEstudiante.setText(limTiposPasaje[1]); jTxtProfesor.setText(limTiposPasaje[2]);
            jTxtSenectud.setText(limTiposPasaje[3]); jTxtCortesia.setText(limTiposPasaje[4]);
        }
        jCboRuta.requestFocusInWindow();
    }
    
    private void HabilPorModificacion(boolean datosBD){
        jTxtNombreOfertaServicio.setEnabled(false);
        jCboServicio.setEnabled(false);
        jCboEmpresa.setEnabled(true);
        jCboRuta.setEnabled(true);
        sesionVenta.cargaRelacionServicioRutas(jCboServicio.getSelectedItem().toString());
        auxCargaRelacionesServicioModificacion(datosBD);
        mCboEmpresas = new DefaultComboBoxModel(sesionVenta.getEmpresasV());
        jCboEmpresa.setModel(mCboEmpresas);
        jCboOrigenTramo.setEnabled(true);
        jCboDestinoTramo.setEnabled(true);
        jTxtHora.setEnabled(true);
        jCkbLunes.setEnabled(true); jCkbMartes.setEnabled(true); jCkbMiercoles.setEnabled(true); jCkbJueves.setEnabled(true);
        jCkbViernes.setEnabled(true); jCkbSabado.setEnabled(true); jCkbDomingo.setEnabled(true);
        jTxtEstudiante.setEnabled(true); jTxtProfesor.setEnabled(true);
        jTxtSenectud.setEnabled(true); jTxtCortesia.setEnabled(true);
        if(datosBD){
            jCboEmpresa.setSelectedItem(sesionVenta.getResultadosBusquedas().get(fila).getEmpresaNombre());
        }
        else
            jCboEmpresa.setSelectedItem(tmsOfertasServicioV.get(fila).getEmpresaNombre());
    }
    
    private void desHabilTodo(){
        jTxtNombreOfertaServicio.setEnabled(false);
        jCboServicio.setEnabled(false);
        jCboRuta.setEnabled(false);
        jCboOrigenTramo.setEnabled(false);
        jCboDestinoTramo.setEnabled(false);
        jCboEmpresa.setEnabled(false);
        jTxtHora.setEnabled(false);
        jCkbLunes.setEnabled(false); jCkbMartes.setEnabled(false); jCkbMiercoles.setEnabled(false); jCkbJueves.setEnabled(false);
        jCkbViernes.setEnabled(false); jCkbSabado.setEnabled(false); jCkbDomingo.setEnabled(false);
        jTxtEstudiante.setEnabled(false); jTxtProfesor.setEnabled(false);
        jTxtSenectud.setEnabled(false); jTxtCortesia.setEnabled(false);
    }
    
    private void limpieza(){
        desHabilPorBusq();
        xListado.setDataVector(null, encabezado);
        AnchoColumnas();
        jLblBarraEstado.setText(barraMensajes.getMsg(0));
        transaccion = txBusq;
        primerRegistro=true;
        this.jTxtNombreOfertaServicio.setText("%");
        tmsOfertasServicioV=null;
        jTxtNombreOfertaServicio.requestFocusInWindow();
    }
    
    private void CargaEncabezado(boolean infoBD){
        if(fila==-1){
            return;
        }
        if(infoBD){
            jTxtNombreOfertaServicio.setText(sesionVenta.getResultadosBusquedas().get(fila).getOfertaServicioNombre());
            jCboServicio.setSelectedItem(sesionVenta.getResultadosBusquedas().get(fila).getServicioNombre());
            jCboRuta.setSelectedItem(sesionVenta.getResultadosBusquedas().get(fila).getRutaNumero());
            jTxtRutaNombre.setText(sesionVenta.getResultadosBusquedas().get(fila).getRutaNombre());
            auxOrigenes=new Vector();
            auxOrigenes.add(sesionVenta.getResultadosBusquedas().get(fila).getOrigen());
            mCboOrigenes = new DefaultComboBoxModel(auxOrigenes);
            jCboOrigenTramo.setModel(mCboOrigenes);
            auxDestinos=new Vector();
            auxDestinos.add(sesionVenta.getResultadosBusquedas().get(fila).getDestino());
            mCboDestinos = new DefaultComboBoxModel(auxDestinos);
            jCboDestinoTramo.setModel(mCboDestinos);
            jCboEmpresa.setSelectedItem(sesionVenta.getResultadosBusquedas().get(fila).getEmpresaNombre());
            jTxtHora.setText(formatoHora.format(sesionVenta.getResultadosBusquedas().get(fila).getHoraCorrida()));
            jCkbLunes.setSelected(sesionVenta.getResultadosBusquedas().get(fila).getLunesAplica().equals("S") ? true : false);
            jCkbMartes.setSelected(sesionVenta.getResultadosBusquedas().get(fila).getMartesAplica().equals("S") ? true : false);
            jCkbMiercoles.setSelected(sesionVenta.getResultadosBusquedas().get(fila).getMiercolesAplica().equals("S") ? true : false);
            jCkbJueves.setSelected(sesionVenta.getResultadosBusquedas().get(fila).getJuevesAplica().equals("S") ? true : false);
            jCkbViernes.setSelected(sesionVenta.getResultadosBusquedas().get(fila).getViernesAplica().equals("S") ? true : false);
            jCkbSabado.setSelected(sesionVenta.getResultadosBusquedas().get(fila).getSabadoAplica().equals("S") ? true : false);
            jCkbDomingo.setSelected(sesionVenta.getResultadosBusquedas().get(fila).getDomingoAplica().equals("S") ? true : false);
            jTxtEstudiante.setText(String.valueOf(sesionVenta.getResultadosBusquedas().get(fila).getEstudiantesCorrida()));
            jTxtProfesor.setText(String.valueOf(sesionVenta.getResultadosBusquedas().get(fila).getProfesoresCorrida()));
            jTxtSenectud.setText(String.valueOf(sesionVenta.getResultadosBusquedas().get(fila).getSenectudCorrida()));
            jTxtCortesia.setText(String.valueOf(sesionVenta.getResultadosBusquedas().get(fila).getCortesiasCorrida()));
        }
        else{
            jTxtNombreOfertaServicio.setText(tmsOfertasServicioV.get(fila).getOfertaServicioNombre());
            jCboServicio.setSelectedItem(tmsOfertasServicioV.get(fila).getServicioNombre());
            jCboRuta.setSelectedItem(tmsOfertasServicioV.get(fila).getRutaNumero());
            jTxtRutaNombre.setText(tmsOfertasServicioV.get(fila).getRutaNombre());
            auxOrigenes=new Vector();
            auxOrigenes.add(tmsOfertasServicioV.get(fila).getOrigen());
            mCboOrigenes = new DefaultComboBoxModel(auxOrigenes);
            jCboOrigenTramo.setModel(mCboOrigenes);
            auxDestinos=new Vector();
            auxDestinos.add(tmsOfertasServicioV.get(fila).getDestino());
            mCboDestinos = new DefaultComboBoxModel(auxDestinos);
            jCboDestinoTramo.setModel(mCboDestinos);
            jCboEmpresa.setSelectedItem(tmsOfertasServicioV.get(fila).getEmpresaNombre());
            jTxtHora.setText(formatoHora.format(tmsOfertasServicioV.get(fila).getHoraCorrida()));
            jCkbLunes.setSelected(tmsOfertasServicioV.get(fila).getLunesAplica().equals("S") ? true : false);
            jCkbMartes.setSelected(tmsOfertasServicioV.get(fila).getMartesAplica().equals("S") ? true : false);
            jCkbMiercoles.setSelected(tmsOfertasServicioV.get(fila).getMiercolesAplica().equals("S") ? true : false);
            jCkbJueves.setSelected(tmsOfertasServicioV.get(fila).getJuevesAplica().equals("S") ? true : false);
            jCkbViernes.setSelected(tmsOfertasServicioV.get(fila).getViernesAplica().equals("S") ? true : false);
            jCkbSabado.setSelected(tmsOfertasServicioV.get(fila).getSabadoAplica().equals("S") ? true : false);
            jCkbDomingo.setSelected(tmsOfertasServicioV.get(fila).getDomingoAplica().equals("S") ? true : false);
            jTxtEstudiante.setText(String.valueOf(tmsOfertasServicioV.get(fila).getEstudiantesCorrida()));
            jTxtProfesor.setText(String.valueOf(tmsOfertasServicioV.get(fila).getProfesoresCorrida()));
            jTxtSenectud.setText(String.valueOf(tmsOfertasServicioV.get(fila).getSenectudCorrida()));
            jTxtCortesia.setText(String.valueOf(tmsOfertasServicioV.get(fila).getCortesiasCorrida()));
        }
    }
    
    private boolean validaHoras(){
        if(jTblDetalle.getRowCount()==0) return true;
        Timestamp hora, hi;
        try {
            hi = new Timestamp(formatoHora.parse(jTxtHora.getText()).getTime());
        } catch (ParseException ex) {
            return false;
        }
        int finalX=0;
        if(this.transaccion == txRegistrarNuevasLineas)
            finalX = jTblDetalle.getRowCount()-tmsOfertasServicioV.size();
        else
            finalX = jTblDetalle.getRowCount();
        
        //System.out.println(" --1-- "+finalX);
        for(int i=0; i<finalX; i++){
            try {
                hora=new Timestamp(formatoHora.parse(xListado.getValueAt(i,5).toString()).getTime());
            } catch (ParseException ex) {
                return false;
            }
            
            if(hi.getTime()==hora.getTime()){
                if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                   jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                    if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                        jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                            jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                        if(this.transaccion == txRegistrarNuevasLineas){
                            if(seIntersectanDias_ViejoNuevo(i, sesionVenta.getResultadosBusquedas())){
                                DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!",
                                        "Hay horarios que coinciden.", Color.RED);
                                return false;
                            }
                        }
                        else{
                            if(seIntersectanDias(i, tmsOfertasServicioV)){
                                DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", 
                                        "Hay horarios que coinciden.", Color.RED);
                                return false;
                            }
                        }
                    }
                }
            }
        }
        
        if(this.transaccion == txRegistrarNuevasLineas){
            //System.out.println(" --2-- "+tmsOfertasServicioV.size()+" --- finalX: "+finalX);
            for(int i=finalX, ji=0; i<jTblDetalle.getRowCount(); i++){
                try {
                    hora=new Timestamp(formatoHora.parse(xListado.getValueAt(i,5).toString()).getTime());
                } catch (ParseException ex) {
                    return false;
                }
                //System.out.println(hi+" --2-- "+hora+" --->i: "+i);
                if(hi.getTime()==hora.getTime()){
                    if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                       jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                        if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                            jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                                jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                                    if(seIntersectanDias(ji, tmsOfertasServicioV)){
                                        DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", 
                                                "Hay horarios que coinciden.", Color.RED);
                                        return false;
                                    }
                        }
                    }
                }
                ji++;
            }
        }
        
        return true;
    }
    
    private boolean validaHorasGenAuto(Timestamp hi, Timestamp hf, long lapso){
        if(jTblDetalle.getRowCount()==0) return true;
        Timestamp hora;
        int finalX=0;
        if(this.transaccion == txRegistrarNuevasLineas)
            finalX = jTblDetalle.getRowCount()-tmsOfertasServicioV.size();
        else
            finalX = jTblDetalle.getRowCount();
        
        while(hi.getTime()<=hf.getTime()){
            for(int i=0; i<finalX; i++){
                try {
                    hora=new Timestamp(formatoHora.parse(xListado.getValueAt(i,5).toString()).getTime());
                } catch (ParseException ex) {
                    return false;
                }
                System.out.println("hora calc: "+hi+" --1-- hora guardada: "+hora+" --->i: "+i+" ---> lapso: "+lapso);
                if(hi.getTime()==hora.getTime()){
                    if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                       jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                        if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                            jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                                jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                                if(this.transaccion == txRegistrarNuevasLineas){
                                    if(seIntersectanDias_ViejoNuevo(i, sesionVenta.getResultadosBusquedas())){
                                        DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", 
                                                "Hay horarios que coinciden.", Color.RED);
                                        return false;
                                    }
                                }
                                else{
                                    if(seIntersectanDias(i, tmsOfertasServicioV)){
                                        DialogoAceptar = new JDlgAceptar("¡No se pueden generar ofertas automaticas!", 
                                                "Hay horarios que coinciden.", Color.RED);
                                        return false;
                                    }
                                }
                        }
                    }
                }
            }

            if(this.transaccion == txRegistrarNuevasLineas){
                for(int i=finalX, ji=0; i<jTblDetalle.getRowCount(); i++){
                    try {
                        hora=new Timestamp(formatoHora.parse(xListado.getValueAt(i,5).toString()).getTime());
                    } catch (ParseException ex) {
                        return false;
                    }
                    System.out.println(hi+" --2-- "+hora+" --->i: "+i+" ---> lapso: "+lapso);
                    if(hi.getTime()==hora.getTime()){
                        if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                           jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                            if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                                jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                                    jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                                        if(seIntersectanDias(ji, tmsOfertasServicioV)){
                                            DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", 
                                                    "Hay horarios que coinciden.", Color.RED);
                                            return false;
                                        }
                            }
                        }
                    }
                    ji++;
                }
            }
            hi.setTime(hi.getTime()+lapso);
        }
        return true;
    }
    
    private boolean validaDatosEntrada(){
        int error=0;
        if(jTxtNombreOfertaServicio.getText().equals("")) error = -1;
        else
        if(jTxtNombreOfertaServicio.getText().length()>29) error = -15;
        else
        if(jTxtHora.getText().equals("")) error = -2;
        else
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
        if(jCboEmpresa.getSelectedItem()==null || jCboEmpresa.getSelectedItem().toString().equals("TODOS")) error = -11;
        else
        if(jCboServicio.getSelectedItem()==null) error = -12;
        else
        if(jCboOrigenTramo.getSelectedItem().equals(jCboDestinoTramo.getSelectedItem())) error = -13;
        else
        if(!jCkbLunes.isSelected() && !jCkbMartes.isSelected() && !jCkbMiercoles.isSelected() && !jCkbJueves.isSelected()
            && !jCkbViernes.isSelected() && !jCkbSabado.isSelected() && !jCkbDomingo.isSelected()) error=-14;
        
        switch(error){
            case -1:
            DialogoAceptar = new JDlgAceptar("¡Ingrese nombre de oferta!","Presione ENTER para continuar", Color.RED);
            jTxtNombreOfertaServicio.requestFocusInWindow();
            break;
            case -2:
            DialogoAceptar = new JDlgAceptar("¡Ingrese hora!","Presione ENTER para continuar", Color.RED);
            jTxtHora.requestFocusInWindow();
            break;
            case -4:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de estudiantes permitidos!","Presione ENTER para continuar", Color.RED);
            jTxtEstudiante.requestFocusInWindow();
            break;
            case -5:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de profesores permitidos!","Presione ENTER para continuar", Color.RED);
            jTxtProfesor.requestFocusInWindow();
            break;
            case -6:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de senectud permitidos!","Presione ENTER para continuar", Color.RED);
            jTxtSenectud.requestFocusInWindow();
            break;
            case -7:
            DialogoAceptar = new JDlgAceptar("¡Ingrese cantidad de especial permitidas!","Presione ENTER para continuar", Color.RED);
            jTxtCortesia.requestFocusInWindow();
            break;
            case -8:
            DialogoAceptar = new JDlgAceptar("¡Ingrese numero de ruta!","Presione ENTER para continuar", Color.RED);
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
            DialogoAceptar = new JDlgAceptar("¡Ingrese diferente origen destino!","Valor repetido.", Color.RED);
            jCboDestinoTramo.requestFocusInWindow();
            break;
            case -14:
            DialogoAceptar = new JDlgAceptar("¡Es obligatorio ingresar dias!","Ingrese al menos un dia en que se aplica la oferta.", Color.RED);
            jCkbLunes.requestFocusInWindow();
            break;
            case -15:
            DialogoAceptar = new JDlgAceptar("¡Nombre de oferta invalido!","Ingrese maximo 30 caracteres.", Color.RED);
            jTxtNombreOfertaServicio.requestFocusInWindow();
            break;
        }
        
        if(error!=0) return false;
        
        if(!sesionVenta.validaServicioEmpresa(jCboServicio.getSelectedItem().toString(), jCboRuta.getSelectedItem().toString(), jCboEmpresa.getSelectedItem().toString())){
            DialogoAceptar = new JDlgAceptar("Oferta de Servicio.","<html>El servicio, ruta y empresa seleccionados no corresponden<br>a una configuracion valida.</html>", Color.RED);
            return false;
        }
        
        return true;
    }
    
    private boolean validaCupoTipos(){
        //obtiene plantilla
        long[] z = sesionVenta.getPlantillaDefaultRuta(jCboRuta.getSelectedItem().toString());
        if(z==null){
            DialogoAceptar = new JDlgAceptar("¡Error de configuracion!", "La ruta no tiene relacionada una plantilla.", Color.RED);
            return false;
        }
        plantillaId = z[0];
        cupo = z[1];
        int suma=0;
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
    
    private boolean ActualizaLinea(boolean infoBD){
        Timestamp hora1, hora2;
        try {
            hora1 = new Timestamp(formatoHora.parse(jTxtHora.getText()).getTime());
        } catch (ParseException ex) {
            DialogoAceptar = new JDlgAceptar("¡El valor del campo hora es incorrecto!","Presione ENTER para continuar", Color.RED);
            jTxtHora.requestFocusInWindow();
            return false;
        }
        if(infoBD){
            //if(!sesionVenta.validaHora(jCboServicio.getSelectedItem().toString(), jCboRuta.getSelectedItem().toString(), hora1)){
            long ofId=sesionVenta.validaHoraModificada(sesionVenta.getResultadosBusquedas().get(fila).getOfertaServicioNombre(),
                sesionVenta.getResultadosBusquedas().get(fila).getOfertaServicioId(), hora1,
                sesionVenta.getOrigenDestinoId(jCboOrigenTramo.getSelectedItem().toString()),
                sesionVenta.getOrigenDestinoId(jCboDestinoTramo.getSelectedItem().toString()),
                sesionVenta.getEmpresaId(jCboEmpresa.getSelectedItem().toString()));
            if(ofId>-1){
                int filaCorrectaAModificar = obtengoFilaAModificar(ofId);
                if(filaCorrectaAModificar == -1){
                    DialogoAceptar = new JDlgAceptar("¡No es posible modificar la hora!", "Modifique otros campos.", Color.RED);
                    jTxtHora.requestFocusInWindow();
                    return false;
                }
                if(seIntersectanDias(filaCorrectaAModificar, sesionVenta.getResultadosBusquedas())){
                    DialogoAceptar = new JDlgAceptar("¡No se puede modificar la oferta!", "Ya existe una oferta con la misma hora dentro del grupo.", Color.RED);
                    jTxtHora.requestFocusInWindow();
                    return false;
                }
            }
        }
        else{
            for(int i=0; i<jTblDetalle.getRowCount(); i++)
                if(i!=fila){
                    try {
                        hora2=new Timestamp(formatoHora.parse(xListado.getValueAt(i,5).toString()).getTime());
                    } catch (ParseException ex) {
                        return false;
                    }
                    if(hora1.getTime()==hora2.getTime()){
                        /*if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                           jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString()) &&
                            jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){*/
                        if(jCboServicio.getSelectedItem().toString().equals(xListado.getValueAt(i,0).toString()) &&
                           jCboRuta.getSelectedItem().toString().equals(xListado.getValueAt(i,1).toString())){
                            if(jCboOrigenTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,2).toString()) &&
                                jCboDestinoTramo.getSelectedItem().toString().equals(xListado.getValueAt(i,3).toString()) &&
                                    jCboEmpresa.getSelectedItem().toString().equals(xListado.getValueAt(i,4).toString())){
                                if(seIntersectanDias(i, tmsOfertasServicioV)){
                                    DialogoAceptar = new JDlgAceptar("¡No se puede agregar la nueva oferta!", 
                                            /*"<html>La hora de la oferta <font color=ff0000>"+(i+1)+"</font> coincide con la hora<br>de la oferta actual (<font color=ff0000>"+
                                            formatoHora.format(hora1)+"</font>).<html>", Color.RED);*/
                                            "Hay horarios que coinciden.", Color.RED);
                                    jTxtHora.requestFocusInWindow();
                                    return false;
                                }
                            }
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
        String horario = jTxtHora.getText()+" ";
        horario = jCkbLunes.isSelected() ? horario+"Lun" : horario;
        horario = jCkbMartes.isSelected() ? horario+"Mar" : horario;
        horario = jCkbMiercoles.isSelected() ? horario+"Mie" : horario;
        horario = jCkbJueves.isSelected() ? horario = horario+"Jue" : horario;
        horario = jCkbViernes.isSelected() ? horario = horario+"Vie" : horario;
        horario = jCkbSabado.isSelected() ? horario = horario+"Sab" : horario;
        horario = jCkbDomingo.isSelected() ? horario = horario+"Dom" : horario;
        xListado.setValueAt(horario, fila, 5);
        if(infoBD){
            sesionVenta.getResultadosBusquedas().get(fila).setOfertaServicioNombre(jTxtNombreOfertaServicio.getText());
            sesionVenta.getResultadosBusquedas().get(fila).setServicioNombre(jCboServicio.getSelectedItem().toString());
            sesionVenta.getResultadosBusquedas().get(fila).setServicioId(sesionVenta.getServicioId(sesionVenta.getResultadosBusquedas().get(fila).getServicioNombre()));
            sesionVenta.getResultadosBusquedas().get(fila).setRutaNumero(xListado.getValueAt(fila,1).toString());
            sesionVenta.getResultadosBusquedas().get(fila).setRutaId(sesionVenta.getRutaId(sesionVenta.getResultadosBusquedas().get(fila).getRutaNumero()));
            sesionVenta.getResultadosBusquedas().get(fila).setRutaNombre(jTxtRutaNombre.getText());
            sesionVenta.getResultadosBusquedas().get(fila).setOrigen(xListado.getValueAt(fila,2).toString());
            sesionVenta.getResultadosBusquedas().get(fila).setOrigenId(sesionVenta.getOrigenDestinoId(sesionVenta.getResultadosBusquedas().get(fila).getOrigen()));
            sesionVenta.getResultadosBusquedas().get(fila).setDestino(xListado.getValueAt(fila,3).toString());
            sesionVenta.getResultadosBusquedas().get(fila).setDestinoId(sesionVenta.getOrigenDestinoId(sesionVenta.getResultadosBusquedas().get(fila).getDestino()));
            sesionVenta.getResultadosBusquedas().get(fila).setEmpresaNombre(xListado.getValueAt(fila,4).toString());
            sesionVenta.getResultadosBusquedas().get(fila).setEmpresaId(sesionVenta.getEmpresaId(sesionVenta.getResultadosBusquedas().get(fila).getEmpresaNombre()));
            try {    
                hora = new Timestamp(formatoHora.parse(xListado.getValueAt(fila, 5).toString()).getTime());
            } catch (ParseException ex) {
                ;
            }
            sesionVenta.getResultadosBusquedas().get(fila).setHoraCorrida(hora);
            sesionVenta.getResultadosBusquedas().get(fila).setLunesAplica(jCkbLunes.isSelected() ? "S" : "N");
            sesionVenta.getResultadosBusquedas().get(fila).setMartesAplica(jCkbMartes.isSelected() ? "S" : "N");
            sesionVenta.getResultadosBusquedas().get(fila).setMiercolesAplica(jCkbMiercoles.isSelected() ? "S" : "N");
            sesionVenta.getResultadosBusquedas().get(fila).setJuevesAplica(jCkbJueves.isSelected() ? "S" : "N");
            sesionVenta.getResultadosBusquedas().get(fila).setViernesAplica(jCkbViernes.isSelected() ? "S" : "N");
            sesionVenta.getResultadosBusquedas().get(fila).setSabadoAplica(jCkbSabado.isSelected() ? "S" : "N");
            sesionVenta.getResultadosBusquedas().get(fila).setDomingoAplica(jCkbDomingo.isSelected() ? "S" : "N");
            sesionVenta.getResultadosBusquedas().get(fila).setEstudiantesCorrida(Long.valueOf(jTxtEstudiante.getText()));
            sesionVenta.getResultadosBusquedas().get(fila).setProfesoresCorrida(Long.valueOf(jTxtProfesor.getText()));
            sesionVenta.getResultadosBusquedas().get(fila).setSenectudCorrida(Long.valueOf(jTxtSenectud.getText()));
            sesionVenta.getResultadosBusquedas().get(fila).setCortesiasCorrida(Long.valueOf(jTxtCortesia.getText()));
        }
        else{
            tmsOfertasServicioV.get(fila).setOfertaServicioNombre(jTxtNombreOfertaServicio.getText());
            tmsOfertasServicioV.get(fila).setServicioNombre(jCboServicio.getSelectedItem().toString());
            tmsOfertasServicioV.get(fila).setServicioId(sesionVenta.getServicioId(tmsOfertasServicioV.get(fila).getServicioNombre()));
            tmsOfertasServicioV.get(fila).setRutaNumero(xListado.getValueAt(fila,1).toString());
            tmsOfertasServicioV.get(fila).setRutaId(sesionVenta.getRutaId(tmsOfertasServicioV.get(fila).getRutaNumero()));
            tmsOfertasServicioV.get(fila).setRutaNombre(jTxtRutaNombre.getText());
            tmsOfertasServicioV.get(fila).setOrigen(xListado.getValueAt(fila,2).toString());
            tmsOfertasServicioV.get(fila).setOrigenId(sesionVenta.getOrigenDestinoId(tmsOfertasServicioV.get(fila).getOrigen()));
            tmsOfertasServicioV.get(fila).setDestino(xListado.getValueAt(fila,3).toString());
            tmsOfertasServicioV.get(fila).setDestinoId(sesionVenta.getOrigenDestinoId(tmsOfertasServicioV.get(fila).getDestino()));
            tmsOfertasServicioV.get(fila).setEmpresaNombre(xListado.getValueAt(fila,4).toString());
            tmsOfertasServicioV.get(fila).setEmpresaId(sesionVenta.getEmpresaId(tmsOfertasServicioV.get(fila).getEmpresaNombre()));
            try {    
                hora = new Timestamp(formatoHora.parse(xListado.getValueAt(fila, 5).toString()).getTime());
            } catch (ParseException ex) {
                ;
            }
            tmsOfertasServicioV.get(fila).setHoraCorrida(hora);
            tmsOfertasServicioV.get(fila).setLunesAplica(jCkbLunes.isSelected() ? "S" : "N");
            tmsOfertasServicioV.get(fila).setMartesAplica(jCkbMartes.isSelected() ? "S" : "N");
            tmsOfertasServicioV.get(fila).setMiercolesAplica(jCkbMiercoles.isSelected() ? "S" : "N");
            tmsOfertasServicioV.get(fila).setJuevesAplica(jCkbJueves.isSelected() ? "S" : "N");
            tmsOfertasServicioV.get(fila).setViernesAplica(jCkbViernes.isSelected() ? "S" : "N");
            tmsOfertasServicioV.get(fila).setSabadoAplica(jCkbSabado.isSelected() ? "S" : "N");
            tmsOfertasServicioV.get(fila).setDomingoAplica(jCkbDomingo.isSelected() ? "S" : "N");
            
            tmsOfertasServicioV.get(fila).setEstudiantesCorrida(Long.valueOf(jTxtEstudiante.getText()));
            tmsOfertasServicioV.get(fila).setProfesoresCorrida(Long.valueOf(jTxtProfesor.getText()));
            tmsOfertasServicioV.get(fila).setSenectudCorrida(Long.valueOf(jTxtSenectud.getText()));
            tmsOfertasServicioV.get(fila).setCortesiasCorrida(Long.valueOf(jTxtCortesia.getText()));
        }
    }
 
    private boolean agregarLinea(int noSalidas, long Frecuencia){
        Object[] filaDatos;
        Timestamp hora;
        long tiempo;
        for(int i=0; i<noSalidas; i++){
            filaDatos = new Object[6];
            filaDatos[0] = jCboServicio.getSelectedItem().toString();
            filaDatos[1] = jCboRuta.getSelectedItem().toString();
            filaDatos[2] = jCboOrigenTramo.getSelectedItem().toString();
            filaDatos[3] = jCboDestinoTramo.getSelectedItem().toString();
            filaDatos[4] = jCboEmpresa.getSelectedItem().toString();
            OfertaServicio = new TmsOfertasServicioV();
            OfertaServicio.setOfertaServicioNombre(jTxtNombreOfertaServicio.getText());
            OfertaServicio.setServicioNombre(jCboServicio.getSelectedItem().toString());
            OfertaServicio.setServicioId(sesionVenta.getServicioId(OfertaServicio.getServicioNombre()));
            OfertaServicio.setRutaNumero(filaDatos[1].toString());
            OfertaServicio.setRutaId(sesionVenta.getRutaId(OfertaServicio.getRutaNumero()));
            OfertaServicio.setRutaNombre(jTxtRutaNombre.getText());
            OfertaServicio.setOrigen(filaDatos[2].toString());
            OfertaServicio.setOrigenId(sesionVenta.getOrigenDestinoId(OfertaServicio.getOrigen()));
            OfertaServicio.setDestino(filaDatos[3].toString());
            OfertaServicio.setDestinoId(sesionVenta.getOrigenDestinoId(OfertaServicio.getDestino()));
            OfertaServicio.setEmpresaNombre(filaDatos[4].toString());
            OfertaServicio.setEmpresaId(sesionVenta.getEmpresaId(OfertaServicio.getEmpresaNombre()));
            try {
                tiempo = formatoHora.parse(jTxtHora.getText()).getTime()+(Frecuencia*i);
            } catch (ParseException ex) {
                return false;
            }
            hora = new Timestamp(tiempo);
            OfertaServicio.setHoraCorrida(hora);
            OfertaServicio.setLunesAplica(jCkbLunes.isSelected() ? "S" : "N");
            OfertaServicio.setMartesAplica(jCkbMartes.isSelected() ? "S" : "N");
            OfertaServicio.setMiercolesAplica(jCkbMiercoles.isSelected() ? "S" : "N");
            OfertaServicio.setJuevesAplica(jCkbJueves.isSelected() ? "S" : "N");
            OfertaServicio.setViernesAplica(jCkbViernes.isSelected() ? "S" : "N");
            OfertaServicio.setSabadoAplica(jCkbSabado.isSelected() ? "S" : "N");
            OfertaServicio.setDomingoAplica(jCkbDomingo.isSelected() ? "S" : "N");
            
            OfertaServicio.setEstudiantesCorrida(Long.valueOf(jTxtEstudiante.getText()));
            OfertaServicio.setProfesoresCorrida(Long.valueOf(jTxtProfesor.getText()));
            OfertaServicio.setSenectudCorrida(Long.valueOf(jTxtSenectud.getText()));
            OfertaServicio.setCortesiasCorrida(Long.valueOf(jTxtCortesia.getText()));
            
            tmsOfertasServicioV.add(OfertaServicio);
            
            // codigo nuevo
            String horario = formatoHora.format(hora)+" ";
            horario = jCkbLunes.isSelected() ? horario+"Lun" : horario;
            horario = jCkbMartes.isSelected() ? horario+"Mar" : horario;
            horario = jCkbMiercoles.isSelected() ? horario+"Mie" : horario;
            horario = jCkbJueves.isSelected() ? horario = horario+"Jue" : horario;
            horario = jCkbViernes.isSelected() ? horario = horario+"Vie" : horario;
            horario = jCkbSabado.isSelected() ? horario = horario+"Sab" : horario;
            horario = jCkbDomingo.isSelected() ? horario = horario+"Dom" : horario;
            //
            filaDatos[5] = horario;
            xListado.addRow(filaDatos);
            AnchoColumnas();
        }
        return true;
    }

    private void cargaParametros() {
        //if(!jCboServicio.getSelectedItem().toString().equals(strServicioAnterior)){
        jTxtEstudiante.setText(sesionVenta.getValorParametro("P_LIMESTCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));
        jTxtProfesor.setText(sesionVenta.getValorParametro("P_LIMPROCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));
        jTxtSenectud.setText(sesionVenta.getValorParametro("P_LIMSENCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));
        jTxtCortesia.setText(sesionVenta.getValorParametro("P_LIMCORCOR"+sesionVenta.getServicioId(jCboServicio.getSelectedItem().toString()), -1));

        limTiposPasaje = new String[5];
        limTiposPasaje[0] = "0";
        limTiposPasaje[1] = jTxtEstudiante.getText();
        limTiposPasaje[2] = jTxtProfesor.getText();
        limTiposPasaje[3] = jTxtSenectud.getText();
        limTiposPasaje[4] = jTxtCortesia.getText();
        //}
    }

    private void inhabilitarF10() {
        this.jTxtNombreOfertaServicio.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
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
        this.jTxtHora.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbLunes.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbMartes.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbMiercoles.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbJueves.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbViernes.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbSabado.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jCkbDomingo.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtEstudiante.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtProfesor.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtSenectud.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTxtCortesia.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
    }

    private boolean seIntersectanDias(int ixRegistroExistente, List<TmsOfertasServicioV> osv) {
        //System.out.println("ix "+ixRegistroExistente+" - osv "+osv.size());
        String aplicaDia=null;
        aplicaDia=osv.get(ixRegistroExistente).getLunesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbLunes.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getMartesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbMartes.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getMiercolesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbMiercoles.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getJuevesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbJueves.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getViernesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbViernes.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getSabadoAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbSabado.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getDomingoAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbDomingo.isSelected() ? "S" : "N")) return true;
        return false;
    }
    
    private boolean seIntersectanDias_ViejoNuevo(int ixRegistroExistente, List<TmsOfertasServicioV> osv) {
        //System.out.println("ix "+ixRegistroExistente+" - osv "+osv.size());
        String aplicaDia=null;
        aplicaDia=osv.get(ixRegistroExistente).getLunesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbLunes.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getMartesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbMartes.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getMiercolesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbMiercoles.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getJuevesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbJueves.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getViernesAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbViernes.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getSabadoAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbSabado.isSelected() ? "S" : "N")) return true;
        aplicaDia=osv.get(ixRegistroExistente).getDomingoAplica();
        if(aplicaDia.equals("S") &&
                aplicaDia.equals(jCkbDomingo.isSelected() ? "S" : "N")) return true;
        return false;
    }

    private int obtengoFilaAModificar(long ofId) {
        for(int i=0; i<jTblDetalle.getRowCount(); i++)
            if(sesionVenta.getResultadosBusquedas().get(i).getOfertaServicioId()==ofId) return i;
        return -1;
    }

    private boolean cargarOfertaDeServicio() {
        System.out.println("ofertaServicio_Nombre::: "+ofertaServicio_Nombre);
        if((datos=sesionVenta.busqueda(ofertaServicio_Nombre, "TODOS", "TODOS"))==null){
            DialogoAceptar = new JDlgAceptar("¡La busqueda no genero resultados!", "Presione ENTER para continuar", Color.RED);
            jLblBarraEstado.setText(barraMensajes.getMsg(0));
            return false;
        }
        jLblBarraEstado.setText(barraMensajes.getMsg(2));
        xListado.setDataVector(datos, encabezado);
        AnchoColumnas();
        fila=0;
        CargaEncabezado(true);
        desHabilTodo();
        transaccion=txResul;
        jTblDetalle.setRowSelectionInterval(0,0);
        jTblDetalle.setColumnSelectionInterval(0,0);
        jTblDetalle.requestFocusInWindow();
        return true;
    }
    
    public void guardarNuevaLineaOferta(){
        if(transaccion!=txRegistrarNuevasLineas) return;
        // GUARDAR NUEVAS LINEAS
        if(tmsOfertasServicioV.size()==0){
            DialogoAceptar = new JDlgAceptar("¡Aviso!", "¡No existen ofertas nuevas para ser guardadas!", Color.RED);
            return;
        }

        DialogoSiNoCancelar = new JDlgSiNoCancelar("¡Confirme!","¿Desea agregar las nuevas ofertas.");
        switch(DialogoSiNoCancelar.getResultado()){
            case 3: return;
            case 2: if(!cargarOfertaDeServicio()) return;
        }
        if(tmsOfertasServicioV.size()>15){
            DialogoAceptar = new JDlgAceptar("¡Importante!","<html>Es posible que el proceso de guardar<br><font color=ff0000>"+
                    jTblDetalle.getRowCount()+"</font> ofertas tarde varios segundos.</html>", Color.ORANGE);
        }
        if(!sesionVenta.guardarOfertas(tmsOfertasServicioV))
            DialogoAceptar = new JDlgAceptar("¡No fue posible guardar las nuevas ofertas!","Contacte al administrador del sistem.", Color.RED);
        else
            DialogoAceptar = new JDlgAceptar("¡Las nuevas ofertas se guardaron correctamente!","Presione ENTER para continuar.", colorDialogoActivo);
        // regreso a listado
        if(!cargarOfertaDeServicio()) return;
    }
    
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCboDestinoTramo;
    private javax.swing.JComboBox jCboEmpresa;
    private javax.swing.JComboBox jCboOrigenTramo;
    private javax.swing.JComboBox jCboRuta;
    private javax.swing.JComboBox jCboServicio;
    private javax.swing.JCheckBox jCkbDomingo;
    private javax.swing.JCheckBox jCkbJueves;
    private javax.swing.JCheckBox jCkbLunes;
    private javax.swing.JCheckBox jCkbMartes;
    private javax.swing.JCheckBox jCkbMiercoles;
    private javax.swing.JCheckBox jCkbSabado;
    private javax.swing.JCheckBox jCkbViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JLabel jLblEmpresa;
    private javax.swing.JLabel jLblHorario;
    private javax.swing.JLabel jLblNombreOfertaServicio;
    private javax.swing.JLabel jLblRuta;
    private javax.swing.JLabel jLblServicio;
    private javax.swing.JLabel jLblTramoDestino;
    private javax.swing.JLabel jLblTramoOrigen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScpDetalle;
    private javax.swing.JTable jTblDetalle;
    private javax.swing.JTextField jTxtCortesia;
    private javax.swing.JTextField jTxtEstudiante;
    private javax.swing.JTextField jTxtHora;
    private javax.swing.JTextField jTxtNombreOfertaServicio;
    private javax.swing.JTextField jTxtProfesor;
    private javax.swing.JTextField jTxtRutaNombre;
    private javax.swing.JTextField jTxtSenectud;
    // End of variables declaration//GEN-END:variables
    private Vector datosIniciales = new Vector();
    private long MENU_ID=282;
    private long USUARIO_ID=282;
    private String USUARIO_NUMERO;
    private String USUARIO_NOMBRE;
    private long SESION_ID=282;
    
    private final int txBusq = 0;
    private final int txResul = 1;
    private final int txRegistrar = 2;
    private final int txModificarAlta = 3;
    private final int txEliminar = 4;
    private final int txModificar = 5;
    private final int txRegistrarNuevasLineas = 6;
    private Object[] encabezado = {"Servicio", "Ruta", "Origen", "Destino", "Empresa", "Horario"};
    private DefaultTableModel xListado = new DefaultTableModel(null, encabezado){
            public boolean isCellEditable(int row, int col) {
                return false;
            }   
    };
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
    private int fila=-1;
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
    private List<TmsOfertasServicioV> tmsOfertasServicioV=null;
    private TmsOfertasServicioV OfertaServicio=null;
    private Color colorDialogoActivo = new Color(0,83,255);
    private boolean temporal=false;
    private boolean inicioGral;
    private long plantillaId;
    private long cupo;
    private String[] limTiposPasaje;
    private JDlgCargaOferta jDlgCargaOferta;
    private String ofertaServicio_Nombre;
    private KeyEvent eventoTeclado;
    private boolean avisameSINuevaLinea;
}