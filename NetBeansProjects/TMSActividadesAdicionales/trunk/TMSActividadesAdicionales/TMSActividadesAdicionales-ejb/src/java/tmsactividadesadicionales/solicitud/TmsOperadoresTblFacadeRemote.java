/*
 * TmsOperadoresTblFacadeRemote.java
 *
 * Created on 2 de octubre de 2007, 01:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsactividadesadicionales.entidad.TmsOperadoresTbl;

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

    java.util.List<tmsactividadesadicionales.entidad.TmsOperadoresTbl> BuscarOperadorPorClave(String claveOp);
    
}
