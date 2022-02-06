
package servicios;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Convertidor", targetNamespace = "http://servicios/", wsdlLocation = "http://localhost:8080/Convertidor/Convertidor?wsdl")
public class Convertidor_Service
    extends Service
{

    private final static URL CONVERTIDOR_WSDL_LOCATION;
    private final static WebServiceException CONVERTIDOR_EXCEPTION;
    private final static QName CONVERTIDOR_QNAME = new QName("http://servicios/", "Convertidor");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Convertidor/Convertidor?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CONVERTIDOR_WSDL_LOCATION = url;
        CONVERTIDOR_EXCEPTION = e;
    }

    public Convertidor_Service() {
        super(__getWsdlLocation(), CONVERTIDOR_QNAME);
    }

    public Convertidor_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CONVERTIDOR_QNAME, features);
    }

    public Convertidor_Service(URL wsdlLocation) {
        super(wsdlLocation, CONVERTIDOR_QNAME);
    }

    public Convertidor_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CONVERTIDOR_QNAME, features);
    }

    public Convertidor_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Convertidor_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Convertidor
     */
    @WebEndpoint(name = "ConvertidorPort")
    public Convertidor getConvertidorPort() {
        return super.getPort(new QName("http://servicios/", "ConvertidorPort"), Convertidor.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Convertidor
     */
    @WebEndpoint(name = "ConvertidorPort")
    public Convertidor getConvertidorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servicios/", "ConvertidorPort"), Convertidor.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CONVERTIDOR_EXCEPTION!= null) {
            throw CONVERTIDOR_EXCEPTION;
        }
        return CONVERTIDOR_WSDL_LOCATION;
    }

}