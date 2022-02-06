/*
 * ValidateCorridasReq.java
 *
 * Created on 21 de junio de 2010, 10:41 AM
 *
 * Interceptor diseñado para la validación de los parámetros provistos en la clase:
 * - CorridasReq 
 * para el método:
 * - getCorridas(CorridasReq corridasReq)
 * del Bean:
 * ejbERTMSWSFacadeBean
 */

package ERTMSWS.LocalInterceptors.Validators;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ERTMSWS.clasesx.Corridas.CorridasReq;
import java.util.Date;

/**
 *
 * @author opalafox
 */
public class ValidateCorridasReq extends MainValidator{
    private int sesionID;
    private String empresa;
    private String servicio;
    private String origen;
    private String destino;
    private Date fechaHoraSalida;
    
    /** Creates a new instance of ValidateCorridasReq */
    public ValidateCorridasReq() {
    }
    @AroundInvoke
    public Object validar(InvocationContext ic ) throws Exception{
        System.out.println("-- Interceptor -- Validando CorridasReq: ");
        
        boolean validado = false;
        
        
        
        Object[] parameters = ic.getParameters();
        CorridasReq corridasReq = (CorridasReq)parameters[0];
        this.printParameters(corridasReq);
        sesionID = corridasReq.getSesionId();
        empresa = corridasReq.getEmpresa();
        servicio = corridasReq.getServicio();
        origen = corridasReq.getOrigen();
        destino = corridasReq.getDestino();
        fechaHoraSalida = corridasReq.getFechaHoraSalida();
       
        
        // Realizar procedimiento de validación
        if (this.checkRequired()){
           // System.out.println("Valores requeridos correctos");
            if(this.checkOptional())
             //   System.out.println("Valores opcionales correctos");
                System.out.println("-- Interceptor -- CorridasReq correcto: R0");
            else
                //System.out.println("Error en opcionales, valores por defecto colocados");
                System.out.println("-- Interceptor -- CorridasReq correcto: R");
        }
        else{
            System.out.println("-- Interceptor -- ERROR!!!: CorridasReq incorrecto ");
        }
        
        
        
        return ic.proceed();
    }
    private boolean isValidEmpresa(String empresa)throws Exception{
        //Añadir código de validación
        if (empresa != null){
            if (this.isValidLength(empresa,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"Empresa"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"Empresa"));    
    }
    private boolean isValidFechaHoraSalida(Date fecha)throws Exception{
        //Añadir código de validación
        if (fecha != null)
            return true;
        else
            throw new Exception (this.getErrorMessage(NULO,"FechaHoraSalida"));
    }
    private boolean isValidServicio(String servicio)throws Exception{
        if (servicio != null){
            if (this.isValidLength(servicio,30))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"Servicio"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"Servicio"));    
        
    }
    public boolean checkRequired() throws Exception{
        if ((this.isValidSesionId(sesionID)) &&
                (this.isValidOrigen(origen)) &&
                (this.isValidFechaHoraSalida(fechaHoraSalida)))
        return true;
        else
            return false;
    }
    public boolean checkOptional() throws Exception{
        if ((this.isValidEmpresa(empresa))&&
                (this.isValidServicio(servicio))&&
                (this.isValidDestino(destino)))
            if (origen.compareTo(destino)!= 0)
                return true;
            else
                return false;
        else
            return false;
    }
}
