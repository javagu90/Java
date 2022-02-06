/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMSS;
import java.io.Serializable;

/**
 *
 * @author sis.admin
 */
public class getOrigenesDestinosReqEr implements Serializable{
    private long SesionId;
    private String Origen;
    private String Empresa;
    private String UnidadNegocio;
    private String Servicio;
    private String Canal;


    /**
     * @return the SesionId
     */
    public long getSesionId() {
        return SesionId;
    }

    /**
     * @param SesionId the SesionId to set
     */
    public void setSesionId(long SesionId) {
        this.SesionId = SesionId;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return Origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.Origen = origen;
    }

    /**
     * @return the Empresa
     */
    public String getEmpresa() {
        return Empresa;
    }

    /**
     * @param Empresa the Empresa to set
     */
    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    /**
     * @return the UnidadNegocio
     */
    public String getUnidadNegocio() {
        return UnidadNegocio;
    }

    /**
     * @param UnidadNegocio the UnidadNegocio to set
     */
    public void setUnidadNegocio(String UnidadNegocio) {
        this.UnidadNegocio = UnidadNegocio;
    }

    /**
     * @return the Servicio
     */
    public String getServicio() {
        return Servicio;
    }

    /**
     * @param Servicio the Servicio to set
     */
    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    /**
     * @return the Canal
     */
    public String getCanal() {
        return Canal;
    }

    /**
     * @param Canal the Canal to set
     */
    public void setCanal(String Canal) {
        this.Canal = Canal;
    }



}
