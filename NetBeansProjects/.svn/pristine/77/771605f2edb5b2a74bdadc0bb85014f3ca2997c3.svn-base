
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransferenciaBoletoADO_ADO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransferenciaBoletoADO_ADO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Folios_Canjear" type="{http://interfazado.estrellaroja.com.mx/}folioCanjear" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Boleto_Canjeado" type="{http://interfazado.estrellaroja.com.mx/}boletoCanjeado" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Transacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HoraCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PromoRedondo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferenciaBoletoADO_ADO", propOrder = {
    "foliosCanjear",
    "boletoCanjeado",
    "transacion",
    "fechaCorrida",
    "horaCorrida",
    "promoRedondo"
})
public class TransferenciaBoletoADOADO {

    @XmlElement(name = "Folios_Canjear")
    protected List<FolioCanjear> foliosCanjear;
    @XmlElement(name = "Boleto_Canjeado")
    protected List<BoletoCanjeado> boletoCanjeado;
    @XmlElement(name = "Transacion")
    protected String transacion;
    @XmlElement(name = "FechaCorrida")
    protected String fechaCorrida;
    @XmlElement(name = "HoraCorrida")
    protected String horaCorrida;
    @XmlElement(name = "PromoRedondo")
    protected boolean promoRedondo;

    /**
     * Gets the value of the foliosCanjear property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the foliosCanjear property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFoliosCanjear().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FolioCanjear }
     * 
     * 
     */
    public List<FolioCanjear> getFoliosCanjear() {
        if (foliosCanjear == null) {
            foliosCanjear = new ArrayList<FolioCanjear>();
        }
        return this.foliosCanjear;
    }

    /**
     * Gets the value of the boletoCanjeado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boletoCanjeado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoletoCanjeado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BoletoCanjeado }
     * 
     * 
     */
    public List<BoletoCanjeado> getBoletoCanjeado() {
        if (boletoCanjeado == null) {
            boletoCanjeado = new ArrayList<BoletoCanjeado>();
        }
        return this.boletoCanjeado;
    }

    /**
     * Gets the value of the transacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransacion() {
        return transacion;
    }

    /**
     * Sets the value of the transacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransacion(String value) {
        this.transacion = value;
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

}
