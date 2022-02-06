/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectohibernate;

import conexion.Datos;
import entidad.Usuario;

/**
 *
 * @author Jav
 */
public class ProyectoHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Datos d= new Datos();
        Usuario u= d.getUsuario(2);
        System.out.println("id: "+ u.getIdusuario());
        System.out.println("nombre: "+ u.getNombre());
    }
    
}
