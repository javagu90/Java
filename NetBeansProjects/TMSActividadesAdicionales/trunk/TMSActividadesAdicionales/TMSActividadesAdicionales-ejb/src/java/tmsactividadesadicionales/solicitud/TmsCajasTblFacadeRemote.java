/*
 * TmsCajasTblFacadeRemote.java
 *
 * Created on 3 de septiembre de 2007, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsactividadesadicionales.entidad.TmsCajasTbl;
import tmsactividadesadicionales.excepciones.CajaNoEncontradaException;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCajasTblFacadeRemote {
    void create(TmsCajasTbl tmsCajasTbl);

    void edit(TmsCajasTbl tmsCajasTbl);

    void destroy(TmsCajasTbl tmsCajasTbl);

    TmsCajasTbl find(Object pk);

    List findAll();

    tmsactividadesadicionales.entidad.TmsCajasTbl buscarCajaPorEquipo(String nomEq) throws CajaNoEncontradaException;

    java.lang.Object buscarEstadoSesion(long sesionId);
    
}
