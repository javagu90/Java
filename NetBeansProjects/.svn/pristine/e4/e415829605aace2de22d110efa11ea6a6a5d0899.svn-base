/*
 * ValidateCanjeBAReq.java
 *
 * Created on 21 de junio de 2010, 10:44 AM
 *
 * Interceptor diseñado para la validación de los parámetros provistos en la clase:
 * - CanjeBAReq 
 * para el método:
 * - getCanjeBA(CanjeBAReq canjeBAReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.CanjeBA.CanjeBAReq;
import ERTMSWS.clases.FoliosBoletos;
import ERTMSWS.clases.Asientos;


/**
 *
 * @author opalafox
 */
public class ValidateCanjeBAReq extends MainValidator{
    private int sesionID; 
    private FoliosBoletos[] foliosBoletos;
    private Asientos[] asientos;
    
    /** Creates a new instance of ValidateCanjeBAReq */
    public ValidateCanjeBAReq() {
    }
    
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando CanjeBAReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        CanjeBAReq canjeBAReq = (CanjeBAReq)parameters[0];
        this.printParameters(canjeBAReq);
        sesionID = canjeBAReq.getSesionId();
        foliosBoletos = canjeBAReq.getFoliosBoletos();
        asientos = canjeBAReq.getAsientos();
        
        
        // Realizar procedimiento de validación
        
        if (this.checkRequired()){
            System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
                System.out.println("Valores opcionales correctos");
                
            
            else
                System.out.println("Error en opcionales, valores por defecto colocados");
            System.out.println("-- Interceptor -- CanjeBAReq correcto ");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: CanjeBAReq incorrecto ");
        }
        
        
        
        return ic.proceed();
    }
    public boolean checkRequired()throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidAsientos(asientos))&&
                (this.isValidFoliosBoletos(foliosBoletos)))
            return true;
        else
            return false;
    }
    public boolean checkOptional(){
        return true;
    }
}
