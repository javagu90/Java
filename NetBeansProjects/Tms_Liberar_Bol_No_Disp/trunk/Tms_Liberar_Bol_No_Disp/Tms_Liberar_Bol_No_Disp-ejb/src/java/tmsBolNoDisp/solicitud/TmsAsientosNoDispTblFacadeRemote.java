/*
 * TmsAsientosNoDispTblFacadeRemote.java
 *
 * Created on 7 de diciembre de 2007, 07:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsBolNoDisp.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsBolNoDisp.entidad.TmsAsientosNoDispTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsAsientosNoDispTblFacadeRemote {
    void create(TmsAsientosNoDispTbl tmsAsientosNoDispTbl);

    void edit(TmsAsientosNoDispTbl tmsAsientosNoDispTbl);

    void destroy(TmsAsientosNoDispTbl tmsAsientosNoDispTbl);

    TmsAsientosNoDispTbl find(Object pk);

    List findAll();
    
}
