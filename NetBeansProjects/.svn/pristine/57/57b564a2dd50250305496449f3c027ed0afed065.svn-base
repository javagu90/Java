/*
 * TmsSesionActividadesTblFacade.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xertmsCorteReca.entidad.TmsSesionActividadesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsSesionActividadesTblFacade implements TmsSesionActividadesTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsSesionActividadesTblFacade */
    public TmsSesionActividadesTblFacade() {
    }

    public TmsSesionActividadesTbl create(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter) {
        em.persist(tmsSesionActividadesTbl);
        String ids = ""+tmsSesionActividadesTbl.getSesionActividadId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsSesionActividadesTbl.setSesionActividadId(BigDecimal.valueOf(idn));
        return tmsSesionActividadesTbl;
    }

    public void edit(TmsSesionActividadesTbl tmsSesionActividadesTbl) {
        em.merge(tmsSesionActividadesTbl);
    }

    public void destroy(TmsSesionActividadesTbl tmsSesionActividadesTbl) {
        em.merge(tmsSesionActividadesTbl);
        em.remove(tmsSesionActividadesTbl);
    }

    public TmsSesionActividadesTbl find(Object pk) {
        return (TmsSesionActividadesTbl) em.find(TmsSesionActividadesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsSesionActividadesTbl as o").getResultList();
    }
    
}
