/*
 * TmsJornadasServiciosTblFacade.java
 *
 * Created on 9 de diciembre de 2007, 05:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsedosoperador.entidad.TmsJornadasServiciosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsJornadasServiciosTblFacade implements TmsJornadasServiciosTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsJornadasServiciosTblFacade */
    public TmsJornadasServiciosTblFacade() {
    }

    public void create(TmsJornadasServiciosTbl tmsJornadasServiciosTbl) {
        em.persist(tmsJornadasServiciosTbl);
    }

    public void edit(TmsJornadasServiciosTbl tmsJornadasServiciosTbl) {
        em.merge(tmsJornadasServiciosTbl);
    }

    public void destroy(TmsJornadasServiciosTbl tmsJornadasServiciosTbl) {
        em.merge(tmsJornadasServiciosTbl);
        em.remove(tmsJornadasServiciosTbl);
    }

    public TmsJornadasServiciosTbl find(Object pk) {
        return (TmsJornadasServiciosTbl) em.find(TmsJornadasServiciosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsJornadasServiciosTbl as o").getResultList();
    }
    
}
