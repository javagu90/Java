/*
 * TmsAsientosNoDispTblFacade.java
 *
 * Created on 7 de diciembre de 2007, 07:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsBolNoDisp.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsBolNoDisp.entidad.TmsAsientosNoDispTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsAsientosNoDispTblFacade implements TmsAsientosNoDispTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsAsientosNoDispTblFacade */
    public TmsAsientosNoDispTblFacade() {
    }

    public void create(TmsAsientosNoDispTbl tmsAsientosNoDispTbl) {
        em.persist(tmsAsientosNoDispTbl);
    }

    public void edit(TmsAsientosNoDispTbl tmsAsientosNoDispTbl) {
        em.merge(tmsAsientosNoDispTbl);
    }

    public void destroy(TmsAsientosNoDispTbl tmsAsientosNoDispTbl) {
        em.merge(tmsAsientosNoDispTbl);
        em.remove(tmsAsientosNoDispTbl);
    }

    public TmsAsientosNoDispTbl find(Object pk) {
        return (TmsAsientosNoDispTbl) em.find(TmsAsientosNoDispTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsAsientosNoDispTbl as o").getResultList();
    }
    
}
