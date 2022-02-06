
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for buscaCorridaResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="buscaCorridaResp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="corridas" type="{http://interfazado.estrellaroja.com.mx/}corrida" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="errorMessage" type="{http://interfazado.estrellaroja.com.mx/}errorMessage" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="transaccionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscaCorridaResp", propOrder = {
    "corridas",
    "errorMessage",
    "success",
    "transaccionId"
})
public class BuscaCorridaResp {

    @XmlElement(nillable = true)
    protected List<Corrida> corridas;
    protected ErrorMessage errorMessage;
    protected boolean success;
    protected String transaccionId;

    /**
     * Gets the value of the corridas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the corridas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCorridas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Corrida }
     * 
     * 
     */
    public List<Corrida> getCorridas() {
        if (corridas == null) {
            corridas = new ArrayList<Corrida>();
        }
        return this.corridas;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorMessage }
     *     
     */
    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorMessage }
     *     
     */
    public void setErrorMessage(ErrorMessage value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

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

}
