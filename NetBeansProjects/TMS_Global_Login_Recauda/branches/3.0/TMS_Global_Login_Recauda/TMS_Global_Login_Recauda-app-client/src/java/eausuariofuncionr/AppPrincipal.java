/*
 * AppPrincipal.java
 *
 * Created on 7 de agosto de 2007, 06:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eausuariofuncionr;

import corterecaudacion.JIFCorteRecaudacion;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import reportesrecaudajasper.JIFReportesRecauda;
import tms_desktop.MDIDesktopPane;
//import tms_ejecutarreportes.TMSEjecutarReportesMonitor;
import tms_global_login_recaudar.PcInfo;
import tms_global_login_recaudar.TMSLoginGlobalManagedBean;
import tms_global_login_recaudar.jdlg_advertencia;
import tms_global_login_recaudar.jdlg_bloqueo;
import tms_global_login_recaudar.jdlg_error;
import tms_global_login_recaudar.login_main;
import tmsactividadesadicionales.JIFrameActividadesAdicionales;
import tmslecturadatafare.JIFLecturaDatafare;
import tmslecturaviaxer.JIFLecturaViaxer;
import tmsrecaudacion.JIFRecaudacion;
import xertmsr.solicitud.TmsSesionGeneralRecaudaFacadeRemote;

/**
 *
 * @author ocruz
 */
public class AppPrincipal extends JFrame implements ActionListener{
    private TmsSesionGeneralRecaudaFacadeRemote sesionGeneralFacateRemote;
    private JScrollPane jscpDesktop;
    private MDIDesktopPane dskPrincipal;
    private JInternalFrame iApp;
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
    private JIFrameActividadesAdicionales fr1;
    private JIFRecaudacion fr2;
    private JIFCorteRecaudacion fr3;
    private JIFReportesRecauda fr4;
    private JIFLecturaDatafare fr5;
    private JIFLecturaViaxer fr6;
    private JFrame appPrincipal = null;
    
        
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


   
    
    // METODO INIT
    private void jbInit() throws Exception {
        PcInfo estaPC = new PcInfo();
        this.setTitle("TMS Recauda  v2.7.6 "+nombreTerminal+"- Administrador de Aplicaciones - " + this.nombreUsuario+" - IP: "+estaPC.getHostAddress());
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
        jLabel1.setIcon(new javax.swing.ImageIcon(AppPrincipal.class.getResource("/eausuariofuncionr/images/fondoPantallaLanzadora.gif")));//"C:\\NetBeansProyects\\TMSCorteTaquilla\\TMSCorteTaquilla-app-client\\src\\java\\tms_cortetaquilla\\images\\bien.gif"));
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
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARAD"))                 
            {
                if(fr1!=null){
                    if(fr1.isIconifiable()) 
                        try { fr1.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }

                encontrada = true;
                fr1 = new JIFrameActividadesAdicionales(datosIniciales);
//                  JIFrameActividadesAdicionales fr = new JIFrameActividadesAdicionales(datosIniciales);
                  fr1.setName("TMSJARAD");
                    fr1.addInternalFrameListener(new InternalFrameListener() {
                        public void internalFrameActivated(InternalFrameEvent e) { }
                        public void internalFrameClosed(InternalFrameEvent e) { fr1=null; }
                        public void internalFrameClosing(InternalFrameEvent e) { }
                        public void internalFrameDeactivated(InternalFrameEvent e) { }
                        public void internalFrameDeiconified(InternalFrameEvent e) { fr1.setFoco(); }
                        public void internalFrameIconified(InternalFrameEvent e) {
                            if(fr1.getEventoTeclado()!=null){
                                if(fr1.getEventoTeclado().getKeyCode() == KeyEvent.VK_1 && fr1.getEventoTeclado().isControlDown()){
                                    muestraSiguienteJIF(fr1.getName());
                                    fr1.getEventoTeclado().consume();
                                    fr1.setEventoTeclado(null);
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
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARTR"))                 
            {
//                JIFRecaudacion fr = new JIFRecaudacion(datosIniciales);
//                if(!fr.getInicioGral())
//                  return;
//                else
//                {
//                  encontrada = true;
//                  dskPrincipal.add(fr,2);
//                  fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
//                  fr.setVisible(true);
//                  fr.requestFocus();
//                }
                if(fr2!=null){
                    if(fr2.isIconifiable())
                        try { fr2.setIcon(false); } catch (PropertyVetoException ex) { ; }
                    return;
                }
                
                encontrada = true;
                fr2 = new JIFRecaudacion(datosIniciales);
                if(!fr2.getInicioGral()){
                    fr2 = null;
                    return;
                }
                else
                {
                fr2.setName("TMSJARTR");
                fr2.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent e) { }
                    public void internalFrameClosed(InternalFrameEvent e) { fr2=null; }
                    public void internalFrameClosing(InternalFrameEvent e) { }
                    public void internalFrameDeactivated(InternalFrameEvent e) { }
                    public void internalFrameDeiconified(InternalFrameEvent e) { fr2.setFoco(); }
                    public void internalFrameIconified(InternalFrameEvent e) {
                        if(fr2.getEventoTeclado()!=null){
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
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARCR"))                 
            {
//                JIFCorteRecaudacion fr = new JIFCorteRecaudacion(datosIniciales);
//                encontrada = true;
//                dskPrincipal.add(fr,2);
//                fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
//                fr.setVisible(true);
//                fr.requestFocus();
                if(fr3!=null){
                    if(fr3.isIconifiable()) 
                        try { fr3.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }

                encontrada = true;
                fr3 = new JIFCorteRecaudacion(datosIniciales);
                  fr3.setName("TMSJARCR");
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
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARREP"))                 
            {
//                JIFReportesRecauda fr = new JIFReportesRecauda();
//                encontrada = true;
//                dskPrincipal.add(fr,2);
//                fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
//                fr.setVisible(true);
//                fr.requestFocus();
                if(fr4!=null){
                    if(fr4.isIconifiable()) 
                        try { fr4.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }

                encontrada = true;
                fr4 = new JIFReportesRecauda();
                  fr4.setName("TMSJARREP");
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
                  fr4.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                  fr4.setVisible(true);
                  fr4.requestFocus();
                
            }            
            
            
            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARECDAT"))                 
            {
                if(fr5!=null){
                    if(fr5.isIconifiable()) 
                        try { fr5.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr5 = new JIFLecturaDatafare(datosIniciales);
                if(!fr5.getInicioGral()){
                    fr5 = null;
                    return;
                }
                else
                {
                fr5.setName("TMSJARECDAT");
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
//                JIFLecturaDatafare fr = new JIFLecturaDatafare(datosIniciales);
//                if(!fr.getInicioGral())
//                  return;
//                else
//                {
//                  encontrada = true;
//                  dskPrincipal.add(fr,2);
//                  fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
//                  fr.setVisible(true);
//                  fr.requestFocus();
//                }
            }

            if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJARECVIAX"))
            {
                if(fr6!=null){
                    if(fr6.isIconifiable())
                        try { fr6.setIcon(false); } catch (PropertyVetoException ex) { }
                    return;
                }
                encontrada = true;
                fr6 = new JIFLecturaViaxer(datosIniciales);
                if(!fr6.getInicioGral()){
                    fr6 = null;
                    return;
                }
                else
                {
                fr6.setName("TMSJARECVIAX");
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

                dskPrincipal.add(fr6,2);
                fr6.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                fr6.setVisible(true);
                fr6.requestFocus();
                }
//                JIFLecturaDatafare fr = new JIFLecturaDatafare(datosIniciales);
//                if(!fr.getInicioGral())
//                  return;
//                else
//                {
//                  encontrada = true;
//                  dskPrincipal.add(fr,2);
//                  fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
//                  fr.setVisible(true);
//                  fr.requestFocus();
//                }
            }
            
//           if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAXRPT")) 
//            {
//                encontrada = true;
//                TMSEjecutarReportesMonitor fr = new TMSEjecutarReportesMonitor(datosIniciales);
//                if(!fr.getInicioGral())
//                  return;
//                else
//                {
//                    dskPrincipal.add(fr,2);
//                    //fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
//                    fr.setVisible(true);
//                    fr.requestFocus();
//                }
//            } 
            
            if(!encontrada){
                new jdlg_advertencia("¡La funcion "+Tabla[indicesel][4].toString().toUpperCase()+" no se reconoce! ","","Error de configuracion").setVisible(true);
                return;
            }


        }//Funcion configurada
      }
    }
    
    private void iAppInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
        this.requestFocusInWindow();
    }
    
    private void iAppKeyPressed(KeyEvent evt) {
        if(evt.getKeyCode()==KeyEvent.VK_F4){ iApp.dispose(); this.requestFocusInWindow(); }
    }
    
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
            menuItem = new JMenuItem(String.valueOf(ctd_perfil_menusN1[h][1]), new ImageIcon(AppPrincipal.class.getResource("/eausuariofuncionr/images/perfil.gif")));
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
        //System.out.println("NIVEL "+ Integer.valueOf(Tabla[contador][1].toString())+"   pos: "+Integer.valueOf(Tabla[contador][0].toString()));
        JMenuItem menuItem = new JMenuItem(Tabla[contador][2].toString());
        menuItem.addActionListener(this);
        menuItem.setName(Tabla[contador][0].toString());
        subMenuX.add(menuItem);
    }
    
    private void muestraSiguienteJIF(String nombreJIFActual){
        int i;
        for(i=0; i<dskPrincipal.getAllFrames().length; i++)
            if(dskPrincipal.getAllFrames()[i].getName().equals(nombreJIFActual)) break;
        i = (i+1)%dskPrincipal.getAllFrames().length;
        String nombreSigJIF = dskPrincipal.getAllFrames()[i].getName();
        if(nombreSigJIF.equals("TMSJARAD")){ dskPrincipal.setSelectedFrame(fr1); try { fr1.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJARTR")){ dskPrincipal.setSelectedFrame(fr2); try { fr2.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJARCR")){ dskPrincipal.setSelectedFrame(fr3); try { fr3.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJAACCUBI")){ dskPrincipal.setSelectedFrame(fr4); try { fr4.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
        if(nombreSigJIF.equals("TMSJARECDAT")){ dskPrincipal.setSelectedFrame(fr5); try { fr5.setIcon(false); } catch (PropertyVetoException ex) { ; } return; }
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
