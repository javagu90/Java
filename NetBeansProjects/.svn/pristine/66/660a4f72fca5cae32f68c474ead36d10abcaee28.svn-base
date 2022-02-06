/*
 * TmsCorridasTblFacade.java
 *
 * Created on 9 de octubre de 2007, 10:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_ocupacion.entidad.TmsCorridasTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCorridasTblFacade implements TmsCorridasTblFacadeRemote {

    @PersistenceContext(unitName="TMSConsultaOcupaciones-ejbPU")
    private EntityManager em;
    
    /** Creates a new instance of TmsCorridasTblFacade */
    public TmsCorridasTblFacade() {
    }

    public void create(TmsCorridasTbl tmsCorridasTbl) {
        em.persist(tmsCorridasTbl);
    }

    public void edit(TmsCorridasTbl tmsCorridasTbl) {
        em.merge(tmsCorridasTbl);
    }

    public void destroy(TmsCorridasTbl tmsCorridasTbl) {
        em.merge(tmsCorridasTbl);
        em.remove(tmsCorridasTbl);
    }

    public TmsCorridasTbl find(Object pk) {
        return (TmsCorridasTbl) em.find(TmsCorridasTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCorridasTbl as o").getResultList();
    }
    
}
