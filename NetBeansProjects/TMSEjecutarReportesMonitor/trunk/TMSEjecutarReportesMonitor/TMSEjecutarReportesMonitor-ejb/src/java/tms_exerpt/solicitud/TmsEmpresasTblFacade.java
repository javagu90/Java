/*
 * TmsEmpresasTblFacade.java
 *
 * Created on 30 de mayo de 2008, 10:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_exerpt.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_exerpt.entidad.TmsEmpresasTbl;

/**
 *
 * @author imunoz
 */
@Stateless
public class TmsEmpresasTblFacade implements TmsEmpresasTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsEmpresasTblFacade */
    public TmsEmpresasTblFacade() {
    }

    public void create(TmsEmpresasTbl tmsEmpresasTbl) {
        em.persist(tmsEmpresasTbl);
    }

    public void edit(TmsEmpresasTbl tmsEmpresasTbl) {
        em.merge(tmsEmpresasTbl);
    }

    public void destroy(TmsEmpresasTbl tmsEmpresasTbl) {
        em.merge(tmsEmpresasTbl);
        em.remove(tmsEmpresasTbl);
    }

    public TmsEmpresasTbl find(Object pk) {
        return (TmsEmpresasTbl) em.find(TmsEmpresasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsEmpresasTbl as o").getResultList();
        //return em.createNativeQuery("select 99999,'Todas','Todas','Todas',1,sysdate,1,sysdate,' ',0,' ' from dual union select * from Tms_Empresas_Tbl",TmsEmpresasTbl.class).getResultList();
    }
    
}
