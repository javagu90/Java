/*
 * TmsAutobusesTblFacade.java
 *
 * Created on 13 de diciembre de 2007, 10:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsroles.entidad.TmsAutobusesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsAutobusesTblFacade implements TmsAutobusesTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsAutobusesTblFacade */
    public TmsAutobusesTblFacade() {
    }

    public void create(TmsAutobusesTbl tmsAutobusesTbl) {
        em.persist(tmsAutobusesTbl);
    }

    public void edit(TmsAutobusesTbl tmsAutobusesTbl) {
        em.merge(tmsAutobusesTbl);
    }

    public void destroy(TmsAutobusesTbl tmsAutobusesTbl) {
        em.merge(tmsAutobusesTbl);
        em.remove(tmsAutobusesTbl);
    }

    public TmsAutobusesTbl find(Object pk) {
        return (TmsAutobusesTbl) em.find(TmsAutobusesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsAutobusesTbl as o").getResultList();
    }
    
    public List<TmsAutobusesTbl> buscaBusPorNumeroEconomico(String numEcon){
        return (List<TmsAutobusesTbl>) em.createNativeQuery("select * from tms_autobuses_tbl where numero_economico = '"+numEcon+"'",TmsAutobusesTbl.class).getResultList();
    }
}
