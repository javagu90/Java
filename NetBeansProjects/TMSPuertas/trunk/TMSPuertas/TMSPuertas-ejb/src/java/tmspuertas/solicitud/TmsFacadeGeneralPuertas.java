/*
 * TmsCajasTblFacade.java
 *
 * Created on 3 de septiembre de 2007, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.solicitud;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmspuertas.entidad.TmsAccionesTbl;
import tmspuertas.entidad.TmsAutobusPlantLineasTbl;
import tmspuertas.entidad.TmsAutobusPlantillasEncTbl;
import tmspuertas.entidad.TmsAutobusesTbl;
import tmspuertas.entidad.TmsComponenteBusTbl;
import tmspuertas.entidad.TmsCorridasTbl;
import tmspuertas.entidad.TmsCorridasVentaTbl;
import tmspuertas.entidad.TmsEstadosCorridaTbl;
import tmspuertas.entidad.TmsEstadosTbl;
import tmspuertas.entidad.TmsOperadoresTbl;
import tmspuertas.entidad.TmsTarjetasViajeTbl;
import javax.annotation.Resource;
import javax.sql.DataSource;
import oracle.jdbc.OracleCallableStatement;


/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsFacadeGeneralPuertas implements TmsFacadeGeneralPuertasRemote {

    @PersistenceContext
    private EntityManager em;

    @Resource(name = "TMS_DB")
    private DataSource dataSource;

    
    /** Creates a new instance of TmsCajasTblFacade */
    public TmsFacadeGeneralPuertas() {
    }

    
    public Vector buscarDatosCajaPorEquipo(String nombreEquipo) {
        return (Vector)em.createNativeQuery("SELECT caj.CAJA_ID, caj.UBICACION_ID FROM Tms_Cajas_Tbl caj WHERE caj.nombre_Equipo = '"+nombreEquipo+"'").getResultList();
    }

    public Vector buscarDatosImpresoraPorCaja(long  cajaId) {
        String query = "select cimp.ACTIVIDAD_IMPRESORA, cimp.SALIDA_IMPRESION, imp.IMPRESORA_NOMBRE  from TMS_CAJAS_IMPRESORAS_TBL cimp " +
                ",TMS_IMPRESORAS_TBL imp " +
                "where cimp.CAJA_ID = "+cajaId+" " +
                "and   imp.IMPRESORA_ID = cimp.IMPRESORA_ID  ";
        return (Vector)em.createNativeQuery(query).getResultList();
    }

    
    public long buscaPermitirCrearTarjeta(String clave, String servicio, String DBLink){
            String query = "select  Xer_Tms_Pkg.GET_NUM_TAR_OPER('"+clave+"', '"+servicio+"', '"+DBLink+"') from dual";
        Vector vec = (Vector)em.createNativeQuery(query).getSingleResult();
        return Long.valueOf(vec.get(0).toString());
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
                "and     fun.FUNCION_NUMERO = '"+funcion+"' "+
                "and (  USRPER.FECHA_FINAL is null or sysdate between usrper.fecha_inicial and USRPER.FECHA_FINAL)";

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
    
 public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return em.createNativeQuery(consulta).getSingleResult();
 }
    
    
    public Vector buscaEsquemasTerminales(String esq){
        return (Vector)em.createNativeQuery("SELECT t.nombre_Terminal, t.NOMBRE_DBLINK FROM Tms_Base_Datos_Config_Tbl t WHERE t.esquema_Propio = '"+esq+"' and t.nombre_Terminal <> 'CENTRAL'").getResultList();
    }
    
    
    
   public Object buscarNombreOperador(String clave){ 
       String consulta = "select opr.OPERADOR_NOMBRE_COMPLETO from   tms_operadores_tbl opr where clave_operador = '"+clave+"'";
        return em.createNativeQuery(consulta).getResultList();
    }
    
     public long numeroAsientosOcupadosNoDisponibles(long corridaId){
        String query = "select  Xer_Tms_Pkg.GET_NUM_ASIENTOS_NO_DISP("+corridaId+") from dual";
        Vector vec = (Vector)em.createNativeQuery(query).getSingleResult();
        return Long.valueOf(vec.get(0).toString());
    }
   
    public String AsientosPorCorrida(long corridaId){
        String query = "SELECT  cor.Asiento1||','||cor.Asiento2||','||cor.Asiento3||','||cor.Asiento4||','||cor.Asiento5||','||cor.Asiento6||','||cor.Asiento7||','||cor.Asiento8||','||cor.Asiento9||','||cor.Asiento10||','|| " +
                "cor.Asiento11||','||cor.Asiento12||','||cor.Asiento13||','||cor.Asiento14||','||cor.Asiento15||','||cor.Asiento16||','||cor.Asiento17||','||cor.Asiento18||','||cor.Asiento19||','||cor.Asiento20||','||  " +
                "cor.Asiento21||','||cor.Asiento22||','||cor.Asiento23||','||cor.Asiento24||','||cor.Asiento25||','||cor.Asiento26||','||cor.Asiento27||','||cor.Asiento28||','||cor.Asiento29||','||cor.Asiento30||','|| " +
                "cor.Asiento31||','||cor.Asiento32||','||cor.Asiento33||','||cor.Asiento34||','||cor.Asiento35||','||cor.Asiento36||','||cor.Asiento37||','||cor.Asiento38||','||cor.Asiento39||','||cor.Asiento40||','|| " +
                "cor.Asiento41||','||cor.Asiento42||','||cor.Asiento43||','||cor.Asiento44||','||cor.Asiento45||','||cor.Asiento46||','||cor.Asiento47||','||cor.Asiento48||','||cor.Asiento49||','||cor.Asiento50||','|| " +
                "cor.Asiento51||','||cor.Asiento52||','||cor.Asiento53||','||cor.Asiento54||','||cor.Asiento55||','||cor.Asiento56||','||cor.Asiento57||','||cor.Asiento58||','||cor.Asiento59||','||cor.Asiento60||','|| " +
                "cor.Asiento61||','||cor.Asiento62||','||cor.Asiento63||','||cor.Asiento64||','||cor.Asiento65||','||cor.Asiento66||','||cor.Asiento67||','||cor.Asiento68||','||cor.Asiento69||','||cor.Asiento70||','|| " +
                "cor.Asiento71||','||cor.Asiento72||','||cor.Asiento73||','||cor.Asiento74||','||cor.Asiento75||','||cor.Asiento76||','||cor.Asiento77||','||cor.Asiento78||','||cor.Asiento79||','||cor.Asiento80||','|| " +
                "cor.Asiento81||','||cor.Asiento82||','||cor.Asiento83||','||cor.Asiento84||','||cor.Asiento85 ASIENTOS " +
                "FROM TMS_CORRIDAS_VENTA_TBL cor " +
                "WHERE cor.CORRIDA_ID = " + corridaId;
        Vector vec = (Vector)em.createNativeQuery(query).getSingleResult();
        return vec.get(0).toString();
    }  
    
    public Object buscaTarjetaViajeExistente(String folTar){
        String consulta = "select edos.NOMBRE_ESTADO " +
                "from " +
                "TMS_TARJETAS_VIAJE_TBL tar " +
                ",TMS_ESTADOS_TARJETA_VIAJE_TBL   edos " +
                "where FOLIO_TARJETA  = '"+folTar+"' " +
                "and   edos.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID"; 
        return em.createNativeQuery(consulta).getResultList(); 
    }
    
   public TmsTarjetasViajeTbl buscaTarjetaViajeExistenteVistaPrevia(long corridaId){
    TmsTarjetasViajeTbl tarExist = null;
//       String consulta = "select * " +
//               "from " +
//               "TMS_TARJETAS_VIAJE_TBL " +
//               "where CORRIDA_ID = "+corridaId+"";
          String consulta = "select * " +
                  "from " +
                  "TMS_TARJETAS_VIAJE_TBL tar " +
                  ",TMS_ESTADOS_TARJETA_VIAJE_TBL   edos " +
                  "where tar.CORRIDA_ID =  "+corridaId+" " +
                  "and edos.NOMBRE_ESTADO <> 'CANCELADA' " +
                  "and tar.ESTADO_TARJETA_ID = edos.ESTADO_TARJETA_VIAJE_ID"; 
    
        try {
            tarExist = (TmsTarjetasViajeTbl) em.createNativeQuery(consulta, TmsTarjetasViajeTbl.class).getSingleResult();
        } catch(NoResultException e) {
        }
    return tarExist;
   }    

    public boolean ActualizarTarjetaExistente(long noAdulTarexist,double mtoAdulTarexist,long noMenTarexist,double mtoMenTarexist,long noProfTarexist,double mtoProfTarexist,long noSenTarexist,double mtoSenTarexist,long noEstudTarexist,double mtoEstudTarexist,long noEspTarexist,double mtoEspTarexist,long edotarexist,String nombreEsquema,long idTar, long user)
    {
        String Consulta = "update tms_tarjetas_viaje_tbl " +
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

   public Object queryBuscaNombreEsquema(){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }  
    
   public Object queryBuscaNombreTerminal(){
    return  em.createNativeQuery("select nombre_terminal from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }  

   public long queryBuscaEstadoTarjetaViaje(){
      Vector vedo = (Vector)em.createNativeQuery("select tmsEstTar.ESTADO_TARJETA_VIAJE_ID from TMS_ESTADOS_TARJETA_VIAJE_TBL tmsEstTar where tmsEstTar.NOMBRE_ESTADO = 'ABIERTA'" ).getSingleResult();
      return Long.valueOf(vedo.get(0).toString());
  }   

  public Object queryBuscaIdTerminal(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }    

  public Vector buscaEstadosAcciones(){
    Vector vEA= new Vector();
    Vector vEE =(Vector) em.createNativeQuery("SELECT t.ESTADO_ID FROM Tms_Estados_Tbl t WHERE t.estado_Nombre = 'ENROLADO'" ).getResultList();
        if(vEE.size()==0) vEA.add(null); 
        else {
            Vector ee = (Vector)vEE.get(0);
            vEA.add(ee.toString());
        }
    Vector vEC =(Vector) em.createNativeQuery("SELECT t.ESTADO_ID FROM Tms_Estados_Tbl t WHERE t.estado_Nombre = 'CORRIDA'" ).getResultList();
        if(vEC.size()==0) vEA.add(null); 
        else {
            Vector ec = (Vector)vEC.get(0);
            vEA.add(ec.toString());
        }
    Vector vAE =(Vector) em.createNativeQuery("SELECT t.ACCION_ID FROM Tms_Acciones_Tbl t WHERE t.accion = 'ENROLADO'" ).getResultList();
        if(vAE.size()==0) vEA.add(null); 
        else {
            Vector ae = (Vector)vAE.get(0);
            vEA.add(ae.toString());
        }
    Vector vAC =(Vector) em.createNativeQuery("SELECT t.ACCION_ID FROM Tms_Acciones_Tbl t WHERE t.accion = 'CORRIDA'" ).getResultList();
        if(vAC.size()==0) vEA.add(null); 
        else {
            Vector ac = (Vector)vAC.get(0);
            vEA.add(ac.toString());
        }
    
    return  vEA;
  }    
  
  public Vector buscaNombreServicios(){
      return (Vector) em.createNativeQuery("select servicio_nombre from TMS_SERVICIOS_TBL").getResultList();
  }
  
  public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-MM-dd HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }     
  
  public Object fechaServidorManana(){
        String consulta="SELECT TO_CHAR(SYSDATE+1,'yyyy-MM-dd') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }     
  

  public Object buscaDBLinkCentral(){
    return  em.createNativeQuery("select NOMBRE_DBLINK from TMS_BASE_DATOS_CONFIG_TBL where NOMBRE_TERMINAL = 'CENTRAL'" ).getResultList();
  }
  
    public Object buscarTiposPasajero(){
        return em.createNativeQuery("select nombre_tipo, letra_tipo from tms_tipos_pasajero_tbl t where (t.fecha_hasta is null or t.fecha_hasta >= SYSDATE )").getResultList();
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
    public Vector buscarCorridasProximas(String feha1, String fecha2, String operador, String servic, String bus){
      String consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              ",Tms_Corridas_Tbl c " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
//              "and (t.operador like '"+operador+"' or t.operador is null) " +
//              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida	";
      if(!operador.equals("%") && bus.equals("%"))
      {
              consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              ",Tms_Corridas_Tbl c " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"') " +
//              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida" +
                      "	";          
      }
      if(!bus.equals("%") && operador.equals("%"))
      {
             consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              ",Tms_Corridas_Tbl c " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
//              "and (t.operador like '"+operador+"' or t.operador is null) " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida	";          
      }
      if(!bus.equals("%") && !operador.equals("%"))
      {
             consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl t " +
              ",Tms_Corridas_Tbl c " +       
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and t.operador like '"+operador+"' " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida	";          
      }
      //System.out.println("buscarCorridasProximas: "+consulta);
      
        return  (Vector)em.createNativeQuery(consulta).getResultList();
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

   
   public Object buscarDatosRuta(String corrida){ 
       String consulta = "select rut.RUTA_NUMERO, rut.RUTA_NOMBRE, rut.RUTA_ID from tms_corridas_tbl cor, tms_rutas_tbl rut where  cor.CLAVE_CORRIDA = '"+corrida+"'  and rut.RUTA_ID = cor.RUTA_ID";
        return em.createNativeQuery(consulta).getResultList();
    }
    
    public Object consultaOcupacionPorCorrida(long corridaid){
            return em.createNativeQuery("select Xer_Tms_Pkg.TMS_PASAJEROS_NETO_CORRIDA_FNC("+corridaid+") from dual").getSingleResult();
    } 
    
    public Object buscaDatosOcupacionPorSistema(String corrida){
//     String consulta ="select bol.BOLETO_ID, bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO from tms_boletos_venta_tbl bol "+
//                    " where bol.TIPO_OPERACION IN('VT','VA','HO','AC') "+
//                    " and bol.CLAVE_CORRIDA = '"+corrida+"' " +
//                    " and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";

     //String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
     String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO,(CASE BOL.CIUDAD_VENTA WHEN 'WEB' THEN '*'||bol.DESTINO ELSE bol.DESTINO END) DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
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
    
    
    public TmsTarjetasViajeTbl createTarjetaViaje(TmsTarjetasViajeTbl tmsTarjetasViajeTbl, String ter) {
        em.persist(tmsTarjetasViajeTbl);
        String ids = ""+tmsTarjetasViajeTbl.getTarjetaViajeId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        System.out.println("Folio_Viejo_"+tmsTarjetasViajeTbl.getFolioTarjeta()+"_Folio_Nuevo_"+tmsTarjetasViajeTbl.getFolioTarjeta().substring(0,10)+""+idn);
        tmsTarjetasViajeTbl.setFolioTarjeta(tmsTarjetasViajeTbl.getFolioTarjeta().substring(0,10)+""+idn);
        tmsTarjetasViajeTbl.setTarjetaViajeId(BigDecimal.valueOf(idn));
        em.merge(tmsTarjetasViajeTbl);
        return tmsTarjetasViajeTbl;
    }

    public void editTarjetaViaje(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
    }
    
    public boolean ActualizarFolioTarjeta(long idTar, String folio)
    {
        String Consulta = "update tms_tarjetas_viaje_tbl " +
                "set  FOLIO_TARJETA = '"+folio+"' "+
                "where TARJETA_VIAJE_ID = "+idTar;
        System.out.println("manda a actualizar el folio: "+folio);
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
    }    

   public Object queryBuscaValorActualTarjetaViaje(){
       return   em.createNativeQuery("select  NVL(max(o.TARJETA_VIAJE_ID),0) from tms_tarjetas_viaje_tbl o").getSingleResult();
   }
    
    public TmsCorridasVentaTbl findCorridaVenta(Object pk) {
        //TmsCorridasVentaTbl corrida = (TmsCorridasVentaTbl) em.find(TmsCorridasVentaTbl.class, pk);
        //em.refresh(corrida);
        TmsCorridasVentaTbl corrida = new TmsCorridasVentaTbl();
        try {
             Vector vCorridas = (Vector) em.createNativeQuery("select * from tms_corridas_venta_tbl where CORRIDA_ID = "+Long.valueOf(pk.toString())).getResultList();
           if(vCorridas.size()>0)
           {
                 Vector vCorrida = (Vector)vCorridas.get(0);
                 corrida.setValoresIniciales(vCorrida);
           }
        } catch (NoSuchElementException evt){corrida = null;}
        return corrida;
        
//        return corrida;
    }    

public TmsCorridasTbl findCorrida(Object pk) {
//        return (TmsCorridasTbl) em.find(TmsCorridasTbl.class, pk);
        TmsCorridasTbl corrida = new TmsCorridasTbl();
        try {
             Vector vCorridas = (Vector) em.createNativeQuery("select  CORRIDA_ID, CLAVE_CORRIDA,TIPO_CORRIDA  from tms_corridas_venta_tbl where CORRIDA_ID = "+Long.valueOf(pk.toString())).getResultList();
           if(vCorridas.size()>0)
           {
                 Vector vCorrida = (Vector)vCorridas.get(0);
                 corrida.setCorridaId(new BigDecimal(vCorrida.get(0).toString()));
                 corrida.setClaveCorrida(vCorrida.get(1).toString());
                 corrida.setTipoCorrida(vCorrida.get(2).toString());
           }
        } catch (NoSuchElementException evt){corrida = null;}
        return corrida;
    }
    
    public List<TmsEstadosCorridaTbl> buscarPorLetra(String letra)
    {
        return em.createNamedQuery("TmsEstadosCorridaTbl.findByNombreCortoEstado").setParameter("nombreCortoEstado",letra).getResultList();
    }
   
   
   public int editCorridaVenta(TmsCorridasVentaTbl tmsCorridasVentaTbl) {
//        em.merge(tmsCorridasVentaTbl);
        String consulta0 = "select ESTADO_CORRIDA from tms_corridas_venta_tbl where CORRIDA_ID = "+tmsCorridasVentaTbl.getCorridaId();
        String Consulta1 = "update tms_corridas_venta_tbl set ESTADO_CORRIDA = '"+tmsCorridasVentaTbl.getEstadoCorrida()+"', REPLICACION_ORIGEN = '"+tmsCorridasVentaTbl.getReplicacionOrigen()+"', ADICIONAL4 = '"+tmsCorridasVentaTbl.getAdicional4()+"'  where corrida_id = "+tmsCorridasVentaTbl.getCorridaId();
//    try{        
//         em.createNativeQuery(consulta0).executeUpdate();
//         em.createNativeQuery(Consulta1).executeUpdate();
//         return true;
//        }catch(Exception nrex){
//            nrex.printStackTrace();
//            return false; // esta bloqueado
//        }
        
	String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_venta_tbl "+
                "WHERE CORRIDA_ID="+tmsCorridasVentaTbl.getCorridaId()+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta1+"; "+
                "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        System.out.println("Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado");
            //nrex.printStackTrace();
            return 1;
        }        
       
    }

    public int editCorrida(TmsCorridasTbl tmsCorridasTbl) {
        //em.merge(tmsCorridasTbl);
        //ESTADO_CORRIDA_ID
        String consulta0 = "select ESTADO_CORRIDA_ID from tms_corridas_tbl where CORRIDA_ID = "+tmsCorridasTbl.getCorridaId();
        String Consulta1 = "update tms_corridas_tbl set ESTADO_CORRIDA_ID = "+tmsCorridasTbl.getEstadoCorridaId().getEstadoCorridaId()+", REPLICACION_ORIGEN = '"+tmsCorridasTbl.getReplicacionOrigen()+"', ADICIONAL4 = '"+tmsCorridasTbl.getAdicional4()+"'  where corrida_id = "+tmsCorridasTbl.getCorridaId();
//    try{        
//         em.createNativeQuery(consulta0).executeUpdate();
//         em.createNativeQuery(Consulta1).executeUpdate();
//         return true;
//        }catch(Exception nrex){
//            nrex.printStackTrace();
//            return false; // esta bloqueado
//        }
	String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_tbl "+
                "WHERE CORRIDA_ID="+tmsCorridasTbl.getCorridaId()+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta1+"; "+
                "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        System.out.println("Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado");
            //nrex.printStackTrace();
            return 1;
        }         
    }

    public TmsAutobusesTbl buscaBusPorNumero(String num) {
        TmsAutobusesTbl bus = null; 
         bus = (TmsAutobusesTbl) em.createNamedQuery("TmsAutobusesTbl.findByNumeroEconomico").setParameter("numeroEconomico",num).getSingleResult();
         if(bus!=null)
             em.refresh(bus);
         return bus;
    }     

    public void editAutobus(TmsAutobusesTbl tmsAutobusesTbl) {
        em.merge(tmsAutobusesTbl);
    }
   
    public TmsOperadoresTbl buscaOperadorPorNombre(String clave) {
        TmsOperadoresTbl operador = null; 
         operador = (TmsOperadoresTbl) em.createNamedQuery("TmsOperadoresTbl.findByClaveOperador").setParameter("claveOperador",clave).getSingleResult();
         if(operador!=null)
             em.refresh(operador);
         return operador;
    }      
    
    public void editOperador(TmsOperadoresTbl tmsOperadoresTbl) {
        em.merge(tmsOperadoresTbl);
    }
    

    public TmsAccionesTbl findAccion(Object pk) {
        return (TmsAccionesTbl) em.find(TmsAccionesTbl.class, pk);
    }

    public TmsAccionesTbl buscaAccionPorNombre(String acc) {
        TmsAccionesTbl accion = null; 
         accion = (TmsAccionesTbl) em.createNamedQuery("TmsAccionesTbl.findByAccion").setParameter("accion",acc).getSingleResult();
        return accion;
    }    
    
    public TmsEstadosTbl buscaEstadoPorNombre(String edo) {
        TmsEstadosTbl estado = null; 
         estado = (TmsEstadosTbl) em.createNamedQuery("TmsEstadosTbl.findByEstadoNombre").setParameter("estadoNombre",edo).getSingleResult();
        return estado;
    }
    
 public Object queryBuscaTerminalOperador(){
     String consulta = "select acc.accion_id from " +
             "tms_base_datos_config_tbl conf " +
             ",tms_acciones_tbl		   acc " +
             "where esquema_propio = 'S' " +
             "and acc.accion = conf.NOMBRE_TERMINAL ";
   return   em.createNativeQuery(consulta).getResultList();
  }
    
public Vector buscaEmpresas(){
    return (Vector)em.createNativeQuery("select empresa_id, EMPRESA_NOMBRE  from tms_empresas_tbl").getResultList();
} 
    
 public Object buscaNumeroUsuario(long usuarioId){
     String consulta =  "select usuario_numero from tms_usuarios_tbl where usuario_id = "+usuarioId;
     return em.createNativeQuery(consulta).getSingleResult();
 } 
 
  public Vector buscaBusPorNumeroParaAsignar(String numEcon, String empresa){
     //String consulta =  "select bus.AUTOBUS_ID from tms_autobuses_tbl bus  where bus.NUMERO_ECONOMICO = '"+numEcon+"'";
//       String consulta =  "select bus.AUTOBUS_ID " +
//               ",op.CLAVE_OPERADOR " +
//               ",op.OPERADOR_ID " +
//               ",op.OPERADOR_NOMBRE_COMPLETO   " +
//               "from tms_autobuses_tbl bus " +
//               ",tms_operadores_tbl op   " +
//               "where bus.NUMERO_ECONOMICO = '"+numEcon+"' " +
//               "and   op.OPERADOR_ID(+) = bus.OPERADOR_ID_PLANTA " +
//               "and   bus.EMPRESA_ID in (select empresa_id from tms_empresas_tbl where empresa_nombre = '"+empresa+"')";
      String consulta =  "select bus.AUTOBUS_ID " +
              ",op.CLAVE_OPERADOR  " +
              ",op.OPERADOR_ID " +
              ",op.OPERADOR_NOMBRE_COMPLETO " +
              ",bus.PLANTILLA_ENC_ID " +
              ",plantilla.NOMBRE_CORTO " +
              ",plantilla.CAPACIDAD_ASIENTOS 	" +
              "from tms_autobuses_tbl bus " +
              ",TMS_AUTOBUS_PLANTILLAS_ENC_TBL plantilla " +
              ",tms_operadores_tbl op    " +
              "where bus.NUMERO_ECONOMICO = '"+numEcon+"' " +
              "and   plantilla.PLANTILLA_ENC_ID = bus.PLANTILLA_ENC_ID " +
              "and   op.OPERADOR_ID(+) = bus.OPERADOR_ID_PLANTA  " +
              "and   bus.EMPRESA_ID in (select empresa_id from tms_empresas_tbl where empresa_nombre =  '"+empresa+"') " +
              "AND (bus.FECHA_HASTA IS NULL OR bus.FECHA_HASTA  > SYSDATE) ";
     return (Vector)em.createNativeQuery(consulta).getResultList();
 }
 
   public Vector buscaOperadorPorClaveParaAsignar(String clave, String empresa){
       //String consulta =  "select op.OPERADOR_ID,  op.OPERADOR_NOMBRE_COMPLETO from tms_operadores_tbl op  where op.CLAVE_OPERADOR = '"+clave+"'";
       String consulta =  "select op.OPERADOR_ID " +
               ",op.OPERADOR_NOMBRE_COMPLETO " +
               "from tms_operadores_tbl op  " +
               "where op.CLAVE_OPERADOR = '"+clave+"' " +
               "and op.EMPRESA_ID in (select empresa_id from tms_empresas_tbl where empresa_nombre = '"+empresa+"') " +
               "AND (op.FECHA_BAJA IS NULL OR op.FECHA_BAJA > SYSDATE) ";
     return (Vector)em.createNativeQuery(consulta).getResultList();
 }
 
      public int ActualizarCorridasAsignacion(long idCorrida,long idAutobus, String numEconBus,String claveOper, long idOper, String nombreEsquema, long usr, String empresa, long idEmpresa)
    {
        String consulta0 = "select OPERADOR_ID from tms_corridas_tbl where  CORRIDA_ID = "+idCorrida+" FOR UPDATE WAIT 2";    
        String Consulta1 = "update tms_corridas_tbl set AUTOBUS_ID =  "+idAutobus+"  " +
                ",OPERADOR_ID = "+idOper+" " +
                ",AUTOBUS_ORIGINAL_ID =  "+idAutobus+" " +
                ",OPERADOR_ORIGINAL_ID  = "+idOper+" ";
                if(!empresa.equals(""))
                    Consulta1 =Consulta1 + ",EMPRESA_ID = "+idEmpresa+" ";
                   Consulta1 =Consulta1 + 
                    ",REPLICACION_ORIGEN =	'"+nombreEsquema+"' "+
                    ",ULTIMA_ACTUALIZACION_POR = "+usr+"   " +
                    ",ULTIMA_FECHA_ACTUALIZACION  = SYSDATE " +
                    "where CORRIDA_ID = "+idCorrida;
        //em.createNativeQuery(Consulta1).executeUpdate();
        String consulta2 = "select AUTOBUS from tms_corridas_venta_tbl where  CORRIDA_ID = "+idCorrida+" FOR UPDATE WAIT 2";    
        String Consulta3 = "update tms_corridas_venta_tbl " +
                "set AUTOBUS = '"+numEconBus+"'  " +
                ",OPERADOR = '"+claveOper+"' ";
                if(!empresa.equals(""))
                    Consulta3 =Consulta3 + ",EMPRESA = '"+empresa+"' ";
                   Consulta3 =Consulta3 + 
                ",REPLICACION_ORIGEN = '"+nombreEsquema+"' " +
                "where CORRIDA_ID = "+idCorrida;
//    try{        
//         em.createNativeQuery(consulta0).executeUpdate();
//         em.createNativeQuery(Consulta1).executeUpdate();
//         em.createNativeQuery(consulta2).executeUpdate();
//         em.createNativeQuery(Consulta3).executeUpdate();
//         return true;
//        }catch(Exception nrex){
//            //System.out.println(nrex.printStackTrace());
//            nrex.printStackTrace();
//            return false; // esta bloqueado
//        }
	String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_tbl "+
                "WHERE CORRIDA_ID="+idCorrida+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta1+"; "+
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_venta_tbl "+
                "WHERE CORRIDA_ID="+idCorrida+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta3+"; "+
                "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        System.out.println("Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado");
            //nrex.printStackTrace();
            return 1;
        }                    
    }
    

  public void actualizarEstadosOperBus(String claveOper, String numEcon, String DBLink, long remoto, String empresa, String esquema){
      String qry = "DECLARE " +
              "RetVal NUMBER; " +
              "CLAVE_OPERADOR VARCHAR2(200); " +
              "NUMERO_ECONOMICO_BUS VARCHAR2(200); " +
              "DBLINK VARCHAR2(200); " +
              "REMOTO NUMBER;  " +
              "EMPRESA VARCHAR2(200); " +
              "BEGIN  " +
              "CLAVE_OPERADOR := '"+claveOper+"'; " +
              "NUMERO_ECONOMICO_BUS := '"+numEcon+"'; " +
              "DBLINK := '"+DBLink+"'; " +
              "REMOTO := "+remoto+"; " +
              "EMPRESA := '"+empresa+"'; "+
              "RetVal := XER_TMS_PKG.ACT_EDO_BUS_OPER ( CLAVE_OPERADOR, NUMERO_ECONOMICO_BUS, DBLINK, REMOTO, EMPRESA ); " +
              "COMMIT; " +
              "END; ";
        em.createNativeQuery(qry).executeUpdate();
  }
  
   
   
//****************************  R E M O T O  ************************************//
    
    public Object buscaTarjetaViajeExistenteRemoto(String DBLink,String folTar){
        String consulta = "select edos.NOMBRE_ESTADO " +
                "from " +
                "TMS_TARJETAS_VIAJE_TBL@"+DBLink+" tar " +
                ",TMS_ESTADOS_TARJETA_VIAJE_TBL@"+DBLink+"   edos " +
                "where FOLIO_TARJETA  = '"+folTar+"' " +
                "and   edos.ESTADO_TARJETA_VIAJE_ID = tar.ESTADO_TARJETA_ID"; 
        return em.createNativeQuery(consulta).getResultList();
    }
    
   public TmsTarjetasViajeTbl buscaTarjetaViajeExistenteVistaPreviaRemoto(long corridaId, String DBLink){
    TmsTarjetasViajeTbl tarExist = null;
//       String consulta = "select * " +
//               "from " +
//               "TMS_TARJETAS_VIAJE_TBL@"+DBLink+" " +
//               "where CORRIDA_ID = "+corridaId+"";
          String consulta = "select * " +
                  "from " +
                  "TMS_TARJETAS_VIAJE_TBL@"+DBLink+" tar " +
                  ",TMS_ESTADOS_TARJETA_VIAJE_TBL@"+DBLink+"  edos " +
                  "where tar.CORRIDA_ID =  "+corridaId+" " +
                  "and edos.NOMBRE_ESTADO <> 'CANCELADA' " +
                  "and tar.ESTADO_TARJETA_ID = edos.ESTADO_TARJETA_VIAJE_ID"; 
    
        try {
            tarExist = (TmsTarjetasViajeTbl) em.createNativeQuery(consulta, TmsTarjetasViajeTbl.class).getSingleResult();
        } catch(NoResultException e) {
        }
    return tarExist;
   }    
    
    
    public boolean ActualizarTarjetaExistenteRemota(long noAdulTarexist,double mtoAdulTarexist,long noMenTarexist,double mtoMenTarexist,long noProfTarexist,double mtoProfTarexist,long noSenTarexist,double mtoSenTarexist,long noEstudTarexist,double mtoEstudTarexist,long noEspTarexist,double mtoEspTarexist,long edotarexist,String nombreEsquema,String DBLink,long idTar, long user)
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
    
   public Object queryBuscaNombreEsquemaRemoto(String DBLink){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl@"+DBLink+" where esquema_propio = 'S'" ).getSingleResult();
  }  

  public Object queryBuscaIdTerminalRemota(String DBLink){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl@"+DBLink+" where esquema_propio = 'S'" ).getSingleResult();
  }    
  
 
    public Vector buscarCorridasProximasRemoto(String feha1, String fecha2, String operador, String servic, String bus, String DBLink){
      String consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              ",Tms_Corridas_Tbl@"+DBLink+" c " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
//              "and (t.operador like '"+operador+"' or t.operador is null) " +
//              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida	";
      if(!operador.equals("%") && bus.equals("%"))
      {
              consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              ",Tms_Corridas_Tbl@"+DBLink+" c " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and (t.operador like '"+operador+"') " +
//              "and (t.autobus LIKE '"+bus+"' or  t.autobus is null) " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida	";          
      }
      if(!bus.equals("%") && operador.equals("%"))
      {
             consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              ",Tms_Corridas_Tbl@"+DBLink+" c " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
//              "and (t.operador like '"+operador+"' or t.operador is null) " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida	";          
      }
      if(!bus.equals("%") && !operador.equals("%"))
      {
             consulta = "SELECT t.CORRIDA_ID, t.CLAVE_CORRIDA, t.SERVICIO, t.ORIGEN, t.DESTINO, t.FECHA_HORA_CORRIDA, t.OPERADOR, t.AUTOBUS, t.PLANTILLA_ID, t.EMPRESA, t.TIPO_CORRIDA FROM " +
              "Tms_Corridas_Venta_Tbl@"+DBLink+" t " +
              ",Tms_Corridas_Tbl@"+DBLink+" c " +
              "where t.fecha_Hora_Corrida between to_date('"+feha1+"','DD/MM/YYYY HH24:MI') and to_date('"+fecha2+"','DD/MM/YYYY HH24:MI') " +
              "and t.operador like '"+operador+"' " +
              "and t.autobus LIKE '"+bus+"' " +
              "and t.servicio LIKE '"+servic+"'  " +
              "and t.estado_Corrida = 'A'  " +
              "and t.CORRIDA_ID = c.CORRIDA_ID " +
              "order by t.fecha_Hora_Corrida	";          
      }
      
        return  (Vector)em.createNativeQuery(consulta).getResultList();
    }         

   public Object buscarDatosRutaRemoto(String corrida, String DBLink){ 
       String consulta = "select rut.RUTA_NUMERO, rut.RUTA_NOMBRE, rut.RUTA_ID from tms_corridas_tbl@"+DBLink+" cor, tms_rutas_tbl@"+DBLink+" rut where  cor.CLAVE_CORRIDA = '"+corrida+"'  and rut.RUTA_ID = cor.RUTA_ID";
        return em.createNativeQuery(consulta).getResultList();
    }


 public Object numeroAsientosOcupadosNoDisponiblesRemoto(long corridaId, String DBLink){
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
             "and tmsAs.CORRIDA_ID = "+corridaId+" " +
             "AND tmsas.tipo_pasajero IS NOT NULL " +
             "and tmsAs.NO_ASIENTO NOT IN (select res.NO_ASIENTO from tms_reservaciones_tbl@"+DBLink+" res where res.CORRIDA_ID = "+corridaId+" and res.estado_reservacion <> 'CANCELADA' )";
 
     return em.createNativeQuery(consulta).getSingleResult();
 }

    public Object buscaDatosOcupacionPorSistemaRemoto(String corrida,String DBLink){
//     String consulta ="select bol.BOLETO_ID, bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO from tms_boletos_venta_tbl bol "+
//                    " where bol.TIPO_OPERACION IN('VT','VA','HO','AC') "+
//                    " and bol.CLAVE_CORRIDA = '"+corrida+"' " +
//                    " and not exists (select 1 from tms_boletos_venta_tbl bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";

     //String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO, bol.DESTINO, bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
       String consulta = "select distinct(bol.BOLETO_ID), bol.NO_ASIENTO,(CASE BOL.CIUDAD_VENTA WHEN 'WEB' THEN '*'||bol.DESTINO ELSE bol.DESTINO END) DESTINO,bol.FOLIO_BOLETO, bol.TIPO_PASAJERO, bol.IMPORTE_BOLETO " +
             // "NVL((select bolrel.importe_boleto from tms_boletos_venta_tbl bolrel where bolrel.boleto_id = bol.BOLETO_RELACIONADO_ID), bol.IMPORTE_BOLETO) importe_boleto  " +
             "from tms_boletos_venta_tbl@"+DBLink+" bol " +
             ",tms_corridas_tbl@"+DBLink+" cor  " +
             ",tms_corridas_tbl@"+DBLink+" correla  " +
             "where bol.TIPO_OPERACION IN('VT','VA','HO','AC','FT','FO','FC')  " +
             "and cor.CLAVE_CORRIDA= '"+corrida+"' " +
             "and correla.CORRIDA_RELACIONADA_ID(+) = cor.CORRIDA_ID " +
             "and bol.CLAVE_CORRIDA in (cor.CLAVE_CORRIDA,correla.CLAVE_CORRIDA) " +
             "and not exists (select 1 from tms_boletos_venta_tbl@"+DBLink+" bol2 where bol2.boleto_relacionado_id = bol.BOLETO_ID )";   
        
 
        return em.createNativeQuery(consulta).getResultList();
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
    
   public boolean modificarTarjetaReimpresion(TmsTarjetasViajeTbl tarjeta, String DBLink){
       
       String Consulta = "update tms_tarjetas_viaje_tbl@"+DBLink+"  set NUMERO_IMPRESION  = "+tarjeta.getNumeroImpresion().longValue()+" , MOTIVO_REIMPRESION  = '"+tarjeta.getMotivoReimpresion()+"', ULTIMA_ACTUALIZACION_POR = "+tarjeta.getUltimaActualizacionPor().longValue()+", ULTIMA_FECHA_ACTUALIZACION = SYSDATE, REPLICACION_ORIGEN = '"+tarjeta.getReplicacionOrigen()+"'  where tarjeta_viaje_id = "+tarjeta.getTarjetaViajeId().longValue();
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
       
   }
    
   public Object queryBuscaValorActualTarjetaViajeRemoto(String DBLink){
       System.out.println("Busca Folio Actual: select  NVL(max(o.TARJETA_VIAJE_ID),0) from tms_tarjetas_viaje_tbl@"+DBLink+" o");
       return   em.createNativeQuery("select  NVL(max(o.TARJETA_VIAJE_ID),0) from tms_tarjetas_viaje_tbl@"+DBLink+" o").getSingleResult();
   }    

   public int ActualizarCorrida(long idCorrida,long idEstado,String NombreEsquema,String adicional4,String DBLink)
    {
        String Consulta1 = "update tms_corridas_tbl@"+DBLink+" set ESTADO_CORRIDA_ID = "+idEstado+" , REPLICACION_ORIGEN = '"+NombreEsquema+"', adicional4 = '"+adicional4+"' where corrida_id = "+idCorrida;
//        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
//        else
//            return true;
	String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_tbl@"+DBLink+" "+
                "WHERE CORRIDA_ID="+idCorrida+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta1+"; "+
                "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        System.out.println("Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado");
            //nrex.printStackTrace();
            return 1;
        }        
        
    }
    
    public int ActualizarCorridaVenta(long idCorridaVenta,String letraEstado,String NombreEsquema,String adicional4,String DBLink)
    {
        String consulta0 = "select ESTADO_CORRIDA from tms_corridas_venta_tbl@"+DBLink+" where CORRIDA_ID = "+idCorridaVenta;
        String Consulta1 = "update tms_corridas_venta_tbl@"+DBLink+" set ESTADO_CORRIDA = '"+letraEstado+"', REPLICACION_ORIGEN = '"+NombreEsquema+"', adicional4 = '"+adicional4+"' where corrida_id = "+idCorridaVenta;
//        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
//        else
//            return true;
//    try{        
//         em.createNativeQuery(consulta0).executeUpdate();
//         em.createNativeQuery(Consulta1).executeUpdate();
//         return true;
//        }catch(Exception nrex){
//            //System.out.println(nrex.printStackTrace());
//            nrex.printStackTrace();
//            return false; // esta bloqueado
//        }

        
	String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_venta_tbl@"+DBLink+" "+
                "WHERE CORRIDA_ID="+idCorridaVenta+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta1+"; "+
                "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        System.out.println("Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado");
            //nrex.printStackTrace();
            return 1;
        }        
        
        
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
   
   public TmsTarjetasViajeTbl insertarTarjetaRemoto(TmsTarjetasViajeTbl tarjeta, String ter, String DBLink){
       TmsTarjetasViajeTbl tar = null; 
       String Consulta = "insert into tms_tarjetas_viaje_tbl@"+DBLink+"  (TARJETA_VIAJE_ID,FOLIO_TARJETA,CORRIDA_ID,ESTADO_TARJETA_ID,NUMERO_IMPRESION,NO_ADULTOS_ABORDADOS,MONTO_ADULTOS_ABORDADOS,NO_ESTUDIANTES_ABORDADOS,MONTO_ESTUDIANTES_ABORDADOS,NO_SENECTUD_ABORDADOS,MONTO_SENECTUD_ABORDADOS,NO_MENORES_ABORDADOS,MONTO_MENORES_ABORDADOS,NO_PROFESOR_ABORDADOS,MONTO_PROFESOR_ABORDADOS,NO_ESPECIAL_ABORDADOS,MONTO_ESPECIAL_ABORDADOS,OPERADOR,AUTOBUS,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION,REPLICACION_ORIGEN) " +
                "values    ( '"+ter+"'||TMS_TARJETAS_VIAJE_SEQ.NEXTVAL@"+DBLink+",'"+tarjeta.getFolioTarjeta()+"',"+tarjeta.getCorridaId().longValue()+",  "+tarjeta.getEstadoTarjetaId().longValue()+",0,"+tarjeta.getNoAdultosAbordados().longValue()+","+tarjeta.getMontoAdultosAbordados().doubleValue()+","+tarjeta.getNoEstudiantesAbordados().longValue()+","+tarjeta.getMontoEstudiantesAbordados().doubleValue()+","+tarjeta.getNoSenectudAbordados().longValue()+","+tarjeta.getMontoSenectudAbordados().doubleValue()+","+tarjeta.getNoMenoresAbordados().longValue()+","+tarjeta.getMontoMenoresAbordados().doubleValue()+","+tarjeta.getNoProfesorAbordados().longValue()+","+tarjeta.getMontoProfesorAbordados().doubleValue()+","+tarjeta.getNoEspecialAbordados().longValue()+","+tarjeta.getMontoEspecialAbordados()+", '"+tarjeta.getOperador()+"','"+tarjeta.getAutobus()+"',"+tarjeta.getCreadoPor().longValue()+",SYSDATE,"+tarjeta.getUltimaActualizacionPor().longValue()+",SYSDATE, '"+tarjeta.getReplicacionOrigen()+"')";
       System.out.println("Query insertarTarjetaRemoto: "+Consulta); 
       if(em.createNativeQuery(Consulta).executeUpdate()==0) return tar;
        else
        {
           System.out.println("Query insertarTarjetaRemoto(2): "+"select * from tms_tarjetas_viaje_tbl@"+DBLink+" where CORRIDA_ID = "+tarjeta.getCorridaId().longValue());
           tar = (TmsTarjetasViajeTbl)em.createNativeQuery("select * from tms_tarjetas_viaje_tbl@"+DBLink+" where CORRIDA_ID = "+tarjeta.getCorridaId().longValue(),TmsTarjetasViajeTbl.class).getSingleResult();
           return tar;
        }
   }   
   
   public Vector buscaTarjetaCreadaRemoto(long idCorrida, String DBLink){
       return (Vector)em.createNativeQuery("select tarjeta_viaje_id from tms_tarjetas_viaje_tbl@"+DBLink+" where CORRIDA_ID = "+idCorrida).getResultList();
   }
      
    public boolean ActualizarFolioTarjetaRemoto(String DBLink,long idTar, String folio)
    {
        String Consulta = "update tms_tarjetas_viaje_tbl@"+DBLink+" " +
                "set  FOLIO_TARJETA = '"+folio+"' "+
                "where TARJETA_VIAJE_ID = "+idTar;
        if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
        else
            return true;
    }

   public List<TmsTarjetasViajeTbl> buscarTarjetasReimpresionRemoto(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf,String DBLink){
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


   public List<TmsCorridasVentaTbl> buscarCorridasReimpresionRemoto(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf,String DBLink){
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
    
  public int ActualizarCorridasAsignacionRemoto(long idCorrida,long idAutobus, String numEconBus,String claveOper, long idOper, String nombreEsquema, long usr, String DBLink, String empresa, long idEmpresa)
    {
      String consulta0 =   "select AUTOBUS_ID from tms_corridas_tbl@"+DBLink+" where CORRIDA_ID = "+idCorrida;
      String Consulta1 = "update tms_corridas_tbl@"+DBLink+" set AUTOBUS_ID =  "+idAutobus+"  " +
                ",OPERADOR_ID = "+idOper+" " +
                ",AUTOBUS_ORIGINAL_ID =  "+idAutobus+" " +
                ",OPERADOR_ORIGINAL_ID  = "+idOper+" ";
                if(!empresa.equals(""))
                   Consulta1 =Consulta1 + ",EMPRESA_ID = "+idEmpresa+" ";
                Consulta1 =Consulta1 + 
                ",REPLICACION_ORIGEN =	'"+nombreEsquema+"' "+
                ",ULTIMA_ACTUALIZACION_POR = "+usr+"   " +
                ",ULTIMA_FECHA_ACTUALIZACION  = SYSDATE " +
                "where CORRIDA_ID = "+idCorrida;
        //em.createNativeQuery(Consulta1).executeUpdate();
        String consulta2 = "select AUTOBUS from tms_corridas_venta_tbl@"+DBLink+" where CORRIDA_ID = "+idCorrida;
        String Consulta3 = "update tms_corridas_venta_tbl@"+DBLink+" " +
                "set AUTOBUS = '"+numEconBus+"'  " +
                ",OPERADOR = '"+claveOper+"' ";
                if(!empresa.equals(""))
                   Consulta3 =Consulta3 + ",EMPRESA = '"+empresa+"' ";
                Consulta3 =Consulta3 + 
                ",REPLICACION_ORIGEN = '"+nombreEsquema+"' " +
                "where CORRIDA_ID = "+idCorrida;
//        em.createNativeQuery(Consulta3).executeUpdate();
//    try{        
//         em.createNativeQuery(consulta0).executeUpdate();
//         em.createNativeQuery(Consulta1).executeUpdate();
//         em.createNativeQuery(consulta2).executeUpdate();
//         em.createNativeQuery(Consulta3).executeUpdate();
//         return true;
//        }catch(Exception nrex){
//            //System.out.println(nrex.printStackTrace());
//            nrex.printStackTrace();
//            return false; // esta bloqueado
//        }
	String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_tbl@"+DBLink+"  "+
                "WHERE CORRIDA_ID="+idCorrida+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta1+"; "+
                "BEGIN SELECT 1 INTO dummy " +
                "FROM tms_corridas_venta_tbl@"+DBLink+"  "+
                "WHERE CORRIDA_ID="+idCorrida+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    Consulta3+"; "+
                "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        System.out.println("Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            System.out.println("msg: "+nrex.getCause().getMessage());
            System.out.println("msg terminado");
            //nrex.printStackTrace();
            return 1;
        }                
    }
  
  public Vector buscaIdServicio(String servicio){
      return (Vector)em.createNativeQuery("select serv.SERVICIO_ID from tms_servicios_tbl serv where serv.SERVICIO_NOMBRE = '"+servicio+"'").getResultList();
  }

  public Vector buscaServicioEmpresas(long idServicio, long idEmpresa, long idRuta){
      return (Vector)em.createNativeQuery("select se.SERVICIOS_EMPRESAS_ID from TMS_SERVICIOS_EMPRESAS_TBL se where se.SERVICIO_ID ="+idServicio+" and se.EMPRESA_ID = "+idEmpresa+" and se.RUTA_ID ="+idRuta).getResultList();
  }

    
    
   
//*********************** PLATILLA DE AUTOBUS******************************//
    
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
