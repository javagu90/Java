/*
 * VentaTaePrincipal.java
 *
 * Created on 3 de agosto de 2010, 01:17 PM
 */

package ventatae;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.print.attribute.AttributeSet;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import java.net.URL;

import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import javax.swing.text.MaskFormatter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.swing.text.PlainDocument;
import ventataebean.ejbVentaTAERemote;

import java.text.SimpleDateFormat;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.KeyboardFocusManager;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.naming.InitialContext;
import javax.naming.Context;


import java.awt.Toolkit;
/**
 *
 * @author  opalafox
 */
@Stateless
public class VentaTaePrincipal extends javax.swing.JDialog  {
    
    @EJB    
    public static ejbVentaTAERemote ejbVentaTAEBean;
    
    private MaskFormatter telFormat;
    
    
    private final int P_Exito = 0;
    private final int P_Transactionno_Out = 1;
    private final int P_Referenceid = 2;
    private final int P_Telcelid = 3;
    private final int P_Responsetelcel = 4;
    private final int P_Responsetime = 5;
    private final int P_Status = 6;
    private final int P_Store_Id = 7;
    private final int P_Terminal = 8;  
    private final int P_Amount_Out = 9;
    private final int P_Phone_Out = 10;
    //private final int P_Ciudad_Venta = 1;
    private final int P_Errormsg = 11;
    private final String montoTelcel[] ={"","$20","$30","$50","$100","$150","$200","$300","$500"};
    private final String montoIusa[] ={"","$50","$100","$150","$200","$300","$500","$750","$1000"};
    private final String montoMovistar[] ={"","$30","$60","$70","$120","$140","$200","$300","$500","$1000"};
    private final String montoUnefon[] ={"","$50","$100","$150","$200","$300","$500","$750","$1000"};
    
    private String noUsuario;
    private String nombreUsuario;
    private String usuarioId;
    private String caja;
    private String corteId;
    private String ciudadVenta;
    private String canalVenta;
    
    private String titulo;
    private String taeCompany;
    private String taeUsuario;
    private String taePass;
    
    private String telefono1,telefono2;
    private String compania = "";
    private String monto = "";
    private String resp ="";
    private String SalidaImpresion = "";
    private String tipoPago ="";
    private String ImprimeComp ="";
    
    ProgressMonitor progressMonitor;
    Thread  hilo;
    
        private HLoad hload = null;
  
   private int limite  = 10;  
   private boolean BanRecarga=false;

    /** Creates new form VentaTaePrincipal */
    public VentaTaePrincipal(String taeCompany, String taeUsuario, String taePass, String noUsuario, String nombreUsuario, String usuarioId, String caja, String corteId, String ciudadVenta, String canalVenta,String salidaImpresion, String tipoPago
            , String impricompro) {
        
        
        if (ejbVentaTAEBean == null)
            ejbVentaTAEBean = this.lookupVentaTAEFacade();
     
        System.out.println("Tiempo Aire Rev. 01/10/2010");
        this.taeCompany =  taeCompany;
        this.taeUsuario =  taeUsuario;
        this.taePass    =  taePass;
        this.noUsuario  =  noUsuario;
        this.nombreUsuario = nombreUsuario;
        this.usuarioId     = usuarioId;
        this.caja          = caja;  
        this.corteId       = corteId;
        this.ciudadVenta   = ciudadVenta;
        this.canalVenta    = canalVenta;
        this.titulo        = canalVenta+" - Venta TAE";
        this.SalidaImpresion = salidaImpresion;
         this.tipoPago      = tipoPago;
        this.ImprimeComp   = impricompro; 
        try{
        telFormat = new MaskFormatter("##########");
        }catch (Exception e){
            telFormat = null;
        }    
       
        initComponents();
        System.out.println("Salida a impresion "+this.SalidaImpresion);
        
        jtxtNumero.setText("");
        jtxtConfNumero.setText("");
        jcmbCompania.setSelectedIndex(0);
    //jcmbMonto.setSelectedIndex(0);
        selectMonto();
        
        this.setLocationRelativeTo(null);
        
        JTextComponent.KeyBinding[] newBindings = {
        new JTextComponent.KeyBinding(
          KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK),
          DefaultEditorKit.beepAction),
        new JTextComponent.KeyBinding(
          KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK),
          DefaultEditorKit.beepAction),
        new JTextComponent.KeyBinding(
            KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK),
            DefaultEditorKit.beepAction)
      };
        
           
      Keymap k = jtxtNumero.getKeymap();
      JTextComponent.loadKeymap(k, newBindings, jtxtNumero.getActions());

      k = jtxtConfNumero.getKeymap();
      JTextComponent.loadKeymap(k, newBindings, jtxtConfNumero.getActions());
           // this.getRootPane().setDefaultButton(jbtnAceptar);
      
       
      this.setTitle(titulo);
      this.centrar();   
     
     // jcmbCompania.requestFocusInWindow();
          /*
            jbtnAceptar.getInputMap(jbtnAceptar.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"),"vender");

            jbtnAceptar.getActionMap().put("vender", new AbstractAction("vender") {
              public void actionPerformed(ActionEvent evt) {
                System.out.println("Se acciono el boton Aceptar");
                  Recarga();
                //jbtnAceptar.doClick();
              }
            });
            jbtnCancelar.getInputMap(jbtnCancelar.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F4"),"salir");
            jbtnCancelar.getActionMap().put("salir", new AbstractAction("salir") {
              public void actionPerformed(ActionEvent evt) {
                  System.out.println("Se acciono el boton Cancelar");
              //  jbtnCancelar.doClick();
                  Cancelar();
              }
            });
            */
    

   
/*
  jtxtNumero.addKeyListener(new java.awt.event.KeyAdapter() { 
    public void keyTyped(java.awt.event.KeyEvent e) { 
           int k = e.getKeyChar(); 
           System.out.println(" addKeyListener ");         
          if (jtxtNumero.getText().length() <= 10 - 1) { 
                    //deixe passar 
           } else { 
                 e.setKeyChar((char) KeyEvent.VK_CLEAR); 
            } 
         } 
      } 
    );*/
        

  //jtxtNumero.setDocument(new LimitadorCaracteres((JTextField)jtxtNumero,10));
   
     inhabilitarF10(); 
     
     if( SalidaImpresion ==  null || SalidaImpresion.length() <= 0 )
     { 
         JOptionPane.showMessageDialog(null,"La impresora para comprobantes no esta configurada.\n Contacte al administrador del sistema.",titulo,JOptionPane.ERROR_MESSAGE);
     }
      jcmbCompania.requestFocusInWindow();
      
        this.setModal(true);
        this.setVisible(true);
      jLabelVer.setText("Rev. 06.1.10");
     
    } 
    // 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jlblNumero = new javax.swing.JLabel();
        jlblCompañia = new javax.swing.JLabel();
        jcmbCompania = new javax.swing.JComboBox();
        jlblMonto = new javax.swing.JLabel();
        jbtnAceptar = new javax.swing.JButton();
        jlblImagen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlblConfNumero = new javax.swing.JLabel();
        jcmbMonto = new javax.swing.JComboBox();
        jbtnCancelar = new javax.swing.JButton();
        jtxtNumero = new tms_TextFields.JNumberTextField();
        jLabelVer = new javax.swing.JLabel();
        jtxtConfNumero = new tms_TextFields.JNumberTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Venta TAE");
        jlblNumero.setFont(new java.awt.Font("Tahoma", 0, 18));
        jlblNumero.setLabelFor(jtxtNumero);
        jlblNumero.setText("N\u00famero Celular:");
        jlblNumero.setRequestFocusEnabled(false);

        jlblCompañia.setFont(new java.awt.Font("Tahoma", 0, 18));
        jlblCompañia.setLabelFor(jcmbCompania);
        jlblCompañia.setText("Compa\u00f1ia:");
        jlblCompañia.setRequestFocusEnabled(false);

        jcmbCompania.setFont(new java.awt.Font("Tahoma", 0, 18));
        jcmbCompania.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Telcel", "Movistar", "Iusacell", "Unefon" }));
        jcmbCompania.setNextFocusableComponent(jcmbMonto);
        jcmbCompania.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbCompaniaActionPerformed(evt);
            }
        });
        jcmbCompania.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbCompaniaKeyPressed(evt);
            }
        });

        jlblMonto.setFont(new java.awt.Font("Tahoma", 0, 18));
        jlblMonto.setText("Monto:");
        jlblMonto.setRequestFocusEnabled(false);

        jbtnAceptar.setFont(new java.awt.Font("Tahoma", 1, 14));
        jbtnAceptar.setText("ACEPTAR (F10)");
        jbtnAceptar.setNextFocusableComponent(jbtnCancelar);
        jbtnAceptar.setRequestFocusEnabled(false);
        jbtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAceptarActionPerformed(evt);
            }
        });
        jbtnAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbtnAceptarKeyPressed(evt);
            }
        });

        jlblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlblImagen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlblImagen.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VENTA TAE");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jlblConfNumero.setFont(new java.awt.Font("Tahoma", 0, 18));
        jlblConfNumero.setLabelFor(jtxtNumero);
        jlblConfNumero.setText("Confirmar N\u00famero:");
        jlblConfNumero.setRequestFocusEnabled(false);

        jcmbMonto.setFont(new java.awt.Font("Tahoma", 0, 18));
        jcmbMonto.setNextFocusableComponent(jtxtNumero);
        jcmbMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbMontoKeyPressed(evt);
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14));
        jbtnCancelar.setText("CANCELAR (F4)");
        jbtnCancelar.setRequestFocusEnabled(false);
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });
        jbtnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbtnCancelarKeyPressed(evt);
            }
        });

        jtxtNumero.setFont(new java.awt.Font("Arial", 1, 36));
        jtxtNumero.setNextFocusableComponent(jtxtConfNumero);
        jtxtNumero.setOpaque(false);
        jtxtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtNumeroKeyPressed(evt);
            }
        });

        jLabelVer.setText("Rev. 06.10.10");

        jtxtConfNumero.setFont(new java.awt.Font("Arial", 1, 36));
        jtxtConfNumero.setNextFocusableComponent(jbtnAceptar);
        jtxtConfNumero.setOpaque(false);
        jtxtConfNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtConfNumeroKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(39, 39, 39)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jlblConfNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 191, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jlblNumero, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                                    .add(jtxtNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 363, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jtxtConfNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 363, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(45, 45, 45))
                    .add(layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(jlblImagen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 380, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(93, 93, 93)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jbtnCancelar)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jcmbCompania, 0, 143, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jlblCompañia)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jlblMonto)
                                .add(jcmbMonto, 0, 143, Short.MAX_VALUE)
                                .add(layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(jLabelVer)))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                        .add(jbtnAceptar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                .add(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jLabelVer))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jlblCompañia)
                        .add(15, 15, 15)
                        .add(jcmbCompania, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(21, 21, 21)
                        .add(jlblMonto)
                        .add(24, 24, 24)
                        .add(jcmbMonto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(146, 146, 146)
                        .add(jbtnAceptar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(62, 62, 62)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jbtnCancelar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jtxtConfNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(jlblImagen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(55, 55, 55)
                        .add(jlblNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxtNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jlblConfNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbCompaniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbCompaniaKeyPressed
// TODO add your handling code here:
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_LEFT){
            jcmbCompania.requestFocusInWindow(); 
       //   ((Component)evt.getSource()).transferFocusBackward();
           
        }else if ( evt.getKeyCode() == KeyEvent.VK_RIGHT 
                 || evt.getKeyCode() == KeyEvent.VK_RIGHT ||   evt.getKeyCode() == KeyEvent.VK_TAB ){
                  jcmbMonto.requestFocusInWindow();
               
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F10)
            Recarga();
        else if(evt.getKeyCode() == KeyEvent.VK_F4)
            Cancelar();
    }//GEN-LAST:event_jcmbCompaniaKeyPressed

    private void jcmbMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbMontoKeyPressed
// TODO add your handling code here:
        
        if (evt.getKeyCode() == KeyEvent.VK_LEFT){
           // ((Component)evt.getSource()).transferFocusBackward();
       
             jcmbCompania.requestFocusInWindow();
        }else if ( evt.getKeyCode() == KeyEvent.VK_RIGHT 
                || evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB ){
                        
               //((Component)evt.getSource()).transferFocus();
               jtxtNumero.requestFocusInWindow();
               jtxtNumero.selectAll(); 
        }// TODO add your handling code here:
        else if(evt.getKeyCode() == KeyEvent.VK_F10)
            Recarga();
        else if(evt.getKeyCode() == KeyEvent.VK_F4)
            Cancelar();
    }//GEN-LAST:event_jcmbMontoKeyPressed

    private void jtxtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNumeroKeyPressed
// TODO add your handling code here:
        // TODO add your handling code here:
          if (evt.getKeyCode() == KeyEvent.VK_UP){
              jcmbMonto.requestFocusInWindow();
          }
          else if ( evt.getKeyCode() == KeyEvent.VK_DOWN 
                   ){ // || evt.getKeyCode() == KeyEvent.VK_TAB   || evt.getKeyCode() == KeyEvent.VK_ENTER 
                  jtxtConfNumero.requestFocusInWindow();
                  jtxtConfNumero.selectAll();   
                //  System.out.println("Se oprimio tecla enter en numero tel ");
        }// TODO add your handling code here:
          else  if(evt.getKeyCode() == KeyEvent.VK_F10)
            Recarga();
           else if(evt.getKeyCode() == KeyEvent.VK_F4)
            Cancelar();
    }//GEN-LAST:event_jtxtNumeroKeyPressed

    private void jtxtConfNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtConfNumeroKeyPressed
// TODO add your handling code here:
        // TODO add your handling code here:
             if (evt.getKeyCode() == KeyEvent.VK_UP){
              jtxtNumero.requestFocusInWindow();
              jtxtNumero.selectAll();   
          }
          else if ( evt.getKeyCode() == KeyEvent.VK_DOWN  
                      ){  // || evt.getKeyCode() == KeyEvent.VK_TAB   || evt.getKeyCode() == KeyEvent.VK_ENTER 
               jbtnAceptar.requestFocusInWindow();
              
        }// TODO add your handling code here:
          else  if(evt.getKeyCode() == KeyEvent.VK_F10)
            Recarga();
           else if(evt.getKeyCode() == KeyEvent.VK_F4)
            Cancelar();
    }//GEN-LAST:event_jtxtConfNumeroKeyPressed

    private void jbtnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtnCancelarKeyPressed
// TODO add your handling code here:
        
     //  System.out.println("Cancelando "+evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_UP){
            jbtnAceptar.requestFocusInWindow();
        }else if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_TAB){
             jcmbCompania.requestFocusInWindow();
        }
        else  if ( evt.getKeyCode() == evt.VK_F4 || evt.getKeyCode() == evt.VK_ENTER){
           
            Cancelar();
        }
        else  if(evt.getKeyCode() == KeyEvent.VK_F10)
            Recarga();
       
    }//GEN-LAST:event_jbtnCancelarKeyPressed

    private void jbtnAceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbtnAceptarKeyPressed
// TODO add your handling code here:
           if (evt.getKeyCode() == KeyEvent.VK_UP){
             jtxtConfNumero.requestFocusInWindow();
             jtxtConfNumero.selectAll(); 
        }else if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_TAB){
                        
               //((Component)evt.getSource()).transferFocus();
               jbtnCancelar.requestFocusInWindow();
        }// TODO add your handling code here:
        else if ( evt.getKeyCode() == KeyEvent.VK_ENTER ||  evt.getKeyCode() == KeyEvent.VK_F10 ){   
            { System.out.println("Entro por enter");
                Recarga(); }
        }
        else if(evt.getKeyCode() == KeyEvent.VK_F4)
            Cancelar(); 
        
    }//GEN-LAST:event_jbtnAceptarKeyPressed
/*    public void keyPressed(java.awt.event.KeyEvent evt){
         System.out.println(evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_F10){
            System.out.println("F10");
        }else if (evt.getKeyCode() == KeyEvent.VK_F4){
            System.out.println("F4");
        }
       
    }
    public void keyReleased(KeyEvent evt) {
               System.out.println(evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_F10){
            System.out.println("F10");
        }else if (evt.getKeyCode() == KeyEvent.VK_F4){
            System.out.println("F4");
        }
            
  }
    

  public void keyTyped(KeyEvent evt) {

            
  }
*/
    private ejbVentaTAERemote lookupVentaTAEFacade() {
        try {
            Context c = new InitialContext();
            return (ejbVentaTAERemote) c.lookup("ventataebean.ejbVentaTAERemote");
        }
        catch(Exception ne) {
            ne.printStackTrace();
            
            throw new RuntimeException(ne);
        } 
    }

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
      
     Cancelar();
     
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    
    public void Cancelar()
    {
     // System.out.println(" Entra a cancelar  ");
       if (hilo != null){
           hilo.interrupt();
           hilo = null;
        }
        this.dispose();
    }
  
    
    private void jbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAceptarActionPerformed
   System.out.println("Entro pro enter");
      Recarga();
    
    }//GEN-LAST:event_jbtnAceptarActionPerformed
       
    
    private void jcmbCompaniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbCompaniaActionPerformed

        selectMonto();
        
    }//GEN-LAST:event_jcmbCompaniaActionPerformed
   
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentaTaePrincipal("8002","EstrellaRoja","123456","8485","Iván Muñoz","6","PV044","1503318",
                                      "CAPU","TMS Móvil","\\\\CAPUSIS61\\EPSON890","EFE","S");   // "\\\\CAPUSIS53\\HPLaserJ"
                
            }
        });
    }
   
    public void Recarga()
    {
        System.out.println("BanRecarga  "+BanRecarga);
        if (!BanRecarga ){
            BanRecarga= true;
            javax.swing.JProgressBar progressBar1 = new javax.swing.JProgressBar();
            progressBar1.setIndeterminate(true);
            telefono1 = jtxtNumero.getText().trim();
            telefono2 = jtxtConfNumero.getText().trim();
            //System.out.println("tel "+telefono1.length() +"  telefono2  "+telefono2.length() );
            //System.out.println("Compania "+jcmbCompania.getSelectedIndex() +"  Monto  "+jcmbMonto.getSelectedIndex() );

            /*if(telefono1.length() <=0 || telefono2.length() <=0 ||
               jcmbCompania.getSelectedIndex() < 0 || jcmbMonto.getSelectedIndex() <= 0 )
               JOptionPane.showMessageog(null,"Verifique Los datos.",titulo,JOptionPane.ERROR_MESSAGE);
              return;
           */

            if(telefono1.length() != 10 ){
             //this.setModal(false);
             JOptionPane.showMessageDialog(null,"Favor de verificar el Número Celular. Deben ser 10 digitos.",titulo,JOptionPane.ERROR_MESSAGE);
             jtxtNumero.requestFocus();
             jtxtNumero.selectAll(); 
             //this.setModal(true);
             BanRecarga=false;
             return;
            }
            if(telefono2.length() != 10 ){
             JOptionPane.showMessageDialog(null,"Favor de verificar Confirmar Número. Deben ser 10 digitos.",titulo,JOptionPane.ERROR_MESSAGE);
             jtxtConfNumero.requestFocus();
             jtxtConfNumero.selectAll(); 
             BanRecarga=false;
             return;
            }
             //************  Validando monto
             if (jcmbMonto.getSelectedIndex() <= 0){ // BRA 

                 JOptionPane.showMessageDialog(null,"Favor de verificar el Monto.",titulo,JOptionPane.ERROR_MESSAGE);
                 jcmbMonto.setSelectedIndex(0);
                 jcmbMonto.requestFocusInWindow();
                 jcmbMonto.setSelectedIndex(0);
                 BanRecarga=false;
                 return;
            }
                //***********
            if (jcmbCompania.getSelectedIndex() >= 0){ // BRA 
                compania = jcmbCompania.getSelectedItem().toString().substring(0,1);

                if (jcmbMonto.getSelectedIndex() != 0){
                    monto = jcmbMonto.getSelectedItem().toString().substring(1);
                    if (telefono1.compareTo(telefono2) == 0){
                        //TODO BIEN :D
                        String msj="<HTML>¿Estos datos son Correctos?<BR><BR><BR>" +
                                "<table><tr><td><font size = +1>Compañía: </font></td><td><font size = +5>"+jcmbCompania.getSelectedItem()+"</font></td></tr><tr>" +
                                           "<td><font size = +1>Teléfono: </font></td><td><font size = +5>"+telefono1+"</font></td></tr><tr>" +
                                           "<td><font size = +1>Monto: </font></td><td><font size = +5>"+jcmbMonto.getSelectedItem()+"</font></td></tr></table></HTML>";

                        hload = new HLoad(this,true);
                        hload.jPgrStatus.setString("Procesando Venta-TAE  "+jcmbCompania.getSelectedItem().toString());


                        JdlQuestion jdlQues = new JdlQuestion(this.getParent(), true, msj);
                        jdlQues.setVisible(true);
                        boolean resp_jdl = jdlQues.getRespuesta();

                        //int opt = JOptionPane.showConfirmDialog(this, "<HTML>¿Estos datos son Correctos?<BR><table><tr><td><font size = +1>Compañía: </font></td><td<font size = +5>"+jcmbCompania.getSelectedItem()+"</font></td></tr><tr><td><font size = +1>Teléfono: </font></td><td><font size = +5>"+telefono1+"</font></td></tr><tr><td><font size = +1>Monto: </font></td><td><font size = +5>"+jcmbMonto.getSelectedItem()+"</font></td></tr></table></HTML>",titulo,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                       // if (opt == 0){  

                        if(resp_jdl){    

                         //   progressMonitor = new ProgressMonitor(this.getParent(), "Procesando venta TAE",jcmbCompania.getSelectedItem().toString(), 0, 40);    

                              try{
                                hilo = new Thread(){
                                    public void run(){
                                        try{
                                        //    Thread.sleep(10000);   //   Manda a llamr el servicio wevb de venta  //"true,2801,797424";
                                        //  resp=  "TRUE,2986,CROSS2986,132280,00,2010-09-28 13:36:26,5,00001,0000000001,20.0,2228610027,Éxito";
                                          resp = ejbVentaTAEBean.ventaTaePrincipal(taeCompany,taeUsuario,taePass,compania,telefono1,monto,tipoPago,noUsuario,usuarioId,caja,corteId,ciudadVenta,canalVenta);
                                          System.out.println("Salida Venta TAE -- > "+resp);
                                            hload.setVisible(false);
                                            hload = null;

                                        }catch (Exception ex){
                                           ex.printStackTrace();
                                           JOptionPane.showMessageDialog(null,ex.getMessage(),titulo,JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                };
                                hilo.start();
                                this.setModal(true);

                                Thread  hilo2 = new Thread(){
                                    public void run(){


                                        try{
                                            int count = 1;

                                            while (hilo.isAlive()){
                             //                   System.out.println(count+" JAVA2");
                                                count += 1;
                                                try{
                                                    Thread.sleep(500);
                                                    if (count > 20)
                                                        count = 1;

                                              //      progressMonitor.setProgress(count);
                                                     hload.setVisible(true);


                                                }catch(Exception e){
                                                    System.out.println("Error");
                                                    BanRecarga=false;
                                                }
                                            }
                                        //    progressMonitor.setProgress(20);

                                        if(hload != null )
                                            {  hload.setVisible(false);
                                                hload = null;}
                                            BanRecarga= false ;

                                            String taeR2[] = resp.split(",");
                                            
                                            if (taeR2[P_Exito].compareTo("TRUE") == 0){
                                              //  String msg = "\nOperación Exitosa\n";

                                                      setPrint(taeR2);

                                                JOptionPane.showMessageDialog(null,"\nOperación Exitosa\n",titulo,JOptionPane.INFORMATION_MESSAGE);
                                                jcmbMonto.setSelectedIndex(0);
                                                jcmbCompania.setSelectedIndex(0);
                                                jtxtNumero.setText("");
                                                jtxtConfNumero.setText("");
                                               // jlblImagen.setIcon(null);
                                                jcmbCompania.requestFocusInWindow();

                                            }else{
                                                String msg = "\nOperación fallida\n"+taeR2[P_Errormsg]+"\n";
                                                JOptionPane.showMessageDialog(null,msg,titulo,JOptionPane.ERROR_MESSAGE);

                                            }
                                       }catch (Exception ex){  
                                           ex.printStackTrace();
                                           BanRecarga=false;
                                           JOptionPane.showMessageDialog(null,ex.getMessage(),titulo,JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                };

                                this.setVisible(false);
                                hilo2.start();
                               this.setVisible(true);
                               //String resp = ejbVentaTAEBean.ventaTaePrincipal(taeCompany,taeUsuario,taePass,compañía,teléfono1,monto,noUsuario,usuarioId,caja,corteId,ciudadVenta,canalVenta);
                                /*
                                String taeR2[] = resp.split(",");

                                if (taeR2[P_Exito].compareTo("TRUE") == 0){
                                  //  String msg = "\nOperación Exitosa\n";
                                    this.setPrint(taeR2);
                                    JOptionPane.showMessageDialog(this,"\nOperación Exitosa\n",titulo,JOptionPane.INFORMATION_MESSAGE);

                                }else{
                                    String msg = "\nOperación fallida\n"+taeR2[P_Errormsg]+"\n";
                                    JOptionPane.showMessageDialog(this,msg,titulo,JOptionPane.ERROR_MESSAGE);

                                }

                             */
                            }catch (Exception ex){
                                System.out.println("Error 2");
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(this,ex.getMessage(),titulo,JOptionPane.ERROR_MESSAGE);
                            }

                        }

                    }else{
                        JOptionPane.showConfirmDialog(this, "Los Números de celular ingresados no coinciden.",titulo,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                        jtxtNumero.setRequestFocusEnabled(true);
                        jtxtNumero.selectAll(); 
                    }


                 }else{
                    JOptionPane.showConfirmDialog(this, "Favor de seleccioanr el Monto.",titulo,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                    jcmbMonto.setRequestFocusEnabled(true);  
                }
            }
            else{
                JOptionPane.showConfirmDialog(this, "Favor de seleccionar una Compañía.",titulo,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                jcmbCompania.setRequestFocusEnabled(true);  
            }

           BanRecarga= false;
        }    
    }
    
    
    public void selectMonto()
    {
        if (jcmbCompania.getSelectedIndex() != -1){
            ClassLoader cldr = this.getClass().getClassLoader();
            
            //System.out.println(cldr.toString());
         //   URL imageurl = cldr.getResource("ventatae/images/"+ jcmbCompañia.getSelectedItem()+".jpg");
            
            URL imageURL = VentaTaePrincipal.class.getResource("images/"+ jcmbCompania.getSelectedItem().toString().toLowerCase()+".jpg");
         //    System.out.println(imageURL);
            //Toolkit.getDefaultToolkit().getImage
                    //jlblImagen.setIcon(new ImageIcon(new ImageLoader().loadImage("ventatae/images/"+ jcmbCompañia.getSelectedItem()+".jpg")) );
            
            jlblImagen.setIcon(new ImageIcon(imageURL));
            switch (jcmbCompania.getSelectedIndex()){
               // case 0:
                 //   jlblImagen.setIcon(null);
                  //  break;
                case 0: //Telcel
                    jcmbMonto.setModel(new DefaultComboBoxModel(montoTelcel));
                
                    break;
                case 1: //Movistar
                    jcmbMonto.setModel(new DefaultComboBoxModel(montoMovistar));
                    break;
                case 2: //Iusacell
                    jcmbMonto.setModel(new DefaultComboBoxModel(montoIusa));
                    break;
                case 3: //Unefon
                    jcmbMonto.setModel(new DefaultComboBoxModel(montoUnefon));
                    break;
         
                   
            }
        }
    }
    
    
    
    private boolean setPrint(String arrdata[]){
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
        SimpleDateFormat formatt = new SimpleDateFormat ("ddMMyyyy-hhmmss");
        Calendar clr = Calendar.getInstance();
        
        System.out.println("Entro a Imprimir "+ this.ImprimeComp + "   " +SalidaImpresion );
        if (this.ImprimeComp.equalsIgnoreCase("S")  && SalidaImpresion != null && SalidaImpresion.length()>0) {
            
            System.out.println("Entro a Imprimir "+SalidaImpresion);   
            int opt = JOptionPane.showConfirmDialog(this.getParent(), "¿Desea Imprimir el ticket? ",titulo,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);    
             if(opt !=0)
                 {   return true; }
            
                String sCad="\n";

                sCad += "      AUTOBUSES MEXICO PUEBLA\n";
                sCad += "     ESTRELLA ROJA S.A. DE C.V.\n";
                sCad += "\n";     
                sCad += "Boulevard Norte Heroes del 5 de Mayo\n";
                sCad += "no.4222 Col. las Cuartillas Pue,Pue.\n";
                sCad += "\n";
                sCad += "\n";
                sCad += "        Recarga Tiempo Aire\n";
                sCad += "             "+jcmbCompania.getSelectedItem().toString().toUpperCase()+"\n";
                sCad += "\n";     
                sCad += "\n";
                sCad += "Fecha:      "+formatf.format(clr.getTime())+"\n";
                sCad += "Tienda:     "+arrdata[P_Store_Id]+"\n";
                sCad += "Terminal:   "+arrdata[P_Terminal]+"\n";
                sCad += "Lugar:      "+ciudadVenta+"\n";
                sCad += "Fecha V.:   "+arrdata[P_Responsetime]+"\n";
                sCad += "Referencia: "+arrdata[P_Referenceid]+"\n";
                sCad += "Teléfono:   "+arrdata[P_Phone_Out]+"\n";
                sCad += "Folio:      "+arrdata[P_Transactionno_Out]+"\n";
                sCad += "Importe:    "+arrdata[P_Amount_Out]+"\n";

                sCad += "No. Aut:    "+arrdata[P_Telcelid]+"\n";
                sCad += "\n";
                sCad += "\n";
                sCad += "Usuario:    "+noUsuario+"-"+nombreUsuario+"\n";
                sCad += "\n";
                sCad += "************************************\n";

                sCad += "    Comprobante sin valor fiscal\n";
               /*
                sCad += "SI USTED DESEA FACTURAR ESTA COMPRA,\n";
                sCad += "FAVOR DE COMUNICARSE AL TEL *333,\n";
                sCad += "*264 DE ATENCIÓN AL CLIENTE TELCEL\n";
                **/
              //  String SalidaImpresion = "LPT1";
                
                  System.out.println("Imprimiendo "); 
                try{
                    if(SalidaImpresion.equals("ARCHIVO"))
                        SalidaImpresion = "C:\\ticket_TAE"+ formatt.format(clr.getTime()) +".txt";
                    

                    //FileDescriptor fd = new FileDescriptor();
                    FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM \\CAPUSIS53\HPLaserJ
                    PrintStream ps = new PrintStream(os); 
                    ps.print(sCad); // CADENA A IMPRIMIR

                    ps.flush();
                    os.close();
                }catch(java.io.FileNotFoundException fsctex){
                    fsctex.printStackTrace();
                    SalidaImpresion = "C:\\errorTicketLog.txt";
                    FileOutputStream os;
                    try {
                        os = new FileOutputStream(SalidaImpresion);
                        PrintStream ps = new PrintStream(os); 
                        ps.print(sCad); // CADENA A IMPRIMIR
                        ps.flush();
                        try {
                            os.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } // LPT / C:\\ARCHIVO.TXT / COM
                    return false;
                }catch(Exception sctex){
                    sctex.printStackTrace();
                    return false;
                }
        } // if (this.ImprimeComp.equalsIgnoreCase("S")) 
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelVer;
    public javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JComboBox jcmbCompania;
    private javax.swing.JComboBox jcmbMonto;
    private javax.swing.JLabel jlblCompañia;
    private javax.swing.JLabel jlblConfNumero;
    private javax.swing.JLabel jlblImagen;
    private javax.swing.JLabel jlblMonto;
    private javax.swing.JLabel jlblNumero;
    private tms_TextFields.JNumberTextField jtxtConfNumero;
    private tms_TextFields.JNumberTextField jtxtNumero;
    // End of variables declaration//GEN-END:variables
    

    
     private void inhabilitarF10() {
        System.out.println("Deshabilita F10"); 
        this.jcmbCompania.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
       // this.jcmbCompania.registerKeyboardAction(new ActionListener() { 
        //public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);

        this.jcmbMonto.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        //this.jcmbMonto.registerKeyboardAction(new ActionListener() { 
        //public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);

        
        this.jtxtNumero.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        //this.jtxtNumero.registerKeyboardAction(new ActionListener() { 
        //public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);

        this.jtxtConfNumero.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        //this.jtxtConfNumero.registerKeyboardAction(new ActionListener() { 
        //public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);

        this.jbtnAceptar.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
                                                          
        this.jbtnAceptar.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);

        this.jbtnCancelar.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jbtnCancelar.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
              

   }

public void centrar(){
    
      Dimension pantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
      // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
         this.setLocation((pantalla.width - ventana.width) / 2,
                                (pantalla.height - ventana.height) / 2);
    }
    
  
    


}



