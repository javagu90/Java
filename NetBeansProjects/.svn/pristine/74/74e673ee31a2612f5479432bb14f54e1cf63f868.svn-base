
package tms_ocupacion.solicitud;

import java.util.List;
import javax.ejb.Remote;
import tms_ocupacion.entidad.TmsAutobusPlantLineasTbl;
import tms_ocupacion.entidad.TmsAutobusPlantillasEncTbl;
import tms_ocupacion.entidad.TmsComponenteBusTbl;


/**
 * This is the business interface for TmsVariosFacade enterprise bean.
 */
@Remote
public interface TmsVariosFacadeRemote {

    java.lang.Object actualizarImportes(String claveCorrida);

    java.lang.Object fechaServidor();

    java.lang.Object queryBuscaEsquemaLocal();

    java.lang.Object queryBuscaTerminal();
    
    // plantilla
    
    /**
     * <code>select o from TmsComponenteBusTbl o where o.tipo<>-1</code>
     * @return 
     */
    List<TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll();

    /**
     * 
     * @param pId 
     * @return 
     */
    TmsAutobusPlantillasEncTbl queryTmsAutobusPlantillasEncTblFindById(Long pId);

    /**
     * 
     * @return 
     */
    List<TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll();
    
    List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindAll();

    /**
     * 
     * @param pId 
     * @return 
     */
    List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindById(Long pId);

    java.lang.Object buscarTiposPasajero();

    java.lang.Object buscaParametrosPorServicio();

    java.lang.Object buscaDatosOcupacionPorSistema(String corrida);

    java.lang.Object buscarDatosRuta(String corrida);

    java.lang.Object buscarNombreOperador(String clave);

    java.lang.Object queryBuscaValorActualTarjetaViaje();

    java.util.List<tms_ocupacion.entidad.TmsTarjetasViajeTbl> buscarTarjetasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf);

    java.util.List<tms_ocupacion.entidad.TmsCorridasVentaTbl> buscarCorridasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf);

    java.lang.Object buscarParametroNumMaxTar(String servicios);

    java.lang.String buscaFuncion(String usuarioNumero, String funcion);

    java.lang.String esUsuarioSupervisor(String usuarioNumero, String pwdEnc, String funcion);

    java.lang.Object buscarEstadoSesion(long sesionId);

    java.lang.Object numeroAsientosOcupadosNoDisponibles(long corridaId);

    java.lang.Object buscaNumeroUsuario(long usuarioId);

    java.lang.Object buscaParametrosPorRuta();

    java.lang.Object queryBuscaIdTerminal();

    java.lang.Object queryBuscaNombreEsquema();

    java.lang.Object queryBuscaEstadoTarjetaViaje();

    tms_ocupacion.entidad.TmsTarjetasViajeTbl buscaTarjetaViajeExistente(long corridaId);

    java.lang.Object buscaDBLinkCentral();

    java.lang.Object buscaTarjetaViajeExistenteCentral(String DBLink, String folTar);

    java.lang.Object queryBuscaTerminalOperador();

    java.lang.Object buscarNumTar(String operador, String servicio, String DBLinkCentral);

    java.lang.Object parametrosEmpresas();

    java.lang.Object buscaEncabezados(String tabla);

    java.lang.String buscaPermisoIngreso(String usuarioNumero, String funcion);

    java.lang.Object buscaDatosOcupacionPorSistemaRemoto(String corrida, String dblink);

    java.lang.Object buscarDatos(String autobus, String operador, String servicio, String empresa, String origen, String destino, String fi, String ff, String hi, String tabla, String rutas, boolean extras);

    java.util.Vector buscaRutasPerfil(long usuarioId);

    java.util.Vector buscaDestinos();

    public java.lang.Object fechaServidor2();
 
   
}
