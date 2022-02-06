/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fraccionamiento;

import java.io.Serializable;

/**
 *
 * @author Jav
 */
public class Vecino implements Serializable
{
    private String nombre;
    private String direccion;
    
    public Vecino()
    {
        this.nombre="nada";
        this.direccion="nada";
    }
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setDireccion(String direccion)
    {
        this.direccion=direccion;
    }
    
    public String getDireccion()
    {
        return direccion;
    }
}
