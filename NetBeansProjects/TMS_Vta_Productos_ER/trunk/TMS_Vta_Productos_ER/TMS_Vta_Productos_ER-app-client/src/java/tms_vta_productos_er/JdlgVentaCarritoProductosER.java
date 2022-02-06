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
import java.awt.Font;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;
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
import java.util.ArrayList;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author vgonzalez
 */
public class JdlgVentaCarritoProductosER extends javax.swing.JDialog {
    private Vector datosIniciales;
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
    private final Color ARENA=new Color(238, 238, 238);
    private static Color SELECCION = new Color(184, 207, 229);
    private static Color BLANCO = new Color(255, 255, 255);
    private long usuarioId;
    private Icon sinVista= null;
    private facadeVtaProductos SeetingTMS;
    private List<Producto> productosVenta;
    private List<ProductoCarrito> pructosAgregados = new ArrayList<ProductoCarrito>();
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
    private boolean venta=false;
    private boolean ventaJuconi=false;
    private String folio="";
    private float impBol;

    /** Creates new form JdlgVentaProductosER */
    public JdlgVentaCarritoProductosER(java.awt.Frame parent, boolean modal,Vector pDatosIniciales,String pterminal, String pcaja, long pterminalId,long pcorteId,String pfolio,List<ProductoCarrito> pructosAgregadosAnt, float impBol, String ptipoBoleto, boolean pventaJuconi) {
        super(parent, modal);
        initComponents();
        datosIniciales = pDatosIniciales;
        caja = pcaja;
        terminal = pterminal;
        terminalId = pterminalId;
        corteId = pcorteId;
        jtxt_total.setFocusable(false);
        this.impBol = impBol;
        this.ventaJuconi = pventaJuconi;
        if(this.ventaJuconi) jLabel5.setText("Donativo JUCONI:");
        else jLabel5.setText("Venta Productos:");
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
        if(ptipoBoleto.equals("N"))
            this.folio = pfolio;
        else
            this.folio = SeetingTMS.generico_tmsRemote.getTicketCompra(getPrefijo());

        productosVenta = SeetingTMS.generico_tmsRemote.getProductosParaVentaCarrito();
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

        this.jlblImporteBol.setText("$ "+impBol);
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
        jLabel8.setText("");
        /*********** Agrega los productos que ya estaban en el carrito *********/
        if(pructosAgregadosAnt.size()>0 )
        {
           for(ProductoCarrito p:pructosAgregadosAnt)
           {
                if(ventaJuconi) jLabel8.setText("¡¡ GRACIAS POR SU DONATIVO !!");
                //Producto pro =  productosVenta.get(JTblProductosER.getSelectedRow());
                Object[] fila = new Object[5];
                fila[0] = p.getProducto(); //pro.getProductoNombre();
                fila[1] = p.getCantidad(); //JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),3);
                float monto = p.getTotal(); //pro.getImporteTarifaMostrador()*(Integer.valueOf(JTblProductosER.getValueAt(JTblProductosER.getSelectedRow(),3).toString()));
                sumarTotal(monto);
                fila[2] = "$"+customFormat("##,##0.00",monto);
                fila[3] = ""+p.getProductoId();
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
            }
           }
            /******************************************************************/
        JTblProductosER.setColumnSelectionInterval(3, 3);
        JTblProductosER.changeSelection(JTblProductosER.getSelectedRow(), 3, false, false);
        JTblProductosER.requestFocus();
    }

    public String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }

    /**
     * @return the venta
     */
    public boolean isVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(boolean venta) {
        this.venta = venta;
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
        jLabel1 = new javax.swing.JLabel();
        jtxt_total = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlblImporteBol = new javax.swing.JLabel();
        jlblImporteJuconi = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlblImporteTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
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
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_total, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jtxt_total, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(67, 67, 67))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Importe Boletos:");
        jLabel2.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 40));

        jlblImporteBol.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jlblImporteBol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblImporteBol.setText("$0.0");
        jlblImporteBol.setMaximumSize(new java.awt.Dimension(80, 14));
        jlblImporteBol.setMinimumSize(new java.awt.Dimension(80, 14));
        jlblImporteBol.setPreferredSize(new java.awt.Dimension(70, 40));

        jlblImporteJuconi.setFont(new java.awt.Font("Tahoma", 1, 15));
        jlblImporteJuconi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblImporteJuconi.setText("$0.0");
        jlblImporteJuconi.setMaximumSize(new java.awt.Dimension(80, 14));
        jlblImporteJuconi.setMinimumSize(new java.awt.Dimension(80, 14));
        jlblImporteJuconi.setPreferredSize(new java.awt.Dimension(70, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Donativo JUCONI:");
        jLabel5.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel5.setPreferredSize(new java.awt.Dimension(70, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("TOTAL:");
        jLabel6.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 40));

        jlblImporteTotal.setFont(new java.awt.Font("Tahoma", 1, 15));
        jlblImporteTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlblImporteTotal.setText("$0.0");
        jlblImporteTotal.setMaximumSize(new java.awt.Dimension(80, 14));
        jlblImporteTotal.setMinimumSize(new java.awt.Dimension(80, 14));
        jlblImporteTotal.setPreferredSize(new java.awt.Dimension(70, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("¡¡¡  GRACIAS POR SU DONATIVO !!!");
        jLabel8.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel8.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 40));

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(35, 35, 35)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 61, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(jlblImporteTotal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(22, 22, 22))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jlblImporteJuconi, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jlblImporteBol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 93, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(31, 31, 31))))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(51, 51, 51))
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jlblImporteBol, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jlblImporteJuconi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(51, 51, 51))))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(153, 153, 153)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlblImporteTotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 59, Short.MAX_VALUE)
                .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(59, 59, 59))
        );

        jPanelImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vista Previa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLblImagen.setFont(new java.awt.Font("Tahoma", 1, 11));

        org.jdesktop.layout.GroupLayout jPanelImagenLayout = new org.jdesktop.layout.GroupLayout(jPanelImagen);
        jPanelImagen.setLayout(jPanelImagenLayout);
        jPanelImagenLayout.setHorizontalGroup(
            jPanelImagenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLblImagen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelImagenLayout.setVerticalGroup(
            jPanelImagenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelImagenLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .add(jLblImagen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 206, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(43, 43, 43))
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
                .add(184, 184, 184))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanelImagen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .add(jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanelImagen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void interfazColor(){
        setBackground(ColoresInterfaz.cFondoVentana);
        jPanelImagen.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel1.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel3.setBackground(ColoresInterfaz.cFondoVentana);
        jPanel4.setBackground(ColoresInterfaz.cFondoVentana);
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


        //jLblBarraEstado.setForeground(ColoresInterfaz.cTexto1);

        jLabel1.setForeground(ColoresInterfaz.cTexto1);
        jLblBarraEstado.setFont(ColoresInterfaz.fuente2);

        jLabel2.setForeground(ColoresInterfaz.cTexto1);
        jLabel2.setFont(new Font("Calibri",1,24));
        jLabel5.setForeground(ColoresInterfaz.cTexto1);
        jLabel5.setFont(new Font("Calibri",1,24));
        jLabel6.setForeground(ColoresInterfaz.cTexto1);
        jLabel6.setFont(new Font("Calibri",1,24));
        jLabel8.setForeground(ColoresInterfaz.cTexto1);
        jLabel8.setFont(new Font("Calibri",1,24));

        jlblImporteBol.setForeground(ColoresInterfaz.cTexto1);
        jlblImporteBol.setFont(new Font("Calibri",1,24));
        jlblImporteJuconi.setForeground(ColoresInterfaz.cTexto1);
        jlblImporteJuconi.setFont(new Font("Calibri",1,24));
        jlblImporteTotal.setForeground(ColoresInterfaz.cTexto1);
        jlblImporteTotal.setFont(new Font("Calibri",1,24));
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
            if(ventaJuconi) jLabel8.setText("¡¡ GRACIAS POR SU DONATIVO !!");
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
        if(JTblProductosER.getRowCount()>0)
            JTblProductosER.setRowSelectionInterval(0,0);
        JTblProductosER.requestFocus();
    }

    private void sumarTotal(float monto )
    {
        this.montoTotal += monto;
        jtxt_total.setText("$"+customFormat("##,##0.00",montoTotal));
        jlblImporteTotal.setText("$"+customFormat("##,##0.00",(montoTotal+impBol)));
        jlblImporteJuconi.setText("$"+customFormat("##,##0.00",montoTotal));
    }

    private void restarTotal(float monto )
    {
        this.montoTotal -= monto;
        jtxt_total.setText("$"+customFormat("##,##0.00",montoTotal));
        jlblImporteTotal.setText("$"+customFormat("##,##0.00",(montoTotal+impBol)));
        jlblImporteJuconi.setText("$"+customFormat("##,##0.00",montoTotal));
    }

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
                        jLabel8.setText("");
                    }
                  }
              break;
              case KeyEvent.VK_F10:
                String empresa="";
                String producto="";
                long cantidad=0;
                String folio=this.folio;//SeetingTMS.generico_tmsRemote.getTicketCompra(getPrefijo());
                String clave_producto="";
                float precio_unitario=0;
                float subtotal=0;
                float iva=0;
                float total=0;
                float retencion=0;
                pructosAgregados = new ArrayList<ProductoCarrito>();
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
                        pructosAgregados.add(new ProductoCarrito(product.getProductoId(),empresa, producto, cantidad, folio, clave_producto, precio_unitario, subtotal, iva, total, retencion, corteId, caja, cajero, terminal, getPrefijo(), usuarioId,product.getAdicional2(),product.getAdicional4().equals("S"),product.getEmpresaDescripcion()));
                        //SeetingTMS.generico_tmsRemote.registrarVentaProducto(empresa, producto, cantidad, folio, clave_producto, precio_unitario, subtotal, iva, total, retencion, corteId, caja, cajero, terminal, getPrefijo(), usuarioId);
                    }
                }
               }
                  this.setVenta(true);
                  this.dispose();
              break;

            }
        }
    }//GEN-LAST:event_JTblResumenKeyReleased

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
                JdlgVentaCarritoProductosER dialog = new JdlgVentaCarritoProductosER(new javax.swing.JFrame(), true,null,"","",1,1000,"122323",new ArrayList<ProductoCarrito>(),480,"F",false);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JLabel jLblImagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlblImporteBol;
    private javax.swing.JLabel jlblImporteJuconi;
    private javax.swing.JLabel jlblImporteTotal;
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

    public List<ProductoCarrito> getProductosAgregados(){
        return pructosAgregados;
    }

    public float getTotalProductorAgregados(){
        return this.montoTotal;
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
        JDlgSiNo DialogoSiNo = new JDlgSiNo("¡Confirme!","¿Desea cerrar la ventana de Productos sin agregar productos?",false);
        if(DialogoSiNo.getResultado())
        {
            this.setVenta(false);
          this.dispose();
        }
        else
            this.setAlwaysOnTop(true);
    }

}
