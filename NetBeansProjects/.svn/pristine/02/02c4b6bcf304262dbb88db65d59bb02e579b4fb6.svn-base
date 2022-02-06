
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bloquearAsiento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bloquearAsiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transaccionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corridaId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promoRedondo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="asientos" type="{http://interfazado.estrellaroja.com.mx/}asiento" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bloquearAsiento", propOrder = {
    "transaccionId",
    "corridaId",
    "fechaCorrida",
    "horaCorrida",
    "promoRedondo",
    "asientos",
    "servicio"
})
public class BloquearAsiento {

    protected String transaccionId;
    protected String corridaId;
    protected String fechaCorrida;
    protected String horaCorrida;
    protected boolean promoRedondo;
    protected List<Asiento> asientos;
    protected String servicio;

    /**
     * Gets the value of the transaccionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransaccionId() {
        return transaccionId;
    }

    /**
     * Sets the value of the transaccionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransaccionId(String value) {
        this.transaccionId = value;
    }

    /**
     * Gets the value of the corridaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorridaId() {
        return corridaId;
    }

    /**
     * Sets the value of the corridaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorridaId(String value) {
        this.corridaId = value;
    }

    /**
     * Gets the value of the fechaCorrida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCorrida() {
        return fechaCorrida;
    }

    /**
     * Sets the value of the fechaCorrida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCorrida(String value) {
        this.fechaCorrida = value;
    }

    /**
     * Gets the value of the horaCorrida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraCorrida() {
        return horaCorrida;
    }

    /**
     * Sets the value of the horaCorrida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraCorrida(String value) {
        this.horaCorrida = value;
    }

    /**
     * Gets the value of the promoRedondo property.
     * 
     */
    public boolean isPromoRedondo() {
        return promoRedondo;
    }

    /**
     * Sets the value of the promoRedondo property.
     * 
     */
    public void setPromoRedondo(boolean value) {
        this.promoRedondo = value;
    }

    /**
     * Gets the value of the asientos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asientos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAsientos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Asiento }
     * 
     * 
     */
    public List<Asiento> getAsientos() {
        if (asientos == null) {
            asientos = new ArrayList<Asiento>();
        }
        return this.asientos;
    }

    /**
     * Gets the value of the servicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Sets the value of the servicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicio(String value) {
        this.servicio = value;
    }

}
