/*
 * TmsOperadoresTblFacade.java
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
import tmslecturaviaxer.entidad.TmsOperadoresTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsOperadoresTblFacade implements TmsOperadoresTblFacadeRemote {

    @PersistenceContext(unitName="tmslecturaviaxer-ejbCENTRAL")
    private EntityManager em;
    
    /** Creates a new instance of TmsOperadoresTblFacade */
    public TmsOperadoresTblFacade() {
    }

    public void create(TmsOperadoresTbl tmsOperadoresTbl) {
        em.persist(tmsOperadoresTbl);
    }

    public void edit(TmsOperadoresTbl tmsOperadoresTbl) {
        em.merge(tmsOperadoresTbl);
    }

    public void destroy(TmsOperadoresTbl tmsOperadoresTbl) {
        em.merge(tmsOperadoresTbl);
        em.remove(tmsOperadoresTbl);
    }

    public TmsOperadoresTbl find(Object pk) {
        return (TmsOperadoresTbl) em.find(TmsOperadoresTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsOperadoresTbl as o").getResultList();
    }
    
}
