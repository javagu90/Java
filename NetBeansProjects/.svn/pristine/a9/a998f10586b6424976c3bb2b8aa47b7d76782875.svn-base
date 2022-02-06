/*
 * TmsAuditoriaTblFacade.java
 *
 * Created on 1 de octubre de 2007, 11:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmslecturaviaxer.entidad.TmsAuditoriaTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsAuditoriaTblFacade implements TmsAuditoriaTblFacadeRemote {

    @PersistenceContext(unitName="tmslecturaviaxer-ejbCENTRAL")
    private EntityManager em;
    
    /** Creates a new instance of TmsAuditoriaTblFacade */
    public TmsAuditoriaTblFacade() {
    }

    public void create(TmsAuditoriaTbl tmsAuditoriaTbl) {
        em.persist(tmsAuditoriaTbl);
    }

    public void edit(TmsAuditoriaTbl tmsAuditoriaTbl) {
        em.merge(tmsAuditoriaTbl);
    }

    public void destroy(TmsAuditoriaTbl tmsAuditoriaTbl) {
        em.merge(tmsAuditoriaTbl);
        em.remove(tmsAuditoriaTbl);
    }

    public TmsAuditoriaTbl find(Object pk) {
        return (TmsAuditoriaTbl) em.find(TmsAuditoriaTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsAuditoriaTbl as o").getResultList();
    }
    
}
