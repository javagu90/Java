package ERTMSWS;

import ERTMSWS.clasesx.BloquearAsientos.BloquearAsientosReq;
import ERTMSWS.clasesx.BloquearAsientos.BloquearAsientosResp;
import ERTMSWS.clasesx.CambioHorario.CambioHorarioReq;
import ERTMSWS.clasesx.CambioHorario.CambioHorarioResp;
import ERTMSWS.clasesx.CancelarBoletos.CancelarBoletosReq;
import ERTMSWS.clasesx.CancelarBoletos.CancelarBoletosResp;
import ERTMSWS.clasesx.CanjeBA.CanjeBAReq;
import ERTMSWS.clasesx.CanjeBA.CanjeBAResp;
import ERTMSWS.clasesx.Corridas.CorridasReq;
import ERTMSWS.clasesx.Corridas.CorridasResp;
import ERTMSWS.clasesx.Login.LoginReq;
import ERTMSWS.clasesx.Logout.LogoutReq;
import ERTMSWS.clasesx.Logout.LogoutResp;
import ERTMSWS.clasesx.ValidarBoleto.ValidarBoletoReq;
import ERTMSWS.clasesx.ValidarBoleto.ValidarBoletoResp;
import ERTMSWS.clasesx.VenderBoletos.VenderBoletosReq;
import ERTMSWS.clasesx.VenderBoletos.VenderBoletosResp;
import javax.ejb.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import ERTMSWS.clasesx.AsientosDisp.AsientosDispReq;
import ERTMSWS.LocalInterceptors.Validators.ValidateAsientosDispReq;
import ERTMSWS.clasesx.AsientosDisp.AsientosDispResp;
import ERTMSWS.clasesx.Login.LoginResp;
import java.rmi.ServerException;
import ERTMSWS.clasesx.ExchangeResp;
import ERTMSWS.LocalInterceptors.Validators.MainValidator;
/**
 * This is the bean class for the reer enterprise bean.
 * Created 21/06/2010 05:58:49 PM
 * 
 * @author opalafox
 *
 */

@Stateless
@WebService
public class ERTMSWSImpl {
    
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
    @EJB
    private ejbERTMSWSFacadeRemote ejbERTMSWSFacadeBean;

    
    /**
     * Web service operation
     */
    @WebMethod
    public LoginResp getLogin(@WebParam(name = "request") LoginReq request) {
        System.out.println("*** INI: WS.getLogin() ***");
        LoginResp loginResp = new LoginResp();
      
        try{
            //Ejecución del procedimiento de BD para el getLogin 
            loginResp = ejbERTMSWSFacadeBean.getLogin(request);
            loginResp.printContent();
            
        }catch(EJBException ejbex){
            //Manejo de excepciones internas definidas por MainValidator
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            MainValidator.responseErrorClass(loginResp,ex);
            
        }catch(Exception ex){
            //Manejo de Excepciones en general
           
            MainValidator.responseErrorClass(loginResp,ex);
            
        }
        System.out.println("*** FIN: WS.getLogin ***");  
        return loginResp;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public CorridasResp getCorridas(@WebParam(name = "request") CorridasReq request) {
        System.out.println("*** INI: WS.getCorridas() ***");
        CorridasResp corridasResp = new CorridasResp();
        try{
            corridasResp = ejbERTMSWSFacadeBean.getCorridas(request);
            corridasResp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(corridasResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(corridasResp,ex);
            //Manejo de Excepciones en general
        }
        
        
        System.out.println("*** FIN: WS.getCorridas() ***");
        return corridasResp;
    }
    

    /**
     * Web service operation
     */
    @WebMethod
    public AsientosDispResp getAsientosDisp(@WebParam(name = "request") AsientosDispReq request) {
        System.out.println("*** INI: WS.getAsientosDisp() ***");
        AsientosDispResp asientosDispResp = new AsientosDispResp();
        try{
            asientosDispResp = ejbERTMSWSFacadeBean.getAsientosDisp(request);
            asientosDispResp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(asientosDispResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(asientosDispResp,ex);
            //Manejo de Excepciones en general
        }
        
        
        System.out.println("*** FIN: WS.getAsientosDisp() ***");
        return asientosDispResp;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public BloquearAsientosResp getBloquearAsientos(@WebParam(name = "request") BloquearAsientosReq request) {
        System.out.println("*** INI: WS.getBloquearAsientos() ***");
        BloquearAsientosResp bloquearAsientosResp = new BloquearAsientosResp();
        try{
            bloquearAsientosResp = ejbERTMSWSFacadeBean.getBloquearAsientos(request);
            bloquearAsientosResp.printContent();
        
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(bloquearAsientosResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(bloquearAsientosResp,ex);
            //Manejo de Excepciones en general
        }
        
        // TODO implement operation 
        System.out.println("*** FIN: WS.getBloquearAsientos() ***");
        return bloquearAsientosResp;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public VenderBoletosResp getVenderBoletos(@WebParam(name = "request") VenderBoletosReq request) {
        System.out.println("*** INI: WS.getVenderBoletos() ***");
        VenderBoletosResp venderBoletosResp = new VenderBoletosResp();
        
        try{
            venderBoletosResp = ejbERTMSWSFacadeBean.getVenderBoletos(request);
            venderBoletosResp.printContent();
        
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(venderBoletosResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(venderBoletosResp,ex);
            //Manejo de Excepciones en general
        }
        
        System.out.println("*** FIN: WS.getVenderBoletos() ***");
        return venderBoletosResp;
        
    }

    /**
     * Web service operation
     */
    @WebMethod
    public CancelarBoletosResp getCancelarBoletos(@WebParam(name = "request") CancelarBoletosReq request) {
        CancelarBoletosResp cancelarBoletosResp = new CancelarBoletosResp();
        
        try{
            cancelarBoletosResp = ejbERTMSWSFacadeBean.getCancelarBoletos(request);
            cancelarBoletosResp.printContent();
        
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(cancelarBoletosResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(cancelarBoletosResp,ex);
            //Manejo de Excepciones en general
        }
        
        
        return cancelarBoletosResp;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public CambioHorarioResp getCambioHorario(@WebParam(name = "request") CambioHorarioReq request) {
        
        CambioHorarioResp cambioHorarioResp = new CambioHorarioResp();
        try{
            cambioHorarioResp = ejbERTMSWSFacadeBean.getCambioHorario(request);
            cambioHorarioResp.printContent();
        
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(cambioHorarioResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(cambioHorarioResp,ex);
            //Manejo de Excepciones en general
        }
        
        return cambioHorarioResp;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public CanjeBAResp getCanjeBA(@WebParam(name = "request") CanjeBAReq request) {
        CanjeBAResp canjeBAResp = new CanjeBAResp();
        
        try{
            canjeBAResp = ejbERTMSWSFacadeBean.getCanjeBA(request);
            canjeBAResp.printContent();
        
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(canjeBAResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(canjeBAResp,ex);
            //Manejo de Excepciones en general
        }
        
        return canjeBAResp;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public LogoutResp getLogout(@WebParam(name = "request") LogoutReq request) {
        LogoutResp logoutResp = new LogoutResp();
        
        try{
            //Ejecución del procedimiento de BD para el getLogout 
            logoutResp =ejbERTMSWSFacadeBean.getLogout(request);
            logoutResp.printContent();
        }catch (EJBException ejbex){
            //Manejo de excepciones internas definidas por MainValidator
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(logoutResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(logoutResp,ex);
            //Manejo de Excepciones en general
        }
        
        return logoutResp;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public ValidarBoletoResp getValidarBoleto(@WebParam(name = "request") ValidarBoletoReq request) {
       ValidarBoletoResp validarBoletoResp = new ValidarBoletoResp();
        
        try{
            //Ejecución del procedimiento de BD para el getLogout 
            validarBoletoResp =ejbERTMSWSFacadeBean.getValidarBoleto(request);
            validarBoletoResp.printContent();
        }catch (EJBException ejbex){
            //Manejo de excepciones internas definidas por MainValidator
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            
            MainValidator.responseErrorClass(validarBoletoResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(validarBoletoResp,ex);
            //Manejo de Excepciones en general
        }
        
        return validarBoletoResp;
    }
    

    
}
