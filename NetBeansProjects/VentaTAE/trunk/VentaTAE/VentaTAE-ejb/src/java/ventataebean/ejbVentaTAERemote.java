
package ventataebean;

import javax.ejb.Remote;
import java.util.Date;

/**
 * This is the business interface for ejbVentaTAE enterprise bean.
 */
@Remote
public interface ejbVentaTAERemote {
    //String ventaTaePrincipal(String Compañía, String Usuario, String Password, String Carrier, String Teléfono, String Cantidad, String Caja, String no_Usuario) throws Exception;
    String ventaTaePrincipal(String Compañía, String Usuario, String Password, String Carrier, String Teléfono, String Cantidad, String TipoVenta, String No_Usuario, String Usuario_id, String Caja, String Corte_id,String Ciudad, String Canal )throws Exception;
    
}
