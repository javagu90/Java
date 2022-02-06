/*
 * ejbERTMSWSFacadeRemote.java
 *
 * Created on 21 de junio de 2010, 03:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


package ERTMSWS;

import javax.ejb.Remote;

//Importación de clases definidas para intercambio
import ERTMSWS.clasesx.AsientosDisp.AsientosDispReq;
import ERTMSWS.clasesx.AsientosDisp.AsientosDispResp;
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
import ERTMSWS.clasesx.Login.LoginResp;
import ERTMSWS.clasesx.Logout.LogoutReq;
import ERTMSWS.clasesx.Logout.LogoutResp;
import ERTMSWS.clasesx.VenderBoletos.VenderBoletosReq;
import ERTMSWS.clasesx.VenderBoletos.VenderBoletosResp;
import ERTMSWS.clasesx.ValidarBoleto.ValidarBoletoReq;
import ERTMSWS.clasesx.ValidarBoleto.ValidarBoletoResp;
/**
 * This is the business interface for ejbERTMSWSFacade enterprise bean.
 */
@Remote
public interface ejbERTMSWSFacadeRemote{
        
    public LoginResp getLogin (LoginReq loginReq)throws Exception;
    public CorridasResp getCorridas(CorridasReq corridasReq)throws Exception;
    public AsientosDispResp getAsientosDisp(AsientosDispReq asientosDispReq)throws Exception;
    public BloquearAsientosResp getBloquearAsientos(BloquearAsientosReq bloquerAsientosReq)throws Exception;
    public VenderBoletosResp getVenderBoletos(VenderBoletosReq venderBoletosReq)throws Exception;
    public CancelarBoletosResp getCancelarBoletos(CancelarBoletosReq cancelarBoletosReq)throws Exception;
    public CambioHorarioResp getCambioHorario(CambioHorarioReq cambioHorarioReq)throws Exception;
    public CanjeBAResp getCanjeBA(CanjeBAReq canjeBAReq)throws Exception;
    public LogoutResp getLogout(LogoutReq logoutReq)throws Exception;
    public ValidarBoletoResp getValidarBoleto(ValidarBoletoReq validarBoletoReq)throws Exception;
    
}