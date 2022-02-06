/*
 * TmsRolesBaseLineasTblFacade.java
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
import tmsroles.entidad.TmsRolesBaseLineasTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsRolesBaseLineasTblFacade implements TmsRolesBaseLineasTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsRolesBaseLineasTblFacade */
    public TmsRolesBaseLineasTblFacade() {
    }

    public void create(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl) {
        em.persist(tmsRolesBaseLineasTbl);
    }

    public void edit(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl) {
        em.merge(tmsRolesBaseLineasTbl);
    }

    public void destroy(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl) {
        em.merge(tmsRolesBaseLineasTbl);
        em.remove(tmsRolesBaseLineasTbl);
    }

    public TmsRolesBaseLineasTbl find(Object pk) {
        return (TmsRolesBaseLineasTbl) em.find(TmsRolesBaseLineasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRolesBaseLineasTbl as o").getResultList();
    }
    
    public List<TmsRolesBaseLineasTbl> buscaLineasPorRolId(long rolBaseId){
        return (List<TmsRolesBaseLineasTbl>)em.createNativeQuery("select * from tms_roles_base_lineas_tbl where rol_base_id = "+rolBaseId+" order by numero_cuadro",TmsRolesBaseLineasTbl.class).getResultList();
    }
}
