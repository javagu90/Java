/*
 * TmsCorteTerminalFacade.java
 *
 * Created on 15 de agosto de 2007, 11:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.solicitud;   

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import xertms.entidad.TmsBDConfigTbl;
import xertms.entidad.TmsCortesTbl;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsCorteTerminalFacade implements xertms.solicitud.TmsCorteTerminalFacadeRemote {
    @PersistenceContext(unitName="TMSCortesTerminal-ejbPU")
    EntityManager em;
    /**
     * Creates a new instance of TmsCorteTerminalFacade
     */
    public TmsCorteTerminalFacade() {
    }
    
    public List<TmsCortesTbl> queryTmsCortesTblByFecha(Timestamp fecha){
        return em.createNamedQuery("TmsCortesTbl.findByFechaCreacion").setParameter("FechaCreacion",fecha).getResultList();
    }

    public List<Object[]> queryTmsCortesTblForPrint(String fecha, String pendientes, String prefijo){
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
                "AND     TO_CHAR(COR.CORTE_ID) LIKE '"+prefijo+"%' "+
//                "AND	 COR.NOMBRE_SESION NOT LIKE 'R_%' "+
//                "AND	 COR.ESTADO_CORTE = NVL('"+pendientes+"',COR.ESTADO_CORTE) "+
                "AND	 COR.NOMBRE_SESION NOT LIKE 'R_%' ";
                if(pendientes.equals("P"))
                   Consulta = Consulta+ "AND	COR.ESTADO_CORTE IN ('P','T')";// = NVL('"+pendientes+"',COR.ESTADO_CORTE) "+
                 Consulta = Consulta+"AND	COR.USUARIO_ID = USU.USUARIO_ID";

        try{
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
            System.out.println(Consulta);
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
    
    public List<TmsBDConfigTbl> queryTmsBaseDatosConfigTblAll(){
        try{
            return em.createNamedQuery("TmsBDConfigTbl.findAll").getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
    public Vector obtenerTerminalPrefijo(){
        String Consulta = 
                "SELECT TERMINAL_ID, RPAD(TERMINAL_ID,3,'0'), NOMBRE_TERMINAL "+
                "FROM TMS_BASE_DATOS_CONFIG_TBL "+
                "ORDER BY 1";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            return x;
        }catch(Exception ex){
            return null;
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
}