/*
 * TmsCortesTblFacade.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import xertmsCorteReca.entidad.TmsCortesTbl;
import xertmsCorteReca.entidad.TmsFechahoraV;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsCortesTblFacade implements TmsCortesTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsCortesTblFacade */
    public TmsCortesTblFacade() {
    }

    public void create(TmsCortesTbl tmsCortesTbl) {
        em.persist(tmsCortesTbl);
    }

    public void edit(TmsCortesTbl tmsCortesTbl) {
        em.merge(tmsCortesTbl);
    }

    public void destroy(TmsCortesTbl tmsCortesTbl) {
        em.merge(tmsCortesTbl);
        em.remove(tmsCortesTbl);
    }

    public TmsCortesTbl find(Object pk) {
        return (TmsCortesTbl) em.find(TmsCortesTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsCortesTbl as o").getResultList();
    }
    
     public List buscarCortes(String fecha, boolean pendiente,String ts) {
        String query = "select cor.CORTE_ID" +
           " ,cor.USUARIO_ID" +
           " ,cor.FECHA_CREACION " +
           " ,cor.ULTIMA_FECHA_ACTUALIZACION " +
           " ,cor.NOMBRE_SESION"+
           " ,cor.ESTADO_CORTE"+
           " from tms_cortes_tbl cor "+
	   //" where trunc(cor.FECHA_CREACION) = '"+fecha+"'"+
           " where to_char(cor.FECHA_CREACION, 'dd/mm/yyyy') = '"+fecha+"'"+
	   " and "+ts;
	   if(pendiente)
             query =   query+" and   cor.ESTADO_CORTE = 'P'";
         return em.createNativeQuery(query).getResultList();
    }    

     public List buscarTodosCortes() {
        String query = "select cor.CORTE_ID" +
           " ,cor.USUARIO_ID" +
           " ,cor.FECHA_CREACION " +
           " ,cor.ULTIMA_FECHA_ACTUALIZACION " +
           " ,cor.NOMBRE_SESION"+
           " ,cor.ESTADO_CORTE"+
           " from tms_cortes_tbl cor "+
           " where cor.NOMBRE_SESION =  'R_AMPERSA' "+
           " and cor.ESTADO_CORTE = 'P'";
         return em.createNativeQuery(query).getResultList();
    }    
     
    /** <code>select o from TmsFechahoraV o</code> */
    public TmsFechahoraV queryTmsFechahoraVFindAll() {
        return  (TmsFechahoraV)em.createNamedQuery("TmsFechahoraV.findAll").getSingleResult();
    }
    
    public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    } 
    
    public Vector buscaDatosServidorReportes(){
        Vector datosServerCortes = new Vector();
        String consulta1 = "SELECT tmsTer.PARAMETRO_VALOR " +
                "FROM TMS_TERMINAL_PARAMETROS_TBL tmsTer " +
                ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo " +
                ",TMS_BASE_DATOS_CONFIG_TBL  tmsBD " +
                "WHERE tmsTer.TERMINAL_ID = tmsBD.TERMINAL_ID " +
                "AND tmsBD.ESQUEMA_PROPIO = 'S' " +
                "AND tmsTer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID " +
                "AND tmsGlo.PARAMETRO_CODIGO = 'P_VLRREPSER'";
        String consulta2 = "SELECT tmsTer.PARAMETRO_VALOR " +
                "FROM TMS_TERMINAL_PARAMETROS_TBL tmsTer " +
                ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo " +
                ",TMS_BASE_DATOS_CONFIG_TBL  tmsBD " +
                "WHERE tmsTer.TERMINAL_ID = tmsBD.TERMINAL_ID " +
                "AND tmsBD.ESQUEMA_PROPIO = 'S' " +
                "AND tmsTer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID " +
                "AND tmsGlo.PARAMETRO_CODIGO = 'P_VLRREPUID'";
        String consulta3= "SELECT tmsTer.PARAMETRO_VALOR " +
                "FROM TMS_TERMINAL_PARAMETROS_TBL tmsTer " +
                ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo " +
                ",TMS_BASE_DATOS_CONFIG_TBL  tmsBD " +
                "WHERE tmsTer.TERMINAL_ID = tmsBD.TERMINAL_ID " +
                "AND tmsBD.ESQUEMA_PROPIO = 'S' " +
                "AND tmsTer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID " +
                "AND tmsGlo.PARAMETRO_CODIGO = 'P_VLRREPURL'";        
        datosServerCortes.add(em.createNativeQuery(consulta1).getResultList()); 
        datosServerCortes.add(em.createNativeQuery(consulta2).getResultList()); 
        datosServerCortes.add(em.createNativeQuery(consulta3).getResultList()); 
        return datosServerCortes;
    }
    
    public Object buscaURL(){
        String consulta = "SELECT tmsTer.PARAMETRO_VALOR " +
                "FROM TMS_TERMINAL_PARAMETROS_TBL tmsTer " +
                ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo " +
                ",TMS_ESTADOS_TBL edo " +
                "WHERE edo.ESTADO_NOMBRE = 'CENTRAL' " +
                "AND tmsTer.TERMINAL_ID = edo.ESTADO_ID " +
                "AND tmsTer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID " +
                "AND tmsGlo.PARAMETRO_CODIGO = 'P_VLRREPURL'";
        return em.createNativeQuery(consulta).getResultList();
    }
    
 public Object buscaFolioCorte(String numUsuario){
//     String consulta = "SELECT NVL(MAX(tmssa.VALOR_ACTIVIDAD),0) " +
//             "FROM TMS_CORTES_TBL              tmsco " +
//             ",TMS_USUARIOS_TBL           tmsusr   " +
//             ",TMS_ACTIVIDADES_SESION_TBL tmsact " +
//             ",TMS_SESION_ACTIVIDADES_TBL tmssa " +
//             "WHERE tmsusr.USUARIO_NUMERO = '"+numUsuario+"'   " +
//             "AND tmsco.USUARIO_ID =  tmsusr.USUARIO_ID   " +
//             "AND tmssa.ACTIVIDAD_SESION_ID= tmsact.ACTIVIDAD_SESION_ID " +
//             "AND tmsact.CLAVE_ACTIVIDAD = 'CORTE'";
     String consulta = "SELECT NVL(MAX(to_number(tmssa.VALOR_SECUENCIAL)),0) "+//"NVL(MAX(to_number(tmssa.VALOR_ACTIVIDAD)),0) " +
             "FROM TMS_CORTES_TBL         tmsco " +
             ",TMS_ACTIVIDADES_SESION_TBL tmsact " +
             ",TMS_SESION_ACTIVIDADES_TBL tmssa " +
             "WHERE tmsco.NOMBRE_SESION = 'R_AMPERSA' " +
             "AND tmssa.CORTE_ID = tmsco.CORTE_ID " +
             "AND tmssa.ACTIVIDAD_SESION_ID= tmsact.ACTIVIDAD_SESION_ID " +
             "AND tmsact.CLAVE_ACTIVIDAD = 'CORTE'";

     
     return em.createNativeQuery(consulta).getSingleResult();
 }    
 
 
    public Object queryBuscaNumeroPaqueteActual(){
       String qr = "SELECT NVL(MAX(to_number(tmssa.VALOR_ACTIVIDAD)),0) ULTIMO_FOLIO "+
		      "FROM TMS_CORTES_TBL tmsco"+
		      ",TMS_SESION_ACTIVIDADES_TBL tmssa"+
	              ",TMS_ACTIVIDADES_SESION_TBL tmsact"+
		      " WHERE tmsco.CORTE_ID = tmssa.CORTE_ID"+
   		      //"	AND tmsco.NOMBRE_SESION = 'R_AMPERSA'"+
                      " AND ( tmsco.NOMBRE_SESION = 'R_AMPERSA' OR tmsco.NOMBRE_SESION ='R_SUPERRAPIDOS')"+  
   		      " AND tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID"+
   		      "	AND (tmsact.CLAVE_ACTIVIDAD = 'RECOL' or tmsact.CLAVE_ACTIVIDAD = 'CORTE') "+
		      " AND TO_CHAR(tmssa.FECHA_CREACION,'DD/MM/RR') = TO_CHAR(SYSDATE,'DD/MM/RR')";
           return  em.createNativeQuery(qr).getSingleResult();
   }
 
}
