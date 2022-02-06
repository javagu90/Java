/*
 * TmsCorridasVentaTblFacadeRemote.java
 *
 * Created on 14 de noviembre de 2007, 05:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsroles.entidad.TmsCorridasVentaTbl;

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
