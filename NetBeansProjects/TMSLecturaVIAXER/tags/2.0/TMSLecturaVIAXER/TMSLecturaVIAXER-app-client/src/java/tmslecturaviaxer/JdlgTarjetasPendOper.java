/*
 * JdlgTarjetasPendOper.java
 *
 * Created on 5 de julio de 2008, 03:15 PM
 */

package tmslecturaviaxer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author  vgonzalez
 */
public class JdlgTarjetasPendOper extends javax.swing.JDialog {
    
    /** Creates new form JdlgTarjetasPendOper */
    public JdlgTarjetasPendOper(TmsLecturaViaxerManagedBean pbusquedas, Vector tarPend) {
        //super(parent, modal);
        initComponents();
        this.tarPend = ptarPend;
        this.busquedas = pbusquedas;
        encabezadoPartidas = new Vector();
        encabezadoPartidas.add("Folio");
        encabezadoPartidas.add("Servicio");
        encabezadoPartidas.add("Horigen");
        encabezadoPartidas.add("Destino");
        encabezadoPartidas.add("Autobus");
        encabezadoPartidas.add("Fecha");
        encabezadoPartidas.add("Hora");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setModal(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
           jlbl_barraEstado.setText("<html>  <font color=FF0000>   Enter </font> Asignar Folio | <font color=FF0000> F1  </font> Buscar Tarjeta | <font color=FF0000> Arriba/Abajo </font>  Moverse entre tarjetas | <font color=FF0000> F2 </font> Dejar pendiente | <font color=FF0000> ESC </font> Cerrar</html>");
//         jlbl_barraEstado.setFocusTraversalKeysEnabled(false);
           jtbl_TarjetasPendientes.setModel(defaultmodelo);
           if(tarPend.size()>0)
           {
               defaultmodelo.setDataVector(tarPend,encabezadoPartidas);
               jtbl_TarjetasPendientes.setRowSelectionInterval(0,0);
               jtbl_TarjetasPendientes.requestFocus();
               resizeColumnasRoles();
           }
           else
               jlbl_barraEstado.requestFocus();
           
           
    }
    
      private void resizeColumnasRoles(){
        TableColumn columinv = jtbl_TarjetasPendientes.getColumnModel().getColumn(0); columinv.setMinWidth( 150 );columinv.setMaxWidth( 150 );columinv.setPreferredWidth(150);
        columinv = jtbl_TarjetasPendientes.getColumnModel().getColumn(1); columinv.setMinWidth(90);columinv.setMaxWidth(90);columinv.setPreferredWidth(90);
//        columinv = jtbl_rolesBase.getColumnModel().getColumn(2); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
//        columinv = jtbl_rolesBase.getColumnModel().getColumn(3); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
//        columinv = jtbl_rolesBase.getColumnModel().getColumn(4); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
//        columinv = jtbl_rolesBase.getColumnModel().getColumn(5); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
//        columinv = jtbl_rolesBase.getColumnModel().getColumn(6); columinv.setMinWidth( 55 );columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
//        columinv = jtbl_rolesBase.getColumnModel().getColumn(7);columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);        
//        columinv = jtbl_rolesBase.getColumnModel().getColumn(8);columinv.setMinWidth(90);columinv.setMaxWidth(90);columinv.setPreferredWidth(90);        
        }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlbl_nombreOperador = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_TarjetasPendientes = new javax.swing.JTable();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarjetas Pendientes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Operador:");

        jlbl_nombreOperador.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_nombreOperador.setText(" ");

        jtbl_TarjetasPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbl_TarjetasPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_TarjetasPendientesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbl_TarjetasPendientesKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_TarjetasPendientes);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_nombreOperador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jlbl_nombreOperador))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_barraEstado.setText("jLabel3");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jlbl_barraEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_barraEstadoKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlbl_barraEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbl_barraEstadoKeyPressed
        if(evt.getKeyCode() == evt.VK_F1){
            this.setAlwaysOnTop(false);
            JDialogBuscarTarjeta dbt = new JDialogBuscarTarjeta(busquedas);
            dbt.setVisible(true);
            this.setAlwaysOnTop(true);
            if(!dbt.getFolioTar().equals("nada"))
            {
                defaultmodelo.setDataVector(null,encabezadoPartidas) ;
                defaultmodelo.setDataVector(dbt.getVFolioTar(),encabezadoPartidas);
                jtbl_TarjetasPendientes.setRowSelectionInterval(0,0);
                jtbl_TarjetasPendientes.requestFocus();
                resizeColumnasRoles();
                jtbl_TarjetasPendientes.repaint();
            }
        }
        
        if(evt.getKeyCode() == evt.VK_F2){
            jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de Asignacion", "¿Seguro que desea dejar la relacion Pendiente ?");
              psn.setVisible(true);
              if(respuestaSN)
              {
                  folioTarjeta = "PENDIENTE";
                  this.dispose();
              }
        }

        
        if(evt.getKeyCode() == evt.VK_ESCAPE){
            folioTarjeta = "nada";
            this.dispose();
        }        
    }//GEN-LAST:event_jlbl_barraEstadoKeyPressed

    private void jtbl_TarjetasPendientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_TarjetasPendientesKeyPressed
        if(evt.getKeyCode() == evt.VK_F1){
            this.setAlwaysOnTop(false);
            JDialogBuscarTarjeta dbt = new JDialogBuscarTarjeta(busquedas);
            dbt.setVisible(true);
            this.setAlwaysOnTop(true);
            if(!dbt.getFolioTar().equals("nada"))
            {
                defaultmodelo.setDataVector(null,encabezadoPartidas) ;
                defaultmodelo.setDataVector(dbt.getVFolioTar(),encabezadoPartidas);
            }
        }  
        
        if(evt.getKeyCode() == evt.VK_F2){
            jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de Asignacion", "¿Seguro que desea dejar la relacion Pendiente ?");
              psn.setVisible(true);
              if(respuestaSN)
              {
                  folioTarjeta = "PENDIENTE";
                  this.dispose();
              }
        }        
        
        if(evt.getKeyCode() == evt.VK_ENTER){
              if(jtbl_TarjetasPendientes.getSelectedRow()<0)
              {
                  new jdlg_advertencia("¡Debes seleccionar una tarjeta!","","").setVisible(true);
                  return;
              }             
             jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de Asignacion", "¿Seguro que desea sustituir por el folio "+jtbl_TarjetasPendientes.getValueAt(jtbl_TarjetasPendientes.getSelectedRow(),0).toString()+" ?");
              psn.setVisible(true);
              if(respuestaSN)
              {

                  folioTarjeta = jtbl_TarjetasPendientes.getValueAt(jtbl_TarjetasPendientes.getSelectedRow(),0).toString();
                  this.dispose();
              }
              else
                  folioTarjeta = "nada";
              
        }
        
        if(evt.getKeyCode() == evt.VK_ESCAPE){
            folioTarjeta = "nada";
            this.dispose();
        }
    }//GEN-LAST:event_jtbl_TarjetasPendientesKeyPressed

    private void jtbl_TarjetasPendientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_TarjetasPendientesKeyReleased

    }//GEN-LAST:event_jtbl_TarjetasPendientesKeyReleased
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new JdlgTarjetasPendOper(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_nombreOperador;
    private javax.swing.JTable jtbl_TarjetasPendientes;
    // End of variables declaration//GEN-END:variables
    private TmsLecturaViaxerManagedBean busquedas;
    //private Object[] encabezadoPartidas = {"Folio","Servicio","Horigen","Destino","Autobus","Fecha","Hora"};
    private Vector encabezadoPartidas;
    private DefaultTableModel defaultmodelo= new DefaultTableModel(null,encabezadoPartidas){
    public boolean isCellEditable (int row, int column){ if (column == 20) return true; return false;}};
    private Vector tarPend;
    private String folioTarjeta;
    private Vector ptarPend;
    private boolean respuestaSN = true;
    

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
                jlbl_mensajeKeyPressed(evt);
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

    private void jlbl_mensajeKeyPressed(java.awt.event.KeyEvent evt) {                                        
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

    public String getFolioTarjeta() {
        return folioTarjeta;
    }

}
