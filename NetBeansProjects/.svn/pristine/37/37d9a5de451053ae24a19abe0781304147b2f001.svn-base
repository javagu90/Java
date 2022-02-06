/*
 * AppPrincipal.java
 *
 * Created on 7 de agosto de 2007, 06:44 PM
 *
 * To change this template, choose Tools | Templat  e Manager
 * and open the template in the editor.
 */

package tmstrafico;



import com.estrellaroja.confirmacionviajes.forms.JIFConfirmacionViajes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu; 
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import tms.compensacion.gui.JIFCompensacion;
import tms_accesoubicaciones.JIFAccUbic;
import tms_desktop.MDIDesktopPane;
import tms_ejecutarreportes.TMSEjecutarReportesMonitor;
import tmslistapasajeros.JIF_ListaPasajeros;
import tmspases_traslado.JIF_PasesTrasladov1;
import tmstraficomain.TMSLoginGlobalManagedBean;
import tmstraficomain.jdlg_advertencia;
import tmstraficomain.jdlg_bloqueo;
import tmstraficomain.jdlg_error;
import tmstraficomain.login_main;
import tms_guardias.JIFGuardia;
import tms_incidencias.JIFIncidencia;   
import tms_ofertasservicios.JIFOfertasServicios;
import tms_psd.JIFPSD;
import tmsbloqueoporlote.JIFTmsBloqueoPorLote;
import tmsconsultaocupacion.JIFConsultaOcupacion;
import tmsestadosoperador.JIFCambioEstadoOperador;
import tmsmodificatarjetaviaje.JIFModificaTarViaje;
import tmspuertas.JIFPuertas;
import tmspuertas.util.PcInfo;
import tmsreglassustitucion.JIFRaglasSustitucion;
import tmsreimpresiontarjetasmonitor.JIF_Reimpresion_Tarjetas;
import tmsroles.JIFCFNDatosIniciales;
import tmstrafico.solicitud.TmsSesionGeneralTraficFacadeRemote;
 
/**
 *
 * @author ocruz
 */
public class AppPrincipal extends JFrame implements ActionListener{
    // clases que se cargan
    JFrame appPrincipal = null;
    JIFPSD fr1 = null;
    JIFPuertas fr2 = null;
    JIF_Reimpresion_Tarjetas fr3 = null;
    JIFAccUbic fr4 = null;
    JIFOfertasServicios fr5 = null;
    JIFCFNDatosIniciales fr6 = null;
    JIFRaglasSustitucion fr7 = null;
    JIFIncidencia fr8 = null;
    JIFGuardia fr9 = null;
    JIFModificaTarViaje fr10 = null;
    JIFCambioEstadoOperador fr11 = null;
    TMSEjecutarReportesMonitor fr12 = null;
    //JIFVenta fr13 = null; 
    JIFConsultaOcupacion fr14 = null; 
    JIF_ListaPasajeros fr15 = null;
    tmscodigobarras.JIF_ValidarCodigoBarras fr16 = null;
    JIF_PasesTrasladov1 fr17 = null;
    JIFTmsBloqueoPorLote fr18 = null;
    JIFConfirmacionViajes fr19 = null;
    JIFCompensacion fr20 = null;
    private TmsSesionGeneralTraficFacadeRemote sesionGeneralFacateRemote;
    private JScrollPane jscpDesktop;
    private MDIDesktopPane dskPrincipal;
    //private JInternalFrame iApp;
    private JPopupMenu popMenuPrincipal;
    private Object[][] Tabla;
    private String NombreIFrame;
    private static int indice=0;
    private static int contador=0;
    private long SesionId;
    private long UsuarioId;
    private String nombreUsuario;
    private String numeroUsuario;
    private String contraseñaUsuario;
    private login_main appInicial;
    private Vector datosIniciales = new Vector();
    private String nombreTerminal;
    private TMSLoginGlobalManagedBean vntaLoginCnx;
    private Object[] objX;
    /** Creates a new instance of AppPrincipal */
    public AppPrincipal(long pSesionId, long pUsuarioId, String pNombreUsuario, String pNumeroUsuario, String pContraseñaUsuario ,login_main pAppInicial, String pnombreTerminal,TMSLoginGlobalManagedBean  pventaLoginCnx) {

        this.SesionId=pSesionId;
        this.UsuarioId=pUsuarioId;
        this.appInicial=pAppInicial;
        this.nombreUsuario=pNombreUsuario;
        this.numeroUsuario = pNumeroUsuario;
        this.contraseñaUsuario = pContraseñaUsuario;
        this.nombreTerminal = pnombreTerminal;
        this.vntaLoginCnx = pventaLoginCnx;
        this.sesionGeneralFacateRemote = this.vntaLoginCnx.getSesionesGlobalFacadeRemote();
        objX = this.queryAS();
        datosIniciales.add(UsuarioId);
        datosIniciales.add(numeroUsuario);
        datosIniciales.add(nombreUsuario);
        datosIniciales.add(""+pSesionId);
        datosIniciales.add("0");
        datosIniciales.add("0");
        datosIniciales.add("0");
        datosIniciales.add("0");
        appInicial.setVisible(false);
        
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cargaMenuDinamico();
        appPrincipal = this;
    }
    
    
    
/* 
* @param jar
* La ubicacion en el dico del archivo .jar
* Ej: c:/lib/classes12.jar
* @param clase El nombre completo de la clase
* Ej: oracle.jdbc.driver.OracleDriver
* @return La clase
* @throws MalformedURLException
* @throws ClassNotFoundException
*/
public Class getClass(String jar, String clase)throws MalformedURLException, ClassNotFoundException {
//URL[] urls = new URL[1];
//urls[0] = new URL("jar:" + jar);
//URLClassLoader ucl = URLClassLoader.newInstance(urls);
//return ucl.loadClass(clase);
 Class c = null;   
 try {
  URL listaURLs[] = {
    new URL (""+jar),
    //new URL ("http://case.iti.upv.es/Monkey.zip"),
    //new URL ("http://torpedo.upv.es/luis/norte/"),
   // new File ("misClases.jar").toURL()
  };
  URLClassLoader cargador = new URLClassLoader (listaURLs);
  c = cargador.loadClass(clase);
  return cargador.loadClass(clase);
    //MiClase mc = (clase) c.newInstance();
} catch (MalformedURLException e) {
  // cargar la clase de otra manera o error
}

   return c;
    
}


   
    // CONEXION A LA SOLICITUD GENERAL
    private TmsSesionGeneralTraficFacadeRemote lookupTmsSesionGeneralFacade() {
        try {
            Context c = new InitialContext();
            return (TmsSesionGeneralTraficFacadeRemote) c.lookup("xertmst.solicitud.TmsSesionGeneralFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    // METODO INIT
    private void jbInit() throws Exception {
        PcInfo estaPC = new PcInfo();
        this.setTitle("TMS Tráfico v2.7.6 "+nombreTerminal+"- Administrador de Aplicaciones - " + this.nombreUsuario+" - IP: "+estaPC.getHostAddress());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setResizable(false);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        
        popMenuPrincipal = new JPopupMenu();
        popMenuPrincipal.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
        jscpDesktop = new JScrollPane();
        dskPrincipal = new MDIDesktopPane();
        JLabel jLabel1 = new JLabel();
        jLabel1.setIcon(new javax.swing.ImageIcon(AppPrincipal.class.getResource("/tmstrafico/images/fondoPantallaLanzadora.gif")));//"C:\\NetBeansProyects\\TMSCorteTaquilla\\TMSCorteTaquilla-app-client\\src\\java\\tms_cortetaquilla\\images\\bien.gif"));
        jLabel1.setText("");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jLabel1.setBounds(0, 0, 700, 481);
        Dimension DilaogSize = jLabel1.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        jLabel1.setBounds( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 , 700, 481);
        dskPrincipal.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskPrincipal.add(popMenuPrincipal,1);
        jscpDesktop.getViewport().add(dskPrincipal, null);
        this.setContentPane(jscpDesktop);
    }
    
    // METODO PARA EL KEYLISTENER DEL JFRAME PRINCIPAL
    private void formKeyPressed(KeyEvent evt) {
        if(evt.isControlDown() && evt.getKeyCode()==KeyEvent.VK_M){
            mostrarPopupPrincipal();
        }
        else{
            switch(evt.getKeyCode()){
                case KeyEvent.VK_ESCAPE: popMenuPrincipal.setVisible(false); break;
            }
        }
        
        if(evt.isControlDown() && evt.getKeyCode()==KeyEvent.VK_B){
          new jdlg_bloqueo("Pantalla Bloqueada",sesionGeneralFacateRemote,numeroUsuario, contraseñaUsuario).setVisible(true);
            
        }        
    }
    
    // DESPLIEGA POPUP MENU
    private void mostrarPopupPrincipal(){
        popMenuPrincipal.show(dskPrincipal, 0, 0);
    }
    
    // ACTION LISTENER DE LOS ELEMENTOS DE MENU
    public void actionPerformed(ActionEvent e) {
        //if(dskPrincipal.getAllFrames().length>0) return;
        JMenuItem source = (JMenuItem)(e.getSource());
        if(source.getName().equals("Salir")){
            if(dskPrincipal.getAllFrames().length>0){
                new jdlg_error("No es posible cerrar el sistema.","Primero cierre las ventanas que tenga abiertas.","No es posible cerrar el sistema.").setVisible(true);
                return;
            }
            try{
            vntaLoginCnx.getSesionesGlobalFacadeRemote().UpdateSesion(this.SesionId, vntaLoginCnx.FechaServidor());
            this.appInicial.setVisible(true);
            this.dispose(); // FINALIZA APLICACION
            }catch(Exception ex){
                new jdlg_error("¡Error del Sistema! ","El Sistema se cerrará completamente","Error del Sistema").setVisible(true);
                System.exit(0);
            }
        }   
        else
        {
         Vector vestado= (Vector)sesionGeneralFacateRemote.buscarEstadoSesion(SesionId);
         String estado = vestado.get(0).toString();
         if(estado.equals("CERRADA")){
             new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
             System.exit(0);
         }
        int indicesel = Integer.parseInt(source.getName());
        System.out.println("MENU_ID: "+Tabla[indicesel][6]);
        datosIniciales.set(4,Tabla[indicesel][6]);
        datosIniciales.set(5,objX[0].toString());
        datosIniciales.set(6,objX[1].toString());
        datosIniciales.set(7,Tabla[indicesel][2].toString().toUpperCase());
        System.out.println("Vector inicial: "+datosIniciales.toString());
                
        if(Tabla[indicesel][4].toString().equals("Nada"))// || Tabla[indicesel][5].toString().equals("Nada"))
            new jdlg_advertencia("¡La funcion no esta configurada! ","","Error de configuracion").setVisible(true);
        else
        {
            boolean encontrada = false;
            //JInternalFrame fr = null;
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAPSD")) 
            {
                if(fr1!=null){
                    if(fr1.isIconifiable()) 
                        try { fr1.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }

                encontrada = true;
                fr1 = new JIFPSD(datosIniciales);
                
                if(!fr1.getinicioGral()){
                    fr1 = null;
                    return;
                }
                else
                {//appPrincipal.requestFocusInWindow();
                    fr1.setName("TMSJAPSD");
                    fr1.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr1=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr1.jPnlPsdX.setFoco(); }
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr1.jPnlPsdX.getEventoTeclado()!=null){
                                if(fr1.jPnlPsdX.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr1.jPnlPsdX.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr1.getName());
                                    fr1.jPnlPsdX.getEventoTeclado().consume();
                                    fr1.jPnlPsdX.setEventoTeclado(null);
                                }
                                return;
                            }
                            appPrincipal.requestFocus();
                        }
                        public void internalFrameOpened(InternalFrameEvent e) { }
                    });

                    dskPrincipal.add(fr1,2);
                    fr1.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr1.setVisible(true);
                    fr1.requestFocus();
                }
            }
            

            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARPT")) 
            {
                if(fr2!=null){
                    if(fr2.isIconifiable())
                        try { fr2.setIcon(false); } catch (PropertyVetoException ex) { ; }
                    return;
                }

                encontrada = true;
                fr2 = new JIFPuertas(datosIniciales);
                if(!fr2.getInicioGral()){
                    fr2 = null;
                    return;
                }
                else
                {
                fr2.setName("TMSJARPT");
                fr2.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr2=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr2.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        System.out.println("Antes de verificar.... if(fr2.getEventoTeclado()!=null) == "+fr2.getEventoTeclado());
                        if(fr2.getEventoTeclado()!=null){
                            System.out.println("Evento teclado: "+fr2.getEventoTeclado().getKeyCode());
                            System.out.println("isControlDown: "+fr2.getEventoTeclado().isControlDown());
                            if(fr2.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr2.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr2.getName());
                                fr2.getEventoTeclado().consume();
                                fr2.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                
                dskPrincipal.add(fr2,2);
                fr2.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr2.setVisible(true);
                fr2.requestFocus();
            }
            }            
            
             if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARETARMON")) 
            {
                if(fr3!=null){
                    if(fr3.isIconifiable())
                        try { fr3.setIcon(false); } catch (PropertyVetoException ex) { ; }
                    return;
                }
                encontrada = true;
                fr3 = new JIF_Reimpresion_Tarjetas(datosIniciales);
                if(!fr3.getInicioGral()){
                    fr3 = null;
                    return;
                }
                else
                {
                    fr3.setName("TMSJARETARMON");
                    fr3.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr3=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr3.setFoco(); }
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr3.getEventoTeclado()!=null){
                                if(fr3.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr3.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr3.getName());
                                    fr3.getEventoTeclado().consume();
                                    fr3.setEventoTeclado(null);
                                }
                                return;
                            }
                            appPrincipal.requestFocus();
                        }
                        public void internalFrameOpened(InternalFrameEvent e) { }
                    });

                    dskPrincipal.add(fr3,2);
                    fr3.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr3.setVisible(true);
                    fr3.requestFocus();
             }
                
            }            
                      
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAACCUBI")) 
            {
                if(fr4!=null){
                    if(fr4.isIconifiable()) 
                        try { fr4.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr4 = new JIFAccUbic(datosIniciales);
                if(!fr4.getInicioGral()){
                    fr4 = null;
                    return;
                }
                else
                {
                    fr4.setName("TMSJAACCUBI");
                    fr4.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr4=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr4.setFoco(); } 
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr4.getEventoTeclado()!=null){
                                if(fr4.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr4.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr4.getName());
                                    fr4.getEventoTeclado().consume();
                                    fr4.setEventoTeclado(null);
                                }
                                return;
                            }
                            appPrincipal.requestFocus();
                        }
                        public void internalFrameOpened(InternalFrameEvent e) { }
                    });
                    
                    dskPrincipal.add(fr4,2);
                    //fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr4.setVisible(true);
                    fr4.requestFocus();
                }
            }
            
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAOFS"))                 
            {
                if(fr5!=null){
                    if(fr5.isIconifiable()) 
                        try { fr5.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true; 
                fr5 = new JIFOfertasServicios(datosIniciales);
                if(!fr5.getInicioGral()){
                    fr5 = null;
                    return;
                }
                else
                {
                    fr5.setName("TMSJAOFS");
                    fr5.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr5=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr5.setFoco(); } 
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr5.getEventoTeclado()!=null){
                                if(fr5.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr5.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr5.getName());
                                    fr5.getEventoTeclado().consume();
                                    fr5.setEventoTeclado(null);
                                }
                                return;
                            }
                            appPrincipal.requestFocus();
                        }
                        public void internalFrameOpened(InternalFrameEvent e) { }
                    });
                    dskPrincipal.add(fr5,2);
                    fr5.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr5.setVisible(true);
                    fr5.requestFocus();
                }
                
            }
             
           if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAGENCOR"))                 
            {
                if(fr6!=null){
                    if(fr6.isIconifiable()) 
                        try { fr6.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                fr6 = new JIFCFNDatosIniciales(datosIniciales);
                fr6.setName("TMSJAGENCOR");
                fr6.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr6=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr6.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr6.getEventoTeclado()!=null){
                            if(fr6.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr6.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr6.getName());
                                fr6.getEventoTeclado().consume();
                                fr6.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                encontrada = true;
                dskPrincipal.add(fr6,2);
                fr6.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr6.setVisible(true);
                fr6.requestFocus();
            }   
            
           if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJRSUST"))                 
            {
                if(fr7!=null){
                    if(fr7.isIconifiable()) 
                        try { fr7.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                    fr7 = new JIFRaglasSustitucion(datosIniciales);
                fr7.setName("TMSJRSUST");
                fr7.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr7=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr7.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr7.getEventoTeclado()!=null){
                            if(fr7.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr7.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr7.getName());
                                fr7.getEventoTeclado().consume();
                                fr7.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                encontrada = true;
                dskPrincipal.add(fr7,2);
                fr7.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr7.setVisible(true);
                fr7.requestFocus();
            } 
            
            
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAINC"))                 
            {
                if(fr8!=null){
                    if(fr8.isIconifiable()) 
                        try { fr8.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                    try {
                        fr8 = new JIFIncidencia(datosIniciales);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                if(!fr8.getInicioGral()){
                    fr8 = null;
                    return;
                }
                else
                {
                    fr8.setName("TMSJAINC");
                    fr8.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr8=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr8.setFoco(); } 
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr8.getEventoTeclado()!=null){
                                if(fr8.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr8.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr8.getName());
                                    fr8.getEventoTeclado().consume();
                                    fr8.setEventoTeclado(null);
                                }
                                return;
                            }
                            appPrincipal.requestFocus();
                        }
                        public void internalFrameOpened(InternalFrameEvent e) { }
                    });
                    dskPrincipal.add(fr8,2);
                    fr8.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr8.setVisible(true);
                    fr8.requestFocus();
                }
                
            }
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAGUA"))                 
            {
                if(fr9!=null){
                    if(fr9.isIconifiable()) 
                        try { fr9.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true; 
                fr9 = new JIFGuardia(datosIniciales);
                if(!fr9.getInicioGral()){
                    fr9 = null;
                    return;
                }
                else
                {
                    fr9.setName("TMSJAGUA");
                    fr9.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr9=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr9.setFoco(); }
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr9.getEventoTeclado()!=null){
                                if(fr9.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr9.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr9.getName());
                                    fr9.getEventoTeclado().consume();
                                    fr9.setEventoTeclado(null);
                                }
                                return;
                            }
                            appPrincipal.requestFocus();
                        }
                        public void internalFrameOpened(InternalFrameEvent e) { }
                    });
                    dskPrincipal.add(fr9,2);
                    fr9.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr9.setVisible(true);
                    fr9.requestFocus();
                }
                
            }            

            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAMODTAR")) 
            {
                if(fr10!=null){
                    if(fr10.isIconifiable()) 
                        try { fr10.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr10 = new JIFModificaTarViaje(datosIniciales);
                fr10.setName("TMSJAMODTAR");
                fr10.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr10=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr10.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr10.getEventoTeclado()!=null){
                            if(fr10.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr10.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr10.getName());
                                fr10.getEventoTeclado().consume();
                                fr10.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr10,2);
                //fr10.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr10.setVisible(true);
                fr10.requestFocus();
            }            
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACAMEDOPER")) 
            {
                if(fr11!=null){
                    if(fr11.isIconifiable()) 
                        try { fr11.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr11 = new JIFCambioEstadoOperador(datosIniciales);
                fr11.setName("TMSJACAMEDOPER");
                fr11.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr11=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr11.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr11.getEventoTeclado()!=null){
                            if(fr11.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr11.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr11.getName());
                                fr11.getEventoTeclado().consume();
                                fr11.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr11,2);
                fr11.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr11.setVisible(true);
                fr11.requestFocus();
            }            
                        
            
           if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAXRPT")) 
            {
                if(fr12!=null){
                    if(fr12.isIconifiable()) 
                        try { fr12.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr12 = new TMSEjecutarReportesMonitor(datosIniciales);
                if(!fr12.getInicioGral()){
                    fr12 = null;
                    return;
                }
                else
                {
                    fr12.setName("TMSJAXRPT");
                    fr12.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr12=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr12.setFoco(); }
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr12.getEventoTeclado()!=null){
                                if(fr12.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr12.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr12.getName());
                                    fr12.getEventoTeclado().consume();
                                    fr12.setEventoTeclado(null);
                                }
                                return;
                            }
                            appPrincipal.requestFocus();
                        }
                        public void internalFrameOpened(InternalFrameEvent e) { }
                    });
                    dskPrincipal.add(fr12,2);
                    //fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr12.setVisible(true);
                    fr12.requestFocus();
                }
            } 
            

//            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACALL"))                 
//            {
//                if(fr13!=null){
//                    if(fr13.isIconifiable()) 
//                        try { fr13.setIcon(false); } catch (PropertyVetoException ex) { }
//                    return;
//                }
//                fr13 = new JIFVenta(datosIniciales);
//                if(!fr13.getInicioGral()){
//                    fr13 = null;
//                    return;
//                }
//                else
//                {
//                    fr13.setName("TMSJACALL");
//                    fr13.addInternalFrameListener(new InternalFrameListener() {
//                        public void internalFrameActivated(InternalFrameEvent e) { }
//                        public void internalFrameClosed(InternalFrameEvent e) { fr13=null; }
//                        public void internalFrameClosing(InternalFrameEvent e) { }
//                        public void internalFrameDeactivated(InternalFrameEvent e) { }
//                        public void internalFrameDeiconified(InternalFrameEvent e) { fr13.setFoco(); }
//                        public void internalFrameIconified(InternalFrameEvent e) {
//                            if(fr13.getEventoTeclado()!=null){
//                                if(fr13.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr13.getEventoTeclado().isControlDown()){
//                                    muestraSiguienteJIF(fr13.getName());
//                                    fr13.getEventoTeclado().consume();
//                                    fr13.setEventoTeclado(null);
//                                }
//                                return;
//                            }
//                            appPrincipal.requestFocus();
//                        }
//                        public void internalFrameOpened(InternalFrameEvent e) { }
//                    });
//                    encontrada = true;
//                    dskPrincipal.add(fr13,2);
//                    fr13.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
//                    fr13.setVisible(true);
//                    fr13.requestFocus();
//                }
//            }

            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACONOCUP")) 
            {
                if(fr14!=null){
                    if(fr14.isIconifiable()) 
                        try { fr14.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr14 = new JIFConsultaOcupacion(datosIniciales);
                fr14.setName("TMSJACONOCUP");
                fr14.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr14=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr14.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr14.getEventoTeclado()!=null){
                            if(fr14.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr14.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr14.getName());
                                fr14.getEventoTeclado().consume();
                                fr14.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr14,2);
                fr14.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr14.setVisible(true);
                fr14.requestFocus();
            } 
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJALTPA")) 
            {
                if(fr15!=null){
                    if(fr15.isIconifiable()) 
                        try { fr15.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr15 = new JIF_ListaPasajeros();
                fr15.setName("TMSJALTPA");
                fr15.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr15=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr15.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr15.getEventoTeclado()!=null){
                            System.out.println("Evento teclado: "+fr15.getEventoTeclado().getKeyCode());
                            System.out.println("isControlDown: "+fr15.getEventoTeclado().isControlDown());
                            if(fr15.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr15.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr15.getName());
                                fr15.getEventoTeclado().consume();
                                fr15.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr15,2);
                fr15.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr15.setVisible(true);
                fr15.requestFocus();
            }             
             
             
                        
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAVBINT")) 
            {
                if(fr16!=null){
                    if(fr16.isIconifiable()) 
                        try { fr16.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                } 
                encontrada = true;
                this.fr16 = new tmscodigobarras.JIF_ValidarCodigoBarras(); 
                this.fr16.setName("TMSJAVBINT");
                this.fr16.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr16=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr16.setFoco(); } 
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr16.getEventoTeclado()!=null){
                            System.out.println("Evento teclado: "+fr16.getEventoTeclado().getKeyCode());
                            System.out.println("isControlDown: "+fr16.getEventoTeclado().isControlDown());
                            if(fr16.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr16.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr16.getName());
                                fr16.getEventoTeclado().consume();
                                fr16.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr16,2);
                fr16.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr16.setVisible(true);
                fr16.requestFocus();
            }         
            
             if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAPSTR")) 
            {
                if(fr17!=null){
                    if(fr17.isIconifiable()) 
                        try { fr17.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr17 = new JIF_PasesTrasladov1(datosIniciales);
                fr17.setName("TMSJAPSTR");
                fr17.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr17=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr17.setFoco(); } 
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr17.getEventoTeclado()!=null){
                            System.out.println("Evento teclado: "+fr17.getEventoTeclado().getKeyCode());
                            System.out.println("isControlDown: "+fr17.getEventoTeclado().isControlDown());
                            if(fr17.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr17.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr17.getName());
                                fr17.getEventoTeclado().consume();
                                fr17.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr17,2);
                fr17.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr17.setVisible(true);
                fr17.requestFocus();
            }     
            
           
             if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJABL")) 
            {
                if(fr18!=null){
                    if(fr18.isIconifiable()) 
                        try { fr18.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr18 = new JIFTmsBloqueoPorLote(datosIniciales);
                fr18.setName("TMSJABL");
                fr18.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr18=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr18.setFoco(); } 
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr18.getEventoTeclado()!=null){
                            System.out.println("Evento teclado: "+fr18.getEventoTeclado().getKeyCode());
                            System.out.println("isControlDown: "+fr18.getEventoTeclado().isControlDown());
                            if(fr18.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr18.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr18.getName());
                                fr18.getEventoTeclado().consume();
                                fr18.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr18,2);
                fr18.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr18.setVisible(true);
                fr18.requestFocus();
            }             
            
            
         
             if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACONFVJS"))
            {
                if(fr19!=null){
                    if(fr19.isIconifiable())
                        try { fr19.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr19 = new JIFConfirmacionViajes(datosIniciales);
                fr19.setName("TMSJACONFVJS");
                fr19.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr19=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr19.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr19.getEventoTeclado()!=null){
                            System.out.println("Evento teclado: "+fr19.getEventoTeclado().getKeyCode());
                            System.out.println("isControlDown: "+fr19.getEventoTeclado().isControlDown());
                            if(fr19.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr19.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr19.getName());
                                fr19.getEventoTeclado().consume();
                                fr19.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr19,2);
                fr19.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr19.setVisible(true);
                fr19.requestFocus();
            }             
                        

             if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACONCOMVAR"))
            {
                if(fr20!=null){
                    if(fr20.isIconifiable())
                        try { fr20.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr20 = new JIFCompensacion(datosIniciales);
                fr20.setName("TMSJACONCOMVAR");
                fr20.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr20=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr20.setFoco();  }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr20.getEventoTeclado()!=null){
                                System.out.println("Evento teclado: "+fr20.getEventoTeclado().getKeyCode());
                            System.out.println("isControlDown: "+fr20.getEventoTeclado().isControlDown());
                            if(fr20.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr20.getEventoTeclado().isControlDown()){
                                muestraSiguienteJIF(fr20.getName());
                                fr20.getEventoTeclado().consume();
                                fr20.setEventoTeclado(null);
                            }
                            return;
                        }
                        appPrincipal.requestFocus();
                    }
                    public void internalFrameOpened(InternalFrameEvent e) { }
                });
                dskPrincipal.add(fr20,2);
                fr20.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr20.setVisible(true);
                fr20.requestFocus();
            }

            if(!encontrada){
                new jdlg_advertencia("¡La funcion "+Tabla[indicesel][4].toString().toUpperCase()+" no se reconoce! ","","Error de configuracion").setVisible(true);
                return;
            }


        }//Funcion configurada
      }
    }
    
    /*private void iAppInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
        this.requestFocusInWindow();
    }
    
    private void iAppKeyPressed(KeyEvent evt) {
        if(evt.getKeyCode()==KeyEvent.VK_F4){ iApp.dispose(); this.requestFocusInWindow(); }
    }*/
    
    // CONSTRUYE EL MENU A PARTIR DEL PERFIL ESPECIFICADO EN MenuId
    private void cargaMenuDinamico(){
        Vector Registros;
        int h, i, j;
        Vector vAux1 = new Vector();
        vAux1 = (Vector) sesionGeneralFacateRemote.ctd_menus_Nivel1(this.UsuarioId);        
        if(vAux1.size()==0){
            JOptionPane.showMessageDialog(null,"El usuario no tiene asignado un perfil correcto.\nContacte al administrador del sistema."
                    ,"¡Error de inicio de sesión!",JOptionPane.ERROR_MESSAGE);
            this.dispose();
            //return;
            System.exit(0);
        }
        Vector vAux2 = new Vector();
        Object[][] ctd_perfil_menusN1 = new Object[vAux1.size()][3];
        h=0;
        for(i=0; i<vAux1.size(); i++){
            for(j=0; j<3; j++){
                vAux2=(Vector) vAux1.get(i);
                ctd_perfil_menusN1[i][j] = vAux2.get(j);
            }
            h+=Integer.valueOf(ctd_perfil_menusN1[i][2].toString());
        }
        //System.out.println("acheeee "+h);
        JMenuItem menuItem;
        indice=0;
        contador=0;
        Tabla = new Object[h+1][7];
        for(h=0; h<ctd_perfil_menusN1.length; h++){
            //System.out.println("contador "+contador);
            Registros = new Vector();
            
            //System.out.println("MENU_ID"+ctd_perfil_menusN1[h][0]);
            Registros = (Vector) sesionGeneralFacateRemote.ArbolFunciones(Integer.valueOf(ctd_perfil_menusN1[h][0].toString())); // CONSULTA MENU DEL PERFIL
            if(Registros.size()==0){
                JOptionPane.showMessageDialog(null,"El usuario no tiene asignado un menu correcto.\nContacte al administrador del sistema."
                        ,"¡Error de inicio de sesión!",JOptionPane.ERROR_MESSAGE);
                this.dispose();
                //return;
                System.exit(0);
            }
            Vector Registro = new Vector();
            for(i=contador; i<Registros.size()+contador; i++)
                for(j=0; j<7; j++){
                    Registro=(Vector) Registros.get(i-contador);
                    if(j==0) Tabla[i][0] = i;
                    else{
                        if(j==6) Tabla[i][6] = ctd_perfil_menusN1[h][0]; // menu_id
                        else Tabla[i][j] = Registro.get(j);
                    }
                }
            
            // opcion salir
                for(j=0; j<7; j++){
                    if(j==0) Tabla[i][0] = i;
                    else Tabla[i][j]=0;
                }
            
            Registros = null; Registro = null;
            // ESTRUCTURA TABLA:
            /*for(i=0; i<Tabla.length; i++){
                System.out.println("Nombre Clase: "+Tabla[i][4]);
                System.out.println("Nombre Ruta Base Jar: "+Tabla[i][5]);
            }*/
            /*
            Tabla[i][4] PANTALLA_NOMBRE
            Tabla[i][5] RUTA_BASE
            */
            /* MENU DINAMICO*/
            menuItem = new JMenuItem(String.valueOf(ctd_perfil_menusN1[h][1]), new ImageIcon(AppPrincipal.class.getResource("/tmstrafico/images/perfil.gif")));
            menuItem.setHorizontalTextPosition(JMenuItem.RIGHT);
            menuItem.setName(String.valueOf(ctd_perfil_menusN1[h][1]));
            popMenuPrincipal.add(menuItem);
            //popMenuPrincipal.addSeparator();
            
            menu_recursivo(null);
            popMenuPrincipal.addSeparator();
            popMenuPrincipal.addSeparator();
        }    
        menuItem = new JMenuItem("Salir");
        menuItem.addActionListener(this);
        menuItem.setName("Salir");
        popMenuPrincipal.add(menuItem);
        /* FIN MENU DINAMICO*/
    }
    
    // FUNCION RECURSIVA PARA CONSTRUIR MENU, SUBMENUS Y ELEMENTOS DE MENU
    private void menu_recursivo(JMenu SubMenu){
        JMenu nuevo;
        while(Integer.valueOf(Tabla[contador][1].toString())!=0){
            if(Integer.valueOf(Tabla[contador][1].toString())>Integer.valueOf(Tabla[contador+1][1].toString())) {
                if(Integer.valueOf(Tabla[contador][1].toString())==1) agregaMenuItem_Nivel1();
                else agregaMenuItem_Niveles(SubMenu);
                contador++;
                return;
            }
            if(Integer.valueOf(Tabla[contador][1].toString())<Integer.valueOf(Tabla[contador+1][1].toString())) {
                if(Integer.valueOf(Tabla[contador][1].toString())==1) nuevo = agregaSubMenu_Nivel1();
                else nuevo = agregaSubMenu_Niveles(SubMenu);
                contador++; menu_recursivo(nuevo);
            }
            else{
                if(Integer.valueOf(Tabla[contador][1].toString())==1) agregaMenuItem_Nivel1();
                else agregaMenuItem_Niveles(SubMenu);
                contador++;
            }
        }
        return;
    }
    // FUNCIONES PARA CREAR SUBMENUS Y ELEMENTOS DE MENU
    private void agregaMenuItem_Nivel1(){
        //System.out.println("NIVEL "+ Integer.valueOf(Tabla[contador][1].toString())+"   pos: "+Integer.valueOf(Tabla[contador][0].toString()));
        JMenuItem menuItem = new JMenuItem(Tabla[contador][2].toString());
        menuItem.addActionListener(this);
        menuItem.setName(Tabla[contador][0].toString());
        popMenuPrincipal.add(menuItem);
    }
    
    private JMenu agregaSubMenu_Nivel1(){
        JMenu subMenuX = new JMenu(Tabla[contador][2].toString());
        popMenuPrincipal.add(subMenuX);
        return subMenuX;
    }
    
    private JMenu agregaSubMenu_Niveles(JMenu subMenuA){
        JMenu subMenuX = new JMenu(Tabla[contador][2].toString());
        subMenuA.add(subMenuX);
        return subMenuX;
    }
    
    private void agregaMenuItem_Niveles(JMenu subMenuX){
        System.out.println("NIVEL "+ Tabla[contador][2].toString()+"   pos: "+Tabla[contador][0].toString());
        JMenuItem menuItem = new JMenuItem(Tabla[contador][2].toString());
        menuItem.addActionListener(this);
        menuItem.setName(Tabla[contador][0].toString());
        if(subMenuX==null) System.out.println("subMenuX null");
        subMenuX.add(menuItem);
    }
    
    private void muestraSiguienteJIF(String nombreJIFActual){
        int i;
        for(i=0; i<dskPrincipal.getAllFrames().length; i++)
            if(dskPrincipal.getAllFrames()[i].getName().equals(nombreJIFActual)) break;
        i = (i+1)%dskPrincipal.getAllFrames().length;
        String nombreSigJIF = dskPrincipal.getAllFrames()[i].getName();
        if(nombreSigJIF.equals("TMSJAPSD")){ dskPrincipal.setSelectedFrame(fr1); try { fr1.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJARPT")){ dskPrincipal.setSelectedFrame(fr2); try { fr2.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJARETARMON")){ dskPrincipal.setSelectedFrame(fr3); try { fr3.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAACCUBI")){ dskPrincipal.setSelectedFrame(fr4); try { fr4.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAOFS")){ dskPrincipal.setSelectedFrame(fr5); try { fr5.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAGENCOR")){ dskPrincipal.setSelectedFrame(fr6); try { fr6.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJRSUST")){ dskPrincipal.setSelectedFrame(fr7); try { fr7.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAINC")){ dskPrincipal.setSelectedFrame(fr8); try { fr8.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAGUA")){ dskPrincipal.setSelectedFrame(fr9); try { fr9.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAMODTAR")){ dskPrincipal.setSelectedFrame(fr10); try { fr10.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJACAMEDOPER")){ dskPrincipal.setSelectedFrame(fr11); try { fr11.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAXRPT")){ dskPrincipal.setSelectedFrame(fr12); try { fr12.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
//        if(nombreSigJIF.equals("TMSJACALL")){ dskPrincipal.setSelectedFrame(fr13); try { fr13.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJACONOCUP")){ dskPrincipal.setSelectedFrame(fr14); try { fr14.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJALTPA")){ dskPrincipal.setSelectedFrame(fr15); try { fr15.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAVBINT")){ dskPrincipal.setSelectedFrame(fr16); try { fr16.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAPSTR")){ dskPrincipal.setSelectedFrame(fr17); try { fr17.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJABL")){ dskPrincipal.setSelectedFrame(fr18); try { fr18.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJABL")){ dskPrincipal.setSelectedFrame(fr19); try { fr19.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }

    }
    
    public Object[] queryAS(){
        Vector q = sesionGeneralFacateRemote.queryAS();
        if(q==null || q.size()==0) return null;
        Object[] x = new Object[2];
        Vector z = new Vector();
        for(int i=0; i<q.size(); i++){
                z = (Vector) q.get(i);
                if(z.get(0).toString().equals("P_AS_IP"))
                        x[0]=z.get(1).toString();
                else
                        x[1]=z.get(1).toString();
        }   
        return x;
    }
}
