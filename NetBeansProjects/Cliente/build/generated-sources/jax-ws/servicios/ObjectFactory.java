
package servicios;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicios package. 
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

    private final static QName _Convertir_QNAME = new QName("http://servicios/", "convertir");
    private final static QName _Hello_QNAME = new QName("http://servicios/", "hello");
    private final static QName _ConvertirResponse_QNAME = new QName("http://servicios/", "convertirResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://servicios/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConvertirResponse }
     * 
     */
    public ConvertirResponse createConvertirResponse() {
        return new ConvertirResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Convertir }
     * 
     */
    public Convertir createConvertir() {
        return new Convertir();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Convertir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios/", name = "convertir")
    public JAXBElement<Convertir> createConvertir(Convertir value) {
        return new JAXBElement<Convertir>(_Convertir_QNAME, Convertir.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertirResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios/", name = "convertirResponse")
    public JAXBElement<ConvertirResponse> createConvertirResponse(ConvertirResponse value) {
        return new JAXBElement<ConvertirResponse>(_ConvertirResponse_QNAME, ConvertirResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
