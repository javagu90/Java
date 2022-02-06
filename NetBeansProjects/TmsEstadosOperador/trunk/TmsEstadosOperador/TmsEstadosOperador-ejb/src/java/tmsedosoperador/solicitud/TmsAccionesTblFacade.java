/*
 * TmsAccionesTblFacade.java
 *
 * Created on 9 de diciembre de 2007, 05:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.solicitud;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsedosoperador.entidad.TmsAccionesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsAccionesTblFacade implements TmsAccionesTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsAccionesTblFacade */
    public TmsAccionesTblFacade() {
    }

    public void create(TmsAccionesTbl tmsAccionesTbl) {
        em.persist(tmsAccionesTbl);
    }

    public void edit(TmsAccionesTbl tmsAccionesTbl) {
        em.merge(tmsAccionesTbl);
    }

    public void destroy(TmsAccionesTbl tmsAccionesTbl) {
        em.merge(tmsAccionesTbl);
        em.remove(tmsAccionesTbl);
    }

    public TmsAccionesTbl find(Object pk) {
        return (TmsAccionesTbl) em.find(TmsAccionesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsAccionesTbl as o").getResultList();
    }

    public List<TmsAccionesTbl> buscarPorComponente( BigInteger comp,  BigInteger comp2){
        return (List<TmsAccionesTbl>)em.createNamedQuery("TmsAccionesTbl.findByComponente").setParameter("componente", comp).setParameter("componente2",comp2).getResultList();
    }
    
    public TmsAccionesTbl buscarPorAccion( String acc ){
        TmsAccionesTbl accion = null;
        try {
            accion = (TmsAccionesTbl)em.createNamedQuery("TmsAccionesTbl.findByAccion").setParameter("accion", acc).getSingleResult();
        } catch (Exception ex) {
            accion = null;
        }
        return accion;
    }
    
}
