package mx.com.estrellaroja.TMSFacturaElectronicaWS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author cpalalia (cpalalia@estrellaroja.com.mx)
 */
@WebService()
@Stateless()
public class TMSFacturaEWS {
    //<editor-fold defaultstate="collapse" desc="VARIABLES Y CONSTANTES">

    @Resource(name = "TMS_CENTRAL_DB")
    private DataSource TMS_DB;
    
    @Resource(name = "TICKETS_DB")
    private DataSource TICKETS_DB;
    
    @Resource(name = "REP_CONTROL_DB")
    private DataSource dataSource_ER_CONTROL;

    @Resource(name = "PAQER_DB")
    private DataSource PAQUER_DB;

    
    private Connection connection;
    private ResultSet resultSet;
    private CallableStatement callableStatement;
    private PreparedStatement preparedStatement;
    private String query;
    private final int TIPO_CLIENTE_ID = 1;
    private final int USUARIO_WEB = 1;
    private final int CLIENTE_RELACIONADO = 0;
    private final String REPLICACION_ESTADO = "P";
    private final String REPLICACION_ORIGEN = "O";
    private final String ESTADO_FACTURA = "FACTURADO";
    private final int REPLICACION_INTENTOS = 0;
    public final static boolean CONSULTA_EXITOSA = true;
    private final int FIRST_COLUMN = 1;
    private final int CREADO_POR_ADMINISTRADOR = 1;
    //XER_TMS_PKG3
    private String DB_PACKAGE_NAME = "XER_TMS_PKG2.";
    private String PREFIJO_FOLIO_FACTURA = "200000";

    private Logger log = Logger.getLogger("wsFacturacion");

    String SMTP_AUTH_USER = null;
    String SMTP_AUTH_PWD = null;

    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="WEB METHODS">
    @WebMethod(operationName = "getInformacionBoleto")
    /**
     * @param FECHA_VENTA ==> Fecha de la venta del boleto en formato DD/MM/RRRR
     * @param CODIGO_SEGURIDAD ==> Código de seguridad(aín esta por definir de donde se tomará
     * @param P_REFERENCIA_BOLETO ==> Referencia del boleto a validar
     * @param P_FOLIO_PREIMPRESO ==> Folio Preimpreso del boleto a validar
     *
     */
    public Boleto getInformacionBoleto(@WebParam(name = "P_REFERENCIA_BOLETO") final String P_REFERENCIA_BOLETO,
            @WebParam(name = "P_FOLIO_PREIMPRESO") String P_FOLIO_PREIMPRESO,
            @WebParam(name = "FECHA_VENTA") String FECHA_VENTA, @WebParam(name = "CODIGO_SEGURIDAD") String CODIGO_SEGURIDAD,
            @WebParam(name = "ORIGEN") String ORIGEN, @WebParam(name = "DESTINO") String DESTINO,
            @WebParam(name = "ASIENTO") String ASIENTO, @WebParam(name = "SERVICIO") String SERVICIO, @WebParam(name = "FOLIOS_AGREGADOS") String FOLIOS_AGREGADOS) {
      //setQuery("CALL XER_TMS_PKG3.TMS_VALIDA_BOL_FACT_WEB_PRC(?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        setQuery("CALL XER_TMS_PKG3.TMS_VALIDA_BOL_FACT_WEB_PRC(?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Boleto boleto = new Boleto();
        log.log(Level.INFO, "-------- Inicia getInformacionBoleto --------");
        try {
            setConnection(TMS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));
            getCallableStatement().setString(1, FECHA_VENTA);
            getCallableStatement().setString(2, CODIGO_SEGURIDAD);
            getCallableStatement().setString(3, P_REFERENCIA_BOLETO);
            getCallableStatement().setString(4, P_FOLIO_PREIMPRESO);
            getCallableStatement().setString(5, ORIGEN);
            getCallableStatement().setString(6, DESTINO);
            getCallableStatement().setString(7, ASIENTO);
            getCallableStatement().setString(8, SERVICIO);
            getCallableStatement().registerOutParameter(9, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(10, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(11, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(12, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(13, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(14, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(15, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(16, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(17, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(18, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(19, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(20, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(21, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(22, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(23, java.sql.Types.VARCHAR);
            getCallableStatement().setString(24, FOLIOS_AGREGADOS);
            getCallableStatement().registerOutParameter(25, java.sql.Types.VARCHAR);
            //getCallableStatement().registerOutParameter(23, java.sql.Types.VARCHAR);
            //getCallableStatement().registerOutParameter(24, java.sql.Types.VARCHAR);

            getCallableStatement().executeUpdate();
            System.out.println("P_FOLIO_PREIMPRESO: "+P_FOLIO_PREIMPRESO);
            System.out.println("Valido: "+getCallableStatement().getString(9));
            System.out.println("mesnaje: "+getCallableStatement().getString(10));
            if (Boolean.valueOf(getCallableStatement().getString(9)) == CONSULTA_EXITOSA) {

                boleto.setClaveCorrida(getCallableStatement().getString(11));
                boleto.setNombrePasajero(getCallableStatement().getString(12));
                boleto.setTipoPasajero(getCallableStatement().getString(13));
                boleto.setNumeroAsiento((getCallableStatement().getString(14)) == null ?-1 :Integer.valueOf(getCallableStatement().getString(14)));
                boleto.setFechaHoraCorrida((getCallableStatement().getString(15)==null) ?"" :getCallableStatement().getString(15));
                boleto.setFechaHoraVenta((getCallableStatement().getString(16)==null) ?"" :getCallableStatement().getString(16));
                boleto.setImporte(Double.parseDouble(getCallableStatement().getString(17)));
                boleto.setOrigen(getCallableStatement().getString(18));
                boleto.setDestino(getCallableStatement().getString(19));
                boleto.setServicio(getCallableStatement().getString(20));
                boleto.setpFolio(getCallableStatement().getString(21));
                boleto.setBoletoId(getCallableStatement().getString(22));
                boleto.setTipoPago(getCallableStatement().getString(25));
                //boleto.setNoCuenta(getCallableStatement().getString(24));
                boleto.setStatus(CONSULTA_EXITOSA);
                boleto.setMensaje(getCallableStatement().getString(10));
                boleto.setSubTotal(boleto.getImporte());
                boleto.setTotal(boleto.getSubTotal() + boleto.getIva());
                String sucursalesTTP = "'CAPSETEX','CHALCO','CHOL','HUEJO','JCORONACO','MHIDALGO','SMA','SRRFRIO','SRSMA','SUPER','TIANG'";
                System.out.println("Ciudad Venta: "+getCallableStatement().getString(23));
                if (sucursalesTTP.indexOf("'"+getCallableStatement().getString(23)+"'")>=0)
                    boleto.setEmpresaFactura("SMA");
                else
                    boleto.setEmpresaFactura("CAPU");
            } else {
                boleto.setStatus(!CONSULTA_EXITOSA);
                boleto.setMensaje(getCallableStatement().getString(10));
            }
            


            //---------- Busca Desglose de IVA
            if (Boolean.valueOf(getCallableStatement().getString(9)) == CONSULTA_EXITOSA)
            {
                setQuery("CALL XER_TMS_PKG3.get_datos_iva_boletos_prc(?,?,?,?,?,?,?,?)");
                //setConnection(TMS_DB.getConnection());
                setCallableStatement(getConnection().prepareCall(getQuery()));
                getCallableStatement().setString(1, P_FOLIO_PREIMPRESO);
                getCallableStatement().registerOutParameter(2, java.sql.Types.VARCHAR);
                getCallableStatement().registerOutParameter(3, java.sql.Types.VARCHAR);
                getCallableStatement().registerOutParameter(4, java.sql.Types.VARCHAR);
                getCallableStatement().registerOutParameter(5, java.sql.Types.VARCHAR);
                getCallableStatement().registerOutParameter(6, java.sql.Types.VARCHAR);
                getCallableStatement().registerOutParameter(7, java.sql.Types.VARCHAR);
                getCallableStatement().setString(8, "WS");
                getCallableStatement().executeUpdate();
                log.log(Level.INFO, "   -- Desglose IVA "+P_FOLIO_PREIMPRESO+" ---- ");
                log.log(Level.INFO, "       P_VALIDO: "+getCallableStatement().getString(6));
                log.log(Level.INFO, "       P_MENSAJE: "+getCallableStatement().getString(7));
                if (Boolean.valueOf(getCallableStatement().getString(6)) == CONSULTA_EXITOSA) {
                    double P_IMPORTE_BOLETO = Double.parseDouble(getCallableStatement().getString(2));
                    double P_IMPORTE_IVA_BOLETO = Double.parseDouble(getCallableStatement().getString(3));
                    double P_IMPORTE_ENTRADA =Double.parseDouble(getCallableStatement().getString(4));
                    double P_PORCENTAJE_IVA =Double.parseDouble(getCallableStatement().getString(5));
                    double total_boleto = P_IMPORTE_BOLETO+P_IMPORTE_ENTRADA;

                    log.log(Level.INFO, "       P_IMPORTE_BOLETO: "+P_IMPORTE_BOLETO);
                    log.log(Level.INFO, "       P_IMPORTE_IVA_BOLETO: "+P_IMPORTE_IVA_BOLETO);
                    log.log(Level.INFO, "       P_IMPORTE_ENTRADA: "+P_IMPORTE_ENTRADA);
                    log.log(Level.INFO, "       P_PORCENTAJE_IVA: "+P_PORCENTAJE_IVA);

                    //Desglosa el importe total del boleto
                    boleto.setPorcentajeIva(P_PORCENTAJE_IVA);
                    boleto.setIva(P_IMPORTE_IVA_BOLETO );
                    boleto.setImporteIva(P_IMPORTE_IVA_BOLETO );
                    boleto.setImporte(total_boleto- boleto.getImporteIva());
                    boleto.setSubTotal(total_boleto- boleto.getImporteIva());
                    boleto.setTotal(boleto.getSubTotal() + boleto.getImporteIva());
                    //Desglosa la parte del Boleto
                    boleto.setBoletoporcentajeIva(P_PORCENTAJE_IVA);
                    boleto.setBoletoimporteIva(P_IMPORTE_IVA_BOLETO);
                    boleto.setBoletosubTotal(P_IMPORTE_BOLETO-P_IMPORTE_IVA_BOLETO);
                    boleto.setBoletototal(P_IMPORTE_BOLETO);
                    //Desglosa la parte de la entrada
                    if(P_IMPORTE_ENTRADA>0)
                    {
                        boleto.setEntradaporcentajeIva(0);
                        boleto.setEntradaimporteIva(0);
                        boleto.setEntradasubTotal(P_IMPORTE_ENTRADA);
                        boleto.setEntradatotal(P_IMPORTE_ENTRADA);

                    }
                    else
                    {
                        boleto.setEntradaporcentajeIva(0);
                        boleto.setEntradaimporteIva(0);
                        boleto.setEntradasubTotal(0);
                        boleto.setEntradatotal(0);
                    }
                }
            } 
            
            //----------
            log.log(Level.INFO, "-------- Finalizado getInformacionBoleto --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception getInformacionBoleto --------", e);
        } finally {
            //log.log(Level.SEVERE, "-------- Regresa el Boleto con folio "+ boleto.getpFolio()+" --------");
            releaseTransaction();
            return boleto;

        }
    }

    @WebMethod(operationName = "getDatosCliente")
    public Cliente getDatosCliente(@WebParam(name = "rfcCliente") String rfcCliente) {
        Cliente cliente = null;
        try {
            log.log(Level.INFO, "-------- Inicia getDatosCliente --------");
            cliente = new Cliente();
            setConnection(TMS_DB.getConnection());
            //setQuery("select * from tms_clientes_tbl where UPPER(rfc) = ?");
            setQuery("select "
                    + "CLIENTE_ID, "
                    + "NOMBRE, "
                    + "'' APELLIDOS, "
                    + "'' CONTACTO, "
                    + "CALLE, "
                    + "NO_INT NUMERO_INT, "
                    + "NO_EXT NUMERO_EXT, "
                    + "COLONIA, "
                    + "CODIGO_POSTAL, "
                    + "CIUDAD DELEG_MUN, "
                    + "CIUDAD, "
                    + "ESTADO, "
                    + "'' TELEFONO_CASA, "
                    + "'' TELEFONO_OFICINA, "
                    + "'' FECHA_NAC, "
                    + "'' SEXO, "
                    + "'' ESTADO_CIVIL, "
                    + "'' COMPANIA, "
                    + "'' PUESTO, "
                    + "EMAIL, "
                    + "'' SALDO_PUNTOS, "
                    + "'' FECHA_ULTIMA_ACUMULACION, "
                    + "'' FECHA_ULTIMO_CANJE, "
                    + "'' TIPO_CLIENTE_ID, "
                    + "'' FECHA_DESDE, "
                    + "'' FECHA_HASTA, "
                    + "'' CREADO_POR, "
                    + "'' FECHA_CREACION, "
                    + "'' ULTIMA_ACTUALIZACION_POR, "
                    + "'' ULTIMA_FECHA_ACTUALIZACION, "
                    + "'' REPLICACION_ESTADO, "
                    + "'' REPLICACION_INTENTOS, "
                    + "'' REPLICACION_ORIGEN, "
                    + "'' CONTRASENIA, "
                    + "'' TELEFONO_CELULAR, "
                    + "'' NO_PASAPORTE, "
                    + "'' PAIS, "
                    + "'' TELEFONO_FAX, "
                    + "RFC  "
                    + ",-1 CLIENTE_RELACIONADO_ID "
                    + "from ER_CLIENTES_TBL@REPCONTROL_LINK.ESTRELLAROJA.COM.MX "
                    + "WHERE UPPER(rfc) =  ?");
            setPreparedStatement(getConnection().prepareStatement(getQuery()));
            getPreparedStatement().setString(1, rfcCliente.toUpperCase());
            setResultSet(getPreparedStatement().executeQuery());

            while (getResultSet().next()) {
                cliente.setApellidos(getResultSet().getString("APELLIDOS"));
                cliente.setCalle(getResultSet().getString("CALLE"));
                cliente.setCiudad(getResultSet().getString("CIUDAD"));
                cliente.setClienteId(getResultSet().getInt("CLIENTE_ID"));
                cliente.setClienteRelacionado(getResultSet().getInt("CLIENTE_RELACIONADO_ID"));
                cliente.setCodigoPostal(getResultSet().getString("CODIGO_POSTAL"));
                cliente.setColonia(getResultSet().getString("COLONIA"));
                cliente.setCompania(getResultSet().getString("COMPANIA"));
                cliente.setContacto(getResultSet().getString("CONTACTO"));
                cliente.setContrasenia(getResultSet().getString("CONTRASENIA"));
                cliente.setCreadoPor(getResultSet().getInt("CREADO_POR"));
                cliente.setDelegacionMunicipio(getResultSet().getString("DELEG_MUN"));
                cliente.setEmail(getResultSet().getString("EMAIL"));
                cliente.setEstado(getResultSet().getString("ESTADO"));
                cliente.setEstadoCivil(getResultSet().getString("ESTADO_CIVIL"));
                cliente.setFechaCreacion(getResultSet().getString("FECHA_CREACION"));
                cliente.setFechaDesde(getResultSet().getString("FECHA_DESDE"));
                cliente.setFechaHasta(getResultSet().getString("FECHA_HASTA"));
                cliente.setFechaNacimiento(getResultSet().getString("FECHA_NAC"));
                cliente.setFechaUltimaAcumulacion(getResultSet().getString("FECHA_ULTIMA_ACUMULACION"));
                cliente.setFechaUltimoCanje(getResultSet().getString("FECHA_ULTIMO_CANJE"));
                cliente.setNombre(getResultSet().getString("NOMBRE"));
                cliente.setNumeroExterior(getResultSet().getString("NUMERO_EXT"));
                cliente.setNumeroInterior(getResultSet().getString("NUMERO_INT"));
                cliente.setNumeroPasaporte(getResultSet().getString("NO_PASAPORTE"));
                cliente.setPais(getResultSet().getString("PAIS"));
                cliente.setPuesto(getResultSet().getString("PUESTO"));
                cliente.setRfc(getResultSet().getString("RFC"));
                cliente.setSaldoPuntos(getResultSet().getInt("SALDO_PUNTOS"));
                cliente.setSexo(getResultSet().getString("SEXO"));
                cliente.setTelefonoCasa(getResultSet().getString("TELEFONO_CASA"));
                cliente.setTelefonoCelular(getResultSet().getString("TELEFONO_CELULAR"));
                cliente.setTelefonoFax(getResultSet().getString("TELEFONO_FAX"));
                cliente.setTelefonoOficina(getResultSet().getString("TELEFONO_OFICINA"));
                cliente.setTipoCliente(getResultSet().getInt("TIPO_CLIENTE_ID"));
            }
            log.log(Level.INFO, "-------- Finaliza getDatosCliente --------");
        } catch (Exception ex) {
            log.log(Level.INFO, "-------- Inicia getDatosCliente --------", ex);
        } finally {
            releaseTransaction();
            return cliente;
        }

    }

    @WebMethod(operationName = "getInformacionServicios")
    public List<Servicio> getInformacionServicios() {
        List<Servicio> servicios = new ArrayList<Servicio>();
        try {
            log.log(Level.INFO, "-------- Inicia getInformacionServicios --------");
            setQuery("select servicio_id, servicio_clave, "
                    + "servicio_numero, servicio_nombre, "
                    + "descripcion from tms_servicios_tbl WHERE servicio_nombre NOT IN('COMBINADO', 'PISTA INTERMEDIO',"
                    + "'SERVICIO TIJ-SDIEGO-TIJ', 'SUPER RAPIDOS PISTA', 'URBANO BICENTENARIO')"
                    + " order by servicio_nombre");
            setConnection(TMS_DB.getConnection());
            setPreparedStatement(getConnection().prepareStatement(getQuery()));
            setResultSet(getPreparedStatement().executeQuery());
            while (getResultSet().next()) {
                Servicio servicio = new Servicio();
                servicio.setServicioId(getResultSet().getInt("servicio_id"));
                servicio.setServicioClave(getResultSet().getString("servicio_clave"));
                servicio.setServicioNumero(getResultSet().getInt("servicio_numero"));
                servicio.setServicioNombre(getResultSet().getString("servicio_nombre"));
                servicio.setDescripcion(getResultSet().getString("descripcion"));
                servicios.add(servicio);
            }
            log.log(Level.INFO, "-------- Finalizado getInformacionServicios --------");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "-------- Exception getInformacionServicios --------", ex);
        } finally {
            releaseTransaction();
            return servicios;
        }
    }

    /**
     * @author jmendoza
     * Obtiene una lista de servicios
     * @return Una lista de servicios
     *
     */
    @WebMethod(operationName = "getInformacionServiciosProcedure")
    public List<Servicio> getInformacionServiciosProcedure() {
        List<Servicio> servicios = new ArrayList<Servicio>();
        try {
            log.log(Level.INFO, "-------- Inicia getInformacionServiciosProcedure --------");
            setConnection(TMS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall("BEGIN XER_TMS_PKG3.OBTENER_INF_SERVICIOS_PRC(?,?); END;"));
            getCallableStatement().registerOutParameter(1, OracleTypes.CURSOR); //REF CURSOR
            getCallableStatement().registerOutParameter(2, Types.VARCHAR);
            getCallableStatement().execute();
            ResultSet rs = ((OracleCallableStatement) callableStatement).getCursor(1);
            //String mensaje = rs.getString(2);
            //log.log(Level.INFO, "MENSAJE: " + mensaje);
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setServicioId(rs.getInt("servicio_id"));
                servicio.setServicioClave(rs.getString("servicio_clave"));
                servicio.setServicioNumero(rs.getInt("servicio_numero"));
                servicio.setServicioNombre(rs.getString("servicio_nombre"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicios.add(servicio);
            }
            rs.close();
            rs = null;
            log.log(Level.INFO, "-------- Finalizado getInformacionServiciosProcedure --------");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "-------- Exception getInformacionServiciosProcedure --------", ex);
        } finally {
            releaseTransaction();
        }
        return servicios;
    }

    @WebMethod(operationName = "getEstados")
    public List getEstados() {
        List<String> listEstados = new ArrayList<String>();
        try {
            log.log(Level.INFO, "-------- Inicia getEstados --------");
            setConnection(TMS_DB.getConnection());
            setQuery("select DESCRIPCION, ESTADO_NOMBRE || ' - ' || DESCRIPCION As Estado_Nombre from tms_estados_tbl where tipo_componente IN "
                    + "('TERMINAL', 'CIUDAD' , 'UBICACION') AND Estado_Nombre NOT IN('AEROT2', 'AFRPM', 'AFRPM', 'CENTRAL',"
                    + " 'CHAPM', 'CIUPM', 'CRAICM', 'FRANQ', 'GRAPM', 'MOVIL', 'SIXPM', 'STAFEM', 'TEOPM', 'TOREOM',"
                    + "'TTDESTINO', 'TTORIGEN', 'VILLAM', 'WEB', 'WTCM') ORDER BY ESTADO_NOMBRE");
            setPreparedStatement(getConnection().prepareStatement(getQuery()));
            setResultSet(getPreparedStatement().executeQuery());
            String estadoNombreDescripcion = "";
            while (getResultSet().next()) {
                estadoNombreDescripcion = getResultSet().getString("ESTADO_NOMBRE");
                //estadoNombreDescripcion += getResultSet().getString("DESCRIPCION");
                listEstados.add(estadoNombreDescripcion);
            }
            log.log(Level.INFO, "-------- Finaliza getEstados --------");
        } catch (Exception ex) {
            log.log(Level.INFO, "-------- Exception getEstados --------", ex);
        } finally {
            releaseTransaction();
            return listEstados;
        }
    }

    /**
     * @author jmendoza
     * Obtiene una lista de estados
     * @return Una lista de estados
     *
     */
    @WebMethod(operationName = "getEstadosProcedure")
    public List<String> getEstadosProcedure() {
        List<String> listEstados = new ArrayList<String>();     
        Connection connect = null;
        CallableStatement callableStat = null;
        try {
            log.log(Level.INFO, "-------- Inicia getEstadosProcedure --------");
            //setConnection(TMS_DB.getConnection());
            connect = TMS_DB.getConnection();
            //Obtiene el Top Ten de los destinos facturados
            //setCallableStatement(getConnection().prepareCall("BEGIN XER_TMS_PKG3.OBTENER_TOP_TEN_ESTADOS_PRC(?,?); END;"));
//            getCallableStatement().registerOutParameter(1, OracleTypes.CURSOR); //REF CURSOR
//            getCallableStatement().registerOutParameter(2, Types.VARCHAR);
//            getCallableStatement().execute();
            callableStat =  connect.prepareCall("BEGIN XER_TMS_PKG3.OBTENER_TOP_TEN_ESTADOS_PRC(?,?); END;");
            callableStat.registerOutParameter(1, OracleTypes.CURSOR); //REF CURSOR
            callableStat.registerOutParameter(2, Types.VARCHAR);
            callableStat.execute();
            ResultSet rs = ((OracleCallableStatement) callableStat).getCursor(1);
            //String mensaje = rs.getString(2);
            //log.log(Level.INFO, "MENSAJE: " + mensaje);
            String estadoNombreDescripcion = "";
            while (rs.next()) {
                estadoNombreDescripcion = rs.getString("ESTADO_NOMBRE");
                //System.out.println("Primeros ESTADO_NOMBRE: "+estadoNombreDescripcion);
                listEstados.add(estadoNombreDescripcion);
            }
            //Obtiene el resto de los destinos
//            setCallableStatement(getConnection().prepareCall("BEGIN XER_TMS_PKG3.OBTENER_ESTADOS_PRC(?,?); END;"));
//            getCallableStatement().registerOutParameter(1, OracleTypes.CURSOR); //REF CURSOR
//            getCallableStatement().registerOutParameter(2, Types.VARCHAR);
//            getCallableStatement().execute();
              callableStat = connect.prepareCall("BEGIN XER_TMS_PKG3.OBTENER_ESTADOS_PRC(?,?); END;");
              callableStat.registerOutParameter(1, OracleTypes.CURSOR); //REF CURSOR
              callableStat.registerOutParameter(2, Types.VARCHAR);
              callableStat.execute();
             rs = ((OracleCallableStatement) callableStat).getCursor(1);
            //String mensaje = rs.getString(2);
            //log.log(Level.INFO, "MENSAJE: " + mensaje);
             estadoNombreDescripcion = "";
            while (rs.next()) {
                estadoNombreDescripcion = rs.getString("ESTADO_NOMBRE");
                //System.out.println("Segundos ESTADO_NOMBRE: "+estadoNombreDescripcion);
                listEstados.add(estadoNombreDescripcion);
            }
            rs.close();
            rs = null;
            if(callableStat !=null)
               callableStat.close();
            if(connect !=null)
               connect.close();
            log.log(Level.INFO, "-------- Finaliza getEstadosProcedure --------");
        } catch (Exception ex) {
            log.log(Level.INFO, "-------- Exception getEstados --------", ex);
            if(callableStat !=null)
            {
                try {
                    callableStat.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(TMSFacturaEWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connect !=null)
            {
                try {
                    connect.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(TMSFacturaEWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            //releaseTransaction();
            if(callableStat !=null)
            {
                try {
                    callableStat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TMSFacturaEWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connect !=null)
            {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TMSFacturaEWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listEstados;
    }//catch (SQLException ex)

    @WebMethod(operationName = "actualizarInformacionClienteProcedure")
    public String actualizarInformacionClienteProcedure(@WebParam(name = "cliente") Cliente cliente) {
        String mensaje = "";
        try {
            log.log(Level.INFO, "-------- Inicia actualizarInformacionClienteProcedure --------");
            /*
            setQuery("CALL XER_TMS_PKG3.ACTUALIZA_CLIENTE_PRC (?,?,?,?,?,?,?,?,?,?,?,?,?)");

            setConnection(TMS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));

            getCallableStatement().setInt(1, cliente.getClienteId());//CLIENTE_ID
            getCallableStatement().setString(2, cliente.getNombre());//NOMBREC
            getCallableStatement().setString(3, cliente.getApellidos());//APELLIDOSC
            getCallableStatement().setString(4, cliente.getCalle());//CALLEC
            getCallableStatement().setString(5, cliente.getNumeroInterior());//NUMERO_INTC
            getCallableStatement().setString(6, cliente.getNumeroExterior());//NUMERO_EXTC
            getCallableStatement().setString(7, cliente.getColonia());//COLONIAC
            getCallableStatement().setString(8, cliente.getCodigoPostal());//CODIGOPOSTALC
            getCallableStatement().setString(9, cliente.getDelegacionMunicipio());//DELEG_MUNC
            getCallableStatement().setString(10, cliente.getCiudad());//CIUDADC
            getCallableStatement().setString(11, cliente.getEstado());//ESTADOC
            getCallableStatement().setString(12, cliente.getEmail());//EMAILC
            getCallableStatement().registerOutParameter(13, java.sql.Types.VARCHAR);
            getCallableStatement().executeUpdate();
            mensaje = getCallableStatement().getString(13);
             *
             */
            String qry = "UPDATE ER_CLIENTES_TBL@REPCONTROL_LINK.ESTRELLAROJA.COM.MX C "
                                  + "SET C.nombre = '"+cliente.getNombre()+"', "
                                  + "C.CALLE = '"+cliente.getCalle()+"', "
                                  + "C.CIUDAD = '"+cliente.getCiudad()+"', "
                                  + "C.CODIGO_POSTAL = '"+cliente.getCodigoPostal()+"', "
                                  + "C.COLONIA = '"+cliente.getColonia()+"', "
                                  + "C.ESTADO = '"+cliente.getEstado()+"', "
                                  + "C.ULTIMA_FECHA_ACTUALIZACION = SYSDATE, "
                                  + "C.NO_EXT = '"+cliente.getNumeroExterior()+"', "
                                  + "C.NO_INT = '"+cliente.getNumeroInterior()+"', "
                                  + "C.RFC = '"+cliente.getRfc()+"', "
                                  + "C.email = '"+cliente.getEmail()+"' "
                                  //+ "C.ULTIMA_ACTUALIZACION_POR = 1000"+usuario
                                  + "WHERE C.cliente_id = "+cliente.getClienteId();
                           System.out.println("qry(Actualiza): "+qry);
               setQuery(qry);
                setConnection(TMS_DB.getConnection());
                setCallableStatement(getConnection().prepareCall(getQuery()));
                setResultSet(getCallableStatement().executeQuery());

            log.log(Level.INFO, "-------- Finaliza actualizarInformacionClienteProcedure --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception actualizarInformacionClienteProcedure --------", e);
        } finally {
            releaseTransaction();
        }
        return mensaje;
    }

    @WebMethod(operationName = "actualizarInformacionCliente")
    public boolean actualizarInformacionCliente(@WebParam(name = "actualizarCliente") Cliente actualizarCliente) {
        boolean successfully = true;
        try {
            log.log(Level.INFO, "-------- Inicia actualizarInformacionCliente --------");
            setQuery("update tms_clientes_tbl set nombre = ?, apellidos = ?, contacto = ?, calle = ?, "
                    + "numero_ext = ?, numero_int=?, colonia=?, codigo_postal=?, deleg_mun=?, ciudad=?, "
                    + "estado=?, telefono_casa=?, telefono_oficina=?,fecha_nac = ?,sexo = ?, estado_civil = ?, "
                    + "compania = ?, puesto = ?,email = ?, saldo_puntos = ?, fecha_ultima_acumulacion = ?, "
                    + "fecha_ultimo_canje = ?, tipo_cliente_id = ?, fecha_desde = ?, fecha_hasta = ?, "
                    + "creado_por= ?, fecha_creacion = ?, ultima_actualizacion_por = ?, sysdate, replicacion_estado = ?, "
                    + "replicacion_intentos = ?, replicacion_origen = ?, contrasenia = ?, telefono_celular = ?, "
                    + "no_pasaporte = ?, pais = ?, telefono_fax = ?, rfc = ?, cliente_relacionado_id = ?, "
                    + "adicional1 = ?, adicional2 = ?, adicional3 = ?, adicional4 = ?, adicional5 = ? "
                    + "where cliente_id = ?");
            if (actualizarCliente.isMarkedToUpdate()) {
                setConnection(TMS_DB.getConnection());
                setPreparedStatement(getConnection().prepareStatement(getQuery()));
                getPreparedStatement().setString(1, actualizarCliente.getNombre());
                getPreparedStatement().setString(2, actualizarCliente.getApellidos());
                getPreparedStatement().setString(3, actualizarCliente.getContacto());
                getPreparedStatement().setString(4, actualizarCliente.getCalle());
                getPreparedStatement().setString(5, actualizarCliente.getNumeroInterior());
                getPreparedStatement().setString(6, actualizarCliente.getNumeroExterior());
                getPreparedStatement().setString(7, actualizarCliente.getColonia());
                getPreparedStatement().setString(8, actualizarCliente.getCodigoPostal());
                getPreparedStatement().setString(9, actualizarCliente.getDelegacionMunicipio());
                getPreparedStatement().setString(10, actualizarCliente.getCiudad());
                getPreparedStatement().setString(11, actualizarCliente.getEstado());
                getPreparedStatement().setString(12, actualizarCliente.getTelefonoCasa());
                getPreparedStatement().setString(13, actualizarCliente.getTelefonoOficina());
                getPreparedStatement().setString(14, actualizarCliente.getFechaNacimiento());
                getPreparedStatement().setString(15, actualizarCliente.getSexo());
                getPreparedStatement().setString(16, actualizarCliente.getEstadoCivil());
                getPreparedStatement().setString(17, actualizarCliente.getCompania());
                getPreparedStatement().setString(18, actualizarCliente.getPuesto());
                getPreparedStatement().setString(19, actualizarCliente.getEmail());
                getPreparedStatement().setInt(20, actualizarCliente.getSaldoPuntos());
                getPreparedStatement().setString(21, actualizarCliente.getFechaUltimaAcumulacion());
                getPreparedStatement().setString(22, actualizarCliente.getFechaUltimoCanje());
                getPreparedStatement().setInt(23, actualizarCliente.getTipoCliente());
                getPreparedStatement().setString(24, actualizarCliente.getFechaDesde());
                getPreparedStatement().setString(25, actualizarCliente.getFechaHasta());
                getPreparedStatement().setInt(26, actualizarCliente.getCreadoPor());
                getPreparedStatement().setString(27, actualizarCliente.getFechaCreacion());
                getPreparedStatement().setInt(28, actualizarCliente.getUsuarioWEB());
                getPreparedStatement().setString(29, REPLICACION_ESTADO);
                getPreparedStatement().setInt(30, 0);
                getPreparedStatement().setString(31, REPLICACION_ORIGEN);
                getPreparedStatement().setString(32, actualizarCliente.getContrasenia());
                getPreparedStatement().setString(33, actualizarCliente.getTelefonoCelular());
                getPreparedStatement().setString(34, actualizarCliente.getNumeroPasaporte());
                getPreparedStatement().setString(35, actualizarCliente.getPais());
                getPreparedStatement().setString(36, actualizarCliente.getRfc());
                getPreparedStatement().setInt(37, actualizarCliente.getClienteRelacionado());
                getPreparedStatement().setString(38, "");
                getPreparedStatement().setString(39, "");
                getPreparedStatement().setString(40, "");
                getPreparedStatement().setString(41, "");
                getPreparedStatement().setString(42, "");
                getPreparedStatement().setInt(43, actualizarCliente.getClienteId());
                getPreparedStatement().executeUpdate();
            }
            log.log(Level.INFO, "-------- Finaliza actualizarInformacionClienteProcedure --------");
        } catch (SQLException e) {
            successfully = false;
            log.log(Level.SEVERE, "-------- Exception actualizarInformacionClienteProcedure --------", e);
        } finally {
            releaseTransaction();
            return successfully;
        }
    }

    @WebMethod(operationName = "registrarInformacionCliente")
    public int registrarInformacionCliente(@WebParam(name = "nuevoCliente") Cliente nuevoCliente) {
        int nextValSeq = -1;
//        try {
//            log.log(Level.INFO, "-------- Inicia registrarInformacionCliente --------");
//            //setQuery("CALL XER_TMS_PKG3.INSERTA_NUEVO_CLIENTE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            if (nuevoCliente.isMarkedAsNewRecord()) {
                /*
                setConnection(TMS_DB.getConnection());
                setCallableStatement(getConnection().prepareCall(getQuery()));

                getCallableStatement().setString(1, nuevoCliente.getNombre());//NOMBREC
                getCallableStatement().setString(2, nuevoCliente.getApellidos());//APELLIDOSC
                getCallableStatement().setString(3, nuevoCliente.getContacto());//CONTACTO
                getCallableStatement().setString(4, nuevoCliente.getCalle());//CALLEC
                getCallableStatement().setString(5, nuevoCliente.getNumeroInterior());//NUMERO_INTC
                getCallableStatement().setString(6, nuevoCliente.getNumeroExterior());//NUMERO_EXTC
                getCallableStatement().setString(7, nuevoCliente.getColonia());//COLONIAC
                getCallableStatement().setString(8, nuevoCliente.getCodigoPostal());//CODIGOPOSTALC
                getCallableStatement().setString(9, nuevoCliente.getDelegacionMunicipio());//DELEG_MUNC
                getCallableStatement().setString(10, nuevoCliente.getCiudad());//CIUDADC
                getCallableStatement().setString(11, nuevoCliente.getEstado());//ESTADOC
                getCallableStatement().setString(12, nuevoCliente.getTelefonoCasa());//TELEFONO_CASAC
                getCallableStatement().setString(13, nuevoCliente.getTelefonoOficina());//TELEFONO_OFICINAC
                getCallableStatement().setString(14, nuevoCliente.getFechaNacimiento());//FECHA_NACC
                getCallableStatement().setString(15, nuevoCliente.getSexo());//SEXOC
                getCallableStatement().setString(16, nuevoCliente.getEstadoCivil());//ESTADO_CIVILC
                getCallableStatement().setString(17, nuevoCliente.getCompania());//COMPANIAC
                getCallableStatement().setString(18, nuevoCliente.getPuesto());//PUESTOC
                getCallableStatement().setString(19, nuevoCliente.getEmail());//EMAILC
                getCallableStatement().setInt(20, TIPO_CLIENTE_ID);//TIPO_CLIENTE_IDC
                getCallableStatement().setInt(21, USUARIO_WEB);//CREADO_PORC
                getCallableStatement().setString(22, nuevoCliente.getRfc());//RFCC
                getCallableStatement().registerOutParameter(23, java.sql.Types.VARCHAR);
                getCallableStatement().executeUpdate();
                nextValSeq = Integer.parseInt(getCallableStatement().getString(23));

                */
              /*  setQuery("select ER_CLIENTE_SEQ.NEXTVAL@REPCONTROL_LINK.ESTRELLAROJA.COM.MX ID from dual");
                setConnection(TMS_DB.getConnection());
                setCallableStatement(getConnection().prepareCall(getQuery()));
                setResultSet(getCallableStatement().executeQuery());
                while (getResultSet().next())
                    nextValSeq = getResultSet().getInt("ID");

                 String  qry = "   INSERT INTO ER_CLIENTES_TBL@REPCONTROL_LINK.ESTRELLAROJA.COM.MX(CLIENTE_ID ,NOMBRE,  CALLE,  NO_INT, NO_EXT,  COLONIA, CODIGO_POSTAL, CIUDAD, ESTADO, RFC, EMAIL,PERSONA_MORAL,APLICA_RETENCION,FECHA_REGISTRO,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION) "
                           + "VALUES("+nextValSeq+",'"+nuevoCliente.getNombre()+"','"+nuevoCliente.getCalle()+"','"+nuevoCliente.getNumeroInterior()+"','"+nuevoCliente.getNumeroExterior()+"','"+nuevoCliente.getColonia()+"','"+nuevoCliente.getCodigoPostal()+"','"+nuevoCliente.getCiudad()+"','"+nuevoCliente.getEstado()+"','"+nuevoCliente.getRfc()+"','"+nuevoCliente.getEmail()+"','N','N',SYSDATE,"+("1000"+USUARIO_WEB)+",SYSDATE,"+("1000"+USUARIO_WEB)+",SYSDATE)";
                //System.out.println("qry: "+qry);
                setQuery(qry);
                setConnection(TMS_DB.getConnection());
                //setPreparedStatement(getConnection().prepareStatement(getQuery()));
                setCallableStatement(getConnection().prepareCall(getQuery()));
                getCallableStatement().executeUpdate();
                log.log(Level.INFO, query);
                System.out.println("Inserto el cliente "+nextValSeq+"...");
                 
            }
            log.log(Level.INFO, "-------- Finaliza registrarInformacionCliente --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception registrarInformacionCliente --------", e);
        } finally {
            releaseTransaction();
            return nextValSeq;
        }
        */
        return nextValSeq;
    }

    @WebMethod(operationName = "cancelaFacturaElec")
    public String cancelaFacturaElec(@WebParam(name = "LlavefactElec") String LlavefactElec) {
        String msj = "";
        try {
            log.log(Level.INFO, "-------- Inicia cancelaFacturaElec --------");
            setConnection(TMS_DB.getConnection());
            setQuery("BEGIN "
                    + DB_PACKAGE_NAME + "CancelFactura_Elect_PRC(?, ?); "
                    + "COMMIT; "
                    + "EXCEPTION "
                    + "WHEN OTHERS THEN "
                    + "ROLLBACK; "
                    + "RAISE; "
                    + "END;");
            setCallableStatement(getConnection().prepareCall(getQuery()));
            getCallableStatement().setString(1, LlavefactElec);
            getCallableStatement().registerOutParameter(2, java.sql.Types.VARCHAR);
            getCallableStatement().execute();
            msj = getCallableStatement().getString(2);
            log.log(Level.INFO, "-------- Finaliza cancelaFacturaElec --------");
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "-------- Exception cancelaFacturaElec --------", ex);
        } finally {
            releaseTransaction();
            return msj;
        }
    }

    @WebMethod(operationName = "generarFacturacionElectronica")
    public FacturaElect generarFacturacionElectronica(@WebParam(name = "factura") FacturaElect factura) {
      OracleCallableStatement stmt=null;
      Connection cnx=null;
       try {
            log.log(Level.INFO, "-------- Inicia generarFacturacionElectronica --------");
            //setConnection(TMS_DB.getConnection());
            /*
            setQuery(" CALL " + DB_PACKAGE_NAME + "Factura_Elect_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            setCallableStatement(getConnection().prepareCall(getQuery()));
            getCallableStatement().setString(1, factura.getDatosReceptor());
            getCallableStatement().setString(2, factura.getDatosEmisor());
            getCallableStatement().setString(3, factura.getDatosFactura());
            getCallableStatement().setString(4, factura.getLineas());
            getCallableStatement().setString(5, factura.getTotales());
            getCallableStatement().setString(6, factura.getImpuestos());
            getCallableStatement().setString(7, factura.getRetencion());
            getCallableStatement().setString(8, factura.getTraslado());            

            log.log(Level.INFO,new StringBuilder("Receptor:").append(factura.getDatosReceptor()).append("\nEmisor:").append(factura.getDatosEmisor()).
                    append("\nFactura:").append(factura.getDatosFactura()).append("\nLineas:").append(factura.getLineas()).append("\nTotales:").
                    append(factura.getTotales()).append("\nImpuestos:").append(factura.getImpuestos()).append("\nRetencion:").
                    append(factura.getRetencion()).append("\nTraslado:").append(factura.getTraslado()).toString());

            getCallableStatement().registerOutParameter(9, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(10, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(11, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(12, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(13, java.sql.Types.VARCHAR);
            getCallableStatement().execute();

            log.log(Level.INFO, new StringBuilder("EStatus:").append(getCallableStatement().getString(9)).append("\nMensaje:").
                    append(getCallableStatement().getString(10)).append("\nLlave:").append(getCallableStatement().getString(11)).
                    append("\nRutaPdf:").append(getCallableStatement().getString(12)).append("\nRutaXml:").append(getCallableStatement().getString(13)).toString());

            factura.setStatus(getCallableStatement().getString(9));
            factura.setMensaje(getCallableStatement().getString(10));
            factura.setLlaveFact(getCallableStatement().getString(11));
            factura.setRutapdf(getCallableStatement().getString(12));
            factura.setRutaxml(getCallableStatement().getString(13));
            */
            System.out.println("factura.getTotales: "+factura.getTotales());
            System.out.println("factura.getImpuestos: "+factura.getImpuestos());
            System.out.println("factura.getTraslado: "+factura.getTraslado());
            String DatosReceptor[] = factura.getDatosReceptor().split("\\|");
            String Impuestos[] = factura.getImpuestos().split("\\|");
            String Totales[] = factura.getTotales().split("\\|"); 
            String DatosFactura[] = factura.getDatosFactura().split("\\|");
            String Lineas[] = factura.getLineas().split("\\|"); 
               String P_PARAMETROS_FACTURA = "|"+"||"+DatosFactura[12]+//TMS-PAQUER-ESTACIONAMIENTO
                             "||"+
                             "|" +Totales[1]+
                             "|"+((Totales[1].equals(Totales[2]))?"0":"16") +
                             "|0"+
                             "|0"+
                             "|0"+
                             "|0"+
                             "|"+Totales[2]+
                             "|"+ DatosReceptor[2]   +
                             "|" +DatosReceptor[1]+
                             "|" +Lineas[2]+
                             "|"+
                             "|"+
                             "|"+ DatosReceptor[3]+
                             "|"+DatosReceptor[4]+"|"+DatosReceptor[5]+      // Calle, No ext, No Int
                             "|"+ DatosReceptor[6]+
                             "|"+DatosReceptor[12]+
                             "|"+DatosReceptor[7]+  //Localidad
                             "|"+DatosReceptor[10]+
                             "|"+DatosReceptor[11]+
                             "|||A"+
                             "|"+DatosReceptor[13]+
                             "|||"+DatosFactura[13]+ //BOLETO-GUIA-TICKET 
                             "|"+DatosFactura[2]+ //METODO PAGO
                             "|"+DatosFactura[3].replace("XXXX", "")+ //NO_CUENTA
                             "|No Aplica"+
                             "|MOS"+
                             "|ingreso||1000"+CREADO_POR_ADMINISTRADOR+
                             //"|CAPU"+
                             "|"+DatosFactura[15]+
                             //"|"+DatosFactura[14]+
                             "|"+Impuestos[3];

      String P_PARAMETROS_PRODUCTOS = DatosFactura[11].replace(",", "^|"+DatosFactura[13]+"|").replace("BOLETO|-PARQUE-","BOLETO PARQUE|").replace("*","|");
      String modo = "T";
      String tipoFactura = "MOS";
       try {
            System.out.println("MODO "+modo);
            System.out.println("P_PARAMETROS_FACTURA "+P_PARAMETROS_FACTURA);
            System.out.println("P_PARAMETROS_PRODUCTOS "+P_PARAMETROS_PRODUCTOS);
            System.out.println("tipoFactura: "+tipoFactura);
            //System.out.println("clienteId: "+clienteId);
            System.out.println("monto: "+Totales[2]);
            System.out.println("usuarioId: "+CREADO_POR_ADMINISTRADOR);
            System.out.println("");



            cnx = dataSource_ER_CONTROL.getConnection();

            String q1 =
                        "BEGIN "+
                         "ER_CONTROL_PKG2.GET_OPERACION_FACTURA_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?); "+
                         "COMMIT; "+
                        "EXCEPTION "+
                        "WHEN OTHERS THEN "+
                         "ROLLBACK; "+
                         "RAISE; "+
                        "END;";
                
                stmt = (OracleCallableStatement) cnx.prepareCall(q1);
                ((OraclePreparedStatement)stmt).setString(1, null);
                ((OraclePreparedStatement)stmt).setString(2, P_PARAMETROS_FACTURA);
                ((OraclePreparedStatement)stmt).setString(3, P_PARAMETROS_PRODUCTOS);
                ((OraclePreparedStatement)stmt).setString(4,null);
                ((OraclePreparedStatement)stmt).setString(5, modo);
                stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(8,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(9,java.sql.Types.VARCHAR);
                System.out.println("ENTRANDO a GeneraFactura.... ");
                boolean bResultado=stmt.execute();
                System.out.println("SALIENDO de GeneraFactura.... ");
                String P_FACTURA_SALIDA = stmt.getString(6);
                System.out.println("P_FACTURA_SALIDA: "+P_FACTURA_SALIDA);
                String status = stmt.getString(7);
                String error = stmt.getString(9);
                System.out.println("status: "+status); 
                System.out.println("error: "+error);
                if(status.equals("TRUE"))
                {
                    StringTokenizer fst = new StringTokenizer(P_FACTURA_SALIDA,"~");
                    String facturaId = fst.nextToken();
                    String LlaveFact = fst.nextToken();
                    String rutapdf = fst.nextToken();
                    String rutaxml = fst.nextToken();
					factura.setAnexo20(""+facturaId);//Temporalmente se usa este campo para guardar el Id de la factura
                    factura.setStatus("ACEPTADA");
                    factura.setMensaje(error);
                    factura.setLlaveFact(LlaveFact);
                    factura.setRutapdf(rutapdf);
                    factura.setRutaxml(rutaxml);

                }
                else
                {
                    factura.setStatus("RECHAZADA");
                    factura.setMensaje(error);
                }


                stmt.close();
                 
                if(cnx!=null) cnx.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                factura.setStatus("RECHAZADA");
                factura.setMensaje("No se pudo generar la factura: "+ex.getErrorCode());
                try {
                    if(stmt!=null)
                        stmt.close();
                    if(cnx!=null) cnx.close();
                } catch (Exception e1) {
                   e1.printStackTrace();
                }
                return factura;
            }
        //return P_RESULTADO;
        
            log.log(Level.INFO, "-------- Finaliza generarFacturacionElectronica --------");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "-------- Exception generarFacturacionElectronica --------", ex);
                try {
                      if(stmt!=null) stmt.close();
                      if(cnx!=null) cnx.close();
                    } catch (Exception e1) { e1.printStackTrace(); }
        } finally {
                try {
                      if(stmt!=null) stmt.close();
                      if(cnx!=null) cnx.close();
                    } catch (Exception e1) { e1.printStackTrace(); }
            //releaseTransaction();
            return factura;
        }
    }

     @WebMethod(operationName = "generarFacturacionElectronicaPAQUER")
    public FacturaElect generarFacturacionElectronicaPAQUER(@WebParam(name = "factura") FacturaElect factura) {
      OracleCallableStatement stmt=null;
      Connection cnx=null;
       try {
            log.log(Level.INFO, "-------- Inicia generarFacturacionElectronica --------");
            System.out.println("factura.getTotales: "+factura.getTotales());
            System.out.println("factura.getImpuestos: "+factura.getImpuestos());
            System.out.println("factura.getTraslado: "+factura.getTraslado());
            String DatosReceptor[] = factura.getDatosReceptor().split("\\|");
            String Impuestos[] = factura.getImpuestos().split("\\|");
            String Totales[] = factura.getTotales().split("\\|");
            String DatosFactura[] = factura.getDatosFactura().split("\\|");
            String Lineas[] = factura.getLineas().split("\\|");
            String ids="";
                   try{
                       ids=Lineas[16];
                       if(ids.startsWith(","))
                           ids=ids.replaceFirst(",", "");
                   }catch(Exception e){

                   }
            try{
                float ret=Float.parseFloat(Lineas[12]);
                if(ret<=0){
                    Lineas[11]="0";
                    Lineas[12]="0";
                }
            }catch(Exception e){
                    Lineas[11]="0";
                    Lineas[12]="0";
            }
               String P_PARAMETROS_FACTURA = "|"+"||"+DatosFactura[12]+//TMS-PAQUER-ESTACIONAMIENTO
                             "||"+
                             "|" +Totales[1]+
                             "|"+((Totales[1].equals(Totales[2]))?"0":"16") +
                             "|"+Lineas[11]+
                             "|"+Lineas[12]+
                             "|0"+
                             "|0"+
                             "|"+Totales[2]+
                             "|"+ DatosReceptor[2]   +
                             "|" +DatosReceptor[1]+
                             "|" +Lineas[2]+
                             "|"+
                             "|"+
                             "|"+ DatosReceptor[3]+
                             "|"+DatosReceptor[4]+"|"+DatosReceptor[5]+      // Calle, No ext, No Int
                             "|"+ DatosReceptor[6]+
                             "|"+DatosReceptor[12]+
                             "|"+DatosReceptor[7]+  //Localidad
                             "|"+DatosReceptor[10]+
                             "|"+DatosReceptor[11]+
                             "|||A"+
                             "|"+DatosReceptor[13]+
                             "|||"+DatosFactura[13]+ //BOLETO-GUIA-TICKET
                             "|"+DatosFactura[2]+ //METODO PAGO
                             "|"+DatosFactura[3].replace("XXXX", "")+ //NO_CUENTA
                             "|No Aplica"+
                             "|MOS"+
                             "|ingreso||1000"+CREADO_POR_ADMINISTRADOR+
                             //"|CAPU"+
                             "|"+DatosFactura[15]+
                             //"|"+DatosFactura[14]+
                            // "|"+Impuestos[3]+
                             "|"+ids;

      String P_PARAMETROS_PRODUCTOS = DatosFactura[11].replace(",", "^|"+DatosFactura[13]+"|").replace("BOLETO|-PARQUE-","BOLETO PARQUE|").replace("*","|");
      String modo = "T";
      String tipoFactura = "MOS";
       try {
            System.out.println("MODO "+modo);
            System.out.println("P_PARAMETROS_FACTURA "+P_PARAMETROS_FACTURA);
            System.out.println("P_PARAMETROS_PRODUCTOS "+P_PARAMETROS_PRODUCTOS);
            System.out.println("tipoFactura: "+tipoFactura);
            //System.out.println("clienteId: "+clienteId);
            System.out.println("monto: "+Totales[2]);
            System.out.println("usuarioId: "+CREADO_POR_ADMINISTRADOR);
           



            cnx = dataSource_ER_CONTROL.getConnection();

            String q1 =
                        "BEGIN "+
                         "ER_CONTROL_PKG2.GET_OPERACION_FACTURA_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?); "+
                         "COMMIT; "+
                        "EXCEPTION "+
                        "WHEN OTHERS THEN "+
                         "ROLLBACK; "+
                         "RAISE; "+
                        "END;";

                stmt = (OracleCallableStatement) cnx.prepareCall(q1);
                ((OraclePreparedStatement)stmt).setString(1, null);
                ((OraclePreparedStatement)stmt).setString(2, P_PARAMETROS_FACTURA);
                ((OraclePreparedStatement)stmt).setString(3, P_PARAMETROS_PRODUCTOS);
                ((OraclePreparedStatement)stmt).setString(4,null);
                ((OraclePreparedStatement)stmt).setString(5, modo);
                stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(8,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(9,java.sql.Types.VARCHAR);
                System.out.println("ENTRANDO a GeneraFactura.... ");
                boolean bResultado=stmt.execute();
                System.out.println("SALIENDO de GeneraFactura.... ");
                String P_FACTURA_SALIDA = stmt.getString(6);
                System.out.println("P_FACTURA_SALIDA: "+P_FACTURA_SALIDA);
                String status = stmt.getString(7);
                String error = stmt.getString(9);
                System.out.println("status: "+status);
                System.out.println("error: "+error);
                if(status.equals("TRUE"))
                {
                    StringTokenizer fst = new StringTokenizer(P_FACTURA_SALIDA,"~");
                    String facturaId = fst.nextToken();
                    String LlaveFact = fst.nextToken();
                    String rutapdf = fst.nextToken();
                    String rutaxml = fst.nextToken();
		    factura.setAnexo20(""+facturaId);//Temporalmente se usa este campo para guardar el Id de la factura
                    factura.setStatus("ACEPTADA");
                    factura.setMensaje(error);
                    factura.setLlaveFact(LlaveFact);
                    factura.setRutapdf(rutapdf);
                    factura.setRutaxml(rutaxml);

                }
                else
                {
                    factura.setStatus("RECHAZADA");
                    factura.setMensaje(error);
                }


                stmt.close();

                if(cnx!=null) cnx.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                factura.setStatus("RECHAZADA");
                factura.setMensaje("No se pudo generar la factura: "+ex.getErrorCode());
                try {
                    if(stmt!=null)
                        stmt.close();
                    if(cnx!=null) cnx.close();
                } catch (Exception e1) {
                   e1.printStackTrace();
                }
                return factura;
            }
        //return P_RESULTADO;

            log.log(Level.INFO, "-------- Finaliza generarFacturacionElectronica --------");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "-------- Exception generarFacturacionElectronica --------", ex);
                try {
                      if(stmt!=null) stmt.close();
                      if(cnx!=null) cnx.close();
                    } catch (Exception e1) { e1.printStackTrace(); }
        } finally {
                try {
                      if(stmt!=null) stmt.close();
                      if(cnx!=null) cnx.close();
                    } catch (Exception e1) { e1.printStackTrace(); }
            //releaseTransaction();
            return factura;
        }
    }

    @WebMethod(operationName = "getParametrosGlobales")
    public List<String> getParametrosGlobales(@WebParam(name = "parametro") String parametro) {
        List<String> resultado = new ArrayList<String>();
        try {
            log.log(Level.INFO, "-------- Inicia getParametrosGlobales --------");
            setQuery("SELECT g.PARAMETRO_VALOR "
                    + "FROM TMS_PARAMETROS_CONFIG_TBL P , TMS_GLOBAL_PARAMETROS_TBL g "
                    + "WHERE P.PARAMETRO_CODIGO IN(" + parametro + ") "
                    + "AND P.PARAMETRO_CONFIG_ID = g.PARAMETRO_CONFIG_ID order by P.PARAMETRO_CODIGO");
            setConnection(TMS_DB.getConnection());
            setPreparedStatement(getConnection().prepareStatement(getQuery()));
            setResultSet(getPreparedStatement().executeQuery());
            while (getResultSet().next())
                resultado.add(getResultSet().getString(FIRST_COLUMN));

            log.log(Level.INFO, "-------- Finaliza getParametrosGlobales --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Inicia getParametrosGlobales --------", e);
        } finally {
            releaseTransaction();
            return resultado;
        }
    }

     @WebMethod(operationName = "getParametrosGlobalesPAQUER")
    public List<String> getParametrosGlobalesPAQUER(@WebParam(name = "parametro") String parametro) {
        List<String> resultado = new ArrayList<String>();
        try {
            log.log(Level.INFO, "-------- Inicia getParametrosGlobales PAQUER --------");
            setQuery("SELECT gp.parametro_valor "+
                       "FROM PAQ_PARAMETROS_CONFIG_TBL PC LEFT JOIN PAQ_GLOBAL_PARAMETROS_TBL GP ON GP.PARAMETRO_CONFIG_ID = PC.PARAMETRO_CONFIG_ID "+
                        "WHERE pc.PARAMETRO_CODIGO IN (" + parametro + ") "
                    + " order by PC.PARAMETRO_CODIGO");
            setConnection(PAQUER_DB.getConnection());
            setPreparedStatement(getConnection().prepareStatement(getQuery()));
            setResultSet(getPreparedStatement().executeQuery());
            while (getResultSet().next())
                resultado.add(getResultSet().getString(FIRST_COLUMN));

            log.log(Level.INFO, "-------- Finaliza getParametrosGlobalesPAQUER --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Inicia getParametrosGlobalesPAQUER --------", e);
        } finally {
            releaseTransaction();
            return resultado;
        }
    }

    @WebMethod(operationName = "getParametrosSucursal")
    public List<String> getParametrosSucursal(@WebParam(name = "parametro") String parametro,
            @WebParam(name = "sucursalId") String sucursalId) {
        List<String> resultado = new ArrayList<String>();
        try {
            log.log(Level.INFO, "-------- Inicia getParametrosSucursal --------");
            setQuery("SELECT u.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL P, "
                    + "TMS_TERMINAL_PARAMETROS_TBL u, TMS_ESTADOS_TBL e "
                    + "WHERE P.PARAMETRO_CODIGO IN(" + parametro + ") "
                    + "and p.parametro_config_id = u.parametro_config_id AND U.TERMINAL_ID = e.ESTADO_ID "
                    + "order by P.PARAMETRO_CODIGO"); // Desarrollo
                    // "AND e.ESTADO_NOMBRE = 'WEB' order by P.PARAMETRO_CODIGO"); // Producción
            setConnection(TMS_DB.getConnection());
            setPreparedStatement(getConnection().prepareStatement(getQuery()));
            setResultSet(getPreparedStatement().executeQuery());
            while (getResultSet().next())
                resultado.add(getResultSet().getString(FIRST_COLUMN));
            log.log(Level.INFO, "-------- Finaliza getParametrosSucursal --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception getParametrosSucursal --------", e);
        } finally {
            releaseTransaction();
            return resultado;
        }
    }

    @WebMethod(operationName = "registrarBoletosFacturados")
    public FacturaElect registrarBoletosFacturados(@WebParam(name = "facturados") List<BoletosFacturados> facturados, @WebParam(name = "factElec") FacturaElect factElec) {
        FacturaElect facturaElect = null;
        try {
            log.log(Level.INFO, "-------- Inicia registrarBoletosFacturados --------");
            System.out.println("factElec.DatosReceptor : "+factElec.getDatosReceptor());
            System.out.println("factElec.Totales : "+factElec.getTotales());
            System.out.println("factElec.DatosEmisor : "+factElec.getDatosEmisor());
            System.out.println("factElec.Impuestos : "+factElec.getImpuestos());
            System.out.println("factElec.DatosFactura : "+factElec.getDatosFactura());
            System.out.println("factElec.Retencion : "+factElec.getRetencion());
            System.out.println("factElec.Traslado : "+factElec.getTraslado());
            System.out.println("factElec.Lineas : "+factElec.getLineas());
            System.out.println("facturados.size() : "+facturados.size());
            String folios = "";
            String ids = ""; 
            String unidadNegocio = "";
            String producto = "";
            String ciudadVenta = "";
                    //TMS-PAQUER-ESTACIONAMIENTO
            //BOLETO-GUIA-TICKET 
            for (Iterator<BoletosFacturados> it = facturados.iterator(); it.hasNext();)
            {
                BoletosFacturados bfact = it.next();
                if(bfact.getBoleto().getBoletoId() != null)
                {
                    //folios = folios+ "," + bfact.getBoleto().getpFolio();
                    folios = folios+ "," + bfact.getBoleto().getpFolio()+"*SERVICIO DE BOLETOS*EFE*Boleto de autobús con folio " + bfact.getBoleto().getpFolio()+"*No Aplica*"+bfact.getBoleto().getBoletoporcentajeIva()+"*"+bfact.getBoleto().getBoletoimporteIva()+"*0*0*0*0*" + bfact.getBoleto().getBoletosubTotal()+"*" + bfact.getBoleto().getBoletototal();
                    if(bfact.getBoleto().getEntradatotal()>0)
                        folios = folios+ ",-PARQUE-" + bfact.getBoleto().getpFolio()+"*SERVICIO DE BOLETOS*EFE*Boleto de entrada al parque con folio " + bfact.getBoleto().getpFolio()+"*No Aplica*"+bfact.getBoleto().getEntradaporcentajeIva()+"*"+bfact.getBoleto().getEntradaimporteIva()+"*0*0*0*0*" + bfact.getBoleto().getEntradasubTotal()+"*" + bfact.getBoleto().getEntradatotal();
                    ids = ids+"," + bfact.getBoleto().getBoletoId();
                    unidadNegocio = "TMS";
                    producto = "BOLETO";
                    ciudadVenta = bfact.getBoleto().getEmpresaFactura();
                }
                else
                {
                    //folios = folios + "," + bfact.getBoleto().getNumTicket();
                    folios = folios+ "," + bfact.getBoleto().getpFolio()+"*SERVICIO DE ESTACIONAMIENTO*EFE*Uso de estacionamiento Ticket " + bfact.getBoleto().getNumTicket()+"*No Aplica*16*"+(bfact.getBoleto().getImporte()*16)+"*0*0*0*0*" + bfact.getBoleto().getSubTotal()+"*" + bfact.getBoleto().getImporte();
                    ids = ids+"," + bfact.getBoleto().getNumTicket();
                    unidadNegocio = "ESTACIONAMIENTO";
                    producto = "TICKET";
                    ciudadVenta = "CAPU";
                }
            }
            log.log(Level.INFO, "folios: " + folios);
            log.log(Level.INFO, "ids: " + ids);
            factElec.setDatosFactura(factElec.getDatosFactura()+"|"+folios+"|"+unidadNegocio+"|"+producto+"|"+ids+"|"+ciudadVenta);
            System.out.println("factElec.DatosFactura : "+factElec.getDatosFactura());
            facturaElect = generarFacturacionElectronica(factElec);
            if (facturaElect.getLlaveFact() != null
                    && facturaElect.getStatus().equalsIgnoreCase("ACEPTADA")
                    && facturaElect.getLlaveFact().length() > 0) {

                factElec.setLlaveFact(facturaElect.getLlaveFact());
                factElec.setRutapdf(facturaElect.getRutapdf());
                factElec.setRutaxml(facturaElect.getRutaxml());

                StringTokenizer fst = new StringTokenizer(facturaElect.getLlaveFact(), "|");
                fst.nextToken();
                String folioFacID = PREFIJO_FOLIO_FACTURA + fst.nextToken();
                /*
                setQuery("INSERT INTO TMS_BOLETOS_FACTURADOS_TBL (BOL_FACTURADO_ID, "
                        + "BOLETO_ID, FOLIO_PREIMPRESO, CLIENTE_ID, "
                        + "RFC, CREADO_POR, FECHA_CREACION, ULTIMA_ACTUALIZACION_POR, "
                        + "FECHA_ULTIMA_ACTUALIZACION, REPLICACION_ESTADO,"
                        + "REPLICACION_ORIGEN, REPLICACION_INTENTOS, FOLIO_FACTURA, ESTADO, "
                        + "ADICIONAL1, ADICIONAL2, ADICIONAL3, ADICIONAL4) VALUES "
                        + "(TMS_BOLETOS_FACTURADOS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, sysdate, ?, sysdate, ?, "
                        + "?, ?, ?, ?, ?, ?, ?, ?)");
                setConnection(TMS_DB.getConnection());
                setPreparedStatement(getConnection().prepareStatement(getQuery()));
                log.log(Level.INFO, query);
                for (Iterator<BoletosFacturados> it = facturados.iterator(); it.hasNext();) {
                    BoletosFacturados bfact = it.next();
                    if(bfact.getBoleto().getBoletoId() != null)
                        getPreparedStatement().setLong(1, Long.parseLong(bfact.getBoleto().getBoletoId()));
                    else
                        getPreparedStatement().setLong(1, Long.parseLong(bfact.getBoleto().getNumTicket()));
                    log.log(Level.INFO, "folio pre impreso: " + bfact.getBoleto().getpFolio());
                    getPreparedStatement().setString(2, bfact.getBoleto().getpFolio());
                    getPreparedStatement().setInt(3, bfact.getCliente().getClienteId());
                    getPreparedStatement().setString(4, bfact.getCliente().getRfc());
                    getPreparedStatement().setInt(5, CREADO_POR_ADMINISTRADOR);
                    getPreparedStatement().setInt(6, CREADO_POR_ADMINISTRADOR);
                    getPreparedStatement().setString(7, REPLICACION_ESTADO);
                    getPreparedStatement().setString(8, REPLICACION_ORIGEN);
                    getPreparedStatement().setInt(9, REPLICACION_INTENTOS);
                    getPreparedStatement().setString(10, folioFacID);
                    getPreparedStatement().setString(11, ESTADO_FACTURA);
                    getPreparedStatement().setString(12, facturaElect.getRutapdf());
                    getPreparedStatement().setString(13, facturaElect.getLlaveFact());
                    getPreparedStatement().setString(14, bfact.getBoleto().getAdicional3());
                    getPreparedStatement().setString(15, bfact.getBoleto().getAdicional4());
                    getPreparedStatement().executeUpdate();
                }
                */
                log.log(Level.INFO, "-------- Finaliza registrarBoletosFacturados --------");
            }
        } catch (Exception e) {
            log.log(Level.INFO, "-------- Exception registrarBoletosFacturados --------", e);
        } finally {
            releaseTransaction();
            return facturaElect;
        }
    }


    @WebMethod(operationName = "registrarBoletosFacturadosTaxi")
    public FacturaElect registrarBoletosFacturadosTaxi(@WebParam(name = "facturados") List<BoletosFacturados> facturados, @WebParam(name = "factElec") FacturaElect factElec) {
        FacturaElect facturaElect = null;
        try {
            log.log(Level.INFO, "-------- Inicia registrarBoletosFacturadosTaxi --------");
            System.out.println("factElec.DatosReceptor : "+factElec.getDatosReceptor());
            System.out.println("factElec.Totales : "+factElec.getTotales());
            System.out.println("factElec.DatosEmisor : "+factElec.getDatosEmisor());
            System.out.println("factElec.Impuestos : "+factElec.getImpuestos());
            System.out.println("factElec.DatosFactura : "+factElec.getDatosFactura());
            System.out.println("factElec.Retencion : "+factElec.getRetencion());
            System.out.println("factElec.Traslado : "+factElec.getTraslado());
            System.out.println("factElec.Lineas : "+factElec.getLineas());
            System.out.println("facturados.size() : "+facturados.size());
            String folios = "";
            String ids = "";
            String unidadNegocio = "";
            String producto = "";
            String ciudadVenta = "";
                    //TMS-PAQUER-ESTACIONAMIENTO
            //BOLETO-GUIA-TICKET
            for (Iterator<BoletosFacturados> it = facturados.iterator(); it.hasNext();)
            {
                BoletosFacturados bfact = it.next();
                    //folios = folios + "," + bfact.getBoleto().getNumTicket();
                    folios = folios+ "," + bfact.getBoleto().getpFolio()+"*TOP DRIVER EXPRESS*EFE*Servicio de Taxi Ticket " + bfact.getBoleto().getNumTicket()+"*No Aplica*0*0*0*0*0*0*" + bfact.getBoleto().getImporte()+"*" + bfact.getBoleto().getImporte();
                    ids = ids+"," + bfact.getBoleto().getNumTicket();
                    unidadNegocio = "TOPDRIVER";
                    producto = "TICKET TAXI";
                    ciudadVenta = "CAPU";
            }
            log.log(Level.INFO, "folios: " + folios);
            log.log(Level.INFO, "ids: " + ids);
            factElec.setDatosFactura(factElec.getDatosFactura()+"|"+folios+"|"+unidadNegocio+"|"+producto+"|"+ids+"|"+ciudadVenta);
            System.out.println("factElec.DatosFactura : "+factElec.getDatosFactura());
            facturaElect = generarFacturacionElectronica(factElec);
            if (facturaElect.getLlaveFact() != null
                    && facturaElect.getStatus().equalsIgnoreCase("ACEPTADA")
                    && facturaElect.getLlaveFact().length() > 0) {

                factElec.setLlaveFact(facturaElect.getLlaveFact());
                factElec.setRutapdf(facturaElect.getRutapdf());
                factElec.setRutaxml(facturaElect.getRutaxml());

                StringTokenizer fst = new StringTokenizer(facturaElect.getLlaveFact(), "|");
                fst.nextToken();
                String folioFacID = PREFIJO_FOLIO_FACTURA + fst.nextToken();
                /*
                setQuery("INSERT INTO TMS_BOLETOS_FACTURADOS_TBL (BOL_FACTURADO_ID, "
                        + "BOLETO_ID, FOLIO_PREIMPRESO, CLIENTE_ID, "
                        + "RFC, CREADO_POR, FECHA_CREACION, ULTIMA_ACTUALIZACION_POR, "
                        + "FECHA_ULTIMA_ACTUALIZACION, REPLICACION_ESTADO,"
                        + "REPLICACION_ORIGEN, REPLICACION_INTENTOS, FOLIO_FACTURA, ESTADO, "
                        + "ADICIONAL1, ADICIONAL2, ADICIONAL3, ADICIONAL4) VALUES "
                        + "(TMS_BOLETOS_FACTURADOS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, sysdate, ?, sysdate, ?, "
                        + "?, ?, ?, ?, ?, ?, ?, ?)");
                setConnection(TMS_DB.getConnection());
                setPreparedStatement(getConnection().prepareStatement(getQuery()));
                log.log(Level.INFO, query);
                for (Iterator<BoletosFacturados> it = facturados.iterator(); it.hasNext();) {
                    BoletosFacturados bfact = it.next();
                    if(bfact.getBoleto().getBoletoId() != null)
                        getPreparedStatement().setLong(1, Long.parseLong(bfact.getBoleto().getBoletoId()));
                    else
                        getPreparedStatement().setLong(1, Long.parseLong(bfact.getBoleto().getNumTicket()));
                    log.log(Level.INFO, "folio pre impreso: " + bfact.getBoleto().getpFolio());
                    getPreparedStatement().setString(2, bfact.getBoleto().getpFolio());
                    getPreparedStatement().setInt(3, bfact.getCliente().getClienteId());
                    getPreparedStatement().setString(4, bfact.getCliente().getRfc());
                    getPreparedStatement().setInt(5, CREADO_POR_ADMINISTRADOR);
                    getPreparedStatement().setInt(6, CREADO_POR_ADMINISTRADOR);
                    getPreparedStatement().setString(7, REPLICACION_ESTADO);
                    getPreparedStatement().setString(8, REPLICACION_ORIGEN);
                    getPreparedStatement().setInt(9, REPLICACION_INTENTOS);
                    getPreparedStatement().setString(10, folioFacID);
                    getPreparedStatement().setString(11, ESTADO_FACTURA);
                    getPreparedStatement().setString(12, facturaElect.getRutapdf());
                    getPreparedStatement().setString(13, facturaElect.getLlaveFact());
                    getPreparedStatement().setString(14, bfact.getBoleto().getAdicional3());
                    getPreparedStatement().setString(15, bfact.getBoleto().getAdicional4());
                    getPreparedStatement().executeUpdate();
                }
                */
                log.log(Level.INFO, "-------- Finaliza registrarBoletosFacturados --------");
            }
        } catch (Exception e) {
            log.log(Level.INFO, "-------- Exception registrarBoletosFacturados --------", e);
        } finally {
            //releaseTransaction();
            return facturaElect;
        }
    }

    @WebMethod(operationName = "getReimprimirFactura")
    public FacturaElect getReimprimirFactura(@WebParam(name = "folio") String folio, @WebParam(name = "rfc") String rfc, @WebParam(name = "tipo") String tipo) {
        FacturaElect facturaElect = new FacturaElect();
        OracleCallableStatement stmt=null;
        Connection cnx=null;
        try {
            log.log(Level.INFO, "-------- Inicia getReimprimirFactura --------");
            System.out.println("folio : "+folio);
            System.out.println("rfc : "+rfc.toUpperCase());
            System.out.println("tipo : "+tipo);

            /*log.log(Level.INFO, "folio: " + folio);
            log.log(Level.INFO, "rfc: " + rfc);
            log.log(Level.INFO, "tipo: " + tipo);
            *///facturaElect = generarFacturacionElectronica(factElec);

            cnx = dataSource_ER_CONTROL.getConnection();

            String q1 =
                        "BEGIN "+
                         "ER_CONTROL_PKG2.GET_REIMPRESION_FACTURA_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); "+
                         "COMMIT; "+
                        "EXCEPTION "+
                        "WHEN OTHERS THEN "+
                         "ROLLBACK; "+
                         "RAISE; "+
                        "END;";

                stmt = (OracleCallableStatement) cnx.prepareCall(q1);
                ((OraclePreparedStatement)stmt).setString(1, folio);
                ((OraclePreparedStatement)stmt).setString(2, rfc.toUpperCase());
                ((OraclePreparedStatement)stmt).setString(3, tipo);
                stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(8,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(9,java.sql.Types.VARCHAR);
                stmt.registerOutParameter(10,java.sql.Types.VARCHAR);
                System.out.println("ENTRANDO a obtener Factura para reimpresion.... ");
                boolean bResultado=stmt.execute();
                System.out.println("SALIENDO de obtener Factura  para reimpresion.... ");
                String status = stmt.getString(8);
                String error = stmt.getString(10);
                System.out.println("status: "+status);
                System.out.println("error: "+error);
                System.out.println("Rutapdf: "+stmt.getString(4));
                System.out.println("Rutaxml: "+stmt.getString(5));
                System.out.println("RFC: "+stmt.getString(6));
                System.out.println("Folio: "+stmt.getString(7));
                if(status.equals("TRUE"))
                {
                    facturaElect.setStatus("ACEPTADA");
                    facturaElect.setTipoProducto(tipo);
                    facturaElect.setRutapdf(stmt.getString(4));
                    facturaElect.setRutaxml(stmt.getString(5));
                    facturaElect.setRfc(stmt.getString(6));
                    facturaElect.setFolio(stmt.getString(7));
                }
                else
                {
                    facturaElect.setStatus("RECHAZADA");
                    facturaElect.setMensaje(error);
                }
                stmt.close();
                if(cnx!=null) cnx.close();
     
        } catch (Exception e) {
            log.log(Level.INFO, "-------- Exception getReimprimirFactura --------", e);
                try {
                    if(stmt!=null)
                        stmt.close();
                    if(cnx!=null) cnx.close();
                } catch (Exception e1) {
                   e1.printStackTrace();
                }

        } finally {
                try {
                    if(stmt!=null)
                        stmt.close();
                    if(cnx!=null) cnx.close();
                } catch (Exception e1) {
                   e1.printStackTrace();
                }
            //releaseTransaction();
            return facturaElect;
        }
    }

    /**
     * @author jmendoza
     * Obtiene la informacion del un ticket en base a su número y fecha de salida
     * @param P_NUM_TICKET ==> Número del ticket
     * @param P_NUM_TICKET ==> Fecha del termino del servicio (de salida) en formato DD/MM/RRRR
     * @return Una instancia de la clase TicketEstacionamiento con la información del ticket
     * @see TicketEstacionamiento
     * */
    @WebMethod(operationName = "getInformacionTicket")
    public TicketEstacionamiento getInformacionTicket(@WebParam(name = "P_NUM_TICKET") long P_NUM_TICKET,
            @WebParam(name = "P_FECHA_SALIDA") String P_FECHA_SALIDA) {
        TicketEstacionamiento ticket = null;        
        StringBuilder sb = new StringBuilder("SELECT tec.*  FROM ti_estacionamientos_cobro_tbl tec ");
        sb.append("WHERE num_recibo = ");
        sb.append(P_NUM_TICKET);
        sb.append(" AND fecha_salida = to_date('");
        sb.append(P_FECHA_SALIDA);
        sb.append("', 'dd/MM/RRRR') AND (EXTRACT(MONTH FROM sysdate) = EXTRACT(MONTH FROM to_date('");
        sb.append(P_FECHA_SALIDA);
        sb.append("', 'DD/MM/RRRR')) OR to_date('");
        sb.append(P_FECHA_SALIDA);
        sb.append("', 'DD/MM/RRRR') < (last_day(to_date('");
        sb.append(P_FECHA_SALIDA);
        sb.append("', 'DD/MM/RRRR')) + 5))");
        setQuery(sb.toString());
        //System.out.println("Qry getInformacionTicket: "+sb.toString());
        try {
            log.log(Level.INFO, "-------- Inicia getInformacionTicket --------");
            setConnection(TICKETS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));
            setResultSet(getCallableStatement().executeQuery());

            ticket = new TicketEstacionamiento();
            while (getResultSet().next()) {
                ticket.setNumTicket(getResultSet().getLong("NUM_TICKET"));
                ticket.setFechaEntrada(getResultSet().getString("FECHA_ENTRADA"));
                ticket.setHoraEntrada(getResultSet().getString("HORA_ENTRADA"));
                ticket.setFechaSalida(getResultSet().getString("FECHA_SALIDA"));
                ticket.setHoraSalida(getResultSet().getString("HORA_SALIDA"));
                ticket.setImporte(getResultSet().getString("IMPORTE"));
                ticket.setImpuestos(getResultSet().getString("IMPUESTOS"));
                ticket.setTotal(getResultSet().getString("TOTAL"));
                ticket.setServicio(getResultSet().getString("SERVICIO"));                
                ticket.setNumRecibo(getResultSet().getString("NUM_RECIBO"));
            }
            if(ticket.getNumTicket() == 0)
                log.log(Level.INFO, "--------------- No se encontró información ---------------");
            else
                log.log(Level.INFO, "--------------- Ticket encontrado ---------------");
            log.log(Level.INFO, "-------- Finaliza getInformacionTicket --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception getInformacionTicket --------", e);
        } finally {
            setResultSet(null);
            releaseTransaction();
            return ticket;
        }
    }


    /**
     * @author vgonzalez
     *
     */
    @WebMethod(operationName = "getInformacionTaxi")
    public TicketTaxi getInformacionTaxi(@WebParam(name = "P_NUM_TICKET_TAXI") long P_NUM_TICKET,
            @WebParam(name = "P_FECHA_SALIDA") String P_FECHA_SALIDA) {
        TicketTaxi ticket = null;
        String qry = "SELECT TEC.NO_BOLETO,to_char(TEC.FECHA,'dd/MM/RRRR') FECHA,TEC.HORA,TEC.RUTA,TEC.NO_TAXI,TEC.IMPORTE,TEC.NOMBRE_DESTINO   FROM TI_DET_TAXIS_TBL TEC  "
                + "WHERE no_boleto = "+P_NUM_TICKET +" "
                + "AND FECHA = TO_DATE('"+P_FECHA_SALIDA+"', 'dd/MM/RRRR')  "
                + "AND (EXTRACT(MONTH FROM SYSDATE) = EXTRACT(MONTH FROM TO_DATE('"+P_FECHA_SALIDA+"', 'DD/MM/RRRR')) OR TO_DATE('"+P_FECHA_SALIDA+"','DD/MM/RRRR') < (LAST_DAY(TO_DATE('"+P_FECHA_SALIDA+"', 'DD/MM/RRRR')) + 5)) ";
        setQuery(qry);
        //System.out.println("Qry getInformacionTaxi: "+qry);
        try {
            log.log(Level.INFO, "-------- Inicia getInformacionTicketTaxi --------");
            setConnection(TICKETS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));
            setResultSet(getCallableStatement().executeQuery());

            ticket = new TicketTaxi();
            ticket.setNumReciboTaxi("0");
            while (getResultSet().next()) {
                ticket.setNumReciboTaxi(getResultSet().getString("NO_BOLETO"));
                ticket.setNumRecibo(getResultSet().getString("NO_BOLETO"));
                ticket.setNumTicket(getResultSet().getString("NO_BOLETO"));
                ticket.setFecha(getResultSet().getString("FECHA"));
                ticket.setHora(getResultSet().getString("HORA"));
                ticket.setRuta(getResultSet().getString("RUTA"));
                ticket.setNoTaxi(getResultSet().getString("NO_TAXI"));
                ticket.setImporte(getResultSet().getString("IMPORTE"));
                ticket.setTotal(getResultSet().getString("IMPORTE"));
                ticket.setDestino(getResultSet().getString("NOMBRE_DESTINO"));
            }
            if(ticket.getNumReciboTaxi().equals("0"))
                log.log(Level.INFO, "--------------- No se encontró información Ticket Taxi ---------------");
            else
                log.log(Level.INFO, "--------------- Ticket Taxi encontrado ---------------");
            log.log(Level.INFO, "-------- Finaliza getInformacionTicketTaxi --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception getInformacionTicketTaxi --------", e);
        } finally {
            setResultSet(null);
            releaseTransaction();
            return ticket;
        }
    }


    /**
     * @author jmendoza
     * Método que verifica si un ticket ya ha sido facturado
     * @param numTicket Número del ticket a verificar
     * @return Una cadena con "S" si el ticket ya ha sido facturado, "N" si no lo ha sido y "" en cualquier otro caso
     */
    @WebMethod(operationName = "esTicketFacturado")
    public String esTicketFacturado(@WebParam(name = "numTicket") String numTicket,
            @WebParam(name = "fechaServicio") String fechaServicio) {
        StringBuilder sb = new StringBuilder("SELECT BOL_FACTURADO_ID FROM TMS_BOLETOS_FACTURADOS_TBL");
        sb.append(" WHERE FOLIO_PREIMPRESO = '");
        sb.append(numTicket);
        sb.append("' AND ESTADO = 'FACTURADO' AND ADICIONAL3 = 'TICKET'");
        sb.append(" AND to_date(adicional4, 'RRRR/MM/DD') = to_date('");
        sb.append(cambiarFormatoFecha(fechaServicio));
        sb.append("', 'RRRR/MM/DD')");
        setQuery(sb.toString());
        String resultado = "";        
        try {
            log.log(Level.INFO, "-------- Inicia esTicketFacturado --------");
            setConnection(TMS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));            
            setResultSet(getCallableStatement().executeQuery());
            while (getResultSet().next())
                resultado = getResultSet().getString(1);
            log.log(Level.INFO, "-------- Finaliza esTicketFacturado --------");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "-------- Exception esTicketFacturado --------", ex);
        } finally {
            setResultSet(null);
            releaseTransaction();
        }
        return resultado;
    }

  /**
     * @author jmendoza
     * Método que verifica si un ticket ya ha sido facturado
     * @param numTicket Número del ticket a verificar
     * @return Una cadena con "S" si el ticket ya ha sido facturado, "N" si no lo ha sido y "" en cualquier otro caso
     */
    @WebMethod(operationName = "esTicketFacturadoRepControl")
    public String esTicketFacturadoRepControl(@WebParam(name = "numTicket") String numTicket,
            @WebParam(name = "P_PRODUCTO") String P_PRODUCTO) {
        String qry ="select PRODUCTO_FACTURA_ID from ER_PRODUCTOS_FACTURAS_TBL where NOMBRE_PRODUCTO = '"+P_PRODUCTO+"' and REFERENCIA_PRODUCTO = '"+numTicket+"'";
        setQuery(qry);
        String resultado = "";
        try {
            log.log(Level.INFO, "-------- Inicia esTicketFacturadoRepControl --------");
            setConnection(dataSource_ER_CONTROL.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));
            setResultSet(getCallableStatement().executeQuery());
            while (getResultSet().next())
                resultado = getResultSet().getString(1);
            log.log(Level.INFO, "-------- Finaliza esTicketFacturadoRepControl --------");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "-------- Exception esTicketFacturadoRepControl --------", ex);
        } finally {
            setResultSet(null);
            releaseTransaction();
        }
        return resultado;
    }

    /**
     * @author jmendoza
     * Método que verifica si un cliente se encuentra en la lista negra de correos.
     * @param correoCliente Correo a verificar
     * @return 1 si se encuentra el correo en la lista negra, 0 en caso contrario y -2 si ocurre algun error al tratar de verificar
     */
    @WebMethod(operationName = "verificarClienteListaNegra")
    public int verificarClienteListaNegra(@WebParam(name = "correoCliente") String correoCliente) {
        int resultado = -2;
        try {
            log.log(Level.INFO, "-------- Inicia verificarClienteListaNegra --------");
            setQuery("CALL XER_TMS_WS_PKG.VERIFICARCLIENTELISTANEGRA (?,?)");

            setConnection(TMS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));

            getCallableStatement().setString(1, correoCliente);
            getCallableStatement().registerOutParameter(2, java.sql.Types.NUMERIC);
            getCallableStatement().executeUpdate();
            resultado = getCallableStatement().getInt(2);
            log.log(Level.INFO, "-------- Finaliza verificarClienteListaNegra --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception verificarClienteListaNegra --------", e);
        } finally {
            releaseTransaction();
        }
        return resultado;
    }

    /**
     * Busca en la base de datos si se encuentra la guia para facturar disponible
     * @param guia
     * @param total
     * @return
     */
    @WebMethod(operationName = "getInformacionGuia")
      public Guia getInformacionGuia(@WebParam(name = "guia") final String guia,
            @WebParam(name = "total") Double total) {
        setQuery("CALL XER_PAQ_PKG.VALIDA_GUIA_FACTURACION_WEB(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        Guia guiaObj = new Guia();
        log.log(Level.INFO, "-------- Inicia getInformacionGuia --------");
        try {
            setConnection(PAQUER_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));
            //Paramtros de entrada
            getCallableStatement().setString("P_NUM_GUIA", guia);
            getCallableStatement().setDouble("P_TOTAL_CON_IVA", total);

          //Parametros de salida
            getCallableStatement().registerOutParameter("P_VALIDO", java.sql.Types.INTEGER);
            getCallableStatement().registerOutParameter("P_MENSAJE", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_NUMERO_GUIA", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_FECHA", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_ORIGEN", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_NOMBRE_REMITENTE", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_IMPORTE", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_IVA", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_TOTAL", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_IMPUESTO_RETENIDO", java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter("P_FECHA_ENTREGA", java.sql.Types.VARCHAR);

            getCallableStatement().registerOutParameter("P_ID_GUIA", java.sql.Types.VARCHAR);


            getCallableStatement().executeUpdate();

            System.out.println("Valido: "+getCallableStatement().getString("P_VALIDO"));
            System.out.println("mensaje: "+getCallableStatement().getString("P_MENSAJE"));
            if (Integer.valueOf(getCallableStatement().getString("P_VALIDO")) == 1) {
                guiaObj.setFechaEntrega(getCallableStatement().getString("P_FECHA_ENTREGA"));
                guiaObj.setGuia(getCallableStatement().getString("P_NUMERO_GUIA"));
                guiaObj.setTotal(getCallableStatement().getString("P_TOTAL")==null?0:Double.parseDouble(getCallableStatement().getString("P_TOTAL")));
                guiaObj.setIva(getCallableStatement().getString("P_IVA")==null?0:Double.parseDouble(getCallableStatement().getString("P_IVA")));
                guiaObj.setSubtotal(getCallableStatement().getString("P_IMPORTE")==null?0:Double.parseDouble(getCallableStatement().getString("P_IMPORTE")));
                guiaObj.setOrigen(getCallableStatement().getString("P_ORIGEN"));
                guiaObj.setNombreRemitente(getCallableStatement().getString("P_NOMBRE_REMITENTE"));
                guiaObj.setFechaDocumentacion(getCallableStatement().getString("P_FECHA"));
                guiaObj.setImpuestoRetenido(getCallableStatement().getString("P_IMPUESTO_RETENIDO")==null?0:Double.parseDouble(getCallableStatement().getString("P_IMPUESTO_RETENIDO")));
                guiaObj.setEstatus(getCallableStatement().getInt("P_VALIDO"));
                guiaObj.setpGuiaId(getCallableStatement().getString("P_ID_GUIA"));
            }
            guiaObj.setEstatus(getCallableStatement().getInt("P_VALIDO"));
            log.log(Level.INFO, "-------- Finalizado getInformacionGuia --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception getInformacionGuia --------", e);
        } finally {          
            releaseTransaction();
            return guiaObj;
        }
    }
      /**
       * Actualiza las guias que se van a facturar
       * @param facturados
       * @param factElec
       * @return
       */

    @WebMethod(operationName = "registrarGuiasFacturadasPAQUER")
    public FacturaElect registrarGuiasFacturadasPAQUER(@WebParam(name = "facturados") List<GuiaFacturada> facturados, @WebParam(name = "factElec") FacturaElect factElec) {
        FacturaElect facturaElect = null;
        try {
            log.log(Level.INFO, "-------- Inicia registrarGuiasFacturadasPAQUER. --------");
            System.out.println("factElec.DatosReceptor : "+factElec.getDatosReceptor());
            System.out.println("factElec.Totales : "+factElec.getTotales());
            System.out.println("factElec.DatosEmisor : "+factElec.getDatosEmisor());
            System.out.println("factElec.Impuestos : "+factElec.getImpuestos());
            System.out.println("factElec.DatosFactura : "+factElec.getDatosFactura());
            System.out.println("factElec.Retencion : "+factElec.getRetencion());
            System.out.println("factElec.Traslado : "+factElec.getTraslado());
            System.out.println("factElec.Lineas : "+factElec.getLineas());
            System.out.println("facturados.size() : "+facturados.size());
            String folios = "";
            String ids = "";
            String unidadNegocio = "";
            String producto = "";
            String ciudadVenta = "";
                    //TMS-PAQUER-ESTACIONAMIENTO
            //BOLETO-GUIA-TICKET
            for (Iterator<GuiaFacturada> it = facturados.iterator(); it.hasNext();)
            {
                GuiaFacturada bfact = it.next();
                
                    //folios = folios+ "," + bfact.getBoleto().getpFolio();
                    folios = folios+ "," + bfact.getGuia().getGuia()+"*SERVICIO DE PAQUETERIA*EFE*Envio de Paqueteria con No. de Guia " + bfact.getGuia().getGuia()+"*No Aplica*"+bfact.getGuia().getGuiaporcentajeIva()+"*"+bfact.getGuia().getGuiaimporteIva()+"*0*0*0*0*" + bfact.getGuia().getGuiasubTotal()+"*" + bfact.getGuia().getGuiatotal();
                    ids = ids+"," + bfact.getGuia().getGuia();
                    unidadNegocio = "PAQUER";
                    producto = "GUIA";
                    ciudadVenta="CPU";
                   // ciudadVenta = bfact.getGuia().getEmpresaFactura();
                
            }
            log.log(Level.INFO, "folios: " + folios);
            log.log(Level.INFO, "ids: " + ids);
            factElec.setDatosFactura(factElec.getDatosFactura()+"|"+folios+"|"+unidadNegocio+"|"+producto+"|"+ids+"|"+ciudadVenta);
            System.out.println("factElec.DatosFactura : "+factElec.getDatosFactura());            
            facturaElect = generarFacturacionElectronicaPAQUER(factElec);
             log.log(Level.INFO, "PASO LA GENERACION CORRECTAMENTE");
            if (facturaElect.getLlaveFact() != null
                    && facturaElect.getStatus().equalsIgnoreCase("ACEPTADA")
                    && facturaElect.getLlaveFact().length() > 0) {

                factElec.setLlaveFact(facturaElect.getLlaveFact());
                factElec.setRutapdf(facturaElect.getRutapdf());
                factElec.setRutaxml(facturaElect.getRutaxml());

                StringTokenizer fst = new StringTokenizer(facturaElect.getLlaveFact(), "|");
                fst.nextToken();
                //String folioFacID = PREFIJO_FOLIO_FACTURA + fst.nextToken();
                String folioFac = fst.nextToken();//;PREFIJO_FOLIO_FACTURA + fst.nextToken();
                String facId = factElec.getAnexo20();//Temporalmente se usa este campo para obtener el Id de la factura
                log.log(Level.INFO, "   folioFac: "+folioFac);
                log.log(Level.INFO, "      facId: "+facId);
                log.log(Level.INFO, "      rutaPDF: "+factElec.getRutapdf());
                log.log(Level.INFO, "      rutaXML: "+factElec.getRutaxml());
                log.log(Level.INFO, "Manda a crear el Anexo:" );
                byte[] anexo =  generaAnexo(facId, folioFac, factElec.getLlaveFact(),new Date());
                String DatosReceptor[] = factElec.getDatosReceptor().split("\\|");
                log.log(Level.INFO, "      email: "+DatosReceptor[13]);
                sendFile(DatosReceptor[13], anexo, facturaElect.getRutapdf(), facturaElect.getRutaxml(), factElec.getLlaveFact() );
                log.log(Level.INFO, "-------- Finaliza registrarGuiasFacturadasPAQUER --------");
                facturaElect.setAnexofact(anexo);
            }
        } catch (Exception e) {
            log.log(Level.INFO, "-------- Exception registrarGuiasFacturadasPAQUER --------", e);
        } finally {
            //releaseTransaction();
             return facturaElect;
           
        }

    }

    @WebMethod(operationName = "existenGuiasFacturadas")
    public boolean existenGuiasFacturadas(@WebParam(name = "ids")String ids){
        boolean resultado=true;
         setQuery("CALL XER_PAQ_PKG.GUIAS_FACTURADAS_FNC(?,?)");

        log.log(Level.INFO, "-------- Se valida que no se hayan facturado antes las guias --------");

        try {
            setConnection(PAQUER_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));
            //Paramtros de entrada
            System.out.println("GUIAS:"+ids);
            getCallableStatement().setString("P_GUIAS", ids);
            getCallableStatement().registerOutParameter("V_GUIA_FACTURADA", java.sql.Types.VARCHAR);
              getCallableStatement().executeUpdate();
              String res=getCallableStatement().getString("V_GUIA_FACTURADA");
              System.out.println("EXISTEN GUIAS FACTURADAS:"+res);
              if(res.equals("N"))
                  resultado=false;
            } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Exception existenGuiasFacturadas --------", e);
        } finally {
            releaseTransaction();            
        }
        return resultado;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="GETTERS Y SETTERS">
    private void releaseTransaction() {
        try{
        if (getConnection() != null) {
            try {
                if (getCallableStatement() != null) {
                   /*try{
                    getCallableStatement().clearParameters();
                   } catch(SQLException ex){log.log(Level.SEVERE, "ERROR AL LIBERAR LOS PARAMETROS/n", ex);}
                    * 
                    */
                    getCallableStatement().close();                   
                }
                getConnection().close();              
            } catch (SQLException ex) {
                log.log(Level.SEVERE, "ERROR AL LIBERAR LA SESIÓN DE LA BASE DE DATOS/n", ex);
            }
        }
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR AL LIBERAR LA SESIÓN DE LA BASE DE DATOS, PROBABLEMENTE YA FUE CERRADA POR EL POOL DE CONEXIONES DEL SERVIDOR");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setCallableStatement(CallableStatement callableStatement) {
        this.callableStatement = callableStatement;
    }

    public CallableStatement getCallableStatement() {
        return callableStatement;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="METODOS">
    private String cambiarFormatoFecha(String fecha) {
        if("".equals(fecha))
            return "";
        if(fecha == null)
            return "";
        StringBuilder sb = null;
        try {
            sb = new StringBuilder(fecha.substring(6, 10));
            sb.append("/");
            sb.append(fecha.substring(3, 5));
            sb.append("/");
            sb.append(fecha.substring(0, 2));
        } catch(ArrayIndexOutOfBoundsException ioobe) {
            log.log(Level.SEVERE, ioobe.getMessage(), ioobe);
            return "";
        }
        return sb.toString();
    }
    //</editor-fold>




public byte[]  generaAnexo(String facturaId, String folioFactura, String claveElectronica,Date fecha) {
byte[]  strbyte    =  null;
String pathReportes = "paqer/reportes/";
InputStream entradaAnexoFactura ;

JasperReport jasperReportAnexoFactura;
JasperPrint jasperPrintAnexoFactura;

 try{   
   /* String qryDetalleFactura =
        "( select g.fecha fecha, " +
        "to_char(g.num_guia) guia_id,         " +
        "paq.peso  peso, " +
        "paq.ALTO||'x'||paq.ANCHO||'x'||paq.LARGO medidas, " +
        "g.importe importe, " +
        "g.iva * g.importe iva, " +
        "g.RETENCION,"+
        "g.total total, " +
        "NVL( ( select gs.precio  from" +
        "   paq_g_servicios_tbl gs," +
         "  paq_servicios_tbl s" +
         "  where gs.guia_id=g.guia_id" +
         "  and s.codigo='VDEC'" +
         "  and s.servicio_id=gs.servicio_id),0) Valor_Declarado , "+
        "(select s.sucursal_codigo from paq_sucursales_tbl s where s.sucursal_id = r.ruta_origen) origen, " +
        "(select s.sucursal_codigo from paq_sucursales_tbl s where s.sucursal_id = r.ruta_destino) destino, " +
        "paq.NUM numero " +
        //16/04/2013 VAGL "from paq_facturas_guias_tbl fg, paq_guias_tbl g, paq_rutas_tbl r, paq_g_paquetes_tbl gopaq, paq_paquetes_tbl paq " +
        "FROM ER_PRODUCTOS_FACTURAS_TBL@REPCONTROL_LINK.ESTRELLAROJA.COM.MX FG, PAQ_GUIAS_TBL G, PAQ_RUTAS_TBL R, PAQ_G_PAQUETES_TBL GOPAQ, PAQ_PAQUETES_TBL PAQ  " +
        "where fg.factura_id = $P{paFacturaId} " +
        //16/04/2013 VAGL"and g.guia_id = fg.guia_id " +
        "AND G.NUM_GUIA = FG.REFERENCIA_PRODUCTO "
        + "AND FG.NOMBRE_PRODUCTO = 'GUIA' " +
        "and r.ruta_id = g.ruta_id " +
        "and gopaq.guia_id = g.guia_id  " +
        "and paq.paquete_id = gopaq.paquete_id " +
        //"group by   paq.PAQUETE_ID, g.fecha, g.num_guia, g.importe, g.total, g.iva, G.RETENCION, r.ruta_origen, r.ruta_destino, paq.peso, paq.ALTO, paq.ANCHO, paq.LARGO, paq.NUM
        ") " +
        "union  " +
        "( select  " +
        "gp.fecha fecha, " +
        "to_char((select min(num_guia_operativa) from paq_gp_gpo_go_tbl where guiaprepagada_id = gp.guiaprepagada_id AND GRUPO_ID = grup.GRUPO_ID) || '-' ||  " +
        "(select max(num_guia_operativa) from paq_gp_gpo_go_tbl where guiaprepagada_id = gp.guiaprepagada_id AND GRUPO_ID = grup.GRUPO_ID)) guia_id,  " +
        "(select paq.peso from paq_gp_gpos_tbl gpo, PAQ_GP_GPO_PAQ_TBL gpo_paq, paq_paquetes_tbl paq  " +
        "where gpo.guiaprepagada_id = gp.guiaprepagada_id   " +
        "and gpo_paq.gu"
        + "iaprepagada_id = gpo.guiaprepagada_id  " +
        "and gpo_paq.grupo_id = gpo.grupo_id  " +
        "and paq.paquete_id = gpo_paq.paquete_id " +
        "and gpo_paq.GRUPO_ID = grup.GRUPO_ID " +
        ") peso,  " +
        "(select paq.ALTO||'x'||paq.ANCHO||'x'||paq.LARGO from paq_gp_gpos_tbl gpo, PAQ_GP_GPO_PAQ_TBL gpo_paq, paq_paquetes_tbl paq  " +
        "where gpo.guiaprepagada_id = gp.guiaprepagada_id   " +
        "and gpo_paq.guiaprepagada_id = gpo.guiaprepagada_id  " +
        "and gpo_paq.grupo_id = gpo.grupo_id  " +
        "and paq.paquete_id = gpo_paq.paquete_id " +
        "and gpo_paq.GRUPO_ID = grup.GRUPO_ID ) medidas,  " +
        "pgpo.precio importe, " +
        "gp.iva * gp.importe iva, gp.RETENCION, " +
        "gp.total total, " +
        " NVL( ( select gs.precio  from " +
          " paq_gp_gpo_servicios_tbl gs, " +
          " paq_servicios_tbl s " +
          " where gs.guiaprepagada_id=gp.guiaprepagada_id " +
          " and s.codigo='VDEC' " +
          " and s.servicio_id=gs.servicio_id),0) Valor_Declarado , "+
        "(select s.sucursal_codigo from paq_sucursales_tbl s where s.sucursal_id = r.ruta_origen) origen, " +
        "(select s.sucursal_codigo from paq_sucursales_tbl s where s.sucursal_id = r.ruta_destino) destino," +
        "1 numero  " +
         //16/04/2013 VAGL "from paq_facturas_gp_tbl fgp, paq_guias_prepagadas_tbl gp, paq_rutas_tbl r,paq_gp_gpo_go_tbl grup,paq_gp_gpos_tbl pgpo " +
        "FROM ER_PRODUCTOS_FACTURAS_TBL@REPCONTROL_LINK.ESTRELLAROJA.COM.MX FGP, PAQ_GUIAS_PREPAGADAS_TBL GP, PAQ_RUTAS_TBL R,PAQ_GP_GPO_GO_TBL GRUP,PAQ_GP_GPOS_TBL PGPO "+
        "where fgp.factura_id = $P{paFacturaId} " +
         //16/04/2013 VAGL "and gp.guiaprepagada_id = fgp.guiaprepagada_id " +
        "AND GP.GUIAPREPAGADA_ID = FGP.REFERENCIA_PRODUCTO  "
        + "AND FGP.NOMBRE_PRODUCTO = 'GUIA PREPAGADA' "+
        "and r.ruta_id = pgpo.ruta_id " +    
        "and grup.guiaprepagada_id = gp.guiaprepagada_id " +
        "and pgpo.GUIAPREPAGADA_ID = gp.guiaprepagada_id " +
        "and pgpo.Grupo_id = grup.Grupo_id " +
        //"group by grup.GRUPO_ID,gp.fecha,gp.guiaprepagada_id,gp.importe,gp.iva,gp.total,gp.RETENCION,r.ruta_origen,r.ruta_destino,pgpo.precio " +
        ")";
    */
   String qryDetalleFactura =  "( select g.fecha fecha,  "
           + "to_char(g.num_guia) guia_id,          "
           + "paq.peso  peso,  "
           + "paq.ALTO||'x'||paq.ANCHO||'x'||paq.LARGO medidas,  "
           + "g.importe importe,  "
           + "g.iva * g.importe iva,  "
           + "g.RETENCION, "
           + "g.total total,  "
           + "NVL( ( select gs.precio  from "
           + "paq_g_servicios_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX gs, "
           + "paq_servicios_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX s "
           + "where gs.guia_id=g.guia_id "
           + "and s.codigo='VDEC' "
           + "and s.servicio_id=gs.servicio_id),0) Valor_Declarado ,  "
           + "(select s.sucursal_codigo from paq_sucursales_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX s where s.sucursal_id = r.ruta_origen) origen, "
           + "(select s.sucursal_codigo from paq_sucursales_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX s where s.sucursal_id = r.ruta_destino) destino,  "
           + "paq.NUM numero  "
           + "FROM ER_PRODUCTOS_FACTURAS_tbl FG, PAQ_GUIAS_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX G, PAQ_RUTAS_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX R, PAQ_G_PAQUETES_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX GOPAQ, PAQ_PAQUETES_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX PAQ   "
           + "where fg.factura_id = $P{paFacturaId}  "
           + "AND G.NUM_GUIA = FG.REFERENCIA_PRODUCTO  "
           + "AND FG.NOMBRE_PRODUCTO = 'GUIA'  "
           + "and r.ruta_id = g.ruta_id  "
           + "and gopaq.guia_id = g.guia_id   "
           + "and paq.paquete_id = gopaq.paquete_id  "
           + ")  "
           + "union   "
           + "( select   "
           + "gp.fecha fecha,  "
           + "to_char((select min(num_guia_operativa) from paq_gp_gpo_go_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX where guiaprepagada_id = gp.guiaprepagada_id AND GRUPO_ID = grup.GRUPO_ID) || '-' ||   "
           + "(select max(num_guia_operativa) from paq_gp_gpo_go_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX where guiaprepagada_id = gp.guiaprepagada_id AND GRUPO_ID = grup.GRUPO_ID)) guia_id,  "
           + "(select paq.peso from paq_gp_gpos_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX gpo, PAQ_GP_GPO_PAQ_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX gpo_paq, paq_paquetes_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX paq   "
           + "where gpo.guiaprepagada_id = gp.guiaprepagada_id    "
           + "and gpo_paq.guiaprepagada_id = gpo.guiaprepagada_id   "
           + "and gpo_paq.grupo_id = gpo.grupo_id   "
           + "and paq.paquete_id = gpo_paq.paquete_id  "
           + "and gpo_paq.GRUPO_ID = grup.GRUPO_ID  "
           + ") peso,   "
           + "(select paq.ALTO||'x'||paq.ANCHO||'x'||paq.LARGO from paq_gp_gpos_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX gpo, PAQ_GP_GPO_PAQ_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX gpo_paq, paq_paquetes_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX paq   "
           + "where gpo.guiaprepagada_id = gp.guiaprepagada_id    "
           + "and gpo_paq.guiaprepagada_id = gpo.guiaprepagada_id   "
           + "and gpo_paq.grupo_id = gpo.grupo_id   "
           + "and paq.paquete_id = gpo_paq.paquete_id  "
           + "and gpo_paq.GRUPO_ID = grup.GRUPO_ID ) medidas,   "
           + "pgpo.precio importe, "
           + "gp.iva * gp.importe iva, gp.RETENCION,  "
           + "gp.total total,  "
           + "NVL( ( select gs.precio  from  "
           + "paq_gp_gpo_servicios_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX gs,  "
           + "paq_servicios_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX s  "
           + "where gs.guiaprepagada_id=gp.guiaprepagada_id  "
           + "and s.codigo='VDEC' "
           + "and s.servicio_id=gs.servicio_id),0) Valor_Declarado ,   "
           + "(select s.sucursal_codigo from paq_sucursales_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX s where s.sucursal_id = r.ruta_origen) origen,  "
           + "(SELECT S.SUCURSAL_CODIGO FROM PAQ_SUCURSALES_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX S WHERE S.SUCURSAL_ID = R.RUTA_DESTINO) DESTINO, "
           + "1 numero   "
           + "FROM ER_PRODUCTOS_FACTURAS_tbl FGP, PAQ_GUIAS_PREPAGADAS_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX GP, PAQ_RUTAS_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX R,PAQ_GP_GPO_GO_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX GRUP,PAQ_GP_GPOS_tbl@PAQUER_LINK.ESTRELLAROJA.COM.MX PGPO  "
           + "where fgp.factura_id = $P{paFacturaId}  "
           + "AND GP.GUIAPREPAGADA_ID = FGP.REFERENCIA_PRODUCTO   "
           + "AND FGP.NOMBRE_PRODUCTO = 'GUIA PREPAGADA'  "
           + "and r.ruta_id = pgpo.ruta_id      "
           + "and grup.guiaprepagada_id = gp.guiaprepagada_id  "
           + "and pgpo.GUIAPREPAGADA_ID = gp.guiaprepagada_id  "
           + "AND PGPO.GRUPO_ID = GRUP.GRUPO_ID  "
           + ")                 ";
    
   System.out.println("qryDetalleFactura --> "+qryDetalleFactura);
   System.out.println("facturaId: "+facturaId);
        String RepAnexoFactura = pathReportes + "AnexoFactura.jasper";
            releaseTransaction();
            Connection con = obtenConexion();
            System.out.println("Hace la coneccion..."+ con);
            //System.out.println("Ya no hace la coneccion...");
            //System.out.println("Paso de llenar el Reporte...");
           //     System.out.println("paso : job.setPrintService(Settings.impresoraGuia)");
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            float ancho = Float.parseFloat("215.900");
            float largo = Float.parseFloat("279.401");
            MediaSizeName mediaSizeName = MediaSize.findMedia(ancho,largo,MediaPrintableArea.MM);
          //float ancho = jasperPrintFactura.getPageWidth() /72f;
          //float largo = jasperPrintFactura.getPageHeight() /72f;
          //MediaSizeName mediaSizeName = MediaSize.findMedia(ancho,largo,MediaPrintableArea.INCH);
          System.out.println("paso :  MediaSizeName mediaSizeName = MediaSize.findMedia("+ancho+","+largo+","+MediaPrintableArea.INCH+")");
          System.out.println("mediaSizeName: "+mediaSizeName);
          System.out.println("mediaSizeName: "+mediaSizeName.getValue());
          printRequestAttributeSet.add(mediaSizeName);
          System.out.println("paso : printRequestAttributeSet.add(mediaSizeName)");
            ////printRequestAttributeSet.add(new Copies(num_copias));
            JRPrintServiceExporter exporter;
            // BRA exporter = new JRPrintServiceExporter();
            // BRA exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrintFactura);
            
            System.out.println("paso : exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrintFactura)");
            // Anexo de Factura
            System.out.println("Genera Anexo...");            
                    entradaAnexoFactura = this.getClass().getClassLoader().getResourceAsStream(RepAnexoFactura);
                    //Connection con2 = getConnection();
                    jasperReportAnexoFactura = (JasperReport) JRLoader.loadObject(entradaAnexoFactura);
                
                    Map parametros = new HashMap();                
                    parametros.put("paConsulta",qryDetalleFactura);
                    parametros.put("paLogo",getLogo());
                    parametros.put("paFacturaId",new BigDecimal(facturaId));
                    parametros.put("paFecha",fecha);//factura.getFecha());
                    String ClaveFactElec= claveElectronica;//factura.getClaveFactElec().trim();
                    System.out.println("-----------  Detalle ClaveFactElec "+ClaveFactElec);
                    //if (ClaveFactElec != null && ClaveFactElec.trim().length() > 0)
                    //{d
                       StringTokenizer fst = new StringTokenizer(ClaveFactElec,"|");
                        fst.nextToken(); fst.nextToken();
                        parametros.put("paSucursalCodigo",fst.nextToken());            
                        parametros.put("paFolioFactura", folioFactura);//factura.getAdicional2());//factura.getFacturaId().toString().replaceAll(PrefijoFolioFact,""));

                    //}
                    //else
                    //{    parametros.put("paSucursalCodigo",factura.getSucursal().getCodigo());            
                    //     parametros.put("paFolioFactura", factura.getAdicional2());//factura.getFacturaId().toString().substring( factura.getSucursal().getSucursalId().toString().length() ));
                    //}
                    System.out.println("parametros(paFacturaId): "+parametros.get("paFacturaId"));
                    System.out.println("Antes de fillReport...");
                    jasperPrintAnexoFactura  = JasperFillManager.fillReport(jasperReportAnexoFactura, parametros, con);
                    //jasperPrintAnexoFactura  = JasperFillManager.fillReport(jasperReportAnexoFactura, parametros, PAQUER_DB.getConnection());
                    //jasperPrintAnexoFactura  = JasperFillManager.fillReport(jasperReportAnexoFactura, parametros, dataSource_ER_CONTROL.getConnection());
                    System.out.println("Despues del fillReport...");
       
                    
                    System.out.println("Imprimir ANEXO  paSucursalCodigo "+ parametros.get("paSucursalCodigo"));  
                    System.out.println("Imprimir ANEXO  de Folio Fact "+ parametros.get("paFacturaId"));            
                    //JasperViewer.viewReport(jasperPrintAnexoFactura, false); 
                    
                 strbyte=JasperExportManager.exportReportToPdf(jasperPrintAnexoFactura);
                 System.out.println("imprimirFacturaElec.strbyte "+strbyte.length);
            
        }catch (JRException e) {
           System.out.println("Error del reporte");
            e.printStackTrace();   
            //JOptionPane.showMessageDialog(null, "Error de Impresion",Settings.ERR_MENSAJE,JOptionPane.ERROR_MESSAGE);
        }catch(Exception ex){
            System.out.println("no se realizo la impresion");
            ex.printStackTrace();
        }
        
        //monitor.updateStatus(IFMonitorStatus.FINALIZAR);
    return strbyte;
    
    }

    private Connection obtenConexion() throws NamingException {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            //DataSource ds = (DataSource)initContext.lookup("PAQER_DB");
            DataSource ds = (DataSource)initContext.lookup("REP_CONTROL_DB");
            conn = ds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;

    }

    private InputStream getLogo(){  
        return this.getClass().getResourceAsStream("paqer/recursos/paquer logo.JPG");
    }

public void sendFile(String Destinatarios, byte[]  strbyeAnexo, String rutaPDF, String rutaXML, String LlaveFactElec )
 {
    if (Destinatarios ==  null || Destinatarios.length() <=0)
          return;
          
//    String RutAbs =  HTDatosFactura.get("RUTABS").toString();
//    System.out.println(" Ruta ABS "+RutAbs);
//
//    String RutAlias   =  HTDatosFactura.get("RUTALIAS").toString();
//    System.out.println(" RutAlias  "+RutAlias);
//
    String  rutpd = rutaPDF;  
    String  rutxm = rutaXML;  

     String os=System.getProperty("os.name");
     String logo=null;    
     String Asunto = "Anexo Factura Electronica";
     int i=0;
     String Contenido ="Envio de Anexo de Factura Electronica ";// formato Pdf , archivo XML y Anexo";
  
     String[] ArrDestinatarios = Destinatarios.split("\\|");

     System.out.println(" Enviando Correo Asunto  "+Asunto);
     System.out.println(" Enviando Correo Destinatarios  "+Destinatarios);
     System.out.println(" Enviando Correo ArrDestinatarios  "+ArrDestinatarios);
     System.out.println(" Enviando Correo Contenido  "+Contenido);
     System.out.println(" Enviando Correo rutxm  "+rutxm);
     System.out.println(" Enviando Correo rutpd  "+rutpd);
     System.out.println(" Enviando Correo strbyeAnexo  "+strbyeAnexo);
     System.out.println(" Enviando Correo strbyeAnexo Tamaño  "+strbyeAnexo.length);
         
     if (ArrDestinatarios!= null && ArrDestinatarios.length>0)
           sendMail(logo,Asunto,ArrDestinatarios,Contenido,rutxm,rutpd,strbyeAnexo,LlaveFactElec );
     
    }
 
 public boolean sendMail(String logo, String asunto, String[] destinatarios, String contenido, String rutaXML, String rutaPDF, byte[] anexo, String LlaveFactElec)
 {
      String mailHost="";//"mail.estrellaroja.com.mx"
      String mailPort="";//25
      String mailUser="";//facturas
      String mailInetAddress="";//"facturas@estrellaroja.com.mx"
      String mailcontrasena=""; // "123456"
     String os = System.getProperty("os.name");
        System.out.println(" os  "+os);
        System.out.println(rutaPDF);
        System.out.println(rutaXML);
         if (!os.contains("Windows") )   //No esta en Windows
         {
             rutaPDF = "smb://"+rutaPDF.replaceAll("\\\\","/");
             rutaXML = "smb://"+rutaXML.replaceAll("\\\\","/");
         }
         System.out.println("***************** sendMail ******************** "+rutaPDF);
        System.out.println("rutaPDF: "+rutaPDF);
        System.out.println("rutaXML: "+rutaXML);
        System.out.println("Llave factura "+LlaveFactElec);
        String nofactura=LlaveFactElec.substring(LlaveFactElec.indexOf("|"),LlaveFactElec.length()-1);
        nofactura= nofactura.substring(0,nofactura.length()-1);
        System.out.println("nofactura"+nofactura);
        List<String> resultado = new ArrayList<String>();
        try {
            log.log(Level.INFO, "-------- Inicia getParametrosGlobales PAQUER sendMail --------");
            setQuery("SELECT gp.parametro_valor "+
                       "FROM PAQ_PARAMETROS_CONFIG_TBL@PAQUER_LINK.ESTRELLAROJA.COM.MX PC LEFT JOIN PAQ_GLOBAL_PARAMETROS_TBL@PAQUER_LINK.ESTRELLAROJA.COM.MX GP ON GP.PARAMETRO_CONFIG_ID = PC.PARAMETRO_CONFIG_ID "+
                        "WHERE pc.PARAMETRO_CODIGO IN ('P_FE_MAIL_CONTRASENA','P_FE_MAIL_HOST','P_FE_MAIL_PORT','P_FE_MAIL_USER','P_FE_MAIL_INETADDRESS') "
                    + " order by PC.PARAMETRO_CODIGO");
            setConnection(dataSource_ER_CONTROL.getConnection());
            setPreparedStatement(getConnection().prepareStatement(getQuery()));
            setResultSet(getPreparedStatement().executeQuery());
            while (getResultSet().next())
                resultado.add(getResultSet().getString(FIRST_COLUMN));
          mailcontrasena=resultado.get(0); // "123456"
          mailHost=resultado.get(1);//"mail.estrellaroja.com.mx"
          mailInetAddress=resultado.get(2);//"facturas@estrellaroja.com.mx"
          mailPort=resultado.get(3);//25
          mailUser=resultado.get(4);//facturas
          SMTP_AUTH_USER = mailUser;
          SMTP_AUTH_PWD = mailcontrasena;
          System.out.println("mailcontrasena: "+mailcontrasena);
          System.out.println("mailHost: "+mailHost);
          System.out.println("mailInetAddress: "+mailInetAddress);
          System.out.println("mailPort: "+mailPort);
          System.out.println("mailUser: "+mailUser);

            log.log(Level.INFO, "-------- Finaliza getParametrosGlobalesPAQUER --------");
        } catch (Exception e) {
            log.log(Level.SEVERE, "-------- Inicia getParametrosGlobalesPAQUER --------", e);
        }/* finally {
            releaseTransaction();
        }*/

        try {
            //Carga de los parametros
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", mailHost);
            props.setProperty("mail.smtp.port",mailPort);//25//5751
            props.setProperty("mail.smtp.user", mailUser);
            //Se inicia la sesión
            //Session session = Session.getDefaultInstance(props);

            //Se agrega para la autenticacion
            props.setProperty("mail.smtp.auth","true");
            //props.setProperty("mail.smtp.starttls.enable", "true");
            //props.setProperty ("mail.transport.protocol", "smtp");

        /*Session session = Session.getDefaultInstance(props,new Authenticator(){
        public PasswordAuthentication getPasswordAuthentication()
        {
            //Authentic user name and password of SMTP server is needed here (Your Email id and password would work)
            String username = SMTP_AUTH_USER; // give the username
            String password = SMTP_AUTH_PWD;  // and password
            return new PasswordAuthentication(username, password);
        }
        } );
        */
         //System.out.println(" SMTP_AUTH_USER: "+SMTP_AUTH_USER);
         //System.out.println(" SMTP_AUTH_PWD: "+SMTP_AUTH_PWD);
         Session session = Session.getInstance(props, new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);
          }
        });            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailInetAddress));
            
            System.out.println("DESTINATARIOS.length: "+destinatarios.length);
            System.out.println("DESTINATARIOS: "+destinatarios);
            if(destinatarios != null && destinatarios.length>0)
            for(String i : destinatarios)
            {
                 System.out.println("   Destinatario: "+i);
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(i));
            }
                String datosFolio[] = nofactura.split("\\|");

            message.setSubject(asunto+" "+datosFolio[2]+"-"+ datosFolio[1]);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent((logo != null ? "<img src='" + logo + "'/>" : "") + "<br/><br/>" + contenido+" "+datosFolio[2]+"-"+ datosFolio[1], "text/html");
            //Se cargan los archivos
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart adjuntosPart = null;
            javax.activation.DataSource source = null;
            //if(false) {
            if(anexo != null) {
                //Se agrega el Anexo
                System.out.println("Antes de Agregar Anexo...");
                adjuntosPart = new MimeBodyPart();
                File anexoFile = File.createTempFile("mailfacturaAcexo", ".tmp");
                FileOutputStream fos = new FileOutputStream(anexoFile);
                fos.write(anexo);
                fos.close();
                source = new FileDataSource(anexoFile);
                adjuntosPart.setDataHandler(new DataHandler(source));
                adjuntosPart.setFileName("Anexo"+nofactura+".pdf");
                multipart.addBodyPart(adjuntosPart);
                System.out.println("Despues de Agregar Anexo...");
            }
            /*
            //Se agrega el XML
            System.out.println("Antes de Agregar XML...");
            adjuntosPart = new MimeBodyPart();
            source = new FileDataSource(new File(rutaXML));
            adjuntosPart.setDataHandler(new DataHandler(source));
            adjuntosPart.setFileName("Factura"+nofactura+".xml");
            multipart.addBodyPart(adjuntosPart);
            System.out.println("Dsspues de Agregar XML...");

            //Se agrega el PDF
            System.out.println("Antes de Agregar PDF...");
            adjuntosPart = new MimeBodyPart();
            source = new FileDataSource(new File(rutaPDF));
            adjuntosPart.setDataHandler(new DataHandler(source));
            adjuntosPart.setFileName("Factura"+nofactura+".pdf");
            multipart.addBodyPart(adjuntosPart);
            System.out.println("Despues de Agregar PDF...");
            */
            message.setContent(multipart);
            Transport t = session.getTransport("smtp");

            System.out.println("t.connect("+mailHost+","+Integer.valueOf(mailPort)+","+mailInetAddress+","+mailcontrasena+")");
           // t.connect("facturas@estrellaroja.com.mx","123456");
             System.out.println("Antes de connect...");
             t.connect(mailHost,Integer.valueOf(mailPort),mailInetAddress,mailcontrasena);
             System.out.println("Despues de connect...");

             System.out.println("Antes de enviar Email...");
             t.sendMessage(message,message.getAllRecipients());
            System.out.println("Despues de enviar Email...");
             t.close();
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            releaseTransaction();
        }
        return true;
    }    
    
}


