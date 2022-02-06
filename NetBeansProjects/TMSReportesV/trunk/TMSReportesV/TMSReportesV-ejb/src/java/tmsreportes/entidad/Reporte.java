package tmsreportes.entidad;

import java.io.Serializable;

/**
 * Clase que representa un objeto reporte de la tabla de base de datos
 * @author jmendoza
 */
public class Reporte implements Serializable {
    //<editor-fold defaultstate="collapse" desc="VARIABLES">
    private long reporteId;
    private String reporteCodigo;
    private String nombreReporte;
    private String descripcionReporte;
    private String reporteEjecutable;
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="GETTERS Y SETTERS">
    public String getDescripcionReporte() {
        return descripcionReporte;
    }

    public void setDescripcionReporte(String descripcionReporte) {
        this.descripcionReporte = descripcionReporte;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getReporteCodigo() {
        return reporteCodigo;
    }

    public void setReporteCodigo(String reporteCodigo) {
        this.reporteCodigo = reporteCodigo;
    }

    public String getReporteEjecutable() {
        return reporteEjecutable;
    }

    public void setReporteEjecutable(String reporteEjecutable) {
        this.reporteEjecutable = reporteEjecutable;
    }

    public long getReporteId() {
        return reporteId;
    }

    public void setReporteId(long reporteId) {
        this.reporteId = reporteId;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="MÉTODOS">
    @Override
    public String toString() {
        return getNombreReporte();
    }
    //</editor-fold>
}
