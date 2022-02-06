/*
 * TmsTiposPasajeroTblFacade.java
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
import xertmsCorteReca.entidad.TmsTiposPasajeroTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTiposPasajeroTblFacade implements TmsTiposPasajeroTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsTiposPasajeroTblFacade */
    public TmsTiposPasajeroTblFacade() {
    }

    public void create(TmsTiposPasajeroTbl tmsTiposPasajeroTbl) {
        em.persist(tmsTiposPasajeroTbl);
    }

    public void edit(TmsTiposPasajeroTbl tmsTiposPasajeroTbl) {
        em.merge(tmsTiposPasajeroTbl);
    }

    public void destroy(TmsTiposPasajeroTbl tmsTiposPasajeroTbl) {
        em.merge(tmsTiposPasajeroTbl);
        em.remove(tmsTiposPasajeroTbl);
    }

    public TmsTiposPasajeroTbl find(Object pk) {
        return (TmsTiposPasajeroTbl) em.find(TmsTiposPasajeroTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsTiposPasajeroTbl as o").getResultList();
    }
    
}
