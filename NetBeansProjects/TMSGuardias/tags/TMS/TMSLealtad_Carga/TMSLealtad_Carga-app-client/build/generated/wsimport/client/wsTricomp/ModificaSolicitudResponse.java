
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModificaSolicitudResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ModificaSolicitudResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="ModificaSolicitudResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "modificaSolicitudResult"
})
@XmlRootElement(name = "ModificaSolicitudResponse")
public class ModificaSolicitudResponse {

    @XmlElement(name = "ModificaSolicitudResult", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected boolean modificaSolicitudResult;

    /**
     * Gets the value of the modificaSolicitudResult property.
     * 
     */
    public boolean isModificaSolicitudResult() {
        return modificaSolicitudResult;
    }

    /**
     * Sets the value of the modificaSolicitudResult property.
     * 
     */
    public void setModificaSolicitudResult(boolean value) {
        this.modificaSolicitudResult = value;
    }

}
