/*
 * TmsSesionActividadesTblFacadeRemote.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Remote;
import xertmsCorteReca.entidad.TmsSesionActividadesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsSesionActividadesTblFacadeRemote {
    
    void edit(TmsSesionActividadesTbl tmsSesionActividadesTbl);

    void destroy(TmsSesionActividadesTbl tmsSesionActividadesTbl);

    TmsSesionActividadesTbl find(Object pk);

    List findAll();

    xertmsCorteReca.entidad.TmsSesionActividadesTbl create(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter);

}
