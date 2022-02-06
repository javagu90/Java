
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cancelaBoleto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancelaBoleto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boletos" type="{http://interfazado.estrellaroja.com.mx/}boletoCB" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelaBoleto", propOrder = {
    "boletos"
})
public class CancelaBoleto {

    protected List<BoletoCB> boletos;

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
     * {@link BoletoCB }
     * 
     * 
     */
    public List<BoletoCB> getBoletos() {
        if (boletos == null) {
            boletos = new ArrayList<BoletoCB>();
        }
        return this.boletos;
    }

}
