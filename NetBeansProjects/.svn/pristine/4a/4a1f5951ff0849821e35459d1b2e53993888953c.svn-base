package wscs;

import java.util.Vector;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import wsc.entidades.Cliente;
import wsc.entidades.compra;
import wsc.entidades.reservacion;
import wsc.excepciones.AgenteNoCreadoException;
import wsc.solicitudes.peticionesFacadeRemote;

/**
 * This is the implementation bean class for the ERWSClientes web service.
 * Created 22/09/2008 02:29:47 PM
 * @author vgonzalez
 */

@WebService
public class ERWSClientesImpl {
    
    // Enter web service operations here. (Popup menu: Web Service->Add Operation)
    @EJB
    private peticionesFacadeRemote peticiones;

    /**
     * Web service operation
     */
    @WebMethod
    public validaClienteResponse validarCliente(@WebParam(name = "clientevalidorequest") validaClienteRequest clientevalidorequest){  
        // TODO implement operation 
        validaClienteResponse response = new validaClienteResponse();
        String uid = clientevalidorequest.getUid();
        String email = clientevalidorequest.getEmail();
        String contrasenia = clientevalidorequest.getContrasenia();
        response.setUid(uid);
        if(uid.equals("") || uid==null)
        {
            response.setSuccess(false);
            response.setErrorCode(1);
            response.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return response;
        }
        
        if(email.equals("") || contrasenia.equals("") || email == null || contrasenia == null)
        {
            String campo = "";
            if(email.equals("") || email == null) campo = "Email";
            if(contrasenia.equals("")|| contrasenia == null) campo = campo + ",Contraseña";
            response.setSuccess(false);
            response.setErrorCode(2);
            response.setErrorMsg("El campo(s) son requeridos : "+campo);
            return response;
        }

        if(peticiones.getvalidaCliente(email, contrasenia)<0)
        {
            response.setSuccess(false);
            response.setErrorCode(3);
            response.setErrorMsg("cliente o contraseña invalidos");
            return response;
        }

        Vector vcid = (Vector)peticiones.buscaCliente(email);
        Vector cid = (Vector) vcid.get(0);
        
        response.setClienteId(Long.valueOf(cid.get(0).toString()));
        response.setSuccess(true);
        response.setErrorCode(-1);
        response.setErrorMsg("El cliente es valido");
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public consultaClienteResponse consultarCliente(@WebParam(name = "request") consultaClienteRequest request) {
     consultaClienteResponse responseCC = new consultaClienteResponse();
        String uid = request.getUid();
        String email = request.getEmail();
        String contrasenia =request.getContrasenia();
        responseCC.setUid(uid);
        System.out.println("Entra a consulta cliente...");
        if(uid.equals("") || uid==null)
        {
            responseCC.setSuccess(false);
            responseCC.setErrorCode(1);
            responseCC.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return responseCC;
        }
        
        if(email.equals("") || contrasenia.equals("") || email == null || contrasenia == null)
        {
          System.out.println("Entra a consulta a ver campos vacios...");
            String campo = "";
            if(email.equals("") || email == null) campo = "Email";
            if(contrasenia.equals("") || contrasenia == null ) campo = campo + ",Contraseña";
            responseCC.setSuccess(false);
            responseCC.setErrorCode(2);
            responseCC.setErrorMsg("El campo(s) son requeridos : "+campo);
            return responseCC;
        }
        Vector vvcliente =  peticiones.getCliente4(email, contrasenia);
        if(vvcliente.size()==0)
        {
           System.out.println("Entra a buscar el cliente");
            responseCC.setSuccess(false);
            responseCC.setErrorCode(3);
            responseCC.setErrorMsg("cliente o contraseña invalidos. ");
            return responseCC;
        }
        System.out.println("paso hasta cliente valido...");
        Vector vcliente = (Vector) vvcliente.get(0);
        Cliente cliente = new Cliente(vcliente);
        responseCC.setCliente(cliente);
        responseCC.setSuccess(true);
        responseCC.setErrorCode(-1);
        responseCC.setErrorMsg("El cliente es valido");
        return responseCC;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public altaClienteResponse agregarCliente(@WebParam(name = "request") altaClienteRequest request) {
        altaClienteResponse response = new altaClienteResponse();
        String uid = request.getUid();
        Cliente cliente = request.getCliente();
        response.setUid(uid);
        if(uid.equals("") || uid==null)
        {
            response.setSuccess(false);
            response.setErrorCode(1);
            response.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return response;
        }
        if(cliente==null)
        {
            response.setSuccess(false);
            response.setErrorCode(2);
            response.setErrorMsg("El registro del cliente no puede estar vacio.");
            return response;
        }
        
        if(cliente.getnombre().equals("") || cliente.getCodigoPostal().equals("") || cliente.getEmail().equals("") || cliente.getContrasenia().equals("") || cliente.getPais().equals("") 
          || cliente.getnombre() == null || cliente.getCodigoPostal() == null || cliente.getEmail() == null || cliente.getContrasenia() == null || cliente.getPais() == null)
        {
            String campo = "";
            if(cliente.getnombre().equals("") || cliente.getnombre() == null) campo = campo + "Nombre";
            if(cliente.getCodigoPostal().equals("") || cliente.getCodigoPostal() == null) campo = campo + ",CodigoPostal";
            if(cliente.getEmail().equals("") || cliente.getEmail() == null) campo = campo + ",Email";
            if(cliente.getContrasenia().equals("") || cliente.getContrasenia() == null) campo = campo + ",Contraseña";
            if(cliente.getPais().equals("") || cliente.getPais() == null) campo = campo + ",Pais";
            response.setSuccess(false);
            response.setErrorCode(3);
            response.setErrorMsg("El campo(s) son requeridos : "+campo);
            return response;
        }
        
        if(peticiones.buscaCliente(cliente.getEmail()).size()>0)
        {
            response.setSuccess(false);
            response.setErrorCode(4);
            response.setErrorMsg("Existe un cliente registrado con el email "+cliente.getEmail());
            return response;
        }
        
        int resp = peticiones.agregarCliente(cliente);
        if(resp==-1)
        {
            response.setSuccess(false);
            response.setErrorCode(5);
            response.setErrorMsg("No se encontro dado de alta el Usuario Web. favor de contactar al Administrador del Sistema!");
            return response;            
        }
        if(resp==1)
        {
            response.setSuccess(false);
            response.setErrorCode(6);
            response.setErrorMsg("En este momento no se puede dar de alta el cliente, intente mas tarde.");
            return response;            
        }        

         // Agregar en tabla de agente solo si es AGENTE
          if (cliente.getClienteRelacionadoId() > 0)
          {
              try
              {
                 resp = peticiones.agregarAgente(cliente);
                 System.out.println("Respuesta de peticiones.agregarAgente:"+resp);
              }catch(AgenteNoCreadoException agv){
                  agv.printStackTrace();
              }
          }

        response.setSuccess(true);
            response.setErrorCode(-1);
            response.setErrorMsg("Cliente "+cliente.getnombre()+" dado de alta");
            return response;
    }
    
  
    
   /**
     * Web service operation
     */
    @WebMethod
    public modificarClienteResponse modificacionCliente(@WebParam(name = "request") modificarClienteRequest request) {
        modificarClienteResponse response = new modificarClienteResponse();
        String uid = request.getUid();
        Cliente cliente = request.getCliente();
        response.setUid(uid);
        String contrasenia =request.getContrasenia();
        String email = request.getEmail();
        if(uid.equals("") || uid==null)
        {
            response.setSuccess(false);
            response.setErrorCode(1);
            response.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return response;
        }
        if(cliente==null)
        {
            response.setSuccess(false);
            response.setErrorCode(2);
            response.setErrorMsg("El registro del cliente no puede estar vacio.");
            return response;
        }
        
        if(cliente.getnombre().equals("") || cliente.getCodigoPostal().equals("") || cliente.getPais().equals("") 
          || cliente.getnombre() == null || cliente.getCodigoPostal() == null ||  cliente.getPais() == null)
        {
            String campo = "";
            if(cliente.getnombre().equals("") || cliente.getnombre() == null) campo = campo + ",Nombre";
            if(cliente.getCodigoPostal().equals("") || cliente.getCodigoPostal() == null) campo = campo + ",CodigoPostal";
           // if(cliente.getEmail().equals("") || cliente.getEmail() == null) campo = campo + ",Email";
            //if(cliente.getContrasenia().equals("") || cliente.getContrasenia() == null) campo= "Contraseña";
            if(cliente.getPais().equals("") || cliente.getPais() == null) campo = campo + ",Pais";
            response.setSuccess(false);
            response.setErrorCode(3);
            response.setErrorMsg("El campo(s) son requeridos : "+campo);
            return response;
        }
        
        if(peticiones.getvalidaCliente2(email, contrasenia)<=0)
        {
            response.setSuccess(false);
            response.setErrorCode(4);
            response.setErrorMsg("cliente o contraseña invalidos");
            return response;
        }
        
        Vector vcid = (Vector)peticiones.buscaCliente(email);
        Vector cid = (Vector) vcid.get(0);
        cliente.setClienteId(Long.valueOf(cid.get(0).toString()));
        if(cliente.getContrasenia().equals("") || cliente.getContrasenia()==null)
             cliente.setContrasenia(contrasenia);
        if(cliente.getEmail().equals("") || cliente.getEmail()==null)
            cliente.setEmail(email);
            
        int resp = peticiones.modificacionCliente(cliente);
        if(resp==-1)
        {
            response.setSuccess(false);
            response.setErrorCode(5);
            response.setErrorMsg("No se encontro dado de alta el Usuario Web. favor de contactar al Administrador del Sistema!");
            return response;            
        }
        if(resp==1)
        {
            response.setSuccess(false);
            response.setErrorCode(6);
            response.setErrorMsg("En este momento no se puede actualizar la informacion del cliente, intente mas tarde.");
            return response;            
        }        

            response.setSuccess(true);
            response.setErrorCode(-1);
            response.setErrorMsg("Cliente "+cliente.getnombre()+" modificado ");
            return response;        
    }

    /**
     * Web service operation
     */
    @WebMethod
    public reservacionesClienteResponse getReservacionesCliente(@WebParam(name = "request") reservacionesClienteRequest request) {
        reservacionesClienteResponse response = new reservacionesClienteResponse();
        String uid = request.getUid();
        reservacion[] reservaciones = null;
       if(uid.equals("") || uid==null)
        {
            response.setSuccess(false);
            response.setErrorCode(1);
            response.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return response;
        }
        
       Vector vres = peticiones.getreservacionesCliente(request.getClienteId(),request.getFechaInicial(), request.getFechaFinal());
       if(vres.size()==0)
        {
            response.setSuccess(false);
            response.setErrorCode(2);
            response.setErrorMsg("No se encontro ninguna reservacion del cliente");
            return response;
        }
       reservaciones=new reservacion[vres.size()];
       for(int i=0; i<vres.size(); i++) 
       {
           reservacion res = new reservacion((Vector)vres.get(i));
           reservaciones[i] = res;
       }
       
            response.setReservaciones(reservaciones);
            response.setSuccess(true);
            response.setErrorCode(-1);
            response.setErrorMsg("Se encontraron "+vres.size()+" Reservaciones del cliente");
            return response;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public comprasClienteResponse getComprasCliente(@WebParam(name = "request") comprasClienteRequest request) {
       comprasClienteResponse response = new comprasClienteResponse();
        String uid = request.getUid();
        compra[] compras = null;
       if(uid.equals("") || uid==null)
        {
            response.setSuccess(false);
            response.setErrorCode(1);
            response.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return response;
        }
        
       Vector vcom = peticiones.getcomprasCliente(request.getClienteId(),request.getFechaInicial(), request.getFechaFinal());
       if(vcom.size()==0)
        {
            response.setSuccess(false);
            response.setErrorCode(2);
            response.setErrorMsg("No se encontro ninguna reservacion del cliente");
            return response;
        }
       compras=new compra[vcom.size()];
       for(int i=0; i<vcom.size(); i++) 
       {
           compra res = new compra((Vector)vcom.get(i));
           compras[i] = res;
       }
       
            response.setCompras(compras);
            response.setSuccess(true);
            response.setErrorCode(-1);
            response.setErrorMsg("Se encontraron "+vcom.size()+" Compras del cliente");
            return response;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public reestableceContraseniaResponse reestablecerContrasenia(@WebParam(name = "request")
    reestableceContraseniaRequest request){
        // TODO implement operation 
        reestableceContraseniaResponse response = new reestableceContraseniaResponse();
        String uid = request.getUid();
        String email = request.getEmail();
        long adminId = request.getAgenteAutorizaId();
        response.setUid(uid);
        if(uid.equals("") || uid==null)
        {
            response.setSuccess(false);
            response.setErrorCode(1);
            response.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return response;
        }
        
        if(email.equals("") ||  email == null)
        {
            String campo = "";
            if(email.equals("") || email == null) campo = "Email";
            response.setSuccess(false);
            response.setErrorCode(2);
            response.setErrorMsg("El campo(s) son requeridos : "+campo);
            return response;
        }

        Vector vvalida = (Vector) peticiones.getValidaAdministradorAutoriza(email,adminId);
        Vector vvcliente = null;
        if(vvalida.size()==0)
        {
            vvcliente = (Vector) peticiones.getCliente2(email);
            if(vvcliente.size()==0)
            {
                response.setSuccess(false);
                response.setErrorCode(3);
                response.setErrorMsg("No existe un cliente registrado con el email "+email+" o esta deshabilitado");
                return response;
            }
        }
        else
        {

            vvcliente = (Vector) peticiones.getCliente3(email);
            if(vvcliente.size()==0)
            {
                response.setSuccess(false);
                response.setErrorCode(3);
                response.setErrorMsg("No existe un cliente registrado con el email "+email);
                return response;
            }
        }            
        
        System.out.println("paso hasta cliente encontrado...");
        Vector vcliente = (Vector) vvcliente.get(0);
        Cliente cliente = new Cliente(vcliente);
        String contrasenia =peticiones.getContraseniaNueva();
        cliente.setContrasenia(contrasenia);
        int resp = peticiones.modificacionCliente(cliente);
        if(resp==-1)
        {
            response.setSuccess(false);
            response.setErrorCode(4);
            response.setErrorMsg("No se encontro dado de alta el Usuario Web. favor de contactar al Administrador del Sistema!");
            return response;            
        }
        if(resp==1)
        {
            response.setSuccess(false);
            response.setErrorCode(5);
            response.setErrorMsg("En este momento no se puede actualizar la informacion del cliente, intente mas tarde.");
            return response;            
        }        
        response.setContrasenia(contrasenia);
        response.setSuccess(true);
        response.setErrorCode(-1);
        response.setErrorMsg("La contrasenia se ha actualizado correctamente");
        return response;
    }    
    
    
    /**
     * Web service operation
     */
    @WebMethod
    public consultaClientesAdmistradosResponse consultarClientesAdministrados(@WebParam(name = "request") consultaClientesAdmistradosRequest request) {
     consultaClientesAdmistradosResponse responseCC = new consultaClientesAdmistradosResponse();
        String uid = request.getUid();
        String administradorId = ""+request.getadministradorId();
         responseCC.setUid(uid);
        System.out.println("Entra a consultar clientes Administrados...");
        if(uid.equals("") || uid==null)
        {
            responseCC.setSuccess(false);
            responseCC.setErrorCode(1);
            responseCC.setErrorMsg("No se encontro identificador de la peticion (uid)");
            return responseCC;
        }
        
         if(administradorId == null || administradorId.equals(""))
        {
            
            System.out.println("Entra a consulta a ver campos vacios...");
              String campo = "";
            if(administradorId.equals("") || administradorId == null) campo = "Usuario Administrador";
            responseCC.setSuccess(false);
            responseCC.setErrorCode(2);
            responseCC.setErrorMsg("El campo(s) son requeridos : "+campo);
            return responseCC;
        }
        Vector vvclientes =  peticiones.getClientesAdministrados(Long.valueOf(administradorId));
        if(vvclientes.size()==0)
        {
           System.out.println("no tiene clientes administrados");
            responseCC.setSuccess(false);
            responseCC.setErrorCode(3);
            responseCC.setErrorMsg("No hay usuarios administrados que mostrar ");
            return responseCC;
        }
        System.out.println("Hay "+vvclientes.size()+" usurios administrados...");
        Vector<Cliente> vcts = new Vector<Cliente>();
        for(int i=0; i<vvclientes.size(); i++)
        {
            Vector vcliente = (Vector) vvclientes.get(i);
            Cliente cliente = new Cliente(vcliente);
            vcts.add(cliente);
        }
        responseCC.setClientesAdministrados(vcts);
        responseCC.setSuccess(true);
        responseCC.setErrorCode(-1);
        responseCC.setErrorMsg("Existen "+vcts.size()+" clientes Administrados" );
        return responseCC;
    }    
    
        
}
