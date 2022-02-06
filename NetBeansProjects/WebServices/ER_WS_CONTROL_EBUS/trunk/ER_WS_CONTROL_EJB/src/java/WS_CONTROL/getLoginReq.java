/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL;

import java.io.Serializable;

/**
 *
 * @author vgonzalez
 */
public class getLoginReq implements Serializable{
   private String usuarioNumero="";
   private String  usuarioContrasena="";
   private String  nombreEquipo="";
   private String  nombreCaja="";
   private String  direccionIP="";
   private String  direccionMAC="";
   private String  sucursalClave="";
   private String  canalVenta="";

    /**
     * @return the usuarioNumero
     */
    public String getUsuarioNumero() {
        return usuarioNumero;
    }

    /**
     * @param usuarioNumero the usuarioNumero to set
     */
    public void setUsuarioNumero(String usuarioNumero) {
        this.usuarioNumero = usuarioNumero;
    }

    /**
     * @return the usuarioContrasena
     */
    public String getUsuarioContrasena() {
        return usuarioContrasena;
    }

    /**
     * @param usuarioContrasena the usuarioContrasena to set
     */
    public void setUsuarioContrasena(String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }

    /**
     * @return the nombreEquipo
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * @param nombreEquipo the nombreEquipo to set
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * @return the nombreCaja
     */
    public String getNombreCaja() {
        return nombreCaja;
    }

    /**
     * @param nombreCaja the nombreCaja to set
     */
    public void setNombreCaja(String nombreCaja) {
        this.nombreCaja = nombreCaja;
    }

    /**
     * @return the direccionIP
     */
    public String getDireccionIP() {
        return direccionIP;
    }

    /**
     * @param direccionIP the direccionIP to set
     */
    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    /**
     * @return the direccionMAC
     */
    public String getDireccionMAC() {
        return direccionMAC;
    }

    /**
     * @param direccionMAC the direccionMAC to set
     */
    public void setDireccionMAC(String direccionMAC) {
        this.direccionMAC = direccionMAC;
    }

    /**
     * @return the sucursalClave
     */
    public String getSucursalClave() {
        return sucursalClave;
    }

    /**
     * @param sucursalClave the sucursalClave to set
     */
    public void setSucursalClave(String sucursalClave) {
        this.sucursalClave = sucursalClave;
    }

    /**
     * @return the canalVenta
     */
    public String getCanalVenta() {
        return canalVenta;
    }

    /**
     * @param canalVenta the canalVenta to set
     */
    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }


}
