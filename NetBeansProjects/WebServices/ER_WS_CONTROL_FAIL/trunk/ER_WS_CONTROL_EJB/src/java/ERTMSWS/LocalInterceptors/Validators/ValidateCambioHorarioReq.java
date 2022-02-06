/*
 * ValidateCambioHorarioReq.java
 *
 * Created on 23 de junio de 2010, 09:23 AM
 *
 * Interceptor diseoado para la validacion de los parometros provistos en la clase:
 * - CambioHorarioReq 
 * para el motodo:
 * - getCambioHorario(CambioHorarioReq cambioHorarioReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clases.Asientos;
import ERTMSWS.clases.FoliosBoletos;
import ERTMSWS.clasesx.CambioHorario.CambioHorarioReq;

/**
 *
 * @author opalafox
 */
public class ValidateCambioHorarioReq extends MainValidator{
    private int sesionID;
    private FoliosBoletos[] foliosBoletos;
    private Asientos[] asientos;
    /** Creates a new instance of ValidateCambioHorarioReq */
    public ValidateCambioHorarioReq() {
    }
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando CambioHorarioReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        CambioHorarioReq cambioHorarioReq = (CambioHorarioReq)parameters[0];
        this.printParameters(cambioHorarioReq);
        sesionID = cambioHorarioReq.getSesionId();
        foliosBoletos = cambioHorarioReq.getFoliosBoletos();
        asientos = cambioHorarioReq.getAsientos();
        
        
        if (this.checkRequired()){
            System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
                System.out.println("Valores opcionales correctos");
            else
                System.out.println("Error en opcionales, valores por defecto colocados");
            System.out.println("-- Interceptor -- CambioHorarioReq correcto");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: CambioHorarioReq incorrecto ");
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
