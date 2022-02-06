/*
 * TmsSesionActividadesTblFacadeRemote.java
 *
 * Created on 14 de noviembre de 2007, 01:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmslecturaviaxer.entidad.TmsSesionActividadesTbl;

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


    tmslecturaviaxer.entidad.TmsSesionActividadesTbl create(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter);

    tmslecturaviaxer.entidad.TmsSesionActividadesTbl insertarSesionActividad(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter);
    
}
