/*
 * LoginReq.java
 *
 * Created on 18 de junio de 2010, 04:48 PM
 *
 *  Clase para conjuntar los argumentos dado getLoginReq al WebService y que seron procesadas por EJB
 *
 */

package ERTMSWS.clasesx.Login;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author opalafox
 */
public class LoginReq  implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private int UsuarioId;
    private String UsuarioNumero;
    private String UsuarioContrasena;
    private String NombreEquipo;
    private String NombreCaja;
    private String DireccionIP;
    private String DireccionMAC;
    private int AutorizadoPor;
    private String SucursalClave;
    private String CanalVenta;
   
    private Date FechaCreacion;
    
    /** Creates a new instance of LoginReq */
    public LoginReq() {
        
        
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    public String getUsuarioNumero() {
        return UsuarioNumero;
    }

    public void setUsuarioNumero(String UsuarioNumero) {
        this.UsuarioNumero = UsuarioNumero;
    }

    public String getUsuarioContrasena() {
        return UsuarioContrasena;
    }

    public void setUsuarioContrasena(String UsuarioContrasena) {
        this.UsuarioContrasena = UsuarioContrasena;
    }

    public String getNombreEquipo() {
        return NombreEquipo;
    }

    public void setNombreEquipo(String NombreEquipo) {
        this.NombreEquipo = NombreEquipo;
    }

    public String getNombreCaja() {
        return NombreCaja;
    }

    public void setNombreCaja(String NombreCaja) {
        this.NombreCaja = NombreCaja;
    }

    public String getDireccionIP() {
        return DireccionIP;
    }

    public void setDireccionIP(String DireccionIP) {
        this.DireccionIP = DireccionIP;
    }

    public String getDireccionMAC() {
        return DireccionMAC;
    }

    public void setDireccionMAC(String DireccionMAC) {
        this.DireccionMAC = DireccionMAC;
    }

    public int getAutorizadoPor() {
        return AutorizadoPor;
    }

    public void setAutorizadoPor(int AutorizadoPor) {
        this.AutorizadoPor = AutorizadoPor;
    }

    public String getSucursalClave() {
        return SucursalClave;
    }

    public void setSucursalClave(String SucursalClave) {
        this.SucursalClave = SucursalClave;
    }

    public String getCanalVenta() {
        return CanalVenta;
    }

    public void setCanalVenta(String CanalVenta) {
        this.CanalVenta = CanalVenta;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }
    
}
