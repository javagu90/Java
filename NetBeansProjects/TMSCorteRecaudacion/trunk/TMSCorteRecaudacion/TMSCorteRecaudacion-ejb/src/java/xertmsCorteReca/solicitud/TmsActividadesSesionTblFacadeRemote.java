/*
 * TmsActividadesSesionTblFacadeRemote.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Remote;
import xertmsCorteReca.entidad.TmsActividadesSesionTbl;

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

    xertmsCorteReca.entidad.TmsActividadesSesionTbl buscarActividadPorClave(String clave);
    
}
