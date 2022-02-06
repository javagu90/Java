/*
 * TmsRutasTblFacade.java
 *
 * Created on 14 de septiembre de 2007, 12:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRutasTbl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsRutasTblFacade implements TmsRutasTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsRutasTblFacade */
    public TmsRutasTblFacade() {
    }

    public void create(TmsRutasTbl tmsRutasTbl) {
        em.persist(tmsRutasTbl);
    }

    public void edit(TmsRutasTbl tmsRutasTbl) {
        em.merge(tmsRutasTbl);
    }

    public void destroy(TmsRutasTbl tmsRutasTbl) {
        em.merge(tmsRutasTbl);
        em.remove(tmsRutasTbl);
    }

    public TmsRutasTbl find(Object pk) {
        TmsRutasTbl ruta = (TmsRutasTbl) em.find(TmsRutasTbl.class, pk);
        em.refresh(ruta);
        return ruta;
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRutasTbl as o").getResultList();
    }
    
}
