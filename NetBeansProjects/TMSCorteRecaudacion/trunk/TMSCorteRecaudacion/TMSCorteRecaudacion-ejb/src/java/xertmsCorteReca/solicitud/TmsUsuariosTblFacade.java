/*
 * TmsUsuariosTblFacade.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xertmsCorteReca.entidad.TmsUsuariosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsUsuariosTblFacade implements TmsUsuariosTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsUsuariosTblFacade */
    public TmsUsuariosTblFacade() {
    }

    public void create(TmsUsuariosTbl tmsUsuariosTbl) {
        em.persist(tmsUsuariosTbl);
    }

    public void edit(TmsUsuariosTbl tmsUsuariosTbl) {
        em.merge(tmsUsuariosTbl);
    }

    public void destroy(TmsUsuariosTbl tmsUsuariosTbl) {
        em.merge(tmsUsuariosTbl);
        em.remove(tmsUsuariosTbl);
    }

    public TmsUsuariosTbl find(Object pk) {
        return (TmsUsuariosTbl) em.find(TmsUsuariosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsUsuariosTbl as o").getResultList();
    }
    
    public Object buscarRecolecciones(String numUsr, BigDecimal corteId){
        String consulta = "  SELECT  NVL(SUM(tmsrec.CANTIDAD),0)" +
                ",NVL(SUM(tmsrec.CANTIDAD * tmsrec.MONTO),0)" +
                " FROM TMS_RECOLECCIONES_TBL tmsrec " +
                ",TMS_SESION_ACTIVIDADES_TBL tmssa " +
                ",TMS_ACTIVIDADES_SESION_TBL tmsact " +
                ",TMS_VTA_TIPOS_PAGO_TBL     tmspagos " +
                ",TMS_USUARIOS_TBL  		  tmsus" +
                ",TMS_CORTES_TBL              tmsco " +
                " WHERE tmsus.USUARIO_NUMERO = "+numUsr+ 
                " AND  tmssa.CREADO_POR = tmsus.USUARIO_ID" +
                " AND  tmsco.CORTE_ID = " +corteId+
                " AND  tmspagos.NOMBRE = 'EFECTIVO'" +
                " AND  tmsact.NOMBRE_ACTIVIDAD = 'RECOLECCION'" +
                " AND  tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID" +
                " AND  tmsrec.TIPO_PAGO_ID = tmspagos.TIPO_PAGO_ID" +
                " AND  tmsrec.SESION_ACTIVIDAD_ID = tmssa.SESION_ACTIVIDAD_ID" +
                " AND  tmssa.CORTE_ID = tmsco.CORTE_ID";
        
        return em.createNativeQuery(consulta).getSingleResult();
    }


}
