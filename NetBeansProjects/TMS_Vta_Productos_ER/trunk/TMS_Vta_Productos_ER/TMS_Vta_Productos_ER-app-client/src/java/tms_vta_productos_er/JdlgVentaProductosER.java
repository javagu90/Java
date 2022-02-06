/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JdlgVentaProductosER.java
 *
 * Created on 8/08/2012, 02:04:21 PM
 */

package tms_vta_productos_er;

import TMSVtaProductosER.entidad.Producto;
import TMSVtaProductosER.entidad.ProductoCarrito;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import tms_TextFields.JNumberTextField;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author vgonzalez
 */
public class JdlgVentaProductosER extends javax.swing.JDialog {
    private Vector datosIniciales;
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
    private final Color ARENA=new Color(238, 238, 238);
    private static Color SELECCION = new Color(184, 207, 229);
    private static Color BLANCO = new Color(255, 255, 255);
    private long usuarioId;
    private Icon sinVista= null;
    private facadeVtaProductos SeetingTMS;
    private List<Producto> productosVenta;
    private DefaultTableModel modeloProductos;
    private DefaultTableModel modeloResumen;
    private Vector headerProductos ;
    private Vector headerResumen ;
    private long corteId=1;
    private String caja="";
    private long cajero=1;
    private String terminal="";
    private long terminalId=1;
    private float montoTotal = 0;
    private String SalidaImpresion="ARCHIVO";
    private String puerto = "";
    private String nombreFormato = "";
    private String nombreImpresora = "";

    /** Creates new form JdlgVentaProductosER */
    public JdlgVentaProductosER(java.awt.Frame parent, boolean modal,Vector pDatosIniciales,String pterminal, String pcaja, long pterminalId,long pcorteId) {
        super(parent, modal);
        initComponents();
        datosIniciales = pDatosIniciales;
        caja = pcaja;
        terminal = pterminal;
        terminalId = pterminalId;
        corteId = pcorteId;
        JTxtUsusario.setText(datosIniciales.get(1).toString()+"-"+datosIniciales.get(2));
        JTxtCaja.setText(caja);
        JTxtTerminal.setText(terminal);
        this.setUsuarioId(Long.parseLong(datosIniciales.get(0).toString()));
        this.cajero = Long.parseLong(datosIniciales.get(1).toString());
        ImageIcon fot = new ImageIcon(getClass().getResource("/ico/sinVista2.png"));
        sinVista = new ImageIcon(fot.getImage());
        jLblImagen.setIcon(sinVista);
        jLblImagen.setHorizontalAlignment(JLabel.CENTER);
        headerProductos = new Vector();
        headerProductos.add("CLAVE");
        headerProductos.add("PRODUCTO");
        headerProductos.add("PRECIO");
        headerProductos.add("CANT.");
        headerResumen = new Vector();
        headerResumen.add("PRODUCTO");
        headerResumen.add("CANTIDAD");
        headerResumen.add("MONTO");
        headerResumen.add("ID");
        modeloProductos = (DefaultTableModel)JTblProductosER.getModel();
        modeloResumen = (DefaultTableModel)JTblResumen.getModel();
        this.JTblProductosER.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.JTblProductosER.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), JComponent.WHEN_FOCUSED);
        this.JTblProductosER.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_FOCUSED);
        //this.JTblProductosER.setRequestFocusEnabled(false);

        this.JTblResumen.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.JTblResumen.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), JComponent.WHEN_FOCUSED);
        this.JTblResumen.registerKeyboardAction(new ActionListener() {
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_FOCUSED);
        this.JTblResumen.setRequestFocusEnabled(false);
        JTblProductosER.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //JTblProductosER.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTblResumen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JTblResumen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SeetingTMS = new facadeVtaProductos();
        SeetingTMS.lookupGenerico();
        if(!cargaImpresoras())
           JOptionPane.showMessageDialog(this,  "No existen impresoras configuradas para Tickets en esta caja. \nFavor de contactar al Administrador del Sistema", "Advertencia",JOptionPane.WARNING_MESSAGE);
        productosVenta = SeetingTMS.generico_tmsRemote.getProductosParaVenta();
        //for (Producto p : productosVenta )
        if(productosVenta.size()==0)
        {
          JOptionPane.showMessageDialog(this,  "No existen productos a la venta en esta terminal", "Advertencia",JOptionPane.WARNING_MESSAGE);
          return;
        }
        sumarTotal(0);
        limpiaTabla();
        for(Producto p : productosVenta)
        {

            Object[] fila = new Object[4];
            fila[0] = p.getProductoClave();
            fila[1] = p.getProductoNombre();
            fila[2] = "$"+customFormat("##,##0.00",p.getImporteTarifaMostrador());
            fila[3] = "1";
            modeloProductos.addRow(fila);
            //System.out.println("JTblProductosER.getColumnCount(): "+JTblProductosER.getColumnCount());
            TableColumn sportColumn = JTblProductosER.getColumnModel().getColumn(3);
            JNumberTextField c = new JNumberTextField();
            c.setHorizontalAlignment(JTextField.RIGHT);
            //sportColumn.setCellEditor(new DefaultCellEditor(c));
            sportColumn.setCellEditor(new MyNumberEditor());
        }


        this.JTblProductosER.setModel(modeloProductos);
        JTblProductosER.setRowSelectionInterval(0,0);
        JTblProductosER.setDefaultRenderer(Object.class, new AttributiveCellRenderer1());
        this.JTblResumen.setModel(modeloResumen);
        JTblResumen.setDefaultRenderer(Object.class, new AttributiveCellRenderer1());
        SetsizeTabla();
        jLblBarraEstado.setBackground(ColoresInterfaz.cFondoPieEncabezado);
        jLblBarraEstado.setForeground(ColoresInterfaz.cTextoAyuda1);
        jLblBarraEstado.setFont(ColoresInterfaz.fuente3);
        jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">F1</font> Buscar Producto por Nombre |<font color="+ColoresInterfaz.cHTML3+">F2</font> Buscar Producto por Clave | <font color="+ColoresInterfaz.cHTML3+">F8</font> Agregar Producto | <font color="+ColoresInterfaz.cHTML3+">F4</font> Salir </html>");
        interfazColor();
        jTxtMonto.setText("$0.0");
        jTxtCambio.setText("$0.0");
        jTxtRestan.setText("$0.0");
        JTblProductosER.requestFocus();
    }

    public String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }


    private class MyNumberEditor extends DefaultCellEditor{


        public MyNumberEditor(){
            super(new JNumberTextField());
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JNumberTextField campo = (JNumberTextField)getComponent();
            campo.setHorizontalAlignment(JTextField.RIGHT);
            campo.setText(value.toString());
            campo.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), JComponent.WHEN_FOCUSED);
            campo.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_FOCUSED);
            campo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                //System.out.println("Se cacha el evento en JNumberTextField...");
                JtxtNumberKeyPressed(evt);
            }
        });
            return campo;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTblProductosER = new javax.swing.JTable();
        JTblProductosER = new javax.swing.JTable(){
            //  Place cell in edit mode when it 'gains focus'
            public void changeSelection(
                int row, int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);

                if(JTblProductosER.getRowCount()>0)
                {
                    Producto p = productosVenta.get(JTblProductosER.getSelectedRow());
                    jLblImagen.setIcon(sinVista);
                    if(p.getProductoImagen() != null)
                    jLblImagen.setIcon(new ImageIcon( (byte[])p.getProductoImagen()));
                }
                if (editCellAt(row, column))
                {
                    Component editor = getEditorComponent();
                    editor.requestFocusInWindow();
                    ((JTextComponent)editor).selectAll();
                    ((JTextField)editor).setHorizontalAlignment(javax.swing.SwingConstants.RIGHT );
                }
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTblResumen = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JTxtUsusario = new javax.swing.JTextField();
        JTxtCaja = new javax.swing.JTextField();
        JTxtTerminal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_total = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTxtMonto = new javax.swing.JTextField();
        jTxtMonto = new RecibirTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtCambio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtRestan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtVenta = new javax.swing.JTextField();
        jPanelImagen = new javax.swing.JPanel();
        jLblImagen = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLblBarraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        JTblProductosER.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "CLAVE", "PRODUCTO", "PRECIO", "CANT."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTblProductosER.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JTblProductosER.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTblProductosERFocusGained(evt);
            }
        });
        JTblProductosER.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTblProductosERKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTblProductosERKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(JTblProductosER);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMEN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        JTblResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "PRODUCTO", "CANTIDAD", "MONTO", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTblResumen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTblResumenFocusGained(evt);
            }
        });
        JTblResumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTblResumenKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(JTblResumen);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("USUARIO:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("CAJA:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("TERMINAL:");

        JTxtUsusario.setEditable(false);

        JTxtCaja.setEditable(false);

        JTxtTerminal.setEditable(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Fecha Hora");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(JTxtTerminal))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(JTxtCaja))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(JTxtUsusario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 259, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(184, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(279, Short.MAX_VALUE)
                .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jLabel9)
                .add(17, 17, 17)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(JTxtUsusario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(JTxtCaja, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(JTxtTerminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("TOTAL:");

        jtxt_total.setFont(new java.awt.Font("Tahoma", 1, 14));
        jtxt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_totalActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_total, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jtxt_total, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Recibo:");
        jLabel2.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 40));
        jPanel2.add(jLabel2, new java.awt.GridBagConstraints());

        jTxtMonto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTxtMonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtMonto.setPreferredSize(new java.awt.Dimension(210, 35));
        jTxtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtMontoActionPerformed(evt);
            }
        });
        jTxtMonto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtMontoFocusGained(evt);
            }
        });
        jTxtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtMontoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jTxtMonto, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Cambio:");
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jTxtCambio.setEditable(false);
        jTxtCambio.setFont(new java.awt.Font("Tahoma", 1, 16));
        jTxtCambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtCambio.setFocusable(false);
        jTxtCambio.setPreferredSize(new java.awt.Dimension(210, 35));
        jTxtCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCambioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(40, 5, 0, 0);
        jPanel2.add(jTxtCambio, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Restan:");
        jLabel4.setPreferredSize(new java.awt.Dimension(70, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        jTxtRestan.setEditable(false);
        jTxtRestan.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTxtRestan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtRestan.setFocusable(false);
        jTxtRestan.setPreferredSize(new java.awt.Dimension(210, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(40, 5, 0, 0);
        jPanel2.add(jTxtRestan, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText(" Venta:");
        jLabel5.setPreferredSize(new java.awt.Dimension(70, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jTxtVenta.setEditable(false);
        jTxtVenta.setFont(new java.awt.Font("Tahoma", 1, 16));
        jTxtVenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtVenta.setFocusable(false);
        jTxtVenta.setPreferredSize(new java.awt.Dimension(210, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(40, 5, 0, 0);
        jPanel2.add(jTxtVenta, gridBagConstraints);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vista Previa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLblImagen.setFont(new java.awt.Font("Tahoma", 1, 11));

        org.jdesktop.layout.GroupLayout jPanelImagenLayout = new org.jdesktop.layout.GroupLayout(jPanelImagen);
        jPanelImagen.setLayout(jPanelImagenLayout);
        jPanelImagenLayout.setHorizontalGroup(
            jPanelImagenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLblImagen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelImagenLayout.setVerticalGroup(
            jPanelImagenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelImagenLayout.createSequentialGroup()
                .add(jLblImagen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 206, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setFocusable(false);

        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLblBarraEstado.setForeground(new java.awt.Color(255, 255, 255));
        jLblBarraEstado.setText("<html> ok <br> no</html>");
        jLblBarraEstado.setFocusable(false);

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                .add(73, 73, 73))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelImagen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .add(jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelImagen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(17, 17, 17)
                .add(jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void interfazColor(){
        setBackground(ColoresInterfaz.cFondoVentana);
        jPanelImagen.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel1.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel2.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel3.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel4.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel5.setBackground(ColoresInterfaz.cFondoVentana);
//        jPanel1.setFont(ColoresInterfaz.fuente4);
//        jPanel2.setFont(ColoresInterfaz.fuente4);
//        jPanel3.setFont(ColoresInterfaz.fuente4);
//        jPanel4.setFont(ColoresInterfaz.fuente4);
//        jPanel5.setFont(ColoresInterfaz.fuente4);
        //setFont(ColoresInterfaz.fuente4);
        jScrollPane1.setBackground(ColoresInterfaz.cFondoVentana);
        jScrollPane2.setBackground(ColoresInterfaz.cFondoVentana);
        JTblProductosER.setBackground(ColoresInterfaz.cFondoVentana);
        JTblResumen.setBackground(ColoresInterfaz.cFondoVentana);
        jLabel9.setBackground(ColoresInterfaz.cFondoVentana);


        //jLblBarraEstado.setForeground(ColoresInterfaz.cTexto1);

        jLabel6.setForeground(ColoresInterfaz.cTexto1);
        jLabel7.setForeground(ColoresInterfaz.cTexto1);
        jLabel8.setForeground(ColoresInterfaz.cTexto1);
        jLabel8.setForeground(ColoresInterfaz.cTexto1);
        jLabel9.setForeground(ColoresInterfaz.cTexto1);
        jLabel1.setForeground(ColoresInterfaz.cTexto1);

        jLabel2.setForeground(ColoresInterfaz.cTexto1);
        jLabel2.setFont(ColoresInterfaz.fuente4);
        jTxtMonto.setFont(ColoresInterfaz.fuente4);


        jLabel3.setForeground(ColoresInterfaz.cTexto1);
        jLabel3.setFont(ColoresInterfaz.fuente4);
        jTxtCambio.setFont(ColoresInterfaz.fuente4);

        jLabel4.setForeground(ColoresInterfaz.cTexto1);
        jLabel4.setFont(ColoresInterfaz.fuente4);
        jTxtRestan.setFont(ColoresInterfaz.fuente4);

        jLabel5.setForeground(ColoresInterfaz.cTexto1);
        jLabel5.setFont(ColoresInterfaz.fuente4);
        jTxtVenta.setFont(ColoresInterfaz.fuente4);

        jLblBarraEstado.setFont(ColoresInterfaz.fuente2);
    }

  public void limpiaTabla(){
        int rows=modeloProductos.getRowCount();
        for(int j=0; j<rows; j++) modeloProductos.removeRow(0);
        modeloProductos.setDataVector(null,headerProductos);
        JTblProductosER.setModel(modeloProductos);
        
        rows=modeloResumen.getRowCount();
        for(int j=0; j<rows; j++) modeloResumen.removeRow(0);
        modeloResumen.setDataVector(null,headerResumen);
        JTblResumen.setModel(modeloResumen);
    }
     public void SetsizeTabla()
    {
        JTblProductosER.setRowHeight(22);
        JTblProductosER.getColumnModel().getColumn(0).setPreferredWidth(120);
        JTblProductosER.getColumnModel().getColumn(1).setPreferredWidth(255);
        JTblProductosER.getColumnModel().getColumn(2).setPreferredWidth(80);
        JTblProductosER.getColumnModel().getColumn(3).setPreferredWidth(50);

        JTblResumen.setRowHeight(22);
        JTblResumen.getColumnModel().getColumn(0).setPreferredWidth(300);
        JTblResumen.getColumnModel().getColumn(1).setPreferredWidth(70);
        JTblResumen.getColumnModel().getColumn(2).setPreferredWidth(140);
        JTblResumen.getColumnModel().getColumn(3).setPreferredWidth(0);
        JTblResumen.getColumnModel().getColumn(3).setMaxWidth(0);
        JTblResumen.getColumnModel().getColumn(3).setMinWidth(0);
    }


class AttributiveCellRenderer1 extends JLabel  implements TableCellRenderer {

        public AttributiveCellRenderer1() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(value==null || value.equals(""))
                setText("");
            else
                setText(String.valueOf(value));
            this.setFont(ColoresInterfaz.fuente4);
            //if(row == JTblProductosER.getSelectedRow() )//&& column == JTblProductosER.getSelectedColumn())
            if(row == table.getSelectedRow() )//&& column == JTblProductosER.getSelectedColumn())
            {
                //if(jtbl_boletos.getSelectedRow() == filaBancomer && jtbl_boletos.getSelectedColumn() == 4) this.setForeground(SELECCION);
                //else
                this.setForeground(Color.BLACK);
                this.setBackground(SELECCION);
                //    if(column ==3)

            }
            else{
                this.setBackground(ColoresInterfaz.cFondoVentana);
                this.setForeground(BLANCO);
            }

            if(column==2 || column==3) this.setHorizontalAlignment(RIGHT );
             else this.setHorizontalAlignment(LEFT);

            return this;

        }

}


    private void jtxt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_totalActionPerformed

    private void jTxtCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCambioActionPerformed

    private void JTblProductosERKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTblProductosERKeyReleased
       if(JTblProductosER.getRowCount()>0)
       {
        switch (evt.getKeyCode()){
          case KeyEvent.VK_UP: case KeyEvent.VK_DOWN: case KeyEvent.VK_LEFT:  case KeyEvent.VK_RIGHT:
              JTblProductosER.setColumnSelectionInterval(3, 3);
              JTblProductosER.changeSelection(JTblProductosER.getSelectedRow(), 3, false, false);
              //getCellEditor().stopCellEditing();
         break;
            case KeyEvent.VK_F1: case KeyEvent.VK_F2:
                this.setAlwaysOnTop(false);
                Jdlg_busqueda busqueda = new Jdlg_busqueda(modeloProductos.getDataVector(),evt.getKeyCode()==KeyEvent.VK_F2,evt.getKeyCode()==KeyEvent.VK_F1,ColoresInterfaz, JTblProductosER.getSize());
                busqueda.setVisible(true);
                System.out.println("Renglon encontrado: "+busqueda.getRenglon());
                if(busqueda.getRenglon()>=0)
                {
                    JTblProductosER.setRowSelectionInterval(busqueda.getRenglon(),busqueda.getRenglon());
                    JTblProductosER.setColumnSelectionInterval(3,3);
                    if(JTblProductosER.getRowCount()>9)
                        jScrollPane1.getViewport().setViewPosition(new Point(0,JTblProductosER.getRowHeight() * busqueda.getRenglon()));
                    busqueda = null;
                }
                this.setAlwaysOnTop(true);
                JTblProductosER.setColumnSelectionInterval(2, 2);
                JTblProductosER.changeSelection(JTblProductosER.getSelectedRow(), 3, false, false);
            break;
            case KeyEvent.VK_F8:
            //System.out.println("Cantidad: "+JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),3).toString());
            if(JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),3).toString().equals(""))
                JTblProductosER.setValueAt("1",JTblProductosER.getSelectedRow(),3);
            float precio = Float.valueOf((JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),2).toString().replace("$","")).replace(",",""));
            if(precio==0)
            {
              JOptionPane.showMessageDialog(this,  "El producto "+JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),1)+" No tiene una tarifa asiganada. \n Contacta al Administrador del Sistema ", "Advertencia",JOptionPane.ERROR_MESSAGE);
              JTblProductosER.setColumnSelectionInterval(3, 3);
              JTblProductosER.changeSelection(JTblProductosER.getSelectedRow(), 3, false, false);
              return;
            }
            Producto pro =  productosVenta.get(JTblProductosER.getSelectedRow());
            Object[] fila = new Object[5];
            fila[0] = pro.getProductoNombre();
            fila[1] = JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),3);
            float monto = pro.getImporteTarifaMostrador()*(Integer.valueOf(JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),3).toString()));
            sumarTotal(monto);
            fila[2] = "$"+customFormat("##,##0.00",monto);
            fila[3] = ""+pro.getProductoId();
            modeloResumen.addRow(fila);
            JTblResumen.setModel(modeloResumen);
            if (JTblProductosER.getCellEditor() != null) {
                JTblProductosER.getCellEditor().stopCellEditing();
            }
            //System.out.println("ColumnCount(): "+JTblResumen.getColumnCount());
            TableColumn column = JTblResumen.getColumnModel().getColumn(3);
            column.setPreferredWidth(0);
            column.setMaxWidth(0);
            column.setMinWidth(0);
            //System.out.println("Se setea a 1 la cantidad");
            JTblProductosER.setValueAt("1", JTblProductosER.getSelectedRow(),3);
            JTblProductosER.setColumnSelectionInterval(2, 2);
            JTblProductosER.requestFocus();
         break;
        case KeyEvent.VK_F10:
            if(JTblResumen.getRowCount()>0)
            {
                JTblResumen.setRowSelectionInterval(0, 0);
                JTblResumen.requestFocus();
            }
        break;
        case KeyEvent.VK_F4:
            CerrarVentana(); break;
        }
        }

    }//GEN-LAST:event_JTblProductosERKeyReleased

       private void limpiar()
    {
        this.montoTotal = 0;
        jTxtVenta.setText("$"+customFormat("##,##0.00",montoTotal));
        jtxt_total.setText("$"+customFormat("##,##0.00",montoTotal));
        int rows=modeloResumen.getRowCount();
        for(int j=0; j<rows; j++) modeloResumen.removeRow(0);
        modeloResumen.setDataVector(null,headerResumen);
        JTblResumen.setModel(modeloResumen);
        JTblResumen.setRowHeight(22);
        JTblResumen.getColumnModel().getColumn(0).setPreferredWidth(300);
        JTblResumen.getColumnModel().getColumn(1).setPreferredWidth(70);
        JTblResumen.getColumnModel().getColumn(2).setPreferredWidth(140);
        JTblResumen.getColumnModel().getColumn(3).setPreferredWidth(0);
        JTblResumen.getColumnModel().getColumn(3).setMaxWidth(0);
        JTblResumen.getColumnModel().getColumn(3).setMinWidth(0);
        jTxtMonto.setText("$0.0");
        jTxtCambio.setText("$0.0");
        jTxtRestan.setText("$0.0");
        if(JTblProductosER.getRowCount()>0)
            JTblProductosER.setRowSelectionInterval(0,0);
        JTblProductosER.requestFocus();
    }

    private void sumarTotal(float monto )
    {
        this.montoTotal += monto;
        jTxtVenta.setText("$"+customFormat("##,##0.00",montoTotal));
        jtxt_total.setText("$"+customFormat("##,##0.00",montoTotal));
    }

    private void restarTotal(float monto )
    {
        this.montoTotal -= monto;
        jTxtVenta.setText("$"+customFormat("##,##0.00",montoTotal));
        jtxt_total.setText("$"+customFormat("##,##0.00",montoTotal));
    }

    private void jTxtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtMontoActionPerformed
    jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">ESC</font> Regresar al Resumen | <font color="+ColoresInterfaz.cHTML3+">F10</font> Registrar Venta </html>");
    }//GEN-LAST:event_jTxtMontoActionPerformed

    private void JTblProductosERKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTblProductosERKeyPressed
//       if(JTblProductosER.getRowCount()>0)
//       {
//        switch (evt.getKeyCode()){
//            case KeyEvent.VK_F1: case KeyEvent.VK_F2:
//                this.setAlwaysOnTop(false);
//                Jdlg_busqueda busqueda = new Jdlg_busqueda(modeloProductos.getDataVector(),evt.getKeyCode()==KeyEvent.VK_F2,evt.getKeyCode()==KeyEvent.VK_F1,ColoresInterfaz, JTblProductosER.getSize());
//                busqueda.setVisible(true);
//                System.out.println("Renglon encontrado: "+busqueda.getRenglon());
//                if(busqueda.getRenglon()>=0)
//                {
//                    JTblProductosER.setRowSelectionInterval(busqueda.getRenglon(),busqueda.getRenglon());
//                    JTblProductosER.setColumnSelectionInterval(3,3);
//                    if(JTblProductosER.getRowCount()>9)
//                        jScrollPane1.getViewport().setViewPosition(new Point(0,JTblProductosER.getRowHeight() * busqueda.getRenglon()));
//                    busqueda = null;
//                }
//                this.setAlwaysOnTop(true);
//                JTblProductosER.setColumnSelectionInterval(2, 2);
//                JTblProductosER.changeSelection(JTblProductosER.getSelectedRow(), 3, false, false);
//            break;
//           }
//        }

    }//GEN-LAST:event_JTblProductosERKeyPressed

    private void JTblProductosERFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTblProductosERFocusGained
        jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">F1</font> Buscar Producto por Nombre |<font color="+ColoresInterfaz.cHTML3+">F2</font> Buscar Producto por Clave | <font color="+ColoresInterfaz.cHTML3+">F8</font> Agregar Producto | <font color="+ColoresInterfaz.cHTML3+">F10</font> Resumen  | <font color="+ColoresInterfaz.cHTML3+">F4</font> Salir </html>");
    }//GEN-LAST:event_JTblProductosERFocusGained

    private void JTblResumenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTblResumenFocusGained
        jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">ESC</font> Cancelar Venta Actual | <font color="+ColoresInterfaz.cHTML3+">F9</font> Quitar Producto | <font color="+ColoresInterfaz.cHTML3+">F10</font> Cobrar </html>");
    }//GEN-LAST:event_JTblResumenFocusGained

    private void JTblResumenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTblResumenKeyReleased
        if(JTblResumen.getRowCount()>0)
        {
            switch (evt.getKeyCode()){
              case KeyEvent.VK_ESCAPE:
                  /*
                  for(int i=0; i<JTblResumen.getRowCount();i++)
                  {
                    float monto = Float.valueOf(((JTblResumen.getValueAt(0,2).toString()).replace("$","").trim()).replace(",",""));
                    modeloResumen.removeRow(0);
                    restarTotal(monto);
                  }
                    JTblResumen.setModel(modeloResumen);
                    */
                    if(JTblProductosER.getRowCount()>0)
                        JTblProductosER.setRowSelectionInterval(0,0);
                    JTblProductosER.requestFocus();
              break;
              case KeyEvent.VK_F9:
                  if(JTblResumen.getSelectedRow()>=0)
                  {
                    float monto = Float.valueOf(((JTblResumen.getValueAt(JTblResumen.getSelectedRow(),2).toString()).replace("$","").trim()).replace(",","") ) ;
                    modeloResumen.removeRow(JTblResumen.getSelectedRow());
                    JTblResumen.setModel(modeloResumen);
                    JTblResumen.requestFocus();
                    restarTotal(monto);
                    if(JTblResumen.getRowCount()>0)
                        JTblResumen.setRowSelectionInterval(0,0);
                    else
                    {
                        if(JTblProductosER.getRowCount()>0)
                            JTblProductosER.setRowSelectionInterval(0,0);
                        JTblProductosER.requestFocus();
                    }
                  }
              break;
              case KeyEvent.VK_F10:
                jTxtMonto.selectAll();
                jTxtMonto.requestFocus();
              break;

            }
        }
    }//GEN-LAST:event_JTblResumenKeyReleased

    private void jTxtMontoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtMontoFocusGained
        jTxtMonto.selectAll();
    }//GEN-LAST:event_jTxtMontoFocusGained

    private void jTxtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtMontoKeyReleased
        switch (evt.getKeyCode()){
             case KeyEvent.VK_0: case KeyEvent.VK_1: case KeyEvent.VK_2: case KeyEvent.VK_3: case KeyEvent.VK_4:
             case KeyEvent.VK_5: case KeyEvent.VK_6: case KeyEvent.VK_7: case KeyEvent.VK_8: case KeyEvent.VK_9:
             case KeyEvent.VK_NUMPAD0: case KeyEvent.VK_NUMPAD1: case KeyEvent.VK_NUMPAD2: case KeyEvent.VK_NUMPAD3: case KeyEvent.VK_NUMPAD4:
             case KeyEvent.VK_NUMPAD5: case KeyEvent.VK_NUMPAD6: case KeyEvent.VK_NUMPAD7: case KeyEvent.VK_NUMPAD8: case KeyEvent.VK_NUMPAD9:
                  case KeyEvent.VK_BACK_SPACE:  case KeyEvent.VK_DELETE:
                    String texto =    (jTxtMonto.getText().replace("$", "")).replace(",","");
                    if(!texto.equals(""))
                    {
                        float f = (Float.valueOf(texto));
                        if(f>=this.montoTotal)
                        {
                            jTxtRestan.setText("$0.0");
                            jTxtCambio.setText("$"+(f-this.montoTotal));
                        }
                        else
                        {
                            jTxtCambio.setText("$0.0");
                            jTxtRestan.setText("$"+(this.montoTotal-f));
                        }

                    }
           break;
           case KeyEvent.VK_ESCAPE:
               JTblResumen.setRowSelectionInterval(0,0);
               JTblResumen.requestFocus();;
           break;
            case KeyEvent.VK_F10:
                if(jTxtMonto.getText().equals("") || jTxtMonto.getText().equals("$") || jTxtMonto.getText().equals("$0.0") || !jTxtRestan.getText().equals("$0.0"))
                {
                  JOptionPane.showMessageDialog(this,  "No ha cobrado el monto completo", "Advertencia",JOptionPane.WARNING_MESSAGE);
                  return;
                }
                String empresa="";
                String producto="";
                long cantidad=0;
                String folio=SeetingTMS.generico_tmsRemote.getTicketCompra(getPrefijo());
                String clave_producto="";
                float precio_unitario=0;
                float subtotal=0;
                float iva=0;
                float total=0;
                float retencion=0;
                float montoTotalTicket =0;
                String emp = "";
                for(Producto product:productosVenta)
                {
                    if(product.getProductoId()==Long.valueOf(JTblResumen.getValueAt(0,3).toString()))
                    {
                        emp = product.getEmpresaDescripcion().toUpperCase();
                        break;
                    }
                }
                if(emp.length()<50)
                {
                    //for(int x=emp.length(); x<50; x++)
                        emp = "    "+emp;
                }
                String sCad="\n";
                //sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
                System.out.println("Empresa Nombre: "+emp);
                if (emp.length()>23)
                    sCad = sCad+"      "+emp.substring(0,23).trim();
                sCad = sCad+"\n";
                //sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
                if (emp.length()>23)
                    sCad = sCad+"      "+emp.substring(24,emp.length()).trim();
                sCad = sCad+"\n";
                sCad = sCad+"        Comprobante de Pago ";
                sCad = sCad+"\n";
                SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
                SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
                sCad = sCad+"  FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
                sCad = sCad+"\n";
                sCad = sCad+"  No.Ticket: "+folio;
                sCad = sCad+"\n\n";
                sCad = sCad+"            Productos      "; //750
                sCad = sCad+"\n";
                sCad = sCad+"_____________________________________";
                sCad = sCad+"\n";
                sCad = sCad+"Cant.\tNombre\t\tPrecio\tImporte";
                sCad = sCad+"\n";
                sCad = sCad+"_____________________________________";
                sCad = sCad+"\n";



                boolean imprimirTicket=false;
               List<ProductoCarrito> productosImp = new ArrayList<ProductoCarrito>();
               for(int i=0; i<modeloResumen.getRowCount(); i++)
               {
                for(Producto product:productosVenta)
                {
                    if(product.getProductoId()==Long.valueOf(JTblResumen.getValueAt(i,3).toString()))
                    {
                        System.out.println("*****************************************");
                        System.out.println(" ProductoId: "+product.getProductoId());
                        System.out.println(" Clave: "+product.getProductoClave());
                        System.out.println(" Nombre: "+product.getProductoNombre());
                        System.out.println(" TarifaMostrador: "+product.getImporteTarifaMostrador());
                        System.out.println(" EmpresaNombre: "+product.getEmpresaNombre());
                        empresa=product.getEmpresaNombre();
                        producto=product.getProductoNombre();
                        cantidad=Long.valueOf(JTblResumen.getValueAt(i,1).toString());
                        clave_producto=product.getProductoClave();
                        precio_unitario=product.getImporteTarifaMostrador();
                        subtotal=Float.valueOf((JTblResumen.getValueAt(i,2).toString().replace("$","")).trim().replace(",",""));
                        iva=0;
                        total=Float.valueOf((JTblResumen.getValueAt(i,2).toString().replace("$","")).trim().replace(",",""));
                        retencion=0;
                        SeetingTMS.generico_tmsRemote.registrarVentaProducto(empresa, producto, cantidad, folio, clave_producto, precio_unitario, subtotal, iva, total, retencion, corteId, caja, cajero, terminal, getPrefijo(), usuarioId);
                        if(product.getAdicional4().equals("S"))
                        {
                        try {
                            productosImp.add(new ProductoCarrito(product.getProductoId(), empresa, producto, cantidad, folio, clave_producto, precio_unitario, Float.valueOf("" + roundNum(Double.valueOf((subtotal / 1.16)/cantidad), 2)), subtotal - (Float.valueOf("" + roundNum(Double.valueOf(subtotal / 1.16), 2))), total, retencion, corteId, caja, cajero, terminal, 0, usuarioId, "EFE", product.getAdicional4().equals("S"), product.getEmpresaDescripcion()));
                        } catch (Exception ex) {
                            Logger.getLogger(JdlgVentaProductosER.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            imprimirTicket = true;
                            //sCad = sCad+" Catidad   Nombre  Precio  Importe";
                            montoTotalTicket=montoTotalTicket+total;
                            sCad = sCad+" "+cantidad+(((""+cantidad).length()>2)?" ":"  ")+((producto.length()<=11)?producto:producto.substring(0,11))+(((""+producto).length()>7)?"\t":"\t\t")+precio_unitario+(((""+precio_unitario).length()>3)?"\t":"\t\t")+total;
                            sCad = sCad+"\n";
                        }
                    }
                }

               }
               if(imprimirTicket)
               {
                   sCad = sCad+"\n";
                    sCad = sCad+"\t\t\t\t\tTotal: $"+montoTotalTicket;
                    sCad = sCad+"\n\n";
                    String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(montoTotalTicket)).longValue()))  + "Pesos 00/M.N. )";
                    sCad = sCad+letras;
                    sCad = sCad+"\n\n";
                    sCad = sCad+"\n";
                    sCad = sCad+"Cajero: "+(JTxtUsusario.getText().length()<25?JTxtUsusario.getText():JTxtUsusario.getText().substring(0,25));//+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
                    sCad = sCad+"\n";
                    sCad = sCad+" #Caja: "+caja;
                    sCad = sCad+"\n\n   Este comprobante de pago";
                    sCad = sCad+"\n"+"      No es Valido";
                    sCad = sCad+"\n"+" Como cupon de viaje, como";
                    sCad = sCad+"\n"+"boleto de transporte ni como ";
                    sCad = sCad+"\n"+"     comprobante Fiscal";   
                    sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
                    sCad = sCad+"         .";
                    String sCadTermico = sCad;
                 if(nombreFormato.equals("TICKET_TERMICO"))// || nombreFormato.equals("TICKET"))
                 {
                     sCadTermico = "";
                     float impTotalTick = 0;
                     float subTotalTick = 0;
                     float ivaTotalTick = 0;
                     System.out.println(" Entra a generar Ticket Termico...");
                     for(int j=0; j<productosImp.size();j++)
                     {
                         ProductoCarrito prod= productosImp.get(j);
                         impTotalTick  = impTotalTick + prod.getTotal();
                         subTotalTick  = subTotalTick + (prod.getSubtotal()*prod.getCantidad());
                         ivaTotalTick  = ivaTotalTick + prod.getIva();
                     }
                         sCadTermico = "^XA\n";
                        sCadTermico = sCadTermico + "^MMT\n";
                        sCadTermico = sCadTermico + "^PW609\n";
                        sCadTermico = sCadTermico + "^LL0972\n";
                        sCadTermico = sCadTermico + "^LS0\n";
                        if(productosImp.get(0).getEmpresa().equals("Terminal Las Torres Puebla 1"))
                            sCadTermico = sCadTermico + "^FT35,110^XGE:cmpEbus2.GRF,1,1'+'^FS'\n";
                        else
                            sCadTermico = sCadTermico + "^FT35,110^XGE:cmpER.GRF,1,1'+'^FS'\n";
                        sCadTermico = sCadTermico + "^FO410,30\n";
                        sCadTermico = sCadTermico + "^GS^FDA^FS\n";
                        sCadTermico = sCadTermico + "^FT512,191^A0N,28,28^FH\\^FD"+formath.format(new Date()) +"^FS\n";
                        sCadTermico = sCadTermico + "^FT24,738^A0N,28,28^FH\\^FD#Caja:^FS\n";
                        sCadTermico = sCadTermico + "^FT24,230^A0N,28,28^FH\\^FDNo. Ticket:^FS\n";
                        sCadTermico = sCadTermico + "^FT110,742^A0N,28,28^FH\\^FD"+caja+"^FS\n";
                        sCadTermico = sCadTermico + "^FT29,628^A0N,28,28^FH\\^FD"+"( "+new cantidad_a_letras().toLetras((long)((new Double(impTotalTick)).longValue()))  + "Pesos 00/M.N. )"+"^FS\n";
                        sCadTermico = sCadTermico + "^FT170,232^A0N,28,28^FH\\^FD"+folio+"^FS\n";
                        sCadTermico = sCadTermico + "^FT434,191^A0N,28,28^FH\\^FDHora:^FS\n";
                        sCadTermico = sCadTermico + "^FT113,697^A0N,28,28^FH\\^FD"+(JTxtUsusario.getText().length()<25?JTxtUsusario.getText():JTxtUsusario.getText().substring(0,25))+"^FS\n";
                        sCadTermico = sCadTermico + "^FT171,191^A0N,28,28^FH\\^FD"+formatf.format(new Date()) +"^FS\n";
                        sCadTermico = sCadTermico + "^FT126,329^A0N,23,24^FH\\^FDNombre^FS\n";
                        sCadTermico = sCadTermico + "^FT480,330^A0N,23,24^FH\\^FDImporte^FS\n";
                        sCadTermico = sCadTermico + "^FT351,573^A0N,28,28^FH\\^FDTotal:^FS\n";
                        sCadTermico = sCadTermico + "^FT280,330^A0N,23,24^FH\\^FDPrecio Unitario^FS\n";
                        sCadTermico = sCadTermico + "^FT440,570^A0N,28,28^FH\\^FD"+getLPAD("$"+impTotalTick,10)+"^FS\n";
                        sCadTermico = sCadTermico + "^FT24,329^A0N,23,24^FH\\^FDCant.^FS\n";
                        sCadTermico = sCadTermico + "^FT452,497^A0N,23,24^FH\\^FD"+getLPAD("$"+subTotalTick,10)+"^FS\n";
                        sCadTermico = sCadTermico + "^FT452,529^A0N,23,24^FH\\^FD"+getLPAD("$"+ivaTotalTick,10)+"^FS\n";
                        if(productosImp.size()>=3)
                            sCadTermico = sCadTermico + "^FT468,450^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(2).getSubtotal())*productosImp.get(2).getCantidad()), 8)+"^FS\n";
                        if(productosImp.size()>=2)
                        sCadTermico = sCadTermico + "^FT468,413^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(1).getSubtotal())*productosImp.get(1).getCantidad()), 8)+"^FS\n";
                        sCadTermico = sCadTermico + "^FT333,499^A0N,23,24^FH\\^FDSubtotal:^FS\n";
                        if(productosImp.size()>=3)
                            sCadTermico = sCadTermico + "^FT121,455^A0N,23,24^FH\\^FD"+productosImp.get(2).getProducto()+"^FS\n";
                        sCadTermico = sCadTermico + "^FT358,534^A0N,23,24^FH\\^FDI.V.A.:^FS\n";
                        if(productosImp.size()>=3)
                            sCadTermico = sCadTermico + "^FT328,453^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(2).getSubtotal(), 8)+"^FS\n";
                        if(productosImp.size()>=1)
                            sCadTermico = sCadTermico + "^FT467,376^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(0).getSubtotal())*productosImp.get(0).getCantidad()), 8)+"^FS\n";
                        if(productosImp.size()>=2)
                        sCadTermico = sCadTermico + "^FT121,419^A0N,23,24^FH\\^FD"+productosImp.get(1).getProducto()+"^FS\n";
                        if(productosImp.size()>=3)
                            sCadTermico = sCadTermico + "^FT35,455^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(2).getCantidad(), 2)+"^FS\n";
                        if(productosImp.size()>=2)
                            sCadTermico = sCadTermico + "^FT328,417^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(1).getSubtotal(), 8)+"^FS\n";
                        if(productosImp.size()>=1)
                            sCadTermico = sCadTermico + "^FT328,380^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(0).getSubtotal(), 8)+"^FS\n";
                        if(productosImp.size()>=1)
                            sCadTermico = sCadTermico + "^FT121,382^A0N,23,24^FH\\^FD"+productosImp.get(0).getProducto()+"^FS\n";
                        if(productosImp.size()>=2)
                            sCadTermico = sCadTermico + "^FT35,418^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(1).getCantidad(), 2)+"^FS\n";
                        if(productosImp.size()>=1)
                            sCadTermico = sCadTermico + "^FT35,381^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(0).getCantidad(), 2)+"^FS\n";
                        sCadTermico = sCadTermico + "^FT17,693^A0N,28,28^FH\\^FDCajero:^FS\n";
                        sCadTermico = sCadTermico + "^FT76,190^A0N,28,28^FH\\^FDFecha:^FS\n";
                        sCadTermico = sCadTermico + "^FT6,339^A0N,25,24^FH\\^FD__________________________________________________^FS\n";
                        sCadTermico = sCadTermico + "^FT251,286^A0N,34,33^FH\\^FDProductos^FS\n";
                        sCadTermico = sCadTermico + "^FT8,293^A0N,25,24^FH\\^FD__________________________________________________^FS\n";
                        sCadTermico = sCadTermico + "^FT25,827^A0N,23,24^FH\\^FDcomo boleto de transporte ni como comprobante fiscal.^FS\n";
                        sCadTermico = sCadTermico + "^FT2,797^A0N,23,24^FH\\^FDEste comprobante de pago no es valido como cupon de viaje,^FS\n";
                        if(productosImp.get(0).getEmpresa().equals("Terminal Las Torres Puebla 1"))
                            sCadTermico = sCadTermico + "^FT40,965^XGE:Ebusred.GRF,1,1'+'^FS'\n";
                        else
                            sCadTermico = sCadTermico + "^FT40,965^XGE:ER redes.GRF,1,1'+'^FS'\n";
                        sCadTermico = sCadTermico + "^PQ1,0,1,Y^XZ\n";
                        sCadTermico = sCadTermico + "C\n";
                        sCadTermico= sCadTermico.replace("Ñ","N");
                     
                 }
                sCad = sCadTermico;
               if(puerto.equals("LPT1") ||puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2")  || puerto.equals("ARCHIVO") )
                    SalidaImpresion = puerto;
               if(puerto.equals("RED") )
                       SalidaImpresion =  nombreImpresora;
               if(puerto.equals("USB"))
                   SalidaImpresion = "\\\\127.0.0.1\\"+ nombreImpresora;
                    try{
                    String UserHome = System.getProperty("user.home");
                    if(SalidaImpresion.equals("ARCHIVO"))
                        SalidaImpresion = UserHome+"\\TICKET_COMPRA_"+folio+".TXT";

                    //FileDescriptor fd = new FileDescriptor();
                    FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
                    PrintStream ps = new PrintStream(os);
                    ps.print(sCad); // CADENA A IMPRIMIR
                   // ps.print(sCad); // CADENA A IMPRIMIR(Copia)
                    ps.flush();
                    os.close();
                }catch(java.io.FileNotFoundException fsctex){
                    fsctex.printStackTrace();
                    String UserHome = System.getProperty("user.home");
                    SalidaImpresion = UserHome+"\\TICKET_COMPRA_"+folio+".TXT";
                    FileOutputStream os;
                        try {
                            os = new FileOutputStream(SalidaImpresion);
                            PrintStream ps = new PrintStream(os);
                            ps.print(sCad); // CADENA A IMPRIMIR
                            ps.flush();
                            try {
                                os.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } // LPT / C:\\ARCHIVO.TXT / COM
                }catch(Exception sctex){
                    sctex.printStackTrace();
                }


               }
              JOptionPane.showMessageDialog(this,  "Los Productos se vendieron correctamente", "Venta Realizada",JOptionPane.INFORMATION_MESSAGE);
              limpiar();
           break;
          
        }
    }//GEN-LAST:event_jTxtMontoKeyReleased


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

    private String getLPAD(String texto, int lenght)
    {
        String lpad =texto;
        for(int i=texto.length(); i<lenght;i++ )
            lpad = " " + lpad;
        return lpad;
    }

    private long getPrefijo()
    {
        int tamanio = (""+terminalId).length();
        String res = ""+terminalId;
        for(int i=tamanio; i<4;i++)
            res = res+"0";
        return Long.valueOf(res);

    }

    private void JtxtNumberKeyPressed(java.awt.event.KeyEvent evt) {
     if(JTblProductosER.getRowCount()>0)
     {
        switch (evt.getKeyCode()){
            case KeyEvent.VK_F8: case KeyEvent.VK_F1: case KeyEvent.VK_F2: case KeyEvent.VK_F4: case KeyEvent.VK_F10:
                if (JTblProductosER.getCellEditor() != null) {
                    JTblProductosER.getCellEditor().stopCellEditing();
                }
                JTblProductosER.setColumnSelectionInterval(2, 2);
            break;
        }
      }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdlgVentaProductosER dialog = new JdlgVentaProductosER(new javax.swing.JFrame(), true,null,"","",1,1000);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTblProductosER;
    private javax.swing.JTable JTblResumen;
    private javax.swing.JTextField JTxtCaja;
    private javax.swing.JTextField JTxtTerminal;
    private javax.swing.JTextField JTxtUsusario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JLabel jLblImagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTxtCambio;
    private javax.swing.JTextField jTxtMonto;
    private javax.swing.JTextField jTxtRestan;
    private javax.swing.JTextField jTxtVenta;
    private javax.swing.JTextField jtxt_total;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the usuarioId
     */
    public long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }


public boolean cargaImpresoras(){
        PcInfo pc = new PcInfo();
         System.out.println("Host Name: "+pc.getHostName());
       Vector res = SeetingTMS.generico_tmsRemote.getImpresora(pc.getHostName());
       if(res.size()==0)
           return false;
       Vector v = (Vector)res.get(0);
       nombreImpresora= (v.get(0).toString());
       puerto= (v.get(1).toString());
       nombreFormato=(v.get(2).toString());
       System.out.println("NombreImpresora: "+nombreImpresora);
       System.out.println("puerto: "+puerto);
       System.out.println("NombreFormato: "+nombreFormato);
       return true;
    }

class RecibirTextField extends JTextField
{
  private boolean beep_on_error = false;
  private char c;

  public RecibirTextField()
  {
    initializeForCuantity();
  }

  private void initializeForCuantity() {
    setDocument(new PlainDocument() {
      public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        StringBuffer buf = new StringBuffer(str);
        int size = buf.length();
        if (size > 1)
          super.insertString(offs, buf.toString(), a);
        else {
          for (int i = 0; i < size; i++) {
             c = buf.charAt(i);
            if ((Character.isDigit(c)) || (c == '.'))
            {
                if(getLength()==0)
                    buf.insert(0, "$");
                continue;
            }
            RecibirTextField.this.setBeepOnError(true);
            if (RecibirTextField.this.beep_on_error) {
              Toolkit.getDefaultToolkit().beep();
            }
            buf.deleteCharAt(i);
          }

          if ((c == '.') && (getText(0, getLength()).indexOf(".") >= 0))
            Toolkit.getDefaultToolkit().beep();
          else
            super.insertString(offs, buf.toString(), a);
        }
      } }
    );
  }

  public void setBeepOnError(boolean beep) {
    this.beep_on_error = beep;
  }
}

    public void centrarDialogo(){
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


private void CerrarVentana(){
        this.setAlwaysOnTop(false);
        JDlgSiNo DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Desea cerrar la Aplicación de Venta de Productos?",false);
        if(DialogoSiNo.getResultado())
          this.dispose();
        else
            this.setAlwaysOnTop(true);
    }

}
