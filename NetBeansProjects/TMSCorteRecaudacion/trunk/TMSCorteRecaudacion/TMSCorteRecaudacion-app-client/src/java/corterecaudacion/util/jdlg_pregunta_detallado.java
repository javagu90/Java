/*
 * jdlg_error.java
 *
 * Created on 26 de septiembre de 2007, 07:42 PM
 */

package corterecaudacion.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JRootPane;
import javax.swing.JTextField;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_pregunta_detallado extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlg_error
     */
    public jdlg_pregunta_detallado(){
        this.setTitle("Reimpresion de Corte");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.setResizable(false);
        group.add(jrbtnCNormal);
        group.add(jrbtnCDetallado);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Aceptar </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            jrbtnCNormal.setSelected(true);
            jrbtnCNormal.requestFocus();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        group = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();
        jrbtnCNormal = new javax.swing.JRadioButton();
        jlbl_mensaje1 = new javax.swing.JLabel();
        jrbtnCDetallado = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\NetBeansProyects\\TMSRecaudacion\\TMSRecaudacion-app-client\\src\\java\\tmsrecaudacion\\images\\pregunta.gif"));

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jrbtnCNormal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jrbtnCNormal.setText("Corte Normal");
        jrbtnCNormal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrbtnCNormal.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrbtnCNormal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jrbtnCNormalKeyPressed(evt);
            }
        });

        jlbl_mensaje1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_mensaje1.setText("Selccione el tipo de Reporte que dese reimprimir");

        jrbtnCDetallado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jrbtnCDetallado.setText("Corte Detallado");
        jrbtnCDetallado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrbtnCDetallado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jrbtnCDetallado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jrbtnCDetalladoKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                .add(jlbl_mensaje1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 318, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .add(jrbtnCNormal)
                .add(31, 31, 31)
                .add(jrbtnCDetallado)
                .add(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_mensaje1))
                .add(16, 16, 16)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jrbtnCNormal)
                    .add(jrbtnCDetallado))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                .add(jlbl_barraEstado))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrbtnCDetalladoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrbtnCDetalladoKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER)
         {
            if(jrbtnCNormal.isSelected())
                this.tipo = "normal";
            if(jrbtnCDetallado.isSelected())
                this.tipo = "detallado";
            this.dispose();
         }
         
         if(evt.getKeyCode() == evt.VK_ESCAPE)
         {
            this.tipo = "nada";
            this.dispose();
         }
         
         if(evt.getKeyCode() == evt.VK_LEFT || evt.getKeyCode() == evt.VK_RIGHT)
         {
             jrbtnCDetallado.setSelected(false);
             jrbtnCNormal.setSelected(true);
             jrbtnCNormal.requestFocus();
             
         }
    }//GEN-LAST:event_jrbtnCDetalladoKeyPressed

    private void jrbtnCNormalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrbtnCNormalKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER)
         {
            if(jrbtnCNormal.isSelected())
                this.tipo = "normal";
            if(jrbtnCDetallado.isSelected())
                this.tipo = "detallado";
            this.dispose();
         }
         
         if(evt.getKeyCode() == evt.VK_ESCAPE)
         {
            this.tipo = "nada";
            this.dispose();
         }
         
         if(evt.getKeyCode() == evt.VK_LEFT || evt.getKeyCode() == evt.VK_RIGHT)
         {
             jrbtnCNormal.setSelected(false);
             jrbtnCDetallado.setSelected(true);
             jrbtnCDetallado.requestFocus();
         }
             
    }//GEN-LAST:event_jrbtnCNormalKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup group;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_mensaje1;
    private javax.swing.JRadioButton jrbtnCDetallado;
    private javax.swing.JRadioButton jrbtnCNormal;
    // End of variables declaration//GEN-END:variables
    private String tipo = "";

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
