/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.estrellaroja.TMSFacturaElectronicaWS;

/**
 *
 * @author jmendoza
 */
public class TicketEstacionamiento {
    private long numTicket;
    private String fechaEntrada;
    private String horaEntrada;
    private String fechaSalida;
    private String horaSalida;
    private String importe;
    private String impuestos;
    private String total;
    private String servicio;
    private int diasTranscurridos;
    private String numRecibo;

    /**
     * @return the numTicket
     */
    public long getNumTicket() {
        return numTicket;
    }

    /**
     * @param numTicket the numTicket to set
     */
    public void setNumTicket(long numTicket) {
        this.numTicket = numTicket;
    }

    /**
     * @return the fechaEntrada
     */
    public String getFechaEntrada() {
        if("".equals(fechaEntrada) || fechaEntrada == null)
            return "";
        else
            return fechaEntrada.replace('-', '/').substring(0, 10);
    }

    /**
     * @param fechaEntrada the fechaEntrada to set
     */
    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * @return the horaEntrada
     */
    public String getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * @param horaEntrada the horaEntrada to set
     */
    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * @return the fechaSalida
     */
    public String getFechaSalida() {
         if("".equals(fechaSalida) || fechaSalida == null)
            return "";
        else
            return fechaSalida.replace('-', '/').substring(0, 10);
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the importe
     */
    public String getImporte() {
        return importe;
    }

    /**
     * @return the horaSalida
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(String importe) {
        this.importe = importe;
    }

    /**
     * @return the impuestos
     */
    public String getImpuestos() {
        return impuestos;
    }

    /**
     * @param impuestos the impuestos to set
     */
    public void setImpuestos(String impuestos) {
        this.impuestos = impuestos;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the origen
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * @param origen the origen to set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the fechaFacturacionPermitida
     */
    public int getDiasTranscurridos() {
        return diasTranscurridos;
    }

    /**
     * @param fechaFacturacionPermitida the fechaFacturacionPermitida to set
     */
    public void setDiasTranscurridos(int diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }

    /**
     * @return the numRecibo
     */
    public String getNumRecibo() {
        return numRecibo;
    }

    /**
     * @param numRecibo the numRecibo to set
     */
    public void setNumRecibo(String numRecibo) {
        this.numRecibo = numRecibo;
    }

    @Override
    public String toString() {
        return String.valueOf(getNumTicket());
    }
}
