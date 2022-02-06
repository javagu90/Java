/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TMSVtaProductosER.entidad;

/**
 *
 * @author vgonzalez
 */
public class ProductoCarrito implements java.io.Serializable{
                private long productoId;
                private String empresa="";
                private String producto="";
                private long cantidad=0;
                private String folio="";
                private String clave_producto="";
                private float precio_unitario=0;
                private float subtotal=0;
                private float iva=0;
                private float total=0;
                private float retencion=0;
                private long corteId;
                private String caja;
                private long cajero;
                private String terminal;
                private long prefijo;
                private long usuarioId;
                private String tiposPagoPer;
                private boolean imprimeTicket=false;
                private String empresaDescipcion="";
    
    public ProductoCarrito(long productoId,String empresa,String producto,long cantidad, String folio, String clave_producto, float precio_unitario, float subtotal,float iva,float total,float retencion, long corteId, String caja,long cajero, String terminal, long prefijo, long usuarioId,String tiposPago, boolean impresion, String empDes ){
                this.productoId=productoId;
                this.empresa=empresa;
                this.producto=producto;
                this.cantidad=cantidad;
                this.folio=folio;
                this.clave_producto=clave_producto;
                this.precio_unitario=precio_unitario;
                this.subtotal=subtotal;
                this.iva=iva;
                this.total=total;
                this.retencion=retencion;
                this.corteId=corteId;
                this.caja= caja;
                this.cajero= cajero;
                this.terminal= terminal;
                this.prefijo=prefijo;
                this.usuarioId= usuarioId;
                this.tiposPagoPer = tiposPago;
                this.setImprimeTicket(impresion);
                this.empresaDescipcion = empDes;
    }

    /**
     * @return the productoId
     */
    public long getProductoId() {
        return productoId;
    }

    /**
     * @param productoId the productoId to set
     */
    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the cantidad
     */
    public long getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the clave_producto
     */
    public String getClave_producto() {
        return clave_producto;
    }

    /**
     * @param clave_producto the clave_producto to set
     */
    public void setClave_producto(String clave_producto) {
        this.clave_producto = clave_producto;
    }

    /**
     * @return the precio_unitario
     */
    public float getPrecio_unitario() {
        return precio_unitario;
    }

    /**
     * @param precio_unitario the precio_unitario to set
     */
    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    /**
     * @return the subtotal
     */
    public float getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the iva
     */
    public float getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(float iva) {
        this.iva = iva;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the retencion
     */
    public float getRetencion() {
        return retencion;
    }

    /**
     * @param retencion the retencion to set
     */
    public void setRetencion(float retencion) {
        this.retencion = retencion;
    }

    /**
     * @return the corteId
     */
    public long getCorteId() {
        return corteId;
    }

    /**
     * @param corteId the corteId to set
     */
    public void setCorteId(long corteId) {
        this.corteId = corteId;
    }

    /**
     * @return the caja
     */
    public String getCaja() {
        return caja;
    }

    /**
     * @param caja the caja to set
     */
    public void setCaja(String caja) {
        this.caja = caja;
    }

    /**
     * @return the cajero
     */
    public long getCajero() {
        return cajero;
    }

    /**
     * @param cajero the cajero to set
     */
    public void setCajero(long cajero) {
        this.cajero = cajero;
    }

    /**
     * @return the terminal
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * @param terminal the terminal to set
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    /**
     * @return the prefijo
     */
    public long getPrefijo() {
        return prefijo;
    }

    /**
     * @param prefijo the prefijo to set
     */
    public void setPrefijo(long prefijo) {
        this.prefijo = prefijo;
    }

    /**
     * @return the usuarioId
     */
    public long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the tiposPagoPer
     */
    public String getTiposPagoPer() {
        return tiposPagoPer;
    }

    /**
     * @param tiposPagoPer the tiposPagoPer to set
     */
    public void setTiposPagoPer(String tiposPagoPer) {
        this.tiposPagoPer = tiposPagoPer;
    }

    /**
     * @return the imprimeTicket
     */
    public boolean isImprimeTicket() {
        return imprimeTicket;
    }

    /**
     * @param imprimeTicket the imprimeTicket to set
     */
    public void setImprimeTicket(boolean imprimeTicket) {
        this.imprimeTicket = imprimeTicket;
    }

    /**
     * @return the empresaDescipcion
     */
    public String getEmpresaDescipcion() {
        return empresaDescipcion;
    }

    /**
     * @param empresaDescipcion the empresaDescipcion to set
     */
    public void setEmpresaDescipcion(String empresaDescipcion) {
        this.empresaDescipcion = empresaDescipcion;
    }

}
