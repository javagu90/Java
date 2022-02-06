/*
 * TMSAcumularEstrellasFacadeBean.java
 *
 * Created on 5 de febrero de 2010, 06:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package soliciutdAcumularEstrellas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.internal.OracleCallableStatement;

/**
 *
 * @author asolis
 */
@Stateless
public class TMSAcumularEstrellasFacadeBean implements soliciutdAcumularEstrellas.TMSAcumularEstrellasFacadeRemote {
    @PersistenceContext(unitName = "TMSAcumularEstrellas-ACUM")
    private EntityManager em;    
    @Resource(name = "TMS_DB")
    private DataSource dataSource;    
    
    /**
     * Creates a new instance of TMSAcumularEstrellasFacadeBean
     */
    public TMSAcumularEstrellasFacadeBean() {
    }
    
    public Vector buscarOrigenesDestinos(){
       // System.out.println("Buscar Origenes/Destinos");        
//        return (Vector) em.createNativeQuery("SELECT estado_nombre FROM tms_estados_tbl "+
//                                                    " WHERE estado_nombre NOT IN ('CENTRAL') "+
//                                                    " AND TIPO_COMPONENTE NOT IN ('ACTIVIDAD', 'EDOGRAL') "+
//                                                    " ORDER BY estado_nombre").getResultList();
        return (Vector) em.createNativeQuery("select bd.TERMINAL_ID,bd.NOMBRE_TERMINAL, bd.NOMBRE_ESQUEMA, bd.NOMBRE_DBLINK from TMS_BASE_DATOS_CONFIG_TBL bd where bd.NOMBRE_TERMINAL <> 'CENTRAL' order by bd.NOMBRE_TERMINAL ASC").getResultList();
    }
    
    public Vector buscarServicios(){
        //System.out.println("Buscar Servicios");        
        return (Vector) em.createNativeQuery("SELECT SERVICIO_ID, SERVICIO_NOMBRE FROM tms_servicios_tbl "+
                                                  " ORDER BY SERVICIO_NOMBRE").getResultList();
    }

    public Vector buscarEmpresas(){
        //System.out.println("Buscar Empresas");        
        return (Vector) em.createNativeQuery("select e.EMPRESA_ID, e.EMPRESA_NOMBRE from TMS_PARAMETROS_CONFIG_TBL p ,TMS_EMPRESA_PARAMETROS_TBL ep ,TMS_EMPRESAS_TBL e where p.PARAMETRO_CODIGO = 'P_VTPLEALTAEMP' and ep.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID and e.EMPRESA_ID = ep.EMPRESA_ID and ep.PARAMETRO_VALOR = 'S'").getResultList();
    }

    public Vector buscarTiposPasajero(){
        //System.out.println("Buscar Tipos Pasajero");        
        return (Vector) em.createNativeQuery("select t.TIPO_PASAJERO_ID, t.NOMBRE_TIPO, t.LETRA_TIPO from TMS_TIPOS_PASAJERO_TBL t order by 1").getResultList();
    }


    public Vector buscaFolio(String folio, String tiposPago, String pDBLink){
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        //System.out.println("query buscaFolio: select b.EMPRESA, b.SERVICIO, b.ORIGEN, b.DESTINO, b.TIPO_PASAJERO, b.IMPORTE_BOLETO, b.NO_ASIENTO, b.NOMBRE_PASAJERO, b.CIUDAD_VENTA,to_char(b.FECHA_HORA_VENTA,'DD/MM/RRRR'),to_char(b.FECHA_HORA_VENTA,'RRRR-MM-DD'), c.RUTA_ID, b.FOLIO_PREIMPRESO,CASE WHEN add_months(trunc(b.FECHA_HORA_VENTA),+1)>= trunc(SYSDATE) then 'S' else 'N' end VALIDO, CASE WHEN b.CLAVE_CORRIDA is null then 'S' else 'N' end ABIERTO  from tms_boletos_venta_tbl"+pDBLink+" b, tms_corridas_tbl"+pDBLink+" c where b.FOLIO_PREIMPRESO in ("+folio+") and b.TIPO_PAGO not in ("+tiposPago+")  and c.CLAVE_CORRIDA(+) = b.CLAVE_CORRIDA)");
            return (Vector)em.createNativeQuery("select b.EMPRESA, b.SERVICIO, b.ORIGEN, b.DESTINO, b.TIPO_PASAJERO, b.IMPORTE_BOLETO, b.NO_ASIENTO, b.NOMBRE_PASAJERO, b.CIUDAD_VENTA,to_char(b.FECHA_HORA_VENTA,'DD/MM/RRRR'),to_char(b.FECHA_HORA_VENTA,'RRRR-MM-DD'), c.RUTA_ID, b.FOLIO_PREIMPRESO,CASE WHEN add_months(trunc(b.FECHA_HORA_VENTA),+1)>= trunc(SYSDATE) then 'S' else 'N' end VALIDO, CASE WHEN b.CLAVE_CORRIDA is null then 'S' else 'N' end ABIERTO  from tms_boletos_venta_tbl"+pDBLink+" b, tms_corridas_tbl"+pDBLink+" c where b.FOLIO_PREIMPRESO in ("+folio+") and b.TIPO_PAGO not in ("+tiposPago+")  and c.CLAVE_CORRIDA(+) = b.CLAVE_CORRIDA").getResultList();
    }    
    
    public String _ObtieneFechaHoraBDLealtad(){
        return em.createNativeQuery("select to_char(SYSDATE,'DDMMYYYYHH24MISS') from dual").getSingleResult().toString();
    }    
    
    public String getTerminalLocal(){
        return em.createNativeQuery("select bd.TERMINAL_ID from tms_base_datos_config_tbl bd where bd.ESQUEMA_PROPIO = 'S'").getSingleResult().toString();
    }    
    
    public Vector buscaCajaId(String nombre){
        return (Vector)em.createNativeQuery("select c.CAJA_ID from tms_cajas_tbl c where c.NOMBRE_EQUIPO = '"+nombre+"'").getResultList();
    }  
      
    public Vector getParametrosTermial(long terminal){
        String qry = "select tmsSp.PARAMETRO_VALOR " +
                "FROM TMS_PARAMETROS_CONFIG_TBL tmsPar   " +
                ",TMS_TERMINAL_PARAMETROS_TBL tmsSp  " +
                "WHERE 1 = 1  " +
                "AND tmsPar.PARAMETRO_CONFIG_ID = tmsSp.PARAMETRO_CONFIG_ID  " +
                "AND tmsSp.TERMINAL_ID = "+terminal+"  " +
                "AND tmsPar.PARAMETRO_CODIGO IN ('P_EMV_URL', 'P_EMV_BRANCH', 'P_EMV_USER', 'P_EMV_PASSWORD', 'P_EMV_MERCHANT', 'P_EMV_MERCHANT_MOTO', 'P_EMV_MERCHANT_AMEX', 'P_EMV_MERCHANT_AMEX_MOTO','P_SOCUSR','P_SOCPWD','P_SOCIDSUC') " +
                "ORDER BY tmsPar.PARAMETRO_CODIGO"; 
        Vector ve = (Vector)em.createNativeQuery(qry).getResultList();
        Vector v = new Vector();
        for(int i=0; i<ve.size();i++)
        {
            String s = ve.get(i).toString();
            s = s.replace('[',' ');
            s = s.replace(']',' ');
            s = s.trim();
            v.add(s);
        }
        return v;
    }    
        
    public Vector buscaRutaBoleto(String servicio, String empresa, String origen, String destino)
    {
//        String qry = "select r.RUTA_ID from TMS_RUTAS_TBL r, TMS_SERVICIOS_EMPRESAS_TBL s  " +
//                "where r.SERVICIO_ID = "+servicio+"  " +
//                "and r.TRAMO_ORIGEN_ID = "+origen+"  " +
//                "and r.TRAMO_DESTINO_ID = "+destino+" " +
//                "and s.SERVICIO_ID = r.SERVICIO_ID " +
//                "and s.EMPRESA_ID = "+empresa+"		 " +
//                "and s.RUTA_ID = r.RUTA_ID";
        
           String qry = " select r.RUTA_ID, r.TRAMO_ORIGEN_ID ORIGEN, r.TRAMO_DESTINO_ID DESTINO  " +
                   "from TMS_RUTAS_TBL r " +
                   ", TMS_SERVICIOS_EMPRESAS_TBL s " +
                   ", TMS_tramos_TBL t   " +
                   "where   1=1 " +
                   "and s.SERVICIO_ID = "+servicio+"  " +
                   "and s.EMPRESA_ID = "+empresa+" " +
                   "and t.TRAMO_ORIGEN_ID =  "+origen+"    " +
                   "and t.TRAMO_DESTINO_ID = "+destino+" " +
                   "and r.RUTA_ID = t.RUTA_ID  " +
                   "and s.RUTA_ID = r.RUTA_ID " +
                   "order by r.RUTA_ID        ";
        
        System.out.println("qry(buscaRutaBoleto): "+qry);
       return (Vector) em.createNativeQuery(qry).getResultList();
    }
    
     public Vector buscaDatosLealtadBoleto(String ruta)
    {
        String qry = "SELECT  TP.TIPO_PASAJERO_ID " +
                ",(select rp.LUNES_VALOR||','||rp.MARTES_VALOR||','||rp.MIERCOLES_VALOR||','||rp.JUEVES_VALOR||','||rp.VIERNES_VALOR||','||rp.SABADO_VALOR||','||rp.DOMINGO_VALOR from  TMS_PARAMETROS_CONFIG_TBL p,TMS_RUTA_PARAMETROS_TBL rp where p.PARAMETRO_CODIGO = 'P_VTPLEALTAD' and rp.RUTA_ID = TDP.RUTA_ID and rp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID) LEALTAD  " +
                ",(select rp.PARAMETRO_VALOR from  TMS_PARAMETROS_CONFIG_TBL p,TMS_RUTA_PARAMETROS_TBL rp where p.PARAMETRO_CODIGO = 'P_VLEALTXVTXC' and rp.RUTA_ID = TDP.RUTA_ID and rp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID) APLICA_TIPO_LEALTAD  " +
                ",TDP.ADICIONAL2 APLICA_LEALTAD, TDP.PCT_DESCUENTO, TDP.ADICIONAL1  " +
                "FROM Tms_Descuentos_Rutas_Tbl TDP, TMS_TIPOS_PASAJERO_TBL TP  " +
                "WHERE TRUNC(SYSDATE) BETWEEN TRUNC(TP.FECHA_DESDE) AND TRUNC(NVL(TP.FECHA_HASTA,TRUNC(SYSDATE)))  " +
                "and TDP.TIPO_PASAJERO_ID = tp.TIPO_PASAJERO_ID " +
                "and TDP.ruta_id = "+ruta; 
       return (Vector) em.createNativeQuery(qry).getResultList();
    }
       
    public Vector buscaODRuta(String ruta)
    {
        String qry = "SELECT  r.TRAMO_ORIGEN_ID, r.TRAMO_DESTINO_ID " +
                "FROM Tms_Rutas_Tbl r "+
                "WHERE r.ruta_id = "+ruta; 
       Vector v = (Vector) em.createNativeQuery(qry).getResultList();
       return (Vector)v.get(0) ;
    }   
     
    public void insertaRegistroLealtad(String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento,long user, String pDBSchema, String pDBLink, String fecha, String terminalId){
         if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        if(terminalId.length()<3)
        {
             for(int i=terminalId.length(); i<3; i++)
                terminalId = terminalId+"0";
        }
            
        String Consulta =
                        "INSERT INTO " +
                        pDBSchema+"XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" " +
                        " VALUES ('"+terminalId+"'||XER_PROGRAMA_LEALTAD_SEQ.nextval"+pDBLink+","+boleto_id+",'"+folio_preimpreso+"','"+preoducto+"','"+ciudad_venta+"','P','"+tipo_operacion+"','"+num_tarjeta+"','"+numero_operacion+"','"+usuario+"','"+contraseña+"',"+importe+",'"+tipo_pasajero+"','"+caja+"','"+unidad_negocio+"',"+descuento+",null,'','','','','','','','','','','',"+user+",to_date('"+fecha+" 00:00','DD/MM/RRRR HH24:MI'),"+user+",SYSDATE)";

        em.createNativeQuery(Consulta).executeUpdate();
    }    

  public Vector buscaRegistroLealtad(String operacion, String folio){
        String qry = "select t.NUMERO_TARJETA num, t.IMPORTE_BOLETO importe, t.TIPO_BOLETO tipo from XER_PROGRAMA_LEALTAD_TBL t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = '"+operacion+"' and t.STATUS <> 'F' order by t.FECHA_CREACION DESC";
        return(Vector) em.createNativeQuery(qry).getResultList();
    }

    public String getTipoPagosValidoslealtad(){
       String qry = "select tp.CLAVE from TMS_VTA_TIPOS_PAGO_TBL tp where tp.ADICIONAL1 = 'N'";
       String cad = "";
       Vector v = (Vector) em.createNativeQuery(qry).getResultList();
       for(int i=0; i<v.size(); i++)
       {
           Vector vv = (Vector) v.get(i);
           if(i==0)
               cad = "'"+vv.get(0).toString()+"'";
           else
               cad = cad + ",'"+vv.get(0).toString()+"'";
       }
       return cad;
    }

    public float getTarifaRuta(String fecha, long rutaId, long origenId, long destinoId){
       String qry = "  SELECT t1.IMPORTE_TARIFA  " +
               "FROM ( " +
               "SELECT ta.IMPORTE_TARIFA " +
               "FROM TMS_RUTAS_TBL rt " +
               ",TMS_TRAMOS_TBL tr " +
               ",TMS_TARIFAS_TBL ta " +
                "WHERE rt.RUTA_ID = "+rutaId+"  " +
               "AND rt.RUTA_ID = tr.RUTA_ID " +
               "AND tr.TRAMO_ORIGEN_ID = "+origenId+" " +
               "AND tr.TRAMO_DESTINO_ID = "+destinoId+" " +
               "AND tr.TRAMO_ID = ta.TRAMO_ID " +
               "AND ta.TIPO_TARIFA = 'SEN' " +
               "AND ta.FECHA_HORA_TARIFA <= to_date('"+fecha+"','DD/MM/RRRR') " +
               "ORDER BY ta.FECHA_HORA_TARIFA DESC " +
               ") t1 " +
               "WHERE ROWNUM = 1"; 
       String cad = "";
       Vector v = (Vector) em.createNativeQuery(qry).getResultList();
       if(v.size()==0)
           return 0;
       Vector vv = (Vector) v.get(0);
       return Float.valueOf(vv.get(0).toString());
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
    
      public List<String> Registra_Transaccion_Lealtad(String operacion){
        List listado = new ArrayList<String>();
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        System.out.println("operacion: "+operacion);
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "TMS_LEALTAD_WS_PKG.REGISTRA_TRANSACCION_XML_STR(?, ?, ?, ?); " +
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
            stmt.setString(1, operacion);
            stmt.execute();
            
            listado.add(stmt.getString(2));
            listado.add(stmt.getString(3));
            listado.add(stmt.getString(4));
            //System.out.println(this.getCaja()+"=> Valor "+valor);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            return listado;
        } catch (SQLException ex){
            try {
                //System.out.println(this.getCaja()+"=> SP ocupa "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                return null;
            }
        }
    }
    
      public String buscaParametroCajaLealtad(String cajaId, String parametro){
        String qry = "select cp.PARAMETRO_VALOR from   TMS_PARAMETROS_CONFIG_TBL p,TMS_CAJA_PARAMETROS_TBL cp where p.PARAMETRO_CODIGO = '"+parametro+"' and cp.CAJA_ID = "+cajaId+" and cp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID";
        //System.out.println("buscaParametroLealtad: "+qry);
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
    
}
