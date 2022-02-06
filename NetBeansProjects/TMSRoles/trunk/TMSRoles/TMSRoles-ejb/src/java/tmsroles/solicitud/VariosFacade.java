/*
 * VariosFacade.java
 *
 * Created on 29 de octubre de 2007, 07:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsroles.entidad.TmsCorridasTbl;
import tmsroles.entidad.TmsCorridasVentaTbl;
import tmsroles.entidad.TmsOfertasServicioTbl;
import tmsroles.entidad.TmsRolesBaseLineasTbl;
import tmsroles.entidad.TmsRolesBaseTbl;
import tmsroles.entidad.TmsRolesMaestroTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class VariosFacade implements tmsroles.solicitud.VariosFacadeRemote {

    @PersistenceContext
    private EntityManager em;    
    
    /**
     * Creates a new instance of VariosFacade
     */
    public VariosFacade() {
    }
    
    public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }     
    
    
    public Object buscarOfertasPorServicioId(long servicioId){
        String consulta = "select DISTINCT(ofer.OFERTA_SERVICIO_NOMBRE) from tms_ofertas_servicio_tbl ofer where servicio_id = "+servicioId;
     return em.createNativeQuery(consulta).getResultList();   
    }
    
    
   public Object buscarRutasPorOfertaServicio(long servicioId, String nombreOferta){
        String consulta = "select DISTINCT(ofer.RUTA_ID), rut.RUTA_ID, rut.RUTA_NUMERO,rut.RUTA_NOMBRE,rut.TIEMPO_RECORRIDO, rut.TIEMPO_ESTANCIA " +
                "from tms_ofertas_servicio_tbl  ofer " +
                ",tms_rutas_tbl 			  rut " +
                "where ofer.servicio_id = "+servicioId+" and " +
                "ofer.OFERTA_SERVICIO_NOMBRE = '"+nombreOferta+"' " +
                "and rut.RUTA_ID = ofer.RUTA_ID";
     return em.createNativeQuery(consulta).getResultList();   
    }
   
   public Object buscaHorarios(long idSer, long idRuta, String oferta){
       String consulta="select to_char(ofer.HORA_CORRIDA,'HH24:MI') " +
               ",estori.ESTADO_NOMBRE " +
               ",estdes.ESTADO_NOMBRE " +
               ",empre.EMPRESA_NOMBRE " +
               ",ofer.LUNES_APLICA " +
               ",ofer.MARTES_APLICA " +
               ",ofer.MIERCOLES_APLICA " +
               ",ofer.JUEVES_APLICA " +
               ",ofer.VIERNES_APLICA " +
               ",ofer.SABADO_APLICA " +
               ",ofer.DOMINGO_APLICA " +
               "from  tms_ofertas_servicio_tbl ofer " +
               ",tms_estados_tbl estori " +
               ",tms_estados_tbl estdes " +
               ",tms_empresas_tbl empre " +
               "where servicio_id = "+idSer+" " +
               "and ofer.RUTA_ID = "+idRuta+" " +
               "and ofer.OFERTA_SERVICIO_NOMBRE = '"+oferta+"' " +
               "and estori.ESTADO_ID =  ofer.TRAMO_ORIGEN_ID " +
               "and estdes.ESTADO_ID = ofer.TRAMO_DESTINO_ID " +
               "and ofer.EMPRESA_ID = empre.EMPRESA_ID " +
               "order by ofer.HORA_CORRIDA asc";
      return em.createNativeQuery(consulta).getResultList();
   }
   
   
   public Object buscaHorariosDeLaOfertadeServicios(long idSer, String oferta){
       String consulta="Select   to_char(ofer.HORA_CORRIDA,'HH24:MI') " +
               ",estori.ESTADO_NOMBRE " +
               ",estdes.ESTADO_NOMBRE " +
               ",empre.EMPRESA_NOMBRE " +
               ",ofer.LUNES_APLICA " +
               ",ofer.MARTES_APLICA " +
               ",ofer.MIERCOLES_APLICA " +
               ",ofer.JUEVES_APLICA " +
               ",ofer.VIERNES_APLICA " +
               ",ofer.SABADO_APLICA " +
               ",ofer.DOMINGO_APLICA " +
               ",ofer.RUTA_ID " +
               ",estori.ESTADO_ID " +
               ",estdes.ESTADO_ID " +
               ",empre.EMPRESA_ID " +
               ",ser.SERVICIO_NOMBRE " +
               ",ser.SERVICIO_CLAVE " +
               ",empre.EMPRESA_NOMBRE " +
               ",ofer.MENORES_CORRIDA " +
               ",ofer.SENECTUD_CORRIDA " +
               ",ofer.ESTUDIANTES_CORRIDA " +
               ",ofer.PROFESORES_CORRIDA " +
               ",ofer.CORTESIAS_CORRIDA " + 
               "from  tms_ofertas_servicio_tbl ofer " +
               ",tms_servicios_tbl ser " +
               ",tms_estados_tbl estori " +
               ",tms_estados_tbl estdes " +
               ",tms_empresas_tbl empre " +
               "where ser.servicio_id = "+idSer+" " +
               "and ofer.SERVICIO_ID = ser.SERVICIO_ID " +
               "and ofer.OFERTA_SERVICIO_NOMBRE = '"+oferta+"' " +
               "and estori.ESTADO_ID = ofer.TRAMO_ORIGEN_ID " +
               "and estdes.ESTADO_ID = ofer.TRAMO_DESTINO_ID " +
               "and ofer.EMPRESA_ID = empre.EMPRESA_ID " +
               "order by ofer.HORA_CORRIDA asc";
      return em.createNativeQuery(consulta).getResultList();
   }
   
   public Object buscaHorariosDeLaOfertaRolManual(long idSer, String oferta){
       String consulta="Select   to_char(ofer.HORA_CORRIDA,'HH24:MI') " +
               ",estori.ESTADO_NOMBRE " +
               ",estdes.ESTADO_NOMBRE " +
               ",ofer.OFERTA_SERVICIO_ID " +
               ",ofer.LUNES_APLICA " +
               ",ofer.MARTES_APLICA " +
               ",ofer.MIERCOLES_APLICA " +
               ",ofer.JUEVES_APLICA " +
               ",ofer.VIERNES_APLICA " +
               ",ofer.SABADO_APLICA " +
               ",ofer.DOMINGO_APLICA " +
               ",ofer.RUTA_ID " +
               ",empre.EMPRESA_NOMBRE " +
               "from  tms_ofertas_servicio_tbl ofer " +
               ",tms_servicios_tbl ser " +
               ",tms_estados_tbl estori " +
               ",tms_estados_tbl estdes " +
               ",tms_empresas_tbl empre " +
               "where ser.servicio_id = "+idSer+" " +
               "and ofer.SERVICIO_ID = ser.SERVICIO_ID " +
               "and ofer.OFERTA_SERVICIO_NOMBRE = '"+oferta+"' " +
               "and estori.ESTADO_ID = ofer.TRAMO_ORIGEN_ID " +
               "and estdes.ESTADO_ID = ofer.TRAMO_DESTINO_ID " +
               "and ofer.EMPRESA_ID = empre.EMPRESA_ID " +
               "order by ofer.HORA_CORRIDA asc";
      return em.createNativeQuery(consulta).getResultList();
   }
   
  public Object buscaPlantllaefaultPorServicio(String serv){
       String consulta="select plant.PLANTILLA_ENC_ID from " +
               "tms_servicios_tbl ser " +
               ",tms_servicio_parametros_tbl serparam " +
               ",tms_parametros_config_tbl param " +
               ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL plant " +
               "where ser.SERVICIO_NOMBRE = '"+serv+"' " +
               "and param.PARAMETRO_CODIGO = 'P_PLANTBUSPRED' " +
               "and serparam.SERVICIO_ID = ser.SERVICIO_ID " +
               "and serparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
               "and plant.NOMBRE_CORTO = serparam.PARAMETRO_VALOR";
      return em.createNativeQuery(consulta).getResultList();
   }
  
    public Object buscaPlantllaefaultPorRuta(long ruta){
       String consulta="select plant.PLANTILLA_ENC_ID from " +
               "tms_rutas_tbl ruta " +
               ",tms_ruta_parametros_tbl serparam " +
               ",tms_parametros_config_tbl param " +
               ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL plant " +
               "where ruta.RUTA_ID = "+ruta+ " " +
               "and param.PARAMETRO_CODIGO = 'P_PLANTBUSPRED' " +
               "and serparam.RUTA_ID = ruta.RUTA_ID " +
               "and serparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
               "and plant.NOMBRE_CORTO = serparam.PARAMETRO_VALOR";
      return em.createNativeQuery(consulta).getResultList();
   }
    
  public Object queryBuscaCR(String fi, String ff, long idservicio, String oris, String des, String emps, String tutas){
       /*String query = "select corrida_id from " +
               "tms_corridas_tbl corrida " +
               ",tms_estados_corrida_tbl estado " +
               "where  TRUNC(FECHA_HORA_CORRIDA) between TO_DATE('"+fi+"','dd/MM/yyyy') and TO_DATE('"+ff+"','dd/MM/yyyy') " +
               "and servicio_id = "+idservicio+" " +
               "and corrida.tipo_corrida = 'N' " +
               "and (estado.NOMBRE_ESTADO = 'ABIERTA' OR estado.NOMBRE_ESTADO = 'CERRADA') " +
               "and corrida.ESTADO_CORRIDA_ID = estado.ESTADO_CORRIDA_ID ";*/
   String query = "select corrida_id " +
           "from " +
           "tms_corridas_tbl corrida  " +
           ",tms_estados_corrida_tbl estado " +
           ",tms_estados_tbl ori " +
           ",tms_estados_tbl des " +
           ",tms_empresas_tbl emp " +
           "where  TRUNC(FECHA_HORA_CORRIDA) between TO_DATE('"+fi+"','dd/MM/yyyy') and TO_DATE('"+ff+"','dd/MM/yyyy')  " +
           "and corrida.servicio_id = "+idservicio+" " +
           "and corrida.tipo_corrida = 'N'  " +
           "and (estado.NOMBRE_ESTADO = 'ABIERTA' OR estado.NOMBRE_ESTADO = 'CERRADA')  " +
           "and corrida.ESTADO_CORRIDA_ID = estado.ESTADO_CORRIDA_ID " +
           "and ori.ESTADO_ID = corrida.ORIGEN_ID " +
           "and des.ESTADO_ID = corrida.DESTINO_ID " +
           "and corrida.EMPRESA_ID = emp.EMPRESA_ID " +
           "and ori.ESTADO_NOMBRE IN("+oris+")  " +
           "and des.ESTADO_NOMBRE IN("+des+") " +
           "and emp.EMPRESA_NOMBRE IN ("+emps+") " +
           "and corrida.RUTA_ID IN "+tutas;   
   System.out.println("Query: "+query);
           return   em.createNativeQuery(query).getResultList();
  }

    /** <code>select o from TmsCorridasTbl o where  o.corridaId = :corridaid</code> */
    public List<TmsCorridasTbl> queryTmsCorridasTblFindById(Object corridaid) {
        return em.createNamedQuery("TmsCorridasTbl.findByCorridaId").setParameter("corridaId", corridaid).getResultList();
    }  
    
    public Object queryBuscarBoletosVendidos(long idCorrida) {
        String consulta="select bol.BOLETO_ID " +
                "from tms_boletos_venta_tbl bol " +
                ",tms_corridas_tbl		cor " +
                "where bol.TIPO_OPERACION IN('VT','VA','HO','AC') " +   
                "and cor.CORRIDA_ID = "+idCorrida+" " +
                "and bol.CLAVE_CORRIDA = cor.CLAVE_CORRIDA ";
        return em.createNativeQuery(consulta).getResultList();
    }  
    
      public Object queryBuscarBoletosReservados(long idCorrida) {
        String consulta="select res.RESERVACION_ID from " +
                "tms_reservaciones_tbl res " +
                "where res.ESTADO_RESERVACION = 'RESERVADA' " +
                "and   res.CORRIDA_ID =  "+idCorrida+" ";
        return em.createNativeQuery(consulta).getResultList();
    }  
      
      
    public void removeTmsCorridasTbl(TmsCorridasTbl tmsCorridasTbl) {
        tmsCorridasTbl = em.find(TmsCorridasTbl.class, tmsCorridasTbl.getCorridaId());
        em.remove(tmsCorridasTbl);
    }      

    public void removeTmsCorridasVentaTbl(BigDecimal id) {
        TmsCorridasVentaTbl tmsCorridasVentaTbl = null;
        tmsCorridasVentaTbl = em.find(TmsCorridasVentaTbl.class, id);
        if(tmsCorridasVentaTbl!=null)
         em.remove(tmsCorridasVentaTbl);
    }    
    
    public void updateCorrida(TmsCorridasTbl corridanueva, BigDecimal idcorridavieja, TmsCorridasVentaTbl corridaVenta){
        TmsCorridasTbl corridaupdate;
        TmsCorridasVentaTbl corridaVentaUpdate;
        corridaupdate = em.find(TmsCorridasTbl.class, idcorridavieja);
        corridaVentaUpdate = em.find(TmsCorridasVentaTbl.class, idcorridavieja);
        //String nomc =corridanueva.getClaveCorrida()+""+corridaupdate.getCorridaId();
        //corridaupdate.setClaveCorrida(nomc);
        //corridaVentaUpdate.setClaveCorrida(nomc);
        corridaupdate.setAutobusId(corridanueva.getAutobusId());
        corridaupdate.setAutobusOriginalId(corridanueva.getAutobusOriginalId());
        corridaupdate.setOperadorId(corridanueva.getOperadorId());
        corridaupdate.setOperadorOriginalId(corridanueva.getOperadorOriginalId());
        corridaVentaUpdate.setOperador(corridaVenta.getOperador());
        corridaVentaUpdate.setAutobus(corridaVenta.getAutobus());
        //corridaVentaUpdate.setAdicional4("PSD");
        corridaupdate.setUltimaActualizacionPor(corridanueva.getUltimaActualizacionPor());
        corridaupdate.setUltimaFechaActualizacion(corridanueva.getUltimaFechaActualizacion());
        //corridaupdate.setAdicional4("PSD");
//        corridaupdate.setReplicacionOrigen("PCENTRAL");
//        corridaupdate.setReplicacionEstado("P");
//        corridaVentaUpdate.setReplicacionOrigen("PCENTRAL");
//        corridaVentaUpda  te.setReplicacionEstado("P");
        
        em.merge(corridaVentaUpdate);
        em.merge(corridaupdate);
    }

   public Object queryBuscaNombreTerminal(){
    return  em.createNativeQuery("select nombre_terminal from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  } 

   public Object queryBuscaIdTerminal(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }    
   
   public Object queryBuscaIdEstadoCorriaAbierta(){
    return  em.createNativeQuery("select estado_corrida_id from tms_estados_corrida_tbl where nombre_estado = 'ABIERTA'").getResultList();
  }
   
   public Object queryBuscaIdEstadoCorridaCerrada(){
    return  em.createNativeQuery("select estado_corrida_id from tms_estados_corrida_tbl where nombre_estado = 'CERRADA'").getResultList();
  }

   
   public Object buscaFlotillas(){
    return  em.createNativeQuery("select nombre_flotilla from tms_flotillas_tbl").getResultList();
  } 
 
  public Object buscaAutobusesPorFlotilla(String flotillaNombre){
      String consulta  = "select bus.NUMERO_ECONOMICO " +
              "from " +
              "tms_autobuses_tbl bus " +
              ",tms_flotillas_tbl flot " +
              "where flot.NOMBRE_FLOTILLA = '"+flotillaNombre+"' " +
              "and bus.FLOTILLA_ID = flot.FLOTILLA_ID " +
              "order by to_number(bus.NUMERO_ECONOMICO) asc";
    return  em.createNativeQuery(consulta).getResultList();
 }
  
    public Object buscarOperBusesporNumEcon(String bus) {
        String consulta="select bus.AUTOBUS_ID " +
                ",oper.OPERADOR_ID " +
                ",oper.CLAVE_OPERADOR " +
                ",operfijo.OPERADOR_ID " +
                ",operfijo.CLAVE_OPERADOR " +
                "from  " +
                "tms_autobuses_tbl  bus " +
                ",tms_operadores_tbl oper " +
                ",tms_operadores_tbl operfijo " +
                "where bus.NUMERO_ECONOMICO = '"+bus+"' " +
                "and  bus.OPERADOR_ID_PLANTA = oper.OPERADOR_ID(+)  " +
                "and  oper.OPERADOR_ID_FIJO  = operfijo.OPERADOR_ID (+)";
        return em.createNativeQuery(consulta).getResultList();
    }   
  
  /////////////////////////////////////////////////////////////////////////////////////
      public TmsOfertasServicioTbl findOferServ(Object pk) {
        return (TmsOfertasServicioTbl) em.find(TmsOfertasServicioTbl.class, pk);
    }

          
    public TmsRolesMaestroTbl createRolMaestro(TmsRolesMaestroTbl tmsRolesMaestroTbl,  String ter) {
        em.persist(tmsRolesMaestroTbl);
        String ids = ""+tmsRolesMaestroTbl.getRolMaestroId();
        String idnuevo = ter+""+ids;        
        long idn = Long.valueOf(idnuevo);
        tmsRolesMaestroTbl.setRolMaestroId(BigDecimal.valueOf(idn));
        return tmsRolesMaestroTbl;
    }
    
    
    public TmsRolesBaseTbl createRolBase(TmsRolesBaseTbl tmsRolesBaseTbl,  String ter) {
        em.persist(tmsRolesBaseTbl);
        String ids = ""+tmsRolesBaseTbl.getRolBaseId();
        String idnuevo = ter+""+ids;        
        long idn = Long.valueOf(idnuevo);
        tmsRolesBaseTbl.setRolBaseId(BigDecimal.valueOf(idn));
        return tmsRolesBaseTbl;
    }
    
     public void createRolBaseLineas(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl,  String ter) {
        em.persist(tmsRolesBaseLineasTbl);
        String ids = ""+tmsRolesBaseLineasTbl.getRolBaseLineaId();
        String idnuevo = ter+""+ids;        
        long idn = Long.valueOf(idnuevo);
        tmsRolesBaseLineasTbl.setRolBaseLineaId(BigDecimal.valueOf(idn));
    }   
    
      
    public List findAllFlotillas() {
        List listado = em.createQuery("select object(o) from TmsFlotillasTbl as o").getResultList();
        return listado;
    }

    public Object buscaIdEdoPorNombre(String edoNombre){
        return em.createNativeQuery("select ESTADO_ID  from tms_estados_tbl  where ESTADO_NOMBRE = '"+edoNombre+"'").getSingleResult();
    }

    public Object buscaEdoPorId(long edoId){
        return em.createNativeQuery("select ESTADO_NOMBRE  from tms_estados_tbl  where ESTADO_ID = "+edoId).getSingleResult();
    }
    
    
    public Object buscaRolesBase(long rolMaestroId)
    {
        return em.createNativeQuery("select rb.ROL_BASE_ID from tms_roles_base_tbl	   rb where rb.ROL_MAESTRO_ID = "+rolMaestroId).getResultList();
    }

    public Object buscaNombreRolesBase(long rolMaestroId)
    {
        return em.createNativeQuery("select rb.ROL_BASE_CATEGORIA from tms_roles_base_tbl	   rb where rb.ROL_MAESTRO_ID = "+rolMaestroId).getResultList();
    }
    
    public void eliminarRolesBaseLineas(String rolesBaseIds){
       String Consulta = "delete from tms_roles_base_lineas_tbl rbl where rbl.ROL_BASE_ID in("+rolesBaseIds+")";
        em.createNativeQuery(Consulta).executeUpdate();
   }    

    public void eliminarRolesBase(long rolMaestroId){
       String Consulta = "delete from tms_roles_base_tbl	   rb where rb.ROL_MAESTRO_ID = "+rolMaestroId;
        em.createNativeQuery(Consulta).executeUpdate();
   }    

    public Vector buscaRolMaestroExistente(String nombre){
     return (Vector) em.createNativeQuery("select * from tms_roles_maestro_tbl where ROL_MAESTRO_NOMBRE = '"+nombre+"'").getResultList();
   }

  public Object buscaPlantllasDefaultPorRuta(){
       String consulta="select rut.RUTA_ID,plant.PLANTILLA_ENC_ID " +
               "from tms_rutas_tbl rut " +
               ",tms_ruta_parametros_tbl rutparam " +
               ",tms_parametros_config_tbl param " +
               ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL plant " +
               "where param.PARAMETRO_CODIGO = 'P_PLANTBUSPRED' " +
               "and rutparam.RUTA_ID = rut.RUTA_ID " +
               "and rutparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
               "and plant.NOMBRE_CORTO = rutparam.PARAMETRO_VALOR";
      return em.createNativeQuery(consulta).getResultList();
   }

  public Object buscarRolesBasesPorRolMaestro(long idrm){
      return em.createNativeQuery("select rolbase.ROL_BASE_ID from tms_roles_base_tbl rolbase where rolbase.ROL_MAESTRO_ID = "+idrm).getResultList();
  }

  public Object buscarLineasBasesPorRolBase(long idrb){
      return em.createNativeQuery("select baselines.ROL_BASE_LINEA_ID from tms_roles_base_lineas_tbl baselines where baselines.ROL_BASE_ID = "+idrb).getResultList();
  }
  
  public boolean eliminarLineasBasePorRolBaseId(long idrb){
       String Consulta = "delete from tms_roles_base_lineas_tbl baselines where baselines.ROL_BASE_ID = "+idrb;
       int res =  em.createNativeQuery(Consulta).executeUpdate();
       //System.out.println("tms_roles_base_lineas_tbl = "+res);
       if(res==0) return false;
        else
            return true;
  }
  
   public boolean eliminarRolesBasePorRolMaestroId(long idrm){
       String Consulta = "delete from tms_roles_base_tbl rolbase where rolbase.ROL_MAESTRO_ID =  "+idrm;
       int res= em.createNativeQuery(Consulta).executeUpdate();
       //System.out.println("tms_roles_base_tbl = "+res);
       if(res==0) return false;
        else
            return true;
   }

   public boolean eliminarrRolMaestroPorId(long idrm){
       String Consulta = "delete from tms_roles_maestro_tbl rolmaestro where rolmaestro.ROL_MAESTRO_ID =  "+idrm;
       int res = em.createNativeQuery(Consulta).executeUpdate();
       //System.out.println("tms_roles_maestro_tbl = "+res);
       if(res==0) return false;
        else
            return true;
   }  

  public Object buscarDatosRutas(String rutas){
      return em.createNativeQuery("select rutas.RUTA_ID,rutas.RUTA_NUMERO,rutas.RUTA_NOMBRE from tms_rutas_tbl rutas where rutas.RUTA_ID in "+rutas).getResultList();
  }


public Vector buscaDiasOfertaPorId(long ofertaId){
  return (Vector)em.createNativeQuery("select lunes_aplica,martes_aplica,miercoles_aplica,jueves_aplica,viernes_aplica,sabado_aplica,domingo_aplica from TMS_OFERTAS_SERVICIO_TBL where oferta_servicio_id = "+ofertaId).getSingleResult();
}

public Object queryBuscarPasajeros(long idCorrida) {
        return em.createNativeQuery("select XER_TMS_PKG.TMS_PASAJEROS_NETO_CORRIDA_FNC("+idCorrida+") from dual").getResultList();
    } 
}
