/*
 * TmsAccUbicaFacade.java
 *
 * Created on 15 de agosto de 2007, 11:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.solicitud;   

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xertms.entidad.TmsAutobusesTbl;
import xertms.entidad.TmsEstadoAutobusesV;
import xertms.entidad.TmsEstadosTbl;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsAccUbicaFacade implements TmsAccUbicaFacadeRemote {
    @PersistenceContext(unitName="TMS_AccesoUbicaciones-ejbPU")
    EntityManager em;
    /**
     * Creates a new instance of TmsAccUbicaFacade
     */
    public TmsAccUbicaFacade() {
    }
    
    public Object[] obtenerDatosTerminalLocal(){
        String Consulta = 
        "SELECT BD.TERMINAL_ID, BD.NOMBRE_TERMINAL, BD.NOMBRE_ESQUEMA "+
        "FROM TMS_BASE_DATOS_CONFIG_TBL BD "+
        "WHERE BD.ESQUEMA_PROPIO = 'S'";
        try{
            System.out.println("Conf Terminal: "+Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Vector y = new Vector();
            y=(Vector) x.get(0);
            Object[] objetos = new Object[3];
            objetos[0] = y.get(0);
            objetos[1] = y.get(1);
            objetos[2] = y.get(2);
            return objetos;
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }
    
    public List<TmsAutobusesTbl> queryTmsAutobusesTblAll(){
        List<TmsAutobusesTbl> x = em.createNamedQuery("TmsAutobusesTbl.findAll").getResultList();
        if(x.size()==0) return null;
        for(int i=0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public List<TmsEstadosTbl> queryTmsEstadosTblAll(){
        return (List<TmsEstadosTbl>)em.createNamedQuery("TmsEstadosTbl.findAll").getResultList();
    }
    
    public List<TmsEstadoAutobusesV> queryTmsEstadoAutobusesVByNumeroEconomico(String NumeroEconomico){
        List<TmsEstadoAutobusesV> x=em.createNamedQuery("TmsEstadoAutobusesV.findByNumeroEconomico").setParameter("NumeroEconomico",NumeroEconomico).getResultList();
        if(x==null || x.size()==0) return null;
        for(int i=0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    public boolean registrarAccesoUbicacion(long autobusId, long estadoId, long ubicacionId, long actividadId,
                                            long usuarioId, Timestamp fechaAct, String esquema){
        try{
            TmsAutobusesTbl x = em.find(TmsAutobusesTbl.class, autobusId);
            if(x==null) return false;
            x.setComponente1Id(estadoId);
            x.setComponente2Id(ubicacionId);
            x.setComponente3Id(actividadId);
            x.setReplicacionOrigen(esquema);
            x.setUltimaActualizacionPor(usuarioId);
            x.setUltimaFechaActualizacion(fechaAct);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public int tieneOperadorTarjetaPendiente(){
        String Consulta = 
        "SELECT BD.TERMINAL_ID, BD.NOMBRE_TERMINAL "+
        "FROM TMS_BASE_DATOS_CONFIG_TBL BD "+
        "WHERE BD.ESQUEMA_PROPIO = 'S'";
        try{
            System.out.println("Conf Terminal: "+Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return 0;
            Vector y = new Vector();
            y=(Vector) x.get(0);
            Object[] objetos = new Object[2];
            objetos[0] = y.get(0);
            objetos[1] = y.get(1);
            return 1;
        }catch(Exception ex){
            //ex.printStackTrace();
            return -1;
        }
    }
    
    public String dbLinkCentral(){
        String Consulta = 
            "SELECT tmsBD.NOMBRE_DBLINK "+
            "FROM TMS_BASE_DATOS_CONFIG_TBL  tmsBD "+
            "WHERE tmsBD.NOMBRE_TERMINAL LIKE '%CENTRAL%'";
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x.size()==0) return null;
            return x.get(0).toString();
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }
    
    public String RecaudaAuto(long servicioId){
        String Consulta = 
            "SELECT tmsSer.PARAMETRO_VALOR "+
              "FROM TMS_SERVICIO_PARAMETROS_TBL tmsSer "+
              ",TMS_PARAMETROS_CONFIG_TBL  tmsGlo "+
             "WHERE tmsSer.SERVICIO_ID = "+servicioId+" "+
               "AND tmsSer.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID "+
               "AND tmsGlo.PARAMETRO_CODIGO = 'P_R_AUTORECAUDA'";
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x.size()==0) return null;
            return x.get(0).toString();
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }
    
    public Object[] OperadorDeAutobus(long autobusId){
        String Consulta = 
            "SELECT MAX(TVJ.FECHA_CREACION), CORN.CORRIDA_ID, CORN.CLAVE_CORRIDA, CORN.OPERADOR_ID, OPER.OPERADOR_NOMBRE_COMPLETO, OPER.APLICA_RETENCION, OPER.EMPRESA_ID "+
            "FROM TMS_TARJETAS_VIAJE_TBL TVJ "+
            ",TMS_CORRIDAS_TBL CORN "+
            ",TMS_OPERADORES_TBL OPER "+
            "WHERE CORN.AUTOBUS_ID = "+autobusId+" "+
            "AND   TVJ.CORRIDA_ID = CORN.CORRIDA_ID "+
            "AND   TVJ.ESTADO_TARJETA_ID = "+
                     "(SELECT ESTADO_TARJETA_VIAJE_ID "+
                      "FROM TMS_ESTADOS_TARJETA_VIAJE_TBL "+
                      "WHERE NOMBRE_ESTADO='ABIERTA') "+
            "AND  CORN.OPERADOR_ID = OPER.OPERADOR_ID "+
            "GROUP BY TVJ.FECHA_CREACION, CORN.CORRIDA_ID, CORN.CLAVE_CORRIDA, CORN.OPERADOR_ID, OPER.OPERADOR_NOMBRE_COMPLETO, OPER.APLICA_RETENCION, OPER.EMPRESA_ID";
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            Object[] datos = new Object[6];
            datos[0] =  y.get(1); // CORRIDAID
            datos[1] =  y.get(2); // CLAVECORRIDA
            datos[2] =  y.get(3); // OPERADORID
            datos[3] =  y.get(4); // OPERADOR NOMBRE
            datos[4] =  y.get(5); // APLICA RET
            datos[5] =  y.get(6); // EMPRESAID
            return datos;
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }
    
    public String TarjetasViajePendientes(long operadorId){
        String Consulta = 
            "SELECT TVJ.* "+
            "FROM TMS_TARJETAS_VIAJE_TBL TVJ "+
            ",TMS_CORRIDAS_TBL CORN "+
            "WHERE CORN.OPERADOR_ID = "+operadorId+" "+
            "AND   TVJ.CORRIDA_ID = CORN.CORRIDA_ID "+
            "AND   TVJ.ESTADO_TARJETA_ID = "+
                      "(SELECT ESTADO_TARJETA_VIAJE_ID "+
                       "FROM TMS_ESTADOS_TARJETA_VIAJE_TBL "+
                       "WHERE NOMBRE_ESTADO='ABIERTA')";
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            return y.get(1).toString();
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }
    
    public String obtieneRetencion(long empresaId){
        String Consulta = 
            "select emparam.PARAMETRO_VALOR from "+
                "tms_empresa_parametros_tbl emparam "+
                ",tms_parametros_config_tbl  param "+
                "where param.PARAMETRO_CODIGO = 'P_R_PORRET' "+
                "and   emparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID "+
                "and   emparam.EMPRESA_ID = "+empresaId;
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x.size()==0) return null;
            return x.get(0).toString();
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }
    
    public Object[] VentaSistema(String ClaveCorrida){
        String Consulta = 
            "select count(bol.BOLETO_ID) "+
            ", sum(bol.IMPORTE_BOLETO) from "+
            "tms_boletos_venta_tbl bol "+
            "where bol.TIPO_OPERACION IN('VT','VA','HO','AC') "+
            "and bol.CLAVE_CORRIDA = '"+ClaveCorrida+"'";
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            Object[] datos = new Object[6];
            datos[0] =  y.get(1); // CORRIDAID
            datos[1] =  y.get(2); // CLAVECORRIDA
            datos[2] =  y.get(3); // OPERADORID
            datos[3] =  y.get(4); // OPERADOR NOMBRE
            datos[4] =  y.get(5); // APLICA RET
            datos[5] =  y.get(6); // EMPRESAID
            return datos;
        }catch(Exception ex){
            //ex.printStackTrace();
            return null;
        }
    }
    
    public String claveOperador(String numEco){
        String Consulta = 
            "SELECT TABLA.OPERADOR "+
            "FROM "+
            "( "+
                "SELECT CVT.OPERADOR, CVT.FECHA_HORA_CORRIDA "+
                "FROM TMS_CORRIDAS_VENTA_TBL CVT "+
                "WHERE CVT.AUTOBUS = '"+numEco+"' "+
                "AND   (CVT.ESTADO_CORRIDA = 'D' "+
                       "OR CVT.FECHA_HORA_CORRIDA <= SYSDATE) "+
                "ORDER BY CVT.FECHA_HORA_CORRIDA DESC "+
            ") TABLA "+
            "WHERE ROWNUM=1";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            Object operador = y.get(0);
            if(operador==null) return null;
            return operador.toString();
        }catch(Exception ex){
            return null;
        }
    }

    public boolean actualizaEstadoOperador(String claveOperador, String edo, String ubi, String act, String esquema){
        String Consulta = 
            "UPDATE TMS_OPERADORES_TBL O "+
            "SET O.ACCION1_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL AC WHERE AC.ACCION = '"+edo+"'), "+
                "O.ACCION2_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL AC WHERE AC.ACCION = '"+ubi+"'), "+
                "O.ACCION3_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL AC WHERE AC.ACCION = '"+act+"'), "+
                "O.REPLICACION_ORIGEN = '"+esquema+"' "+
            "WHERE O.CLAVE_OPERADOR = '"+claveOperador+"'";
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    /************ esquema de replicacion ***************/
// TMS_REPLICAR_CONFIGURACION_PRC(vObjetoReplicar,vIdReplicar,vTerminal,vTerminalOrigen,vModo,vResultado);
    public boolean ejecutaReplicacion(String Tabla, String filaId, String esquemaPropio, String esquemaPropioOrigen, String vModo){
        String Consulta = 
            "DECLARE "+
            "vResultado VARCHAR2(1); "+
            "BEGIN "+
            "Tms_Replicacion_Pkg.TMS_REPLICAR_CONFIGURACION_PRC('"+Tabla+"','"+filaId+"','"+esquemaPropio+"','"+esquemaPropioOrigen+"','"+vModo+"',vResultado); "+
            "END;";
        try{
            int r = em.createNativeQuery(Consulta).executeUpdate();
            if(r>0) return true;
            return false;
        }catch(Exception ex){
            return false;
        }
    }
    
    public long obtenerOperadorId(String claveOperador){
        String Consulta = 
            "SELECT OPERADOR_ID FROM TMS_OPERADORES_TBL WHERE CLAVE_OPERADOR = '"+claveOperador+"'";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            Object operador = y.get(0);
            if(operador==null) return -1;
            return Long.valueOf(operador.toString());
        }catch(Exception ex){
            return -1;
        }
    }
}