/*
 * ValidateCancelarBoletosReq.java
 *
 * Created on 21 de junio de 2010, 10:44 AM
 *
 * Interceptor diseoado para la validacion de los parometros provistos en la clase:
 * - CancelarBoletosReq 
 * para el motodo:
 * - getCancelarBoletos(CancelarBoletosReq cancelarBoletosReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.CancelarBoletos.CancelarBoletosReq;
import ERTMSWS.clases.FoliosBoletos;

/**
 *
 * @author opalafox
 */
public class ValidateCancelarBoletosReq extends MainValidator{
    private int sesionID;
    private FoliosBoletos[] foliosBoletos;
    private String motivoCancelacion;
    
    
    /** Creates a new instance of ValidateCancelarBoletosReq */
    public ValidateCancelarBoletosReq() {
    }
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando CancelarBoletosReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        CancelarBoletosReq cancelarBoletosReq = (CancelarBoletosReq)parameters[0];
        this.printParameters(cancelarBoletosReq);
        sesionID = cancelarBoletosReq.getSesionId();
        foliosBoletos = cancelarBoletosReq.getFoliosBoletos();
        motivoCancelacion = cancelarBoletosReq.getMotivoCancelacion();
        
        
        // Realizar procedimiento de validacion
        
        if (this.checkRequired()){
            System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
                System.out.println("Valores opcionales correctos");
                
            
            else
                System.out.println("Error en opcionales, valores por defecto colocados");
            System.out.println("-- Interceptor -- CancelarBoletosReq correcto ");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: CancelarBoletosReq incorrecto ");
        }
        
        
        return ic.proceed();
    }
    
    private boolean isValidMotivoCancelacion(String mCancelacion) throws Exception{
        if (mCancelacion != null){
            if (this.isValidLength(mCancelacion,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"MotivoCancelacion"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"MotivoCancelacion"));
    }
    public boolean checkRequired() throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidFoliosBoletos(foliosBoletos))&&
                (this.isValidMotivoCancelacion(motivoCancelacion)))
            return true;
        else
            return false;
    }
    public boolean checkOptional() throws Exception{
        return true;
    }
}
