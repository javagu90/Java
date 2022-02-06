/*
 * TmsBaseDatosConfigTblFacadeRemote.java
 *
 * Created on 10 de diciembre de 2007, 09:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tms_ocupacion.entidad.TmsBaseDatosConfigTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsBaseDatosConfigTblFacadeRemote {
    void create(TmsBaseDatosConfigTbl tmsBaseDatosConfigTbl);

    void edit(TmsBaseDatosConfigTbl tmsBaseDatosConfigTbl);

    void destroy(TmsBaseDatosConfigTbl tmsBaseDatosConfigTbl);

    TmsBaseDatosConfigTbl find(Object pk);

    List findAll();

    java.util.List<tms_ocupacion.entidad.TmsBaseDatosConfigTbl> buscaEsquemasTerminales(String esq);
    
}
