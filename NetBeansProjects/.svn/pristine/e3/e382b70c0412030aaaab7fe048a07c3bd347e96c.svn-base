/*
 * ValidateAsientosDispReq.java
 *
 * Created on 21 de junio de 2010, 10:42 AM
 *
 * Interceptor diseoado para la validacion de los parometros provistos en la clase:
 * - AsientosDispReq 
 * para el motodo:
 * - getAsientosDisp(AsientosDispReq asientosDispReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.AsientosDisp.AsientosDispReq;

/*import ERTMSWS.LocalInterceptors.Validators.ClaveCorridaException;
import ERTMSWS.LocalInterceptors.Validators.SesionIdException;
/**
 *
 * @author opalafox
 */
public class ValidateAsientosDispReq extends MainValidator{
    
    private int sesionID;
    private String claveCorrida;
    private String origen;
    
    /** Creates a new instance of ValidateAsientosDispReq */
    public ValidateAsientosDispReq() {
    }
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws  Exception{
        System.out.println("-- Interceptor -- Validando AsientosDispReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        AsientosDispReq asientosDispReq = (AsientosDispReq)parameters[0];
        this.printParameters(asientosDispReq);
        sesionID = asientosDispReq.getSesionId();
        claveCorrida = asientosDispReq.getClaveCorrida();
        origen = asientosDispReq.getOrigen();
        
        
        // Realizar procedimiento de validacion
        
        
       if (this.checkRequired()){
         //   System.out.println("Valores requeridos correctos");
            if(this.checkOptional()){
           //     System.out.println("Valores opcionales correctos");
                System.out.println("-- Interceptor -- AsientosDispReq correcto: R0");
            }
            else
                //System.out.println("Error en opcionales, valores por defecto colocados");
                System.out.println("-- Interceptor -- AsientosDispReq correcto: R");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: AsientosDispReq incorrecto ");
        }
        
        
        return ic.proceed();
    }
    public boolean checkOptional()throws Exception{
        return true;
        
    }
    public boolean checkRequired()throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidClaveCorrida(claveCorrida)) &&
                (this.isValidOrigen(origen)))
            return true;
        else
            return false;
        
    }
}
