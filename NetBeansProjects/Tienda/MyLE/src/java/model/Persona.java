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
    
    private String nombre="nada";
    private int edad;
   
    public Persona(){}
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    public String getNombre()
    {
        return nombre;
    }
    public void setEdad(int edad)
    {
        this.edad=edad;
    }
    public int getEdad()
    {
        return edad;
    }
}
