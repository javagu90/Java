/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class Token implements Serializable {
    private BigDecimal clienteTokenId;
    private BigDecimal clienteId;
    private String formaPago="";
    private String token="";
    private String banco="";
    private String descripcion="";
    private String adicional1="";
    private String adicional2="";
    private String adicional3="";
    private String adicional4="";
    private String adicional5="";

public Token(){

}

public Token(Vector val){
   this.clienteTokenId=new BigDecimal(val.get(0).toString());
   this.clienteId=new BigDecimal(val.get(1).toString());
    this.formaPago=val.get(2)==null?"":val.get(2).toString();
    this.token=val.get(3)==null?"":val.get(3).toString();
    this.banco=val.get(4)==null?"":val.get(4).toString();
    this.descripcion=val.get(5)==null?"":val.get(5).toString();
    this.adicional1=val.get(6)==null?"":val.get(6).toString();
    this.adicional2=val.get(7)==null?"":val.get(7).toString();
    this.adicional3=val.get(8)==null?"":val.get(8).toString();
    this.adicional4=val.get(9)==null?"":val.get(9).toString();
    this.adicional5=val.get(10)==null?"":val.get(10).toString();}


    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the adicional1
     */
    public String getAdicional1() {
        return adicional1;
    }

    /**
     * @param adicional1 the adicional1 to set
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * @return the adicional2
     */
    public String getAdicional2() {
        return adicional2;
    }

    /**
     * @param adicional2 the adicional2 to set
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * @return the adicional3
     */
    public String getAdicional3() {
        return adicional3;
    }

    /**
     * @param adicional3 the adicional3 to set
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * @return the adicional4
     */
    public String getAdicional4() {
        return adicional4;
    }

    /**
     * @param adicional4 the adicional4 to set
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * @return the adicional5
     */
    public String getAdicional5() {
        return adicional5;
    }

    /**
     * @param adicional5 the adicional5 to set
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }


    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the clienteTokenId
     */
    public BigDecimal getClienteTokenId() {
        return clienteTokenId;
    }

    /**
     * @param clienteTokenId the clienteTokenId to set
     */
    public void setClienteTokenId(BigDecimal clienteTokenId) {
        this.clienteTokenId = clienteTokenId;
    }

    /**
     * @return the clienteId
     */
    public BigDecimal getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }


}
