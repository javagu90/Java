
package solicitud;

import javax.ejb.Remote;


/**
 * This is the business interface for TmsSesionBeanFacturar enterprise bean.
 */
@Remote
public interface TmsSesionBeanFacturarTMSRemote {

    java.util.Vector buscarCodigoBarras(String referencia);

    java.lang.String validar(String hora, String boletoid);

   // java.lang.String insertarBoletosFacturados(String id_factura, String folios, String ids, String rfc, String usuario, String dblink) throws javax.ejb.EJBException;

    //java.util.Vector buscarClienteRFC(String RFC);

    java.util.Vector buscarImpresoraFacturas(String nombreCaja);

  //  java.lang.String eliminarBoletosFacturados(String id_factura, String dblink, String usuario) throws javax.ejb.EJBException;

    java.util.Vector buscarporFolioPreimpreso(String folio_preimpreso);

    java.lang.Object buscarEstadoSesion(long sesionId, String dblink);

    //java.util.Vector buscarClienteNombre(String nombres);

    java.lang.String insertarcliente(int par, String nombre, String calle, String interior, String exterior, String col, String cp, String mun, String cd, String edo, String usuario, String rfc, String rfc_ant) throws javax.ejb.EJBException;

    java.util.Vector buscarBoletoNombre(String nombre, String fechaHoraCorrida, String origen, String destino, String servicio, String fecha);

    java.util.Vector buscarTerminal();

    java.util.Vector buscarOrigenesDestinos();

    java.util.Vector buscarServicios();

    java.lang.String fecha();  

    java.util.Vector getParamGlobal(String Parametros);

    java.util.Vector getParamSucursal(String Parametros, String Sucrusal_ID);

    java.util.Vector buscarFolioPreimpreso(String folio_preimpreso);

    java.util.Vector buscarCodigoBarrasReferencia(String referencia);

    String getTerminalID(String NombreTerminal);

    java.util.Vector getParamTerminal(String Parametros, String Sucursal_ID);

   
    java.util.Vector getCiudadVentaList();

    java.util.Vector ValidaBoletoAFacturarPRC(String CiudadVentaID, String Referencia, String FolioPreimpreso);

   
}
