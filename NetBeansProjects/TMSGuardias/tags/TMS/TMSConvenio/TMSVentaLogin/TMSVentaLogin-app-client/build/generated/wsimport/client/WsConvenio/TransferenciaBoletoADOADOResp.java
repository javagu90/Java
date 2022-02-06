
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transferenciaBoletoADOADOResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transferenciaBoletoADOADOResp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorMessage" type="{http://interfazado.estrellaroja.com.mx/}errorMessage" minOccurs="0"/>
 *         &lt;element name="errormsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exito" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="folio" type="{http://interfazado.estrellaroja.com.mx/}folio" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "transferenciaBoletoADOADOResp", propOrder = {
    "errorMessage",
    "errormsg",
    "exito",
    "folio",
    "success"
})
public class TransferenciaBoletoADOADOResp {

    protected ErrorMessage errorMessage;
    protected String errormsg;
    protected boolean exito;
    @XmlElement(required = true, nillable = true)
    protected List<Folio> folio;
    protected boolean success;

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
     * Gets the value of the errormsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * Sets the value of the errormsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrormsg(String value) {
        this.errormsg = value;
    }

    /**
     * Gets the value of the exito property.
     * 
     */
    public boolean isExito() {
        return exito;
    }

    /**
     * Sets the value of the exito property.
     * 
     */
    public void setExito(boolean value) {
        this.exito = value;
    }

    /**
     * Gets the value of the folio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the folio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFolio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Folio }
     * 
     * 
     */
    public List<Folio> getFolio() {
        if (folio == null) {
            folio = new ArrayList<Folio>();
        }
        return this.folio;
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
