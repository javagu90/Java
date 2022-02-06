/*
 * Ruta.java
 *
 * Created on 10 de diciembre de 2007, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package WS_CONTROL.paquer.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase Entidad Ruta
 *
 * @author galbarran
 */
@Entity
@Table(name = "PAQ_RUTAS_TBL")
public class Ruta implements Serializable {
    
    @Id
    @Column(name = "RUTA_ID", nullable = false)
    private BigDecimal rutaId;
    
    @Column(name = "RUTA_KILOMETROS")
    private float distancia;
    
    @Column(name = "RUTA_ORIGEN")
    private long origen;
    
    @Column(name = "RUTA_DESTINO")
    private long destino;
    
    @Column(name= "RUTA_ALIANZA")
    private int alianza;
    
    @Transient
    private String claveIATA;

    private String rutaOrigenCodigo;
    private String rutaDestinoCodigo;

    /** Creates a new instance of Ruta */
    public Ruta() {
    }

    public Ruta(String[] campos) {
        this.setRutaId(new BigDecimal(campos[0]));
        this.setOrigen(Long.valueOf(campos[1]));
        this.setDestino(Long.valueOf(campos[2]));
        this.setDistancia(Float.valueOf(campos[3]));
        this.setAlianza(Integer.valueOf(campos[4]));
        this.setRutaOrigenCodigo(campos[5]);
        this.setRutaDestinoCodigo(campos[6]);
        this.setClaveIATA(campos[7]);
    }
    /**
     * Crear una nueva instancia de  Ruta con los valores especificados.
     * @param rutaId el rutaId del Ruta
     */
    public Ruta(BigDecimal rutaId) {
        this.rutaId = rutaId;
    }
    
    /**
     * Obtener el rutaId de este Ruta.
     * @return el rutaId
     */
    public BigDecimal getRutaId() {
        return this.rutaId;
    }
    
    /**
     * Define el rutaId de este Ruta al valor especificado.
     * @param rutaId el nuevorutaId
     */
    public void setRutaId(BigDecimal rutaId) {
        this.rutaId = rutaId;
    }
    
    /**
     * Obtener el distancia de este Ruta.
     * @return el distancia
     */
    public float getDistancia() {
        return this.distancia;
    }
    
    /**
     * Define el distancia de este Ruta al valor especificado.
     * @param distancia el nuevodistancia
     */
    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    /**
     * Returna un valor de codigo hash para el objeto.  Esta implementacion computa
     * un valor de codigo hash basado sobre los campos id en este objeto.
     * @return  un valor de codigo hash para este objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.rutaId != null ? this.rutaId.hashCode() : 0);
        return hash;
    }
    
    /**
     * Determina si otro objeto es igual a este Ruta.  El resultado es
     * <code>verdadero</code> so y solo so el argumento no es nulo y es unRuta objeto que tiene  valores idonticos en el campo id tal como este objeto.
     * @param objeto que referencia el objeto con el cual comparar @return <code>verdadero</code> si este objeto es idontico al argumento; de otra manera sero<code>falso</code>.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta)object;
        if (this.rutaId != other.rutaId && (this.rutaId == null || !this.rutaId.equals(other.rutaId))) return false;
        return true;
    }
    
    /**
     * Returna un representacion de cadena del objeto.  Esta implementacion construye esta representacion basada sobre los campos id.
     * @return una  representacion de cadena del objeto.
     */
    @Override
    public String toString() {
        return "dominio.Ruta[rutaId=" + rutaId + "]";
    }
    
    public int getAlianza() {
        return alianza;
    }
    
    public void setAlianza(int alianza) {
        this.alianza = alianza;
    }
    
    public String getClaveIATA() {
        return claveIATA;
    }
    
    public void setClaveIATA(String claveIATA) {
        this.claveIATA = claveIATA;
    }


    /**
     * @return the rutaOrigenCodigo
     */
    public String getRutaOrigenCodigo() {
        return rutaOrigenCodigo;
    }

    /**
     * @param rutaOrigenCodigo the rutaOrigenCodigo to set
     */
    public void setRutaOrigenCodigo(String rutaOrigenCodigo) {
        this.rutaOrigenCodigo = rutaOrigenCodigo;
    }

    /**
     * @return the rutaDestinoCodigo
     */
    public String getRutaDestinoCodigo() {
        return rutaDestinoCodigo;
    }

    /**
     * @param rutaDestinoCodigo the rutaDestinoCodigo to set
     */
    public void setRutaDestinoCodigo(String rutaDestinoCodigo) {
        this.rutaDestinoCodigo = rutaDestinoCodigo;
    }

    /**
     * @return the origen
     */
    public long getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(long origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public long getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(long destino) {
        this.destino = destino;
    }




    
}
