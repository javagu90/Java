/*
 * TmsRecoleccionesTblFacadeRemote.java
 *
 * Created on 15 de agosto de 2007, 08:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.solicitud;

import java.util.List;
import javax.ejb.Remote;
import xertmsCorteReca.entidad.TmsRecoleccionesTbl;

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

    java.lang.Object queryBuscaIdTerminal();

    void create(TmsRecoleccionesTbl tmsRecoleccionesTbl, String ter);
    
}
