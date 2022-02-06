/*
 * TmsBoletosBoleteraTblFacade.java
 *
 * Created on 10 de noviembre de 2007, 10:13 PM
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
import tmslecturadatafare.entidad.TmsBoletosBoleteraTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsBoletosBoleteraTblFacade implements TmsBoletosBoleteraTblFacadeRemote {

    @PersistenceContext(unitName="TMSLecturaDATAFARE-ejbCENTRAL")
    private EntityManager em;
    
    /** Creates a new instance of TmsBoletosBoleteraTblFacade */
    public TmsBoletosBoleteraTblFacade() {
    }

    public TmsBoletosBoleteraTbl create(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl, String ter) {
        em.persist(tmsBoletosBoleteraTbl);
        String ids = ""+tmsBoletosBoleteraTbl.getBoletoBoleteraId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsBoletosBoleteraTbl.setBoletoBoleteraId(BigDecimal.valueOf(idn));
        return tmsBoletosBoleteraTbl;
    }

    public void edit(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl) {
        em.merge(tmsBoletosBoleteraTbl);
    }

    public void destroy(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl) {
        em.merge(tmsBoletosBoleteraTbl);
        em.remove(tmsBoletosBoleteraTbl);
    }

    public TmsBoletosBoleteraTbl find(Object pk) {
        return (TmsBoletosBoleteraTbl) em.find(TmsBoletosBoleteraTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsBoletosBoleteraTbl as o").getResultList();
    }
    
}
