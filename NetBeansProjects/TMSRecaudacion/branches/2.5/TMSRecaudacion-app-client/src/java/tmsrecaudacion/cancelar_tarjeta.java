/*
 * cancelar_tarjeta.java
 *
 * Created on 12 de junio de 2007, 06:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsrecaudacion;
import TmsRecaudacion.entidad.TmsRecaudacionTbl;
import TmsRecaudacion.entidad.TmsTarjetasViajeTbl;
import TmsRecaudacion.entidad.TmsUsuariosTbl;
import TmsRecaudacion.solicitud.UsuarioNoEncontradoException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Vector;
import javax.swing.JTextField;
import tms_TextFields.JTextTextField;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import tms_encriptacion.EncriptarMD5;


/**
 *
 * @author vgonzalez
 */
public class cancelar_tarjeta {
    private TmsRecaudaManagedBean busquedas;    
    private String dato="nada";
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private ImageIcon imagen_pregunta = new ImageIcon(cancelar_tarjeta.class.getResource("pregunta.gif"));
    private JButton jbtn_respuesta_si = new JButton();
    private JButton jbtn_respuesta_no = new JButton();
    private JTextTextField jtxt_ncm = new JTextTextField();
    private TmsRecaudacionTbl tarjetaCancelada;
    private long usuario =0;
    private long supervisor = 0;
    private jdlgDatosSupervisor dlgSupervisor;
    private String autorizado="0";
    private TmsTarjetasViajeTbl te;
    private Timestamp fecha_servidor = null;
    private Vector funciones;
    private long idSesion;
    private long corteId;
    private JIFRecaudacion principal;
    
    /** Creates a new instance of cancelar_tarjeta */
    public cancelar_tarjeta(long us,TmsRecaudaManagedBean pbusquedas,Vector pfunciones,long pidSesion, long pcorteId, JIFRecaudacion pprincipal){//(long us,aTMSRecaLogin lg) {
        this.busquedas = pbusquedas;
        this.usuario = us;
        this.funciones = pfunciones;
        this.idSesion = pidSesion;
        this.corteId = pcorteId;
        this.principal = pprincipal;
           int indexfun = funciones.indexOf("6001");
           if(indexfun>=0)
           {
               supervisor = us;
              jdlg_cancelar_PagoNormal cpn = new jdlg_cancelar_PagoNormal();
              cpn.setVisible(true);               
           }
           else
           {
            if(validarDatosSupervisor("6002"))
             {
              supervisor = Long.valueOf(autorizado);   
              jdlg_cancelar_PagoNormal cpn = new jdlg_cancelar_PagoNormal();
              cpn.setVisible(true);
             }
           }

    }
    
    
        
    private void mostrarDialogoSupervisor(String nfuncion) {
        dlgSupervisor = new jdlgDatosSupervisor(true,nfuncion);
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
                respuesta = busquedas.usuariosTblFacadeRemote.esUsuarioSupervisor(dlgSupervisor.getNumeroUsuario(),pwdEnc.encriptar(dlgSupervisor.getContrasenaUsuario()),nfuncion);
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
                new jdlg_error("¡Numero de usuario o Contraseña invalidos!   "," Favor de intentar nuevamente", "Datos incorrector").setVisible(true);
            }
            else
            {
               if(respuesta.equals("invalido"))
               {
                    valido = false;
                    new jdlg_error("¡Tu perfil de usuario no te permite realizar esta funcion!   "," Favor de contactar al Administrador del Sistema", "Permiso Denegado").setVisible(true);
               }                
               else
               {
                  valido = true;
                  autorizado = respuesta;
               }
            }
        }
       return valido;
    }
    
        
    
    /**
     * Inicializa el contexto inicial para el servidor OC4J
     * @return Contexto inicial del OC4J
     * @throws NamingException En caso de que el servidor no se pueda inicializar correctamente
     */
    private static Context getInitialContext() throws NamingException {
        // Get InitialContext for Embedded OC4J
        // The embedded server must be running for lookups to succeed.
        return new InitialContext();
    }


    
class jdlg_cancelar_PagoNormal extends JDialog {

    public jdlg_cancelar_PagoNormal() {
        this.setTitle("Busqueda de Pago de Tarjeta de Viaje");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(333, 128));
        this.getContentPane().setLayout( null );
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
        jLabel1.setText("¿Numero de Folio de la Tarjeta?");
        jLabel1.setBounds(new Rectangle(80, 10, 205, 15));
        jLabel1.setFont(new Font("Arial", 1, 12));
        jLabel2.setBounds(new Rectangle(20, 5, 35, 45));
        jLabel2.setSize(new Dimension(35, 45));
        jLabel2.setIcon(imagen_pregunta);
        jbtn_respuesta_si.setText("Aceptar");
        jbtn_respuesta_si.setBounds(new Rectangle(80, 60, 80, 25));
        jbtn_respuesta_si.setMnemonic('a');
        jbtn_respuesta_si.setFont(new Font("Arial", 1, 12));
        jbtn_respuesta_si.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtn_respuesta_si_actionPerformed(e);
                    }
                });
        jbtn_respuesta_si.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jbtn_respuesta_si_keyPressed(e);
                    }
                });
        jbtn_respuesta_no.setText("Cancelar");
        jbtn_respuesta_no.setBounds(new Rectangle(175, 60, 90, 25));
        jbtn_respuesta_no.setMnemonic('c');
        jbtn_respuesta_no.setFont(new Font("Arial", 1, 12));
        jbtn_respuesta_no.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jbtn_respuesta_no_actionPerformed(e);
                    }
                });
        jbtn_respuesta_no.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jbtn_respuesta_no_keyPressed(e);
                    }
                });
        jtxt_ncm.setBounds(new Rectangle(110, 30, 120, 20));
        jtxt_ncm.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jtxt_ncm_keyPressed(e);
                    }
                });
        this.getContentPane().add(jtxt_ncm, null);
        this.add(jbtn_respuesta_no, null);
        this.add(jbtn_respuesta_si, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
    }
 
    private void jbtn_respuesta_si_actionPerformed(ActionEvent e) {
       if(jtxt_ncm.getText().equals(""))
        dato="nada";
       else
       {
        dato= jtxt_ncm.getText();
         te = new TmsTarjetasViajeTbl();
//         List<TmsTarjetasViajeTbl> lt =  sesioninicial.queryTmsTarjetasViajeEncTblFindAByFolio(dato);     
        System.out.println("busca la tarjeta con el folio: "+dato);
         List<TmsTarjetasViajeTbl> lt =  (List<TmsTarjetasViajeTbl>)busquedas.tarjetasViajeTblFacadeRemote.queryTmsTarjetasViajeTblFindAByFolio2(dato);     
         System.out.println("Regresa la lista: "+lt);
         if(lt.size()>0)
            te = lt.get(0);
         else
           {
             JOptionPane.showMessageDialog(this,"¡El folio de tarjeta "+dato+" no se encontro en la base de datos!", "Folio no Registrado",JOptionPane.ERROR_MESSAGE);      
             jtxt_ncm.setText("");
             jtxt_ncm.requestFocus();
             return;
           }
         List<TmsRecaudacionTbl> lpn  = busquedas.recaudacionTblFacadeRemote.busquedaPagosPorTarjetaId(te.getTarjetaViajeId().toBigInteger());
         if(lpn.size()>0) 
         {
             boolean pagado = false;
             for(int i=0; i<lpn.size(); i++ )
               {
                tarjetaCancelada = lpn.get(i);
                if(tarjetaCancelada.getEstadoRecaudacion().equals("R"))
                 {
                  pagado = true;
                  break;
                 }
                  
               } 
             
             if(pagado) 
             {
                 this.dispose();
                 vista_cancelar_tarjeta pcpn = new vista_cancelar_tarjeta(); 
                 pcpn.setVisible(true);
             }
             else
             {
                 JOptionPane.showMessageDialog(this,"¡La tarjeta con folio "+dato+" ya esta cancelada!", "Tarjeta Cancelada",JOptionPane.ERROR_MESSAGE);      
                 jtxt_ncm.setText("");
                 jtxt_ncm.requestFocus();
             }
         }
         else
           {
             JOptionPane.showMessageDialog(this,"¡El folio de tarjeta "+dato+" no se encontro en la base de datos!", "Folio no Registrado",JOptionPane.ERROR_MESSAGE);      
             jtxt_ncm.setText("");
             jtxt_ncm.requestFocus();
           }
       }
       //dato= jtxt_ncm.getText();
        //aceptado= true;
        //this.dispose();
    }

    private void jbtn_respuesta_no_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void jbtn_respuesta_si_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 37 || e.getKeyCode() == 38)
            jtxt_ncm.requestFocus();
        if(e.getKeyCode() == 39 || e.getKeyCode() == 40)
            jbtn_respuesta_no.requestFocus();
        if(e.getKeyCode() == 10)
            jbtn_respuesta_si.doClick();
    }

    private void jbtn_respuesta_no_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 37 || e.getKeyCode() == 38)
            jbtn_respuesta_si.requestFocus();
        if(e.getKeyCode() == 39 || e.getKeyCode() == 40)
            jtxt_ncm.requestFocus();
        if(e.getKeyCode() == 10)
            jbtn_respuesta_no.doClick();
    }

    private void jtxt_ncm_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10)
          jbtn_respuesta_si.requestFocus();
        if(e.getKeyCode() == e.VK_DOWN)
          jbtn_respuesta_si.requestFocus();
        if(e.getKeyCode() == e.VK_UP)
          jbtn_respuesta_no.requestFocus();
    }
   

   

   /////////////////////////
 


    class vista_cancelar_tarjeta extends JDialog{

        private JTextArea jTextArea1 = new JTextArea();
        private JLabel jLabel1 = new JLabel();
        private JButton jbtn_si = new JButton();
        private JButton jbtn_no = new JButton();
        private ImageIcon imageHelp = new ImageIcon(vista_cancelar_tarjeta.class.getResource("pregunta.gif"));
        private JLabel jLabel2 = new JLabel();
        private int alto = 0;

        private JLabel jlbl_motivo = new JLabel();
        private JTextField jtxt_motivo = new JTextField();
        
        public vista_cancelar_tarjeta() {
            this.setTitle("Cancelacion de pago de tarjeta ");
            this.setDefaultLookAndFeelDecorated(true);
            this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.setAlwaysOnTop(true);
            this.setModal(true);
            alto = 40;
            try {
                jbInit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void jbInit() throws Exception {
            this.setSize(new Dimension(400, 450+alto));
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
            jTextArea1.setBounds(new Rectangle(10, 10, 375, 250+alto));
            jTextArea1.setEditable(false);
            jTextArea1.setFocusable(false);       
            jTextArea1.setFont(new Font("Dialog", 0, 12));
            jTextArea1.setSize(new Dimension(375, 250+alto));
            
            
            jlbl_motivo.setBounds(new Rectangle(50, 325+alto, 250, 25));
            jlbl_motivo.setFont(new Font("Arial", 1, 12));
            jlbl_motivo.setText("Motivo:");
            jtxt_motivo.setBounds(new Rectangle(105, 325+alto, 250, 25));
            jtxt_motivo.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            jtxt_motivo_keyPressed(e);
                        }
                    });
            
            
            jLabel1.setText("¿Seguro que desea cancelar el pago?");
            jLabel1.setBounds(new Rectangle(105, 280+alto, 250, 15));
            jLabel1.setFont(new Font("Arial", 1, 12));
            jbtn_si.setText("Si");
            jbtn_si.setBounds(new Rectangle(145, 370+alto, 50, 25));
            jbtn_si.setFont(new Font("Arial", 1, 12));
            jbtn_si.setMnemonic('s');
            jbtn_si.setHorizontalTextPosition(SwingConstants.CENTER);
            jbtn_si.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jbtn_si_actionPerformed(e);
                        }
                    });
            jbtn_si.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            jbtn_si_keyPressed(e);
                        }
                    });
            jbtn_no.setText("No");
            jbtn_no.setBounds(new Rectangle(205, 370+alto, 50, 25));
            jbtn_no.setMnemonic('n');
            jbtn_no.setFont(new Font("Arial", 1, 12));
            jbtn_no.setHorizontalTextPosition(SwingConstants.CENTER);
            jbtn_no.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jbtn_no_actionPerformed(e);
                        }
                    });
            jbtn_no.addKeyListener(new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                            jbtn_no_keyPressed(e);
                        }
                    });
            jLabel2.setBounds(new Rectangle(60, 265+alto, 35, 45));
            jLabel2.setIcon(imageHelp);
            jTextArea1.append("\n     ESTE PAGO SE REGISTRO CON LOS SIGUIENTES DATOS\n\n");
            jTextArea1.append(" REFERENCIA  : "+tarjetaCancelada.getRecaudacionReferencia()+ "\n\n");
             TmsTarjetasViajeTbl tar = te;
             jTextArea1.append(" Folio de Tarjeta  :   "+tar.getFolioTarjeta()+ "\n");
             jTextArea1.append(" Sueldo                 :   "+tarjetaCancelada.getSueldoOperador()+ "\n");
             jTextArea1.append(" Retencion           :   "+tarjetaCancelada.getRetencion()+ "\n");
             double sueldo = tarjetaCancelada.getSueldoOperador().doubleValue();
             double retencion = tarjetaCancelada.getRetencion().doubleValue();
             double totaltarjeta = sueldo - retencion;
             jTextArea1.append(" Total                     :   "+totaltarjeta+ "\n");
             double vtaAbordo = tarjetaCancelada.getImporteVentaAbordo().doubleValue();
             if(vtaAbordo>0)
                {
                    jTextArea1.append(" Boletos Abordo  :   "+tarjetaCancelada.getBoletosVentaAbordo() + "\n");
                    jTextArea1.append(" Venta Abordo      :   "+tarjetaCancelada.getImporteVentaAbordo() + "\n");
                }
             double vtaManual = tarjetaCancelada.getImporteVentaManual().doubleValue();
             if(vtaManual>0)
               {
                   jTextArea1.append(" Boletos Manual  :   "+tarjetaCancelada.getBoletosVentaManual()+ "\n");
                   jTextArea1.append(" Venta Manual     :   "+tarjetaCancelada.getImporteVentaManual()+ "\n");
               }
            TmsUsuariosTbl usr = busquedas.usuariosTblFacadeRemote.find(BigDecimal.valueOf(tarjetaCancelada.getCreadoPor().longValue()));//; queryTmsUsuariosTblFindById((long)tarjetaCancelada.getCreadoPor());
             jTextArea1.append(" Recaudador       :   "+usr.getUsuarioNombre()+ "\n");
             SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
             SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
             jTextArea1.append(" Fecha de Recaudacion:  "+formatf.format(new Date(tarjetaCancelada.getFechaCreacion().getTime()))  + "\n");
             jTextArea1.append(" Hora de Recaudacion  : "+ formath.format(new Date(tarjetaCancelada.getFechaHoraRecaudacion().getTime()))+ "\n");
            this.add(jLabel2, null);
            this.add(jbtn_no, null);
            this.add(jbtn_si, null);
            this.add(jLabel1, null);
            this.add(jTextArea1, null);
            this.add(jlbl_motivo,null);
            this.add(jtxt_motivo,null);
            this.jbtn_si.requestFocus();
        }


    //----------- Acciones de los Botones -------------------//
        private void jbtn_si_actionPerformed(ActionEvent e) {
            if(jtxt_motivo.getText().equals("") || jtxt_motivo.getText().length()>29)
            {
                JOptionPane.showMessageDialog(this,"¡Debes escribir un motivo menor a 30 caracteres!","",JOptionPane.INFORMATION_MESSAGE);
                jtxt_motivo.requestFocus();
            }
            else
            {
             Vector vestado= (Vector)busquedas.cajasTblFacadeRemote.buscarEstadoSesion(idSesion);
             String estado = vestado.get(0).toString();
             if(estado.equals("CERRADA")){
                 new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
                 System.exit(0);
             }
              System.out.println("recaudacionAutomatica: "+te.getRecaudacionAutomatica());
             long corteTar = tarjetaCancelada.getCorteId().getCorteId().longValue();
              if(corteTar==corteId || te.getRecaudacionAutomatica().equals("S"))
              {            
               Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
               fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
             
             //List<TmsTarjetasViajeTbl> lte = sesioninicial.queryTmsTarjetasViajeEncTblFindAById(Long.valueOf(tarjetaCancelada.getTarjetaViajeEncId()));
             //TmsTarjetasViajeTbl tarenc = lte.get(0);
             //List<TmsFechahoraV> lfecha = sesioninicial.queryTmsFechahoraVFindAll();
             //List<TmsUsuariosTbl> lu = sesioninicial.queryTmsUsuariosTblFindByUsuarioNumero(""+supervisor);
             //sesioninicial.updateTarjetaViaje(tarenc.getTarjetaViajeEncId(),(long)0,usuario,lfecha.get(0).getFechahorasys(),tarenc.getAdicional1(),tarenc.getAdicional2());
             //sesioninicial.cancelarPagoNormal(tarjetaCancelada.getPagoNormalId(), usuario, lfecha.get(0).getFechahorasys(),Long.valueOf(lu.get(0).getUsuarioId().toString()), jtxt_motivo.getText());
              
               //VAGL28032014 Todas las tarjetas se regresan al status CONFIRMADA
               Vector vedotar = null;
              //if(te.getRecaudacionAutomatica().equals("N"))
                //vedotar = (Vector)busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje("ABIERTA");
              //else
                vedotar = (Vector)busquedas.variosFacadeRemote.queryBuscaEstadoTarjetaViaje("CONFIRMADA");

              Vector edotar = (Vector)vedotar.get(0);
              te.setEstadoTarjetaId(BigInteger.valueOf(Long.valueOf(edotar.get(0).toString())));
              te.setUltimaActualizacionPor(BigInteger.valueOf(usuario));
              te.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
              te.setReplicacionOrigen("CENTRAL");
              ///*VAGL04092008
              Vector vsaldoAnterior = busquedas.variosFacadeRemote.buscaSaldoOperador(te.getOperador());
              Vector vultimaRecaudacion = null;
              Vector vpultimaRecaudacion = null;
              TmsRecaudacionTbl ultRec = null;
              double saldoAnterior = 0;
              long ultimaRecaudacion =0;
              if(vsaldoAnterior.size()>0)
              {
                  Vector vec = (Vector)vsaldoAnterior.get(0);
                  saldoAnterior = Double.valueOf(vec.get(0).toString());
                  vultimaRecaudacion = busquedas.variosFacadeRemote.buscaUltimaRecaudacion(te.getOperador());
                  Vector vec2 = (Vector)vultimaRecaudacion.get(0);
                  ultimaRecaudacion =Long.valueOf(vec2.get(0).toString());
                  System.out.println("ulrima recaudacion: "+ultimaRecaudacion);
                  System.out.println("tarjeta cancelada:  "+tarjetaCancelada.getRecaudacionId().longValue());
                  if(ultimaRecaudacion==tarjetaCancelada.getRecaudacionId().longValue())
                  {
                      vpultimaRecaudacion = busquedas.variosFacadeRemote.buscaPuenultimaRecaudacion(te.getOperador(), ultimaRecaudacion);
                     if(vpultimaRecaudacion.size()>0)
                     {
                      Vector vecp2 = (Vector)vpultimaRecaudacion.get(0);
                      ultimaRecaudacion =Long.valueOf(vecp2.get(0).toString());
                     }
                  }
                  ultRec  = busquedas.recaudacionTblFacadeRemote.find(BigDecimal.valueOf(ultimaRecaudacion));
                  //VAGL03102008 double saldoRecaudacion = tarjetaCancelada.getSaldo().doubleValue(); 
                  Vector vvcv = busquedas.variosFacadeRemote.buscaCarteraUltimaRecaudacion(tarjetaCancelada.getRecaudacionId().longValue()); //VAGL03102008
                  double valorCarteraRecaudacion =  0;
                  if(vvcv.size()>0)
                  {
                      Vector vcv = (Vector)vvcv.get(0);
                      valorCarteraRecaudacion = Double.valueOf(vcv.get(0).toString());
                  }
                   //VAGL03102008 double saldoNuevo = saldoAnterior - saldoRecaudacion;
                  double saldoNuevo = saldoAnterior - valorCarteraRecaudacion;
                  System.out.println("\nsaldoAnterior= "+saldoAnterior);
                  System.out.println("valorCarteraRecaudacion = "+valorCarteraRecaudacion);
                  System.out.println("saldoNuevo = "+saldoNuevo);
                  ultRec.setSaldo(BigDecimal.valueOf(saldoNuevo));
                  ultRec.setAdicional2(""+saldoAnterior);
                  busquedas.recaudacionTblFacadeRemote.edit(ultRec);
              }
              //*/VAGL04092008
              busquedas.tarjetasViajeTblFacadeRemote.edit(te);
              tarjetaCancelada.setAdicional1(jtxt_motivo.getText());
              tarjetaCancelada.setAutorizadoPor(BigInteger.valueOf(supervisor));
              tarjetaCancelada.setUltimaActualizacionPor(BigInteger.valueOf(supervisor));
              tarjetaCancelada.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
              tarjetaCancelada.setEstadoRecaudacion("C");
              //VAGL04092008float saldo = tarjetaCancelada.getSaldo().floatValue();
              Vector vmntGastos = (Vector)busquedas.variosFacadeRemote.buscaMontoGastosPorTarjeta(te.getFolioTarjeta());
              Vector vmntGastosRestar = (Vector)busquedas.variosFacadeRemote.buscaMontoGastosPorTarjetaParaRestar(te.getFolioTarjeta());//VAGL04092008
              
              //VAGL04092008 double mntGastos = Double.valueOf(vmntGastos.get(0).toString());
              double mntGastos = Double.valueOf(vmntGastos.get(0).toString()) - Double.valueOf(vmntGastosRestar.get(0).toString());//VAGL04092008
              //VAGL04092008if(saldo<0)
              //VAGL04092008    saldo = (saldo *(-1));
              busquedas.recaudacionTblFacadeRemote.edit(tarjetaCancelada);
//              long corteTar = tarjetaCancelada.getCorteId().getCorteId().longValue();
//              if(corteTar==corteId)
//              {
                  principal.restaAcumulado(mntGastos);
                  //VAGL04092008 principal.restaAcumulado(saldo);
              //}
              this.dispose();
             JOptionPane.showMessageDialog(this,"¡El pago de la tarjeta se cancelo correctamente!","Pago Cancelado",JOptionPane.INFORMATION_MESSAGE);
           }
          else
              JOptionPane.showMessageDialog(this,"¡La tarjeta se recaudó en otro corte, no es posible cancelarla!","Imposible cancelar Tarjeta",JOptionPane.ERROR_MESSAGE);
          }
        }

        private void jbtn_no_actionPerformed(ActionEvent e) {
            this.dispose();
        }
    
    //--------------------Eventos del teclado --------------//
    private void jtxt_motivo_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP)
            jbtn_no.requestFocus();
        if(e.getKeyCode() == e.VK_DOWN)
            jbtn_si.requestFocus();
        if(e.getKeyCode() == 10)
            jbtn_si.requestFocus();
    }
            
    private void jbtn_si_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_UP)
            jtxt_motivo.requestFocus();
        if(e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_DOWN)
            jbtn_no.requestFocus();
        if(e.getKeyCode() == 10)
            jbtn_si.doClick();
    }
    
    private void jbtn_no_keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_UP)
            jbtn_si.requestFocus();
        if(e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_DOWN)
            jtxt_motivo.requestFocus();
        if(e.getKeyCode() == 10)
            jbtn_no.doClick();
    }
      
    }
}

}
