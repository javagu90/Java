
package tmsactividadesadicionales.solicitud;

import javax.ejb.Remote;
import tmsactividadesadicionales.excepciones.UsuarioNoEncontradoException;


/**
 * This is the business interface for TmsVarios enterprise bean.
 */
@Remote
public interface TmsVariosFacadeRemote {
    java.lang.Object fechaServidor();

    java.lang.Object buscarFuncionesPorMenuId(long menuId);

    java.lang.Object buscarPorcentajeRetencion(String empresa);

    java.lang.Object buscaParametrosPorEmpresa();

    java.lang.Object buscaParametrosPorServicio();
    
    java.lang.Object queryBuscaTerminalId();

    java.lang.Object buscarUsuarioPorId(long idusr) throws UsuarioNoEncontradoException;

    java.lang.Object buscarTodasFunciones();

    java.lang.Object queryBuscaValorActualPagoActividadesAdicionales();

    java.lang.Object buscarEstadoSesion(long sesionId);

    java.lang.Object queryBuscaIdTerminal();
}
