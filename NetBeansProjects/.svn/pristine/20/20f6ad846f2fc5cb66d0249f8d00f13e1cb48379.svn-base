/*
 * TmsCorridasTblFacadeRemote.java
 *
 * Created on 14 de noviembre de 2007, 05:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsroles.entidad.TmsCorridasTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCorridasTblFacadeRemote {

    void edit(TmsCorridasTbl tmsCorridasTbl);

    void destroy(TmsCorridasTbl tmsCorridasTbl);

    TmsCorridasTbl find(Object pk);

    List findAll();

    tmsroles.entidad.TmsCorridasTbl create(TmsCorridasTbl tmsCorridasTbl, String ter);

    
}
