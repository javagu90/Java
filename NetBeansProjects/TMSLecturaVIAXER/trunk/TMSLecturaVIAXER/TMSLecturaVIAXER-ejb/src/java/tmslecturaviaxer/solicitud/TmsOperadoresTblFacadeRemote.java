/*
 * TmsOperadoresTblFacadeRemote.java
 *
 * Created on 10 de noviembre de 2007, 10:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmslecturaviaxer.entidad.TmsOperadoresTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsOperadoresTblFacadeRemote {
    void create(TmsOperadoresTbl tmsOperadoresTbl);

    void edit(TmsOperadoresTbl tmsOperadoresTbl);

    void destroy(TmsOperadoresTbl tmsOperadoresTbl);

    TmsOperadoresTbl find(Object pk);

    List findAll();
    
}
