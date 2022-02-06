/*
 * TmsOperadoresTblFacade.java
 *
 * Created on 2 de octubre de 2007, 01:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsOperadoresTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsOperadoresTblFacade implements TmsOperadoresTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsOperadoresTblFacade */
    public TmsOperadoresTblFacade() {
    }

    public void create(TmsOperadoresTbl tmsOperadoresTbl) {
        em.persist(tmsOperadoresTbl);
    }

    public void edit(TmsOperadoresTbl tmsOperadoresTbl) {
        em.merge(tmsOperadoresTbl);
    }

    public void destroy(TmsOperadoresTbl tmsOperadoresTbl) {
        em.merge(tmsOperadoresTbl);
        em.remove(tmsOperadoresTbl);
    }

    public TmsOperadoresTbl find(Object pk) {
        return (TmsOperadoresTbl) em.find(TmsOperadoresTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsOperadoresTbl as o").getResultList();
    }

    public List<TmsOperadoresTbl> BuscarOperadorPorClave(String claveOp){
        return em.createNamedQuery("TmsOperadoresTbl.findByClaveOperador").setParameter("claveOperador",claveOp).getResultList();
    }
    
}
