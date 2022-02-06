/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class TransaccionViajesCliente implements Serializable{
    private BigInteger numTransaccion;
    private BigDecimal importeTransaccion;
    private String fechaTransaccion = "";
    private Date fechaViaje;
    private BigInteger noBoletosTransaccion;
    private BoletoTransaccion[] BoletosTransaccion;

public TransaccionViajesCliente(){

}

public TransaccionViajesCliente(Vector datos){
    this.numTransaccion = BigInteger.valueOf(Long.valueOf(datos.get(0).toString()));
    this.importeTransaccion = BigDecimal.valueOf(Double.valueOf(datos.get(1).toString()));
    this.fechaTransaccion = datos.get(2).toString();
    this.noBoletosTransaccion = BigInteger.valueOf(Long.valueOf(datos.get(3).toString()));
    Timestamp t = Timestamp.valueOf(datos.get(4).toString());
    this.fechaViaje = new Date(t.getTime());

}

public TransaccionViajesCliente(String pdatos){
    String[] datos = pdatos.split(",");
    this.numTransaccion = BigInteger.valueOf(Long.valueOf(datos[0]));
    this.importeTransaccion = BigDecimal.valueOf(Double.valueOf(datos[1]));
    this.fechaTransaccion = datos[2];
    this.noBoletosTransaccion = BigInteger.valueOf(Long.valueOf(datos[3]));
    Timestamp t = Timestamp.valueOf(datos[4]);
    this.fechaViaje = new Date(t.getTime());
}
    /**
     * @return the numTransaccion
     */
    public BigInteger getNumTransaccion() {
        return numTransaccion;
    }

    /**
     * @param numTransaccion the numTransaccion to set
     */
    public void setNumTransaccion(BigInteger numTransaccion) {
        this.numTransaccion = numTransaccion;
    }


    /**
     * @return the importeTransaccion
     */
    public BigDecimal getImporteTransaccion() {
        return importeTransaccion;
    }

    /**
     * @param importeTransaccion the importeTransaccion to set
     */
    public void setImporteTransaccion(BigDecimal importeTransaccion) {
        this.importeTransaccion = importeTransaccion;
    }

    /**
     * @return the fechaTransaccion
     */
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * @param fechaTransaccion the fechaTransaccion to set
     */
    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    /**
     * @return the noBoletosTransaccion
     */
    public BigInteger getNoBoletosTransaccion() {
        return noBoletosTransaccion;
    }

    /**
     * @param noBoletosTransaccion the noBoletosTransaccion to set
     */
    public void setNoBoletosTransaccion(BigInteger noBoletosTransaccion) {
        this.noBoletosTransaccion = noBoletosTransaccion;
    }

    /**
     * @return the BoletosTransaccion
     */
    public BoletoTransaccion[] getBoletosTransaccion() {
        return BoletosTransaccion;
    }

    /**
     * @param BoletosTransaccion the BoletosTransaccion to set
     */
    public void setBoletosTransaccion(BoletoTransaccion[] BoletosTransaccion) {
        this.BoletosTransaccion = BoletosTransaccion;
    }

    /**
     * @return the fechaViaje
     */
    public Date getFechaViaje() {
        return fechaViaje;
    }

    /**
     * @param fechaViaje the fechaViaje to set
     */
    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

}
