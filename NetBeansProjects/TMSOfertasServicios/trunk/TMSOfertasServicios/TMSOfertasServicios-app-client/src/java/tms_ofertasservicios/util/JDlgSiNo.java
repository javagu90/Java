/*
 * JDlgSiNo.java
 *
 * Created on 7 de septiembre de 2007, 01:47 PM
 */

package tms_ofertasservicios.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author  ocruz
 */
public class JDlgSiNo extends javax.swing.JDialog {
    
    /** Creates new form JDlgSiNo */
    public JDlgSiNo(String Titulo, String Mensaje) {
        super.setModal(true);
        initComponents();
        centrarJDialog();
        this.setTitle(Titulo);
        this.jLblMensaje.setText(Mensaje);
        this.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLblMensaje = new javax.swing.JLabel();
        jBtnSi = new javax.swing.JButton();
        jBtnNo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLblMensaje.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblMensaje.setText("Mensaje");

        jBtnSi.setFont(new java.awt.Font("Tahoma", 1, 11));
        jBtnSi.setText("Si");
        jBtnSi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnSiKeyPressed(evt);
            }
        });

        jBtnNo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jBtnNo.setText("No");
        jBtnNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnNoKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLblMensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(118, 118, 118)
                        .add(jBtnSi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jBtnNo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLblMensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(21, 21, 21)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jBtnNo)
                    .add(jBtnSi))
                .add(43, 43, 43))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnNoKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ENTER: Respuesta = false; this.dispose(); break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT: jBtnSi.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jBtnNoKeyPressed

    private void jBtnSiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnSiKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ENTER: Respuesta = true; this.dispose(); break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT: jBtnNo.requestFocusInWindow(); break;
        }
    }//GEN-LAST:event_jBtnSiKeyPressed

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
    
    public boolean getResultado(){ return Respuesta; }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnNo;
    private javax.swing.JButton jBtnSi;
    private javax.swing.JLabel jLblMensaje;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    private boolean Respuesta = false;
}
