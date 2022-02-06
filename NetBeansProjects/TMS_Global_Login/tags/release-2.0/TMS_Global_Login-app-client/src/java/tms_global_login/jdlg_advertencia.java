/*
 * jdlg_error.java
 *
 * Created on 26 de septiembre de 2007, 07:42 PM
 */

package tms_global_login;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JRootPane;
import javax.swing.JTextField;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_advertencia extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlg_error
     */
    public jdlg_advertencia(String texto1, String texto2, String titulo){
        this.setTitle(titulo);
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.setResizable(false);
        jlbl_mensaje1.setText(texto1);
        jlbl_mensaje2.setText(texto2);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Aceptar </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        int nletras = 0;
        if(texto1.length()>texto2.length())
         nletras = texto1.length();
        else 
         nletras = texto2.length();   
        this.setSize((nletras * 6) + 90,126);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.WARNING_DIALOG);
            jlbl_mensaje1.requestFocus();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();
        jlbl_mensaje1 = new javax.swing.JLabel();
        jlbl_mensaje1.setFocusTraversalKeysEnabled(false);
        jlbl_mensaje2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\NetBeansProyects\\TMSRecaudacion\\TMSRecaudacion-app-client\\src\\java\\tmsrecaudacion\\images\\advertencia.gif"));

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jlbl_mensaje1.setFont(new java.awt.Font("Arial", 1, 12));
        jlbl_mensaje1.setText("sdfsdsdfsdfsdfsdfsdf");
        jlbl_mensaje1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_mensaje1KeyPressed(evt);
            }
        });

        jlbl_mensaje2.setFont(new java.awt.Font("Arial", 1, 12));
        jlbl_mensaje2.setText("dasdasdasdasdasdasdasd");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(22, 22, 22)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jlbl_mensaje1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 702, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_mensaje2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 702, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(jlbl_mensaje1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jlbl_mensaje2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 56, Short.MAX_VALUE)
                .add(jlbl_barraEstado))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlbl_mensaje1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbl_mensaje1KeyPressed
      if(evt.getKeyCode() == evt.VK_ENTER)
          this.dispose();
    }//GEN-LAST:event_jlbl_mensaje1KeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_mensaje1;
    private javax.swing.JLabel jlbl_mensaje2;
    // End of variables declaration//GEN-END:variables
    
}
