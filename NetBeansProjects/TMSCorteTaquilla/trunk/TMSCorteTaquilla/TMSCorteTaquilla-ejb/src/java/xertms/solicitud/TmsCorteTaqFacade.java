/*
 * TmsCorteTaqFacade.java
 *
 * Created on 15 de agosto de 2007, 11:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.solicitud;   

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OraclePreparedStatement;
import xertms.entidad.TmsCortesTbl;
import xertms.entidad.TmsDesgloceEfectivoTbl;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsRecoleccionesTbl;
import xertms.entidad.TmsSesionActividadesTbl;
import xertms.entidad.TmsTiposPasajeroTbl;
import xertms.entidad.TmsVtaTiposPagoTbl;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsCorteTaqFacade implements xertms.solicitud.TmsCorteTaqFacadeRemote {
    @PersistenceContext(unitName="TMSDB_CorteTaquilla-ejbPU")
    EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    /**
     * Creates a new instance of TmsCorteTaqFacade
     */
    public TmsCorteTaqFacade() {
    }
    
    public List<TmsCortesTbl> queryTmsCortesTblByFecha(Timestamp fecha){
        return em.createNamedQuery("TmsCortesTbl.findByFechaCreacion").setParameter("FechaCreacion",fecha).getResultList();
    }
    
    public List<TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblfindAll(){
        return em.createNamedQuery("TmsTiposPasajeroTbl.findAll").getResultList();
    }
    
    public List<TmsVtaTiposPagoTbl> queryTmsVtaTiposPagoTblfindAll(){
        return em.createNamedQuery("TmsVtaTiposPagoTbl.findAll").getResultList();
    }
    
    public List<Object[]> queryTmsCortesTblForSales(String fecha, String pendientes){
        String Consulta = "SELECT "+
                "COR.CORTE_ID "+
                ",USU.USUARIO_NUMERO "+
                ",USU.USUARIO_NOMBRE "+
                ",COR.FECHA_CREACION "+
                ",COR.ULTIMA_FECHA_ACTUALIZACION "+
                ",COR.ESTADO_CORTE "+
                ",USU.USUARIO_ID "+
                ",COR.AUTORIZADO_POR "+
                "FROM "+
                "TMS_CORTES_TBL COR "+
                ",TMS_USUARIOS_TBL USU "+
                "WHERE "+
                "TRUNC(COR.FECHA_CREACION) = TO_DATE('"+fecha+"','DD/MM/YYYY') "+
//                "AND	 COR.NOMBRE_SESION NOT LIKE 'R_%' "+
//                "AND	 COR.ESTADO_CORTE = NVL('"+pendientes+"',COR.ESTADO_CORTE) "+
                "AND	 COR.NOMBRE_SESION NOT LIKE 'R_%' ";
                if(pendientes.equals("P"))
                   Consulta = Consulta+ "AND	 COR.ESTADO_CORTE IN ('P','T')";// = NVL('"+pendientes+"',COR.ESTADO_CORTE) "+
                Consulta = Consulta+"AND	 COR.USUARIO_ID = USU.USUARIO_ID";
        try{
            System.out.println(Consulta);
            return em.createNativeQuery(Consulta).getResultList();
        }catch(Exception ejbex){
            return null;
        }
    }
    
    public Object queryTmsCortesPendientesEnDiaLaboral(String dia){
        Vector x = null;
//        String Consulta = "SELECT NVL(" +
//                "(SELECT DISTINCT(1) "+
//            "FROM TMS_CORTES_TBL CORTE "+
//            ",TMS_USUARIOS_TBL	USUARIO "+
//            "WHERE CORTE.ESTADO_CORTE = 'P' "+
//            "AND	  USUARIO.USUARIO_ID = CORTE.USUARIO_ID "+
//            "AND CORTE.FECHA_CREACION BETWEEN "+
//                    "(SELECT TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR, 'DD/MM/YYYY HH24:MI:SS')-1 "+
//                    "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
//                    ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL "+
//                    "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "+
//                    "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID) "+
//                    "AND "+
//                    "(SELECT TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR || ':59', 'DD/MM/YYYY HH24:MI:SS')-(1/1440) "+
//                    "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
//                    ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL "+
//                    "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "+
//                    "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID)), " +
//            "0) RESULTADO FROM DUAL";
        String Consulta = "SELECT NVL( " +
                "(SELECT DISTINCT(1) FROM ( " +
                "(SELECT DISTINCT(1)  " +
                " FROM TMS_CORTES_TBL CORTE  " +
                ",TMS_USUARIOS_TBL	USUARIO  " +
                "WHERE CORTE.ESTADO_CORTE = 'P'  " +
                "AND	  USUARIO.USUARIO_ID = CORTE.USUARIO_ID  " +
                "AND CORTE.FECHA_CREACION BETWEEN  " +
                "(SELECT TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR, 'DD/MM/YYYY HH24:MI:SS')-1  " +
                "FROM TMS_PARAMETROS_CONFIG_TBL PARAM  " +
                ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL  " +
                "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ'  " +
                "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID)  " +
                " AND  " +
                "(SELECT (TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR || ':59', 'DD/MM/YYYY HH24:MI:SS')-(1/1440)) " +
                "FROM TMS_PARAMETROS_CONFIG_TBL PARAM  " +
                ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL  " +
                "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ'  " +
                "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID) " +
                ") " +
                "UNION   " +
                "(SELECT DISTINCT(1)  " +
                "FROM TMS_CORTES_TBL CORTE  " +
                ",TMS_USUARIOS_TBL	USUARIO  " +
                "WHERE CORTE.ESTADO_CORTE = 'T'  " +
                "AND	  USUARIO.USUARIO_ID = CORTE.USUARIO_ID  " +
                "AND CORTE.FECHA_CREACION BETWEEN  " +
                "(SELECT (TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR, 'DD/MM/YYYY HH24:MI:SS')-1)-1  " +
                "FROM TMS_PARAMETROS_CONFIG_TBL PARAM  " +
                ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL  " +
                "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ'  " +
                "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID)  " +
                "AND " +
                "(SELECT (TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR || ':59', 'DD/MM/YYYY HH24:MI:SS')-(1/1440))-1 " +
                "FROM TMS_PARAMETROS_CONFIG_TBL PARAM  " +
                ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL  " +
                "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ'  " +
                "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID)  " +
                ") " +
                ")	 " +
                ") " +
                ",0) RESULTADO FROM DUAL";
        try{
            //System.out.println("Corte find e dia: "+Consulta);
            x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            if(y.get(0).toString().equals("1")) return true;
            return false;
        }catch(Exception ejbex){
            return null;
        }
    }
    /*** IMM 28/01/2009 ***/
    public String getFechaInicialCorte(String dia){
        String fechaDesde = "";
       String Consulta = "SELECT TO_CHAR(TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR, 'DD/MM/YYYY HH24:MI:SS')-1,'DD/MM/YYYY HH24:MI:SS') "+
                    "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
                    ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL "+
                    "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "+
                    "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID"; 
       try{
           fechaDesde = em.createNativeQuery(Consulta).getSingleResult().toString();
           fechaDesde = fechaDesde.replace('[',' ');
           fechaDesde = fechaDesde.replace(']',' ');
           fechaDesde = fechaDesde.trim();
       }catch(EJBException ejbex){
           ejbex.printStackTrace();
           return null;
       }
           return fechaDesde;
    }

    public String getFechaFinalCorte(String dia){
        String fechaHasta = "";
       String Consulta = "SELECT TO_CHAR(TO_DATE('"+dia+"' || ' ' || PARAMGLOBAL.PARAMETRO_VALOR || ':59', 'DD/MM/YYYY HH24:MI:SS')-(1/1440),'DD/MM/YYYY HH24:MI:SS') "+
                    "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
                    ",TMS_GLOBAL_PARAMETROS_TBL	   PARAMGLOBAL "+
                    "WHERE PARAM.PARAMETRO_CODIGO = 'P_TMPCORCAJ' "+
                    "AND	  PARAMGLOBAL.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID"; 
       try{
           fechaHasta = em.createNativeQuery(Consulta).getSingleResult().toString();
           fechaHasta = fechaHasta.replace('[',' ');
           fechaHasta = fechaHasta.replace(']',' ');
           fechaHasta = fechaHasta.trim();
       }catch(EJBException ejbex){
           ejbex.printStackTrace();
           return null;
       }
       return fechaHasta;
    }
    /*** IMM 28/01/2009 ***/
    
    public List<Object[]> queryFolioFinal(long corteId){
        String Consulta = "SELECT CORV.EMPRESA_ID, CORV.EMPRESA_NOMBRE, CORV.CORTE_ID, CORV.VALOR_ACTIVIDAD_CORTE, CORV.EMPRESA_NOMBRE_CORTO "+
                "FROM TMS_CORTES_V CORV "+
                "WHERE CORV.CORTE_ID = "+corteId+" "+
                "AND CORV.CLAVE_ACTIVIDAD_CORTE IN ('FOLFIN','REFOLFIN') "+
                "AND (CORV.EMPRESA_ID, CORV.CORTE_ID, FECHA_ACTIVIDAD_CORTE ) in " +
                                       "(SELECT EMPRESA_ID, CORTE_ID ,MAX(FECHA_ACTIVIDAD_CORTE) "+
                                       "FROM TMS_CORTES_V b "+
                                       "WHERE CORTE_ID=CORV.CORTE_ID "+
                                       "AND CLAVE_ACTIVIDAD_CORTE IN ('FOLFIN','REFOLFIN') "+
				       "GROUP BY EMPRESA_ID, CORTE_ID "+
                                       ")";
        try{
            return em.createNativeQuery(Consulta).getResultList();
        }catch(Exception ejbex){
            return null;
        }
    }
    
    public Object queryUltimoFolioVendido(long corteId, String empresaNombre){
        /*String Consulta = "SELECT MAX(TO_NUMBER(FOLIO_PREIMPRESO)) "+
                          "FROM TMS_BOLETOS_VENTA_TBL BOLETOS "+
                          "WHERE "+
                          "BOLETOS.CORTE_ID="+corteId+" "+
                          "AND BOLETOS.EMPRESA='"+empresaNombre+"'";*/
        String Consulta = "SELECT VALOR_ACTIVIDAD "+
                            "FROM TMS_SESION_ACTIVIDADES_TBL "+
                            "WHERE CORTE_ID="+corteId+" "+
                            "AND   EMPRESA_ID=(SELECT EM.EMPRESA_ID FROM TMS_EMPRESAS_TBL EM WHERE EM.EMPRESA_NOMBRE='"+empresaNombre+"') "+
                            "AND   ACTIVIDAD_SESION_ID=(SELECT ACT.ACTIVIDAD_SESION_ID FROM TMS_ACTIVIDADES_SESION_TBL ACT WHERE ACT.CLAVE_ACTIVIDAD='ULTFOLVEN')";
        try{
            return em.createNativeQuery(Consulta).getSingleResult();
        }catch(Exception ejbex){
            return null;
        }
    }
    
    public Object queryFolioInicial(long corteId, long empresaId){
        String Consulta = "SELECT CORV.VALOR_ACTIVIDAD_CORTE "+
                          "FROM TMS_CORTES_V CORV  "+
                          "WHERE CORV.CORTE_ID = "+corteId+" "+
                          "AND CORV.EMPRESA_ID = "+empresaId+" "+
                          "AND CORV.CLAVE_ACTIVIDAD_CORTE ='FOLINI' "+
                          "AND (CORV.FECHA_ACTIVIDAD_CORTE ) in ( "+
				       "SELECT MIN(FECHA_ACTIVIDAD_CORTE) "+
                                       "FROM TMS_CORTES_V b "+
				       "WHERE CORTE_ID=CORV.CORTE_ID "+
				       "AND EMPRESA_ID=CORV.EMPRESA_ID "+
                                       "AND CLAVE_ACTIVIDAD_CORTE = 'FOLINI' "+
				       ")";
        try{
            return em.createNativeQuery(Consulta).getSingleResult();
        }catch(Exception ejbex){
            return null;
        }
    }
    
    public Object queryRecolMonto(long corteId){
        String Consulta =
                "SELECT COUNT(tmssa.ACTIVIDAD_SESION_ID), NVL(SUM(tmssa.VALOR_ACTIVIDAD),0) "+
                "FROM "+
                "TMS_SESION_ACTIVIDADES_TBL tmssa "+
                ",TMS_ACTIVIDADES_SESION_TBL tmsact "+
                "WHERE tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID "+
                "AND tmsact.CLAVE_ACTIVIDAD = 'RECOL' "+
                "AND tmssa.CORTE_ID = "+corteId;
        try{
            return em.createNativeQuery(Consulta).getSingleResult();
        }catch(Exception ejbex){
            System.out.println("Error en Recoleccion: ");
            ejbex.printStackTrace();
            return null;
        }
    }
    // TRANSACCION
    public boolean updateCorteRealizado(long corteId, long usuarioId, Timestamp fechaAct, String esquema, String edoCorte){
        try{
            TmsCortesTbl tmsCortesTbl = em.find(TmsCortesTbl.class, corteId);
            em.refresh(tmsCortesTbl);
            if(edoCorte.equals("T") || edoCorte.equals("B"))
                tmsCortesTbl.setEstadoCorte("D");
            else
                tmsCortesTbl.setEstadoCorte("R");
            tmsCortesTbl.setAutorizadoPor(usuarioId);
            tmsCortesTbl.setUltimaActualizacionPor(usuarioId);
            tmsCortesTbl.setUltimaFechaActualizacion(fechaAct);
            tmsCortesTbl.setReplicacionOrigen(esquema);
            tmsCortesTbl.setReplicacionEstado("P");
            em.merge(tmsCortesTbl);
            return true;
         }catch(Exception ejbex){
            return false;
        }
    }
    
    public boolean updateCorteEnProceso(long corteId, long usuarioId, Timestamp fechaAct, String estado){
        try{
            TmsCortesTbl tmsCortesTbl = em.find(TmsCortesTbl.class, corteId);
            em.refresh(tmsCortesTbl);
            //tmsCortesTbl.setEstadoCorte("E");
            tmsCortesTbl.setEstadoCorte(estado);
            tmsCortesTbl.setUltimaActualizacionPor(usuarioId);
            //tmsCortesTbl.setUltimaFechaActualizacion(fechaAct);
            em.merge(tmsCortesTbl);
            return true;
         }catch(Exception ejbex){
            return false;
        }
    }
    
    public boolean updateCortePendiente(long corteId, long usuarioId, Timestamp fechaAct,String pedoAnteriorCorte){
        try{
            TmsCortesTbl tmsCortesTbl = em.find(TmsCortesTbl.class, corteId);
            em.refresh(tmsCortesTbl);
            if(pedoAnteriorCorte.equals("E")) pedoAnteriorCorte = "P";
            if(pedoAnteriorCorte.equals("B")) pedoAnteriorCorte = "T";
            tmsCortesTbl.setEstadoCorte(pedoAnteriorCorte);
            tmsCortesTbl.setUltimaActualizacionPor(usuarioId);
            //tmsCortesTbl.setUltimaFechaActualizacion(fechaAct);
            em.merge(tmsCortesTbl);
            return true;
         }catch(Exception ejbex){
            return false;
        }
    }
    
    public Object addSesionCorte(String PrefijoTerminal, TmsSesionActividadesTbl tmsSesionActividadesTbl){
        try{
            em.persist(tmsSesionActividadesTbl);
            tmsSesionActividadesTbl.setSesionActividadId(Long.valueOf(PrefijoTerminal+tmsSesionActividadesTbl.getSesionActividadId()));
            return tmsSesionActividadesTbl.getSesionActividadId();
         }catch(Exception ejbex){
            ejbex.printStackTrace();
           return null;
        }
    }
    
    public Object queryActividadSesionCorteGetId(){
        String Consulta = "SELECT "+
                          "ACT.ACTIVIDAD_SESION_ID "+
                          "FROM "+
                          "TMS_ACTIVIDADES_SESION_TBL ACT "+
                          "WHERE "+
                          "ACT.CLAVE_ACTIVIDAD = 'CORTE'";
        try{
            return em.createNativeQuery(Consulta).getSingleResult();
        }catch(Exception ejbex){
            return null;
        }
    }
    
    public Object queryActividadSesionUltFolioGetId(){
        String Consulta = "SELECT "+
                          "ACT.ACTIVIDAD_SESION_ID "+
                          "FROM "+
                          "TMS_ACTIVIDADES_SESION_TBL ACT "+
                          "WHERE "+
                          "ACT.CLAVE_ACTIVIDAD = 'ULTFOLVEN'";
        try{
            return em.createNativeQuery(Consulta).getSingleResult();
        }catch(Exception ejbex){
            return null;
        }
    }
    
    public Object addRecoleccion(String PrefijoTerminal, TmsRecoleccionesTbl tmsRecoleccionesTbl){
        try{
            em.persist(tmsRecoleccionesTbl);
            tmsRecoleccionesTbl.setRecoleccionId(Long.valueOf(PrefijoTerminal+tmsRecoleccionesTbl.getRecoleccionId()));
            return tmsRecoleccionesTbl.getRecoleccionId();
         }catch(Exception ejbex){
            return null;
        }
    }
    
    public boolean addDesgloce(String PrefijoTerminal, TmsDesgloceEfectivoTbl tmsDesgloceEfectivoTbl){
        try{
            em.persist(tmsDesgloceEfectivoTbl);
            tmsDesgloceEfectivoTbl.setDesgloceEfectivoId(Long.valueOf(PrefijoTerminal+tmsDesgloceEfectivoTbl.getDesgloceEfectivoId()));
            return true;
         }catch(Exception ejbex){
            return false;
        }
    }
    
    public long obtenerFolioActIdIniCor(String claveActividad, long usuarioId){
        long folio;
        String consultaFolio;
        consultaFolio = "SELECT sesion.SESION_ACTIVIDAD_ID" +
                " FROM (" +
                "SELECT tmssa.SESION_ACTIVIDAD_ID" +
                " FROM TMS_CORTES_TBL            tmses" +
                " ,TMS_SESION_ACTIVIDADES_TBL tmssa" +
                " ,TMS_ACTIVIDADES_SESION_TBL tmsact" +
                " WHERE tmses.CORTE_ID = tmssa.CORTE_ID" +
                "  AND tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID" +
                "  AND tmsact.CLAVE_ACTIVIDAD = '"+claveActividad+"'" +
                "  AND tmses.USUARIO_ID = "+usuarioId +
                " ORDER BY tmssa.FECHA_HORA_ACTIVIDAD DESC) sesion" +
                " WHERE ROWNUM = 1";
        //System.out.println(consultaFolio);
        try{
            String folioS = em.createNativeQuery(consultaFolio).getSingleResult().toString();
            folioS = folioS.replace("[","");
            folioS = folioS.replace("]","");
            folio = Long.valueOf(folioS);
        }catch(NoResultException ex){
            return -1;
        }
        return folio;
    }
    
    public Date obtenerFechaSesion(long sesionId){
        String consultaFolio;
        consultaFolio = "SELECT "+
		"tmssa.FECHA_HORA_ACTIVIDAD "+
		"FROM "+
                "TMS_SESION_ACTIVIDADES_TBL tmssa "+
                "WHERE tmssa.SESION_ACTIVIDAD_ID="+sesionId;
        try{
            String folioS = em.createNativeQuery(consultaFolio).getSingleResult().toString();
            folioS = folioS.replace("[","");
            folioS = folioS.replace("]","");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return format.parse(folioS);
            } catch (ParseException ex) {
                return null;
            }
        }catch(NoResultException ex){
            return null;
        }
    }
    
    public String[] paramReporte(){
        String Consulta1 = 
                "SELECT tmsTer.PARAMETRO_VALOR "+
                  "FROM TMS_TERMINAL_PARAMETROS_TBL tmsTer "+
                         ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo "+
                         ",TMS_BASE_DATOS_CONFIG_TBL  tmsBD "+
                 "WHERE tmsTer.TERMINAL_ID = tmsBD.TERMINAL_ID "+
                   "AND tmsBD.ESQUEMA_PROPIO = 'S' "+
                   "AND tmsTer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID "+
                   "AND tmsGlo.PARAMETRO_CODIGO = 'P_VLRREPSER'";
        
        String Consulta2 = 
                "SELECT tmsTer.PARAMETRO_VALOR "+
                  "FROM TMS_TERMINAL_PARAMETROS_TBL tmsTer "+
                         ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo "+
                         ",TMS_BASE_DATOS_CONFIG_TBL  tmsBD "+
                 "WHERE tmsTer.TERMINAL_ID = tmsBD.TERMINAL_ID "+
                   "AND tmsBD.ESQUEMA_PROPIO = 'S' "+
                   "AND tmsTer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID "+
                   "AND tmsGlo.PARAMETRO_CODIGO = 'P_VLRREPUID'";
        
        String Consulta3 = 
                "SELECT tmsTer.PARAMETRO_VALOR "+
                  "FROM TMS_TERMINAL_PARAMETROS_TBL tmsTer "+
                         ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo "+
                         ",TMS_BASE_DATOS_CONFIG_TBL  tmsBD "+
                 "WHERE tmsTer.TERMINAL_ID = tmsBD.TERMINAL_ID "+
                   "AND tmsBD.ESQUEMA_PROPIO = 'S' "+
                   "AND tmsTer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID "+
                   "AND tmsGlo.PARAMETRO_CODIGO = 'P_VLRREPURL'";
        try{
            Vector x = new Vector();
            //x = (Vector) em.createNativeQuery(Consulta1).getSingleResult();
            //System.out.println("ES PARAMETROS: "+x);
            /*if(x.size()==0) return null;*/
            String[] param = new String[3];
            param[0] = "";
            
            x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta2).getSingleResult();
            //System.out.println("ES PARAMETROS: "+x);
            if(x.size()==0) return null;
            param[1] = x.get(0).toString();
            
            x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta3).getSingleResult();
            //System.out.println("ES PARAMETROS: "+x);
            if(x.size()==0) return null;
            param[2] = x.get(0).toString();
            return param;
        }catch(Exception ex){
            return null;
        }
    }
    
    public int finalizaLoteFolios(String empresa, String ubv, long usuarioId, String esquema){
        String Consulta = "UPDATE TMS_ASIGNACION_FOLIOS_TBL AFO "+
                        "SET AFO.FECHA_FINALIZO = SYSDATE, AFO.ULTIMA_ACTUALIZACION_POR = "+usuarioId+", AFO.ULTIMA_FECHA_ACTUALIZACION = SYSDATE, " +
                        "AFO.REPLICACION_ORIGEN = '"+esquema+"', AFO.REPLICACION_ESTADO = 'P' "+
                        "WHERE AFO.BDCONFIG_ID = ( "+
                                "SELECT BDC.BDCONFIG_ID FROM "+
                                "TMS_BASE_DATOS_CONFIG_TBL BDC "+
                                "WHERE BDC.ESQUEMA_PROPIO = 'S' "+
                        ") "+
                        "AND	  AFO.EMPRESA_ID = (SELECT EM.EMPRESA_ID FROM TMS_EMPRESAS_TBL EM WHERE EM.EMPRESA_NOMBRE = '"+empresa+"' ) "+
                        "AND	  AFO.FECHA_FINALIZO IS NULL "+
                        "AND   "+ubv+">=AFO.FOLIO_FINAL";
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 0;
            return 1;
        }catch(Exception ex){
            return -1;
        }
    }
    
    public int sigCorteSeq(){
        Vector x = null;
        String Consulta = "SELECT (COUNT(SACT.ACTIVIDAD_SESION_ID)+1) CORTE "+
                          "FROM TMS_SESION_ACTIVIDADES_TBL SACT "+
                          "WHERE SACT.ACTIVIDAD_SESION_ID = ( "+
                               "SELECT ACTS.ACTIVIDAD_SESION_ID FROM TMS_ACTIVIDADES_SESION_TBL ACTS "+
                               "WHERE ACTS.CLAVE_ACTIVIDAD = 'CORTE' "+
                          ")";
        try{
            x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Integer.valueOf(y.get(0).toString());
        }catch(Exception ex){
            return -1;
        }
    }
    
    public String[] obtenerTerminal(){
        String Consulta = 
                "SELECT BDC.TERMINAL_ID, BDC.NOMBRE_TERMINAL, BDC.NOMBRE_ESQUEMA FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
                "WHERE BDC.ESQUEMA_PROPIO = 'S'";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            String s[] = new String[3];
            s[0] = y.get(0).toString();
            s[1] = y.get(1).toString();
            s[2] = y.get(2).toString();
            return s;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String obtenerEmpresaPrincipal(long cajaId){
        String Consulta = 
                "SELECT GLOB.PARAMETRO_VALOR "+
                "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
                ",TMS_CAJA_PARAMETROS_TBL GLOB "+
                "WHERE PARAM.PARAMETRO_CODIGO = 'P_VLREMPPRI' "+
                "AND  GLOB.CAJA_ID = "+cajaId+" "+
                "AND  GLOB.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            return y.get(0).toString();
        }catch(Exception ex){
            return null;
        }
    }
    
    public long obtenerCaja(String computadora){
        String Consulta = 
                "SELECT CAJA.CAJA_ID "+
                "FROM TMS_CAJAS_TBL CAJA "+
                "WHERE CAJA.CAJA_NOMBRE = '"+computadora+"'";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Long.valueOf(y.get(0).toString());
        }catch(Exception ex){
            return -1;
        }
    }
    
    public List<TmsEmpresasTbl> queryTmsEmpresasTblAll(){
        return (List<TmsEmpresasTbl>)em.createNamedQuery("TmsEmpresasTbl.findAll").getResultList();
    }
    
    //SELECT CAJA_ID, EMPRESA_ID, VALOR_ACTIVIDAD FROM TMS_SESION_ACTIVIDADES_TBL WHERE CORTE_ID=
    public boolean queryFinalizaSesionVenta(String PrefijoTerminal, long corteId, long usuarioId){
        String Consulta = 
                "INSERT INTO TMS_SESION_ACTIVIDADES_TBL (SESION_ACTIVIDAD_ID, CORTE_ID, CAJA_ID, EMPRESA_ID, ACTIVIDAD_SESION_ID, "+
                "VALOR_ACTIVIDAD, FECHA_HORA_ACTIVIDAD, AUTORIZADO_POR, VALOR_SECUENCIAL, CREADO_POR, FECHA_CREACION, "+
                "ULTIMA_ACTUALIZACION_POR, ULTIMA_FECHA_ACTUALIZACION, REPLICACION_ESTADO, REPLICACION_INTENTOS, REPLICACION_ORIGEN) "+
                "VALUES "+
                "("+PrefijoTerminal+"||TMS_SESION_ACTIVIDAD_SEQ.NEXTVAL,"+corteId+", "+
                "(SELECT CAJA_ID FROM TMS_SESION_ACTIVIDADES_TBL WHERE CORTE_ID="+corteId+" AND ROWNUM=1), "+
                "(SELECT EMPRESA_ID FROM TMS_SESION_ACTIVIDADES_TBL WHERE CORTE_ID="+corteId+" AND ROWNUM=1), "+
                "(SELECT ACS.ACTIVIDAD_SESION_ID FROM TMS_ACTIVIDADES_SESION_TBL ACS WHERE ACS.CLAVE_ACTIVIDAD = 'FINSES'), "+
                "(SELECT USUARIO_NOMBRE FROM TMS_USUARIOS_TBL WHERE USUARIO_ID="+usuarioId+"), "+
                "SYSDATE, "+usuarioId+",NULL,"+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE,NULL,NULL," +
                "(SELECT REPLICACION_ORIGEN FROM TMS_SESION_ACTIVIDADES_TBL WHERE CORTE_ID="+corteId+" AND ROWNUM=1))";
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean esUsuarioSupervisorConFuncion(String funcionNumero, String usuarioNumero, String pwdEnc) throws javax.ejb.EJBException{
        String consultaUsuario = "SELECT DISTINCT(tmsus.USUARIO_ID) " +
                " FROM TMS_USUARIOS_TBL tmsus " +
                " ,TMS_USUARIO_PERFILES_TBL tmsup " +
                " ,TMS_PERFILES_TBL tmspe " +
                " ,TMS_MENUS_ENCABEZADO_TBL tmsme " +
                " ,TMS_MENUS_LINEAS_TBL    tmsml " +
                " ,TMS_FUNCIONES_TBL 		tmsfn " +
                " WHERE tmsus.USUARIO_ID = tmsup.USUARIO_ID " +
                " AND tmsup.PERFIL_ID  = tmspe.PERFIL_ID " +
                " AND tmspe.MENU_ID    = tmsme.MENU_ID " +
                " AND tmsme.MENU_ID    = tmsml.MENU_ID " +
                " AND tmsml.FUNCION_ID = tmsfn.FUNCION_ID " +
                " AND tmsfn.FUNCION_NUMERO = '"+funcionNumero+"' " +
                " AND tmsus.USUARIO_NUMERO = '"+usuarioNumero+"' "+
                " AND tmsus.CONTRASENA_ENCRIPTADA = '"+pwdEnc+"' " +
                " AND tmsus.UBICACION_ID = (select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S')";
        
        try{
            Object resultado = em.createNativeQuery(consultaUsuario).getSingleResult();
            if(resultado==null) return false;
            String valor = resultado.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return false;
            return true;
        }catch(NoResultException ex){
            return false;
        }
    }

     public String buscaCortesFinDia(Timestamp fi, Timestamp ff){ 
     String cadena ="";    
     //System.out.println("fi: "+fi.toString().substring(0,19));
     String qry = "select * from( " +
             " select c.CORTE_ID from tms_cortes_tbl c where c.FECHA_CREACION between to_date('"+fi.toString().substring(0,19)+"','RRRR-MM-DD HH24:MI:SS') AND to_date('"+ff.toString().substring(0,19)+"','RRRR-MM-DD HH24:MI:SS') AND C.ESTADO_CORTE = 'R' " +
             "UNION ALL  " +
             "select c.CORTE_ID from tms_cortes_tbl c where c.FECHA_CREACION between to_date('"+fi.toString().substring(0,19)+"','RRRR-MM-DD HH24:MI:SS')-1 AND to_date('"+ff.toString().substring(0,19)+"','RRRR-MM-DD HH24:MI:SS')-1 AND C.ESTADO_CORTE = 'D' " +
             ")  order by 1";
    //System.out.println("qry: "+qry);
       Vector vc =(Vector) em.createNativeQuery(qry).getResultList();//"select c.CORTE_ID from tms_cortes_tbl c where c.FECHA_CREACION between to_date('"+fi.toString().substring(0,19)+"','RRRR-MM-DD HH24:MI:SS') AND to_date('"+ff.toString().substring(0,19)+"','RRRR-MM-DD HH24:MI:SS')  AND C.ESTADO_CORTE IN ('R','D') order by c.CORTE_ID").getResultList();
       if(vc.size()==0)
           return "";
       else
       {
           cadena = vc.toString().replace('[',' ');
           cadena = cadena.replace(']',' ');
           cadena = cadena.trim();
       }
     return cadena;
     }

    public String _ObtieneFechaHoraBD(){
        return ((Vector)em.createNativeQuery("select to_char(SYSDATE,'RRRR-MM-DD HH24:MI:SS') from dual").getSingleResult()).get(0).toString();
    }

    public float getVentaNetaCorte(long corteId){
        //return Float.valueOf( ((Vector)em.createNativeQuery("SELECT NVL(SUM(Xer_Tms_Pkg.TMS_VENTA_NETA_CORTE_FNC(tmsCor.CORTE_ID)),0) monto FROM TMS_CORTES_TBL TMSCOR WHERE TMSCOR.CORTE_ID = "+corteId).getSingleResult()).get(0).toString());
        return Float.valueOf( ((Vector)em.createNativeQuery("SELECT TMS_CORTE_PKG.CF_POR_ENTREGAR_PAG_STR ('EFECTIVO','T', -1, NULL, NULL,-1, '"+corteId+"') MONTO from dual").getSingleResult()).get(0).toString());
    }

    public float getTAECorte(long corteId){
        return Float.valueOf( ((Vector)em.createNativeQuery("SELECT  NVL(SUM(NVL(TOTAL,0)),0) MONTO FROM XER_VENTAS_MISCELANEAS_TBL WHERE CORTE_ID = "+corteId).getSingleResult()).get(0).toString());
    }

    public boolean corteDummyFinDia(long usuarioId){
        Vector v =(Vector)em.createNativeQuery("SELECT CORTE_ID FROM TMS_CORTES_TBL WHERE CORTE_ID = 1").getResultList();
        if(v.size()>0)
            return true;
        else
        {
            try{
            em.createNativeQuery("INSERT INTO TMS_CORTES_TBL (CORTE_ID,NOMBRE_SESION,USUARIO_ID,ESTADO_CORTE,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) values (1,'FIN DE DIA',"+usuarioId+",'R',"+usuarioId+","+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE,'R',0,'XERTMS')").executeUpdate();
            return true;
            }
            catch(EJBException e){
                e.printStackTrace();
                return false;
            }
        }
    }

    public Object queryActividadSesionCorteFinDiaGetId(){
        String Consulta = "SELECT "+
                          "ACT.ACTIVIDAD_SESION_ID "+
                          "FROM "+
                          "TMS_ACTIVIDADES_SESION_TBL ACT "+
                          "WHERE "+
                          "ACT.CLAVE_ACTIVIDAD = 'CORTE_FIN_DIA'";
        try{
            return em.createNativeQuery(Consulta).getSingleResult();
        }catch(Exception ejbex){
            return null;
        }
    }
 
    public long addSesionCorteFinDia(String PrefijoTerminal, TmsSesionActividadesTbl tmsSesionActividadesTbl, String ids, String rt,String rm,String ra,String rf,String rs){
        try{
            Vector v = (Vector)em.createNativeQuery("select SESION_ACTIVIDAD_ID from TMS_SESION_ACTIVIDADES_TBL where VALOR_ACTIVIDAD = '"+tmsSesionActividadesTbl.getValorActividad()+"'").getResultList();
            if(v.size()==0)
            {
                em.persist(tmsSesionActividadesTbl);
                tmsSesionActividadesTbl.setSesionActividadId(Long.valueOf(PrefijoTerminal+tmsSesionActividadesTbl.getSesionActividadId()));
                /*String qry = "  SELECT 'TAQUILLA' TIPO "
                        + ",SUM(Xer_Tms_Pkg.TMS_VENTA_NETA_CORTE_FNC(tmsCor.CORTE_ID)) monto "
                        + "FROM TMS_CORTES_TBL TMSCOR  "
                        + "WHERE TMSCOR.CORTE_ID IN ("+ids+") GROUP BY 'TAQUILLA' "
                        + "UNION ALL    "
                        + "SELECT 'MORRALLA' tipo "
                        + ",NVL(SUM(TO_NUMBER(tmsSes.VALOR_ACTIVIDAD,'99999999.999')),0) MONTO "
                        + "FROM TMS_CORTES_TBL               tmsCor "
                        + ",TMS_SESION_ACTIVIDADES_TBL tmsSes "
                        + ",TMS_ACTIVIDADES_SESION_TBL tmsAct "
                        + "WHERE tmsCor.CORTE_ID = tmsSes.CORTE_ID "
                        + "AND tmsSes.ACTIVIDAD_SESION_ID = tmsAct.ACTIVIDAD_SESION_ID "
                        + "AND TMSACT.CLAVE_ACTIVIDAD       = 'FONDINI' "
                        + "AND TMSCOR.CORTE_ID IN ("+ids+") "
                        + "UNION ALL "
                        + "SELECT 'TIEMPO AIRE' TIPO, NVL(SUM(NVL(TOTAL,0)),0) MONTO FROM XER_VENTAS_MISCELANEAS_TBL WHERE CORTE_ID IN  ("+ids+") "
                        + "UNION ALL "
                        + "SELECT 'FALTANTES' TIPO, NVL(SUM(TO_NUMBER(TMSRECOL.MONTO ,'99999999.999')),0) MONTO   "
                        + "FROM TMS_CORTES_TBL TMSCOR LEFT JOIN TMS_SESION_ACTIVIDADES_TBL TMSSES ON  TMSSES.CORTE_ID = TMSCOR.CORTE_ID "
                        + "LEFT JOIN TMS_ACTIVIDADES_SESION_TBL TMSACT ON  TMSACT.ACTIVIDAD_SESION_ID = TMSSES.ACTIVIDAD_SESION_ID "
                        + "LEFT JOIN TMS_RECOLECCIONES_TBL TMSRECOL ON TMSRECOL.SESION_ACTIVIDAD_ID = TMSSES.SESION_ACTIVIDAD_ID "
                        + "WHERE TMSACT.CLAVE_ACTIVIDAD   = 'CORTE' "
                        + "AND  TMSRECOL.REFERENCIA  = 'FALTANTE' "
                        + "AND TMSCOR.CORTE_ID IN ("+ids+")"
                        + "UNION ALL "
                        + "SELECT 'SOBRANTES' TIPO, NVL(SUM(TO_NUMBER(TMSRECOL.MONTO ,'99999999.999')),0) MONTO   "
                        + "FROM TMS_CORTES_TBL TMSCOR LEFT JOIN TMS_SESION_ACTIVIDADES_TBL TMSSES ON  TMSSES.CORTE_ID = TMSCOR.CORTE_ID "
                        + "LEFT JOIN TMS_ACTIVIDADES_SESION_TBL TMSACT ON  TMSACT.ACTIVIDAD_SESION_ID = TMSSES.ACTIVIDAD_SESION_ID "
                        + "LEFT JOIN TMS_RECOLECCIONES_TBL TMSRECOL ON TMSRECOL.SESION_ACTIVIDAD_ID = TMSSES.SESION_ACTIVIDAD_ID "
                        + "WHERE TMSACT.CLAVE_ACTIVIDAD   = 'CORTE' "
                        + "AND  TMSRECOL.REFERENCIA  = 'SOBRANTE' "
                        + "AND TMSCOR.CORTE_ID IN ("+ids+")";*/
                     String qry = "SELECT 'TAQUILLA' TIPO  "
                             + ",TMS_CORTE_PKG.CF_POR_ENTREGAR_PAG_STR ('EFECTIVO','T', -1, NULL, NULL,-1, '"+ids+"') MONTO  "
                             + "from dual "
                             + "UNION ALL     "
                             + "SELECT 'MORRALLA' tipo  "
                             + ",NVL(SUM(TO_NUMBER(tmsSes.VALOR_ACTIVIDAD,'99999999.999')),0) MONTO   "
                             + "FROM TMS_CORTES_TBL               tmsCor  "
                             + ",TMS_SESION_ACTIVIDADES_TBL tmsSes  "
                             + ",TMS_ACTIVIDADES_SESION_TBL tmsAct  "
                             + "WHERE tmsCor.CORTE_ID = tmsSes.CORTE_ID  "
                             + "AND tmsSes.ACTIVIDAD_SESION_ID = tmsAct.ACTIVIDAD_SESION_ID  "
                             + "AND TMSACT.CLAVE_ACTIVIDAD       = 'FONDINI' "
                             + "AND TMSCOR.CORTE_ID IN ("+ids+")   ";
                     Vector res = (Vector)em.createNativeQuery(qry).getResultList();
                     for(int i=0; i<res.size(); i++)
                     {
                         Vector row = (Vector)res.get(i);
                         if(row.get(0).toString().equals("TAQUILLA"))
                         {
                             qry = "INSERT INTO TMS_RECOLECCIONES_TBL (RECOLECCION_ID,SESION_ACTIVIDAD_ID,TIPO_PAGO_ID,TIPO_PASAJERO_ID,REFERENCIA,CANTIDAD,MONTO,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) "
                                     + "values ("+PrefijoTerminal+"||TMS_RECOLECCIONES_SEQ.nextval,"+tmsSesionActividadesTbl.getSesionActividadId()+",null,null,'TAQUILLA_"+rt+"',1,"+row.get(1).toString()+","+tmsSesionActividadesTbl.getCreadoPor()+","+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,"+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,'R',0,'XERTMS')";
                             em.createNativeQuery(qry).executeUpdate();
                         }
                         if(row.get(0).toString().equals("MORRALLA"))
                         {
                             qry = "INSERT INTO TMS_RECOLECCIONES_TBL (RECOLECCION_ID,SESION_ACTIVIDAD_ID,TIPO_PAGO_ID,TIPO_PASAJERO_ID,REFERENCIA,CANTIDAD,MONTO,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) "
                                     + "values ("+PrefijoTerminal+"||TMS_RECOLECCIONES_SEQ.nextval,"+tmsSesionActividadesTbl.getSesionActividadId()+",null,null,'MORRALLA_"+rm+"',1,"+row.get(1).toString()+","+tmsSesionActividadesTbl.getCreadoPor()+","+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,"+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,'R',0,'XERTMS')";
                             em.createNativeQuery(qry).executeUpdate();
                         }
                         if(row.get(0).toString().equals("TIEMPO AIRE"))
                         {
                             qry = "INSERT INTO TMS_RECOLECCIONES_TBL (RECOLECCION_ID,SESION_ACTIVIDAD_ID,TIPO_PAGO_ID,TIPO_PASAJERO_ID,REFERENCIA,CANTIDAD,MONTO,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) "
                                     + "values ("+PrefijoTerminal+"||TMS_RECOLECCIONES_SEQ.nextval,"+tmsSesionActividadesTbl.getSesionActividadId()+",null,null,'TIEMPO AIRE_"+ra+"',1,"+row.get(1).toString()+","+tmsSesionActividadesTbl.getCreadoPor()+","+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,"+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,'R',0,'XERTMS')";
                             em.createNativeQuery(qry).executeUpdate();
                         }
                         if(row.get(0).toString().equals("FALTANTES"))
                         {
                             qry = "INSERT INTO TMS_RECOLECCIONES_TBL (RECOLECCION_ID,SESION_ACTIVIDAD_ID,TIPO_PAGO_ID,TIPO_PASAJERO_ID,REFERENCIA,CANTIDAD,MONTO,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) "
                                     + "values ("+PrefijoTerminal+"||TMS_RECOLECCIONES_SEQ.nextval,"+tmsSesionActividadesTbl.getSesionActividadId()+",null,null,'FALTANTES_"+rf+"',1,"+row.get(1).toString()+","+tmsSesionActividadesTbl.getCreadoPor()+","+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,"+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,'R',0,'XERTMS')";
                             em.createNativeQuery(qry).executeUpdate();
                         }
                         if(row.get(0).toString().equals("SOBRANTES"))
                         {
                             qry = "INSERT INTO TMS_RECOLECCIONES_TBL (RECOLECCION_ID,SESION_ACTIVIDAD_ID,TIPO_PAGO_ID,TIPO_PASAJERO_ID,REFERENCIA,CANTIDAD,MONTO,AUTORIZADO_POR,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ESTADO,REPLICACION_INTENTOS,REPLICACION_ORIGEN) "
                                     + "values ("+PrefijoTerminal+"||TMS_RECOLECCIONES_SEQ.nextval,"+tmsSesionActividadesTbl.getSesionActividadId()+",null,null,'SOBRANTES_"+rs+"',1,"+row.get(1).toString()+","+tmsSesionActividadesTbl.getCreadoPor()+","+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,"+tmsSesionActividadesTbl.getCreadoPor()+",SYSDATE,'R',0,'XERTMS')";
                             em.createNativeQuery(qry).executeUpdate();
                         }                     }
                return tmsSesionActividadesTbl.getSesionActividadId().longValue();
            }
            else
                return Long.valueOf(v.get(0).toString());

         }catch(Exception ejbex){
            //ejbex.printStackTrace();
           return -1;
        }
    }
    public String tmsAlgoritmoBanco(String cadena,String algoritmoVigente){
        //CODIGO A REMPLAZAR AMS nuevo algoritmo
        String sql="";
            try {
                Connection cnx = dataSource.getConnection();
                sql="BEGIN " +
                "? := TMS_BANCO_PKG.ALGOTIMO_VERIFICADOR_FNC(?,?); " +
                "END;";
                OracleCallableStatement stmt = (OracleCallableStatement) cnx.prepareCall(sql);
                stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
                ((OraclePreparedStatement)stmt).setString(2, cadena);
                ((OraclePreparedStatement)stmt).setString(3, algoritmoVigente);
                boolean bResultado=stmt.execute();
                System.out.println("Cadena resultante "+stmt.getString(1));
                //touCotizacionesLTbl.setCotizacionReferencia(stmt.getString(1));
                return stmt.getString(1);
            } catch (Exception ex) {
                System.out.println("Algun error ocurrio" + ex );
                return null;
            }
        
        //END AMS                
    }

    public String algoritmoBancoVigente(){
        String sql="SELECT GP.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL PC " +
                "INNER JOIN TMS_GLOBAL_PARAMETROS_TBL GP ON " +
                "PC.PARAMETRO_CONFIG_ID = GP.PARAMETRO_CONFIG_ID WHERE " +
                "PC.PARAMETRO_CODIGO='P_ALGORITMO_BANCO'";
        Vector parametroConfig = (Vector) em.createNativeQuery(sql).getSingleResult();
        System.out.println("Algoritmo vigente Bancos: " +parametroConfig.get(0).toString());
        return parametroConfig.get(0).toString();

    }
}