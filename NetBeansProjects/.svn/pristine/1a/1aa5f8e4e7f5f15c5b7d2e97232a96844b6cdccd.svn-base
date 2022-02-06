/*
 * TmsCorridasVentaTblFacade.java
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
import tmsBolNoDisp.entidad.TmsCorridasVentaTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCorridasVentaTblFacade implements TmsCorridasVentaTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsCorridasVentaTblFacade */
    public TmsCorridasVentaTblFacade() {
    }

    public void create(TmsCorridasVentaTbl tmsCorridasVentaTbl) {
        em.persist(tmsCorridasVentaTbl);
    }

    public void edit(TmsCorridasVentaTbl tmsCorridasVentaTbl) {
        em.merge(tmsCorridasVentaTbl);
    }

    public void destroy(TmsCorridasVentaTbl tmsCorridasVentaTbl) {
        em.merge(tmsCorridasVentaTbl);
        em.remove(tmsCorridasVentaTbl);
    }

    public TmsCorridasVentaTbl find(Object pk) {
        TmsCorridasVentaTbl cor = (TmsCorridasVentaTbl) em.find(TmsCorridasVentaTbl.class, pk);
        em.refresh(cor);
        return cor;
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCorridasVentaTbl as o").getResultList();
    }
    
}
