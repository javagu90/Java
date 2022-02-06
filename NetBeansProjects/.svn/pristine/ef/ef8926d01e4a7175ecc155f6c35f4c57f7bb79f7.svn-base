/*
 * TmsEmpresasTblFacadeRemote.java
 *
 * Created on 9 de octubre de 2007, 10:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tms_ocupacion.entidad.TmsEmpresasTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsEmpresasTblFacadeRemote {
    void create(TmsEmpresasTbl tmsEmpresasTbl);

    void edit(TmsEmpresasTbl tmsEmpresasTbl);

    void destroy(TmsEmpresasTbl tmsEmpresasTbl);

    TmsEmpresasTbl find(Object pk);

    List findAll();
    
}
