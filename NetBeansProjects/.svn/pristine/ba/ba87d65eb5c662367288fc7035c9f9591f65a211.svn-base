/*
 * TmsRecoleccionesTblFacade.java
 *
 * Created on 26 de septiembre de 2007, 06:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRecoleccionesTbl;
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
public class TmsRecoleccionesTblFacade implements TmsRecoleccionesTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsRecoleccionesTblFacade */
    public TmsRecoleccionesTblFacade() {
    }

    public void create(TmsRecoleccionesTbl tmsRecoleccionesTbl, String ter) {
        em.persist(tmsRecoleccionesTbl);
        String ids = ""+tmsRecoleccionesTbl.getRecoleccionId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsRecoleccionesTbl.setRecoleccionId(BigDecimal.valueOf(idn));
    }

    public void edit(TmsRecoleccionesTbl tmsRecoleccionesTbl) {
        em.merge(tmsRecoleccionesTbl);
    }

    public void destroy(TmsRecoleccionesTbl tmsRecoleccionesTbl) {
        em.merge(tmsRecoleccionesTbl);
        em.remove(tmsRecoleccionesTbl);
    }

    public TmsRecoleccionesTbl find(Object pk) {
        return (TmsRecoleccionesTbl) em.find(TmsRecoleccionesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsRecoleccionesTbl as o").getResultList();
    }
    
}
