/*
 * JDlgDatosAdicionales.java
 *
 * Created on 19 de noviembre de 2007, 10:28 PM
 */

package Dialogos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_guardias.SesionVenta;

/**
 *
 * @author  ocruz
 */
public class JDlgDatosAdicionales extends javax.swing.JDialog {
public boolean FDoOperacion = false;     
    /** Creates new form JDlgDatosAdicionales */
    public JDlgDatosAdicionales(SesionVenta pSesionVenta) {
        this.sesionVenta = pSesionVenta;
        datos = new Object[sesionVenta.xDatosAdicionales().length][2];
        for(int i=0; i<datos.length; i++){
            datos[i][0] = sesionVenta.xDatosAdicionales()[i][0];
            datos[i][1] = "";
        }
        this.setModal(true);
        initComponents();
        xTabla.setDataVector(datos, encabezado);
        centrarJDialog();
        FDoOperacion = false;
    }
    
    private void AnchoColumnas() {
        int anchoContenedor = jScrollPane1.getWidth();
        TableColumn column;
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            column = jTable1.getColumnModel().getColumn(i);
            switch (i) {
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.50)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.50)); break;
            }
        }
    }
    
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
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Datos Adicionales");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("<html><font color=ff0000>F9</font> Continuar Autorizacion<font color=ff0000>  ESC</font> Regresar</html>");

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setModel(xTabla);
        jTable1.setFocusTraversalKeysEnabled(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .add(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 191, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel1))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
        case KeyEvent.VK_F9:
            System.out.println("Entrado a F9");
                FDoOperacion = true;
                for(int i=0; i<datos.length; i++){
                    if(xTabla.getValueAt(i,1).toString().equals("")){
                        DialogoAceptar = new JDlgAceptar("�Datos Obligatorios!", "Ingrese valores.", Color.RED);
                        jTable1.setColumnSelectionInterval(1,1);
                        jTable1.setRowSelectionInterval(i,i);
                        jTable1.requestFocusInWindow();
                      return;
                    }
                    sesionVenta.xDatosAdicionales()[i][2] = xTabla.getValueAt(i,1).toString();
                }
      
        /*    case 82:
                FDoOperacion = true;
                 for(int i=0; i<datos.length; i++){
                    if(xTabla.getValueAt(i,1).toString().equals("")){
                        DialogoAceptar = new JDlgAceptar("�Datos Obligatorios!", "Ingrese valores.", Color.RED);
                        jTable1.setColumnSelectionInterval(1,1);
                        jTable1.setRowSelectionInterval(i,i);
                        jTable1.requestFocusInWindow();
                         
                        return;
                    }
                    sesionVenta.xDatosAdicionales()[i][2] = xTabla.getValueAt(i,1).toString();
                } FDoOperacion = true;*/
               case KeyEvent.VK_ESCAPE:
               {  this.dispose(); 
                       return;   }
            //this.dispose();  
            //return;
        }
      
    }//GEN-LAST:event_jTable1KeyPressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        AnchoColumnas();
        jTable1.setRowSelectionInterval(0,0);
        jTable1.setColumnSelectionInterval(1,1);
        jTable1.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezado={"Campo", "Valor"};
    private DefaultTableModel xTabla = new DefaultTableModel(null,encabezado){
        public boolean isCellEditable(int row, int col){
            if(col==0) return false;
            return true;
        }
    };
    private Object[][] datos = null;
    private SesionVenta sesionVenta;

    private JDlgAceptar DialogoAceptar;
}
