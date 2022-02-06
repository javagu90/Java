/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

/************************* TMS **************************/
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
import ERTMSWS.clasesx.AsientosDisp.AsientosDispReq;
import ERTMSWS.LocalInterceptors.Validators.ValidateAsientosDispReq;
import ERTMSWS.clasesx.AsientosDisp.AsientosDispResp;
import ERTMSWS.clasesx.Login.LoginResp;
import java.rmi.ServerException;
import ERTMSWS.clasesx.ExchangeResp;
import ERTMSWS.LocalInterceptors.Validators.MainValidator;
import ERTMSWS.clasesx.Catalogos.EmpresasVtaReq;
import ERTMSWS.clasesx.Catalogos.EmpresasVtaResp;
import ERTMSWS.clasesx.Catalogos.ServiciosVtaReq;
import ERTMSWS.clasesx.Catalogos.ServiciosVtaResp;
/*************************************************************************************/import ERTMSWS.clasesx.Catalogos.TerminalesReq;
import ERTMSWS.clasesx.Catalogos.TerminalesResp;
import ERTMSWS.clasesx.Catalogos.TiposPagoReq;
import ERTMSWS.clasesx.Catalogos.TiposPagoResp;
import ERTMSWS.clasesx.VenderBoletos.getAsociarComprasClienteReq;
import ERTMSWS.clasesx.VenderBoletos.getAsociarComprasClienteResp;
import ERTMSWS.clasesx.VenderBoletos.getAsociarTokensClienteReq;
import ERTMSWS.clasesx.VenderBoletos.getAsociarTokensClienteResp;
import ERTMSWS.clasesx.VenderBoletos.getModificarTokenReq;
import ERTMSWS.clasesx.VenderBoletos.getModificarTokenResp;
import ERTMSWS.clasesx.VenderBoletos.getTokensClienteReq;
import ERTMSWS.clasesx.VenderBoletos.getTokensClienteResp;

import ERTMSWS.ejbERCONTROLTMSWSFacadeRemote;


import WS_CONTROL.TMS.Solicitud.wsControlTMSFacadeBeanRemote;
import WS_CONTROL.TMSS.getRutasItinerariosReq;
import WS_CONTROL.TMSS.getRutasItinerariosResp;
import WS_CONTROL.Ventour.solicitudes.wsControlVentourFacadeBeanRemote;
import WS_CONTROL.getAsociarDireccionClienteReq;
import WS_CONTROL.getAsociarDireccionClienteResp;
import WS_CONTROL.getAsociarDireccionFiscalClienteReq;
import WS_CONTROL.getAsociarDireccionFiscalClienteResp;
import WS_CONTROL.getClientesReq;
import WS_CONTROL.getClientesResp;
import WS_CONTROL.getDireccionesClienteReq;
import WS_CONTROL.getDireccionesClienteResp;
import WS_CONTROL.getDireccionesFiscalesClienteReq;
import WS_CONTROL.getDireccionesFiscalesClienteResp;
import WS_CONTROL.getFacturasReq;
import WS_CONTROL.getFacturasResp;
import WS_CONTROL.getOperacionesClienteReq;
import WS_CONTROL.getOperacionesClienteResp;
import WS_CONTROL.getOperacionesClientesUsuarioReq;
import WS_CONTROL.getOperacionesClientesUsuarioResp;
import WS_CONTROL.getOperacionesFacturaReq;
import WS_CONTROL.getOperacionesFacturaResp;

import WS_CONTROL.getEstadoCuentaClienteReq;
import WS_CONTROL.getEstadoCuentaClienteResp;
import WS_CONTROL.getLoginReq;
import WS_CONTROL.getLoginResp;
import WS_CONTROL.getLogoutReq;
import WS_CONTROL.getLogoutResp;
import WS_CONTROL.getModificarDireccionClienteReq;
import WS_CONTROL.getModificarDireccionClienteResp;
import WS_CONTROL.getModificarDireccionFiscalClienteReq;
import WS_CONTROL.getModificarDireccionFiscalClienteResp;
import WS_CONTROL.getOperacionesCreditoClientesReq;
import WS_CONTROL.getOperacionesCreditoClientesResp;
import WS_CONTROL.getOperacionesPagosClienteReq;
import WS_CONTROL.getOperacionesPagosClienteResp;
import WS_CONTROL.getOperacionesPagosFacturasReq;
import WS_CONTROL.getOperacionesPagosFacturasResp;

import WS_CONTROL.getOperacionesPromocionReq;
import WS_CONTROL.getOperacionesPromocionResp;

import WS_CONTROL.getOperacionesUsuarioReq;
import WS_CONTROL.getOperacionesUsuarioResp;
import WS_CONTROL.getPagosReq;
import WS_CONTROL.getPagosResp;
import WS_CONTROL.getProductosClienteReq;
import WS_CONTROL.getProductosClienteResp;
import WS_CONTROL.getUsuariosReq;
import WS_CONTROL.getUsuariosResp;

import WS_CONTROL.getPagosFacturaReq;
import WS_CONTROL.getPagosFacturaResp;
import WS_CONTROL.getPerfilesUsuarioReq;
import WS_CONTROL.getPerfilesUsuarioResp;
import WS_CONTROL.getPromocionesReq;
import WS_CONTROL.getPromocionesResp;
import WS_CONTROL.getRegistraTransaccionesClienteReq;
import WS_CONTROL.getRegistraTransaccionesClienteResp;
import WS_CONTROL.getValidaSesionReq;
import WS_CONTROL.getValidaSesionResp;
import WS_CONTROL.paquer.getCancelarGuiaReq;
import WS_CONTROL.paquer.getCancelarGuiaResp;
import WS_CONTROL.paquer.getOperacionesGuiaReq;
import WS_CONTROL.paquer.getOperacionesGuiaResp;
import WS_CONTROL.paquer.getRutaServiciosReq;
import WS_CONTROL.paquer.getRutaServiciosResp;
import WS_CONTROL.paquer.getServiciosReq;
import WS_CONTROL.paquer.getServiciosResp;
import WS_CONTROL.paquer.solicitudes.wsPaqerFacadeBeanRemote;

import WS_CONTROL.solicitudes.wsRepControlFacadeBeanRemote;
import WS_CONTROLVentour.getCancelaContratoReq;
import WS_CONTROLVentour.getCancelaContratoResp;
import WS_CONTROLVentour.getEstadosReq;
import WS_CONTROLVentour.getEstadosResp;
import WS_CONTROLVentour.getMunicipiosReq;
import WS_CONTROLVentour.getMunicipiosResp;
import WS_CONTROLVentour.getOperacionContratoReq;
import WS_CONTROLVentour.getOperacionContratoResp;
import WS_CONTROLVentour.getUnidadesReq;
import WS_CONTROLVentour.getUnidadesResp;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author vgonzalez
 */
@WebService()
public class ERWSINFRA {
    @EJB
    private wsRepControlFacadeBeanRemote controlManager=null;

    @EJB
    private wsControlVentourFacadeBeanRemote ventourManager=null;

    @EJB
    private wsControlTMSFacadeBeanRemote tmsManager=null;

    @EJB
    private wsPaqerFacadeBeanRemote paquerManager=null;

    @EJB
    private ejbERCONTROLTMSWSFacadeRemote ejbERTMSWSFacadeBean=null;

 

    /*************** lookup ************/

    private void lookupTmsTarjetasViajeFacade() {
    /*
     try {
      Context c = new InitialContext();
      if(controlManager==null)
          controlManager = (wsRepControlFacadeBeanRemote) c.lookup("WS_CONTROL.solicitudes.wsRepControlFacadeBeanRemote");
      if(ventourManager==null)
          ventourManager = (wsControlVentourFacadeBeanRemote) c.lookup("WS_CONTROL.Ventour.solicitudes.wsControlVentourFacadeBeanRemote");
      if(tmsManager==null)
          tmsManager = (wsControlTMSFacadeBeanRemote) c.lookup("WS_CONTROL.TMS.Solicitud.wsControlTMSFacadeBeanRemote");
      if(paquerManager==null)
          paquerManager = (wsPaqerFacadeBeanRemote) c.lookup("WS_CONTROL.paquer.solicitudes.wsPaqerFacadeBeanRemote");
      if(ejbERTMSWSFacadeBean==null)
          ejbERTMSWSFacadeBean = (ejbERCONTROLTMSWSFacadeRemote) c.lookup("ERTMSWS.ejbERCONTROLTMSWSFacadeRemote");

    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
     * 
     */
  }

    /*************** lookup ************/

    /*********************** OPERACIONES TMS **************************************/
    /**
     * Web service operation
     */
    @WebMethod
    public CorridasResp getCorridas(@WebParam(name = "request") CorridasReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getCorridas() ***");
        CorridasResp corridasResp = new CorridasResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                corridasResp.setOperacionExitosa(false);
                corridasResp.setErrorCode(resp.getErrorCode());
                corridasResp.setErrorMsg(resp.getErrorMsg());
                return corridasResp;
            }
            corridasResp = ejbERTMSWSFacadeBean.getCorridas(request);
            corridasResp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            ex.printStackTrace();
            MainValidator.responseErrorClass(corridasResp,ex);
        }catch (Exception ex){
            System.out.println("*******1******** Ver Exception.....");
            ex.printStackTrace();
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
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getAsientosDisp() ***");
        AsientosDispResp asientosDispResp = new AsientosDispResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                asientosDispResp.setOperacionExitosa(false);
                asientosDispResp.setErrorCode(resp.getErrorCode());
                asientosDispResp.setErrorMsg(resp.getErrorMsg());
                return asientosDispResp;
            }
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
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getBloquearAsientos() ***");
        BloquearAsientosResp bloquearAsientosResp = new BloquearAsientosResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                bloquearAsientosResp.setOperacionExitosa(false);
                bloquearAsientosResp.setErrorCode(resp.getErrorCode());
                bloquearAsientosResp.setErrorMsg(resp.getErrorMsg());
                return bloquearAsientosResp;
            }
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
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getVenderBoletos() ***");
        VenderBoletosResp venderBoletosResp = new VenderBoletosResp();
        long clienteId =-1;
        long sesionId = request.getSesionId();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
            {
                request.setSesionId((int)resp.getCorteId());
                //request.setClienteId((int)resp.getClienteId());
                clienteId = request.getClienteId();
                for(int i=0; i<request.getTransaccion().getAsientos().length;i++)
                {
                    request.getTransaccion().getAsientos()[i].setClienteId((int)clienteId);
                    request.getTransaccion().getAsientos()[i].setReferenciaAdicional12("MOS");
                }

            }
            else
            {
                venderBoletosResp.setOperacionExitosa(false);
                venderBoletosResp.setErrorCode(resp.getErrorCode());
                venderBoletosResp.setErrorMsg(resp.getErrorMsg());
                return venderBoletosResp;
            }
            System.out.println("clienteId: "+clienteId);

            venderBoletosResp = ejbERTMSWSFacadeBean.getVenderBoletos(request);
            venderBoletosResp.printContent();
            //Agregar la funcionalidad de Registrar las Ventas del cliente
            if(venderBoletosResp.isOperacionExitosa() && clienteId!=-1 && clienteId!=0)
            {
                for(int i=0; i<request.getTransaccion().getAsientos().length;i++)
                {
                    ERTMSWS.clases.Asientos asiento = request.getTransaccion().getAsientos()[i];
                    getRegistraTransaccionesClienteReq par = new getRegistraTransaccionesClienteReq();
                    par.setClienteId(clienteId);
                    par.setFechaTransaccion(new Date());
                    par.setIVA(0);
                    par.setIVARetenido(0);
                    par.setMontoComision(0);
                    par.setMontoDescuento(0);
                    par.setMontoTransaccion(asiento.getImporteBoleto());
                    par.setNombreProducto("BOLETO");
                    par.setNumeroTransaccion(asiento.getFolioPreimpreso());
                    par.setRetencion5Millar(0);
                    par.setSesionId(sesionId);
                    par.setSubtotal(asiento.getImporteBoleto());
                    par.setTipoMovimiento("VENTA_PRODUCTO");
                    par.setTotal(asiento.getImporteBoleto());
                    getRegistraTransaccionesClienteResp respCre = getRegistraTransaccionesCliente(par);
                }
            }

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
        lookupTmsTarjetasViajeFacade();
        CancelarBoletosResp cancelarBoletosResp = new CancelarBoletosResp();
        long clienteId =-1;
        long sesionId = request.getSesionId();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
            {
                request.setSesionId((int)resp.getCorteId());
                clienteId = resp.getClienteId();
            }
            else
            {
                cancelarBoletosResp.setOperacionExitosa(false);
                cancelarBoletosResp.setErrorCode(resp.getErrorCode());
                cancelarBoletosResp.setErrorMsg(resp.getErrorMsg());
                return cancelarBoletosResp;
            }
            cancelarBoletosResp = ejbERTMSWSFacadeBean.getCancelarBoletos(request);
            if(cancelarBoletosResp.isOperacionExitosa() && clienteId!=-1)
            {
            //Agregar la funcionalidad de Registrar las Ventas del cliente
                for(int i=0; i<request.getFoliosBoletos().length;i++)
                {
                    ERTMSWS.clases.FoliosBoletos asiento = request.getFoliosBoletos()[i];
                    getRegistraTransaccionesClienteReq par = new getRegistraTransaccionesClienteReq();
                    par.setClienteId(clienteId);
                    par.setFechaTransaccion(new Date());
                    par.setIVA(0);
                    par.setIVARetenido(0);
                    par.setMontoComision(0);
                    par.setMontoDescuento(0);
                    par.setMontoTransaccion(0);
                    par.setNombreProducto("BOLETO");
                    par.setNumeroTransaccion(asiento.getFolioPreimpreso());
                    par.setRetencion5Millar(0);
                    par.setSesionId(sesionId);
                    par.setSubtotal(0);
                    par.setTipoMovimiento("CANCELACION_PRODUCTO");
                    par.setTotal(0);
                    getRegistraTransaccionesClienteResp respCre = getRegistraTransaccionesCliente(par);
                }
            }
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
        lookupTmsTarjetasViajeFacade();
        CambioHorarioResp cambioHorarioResp = new CambioHorarioResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                cambioHorarioResp.setOperacionExitosa(false);
                cambioHorarioResp.setErrorCode(resp.getErrorCode());
                cambioHorarioResp.setErrorMsg(resp.getErrorMsg());
                return cambioHorarioResp;
            }
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
        lookupTmsTarjetasViajeFacade();
        CanjeBAResp canjeBAResp = new CanjeBAResp();

        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                canjeBAResp.setOperacionExitosa(false);
                canjeBAResp.setErrorCode(resp.getErrorCode());
                canjeBAResp.setErrorMsg(resp.getErrorMsg());
                return canjeBAResp;
            }
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
    public ValidarBoletoResp getValidarBoleto(@WebParam(name = "request") ValidarBoletoReq request) {
        lookupTmsTarjetasViajeFacade();
       ValidarBoletoResp validarBoletoResp = new ValidarBoletoResp();

        try{
            //Ejecución del procedimiento de BD para el getLogout
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                validarBoletoResp.setOperacionExitosa(false);
                validarBoletoResp.setErrorCode(resp.getErrorCode());
                validarBoletoResp.setErrorMsg(resp.getErrorMsg());
                return validarBoletoResp;
            }
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





    /******************************************************************************/

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOperacionesPromocion")
    public getOperacionesPromocionResp getOperacionesPromocion(@WebParam(name = "request")
    getOperacionesPromocionReq request) {
        lookupTmsTarjetasViajeFacade();
        getOperacionesPromocionResp respuesta = new getOperacionesPromocionResp();
        System.out.println("request.getSesionId(getOperacionesPromocionResp): "+request.getSesionId());
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("REPCONTROL");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(!resp.isOperacionExitosa())
            {
                respuesta.setOperacionExitosa(false);
                respuesta.setErrorCode(resp.getErrorCode());
                respuesta.setErrorMsg(resp.getErrorMsg());
                return respuesta;
            }
            request.setUsuarioId(resp.getUsuarioId());
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            ex.printStackTrace();
        }catch (Exception ex){
            //Manejo de Excepciones en general
            ex.printStackTrace();
        }
        return controlManager.getOperacionesPromocion(request);
    }


        @WebMethod(operationName = "getOperacionesUsuario")
    public getOperacionesUsuarioResp getOperacionesUsuario(@WebParam(name = "request")
    getOperacionesUsuarioReq request) {
            lookupTmsTarjetasViajeFacade();
        return controlManager.getOperacionesUsuario(request);
    }

 @WebMethod(operationName = "getUsuarios")
    public getUsuariosResp getUsuarios(@WebParam(name = "request")
    getUsuariosReq request) {
     lookupTmsTarjetasViajeFacade();
        return controlManager.getUsuarios(request);
    }

 @WebMethod(operationName = "getOperacionesCliente")
    public getOperacionesClienteResp getOperacionesCliente(@WebParam(name = "request")
    getOperacionesClienteReq request) {
     lookupTmsTarjetasViajeFacade();
        return controlManager.getOperacionesCliente(request);
    }

 @WebMethod(operationName = "getClientes")
    public getClientesResp getClientes(@WebParam(name = "request")
    getClientesReq request) {
     lookupTmsTarjetasViajeFacade();
        return controlManager.getClientes(request);
    }

 @WebMethod(operationName = "getOperacionesClientesUsuario")
    public getOperacionesClientesUsuarioResp getOperacionesClientesUsuario(@WebParam(name = "request")
    getOperacionesClientesUsuarioReq request) {
     lookupTmsTarjetasViajeFacade();
        return controlManager.getOperacionesClientesUsuario(request);
    }

  @WebMethod(operationName = "getPagos")
    public getPagosResp getPagos(@WebParam(name = "request")
    getPagosReq request) {
      lookupTmsTarjetasViajeFacade();
        return controlManager.getPagos(request);
    }

  @WebMethod(operationName = "getOperacionesFactura")
    public getOperacionesFacturaResp getOperacionesFactura(@WebParam(name = "request")
    getOperacionesFacturaReq request) {
      lookupTmsTarjetasViajeFacade();
        return controlManager.getOperacionesFactura(request);
    }

   @WebMethod(operationName = "getFacturas")
    public getFacturasResp getFacturas(@WebParam(name = "request")
    getFacturasReq request) {
       lookupTmsTarjetasViajeFacade();
        return controlManager.getFacturas(request);
    }

 /* @WebMethod(operationName = "getProductosCliente")
    public getProductosClienteResp getProductosCliente(@WebParam(name = "request")
    getProductosClienteReq request) {
        return controlManager.getProductosCliente(request);
    }*/

   @WebMethod(operationName = "getProductosCliente")
    public getProductosClienteResp getProductosCliente(@WebParam(name = "request")
    getProductosClienteReq  request) {
       lookupTmsTarjetasViajeFacade();
        return controlManager.getProductosCliente(request);
    }


    @WebMethod(operationName = "getUnidades")
    public getUnidadesResp getUnidades(@WebParam(name = "request")
    getUnidadesReq  request) {
    lookupTmsTarjetasViajeFacade();
        return ventourManager.getUnidades(request);
    }


    @WebMethod(operationName = "getCancelaContrato")
    public getCancelaContratoResp getCancelaContrato(@WebParam(name = "request")
    getCancelaContratoReq  request) {
    lookupTmsTarjetasViajeFacade();
        return ventourManager.getCancelaContrato(request);
    }

    @WebMethod(operationName = "getOperacionContrato")
    public getOperacionContratoResp  getOperacionContrato(@WebParam(name = "request")
    getOperacionContratoReq  request) {
    lookupTmsTarjetasViajeFacade();
        return ventourManager.getOperacionContrato(request);
    }

     @WebMethod(operationName = "getRutasItinerarios")
    public getRutasItinerariosResp  getRutasItinerarios(@WebParam(name = "request")
          getRutasItinerariosReq  request) {
          lookupTmsTarjetasViajeFacade();
        return tmsManager.getServicios(request);
    }

  

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPromociones")
    public getPromocionesResp getPromociones(@WebParam(name = "parametros")
    getPromocionesReq parametros) {
        lookupTmsTarjetasViajeFacade();
        getPromocionesResp respuesta = new getPromocionesResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("REPCONTROL");
            param.setSesionId(parametros.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(!resp.isOperacionExitosa())
            {
                respuesta.setOperacionExitosa(false);
                respuesta.setErrorCode(resp.getErrorCode());
                respuesta.setErrorMsg(resp.getErrorMsg());
                return respuesta;
            }
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            ex.printStackTrace();
        }catch (Exception ex){
            //Manejo de Excepciones en general
            ex.printStackTrace();
        }
        return controlManager.getPromociones(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPerfilesUsuario")
    public getPerfilesUsuarioResp getPerfilesUsuario(@WebParam(name = "parametros")
    getPerfilesUsuarioReq parametros) {
    lookupTmsTarjetasViajeFacade();
        getPerfilesUsuarioResp respuesta = new getPerfilesUsuarioResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("REPCONTROL");
            param.setSesionId(parametros.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(!resp.isOperacionExitosa())
            {
                respuesta.setOperacionExitosa(false);
                respuesta.setErrorCode(resp.getErrorCode());
                respuesta.setErrorMsg(resp.getErrorMsg());
                return respuesta;
            }
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();
            ex.printStackTrace();
        }catch (Exception ex){
            //Manejo de Excepciones en general
            ex.printStackTrace();
        }
        return controlManager.getPerfilesUsuario(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOperacionesCreditoClientes")
    public getOperacionesCreditoClientesResp getOperacionesCreditoClientes(@WebParam(name = "parametros")
    getOperacionesCreditoClientesReq parametros) {
        lookupTmsTarjetasViajeFacade();
         return controlManager.getOperacionesCreditoClientes(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOperacionesPagosCliente")
    public getOperacionesPagosClienteResp getOperacionesPagosCliente(@WebParam(name = "parametros")
    getOperacionesPagosClienteReq parametros) {
        lookupTmsTarjetasViajeFacade();
        return controlManager.getOperacionesPagosCliente(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPagosFactura")
    public getPagosFacturaResp getPagosFactura(@WebParam(name = "parametros")
    getPagosFacturaReq parametros) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return controlManager.getPagosFactura(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOperacionesPagosFacturas")
    public getOperacionesPagosFacturasResp getOperacionesPagosFacturas(@WebParam(name = "parametros")
    getOperacionesPagosFacturasReq parametros) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return controlManager.getOperacionesPagosFacturas(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRegistraTransaccionesCliente")
    public getRegistraTransaccionesClienteResp getRegistraTransaccionesCliente(@WebParam(name = "parametros")
    getRegistraTransaccionesClienteReq parametros) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return controlManager.getRegistraTransaccionesCliente(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEstadoCuentaCliente")
    public getEstadoCuentaClienteResp getEstadoCuentaCliente(@WebParam(name = "parametros")
    getEstadoCuentaClienteReq parametros) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return controlManager.getEstadoCuentaCliente(parametros);
    }

    @WebMethod(operationName = "getServicios")
    public getServiciosResp getServicios(@WebParam(name = "parametros") getServiciosReq parametros) {
        lookupTmsTarjetasViajeFacade();
        return paquerManager.getServicios(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRutaServicios")
    public getRutaServiciosResp getRutaServicios(@WebParam(name = "parametros")
    getRutaServiciosReq parametros) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return paquerManager.getRutaServicios(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOperacionesGuia")
    public getOperacionesGuiaResp getOperacionesGuia(@WebParam(name = "parametros")
    getOperacionesGuiaReq parametros) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return paquerManager.getOperacionesGuia(parametros);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCancelarGuia")
    public getCancelarGuiaResp getCancelarGuia(@WebParam(name = "parameter")
    getCancelarGuiaReq parameter) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return paquerManager.getCancelarGuia(parameter);
    }
 
    @WebMethod(operationName = "getLogin")
    public getLoginResp getLogin(@WebParam(name = "parameter")
    getLoginReq parameter) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return controlManager.getLogin(parameter);
    }

    @WebMethod(operationName = "getLogout")
    public getLogoutResp getLogout(@WebParam(name = "parameter")
    getLogoutReq parameter) {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return controlManager.getLogout(parameter);
    }


    
    @WebMethod(operationName = "getFecha")
    public String getFecha() {
        //TODO write your implementation code here:
        lookupTmsTarjetasViajeFacade();
        return tmsManager.getFecha();
    }


    @WebMethod(operationName = "getEstados")
    public getEstadosResp getEstados(@WebParam(name = "request")
    getEstadosReq  request) {
    lookupTmsTarjetasViajeFacade();
        return ventourManager.getEstados(request);
    }


    @WebMethod(operationName = "getMunicipios")
    public getMunicipiosResp getMunicipios(@WebParam(name = "request")
    getMunicipiosReq  request) {
    lookupTmsTarjetasViajeFacade();
        return ventourManager.getMunicipios(request);
    }




    /*
    @WebMethod(operationName = "getLogin2")
    public getLoginResp getLogin2(@WebParam(name = "parameter")
    getLoginReq parameter) {
        //TODO write your implementation code here:
        return controlManager.getLogin(parameter);
    }
    */

/*
 *  ********* METOS DE EBUS *********** 
 *  
 */

    /**
     * Web service operation
     */
    @WebMethod
    public TerminalesResp getTerminalesVta(@WebParam(name = "request") TerminalesReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getTerminales() ***");
        TerminalesResp terminalesResp = new TerminalesResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                terminalesResp.setOperacionExitosa(false);
                terminalesResp.setErrorCode(resp.getErrorCode());
                terminalesResp.setErrorMsg(resp.getErrorMsg());
                return terminalesResp;
            }
            terminalesResp = ejbERTMSWSFacadeBean.getTerminales();
            terminalesResp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(terminalesResp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(terminalesResp,ex);
            //Manejo de Excepciones en general
        }


        System.out.println("*** FIN: WS.getTerminales() ***");
        return terminalesResp;
    }

    @WebMethod
    public TiposPagoResp getTiposPagoVta(@WebParam(name = "request") TiposPagoReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getTiposPago() ***");
        TiposPagoResp tesp = new TiposPagoResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = ejbERTMSWSFacadeBean.getTiposPago();
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }


        System.out.println("*** FIN: WS.getTiposPago() ***");
        return tesp;
    }

    @WebMethod
    public ServiciosVtaResp getSericiosVta(@WebParam(name = "request") ServiciosVtaReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getSericiosVta() ***");
        ServiciosVtaResp tesp = new ServiciosVtaResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = ejbERTMSWSFacadeBean.getServciosVta();
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }


        System.out.println("*** FIN: WS.getSericiosVta() ***");
        return tesp;
    }

    @WebMethod
    public EmpresasVtaResp getEmpresasVta(@WebParam(name = "request") EmpresasVtaReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getEmpresasVta() ***");
        EmpresasVtaResp tesp = new EmpresasVtaResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = ejbERTMSWSFacadeBean.getEmpresas();
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getEmpresasVta() ***");
        return tesp;
    } 

    @WebMethod
    public getAsociarComprasClienteResp getAsociarComprasCliente(@WebParam(name = "request") getAsociarComprasClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getAsociarComprasCliente() ***");
        getAsociarComprasClienteResp tesp = new getAsociarComprasClienteResp();
        try{

            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = ejbERTMSWSFacadeBean.getAsociarComprasCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getAsociarComprasCliente() ***");
        return tesp;
    }


    @WebMethod
    public getAsociarTokensClienteResp getAsociarTokensCliente(@WebParam(name = "request") getAsociarTokensClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getAsociarTokensCliente() ***");
        getAsociarTokensClienteResp tesp = new getAsociarTokensClienteResp();
        try{

            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getAsociarTokensCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getAsociarTokensCliente() ***");
        return tesp;
    }

    @WebMethod
    public getModificarTokenResp geModificarToken(@WebParam(name = "request") getModificarTokenReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.geModificarToken() ***");
        getModificarTokenResp tesp = new getModificarTokenResp();
        try{

            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.geModificarToken(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.geModificarToken() ***");
        return tesp;
    }

    @WebMethod
    public getTokensClienteResp getTokensCliente(@WebParam(name = "request") getTokensClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getTokensCliente() ***");
        getTokensClienteResp tesp = new getTokensClienteResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((int)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getTokensCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getTokensCliente() ***");
        return tesp;
    }

    @WebMethod
    public getAsociarDireccionClienteResp getAsociarDireccionCliente(@WebParam(name = "request") getAsociarDireccionClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getAsociarDireccionCliente() ***");
        getAsociarDireccionClienteResp tesp = new getAsociarDireccionClienteResp();
        try{

            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((long)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getAsociarDireccionCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getAsociarDireccionCliente() ***");
        return tesp;
    }

    @WebMethod
    public getModificarDireccionClienteResp getModificarDireccionCliente(@WebParam(name = "request") getModificarDireccionClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getModificarDireccionCliente() ***");
        getModificarDireccionClienteResp tesp = new getModificarDireccionClienteResp();
        try{

            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((long)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getModificarDireccionCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getModificarDireccionCliente() ***");
        return tesp;
    }

    @WebMethod
    public getDireccionesClienteResp getDireccionesCliente(@WebParam(name = "request") getDireccionesClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getDireccionesCliente() ***");
        getDireccionesClienteResp tesp = new getDireccionesClienteResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((long)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getDireccionesCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getDireccionesCliente() ***");
        return tesp;
    }


/**********************************/
    @WebMethod
    public getAsociarDireccionFiscalClienteResp getAsociarDireccionFiscalCliente(@WebParam(name = "request") getAsociarDireccionFiscalClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getAsociarDireccionFiscalCliente() ***");
        getAsociarDireccionFiscalClienteResp tesp = new getAsociarDireccionFiscalClienteResp();
        try{

            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((long)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getAsociarDireccionFiscalCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getAsociarDireccionFiscalCliente() ***");
        return tesp;
    }

    @WebMethod
    public getModificarDireccionFiscalClienteResp getModificarDireccionFiscalCliente(@WebParam(name = "request") getModificarDireccionFiscalClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getModificarDireccionFiscalCliente() ***");
        getModificarDireccionFiscalClienteResp tesp = new getModificarDireccionFiscalClienteResp();
        try{

            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((long)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getModificarDireccionFiscalCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getModificarDireccionFiscalCliente() ***");
        return tesp;
    } 

    @WebMethod
    public getDireccionesFiscalesClienteResp getDireccionesFiscalesCliente(@WebParam(name = "request") getDireccionesFiscalesClienteReq request) {
        lookupTmsTarjetasViajeFacade();
        System.out.println("*** INI: WS.getDireccionesFiscalesCliente() ***");
        getDireccionesFiscalesClienteResp tesp = new getDireccionesFiscalesClienteResp();
        try{
            getValidaSesionReq param = new getValidaSesionReq();
            param.setCanalVenta("TMS");
            param.setSesionId(request.getSesionId());
            getValidaSesionResp resp =  controlManager.getValidaSesion(param);
            if(resp.isOperacionExitosa())
                request.setSesionId((long)resp.getCorteId());
            else
            {
                tesp.setOperacionExitosa(false);
                tesp.setErrorCode(resp.getErrorCode());
                tesp.setErrorMsg(resp.getErrorMsg());
                return tesp;
            }
            tesp = controlManager.getDireccionesFiscalesCliente(request);
            tesp.printContent();
        }catch (EJBException ejbex){
            Exception ex = (Exception)((ServerException) ejbex.getCausedByException()).detail.getCause();

            MainValidator.responseErrorClass(tesp,ex);
        }catch (Exception ex){
            MainValidator.responseErrorClass(tesp,ex);
            //Manejo de Excepciones en general
        }
        System.out.println("*** FIN: WS.getDireccionesFiscalesCliente() ***");
        return tesp;
    }



}


