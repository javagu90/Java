/*
 * LogoutReq.java
 *
 * Created on 18 de junio de 2010, 06:10 PM
 *
 * Clase para conjuntar los argumentos dados a getLogOut del WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.Logout;

import java.io.Serializable;

/**
 *
 * @author opalafox
 */
public class LogoutReq  implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private int SesionId;
    private String UsuarioNumero;
    private String UsuarioContrasena;

    
    /** Creates a new instance of LogoutReq */
    public LogoutReq() {
    }

    public int getSesionId() {
        return SesionId;
    }

    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
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
    
}
