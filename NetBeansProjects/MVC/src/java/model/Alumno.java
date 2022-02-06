/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jav
 */
public class Alumno {
    String nombre;
    String apellido;
    String edad;
    
    public Alumno (String nombre, String apellido, String edad)
    {
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
    }
    
    public String toString()
    {
        return nombre+apellido+edad;
    }
}
