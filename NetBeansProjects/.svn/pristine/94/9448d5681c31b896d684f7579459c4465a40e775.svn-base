/*
 * TmsGuardiasFacadeBean.java
 *
 * Created on 16 de abril de 2007, 12:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_guardias.solicitud;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Stateless;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tms_guardias.entidad.TmsAccionesTbl;
import tms_guardias.entidad.TmsEstadoOperadoresV;
import tms_guardias.entidad.TmsActAdicionalesTbl;
import tms_guardias.entidad.TmsOperadorIncidenciasTbl;
import tms_guardias.entidad.TmsOperadoresTbl;
import tms_guardias.entidad.TmsPagosActAdicionalesTbl;
import tms_guardias.entidad.TmsServiciosTbl;

/**
 * Este Metodo contiene todas las declaraciones de las funciones utilizadas en las
 * anotaciones de los entity beans. las cuales seran utilizadas al instanciar este campo
 * @author imorales
 * @version 1.01 27-Marzo-2007
 */
@Stateless
public class TmsGuardiasFacadeBean implements TmsGuardiasFacadeRemote {
    @PersistenceContext(unitName="TMS_Guardias-ejbPU")
    private EntityManager em;

    /*
    * Clase del Entity Bean, que se usa en la aplicacion
    */
    public TmsGuardiasFacadeBean(){ }

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
            s[2] = y.get(1).toString();
            return s;
        }catch(Exception ex){
            return null;
        }
    }


    public List<TmsActAdicionalesTbl> queryTmsIncidenciasAll(){
        List<TmsActAdicionalesTbl> x = em.createNamedQuery("TmsActAdicionalesTbl.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public List<TmsAccionesTbl> queryTmsAccionesTblAll(){
        List<TmsAccionesTbl> x = em.createNamedQuery("TmsAccionesTbl.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public List<TmsServiciosTbl> queryTmsServiciosAll(){
        List<TmsServiciosTbl> x = new ArrayList<TmsServiciosTbl>();
        String qry ="SELECT S.SERVICIO_ID,S.SERVICIO_CLAVE,S.SERVICIO_NUMERO,S.SERVICIO_NOMBRE, XER_TMS_PKG3.GET_DUR_GUARD_FNC(S.SERVICIO_ID) from TMS_SERVICIOS_TBL S ORDER BY S.SERVICIO_NOMBRE";
        Vector res = (Vector)em.createNativeQuery(qry).getResultList();
        if(res.size()==0) return null;
        for(int i = 0; i<res.size(); i++)
           x.add(new TmsServiciosTbl((Vector)res.get(i)));
        return x;
    }
    
    public Vector OperadorIncidencias(String claveOperador, String Servicio, String claveIncidencia, String fecha1, String fecha2, String estado, String termial){
        Vector vVector = new Vector();
          
        System.out.println("Inicio  OperadorIncidencias "+claveOperador+"   "+fecha1+"    "+fecha2+"    "+termial);
        try{
        String Consulta = 
                "SELECT "+
                "OI.OPER_INCIDENCIA_ID, OI.OPERADOR_ID, O.CLAVE_OPERADOR, O.OPERADOR_NOMBRE_COMPLETO, "+
                "OI.INCIDENCIA_ID, AA.ACTIVIDAD_CLAVE, I.ACCION, AA.APLICA_RECAUDACION, AA.CONFIGURABLE, "+
                "TO_CHAR(OI.FECHA_INICIAL,'dd/MM/yyyy HH24:MI'), TO_CHAR(OI.FECHA_FINAL,'dd/MM/yyyy HH24:MI'), OI.INCIDENCIA_VALOR, "+
                "DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) DURACION, "+
                "( "+
                "(NVL((SELECT EP.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL P, TMS_EMPRESA_PARAMETROS_TBL EP WHERE "+
                  "P.PARAMETRO_CODIGO='P_R_PORRET' "+
                  "AND O.APLICA_RETENCION = 'S' "+
                  "AND EP.EMPRESA_ID = O.EMPRESA_ID "+
                  "AND P.PARAMETRO_CONFIG_ID=EP.PARAMETRO_CONFIG_ID),0)/100) "+
                ") RETENCION, "+
                "(DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) * OI.INCIDENCIA_VALOR) MONTO, "+
                "S.SERVICIO_ID, "+
                "S.SERVICIO_NOMBRE, "+
                "OI.INCIDENCIA_AUTORIZADA, " +
                "(OI.ADICIONAL1 || OI.ADICIONAL2) OBSERVACION, "+
                "OI.ADICIONAL3, "+
                "OI.ADICIONAL4, " +
                "decode(OI.INCIDENCIA_AUTORIZADA,'S',to_char(OI.ULTIMA_FECHA_ACTUALIZACION,'DD/MM/RRRR HH24:MI'),'') "+
                "FROM TMS_OPERADOR_INCIDENCIAS_TBL OI "+
                ",TMS_OPERADORES_TBL O "+
                ",TMS_ACCIONES_TBL I "+
                ",TMS_ACT_ADICIONALES_TBL AA "+
                ",TMS_SERVICIOS_TBL S "+
                "WHERE OI.OPERADOR_ID = O.OPERADOR_ID "+
                "AND   OI.INCIDENCIA_ID = AA.TIPO_ACTIVIDAD_ADICIONAL_ID "+
                "AND   I.ACCION_ID = AA.ACCION_ID "+
                "AND   OI.SERVICIO_ID = S.SERVICIO_ID "+
                "AND   O.CLAVE_OPERADOR LIKE NVL('"+claveOperador+"', O.CLAVE_OPERADOR) "+
                "and   OI.adicional3 LIKE NVL('"+termial+"', OI.adicional3) "+
                "AND   S.SERVICIO_NOMBRE = NVL('"+Servicio+"', S.SERVICIO_NOMBRE) "+
                "AND   AA.ACTIVIDAD_CLAVE = NVL('GUA', AA.ACTIVIDAD_CLAVE) ";
             /*  "AND  (  (   (TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), OI.FECHA_INICIAL)) <= TRUNC(OI.FECHA_INICIAL) "+
                "AND   TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE))) >= TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)))   )"+
                "     OR (   (TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), OI.FECHA_INICIAL)) >= TRUNC(OI.FECHA_INICIAL) "+
                "     AND   TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE))) >= TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)))   )"+
                "     OR (   (TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), OI.FECHA_INICIAL)) <= TRUNC(OI.FECHA_FINAL) "+
                "     AND   TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE))) >= TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)))   )"+
                "     OR (   (TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), OI.FECHA_INICIAL)) >= TRUNC(OI.FECHA_INICIAL) "+
                "     AND   TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE))) >= TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)))   )"+
                "  ) " +*/
                /*"AND ("+
                "    ( TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), NVL( OI.FECHA_INICIAL,SYSDATE))) >= TRUNC(OI.FECHA_INICIAL) "+
                "     AND   TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE))) <= TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)) )  "+
                "   OR ( TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), NVL( OI.FECHA_INICIAL,SYSDATE))) <= TRUNC(OI.FECHA_INICIAL) "+
                "     AND   TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE))) <= TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)) )  "+
             //   "   OR ( TRUNC(NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'), NVL( OI.FECHA_INICIAL,SYSDATE))) >= TRUNC(OI.FECHA_INICIAL) "+
              //  "     AND   TRUNC(NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'), NVL(OI.FECHA_FINAL,SYSDATE))) >= TRUNC(NVL(OI.FECHA_FINAL,SYSDATE)) )  "+
                  " )"+*/
                //VAGL 02/04/2014
                if(!fecha1.equals("") && !fecha2.equals(""))
                    Consulta = Consulta +" and trunc(OI.FECHA_INICIAL) between NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'),trunc(OI.FECHA_INICIAL)) and NVL(TO_DATE('"+fecha2+"','DD/MM/YYYY'),trunc(OI.FECHA_INICIAL)) ";
                if(!fecha1.equals("") && fecha2.equals(""))
                    Consulta = Consulta +" and trunc(OI.FECHA_INICIAL) between NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'),trunc(OI.FECHA_INICIAL)) and NVL(TO_DATE('"+fecha1+"','DD/MM/YYYY'),trunc(OI.FECHA_INICIAL)) ";
                Consulta = Consulta+"AND   OI.INCIDENCIA_AUTORIZADA = NVL('"+estado+"',OI.INCIDENCIA_AUTORIZADA) "+
                "ORDER BY OI.OPER_INCIDENCIA_ID";
             System.out.println("OperadorIncidencias ----   Buscar::: "+Consulta);
           
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            return vVector;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public Vector OperadorIncidencia(long operIncId){
        Vector vVector = new Vector();
        String Consulta = 
            "SELECT "+
            "OI.OPER_INCIDENCIA_ID, OI.OPERADOR_ID, O.CLAVE_OPERADOR, O.OPERADOR_NOMBRE_COMPLETO, "+
            "OI.INCIDENCIA_ID, AA.ACTIVIDAD_CLAVE, I.ACCION, AA.APLICA_RECAUDACION, AA.CONFIGURABLE, "+
            "OI.FECHA_INICIAL, OI.FECHA_FINAL, OI.INCIDENCIA_VALOR, "+
            "DECODE(AA.INCIDENCIA_DURACION, 'EVENTO', 1, DECODE(OI.FECHA_FINAL, NULL, NULL, (OI.FECHA_FINAL-OI.FECHA_INICIAL)+1)) DURACION, "+
            "( "+
            "(NVL((SELECT EP.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL P, TMS_EMPRESA_PARAMETROS_TBL EP WHERE "+
              "P.PARAMETRO_CODIGO='P_R_PORRET' "+
              "AND O.APLICA_RETENCION = 'S' "+
              "AND EP.EMPRESA_ID = O.EMPRESA_ID "+
              "AND P.PARAMETRO_CONFIG_ID=EP.PARAMETRO_CONFIG_ID),0)/100) "+
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
            "WHERE AA.ACTIVIDAD_CLAVE = NVL('GUA', AA.ACTIVIDAD_CLAVE) "+
            "AND   OI.OPERADOR_ID = O.OPERADOR_ID "+
            "AND   OI.INCIDENCIA_ID = AA.TIPO_ACTIVIDAD_ADICIONAL_ID "+
            "AND   I.ACCION_ID = AA.ACCION_ID "+
            "AND   OI.SERVICIO_ID = S.SERVICIO_ID "+
            "AND   OI.OPER_INCIDENCIA_ID = "+operIncId;
        try{
            //System.out.println("Buscar::: "+Consulta);
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            Vector y = (Vector) vVector.get(0);
            return y;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<TmsEstadoOperadoresV> queryTmsEstadoOperadoresVByNumero(String numero){
        List<TmsEstadoOperadoresV> x = em.createNamedQuery("TmsEstadoOperadoresV.findByNumero").setParameter("Numero", numero).getResultList();
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public long registraIncidenciaOperador(String prefijo, TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl){
        System.out.println("Guardia Fecha Inicial: "+tmsIncidenciaOperadorTbl.getFechaInicial());
        System.out.println("Guardia Fecha Final: "+tmsIncidenciaOperadorTbl.getFechaFinal());
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
    
    public boolean modificarIncidenciaOperador(long IncOperId, String valor, Timestamp fechaFinal, String observacion1, String observacion2, long usuarioId, Timestamp Fecha){
        TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl = em.find(TmsOperadorIncidenciasTbl.class, IncOperId);
        if(tmsIncidenciaOperadorTbl==null) return false;
        em.refresh(tmsIncidenciaOperadorTbl);
        tmsIncidenciaOperadorTbl.setIncidenciaValor(valor);
        tmsIncidenciaOperadorTbl.setFechaFinal(fechaFinal);
        tmsIncidenciaOperadorTbl.setAdicional1(observacion1);
        tmsIncidenciaOperadorTbl.setAdicional2(observacion2);
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
    
    // DATOS GUARDIA
    public String duracionGuardia(){
        Vector vVector = new Vector();
        String Consulta = 
            "SELECT EP.PARAMETRO_VALOR "+
            "FROM TMS_PARAMETROS_CONFIG_TBL P "+
            ",TMS_EMPRESA_PARAMETROS_TBL EP "+
            "WHERE P.PARAMETRO_CODIGO = 'P_HRSGUA' "+
            "AND   P.PARAMETRO_CONFIG_ID = EP.PARAMETRO_CONFIG_ID "+
            "AND   EP.EMPRESA_ID = (SELECT EM.EMPRESA_ID FROM TMS_EMPRESAS_TBL EM WHERE EM.EMPRESA_NOMBRE_CORTO = 'AMPERSA')";
        try{
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return "8";
            Vector y = (Vector) vVector.get(0);
            return y.get(0).toString();
        }catch(Exception ex){
            return null;
        }
    }
    
    public String[][] valorServicioGuardia(){
        Vector vVector = new Vector();        String Consulta = 
            "SELECT SP.SERVICIO_ID, SP.PARAMETRO_VALOR "+
            "FROM TMS_PARAMETROS_CONFIG_TBL P "+
            ",TMS_SERVICIO_PARAMETROS_TBL SP "+
            "WHERE P.PARAMETRO_CODIGO = 'P_R_PAGGUA' "+
            "AND   P.PARAMETRO_CONFIG_ID = SP.PARAMETRO_CONFIG_ID";
        try{
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            Vector y;
            String[][] z = new String[vVector.size()][2];
            for(int i=0; i<vVector.size(); i++){
                y = (Vector) vVector.get(i);
                z[i][0] = y.get(0).toString();
                z[i][1] = y.get(1).toString();
            }
            return z;
        }catch(Exception ex){
            return null;
        }
    }
    
    
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

    public String obtenerTerminales(){
        String Consulta = "SELECT XER_TMS_PKG3.GET_TERMINALES_PRC FROM DUAL";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return "";
           return x.toString().replace("[","").replace("]","");
        }catch(Exception ex){
            return "";
        }
    }

        public String obtenerRegistrosOperador(String clave,String fechaInicial, String fechaFinal){
        String Consulta = "select XER_TMS_PKG3.GET_REGISTROS_OPER_PRC('"+clave+"','"+fechaInicial+"','"+fechaFinal+"') from dual";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            System.out.println("Vector Registros: "+x.toString());
            if(x==null || x.size()==0) return "";
            return x.toString().replace("[","").replace("]","");
        }catch(Exception ex){
            return "";
        }
    }

        public String obtenerTarjetasOperador(String clave,String fechaInicial, String fechaFinal){
        String Consulta = "select XER_TMS_PKG3.GET_TARJETAS_OPER_PRC('"+clave+"','"+fechaInicial+"','"+fechaFinal+"') from dual";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            //System.out.println("Vector Tarjetas: "+x.toString());
            if(x==null || x.size()==0) return "";
            return x.toString().replace("[","").replace("]","");
        }catch(Exception ex){
            return "";
        }
    }

        public String obtenerNumTarOperador(String clave,String fechaInicial, String fechaFinal){
        String Consulta = "select XER_TMS_PKG3.GET_NUM_TAR_OPER_PRC('"+clave+"','"+fechaInicial+"','"+fechaFinal+"') from dual";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            //System.out.println("Vector Tarjetas: "+x.toString());
            if(x==null || x.size()==0) return "0";
            return x.toString().replace("[","").replace("]","");
        }catch(Exception ex){
            return "";
        }
    }

        public String validaGuardia(String clave,String fechaInicial, String fechaFinal,long pServicioId){
        String Consulta = "select XER_TMS_PKG3.GET_VALIDA_GUARDIA_PRC('"+clave+"','"+fechaInicial+"','"+fechaFinal+"',"+pServicioId+") from dual";
        System.out.println("Consulta validaGuardia: "+Consulta);
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            System.out.println("Vector Registros: "+x.toString());
            if(x==null || x.size()==0) return "";
            return x.toString().replace("[","").replace("]","");
        }catch(Exception ex){
            return "";
        }
    }


    public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'RRRR-MM-DD HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

   public long buscaGuardiasCruzadas(String operado, String fechaInicial, String fechaFinal){
       String qry = "SELECT count(OI.OPER_INCIDENCIA_ID) "
               + "FROM TMS_OPERADOR_INCIDENCIAS_TBL OI  "
               + ",TMS_OPERADORES_TBL O ,TMS_ACCIONES_TBL I  "
               + ",TMS_ACT_ADICIONALES_TBL AA  "
               + ",TMS_SERVICIOS_TBL S  "
               + "WHERE OI.OPERADOR_ID = O.OPERADOR_ID  "
               + "AND   OI.INCIDENCIA_ID = AA.TIPO_ACTIVIDAD_ADICIONAL_ID "
               + "AND   I.ACCION_ID = AA.ACCION_ID "
               + "AND   OI.SERVICIO_ID = S.SERVICIO_ID "
               + "AND   O.CLAVE_OPERADOR LIKE NVL('"+operado+"', O.CLAVE_OPERADOR) "
               + "AND   AA.ACTIVIDAD_CLAVE = NVL('GUA', AA.ACTIVIDAD_CLAVE)   "
               + "AND   (OI.FECHA_INICIAL BETWEEN TO_DATE('"+fechaInicial+"','DD/MM/YYYY HH24:MI') "
               + "AND  TO_DATE('"+fechaFinal+"','DD/MM/YYYY HH24:MI') "
               + "OR   OI.FECHA_FINAL > TO_DATE('"+fechaInicial+"','DD/MM/YYYY HH24:MI') "
               + "AND  OI.FECHA_FINAL <  TO_DATE('"+fechaFinal+"','DD/MM/YYYY HH24:MI')) "
               + "ORDER BY OI.OPER_INCIDENCIA_ID";
       System.out.println("buscaGuardiasCruzadas: "+qry);
       Vector resp = (Vector)em.createNativeQuery(qry).getSingleResult();

       return Long.valueOf(resp.get(0).toString());
   }

    public Vector FuncionesDeUsuario(long MENU_ID,String usuario) throws javax.ejb.EJBException{
       /* String Consulta =
            "select fun.FUNCION_NUMERO, fun.DESCRIPCION, fun.AUDITABLE from "+
            "tms_funciones_tbl fun "+
            ",tms_menus_encabezado_tbl men "+
            ",tms_menus_lineas_tbl mlin "+
            "where men.MENU_ID = "+MENU_ID+" "+
            "and men.MENU_ID = mlin.MENU_ID "+
            "and mlin.FUNCION_ID = fun.FUNCION_ID " +
            "order by fun.FUNCION_NUMERO";*/
           String Consulta = "select fun.FUNCION_NUMERO, fun.DESCRIPCION, fun.AUDITABLE from  "
                   + "tms_funciones_tbl fun  "
                   + ",tms_menus_encabezado_tbl men "
                   + ",TMS_MENUS_LINEAS_TBL MLIN  "
                   + ",TMS_USUARIOS_TBL U "
                   + ",TMS_PERFILES_TBL P "
                   + ",TMS_USUARIO_PERFILES_TBL UP "
                   + "WHERE U.USUARIO_NUMERO ='"+usuario+"' "
                   + "AND UP.USUARIO_ID= U.USUARIO_ID "
                   + "and P.PERFIL_ID = UP.PERFIL_ID "
                   + "and men.MENU_ID = P.MENU_ID "
                   + "and men.MENU_ID = mlin.MENU_ID "
                   + "AND MLIN.FUNCION_ID = FUN.FUNCION_ID "
                   + "order by fun.FUNCION_NUMERO";
                
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            return x;
        }
        catch(NoResultException nrex){
            return null;
        }
    }

   public long duracionGuardiaServicio(long pServcioId){
        Vector vVector = new Vector();
        String Consulta = "SELECT XER_TMS_PKG3.GET_DUR_GUARD_FNC("+pServcioId+") FROM DUAL";
        System.out.println("Consulta(duracionGuardiaServicio): "+Consulta);
        try{
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return 8;
            Vector y = (Vector) vVector.get(0);
            System.out.println("resp(duracionGuardiaServicio): "+Long.valueOf(y.get(0).toString()));
            return Long.valueOf(y.get(0).toString());

        }catch(Exception ex){
            return 8;
        }
    }
   
      public long getDatosRecaudador(){
        Vector vVector = new Vector();
        String Consulta = "SELECT XER_TMS_PKG3.GET_USUARIO_RECAUDA_FNC FROM DUAL";
        System.out.println("Consulta(duracionGuardiaServicio): "+Consulta);
        try{
            vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return 1;
            Vector y = (Vector) vVector.get(0);
            return Long.valueOf(y.get(0).toString());
        }catch(Exception ex){
            return 1;
        }
    }

      public Vector getImpresora(String caja){
          String qry ="SELECT I.IMPRESORA_NOMBRE,CI.SALIDA_IMPRESION, F.FORMATO_NOMBRE "
                  + "FROM TMS_CAJAS_TBL C "
                  + "LEFT JOIN TMS_CAJAS_IMPRESORAS_TBL CI ON CI.CAJA_ID = C.CAJA_ID  "
                  + "LEFT JOIN TMS_FORMATOS_BOLETO_TBL F on F.FORMATO_BOLETO_ID = CI.FORMATO_BOLETO_ID  "
                  + "LEFT JOIN TMS_IMPRESORAS_TBL I ON I.IMPRESORA_ID = CI.IMPRESORA_ID  "
                  + "WHERE UPPER(C.CAJA_NOMBRE) = upper('"+caja+"')  "
                  + "AND F.FORMATO_NOMBRE IN ('TICKET_TERMICO','TICKET') "
                  + "AND CI.ACTIVIDAD_IMPRESORA = 'TICKETS'";

          return (Vector)em.createNativeQuery(qry).getResultList();
      }

      public TimeZone getTimeZone()
      {
        return TimeZone.getDefault();
      }


}