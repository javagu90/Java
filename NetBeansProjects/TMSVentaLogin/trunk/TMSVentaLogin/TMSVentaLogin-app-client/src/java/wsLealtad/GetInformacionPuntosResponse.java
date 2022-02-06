package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="getInformacionPuntosResponse", propOrder={"_return"})
public class GetInformacionPuntosResponse
{

  @XmlElement(name="return")
  protected InformacionconPuntosResponse _return;

  public InformacionconPuntosResponse getReturn()
  {
    return this._return;
  }

  public void setReturn(InformacionconPuntosResponse value)
  {
    this._return = value;
  }
}