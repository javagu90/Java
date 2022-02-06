/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conexion;

import entidad.Usuario;
import org.hibernate.Session;

/**
 *
 * @author Jav
 */
public class Datos 
{
    public Datos()
    {}
    
        
    public Usuario getAllUsers(int id)
    {
        Session s= HibernateUtil.getCurrentSession();
        s.beginTransaction();
        Usuario result= (Usuario) s.get(Usuario.class,id);
        s.getTransaction().commit();
        s.close();
        return result;
    }
    
    public Usuario getUsuario(int id)
    {
        Session s= HibernateUtil.getCurrentSession();
        s.beginTransaction();
        Usuario result= (Usuario) s.get(Usuario.class,id);
        s.getTransaction().commit();
        s.close();
        return result;
    }
    
    public void insertarUsuario(Usuario u)
    {
        Session s= HibernateUtil.getCurrentSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
    }
    
        public void eliminarUsuario(Usuario u)
    {
        Session s= HibernateUtil.getCurrentSession();
        s.beginTransaction();
        s.delete(u);
        s.getTransaction().commit();
        s.close();
    }
}
