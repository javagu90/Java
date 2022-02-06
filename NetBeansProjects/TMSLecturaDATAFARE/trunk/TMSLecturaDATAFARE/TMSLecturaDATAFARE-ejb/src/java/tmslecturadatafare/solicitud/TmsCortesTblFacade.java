/*
 * TmsCortesTblFacade.java
 *
 * Created on 10 de septiembre de 2007, 08:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare.solicitud;


import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmslecturadatafare.entidad.TmsCortesTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCortesTblFacade implements TmsCortesTblFacadeRemote {

    @PersistenceContext(unitName="TMSLecturaDATAFARE-ejbCENTRAL")
    private EntityManager em;
    
    /** Creates a new instance of TmsCortesTblFacade */
    public TmsCortesTblFacade() {
    }

    public void create(TmsCortesTbl tmsCortesTbl) {
        em.persist(tmsCortesTbl);
    }

    public void edit(TmsCortesTbl tmsCortesTbl) {
        em.merge(tmsCortesTbl);
    }

    public void destroy(TmsCortesTbl tmsCortesTbl) {
        em.merge(tmsCortesTbl);
        em.remove(tmsCortesTbl);
    }

    public TmsCortesTbl find(Object pk) {
        return (TmsCortesTbl) em.find(TmsCortesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCortesTbl as o").getResultList();
    }
    
    public TmsCortesTbl insertarCorte(TmsCortesTbl tmsCortesTbl, String ter ){
        em.persist(tmsCortesTbl);
        String ids = ""+tmsCortesTbl.getCorteId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsCortesTbl.setCorteId(BigDecimal.valueOf(idn));
        return tmsCortesTbl;      
    }
}
