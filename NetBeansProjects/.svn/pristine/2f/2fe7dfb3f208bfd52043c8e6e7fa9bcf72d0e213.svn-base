/*
 * JdlgConfFlotilla.java
 *
 * Created on 2 de junio de 2010, 06:11 PM
 */

package tms_vta_productos_er;

import TMSVtaProductosER.entidad.Producto;
import TMSVtaProductosER.entidad.Tarifa;
import com.sun.codemodel.JOp;
import com.sun.mail.iap.ByteArray;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_TextFields.JCuantityTextField;
import tms_TextFields.JDateTextField;
import tms_TextFields.JNumberTextField;
import tms_TextFields.JTextTextField;


/**
 *
 * @author  brojasa
 */
public class JdlgConfProductoER extends javax.swing.JDialog {
   
    private byte[] imagenPantalla;
    private facadeVtaProductos SeetingTMS;
    private long usuarioId;
    private Producto cp;
    private Object[] encTarifas = {"Tipo", "Moneda", "Importe Tarifa", "Fecha Tarifa", "Sucursal","id"};
    private DefaultTableModel modeloTarifas = null;
    private JComboBox jcmbTipoTarifa = new JComboBox();
    private JComboBox jcmbTipoMoneda = new JComboBox();
    private JComboBox jcmbTerminales = new JComboBox();
    private String terminalActual;
    private Vector terminales;
    private Vector empresas;
    private Vector tarifasEliminadas;
    private Object []row = new Object[6];
            
      
    /** Creates new form JdlgConfFlotilla */
    public JdlgConfProductoER(JInternalFrame parent, boolean modal,facadeVtaProductos pSeetingTMS, long  pusuarioId,String pterminalActual, Producto pcp, Vector pTerminales, Vector pempresas) {
       // super(parent, modal);
        this.setModal(modal);
        initComponents();
        JDialog.setDefaultLookAndFeelDecorated(true);
        this.SeetingTMS = pSeetingTMS;
        this.usuarioId  = pusuarioId;
        this.terminalActual = pterminalActual;
        this.terminales = pTerminales;
        this.empresas = pempresas;
        tarifasEliminadas = new Vector();
        centrar();
        this.setTitle("Productos ER");
        this.cp = pcp;
        System.out.println("Venta Productos Rev.131014");
        //limpiar(jPanelAutobus.getComponents());
        //limpiar(jPanelNombre.getComponents());
        jCmbEmpresa.removeAllItems();
        jCmbEmpresa.addItem("NO ASIGNADA");
        for(int i=0 ; i<empresas.size();i++)
        {
            Vector emp = (Vector)empresas.get(i);
            jCmbEmpresa.addItem(emp.get(1));
        }
        jCmbEmpresa.setSelectedIndex(0);
        //limpiar(jPanelCheckbox.getComponents());
        //SeetingTMS = pSeetingTMS;
        modeloTarifas = new DefaultTableModel(null, encTarifas);
        JtblTarifas.setModel(modeloTarifas);
        JtblTarifas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JtblTarifas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        AnchoColumnasServicios();
        jCmbxActivo.setSelected(true);
        jLblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/sinVista2.png")));
        if(this.cp != null)
          showData(this.cp); 
        
        
     
    }
    
    private void agregarTarifa(Tarifa pTarifa){
        if(pTarifa !=null)
        {
            row[0]=pTarifa.getTipoTarifa();// "CADA UNO";
            row[1]=pTarifa.getTipoMonedaTarifa();// "MXP";
            row[2]=pTarifa.getImporteTarifa();// "";
            row[3]=pTarifa.getFechaTarifa();//"";
            row[4]=pTarifa.getTerminalNombreTarifa();
            row[5]=pTarifa.getTarifaId(); //"-1";
        }
        else
        {
            row[0]="CADA UNO";
            row[1]="MXP";
            row[2]="";
            row[3]="";
            row[4]=terminalActual;
            row[5]="-1";
        }
        modeloTarifas.addRow(row);
        jcmbTipoTarifa.removeAllItems();
        jcmbTipoTarifa.addItem("CADA UNO");
        jcmbTipoTarifa.addItem("2 Ó MAS");
        jcmbTipoTarifa.addItem("3 Ó MAS");
        jcmbTipoTarifa.addItem("4 Ó MAS");
        jcmbTipoTarifa.addItem("5 Ó MAS");
        jcmbTipoMoneda.removeAllItems();
        jcmbTipoMoneda.addItem("MXP");
        jcmbTipoMoneda.addItem("USD");
        TableColumn sportColumn = JtblTarifas.getColumnModel().getColumn(0);
        jcmbTipoTarifa.setSelectedIndex(0);
        sportColumn.setCellEditor(new DefaultCellEditor(jcmbTipoTarifa));
        jcmbTipoTarifa.setSelectedIndex(0);
            
        sportColumn = JtblTarifas.getColumnModel().getColumn(1);
        sportColumn.setCellEditor(new DefaultCellEditor(jcmbTipoMoneda));
        jcmbTipoMoneda.setSelectedIndex(0);

        sportColumn = JtblTarifas.getColumnModel().getColumn(2);
        JCuantityTextField c = new JCuantityTextField();
        c.setHorizontalAlignment(JTextField.RIGHT);
        sportColumn.setCellEditor(new DefaultCellEditor(c));

        sportColumn = JtblTarifas.getColumnModel().getColumn(3);
        JDateTextField f = new JDateTextField();
        sportColumn.setCellEditor(new DefaultCellEditor(f));

        sportColumn = JtblTarifas.getColumnModel().getColumn(4);
        jcmbTerminales.removeAllItems();
        jcmbTerminales.addItem("TODAS");
        if(terminales != null)
        {
            for(int i=0; i<terminales.size();i++)
            {
                Vector v  = (Vector)terminales.get(i);
                jcmbTerminales.addItem(v.get(1).toString());
            }
        }
        jcmbTerminales.setSelectedIndex(0);
        sportColumn.setCellEditor(new DefaultCellEditor(jcmbTerminales));
        
        AnchoColumnasServicios();
        
    }

    private long getTerminalId(String terminalNombre)
    {
        long id = -1;
        for(int y=0; y<terminales.size();y++)
        {
            Vector v = (Vector)terminales.get(y);
            if(v.get(1).toString().equals(terminalNombre))
                return Long.valueOf(v.get(0).toString());
        }
        return -1;
    }

    private long getEmpresaId(String empresaNombre)
    {
        long id = -1;
        for(int y=0; y<empresas.size();y++)
        {
            Vector v = (Vector)empresas.get(y);
            if(v.get(1).toString().equals(empresaNombre))
                return Long.valueOf(v.get(0).toString());
        }
        return -1;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelNombre = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelImagen = new javax.swing.JLabel();
        jBtnImg = new javax.swing.JButton();
        jTxtImagenRuta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTxtExistencia = new tms_TextFields.JNumberTextField();
        jLabel11 = new javax.swing.JLabel();
        jCmbEmpresa = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jCmbxActivo = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jTxtClave = new tms_TextFields.JTextTextField();
        jTxtNombre = new tms_TextFields.JTextTextField();
        jTxtDescripcion = new tms_TextFields.JTextTextField();
        jTxtCategoria = new tms_TextFields.JTextTextField();
        jPanelAutobus = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtblTarifas = new javax.swing.JTable();
        jPanelImagen = new javax.swing.JPanel();
        jLblImagen = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        JbtnSalir = new javax.swing.JButton();
        JbtnGuardar = new javax.swing.JButton();
        JbtnAgregar = new javax.swing.JButton();
        JbtnQuitar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelNombre.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRDUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripcion:");

        jLabelImagen.setText("Imagen:");

        jBtnImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/folder_image.png"))); // NOI18N
        jBtnImg.setMnemonic('E');
        jBtnImg.setText("Seleccionar");
        jBtnImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnImgActionPerformed(evt);
            }
        });

        jTxtImagenRuta.setEnabled(false);

        jLabel3.setText("Categoria:");

        jLabel10.setText("Empresa:");

        jTxtExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtExistenciaActionPerformed(evt);
            }
        });

        jLabel11.setText("En Existencia:");

        jLabel12.setText("Activo:");

        jCmbxActivo.setSelected(true);
        jCmbxActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbxActivoActionPerformed(evt);
            }
        });

        jLabel4.setText("Clave:");

        org.jdesktop.layout.GroupLayout jPanelNombreLayout = new org.jdesktop.layout.GroupLayout(jPanelNombre);
        jPanelNombre.setLayout(jPanelNombreLayout);
        jPanelNombreLayout.setHorizontalGroup(
            jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelNombreLayout.createSequentialGroup()
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel12)
                    .add(jLabel4)
                    .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(jLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanelNombreLayout.createSequentialGroup()
                            .addContainerGap()
                            .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel1)
                                .add(jLabelImagen)
                                .add(jLabel3)))
                        .add(jPanelNombreLayout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel10))))
                .add(25, 25, 25)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTxtCategoria, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTxtDescripcion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jBtnImg, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtImagenRuta, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .add(jCmbEmpresa, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTxtClave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtNombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanelNombreLayout.createSequentialGroup()
                        .add(1, 1, 1)
                        .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jCmbxActivo)
                            .add(jTxtExistencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanelNombreLayout.setVerticalGroup(
            jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelNombreLayout.createSequentialGroup()
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jTxtClave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTxtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTxtDescripcion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jBtnImg)
                    .add(jLabelImagen))
                .add(12, 12, 12)
                .add(jTxtImagenRuta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jTxtCategoria, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(jCmbEmpresa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(jTxtExistencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(9, 9, 9)
                .add(jPanelNombreLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jCmbxActivo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel12))
                .addContainerGap())
        );

        jPanelAutobus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TARIFAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JtblTarifas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JtblTarifas);

        org.jdesktop.layout.GroupLayout jPanelAutobusLayout = new org.jdesktop.layout.GroupLayout(jPanelAutobus);
        jPanelAutobus.setLayout(jPanelAutobusLayout);
        jPanelAutobusLayout.setHorizontalGroup(
            jPanelAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelAutobusLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 481, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelAutobusLayout.setVerticalGroup(
            jPanelAutobusLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelAutobusLayout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelImagen.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagen"));

        org.jdesktop.layout.GroupLayout jPanelImagenLayout = new org.jdesktop.layout.GroupLayout(jPanelImagen);
        jPanelImagen.setLayout(jPanelImagenLayout);
        jPanelImagenLayout.setHorizontalGroup(
            jPanelImagenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLblImagen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );
        jPanelImagenLayout.setVerticalGroup(
            jPanelImagenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelImagenLayout.createSequentialGroup()
                .add(23, 23, 23)
                .add(jLblImagen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 206, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        JbtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/door_in.png"))); // NOI18N
        JbtnSalir.setText("Salir   ");
        JbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSalirActionPerformed(evt);
            }
        });

        JbtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/disk_blue.png"))); // NOI18N
        JbtnGuardar.setText("Guardar");
        JbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnGuardarActionPerformed(evt);
            }
        });

        JbtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/add.png"))); // NOI18N
        JbtnAgregar.setText("Agregar");
        JbtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnAgregarActionPerformed(evt);
            }
        });

        JbtnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/cancel.png"))); // NOI18N
        JbtnQuitar.setText("Eliminar");
        JbtnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnQuitarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(JbtnGuardar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(JbtnQuitar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(JbtnAgregar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .add(JbtnSalir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(JbtnGuardar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(JbtnAgregar)
                .add(11, 11, 11)
                .add(JbtnQuitar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(JbtnSalir)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(jPanelAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(27, 27, 27)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jPanelNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanelImagen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanelImagen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jPanelAutobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSalirActionPerformed
// TODO add your handling code here:
        //this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_JbtnSalirActionPerformed

    private void JbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnGuardarActionPerformed
    // TODO add your handling code here:
        if (JtblTarifas.getCellEditor() != null) {
            JtblTarifas.getCellEditor().stopCellEditing();
        }
        guardar();
    }//GEN-LAST:event_JbtnGuardarActionPerformed

    private void jBtnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnImgActionPerformed
        JFileChooser fc = new JFileChooser();
        
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setFileView(new ImageFileView());
        fc.setAccessory(new ImagePreview(fc));
        
        int returnVal = fc.showDialog(this,"Elegir");
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            ImageIcon tmpIcon = new ImageIcon(file.getPath());
            
            System.out.println("Ancho "+tmpIcon.getIconWidth());
            System.out.println("Alto  "+tmpIcon.getIconHeight());
                                      //210                              //150
            if (tmpIcon.getIconWidth() >260  ||  tmpIcon.getIconHeight() > 200)
            {
              JOptionPane.showMessageDialog(this,  "El tamaño de la imagen es mayor a 260 x 200", "Error",JOptionPane.ERROR_MESSAGE);
              imagenPantalla = null;                                                //205 x 150
              return;  //   
            }
            
            imagenPantalla = imageIconToByteArray(tmpIcon);
            jTxtImagenRuta.setText("Archivo: " + file.getName());
            jLblImagen.setIcon(tmpIcon);
        }
    }//GEN-LAST:event_jBtnImgActionPerformed

    private void jTxtExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtExistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtExistenciaActionPerformed

    private void jCmbxActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbxActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCmbxActivoActionPerformed

    private void JbtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnAgregarActionPerformed
        agregarTarifa(null);
    }//GEN-LAST:event_JbtnAgregarActionPerformed

    private void JbtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnQuitarActionPerformed
        if(JtblTarifas.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(this,"¡Debes seleccionar una tarifa!","¡Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar la tarifa del "+JtblTarifas.getValueAt(JtblTarifas.getSelectedRow(), 3)+" ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            if (JtblTarifas.getCellEditor() != null) {
                JtblTarifas.getCellEditor().stopCellEditing();
            }
            getTarifasEliminadas().add(JtblTarifas.getValueAt(JtblTarifas.getSelectedRow(), 5));
            int index = JtblTarifas.getSelectedRow();
            modeloTarifas.removeRow(index);
            modeloTarifas.fireTableRowsDeleted(index, index);
            modeloTarifas.fireTableDataChanged();
            JtblTarifas.setModel(modeloTarifas);
        }
            }//GEN-LAST:event_JbtnQuitarActionPerformed
      
     
    /**
     * @param args the command line arguments   
     */
   /* public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JdlgConfFlotilla(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }*/
    
      private byte[] imageIconToByteArray(ImageIcon icon) {

        BufferedImage bi = new BufferedImage(icon.getImage().getWidth(null), icon.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();

        g.drawImage(icon.getImage(), 0, 0, null);
        g.dispose();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi,"png",stream);
        } catch (IOException ex1) { }

    return stream.toByteArray();

}     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnAgregar;
    private javax.swing.JButton JbtnGuardar;
    private javax.swing.JButton JbtnQuitar;
    private javax.swing.JButton JbtnSalir;
    private javax.swing.JTable JtblTarifas;
    private javax.swing.JButton jBtnImg;
    private javax.swing.JComboBox jCmbEmpresa;
    private javax.swing.JCheckBox jCmbxActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLblImagen;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelAutobus;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JPanel jPanelNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private tms_TextFields.JTextTextField jTxtCategoria;
    private tms_TextFields.JTextTextField jTxtClave;
    private tms_TextFields.JTextTextField jTxtDescripcion;
    private tms_TextFields.JNumberTextField jTxtExistencia;
    private javax.swing.JTextField jTxtImagenRuta;
    private tms_TextFields.JTextTextField jTxtNombre;
    // End of variables declaration//GEN-END:variables

   public void buscaImagen()
   {
      JFileChooser fc = new JFileChooser();
        
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setFileView(new ImageFileView());
        fc.setAccessory(new ImagePreview(fc));
        
        int returnVal = fc.showDialog(this,"Elegir");
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            ImageIcon tmpIcon = new ImageIcon(file.getPath());
            
            imagenPantalla = imageIconToByteArray(tmpIcon);
            
            jTxtImagenRuta.setText("Archivo: " + file.getName());
            jLblImagen.setIcon(tmpIcon);
        }
   } 
    
   
 /*  public void getPlantilla()
   {
   jcmbPlantilla.removeAllItems();
   
   Vector vplantillas= this.SeetingTMS. generico_tmsRemote.getPlantillas();
   Vector vdata= null;
   System.out.println("GetPlatillas "+vplantillas);
   AutobusPlantillasEnc plantillas = null;
   for (int i = 0; i < vplantillas.size(); i++) {
           vdata = (Vector)vplantillas.elementAt(i);
        
           plantillas = new AutobusPlantillasEnc(vdata.elementAt(0)+"",
                                                 vdata.elementAt(1)+"",
                                                 vdata.elementAt(2)+"");
        
           //jcmbPlantilla.addItem((AutobusPlantillasEnc)vplantillas.elementAt(i) );
           jcmbPlantilla.addItem(plantillas);
       }
   }*/
   
   
   public Producto getProducto()
   {
       Producto Config_Producto = new Producto();
       if(this.cp!=null)
         Config_Producto.setProductoId(this.cp.getProductoId());
     //String cade = "";
     Config_Producto.setProductoClave(!jTxtClave.getText().equals("")? jTxtClave.getText().trim(): "");
     Config_Producto.setProductoNombre(!jTxtNombre.getText().equals("")? jTxtNombre.getText().trim(): "");
     Config_Producto.setProductoDescipcion((!jTxtDescripcion.getText().equals("")? jTxtDescripcion.getText().trim(): ""));
     Config_Producto.setEmpresaId((jCmbEmpresa.getSelectedItem().toString().equals("NO ASIGNADA"))?-1:getEmpresaId(jCmbEmpresa.getSelectedItem().toString()));
     if (imagenPantalla != null)
        Config_Producto.setProductoImagen(imagenPantalla);
     else
       if(this.cp!=null)
           Config_Producto.setProductoImagen(this.cp.getProductoImagen());
    Config_Producto.setProductoCategoria((jTxtCategoria.getText()!= null? jTxtCategoria.getText().trim(): ""));
    Config_Producto.setProductoExistencia((jTxtExistencia.getText().equals("")?-1 : Long.valueOf(jTxtExistencia.getText())));
    Config_Producto.setEliminado(!jCmbxActivo.isSelected());
    for(int i=0; i<JtblTarifas.getRowCount();i++)
    {
        //  0        1              2               3             4       5
        //"Tipo", "Moneda", "Importe Tarifa", "Fecha Tarifa", "Sucursal","id"
        Tarifa t = new Tarifa();

        t.setFechaTarifa(JtblTarifas.getValueAt(i,3).toString().trim());
        t.setImporteTarifa(Float.valueOf(JtblTarifas.getValueAt(i,2).toString().trim()));
        t.setTipoMonedaTarifa(JtblTarifas.getValueAt(i,1).toString().trim());
        t.setTipoTarifa(JtblTarifas.getValueAt(i,0).toString().trim());
        t.setTerminalIdTarifa(JtblTarifas.getValueAt(i,4).toString().equals("TODAS")?-1:getTerminalId(JtblTarifas.getValueAt(i,4).toString()));
        t.setTarifaId(Long.valueOf(JtblTarifas.getValueAt(i,5).toString()));
        Config_Producto.addTarifa(t);
    }
    return Config_Producto;
   }
   
   
public void showData( Producto cf)
{
   // ConfigFlotilla cf = SettingsCF_TOU.generico_TOU_Remote.getConfigFlotilla(confifFlot);
    if(cf != null){
        try {

             //System.out.println(" TRAE "+ cf.getProductoNombre());
            if(cf.getProductoImagen() != null){
               jLblImagen.setIcon(new ImageIcon( (byte[])cf.getProductoImagen()));
               imagenPantalla = null;
            }
            jTxtClave.setText(cf.getProductoClave()!=null ? cf.getProductoClave().trim():"");
            jTxtNombre.setText(cf.getProductoNombre()!=null ? cf.getProductoNombre().trim():"");
            jTxtDescripcion.setText(cf.getProductoDescipcion()!=null ? cf.getProductoDescipcion().trim():"");
            jTxtCategoria.setText(cf.getProductoCategoria()!=null ? cf.getProductoCategoria().trim():"");
            jTxtExistencia.setText(cf.getProductoExistencia()==-1?"":""+cf.getProductoExistencia());
            jCmbEmpresa.setSelectedItem(cf.getEmpresaNombre());
            jCmbxActivo.setSelected(!cf.isEliminado());
            for(Tarifa t : cf.getProductoTarifas())
                 agregarTarifa(t);
            
        } catch (Exception e) {
         e.printStackTrace();   
        }
    }
}
 public void centrar(){
       Dimension pantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = getSize();
       // Una cuenta para situar la ventana en el centro de la pantalla.
         this.setLocation((pantalla.width - ventana.width) / 2,
                                (pantalla.height - ventana.height) / 2);
    }
 
 
public boolean valida()
{
    boolean  fvalida = true;
    String msj="";
    msj=msj+(jTxtNombre.getText().trim().length()>0?"":" \n Nombre del Porducto  ");
    //msj=msj+(jTxtDescripcion.getText().trim().length()>0?"":" \n T�tulo de la flotilla  ");
    if(JtblTarifas.getRowCount()==0)
        msj=msj+("\n Tarifas");
    for(int i=0; i<JtblTarifas.getRowCount();i++)
    {
        if(JtblTarifas.getValueAt(i,2).toString().trim().equals(""))
        {
            msj=msj+("\n Tarifas");
            break;
        }
        if(JtblTarifas.getValueAt(i,3).toString().trim().length()<10)
        {
            msj=msj+("\n Tarifas");
            break;
        }
    }

    if (msj.length() > 0)
     {
         JOptionPane.showMessageDialog(this, "Verifique la siguiente información para guardar el producto \n"+msj,"Error", JOptionPane.ERROR_MESSAGE);
         fvalida = false;
     }    
    return fvalida;  
} 
  

public void guardar()
{
    if(valida())
    {
      if(this.cp != null && !(this.cp.getProductoId()+"").equalsIgnoreCase("-1")  )
       update();
     else
       insert();
   }
}
   
public void insert()
{
    if(JOptionPane.showConfirmDialog(this, "¿Seguro que desea guardar el producto con la informacion actual?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    {
       //Producto cf= getProducto();

       try{
            String res = SeetingTMS.generico_tmsRemote.agregarProducto(getProducto(),usuarioId);
            if(res.equals("valido"))
            {
                JOptionPane.showMessageDialog(this, "El producto se guardo exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else
             JOptionPane.showMessageDialog(this,  "No fue posible guardar el Producto en el Sistema. Contacte al Admistrador del Sistema", "Error",JOptionPane.ERROR_MESSAGE);
            
            }catch(EJBException ex){
              ex.printStackTrace();
           JOptionPane.showMessageDialog(this,  "No fue posible guardar el Producto en el Sistema. Contacte al Admistrador del Sistema", "Error",JOptionPane.ERROR_MESSAGE);
       }
    }
}


public void update()
{
    if(JOptionPane.showConfirmDialog(this, "¿Seguro que desea actualizar el producto con la informacion actual?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    {
       try{
           String eliminar = "";
           if(this.tarifasEliminadas.size()>0)
              eliminar = tarifasEliminadas.toString().replace("[", "").replace("]", "");
           System.out.println("tarifasEliminadas: "+eliminar);
           String res = SeetingTMS.generico_tmsRemote.actualizarProducto(getProducto(),usuarioId,eliminar);
            if(res.equals("valido"))
            {
                JOptionPane.showMessageDialog(this, "El producto se actualizó exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            else
             JOptionPane.showMessageDialog(this,  "No fue posible actualizar el Producto en el Sistema. Contacte al Admistrador del Sistema", "Error",JOptionPane.ERROR_MESSAGE);

            }catch(EJBException ex){
              ex.printStackTrace();
           JOptionPane.showMessageDialog(this,  "No fue posible actualizar el Producto en el Sistema. Contacte al Admistrador del Sistema", "Error",JOptionPane.ERROR_MESSAGE);
       }
    }
    /*
   Producto cf= getProducto();
   
    if(this.p_ConfiguraFlotilla != null){
      cf.setConfigPlantillaId(this.p_ConfiguraFlotilla.getConfigPlantillaId());
      cf.setCreadoPor(this.p_ConfiguraFlotilla.getCreadoPor());
      cf.setFechaCreacion(this.p_ConfiguraFlotilla.getFechaCreacion());
      cf.setNoUnidad(this.p_ConfiguraFlotilla.getNoUnidad());
   }
      
      cf.setUltimaActualizacionPor(p_usuario);
      
   SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
   String fecha = ((Vector)this.SeetingTOU.generico_TOU_Remote.FechaHoraServidor()).elementAt(0)+"";
   System.out.println("Fecha del servido "+fecha);
   Date dfecha= null;
   try { 
       dfecha = sf.parse(fecha);
   } catch (Exception e) {
       e.printStackTrace();
       return;
   }
   
   cf.setUltimaFechaActualizacion(dfecha);
   
   
   boolean result = this.SeetingTOU.generico_TOU_Remote.UpdateConfigFlotilla(cf); 
   System.out.println("Update con folio "+cf.getConfigPlantillaId());
   String strmsj="";
   if(result)
   {
     this.p_ConfiguraFlotilla = cf ;  
    strmsj="La configuraci�n de Flotilla fu� actualizada correctamente. ";
             JOptionPane.showMessageDialog(this,strmsj);  
             
   }
   else 
   {
       strmsj="La configuraci�n de Flotilla no pudo ser actualizada. ";
       JOptionPane.showMessageDialog(this,  strmsj, "Error",JOptionPane.ERROR_MESSAGE);
   }
   */
}

public void limpiar(java.awt.Component[] components)
    {
       // this.setCursor(busyCursor);
        Object obj=  null;
     //   System.out.println("Entrando a Limpiar");
         for (int count=0;  count<components.length; count++)
        { 
            obj = (Object)components[count];
          //  System.out.println(" obj.getClass() ---> "+obj.getClass());
            if (obj instanceof  JTextTextField  )
                ((JTextTextField)obj).setText("");
            else if (obj instanceof  JTextField  )
                ((JTextField)obj).setText("");
             else if (obj instanceof JCuantityTextField )
                   ((JCuantityTextField)obj).setText("0");
                 else if ( obj instanceof JNumberTextField)
                    ((JNumberTextField)obj).setText("0");
                   else if (obj instanceof JComboBox) 
                ((JComboBox)obj).setSelectedIndex(0);
                  else if (obj instanceof JCheckBox ) 
                ((JCheckBox)obj).setSelected(false); 
           }  // for
        this.cp = null;
        jCmbxActivo.setSelected(true);
        jLblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/sinVista2.png")));
    //          jLblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource(null)));
     }

 public void selectData_cmb(JComboBox cmb, String cmb_cve)
  {
     /*
   //  System.out.println("CVE "+cmb_cve);
      for (int i = 0; i < cmb.getItemCount(); i++) {
       //   System.out.println("Combo "+((AutobusPlantillasEnc)cmb.getItemAt(i)).getPlantilla_id());
          
            if( ((AutobusPlantillasEnc)cmb.getItemAt(i)).getPlantilla_id().equalsIgnoreCase(cmb_cve))
                {    cmb.setSelectedIndex(i);  break;  }         
      }//
      *
      */
  }

  private void AnchoColumnasServicios(){
        //JtblTarifas.setTableHeader(null);
        //jScrollPane4.setColumnHeader(null);
       // JtblTarifas.setShowGrid(false);
        JtblTarifas.setFont(JtblTarifas.getFont().deriveFont(Font.BOLD,12));
        JtblTarifas.setRowHeight(22);
        TableColumn column = null;
        for (int i = 0; i <= 5; i++) {
            column = JtblTarifas.getColumnModel().getColumn(i);
            switch(i){
                //  0        1            2                 3               4      5
                //"Tipo", "Moneda", "Importe Tarifa", "Fecha Tarifa", "Sucursal","id"
                case 0: column.setPreferredWidth(80); break;
                case 1: column.setPreferredWidth(50); break;
                case 2: column.setPreferredWidth(110); break;
                case 3: column.setPreferredWidth(100); break;
                case 4: column.setPreferredWidth(135); break;
                case 5: column.setPreferredWidth(0);
                        column.setMinWidth(0);
                        column.setMaxWidth(0);
                        break;
            }
            column.setResizable(false);
        }
    }
  
 public  void habilitaPlantilla (boolean ban)
 {
    jPanelImagen.setVisible(ban); 
   // jcmbPlantilla.setVisible(ban); 
   // jLabelPlaltillaId.setVisible(ban); 
    jBtnImg.setVisible(ban); 
    jLabelImagen.setVisible(ban); 
    jTxtImagenRuta.setVisible(ban);
    /*
    jTxtLinea7.setVisible(ban);
    jLabelCaract7.setVisible(ban); 
    jTxtLinea8.setVisible(ban); 
    jLabelCaract8.setVisible(ban);     
    jTxtLinea9.setVisible(ban); 
    jLabelCaract9.setVisible(ban); 
    jTxtLinea10.setVisible(ban); 
    jLabelCaract10.setVisible(ban); 
    jTxtLinea11.setVisible(ban); 
    jLabelCaract11.setVisible(ban); 
    jTxtLinea12.setVisible(ban); 
    jLabelCaract12.setVisible(ban); 
    jTxtLinea13.setVisible(ban); 
    jLabelCaract13.setVisible(ban); 
    jTxtLinea14.setVisible(ban); 
    jLabelCaract14.setVisible(ban); 
    jTxtLinea15.setVisible(ban); 
    jLabelCaract15.setVisible(ban); 
    jCheckBoxTV.setVisible(ban); 
    jCheckBoxAireAcondicionado.setVisible(ban); 
    jCheckBoxBanio.setVisible(ban); 
    
    jLabelOtros1.setVisible(ban); 
    jTxtOtros1.setVisible(ban); 
    jLabelOtros2.setVisible(ban); 
    jTxtOtros2.setVisible(ban);
     *
     */
 }

    /**
     * @return the tarifasEliminadas
     */
    public Vector getTarifasEliminadas() {
        return tarifasEliminadas;
    }

    /**
     * @param tarifasEliminadas the tarifasEliminadas to set
     */
    public void setTarifasEliminadas(Vector tarifasEliminadas) {
        this.tarifasEliminadas = tarifasEliminadas;
    }

//         limpiar(jPanelAutobus.getComponents());
//        limpiar(jPanelNombre.getComponents());
//        limpiar(jPanelCheckbox.getComponents());

}



