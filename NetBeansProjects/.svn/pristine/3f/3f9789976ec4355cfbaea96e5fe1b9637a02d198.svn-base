/*
 * TmsAccionesTblFacadeRemote.java
 *
 * Created on 9 de diciembre de 2007, 05:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.solicitud;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Remote;
import tmsedosoperador.entidad.TmsAccionesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsAccionesTblFacadeRemote {
    void create(TmsAccionesTbl tmsAccionesTbl);

    void edit(TmsAccionesTbl tmsAccionesTbl);

    void destroy(TmsAccionesTbl tmsAccionesTbl);

    TmsAccionesTbl find(Object pk);

    List findAll();

    tmsedosoperador.entidad.TmsAccionesTbl buscarPorAccion(String acc);

    java.util.List<tmsedosoperador.entidad.TmsAccionesTbl> buscarPorComponente(BigInteger comp, BigInteger comp2);
    
}
