/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tms_incidencias.entidad;

import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 *Excepciones de incidencia
 * Se registran las excepciones en las que dos incidencias
 * crean una excepcion para que al momento de dar de alta una incidencia a un operador
 * se pueda validar que no se dupliquen
 * @author Osvaldo Sanchez
 * Fecha de creacion: 11/06/2014
 */
@Entity
@NamedQuery(name = "TmsExcepcionIncidenciaTbl.findAll",
    query = "select o from TmsExcepcionIncidenciaTbl o")
@Table(name = "TMS_EXCEPCIONES_INC_TBL")
public class TmsExcepcionIncidenciaTbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_EXCEPCION_INC_GENERADOR")
    @SequenceGenerator(name = "TMS_EXCEPCION_INC_GENERADOR",
    sequenceName = "TMS_EXCEPCION_INCIDENCIA_SEQ", initialValue = 1,
    allocationSize = 1)
    @Column(name = "EXCEPCION_INCIDENCIA_ID", nullable = false)
    private Short excepcionIncidenciaId;
    @JoinColumn(name = "TIPO_ACTIVIDAD_ADICIONAL1_ID", referencedColumnName = "TIPO_ACTIVIDAD_ADICIONAL_ID")
    @ManyToOne(optional = false)
    private TmsActAdicionalesTbl tipoActividadAdicional1;
    @JoinColumn(name = "TIPO_ACTIVIDAD_ADICIONAL2_ID", referencedColumnName = "TIPO_ACTIVIDAD_ADICIONAL_ID")
    @ManyToOne(optional = false)
    private TmsActAdicionalesTbl tipoActividadAdicional2;
    @Column(name = "CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name = "FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;

    /**
     * Constructor minimo
     */
    public TmsExcepcionIncidenciaTbl() {
    }

    /**
     * Constructor completo
     * @param tipoActividadAdicional1
     * @param tipoActividadAdicional2
     * @param creadoPor
     * @param fechaCreacion
     */
    public TmsExcepcionIncidenciaTbl(TmsActAdicionalesTbl tipoActividadAdicional1, TmsActAdicionalesTbl tipoActividadAdicional2, Long creadoPor, Timestamp fechaCreacion) {
        this.tipoActividadAdicional1 = tipoActividadAdicional1;
        this.tipoActividadAdicional2 = tipoActividadAdicional2;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    public Short getExcepcionIncidenciaId() {
        return excepcionIncidenciaId;
    }

    public void setExcepcionIncidenciaId(Short excepcionIncidenciaId) {
        this.excepcionIncidenciaId = excepcionIncidenciaId;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TmsActAdicionalesTbl getTipoActividadAdicional1() {
        return tipoActividadAdicional1;
    }

    public void setTipoActividadAdicional1(TmsActAdicionalesTbl tipoActividadAdicional1) {
        this.tipoActividadAdicional1 = tipoActividadAdicional1;
    }

    public TmsActAdicionalesTbl getTipoActividadAdicional2() {
        return tipoActividadAdicional2;
    }

    public void setTipoActividadAdicional2(TmsActAdicionalesTbl tipoActividadAdicional2) {
        this.tipoActividadAdicional2 = tipoActividadAdicional2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (excepcionIncidenciaId != null ? excepcionIncidenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsExcepcionIncidenciaTbl)) {
            return false;
        }
        TmsExcepcionIncidenciaTbl other = (TmsExcepcionIncidenciaTbl) object;
        if ((this.excepcionIncidenciaId == null && other.excepcionIncidenciaId != null) || (this.excepcionIncidenciaId != null && !this.excepcionIncidenciaId.equals(other.excepcionIncidenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tms_incidencias.entidad.TmsExcepcionIncidenciaTbl[id=" + excepcionIncidenciaId + "]";
    }
}
