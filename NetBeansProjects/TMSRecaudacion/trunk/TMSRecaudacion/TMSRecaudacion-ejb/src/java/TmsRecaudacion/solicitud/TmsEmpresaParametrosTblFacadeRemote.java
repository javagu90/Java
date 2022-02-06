/*
 * TmsEmpresaParametrosTblFacadeRemote.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import TmsRecaudacion.entidad.TmsEmpresaParametrosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsEmpresaParametrosTblFacadeRemote {
    void create(TmsEmpresaParametrosTbl tmsEmpresaParametrosTbl);

    void edit(TmsEmpresaParametrosTbl tmsEmpresaParametrosTbl);

    void destroy(TmsEmpresaParametrosTbl tmsEmpresaParametrosTbl);

    TmsEmpresaParametrosTbl find(Object pk);

    List findAll();
    
}
