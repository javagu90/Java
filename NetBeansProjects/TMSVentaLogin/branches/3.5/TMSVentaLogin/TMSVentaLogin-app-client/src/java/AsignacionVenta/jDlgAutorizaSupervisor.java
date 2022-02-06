/*
 * jDlgDatosSupervisor.java
 *
 * Created on 28 de mayo de 2007, 10:26 AM
 */

package AsignacionVenta;

import DialogosX.JDlgAceptar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import tms_venta.JClsColoresInterfaz;
import tms_venta.SesionVenta;


/**
 *
 * @author  imunoz
 */
public class jDlgAutorizaSupervisor extends javax.swing.JDialog {
    private SesionVenta sesionVenta=null;
    private String numeroFuncion;
    private String descripcionFuncion;
    /**
     * Creates new form jDlgDatosSupervisor
     */
    public jDlgAutorizaSupervisor(SesionVenta pSesionVenta, String pNumeroFuncion, String pDescripcionFuncion) {
        this.sesionVenta = pSesionVenta;
        this.numeroFuncion = pNumeroFuncion;
        this.descripcionFuncion = pDescripcionFuncion;
        this.setModal(true);
        initComponents();
        interfazColor();
        this.setTitle("Autorizacion del Supervisor para "+pDescripcionFuncion);
        jLabel1.setText("<html><font color="+ColoresInterfaz.cHTML3+">ENTER</font> Ingresar datos | <font color="+ColoresInterfaz.cHTML3+">ESC</font> Cancelar</html>");
        this.jLblFuncion.setText("<html>Funcion: <font color="+ColoresInterfaz.cHTML3+">"+numeroFuncion+"</font></html>");
        centrarDialogo();
        jFrmTxtPwdUsr.setFocusTraversalKeysEnabled(false);
        jTxtNumeroUsr.setFocusTraversalKeysEnabled(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPnlValidar = new javax.swing.JPanel();
        jTxtNumeroUsr = new javax.swing.JTextField();
        jLblNumeroUsr = new javax.swing.JLabel();
        jLblPwdUsr = new javax.swing.JLabel();
        jFrmTxtPwdUsr = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLblFuncion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("");
        setName("dlgValidar");
        setResizable(false);
        jPnlValidar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTxtNumeroUsr.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtNumeroUsr.setPreferredSize(new java.awt.Dimension(100, 19));
        jTxtNumeroUsr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtNumeroUsrKeyPressed(evt);
            }
        });

        jLblNumeroUsr.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblNumeroUsr.setText("Numero: ");

        jLblPwdUsr.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLblPwdUsr.setText("Contrase\u00f1a: ");

        jFrmTxtPwdUsr.setFont(new java.awt.Font("Courier", 1, 13));
        jFrmTxtPwdUsr.setPreferredSize(new java.awt.Dimension(100, 19));
        jFrmTxtPwdUsr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFrmTxtPwdUsrKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html><font color=\"+ColoresInterfaz.cHTML1+\">ENTER</font> Ingresar datos | <font color=\"+ColoresInterfaz.cHTML1+\">ESC</font> Cancelar</html>");

        jLblFuncion.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLblFuncion.setText("<html>Funcion: <font color=\"+ColoresInterfaz.cHTML1+\">5003</font></html>");

        org.jdesktop.layout.GroupLayout jPnlValidarLayout = new org.jdesktop.layout.GroupLayout(jPnlValidar);
        jPnlValidar.setLayout(jPnlValidarLayout);
        jPnlValidarLayout.setHorizontalGroup(
            jPnlValidarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlValidarLayout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .add(jPnlValidarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLblPwdUsr)
                    .add(jPnlValidarLayout.createSequentialGroup()
                        .add(jLblNumeroUsr)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jPnlValidarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jFrmTxtPwdUsr, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTxtNumeroUsr, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(49, 49, 49)
                .add(jLblFuncion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 99, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        jPnlValidarLayout.setVerticalGroup(
            jPnlValidarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlValidarLayout.createSequentialGroup()
                .add(jPnlValidarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLblFuncion)
                    .add(jPnlValidarLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPnlValidarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTxtNumeroUsr, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLblNumeroUsr))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPnlValidarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPnlValidarLayout.createSequentialGroup()
                                .add(2, 2, 2)
                                .add(jLblPwdUsr))
                            .add(jFrmTxtPwdUsr, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlValidar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlValidar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFrmTxtPwdUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFrmTxtPwdUsrKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ESCAPE) {
            respuesta=false;
            this.dispose();
            return;
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTxtNumeroUsr.requestFocusInWindow();
            return;
        }
        if(!jFrmTxtPwdUsr.getText().equals(""))
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){ validarSupervisor(); evt.consume(); }
    }//GEN-LAST:event_jFrmTxtPwdUsrKeyPressed

    private void jTxtNumeroUsrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNumeroUsrKeyPressed
// TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ESCAPE) {
            respuesta=false;
            this.dispose();
            return;
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN){
            jFrmTxtPwdUsr.requestFocusInWindow();
            return;
        }
        if(!jTxtNumeroUsr.getText().equals(""))
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) jFrmTxtPwdUsr.requestFocusInWindow();
    }//GEN-LAST:event_jTxtNumeroUsrKeyPressed

    private void validarSupervisor(){
        String numero = jTxtNumeroUsr.getText();
        String clave = String.valueOf(jFrmTxtPwdUsr.getPassword());
        if(numero.length() == 0){
            DialogoAceptar.mostrarDialogo("Validar Clave", "<html>El numero de usuario no puede estar vacio.<br>Por favor intenta de nuevo.</html>", Color.RED);
        }else{
            if(clave.length() == 0){
                DialogoAceptar.mostrarDialogo("Validar Clave", "<html>La Contrase�a de usuario no puede estar vacia.\nPor favor intenta de nuevo.</html>",Color.RED);
            }else{
                if(!sesionVenta.validarClaveSupervisorConFuncion(numeroFuncion, numero, clave)){
                    respuesta = false;
                    DialogoAceptar.mostrarDialogo("�Acceso denegado!", "<html>La clave proporcionada no es correcta,<br>o no tiene permiso para realizar esta transaccion.</html>", Color.RED);
                    jTxtNumeroUsr.setText("");
                    jFrmTxtPwdUsr.setText("");
                    jTxtNumeroUsr.requestFocusInWindow();
                    return;
                }
                respuesta = true;
                this.dispose();
            }
        }
    }    
    /**
     * M�todo que centra el cuadro de dialogo respecto a la pantalla.
     */
    private void centrarDialogo(){
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

    public boolean getRespuesta(){ return respuesta; }
    
    private void interfazColor(){
        jPnlValidar.setBackground(ColoresInterfaz.cFondoDialogo);
        /*jLabel1.setOpaque(true);
        jLabel1.setBackground(ColoresInterfaz.cFondoPieEncabezado2);*/
        jLabel1.setForeground(ColoresInterfaz.cTextoAyuda1);

        setFont(ColoresInterfaz.fuente1);
        jLblFuncion.setFont(ColoresInterfaz.fuente1);
        jLblNumeroUsr.setFont(ColoresInterfaz.fuente1);
        jTxtNumeroUsr.setFont(ColoresInterfaz.fuente1);
        jLblPwdUsr.setFont(ColoresInterfaz.fuente1);
        jFrmTxtPwdUsr.setFont(ColoresInterfaz.fuente1);
        jLabel1.setFont(ColoresInterfaz.fuente1);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField jFrmTxtPwdUsr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblFuncion;
    private javax.swing.JLabel jLblNumeroUsr;
    private javax.swing.JLabel jLblPwdUsr;
    private javax.swing.JPanel jPnlValidar;
    private javax.swing.JTextField jTxtNumeroUsr;
    // End of variables declaration//GEN-END:variables
    private boolean respuesta;
    private JDlgAceptar DialogoAceptar = new JDlgAceptar();
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
}
