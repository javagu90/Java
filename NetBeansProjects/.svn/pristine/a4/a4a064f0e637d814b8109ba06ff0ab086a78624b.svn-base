/*
 * TmsUsuariosTblFacadeRemote.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;
import xertmsCorteReca.entidad.TmsUsuariosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsUsuariosTblFacadeRemote {
    void create(TmsUsuariosTbl tmsUsuariosTbl);

    void edit(TmsUsuariosTbl tmsUsuariosTbl);

    void destroy(TmsUsuariosTbl tmsUsuariosTbl);

    TmsUsuariosTbl find(Object pk);

    List findAll();

    java.lang.Object buscarRecolecciones(String numUsr, BigDecimal corteId);
    
}
