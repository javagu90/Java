/*
 * TmsUsuariosTblFacade.java
 *
 * Created on 1 de octubre de 2007, 12:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;


import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tmsactividadesadicionales.entidad.TmsUsuariosTbl;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class TmsUsuariosTblFacade implements TmsUsuariosTblFacadeRemote {

    @PersistenceContext(unitName="TMSActividadesAdicionales-ejbCentral")
    private EntityManager em;
    
    /** Creates a new instance of TmsUsuariosTblFacade */
    public TmsUsuariosTblFacade() {
    }

    public void create(TmsUsuariosTbl tmsUsuariosTbl) {
        em.persist(tmsUsuariosTbl);
    }

    public void edit(TmsUsuariosTbl tmsUsuariosTbl) {
        em.merge(tmsUsuariosTbl);
    }

    public void destroy(TmsUsuariosTbl tmsUsuariosTbl) {
        em.merge(tmsUsuariosTbl);
        em.remove(tmsUsuariosTbl);
    }

    public TmsUsuariosTbl find(Object pk) {
        return (TmsUsuariosTbl) em.find(TmsUsuariosTbl.class, pk);
    }

    public List findAll() {
        return em.createQuery("select object(o) from TmsUsuariosTbl as o").getResultList();
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
