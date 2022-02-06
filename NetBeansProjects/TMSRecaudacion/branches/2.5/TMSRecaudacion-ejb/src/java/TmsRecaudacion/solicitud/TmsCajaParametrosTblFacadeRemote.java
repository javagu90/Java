/*
 * TmsCajaParametrosTblFacadeRemote.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import TmsRecaudacion.entidad.TmsCajaParametrosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCajaParametrosTblFacadeRemote {
    void create(TmsCajaParametrosTbl tmsCajaParametrosTbl);

    void edit(TmsCajaParametrosTbl tmsCajaParametrosTbl);

    void destroy(TmsCajaParametrosTbl tmsCajaParametrosTbl);

    TmsCajaParametrosTbl find(Object pk);

    List findAll();
    
}
