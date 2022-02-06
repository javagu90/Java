/*
 * TmsRecaudacionLineasTblFacade.java
 *
 * Created on 3 de noviembre de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRecaudacionLineasTbl;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsRecaudacionLineasTblFacade implements TmsRecaudacionLineasTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsRecaudacionLineasTblFacade */
    public TmsRecaudacionLineasTblFacade() {
    }

    public void create(TmsRecaudacionLineasTbl tmsRecaudacionLineasTbl, String ter) {
        em.persist(tmsRecaudacionLineasTbl);
        String ids = ""+tmsRecaudacionLineasTbl.getRecaudacionLineaId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsRecaudacionLineasTbl.setRecaudacionLineaId(BigDecimal.valueOf(idn));
    }

    public void edit(TmsRecaudacionLineasTbl tmsRecaudacionLineasTbl) {
        em.merge(tmsRecaudacionLineasTbl);
    }

    public void destroy(TmsRecaudacionLineasTbl tmsRecaudacionLineasTbl) {
        em.merge(tmsRecaudacionLineasTbl);
        em.remove(tmsRecaudacionLineasTbl);
    }

    public TmsRecaudacionLineasTbl find(Object pk) {
        return (TmsRecaudacionLineasTbl) em.find(TmsRecaudacionLineasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRecaudacionLineasTbl as o").getResultList();
    }
    
}
