/*
 * TmsCortesTblFacadeRemote.java
 *
 * Created on 10 de septiembre de 2007, 08:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare.solicitud;


import java.util.List;
import javax.ejb.Remote;
import tmslecturadatafare.entidad.TmsCortesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCortesTblFacadeRemote {
    void create(TmsCortesTbl tmsCortesTbl);

    void edit(TmsCortesTbl tmsCortesTbl);

    void destroy(TmsCortesTbl tmsCortesTbl);

    TmsCortesTbl find(Object pk);

    List findAll();

    tmslecturadatafare.entidad.TmsCortesTbl insertarCorte(TmsCortesTbl tmsCortesTbl, String ter);

   
    
}
