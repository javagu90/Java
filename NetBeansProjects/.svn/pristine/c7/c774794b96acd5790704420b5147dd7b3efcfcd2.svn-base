/*
 * TmsRolesBaseTblFacade.java
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
import tmsroles.entidad.TmsRolesBaseTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsRolesBaseTblFacade implements TmsRolesBaseTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsRolesBaseTblFacade */
    public TmsRolesBaseTblFacade() {
    }

    public TmsRolesBaseTbl create(TmsRolesBaseTbl tmsRolesBaseTbl) {
        em.persist(tmsRolesBaseTbl);
        return tmsRolesBaseTbl;
    }

    public void edit(TmsRolesBaseTbl tmsRolesBaseTbl) {
        em.merge(tmsRolesBaseTbl);
    }

    public void destroy(TmsRolesBaseTbl tmsRolesBaseTbl) {
        em.merge(tmsRolesBaseTbl);
        em.remove(tmsRolesBaseTbl);
    }

    public TmsRolesBaseTbl find(Object pk) {
        return (TmsRolesBaseTbl) em.find(TmsRolesBaseTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRolesBaseTbl as o").getResultList();
    }
    
    public List<TmsRolesBaseTbl> buscaRolBase(String categoria, long rolMaestroId)
    {
       List<TmsRolesBaseTbl> lista = (List<TmsRolesBaseTbl>)em.createNativeQuery("select * from tms_roles_base_tbl rb where rb.ROL_BASE_CATEGORIA = '"+categoria+"' and rb.ROL_MAESTRO_ID = "+rolMaestroId,TmsRolesBaseTbl.class).getResultList();
       for(int i=0; i<lista.size(); i++)
       {
           em.refresh(lista.get(i));
          // System.out.println("Colecion de lineas: "+lista.get(i).getTmsRolesBaseLineasTblCollection().size());
       }
       return lista;
    }
    
    public List<TmsRolesBaseTbl> buscaRolBasePorRolMaestroI(long rolMaestroId)
    {
       List<TmsRolesBaseTbl> lista = (List<TmsRolesBaseTbl>)em.createNativeQuery("select * from tms_roles_base_tbl rb where rb.ROL_MAESTRO_ID = "+rolMaestroId+" order by rb.ROL_BASE_CATEGORIA ",TmsRolesBaseTbl.class).getResultList();
       for(int i=0; i<lista.size(); i++)
           em.refresh(lista.get(i));
       return lista;
    }
    
}
