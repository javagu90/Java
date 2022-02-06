/*
 * TmsTiposJornadaTblFacadeRemote.java
 *
 * Created on 9 de diciembre de 2007, 05:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsedosoperador.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmsedosoperador.entidad.TmsTiposJornadaTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsTiposJornadaTblFacadeRemote {
    void create(TmsTiposJornadaTbl tmsTiposJornadaTbl);

    void edit(TmsTiposJornadaTbl tmsTiposJornadaTbl);

    void destroy(TmsTiposJornadaTbl tmsTiposJornadaTbl);

    TmsTiposJornadaTbl find(Object pk);

    List findAll();
    
}
