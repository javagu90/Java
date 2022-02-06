/*
 * TmsGlobalParametrosTblFacadeRemote.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;
import TmsRecaudacion.entidad.TmsGlobalParametrosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsGlobalParametrosTblFacadeRemote {
    void create(TmsGlobalParametrosTbl tmsGlobalParametrosTbl);

    void edit(TmsGlobalParametrosTbl tmsGlobalParametrosTbl);

    void destroy(TmsGlobalParametrosTbl tmsGlobalParametrosTbl);

    TmsGlobalParametrosTbl find(Object pk);

    List findAll();

    java.util.List<TmsRecaudacion.entidad.TmsGlobalParametrosTbl> buscarPorParamConfigId(BigDecimal pcid);

}
