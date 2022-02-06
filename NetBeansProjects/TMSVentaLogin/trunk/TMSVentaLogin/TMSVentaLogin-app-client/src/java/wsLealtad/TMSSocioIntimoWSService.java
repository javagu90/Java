/*
 * TMSSocioIntimoWSService.java
 *
 * Created on 20 de enero de 2011, 05:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsLealtad;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

@WebServiceClient(name="TMSSocioIntimoWSService", targetNamespace="http://wsTMSSocioIntimo/", wsdlLocation="http://wsas.estrellaroja.com.mx:8090/TMSSocioIntimoWS/TMSSocioIntimoWSService?WSDL")
public class TMSSocioIntimoWSService extends Service
{
  private static final URL TMSSOCIOINTIMOWSSERVICE_WSDL_LOCATION;

  public TMSSocioIntimoWSService(URL wsdlLocation, QName serviceName)
  {
    super(wsdlLocation, serviceName);
  }

  public TMSSocioIntimoWSService() {
    super(TMSSOCIOINTIMOWSSERVICE_WSDL_LOCATION, new QName("http://wsTMSSocioIntimo/", "TMSSocioIntimoWSService"));
  }

  @WebEndpoint(name="TMSSocioIntimoWSPort")
  public TMSSocioIntimoWS getTMSSocioIntimoWSPort() 
  {
    return (TMSSocioIntimoWS)super.getPort(new QName("http://wsTMSSocioIntimo/", "TMSSocioIntimoWSPort"), TMSSocioIntimoWS.class);
  }

  static
  {
    URL url = null;
    try {
      url = new URL("http://wsas.estrellaroja.com.mx:8090/TMSSocioIntimoWS/TMSSocioIntimoWSService?WSDL");
                     //http://192.168.16.114:8090          /TMSLealtadWS/TMSLealtadWSService?WSDL 
              
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    TMSSOCIOINTIMOWSSERVICE_WSDL_LOCATION = url;
  }
}