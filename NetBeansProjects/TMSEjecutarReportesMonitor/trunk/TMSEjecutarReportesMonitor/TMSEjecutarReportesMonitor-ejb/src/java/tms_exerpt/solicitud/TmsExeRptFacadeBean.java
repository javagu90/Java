/*
 * TmsExeRptFacadeBean.java
 *
 * Created on 16 de abril de 2007, 12:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_exerpt.solicitud;

import java.sql.Timestamp;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Este Metodo contiene todas las declaraciones de las funciones utilizadas en las
 * anotaciones de los entity beans. las cuales seran utilizadas al instanciar este campo
 * @author imorales
 * @version 1.01 27-Marzo-2007
 */
@Stateless
public class TmsExeRptFacadeBean implements TmsExeRptFacadeRemote {
    @PersistenceContext(unitName="TMSEjecutarReportesMonitor-ejbPU")
    private EntityManager em;

    /*
    * Clase del Entity Bean, que se usa en la aplicacion
    */
    public TmsExeRptFacadeBean(){ }

    /****************************************/
    public String[] obtenerTerminal(){
        String Consulta = 
                "SELECT BDC.TERMINAL_ID, BDC.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
                "WHERE BDC.ESQUEMA_PROPIO = 'S'";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            String s[] = new String[2];
            s[0] = y.get(0).toString();
            s[1] = y.get(1).toString();
            return s;
        }catch(Exception ex){
            return null;
        }
    }   
    
    public Vector Reportes(long usuarioId){
        String Consulta = 
                "SELECT GR.GRUPO_NOMBRE, "+
                "(SELECT COUNT(*) "+
                 "FROM TMS_GRUPOS_REPORTES_LINEAS_TBL GZ "+
                 "WHERE GZ.GRUPO_REPORTE_ID =GR.GRUPO_REPORTE_ID "+
                ") CTD, "+
                "R.REPORTE_NOMBRE, R.DESCRIPCION, R.REPORTE_EXECUTABLE "+
                "FROM "+
                "TMS_GRUPOS_REPORTES_TBL GR "+
                ",TMS_GRUPOS_REPORTES_LINEAS_TBL GRL "+
                ",TMS_REPORTES_TBL R "+
                "WHERE "+
                "GR.GRUPO_REPORTE_ID IN ( "+
                        "SELECT P.SOLICITUDES_ID FROM "+
                        "TMS_USUARIO_PERFILES_TBL UP "+
                        ",TMS_PERFILES_TBL P "+
                        "WHERE UP.USUARIO_ID = "+usuarioId+" "+
                        "AND  P.PERFIL_ID = UP.PERFIL_ID "+
                        "AND  TRUNC(NVL(P.FECHA_FINAL, SYSDATE)) <= TRUNC(SYSDATE) "+
                ") "+
                "AND  GRL.GRUPO_REPORTE_ID = GR.GRUPO_REPORTE_ID "+
                "AND  GRL.REPORTE_ID = R.REPORTE_ID";
        System.out.println("Consulta:"+Consulta);
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            System.out.println("x.size():"+x.size());
            if(x==null || x.size()==0) return null;
            return x;
        }catch(Exception ex){
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
            System.out.println(Consulta1);
            Vector x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta1).getSingleResult();
            if(x.size()==0) return null;
            String[] param = new String[3];
            param[0] = x.get(0).toString();
            
            System.out.println(Consulta2);
            x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta2).getSingleResult();
            if(x.size()==0) return null;
            param[1] = x.get(0).toString();
            
            System.out.println(Consulta3);
            x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta3).getSingleResult();
            if(x.size()==0) return null;
            param[2] = x.get(0).toString();
            System.out.println(param[2]);
            return param;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
}