/*
 * TmsParametrosConfigTblFacadeRemote.java
 *
 * Created on 30 de agosto de 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import TmsRecaudacion.entidad.TmsParametrosConfigTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsParametrosConfigTblFacadeRemote {
    void create(TmsParametrosConfigTbl tmsParametrosConfigTbl);

    void edit(TmsParametrosConfigTbl tmsParametrosConfigTbl);

    void destroy(TmsParametrosConfigTbl tmsParametrosConfigTbl);

    TmsParametrosConfigTbl find(Object pk);

    List findAll();

    java.lang.Object queryBuscaTerminalId();

    java.lang.Object fechaServidor();

    TmsRecaudacion.entidad.TmsParametrosConfigTbl busquedaPorCodigo(String codigo);
    
}
