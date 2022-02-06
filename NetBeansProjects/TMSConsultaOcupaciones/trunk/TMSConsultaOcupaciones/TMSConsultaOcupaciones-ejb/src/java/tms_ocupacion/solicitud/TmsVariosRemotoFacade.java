/*
 * TmsVariosRemotoFacade.java
 *
 * Created on 10 de diciembre de 2007, 08:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tms_ocupacion.entidad.TmsAutobusPlantLineasTbl;
import tms_ocupacion.entidad.TmsAutobusPlantillasEncTbl;
import tms_ocupacion.entidad.TmsComponenteBusTbl;
import tms_ocupacion.entidad.TmsCorridasTbl;
import tms_ocupacion.entidad.TmsCorridasVentaTbl;
import tms_ocupacion.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsVariosRemotoFacade implements tms_ocupacion.solicitud.TmsVariosRemotoFacadeRemote {
    
   
    @PersistenceContext(unitName="TMSConsultaOcupaciones-ejbPU")
    private EntityManager em;
    
    /**
     * Creates a new instance of TmsVariosFacade
     */
    public TmsVariosRemotoFacade() {
    }
    
    
  public Object actualizarImportes(String claveCorrida){
        String query = "select count(bol.BOLETO_ID)" +
                ", sum(bol.IMPORTE_BOLETO) from " +
                "tms_boletos_venta_tbl bol " +
                "where bol.TIPO_OPERACION IN('VT','VA','HO','AC') " +
                " and bol.CLAVE_CORRIDA = '"+claveCorrida+"' " +
                " and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";
       return em.createNativeQuery(query).getResultList();
    }
 
  public Object fechaServidor(String DBLink){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL@"+DBLink;
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }     

  public Object fechaServidor2(){
        String consulta="SELECT TO_CHAR(SYSDATE-1,'DD/MM/RRRR')||','||TO_CHAR(SYSDATE,'RRRR-MM-DD') fechas FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

  public Object queryBuscaEsquemaLocal(){
   return   em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }

 public Object queryBuscaTerminal(){
     String consulta = "select est.ESTADO_NOMBRE from " +
             "tms_base_datos_config_tbl conf " +
             ",tms_estados_tbl		   est " +
             "where esquema_propio = 'S' " +
             "and est.ESTADO_ID = conf.TERMINAL_ID ";
   return   em.createNativeQuery(consulta).getSingleResult();
  }
 
 public Object queryBuscaTerminalOperador(){
     String consulta = "select acc.accion_id from " +
             "tms_base_datos_config_tbl conf " +
             ",tms_acciones_tbl		   acc " +
             "where esquema_propio = 'S' " +
             "and acc.accion = conf.NOMBRE_TERMINAL ";
   return   em.createNativeQuery(consulta).getResultList();
  }
  // plantilla de autobus
 /**
     * <code>select o from TmsComponenteBusTbl o where o.tipo<>-1</code>
     * @return 
     */
    public List<TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll() {
        return em.createNamedQuery("TmsComponenteBusTbl.findAll").getResultList();
    }

    /**
     * 
     * @param pId 
     * @return 
     */
    public TmsAutobusPlantillasEncTbl queryTmsAutobusPlantillasEncTblFindById(Long pId) {
        return em.find(TmsAutobusPlantillasEncTbl.class, pId);
    }

    /**
     * 
     * @return 
     */
    public List<TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll() {
        return em.createNamedQuery("TmsAutobusPlantillasEncTbl.findAll").getResultList();
    }
    
    public List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindAll() {
        return em.createNamedQuery("TmsAutobusPlantLineasTbl.findTodo").getResultList();
    }

    /**
     * 
     * @param pId 
     * @return 
     */
    public List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindById(Long pId) {
        return em.createNamedQuery("TmsAutobusPlantLineasTbl.findAll").setParameter("encId", 
                                                                                    pId).getResultList();
    }
    
    public Object buscarTiposPasajero(){
        return em.createNativeQuery("select nombre_tipo, letra_tipo from tms_tipos_pasajero_tbl").getResultList();
    }

 
   public Object buscaParametrosPorServicio(){
    String consulta = "select param.PARAMETRO_CODIGO,serv.SERVICIO_NOMBRE, servparam.PARAMETRO_VALOR  from " +
            "tms_parametros_config_tbl  param " +
            ",tms_servicio_parametros_tbl servparam " +
            ",tms_servicios_tbl			serv " +
            "where   servparam.SERVICIO_ID = serv.SERVICIO_ID " +
            "and   param.PARAMETRO_TIPO = 'SERVICIO' " +
            "and   param.PARAMETRO_CODIGO = 'P_VLRVENNAS' " +
            "and   servparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
            "and   param.APLICA_TURNO_DIAS = 'N'";
    return em.createNativeQuery(consulta).getResultList();
    }    

   
   public Object buscaParametrosPorRuta(){
    String consulta = "select param.PARAMETRO_CODIGO,rutas.RUTA_NUMERO, rutparam.PARAMETRO_VALOR  from " +
            "tms_parametros_config_tbl  param " +
            ",tms_ruta_parametros_tbl rutparam " +
            ",tms_rutas_tbl			rutas " +
            "where   rutparam.RUTA_ID = rutas.RUTA_ID " +
            "and   param.PARAMETRO_TIPO = 'RUTA' " +
            "and   param.PARAMETRO_CODIGO = 'P_VLRVENNAS' " +
            "and   rutparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
            "and   param.APLICA_TURNO_DIAS = 'N' ";
    return em.createNativeQuery(consulta).getResultList();
    }    

   
   
/*
* Query para buscar los boletos vendidos y el importe de la venta por sistema de una corrida, para llenar lainformacion de la tarjeta de viaje
*/
    public Object buscaDatosOcupacionPorSistema(String corrida,String DBLink){
//     String consulta ="select bol.BOLETO_ID, bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO from tms_boletos_venta_tbl bol "+
//                    " where bol.TIPO_OPERACION IN('VT','VA','HO','AC') "+
//                    " and bol.CLAVE_CORRIDA = '"+corrida+"' " +
//                    " and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";

     String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
            // "NVL((select bolrel.importe_boleto from tms_boletos_venta_tbl bolrel where bolrel.boleto_id = bol.BOLETO_RELACIONADO_ID), bol.IMPORTE_BOLETO) importe_boleto  " +
             "from tms_boletos_venta_tbl@"+DBLink+" bol " +
             ",tms_corridas_tbl@"+DBLink+" cor  " +
             ",tms_corridas_tbl@"+DBLink+" correla  " +
             "where bol.TIPO_OPERACION IN('VT','VA','HO','AC')  " +
             "and cor.CLAVE_CORRIDA= '"+corrida+"' " +
             "and correla.CORRIDA_RELACIONADA_ID(+) = cor.CORRIDA_ID " +
             "and bol.CLAVE_CORRIDA in (cor.CLAVE_CORRIDA,correla.CLAVE_CORRIDA) " +
             "and not exists (select 1 from tms_boletos_venta_tbl@"+DBLink+" bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";   
        
 
        return em.createNativeQuery(consulta).getResultList();
    }
   
/*  Query para buscar el numero de ruta y el nombre de la ruta ando la clave de la corrida */	
   public Object buscarDatosRuta(String corrida, String DBLink){ 
       String consulta = "select rut.RUTA_NUMERO, rut.RUTA_NOMBRE from tms_corridas_tbl@"+DBLink+" cor, tms_rutas_tbl@"+DBLink+" rut where  cor.CLAVE_CORRIDA = '"+corrida+"'  and rut.RUTA_ID = cor.RUTA_ID";
        return em.createNativeQuery(consulta).getResultList();
    }

   public Object buscarNombreOperador(String clave){ 
       String consulta = "select opr.OPERADOR_NOMBRE, opr.OPERADOR_NOMBRE_MEDIO, opr.OPERADOR_APELLIDO from   tms_operadores_tbl opr where clave_operador = '"+clave+"'";
        return em.createNativeQuery(consulta).getResultList();
    }
    
   
   public Object queryBuscaValorActualTarjetaViaje(String DBLink){
       return   em.createNativeQuery("select  NVL(max(o.TARJETA_VIAJE_ID),0) from tms_tarjetas_viaje_tbl@"+DBLink+" o").getSingleResult();
   }
   
   public List<TmsTarjetasViajeTbl> buscarTarjetasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf,String DBLink){
       String consulta="   select tar.* from " +
               "tms_tarjetas_viaje_tbl@"+DBLink+" tar " +
               ",tms_corridas_venta_tbl@"+DBLink+" cor " +
               ",tms_estados_tarjeta_viaje_tbl@"+DBLink+" edotar " +
               "where cor.CORRIDA_ID = tar.CORRIDA_ID " +
               "and   cor.SERVICIO like '"+servicio+"' " +
               "and   tar.AUTOBUS  like '"+autobus+"' " +
               "and   tar.OPERADOR like '"+operador+"' " +
               "and   cor.ESTADO_CORRIDA = 'D' " +
               "and   edotar.NOMBRE_ESTADO = 'ABIERTA' " +
               "and   tar.ESTADO_TARJETA_ID =  edotar.ESTADO_TARJETA_VIAJE_ID " +
               "and   to_char(cor.FECHA_HORA_CORRIDA,'dd/mm/yyyy') between '"+fi+"' and '"+ff+"'  " +
               "and   to_char(cor.FECHA_HORA_CORRIDA,'HH24:MI')  between   '"+hi+"' and '"+hf+"' " +
               "order by cor.SERVICIO asc " +
               ",cor.FECHA_HORA_CORRIDA asc";
       return (List<TmsTarjetasViajeTbl>)em.createNativeQuery(consulta,TmsTarjetasViajeTbl.class).getResultList();
   }


   public List<TmsCorridasVentaTbl> buscarCorridasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf,String DBLink){
       String consulta="   select cor.* from " +
               "tms_tarjetas_viaje_tbl@"+DBLink+" tar " +
               ",tms_corridas_venta_tbl@"+DBLink+" cor " +
               ",tms_estados_tarjeta_viaje_tbl@"+DBLink+" edotar " +
               "where cor.CORRIDA_ID = tar.CORRIDA_ID " +
               "and   cor.SERVICIO like '"+servicio+"' " +
               "and   tar.AUTOBUS  like '"+autobus+"' " +
               "and   tar.OPERADOR like '"+operador+"' " +
               "and   cor.ESTADO_CORRIDA = 'D' " +
               "and   edotar.NOMBRE_ESTADO = 'ABIERTA' " +
               "and   tar.ESTADO_TARJETA_ID =  edotar.ESTADO_TARJETA_VIAJE_ID " +
               "and   to_char(cor.FECHA_HORA_CORRIDA,'dd/mm/yyyy') between '"+fi+"' and '"+ff+"'  " +
               "and   to_char(cor.FECHA_HORA_CORRIDA,'HH24:MI')  between   '"+hi+"' and '"+hf+"' " +
               "order by cor.SERVICIO asc " +
               " ,cor.FECHA_HORA_CORRIDA asc";
       return (List<TmsCorridasVentaTbl>)em.createNativeQuery(consulta,TmsCorridasVentaTbl.class).getResultList();
   }

   public Object buscarParametroNumMaxTar(String servicios){
       String consulta = "select serparam.PARAMETRO_VALOR from " +
               "tms_servicios_tbl ser " +
               ",tms_parametros_config_tbl param " +
               ",tms_servicio_parametros_tbl serparam " +
               "where param.PARAMETRO_CODIGO = 'P_LIMTARNRE' " +
               "and ser.SERVICIO_NOMBRE = '"+servicios+"' " +
               "and serparam.SERVICIO_ID = ser.SERVICIO_ID " +
               "and serparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID";
       return em.createNativeQuery(consulta).getResultList();
   }
   
   public Object buscarNumTar(String operador, String servicio, String DBLink){
       String consulta = "select count(tar.TARJETA_VIAJE_ID) from " +
               "tms_tarjetas_viaje_tbl@"+DBLink+" tar " +
               ",tms_corridas_tbl@"+DBLink+" cor " +
               ",tms_estados_tarjeta_viaje_tbl@"+DBLink+" est " +
               ",tms_operadores_tbl@"+DBLink+" oper " +
               ",tms_servicios_tbl@"+DBLink+" ser " +
               "where est.NOMBRE_ESTADO = 'ABIERTA' " +
               "and oper.CLAVE_OPERADOR = '"+operador+"' " +
               "and ser.SERVICIO_NOMBRE = '"+servicio+"' " +
               "and est.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID " +
               "and tar.CORRIDA_ID = cor.CORRIDA_ID " +
               "and cor.SERVICIO_ID = ser.SERVICIO_ID " +
               "and cor.OPERADOR_ID = oper.OPERADOR_ID";
       return em.createNativeQuery(consulta).getResultList();
   }   
   
    public String esUsuarioSupervisor(String usuarioNumero,String pwdEnc, String funcion){
        String respuesta = "";
        Vector resultado;
        Vector usrfuncion;
        String consultaUsuario;
        String consultaUsuarioFuncion;
        consultaUsuario = "select usr.USUARIO_ID from tms_usuarios_tbl usr where usr.USUARIO_NUMERO = '"+usuarioNumero+"' and usr.CONTRASENA_ENCRIPTADA = '"+pwdEnc+"'";
        consultaUsuarioFuncion = "select usr.USUARIO_ID from " +
                "tms_funciones_tbl 		  fun " +
                ",tms_menus_encabezado_tbl men " +
                ",tms_menus_lineas_tbl     mlin " +
                ",tms_usuarios_tbl		  usr " +
                ",tms_perfiles_tbl		  per " +
                ",tms_usuario_perfiles_tbl usrper " +
                "where   usr.USUARIO_NUMERO = '"+usuarioNumero+"' " +
                "and     usrper.USUARIO_ID = usr.USUARIO_ID " +
                "and     per.PERFIL_ID = usrper.PERFIL_ID " +
                "and     men.MENU_ID = per.MENU_ID " +
                "and 	mlin.MENU_ID = men.MENU_ID " +
                "and 	fun.FUNCION_ID = mlin.FUNCION_ID " +
                "and     fun.FUNCION_NUMERO = '"+funcion+"' ";

            resultado =(Vector) em.createNativeQuery(consultaUsuario).getResultList();
        if(resultado.size()==0)
            respuesta = "no encontrado";
        else
        {
             usrfuncion = (Vector) em.createNativeQuery(consultaUsuarioFuncion).getResultList();
             if(usrfuncion.size()==0)
               respuesta = "invalido";
             else
             {
                 Vector vre = (Vector)usrfuncion.get(0);
                 respuesta = vre.get(0).toString();
             }
        }
    return respuesta;
    }      


    public String buscaFuncion(String usuarioNumero, String funcion){
        String respuesta = "";
        Vector resultado;
        Vector usrfuncion;
        String consultaUsuario;
        String consultaUsuarioFuncion;
        consultaUsuario = "select usr.USUARIO_ID from tms_usuarios_tbl usr where usr.USUARIO_NUMERO = '"+usuarioNumero+"' ";
        consultaUsuarioFuncion = "select usr.USUARIO_ID from " +
                "tms_funciones_tbl 		  fun " +
                ",tms_menus_encabezado_tbl men " +
                ",tms_menus_lineas_tbl     mlin " +
                ",tms_usuarios_tbl		  usr " +
                ",tms_perfiles_tbl		  per " +
                ",tms_usuario_perfiles_tbl usrper " +
                "where   usr.USUARIO_NUMERO = '"+usuarioNumero+"' " +
                "and     usrper.USUARIO_ID = usr.USUARIO_ID " +
                "and     per.PERFIL_ID = usrper.PERFIL_ID " +
                "and     men.MENU_ID = per.MENU_ID " +
                "and 	mlin.MENU_ID = men.MENU_ID " +
                "and 	fun.FUNCION_ID = mlin.FUNCION_ID " +
                "and     fun.FUNCION_NUMERO = '"+funcion+"' ";

            resultado =(Vector) em.createNativeQuery(consultaUsuario).getResultList();
        if(resultado.size()==0)
            respuesta = "no encontrado";
        else
        {
             usrfuncion = (Vector) em.createNativeQuery(consultaUsuarioFuncion).getResultList();
             if(usrfuncion.size()==0)
               respuesta = "invalido";
             else
             {
                 //Vector vre = (Vector)usrfuncion.get(0);
                 respuesta = "encontrado";//vre.get(0).toString();
             }
        }
    return respuesta;
    }      




 public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return em.createNativeQuery(consulta).getSingleResult();
 }
 
 public Object buscaNumeroUsuario(long usuarioId){
     String consulta =  "select usuario_numero from tms_usuarios_tbl where usuario_id = "+usuarioId;
     return em.createNativeQuery(consulta).getSingleResult();
 } 
 
 public Object numeroAsientosOcupadosNoDisponibles(long corridaId, String DBLink){
     String consulta =  "select count(1)  " +
             "from tms_asientos_no_disp_tbl@"+DBLink+" tmsAs " +
             "where not exists ( " +
             "select 1 " +
             "from tms_boletos_venta_tbl@"+DBLink+" bol " +
             ",tms_corridas_tbl@"+DBLink+"		cor " +
             "where cor.CLAVE_CORRIDA = bol.CLAVE_CORRIDA " +
             "and bol.NO_ASIENTO	= tmsAs.NO_ASIENTO " +
             "and tmsAs.CORRIDA_ID = cor.CORRIDA_ID  " +
             "and not exists (select 1 from tms_boletos_venta_tbl@"+DBLink+" bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID) " +
             ") " +
             "and tmsAs.ESTADO_ASIENTO = 'N'	  " +
             "and tmsAs.CORRIDA_ID = "+corridaId;
     return em.createNativeQuery(consulta).getSingleResult();
 }


   public Object queryBuscaIdTerminal(String DBLink){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl@"+DBLink+" where esquema_propio = 'S'" ).getSingleResult();
  }    
   
   public Object queryBuscaNombreEsquema(String DBLink){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl@"+DBLink+" where esquema_propio = 'S'" ).getSingleResult();
  }  


    public Object queryBuscaEstadoTarjetaViaje(){
    return  em.createNativeQuery("select tmsEstTar.ESTADO_TARJETA_VIAJE_ID from TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar where tmsEstTar.NOMBRE_ESTADO = 'ABIERTA'" ).getResultList();
  }   
  
   public TmsTarjetasViajeTbl buscaTarjetaViajeExistente(long corridaId, String DBLink){
    TmsTarjetasViajeTbl tarExist = null;
       String consulta = "select * " +
               "from " +
               "TMS_TARJETAS_VIAJE_TBL@"+DBLink+" " +
               "where CORRIDA_ID = "+corridaId+"";
        try {
            tarExist = (TmsTarjetasViajeTbl) em.createNativeQuery(consulta, TmsTarjetasViajeTbl.class).getSingleResult();
        } catch(NoResultException e) {
        }
    return tarExist;
   }    
    
   public Object buscaDBLinkCentral(){
    return  em.createNativeQuery("select NOMBRE_DBLINK from TMS_BASE_DATOS_CONFIG_TBL where NOMBRE_TERMINAL = 'CENTRAL'" ).getResultList();
  }
   
    public Object buscaTarjetaViajeExistenteCentral(String DBLink,String folTar){
        String consulta = "select edos.NOMBRE_ESTADO " +
                "from " +
                "TMS_TARJETAS_VIAJE_TBL@"+DBLink+" tar " +
                ",TMS_ESTADOS_TARJETA_VIAJE_TBL@"+DBLink+"   edos " +
                "where FOLIO_TARJETA  = '"+folTar+"' " +
                "and   edos.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID"; 
        return em.createNativeQuery(consulta).getResultList();
    }
    
    public List<TmsCorridasVentaTbl> buscarCorridasProximas(String feha1, String fecha2, String operador, String servic, String bus, String DBLink){
      String consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"' or t.operador is null) " +
              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";
      if(!operador.equals("%") && bus.equals("%"))
      {
              consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"') " +
              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";          
      }
      if(!bus.equals("%") && operador.equals("%"))
      {
             consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"' or t.operador is null) " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";          
      }
      if(!bus.equals("%") && !operador.equals("%"))
      {
             consulta = "SELECT * FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and t.operador like '"+operador+"' " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "order by t.fecha_Hora_Corrida	";          
      }

      List<TmsCorridasVentaTbl> listado = new ArrayList<TmsCorridasVentaTbl>();
      Vector  vCorridas =  (Vector)em.createNativeQuery(consulta).getResultList();
      for(int i=0; i<vCorridas.size();i++)
      {
          Vector corrida = (Vector)vCorridas.get(i);
          TmsCorridasVentaTbl cv = new TmsCorridasVentaTbl();
          cv.setValoresIniciales(corrida);
          listado.add(cv);
      }      

//      List<TmsCorridasVentaTbl> listado = null;
//      listado =  (List<TmsCorridasVentaTbl>)em.createNativeQuery(consulta,TmsCorridasVentaTbl.class).getResultList();
//      //for(int i=0; i<listado.size();i++)
//      //    em.refresh(listado.get(i)); 
     return listado;          
    }   
    
    public TmsCorridasVentaTbl buscaCorridaRemota(long idCorrida, String DBLink){
        TmsCorridasVentaTbl corrida = new TmsCorridasVentaTbl();
        try {
             Vector vCorridas = (Vector) em.createNativeQuery("select * from tms_corridas_venta_tbl@"+DBLink+" where CORRIDA_ID = "+idCorrida).getResultList();
           if(vCorridas.size()>0)
           {
                 Vector vCorrida = (Vector)vCorridas.get(0);
                 corrida.setValoresIniciales(vCorrida);
           }
        } catch (NoSuchElementException evt){corrida = null;}
        return corrida;
    }

    public TmsCorridasTbl buscaCorridaSolaRemota(long idCorrida, String DBLink){
        TmsCorridasTbl corrida = null;
        try {
            corrida = (TmsCorridasTbl) em.createNativeQuery("select * from tms_corridas_tbl@"+DBLink+" where CORRIDA_ID = "+idCorrida,TmsCorridasTbl.class).getSingleResult();
        } catch (NoSuchElementException evt){
         corrida = null;
        }
        return corrida;
    }
    
    public boolean ActualizarCorrida(long idCorrida,long idEstado,String NombreEsquema,String DBLink)
    {
        String Consulta = "update tms_corridas_tbl@"+DBLink+" set ESTADO_CORRIDA_ID = "+idEstado+" , REPLICACION_ORIGEN = '"+NombreEsquema+"' where corrida_id = "+idCorrida;
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
    }
    
    public boolean ActualizarCorridaVenta(long idCorridaVenta,String letraEstado,String NombreEsquema,String DBLink)
    {
        String Consulta = "update tms_corridas_venta_tbl@"+DBLink+" set ESTADO_CORRIDA = '"+letraEstado+"', REPLICACION_ORIGEN = '"+NombreEsquema+"' where corrida_id = "+idCorridaVenta;
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
    }
    
    public boolean ActualizarTarjetaExistente(long noAdulTarexist,double mtoAdulTarexist,long noMenTarexist,double mtoMenTarexist,long noProfTarexist,double mtoProfTarexist,long noSenTarexist,double mtoSenTarexist,long noEstudTarexist,double mtoEstudTarexist,long noEspTarexist,double mtoEspTarexist,long edotarexist,String nombreEsquema,String DBLink,long idTar, long user)
    {
        String Consulta = "update tms_tarjetas_viaje_tbl@"+DBLink+" " +
                "set  NO_ADULTOS_ABORDADOS = "+noAdulTarexist+" " +
                ",MONTO_ADULTOS_ABORDADOS = "+mtoAdulTarexist+" " +
                ",NO_ESTUDIANTES_ABORDADOS = "+noMenTarexist+" " +
                ",MONTO_ESTUDIANTES_ABORDADOS = "+mtoMenTarexist+" " +
                ",NO_SENECTUD_ABORDADOS = "+noProfTarexist+" " +
                ",MONTO_SENECTUD_ABORDADOS = "+mtoProfTarexist+" " +
                ",NO_MENORES_ABORDADOS = "+noSenTarexist+" " +
                ",MONTO_MENORES_ABORDADOS = "+mtoSenTarexist+" " +
                ",NO_PROFESOR_ABORDADOS = "+noEstudTarexist+" " +
                ",MONTO_PROFESOR_ABORDADOS = "+mtoEstudTarexist+" " +
                ",NO_ESPECIAL_ABORDADOS = "+noEspTarexist+" " +
                ",MONTO_ESPECIAL_ABORDADOS = "+mtoEspTarexist+" " +
                ",ESTADO_TARJETA_ID = "+edotarexist+" " +
                ",REPLICACION_ORIGEN  = '"+nombreEsquema+"' " +
                ",ULTIMA_ACTUALIZACION_POR = "+user+"   " +
                ",ULTIMA_FECHA_ACTUALIZACION  = SYSDATE " +
                "where TARJETA_VIAJE_ID = "+idTar;
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
    }
    
    
    public boolean ActualizarFolioTarjeta(String DBLink,long idTar, String folio)
    {
        String Consulta = "update tms_tarjetas_viaje_tbl@"+DBLink+" " +
                "set  FOLIO_TARJETA = '"+folio+"' "+
                "where TARJETA_VIAJE_ID = "+idTar;
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
    }
    
   public TmsTarjetasViajeTbl insertarTarjeta(TmsTarjetasViajeTbl tarjeta, String ter, String DBLink){
       TmsTarjetasViajeTbl tar = null; 
       String Consulta = "insert into tms_tarjetas_viaje_tbl@"+DBLink+"  (TARJETA_VIAJE_ID,FOLIO_TARJETA,CORRIDA_ID,ESTADO_TARJETA_ID,NUMERO_IMPRESION,NO_ADULTOS_ABORDADOS,MONTO_ADULTOS_ABORDADOS,NO_ESTUDIANTES_ABORDADOS,MONTO_ESTUDIANTES_ABORDADOS,NO_SENECTUD_ABORDADOS,MONTO_SENECTUD_ABORDADOS,NO_MENORES_ABORDADOS,MONTO_MENORES_ABORDADOS,NO_PROFESOR_ABORDADOS,MONTO_PROFESOR_ABORDADOS,NO_ESPECIAL_ABORDADOS,MONTO_ESPECIAL_ABORDADOS,OPERADOR,AUTOBUS,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ORIGEN) " +
                "values    ( '"+ter+"'||TMS_TARJETAS_VIAJE_SEQ.NEXTVAL@"+DBLink+",'"+tarjeta.getFolioTarjeta()+"',"+tarjeta.getCorridaId().longValue()+",  "+tarjeta.getEstadoTarjetaId().longValue()+",0,"+tarjeta.getNoAdultosAbordados().longValue()+","+tarjeta.getMontoAdultosAbordados().doubleValue()+","+tarjeta.getNoEstudiantesAbordados().longValue()+","+tarjeta.getMontoEstudiantesAbordados().doubleValue()+","+tarjeta.getNoSenectudAbordados().longValue()+","+tarjeta.getMontoSenectudAbordados().doubleValue()+","+tarjeta.getNoMenoresAbordados().longValue()+","+tarjeta.getMontoMenoresAbordados().doubleValue()+","+tarjeta.getNoProfesorAbordados().longValue()+","+tarjeta.getMontoProfesorAbordados().doubleValue()+","+tarjeta.getNoEspecialAbordados().longValue()+","+tarjeta.getMontoEspecialAbordados()+", '"+tarjeta.getOperador()+"','"+tarjeta.getAutobus()+"',"+tarjeta.getCreadoPor().longValue()+",SYSDATE,"+tarjeta.getUltimaActualizacionPor().longValue()+",SYSDATE, '"+tarjeta.getReplicacionOrigen()+"')";
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return tar;
        else
        {
           tar = (TmsTarjetasViajeTbl)em.createNativeQuery("select * from tms_tarjetas_viaje_tbl@"+DBLink+" where CORRIDA_ID = "+tarjeta.getCorridaId().longValue(),TmsTarjetasViajeTbl.class).getSingleResult();
           return tar;
        }
   }
   
   public Vector buscaTarjetaCreada(long idCorrida, String DBLink){
       return (Vector)em.createNativeQuery("select tarjeta_viaje_id from tms_tarjetas_viaje_tbl@"+DBLink+" where CORRIDA_ID = "+idCorrida).getResultList();
   }
   
   
   public boolean modificarTarjetaReimpresion(TmsTarjetasViajeTbl tarjeta, String DBLink){
       
       String Consulta = "update tms_tarjetas_viaje_tbl@"+DBLink+"  set NUMERO_IMPRESION  = "+tarjeta.getNumeroImpresion().longValue()+" , MOTIVO_REIMPRESION  = '"+tarjeta.getMotivoReimpresion()+"', ULTIMA_ACTUALIZACION_POR = "+tarjeta.getUltimaActualizacionPor().longValue()+", ULTIMA_FECHA_ACTUALIZACION = SYSDATE, REPLICACION_ORIGEN = '"+tarjeta.getReplicacionOrigen()+"'  where tarjeta_viaje_id = "+tarjeta.getTarjetaViajeId().longValue();
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
       
   }
   
}
