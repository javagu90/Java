 /*
 * TMSLoginGlobalManagedBean.java
 *
 * Created on 22 de mayo de 2007, 05:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmstraficomain;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import tms_encriptacion.EncriptarMD5;
import tmstrafico.entidad.TmsAuditoriaTbl;
import tmstrafico.entidad.TmsUsuariosTbl;
import tmstrafico.excepcion.UsuarioNoEncontradoException;
import tmstrafico.solicitud.TmsSesionGeneralTraficFacadeRemote;


/**
 *
 * @author imunoz
 */
public class TMSLoginGlobalManagedBean {
    
    /**
     * Creates a new instance of TMSLoginGlobalManagedBean
     */
    
    public TMSLoginGlobalManagedBean(){
        SesionesGlobalFacadeRemote = lookupSesionGlobal();
        Vector TER = (Vector)getSesionesGlobalFacadeRemote().queryBuscaIdTerminal();
            idTerminal = TER.get(0).toString();
            String te = getIdPrefijoTerminal();
            if(getIdPrefijoTerminal().length()<3)
            {
               for(int i=getIdPrefijoTerminal().length(); i<3;i++)
                   te = te+"0";
            }
            if(getIdPrefijoTerminal().length()>=3)
             te = getIdPrefijoTerminal().substring(0,2);
            idTerminal = te;
     
            Vector NTER = (Vector)getSesionesGlobalFacadeRemote().queryBuscaNombreTerminal();
            nombreTerminal = NTER.get(0).toString();
            
            Vector NESP = (Vector)getSesionesGlobalFacadeRemote().queryBuscaNombreEsquema();
            nombreEsquema = NESP.get(0).toString();          

    }
/***********************************/
    private TmsSesionGeneralTraficFacadeRemote lookupSesionGlobal() {
          try {
            Context c = new InitialContext();
            return (TmsSesionGeneralTraficFacadeRemote) c.lookup("tmstrafico.solicitud.TmsSesionGeneralTraficFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
      }
/******************************************************************************/

    public Date FechaServidor(){
        Date fecha;
        Vector x = (Vector) getSesionesGlobalFacadeRemote().fechaServidor();
        if(x==null) return null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(x.get(0).toString());
        }catch(ParseException ex) {
            ex.printStackTrace();
            fecha = null;
        }
        return fecha;
    }
   
    /*
     *Verifica que el usuario esta hablilitado, que no este bloequeado 
     *Tablas involucradas
     *TMS_USURIOS_TBL, TMS_USURIOS_PERFILES_TBL
     */
    public int esPwdValido(String usuarioNumero,String pwdUsuario){
       PcInfo estaTerminal = new PcInfo();
        try{
            usuario = getSesionesGlobalFacadeRemote().getUsuarioPorNumero(usuarioNumero);
            numUsuarioPerfiles = getSesionesGlobalFacadeRemote().getPerfilesUsuario(usuarioNumero);
            Vector vterlocal = (Vector)getSesionesGlobalFacadeRemote().queryBuscaTerminalLocal();
            String terminalLocal = vterlocal.get(0).toString();
                    
            if(usuario.getEstado()!=null)
            {
                if(usuario.getEstado().toUpperCase().equals("S"))
                    return 4;
            }
            if(usuario.getEstado()!=null)
            {
                if(usuario.getEstado().toUpperCase().equals("B"))
                    return 5;
            }
            if(usuario.getUbicacionId()== null)
                return 6;
            if(!terminalLocal.equals(usuario.getUbicacionId().toString()))
                 return 6;
            /***** Sesion ******/
            this.setUsuarioId(usuario.getUsuarioId().longValue());
            this.setUsuarioNumero(usuario.getUsuarioNumero());
            this.setUsuarioNombre(usuario.getUsuarioNombre());
            this.setUsuarioContrasena(usuario.getContrasenaEncriptada());
        }catch(EJBException rex){
            return 1; // PROBLEMA DE RED
        }catch(UsuarioNoEncontradoException ex){
            ex.getMessage();
            return 3; // USUARIO INVALIDO
        }
        try{
            if(evaluarContrasena(usuario,pwdUsuario))
            {
                usuario.setContadorIntentos((long)0);
                usuario.setReplicacionOrigen(nombreEsquema);
                getSesionesGlobalFacadeRemote().editUsuario(usuario);   
                return 0;
            }
            else
            return 2;
        }catch(Exception ex){   
            ex.printStackTrace();
        }
        if((usuario.getContadorIntentos()+1) >= 4) //verifica que el numero de intentos de acceso no sea mayor al permitido
        {
           JOptionPane.showMessageDialog(new JDialog(),"               ¡La cuenta ha sido suspendida!\nFavor de  contactar al Administrador del Sistema","Usuario Suspendido",JOptionPane.ERROR_MESSAGE);
           //Se cumplio el numero de intentos de acceso permitidos, se suspende la cuenta del ususrio usuario
            usuario.setEstado("S");
            usuario.setReplicacionOrigen(nombreEsquema);
            getSesionesGlobalFacadeRemote().editUsuario(usuario);   
           //Agrega un registro de logueo en la tabla de auditoria
           TmsAuditoriaTbl auditoria = new TmsAuditoriaTbl();
           auditoria.setUsuarioId(Long.valueOf(""+usuario.getUsuarioId()));
           auditoria.setNombreEquipo(estaTerminal.getHostName().toUpperCase());
           auditoria.setFuncionNumero("1000");
           auditoria.setFecha(FechaServidor());
           auditoria.setEstadoProceso("FRACASO");
           auditoria.setDescripcionProceso("Es el intento de acceso numero "+(usuario.getContadorIntentos() + 1)+". Se bloqueo el usuario");
           SesionesGlobalFacadeRemote.createAuditoria(auditoria, getIdPrefijoTerminal());
           //Setea en 0 el contador de intentos para que cuando se desbloquee al usuario comience el conteo
           usuario.setContadorIntentos((long)0);
           usuario.setReplicacionOrigen(nombreEsquema);
           getSesionesGlobalFacadeRemote().editUsuario(usuario);   
           //como ya esta bloquequeado el usuario el sistema se cierra
           System.exit(0);
        }
        else
        {
            long num = usuario.getContadorIntentos() + 1;
            usuario.setContadorIntentos(num);
            usuario.setReplicacionOrigen(nombreEsquema);
            getSesionesGlobalFacadeRemote().editUsuario(usuario);   
           //Agrega un registro fracaso de logueo en la tabla de auditoria
           TmsAuditoriaTbl auditoria = new TmsAuditoriaTbl();
           auditoria.setUsuarioId(Long.valueOf(""+usuario.getUsuarioId()));
           auditoria.setNombreEquipo(estaTerminal.getHostName().toUpperCase());
           auditoria.setFuncionNumero("1000");
           auditoria.setFecha(FechaServidor());
           auditoria.setEstadoProceso("FRACASO");
           auditoria.setDescripcionProceso("Es el intento de acceso numero "+usuario.getContadorIntentos());
           SesionesGlobalFacadeRemote.createAuditoria(auditoria, getIdPrefijoTerminal());
        }
        return 7;
    }
   
    
    /*
     *Verifica la vigencia de la contaseña
     *Tablas involucradas
     *TMS_USURIOS_TBL
     */
    private boolean evaluarContrasena(TmsUsuariosTbl usuarioReg,String pwdUsuario){
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        Date fechaHoy = FechaServidor();//SesionesGlobalFacadeRemote.fechaHoraServidor();
        Long fechaInicial;
        Long fechaFinal;
        System.out.println("Entra a evaluar contraseña...");    
        //Verifica la fecha de Vigencia de Usuario
        fechaInicial = usuarioReg.getFechaInicial().getTime();
        
        if (usuario.getFechaFinal() == null)
            fechaFinal = fechaHoy.getTime();
        else
            fechaFinal = usuario.getFechaFinal().getTime();
        try {
            if(usuarioReg.getContrasenaEncriptada().equals(pwdEnc.encriptar(pwdUsuario)) && 
                    (fechaHoy.getTime() >= fechaInicial) && 
                    (fechaHoy.getTime() <= fechaFinal) ){
                return true;                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;        
    }
    
    /*
     *renueva la contraseña
     *Tablas involucradas
     *TMS_USURIOS_TBL
     */            
    public boolean cambiarContrasena(){
        Date fechaHoy = FechaServidor();//SesionesGlobalFacadeRemote.fechaHoraServidor();
        boolean cambiarPwd = false;
        TmsUsuariosTbl usuarioReg = usuario;
                
        Date conFecha = usuarioReg.getContrasenaFecha();
        BigInteger conAccesosFaltan = usuarioReg.getContrasenaAccesosFaltan();
        BigInteger conLimiteAccesos = usuarioReg.getContrasenaLimiteAccesos();
        BigInteger conLimiteDias = usuarioReg.getContrasenaLimiteDias();
        Date fechaCaduca = getSesionesGlobalFacadeRemote().fechaCaducaPwd(usuarioReg.getUsuarioId().toString());
        
        if (conFecha == null){
            cambiarPwd = true;
        }else if (conAccesosFaltan == null){
            if (fechaCaduca.getTime() < fechaHoy.getTime() && conLimiteDias != null){
                cambiarPwd = true;
            }
         }else{ 
            if(conAccesosFaltan.intValue() == 0){
                cambiarPwd = true;
            }               
         }

        
        if(!cambiarPwd)
            modificarAccesosFaltan(usuarioReg);//agregar un acceso
        
        return cambiarPwd;        
    }
    
    //modifica en la base de datos los accesos que faltan para que caduque la contraseña
    private void modificarAccesosFaltan(TmsUsuariosTbl usuarioReg) {
        Date hoy = FechaServidor();//SesionesGlobalFacadeRemote.fechaHoraServidor();
        
        int nvoAccesos = 0;
        try{
            if (usuarioReg.getContrasenaAccesosFaltan() != null){
                nvoAccesos = (usuarioReg.getContrasenaAccesosFaltan().intValue() - 1);
                usuarioReg.setContrasenaAccesosFaltan(new BigInteger(String.valueOf(nvoAccesos)));
            }else
                usuarioReg.setContrasenaAccesosFaltan(null);

            usuarioReg.setUltimaActualizacionPor(usuarioReg.getUsuarioId().toBigInteger());
            usuarioReg.setUltimaFechaActualizacion(hoy);
            usuarioReg.setReplicacionOrigen(nombreEsquema);
            getSesionesGlobalFacadeRemote().editUsuario(usuarioReg);
        }catch (EJBException ex){
            System.out.println("Error en actualizar accesosFaltan");
            ex.printStackTrace();
        }
    }

    public boolean actualizarContrasena(String nuevaContrasena){
        Date hoy = FechaServidor();//SesionesGlobalFacadeRemote.fechaHoraServidor();
        TmsUsuariosTbl usuarioReg = usuario;
        try{
            usuarioReg.setContrasenaEncriptada(nuevaContrasena);
            usuarioReg.setContrasenaFecha(hoy);
            usuarioReg.setContrasenaAccesosFaltan(usuarioReg.getContrasenaLimiteAccesos());
            usuarioReg.setUltimaActualizacionPor(usuarioReg.getUsuarioId().toBigInteger());
            usuarioReg.setUltimaFechaActualizacion(hoy);
                //se agrega la terminal donde se esta cambiando el password
            Vector NESQL = (Vector)getSesionesGlobalFacadeRemote().queryBuscaEsquemaLocal();
            String EsquemaLocal = NESQL.get(0).toString();
            usuarioReg.setAdicional1(EsquemaLocal);
            usuarioReg.setReplicacionOrigen(nombreEsquema);
            getSesionesGlobalFacadeRemote().editUsuario(usuarioReg);
        }catch (EJBException ex){
            System.out.println("Error en actualizar contraseña");
            ex.printStackTrace();
            return false;                    
        }
       return true;
    }
   
    public boolean validarClaveSuper(String usuarioNumero,String usuarioPwd) {
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        boolean valido = false;
        try {
                valido = getSesionesGlobalFacadeRemote().esUsuarioSupervisor(usuarioNumero,pwdEnc.encriptar(usuarioPwd));
        } catch (UsuarioNoEncontradoException exu) {
            System.out.println(exu.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return valido;
    }
    
    
    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long pUsuarioId) {
        this.usuarioId = pUsuarioId;
    }
    
    public String getUsuarioNumero() {
        return usuarioNumero;
    }

    public void setUsuarioNumero(String pUsuarioNumero) {
        this.usuarioNumero = pUsuarioNumero;
    }
    
    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String pUsuarioNombre) {
        this.usuarioNombre = pUsuarioNombre;
    }
    
    public String getUsuarioContrasena() {
        return usuarioContrasena;
    }

    public void setUsuarioContrasena(String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }
    
    public int getNumUsuarioPerfiles(){
        return this.numUsuarioPerfiles;
    }
    
    private  TmsSesionGeneralTraficFacadeRemote SesionesGlobalFacadeRemote;
    private TmsUsuariosTbl usuario;
    private int numUsuarioPerfiles;
    private long usuarioId;
    private String usuarioContrasena;
    private String usuarioNumero;
    private String usuarioNombre;
    private String idTerminal;
    private String nombreTerminal;
    private String nombreEsquema;

    public String getIdPrefijoTerminal() {
        return idTerminal;
        
    } 
    
    public String getNombreTerminal() {
        return nombreTerminal;
    }

    public TmsSesionGeneralTraficFacadeRemote getSesionesGlobalFacadeRemote() {
        return SesionesGlobalFacadeRemote;
    }
}