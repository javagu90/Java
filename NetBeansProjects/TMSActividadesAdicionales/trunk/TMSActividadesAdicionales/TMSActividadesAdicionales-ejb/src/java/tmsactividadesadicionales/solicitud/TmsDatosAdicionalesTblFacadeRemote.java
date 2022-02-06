/*
 * TmsDatosAdicionalesTblFacadeRemote.java
 *
 * Created on 2 de octubre de 2007, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsactividadesadicionales.entidad.TmsDatosAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsDatosAdicionalesTblFacadeRemote {
    void create(TmsDatosAdicionalesTbl tmsDatosAdicionalesTbl);

    void edit(TmsDatosAdicionalesTbl tmsDatosAdicionalesTbl);

    void destroy(TmsDatosAdicionalesTbl tmsDatosAdicionalesTbl);

    TmsDatosAdicionalesTbl find(Object pk);

    List findAll();
    
}
