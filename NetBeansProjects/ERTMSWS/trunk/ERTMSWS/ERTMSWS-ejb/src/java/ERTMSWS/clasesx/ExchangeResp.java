/*
 * ExchangeResp.java
 *
 * Created on 22 de junio de 2010, 04:13 PM
 *
 * Root class para hacer la definición de Campos comunes
 * OperacionExitosa
 * ErrorCode
 * ErrorMsg
 */

package ERTMSWS.clasesx;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author opalafox
 */
public class ExchangeResp {
    private boolean OperacionExitosa;
    private String ErrorCode;
    private String ErrorMsg;
    
    /** Creates a new instance of ExchangeResp */
    public ExchangeResp() {
    }

    public boolean isOperacionExitosa() {
        return OperacionExitosa;
    }

    public void setOperacionExitosa(boolean operacionExitosa) {
        this.OperacionExitosa = operacionExitosa;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        this.ErrorCode = errorCode;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.ErrorMsg = errorMsg;
    }
    public void printContent() throws Exception{
        String nl = System.getProperty("line.separator");
        //TODO: manipular método para que no solo tome la superclase inmediata, sino todo el camino para la
        //obtención de los campos a mostrar
        Object ob = this;
        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String clase = ob.getClass().getSimpleName();
       // System.out.println("Contenido "+clase+" : ");
        String superclase = ob.getClass().getSuperclass().getSimpleName();
     //   System.out.println("superclase: "+superclase);
        Field lcampos[] = ob.getClass().getDeclaredFields();
        Field scampos[] = ob.getClass().getSuperclass().getDeclaredFields();
        Vector <Field> campos = new Vector<Field>();
        for (Field campo: lcampos){
            campos.add(campo);
        }
        for (Field campo: scampos){
            campos.add(campo);
        }
        
        Method metodos[] = ob.getClass().getMethods();
        
        String result = "";
        Object objeto;
        for (Field campo: campos){
            //System.out.println(campo.getName());
            for (Method metodo:metodos){
              //  System.out.println(metodo.getName());
                if (((metodo.getName().startsWith("get")) || (metodo.getName().startsWith("is")))&&(metodo.getName().indexOf(campo.getName())!= -1)){
                    
                    
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
            // System.out.println(metodo.getName());
                    System.out.println(result);
                    
        /*System.out.println("FSDFSDFSDFSDFSDF");
        for (int i = 0; i < campos.length;i++){
            System.out.println(campos[i].getName());
        }
         **/
    }
   
}
