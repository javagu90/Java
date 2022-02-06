/*
 * TmsServiciosGastosTblFacade.java
 *
 * Created on 13 de septiembre de 2007, 08:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsServiciosGastosTbl;
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
public class TmsServiciosGastosTblFacade implements TmsServiciosGastosTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsServiciosGastosTblFacade */
    public TmsServiciosGastosTblFacade() {
    }

    public void create(TmsServiciosGastosTbl tmsServiciosGastosTbl) {
        em.persist(tmsServiciosGastosTbl);
    }

    public void edit(TmsServiciosGastosTbl tmsServiciosGastosTbl) {
        em.merge(tmsServiciosGastosTbl);
    }

    public void destroy(TmsServiciosGastosTbl tmsServiciosGastosTbl) {
        em.merge(tmsServiciosGastosTbl);
        em.remove(tmsServiciosGastosTbl);
    }

    public TmsServiciosGastosTbl find(Object pk) {
        return (TmsServiciosGastosTbl) em.find(TmsServiciosGastosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsServiciosGastosTbl as o").getResultList();
    }
    
    public List<TmsServiciosGastosTbl> buscaGastosPorServicio(BigDecimal idserv){
        List<TmsServiciosGastosTbl>  listag = em.createNamedQuery("TmsServiciosGastosTbl.findByServicioId").setParameter("servicioId",idserv).getResultList();
        for(int i=0; i<listag.size(); i++)
        {
            em.refresh(listag.get(i));
            em.refresh(listag.get(i).getRecaudacionGastoId());
        }
        return listag;
    }
}
