/*
 * ValidateVenderBoletosReq.java
 *
 * Created on 21 de junio de 2010, 10:43 AM
 *
 * Interceptor diseñado para la validación de los parámetros provistos en la clase:
 * - VenderBoletosReq 
 * para el método:
 * - getVenderBoletos(VenderBoletosReq venderBoletosReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.VenderBoletos.VenderBoletosReq;
import ERTMSWS.clases.Asientos;
/**
 *
 * @author opalafox
 */
public class ValidateVenderBoletosReq extends MainValidator{
    private int sesionID;
    private Asientos[] asientos;
    /** Creates a new instance of ValidateVenderBoletosReq */
    public ValidateVenderBoletosReq() {
    }
    
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando VenderBoletosReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        VenderBoletosReq venderBoletosReq = (VenderBoletosReq)parameters[0];
        this.printParameters(venderBoletosReq);
        sesionID = venderBoletosReq.getSesionId();
        asientos = venderBoletosReq.getAsientos();
   
        
        // Realizar procedimiento de validación
        
        if (this.checkRequired()){
            System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
                System.out.println("Valores opcionales correctos");
                
            
            else
                System.out.println("Error en opcionales, valores por defecto colocados");
            System.out.println("-- Interceptor -- VenderBoletosReq correcto ");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: VenderBoletosReq incorrecto ");
        }
        
        return ic.proceed();
    }
    
    public boolean checkRequired() throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidAsientos(asientos)))
            return true;
        else
            return false;
    }
    public boolean checkOptional() throws Exception{
        return true;
    }
}
