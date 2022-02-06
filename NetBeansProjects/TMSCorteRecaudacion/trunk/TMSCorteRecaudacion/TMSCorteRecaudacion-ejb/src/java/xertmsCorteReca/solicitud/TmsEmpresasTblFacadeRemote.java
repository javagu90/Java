/*
 * TmsEmpresasTblFacadeRemote.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Remote;
import xertmsCorteReca.entidad.TmsEmpresasTbl;

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
