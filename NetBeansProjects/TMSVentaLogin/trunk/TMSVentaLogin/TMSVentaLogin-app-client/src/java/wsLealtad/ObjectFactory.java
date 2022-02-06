package wsLealtad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory
{
  private static final QName _ImporteSuficiente_QNAME = new QName("http://wsTMSSocioIntimo/", "importeSuficiente");
  private static final QName _GetOperacion_QNAME = new QName("http://wsTMSSocioIntimo/", "getOperacion");
  private static final QName _GetInformacionPuntosResponse_QNAME = new QName("http://wsTMSSocioIntimo/", "getInformacionPuntosResponse");
  private static final QName _GetInformacionPuntos_QNAME = new QName("http://wsTMSSocioIntimo/", "getInformacionPuntos");
  private static final QName _GetOperacionResponse_QNAME = new QName("http://wsTMSSocioIntimo/", "getOperacionResponse");
  private static final QName _ImporteSuficienteResponse_QNAME = new QName("http://wsTMSSocioIntimo/", "importeSuficienteResponse");

  public StatusType createStatusType()
  {
    return new StatusType();
  }

  public GetInformacionPuntos createGetInformacionPuntos()
  {
    return new GetInformacionPuntos();
  }

  public ImporteSuficienteResponse createImporteSuficienteResponse()
  {
    return new ImporteSuficienteResponse();
  }

  public GetInformacionPuntosResponse createGetInformacionPuntosResponse()
  {
    return new GetInformacionPuntosResponse();
  }

  public GetOperacionResponse createGetOperacionResponse()
  {
    return new GetOperacionResponse();
  }

  public ImporteSuficiente createImporteSuficiente()
  {
    return new ImporteSuficiente();
  }

  public OperacionesResponse createOperacionesResponse()
  {
    return new OperacionesResponse();
  }

  public InformacionconPuntosResponse createInformacionconPuntosResponse()
  {
    return new InformacionconPuntosResponse();
  }

  public Puntos createPuntos()
  {
    return new Puntos();
  }

  public GetOperacion createGetOperacion()
  {
    return new GetOperacion();
  }

  public InformacionCliente createInformacionCliente()
  {
    return new InformacionCliente();
  }

  @XmlElementDecl(namespace="http://wsTMSSocioIntimo/", name="importeSuficiente")
  public JAXBElement<ImporteSuficiente> createImporteSuficiente(ImporteSuficiente value)
  {
    return new JAXBElement(_ImporteSuficiente_QNAME, ImporteSuficiente.class, null, value);
  }

  @XmlElementDecl(namespace="http://wsTMSSocioIntimo/", name="getOperacion")
  public JAXBElement<GetOperacion> createGetOperacion(GetOperacion value)
  {
    return new JAXBElement(_GetOperacion_QNAME, GetOperacion.class, null, value);
  }

  @XmlElementDecl(namespace="http://wsTMSSocioIntimo/", name="getInformacionPuntosResponse")
  public JAXBElement<GetInformacionPuntosResponse> createGetInformacionPuntosResponse(GetInformacionPuntosResponse value)
  {
    return new JAXBElement(_GetInformacionPuntosResponse_QNAME, GetInformacionPuntosResponse.class, null, value);
  }

  @XmlElementDecl(namespace="http://wsTMSSocioIntimo/", name="getInformacionPuntos")
  public JAXBElement<GetInformacionPuntos> createGetInformacionPuntos(GetInformacionPuntos value)
  {
    return new JAXBElement(_GetInformacionPuntos_QNAME, GetInformacionPuntos.class, null, value);
  }

  @XmlElementDecl(namespace="http://wsTMSSocioIntimo/", name="getOperacionResponse")
  public JAXBElement<GetOperacionResponse> createGetOperacionResponse(GetOperacionResponse value)
  {
    return new JAXBElement(_GetOperacionResponse_QNAME, GetOperacionResponse.class, null, value);
  }

  @XmlElementDecl(namespace="http://wsTMSSocioIntimo/", name="importeSuficienteResponse")
  public JAXBElement<ImporteSuficienteResponse> createImporteSuficienteResponse(ImporteSuficienteResponse value)
  {
    return new JAXBElement(_ImporteSuficienteResponse_QNAME, ImporteSuficienteResponse.class, null, value);
  }
}