/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jav
 */
public class Alumno 
{
    public String nombre;
    public String edad;
    
    public Alumno (String nombre, String edad)
    {
        this.nombre=nombre;
        this.edad=edad;
    }
    
    public String toString()
    {
        return nombre+" "+edad;
    }
}
