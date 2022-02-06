/*
 * TmsTarjetasViajeTblFacade.java
 *
 * Created on 10 de diciembre de 2007, 12:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsmodiftarviaje.solicitud;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tmsmodiftarviaje.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsTarjetasViajeTblFacade implements TmsTarjetasViajeTblFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of TmsTarjetasViajeTblFacade */
    public TmsTarjetasViajeTblFacade() {
    }

    public void create(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.persist(tmsTarjetasViajeTbl);
    }

    public void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
    }

    public void destroy(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        em.merge(tmsTarjetasViajeTbl);
        em.remove(tmsTarjetasViajeTbl);
    }

    public TmsTarjetasViajeTbl find(Object pk) {
        return (TmsTarjetasViajeTbl) em.find(TmsTarjetasViajeTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsTarjetasViajeTbl as o").getResultList();
    }
    
    public TmsTarjetasViajeTbl buscaTarjetaPorFolio(String folio, BigInteger edo){
        TmsTarjetasViajeTbl tarjeta = null;
        try {
            tarjeta = (TmsTarjetasViajeTbl)em.createNamedQuery("TmsTarjetasViajeTbl.findByFolioTarjeta").setParameter("folioTarjeta",folio).getSingleResult();
        } catch(Exception ex){
            System.out.println("Excepcion controlada...");
            ex.printStackTrace();
        }
        if(tarjeta!=null)
            em.refresh(tarjeta);
        return tarjeta;
    }
    
    public Object buscaCorrida(long idCor){
        return em.createNativeQuery("select cor.CLAVE_CORRIDA, cor.ORIGEN, cor.DESTINO, to_char(cor.FECHA_HORA_CORRIDA,'dd/mm/yyyy'), to_char(cor.FECHA_HORA_CORRIDA,'HH:MI') from tms_corridas_venta_tbl cor where cor.corrida_id = "+idCor).getResultList();
    }
    
     public Object buscaEstadoTajeta(String  edo){
        return em.createNativeQuery("select tar.ESTADO_TARJETA_VIAJE_ID from tms_estados_tarjeta_viaje_tbl tar where tar.NOMBRE_ESTADO= '"+edo+"'").getResultList();
    }   
     
        public Object buscaNombreEstadoTajeta(long  id){
        String qry = "select tar.NOMBRE_ESTADO from tms_estados_tarjeta_viaje_tbl tar where tar.ESTADO_TARJETA_VIAJE_ID = "+id;
        //System.out.println("query de Estado: "+qry);    
        return em.createNativeQuery(qry).getResultList();
    }  
     
   public Object queryBuscaNombreEsquema(){
    return  em.createNativeQuery("select nombre_esquema from tms_base_datos_config_tbl where esquema_propio = 'S'" ).getSingleResult();
  }  

  public Object fechaServidor(){
        String consulta="SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd HH24:MI:SS') FROM DUAL";
        try{
            return em.createNativeQuery(consulta).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
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
}
