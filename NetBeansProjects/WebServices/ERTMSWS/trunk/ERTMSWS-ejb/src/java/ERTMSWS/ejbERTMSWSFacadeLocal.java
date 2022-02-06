
package ERTMSWS;

import javax.ejb.Local;

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
/**
 * This is the business interface for ejbERTMSWSFacade enterprise bean.
 */
@Local
public interface ejbERTMSWSFacadeLocal{
        
    public LoginResp getLogin (LoginReq loginReq);
    public CorridasResp getCorridas(CorridasReq corridasReq);
    public AsientosDispResp getAsientosDisp(AsientosDispReq asientosDispReq);
    public BloquearAsientosResp getBloquearAsientos(BloquearAsientosReq bloquerAsientosReq);
    public VenderBoletosResp getVenderBoletos(VenderBoletosReq venderBoletosReq);
    public CancelarBoletosResp getCancelarBoletos(CancelarBoletosReq cancelarBoletosReq);
    public CambioHorarioResp getCambioHorario(CambioHorarioReq cambioHorarioReq);
    public CanjeBAResp getCanjeBA(CanjeBAReq canjeBAReq);
    public LogoutResp getLogout(LogoutReq logoutReq);
    
}
