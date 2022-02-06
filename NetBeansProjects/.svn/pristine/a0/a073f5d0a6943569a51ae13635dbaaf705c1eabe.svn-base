/*
 * TmsBaseDatosConfigTblFacade.java
 *
 * Created on 10 de diciembre de 2007, 09:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_ocupacion.entidad.TmsBaseDatosConfigTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsBaseDatosConfigTblFacade implements TmsBaseDatosConfigTblFacadeRemote {

    @PersistenceContext(unitName="TMSConsultaOcupaciones-ejbPU")
    private EntityManager em;
    
    /** Creates a new instance of TmsBaseDatosConfigTblFacade */
    public TmsBaseDatosConfigTblFacade() {
    }

    public void create(TmsBaseDatosConfigTbl tmsBaseDatosConfigTbl) {
        em.persist(tmsBaseDatosConfigTbl);
    }

    public void edit(TmsBaseDatosConfigTbl tmsBaseDatosConfigTbl) {
        em.merge(tmsBaseDatosConfigTbl);
    }

    public void destroy(TmsBaseDatosConfigTbl tmsBaseDatosConfigTbl) {
        em.merge(tmsBaseDatosConfigTbl);
        em.remove(tmsBaseDatosConfigTbl);
    }

    public TmsBaseDatosConfigTbl find(Object pk) {
        return (TmsBaseDatosConfigTbl) em.find(TmsBaseDatosConfigTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsBaseDatosConfigTbl as o").getResultList();
    }
    
    public List<TmsBaseDatosConfigTbl> buscaEsquemasTerminales(String esq){
        List<TmsBaseDatosConfigTbl> listado = (List<TmsBaseDatosConfigTbl>)em.createNamedQuery("TmsBaseDatosConfigTbl.findByEsquemaPropio").getResultList();
        return listado;
    }
    
}
