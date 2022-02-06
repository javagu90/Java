/*
 * TmsEmpresaParametrosTblFacade.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import TmsRecaudacion.entidad.TmsEmpresaParametrosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsEmpresaParametrosTblFacade implements TmsEmpresaParametrosTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsEmpresaParametrosTblFacade */
    public TmsEmpresaParametrosTblFacade() {
    }

    public void create(TmsEmpresaParametrosTbl tmsEmpresaParametrosTbl) {
        em.persist(tmsEmpresaParametrosTbl);
    }

    public void edit(TmsEmpresaParametrosTbl tmsEmpresaParametrosTbl) {
        em.merge(tmsEmpresaParametrosTbl);
    }

    public void destroy(TmsEmpresaParametrosTbl tmsEmpresaParametrosTbl) {
        em.merge(tmsEmpresaParametrosTbl);
        em.remove(tmsEmpresaParametrosTbl);
    }

    public TmsEmpresaParametrosTbl find(Object pk) {
        return (TmsEmpresaParametrosTbl) em.find(TmsEmpresaParametrosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsEmpresaParametrosTbl as o").getResultList();
    }
    
}
