
package soliciutdAcumularEstrellas;

import javax.ejb.Remote;


/**
 * This is the business interface for TMSAcumulacionCentralFacadeBean enterprise bean.
 */
@Remote
public interface TMSAcumularCentralFacadeRemote {
    
    java.util.Vector buscarOrigenesDestinos();

    java.util.Vector buscarServicios();

    java.util.Vector buscarEmpresas();

    java.util.Vector buscarTiposPasajero();

    java.lang.String _ObtieneFechaHoraBDLealtad();

    java.lang.String getTerminalLocal();

    java.util.Vector getParametrosTermial(long terminal);

    java.util.Vector buscaRutaBoleto(String servicio, String empresa, String origen, String destino);

    java.util.Vector buscaDatosLealtadBoleto(String ruta);

    void insertaRegistroLealtad(String boleto_id, String folio_preimpreso, String preoducto, String ciudad_venta, String tipo_operacion, String num_tarjeta, String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento, long user, String pDBSchema, String pDBLink);

    java.lang.String getTipoPagosValidoslealtad();

    //java.util.Vector buscaFolio(String folio, String tiposPago);

    java.util.Vector buscaRegistroLealtad(String operacion, String folio);

    public java.lang.String buscaParametroCajaLealtad(java.lang.String cajaId, java.lang.String parametro);

   
}
