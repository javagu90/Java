/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.paquer.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase Entidad Servicio
 *
 * @author galbarran
 */
@Entity
@Table(name = "PAQ_SERVICIOS_TBL")

public class Servicio implements Serializable {
    //----------------- codigos
    public static String VALOR_DECLARADO = "VDEC";
    public static final String CODIGO_ENTREGA_DOMICILIO = "DOM";
    public static final String CODIGO_SOBRE_DOMICILIO = "SOBDOM";
    public static final String CODIGO_OCURRE = "OCURRE";
    public static final String CODIGO_FLETE = "FLETE";
    public static final String CODIGO_VALOR_DECLARADO = "VDEC";
    //----------------- codigos.fin

    //----------------- descripciones
    public static final String ENTREGA_DOMICILIO = "ENTREGA A DOMICILIO";
    public static final String SOBRE_DOMICILIO = "SOBRE A DOMICILIO";
    public static final String EXTRAURGENTE_DOMICILIO = "EXTRAURGENTE DOMICILIO";
    public static final String FLETE_X_COBRAR = "FLETE POR COBRAR";
    public static final String FLETE = "FLETE";
    //----------------- descripciones.fin

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SERVICIO_ID", nullable = false)
    private BigDecimal servicioId;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "CLASIFICA_TIPO_ENVIO")
    private Integer clasificaTipoEnvio;

    @Column(name = "REQUIERE_RETENCION")
    private String requiereRetencion;

    @Column(name = "APLICA_DESCUENTO")
    private String aplicaDescuento;


    @Column(name = "CLASIFICACIONLP_ID")
    private Integer clasificaLpId;

    //c.CLASIFICACIONLP_ID,rs.ruta_id

    @Column(name = "RUTA_ID")
    private Integer rutaId;

    private BigDecimal importe;
    private BigDecimal descuento;
    private BigDecimal iva;
    private BigDecimal ivaRetenido;
    private BigDecimal total;


    //@OneToMany(mappedBy = "servicio")
    //private Collection<LPTarifa> lPTarifaCollection;

    /*
    @Id
    @Column(name = "SERVICIO_ID", nullable = false)
    private BigDecimal servicioId;

    @Column(name =  "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO_UNITARIO")
    private float precioUnitario;

    @Column(name = "NUM_PAQUETES")
    private int numPaquetes;

    @Column(name = "PRIORIDAD")
    private int prioridad;*/

    //@ManyToMany(mappedBy = "servicioIdCollection")
    //private Collection<Ruta> rutaIdCollection;

//    @JoinTable(name = "PAQ_SERVEXCLUSIONES_TBL", joinColumns =  {
//            @JoinColumn(name = "SERVICIO_ID", referencedColumnName = "SERVICIO_ID")
//        }, inverseJoinColumns =  {
//            @JoinColumn(name = "EXCLUSION", referencedColumnName = "SERVICIO_ID")
//        })
//    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//    private Collection<Servicio> exclusionCollection;

//    @OneToOne(mappedBy = "servicio")
//    private GuiaPServicioPK guiaPServicioPK;

    //@ManyToMany(mappedBy = "exclusionCollection")
    //private Collection<Servicio> servicioIdCollection;

    /** Creates a new instance of Servicio */
    public Servicio() {
    }

    public Servicio(String[] campos) {
        this.setServicioId(new BigDecimal(campos[0]));
        this.setCodigo(campos[1]);
        this.setDescripcion(campos[2]);
        this.setClasificaTipoEnvio(Integer.valueOf(campos[3]));
        this.setRequiereRetencion(campos[4]);
        this.setAplicaDescuento(campos[5]);
        this.setClasificaLpId(Integer.valueOf(campos[6]));
    }

    public void setPrecio(String[] campos) {
        this.setImporte(new BigDecimal(campos[7]));
        this.setDescuento(new BigDecimal(campos[8]));
        this.setIva(new BigDecimal(campos[9]));
        this.setIvaRetenido(new BigDecimal(campos[10]));
        this.setTotal(new BigDecimal(campos[11]));
    }

    public Servicio(Vector vs) {
        this.setServicioId(new BigDecimal(vs.get(0).toString()));
        this.setCodigo(vs.get(1).toString());
        this.setDescripcion(vs.get(2).toString());
        this.setClasificaTipoEnvio(Integer.parseInt(vs.get(3).toString()));
        this.setRequiereRetencion(vs.get(4).toString());
        this.setAplicaDescuento(vs.get(5).toString());
        this.setClasificaLpId(Integer.parseInt(vs.get(6).toString()));
        this.setRutaId(Integer.parseInt(vs.get(7).toString()));
    }


    /**
     * Crear una nueva instancia de  Servicio con los valores especificados.
     * @param servicioId el servicioId del Servicio
     */
    public Servicio(BigDecimal servicioId) {
        this.servicioId = servicioId;
    }

    /*
    public float getTarifa(int num_paquetes){
        int npaqs = getNumPaquetes();

        if ( npaqs > 0 ){
            int modulo = (num_paquetes % npaqs);
            if ( modulo > 0){
                return ((num_paquetes / npaqs) + 1) * precioUnitario;
            }else {
                return (num_paquetes / npaqs) * precioUnitario;
            }
        }else
            return precioUnitario;
    } // getTarifa
     * */

    /**
     * Obtener el servicioId de este Servicio.
     * @return el servicioId
     */
    public BigDecimal getServicioId() {
        return this.servicioId;
    }

    /**
     * Define el servicioId de este Servicio al valor especificado.
     * @param servicioId el nuevoservicioId
     */
    public void setServicioId(BigDecimal servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Obtener el descripcion de este Servicio.
     * @return el descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Define el descripcion de este Servicio al valor especificado.
     * @param descripcion el nuevodescripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    /**
//     * Obtener el exclusionCollection de este Servicio.
//     * @return el exclusionCollection
//     */
//    public Collection<Servicio> getExclusionCollection() {
//        return this.exclusionCollection;
//    }
//
//    /**
//     * Define el exclusionCollection de este Servicio al valor especificado.
//     * @param exclusionCollection el nuevoexclusionCollection
//     */
//    public void setExclusionCollection(Collection<Servicio> exclusionCollection) {
//        this.exclusionCollection = exclusionCollection;
//    }


    /**
     * Returna un valor de código hash para el objeto.  Esta implementación computa
     * un valor de código hash basado sobre los campos id en este objeto.
     * @return  un valor de código hash para este objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.servicioId != null ? this.servicioId.hashCode() : 0);
        return hash;
    }

    /**
     * Determina si otro objeto es igual a este Servicio.  El resultado es
     * <code>verdadero</code> sí y sólo sí el argumento no es nulo y es unServicio objeto que tiene  valores idénticos en el campo id tal como este objeto.
     * @param objeto que referencia el objeto con el cual comparar @return <code>verdadero</code> si este objeto es idéntico al argumento; de otra manera será<code>falso</code>.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio)object;
        if (this.servicioId != other.servicioId && (this.servicioId == null || !this.servicioId.equals(other.servicioId))) return false;
        return true;
    }

    /**
     * Returna un representación de cadena del objeto.  Esta implementación construye esta representación basada sobre los campos id.
     * @return una  representación de cadena del objeto.
     */
    @Override
    public String toString() {
        return "dominio.Servicio[servicioId=" + servicioId + "]";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getClasificaTipoEnvio() {
        return clasificaTipoEnvio;
    }

    public void setClasificaTipoEnvio(Integer clasificaTipoEnvio) {
        this.clasificaTipoEnvio = clasificaTipoEnvio;
    }

    public String getRequiereRetencion() {
        return requiereRetencion;
    }

    public void setRequiereRetencion(String requiereRetencion) {
        this.requiereRetencion = requiereRetencion;
    }

    public boolean isAplicaRetencion(){
        return requiereRetencion.equals("S");
    }

    public boolean isEntregaDomicilio(){
        return codigo.equals(CODIGO_ENTREGA_DOMICILIO);
    }

    public boolean isSobreDomicilio(){
        return codigo.equals(CODIGO_SOBRE_DOMICILIO);
    }

    public boolean isOcurre(){
        return codigo.equals(CODIGO_OCURRE);
    }

    public boolean isFletexCobrar(){
        return descripcion.equals(FLETE_X_COBRAR);
    }

    public String getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(String aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public Integer getClasificaLpId() {
        return clasificaLpId;
    }

    public void setClasificaLpId(Integer clasificaLpId) {
        this.clasificaLpId = clasificaLpId;
    }

    public Integer getRutaId() {
        return rutaId;
    }

    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    /**
     * @return the descuento
     */
    public BigDecimal getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the ivaRetenido
     */
    public BigDecimal getIvaRetenido() {
        return ivaRetenido;
    }

    /**
     * @param ivaRetenido the ivaRetenido to set
     */
    public void setIvaRetenido(BigDecimal ivaRetenido) {
        this.ivaRetenido = ivaRetenido;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
