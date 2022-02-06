/*
 * ParametrosIniciales.java
 *
 * Created on 9 de septiembre de 2009, 01:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.datos;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author asolis
 */
public class ParametrosIniciales {
    int idParametro;
    String parametroCodigo;
    String parametroNombre;
    String parametroValor;
    String descripcion;
    int idParametroValor;
    String parametroTipo;
    Date fechaDesde;
    Date fechaHasta;
    String valorAdicional1;
    String valorAdicional2;
    String valorAdicional3;
    String valorAdicional4;
    String valorAdicional5;
    
    /** Creates a new instance of ParametrosIniciales */
    public ParametrosIniciales() {        
    }
    
    public ParametrosIniciales(Vector parametros) {
        //System.out.println("parametros "+parametros+ " "+parametros.get(0).toString());
        this.idParametro = Integer.parseInt(parametros.get(0).toString());        
        this.parametroCodigo = (parametros.get(1)== null ? "" : parametros.get(1).toString());
        this.parametroNombre = (parametros.get(2)== null ? "" : parametros.get(2).toString());
        this.parametroValor = (parametros.get(3)== null ? "" : parametros.get(3).toString());
        this.descripcion = (parametros.get(4)== null ? "" : parametros.get(4).toString());
        this.idParametroValor = Integer.parseInt( parametros.get(5).toString());
        this.parametroTipo = (parametros.get(10)== null ? "" : parametros.get(10).toString());
        this.fechaDesde = null;
        this.fechaHasta = null;
        this.valorAdicional1 = (parametros.get(9)== null ? "" : parametros.get(9).toString());
        this.valorAdicional2 = (parametros.get(10)== null ? "" : parametros.get(10).toString());
        this.valorAdicional3 = (parametros.get(11)== null ? "" : parametros.get(11).toString());
        this.valorAdicional4 = (parametros.get(12)== null ? "" : parametros.get(12).toString());
        this.valorAdicional5 = (parametros.get(13)== null ? "" : parametros.get(13).toString());
    }
    
    public void setIdParametro(int idParametro){
        this.idParametro = idParametro;
    }
    public int getIdParametro(){
        return this.idParametro;
    }
    
    public void setParametroCodigo(String parametroCodigo){
        this.parametroCodigo =parametroCodigo;
    }
    public String getParametroCodigo(){
        return this.parametroCodigo;
    }
        
    public void setParametroNombre(String parametroNombre){
        this.parametroNombre =parametroNombre;
    }
    public String getParametroNombre(){
        return this.parametroNombre;
    }    
    
    public void setParametroValor(String parametroValor){
        this.parametroValor = parametroValor;
    }
    public String getParametroValor(){
        return this.parametroValor;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
            
    public void setIdParametroValor(int idParametroValor){
        this.idParametroValor = idParametroValor;
    }
    public int getIdParametroValor(){
        return this.idParametroValor;
    }
            
    public void setParametroTipo(String parametroTipo){
        this.parametroTipo = parametroTipo;
    }
    public String getParametro(){
        return this.parametroTipo;
    }
            
    public void setFechaDesde(Date fechaDesde){
        this.fechaDesde = fechaDesde;
    }
    public Date getFechaDesde(){
        return this.fechaDesde;
    }
            
    public void setFechaHasta(Date fechaHasta){
        this.fechaHasta = fechaHasta;
    }
    public Date getFechaHasta(){
        return this.fechaHasta;
    }
    
    public void setValorAdicional1(String valorAdicional1){
        this.valorAdicional1 = valorAdicional1;
    }
    public String getValorAdicional1(){
        return this.valorAdicional1;
    }
    
    public void setValorAdicional2(String valorAdicional2){
        this.valorAdicional2 = valorAdicional2;
    }
    public String getValorAdicional2(){
        return this.valorAdicional2;
    }
    
    public void setValorAdicional3(String valorAdicional3){
        this.valorAdicional3 = valorAdicional3;
    }
    public String getValorAdicional3(){
        return this.valorAdicional3;
    }
    
    public void setValorAdicional4(String valorAdicional4){
        this.valorAdicional4 = valorAdicional4;
    }
    public String getValorAdicional4(){
        return this.valorAdicional4;
    }
    
    public void setValorAdicional5(String valorAdicional5){
        this.valorAdicional5 = valorAdicional5;
    }
    public String getValorAdicional5(){
        return this.valorAdicional5;
    }
    
}
