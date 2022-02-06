/*
 * TmsInspRecolFacade.java
 *
 * Created on 15 de agosto de 2007, 11:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsinsprecol.solicitud;   

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsInspRecolFacade implements xertmsinsprecol.solicitud.TmsInspRecolFacadeRemote {
    @PersistenceContext(unitName="TMSInspRecol-ejbPU")
    EntityManager em;
    /*
     * Creates a new instance of TmsInspRecolFacade
     */
    public TmsInspRecolFacade() {
    }
    
    
    
    public List<Object[]> obtenerEstadoCajasActivas(String Terminal, String usuarioNumero){
        String consulta = "SELECT V.* "+
                "FROM TMS_CORTES_LIMITES_V V "+
                "WHERE V.TERMINAL = '"+Terminal+"' " +
                "AND V.CLAVE_USUARIO = NVL('"+usuarioNumero+"',V.CLAVE_USUARIO)";
        try{
            return em.createNativeQuery(consulta).getResultList();
        }catch(NoResultException nrex){
            return null;
        }
    }
}