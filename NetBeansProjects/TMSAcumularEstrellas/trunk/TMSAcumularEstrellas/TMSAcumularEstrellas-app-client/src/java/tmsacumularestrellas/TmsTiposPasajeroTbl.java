package tmsacumularestrellas;

import java.util.Vector;

public class TmsTiposPasajeroTbl
{
  private Long tipoPasajeroId;
  private String aplicaTipoLealtad;
  private String aplicaLealtad;
  private String L_LUNES="N";
  private String L_MARTES="N";
  private String L_MIERCOLES="N";
  private String L_JUEVES="N";
  private String L_VIERNES="N";
  private String L_SABADO="N";
  private String L_DOMINGO="N";
  private String lealtad;

  public TmsTiposPasajeroTbl()
  {
  }

  public TmsTiposPasajeroTbl(Vector v)
  {
    setTipoPasajeroId(Long.valueOf(v.get(0) == null ? -1L : Long.valueOf(v.get(0).toString()).longValue()));
    setLealtad(v.get(1) == null ? "" : v.get(1).toString());
    setAplicaTipoLealtad(v.get(2) == null ? "" : v.get(2).toString());
    setAplicaLealtad(v.get(3) == null ? "" : v.get(3).toString());
    setLealtades();
  }

  public Long getTipoPasajeroId()
  {
    return this.tipoPasajeroId;
  }

  public void setTipoPasajeroId(Long tipoPasajeroId)
  {
    this.tipoPasajeroId = tipoPasajeroId;
  }

  public int hashCode()
  {
    int hash = 0;
    hash += (this.tipoPasajeroId != null ? this.tipoPasajeroId.hashCode() : 0);
    return hash;
  }

  public boolean equals(Object object)
  {
    if (!(object instanceof TmsTiposPasajeroTbl)) {
      return false;
    }
    TmsTiposPasajeroTbl other = (TmsTiposPasajeroTbl)object;
    return (this.tipoPasajeroId == other.tipoPasajeroId) || ((this.tipoPasajeroId != null) && (this.tipoPasajeroId.equals(other.tipoPasajeroId)));
  }

  public String toString()
  {
    return "tmsventalogin.entidad.TmsTiposPasajeroTbl[tipoPasajeroId=" + this.tipoPasajeroId + "]";
  }

  public String getLealtad()
  {
    return this.lealtad;
  }

  public void setLealtad(String lealtad) {
    this.lealtad = lealtad;

    if ((lealtad != null) && 
      (!lealtad.equals("")))
    {
      String[] arreglo = lealtad.split(",");
      setL_LUNES(arreglo[0]);
      setL_MARTES(arreglo[1]);
      setL_MIERCOLES(arreglo[2]);
      setL_JUEVES(arreglo[3]);
      setL_VIERNES(arreglo[4]);
      setL_SABADO(arreglo[5]);
      setL_DOMINGO(arreglo[6]);
    }
  }

  public void setLealtades()
  {
    if ((this.lealtad != null) && 
      (!this.lealtad.equals("")))
    {
      String[] arreglo = this.lealtad.split(",");
      setL_LUNES(arreglo[0] == null ? "N" : arreglo[0]);
      setL_MARTES(arreglo[1] == null ? "N" : arreglo[1]);
      setL_MIERCOLES(arreglo[2] == null ? "N" : arreglo[2]);
      setL_JUEVES(arreglo[3] == null ? "N" : arreglo[3]);
      setL_VIERNES(arreglo[4] == null ? "N" : arreglo[4]);
      setL_SABADO(arreglo[5] == null ? "N" : arreglo[5]);
      setL_DOMINGO(arreglo[6] == null ? "N" : arreglo[6]);
    }
  }

  public String getL_LUNES()
  {
    return this.L_LUNES;
  }

  public void setL_LUNES(String L_LUNES) {
    this.L_LUNES = L_LUNES;
  }

  public String getL_MARTES() {
    return this.L_MARTES;
  }

  public void setL_MARTES(String L_MARTES) {
    this.L_MARTES = L_MARTES;
  }

  public String getL_MIERCOLES() {
    return this.L_MIERCOLES;
  }

  public void setL_MIERCOLES(String L_MIERCOLES) {
    this.L_MIERCOLES = L_MIERCOLES;
  }

  public String getL_JUEVES() {
    return this.L_JUEVES;
  }

  public void setL_JUEVES(String L_JUEVES) {
    this.L_JUEVES = L_JUEVES;
  }

  public String getL_VIERNES() {
    return this.L_VIERNES;
  }

  public void setL_VIERNES(String L_VIERNES) {
    this.L_VIERNES = L_VIERNES;
  }

  public String getL_SABADO() {
    return this.L_SABADO;
  }

  public void setL_SABADO(String L_SABADO) {
    this.L_SABADO = L_SABADO;
  }

  public String getL_DOMINGO() {
    return this.L_DOMINGO;
  }

  public void setL_DOMINGO(String L_DOMINGO) {
    this.L_DOMINGO = L_DOMINGO;
  }

  public String getAplicaTipoLealtad() {
    return this.aplicaTipoLealtad;
  }

  public void setAplicaTipoLealtad(String aplicaTipoLealtad) {
    this.aplicaTipoLealtad = aplicaTipoLealtad;
  }

  public String getAplicaLealtad() {
    return this.aplicaLealtad;
  }

  public void setAplicaLealtad(String aplicaLealtad) {
    this.aplicaLealtad = aplicaLealtad;
  }
}