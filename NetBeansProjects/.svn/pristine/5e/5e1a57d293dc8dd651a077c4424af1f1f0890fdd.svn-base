/*
 * TmsSesionActividadesTblFacadeRemote.java
 *
 * Created on 26 de septiembre de 2007, 06:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsSesionActividadesTbl;
import java.util.List;
import javax.ejb.Remote;

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


    void create(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter);

    TmsRecaudacion.entidad.TmsSesionActividadesTbl insertarSesionActividad(TmsSesionActividadesTbl tmsSesionActividadesTbl, String ter);

    
}
