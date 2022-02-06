package tmsreportes.reportes;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Clase que se encarga de generar los reportes
 * @author jmendoza
 */
@Stateless()
public class GeneradorReportes {

    //<editor-fold defaultstate="collapse" desc="CONSTANTES">
    public static final String LOGO_ER    = "img/er.gif";
    public static final String RUTA_REPORTES = "tmsreportes/reportes/";
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="VARIABLES">
    private final Map<String, String> reportesNombresJasper;
    private JasperPrint reporteJasper;
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="CONSTRUCTORES">
    public GeneradorReportes() {
        reportesNombresJasper = new HashMap<String, String>();
        reportesNombresJasper.put("TMSRPT5R", "RecoleccionesParcialesVenta.jasper");
        reportesNombresJasper.put("TMSRPTT3", "ReporteControlMorrallaTaquillas.jasper");
        reportesNombresJasper.put("TMSRPTT5", "ReporteDepositosBancariosDiarios.jasper");
        reportesNombresJasper.put("TMSRPTT6", "ReporteControlBoletosTermicos.jasper");
        reportesNombresJasper.put("TMSRPT16", "ReporteVentasPaquetesTuristicos.jasper");
        reportesNombresJasper.put("TMSRPT18", "DepositoBancarioPorTerminal.jasper");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="OPERACIONES">
    /**
     * Método que se encarga de generar un reporte usando un archivo jrxml com base.
     * @param reporte Nombre del archivo jrxml a generar
     * @param fechas Rango de fechas parámetro de todos los reportes
     * @param nombreTerminal Nombre de la terminal donde se genera el reporte
     * @param conn Instancia de la conexión a base de datos activa
     * @return true si se genera el reporte y false en cualquier otro caso
     */
    public boolean generarReporte(String reporte, String[] fechas, String nombreTerminal,
            Connection conn) {
        boolean generado = false;        
        try {            
            Map parameters = new HashMap();            
            parameters.put("P_NOMBRE_TERMINAL", "CAPU");
            parameters.put("P_FECHA_INICIAL", fechas[0]);
            parameters.put("P_FECHA_FINAL", fechas[1]);
            parameters.put("P_FECHA_HORA", new Date());
            InputStream imagen = getImage();
            parameters.put("P_PATH_IMAGEN", imagen);            
            InputStream entradaGastos = getPath(RUTA_REPORTES + reporte);
            JasperReport report = (JasperReport) JRLoader.loadObject(entradaGastos); // Esto funciona con .jasper
            //JasperCompileManager.compileReport(entradaGastos); Esto funciona con .jrxml
            reporteJasper = JasperFillManager.fillReport(report, parameters, obtenConexion());
            if(entradaGastos != null)    entradaGastos.close();
            if(imagen != null)    imagen.close();                       
            generado = reporteJasper.getPages().isEmpty() ? false : true;
         } catch (Exception e) {
            e.printStackTrace();
            generado = false;
         } finally {
             try {
                if(conn != null)
                    conn.close();
             } catch (SQLException ex) {
                ex.printStackTrace();
             }
         }
         return generado;
    }

    private Connection obtenConexion() throws NamingException {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();            
            DataSource ds = (DataSource)initContext.lookup("TMS_DB");
            conn = ds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="GETTERS">
    public JasperPrint getReporteJasper() {
        return reporteJasper;
    }    

    public InputStream getImage() {
        InputStream logo_imagen = null;
        logo_imagen = getClass().getClassLoader().getResourceAsStream(LOGO_ER);
        return logo_imagen;
    }

    public InputStream getPath(String archivo) {
        return this.getClass().getClassLoader().getResourceAsStream(archivo);
    }

    public String getPathString(String archivo) {
        return this.getClass().getClassLoader().getResource(archivo).getPath();
    }

    public String getNombreArchivoReporte(String ejecutable) {
        return reportesNombresJasper.get(ejecutable);
    }
    //</editor-fold>    
}
