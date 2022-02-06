/*
 * TmsRolesBaseTblFacadeRemote.java
 *
 * Created on 17 de diciembre de 2007, 12:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsroles.entidad.TmsRolesBaseTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsRolesBaseTblFacadeRemote {
    void edit(TmsRolesBaseTbl tmsRolesBaseTbl);

    void destroy(TmsRolesBaseTbl tmsRolesBaseTbl);

    TmsRolesBaseTbl find(Object pk);

    List findAll();

    tmsroles.entidad.TmsRolesBaseTbl create(TmsRolesBaseTbl tmsRolesBaseTbl);

    java.util.List<tmsroles.entidad.TmsRolesBaseTbl> buscaRolBase(String categoria, long rolMaestroId);

    java.util.List<tmsroles.entidad.TmsRolesBaseTbl> buscaRolBasePorRolMaestroI(long rolMaestroId);
    
}
