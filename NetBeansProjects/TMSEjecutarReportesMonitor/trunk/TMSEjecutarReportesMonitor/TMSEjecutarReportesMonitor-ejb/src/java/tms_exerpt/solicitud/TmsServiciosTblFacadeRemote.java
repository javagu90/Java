/*
 * TmsServiciosTblFacadeRemote.java
 *
 * Created on 30 de mayo de 2008, 09:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_exerpt.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tms_exerpt.entidad.TmsServiciosTbl;

/**
 *
 * @author imunoz
 */
@Remote
public interface TmsServiciosTblFacadeRemote {
    void create(TmsServiciosTbl tmsServiciosTbl);

    void edit(TmsServiciosTbl tmsServiciosTbl);

    void destroy(TmsServiciosTbl tmsServiciosTbl);

    TmsServiciosTbl find(Object pk);

    List findAll();

    java.util.Vector getAutobusId(String numeroEconomico);

    java.util.Vector getOperadorId(String claveOperador);
    
}
