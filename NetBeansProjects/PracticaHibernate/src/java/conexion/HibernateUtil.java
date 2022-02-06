/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conexion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Jav
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal session= new ThreadLocal();
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration configuracion = new Configuration().configure();
            ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(configuracion.getProperties()).buildServiceRegistry();
            sessionFactory = configuracion.buildSessionFactory(sr);
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getCurrentSession() 
    {
        System.out.println("Hilla");
        Session s= (Session)session.get();
        if(s==null || !s.isOpen())
        {
            s= sessionFactory.openSession();
        }
        session.set(s);
        return s;
    }
    
        public static void closeSession() 
    {
        Session s= (Session)session.get();
        if(s !=null || s.isOpen())
        {
            s.close();
        }
        session.set(s);
    }
}
