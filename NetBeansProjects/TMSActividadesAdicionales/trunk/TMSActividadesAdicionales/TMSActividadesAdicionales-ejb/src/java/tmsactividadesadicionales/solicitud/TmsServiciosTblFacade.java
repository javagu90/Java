/*
 * TmsServiciosTblFacade.java
 *
 * Created on 2 de octubre de 2007, 07:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsServiciosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsServiciosTblFacade implements TmsServiciosTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
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
        return em.createQuery("select object(o) from TmsServiciosTbl as o").getResultList();
    }
    
}
