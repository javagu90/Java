/*
 * ValidateValidarBoletoReq.java
 *
 * Created on 26 de agosto de 2010, 09:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ERTMSWS.LocalInterceptors.Validators;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.ValidarBoleto.ValidarBoletoReq;
import ERTMSWS.clases.FoliosBoletos;
/**
/**
 *
 * @author opalafox
 */
public class ValidateValidarBoletoReq extends MainValidator{
    private int sesionID;
    private FoliosBoletos foliosBoletos[];
    private String tipoMovimiento;
    /** Creates a new instance of ValidateValidarBoletoReq */
    public ValidateValidarBoletoReq() {
    }
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando ValidarBoletoReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        ValidarBoletoReq validarBoletoReq = (ValidarBoletoReq)parameters[0];
        this.printParameters(validarBoletoReq);
        sesionID = validarBoletoReq.getSesionId();
        foliosBoletos = validarBoletoReq.getFoliosBoletos();
        tipoMovimiento = validarBoletoReq.getTipoMovimiento();
        
   
        
        // Realizar procedimiento de validacion
        
        if (this.checkRequired()){
            System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
                System.out.println("Valores opcionales correctos");
                
            
            else
                System.out.println("Error en opcionales, valores por defecto colocados");
            System.out.println("-- Interceptor -- ValidarBoletoReq correcto ");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: ValidarBoletoReq incorrecto ");
        }
        
        return ic.proceed();
    }
    
    public boolean checkRequired() throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidFoliosBoletos(foliosBoletos)) &&
                this.isValidLength(tipoMovimiento,1)     )
            return true;
        else
            return false;
    }
    public boolean checkOptional() throws Exception{
        return true;
    }
}
