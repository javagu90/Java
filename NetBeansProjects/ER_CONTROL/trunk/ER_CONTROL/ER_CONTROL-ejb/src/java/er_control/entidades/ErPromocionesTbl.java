/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package er_control.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vgonzalez
 */
@Entity
@Table(name = "ER_PROMOCIONES_TBL")
@NamedQueries({
    @NamedQuery(name = "ErPromocionesTbl.findAll", query = "SELECT e FROM ErPromocionesTbl e"),
    @NamedQuery(name = "ErPromocionesTbl.findByPromocionId", query = "SELECT e FROM ErPromocionesTbl e WHERE e.promocionId = :promocionId"),
    @NamedQuery(name = "ErPromocionesTbl.findByNombrePromocion", query = "SELECT e FROM ErPromocionesTbl e WHERE e.nombrePromocion = :nombrePromocion"),
    @NamedQuery(name = "ErPromocionesTbl.findByCodigoPromocion", query = "SELECT e FROM ErPromocionesTbl e WHERE e.codigoPromocion = :codigoPromocion"),
    @NamedQuery(name = "ErPromocionesTbl.findByDescripcionPromocion", query = "SELECT e FROM ErPromocionesTbl e WHERE e.descripcionPromocion = :descripcionPromocion"),
    @NamedQuery(name = "ErPromocionesTbl.findByNombreProducto", query = "SELECT e FROM ErPromocionesTbl e WHERE e.nombreProducto = :nombreProducto"),
    @NamedQuery(name = "ErPromocionesTbl.findByServicioId", query = "SELECT e FROM ErPromocionesTbl e WHERE e.servicioId = :servicioId"),
    @NamedQuery(name = "ErPromocionesTbl.findByRutaId", query = "SELECT e FROM ErPromocionesTbl e WHERE e.rutaId = :rutaId"),
    @NamedQuery(name = "ErPromocionesTbl.findByTramoId", query = "SELECT e FROM ErPromocionesTbl e WHERE e.tramoId = :tramoId"),
    @NamedQuery(name = "ErPromocionesTbl.findByEmpresaId", query = "SELECT e FROM ErPromocionesTbl e WHERE e.empresaId = :empresaId"),
    @NamedQuery(name = "ErPromocionesTbl.findByAplicaSencillo", query = "SELECT e FROM ErPromocionesTbl e WHERE e.aplicaSencillo = :aplicaSencillo"),
    @NamedQuery(name = "ErPromocionesTbl.findByAplicaRedondo", query = "SELECT e FROM ErPromocionesTbl e WHERE e.aplicaRedondo = :aplicaRedondo"),
    @NamedQuery(name = "ErPromocionesTbl.findByVigenciaFechaInicial", query = "SELECT e FROM ErPromocionesTbl e WHERE e.vigenciaFechaInicial = :vigenciaFechaInicial"),
    @NamedQuery(name = "ErPromocionesTbl.findByVigenciaFechaFinal", query = "SELECT e FROM ErPromocionesTbl e WHERE e.vigenciaFechaFinal = :vigenciaFechaFinal"),
    @NamedQuery(name = "ErPromocionesTbl.findByCanalVenta", query = "SELECT e FROM ErPromocionesTbl e WHERE e.canalVenta = :canalVenta"),
    @NamedQuery(name = "ErPromocionesTbl.findByCompraMinima", query = "SELECT e FROM ErPromocionesTbl e WHERE e.compraMinima = :compraMinima"),
    @NamedQuery(name = "ErPromocionesTbl.findByAdicional1", query = "SELECT e FROM ErPromocionesTbl e WHERE e.adicional1 = :adicional1"),
    @NamedQuery(name = "ErPromocionesTbl.findByAdicional2", query = "SELECT e FROM ErPromocionesTbl e WHERE e.adicional2 = :adicional2"),
    @NamedQuery(name = "ErPromocionesTbl.findByAdicional3", query = "SELECT e FROM ErPromocionesTbl e WHERE e.adicional3 = :adicional3"),
    @NamedQuery(name = "ErPromocionesTbl.findByAdicional4", query = "SELECT e FROM ErPromocionesTbl e WHERE e.adicional4 = :adicional4"),
    @NamedQuery(name = "ErPromocionesTbl.findByAdicional5", query = "SELECT e FROM ErPromocionesTbl e WHERE e.adicional5 = :adicional5"),
    @NamedQuery(name = "ErPromocionesTbl.findByCreadoPor", query = "SELECT e FROM ErPromocionesTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErPromocionesTbl.findByFechaCreacion", query = "SELECT e FROM ErPromocionesTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErPromocionesTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErPromocionesTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErPromocionesTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErPromocionesTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion")})
public class ErPromocionesTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PROMOCION_ID")
    private BigDecimal promocionId;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PROMOCION")
    private String nombrePromocion;
    @Basic(optional = false)
    @Column(name = "CODIGO_PROMOCION")
    private String codigoPromocion;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_PROMOCION")
    private String descripcionPromocion;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "SERVICIO_ID")
    private BigInteger servicioId;
    @Column(name = "RUTA_ID")
    private BigInteger rutaId;
    @Column(name = "TRAMO_ID")
    private BigInteger tramoId;
    @Column(name = "EMPRESA_ID")
    private BigInteger empresaId;
    @Basic(optional = false)
    @Column(name = "APLICA_SENCILLO")
    private String aplicaSencillo;
    @Basic(optional = false)
    @Column(name = "APLICA_REDONDO")
    private String aplicaRedondo;
    @Column(name = "VIGENCIA_FECHA_INICIAL")
    @Temporal(TemporalType.DATE)
    private Date vigenciaFechaInicial;
    @Column(name = "VIGENCIA_FECHA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date vigenciaFechaFinal;
    @Column(name = "CANAL_VENTA")
    private String canalVenta;
    @Column(name = "COMPRA_MINIMA")
    private BigInteger compraMinima;
    @Column(name = "ADICIONAL1")
    private String adicional1;
    @Column(name = "ADICIONAL2")
    private String adicional2;
    @Column(name = "ADICIONAL3")
    private String adicional3;
    @Column(name = "ADICIONAL4")
    private String adicional4;
    @Column(name = "ADICIONAL5")
    private String adicional5;
    @Basic(optional = false)
    @Column(name = "CREADO_POR")
    private BigInteger creadoPor;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;
    @Basic(optional = false)
    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    public ErPromocionesTbl() {
    }

    public ErPromocionesTbl(BigDecimal promocionId) {
        this.promocionId = promocionId;
    }

    public ErPromocionesTbl(BigDecimal promocionId, String nombrePromocion, String codigoPromocion, String descripcionPromocion, String nombreProducto, String aplicaSencillo, String aplicaRedondo, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.promocionId = promocionId;
        this.nombrePromocion = nombrePromocion;
        this.codigoPromocion = codigoPromocion;
        this.descripcionPromocion = descripcionPromocion;
        this.nombreProducto = nombreProducto;
        this.aplicaSencillo = aplicaSencillo;
        this.aplicaRedondo = aplicaRedondo;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    public BigDecimal getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(BigDecimal promocionId) {
        this.promocionId = promocionId;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getDescripcionPromocion() {
        return descripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        this.descripcionPromocion = descripcionPromocion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigInteger getServicioId() {
        return servicioId;
    }

    public void setServicioId(BigInteger servicioId) {
        this.servicioId = servicioId;
    }

    public BigInteger getRutaId() {
        return rutaId;
    }

    public void setRutaId(BigInteger rutaId) {
        this.rutaId = rutaId;
    }

    public BigInteger getTramoId() {
        return tramoId;
    }

    public void setTramoId(BigInteger tramoId) {
        this.tramoId = tramoId;
    }

    public BigInteger getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(BigInteger empresaId) {
        this.empresaId = empresaId;
    }

    public String getAplicaSencillo() {
        return aplicaSencillo;
    }

    public void setAplicaSencillo(String aplicaSencillo) {
        this.aplicaSencillo = aplicaSencillo;
    }

    public String getAplicaRedondo() {
        return aplicaRedondo;
    }

    public void setAplicaRedondo(String aplicaRedondo) {
        this.aplicaRedondo = aplicaRedondo;
    }

    public Date getVigenciaFechaInicial() {
        return vigenciaFechaInicial;
    }

    public void setVigenciaFechaInicial(Date vigenciaFechaInicial) {
        this.vigenciaFechaInicial = vigenciaFechaInicial;
    }

    public Date getVigenciaFechaFinal() {
        return vigenciaFechaFinal;
    }

    public void setVigenciaFechaFinal(Date vigenciaFechaFinal) {
        this.vigenciaFechaFinal = vigenciaFechaFinal;
    }

    public String getCanalVenta() {
        return canalVenta;
    }

    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    public BigInteger getCompraMinima() {
        return compraMinima;
    }

    public void setCompraMinima(BigInteger compraMinima) {
        this.compraMinima = compraMinima;
    }

    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    public String getAdicional3() {
        return adicional3;
    }

    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    public String getAdicional4() {
        return adicional4;
    }

    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    public String getAdicional5() {
        return adicional5;
    }

    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    public BigInteger getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Date getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promocionId != null ? promocionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErPromocionesTbl)) {
            return false;
        }
        ErPromocionesTbl other = (ErPromocionesTbl) object;
        if ((this.promocionId == null && other.promocionId != null) || (this.promocionId != null && !this.promocionId.equals(other.promocionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "er_control.entidades.ErPromocionesTbl[promocionId=" + promocionId + "]";
    }

}
