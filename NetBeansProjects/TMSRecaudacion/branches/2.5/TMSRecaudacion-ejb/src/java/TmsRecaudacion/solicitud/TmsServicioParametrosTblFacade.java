/*
 * TmsServicioParametrosTblFacade.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsServiciosGastosTbl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import TmsRecaudacion.entidad.TmsServicioParametrosTbl;
import java.math.BigDecimal;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsServicioParametrosTblFacade implements TmsServicioParametrosTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsServicioParametrosTblFacade */
    public TmsServicioParametrosTblFacade() {
    }

    public void create(TmsServicioParametrosTbl tmsServicioParametrosTbl) {
        em.persist(tmsServicioParametrosTbl);
    }

    public void edit(TmsServicioParametrosTbl tmsServicioParametrosTbl) {
        em.merge(tmsServicioParametrosTbl);
    }

    public void destroy(TmsServicioParametrosTbl tmsServicioParametrosTbl) {
        em.merge(tmsServicioParametrosTbl);
        em.remove(tmsServicioParametrosTbl);
    }

    public TmsServicioParametrosTbl find(Object pk) {
        return (TmsServicioParametrosTbl) em.find(TmsServicioParametrosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsServicioParametrosTbl as o").getResultList();
    }
    
}
