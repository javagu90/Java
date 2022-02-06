
package xertms.solicitud;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import tmsgloballogin.entidad.TmsAuditoriaTbl;
import tmsgloballogin.entidad.TmsSesionesGlobalTbl;
import tmsgloballogin.entidad.TmsUsuariosTbl;
import tmsgloballogin.excepcion.UsuarioNoEncontradoException;

/**
 * This is the business interface for TmsSesionGeneralFacade enterprise bean.
 */
@Remote
public interface TmsSesionGeneralFacadeRemote {
    List<Object[]> ArbolFunciones(long MAIN_MENU_ID);
    
    List<Object[]> ctd_menus_Nivel1(long USUARIO_ID);

    java.lang.String esUsuarioSupervisor(String usuarioNumero, String pwdEnc, String funcion);

    java.lang.Object buscarEstadoSesion(long sesionId);

    void editUsuario(TmsUsuariosTbl tmsUsuariosTbl);

    tmsgloballogin.entidad.TmsUsuariosTbl getUsuarioPorNumero(String usuarioNumero) throws UsuarioNoEncontradoException;

    int getPerfilesUsuario(String usuarioNumero);

    boolean esUsuarioSupervisor(String usuarioNumero, String pwdEnc) throws UsuarioNoEncontradoException;

    java.util.List<tmsgloballogin.entidad.TmsUsuariosTbl> queryTmsUsuariosTblx(Object usuarioNumero);

    java.util.Date fechaCaducaPwd(String usuarioId);

    java.lang.Object queryBuscaEsquemaLocal();

    java.lang.Object queryBuscaTerminalLocal();

    java.lang.Object queryBuscaIdTerminal();

    java.lang.Object queryBuscaNombreTerminal();

    java.lang.Object queryBuscaNombreEsquema();

    java.util.Date fechaHoraServidor();

    java.lang.Object fechaServidor();

    java.util.List<tmsgloballogin.entidad.TmsSesionesGlobalTbl> getSesionPorUsuario(String estado, long idusuario);

    void UpdateSesion(Object pk, Date fechafin);

    java.lang.Object buscaEquipoPorNombre(String nombre);

    long createSesion(TmsSesionesGlobalTbl tmsSesionesGlobalTbl, String ter);

    void createAuditoria(TmsAuditoriaTbl tmsAuditoriaTbl, String ter);

    java.util.Vector queryAS();

    java.lang.Object queryDatosEsquemaLocal();

}
