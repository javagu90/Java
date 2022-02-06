/*
 * TmsVariosFacade.java
 *
 * Created on 10 de septiembre de 2007, 09:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;


import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tms_ocupacion.entidad.TmsAutobusPlantLineasTbl;
import tms_ocupacion.entidad.TmsAutobusPlantillasEncTbl;
import tms_ocupacion.entidad.TmsComponenteBusTbl;
import tms_ocupacion.entidad.TmsCorridasVentaTbl;
import tms_ocupacion.entidad.TmsTarjetasViajeTbl;
/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsVariosFacade implements tms_ocupacion.solicitud.TmsVariosFacadeRemote {
   
    @PersistenceContext(unitName="TMSConsultaOcupaciones-ejbPU")
    private EntityManager em;
    
    /**
     * Creates a new instance of TmsVariosFacade
     */
    public TmsVariosFacade() {
    }
    
    
  public Object actualizarImportes(String claveCorrida){
        String query = "select count(bol.BOLETO_ID)" +
                ", sum(bol.IMPORTE_BOLETO) from " +
                "tms_boletos_venta_tbl bol " +
                "where bol.TIPO_OPERACION IN('VT','VA','HO','AC','FT','FO','FC') " +
                " and bol.CLAVE_CORRIDA = '"+claveCorrida+"' " +
                " and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";
       return em.createNativeQuery(query).getResultList();
    }
 
  public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL";
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
        //return em.createNativeQuery("select nombre_tipo, letra_tipo from tms_tipos_pasajero_tbl").getResultList();
        return em.createNativeQuery("select t.nombre_tipo, t.letra_tipo from tms_tipos_pasajero_tbl t where (t.fecha_hasta is null or t.fecha_hasta >= SYSDATE )").getResultList();
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
    public Object buscaDatosOcupacionPorSistema(String corrida){
//     String consulta ="select bol.BOLETO_ID, bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO from tms_boletos_venta_tbl bol "+
//                    " where bol.TIPO_OPERACION IN('VT','VA','HO','AC') "+
//                    " and bol.CLAVE_CORRIDA = '"+corrida+"' " +
//                    " and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";
//System.out.println("buscaDatosOcupacionPorSistema en local de la corrida: "+corrida);
     String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO||'  '||bol.NOMBRE_PASAJERO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
            // "NVL((select bolrel.importe_boleto from tms_boletos_venta_tbl bolrel where bolrel.boleto_id = bol.BOLETO_RELACIONADO_ID), bol.IMPORTE_BOLETO) importe_boleto  " +
             "from tms_boletos_venta_tbl bol " +
             ",tms_corridas_tbl cor  " +
             ",tms_corridas_tbl correla  " +
             "where bol.TIPO_OPERACION IN('VT','VA','HO','AC','FT','FO','FC')  " +
             "and cor.CLAVE_CORRIDA= '"+corrida+"' " +
             "and correla.CORRIDA_RELACIONADA_ID(+) = cor.CORRIDA_ID " +
             "and bol.CLAVE_CORRIDA in (cor.CLAVE_CORRIDA,correla.CLAVE_CORRIDA) " +
             "and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";   
        
 
        return em.createNativeQuery(consulta).getResultList();
    }
    
    public Object buscaDatosOcupacionPorSistemaRemoto(String corrida, String dblink){
     String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO||'  '||bol.NOMBRE_PASAJERO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
            // "NVL((select bolrel.importe_boleto from tms_boletos_venta_tbl bolrel where bolrel.boleto_id = bol.BOLETO_RELACIONADO_ID), bol.IMPORTE_BOLETO) importe_boleto  " +
             "from tms_boletos_venta_tbl@"+dblink+" bol" +
             ",tms_corridas_tbl@"+dblink+" cor  " +
             ",tms_corridas_tbl@"+dblink+" correla  " +
             "where bol.TIPO_OPERACION IN('VT','VA','HO','AC','FT','FO','FC')  " +
             "and cor.CLAVE_CORRIDA= '"+corrida+"' " +
             "and correla.CORRIDA_RELACIONADA_ID(+) = cor.CORRIDA_ID " +
             "and bol.CLAVE_CORRIDA in (cor.CLAVE_CORRIDA,correla.CLAVE_CORRIDA) " +
             "and not exists (select 1 from tms_boletos_venta_tbl@"+dblink+" bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";   
        
    System.out.println("Conuslta ocupacion: "+consulta);
        return em.createNativeQuery(consulta).getResultList();
    }    
   
/*  Query para buscar el numero de ruta y el nombre de la ruta dando la clave de la corrida */	
   public Object buscarDatosRuta(String corrida){ 
       String consulta = "select rut.RUTA_NUMERO, rut.RUTA_NOMBRE from tms_corridas_tbl cor, tms_rutas_tbl rut where  cor.CLAVE_CORRIDA = '"+corrida+"'  and rut.RUTA_ID = cor.RUTA_ID";
        return em.createNativeQuery(consulta).getResultList();
    }

   public Object buscarNombreOperador(String clave){ 
       String consulta = "select opr.OPERADOR_NOMBRE, opr.OPERADOR_NOMBRE_MEDIO, opr.OPERADOR_APELLIDO from   tms_operadores_tbl opr where clave_operador = '"+clave+"'";
        return em.createNativeQuery(consulta).getResultList();
    }
    
   
   public Object queryBuscaValorActualTarjetaViaje(){
       return   em.createNativeQuery("select  NVL(max(o.TARJETA_VIAJE_ID),0) from tms_tarjetas_viaje_tbl o").getSingleResult();
   }
   
   public List<TmsTarjetasViajeTbl> buscarTarjetasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf){
       String consulta="   select tar.* from " +
               "tms_tarjetas_viaje_tbl tar " +
               ",tms_corridas_venta_tbl cor " +
               ",tms_estados_tarjeta_viaje_tbl edotar " +
               "where cor.CORRIDA_ID = tar.CORRIDA_ID " +
               "and   cor.SERVICIO like '"+servicio+"' " +
               "and   tar.AUTOBUS  like '"+autobus+"' " +
               "and   tar.OPERADOR like '"+operador+"' " +
               "and   cor.ESTADO_CORRIDA = 'D' " +
               "and   edotar.NOMBRE_ESTADO = 'ABIERTA' " +
               "and   tar.ESTADO_TARJETA_ID =  edotar.ESTADO_TARJETA_VIAJE_ID " +
               "and   cor.FECHA_HORA_CORRIDA between to_date('"+fi+" 00:00','DD/MM/RRRR HH24:MI') and to_date('"+ff+" 23:59','DD/MM/RRRR HH24:MI') " +
               "and   to_char(cor.FECHA_HORA_CORRIDA,'HH24:MI')  between   '"+hi+"' and '"+hf+"' " +
               "order by cor.SERVICIO asc " +
               ",cor.FECHA_HORA_CORRIDA asc";
        List<TmsTarjetasViajeTbl> listado = (List<TmsTarjetasViajeTbl>)em.createNativeQuery(consulta,TmsTarjetasViajeTbl.class).getResultList();
        if(listado.size()>0){
           for(int i=0; i<listado.size(); i++)
              em.refresh(listado.get(i));
        }
        return listado;
   }


   public List<TmsCorridasVentaTbl> buscarCorridasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf){
       String consulta="   select cor.* from " +
               "tms_tarjetas_viaje_tbl tar " +
               ",tms_corridas_venta_tbl cor " +
               ",tms_estados_tarjeta_viaje_tbl edotar " +
               "where cor.CORRIDA_ID = tar.CORRIDA_ID " +
               "and   cor.SERVICIO like '"+servicio+"' " +
               "and   tar.AUTOBUS  like '"+autobus+"' " +
               "and   tar.OPERADOR like '"+operador+"' " +
               "and   cor.ESTADO_CORRIDA = 'D' " +
               "and   edotar.NOMBRE_ESTADO = 'ABIERTA' " +
               "and   tar.ESTADO_TARJETA_ID =  edotar.ESTADO_TARJETA_VIAJE_ID " +
               "and  cor.FECHA_HORA_CORRIDA between to_date('"+fi+" 00:00','DD/MM/RRRR HH24:MI') and to_date('"+ff+" 23:59','DD/MM/RRRR HH24:MI') " +
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
   
   public Object buscarNumTar(String operador, String servicio,String DBLinkCentral){
//       String consulta = "select count(tar.TARJETA_VIAJE_ID) from " +
//               "tms_tarjetas_viaje_tbl tar " +
//               ",tms_corridas_tbl cor " +
//               ",tms_estados_tarjeta_viaje_tbl est " +
//               ",tms_operadores_tbl oper " +
//               ",tms_servicios_tbl ser " +
//               "where est.NOMBRE_ESTADO = 'ABIERTA' " +
//               "and oper.CLAVE_OPERADOR = '"+operador+"' " +
//               "and ser.SERVICIO_NOMBRE = '"+servicio+"' " +
//               "and tar.ESTADO_TARJETA_ID = est.ESTADO_TARJETA_VIAJE_ID " +
//               "and tar.CORRIDA_ID = cor.CORRIDA_ID " +
//               "and cor.SERVICIO_ID = ser.SERVICIO_ID " +
//               "and cor.OPERADOR_ID = oper.OPERADOR_ID";
      String consulta = "select count(tar.TARJETA_VIAJE_ID) from " +
              "tms_tarjetas_viaje_tbl@"+DBLinkCentral+" tar " +
              ",tms_corridas_tbl@"+DBLinkCentral+" cor " +
              ",tms_estados_tarjeta_viaje_tbl@"+DBLinkCentral+" est " +
              ",tms_servicios_tbl@"+DBLinkCentral+" ser " +
              "where est.NOMBRE_ESTADO = 'ABIERTA' " +
              "and tar.OPERADOR = '"+operador+"' " +
              "and ser.SERVICIO_NOMBRE = '"+servicio+"' " +
              "and tar.ESTADO_TARJETA_ID = est.ESTADO_TARJETA_VIAJE_ID " +
              "and tar.CORRIDA_ID = cor.CORRIDA_ID  " +
              "and cor.SERVICIO_ID = ser.SERVICIO_ID ";
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
 
 public Object numeroAsientosOcupadosNoDisponibles(long corridaId){
     String consulta =  "select count(1)  " +
             "from tms_asientos_no_disp_tbl tmsAs " +
             "where not exists ( " +
             "select 1 " +
             "from tms_boletos_venta_tbl bol " +
             ",tms_corridas_tbl		cor " +
             "where cor.CLAVE_CORRIDA = bol.CLAVE_CORRIDA " +
             "and bol.NO_ASIENTO	= tmsAs.NO_ASIENTO " +
             "and tmsAs.CORRIDA_ID = cor.CORRIDA_ID  " +
             "and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID) " +
             ") " +
             "and tmsAs.ESTADO_ASIENTO = 'N'	  " +
             "and tmsAs.CORRIDA_ID = "+corridaId;
     return em.createNativeQuery(consulta).getSingleResult();
 }


   public Object queryBuscaIdTerminal(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }    
   
   public Object queryBuscaNombreEsquema(){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }  


    public Object queryBuscaEstadoTarjetaViaje(){
    return  em.createNativeQuery("select tmsEstTar.ESTADO_TARJETA_VIAJE_ID from TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar where tmsEstTar.NOMBRE_ESTADO = 'ABIERTA'" ).getResultList();
  }   
  
   public TmsTarjetasViajeTbl buscaTarjetaViajeExistente(long corridaId){
    TmsTarjetasViajeTbl tarExist = null;
       String consulta = "select * " +
               "from " +
               "TMS_TARJETAS_VIAJE_TBL " +
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
    
    public Object buscaEncabezados(String tabla){
        String consulta = "SELECT COLS.COLUMN_NAME FROM SYS.ALL_TAB_COLUMNS COLS WHERE COLS.OWNER = USER AND COLS.TABLE_NAME = '"+tabla+"'";
        return em.createNativeQuery(consulta).getResultList();
    }    
    

    public Object buscarDatos(String autobus, String operador, String servicio, String empresa, String origen, String destino, String fi, String ff, String hi, String tabla, String rutas, boolean extras){
        /*String consulta = "SELECT COUNT(*) FROM TMS_PERFILES_RUTAS_TBL pr "+
                          " WHERE pr.PERFIL_ID IN ( SELECT up.PERFIL_ID FROM TMS_USUARIO_PERFILES_TBL up WHERE up.USUARIO_ID = "+p_usuario+" ) " + 
                          " AND pr.RUTA_ID = (SELECT ruta_id FROM TMS_RUTAS_TBL rt "+
                                                                         " WHERE rt.SERVICIO_ID = (SELECT servicio_id FROM TMS_SERVICIOS_TBL WHERE servicio_nombre = '"+servicio+"') "+
                                                                             " AND RT.TRAMO_ORIGEN_ID = (SELECT ESTADO_ID FROM TMS_ESTADOS_TBL WHERE ESTADO_NOMBRE = '"+origen+"') "+
                                                                             " AND RT.TRAMO_DESTINO_ID = (SELECT ESTADO_ID FROM TMS_ESTADOS_TBL WHERE ESTADO_NOMBRE = '"+destino+"')) "+
                                                                             " AND pr.EMPRESA_ID = (SELECT empresa_id FROM TMS_EMPRESAS_TBL WHERE empresa_nombre = '"+empresa+"')";
        System.out.println("Conuslta: "+consulta);
        Vector numero = (Vector) em.createNativeQuery(consulta).getSingleResult();
        System.out.println("numero "+ numero.get(0).toString());*/
        //System.out.println("La busqueda es en local con la tabla: "+tabla);

        String consulta="  select con.*,'LOCAL' conn from " +
               tabla+" con " +
               "where   con.SERVICIO = '"+servicio+"' ";
               if(!autobus.equals("%"))
                //   consulta  = consulta +"and   con.AUTOBUS  like '"+autobus+"' or con.AUTOBUS is null  ";
               //else
                   consulta  = consulta +"and   con.AUTOBUS  like '"+autobus+"'  ";
               if(!operador.equals("%"))
                   //consulta  = consulta+ "and   con.OPERADOR like '"+operador+"' or con.OPERADOR is null  ";
               //else
                   consulta  = consulta+ "and   con.OPERADOR like '"+operador+"' ";
               consulta  = consulta+"and   con.ORIGEN   = '"+origen+"' " +
               "and   con.DESTINO   like '"+destino+"' " +
               //"and   con.EMPRESA   like '"+empresa+"' " +
               //"and   con.RUTA_ID in (53,49)" +
               "and ("+ rutas+")"+
               "and   to_date(con.FECHA_CORRIDA,'DD/MM/RRRR') between to_date('"+fi+" 00:00','DD/MM/RRRR HH24:MI') and to_date('"+ff+" 23:59','DD/MM/RRRR HH24:MI') " +
               "and   con.HORA_CORRIDA  between   '"+hi+"' and '23:59' " ;
               if(extras)
                   consulta  = consulta+ "and con.TIPO_CORRIDA = 'E' ";
               consulta  = consulta+ "order by con.SERVICIO asc " +
               ",to_date(con.FECHA_CORRIDA,'DD/MM/RRRR') " +
               ",con.HORA_CORRIDA asc";
       System.out.println("Conuslta(buscarDatos): "+consulta);
       return em.createNativeQuery(consulta).getResultList();
   }

    public Object parametrosEmpresas(){
        String consulta = "select empre.EMPRESA_NOMBRE, empre.DESCRIPCION,  empreparam.PARAMETRO_VALOR " +
                "from  tms_empresas_tbl empre " +
                ",tms_parametros_config_tbl param " +
                ",tms_empresa_parametros_tbl empreparam " +
                "where   param.PARAMETRO_CODIGO = 'P_VLRPERREC' " +
                "and   empreparam.EMPRESA_ID = empre.EMPRESA_ID " +
                "and   empreparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID"; 
        return em.createNativeQuery(consulta).getResultList();
    }

  public String buscaPermisoIngreso(String usuarioNumero, String funcion){
        String respuesta = "";
        Vector resultado;
        Vector usrfuncion;
        String consultaUsuario;
        String consultaUsuarioFuncion;
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
             usrfuncion = (Vector) em.createNativeQuery(consultaUsuarioFuncion).getResultList();
             if(usrfuncion.size()==0)
               respuesta = "no";
             else
               respuesta = "si";  
    return respuesta;
  } 

public Vector buscaRutasPerfil(long usuarioId){
    String rutas ="";
    String qry = "select DISTINCT  pr.RUTA_ID, pr.EMPRESA_ID, e.empresa_nombre  from TMS_PERFILES_RUTAS_TBL pr, tms_empresas_tbl e  where pr.PERFIL_ID in ( select up.PERFIL_ID from TMS_USUARIO_PERFILES_TBL up where up.USUARIO_ID = "+usuarioId+") and e.empresa_id = pr.EMPRESA_ID order by pr.ruta_id";
//    Vector vv = (Vector)em.createNativeQuery(qry).getResultList();
    return (Vector)em.createNativeQuery(qry).getResultList();
    
    //System.out.println("entra a buscaRutasPerfil...");
    //System.out.println("VECTOR "+vv.size());
    /*VAGL10102011
    String qry1 = "SELECT empresa_nombre FROM TMS_EMPRESAS_TBL order by empresa_id";
    Vector vv1 = (Vector)em.createNativeQuery(qry1).getResultList();
     Vector temp = new Vector();
     *
     */
    /*System.out.println("Vector emp" + vv1);
    System.out.println("Vector emp" + vv1.get(0).toString());*/
    
    /*temp = (Vector) vv1.get(0);
    System.out.println("Temp de 0 " + temp.get(0).toString());
    temp = (Vector) vv1.get(1);
    System.out.println("Temp de 1 " + temp.get(0).toString());*/
    
//    if(vv.size()==0)
//        return "-1";
//    else
//    {
//        /*System.out.println("Vector " +vv);
//        System.out.println("Vector de 0 " + vv.get(0).toString());
//        System.out.println("Vector de 1 " + vv.get(1).toString());*/
//
//        /* VAGL10102011
//        for(int i=0; i<vv.size();i++)
//        {
//          Vector v = (Vector)vv.get(i);
//          temp = (Vector) vv1.get(Integer.parseInt(v.get(1).toString()) -1);
//    //      System.out.println("Temp("+i+")= "+temp.get(0).toString());
//          if(i==0)
//              rutas = rutas + "(con.ruta_id = " +v.get(0).toString() + " and con.empresa = '"+temp.get(0).toString()+"')";
//          else
//              //rutas = rutas + ","+v.get(0).toString();
//              rutas = rutas + " or (con.ruta_id = "+ v.get(0).toString() + " and con.empresa = '"+temp.get(0).toString()+"')";
//        }
//
//        //System.out.println("Rutas "+rutas);
//        */
//        Vector completo = new Vector();
//        Vector rut = new Vector();
//
//        for(int i=0; i<vv.size();i++)
//        {
//            Vector v = (Vector)vv.get(i);
//            boolean existe = false;
//            for(int j=0; j<completo.size();j++)
//            {
//                Vector vc=(Vector)completo.get(j);
//                if(v.get(2).toString().equals(vc.get(0).toString()))
//                {
//                    ((Vector) vc.get(1)).add(v.get(0).toString());
//                    existe = true;
//                    break;
//                }
//            }
//            if(!existe)
//            {
//                Vector nv = new Vector();
//                Vector nvr = new Vector();
//                nvr.add(v.get(0).toString());
//                nv.add(v.get(2).toString());
//                nv.add(nvr);
//                completo.add(nv);
//            }
//
//        }
//        System.out.println("Vector Rutas "+rutas);
//        String rutas2 = "";
//
//        for(int k=0; k<completo.size();k++)
//        {
//            String r="";
//            Vector vc=(Vector)completo.get(k);
//            Vector vr=(Vector)vc.get(1);
//            for(int m=0; m<vr.size();m++)
//            {
//                if (m==0)
//                    r = r +vr.get(m).toString();
//                else
//                    r = r + ","+vr.get(m).toString();
//            }
//            if(k==0)
//                rutas2="(con.empresa = '"+vc.get(0).toString()+"' and  con.ruta_id in ("+r+")) \n";
//            else
//                rutas2=rutas2+" or (con.empresa = '"+vc.get(0).toString()+"' and  con.ruta_id in ("+r+")) \n";
//        }
//        System.out.println("Rutas2 "+rutas2);
//        return rutas2;
//    }

}

    public Vector buscaDestinos(){
        return (Vector)em.createNativeQuery("SELECT e.ESTADO_NOMBRE FROM TMS_ESTADOS_TBL e WHERE e.TIPO_COMPONENTE = 'TERMINAL' ORDER BY  e.ESTADO_NOMBRE").getResultList();
    }
}
