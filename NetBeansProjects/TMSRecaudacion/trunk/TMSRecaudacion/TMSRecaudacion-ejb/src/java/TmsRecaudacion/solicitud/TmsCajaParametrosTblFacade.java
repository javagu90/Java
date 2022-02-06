/*
 * TmsCajaParametrosTblFacade.java
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
import TmsRecaudacion.entidad.TmsCajaParametrosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCajaParametrosTblFacade implements TmsCajaParametrosTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsCajaParametrosTblFacade */
    public TmsCajaParametrosTblFacade() {
    }

    public void create(TmsCajaParametrosTbl tmsCajaParametrosTbl) {
        em.persist(tmsCajaParametrosTbl);
    }

    public void edit(TmsCajaParametrosTbl tmsCajaParametrosTbl) {
        em.merge(tmsCajaParametrosTbl);
    }

    public void destroy(TmsCajaParametrosTbl tmsCajaParametrosTbl) {
        em.merge(tmsCajaParametrosTbl);
        em.remove(tmsCajaParametrosTbl);
    }

    public TmsCajaParametrosTbl find(Object pk) {
        return (TmsCajaParametrosTbl) em.find(TmsCajaParametrosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCajaParametrosTbl as o").getResultList();
    }
    
}
