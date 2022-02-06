/*
 * TmsRolesMaestroTblFacadeRemote.java
 *
 * Created on 17 de diciembre de 2007, 12:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsroles.entidad.TmsRolesMaestroTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsRolesMaestroTblFacadeRemote {

    void edit(TmsRolesMaestroTbl tmsRolesMaestroTbl);

    void destroy(TmsRolesMaestroTbl tmsRolesMaestroTbl);

    TmsRolesMaestroTbl find(Object pk);

    List findAll();

    tmsroles.entidad.TmsRolesMaestroTbl create(TmsRolesMaestroTbl tmsRolesMaestroTbl);

    java.util.List<tmsroles.entidad.TmsRolesMaestroTbl> buscaRolMAestroPorNombreOferta(String nomOfer);
    
}
