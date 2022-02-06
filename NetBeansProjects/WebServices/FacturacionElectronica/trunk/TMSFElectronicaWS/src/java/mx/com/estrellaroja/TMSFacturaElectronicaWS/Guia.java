/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.estrellaroja.TMSFacturaElectronicaWS;

/**
 *
 * @author Osvaldo Sanchez
 */
public class Guia implements java.io.Serializable{
    private String origen;
    private String guia;
    private String nombreRemitente;
    private String fechaDocumentacion;
    private String fechaEntrega;
    private double total;
    private double subtotal;
    private double iva;
    private double importeIva;
    private double porcentajeIva;
    private double impuestoRetenido;
    private double GuiasubTotal;
    private double GuiaporcentajeIva;
    private double GuiaimporteIva;
    private double Guiatotal;
    private String pGuiaId;
    private String empresaFactura;
    private int estatus;

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the iva
     */
    public double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * @return the guia
     */
    public String getGuia() {
        return guia;
    }

    /**
     * @param guia the guia to set
     */
    public void setGuia(String guia) {
        this.guia = guia;
    }

    /**
     * @return the remitente
     */
    public String getNombreRemitente() {
        return nombreRemitente;
    }

    /**
     * @param remitente the remitente to set
     */
    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    /**
     * @return the fechaDocumentacion
     */
    public String getFechaDocumentacion() {
        return fechaDocumentacion;
    }

    /**
     * @param fechaDocumentacion the fechaDocumentacion to set
     */
    public void setFechaDocumentacion(String fechaDocumentacion) {
        this.fechaDocumentacion = fechaDocumentacion;
    }

    /**
     * @return the fechaEntrega
     */
    public String getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the importeIva
     */
    public double getImporteIva() {
        return importeIva;
    }

    /**
     * @param importeIva the importeIva to set
     */
    public void setImporteIva(double importeIva) {
        this.importeIva = importeIva;
    }

    /**
     * @return the porcentajeIva
     */
    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    /**
     * @param porcentajeIva the porcentajeIva to set
     */
    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    /**
     * @return the GuiasubTotal
     */
    public double getGuiasubTotal() {
        return GuiasubTotal;
    }

    /**
     * @param GuiasubTotal the GuiasubTotal to set
     */
    public void setGuiasubTotal(double GuiasubTotal) {
        this.GuiasubTotal = GuiasubTotal;
    }

    /**
     * @return the GuiaporcentajeIva
     */
    public double getGuiaporcentajeIva() {
        return GuiaporcentajeIva;
    }

    /**
     * @param GuiaporcentajeIva the GuiaporcentajeIva to set
     */
    public void setGuiaporcentajeIva(double GuiaporcentajeIva) {
        this.GuiaporcentajeIva = GuiaporcentajeIva;
    }

    /**
     * @return the GuiaimporteIva
     */
    public double getGuiaimporteIva() {
        return GuiaimporteIva;
    }

    /**
     * @param GuiaimporteIva the GuiaimporteIva to set
     */
    public void setGuiaimporteIva(double GuiaimporteIva) {
        this.GuiaimporteIva = GuiaimporteIva;
    }

    /**
     * @return the Guiatotal
     */
    public double getGuiatotal() {
        return Guiatotal;
    }

    /**
     * @param Guiatotal the Guiatotal to set
     */
    public void setGuiatotal(double Guiatotal) {
        this.Guiatotal = Guiatotal;
    }

    /**
     * @return the pGuiaId
     */
    public String getpGuiaId() {
        return pGuiaId;
    }

    /**
     * @param pGuiaId the pGuiaId to set
     */
    public void setpGuiaId(String pGuiaId) {
        this.pGuiaId = pGuiaId;
    }

    /**
     * @return the empresaFactura
     */
    public String getEmpresaFactura() {
        return empresaFactura;
    }

    /**
     * @param empresaFactura the empresaFactura to set
     */
    public void setEmpresaFactura(String empresaFactura) {
        this.empresaFactura = empresaFactura;
    }

    /**
     * @return the impuestoRetenido
     */
    public double getImpuestoRetenido() {
        return impuestoRetenido;
    }

    /**
     * @param impuestoRetenido the impuestoRetenido to set
     */
    public void setImpuestoRetenido(double impuestoRetenido) {
        this.impuestoRetenido = impuestoRetenido;
    }

    /**
     * @return the estatus
     */
    public int getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

}
