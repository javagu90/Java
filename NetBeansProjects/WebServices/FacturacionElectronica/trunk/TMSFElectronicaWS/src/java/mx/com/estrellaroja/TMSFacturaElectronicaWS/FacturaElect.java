/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.estrellaroja.TMSFacturaElectronicaWS;

/**
 *
 * @author brojas
 */
public class FacturaElect {

    //<editor-fold defaultstate="collapsed" desc=" Atributos ">
    private String DatosReceptor  ="";
    private String DatosEmisor   = "";
    private String DatosFactura  = "";
    private String Lineas    = "";
    private String Totales   = "";
    private String Impuestos = "";
    private String Retencion = "";
    private String Traslado  = "";
    private String anexo20 = "";
    private String status    = "";
    private String mensaje     = "";
    private String LlaveFact = "";
    private String rutapdf   = "";
    private String rutaxml   = "";
    private byte[] Anexofact = null;
    private String tipoProducto   = "";
    private String rfc   = "";
    private String folio   = "";


    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc=" Propiedades ">
    /**
     * @return the DatosReceptor
     */
    public String getDatosReceptor() {
        return DatosReceptor;
    }

    /**
     * @param DatosReceptor the DatosReceptor to set
     */
    public void setDatosReceptor(String DatosReceptor) {
        this.DatosReceptor = DatosReceptor;
    }

    /**
     * @return the DatosEmisor
     */
    public String getDatosEmisor() {
        return DatosEmisor;
    }

    /**
     * @param DatosEmisor the DatosEmisor to set
     */
    public void setDatosEmisor(String DatosEmisor) {
        this.DatosEmisor = DatosEmisor;
    }

    /**
     * @return the DatosFactura
     */
    public String getDatosFactura() {
        return DatosFactura;
    }

    /**
     * @param DatosFactura the DatosFactura to set
     */
    public void setDatosFactura(String DatosFactura) {
        this.DatosFactura = DatosFactura;
    }

    /**
     * @return the Lineas
     */
    public String getLineas() {
        return Lineas;
    }

    /**
     * @param Lineas the Lineas to set
     */
    public void setLineas(String Lineas) {
        this.Lineas = Lineas;
    }

    /**
     * @return the Totales
     */
    public String getTotales() {
        return Totales;
    }

    /**
     * @param Totales the Totales to set
     */
    public void setTotales(String Totales) {
        this.Totales = Totales;
    }

    /**
     * @return the Impuestos
     */
    public String getImpuestos() {
        return Impuestos;
    }

    /**
     * @param Impuestos the Impuestos to set
     */
    public void setImpuestos(String Impuestos) {
        this.Impuestos = Impuestos;
    }

    /**
     * @return the Retencion
     */
    public String getRetencion() {
        return Retencion;
    }

    /**
     * @param Retencion the Retencion to set
     */
    public void setRetencion(String Retencion) {
        this.Retencion = Retencion;
    }

    /**
     * @return the Traslado
     */
    public String getTraslado() {
        return Traslado;
    }

    /**
     * @param Traslado the Traslado to set
     */
    public void setTraslado(String Traslado) {
        this.Traslado = Traslado;
    }

    /**
     * @return the anexo20
     */
    public String getAnexo20() {
        return anexo20;
    }

    /**
     * @param anexo20 the anexo20 to set
     */
    public void setAnexo20(String anexo20) {
        this.anexo20 = anexo20;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the error
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param error the error to set
     */
    public void setMensaje(String error) {
        this.mensaje= error;
    }

    /**
     * @return the LlaveFact
     */
    public String getLlaveFact() {
        return LlaveFact;
    }

    /**
     * @param LlaveFact the LlaveFact to set
     */
    public void setLlaveFact(String LlaveFact) {
        this.LlaveFact = LlaveFact;
    }

    /**
     * @return the rutapdf
     */
    public String getRutapdf() {
        return rutapdf;
    }

    /**
     * @param rutapdf the rutapdf to set
     */
    public void setRutapdf(String rutapdf) {
        this.rutapdf = rutapdf;
    }

    /**
     * @return the rutaxml
     */
    public String getRutaxml() {
        return rutaxml;
    }

    /**
     * @param rutaxml the rutaxml to set
     */
    public void setRutaxml(String rutaxml) {
        this.rutaxml = rutaxml;
    }

    /**
     * @return the Anexofact
     */
    public byte[] getAnexofact() {
        return Anexofact;
    }

    /**
     * @param Anexofact the Anexofact to set
     */
    public void setAnexofact(byte[] Anexofact) {
        this.Anexofact = Anexofact;
    }

    /**
     * @return the tipoProducto
     */
    public String getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
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


}
