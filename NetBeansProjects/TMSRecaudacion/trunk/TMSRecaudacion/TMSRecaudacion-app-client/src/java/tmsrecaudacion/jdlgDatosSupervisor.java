/*
 * jdlgDatosSupervisor.java
 *
 * Created on 28 de mayo de 2007, 10:26 AM
 */

package tmsrecaudacion;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author  imunoz
 */
public class jdlgDatosSupervisor extends javax.swing.JDialog {
    
    /** Creates new form jdlgDatosSupervisor */
    public jdlgDatosSupervisor( boolean modal, String titulo) {
        super(new java.awt.Frame(), modal);
        initComponents();
        this.setTitle(titulo);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Aceptar        |         <font color=FF0000>             ESC: </font> Salir  </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        jTxtNumeroUsr.requestFocus();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPnlValidar = new javax.swing.JPanel();
        jTxtNumeroUsr = new javax.swing.JTextField();
        jLblNumeroUsr = new javax.swing.JLabel();
        jLblPwdUsr = new javax.swing.JLabel();
        jFrmTxtPwdUsr = new javax.swing.JPasswordField();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TMS - Validar Supervisor");
        setName("dlgValidar");
        setResizable(false);
        jPnlValidar.setLayout(new java.awt.GridBagLayout());

        jTxtNumeroUsr.setPreferredSize(new java.awt.Dimension(100, 19));
        jTxtNumeroUsr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtNumeroUsrKeyPressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPnlValidar.add(jTxtNumeroUsr, gridBagConstraints);

        jLblNumeroUsr.setText("Numero: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPnlValidar.add(jLblNumeroUsr, gridBagConstraints);

        jLblPwdUsr.setText("Contrase\u00f1a: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPnlValidar.add(jLblPwdUsr, gridBagConstraints);

        jFrmTxtPwdUsr.setPreferredSize(new java.awt.Dimension(100, 19));
        jFrmTxtPwdUsr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFrmTxtPwdUsrKeyPressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPnlValidar.add(jFrmTxtPwdUsr, gridBagConstraints);

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPnlValidar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPnlValidar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFrmTxtPwdUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFrmTxtPwdUsrKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_LEFT || evt.getKeyCode() == evt.VK_DOWN)
            jTxtNumeroUsr.requestFocus();
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
            String psw = new String(jFrmTxtPwdUsr.getPassword());
            if(psw.equals(""))
            {
                jTxtNumeroUsr.selectAll();
                jTxtNumeroUsr.requestFocus();
            }
            else
                if(jTxtNumeroUsr.getText().equals(""))
                {
                  jTxtNumeroUsr.selectAll();
                  jTxtNumeroUsr.requestFocus();
                }
                else
                    aceptar();
        }
        if(evt.getKeyCode() == evt.VK_ESCAPE)
         {
            setNumeroUsuario("nada");
            setContrasenaUsuario("nada");
            this.dispose();
        }
    }//GEN-LAST:event_jFrmTxtPwdUsrKeyPressed

    private void jTxtNumeroUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNumeroUsrKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_LEFT || evt.getKeyCode() == evt.VK_DOWN)
            jFrmTxtPwdUsr.requestFocus();
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
            if(jTxtNumeroUsr.getText().equals(""))
            {
                jFrmTxtPwdUsr.selectAll();
                jFrmTxtPwdUsr.requestFocus();
            }
            else
            {
                String psw = new String(jFrmTxtPwdUsr.getPassword());
                if(psw.equals(""))
                {
                 jFrmTxtPwdUsr.selectAll();
                 jFrmTxtPwdUsr.requestFocus(); 
                }
                else
                    aceptar();
            }
        }
        if(evt.getKeyCode() == evt.VK_ESCAPE)
         {
            setNumeroUsuario("nada");
            setContrasenaUsuario("nada");
            this.dispose();
        }
    }//GEN-LAST:event_jTxtNumeroUsrKeyPressed

    
    private void aceptar(){
        String numero = jTxtNumeroUsr.getText();
        String clave = String.valueOf(jFrmTxtPwdUsr.getPassword());
        if(numero.length() == 0){
            JOptionPane.showMessageDialog(this,"El numero de usuario no puede estar vacio.\nPor favor intenta de nuevo.","Validar Clave",JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(clave.length() == 0){
                JOptionPane.showMessageDialog(this,"La Contrase�a de usuario no puede estar vacia.\nPor favor intenta de nuevo.","Validar Clave",JOptionPane.INFORMATION_MESSAGE);
            }else{
                setNumeroUsuario(numero);
                setContrasenaUsuario(clave);
                this.dispose();
            }
        }                
    }
        
    /**
     * M�todo que centra el cuadro de dialogo respecto a la pantalla.
     */
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

    public String getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(String numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField jFrmTxtPwdUsr;
    private javax.swing.JLabel jLblNumeroUsr;
    private javax.swing.JLabel jLblPwdUsr;
    private javax.swing.JPanel jPnlValidar;
    private javax.swing.JTextField jTxtNumeroUsr;
    private javax.swing.JLabel jlbl_barraEstado;
    // End of variables declaration//GEN-END:variables
    private String numeroUsuario;
    private String contrasenaUsuario;
}
