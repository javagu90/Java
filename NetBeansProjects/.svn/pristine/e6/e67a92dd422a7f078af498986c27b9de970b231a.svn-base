/*
 * TmsRolesMaestroTblFacade.java
 *
 * Created on 17 de diciembre de 2007, 12:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsroles.entidad.TmsRolesMaestroTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsRolesMaestroTblFacade implements TmsRolesMaestroTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsRolesMaestroTblFacade */
    public TmsRolesMaestroTblFacade() {
    }

    public TmsRolesMaestroTbl create(TmsRolesMaestroTbl tmsRolesMaestroTbl) {
        em.persist(tmsRolesMaestroTbl);
        return tmsRolesMaestroTbl;
    }

    public void edit(TmsRolesMaestroTbl tmsRolesMaestroTbl) {
        em.merge(tmsRolesMaestroTbl);
    }

    public void destroy(TmsRolesMaestroTbl tmsRolesMaestroTbl) {
        em.merge(tmsRolesMaestroTbl);
        em.remove(tmsRolesMaestroTbl);
    }

    public TmsRolesMaestroTbl find(Object pk) {
        return (TmsRolesMaestroTbl) em.find(TmsRolesMaestroTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRolesMaestroTbl as o").getResultList();
    }
    
    public List<TmsRolesMaestroTbl> buscaRolMAestroPorNombreOferta(String nomOfer) {
        List<TmsRolesMaestroTbl> listaRolesMaster = (List<TmsRolesMaestroTbl>) em.createNamedQuery("TmsRolesMaestroTbl.findByOfertaServicioNombre").setParameter("ofertaServicioNombre",nomOfer).getResultList();
        if(listaRolesMaster.size()>0)
        {
            for(int i=0; i<listaRolesMaster.size(); i++)
                em.refresh(listaRolesMaster.get(i));
        }
        return listaRolesMaster;
    }
    
}
