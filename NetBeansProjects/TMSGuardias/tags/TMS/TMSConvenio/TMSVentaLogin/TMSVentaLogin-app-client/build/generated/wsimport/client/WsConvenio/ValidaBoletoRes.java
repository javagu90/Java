
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for validaBoletoRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="validaBoletoRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boletos" type="{http://interfazado.estrellaroja.com.mx/}boletoVBRes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="errorMessage" type="{http://interfazado.estrellaroja.com.mx/}errorMessage" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validaBoletoRes", propOrder = {
    "boletos",
    "errorMessage",
    "success"
})
public class ValidaBoletoRes {

    @XmlElement(required = true, nillable = true)
    protected List<BoletoVBRes> boletos;
    protected ErrorMessage errorMessage;
    protected boolean success;

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
     * {@link BoletoVBRes }
     * 
     * 
     */
    public List<BoletoVBRes> getBoletos() {
        if (boletos == null) {
            boletos = new ArrayList<BoletoVBRes>();
        }
        return this.boletos;
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

}
