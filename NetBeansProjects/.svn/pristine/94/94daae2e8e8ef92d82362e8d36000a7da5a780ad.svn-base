/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package solicitud;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface FacturaTMSREPCONTROLBeanRemote {

    public java.util.Vector buscarClienteRFC(java.lang.String RFC);

    public java.util.Vector buscarClienteNombre(java.lang.String nombres);

    public java.util.Vector GeneraFactura(java.lang.String P_PARAMETROS_FACTURA, java.lang.String P_PARAMETROS_PRODUCTOS, java.lang.String modo, java.lang.String tipoFactura, long clienteId, float monto, java.lang.String usuarioId);

    public java.lang.String getRutaPDFFactura(java.lang.String FolioFactura);

    public java.util.Vector buscarDatosFacCan(java.lang.String folio);

    public java.lang.String boletosFolCanFact(java.lang.String folio);

    public java.util.Vector cancelarFactura(java.lang.String P_PARAMETROS_FACTURA, java.lang.String P_PARAMETROS_PRODUCTOS, java.lang.String modo, java.lang.String tipoFactura, long clienteId, float monto, java.lang.String usuarioId);

    public java.lang.String insertarcliente(int par, java.lang.String nombre, java.lang.String calle, java.lang.String interior, java.lang.String exterior, java.lang.String col, java.lang.String cp, java.lang.String mun, java.lang.String cd, java.lang.String edo, java.lang.String usuario, java.lang.String rfc, java.lang.String rfc_ant, java.lang.String email);
    
}
