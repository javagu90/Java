/*
 * TmsVtaTiposPagoTblFacadeRemote.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Remote;
import xertmsCorteReca.entidad.TmsVtaTiposPagoTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsVtaTiposPagoTblFacadeRemote {
    void create(TmsVtaTiposPagoTbl tmsVtaTiposPagoTbl);

    void edit(TmsVtaTiposPagoTbl tmsVtaTiposPagoTbl);

    void destroy(TmsVtaTiposPagoTbl tmsVtaTiposPagoTbl);

    TmsVtaTiposPagoTbl find(Object pk);

    List findAll();

    xertmsCorteReca.entidad.TmsVtaTiposPagoTbl buscarTipoPagoPorClave(String clave);
    
}
