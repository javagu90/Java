/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente.webservice;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ejb.Stateless;
import cliente.ejb.ClienteBeanRemote;
import cliente.pojo.Cliente;
import cliente.pojo.Status;

/**
 *
 * @author jildefonso
 */
@WebService()
@Stateless()
public class ClienteWS {

    @EJB
    private ClienteBeanRemote ejbRef;

    /**
     * Agrega un nuevo cliente
     * @param cliente El cliente a agregar
     * @return El status de la operación
     */
    @WebMethod(operationName = "agregarCliente")
    public Status agregarCliente(@WebParam(name = "cliente") Cliente cliente) {
        return ejbRef.agregarCliente(cliente);
    }

    /**
     * Actualiza un cliente existente
     * @param cliente El cliente a agregar
     * @return El status de la operación
     */
    @WebMethod(operationName = "modificarCliente")
    public Status modificarCliente(@WebParam(name = "cliente") Cliente cliente) {
        return ejbRef.actualizarCliente(cliente);
    }

    /**
     * Obtiene una instancia del cliente y el su status de la operación
     * @param email Email a buscar
     * @param contrasenia Contraseña para verificar el email
     * @return [0] El status de la operación<br/>[1] La instancia cliente
     */
    @WebMethod(operationName = "getClienteByEmail")
    public Object[] getClienteByEmail(@WebParam(name = "email") String email, @WebParam(name = "contrasenia") String contrasenia) {
        return ejbRef.getClienteByEmail(email, contrasenia);
    }

    /**
     * Reestablece la contraseña y la manda al correo si es que existe
     * @param email El correo a verificar y enviar la nueva contraseña
     * @return [0] El status de la operación<br/>[1] La nueva contraseña
     */
    @WebMethod(operationName = "restablecerContrasenia")
    public Object[] restablecerContrasenia(@WebParam(name = "email") String email) {
        return ejbRef.restablecerContrasenia(email);
    }

    /**
     * Valida la contraseña del cliente antes de ser cifrada
     * @param email la contraseña a verificar
     * @param contrasenia La contraseña proporcionada
     * @return True: Si la contraseña corresponde a la contraseña del usuario<br/>False: Si la contraseña es incorrecta
     */
    @WebMethod(operationName = "validarContraseniaByEmail")
    public boolean validarContraseniaByEmail(@WebParam(name = "email") String email, @WebParam(name = "contrasenia") String contrasenia) {
        return ejbRef.validarContraseniaByEmail(email, contrasenia);
    }

    /**
     * Obtiene la lista de socios intimos administrados por un cliente
     * @param id Id del cliente a revisar
     * @param contrasenia Contraseña para el usuario
     * @return [0] El status de la operación<br/>[1] Lista de usuarios intimos administrados por el cliente
     */
    @WebMethod(operationName = "getClientesAdministrados")
    public Object[] getClientesAdministrados(@WebParam(name = "id") String id, @WebParam(name = "contrasenia") String contrasenia) {
        return ejbRef.getClientesAdministrados(id, contrasenia);
    }

}