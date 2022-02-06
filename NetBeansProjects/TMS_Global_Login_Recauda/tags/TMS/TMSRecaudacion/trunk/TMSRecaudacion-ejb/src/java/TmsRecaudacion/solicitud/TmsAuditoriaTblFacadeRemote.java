/*
 * TmsAuditoriaTblFacadeRemote.java
 *
 * Created on 1 de octubre de 2007, 11:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsAuditoriaTbl;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsAuditoriaTblFacadeRemote {

    void edit(TmsAuditoriaTbl tmsAuditoriaTbl);

    void destroy(TmsAuditoriaTbl tmsAuditoriaTbl);

    TmsAuditoriaTbl find(Object pk);

    List findAll();

    void create(TmsAuditoriaTbl tmsAuditoriaTbl, String ter);
    
}
