/*
 * TmsServiciosTblFacade.java
 *
 * Created on 30 de mayo de 2008, 09:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_exerpt.solicitud;

import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_exerpt.entidad.TmsServiciosTbl;

/**
 *
 * @author imunoz
 */
@Stateless
public class TmsServiciosTblFacade implements TmsServiciosTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsServiciosTblFacade */
    public TmsServiciosTblFacade() {
    }

    public void create(TmsServiciosTbl tmsServiciosTbl) {
        em.persist(tmsServiciosTbl);
    }

    public void edit(TmsServiciosTbl tmsServiciosTbl) {
        em.merge(tmsServiciosTbl);
    }

    public void destroy(TmsServiciosTbl tmsServiciosTbl) {
        em.merge(tmsServiciosTbl);
        em.remove(tmsServiciosTbl);
    }

    public TmsServiciosTbl find(Object pk) {
        return (TmsServiciosTbl) em.find(TmsServiciosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsServiciosTbl as o").getResultList();
        //return em.createNativeQuery("select -1,' ',' ','Todos','Todos',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',1,sysdate,1,sysdate,' ',0,' ' from Tms_Servicios_Tbl union select * from Tms_Servicios_Tbl", TmsServiciosTbl.class).getResultList();
    }
    
    public Vector getAutobusId(String numeroEconomico){
        String consulta = "SELECT a.AUTOBUS_ID " +
                "FROM TMS_AUTOBUSES_TBL a " +
                "WHERE a.NUMERO_ECONOMICO = "+numeroEconomico;
        
        return (Vector) em.createNativeQuery(consulta).getResultList();
    }

    public Vector getOperadorId(String claveOperador){
        String consulta = "SELECT o.OPERADOR_ID " +
                "FROM TMS_OPERADORES_TBL o " +
                "WHERE o.CLAVE_OPERADOR = "+claveOperador;
        
        return (Vector) em.createNativeQuery(consulta).getResultList();
    }
    
}
