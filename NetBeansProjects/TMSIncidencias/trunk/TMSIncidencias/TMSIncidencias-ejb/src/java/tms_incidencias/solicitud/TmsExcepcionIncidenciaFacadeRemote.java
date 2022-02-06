/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tms_incidencias.solicitud;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;
import tms_excepciones_incidencias.exceptions.ControlException;
import tms_excepciones_incidencias.exceptions.ExisteExcepcionIncidenciaException;
import tms_incidencias.entidad.TmsExcepcionIncidenciaTbl;

/**
 *Interface Excepcion de Incidencia para el manejo de EJB
 * @author Osvaldo Sanchez
 */
@Remote
public interface TmsExcepcionIncidenciaFacadeRemote {

    /**
     * Obtiene lista con todas las excepciones de incidencias
     * @return List<TmsExcepcionIncidenciaTbl> Lista con las excepciones de incidencias
     */
     Vector getAllExcepcionesIncidencias()throws ControlException;

     /**
      * Obtiene lista de la actividades adicionales
      * para obtener la descripcion de la incidencia
      * mediante una accion
      * @return
      */
     Vector getActAdicionales();
     /**
      * Guarda una excepcion de incidencia en la
      * bae de datos
      * @param excepcionIncidencia
      * @return true si se guardo exitosamente en otro caso false
      */
     void guardarExcepcionIncidencia(TmsExcepcionIncidenciaTbl excepcionIncidencia)throws ControlException,ExisteExcepcionIncidenciaException;
    /**
     * Elimina una excepcion de incidencia de la base de datos
     * @param idExcepcionIncidencia
     */
     void eliminarExcepcionIncidencia(Short idExcepcionIncidencia);
    
     /**
      * Valida si la incidencia a registrar es valida
      * y no se encuentre en la tabla de excepciones no permitidas
      * @param claveOperador
      * @param fecha1
      * @param fecha2
      * @param idIncidencia
      * @return
      */
      boolean validaExcepciones(String claveOperador, String fecha1, String fecha2,Long idIncidencia);
      
      /**
       * Valida si un usuario tiene permisos
       * para visualizar o ejecutar una funcion o menu
       * @param idUsuario id de usuario
       * @param numeroFuncion numero de funcion asociada a los permisos del usuario
       * @return true si tiene permisos
       */
      boolean tienePermisosUsuario(Long idUsuario,String numeroFuncion);
}
