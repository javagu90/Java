
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RegistraTransaccion element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="RegistraTransaccion">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="NoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="Ticket" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *           &lt;element name="tipotransaccion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="taquilla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="usrkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "noTarjeta",
    "ticket",
    "sucursal",
    "usuario",
    "fecha",
    "tipotransaccion",
    "total",
    "descuento",
    "taquilla",
    "usrkey"
})
@XmlRootElement(name = "RegistraTransaccion")
public class RegistraTransaccion {

    @XmlElement(name = "NoTarjeta", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected String noTarjeta;
    @XmlElement(name = "Ticket", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected String ticket;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected long sucursal;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected long usuario;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/", required = true)
    protected XMLGregorianCalendar fecha;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected long tipotransaccion;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected double total;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected double descuento;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected String taquilla;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected String usrkey;

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
     * Gets the value of the ticket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * Sets the value of the ticket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicket(String value) {
        this.ticket = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     */
    public long getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     */
    public void setSucursal(long value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     */
    public long getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     */
    public void setUsuario(long value) {
        this.usuario = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the tipotransaccion property.
     * 
     */
    public long getTipotransaccion() {
        return tipotransaccion;
    }

    /**
     * Sets the value of the tipotransaccion property.
     * 
     */
    public void setTipotransaccion(long value) {
        this.tipotransaccion = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public double getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(double value) {
        this.total = value;
    }

    /**
     * Gets the value of the descuento property.
     * 
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Sets the value of the descuento property.
     * 
     */
    public void setDescuento(double value) {
        this.descuento = value;
    }

    /**
     * Gets the value of the taquilla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaquilla() {
        return taquilla;
    }

    /**
     * Sets the value of the taquilla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaquilla(String value) {
        this.taquilla = value;
    }

    /**
     * Gets the value of the usrkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsrkey() {
        return usrkey;
    }

    /**
     * Sets the value of the usrkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsrkey(String value) {
        this.usrkey = value;
    }

}
