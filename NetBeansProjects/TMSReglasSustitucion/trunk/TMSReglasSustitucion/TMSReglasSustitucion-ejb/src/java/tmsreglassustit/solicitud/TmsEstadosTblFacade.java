/*
 * TmsEstadosTblFacade.java
 *
 * Created on 21 de octubre de 2007, 07:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustit.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsreglassustit.entidad.TmsEstadosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsEstadosTblFacade implements TmsEstadosTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsEstadosTblFacade */
    public TmsEstadosTblFacade() {
    }

    public void create(TmsEstadosTbl tmsEstadosTbl) {
        em.persist(tmsEstadosTbl);
    }

    public void edit(TmsEstadosTbl tmsEstadosTbl) {
        em.merge(tmsEstadosTbl);
    }

    public void destroy(TmsEstadosTbl tmsEstadosTbl) {
        em.merge(tmsEstadosTbl);
        em.remove(tmsEstadosTbl);
    }

    public TmsEstadosTbl find(Object pk) {
        return (TmsEstadosTbl) em.find(TmsEstadosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsEstadosTbl as o").getResultList();
    }

    public EntityManager getEm() {
        return em;
    }
    
    public List<TmsEstadosTbl> buscarPorTipComponente(String tipo){
        return em.createNamedQuery("TmsEstadosTbl.findByTipoComponente").setParameter("tipoComponente",tipo).getResultList();
    }
}
