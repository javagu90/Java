
package WsConvenio;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for validaBoletoAbierto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="validaBoletoAbierto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boletos" type="{http://interfazado.estrellaroja.com.mx/}boletoVB" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validaBoletoAbierto", propOrder = {
    "boletos"
})
public class ValidaBoletoAbierto {

    protected List<BoletoVB> boletos;

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
     * {@link BoletoVB }
     * 
     * 
     */
    public List<BoletoVB> getBoletos() {
        if (boletos == null) {
            boletos = new ArrayList<BoletoVB>();
        }
        return this.boletos;
    }

}
