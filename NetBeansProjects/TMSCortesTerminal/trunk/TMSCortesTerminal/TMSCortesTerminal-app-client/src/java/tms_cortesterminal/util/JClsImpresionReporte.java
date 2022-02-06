/*
 * JClsImpresionReporte.java
 *
 * Created on 9 de mayo de 2008, 11:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_cortesterminal.util;

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

    private Map param = null;
    private Connection cnx = null;
    private InputStream archivo = null;
    private String strRutaRelRpt;
    private JasperPrint jasJasperPrint;
    private Context CONTEXTO;
    
    /** Creates a new instance of JClsImpresionReporte */
    public JClsImpresionReporte(Context pCONTEXTO) {
        CONTEXTO = pCONTEXTO;
        jasJasperPrint = new JasperPrint();
        strRutaRelRpt = "tms_cortesterminal/reportes/";
        
        try {
            cnx = getConexion();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    
    private Connection getConexion() throws NamingException {
        Connection conn = null;
        try {
            //DataSource ds = (DataSource)CONTEXTO.lookup("TMS_DB");
            DataSource ds = (DataSource)CONTEXTO.lookup("TMS_CENTRAL_DB");
            conn = ds.getConnection();
            //conn = ds.getConnection("T4PTE", "T4PTE1");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public boolean imprimirCorte(String corteId, Timestamp fi, Timestamp ff,
            String tipoReporte, String corteDetallado, String reporte, String idCortes) {
        try{
            archivo = getClass().getClassLoader().getResourceAsStream(strRutaRelRpt+reporte);
            
            param = new HashMap();
            if(corteId!=null){
                int cI=Integer.valueOf(corteId);
                param.put("P_CORTE_ID",cI);
            }
            else param.put("P_CORTE_ID", null);
            param.put("P_FECHA_INICIO",fi);
            param.put("P_FECHA_FIN",ff);
            param.put("P_TIPO_REPORTE",tipoReporte);
            param.put("P_DESGLOSADO",corteDetallado);
            param.put("P_CORTE_ID_STR",idCortes);
            
            param.put("SUBREPORT_DIR",strRutaRelRpt);
            System.out.println("parametros "+corteId+" "+fi+" "+ff+" "+tipoReporte+" "+corteDetallado+"-"+strRutaRelRpt+reporte+"-("+idCortes+")");
            jasJasperPrint = JasperFillManager.fillReport(archivo, param, cnx);
            JasperViewer.viewReport(jasJasperPrint, false);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
