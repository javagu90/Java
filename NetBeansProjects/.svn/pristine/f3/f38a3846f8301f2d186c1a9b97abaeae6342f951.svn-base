/*
 * TmsActDatosAdicionalesTblFacade.java
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
import tmsactividadesadicionales.entidad.TmsActDatosAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsActDatosAdicionalesTblFacade implements TmsActDatosAdicionalesTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsActDatosAdicionalesTblFacade */
    public TmsActDatosAdicionalesTblFacade() {
    }

    public void create(TmsActDatosAdicionalesTbl tmsActDatosAdicionalesTbl) {
        em.persist(tmsActDatosAdicionalesTbl);
    }

    public void edit(TmsActDatosAdicionalesTbl tmsActDatosAdicionalesTbl) {
        em.merge(tmsActDatosAdicionalesTbl);
    }

    public void destroy(TmsActDatosAdicionalesTbl tmsActDatosAdicionalesTbl) {
        em.merge(tmsActDatosAdicionalesTbl);
        em.remove(tmsActDatosAdicionalesTbl);
    }

    public TmsActDatosAdicionalesTbl find(Object pk) {
        return (TmsActDatosAdicionalesTbl) em.find(TmsActDatosAdicionalesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsActDatosAdicionalesTbl as o").getResultList();
    }
    
}
