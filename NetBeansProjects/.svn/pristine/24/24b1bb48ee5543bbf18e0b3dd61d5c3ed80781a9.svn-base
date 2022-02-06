/*
 * TmsAuditoriaTblFacade.java
 *
 * Created on 1 de octubre de 2007, 11:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsAuditoriaTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsAuditoriaTblFacade implements TmsAuditoriaTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsAuditoriaTblFacade */
    public TmsAuditoriaTblFacade() {
    }

    public void create(TmsAuditoriaTbl tmsAuditoriaTbl, String ter) {
            em.persist(tmsAuditoriaTbl);
        String ids = ""+tmsAuditoriaTbl.getAuditoriaId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsAuditoriaTbl.setAuditoriaId(BigDecimal.valueOf(idn));
            
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
