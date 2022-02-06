/*
 * JIFTmsBloqueoPorLote.java
 *
 * Created on 29 de julio de 2009, 01:20 PM
 */
/*TMSJABL*/
package tmsbloqueoporlote;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import tms_TextFields.JHourTextField;
import tms_bloqueo.entidad.TmsAutobusPlantLineasTbl;
import tms_bloqueo.entidad.TmsAutobusPlantillasEncTbl;
import tms_bloqueo.entidad.TmsComponenteBusTbl;
import tms_bloqueo.entidad.TmsEmpresasTbl;
import tms_bloqueo.entidad.TmsRutasTbl;
import tms_bloqueo.solicitud.TmsBloqueoPorLoteFacadeRemote;
import tms_calendario.JDateChooser;

/**
 *
 * @author  asolis
 */
public class JIFTmsBloqueoPorLote extends /*javax.swing.JFrame{//*/javax.swing.JInternalFrame {
    private Context cont = null;
    private TmsBloqueoPorLoteFacadeRemote busqueda;
    private DefaultComboBoxModel cbmEmpresas;
    private DefaultComboBoxModel cbmServicio;
    private DefaultComboBoxModel cbmRuta;    
    private Vector rutas;
    private Vector empresas;
    private Vector servicios;
    private DefaultTableModel corridasTable;
    private Vector corridasHeader = new Vector();
    public Vector corridasData = null;
    private JPNL_Bus JPnlbus;
    private List<TmsComponenteBusTbl> componentes;
    private List<TmsAutobusPlantillasEncTbl> encabezados;
    private List<TmsAutobusPlantLineasTbl> lineas;
    private long PLANTILLA_DEFAULT;
    private Vector asientos;
    private Vector corridasId;
    private String dblink ="";
    private Vector datosIniciales;
    private Vector fechaHora;
    private String plantillaId;
    private KeyEvent eventoTeclado;    
    /** Creates new form JIFTmsBloqueoPorLote */
    public JIFTmsBloqueoPorLote(Vector datosIniciales) {
        this.datosIniciales = datosIniciales;                
        iniciarConexion();
        cargaInicial();
        initComponents();
        this.setTitle("Bloqueo de Asientos por Lote");
        jlbl_rev.setText("Rev 05.05.10");
        jta_resumen.setEditable(false);
        cbmServicio = new DefaultComboBoxModel(servicios);
        cbmServicio.insertElementAt("Seleccione un Servicio", 0);
        jcmb_servicio.setModel(cbmServicio);
        jcmb_servicio.setSelectedIndex(0);        
        cargaEmpresa();
        jdc_desde.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
            if(jdc_desde.isEnabled()) {
                if(evt.getKeyCode() == evt.VK_DOWN){
                    jdc_hasta.dateEditor.setFoco();
                }
                if(evt.getKeyCode() == evt.VK_UP){
                    jdc_desde.dateEditor.setEnabled(false);
                    jdc_hasta.dateEditor.setEnabled(false);
                    jdc_desde.setEnabled(false);
                    jdc_hasta.setEnabled(false);
                    jht_desde.setEnabled(false);
                    jht_hasta.setEnabled(false);
                    jht_desde.setText("");
                    jht_hasta.setText("");
                    jdc_desde.dateEditor.setTexto("");
                    jdc_hasta.dateEditor.setTexto("");
                    jcmb_empresa.requestFocus();
                }
                if(evt.getKeyCode() == evt.VK_ENTER){
                    consultarCorridas();
                }
                otrasFunciones(evt);
            }
        }
        });
        jdc_hasta.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {                
            if(jdc_hasta.isEnabled()) {
                if(evt.getKeyCode() == evt.VK_DOWN){
                    jht_desde.requestFocus();
                }
                if(evt.getKeyCode() == evt.VK_UP){
                    jdc_desde.dateEditor.setFoco();
                }
                if(evt.getKeyCode() == evt.VK_ENTER){
                    consultarCorridas();
                }                                
                otrasFunciones(evt);
            }
        }
        });        
        corridasTable = new DefaultTableModel(null, corridasHeader){
            public boolean isCellEditable(int row, int col) {
                return false;
            }            
        };
        jtbl_corridas.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Bloqueos por Lote| <font color=FF0000>ENTER</font> Buscar Corridas |<font color=FF0000> « », ARRIBA/ABAJO</font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");  

    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        JPnlAutobus = new javax.swing.JPanel();
        JPnlbus = new JPNL_Bus(this.getComponentes(), this.getEncabezado(this.getPLANTILLA_DEFAULT()), this.getLineas(this.getPLANTILLA_DEFAULT()));
        JPnlbus.setVisibleOcupado(false);
        JPnlbus.setVisibleReservado(true);
        JPnlbus.setVisibleReservadoNC(true);
        JPnlAutobus.add(JPnlbus,
            new GridBagConstraints(0, 0, 0, 0, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        JPnlAutobus.setBackground(java.awt.Color.white);
        JPnlAutobus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JPnlAutobus.setFocusable(false);

        jpnl_busqueda = new javax.swing.JPanel();
        jcmb_servicio = new javax.swing.JComboBox();
        jlbl_servicio = new javax.swing.JLabel();
        jlbl_ruta = new javax.swing.JLabel();
        jcmb_ruta = new javax.swing.JComboBox();
        jlbl_empresa = new javax.swing.JLabel();
        jcmb_empresa = new javax.swing.JComboBox();
        jpnl_fecha = new javax.swing.JPanel();
        jlbl_desde = new javax.swing.JLabel();
        jlbl_hasta = new javax.swing.JLabel();
        jdc_desde = new tms_calendario.JDateChooser();
        jdc_hasta = new tms_calendario.JDateChooser();
        jpnl_hora = new javax.swing.JPanel();
        jht_desde = new tms_TextFields.JHourTextField();
        jlbl_hdesde = new javax.swing.JLabel();
        jlbl_hhasta = new javax.swing.JLabel();
        jht_hasta = new tms_TextFields.JHourTextField();
        jscp_corridas = new javax.swing.JScrollPane();
        jtbl_corridas = new javax.swing.JTable();
        jlbl_asientos = new javax.swing.JLabel();
        jtxt_asientos = new jtxtfieldasientos.JTxtFieldAsientos();
        jlbl_barraEstado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jta_resumen = new javax.swing.JTextArea();
        jlbl_rev = new javax.swing.JLabel();

        setIconifiable(true);
        setTitle("Bloqueo Por Lote");
        setFrameIcon(null);
        setMaximumSize(new java.awt.Dimension(976, 735));
        setMinimumSize(new java.awt.Dimension(976, 735));
        setPreferredSize(new java.awt.Dimension(976, 735));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        JPnlAutobus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Plantilla de la Ruta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        JPnlAutobus.setMaximumSize(new java.awt.Dimension(248, 431));
        JPnlAutobus.setPreferredSize(new java.awt.Dimension(248, 431));
        org.jdesktop.layout.GroupLayout JPnlAutobusLayout = new org.jdesktop.layout.GroupLayout(JPnlAutobus);
        JPnlAutobus.setLayout(JPnlAutobusLayout);
        JPnlAutobusLayout.setHorizontalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 232, Short.MAX_VALUE)
        );
        JPnlAutobusLayout.setVerticalGroup(
            JPnlAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 445, Short.MAX_VALUE)
        );

        jpnl_busqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de B\u00fasqueda", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 11)));
        jcmb_servicio.setBackground(new java.awt.Color(255, 255, 204));
        jcmb_servicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Servicio" }));
        jcmb_servicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_servicioKeyPressed(evt);
            }
        });

        jlbl_servicio.setText("Servicio: ");

        jlbl_ruta.setText("Ruta: ");

        jcmb_ruta.setBackground(new java.awt.Color(255, 255, 204));
        jcmb_ruta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selccione una Ruta" }));
        jcmb_ruta.setEnabled(false);
        jcmb_ruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_rutaKeyPressed(evt);
            }
        });

        jlbl_empresa.setText("Empresa: ");

        jcmb_empresa.setBackground(new java.awt.Color(255, 255, 204));
        jcmb_empresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Empresa" }));
        jcmb_empresa.setEnabled(false);
        jcmb_empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_empresaKeyPressed(evt);
            }
        });

        jpnl_fecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));
        jlbl_desde.setText("Desde: ");

        jlbl_hasta.setText("Hasta:");

        jdc_desde.setEnabled(false);

        jdc_hasta.setEnabled(false);

        org.jdesktop.layout.GroupLayout jpnl_fechaLayout = new org.jdesktop.layout.GroupLayout(jpnl_fecha);
        jpnl_fecha.setLayout(jpnl_fechaLayout);
        jpnl_fechaLayout.setHorizontalGroup(
            jpnl_fechaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_fechaLayout.createSequentialGroup()
                .add(jlbl_desde)
                .add(14, 14, 14)
                .add(jdc_desde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 201, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                .add(jlbl_hasta)
                .add(17, 17, 17)
                .add(jdc_hasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpnl_fechaLayout.setVerticalGroup(
            jpnl_fechaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_fechaLayout.createSequentialGroup()
                .add(jpnl_fechaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jdc_hasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_hasta)
                    .add(jdc_desde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_desde))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnl_hora.setBorder(javax.swing.BorderFactory.createTitledBorder("Hora"));
        jht_desde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jht_desde.setEnabled(false);
        jht_desde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jht_desdeKeyPressed(evt);
            }
        });

        jlbl_hdesde.setText("Desde:");

        jlbl_hhasta.setText("Hasta: ");

        jht_hasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jht_hasta.setEnabled(false);
        jht_hasta.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jht_hastaInputMethodTextChanged(evt);
            }
        });
        jht_hasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jht_hastaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jpnl_horaLayout = new org.jdesktop.layout.GroupLayout(jpnl_hora);
        jpnl_hora.setLayout(jpnl_horaLayout);
        jpnl_horaLayout.setHorizontalGroup(
            jpnl_horaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_horaLayout.createSequentialGroup()
                .add(jlbl_hdesde)
                .add(14, 14, 14)
                .add(jht_desde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(17, 17, 17)
                .add(jlbl_hhasta)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jht_hasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jpnl_horaLayout.setVerticalGroup(
            jpnl_horaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_horaLayout.createSequentialGroup()
                .add(jpnl_horaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jpnl_horaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jht_desde, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jlbl_hdesde))
                    .add(jlbl_hhasta)
                    .add(jht_hasta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jpnl_busquedaLayout = new org.jdesktop.layout.GroupLayout(jpnl_busqueda);
        jpnl_busqueda.setLayout(jpnl_busquedaLayout);
        jpnl_busquedaLayout.setHorizontalGroup(
            jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jpnl_busquedaLayout.createSequentialGroup()
                .add(36, 36, 36)
                .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jpnl_busquedaLayout.createSequentialGroup()
                        .add(jlbl_empresa)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(jpnl_busquedaLayout.createSequentialGroup()
                        .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jlbl_ruta)
                            .add(jlbl_servicio))
                        .add(8, 8, 8)))
                .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jcmb_ruta, 0, 269, Short.MAX_VALUE)
                    .add(jcmb_servicio, 0, 269, Short.MAX_VALUE)
                    .add(jpnl_busquedaLayout.createSequentialGroup()
                        .add(jcmb_empresa, 0, 269, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jpnl_busquedaLayout.createSequentialGroup()
                        .add(43, 43, 43)
                        .add(jpnl_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jpnl_busquedaLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jpnl_hora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(146, 146, 146))))
        );
        jpnl_busquedaLayout.setVerticalGroup(
            jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_busquedaLayout.createSequentialGroup()
                .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jpnl_busquedaLayout.createSequentialGroup()
                        .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jlbl_servicio)
                            .add(jcmb_servicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(15, 15, 15)
                        .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jlbl_ruta)
                            .add(jcmb_ruta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                        .add(jpnl_busquedaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jcmb_empresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_empresa))
                        .add(8, 8, 8))
                    .add(jpnl_busquedaLayout.createSequentialGroup()
                        .add(jpnl_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jpnl_hora, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(40, 40, 40))
        );

        jscp_corridas.setEnabled(false);
        jtbl_corridas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbl_corridas.setFocusTraversalKeysEnabled(true);
        jtbl_corridas.getTableHeader().setReorderingAllowed(false);
        jtbl_corridas.setDefaultRenderer(Object.class, new colorCorridaEstadoRenderer());
        jscp_corridas.setViewportView(jtbl_corridas);

        jlbl_asientos.setText("Asientos: ");

        jtxt_asientos.setEnabled(false);
        jtxt_asientos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_asientosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_asientosFocusLost(evt);
            }
        });
        jtxt_asientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_asientosKeyPressed(evt);
            }
        });

        jlbl_barraEstado.setText("jLabel1");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11)));
        jta_resumen.setFont(new java.awt.Font("Arial", 0, 12));
        jScrollPane1.setViewportView(jta_resumen);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );

        jlbl_rev.setText("jLabel1");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jscp_corridas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)))
                    .add(jpnl_busqueda, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                .add(20, 20, 20))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jlbl_asientos)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_asientos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .add(753, 753, 753))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(840, Short.MAX_VALUE)
                .add(jlbl_rev, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jlbl_rev)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnl_busqueda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(9, 9, 9)
                        .add(jscp_corridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 249, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(JPnlAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 476, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jtxt_asientos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_asientos))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        jcmb_servicio.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    private void jht_hastaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jht_hastaInputMethodTextChanged
// TODO add your handling code here:
    }//GEN-LAST:event_jht_hastaInputMethodTextChanged

    private void jtxt_asientosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_asientosFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Bloqueos por Lote| <font color=FF0000>ENTER</font> Buscar Corridas | <font color=FF0000>« », ARRIBA/ABAJO</font> Moverse entre Campos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");  
    }//GEN-LAST:event_jtxt_asientosFocusLost

    private void jtxt_asientosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_asientosFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Nueva Busqueda| <font color=FF0000>F8</font> Bloquear Asientos | <font color=FF0000>F11</font> Desbloquear Asientos | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana");          
    }//GEN-LAST:event_jtxt_asientosFocusGained

    private void jtxt_asientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_asientosKeyPressed
        Vector respuesta = null;
        if(evt.getKeyCode() == evt.VK_F8){
            //bloquear
            if(ValAsientos(getCapacidadPlantilla(PLANTILLA_DEFAULT))){
                System.out.println("asientos para bloquear "+asientos);
                //int response = ocuparAsientos(corrida.getCorridaId(), sesionVenta.getCadenaAsientos(), null, "I");
                mensajeDialogo("Bloqueo Asientos","Se inicial el Bloqueo de Asientos",JOptionPane.INFORMATION_MESSAGE);
                respuesta = un_blockAsientos("I");
                System.out.println("respuesta "+respuesta);
                String clave = "";
                String res = "";
                if(respuesta == null)
                    return;
                if(respuesta.get(0).toString() == "0")
                    return;
                jta_resumen.append("              ****************** Bloqueo ******************              \n");
                for(int k = 0; k < respuesta.size(); k++) {
                    clave = ((Vector)corridasData.get(k)).get(0).toString();                    
                    if(respuesta.get(k).toString().equals("-1"))
                        res = "Registro Ocupado";
                    else
                        res = "Correcto";
                    jta_resumen.append(clave+"   -->   "+res+"\n");
                }
            }
            jta_resumen.append("              ***********************************************              \n");
            jtxt_asientos.setText("");
        }
                    
        boolean imprimirUltimaLinea = false;
        if(evt.getKeyCode() == evt.VK_F11) {
            if(ValAsientos(getCapacidadPlantilla(PLANTILLA_DEFAULT))){
                System.out.println("asientos para desbloquear "+asientos);
                mensajeDialogo("Desbloqueo Asientos","Se inicial el Desbloqueo de Asientos",JOptionPane.INFORMATION_MESSAGE);
                respuesta = un_blockAsientos("E");
                System.out.println("respuesta "+respuesta);
               //System.out.println("respuesta "+respuesta);
                String clave = "";
                String res = "";
                if(respuesta == null)
                    return;
                if(respuesta.get(0).toString() == "0")
                    return;
                imprimirUltimaLinea = true;
                jta_resumen.append("              **************** Desbloqueo ****************              \n");
                for(int k = 0; k < respuesta.size(); k++) {
                    clave = ((Vector)corridasData.get(k)).get(0).toString();                    
                    if(respuesta.get(k).toString().equals("-1"))
                        res = "Registro Ocupado";
                    else
                        res = "Correcto";
                    jta_resumen.append(clave+  "  -->  "+res+"\n");
                }                
            }
            if(imprimirUltimaLinea)
                jta_resumen.append("              ***********************************************              \n");
            jtxt_asientos.setText("");
        }
        if(evt.getKeyCode() == evt.VK_ESCAPE) {
            limpiarCajitas(this.getComponents());
            jcmb_servicio.requestFocus();
            
        }   
        if(evt.getKeyCode() == evt.VK_F4){
            int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Bloque por Lotes?",
            "TMS - Bloqueo Por Lotes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(result == JOptionPane.YES_OPTION)
                this.dispose();
        }
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
    }//GEN-LAST:event_jtxt_asientosKeyPressed

    private void jht_hastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jht_hastaKeyPressed
        if(jht_hasta.isEnabled()) {
            if(evt.getKeyCode() == evt.VK_DOWN)
                jcmb_servicio.requestFocus();        
            if(evt.getKeyCode() == evt.VK_UP) {            
                jht_desde.requestFocus();
            } 
             if(evt.getKeyCode() == evt.VK_ENTER)
                consultarCorridas();
            if(evt.getKeyCode() == evt.VK_F4){
                    int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Bloque por Lotes?",
                    "TMS - Bloqueo Por Lotes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(result == JOptionPane.YES_OPTION)
                        this.dispose();
                }
            if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        }
    }//GEN-LAST:event_jht_hastaKeyPressed

    private void jht_desdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jht_desdeKeyPressed
        if(jht_desde.isEnabled()) {
            if(evt.getKeyCode() == evt.VK_DOWN)             
                jht_hasta.requestFocus();        
            if(evt.getKeyCode() == evt.VK_UP) {            
                jdc_hasta.dateEditor.setFoco();
            }
             if(evt.getKeyCode() == evt.VK_ENTER)
                consultarCorridas();
            if(evt.getKeyCode() == evt.VK_F4){
                    int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Bloque por Lotes?",
                    "TMS - Bloqueo Por Lotes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(result == JOptionPane.YES_OPTION)
                        this.dispose();
                }
            if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        }
    }//GEN-LAST:event_jht_desdeKeyPressed

    private void jcmb_empresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_empresaKeyPressed
        if(jcmb_empresa.isEnabled()){
            if(evt.getKeyCode() == evt.VK_RIGHT) {
                if(jcmb_ruta.getSelectedItem().equals("Seleccione una Ruta"))
                    jcmb_ruta.requestFocus();
                else {
                    jdc_desde.dateEditor.setEnabled(true);
                    jdc_hasta.dateEditor.setEnabled(true);
                    jdc_desde.setEnabled(true);
                    jdc_hasta.setEnabled(true);
                    jht_desde.setEnabled(true);
                    jht_hasta.setEnabled(true);
                    jdc_desde.dateEditor.setTexto(fechaHora.get(0).toString());
                    jdc_hasta.dateEditor.setTexto(fechaHora.get(0).toString());
                    jht_desde.setText(fechaHora.get(1).toString());
                    jht_hasta.setText(fechaHora.get(1).toString());
                    jdc_desde.dateEditor.setFoco();
                }
            }
            if(evt.getKeyCode() == evt.VK_LEFT) {            
                jdc_desde.dateEditor.setEnabled(false);
                jdc_hasta.dateEditor.setEnabled(false);            
                jdc_desde.setEnabled(false);
                jdc_hasta.setEnabled(false);
                jht_desde.setEnabled(false);
                jht_hasta.setEnabled(false);
                jht_desde.setText("");
                jht_hasta.setText("");
                jdc_desde.dateEditor.setTexto("");
                jdc_hasta.dateEditor.setTexto("");
                jcmb_ruta.requestFocus();
                jcmb_empresa.setEnabled(false);
                jcmb_empresa.setSelectedIndex(0);                
            }
            if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
            if(evt.getKeyCode() == evt.VK_ENTER) 
                consultarCorridas();            
            if(evt.getKeyCode() == evt.VK_F4){
                    int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Bloque por Lotes?",
                    "TMS - Bloqueo Por Lotes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(result == JOptionPane.YES_OPTION)
                        this.dispose();
                }
        }        
    }//GEN-LAST:event_jcmb_empresaKeyPressed

    private void jcmb_servicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_servicioKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT) 
            if(jcmb_servicio.getSelectedItem().equals("Seleccione un Servicio"))
                jcmb_servicio.requestFocus();
            else {
                jcmb_ruta.setEnabled(true);
                cargarRutas();
                jcmb_ruta.requestFocus();                
            }
        if(evt.getKeyCode() == evt.VK_LEFT) {
            if(jcmb_servicio.getSelectedItem().equals("Seleccione un Servicio"))
                jcmb_servicio.requestFocus();
            else {
                if(jht_hasta.isEnabled())
                    jht_hasta.requestFocus();
                else {
                    if(jcmb_empresa.isEnabled())
                        jcmb_empresa.requestFocus();
                    else 
                        jcmb_servicio.requestFocus();
                }
            }
        }
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
         if(evt.getKeyCode() == evt.VK_ENTER)
                consultarCorridas();
        if(evt.getKeyCode() == evt.VK_F4){
                    int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Bloque por Lotes?",
                    "TMS - Bloqueo Por Lotes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(result == JOptionPane.YES_OPTION)
                        this.dispose();
                }
                
    }//GEN-LAST:event_jcmb_servicioKeyPressed

    private void jcmb_rutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_rutaKeyPressed
       if(evt.getKeyCode() == evt.VK_RIGHT) {
            if(jcmb_ruta.getSelectedItem().equals("Seleccione una Ruta"))
                jcmb_ruta.requestFocus();
            else {
                jcmb_empresa.setEnabled(true);
                jcmb_empresa.requestFocus();
                jcmb_empresa.setSelectedIndex(1);
            }
        }
        if(evt.getKeyCode() == evt.VK_LEFT) {
            jcmb_ruta.setEnabled(false);
            jcmb_ruta.setSelectedIndex(0);
            jcmb_servicio.requestFocus();
            
        }
       if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        if(evt.getKeyCode() == evt.VK_ENTER)
                consultarCorridas();
       if(evt.getKeyCode() == evt.VK_F4){
                    int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Bloque por Lotes?",
                    "TMS - Bloqueo Por Lotes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(result == JOptionPane.YES_OPTION)
                        this.dispose();
       }
    }//GEN-LAST:event_jcmb_rutaKeyPressed

    private void iniciarConexion() {
        try {
            cont = new InitialContext(System.getProperties());
            busqueda = (TmsBloqueoPorLoteFacadeRemote) cont.lookup(TmsBloqueoPorLoteFacadeRemote.class.getName());        
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    private void cargaInicial() {
        //TmsServiciosTbl servicio;
        Vector temp = busqueda.getServicios();
        servicios = new Vector();
        for(int k = 0; k < temp.size(); k++) {            
            servicios.add(temp.get(k).toString().subSequence(1, temp.get(k).toString().length()-1));            
        }
                      
        temp = busqueda.getRutas();
        //System.out.println("temp "+temp);        
        //servicios = new Vector();
        TmsRutasTbl ruta;        
        rutas = new Vector();
        for(int k = 0; k < temp.size(); k++) {
            ruta = new TmsRutasTbl((Vector) temp.get(k));
            //System.out.println("ruta  "+ruta.getRutaNumeroNombre());
            rutas.add(ruta);
           // rutitas.add(ruta.getRutaNumeroNombre());
        }
        
        /*cbmRuta = new DefaultComboBoxModel(rutitas);
        jcmb_ruta.setModel(cbmRuta);*/
        
        temp = busqueda.getEmpresas();
        //System.out.println("temp "+temp);        
        //servicios = new Vector();
        TmsEmpresasTbl empresa;
        //Vector empresitas = new Vector();
        empresas = new Vector();
        for(int k = 0; k < temp.size(); k++) {
            empresa = new TmsEmpresasTbl((Vector) temp.get(k));
            //System.out.println("empresa "+empresa.getEmpresaNombre());
            empresas.add(empresa);
            //empresitas.add(empresa.getEmpresaNombre());
        }
        
        /*cbmEmpresas = new DefaultComboBoxModel(empresitas);
        jcmb_empresa.setModel(cbmEmpresas);*/
        
        corridasHeader = new Vector();
        corridasHeader.add("Clave Corrida");
        corridasHeader.add("Servicio");
        corridasHeader.add("Origen");
        corridasHeader.add("Destino");
        corridasHeader.add("Operador");
        corridasHeader.add("Autobus");
        corridasHeader.add("Fecha");
        corridasHeader.add("Hora");
        corridasHeader.add("Estado Corrida");
        
        fechaHora = busqueda.getFechaHora();
        //System.out.println("fechaHora "+fechaHora);        
        Plantillas();
    }

    private void cargarRutas() {        
        //System.out.println("****************** Entre a cargarRutas");
        Vector rutitas = new Vector();
        for(int k = 0; k < rutas.size(); k++){            
            if(((TmsRutasTbl)rutas.get(k)).getServicioNombre().equals(jcmb_servicio.getSelectedItem().toString())) {
                rutitas.add(rutas.get(k));
            }
        }
        if(rutitas == null) {
            mensajeDialogo("No hay Corridas", "<html>No existen corridas con el servicio selccionado. <br>Por favor, elija un servicio diferente e intente nuevamente</html>", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(rutitas.size() <= 0){
            mensajeDialogo("No hay Corridas", "<html>No existen corridas con el servicio selccionado. <br>Por favor, elija un servicio diferente e intente nuevamente</html>", JOptionPane.ERROR_MESSAGE);
            return;
        }
        rutitas.add(0, "Seleccione una Ruta");
        cbmRuta = new DefaultComboBoxModel(rutitas);
        jcmb_ruta.setModel(cbmRuta);
        jcmb_ruta.setSelectedIndex(0);
        //System.out.println("****************** Fin cargarRutas");
    }

    private void cargaEmpresa() {
        Vector empresitas = new Vector();
        for(int k = 0; k < empresas.size(); k++){
            empresitas.add(((TmsEmpresasTbl)empresas.get(k)).getEmpresaNombre());
        }
        empresitas.add(0, "Seleccione una Empresa");
        cbmEmpresas = new DefaultComboBoxModel(empresitas);
        jcmb_empresa.setModel(cbmEmpresas);
        jcmb_empresa.setSelectedIndex(0);
    }
    
    
   public void consultarCorridas(){
       System.out.println("Servicio "+jcmb_servicio.getSelectedItem().toString());
       System.out.println("Empresa "+jcmb_empresa.getSelectedItem().toString());
       System.out.println("Ruta "+jcmb_ruta.getSelectedItem().toString());
       String servicio = jcmb_servicio.getSelectedItem().toString();       
       String rutaId = "";
       if(servicio.equals("Selccione un Servicio")) {
           mensajeDialogo("Servicio Inválido", "Por favor selccione un servicio válido e intente nuevamente", JOptionPane.ERROR_MESSAGE);
           limpiarCajitas(this.getComponents());
           return;
       }
       if(jcmb_ruta.getSelectedItem().equals("Selccione una Ruta")) {
           mensajeDialogo("Ruta Inválida", "Por favor selccione una Ruta válida e intente nuevamente", JOptionPane.ERROR_MESSAGE);
           limpiarCajitas(this.getComponents());
           return;
       }else
          rutaId = ((TmsRutasTbl)jcmb_ruta.getSelectedItem()).getRutaId();
       
       String empresa = jcmb_empresa.getSelectedItem().toString();
       if(empresa.equals("Selccione una Empresa")) {
           mensajeDialogo("Empresa Inválida", "Por favor selccione una Empresa válida e intente nuevamente", JOptionPane.ERROR_MESSAGE);
           limpiarCajitas(this.getComponents());
           return;
       }
              
       System.out.println("Ruta "+rutaId);
       String fechaInicial = jdc_desde.dateEditor.getTexto();
       if(fechaInicial.equals("")||fechaInicial.length() == 0)
           fechaInicial = "sysdate";
       else
           fechaInicial = "'"+fechaInicial;
       System.out.println("fechaInicial "+fechaInicial);       
       String fechaFinal = jdc_hasta.dateEditor.getTexto();
       if(fechaFinal.equals("")||fechaFinal.length() == 0)
           fechaFinal = "sysdate";
       else
           fechaFinal = "'"+fechaFinal;
       System.out.println("fechaFinal "+fechaFinal);
       String horaDesde = jht_desde.getText();
       String horaHasta = jht_hasta.getText();
       if(horaDesde.equals("")||horaDesde.length() == 0)
           horaDesde = "00:00";       
       horaDesde = horaDesde+"'";
       System.out.println("horaDesde "+horaDesde);
       if(horaHasta.equals("")||horaHasta.length() == 0)
           horaHasta = "23:59";       
       horaHasta = horaHasta+"'";
       System.out.println("horaHasta "+horaHasta);       
       
       corridasData = busqueda.getCorridas(jcmb_servicio.getSelectedItem().toString(), 
                                           jcmb_empresa.getSelectedItem().toString(), 
                                           rutaId,
                                           fechaInicial,
                                           fechaFinal,
                                           horaDesde,
                                           horaHasta);
       //System.out.println("corridasData "+corridasData+ " tamaño "+corridasData.size());
       if(corridasData.size() < 4){                      
           mensajeDialogo("Corridas", "No existen corridas con los parámetros seleccioandos", JOptionPane.ERROR_MESSAGE);
           limpiarCajitas(this.getComponents());
           return;           
       }
       dblink = corridasData.get(corridasData.size()-3).toString();
       System.out.println("dblink "+dblink);
       String temp = busqueda.obtieneTerminal(dblink);
       System.out.println("es la misma terminal? => "+temp);
       if(temp == null){
           mensajeDialogo("Corridas", "No se pudo localizar el dblink propio de la terminal", JOptionPane.ERROR_MESSAGE);
           limpiarCajitas(this.getComponents());
           dblink = "";
           return;
       }
       if(temp.equalsIgnoreCase("true"))
           dblink = "";
       
       System.out.println("dblink dps de equals "+dblink);
       corridasId = new Vector();
       for(int k = 0; k < ((Vector)corridasData.get(corridasData.size()-2)).size(); k++){
           corridasId.add(((Vector)corridasData.get(corridasData.size()-2)).get(k).toString());
       }
       System.out.println("corridasId "+corridasId);
       //corridasData = new Vector();
       
       plantillaId = corridasData.get(corridasData.size()-1).toString();
       corridasData.remove(corridasData.size()-1);
       corridasData.remove(corridasData.size()-1);
       corridasData.remove(corridasData.size()-1);
       //System.out.println("corridasData " +corridasData);
       corridasTable.setDataVector(corridasData, corridasHeader);
       jtbl_corridas.setModel(corridasTable);
       resizeColumnasCorridas();
       jtxt_asientos.setEnabled(true);
       jtxt_asientos.requestFocus();
       JPnlbus.setPlantilla(this.getComponentes(),
                             this.getEncabezado(Long.parseLong(plantillaId)),
                             this.getLineas(Long.parseLong(plantillaId)));
        JPnlbus.pintaPlantilla(Long.parseLong(plantillaId));
   }
   
   public void resizeColumnasCorridas(){
        //DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        //tcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel = jtbl_corridas.getColumnModel();
        int anchoOcupado = 0;
        Vector ancho = new Vector();
           for (int col=0; col<jtbl_corridas.getColumnCount(); col++)
           {
                int maxwidth = 0;            
                for (int row=0; row<jtbl_corridas.getRowCount(); row++) {
                    TableCellRenderer rend  = jtbl_corridas.getCellRenderer(row, col); 
                    Object value = jtbl_corridas.getValueAt (row, col); 
                    Component comp = rend.getTableCellRendererComponent (jtbl_corridas, value, false, false, row, col);
                    maxwidth = Math.max (comp.getPreferredSize().width, maxwidth); 
                } // for row

                TableColumn column = columnModel.getColumn (col);
                TableCellRenderer headerRenderer = column.getHeaderRenderer();
                if (headerRenderer == null)
                    headerRenderer = jtbl_corridas.getTableHeader().getDefaultRenderer();

                Object headerValue = column.getHeaderValue();
                Component headerComp = headerRenderer.getTableCellRendererComponent (jtbl_corridas, headerValue, false, false, 0, col);
                maxwidth = Math.max (maxwidth, headerComp.getPreferredSize().width);
                column.setPreferredWidth (maxwidth + 15);
                anchoOcupado += maxwidth + 15;
                ancho.add(maxwidth + 15);
                ///jtbl_corridas.getColumnModel().getColumn(col).setCellRenderer(tcr);
           }
        //System.out.println("jscroll.getWidth() "+jscp_corridas.getWidth()+" anchoOcupado "+anchoOcupado);
        if(anchoOcupado < jscp_corridas.getWidth()) {
            int cachitoFaltante = (jscp_corridas.getWidth() - anchoOcupado)/jtbl_corridas.getColumnCount();
            //System.out.println("cachitoFaltante "+cachitoFaltante);
            for (int col=0; col<jtbl_corridas.getColumnCount(); col++){
                TableColumn column = columnModel.getColumn (col);
                //System.out.println("column.getPreferredWidth() "+column.getPreferredWidth());
                column.setPreferredWidth(Integer.parseInt(ancho.get(col).toString())+cachitoFaltante);
            }
        }
      }
   
   private boolean ValAsientos(long CupoMax) {
        //System.out.println("entre a ValAsientos");
        try{
            CupoMax=CupoMax+1;
            //System.out.println("jtxt_asientos.getText() "+jtxt_asientos.getText());
            if(jtxt_asientos.getText().equals("") || jtxt_asientos.getText().equals("0")) return false;
            if(asientos != null) asientos=null;
                asientos = new Vector();
            String aux = this.jtxt_asientos.getText();
            //System.out.println("aux "+aux);
            String Salida="";
            boolean AsientoOk = true;
            boolean NoAsientoInv = false;
            int no_asientos =0;
            int tipo_asientos = 0;
            if (aux.length() != 0) {
                long asiento, ainicio, afinal;
                String[] Strasiento;
                String[] rango;
                Strasiento = aux.split(" ");
                //System.out.println("Strasiento "+Strasiento + " "+Strasiento.length);
                String StrCorridaId;
                int h, bs;
                boolean yaExiste = true;
                for (int i = 0; i < Strasiento.length; i++) {
                    if (Strasiento[i].contains("-")) {
                        //System.out.println("es un ciclo");//evaluo que sea un rango
                        rango = Strasiento[i].split("-"); //divido los dos elementos
                        if (rango.length == 2) { //aseguro que sean dos numeros enteros.
                            ainicio = Integer.parseInt(rango[0]);
                            afinal = Integer.parseInt(rango[1]);
                            if(ainicio==0 || afinal==0){ NoAsientoInv=true; AsientoOk = false; break; }
                            //reviso que sea un rango valido
                            if (ainicio < afinal) {
                                for ( asiento = ainicio; asiento <= afinal; asiento++) { //agrego los valores
                                    //aseguro que no se repita el asiento seleccionado
                                    if(!asientos.contains(asiento) && asiento<CupoMax) {
                                        asientos.addElement(asiento);
                                    } else {
                                        Salida += asiento + " ";
                                        AsientoOk = false;
                                    }
                                    if(asiento>=CupoMax) {
                                        this.getToolkit().beep();
                                        mensajeDialogo("¡Error!","Asiento inválido: "+asiento, JOptionPane.ERROR_MESSAGE);
                                        return false;
                                    }
                                }
                                no_asientos++;
                            }
                        }
                    } else { //no es un ciclo
                        //System.out.println("no es un ciclo");
                        asiento = Integer.parseInt(Strasiento[i]);
                        //System.out.println("asiento "+asiento);
                        if((asiento==0)||(asiento >= CupoMax)){
                            if(asiento>=CupoMax) {
                                this.getToolkit().beep();
                                mensajeDialogo("¡Error!","Asiento inválido: "+asiento, JOptionPane.ERROR_MESSAGE);
                                return false;
                            }
                        }
                        else{
                            //aseguro que no se repita el asiento seleccionado
                            if(!asientos.contains(asiento) && asiento < CupoMax){
                                asientos.addElement(asiento);
                                no_asientos++;
                            } else {
                                Salida += asiento + " ";
                                AsientoOk = false;
                            }
                        }
                    }                    
                }
            }            
            
            return AsientoOk;
        } catch ( NullPointerException npex ) {
            return false;
        } catch ( java.lang.ArrayIndexOutOfBoundsException aiex ) {
            return false;
        }catch(java.util.NoSuchElementException ex){
            //ex.printStackTrace();
            return false;
        }catch(Exception x){
            //x.printStackTrace();
            return false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPnlAutobus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmb_empresa;
    private javax.swing.JComboBox jcmb_ruta;
    private javax.swing.JComboBox jcmb_servicio;
    private tms_calendario.JDateChooser jdc_desde;
    private tms_calendario.JDateChooser jdc_hasta;
    private tms_TextFields.JHourTextField jht_desde;
    private tms_TextFields.JHourTextField jht_hasta;
    private javax.swing.JLabel jlbl_asientos;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_desde;
    private javax.swing.JLabel jlbl_empresa;
    private javax.swing.JLabel jlbl_hasta;
    private javax.swing.JLabel jlbl_hdesde;
    private javax.swing.JLabel jlbl_hhasta;
    private javax.swing.JLabel jlbl_rev;
    private javax.swing.JLabel jlbl_ruta;
    private javax.swing.JLabel jlbl_servicio;
    private javax.swing.JPanel jpnl_busqueda;
    private javax.swing.JPanel jpnl_fecha;
    private javax.swing.JPanel jpnl_hora;
    private javax.swing.JScrollPane jscp_corridas;
    private javax.swing.JTextArea jta_resumen;
    private javax.swing.JTable jtbl_corridas;
    private jtxtfieldasientos.JTxtFieldAsientos jtxt_asientos;
    // End of variables declaration//GEN-END:variables

     /************************* plantillas ***************************/
    private boolean Plantillas(){
        componentes = new ArrayList<TmsComponenteBusTbl>(busqueda.queryTmsComponenteBusTblFindAll());
        encabezados = new ArrayList<TmsAutobusPlantillasEncTbl>(busqueda.queryTmsAutobusPlantillasEncTblFindAll());
        lineas =  new ArrayList<TmsAutobusPlantLineasTbl>(busqueda.queryTmsAutobusPlantLineasTblFindAll());
        
        if(componentes.size()==0 || encabezados.size()==0 || lineas.size()==0) return false;
        PLANTILLA_DEFAULT=encabezados.get(0).getPlantillaEncId();
        return true;
    }
    
    public List<TmsComponenteBusTbl> getComponentes(){
        return componentes;
    }
    
    public TmsAutobusPlantillasEncTbl getEncabezado(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i);
        return null;
    }
    
    public List<TmsAutobusPlantLineasTbl> getLineas(long PlantillaId){
        Vector vLineas = new Vector();
        
        for(int i=0; i<lineas.size(); i++)
            if(lineas.get(i).getPlantillaEncId()==PlantillaId)
                vLineas.addElement(lineas.get(i));
        
        if(vLineas.size()==0) return null;
        return new ArrayList<TmsAutobusPlantLineasTbl>(vLineas);
    }
    
    public int getCapacidadPlantilla(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i).getCapacidadAsientos().intValue();
        return 0;
    }
    
    public long getPLANTILLA_DEFAULT(){ 
        return this.PLANTILLA_DEFAULT; 
    }

    private void mensajeDialogo(String titulo, String error, int opcion) {
        JOptionPane optionPane = new JOptionPane("<html><center>"+error+"</center></html>", opcion, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog(this, "TMS-BloqueoPorLote-"+titulo);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
    }

    private Vector un_blockAsientos(String modo) {
        String noAsientos = "";
        String tipoAsientos = "";
        Vector respuesta = new Vector();
        for(int k = 0; k < asientos.size(); k++) {
            noAsientos=noAsientos+","+asientos.get(k).toString();
            tipoAsientos = tipoAsientos+",A";
        }
        System.out.println("noAsientos "+noAsientos);
        for(int k = 0; k < corridasId.size(); k++){
            System.out.println("corridaId "+corridasId.get(k).toString());
            if((noAsientos == "" || noAsientos.length() <= 0)|| (tipoAsientos == "" || tipoAsientos.length() <= 0))
                return null;
            if(modo.equals("E"))
                respuesta.add(busqueda.ocuparAsientos(dblink, corridasId.get(k).toString().subSequence(1, corridasId.get(k).toString().length()-1).toString(), noAsientos, tipoAsientos, modo, this.datosIniciales.get(0).toString()));
            else
                respuesta.add(busqueda.ocuparAsientos(dblink, corridasId.get(k).toString().subSequence(1, corridasId.get(k).toString().length()-1).toString(), noAsientos, null, modo, this.datosIniciales.get(0).toString()));
        }
        return respuesta;
    }
    
    private void limpiarCajitas(Component[] component) {        
        Object obj;        
        corridasTable.setDataVector(null, corridasHeader);
        jtbl_corridas.setModel(corridasTable);
        jta_resumen.setText("");
        for(int i = 0; i < component.length; i++){
            obj = (Object)component[i];
            //System.out.println("obj "+obj.getClass().toString());
            if (obj instanceof JComboBox ) {                
                ((JComboBox)obj).setSelectedIndex(0);
                ((JComboBox)obj).setEnabled(false);
            }
            if (obj instanceof JTextField) {
                ((JTextField)obj).setText("");
                ((JTextField)obj).setEnabled(false);
            }
            if (obj instanceof JDateChooser) {
                ((JDateChooser)obj).dateEditor.setTexto("");
                ((JDateChooser)obj).setEnabled(false);
            }
            if (obj instanceof JHourTextField) {
                ((JHourTextField)obj).setText("");
                ((JHourTextField)obj).setEnabled(false);
            }
            if (obj instanceof JTable) {
                corridasTable.setDataVector(null, corridasHeader);
                ((JTable)obj).setModel(corridasTable);                
            }
            if (obj instanceof JTextArea) {                
                ((JTextArea)obj).setText("");                
            }                        
            if(obj instanceof JPanel)
                limpiarCajitas(((JPanel)obj).getComponents());
            if(obj instanceof JRootPane)
                limpiarCajitas(((JRootPane)obj).getComponents());
            if(obj instanceof JLayeredPane)
                limpiarCajitas(((JLayeredPane)obj).getComponents());
        }       
        jcmb_servicio.setEnabled(true);
        setFoco();
    }

    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
    public void setFoco(){
        jcmb_servicio.requestFocus();
    }
    
     private class colorCorridaEstadoRenderer extends DefaultTableCellRenderer{
        private Color cFONDO_CORRIDAABIERTA = Color.WHITE;
        private Color cFONDO_CORRIDADESPACHADA = new Color(146,205,255);
        private Color cFONDO_CORRIDABORRADA=new Color(254,129,129);
        private Color cFONDO_OTRAS=new Color(255,255,202);
        private Color cLETRA=new Color(0,0,102);
        private Color cFONDO_SELECCION=new Color(49,106,197);
        private Color bg;
        private Color fg;
        private String cEdoCorte;
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
            boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if(corridasData==null){
                setBackground(cFONDO_SELECCION);
                setForeground(fg);
                return this;
            }
            cEdoCorte =jtbl_corridas.getModel().getValueAt(row, 8).toString();
            //System.out.println("cEdoCorte "+cEdoCorte);
            
            if(cEdoCorte.equals("BORRADA")) 
                bg = cFONDO_CORRIDABORRADA;
            else
                if(cEdoCorte.equals("ABIERTA")) 
                    bg = cFONDO_CORRIDAABIERTA;
                else
                    if(cEdoCorte.equals("DESPACHADA"))                 
                        bg = cFONDO_CORRIDADESPACHADA;
                    else
                        bg = cFONDO_OTRAS;
            setBackground(bg);
            setForeground(fg);
            setHorizontalAlignment(SwingConstants.CENTER);
            
            if(row==jtbl_corridas.getSelectedRow() && column==0){
                setBackground(cFONDO_SELECCION);
                setForeground(Color.WHITE);
            }
            return this;
        }
    }
     
     public void otrasFunciones(java.awt.event.KeyEvent evt){
         if(evt.getKeyCode() == evt.VK_F4){
            int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Bloque por Lotes?",
            "TMS - Bloqueo Por Lotes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(result == JOptionPane.YES_OPTION)
                dispose();
        }

        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
            setEventoTeclado(evt);
            try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
            return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
     }
}
