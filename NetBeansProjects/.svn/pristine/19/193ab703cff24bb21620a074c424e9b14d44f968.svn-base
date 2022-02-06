package tms_venta.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
    @NamedQuery(name = "TmsTipopagosV.findAll", query = "select o from TmsTipopagosV o"),
    @NamedQuery(name = "TmsTipopagosV.findAllById", query = "select o.identificador from TmsTipopagosV o where o.tipopagoId=:TipoPagoId")
})
@Table(name = "TMS_TIPOPAGOS_V")
public class TmsTipopagosV implements Serializable {
    private String identificador;
    private String nombre;
    private String socio_intimo;
    private String contado;
    @Column(name = "TIPOPAGO_ID", nullable = false)
    @Id
    private Long tipopagoId;

    public TmsTipopagosV() {
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTipopagoId() {
        return tipopagoId;
    }

    public void setTipopagoId(Long tipopagoId) {
        this.tipopagoId = tipopagoId;
    }

    public String getSocio_intimo() {
        return socio_intimo;
    }

    public void setSocio_intimo(String socio_intimo) {
        this.socio_intimo = socio_intimo;
    }

    /**
     * @return the contado
     */
    public String getContado() {
        return contado;
    }

    /**
     * @param contado the contado to set
     */
    public void setContado(String contado) {
        this.contado = contado;
    }
}
