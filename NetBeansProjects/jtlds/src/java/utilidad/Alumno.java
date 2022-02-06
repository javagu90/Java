/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jav
 */
package utilidad;
public class Alumno 
{
    private static String edad;
    private static String nombre;
    
    public  Alumno (String nombre, String edad)
    {
        this.edad=edad;
        this.nombre=nombre;
    }
    
    public static String imprimir()
    {
        return nombre+ " "+ edad;
    }
}
