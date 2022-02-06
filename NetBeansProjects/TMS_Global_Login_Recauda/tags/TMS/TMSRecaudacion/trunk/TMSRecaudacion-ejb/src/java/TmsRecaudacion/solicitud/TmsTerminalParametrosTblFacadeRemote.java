/*
 * TmsTerminalParametrosTblFacadeRemote.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import TmsRecaudacion.entidad.TmsTerminalParametrosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsTerminalParametrosTblFacadeRemote {
    void create(TmsTerminalParametrosTbl tmsTerminalParametrosTbl);

    void edit(TmsTerminalParametrosTbl tmsTerminalParametrosTbl);

    void destroy(TmsTerminalParametrosTbl tmsTerminalParametrosTbl);

    TmsTerminalParametrosTbl find(Object pk);

    List findAll();
    
}
