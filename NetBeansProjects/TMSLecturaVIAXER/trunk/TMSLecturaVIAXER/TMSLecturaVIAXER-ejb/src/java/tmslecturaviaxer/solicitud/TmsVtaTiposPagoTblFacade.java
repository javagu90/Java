/*
 * TmsVtaTiposPagoTblFacade.java
 *
 * Created on 3 de noviembre de 2007, 03:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmslecturaviaxer.entidad.TmsVtaTiposPagoTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsVtaTiposPagoTblFacade implements TmsVtaTiposPagoTblFacadeRemote {

    @PersistenceContext(unitName="tmslecturaviaxer-ejbCENTRAL")
    private EntityManager em;
    
    /** Creates a new instance of TmsVtaTiposPagoTblFacade */
    public TmsVtaTiposPagoTblFacade() {
    }

    public void create(TmsVtaTiposPagoTbl tmsVtaTiposPagoTbl) {
        em.persist(tmsVtaTiposPagoTbl);
    }

    public void edit(TmsVtaTiposPagoTbl tmsVtaTiposPagoTbl) {
        em.merge(tmsVtaTiposPagoTbl);
    }

    public void destroy(TmsVtaTiposPagoTbl tmsVtaTiposPagoTbl) {
        em.merge(tmsVtaTiposPagoTbl);
        em.remove(tmsVtaTiposPagoTbl);
    }

    public TmsVtaTiposPagoTbl find(Object pk) {
        return (TmsVtaTiposPagoTbl) em.find(TmsVtaTiposPagoTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsVtaTiposPagoTbl as o").getResultList();
    }

    public TmsVtaTiposPagoTbl obtenerPagoPorClave(String clave){
        return (TmsVtaTiposPagoTbl)em.createNamedQuery("TmsVtaTiposPagoTbl.findByClave").setParameter("clave",clave).getSingleResult();
    }    
    
}
