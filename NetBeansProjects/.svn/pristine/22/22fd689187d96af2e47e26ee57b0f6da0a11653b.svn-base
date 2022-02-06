/*
 * TmsFlotillasTblFacade.java
 *
 * Created on 17 de diciembre de 2007, 12:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsroles.entidad.TmsFlotillasTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsFlotillasTblFacade implements TmsFlotillasTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsFlotillasTblFacade */
    public TmsFlotillasTblFacade() {
    }

    public void create(TmsFlotillasTbl tmsFlotillasTbl) {
        em.persist(tmsFlotillasTbl);
    }

    public void edit(TmsFlotillasTbl tmsFlotillasTbl) {
        em.merge(tmsFlotillasTbl);
    }

    public void destroy(TmsFlotillasTbl tmsFlotillasTbl) {
        em.merge(tmsFlotillasTbl);
        em.remove(tmsFlotillasTbl);
    }

    public TmsFlotillasTbl find(Object pk) {
        return (TmsFlotillasTbl) em.find(TmsFlotillasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsFlotillasTbl as o").getResultList();
    }
    
}
