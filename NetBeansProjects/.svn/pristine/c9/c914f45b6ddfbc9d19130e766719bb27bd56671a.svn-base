/*
 * TmsFlotillasTblFacade.java
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
import tmsreglassustit.entidad.TmsFlotillasTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsFlotillasTblFacade implements TmsFlotillasTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsFlotillasTblFacade */
    public TmsFlotillasTblFacade() {
    }

    public void create(TmsFlotillasTbl tmsFlotillasTbl) {
        em.persist(tmsFlotillasTbl);
    }

    public void edit(TmsFlotillasTbl tmsFlotillasTbl) {
        em.merge(tmsFlotillasTbl);
    }

    public void destroy(TmsFlotillasTbl tmsFlotillasTbl) {
        em.merge(tmsFlotillasTbl);
        em.remove(tmsFlotillasTbl);
    }

    public TmsFlotillasTbl find(Object pk) {
        return (TmsFlotillasTbl) em.find(TmsFlotillasTbl.class, pk);
    }

    public List findAll() {
        List listado = em.createQuery("select object(o) from TmsFlotillasTbl as o").getResultList();
        for(int i=0; i<listado.size(); i++)
        {
            TmsFlotillasTbl flotilla = (TmsFlotillasTbl) listado.get(i);
            em.refresh(flotilla);
            System.out.println("La Flotilla: "+flotilla.getNombreFlotilla());
            System.out.println("La Coleccion de flotillas de sustitucion  es de: "+flotilla.getTmsReglasSustTblCollection().size());
        }
        return listado;

    }
    
}
