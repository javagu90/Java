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
public class Persona 
{
    public String nombre;
    private String apellido;
    //private Direccion direccion;
    private String edad;
    private String telefono;
    
    public Persona()
    {}
    
    public Persona(String nombre, String apellido,String edad, String telefono)
    {
        this.nombre=nombre;
        this.apellido=apellido;
      //  this.direccion=direccion;
        this.edad=edad;
        this.telefono=telefono;
    }
    
    public String toString()
    {
        return nombre+" "+apellido+" "+edad+" "+telefono;
    }
}
