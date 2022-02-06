/*
 * StatusType.java
 *
 * Created on 29 de diciembre de 2009, 04:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Datos;

/**
 *
 * @author brojasa
 */
public class StatusType {
    
    /** Creates a new instance of StatusType */
    boolean success;
    String message;
    int code;
    
    public StatusType() {
        success = false;
        message = "";
        code = 0;
    }
    
    public StatusType(boolean success, String message, int code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }
    
    public void setSuccess(boolean success){
        this.success = success;    
    }
    public boolean getSuccess(){
        return this.success;    
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;    
    }
    
    public String getMsg(int codigo, String excepcion){
        switch(codigo){
            case 0: 
                return "Transaccion realizada correcetamente";
            case 1: 
                return "Ocurrio el siguiente problema \n"+excepcion;
            case 2: 
                return "El Registra Transaccion Tricomp Falló \n"+excepcion;
            case 3: 
                return "El numero de Operacion "+excepcion+" ya ha sido utilizado";
            case 4: 
                return "El registraDetalleTransaccion Tricomp falló "+excepcion;
            case 5: 
                return excepcion+" no pudo cerrar la transaccion";
        }
        return "0";
    }
    
}
