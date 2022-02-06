/*
 * jdlg_nombreRolMaestro.java
 *
 * Created on 19 de noviembre de 2007, 12:22 PM
 */

package tmsroles;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import tmsroles.entidad.TmsFlotillasTbl;
import tmsroles.entidad.TmsRolesMaestroTbl;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_nombreRolMaestro extends javax.swing.JDialog {
    
 
    public jdlg_nombreRolMaestro(TmsRolesManagedBean pbusquedas,long idSer,  String poferta, Vector pexcluir, long pusuarioId, String pnombreOferta, Vector pvrutes){
       this.setTitle("Horarios de la Oferta para generar Corridas");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.setResizable(false);
        this.busquedas = pbusquedas;
        this.idrservicio = idSer;
        this.vrutes = pvrutes;
        System.out.println("Las Rutas en Nombre Rol Maestro son: "+vrutes);
        this.excluir = pexcluir;
        this.usuarioId = pusuarioId;
        this.oferta = poferta;
        this.nombreOferta = pnombreOferta;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );    
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESC: </font> Cancelar | <font color=FF0000>ENTER: </font> Aceptar </html>");
        jtxt_fecha_final.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt__fecha_finalKeyPressed(evt);
            }
        });
        jtxt_fecha_inicial.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt__fecha_inicialKeyPressed(evt);
            }
        });
        listadoFlotillas = (List<TmsFlotillasTbl>)busquedas.variosFacadeRemote.findAllFlotillas();
        jcmbx_flotillas.removeAllItems();
        for(int i=0; i<listadoFlotillas.size(); i++)
        {
            TmsFlotillasTbl flotilla = (TmsFlotillasTbl)listadoFlotillas.get(i);
            jcmbx_flotillas.addItem(flotilla.getNombreFlotilla());
        }
        jtxt_nombre.requestFocus();
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxt_fecha_inicial = new tms_calendario.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jtxt_fecha_final = new tms_calendario.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jcmbx_flotillas = new javax.swing.JComboBox();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Rol Maestro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Nombre:");

        jtxt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_nombreKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Vigencia");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Fecha desde: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("hasta");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Flotilla:");

        jcmbx_flotillas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbx_flotillas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbx_flotillasKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jcmbx_flotillas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 306, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jLabel2)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel3)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jtxt_fecha_inicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(19, 19, 19)
                                        .add(jLabel4)))
                                .add(17, 17, 17)
                                .add(jtxt_fecha_final, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 103, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 83, Short.MAX_VALUE))
                    .add(jLabel5))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(jLabel2)
                .add(19, 19, 19)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jtxt_fecha_inicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jtxt_fecha_final, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 27, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jcmbx_flotillas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(25, 25, 25))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbl_barraEstado.setText("jLabel5");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbx_flotillasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbx_flotillasKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            registrar();
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();    
    }//GEN-LAST:event_jcmbx_flotillasKeyPressed

    private void jtxt_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_nombreKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            registrar();
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }//GEN-LAST:event_jtxt_nombreKeyPressed
    
    private void jtxt__fecha_inicialKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() == evt.VK_ENTER)
            registrar();
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }
    
    private void jtxt__fecha_finalKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() == evt.VK_ENTER)
            registrar();
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }
    
    private void registrar(){
     if(jtxt_nombre.equals("") || jtxt_fecha_final.dateEditor.getTexto().equals("") || jtxt_fecha_inicial.dateEditor.getTexto().equals(""))
        new jdlg_error("¡Desbes introducir todos los datos!","","Error de datos").setVisible(true);
     else
      {
         Vector nombres = busquedas.variosFacadeRemote.buscaRolMaestroExistente(jtxt_nombre.getText());         
         if(nombres.size()>0)
         {
             new jdlg_error("¡Ya existe un Rol Maestro con ese nombre! ","Intente con otro nombre por favor","Nombre existente").setVisible(true);
             return;
         }
        Date di = jtxt_fecha_inicial.getDate();
        Date df = jtxt_fecha_final.getDate();
        if(jtxt_fecha_final.dateEditor.getTexto().length()<10  ||  jtxt_fecha_inicial.dateEditor.getTexto().length()<10)
        new jdlg_error("¡Debes introducir fechas validas!","","Error de formato de fechas").setVisible(true);
        else
        {
            if(di.getTime()<df.getTime())
            {
                this.dispose();
                int index = jcmbx_flotillas.getSelectedIndex();
                TmsFlotillasTbl flotillaSeleccionada = listadoFlotillas.get(index);
                jdlg_RolesManual corridas = new jdlg_RolesManual(busquedas,idrservicio,flotillaSeleccionada,oferta,excluir,usuarioId, jtxt_nombre.getText(), new Timestamp(di.getTime()), new Timestamp(df.getTime()),nombreOferta,new TmsRolesMaestroTbl(), false,vrutes );
                 if(corridas.getHaycorridas())
                 {
                     corridas.setVisible(true);
                 }
                 else
                 {
                     new jdlg_error("¡No hay horarios para generar un rol!"," Verifique si seleccionó alguna ruta","No hay horarios agregados").setVisible(true);
                     corridas.dispose();
                 }
            }
            else
                new jdlg_error("¡La fecha hasta debe ser mayor a la fecha desde!","","").setVisible(true);
        }
    }

   
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jcmbx_flotillas;
    private javax.swing.JLabel jlbl_barraEstado;
    private tms_calendario.JDateChooser jtxt_fecha_final;
    private tms_calendario.JDateChooser jtxt_fecha_inicial;
    private javax.swing.JTextField jtxt_nombre;
    // End of variables declaration//GEN-END:variables

    private TmsRolesManagedBean busquedas;
    private long idrservicio;
    private Vector excluir;
    private long usuarioId;
    private String oferta;
    private List<TmsFlotillasTbl> listadoFlotillas;
    private String nombreOferta;
    private Vector vrutes;
    
}
