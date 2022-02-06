/*
 * TmsRolesBaseLineasTblFacadeRemote.java
 *
 * Created on 17 de diciembre de 2007, 12:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsroles.entidad.TmsRolesBaseLineasTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsRolesBaseLineasTblFacadeRemote {
    void create(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl);

    void edit(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl);

    void destroy(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl);

    TmsRolesBaseLineasTbl find(Object pk);

    List findAll();

    java.util.List<tmsroles.entidad.TmsRolesBaseLineasTbl> buscaLineasPorRolId(long rolBaseId);
    
}
