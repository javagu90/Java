/*
 * jdlg_bloqueo.java
 *
 * Created on 23 de octubre de 2007, 11:04 AM
 */

package tms_global_login;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JRootPane;
import tms_encriptacion.EncriptarMD5;
import xertms.solicitud.TmsSesionGeneralFacadeRemote;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_bloqueo extends javax.swing.JDialog {
    
    /** Creates new form jdlg_bloqueo */
    public jdlg_bloqueo(String titulo,TmsSesionGeneralFacadeRemote psesionGeneralFacateRemote,String pUsuarioNumero, String pUsuarioContrase�a) {
        this.setTitle(titulo);
        initComponents();
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.setResizable(false);
        this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
        this.setDefaultLookAndFeelDecorated(true);
        this.sesionGeneralFacateRemote = psesionGeneralFacateRemote;
        this.usuarioNumero = pUsuarioNumero;
        this.usuarioContrase�a = pUsuarioContrase�a;
        jtxt_contrase�a.setFocusTraversalKeysEnabled(false);
        jtxt_usuario.setFocusTraversalKeysEnabled(false);
        jtxt_usuario.requestFocus();
    }
    
    
    private void aceptar(){
        String contrase�a = "";
        try {
            contrase�a = pwdEnc.encriptar(new String(jtxt_contrase�a.getPassword()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(jtxt_usuario.getText().equals(usuarioNumero)){
            if(contrase�a.equals(usuarioContrase�a))
             this.dispose();
            else
            {
                new jdlg_error("�N�mero de usuario o contrase�a no v�lido! ","Favor de intentarlo nuevamente","Datos invalidos").setVisible(true);
                jtxt_contrase�a.setText("");
                jtxt_contrase�a.requestFocus();
            }  
        }
            
        else
        {
            String respuesta = sesionGeneralFacateRemote.esUsuarioSupervisor(jtxt_usuario.getText(),contrase�a,"1035");
            System.out.println("los datos para buscar al usuario son; Usuario: "+jtxt_usuario.getText()+"   Contrase�a: "+contrase�a);
            if(respuesta.equals("no encontrado"))
            {
                new jdlg_error("�N�mero de usuario o contrase�a no v�lido! ","Favor de intentarlo nuevamente","Datos invalidos").setVisible(true);
                jtxt_contrase�a.setText("");
                jtxt_usuario.setText("");
                jtxt_usuario.requestFocus();
            }
            else{
            if(respuesta.equals("invalido"))
            {
                new jdlg_advertencia("�Este usuario no tiene la funci�n asignada!","Favor de contactar al administrador del sistema","Funcion no asignada").setVisible(true);
                jtxt_contrase�a.setText("");
                jtxt_usuario.setText("");
                jtxt_usuario.requestFocus();
            }
            else
            {
                if(contrase�a.equals(respuesta))
                    this.dispose();
                else
                {
                    new jdlg_error("�N�mero de usuario o contrase�a no v�lido! ","Favor de intentarlo nuevamente","Datos invalidos").setVisible(true);
                    jtxt_contrase�a.setText("");
                    jtxt_usuario.setText("");
                    jtxt_usuario.requestFocus();
                }
            }
            }
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxt_usuario = new tms_TextFields.JNumberTextField();
        jLabel3 = new javax.swing.JLabel();
        jtxt_contrase�a = new javax.swing.JPasswordField();
        jlbl_barraEstado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Introduce tu numero de usuario y contrase\u00f1a ");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel2.setText("Numero:");

        jtxt_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_usuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_usuarioKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setText("Contrase\u00f1a:");

        jtxt_contrase�a.setFont(new java.awt.Font("Tahoma", 0, 10));
        jtxt_contrase�a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_contrase�aKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_contrase�aKeyReleased(evt);
            }
        });

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Aceptar </html>");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("para desbloquear el equipo");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(59, 59, 59)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel2)
                    .add(jLabel3))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jtxt_contrase�a)
                    .add(jtxt_usuario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jtxt_usuario, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jtxt_contrase�a, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 29, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_usuarioKeyReleased
      if(evt.getKeyCode() == evt.VK_TAB)
        jtxt_usuario.requestFocus();
    }//GEN-LAST:event_jtxt_usuarioKeyReleased

    private void jtxt_contrase�aKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_contrase�aKeyReleased
       if(evt.getKeyCode() == evt.VK_TAB)
          jtxt_contrase�a.requestFocus();
    }//GEN-LAST:event_jtxt_contrase�aKeyReleased

    private void jtxt_contrase�aKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_contrase�aKeyPressed
       if(evt.getKeyCode() == evt.VK_UP || evt.getKeyCode() == evt.VK_DOWN)
       {
           jtxt_usuario.requestFocus();
           jtxt_usuario.selectAll();
       }
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
           String contra = new String(jtxt_contrase�a.getPassword());
           if(contra.equals(""))
            {
                jtxt_usuario.selectAll();
                jtxt_usuario.requestFocus();
            }
            else
                if(jtxt_usuario.getText().equals(""))
                {
                  jtxt_usuario.selectAll();
                  jtxt_usuario.requestFocus();
                }
                else
                    aceptar();
        }

       if(evt.getKeyCode() == evt.VK_TAB)
          jtxt_contrase�a.requestFocus();
       
    }//GEN-LAST:event_jtxt_contrase�aKeyPressed

    private void jtxt_usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_usuarioKeyPressed
      if(evt.getKeyCode() == evt.VK_UP || evt.getKeyCode() == evt.VK_DOWN)
      {
          jtxt_contrase�a.requestFocus();
          jtxt_contrase�a.selectAll();
      }
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
          String contra = new String(jtxt_contrase�a.getPassword());
          System.out.println("contrase�a: "+contra);
          if(jtxt_usuario.getText().equals(""))
            {
                jtxt_contrase�a.selectAll();
                jtxt_contrase�a.requestFocus();
            }
            else
            {
                if(contra.equals(""))
                {
                 jtxt_contrase�a.selectAll();
                 jtxt_contrase�a.requestFocus(); 
                }
                else
                    aceptar();
            }
        }
      
      if(evt.getKeyCode() == evt.VK_TAB)
        jtxt_usuario.requestFocus();

    }//GEN-LAST:event_jtxt_usuarioKeyPressed
    
    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jdlg_bloqueo("Aplicacion Bloqueada");
            }
        });
    }
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JPasswordField jtxt_contrase�a;
    private tms_TextFields.JNumberTextField jtxt_usuario;
    // End of variables declaration//GEN-END:variables
    private TmsSesionGeneralFacadeRemote sesionGeneralFacateRemote;
    private EncriptarMD5 pwdEnc = new EncriptarMD5();
    private String usuarioNumero;
    private String usuarioContrase�a;
}
