
package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for informacionCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="informacionCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaAfiliacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rfc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldoMoneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldoPuntos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informacionCliente", propOrder = {
    "fechaAfiliacion",
    "fechaNacimiento",
    "fechaVencimiento",
    "noTarjeta",
    "nombre",
    "rfc",
    "saldoMoneda",
    "saldoPuntos",
    "statusTarjeta",
    "tipoTarjeta"
})
public class InformacionCliente {

    protected String fechaAfiliacion;
    protected String fechaNacimiento;
    protected String fechaVencimiento;
    protected String noTarjeta;
    protected String nombre;
    protected String rfc;
    protected String saldoMoneda;
    protected String saldoPuntos;
    protected String statusTarjeta;
    protected String tipoTarjeta;

    /**
     * Gets the value of the fechaAfiliacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    /**
     * Sets the value of the fechaAfiliacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAfiliacion(String value) {
        this.fechaAfiliacion = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Gets the value of the noTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoTarjeta() {
        return noTarjeta;
    }

    /**
     * Sets the value of the noTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoTarjeta(String value) {
        this.noTarjeta = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the rfc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Sets the value of the rfc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfc(String value) {
        this.rfc = value;
    }

    /**
     * Gets the value of the saldoMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoMoneda() {
        return saldoMoneda;
    }

    /**
     * Sets the value of the saldoMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoMoneda(String value) {
        this.saldoMoneda = value;
    }

    /**
     * Gets the value of the saldoPuntos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoPuntos() {
        return saldoPuntos;
    }

    /**
     * Sets the value of the saldoPuntos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoPuntos(String value) {
        this.saldoPuntos = value;
    }

    /**
     * Gets the value of the statusTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusTarjeta() {
        return statusTarjeta;
    }

    /**
     * Sets the value of the statusTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusTarjeta(String value) {
        this.statusTarjeta = value;
    }

    /**
     * Gets the value of the tipoTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the value of the tipoTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTarjeta(String value) {
        this.tipoTarjeta = value;
    }

}
