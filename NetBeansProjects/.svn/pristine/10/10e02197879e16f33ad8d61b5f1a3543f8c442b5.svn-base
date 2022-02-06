/*
 * JIF_PasesTrasladov1.java
 *
 * Created on 29 de enero de 2009, 07:18 PM
 */

package tmspases_traslado;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterJob;
import java.beans.PropertyVetoException;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.PrintService;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import solicitud.TMSPasesTrasladoFacadeRemote;

/**
 *
 * @author  asolis
 */
public class JIF_PasesTrasladov1 extends javax.swing.JInternalFrame {
    String p_nombre = "";
    Context cont = null;
    TMSPasesTrasladoFacadeRemote cosa = null;
    String usuario = null;
    private KeyEvent eventoTeclado;
    Vector datosIniciales = null;
    PrintService impresoraEncontrada = null;
    Vector rutitas;    
            
    /** Creates new form JIF_PasesTrasladov1 */
    public JIF_PasesTrasladov1(Vector datosIniciales) {
        this.usuario = datosIniciales.get(1).toString();
        this.p_nombre = datosIniciales.get(2).toString();
        this.datosIniciales = datosIniciales;
        try {
            cont = new InitialContext(System.getProperties());
            cosa = (TMSPasesTrasladoFacadeRemote) cont.lookup(TMSPasesTrasladoFacadeRemote.class.getName());
            //System.out.println(cosa + " "+cont);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        initComponents();
        this.setIconifiable(true);
        this.setTitle("Pase de Traslado Rev25.11.09");
        jntxt_operador.setBackground(new Color(192,190,207));
        jntxt_operador.requestFocus();
        jlbl_barraEstado.setText("<html>  <font color=FF0000> F4</font> Salir de Pases de Traslado | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Campos | <font color=FF0000> F12 </font> Cancelar Pase de Traslado | <font color=FF0000> ENTER </font> Imprimir Pase de Traslado | <font color=FF0000> ESC </font> Limpiar Campos  | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Siguiente ventana</html>");
        PcInfo pc = new PcInfo();
        boolean existeimp = false;
        PrintService impresoraEncontrada = null;
        Vector vimp = (Vector) cosa.buscarImpresoraFacturas(pc.getHostName().toString().toUpperCase());
        if(vimp.size()==0) {
              //enviar mensaje de error indicando que no hay impresoras para facturas configuradas en la caja
              JOptionPane.showMessageDialog( this, "No hay impresoras configuradas para esta caja. Contacte al administrador del sistema",
                                "TMS - Pase de Traslado", JOptionPane.ERROR_MESSAGE ); 
              jntxt_operador.setBackground(new Color(192,190,207));
              jntxt_operador.requestFocus();
        }
        else
        {
             String nomImpre = vimp.get(0).toString();
             nomImpre = nomImpre.replace('[', ' ');
             nomImpre = nomImpre.replace(']', ' ');
             nomImpre = nomImpre.trim();
             System.out.println("nomImpre " + nomImpre);

             PrintService service[] = PrinterJob.lookupPrintServices();
             System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
             for(int i=0; i<service.length;i++)
             {
                System.out.println("        "+service[i]. getName());
                if(service[i].getName().toUpperCase().equals(nomImpre.toUpperCase()))
                {
                    existeimp = true;
                    impresoraEncontrada = service[i];
                }
             }
        }

           if(!existeimp) {
                    //enviar mensaje de error indicando que la impresoras para facturas no esta configurada en Windows  
                    JOptionPane.showMessageDialog( this, "La impresora no esta configurada en Windows. Contacte al administrador del sistema",
                     "TMS - Pase de Traslado", JOptionPane.ERROR_MESSAGE );
                    jntxt_operador.setBackground(new Color(192,190,207));
                    jntxt_operador.requestFocus();
           }
           Vector rutas = (Vector) cosa.buscarRutas();
           rutitas = new Vector();
           System.out.println("rutas "+rutas+" rutas.size "+rutas.size());
           for(int i = 0; i < rutas.size(); i++) {                
                if(((Vector)rutas.get(i)).get(2).toString().equals("0"))
                    JOptionPane.showMessageDialog( this, "El peaje para la ruta "+ ((Vector)rutas.get(i)).get(1).toString() +" no esta configurado",
                            "TMS - Pase de Traslado", JOptionPane.ERROR_MESSAGE );
                else{
                    jcmbox_ruta.addItem(((Vector)rutas.get(i)).get(0).toString());
                    rutitas.add(rutas.get(i));
                }
           }
           Vector motivos = (Vector) cosa.buscarMotivos();
           System.out.println("motivos "+motivos+ "motivos.size "+motivos.size());
           for(int i = 0; i < motivos.size(); i++)
               jcmbox_motivo.addItem(((Vector)motivos.get(i)).get(0).toString());
        }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jlbl_titulo = new javax.swing.JLabel();
        jlbl_autobus = new javax.swing.JLabel();
        jlbl_operador = new javax.swing.JLabel();
        jntxt_autobus = new tms_TextFields.JNumberTextField();
        jntxt_operador = new tms_TextFields.JNumberTextField();
        jlbl_ruta = new javax.swing.JLabel();
        jcmbox_ruta = new javax.swing.JComboBox();
        jlbl_barraEstado = new javax.swing.JLabel();
        jcmbox_motivo = new javax.swing.JComboBox();
        jlbl_motivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jlbl_titulo.setFont(new java.awt.Font("Tahoma", 1, 14));
        jlbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbl_titulo.setText("Pase de Traslado");
        jlbl_titulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jlbl_autobus.setText("No. Econ\u00f3mico de Autob\u00fas: ");

        jlbl_operador.setText("Clave de Operador: ");

        jntxt_autobus.setBackground(new java.awt.Color(255, 255, 204));
        jntxt_autobus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jntxt_autobusFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jntxt_autobusFocusLost(evt);
            }
        });
        jntxt_autobus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jntxt_autobusKeyPressed(evt);
            }
        });

        jntxt_operador.setBackground(new java.awt.Color(255, 255, 204));
        jntxt_operador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jntxt_operadorFocusLost(evt);
            }
        });
        jntxt_operador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jntxt_operadorKeyPressed(evt);
            }
        });

        jlbl_ruta.setText("Ruta: ");

        jcmbox_ruta.setBackground(new java.awt.Color(255, 255, 204));
        jcmbox_ruta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmbox_rutaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbox_rutaFocusLost(evt);
            }
        });
        jcmbox_ruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbox_rutaKeyPressed(evt);
            }
        });

        jlbl_barraEstado.setText("revision");

        jcmbox_motivo.setBackground(new java.awt.Color(255, 255, 204));
        jcmbox_motivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcmbox_motivoFocusLost(evt);
            }
        });
        jcmbox_motivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmbox_motivoKeyPressed(evt);
            }
        });
        jcmbox_motivo.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jcmbox_motivoComponentAdded(evt);
            }
        });

        jlbl_motivo.setText("Motivo: ");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jlbl_autobus)
                            .add(jlbl_operador)
                            .add(jlbl_ruta)
                            .add(jlbl_motivo))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jcmbox_ruta, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jntxt_autobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jntxt_operador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .add(jcmbox_motivo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jlbl_titulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 291, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jlbl_titulo)
                .add(22, 22, 22)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlbl_operador)
                    .add(jntxt_operador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlbl_autobus)
                    .add(jntxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlbl_ruta)
                    .add(jcmbox_ruta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jcmbox_motivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_motivo))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 65, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jntxt_autobusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jntxt_autobusFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000> F4</font> Salir de Pases de Traslado | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Campos | <font color=FF0000> F12 </font> Cancelar Pase de Traslado | <font color=FF0000> ENTER </font> Imprimir Pase de Traslado | <font color=FF0000> ESC </font> Limpiar Campos   |<font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Siguiente ventana</html>");
    }//GEN-LAST:event_jntxt_autobusFocusGained

    private void jcmbox_motivoComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jcmbox_motivoComponentAdded
       jlbl_barraEstado.setText("<html>  <font color=FF0000> F4</font> Salir de Pases de Traslado | <font color=FF0000> « »</font> Moverse entre Campos | <font color=FF0000> F12 </font> Cancelar Pase de Traslado | <font color=FF0000> ENTER </font> Imprimir Pase de Traslado | <font color=FF0000> ESC </font> Limpiar Campos  |<font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Siguiente ventana</html>");
    }//GEN-LAST:event_jcmbox_motivoComponentAdded

    private void jcmbox_rutaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbox_rutaFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000> F4</font> Salir de Pases de Traslado | <font color=FF0000> « »</font> Moverse entre Campos | <font color=FF0000> F12 </font> Cancelar Pase de Traslado | <font color=FF0000> ENTER </font> Imprimir Pase de Traslado | <font color=FF0000> ESC </font> Limpiar Campos  |<font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Siguiente ventana</html>");
    }//GEN-LAST:event_jcmbox_rutaFocusGained

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        jntxt_operador.setBackground(new Color(192,190,207));
        jntxt_operador.requestFocus();
    }//GEN-LAST:event_formFocusGained

    private void jcmbox_motivoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbox_motivoFocusLost
        jcmbox_motivo.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jcmbox_motivoFocusLost

    private void jcmbox_motivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbox_motivoKeyPressed
        if(evt.getKeyCode() == evt.VK_RIGHT){
            jntxt_operador.setBackground(new Color(192,190,207));
            jntxt_operador.requestFocus();
            jlbl_barraEstado.setText("<html>  <font color=FF0000> F4</font> Salir de Pases de Traslado | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Campos | <font color=FF0000> F12 </font> Cancelar Pase de Traslado | <font color=FF0000> ENTER </font> Imprimir Pase de Traslado | <font color=FF0000> ESC </font> Limpiar Campos  |<font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Siguiente ventana</html>");
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT){
            jcmbox_ruta.setBackground(new Color(192,190,207));
            jcmbox_ruta.requestFocus();
        }
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        evento(evt);
    }//GEN-LAST:event_jcmbox_motivoKeyPressed

    private void jcmbox_rutaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmbox_rutaFocusLost
        jcmbox_ruta.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jcmbox_rutaFocusLost

    private void jcmbox_rutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbox_rutaKeyPressed
        jlbl_barraEstado.setText("<html>  <font color=FF0000> F4</font> Salir de Pases de Traslado | <font color=FF0000> « »</font> Moverse entre Campos | <font color=FF0000> F12 </font> Cancelar Pase de Traslado | <font color=FF0000> ENTER </font> Imprimir Pase de Traslado | <font color=FF0000> ESC </font> Limpiar Campos |<font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Siguiente ventana</html>");
        if(evt.getKeyCode() == evt.VK_RIGHT){
            jcmbox_motivo.setBackground(new Color(192,190,207));
            jcmbox_motivo.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_LEFT){
            jntxt_autobus.setBackground(new Color(192,190,207));
            jntxt_autobus.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        
        evento(evt);
    }//GEN-LAST:event_jcmbox_rutaKeyPressed

    private void jntxt_operadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jntxt_operadorKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN){
            jntxt_autobus.setBackground(new Color(192,190,207));
            jntxt_autobus.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_UP){
            jcmbox_motivo.setBackground(new Color(192,190,207));
            jcmbox_motivo.requestFocus();
        }
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        
        evento(evt);
    }//GEN-LAST:event_jntxt_operadorKeyPressed

    private void jntxt_operadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jntxt_operadorFocusLost
        jntxt_operador.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jntxt_operadorFocusLost

    private void jntxt_autobusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jntxt_autobusKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN){
            jcmbox_ruta.setBackground(new Color(192,190,207));
            jcmbox_ruta.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_UP){
            jntxt_operador.setBackground(new Color(192,190,207));
            jntxt_operador.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        evento(evt);
    }//GEN-LAST:event_jntxt_autobusKeyPressed

    private void jntxt_autobusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jntxt_autobusFocusLost
        jntxt_autobus.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jntxt_autobusFocusLost

    private void evento(KeyEvent evt) {
        //Metodo para los eventos
        if(evt.getKeyCode() == evt.VK_ESCAPE){
            jntxt_autobus.setText("");
            jntxt_operador.setText("");
            jcmbox_motivo.setSelectedIndex(0);
            jcmbox_ruta.setSelectedIndex(0);
            jntxt_operador.setBackground(new Color(192,190,207));
            jntxt_operador.requestFocus();
        }
            
        if(evt.getKeyCode() == evt.VK_ENTER){
            if(jntxt_operador.getText().length() <= 0||jntxt_autobus.getText().length() <= 0) {
                JOptionPane.showMessageDialog( this, "Los campos Clave de Operador, Número Económico de Autobús, Ruta y Motivo son obligatorios. Verifique nuevamente",
                       "TMS - Pase de Traslado", JOptionPane.ERROR_MESSAGE ); 
                jntxt_operador.setBackground(new Color(192,190,207));
                jntxt_operador.requestFocus();
            }
            
            else{
                //Mandar a impresion
                if(!validarDatos(jntxt_operador.getText(), jntxt_autobus.getText())) {
                    JOptionPane.showMessageDialog( this, "La clave del operador y/o el número económico del autobús no existe(n). Por favor verifique e intente nuevamente",
                       "TMS - Pase de Traslado", JOptionPane.ERROR_MESSAGE ); 
                    jntxt_operador.setBackground(new Color(192,190,207));
                    jntxt_operador.requestFocus();
                }
                else{
                    
                        PcInfo pc = new PcInfo();
                        boolean existeimp = false;
                        PrintService impresoraEncontrada = null;
                        Vector vimp = (Vector) cosa.buscarImpresoraFacturas(pc.getHostName().toString().toUpperCase());
                        if(vimp.size()==0) {
                              //enviar mensaje de error indicando que no hay impresoras para facturas configuradas en la caja
                              JOptionPane.showMessageDialog( this, "No hay impresoras configuradas para esta caja. Contacte al administrador del sistema",
                                                "TMS - Pases de Traslado", JOptionPane.ERROR_MESSAGE ); 
                              jntxt_operador.setBackground(new Color(192,190,207));
                              jntxt_operador.requestFocus();
                        }
                        else
                        {
                             String nomImpre = vimp.get(0).toString();
                             nomImpre = nomImpre.replace('[', ' ');
                             nomImpre = nomImpre.replace(']', ' ');
                             nomImpre = nomImpre.trim();
                             System.out.println("nomImpre " + nomImpre);

                             PrintService service[] = PrinterJob.lookupPrintServices();
                             System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
                             for(int i=0; i<service.length;i++)
                             {
                                System.out.println("        "+service[i]. getName());
                                if(service[i].getName().toUpperCase().equals(nomImpre.toUpperCase()))
                                {
                                    existeimp = true;
                                    impresoraEncontrada = service[i];
                                }
                             }
                        }

                       if(!existeimp) {
                                //enviar mensaje de error indicando que la impresoras para facturas no esta configurada en Windows  
                                JOptionPane.showMessageDialog( this, "La impresora no esta configurada en Windows. Contacte al administrador del sistema",
                                 "TMS - Pases de Traslado", JOptionPane.ERROR_MESSAGE ); 
                                jntxt_operador.setBackground(new Color(192,190,207));
                                jntxt_operador.requestFocus();
                       }
                       else{
                            //VERIFICAR SESION ABIERTA
                           System.out.println(impresoraEncontrada);
                           Vector vestado= (Vector) cosa.buscarEstadoSesion(Long.valueOf(datosIniciales.get(3).toString()));
                            String estadoses = vestado.get(0).toString();
                             if(estadoses.equals("CERRADA")){
                                 JOptionPane.showMessageDialog( this, "¡La sesion es invaida!El Sistema se cerrará automáticamente",
                                            "TMS - Pases de Traslado", JOptionPane.WARNING_MESSAGE ); 
                                 System.exit(0);
                             }
                            //INSERCION EN TABLA    
                        System.out.println("rutitas "+rutitas);
                            System.out.println("((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(1).toString() "+((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(1).toString());
                        System.out.println(((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(2).toString());
                            String respuesta = cosa.insertarPasesTraslado(String.valueOf(jntxt_operador.getText()), String.valueOf(jntxt_autobus.getText()), ((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(1).toString(), String.valueOf(jcmbox_motivo.getItemAt(jcmbox_motivo.getSelectedIndex())), usuario, ((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(2).toString());
                            System.out.println("respuesta "+respuesta);
                            if(!respuesta.equals("Algo no salio bien")) {
                                //Impresion
                                System.out.println("Impresion!!");
                                Vector ruta = new Vector();
                                ruta = cosa.buscarDatosRuta(String.valueOf(((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(1).toString()));
                                imprimirFactura(String.valueOf(jntxt_operador.getText()), String.valueOf(jntxt_autobus.getText()), ruta, String.valueOf(jcmbox_motivo.getItemAt(jcmbox_motivo.getSelectedIndex())), usuario, respuesta, impresoraEncontrada, ((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(2).toString());
                                JOptionPane.showMessageDialog( this, "Tome el Pase de Traslado de la impresora ",
                                    "TMS - Pase de Traslado", JOptionPane.WARNING_MESSAGE ); 
                                int result = JOptionPane.showOptionDialog(this, "¿El Pase de Traslado es correcto?",
                                    "TMS - Pase de Traslado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                while(result == JOptionPane.NO_OPTION) {
                                    imprimirFactura(String.valueOf(jntxt_operador.getText()), String.valueOf(jntxt_autobus.getText()), ruta, String.valueOf(jcmbox_motivo.getItemAt(jcmbox_motivo.getSelectedIndex())), usuario, respuesta, impresoraEncontrada, ((Vector)rutitas.get(jcmbox_ruta.getSelectedIndex())).get(2).toString());
                                    JOptionPane.showMessageDialog( this, "Tome el Pase de Traslado de la impresora ",
                                    "TMS - Pase de Traslado", JOptionPane.WARNING_MESSAGE );

                                    result = JOptionPane.showOptionDialog(this, "¿El Pase de Traslado es correcto?",
                                    "TMS - Pase de Traslado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }
                                
                                jntxt_autobus.setText("");
                                jntxt_operador.setText("");
                                jcmbox_motivo.setSelectedIndex(0);
                                jcmbox_ruta.setSelectedIndex(0);
                                jntxt_operador.requestFocus();
                                jntxt_operador.setBackground(new Color(192,190,207));
                                jntxt_operador.requestFocus();
                                
                            }
                            else {
                                JOptionPane.showMessageDialog( this, "En este momento su solicitud no puede ser procesada. Intente mas tarde",
                                 "TMS - Pase de Traslado", JOptionPane.ERROR_MESSAGE ); 
                                jntxt_operador.setBackground(new Color(192,190,207));
                                jntxt_operador.requestFocus();
                            }
                       }
                }
            }
        }
        
        if(evt.getKeyCode() == evt.VK_F12) {
            JDLG_Cancelar cancelar = new JDLG_Cancelar(null, true, usuario, cosa);
            cancelar.setLocationRelativeTo(this);
            cancelar.setModal(true);
            cancelar.setSize(400,110);
            cancelar.setVisible(true); 
            jntxt_operador.setBackground(new Color(192,190,207));
            jntxt_operador.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_F4) {
            int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Pases de Traslado?",
                    "TMS - Pase de Traslado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(result == JOptionPane.YES_OPTION)
                this.dispose();
        }
    }

    private boolean validarDatos(String op, String aut) {
        if(cosa.getValidarOpeador(op) && cosa.getValidarAutobus(aut))
            return true;
        return false;
    }

    private void imprimirFactura(String op, String aut, Vector ruta, String string1, String usuario, String respuesta, PrintService impresoraEncontrada, String peaje) {
        imprimir_recibo_tarjeta imp = new imprimir_recibo_tarjeta(String.valueOf(jntxt_operador.getText()), String.valueOf(jntxt_autobus.getText()), ruta, String.valueOf(jcmbox_motivo.getItemAt(jcmbox_motivo.getSelectedIndex())), usuario, p_nombre, respuesta, impresoraEncontrada, cosa, peaje);
        if(!imp.ImprimeDatos()) {
            System.out.println("Fallo la impresion!!");
            JOptionPane.showMessageDialog( this, "Falló la Impresión. Verifique e intente nuevamente",
                                 "TMS - Pase de Traslado", JOptionPane.ERROR_MESSAGE ); 
        }
    }
    
     public void setFoco(){
        jntxt_operador.setBackground(new Color(192,190,207));
        jntxt_operador.requestFocus();
    }
    
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jcmbox_motivo;
    private javax.swing.JComboBox jcmbox_ruta;
    private javax.swing.JLabel jlbl_autobus;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_motivo;
    private javax.swing.JLabel jlbl_operador;
    private javax.swing.JLabel jlbl_ruta;
    private javax.swing.JLabel jlbl_titulo;
    private tms_TextFields.JNumberTextField jntxt_autobus;
    private tms_TextFields.JNumberTextField jntxt_operador;
    // End of variables declaration//GEN-END:variables
    
}
