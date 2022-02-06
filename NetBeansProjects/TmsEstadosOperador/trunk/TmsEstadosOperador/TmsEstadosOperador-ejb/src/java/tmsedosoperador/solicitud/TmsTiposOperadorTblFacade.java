/*
 * TmsTiposOperadorTblFacade.java
 *
 * Created on 9 de diciembre de 2007, 05:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsedosoperador.entidad.TmsTiposOperadorTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTiposOperadorTblFacade implements TmsTiposOperadorTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsTiposOperadorTblFacade */
    public TmsTiposOperadorTblFacade() {
    }

    public void create(TmsTiposOperadorTbl tmsTiposOperadorTbl) {
        em.persist(tmsTiposOperadorTbl);
    }

    public void edit(TmsTiposOperadorTbl tmsTiposOperadorTbl) {
        em.merge(tmsTiposOperadorTbl);
    }

    public void destroy(TmsTiposOperadorTbl tmsTiposOperadorTbl) {
        em.merge(tmsTiposOperadorTbl);
        em.remove(tmsTiposOperadorTbl);
    }

    public TmsTiposOperadorTbl find(Object pk) {
        return (TmsTiposOperadorTbl) em.find(TmsTiposOperadorTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsTiposOperadorTbl as o").getResultList();
    }
    
}
