/*
 * TmsIncidenciaFacadeBean.java
 *
 * Created on 16 de abril de 2007, 12:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_incidencias.solicitud;

import java.sql.Timestamp;
import java.util.TimeZone;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tms_incidencias.entidad.TmsAccionesTbl;
import tms_incidencias.entidad.TmsEmpresasTbl;
import tms_incidencias.entidad.TmsEstadoOperadoresV;
import tms_incidencias.entidad.TmsActAdicionalesTbl;
import tms_incidencias.entidad.TmsOperadorIncidenciasTbl;
import tms_incidencias.entidad.TmsOperadoresTbl;
import tms_incidencias.entidad.TmsPagosActAdicionalesTbl;
import tms_incidencias.entidad.TmsServiciosTbl;

/**
 * Este Metodo contiene todas las declaraciones de las funciones utilizadas en las
 * anotaciones de los entity beans. las cuales seran utilizadas al instanciar este campo
 * @author imorales
 * @version 1.01 27-Marzo-2007
 */
@Stateless
public class TmsIncidenciaFacadeBean implements TmsIncidenciaFacadeRemote {
    @PersistenceContext(unitName="TMS_Incidencias-ejbPU")
    private EntityManager em;

    /*
    * Clase del Entity Bean, que se usa en la aplicacion
    */
    public TmsIncidenciaFacadeBean(){ }

    /****************************************/
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
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<TmsActAdicionalesTbl> queryTmsIncidenciasAll(){
        List<TmsActAdicionalesTbl> x = em.createNamedQuery("TmsActAdicionalesTbl.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    //      
    public Object[][] queryParamIncidencias(){
        String Consulta =
                "S  ELECT o.TIPO_ACTIVIDAD_ADICIONAL_ID, SP.SERVICIO_ID, SP.PARAMETRO_VALOR "+
                "FROM "+
                "TMS_ACT_ADICIONALES_TBL o "+
                ",TMS_PARAMETROS_CONFIG_TBL P "+
                ",TMS_SERVICIO_PARAMETROS_TBL SP "+
                "WHERE o.actividad_Clave <> 'GUA' "+
                "AND   o.habilitado = 'S' "+
                "AND   P.PARAMETRO_CODIGO = o.CODIGO_PARAMETRO "+
                "AND   P.PARAMETRO_CONFIG_ID = SP.PARAMETRO_CONFIG_ID "+
                "ORDER BY o.tipo_Actividad_Adicional_Id, SP.SERVICIO_ID";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Object[][] s = new String[x.size()][3];
            Vector y;
            
            for(int i=0; i<s.length; i++){
                y = (Vector) x.get(i);
                s[i][0] = y.get(0).toString();
                s[i][1] = y.get(1).toString();
                s[i][2] = y.get(2).toString();
            }
            
            return s;
        }catch(Exception ex){
            return null;
        }
    }
    
    public List<TmsAccionesTbl> queryTmsAccionesTblAll(){
       List<TmsAccionesTbl> x = em.createNamedQuery("TmsAccionesTbl.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public List<TmsEstadoOperadoresV> queryTmsOperadoresVAll(){
        List<TmsEstadoOperadoresV> x = em.createNamedQuery("TmsEstadoOperadoresV.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public List<TmsServiciosTbl> queryTmsServiciosAll(){
        List<TmsServiciosTbl> x = em.createNamedQuery("TmsServiciosTbl.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public List<TmsEmpresasTbl> queryTmsEmpresasAll(){
        List<TmsEmpresasTbl> x = em.createNamedQuery("TmsEmpresasTbl.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    } // 
    
    public Vector OperadorIncidencias(String claveOperador, String Servicio, String claveIncidencia, String fecha1, String fecha2, String estado){
        Vector vVector = new Vector();
        String Consulta =
                "SELECT "+
                "OI.OPER_INCIDENCIA_ID, OI.OPERADOR_ID, O.CLAVE_OPERADOR, O.OPERADOR_NOMBRE_COMPLETO, "+
                "OI.INCIDENCIA_ID, AA.ACTIVIDAD_CLAVE, I.ACCION, AA.APLICA_RECAUDACION, AA.CONFIGURABLE, "+
                "TO_CHAR(OI.FECHA_INICIAL,'dd/MM/yyyy HH24:MI'), TO_CHAR(OI.FECHA_FINAL,'dd/MM/yyyy HH24:MI'), OI.INCIDENCIA_VALOR, "+
                "DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) DURACION, "+
                "TRUNC( "+
                "(DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) * OI.INCIDENCIA_VALOR) "+
                "* (NVL((SELECT EP.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL P, TMS_EMPRESA_PARAMETROS_TBL EP WHERE "+
                  "P.PARAMETRO_CODIGO='P_R_PORRET' "+ 
                  "AND O.APLICA_RETENCION = 'S' "+
                  "AND EP.EMPRESA_ID = O.EMPRESA_ID "+
                  "AND P.PARAMETRO_CONFIG_ID=EP.PARAMETRO_CONFIG_ID),0)/100) "+
                ") RETENCION, "+
                "(DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) * OI.INCIDENCIA_VALOR) MONTO, "+
                "S.SERVICIO_ID, "+
                "S.SERVICIO_NOMBRE, "+
                "OI.INCIDENCIA_AUTORIZADA, " +
                "OI.ADICIONAL1 || OI.ADICIONAL2 OBSERVACION "+
                "FROM TMS_OPERADOR_INCIDENCIAS_TBL OI "+
                ",TMS_OPERADORES_TBL O "+
                ",TMS_ACCIONES_TBL I "+
                ",TMS_ACT_ADICIONALES_TBL AA  "+
                ",TMS_SERVICIOS_TBL S "+
                "WHERE OI.OPERADOR_ID = O.OPERADOR_ID "+
                "AND   OI.INCIDENCIA_ID = AA.TIPO_ACTIVIDAD_ADICIONAL_ID "+
                "AND   I.ACCION_ID = AA.ACCION_ID "+
                "AND   OI.SERVICIO_ID = S.SERVICIO_ID "+
                "AND   O.CLAVE_OPERADOR LIKE NVL('"+claveOperador+"', O.CLAVE_OPERADOR) "+
                "AND   S.SERVICIO_NOMBRE = NVL('"+Servicio+"', S.SERVICIO_NOMBRE) "+
                "AND   AA.ACTIVIDAD_CLAVE = NVL('"+claveIncidencia+"', AA.ACTIVIDAD_CLAVE) "+
                "AND   OI.INCIDENCIA_AUTORIZADA = NVL('"+estado+"', OI.INCIDENCIA_AUTORIZADA) "+
                "AND ( ( (TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), OI.FECHA_INICIAL))<=TRUNC(OI.FECHA_INICIAL) "+
                "AND  TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE)))>=TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)))  )"+
                "  OR   TRUNC(OI.FECHA_FINAL) BETWEEN TO_DATE('"+fecha1+"','DD/MM/YYYY') AND TRUNC(LAST_DAY(TO_DATE('"+fecha2+"','DD/MM/YYYY'))) )"+
                "AND	  OI.INCIDENCIA_AUTORIZADA = NVL('',OI.INCIDENCIA_AUTORIZADA) "+
                "ORDER BY O.CLAVE_OPERADOR, OI.FECHA_INICIAL";
        try{ 
            System.out.println("Buscar::: "+Consulta);
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            return vVector;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }   
    }// 
      // 
    public boolean tieneIncidencias(String claveOperador, String fecha1, String fecha2){
        Vector vVector = new Vector();
        String Consulta="SELECT COUNT(*) "+
                        "FROM TMS_OPERADOR_INCIDENCIAS_TBL op,TMS_OPERADORES_TBL o "+
                        "WHERE "+
                        "op.OPERADOR_ID = o.OPERADOR_ID AND "+
                        "("+
                        "TO_DATE(TO_CHAR(op.FECHA_INICIAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss')<=TO_DATE('"+fecha1+"','DD/MM/YYYY hh24:mi:ss') AND TO_DATE('"+fecha1+"','DD/MM/YYYY hh24:mi:ss')<=TO_DATE(TO_CHAR(op.FECHA_FINAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss') OR "+
                        "TO_DATE(TO_CHAR(op.FECHA_INICIAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss')<=TO_DATE('"+fecha2+"','DD/MM/YYYY hh24:mi:ss') AND TO_DATE('"+fecha2+"','DD/MM/YYYY hh24:mi:ss')<=TO_DATE(TO_CHAR(op.FECHA_FINAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss') OR "+
                        "TO_DATE('"+fecha1+"','DD/MM/YYYY hh24:mi:ss') <TO_DATE(TO_CHAR(op.FECHA_INICIAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss') AND TO_DATE(TO_CHAR(op.FECHA_FINAL,'DD/MM/YYYY hh24:mi:ss'),'DD/MM/YYYY hh24:mi:ss')<TO_DATE('"+fecha2+"','DD/MM/YYYY hh24:mi:ss')) AND "+
                        "o.CLAVE_OPERADOR LIKE NVL('"+claveOperador+"', o.CLAVE_OPERADOR) AND "+
                        "op.INCIDENCIA_AUTORIZADA='N'";
       /* String Consulta =
                "SELECT "+
                "COUNT(*) "+
                "FROM TMS_OPERADOR_INCIDENCIAS_TBL OI "+
                ",TMS_OPERADORES_TBL O "+
                "WHERE OI.OPERADOR_ID = O.OPERADOR_ID "+
                "AND   O.CLAVE_OPERADOR LIKE NVL('"+claveOperador+"', O.CLAVE_OPERADOR) "+
                "AND   ( "+
                          "(TRUNC(OI.FECHA_INICIAL)<=TRUNC(TO_DATE('"+fecha1+"','DD/MM/YYYY')) AND TRUNC(TO_DATE('"+fecha1+"','DD/MM/YYYY'))<=TRUNC(OI.FECHA_FINAL)) "+
                          "OR "+
                          "(TRUNC(OI.FECHA_INICIAL)<=TRUNC(TO_DATE('"+fecha2+"','DD/MM/YYYY')) AND TRUNC(TO_DATE('"+fecha2+"','DD/MM/YYYY'))<=TRUNC(OI.FECHA_FINAL)) "+
                          "OR "+
                          "(TRUNC(TO_DATE('"+fecha1+"','DD/MM/YYYY'))<TRUNC(OI.FECHA_INICIAL)) AND TRUNC(OI.FECHA_FINAL)<TRUNC(TO_DATE('"+fecha2+"','DD/MM/YYYY')) "+
                      ") "+
                "AND   OI.INCIDENCIA_AUTORIZADA = 'N'";*/
      try{
            //System.out.println("Buscar::: "+Consulta);
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return true;
            Vector z = (Vector) vVector.get(0);
            int x = Integer.valueOf(z.get(0).toString());
            if(x==0) return false;
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return true;
        }
    }  // 
    
    public Vector OperadorIncidencia(long operIncId){
        Vector vVector = new Vector();
        String Consulta = 
            "SELECT "+
            "OI.OPER_INCIDENCIA_ID, OI.OPERADOR_ID, O.CLAVE_OPERADOR, O.OPERADOR_NOMBRE_COMPLETO, "+
            "OI.INCIDENCIA_ID, AA.ACTIVIDAD_CLAVE, I.ACCION, AA.APLICA_RECAUDACION, AA.CONFIGURABLE, "+
            "OI.FECHA_INICIAL, OI.FECHA_FINAL, OI.INCIDENCIA_VALOR, "+
            "DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) DURACION, "+
            "TRUNC( "+
            "(DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) * OI.INCIDENCIA_VALOR) "+
            "* (NVL((SELECT EP.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL P, TMS_EMPRESA_PARAMETROS_TBL EP WHERE "+
            "  P.PARAMETRO_CODIGO='P_R_PORRET' "+
            "  AND O.APLICA_RETENCION = 'S' "+
            "  AND EP.EMPRESA_ID = O.EMPRESA_ID "+
            "  AND P.PARAMETRO_CONFIG_ID=EP.PARAMETRO_CONFIG_ID),0)/100) "+
            ") RETENCION, "+
            "(DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) * OI.INCIDENCIA_VALOR) MONTO, "+
            "S.SERVICIO_ID, "+
            "S.SERVICIO_NOMBRE, "+
            "OI.INCIDENCIA_AUTORIZADA "+
            "FROM TMS_OPERADOR_INCIDENCIAS_TBL OI "+
            ",TMS_OPERADORES_TBL O "+
            ",TMS_ACCIONES_TBL I "+
	    ",TMS_ACT_ADICIONALES_TBL AA "+
            ",TMS_SERVICIOS_TBL S "+
            "WHERE I.ACCION <> 'GUARDIA' "+
            "AND   OI.OPERADOR_ID = O.OPERADOR_ID "+
            "AND   OI.INCIDENCIA_ID = AA.TIPO_ACTIVIDAD_ADICIONAL_ID "+
            "AND   I.ACCION_ID = AA.ACCION_ID "+
            "AND   OI.SERVICIO_ID = S.SERVICIO_ID "+
            "AND   OI.OPER_INCIDENCIA_ID = "+operIncId;
        try{
            System.out.println("Buscar::: "+Consulta);
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            Vector y = (Vector) vVector.get(0);
            return y;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    /*public List<TmsEstadoOperadoresV> queryTmsEstadoOperadoresVByNumero(String numero){
        List<TmsEstadoOperadoresV> x = em.createNamedQuery("TmsEstadoOperadoresV.findByNumero").setParameter("Numero", numero).getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }*/
    
    public long registraIncidenciaOperador(String prefijo, TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl){
        em.persist(tmsIncidenciaOperadorTbl);
        tmsIncidenciaOperadorTbl.setOperIncidenciaId(Long.valueOf(prefijo+tmsIncidenciaOperadorTbl.getOperIncidenciaId()));
        return tmsIncidenciaOperadorTbl.getOperIncidenciaId();
    }
    
    public boolean autorizaIncidenciaOperador(long IncOperId, long usuarioId, Timestamp Fecha){
        TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl = em.find(TmsOperadorIncidenciasTbl.class, IncOperId);
        if(tmsIncidenciaOperadorTbl==null) return false;
        em.refresh(tmsIncidenciaOperadorTbl);
        tmsIncidenciaOperadorTbl.setIncidenciaAutorizada("S");
        tmsIncidenciaOperadorTbl.setUltimaActualizacionPor(usuarioId);
        tmsIncidenciaOperadorTbl.setUltimaFechaActualizacion(Fecha);
        return true;
    }
    
    public boolean cancelaIncidenciaOperador(long IncOperId, long usuarioId, Timestamp Fecha){
        TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl = em.find(TmsOperadorIncidenciasTbl.class, IncOperId);
        if(tmsIncidenciaOperadorTbl==null) return false;
        em.refresh(tmsIncidenciaOperadorTbl);
        tmsIncidenciaOperadorTbl.setIncidenciaAutorizada("C");
        tmsIncidenciaOperadorTbl.setUltimaActualizacionPor(usuarioId);
        tmsIncidenciaOperadorTbl.setUltimaFechaActualizacion(Fecha);
        return true;
    }
    
    public boolean modificarIncidenciaOperador(long IncOperId, String valor, Timestamp fechaFinal1, Timestamp fechaFinal2, String Observaciones1, String Observaciones2, long usuarioId, Timestamp Fecha){
        TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl = em.find(TmsOperadorIncidenciasTbl.class, IncOperId);
        if(tmsIncidenciaOperadorTbl==null) return false;
        em.refresh(tmsIncidenciaOperadorTbl);
        tmsIncidenciaOperadorTbl.setIncidenciaValor(valor);
        tmsIncidenciaOperadorTbl.setFechaInicial(fechaFinal1);
        tmsIncidenciaOperadorTbl.setFechaFinal(fechaFinal2);
        tmsIncidenciaOperadorTbl.setAdicional1(Observaciones1);
       // tmsIncidenciaOperadorTbl.setAdicional2(Observaciones2);
        tmsIncidenciaOperadorTbl.setUltimaActualizacionPor(usuarioId);
        tmsIncidenciaOperadorTbl.setUltimaFechaActualizacion(Fecha);
        return true;
    }
    
    public String[][] obtieneDatosAdicionales(String nombreIncidencia){
        Vector vVector = new Vector();
        String Consulta = 
            "SELECT DATO.DATO_NOMBRE, DADIC.GUARDAR_VALOR_EN, ADIC.TIPO_ACTIVIDAD_ADICIONAL_ID "+
            "FROM TMS_ACT_ADICIONALES_TBL ADIC "+
            ",TMS_ACT_DATOS_ADICIONALES_TBL DADIC "+
            ",TMS_DATOS_ADICIONALES_TBL DATO "+
            "WHERE ADIC.ACTIVIDAD_CLAVE = '"+nombreIncidencia+"' "+
            "AND   ADIC.TIPO_ACTIVIDAD_ADICIONAL_ID = DADIC.TIPO_ACTIVIDAD_ADICIONAL_ID "+
            "AND   DADIC.DATO_ADICIONAL_ID = DATO.DATO_ADICIONAL_ID";
        try{
            System.out.println("obtieneDatosAdicionales "+ Consulta);
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            Vector y;
            String[][] x = new String[vVector.size()][3];
            for(int i=0; i<vVector.size(); i++){
                y=(Vector) vVector.get(i);
                x[i][0] = y.get(0).toString();
                x[i][1] = y.get(1).toString();
                x[i][2] = y.get(2).toString();  
            }
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public void registraPagoAdicional(String prefijo, TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl){
        em.persist(tmsPagosActAdicionalesTbl);
        tmsPagosActAdicionalesTbl.setReferenciaPagoActAdicional(tmsPagosActAdicionalesTbl.getPagoActividadAdicionalId());
        tmsPagosActAdicionalesTbl.setPagoActividadAdicionalId(Long.valueOf(prefijo+tmsPagosActAdicionalesTbl.getPagoActividadAdicionalId()));
    }
    
    public boolean cambiaEstadoOperador(long OperId, long EstadoId, long UbicacionId, long ActividadId, long usuarioId, Timestamp Fecha, String esquema){
        TmsOperadoresTbl tmsIncidenciaOperadorTbl = em.find(TmsOperadoresTbl.class, OperId);
        if(tmsIncidenciaOperadorTbl==null) return false;
        em.refresh(tmsIncidenciaOperadorTbl);
        tmsIncidenciaOperadorTbl.setAccion1Id(EstadoId);
        tmsIncidenciaOperadorTbl.setAccion2Id(UbicacionId);
        tmsIncidenciaOperadorTbl.setAccion3Id(ActividadId);
        tmsIncidenciaOperadorTbl.setReplicacionOrigen(esquema);
        tmsIncidenciaOperadorTbl.setUltimaActualizacionPor(usuarioId);
        tmsIncidenciaOperadorTbl.setUltimaFechaActualizacion(Fecha);
        return true;
    }
    
    public long obtieneIdDatosAdicionales(String nombreIncidencia){
        Vector vVector = new Vector();
        String Consulta = 
            "SELECT ADIC.TIPO_ACTIVIDAD_ADICIONAL_ID "+
            "FROM TMS_ACT_ADICIONALES_TBL ADIC "+
            "WHERE ADIC.ACTIVIDAD_CLAVE = '"+nombreIncidencia+"'";
        try{
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return -1;
            Vector y;
            y=(Vector) vVector.get(0);
            if(y==null || y.size()==0) return -1;
            return Long.valueOf(y.get(0).toString());
        }catch(Exception ex){
            return -1;
        }
    }
    
    public String OperadorTVPR(String operador, String ligaCentral){
        String Consulta = 
                "SELECT COUNT(*) "+
                "FROM TMS_TARJETAS_VIAJE_TBL@"+ligaCentral+" TV "+
                "WHERE TV.OPERADOR = '"+operador+"' "+
                "AND   TV.ESTADO_TARJETA_ID = " +
                "(SELECT ESTADO_TARJETA_VIAJE_ID FROM TMS_ESTADOS_TARJETA_VIAJE_TBL WHERE NOMBRE_ESTADO = 'ABIERTA')";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x==null || x.size()==0) return null;
            return x.get(0).toString();
        }catch(Exception ex){
            return null;
        }
    }
    
    public String obtenerLigaCentral(){
        String Consulta = 
        "SELECT BDC.NOMBRE_DBLINK "+
        "FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
        "WHERE BDC.NOMBRE_ESQUEMA = 'XERTMS'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            return y.get(0).toString();
        }catch(Exception ex){
            return null;
        }
    }
    
    public String obtenerPMT(String mes, String Empresa, String Servicio, String Categoria){
        String Consulta =
                "SELECT Xer_Tms_Pkg2.TMS_GENERA_PMT_PRC ('"+mes+"', '"+Empresa+"', '"+Servicio+"', '"+Categoria+"') FROM DUAL";
        try{
            System.out.println(Consulta);
            Object res = em.createNativeQuery(Consulta).getSingleResult();
            if(res==null) return null;
            String resultado = res.toString();
            resultado = resultado.replace("[","");
            resultado = resultado.replace("]","");
            if(resultado==null || resultado.equals("")) return null;
            return resultado;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }        
    }
    
    // Trae la fecha del Servidor
    public Object getToDate() {
        String qry = "SELECT TO_CHAR(SYSDATE,'dd/MM/yyyy') FROM DUAL";  
        try {
            return  ((Vector)em.createNativeQuery(qry).getSingleResult()).elementAt(0).toString();
        } catch (Exception err) {
           System.err.println("Error getToDate: "+err.getMessage());
            err.printStackTrace();
            return false;
        }
    } 
    
    
    public TimeZone getTimeZone(){

        return TimeZone.getDefault(); 

    }
     
    public  Vector getIncidenciabyOperador_Date(String OperadoClave, String fecha)
    {
      Vector vresult = null;
      String query ="select  A.ACCION,TO_CHAR(OI.FECHA_INICIAL,'dd/MM/yyyy HH24:MI'), TO_CHAR(OI.FECHA_FINAL,'dd/MM/yyyy HH24:MI'), OI.ADICIONAL1    "+
                    ", DECODE(OI.INCIDENCIA_AUTORIZADA,'S','Autorizada','N','No Autorizada','C','Cancelada')  STATUS "+
                    " FROM TMS_OPERADOR_INCIDENCIAS_TBL OI, "+
                    " TMS_ACT_ADICIONALES_TBL AA,  "+
                    " TMS_OPERADORES_TBL O "+
                    " , TMS_ACCIONES_TBL A "+
                    " WHERE  O.CLAVE_OPERADOR ='"+OperadoClave+"' AND O.OPERADOR_ID= OI.OPERADOR_ID "+
                    " AND A.ACCION_ID=AA.ACCION_ID "+
                    //" AND  TO_DATE('"+fecha+"','DD/MM/YYYY')  BETWEEN OI.FECHA_INICIAL AND OI.FECHA_FINAL "+
                    " AND (TRUNC(OI.FECHA_INICIAL)  <= TO_DATE('"+fecha+"','DD/MM/YYYY')   and TO_DATE('"+fecha+"','DD/MM/YYYY') <= TRUNC(OI.FECHA_FINAL) )"+
	            " AND   OI.INCIDENCIA_ID = AA.TIPO_ACTIVIDAD_ADICIONAL_ID ";
      System.out.println("query "+query);
      vresult =(Vector) em.createNativeQuery(query).getResultList();
      
      return vresult;  
    }
    public Vector getCorridasByOperador_Date(String OperadoClave, String fecha)
    {
        Vector vresult = null;
        String query = " select TO_CHAR(C.FECHA_HORA_CORRIDA,'dd/MM/yyyy HH24:MI'),  C.SERVICIO, C.ORIGEN, C.DESTINO, C.AUTOBUS "+
                       " from TMS_TARJETAS_VIAJE_TBL T , "+
                       " TMS_CORRIDAS_VENTA_TBL C "+
	               " WHERE T.OPERADOR='"+OperadoClave+"' "+
                       " AND T.CORRIDA_ID= C.CORRIDA_ID  "+
                       " AND C.FECHA_HORA_CORRIDA     "+
                       " BETWEEN TO_DATE('"+fecha+" 00:00:00','DD/MM/YYYY HH24:MI:SS') " +
                       " AND  TO_DATE('"+fecha+" 23:59:00','DD/MM/YYYY HH24:MI:SS')";
       System.out.println("query "+query);
       vresult =(Vector) em.createNativeQuery(query).getResultList();
      
      return vresult;  
    }
    // 
    
   // trae todas las incidencias clave + descripcion
    public Vector getAllKindIncidencias()
   {
     Vector Vincidencias = new Vector();
     String query ="select AA.ACTIVIDAD_CLAVE, A.ACCION "+
                   " from TMS_ACT_ADICIONALES_TBL AA, TMS_ACCIONES_TBL A"+
                   " where AA.ACCION_ID =A.ACCION_ID  ";
       System.out.println("query "+query);
       Vincidencias =(Vector) em.createNativeQuery(query).getResultList();
      
      return Vincidencias;  
   }
   
    public Vector valorServicioMedGuardia(){
        Vector vValorGuardias = new Vector();
        String Consulta = 
            "SELECT SP.SERVICIO_ID, SP.PARAMETRO_VALOR "+
            "FROM TMS_PARAMETROS_CONFIG_TBL P "+
            ",TMS_SERVICIO_PARAMETROS_TBL SP "+
            "WHERE P.PARAMETRO_CODIGO = 'P_R_PAGMEDGUA' "+
            "AND   P.PARAMETRO_CONFIG_ID = SP.PARAMETRO_CONFIG_ID";
       
            vValorGuardias = (Vector) em.createNativeQuery(Consulta).getResultList();
           
     return vValorGuardias;
    }  
    
     //   checa si la incidencia aplica para recaudacion
    public boolean  getInc_Aplica_Recauda(String ClaveInc){
    boolean  frecauda = false;
     String query ="Select AA.APLICA_RECAUDACION "+
                   " from TMS_ACT_ADICIONALES_TBL AA "+
             
                   " where AA.ACTIVIDAD_CLAVE= '"+ClaveInc+"'";
       System.out.println("query "+query);
       Vector x = (Vector) em.createNativeQuery(query).getSingleResult();
         if(x==null || x.size()==0) return frecauda;
         else  if (x.get(0).toString().equalsIgnoreCase("S"))
               frecauda= true;            
      System.out.println("getInc_Aplica_Recauda "+frecauda);
      return frecauda;  
   }  


} // end class