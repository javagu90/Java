/*
 * TmsEmpresasTblFacade.java
 *
 * Created on 10 de septiembre de 2007, 08:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsEmpresasTbl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsEmpresasTblFacade implements TmsEmpresasTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
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
    }
    
}
