/*
 * jdlgActualizarPwd.java
 *
 * Created on 29 de julio de 2007, 08:31 PM
 */

package tms_global_login;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import tms_encriptacion.EncriptarMD5;

/**
 *
 * @author  ocruz
 */
public class jdlgActualizarPwd extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlgActualizarPwd
     */
    public jdlgActualizarPwd(){//java.awt.Frame parent, boolean modal) {
        //super(parent, modal);
        this.setModal(true);
        this.setResizable(false);
        initComponents();
        centrarDialogo();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPnlFormulario = new javax.swing.JPanel();
        jBtnActualizar = new javax.swing.JButton();
        jPwdConfirPwd = new javax.swing.JPasswordField();
        jPwdNuevoPwd = new javax.swing.JPasswordField();
        jLblConfirmarPwd = new javax.swing.JLabel();
        jLblNuevoPwd = new javax.swing.JLabel();
        jBtnCancelar = new javax.swing.JButton();
        jLblMensaje = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jBtnActualizar.setMnemonic('A');
        jBtnActualizar.setText("Actualizar");
        jBtnActualizar.setToolTipText("Actualizar Contrase\u00f1a");
        jBtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarActionPerformed(evt);
            }
        });
        jBtnActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnActualizarKeyPressed(evt);
            }
        });

        jPwdConfirPwd.setToolTipText("Confirmar Contrase\u00f1a");
        jPwdConfirPwd.setPreferredSize(new java.awt.Dimension(111, 20));
        jPwdConfirPwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPwdConfirPwdKeyPressed(evt);
            }
        });

        jPwdNuevoPwd.setToolTipText("Nueva Contrase\u00f1a");
        jPwdNuevoPwd.setPreferredSize(new java.awt.Dimension(111, 20));
        jPwdNuevoPwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPwdNuevoPwdKeyPressed(evt);
            }
        });

        jLblConfirmarPwd.setText("Confirmar Contrase\u00f1a:");

        jLblNuevoPwd.setText("Nueva Contrase\u00f1a:");

        jBtnCancelar.setMnemonic('C');
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.setToolTipText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });
        jBtnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnCancelarKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPnlFormularioLayout = new org.jdesktop.layout.GroupLayout(jPnlFormulario);
        jPnlFormulario.setLayout(jPnlFormularioLayout);
        jPnlFormularioLayout.setHorizontalGroup(
            jPnlFormularioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPnlFormularioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLblConfirmarPwd)
                    .add(jLblNuevoPwd))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPnlFormularioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPwdConfirPwd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlFormularioLayout.createSequentialGroup()
                        .add(jPwdNuevoPwd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(jPnlFormularioLayout.createSequentialGroup()
                        .add(jBtnActualizar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                        .add(jBtnCancelar)))
                .addContainerGap())
        );
        jPnlFormularioLayout.setVerticalGroup(
            jPnlFormularioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlFormularioLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPnlFormularioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLblConfirmarPwd)
                    .add(jPwdNuevoPwd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPnlFormularioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLblNuevoPwd)
                    .add(jPwdConfirPwd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPnlFormularioLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jBtnActualizar)
                    .add(jBtnCancelar)))
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPnlFormulario, gridBagConstraints);

        jLblMensaje.setText("La contrase\u00f1a ha vencido. Por favor ingresa una nueva.");
        getContentPane().add(jLblMensaje, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnCancelarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) jBtnCancelar.doClick();
    }//GEN-LAST:event_jBtnCancelarKeyPressed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jPwdNuevoPwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPwdNuevoPwdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) jPwdConfirPwd.requestFocusInWindow();
    }//GEN-LAST:event_jPwdNuevoPwdKeyPressed

    private void jPwdConfirPwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPwdConfirPwdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) jBtnActualizar.requestFocusInWindow();
    }//GEN-LAST:event_jPwdConfirPwdKeyPressed

    private void jBtnActualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnActualizarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) jBtnActualizar.doClick();
    }//GEN-LAST:event_jBtnActualizarKeyPressed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarActionPerformed
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        String nuevoPwd = new String(jPwdNuevoPwd.getPassword());
        String confirmarPwd = new String(jPwdConfirPwd.getPassword());
        String pwdActualEnc = ventaLoginCnx.getUsuarioContrasena();
        String nuevaContraEnc = null;
        
        try {
            nuevaContraEnc = pwdEnc.encriptar(nuevoPwd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if(!pwdActualEnc.equals(nuevaContraEnc) && nuevoPwd.equals(confirmarPwd) && nuevoPwd.length() > 5){
            if(actualizarContrasena(nuevaContraEnc)){
                JOptionPane.showMessageDialog(this,"La contrase�a se ha actualizado correctamente.\nPor favor vuelve a ingresar","Contrase�a Actualizada",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else
                JOptionPane.showMessageDialog(this,"La contrase�a no pudo ser actualizada.\nPor favor intenta de nuevo","Contrase�a No Actualizada",JOptionPane.ERROR_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(this,"La contrase�a no es v�lida.\nPor favor intenta de nuevo","Datos Incorrectos",JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            jPwdNuevoPwd.requestFocusInWindow();
        }
    }//GEN-LAST:event_jBtnActualizarActionPerformed
    
    public void centrarDialogo(){
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

    private void limpiarFormulario(){
        jPwdNuevoPwd.setText("");
        jPwdConfirPwd.setText("");
    }
    
    public TMSLoginGlobalManagedBean getVentaLoginCnx() {
        return ventaLoginCnx;
    }

    public void setVentaLoginCnx(TMSLoginGlobalManagedBean ventaLoginCnx) {
        this.ventaLoginCnx = ventaLoginCnx;
    }

    private boolean actualizarContrasena(String nuevoPwd) {
        return ventaLoginCnx.actualizarContrasena(nuevoPwd);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jdlgActualizarPwd();//new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JLabel jLblConfirmarPwd;
    private javax.swing.JLabel jLblMensaje;
    private javax.swing.JLabel jLblNuevoPwd;
    private javax.swing.JPanel jPnlFormulario;
    private javax.swing.JPasswordField jPwdConfirPwd;
    private javax.swing.JPasswordField jPwdNuevoPwd;
    // End of variables declaration//GEN-END:variables
    private TMSLoginGlobalManagedBean ventaLoginCnx;    
}
