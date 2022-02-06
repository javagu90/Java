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
    private String edad;
    private String nombre;
    
    public Alumno (String edad, String nombre)
    {
        this.edad=edad;
        this.nombre=nombre;
    }
    
    public String toString()
    {
        return nombre+ " "+ edad;
    }
    
    public static Alumno imprimir(Alumno a)
    {
        System.out.println(a);
        return a;
    }
    
    
}
