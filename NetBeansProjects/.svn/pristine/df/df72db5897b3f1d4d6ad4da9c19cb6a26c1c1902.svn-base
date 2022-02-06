/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMS.Solicitud;

import WS_CONTROL.TMS.entidades.Boleto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class wsControlTMSCentralFacadeBean implements wsControlTMSCentralFacadeBeanRemote {

 @Resource(name = "TMS_CENTRAL_DB")
 private DataSource TMS_DB;
 private String query;
 private Logger log = Logger.getLogger("wsFacturacion");
 private Connection connection;
 private CallableStatement callableStatement;
 private PreparedStatement preparedStatement;
 public final static boolean CONSULTA_EXITOSA = true;

    public Boleto getInformacionBoleto(String P_FOLIO_PREIMPRESO, String P_NO_ASIENTO,String P_ORIGEN){
      //setQuery("CALL XER_TMS_PKG3.TMS_VALIDA_BOL_FACT_WEB_PRC(?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        setQuery("CALL XER_TMS_PKG3.TMS_VALIDA_BOL_FACT_EBUS_PRC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Boleto boleto = new Boleto();
        log.log(Level.INFO, "-------- Inicia getInformacionBoleto --------");
        try {
            setConnection(TMS_DB.getConnection());
            setCallableStatement(getConnection().prepareCall(getQuery()));
            getCallableStatement().setString(1, "");
            getCallableStatement().setString(2, P_FOLIO_PREIMPRESO);
            System.out.println("........"+P_FOLIO_PREIMPRESO);
            getCallableStatement().setString(3, P_NO_ASIENTO);
            System.out.println("........"+P_NO_ASIENTO);
            getCallableStatement().registerOutParameter(4, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(5, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(6, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(7, java.sql.Types.VARCHAR);
            getCallableStatement().registerOutParameter(8, java.sql.Types.VARCHAR);
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
            getCallableStatement().setString(19, ",");
            getCallableStatement().registerOutParameter(20, java.sql.Types.VARCHAR);
            getCallableStatement().setString(21, P_ORIGEN);
            //getCallableStatement().registerOutParameter(23, java.sql.Types.VARCHAR);
            //getCallableStatement().registerOutParameter(24, java.sql.Types.VARCHAR);

            getCallableStatement().executeUpdate();
            System.out.println("P_FOLIO_PREIMPRESO: "+P_FOLIO_PREIMPRESO);
            System.out.println("Valido: "+getCallableStatement().getString(4));
            System.out.println("mesnaje: "+getCallableStatement().getString(5));
            boleto.setpFolio(P_FOLIO_PREIMPRESO);
            boleto.setStatus(CONSULTA_EXITOSA);
            boleto.setMensaje(getCallableStatement().getString(5));
            if (Boolean.valueOf(getCallableStatement().getString(4)) == CONSULTA_EXITOSA) {

                boleto.setClaveCorrida(getCallableStatement().getString(6));
                boleto.setNombrePasajero(getCallableStatement().getString(7));
                boleto.setTipoPasajero(getCallableStatement().getString(8));
                boleto.setNumeroAsiento((getCallableStatement().getString(9)) == null ?-1 :Integer.valueOf(getCallableStatement().getString(9)));
                boleto.setFechaHoraCorrida((getCallableStatement().getString(10)==null) ?"" :getCallableStatement().getString(10));
                boleto.setFechaHoraVenta((getCallableStatement().getString(11)==null) ?"" :getCallableStatement().getString(11));
                boleto.setImporte(Double.parseDouble(getCallableStatement().getString(12)));
                boleto.setOrigen(getCallableStatement().getString(13));
                boleto.setDestino(getCallableStatement().getString(14));
                boleto.setServicio(getCallableStatement().getString(15));
                boleto.setpFolio(getCallableStatement().getString(16));
                boleto.setBoletoId(getCallableStatement().getString(17));
                boleto.setTipoPago(getCallableStatement().getString(20));
                //boleto.setNoCuenta(getCallableStatement().getString(24));
                boleto.setStatus(CONSULTA_EXITOSA);
                boleto.setMensaje(getCallableStatement().getString(5));
                boleto.setSubTotal(boleto.getImporte());
                boleto.setTotal(boleto.getSubTotal() + boleto.getIva());
                String sucursalesTTP = "'CAPSETEX','CHALCO','CHOL','HUEJO','JCORONACO','MHIDALGO','SMA','SRRFRIO','SRSMA','SUPER','TIANG'";
                System.out.println("Ciudad Venta: "+getCallableStatement().getString(18));
                if (sucursalesTTP.indexOf("'"+getCallableStatement().getString(18)+"'")>=0)
                    boleto.setEmpresaFactura("SMA");
                else
                    boleto.setEmpresaFactura("CAPU");  //CAPU
            } else {
                boleto.setStatus(!CONSULTA_EXITOSA);
                boleto.setMensaje(getCallableStatement().getString(5));
            }


            //---------- Busca Desglose de IVA
            if (Boolean.valueOf(getCallableStatement().getString(4)) == CONSULTA_EXITOSA) {
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


  private void releaseTransaction() {
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
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the callableStatement
     */
    public CallableStatement getCallableStatement() {
        return callableStatement;
    }

    /**
     * @param callableStatement the callableStatement to set
     */
    public void setCallableStatement(CallableStatement callableStatement) {
        this.callableStatement = callableStatement;
    }

    /**
     * @return the preparedStatement
     */
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * @param preparedStatement the preparedStatement to set
     */
    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

 
   
}
