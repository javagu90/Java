
package TmsLealtadSolicitud;

import javax.ejb.Remote;


/**
 * This is the business interface for TMSLealtadFacade enterprise bean.
 */
@Remote
public interface TMSLealtadBatchFacadeRemote {
    java.util.Vector TMSConsultaPorNumeroOperacion(String numeroOperacion);

    java.util.Vector TMSOperacionesPendientes();

    //java.lang.String TMSLealtadCambiaStatus(String numeroOperacion, String status, String numeroTransaccion);

    java.lang.String TMSLealtadCambiaStatus(String numeroOperacion, String status, String numeroTransaccion, String procesoRealizado);

    java.lang.String find_Parametro(String strParametro);

    java.lang.Object getToDate();

    int UpdateBloqueados();

    java.util.Vector TMSAcumulacionesPendientes();

    java.util.Vector TMSCancelacionesPendientes();

    java.util.List<java.lang.String> REGISTRA_TRANSACCION_XM(String operacion);
    
}
