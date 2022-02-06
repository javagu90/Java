
package tmsroles.solicitud;

import java.math.BigDecimal;
import javax.ejb.Remote;
import tmsroles.entidad.TmsCorridasTbl;
import tmsroles.entidad.TmsCorridasVentaTbl;
import tmsroles.entidad.TmsRolesBaseLineasTbl;
import tmsroles.entidad.TmsRolesBaseTbl;
import tmsroles.entidad.TmsRolesMaestroTbl;


/**
 * This is the business interface for VariosFacade enterprise bean.
 */
@Remote
public interface VariosFacadeRemote {
    java.lang.Object fechaServidor();

    java.lang.Object buscarOfertasPorServicioId(long servicioId);

    java.lang.Object buscarRutasPorOfertaServicio(long servicioId, String nombreOferta);

    java.lang.Object buscaHorarios(long idSer, long idRuta, String oferta);

    java.lang.Object buscaPlantllaefaultPorServicio(String serv);

    java.lang.Object buscaHorariosDeLaOfertadeServicios(long idSer, String oferta);


    /**
     * <code>select o from TmsCorridasTbl o where  o.corridaId = :corridaid</code>
     */
    java.util.List<tmsroles.entidad.TmsCorridasTbl> queryTmsCorridasTblFindById(Object corridaid);

    java.lang.Object queryBuscarBoletosReservados(long idCorrida);

    java.lang.Object queryBuscarBoletosVendidos(long idCorrida);

    void removeTmsCorridasTbl(TmsCorridasTbl tmsCorridasTbl);

    void updateCorrida(TmsCorridasTbl corridanueva, BigDecimal idcorridavieja, TmsCorridasVentaTbl corridaVenta);

    java.lang.Object queryBuscaNombreTerminal();

    java.lang.Object queryBuscaIdEstadoCorriaAbierta();

    void removeTmsCorridasVentaTbl(BigDecimal id);

    java.lang.Object buscaHorariosDeLaOfertaRolManual(long idSer, String oferta);

    java.lang.Object buscaFlotillas();

    java.lang.Object buscaAutobusesPorFlotilla(String flotillaNombre);

    tmsroles.entidad.TmsOfertasServicioTbl findOferServ(Object pk);

    java.lang.Object queryBuscaIdTerminal();

    java.util.List findAllFlotillas();

    tmsroles.entidad.TmsRolesMaestroTbl createRolMaestro(TmsRolesMaestroTbl tmsRolesMaestroTbl, String ter);

    void createRolBaseLineas(TmsRolesBaseLineasTbl tmsRolesBaseLineasTbl, String ter);

    tmsroles.entidad.TmsRolesBaseTbl createRolBase(TmsRolesBaseTbl tmsRolesBaseTbl, String ter);

    java.lang.Object buscarOperBusesporNumEcon(String bus);

    java.lang.Object buscaIdEdoPorNombre(String edoNombre);

    java.lang.Object buscaRolesBase(long rolMaestroId);

    void eliminarRolesBase(long rolMaestroId);

    void eliminarRolesBaseLineas(String rolesBaseIds);

    java.lang.Object buscaNombreRolesBase(long rolMaestroId);

    java.lang.Object buscaEdoPorId(long edoId);

    java.util.Vector buscaRolMaestroExistente(String nombre);

    java.lang.Object buscaPlantllasDefaultPorRuta();

    java.lang.Object queryBuscaIdEstadoCorridaCerrada();

    java.lang.Object buscarRolesBasesPorRolMaestro(long idrm);

    java.lang.Object buscarLineasBasesPorRolBase(long idrb);

    boolean eliminarLineasBasePorRolBaseId(long idrb);

    boolean eliminarRolesBasePorRolMaestroId(long idrm);

    boolean eliminarrRolMaestroPorId(long idrm);

    java.lang.Object queryBuscaCR(String fi, String ff, long idservicio, String oris, String des, String emps, String tutas);

    java.lang.Object buscarDatosRutas(String rutas);

    java.util.Vector buscaDiasOfertaPorId(long ofertaId);

    java.lang.Object queryBuscarPasajeros(long idCorrida);


    
}
