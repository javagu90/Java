/*
 * TmsBoletosBoleteraTblFacadeRemote.java
 *
 * Created on 10 de septiembre de 2007, 08:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsBoletosBoleteraTbl;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsBoletosBoleteraTblFacadeRemote {
    void create(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl);

    void edit(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl);

    void destroy(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl);

    TmsBoletosBoleteraTbl find(Object pk);

    List findAll();

    /**
     * <code>select o from TmsBoletosBoleteraTbl o where o.folioTarjeta = :folio and o.servicio = :nomserv and o.origenTarjeta = :origen</code>
     */
    java.util.List<TmsRecaudacion.entidad.TmsBoletosBoleteraTbl> queryTmsBoletosBoleteraTblFindByTarjeta(Object folio, Object origen);

    
}
