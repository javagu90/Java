    /*
 * JIFModificaTarViaje.java
 *
 * Created on 10 de diciembre de 2007, 10:42 AM
 */

package tmsmodificatarjetaviaje;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import tms_encriptacion.EncriptarMD5;
import tmsmodiftarviaje.entidad.TmsTarjetasViajeTbl;
import tmsmodiftarviaje.solicitud.UsuarioNoEncontradoException;

/**
 *
 * @author  vgonzalez
 */
public class JIFModificaTarViaje extends javax.swing.JInternalFrame {//javax.swing.JFrame { //
    
    /** Creates new form JIFModificaTarViaje */
    public JIFModificaTarViaje(Vector pDatosIniciales) {
        this.datosIniciales = pDatosIniciales;
        this.setIdmenu(Long.valueOf(datosIniciales.get(4).toString()));
        this.setIdusuario(Long.valueOf(datosIniciales.get(0).toString()));
        this.nombre_recaudador = datosIniciales.get(2).toString();
        this.setSesionId(datosIniciales.get(3).toString());
        initComponents();
	this.setTitle("Modificar Tarjeta Viaje");
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        jtxt_operador.setFocusTraversalKeysEnabled(false);
        jtxt_autobus.setFocusTraversalKeysEnabled(false);
        jtxt_tarjeta.setFocusTraversalKeysEnabled(false);
        jtxt_operador.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jtxt_autobus.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jtxt_tarjeta.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        this.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>F4 </font> Salir de la aplicaci?n | <font color=FF0000> Enter </font> Buscar Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");        
        limpiar();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlbl_corrida = new javax.swing.JLabel();
        jlbl_origen = new javax.swing.JLabel();
        jlbl_destino = new javax.swing.JLabel();
        jlbl_fecha = new javax.swing.JLabel();
        jlbl_hora = new javax.swing.JLabel();
        jtxt_tarjeta = new tms_TextFields.JTextTextField();
        jtxt_autobus = new tms_TextFields.JTextTextField();
        jtxt_operador = new tms_TextFields.JTextTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxt_estado = new javax.swing.JTextField();
        jlbl_barraEstado = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Modificar Tarjeta Viaje");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar Tarjeta de Viaje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Folio de Tarjeta:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Corrida:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Origen:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Destino:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Fecha:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Hora:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Autobus:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Operador:");

        jlbl_corrida.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_corrida.setText("jLabel9");

        jlbl_origen.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_origen.setText("jLabel10");

        jlbl_destino.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_destino.setText("jLabel11");

        jlbl_fecha.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_fecha.setText("jLabel12");

        jlbl_hora.setFont(new java.awt.Font("Tahoma", 0, 10));
        jlbl_hora.setText("jLabel13");

        jtxt_tarjeta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_tarjetaFocusGained(evt);
            }
        });
        jtxt_tarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_tarjetaKeyReleased(evt);
            }
        });

        jtxt_autobus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_autobusKeyReleased(evt);
            }
        });

        jtxt_operador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_operadorKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("Estado:");

        jtxt_estado.setEditable(false);
        jtxt_estado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_estado.setFocusable(false);

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
                        .add(jtxt_tarjeta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 286, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel3)
                            .add(jLabel4)
                            .add(jLabel5)
                            .add(jLabel6)
                            .add(jLabel7)
                            .add(jLabel8)
                            .add(jLabel9))
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jlbl_destino, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jlbl_origen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jlbl_corrida, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .add(jlbl_fecha, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jlbl_hora, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jtxt_operador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jtxt_autobus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                            .add(jtxt_estado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))))
                .add(220, 220, 220))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jtxt_tarjeta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(28, 28, 28)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jlbl_corrida))
                .add(21, 21, 21)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jlbl_origen))
                .add(23, 23, 23)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jlbl_destino))
                .add(26, 26, 26)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jlbl_fecha))
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jlbl_hora))
                .add(27, 27, 27)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel7)
                        .add(27, 27, 27)
                        .add(jLabel8))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jtxt_autobus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(23, 23, 23)
                        .add(jtxt_operador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 20, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jtxt_estado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9)))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel9");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jlbl_barraEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_barraEstadoKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Rev19.05.2014");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(541, Short.MAX_VALUE)
                .add(jLabel10)
                .add(18, 18, 18))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLabel10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlbl_barraEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlbl_barraEstadoKeyPressed
          if(evt.getKeyCode() == evt.VK_ESCAPE)
              limpiar();
    
          if(evt.getKeyCode() == evt.VK_F7)
            abrir();

          if(evt.getKeyCode() == evt.VK_F8)
            pendiente();
          
    }//GEN-LAST:event_jlbl_barraEstadoKeyPressed

    private void jtxt_operadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_operadorKeyReleased
        if(evt.getKeyCode() == evt.VK_LEFT || evt.getKeyCode() == evt.VK_RIGHT || evt.getKeyCode() == evt.VK_UP || evt.getKeyCode() == evt.VK_DOWN)
        {
            jtxt_autobus.selectAll();
            jtxt_autobus.requestFocus();
        }

        if(evt.getKeyCode() == evt.VK_ESCAPE)
            limpiar();

        if(evt.getKeyCode() == evt.VK_F8)
            pendiente();

        if(evt.getKeyCode() == evt.VK_F9)
            cancelar();

        if(evt.getKeyCode() == evt.VK_F12)
            guardar();  

    }//GEN-LAST:event_jtxt_operadorKeyReleased

    private void jtxt_autobusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_autobusKeyReleased
        if(evt.getKeyCode() == evt.VK_LEFT || evt.getKeyCode() == evt.VK_RIGHT || evt.getKeyCode() == evt.VK_UP || evt.getKeyCode() == evt.VK_DOWN) {
            jtxt_operador.selectAll();
            jtxt_operador.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            limpiar();

        if(evt.getKeyCode() == evt.VK_F8)
            pendiente();
        
        if(evt.getKeyCode() == evt.VK_F9)
            cancelar();
        
        if(evt.getKeyCode() == evt.VK_F12)
            guardar();

    }//GEN-LAST:event_jtxt_autobusKeyReleased

    private void jtxt_tarjetaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_tarjetaFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000>F4 </font> Salir de la aplicaci?n | <font color=FF0000> Enter </font> Buscar Tarjeta | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");        
    }//GEN-LAST:event_jtxt_tarjetaFocusGained

    private void jtxt_tarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_tarjetaKeyReleased
     if(evt.getKeyCode() == evt.VK_ENTER)
     {
//          Vector vvEstadoTarjeta = (Vector)busquedas.tarjetasViajeTblFacadeRemote.buscaEstadoTajeta("ABIERTA");
//          if(vvEstadoTarjeta.size()==0)
//          {
//              new jdlg_error("?El estado ABIERTA de la tarjeta de viaje, esta mal configurado! ","Favor de contactar al Administrador del Sistema","Error de Configuracion").setVisible(true);
//              return;
//          }
//       Vector vEstadoTarjeta = (Vector)vvEstadoTarjeta.get(0);
//       System.out.println("El id del estado ABIERTA es: "+vEstadoTarjeta.get(0).toString());
//       BigInteger edo = BigInteger.valueOf(Long.valueOf(vEstadoTarjeta.get(0).toString()));
          BigInteger edo = BigInteger.valueOf(Long.valueOf("0"));
       tarjeta = null;
       tarjeta = busquedas.tarjetasViajeTblFacadeRemote.buscaTarjetaPorFolio(jtxt_tarjeta.getText(),edo);
       if(tarjeta==null)
       {
           new jdlg_advertencia("?No existe una tarjeta con folio "+jtxt_tarjeta.getText()+" !","","").setVisible(true);
           jtxt_tarjeta.setText("");
           jtxt_tarjeta.requestFocus();
           return;
       }
      Vector vvCorrida = (Vector)busquedas.tarjetasViajeTblFacadeRemote.buscaCorrida(tarjeta.getCorridaId().longValue());
      if(vvCorrida.size()==0)
      {
          new jdlg_error("?La corrida de la tarjeta con folio "+jtxt_tarjeta.getText()+" esta mal configurada! ","Favor de contctar al Administrador del Sistema","").setVisible(true);
          return;
      }
      Vector vvEstadoTarjeta = (Vector)busquedas.tarjetasViajeTblFacadeRemote.buscaNombreEstadoTajeta(tarjeta.getEstadoTarjetaId().longValue());
      if(vvEstadoTarjeta.size()==0)
      {
          new jdlg_error("?El estado de la tarjeta de viaje, esta mal configurado! ","Favor de contactar al Administrador del Sistema","Error de Configuracion").setVisible(true);
          return;
      }
      Vector v = (Vector)vvEstadoTarjeta.get(0);
      String estadoTarjeta = v.get(0).toString();
        jtxt_tarjeta.setEnabled(false);
        jtxt_autobus.setEnabled(true);
        jtxt_operador.setEnabled(true);
      Vector vCorrida = (Vector)vvCorrida.get(0);
        jlbl_corrida.setText(vCorrida.get(0).toString());
        jlbl_origen.setText(vCorrida.get(1).toString());
        jlbl_destino.setText(vCorrida.get(2).toString());
        jlbl_fecha.setText(vCorrida.get(3).toString());
        jlbl_hora.setText(vCorrida.get(4).toString());
        jtxt_autobus.setText(tarjeta.getAutobus());
        jtxt_operador.setText(tarjeta.getOperador());
        jtxt_autobus.selectAll();
        jtxt_estado.setText(estadoTarjeta);
        jtxt_autobus.requestFocus();
       
      if(estadoTarjeta.equals("ABIERTA") || estadoTarjeta.equals("CONFIRMADA"))
      {
          modificar = true;
          cancelada = true;
          pendiente = true;
          abierta = false;
          jlbl_barraEstado.setText("<html>  <font color=FF0000>ESC</font> Buscar Tarjeta de Viaje | <font color=FF0000> F8 </font> Poner en estado de Pendiente la Tarjeta | <font color=FF0000> F9 </font> Cancelar Tarjeta | <font color=FF0000> F12 </font> Guardar Tarjeta </html>");
      }
      if(estadoTarjeta.equals("PENDIENTE"))
      {
          modificar = false;
          cancelada = false;
          pendiente = false;
          abierta = true;
        jtxt_tarjeta.setEnabled(false);
        jtxt_autobus.setEnabled(false);
        jtxt_operador.setEnabled(false);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESC</font> Buscar Tarjeta de Viaje | <font color=FF0000> F7 </font> Poner en estado de Abierta la Tarjeta </html>");
        jlbl_barraEstado.requestFocus();
      }       
      if(estadoTarjeta.equals("CANCELADA"))
      {
          modificar = false;
          cancelada = false;
          pendiente = false;
          abierta = false;
        jtxt_tarjeta.setEnabled(true);
        jtxt_autobus.setEnabled(false);
        jtxt_operador.setEnabled(false);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESC</font> Buscar Tarjeta de Viaje </html>");
        jlbl_barraEstado.requestFocus();
      } 
      if(estadoTarjeta.equals("RECAUDADA"))
      {
          modificar = false;
          cancelada = false;
          pendiente = false;
          abierta = false;
        jtxt_tarjeta.setEnabled(true);
        jtxt_autobus.setEnabled(false);
        jtxt_operador.setEnabled(false);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESC</font> Buscar Tarjeta de Viaje </html>");
        jlbl_barraEstado.requestFocus();
      } 
        
      
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
     
     if(evt.getKeyCode() == evt.VK_F4)
     {
              jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de salida", "?Seguro que desea salir de Modificar Tarjeta?");
              psn.setVisible(true);
              if(respuestaSN)
                this.dispose();
              else
                  return;
     }

    }//GEN-LAST:event_jtxt_tarjetaKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        jtxt_tarjeta.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    public void setFoco(){
        jtxt_tarjeta.requestFocusInWindow();
    }    
    private void limpiar()
    {
        jlbl_corrida.setText("");
        jlbl_origen.setText("");
        jlbl_destino.setText("");
        jlbl_fecha.setText("");
        jlbl_hora.setText("");
        jtxt_autobus.setText("");
        jtxt_operador.setText("");
        jtxt_tarjeta.setText("");
        jtxt_autobus.setEnabled(false);
        jtxt_operador.setEnabled(false);
        jtxt_tarjeta.setEnabled(true);
        jtxt_estado.setText("");
        jtxt_tarjeta.requestFocus();
    }


    private void guardar() {
           if(!modificar)
             return;

          if(jtxt_autobus.getText().equals("") || jtxt_operador.getText().equals(""))
          {
              new jdlg_error("?Debes introducir el Autobus y el Operador!","","Faltan datos").setVisible(true);
              return;
          }
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de guardar", "?Seguro que desea guardar los cambio a la tarjeta de viaje?");
          psn.setVisible(true);
          if(!respuestaSN)
             return;
          Vector x = (Vector) busquedas.tarjetasViajeTblFacadeRemote.fechaServidor();
          fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
          tarjeta.setAutobus(jtxt_autobus.getText());
          tarjeta.setOperador(jtxt_operador.getText());
          Vector NESP = (Vector)busquedas.tarjetasViajeTblFacadeRemote.queryBuscaNombreEsquema();
          String nombreEsquema = NESP.get(0).toString();          
          tarjeta.setReplicacionOrigen(nombreEsquema);
          tarjeta.setUltimaActualizacionPor(BigInteger.valueOf(getIdusuario()));
          tarjeta.setUltimaFechaActualizacion(fecha_servidor);
          busquedas.tarjetasViajeTblFacadeRemote.edit(tarjeta);
          
          new jdlg_informacion("?La tarjeta se actualizo correctamente!","","Tarjeta actualizada").setVisible(true);
          limpiar();
    }
     
    private void cancelar() {
            if(!cancelada)
             return;
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de cancelacion", "?Seguro que desea cancelar esta tarjeta de viaje?");
          psn.setVisible(true);
          if(!respuestaSN)
             return;
          Vector vvEstadoTarjeta = (Vector)busquedas.tarjetasViajeTblFacadeRemote.buscaEstadoTajeta("CANCELADA");
          if(vvEstadoTarjeta.size()==0)
          {
              new jdlg_error("?El estado CANCELADA de la tarjeta de viaje, esta mal configurado! ","Favor de contactar al Administrador del Sistema","Error de Configuracion").setVisible(true);
              return;
          }
         Vector x = (Vector) busquedas.tarjetasViajeTblFacadeRemote.fechaServidor();
         fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
          Vector vEstadoTarjeta = (Vector)vvEstadoTarjeta.get(0);
          Vector NESP = (Vector)busquedas.tarjetasViajeTblFacadeRemote.queryBuscaNombreEsquema();
          String nombreEsquema = NESP.get(0).toString();          
          tarjeta.setEstadoTarjetaId(BigInteger.valueOf(Long.valueOf(vEstadoTarjeta.get(0).toString())));
          tarjeta.setReplicacionOrigen(nombreEsquema);
          tarjeta.setUltimaActualizacionPor(BigInteger.valueOf(getIdusuario()));
          tarjeta.setUltimaFechaActualizacion(fecha_servidor);
          busquedas.tarjetasViajeTblFacadeRemote.edit(tarjeta);
          new jdlg_informacion("?La tarjeta se cancelo correctamente!","","Tarjeta cancelada").setVisible(true);
          limpiar();
    }   
    
     private void abrir() {
         if(!abierta)
             return;
        String respuesta = busquedas.tarjetasViajeTblFacadeRemote.buscaFuncion(datosIniciales.get(1).toString(),"6026"); 
        if(!respuesta.equals("encontrado"))
        {
          if(!validarDatosSupervisor("6026"))
            return;
        }
         
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de cambio de estado", "?Seguro que desea poner en estado Abierta esta tarjeta de viaje?");
          psn.setVisible(true);
          if(!respuestaSN)
             return;
          Vector vvEstadoTarjeta = (Vector)busquedas.tarjetasViajeTblFacadeRemote.buscaEstadoTajeta("ABIERTA");
          if(vvEstadoTarjeta.size()==0)
          {
              new jdlg_error("?El estado ABIERTA de la tarjeta de viaje, esta mal configurado! ","Favor de contactar al Administrador del Sistema","Error de Configuracion").setVisible(true);
              return;
          }
         Vector x = (Vector) busquedas.tarjetasViajeTblFacadeRemote.fechaServidor();
         fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
          Vector vEstadoTarjeta = (Vector)vvEstadoTarjeta.get(0);
          Vector NESP = (Vector)busquedas.tarjetasViajeTblFacadeRemote.queryBuscaNombreEsquema();
          String nombreEsquema = NESP.get(0).toString();          
          tarjeta.setEstadoTarjetaId(BigInteger.valueOf(Long.valueOf(vEstadoTarjeta.get(0).toString())));
          tarjeta.setReplicacionOrigen(nombreEsquema);
          tarjeta.setUltimaActualizacionPor(BigInteger.valueOf(getIdusuario()));
          tarjeta.setUltimaFechaActualizacion(fecha_servidor);
          busquedas.tarjetasViajeTblFacadeRemote.edit(tarjeta);
          new jdlg_informacion("?La tarjeta se cancelo correctamente!","","Tarjeta cancelada").setVisible(true);
          limpiar();
         
     }
    
    private void pendiente() {
         if(!pendiente)
             return;
        String respuesta = busquedas.tarjetasViajeTblFacadeRemote.buscaFuncion(datosIniciales.get(1).toString(),"6025"); 
        if(!respuesta.equals("encontrado"))
        {
          if(!validarDatosSupervisor("6025"))
            return;
        }         
          jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de cambio de estado", "?Seguro que desea poner en estado Pendiente esta tarjeta de viaje?");
          psn.setVisible(true);
          if(!respuestaSN)
             return;
          Vector vvEstadoTarjeta = (Vector)busquedas.tarjetasViajeTblFacadeRemote.buscaEstadoTajeta("PENDIENTE");
          if(vvEstadoTarjeta.size()==0)
          {
              new jdlg_error("?El estado PENDIENTE de la tarjeta de viaje, esta mal configurado! ","Favor de contactar al Administrador del Sistema","Error de Configuracion").setVisible(true);
              return;
          }
         Vector x = (Vector) busquedas.tarjetasViajeTblFacadeRemote.fechaServidor();
         fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
          Vector vEstadoTarjeta = (Vector)vvEstadoTarjeta.get(0);
          Vector NESP = (Vector)busquedas.tarjetasViajeTblFacadeRemote.queryBuscaNombreEsquema();
          String nombreEsquema = NESP.get(0).toString();          
          tarjeta.setEstadoTarjetaId(BigInteger.valueOf(Long.valueOf(vEstadoTarjeta.get(0).toString())));
          tarjeta.setReplicacionOrigen(nombreEsquema);
          tarjeta.setUltimaActualizacionPor(BigInteger.valueOf(getIdusuario()));
          tarjeta.setUltimaFechaActualizacion(fecha_servidor);
          busquedas.tarjetasViajeTblFacadeRemote.edit(tarjeta);
          new jdlg_informacion("?La tarjeta se cancelo correctamente!","","Tarjeta cancelada").setVisible(true);
          limpiar();
         
     }
    
    private void mostrarDialogoSupervisor(String fun) {
        dlgSupervisor = new jdlgDatosSupervisor(true,"TMS - Validar Supervisor         Funcion: "+fun);
        dlgSupervisor.centrarDialogo();
        dlgSupervisor.setVisible(true);
    }
    
private boolean validarDatosSupervisor(String nfuncion) {
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        mostrarDialogoSupervisor(nfuncion);
        String numeroUsr = dlgSupervisor.getNumeroUsuario();
        String claveSuper = dlgSupervisor.getContrasenaUsuario();
        boolean valido = false;
        String respuesta = "";
        if(numeroUsr.equals("nada") && claveSuper.equals("nada"))
            valido = false;
        else
        {
            try{
                respuesta = busquedas.tarjetasViajeTblFacadeRemote.esUsuarioSupervisor(dlgSupervisor.getNumeroUsuario(),pwdEnc.encriptar(dlgSupervisor.getContrasenaUsuario()),nfuncion);
            } catch (UsuarioNoEncontradoException exu) {
                System.out.println(exu.getMessage());
                return false;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
     
            if(respuesta.equals("no encontrado"))
            {
                valido = false;
                new jdlg_error("?Numero de usuario o Contrase?a invalidos!   "," Favor de intentar nuevamente", "Datos incorrector").setVisible(true);
            }
            else
            {
               if(respuesta.equals("invalido"))
               {
                    valido = false;
                    new jdlg_error("?Tu perfil de usuario no te permite realizar esta funcion!   "," Favor de contactar al Administrador del Sistema", "Permiso Denegado").setVisible(true);
               }                
               else
                  valido = true;
            }
        }
       return valido;
    }       
     
    public void setIdusuario(long id) {
        this.idusuario = id;
    }
        
    public long getIdusuario(){
      return   idusuario;
    }

    public long getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(long idmenu) {
        this.idmenu = idmenu;
    }
    
    public long getSesionId() {
        return sesionId;
    }

        public void setSesionId(String sesionId) {
        this.sesionId = Long.valueOf(sesionId);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_corrida;
    private javax.swing.JLabel jlbl_destino;
    private javax.swing.JLabel jlbl_fecha;
    private javax.swing.JLabel jlbl_hora;
    private javax.swing.JLabel jlbl_origen;
    private tms_TextFields.JTextTextField jtxt_autobus;
    private javax.swing.JTextField jtxt_estado;
    private tms_TextFields.JTextTextField jtxt_operador;
    private tms_TextFields.JTextTextField jtxt_tarjeta;
    // End of variables declaration//GEN-END:variables
    private long sesionId = 1;
    private long idusuario = 999;
    private long idmenu = 0;
    private TmsModTarViajeManagedBean busquedas = new TmsModTarViajeManagedBean();
    private TmsTarjetasViajeTbl tarjeta;
    private boolean respuestaSN = true;
    private Vector datosIniciales;
    private String nombre_recaudador;
    private Timestamp fecha_servidor = null;
    private boolean modificar = false;
    private boolean cancelada = false;
    private boolean abierta = false;
    private boolean pendiente = false;    
    private jdlgDatosSupervisor dlgSupervisor;

    ////// CLASES ///////////
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
        jlbl_barraEstado.setHorizontalAlignment( JTextField.LEFT );
        int nletras = pregunta.length();
        this.setSize((nletras * 6) + 80,155);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
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
 
    private KeyEvent eventoTeclado;

    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
}
