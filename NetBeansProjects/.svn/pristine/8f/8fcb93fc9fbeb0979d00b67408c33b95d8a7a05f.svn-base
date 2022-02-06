/*
 * TmsServiciosGastosTblFacadeRemote.java
 *
 * Created on 13 de septiembre de 2007, 08:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsServiciosGastosTbl;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsServiciosGastosTblFacadeRemote {
    void create(TmsServiciosGastosTbl tmsServiciosGastosTbl);

    void edit(TmsServiciosGastosTbl tmsServiciosGastosTbl);

    void destroy(TmsServiciosGastosTbl tmsServiciosGastosTbl);

    TmsServiciosGastosTbl find(Object pk);

    List findAll();

    java.util.List<TmsRecaudacion.entidad.TmsServiciosGastosTbl> buscaGastosPorServicio(BigDecimal idserv);
    
}
