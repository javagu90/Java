/*
 * TMSSocioIntimoWS.java
 *
 * Created on 20 de enero de 2011, 05:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsLealtad;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name="TMSSocioIntimoWS", targetNamespace="http://wsTMSSocioIntimo/") 
public abstract interface TMSSocioIntimoWS
{
  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="getInformacionPuntos", targetNamespace="http://wsTMSSocioIntimo/", className="wsLealtad.GetInformacionPuntos")
  @ResponseWrapper(localName="getInformacionPuntosResponse", targetNamespace="http://wsTMSSocioIntimo/", className="wsLealtad.GetInformacionPuntosResponse")
  public abstract InformacionconPuntosResponse getInformacionPuntos(@WebParam(name="noTarjeta", targetNamespace="") String paramString);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="getOperacion", targetNamespace="http://wsTMSSocioIntimo/", className="wsLealtad.GetOperacion")
  @ResponseWrapper(localName="getOperacionResponse", targetNamespace="http://wsTMSSocioIntimo/", className="wsLealtad.GetOperacionResponse")
  public abstract OperacionesResponse getOperacion(@WebParam(name="numeroOperacion", targetNamespace="") String paramString);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="importeSuficiente", targetNamespace="http://wsTMSSocioIntimo/", className="wsLealtad.ImporteSuficiente")
  @ResponseWrapper(localName="importeSuficienteResponse", targetNamespace="http://wsTMSSocioIntimo/", className="wsLealtad.ImporteSuficienteResponse")
  public abstract Boolean importeSuficiente(@WebParam(name="noTarjeta", targetNamespace="") String paramString1, @WebParam(name="usuario", targetNamespace="") long paramLong, @WebParam(name="usrkey", targetNamespace="") String paramString2, @WebParam(name="importeACubrir", targetNamespace="") double paramDouble);
}