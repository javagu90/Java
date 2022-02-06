/*
 * JDesktop_Frame.java
 *
 * Created on 27 de septiembre de 2007, 01:29 PM
 */

package tmsrecaudacion;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

/**
 *
 * @author  vgonzalez
 */
public class JDesktop_Frame extends javax.swing.JFrame {//javax.swing.JFrame {//
    
    /** Creates new form JDesktop_Frame */
    public JDesktop_Frame() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
       Vector datos = new Vector();
        datos.add("45");//7");
        datos.add("6000");//6000");
        datos.add("Usuario Recauda");
        datos.add("150211292");//1901210");
        datos.add("15");
        datos.add("192.168.106.31");// 192.168.106.33  192.168.16.153 -- QA 192.168.16.54
        datos.add("3820");  //3700    --  QA24796

//        datos.add("18");//"7");
//        datos.add("102");//"6000");
//        datos.add("algun usuario");
//        datos.add("200");
//        datos.add("309");   
        
        JIFRecaudacion pr = new JIFRecaudacion(datos);
        pr.setVisible(true);
        jDesktopPane1.add(pr,2);        
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
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDesktop_Frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables
    
}
