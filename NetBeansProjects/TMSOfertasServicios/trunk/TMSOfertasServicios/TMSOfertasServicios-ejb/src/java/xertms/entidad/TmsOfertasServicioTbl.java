/*
 * TmsOfertasServicioTbl.java
 *
 * Created on 8 de octubre de 2007, 10:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
@NamedQuery(name = "TmsOfertasServicioTbl.findAll", query = "select o from TmsOfertasServicioTbl o"),
@NamedQuery(name = "TmsOfertasServicioTbl.findByName", query = "select o from TmsOfertasServicioTbl o where o.ofertaServicioNombre = :Nombre")
})
@Table(name = "TMS_OFERTAS_SERVICIO_TBL")
public class TmsOfertasServicioTbl implements Serializable {
    private String adicional1;
    private String adicional10;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;
    private String adicional6;
    private String adicional7;
    private String adicional8;
    private String adicional9;
    @Column(name="CORTESIAS_CORRIDA")
    private Long cortesiasCorrida;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    private String descripcion;
    @Column(name="DOMINGO_APLICA", nullable = false)
    private String domingoAplica;
    @Column(name="EMPRESA_ID", nullable = false)
    private Long empresaId;
    @Column(name="ESTUDIANTES_CORRIDA")
    private Long estudiantesCorrida;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name="HORA_CORRIDA", nullable = false)
    private Timestamp horaCorrida;
    @Column(name="JUEVES_APLICA", nullable = false)
    private String juevesAplica;
    @Column(name="LUNES_APLICA", nullable = false)
    private String lunesAplica;
    @Column(name="MARTES_APLICA", nullable = false)
    private String martesAplica;
    @Column(name="MENORES_CORRIDA")
    private Long menoresCorrida;
    @Column(name="MIERCOLES_APLICA", nullable = false)
    private String miercolesAplica;    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_OFERTAS_SERVICIO_GENERADOR")
    @SequenceGenerator(name = "TMS_OFERTAS_SERVICIO_GENERADOR", 
                       sequenceName = "TMS_OFERTAS_SERV_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name="OFERTA_SERVICIO_ID", nullable = false)
    private Long ofertaServicioId;
    @Column(name="OFERTA_SERVICIO_NOMBRE", nullable = false)
    private String ofertaServicioNombre;
    @Column(name="PROFESORES_CORRIDA")
    private Long profesoresCorrida;
    @Column(name="RUTA_ID", nullable = false)
    private Long rutaId;
    @Column(name="SABADO_APLICA", nullable = false)
    private String sabadoAplica;
    @Column(name="SENECTUD_CORRIDA")
    private Long senectudCorrida;
    @Column(name="SERVICIO_ID", nullable = false)
    private Long servicioId;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;
    @Column(name="VIERNES_APLICA", nullable = false)
    private String viernesAplica;
    @Column(name="TRAMO_DESTINO_ID", nullable = false)
    private Long tramoDestinoId;
    @Column(name="TRAMO_ORIGEN_ID", nullable = false)
    private Long tramoOrigenId;

    public TmsOfertasServicioTbl() {
    }

    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional10() {
        return adicional10;
    }

    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
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

    public String getAdicional6() {
        return adicional6;
    }

    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    public String getAdicional7() {
        return adicional7;
    }

    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    public String getAdicional8() {
        return adicional8;
    }

    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    public String getAdicional9() {
        return adicional9;
    }

    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    public Long getCortesiasCorrida() {
        return cortesiasCorrida;
    }

    public void setCortesiasCorrida(Long cortesiasCorrida) {
        this.cortesiasCorrida = cortesiasCorrida;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDomingoAplica() {
        return domingoAplica;
    }

    public void setDomingoAplica(String domingoAplica) {
        this.domingoAplica = domingoAplica;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getEstudiantesCorrida() {
        return estudiantesCorrida;
    }

    public void setEstudiantesCorrida(Long estudiantesCorrida) {
        this.estudiantesCorrida = estudiantesCorrida;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getHoraCorrida() {
        return horaCorrida;
    }

    public void setHoraCorrida(Timestamp horaCorrida) {
        this.horaCorrida = horaCorrida;
    }

    public String getJuevesAplica() {
        return juevesAplica;
    }

    public void setJuevesAplica(String juevesAplica) {
        this.juevesAplica = juevesAplica;
    }

    public String getLunesAplica() {
        return lunesAplica;
    }

    public void setLunesAplica(String lunesAplica) {
        this.lunesAplica = lunesAplica;
    }

    public String getMartesAplica() {
        return martesAplica;
    }

    public void setMartesAplica(String martesAplica) {
        this.martesAplica = martesAplica;
    }

    public Long getMenoresCorrida() {
        return menoresCorrida;
    }

    public void setMenoresCorrida(Long menoresCorrida) {
        this.menoresCorrida = menoresCorrida;
    }

    public String getMiercolesAplica() {
        return miercolesAplica;
    }

    public void setMiercolesAplica(String miercolesAplica) {
        this.miercolesAplica = miercolesAplica;
    }

    public Long getOfertaServicioId() {
        return ofertaServicioId;
    }

    public void setOfertaServicioId(Long ofertaServicioId) {
        this.ofertaServicioId = ofertaServicioId;
    }

    public String getOfertaServicioNombre() {
        return ofertaServicioNombre;
    }

    public void setOfertaServicioNombre(String ofertaServicioNombre) {
        this.ofertaServicioNombre = ofertaServicioNombre;
    }

    public Long getProfesoresCorrida() {
        return profesoresCorrida;
    }

    public void setProfesoresCorrida(Long profesoresCorrida) {
        this.profesoresCorrida = profesoresCorrida;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public String getSabadoAplica() {
        return sabadoAplica;
    }

    public void setSabadoAplica(String sabadoAplica) {
        this.sabadoAplica = sabadoAplica;
    }

    public Long getSenectudCorrida() {
        return senectudCorrida;
    }

    public void setSenectudCorrida(Long senectudCorrida) {
        this.senectudCorrida = senectudCorrida;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public Long getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(Long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Timestamp getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    public String getViernesAplica() {
        return viernesAplica;
    }

    public void setViernesAplica(String viernesAplica) {
        this.viernesAplica = viernesAplica;
    }
    
    public Long getTramoDestinoId() {
        return tramoDestinoId;
    }

    public void setTramoDestinoId(Long tramoDestinoId) {
        this.tramoDestinoId = tramoDestinoId;
    }

    public Long getTramoOrigenId() {
        return tramoOrigenId;
    }

    public void setTramoOrigenId(Long tramoOrigenId) {
        this.tramoOrigenId = tramoOrigenId;
    }
}