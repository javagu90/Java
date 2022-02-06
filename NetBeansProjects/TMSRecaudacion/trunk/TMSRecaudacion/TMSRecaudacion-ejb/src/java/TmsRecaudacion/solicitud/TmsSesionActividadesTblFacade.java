/*
 * TmsSesionActividadesTblFacade.java
 *
 * Created on 26 de septiembre de 2007, 06:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsSesionActividadesTbl;
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
public class TmsSesionActividadesTblFacade implements TmsSesionActividadesTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsSesionActividadesTblFacade */
    public TmsSesionActividadesTblFacade() {
    }

    public void create(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter) {
        em.persist(tmsSesionActividadesTbl);
        String ids = ""+tmsSesionActividadesTbl.getSesionActividadId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsSesionActividadesTbl.setSesionActividadId(BigDecimal.valueOf(idn));
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

    public TmsSesionActividadesTbl insertarSesionActividad(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter){
            em.persist(tmsSesionActividadesTbl);
            String ids = ""+tmsSesionActividadesTbl.getSesionActividadId();
            String idnuevo = ter+""+ids;
            long idn = Long.valueOf(idnuevo);
            tmsSesionActividadesTbl.setSesionActividadId(BigDecimal.valueOf(idn));
            return tmsSesionActividadesTbl;
    }
    
}
