/*
 * TmsTarjetasViajeTblFacade.java
 *
 * Created on 10 de noviembre de 2007, 10:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmslecturaviaxer.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTarjetasViajeTblFacade implements TmsTarjetasViajeTblFacadeRemote {

    @PersistenceContext(unitName="tmslecturaviaxer-ejbCENTRAL")
    private EntityManager em;
    
    /** Creates a new instance of TmsTarjetasViajeTblFacade */
    public TmsTarjetasViajeTblFacade() {
    }

    public void create(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.persist(tmsTarjetasViajeTbl);
    }

    public void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
    }

    public void destroy(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
        em.remove(tmsTarjetasViajeTbl);
    }

    public TmsTarjetasViajeTbl find(Object pk) {
        return (TmsTarjetasViajeTbl) em.find(TmsTarjetasViajeTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsTarjetasViajeTbl as o").getResultList();
    }
    
}
