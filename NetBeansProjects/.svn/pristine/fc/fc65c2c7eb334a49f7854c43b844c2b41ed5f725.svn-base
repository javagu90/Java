/*
 * TmsBoletosBoleteraTblFacade.java
 *
 * Created on 10 de septiembre de 2007, 08:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsBoletosBoleteraTbl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsBoletosBoleteraTblFacade implements TmsBoletosBoleteraTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsBoletosBoleteraTblFacade */
    public TmsBoletosBoleteraTblFacade() {
    }

    public void create(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl) {
        em.persist(tmsBoletosBoleteraTbl);
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
    
    /** <code>select o from TmsBoletosBoleteraTbl o where o.folioTarjeta = :folio and o.servicio = :nomserv and o.origenTarjeta = :origen</code> */
    public List<TmsBoletosBoleteraTbl> queryTmsBoletosBoleteraTblFindByTarjeta(Object folio, Object origen) {
        return em.createNamedQuery("TmsBoletosBoleteraTbl.findByTarjeta").setParameter("folio", folio).setParameter("origen", origen).getResultList();
    }
    
}
