/*
 * Corrida.java
 *
 * Created on 18 de junio de 2010, 04:05 PM
 *
 *  Clase para representar a las Corridas dentro de las operaciones de EJB
 */

package ERTMSWS.clases;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

/**
 *
 * @author opalafox
 */
import java.util.Date;
public class Corrida{
    
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validación de datos
     *se verifica la longitud de la cadena
     */
    private String ClaveCorrida;
    private String Empresa;
    private String Servicio;
    private Date FechaCorrida;
    private Date HoraCorrida;
    private String OrigenCorrida;
    private String DestinoCorrida;
    private Date FechaHoraSalida;
    private String OrigenSolicitado;
    private String DestinoSolicitado;
    private String  TarifaSencillo;
    private String  TarifaRedondo;
    private String AplicaDescuentoRedondo;
    private String TipoPaxPermitido;
    private String Autobus;
    private String Operador;
    private String  TipoCorrida;
    private String  EstadoCorrida;
    private int PlantillaId;
    private int Capacidad;
    private Date FechaLlegada;
    private Date HoraLLegada;
    private String  TipoVenta;
    private SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat sdfFechaHora2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private TimeZone tz = TimeZone.getTimeZone("America/Mexico_City"); // UTC
    /** Creates a new instance of Corrida */ 
    public Corrida() {
        /*Se hace el desfasamiento de la hora America/Mexico_City a UTC para cumplir con los estándares iso de los WS
         */
        sdfFechaHora.setTimeZone(tz); 
    }

    public String getClaveCorrida() {
        return ClaveCorrida;
    }

    public void setClaveCorrida(String ClaveCorrida) {
        this.ClaveCorrida = ClaveCorrida;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    public Date getFechaCorrida() {
        return FechaCorrida;
    }

    public void setFechaCorrida(Date FechaCorrida)throws Exception {
        
        //this.FechaCorrida = FechaCorrida;
        this.FechaCorrida = sdfFechaHora2.parse(sdfFechaHora.format(FechaCorrida.getTime()));
    }

    public Date getHoraCorrida() {
        return HoraCorrida;
    }

    public void setHoraCorrida(Date HoraCorrida) throws Exception{
      //  sdfFechaHora.setTimeZone(TimeZone.getTimeZone("UTC")); 
       // sdfFechaHora.parse(sdfFechaHora.format(HoraCorrida.getTime()));
        
        this.HoraCorrida = sdfFechaHora2.parse(sdfFechaHora.format(HoraCorrida.getTime()));
    }

    public String getOrigenCorrida() {
        return OrigenCorrida;
    }

    public void setOrigenCorrida(String OrigenCorrida) {
        this.OrigenCorrida = OrigenCorrida;
    }

    public String getDestinoCorrida() {
        return DestinoCorrida;
    }

    public void setDestinoCorrida(String DestinoCorrida) {
        this.DestinoCorrida = DestinoCorrida;
    }

    public Date getFechaHoraSalida() {
        return FechaHoraSalida;
    }

    public void setFechaHoraSalida(Date FechaHoraSalida) throws Exception{
        //sdfFechaHora.setTimeZone(TimeZone.getTimeZone("UTC")); 
        //TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
        
        
      //  String tempdata = sdfFechaHora.format(FechaHoraSalida.getTime());
      //  System.out.println("Tz "+tempdata);
        //sdfFechaHora.parse(sdfFechaHora.format(FechaHoraSalida.getTime()));
        this.FechaHoraSalida = sdfFechaHora2.parse(sdfFechaHora.format(FechaHoraSalida.getTime()));
       
          
    }

    public String getOrigenSolicitado() {
        return OrigenSolicitado;
    }

    public void setOrigenSolicitado(String OrigenSolicitado) {
        this.OrigenSolicitado = OrigenSolicitado;
    }

    public String getDestinoSolicitado() {
        return DestinoSolicitado;
    }

    public void setDestinoSolicitado(String DestinoSolicitado) {
        this.DestinoSolicitado = DestinoSolicitado;
    }

    public String  getTarifaSencillo() {
        return TarifaSencillo;
    }

    public void setTarifaSencillo(String  TarifaSencillo) {
        this.TarifaSencillo = TarifaSencillo;
    }

    public String  getTarifaRedondo() {
        return TarifaRedondo;
    }

    public void setTarifaRedondo(String  TarifaRedondo) {
        this.TarifaRedondo = TarifaRedondo;
    }

    public String getAplicaDescuentoRedondo() {
        return AplicaDescuentoRedondo;
    }

    public void setAplicaDescuentoRedondo(String AplicaDescuentoRedondo) {
        this.AplicaDescuentoRedondo = AplicaDescuentoRedondo;
    }

    public String getTipoPaxPermitido() {
        return TipoPaxPermitido;
    }

    public void setTipoPaxPermitido(String TipoPaxPermitido) {
        this.TipoPaxPermitido = TipoPaxPermitido;
    }

    public String getAutobus() {
        return Autobus;
    }

    public void setAutobus(String Autobus) {
        this.Autobus = Autobus;
    }

    public String getOperador() {
        return Operador;
    }

    public void setOperador(String Operador) {
        this.Operador = Operador;
    }

    public String  getTipoCorrida() {
        return TipoCorrida;
    }

    public void setTipoCorrida(String  TipoCorrida) {
        this.TipoCorrida = TipoCorrida;
    }

    public String  getEstadoCorrida() {
        return EstadoCorrida;
    }

    public void setEstadoCorrida(String  EstadoCorrida) {
        this.EstadoCorrida = EstadoCorrida;
    }

    public int getPlantillaId() {
        return PlantillaId;
    }

    public void setPlantillaId(int PlantillaId) {
        this.PlantillaId = PlantillaId;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public Date getFechaLlegada() {
        return FechaLlegada;
    }

    public void setFechaLlegada(Date FechaLlegada) throws Exception {
        this.FechaLlegada = sdfFechaHora2.parse(sdfFechaHora.format(FechaLlegada.getTime()));
    }

    public Date getHoraLLegada() {
        return HoraLLegada;
    }

    public void setHoraLLegada(Date HoraLLegada)  throws Exception{
        this.HoraLLegada = sdfFechaHora2.parse(sdfFechaHora.format(HoraLLegada.getTime()));
    }

    public String  getTipoVenta() {
        return TipoVenta;
    }

    public void setTipoVenta(String  TipoVenta) {
        this.TipoVenta = TipoVenta;
    }
    
}
