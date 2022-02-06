
package solicitud;

import entidad.TmsCorridasVenta;
import java.math.BigDecimal;
import javax.ejb.Remote;


/**
 * This is the business interface for TMSSesionCodigoBarras enterprise bean.
 */
@Remote
public interface TMSSesionCodBarRemote {
    entidad.TmsBoletosVentaTbl buscarCodigoBarras(String referencia);

    java.lang.String buscardblink(String folio_preimpreso, boolean ban);

    entidad.TmsBoletosVentaTbl buscarFolioPreimpreso(String foliopreimpreso);

    java.lang.String[] validar(String claveCorrida, String corrida, String referencia);

    java.util.Vector buscarBoletoNombre(String nombre, String fechaHoraCorrida, String origen, String destino, String servicio, String fecha);

    java.util.Vector buscarOrigenesDestinos();

    java.util.Vector buscarServicios();

    java.util.Vector buscarTerminal();
    
    java.lang.String fecha();

    java.lang.String[] validarEstado(String claveCorrida, String referencia);

    java.lang.String[] validarEstadoNormal(String claveCorrida, String folioP);

    java.lang.String[] validarNormal(String corrida, entidad.TmsBoletosVentaTbl nombre);

    java.lang.String getCorridaFH(String corrida);

    java.lang.String esUsuarioSupervisor(String usuarioNumero, String pwdEnc, String funcion);

    java.util.Vector getAsientosDisponibles(String IDCorrida);

    java.util.Vector getFuncionesUsuario(String UsuarioID);

    int registra(BigDecimal idBoleto, String corridaID, String usuarioID, String estado, String fecha);

    int getPersonasAbordo(String corridaCve);

    java.lang.String[] validarBolInternet(String claveCorrida, String referencia);

}
