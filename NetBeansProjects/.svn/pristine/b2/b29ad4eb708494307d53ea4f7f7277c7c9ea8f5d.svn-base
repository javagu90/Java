/*
 * Asientos.java
 *
 * Created on 18 de junio de 2010, 04:16 PM
 *
 * Clase para representar a los Asientos dentro de las operaciones de EJB
 */

package ERTMSWS.clases;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
/**
 *
 * @author opalafox
 */
public class Asientos implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private String Empresa;
    private String Servicio;
    private String Caja;
    private String TipoBoleto;
    private String TipoVenta;
    private String ClaveCorrida;
    private int ClienteId;
    private int NoAsiento;
    private String NombrePasajero;
    private String TipoPasajero;
    private String TipoPago;
    private String ReferenciaPago;
    private float ImporteBoleto;
    private String ClaveReservacion;
    private String FolioPreimpreso;
    private String SerieBoleto;
    private String SucursalClave;
    private String Origen;
    private String OrigenCorrida;
    private String Destino;
    private String UsuarioNumero;
    private int AutorizadoPor;
    private Date FechaHoraVenta;
    private String ReferenciaAdicional1;
    private String ReferenciaAdicional2;
    private String ReferenciaAdicional3;
    private String ReferenciaAdicional4;
    private String ReferenciaAdicional5;
    private String ReferenciaAdicional6;
    private String ReferenciaAdicional7;
    private String ReferenciaAdicional8;
    private String ReferenciaAdicional9;
    private String ReferenciaAdicional10;
    private String ReferenciaAdicional11;
    private String ReferenciaAdicional12;
    private String GeneroPasajero;
    private float ImporteCargo;
    private SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat sdfFechaHora2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
    


    /** Creates a new instance of Asientos */
    public Asientos() {
        /*Se hace el desfasamiento de la hora America/Mexico_City a UTC para cumplir con los estondares iso de los WS
         */
        sdfFechaHora.setTimeZone(tz);
        OrigenCorrida = "";
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

    public String getCaja() {
        return Caja;
    }

    public void setCaja(String Caja) {
        this.Caja = Caja;
    }

    public String  getTipoBoleto() {
        return TipoBoleto;
    }

    public void setTipoBoleto(String  TipoBoleto) {
        this.TipoBoleto = TipoBoleto;
    }

    public String  getTipoVenta() {
        return TipoVenta;
    }

    public void setTipoVenta(String  TipoVenta) {
        this.TipoVenta = TipoVenta;
    }

    public String getClaveCorrida() {
        return ClaveCorrida;
    }

    public void setClaveCorrida(String ClaveCorrida) {
        this.ClaveCorrida = ClaveCorrida;
    }

    public int getClienteId() {
        return ClienteId;
    }

    public void setClienteId(int ClienteId) {
        this.ClienteId = ClienteId;
    }

    public int getNoAsiento() {
        return NoAsiento;
    }

    public void setNoAsiento(int NoAsiento) {
        this.NoAsiento = NoAsiento;
    }

    public String getNombrePasajero() {
        return NombrePasajero;
    }

    public void setNombrePasajero(String NombrePasajero) {
        this.NombrePasajero = NombrePasajero;
    }

    public String getTipoPasajero() {
        return TipoPasajero;
    }

    public void setTipoPasajero(String TipoPasajero) {
        this.TipoPasajero = TipoPasajero;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }

    public String getReferenciaPago() {
        return ReferenciaPago;
    }

    public void setReferenciaPago(String ReferenciaPago) {
        this.ReferenciaPago = ReferenciaPago;
    }

    public float getImporteBoleto() {
        return ImporteBoleto;
    }

    public void setImporteBoleto(float ImporteBoleto) {
        this.ImporteBoleto = ImporteBoleto;
    }

    public String getClaveReservacion() {
        return ClaveReservacion;
    }

    public void setClaveReservacion(String ClaveReservacion) {
        this.ClaveReservacion = ClaveReservacion;
    }

    public String getFolioPreimpreso() {
        return FolioPreimpreso;
    }

    public void setFolioPreimpreso(String FolioPreimpreso) {
        this.FolioPreimpreso = FolioPreimpreso;
    }

    public String getSerieBoleto() {
        return SerieBoleto;
    }

    public void setSerieBoleto(String SerieBoleto) {
        this.SerieBoleto = SerieBoleto;
    }

    public String getSucursalClave() {
        return SucursalClave;
    }

    public void setSucursalClave(String SucursalClave) {
        this.SucursalClave = SucursalClave;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getUsuarioNumero() {
        return UsuarioNumero;
    }

    public void setUsuarioNumero(String UsuarioNumero) {
        this.UsuarioNumero = UsuarioNumero;
    }

    public int getAutorizadoPor() {
        return AutorizadoPor;
    }

    public void setAutorizadoPor(int AutorizadoPor) {
        this.AutorizadoPor = AutorizadoPor;
    }

    public Date getFechaHoraVenta() {
        return FechaHoraVenta;
    }

    public void setFechaHoraVenta(Date FechaHoraVenta) throws Exception {
        this.FechaHoraVenta = sdfFechaHora2.parse(sdfFechaHora.format(FechaHoraVenta.getTime()));
    }

    public String getReferenciaAdicional1() {
        return ReferenciaAdicional1;
    }

    public void setReferenciaAdicional1(String ReferenciaAdicional1) {
        this.ReferenciaAdicional1 = ReferenciaAdicional1;
    }

    public String getReferenciaAdicional2() {
        return ReferenciaAdicional2;
    }

    public void setReferenciaAdicional2(String ReferenciaAdicional2) {
        this.ReferenciaAdicional2 = ReferenciaAdicional2;
    }

    public String getReferenciaAdicional3() {
        return ReferenciaAdicional3;
    }

    public void setReferenciaAdicional3(String ReferenciaAdicional3) {
        this.ReferenciaAdicional3 = ReferenciaAdicional3;
    }

    public String getReferenciaAdicional4() {
        return ReferenciaAdicional4;
    }

    public void setReferenciaAdicional4(String ReferenciaAdicional4) {
        this.ReferenciaAdicional4 = ReferenciaAdicional4;
    }

    public String getReferenciaAdicional5() {
        return ReferenciaAdicional5;
    }

    public void setReferenciaAdicional5(String ReferenciaAdicional5) {
        this.ReferenciaAdicional5 = ReferenciaAdicional5;
    }

    public String getReferenciaAdicional6() {
        return ReferenciaAdicional6;
    }

    public void setReferenciaAdicional6(String ReferenciaAdicional6) {
        this.ReferenciaAdicional6 = ReferenciaAdicional6;
    }

    public String getReferenciaAdicional7() {
        return ReferenciaAdicional7;
    }

    public void setReferenciaAdicional7(String ReferenciaAdicional7) {
        this.ReferenciaAdicional7 = ReferenciaAdicional7;
    }

    public String getReferenciaAdicional8() {
        return ReferenciaAdicional8;
    }

    public void setReferenciaAdicional8(String ReferenciaAdicional8) {
        this.ReferenciaAdicional8 = ReferenciaAdicional8;
    }

    public String getReferenciaAdicional9() {
        return ReferenciaAdicional9;
    }

    public void setReferenciaAdicional9(String ReferenciaAdicional9) {
        this.ReferenciaAdicional9 = ReferenciaAdicional9;
    }

    public String getReferenciaAdicional10() {
        return ReferenciaAdicional10;
    }

    public void setReferenciaAdicional10(String ReferenciaAdicional10) {
        this.ReferenciaAdicional10 = ReferenciaAdicional10;
    }

    public String getOrigenCorrida() {
        return OrigenCorrida;
    }

    public void setOrigenCorrida(String OrigenCorrida) {
        this.OrigenCorrida = OrigenCorrida;
    }

    /**
     * @return the ReferenciaAdicional11
     */
    public String getReferenciaAdicional11() {
        return ReferenciaAdicional11;
    }

    /**
     * @param ReferenciaAdicional11 the ReferenciaAdicional11 to set
     */
    public void setReferenciaAdicional11(String ReferenciaAdicional11) {
        this.ReferenciaAdicional11 = ReferenciaAdicional11;
    }

    /**
     * @return the ReferenciaAdicional12
     */
    public String getReferenciaAdicional12() {
        return ReferenciaAdicional12;
    }

    /**
     * @param ReferenciaAdicional12 the ReferenciaAdicional12 to set
     */
    public void setReferenciaAdicional12(String ReferenciaAdicional12) {
        this.ReferenciaAdicional12 = ReferenciaAdicional12;
    }

    /**
     * @return the GeneroPasajero
     */
    public String getGeneroPasajero() {
        return GeneroPasajero;
    }

    /**
     * @param GeneroPasajero the GeneroPasajero to set
     */
    public void setGeneroPasajero(String GeneroPasajero) {
        this.GeneroPasajero = GeneroPasajero;
    }

    /**
     * @return the ImporteCargo
     */
    public float getImporteCargo() {
        return ImporteCargo;
    }

    /**
     * @param ImporteCargo the ImporteCargo to set
     */
    public void setImporteCargo(float ImporteCargo) {
        this.ImporteCargo = ImporteCargo;
    }

 
    
}
