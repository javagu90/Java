package model;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GrayFox
 */
public class Alumno {
    String nombre;
    String apellido;
    String edad;
    public Alumno(String nombre,String apellido,String edad){
    this.nombre=nombre;
    this.apellido=apellido;
    this.edad=edad;
    
    }
    public String Alumno(){
        return nombre+" "+apellido+" "+edad;
    }
}
