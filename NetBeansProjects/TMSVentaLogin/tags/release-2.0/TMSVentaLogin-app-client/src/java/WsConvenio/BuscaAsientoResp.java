
package WsConvenio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for buscaAsientoResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="buscaAsientoResp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="asientos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorMessage" type="{http://interfazado.estrellaroja.com.mx/}errorMessage" minOccurs="0"/>
 *         &lt;element name="promocion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipoPasajeroA" type="{http://interfazado.estrellaroja.com.mx/}tipoPasajero" minOccurs="0"/>
 *         &lt;element name="tipoPasajeroC" type="{http://interfazado.estrellaroja.com.mx/}tipoPasajero" minOccurs="0"/>
 *         &lt;element name="tipoPasajeroE" type="{http://interfazado.estrellaroja.com.mx/}tipoPasajero" minOccurs="0"/>
 *         &lt;element name="tipoPasajeroM" type="{http://interfazado.estrellaroja.com.mx/}tipoPasajero" minOccurs="0"/>
 *         &lt;element name="tipoPasajeroP" type="{http://interfazado.estrellaroja.com.mx/}tipoPasajero" minOccurs="0"/>
 *         &lt;element name="tipoPasajeroS" type="{http://interfazado.estrellaroja.com.mx/}tipoPasajero" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscaAsientoResp", propOrder = {
    "asientos",
    "errorMessage",
    "promocion",
    "success",
    "tipoPasajeroA",
    "tipoPasajeroC",
    "tipoPasajeroE",
    "tipoPasajeroM",
    "tipoPasajeroP",
    "tipoPasajeroS",
    "transactionId"
})
public class BuscaAsientoResp {

    protected String asientos;
    protected ErrorMessage errorMessage;
    protected boolean promocion;
    protected boolean success;
    protected TipoPasajero tipoPasajeroA;
    protected TipoPasajero tipoPasajeroC;
    protected TipoPasajero tipoPasajeroE;
    protected TipoPasajero tipoPasajeroM;
    protected TipoPasajero tipoPasajeroP;
    protected TipoPasajero tipoPasajeroS;
    protected String transactionId;

    /**
     * Gets the value of the asientos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsientos() {
        return asientos;
    }

    /**
     * Sets the value of the asientos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsientos(String value) {
        this.asientos = value;
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
     * Gets the value of the tipoPasajeroA property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPasajero }
     *     
     */
    public TipoPasajero getTipoPasajeroA() {
        return tipoPasajeroA;
    }

    /**
     * Sets the value of the tipoPasajeroA property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPasajero }
     *     
     */
    public void setTipoPasajeroA(TipoPasajero value) {
        this.tipoPasajeroA = value;
    }

    /**
     * Gets the value of the tipoPasajeroC property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPasajero }
     *     
     */
    public TipoPasajero getTipoPasajeroC() {
        return tipoPasajeroC;
    }

    /**
     * Sets the value of the tipoPasajeroC property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPasajero }
     *     
     */
    public void setTipoPasajeroC(TipoPasajero value) {
        this.tipoPasajeroC = value;
    }

    /**
     * Gets the value of the tipoPasajeroE property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPasajero }
     *     
     */
    public TipoPasajero getTipoPasajeroE() {
        return tipoPasajeroE;
    }

    /**
     * Sets the value of the tipoPasajeroE property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPasajero }
     *     
     */
    public void setTipoPasajeroE(TipoPasajero value) {
        this.tipoPasajeroE = value;
    }

    /**
     * Gets the value of the tipoPasajeroM property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPasajero }
     *     
     */
    public TipoPasajero getTipoPasajeroM() {
        return tipoPasajeroM;
    }

    /**
     * Sets the value of the tipoPasajeroM property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPasajero }
     *     
     */
    public void setTipoPasajeroM(TipoPasajero value) {
        this.tipoPasajeroM = value;
    }

    /**
     * Gets the value of the tipoPasajeroP property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPasajero }
     *     
     */
    public TipoPasajero getTipoPasajeroP() {
        return tipoPasajeroP;
    }

    /**
     * Sets the value of the tipoPasajeroP property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPasajero }
     *     
     */
    public void setTipoPasajeroP(TipoPasajero value) {
        this.tipoPasajeroP = value;
    }

    /**
     * Gets the value of the tipoPasajeroS property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPasajero }
     *     
     */
    public TipoPasajero getTipoPasajeroS() {
        return tipoPasajeroS;
    }

    /**
     * Sets the value of the tipoPasajeroS property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPasajero }
     *     
     */
    public void setTipoPasajeroS(TipoPasajero value) {
        this.tipoPasajeroS = value;
    }

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

}
