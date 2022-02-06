
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetPuntosXTarjeta element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="GetPuntosXTarjeta">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="NoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "noTarjeta",
    "usuario",
    "usrkey"
})
@XmlRootElement(name = "GetPuntosXTarjeta")
public class GetPuntosXTarjeta {

    @XmlElement(name = "NoTarjeta", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected String noTarjeta;
    @XmlElement(namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected long usuario;
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
