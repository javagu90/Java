/*
 * TmsEmpresasTblFacadeRemote.java
 *
 * Created on 10 de septiembre de 2007, 08:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsEmpresasTbl;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsEmpresasTblFacadeRemote {
    void create(TmsEmpresasTbl tmsEmpresasTbl);

    void edit(TmsEmpresasTbl tmsEmpresasTbl);

    void destroy(TmsEmpresasTbl tmsEmpresasTbl);

    TmsEmpresasTbl find(Object pk);

    List findAll();
    
}
