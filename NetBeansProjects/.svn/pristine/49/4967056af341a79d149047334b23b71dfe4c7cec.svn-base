/*
 * TmsTerminalesTblFacade.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xertmsCorteReca.entidad.TmsTerminalesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTerminalesTblFacade implements TmsTerminalesTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsTerminalesTblFacade */
    public TmsTerminalesTblFacade() {
    }

    public void create(TmsTerminalesTbl tmsTerminalesTbl) {
        em.persist(tmsTerminalesTbl);
    }

    public void edit(TmsTerminalesTbl tmsTerminalesTbl) {
        em.merge(tmsTerminalesTbl);
    }

    public void destroy(TmsTerminalesTbl tmsTerminalesTbl) {
        em.merge(tmsTerminalesTbl);
        em.remove(tmsTerminalesTbl);
    }

    public TmsTerminalesTbl find(Object pk) {
        return (TmsTerminalesTbl) em.find(TmsTerminalesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsTerminalesTbl as o").getResultList();
    }
    
}
