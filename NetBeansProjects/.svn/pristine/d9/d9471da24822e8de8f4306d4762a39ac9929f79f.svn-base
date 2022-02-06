/*
 * ValidateLogoutReq.java
 *
 * Created on 21 de junio de 2010, 10:44 AM
 *
 * Interceptor diseñado para la validación de los parámetros provistos en la clase:
 * - LogoutReq 
 * para el método:
 * - getLogout(LogoutReq logoutReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 *
 */

package ERTMSWS.LocalInterceptors.Validators;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.Logout.LogoutReq;
import tms_encriptacion.EncriptarMD5;

/**
 *
 * @author opalafox
 */
public class ValidateLogoutReq extends MainValidator{
    
    private int sesionID;
    private String usuarioNumero;
    private String usuarioContrasena;
        
    /** Creates a new instance of ValidateLogoutReq */
    public ValidateLogoutReq() {
    }
    
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando LogoutReq: ");
        boolean validado = false;
        
        Object[] parameters = ic.getParameters();
        LogoutReq logoutReq = (LogoutReq)parameters[0];
        this.printParameters(logoutReq);
        sesionID = logoutReq.getSesionId();
        usuarioNumero = logoutReq.getUsuarioNumero();
        logoutReq.setUsuarioContrasena(new EncriptarMD5().encriptar(logoutReq.getUsuarioContrasena()));        
        usuarioContrasena = logoutReq.getUsuarioContrasena();
        // Realizar procedimiento de validación
        
           if (this.checkRequired()){
            System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
                System.out.println("Valores opcionales correctos");
                
            
            else
                System.out.println("Error en opcionales, valores por defecto colocados");
            System.out.println("-- Interceptor -- LogoutReq correcto ");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: LogoutReq incorrecto ");
        }
        
        return ic.proceed();
    }
    public boolean checkRequired() throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidUsuarioNumero(usuarioNumero))&&
                (this.isValidUsuarioContrasena(usuarioContrasena)))
            return true;
        else
            return false;
    }
    public boolean checkOptional() throws Exception{
        return true;
    }
}
