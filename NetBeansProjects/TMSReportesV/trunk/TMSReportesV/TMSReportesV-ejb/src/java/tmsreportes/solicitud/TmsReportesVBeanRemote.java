/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tmsreportes.solicitud;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import javax.ejb.Remote;
import tmsreportes.entidad.Reporte;

/**
 *
 * @author jmendoza
 */
@Remote
public interface TmsReportesVBeanRemote {
    String[] obtenerFechas(Date fechaCorte);
    List<Reporte> getReportes(long menuId);
    String getNombreTerminal();    
}
