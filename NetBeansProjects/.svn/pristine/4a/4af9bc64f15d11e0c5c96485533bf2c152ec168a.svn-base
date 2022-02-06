/*
 * TmsRecaudacionLineasTblFacadeRemote.java
 *
 * Created on 3 de noviembre de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRecaudacionLineasTbl;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsRecaudacionLineasTblFacadeRemote {

    void edit(TmsRecaudacionLineasTbl tmsRecaudacionLineasTbl);

    void destroy(TmsRecaudacionLineasTbl tmsRecaudacionLineasTbl);

    TmsRecaudacionLineasTbl find(Object pk);

    List findAll();

    void create(TmsRecaudacionLineasTbl tmsRecaudacionLineasTbl, String ter);
    
}
