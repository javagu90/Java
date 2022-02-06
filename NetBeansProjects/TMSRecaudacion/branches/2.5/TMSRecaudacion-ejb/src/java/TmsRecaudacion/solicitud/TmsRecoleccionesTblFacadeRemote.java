/*
 * TmsRecoleccionesTblFacadeRemote.java
 *
 * Created on 26 de septiembre de 2007, 06:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsRecoleccionesTbl;
import java.util.List;
import javax.ejb.Remote;

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
