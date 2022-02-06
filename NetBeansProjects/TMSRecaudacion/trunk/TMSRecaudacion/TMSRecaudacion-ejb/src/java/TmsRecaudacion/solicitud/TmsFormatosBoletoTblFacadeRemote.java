/*
 * TmsFormatosBoletoTblFacadeRemote.java
 *
 * Created on 3 de septiembre de 2007, 09:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import TmsRecaudacion.entidad.TmsFormatosBoletoTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsFormatosBoletoTblFacadeRemote {
    void create(TmsFormatosBoletoTbl tmsFormatosBoletoTbl);

    void edit(TmsFormatosBoletoTbl tmsFormatosBoletoTbl);

    void destroy(TmsFormatosBoletoTbl tmsFormatosBoletoTbl);

    TmsFormatosBoletoTbl find(Object pk);

    List findAll();
    
}
