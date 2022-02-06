/*
 * TmsTarjetasViajeTblFacade.java
 *
 * Created on 2 de noviembre de 2007, 09:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsTarjetasViajeTbl;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTarjetasViajeTblFacade implements TmsTarjetasViajeTblFacadeRemote {

    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsTarjetasViajeTblFacade */
    public TmsTarjetasViajeTblFacade() {
    }

    public void create(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.persist(tmsTarjetasViajeTbl);
    }

    public void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
    }

    public void destroy(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
        em.remove(tmsTarjetasViajeTbl);
    }

    public TmsTarjetasViajeTbl find(Object pk) {
        TmsTarjetasViajeTbl tar = (TmsTarjetasViajeTbl) em.find(TmsTarjetasViajeTbl.class, pk);
        em.refresh(tar);
        return tar;
    }
 
    public List findAll() {
        return em.createQuery("select object(o) from TmsTarjetasViajeTbl as o").getResultList();
    }

    public List<TmsTarjetasViajeTbl> queryTmsTarjetasViajeTblFindAByFolio(String foltar) {
        return em.createNamedQuery("TmsTarjetasViajeTbl.findByFolioTarjeta").setParameter("folioTarjeta", foltar).getResultList();
    }
    
     public List<TmsTarjetasViajeTbl> queryTmsTarjetasViajeTblFindAByFolio2(String foltar) {
         String consulta= "select * from tms_tarjetas_viaje_tbl tar where tar.FOLIO_TARJETA = '"+foltar+"'";
         System.out.println("Consulta: "+consulta);
         List<TmsTarjetasViajeTbl> list = (List<TmsTarjetasViajeTbl>)em.createNativeQuery(consulta,TmsTarjetasViajeTbl.class).getResultList();
         List<TmsTarjetasViajeTbl> listado = new ArrayList<TmsTarjetasViajeTbl>();
         for(TmsTarjetasViajeTbl t : list)
         {
         //VAGL 21/04/2014 Se agrego el campo recaudacion_Automatica para cambiar el estado de la tarjeta a ABIERTA o CONFIRMADA en base a este campo
            String consulta2 = "select NVL(R.ADICIONAL5,'N') recaudacion_Automatica from tms_tarjetas_viaje_tbl tar left join TMS_CORRIDAS_TBL c on C.CORRIDA_ID = TAR.CORRIDA_ID left join TMS_RUTAS_TBL r on R.RUTA_ID = C.RUTA_ID where tar.TARJETA_VIAJE_ID = "+t.getTarjetaViajeId().longValue();
             Vector res = (Vector)em.createNativeQuery(consulta2).getSingleResult();
             t.setRecaudacionAutomatica(res.get(0).toString());
             listado.add(t);
         }
         return listado;
    }
      
    
    
}
