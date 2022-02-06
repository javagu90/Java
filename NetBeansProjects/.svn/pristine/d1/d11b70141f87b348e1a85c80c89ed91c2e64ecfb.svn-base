/*
 * TmsServiciosTblFacade.java
 *
 * Created on 9 de octubre de 2007, 10:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_ocupacion.entidad.TmsServiciosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsServiciosTblFacade implements TmsServiciosTblFacadeRemote {

    @PersistenceContext(unitName="TMSConsultaOcupaciones-ejbPU")
    private EntityManager em;
    
    /** Creates a new instance of TmsServiciosTblFacade */
    public TmsServiciosTblFacade() {
    }

    public void create(TmsServiciosTbl tmsServiciosTbl) {
        em.persist(tmsServiciosTbl);
    }

    public void edit(TmsServiciosTbl tmsServiciosTbl) {
        em.merge(tmsServiciosTbl);
    }

    public void destroy(TmsServiciosTbl tmsServiciosTbl) {
        em.merge(tmsServiciosTbl);
        em.remove(tmsServiciosTbl);
    }

    public TmsServiciosTbl find(Object pk) {
        return (TmsServiciosTbl) em.find(TmsServiciosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsServiciosTbl as o order by o.servicioClave").getResultList();
    }
    
}
