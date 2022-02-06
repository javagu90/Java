
package solicitud;

import javax.ejb.Remote;


/**
 * This is the business interface for TMSPasesTrasladoFacade enterprise bean.
 */
@Remote
public interface TMSPasesTrasladoFacadeRemote {
    java.util.Vector buscarDatosRuta(String ruta);

    java.util.Vector buscarOperador(String operador);

    java.lang.String buscarNombreEstado(String estadoId);

    java.lang.String buscarNombreServicio(String servicioId);

    boolean getValidarAutobus(String autobus);

    boolean getValidarOpeador(String opeador);

    java.util.Vector buscarImpresoraFacturas(String nombreCaja);

    java.lang.Object buscarEstadoSesion(long sesionId);

    java.lang.String eliminarPasesTraslado(String folio, String usuario) throws javax.ejb.EJBException;

    java.lang.String insertarPasesTraslado(String operador, String autobus, String ruta, String motivo, String usuario, String peaje) throws javax.ejb.EJBException;

    java.util.Vector buscarRutas();

    java.util.Vector buscarMotivos();
    
    
}
