
package wsc.solicitudes;

import java.util.Vector;
import javax.ejb.Remote;
import wsc.entidades.Cliente;
//import wsc.excepciones.AgenteNoCreadoException;


/**
 * This is the business interface for NewSession enterprise bean.
 */
@Remote
public interface peticionesFacadeRemote {
    int getvalidaCliente(String email, String psw);

    Vector getCliente(String email, String ppsw);

    int agregarCliente(Cliente cliente);

    int modificacionCliente(Cliente cliente);

    java.util.Vector getreservacionesCliente(long clienteId, String fechaInicial, String fechaFinal);

    java.util.Vector getcomprasCliente(long clienteId, String fechaInicial, String fechaFinal);

    java.util.Vector buscaCliente(String email);

    java.util.Vector getCliente2(String email);

    java.lang.String getContraseniaNueva();

    java.util.Vector getClientesAdministrados(long administradorId);

 //   java.util.Vector getCliente3(String email);

    java.util.Vector getValidaAdministradorAutoriza(String email, long adminId);

    java.util.Vector getCliente4(String email, String ppsw);

    int getvalidaCliente2(String email, String ppsw);

    int agregarAgente(Cliente cliente);// throws AgenteNoCreadoException;

    String getFecha();

    public java.util.Vector getCliente3(java.lang.String email, long adminId);
}
