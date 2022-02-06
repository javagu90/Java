
package xertms.solicitud;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import xertms.entidad.TmsCortesTbl;
import xertms.entidad.TmsDesgloceEfectivoTbl;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsRecoleccionesTbl;
import xertms.entidad.TmsSesionActividadesTbl;
import xertms.entidad.TmsTiposPasajeroTbl;
import xertms.entidad.TmsVtaTiposPagoTbl;


/**
 * This is the business interface for TmsCorteTaqFacade enterprise bean.
 */
@Remote
public interface TmsCorteTaqFacadeRemote {
    List<TmsCortesTbl> queryTmsCortesTblByFecha(Timestamp fecha);
    
    List<TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblfindAll();
    
    List<TmsVtaTiposPagoTbl> queryTmsVtaTiposPagoTblfindAll();

    List<Object[]> queryTmsCortesTblForSales(String fecha, String pendientes);
    
    List<Object[]> queryFolioFinal(long corteId);
    
    Object queryUltimoFolioVendido(long corteId, String empresaNombre);
    
    Object queryFolioInicial(long corteId, long empresaId);
    
    Object queryRecolMonto(long corteId);
    
    // TRANSACCION
   
   
   
    Object addSesionCorte(String PrefijoTerminal, TmsSesionActividadesTbl tmsSesionActividadesTbl);
    
    Object queryActividadSesionCorteGetId();
    
    Object queryActividadSesionUltFolioGetId();
    
    Object addRecoleccion(String PrefijoTerminal, TmsRecoleccionesTbl tmsRecoleccionesTbl);
    
    boolean addDesgloce(String PrefijoTerminal, TmsDesgloceEfectivoTbl tmsDesgloceEfectivoTbl);
    
    long obtenerFolioActIdIniCor(String claveActividad, long usuarioId);
    
    Date obtenerFechaSesion(long sesionId);
    
    String[] paramReporte();
    
    int finalizaLoteFolios(String empresa, String ubv, long usuarioId, String esquema);
    
    String[] obtenerTerminal();
    
    Object queryTmsCortesPendientesEnDiaLaboral(String dia);

    int sigCorteSeq();

    String obtenerEmpresaPrincipal(long cajaId);
    
    long obtenerCaja(String computadora);

    List<TmsEmpresasTbl> queryTmsEmpresasTblAll();

    boolean queryFinalizaSesionVenta(String PrefijoTerminal, long corteId, long usuarioId);

    boolean esUsuarioSupervisorConFuncion(String funcionNumero, String usuarioNumero, String pwdEnc) throws javax.ejb.EJBException;

    /**
     * IMM 28/01/2009 **
     */
    java.lang.String getFechaInicialCorte(String dia);

    java.lang.String getFechaFinalCorte(String dia);

    boolean updateCortePendiente(long corteId, long usuarioId, Timestamp fechaAct, String pedoAnteriorCorte);

    java.lang.String buscaCortesFinDia(Timestamp fi, Timestamp ff);

    boolean updateCorteRealizado(long corteId, long usuarioId, Timestamp fechaAct, String esquema, String edoCorte);

    boolean updateCorteEnProceso(long corteId, long usuarioId, Timestamp fechaAct, String estado);


    public java.lang.String _ObtieneFechaHoraBD();

    public float getVentaNetaCorte(long corteId);

    public float getTAECorte(long corteId);

    public boolean corteDummyFinDia(long usuarioId);

    public java.lang.Object queryActividadSesionCorteFinDiaGetId();

    public long addSesionCorteFinDia(java.lang.String PrefijoTerminal, xertms.entidad.TmsSesionActividadesTbl tmsSesionActividadesTbl, java.lang.String ids, java.lang.String rt, java.lang.String rm, java.lang.String ra, java.lang.String rf, java.lang.String rs);

    public java.lang.String tmsAlgoritmoBanco(java.lang.String cadena,java.lang.String algoritmoVigente);
    //String cadena,String algoritmo

    public java.lang.String algoritmoBancoVigente();

    
}
