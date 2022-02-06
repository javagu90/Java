/*
 * TmsBoletosNoDisponiblesVFacadeRemote.java
 *
 * Created on 7 de diciembre de 2007, 06:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsBolNoDisp.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsBolNoDisp.entidad.*;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsBoletosNoDisponiblesVFacadeRemote {
    void create(TmsBoletosNoDisponiblesV tmsBoletosNoDisponiblesV);

    void edit(TmsBoletosNoDisponiblesV tmsBoletosNoDisponiblesV);

    void destroy(TmsBoletosNoDisponiblesV tmsBoletosNoDisponiblesV);

    TmsBoletosNoDisponiblesV find(Object pk);

    java.util.List<tmsBolNoDisp.entidad.TmsBoletosNoDisponiblesV> findAll();

    
}
