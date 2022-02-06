/*
 * vista_cancelar_pago.java
 *
 * Created on 5 de octubre de 2007, 10:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tms_TextFields.JTextTextField;
import tmsactividadesadicionales.entidad.TmsActAdicionalesTbl;
import tmsactividadesadicionales.entidad.TmsActDatosAdicionalesTbl;
import tmsactividadesadicionales.entidad.TmsAuditoriaTbl;
import tmsactividadesadicionales.entidad.TmsOperadoresTbl;
import tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
 class vista_cancelar_pago extends JDialog{

        private JTextArea jTextArea1 = new JTextArea();
        private JLabel jlbl_mensajeVPI = new JLabel();
        private JLabel jlbl_barraEstadoVPI = new JLabel();
        private JLabel jlbl_motivo = new JLabel("Motivo:");
        private JTextTextField jtxt_motivo = new JTextTextField();
        private ImageIcon imageHelp = new ImageIcon(vista_cancelar_pago.class.getResource("pregunta.gif"));
        private JLabel jLabel2 = new JLabel();
        private int alto = 0;
        private TmsActAdicionalesManagedBean busquedas2;
        private TmsPagosActAdicionalesTbl pgCancelar; 
        private TmsActAdicionalesTbl actividad;
        private Vector funciones2;
        private Vector fauditables2;
        private long idUsuario =0;
        private long idterminal;
         private String idTerminalsub;
        private String nombreEquipo ="";
        private long idSesion;
       
        public vista_cancelar_pago(TmsActAdicionalesManagedBean busq, TmsPagosActAdicionalesTbl pcan, Vector fun, Vector fuadit, long iu, String ne, long it, long pidSesion) {
            this.setTitle("Actividad Adicional Pagada");
            this.setDefaultLookAndFeelDecorated(true);
            this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.setAlwaysOnTop(true);
            this.setModal(true);
            this.busquedas2 = busq;
            this.pgCancelar = pcan;
            this.funciones2 = fun;
            this.fauditables2 = fuadit;
            this.idUsuario = iu;
            this.nombreEquipo = ne;
            this.idterminal = it;
            this.idSesion = pidSesion;
            try {
                jbInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.jtxt_motivo.requestFocus();
        }

        private void jbInit() throws Exception {
            this.setSize(new Dimension(400, 500+alto));
            this.setLayout(null);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
            this.setResizable(false);
            jlbl_barraEstadoVPI.setFont(new java.awt.Font("Tahoma", 1, 12));
            jlbl_barraEstadoVPI.setForeground(new java.awt.Color(153, 153, 153));
            jlbl_barraEstadoVPI.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            jlbl_barraEstadoVPI.setBounds(new Rectangle(0, 445+alto, 400, 25));
            jlbl_barraEstadoVPI.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
            jlbl_barraEstadoVPI.setHorizontalAlignment(JTextField.CENTER);
            jtxt_motivo.setBounds(new Rectangle(65, 365, 250, 20 ));
            jtxt_motivo.setFocusTraversalKeysEnabled(false);
            jlbl_motivo.setFont(new Font("Arial", 1, 12));
            jlbl_motivo.setBounds(new Rectangle(10, 365, 50, 20 ));
            jTextArea1.setBounds(new Rectangle(10, 10, 370, 300+alto));
            jTextArea1.setEditable(false);
            jTextArea1.setFocusable(false);       
            jTextArea1.setFont(new Font("Dialog", 0, 12));
            jTextArea1.setSize(new Dimension(375, 335+alto));
            jlbl_mensajeVPI.setText("¿Seguro que desea cancelar el pago?");
            jlbl_mensajeVPI.setBounds(new Rectangle(105, 415+alto, 250, 15));
            jlbl_mensajeVPI.setFont(new Font("Arial", 1, 12));
            jtxt_motivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1_KeyPressed(evt);
            }
            });
             jLabel2.setBounds(new Rectangle(60, 400+alto, 35, 45));
             jLabel2.setIcon(imageHelp);
             jTextArea1.append(" REF.: "+pgCancelar.getReferenciaPagoActAdicional()+ "\n\n");
             actividad = pgCancelar.getTipoActividadAdicionalId();
             jTextArea1.append(" Actividad            :   "+actividad.getAccionId().getAccion() + "\n");
             jTextArea1.append(" Servicio             :   "+pgCancelar.getServicioId().getServicioNombre()+ "\n");
             Collection<TmsActDatosAdicionalesTbl> coleccion = pgCancelar.getTipoActividadAdicionalId().getTmsActDatosAdicionalesTblCollection();
             Iterator ipre = coleccion.iterator();
                   while(ipre.hasNext()){
                       TmsActDatosAdicionalesTbl adicional = (TmsActDatosAdicionalesTbl)ipre.next();
                       if(adicional.getHabilitado().equals("S"))
                       {
                           String valor = "";
                           String nombreAdicional = adicional.getDatoAdicionalId().getDatoNombre();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL1"))valor = pgCancelar.getAdicional1();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL2"))valor = pgCancelar.getAdicional2();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL3"))valor = pgCancelar.getAdicional3();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL4"))valor = pgCancelar.getAdicional4();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL5"))valor = pgCancelar.getAdicional5();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL6"))valor = pgCancelar.getAdicional16();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL7"))valor = pgCancelar.getAdicional7();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL8"))valor = pgCancelar.getAdicional8();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL9"))valor = pgCancelar.getAdicional9();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL11"))valor = pgCancelar.getAdicional10();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL11"))valor = pgCancelar.getAdicional11();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL12"))valor = pgCancelar.getAdicional12();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL13"))valor = pgCancelar.getAdicional3();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL14"))valor = pgCancelar.getAdicional4();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL15"))valor = pgCancelar.getAdicional5();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL16"))valor = pgCancelar.getAdicional6();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL17"))valor = pgCancelar.getAdicional7();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL18"))valor = pgCancelar.getAdicional8();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL19"))valor = pgCancelar.getAdicional9();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL20"))valor = pgCancelar.getAdicional20();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL21"))valor = pgCancelar.getAdicional21();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL22"))valor = pgCancelar.getAdicional22();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL23"))valor = pgCancelar.getAdicional23();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL24"))valor = pgCancelar.getAdicional24();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL24"))valor = pgCancelar.getAdicional25();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL26"))valor = pgCancelar.getAdicional26();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL27"))valor = pgCancelar.getAdicional27();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL28"))valor = pgCancelar.getAdicional28();
                           if(adicional.getGuardarValorEn().equals("ADICIONAL29"))valor = pgCancelar.getAdicional29();
                           jTextArea1.append(" "+nombreAdicional+"      :   "+valor+ "\n");
                       }
                      }

             
             TmsOperadoresTbl operador = busquedas2.operadoresTblFacadeRemote.find(BigDecimal.valueOf(pgCancelar.getOperadorId().longValue()));
             String nombreoperador = "";
             if(operador.getOperadorNombreMedio()!=null && operador.getOperadorNombreMedio().indexOf("OPERADOR")==-1)
                nombreoperador =  operador.getOperadorNombre() + " "+operador.getOperadorNombreMedio() +" " + operador.getOperadorApellido();//System.out.println("Sin nombre medio..");
              else
                nombreoperador = operador.getOperadorNombre() + " " + operador.getOperadorApellido();
             
             jTextArea1.append(" Operador           :   "+operador.getClaveOperador()+"-"+nombreoperador+ "\n");
             jTextArea1.append(" Sueldo               :   "+pgCancelar.getMontoPago()+ "\n");
             jTextArea1.append(" Retencion         :   "+pgCancelar.getRetencion()+ "\n");
             float total = (Float.valueOf(pgCancelar.getMontoPago().toString())) - (Float.valueOf(pgCancelar.getRetencion().toString()));
             long total2 = Float.valueOf(total).longValue();
             jTextArea1.append(" Total                  :   "+total+ "\n");
             jTextArea1.append("              ( "+new cantidad_a_letras().toLetras((long) total2) + " Pesos )\n");
             Vector vusuario = (Vector)busquedas2.variosFacadeRemote.buscarUsuarioPorId(pgCancelar.getRecaudadorId().longValue());
             String nombre_recaudador = vusuario.get(0).toString();

             jTextArea1.append(" Recaudador      :   "+nombre_recaudador+ "\n");
             SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
             SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
             jTextArea1.append(" Fecha de Recaudacion:  "+formatf.format(pgCancelar.getFechaHoraRecaudacion().getTime())  + "\n");
             jTextArea1.append(" Hora de Recaudacion  : "+ formath.format(pgCancelar.getFechaHoraRecaudacion().getTime())+ "\n\n");
             jTextArea1.append(" Firma: ________________________ \n");
             jLabel2.setFocusable(false);
             jlbl_mensajeVPI.setFocusable(false);
             this.add(jLabel2, null);
             this.add(jTextArea1, null);
             this.add(jtxt_motivo, null);
             this.add(jlbl_motivo, null);
             this.add(jlbl_barraEstadoVPI,null);
             this.add(jlbl_mensajeVPI, null);
             this.jtxt_motivo.requestFocus();
        }


   
    //--------------------Eventos del teclado --------------//
      private void jTextArea1_KeyPressed(java.awt.event.KeyEvent evt) {                                        
      if(evt.getKeyCode() == evt.VK_ESCAPE)
          this.dispose();

      if(evt.getKeyCode() == evt.VK_ENTER)
      {
         if(jtxt_motivo.getText().equals(""))
           new jdlg_advertencia("¡Debe escribir un motivo! ","","Faltan datos").setVisible(true);
         else
         {
             Vector vestado= (Vector)busquedas2.cajasTblFacadeRemote.buscarEstadoSesion(idSesion);
             String estado = vestado.get(0).toString();
             if(estado.equals("CERRADA")){
                 new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
                 System.exit(0);
             }             
            Vector TER = (Vector)busquedas2.variosFacadeRemote.queryBuscaIdTerminal();
            idTerminalsub = TER.get(0).toString();
            String te = idTerminalsub;
            if(idTerminalsub.length()<3)
            {
               for(int i=idTerminalsub.length(); i<3;i++)
                   te = te+"0";
            }
            if(idTerminalsub.length()>=3)
             te = idTerminalsub.substring(0,2);
                
            idTerminalsub = te;
             
            Timestamp fecha_servidor = null; 
            Vector x = (Vector) busquedas2.variosFacadeRemote.fechaServidor();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
            pgCancelar.setEstadoPago("C");
            pgCancelar.setComentarios(jtxt_motivo.getText());
            pgCancelar.setAutorizadoPor(BigInteger.valueOf(idUsuario));
            pgCancelar.setCiudadRecaudacionId(BigInteger.valueOf(idterminal));
            pgCancelar.setUltimaActualizacionPor(BigInteger.valueOf(idUsuario));
            pgCancelar.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
            busquedas2.pagosActAdicionalesTblFacadeRemote.edit(pgCancelar);
           
           //AGREGA EL REGISTRO DE AUDITORIA
           int indexfun = funciones2.indexOf("6004");
           if(indexfun>=0)
           {
            String auditable = fauditables2.get(indexfun).toString().toUpperCase();
             if(auditable.equals("S") || auditable.equals("Y"))
             {
               TmsAuditoriaTbl auditoria = new TmsAuditoriaTbl();
               auditoria.setUsuarioId(BigInteger.valueOf(idUsuario));
               auditoria.setNombreEquipo(nombreEquipo);
               auditoria.setFuncionNumero(BigInteger.valueOf((long)6004));
               auditoria.setFecha(fecha_servidor);
               auditoria.setEstadoProceso("EXITO");
               auditoria.setDescripcionProceso("Se Cancelo la Actividad Adicional de "+actividad.getAccionId().getAccion());
               busquedas2.auditoriaTblFacadeRemote.create(auditoria, idTerminalsub);
            }
           }
          this.dispose();
          new jdlg_informacion("¡El pago fue cancelado satisfactoriamente!  ",""," Cancelacion de pago completada").setVisible(true);
         }
      }
      
    } 
    
}   