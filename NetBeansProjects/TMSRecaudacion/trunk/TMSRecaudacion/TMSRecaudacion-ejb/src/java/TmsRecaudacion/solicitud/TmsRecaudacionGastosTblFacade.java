/*
 * TmsRecaudacionGastosTblFacade.java
 *
 * Created on 5 de noviembre de 2007, 07:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRecaudacionGastosTbl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsRecaudacionGastosTblFacade implements TmsRecaudacionGastosTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsRecaudacionGastosTblFacade */
    public TmsRecaudacionGastosTblFacade() {
    }

    public void create(TmsRecaudacionGastosTbl tmsRecaudacionGastosTbl) {
        em.persist(tmsRecaudacionGastosTbl);
    }

    public void edit(TmsRecaudacionGastosTbl tmsRecaudacionGastosTbl) {
        em.merge(tmsRecaudacionGastosTbl);
    }

    public void destroy(TmsRecaudacionGastosTbl tmsRecaudacionGastosTbl) {
        em.merge(tmsRecaudacionGastosTbl);
        em.remove(tmsRecaudacionGastosTbl);
    }

    public TmsRecaudacionGastosTbl find(Object pk) {
        return (TmsRecaudacionGastosTbl) em.find(TmsRecaudacionGastosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRecaudacionGastosTbl as o").getResultList();
    }
    
}
