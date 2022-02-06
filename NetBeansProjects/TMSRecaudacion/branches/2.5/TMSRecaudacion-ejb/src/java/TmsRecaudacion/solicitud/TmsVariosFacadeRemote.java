package TmsRecaudacion.solicitud;

import TmsRecaudacion.entidad.TmsEmpresasTbl;
import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public abstract interface TmsVariosFacadeRemote
{
  public abstract Object BuscaTarjetasPendientes(String paramString1, String paramString2);

  public abstract Object BuscaCasetasTramo(String paramString1, String paramString2, String paramString3);

  public abstract Object BuscaKilometrosTramo(String paramString1, String paramString2, String paramString3);

  public abstract Object actualizarImportes(String paramString);

  public abstract Object fechaServidor();

  public abstract Object buscaCortePorUsuario(String paramString, long paramLong);

  public abstract Object buscarFuncionesPorMenuId(long paramLong);

  public abstract Object queryBuscaTerminalId();

  public abstract Object queryBuscaNumeroPaqueteActual();

  public abstract Object queryBuscaIdEmpresaByNombre(Object paramObject);

  public abstract TmsEmpresasTbl queryBuscaEmpresaPorId(BigDecimal paramBigDecimal);

  public abstract Object queryBuscaValorActualPago();

  public abstract Object buscaRegistroPagoPorDia(long paramLong, String paramString1, String paramString2);

  public abstract List<Object[]> queryBuscaOrigenServicioPorNumero(long paramLong);

  public abstract List<Object[]> queryBuscaDestinoServicioPorNumero(long paramLong);

  public abstract List<Object[]> queryBuscaCiudades();

  public abstract Object buscaEstadoPorId(long paramLong);

  public abstract Object queryBuscaTerminalLocal();

  public abstract Object buscarEstadoSesion(long paramLong);

  public abstract Object buscarVentaAbordo(String paramString);

  public abstract Object queryBuscaIdTerminal();

  public abstract Object buscaMontoAcumulado(long paramLong);

  public abstract Object buscaMontoRecolecciones(long paramLong1, long paramLong2);

  public abstract Object buscaBoletosDATAFARERecolectados(long paramLong);

  public abstract Object buscaMontoGastosPorTarjeta(String paramString);

  public abstract Object queryBuscaEstadoTarjetaViaje(String paramString);

  public abstract Object buscaVentaAbordoManual(long paramLong);

  public abstract Object buscaSaldos(long paramLong);

  public abstract Object consultaImportePorCorrida(long paramLong);

  public abstract Object consultaOcupacionPorCorrida(long paramLong);

  public abstract Object buscaTicket(long paramLong);

  public abstract Vector buscaSaldoOperador(String paramString);

  public abstract Object buscaMontoAcumuladoParaRestar(long paramLong);

  public abstract Object buscaMontoGastosPorTarjetaParaRestar(String paramString);

  public abstract Vector buscaUltimaRecaudacion(String paramString);

  public abstract Vector buscaCarteraUltimaRecaudacion(long paramLong);

  public abstract Vector buscaPuenultimaRecaudacion(String paramString, long paramLong);

  public abstract float buscaViaticos(String paramString);

  public abstract float buscaTarifaTarjeta(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

  public abstract TimeZone getTimeZone();

    public java.util.Vector getEstadoEstadoTarjetaViaxer(java.lang.String pFolioTarjeta);
}