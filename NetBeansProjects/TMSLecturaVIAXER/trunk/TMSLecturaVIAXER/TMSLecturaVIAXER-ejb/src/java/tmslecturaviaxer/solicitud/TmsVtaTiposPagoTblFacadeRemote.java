/*
 * TmsVtaTiposPagoTblFacadeRemote.java
 *
 * Created on 3 de noviembre de 2007, 03:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmslecturaviaxer.entidad.TmsVtaTiposPagoTbl;

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

    TmsVtaTiposPagoTbl obtenerPagoPorClave(String clave);
    
}
