/*
 * Desktop.java
 *
 * Created on 4 de junio de 2008, 04:47 PM
 */

package tmslecturaviaxer;

import java.util.Vector;

/**
 *
 * @author  vgonzalez
 */
public class Desktop extends javax.swing.JFrame {
    
    /** Creates new form Desktop */
    public Desktop() {
        initComponents();
        Vector datos = new Vector();
        datos.add("21");//"7");
        datos.add("800");//"6000");
        datos.add("algun usuario");
        datos.add("5001321");
        datos.add("309");   
        datos.add("192.168.16.153");   
        datos.add("3700");
        System.out.println("Manda a crear una instancia de JIFLecturaViaxer...");
         JIFLecturaViaxer jif =   new JIFLecturaViaxer(datos);
         jif.setVisible(true);          
         desktop.add(jif);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        desktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(desktop, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(desktop, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Desktop().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    // End of variables declaration//GEN-END:variables
    
}
