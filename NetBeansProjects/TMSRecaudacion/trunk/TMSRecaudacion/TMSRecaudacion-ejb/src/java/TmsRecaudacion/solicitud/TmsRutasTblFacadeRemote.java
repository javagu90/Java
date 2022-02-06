/*
 * TmsRutasTblFacadeRemote.java
 *
 * Created on 14 de septiembre de 2007, 12:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRutasTbl;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsRutasTblFacadeRemote {
    void create(TmsRutasTbl tmsRutasTbl);

    void edit(TmsRutasTbl tmsRutasTbl);

    void destroy(TmsRutasTbl tmsRutasTbl);

    TmsRutasTbl find(Object pk);

    List findAll();
    
}
