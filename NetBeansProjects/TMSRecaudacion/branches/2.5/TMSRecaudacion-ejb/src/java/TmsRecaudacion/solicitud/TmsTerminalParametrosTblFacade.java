/*
 * TmsTerminalParametrosTblFacade.java
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
import TmsRecaudacion.entidad.TmsTerminalParametrosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTerminalParametrosTblFacade implements TmsTerminalParametrosTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsTerminalParametrosTblFacade */
    public TmsTerminalParametrosTblFacade() {
    }

    public void create(TmsTerminalParametrosTbl tmsTerminalParametrosTbl) {
        em.persist(tmsTerminalParametrosTbl);
    }

    public void edit(TmsTerminalParametrosTbl tmsTerminalParametrosTbl) {
        em.merge(tmsTerminalParametrosTbl);
    }

    public void destroy(TmsTerminalParametrosTbl tmsTerminalParametrosTbl) {
        em.merge(tmsTerminalParametrosTbl);
        em.remove(tmsTerminalParametrosTbl);
    }

    public TmsTerminalParametrosTbl find(Object pk) {
        return (TmsTerminalParametrosTbl) em.find(TmsTerminalParametrosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsTerminalParametrosTbl as o").getResultList();
    }
    
}
