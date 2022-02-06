/*
 * TmsActividadesSesionTblFacade.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xertmsCorteReca.entidad.TmsActividadesSesionTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsActividadesSesionTblFacade implements TmsActividadesSesionTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsActividadesSesionTblFacade */
    public TmsActividadesSesionTblFacade() {
    }

    public void create(TmsActividadesSesionTbl tmsActividadesSesionTbl) {
        em.persist(tmsActividadesSesionTbl);
    }

    public void edit(TmsActividadesSesionTbl tmsActividadesSesionTbl) {
        em.merge(tmsActividadesSesionTbl);
    }

    public void destroy(TmsActividadesSesionTbl tmsActividadesSesionTbl) {
        em.merge(tmsActividadesSesionTbl);
        em.remove(tmsActividadesSesionTbl);
    }

    public TmsActividadesSesionTbl find(Object pk) {
        return (TmsActividadesSesionTbl) em.find(TmsActividadesSesionTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsActividadesSesionTbl as o").getResultList();
    }
    
    public TmsActividadesSesionTbl buscarActividadPorClave(String clave){
        return (TmsActividadesSesionTbl)em.createNamedQuery("TmsActividadesSesionTbl.findByClaveActividad").setParameter("claveActividad",clave).getSingleResult();
    }
}
