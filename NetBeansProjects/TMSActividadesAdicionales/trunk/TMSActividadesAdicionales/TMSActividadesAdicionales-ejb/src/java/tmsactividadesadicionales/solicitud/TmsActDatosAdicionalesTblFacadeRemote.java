/*
 * TmsActDatosAdicionalesTblFacadeRemote.java
 *
 * Created on 2 de octubre de 2007, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsactividadesadicionales.entidad.TmsActDatosAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsActDatosAdicionalesTblFacadeRemote {
    void create(TmsActDatosAdicionalesTbl tmsActDatosAdicionalesTbl);

    void edit(TmsActDatosAdicionalesTbl tmsActDatosAdicionalesTbl);

    void destroy(TmsActDatosAdicionalesTbl tmsActDatosAdicionalesTbl);

    TmsActDatosAdicionalesTbl find(Object pk);

    List findAll();
    
}
