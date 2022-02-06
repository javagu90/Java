package tmsacumularestrellas;

import java.util.Date;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTxtTipoPasajeAcum extends JTextField
{
  private boolean beep_on_error = false;
  private Vector TiposPasajeResp = new Vector();
  private Vector TiposPasaje = new Vector();
  private Vector ExcluirTPas = new Vector();
  private Vector<ClaseTiposPasaje> MiLista;

  public void setTipos(Vector aMiLista)
  {
    this.MiLista = new Vector();

    for (int i = 0; i < aMiLista.size(); i++) {
      Vector z = new Vector();
      z = (Vector)aMiLista.get(i);

      ClaseTiposPasaje x = new ClaseTiposPasaje();
      x.setTipoPasajeroId(Long.valueOf(z.get(0).toString()));
      x.setNombreTipo(z.get(1).toString());
      x.setLetraTipo(z.get(2).toString());
      x.setPctDescuento(Double.valueOf("0"));
      x.setRuta(Long.valueOf("0"));
      x.setRedondeo("S");
      this.MiLista.addElement(x);
    }
    obtenertipos();
    setRespaldo();
    initializeForTiposPasajeros();
  }
  private void setRespaldo() {
    for (int i = 0; i < this.TiposPasaje.size(); i++)
      this.TiposPasajeResp.addElement(this.TiposPasaje.get(i));
  }

  private void initializeForTiposPasajeros() {
    setDocument(new PlainDocument()
    {
      public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
          return;
        }
        StringBuffer tipopas = new StringBuffer(str);

        for (int i = 0; i < str.length(); i++)
        {
          if (!Character.isDigit(tipopas.charAt(i)))
            tipopas.setCharAt(i, Character.toUpperCase(tipopas.charAt(i)));
          else
            tipopas.setCharAt(i, tipopas.charAt(i));
          char c = tipopas.charAt(i);
          if (Character.isDigit(c)) {
            if (offs <= 0)
              continue;
            char cb = getText(0, offs).charAt(offs - 1);
            if ((!Character.isDigit(cb)) && (cb != ' ')) {
              tipopas.deleteCharAt(i); } else {
              if ((!Character.isDigit(cb)) || (offs < 2))
                continue;
              char cb2 = getText(0, offs).charAt(offs - 2);
              if (!Character.isDigit(cb2))
                continue;
              tipopas.deleteCharAt(i);
            }

          }
          else if (offs == 0) {
            if (JTxtTipoPasajeAcum.this.TiposPasaje.contains(Character.valueOf(c))) continue; tipopas.deleteCharAt(i);
          }
          else if ((JTxtTipoPasajeAcum.this.TiposPasaje.contains(Character.valueOf(c))) && (Character.isLetter(c)) && (offs >= 1)) {
            char cb = getText(0, offs).charAt(offs - 1);

            if ((!Character.isDigit(cb)) && (cb != ' '))
              tipopas.deleteCharAt(i);
          }
          else if ((c == ' ') && (offs > 0)) {
            char cb = getText(0, offs).charAt(offs - 1);

            if (!Character.isLetter(cb))
              tipopas.deleteCharAt(i);
          }
          else {
            tipopas.deleteCharAt(i);
          }
        }
        super.insertString(offs, new String(tipopas.toString()), a);
      }
    });
  }

  public void setExclusiones(Vector pExcluirTPas) {
    this.ExcluirTPas = pExcluirTPas;
    Excluir();
  }

  private void Excluir() {
    this.TiposPasaje.clear();

    if (this.ExcluirTPas.size() == 0) {
      for (int i = 0; i < this.TiposPasajeResp.size(); i++) {
        char chartipopasaje = this.TiposPasajeResp.get(i).toString().charAt(0);
        this.TiposPasaje.addElement(Character.valueOf(chartipopasaje));
      }
    }
    else
      for (int i = 0; i < this.TiposPasajeResp.size(); i++) {
        char chartipopasaje = this.TiposPasajeResp.get(i).toString().charAt(0);
        if (!this.ExcluirTPas.contains(Character.valueOf(chartipopasaje)))
          this.TiposPasaje.addElement(Character.valueOf(chartipopasaje));
      }
  }

  private void obtenertipos()
  {
    this.TiposPasaje.clear();

    for (int i = 0; i < this.MiLista.size(); i++) {
      ClaseTiposPasaje tbltp = (ClaseTiposPasaje)this.MiLista.get(i);
      char chartipopasaje = tbltp.getLetraTipo().charAt(0);

      if (this.TiposPasaje.indexOf(Character.valueOf(chartipopasaje)) == -1)
        this.TiposPasaje.addElement(Character.valueOf(chartipopasaje));
    }
  }

  public boolean isok()
  {
    return true;
  }
  public long getTipopasajeid(char c, long ruta) {
    int i = 0;
    long gettipopasaje = -1L;
    boolean ban = true;
    if (!this.MiLista.isEmpty())
    {
      while ((i < this.MiLista.size()) && (ban)) {
        ClaseTiposPasaje tbltp = (ClaseTiposPasaje)this.MiLista.get(i);
        if ((c == tbltp.getLetraTipo().toString().charAt(0)) && (tbltp.getRuta().longValue() == ruta)) {
          gettipopasaje = tbltp.getTipoPasajeroId().longValue();
          ban = false;
        }
        i++;
      }
    }
    return gettipopasaje;
  }

  public double getDescuento(long idtipo) {
    int i = 0;
    double getdescuento = 0.0D;
    boolean ban = true;
    if (!this.MiLista.isEmpty())
    {
      while ((i < this.MiLista.size()) && (ban)) {
        ClaseTiposPasaje tbltp = (ClaseTiposPasaje)this.MiLista.get(i);
        if (idtipo == tbltp.getTipoPasajeroId().longValue()) {
          getdescuento = tbltp.getPctDescuento().doubleValue();
          ban = false;
        }i++;
      }
    }

    return getdescuento / 100.0D;
  }

  public String getRedondeo(long idtipo) {
    int i = 0;
    String redondeo = "S";
    boolean ban = true;
    if (!this.MiLista.isEmpty())
    {
      while ((i < this.MiLista.size()) && (ban)) {
        ClaseTiposPasaje tbltp = (ClaseTiposPasaje)this.MiLista.get(i);
        if (idtipo == tbltp.getTipoPasajeroId().longValue()) {
          if ((tbltp.getRedondeo() == null) || (tbltp.getRedondeo().equals("")))
            redondeo = "S";
          else
            redondeo = tbltp.getRedondeo();
          ban = false;
        }i++;
      }
    }

    return redondeo;
  }

  public char getDefault() {
    char salida = 'D';
    int i = 0;
    if (!this.MiLista.isEmpty())
    {
      while (i < this.MiLista.size()) {
        this.MiLista.get(i);
        if (((ClaseTiposPasaje)this.MiLista.get(i)).getPctDescuento().doubleValue() == 0.0D) {
          salida = ((ClaseTiposPasaje)this.MiLista.get(i)).getLetraTipo().toString().charAt(0);
          break;
        }
        i++;
      }
    }
    return salida;
  }

  public void setBeepOnError(boolean beep)
  {
    this.beep_on_error = beep;
  }

  public int getTam()
  {
    return this.MiLista.size(); } 
  class ClaseTiposPasaje { private Long tipoPasajeroId;
    private String nombreTipo;
    private String letraTipo;
    private String redondeo;
    private Double pctDescuento;
    private Date fechaDesde;
    private Date fechaHasta;
    private Integer creadoPor;
    private Date fechaCreacion;
    private Integer ultimaActualizacionPor;
    private Date ultimaFechaActualizacion;
    private Long ruta;

    public ClaseTiposPasaje() {  } 
    public Long getTipoPasajeroId() { return this.tipoPasajeroId;
    }

    public void setTipoPasajeroId(Long tipoPasajeroId)
    {
      this.tipoPasajeroId = tipoPasajeroId;
    }

    public Long getRuta()
    {
      return this.ruta;
    }

    public void setRuta(Long ruta)
    {
      this.ruta = ruta;
    }

    public String getNombreTipo()
    {
      return this.nombreTipo;
    }

    public void setNombreTipo(String nombreTipo)
    {
      this.nombreTipo = nombreTipo;
    }

    public String getLetraTipo()
    {
      return this.letraTipo;
    }

    public void setLetraTipo(String letraTipo)
    {
      this.letraTipo = letraTipo;
    }

    public String getRedondeo()
    {
      return this.redondeo;
    }

    public void setRedondeo(String redon)
    {
      this.redondeo = redon;
    }

    public Double getPctDescuento()
    {
      return this.pctDescuento;
    }

    public void setPctDescuento(Double pctDescuento)
    {
      this.pctDescuento = pctDescuento;
    }

    public Date getFechaDesde()
    {
      return this.fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde)
    {
      this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta()
    {
      return this.fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta)
    {
      this.fechaHasta = fechaHasta;
    }

    public Integer getCreadoPor()
    {
      return this.creadoPor;
    }

    public void setCreadoPor(Integer creadoPor)
    {
      this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion()
    {
      return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion)
    {
      this.fechaCreacion = fechaCreacion;
    }

    public Integer getUltimaActualizacionPor()
    {
      return this.ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor)
    {
      this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Date getUltimaFechaActualizacion()
    {
      return this.ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion)
    {
      this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
  }
}