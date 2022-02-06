/*
 * JClsImpresionReporte.java
 *
 * Created on 9 de mayo de 2008, 11:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package DialogosExeRrp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ocruz
 */
public class JClsImpresionReporte {
    private Connection cnx = null;
    private InputStream archivo = null;
    private JasperPrint jasJasperPrint;
    private Context CONTEXTO;
    
    /** Creates a new instance of JClsImpresionReporte */
    public JClsImpresionReporte(Context pCONTEXTO) {
        CONTEXTO = pCONTEXTO;
        jasJasperPrint = new JasperPrint();
        try {
            cnx = getConexion();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    
    private Connection getConexion() throws NamingException {
        Connection conn = null;
        try {
            DataSource ds = (DataSource)CONTEXTO.lookup("TMS_CENTRAL_DB");
            conn = ds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public boolean imprimirReporte(HashMap param, String strReporte) {
        try{
            archivo = getClass().getClassLoader().getResourceAsStream(strReporte);
            
            jasJasperPrint = JasperFillManager.fillReport(archivo, param, cnx);
            JasperViewer.viewReport(jasJasperPrint, false);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
