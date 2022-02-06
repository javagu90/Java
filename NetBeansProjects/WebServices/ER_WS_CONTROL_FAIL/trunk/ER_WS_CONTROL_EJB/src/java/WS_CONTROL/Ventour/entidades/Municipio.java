/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author brojas
 */

public class Municipio implements Serializable {
    private Long Municipio_ID;
    private Long Estado_ID;
    private String Municipio_Codigo;
    private String Descripcion;

    public Municipio() {
    }


    public Municipio(String[] campos) {
        this.setMunicipio_ID(new Long(campos[0]));
        this.setEstado_ID(new Long(campos[1]));
        this.setMunicipio_Codigo(campos[2].toString());
        this.setDescripcion(campos[3].toString());
    }

    /**
     * @return the Municipio_ID
     */
    public Long getMunicipio_ID() {
        return Municipio_ID;
    }

    /**
     * @param Municipio_ID the Municipio_ID to set
     */
    public void setMunicipio_ID(Long Municipio_ID) {
        this.Municipio_ID = Municipio_ID;
    }

    /**
     * @return the Estado_ID
     */
    public Long getEstado_ID() {
        return Estado_ID;
    }

    /**
     * @param Estado_ID the Estado_ID to set
     */
    public void setEstado_ID(Long Estado_ID) {
        this.Estado_ID = Estado_ID;
    }

    /**
     * @return the Municipio_Codigo
     */
    public String getMunicipio_Codigo() {
        return Municipio_Codigo;
    }

    /**
     * @param Municipio_Codigo the Municipio_Codigo to set
     */
    public void setMunicipio_Codigo(String Municipio_Codigo) {
        this.Municipio_Codigo = Municipio_Codigo;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
   
}
