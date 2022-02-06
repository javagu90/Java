
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InsertaSolicitudResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="InsertaSolicitudResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="InsertaSolicitudResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "insertaSolicitudResult"
})
@XmlRootElement(name = "InsertaSolicitudResponse")
public class InsertaSolicitudResponse {

    @XmlElement(name = "InsertaSolicitudResult", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected boolean insertaSolicitudResult;

    /**
     * Gets the value of the insertaSolicitudResult property.
     * 
     */
    public boolean isInsertaSolicitudResult() {
        return insertaSolicitudResult;
    }

    /**
     * Sets the value of the insertaSolicitudResult property.
     * 
     */
    public void setInsertaSolicitudResult(boolean value) {
        this.insertaSolicitudResult = value;
    }

}
