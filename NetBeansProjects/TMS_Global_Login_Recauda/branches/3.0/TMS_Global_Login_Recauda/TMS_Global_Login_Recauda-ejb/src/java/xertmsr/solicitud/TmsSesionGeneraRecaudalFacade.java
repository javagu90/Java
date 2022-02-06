/*
 * TmsSesionGeneraRecaudalFacade.java
 *
 * Created on 7 de agosto de 2007, 09:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsr.solicitud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsgloballoginr.entidad.TmsAuditoriaTbl;
import tmsgloballoginr.entidad.TmsSesionesGlobalTbl;
import tmsgloballoginr.entidad.TmsUsuariosTbl;
import tmsgloballoginr.excepcion.UsuarioNoEncontradoException;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsSesionGeneraRecaudalFacade implements xertmsr.solicitud.TmsSesionGeneralRecaudaFacadeRemote {
    @PersistenceContext(unitName="TMSDB2-ejbPU")
    private EntityManager em;
    /**
     * Creates a new instance of TmsSesionGeneraRecaudalFacade
     */
    public TmsSesionGeneraRecaudalFacade() {
    }
    
    ////////////////////////////////// FacadeUsuario //////////////////////////////////////

    
    public void editUsuario(TmsUsuariosTbl tmsUsuariosTbl) {
        em.merge(tmsUsuariosTbl);
    }
    
    public TmsUsuariosTbl getUsuarioPorNumero(String usuarioNumero) throws UsuarioNoEncontradoException {
        TmsUsuariosTbl usuarior;
        try{
            usuarior = (TmsUsuariosTbl)em.createNamedQuery("TmsUsuariosTbl.findByUsuarioNumero").setParameter("usuarioNumero",usuarioNumero).getSingleResult();
            em.refresh(usuarior);
        }catch(NoResultException ex){
            throw new UsuarioNoEncontradoException("No encontro usuario por numero");
        }
        //System.out.println("Tamaño de la coleccion de perfiles es: "+usuarior.getTmsUsuarioPerfilesTblCollection().size());
        //instanciarMenuCollection(usuarior.getTmsUsuarioPerfilesTblCollection());
        return usuarior;
    }
    
    
public int getPerfilesUsuario(String usuarioNumero){
    int numper = 0;
    Vector vec = (Vector)em.createNativeQuery("select count(perus.USUARIO_PERFIL_ID) from tms_usuarios_tbl us ,tms_usuario_perfiles_tbl perus where us.USUARIO_NUMERO = "+usuarioNumero+" and perus.USUARIO_ID = us.USUARIO_ID").getSingleResult();
    numper = Integer.valueOf(vec.get(0).toString());
    return numper;
}
    
//    public int getPerfilesUsuario(String usuarioNumero)  UsuarioNoEncontradoException {
//        TmsUsuariosTbl usuarior;
//        try{
//            usuarior = (TmsUsuariosTbl)em.createNamedQuery("TmsUsuariosTbl.findByUsuarioNumero").setParameter("usuarioNumero",usuarioNumero).getSingleResult();
//            em.refresh(usuarior);
//        }catch(NoResultException ex){
//            throw new UsuarioNoEncontradoException("No encontro usuario por numero");
//        }
////        System.out.println("#Tamaño de la coleccion de perfiles es: "+usuarior.getTmsUsuarioPerfilesTblCollection().size());
//        return usuarior.getTmsUsuarioPerfilesTblCollection().size();
//    }
    
    public boolean esUsuarioSupervisor(String usuarioNumero,String pwdEnc) throws UsuarioNoEncontradoException{
        String resultado;
        String consultaUsuario;
        consultaUsuario = "SELECT 1" +
                " FROM TMS_USUARIOS_TBL tmsus" +
                " ,TMS_USUARIO_PERFILES_TBL tmsup" +
                " ,TMS_PERFILES_TBL tmspe" +
                " ,TMS_MENUS_ENCABEZADO_TBL tmsme" +
                " ,TMS_MENUS_LINEAS_TBL    tmsml" +
                " ,TMS_FUNCIONES_TBL 		tmsfn" +
                " WHERE tmsus.USUARIO_ID = tmsup.USUARIO_ID" +
                " AND tmsup.PERFIL_ID  = tmspe.PERFIL_ID" +
                " AND tmspe.MENU_ID    = tmsme.MENU_ID " +
                " AND tmsme.MENU_ID    = tmsml.MENU_ID" +
                " AND tmsml.FUNCION_ID = tmsfn.FUNCION_ID" +
                " AND tmsfn.FUNCION_NUMERO = '5000'" +
                " AND tmsus.USUARIO_NUMERO = '"+usuarioNumero+"'"+
                " AND tmsus.CONTRASENA_ENCRIPTADA = '"+pwdEnc+"'";
        
        try{
            resultado = String.valueOf(em.createNativeQuery(consultaUsuario).getSingleResult());
        }catch(NoResultException ex){
            throw new UsuarioNoEncontradoException("No es usuario supervisor");
        }
        if(resultado.equals("[1]"))
            return true;
        else
            return false;
    }

    public List<TmsUsuariosTbl> queryTmsUsuariosTblx(Object usuarioNumero) {
        return em.createNamedQuery("TmsUsuariosTbl.findByUsuarioNumero").setParameter("usuarioNumero", usuarioNumero).getResultList();
    }

    public Date fechaCaducaPwd(String usuarioId){
        Date fecha = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String consulta = "SELECT TO_CHAR(NVL((t.CONTRASENA_FECHA + t.CONTRASENA_LIMITE_DIAS),SYSDATE-1),'RRRR-MM-DD HH24:MI:SS') FECHA_CADUCA" +
                " FROM TMS_USUARIOS_TBL t" +
                " WHERE t.USUARIO_ID = " + usuarioId;
        try {
            String folioS = String.valueOf(em.createNativeQuery(consulta).getSingleResult());
            folioS = folioS.replace("[","");
            folioS = folioS.replace("]","");
            fecha = format.parse(folioS);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    
   public Object queryBuscaEsquemaLocal(){
   return   em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }
   
   public Object queryBuscaTerminalLocal(){
   return   em.createNativeQuery("select  est.ESTADO_ID from tms_base_datos_config_tbl base ,tms_estados_tbl est where esquema_propio = 'S' and est.ESTADO_NOMBRE = base.nombre_terminal" ).getSingleResult();
  }    
   
   public Object queryBuscaIdTerminal(){
    return  em.createNativeQuery("select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }  
   
   public Object queryBuscaNombreTerminal(){
    return  em.createNativeQuery("select NOMBRE_TERMINAL from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }   
   
   public Object queryBuscaNombreEsquema(){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }      

   ////////////////////////////////// Fin FacadeUsuario //////////////////////////////////////

   ////////////////////////////////// inicio TmsSesionesGlobalTblFacadeRemote ////////////////////////////
    
    public long createSesion(TmsSesionesGlobalTbl tmsSesionesGlobalTbl, String ter) {
        em.persist(tmsSesionesGlobalTbl);
        String ids = ""+tmsSesionesGlobalTbl.getNumeroSesion();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsSesionesGlobalTbl.setNumeroSesion(idn);
        return tmsSesionesGlobalTbl.getNumeroSesion();
    }

    public Date fechaHoraServidor(){
        Date fecha = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String fechaS = String.valueOf(em.createNativeQuery("SELECT TO_CHAR(t.fechahorasys,'RRRR-MM-DD HH24:MI:SS') fechahora FROM tms_fechahora_v t").getSingleResult());
            fechaS = fechaS.replace("[","");
            fechaS = fechaS.replace("]","");
            fecha = format.parse(fechaS);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    
    public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'dd/MM/yyyy HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }    
    
    public List<TmsSesionesGlobalTbl> getSesionPorUsuario(String estado, long idusuario) {
        List<TmsSesionesGlobalTbl>sesion = null;
        try{
            sesion = em.createNamedQuery("TmsSesionesGlobalTbl.findByEdoSesionAndUsuario").setParameter("estadoSesion",estado).setParameter("usuarioid",idusuario).getResultList();
            em.refresh(sesion);
        }catch(Exception ex){
            System.out.println("No encontro usuario por numero");
        }
       
        return sesion;
    }
    
    public void UpdateSesion(Object pk, Date fechafin) {
      TmsSesionesGlobalTbl upses =  (TmsSesionesGlobalTbl) em.find(TmsSesionesGlobalTbl.class, pk);
      upses.setFechaFin(fechafin);
      upses.setEstadoSesion("CERRADA");
    }

    public Object buscaEquipoPorNombre(String nombre){
     String consulta = "select c.NOMBRE_EQUIPO, c.DIRECCION_MAC from tms_cajas_tbl c where c.NOMBRE_EQUIPO='"+nombre+"'";
    return em.createNativeQuery(consulta).getResultList();
  }
    
    public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return em.createNativeQuery(consulta).getSingleResult();
 }
  ////////////////////////////////// Fin TmsSesionesGlobalTblFacadeRemote ////////////////////////////

 ////////////////////////////////// Inicio Auditoria////////////////////////////
    public void createAuditoria(TmsAuditoriaTbl tmsAuditoriaTbl, String ter) {
        em.persist(tmsAuditoriaTbl);
        String ids = ""+tmsAuditoriaTbl.getAuditoriaId();
        String idnuevo = ter+""+ids;
        long idn = Long.valueOf(idnuevo);
        tmsAuditoriaTbl.setAuditoriaId(idn);
    }
    ////////////////////////////////// Fin Auditoria////////////////////////////
    
    public List<Object[]> ArbolFunciones(long MAIN_MENU_ID){
        String consulta="SELECT -1, LEVEL ,tmsML.ETIQUETA , tmsML.DESCRIPCION, NVL(tmsPt.PANTALLA_NOMBRE,'Nada'), NVL(tmsPt.RUTA_BASE,'Nada') "+
                        "FROM TMS_MENUS_ENCABEZADO_TBL tmsME "+
                        ",TMS_MENUS_LINEAS_TBL tmsML "+
                        ",TMS_FUNCIONES_TBL tmsFn "+
                        ",TMS_PANTALLAS_TBL tmsPt "+
                        "WHERE tmsME.MENU_ID = tmsML.MENU_ID "+
                        "AND (tmsML.ASIGNADO = 'Y' OR tmsML.ASIGNADO = 'S')"+ // SOLO AGREGUE ESTA LINEA, SERIA BUENO QUE EL CAMPOS ASIGNADO VALIERA S o N
                        "AND tmsML.FUNCION_ID = tmsFn.FUNCION_ID(+) "+
                        "AND tmsFn.PANTALLA_ID = tmsPt.PANTALLA_ID(+) "+
                        "CONNECT BY PRIOR tmsML.SUB_MENU_ID = tmsME.MENU_ID "+
                        "START WITH tmsME.MENU_ID = "+MAIN_MENU_ID;
        return em.createNativeQuery(consulta).getResultList();
    }
    
    public List<Object[]> ctd_menus_Nivel1(long USUARIO_ID){
            String consulta="SELECT tmsME.MENU_ID, tmsPex.PERFIL_NOMBRE, COUNT(LEVEL) "+
                            "FROM TMS_PERFILES_TBL tmsPex " +
                            ",TMS_MENUS_ENCABEZADO_TBL tmsME "+
                            ",TMS_MENUS_LINEAS_TBL tmsML "+
                            "WHERE tmsPex.MENU_ID = tmsME.MENU_ID " +
                            "AND tmsME.MENU_ID     = tmsML.MENU_ID "+
                            "AND LEVEL = 1 "+
                            "CONNECT BY PRIOR tmsML.SUB_MENU_ID = tmsME.MENU_ID "+
                            "START WITH tmsPex.PERFIL_ID IN ( "+
                                                      "SELECT "+
                                                      "tmsUP.PERFIL_ID "+
                                                      "FROM "+
                                                      "TMS_USUARIO_PERFILES_TBL tmsUP, "+
                                                      "TMS_PERFILES_TBL tmsPe "+
                                                      "WHERE "+
                                                      "tmsUP.USUARIO_ID = "+USUARIO_ID+" "+
                                                      "AND  tmsUP.PERFIL_ID  = tmsPe.PERFIL_ID " +
                                                      "AND trunc(SYSDATE)  BETWEEN trunc(tmsUP.FECHA_INICIAL) AND NVL(trunc(tmsUP.FECHA_FINAL),SYSDATE) " +
                                                      "AND trunc(SYSDATE)  BETWEEN trunc(tmsPe.FECHA_INICIAL) AND NVL(trunc(tmsPe.FECHA_FINAL),SYSDATE) "+
                                                      ") " +
                            "GROUP BY tmsME.MENU_ID, tmsPex.PERFIL_NOMBRE";
        return em.createNativeQuery(consulta).getResultList();
    }
    
    public String esUsuarioSupervisor(String usuarioNumero,String pwdEnc, String funcion){
        String respuesta = "";
        Vector resultado;
        Vector usrfuncion;
        String consultaUsuario;
        String consultaUsuarioFuncion;
        consultaUsuario = "select usr.USUARIO_ID from tms_usuarios_tbl usr where usr.USUARIO_NUMERO = '"+usuarioNumero+"'";// and usr.CONTRASENA_ENCRIPTADA = '"+pwdEnc+"'";
        consultaUsuarioFuncion = "select usr.CONTRASENA_ENCRIPTADA from " +
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
        System.out.println("select usr.USUARIO_ID from tms_usuarios_tbl usr where usr.USUARIO_NUMERO = '"+usuarioNumero+"'");
        System.out.println("El tamaño del vector de la bsuqueda de usuariso es de: "+resultado.size());
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

    public Vector queryAS(){
        Vector yvector = new Vector();
        String Consulta =
                "SELECT "+
                "tmsPar.PARAMETRO_CODIGO, tmsSp.PARAMETRO_VALOR VALOR "+
                "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar "+
                   ",TMS_TERMINAL_PARAMETROS_TBL tmsSp "+
                   ",TMS_ESTADOS_TBL tmsSer "+
                "WHERE tmsSer.ESTADO_NOMBRE = (SELECT NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL WHERE ESQUEMA_PROPIO='S') "+
                 "AND tmsPar.PARAMETRO_CONFIG_ID = tmsSp.PARAMETRO_CONFIG_ID "+
                 "AND tmsSp.TERMINAL_ID = tmsSer.ESTADO_ID "+
                 "AND tmsPar.PARAMETRO_CODIGO IN ('P_AS_IP', 'P_AS_PUERTO')";
        try{
            //System.out.println(Consulta);
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(yvector==null || yvector.size()==0) return null;
            return yvector;
        }catch(Exception nrex){
            return null;
        }
    }
}
