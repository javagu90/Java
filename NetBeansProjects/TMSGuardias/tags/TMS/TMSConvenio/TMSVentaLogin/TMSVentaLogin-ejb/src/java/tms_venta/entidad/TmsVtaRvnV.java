package tms_venta.entidad;

import java.io.Serializable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity

@NamedQueries( { 
@NamedQuery(name = "TmsVtaRvnV.findAll", query = "select o from TmsVtaRvnV o order by o.fechaHoraCorrida desc"),
@NamedQuery(name = "TmsVtaRvnV.findByCRvn", query = "select o from TmsVtaRvnV o where o.claveReservacion=:cRvn order by o.fechaHoraCorrida desc"),
@NamedQuery(name = "TmsVtaRvnV.findByNomResp", query = "select o from TmsVtaRvnV o where o.responsableReservacion like :nomResp order by o.fechaHoraCorrida desc")
        } )

@Table(name = "TMS_VTA_RVN_V")
public class TmsVtaRvnV implements Serializable {
    @Id
    @Column(name="RESERVACION_ID", nullable = false)
    private Long reservacionId;
    
    @Column(name="CLAVE_RESERVACION")
    private String claveReservacion;
    
    @Column(name="CLIENTE_ID")
    private Long clienteId;
    
    @Column(name="RESPONSABLE_RESERVACION")
    private String responsableReservacion;
    
    @Column(name="CANCELABLE")
    private String cancelable;
    
    @Column(name="TOTAL_RVDOS")
    private Long totalRvdos;
            
    @Column(name="TOTAL_CNDOS")
    private Long totalCndos;
    
    @Column(name="TOTAL_VTA")
    private Long totalVta;
    
    @Column(name="CORRIDA_ID")
    private Long corridaId;
    
    @Column(name="CLAVE_CORRIDA")
    private String claveCorrida;
    
    @Column(name="NO_ASIENTO")
    private Long noAsiento;
    
    @Column(name="TIPO_PASAJERO")
    private String tipoPasajero;
    
    @Column(name="FECHA_CREACION")
    private Timestamp fechaCreacion;
    
    @Column(name="ESTADO_RESERVACION")
    private String estadoReservacion;
    
    @Column(name="FECHA_HORA_CORRIDA")
    private Timestamp fechaHoraCorrida;
    
    @Column(name="ORIGEN")
    private String origen;
    
    @Column(name="DESTINO")
    private String destino;
    
    @Column(name="SERVICIO")
    private String servicio;
    
    @Column(name="EMPRESA")
    private String empresa;
   
   @Column(name="RUTA_ID")
    private long RutaId;
   
    private String hCorrida;
    private String fCorrida;
    private String hCreacion;
    private String fCreacion;
   
  
    public TmsVtaRvnV() { }

    public Long getReservacionId(){ return this.reservacionId; }
        
    public String getClaveReservacion(){ return this.claveReservacion; }
        
    public Long getClienteId(){ return this.clienteId; }
    
    public String getResponsableReservacion(){ return this.responsableReservacion; }
       
    public String getCancelable(){ return this.cancelable; }   
    
    public Long getTotalRvdos(){ return this.totalRvdos; }
    
    public Long getTotalCndos(){ return this.totalCndos; }
    
    public Long getTotalVta(){ return this.totalVta; }
        
    public Long getCorridaId(){ return this.corridaId; }
        
    public String getClaveCorrida(){ return this.claveCorrida; }
        
    public Long getNoAsiento(){ return this.noAsiento; }
        
    public String getTipoPasajero(){ return this.tipoPasajero; }
        
    public Timestamp getFechaCreacion(){ return this.fechaCreacion; }
        
    public String getEstadoReservacion(){ return this.estadoReservacion; }
        
    public Timestamp getFechaHoraCorrida(){ return this.fechaHoraCorrida; }
        
    public String getOrigen(){ return this.origen; }
        
    public String getDestino(){ return this.destino; }
        
    public String getServicio(){ return this.servicio; }
    
    public String getEmpresa(){ return this.empresa; }
    
    public void setReservacionId(Long reservacionId){ this.reservacionId=reservacionId; }
        
    public void setClaveReservacion(String claveReservacion){ this.claveReservacion=claveReservacion; }
        
    public void setClienteId(Long clienteId){ this.clienteId=clienteId; }
    
    public void setResponsableReservacion(String responsableReservacion){ this.responsableReservacion=responsableReservacion; }
       
    public void setCancelable(String cancelable){ this.cancelable=cancelable; }   
    
    public void setTotalRvdos(Long totalRvdos){ this.totalRvdos=totalRvdos; }
    
    public void setTotalCndos(Long totalCndos){ this.totalCndos=totalCndos; }
    
    public void setTotalVta(Long totalVta){ this.totalVta=totalVta; }
        
    public void setCorridaId(Long corridaId){ this.corridaId=corridaId; }
        
    public void setClaveCorrida(String claveCorrida){ this.claveCorrida=claveCorrida; }
        
    public void setNoAsiento(Long noAsiento){ this.noAsiento=noAsiento; }
        
    public void setTipoPasajero(String tipoPasajero){ this.tipoPasajero=tipoPasajero; }
        
    public void setFechaCreacion(Timestamp fechaCreacion){ this.fechaCreacion=fechaCreacion; }
        
    public void setEstadoReservacion(String estadoReservacion){ this.estadoReservacion=estadoReservacion; }
        
    public void setFechaHoraCorrida(Timestamp fechaHoraCorrida){ this.fechaHoraCorrida=fechaHoraCorrida; }
        
    public void setOrigen(String origen){ this.origen=origen; }
        
    public void setDestino(String destino){ this.destino=destino; }
        
    public void setServicio(String servicio){ this.servicio=servicio; }
    
    public void setEmpresa(String empresa){ this.empresa=empresa; }

    public long getRutaId() {return RutaId;}

    public void setRutaId(long RutaId) { this.RutaId = RutaId; }
    public String getHCorrida()
    {
        return hCorrida;
    }

    public void setHCorrida(String hCorrida)
    {
        this.hCorrida = hCorrida;
    }

    public String getfCreacion()
    {
        return fCreacion;
    }

    public void setfCreacion(String fCreacion)
    {
        this.fCreacion = fCreacion;
    }

    public String getFCorrida()
    {
        return fCorrida;
    }

    public void setFCorrida(String fCorrida)
    {
        this.fCorrida = fCorrida;
    }

    public String gethCreacion()
    {
        return hCreacion;
    }

    public void sethCreacion(String hCreacion)
    {
        this.hCreacion = hCreacion;
    }    
}
