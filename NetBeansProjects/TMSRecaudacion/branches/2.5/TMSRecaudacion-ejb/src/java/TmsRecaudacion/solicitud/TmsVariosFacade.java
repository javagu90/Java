    /*
 * TmsVariosFacade.java
 *
 * Created on 10 de septiembre de 2007, 09:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;


import TmsRecaudacion.entidad.TmsEmpresasTbl;
import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.annotation.Resource;
import javax.sql.DataSource;
import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OraclePreparedStatement;
import oracle.jdbc.internal.OracleTypes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsVariosFacade implements TmsRecaudacion.solicitud.TmsVariosFacadeRemote {
   
    @PersistenceContext(unitName="TMSRecaudacion-ejbCentral")
    private EntityManager em;

    @Resource(name = "TMS_CENTRAL_DB")
    private DataSource dataSource;

    /**
     * Creates a new instance of TmsVariosFacade
     */
    public TmsVariosFacade() {
    }
    
 public Object BuscaTarjetasPendientes(String claveOper, String autobus)
  {
    //String query = "SELECT tmsTar.FOLIO_TARJETA ,tmsSer.SERVICIO_NOMBRE ,tmsEstO.ESTADO_NOMBRE ,tmsEstD.ESTADO_NOMBRE ,tmsTar.AUTOBUS  ,tmsCor.FECHA_HORA_CORRIDA ,TO_NUMBER('0') ,tmsCor.NUMERO_CONTRATO ,tmsCor.NUMERO_ORDEN ,tmsCor.CLAVE_CORRIDA ,plantilla.CAPACIDAD_ASIENTOS ,tmsSer.SERVICIO_ID ,tmsCor.EMPRESA_ID ,tmsCor.RUTA_ID ,tmsCor.MONTO_ANTICIPOS ,tmsCor.ADICIONAL5 ,tmsTar.TARJETA_VIAJE_ID ,tmsCor.CORRIDA_ID ,tmsCor.SUELDO_OPERADOR FROM TMS_CORRIDAS_TBL    tmsCor ,TMS_ESTADOS_CORRIDA_TBL  tmsEdoCor ,TMS_TARJETAS_VIAJE_TBL   tmsTar ,TMS_ESTADOS_TBL    tmsEstO ,TMS_ESTADOS_TBL    tmsEstD ,TMS_SERVICIOS_TBL  tmsSer ,TMS_AUTOBUS_PLANTILLAS_ENC_TBL plantilla ,TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar WHERE tmsCor.CORRIDA_ID  = tmsTar.CORRIDA_ID and tmsCor.ORIGEN_ID   = tmsEstO.ESTADO_ID AND tmsCor.DESTINO_ID  = tmsEstD.ESTADO_ID AND tmsCor.SERVICIO_ID = tmsSer.SERVICIO_ID AND tmsCor.PLANTILLA_ID = plantilla.PLANTILLA_ENC_ID AND tmsEstTar.NOMBRE_ESTADO = 'ABIERTA' AND tmsEdoCor.NOMBRE_ESTADO = 'DESPACHADA' AND tmsTar.ESTADO_TARJETA_ID = tmsEstTar.ESTADO_TARJETA_VIAJE_ID AND tmsCor.ESTADO_CORRIDA_ID = tmsEdoCor.ESTADO_CORRIDA_ID AND tmsCor.EMPRESA_ID in (select empre.EMPRESA_ID from  tms_empresas_tbl empre  ,tms_parametros_config_tbl param  ,tms_empresa_parametros_tbl empreparam  where   param.PARAMETRO_CODIGO = 'P_VLRPERREC'  and   empreparam.EMPRESA_ID = empre.EMPRESA_ID  and   empreparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID and empreparam.PARAMETRO_VALOR  = 'S') ";
    // String query = "SELECT tmsTar.FOLIO_TARJETA ,tmsSer.SERVICIO_NOMBRE ,tmsEstO.ESTADO_NOMBRE ,tmsEstD.ESTADO_NOMBRE ,tmsTar.AUTOBUS  ,tmsCor.FECHA_HORA_CORRIDA ,TO_NUMBER('0') ,tmsCor.NUMERO_CONTRATO ,tmsCor.NUMERO_ORDEN ,tmsCor.CLAVE_CORRIDA ,plantilla.CAPACIDAD_ASIENTOS ,tmsSer.SERVICIO_ID ,tmsCor.EMPRESA_ID ,tmsCor.RUTA_ID ,tmsCor.MONTO_ANTICIPOS ,tmsCor.ADICIONAL5 ,tmsTar.TARJETA_VIAJE_ID ,tmsCor.CORRIDA_ID ,tmsCor.SUELDO_OPERADOR FROM TMS_CORRIDAS_TBL    tmsCor ,TMS_ESTADOS_CORRIDA_TBL  tmsEdoCor ,TMS_TARJETAS_VIAJE_TBL   tmsTar ,TMS_ESTADOS_TBL    tmsEstO ,TMS_ESTADOS_TBL    tmsEstD ,TMS_SERVICIOS_TBL  tmsSer ,TMS_AUTOBUS_PLANTILLAS_ENC_TBL plantilla ,TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar WHERE tmsCor.CORRIDA_ID  = tmsTar.CORRIDA_ID and tmsCor.ORIGEN_ID   = tmsEstO.ESTADO_ID AND tmsCor.DESTINO_ID  = tmsEstD.ESTADO_ID AND tmsCor.SERVICIO_ID = tmsSer.SERVICIO_ID AND tmsCor.PLANTILLA_ID = plantilla.PLANTILLA_ENC_ID AND tmsEstTar.NOMBRE_ESTADO = 'ABIERTA' AND tmsEdoCor.NOMBRE_ESTADO = 'DESPACHADA' AND tmsTar.ESTADO_TARJETA_ID = tmsEstTar.ESTADO_TARJETA_VIAJE_ID AND tmsCor.ESTADO_CORRIDA_ID = tmsEdoCor.ESTADO_CORRIDA_ID AND tmsCor.EMPRESA_ID in (select empre.EMPRESA_ID from  tms_empresas_tbl empre  ,tms_parametros_config_tbl param  ,tms_empresa_parametros_tbl empreparam  where   param.PARAMETRO_CODIGO = 'P_VLRPERREC'  and   empreparam.EMPRESA_ID = empre.EMPRESA_ID  and   empreparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID and empreparam.PARAMETRO_VALOR  = 'S') AND (select NVL(R.ADICIONAL5,'N') from tms_rutas_tbl  r where R.RUTA_ID =TMSCOR.RUTA_ID) = 'N' ";
    //VAGL 21/03/2014 Se agrega el filtro para que no traiga las tarjetas que se recaudan automaticamente
     String query = "SELECT tmsTar.FOLIO_TARJETA ,tmsSer.SERVICIO_NOMBRE ,tmsEstO.ESTADO_NOMBRE ,tmsEstD.ESTADO_NOMBRE ,tmsTar.AUTOBUS  ,tmsCor.FECHA_HORA_CORRIDA ,TO_NUMBER('0') ,tmsCor.NUMERO_CONTRATO ,tmsCor.NUMERO_ORDEN ,tmsCor.CLAVE_CORRIDA ,plantilla.CAPACIDAD_ASIENTOS ,tmsSer.SERVICIO_ID ,tmsCor.EMPRESA_ID ,tmsCor.RUTA_ID ,tmsCor.MONTO_ANTICIPOS ,tmsCor.ADICIONAL5 ,tmsTar.TARJETA_VIAJE_ID ,tmsCor.CORRIDA_ID ,tmsCor.SUELDO_OPERADOR FROM TMS_CORRIDAS_TBL    tmsCor ,TMS_ESTADOS_CORRIDA_TBL  tmsEdoCor ,TMS_TARJETAS_VIAJE_TBL   tmsTar ,TMS_ESTADOS_TBL    tmsEstO ,TMS_ESTADOS_TBL    tmsEstD ,TMS_SERVICIOS_TBL  tmsSer ,TMS_AUTOBUS_PLANTILLAS_ENC_TBL plantilla ,TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar WHERE tmsCor.CORRIDA_ID  = tmsTar.CORRIDA_ID and tmsCor.ORIGEN_ID   = tmsEstO.ESTADO_ID AND tmsCor.DESTINO_ID  = tmsEstD.ESTADO_ID AND tmsCor.SERVICIO_ID = tmsSer.SERVICIO_ID AND tmsCor.PLANTILLA_ID = plantilla.PLANTILLA_ENC_ID AND tmsEstTar.NOMBRE_ESTADO = 'CONFIRMADA' AND tmsEdoCor.NOMBRE_ESTADO = 'DESPACHADA' AND tmsTar.ESTADO_TARJETA_ID = tmsEstTar.ESTADO_TARJETA_VIAJE_ID AND tmsCor.ESTADO_CORRIDA_ID = tmsEdoCor.ESTADO_CORRIDA_ID AND tmsCor.EMPRESA_ID in (select empre.EMPRESA_ID from  tms_empresas_tbl empre  ,tms_parametros_config_tbl param  ,tms_empresa_parametros_tbl empreparam  where   param.PARAMETRO_CODIGO = 'P_VLRPERREC'  and   empreparam.EMPRESA_ID = empre.EMPRESA_ID  and   empreparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID and empreparam.PARAMETRO_VALOR  = 'S') AND (select NVL(R.ADICIONAL5,'N') from tms_rutas_tbl  r where R.RUTA_ID =TMSCOR.RUTA_ID) = 'N' ";
    if (claveOper.equals(""))
    {
      query = query + " AND tmsTar.AUTOBUS  = '" + autobus + "'" + " order by tmsSer.SERVICIO_NOMBRE\t                ";
    }
    else if (autobus.equals("")) {
      query = query + " AND tmsTar.OPERADOR =  '" + claveOper + "'" + " order by tmsSer.SERVICIO_NOMBRE\t                ";
    }
    else
    {
      query = query + " AND tmsTar.OPERADOR =  '" + claveOper + "'" + " AND tmsTar.AUTOBUS  = '" + autobus + "'" + " order by tmsSer.SERVICIO_NOMBRE\t                ";
    }

    return this.em.createNativeQuery(query).getResultList();
  }

  public Object BuscaCasetasTramo(String servicio, String origen, String destino)
  {
    String query = "select cas.CASETA_NUMERO, cas.CASETA_COSTO from tms_servicios_tbl ser ,tms_rutas_tbl\t\trut ,tms_tramos_tbl\ttram ,tms_casetas_tbl\tcas ,tms_tramos_casetas_tbl tracas ,tms_estados_tbl\t\t ori ,tms_estados_tbl\t\t des  where ser.SERVICIO_NOMBRE = '" + servicio + "'" + " and   rut.SERVICIO_ID = ser.SERVICIO_ID " + " and   tram.RUTA_ID = rut.RUTA_ID " + " and   ori.ESTADO_NOMBRE = '" + origen + "'" + " and   des.ESTADO_NOMBRE = '" + destino + "'" + " and   tram.TRAMO_ORIGEN_ID = ori.ESTADO_ID " + " and   tram.TRAMO_DESTINO_ID = des.ESTADO_ID " + " and   tracas.TRAMO_ID = tram.TRAMO_ID " + " and   tracas.CASETA_ID = cas.CASETA_ID ";

    return this.em.createNativeQuery(query).getResultList();
  }

  public Object BuscaKilometrosTramo(String servicio, String origen, String destino) {
    String query = "select rut.DISTANCIA_RECORRIDO from  tms_servicios_tbl ser ,tms_rutas_tbl\t\trut ,tms_tramos_tbl\ttram ,tms_estados_tbl\t\t ori,tms_estados_tbl\t\t des where ser.SERVICIO_NOMBRE = '" + servicio + "'" + " and   rut.SERVICIO_ID = ser.SERVICIO_ID " + " and   tram.RUTA_ID = rut.RUTA_ID " + " and   ori.ESTADO_NOMBRE = '" + origen + "'" + " and   des.ESTADO_NOMBRE = '" + destino + "'" + " and   tram.TRAMO_ORIGEN_ID = ori.ESTADO_ID " + " and   tram.TRAMO_DESTINO_ID = des.ESTADO_ID ";

    return this.em.createNativeQuery(query).getResultList();
  }

  public Object actualizarImportes(String claveCorrida)
  {
    String query = "select count(distinct(bol.BOLETO_ID)) ,NVL(sum( bol.IMPORTE_BOLETO),0) from tms_boletos_venta_tbl bol ,tms_corridas_tbl cor  ,tms_corridas_tbl correla  where bol.TIPO_OPERACION IN('VT','VA','HO','AC','FT','FO','FC')  and cor.CLAVE_CORRIDA= '" + claveCorrida + "' " + "and correla.CORRIDA_RELACIONADA_ID(+) = cor.CORRIDA_ID " + "and bol.CLAVE_CORRIDA in (cor.CLAVE_CORRIDA,correla.CLAVE_CORRIDA) " + "and bol.TIPO_TRANSACCION = 'L' " + "and bol.FOLIO_PREIMPRESO not in(select bol2.FOLIO_PREIMPRESO from tms_boletos_venta_tbl bol2 where bol2.TIPO_TRANSACCION = 'L' and bol2.TIPO_OPERACION = 'CN')";

    return this.em.createNativeQuery(query).getResultList();
  }

  public Object consultaImportePorCorrida(long corridaid)
  {
    return this.em.createNativeQuery("select Xer_Tms_Pkg.TMS_VENTA_NETA_CORRIDA_FNC(" + corridaid + ") from dual").getSingleResult();
  }

  public Object consultaOcupacionPorCorrida(long corridaid) {
    return this.em.createNativeQuery("select Xer_Tms_Pkg.TMS_PASAJEROS_NETO_CORRIDA_FNC(" + corridaid + ") from dual").getSingleResult();
  }

  public Object fechaServidor() {
    String consulta = "SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') FROM DUAL";
    try
    {
      return this.em.createNativeQuery(consulta).getSingleResult(); } catch (NoResultException nre) {
    }
    return null;
  }

  public TimeZone getTimeZone()
  {
    return TimeZone.getDefault();
  }

  public Object buscaCortePorUsuario(String empresa, long usuarioId) {
    String consulta = "select cor.CORTE_ID from tms_cortes_tbl cor where    cor.NOMBRE_SESION = '" + empresa + "' " + " and cor.ESTADO_CORTE = 'P'" + " and cor.CREADO_POR = " + usuarioId;

    return this.em.createNativeQuery(consulta).getResultList();
  }

  public Object buscarFuncionesPorMenuId(long menuId)
  {
    String consulta = "select fun.FUNCION_NUMERO, fun.AUDITABLE from  tms_funciones_tbl \t\t  fun ,tms_menus_encabezado_tbl men ,tms_menus_lineas_tbl     mlin  where   men.MENU_ID = " + menuId + " and \tmlin.MENU_ID = men.MENU_ID" + " and \tfun.FUNCION_ID = mlin.FUNCION_ID";

    return this.em.createNativeQuery(consulta).getResultList();
  }

  public Object queryBuscaNumeroPaqueteActual()
  {
    String qr = "SELECT NVL(MAX(to_number(tmssa.VALOR_ACTIVIDAD)),0) ULTIMO_FOLIO FROM TMS_CORTES_TBL tmsco,TMS_SESION_ACTIVIDADES_TBL tmssa,TMS_ACTIVIDADES_SESION_TBL tmsact WHERE tmsco.CORTE_ID = tmssa.CORTE_ID AND ( tmsco.NOMBRE_SESION = 'R_AMPERSA' OR tmsco.NOMBRE_SESION ='R_SUPERRAPIDOS')  AND tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID \tAND (tmsact.CLAVE_ACTIVIDAD = 'RECOL' or tmsact.CLAVE_ACTIVIDAD = 'CORTE')  AND TO_CHAR(tmssa.FECHA_CREACION,'DD/MM/RR') = TO_CHAR(SYSDATE,'DD/MM/RR')";

    return this.em.createNativeQuery(qr).getSingleResult();
  }

  public Object queryBuscaIdEmpresaByNombre(Object nombre) {
    return this.em.createNativeQuery("select o.EMPRESA_ID from tms_empresas_tbl o where o.EMPRESA_NOMBRE_CORTO = '" + nombre + "'").getSingleResult();
  }

  public TmsEmpresasTbl queryBuscaEmpresaPorId(BigDecimal idemp)
  {
    TmsEmpresasTbl emp = (TmsEmpresasTbl)this.em.find(TmsEmpresasTbl.class, idemp);
    return emp;
  }

  public Object queryBuscaValorActualPago() {
    return this.em.createNativeQuery("select  NVL(max(o.RECAUDACION_REFERENCIA),0) from tms_recaudacion_tbl o").getSingleResult();
  }

  public Object buscaRegistroPagoPorDia(long gastoId, String operaddor, String fecha)
  {
    String conuslta = "select count(rec.RECAUDACION_ID) from tms_recaudacion_tbl rec ,tms_recaudacion_lineas_tbl reclin ,tms_tarjetas_viaje_tbl tar where tar.OPERADOR = '" + operaddor + "' " + "and  reclin.RECAUDACION_GASTO_ID = " + gastoId + " " + "and  rec.ESTADO_RECAUDACION = 'R'  " + "and  reclin.RECAUDACION_ID = rec.RECAUDACION_ID  " + "and  rec.TARJETA_VIAJE_ID = tar.TARJETA_VIAJE_ID " + "and  to_char(rec.FECHA_HORA_RECAUDACION,'dd/MM/yyyy') = '" + fecha + "'";

    return this.em.createNativeQuery(conuslta).getResultList();
  }

  public int buscaNumTarRecPorOperador(String claveOper) {
    int numTar = 0;
    Vector vnt = (Vector)this.em.createNativeQuery("").getSingleResult();
    return numTar;
  }

  public Object queryBuscaIdTerminal() {
    return this.em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'").getSingleResult();
  }

  public Object buscaMontoGastosPorTarjeta(String folioTar)
  {
    String consulta = "select NVL(sum(to_number(reclin.RECAUDACION_VALOR,'999999.99')),0) from tms_recaudacion_tbl    rec ,tms_recaudacion_lineas_tbl    reclin ,tms_tarjetas_viaje_tbl tar ,tms_recaudacion_gastos_tbl   gas where rec.ESTADO_RECAUDACION = 'R' and   reclin.RECAUDACION_ID = rec.RECAUDACION_ID and   tar.FOLIO_TARJETA = '" + folioTar + "' " + "and   rec.TARJETA_VIAJE_ID = tar.TARJETA_VIAJE_ID " + "and   reclin.RECAUDACION_GASTO_ID = gas.RECAUDACION_GASTO_ID " + "and   gas.RECAUDACION_GASTO_AFECTA_CAJA = 'S' " + "and   gas.RECAUDACION_GASTO_CARGA = 'O'";

    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object buscaMontoGastosPorTarjetaParaRestar(String folioTar)
  {
    String consulta = "select NVL(sum(to_number(reclin.RECAUDACION_VALOR,'999999.99')),0) from tms_recaudacion_tbl    rec ,tms_recaudacion_lineas_tbl    reclin ,tms_tarjetas_viaje_tbl tar ,tms_recaudacion_gastos_tbl   gas where rec.ESTADO_RECAUDACION = 'R' and   reclin.RECAUDACION_ID = rec.RECAUDACION_ID and   tar.FOLIO_TARJETA = '" + folioTar + "' " + "and   rec.TARJETA_VIAJE_ID = tar.TARJETA_VIAJE_ID " + "and   reclin.RECAUDACION_GASTO_ID = gas.RECAUDACION_GASTO_ID " + "and   gas.RECAUDACION_GASTO_AFECTA_CAJA = 'S' " + "and   gas.RECAUDACION_GASTO_CARGA = 'E'";

    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public List<Object[]> queryBuscaOrigenServicioPorNumero(long numserv)
  {
    String query = "Select tmss.SERVICIO_NOMBRE  ,tmse.ESTADO_NOMBRE  from\t tms_servicios_tbl tmss  ,tms_rutas_tbl ruta  ,tms_estados_tbl   tmse  where ruta.RUTA_NUMERO = " + numserv + " " + " and   tmss.SERVICIO_ID = ruta.SERVICIO_ID " + " and      tmse.ESTADO_ID = ruta.TRAMO_DESTINO_ID";

    return this.em.createNativeQuery(query).getResultList();
  }

  public List<Object[]> queryBuscaDestinoServicioPorNumero(long numserv)
  {
    String query = "Select tmss.SERVICIO_NOMBRE  ,tmse.ESTADO_NOMBRE  from\t tms_servicios_tbl tmss  ,tms_rutas_tbl ruta  ,tms_estados_tbl   tmse  where ruta.RUTA_NUMERO = " + numserv + " " + " and   tmss.SERVICIO_ID = ruta.SERVICIO_ID " + " and      tmse.ESTADO_ID = ruta.TRAMO_DESTINO_ID";

    return this.em.createNativeQuery(query).getResultList();
  }

  public List<Object[]> queryBuscaCiudades()
  {
    String query = "select tmse.ESTADO_NOMBRE ,NVL(tmse.CLAVE_ESTADO,'NV')  ,tmse.ESTADO_ID  from\t tms_estados_tbl tmse where tmse.TIPO_COMPONENTE = 'CIUDAD'";

    return this.em.createNativeQuery(query).getResultList();
  }

  public Object buscarEstadoSesion(long sesionId) {
    String consulta = "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = " + sesionId;
    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object buscarVentaAbordo(String tar) {
    String consulta = "select count(bol.BOLETO_BOLETERA_ID), NVL(sum(bol.IMPORTE_BOLETO),0) from tms_boletos_boletera_tbl    bol where bol.FOLIO_TARJETA = '" + tar + "'";
    return this.em.createNativeQuery(consulta).getResultList();
  }

  public Object buscaEstadoPorId(long idEdo)
  {
    return this.em.createNativeQuery("select est.ESTADO_NOMBRE from tms_estados_tbl est where est.ESTADO_ID = " + idEdo).getResultList();
  }

  public Object queryBuscaTerminalLocal() {
    return this.em.createNativeQuery("select  est.ESTADO_ID from tms_base_datos_config_tbl base ,tms_estados_tbl est where esquema_propio = 'S' and est.ESTADO_NOMBRE = base.nombre_terminal").getSingleResult();
  }

  public Object queryBuscaTerminalId() {
    return this.em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'").getSingleResult();
  }

  public Object buscaMontoAcumulado(long idCorte) {
    String consulta = "select NVL(sum(to_number(reclin.RECAUDACION_VALOR,'999999.99')),0) from  tms_recaudacion_tbl    rec ,tms_recaudacion_lineas_tbl    reclin ,tms_tarjetas_viaje_tbl tar ,tms_recaudacion_gastos_tbl   gas where rec.ESTADO_RECAUDACION = 'R' and   rec.CORTE_ID = " + idCorte + " " + "and   reclin.RECAUDACION_ID = rec.RECAUDACION_ID " + "and   rec.TARJETA_VIAJE_ID =tar.TARJETA_VIAJE_ID " + "and   reclin.RECAUDACION_GASTO_ID = gas.RECAUDACION_GASTO_ID " + "and   gas.RECAUDACION_GASTO_AFECTA_CAJA = 'S' " + "and   gas.RECAUDACION_GASTO_CARGA = 'O'";

    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object buscaMontoAcumuladoParaRestar(long idCorte) {
    String consulta = "select NVL(sum(to_number(reclin.RECAUDACION_VALOR,'999999.99')),0) from  tms_recaudacion_tbl    rec ,tms_recaudacion_lineas_tbl    reclin ,tms_tarjetas_viaje_tbl tar ,tms_recaudacion_gastos_tbl   gas where rec.ESTADO_RECAUDACION = 'R' and   rec.CORTE_ID = " + idCorte + " " + "and   reclin.RECAUDACION_ID = rec.RECAUDACION_ID " + "and   rec.TARJETA_VIAJE_ID =tar.TARJETA_VIAJE_ID " + "and   reclin.RECAUDACION_GASTO_ID = gas.RECAUDACION_GASTO_ID " + "and   gas.RECAUDACION_GASTO_AFECTA_CAJA = 'S' " + "and   gas.RECAUDACION_GASTO_CARGA = 'E'";

    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object buscaSaldos(long idCorte)
  {
    String consulta = "select NVL(sum((rec.saldo)* -1),0) from tms_recaudacion_tbl rec where rec.ESTADO_RECAUDACION = 'R' and rec.CORTE_ID = " + idCorte + " and rec.saldo < 0";
    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object buscaMontoRecolecciones(long idCorte, long numUsuario) {
    String consulta = "  SELECT NVL(SUM(tmsrec.CANTIDAD * tmsrec.MONTO),0) FROM TMS_RECOLECCIONES_TBL tmsrec   ,TMS_SESION_ACTIVIDADES_TBL tmssa   ,TMS_ACTIVIDADES_SESION_TBL tmsact  ,TMS_VTA_TIPOS_PAGO_TBL     tmspagos  ,TMS_USUARIOS_TBL  \t\t  tmsus ,TMS_CORTES_TBL              tmsco  WHERE tmsus.USUARIO_NUMERO = " + numUsuario + " " + "AND  tmssa.CREADO_POR = tmsus.USUARIO_ID " + "AND  tmsco.CORTE_ID =  " + idCorte + " " + "AND  tmspagos.NOMBRE = 'EFECTIVO' " + "AND  tmsact.NOMBRE_ACTIVIDAD = 'RECOLECCION' " + "AND  tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID " + "AND  tmsrec.TIPO_PAGO_ID = tmspagos.TIPO_PAGO_ID " + "AND  tmsrec.SESION_ACTIVIDAD_ID = tmssa.SESION_ACTIVIDAD_ID " + "AND  tmssa.CORTE_ID = tmsco.CORTE_ID";

    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object buscaBoletosDATAFARERecolectados(long idCorte) {
    String consulta = "  select NVL(sum(bol.IMPORTE_BOLETO),0) from tms_boletos_boletera_tbl    bol where bol.CORTE_ID = " + idCorte;

    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object buscaVentaAbordoManual(long idCorte) {
    String consulta = "select  NVL(sum(to_number(rec.IMPORTE_VENTA_ABORDO,'999999.99')),0) from  tms_recaudacion_tbl    rec ,tms_tarjetas_viaje_tbl tar where rec.ESTADO_RECAUDACION = 'R' and   rec.CORTE_ID = " + idCorte + " " + "and   rec.TARJETA_VIAJE_ID =tar.TARJETA_VIAJE_ID " + "and   tar.FOLIO_TARJETA  not in(select distinct(FOLIO_TARJETA) from tms_boletos_boletera_tbl)";

    return this.em.createNativeQuery(consulta).getSingleResult();
  }

  public Object queryBuscaEstadoTarjetaViaje(String edo) {
    return this.em.createNativeQuery("select tmsEstTar.ESTADO_TARJETA_VIAJE_ID from TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar where tmsEstTar.NOMBRE_ESTADO = '" + edo + "'").getResultList();
  }

  public Object buscaTicket(long tarId) {
    return this.em.createNativeQuery(" select rec.RECAUDACION_REFERENCIA, edo.ESTADO_NOMBRE from tms_recaudacion_tbl rec, tms_estados_tbl edo where edo.ESTADO_ID = rec.CIUDAD_RECAUDACION_ID and rec.TARJETA_VIAJE_ID = " + tarId).getResultList();
  }

  public Vector buscaSaldoOperador(String clave)
  {
    String qry = "select r.SALDO from tms_recaudacion_tbl r  ,tms_TARJETAS_VIAJE_tbl T where  r.FECHA_CREACION = ( select max(r2.FECHA_CREACION)  from tms_tarjetas_viaje_tbl t2 ,tms_recaudacion_tbl r2 where t2.OPERADOR = '" + clave + "' " + "and r2.ESTADO_RECAUDACION = 'R' " + "and   r2.TARJETA_VIAJE_ID = t2.TARJETA_VIAJE_ID) " + "AND t.OPERADOR = '" + clave + "' " + "AND R.TARJETA_VIAJE_ID = T.TARJETA_VIAJE_ID ";

    return (Vector)this.em.createNativeQuery(qry).getResultList();
  }

  public Vector buscaUltimaRecaudacion(String clave)
  {
    String qry = "select r.RECAUDACION_ID from tms_recaudacion_tbl r  ,tms_TARJETAS_VIAJE_tbl T where  r.FECHA_CREACION = ( select max(r2.FECHA_CREACION)  from tms_tarjetas_viaje_tbl t2 ,tms_recaudacion_tbl r2 where t2.OPERADOR = '" + clave + "' " + "and r2.ESTADO_RECAUDACION = 'R' " + "and   r2.TARJETA_VIAJE_ID = t2.TARJETA_VIAJE_ID) " + "AND t.OPERADOR = '" + clave + "' " + "AND R.TARJETA_VIAJE_ID = T.TARJETA_VIAJE_ID ";

    return (Vector)this.em.createNativeQuery(qry).getResultList();
  }

  public Vector buscaPuenultimaRecaudacion(String clave, long ultimarecId)
  {
    String qry = "select r.RECAUDACION_ID from tms_recaudacion_tbl r  ,tms_TARJETAS_VIAJE_tbl T where  r.FECHA_CREACION = ( select max(r2.FECHA_CREACION)  from tms_tarjetas_viaje_tbl t2 ,tms_recaudacion_tbl r2 where t2.OPERADOR = '" + clave + "' " + "and r2.ESTADO_RECAUDACION = 'R' " + "and   r2.TARJETA_VIAJE_ID = t2.TARJETA_VIAJE_ID " + "AND R2.RECAUDACION_ID <> " + ultimarecId + ") " + "AND t.OPERADOR = '" + clave + "' " + "AND R.TARJETA_VIAJE_ID = T.TARJETA_VIAJE_ID ";

    return (Vector)this.em.createNativeQuery(qry).getResultList();
  }

  public Vector buscaCarteraUltimaRecaudacion(long recId) {
    String qry = "select reclines.RECAUDACION_VALOR from tms_RECAUDACION_LINEAS_TBL reclines ,tms_RECAUDACION_GASTOS_TBL gasto   where reclines.RECAUDACION_ID = " + recId + " " + "and gasto.RECAUDACION_GASTO_ID = reclines.RECAUDACION_GASTO_ID " + "and gasto.RECAUDACION_GASTO_NOMBRE = 'CARTERA_VENCIDA'";

    return (Vector)this.em.createNativeQuery(qry).getResultList();
  }

  public float buscaViaticos(String bus) {
    String qry = "SELECT NVL(SUM(TO_NUMBER(rl.RECAUDACION_VALOR,'999999.99')),0) viatico FROM TMS_RECAUDACION_TBL r ,TMS_RECAUDACION_LINEAS_TBL rl ,TMS_TARJETAS_VIAJE_TBL t ,TMS_ESTADOS_TARJETA_VIAJE_TBL et ,TMS_RECAUDACION_GASTOS_TBL g WHERE t.OPERADOR = '" + bus + "' " + "AND   et.NOMBRE_ESTADO = 'RECAUDADA' " + "AND   T.ESTADO_TARJETA_ID = et.ESTADO_TARJETA_VIAJE_ID " + "AND   r.TARJETA_VIAJE_ID = t.TARJETA_VIAJE_ID  " + "AND   TO_CHAR(r.FECHA_CREACION,'DD/MM/RRRR') = TO_CHAR(SYSDATE,'DD/MM/RRRR')  " + "AND   rl.RECAUDACION_ID = r.RECAUDACION_ID " + "AND g.RECAUDACION_GASTO_NOMBRE = 'Viaticos_Setex' " + "AND rl.RECAUDACION_GASTO_ID = g.RECAUDACION_GASTO_ID";

    Vector v = (Vector)this.em.createNativeQuery(qry).getSingleResult();
    return Float.valueOf(v.get(0).toString()).floatValue();
  }

  public float buscaTarifaTarjeta(String codigo, String servicio, String ruta, String empresa, String fecha, String diaSemana)
  {
    String qry = "select  nvl(max(case when tr.PARAMETRO_VALOR is not null then tr.PARAMETRO_VALOR  else (case when '" + diaSemana + "' = 'lunes'     then tr.LUNES_VALOR  " + "when '" + diaSemana + "' = 'martes'    then tr.MARTES_VALOR " + "when '" + diaSemana + "' = 'miércoles' then tr.MIERCOLES_VALOR " + "when '" + diaSemana + "' = 'jueves'    then tr.JUEVES_VALOR  " + "when '" + diaSemana + "' = 'viernes'   then tr.VIERNES_VALOR " + "when '" + diaSemana + "' = 'sábado'    then tr.SABADO_VALOR " + "when '" + diaSemana + "' = 'domingo'   then tr.DOMINGO_VALOR " + "end) end ),0) tarifa " + "from " + "TMS_TARIFAS_RECAUDA_TBL tr " + ",TMS_PARAMETROS_CONFIG_TBL p " + "where p.PARAMETRO_CODIGO = '" + codigo + "' " + "and tr.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID " + "and tr.SERVICIO_ID = " + servicio + " " + "and tr.RUTA_ID = " + ruta + " " + "and tr.EMPRESA_ID = " + empresa + " " + "and to_date('" + fecha + "', 'DD/MM/RRRR HH24:MI') >= tr.FECHA_HORA_TARIFA " + "and tr.FECHA_HORA_TARIFA = (select max(tr2.FECHA_HORA_TARIFA) " + "from " + "TMS_TARIFAS_RECAUDA_TBL tr2 " + ",TMS_PARAMETROS_CONFIG_TBL p2 " + "where p2.PARAMETRO_CODIGO = '" + codigo + "' " + "and tr2.PARAMETRO_CONFIG_ID = p2.PARAMETRO_CONFIG_ID " + "and tr2.SERVICIO_ID = " + servicio + " " + "and tr2.RUTA_ID = " + ruta + " " + "and tr2.EMPRESA_ID = " + empresa + " " + "and to_date('" + fecha + "', 'DD/MM/RRRR HH24:MI') >= tr2.FECHA_HORA_TARIFA) ";

    System.out.println("buscaTarifaTarjeta qry: " + qry);
    Vector v = (Vector)this.em.createNativeQuery(qry).getSingleResult();
    return Float.valueOf(v.get(0).toString()).floatValue();
  }

    public Vector getEstadoEstadoTarjetaViaxer(String pFolioTarjeta){
        Vector respuesta=new Vector();
        String estado="";
        String mensaje="";
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        System.out.println("---------------------------------------");
        System.out.println("FolioTarjeta(Viaxer): "+pFolioTarjeta);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "TMS_VIAXER_PKG.GET_Edo_TarjetaBoletera_prc(?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setString(1, pFolioTarjeta);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
            stmt.execute();
            //"NO EXISTE" => No existe la tarjeta y regresara el mensaje "No se encuentra venta con el Folio de tarjeta XXXX. Favor d eVerificar Ventas Pendientes"
            //"CERRADO"   => Esta todo Ok y regresa el mensaje "Todo ok"
            //"ABIERTO"   => Tienen Apertura pero no tiene cierre y regresa el mensaje "Existe venta por N Boletos por la cantidad de Y Pesos"
                                                                                       //"No hay venta turno abierto desde las XX:XX"
            //"CERRADO SIN APERTURA" => Tiene cierre pero no tiene apertura y regresa el mensaje "Existe venta por N Boletos por la cantidad de Y Pesos"
            estado = stmt.getString(2);
            mensaje = stmt.getString(3);
            System.out.println("estado: "+estado);
            System.out.println("mensaje: "+mensaje);
            stmt.close();
            if(cnx!=null) cnx.close();
            respuesta.add(estado);
            respuesta.add(mensaje);
            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            try {
                respuesta.add("ERROR");
                respuesta.add("No hubo respuesta de Viaxer");
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }
        return respuesta;
    }

}