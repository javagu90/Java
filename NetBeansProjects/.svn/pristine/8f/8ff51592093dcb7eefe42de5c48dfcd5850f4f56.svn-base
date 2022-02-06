/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente.ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import cliente.pojo.Cliente;
import cliente.pojo.Status;

/**
 *
 * @author jildefonso
 */
@Stateless
public class ClienteBean implements ClienteBeanRemote {

    private static final String EMAIL_FROM = "jildefonso@estrellaroja.com.mx";
    private static final String SMTP_USER = "jildefonso";
    private static final String SMTP_PASSWORD = "123456";

    //<editor-fold defaultstate="collapsed" desc=" Atributos ">
    @PersistenceContext(unitName="TMS_CENTRAL_DB")
    private EntityManager em;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Métodos ">
    //<editor-fold defaultstate="collapsed" desc=" Seguridad ">
    /***
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private String toHexadecimal(byte[] digest) {
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }

    /***
     * Encripta un mensaje de texto mediante el algoritmo MD5
     * @param message texto a encriptar
     * @return mensaje encriptado
     */
    private String cifrar(String message) throws NoSuchAlgorithmException {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(buffer);
        digest = messageDigest.digest();
        return toHexadecimal(digest);
    }

    /**
     * Valida la contraseña del cliente antes de ser cifrada
     * @param idcliente El identificador del cliente a verificar
     * @param contrasenia La contraseña proporcionada
     * @return True: Si la contraseña corresponde a la contraseña del usuario<br/>False: Si la contraseña es incorrecta
     */
    private boolean validarContrasenia(String idcliente, String contrasenia) throws NoSuchAlgorithmException {
        String consulta = "select cl.contrasenia from TMS_clientes_TBL cl where cl.cliente_id = '" + idcliente + "'";
        List result = em.createNativeQuery(consulta ).getResultList();
        if(result.isEmpty()) return false;
        return ((List)result.get(0)).get(0).toString().equals(cifrar(contrasenia));
    }

    /**
     * Valida la contraseña del cliente antes de ser cifrada
     * @param email la contraseña a verificar
     * @param contrasenia La contraseña proporcionada
     * @return True: Si la contraseña corresponde a la contraseña del usuario<br/>False: Si la contraseña es incorrecta
     */
    public boolean validarContraseniaByEmail(String email, String contrasenia) {
        String consulta = "select cl.contrasenia from TMS_clientes_TBL cl where cl.email = '" + email + "'";
        List result = em.createNativeQuery(consulta ).getResultList();
        if(result.isEmpty()) return false;
        try {
            return ((List)result.get(0)).get(0).toString().equals(cifrar(contrasenia));
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Buscar ">
    /**
     * Busca cliente por medio del Email
     * @param email El email a buscar
     * @return El id del cliente(cliente_id)<br/>-1 Si el cliente no se encuentra
     */
    private Long getIdclienteByEmail(String email) {
        String consulta = "select cl.cliente_id from TMS_clientes_TBL cl where cl.email = '" + email + "'";
        List result = em.createNativeQuery(consulta ).getResultList();
        if(result.isEmpty()) return -1L;
        return Long.parseLong(result.get(0).toString().replace("[", "").replace("]", ""));
    }

    /**
     * Verifica la existencia de un cliente por medio del identificador
     * @param idcliente El identificar a buscar
     * @return true: Si el cliente existe<br/>false: Si el cliente no existe
     */
    private boolean existeClienteById(String idcliente) {
        String consulta = "select cl.cliente_id from TMS_clientes_TBL cl where cl.cliente_id = '" + idcliente + "'";
        List result = em.createNativeQuery(consulta ).getResultList();
        return !result.isEmpty();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Agregar ">
    /**
     * Agrega un nuevo cliente
     * @param cliente Los datos del cliente a agregar
     * @return El status de la operación
     */
    public Status agregarCliente(Cliente cliente) {
        List usuarioWeb = em.createNativeQuery("select us.USUARIO_ID from TMS_USUARIOS_TBL us where us.USUARIO_NOMBRE = 'USUARIO WEB'").getResultList();
        if(usuarioWeb.isEmpty()) return new Status(Status.NO_EXISTE_REGISTRO, "No se encontro dado de alta el Usuario Web. favor de contactar al Administrador del Sistema!");
        long idUsuarioWeb = Long.parseLong(((List)usuarioWeb.get(0)).get(0).toString());
        Status status = cliente.validaCliente();
        if(status.getCodigo() == Status.OK) {
            try {
                if(getIdclienteByEmail(cliente.getEmail()) != -1) {
                    status.setCodigo(Status.REGISTRO_YA_EXISTE);
                    status.setDetalles("Ya existe un cliente con el email '" + cliente.getEmail() + "'");
                }
                else {
                    cliente.setContrasenia(cifrar(cliente.getContrasenia()));
                    String sql = cliente.createSQLInsert(idUsuarioWeb);
                    System.out.println("-----------------------");
                    System.out.println(sql);
                    System.out.println("-----------------------");
                    if(em.createNativeQuery(sql).executeUpdate() == 0) {
                        status.setCodigo(Status.ERROR_COMUNICARSE_BD);
                        status.setDetalles("No hay registros afectados");
                    }
                }
            } catch (Exception ex) {
                status.setCodigo(Status.ERROR_COMUNICARSE_BD);
                status.setDetalles("Error de ejecución");
                ex.printStackTrace();
            }
        }
        return status;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Modificar ">
    /**
     * Actualiza un cliente existente
     * @param cliente Los nuevos datos del cliente a actualizar
     * @return El status de la operación
     */
    public Status actualizarCliente(Cliente cliente) {
        List usuarioWeb = em.createNativeQuery("select us.USUARIO_ID from TMS_USUARIOS_TBL us where us.USUARIO_NOMBRE = 'USUARIO WEB'").getResultList();
        if(usuarioWeb.isEmpty()) return new Status(Status.NO_EXISTE_REGISTRO, "No se encontro dado de alta el Usuario Web. favor de contactar al Administrador del Sistema!");
        long idUsuarioWeb = Long.parseLong(((List)usuarioWeb.get(0)).get(0).toString());
        Status status = cliente.validaCliente();
        if(status.getCodigo() == Status.OK) {
            try {
                if(!existeClienteById(cliente.getId())) {
                    status.setCodigo(Status.NO_EXISTE_REGISTRO);
                    status.setDetalles("No existe el cliente con el identificador '" + cliente.getId() + "'");
                }
                else if(!validarContrasenia(cliente.getId(), cliente.getContrasenia())) {
                    status.setCodigo(Status.CONTRASENIA_INCORRECTA);
                    status.setDetalles("La contraseña proporcionada para el usuario es incorrecta'");
                }
                else {
                    cliente.setContrasenia(cifrar(cliente.getContrasenia()));
                    String sql = cliente.createSQLUpdate(idUsuarioWeb);
                    System.out.println("-----------------------");
                    System.out.println(sql);
                    System.out.println("-----------------------");
                    if(em.createNativeQuery(sql).executeUpdate() == 0) {
                        status.setCodigo(Status.ERROR_COMUNICARSE_BD);
                        status.setDetalles("No hay registros afectados");
                    }
                }
            } catch(Exception ex) {
                status.setCodigo(Status.ERROR_COMUNICARSE_BD);
                status.setDetalles("Error de ejecución");
                ex.printStackTrace();
            }
        }
        return status;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Consultar ">
    /**
     * Obtiene una instancia del cliente y el su status de la operación
     * @param email Email a buscar
     * @param contrasenia Contraseña para verificar el email
     * @return [0] El status de la operación<br/>[1] La instancia cliente
     */
    public Object[] getClienteByEmail(String email, String contrasenia) {
        Object[] response = new Object[2];
        try {
            String sql = Cliente.createSQLgetByEmail(email);
            System.out.println("-----------------------");
            System.out.println(sql);
            System.out.println("-----------------------");
            List result = em.createNativeQuery(sql).getResultList();
            if(result.isEmpty()) {
                response[0] = new Status(Status.NO_EXISTE_REGISTRO, "No existe el usuario con el email '" + email + "'");
                response[1] = new Cliente();
            } else {
                Cliente cliente = Cliente.createFromSQLRow((List)result.get(0));
                if(!validarContrasenia(cliente.getId(), contrasenia)) {
                    response[0] = new Status(Status.CONTRASENIA_INCORRECTA, "La contraseña es incorrecta para el usuario con el email '" + email + "'");
                    response[1] = new Cliente();
                }
                else {
                    cliente.setContrasenia("");
                    response[0] = new Status(Status.OK);
                    response[1] = cliente;
                }
            }
        } catch(Exception ex) {
            response[0] = new Status(Status.ERROR_COMUNICARSE_BD, "Error de ejecución");
            response[1] = new Cliente();
            ex.printStackTrace();
        }
        return response;
    }

    /**
     * Obtiene la lista de clientes intimos administrados por un cliente
     * @param id Id del cliente a revisar
     * @param contrasenia Contraseña para el usuario
     * @return [0] El status de la operación<br/>[1] Lista de usuarios intimos administrados por el cliente
     */
    public Object[] getClientesAdministrados(String id, String password) {
        Object[] response = new Object[2];
        try {
            if(!validarContrasenia(id, password)) {
                response[0] = new Status(Status.CONTRASENIA_INCORRECTA, "La contraseña no corresponde al usuario indicado");
                response[1] = "";
            }
            else {
                String sql = Cliente.createSQLgetByAdmin(Long.parseLong(id));
                System.out.println("-----------------------");
                System.out.println(sql);
                System.out.println("-----------------------");
                List result = em.createNativeQuery(sql).getResultList();
                ArrayList<Cliente> clientes = new ArrayList<Cliente>();
                for(Object i : result)
                    clientes.add(Cliente.createFromSQLRow((List)i));
                response = new Object[clientes.size() + 1];
                response[0] = new Status();
                for(int i = 0; i < clientes.size(); i++)
                    response[i + 1] = clientes.get(i);
            }
        } catch(Exception ex) {
            response[0] = new Status(Status.ERROR_COMUNICARSE_BD, "Error de ejecución");
            response[1] = "";
            ex.printStackTrace();
        }
        return response;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Restablecer contraseña ">
    /**
     * Crea una contraseña generada al azar
     * @return Una nueva contraseña
     * @throws NoSuchAlgorithmException En caso de algun error en la operación
     */
    private String crearContrasenia() throws NoSuchAlgorithmException {
        StringBuilder cadena = new StringBuilder();
        for(int i = 0; i <= Math.random() * 10; i++) cadena.append(Math.random());
        return cifrar(cadena.toString()).substring(0, 5);
    }

    /**
     * Reestablece la contraseña y la manda al correo si es que existe
     * @param email El correo a verificar y enviar la nueva contraseña
     * @return [0] El status de la operación<br/>[1] La nueva contraseña
     */
    public Object[] restablecerContrasenia(String email) {
        Object[] response = new Object[2];
        try {
            if(getIdclienteByEmail(email) == -1) {
                response[0] = new Status(Status.NO_EXISTE_REGISTRO, "No existen clientes registrados con el email '" + email + "'");
                response[1] = "";
            } else {
                String nuevaContrasenia = crearContrasenia();
                if(em.createNativeQuery("update TMS_CLIENTES_TBL set contrasenia = '" + cifrar(nuevaContrasenia) + "' WHERE email = '" + email + "'").executeUpdate() == 0) {
                    response[0] = new Status(Status.NO_EXISTE_REGISTRO, "No existe el cliente con el correo: '" + email + "'");
                    response[1] = "";
                }
                else {
                    response[0] = new Status(Status.OK);
                    response[1] = nuevaContrasenia;
                    Properties props = new Properties();
                    props.setProperty("mail.smtp.host", "mail.estrellaroja.com.mx");
                    props.setProperty("mail.smtp.port","25");
                    props.setProperty("mail.smtp.user", SMTP_USER);
                    Session session = Session.getDefaultInstance(props);
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(EMAIL_FROM));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                    message.setSubject("Cambio de contraseña de cliente");
                    message.setText("Se ha restablecido su contraseña a: " + nuevaContrasenia + "\n\nGracias.");
                    Transport t = session.getTransport("smtp");
                    t.connect(SMTP_USER, SMTP_PASSWORD);
                    t.sendMessage(message,message.getAllRecipients());
                    t.close();
                }
            }
        } catch(Exception ex) {
            response[0] = new Status(Status.ERROR_COMUNICARSE_BD, "Error de ejecución");
            response[1] = "";
        }
        return response;
    }
    //</editor-fold>
    //</editor-fold>

}
