/*
 * TmsActividadesSesionTblFacadeRemote.java
 *
 * Created on 23 de octubre de 2007, 08:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.ActividadNoEncontradoException;
import TmsRecaudacion.entidad.TmsActividadesSesionTbl;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsActividadesSesionTblFacadeRemote {
    void create(TmsActividadesSesionTbl tmsActividadesSesionTbl);

    void edit(TmsActividadesSesionTbl tmsActividadesSesionTbl);

    void destroy(TmsActividadesSesionTbl tmsActividadesSesionTbl);

    TmsActividadesSesionTbl find(Object pk);

    List findAll();

    TmsRecaudacion.entidad.TmsActividadesSesionTbl buscarActidadPorClave(String clave);

    TmsRecaudacion.entidad.TmsActividadesSesionTbl getActividadPorClave(String claveActividad) throws ActividadNoEncontradoException;
    
}
