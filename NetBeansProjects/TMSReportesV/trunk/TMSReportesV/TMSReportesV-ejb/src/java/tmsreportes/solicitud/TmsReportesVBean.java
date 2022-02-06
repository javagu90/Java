/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tmsreportes.solicitud;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import tmsreportes.entidad.Reporte;

/**
 *
 * @author jmendoza
 */
@Stateless
public class TmsReportesVBean implements TmsReportesVBeanRemote {

    //<editor-fold defaultstate="collapse" desc="VARIABLES">
    @PersistenceContext(unitName="TMSReportesV-ejbPU")
    private EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="CONSTRUCTORES">
    public TmsReportesVBean() {}
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="GETTERS Y SETTERS">
     private CallableStatement getCallableStatement() {
        return callableStatement;
    }

    private void setCallableStatement(CallableStatement callableStatement) {
        this.callableStatement = callableStatement;
    }

     private PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    private void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    private Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="OPERACIONES">
    /**
     * Método que obtiene el listado de reportes a los que un perfil tiene acceso
     * @param menuId Identificador del menú desde el que se accesa a la pantalla
     * @return Una lista de los reportes a los que tiene acceso dicho perfil
     * @see Reporte
     */
    public String[] obtenerFechas(Date fechaCorte) {
        String[] rangoFechas = new String[2];
        try {            
            String query = "{ CALL OBTIENE_REPORTES_FECHAS(?,?,?) }";
            setCallableStatement(startTransaction().prepareCall(query));
            getCallableStatement().setDate(1, fechaCorte); //Opción del Procedure
            getCallableStatement().registerOutParameter(2, Types.VARCHAR);
            getCallableStatement().registerOutParameter(3, Types.VARCHAR);
            getCallableStatement().executeUpdate();  
            rangoFechas[0] = getCallableStatement().getString(2);
            rangoFechas[1] = getCallableStatement().getString(3);
        } catch (Exception ex) {
            ex.printStackTrace();//re-raise-handle
        } finally {
            releaseTransaction();
        }
        return rangoFechas;
    }

    /**
     * Método que obtiene el listado de reportes a los que un perfil tiene acceso
     * @param menuId Identificador del menú desde el que se accesa a la pantalla
     * @return Una lista de los reportes a los que tiene acceso dicho perfil
     * @see Reporte
     */
    public List<Reporte> getReportes(long menuId) {
        List<Reporte> reportes = new ArrayList<Reporte>();
        try {
              Vector result = null;              
              Query q = em.createNativeQuery("SELECT rp.* FROM TMS_PERFILES_TBL p, tms_reportes_perfil_v rp"
                + " WHERE p.menu_id in (" + menuId + ") AND rp.perfil_id = p.perfil_id");              
              result = (Vector) q.getResultList();
              Reporte reporte = null;
              for(int i = 0; i < result.size(); i++){
                Object obj = result.get(i);
                Vector objectArray = (Vector) obj;
                reporte = new Reporte();
                reporte.setReporteId(Long.parseLong(objectArray.get(0).toString()));
                reporte.setReporteCodigo(objectArray.get(1).toString());
                reporte.setNombreReporte(objectArray.get(2).toString());
                reporte.setDescripcionReporte(objectArray.get(3).toString());
                reporte.setReporteEjecutable(objectArray.get(4).toString());
                reportes.add(reporte);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return reportes;
        }
    }

    /**
     * Método que obtiene el nombre de la terminal donde se logueo el usuario
     * @return Un String con el nombre de la terminal
     */
    public String getNombreTerminal() {
        String nombreTerminal = "";
        try {            
            Query q = em.createNativeQuery("SELECT nombre_terminal FROM TMS_BASE_DATOS_"
                    + "CONFIG_TBL WHERE esquema_propio = 'S'");
            nombreTerminal = q.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return nombreTerminal;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UTILITIES">
    /**
     * Método que libera la transacción en uso
     */
    private void releaseTransaction() {
        if (getConnection()!= null) {
            try {
                if (getCallableStatement() != null) {
                    getCallableStatement().close();
                }
                getConnection().close();
            } catch (SQLException ex) {
                System.out.println("ERROR AL LIBERAR LA SESIÓN DE LA BASE DE DATOS/n" + ex);
            }
        }
    }

    /**
     * Método que comienza una transacción
     * @return La conexión activa resultante de la transacción
     */    
    private Connection startTransaction() {
        try {
            setConnection(dataSource.getConnection());
            return getConnection();
        }  catch(SQLException se) {
            se.printStackTrace();
            System.out.println("ERROR AL INTENTAR CONECTARSE A LA BASE DE DATOS /n" + se);
            return getConnection();
        }
    }
    //</editor-fold>

}
