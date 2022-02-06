/*
 * Guia.java
 *
 * Created on 26 de diciembre de 2007, 05:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package WS_CONTROL.paquer.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Clase Entidad Guia
 * 
 * @author galbarran
 */
@Entity
@Table(name = "PAQ_GUIAS_TBL")
public class Guia implements Serializable {
    // tipos de guia
//    public static int GUIA_OPERATIVA = 1;
//    public static int GUIA_MENSAJERIA_LOCAL = 2;
    // status de la guia
    public static String CANCELADA = "CA";
    public static String VENDIDA = "VT";
    // tipo de guia
    public static String TIPO_CREDITO = "CRE";
    public static String TIPO_MOSTRADOR = "MOS";
    public static String TIPO_FLETEXCOBRAR = "FXC";
    public static String TIPO_GUIAPREPAGADA = "GP";
    // tipo de pago
    public static String PAGO_CREDITO = "CRE";
    public static String PAGO_MOSTRADOR = "MOS";
    public static String PAGO_FLETEXCOBRAR = "FXC";
    public static String PAGO_GUIAPREPAGADA = "GP";
    public static String TABLENAME= "PAQ_GUIAS_TBL";
    public static String TABLENAMEPAQ_G= "PAQ_G_PAQUETES_TBL";
    public static String TABLENAMEPAGOS= "PAQ_GO_PAGOS_TBL";
    public static String TABLENAMESERVICIOS= "paq_g_servicios_tbl";
    public static String TABLENAMEPAQUETE= "paq_paquetes_tbl";

    /*
    @Id
    @Column(name = "GUIA_ID", nullable = false)
    private BigDecimal guiaId;
        
    @Column(name="NUM_GUIA")
    private BigDecimal num_guia;      
    
//    @Column(name="CORTE_ID")
//    private BigDecimal corte_id;

    
    @Column(name="NUM_GUIA_MANUAL")
    private BigDecimal num_guia_manual;

     *
     */
    @Embedded
      @AttributeOverrides({
        @AttributeOverride(name="nombre",column=@Column(name="REMITENTE_NOMBRE")),
        @AttributeOverride(name="telefono",column=@Column(name="REMITENTE_TELEFONO")),
        @AttributeOverride(name="rfc",column=@Column(name="REMITENTE_RFC")),        
        @AttributeOverride(name="domicilio",column=@Column(name="REMITENTE_DOMICILIO")),
        @AttributeOverride(name="colonia",column=@Column(name="REMITENTE_COLONIA")),
        @AttributeOverride(name="codigoPostal",column=@Column(name="REMITENTE_CODIGOPOSTAL")),
        @AttributeOverride(name="ciudad",column=@Column(name="REMITENTE_CIUDAD")),
        @AttributeOverride(name="estado",column=@Column(name="REMITENTE_ESTADO")),
        @AttributeOverride(name="pais",column=@Column(name="REMITENTE_PAIS"))
    })    
    private DatosPersona remitente;
    
    @Embedded
      @AttributeOverrides({
        @AttributeOverride(name="nombre",column=@Column(name="DESTINATARIO_NOMBRE")),
        @AttributeOverride(name="telefono",column=@Column(name="DESTINATARIO_TELEFONO")),
        @AttributeOverride(name="rfc",column=@Column(name="DESTINATARIO_RFC")),        
        @AttributeOverride(name="domicilio",column=@Column(name="DESTINATARIO_DOMICILIO")),
        @AttributeOverride(name="colonia",column=@Column(name="DESTINATARIO_COLONIA")),
        @AttributeOverride(name="codigoPostal",column=@Column(name="DESTINATARIO_CODIGOPOSTAL")),
        @AttributeOverride(name="ciudad",column=@Column(name="DESTINATARIO_CIUDAD")),
        @AttributeOverride(name="estado",column=@Column(name="DESTINATARIO_ESTADO")),
        @AttributeOverride(name="pais",column=@Column(name="DESTINATARIO_PAIS"))
    })    
    private DatosPersona destinatario;     
    
    @Column(name="PRIORIDAD")
    private Integer clasificacion;
    
//    @Column(name="TIPO_ENVIO")
//    private int tipo_envio;    

    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_pago;

    @Column(name = "DESCUENTO")
    private float descuento = 0f;    
    
    @Column(name = "IMPORTE")
    private float importe;

    @Column(name = "IVA")
    private float iva;
    
//    @Column(name = "GUIA_MANUAL_ID")
//    private BigDecimal guiaManualId;
    
    @Column(name = "TOTAL")
    private float total;    

    //@JoinColumn(name="VENDEDOR_ID", referencedColumnName="USUARIO_ID")
    //@JoinColumn(name="VENDEDOR_ID", referencedColumnName="OPERADOR_ID")
    //@OneToOne
//    @Column(name = "VENDEDOR_ID")
//    private BigDecimal vendedorId;
    
//    @Column(name = "EMPLEADO_ID")
//    //@JoinColumn(name="EMPLEADO_ID",referencedColumnName="USUARIO_ID")
//    private long empleadoId;

    //@Column(name = "SUCURSAL_ID")
    @Column(name="SUCURSAL_ID")
    private long sucursalId;
    
//    @Column (name="CAJA_ID")
//    private long cajaId;

    //@Column(name = "CLIENTE_ID")
    @Column(name="CLIENTE_ID")
    private BigDecimal clienteId;
    
    @Column(name="CLIENTE_PAGO_ID")
    private BigDecimal clientePagoId;

    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "VALOR_DECLARADO")
    private BigDecimal valorDeclarado;

    @Id
    @Column(name = "RUTA_ID")
    private long rutaId;
    
//    @Column(name="TIPO_PAGO")
//    private String tipoPago = PAGO_MOSTRADOR;
    
//    @Column(name="TIPO_GUIA")
//    private String tipoGuia;
    
    @Column(name="RETENCION")
    private float retencion;
    
//    @Column(name= "STATUS")
//    private String status = VENDIDA;
    
    @Column(name = "RESPONSABLE_NOMBRE")
    private String responsable_nombre;

    @Column(name = "RESPONSABLE_IDENTIFICACION")
    private String responsable_identificacion;
    
    @Column(name = "RESPONSABLE_TIPODOC")
    private String responsable_tipodoc;
    
    @Column(name = "CREADO_POR")
    private long creado_por;
    
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_creacion;
    
    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private long ultima_actualizacion_por;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultima_fecha_actualizacion;

    @Column(name = "ADICIONAL1")
    private String ADICIONAL1;

     @Column(name = "ADICIONAL2")
    private String ADICIONAL2;

    @Column(name = "DESTINATARIO_EMAIL")
    private String Destinatario_Email;

 // @Column(name = "RESPONSABLE_EMAIL")
  //  private String Respondable_Email;

        

    private List<Paquete> paquetes;
    
    private Ruta ruta;
    
    private List<Servicio> servicios;
    
//    @JoinColumn(name="LISTAPRECIOS_ID")
//    @OneToOne
//    private ListaPrecios listaPrecios;

//    @JoinColumn(name="LISTAPRECIOS_ID")
//    private float listaPrecios_id;


    /** Creates a new instance of Guia */
    public Guia() {
    }

     /** Creates a new instance of Guia whit parameters*/
    public Guia(Vector res) {
        //this.setGuiaId((BigDecimal)res.get(0));
        this.setClasificacion(((BigDecimal)res.get(2)).intValue());
        this.setFecha((Date)res.get(3));
        this.setImporte(((BigDecimal)res.get(4)).floatValue());
        this.setIva(((BigDecimal)res.get(5)).floatValue());
        if(res.get(8)!=null)
            this.setClienteId(((BigDecimal)res.get(8)));
        this.setObservaciones(res.get(9)==null?"":res.get(9).toString());
        this.setValorDeclarado((BigDecimal)res.get(10));
        this.setTotal(((BigDecimal)res.get(11)).floatValue());
            DatosPersona rem = new DatosPersona();
            rem.setNombre(res.get(12)==null?"":res.get(12).toString());
            rem.setRfc(res.get(19)==null?"":res.get(19).toString());
            rem.setTelefono(res.get(13)==null?"":res.get(13).toString());
            Direccion dRem = new Direccion();
            dRem.setCiudad(res.get(17)==null?"":res.get(17).toString());
            dRem.setCodigoPostal(res.get(16)==null?"":res.get(16).toString());
            dRem.setColonia(res.get(15)==null?"":res.get(15).toString());
            dRem.setDomicilio(res.get(14)==null?"":res.get(14).toString());
            dRem.setEstado(res.get(18)==null?"":res.get(18).toString());
            dRem.setPais(res.get(44)==null?"":res.get(44).toString());
            rem.setDireccion(dRem);
        this.setRemitente(rem);
            DatosPersona des = new DatosPersona();
            des.setNombre(res.get(20)==null?"":res.get(20).toString());
            des.setRfc(res.get(27)==null?"":res.get(27).toString());
            des.setTelefono(res.get(21)==null?"":res.get(21).toString());
            Direccion dDes = new Direccion();
            dDes.setCiudad(res.get(25)==null?"":res.get(25).toString());
            dDes.setCodigoPostal(res.get(24)==null?"":res.get(24).toString());
            dDes.setColonia(res.get(23)==null?"":res.get(23).toString());
            dDes.setDomicilio(res.get(22)==null?"":res.get(22).toString());
            dDes.setEstado(res.get(26)==null?"":res.get(26).toString());
            dDes.setPais(res.get(45)==null?"":res.get(45).toString());
            des.setDireccion(dDes);
        this.setDestinatario(des);
        //res.get()==null?"":res.get().toString()
        this.setResponsable_nombre(res.get(28)==null?"":res.get(28).toString());
        this.setResponsable_identificacion(res.get(29)==null?"":res.get(29).toString());
        this.setResponsable_tipodoc(res.get(30)==null?"":res.get(30).toString());
        //this.setTipoPago(res.get(35)==null?"":res.get(35).toString());
        this.setFecha_pago((Date)res.get(36));
        //this.setStatus(res.get(37)==null?"":res.get(37).toString());
        //this.setTipoGuia(res.get(38)==null?"":res.get(38).toString());
        //this.setListaPrecios_id(Long.valueOf(res.get(40).toString()));
        //this.setNum_guia((BigDecimal)res.get(41));
        //this.setNum_guia_manual((BigDecimal)res.get(42));
        this.setDescuento(((BigDecimal)res.get(43)).floatValue());
        this.setRetencion(((BigDecimal)res.get(47)).floatValue());
        this.setCreado_por(((BigDecimal)res.get(47)).longValue());
        this.setFecha_creacion((Date)res.get(49));
        this.setUltima_actualizacion_por(((BigDecimal)res.get(50)).longValue());
        this.setUltima_fecha_actualizacion((Date)res.get(51));
        //this.setCorte_id((BigDecimal)res.get(52));
//        this.setServicioSinRet("N");
    }   
    
//    /**
//     * Crear una nueva instancia de  Guia con los valores especificados.
//     * @param guiaId el guiaId del Guia
//     */
//    public Guia(BigDecimal guiaId) {
//        this.setGuiaId(guiaId);
//    }

//    /**
//     * Obtener el guiaId de este Guia.
//     * @return el guiaId
//     */
//    public BigDecimal getGuiaId() {
//        return this.guiaId;
//    }
//
//    /**
//     * Define el guiaId de este Guia al valor especificado.
//     * @param guiaId el nuevoguiaId
//     */
//    public void setGuiaId(BigDecimal guiaId) {
//        this.guiaId = guiaId;
//    }

    /**
     * Obtener el fecha de este Guia.
     * @return el fecha
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     * Define el fecha de este Guia al valor especificado.
     * @param fecha el nuevofecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtener el importe de este Guia.
     * @return el importe
     */
    public float getImporte() {
        return this.importe;
    }

    /**
     * Define el importe de este Guia al valor especificado.
     * @param importe el nuevoimporte
     */
    public void setImporte(float importe) {
        this.importe = importe;
    }

    /**
     * Obtener el iva de este Guia.
     * @return el iva
     */
    public float getIva() {
        return this.iva;
    }

    /**
     * Define el iva de este Guia al valor especificado.
     * @param iva el nuevoiva
     */
    public void setIva(float iva) {
        this.iva = iva;
    }

   

    /**
     * Obtener el observaciones de este Guia.
     * @return el observaciones
     */
    public String getObservaciones() {
        return this.observaciones;
    }

    /**
     * Define el observaciones de este Guia al valor especificado.
     * @param observaciones el nuevoobservaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Obtener el valorDeclarado de este Guia.
     * @return el valorDeclarado
     */
    public BigDecimal getValorDeclarado() {
        return this.valorDeclarado;
    }

    /**
     * Define el valorDeclarado de este Guia al valor especificado.
     * @param valorDeclarado el nuevovalorDeclarado
     */
    public void setValorDeclarado(BigDecimal valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    /**
     * Obtener el rutaId de este Guia.
     * @return el rutaId
     */
    /*
    public Ruta getRutaId() {
        return this.rutaId;
    }*/

    /**
     * Define el rutaId de este Guia al valor especificado.
     * @param rutaId el nuevorutaId
     */
    /*
    public void setRutaId(Ruta rutaId) {
        this.rutaId = rutaId;
    }
*/
    /**
     * Returna un valor de codigo hash para el objeto.  Esta implementacion computa
     * un valor de codigo hash basado sobre los campos id en este objeto.
     * @return  un valor de codigo hash para este objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        //hash += (this.getGuiaId() != null ? this.getGuiaId().hashCode() : 0);
        return hash;
    }

    /**
     * Determina si otro objeto es igual a este Guia.  El resultado es  
     * <code>verdadero</code> so y solo so el argumento no es nulo y es unGuia objeto que tiene  valores idonticos en el campo id tal como este objeto.
     * @param objeto que referencia el objeto con el cual comparar @return <code>verdadero</code> si este objeto es idontico al argumento; de otra manera sero<code>falso</code>.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guia)) {
            return false;
        }
        Guia other = (Guia)object;
        //if (this.getGuiaId() != other.getGuiaId() && (this.getGuiaId() == null || !this.getGuiaId().equals(other.getGuiaId()))) return false;
        return true;
    }

    /**
     * Returna un representacion de cadena del objeto.  Esta implementacion construye esta representacion basada sobre los campos id.
     * @return una  representacion de cadena del objeto.
     */
    @Override
    public String toString() {
        return "dominio.Guia[rutaId=" + getRutaId() + "]";
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Integer getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Integer clasificacion) {
        this.clasificacion = clasificacion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    /*
    public boolean isFletexCobrar(){
        for (GuiaServicio elem : servicios) {
            if ( elem.getDescripcion().equals("FLETE POR COBRAR") ){
                return true;
            }
        }                
        
        return false;
    }    */

    public boolean isAcuseRecibo(){
        for (Servicio elem : getServicios()) {
            if ( elem.getDescripcion().equals("ACUSE DE RECIBO") ){
                return true;
            }
        }                
        
        return false;
    }        
    
    public boolean isExtraUrgente(){
        return clasificacion == 0 ? true : false;
    }
    
    public boolean isEntregaDomicilio(){
        System.out.println("Serviocios isEntregaDomicilio()");
        for(int i =0; i< getServicios().size(); i++)
            System.out.println("("+i+") "+ getServicios().get(i).getDescripcion());
        
        for (Servicio elem : getServicios()) {
            System.out.println("checa ED: "+elem.getDescripcion());
            if ( elem.getDescripcion().equals(Servicio.ENTREGA_DOMICILIO) ){
                return true;
            }
        }                
        
        return false;
    }

    public boolean isSobreDomicilio(){
        System.out.println("\nServiocios isSobreDomicilio()");
        for(int i =0; i< getServicios().size(); i++)
            System.out.println("("+i+") "+ getServicios().get(i).getDescripcion());
        
        for (Servicio elem : getServicios()) {
            System.out.println("checa SD: "+elem.getDescripcion());
            if ( elem.getDescripcion().equals(Servicio.SOBRE_DOMICILIO) ){
                return true;
            }
        }                
        
        return false;
    }    
    
    public boolean isExtraurgenteDomicilio(){
        for (Servicio elem : getServicios()) {
            System.out.println("checa SD: "+elem.getDescripcion());
            if ( elem.getDescripcion().equals(Servicio.EXTRAURGENTE_DOMICILIO) ){
                return true;
            }
        }                
        
        return false;
    }     
    
    public String getResponsable_identificacion() {
        return responsable_identificacion;
    }

    public void setResponsable_identificacion(String responsable_identificacion) {
        this.responsable_identificacion = responsable_identificacion;
    }

    public String getResponsable_nombre() {
        return responsable_nombre;
    }

    public void setResponsable_nombre(String responsable_nombre) {
        this.responsable_nombre = responsable_nombre;
    }

    public DatosPersona getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(DatosPersona destinatario) {
        this.destinatario = destinatario;
    }

    public DatosPersona getRemitente() {
        return remitente;
    }

    public void setRemitente(DatosPersona remitente) {
        this.remitente = remitente;
    }

//    public String getTipoPago() {
//        return tipoPago;
//    }
//
//    public void setTipoPago(String tipoPago) {
//        this.tipoPago = tipoPago;
//    }
    /*
    public boolean isPagoMostrador(){
        //return tipoPago.equals(PAGO_MOSTRADOR);
        return tipoGuia.equals(PAGO_MOSTRADOR);
    }
//    public boolean isPagoFletexCobrar(){
//        return tipoPago.equals(PAGO_FLETEXCOBRAR);
//        //return tipoGuia.equals(PAGO_FLETEXCOBRAR);
//    }
    public boolean isPagoCredito(){
        //return tipoPago.equals(PAGO_CREDITO);
        return tipoGuia.equals(PAGO_CREDITO);
    }
    public boolean isPagoGuiaPrepagada(){
        //return tipoPago.equals(PAGO_GUIAPREPAGADA);
        return tipoGuia.equals(PAGO_GUIAPREPAGADA);
    }
    
    public boolean isGuiaPrepagada(){
        return tipoGuia.equals(TIPO_GUIAPREPAGADA);
    }
    public boolean isGuiaCredito(){
        return tipoGuia.equals(TIPO_CREDITO);
    }
    public boolean isGuiaMostrador(){
        return tipoGuia.equals(TIPO_MOSTRADOR);
    }
    public boolean isGuiaFletexCobrar(){
        return tipoGuia.equals(TIPO_FLETEXCOBRAR);
    }
//    public boolean isGuiaManual(){
//        return num_guia_manual == null ? false : true;
//    }
     *
     */

    public String getResponsable_tipodoc() {
        return responsable_tipodoc;
    }

    public void setResponsable_tipodoc(String responsable_tipodoc) {
        this.responsable_tipodoc = responsable_tipodoc;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
    
//    public boolean isVENDIDA(){
//        return status.equals(VENDIDA) ? true : false;
//    }
//
//    public boolean isCANCELADA(){
//        return status.equals(CANCELADA) ? true : false;
//    }

//    public String getTipoGuia() {
//        return tipoGuia;
//    }
//
//    public void setTipoGuia(String tipoGuia) {
//        this.tipoGuia = tipoGuia;
//    }

   
//    public ListaPrecios getListaPrecios() {
//        return listaPrecios;
//    }
//
//    public void setListaPrecios(ListaPrecios listaPrecios) {
//        this.listaPrecios = listaPrecios;
//    }
    
  
    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

//    public BigDecimal getNum_guia() {
//        return num_guia;
//    }
//
//    public void setNum_guia(BigDecimal num_guia) {
//        this.num_guia = num_guia;
//    }
//
//    public BigDecimal getNum_guia_manual() {
//        return num_guia_manual;
//    }
//
//    public void setNum_guia_manual(BigDecimal num_guia_manual) {
//        this.num_guia_manual = num_guia_manual;
//    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Date getUltima_fecha_actualizacion() {
        return ultima_fecha_actualizacion;
    }

    public void setUltima_fecha_actualizacion(Date ultima_fecha_actualizacion) {
        this.ultima_fecha_actualizacion = ultima_fecha_actualizacion;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public long getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(long creado_por) {
        this.creado_por = creado_por;
    }

    public long getUltima_actualizacion_por() {
        return ultima_actualizacion_por;
    }

    public void setUltima_actualizacion_por(long ultima_actualizacion_por) {
        this.ultima_actualizacion_por = ultima_actualizacion_por;
    }

    public float getRetencion() {
        return retencion;
    }

    public void setRetencion(float retencion) {
        this.retencion = retencion;
    }
/*
    public BigDecimal getCorte_id() {
        return corte_id;
    }

    public void setCorte_id(BigDecimal corte_id) {
        this.corte_id = corte_id;
    }

    */

     /**
     * Obtener el Adicional1 de este Guia.
     * @return el Adicional1
     */
    public String getAdicional1() {
        return this.ADICIONAL1;
    }

    /**
     * Define el observaciones de este Guia al valor especificado.
     * @param observaciones el nuevoobservaciones
     */
    public void setAdicional1(String Adicional1) {
        this.ADICIONAL1 = Adicional1;
    }

    /**
     * @return the Destinatario_Email
     */
    public String getDestinatario_Email() {
        return Destinatario_Email;
    }

    /**
     * @param Destinatario_Email the Destinatario_Email to set
     */
    public void setDestinatario_Email(String Destinatario_Email) {
        this.Destinatario_Email = Destinatario_Email;
    }

    /**
     * @return the Respondable_Email
     */
   // public String getRespondable_Email() {
//        return Respondable_Email;
  //  }

    /**
     * @param Respondable_Email the Respondable_Email to set
     */
  //  public void setRespondable_Email(String Respondable_Email) {
  //      this.Respondable_Email = Respondable_Email;
  //  }

    /**
     * @return the ADICIONAL2
     */
    public String getAdicional2() {
        return ADICIONAL2;   
    }

    /**
     * @param ADICIONAL2 the ADICIONAL2 to set
     */
    public void setAdicional2(String ADICIONAL2) {
        this.ADICIONAL2 = ADICIONAL2;
    }

    /**
     * @return the vendedorId
     */
//    public BigDecimal getVendedorId() {
//        return vendedorId;
//    }
//
//    /**
//     * @param vendedorId the vendedorId to set
//     */
//    public void setVendedorId(BigDecimal vendedorId) {
//        this.vendedorId = vendedorId;
//    }

//    /**
//     * @return the empleadoId
//     */
//    public long getEmpleadoId() {
//        return empleadoId;
//    }
//
//    /**
//     * @param empleadoId the empleadoId to set
//     */
//    public void setEmpleadoId(long empleadoId) {
//        this.empleadoId = empleadoId;
//    }

    /**
     * @return the sucursalId
     */
    public long getSucursalId() {
        return sucursalId;
    }

    /**
     * @param sucursalId the sucursalId to set
     */
    public void setSucursalId(long sucursalId) {
        this.sucursalId = sucursalId;
    }

//    /**
//     * @return the cajaId
//     */
//    public long getCajaId() {
//        return cajaId;
//    }
//
//    /**
//     * @param cajaId the cajaId to set
//     */
//    public void setCajaId(long cajaId) {
//        this.cajaId = cajaId;
//    }

    /**
     * @return the clienteId
     */
    public BigDecimal getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the clientePagoId
     */
    public BigDecimal getClientePagoId() {
        return clientePagoId;
    }

    /**
     * @param clientePagoId the clientePagoId to set
     */
    public void setClientePagoId(BigDecimal clientePagoId) {
        this.clientePagoId = clientePagoId;
    }

    /**
     * @return the rutaId
     */
    public long getRutaId() {
        return rutaId;
    }

    /**
     * @param rutaId the rutaId to set
     */
    public void setRutaId(long rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * @return the listaPrecios_id
     */
//    public float getListaPrecios_id() {
//        return listaPrecios_id;
//    }
//
//    /**
//     * @param listaPrecios_id the listaPrecios_id to set
//     */
//    public void setListaPrecios_id(float listaPrecios_id) {
//        this.listaPrecios_id = listaPrecios_id;
//    }

    /**
     * @return the ruta
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

}
