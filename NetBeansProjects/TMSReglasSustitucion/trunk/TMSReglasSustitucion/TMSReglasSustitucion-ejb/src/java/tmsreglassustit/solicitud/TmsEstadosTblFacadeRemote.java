/*
 * TmsEstadosTblFacadeRemote.java
 *
 * Created on 21 de octubre de 2007, 07:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsreglassustit.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsreglassustit.entidad.TmsEstadosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsEstadosTblFacadeRemote {
    void create(TmsEstadosTbl tmsEstadosTbl);

    void edit(TmsEstadosTbl tmsEstadosTbl);

    void destroy(TmsEstadosTbl tmsEstadosTbl);

    TmsEstadosTbl find(Object pk);

    List findAll();

    java.util.List<tmsreglassustit.entidad.TmsEstadosTbl> buscarPorTipComponente(String tipo);
    
}
