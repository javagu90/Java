/*
 * TmsCorridasTblFacade.java
 *
 * Created on 14 de noviembre de 2007, 05:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsroles.entidad.TmsCorridasTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCorridasTblFacade implements TmsCorridasTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsCorridasTblFacade */
    public TmsCorridasTblFacade() {
    }

    public TmsCorridasTbl create(TmsCorridasTbl tmsCorridasTbl, String ter) {
        em.persist(tmsCorridasTbl);
        String ids = ""+tmsCorridasTbl.getCorridaId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        String clv = ""+tmsCorridasTbl.getClaveCorrida();
        tmsCorridasTbl.setCorridaId(BigDecimal.valueOf(idn));
        tmsCorridasTbl.setClaveCorrida(clv+ids);
        return tmsCorridasTbl;
    }

    public void edit(TmsCorridasTbl tmsCorridasTbl) {
        em.merge(tmsCorridasTbl);
    }

    public void destroy(TmsCorridasTbl tmsCorridasTbl) {
        em.merge(tmsCorridasTbl);
        em.remove(tmsCorridasTbl);
    }

    public TmsCorridasTbl find(Object pk) {
        return (TmsCorridasTbl) em.find(TmsCorridasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCorridasTbl as o").getResultList();
    }
    
}
