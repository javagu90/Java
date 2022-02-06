
package solicitud;

import javax.ejb.Remote;


/**
 * This is the business interface for TmsSesionBeanFacturar enterprise bean.
 */
@Remote
public interface TmsSesionBeanFacturarRemote {

    java.util.Vector buscarCodigoBarras(String referencia);

    java.lang.String validar(String hora, String boletoid);

   // java.lang.String insertarBoletosFacturados(String id_factura, String folios, String ids, String rfc, String usuario, String dblink) throws javax.ejb.EJBException;

//    java.util.Vector buscarClienteRFC(String RFC);

    java.util.Vector buscarImpresoraFacturas(String nombreCaja);

    java.lang.String eliminarBoletosFacturados(String id_factura, String dblink, String usuario) throws javax.ejb.EJBException;

    java.lang.String buscarporFolioPreimpreso(String folio_preimpreso);

    java.lang.Object buscarEstadoSesion(long sesionId, String dblink);

//    java.util.Vector buscarClienteNombre(String nombres);

   // java.lang.String insertarcliente(int par, String nombre, String calle, String interior, String exterior, String col, String cp, String mun, String cd, String edo, String usuario, String rfc, String rfc_ant) throws javax.ejb.EJBException;

    java.util.Vector buscarBoletoNombre(String nombre, String fechaHoraCorrida, String origen, String destino, String servicio, String fecha);

    java.util.Vector buscarTerminal();

    java.util.Vector buscarOrigenesDestinos();

    java.util.Vector buscarServicios();
  
    java.lang.String fecha();

    java.util.Vector getParamGlobal(String Parametros);

    java.util.Vector getParamSucursal(String Parametros, String Sucrusal_ID);

    java.util.Vector GeneraFactura(String P_DATOS_RECEPTOR, String P_RFC_EMISOR, String P_DATOS_FACTURA, String slineasfacturas, String P_TOTALES, String P_IMPUESTO, String P_RETENCION, String P_TRASLADO);

    java.lang.String CancelarFact_Elect(String LlaveFactura);

    java.lang.String insertarBoletosFacturados(String id_factura, String folios, String ids, String rfc, String usuario, String dblink, String RutaPDF, String LlaveFactura) throws javax.ejb.EJBException;

    java.lang.String buscarFolioPreimpreso(String folio_preimpreso);

    java.util.Vector buscarCodigoBarrasReferencia(String referencia);

    java.lang.String TieneFactura(String foliopreimpreso);

    java.lang.String getLlaveFactura(String FolioFactura);

    java.lang.String getRutaPDFFactura(String FolioFactura);

    java.lang.String insertarcliente(int par, String nombre, String calle, String interior, String exterior, String col, String cp, String mun, String cd, String edo, String usuario, String rfc, String rfc_ant, String email) throws javax.ejb.EJBException;
    
}
