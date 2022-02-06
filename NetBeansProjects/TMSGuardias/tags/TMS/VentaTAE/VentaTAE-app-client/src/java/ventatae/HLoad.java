/*
 * HLoad.java
 *
 * Created on March 24, 2008, 2:07 AM
 */

package ventatae;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;

/**
 *
 * @author  Hugo Alvarado
 */
public class HLoad extends javax.swing.JDialog {
    
    public HLoad(JDialog parent, boolean modal) {
        initComponents();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension f = this.getSize();
        int x = (d.width - f.width) / 2;
        int y = (d.height - f.height) / 2;
        this.setLocation(x, y); 
        this.setModal(modal); 
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); 
        this.setTitle("TMS Movil - Venta TAE");
       
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPnlContents = new javax.swing.JPanel();
        jPgrStatus = new javax.swing.JProgressBar();
        jLblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        setUndecorated(true);
        jPnlContents.setBackground(new java.awt.Color(255, 255, 255));
        jPnlContents.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPgrStatus.setFont(new java.awt.Font("Tahoma", 1, 11));
        jPgrStatus.setForeground(new java.awt.Color(0, 153, 204));
        jPgrStatus.setFocusable(false);
        jPgrStatus.setIndeterminate(true);
        jPgrStatus.setStringPainted(true);

        jLblTitle.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblTitle.setForeground(new java.awt.Color(102, 102, 102));
        jLblTitle.setText("Por Favor Espere...");

        org.jdesktop.layout.GroupLayout jPnlContentsLayout = new org.jdesktop.layout.GroupLayout(jPnlContents);
        jPnlContents.setLayout(jPnlContentsLayout);
        jPnlContentsLayout.setHorizontalGroup(
            jPnlContentsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlContentsLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPnlContentsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLblTitle)
                    .add(jPgrStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnlContentsLayout.setVerticalGroup(
            jPnlContentsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlContentsLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLblTitle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPgrStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlContents, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlContents, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLblTitle;
    public javax.swing.JProgressBar jPgrStatus;
    private javax.swing.JPanel jPnlContents;
    // End of variables declaration//GEN-END:variables
    
}
