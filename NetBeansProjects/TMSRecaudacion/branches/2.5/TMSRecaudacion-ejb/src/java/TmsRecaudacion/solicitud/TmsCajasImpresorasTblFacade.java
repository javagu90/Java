/*
 * TmsCajasImpresorasTblFacade.java
 *
 * Created on 3 de septiembre de 2007, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import TmsRecaudacion.entidad.TmsCajasImpresorasTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCajasImpresorasTblFacade implements TmsCajasImpresorasTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsCajasImpresorasTblFacade */
    public TmsCajasImpresorasTblFacade() {
    }

    public void create(TmsCajasImpresorasTbl tmsCajasImpresorasTbl) {
        em.persist(tmsCajasImpresorasTbl);
    }

    public void edit(TmsCajasImpresorasTbl tmsCajasImpresorasTbl) {
        em.merge(tmsCajasImpresorasTbl);
    }

    public void destroy(TmsCajasImpresorasTbl tmsCajasImpresorasTbl) {
        em.merge(tmsCajasImpresorasTbl);
        em.remove(tmsCajasImpresorasTbl);
    }

    public TmsCajasImpresorasTbl find(Object pk) {
        return (TmsCajasImpresorasTbl) em.find(TmsCajasImpresorasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCajasImpresorasTbl as o").getResultList();
    }
    
}
