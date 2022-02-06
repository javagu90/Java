/*
 * TmsDatosAdicionalesTblFacade.java
 *
 * Created on 2 de octubre de 2007, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsDatosAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsDatosAdicionalesTblFacade implements TmsDatosAdicionalesTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsDatosAdicionalesTblFacade */
    public TmsDatosAdicionalesTblFacade() {
    }

    public void create(TmsDatosAdicionalesTbl tmsDatosAdicionalesTbl) {
        em.persist(tmsDatosAdicionalesTbl);
    }

    public void edit(TmsDatosAdicionalesTbl tmsDatosAdicionalesTbl) {
        em.merge(tmsDatosAdicionalesTbl);
    }

    public void destroy(TmsDatosAdicionalesTbl tmsDatosAdicionalesTbl) {
        em.merge(tmsDatosAdicionalesTbl);
        em.remove(tmsDatosAdicionalesTbl);
    }

    public TmsDatosAdicionalesTbl find(Object pk) {
        return (TmsDatosAdicionalesTbl) em.find(TmsDatosAdicionalesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsDatosAdicionalesTbl as o").getResultList();
    }
    
}
