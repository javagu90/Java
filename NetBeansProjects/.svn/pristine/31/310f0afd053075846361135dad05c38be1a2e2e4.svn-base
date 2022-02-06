/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TMSVtaProductosER.solicitud;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TMSVtaProductosERFacadeRemote {

    public java.util.Vector getTerminales();

    public java.lang.String getTerminalActual();

    public java.util.Vector getEmpresas();

    public java.util.List<TMSVtaProductosER.entidad.Producto> getProductos(java.lang.String clave, java.lang.String nombre, java.util.Vector terminales, java.util.Vector empresas);

    public java.lang.String agregarProducto(TMSVtaProductosER.entidad.Producto pro, long usuarioId);

    public java.lang.String actualizarProducto(TMSVtaProductosER.entidad.Producto pro, long usuarioId,String eliminarTarifas);

    public java.util.List<TMSVtaProductosER.entidad.Producto> getProductosParaVenta();

    public boolean registrarVentaProducto(java.lang.String empresa, java.lang.String producto, long cantidad, java.lang.String folio, java.lang.String clave_producto, float precio_unitario, float subtotal, float iva, float total, float retencion, long corteId, java.lang.String caja, long cajero, java.lang.String terminal, long terminalId, long usuarioId);

    public java.lang.String getTicketCompra(long terminalId);

    public java.util.List<TMSVtaProductosER.entidad.Producto> getProductosParaVentaCarrito();

    public java.util.Vector getImpresora(java.lang.String caja);

    
}
