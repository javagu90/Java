/*
 * HLoad.java
 *
 * Created on March 24, 2008, 2:07 AM
 */

package recursos;


import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author  Hugo Alvarado
 */
public class HLoad extends javax.swing.JFrame {
    
    public HLoad() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension f = this.getSize();
        int x = (d.width - f.width) / 2;
        int y = (d.height - f.height) / 2;
        this.setLocation(x, y);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPnlContents = new javax.swing.JPanel();
        jPgrStatus = new javax.swing.JProgressBar();
        jLblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        jPnlContents.setBackground(new java.awt.Color(255, 255, 255));
        jPnlContents.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPgrStatus.setFont(new java.awt.Font("Tahoma", 1, 11));
        jPgrStatus.setForeground(new java.awt.Color(0, 153, 204));
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
                    .add(jPgrStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPnlContentsLayout.setVerticalGroup(
            jPnlContentsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlContentsLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLblTitle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPgrStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlContents, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
