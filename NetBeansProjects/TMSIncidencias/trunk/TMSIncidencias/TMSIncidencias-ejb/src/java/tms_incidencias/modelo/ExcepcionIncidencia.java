/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tms_incidencias.modelo;

import java.io.Serializable;

/**
 *Excepcion de incidencia, para un mejor manejo de datos
 * al momento de consultar en la base d edatos
 * @author Osvaldo Sanchez
 */
public class ExcepcionIncidencia implements Serializable {

    private Short id;
    private String incidencia1;
    private String incidencia2;
    private String usuario;
    private String fechaCreacion;

    public ExcepcionIncidencia() {
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getIncidencia1() {
        return incidencia1;
    }

    public void setIncidencia1(String incidencia1) {
        this.incidencia1 = incidencia1;
    }

    public String getIncidencia2() {
        return incidencia2;
    }

    public void setIncidencia2(String incidencia2) {
        this.incidencia2 = incidencia2;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
