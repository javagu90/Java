/*
 * jdlg_ValidarFuncion.java
 *
 * Created on 25 de abril de 2009, 09:51 AM
 */

package tmscodigobarras;

import javax.swing.JOptionPane;
import solicitud.TMSSesionCodBarRemote;
import tms_encriptacion.EncriptarMD5;


/**
 *
 * @author  vgonzalez
 */
public class jdlg_ValidarFuncion extends javax.swing.JDialog {
    private TMSSesionCodBarRemote cosa;
    private boolean valido = false;
    private String funcion;
    private String supernumero;
    
    /** Creates new form jdlg_ValidarFuncion */
    public jdlg_ValidarFuncion(javax.swing.JInternalFrame parent, boolean modal,TMSSesionCodBarRemote b, String funcion) {
        super(JOptionPane.getFrameForComponent(parent), true);
        initComponents();
        this.setTitle("Validaci�n    Funcion: "+funcion);
        cosa = b;
        this.funcion = funcion;
        this.setResizable(false);
        setLocationRelativeTo(null); 
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jtxt_usuario = new tms_TextFields.JNumberTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxt_contrasenia = new javax.swing.JPasswordField();
        jbtn_aceptar = new javax.swing.JButton();
        jbtn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Contrase\u00f1a:");

        jbtn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tmscodigobarras/resources/accept.png")));
        jbtn_aceptar.setMnemonic('A');
        jbtn_aceptar.setText("Aceptar");
        jbtn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_aceptarActionPerformed(evt);
            }
        });

        jbtn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tmscodigobarras/resources/undo.png")));
        jbtn_cancelar.setMnemonic('C');
        jbtn_cancelar.setText("Cancelar");
        jbtn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_cancelarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(50, 50, 50)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jbtn_aceptar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbtn_cancelar))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jtxt_contrasenia)
                            .add(jtxt_usuario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(26, 26, 26)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jtxt_usuario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jtxt_contrasenia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbtn_aceptar)
                    .add(jbtn_cancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_cancelarActionPerformed
        valido = false;
        this.dispose();
    }//GEN-LAST:event_jbtn_cancelarActionPerformed

    private void jbtn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_aceptarActionPerformed
            if(jtxt_usuario.getText().equals(""))
            {
                jtxt_contrasenia.selectAll();
                jtxt_contrasenia.requestFocus();
            }
            else
            {
                String psw = new String(jtxt_contrasenia.getPassword());
                if(psw.equals(""))
                {
                 jtxt_contrasenia.selectAll();
                 jtxt_contrasenia.requestFocus(); 
                }
                else
                    aceptar();
            }
        
        
    }//GEN-LAST:event_jbtn_aceptarActionPerformed
    
    private void aceptar(){
        EncriptarMD5 pwdEnc = new EncriptarMD5();    
        String numero = jtxt_usuario.getText();
        String clave = String.valueOf(jtxt_contrasenia.getPassword());
        if(numero.length() == 0){
            JOptionPane.showMessageDialog(this,"El numero de usuario no puede estar vacio.\nPor favor intenta de nuevo.","VENTOUR - Cotizaci�n",JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(clave.length() == 0){
                JOptionPane.showMessageDialog(this,"La Contrase�a de usuario no puede estar vacia.\nPor favor intenta de nuevo.","VENTOUR - Cotizaci�n",JOptionPane.INFORMATION_MESSAGE);
            }else{
                String respuesta = "no encontrado";
                try {
                    respuesta = cosa.esUsuarioSupervisor(numero, pwdEnc.encriptar(clave), funcion);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }                
                    if(respuesta.equals("no encontrado"))
                    {
                        valido = false;
                        JOptionPane.showMessageDialog(this,"�Numero de usuario o Contrase�a invalidos! " +
                                                          "\n      Favor de intentar nuevamente", "VENTOUR - Cotizaci�n", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                       if(respuesta.equals("invalido"))
                       {
                            valido = false;
                            JOptionPane.showMessageDialog(this,"�Tu perfil de usuario no te permite realizar esta funcion! \n " +
                                                               "     Favor de contactar al Administrador del Sistema", "VENTOUR - Cotizaci�n", JOptionPane.ERROR_MESSAGE);
                       }                
                       else
                          valido = true;
                    }                
                supernumero=numero;
                this.dispose();
            }
        }                
    }

    public boolean isValido() {
        return valido;
    }

    public String getSuperNumero() {
        return supernumero;
    }    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtn_aceptar;
    private javax.swing.JButton jbtn_cancelar;
    private javax.swing.JPasswordField jtxt_contrasenia;
    private tms_TextFields.JNumberTextField jtxt_usuario;
    // End of variables declaration//GEN-END:variables
    
}
