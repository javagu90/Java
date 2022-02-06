/*
 * TmsCajasTblFacadeRemote.java
 *
 * Created on 3 de septiembre de 2007, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturaviaxer.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tmslecturaviaxer.entidad.TmsCajasTbl;


/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsCajasTblFacadeRemote {
    void create(TmsCajasTbl tmsCajasTbl);

    void edit(TmsCajasTbl tmsCajasTbl);

    void destroy(TmsCajasTbl tmsCajasTbl);

    TmsCajasTbl find(Object pk);

    List findAll();

    TmsCajasTbl buscarCajaPorEquipo(String nomEq);

    java.lang.Object buscarEstadoSesion(long sesionId);

    java.lang.Object queryBuscaTerminalId();

    java.lang.Object queryBuscaTerminalLocal();

    java.lang.Object queryBuscaImpresoraTikets(long cajaId);
    
}
