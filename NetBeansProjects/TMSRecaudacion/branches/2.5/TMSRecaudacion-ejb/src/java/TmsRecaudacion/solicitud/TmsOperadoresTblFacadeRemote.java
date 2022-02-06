/*
 * TmsOperadoresTblFacadeRemote.java
 *
 * Created on 11 de septiembre de 2007, 01:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsOperadoresTbl;
import java.util.List;
import javax.ejb.Remote;

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

    java.util.List<TmsRecaudacion.entidad.TmsOperadoresTbl> BuscarOperadorPorClave(String claveOp);

   
}
