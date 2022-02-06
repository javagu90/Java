/*
 * TmsTarjetasViajeTblFacadeRemote.java
 *
 * Created on 10 de noviembre de 2007, 10:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmslecturaviaxer.entidad.TmsTarjetasViajeTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsTarjetasViajeTblFacadeRemote {
    void create(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    void destroy(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    TmsTarjetasViajeTbl find(Object pk);

    List findAll();
    
}
