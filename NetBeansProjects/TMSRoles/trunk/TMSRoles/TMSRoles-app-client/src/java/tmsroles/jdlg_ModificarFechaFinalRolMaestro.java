/*
 * jdlg_ModificarFechaFinalRolMaestro.java
 *
 * Created on 20 de noviembre de 2007, 12:26 PM
 */

package tmsroles;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import tmsroles.entidad.TmsRolesMaestroTbl;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_ModificarFechaFinalRolMaestro extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlg_ModificarFechaFinalRolMaestro
     */
    public jdlg_ModificarFechaFinalRolMaestro(long pfi, long pff, TmsRolesMaestroTbl pRM, TmsRolesManagedBean pbusquedas, long pusuarioId){
        this.setTitle("Fechas para la Generacion de Corridas");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.fechaIni = new Timestamp(pfi);
        this.fechaFin = new Timestamp(pff);
        this.rolMaestro = pRM;
        this.busquedas = pbusquedas;
        this.usuarioId = pusuarioId;
        this.setResizable(false);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        
        jtxt_fechaInicial.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechaicorridas_inicialKeyPressed(evt);
            }
        });
        jtxt_fechaFinal.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechafcorridas_inicialKeyPressed(evt);
            }
        });
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESCAPE: </font> Cancelar | <font color=FF0000>ENTER: </font> Aceptar </html>");
        jtxt_fechaInicial.getDateEditor().setTexto(formatf.format(fechaIni.getTime()));
        jtxt_fechaFinal.getDateEditor().setTexto(formatf.format(fechaFin.getTime()));
        jtxt_fechaFinal.dateEditor.setFoco();
    }
    
    private void jtxt_fechaicorridas_inicialKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
    
        }
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }
    
    private void jtxt_fechafcorridas_inicialKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
         if(jtxt_fechaFinal.getDate().getTime()<jtxt_fechaInicial.getDate().getTime())
             new jdlg_advertencia("¡La fecha final debe ser mayor o igual a la fecha inicial!","","Error de rango de fechas").setVisible(true);
         else
         {
            jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion", "¿Seguro que sea modificar la vigencia del Rol Maestro?");
              psn.setVisible(true);
              if(respuestaSN)
              {
                  Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
                  fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
                  rolMaestro.setFechaFinal(jtxt_fechaFinal.getDate());
                  rolMaestro.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                  rolMaestro.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                  busquedas.rolesMaestroTblFacadeRemote.edit(rolMaestro);     
                  setAceptar(true);
                  new jdlg_informacion("¡La fecha final del Rol Maestro se actualizó correctamente!","","Rol Maestro actualizado").setVisible(true);
                  this.dispose();
              }
         }
        }
        if(evt.getKeyCode() == evt.VK_ESCAPE)
        {
            setAceptar(false);
            this.dispose();
        }
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_fechaInicial = new tms_calendario.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jtxt_fechaFinal = new tms_calendario.JDateChooser();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fechas de vigencia del Rol Maestro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Fecha desde:");

        jtxt_fechaInicial.setEnabled(false);
        jtxt_fechaInicial.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("hasta:");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(55, 55, 55)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_fechaInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(14, 14, 14)
                .add(jLabel2)
                .add(20, 20, 20)
                .add(jtxt_fechaFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(34, 34, 34)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jtxt_fechaFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel2)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel1)
                            .add(jtxt_fechaInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel3");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 14, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private tms_calendario.JDateChooser jtxt_fechaFinal;
    private tms_calendario.JDateChooser jtxt_fechaInicial;
    // End of variables declaration//GEN-END:variables

    private Timestamp fechaFin;
    private Timestamp fechaIni;
    private Timestamp fecha_servidor;
    private SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
    private TmsRolesMaestroTbl rolMaestro;
     private TmsRolesManagedBean busquedas;
    private boolean respuestaSN = false;
    private boolean aceptar = false;
    private long usuarioId;

    class jdlg_pregunta_SN extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlg_pregunta_SN
     */
    public jdlg_pregunta_SN(String titulo, String pregunta){
        this.setTitle(titulo);
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
        this.setResizable(false);
        jlbl_mensaje.setText(pregunta);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si                 <font color=FF0000>             ESC: </font> No  </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        int nletras = pregunta.length();
        this.setSize((nletras * 6) + 80,150);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
            //this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            jlbl_mensaje.requestFocus();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();
        jlbl_mensaje = new javax.swing.JLabel();
        jlbl_mensaje.setFocusTraversalKeysEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\NetBeansProyects\\TMSRecaudacion\\TMSRecaudacion-app-client\\src\\java\\tmsrecaudacion\\images\\pregunta.gif"));

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jlbl_mensaje.setFont(new java.awt.Font("Arial", 1, 12));
        jlbl_mensaje.setText("sdfsdsdfsdfsdfsdfsdf");
        jlbl_mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_mensajeKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(14, 14, 14)
                .add(jlbl_mensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .add(424, 424, 424))
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_mensaje))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 59, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>                           

    private void jlbl_mensajeKeyReleased(java.awt.event.KeyEvent evt) {                                        
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          respuestaSN = false;
          this.dispose();
      }
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
          respuestaSN = true;
          this.dispose();
      }
      
    }                                       
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_mensaje;
    
    // End of variables declaration                   
    
}          

    public boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }
}
