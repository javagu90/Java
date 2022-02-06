/*
 * Resultado.java
 *
 * Created on 27 de noviembre de 2008, 09:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.transacciones;

import java.util.StringTokenizer;
/**
 *
 * @author ocruz
 */
public class Resultado {
    private boolean success1;
    private int errorCode1;
    private String errorMsg1;
    private String informacion1;
    private boolean proceso1;
    
    private boolean success2;
    private int errorCode2;
    private String errorMsg2;
    private String informacion2;
    private boolean proceso2;
    /**
     * Crea una nueva instancia de Resultado
     */
    public Resultado() {
    }
    
    /**
     * Procesa la informacion.
     * @param respuesta Informacion a procesar.
     */
    public void setResultado1(String respuesta){
        System.out.println("Respuesta en setResultado1 "+respuesta);
        errorMsg1 = null;
        informacion1 = null;
        proceso1 = false;
        
        String valor = null;
        int itokens;
        StringTokenizer strToken = new StringTokenizer(respuesta,";");
        itokens = strToken.countTokens();
        System.out.println("Cantidad de datos devueltos por respuesta "+itokens);
        
        if(itokens!=3 && itokens!=4 && itokens!=7) return;
        
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.success1 = valor.equals("0") ? true : false;
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.errorCode1 = Integer.valueOf(valor);
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.errorMsg1 = valor;
        if(itokens==3){
            proceso1 = true;
            return;
        }
        
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.informacion1 = valor;
        
        proceso1 = true;
    }
    
    /**
     * Procesa la informacion.
     * @param respuesta Informacion a procesar.
     */
    public void setResultado2(String respuesta){
        //System.out.println("Respuesta "+respuesta);
        errorMsg2 = null;
        informacion2 = null;
        proceso2 = false;
        
        String valor = null;
        int itokens;
        StringTokenizer strToken = new StringTokenizer(respuesta,";");
        itokens = strToken.countTokens();
        //System.out.println("Cantidad de datos devueltos por respuesta "+itokens);
        
        if(itokens!=3 && itokens!=4) return;
        
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.success2 = valor.equals("0") ? true : false;
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.errorCode2 = Integer.valueOf(valor);
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.errorMsg2 = valor;
        if(itokens==3){
            proceso2 = true;
            return;
        }
        
        valor = strToken.nextToken();
        if(valor==null || valor.equals("")) return;
        this.informacion2 = valor;
        
        proceso2 = true;
    }
    
    /**
     * Procesa dos partes de informacion.
     * @param respuesta Informacion a procesar.
     */
    public void setResultadoMultiple(String[] respuesta){
        System.out.println(respuesta[0]+" - setResultadoMultiple - "+respuesta[1]);
        setResultado1(respuesta[0]);
        if(respuesta[1]==null) return;
        setResultado2(respuesta[1]);
    }
    
    /**
     * Devuelve un valor para success.
     * @return Un boolean.
     */
    public boolean getSuccess1(){ return success1; }
    
    /**
     * Devuelve un codigo de error.
     * @return Un nomero.
     */
    public int getErrorCode1(){ return errorCode1; }
    
    /**
     * Devuelve un mensaje de error.
     * @return Un String.
     */
    public String getErrorMsg1(){ return errorMsg1; }
    
    /**
     * Devuelve la informacion procesada.
     * @return Un String.
     */
    public String getInformacion1(){ return informacion1; }
    
    /**
     * Devuelve verdadero si fue posible procesar la informacion.
     * @return Un boolean.
     */
    public boolean getProceso1(){ return proceso1; }
    
    /**
     * Devuelve un valor para success.
     * @return Un boolean.
     */
    public boolean getSuccess2(){ return (success1 && success2 ? true: false); }
    
    /**
     * Devuelve un codigo de error.
     * @return Un nomero.
     */
    public int getErrorCode2(){ return errorCode2; }
    
    /**
     * Devuelve un mensaje de error.
     * @return Un String.
     */
    public String getErrorMsg2(){ return errorMsg2; }
    
    /**
     * Devuelve la informacion procesada.
     * @return Un String.
     */
    public String getInformacion2(){ return informacion2; }
    
    /**
     * Devuelve verdadero si fue posible procesar la informacion.
     * @return Un boolean.
     */
    public boolean getProceso2(){ return (proceso1 && proceso2 ? true : false); }
}
