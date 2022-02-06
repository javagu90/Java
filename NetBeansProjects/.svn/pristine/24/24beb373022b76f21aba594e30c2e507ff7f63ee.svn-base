/*
 * TmsUsuariosDatafareTblFacadeRemote.java
 *
 * Created on 1 de octubre de 2007, 12:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare.solicitud;


import java.util.List;
import javax.ejb.Remote;
import tmslecturadatafare.entidad.TmsUsuariosTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsUsuariosDatafareTblFacadeRemote {
    void create(TmsUsuariosTbl tmsUsuariosTbl);

    void edit(TmsUsuariosTbl tmsUsuariosTbl);

    void destroy(TmsUsuariosTbl tmsUsuariosTbl);

    TmsUsuariosTbl find(Object pk);

    List findAll();

    java.lang.String esUsuarioSupervisor(String usuarioNumero, String pwdEnc, String funcion) throws UsuarioNoEncontradoException;
    
}
