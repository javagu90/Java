
package tms_ocupacion.solicitud;

import javax.ejb.Remote;
import tms_ocupacion.entidad.TmsTarjetasViajeTbl;


/**
 * This is the business interface for TmsVariosRemoto enterprise bean.
 */
@Remote
public interface TmsVariosRemotoFacadeRemote {
    java.util.List<tms_ocupacion.entidad.TmsCorridasVentaTbl> buscarCorridasProximas(String feha1, String fecha2, String operador, String servic, String bus, String DBLink);

    java.lang.Object buscarDatosRuta(String corrida, String DBLink);

    java.lang.Object numeroAsientosOcupadosNoDisponibles(long corridaId, String DBLink);

    java.lang.Object buscarNumTar(String operador, String servicio, String DBLink);

    java.lang.Object buscaDatosOcupacionPorSistema(String corrida, String DBLink);

    java.lang.Object queryBuscaValorActualTarjetaViaje(String DBLink);

    java.lang.Object queryBuscaNombreEsquema(String DBLink);

    java.lang.Object queryBuscaIdTerminal(String DBLink);

    tms_ocupacion.entidad.TmsCorridasVentaTbl buscaCorridaRemota(long idCorrida, String DBLink);

    tms_ocupacion.entidad.TmsTarjetasViajeTbl buscaTarjetaViajeExistente(long corridaId, String DBLink);

    tms_ocupacion.entidad.TmsCorridasTbl buscaCorridaSolaRemota(long idCorrida, String DBLink);

    boolean ActualizarCorrida(long idCorrida, long idEstado, String NombreEsquema, String DBLink);

    boolean ActualizarCorridaVenta(long idCorridaVenta, String letraEstado, String NombreEsquema, String DBLink);

    boolean ActualizarTarjetaExistente(long noAdulTarexist, double mtoAdulTarexist, long noMenTarexist, double mtoMenTarexist, long noProfTarexist, double mtoProfTarexist, long noSenTarexist, double mtoSenTarexist, long noEstudTarexist, double mtoEstudTarexist, long noEspTarexist, double mtoEspTarexist, long edotarexist, String nombreEsquema, String DBLink, long idTar, long user);

    java.util.Vector buscaTarjetaCreada(long idCorrida, String DBLink);

    java.lang.Object fechaServidor(String DBLink);

    java.util.List<tms_ocupacion.entidad.TmsCorridasVentaTbl> buscarCorridasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf, String DBLink);

    java.util.List<tms_ocupacion.entidad.TmsTarjetasViajeTbl> buscarTarjetasReimpresion(String autobus, String operador, String servicio, String fi, String ff, String hi, String hf, String DBLink);

    boolean modificarTarjetaReimpresion(TmsTarjetasViajeTbl tarjeta, String DBLink);

    tms_ocupacion.entidad.TmsTarjetasViajeTbl insertarTarjeta(TmsTarjetasViajeTbl tarjeta, String ter, String DBLink);

    boolean ActualizarFolioTarjeta(String DBLink, long idTar, String folio);

   

   
}
