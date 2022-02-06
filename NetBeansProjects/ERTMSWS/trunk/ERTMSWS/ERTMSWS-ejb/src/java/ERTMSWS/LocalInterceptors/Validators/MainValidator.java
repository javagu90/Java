/*
 * MainValidator.java
 *
 * Created on 21 de junio de 2010, 01:10 PM
 *
 * Root class para todos los Interceptors de validaci�n, definiendo m�todos comunes para todos ellos
 *
 * En todos los casos se verifica que el valor sea distinto a null
 */

package ERTMSWS.LocalInterceptors.Validators;
import java.util.Vector;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

import ERTMSWS.clases.Asientos;
import ERTMSWS.clases.FoliosBoletos;
import ERTMSWS.clasesx.ExchangeResp;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import ERTMSWS.clasesx.Login.LoginReq;
/**
 *
 * @author opalafox
 */
public abstract class MainValidator {
    private Vector<ErrorMessage> errorList = new Vector<ErrorMessage>();
    public final int NULO = 1;
    public final int LONGITUDEXCEDIDA = 2;
    public final int NEGATIVO = 3;
    SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    /** Creates a new instance of MainValidator */
    public MainValidator() {
        errorList.add(new ErrorMessage(NULO,"Valor nulo"));
        errorList.add(new ErrorMessage(LONGITUDEXCEDIDA,"Longitud excedida"));
        errorList.add(new ErrorMessage(NEGATIVO,"Valor num�rico negativo"));
        
    }
    public boolean isValidLength(String value, int length){
        //if (value != null){
            if (value.length() <= length)
                return true;
            else
                return false;
        
    }
    public boolean isValidClaveCorrida(String value)throws Exception{
        //a�adir c�digo para verificar si es una clavecorrida v�lida
        if (value != null){
            if (this.isValidLength(value,30))
                return true;
            else
                throw new Exception(getErrorMessage(LONGITUDEXCEDIDA,"ClaveCorrida"));
        }else{
            throw new Exception(getErrorMessage(NULO,"ClaveCorrida"));
 
        }
    }
    

    public boolean isValidOrigen(String value)throws Exception{
        //A�adir c�digo para recuperar de BD los Origenes-Destinos v�lidos
        if (value != null){
            if (this.isValidLength(value,30))
                return true;
            else
                throw new Exception(getErrorMessage(LONGITUDEXCEDIDA,"Origen"));
        }else{
            throw new Exception(getErrorMessage(NULO,"Origen"));
            
        }
        
    }
    public boolean isValidDestino(String value)throws Exception{
        //A�adir c�digo para recuperar de BD los Origenes-Destinos v�lidos
        if (value != null){
            if (this.isValidLength(value,30))
                return true;
            else
                throw new Exception(getErrorMessage(LONGITUDEXCEDIDA,"Destino"));
        }else{
            throw new Exception(getErrorMessage(NULO,"Destino"));
        }
        
    }
    public boolean isValidSesionId(int  ID)throws Exception{
        
        //A�adir c�digo para verificar si el ID provisto es v�lido seg�n pol�ticas del negocio
        if (ID >= 0)
            return true;
        else{
            throw new Exception(getErrorMessage(NEGATIVO,"SesionId"));
            //
        }
        
    }
    public boolean isValidAsientos(Asientos[] asientos) throws Exception{
        if (asientos != null)
        //A�adir c�digo de validaci�n de asientos
            return true;
        else
            throw new Exception(getErrorMessage(NULO,"Asientos"));
    }
    public boolean isValidFoliosBoletos(FoliosBoletos[] fBoletos)throws Exception{
        if (fBoletos!= null)
            return true;
        else
            throw new Exception(getErrorMessage(NULO,"FoliosBoletos"));
    }
    public String getErrorMessage(int error,String variable){
        for (ErrorMessage em : errorList){
            if (error == em.geterrorCode())
                return "(C�digo de Error)"+error+": (Campo)"+variable+" -- "+em.getmensaje();
        }
        return "-1 "+variable+": C�digo de Error no encontrado";
    }
    public boolean isValidUsuarioNumero(String uNumero)throws Exception{
        //A�adir c�digo de validaci�n
        if (uNumero != null){
            if (this.isValidLength(uNumero,8))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"UsuarioNumero"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"UsuarioNumero"));    
        
    }
   
    public boolean isValidUsuarioContrasena(String uContrasena)throws Exception{
        //A�adir c�digo de validaci�n
        if (uContrasena != null){
            if (this.isValidLength(uContrasena,30000))
                return true;
            else
                throw new Exception(this.getErrorMessage(LONGITUDEXCEDIDA,"UsuarioContrasena"));
        }else
            throw new Exception (this.getErrorMessage(NULO,"UsuarioContrasena"));    
    }
    public static void responseErrorClass(ExchangeResp exResp, Exception except){
        /* Aprovechando caracter�sticas de polimorfismo, se crea m�todo general para la
         * generaci�n de las clases de respuesta cuando es generada una excepci�n
         */
        
        //ExchangeResp exResp= new ExchangeResp();    
        
        except.printStackTrace();
     //   except.printStackTrace();
        String errormsg = except.getMessage().replaceAll("\\([^\\(]*\\)","");
        //System.out.println(errormsg);
        String errormsgArray[] = errormsg.split(":");
        //System.out.println("errormsgArray "+errormsgArray.length);
        if (errormsgArray.length == 2){
            exResp.setErrorCode(errormsgArray[0]);
            exResp.setErrorMsg(errormsgArray[1]);
            
        }else{
            exResp.setErrorCode("-2");
            exResp.setErrorMsg(except.getMessage());
            
        }
        exResp.setOperacionExitosa(false);
        //return exResp;
    }
    public abstract boolean checkRequired()throws Exception;
    public abstract boolean checkOptional()throws Exception;
    
    public void printParameters(Object ob) throws Exception{
        sdfFechaHora.setTimeZone(TimeZone.getTimeZone("America/Mexico_City")); 
        String clase = ob.getClass().getSimpleName();
    
        Field campos[] = ob.getClass().getDeclaredFields();
        Object objeto;
        Method metodos[] = ob.getClass().getMethods();
        String result = "";
        for (Field campo: campos){
    
            for (Method metodo:metodos){
                if ((metodo.getName().indexOf("get")!= -1)&&(metodo.getName().indexOf(campo.getName())!= -1)){
                    objeto = metodo.invoke(ob);
                    if (objeto instanceof Date){
                        result += "\t"+campo.getName()+": "+sdfFechaHora.format(metodo.invoke(ob))+"\n";
                        //System.out.println("\t"+campo.getName()+": "+sdfFechaHora.format(metodo.invoke(ob)));
                    }else
                       result += "\t"+campo.getName()+": "+metodo.invoke(ob)+"\n";
                    //System.out.println("\t"+campo.getName()+": "+metodo.invoke(ob));
                }
            }
        }
        
        System.out.println(result);
         /*
        for (int i = 0; i < campos.length;i++){
            System.out.println(campos[i].getName());
        }
         **/
    }
}
