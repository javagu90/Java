
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnularFolioResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="AnularFolioResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="AnularFolioResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "anularFolioResult"
})
@XmlRootElement(name = "AnularFolioResponse")
public class AnularFolioResponse {

    @XmlElement(name = "AnularFolioResult", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected boolean anularFolioResult;

    /**
     * Gets the value of the anularFolioResult property.
     * 
     */
    public boolean isAnularFolioResult() {
        return anularFolioResult;
    }

    /**
     * Sets the value of the anularFolioResult property.
     * 
     */
    public void setAnularFolioResult(boolean value) {
        this.anularFolioResult = value;
    }

}
