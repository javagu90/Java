/*
 * Main.java
 *
 * Created on 7 de enero de 2009, 09:39 AM
 */

package tmsfacturarcodigobarras;

import java.util.Vector;

/**
 *
 * @author  asolis
 */
public class Main extends javax.swing.JFrame {
    
    /** Creates new form Main */
    public Main() {
        initComponents();
        Vector vec = new Vector();
        vec.add("011");
        vec.add("169");
        vec.add("Usuario");
        vec.add("1503322");   // session
        vec.add("CAPU");
        vec.add("TCAPU_LINK.ESTRELLAROJA.COM.MX");
        vec.add("TCAPU_LINK.ESTRELLAROJA.COM.MX");
        JIF_Facturar var = new JIF_Facturar(vec);
        jDesktopPane1.add(var);
        jDesktopPane1.setSize(1027,728);
        jDesktopPane1.setVisible(true);
        this.setSize(1100,768);
        var.setSize(950,740);
        var.setVisible(true);
        this.setVisible(true);
        var.requestFocus();
        //PrintService 
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1850, Short.MAX_VALUE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables
    
}