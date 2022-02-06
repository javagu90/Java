/*
 * TmsBoletosBoleteraTblFacadeRemote.java
 *
 * Created on 10 de noviembre de 2007, 10:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmslecturadatafare.entidad.TmsBoletosBoleteraTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsBoletosBoleteraTblFacadeRemote {

    
    void edit(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl);

    void destroy(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl);

    TmsBoletosBoleteraTbl find(Object pk);

    List findAll();

    tmslecturadatafare.entidad.TmsBoletosBoleteraTbl create(TmsBoletosBoleteraTbl tmsBoletosBoleteraTbl, String ter);
    
}
