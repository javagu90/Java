/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * @author brojas
 */
@Entity
@Table(name = "ER_PAGOS_TBL")
/*@NamedQueries({
    @NamedQuery(name = "ErPagosTbl.findAll", query = "SELECT e FROM ErPagosTbl e"),
    @NamedQuery(name = "ErPagosTbl.findByPagoId", query = "SELECT e FROM ErPagosTbl e WHERE e.pagoId = :pagoId"),
    @NamedQuery(name = "ErPagosTbl.findByClienteId", query = "SELECT e FROM ErPagosTbl e WHERE e.clienteId = :clienteId"),
    @NamedQuery(name = "ErPagosTbl.findByTipoPago", query = "SELECT e FROM ErPagosTbl e WHERE e.tipoPago = :tipoPago"),
    @NamedQuery(name = "ErPagosTbl.findByFormaPagoId", query = "SELECT e FROM ErPagosTbl e WHERE e.formaPagoId = :formaPagoId"),
    @NamedQuery(name = "ErPagosTbl.findByEstado", query = "SELECT e FROM ErPagosTbl e WHERE e.estado = :estado"),
    @NamedQuery(name = "ErPagosTbl.findByMonto", query = "SELECT e FROM ErPagosTbl e WHERE e.monto = :monto"),
    @NamedQuery(name = "ErPagosTbl.findByFecha", query = "SELECT e FROM ErPagosTbl e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "ErPagosTbl.findByFechaContable", query = "SELECT e FROM ErPagosTbl e WHERE e.fechaContable = :fechaContable"),
    @NamedQuery(name = "ErPagosTbl.findByReferencia", query = "SELECT e FROM ErPagosTbl e WHERE e.referencia = :referencia"),
    @NamedQuery(name = "ErPagosTbl.findByNoTarjeta", query = "SELECT e FROM ErPagosTbl e WHERE e.noTarjeta = :noTarjeta"),
    @NamedQuery(name = "ErPagosTbl.findByAdicional1", query = "SELECT e FROM ErPagosTbl e WHERE e.adicional1 = :adicional1"),
    @NamedQuery(name = "ErPagosTbl.findByAdicional2", query = "SELECT e FROM ErPagosTbl e WHERE e.adicional2 = :adicional2"),
    @NamedQuery(name = "ErPagosTbl.findByAdicional3", query = "SELECT e FROM ErPagosTbl e WHERE e.adicional3 = :adicional3"),
    @NamedQuery(name = "ErPagosTbl.findByAdicional4", query = "SELECT e FROM ErPagosTbl e WHERE e.adicional4 = :adicional4"),
    @NamedQuery(name = "ErPagosTbl.findByAdicional5", query = "SELECT e FROM ErPagosTbl e WHERE e.adicional5 = :adicional5"),
    @NamedQuery(name = "ErPagosTbl.findByCreadoPor", query = "SELECT e FROM ErPagosTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErPagosTbl.findByFechaCreacion", query = "SELECT e FROM ErPagosTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErPagosTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErPagosTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErPagosTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErPagosTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion")})
 *
 */
public class ErPagosTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAGO_ID")
    private BigDecimal pagoId;
    @Basic(optional = false)
    @Column(name = "CLIENTE_ID")
    private BigInteger clienteId;
    @Column(name = "TIPO_PAGO")
    private String tipoPago;
    @Column(name = "FORMA_PAGO_ID")
    private BigInteger formaPagoId;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "FECHA_CONTABLE")
    @Temporal(TemporalType.DATE)
    private Date fechaContable;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "NO_TARJETA")
    private String noTarjeta;
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

    public ErPagosTbl() {
    }

    public ErPagosTbl(BigDecimal pagoId) {
        this.pagoId = pagoId;
    }

    public ErPagosTbl(BigDecimal pagoId, BigInteger clienteId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.pagoId = pagoId;
        this.clienteId = clienteId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

     public ErPagosTbl(String[] campos) {
        DateFormat formatter ;
        Date date ;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.setPagoId(new BigDecimal(campos[0]));
        this.setClienteId(new BigInteger(campos[1]));
        this.setTipoPago(campos[2]);
        this.setFormaPagoId(new BigInteger(campos[3]));
        this.setEstado(campos[4]);
        this.setMonto(new BigDecimal(campos[5]));

       System.out.println("COrrectamente");

        try {
          if(campos[6]!=null && !campos[6].trim().equals(""))
        {
            System.out.println("Fecha  "+campos[6].trim());
            this.setFecha((Date)formatter.parse(campos[6].trim()));
         }
          if(campos[7]!=null && !campos[7].trim().equals(""))
        {
             System.out.println("Fecha  "+campos[7].trim());
            this.setFechaContable((Date)formatter.parse(campos[7].trim()));

        }
          this.setReferencia(campos[8]);
          this.setNoTarjeta(campos[9]);

        } catch (Exception e) {
            e.printStackTrace();
        }
       System.out.println("COrrectamente 1");
    }


    public BigDecimal getPagoId() {
        return pagoId;
    }

    public void setPagoId(BigDecimal pagoId) {
        this.pagoId = pagoId;
    }

    public BigInteger getClienteId() {
        return clienteId;
    }

    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigInteger getFormaPagoId() {
        return formaPagoId;
    }

    public void setFormaPagoId(BigInteger formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(Date fechaContable) {
        this.fechaContable = fechaContable;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta) {
        this.noTarjeta = noTarjeta;
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
        hash += (pagoId != null ? pagoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErPagosTbl)) {
            return false;
        }
        ErPagosTbl other = (ErPagosTbl) object;
        if ((this.pagoId == null && other.pagoId != null) || (this.pagoId != null && !this.pagoId.equals(other.pagoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ErPagosTbl[pagoId=" + pagoId + "]";
    }



}
