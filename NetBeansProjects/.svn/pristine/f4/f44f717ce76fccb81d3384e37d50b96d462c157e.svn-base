
/*
 * TmsVtaFacadeBean.java
 *
 * Created on 16 de abril de 2007, 12:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.solicitud;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.annotation.Resource;
import javax.sql.DataSource;
import tms_venta.util.NoBloqueoFoliosException;
import java.text.ParseException;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.ejb.EJBException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OraclePreparedStatement;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;

import tms_venta.entidad.TmsAutobusPlantLineasTbl;
import tms_venta.entidad.TmsAutobusPlantillasEncTbl;
import tms_venta.entidad.TmsBDConfigTbl;
import tms_venta.entidad.TmsBoletosVentaTbl;
import tms_venta.entidad.TmsComponenteBusTbl;
import tms_venta.entidad.TmsEstadosTbl;
import tms_venta.entidad.TmsEstadosV;
import tms_venta.entidad.TmsTipopagosV;
import tms_venta.entidad.TmsTiposPasajeroTbl;
import tms_venta.entidad.TmsVtaRvnV;

/**
 * Este Metodo contiene todas las declaraciones de las funciones utilizadas en las
 * anotaciones de los entity beans. las cuales seran utilizadas al instanciar este campo
 * @author imorales
 * @version 1.01 27-Marzo-2007
 */
@Stateless
public class TmsVtaFacadeBean implements tms_venta.solicitud.TmsVtaFacadeRemote {
    @PersistenceContext(unitName="TMSDB-ejbPU")
    private EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    private String caja = "";
    
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat formatoDebugFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
    /*
    * Clase del Entity Bean, que se usa en la aplicacion
    */
    public TmsVtaFacadeBean(){ }
    
    public java.util.List<TmsBDConfigTbl> queryTmsBaseDatosConfigTblAll() throws javax.ejb.EJBException{
        try{
            return em.createNamedQuery("TmsBDConfigTbl.findAll").getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    

    public java.util.List<TmsEstadosV> queryTmsEstadosVAll() throws javax.ejb.EJBException{
        System.out.println("+++++++++ Inicia "+new Date()+" queryTmsEstadosVAll +++++++++");
        java.util.List<TmsEstadosV> x = em.createNamedQuery("TmsEstadosV.findAll").getResultList();
        System.out.println("--------- Termina"+new Date()+" queryTmsEstadosVAll ---------");
        if(x.size()==0) return null;
        for(int i = 0; i<x.size(); i++)
            em.refresh(x.get(i));
        return x;
    }
        
    public Vector queryTmsServicioOrigsDestsV_vista(String RutasExcluidas) throws javax.ejb.EJBException{
        //return (java.util.List<TmsServicioOrigsDestsV>)em.createNamedQuery("TmsServicioOrigsDestsV.findAll").getResultList();
        String Consulta="SELECT RUTA_ID, RUTA_NUMERO, RUTA_NOMBRE, ORIGEN_ID, ORIGEN, "+
                        "SERVICIO_ID, SERVICIO_CLAVE, SERVICIO, LETRA, DESTINO_ID, DESTINO, TRAMO_VTA_REGRESO, " +
                        "RUTA_ORIGEN_ID, RUTA_ORIGEN, RUTA_DESTINO_ID, RUTA_DESTINO, ADICIONAL1 "+
                        "FROM TMS_SERVICIO_ORIGS_DESTS_V "+
                        "WHERE RUTA_NUMERO NOT IN ("+RutasExcluidas+") " +
                        "ORDER BY DESTINO";
        try{
            //System.out.println(Consulta);
        System.out.println("+++++++++ Inicia "+new Date()+" queryTmsServicioOrigsDestsV_vista +++++++++");
            Vector lista = (Vector) em.createNativeQuery(Consulta).getResultList();
        System.out.println("--------- Termina"+new Date()+" queryTmsServicioOrigsDestsV_vista ---------");
        return lista;
        }catch(Exception nrex){
            return null;
        }
    }
   
    public boolean actualizaTipoAsiento(String pDBSchema, String pDBLink, long corridaId, long noAsiento, String tipoAsiento) throws javax.ejb.EJBException {
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                    "UPDATE " +
                    pDBSchema+"TMS_ASIENTOS_NO_DISP_TBL"+pDBLink+" "+
                    "SET TIPO_PASAJERO='"+tipoAsiento+"' "+
                    "WHERE CORRIDA_ID="+corridaId+" AND NO_ASIENTO="+noAsiento;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            System.out.println(this.getCaja()+"=> Excepcion contropdada de actualizaTipoAsiento...");
            nrex.printStackTrace();
            return false;
        }
    }
        
    public java.util.List<TmsBoletosVentaTbl> BuscaBoletoVendido(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
            "SELECT * FROM " +
            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT "+
            "WHERE "+
            //"BVT.ORIGEN = '"+Origen+"'
            "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
            "AND	 BVT.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' "+
            "AND	 BVT.NO_ASIENTO = '"+noAsiento+"' "+
            "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' "+
            "AND         BVT.TIPO_OPERACION = 'VT' "+
            "AND         (BVT.TIPO_PAGO = 'EFE' OR BVT.TIPO_PAGO = 'BBV') "+
            "AND	 BVT.BOLETO_ID NOT IN "+
            "( "+
            "SELECT BSVT.BOLETO_RELACIONADO_ID "+
            "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT "+
            "WHERE BSVT.TIPO_OPERACION <> 'VT' "+
            "AND BSVT.BOLETO_RELACIONADO_ID IS NOT NULL " +
            ")";

        try{
            //System.out.println(Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?null:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?0:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?null:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?null:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?null:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?null:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?null:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?null:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            System.out.println(this.getCaja()+"=> Excepcion contropdada de Busca Boleto Vendido...");
            nrex.printStackTrace();
            return null;
        }
    }
    
    public java.util.List<TmsBoletosVentaTbl> BuscaBoletoVendidoParaCancelar(String pDBSchema, String pDBLink,
            String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
            "SELECT BVT.*, NVL(BVT.ADICIONAL14,BVT.ORIGEN),nvl(bd.viajado,'N') FROM  " +
            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT left join TMS_BOLETOS_VENTA_DATOS_TBL"+pDBLink+" bd on bd.boleto_id = BVT.BOLETO_ID  "+
            "WHERE "+
            "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
            "AND	 BVT.FOLIO_PREIMPRESO IN ("+FolioPreimpreso+") "+
            "AND	 BVT.NO_ASIENTO IN ("+noAsiento+") "+
            "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' "+
            "AND         BVT.TIPO_OPERACION = 'VT' "+
            "AND         (BVT.TIPO_PAGO = 'EFE' OR BVT.TIPO_PAGO = 'BBV') "+
            "AND	 BVT.BOLETO_ID NOT IN "+
            "( "+
            "SELECT BSVT.BOLETO_RELACIONADO_ID "+
            "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT "+
            "WHERE BSVT.TIPO_OPERACION <> 'VT' "+
            "AND BSVT.BOLETO_RELACIONADO_ID IS NOT NULL " +
            ") " +
            "ORDER BY TO_NUMBER(BVT.NO_ASIENTO)";
        try{
            //System.out.println(Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);


                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?null:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?null:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?null:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?null:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?null:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                //Buscar el importe del cargo
                double importe_cargo = 0;
                    Vector resp = (Vector)em.createNativeQuery("SELECT XER_TMS_PKG3.TMS_CARGO_HO_CN_BOL_FNC"+pDBLink+"('"+bv.getFolioPreimpreso()+"','CN') FROM DUAL").getResultList();
                    if(resp.size()>0)
                    {
                        Vector v = (Vector)resp.get(0);
                        importe_cargo = Double.valueOf(v.get(0).toString());
                    }
//                System.out.println("  importeBoleto: "+bv.getImporteBoleto());
//                System.out.println("  importe_cargo: "+importe_cargo);
//                bv.setAdicional15(""+bv.getImporteBoleto());
//                bv.setImporteBoleto(bv.getImporteBoleto()-importe_cargo);
                bv.setAdicional15(""+importe_cargo);
                System.out.println("  importeBoleto: "+bv.getImporteBoleto());
                System.out.println("  Adicional15: "+bv.getAdicional15());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?null:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?null:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?null:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                bv.setAdicional14(vBol.get(54)==null?"":vBol.get(54).toString());
                bv.setOrigenCorrida(vBol.get(54)==null?"":vBol.get(54).toString());
                bv.setViajado(vBol.get(55)==null?"N":vBol.get(55).toString());
                //54
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            System.out.println(this.getCaja()+"=> Excepcion contropdada de buscaBoletoVendidoPara Cancelar...");
            nrex.printStackTrace();
            return null;
        }
    }
    
     public java.util.List<TmsBoletosVentaTbl> BuscaBoletoVendidoParaHO(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
            "SELECT * FROM " +
            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT "+
            "WHERE "+
            //"BVT.ORIGEN = '"+Origen+"'
            "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
            "AND	 BVT.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' "+
            "AND	 BVT.NO_ASIENTO = '"+noAsiento+"' "+
            "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' "+
            "AND         BVT.TIPO_OPERACION IN ('VT', 'HO', 'AC','FO','FT','FC') "+
            //"AND         BVT.TIPO_PAGO IN ('EFE', 'BBV', 'BAB', 'OR1', '') "+
            "AND	 BVT.BOLETO_ID NOT IN "+
            "( "+
            "SELECT BSVT.BOLETO_RELACIONADO_ID "+
            "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT "+
            "WHERE BSVT.TIPO_OPERACION IN ('VA', 'CN','HO','FO','FC') "+
            "AND (BSVT.BOLETO_RELACIONADO_ID = BVT.BOLETO_ID) " +
            ")";
        try{
            //System.out.println(this.getCaja()+"=> busca boleto ya no valido (BuscaBoletoVendidoParaHO): \n"+Consulta);
            System.out.println(this.getCaja()+"=> BuscaBoletoVendidoParaHO: "+Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?null:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?null:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?null:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?null:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?null:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?null:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?null:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?null:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            System.out.println(this.getCaja()+"=> Excepcion contropdada de buscaBoletoVendidoParaHO...");
            nrex.printStackTrace();
            return null;
        }
    }
    
     public boolean BuscaBoletoValidoparaFO(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa, String folio) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
            "SELECT BOLETO_ID FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" WHERE TIPO_OPERACION = 'FT' AND ADICIONAL4 = '"+folio+"' ";                
//            "SELECT BF.* FROM " +
//            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT , " +
//            pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BF  " +
//            "WHERE "+
//            "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
//            "AND	 BVT.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' "+
//            "AND	 BVT.NO_ASIENTO = '"+noAsiento+"' "+
//            "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' " +
//            "AND         BF.BOLETO_ID = BVT.BOLETO_ID " +
//            "AND         BF.ESTADO_REFERENCIA = 'C'" +
//            "AND         BVT.BOLETO_ID IN (SELECT BVT2.BOLETO_RELACIONADO_ID FROM TMS_BOLETOS_VENTA_TBL BVT2 where BVT2.TIPO_OPERACION = 'FT')";
//        String Consulta = "SELECT BF.BOLETO_REFERENCIADO_ID  from " +
//                pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BF where BF.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' and  BF.ESTADO_REFERENCIA = 'P'";
        Vector vX = new Vector();
         System.out.println(this.getCaja()+"=> BuscaBoletoValidoparaFO : "+Consulta);
        try {
            vX = (Vector) em.createNativeQuery(Consulta).getResultList();
        } catch(Exception nrex) {
            System.out.println(this.getCaja()+"=> Excepcion contropdada de BuscaBoletoValidoparaFO...");
            nrex.printStackTrace();
            return false;
        }
           // if(vX.size()>0) return true; 
         if(vX.size()==0) return true;
        return false;
     }
     
//     public boolean BuscaBoletoValidoparaFT(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException{
     public boolean BuscaBoletoValidoparaFT(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa, String folioBoleto) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
        "SELECT BOLETO_ID FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" WHERE TIPO_OPERACION = 'FT' AND ADICIONAL4 = '"+folioBoleto+"' ";
//            "SELECT BVT.* FROM " +
//            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT  " +
//            //pDBSchema+",TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BF  " +
//            "WHERE "+
//            "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
//            "AND	 BVT.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' "+
//            "AND	 BVT.NO_ASIENTO = '"+noAsiento+"' "+
//            "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' " +
//            //"AND         BF.BOLETO_ID = BVT.BOLETO_ID " +
//            //"AND         BF.ESTADO_REFERENCIA = 'C'" +
//            "AND         BVT.BOLETO_ID IN (SELECT BVT2.BOLETO_RELACIONADO_ID FROM TMS_BOLETOS_VENTA_TBL BVT2 where BVT2.TIPO_OPERACION = 'FT')";
////        String Consulta = "SELECT BF.BOLETO_REFERENCIADO_ID  from " +
////                pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BF where BF.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' and  BF.ESTADO_REFERENCIA = 'P'";
        System.out.println(this.getCaja()+"=> BuscaBoletoValidoparaFT: "+Consulta);
        Vector vX = new Vector();
        try {
            vX = (Vector) em.createNativeQuery(Consulta).getResultList();
        }catch(Exception nrex) {
            System.out.println(this.getCaja()+"=> Excepcion contropdada de BuscaBoletoValidoparaFT...");
            nrex.printStackTrace();
            return false;
        }
            //if(vX.size()>0) return true;
        if(vX.size()==0) return true;
        return false;
     }     
    
    public java.util.List<TmsBoletosVentaTbl> BuscaBoletoAbierto(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String Servicio, String empresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta=
            "SELECT * FROM " +
            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT "+
            "WHERE  BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
            "AND    BVT.SERVICIO = '"+Servicio+"' "+
            "AND    BVT.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' "+
            "AND    BVT.EMPRESA = '"+empresa+"' "+
            "AND    BVT.TIPO_OPERACION = 'VA'"+
            "AND    BVT.BOLETO_ID NOT IN "+
            "( "+
            "SELECT BSVT.BOLETO_RELACIONADO_ID "+
            "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT "+
            "WHERE BSVT.TIPO_OPERACION <> 'VA'"+
            "AND BSVT.BOLETO_RELACIONADO_ID IS NOT NULL "+
            ") " +
            "AND (TRUNC(BVT.FECHA_HORA_VENTA)+BVT.DIAS_VALIDEZ_BOLETO_ABIERTO) >= TRUNC(SYSDATE) " +
            "ORDER BY BVT.BOLETO_ID";

        try{
            //System.out.println(Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?0:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?0:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?0:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?0:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?0:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?0:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?0:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?0:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            System.out.println(this.getCaja()+"=> Excepcion contropdada de BuscaBoletoAbierto...");
            nrex.printStackTrace();
            return null;
        }
    }

    public String[] queryBoletoConTJAbierta(String pDBSchema, String pDBLink, String claveCorrida) throws javax.ejb.EJBException{
        Vector x;
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
            "SELECT ETJ.NOMBRE_ESTADO, TVJ.FECHA_CREACION "+
            "FROM TMS_CORRIDAS_TBL"+pDBLink+" CVT, "+
            "TMS_TARJETAS_VIAJE_TBL"+pDBLink+" TVJ, "+
            "TMS_ESTADOS_TARJETA_VIAJE_TBL"+pDBLink+" ETJ "+
            "WHERE CVT.CLAVE_CORRIDA = '"+claveCorrida+"' "+
            "AND   TVJ.CORRIDA_ID = CVT.CORRIDA_ID "+
            "AND   TVJ.ESTADO_TARJETA_ID = ETJ.ESTADO_TARJETA_VIAJE_ID";

        try{
            System.out.println(this.getCaja()+"=> queryBoletoConTJAbierta: "+Consulta);
            x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            String dt[] = new String[2];
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return null;
            dt[0]=y.get(0).toString();
            dt[1]=y.get(1).toString();
            return dt;
        }catch(Exception nrex){
            System.out.println(this.getCaja()+"=> Excepcion contropdada de queryBoletoConTJAbierta...");
            nrex.printStackTrace();;
            return null;
        }
    }
    
    /*********************** CONSULTAS REMOTAS *******************************/
    public boolean queryTmsCorridasVentaBA(String pDBSchema, String pDBLink, String strRutaOrigen, String strOrigen, String strHoy, String strFecha, String strServicio, String strDestino, String strEmpresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        Vector yvector = new Vector();
        String Consulta =
                "SELECT  1 " +
                "FROM TMS_CORRIDAS_CONSULTA_V"+pDBLink+" CORRIDAS " +
                "WHERE (CORRIDAS.FECHA_HORA_CORRIDA BETWEEN (TO_DATE('"+strHoy+"','DD/MM/YYYY HH24:MI')-(NVL((SELECT TO_NUMBER(TMSV.ADICIONAL1) " +
                "FROM TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV " +
                "WHERE (TMSV.RUTA_ID = CORRIDAS.RUTA_ID) AND (TMSV.ORIGEN = '"+strOrigen+"') AND (TMSV.DESTINO LIKE '"+strDestino+"') AND (ROWNUM=1)),0)/1440)) AND (TO_DATE('"+strHoy+"','DD/MM/YYYY HH24:MI')+1)) " +
                "AND (CORRIDAS.RUTA_ID IN ( " +
                "SELECT TMSV.RUTA_ID RUTA_ID " +
                "FROM TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV " +
                "WHERE (TMSV.ORIGEN = '"+strOrigen+"') AND (TMSV.RUTA_ORIGEN = '"+strRutaOrigen+"') AND (TMSV.DESTINO "+(strDestino.equals("%")?"LIKE '"+strDestino+"'":"IN ('"+strDestino+"')")+"))) " +
                "AND CORRIDAS.SERVICIO IN ("+strServicio+") " +
                "AND CORRIDAS.EMPRESA LIKE '"+strEmpresa+"' " +
                "ORDER BY CORRIDAS.FECHA_HORA_CORRIDA";

        try{
            //System.out.println(this.getCaja()+"=> ConsultaBA: "+Consulta);
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(yvector==null || yvector.size()==0) return false;
            return true;
        }catch(Exception nrex){
            
            System.out.println(this.getCaja()+"=> Excepcion contropdada de queryTmsCorridasVentaBA...");
            nrex.printStackTrace();
            return false;
        }
    }
    
    public int liberaAsientoCancelado(String pDBSchema, String pDBLink, String ClaveCorrida, int noAsiento, String tipoPasaje, String ctd) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String campo="";
        if(tipoPasaje.equals("M")) campo = ",MENORES_CORRIDA=MENORES_CORRIDA+"+ctd;
            else if(tipoPasaje.equals("S")) campo = ",SENECTUD_CORRIDA=SENECTUD_CORRIDA+"+ctd;
                 else if(tipoPasaje.equals("E")) campo = ",ESTUDIANTES_CORRIDA=ESTUDIANTES_CORRIDA+"+ctd;
                      else if(tipoPasaje.equals("P")) campo = ",PROFESORES_CORRIDA=PROFESORES_CORRIDA+"+ctd;
                           else if(tipoPasaje.equals("C")) campo = ",CORTESIAS_CORRIDA=CORTESIAS_CORRIDA+"+ctd;
        
        String Consulta =
                    "DECLARE dummy NUMBER; BEGIN " +
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+pDBLink+" " +
                    "WHERE CLAVE_CORRIDA='"+ClaveCorrida+"' "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    pDBSchema+"TMS_CORRIDAS_VENTA_TBL"+pDBLink+" "+
                    "SET ASIENTO"+noAsiento+"='D' "+campo+" "+
                    "WHERE CLAVE_CORRIDA='"+ClaveCorrida+"'; " +
                    "DELETE FROM " +
                    pDBSchema+"TMS_ASIENTOS_NO_DISP_TBL"+pDBLink+" "+
                    "WHERE CORRIDA_ID=( "+
                                      "SELECT CX.CORRIDA_ID "+
                                      "FROM "+pDBSchema+"TMS_CORRIDAS_VENTA_TBL"+pDBLink+" CX "+
                                      "WHERE CX.CLAVE_CORRIDA = '"+ClaveCorrida+"' "+
                                      ") "+
                    "AND NO_ASIENTO='"+noAsiento+"'; COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            //System.out.println(this.getCaja()+"=> msg: "+nrex.getCause().getMessage());
            //System.out.println(this.getCaja()+"=> msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            System.out.println(this.getCaja()+"=> Excepcion contropdada de liberaAsientoCancelado...");
            nrex.printStackTrace();
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")) return -1;
            return 1;
        }
    }
    
    public int _liberaAsientoCancelado(String pDBLink, String CorridaClave, String cadCampoAsientos, String cadCampoTipoPasaje, String noAsientos) throws javax.ejb.EJBException{
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        if(!cadCampoTipoPasaje.equals("")) cadCampoAsientos+=",";
        String Consulta =
                    "DECLARE dummy NUMBER; BEGIN " +
                    "BEGIN SELECT 1 INTO dummy " +
                    "FROM TMS_CORRIDAS_VENTA_TBL"+pDBLink+" " +
                    "WHERE CLAVE_CORRIDA='"+CorridaClave+"' "+
                    "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+pDBLink+" "+
                    "SET "+cadCampoAsientos+" "+cadCampoTipoPasaje+" "+
                    "WHERE CLAVE_CORRIDA='"+CorridaClave+"'; " +
                    "DELETE FROM " +
                    "TMS_ASIENTOS_NO_DISP_TBL"+pDBLink+" "+
                    "WHERE CORRIDA_ID=( "+
                                      "SELECT CX.CORRIDA_ID "+
                                      "FROM TMS_CORRIDAS_VENTA_TBL"+pDBLink+" CX "+
                                      "WHERE CX.CLAVE_CORRIDA = '"+CorridaClave+"' "+
                                      ") "+
                    "AND NO_ASIENTO IN ("+noAsientos+"); COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            //System.out.println(this.getCaja()+"=> msg: "+nrex.getCause().getMessage());
            //System.out.println(this.getCaja()+"=> msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            System.out.println(this.getCaja()+"=> Excepcion contropdada de _liberaAsientoCancelado...");
            nrex.printStackTrace();
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")) return -1;
            return 1;
        }
    }
    
    public boolean LiberaCtdTipoPasaje(String pDBSchema, String pDBLink, long corridaId, String tipoPasaje, String ctd, int asiento, String estado) throws javax.ejb.EJBException {
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String campo="";
        if(tipoPasaje.equals("M")) campo = "MENORES_CORRIDA=MENORES_CORRIDA+"+ctd+", ";
            else if(tipoPasaje.equals("S")) campo = "SENECTUD_CORRIDA=SENECTUD_CORRIDA+"+ctd+", ";
                 else if(tipoPasaje.equals("E")) campo = "ESTUDIANTES_CORRIDA=ESTUDIANTES_CORRIDA+"+ctd+", ";
                      else if(tipoPasaje.equals("P")) campo = "PROFESORES_CORRIDA=PROFESORES_CORRIDA+"+ctd+", ";
                           else if(tipoPasaje.equals("C")) campo = "CORTESIAS_CORRIDA=CORTESIAS_CORRIDA+"+ctd+", ";
        String Consulta =
                    "UPDATE " +
                    pDBSchema+"TMS_CORRIDAS_VENTA_TBL"+pDBLink+" "+
                    "SET "+campo+"ASIENTO"+asiento+"='"+estado+"' "+
                    "WHERE CORRIDA_ID="+corridaId;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println(this.getCaja()+"=> Excepcion contropdada de LiberaCtdTipoPasaje...");
            return false;
        }
    }
    
    public boolean LiberaCtdTipoPasajeRvn(String pDBSchema, String pDBLink, long corridaId, String tipoPasaje, String ctd, int asiento, String estado) throws javax.ejb.EJBException {
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String campo="";
        if(tipoPasaje.equals("M")) campo = "MENORES_CORRIDA=MENORES_CORRIDA+"+ctd+", ";
            else if(tipoPasaje.equals("S")) campo = "SENECTUD_CORRIDA=SENECTUD_CORRIDA+"+ctd+", ";
                 else if(tipoPasaje.equals("E")) campo = "ESTUDIANTES_CORRIDA=ESTUDIANTES_CORRIDA+"+ctd+", ";
                      else if(tipoPasaje.equals("P")) campo = "PROFESORES_CORRIDA=PROFESORES_CORRIDA+"+ctd+", ";
                           else if(tipoPasaje.equals("C")) campo = "CORTESIAS_CORRIDA=CORTESIAS_CORRIDA+"+ctd+", ";
        String Consulta =
                    "UPDATE " +
                    pDBSchema+"TMS_CORRIDAS_VENTA_TBL"+pDBLink+" "+
                    "SET "+campo+"ASIENTO"+asiento+"='"+estado+"' "+
                    "WHERE CORRIDA_ID="+corridaId;
        String Consulta2 =
                    "DELETE FROM " +
                    pDBSchema+"TMS_ASIENTOS_NO_DISP_TBL"+pDBLink+" "+
                    "WHERE CORRIDA_ID="+corridaId+" AND NO_ASIENTO="+asiento;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            if(em.createNativeQuery(Consulta2).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            System.out.println(this.getCaja()+"=> Excepcion contropdada de LiberaCtdTipoPasajeRvn...");
            nrex.printStackTrace();
            return false;
        }
    }
    
    public int _ModificaEstadoAsientoCorrida(String pDBLink, long corridaId, int asiento, String estado) throws javax.ejb.EJBException {
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta = "DECLARE dummy NUMBER; BEGIN " +
                "BEGIN SELECT 1 INTO dummy " +
                "FROM TMS_CORRIDAS_VENTA_TBL"+pDBLink+" " +
                "WHERE CORRIDA_ID="+corridaId+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+pDBLink+" "+
                    "SET ASIENTO"+asiento+"='"+estado+"' "+
                    "WHERE CORRIDA_ID="+corridaId+"; COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        //System.out.println(this.getCaja()+"=> Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            //System.out.println(this.getCaja()+"=> msg: "+nrex.getCause().getMessage());
            //System.out.println(this.getCaja()+"=> msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            System.out.println(this.getCaja()+"=> Excepcion contropdada de _ModificaEstadoAsientoCorrida...");
            nrex.printStackTrace();            
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")) return -1;
            return 1;
        }
    }
    
    public int _ModificaCtdTipoPasajeRem(String pDBLink, long corridaId, String tipoPasaje, String ctd) throws javax.ejb.EJBException {
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String campo="";
        if(tipoPasaje.equals("M")) campo = "MENORES_CORRIDA";
            else if(tipoPasaje.equals("S")) campo = "SENECTUD_CORRIDA";
                 else if(tipoPasaje.equals("E")) campo = "ESTUDIANTES_CORRIDA";
                      else if(tipoPasaje.equals("P")) campo = "PROFESORES_CORRIDA";
                           else if(tipoPasaje.equals("C")) campo = "CORTESIAS_CORRIDA";

        String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM TMS_CORRIDAS_VENTA_TBL"+pDBLink+" " +
                "WHERE CORRIDA_ID="+corridaId+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+pDBLink+" "+
                    "SET "+campo+"= "+campo+"+"+ctd+" "+
                    "WHERE CORRIDA_ID="+corridaId+"; COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
        //System.out.println(this.getCaja()+"=> Consulta: "+Consulta);
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            //System.out.println(this.getCaja()+"=> msg: "+nrex.getCause().getMessage());
            //System.out.println(this.getCaja()+"=> msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")) return -1;
            return 1;
        }
    }

    public String ReservacionRem(String terminalPrefijoId, String pDBSchema, String pDBLink, String Campos, String Valores) throws javax.ejb.EJBException {
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                        "INSERT INTO " +
                        pDBSchema+"TMS_RESERVACIONES_TBL"+pDBLink+" " +
                        Campos + " VALUES "+ Valores;
        try{
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return null;
            Consulta =
                        "SELECT " +
                        pDBSchema+"TMS_RESERVACIONES_SEQ.CURRVAL"+pDBLink+
                        " FROM DUAL";
            Vector x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x.get(0)==null || x==null || x.size()==0) return null;
            return x.get(0).toString();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public java.util.List<TmsVtaRvnV> queryTmsVtaRvnVFindByCRvnRem(String pDBSchema, String pDBLink, String cRvn, String nResp) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        java.util.List<TmsVtaRvnV> x = new ArrayList<TmsVtaRvnV>();
        Vector z = new Vector();
        Vector zx= new Vector();
        String Consulta =
                        "SELECT v.* , to_char(v.FECHA_CREACION,'DD/MM/YYYY') FCREACION,to_char(v.FECHA_CREACION,'HH24:MI:SS') HCREACION,to_char(v.FECHA_HORA_CORRIDA,'DD/MM/YYYY') FCORRIDA,to_char(v.FECHA_HORA_CORRIDA,'HH24:MI:SS') HCORRIDA " +
                        "FROM "+pDBSchema+"Tms_Vta_Rvn_V"+pDBLink+" v " +
                        "where v.responsable_Reservacion LIKE '%'||NVL('"+nResp+"',responsable_Reservacion)||'%' "+
                        "AND v.clave_Reservacion = NVL('"+cRvn+"',v.clave_Reservacion) "+
                        "AND TRUNC(v.FECHA_HORA_CORRIDA) >= TRUNC(SYSDATE) "+
                        "ORDER BY v.FECHA_CREACION DESC, v.NO_ASIENTO";
        try{
            //System.out.println(Consulta);
            z = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(z==null || z.size()==0) return null;
            int i;
            TmsVtaRvnV tmsVtaRvnV;
            for(i=0; i<z.size(); i++){
                zx = (Vector) z.get(i);
                tmsVtaRvnV = new TmsVtaRvnV();
                tmsVtaRvnV.setReservacionId(Long.valueOf(zx.get(0).toString()));
                tmsVtaRvnV.setClaveReservacion(zx.get(1).toString());
                if(zx.get(2)!=null) tmsVtaRvnV.setClienteId(Long.valueOf(zx.get(2).toString()));
                tmsVtaRvnV.setResponsableReservacion(zx.get(3).toString());
                tmsVtaRvnV.setCancelable(zx.get(4).toString());
                tmsVtaRvnV.setTotalRvdos(Long.valueOf(zx.get(5).toString()));
                tmsVtaRvnV.setCorridaId(Long.valueOf(zx.get(6).toString()));
                tmsVtaRvnV.setClaveCorrida(zx.get(7).toString());
                tmsVtaRvnV.setNoAsiento(Long.valueOf(zx.get(8).toString()));
                tmsVtaRvnV.setTipoPasajero(zx.get(9).toString());
                try {
                    tmsVtaRvnV.setFechaCreacion(new Timestamp(formatoFecha.parse(zx.get(10).toString()).getTime()));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return null;
                }
                tmsVtaRvnV.setEstadoReservacion(zx.get(11).toString());
                try {
                    tmsVtaRvnV.setFechaHoraCorrida(new Timestamp(formatoFecha.parse(zx.get(12).toString()).getTime()));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return null;
                }
                tmsVtaRvnV.setOrigen(zx.get(13).toString());
                tmsVtaRvnV.setDestino(zx.get(14).toString());
                tmsVtaRvnV.setServicio(zx.get(15).toString());
                tmsVtaRvnV.setEmpresa(zx.get(16).toString());
                tmsVtaRvnV.setTotalCndos(Long.valueOf(zx.get(17).toString()));
                tmsVtaRvnV.setTotalVta(Long.valueOf(zx.get(18).toString()));
                tmsVtaRvnV.setRutaId(Long.valueOf(zx.get(19).toString()));

                tmsVtaRvnV.setfCreacion(zx.get(20).toString());
                tmsVtaRvnV.sethCreacion(zx.get(21).toString());
                tmsVtaRvnV.setFCorrida(zx.get(22).toString());
                tmsVtaRvnV.setHCorrida(zx.get(23).toString());
                
                x.add(tmsVtaRvnV);
            }
            return x;
        }
        catch(NoResultException nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public String queryTmsVtaRvnVFindByCRvnRem2(String pDBSchema, String pDBLink, String cRvn, String nResp) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        java.util.List<TmsVtaRvnV> x = new ArrayList<TmsVtaRvnV>();
        Vector z = new Vector();
        Vector zx= new Vector();
        String Consulta =
                        "SELECT V.RESERVACION_ID ,V.CLAVE_RESERVACION,V.CLIENTE_ID,V.RESPONSABLE_RESERVACION,V.CANCELABLE,V.TOTAL_RVDOS,V.CORRIDA_ID,V.CLAVE_CORRIDA,V.NO_ASIENTO,V.TIPO_PASAJERO,TO_CHAR(V.FECHA_CREACION,'RRRR-MM-DD HH24:MI:SS'),V.ESTADO_RESERVACION,TO_CHAR(V.FECHA_HORA_CORRIDA,'RRRR-MM-DD HH24:MI:SS'),V.ORIGEN,V.DESTINO,V.SERVICIO,V.EMPRESA,V.TOTAL_CNDOS,V.TOTAL_VTA, V.RUTA_ID " +
                        ", to_char(v.FECHA_CREACION,'DD/MM/YYYY') FCREACION,to_char(v.FECHA_CREACION,'HH24:MI:SS') HCREACION,to_char(v.FECHA_HORA_CORRIDA,'DD/MM/YYYY') FCORRIDA,to_char(v.FECHA_HORA_CORRIDA,'HH24:MI:SS') HCORRIDA " +
                        "FROM "+pDBSchema+"Tms_Vta_Rvn_V"+pDBLink+" v " +
                        "where v.responsable_Reservacion LIKE '%'||NVL('"+nResp+"',responsable_Reservacion)||'%' "+
                        "AND v.clave_Reservacion = NVL('"+cRvn+"',v.clave_Reservacion) "+
                        "AND TRUNC(v.FECHA_HORA_CORRIDA) >= TRUNC(SYSDATE) "+
                        "ORDER BY v.FECHA_CREACION DESC, v.NO_ASIENTO";
        try{
            //System.out.println(Consulta);
            z = (Vector) em.createNativeQuery(Consulta).getResultList();
            System.out.println(this.getCaja()+"=> el vector de reservaciones es: "+z);
            if(z==null || z.size()==0) return null;
            String cadena = "";
            for(int i =0; i<z.size(); i++)
            {
                if(i==0)
                {
                    String s = z.get(i).toString();
                    System.out.println(this.getCaja()+"=> cadena primera: "+s);
                    s = s.replace('[',' ');
                    s = s.replace(']',' ');                      
                    cadena = s.trim();
                    System.out.println(this.getCaja()+"=> cadena quedaria: "+cadena);
                }
                else
                {
                    String s = z.get(i).toString();
                     s = s.replace('[',' ');
                     s = s.replace(']',' ');                      
                    cadena = cadena + "|"+ s.trim();
                }
            }
            return cadena;
        }
        catch(NoResultException nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public boolean actualizaEdoReservacionRem(String pDBSchema, String pDBLink, long IdRvn, String Edo, long idusuario) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                    "update " +
                    pDBSchema+"Tms_Reservaciones_Tbl"+pDBLink+" "+
                    "set ESTADO_RESERVACION='"+Edo+"', ULTIMA_ACTUALIZACION_POR="+idusuario+", ULTIMA_FECHA_ACTUALIZACION=SYSDATE "+
                    "where RESERVACION_ID="+IdRvn;
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            return false;
        }
    }
    
    public boolean existeRecoleccionEnSesion(String corteId) throws javax.ejb.EJBException{
        String resultado;
        String consultaReco;
        int numReco;
        consultaReco = "SELECT COUNT(1)" +
                " FROM TMS_CORTES_TBL            tmses" +
                " ,TMS_SESION_ACTIVIDADES_TBL tmssa" +
                " ,TMS_ACTIVIDADES_SESION_TBL tmsact" +
                " WHERE tmses.CORTE_ID = tmssa.CORTE_ID" +
                " AND tmssa.ACTIVIDAD_SESION_ID = tmsact.ACTIVIDAD_SESION_ID" +
                " AND tmsact.CLAVE_ACTIVIDAD = 'RECOL'" +
                " AND tmses.CORTE_ID = "+corteId;
        resultado = String.valueOf(em.createNativeQuery(consultaReco).getSingleResult());
        resultado = resultado.replace("[","");
        resultado = resultado.replace("]","");
        numReco = Integer.parseInt(resultado);
        if(numReco >= 1)
            return true;
        else
            return false;
    }

    public long esUsuarioSupervisorConFuncion(String funcionNumero, String usuarioNumero, String pwdEnc) throws javax.ejb.EJBException{
        String consultaUsuario = "SELECT DISTINCT(tmsus.USUARIO_ID) " +
                " FROM TMS_USUARIOS_TBL tmsus " +
                " ,TMS_USUARIO_PERFILES_TBL tmsup " +
                " ,TMS_PERFILES_TBL tmspe " +
                " ,TMS_MENUS_ENCABEZADO_TBL tmsme " +
                " ,TMS_MENUS_LINEAS_TBL    tmsml " +
                " ,TMS_FUNCIONES_TBL 		tmsfn " +
                " WHERE tmsus.USUARIO_ID = tmsup.USUARIO_ID " +
                " AND tmsup.PERFIL_ID  = tmspe.PERFIL_ID " +
                " AND tmspe.MENU_ID    = tmsme.MENU_ID " +
                " AND tmsme.MENU_ID    = tmsml.MENU_ID " +
                " AND tmsml.FUNCION_ID = tmsfn.FUNCION_ID " +
                " AND tmsfn.FUNCION_NUMERO = '"+funcionNumero+"' " +
                " AND tmsus.USUARIO_NUMERO = '"+usuarioNumero+"' "+
                " AND tmsus.CONTRASENA_ENCRIPTADA = '"+pwdEnc+"' " +
                " AND tmsus.UBICACION_ID = (select terminal_id from tms_base_datos_config_tbl where esquema_propio = 'S')";
        
        try{
            Object resultado = em.createNativeQuery(consultaUsuario).getSingleResult();
            if(resultado==null) return -1;
            String valor = resultado.toString().replace("[","");
            valor = valor.toString().replace("]","");
            return Long.valueOf(valor);
        }catch(NoResultException ex){
            return -1;
        }
    }
    
    public Object[] obtieneFolios(long corteId, long empresaId) throws javax.ejb.EJBException{
        String Consulta =
            "SELECT "+
            "SACT.VALOR_ACTIVIDAD FI "+
            ",ZACT.VALOR_ACTIVIDAD FF "+
            ",((SELECT TO_NUMBER(ACT.VALOR_ACTIVIDAD) FROM TMS_SESION_ACTIVIDADES_TBL ACT "+
            "WHERE ACT.CORTE_ID = SACT.CORTE_ID " +
            "AND   ACT.EMPRESA_ID = SACT.EMPRESA_ID "+
            "AND   ACT.ACTIVIDAD_SESION_ID = (SELECT X.ACTIVIDAD_SESION_ID FROM TMS_ACTIVIDADES_SESION_TBL X WHERE X.CLAVE_ACTIVIDAD='ULTFOLVEN') "+
            ")+1) BACT "+
            "FROM "+
            "TMS_SESION_ACTIVIDADES_TBL SACT "+
            ",TMS_SESION_ACTIVIDADES_TBL ZACT "+
            ",TMS_ACTIVIDADES_SESION_TBL BX "+
            ",TMS_ACTIVIDADES_SESION_TBL BZ "+
            "WHERE "+
            "SACT.CORTE_ID="+corteId+" AND SACT.EMPRESA_ID = "+empresaId+" "+
            "AND ZACT.CORTE_ID=SACT.CORTE_ID AND SACT.EMPRESA_ID = ZACT.EMPRESA_ID "+
            "AND SACT.VALOR_SECUENCIAL = "+
            "(SELECT "+
            "MAX(TO_NUMBER(A.VALOR_SECUENCIAL)) VS "+
            "FROM "+
            "TMS_SESION_ACTIVIDADES_TBL A "+
            "WHERE "+
            "A.CORTE_ID = SACT.CORTE_ID "+
            "AND  A.EMPRESA_ID=SACT.EMPRESA_ID " +
            "AND  A.ACTIVIDAD_SESION_ID <> (SELECT XA.ACTIVIDAD_SESION_ID FROM TMS_ACTIVIDADES_SESION_TBL XA WHERE XA.CLAVE_ACTIVIDAD = 'RECOL')) "+
            "AND SACT.VALOR_SECUENCIAL = ZACT.VALOR_SECUENCIAL "+
            "AND (BX.CLAVE_ACTIVIDAD = 'FOLINI' OR BX.CLAVE_ACTIVIDAD = 'REFOLINI') "+
            "AND BX.ACTIVIDAD_SESION_ID = SACT.ACTIVIDAD_SESION_ID "+
            "AND (BZ.CLAVE_ACTIVIDAD = 'FOLFIN' OR BZ.CLAVE_ACTIVIDAD = 'REFOLFIN') "+
            "AND BZ.ACTIVIDAD_SESION_ID = ZACT.ACTIVIDAD_SESION_ID";
        try{
            System.out.println(this.getCaja()+"=> Obtiene folios "+Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            //System.out.println(this.getCaja()+"=> XXXXXXXXXXXXXX    "+x.toString());
            if(x.size()==0) return null;
            Object[] z = new Object[3];
            z[0] = x.get(0); z[1] = x.get(1); z[2] = x.get(2);
            return z;
        }
        catch(NoResultException nrex){
            nrex.printStackTrace();
            return null;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public String obtieneFondoInicial(long corteId, long empresaId) throws javax.ejb.EJBException{
        String Consulta =
            "SELECT "+
            "SACT.VALOR_ACTIVIDAD FI "+
            "FROM "+
            "TMS_SESION_ACTIVIDADES_TBL SACT "+
            ",TMS_ACTIVIDADES_SESION_TBL BX "+
            "WHERE SACT.CORTE_ID="+corteId+" "+
	    "AND   BX.ACTIVIDAD_SESION_ID = SACT.ACTIVIDAD_SESION_ID "+
            "AND   BX.CLAVE_ACTIVIDAD = 'FONDINI'";
        try{
            //System.out.println(this.getCaja()+"=> Obtiene fondo de sesion original "+Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return null;
            return y.get(0).toString();
        }
        catch(NoResultException nrex){
            return null;
        }
        catch(Exception ex){
            return null;
        }
    }
    
    public Vector FuncionesDeUsuario(long MENU_ID) throws javax.ejb.EJBException{
        String Consulta =
            "select fun.FUNCION_NUMERO, fun.DESCRIPCION, fun.AUDITABLE from "+
            "tms_funciones_tbl fun "+
            ",tms_menus_encabezado_tbl men "+
            ",tms_menus_lineas_tbl mlin "+
            "where men.MENU_ID = "+MENU_ID+" "+
            "and men.MENU_ID = mlin.MENU_ID "+
            "and mlin.FUNCION_ID = fun.FUNCION_ID " +
            "order by fun.FUNCION_NUMERO";
        try{
            System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
             System.out.println(this.getCaja()+"=> \n"+x);
            return x;
        }
        catch(NoResultException nrex){
            return null;
        }
    }
    
    public String[] FuncionAutorizadaPorSupervisor(String USUARIO_NUMERO, String FUNCION_NUMERO) throws javax.ejb.EJBException{
        String Consulta =
            "SELECT "+
            "A.USUARIO_ID, "+
            "F.FUNCION_NUMERO, "+
            "F.DESCRIPCION, "+
            "F.AUDITABLE "+
            "FROM "+
            "TMS_usuarios_Tbl A, "+
            "Tms_usuario_Perfiles_tbl B, "+
            "Tms_perfiles_tbl C, "+
            "TMS_MENUS_ENCABEZADO_TBL D, "+
            "TMS_MENUS_LINEAS_TBL E, "+
            "TMS_FUNCIONES_TBL F "+
            "WHERE "+
            "A.USUARIO_NUMERO = '"+USUARIO_NUMERO+"' "+
            "AND F.FUNCION_NUMERO = '"+FUNCION_NUMERO+"' "+
            "AND A.USUARIO_ID = B.USUARIO_ID AND "+
            "B.PERFIL_ID = C.PERFIL_ID AND "+
            "C.MENU_ID = D.MENU_ID AND "+
            "D.MENU_ID = E.MENU_ID "+
            "AND E.FUNCION_ID = F.FUNCION_ID";
        try{
            //System.out.println(Consulta);
            Vector y = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(y.size()==0) return null;
            //System.out.println(this.getCaja()+"=> Funcion "+y);
            Vector x = (Vector) y.get(0);
            String[] z = new String[4];
            z[0]=x.get(0).toString(); z[1]=x.get(1).toString(); z[2]=x.get(2).toString(); z[3]=x.get(3).toString();
            return z;
        }catch(NoResultException nrex){
            return null;
        }
    }
    
    public boolean FuncionAuditable(String Valores) throws javax.ejb.EJBException {
        String Consulta = "INSERT INTO TMS_AUDITORIA_TBL " +
                          "VALUES ("+ Valores+") ";
        try{
            //System.out.println(this.getCaja()+"=> Consulta: "+Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public java.util.List<TmsTipopagosV> queryTmsTipopagosVFindAll() throws javax.ejb.EJBException{
        try{
            System.out.println("manda a buscar queryTmsTipopagosVFindAll...");
            return em.createNamedQuery("TmsTipopagosV.findAll").getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public java.util.List<TmsTipopagosV> queryTmsTipopagosCallVFindAll() throws javax.ejb.EJBException{
        try{
            //return em.createNamedQuery("TmsTipopagosCallV.findAll").getResultList();
            return em.createNativeQuery("select * from TMS_TIPOPAGOS_CALL_V",TmsTipopagosV.class).getResultList();
        }catch(Exception ex){
            return null;
        }
    }    
    
    public String queryTmsTipopagosVFindById(long tipoPagoId) throws javax.ejb.EJBException {
        try{
            return em.createNamedQuery("TmsTipopagosV.findAllById").
                setParameter("TipoPagoId",tipoPagoId).getSingleResult().toString();
        }
        catch(NoResultException nrex){
            return "";
        }
    }
    
    /**
     * <code>select o from TmsTipospasajeroTbl o</code>queTiposPasajeVendo
     * @return 
     */
    public java.util.List<TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblFindAll() throws javax.ejb.EJBException {
        return em.createNamedQuery("TmsTiposPasajeroTbl.findAll").getResultList();
    }
    
    public java.util.List<TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblFindAble() throws javax.ejb.EJBException {
        String consulta = "SELECT * FROM TMS_TIPOS_PASAJERO_TBL TP "+
                          "WHERE TRUNC(SYSDATE) BETWEEN TRUNC(TP.FECHA_DESDE) AND TRUNC(NVL(TP.FECHA_HASTA,TRUNC(SYSDATE)))";
        return em.createNativeQuery(consulta,TmsTiposPasajeroTbl.class).getResultList();
    }

    public java.util.List<TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblFindAble2() throws javax.ejb.EJBException {
        String consulta = "SELECT TDP.DESCUENTO_RUTA_ID, TP.TIPO_PASAJERO_ID, TP.NOMBRE_TIPO, TP.LETRA_TIPO, TDP.PCT_DESCUENTO,TP.FECHA_DESDE,TP.FECHA_HASTA,TDP.CREADO_POR, TDP.FECHA_CREACION,TDP.ULTIMA_ACTUALIZACION_POR,TDP.ULTIMA_FECHA_ACTUALIZACION,TDP.RUTA_ID, TDP.ADICIONAL1 REDONDEO " +
                ",(select rp.LUNES_VALOR||','||rp.MARTES_VALOR||','||rp.MIERCOLES_VALOR||','||rp.JUEVES_VALOR||','||rp.VIERNES_VALOR||','||rp.SABADO_VALOR||','||rp.DOMINGO_VALOR from  TMS_PARAMETROS_CONFIG_TBL p,TMS_RUTA_PARAMETROS_TBL rp where p.PARAMETRO_CODIGO = 'P_VTPLEALTAD' and rp.RUTA_ID = TDP.RUTA_ID and rp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID) LEALTAD " +
                ",(select rp.PARAMETRO_VALOR from  TMS_PARAMETROS_CONFIG_TBL p,TMS_RUTA_PARAMETROS_TBL rp where p.PARAMETRO_CODIGO = 'P_VLEALTXVTXC' and rp.RUTA_ID = TDP.RUTA_ID and rp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID) APLICA_TIPO_LEALTAD " +
                ",TDP.ADICIONAL2 APLICA_LEALTAD " +
                "FROM Tms_Descuentos_Rutas_Tbl TDP, TMS_TIPOS_PASAJERO_TBL TP " +
                "WHERE TRUNC(SYSDATE) BETWEEN TRUNC(TP.FECHA_DESDE) AND TRUNC(NVL(TP.FECHA_HASTA,TRUNC(SYSDATE))) " +
                "and TDP.TIPO_PASAJERO_ID = tp.TIPO_PASAJERO_ID";
       //System.out.println(this.getCaja()+"=> queryTmsTiposPasajeroTblFindAble2: "+consulta);
       //java.util.List<TmsTiposPasajeroTbl> listado = em.createNativeQuery(consulta,TmsTiposPasajeroTbl.class).getResultList();
        java.util.List<TmsTiposPasajeroTbl> listado = new ArrayList<TmsTiposPasajeroTbl>();
        Vector vv = (Vector) em.createNativeQuery(consulta).getResultList();
       for(int i=0; i<vv.size(); i++)
           listado.add(new TmsTiposPasajeroTbl((Vector)vv.get(i)));
           //em.refresh(t);
           //t.setLealtades();
       return  listado;
    }

    /**
     * <code>select o from TmsComponenteBusTbl o where o.tipo<>-1</code>
     * @return 
     */
    public java.util.List<TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll() throws javax.ejb.EJBException {
        try{
            return em.createNamedQuery("TmsComponenteBusTbl.findAll").getResultList();
        }catch(Exception ex){
            return null;
        }
    }

    /**
     * 
     * @param pId 
     * @return 
     */
    public TmsAutobusPlantillasEncTbl queryTmsAutobusPlantillasEncTblFindById(Long pId) throws javax.ejb.EJBException {
        try{
            return em.find(TmsAutobusPlantillasEncTbl.class, pId);
        }catch(Exception ex){
            return null;
        }
    }

    /**
     * 
     * @return 
     */
    public java.util.List<TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll() throws javax.ejb.EJBException {
        try{
            return em.createNamedQuery("TmsAutobusPlantillasEncTbl.findAll").getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
    public java.util.List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindAll() throws javax.ejb.EJBException {
        try{
            return em.createNamedQuery("TmsAutobusPlantLineasTbl.findTodo").getResultList();
        }catch(Exception ex){
            return null;
        }
    }

  /**************************************** SELECCIONES *************************************/
  public String FechaHoraCorridaParaBoleto(String pDBSchema, String pDBLink, String claveCorrida) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta=
            "SELECT CX.FECHA_HORA_CORRIDA FROM " +
            pDBSchema+"TMS_CORRIDAS_VENTA_TBL"+pDBLink+" CX "+
            "WHERE CX.CLAVE_CORRIDA = '"+claveCorrida+"'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getSingleResult();
            if(x==null || x.size()==0) return null;
            return x.get(0).toString();
        }catch(Exception nrex){
            return null;
        }
    }

    public boolean existeCorrida(String claveCorrida, String pDBLink) throws javax.ejb.EJBException{
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta=
            "SELECT 1 "+
            "FROM TMS_CORRIDAS_VENTA_TBL"+pDBLink+" CV "+
            "WHERE CV.CLAVE_CORRIDA = '"+claveCorrida+"' "+
            "AND   CV.ESTADO_CORRIDA = 'A'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return false;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return false;
            return true;
        }catch(Exception nrex){
            return false;
        }
    }
    
    // NUEVAS CONSULTAS
    public int estamosEnVacaciones() throws javax.ejb.EJBException{
        String Consulta=
            "SELECT 1 "+
              "FROM dual "+
             "WHERE SYSDATE BETWEEN ( "+
            "SELECT TO_DATE(tmsGlo.PARAMETRO_VALOR,'DD/MM/RRRR HH24:MI') "+
              "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar "+
                       ",TMS_GLOBAL_PARAMETROS_TBL tmsGlo "+
             "WHERE tmsPar.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID "+
               "AND tmsPar.PARAMETRO_CODIGO = 'P_TMPINIVAC' "+
               ") AND ( "+
            "SELECT TO_DATE(tmsGlo.PARAMETRO_VALOR,'DD/MM/RRRR HH24:MI') "+
              "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar "+
                       ",TMS_GLOBAL_PARAMETROS_TBL tmsGlo "+
             "WHERE tmsPar.PARAMETRO_CONFIG_ID = tmsGlo.PARAMETRO_CONFIG_ID "+
               "AND tmsPar.PARAMETRO_CODIGO ='P_TMPFINVAC')";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return 0;  // NO ESTAMOS
            Vector y = (Vector) x.get(0);
            if(y.size()==0) return 0; // NO ESTAMOS
            if(Integer.valueOf(y.get(0).toString())!=1) return 0; // NO ESTAMOS
            return 1; // SI ESTAMOS
        }catch(Exception nrex){
            return -1;
        }
    }
    
    public Object[][] queTiposPasajeVendo(String Servicio) throws javax.ejb.EJBException{
        String Consulta=
            "SELECT DECODE(tmsPar.PARAMETRO_CODIGO,'P_LIMSENCOR','S' "+
                          ",'P_LIMESTCOR','E' "+
                                      ",'P_LIMPROCOR','P' "+
                                      ",'P_LIMCORCOR','C',NULL) TIPO_PASAJERO "+
                       ",DECODE(tmsSp.TURNO,'T',(DECODE(TO_CHAR(SYSDATE,'D'),'1',tmsSp.LUNES_VALOR "+
                               ",'2',tmsSp.MARTES_VALOR "+
                                       ",'3',tmsSp.MIERCOLES_VALOR "+
                                       ",'4',tmsSp.JUEVES_VALOR "+
                                       ",'5',tmsSp.VIERNES_VALOR "+
                                       ",'6',tmsSp.SABADO_VALOR "+
                                       ",'7',tmsSp.DOMINGO_VALOR "+
                                       ",NULL)),tmsSp.PARAMETRO_VALOR) VALOR "+
              "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar "+
                       ",TMS_SERVICIO_PARAMETROS_TBL tmsSp "+
                       ",TMS_SERVICIOS_TBL tmsSer "+
             "WHERE tmsPar.PARAMETRO_CONFIG_ID = tmsSp.PARAMETRO_CONFIG_ID "+
               "AND tmsSp.SERVICIO_ID = tmsSer.SERVICIO_ID "+
               "AND tmsPar.PARAMETRO_CODIGO IN ('P_LIMSENCOR','P_LIMESTCOR','P_LIMPROCOR','P_LIMCORCOR') "+
               "AND tmsSer.SERVICIO_NOMBRE = '"+Servicio+"'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return null;
            Object[][] tiposPasajeVta = new Object[x.size()][2];
            Vector y = null;
            for(int i=0; i<tiposPasajeVta.length; i++){
                y = (Vector) x.get(i);
                tiposPasajeVta[i][0] = y.get(0).toString();
                tiposPasajeVta[i][1] = y.get(1).toString();
            }
            return tiposPasajeVta;
        }catch(Exception nrex){
            return null;
        }
    }
    
    public boolean existeReservacion(String reservacionId, String pDBLink) throws javax.ejb.EJBException{
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta=
            "SELECT 1 FROM TMS_RESERVACIONES_TBL"+pDBLink+" WHERE RESERVACION_ID = "+reservacionId+" AND ESTADO_RESERVACION = 'RESERVADA'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return false;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return false;
            return true;
        }catch(Exception nrex){
            return false;
        }
    }

    public boolean bloqueaPermisoFolios(String valor) throws NoBloqueoFoliosException, javax.ejb.EJBException{
        String Consulta =
                    "UPDATE " +
                    "TMS_BASE_DATOS_CONFIG_TBL "+
                    "SET FOLIADO_VALIDACION='"+valor+"' "+
                    "WHERE ESQUEMA_PROPIO='S'";
        try{
            //System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            throw new NoBloqueoFoliosException("No fue posible obtener permiso de foliado.");
        }
    }

    /************************* CONSULTAS PARA OPTIMIZAR **********************/
    public String getParametrosIniciales(long usuarioId, String caja_nombre){
        String Consulta=
        "SELECT Xer_Tms_Pkg.GETPARAMETROSINICIALES("+usuarioId+",'"+caja_nombre+"') PARAMETROS FROM DUAL";
        try{

            System.out.println("+++++++++ Inicia "+new Date()+" Xer_Tms_Pkg.GETPARAMETROSINICIALES +++++++++");
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            System.out.println("--------- Termina"+new Date()+" Xer_Tms_Pkg.GETPARAMETROSINICIALES ---------");
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }catch(Exception ex1){
            return null;
        }
    }
    
    public String getParametrosIniciales2(String caja_nombre){
        String Consulta = "SELECT Xer_Tms_Pkg.GETPARAMETROSINICIALES2('"+caja_nombre+"') PARAMETROS FROM DUAL";
        try{
            System.out.println("+++++++++ Inicia "+new Date()+" Xer_Tms_Pkg.GETPARAMETROSINICIALES2 +++++++++");
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            System.out.println("--------- Termina"+new Date()+" Xer_Tms_Pkg.GETPARAMETROSINICIALES2 ---------");
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }catch(Exception ex1){
            return null;
        }
    }
    
    public String getParametrosIniciales3(long caja_id){
        String Consulta = "SELECT Xer_Tms_Pkg.GETPARAMETROSINICIALES3("+caja_id+") PARAMETROS FROM DUAL";
        try{
            System.out.println("+++++++++ Inicia "+new Date()+" Xer_Tms_Pkg.GETPARAMETROSINICIALES3 +++++++++");
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            System.out.println("--------- Termina"+new Date()+" Xer_Tms_Pkg.GETPARAMETROSINICIALES3 ---------");
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }catch(Exception ex1){
            return null;
        }
    }
    
    public String getTramosTarifas(){
        String Consulta = "SELECT Xer_Tms_Pkg.GETTARIFAS() PARAMETROS FROM DUAL";
        try{
            System.out.println("+++++++++ Inicia "+new Date()+" Xer_Tms_Pkg.GETTARIFAS() +++++++++");
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            System.out.println("--------- Termina"+new Date()+" Xer_Tms_Pkg.GETTARIFAS() ---------");
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }
    }
    
    public String getTramosTarifas1(){
        String Consulta = "SELECT Xer_Tms_Pkg.GETTARIFAS1() PARAMETROS FROM DUAL";
        try{
            System.out.println("+++++++++ Inicia "+new Date()+" Xer_Tms_Pkg.GETTARIFAS1() +++++++++");
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            System.out.println("--------- Termina"+new Date()+" Xer_Tms_Pkg.GETTARIFAS1() ---------");
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }
    }    
    
    public String obtenerCorridasVenta(String pDBSchema, String pDBLink, String strRutaOrigen, String strOrigen, String strHoy, String strServicio, String strDestino, String strEmpresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                "SELECT " +
                    "Xer_Tms_Pkg.GETCORRIDAS('"+pDBLink+"','''"+strRutaOrigen+"''','''"+strOrigen+"''','''"+strHoy+"''','''"+strServicio+"''','''"+strDestino+"''','''"+strEmpresa+"''') VALOR "+
                "FROM DUAL";
        try{
            System.out.println(this.getCaja()+"=> Consulta VTA: "+Consulta);
            Vector yvector = new Vector();
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            //System.out.println(this.getCaja()+"=> Corridas: "+yvector);
            if(yvector==null || yvector.size()==0) return null;
            String resultado = yvector.get(0).toString();
            resultado = resultado.replace("[","");
            resultado = resultado.replace("]","");
            if(resultado.equals("null")) return null;
            return resultado;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public String obtenerUnaCorridaVenta(String pDBSchema, String pDBLink, String corridaId) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                "SELECT " +
                    "Xer_Tms_Pkg.GETUNACORRIDA"+pDBLink+"('','"+corridaId+"') VALOR "+
                "FROM DUAL";
        try{
            //System.out.println(Consulta);
            Vector yvector = new Vector();
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(yvector==null || yvector.size()==0) return null;
            String resultado = yvector.get(0).toString();
            resultado = resultado.replace("[","");
            resultado = resultado.replace("]","");
            if(resultado.equals("null")) return null;
            //System.out.println(this.getCaja()+"=> Resultado: "+resultado);
            return resultado;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public int queryExisteCorrida(String pDBSchema, String pDBLink, String strRutaOrigen, String strOrigen, String strHoy, String strFecha, String strServicio, String strDestino, String strEmpresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        Vector yvector = new Vector();
        String Consulta =
                "SELECT  DISTINCT(1) " +
                "FROM TMS_CORRIDAS_CONSULTA_V"+pDBLink+" CORRIDAS " +
                "WHERE (CORRIDAS.FECHA_HORA_CORRIDA BETWEEN (TO_DATE('"+strHoy+"','DD/MM/YYYY HH24:MI')-(NVL((SELECT TO_NUMBER(TMSV.ADICIONAL1) " +
                "FROM TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV " +
                "WHERE (TMSV.RUTA_ID = CORRIDAS.RUTA_ID) AND (TMSV.ORIGEN = '"+strOrigen+"') AND (TMSV.DESTINO LIKE '"+strDestino+"') AND (ROWNUM=1)),0)/1440)) AND (TO_DATE('"+strHoy+"','DD/MM/YYYY HH24:MI')+1)) " +
                "AND (CORRIDAS.RUTA_ID IN ( " +
                "SELECT TMSV.RUTA_ID RUTA_ID " +
                "FROM TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV " +
                "WHERE (TMSV.ORIGEN = '"+strOrigen+"') AND (TMSV.RUTA_ORIGEN = '"+strRutaOrigen+"') AND (TMSV.DESTINO "+(strDestino.equals("%")?"LIKE '"+strDestino+"'":"IN ('"+strDestino+"')")+"))) " +
                "AND CORRIDAS.SERVICIO IN ("+strServicio+") " +
                "AND CORRIDAS.EMPRESA LIKE '"+strEmpresa+"' ";
         String Consulta1 =
                "SELECT 1 "+
                "FROM TMS_SERVICIO_ORIGS_DESTS_V "+
                "WHERE RUTA_ORIGEN = '"+strRutaOrigen+"' "+
                "AND   SERVICIO = "+strServicio+" "+
                "AND   ORIGEN = '"+strOrigen+"' "+
                "AND   DESTINO = '"+strDestino+"'";
        try{
            //System.out.println(this.getCaja()+"=> Existe regreso(0) "+Consulta);
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(yvector==null || yvector.size()==0) return -1;
            //System.out.println(this.getCaja()+"=> Existe regreso(1) "+Consulta1);
            yvector = (Vector) em.createNativeQuery(Consulta1).getResultList();
            if(yvector==null || yvector.size()==0) return -2;
            return 0;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return -1;
        }
         
    }
    
    public int getValidaSesionVenta(long corteId, long sesionId, String bVenta){
        String Consulta = "SELECT Xer_Tms_Pkg.GETVALIDARSESIONVENTA("+corteId+","+sesionId+",'"+bVenta+"') PARAMETROS FROM DUAL";
        System.out.println(this.getCaja()+"=> Entra a getValidaSesionVenta...");
        try{
            //System.out.println(this.getCaja()+"=> desde valida venta "+Consulta);
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            if(valor==null) return -1;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return -1;
            if(valor.equals("-1")) return -1;
            return Integer.valueOf(valor.toString());
        }catch(NoResultException ex){
            System.out.println(this.getCaja()+"=> NoResultException1 desde getValidaSesionVenta........");
            return -1;
        }catch(Exception e){
            System.out.println(this.getCaja()+"=> NoResultException2 desde getValidaSesionVenta........");
            return -1;
        }
    }
    
    public boolean bloqueaRvn(String claveRvn, String valorClaveUsuario, String pDBSchema, String pDBLink) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                    "UPDATE TMS_RESERVACIONES_TBL"+pDBLink+" RVN "+
                    "SET RVN.BLOQ_RVN = '"+valorClaveUsuario+"' "+
                    "WHERE RVN.CLAVE_RESERVACION = '"+claveRvn+"'";
        try{
            System.out.println(Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
            return true;
        }
        catch(Exception nrex){
            return false;
        }
    }
    
    public String quienOcupaRvn(String claveRvn, String pDBSchema, String pDBLink) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                    "SELECT NVL(SUBSTR(RVN.BLOQ_RVN,2),'NADA') "+
                    "FROM TMS_RESERVACIONES_TBL"+pDBLink+" RVN "+
                    "WHERE RVN.CLAVE_RESERVACION = '"+claveRvn+"' "+
                    "AND   ROWNUM=1";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return null;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return null;
            if(y.get(0)==null) return null;
            return y.get(0).toString();
        }
        catch(Exception nrex){
            return null;
        }
    }
    
    public int validarFolios(long P_TERMINAL_ID, long P_EMPRESA_ID, long P_FOLIO_INICIAL, long P_FOLIO_FINAL, long P_USUARIO_ID){
        String Consulta=
        "SELECT Xer_Tms_Pkg.Tms_Validar_Folios_Fnc("+P_TERMINAL_ID+", "+P_EMPRESA_ID+", "+P_FOLIO_INICIAL+", "+P_FOLIO_FINAL+", "+P_USUARIO_ID+" ) VALFOL FROM DUAL";
        System.out.println(this.getCaja()+"=> Conuslta de validarFolios: "+Consulta);
        try{
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            if(valor==null) return 0;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return 0;
            return Integer.valueOf(valor.toString());
        }catch(NoResultException ex){
            return 0;
        }
    }
    
    public long[] CancelaBoletoRemoto(String pDBLink, String Campos1, String Valores1, String Campos2, String Valores2, String claveCorrida, long noAsiento, long boletoIdOriginal) throws javax.ejb.EJBException {
        long[] boletoIds=new long[2];
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta0 =
                    "INSERT INTO " +
                    "TMS_BOLETOS_VENTA_TBL " +
                    Campos1 + " VALUES "+ Valores1;
        String Consulta1 =
                    "SELECT BOLETO_ID FROM " +
                    "TMS_BOLETOS_VENTA_TBL "+
                    "WHERE CLAVE_CORRIDA='"+claveCorrida+"' AND NO_ASIENTO='"+noAsiento+"' " +
                    "AND TIPO_OPERACION='CN' " +
                    "AND BOLETO_RELACIONADO_ID = "+boletoIdOriginal;
        
        String Consulta2 =
                    "INSERT INTO " +
                    "TMS_BOLETOS_VENTA_TBL"+pDBLink+" " +
                    Campos2 + " VALUES "+ Valores2;
        String Consulta3 =
                    "SELECT BOLETO_ID FROM " +
                    "TMS_BOLETOS_VENTA_TBL"+pDBLink+" "+
                    "WHERE CLAVE_CORRIDA='"+claveCorrida+"' AND NO_ASIENTO='"+noAsiento+"' " +
                    "AND TIPO_OPERACION='CN' " +
                    "AND BOLETO_RELACIONADO_ID = "+boletoIdOriginal;
        try{
            if(em.createNativeQuery(Consulta0).executeUpdate()==0){
                boletoIds[0] = -1;
                return boletoIds;
            }
            // trabajo con nuevo ID
            Vector x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta1).getSingleResult();
            if(x.size()==0){
                boletoIds[0] = -2;
                return boletoIds;
            }
            boletoIds[0] = Long.valueOf(x.get(0).toString());
            
            if(em.createNativeQuery(Consulta2).executeUpdate()==0){
                boletoIds[1] = -1;
                return boletoIds;
            }
            // trabajo con nuevo ID
            x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta3).getSingleResult();
            if(x.size()==0){
                boletoIds[1] = -2;
                return boletoIds;
            }
            boletoIds[1] = Long.valueOf(x.get(0).toString());
            
            return boletoIds;
        }catch(Exception ex){
            ex.printStackTrace();
            boletoIds[0] = -1;
            boletoIds[1] = -1;
        }
        return boletoIds;
    }
    
    public long CancelaBoletoLocal(String Campos1, String Valores1, String claveCorrida, long noAsiento, long boletoIdOriginal) throws javax.ejb.EJBException {
        long boletoIds;
        String Consulta0 =
                    "INSERT INTO " +
                    "TMS_BOLETOS_VENTA_TBL " +
                    Campos1 + " VALUES "+ Valores1;
        String Consulta1 =
                    "SELECT BOLETO_ID FROM " +
                    "TMS_BOLETOS_VENTA_TBL "+
                    "WHERE CLAVE_CORRIDA='"+claveCorrida+"' AND NO_ASIENTO='"+noAsiento+"' " +
                    "AND TIPO_OPERACION='CN' " +
                    "AND BOLETO_RELACIONADO_ID = "+boletoIdOriginal;
        try{
            //System.out.println(this.getCaja()+"=> Consulta: "+Consulta0);
            if(em.createNativeQuery(Consulta0).executeUpdate()==0) return -1;
            // trabajo con nuevo ID
            Vector x = new Vector();
            x = (Vector) em.createNativeQuery(Consulta1).getSingleResult();
            if(x.size()==0) return -2;
            return  Long.valueOf(x.get(0).toString());
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int _OcuparAsientosSP(String pDBLink, long corridaId, String asientos, String tiposPasajero, String modo, long usuarioId){
        String valor=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        System.out.println("---------------------------------------");
        System.out.println(this.getCaja()+"=> pDBLink: "+pDBLink);
        System.out.println(this.getCaja()+"=> corridaId: "+corridaId);
        System.out.println(this.getCaja()+"=> asientos: "+asientos);
        System.out.println(this.getCaja()+"=> tiposPasajero: "+tiposPasajero);
        System.out.println(this.getCaja()+"=> usuarioId: "+usuarioId);
        System.out.println(this.getCaja()+"=> modo: "+modo);
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "Xer_Tms_Pkg.Tms_Bloquear_Asientos_Prc"+pDBLink+"(?, ?, ?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
            stmt.setLong(1,corridaId);
            stmt.setString(2, asientos);
            stmt.setString(3, tiposPasajero);
            stmt.setLong(4, usuarioId);
            stmt.setString(5, modo);
            stmt.setString(6, "");
            // -1X reg ocupado, 13S CON EL QUE FALLO; 000; 00X reg ocupado
            stmt.execute();
            
            valor = stmt.getString(7);
            //System.out.println(this.getCaja()+"=> Valor "+valor);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            if(valor==null) return -1;
            if(valor.equals("00X") || valor.equals("-1X")) return -1;
            if(valor.equals("000")) return 0;
            String valorTipo = valor.substring(2);
            return Integer.valueOf(valor.substring(0,2));
        } catch (SQLException ex){
            try {
                valor = stmt.getString(7);
                //System.out.println(this.getCaja()+"=> SP ocupa "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null || valor.equals("00X") || valor.equals("-1X")) return -1;
                if(valor.equals("000")) return 0;
                String valorTipo = valor.substring(2);
                return Integer.valueOf(valor.substring(0,2));
            }
        }
    }
    
    public Object[] _solicitudRegistroVentaSP(String[] _strBoletos, String pqry){
//        System.out.println(this.getCaja()+"=> entra al metodo _solicitudRegistroVentaSP...");
//        Object[] res = new Object[2];
//        System.out.println(this.getCaja()+"=> se creo el objeto...");
//        res[0] = "-1"; res[1] = ""; return res;
        
        String valor=null;
        String ref=null;
        Connection cnx=null;
        Object[] res = new Object[2];
        OracleCallableStatement stmt=null;
        boolean bResultado = true;
        if(!pqry.equals(""))
        {
            System.out.println(this.getCaja()+"=> busca si F<T es valido: "+pqry);        
            Vector vX = (Vector) em.createNativeQuery(pqry).getResultList();
            //if(vX.size()>0){
            if(vX.size()>0){
                res[0] = "-1"; res[1] = "";
                return res;
            }
        }
        try {
         System.out.println(this.getCaja()+"=> entra al tray del facade en el metodo _solicitudRegistroVentaSP....");
           cnx = dataSource.getConnection(); 
                   String q1 = 
                    "BEGIN "+
                      "Xer_Tms_Pkg.Tms_Registrar_Venta_Prc(?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            System.out.println(this.getCaja()+"=> prepara la conexion....");
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            System.out.println(this.getCaja()+"=> prepara el ArrayDescriptor....");
            ARRAY newArray = new ARRAY(desc, stmt.getConnection(), _strBoletos);
            System.out.println(this.getCaja()+"=> prepara el ARRAY....");
            
            ((OraclePreparedStatement)stmt).setArray(1, newArray);
////            System.out.println(this.getCaja()+"=> tipoVenta: "+tipoVenta);
////             System.out.println(this.getCaja()+"=> nombreCliente: "+nombreCliente);
////            stmt.setString(2, tipoVenta);
////            stmt.setString(3, nombreCliente);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
            System.out.println(this.getCaja()+"=> ya asigono los parametros");
            System.out.println(this.getCaja()+"=> manda a ejecutar la funcion stmt.execute()...");
            bResultado=stmt.execute();
            System.out.println(this.getCaja()+"=> "+formatoDebugFecha.format(new Date())+":"+"Resultado de execute "+bResultado);
            
            valor = stmt.getString(2);
            ref = stmt.getString(3);
            System.out.println(formatoDebugFecha.format(new Date())+":"+"SP venta "+(valor==null?"null":valor));
            System.out.println(this.getCaja()+"=> valor: "+valor);
            System.out.println(this.getCaja()+"=> Referencia: "+ref);
           
            stmt.close();
            
         System.out.println(this.getCaja()+"=> pasa el bloque deshabilitado....");
            //// 111:NO PUDO COR REM
            if(cnx!=null) cnx.close();
            
            if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("000")) {res[0] = "0"; res[1] = ref;/*obtener la venta Referenciada*/; return res;}
            if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
//            if(valor==null) return -1;
//            if(valor.equals("000")) return 0;
//            if(valor.equals("111")) return -1;
//            if(valor.equals("020")) return -2;
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                valor = stmt.getString(2);
                System.out.println(this.getCaja()+"=> SP venta(2) "+(valor==null?"null":valor));
                stmt.close();   
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
////                if(valor==null) return -1;
////                if(valor.equals("000")) return 0;
////                if(valor.equals("111")) return -1;
////                if(valor.equals("020")) return -2;
///                return -1;
            if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("000")) {res[0] = "0"; res[1] = ref; return res;}
            if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
                res[0] = "-1"; return res;
            }
        }
        //return -1;
        res[0] = "-1"; res[1] = ""; return res;
    }
    

    
    public Object[] _solicitudRegistroVentaSP2(String nuevo_strBoletos, String pqry, int tam){
        //System.out.println(this.getCaja()+"=> entra al metodo _solicitudRegistroVentaSP2...");
        //Object[] res = new Object[2];
        String[] _strBoletos = new String[tam];
        //System.out.println(this.getCaja()+"=> se llena el nuevo array...");
        StringTokenizer st = new StringTokenizer(nuevo_strBoletos,"|");
        if(tam==1)
            _strBoletos[0] = nuevo_strBoletos;
        else
        {
            int i=0;
           while(st.hasMoreTokens()) 
           {
                _strBoletos[i] = st.nextToken();
                i++;
           }   
        }
        //for(int i=0; i<_strBoletos.length; i++)
                //System.out.println(this.getCaja()+"=> _strBoletos["+  i+"]="+_strBoletos[i]);
        
        //res[0] = "-1"; res[1] = ""; return res;
        
        String valor=null;
        String ref=null;
        Connection cnx=null;
        Object[] res = new Object[2];
        OracleCallableStatement stmt=null;
        boolean bResultado = true;
        if(!pqry.equals(""))
        {
            //System.out.println(this.getCaja()+"=> busca si FT es valido: "+pqry);        
            Vector vX = (Vector) em.createNativeQuery(pqry).getResultList();
            if(vX.size()>0){
                res[0] = "-1"; res[1] = "";
                return res;
            }
        }
        try {
         //System.out.println(this.getCaja()+"=> entra al tray del facade en el metodo _solicitudRegistroVentaSP....");
           cnx = dataSource.getConnection(); 
                   String q1 = 
                    "BEGIN "+
                      "Xer_Tms_Pkg.Tms_Registrar_Venta_Prc(?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //System.out.println(this.getCaja()+"=> prepara la conexion....");
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            //System.out.println(this.getCaja()+"=> prepara el ArrayDescriptor....");
            ARRAY newArray = new ARRAY(desc, stmt.getConnection(), _strBoletos);
            //System.out.println(this.getCaja()+"=> prepara el ARRAY....");
            
            ((OraclePreparedStatement)stmt).setArray(1, newArray);
////            System.out.println(this.getCaja()+"=> tipoVenta: "+tipoVenta);
////             System.out.println(this.getCaja()+"=> nombreCliente: "+nombreCliente);
////            stmt.setString(2, tipoVenta);
////            stmt.setString(3, nombreCliente);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
            //System.out.println(this.getCaja()+"=> ya asigono los parametros");
            //System.out.println(this.getCaja()+"=> manda a ejecutar la funcion stmt.execute()...");
            bResultado=stmt.execute();
            //System.out.println(formatoDebugFecha.format(new Date())+":"+"Resultado de execute "+bResultado);
            
            valor = stmt.getString(2);
            ref = stmt.getString(3);
            //System.out.println(formatoDebugFecha.format(new Date())+":"+"SP venta "+(valor==null?"null":valor));
            //System.out.println(this.getCaja()+"=> valor: "+valor);
            //System.out.println(this.getCaja()+"=> Referencia: "+ref);
           
            stmt.close();
            
         //System.out.println(this.getCaja()+"=> pasa el bloque deshabilitado....");
            //// 111:NO PUDO COR REM
            if(cnx!=null) cnx.close();
            
            if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("000")) {res[0] = "0"; res[1] = ref;/*obtener la venta Referenciada*/; return res;}
            if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
//            if(valor==null) return -1;
//            if(valor.equals("000")) return 0;
//            if(valor.equals("111")) return -1;
//            if(valor.equals("020")) return -2;
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                valor = stmt.getString(2);
                //System.out.println(this.getCaja()+"=> SP venta(2) "+(valor==null?"null":valor));
                stmt.close();   
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
////                if(valor==null) return -1;
////                if(valor.equals("000")) return 0;
////                if(valor.equals("111")) return -1;
////                if(valor.equals("020")) return -2;
///                return -1;
            if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("000")) {res[0] = "0"; res[1] = ref; return res;}
            if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
                res[0] = "-1"; return res;
            }
        }
        //return -1;
        res[0] = "-1"; res[1] = ""; return res;
    }    
    
 
    public String _solicitudRegistroVentaSP3(String nuevo_strBoletos, String pqry, int tam){
        //System.out.println(this.getCaja()+"=> entra al metodo _solicitudRegistroVentaSP3...");
        //Object[] res = new Object[2];
        String[] _strBoletos = new String[tam];
        Array array;
        Vector collection=new Vector();
        Vector registro = null;
        //System.out.println(this.getCaja()+"=> se llena el nuevo array3...");
        //System.out.println(this.getCaja()+"=> nuevo_strBoletos: "+nuevo_strBoletos);
        StringTokenizer st = new StringTokenizer(nuevo_strBoletos,"|");
        System.out.println(this.getCaja()+"=> ****** Antes de if(tam==1)...."+_ObtieneFechaHoraBD2());            
        if(tam==1)
            _strBoletos[0] = nuevo_strBoletos;
        else
        {
            int i=0;
           while(st.hasMoreTokens()) 
           {
                _strBoletos[i] = st.nextToken();
                i++;
                //System.out.println(this.getCaja()+"=> Encuentra i= "+i);
           }   
        }
       // for(int i=0; i<_strBoletos.length; i++)
                //System.out.println(this.getCaja()+"=> _strBoletos["+  i+"]="+_strBoletos[i]);
        
        //res[0] = "-1"; res[1] = ""; return res;
        
        String valor=null;
        String ref=null;
        Connection cnx=null;
        //Object[] res = new Object[2];
        String respuesta = "";
        OracleCallableStatement stmt=null;
        boolean bResultado = true;
        System.out.println(this.getCaja()+"=> ****** Antes de  if(!pqry.equals())...."+_ObtieneFechaHoraBD2());   
        System.out.println("pqry: "+pqry);
        if(!pqry.equals(""))
        {
            System.out.println(this.getCaja()+"=> busca si FT es valido3: "+pqry);        
            Vector vX = (Vector) em.createNativeQuery(pqry).getResultList();
            //if(vX.size()>0){
            System.out.println(this.getCaja()+"=> Qry regresa: "+vX);
            if(vX.size()>0){
                //res[0] = "-1"; res[1] = "";
                respuesta = "-1,nada";
                
                return respuesta;
            }
        }
        try {
         //System.out.println(this.getCaja()+"=> entra al tray del facade en el metodo _solicitudRegistroVentaSP3....");
           System.out.println(this.getCaja()+"=> ****** Antes de  dataSource.getConnection()...."+_ObtieneFechaHoraBD2());   
           
           cnx = dataSource.getConnection(); 
                   String q1 = 
                    "BEGIN "+
                      "Xer_Tms_Pkg.Tms_Registrar_Venta2_Prc(?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            System.out.println(this.getCaja()+"=> ****** Antes de  stmt = (OracleCallableStatement) cnx.prepareCall(q1)...."+_ObtieneFechaHoraBD2());   
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //System.out.println(this.getCaja()+"=> prepara la conexion3....");
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            //System.out.println(this.getCaja()+"=> prepara el ArrayDescriptor3....");
            ARRAY newArray = new ARRAY(desc, stmt.getConnection(), _strBoletos);
            //System.out.println(this.getCaja()+"=> prepara el ARRAY3....");
            System.out.println(this.getCaja()+"=> _strBoletos = "+_strBoletos);
            ((OraclePreparedStatement)stmt).setArray(1, newArray);
////            System.out.println(this.getCaja()+"=> tipoVenta: "+tipoVenta);
////             System.out.println(this.getCaja()+"=> nombreCliente: "+nombreCliente);
////            stmt.setString(2, tipoVenta);
////            stmt.setString(3, nombreCliente);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4,OracleTypes.STRUCT, "FOLIO_BOLETO_TYPE");
            stmt.registerOutParameter(5,OracleTypes.ARRAY, "FOLIO_BOLETOS_COLLECTION_TYPE");
            //System.out.println(this.getCaja()+"=> ya asigono los parametros3");
            //System.out.println(this.getCaja()+"=> manda a ejecutar la funcion stmt.execute()3...");
            System.out.println(this.getCaja()+"=> ****** Antes de  stmt.execute()...."+_ObtieneFechaHoraBD2());   
            bResultado=stmt.execute();
            System.out.println(formatoDebugFecha.format(new Date())+":"+"Resultado de execute "+bResultado);
            System.out.println(this.getCaja()+"=> ****** Despues de  stmt.execute()...."+_ObtieneFechaHoraBD2());   
            valor = stmt.getString(2);
            ref = stmt.getString(3);
            //System.out.println(formatoDebugFecha.format(new Date())+":"+"SP venta "+(valor==null?"null":valor));
            //System.out.println(this.getCaja()+"=> valor3: "+valor);
            //System.out.println(this.getCaja()+"=> Referencia3: "+ref);
            if(valor.equals("000")) {
                    array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(5);
                    ResultSet rs=array.getResultSet();

                    registro = new Vector();

                    while(rs.next()){
                        STRUCT obj= (STRUCT)rs.getObject(2);
                        Object[] attrs1=obj.getAttributes();
                        registro = new Vector();
                        registro.add(attrs1[0]); //numero de Asiento
                        registro.add(attrs1[1]); //folio boleto
                        System.out.println("registro "+registro);
                        collection.add(registro);
                    }
                    System.out.println("collection: "+collection);
            }
            stmt.close();
         //System.out.println(this.getCaja()+"=> pasa el bloque deshabilitado3....");
            //// 111:NO PUDO COR REM
            if(cnx!=null) cnx.close();
            
            //if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor==null){respuesta = "-1,nada"; return respuesta;}
            //if(valor.equals("000")) {res[0] = "0"; res[1] = ref;/*obtener la venta Referenciada*/; return res;}
            if(valor.equals("000")) { 
                    respuesta = "0,"+ref;/*obtener la venta Referenciada*/; 
                    if(collection.size()>0){
                        for(int i=0; i<collection.size(); i++){
                          registro = new Vector();
                          registro = (Vector)collection.get(i);
                          respuesta = respuesta+","+(registro.get(0)==null ?"-1" :registro.get(0).toString())+"-"+registro.get(1).toString();
                        }
                    }
                    return respuesta;}
            //if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("111")){respuesta = "-1,nada";; return respuesta;}
            //if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
            if(valor.equals("020")){respuesta = "-2,nada"; return respuesta;}
            //System.out.println(this.getCaja()+"=> Devuelve la cadena3 = "+respuesta);
//            if(valor==null) return -1;
//            if(valor.equals("000")) return 0;
//            if(valor.equals("111")) return -1;
//            if(valor.equals("020")) return -2;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                valor = stmt.getString(2);
                System.out.println(this.getCaja()+"=> SP venta(2)3 "+(valor==null?"null":valor));
                stmt.close();   
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
////                if(valor==null) return -1;
////                if(valor.equals("000")) return 0;
////                if(valor.equals("111")) return -1;
////                if(valor.equals("020")) return -2;
///                return -1;
            //if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor==null){respuesta = "-1,nada"; return respuesta;}
            //if(valor.equals("000")) {res[0] = "0"; res[1] = ref;/*obtener la venta Referenciada*/; return res;}
            if(valor.equals("000")) { respuesta = "0,"+ref;/*obtener la venta Referenciada*/; return respuesta;}
            //if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("111")){respuesta = "-1,nada";; return respuesta;}
            //if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
            if(valor.equals("020")){respuesta = "-2,nada"; return respuesta;}
            //res[0] = "-1"; return res;
            }
        }
        //return -1;
        //res[0] = "-1"; res[1] = ""; return res;
        respuesta = "-1,nada";return respuesta;
    }        
    
    
    public String _solicitudRegistroVentaSP4(String nuevo_strBoletos, String pqry, int tam, long corteId){
        //System.out.println(this.getCaja()+"=> entra al metodo _solicitudRegistroVentaSP4...");
        //Object[] res = new Object[2];
        String[] _strBoletos = new String[tam];
        Array array;
        Vector collection=new Vector();
        Vector registro = null;
        //System.out.println(this.getCaja()+"=> se llena el nuevo array3...");
        StringTokenizer st = new StringTokenizer(nuevo_strBoletos,"|");
        System.out.println(this.getCaja()+"=> ****** Antes de if(tam==1)...."+_ObtieneFechaHoraBD2());            
        if(tam==1)
            _strBoletos[0] = nuevo_strBoletos;
        else
        {
            int i=0;
           while(st.hasMoreTokens()) 
           {
                _strBoletos[i] = st.nextToken();
                i++;
           }   
        }
       // for(int i=0; i<_strBoletos.length; i++)
                //System.out.println(this.getCaja()+"=> _strBoletos["+  i+"]="+_strBoletos[i]);
        
        //res[0] = "-1"; res[1] = ""; return res;
        
        String valor=null;
        String ref=null;
        Connection cnx=null;
        //Object[] res = new Object[2];
        String respuesta = "";
        OracleCallableStatement stmt=null;
        boolean bResultado = true;
        System.out.println(this.getCaja()+"=> ****** Antes de  if(!pqry.equals())...."+_ObtieneFechaHoraBD2());   
        System.out.println("pqry: "+pqry);
        if(!pqry.equals(""))
        {
            System.out.println(this.getCaja()+"=> busca si FT es valido3: "+pqry);        
            Vector vX = (Vector) em.createNativeQuery(pqry).getResultList();
            //if(vX.size()>0){
            System.out.println(this.getCaja()+"=> Qry regresa: "+vX);
            if(vX.size()>0){
                //res[0] = "-1"; res[1] = "";
                respuesta = "-1,nada";
                
                return respuesta;
            }
        }
        try {
         //System.out.println(this.getCaja()+"=> entra al tray del facade en el metodo _solicitudRegistroVentaSP4....");
           System.out.println(this.getCaja()+"=> ****** Antes de  dataSource.getConnection()...."+_ObtieneFechaHoraBD2());   
           
           cnx = dataSource.getConnection(); 
                   String q1 = 
                    "BEGIN "+
                      "XER_TMS_INTERFACE_HER_PKG.tmsws_getVenderBoletos_prc(?, ?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            System.out.println(this.getCaja()+"=> ****** Antes de  stmt = (OracleCallableStatement) cnx.prepareCall(q1)...."+_ObtieneFechaHoraBD2());   
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //System.out.println(this.getCaja()+"=> prepara la conexion3....");
            stmt.setLong(1,corteId);
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            //System.out.println(this.getCaja()+"=> prepara el ArrayDescriptor3....");
            ARRAY newArray = new ARRAY(desc, stmt.getConnection(), _strBoletos);
            //System.out.println(this.getCaja()+"=> prepara el ARRAY3....");
            System.out.println(this.getCaja()+"=> _strBoletos = "+_strBoletos);
            ((OraclePreparedStatement)stmt).setArray(2, newArray);
////            System.out.println(this.getCaja()+"=> tipoVenta: "+tipoVenta);
////             System.out.println(this.getCaja()+"=> nombreCliente: "+nombreCliente);
////            stmt.setString(2, tipoVenta);
////            stmt.setString(3, nombreCliente);
            stmt.registerOutParameter(3,OracleTypes.ARRAY, "FOLIO_BOL_COLLECTION_MV_TYPE");
            stmt.registerOutParameter(4,java.sql.Types.NUMERIC);
            stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
            
            //System.out.println(this.getCaja()+"=> ya asigono los parametros3");
            //System.out.println(this.getCaja()+"=> manda a ejecutar la funcion stmt.execute()3...");
            System.out.println(this.getCaja()+"=> ****** Antes de  stmt.execute()...."+_ObtieneFechaHoraBD2());   
            bResultado=stmt.execute();
            System.out.println(formatoDebugFecha.format(new Date())+":"+"Resultado de execute "+bResultado);
            System.out.println(this.getCaja()+"=> ****** Despues de  stmt.execute()...."+_ObtieneFechaHoraBD2());   
            int bandera = stmt.getInt(4);
            valor = stmt.getString(5);
            ref ="|";// stmt.getString(6);
            System.out.println("bandera: "+bandera);           
            System.out.println("Error: "+valor);  
            System.out.println("Mesnaje: "+stmt.getString(6));  
            //System.out.println(formatoDebugFecha.format(new Date())+":"+"SP venta "+(valor==null?"null":valor));
            //System.out.println(this.getCaja()+"=> valor3: "+valor);
            //System.out.println(this.getCaja()+"=> Referencia3: "+ref);
            if(valor.equals("000")) {
                    array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(3);
                    ResultSet rs=array.getResultSet();

                    registro = new Vector();

                    while(rs.next()){
                        STRUCT obj= (STRUCT)rs.getObject(2);
                        Object[] attrs1=obj.getAttributes();
                        registro = new Vector();
                        registro.add(attrs1[0]); //numero de Asiento
                        registro.add(attrs1[1]); //folio boleto
                        registro.add(attrs1[2]); //folio boleto
                        registro.add(attrs1[3]); //folio boleto
                        registro.add(attrs1[4]); //folio boleto
                        System.out.println("registro "+registro);
                        collection.add(registro);
                    }
                    System.out.println("collection: "+collection);
            }
            stmt.close();
         //System.out.println(this.getCaja()+"=> pasa el bloque deshabilitado3....");
            //// 111:NO PUDO COR REM
            if(cnx!=null) cnx.close();
            
            //if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor==null){respuesta = "-1,nada"; return respuesta;}
            //if(valor.equals("000")) {res[0] = "0"; res[1] = ref;/*obtener la venta Referenciada*/; return res;}
            if(valor.equals("000")) { 
                    respuesta = "0,"+ref;/*obtener la venta Referenciada*/; 
                    if(collection.size()>0){
                        for(int i=0; i<collection.size(); i++){
                          registro = new Vector();
                          registro = (Vector)collection.get(i);
                          respuesta = respuesta+","+(registro.get(0)==null ?"-1" :registro.get(0).toString())+"-"+registro.get(1).toString();
                        }
                    }
                    return respuesta;}
            //if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("111")){respuesta = "-1,nada";; return respuesta;}
            //if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
            if(valor.equals("020")){respuesta = "-2,nada"; return respuesta;}
            //System.out.println(this.getCaja()+"=> Devuelve la cadena3 = "+respuesta);
//            if(valor==null) return -1;
//            if(valor.equals("000")) return 0;
//            if(valor.equals("111")) return -1;
//            if(valor.equals("020")) return -2;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                valor = stmt.getString(2);
                //System.out.println(this.getCaja()+"=> SP venta(2)3 "+(valor==null?"null":valor));
                stmt.close();   
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
////                if(valor==null) return -1;
////                if(valor.equals("000")) return 0;
////                if(valor.equals("111")) return -1;
////                if(valor.equals("020")) return -2;
///                return -1;
            //if(valor==null){res[0] = "-1"; res[1] = ""; return res;}
            if(valor==null){respuesta = "-1,nada"; return respuesta;}
            //if(valor.equals("000")) {res[0] = "0"; res[1] = ref;/*obtener la venta Referenciada*/; return res;}
            if(valor.equals("000")) { respuesta = "0,"+ref;/*obtener la venta Referenciada*/; return respuesta;}
            //if(valor.equals("111")){res[0] = "-1"; res[1] = ""; return res;}
            if(valor.equals("111")){respuesta = "-1,nada";; return respuesta;}
            //if(valor.equals("020")){res[0] = "-2"; res[1] = ""; return res;}
            if(valor.equals("020")){respuesta = "-2,nada"; return respuesta;}
            //res[0] = "-1"; return res;
            }
        }
        //return -1;
        //res[0] = "-1"; res[1] = ""; return res;
        respuesta = "-1,nada";return respuesta;
    }        
    
    public String _ReservarAsientosSP(String pDBLink, long corridaId, String asientos, String tiposPasajero, String clienteId, 
                                   String Responsable, String cancelable, String transaccion, long usuarioId){
        String valor=null;
        String claveReservacion;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "Xer_Tms_Pkg2.Tms_Reservar_Asientos_Prc"+pDBLink+"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";
//            System.out.println(this.getCaja()+"=> BEGIN "+
//                      "Xer_Tms_Pkg2.Tms_Reservar_Asientos_Prc"+pDBLink+"("+corridaId+"_"+asientos+"_"+tiposPasajero+"_"+clienteId+"_"+Responsable+"_"+cancelable+"_"+transaccion+"_"+usuarioId +
//                      "COMMIT; " +
//                      "EXCEPTION " +
//                      "WHEN OTHERS THEN " +
//                      "ROLLBACK;" +
//                      "RAISE; "+
//                    "END;");
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,corridaId);
            stmt.setString(2, asientos);
            stmt.setString(3, tiposPasajero);
            stmt.setString(4, clienteId);
            stmt.setString(5, Responsable);
            stmt.setString(6, cancelable);
            stmt.setString(7, transaccion);
            stmt.setLong(8, usuarioId);
            stmt.registerOutParameter(9,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(10,java.sql.Types.VARCHAR);
            // -1X reg ocupado, 13S CON EL QUE FALLO; 000; 00X reg ocupado
            stmt.execute();
            
            valor = stmt.getString(10);
            claveReservacion = stmt.getString(9);
           // System.out.println(this.getCaja()+"=> Valor Reservacion SP "+valor+" --- "+claveReservacion);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            if(valor==null) return "-1";
            if(valor.equals("00X") || valor.equals("-1X")) return "-1";
            if(valor.equals("000")) return claveReservacion;
            String valorTipo = valor.substring(2);
            return "e_"+valor.substring(0,2);
        } catch (SQLException ex){
            try {
                valor = stmt.getString(10);
              //  System.out.println(this.getCaja()+"=> Valor Reservacion SP "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null || valor.equals("00X") || valor.equals("-1X")) return "-1";
                if(valor.equals("000")) return "-1";
                String valorTipo = valor.substring(2);
                return "e_"+valor.substring(0,2);
            }
        }
    }
        
    public long getCorridaId(String pDBLink, String cCorrida){
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta = 
            "SELECT CORRIDA_ID "+
            "FROM TMS_CORRIDAS_TBL "+pDBLink+" "+
            "WHERE CLAVE_CORRIDA = '"+cCorrida+"'";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x==null || x.size()==0) return -1;
            Vector y = (Vector) x.get(0);
            if(y==null || y.size()==0) return -1;
            return Long.valueOf(y.get(0).toString());
        }catch(Exception ex){
            return -1;
        }
    }
    
    public int _RegistraRecoleccion(String strParametros){
        String valor=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                     "Xer_Tms_Pkg2.Tms_Registrar_Recoleccion_Prc(?,?); "+
                     "COMMIT; "+
                    "EXCEPTION "+
                    "WHEN OTHERS THEN "+
                     "ROLLBACK; "+
                     "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, strParametros);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            
            bResultado=stmt.execute();
            
            valor = stmt.getString(2);
            
            System.out.println(formatoDebugFecha.format(new Date())+":"+"SP Recol "+(valor==null?"null":valor));
           
            stmt.close();
            if(cnx!=null) cnx.close();
            if(valor==null) return -1;
            if(valor.equals("0")) return 0;
            if(valor.equals("-1")) return -1;
        } catch (SQLException ex) {
            try {
                valor = stmt.getString(2);
                System.out.println(this.getCaja()+"=> SP Recol "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null) return -1;
                if(valor.equals("0")) return 0;
                if(valor.equals("-1")) return -1;
                return -1;
            }
        }
        return -1;
    }
    
    public String _ObtieneFechaHoraBD2(){
        return em.createNativeQuery("select SYSDATE from dual").getSingleResult().toString();
    }
    
    public String _ObtieneFechaHoraBDLealtad(){
        return em.createNativeQuery("select to_char(SYSDATE,'DDMMYYYYHH24MISS') from dual").getSingleResult().toString();
    }    
    
    public String _ObtieneDiaVenta(){
        //return em.createNativeQuery("select to_char(SYSDATE,'DAY') from dual").getSingleResult().toString();
        return em.createNativeQuery("select to_char(SYSDATE,'YYYY-MM-DD') from dual").getSingleResult().toString();
    }    
  
    
    public String _ObtieneFechaHoraBD(){
        String valor=null;
        String fechahorabd=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                     "Xer_Tms_Pkg2.Tms_Obtener_FechaHora_Prc(?,?); "+
                     "COMMIT; "+
                    "EXCEPTION "+
                    "WHEN OTHERS THEN "+
                     "ROLLBACK; "+
                     "RAISE; "+
                    "END;";
            
            long x1 = new Date().getTime();
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            
            bResultado=stmt.execute();
            fechahorabd = stmt.getString(1);
            valor = stmt.getString(2);
            System.out.println(formatoDebugFecha.format(new Date())+":"+"SP FechaHora "+(valor==null?"null":valor));
           
            stmt.close();
            if(cnx!=null) cnx.close();
            if(valor==null || !valor.equals("0")) fechahorabd = null;
        } catch (SQLException ex) {
            fechahorabd = null;
            try {
                System.out.println(this.getCaja()+"=> SP SQLException");
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
            }
        }
        return fechahorabd;
    }
    
    public long[] _RegistraFolios(String strParametrosX, String strParametros, int n_empresas){
        String valor=null;
        String valorCorte=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        long[] resultado=new long[2];
        resultado[0]=-1;
        resultado[1]=-1;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                     "Xer_Tms_Pkg2.Tms_Registrar_Folios_Prc(?, ?, ?, ?, ?); "+
                     "COMMIT; "+
                    "EXCEPTION "+
                    "WHEN OTHERS THEN "+
                     "ROLLBACK; "+
                     "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, strParametrosX);
            ((OraclePreparedStatement)stmt).setString(2, strParametros);
            ((OraclePreparedStatement)stmt).setInt(3, n_empresas);
            stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
            
            bResultado=stmt.execute();
            
            valor = stmt.getString(5);
            valorCorte = stmt.getString(4);
            
            System.out.println(formatoDebugFecha.format(new Date())+":"+"SP Folios "+(valor==null?"null":valor));
           
            stmt.close();
            if(cnx!=null) cnx.close();
            if(valor==null){
                resultado[0]=-1;
                resultado[1]=-1;
                return resultado;
            }
            if(valor.equals("0")){
                resultado[0]=0;
                resultado[1]=Long.valueOf(valorCorte);
                return resultado;
            }
            if(valor.equals("-1")){
                resultado[0]=-1;
                resultado[1]=-1;
                return resultado;
            }
        } catch (SQLException ex) {
            try {
                valor = stmt.getString(5);
                System.out.println(this.getCaja()+"=> SP Folios exp:  "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null){
                    resultado[0]=-1;
                    resultado[1]=-1;
                    return resultado;
                }
                if(valor.equals("0")){
                    resultado[0]=0;
                    resultado[1]=0;
                    return resultado;
                }
                if(valor.equals("-1")){
                    resultado[0]=-1;
                    resultado[1]=-1;
                    return resultado;
                }
                resultado[0]=-1;
                resultado[1]=-1;
                return resultado;
            }
        }
        resultado[0]=-1;
        resultado[1]=-1;
        return resultado;
    }
        
    public int _FinalizarVenta(String strParametros){
        String valor=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                     "Xer_Tms_Pkg2.Tms_Finalizar_SesionVta_Prc(?, ?); "+
                     "COMMIT; "+
                    "EXCEPTION "+
                    "WHEN OTHERS THEN "+
                     "ROLLBACK; "+
                     "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, strParametros);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            
            bResultado=stmt.execute();
            
            valor = stmt.getString(2);
            
            System.out.println(formatoDebugFecha.format(new Date())+":"+"SP fin "+(valor==null?"null":valor));
           
            stmt.close();
            if(cnx!=null) cnx.close();
            if(valor==null) return -1;
            if(valor.equals("0")) return 0;
            if(valor.equals("-1")) return -1;
        } catch (SQLException ex) {
            try {
                valor = stmt.getString(2);
                System.out.println(this.getCaja()+"=> SP fin "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null) return -1;
                if(valor.equals("0")) return 0;
                if(valor.equals("-1")) return -1;
                return -1;
            }
        }
        return -1;
    }
    
    public java.util.List<TmsBoletosVentaTbl> BuscaBoletoVendidoParaHOMult(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
            "SELECT BVT.*,NVL(BVT.ADICIONAL14,BVT.ORIGEN),NVL(BD.VIAJADO,'N') FROM " +
            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT left join TMS_BOLETOS_VENTA_DATOS_TBL"+pDBLink+" bd on bd.boleto_id = BVT.BOLETO_ID "+
            "WHERE "+
            //"BVT.ORIGEN = '"+Origen+"'
            "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
            "AND	 BVT.FOLIO_PREIMPRESO IN ("+FolioPreimpreso+") "+
            "AND	 BVT.NO_ASIENTO IN ("+noAsiento+") "+
            "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' "+
            "AND         BVT.TIPO_OPERACION IN ('VT', 'HO', 'AC','FO','FT','FC') "+
            "AND	 BVT.BOLETO_ID NOT IN "+
            "( "+
            "SELECT BSVT.BOLETO_RELACIONADO_ID "+
            "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT "+
            "WHERE BSVT.TIPO_OPERACION IN ('VA', 'CN','HO','FT','F0') "+
            "AND (BSVT.BOLETO_RELACIONADO_ID = BVT.BOLETO_ID) " +
            ") " +
            "ORDER BY TO_NUMBER(BVT.NO_ASIENTO)";
        try{
            System.out.println(this.getCaja()+"=> BuscaBoletoVendidoParaHOMult: "+Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?null:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?null:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?null:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?null:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?null:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                //Buscar el importe del cargo
                double importe_cargo = 0;
                    Vector resp = (Vector)em.createNativeQuery("SELECT XER_TMS_PKG3.TMS_CARGO_HO_CN_BOL_FNC"+pDBLink+"('"+bv.getFolioPreimpreso()+"','HO') FROM DUAL").getResultList();
                    if(resp.size()>0)
                    {
                        Vector v = (Vector)resp.get(0);
                        importe_cargo = Double.valueOf(v.get(0).toString());
                    }
//                System.out.println("  importeBoleto: "+bv.getImporteBoleto());
//                System.out.println("  importe_cargo: "+importe_cargo);
//                bv.setAdicional15(""+bv.getImporteBoleto());
//                bv.setImporteBoleto(bv.getImporteBoleto()+importe_cargo);
                bv.setAdicional15(""+importe_cargo);
                System.out.println("  importeBoleto: "+bv.getImporteBoleto());
                System.out.println("  Adicional15: "+bv.getAdicional15());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?null:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?null:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?null:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                bv.setOrigenCorrida(vBol.get(54)==null?"":vBol.get(54).toString());
                bv.setViajado(vBol.get(55)==null?"N":vBol.get(55).toString());

                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
     public java.util.List<TmsBoletosVentaTbl> BuscaBoletoVendidoParaHORef(String pDBSchema, String pDBLink, String referencia) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
//        String Consulta =
//            "SELECT * FROM " +
//            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT "+
//            "WHERE "+
//            //"BVT.ORIGEN = '"+Origen+"'
//            "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
//            "AND	 BVT.FOLIO_PREIMPRESO IN ("+FolioPreimpreso+") "+
//            "AND	 BVT.NO_ASIENTO IN ("+noAsiento+") "+
//            "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' "+
//            "AND         BVT.TIPO_OPERACION IN ('VT', 'HO', 'AC') "+
//            "AND	 BVT.BOLETO_ID NOT IN "+
//            "( "+
//            "SELECT BSVT.BOLETO_RELACIONADO_ID "+
//            "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT "+
//            "WHERE BSVT.TIPO_OPERACION IN ('VA', 'CN','HO') "+
//            "AND (BSVT.BOLETO_RELACIONADO_ID = BVT.BOLETO_ID) " +
//            ") " +
//            "ORDER BY TO_NUMBER(BVT.NO_ASIENTO)";
       String Consulta= 
                "SELECT BVT.BOLETO_ID,BVT.EMPRESA,BVT.SERVICIO,BVT.CAJA,BVT.CORTE_ID,BVT.CLAVE_CORRIDA,BVT.CLIENTE_ID,BVT.NO_ASIENTO,BVT.NOMBRE_PASAJERO,BVT.TIPO_PASAJERO,BVT.TIPO_PAGO,BVT.REFERENCIA_PAGO,BVT.IMPORTE_BOLETO,BVT.TIPO_OPERACION,BVT.RESERVACION_ID,BVT.BOLETO_RELACIONADO_ID,BVT.DIAS_VALIDEZ_BOLETO_ABIERTO,BVT.FOLIO_PREIMPRESO,BVT.FOLIO_BOLETO,BVT.CIUDAD_VENTA,BVT.ORIGEN,BVT.DESTINO,BVT.TIPO_TRANSACCION,BVT.CLAVE_CAJERO,BVT.FECHA_HORA_VENTA,BVT.AUTORIZADO_POR,BVT.FECHA_HORA_AUTORIZACION,BVT.CREADO_POR,BVT.FECHA_CREACION,BVT.ULTIMA_ACTUALIZACION_POR,BVT.ULTIMA_FECHA_ACTUALIZACION,BVT.ADICIONAL1,BVT.ADICIONAL2,BVT.ADICIONAL3,BVT.ADICIONAL4,BVT.ADICIONAL5,BVT.ADICIONAL6,BVT.ADICIONAL7,BVT.ADICIONAL8,BVT.ADICIONAL9,BVT.ADICIONAL10,BVT.REPLICACION_ESTADO,BVT.REPLICACION_INTENTOS,BVT.REPLICACION_ORIGEN, BRF.BOLETO_REFERENCIA_NOMBRE, BRF.BOLETO_REFERENCIADO_ID,C.FECHA_HORA_CORRIDA FECHA,NVL(BVT.ADICIONAL14,BVT.ORIGEN)   FROM  " +
                pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT, " +
                pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BRF,  " +
                pDBSchema+"TMS_CORRIDAS_TBL"+pDBLink+" C  " +
                "WHERE  BRF.BOLETO_CLAVE = '"+referencia+"' " +
                "AND BRF.ESTADO_REFERENCIA = 'P' " +
                "AND BVT.BOLETO_ID = BRF.BOLETO_ID " +
                "AND BVT.TIPO_OPERACION IN ('VT', 'HO', 'AC','FT','FO') "+
                "AND    BVT.BOLETO_ID NOT IN  " +
                "(  " +
                "SELECT BSVT.BOLETO_RELACIONADO_ID  " +
                "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT  " +
                "WHERE BSVT.TIPO_OPERACION IN ('VA', 'CN','HO','FO','FC') " +
                "AND (BSVT.BOLETO_RELACIONADO_ID = BVT.BOLETO_ID) " +
               ") " +
                "AND C.CLAVE_CORRIDA = BVT.CLAVE_CORRIDA " +
                "ORDER BY BVT.BOLETO_ID";        
        try{
            //System.out.println(Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?null:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?null:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?null:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?null:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?null:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?null:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?null:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?null:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                bv.setNombreAutorizado(vBol.get(44)==null?"":vBol.get(44).toString());
                //bv.setCorridaId(vBol.get(45)==null?"":vBol.get(45).toString());
                bv.setBolReferenciadoId(vBol.get(45)==null?"":vBol.get(45).toString());
                //System.out.println(this.getCaja()+"=> fecha("+i+"): "+vBol.get(46));
                bv.setFecha(vBol.get(46)==null?null:(Date)vBol.get(46));
                bv.setOrigenCorrida(vBol.get(47)==null?"":vBol.get(47).toString());
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public java.util.List<TmsBoletosVentaTbl> BuscaBoletoAbiertoMult(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String Servicio, String empresa) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta=
            "SELECT * FROM " +
            pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT "+
            "WHERE  BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM "+pDBSchema+"TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
            "AND    BVT.SERVICIO = '"+Servicio+"' "+
            "AND    BVT.FOLIO_PREIMPRESO IN ("+FolioPreimpreso+") "+
            "AND    BVT.EMPRESA = '"+empresa+"' "+
            "AND    BVT.TIPO_OPERACION = 'VA'"+
            "AND    BVT.BOLETO_ID NOT IN "+
            "( "+
            "SELECT BSVT.BOLETO_RELACIONADO_ID "+
            "FROM "+pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT "+
            "WHERE BSVT.TIPO_OPERACION <> 'VA'"+
            "AND BSVT.BOLETO_RELACIONADO_ID IS NOT NULL "+
            ") " +
            "AND (TRUNC(BVT.FECHA_HORA_VENTA)+BVT.DIAS_VALIDEZ_BOLETO_ABIERTO) >= TRUNC(SYSDATE) " +
            "ORDER BY BVT.BOLETO_ID";

        try{
            //System.out.println(Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?0:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?0:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?0:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?0:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?0:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?0:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?0:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?0:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }

    public java.util.List<TmsBoletosVentaTbl> BuscaBoletoAbiertoRef(String pDBSchema, String pDBLink, String referencia) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        
        String Consulta= 
                "SELECT BVT.BOLETO_ID,BVT.EMPRESA,BVT.SERVICIO,BVT.CAJA,BVT.CORTE_ID,BVT.CLAVE_CORRIDA,BVT.CLIENTE_ID,BVT.NO_ASIENTO,BVT.NOMBRE_PASAJERO,BVT.TIPO_PASAJERO,BVT.TIPO_PAGO,BVT.REFERENCIA_PAGO,BVT.IMPORTE_BOLETO,BVT.TIPO_OPERACION,BVT.RESERVACION_ID,BVT.BOLETO_RELACIONADO_ID,BVT.DIAS_VALIDEZ_BOLETO_ABIERTO,BVT.FOLIO_PREIMPRESO,BVT.FOLIO_BOLETO,BVT.CIUDAD_VENTA,BVT.ORIGEN,BVT.DESTINO,BVT.TIPO_TRANSACCION,BVT.CLAVE_CAJERO,BVT.FECHA_HORA_VENTA,BVT.AUTORIZADO_POR,BVT.FECHA_HORA_AUTORIZACION,BVT.CREADO_POR,BVT.FECHA_CREACION,BVT.ULTIMA_ACTUALIZACION_POR,BVT.ULTIMA_FECHA_ACTUALIZACION,BVT.ADICIONAL1,BVT.ADICIONAL2,BVT.ADICIONAL3,BVT.ADICIONAL4,BVT.ADICIONAL5,BVT.ADICIONAL6,BVT.ADICIONAL7,BVT.ADICIONAL8,BVT.ADICIONAL9,BVT.ADICIONAL10,BVT.REPLICACION_ESTADO,BVT.REPLICACION_INTENTOS,BVT.REPLICACION_ORIGEN, BRF.BOLETO_REFERENCIA_NOMBRE, BRF.BOLETO_REFERENCIADO_ID, null  FROM  " +
                pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT, " +
                pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BRF  " +
                //pDBSchema+"TMS_CORRIDAS_TBL"+pDBLink+" C " +
                "WHERE  BRF.BOLETO_CLAVE = '"+referencia+"' " +
                "AND BRF.ESTADO_REFERENCIA = 'P' " +
                "AND BVT.BOLETO_ID = BRF.BOLETO_ID " +
                "AND BVT.TIPO_OPERACION = 'VA' " +
                "AND    BVT.BOLETO_ID NOT IN  " +
                "(  " +
                "SELECT BSVT.BOLETO_RELACIONADO_ID  " +
                "FROM " +
                pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT  " +
                "WHERE BSVT.TIPO_OPERACION <> 'VA' " +
                "AND BSVT.BOLETO_RELACIONADO_ID IS NOT NULL  " +
                ") " +
                //"AND C.CLAVE_CORRIDA = BVT.CLAVE_CORRIDA " +
                "AND (TRUNC(BVT.FECHA_HORA_VENTA)+BVT.DIAS_VALIDEZ_BOLETO_ABIERTO) >= TRUNC(SYSDATE) " +
                "ORDER BY BVT.BOLETO_ID";

        try{
            //System.out.println(Consulta);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(vX==null || vX.size()==0) return null;
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?0:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?0:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?0:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?0:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?0:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?0:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?0:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?0:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                bv.setNombreAutorizado(vBol.get(44)==null?"":vBol.get(44).toString());
                //bv.setCorridaId(vBol.get(45)==null?"":vBol.get(45).toString());
                bv.setBolReferenciadoId(vBol.get(45)==null?"":vBol.get(45).toString());
                bv.setFecha(vBol.get(46)==null?null:(Date)vBol.get(46));
                
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }    
    
    
    
    public java.util.List<TmsBoletosVentaTbl> buscaBoletosReferencia(String pDBSchema, String pDBLink, String referencia) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta=
            "SELECT BVT.BOLETO_ID,BVT.EMPRESA,BVT.SERVICIO,BVT.CAJA,BVT.CORTE_ID,BVT.CLAVE_CORRIDA,BVT.CLIENTE_ID,BVT.NO_ASIENTO,BVT.NOMBRE_PASAJERO,BVT.TIPO_PASAJERO,BVT.TIPO_PAGO,BVT.REFERENCIA_PAGO,BVT.IMPORTE_BOLETO,BVT.TIPO_OPERACION,BVT.RESERVACION_ID,BVT.BOLETO_RELACIONADO_ID,BVT.DIAS_VALIDEZ_BOLETO_ABIERTO,BVT.FOLIO_PREIMPRESO,BVT.FOLIO_BOLETO,BVT.CIUDAD_VENTA,BVT.ORIGEN,BVT.DESTINO,BVT.TIPO_TRANSACCION,BVT.CLAVE_CAJERO,BVT.FECHA_HORA_VENTA,BVT.AUTORIZADO_POR,BVT.FECHA_HORA_AUTORIZACION,BVT.CREADO_POR,BVT.FECHA_CREACION,BVT.ULTIMA_ACTUALIZACION_POR,BVT.ULTIMA_FECHA_ACTUALIZACION,BVT.ADICIONAL1,BVT.ADICIONAL2,BVT.ADICIONAL3,BVT.ADICIONAL4,BVT.ADICIONAL5,BVT.ADICIONAL6,BVT.ADICIONAL7,BVT.ADICIONAL8,BVT.ADICIONAL9,BVT.ADICIONAL10,BVT.REPLICACION_ESTADO,BVT.REPLICACION_INTENTOS,BVT.REPLICACION_ORIGEN, BRF.BOLETO_REFERENCIA_NOMBRE, C.CORRIDA_ID, C.FECHA_HORA_CORRIDA FECHA ,TO_CHAR(C.FECHA_HORA_CORRIDA,'HH24:MI') HORA, BRF.BOLETO_REFERENCIADO_ID, TO_CHAR(C.FECHA_HORA_CORRIDA,'DD/MM/RRRR') FCORRIDA ,NVL(BVT.ADICIONAL14,BVT.ORIGEN)  FROM " +
//            "SELECT BVT.*, BRF.BOLETO_REFERENCIA_NOMBRE, C.CORRIDA_ID, TO_CHAR(C.FECHA_HORA_CORRIDA,'DD/MM/RRRR') FECHA ,TO_CHAR(C.FECHA_HORA_CORRIDA,'HH24:MI') HORA, BRF.BOLETO_REFERENCIADO_ID  FROM " +
                pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT, " +
                pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BRF,  " +
                pDBSchema+"TMS_CORRIDAS_TBL"+pDBLink+" C " +
                "WHERE  BRF.BOLETO_CLAVE = '"+referencia+"' " +
                "AND BRF.ESTADO_REFERENCIA = 'P' " +
                    "AND BVT.BOLETO_ID = BRF.BOLETO_ID "     +
                //"AND BVT.TIPO_OPERACION IN ('VT','FO') " +
                "AND BVT.TIPO_OPERACION IN ('VT','FO') " +
                "AND    BVT.BOLETO_ID NOT IN  " +
                "(  " +
                "SELECT BSVT.BOLETO_RELACIONADO_ID  " +
                "FROM " +
                pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT  " +
                "WHERE BSVT.TIPO_OPERACION <> 'VT' " +
                "AND BSVT.BOLETO_RELACIONADO_ID IS NOT NULL  " +
                ") " +
                "AND C.CLAVE_CORRIDA = BVT.CLAVE_CORRIDA " +
                "ORDER BY BVT.BOLETO_ID ";
        String Consulta2=
            "SELECT BVT.BOLETO_ID,BVT.EMPRESA,BVT.SERVICIO,BVT.CAJA,BVT.CORTE_ID,BVT.CLAVE_CORRIDA,BVT.CLIENTE_ID,BVT.NO_ASIENTO,BVT.NOMBRE_PASAJERO,BVT.TIPO_PASAJERO,BVT.TIPO_PAGO,BVT.REFERENCIA_PAGO,BVT.IMPORTE_BOLETO,BVT.TIPO_OPERACION,BVT.RESERVACION_ID,BVT.BOLETO_RELACIONADO_ID,BVT.DIAS_VALIDEZ_BOLETO_ABIERTO,BVT.FOLIO_PREIMPRESO,BVT.FOLIO_BOLETO,BVT.CIUDAD_VENTA,BVT.ORIGEN,BVT.DESTINO,BVT.TIPO_TRANSACCION,BVT.CLAVE_CAJERO,BVT.FECHA_HORA_VENTA,BVT.AUTORIZADO_POR,BVT.FECHA_HORA_AUTORIZACION,BVT.CREADO_POR,BVT.FECHA_CREACION,BVT.ULTIMA_ACTUALIZACION_POR,BVT.ULTIMA_FECHA_ACTUALIZACION,BVT.ADICIONAL1,BVT.ADICIONAL2,BVT.ADICIONAL3,BVT.ADICIONAL4,BVT.ADICIONAL5,BVT.ADICIONAL6,BVT.ADICIONAL7,BVT.ADICIONAL8,BVT.ADICIONAL9,BVT.ADICIONAL10,BVT.REPLICACION_ESTADO,BVT.REPLICACION_INTENTOS,BVT.REPLICACION_ORIGEN, BRF.BOLETO_REFERENCIA_NOMBRE, null, null ,null, BRF.BOLETO_REFERENCIADO_ID, null,NVL(BVT.ADICIONAL14,BVT.ORIGEN)  FROM " +
                pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BVT, " +
                pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BRF  " +
                "WHERE  BRF.BOLETO_CLAVE = '"+referencia+"' " +
                "AND BRF.ESTADO_REFERENCIA = 'P' " +
                "AND BVT.BOLETO_ID = BRF.BOLETO_ID " +
                "AND BVT.TIPO_OPERACION = 'VA' " +
                "AND    BVT.BOLETO_ID NOT IN  " +
                "(  " +
                "SELECT BSVT.BOLETO_RELACIONADO_ID  " +
                "FROM " +
                pDBSchema+"TMS_BOLETOS_VENTA_TBL"+pDBLink+" BSVT  " +
                "WHERE BSVT.TIPO_OPERACION <> 'VT' " +
                "AND BSVT.BOLETO_RELACIONADO_ID IS NOT NULL  " +
                ") " +
                "ORDER BY BVT.BOLETO_ID ";
        
        try{
            //System.out.println(this.getCaja()+"=> Busca (Referenciado): "+Consulta);
            //System.out.println(this.getCaja()+"=> Busca2 (Referenciado): "+Consulta2);
            Vector vX = (Vector) em.createNativeQuery(Consulta).getResultList();
            Vector vX2 = null;
            if(vX==null || vX.size()==0)
            {
                vX2 = (Vector) em.createNativeQuery(Consulta2).getResultList();
                if(vX2==null || vX2.size()==0) return null;
                else vX = vX2;
            }
            java.util.List<TmsBoletosVentaTbl> x = new ArrayList<TmsBoletosVentaTbl>();
            TmsBoletosVentaTbl bv = null;
            Vector vBol = null;
            Timestamp tmFecha=null;
            for(int i=0; i<vX.size(); i++){
                bv = new TmsBoletosVentaTbl();
                vBol = (Vector) vX.get(i);
                
                String bloquea = "SELECT 1 " +
                "FROM "+pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" b " +
                "WHERE b.BOLETO_REFERENCIADO_ID = "+Long.valueOf(vBol.get(48)==null?"":vBol.get(48).toString())+"  " +
                "FOR UPDATE NOWAIT ";
                //System.out.println(this.getCaja()+"=> Bloquea2: "+bloquea);
//                    try {
//                         if(vBol.get(48)!=null)
//                        em.createNativeQuery(bloquea).getResultList();
//                    } catch(EJBException e) {
//                        System.out.println(this.getCaja()+"=> Registro ocupado para SELECT FOR UPDATE NOWAIT");
//                    }
                
                
                bv.setBoletoId(vBol.get(0)==null?0:Long.valueOf(vBol.get(0).toString()));
                bv.setEmpresa(vBol.get(1)==null?"":vBol.get(1).toString());
                bv.setServicio(vBol.get(2)==null?"":vBol.get(2).toString());
                bv.setCaja(vBol.get(3)==null?"":vBol.get(3).toString());
                bv.setCorteId(vBol.get(4)==null?0:Long.valueOf(vBol.get(4).toString()));
                bv.setClaveCorrida(vBol.get(5)==null?"":vBol.get(5).toString());
                bv.setClienteId(vBol.get(6)==null?0:Long.valueOf(vBol.get(6).toString()));
                bv.setNoAsiento(vBol.get(7)==null?null:Long.valueOf(vBol.get(7).toString()));
                bv.setNombrePasajero(vBol.get(8)==null?"":vBol.get(8).toString());
                bv.setTipoPasajero(vBol.get(9)==null?"":vBol.get(9).toString());
                bv.setTipoPago(vBol.get(10)==null?"":vBol.get(10).toString());
                bv.setReferenciaPago(vBol.get(11)==null?"":vBol.get(11).toString());
                bv.setImporteBoleto(vBol.get(12)==null?0:Double.valueOf(vBol.get(12).toString()));
                bv.setTipoOperacion(vBol.get(13)==null?"":vBol.get(13).toString());
                bv.setReservacionId(vBol.get(14)==null?0:Long.valueOf(vBol.get(14).toString()));
                bv.setBoletoRelacionadoId(vBol.get(15)==null?0:Long.valueOf(vBol.get(15).toString()));
                bv.setDiasValidezBoletoAbierto(vBol.get(16)==null?0:Long.valueOf(vBol.get(16).toString()));
                bv.setFolioPreimpreso(vBol.get(17)==null?"":vBol.get(17).toString());
                bv.setFolioBoleto(vBol.get(18)==null?"":vBol.get(18).toString());
                bv.setCiudadVenta(vBol.get(19)==null?"":vBol.get(19).toString());
                bv.setOrigen(vBol.get(20)==null?"":vBol.get(20).toString());
                bv.setDestino(vBol.get(21)==null?"":vBol.get(21).toString());
                bv.setTipoTransaccion(vBol.get(22)==null?"":vBol.get(22).toString());
                bv.setClaveCajero(vBol.get(23)==null?"":vBol.get(23).toString());
                if(vBol.get(24)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(24).toString());
                bv.setFechaHoraVenta(tmFecha);
                bv.setAutorizadoPor(vBol.get(25)==null?0:Long.valueOf(vBol.get(25).toString()));
                if(vBol.get(26)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(26).toString());
                bv.setFechaHoraAutorizacion(tmFecha);
                bv.setCreadoPor(vBol.get(27)==null?0:Long.valueOf(vBol.get(27).toString()));
                if(vBol.get(28)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(28).toString());
                bv.setFechaCreacion(tmFecha);
                bv.setUltimaActualizacionPor(vBol.get(29)==null?0:Long.valueOf(vBol.get(29).toString()));
                if(vBol.get(30)==null) tmFecha = new Timestamp(new Date().getTime());
                else tmFecha=Timestamp.valueOf(vBol.get(30).toString());
                bv.setUltimaFechaActualizacion(tmFecha);
                bv.setAdicional1(vBol.get(31)==null?"":vBol.get(31).toString());
                bv.setAdicional2(vBol.get(32)==null?"":vBol.get(32).toString());
                bv.setAdicional3(vBol.get(33)==null?"":vBol.get(33).toString());
                bv.setAdicional4(vBol.get(34)==null?"":vBol.get(34).toString());
                bv.setAdicional5(vBol.get(35)==null?"":vBol.get(35).toString());
                bv.setAdicional6(vBol.get(36)==null?"":vBol.get(36).toString());
                bv.setAdicional7(vBol.get(37)==null?"":vBol.get(37).toString());
                bv.setAdicional8(vBol.get(38)==null?"":vBol.get(38).toString());
                bv.setAdicional9(vBol.get(39)==null?"":vBol.get(39).toString());
                bv.setAdicional10(vBol.get(40)==null?"":vBol.get(40).toString());
                bv.setNombreAutorizado(vBol.get(44)==null?"":vBol.get(44).toString());
                bv.setCorridaId(vBol.get(45)==null?"":vBol.get(45).toString());
                bv.setFecha(vBol.get(46)==null?null:((Date)(vBol.get(46))));
                bv.setHora(vBol.get(47)==null?"":vBol.get(47).toString());
                bv.setBolReferenciadoId(vBol.get(48)==null?"":vBol.get(48).toString());
                bv.setFcorrida(vBol.get(49) != null ? vBol.get(49).toString() : "");
                bv.setOrigenCorrida(vBol.get(50)==null?"":vBol.get(50).toString());
                x.add(bv);
            }
            return x;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
     public boolean liberarSelectBoletosRef(String pDBSchema, String pDBLink,String liberar){
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta = "DECLARE dummy NUMBER;  " +
                            "BEGIN   " +
                                "UPDATE " +
                                pDBSchema+"TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" br "+
                                "SET br.REPLICACION_INTENTOS = 0 "+
                                "WHERE br.BOLETO_REFERENCIADO_ID in ("+liberar+"); " +
                            "COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";
            try{
                //System.out.println(this.getCaja()+"=> libera2: "+Consulta);
                //System.out.println(Consulta);
                //if(em.createNativeQuery(Consulta).executeUpdate()==0) return false;
                return true;
            }
            catch(Exception nrex){
                System.out.println(this.getCaja()+"=> Excepcion contropdada de liberarSelectBoletosRef...");
                nrex.printStackTrace();
                return false;
            }
     }
    
    public int _ModificaTipoPasajeVarios(String pDBLink, long corridaId, String cadenatipoPasaje) throws javax.ejb.EJBException {
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta = "DECLARE dummy NUMBER; BEGIN " +     
                "BEGIN SELECT 1 INTO dummy " +
                "FROM TMS_CORRIDAS_VENTA_TBL"+pDBLink+" " +
                "WHERE CORRIDA_ID="+corridaId+" "+
                "FOR UPDATE WAIT 1; EXCEPTION WHEN OTHERS THEN RAISE NO_DATA_FOUND; END; " +
                    "UPDATE " +
                    "TMS_CORRIDAS_VENTA_TBL"+pDBLink+" "+
                    "SET "+cadenatipoPasaje+" "+
                    "WHERE CORRIDA_ID="+corridaId+"; COMMIT; EXCEPTION WHEN NO_DATA_FOUND THEN ROLLBACK; RAISE; WHEN OTHERS THEN ROLLBACK; RAISE; END;";

        try{
            //System.out.println(this.getCaja()+"=> _ModificaTipoPasajeVarios: "+Consulta);
            if(em.createNativeQuery(Consulta).executeUpdate()==0) return 1;
            return 0; // actualizo
        }
        catch(Exception nrex){
            //System.out.println(this.getCaja()+"=> msg: "+nrex.getCause().getMessage());
            //System.out.println(this.getCaja()+"=> msg terminado: "+nrex.getCause().getMessage().substring(0,9));
            if(nrex.getCause().getMessage().substring(0,9).equals("ORA-01403")) return -1;
            return 1;
        }
    }

    public Vector buscaTerminalRef(int terminal){
        return (Vector)em.createNativeQuery("SELECT BD.TERMINAL_ID, BD.NOMBRE_TERMINAL, BD.NOMBRE_ESQUEMA, BD.NOMBRE_DBLINK FROM  TMS_BASE_DATOS_CONFIG_TBL BD WHERE  BD.TERMINAL_ID = "+terminal).getResultList();
    }

    
    public int liberarReferenciados(String pDBLink, Vector ids,long usuarioId, String esquema){
       int res = 0;
       //if(!esquema.equals("")) esquema = esquema + ".";
       if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
       System.out.println(this.getCaja()+"=> ids("+_ObtieneFechaHoraBD2()+"): "+ids);
       for(int i=0; i<ids.size(); i++)
       {
           Vector solo =(Vector) ids.get(i);
           //System.out.println(this.getCaja()+"=> solo: "+solo);
//           String qry = "update TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BRF set  BRF.ESTADO_REFERENCIA = 'C', BRF.ADICIONAL1 = '"+solo.get(1).toString()+"', BRF.ULTIMA_ACTUALIZACION_POR = "+usuarioId+", BRF.ULTIMA_FECHA_ACTUALIZACION = SYSDATE, REPLICACION_ESTADO='P',REPLICACION_ORIGEN = (select BDC.nombre_esquema from tms_base_datos_config_tbl"+pDBLink+" BDC where BDC.esquema_propio = 'S') WHERE BRF.BOLETO_REFERENCIADO_ID = "+solo.get(0).toString();
           String qry = "update TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink+" BRF set  BRF.ESTADO_REFERENCIA = 'C', BRF.ADICIONAL1 = '"+solo.get(1).toString()+"', BRF.ULTIMA_ACTUALIZACION_POR = "+usuarioId+", BRF.ULTIMA_FECHA_ACTUALIZACION = SYSDATE, REPLICACION_ESTADO='P',REPLICACION_ORIGEN = '"+esquema+"' WHERE BRF.BOLETO_REFERENCIADO_ID = "+solo.get(0).toString();
           System.out.println(this.getCaja()+"=> Actualiza Referenciado("+ _ObtieneFechaHoraBD2()+"): "+qry);
           try{
               System.out.println(this.getCaja()+"=> Actualiza Referenciado("+ _ObtieneFechaHoraBD2()+"): "+qry);
                if(em.createNativeQuery(qry).executeUpdate()==0 ) res = 1;
                res = 0; // actualizo
            }
            catch(Exception nrex){
                System.out.println(this.getCaja()+"=> msg: "+nrex.getCause().getMessage());
                System.out.println(this.getCaja()+"=> msg terminado");
                //nrex.printStackTrace();
                 res = 1;
            }        
       }
       System.out.println(this.getCaja()+"=> Despues de Actualizar los Referenciados"+ _ObtieneFechaHoraBD2());
       //System.out.println(this.getCaja()+"=> Res = "+res);
       return res;
    }
    
// Buscar Boletos Referenciados
  public List<TmsEstadosTbl> cargarEstadosTBL() {
       // System.out.println(this.getCaja()+"=>  **** CARGAESTADOS *****");
        return em.createNamedQuery("TmsEstadosTbl.getAll").getResultList();
    }
    
    public Vector getBoletosReferenciados(String origen, String fecha, String nombre,String referencia) {
       // System.out.println(this.getCaja()+"=>  **** CONSULTA BOLETOS REFERENCIADOS *****");
        Vector kosa = null;
        Vector info = null;
        try
        {
        /*BUSCAR DBLINK*/
         Vector link = (Vector) em.createNativeQuery("SELECT NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE NOMBRE_TERMINAL = '"+origen+"'").getSingleResult();
         String DB_LINK = link.get(0).toString();
         DB_LINK = "@"+DB_LINK;
        // System.out.println(DB_LINK);
         /* FINAL BUSCAR DB_LINK*/
        // System.out.println(this.getCaja()+"=> SELECT Xer_Tms_Pkg.boletosReferenciados"+DB_LINK+"('"+origen+"', '"+fecha+"', '"+nombre+"', '"+referencia+"') FROM dual");       
        kosa = (Vector) em.createNativeQuery("SELECT Xer_Tms_Pkg.boletosReferenciados"+DB_LINK+"('"+origen+"', '"+fecha+"', '"+nombre+"', '"+referencia+"') FROM dual").getResultList();
        //System.out.println(kosa.get(0) + "  \nTamaño "+kosa.size());
        }catch(Exception e){
           // System.out.println(this.getCaja()+"=> HOLAAAAAAAA" +e);
            List<Vector> list = new ArrayList<Vector>();
            Vector temp = new Vector();
            temp.add("No puedo");
            list.add(temp);
            kosa = new Vector();
            kosa.add(list.iterator().next());
        }
        if (kosa != null) {
            Vector temp = new Vector();
           // System.out.println(kosa.get(0).toString());
            Vector ll = (Vector) kosa.get(0);
            if(ll.contains(null) == false) {
                 //System.out.println(this.getCaja()+"=> Entrooooooooooooo");
                 if (ll.get(0).toString().equals("No puedo")) {
                   // System.out.println(this.getCaja()+"=> No puedo " + kosa.get(0).toString());
                    return kosa;
                }
                if (ll.get(0).toString().equals("0")) {
                   // System.out.println(this.getCaja()+"=> Longitud 0 de respuesta");
                    kosa = null;
                } else if (ll.get(0).toString().equals("ERROR")) {
                   // System.out.println(this.getCaja()+"=> Longitud 0 de respuesta");
                    kosa = null;
                }else{
                    info = new Vector();
                    //Hacer Tokenizer de los objetitos
                    StringTokenizer primero = null;
                    StringTokenizer segundo = null;
                    String cadenas [] = null;
                    int cont = 0;
                    for (int j = 0; j < kosa.size(); j++) {
                        primero = new StringTokenizer(String.valueOf(kosa.get(j)), "-");
                        primero.nextToken();
                        //info = new Vector[primero.countTokens()];
                        //cadenas = new String[cont];
                        while(primero.hasMoreTokens()) {
                            //System.out.print("i " + i);
                            //cadenas[i] = new String(primero.nextToken());
                            segundo = new StringTokenizer(primero.nextToken(), "|");
                            temp = null;
                            temp = new Vector();
                            //System.out.println(this.getCaja()+"=> SEGUNDO "+segundo.countTokens());
                            int ctemp = 0;
                            while(segundo.hasMoreTokens()) {
                                temp.add(segundo.nextToken());
                                //System.out.println(temp);
                                ctemp++;
                            }
                            temp.set(ctemp-1,temp.get(ctemp-1).toString().replaceAll("]",""));
                            //System.out.println(temp.get(ctemp-1).toString().replaceAll("]",""));
                            info.add(temp);
                            cont ++;
                        }
                    }
                }
            }
            else info = null;
        }else
            info = null;
        return info;
    }
    
    public String getRutaId_corrida(String corrida, String pDBLink ){
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta=
        " SELECT c.ruta_id FROM tms_corridas_tbl"+pDBLink+" c WHERE c.CLAVE_CORRIDA = '"+corrida+"'";
        try{
            Object valor = em.createNativeQuery(Consulta).getSingleResult();
            if(valor==null) return null;
            valor = valor.toString().replace("[","");
            valor = valor.toString().replace("]","");
            if(valor.equals("null")) return null;
            return valor.toString();
        }catch(NoResultException ex){
            return null;
        }catch(Exception ex1){
            return null;
        }
    }
 
    public boolean  getPromocionVigente(){
        Vector v = (Vector) em.createNativeQuery("SELECT XER_TMS_PKG3.TMS_PROMO_JUN_VAL_FNC FROM DUAL").getSingleResult();
        if( v.get(0).toString().equals("S"))
            return true;
        else
            return false;
    }
    
    public boolean  getAplicaPromocion(double montoVa, double tarifaRedondo, double descuento, double tarifaSencillo, String dia ){
        String qry = "SELECT XER_TMS_PKG3.TMS_PROMO_JUN_VAL_APL_FNC("+montoVa+","+tarifaRedondo+","+descuento+","+tarifaSencillo+",'"+dia+"') FROM DUAL";
        System.out.println(this.getCaja()+"=> getAplicaPromocion Qry : "+ qry);
        Vector v = (Vector) em.createNativeQuery(qry).getSingleResult();
        if( v.get(0).toString().equals("S"))
            return true;
        else
            return false;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }
    
    
    public void insertaRegistroLealtad(String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento,long user, String pDBSchema, String pDBLink, String terminId){
         if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        if(terminId.length()<3)
        {
             for(int i=terminId.length(); i<3; i++)
                terminId = terminId+"0";
        }
         String Consulta =
                        "INSERT INTO " +
                        pDBSchema+"XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" " +
                        " VALUES ('"+terminId+"'||XER_PROGRAMA_LEALTAD_SEQ.nextval"+pDBLink+","+boleto_id+",'"+folio_preimpreso+"','"+preoducto+"','"+ciudad_venta+"','P','"+tipo_operacion+"','"+num_tarjeta+"','"+numero_operacion+"','"+usuario+"','"+contraseña+"',"+importe+",'"+tipo_pasajero+"','"+caja+"','"+unidad_negocio+"',"+descuento+",null,'','','','','','','','','','','',"+user+",SYSDATE,"+user+",SYSDATE)";
                        //" VALUES ('"+terminId+"'||XER_PROGRAMA_LEALTAD_SEQ.nextval"+pDBLink+","+boleto_id+",'"+folio_preimpreso+"','"+preoducto+"','"+ciudad_venta+"','P','"+tipo_operacion+"','220054945Z','"+numero_operacion+"','"+usuario+"','"+contraseña+"',"+importe+",'"+tipo_pasajero+"','"+caja+"','"+unidad_negocio+"',"+descuento+",null,'','','','','','','','','','','',"+user+",SYSDATE,"+user+",SYSDATE)";
                        //220060003J
                        //220054945Z
        em.createNativeQuery(Consulta).executeUpdate();
    }
    
    public Vector buscaRegistroLealtad(String operacion, String folio, String pDBSchema, String pDBLink ){
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        //String qry = "select t.PRODUCTO, t.UNIDAD_NEGOCIO from "+pDBSchema+"XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.NUMERO_OPERACION = "+operacion;
        Vector vr; 
        String qrya = "select t.NUMERO_TARJETA num, t.UNIDAD_NEGOCIO,t.PRODUCTO from XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = 'A' and t.STATUS <> 'F' order by t.FECHA_CREACION DESC";
        //String qryc = "select t.NUMERO_TARJETA num, t.UNIDAD_NEGOCIO,t.PRODUCTO from XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = 'C' and t.STATUS <> 'F' order by t.FECHA_CREACION DESC";
        String qryc = "select t.NUMERO_TARJETA num, t.UNIDAD_NEGOCIO,t.PRODUCTO from XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = 'C' and t.STATUS = 'R' order by t.FECHA_CREACION DESC";
        Vector va = (Vector) em.createNativeQuery(qrya).getResultList();
        Vector vc = (Vector) em.createNativeQuery(qryc).getResultList();
        System.out.println("Folio: "+folio);
        System.out.println("acumulados: "+va.size()+" == > "+va);
        System.out.println("cancelados: "+vc.size()+" == > "+vc);
        if(va.size()>vc.size())
            return va;
        else
            return new Vector();
    }
      
    public Vector buscaRegistroLealtadAcumulaHO(String operacion, String folio, String pDBSchema, String pDBLink ){
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        //String qry = "select t.PRODUCTO, t.UNIDAD_NEGOCIO from "+pDBSchema+"XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.NUMERO_OPERACION = "+operacion;
        Vector vr; 
        String qrya = "select t.NUMERO_TARJETA num, t.UNIDAD_NEGOCIO,t.PRODUCTO from XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = 'A' and t.STATUS <> 'F' order by t.FECHA_CREACION DESC";
        String qryc = "select t.NUMERO_TARJETA num, t.UNIDAD_NEGOCIO,t.PRODUCTO from XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = 'C' and t.STATUS = 'R' order by t.FECHA_CREACION DESC";
        //String qryc = "select t.NUMERO_TARJETA num, t.UNIDAD_NEGOCIO,t.PRODUCTO from XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = 'C' and t.STATUS <> 'F' order by t.FECHA_CREACION DESC";
        Vector va = (Vector) em.createNativeQuery(qrya).getResultList();
        Vector vc = (Vector) em.createNativeQuery(qryc).getResultList();
        System.out.println("Folio(AcumulaHO): "+folio);
        System.out.println("acumulados(AcumulaHO): "+va.size()+" == > "+va);
        System.out.println("cancelados(AcumulaHO): "+vc.size()+" == > "+vc);
        if(va.size()>vc.size())
            return va;
        else
            return new Vector();
    }    
    
    public String buscaParametroLealtad(String terminal, String parametro){
        String qry = "select tp.PARAMETRO_VALOR from   TMS_PARAMETROS_CONFIG_TBL p,TMS_TERMINAL_PARAMETROS_TBL tp where p.PARAMETRO_CODIGO = '"+parametro+"' and tp.TERMINAL_ID = "+terminal+" and tp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID";
        //System.out.println("buscaParametroLealtad: "+qry);
        Vector v = (Vector) em.createNativeQuery(qry).getResultList();
        Vector v2 = null;
        if(v.size()==0)
            return "";
        else
        {
           v2 = (Vector)v.get(0);
            return v2.get(0).toString();
        }
        
    }

    public String buscaParametroCajaLealtad(String cajaId, String parametro){
        String qry = "select cp.PARAMETRO_VALOR from   TMS_PARAMETROS_CONFIG_TBL p,TMS_CAJA_PARAMETROS_TBL cp where p.PARAMETRO_CODIGO = '"+parametro+"' and cp.CAJA_ID = "+cajaId+" and cp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID";
        System.out.println("buscaParametroCaja: "+qry);
        Vector v = (Vector) em.createNativeQuery(qry).getResultList();
        Vector v2 = null;
        //return "S";
        if(v.size()==0)
            return "";
        else
        {   
           v2 = (Vector)v.get(0);
            return v2.get(0).toString();
        }
        
    }    

    public String buscaEmpresasLealtad(){
        String cadena = "";
        Vector v = (Vector)em.createNativeQuery("select e.EMPRESA_NOMBRE||'-'||ep.PARAMETRO_VALOR from TMS_PARAMETROS_CONFIG_TBL p ,TMS_EMPRESA_PARAMETROS_TBL ep ,TMS_EMPRESAS_TBL e where p.PARAMETRO_CODIGO = 'P_VTPLEALTAEMP' and ep.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID and e.EMPRESA_ID = ep.EMPRESA_ID").getResultList();
        if(v.size()>0)
        {
            for(int i=0; i<v.size(); i++)
            {
                Vector v2 = (Vector)v.get(i);
                cadena = cadena + v2.get(0).toString()+",";
            }
        }
        return cadena;
    }
    
      public String buscaEmpresasRedencion(){
        String cadena = "";
        Vector v = (Vector)em.createNativeQuery("select e.EMPRESA_NOMBRE||'-'||ep.PARAMETRO_VALOR from TMS_PARAMETROS_CONFIG_TBL p ,TMS_EMPRESA_PARAMETROS_TBL ep ,TMS_EMPRESAS_TBL e where p.PARAMETRO_CODIGO = 'P_VTPREDENEMP' and ep.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID and e.EMPRESA_ID = ep.EMPRESA_ID").getResultList();
        if(v.size()>0)
        {
            for(int i=0; i<v.size(); i++)
            {
                Vector v2 = (Vector)v.get(i);
                cadena = cadena + v2.get(0).toString()+",";
            }
        }
        return cadena;
    }  
    
    public String buscaTiposPagoLealtad(){
        String cadena = "";
        Vector v = (Vector)em.createNativeQuery("select tp.CLAVE from TMS_VTA_TIPOS_PAGO_TBL tp where tp.ADICIONAL1 = 'S'").getResultList();
        if(v.size()>0)
        {
            for(int i=0; i<v.size(); i++)
            {
                Vector v2 = (Vector)v.get(i);
                cadena = cadena + v2.get(0).toString()+",";
            }
        }
        return cadena;
    }    
    
    /*
    public Vector GetParametrosTAE(String CajaId, String Terminal_ID)
    {
     Vector Vparametros= new Vector();
     Vector vdatos= null;
     String qry="";
     try {
         
       
        qry =" SELECT CP.PARAMETRO_VALOR,PC.PARAMETRO_CODIGO  "+
                   " FROM "+
                   " TMS_CAJA_PARAMETROS_TBL CP, "+
                   " TMS_PARAMETROS_CONFIG_TBL PC "+
                   " WHERE PC.PARAMETRO_CODIGO IN('P_TAE_COMPANIA','P_TAE_USUARIO','P_TAE_PASSWORD') "+
                   "  AND PC.PARAMETRO_CONFIG_ID = CP.PARAMETRO_CONFIG_ID "+
                   "  AND CP.CAJA_ID = "+ CajaId +
                   "  ORDER BY PC.PARAMETRO_CODIGO  ASC ";
        System.out.println("Buscando datos de configuracion para Tiempo Aire "+qry);
       vdatos = (Vector)em.createNativeQuery(qry).getResultList(); 
        
       System.out.println("RESULTADO  "+vdatos);
       
       if (vdatos == null)
           return null;
       
       Vparametros.addElement( ((Vector)vdatos.elementAt(0)).elementAt(0).toString());
       Vparametros.addElement( ((Vector)vdatos.elementAt(1)).elementAt(0).toString());
       Vparametros.addElement( ((Vector)vdatos.elementAt(2)).elementAt(0).toString());
     
       //   Trae parametros parametros para impresion
       
       //   Parametro de confirmacion a imprimir
       qry ="  SELECT TP.PARAMETRO_VALOR  FROM  "+  
            " TMS_PARAMETROS_CONFIG_TBL PC, "+
            " TMS_TERMINAL_PARAMETROS_TBL TP "+
            " WHERE pc.PARAMETRO_CODIGO ='P_VIMPCOMP' "+
            " AND TP.PARAMETRO_CONFIG_ID= PC.PARAMETRO_CONFIG_ID "+
            " AND TP.TERMINAL_ID= "+Terminal_ID ;
       vdatos = (Vector)em.createNativeQuery(qry).getSingleResult();
         if (vdatos != null && vdatos.get(0) != null )
             Vparametros.addElement(  vdatos.get(0).toString() );
         else 
             Vparametros.addElement( null);
       if(vdatos == null || vdatos.get(0) == null || vdatos.get(0).toString().length() <0 )
       { 
            Vparametros.addElement(  null);
       }
       else{
                //  Trae Direccion de impresora 
              qry =" SELECT IMP.IMPRESORA_NOMBRE FROM  TMS_CAJAS_IMPRESORAS_TBL CI, "+
                                                      " TMS_IMPRESORAS_TBL IMP  "+
                    " WHERE CI.ACTIVIDAD_IMPRESORA='COMPROBANTES' "+
                    " AND CI.CAJA_ID="+CajaId +
                    " AND CI.IMPRESORA_ID = IMP.IMPRESORA_ID";
                System.out.println("Buscando datos de configuracion para Tiempo Aire "+qry);

               vdatos = (Vector)em.createNativeQuery(qry).getSingleResult();
                 if (vdatos != null && vdatos.get(0) != null )
                     Vparametros.addElement(  vdatos.get(0).toString() );
                 else 
                     Vparametros.addElement(  null);
       }
     return Vparametros;
     } catch (Exception e) {
        System.out.println("Error al buscar parametros de configuracion por caja para tiempo Aire");
        System.out.println(qry);
        e.printStackTrace();   
        return null;  
     }
   
    }
    */

    /* 10/02/2012 VAGL Cambio para que el metodo regrese uan cadena y no un vector por si hay una desconexion de la red
    public Vector GetParametrosTAE(long  EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID)
    {
     Vector Vparametros= new Vector();
     Vector vdatos= null;


     try {
         

       String v = "";
       v = obtener_valor_parametro_misc("P_TAE_COMPANIA",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_TAE_COMPANIA =  "+v);
       if(v.equals(""))
           return null;
       else
            Vparametros.addElement(v);

       v = obtener_valor_parametro_misc("P_TAE_PASSWORD",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_TAE_PASSWORD =  "+v);
       if(v.equals(""))
           return null;
       else
            Vparametros.addElement(v);
       
       v = obtener_valor_parametro_misc("P_TAE_USUARIO",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_TAE_USUARIO =  "+v);
       if(v.equals(""))
           return null;
       else
            Vparametros.addElement(v);
       
       //Vparametros.addElement( ((Vector)vdatos.elementAt(0)).elementAt(0).toString());
       //Vparametros.addElement( ((Vector)vdatos.elementAt(1)).elementAt(0).toString());
       //Vparametros.addElement( ((Vector)vdatos.elementAt(2)).elementAt(0).toString());
     
       //   Trae parametros parametros para impresion
 
       
       v = obtener_valor_parametro_misc("P_VIMPCOMP",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_VIMPCOMP =  "+v);
       if(v.trim().equals(""))
           Vparametros.addElement(  null);
//       if(vdatos == null || vdatos.get(0) == null || vdatos.get(0).toString().length() <0 )
//       { 
//            Vparametros.addElement(  null);
//       }
       else{
            Vparametros.addElement( v);  
           //  Trae Direccion de impresora 
              qry =" SELECT IMP.IMPRESORA_NOMBRE FROM  TMS_CAJAS_IMPRESORAS_TBL CI, "+
                                                      " TMS_IMPRESORAS_TBL IMP  "+
                    " WHERE CI.ACTIVIDAD_IMPRESORA='COMPROBANTES' "+
                    " AND CI.CAJA_ID="+CAJA_ID +
                    " AND CI.IMPRESORA_ID = IMP.IMPRESORA_ID";
                System.out.println("Buscando datos de configuracion para Tiempo Aire "+qry);

               vdatos = (Vector)em.createNativeQuery(qry).getResultList();
                 if (vdatos.size()>0)
                     Vparametros.addElement(  vdatos.get(0).toString() );
                 else 
                     Vparametros.addElement(  null);
       }
     return Vparametros;
     } catch (Exception e) {
        System.out.println("Error al buscar parametros de configuracion por caja para tiempo Aire");
        System.out.println(qry);
        e.printStackTrace();   
        return null;  
     }
   
    }
    */

     public String GetParametrosTAE(long  EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID)
    {
     Vector Vparametros= new Vector();
     Vector vdatos= null;
     String qry="";
     /*
        System.out.println("EMPRESA_ID:  "+EMPRESA_ID);
        System.out.println("SERVICIO_ID:  "+SERVICIO_ID);
        System.out.println("TERMINAL_ID:  "+TERMINAL_ID);
        System.out.println("RUTA_ID:  "+RUTA_ID);
        System.out.println("CAJA_ID:  "+CAJA_ID);
     */
     try {

       /*
        qry =" SELECT CP.PARAMETRO_VALOR,PC.PARAMETRO_CODIGO  "+
                   " FROM "+
                   " TMS_TERMINAL_PARAMETROS_TBL CP, "+
                   " TMS_PARAMETROS_CONFIG_TBL PC "+
                   " WHERE PC.PARAMETRO_CODIGO IN('P_TAE_COMPANIA','P_TAE_USUARIO','P_TAE_PASSWORD') "+
                   "  AND PC.PARAMETRO_CONFIG_ID = CP.PARAMETRO_CONFIG_ID "+
                   "  AND cp.TERMINAL_ID = "+ Terminal_ID +
                   "  ORDER BY PC.PARAMETRO_CODIGO  ASC ";
        System.out.println("Buscando datos de configuracion para Tiempo Aire "+qry);
        vdatos = (Vector)em.createNativeQuery(qry).getResultList();

       System.out.println("RESULTADO  "+vdatos);

       if (vdatos == null || vdatos.size()<3)
           return null;
       */
       String v = "";
       v = obtener_valor_parametro_misc("P_TAE_COMPANIA",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_TAE_COMPANIA =  "+v);
       if(v.equals(""))
           return "";
       else
            Vparametros.addElement(v);

       v = obtener_valor_parametro_misc("P_TAE_PASSWORD",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_TAE_PASSWORD =  "+v);
       if(v.equals(""))
           return "";
       else
            Vparametros.addElement(v);

       v = obtener_valor_parametro_misc("P_TAE_USUARIO",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_TAE_USUARIO =  "+v);
       if(v.equals(""))
           return "";
       else
            Vparametros.addElement(v);

       //Vparametros.addElement( ((Vector)vdatos.elementAt(0)).elementAt(0).toString());
       //Vparametros.addElement( ((Vector)vdatos.elementAt(1)).elementAt(0).toString());
       //Vparametros.addElement( ((Vector)vdatos.elementAt(2)).elementAt(0).toString());

       //   Trae parametros parametros para impresion
       /*
       //   Parametro de confirmacion a imprimir
       qry =" SELECT TP.PARAMETRO_VALOR  FROM  "+
            " TMS_PARAMETROS_CONFIG_TBL PC, "+
            " TMS_TERMINAL_PARAMETROS_TBL TP "+
            " WHERE pc.PARAMETRO_CODIGO ='P_VIMPCOMP' "+
            " AND TP.PARAMETRO_CONFIG_ID= PC.PARAMETRO_CONFIG_ID "+
            " AND TP.TERMINAL_ID= "+TERMINAL_ID ;
       vdatos = (Vector)em.createNativeQuery(qry).getSingleResult();
         if (vdatos != null && vdatos.get(0) != null )
             Vparametros.addElement(  vdatos.get(0).toString() );
         else
             Vparametros.addElement( null);
       */

       v = obtener_valor_parametro_misc("P_VIMPCOMP",EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
       System.out.println("P_VIMPCOMP =  "+v);
       if(v.trim().equals(""))
           Vparametros.addElement("N");
       else{
            Vparametros.addElement( v);
           //  Trae Direccion de impresora
              qry =" SELECT IMP.IMPRESORA_NOMBRE FROM  TMS_CAJAS_IMPRESORAS_TBL CI, "+
                                                      " TMS_IMPRESORAS_TBL IMP  "+
                    " WHERE CI.ACTIVIDAD_IMPRESORA='COMPROBANTES' "+
                    " AND CI.CAJA_ID="+CAJA_ID +
                    " AND CI.IMPRESORA_ID = IMP.IMPRESORA_ID";
                System.out.println("Buscando datos de configuracion para Tiempo Aire "+qry);

               vdatos = (Vector)em.createNativeQuery(qry).getResultList();
                 if (vdatos.size()>0)
                 {
                     Vector vd = (Vector)vdatos.get(0);
                     Vparametros.addElement(vd.get(0).toString() );
                 }
                 else
                     Vparametros.addElement("-1");
       }
     return Vparametros.toString();
     } catch (Exception e) {
        System.out.println("Error al buscar parametros de configuracion por caja para tiempo Aire");
        System.out.println(qry);
        e.printStackTrace();
        return null;
     }

    }

    public String obtener_valor_parametro_misc(String parametro, long  EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID){
        String valor=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        /*
        System.out.println("parametro:  "+parametro);
        System.out.println("EMPRESA_ID:  "+EMPRESA_ID);
        System.out.println("SERVICIO_ID:  "+SERVICIO_ID);
        System.out.println("TERMINAL_ID:  "+TERMINAL_ID);
        System.out.println("RUTA_ID:  "+RUTA_ID);
        System.out.println("CAJA_ID:  "+CAJA_ID);
        */
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "XER_TMS_PKG2.TMS_OBTENER_PARAM_MISC_PRC(?, ?, ?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
            stmt.setString(1,parametro);
            stmt.setLong(2, EMPRESA_ID);
            stmt.setLong(3, SERVICIO_ID);
            stmt.setLong(4, TERMINAL_ID);
            stmt.setLong(5, RUTA_ID);
            stmt.setLong(6, CAJA_ID);
            // -1X reg ocupado, 13S CON EL QUE FALLO; 000; 00X reg ocupado
            stmt.execute();
            
            valor = stmt.getString(7);
            //System.out.println(this.getCaja()+"=> Valor "+valor);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            if(valor==null) return "";
        } catch (SQLException ex){
            try {
                valor = stmt.getString(7);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null ) return "";
                return "";
            }
        }        
        return valor;
    }

    
    
    
      public String Registra_Transaccion_Lealtad(String operacion, String pDBLink){
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String listado="";
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        System.out.println(this.getCaja()+"=> operacion: "+operacion);
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "TMS_LEALTAD_WS_PKG.REGISTRA_TRANSACCION_XML_STR"+pDBLink+"(?, ?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " + 
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
            stmt.setString(1, operacion);
            stmt.execute();
            
            listado = stmt.getString(2).replace(",", "");
            listado = listado+","+stmt.getString(3).replace(",", "");
            listado = listado+","+stmt.getString(4).replace(",", "");
            listado = listado+","+stmt.getString(5);
            listado = listado+","+stmt.getString(6);
            System.out.println(this.getCaja()+"=> Valor "+listado);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            return listado;
        } catch (SQLException ex){
            ex.printStackTrace();
            try {
                //System.out.println(this.getCaja()+"=> SP ocupa "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
                System.out.println("    Por la Excepcion regresara: false,Error de Comunicacion con el WS,false");
                return "false,Error de Comunicacion con el WS,false,-1,-1";
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                return "false,Error de Comunicacion con el WS,false";
            }
        }
    }

      
        public boolean importeSuficiente(String noTarjeta,long usuario, String usrkey, double importeACubrir ){
        boolean respuesta = false;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        //noTarjeta = "220060003J";
        System.out.println(this.getCaja()+"=> noTarjeta: "+noTarjeta);
        System.out.println(this.getCaja()+"=> usuario: "+usuario);
        System.out.println(this.getCaja()+"=> usrkey: "+usrkey);
        System.out.println(this.getCaja()+"=> importeACubrir: "+importeACubrir);
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "TMS_LEALTAD_WS_PKG.IS_IMPORTE_SUFICIENTE(?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.setString(1, noTarjeta);
            stmt.setLong(2,usuario);
            stmt.setString(3,usrkey);
            stmt.setFloat(4,Float.valueOf(""+importeACubrir));
            stmt.execute();
            
            respuesta = Boolean.parseBoolean(stmt.getString(5));
            //System.out.println(this.getCaja()+"=> Valor "+valor);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            return respuesta;
        } catch (SQLException ex){
            try {
                ex.printStackTrace();
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                return false;
            }
        }
    }
 
        
    public boolean usarWSLealtad(){
       Vector vc = (Vector)em.createNativeQuery("select gp.PARAMETRO_VALOR from tms_parametros_config_tbl p,  tms_global_parametros_tbl gp where p.PARAMETRO_CODIGO = 'P_INVLEALTADWS' and gp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID").getResultList();
       if(vc.size()==0)
         return true;
       else
       {
           Vector v = (Vector)vc.get(0);
           if(v.get(0).toString().equals("S"))
               return true;
           else
               return false;
       }
    }

       public boolean validaTarjetaNoPermitida(String noTarjeta){
       Vector vc = (Vector)em.createNativeQuery("select XER_TMS_PKG3.VALIDA_TARJETA('"+noTarjeta+"') cantidad from dual ").getSingleResult();
       System.out.println("Numero de veces que se encontro la tarjeta "+noTarjeta+" : "+vc.get(0).toString());
       if((Integer.valueOf(vc.get(0).toString())) > 0)
         return true;
       else
         return false;
    }

       public Vector parametrosPorRutaPromocion(long terminalId){
           return (Vector)em.createNativeQuery("SELECT P.PARAMETRO_CODIGO,PR.RUTA_ID, PR.PARAMETRO_VALOR  FROM TMS_PARAMETROS_CONFIG_TBL P LEFT JOIN TMS_RUTA_PARAMETROS_TBL PR ON PR.PARAMETRO_CONFIG_ID = P.PARAMETRO_CONFIG_ID "
                   + "WHERE P.PARAMETRO_CODIGO IN ('P_VTA_NUM_BOL_PROM','P_VTA_NUM_BOL_X_PROM','P_VTA_PROM_SOLO_ADULT','P_VTA_FECHA_LIM_PROM') "
                   + "AND PR.RUTA_ID IN (SELECT PR1.RUTA_ID FROM TMS_PARAMETROS_CONFIG_TBL P1 LEFT JOIN TMS_RUTA_PARAMETROS_TBL PR1 ON PR1.PARAMETRO_CONFIG_ID = P1.PARAMETRO_CONFIG_ID "
                   + "WHERE P1.PARAMETRO_CODIGO = 'P_VTA_PROM_VIG' "
                   + "and PR1.PARAMETRO_VALOR = 'S') "
                   + "and 'S' = (SELECT TPAR.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL para left join  TMS_TERMINAL_PARAMETROS_TBL tpar on tpar.parametro_config_id = para.parametro_config_id where PARA.PARAMETRO_CODIGO = 'P_VTA_ACTIVA_PROMOS' and TPAR.TERMINAL_ID = "+terminalId+") "
                   + "order by PR.RUTA_ID, P.PARAMETRO_CODIGO").getResultList();

       }

   public Vector getAgenciasViaje()
    {
       Vector resp = new Vector();
       try{
       /*String qry = "select -1 cliente_id,'Seleccione un Cliente' agencia from dual "
               + "union all "
               + "select c.cliente_id, c.no_cuenta||'-'||c.nombre agencia  "
               + "from er_usuarios_tbl@repcontrol_link.estrellaroja.com.mx u  "
               + "left join er_tipos_usuario_tbl@repcontrol_link.estrellaroja.com.mx tu on tu.tipo_usuario_id = u.tipo_usuario_id "
               + "left join er_usuarios_clientes_tbl@repcontrol_link.estrellaroja.com.mx uc on uc.usuario_id = u.usuario_id "
               + "left join ER_CLIENTES_TBL@repcontrol_link.estrellaroja.com.mx c on c.cliente_id = uc.cliente_id "
               + "where tu.nombre_tipo = 'AGENCIA DE VIAJE' "
               + "and to_number(usuario_numero,9999999.9999) > 100004 "
               + "and c.activo = 'A' "
               + "--order by u.usuario_nombre";*/
         Vector v = (Vector)em.createNativeQuery("select XER_TMS_PKG3.get_clientes_credito('AGENCIA') from DUAL").getResultList();
         //System.out.println("getAgenciasViaje(v): "+v);
         Vector v2 = (Vector)v.get(0);
         //System.out.println("getAgenciasViaje(v2): "+v2);
         String[] array = null;
         if (v2.get(0).toString().indexOf("^")>=0)
         {
           array = v2.get(0).toString().split("\\^");
             for(int i= 0; array.length>i;i++)
             {
               String[] array2 = array[i].split("\\~");
               Vector n = new Vector();
               n.add(array2[0]);
               n.add(array2[1]);
               resp.add(n);
             }
        }
        else
         {
               String[] array2 = v2.get(0).toString().split("\\~");
               Vector n = new Vector();
               n.add(array2[0]);
               n.add(array2[1]);
               resp.add(n);
         }
         return resp;
         }catch(EJBException ex){
               Vector n = new Vector();
               n.add("-1");
               n.add("Seleccione un Cliente");
               resp.add(n);
             return resp;}
    }

    public BigDecimal getClienteSEDENA(){
        //Vector v = (Vector)em.createNativeQuery("select c.cliente_id from er_usuarios_tbl@repcontrol_link.estrellaroja.com.mx u   left join er_usuarios_clientes_tbl@repcontrol_link.estrellaroja.com.mx uc on uc.usuario_id = u.usuario_id  left join ER_CLIENTES_TBL@repcontrol_link.estrellaroja.com.mx c on c.cliente_id = uc.cliente_id  where to_number(usuario_numero,9999999.9999) = 100002").getResultList();
        try
        {
            Vector v = (Vector)em.createNativeQuery("select XER_TMS_PKG3.get_clientes_credito('SEDENA') from DUAL").getResultList();
            System.out.println("getClienteSEDENA: "+v);
            if(v.size()==0)
                return null;
            else
            {
                Vector v2 = (Vector)v.get(0);
                if(v2.get(0)==null)
                    return null;
                else
                    return new BigDecimal(v2.get(0).toString());
            }
        }catch(EJBException ex){ return null;}

    }

    public String getMesAnioBD(){
        String res = em.createNativeQuery("select to_char(SYSDATE,'_MM_RR') from dual").getSingleResult().toString();
        return res.replace("[", "").replace("]", "");
    }

    public boolean registrarVentaProducto(
        String empresa,
        String producto,
        long cantidad,
        String folio,
        String clave_producto,
        float precio_unitario,
        float subtotal,
        float iva,
        float total,
        float retencion,
        long corteId,
        String caja,
        long cajero,
        String terminal,
        long terminalId,
        long usuarioId,
        String tipoPago,
        String IdentificadorAdicional
        )
    {
        String query = "INSERT INTO XER_VENTAS_MISCELANEAS_TBL (VM_ID,CANAL_VENTA,EMPRESA,PRODUCTO,TIPO_OPERACION,TIPO_PAGO,CANTIDAD,NO_FOLIO,REFERENCIA,IDENTIFICADOR_ADICIONAL,VALOR_UNITARIO,SUBTOTAL,IVA,TOTAL,RETENCION,CORTE_ID,CAJA,CLAVE_CAJERO,CIUDAD_VENTA,FECHA_HORA_VENTA,FECHA_EFECTIVA,CREADO_POR,FECHA_CREACION,AUTORIZADO_POR,ULTIMA_ACTUALIZACION)"
                + "values ('"+terminalId+"'||XER_VENTAS_MISCELANEAS_SEQ.nextval,'TMS VENTA','"+empresa+"','"+producto+"','VT','"+tipoPago+"',"+cantidad+",'"+folio+"','"+clave_producto+"','',"+precio_unitario+","+subtotal+","+iva+","+total+","+retencion+","+corteId+",'"+caja+"',"+cajero+",'"+terminal+"',SYSDATE,SYSDATE,"+usuarioId+",SYSDATE,"+usuarioId+",SYSDATE)";
       System.out.println("Inserta el Producto: "+query);
        try
       {
          em.createNativeQuery(query).executeUpdate();
           return true;
       }catch(EJBException e){
        e.printStackTrace();
        }
        return false;
    }

    public boolean ventaJuconiActiva(){
        String Consulta=
            "SELECT 1 "
            + "FROM dual "
            + "WHERE trunc(SYSDATE) BETWEEN ( "
            + "SELECT TO_DATE(tmsGlo.PARAMETRO_VALOR,'DD/MM/RRRR') "
            + "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar "
            + ",TMS_GLOBAL_PARAMETROS_TBL tmsGlo "
            + "WHERE TMSPAR.PARAMETRO_CONFIG_ID = TMSGLO.PARAMETRO_CONFIG_ID "
            + "AND tmsPar.PARAMETRO_CODIGO = 'P_FINIJUC' "
            + ") AND ( "
            + "SELECT TO_DATE(tmsGlo.PARAMETRO_VALOR,'DD/MM/RRRR') "
            + "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar "
            + ",TMS_GLOBAL_PARAMETROS_TBL tmsGlo "
            + "WHERE TMSPAR.PARAMETRO_CONFIG_ID = TMSGLO.PARAMETRO_CONFIG_ID "
            + "AND TMSPAR.PARAMETRO_CODIGO ='P_FFINJUC')";
        try{
            //System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(x.size()==0) return false;  // NO ESTAMOS
            Vector y = (Vector) x.get(0);
            if(y.size()==0) return false; // NO ESTAMOS
            if(Integer.valueOf(y.get(0).toString())!=1) return false; // NO ESTAMOS
            return true; // SI ESTAMOS
        }catch(Exception nrex){
            return false;
        }
    }

        public String getNombreServicios(){
            String nombreServicio="";
            String impresionBoleto="";
        String Consulta=
            "SELECT S.SERVICIO_NOMBRE, SP.PARAMETRO_VALOR "
            + "FROM TMS_PARAMETROS_CONFIG_TBL P  "
            + "LEFT JOIN TMS_SERVICIO_PARAMETROS_TBL SP ON SP.PARAMETRO_CONFIG_ID = P.PARAMETRO_CONFIG_ID "
            + "LEFT JOIN TMS_SERVICIOS_TBL S ON S.SERVICIO_ID = SP.SERVICIO_ID "
            + "WHERE P.PARAMETRO_CODIGO = 'P_NOMSERBOL'";
        try{
           System.out.println(Consulta);
            Vector x = (Vector) em.createNativeQuery(Consulta).getResultList();
            System.out.println("getNombreServicios: "+x);
            if(x.size()==0) return ",|,";  // NO ESTAMOS
            for(int i=0; i<x.size(); i++)
            {
                Vector y = (Vector) x.get(i);
                nombreServicio = nombreServicio+y.get(0)+(i==(x.size()-1)?"":",");
                impresionBoleto = impresionBoleto+y.get(1)+(i==(x.size()-1)?"":",");
            }
            return nombreServicio+"|"+impresionBoleto;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return ",|,";
        }
    }

    public String getGeneraFolioPreimpreso(long terminalId)
    {
         Vector resp= (Vector)em.createNativeQuery("SELECT RPAD("+terminalId+",3,'0')||LPAD(TMS_CTRL_FOLIOS_SEQ.NEXTVAL,9,'0') FROM dual").getResultList();
         System.out.println("getGeneraFolioPreimpreso: "+resp.toString());
         return resp.get(0).toString().replace("[","").replace("]","");
    }

    public Vector getEstados()
    {
        return (Vector)em.createNativeQuery("select ESTADO_NOMBRE, to_char(LPAD(estado_id,3,'0')), DESCRIPCION from tms_estados_tbl where TIPO_COMPONENTE in ('CIUDAD','TERMINAL') order by ESTADO_NOMBRE").getResultList();
    }

    public Vector getProductoCargo(String pclaveProducto)
    {
        Vector resp = (Vector)em.createNativeQuery("SELECT P.PRODUCTO_ID, P.PRODUCTO_NOMBRE, E.EMPRESA_NOMBRE FROM TMS_PRODUCTOS_ER_TBL P LEFT JOIN TMS_EMPRESAS_TBL E ON E.EMPRESA_ID = P.EMPRESA_ID WHERE P.PRODUCTO_CLAVE = '"+pclaveProducto+"'").getResultList();
        if(resp.size()==0)
            return null;
        return (Vector)resp.get(0);
    }

       public Vector rutasConPromocionRedondoTTP(){
           return (Vector)em.createNativeQuery("SELECT RP.RUTA_ID FROM  TMS_PARAMETROS_CONFIG_TBL P , TMS_RUTA_PARAMETROS_TBL RP WHERE P.PARAMETRO_CODIGO =  'P_VTA_APLICA_TARIFA_RED' AND RP.PARAMETRO_CONFIG_ID = P.PARAMETRO_CONFIG_ID and RP.PARAMETRO_VALOR = 'S'").getResultList();
       }



    /*public String getCadenaVoucher(String cadena, String impresora,String tipo){
        System.out.println("Invoca getCadenaVoucher con: ");
        System.out.println("impresora: "+impresora);
        System.out.println("tipo: "+tipo);
      Vector vresp = (Vector)em.createNativeQuery("SELECT XER_TMS_PKG3.GET_CADENA_VOUCHER_FNC('"+cadena+"','"+impresora+"','"+tipo+"'') FROM DUAL").getResultList();
      if(vresp==null)
          return cadena;
      if(vresp.size()==0)
          return cadena;
      Vector v = (Vector)vresp.get(0);
      return v.get(0).toString();
    }
    */

      public Vector getImpresora(String caja){
          String qry ="SELECT I.IMPRESORA_NOMBRE,CI.SALIDA_IMPRESION, F.FORMATO_NOMBRE "
                  + "FROM TMS_CAJAS_TBL C "
                  + "LEFT JOIN TMS_CAJAS_IMPRESORAS_TBL CI ON CI.CAJA_ID = C.CAJA_ID  "
                  + "LEFT JOIN TMS_FORMATOS_BOLETO_TBL F on F.FORMATO_BOLETO_ID = CI.FORMATO_BOLETO_ID  "
                  + "LEFT JOIN TMS_IMPRESORAS_TBL I ON I.IMPRESORA_ID = CI.IMPRESORA_ID  "
                  + "WHERE UPPER(C.CAJA_NOMBRE) = upper('"+caja+"')  "
                  + "AND F.FORMATO_NOMBRE IN ('TICKET_TERMICO','TICKET') "
                  + "AND CI.ACTIVIDAD_IMPRESORA = 'TICKETS'";   

          return (Vector)em.createNativeQuery(qry).getResultList();
      }

    public String getTicketCompra(long terminalId)
    {
        Vector res = (Vector)em.createNativeQuery("select TMS_TICKET_PRODUCTOS_SEQ.nextval from dual").getSingleResult();
        return terminalId+res.get(0).toString();

    }

}