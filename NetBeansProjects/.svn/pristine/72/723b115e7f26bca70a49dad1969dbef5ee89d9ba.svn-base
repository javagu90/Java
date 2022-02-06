/*
 * TMSAcumularCentralFacadeBean.java
 *
 * Created on 5 de febrero de 2010, 06:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package soliciutdAcumularEstrellas;

import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author asolis
 */
@Stateless
public class TMSAcumularCentralFacadeBean implements soliciutdAcumularEstrellas.TMSAcumularCentralFacadeRemote {
    @PersistenceContext(unitName = "TMSAcumulacionEstrellas-Central-ACUM")
    private EntityManager em;    
    @Resource(name = "TMS_DB")
    private DataSource dataSource;    
    
    /**
     * Creates a new instance of TMSAcumularCentralFacadeBean
     */
    public TMSAcumularCentralFacadeBean() {
    }
    
    public Vector buscarOrigenesDestinos(){
        System.out.println("Buscar Origenes/Destinos");        
//        return (Vector) em.createNativeQuery("SELECT estado_nombre FROM tms_estados_tbl "+
//                                                    " WHERE estado_nombre NOT IN ('CENTRAL') "+
//                                                    " AND TIPO_COMPONENTE NOT IN ('ACTIVIDAD', 'EDOGRAL') "+
//                                                    " ORDER BY estado_nombre").getResultList();
        return (Vector) em.createNativeQuery("select bd.TERMINAL_ID,bd.NOMBRE_TERMINAL, bd.NOMBRE_ESQUEMA, bd.NOMBRE_DBLINK from TMS_BASE_DATOS_CONFIG_TBL bd where bd.NOMBRE_TERMINAL <> 'CENTRAL' order by bd.NOMBRE_TERMINAL ASC").getResultList();
    }
    
    public Vector buscarServicios(){
        System.out.println("Buscar Servicios");        
        return (Vector) em.createNativeQuery("SELECT SERVICIO_ID, SERVICIO_NOMBRE FROM tms_servicios_tbl "+
                                                  " ORDER BY SERVICIO_NOMBRE").getResultList();
    }

    public Vector buscarEmpresas(){
        System.out.println("Buscar Empresas");        
        return (Vector) em.createNativeQuery("select e.EMPRESA_ID, e.EMPRESA_NOMBRE from TMS_PARAMETROS_CONFIG_TBL p ,TMS_EMPRESA_PARAMETROS_TBL ep ,TMS_EMPRESAS_TBL e where p.PARAMETRO_CODIGO = 'P_VTPLEALTAEMP' and ep.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID and e.EMPRESA_ID = ep.EMPRESA_ID and ep.PARAMETRO_VALOR = 'S'").getResultList();
    }

    public Vector buscarTiposPasajero(){
        System.out.println("Buscar Tipos Pasajero");        
        return (Vector) em.createNativeQuery("select t.TIPO_PASAJERO_ID, t.NOMBRE_TIPO, t.LETRA_TIPO from TMS_TIPOS_PASAJERO_TBL t order by 1").getResultList();
    }


//    public Vector buscaFolio(String folio, String tiposPago){
//            return (Vector)em.createNativeQuery("select b.EMPRESA, b.SERVICIO, b.ORIGEN, b.DESTINO, b.TIPO_PASAJERO, b.IMPORTE_BOLETO, b.NO_ASIENTO, b.NOMBRE_PASAJERO, b.CIUDAD_VENTA,to_char(b.FECHA_HORA_VENTA,'DD/MM/RRRR'),to_char(b.FECHA_HORA_VENTA,'RRRR-MM-DD'), c.RUTA_ID from tms_boletos_venta_tbl b, tms_corridas_tbl c where b.FOLIO_PREIMPRESO = '"+folio+"' and b.TIPO_PAGO not in ("+tiposPago+")  and c.CLAVE_CORRIDA = b.CLAVE_CORRIDA").getResultList();
//    }    
    
    public String _ObtieneFechaHoraBDLealtad(){
        return em.createNativeQuery("select to_char(SYSDATE,'DDMMYYYYHH24MISS') from dual").getSingleResult().toString();
    }    
    
    public String getTerminalLocal(){
        return em.createNativeQuery("select bd.TERMINAL_ID from tms_base_datos_config_tbl bd where bd.ESQUEMA_PROPIO = 'S'").getSingleResult().toString();
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
        String qry = "select r.RUTA_ID from TMS_RUTAS_TBL r, TMS_SERVICIOS_EMPRESAS_TBL s  " +
                "where r.SERVICIO_ID = "+servicio+"  " +
                "and r.TRAMO_ORIGEN_ID = "+origen+"  " +
                "and r.TRAMO_DESTINO_ID = "+destino+" " +
                "and s.SERVICIO_ID = r.SERVICIO_ID " +
                "and s.EMPRESA_ID = "+empresa+"		 " +
                "and s.RUTA_ID = r.RUTA_ID";
        System.out.println("qry(buscaRutaBoleto): "+qry);
       return (Vector) em.createNativeQuery(qry).getResultList();
    }
    
    public Vector buscaDatosLealtadBoleto(String ruta)
    {
        String qry = "SELECT  TP.TIPO_PASAJERO_ID " +
                ",(select rp.LUNES_VALOR||','||rp.MARTES_VALOR||','||rp.MIERCOLES_VALOR||','||rp.JUEVES_VALOR||','||rp.VIERNES_VALOR||','||rp.SABADO_VALOR||','||rp.DOMINGO_VALOR from  TMS_PARAMETROS_CONFIG_TBL p,TMS_RUTA_PARAMETROS_TBL rp where p.PARAMETRO_CODIGO = 'P_VTPLEALTAD' and rp.RUTA_ID = TDP.RUTA_ID and rp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID) LEALTAD  " +
                ",(select rp.PARAMETRO_VALOR from  TMS_PARAMETROS_CONFIG_TBL p,TMS_RUTA_PARAMETROS_TBL rp where p.PARAMETRO_CODIGO = 'P_VLEALTXVTXC' and rp.RUTA_ID = TDP.RUTA_ID and rp.PARAMETRO_CONFIG_ID = p.PARAMETRO_CONFIG_ID) APLICA_TIPO_LEALTAD  " +
                ",TDP.ADICIONAL2 APLICA_LEALTAD  " +
                "FROM Tms_Descuentos_Rutas_Tbl TDP, TMS_TIPOS_PASAJERO_TBL TP  " +
                "WHERE TRUNC(SYSDATE) BETWEEN TRUNC(TP.FECHA_DESDE) AND TRUNC(NVL(TP.FECHA_HASTA,TRUNC(SYSDATE)))  " +
                "and TDP.TIPO_PASAJERO_ID = tp.TIPO_PASAJERO_ID " +
                "and TDP.ruta_id = "+ruta; 
       return (Vector) em.createNativeQuery(qry).getResultList();
    }
       
    public void insertaRegistroLealtad(String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento,long user, String pDBSchema, String pDBLink){
         if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                        "INSERT INTO " +
                        pDBSchema+"XER_PROGRAMA_LEALTAD_TBL"+pDBLink+" " +
                        " VALUES (XER_PROGRAMA_LEALTAD_SEQ.nextval"+pDBLink+","+boleto_id+",'"+folio_preimpreso+"','"+preoducto+"','"+ciudad_venta+"','P','"+tipo_operacion+"','"+num_tarjeta+"','"+numero_operacion+"','"+usuario+"','"+contraseña+"',"+importe+",'"+tipo_pasajero+"','"+caja+"','"+unidad_negocio+"',"+descuento+",null,'','','','','','','','','','','',"+user+",SYSDATE,"+user+",SYSDATE)";

        em.createNativeQuery(Consulta).executeUpdate();
    }    

  public Vector buscaRegistroLealtad(String operacion, String folio){
        String qry = "select t.NUMERO_TARJETA num from XER_PROGRAMA_LEALTAD_TBL t where t.FOLIO_BOLETO = '"+folio+"' and t.TIPO_OPERACION = '"+operacion+"' and t.STATUS <> 'F' order by t.FECHA_CREACION DESC";
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
