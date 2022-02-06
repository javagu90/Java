
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CierraTransaccionStr element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="CierraTransaccionStr">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="transaccion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="totalredimido" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *           &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "transaccion",
    "totalredimido",
    "descuento",
    "usuario",
    "usrkey"
})
@XmlRootElement(name = "CierraTransaccionStr")
public class CierraTransaccionStr {

    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected long transaccion;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected double totalredimido;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected double descuento;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected long usuario;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected String usrkey;

    /**
     * Gets the value of the transaccion property.
     * 
     */
    public long getTransaccion() {
        return transaccion;
    }

    /**
     * Sets the value of the transaccion property.
     * 
     */
    public void setTransaccion(long value) {
        this.transaccion = value;
    }

    /**
     * Gets the value of the totalredimido property.
     * 
     */
    public double getTotalredimido() {
        return totalredimido;
    }

    /**
     * Sets the value of the totalredimido property.
     * 
     */
    public void setTotalredimido(double value) {
        this.totalredimido = value;
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
