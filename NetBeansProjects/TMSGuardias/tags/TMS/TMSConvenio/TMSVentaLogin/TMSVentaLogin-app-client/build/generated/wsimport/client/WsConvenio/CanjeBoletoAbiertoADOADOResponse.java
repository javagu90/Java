
package WsConvenio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CanjeBoletoAbiertoADO_ADOResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CanjeBoletoAbiertoADO_ADOResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://interfazado.estrellaroja.com.mx/}canjeBoletoAbiertoADOADOResp" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CanjeBoletoAbiertoADO_ADOResponse", propOrder = {
    "_return"
})
public class CanjeBoletoAbiertoADOADOResponse {

    @XmlElement(name = "return")
    protected CanjeBoletoAbiertoADOADOResp _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link CanjeBoletoAbiertoADOADOResp }
     *     
     */
    public CanjeBoletoAbiertoADOADOResp getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link CanjeBoletoAbiertoADOADOResp }
     *     
     */
    public void setReturn(CanjeBoletoAbiertoADOADOResp value) {
        this._return = value;
    }

}
