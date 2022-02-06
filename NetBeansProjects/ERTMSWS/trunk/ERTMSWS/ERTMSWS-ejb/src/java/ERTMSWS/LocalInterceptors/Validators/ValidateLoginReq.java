/*
 * ValidateLoginReq.java
 *
 * Created on 21 de junio de 2010, 10:40 AM
 *
 * Interceptor diseñado para la validación de los parámetros provistos en la clase:
 * - LoginReq 
 * para el método:
 * - getLogin(LoginReq loginReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.Login.LoginReq;
import java.util.Date;
import java.lang.reflect.Field;
import tms_encriptacion.EncriptarMD5;
/**
 *
 * @author opalafox
 */
public class ValidateLoginReq extends MainValidator{
    private int usuarioID;
    private String usuarioNumero;
    private String usuarioContrasena;
    private String nombreEquipo;
    private String nombreCaja;
    private String direccionIP;
    private String direccionMAC;
    private int autorizadoPor;
    private String sucursalClave;
    private String canalVenta;
    private Date fechaCreacion;
    
    /** Creates a new instance of ValidateLoginReq */
    public ValidateLoginReq() {
    }
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando LoginReq: ");
        
        
        boolean validado = false;
        
        Object[] parameters = ic.getParameters();
        LoginReq loginReq = (LoginReq)parameters[0];
        try {
            System.out.println("loginReq.getUsuarioContrasena(): "+loginReq.getUsuarioContrasena());
                loginReq.setUsuarioContrasena(new EncriptarMD5().encriptar(loginReq.getUsuarioContrasena()));
            } catch (Exception ex) {
                loginReq.setUsuarioContrasena(ex.getMessage());
            }
        
        this.printParameters(loginReq);
        
        usuarioID = loginReq.getUsuarioId();
        usuarioNumero = loginReq.getUsuarioNumero();
        usuarioContrasena = loginReq.getUsuarioContrasena();
        nombreEquipo = loginReq.getNombreEquipo();
        nombreCaja = loginReq.getNombreCaja();
        direccionIP = loginReq.getDireccionIP();
        direccionMAC = loginReq.getDireccionMAC();
        autorizadoPor = loginReq.getAutorizadoPor();
        sucursalClave = loginReq.getSucursalClave();
        canalVenta = loginReq.getCanalVenta();
        fechaCreacion = loginReq.getFechaCreacion();
       
        
        // Realizar procedimiento de validación
        
            
        
        if (this.checkRequired()){
           // System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
             //   System.out.println("Valores opcionales correctos");
                
                System.out.println("-- Interceptor -- LoginReq correcto: R0");
            else
                //System.out.println("Error en opcionales, valores por defecto colocados");
                System.out.println("-- Interceptor -- LoginReq correcto: R");
           // System.out.println("-- Interceptor -- LoginReq correcto ");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: LoginReq incorrecto ");
        }
        
        
        return ic.proceed();
    }
    
    private boolean isValidNombreEquipo(String nEquipo)throws Exception{
        //Añadir código de validación
        if (nEquipo != null){
            if (this.isValidLength(nEquipo,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"NombreEquipo"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"NombreEquipo"));    
    }
    private boolean isValidNombreCaja(String nCaja)throws Exception{
        //Añadir código de validación
        if (nCaja != null){
            if (this.isValidLength(nCaja,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"NombreCaja"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"NombreCaja"));    
    }
    private boolean isValidDireccionIP(String dIP)throws Exception{
        //Añadir código de validación
        if (dIP != null){
            if (this.isValidLength(dIP,15))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"DireccionIP"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"DireccionIP"));    
    }
    private boolean isValidDireccionMAC(String dMAC)throws Exception{
        //Añadir código de validación
        
        if (dMAC != null){
            if (this.isValidLength(dMAC,17))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"DireccionMAC"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"DireccionMAC"));    
    }
    private boolean isValidSucursalClave(String sClave)throws Exception{
        //Añadir código de validación
        if (sClave != null){
            if (this.isValidLength(sClave,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"SucursalClave"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"SucursalClave"));    
    }
    private boolean isValidFechaCreacion(Date fCreacion)throws Exception{
        //Añadir código de validación
        if (fCreacion != null){
            return true;
        }else
            throw new Exception (this.getErrorMessage(NULO,"FechaCreacion"));
       
        
    }
    public boolean checkRequired()throws Exception{
        if ( (this.isValidDireccionIP(direccionIP)) &&
                (this.isValidDireccionMAC(direccionMAC))&&
                (this.isValidFechaCreacion(fechaCreacion)) &&
                (this.isValidNombreCaja(nombreCaja))&&
                (this.isValidNombreEquipo(nombreEquipo))&& 
                (this.isValidSucursalClave(sucursalClave))&&
                (this.isValidUsuarioNumero(usuarioNumero))&&
                (this.isValidUsuarioContrasena(usuarioContrasena)))
            return true;
        else
            return false;
                 
                   
    }
    private boolean isValidUsuarioID(int uID) throws Exception{
        if (uID >= 0){
            return true;
        }else
            throw new Exception(this.getErrorMessage(NEGATIVO,"UsuarioID"));
    }
    private boolean isValidAutorizadoPor(int aPor) throws Exception{
        if (aPor >= 0){
            return true;
        }else
            throw new Exception(this.getErrorMessage(NEGATIVO,"AutorizadoPor"));
        
    }
    private boolean isValidCanalVenta(String cVenta)throws Exception{
         if (cVenta != null){
            if (this.isValidLength(cVenta,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"CanalVenta"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"CanalVenta"));    
    }
    public boolean checkOptional()throws Exception{
        /* hacer modificación de parámetros para que se devuelvan los valores
         * por defecto en caso de error
         */
        
        
        if ( (this.isValidAutorizadoPor(autorizadoPor)) &&
                (this.isValidCanalVenta(canalVenta))&&
                (this.isValidUsuarioID(usuarioID)))
            return true;
        else
            return false;
       
        
         
      
    }

    
}
