/*
 * JClsUtil.java
 *
 * Created on 19 de mayo de 2008, 11:56 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package DialogosExeRrp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ocruz
 */
public class JClsUtil {
    String MesAnho;
    String Anho;
    /** Creates a new instance of JClsUtil */
    public JClsUtil() {
        Date x = new Date();
        MesAnho = new SimpleDateFormat("MM").format(x.getTime());
        Anho = new SimpleDateFormat("yyyy").format(x.getTime());
    }
    
    public String AutoCompletaFecha(String fecha){
        if(fecha.length()==3) fecha = fecha+MesAnho;
        if(fecha.length()==6) fecha = fecha+Anho;
        
        if(fecha.length()>0 && fecha.length()<10) return "";
        return fecha;
    }
}
