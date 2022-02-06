/*
 * TmsTiposJornadaTblFacade.java
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
import tmsedosoperador.entidad.TmsTiposJornadaTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTiposJornadaTblFacade implements TmsTiposJornadaTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsTiposJornadaTblFacade */
    public TmsTiposJornadaTblFacade() {
    }

    public void create(TmsTiposJornadaTbl tmsTiposJornadaTbl) {
        em.persist(tmsTiposJornadaTbl);
    }

    public void edit(TmsTiposJornadaTbl tmsTiposJornadaTbl) {
        em.merge(tmsTiposJornadaTbl);
    }

    public void destroy(TmsTiposJornadaTbl tmsTiposJornadaTbl) {
        em.merge(tmsTiposJornadaTbl);
        em.remove(tmsTiposJornadaTbl);
    }

    public TmsTiposJornadaTbl find(Object pk) {
        return (TmsTiposJornadaTbl) em.find(TmsTiposJornadaTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsTiposJornadaTbl as o").getResultList();
    }
    
}
