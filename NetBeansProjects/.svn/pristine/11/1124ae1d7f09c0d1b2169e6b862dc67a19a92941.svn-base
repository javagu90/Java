
package tmstrafico.solicitud;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import tmstrafico.entidad.TmsAuditoriaTbl;
import tmstrafico.entidad.TmsSesionesGlobalTbl;
import tmstrafico.entidad.TmsUsuariosTbl;
import tmstrafico.excepcion.UsuarioNoEncontradoException;


/**
 * This is the business interface for TmsSesionGeneralFacade enterprise bean.
 */
@Remote
public interface TmsSesionGeneralTraficFacadeRemote {
    List<Object[]> ArbolFunciones(long MAIN_MENU_ID);
    
    List<Object[]> ctd_menus_Nivel1(long USUARIO_ID);

    java.lang.String esUsuarioSupervisor(String usuarioNumero, String pwdEnc, String funcion);

    java.lang.Object buscarEstadoSesion(long sesionId);

    void editUsuario(TmsUsuariosTbl tmsUsuariosTbl);

    tmstrafico.entidad.TmsUsuariosTbl getUsuarioPorNumero(String usuarioNumero) throws UsuarioNoEncontradoException;

    int getPerfilesUsuario(String usuarioNumero);

    boolean esUsuarioSupervisor(String usuarioNumero, String pwdEnc) throws UsuarioNoEncontradoException;

    java.util.List<tmstrafico.entidad.TmsUsuariosTbl> queryTmsUsuariosTblx(Object usuarioNumero);

    java.util.Date fechaCaducaPwd(String usuarioId);

    java.lang.Object queryBuscaEsquemaLocal();

    java.lang.Object queryBuscaTerminalLocal();

    java.lang.Object queryBuscaIdTerminal();

    java.lang.Object queryBuscaNombreTerminal();

    java.lang.Object queryBuscaNombreEsquema();

    java.util.Date fechaHoraServidor();

    java.lang.Object fechaServidor();

    java.util.List<tmstrafico.entidad.TmsSesionesGlobalTbl> getSesionPorUsuario(String estado, long idusuario);

    void UpdateSesion(Object pk, Date fechafin);

    java.lang.Object buscaEquipoPorNombre(String nombre);

    long createSesion(TmsSesionesGlobalTbl tmsSesionesGlobalTbl, String ter);

    void createAuditoria(TmsAuditoriaTbl tmsAuditoriaTbl, String ter);

    java.util.Vector queryAS();

}
