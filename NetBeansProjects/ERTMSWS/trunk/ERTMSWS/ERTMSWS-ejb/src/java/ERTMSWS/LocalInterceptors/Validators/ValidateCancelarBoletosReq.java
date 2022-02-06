/*
 * ValidateCancelarBoletosReq.java
 *
 * Created on 21 de junio de 2010, 10:44 AM
 *
 * Interceptor dise�ado para la validaci�n de los par�metros provistos en la clase:
 * - CancelarBoletosReq 
 * para el m�todo:
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
    private String motivoCancelaci�n;
    
    
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
        motivoCancelaci�n = cancelarBoletosReq.getMotivoCancelaci�n();
        
        
        // Realizar procedimiento de validaci�n
        
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
    
    private boolean isValidMotivoCancelaci�n(String mCancelaci�n) throws Exception{
        if (mCancelaci�n != null){
            if (this.isValidLength(mCancelaci�n,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"MotivoCancelaci�n"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"MotivoCancelaci�n"));  
    }
    public boolean checkRequired() throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidFoliosBoletos(foliosBoletos))&&
                (this.isValidMotivoCancelaci�n(motivoCancelaci�n)))
            return true;
        else
            return false;
    }
    public boolean checkOptional() throws Exception{
        return true;
    }
}