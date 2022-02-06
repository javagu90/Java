/*
 * TmsRecoleccionesTblFacadeRemote.java
 *
 * Created on 29 de noviembre de 2007, 08:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmslecturaviaxer.entidad.TmsRecoleccionesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsRecoleccionesTblFacadeRemote {

    void edit(TmsRecoleccionesTbl tmsRecoleccionesTbl);

    void destroy(TmsRecoleccionesTbl tmsRecoleccionesTbl);

    TmsRecoleccionesTbl find(Object pk);

    List findAll();

    void create(TmsRecoleccionesTbl tmsRecoleccionesTbl, String ter);
    
}
