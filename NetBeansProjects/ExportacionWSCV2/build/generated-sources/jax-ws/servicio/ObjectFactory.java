
package servicio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicio package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WriteExcel_QNAME = new QName("http://servicio/", "writeExcel");
    private final static QName _EscribeSabana_QNAME = new QName("http://servicio/", "escribeSabana");
    private final static QName _EscribeSabanaResponse_QNAME = new QName("http://servicio/", "escribeSabanaResponse");
    private final static QName _WriteExcelResponse_QNAME = new QName("http://servicio/", "writeExcelResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WriteExcel }
     * 
     */
    public WriteExcel createWriteExcel() {
        return new WriteExcel();
    }

    /**
     * Create an instance of {@link EscribeSabana }
     * 
     */
    public EscribeSabana createEscribeSabana() {
        return new EscribeSabana();
    }

    /**
     * Create an instance of {@link EscribeSabanaResponse }
     * 
     */
    public EscribeSabanaResponse createEscribeSabanaResponse() {
        return new EscribeSabanaResponse();
    }

    /**
     * Create an instance of {@link WriteExcelResponse }
     * 
     */
    public WriteExcelResponse createWriteExcelResponse() {
        return new WriteExcelResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteExcel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "writeExcel")
    public JAXBElement<WriteExcel> createWriteExcel(WriteExcel value) {
        return new JAXBElement<WriteExcel>(_WriteExcel_QNAME, WriteExcel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EscribeSabana }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "escribeSabana")
    public JAXBElement<EscribeSabana> createEscribeSabana(EscribeSabana value) {
        return new JAXBElement<EscribeSabana>(_EscribeSabana_QNAME, EscribeSabana.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EscribeSabanaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "escribeSabanaResponse")
    public JAXBElement<EscribeSabanaResponse> createEscribeSabanaResponse(EscribeSabanaResponse value) {
        return new JAXBElement<EscribeSabanaResponse>(_EscribeSabanaResponse_QNAME, EscribeSabanaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteExcelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "writeExcelResponse")
    public JAXBElement<WriteExcelResponse> createWriteExcelResponse(WriteExcelResponse value) {
        return new JAXBElement<WriteExcelResponse>(_WriteExcelResponse_QNAME, WriteExcelResponse.class, null, value);
    }

}
