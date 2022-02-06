/*
 * TmsCajasTblFacadeRemote.java
 *
 * Created on 3 de septiembre de 2007, 05:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.solicitud;

import javax.ejb.Remote;
import tmspuertas.entidad.TmsAutobusesTbl;
import tmspuertas.entidad.TmsCorridasTbl;
import tmspuertas.entidad.TmsCorridasVentaTbl;
import tmspuertas.entidad.TmsOperadoresTbl;
import tmspuertas.entidad.TmsTarjetasViajeTbl;


/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsFacadeGeneralPuertasRemote {
    java.util.Vector buscarDatosImpresoraPorCaja(long cajaId);
    java.util.Vector buscarDatosCajaPorEquipo(String nombreEquipo);
    long buscaPermitirCrearTarjeta(String clave, String servicio, String DBLink);

    java.lang.Object buscarNombreOperador(String clave);

    long numeroAsientosOcupadosNoDisponibles(long corridaId);

    java.lang.String AsientosPorCorrida(long corridaId);

    java.lang.Object buscaTarjetaViajeExistenteRemoto(String DBLink, String folTar);

    java.lang.Object buscaTarjetaViajeExistente(String folTar);

    boolean ActualizarTarjetaExistenteRemota(long noAdulTarexist, double mtoAdulTarexist, long noMenTarexist, double mtoMenTarexist, long noProfTarexist, double mtoProfTarexist, long noSenTarexist, double mtoSenTarexist, long noEstudTarexist, double mtoEstudTarexist, long noEspTarexist, double mtoEspTarexist, long edotarexist, String nombreEsquema, String DBLink, long idTar, long user);

    boolean ActualizarTarjetaExistente(long noAdulTarexist, double mtoAdulTarexist, long noMenTarexist, double mtoMenTarexist, long noProfTarexist, double mtoProfTarexist, long noSenTarexist, double mtoSenTarexist, long noEstudTarexist, double mtoEstudTarexist, long noEspTarexist, double mtoEspTarexist, long edotarexist, String nombreEsquema, long idTar, long user);

    java.lang.Object queryBuscaNombreEsquemaRemoto(String DBLink);

    java.lang.Object queryBuscaNombreEsquema();

    long queryBuscaEstadoTarjetaViaje();

    java.lang.Object queryBuscaIdTerminal();

    java.lang.Object queryBuscaIdTerminalRemota(String DBLink);

    java.util.Vector buscaEstadosAcciones();

    java.util.Vector buscaNombreServicios();

    java.lang.Object fechaServidor();
    
    java.lang.Object fechaServidorManana();

    java.lang.Object buscaDBLinkCentral();

    java.lang.Object buscarTiposPasajero();

    java.lang.Object buscaParametrosPorServicio();

    java.lang.Object buscaParametrosPorRuta();

    java.lang.Object parametrosEmpresas();

    java.util.Vector buscarCorridasProximas(String feha1, String fecha2, String operador, String servic, String bus);

    java.util.Vector buscarCorridasProximasRemoto(String feha1, String fecha2, String operador, String servic, String bus, String DBLink);

    java.lang.Object buscarDatosRutaRemoto(String corrida, String DBLink);

    java.lang.Object buscarDatosRuta(String corrida);

    java.lang.Object numeroAsientosOcupadosNoDisponiblesRemoto(long corridaId, String DBLink);

    java.lang.String buscaFuncion(String usuarioNumero, String funcion);

    java.util.Vector buscaEsquemasTerminales(String esq);

    java.lang.String esUsuarioSupervisor(String usuarioNumero, String pwdEnc, String funcion);

    java.lang.Object buscaDatosOcupacionPorSistema(String corrida);

    java.lang.Object buscaDatosOcupacionPorSistemaRemoto(String corrida, String DBLink);

    java.util.List<tmspuertas.entidad.TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll();

    /**
     * 
     * 
     * @param pId
     * @return 
     */
    tmspuertas.entidad.TmsAutobusPlantillasEncTbl queryTmsAutobusPlantillasEncTblFindById(Long pId);


    java.util.List<tmspuertas.entidad.TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindAll();

    /**
     * 
     * 
     * @param pId
     * @return 
     */
    java.util.List<tmspuertas.entidad.TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindById(Long pId);

    /**
     * 
     * 
     * @return 
     */
    java.util.List<tmspuertas.entidad.TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll();

    tmspuertas.entidad.TmsCorridasVentaTbl buscaCorridaRemota(long idCorrida, String DBLink);

    boolean modificarTarjetaReimpresion(TmsTarjetasViajeTbl tarjeta, String DBLink);

    tmspuertas.entidad.TmsTarjetasViajeTbl createTarjetaViaje(TmsTarjetasViajeTbl tmsTarjetasViajeTbl, String ter);

    void editTarjetaViaje(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    java.lang.Object buscarEstadoSesion(long sesionId);

    tmspuertas.entidad.TmsTarjetasViajeTbl buscaTarjetaViajeExistenteVistaPrevia(long corridaId);

    tmspuertas.entidad.TmsTarjetasViajeTbl buscaTarjetaViajeExistenteVistaPreviaRemoto(long corridaId, String DBLink);

    java.util.List<tmspuertas.entidad.TmsEstadosCorridaTbl> buscarPorLetra(String letra);

    tmspuertas.entidad.TmsCorridasTbl buscaCorridaSolaRemota(long idCorrida, String DBLink);

    tmspuertas.entidad.TmsCorridasTbl findCorrida(Object pk);

    tmspuertas.entidad.TmsCorridasVentaTbl findCorridaVenta(Object pk);

    void editAutobus(TmsAutobusesTbl tmsAutobusesTbl);

    void editOperador(TmsOperadoresTbl tmsOperadoresTbl);

    tmspuertas.entidad.TmsAccionesTbl findAccion(Object pk);

    tmspuertas.entidad.TmsAccionesTbl buscaAccionPorNombre(String acc);

    tmspuertas.entidad.TmsEstadosTbl buscaEstadoPorNombre(String edo);

    java.lang.Object queryBuscaTerminalOperador();

    tmspuertas.entidad.TmsOperadoresTbl buscaOperadorPorNombre(String clave);

    tmspuertas.entidad.TmsAutobusesTbl buscaBusPorNumero(String num);

    java.lang.Object buscaNumeroUsuario(long usuarioId);

    tmspuertas.entidad.TmsTarjetasViajeTbl insertarTarjetaRemoto(TmsTarjetasViajeTbl tarjeta, String ter, String DBLink);

    java.util.Vector buscaTarjetaCreadaRemoto(long idCorrida, String DBLink);

    boolean ActualizarFolioTarjetaRemoto(String DBLink, long idTar, String folio);

    boolean ActualizarFolioTarjeta(long idTar, String folio);

    java.util.List<tmspuertas.entidad.TmsTarjetasViajeTbl> buscarTarjetasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf);

    java.util.List<tmspuertas.entidad.TmsCorridasVentaTbl> buscarCorridasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf);

    java.util.List<tmspuertas.entidad.TmsCorridasVentaTbl> buscarCorridasReimpresionRemoto(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf, String DBLink);

    java.util.List<tmspuertas.entidad.TmsTarjetasViajeTbl> buscarTarjetasReimpresionRemoto(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf, String DBLink);

    java.util.Vector buscaBusPorNumeroParaAsignar(String numEcon, String empresa);

    java.util.Vector buscaOperadorPorClaveParaAsignar(String clave, String empresa);

    java.util.Vector buscaEmpresas();

    java.lang.Object queryBuscaValorActualTarjetaViajeRemoto(String DBLink);

    java.lang.Object queryBuscaValorActualTarjetaViaje();

    java.lang.Object queryBuscaNombreTerminal();

    void actualizarEstadosOperBus(String claveOper, String numEcon, String DBLink, long remoto, String empresa, String esquema);

    int ActualizarCorridaVenta(long idCorridaVenta, String letraEstado, String NombreEsquema, String adicional4, String DBLink);

    int ActualizarCorrida(long idCorrida, long idEstado, String NombreEsquema, String adicional4, String DBLink);

    int editCorrida(TmsCorridasTbl tmsCorridasTbl);

    int editCorridaVenta(TmsCorridasVentaTbl tmsCorridasVentaTbl);

    int ActualizarCorridasAsignacionRemoto(long idCorrida, long idAutobus, String numEconBus, String claveOper, long idOper, String nombreEsquema, long usr, String DBLink, String empresa, long idEmpresa);

    int ActualizarCorridasAsignacion(long idCorrida, long idAutobus, String numEconBus, String claveOper, long idOper, String nombreEsquema, long usr, String empresa, long idEmpresa);

    public Object consultaOcupacionPorCorrida(long corridaid);
    
    java.util.Vector buscaIdServicio(String servicio);
    
    java.util.Vector buscaServicioEmpresas(long idServicio, long idEmpresa, long idRuta);

    public java.lang.String getValidaIncicenciaOper(java.lang.String p_clave_operador, java.lang.String p_fecha_hora);

}
