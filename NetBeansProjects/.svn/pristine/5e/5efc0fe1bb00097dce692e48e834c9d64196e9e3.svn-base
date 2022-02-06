/*
 * TmsVariosFacade.java
 *
 * Created on 3 de octubre de 2007, 07:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.excepciones.UsuarioNoEncontradoException;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsVariosFacade implements tmsactividadesadicionales.solicitud.TmsVariosFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;

    /**
     * Creates a new instance of TmsVariosFacade
     */
    public TmsVariosFacade() {
    }
    
    

    public Object buscarPorcentajeRetencion(String empresa){
        String consulta = "select emparam.PARAMETRO_VALOR from " +
                "tms_empresa_parametros_tbl emparam " +
                ",tms_empresas_tbl   		empre " +
                ",tms_parametros_config_tbl  param " +
                "where empre.EMPRESA_NOMBRE_CORTO = '"+empresa+"' " +
                "and   param.PARAMETRO_CODIGO = 'P_R_PORRET' " +
                "and   emparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
                "and   emparam.EMPRESA_ID = empre.EMPRESA_ID ";
        return em.createNativeQuery(consulta).getSingleResult();
    }
    
    public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
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
  
  public Object buscarTodasFunciones(){
    String consulta = "select fun.FUNCION_NUMERO, fun.AUDITABLE from " +
            " tms_funciones_tbl 		  fun " +
            " where   fun.FUNCION_NUMERO like '60%'";
  return em.createNativeQuery(consulta).getResultList();
  }  
  
  
  
/*
* Query para buscar los parametrso por empresa 
*/
 public Object buscaParametrosPorEmpresa(){
    String consulta = "select param.PARAMETRO_CODIGO,empre.EMPRESA_NOMBRE_CORTO, empreparam.PARAMETRO_VALOR   from " +
            " tms_parametros_config_tbl  param " +
            ",tms_empresa_parametros_tbl empreparam " +
            ",tms_empresas_tbl			empre " +
            "where   empreparam.EMPRESA_ID = empre.EMPRESA_ID " +
            "and   param.PARAMETRO_TIPO = 'EMPRESA' " +
            "and   empreparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
            "and   param.APLICA_TURNO_DIAS = 'N'";
    return em.createNativeQuery(consulta).getResultList();
}
 

 public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return em.createNativeQuery(consulta).getSingleResult();
 }
 
 
/*
* Query para buscar los parametrso por servicio 
*/
 public Object buscaParametrosPorServicio(){
    String consulta = "select param.PARAMETRO_CODIGO,serv.SERVICIO_NOMBRE, servparam.PARAMETRO_VALOR  from " +
            "tms_parametros_config_tbl  param " +
            ",tms_servicio_parametros_tbl servparam " +
            ",tms_servicios_tbl			serv " +
            "where   servparam.SERVICIO_ID = serv.SERVICIO_ID " +
            "and   param.PARAMETRO_TIPO = 'SERVICIO' " +
            "and   servparam.PARAMETRO_CONFIG_ID = param.PARAMETRO_CONFIG_ID " +
            "and   param.APLICA_TURNO_DIAS = 'N'";
    return em.createNativeQuery(consulta).getResultList();
}

   public Object buscarUsuarioPorId(long idusr) throws UsuarioNoEncontradoException{
        String consulta;
         Object resultado = null;
        try {
            consulta = "select usr.USUARIO_NOMBRE from tms_usuarios_tbl usr where usr.USUARIO_ID = " + idusr;
            resultado = em.createNativeQuery(consulta).getSingleResult();
        } catch(NoResultException e) {
           resultado = null;
           throw new UsuarioNoEncontradoException("Error.-  No se encontro usuario");
        }
       return resultado;
    }
    
   public Object queryBuscaTerminalId(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }     

   public Object queryBuscaValorActualPagoActividadesAdicionales(){
       return   em.createNativeQuery("select  NVL(max(o.REFERENCIA_PAGO_ACT_ADICIONAL),0) from tms_pagos_act_adicionales_tbl o").getSingleResult();
   }
   
   public Object queryBuscaIdTerminal(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }    
   
   
   
}
