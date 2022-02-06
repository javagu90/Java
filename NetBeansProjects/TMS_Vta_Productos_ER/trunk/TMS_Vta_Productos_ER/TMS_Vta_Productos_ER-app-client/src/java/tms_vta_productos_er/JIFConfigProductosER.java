/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JIFConfigProductosER.java
 *
 * Created on 3/08/2012, 01:42:59 PM
 */

package tms_vta_productos_er;

import TMSVtaProductosER.entidad.Producto;
import java.util.List;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vgonzalez
 */
public class JIFConfigProductosER extends javax.swing.JInternalFrame {
    private Vector datosIniciales;
    private facadeVtaProductos SeetingTMS;
    private Vector header ;
    private long usuarioId;
    private Vector terminales;
    private Vector empresas;
    private String terminalActual;
    private DefaultTableModel modelo;
    private List<Producto> lista;
   

    /** Creates new form JIFConfigProductosER */
    public JIFConfigProductosER(Vector pDatosIniciales) {
        initComponents();
        this.setTitle("VENTA DE PRODUCTOS ER");
        JDialog.setDefaultLookAndFeelDecorated(true);
        datosIniciales = pDatosIniciales;
        this.setUsuarioId(Long.parseLong(datosIniciales.get(0).toString()));
        header = new Vector();
        header.add("CLAVE");
        header.add("NOMBRE");
        header.add("EXISTENCIA");
        header.add("ACTIVO");
        System.out.println("Venta Productos Config Rev.131014");
        modelo = (DefaultTableModel)jTableProductosER.getModel();
        SetsizeTabla();
        SeetingTMS = new facadeVtaProductos();
        SeetingTMS.lookupGenerico();
        terminales = SeetingTMS.generico_tmsRemote.getTerminales();
        empresas = SeetingTMS.generico_tmsRemote.getEmpresas();
        terminalActual = SeetingTMS.generico_tmsRemote.getTerminalActual();

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbtnBuscar = new javax.swing.JButton();
        jTxtClave = new tms_TextFields.JTextTextField();
        jTxtNombre = new tms_TextFields.JTextTextField();
        jPanel2 = new javax.swing.JPanel();
        JbtnNuevo = new javax.swing.JButton();
        JbtnDetalle = new javax.swing.JButton();
        JbtnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductosER = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSQUEDA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Clave Producto:");

        jLabel2.setText("Nombre Producto:");

        jbtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/view.png"))); // NOI18N
        jbtnBuscar.setText("Buscar");
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jTxtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 337, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 49, Short.MAX_VALUE)
                        .add(jbtnBuscar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jTxtClave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 136, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTxtClave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTxtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jbtnBuscar))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JbtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/add.png"))); // NOI18N
        JbtnNuevo.setText("Nuevo");
        JbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnNuevoActionPerformed(evt);
            }
        });

        JbtnDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/document_edit.png"))); // NOI18N
        JbtnDetalle.setText("Detalle");
        JbtnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnDetalleActionPerformed(evt);
            }
        });

        JbtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/door_in.png"))); // NOI18N
        JbtnSalir.setText("Salir");
        JbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSalirActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, JbtnDetalle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .add(JbtnNuevo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .add(JbtnSalir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(JbtnNuevo)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(JbtnDetalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(JbtnSalir)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTableProductosER.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLAVE", "NOMBRE", "EXISTENCIA", "ACTIVO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProductosER.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableProductosER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductosERMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProductosER);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarActionPerformed
        // TODO add your handling code here:
        buscar();
}//GEN-LAST:event_jbtnBuscarActionPerformed

    private void JbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnNuevoActionPerformed
        // TODO add your handling code here:
        JdlgConfProductoER jdlg =  new JdlgConfProductoER(this,true, this.SeetingTMS,this.usuarioId,terminalActual,null,this.terminales,this.empresas);
        jdlg.setVisible(true);
        buscar();
}//GEN-LAST:event_JbtnNuevoActionPerformed

    private void JbtnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnDetalleActionPerformed
        // TODO add your handling code here:
        if ( jTableProductosER.getSelectedRow() >=0){
            JdlgConfProductoER jdlg =  new JdlgConfProductoER(this,true, this.SeetingTMS,this.usuarioId,terminalActual,lista.get(jTableProductosER.getSelectedRow()),this.terminales,this.empresas);
            jdlg.setVisible(true);
            buscar();
        }
}//GEN-LAST:event_JbtnDetalleActionPerformed

    private void JbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_JbtnSalirActionPerformed

    private void jTableProductosERMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductosERMouseClicked
        //System.out.println("EL click lo hizo: "+evt.getComponent());
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && jTableProductosER.getSelectedRow() >=0)
           JbtnDetalle.doClick(); //detalle();
}//GEN-LAST:event_jTableProductosERMouseClicked

    public void SetsizeTabla()
    {
        jTableProductosER.getColumnModel().getColumn(0).setPreferredWidth(95);
        jTableProductosER.getColumnModel().getColumn(1).setPreferredWidth(245);
        jTableProductosER.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTableProductosER.getColumnModel().getColumn(3).setPreferredWidth(50);

    }

  public void limpiaTabla(){
    int rows=modelo.getRowCount();
    for(int j=0; j<rows; j++) modelo.removeRow(0);

    modelo.setDataVector(null,header);

    jTableProductosER.setModel(modelo);
}

private void buscar(){
    /*if(jTxtClave.getText().trim().toString().equals("") && jTxtNombre.getText().trim().toString().equals(""))
    {
      JOptionPane.showMessageDialog(this,  "Debes ingresar al menos un filtro para realizar la búsqueda", "Error",JOptionPane.ERROR_MESSAGE);
      return;
    }*/
    lista = SeetingTMS.generico_tmsRemote.getProductos(jTxtClave.getText().trim().toString(),jTxtNombre.getText().trim().toString(),terminales,empresas);
    if(lista.size()==0)
    {
      JOptionPane.showMessageDialog(this,  "No existen productos que coincidan con los filtros ingresados", "Advertencia",JOptionPane.WARNING_MESSAGE);
      return;
    }
    limpiaTabla();
    for(Producto p : lista)
    {
        Object[] fila = new Object[4];
        fila[0] = p.getProductoClave();
        fila[1] = p.getProductoNombre();
        fila[2] = p.getProductoExistencia()==-1?"":p.getProductoExistencia();
        fila[3] = !p.isEliminado();
        modelo.addRow(fila);
    }
    this.jTableProductosER.setModel(modelo);
    SetsizeTabla();
 }
    
    
    /**
     * @return the datosIniciales
     */
    public Vector getDatosIniciales() {
        return datosIniciales;
    }

    /**
     * @param datosIniciales the datosIniciales to set
     */
    public void setDatosIniciales(Vector datosIniciales) {
        this.datosIniciales = datosIniciales;
    }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnDetalle;
    private javax.swing.JButton JbtnNuevo;
    private javax.swing.JButton JbtnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProductosER;
    private tms_TextFields.JTextTextField jTxtClave;
    private tms_TextFields.JTextTextField jTxtNombre;
    private javax.swing.JButton jbtnBuscar;
    // End of variables declaration//GEN-END:variables

}