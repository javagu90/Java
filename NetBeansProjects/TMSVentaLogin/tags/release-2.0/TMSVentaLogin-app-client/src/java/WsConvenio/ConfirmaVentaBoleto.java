
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for confirmaVentaBoleto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="confirmaVentaBoleto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boletos" type="{http://interfazado.estrellaroja.com.mx/}boletoCVB" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="promocion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fechaCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmaVentaBoleto", propOrder = {
    "transactionId",
    "boletos",
    "promocion",
    "fechaCorrida",
    "horaCorrida"
})
public class ConfirmaVentaBoleto {

    protected String transactionId;
    protected List<BoletoCVB> boletos;
    protected boolean promocion;
    protected String fechaCorrida;
    protected String horaCorrida;

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the boletos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boletos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoletos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BoletoCVB }
     * 
     * 
     */
    public List<BoletoCVB> getBoletos() {
        if (boletos == null) {
            boletos = new ArrayList<BoletoCVB>();
        }
        return this.boletos;
    }

    /**
     * Gets the value of the promocion property.
     * 
     */
    public boolean isPromocion() {
        return promocion;
    }

    /**
     * Sets the value of the promocion property.
     * 
     */
    public void setPromocion(boolean value) {
        this.promocion = value;
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

}
