/*
 * TmsBoletosNoDisponiblesVFacade.java
 *
 * Created on 7 de diciembre de 2007, 06:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsBolNoDisp.solicitud;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsBolNoDisp.entidad.*;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsBoletosNoDisponiblesVFacade implements TmsBoletosNoDisponiblesVFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsBoletosNoDisponiblesVFacade */
    public TmsBoletosNoDisponiblesVFacade() {
    }

    public void create(TmsBoletosNoDisponiblesV tmsBoletosNoDisponiblesV) {
        em.persist(tmsBoletosNoDisponiblesV);
    }

    public void edit(TmsBoletosNoDisponiblesV tmsBoletosNoDisponiblesV) {
        em.merge(tmsBoletosNoDisponiblesV);
    }

    public void destroy(TmsBoletosNoDisponiblesV tmsBoletosNoDisponiblesV) {
        em.createNativeQuery("delete from TMS_ASIENTOS_NO_DISP_TBL where ASIENTO_NO_DISPONIBLE_ID = "+tmsBoletosNoDisponiblesV.getBolNoDipId()).executeUpdate();
        //em.merge(tmsBoletosNoDisponiblesV);
        //em.remove(tmsBoletosNoDisponiblesV);
    }

    public TmsBoletosNoDisponiblesV find(Object pk) {
        return (TmsBoletosNoDisponiblesV) em.find(TmsBoletosNoDisponiblesV.class, pk);
    }

    public List<TmsBoletosNoDisponiblesV> findAll() {
        //List<TmsBoletosNoDisponiblesV> listado= (List<TmsBoletosNoDisponiblesV>)em.createQuery("select object(o) from TmsBoletosNoDisponiblesV as o order by to_date(o.fechaCorrida,'DD/MM/RRRR') asc, to_date(o.horaSalida,'HH24:MI') asc, o.claveCorrida").getResultList();
        List<TmsBoletosNoDisponiblesV> listado= (List<TmsBoletosNoDisponiblesV>)em.createNativeQuery("select * from Tms_Boletos_No_Disponibles_V o order by to_date(o.fecha_Corrida,'DD/MM/RRRR') asc, to_date(o.hora_Salida,'HH24:MI') asc, o.clave_Corrida",TmsBoletosNoDisponiblesV.class).getResultList();
        for(int i=0; i<listado.size(); i++)
            em.refresh(listado.get(i));
        return listado;
    }
    
}
