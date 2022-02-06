/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package solicitud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OraclePreparedStatement;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.sql.DataSource;



/**
 *
 * @author vgonzalez
 */
@Stateless
public class FacturaTMSREPCONTROLBean implements FacturaTMSREPCONTROLBeanRemote {
    @PersistenceContext(unitName = "Facturar-REPCONTROL")
    private EntityManager managerRepcontrol;
    
    @Resource(name = "REP_CONTROL_DB")
    private DataSource dataSource_ER_CONTROL;

    public Vector buscarClienteRFC(String RFC)
   {
       Vector temp =  null;
       //System.out.println("BUSCAR CLIENTE POR RFC");
       try{
//           temp = (Vector) manager.createNativeQuery("SELECT * FROM TMS_CLIENTES_TBL WHERE rfc = '"+RFC+"'").getSingleResult();
            String qry = "select "
                    + "'' CLIENTE_ID, "
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
                    + "RFC "
                    + "from ER_CLIENTES_TBL@REPCONTROL_LINK.ESTRELLAROJA.COM.MX "
                    + "WHERE rfc = '"+RFC+"' ";
           temp = (Vector) managerRepcontrol.createNativeQuery(qry).getSingleResult();
           //System.out.println(" Todo bien " + temp);

       }catch(Exception e)
       {
           System.out.println("exception "+e);
           return null;
       }
       return temp;
   }

   public Vector buscarClienteNombre(String nombres)
   {
       Vector temp =  null;
       //System.out.println("BUSCAR CLIENTE POR Nombre");
       try{
           //temp = (Vector) manager.createNativeQuery("SELECT * FROM TMS_CLIENTES_TBL WHERE UPPER(REPLACE(nombre, ' ')) = UPPER(REPLACE('"+nombres+"', ' '))"+
             //                                        " and rownum = 1").getSingleResult();

           //System.out.println(" Todo bien " + temp);
            String qry = "select "
                    + "'' CLIENTE_ID, "
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
                    + "RFC "
                    + "from ER_CLIENTES_TBL "
                    + "WHERE UPPER(REPLACE(nombre, ' ')) = UPPER(REPLACE('"+nombres+"', ' ')) "
                    + " and rownum = 1";
           temp = (Vector) managerRepcontrol.createNativeQuery(qry).getSingleResult();

       }catch(Exception e)
       {
           System.out.println("exception "+e);
           return null;
       }
       return temp;
   }

 public Vector GeneraFactura(String P_PARAMETROS_FACTURA,String P_PARAMETROS_PRODUCTOS, String modo,String tipoFactura,long clienteId,float monto,String usuarioId){
   //PROCEDURE GET_OPERACION_FACTURA_PRC (
   //P_SESION IN VARCHAR2,   P_PARAMETROS_FACTURA IN VARCHAR2, P_PARAMETROS_PRODUCTOS IN VARCHAR2, P_PARAMETROS_ADENDA IN VARCHAR2,  P_OPERACION IN VARCHAR2,
   //P_FACTURA_SALIDA  OUT VARCHAR2, P_OPERACION_EXITOSA   OUT VARCHAR2,   P_ERROR_CODE   OUT VARCHAR2,  P_ERROR_MSJ   OUT VARCHAR2  ) IS
       //P_OPERACION = "T";
       //P_PARAMETROS_FACTURA = factura_id|strFolio|strUnidadNegocio|CLAVE_FACTURA_ELEC|CLIENTE_ID|SUBTOTAL|IVA|IVA_RETENIDO|RET_5MILLAR|DESCUENTO|TOTAL|RAZON_SOCIAL|RFC|CONCEPTO|OBSERVACIONES|FECHA|TELEFONO|DOMICILIO|No_Ext|No_Int|COLONIA|CODIGOPOSTAL|CIUDAD|ESTADO|PAIS|FECHA_VENCIMIENTO|FECHA_CONTABLE|ESTADO_FACTURA|E_MAIL|RUTA_PDF|RUTA_XML|strProducto(GUIA)|met_pago(CRE,TDC,etc)|num_cuenta(digitos de la TDC)|strUnidad(Servicio, no aplica, etc)|creado_por|sucursal|id_guias en un string
       //P_PARAMETROS_PRODUCTOS =^(Registro)|(campos)
       //^|CONTRATO|100312(numero de giua, contrato, folio de boleto, etc)
      Vector P_RESULTADO = new Vector();
      OracleCallableStatement stmt=null;
      Connection cnx=null;
       try {
            System.out.println("MODO "+modo);
            System.out.println("P_PARAMETROS_FACTURA "+P_PARAMETROS_FACTURA);
            System.out.println("P_PARAMETROS_PRODUCTOS "+P_PARAMETROS_PRODUCTOS);
            System.out.println("tipoFactura: "+tipoFactura);
            System.out.println("clienteId: "+clienteId);
            System.out.println("monto: "+monto);
            System.out.println("usuarioId: "+usuarioId);
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
                P_RESULTADO.addElement(status);
                P_RESULTADO.addElement(error);
                if(status.equals("TRUE"))
                {
                    StringTokenizer fst = new StringTokenizer(P_FACTURA_SALIDA,"~");
                    String facturaId = fst.nextToken();
                    String LlaveFact = fst.nextToken();
                    String rutapdf = fst.nextToken();
                    String rutaxml = fst.nextToken();
                    P_RESULTADO.addElement(LlaveFact);
                    P_RESULTADO.addElement(rutapdf);
                    P_RESULTADO.addElement(rutaxml);
                    P_RESULTADO.addElement(facturaId);

                }
                System.out.println("GeneraFactura.P_RESULTADO = "+P_RESULTADO);


                stmt.close();
                if(cnx!=null) cnx.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                P_RESULTADO = new Vector();
                P_RESULTADO.addElement("RECHAZADA");
                P_RESULTADO.addElement( "No se pudo generar la factura: "+ex.getErrorCode());
                try {
                    if(stmt!=null)
                        stmt.close();
                    if(cnx!=null) cnx.close();
                } catch (Exception e1) {
                   e1.printStackTrace();
                }
                return null;
            }
        return P_RESULTADO;
   }

public String getRutaPDFFactura(String FolioFactura)
{
  String BoletoFacturaID="";
      Vector resultado;
      //String qry =" SELECT adicional1 FROM TMS_BOLETOS_FACTURADOS_TBL BF  where BF.folio_factura ='"+FolioFactura+"'";
      String qry ="select RUTA_PDF from ER_FACTURAS_TBL where FOLIO_FACTURA = "+FolioFactura+" and UNIDAD_NEGOCIO = 'TMS'";
      try {
          System.out.println("TieneFactura "+qry);
           resultado =(Vector)managerRepcontrol.createNativeQuery(qry).getResultList();
           if (resultado != null)
               resultado=(Vector )resultado.elementAt(0);
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }

      String cade=null;

      if (resultado != null &&  !   resultado.isEmpty() )
          cade=resultado.get(0).toString();
      return cade;

}

public Vector buscarDatosFacCan(String folio)
{
    String qry =" SELECT FACTURA_ID,FOLIO_FACTURA,CLAVE_FACTURA_ELEC,total FROM ER_FACTURAS_TBL WHERE FOLIO_FACTURA = '"+folio+"' AND UNIDAD_NEGOCIO = 'TMS' and ESTADO_FACTURA = 'A'";
    System.out.println("qry buscarDatosFacCan: "+qry);
    try{
        Vector vres =(Vector)  managerRepcontrol.createNativeQuery(qry).getResultList();
        if(vres!=null && vres.size()>0)
            return (Vector)vres.get(0);
        else
            return null;
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
}

public String boletosFolCanFact(String facturaId)
{
    String qry =" SELECT REFERENCIA_PRODUCTO FROM ER_PRODUCTOS_FACTURAS_TBL "
            + "WHERE FACTURA_ID = "+facturaId+" "
            + "AND NOMBRE_PRODUCTO = 'BOLETO' ";
    System.out.println("qry boletosFolCanFact: "+qry);
    try{
        Vector vres =(Vector)  managerRepcontrol.createNativeQuery(qry).getResultList();
        return vres.toString().replace("[","").replace("]","");
       } catch (Exception e) {
          e.printStackTrace();
          return "";
      }
}

   public Vector cancelarFactura(String P_PARAMETROS_FACTURA,String P_PARAMETROS_PRODUCTOS, String modo,String tipoFactura,long clienteId,float monto,String usuarioId)
   {
   //PROCEDURE GET_OPERACION_FACTURA_PRC (
   //P_SESION IN VARCHAR2,   P_PARAMETROS_FACTURA IN VARCHAR2, P_PARAMETROS_PRODUCTOS IN VARCHAR2, P_PARAMETROS_ADENDA IN VARCHAR2,  P_OPERACION IN VARCHAR2,
   //P_FACTURA_SALIDA  OUT VARCHAR2, P_OPERACION_EXITOSA   OUT VARCHAR2,   P_ERROR_CODE   OUT VARCHAR2,  P_ERROR_MSJ   OUT VARCHAR2  ) IS
       //P_OPERACION = "T";
       //P_PARAMETROS_FACTURA = factura_id|strFolio|strUnidadNegocio|CLAVE_FACTURA_ELEC|CLIENTE_ID|SUBTOTAL|IVA|IVA_RETENIDO|RET_5MILLAR|DESCUENTO|TOTAL|RAZON_SOCIAL|RFC|CONCEPTO|OBSERVACIONES|FECHA|TELEFONO|DOMICILIO|No_Ext|No_Int|COLONIA|CODIGOPOSTAL|CIUDAD|ESTADO|PAIS|FECHA_VENCIMIENTO|FECHA_CONTABLE|ESTADO_FACTURA|E_MAIL|RUTA_PDF|RUTA_XML|strProducto(GUIA)|met_pago(CRE,TDC,etc)|num_cuenta(digitos de la TDC)|strUnidad(Servicio, no aplica, etc)|creado_por|sucursal|id_guias en un string
       //P_PARAMETROS_PRODUCTOS =^(Registro)|(campos)
       //^|CONTRATO|100312(numero de giua, contrato, folio de boleto, etc)
      Vector P_RESULTADO = new Vector();
      OracleCallableStatement stmt=null;
      Connection cnx=null;
       try {
            System.out.println("MODO "+modo);
            System.out.println("P_PARAMETROS_FACTURA "+P_PARAMETROS_FACTURA);
            System.out.println("P_PARAMETROS_PRODUCTOS "+P_PARAMETROS_PRODUCTOS);
            System.out.println("tipoFactura: "+tipoFactura);
            System.out.println("clienteId: "+clienteId);
            System.out.println("monto: "+monto);
            System.out.println("usuarioId: "+usuarioId);
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
                P_RESULTADO.addElement(status);
                P_RESULTADO.addElement(error);
                if(status.equals("TRUE") && P_FACTURA_SALIDA!=null)
                {
                    StringTokenizer fst = new StringTokenizer(P_FACTURA_SALIDA,"~");
                    String facturaId = fst.nextToken();
                    P_RESULTADO.addElement(facturaId);
                    /*
                    if(clienteId!=-1 && tipoFactura.equals("CRE"))
                    {
                        //Actualiza el monto pendiente por facturar del cliente
                        System.out.println("Actualiza el monto pendiente por facturar del cliente...");
                        String qry = "update ER_CLIENTES_TBL set SALDO_PENDIENTE_FACT = (SALDO_PENDIENTE_FACT+"+monto+ "), ULTIMA_FECHA_ACTUALIZACION=SYSDATE, ULTIMA_ACTUALIZACION_POR ="+usuarioId+"  where cliente_id = "+clienteId;
                        System.out.println("qry(Actualiza): "+qry);
                        int executeUpdate = managerRepcontrol.createNativeQuery(qry).executeUpdate();
                        System.out.println("executeUpdate: "+executeUpdate);

                    }*/
                }
                System.out.println("GeneraFactura.P_RESULTADO = "+P_RESULTADO);


                stmt.close();
                if(cnx!=null) cnx.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                P_RESULTADO = new Vector();
                P_RESULTADO.addElement("RECHAZADA");
                P_RESULTADO.addElement( "No se pudo cancelar la factura: "+ex.getErrorCode());
                try {
                    if(stmt!=null)
                        stmt.close();
                    if(cnx!=null) cnx.close();
                } catch (Exception e1) {
                   e1.printStackTrace();
                }
                return null;
            }
        return P_RESULTADO;
   }


   public String insertarcliente(int par, String nombre, String calle, String interior,String exterior, String col,String cp,String mun,String cd,String edo, String usuario, String rfc, String rfc_ant,String email)
   {
        String p_respuesta = null;
        Connection cnx = null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        //System.out.println(par + " "+nombre+" "+apaterno+" "+amaterno + " "+rfc+" "+rfc_ant);
        String apaterno ="";
        String amaterno = "";
        System.out.println("**************   Guardando Email "+email);
        try {
             String qry="";
             int repetido = -1;
             Vector resp = (Vector) managerRepcontrol.createNativeQuery("SELECT COUNT (*) FROM ER_CLIENTES_TBL WHERE rfc = '"+rfc+"'").getSingleResult();
                 repetido = Integer.valueOf(resp.get(0).toString());

              if(par == 0)
              {
                   System.out.println("repetido "+repetido);
                   if(repetido > 0)
                   {
                      System.out.println("Esta Repetido");
                      return "Esta Repetido";
                   }
                   qry = "   INSERT INTO ER_CLIENTES_TBL(CLIENTE_ID ,NOMBRE,  CALLE,  NO_INT, NO_EXT,  COLONIA, CODIGO_POSTAL, CIUDAD, ESTADO, RFC, EMAIL,PERSONA_MORAL,APLICA_RETENCION,FECHA_REGISTRO,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION) "
                           + "VALUES(ER_CLIENTE_SEQ.NEXTVAL,'"+nombre+"','"+calle+"','"+interior+"','"+exterior+"','"+col+"','"+cp+"','"+cd+"','"+edo+"','"+rfc+"','"+email+"','N','N',SYSDATE,"+usuario+",SYSDATE,"+usuario+",SYSDATE)";
                   System.out.println("qry: "+qry);
                   int resultado = managerRepcontrol.createNativeQuery(qry).executeUpdate();
                   System.out.println("resultado: "+resultado);
              }
              //Modificacion
                 if(par == 1)
                 {

                    repetido = -1;
                    resp = (Vector) managerRepcontrol.createNativeQuery("SELECT COUNT (*) FROM ER_CLIENTES_TBL WHERE rfc = '"+rfc_ant+"'").getSingleResult();
                    repetido = Integer.valueOf(resp.get(0).toString());
                    if (repetido < 1)
                    {
                      System.out.println("No existe");
                      return "No existe";
                    }
                      if (repetido > 1) {
                         System.out.println("Existe mas de una coincidencia. Contacte a sistemas");
                       return "Existen muchos";
                      }
                      if (repetido == 1) {
                          qry = "UPDATE ER_CLIENTES_TBL C "
                                  + "SET C.nombre = '"+nombre+"', "
                                  + "C.CALLE = '"+calle+"', "
                                  + "C.CIUDAD = '"+cd+"', "
                                  + "C.CODIGO_POSTAL = '"+cp+"', "
                                  + "C.COLONIA = '"+col+"', "
                                  + "C.ESTADO = '"+edo+"', "
                                  + "C.ULTIMA_FECHA_ACTUALIZACION = SYSDATE, "
                                  + "C.NO_EXT = '"+exterior+"', "
                                  + "C.NO_INT = '"+interior+"', "
                                  + "C.RFC = '"+rfc+"', "
                                  + "C.ULTIMA_ACTUALIZACION_POR = "+usuario+", "
                                  + "c.email = '"+email+"' "
                                  + "WHERE C.RFC = '"+rfc_ant+"'";
                           System.out.println("qry: "+qry);
                           int resultado = managerRepcontrol.createNativeQuery(qry).executeUpdate();
                           System.out.println("resultado: "+resultado);
                       }
                 }
        return "Todo bien";

        } catch (EJBException ex) {
            try {
                ex.printStackTrace();
                p_respuesta = stmt.getString(1);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                p_respuesta="ALGO NO SALIO BIEN";
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("Excepcion Bean IniciarSesion - "+p_respuesta);
                return p_respuesta;
            }
        }
   }
}
