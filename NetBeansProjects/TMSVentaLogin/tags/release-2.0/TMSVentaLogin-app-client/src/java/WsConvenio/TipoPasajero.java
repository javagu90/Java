
package WsConvenio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoPasajero complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tipoPasajero">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadDisponible" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tarifaRedondo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tarifaSencilla" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tipoPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoPasajero", propOrder = {
    "cantidadDisponible",
    "tarifaRedondo",
    "tarifaSencilla",
    "tipoPasajero"
})
public class TipoPasajero {

    protected int cantidadDisponible;
    protected float tarifaRedondo;
    protected float tarifaSencilla;
    protected String tipoPasajero;

    /**
     * Gets the value of the cantidadDisponible property.
     * 
     */
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Sets the value of the cantidadDisponible property.
     * 
     */
    public void setCantidadDisponible(int value) {
        this.cantidadDisponible = value;
    }

    /**
     * Gets the value of the tarifaRedondo property.
     * 
     */
    public float getTarifaRedondo() {
        return tarifaRedondo;
    }

    /**
     * Sets the value of the tarifaRedondo property.
     * 
     */
    public void setTarifaRedondo(float value) {
        this.tarifaRedondo = value;
    }

    /**
     * Gets the value of the tarifaSencilla property.
     * 
     */
    public float getTarifaSencilla() {
        return tarifaSencilla;
    }

    /**
     * Sets the value of the tarifaSencilla property.
     * 
     */
    public void setTarifaSencilla(float value) {
        this.tarifaSencilla = value;
    }

    /**
     * Gets the value of the tipoPasajero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPasajero() {
        return tipoPasajero;
    }

    /**
     * Sets the value of the tipoPasajero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPasajero(String value) {
        this.tipoPasajero = value;
    }

}
