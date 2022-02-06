/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente.ejb;

import javax.ejb.Remote;
import cliente.pojo.Cliente;
import cliente.pojo.Status;

/**
 *
 * @author jildefonso
 */
@Remote
public interface ClienteBeanRemote {

    /**
     * Agrega un nuevo cliente
     * @param cliente Los datos del cliente a agregar
     * @return El status de la operación
     */
    public Status agregarCliente(Cliente cliente);

    /**
     * Actualiza un cliente existente
     * @param cliente Los nuevos datos del cliente a actualizar
     * @return El status de la operación
     */
    public Status actualizarCliente(Cliente cliente);

    /**
     * Obtiene una instancia del cliente y el su status de la operación
     * @param email Email a buscar
     * @param contrasenia Contraseña para verificar el email
     * @return [0] El status de la operación<br/>[1] La instancia cliente
     */
    public Object[] getClienteByEmail(String email, String contrasenia);

    /**
     * Reestablece la contraseña y la manda al correo si es que existe
     * @param email El correo a verificar y enviar la nueva contraseña
     * @return [0] El status de la operación<br/>[1] La nueva contraseña
     */
    public Object[] restablecerContrasenia(String email);

    /**
     * Valida la contraseña del cliente antes de ser cifrada
     * @param email la contraseña a verificar
     * @param contrasenia La contraseña proporcionada
     * @return True: Si la contraseña corresponde a la contraseña del usuario<br/>False: Si la contraseña es incorrecta
     */
    public boolean validarContraseniaByEmail(String email, String contrasenia);

    /**
     * Obtiene la lista de clientes administrados por un cliente
     * @param id Id del cliente a revisar
     * @param contrasenia Contraseña para el usuario
     * @return [0] El status de la operación<br/>[1] Lista de usuarios intimos administrados por el cliente
     */
    public Object[] getClientesAdministrados(String id, String contrasenia);

    
}
