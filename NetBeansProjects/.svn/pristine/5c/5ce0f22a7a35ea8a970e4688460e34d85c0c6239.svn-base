/*
 * AppPrincipal.java
 *
 * Created on 7 de agosto de 2007, 06:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eausuariofuncion;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import tms_cortesterminal.JIFCortesTerminal;
import tms_cortetaquilla.JIFCorteTaquilla;
import tms_cortetaquilla.util.PcInfo;
import tms_desktop.MDIDesktopPane;
import tms_ejecutarreportes.TMSEjecutarReportesMonitor;
import tms_global_login.TMSLoginGlobalManagedBean;
import tms_global_login.jdlg_advertencia;
import tms_global_login.jdlg_bloqueo;
import tms_global_login.jdlg_error;
import tms_global_login.login_main;
import tms_insprecol.JIFInspRecol;
import tms_liberar_bol_no_disp.JIFLiberar_Boletos;
//import tms_accesoubicaciones.JIFAccesoUbicaciones;
//import corterecaudacion.JIFCorteRecaudacion;
//import tms_guardias.JIFGuardia;
//import tms_incidencias.JIFIncidencia;
//import tms_insprecol.JIFInspRecol;
//import tms_ofertasservicios.JIFOfertasServicios;
//import tms_psd.JIFPSD;
//import tmsactividadesadicionales.JIFrameActividadesAdicionales;
//import tmslecturadatafare.JIFLecturaDatafare;
//import tmspuertas.JIFPuertas;
//import tmsrecaudacion.JIFRecaudacion;
//import tmsreglassustitucion.JIFRaglasSustitucion;
//import tmsroles.JIFCFNDatosIniciales;
import tms_tarjetasnopermitidas.JIFTarjetasNoPermitidas;
import tms_venta.JIFVenta;
import tmsacumularestrellas.JIFAcumulaEstrellas;
import tmscodigobarras.JIF_ValidarCodigoBarras;
import tmsconsultaocupacion.JIFConsultaOcupacion;
//import tmsconsultaocupacion.JIFConsultaOcupacion;
import tmsfacturarcodigobarras.JIF_Facturar;
import tmslistapasajeros.JIF_ListaPasajeros;
import tmsreportes.gui.JIFReportesVenta;
import xertms.solicitud.TmsSesionGeneralFacadeRemote;

/**
 *
 * @author ocruz
 */
public class AppPrincipal extends JFrame implements ActionListener{
    private TmsSesionGeneralFacadeRemote sesionGeneralFacateRemote;
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
                new URL(""+jar),
                //new URL ("http://case.iti.upv.es/Monkey.zip"),
                //new URL ("http://torpedo.upv.es/luis/norte/"),
                // new File ("misClases.jar").toURL()
            };
            URLClassLoader cargador = new URLClassLoader(listaURLs);
            c = cargador.loadClass(clase);
            return cargador.loadClass(clase);
            //MiClase mc = (clase) c.newInstance();
        } catch (MalformedURLException e) {
            // cargar la clase de otra manera o error
        }
        
        return c;
        
    }
    
    
    
    // CONEXION A LA SOLICITUD GENERAL
    private TmsSesionGeneralFacadeRemote lookupTmsSesionGeneralFacade() {
        try {
            Context c = new InitialContext();
            return (TmsSesionGeneralFacadeRemote) c.lookup("xertms.solicitud.TmsSesionGeneralFacadeRemote");
        } catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    // METODO INIT
    private void jbInit() throws Exception {
        PcInfo estaPC = new PcInfo();
        this.setTitle("TMS Venta v2.8.8 "+nombreTerminal+"- Administrador de Aplicaciones - " + this.nombreUsuario+" - IP: "+estaPC.getHostAddress());
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
        jLabel1.setIcon(new javax.swing.ImageIcon(AppPrincipal.class.getResource("/eausuariofuncion/images/fondoPantallaLanzadora.gif")));//"C:\\NetBeansProyects\\TMSCorteTaquilla\\TMSCorteTaquilla-app-client\\src\\java\\tms_cortetaquilla\\images\\bien.gif"));
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
        } else{
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
        if(dskPrincipal.getAllFrames().length>0) return;
        JMenuItem source = (JMenuItem)(e.getSource());
        if(source.getName().equals("Salir")){
            try{
                TMSLoginGlobalManagedBean ventaLoginCnx = new TMSLoginGlobalManagedBean();
                ventaLoginCnx.getSesionesGlobalFacadeRemote().UpdateSesion(this.SesionId, ventaLoginCnx.FechaServidor());
                this.appInicial.setVisible(true);
                this.dispose(); // FINALIZA APLICACION
            }catch(Exception ex){
                new jdlg_error("¡Error del Sistema! ","El Sistema se cerrará completamente","Error del Sistema").setVisible(true);
                System.exit(0);
            }
        } else {
            Vector vestado= (Vector)sesionGeneralFacateRemote.buscarEstadoSesion(SesionId);
            String estado = vestado.get(0).toString();
            if(estado.equals("CERRADA")){
                new jdlg_error("¡La sesion es invaida!","El Sistema se cerrará automáticamente","La sesion fue cerrada").setVisible(true);
                System.exit(0);
            }
            int indicesel = Integer.parseInt(source.getName());
        /*System.out.println("Indice Seleccionado: "+indicesel);
        System.out.println("Nombre Clase: "+Tabla[indicesel][4]);
        System.out.println("Nombre Ruta Base Jar: "+Tabla[indicesel][5]);*/
            System.out.println("MENU_ID: "+Tabla[indicesel][6]);
            datosIniciales.set(4,Tabla[indicesel][6]);
            datosIniciales.set(5,objX[0].toString());
            datosIniciales.set(6,objX[1].toString());
            datosIniciales.set(7,Tabla[indicesel][2].toString().toUpperCase());
            System.out.println("Vector inicial: "+datosIniciales.toString());
            
            if(Tabla[indicesel][4].toString().equals("Nada"))// || Tabla[indicesel][5].toString().equals("Nada"))
                new jdlg_advertencia("¡La funcion no esta configurada! ","","Error de configuracion").setVisible(true);
            else {
                boolean encontrada = false;
                //JInternalFrame fr = null;
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAVTA")||Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACALL")) {
                    JIFVenta frv = new JIFVenta(datosIniciales);
                    if(!frv.getInicioGral())
                        return;
                    else {
                        encontrada = true;
                        dskPrincipal.add(frv,2);
                        frv.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        frv.setVisible(true);
                        frv.requestFocus();
                    }
                }
                
                //if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACUMEST")||Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACALL")) {
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACUMEST")) {
                    JIFAcumulaEstrellas frv = new JIFAcumulaEstrellas(datosIniciales);
                        encontrada = true;
                        dskPrincipal.add(frv,2);
                        frv.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        frv.setVisible(true);
                        frv.requestFocus();
                }                
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACTE")) {
                    encontrada = true;
                    JIFCorteTaquilla fr = new JIFCorteTaquilla(datosIniciales);
                    if(!fr.getInicioGral())
                        return;
                    else {
                        dskPrincipal.add(fr,2);
                        fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        fr.setVisible(true);
                        fr.requestFocus();
                    }
                }
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAXRPT")) {
                    encontrada = true;
                    TMSEjecutarReportesMonitor fr = new TMSEjecutarReportesMonitor(datosIniciales);
                    if(!fr.getInicioGral())
                        return;
                    else {
                        dskPrincipal.add(fr,2);
                        //fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        fr.setVisible(true);
                        fr.requestFocus();
                    }
                }
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAINR")) {
                    JIFInspRecol fr = new JIFInspRecol(datosIniciales);
                    if(!fr.getInicioGral())
                        return;
                    else {
                        encontrada = true;
                        dskPrincipal.add(fr,2);
                        fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        fr.setVisible(true);
                        fr.requestFocus();
                    }
                }
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACONOCUP")) {
                    encontrada = true;
                    JIFConsultaOcupacion fr = new JIFConsultaOcupacion(datosIniciales);
                    dskPrincipal.add(fr,2);
                    fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr.setVisible(true);
                    fr.requestFocus();
                }
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJALIBOL")) {
                    encontrada = true;
                    JIFLiberar_Boletos fr = new JIFLiberar_Boletos();
                    dskPrincipal.add(fr,2);
                    fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    fr.setVisible(true);
                    fr.requestFocus();
                }
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJACTET")) {
                    encontrada = true;
                    JIFCortesTerminal fr = new JIFCortesTerminal(datosIniciales);
                    if(!fr.getInicioGral())
                        return;
                    else {
                        dskPrincipal.add(fr,2);
                        fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        fr.setVisible(true);
                        fr.requestFocus();
                    }
                }
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAINRT")) {
                    tms_insprecolglobal.JIFInspRecol fr = new tms_insprecolglobal.JIFInspRecol(datosIniciales);
                    if(!fr.getInicioGral())
                        return;
                    else {
                        encontrada = true;
                        dskPrincipal.add(fr,2);
                        fr.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        fr.setVisible(true);
                        fr.requestFocus();
                    }
                }
                
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAFACT")) {
                    Vector vd = (Vector)this.sesionGeneralFacateRemote.queryDatosEsquemaLocal();
                    datosIniciales.set(4,vd.get(0).toString());
                    datosIniciales.set(5,vd.get(1).toString());
                    datosIniciales.set(6,vd.get(2).toString());
                    JIF_Facturar frv = new JIF_Facturar(datosIniciales);
                    encontrada = true;
                    dskPrincipal.add(frv,2);
                    frv.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    frv.setVisible(true);
                    frv.requestFocus();
                }
                
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJALTPA")) {
                    JIF_ListaPasajeros frv = new JIF_ListaPasajeros();
                    encontrada = true;
                    dskPrincipal.add(frv,2);
                    frv.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    frv.setVisible(true);
                    frv.requestFocus();
                }
                
                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSJAVBINT")) {
                    JIF_ValidarCodigoBarras frv = new JIF_ValidarCodigoBarras(); 
                    encontrada = true;
                    dskPrincipal.add(frv,2);
                    frv.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                    frv.setVisible(true);
                    frv.requestFocus();
                }

                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSREPVTA")) {
                    JIFReportesVenta frv = new JIFReportesVenta(datosIniciales);
                        encontrada = true;
                        dskPrincipal.add(frv,2);
                        frv.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        frv.setVisible(true);
                        frv.requestFocus();
                }

                if(Tabla[indicesel][4].toString().toUpperCase().equals("TMSTARNOPER")) {
                    JIFTarjetasNoPermitidas frv = new JIFTarjetasNoPermitidas(datosIniciales);
                        encontrada = true;
                        dskPrincipal.add(frv,2);
                        //frv.setSize(dskPrincipal.getWidth(), dskPrincipal.getHeight());
                        frv.setVisible(true);
                        frv.requestFocus();
                }

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
            menuItem = new JMenuItem(String.valueOf(ctd_perfil_menusN1[h][1]), new ImageIcon(AppPrincipal.class.getResource("/eausuariofuncion/images/perfil.gif")));
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
            } else{
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
