    /*
 * TmsVariosFacade.java
 *
 * Created on 10 de septiembre de 2007, 09:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare.solicitud;



import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmslecturadatafare.entidad.TmsEmpresasTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsVariosFacade implements tmslecturadatafare.solicitud.TmsVariosFacadeRemote {
   
    @PersistenceContext(unitName="TMSLecturaDATAFARE-ejbCENTRAL")
    private EntityManager em;
    
    /**
     * Creates a new instance of TmsVariosFacade
     */
    public TmsVariosFacade() {
    }
    
    public Object BuscaTarjetasPendientes(String claveOper){
    String query ="SELECT tmsTar.FOLIO_TARJETA " +
            ",tmsSer.SERVICIO_NOMBRE  " +
            ",tmsEstO.ESTADO_NOMBRE  " +
            ",tmsEstD.ESTADO_NOMBRE  " +
            ",tmsTar.AUTOBUS   " +
            ",to_char(tmsCor.FECHA_HORA_CORRIDA,'DD/MM/RRRR') fecha " +
            ",to_char(tmsCor.FECHA_HORA_CORRIDA,'HH24:MI') hora " +
            "FROM TMS_CORRIDAS_TBL    tmsCor " +
            ",TMS_ESTADOS_CORRIDA_TBL  tmsEdoCor  " +
            ",TMS_TARJETAS_VIAJE_TBL   tmsTar  " +
            ",TMS_ESTADOS_TBL    tmsEstO " +
            ",TMS_ESTADOS_TBL    tmsEstD  " +
            ",TMS_SERVICIOS_TBL  tmsSer  " +
            ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL plantilla  " +
            ",TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar   " +
            "WHERE tmsCor.CORRIDA_ID  = tmsTar.CORRIDA_ID  " +
            "and tmsCor.ORIGEN_ID   = tmsEstO.ESTADO_ID  " +
            "AND tmsCor.DESTINO_ID  = tmsEstD.ESTADO_ID  " +
            "AND tmsCor.SERVICIO_ID = tmsSer.SERVICIO_ID  " +
            "AND tmsCor.PLANTILLA_ID = plantilla.PLANTILLA_ENC_ID  " +
            "AND tmsEstTar.NOMBRE_ESTADO = 'ABIERTA' " +
            "AND tmsEdoCor.NOMBRE_ESTADO = 'DESPACHADA' " +
            "AND tmsTar.ESTADO_TARJETA_ID = tmsEstTar.ESTADO_TARJETA_VIAJE_ID " +
            "AND tmsCor.ESTADO_CORRIDA_ID = tmsEdoCor.ESTADO_CORRIDA_ID " +
            "AND tmsTar.OPERADOR =  '"+claveOper+"' " +
            "order by tmsSer.SERVICIO_NOMBRE " +
            ",tmsCor.FECHA_HORA_CORRIDA ";     
        return em.createNativeQuery(query).getResultList();
    }
    
    
    public Object BuscaCasetasTramo(String servicio, String origen, String destino){
        String query = "select cas.CASETA_NUMERO, cas.CASETA_COSTO from " +
                "tms_servicios_tbl ser " +
                ",tms_rutas_tbl		rut " +
                ",tms_tramos_tbl	tram " +
                ",tms_casetas_tbl	cas " +
                ",tms_tramos_casetas_tbl tracas " +
                ",tms_estados_tbl		 ori " +
                ",tms_estados_tbl		 des " +
                " where ser.SERVICIO_NOMBRE = '"+servicio+"'" +
                " and   rut.SERVICIO_ID = ser.SERVICIO_ID " +
                " and   tram.RUTA_ID = rut.RUTA_ID " +
                " and   ori.ESTADO_NOMBRE = '"+origen+"'" +
                " and   des.ESTADO_NOMBRE = '"+destino+"'" +
                " and   tram.TRAMO_ORIGEN_ID = ori.ESTADO_ID " +
                " and   tram.TRAMO_DESTINO_ID = des.ESTADO_ID " +
                " and   tracas.TRAMO_ID = tram.TRAMO_ID " +
                " and   tracas.CASETA_ID = cas.CASETA_ID ";
        return em.createNativeQuery(query).getResultList();
    }
    
    public Object BuscaKilometrosTramo(String servicio, String origen, String destino){
        String query = "select rut.DISTANCIA_RECORRIDO from " +
                " tms_servicios_tbl ser " +
                ",tms_rutas_tbl		rut " +
                ",tms_tramos_tbl	tram " +
                ",tms_estados_tbl		 ori" +
                ",tms_estados_tbl		 des" +
                " where ser.SERVICIO_NOMBRE = '"+servicio+"'" +
                " and   rut.SERVICIO_ID = ser.SERVICIO_ID " +
                " and   tram.RUTA_ID = rut.RUTA_ID " +
                " and   ori.ESTADO_NOMBRE = '"+origen+"'" +
                " and   des.ESTADO_NOMBRE = '"+destino+"'" +
                " and   tram.TRAMO_ORIGEN_ID = ori.ESTADO_ID " +
                " and   tram.TRAMO_DESTINO_ID = des.ESTADO_ID ";
        return em.createNativeQuery(query).getResultList();
    }
    
    
    public Object actualizarImportes(String claveCorrida){
        String query = "select count(bol.BOLETO_ID)" +
                ", sum(bol.IMPORTE_BOLETO) from " +
                "tms_boletos_venta_tbl bol " +
                "where bol.TIPO_OPERACION IN('VT','VA','HO','AC') " +
                " and bol.CLAVE_CORRIDA = '"+claveCorrida+"' " +
                "and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID ) ";
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

  
  public Object buscaCortePorUsuario(String empresa, long usuarioId){
      String consulta = "select cor.CORTE_ID from" +
              " tms_cortes_tbl cor" +
              " where    cor.NOMBRE_SESION = '"+empresa+"' " +
              " and cor.ESTADO_CORTE = 'P'" +
              " and cor.CREADO_POR = "+usuarioId;
      return em.createNativeQuery(consulta).getResultList();
  }
  
  
/*
* Query para buscar las funciones de un Menu por medio del MENU_ID
*/
  public Object buscarFuncionesPorMenuId(long menuId){
    String consulta = "select fun.FUNCION_NUMERO, fun.AUDITABLE from " +
          " tms_funciones_tbl 		  fun " +
          ",tms_menus_encabezado_tbl men " +
          ",tms_menus_lineas_tbl     mlin " +
          " where   men.MENU_ID = "+menuId+
          " and 	mlin.MENU_ID = men.MENU_ID" +
          " and 	fun.FUNCION_ID = mlin.FUNCION_ID";
  return em.createNativeQuery(consulta).getResultList();
  }
  
   public Object queryBuscaTerminalId(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }     
   
   public Object queryBuscaNumeroPaqueteActual(){
       String qr = "SELECT NVL(MAX(to_number(tmssa.VALOR_ACTIVIDAD)),0) ULTIMO_FOLIO "+
		      "FROM TMS_CORTES_TBL tmsco"+
		      ",TMS_SESION_ACTIVIDADES_TBL tmssa"+
	              ",TMS_ACTIVIDADES_SESION_TBL tmsact"+
		      " WHERE tmsco.CORTE_ID = tmssa.CORTE_ID"+
   		      //"	AND tmsco.NOMBRE_SESION = 'R_AMPERSA'"+
                      " AND ( tmsco.NOMBRE_SESION = 'R_AMPERSA' OR tmsco.NOMBRE_SESION ='R_SUPERRAPIDOS') "+  
   		      " AND tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID "+
   		      "	AND (tmsact.CLAVE_ACTIVIDAD = 'RECOL' or tmsact.CLAVE_ACTIVIDAD = 'CORTE') "+
		      " AND TO_CHAR(tmssa.FECHA_CREACION,'DD/MM/RR') = TO_CHAR(SYSDATE,'DD/MM/RR')";
           return  em.createNativeQuery(qr).getSingleResult();
   }   
  
    public Object queryBuscaIdEmpresaByNombre(Object nombre){
       return   em.createNativeQuery("select o.EMPRESA_ID from tms_empresas_tbl o where o.EMPRESA_NOMBRE_CORTO = '"+nombre+"'").getSingleResult();
    }
  
  
   public Object queryBuscaValorActualPago(){
       return   em.createNativeQuery("select  NVL(max(o.RECAUDACION_ID),0) from tms_recaudacion_tbl o").getSingleResult();
   }
   
       
   public Object  buscaRegistroPagoPorDia(long gastoId, String operaddor, String fecha){
      String conuslta = "select count(rec.RECAUDACION_ID) from " +
               "tms_recaudacion_tbl rec " +
               ",tms_recaudacion_lineas_tbl reclin " +
               ",tms_tarjetas_viaje_tbl tar " +
               ",tms_corridas_tbl cor " +
               ",tms_operadores_tbl oper " +
               "where reclin.RECAUDACION_GASTO_ID = "+gastoId+" " +
               "and  oper.CLAVE_OPERADOR = '"+operaddor+"' " +
               "and  rec.ESTADO_RECAUDACION = 'R' "+
               "and  reclin.RECAUDACION_ID = rec.RECAUDACION_ID " +
               "and  rec.TARJETA_VIAJE_ID = tar.TARJETA_VIAJE_ID " +
               "and  tar.CORRIDA_ID = cor.CORRIDA_ID " +
               "and  cor.OPERADOR_ID = oper.OPERADOR_ID " +
               "and  to_char(rec.FECHA_HORA_RECAUDACION,'dd/mm/yyyy') = '"+fecha+"'";
      return   em.createNativeQuery(conuslta).getResultList();
   }
   
///////// Lectura de tarjeta DATAFARE ////////////////

  /**
   *Query que busca el nombre corto y el Origen de un servicio por su numero de servicio
   **/
    public List<Object[]> queryBuscaOrigenServicioPorNumero(String numserv){
//       String query = "Select tmss.SERVICIO_NOMBRE " +
//               " ,tmse.ESTADO_NOMBRE " +
//               " ,tmss.SERVICIO_CLAVE " +
//               " from	 tms_servicios_tbl tmss " +
//               " ,tms_rutas_tbl ruta " +
//               " ,tms_estados_tbl   tmse " +
//               " where ruta.RUTA_NUMERO = '"+numserv+"' " +
//               " and   tmss.SERVICIO_ID = ruta.SERVICIO_ID " +
//               " and      tmse.ESTADO_ID = ruta.TRAMO_ORIGEN_ID";
        String query = "Select tmss.SERVICIO_NOMBRE  " +
                ",tmse.ESTADO_NOMBRE  " +
                ",tmss.SERVICIO_CLAVE  " +
                ",tmsd.ESTADO_NOMBRE " +
                "from	 tms_servicios_tbl tmss  " +
                ",tms_rutas_tbl ruta  " +
                ",tms_estados_tbl   tmse  " +
                ",tms_estados_tbl   tmsd  " +
                "where ruta.RUTA_NUMERO = '"+numserv+"' " +
                "and   tmss.SERVICIO_ID = ruta.SERVICIO_ID  " +
                "and  tmss.SERVICIO_NOMBRE = 'INTERMEDIO'  " +
                "and      tmse.ESTADO_ID = ruta.TRAMO_ORIGEN_ID " +
                "and      tmsd.ESTADO_ID = ruta.TRAMO_DESTINO_ID ";	
        return   em.createNativeQuery(query).getResultList();
       }

 /**
   *Query que busca el nombre corto y el Destino de un servicio por su numero de servicio
   **/
public List<Object[]> queryBuscaDestinoServicioPorNumero(String numserv){
//       String query = "Select tmss.SERVICIO_NOMBRE " +
//               " ,tmse.ESTADO_NOMBRE " +
//               " ,tmss.SERVICIO_CLAVE " +
//               " from	 tms_servicios_tbl tmss " +
//               " ,tms_rutas_tbl ruta " +
//               " ,tms_estados_tbl   tmse " +
//               " where ruta.RUTA_NUMERO = '"+numserv+"' " +
//               " and   tmss.SERVICIO_ID = ruta.SERVICIO_ID " +
//               " and      tmse.ESTADO_ID = ruta.TRAMO_DESTINO_ID";
    String query = "Select tmss.SERVICIO_NOMBRE " +
            ",tmse.ESTADO_NOMBRE  " +
            ",tmss.SERVICIO_CLAVE  " +
            ",tmsd.ESTADO_NOMBRE  " +
            "from	 tms_servicios_tbl tmss  " +
            ",tms_rutas_tbl ruta  " +
            ",tms_estados_tbl   tmse  " +
            ",tms_estados_tbl   tmsd  " +
            "where ruta.RUTA_NUMERO = '"+numserv+"' " +
            "and   tmss.SERVICIO_ID = ruta.SERVICIO_ID  " +
            "and   tmss.SERVICIO_NOMBRE = 'INTERMEDIO'  " +
            "and      tmse.ESTADO_ID = ruta.TRAMO_DESTINO_ID " +
            "and      tmsd.ESTADO_ID = ruta.TRAMO_ORIGEN_ID ";
        return   em.createNativeQuery(query).getResultList();
       }    

/**
*Query para obtener la lista ciudades de la tabla de estados 
**/
public List<Object[]> queryBuscaCiudades(){
   String query = "select tmse.ESTADO_NOMBRE"+
                  " ,NVL(tmse.CLAVE_ESTADO,'NV') " +
                  " ,tmse.ESTADO_ID "+
                  " from	 tms_estados_tbl tmse"+
                  " where (tmse.TIPO_COMPONENTE = 'CIUDAD' OR tmse.TIPO_COMPONENTE = 'TERMINAL')";
    return   em.createNativeQuery(query).getResultList();
   } 

 public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return em.createNativeQuery(consulta).getSingleResult();
 }

 
   public Object queryBuscaIdTerminal(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }    
 
/////////////////////////////////////////////////////   

public Object buscaEstadoPorId(long idEdo){
    return em.createNativeQuery("select est.ESTADO_NOMBRE from tms_estados_tbl est where est.ESTADO_ID = "+idEdo).getResultList();
}

public Object queryBuscaTerminalLocal(){
   return   em.createNativeQuery("select  est.ESTADO_ID from tms_base_datos_config_tbl base ,tms_estados_tbl est where esquema_propio = 'S' and est.ESTADO_NOMBRE = base.nombre_terminal" ).getSingleResult();
  } 

public Object queryBuscaOperadorPorClave(String clave){
   return   em.createNativeQuery("select oper.OPERADOR_NOMBRE, oper.OPERADOR_NOMBRE_MEDIO, oper.OPERADOR_APELLIDO from tms_operadores_tbl oper where oper.CLAVE_OPERADOR = '"+clave+"'" ).getResultList();
  }

public Object queryBuscaTodasTarjetaViaje(String viaje){
//   String consulta = "select tar.FOLIO_TARJETA " +
//           "from  " +
//           "tms_tarjetas_viaje_tbl tar " +
//           ",tms_estados_tarjeta_viaje_tbl est " +
//           "where tar.FOLIO_TARJETA LIKE '"+viaje+"%' " +
//           "and est.NOMBRE_ESTADO = 'ABIERTA' OR est.NOMBRE_ESTADO = 'CANCELADA' " +
//           "and tar.ESTADO_TARJETA_ID = est.ESTADO_TARJETA_VIAJE_ID";
String consulta = "select tar.FOLIO_TARJETA  " +
        "from   " +
        "tms_tarjetas_viaje_tbl tar  " +
        ",tms_estados_tarjeta_viaje_tbl est  " +
        ",tms_corridas_venta_tbl cor  " +
        "where tar.FOLIO_TARJETA LIKE '"+viaje+"%' " +
        "and tar.CORRIDA_ID = cor.CORRIDA_ID  " +
        "and cor.SERVICIO = 'INTERMEDIO'  " +
        "and (est.NOMBRE_ESTADO = 'ABIERTA' OR est.NOMBRE_ESTADO = 'CANCELADA')  " +
        "and tar.ESTADO_TARJETA_ID = est.ESTADO_TARJETA_VIAJE_ID " +
        "ORDER BY tar.FOLIO_TARJETA	 ";    
    return   em.createNativeQuery(consulta ).getResultList();
  }

public Object queryBuscaTarjetasViajePorOperador(String viaje, String operador){
   String consulta = "select tar.FOLIO_TARJETA " +
           "from " +
           "tms_tarjetas_viaje_tbl tar " +
           ",tms_estados_tarjeta_viaje_tbl est " +
           ",tms_corridas_venta_tbl cor " +
           "where tar.FOLIO_TARJETA LIKE '"+viaje+"%' " +
           "and tar.OPERADOR = '"+operador+"'	" +
           "and tar.CORRIDA_ID = cor.CORRIDA_ID " +
           "and cor.SERVICIO = 'INTERMEDIO' " +
           "and (est.NOMBRE_ESTADO = 'ABIERTA' OR est.NOMBRE_ESTADO = 'CANCELADA') " +
           "and tar.ESTADO_TARJETA_ID = est.ESTADO_TARJETA_VIAJE_ID " +
           "ORDER BY tar.FOLIO_TARJETA	";
    return   em.createNativeQuery(consulta ).getResultList();
  }

public Object queryBuscaTarjetasViajePorOperadorParaMod(String operador){
   String consulta = "select tar.FOLIO_TARJETA " +
           "from " +
           "tms_tarjetas_viaje_tbl tar " +
           ",tms_estados_tarjeta_viaje_tbl est " +
           ",tms_corridas_venta_tbl cor " +
           "where tar.OPERADOR = '"+operador+"'	" +
           "and tar.CORRIDA_ID = cor.CORRIDA_ID " +
           "and cor.SERVICIO = 'INTERMEDIO' " +
           "and (est.NOMBRE_ESTADO = 'ABIERTA' OR est.NOMBRE_ESTADO = 'CANCELADA') " +
           "and tar.ESTADO_TARJETA_ID = est.ESTADO_TARJETA_VIAJE_ID " +
           "ORDER BY tar.FOLIO_TARJETA	";
    return   em.createNativeQuery(consulta ).getResultList();
  }

public Object queryBuscaTarjetaViaje(String viaje, String id, String opClv, String origen, String destino, String fecha){
//   String consulta = "select tar.FOLIO_TARJETA " +
//           "from  " +
//           "tms_tarjetas_viaje_tbl tar " +
//           "where tar.FOLIO_TARJETA LIKE '"+viaje+"%"+id+"' " +
//           "and tar.OPERADOR = '"+opClv+"'";
    String consulta = "select tar.FOLIO_TARJETA " +
            "from   " +
            "tms_tarjetas_viaje_tbl tar " +
            ",tms_corridas_venta_tbl cor  " +
            "where tar.FOLIO_TARJETA LIKE '"+viaje+"%"+id+"' " +
            "and tar.OPERADOR = '"+opClv+"' " +
            "and cor.CORRIDA_ID = tar.CORRIDA_ID  " +
            "and cor.ORIGEN = '"+origen+"' " +
            "and cor.DESTINO = '"+destino+"' " +
            "and cor.SERVICIO = 'INTERMEDIO'  "+
            "and to_char(cor.FECHA_HORA_CORRIDA,'DD/MM/RR') = '"+fecha+"'";
   //System.out.println("Busca la tarjeta con: "+consulta);
   return   em.createNativeQuery(consulta ).getResultList();
    
  }

public Object queryBuscaTarjetasViajePorServicio(String Servicio){
    String consulta = "select tar.FOLIO_TARJETA " +
            "from  " +
            "tms_tarjetas_viaje_tbl tar  " +
            ",tms_estados_tarjeta_viaje_TBL est  " +
            ",tms_corridas_venta_tbl cor  " +
            "where 1=1  " +
            "and cor.SERVICIO = '"+Servicio+"' " +
            "and tar.CORRIDA_ID = cor.CORRIDA_ID " +
            "and (est.NOMBRE_ESTADO = 'ABIERTA' OR est.NOMBRE_ESTADO = 'CANCELADA'  )" +
            "and tar.ESTADO_TARJETA_ID = est.ESTADO_TARJETA_VIAJE_ID " +
            "ORDER BY tar.FOLIO_TARJETA	";
  //  System.out.println("queryBuscaTarjetasViajePorServicio: "+consulta);
    return em.createNativeQuery(consulta).getResultList();
}

public Object queryBuscaImpresoraTikets(long cajaId){
   String consulta = "select impres.IMPRESORA_NOMBRE,  cajasimpres.SALIDA_IMPRESION " +
           "from " +
           "tms_cajas_tbl			cajas " +
           ",tms_impresoras_tbl		impres " +
           ",tms_cajas_impresoras_tbl cajasimpres " +
           "where cajas.CAJA_ID = "+cajaId+" " +
           "and 	cajasimpres.CAJA_ID = cajas.CAJA_ID " +
           "and   impres.IMPRESORA_ID = cajasimpres.IMPRESORA_ID " +
           "and   cajasimpres.ACTIVIDAD_IMPRESORA = 'TICKETS'";
    return   em.createNativeQuery(consulta ).getResultList();
  }

public Object buscaMontoRecoleccion(){
    String consulta = "select globparam.PARAMETRO_VALOR from " +
            "tms_parametros_config_tbl  param " +
            ",tms_global_parametros_tbl globparam " +
            "where param.PARAMETRO_TIPO = 'GLOBAL'  " +
            "and   globparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
            "and   param.PARAMETRO_CODIGO = 'P_R_RECLIM'";
   return em.createNativeQuery(consulta).getResultList();             
}

public Object buscaTopeRecoleccion(){
    String consulta = "select globparam.PARAMETRO_VALOR from " +
            "tms_parametros_config_tbl  param " +
            ",tms_global_parametros_tbl globparam " +
            "where param.PARAMETRO_TIPO = 'GLOBAL'  " +
            "and   globparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
            "and   param.PARAMETRO_CODIGO = 'P_R_RECNRE'";
   return em.createNativeQuery(consulta).getResultList();             
}


 public Object buscaMontoAcumulado(long idCorte){
    String consulta = "select NVL(sum(to_number(reclin.RECAUDACION_VALOR,'999999.99')),0) " +
            "from " +
            " tms_recaudacion_tbl    rec " +
            ",tms_recaudacion_lineas_tbl    reclin " +
            ",tms_tarjetas_viaje_tbl tar " +
            ",tms_recaudacion_gastos_tbl   gas " +
            "where rec.ESTADO_RECAUDACION = 'R' " +
            "and   rec.CORTE_ID = "+idCorte+" " +
            "and   reclin.RECAUDACION_ID = rec.RECAUDACION_ID " +
            "and   rec.TARJETA_VIAJE_ID =tar.TARJETA_VIAJE_ID " +
            "and   reclin.RECAUDACION_GASTO_ID = gas.RECAUDACION_GASTO_ID " +
            "and   gas.RECAUDACION_GASTO_AFECTA_CAJA = 'S' " +
            "and   gas.RECAUDACION_GASTO_CARGA = 'O'";
    return em.createNativeQuery(consulta).getSingleResult();
}
 
 public Object buscaMontoAcumuladoParaRestar(long idCorte){
    String consulta = "select NVL(sum(to_number(reclin.RECAUDACION_VALOR,'999999.99')),0) " +
            "from " +
            " tms_recaudacion_tbl    rec " +
            ",tms_recaudacion_lineas_tbl    reclin " +
            ",tms_tarjetas_viaje_tbl tar " +
            ",tms_recaudacion_gastos_tbl   gas " +
            "where rec.ESTADO_RECAUDACION = 'R' " +
            "and   rec.CORTE_ID = "+idCorte+" " +
            "and   reclin.RECAUDACION_ID = rec.RECAUDACION_ID " +
            "and   rec.TARJETA_VIAJE_ID =tar.TARJETA_VIAJE_ID " +
            "and   reclin.RECAUDACION_GASTO_ID = gas.RECAUDACION_GASTO_ID " +
            "and   gas.RECAUDACION_GASTO_AFECTA_CAJA = 'S' " +
            "and   gas.RECAUDACION_GASTO_CARGA = 'E'";
    return em.createNativeQuery(consulta).getSingleResult();
} 
 

 public Object buscaMontoRecolecciones(long idCorte, long numUsuario){
    String consulta = "  SELECT NVL(SUM(tmsrec.CANTIDAD * tmsrec.MONTO),0) " +
            "FROM TMS_RECOLECCIONES_TBL tmsrec   " +
            ",TMS_SESION_ACTIVIDADES_TBL tmssa   " +
            ",TMS_ACTIVIDADES_SESION_TBL tmsact  " +
            ",TMS_VTA_TIPOS_PAGO_TBL     tmspagos  " +
            ",TMS_USUARIOS_TBL  		  tmsus " +
            ",TMS_CORTES_TBL              tmsco  " +
            "WHERE tmsus.USUARIO_NUMERO = "+numUsuario+" " +
            "AND  tmssa.CREADO_POR = tmsus.USUARIO_ID " +
            "AND  tmsco.CORTE_ID =  "+idCorte+" " +
            "AND  tmspagos.NOMBRE = 'EFECTIVO' " +
            "AND  tmsact.NOMBRE_ACTIVIDAD = 'RECOLECCION' " +
            "AND  tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID " +
            "AND  tmsrec.TIPO_PAGO_ID = tmspagos.TIPO_PAGO_ID " +
            "AND  tmsrec.SESION_ACTIVIDAD_ID = tmssa.SESION_ACTIVIDAD_ID " +
            "AND  tmssa.CORTE_ID = tmsco.CORTE_ID";
    return em.createNativeQuery(consulta).getSingleResult();
}

 public Object buscaBoletosDATAFARERecolectados(long idCorte){
    String consulta = "  select NVL(sum(bol.IMPORTE_BOLETO),0) " +
            "from " +
            "tms_boletos_boletera_tbl    bol " +
            "where bol.CORTE_ID = "+idCorte;
    return em.createNativeQuery(consulta).getSingleResult();
}

 public Object buscaVentaAbordoManual(long idCorte){
    String consulta = "select  NVL(sum(to_number(rec.IMPORTE_VENTA_ABORDO,'999999.99')),0) from " +
            " tms_recaudacion_tbl    rec " +
            ",tms_tarjetas_viaje_tbl tar " +
            "where rec.ESTADO_RECAUDACION = 'R' " +
            "and   rec.CORTE_ID = "+idCorte+" " +
            "and   rec.TARJETA_VIAJE_ID =tar.TARJETA_VIAJE_ID " +
            "and   tar.FOLIO_TARJETA  not in(select distinct(FOLIO_TARJETA) from tms_boletos_boletera_tbl)";
    return em.createNativeQuery(consulta).getSingleResult();
}
 
 
   public TmsEmpresasTbl queryBuscaEmpresaPorId(BigDecimal idemp){
     TmsEmpresasTbl emp;
     emp = em.find(TmsEmpresasTbl.class, idemp);
     return emp;
 }
 
   public Object queryBuscaNombreEsquema(){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }  
 

   public Vector buscaTarjetaPorFolio(String folio){
    return  (Vector)em.createNativeQuery("select tar.FOLIO_TARJETA, cor.SERVICIO, cor.ORIGEN, cor.DESTINO, tar.AUTOBUS,to_char(cor.FECHA_HORA_CORRIDA,'DD/MM/RRRR') fecha, to_char(cor.FECHA_HORA_CORRIDA,'HH24:MI') hora  from tms_tarjetas_viaje_tbl tar, tms_corridas_venta_tbl cor where tar.FOLIO_TARJETA = '"+folio+"' and cor.SERVICIO = 'INTERMEDIO'  and tar.CORRIDA_ID = cor.CORRIDA_ID " ).getResultList();
  }
   
 public Object buscaTurnosPendientes(){
     String consulta = "select bol.adicional1, count(bol.BOLETO_BOLETERA_ID) " +
             "from tms_boletos_boletera_tbl bol  " +
             "where bol.FOLIO_TARJETA = 'PENDIENTE' "
             + " and BOL.ADICIONAL1 is not null "
             + " and BOL.ADICIONAL2 is not null " +
             "group by bol.ADICIONAL1";
     return em.createNativeQuery(consulta).getResultList();
 }

 public void actualizarBoletos(String folio, String adicional, long user){
     String query = "update tms_boletos_boletera_tbl bol set bol.FOLIO_TARJETA = '"+folio+"', bol.ULTIMA_ACTUALIZACION_POR = "+user+", bol.ULTIMA_FECHA_ACTUALIZACION = SYSDATE  where bol.ADICIONAL1 ='"+adicional+"'";
     em.createNativeQuery(query).executeUpdate();
 }

  public Object buscaSaldos(long idCorte){
    String consulta = "select NVL(sum((rec.saldo)* -1),0) from tms_recaudacion_tbl rec where rec.ESTADO_RECAUDACION = 'R' and rec.CORTE_ID = "+idCorte+" and rec.saldo < 0";
    return em.createNativeQuery(consulta).getSingleResult();
}

  // ************ Metodos VIAXER ********* //
 public Object buscaTurnosPendientesViaxer(){
     String consulta = "select bol.adicional1, count(bol.BOLETO_BOLETERA_ID) " +
             "from tms_boletos_boletera_tbl bol  " +
             "where bol.FOLIO_TARJETA = 'PENDIENTE' "
             + " and BOL.ADICIONAL1 is not null "
             + " and BOL.ADICIONAL2 is null " +
             "group by bol.ADICIONAL1";
     return em.createNativeQuery(consulta).getResultList();
 }


}

