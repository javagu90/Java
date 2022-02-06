/*
 * TmsTarjetasViajeTblFacadeRemote.java
 *
 * Created on 2 de noviembre de 2007, 09:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsTarjetasViajeTbl;
import java.util.List;
import javax.ejb.Remote;

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

 
    java.util.List<TmsRecaudacion.entidad.TmsTarjetasViajeTbl> queryTmsTarjetasViajeTblFindAByFolio(String foltar);

    java.util.List<TmsRecaudacion.entidad.TmsTarjetasViajeTbl> queryTmsTarjetasViajeTblFindAByFolio2(String foltar);
    
}
