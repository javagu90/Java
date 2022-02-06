/*
 * JDlgSiNo.java
 *
 * Created on 7 de septiembre de 2007, 01:47 PM
 */

package tmsacumularestrellas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;


/**
 *
 * @author  ocruz
 */
public class JDlgAceptar extends javax.swing.JDialog {
    
    /** Creates new form JDlgSiNo */
    public JDlgAceptar() {
        initComponents();
        super.setModal(true);
        interfazColor();
        centrarJDialog();
    }
    
    public void mostrarDialogo(String Titulo, String Mensaje, Color color) {
        jLblMensaje.setForeground(ColoresInterfaz.cFondoVentana);
        super.setTitle(Titulo);
        this.jLblMensaje.setText(Mensaje);
        super.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLblMensaje = new javax.swing.JLabel();
        jBtnAceptar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModal(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLblMensaje.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblMensaje.setText("Mensaje");
        jLblMensaje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jLblMensajeFocusGained(evt);
            }
        });

        jBtnAceptar.setFont(new java.awt.Font("Tahoma", 1, 11));
        jBtnAceptar.setMnemonic('A');
        jBtnAceptar.setText("Aceptar");
        jBtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAceptarActionPerformed(evt);
            }
        });
        jBtnAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnAceptarKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(184, 184, 184)
                .add(jBtnAceptar)
                .addContainerGap(189, Short.MAX_VALUE))
            .add(jLblMensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLblMensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jBtnAceptar)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnAceptarKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) dispose();
    }//GEN-LAST:event_jBtnAceptarKeyPressed

    private void jBtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAceptarActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtnAceptarActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
// TODO add your handling code here:
        jBtnAceptar.requestFocusInWindow();
    }//GEN-LAST:event_formFocusGained

    private void jLblMensajeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLblMensajeFocusGained
// TODO add your handling code here:
        jBtnAceptar.requestFocusInWindow();
    }//GEN-LAST:event_jLblMensajeFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
// TODO add your handling code here:
        jBtnAceptar.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        jBtnAceptar.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown
/**/   
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

    private void interfazColor(){        
        setFont(ColoresInterfaz.fuente1);
        jLblMensaje.setFont(ColoresInterfaz.fuente0);
        jBtnAceptar.setFont(ColoresInterfaz.fuente1);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblMensaje;
    // End of variables declaration//GEN-END:variables
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
}
