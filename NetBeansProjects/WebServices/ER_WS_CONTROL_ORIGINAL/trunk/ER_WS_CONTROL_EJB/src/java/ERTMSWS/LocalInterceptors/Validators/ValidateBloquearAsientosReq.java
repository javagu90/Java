/*
 * ValidateBloquearAsientosReq.java
 *
 * Created on 21 de junio de 2010, 10:42 AM
 *
 * Interceptor diseoado para la validacion de los parometros provistos en la clase:
 * - BloquerAsientosReq 
 * para el motodo:
 * - getBloquearAsientos(BloquearAsientosReq bloquearAsientosReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.BloquearAsientos.BloquearAsientosReq;

/**
 *
 * @author opalafox
 */
public class ValidateBloquearAsientosReq extends MainValidator{
    private int sesionID;
    private String noAsientos;
    private String tipoPasajero;
    private String claveCorrida;
    private String modalidad;
    private String origen;
    
    
    /** Creates a new instance of ValidateBloquearAsientosReq */
    public ValidateBloquearAsientosReq() {
    }
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando BloquearAsientosReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        
        BloquearAsientosReq bloquearAsientosReq = (BloquearAsientosReq)parameters[0];
        this.printParameters(bloquearAsientosReq);
        sesionID = bloquearAsientosReq.getSesionId();
        noAsientos = bloquearAsientosReq.getNoAsientos();
        tipoPasajero = bloquearAsientosReq.getTipoPasajero();
        claveCorrida = bloquearAsientosReq.getClaveCorrida();
        modalidad = bloquearAsientosReq.getModalidad();
        origen = bloquearAsientosReq.getOrigen();
        
        if (this.checkRequired()){
           // System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
             //   System.out.println("Valores opcionales correctos");
                 System.out.println("-- Interceptor -- BloquearAsientosReq correcto: R0");
            else
                //System.out.println("Error en opcionales, valores por defecto colocados");
                System.out.println("-- Interceptor -- BloquearAsientosReq correcto: R");
            //System.out.println("-- Interceptor -- BloquearAsientosReq correcto");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: BloquearAsientosReq incorrecto ");
            
        }
        
        
        return ic.proceed();
    }
    private boolean isValidNoAsientos(String nAsientos) throws Exception{
        if (nAsientos != null){
            if (this.isValidLength(nAsientos,300))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"NoAsientos"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"NoAsientos"));
        
    }
    private boolean isValidTipoPasajero(String tPasajero) throws Exception{
        if (tPasajero != null){
            if (this.isValidLength(tPasajero,300))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"TipoPasajero"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"TipoPasajero"));
        
    }
    private boolean isValidModalidad(String mod) throws Exception{
        if (mod != null){
            if (this.isValidLength(mod,1))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"Modalidad"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"Modalidad"));
        
        //Aoadir codigo de validacion
      
    }
    public boolean checkRequired()throws Exception{
        if ((this.isValidSesionId(sesionID))&&
                (this.isValidNoAsientos(noAsientos))&&
                (this.isValidTipoPasajero(tipoPasajero))&&
                (this.isValidOrigen(origen))&&
                (this.isValidClaveCorrida(claveCorrida))&&
                (this.isValidModalidad(modalidad)))
            return true;
        else
            return false;
    }
    public boolean checkOptional(){
        return true;
    }
}
