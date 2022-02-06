/*
 * TmsAutobusesTblFacadeRemote.java
 *
 * Created on 13 de diciembre de 2007, 10:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsroles.entidad.TmsAutobusesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsAutobusesTblFacadeRemote {
    void create(TmsAutobusesTbl tmsAutobusesTbl);

    void edit(TmsAutobusesTbl tmsAutobusesTbl);

    void destroy(TmsAutobusesTbl tmsAutobusesTbl);

    TmsAutobusesTbl find(Object pk);

    List findAll();

    java.util.List<tmsroles.entidad.TmsAutobusesTbl> buscaBusPorNumeroEconomico(String numEcon);

    
}
