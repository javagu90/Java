/*
 * Jdesktop.java
 *
 * Created on 15 de agosto de 2007, 06:47 PM
 */

package corterecaudacion;

import java.util.Vector;



/**
 *
 * @author  vgonzalez
 */
public class Jdesktop extends javax.swing.JFrame {
    
    /** Creates new form Jdesktop */                    //P_FECHA_CORTE=TO_DATE('02/08/2007','DD/MM/RRRR')"
    public Jdesktop() {
        initComponents();
        Vector datos = new Vector();
        datos.add("18");//"7");
        datos.add("102");//"6000");
        datos.add("algun usuario");
        datos.add("200");
        datos.add("309");       
        datos.add("192.168.16.153");   
        datos.add("3700");   
        
        JIFCorteRecaudacion apli = new JIFCorteRecaudacion(datos);
              apli.setVisible(true);
              jdp.add(apli,1);
              jdp.setVisible(true);
              
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jdp = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jdp, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jdp, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             new Jdesktop().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jdp;
    // End of variables declaration//GEN-END:variables
    

   

}
