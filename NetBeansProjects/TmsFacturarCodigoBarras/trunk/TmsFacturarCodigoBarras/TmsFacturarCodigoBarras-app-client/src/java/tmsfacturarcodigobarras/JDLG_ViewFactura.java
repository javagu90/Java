/*
 * JDLG_CancelacionFactura.java
 *
 * Created on 9 de enero de 2009, 06:57 PM
 */

package tmsfacturarcodigobarras;

import java.io.File;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import solicitud.FacturaTMSREPCONTROLBeanRemote;
import solicitud.TmsSesionBeanFacturarRemote;

/**
 *
 * @author  asolis
 */
public class JDLG_ViewFactura extends javax.swing.JDialog {
    Context cont = null;
    FacturaTMSREPCONTROLBeanRemote RepControlFacade = null;
    String dblink = "";
    String usuario = "";
    String RutAlias ="";
    String RutAbs  ="";
              
    String Prefijo="";
    String TerminalFact="";
    /**
     * Creates new form JDLG_CancelacionFactura
     */
    public JDLG_ViewFactura(java.awt.Frame parent, String dblink, String usuario, String Prefijo,String TerminalFact,String  RutAbs, String RutAlias,FacturaTMSREPCONTROLBeanRemote pRepControlFacade ) {
        super(parent);
        initComponents();
        /*
         try {
            cont = new InitialContext(System.getProperties());
            cosa = (TmsSesionBeanFacturarRemote) cont.lookup(TmsSesionBeanFacturarRemote.class.getName());
            //System.out.println(cosa + " "+cont);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }*/
        this.RepControlFacade = pRepControlFacade;
        this.dblink = dblink;
        this.usuario = usuario;
        this.Prefijo=Prefijo;
        this.TerminalFact = TerminalFact;
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Facturar Boleto | <font color=FF0000> ENTER </font> Visualizar Factura </html>");
        this.setTitle("Vista Prev?a Factura");
        this.RutAlias =RutAlias;
        this.RutAbs  = RutAbs;
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jlbl_factura = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();
        jtxt_factura = new tms_TextFields.JTextTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cancelar Facturas");
        setModal(true);
        setResizable(false);
        jlbl_factura.setText("Folio de Factura: ");

        jtxt_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_facturaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jlbl_factura)
                .add(23, 23, 23)
                .add(jtxt_factura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 254, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlbl_factura)
                    .add(jtxt_factura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(29, 29, 29)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_facturaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
                String respuesta = null;  
                String Foliofactura="";
                if(jtxt_factura.getText().trim().length() <= 0)
                {JOptionPane.showMessageDialog( this, "Escriba el n?mero de factura",
                        "TMS - Facturar Boleto", JOptionPane.WARNING_MESSAGE );
                 return;
                }
                String  FactCancelar="APROBADA";
                //Foliofactura= this.Prefijo+this.TerminalFact+jtxt_factura.getText().trim();
                 
               System.out.println("Factura a cancelar "+jtxt_factura.getText().trim() );
               
               String RutaPDF=RepControlFacade.getRutaPDFFactura(jtxt_factura.getText().trim());
               
               if (RutaPDF == null)
               {
                 JOptionPane.showMessageDialog( this, "La factura "+jtxt_factura.getText().trim()+" no se encuentra en el sistema.",
                                "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                return;
               }
               
               System.out.println("RutAlias "+this.RutAlias );
               System.out.println("RutAbs "+this.RutAbs );
               System.out.println("Ruta a abrir "+RutaPDF );  
             
               OpenFile(RutaPDF.replace(RutAbs,RutAlias));
               
               
              
                
        }
         if(evt.getKeyCode() == evt.VK_ESCAPE) {
            /*int result = JOptionPane.showOptionDialog(this, "?Desea cerrar Cancelar Facturas?",
                    "TMS-Cancelar Facturas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(result == JOptionPane.YES_OPTION)*/
                this.dispose();
        }
                
    }//GEN-LAST:event_jtxt_facturaKeyPressed
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_factura;
    private tms_TextFields.JTextTextField jtxt_factura;
    // End of variables declaration//GEN-END:variables

    
    public void OpenFile(String Ruta)   {
    File nombre;//archivo pdf o xls o doc 
    System.out.println(" Abriendo rutaPDF "+Ruta);
    
    //Ruta="C:\\Compartir\\archivo_guias_prepagadas_05_02_2010_15_46_42.pdf";
      try {     //try statement
        
          nombre = new File(Ruta);
          Process p = null;
          p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+
                                       nombre.getAbsolutePath() ); //+".pdf");
       } catch (Exception e) {
          e.printStackTrace();
      }  
    }
    
}
