/*
 * TmsPSDFacade.java
 *
 * Created on 15 de agosto de 2007, 11:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.solicitud;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.OracleCallableStatement;
import xertms.entidad.TmsAccionesTbl;
import xertms.entidad.TmsAutobusPlantillasEncTbl;
import xertms.entidad.TmsAutobusesTbl;
import xertms.entidad.TmsAutobusesXTbl;
import xertms.entidad.TmsCorridasTbl;
import xertms.entidad.TmsCorridasVentaTbl;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsEstadoAutobusesV;
import xertms.entidad.TmsEstadosCorridaTbl;
import xertms.entidad.TmsEstadosTbl;
import xertms.entidad.TmsFlotillasTbl;
import xertms.entidad.TmsMonitorCorridaV;
import xertms.entidad.TmsOpcionesSustAutobusV;
import xertms.entidad.TmsOperadoresTbl;
import xertms.entidad.TmsRutasTbl;
import xertms.entidad.TmsRutasV;
import xertms.entidad.TmsServiciosTbl;
import xertms.entidad.TmsTarjetasViajeTbl;
import xertms.entidad.TmsBDConfigTbl;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsPSDFacade implements TmsPSDFacadeRemote {
    @PersistenceContext(unitName="TMS_PSD-ejbPU")
    EntityManager em;

    @Resource(name = "TMS_DB")
    private DataSource dataSource;

    /**
     * Creates a new instance of TmsPSDFacade
     */
    public TmsPSDFacade() {
    }
    
    public Object[] obtenerDatosTerminalLocal(){
        String Consulta = 
        "SELECT BD.TERMINAL_ID, BD.NOMBRE_TERMINAL, DECODE(BD.NOMBRE_ESQUEMA,'XERTMS','CENTRAL',BD.NOMBRE_ESQUEMA) "+
        "FROM TMS_BASE_DATOS_CONFIG_TBL BD "+
        "WHERE BD.ESQUEMA_PROPIO = 'S'";
        try{
            //System.out.println("C");
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
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<TmsMonitorCorridaV> BusquedaMonitorCorridas(String Servicio, String Empresa, String Autobus, String Operador,
            String tipoCorrida, String FechaHoraDesde, String FechaHoraHasta,
            String DBLink, String Destino){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String masTiempo = (FechaHoraHasta.equals(FechaHoraDesde) ? "+1" : "");
        List<TmsMonitorCorridaV> x=null;
        String Consulta = 
                "SELECT "+
                "MON.CORRIDA_ID, MON.CLAVE_CORRIDA, MON.TIPO_CORRIDA, MON.ESTADO_CORRIDA, MON.SERVICIO_ID, "+
                "MON.SERVICIO, MON.FECHA, MON.EMPRESA_ID, MON.EMPRESA, MON.ORIGEN_ID, "+
                "MON.ORIGEN, MON.DESTINO_ID, MON.DESTINO, MON.HORA, MON.AUTOBUS_ORIGINAL_ID, "+
                "MON.AUTOBUS_ORIGINAL, MON.AUTOBUS_ID, MON.AUTOBUS, MON.OPERADOR_ORIGINAL_ID, MON.OPERADOR_ORIGINAL, "+
                "MON.NOMBRE_OPERADOR_ORIGINAL, MON.OPERADOR_ID, MON.OPERADOR, MON.NOMBRE_OPERADOR_SUST, MON.CORRIDA_CANCELADA_ID, "+
                "MON.CORRIDA_CANCELADA, MON.TARJETA_VIAJE_ID, MON.FOLIO_TARJETA_VIAJE, MON.ESTADO_TARJETA_VIAJE, MON.FECHA_HORA_CORRIDA, "+
                "MON.FLOTILLA_ID, MON.NOMBRE_FLOTILLA, MON.RUTA_ID "+
                "FROM TMS_MONITOR_CORRIDA_V"+DBLink+" MON "+
                "WHERE MON.SERVICIO = NVL('"+Servicio+"', MON.SERVICIO) "+
                "AND	  MON.EMPRESA = NVL('"+Empresa+"', MON.EMPRESA) "+
                "AND	  MON.DESTINO = NVL('"+Destino+"', MON.DESTINO) "+
                "AND      DECODE(MON.AUTOBUS,NULL,'NADA',MON.AUTOBUS) = NVL('"+Autobus+"', DECODE(MON.AUTOBUS,NULL,'NADA',MON.AUTOBUS)) "+
                "AND      DECODE(MON.OPERADOR,NULL,'NADA',MON.OPERADOR) = NVL('"+Operador+"', DECODE(MON.OPERADOR,NULL,'NADA',MON.OPERADOR)) "+
                "AND	  MON.TIPO_CORRIDA = NVL('"+tipoCorrida+"', MON.TIPO_CORRIDA) "+
                "AND	  MON.FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+FechaHoraDesde+"','DD/MM/YYYY HH24:MI') " +
                                                    "AND TO_DATE('"+FechaHoraHasta+"','DD/MM/YYYY HH24:MI')"+masTiempo+" " +
                "ORDER BY MON.FECHA_HORA_CORRIDA";
        try{
            //System.out.println(Consulta);
            Vector vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            x = new ArrayList<TmsMonitorCorridaV>();
            TmsMonitorCorridaV xx;
            Vector vX;
            Timestamp tm;
            for(int i=0; i<vVector.size(); i++){
                tm = null;
                vX = (Vector) vVector.get(i);
                xx = new TmsMonitorCorridaV();
                xx.setCorridaId(Long.valueOf(vX.get(0).toString()));
                xx.setClaveCorrida(vX.get(1).toString());
                xx.setTipoCorrida(vX.get(2).toString());
                xx.setEstadoCorrida(vX.get(3).toString());
                xx.setServicioId(Long.valueOf(vX.get(4).toString()));
                xx.setServicio(vX.get(5).toString());
                
                tm=tm.valueOf(vX.get(6).toString());
                xx.setFecha(tm);
                
                xx.setEmpresaId(Long.valueOf(vX.get(7).toString()));
                xx.setEmpresa(vX.get(8).toString());
                xx.setOrigenId(Long.valueOf(vX.get(9).toString()));
                xx.setOrigen(vX.get(10).toString());
                xx.setDestinoId(Long.valueOf(vX.get(11).toString()));
                xx.setDestino(vX.get(12).toString());
                
                tm=tm.valueOf(vX.get(13).toString());
                xx.setHora(tm);
                if(vX.get(14)!=null) xx.setAutobusOriginalId(Long.valueOf(vX.get(14).toString()));
                if(vX.get(15)!=null) xx.setAutobusOriginal(vX.get(15).toString());
                if(vX.get(16)!=null) xx.setAutobusId(Long.valueOf(vX.get(16).toString()));
                if(vX.get(17)!=null) xx.setAutobus(vX.get(17).toString());
                if(vX.get(18)!=null) xx.setOperadorOriginalId(Long.valueOf(vX.get(18).toString()));
                if(vX.get(19)!=null) xx.setOperadorOriginal(vX.get(19).toString());
                if(vX.get(20)!=null) xx.setNombreOperadorOriginal(vX.get(20).toString());
                if(vX.get(21)!=null) xx.setOperadorId(Long.valueOf(vX.get(21).toString()));
                if(vX.get(22)!=null) xx.setOperador(vX.get(22).toString());
                if(vX.get(23)!=null) xx.setNombreOperadorSust(vX.get(23).toString());
                if(vX.get(24)!=null) xx.setCorridaCanceladaId(Long.valueOf(vX.get(24).toString()));
                if(vX.get(25)!=null) xx.setCorridaCancelada(vX.get(25).toString());
                if(vX.get(26)!=null) xx.setTarjetaViajeId(Long.valueOf(vX.get(26).toString()));
                if(vX.get(27)!=null) xx.setFolioTarjetaViaje(vX.get(27).toString());
                if(vX.get(28)!=null) xx.setEstadoTarjetaViaje(vX.get(28).toString());
                
                tm=tm.valueOf(vX.get(29).toString());
                xx.setFechaHoraCorrida(tm);
                
                if(vX.get(30)!=null) xx.setFlotillaId(Long.valueOf(vX.get(30).toString()));
                if(vX.get(31)!=null) xx.setNombreFlotilla(vX.get(31).toString());
                if(vX.get(32)!=null) xx.setRutaId(Long.valueOf(vX.get(32).toString()));
                x.add(xx);
            }
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public Vector BusquedaMonitorCorridasSust(String Servicio, String Empresa, String Autobus, String Operador,
            String tipoCorrida, String FechaHoraDesde, String FechaHoraHasta,
            String DBLink, String Destino){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String masTiempo = (FechaHoraHasta.equals(FechaHoraDesde) ? "+1" : "");
        String Consulta = 
                "SELECT "+
                "MON.CLAVE_CORRIDA, MON.SERVICIO, MON.EMPRESA, "+
                "MON.ORIGEN, " +
                "MON.DESTINO, " +
                "TO_CHAR(MON.HORA,'HH24:MI') HORA, " +
                "MON.CORRIDA_ID "+
                "FROM TMS_MONITOR_CORRIDA_V"+DBLink+" MON "+
                "WHERE MON.SERVICIO = NVL('"+Servicio+"', MON.SERVICIO) "+
                "AND	  MON.EMPRESA = NVL('"+Empresa+"', MON.EMPRESA) "+
                "AND	  MON.DESTINO = NVL('"+Destino+"', MON.DESTINO) "+
                "AND      DECODE(MON.AUTOBUS,NULL,'NADA',MON.AUTOBUS) = NVL('"+Autobus+"', DECODE(MON.AUTOBUS,NULL,'NADA',MON.AUTOBUS)) "+
                "AND      DECODE(MON.OPERADOR,NULL,'NADA',MON.OPERADOR) = NVL('"+Operador+"', DECODE(MON.OPERADOR,NULL,'NADA',MON.OPERADOR)) "+
                "AND	  MON.TIPO_CORRIDA = NVL('"+tipoCorrida+"', MON.TIPO_CORRIDA) "+
                "AND      MON.ESTADO_CORRIDA IN ('A','D','E') "+
                "AND	  MON.FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+FechaHoraDesde+"','DD/MM/YYYY HH24:MI') " +
                "AND TO_DATE('"+FechaHoraHasta+"','DD/MM/YYYY HH24:MI')"+masTiempo+" " +
                "ORDER BY MON.FECHA_HORA_CORRIDA";
        try{
            //System.out.println(Consulta);
            Vector vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            return vVector;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }    
    
    public Vector BusquedaMonitorCorridasSust2(String Servicio, String Empresa, String Autobus, String Operador,
            String tipoCorrida, String FechaHoraDesde, String FechaHoraHasta,
            String DBLink, String Destino){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String masTiempo = (FechaHoraHasta.equals(FechaHoraDesde) ? "+1" : "");
        String Consulta = 
                "SELECT "+
                "MON.CLAVE_CORRIDA, MON.SERVICIO, MON.EMPRESA, "+
                "MON.ORIGEN, " +
                "MON.DESTINO, " +
                "TO_CHAR(MON.HORA,'HH24:MI') HORA, " +
                "MON.CORRIDA_ID "+
                "FROM TMS_MONITOR_CORRIDA_V"+DBLink+" MON "+
                "WHERE MON.SERVICIO = NVL('"+Servicio+"', MON.SERVICIO) "+
                "AND	  MON.EMPRESA = NVL('"+Empresa+"', MON.EMPRESA) "+
                "AND	  MON.DESTINO = NVL('"+Destino+"', MON.DESTINO) "+
                "AND      DECODE(MON.AUTOBUS,NULL,'NADA',MON.AUTOBUS) = NVL('"+Autobus+"', DECODE(MON.AUTOBUS,NULL,'NADA',MON.AUTOBUS)) "+
                "AND      DECODE(MON.OPERADOR,NULL,'NADA',MON.OPERADOR) = NVL('"+Operador+"', DECODE(MON.OPERADOR,NULL,'NADA',MON.OPERADOR)) "+
                "AND	  MON.TIPO_CORRIDA = NVL('"+tipoCorrida+"', MON.TIPO_CORRIDA) "+
                "AND      MON.ESTADO_CORRIDA = 'A' "+
                "AND	  MON.FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+FechaHoraDesde+"','DD/MM/YYYY HH24:MI') " +
                "AND TO_DATE('"+FechaHoraHasta+"','DD/MM/YYYY HH24:MI')"+masTiempo+" " +
                "ORDER BY MON.FECHA_HORA_CORRIDA";
        try{
            //System.out.println(Consulta);
            Vector vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            return vVector;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    } 
    
    public List<TmsOpcionesSustAutobusV> buscarOpcionesSustAutobus(long flotillaId, long ubicacionId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String strFlotillaId="";
        if(flotillaId!=-1) strFlotillaId = String.valueOf(flotillaId);
        List<TmsOpcionesSustAutobusV> x=null;
        String Consulta =
          "SELECT * FROM  "+
          "TMS_OPCIONES_SUST_AUTOBUS_V"+DBLink+" "+
          "WHERE REGLA_FLOTILLA_ID = NVL('"+strFlotillaId+"', FLOTILLA_ID) "+
          "AND UBICACION_ID="+ubicacionId+" "+
          "AND EDOGRAL_ID = (SELECT EDO.ESTADO_ID "+
                            "FROM TMS_ESTADOS_TBL"+DBLink+" EDO "+
                            "WHERE EDO.ESTADO_NOMBRE = 'ENROLADO' "+
                            ") " +
           "ORDER BY TO_NUMBER(NUMERO_ECONOMICO)";
        try{
            //System.out.println(Consulta);
            Vector vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            x = new ArrayList<TmsOpcionesSustAutobusV>();
            TmsOpcionesSustAutobusV xx;
            Vector vX;
            Timestamp tm;
            for(int i=0; i<vVector.size(); i++){
                tm = null;
                vX = (Vector) vVector.get(i);
                xx = new TmsOpcionesSustAutobusV();
                
                if(vX.get(0)!=null) xx.setVistaId(Long.valueOf(vX.get(0).toString()));
                if(vX.get(1)!=null) xx.setFlotillaId(Long.valueOf(vX.get(1).toString()));
                if(vX.get(2)!=null) xx.setNombreFlotilla(vX.get(2).toString());
                if(vX.get(3)!=null) xx.setEmpresaId(Long.valueOf(vX.get(3).toString()));
                if(vX.get(4)!=null) xx.setEmpresaNombre(vX.get(4).toString());
                if(vX.get(5)!=null) xx.setAutobusId(Long.valueOf(vX.get(5).toString()));
                if(vX.get(6)!=null) xx.setNumeroEconomico(vX.get(6).toString());
                if(vX.get(7)!=null) xx.setEdogralId(Long.valueOf(vX.get(7).toString()));
                if(vX.get(8)!=null) xx.setEdogral(vX.get(8).toString());
                if(vX.get(9)!=null) xx.setUbicacionId(Long.valueOf(vX.get(9).toString()));
                if(vX.get(10)!=null) xx.setUbicacion(vX.get(10).toString());
                if(vX.get(11)!=null) xx.setActividadId(Long.valueOf(vX.get(11).toString()));
                if(vX.get(12)!=null) xx.setActividad(vX.get(12).toString());
                if(vX.get(13)!=null) xx.setPrioridad(Long.valueOf(vX.get(13).toString()));
                
                x.add(xx);
            }
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    /*public List<TmsOpcionesSustAutobusV> buscarOpcionesSustAutobus(long flotillaId, long ubicacionId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String strFlotillaId="";
        if(flotillaId!=-1) strFlotillaId = String.valueOf(flotillaId);
        String Consulta =
          "SELECT * FROM  "+
          "TMS_OPCIONES_SUST_AUTOBUS_V"+DBLink+" "+
          //"WHERE FLOTILLA_ID = NVL('"+strFlotillaId+"', FLOTILLA_ID) "+
          "WHERE FLOTILLA_ID = NVL('', FLOTILLA_ID) "+
          "AND UBICACION_ID="+ubicacionId+" "+
          "AND EDOGRAL_ID = (SELECT EDO.ESTADO_ID "+
                            "FROM TMS_ESTADOS_TBL"+DBLink+" EDO "+
                            "WHERE EDO.ESTADO_NOMBRE = 'ENROLADO' "+
                            ")";
        try{
            System.out.println(Consulta);
            List<TmsOpcionesSustAutobusV> x = em.createNativeQuery(Consulta, TmsOpcionesSustAutobusV.class).getResultList();
            if(x==null || x.size()==0) return null;
            for(int i=0; i<x.size(); i++) em.refresh(x.get(i));
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }*/
    
    public List<TmsEmpresasTbl> queryTmsEmpresasTblAll(){
        return (List<TmsEmpresasTbl>)em.createNamedQuery("TmsEmpresasTbl.findAll").getResultList();
    }
    
    public List<TmsFlotillasTbl> queryTmsFlotillasTblAll(){
        return (List<TmsFlotillasTbl>)em.createNamedQuery("TmsFlotillasTbl.findAll").getResultList();
    }
    
    public List<TmsAutobusesXTbl> queryTmsAutobusesXTblAll(){
        return (List<TmsAutobusesXTbl>)em.createNamedQuery("TmsAutobusesXTbl.findAll").getResultList();
    }
    
    public List<TmsAutobusesXTbl> queryTmsAutobusesXTblByNumEco(String numEco, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        List<TmsAutobusesXTbl> x=null;
        String Consulta = 
                "SELECT O.* FROM TMS_AUTOBUSES_TBL"+DBLink+" O WHERE O.NUMERO_ECONOMICO LIKE '"+numEco+"' ORDER BY TO_NUMBER(O.NUMERO_ECONOMICO)";
        try{
            //System.out.println(Consulta);
            Vector vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            
            x = new ArrayList<TmsAutobusesXTbl>();
            TmsAutobusesXTbl xx;
            Vector vX;
            Timestamp tm;
            for(int i=0; i<vVector.size(); i++){
                //System.out.println(" "+i);
                tm = null;
                vX = (Vector) vVector.get(i);
                xx = new TmsAutobusesXTbl();
                
                if(vX.get(0)!=null) xx.setAutobusId(Long.valueOf(vX.get(0).toString()));
                if(vX.get(1)!=null) xx.setNumeroEconomico(vX.get(1).toString());
                if(vX.get(2)!=null) xx.setPlantillaEncId(Long.valueOf(vX.get(2).toString()));
                if(vX.get(3)!=null) xx.setFlotillaId(Long.valueOf(vX.get(3).toString()));
                if(vX.get(4)!=null) xx.setOperadorIdPlanta(Long.valueOf(vX.get(4).toString()));
                if(vX.get(5)!=null) xx.setKmsRecorridos(Long.valueOf(vX.get(5).toString()));
                
                if(vX.get(6)!=null){
                    tm=tm.valueOf(vX.get(6).toString());
                    xx.setFechaMantenimiento(tm);
                }
                
                if(vX.get(7)!=null) xx.setComponente1Id(Long.valueOf(vX.get(7).toString()));
                if(vX.get(8)!=null) xx.setComponente2Id(Long.valueOf(vX.get(8).toString()));
                if(vX.get(9)!=null) xx.setComponente3Id(Long.valueOf(vX.get(9).toString()));
                
                if(vX.get(10)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(10).toString());
                    xx.setFechaDesde(tm);
                }
                
                if(vX.get(11)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(11).toString());
                    xx.setFechaHasta(tm);
                }
                
                if(vX.get(12)!=null) xx.setKmsAsignacion(Long.valueOf(vX.get(12).toString()));
                if(vX.get(13)!=null) xx.setKmsUltimaLectura(Long.valueOf(vX.get(13).toString()));
                
                if(vX.get(14)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(14).toString());
                    xx.setFechaUltimaLectura(tm);
                }
                
                if(vX.get(15)!=null) xx.setKmsUltimoReset(Long.valueOf(vX.get(15).toString()));
                
                if(vX.get(16)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(16).toString());
                    xx.setFechaUltimoMp(tm);
                }
                
                if(vX.get(17)!=null) xx.setEmpresaId(Long.valueOf(vX.get(17).toString()));
                if(vX.get(18)!=null) xx.setAdicional1(vX.get(18).toString());
                if(vX.get(19)!=null) xx.setAdicional2(vX.get(19).toString());
                if(vX.get(20)!=null) xx.setAdicional3(vX.get(20).toString());
                if(vX.get(21)!=null) xx.setAdicional4(vX.get(21).toString());
                if(vX.get(22)!=null) xx.setAdicional5(vX.get(22).toString());
                if(vX.get(23)!=null) xx.setAdicional6(vX.get(23).toString());
                if(vX.get(24)!=null) xx.setAdicional7(vX.get(24).toString());
                if(vX.get(25)!=null) xx.setAdicional8(vX.get(25).toString());
                if(vX.get(26)!=null) xx.setAdicional9(vX.get(26).toString());
                if(vX.get(27)!=null) xx.setAdicional10(vX.get(27).toString());
                if(vX.get(28)!=null) xx.setCreadoPor(Long.valueOf(vX.get(28).toString()));
                
                if(vX.get(29)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(29).toString());
                    xx.setFechaCreacion(tm);
                }
                
                if(vX.get(30)!=null) xx.setUltimaActualizacionPor(Long.valueOf(vX.get(30).toString()));
                
                if(vX.get(31)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(31).toString());
                    xx.setUltimaFechaActualizacion(tm);
                }
                
                if(vX.get(32)!=null) xx.setReplicacionEstado(vX.get(32).toString());
                if(vX.get(33)!=null) xx.setReplicacionIntentos(Long.valueOf(vX.get(33).toString()));
                if(vX.get(34)!=null) xx.setReplicacionOrigen(vX.get(34).toString());
                
                x.add(xx);
            }
            
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<TmsOperadoresTbl> queryTmsOperadoresTblByClave(String clave, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        List<TmsOperadoresTbl> x=null;
        String Consulta = 
                "SELECT O.* FROM TMS_OPERADORES_TBL"+DBLink+" O "+
                "WHERE O.CLAVE_OPERADOR LIKE '"+clave+"' "+
                "AND   O.ACCION3_ID<>(SELECT ACCION_ID FROM TMS_ACCIONES_TBL WHERE ACCION='INCIDENCIA') "+
                "ORDER BY TO_NUMBER(O.CLAVE_OPERADOR)";
        try{
            //System.out.println(Consulta);
            Vector vVector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vVector==null || vVector.size()==0) return null;
            x = new ArrayList<TmsOperadoresTbl>();
            TmsOperadoresTbl xx;
            Vector vX;
            Timestamp tm;
            for(int i=0; i<vVector.size(); i++){
                tm = null;
                vX = (Vector) vVector.get(i);
                xx = new TmsOperadoresTbl();
                
                if(vX.get(0)!=null) xx.setOperadorId(Long.valueOf(vX.get(0).toString()));
                if(vX.get(1)!=null) xx.setClaveOperador(vX.get(1).toString());
                if(vX.get(2)!=null) xx.setTipoOperadorId(Long.valueOf(vX.get(2).toString()));
                if(vX.get(3)!=null) xx.setJornadaServicioId(Long.valueOf(vX.get(3).toString()));
                if(vX.get(4)!=null) xx.setDiaActivo(Long.valueOf(vX.get(4).toString()));
                if(vX.get(5)!=null) xx.setAccion1Id(Long.valueOf(vX.get(5).toString()));
                if(vX.get(6)!=null) xx.setAccion2Id(Long.valueOf(vX.get(6).toString()));
                if(vX.get(7)!=null) xx.setAccion3Id(Long.valueOf(vX.get(7).toString()));
                if(vX.get(8)!=null) xx.setAccion4Id(Long.valueOf(vX.get(8).toString()));
                if(vX.get(9)!=null) xx.setHabilitado(vX.get(9).toString());
                if(vX.get(10)!=null) xx.setOperadorIdFijo(Long.valueOf(vX.get(10).toString()));
                if(vX.get(11)!=null) xx.setOperadorNombre(vX.get(11).toString());
                if(vX.get(12)!=null) xx.setOperadorNombreMedio(vX.get(12).toString());
                if(vX.get(13)!=null) xx.setOperadorApellido(vX.get(13).toString());
                if(vX.get(14)!=null) xx.setOperadorNombreCompleto(vX.get(14).toString());
                
                if(vX.get(15)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(15).toString());
                    xx.setFechaAlta(tm);
                }
                
                if(vX.get(16)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(16).toString());
                    xx.setFechaBaja(tm);
                }
                
                if(vX.get(17)!=null) xx.setAplicaRetencion(vX.get(17).toString());
                if(vX.get(18)!=null) xx.setAdicional1(vX.get(18).toString());
                if(vX.get(19)!=null) xx.setAdicional2(vX.get(19).toString());
                if(vX.get(20)!=null) xx.setAdicional3(vX.get(20).toString());
                if(vX.get(21)!=null) xx.setAdicional4(vX.get(21).toString());
                if(vX.get(22)!=null) xx.setAdicional5(vX.get(22).toString());
                if(vX.get(23)!=null) xx.setEmpresaId(Long.valueOf(vX.get(23).toString()));
                if(vX.get(24)!=null) xx.setTipoServicio(vX.get(24).toString());
                if(vX.get(25)!=null) xx.setCreadoPor(Long.valueOf(vX.get(25).toString()));
                
                if(vX.get(26)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(26).toString());
                    xx.setFechaCreacion(tm);
                }
                
                if(vX.get(27)!=null) xx.setUltimaActualizacionPor(Long.valueOf(vX.get(27).toString()));
                
                if(vX.get(28)!=null){
                    tm = null;
                    tm=tm.valueOf(vX.get(28).toString());
                    xx.setUltimaFechaActualizacion(tm);
                }
                
                if(vX.get(29)!=null) xx.setReplicacionEstado(vX.get(29).toString());
                if(vX.get(30)!=null) xx.setReplicacionIntentos(Long.valueOf(vX.get(30).toString()));
                if(vX.get(31)!=null) xx.setReplicacionOrigen(vX.get(31).toString());
                
                x.add(xx);
            }
            
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }    
     
    public List<TmsServiciosTbl> queryTmsServiciosTblAll(){
        return (List<TmsServiciosTbl>)em.createNamedQuery("TmsServiciosTbl.findAll").getResultList();
    }
    
    public List<TmsEstadosCorridaTbl> queryTmsEstadosCorridaTblAll(){
        return (List<TmsEstadosCorridaTbl>)em.createNamedQuery("TmsEstadosCorridaTbl.findAll").getResultList();
    }
    
    public List<TmsAccionesTbl> queryTmsAccionesTblAll(){
        return (List<TmsAccionesTbl>)em.createNamedQuery("TmsAccionesTbl.findAll").getResultList();
    }
    
    public List<TmsEstadosTbl> queryTmsEstadosTblAll(){
        return (List<TmsEstadosTbl>)em.createNamedQuery("TmsEstadosTbl.findAll").getResultList();
    }

    public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'dd/MM/yyyy HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            nre.printStackTrace();
            return null;
        }
    }
    
    public Object fechaServidorX(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd hh:mm:ss') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            nre.printStackTrace();
            return null;
        }
    }
    
    // TRANSACCIONES
    public boolean modificaCorrida(long corridaId, long estadoCorridaId, String letraEstadoCorrida, Timestamp FechaHora, String Hora, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta0 =
                    "SELECT CLAVE_CORRIDA " +
                    "FROM TMS_CORRIDAS_TBL"+DBLink+" "+
                    "WHERE CORRIDA_ID = "+corridaId;
        
        String Consulta = "";

        String Consulta2 = "";
        try{
            //System.out.println(Consulta);
            Vector z = (Vector)em.createNativeQuery(Consulta0).getResultList();
            if(z==null || z.size()==0) return false;
            Vector y = (Vector) z.get(0);
            String claveCorrida = y.get(0).toString();
            claveCorrida = claveCorrida.substring(0,5)+Hora+claveCorrida.substring(10);
            Consulta ="UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET CLAVE_CORRIDA='"+claveCorrida+"', FECHA_HORA_CORRIDA=TO_TIMESTAMP('"+FechaHora+"','YYYY-MM-DD HH24:MI:SS.FF'), ESTADO_CORRIDA_ID="+estadoCorridaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID = "+corridaId;
            Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET CLAVE_CORRIDA='"+claveCorrida+"', FECHA_HORA_CORRIDA=TO_TIMESTAMP('"+FechaHora+"','YYYY-MM-DD HH24:MI:SS.FF'), ESTADO_CORRIDA='"+letraEstadoCorrida+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE CORRIDA_ID = "+corridaId;
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return false;
            //System.out.println("C");
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return false;
        }
    }
    
    public int[] SustituyeAutobusUnaCorrida(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                            long autobusOrigId, String numeroEconomicoOrig, String FechaHora,
                                            long plantillaId, long empresaId, String empresa, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET AUTOBUS_ID="+autobusSustId/*+", AUTOBUS_ORIGINAL_ID="+autobusSustId*/+", EMPRESA_ID ="+empresaId+", PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE AUTOBUS_ORIGINAL_ID = "+autobusOrigId+" "+
                    "AND FECHA_HORA_CORRIDA = TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI')";
        String Consulta2 =
                    "DECLARE dummy NUMBER; BEGIN " +     
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" " +
                    "WHERE CORRIDA_ID="+corridaId+" "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET AUTOBUS='"+numeroEconomicoSust/*+"', AUTOBUS_ORIGINAL='"+numeroEconomicoSust*/+"', EMPRESA='"+empresa+"', PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE AUTOBUS_ORIGINAL = "+numeroEconomicoOrig+" "+
                    "AND FECHA_HORA_CORRIDA = TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI'); COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            int[] regAct = new int[2];
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")){
                int[] regAct = new int[2];
                regAct[0]=-1; regAct[1]=-1;
                return regAct;
            }
            return null;
        }
    }
    
    public int[] SustituyeAutobusUnaCorridaNull(long corridaId, long autobusSustId, String numeroEconomicoSust, String FechaHora,
                                            long plantillaId, long empresaId, String empresa, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    //"SET AUTOBUS_ID="+autobusSustId+", AUTOBUS_ORIGINAL_ID="+autobusSustId+", PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "SET AUTOBUS_ID="+autobusSustId+", PLANTILLA_ID ="+plantillaId+", EMPRESA_ID="+empresaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID = "+corridaId;
        String Consulta2 =
                    "DECLARE dummy NUMBER; BEGIN " +
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" " +
                    "WHERE CORRIDA_ID="+corridaId+" "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    //"SET AUTOBUS='"+numeroEconomicoSust+"', AUTOBUS_ORIGINAL='"+numeroEconomicoSust+"', PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "SET AUTOBUS='"+numeroEconomicoSust+"', EMPRESA='"+empresa+"', PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE CLAVE_CORRIDA = (SELECT CC.CLAVE_CORRIDA FROM TMS_CORRIDAS_TBL"+DBLink+" CC WHERE CC.CORRIDA_ID="+corridaId+"); COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            int[] regAct = new int[2];
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")){
                int[] regAct = new int[2];
                regAct[0]=-1; regAct[1]=-1;
                return regAct;
            }
            return null;
        }
    }
    
    public int[] SustituyeAutobusTodoElDia(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String Fecha,
                                            long plantillaId, long empresaId, String empresa, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET AUTOBUS_ID="+autobusSustId+", AUTOBUS_ORIGINAL_ID="+autobusSustId+", EMPRESA_ID="+empresaId+", PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE AUTOBUS_ORIGINAL_ID = "+autobusOrigId+" "+
                    "AND FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+Fecha+"','DD/MM/YYYY HH24:MI') AND TO_DATE('"+Fecha.substring(0,10)+" 23:59','DD/MM/YYYY HH24:MI')";
        String Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET AUTOBUS='"+numeroEconomicoSust+"', AUTOBUS_ORIGINAL='"+numeroEconomicoSust+"', EMPRESA='"+empresa+"', PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE AUTOBUS_ORIGINAL = '"+numeroEconomicoOrig+"' "+
                    "AND FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+Fecha+"','DD/MM/YYYY HH24:MI') AND TO_DATE('"+Fecha.substring(0,10)+" 23:59','DD/MM/YYYY HH24:MI')";
        try{
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            //System.out.println("C");
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public int[] SustituyeAutobusTodoElRol(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String FechaHora,
                                            long plantillaId, long empresaId, String empresa, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET AUTOBUS_ID="+autobusSustId+", AUTOBUS_ORIGINAL_ID="+autobusSustId+", EMPRESA_ID="+empresaId+", PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE AUTOBUS_ORIGINAL_ID = "+autobusOrigId+" "+
                    "AND FECHA_HORA_CORRIDA >= TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI')";
        String Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET AUTOBUS='"+numeroEconomicoSust+"', AUTOBUS_ORIGINAL='"+numeroEconomicoSust+"', EMPRESA='"+empresa+"', PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE AUTOBUS_ORIGINAL = '"+numeroEconomicoOrig+"' "+
                    "AND FECHA_HORA_CORRIDA >= TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI')";
        try{
            //System.out.println("C");
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            //System.out.println("C");
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
        
    public boolean registraFueraRolAutobusOrig(long autobusOrigId, 
            long fueraRolId, long ubicacionId, long causaId, String esquema, String DBLink){
        
        /*TmsAutobusesXTbl TmsAutobusesXTbl = em.find(TmsAutobusesXTbl.class, autobusOrigId);
        if(TmsAutobusesXTbl == null) return false;
        em.refresh(TmsAutobusesXTbl);
        TmsAutobusesXTbl.setComponente1Id(fueraRolId);
        TmsAutobusesXTbl.setComponente2Id(ubicacionId);
        TmsAutobusesXTbl.setComponente3Id(causaId);
        TmsAutobusesXTbl.setReplicacionOrigen(esquema);
        return true;*/
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_AUTOBUSES_TBL"+DBLink+" "+
                    "SET COMPONENTE_1_ID="+fueraRolId+", COMPONENTE_2_ID="+ubicacionId+", COMPONENTE_3_ID="+causaId+", REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE AUTOBUS_ID = "+autobusOrigId;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return false;
        }
    }
    
    public boolean registraEnRolAutobusSust(long autobusSustId, 
            long rolId, long ubicacionId, long estanciaId, String esquema, String DBLink){
        /*TmsAutobusesXTbl TmsAutobusesXTbl = em.find(TmsAutobusesXTbl.class, autobusSustId);
        if(TmsAutobusesXTbl == null) return false;
        em.refresh(TmsAutobusesXTbl);
        TmsAutobusesXTbl.setComponente1Id(rolId);
        TmsAutobusesXTbl.setComponente2Id(ubicacionId);
        TmsAutobusesXTbl.setComponente3Id(estanciaId);
        TmsAutobusesXTbl.setReplicacionOrigen(esquema);
        return true;*/
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_AUTOBUSES_TBL"+DBLink+" "+
                    "SET COMPONENTE_1_ID="+rolId+", COMPONENTE_2_ID="+ubicacionId+", COMPONENTE_3_ID="+estanciaId+", REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE AUTOBUS_ID = "+autobusSustId;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return false;
        }
    }
    
    public List<Object[]> buscarOpcionesSustOperador(String operadorOrigId, String ubicacion, String DBLink){
        /*String Consulta =
              "(SELECT "+
                "POST.OPERADOR_ID, POST.CLAVE_OPERADOR, POST.OPERADOR_NOMBRE_COMPLETO, "+
                "'POSTURERO FIJO:::' || UBI.ACCION "+
                "FROM "+
                "TMS_OPERADORES_TBL OPER "+
                ",TMS_OPERADORES_TBL POST "+
                ",TMS_ACCIONES_TBL UBI "+
                "WHERE OPER.OPERADOR_ID = NVL('"+operadorOrigId+"', OPER.OPERADOR_ID) "+
                "AND	  OPER.OPERADOR_ID_FIJO = POST.OPERADOR_ID "+
                "AND	  POST.ACCION1_ID = (SELECT EDO.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL EDO WHERE EDO.ACCION = 'ENROLADO') "+
                "AND	  POST.ACCION2_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL AC WHERE AC.ACCION='"+ubicacion+"') "+
		"AND	  POST.ACCION2_ID = UBI.ACCION_ID "+
                "AND	  POST.ACCION3_ID = (SELECT ACT.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL ACT WHERE ACT.ACCION = 'GUARDIA') "+
                "AND	  POST.ACCION4_ID <> (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL AC WHERE AC.ACCION='INCIDENCIA') "+
                ") "+
                "UNION "+
                "(SELECT "+
                "GUA.OPERADOR_ID, GUA.CLAVE_OPERADOR, GUA.OPERADOR_NOMBRE_COMPLETO, "+
                "'GUARDIA:::' || UBI.ACCION "+
                "FROM "+
                "TMS_OPERADORES_TBL GUA "+
                ",TMS_ACCIONES_TBL UBI "+
                "WHERE GUA.ACCION1_ID = (SELECT EDO.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL EDO WHERE EDO.ACCION = 'ENROLADO') "+
                "AND	  GUA.ACCION2_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL AC WHERE AC.ACCION='"+ubicacion+"') "+
				"AND	  GUA.ACCION2_ID = UBI.ACCION_ID "+
                "AND	  GUA.ACCION3_ID = (SELECT ACT.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL ACT WHERE ACT.ACCION = 'GUARDIA') "+
                "AND	  GUA.ACCION4_ID <> (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL AC WHERE AC.ACCION='INCIDENCIA') "+
                "AND   GUA.OPERADOR_ID NOT IN ( "+
                "SELECT "+
                "POST.OPERADOR_ID "+
                "FROM "+
                "TMS_OPERADORES_TBL OPER "+
                ",TMS_OPERADORES_TBL POST "+
                "WHERE OPER.OPERADOR_ID = NVL('"+operadorOrigId+"', OPER.OPERADOR_ID) "+
                "AND	  OPER.OPERADOR_ID_FIJO = POST.OPERADOR_ID "+
                ") "+
                ") "+
                "ORDER BY 4 DESC";*/
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                "SELECT * " +
                "FROM ( " +
                "(SELECT "+
                "POST.OPERADOR_ID OPERADOR_ID, POST.CLAVE_OPERADOR CLAVE_OPERADOR, POST.OPERADOR_NOMBRE_COMPLETO OPERADOR_NOMBRE_COMPLETO, "+
                "('1. POSTURERO FIJO:::' || UBI.ACCION) ESTADO "+
                "FROM "+
                "TMS_OPERADORES_TBL"+DBLink+" OPER "+
                ",TMS_OPERADORES_TBL"+DBLink+" POST "+
                ",TMS_ACCIONES_TBL"+DBLink+" UBI "+
                "WHERE OPER.OPERADOR_ID = NVL('"+operadorOrigId+"', OPER.OPERADOR_ID) "+
                "AND   OPER.OPERADOR_ID_FIJO = POST.OPERADOR_ID "+
                "AND   POST.ACCION1_ID = (SELECT EDO.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL"+DBLink+" EDO WHERE EDO.ACCION = 'ENROLADO') "+
                "AND   POST.ACCION2_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL"+DBLink+" AC WHERE AC.ACCION = '"+ubicacion+"') "+
                "AND   POST.ACCION2_ID = UBI.ACCION_ID "+
                "AND   POST.ACCION3_ID = (SELECT ACT.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL"+DBLink+" ACT WHERE ACT.ACCION = 'GUARDIA') "+
                ") "+
                "UNION "+
                "(SELECT "+
                "GUA.OPERADOR_ID OPERADOR_ID, GUA.CLAVE_OPERADOR CLAVE_OPERADOR, GUA.OPERADOR_NOMBRE_COMPLETO OPERADOR_NOMBRE_COMPLETO, "+
                "('2. GUARDIA:::' || UBI.ACCION) ESTADO "+
                "FROM "+
                "TMS_OPERADORES_TBL"+DBLink+" GUA "+
                ",TMS_ACCIONES_TBL"+DBLink+" UBI "+
                "WHERE GUA.ACCION1_ID = (SELECT EDO.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL"+DBLink+" EDO WHERE EDO.ACCION = 'ENROLADO') "+
                "AND  GUA.ACCION2_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL"+DBLink+" AC WHERE AC.ACCION = '"+ubicacion+"') "+
		"AND  GUA.ACCION2_ID = UBI.ACCION_ID "+
                "AND  GUA.ACCION3_ID = (SELECT ACT.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL"+DBLink+" ACT WHERE ACT.ACCION = 'GUARDIA') "+
                "AND  GUA.OPERADOR_ID NOT IN ( "+
                "SELECT "+
                "POST.OPERADOR_ID "+
                "FROM "+
                "TMS_OPERADORES_TBL"+DBLink+" OPER "+
                ",TMS_OPERADORES_TBL"+DBLink+" POST "+
                "WHERE OPER.OPERADOR_ID = NVL('"+operadorOrigId+"', OPER.OPERADOR_ID) "+
                "AND   OPER.OPERADOR_ID_FIJO = POST.OPERADOR_ID "+
                ") "+
                "UNION "+
                "(SELECT "+
                "GUA.OPERADOR_ID OPERADOR_ID, GUA.CLAVE_OPERADOR CLAVE_OPERADOR, GUA.OPERADOR_NOMBRE_COMPLETO OPERADOR_NOMBRE_COMPLETO, "+
                "('3. ESTANCIA:::' || UBI.ACCION) ESTADO "+
                "FROM "+
                "TMS_OPERADORES_TBL"+DBLink+" GUA "+
                ",TMS_ACCIONES_TBL"+DBLink+" UBI "+
                "WHERE GUA.ACCION1_ID = (SELECT EDO.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL"+DBLink+" EDO WHERE EDO.ACCION = 'ENROLADO') "+
                "AND  GUA.ACCION2_ID = (SELECT AC.ACCION_ID FROM TMS_ACCIONES_TBL"+DBLink+" AC WHERE AC.ACCION = '"+ubicacion+"') "+
                "AND  GUA.ACCION2_ID = UBI.ACCION_ID "+
                "AND  GUA.ACCION3_ID = (SELECT ACT.ACCION_ID FROM "+
                                                                 "TMS_ACCIONES_TBL"+DBLink+" ACT WHERE ACT.ACCION = 'ESTANCIA') "+
                "AND  GUA.OPERADOR_ID NOT IN ( "+
                "SELECT "+
                "POST.OPERADOR_ID "+
                "FROM "+
                "TMS_OPERADORES_TBL"+DBLink+" OPER "+
                ",TMS_OPERADORES_TBL"+DBLink+" POST "+
                "WHERE OPER.OPERADOR_ID = NVL('"+operadorOrigId+"', OPER.OPERADOR_ID) "+
                "AND   OPER.OPERADOR_ID_FIJO = POST.OPERADOR_ID "+
                ") "+
                ") "+
                ") " +
                ") T1 "+
                "ORDER BY T1.ESTADO, TO_NUMBER(T1.CLAVE_OPERADOR)";
        try{
            //System.out.println(Consulta);
            return em.createNativeQuery(Consulta).getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public int[] SustituyeOperadorUnaCorridaNull(long corridaId, long autobusSustId, String numeroEconomicoSust, String FechaHora, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    //"SET OPERADOR_ID="+autobusSustId+", OPERADOR_ORIGINAL_ID="+autobusSustId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "SET OPERADOR_ID="+autobusSustId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID = "+corridaId;
        String Consulta2 =
                    "DECLARE dummy NUMBER; BEGIN " +     
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" " +
                    "WHERE CORRIDA_ID="+corridaId+" "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    //"SET OPERADOR='"+numeroEconomicoSust+"', OPERADOR_ORIGINAL='"+numeroEconomicoSust+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "SET OPERADOR='"+numeroEconomicoSust+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE CLAVE_CORRIDA = (SELECT CC.CLAVE_CORRIDA FROM TMS_CORRIDAS_TBL"+DBLink+" CC WHERE CC.CORRIDA_ID="+corridaId+"); COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            int[] regAct = new int[2];
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")){
                int[] regAct = new int[2];
                regAct[0]=-1; regAct[1]=-1;
                return regAct;
            }
            return null;
        }
    }
    
    public int[] SustituyeOperadorUnaCorrida(long corridaId, long operadorSustId, String numeroEconomicoSust,
                                              long operadorOrigId, String numeroEconomicoOrig, String FechaHora, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET OPERADOR_ID="+operadorSustId/*+", OPERADOR_ORIGINAL_ID="+autobusSustId*/+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    //"WHERE OPERADOR_ORIGINAL_ID = "+operadorOrigId+" "+
                    "WHERE OPERADOR_ID = "+operadorOrigId+" "+
                    "AND FECHA_HORA_CORRIDA = TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI')";
        String Consulta2 =
                    "DECLARE dummy NUMBER; BEGIN " +     
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" " +
                    "WHERE CORRIDA_ID="+corridaId+" "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET OPERADOR='"+numeroEconomicoSust/*+"', OPERADOR_ORIGINAL='"+numeroEconomicoSust*/+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    //"WHERE OPERADOR_ORIGINAL = "+numeroEconomicoOrig+" "+
                    "WHERE OPERADOR = "+numeroEconomicoOrig+" "+
                    "AND FECHA_HORA_CORRIDA = TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI'); COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            int[] regAct = new int[2];
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")){
                int[] regAct = new int[2];
                regAct[0]=-1; regAct[1]=-1;
                return regAct;
            }
            return null;
        }
    }
    
    public int[] SustituyeOperadorTodoElDia(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String Fecha, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET OPERADOR_ID="+autobusSustId+", OPERADOR_ORIGINAL_ID="+autobusSustId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE OPERADOR_ID = "+autobusOrigId+" "+
                    "AND FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+Fecha+"','DD/MM/YYYY HH24:MI') AND TO_DATE('"+Fecha.substring(0,10)+" 23:59','DD/MM/YYYY HH24:MI')";
        String Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET OPERADOR='"+numeroEconomicoSust+"', OPERADOR_ORIGINAL='"+numeroEconomicoSust+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE OPERADOR = '"+numeroEconomicoOrig+"' "+
                    "AND FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+Fecha+"','DD/MM/YYYY HH24:MI') AND TO_DATE('"+Fecha.substring(0,10)+" 23:59','DD/MM/YYYY HH24:MI')";
        try{
            //System.out.println("C");
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            //System.out.println("C");
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public int[] SustituyeOperadorTodoElRol(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String FechaHora, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET OPERADOR_ID="+autobusSustId+", OPERADOR_ORIGINAL_ID="+autobusSustId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE OPERADOR_ID = "+autobusOrigId+" "+
                    "AND A.FECHA_HORA_CORRIDA >= TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI')";
        String Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET OPERADOR='"+numeroEconomicoSust+"', OPERADOR_ORIGINAL='"+numeroEconomicoSust+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE OPERADOR = '"+numeroEconomicoOrig+"' "+
                    "AND FECHA_HORA_CORRIDA >= TO_DATE('"+FechaHora+"','DD/MM/YYYY HH24:MI')";
        try{
            //System.out.println("C");
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            //System.out.println("C");
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
        
    public boolean registraFueraRolOperadorOrig(long autobusOrigId, 
            long fueraRolId, long ubicacionId, long causaId, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_OPERADORES_TBL"+DBLink+" "+
                    "SET ACCION1_ID="+fueraRolId+", ACCION2_ID="+ubicacionId+", ACCION3_ID="+causaId+", REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE OPERADOR_ID = "+autobusOrigId;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return false;
        }
    }
    
    public boolean registraEnRolOperadorSust(long autobusSustId, 
            long rolId, long ubicacionId, long estanciaId, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_OPERADORES_TBL"+DBLink+" "+
                    "SET ACCION1_ID="+rolId+", ACCION2_ID="+ubicacionId+", ACCION3_ID="+estanciaId+", REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE OPERADOR_ID = "+autobusSustId;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
      
            return false;
        }
    }
    
    // corridas extras
        public String[] registrarCorridaExtra(String prefijoTerminalId, TmsCorridasTbl corrida, String nombreTerminal, String letraServicio, String strHora, String esquema, String Origen, String DBLink){
        String s[] = new String[2]; // -1 sin corrida venta
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = "INSERT INTO TMS_CORRIDAS_TBL"+DBLink+" "+
                            "VALUES ('"+prefijoTerminalId+"' || TMS_CORRIDAS_SEQ.NEXTVAL"+DBLink+", '"+Origen+"' || '"+letraServicio+"' || '"+strHora+"' || '"+corrida.getTipoCorrida()+"' || TMS_CORRIDAS_SEQ.CURRVAL"+DBLink+", "+
                            corrida.getServicioId()+", "+corrida.getEmpresaId()+", "+corrida.getRutaId()+", TO_TIMESTAMP('"+corrida.getFechaHoraCorrida()+"','YYYY-MM-DD HH24:MI:SS.FF'), "+corrida.getOrigenId()+", "+
                            corrida.getDestinoId()+", "+corrida.getAutobusId()+", "+corrida.getAutobusOriginalId()+", "+corrida.getOperadorId()+", "+corrida.getOperadorOriginalId()+", "+
                            "'"+corrida.getTipoCorrida()+"', "+corrida.getEstadoCorridaId()+", "+corrida.getPlantillaId()+", "+corrida.getCorridaRelacionadaId()+", "+corrida.getSueldoOperador()+", "+
                            "'"+corrida.getNumeroContrato()+"', '"+corrida.getNumeroOrden()+"', "+corrida.getMontoAnticipos()+", "+corrida.getCreadoPor()+", SYSDATE, "+
                            corrida.getUltimaActualizacionPor()+", SYSDATE, " + //TO_TIMESTAMP('"+corrida.getUltimaFechaActualizacion()+"','YYYY-MM-DD HH24:MI:SS.FF')
                            (corrida.getAdicional1()==null?"NULL":"'"+corrida.getAdicional1()+"'")+", " +
                            (corrida.getAdicional2()==null?"NULL":"'"+corrida.getAdicional2()+"'")+", " +
                            (corrida.getAdicional3()==null?"NULL":"'"+corrida.getAdicional3()+"'")+", " +
                            (corrida.getAdicional4()==null?"NULL":"'"+corrida.getAdicional4()+"'")+", " +
                            (corrida.getAdicional5()==null?"NULL":"'"+corrida.getAdicional5()+"'")+", " +
                            (corrida.getAdicional6()==null?"NULL":"'"+corrida.getAdicional6()+"'")+", " +
                            (corrida.getAdicional7()==null?"NULL":"'"+corrida.getAdicional7()+"'")+", " +
                            (corrida.getAdicional8()==null?"NULL":"'"+corrida.getAdicional8()+"'")+", " +
                            (corrida.getAdicional9()==null?"NULL":"'"+corrida.getAdicional9()+"'")+", " +
                            "'-9', NULL, NULL, " +
                            "'"+esquema+"' "+
                            ")";
        try{
            //System.out.println(Consulta);
            //System.out.println("Insert registrarCorridaExtra: "+Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return null;
            Consulta = "SELECT T1.CORRIDA_ID, T1.CLAVE_CORRIDA FROM ( " +
                       "SELECT CORRIDA_ID, CLAVE_CORRIDA, FECHA_CREACION FROM TMS_CORRIDAS_TBL"+DBLink+" WHERE ADICIONAL10 = '-9' ORDER BY CORRIDA_ID DESC " +
                       ") T1 " +
                       "WHERE ROWNUM=1";
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            //System.out.println("qry registrarCorridaExtra:"+Consulta);
            //System.out.println("res registrarCorridaExtra:"+x);
            if(x==null || x.size()==0){
                s[0] = "-1"; s[1] = Origen+letraServicio+strHora+"E";
                return s;
            }
            Vector y = (Vector) x.get(0);
            s[0] = y.get(0).toString(); s[1] = y.get(1).toString();
            return s;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    /*public String[] registrarCorridaExtra(String prefijoTerminalId, TmsCorridasTbl corrida, String nombreTerminal, String letraServicio, String strHora, String esquema, String Origen){
        String s[] = new String[2];
        try{
            corrida.setReplicacionOrigen(esquema);
            em.persist(corrida);
            s[0] = String.valueOf(corrida.getCorridaId());  
            corrida.setCorridaId(Long.valueOf(prefijoTerminalId+corrida.getCorridaId()));
            corrida.setClaveCorrida(Origen+letraServicio+strHora+"E"+s[0]);
            s[1] = corrida.getClaveCorrida();
            
            return s;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }*/
    
    /*public boolean registrarCorridaExtraVenta(TmsCorridasVentaTbl corridaVenta, String esquema){
        try{
            //System.out.println("Tipo Corrida "+corridaVenta.getTipoCorrida());
            corridaVenta.setMenoresCorrida((long)0);
            corridaVenta.setReplicacionOrigen(esquema);
            em.persist(corridaVenta);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }*/
    
        public String[] registrarCorridaNormal(String prefijoTerminalId, TmsCorridasTbl corrida, String nombreTerminal, String letraServicio, String strHora, String esquema, String Origen, String DBLink){
        String s[] = new String[2]; // -1 sin corrida venta
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = "INSERT INTO TMS_CORRIDAS_TBL"+DBLink+" "+
                            "VALUES ('"+prefijoTerminalId+"' || TMS_CORRIDAS_SEQ.NEXTVAL"+DBLink+", '"+Origen+"' || '"+letraServicio+"' || '"+strHora+"' || '"+corrida.getTipoCorrida()+"' || TMS_CORRIDAS_SEQ.CURRVAL"+DBLink+", "+
                            corrida.getServicioId()+", "+corrida.getEmpresaId()+", "+corrida.getRutaId()+", TO_TIMESTAMP('"+corrida.getFechaHoraCorrida()+"','YYYY-MM-DD HH24:MI:SS.FF'), "+corrida.getOrigenId()+", "+
                            corrida.getDestinoId()+", "+corrida.getAutobusId()+", "+corrida.getAutobusOriginalId()+", "+corrida.getOperadorId()+", "+corrida.getOperadorOriginalId()+", "+
                            "'"+corrida.getTipoCorrida()+"', "+corrida.getEstadoCorridaId()+", "+corrida.getPlantillaId()+", "+corrida.getCorridaRelacionadaId()+", "+corrida.getSueldoOperador()+", "+
                            "'"+corrida.getNumeroContrato()+"', '"+corrida.getNumeroOrden()+"', "+corrida.getMontoAnticipos()+", "+corrida.getCreadoPor()+", SYSDATE, "+
                            corrida.getUltimaActualizacionPor()+", SYSDATE, " + //TO_TIMESTAMP('"+corrida.getUltimaFechaActualizacion()+"','YYYY-MM-DD HH24:MI:SS.FF')
                            (corrida.getAdicional1()==null?"NULL":"'"+corrida.getAdicional1()+"'")+", " +
                            (corrida.getAdicional2()==null?"NULL":"'"+corrida.getAdicional2()+"'")+", " +
                            (corrida.getAdicional3()==null?"NULL":"'"+corrida.getAdicional3()+"'")+", " +
                            (corrida.getAdicional4()==null?"NULL":"'"+corrida.getAdicional4()+"'")+", " +
                            (corrida.getAdicional5()==null?"NULL":"'"+corrida.getAdicional5()+"'")+", " +
                            (corrida.getAdicional6()==null?"NULL":"'"+corrida.getAdicional6()+"'")+", " +
                            (corrida.getAdicional7()==null?"NULL":"'"+corrida.getAdicional7()+"'")+", " +
                            (corrida.getAdicional8()==null?"NULL":"'"+corrida.getAdicional8()+"'")+", " +
                            (corrida.getAdicional9()==null?"NULL":"'"+corrida.getAdicional9()+"'")+", " +
                            "'-9', 'P', NULL, " +
                            "'"+esquema+"' "+
                            ")";
        try{
            //System.out.println("Qry INSERT INTO TMS_CORRIDAS_TBL: "+Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return null;
            Consulta = "SELECT T1.CORRIDA_ID, T1.CLAVE_CORRIDA FROM ( " +
                       "SELECT CORRIDA_ID, CLAVE_CORRIDA, FECHA_CREACION FROM TMS_CORRIDAS_TBL"+DBLink+" WHERE ADICIONAL10 = '-9' ORDER BY CORRIDA_ID DESC " +
                       ") T1 " +
                       "WHERE ROWNUM=1";
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0){
                s[0] = "-1"; s[1] = Origen+letraServicio+strHora+"N";
                return s;
            }
            Vector y = (Vector) x.get(0);
            s[0] = y.get(0).toString(); s[1] = y.get(1).toString();
            return s;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }


    }
        
    public boolean registrarCorridaExtraVenta(TmsCorridasVentaTbl corridaVenta, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = "INSERT INTO TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                            "VALUES ("+corridaVenta.getCorridaId()+", '"+corridaVenta.getClaveCorrida()+"', '"+corridaVenta.getEmpresa()+"', '"+
                            corridaVenta.getServicio()+"', TO_TIMESTAMP('"+corridaVenta.getFechaHoraCorrida()+"','YYYY-MM-DD HH24:MI:SS.FF'), '"+corridaVenta.getOrigen()+"', '"+corridaVenta.getDestino()+"', "+
                            (corridaVenta.getAutobus()==null ? "NULL" : "'"+corridaVenta.getAutobus()+"'") + ", " +
                            (corridaVenta.getOperador()==null ? "NULL" : "'"+corridaVenta.getOperador()+"'") + ", " +
                            (corridaVenta.getOperadorAdicional()==null ? "NULL" : "'"+corridaVenta.getOperadorAdicional()+"'") + ", " +
                            "'"+corridaVenta.getTipoCorrida()+"', '"+corridaVenta.getEstadoCorrida()+"', " +
                            (corridaVenta.getAutobusOriginal()==null ? "NULL" : "'"+corridaVenta.getAutobusOriginal()+"'") + ", " +
                            (corridaVenta.getOperadorOriginal()==null ? "NULL" : "'"+corridaVenta.getOperadorOriginal()+"'") + ", " +
                            (corridaVenta.getCorridaRelacionadaId()==null ? "NULL" : "'"+corridaVenta.getCorridaRelacionadaId()+"'") + ", " +
                            "0, "+corridaVenta.getSenectudCorrida()+", "+corridaVenta.getEstudiantesCorrida()+", "+
                            corridaVenta.getProfesoresCorrida()+", "+corridaVenta.getCortesiasCorrida()+", "+corridaVenta.getPlantillaId()+", '"+
                            corridaVenta.getAsiento1()+"', '"+corridaVenta.getAsiento2()+"', '"+corridaVenta.getAsiento3()+"', '"+corridaVenta.getAsiento4()+"', '"+corridaVenta.getAsiento5()+"', '"+corridaVenta.getAsiento6()+"', '"+corridaVenta.getAsiento7()+"', '"+corridaVenta.getAsiento8()+"', '"+corridaVenta.getAsiento9()+"', '"+corridaVenta.getAsiento10()+"', '"+
                            corridaVenta.getAsiento11()+"', '"+corridaVenta.getAsiento12()+"', '"+corridaVenta.getAsiento13()+"', '"+corridaVenta.getAsiento14()+"', '"+corridaVenta.getAsiento15()+"', '"+corridaVenta.getAsiento16()+"', '"+corridaVenta.getAsiento17()+"', '"+corridaVenta.getAsiento18()+"', '"+corridaVenta.getAsiento19()+"', '"+corridaVenta.getAsiento20()+"', '"+
                            corridaVenta.getAsiento21()+"', '"+corridaVenta.getAsiento22()+"', '"+corridaVenta.getAsiento23()+"', '"+corridaVenta.getAsiento24()+"', '"+corridaVenta.getAsiento25()+"', '"+corridaVenta.getAsiento26()+"', '"+corridaVenta.getAsiento27()+"', '"+corridaVenta.getAsiento28()+"', '"+corridaVenta.getAsiento29()+"', '"+corridaVenta.getAsiento30()+"', '"+
                            corridaVenta.getAsiento31()+"', '"+corridaVenta.getAsiento32()+"', '"+corridaVenta.getAsiento33()+"', '"+corridaVenta.getAsiento34()+"', '"+corridaVenta.getAsiento35()+"', '"+corridaVenta.getAsiento36()+"', '"+corridaVenta.getAsiento37()+"', '"+corridaVenta.getAsiento38()+"', '"+corridaVenta.getAsiento39()+"', '"+corridaVenta.getAsiento40()+"', '"+
                            corridaVenta.getAsiento41()+"', '"+corridaVenta.getAsiento42()+"', '"+corridaVenta.getAsiento43()+"', '"+corridaVenta.getAsiento44()+"', '"+corridaVenta.getAsiento45()+"', '"+corridaVenta.getAsiento46()+"', '"+corridaVenta.getAsiento47()+"', '"+corridaVenta.getAsiento48()+"', '"+corridaVenta.getAsiento49()+"', '"+corridaVenta.getAsiento50()+"', '"+
                            corridaVenta.getAsiento51()+"', '"+corridaVenta.getAsiento52()+"', '"+corridaVenta.getAsiento53()+"', '"+corridaVenta.getAsiento54()+"', '"+corridaVenta.getAsiento55()+"', '"+corridaVenta.getAsiento56()+"', '"+corridaVenta.getAsiento57()+"', '"+corridaVenta.getAsiento58()+"', '"+corridaVenta.getAsiento59()+"', '"+corridaVenta.getAsiento60()+"', '"+
                            corridaVenta.getAsiento61()+"', '"+corridaVenta.getAsiento62()+"', '"+corridaVenta.getAsiento63()+"', '"+corridaVenta.getAsiento64()+"', '"+corridaVenta.getAsiento65()+"', '"+corridaVenta.getAsiento66()+"', '"+corridaVenta.getAsiento67()+"', '"+corridaVenta.getAsiento68()+"', '"+corridaVenta.getAsiento69()+"', '"+corridaVenta.getAsiento70()+"', '"+
                            corridaVenta.getAsiento71()+"', '"+corridaVenta.getAsiento72()+"', '"+corridaVenta.getAsiento73()+"', '"+corridaVenta.getAsiento74()+"', '"+corridaVenta.getAsiento75()+"', '"+corridaVenta.getAsiento76()+"', '"+corridaVenta.getAsiento77()+"', '"+corridaVenta.getAsiento78()+"', '"+corridaVenta.getAsiento79()+"', '"+corridaVenta.getAsiento80()+"', '"+
                            corridaVenta.getAsiento81()+"', '"+corridaVenta.getAsiento82()+"', '"+corridaVenta.getAsiento83()+"', '"+corridaVenta.getAsiento84()+"', '"+corridaVenta.getAsiento85()+"', "+
                            (corridaVenta.getAdicional1()==null?"NULL":"'"+corridaVenta.getAdicional1()+"'")+", " +
                            (corridaVenta.getAdicional2()==null?"NULL":"'"+corridaVenta.getAdicional2()+"'")+", " +
                            (corridaVenta.getAdicional3()==null?"NULL":"'"+corridaVenta.getAdicional3()+"'")+", " +
                            (corridaVenta.getAdicional4()==null?"NULL":"'"+corridaVenta.getAdicional4()+"'")+", " +
                            (corridaVenta.getAdicional5()==null?"NULL":"'"+corridaVenta.getAdicional5()+"'")+", " +
                            (corridaVenta.getAdicional6()==null?"NULL":"'"+corridaVenta.getAdicional6()+"'")+", " +
                            (corridaVenta.getAdicional7()==null?"NULL":"'"+corridaVenta.getAdicional7()+"'")+", " +
                            (corridaVenta.getAdicional8()==null?"NULL":"'"+corridaVenta.getAdicional8()+"'")+", " +
                            (corridaVenta.getAdicional9()==null?"NULL":"'"+corridaVenta.getAdicional9()+"'")+", " +
                            (corridaVenta.getAdicional10()==null?"NULL":"'"+corridaVenta.getAdicional10()+"'")+", " +
                            "'P', NULL, '"+esquema+"' "+
                            ")";
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    /*public boolean actualizaClaveCorridaExtra(String prefijoTerminalId, long corridaId, String claveCorrida){
        try{
            TmsCorridasTbl x = em.find(TmsCorridasTbl.class, corridaId);
            if(x==null) return false;
            em.refresh(x);
            System.out.println("CORRIDA EXTRA"+prefijoTerminalId+""+corridaId+"  -  "+claveCorrida);
            x.setCorridaId(Long.valueOf(prefijoTerminalId+corridaId));
            x.setClaveCorrida(claveCorrida);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }*/
    
    public List<TmsRutasTbl> queryTmsRutasTblAll(){
        return (List<TmsRutasTbl>)em.createNamedQuery("TmsRutasTbl.findAll").getResultList();
    }
    
    public List<TmsRutasV> queryTmsRutasV(String esquemaPropio){
        List<TmsRutasV> x= new ArrayList<TmsRutasV>();
                String Consulta = 
                    "SELECT "+
                    "RUTA.RUTA_ID VISTA_ID "+
                    ",RUTA.RUTA_ID "+
                    ",RUTA.RUTA_NUMERO "+
                    ",RUTA.RUTA_NOMBRE "+
                    ",RUTA.TRAMO_ORIGEN_ID ORIGEN_ID "+
                    ",ORIG.ESTADO_NOMBRE ORIGEN "+
                    ",SER.SERVICIO_ID "+
                    ",SER.SERVICIO_CLAVE "+
                    ",SER.SERVICIO_NOMBRE SERVICIO "+
                    ",(SER.SERVICIO_CLAVE || '-' || SER.SERVICIO_NOMBRE) LETRA "+
                    ",RUTA.TRAMO_DESTINO_ID DESTINO_ID "+
                    ",DEST.ESTADO_NOMBRE DESTINO "+
                    ",(SELECT TRAMO.TRAMO_VTA_REGRESO FROM TMS_TRAMOS_TBL TRAMO WHERE TRAMO.RUTA_ID = RUTA.RUTA_ID AND ROWNUM=1) "+
                    "FROM TMS_RUTAS_TBL RUTA "+
                    ",TMS_SERVICIOS_TBL SER "+
                    ",TMS_ESTADOS_TBL ORIG "+
                    ",TMS_ESTADOS_TBL DEST "+
                    ",TMS_BASE_DATOS_CONFIG_TBL BDC "+
                    "WHERE RUTA.SERVICIO_ID = SER.SERVICIO_ID "+
                    "AND   RUTA.TRAMO_ORIGEN_ID = ORIG.ESTADO_ID "+
                    "AND   RUTA.TRAMO_DESTINO_ID = DEST.ESTADO_ID "+
                    "AND   RUTA.TRAMO_ORIGEN_ID = DECODE(BDC.NOMBRE_TERMINAL,'CENTRAL',RUTA.TRAMO_ORIGEN_ID,BDC.TERMINAL_ID) "+
                    "AND   BDC.NOMBRE_ESQUEMA = '"+esquemaPropio+"' "+
                    "ORDER BY 2, 13";
        try{
            System.out.println(Consulta);
            Vector z = (Vector)em.createNativeQuery(Consulta).getResultList();
            if(z==null || z.size()==0) return null;
            Vector y = null;
            TmsRutasV rutaV = null;
            for(int i=0; i<z.size(); i++){
                y = new Vector();
                y=(Vector) z.get(i);
                rutaV = new TmsRutasV();
                
                System.out.println("Z:"+z.get(i));
                
                rutaV.setVistaId(Long.valueOf(y.get(0).toString()));
                rutaV.setRutaId(Long.valueOf(y.get(1).toString()));
                rutaV.setRutaNumero(y.get(2).toString());
                rutaV.setRutaNombre(y.get(3).toString());
                rutaV.setOrigenId(Long.valueOf(y.get(4).toString()));
                rutaV.setOrigen(y.get(5).toString());
                rutaV.setServicioId(Long.valueOf(y.get(6).toString()));
                rutaV.setServicioClave(y.get(7).toString());
                rutaV.setServicio(y.get(8).toString());
                rutaV.setLetra(y.get(9).toString());
                rutaV.setDestinoId(Long.valueOf(y.get(10).toString()));
                rutaV.setDestino(y.get(11).toString());
                rutaV.setTramoVtaRegreso(y.get(12).toString());
                
                x.add(rutaV);
            }
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    // Abrir/Cerrar Horarios
    
    public int corridasAProcesarVenta(String ServicioId, String Fecha1, String Fecha2, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta1 =  "UPDATE TMS_CORRIDAS_VENTA_TBL"+DBLink+" VENTA "+
                            "SET VENTA.ESTADO_CORRIDA = 'P', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                            "WHERE VENTA.CLAVE_CORRIDA IN "+
                            "( "+
                                    "SELECT COR.CLAVE_CORRIDA "+
                                    "FROM TMS_CORRIDAS_TBL"+DBLink+" COR, TMS_CORRIDAS_VENTA_TBL"+DBLink+" CORV "+
                                    "WHERE COR.SERVICIO_ID = NVL('"+ServicioId+"', COR.SERVICIO_ID) " +
                                    "AND  COR.FECHA_HORA_CORRIDA BETWEEN TO_DATE('"+Fecha1+"','DD/MM/YYYY HH24:MI') AND TO_DATE('"+Fecha2+"','DD/MM/YYYY HH24:MI') "+
                                    "AND  COR.CLAVE_CORRIDA = CORV.CLAVE_CORRIDA  "+
                                    "AND  CORV.ESTADO_CORRIDA = 'A' "+
                                    "AND  CORV.CLAVE_CORRIDA NOT IN (SELECT DECODE(BOL.CLAVE_CORRIDA,NULL,'SINCLAVE',BOL.CLAVE_CORRIDA) FROM TMS_BOLETOS_VENTA_TBL BOL) "+
                                    "AND  COR.CORRIDA_ID NOT IN (SELECT DECODE(RVN.CORRIDA_ID ,NULL,'SINCLAVE', RVN.CORRIDA_ID) FROM TMS_RESERVACIONES_TBL RVN) "+
                            ")";
        try{
            //System.out.println(Consulta1);
            if(em.createNativeQuery(Consulta1).executeUpdate()==0) return 0;
            return 1;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int corridasAProcesarNormal(String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta1 = "UPDATE TMS_CORRIDAS_TBL"+DBLink+" VENTA "+
                            "SET VENTA.ESTADO_CORRIDA_ID = (SELECT ESTADO_CORRIDA_ID FROM TMS_ESTADOS_CORRIDA_TBL WHERE NOMBRE_CORTO_ESTADO='P'), ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                            "WHERE VENTA.CLAVE_CORRIDA IN "+
                            "( "+
                                    "SELECT COR.CLAVE_CORRIDA "+
                                    "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" COR "+
                                    "WHERE COR.ESTADO_CORRIDA = 'P' "+
                            ")";
        try{
            //System.out.println("C");
            if(em.createNativeQuery(Consulta1).executeUpdate()==0) return 0;
            return 1;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public List<Object[]> corridasAProcesar(String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta1 = "SELECT COR.CORRIDA_ID, CORV.CLAVE_CORRIDA, CORV.TIPO_CORRIDA, "+
                            "CORV.ESTADO_CORRIDA, CORV.SERVICIO, CORV.EMPRESA, CORV.ORIGEN, CORV.DESTINO, CORV.FECHA_HORA_CORRIDA "+
                            "FROM TMS_CORRIDAS_TBL"+DBLink+" COR, TMS_CORRIDAS_VENTA_TBL"+DBLink+" CORV "+
                            "WHERE CORV.ESTADO_CORRIDA = 'P' "+
                            "AND CORV.CLAVE_CORRIDA = COR.CLAVE_CORRIDA "+
                            "ORDER BY CORV.FECHA_HORA_CORRIDA";
        try{
            //System.out.println("C");
            List<Object[]> x = em.createNativeQuery(Consulta1).getResultList();
            if(x.size()==0) return null;
            return x;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public int liberarCorridasVenta(String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta1 = "UPDATE TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                "SET ESTADO_CORRIDA = 'A', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                "WHERE ESTADO_CORRIDA = 'P'";
        try{
            //System.out.println("C");
            if(em.createNativeQuery(Consulta1).executeUpdate()==0) return 0;
            return 1;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int liberarCorridas(String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta1 = "UPDATE TMS_CORRIDAS_TBL"+DBLink+" "+
                "SET ESTADO_CORRIDA_ID = (SELECT ESTADO_CORRIDA_ID FROM TMS_ESTADOS_CORRIDA_TBL"+DBLink+" WHERE NOMBRE_CORTO_ESTADO = 'A'), ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                "WHERE ESTADO_CORRIDA_ID = (SELECT ESTADO_CORRIDA_ID FROM TMS_ESTADOS_CORRIDA_TBL"+DBLink+" WHERE NOMBRE_CORTO_ESTADO = 'P')";
        try{
            //System.out.println("C");
            if(em.createNativeQuery(Consulta1).executeUpdate()==0) return 0;
            return 1;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    // AGRUPACION
    
    public int TransfiereBoletosABporClave(String ClaveCorrida, String ClaveCorridaNueva){
        String Consulta1 = "UPDATE TMS_BOLETOS_VENTA_TBL BOL "+
                           "SET BOL.CLAVE_CORRIDA = '"+ClaveCorridaNueva+"' "+
                           "WHERE BOL.CLAVE_CORRIDA = '"+ClaveCorrida+"'";
        try{
            //System.out.println("C");
            if(em.createNativeQuery(Consulta1).executeUpdate()==0) return 0;
            return 0;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int TransfiereBoletosABporId(long CorridaId, long CorridaNuevaId){
        String Consulta1 = "UPDATE TMS_RESERVACIONES_TBL RVN "+
                           "SET RVN.CORRIDA_ID = "+CorridaNuevaId+" "+
                           "WHERE RVN.CORRIDA_ID = "+CorridaId+"";
        try{
            //System.out.println("C");
            if(em.createNativeQuery(Consulta1).executeUpdate()==0) return 0;
            return 0;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public String[] estadoAutobusActualSiguiente(String autobus, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
        "SELECT "+
        "BUS.AUTOBUS_ID "+
        ",BUS.NUMERO_ECONOMICO "+
        ",EDO.ESTADO_NOMBRE   ESTADO_NOMBRE "+
        ",UBI.ESTADO_NOMBRE  UBICACION_NOMBRE "+
        ",DECODE(ACT.ESTADO_NOMBRE,'CORRIDA',NVL( "+
                "(SELECT * "+
                "FROM (SELECT CC.ORIGEN || '-' || CC.DESTINO || ' ' || TO_CHAR(CC.FECHA_HORA_CORRIDA,'DD/MM/YYYY HH24:MI') COL1 "+
                      "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" CC WHERE CC.AUTOBUS='"+autobus+"' AND CC.FECHA_HORA_CORRIDA>=SYSDATE "+
                          "ORDER BY CC.FECHA_HORA_CORRIDA) "+
                "WHERE ROWNUM=1 "+
        "),ACT.ESTADO_NOMBRE),ACT.ESTADO_NOMBRE)  ASIGNACION_ACTUAL "+
        ",DECODE(ACT.ESTADO_NOMBRE,'CORRIDA',NVL( "+
                "(SELECT * "+
                "FROM (SELECT CC.ORIGEN || '-' || CC.DESTINO || ' ' || TO_CHAR(CC.FECHA_HORA_CORRIDA,'DD/MM/YYYY HH24:MI') COL2 "+
                      "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" CC WHERE CC.AUTOBUS='"+autobus+"' AND CC.FECHA_HORA_CORRIDA>=SYSDATE "+
                          "ORDER BY CC.FECHA_HORA_CORRIDA) "+
                "WHERE ROWNUM=2 "+
        "),ACT.ESTADO_NOMBRE),ACT.ESTADO_NOMBRE)  ASIGNACION_SIGUIENTE "+
        "FROM "+
        "TMS_AUTOBUSES_TBL"+DBLink+" BUS "+
        ",TMS_ESTADOS_TBL"+DBLink+" EDO "+
        ",TMS_ESTADOS_TBL"+DBLink+" UBI "+
        ",TMS_ESTADOS_TBL"+DBLink+" ACT "+
        "WHERE BUS.NUMERO_ECONOMICO = '"+autobus+"' "+
        "AND   BUS.COMPONENTE_1_ID = EDO.ESTADO_ID "+
        "AND   BUS.COMPONENTE_2_ID = UBI.ESTADO_ID "+
        "AND   BUS.COMPONENTE_3_ID = ACT.ESTADO_ID";
        try{
            //System.out.println("C");
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Vector y = new Vector();
            y=(Vector) x.get(0);
            String[] objetos = new String[6];
            objetos[0] = y.get(0).toString();
            objetos[1] = y.get(1).toString();
            objetos[2] = y.get(2).toString();
            objetos[3] = y.get(3).toString();
            objetos[4] = y.get(4).toString();
            objetos[5] = y.get(5).toString();
            return objetos;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public String[] estadoOperadorActualSiguiente(String operador, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
        "SELECT "+
        "OPER.OPERADOR_ID "+
        ",OPER.CLAVE_OPERADOR "+
        ",EDO.ACCION   ESTADO_NOMBRE "+
        ",UBI.ACCION   UBICACION_NOMBRE "+
        ",DECODE(ACT.ACCION,'CORRIDA',NVL( "+
                "(SELECT * "+
                "FROM (SELECT CC.ORIGEN || '-' || CC.DESTINO || ' ' || TO_CHAR(CC.FECHA_HORA_CORRIDA,'DD/MM/YYYY HH24:MI') COL1 "+
                      "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" CC WHERE CC.OPERADOR='"+operador+"' AND CC.FECHA_HORA_CORRIDA>=SYSDATE "+
                          "ORDER BY CC.FECHA_HORA_CORRIDA) "+
                "WHERE ROWNUM=1 "+
        "),ACT.ACCION),ACT.ACCION)  ASIGNACION_ACTUAL "+
        ",DECODE(ACT.ACCION,'CORRIDA',NVL( "+
                "(SELECT * "+
                "FROM (SELECT CC.ORIGEN || '-' || CC.DESTINO || ' ' || TO_CHAR(CC.FECHA_HORA_CORRIDA,'DD/MM/YYYY HH24:MI') COL2 "+
                      "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" CC WHERE CC.OPERADOR='"+operador+"' AND CC.FECHA_HORA_CORRIDA>=SYSDATE "+
                          "ORDER BY CC.FECHA_HORA_CORRIDA) "+
                "WHERE ROWNUM=2 "+
        "),ACT.ACCION),ACT.ACCION)  ASIGNACION_SIGUIENTE "+
        "FROM "+
        "TMS_OPERADORES_TBL"+DBLink+" OPER "+
        ",TMS_ACCIONES_TBL"+DBLink+" EDO "+
        ",TMS_ACCIONES_TBL"+DBLink+" UBI "+
        ",TMS_ACCIONES_TBL"+DBLink+" ACT "+
        "WHERE OPER.CLAVE_OPERADOR = '"+operador+"' "+
        "AND	  OPER.ACCION1_ID = EDO.ACCION_ID "+
        "AND   OPER.ACCION2_ID = UBI.ACCION_ID "+
        "AND   OPER.ACCION3_ID = ACT.ACCION_ID";
        try{
            //System.out.println("C");
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Vector y = new Vector();
            y=(Vector) x.get(0);
            String[] objetos = new String[6];
            objetos[0] = y.get(0).toString();
            objetos[1] = y.get(1).toString();
            objetos[2] = y.get(2).toString();
            objetos[3] = y.get(3).toString();
            objetos[4] = y.get(4).toString();
            objetos[5] = y.get(5).toString();
            return objetos;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public long[] obtienePlantilla(String rutaNumero){
        String Consulta = 
                "SELECT PLANTILLA.PLANTILLA_ENC_ID, PLANTILLA.CAPACIDAD_ASIENTOS "+
                "FROM TMS_PARAMETROS_CONFIG_TBL PARAM "+
                ",TMS_RUTA_PARAMETROS_TBL   PARAMRUTA "+
                ",TMS_RUTAS_TBL   RUTAS "+
                ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL PLANTILLA "+
                "WHERE PARAM.PARAMETRO_CODIGO = 'P_PLANTBUSPRED' "+
                "AND   PARAMRUTA.PARAMETRO_CONFIG_ID = PARAM.PARAMETRO_CONFIG_ID "+
                "AND   RUTAS.RUTA_NUMERO = '"+rutaNumero+"' "+
                "AND   PARAMRUTA.RUTA_ID = RUTAS.RUTA_ID "+
                "AND   PLANTILLA.NOMBRE_CORTO = PARAMRUTA.PARAMETRO_VALOR "+
                "ORDER BY 2";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            long[] z = new long[2];
            if(x==null || x.size()==0){
                z[0]=-1;
                z[1]=-1;
                return z;
            }
            Vector y = (Vector) x.get(0);
            z[0]=Long.valueOf(y.get(0).toString());
            z[1]=Long.valueOf(y.get(1).toString());
            return z;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public long obtieneAutobusPlantilla(String numEco, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                "SELECT BUS.PLANTILLA_ENC_ID "+
                "FROM TMS_AUTOBUSES_TBL"+DBLink+" BUS "+
                "WHERE BUS.NUMERO_ECONOMICO = '"+numEco+"'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Long.valueOf(y.get(0).toString());
        }catch(Exception ex){
            return -5;
        }
    }
    
    public long obtienePlantillaCorrida(long corridaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                "SELECT CVTA.PLANTILLA_ID FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" CVTA "+
                "WHERE CVTA.CORRIDA_ID = "+corridaId;
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Long.valueOf(y.get(0).toString());
        }catch(Exception ex){
            return -1;
        }
    }
    
    public int tarjViajeEstaRecaudada(long corridaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
        "SELECT NVL( "+
        "(SELECT 'N' "+
        "FROM TMS_TARJETAS_VIAJE_TBL"+DBLink+" TVJ "+
        "WHERE TVJ.CORRIDA_ID = "+corridaId+" "+
        "AND   TVJ.ESTADO_TARJETA_ID = ( "+
            "SELECT ETJ.ESTADO_TARJETA_VIAJE_ID FROM TMS_ESTADOS_TARJETA_VIAJE_TBL ETJ "+
            "WHERE ETJ.NOMBRE_ESTADO = 'RECAUDADA' "+
            ") "+
        "), 'S') PASA FROM DUAL";
        try{
            //System.out.println("C");
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            if(y.get(0).toString().equals("S")) return 1;
            return 0;
        }catch(Exception ex){
            return -1;
        }
    }
    
    public String obtenerLigaCentral(){
        String Consulta = 
        "SELECT BDC.NOMBRE_DBLINK "+
        "FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
        "WHERE BDC.NOMBRE_ESQUEMA = 'XERTMS'";
        try{
            //System.out.println("C");
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            return y.get(0).toString();
        }catch(Exception ex){
            return null;
        }
    }
    
    // TARJETA VIAJE
    public TmsTarjetasViajeTbl buscaTarjetaPorFolio(String folio, BigInteger edo){
        TmsTarjetasViajeTbl tarjeta = null;
        try {
            tarjeta = (TmsTarjetasViajeTbl)em.createNamedQuery("TmsTarjetasViajeTbl.findByFolioTarjeta").setParameter("folioTarjeta",folio).setParameter("estadoTarjetaId",edo).getSingleResult();
        } catch(Exception ex){
        }
        if(tarjeta!=null)
            em.refresh(tarjeta);
        return tarjeta;
    }
    
    public Object buscaCorrida(long idCor){
        return em.createNativeQuery("select cor.CLAVE_CORRIDA, cor.ORIGEN, cor.DESTINO, to_char(cor.FECHA_HORA_CORRIDA,'dd/mm/yyyy'), to_char(cor.FECHA_HORA_CORRIDA,'HH:MI') from tms_corridas_venta_tbl cor where cor.corrida_id = "+idCor).getResultList();
    }
    
    public Object buscaEstadoTajeta(String  edo){
        return em.createNativeQuery("select tar.ESTADO_TARJETA_VIAJE_ID from tms_estados_tarjeta_viaje_tbl tar where tar.NOMBRE_ESTADO = '"+edo+"'").getResultList();
    }   
     
    public Object queryBuscaNombreEsquema(){
        return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
    }  
   
    public void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
    }
    
    public boolean AsignaOperadorACorrida(long corridaId, long autobusSustId, String numeroEconomicoSust, long usuarioId, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET OPERADOR_ID="+autobusSustId+", OPERADOR_ORIGINAL_ID="+autobusSustId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_ACTUALIZACION_POR = "+usuarioId+", ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    //"SET OPERADOR_ORIGINAL_ID="+autobusSustId+", ADICIONAL4 = 'PSD', ULTIMA_ACTUALIZACION_POR = "+usuarioId+" "+
                    "WHERE CORRIDA_ID = "+corridaId;
        String Consulta2 =
                    "DECLARE dummy NUMBER; BEGIN " +     
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" " +
                    "WHERE CORRIDA_ID="+corridaId+" "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET OPERADOR='"+numeroEconomicoSust+"', OPERADOR_ORIGINAL='"+numeroEconomicoSust+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    //"SET OPERADOR_ORIGINAL='"+numeroEconomicoSust+"', ADICIONAL4 = 'PSD' " +
                    "WHERE CLAVE_CORRIDA = (SELECT CC.CLAVE_CORRIDA FROM TMS_CORRIDAS_TBL"+DBLink+" CC WHERE CC.CORRIDA_ID="+corridaId+"); COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            if(em.createNativeQuery(Consulta2).executeUpdate()==0) return false;
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")) return false;
            return false;
        }
    }
    
    public boolean AsignaAutobusACorrida(long corridaId, long autobusSustId, String numeroEconomicoSust, long plantillaId,
                                       long usuarioId, String esquema, String DBLink, long empresaId, String empresa){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET AUTOBUS_ID="+autobusSustId+", AUTOBUS_ORIGINAL_ID="+autobusSustId+", PLANTILLA_ID ="+plantillaId+", EMPRESA_ID="+empresaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_ACTUALIZACION_POR = "+usuarioId+", ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    //"SET AUTOBUS_ORIGINAL_ID="+autobusSustId+", PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', ULTIMA_ACTUALIZACION_POR = "+usuarioId+" "+
                    "WHERE CORRIDA_ID = "+corridaId;
        String Consulta2 =
                    "DECLARE dummy NUMBER; BEGIN " +     
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" " +
                    "WHERE CORRIDA_ID="+corridaId+" "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET AUTOBUS='"+numeroEconomicoSust+"', AUTOBUS_ORIGINAL='"+numeroEconomicoSust+"', PLANTILLA_ID ="+plantillaId+", EMPRESA='"+empresa+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    //"SET AUTOBUS_ORIGINAL='"+numeroEconomicoSust+"', PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD' "+
                    "WHERE CLAVE_CORRIDA = (SELECT CC.CLAVE_CORRIDA FROM TMS_CORRIDAS_TBL"+DBLink+" CC WHERE CC.CORRIDA_ID="+corridaId+"); COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            if(em.createNativeQuery(Consulta2).executeUpdate()==0) return false;
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")) return false;
            return false;
        }
    }
    
    public List<TmsOperadoresTbl> queryOperadores(){
        return (List<TmsOperadoresTbl>)em.createNamedQuery("TmsOperadoresTbl.findAll").getResultList();
    }
    
    public long UsuarioConFuncion(String funcionNumero, String usuarioNumero){
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
                " AND tmsus.UBICACION_ID = (select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S')";
        
        try{
            Object resultado = em.createNativeQuery(consultaUsuario).getSingleResult();
            if(resultado==null) return -1;
            String valor = resultado.toString().replace("[","");
            valor = valor.toString().replace("]","");
            return Long.valueOf(valor);
        }catch(NoResultException ex){
            return -1;
        }
    }
    
    public long esUsuarioSupervisorConFuncion(String funcionNumero, String usuarioNumero, String pwdEnc){
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
            if(resultado==null) return -1;
            String valor = resultado.toString().replace("[","");
            valor = valor.toString().replace("]","");
            return Long.valueOf(valor);
        }catch(NoResultException ex){
            return -1;
        }
    }
    
    public String estadoCorrida(String claveCorrida, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
        "SELECT CVT.ESTADO_CORRIDA FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" CVT "+
        "WHERE CVT.CLAVE_CORRIDA = '"+claveCorrida+"'";
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
    
    public int ocupacionCorrida(long corridaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                        "SELECT ( "+
                        "(SELECT COUNT(*) FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" BOL "+
						"WHERE (BOL.CLAVE_CORRIDA = CORV.CLAVE_CORRIDA "+
						"OR BOL.CLAVE_CORRIDA = (SELECT XZ.CLAVE_CORRIDA "+
							"FROM TMS_CORRIDAS_TBL"+DBLink+" XZ "+
							"WHERE XZ.CORRIDA_RELACIONADA_ID = CORV.CORRIDA_ID)) "+
                        "AND BOL.TIPO_OPERACION IN ('VT', 'HO', 'AC','FT','FO','FC') "+
                        "AND BOL.BOLETO_ID NOT IN "+
                        "(SELECT X.BOLETO_RELACIONADO_ID FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" X WHERE X.BOLETO_RELACIONADO_ID IS NOT NULL) "+
                        ") + "+
                        "(SELECT COUNT(*) FROM TMS_RESERVACIONES_TBL"+DBLink+" RVN WHERE (RVN.CORRIDA_ID = CORV.CORRIDA_ID "+
						"OR RVN.CORRIDA_ID = (SELECT XZ.CORRIDA_ID "+
											"FROM TMS_CORRIDAS_TBL"+DBLink+" XZ "+
											"WHERE XZ.CORRIDA_RELACIONADA_ID = CORV.CORRIDA_ID)) "+
						"AND RVN.ESTADO_RESERVACION ='RESERVADA') "+
                        ") OCUPACION "+
                        "FROM TMS_CORRIDAS_TBL"+DBLink+" CORV " +
                        ",TMS_ESTADOS_CORRIDA_TBL"+DBLink+" Est "+
                        "WHERE CORV.CORRIDA_ID = "+corridaId+" " +
                        "AND CORV.ESTADO_CORRIDA_ID = Est.ESTADO_CORRIDA_ID "+
                        "AND Est.NOMBRE_CORTO_ESTADO = 'A'";
        try{
            //System.out.println(Consulta); arreglar query
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x==null || x.size()==0) return -1;
            return Integer.valueOf(x.get(0).toString());
        }catch(Exception ex){
            //ex.printStackTrace();
            return -1;
        }
    }
    
    public int capacidadAsientos(long corridaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                        "SELECT Pla.CAPACIDAD_ASIENTOS "+
                        "FROM TMS_CORRIDAS_TBL"+DBLink+" Cor "+
                        ",TMS_AUTOBUSES_TBL"+DBLink+" Aut "+
                        ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL"+DBLink+" Pla "+
                        "WHERE Cor.CORRIDA_ID = "+corridaId+" " +
                        "AND Cor.AUTOBUS_ID = Aut.AUTOBUS_ID(+) "+
                        "AND NVL(Aut.PLANTILLA_ENC_ID,Cor.PLANTILLA_ID) = Pla.PLANTILLA_ENC_ID";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x==null || x.size()==0) return -1;
            return Integer.valueOf(x.get(0).toString());
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int capacidadAsientosAutobus(String numeroEco, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
            "SELECT PLA.CAPACIDAD_ASIENTOS FROM TMS_AUTOBUSES_TBL"+DBLink+" BUS, TMS_AUTOBUS_PLANTILLAS_ENC_TBL"+DBLink+" PLA "+
            "WHERE BUS.NUMERO_ECONOMICO = '"+numeroEco+"' "+
            "AND   PLA.PLANTILLA_ENC_ID = BUS.PLANTILLA_ENC_ID";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Integer.valueOf(y.get(0).toString());
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int MaxAsientoOcupado(String claveCorrida, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
            "SELECT NVL(MAX(ASI.NO_ASIENTO),0) " +
            "FROM TMS_ASIENTOS_NO_DISP_TBL"+DBLink+" ASI "+
            "WHERE ASI.CORRIDA_ID = (SELECT CV.CORRIDA_ID FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" CV WHERE CV.CLAVE_CORRIDA = '"+claveCorrida+"') ";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            return Integer.valueOf(y.get(0).toString());
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public boolean validaHoraModificada(Timestamp hora, long servicioId, long rutaId, long empresaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                "SELECT 1 "+
                "FROM TMS_CORRIDAS_TBL"+DBLink+" A "+
                "WHERE "+ //A.TIPO_CORRIDA = 'E' "+
                //"AND   " +
                "TO_CHAR(A.FECHA_HORA_CORRIDA,'YYYY-MM-DD HH24:MI') = TO_CHAR(TO_TIMESTAMP('"+hora+"','YYYY-MM-DD HH24:MI:SS.FF'), 'YYYY-MM-DD HH24:MI') "+
                "AND   SERVICIO_ID="+servicioId+" "+
                "AND   RUTA_ID="+rutaId+" "+
                "AND   A.EMPRESA_ID = '"+empresaId+"'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return true;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return true;
            return false;
        }catch(NoResultException nrex){
            return false;
        }
    }
    
    public List<TmsBDConfigTbl> queryTmsBaseDatosConfigTblAll(){
        try{
            return em.createNamedQuery("TmsBDConfigTbl.findAll").getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
    public List<TmsEstadoAutobusesV> queryTmsEstadoAutobusesVByNumeroEconomico(String NumeroEconomico){
        List<TmsEstadoAutobusesV> x=em.createNamedQuery("TmsEstadoAutobusesV.findByNumeroEconomico").setParameter("NumeroEconomico",NumeroEconomico).getResultList();
        if(x==null || x.size()==0) return null;
        for(int i=0; i<x.size(); i++) em.refresh(x.get(i));
        return x;
    }
    
    /**********/
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
    
    public boolean registrarEstadoOperador(long operadorId, long estadoId, long ubicacionId, long actividadId,
                                            long usuarioId, Timestamp fechaAct, String esquema){
        try{
            TmsOperadoresTbl x = em.find(TmsOperadoresTbl.class, operadorId);
            if(x==null) return false;
            x.setAccion1Id(estadoId);
            x.setAccion2Id(ubicacionId);
            x.setAccion3Id(actividadId);
            x.setReplicacionOrigen(esquema);
            x.setUltimaActualizacionPor(usuarioId);
            x.setUltimaFechaActualizacion(fechaAct);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public List<TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll() {
        try{
            return em.createNamedQuery("TmsAutobusPlantillasEncTbl.findAll").getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
    public int[] actualizacionesVarias_Plantilla(String loteCorridasId, long plantillaId, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        String Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET PLANTILLA_ID ="+plantillaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        try{
            //System.out.println(Consulta);
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            //System.out.println("C");
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public int[] actualizacionesVarias_Empresa(String loteCorridasId, long empresaId,
            String empresa, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET EMPRESA_ID ="+empresaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        String Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET EMPRESA='"+empresa+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        try{
            //System.out.println(Consulta);
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            //System.out.println("C");
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
 public int actualizacionesVarias_Ruta(String loteCorridasId, long rutaId,
            String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET RUTA_ID ="+rutaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        try{
            System.out.println("Qry actualizacionesVarias_Ruta: "+Consulta);
            int regAct = -1;
            if((regAct=em.createNativeQuery(Consulta).executeUpdate())==0) return -1;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return -1;
        }
    }    
    
    public int[] actualizacionesVarias_LimitesPasaje(String loteCorridasId, String limites, String esquema, String DBLink, 
                                String s, String e, String p, String c){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
         "UPDATE TMS_CORRIDAS_VENTA_TBL"+DBLink+" CV "+
            "SET CV.SENECTUD_CORRIDA = "+s+" - (SELECT COUNT(*) ct FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bv "+
          "WHERE bv.CLAVE_CORRIDA = CV.CLAVE_CORRIDA "+
            "AND bv.TIPO_TRANSACCION = 'L' "+
              "AND bv.TIPO_PASAJERO = 'S' AND bv.TIPO_OPERACION != 'CN' "+
              "AND NOT EXISTS (SELECT 1 FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bvIn "+
                               "WHERE bvIn.BOLETO_RELACIONADO_ID = bv.BOLETO_ID "+
                                        "AND bvIn.TIPO_TRANSACCION = 'L' "+
                                        "AND bvIn.TIPO_OPERACION IN ('CN','HO','AC') "+
                                        "AND bvIn.CLAVE_CORRIDA = bv.CLAVE_CORRIDA) ) "+
               ",CV.PROFESORES_CORRIDA = "+p+" - (SELECT COUNT(*) ct FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bv "+
          "WHERE bv.CLAVE_CORRIDA = CV.CLAVE_CORRIDA "+
            "AND bv.TIPO_TRANSACCION = 'L' "+
              "AND bv.TIPO_PASAJERO = 'P' AND bv.TIPO_OPERACION != 'CN' "+
              "AND NOT EXISTS (SELECT 1 FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bvIn "+
                               "WHERE bvIn.BOLETO_RELACIONADO_ID = bv.BOLETO_ID "+
                                        "AND bvIn.TIPO_TRANSACCION = 'L' "+
                                        "AND bvIn.TIPO_OPERACION IN ('CN','HO','AC') "+
                                        "AND bvIn.CLAVE_CORRIDA = bv.CLAVE_CORRIDA) ) "+
               ",CV.ESTUDIANTES_CORRIDA = "+e+" - (SELECT COUNT(*) ct FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bv "+
          "WHERE bv.CLAVE_CORRIDA = CV.CLAVE_CORRIDA "+
            "AND bv.TIPO_TRANSACCION = 'L' "+
            "AND bv.TIPO_PASAJERO = 'E' AND bv.TIPO_OPERACION != 'CN' "+
              "AND NOT EXISTS (SELECT 1 FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bvIn "+
                               "WHERE bvIn.BOLETO_RELACIONADO_ID = bv.BOLETO_ID "+
                                        "AND bvIn.TIPO_TRANSACCION = 'L' "+
                                        "AND bvIn.TIPO_OPERACION IN ('CN','HO','AC') "+
                                        "AND bvIn.CLAVE_CORRIDA = bv.CLAVE_CORRIDA) ) "+
               ",CV.CORTESIAS_CORRIDA = "+c+" - (SELECT COUNT(*) ct FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bv "+
          "WHERE bv.CLAVE_CORRIDA = CV.CLAVE_CORRIDA "+
            "AND bv.TIPO_TRANSACCION = 'L' "+
              "AND bv.TIPO_PASAJERO = 'C' AND bv.TIPO_OPERACION != 'CN' "+
              "AND NOT EXISTS (SELECT 1 FROM TMS_BOLETOS_VENTA_TBL"+DBLink+" bvIn "+
                               "WHERE bvIn.BOLETO_RELACIONADO_ID = bv.BOLETO_ID "+
                                        "AND bvIn.TIPO_TRANSACCION = 'L' "+
                                        "AND bvIn.TIPO_OPERACION IN ('CN','HO','AC') "+
                                        "AND bvIn.CLAVE_CORRIDA = bv.CLAVE_CORRIDA)) "+
                   ",CV.ADICIONAL2 = '"+limites+"' "+
                 ", CV.ADICIONAL4 = 'PSD', CV.REPLICACION_ORIGEN='"+esquema+"' "+
          "WHERE CV.CORRIDA_ID IN ("+loteCorridasId+")";
        try{
            //System.out.println(Consulta);
            int[] regAct = new int[2];
            regAct[0]=1;
            if((regAct[1]=em.createNativeQuery(Consulta).executeUpdate())==0) return null;
            return regAct;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public String getFlotillaDeAutobus(String numEco, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
            "SELECT FLO.NOMBRE_FLOTILLA "+
            "FROM TMS_AUTOBUSES_TBL"+DBLink+" AU "+
            ",TMS_FLOTILLAS_TBL"+DBLink+" FLO "+
            "WHERE AU.NUMERO_ECONOMICO = "+numEco+" "+
            "AND   AU.FLOTILLA_ID = FLO.FLOTILLA_ID";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return "";
            Vector y = (Vector) x.get(0);
            Object flo = y.get(0);
            if(flo==null) return "";
            return flo.toString();
        }catch(Exception ex){
            return "";
        }
    }
    
    public String obtieneLimitesPasajeCorrida(Long aLong, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
            "SELECT VTA.ADICIONAL2 "+
            "FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" VTA " +
            "WHERE VTA.CORRIDA_ID = "+aLong;
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return "";
            Vector y = (Vector) x.get(0);
            Object flo = y.get(0);
            if(flo==null) return "";
            return flo.toString();
        }catch(Exception ex){
            ex.printStackTrace();
            return "";
        }
    }
    // transacciones por lotes
    public boolean modificaEstadoCorrida(String loteCorridasId, long estadoCorridaId, String letraEstadoCorrida, long relacionada, String claveRelacionada, String esquema, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET ESTADO_CORRIDA_ID="+estadoCorridaId+", ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        if(relacionada!=-1)
        {
            Consulta =
                    "UPDATE " +
                    "TMS_CORRIDAS_TBL"+DBLink+" "+
                    "SET ESTADO_CORRIDA_ID="+estadoCorridaId+", CORRIDA_RELACIONADA_ID ="+relacionada+", ADICIONAL3 ='"+claveRelacionada+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"', ULTIMA_FECHA_ACTUALIZACION = SYSDATE "+
                    "WHERE CORRIDA_ID = "+loteCorridasId;
        }
        String Consulta2 =
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "SET ESTADO_CORRIDA='"+letraEstadoCorrida+"', ADICIONAL4 = 'PSD', REPLICACION_ORIGEN='"+esquema+"' "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        try{
            //System.out.println(Consulta);
            int[] regAct = new int[2];
            if((regAct[0]=em.createNativeQuery(Consulta).executeUpdate())==0) return false;
            //System.out.println(Consulta2);
            if((regAct[1]=em.createNativeQuery(Consulta2).executeUpdate())==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return false;
        }
    }
    
    public boolean borraCorrida(String loteCorridasId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "DELETE FROM TMS_CORRIDAS_TBL"+DBLink+" "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        String Consulta2 =
                    "DELETE FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            //System.out.println(Consulta2);
            if(em.createNativeQuery(Consulta2).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return false;
        }
    }
    
    public boolean borraCorridaCentral(String loteCorridasId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta =
                    "DELETE FROM TMS_CORRIDAS_TBL"+DBLink+" "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        String Consulta2 =
                    "DELETE FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+" "+
                    "WHERE CORRIDA_ID IN ("+loteCorridasId+")";
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            //System.out.println(Consulta2);
            if(em.createNativeQuery(Consulta2).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            return false;
        }
    }
    
    public String getParametrosIniciales2(){
        String Consulta = "SELECT Xer_Tms_Pkg.GETPARAMETROSINICIALES4 PARAMETROS FROM DUAL";
        try{
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }
    }
    
    public String getEp(String ep){
        String Consulta = 
                 "SELECT tmsSp.PARAMETRO_VALOR "+
                 "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar "+ 
                     ",TMS_CAJA_PARAMETROS_TBL tmsSp "+
                 "WHERE tmsSp.CAJA_ID = (SELECT CAJA_ID FROM TMS_CAJAS_TBL WHERE CAJA_NOMBRE='"+ep+"') "+
                   "AND tmsPar.PARAMETRO_CONFIG_ID = tmsSp.PARAMETRO_CONFIG_ID "+
                   "AND tmsPar.PARAMETRO_CODIGO = 'P_VLREMPPRI' "+
                       "AND ROWNUM=1";
        try{
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }
    }
    
    public int esCorridaDePaso(String claveCorrida, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                "SELECT COUNT(DISTINCT(TRAMO.TRAMO_ORIGEN_ID)) VALOR "+
                "FROM TMS_CORRIDAS_TBL"+DBLink+" CORRIDA "+
                     ",TMS_RUTAS_TBL"+DBLink+" RUTA "+
                     ",TMS_TRAMOS_TBL"+DBLink+" TRAMO "+
                     ",TMS_BASE_DATOS_CONFIG_TBL"+DBLink+" B "+
                "WHERE RUTA.RUTA_ID = CORRIDA.RUTA_ID " +
                "AND   TRAMO.RUTA_ID = RUTA.RUTA_ID "+
                "AND   CORRIDA.CLAVE_CORRIDA = '"+claveCorrida+"' "+
                "AND   B.TERMINAL_ID = TRAMO.TRAMO_ORIGEN_ID";
        try{
            //System.out.println(Consulta);
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            if(valor==null) return -1;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return -1;
            return (Integer.valueOf(valor.toString())>1 ? 1 : 0);
        }catch(NoResultException ex){
            return -1;
        }
    }
    
    public String estadoTarjetaViaje(long corridaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
        "SELECT NVL( "+
            "(SELECT ETV.NOMBRE_ESTADO "+
                "FROM TMS_TARJETAS_VIAJE_TBL"+DBLink+" TVJ, "+
                     "TMS_ESTADOS_TARJETA_VIAJE_TBL"+DBLink+" ETV "+ 
            "WHERE TVJ.CORRIDA_ID = "+corridaId+" "+
            "AND   ETV.ESTADO_TARJETA_VIAJE_ID = TVJ.ESTADO_TARJETA_ID), "+
            "'SIN TARJETA') ESTADO_TARJETA "+
        "FROM DUAL";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return "ERROR";
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return "ERROR";
            return y.get(0).toString();
        }catch(Exception ex){
            return "ERROR";
        }
    }
    
    public boolean ServicioEmpresaId(long servicioId, long empresaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                "SELECT COUNT(SE.SERVICIOS_EMPRESAS_ID) VALOR "+
                "FROM TMS_SERVICIOS_EMPRESAS_TBL"+DBLink+" SE "+
                "WHERE SE.SERVICIO_ID = "+servicioId+" "+
                "AND   SE.EMPRESA_ID = "+empresaId;
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return false;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return false;
            return (Integer.valueOf(y.get(0).toString())>0 ? true : false);
        }catch(NoResultException ex){
            return false;
        }
    }
    
    public boolean ServicioEmpresa(String servicio, String empresa, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String Consulta = 
                "SELECT COUNT(SE.SERVICIOS_EMPRESAS_ID) VALOR "+
                "FROM TMS_SERVICIOS_EMPRESAS_TBL"+DBLink+" SE "+
                         ",TMS_SERVICIOS_TBL"+DBLink+" S "+
                         ",TMS_EMPRESAS_TBL"+DBLink+" E "+
                "WHERE S.SERVICIO_NOMBRE = '"+servicio+"' "+
                "AND  E.EMPRESA_NOMBRE = '"+empresa+"' "+
                "AND  SE.SERVICIO_ID = S.SERVICIO_ID "+
                "AND  SE.EMPRESA_ID = E.EMPRESA_ID";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return false;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return false;
            return (Integer.valueOf(y.get(0).toString())>0 ? true : false);
        }catch(NoResultException ex){
            return false;
        }
    }
    
    public Vector ServicioEmpresa(){
        String Consulta = 
                "SELECT S.SERVICIO_NOMBRE, SE.RUTA_ID, E.EMPRESA_NOMBRE "+
                "FROM TMS_SERVICIOS_EMPRESAS_TBL SE "+
                         ",TMS_SERVICIOS_TBL S "+
                         ",TMS_EMPRESAS_TBL E " +
                "WHERE S.SERVICIO_ID = SE.SERVICIO_ID "+
                "AND  E.EMPRESA_ID = SE.EMPRESA_ID";
        try{
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            return x;
        }catch(NoResultException ex){
            return null;
        }
    }

    public TmsCorridasTbl buscaCororida(long corridaId, String DBLink){
        if(!DBLink.equals("")) DBLink = "@" + DBLink;    
        TmsCorridasTbl cor = (TmsCorridasTbl)em.createNativeQuery("SELECT * FROM TMS_CORRIDAS_TBL"+DBLink+"  c where c.CORRIDA_ID = "+corridaId,TmsCorridasTbl.class).getSingleResult();
            return cor;
    }

    public TmsCorridasVentaTbl buscaCororidaVenta(long corridaId, String DBLink){
            if(!DBLink.equals("")) DBLink = "@" + DBLink;    
            TmsCorridasVentaTbl cor = (TmsCorridasVentaTbl)em.createNativeQuery("SELECT * FROM TMS_CORRIDAS_VENTA_TBL"+DBLink+"  c where c.CORRIDA_ID = "+corridaId,TmsCorridasVentaTbl.class).getSingleResult();
            return cor;
    }    


  public TimeZone getTimeZone()
  {
    return TimeZone.getDefault();
  }

  public String getValidaIncicenciaOper(String p_clave_operador, String p_fecha_hora) {
    Connection cnx = null;
    OracleCallableStatement stmt = null;
    try {
      cnx = dataSource.getConnection();
      System.out.println("p_clave_operador: = " + p_clave_operador);
      System.out.println("p_fecha_hora: = " + p_fecha_hora);
      String q1 =
              "BEGIN "
              + "XER_TMS_PKG3.SP_BLOQUEA_DESPACHO_INCIDENCIA(?, ?, ?, ?); "
              + "COMMIT; "
              + "EXCEPTION "
              + "WHEN OTHERS THEN "
              + "ROLLBACK;"
              + "RAISE; "
              + "END;";

      stmt = (OracleCallableStatement) cnx.prepareCall(q1);
      stmt.setString(1, p_clave_operador);
      stmt.setString(2, p_fecha_hora);
      stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
      stmt.registerOutParameter(4, java.sql.Types.NUMERIC);

      stmt.execute();
      long codigo = stmt.getLong(4);
      String mensaje = stmt.getString(3);
      System.out.println("codigo: = " + codigo);
      System.out.println("mensaje: = " + mensaje);
      if (codigo == 0 )
          mensaje = "";
      stmt.close();
      if (cnx != null) {
        cnx.close();
      }
      return mensaje;
    } catch (SQLException ex) {
      System.out.println("ex = " + ex.getMessage());
      try {
        stmt.close();
        if (cnx != null) {
          cnx.close();
        }
      } catch (SQLException ex1) {
          ex1.printStackTrace();
      } finally {
        cnx = null;
        return "" ;
      }
     }
    }


}