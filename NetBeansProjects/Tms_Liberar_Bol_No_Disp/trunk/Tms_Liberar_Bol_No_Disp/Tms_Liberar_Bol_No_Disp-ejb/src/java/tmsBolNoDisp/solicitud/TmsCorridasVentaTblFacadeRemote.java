/*
 * TmsCorridasVentaTblFacadeRemote.java
 *
 * Created on 7 de diciembre de 2007, 07:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsBolNoDisp.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsBolNoDisp.entidad.TmsCorridasVentaTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCorridasVentaTblFacadeRemote {
    void create(TmsCorridasVentaTbl tmsCorridasVentaTbl);

    void edit(TmsCorridasVentaTbl tmsCorridasVentaTbl);

    void destroy(TmsCorridasVentaTbl tmsCorridasVentaTbl);

    TmsCorridasVentaTbl find(Object pk);

    List findAll();
    
}
