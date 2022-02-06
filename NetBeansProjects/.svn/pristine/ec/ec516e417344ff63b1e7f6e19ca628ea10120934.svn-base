/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.solicitudes;

import WS_CONTROL.Ventour.entidades.Contrato;
import WS_CONTROL.Ventour.entidades.Estado;
import WS_CONTROL.Ventour.entidades.Municipio;
import WS_CONTROL.Ventour.entidades.TramoRuta;
import WS_CONTROL.Ventour.entidades.Unidad;
import WS_CONTROLVentour.getCancelaContratoReq;
import WS_CONTROLVentour.getCancelaContratoResp;
import WS_CONTROLVentour.getEstadosReq;
import WS_CONTROLVentour.getEstadosResp;
import WS_CONTROLVentour.getMunicipiosReq;
import WS_CONTROLVentour.getMunicipiosResp;
import WS_CONTROLVentour.getOperacionContratoReq;
import WS_CONTROLVentour.getOperacionContratoResp;
import WS_CONTROLVentour.getUnidadesReq;
import WS_CONTROLVentour.getUnidadesResp;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.driver.OracleCallableStatement;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.smartcardio.ATR;

import oracle.jdbc.OraclePreparedStatement;

import java.io.InputStreamReader;


import java.net.MalformedURLException;


import java.net.URL;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.math.BigInteger;



import java.net.URLConnection;
import java.util.Date;
import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;

import org.xml.sax.helpers.DefaultHandler;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




/**
 *
 * @author brojas
 */
@Stateless
public class wsControlVentourFacadeBean implements wsControlVentourFacadeBeanRemote, Serializable {
 private SimpleDateFormat ffh = new SimpleDateFormat("dd/MM/yyyy HH:mm");
 private String paquete ="ER_CONTROL_VENTOUR_PKG";
 
 @PersistenceContext(unitName="ER_WS_CONTROL_EJBPU")
 private EntityManager em;

 private String DBLINK = "@VENTOUR_LINK.ESTRELLAROJA.COM.MX";

 @Resource(name = "REP_CONTROL_DB")
    private DataSource dataSource;

    
      public getUnidadesResp getUnidades(getUnidadesReq parametros)
    {
        getUnidadesResp respuesta = new getUnidadesResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("getProductosCliente Sesion ");
        System.out.println("getProductosCliente S: "+parametros.getNombre());
        System.out.println("getProductosCliente SesionId: "+parametros.getSesionId());


        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+paquete+ ".GET_UNIDADES_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2,parametros.getNombre());

            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(4).equals("TRUE"))
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                 List<Unidad> listado = new ArrayList<Unidad>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     listado.add(new Unidad(registro));
                    System.out.println(""+listado);
                }
                 respuesta.setUnidades(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }


     public getCancelaContratoResp getCancelaContrato(getCancelaContratoReq parametros)
    {

          getCancelaContratoResp respuesta = new getCancelaContratoResp();
          respuesta.setErrorCode("ER-001");
          respuesta.setErrorMsg("La operacion no pudo ser completada");
          respuesta.setOperacionExitosa(false);


           Connection cnx=null;
           OracleCallableStatement stmt=null;
           try {
             cnx = dataSource.getConnection();
             String q1 =
                   "BEGIN "+paquete+ ".GET_CANCELA_CONTRATO_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, parametros.getContrato_ID());
            stmt.setString(3, parametros.getReferenciaContrato());
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);

            stmt.execute();




            respuesta.setOperacionExitosa(stmt.getString(4).equals("TRUE")?true:false);
            respuesta.setErrorCode(stmt.getString(5));
            respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }

    }

     public boolean getvalidaFecha(String Fecha, String Formato)
    {
         boolean fvalida= true;
          SimpleDateFormat ff = new SimpleDateFormat(Formato);
         try {

               ff.parse(Fecha);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         return fvalida;
     }


    public String ValidaContrato(Contrato contrato)
    {
       String   strValida="";


       if (contrato.getCiudadContrato() == null || contrato.getCiudadContrato().length()<=1)
          {
              strValida ="Ciudad Contrato no puede ser vacio";
              return strValida;
          }
        if (contrato.getFecha_Hora_Salida() == null)
          {
              strValida ="Fecha Hora Salida no puede ser vacio";
              if (!getvalidaFecha(contrato.getFecha_Hora_Salida(), "dd/MM/yyyy HH:mm"))
                  strValida ="Fecha Hora Salida invalida";
              return strValida;
          }

        if (contrato.getFecha_Hora_Llegada() == null)
          {
              strValida ="Fecha Hora Llegada no puede ser vacio";
              if (!getvalidaFecha(contrato.getFecha_Hora_Llegada(), "dd/MM/yyyy HH:mm"))
                  strValida ="Fecha Hora Llegada invalida";
              return strValida;
          }
        if (contrato.getViaje_Redondo() == null )
        {
            strValida = "No indicado si el viaje es redondo ";
              return strValida;
        }

        if (contrato.getViaje_Redondo() != null && contrato.getViaje_Redondo().equalsIgnoreCase("S")&& contrato.getRegreso_Fecha_Llegada() == null)
          {
              strValida ="Regreso Fecha Llegada no puede ser vacio";
              return strValida;
          }
       if (contrato.getRegreso_Fecha_Llegada() != null && !getvalidaFecha(contrato.getRegreso_Fecha_Llegada(), "dd/MM/yyyy HH:mm"))
       {
           strValida = "Regreso Fecha Llegada invalida";
           return strValida;       }

      if (contrato.getViaje_Redondo().equalsIgnoreCase("S")&& contrato.getRegreso_Fecha_Salida() == null)
          {
              strValida ="Regreso Fecha Salida no puede ser vacio";
              return strValida;
          }
       if (contrato.getRegreso_Fecha_Salida() != null && !getvalidaFecha(contrato.getRegreso_Fecha_Salida(), "dd/MM/yyyy HH:mm"))
       {
           strValida = "Regreso Fecha Salida invalida";
           return strValida;       }


       if (contrato.getLugar_Origen()== null)
          {
               strValida = "Lugar de Origen no puede ser valido";
               return strValida;
          }

       if (contrato.getLugar_Destino()== null)
          {
               strValida = "Lugar Destino no puede ser valido";
               return strValida;
          }
        if (contrato.getRefrigerio_Abordo() != null && contrato.getRefrigerio_Abordo().trim().length() > 0
            && contrato.getNO_personas_Refrigerio() <=0 )
        {
              strValida = "Indique el número de personas para el refrigerio. ";
               return strValida;
        }

        

       return strValida;
     }


       public getOperacionContratoResp getOperacionContrato(getOperacionContratoReq parametros)
    {
           double precio =0;
         int noperadores =0;

       //  SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          getOperacionContratoResp respuesta = new getOperacionContratoResp();
          respuesta.setErrorCode("ER-001");
          respuesta.setErrorMsg("La operacion no pudo ser completada");
          respuesta.setOperacionExitosa(false);
          Contrato contrato = parametros.getContrato();
          System.out.println("contrato.getFecha_Hora_Salida() "+contrato.getFecha_Hora_Salida());
          String referencia =(contrato.getCotizacion_Referencia()!= null? contrato.getCotizacion_Referencia():"");
          String  folioCotizaLote ="";

          // Validando
            if ( !parametros.getTipoOperacion().equalsIgnoreCase("C") &&
                 !parametros.getTipoOperacion().equalsIgnoreCase("R") &&
                 !parametros.getTipoOperacion().equalsIgnoreCase("A") )
          {
              respuesta.setErrorMsg("Tipo de operacion Incorrecto");
              return respuesta;
          }
            if ( !parametros.getTipoOperacion().equalsIgnoreCase("C") && contrato.getContrato_ID()<=0)
          {
              respuesta.setErrorMsg("Contrato ID no valido");
              return respuesta;
          }

          String strvalida=ValidaContrato(contrato);
          if ( strvalida.length() > 0 )
          {
             respuesta.setErrorMsg(strvalida);
              return respuesta;
          }

          // End validando

          if (parametros.getTipoOperacion().equalsIgnoreCase("C") )
          {
               folioCotizaLote = buscaFolioActualLote();// reemplazar por el select seq.nextval from dual del lote de cotizaciones
               if(folioCotizaLote.equals("0"))
                  folioCotizaLote = "1";
              String nr  = "VES"+getpref(folioCotizaLote);

            referencia=nr+calculaDV_Alg36(nr);

          }

         // OBTIENE PRECIO
   
           Vector result ;
          
           if ( !contrato.getLugar_Origen().trim().equalsIgnoreCase(contrato.getLugar_Destino()) )
               //cotizacion foranea
               result = getContizacionForanea(contrato.getTramoRuta(), contrato.getUnidad(), contrato.getFecha_Hora_Salida(),
                     contrato.getFecha_Hora_Llegada(),contrato.getViaje_Redondo());
           else//cotizacion local
           {
                result = getContizcionLocal(contrato.getTramoRuta(), contrato.getUnidad(), contrato.getFecha_Hora_Salida(),
                     contrato.getFecha_Hora_Llegada(),contrato.getViaje_Redondo());

           }

         //tringTokenizer stresult = new StringTokenizer(result,"|");
           if (result != null )

         if (result != null){

             //if(sresult.nextToken().equals("0"))  // Configuracion incorrecta de tarfias
              if(result.elementAt(0).toString().equals("0"))  // Configuracion incorrecta de tarfias
              {
                 respuesta.setErrorMsg(result.elementAt(1).toString());
                 return respuesta;
              }


               precio = Double.parseDouble(result.elementAt(1).toString());//stresult.nextToken());
               noperadores = Integer.parseInt(result.elementAt(2).toString());//stresult.nextToken());
               parametros.getContrato().setUnidad((List<Unidad>)result.elementAt(3));
               System.out.println("****    Precio Cotizado   "+precio);
             }
           double precio_lunch=0;
            if (contrato.getRefrigerio_Abordo() != null && contrato.getRefrigerio_Abordo().trim().length() > 0)
            {
                double preciolunch= getTarifaLunch(contrato.getRefrigerio_Abordo());
                if (preciolunch == 0)
                {
                    respuesta.setErrorMsg("No se puede obtener el precio dellunch");
                    return respuesta;
                }
                precio_lunch = preciolunch*contrato.getNO_personas_Refrigerio();
                  System.out.println("****     precio_lunch   "+precio_lunch);
            }
            precio=Math.round(precio+precio_lunch);  
            System.out.println("****     precio Total   "+precio);
            contrato.setImporte_Servicio(new BigDecimal(precio+""));

           // OBTIENE PRECIO


           String strContrato="|"+(contrato.getContrato_ID()!= null? contrato.getContrato_ID():"0")+
                             //"|"+(contrato.getCotizacion_Referencia()!= null? contrato.getCotizacion_Referencia():"")+
                             "|"+referencia+
                             "|"+(contrato.getContrato_Numero()!= null? contrato.getContrato_Numero():"")+
                             "|"+(contrato.getRefrigerio_Abordo()!= null? contrato.getRefrigerio_Abordo():"")+
                             "|"+contrato.getNO_personas_Refrigerio()+
                             "|"+(contrato.getViaje_Redondo().toUpperCase())+   // sigue revision
                             "|"+(contrato.getLugar_Origen()!= null? contrato.getLugar_Origen():"")+
                             "|"+(contrato.getLugar_Destino()!= null? contrato.getLugar_Destino():"")+
                           //  "|"+(contrato.getFecha_Hora_Salida() != null?ff.format(contrato.getFecha_Hora_Salida())+"":"")+
                             "|"+contrato.getFecha_Hora_Salida() +
                             "|"+(contrato.getLugar_Salida()!= null? contrato.getLugar_Salida():"")+
                            // "|"+(contrato.getFecha_Hora_Llegada() != null?ff.format(contrato.getFecha_Hora_Llegada())+"":"")+
                             "|"+contrato.getFecha_Hora_Llegada()+
                             "|"+(contrato.getLugar_Llegada()!= null? contrato.getLugar_Llegada():"")+
                             "|"+(contrato.getNombre_Cliente_Contrato()!= null? contrato.getNombre_Cliente_Contrato():"")+
                             "|"+(contrato.getDireccion_Cliente_Contrato()!= null? contrato.getDireccion_Cliente_Contrato():"")+
                             "|"+(contrato.getTelefono_Cliente_Contrato()!= null? contrato.getTelefono_Cliente_Contrato():"")+
                             "|"+(contrato.getImporte_Servicio()!= null? contrato.getImporte_Servicio():"0")+
                             "|"+(contrato.getFormaPago()!= null? contrato.getFormaPago():"")+
                             "|"+(contrato.getObservaciones()!= null? contrato.getObservaciones():"")+
                              "|"+(contrato.getCiudadContrato()!= null? contrato.getCiudadContrato():"")+
                             //"|"+(contrato.getRegreso_Fecha_Salida() != null?ff.format(contrato.getRegreso_Fecha_Salida())+"":"")+
                            "|"+contrato.getRegreso_Fecha_Salida() +
                             "|"+(contrato.getRegreso_Lugar_Salida()!= null? contrato.getRegreso_Lugar_Salida():"")+
                             "|"+(contrato.getRegreso_Ciudad_Salida()!= null? contrato.getRegreso_Ciudad_Salida():"")+
                            // "|"+(contrato.getRegreso_Fecha_Llegada() != null?ff.format(contrato.getRegreso_Fecha_Llegada())+"":"")+
                             "|"+contrato.getRegreso_Fecha_Llegada().trim()+
                             "|"+(contrato.getRegreso_Lugar_Llegada()!= null? contrato.getRegreso_Lugar_Llegada():"")+
                             "|"+(contrato.getRegreso_Ciudad_Llegada()!= null? contrato.getRegreso_Ciudad_Llegada():"")+
                             "|"+(contrato.getEstado()!= null? contrato.getEstado():"");
             System.out.println("strContrato "+strContrato);
  
            String strUnidades="";
           // long costounidad=0, costototal=0;
            
            List<Unidad> Unidadeslist=parametros.getContrato().getUnidad();
            Unidad unidad =null;
            for (int i = 0; i < Unidadeslist.size(); i++)
                    unidad =(Unidad)Unidadeslist.get(i);
                    System.out.println("Unidades "+unidad.getNombre()+"-"+unidad.getNo_unidades());
                    strUnidades=strUnidades+ "^|"+(unidad.getUnidad()!=null?unidad.getUnidad():"")+
                                              "|"+(unidad.getNombre()!=null?unidad.getNombre():"")+
                                              "|"+(unidad.getNo_unidades())+
                                              "|"+unidad.getOtros1()+
                                              "|"+Math.round(Double.parseDouble(unidad.getOtros1())*unidad.getNo_unidades())+
                                              "|"+(unidad.getNo_unidades()==0?unidad.getNo_unidades():"");

           System.out.println("strUnidades "+strUnidades);


           Connection cnx=null;
           OracleCallableStatement stmt=null;
           try {
             cnx = dataSource.getConnection();
             String q1 =
                   "BEGIN "+paquete+ ".GET_OPERACION_CONTRATO_PRC(?, ?, ?, ?, ?, ?, ?, ?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
//GET_OPERACION_CONTRATO_PRC (  P_SESION_ID IN NUMBER,  P_CLIENTE_ID IN VARCHAR2, P_PARAMETROS_CONTRATO IN VARCHAR2, P_OPERACION IN VARCHAR2,
//                               P_DATOS_SALIDA   OUT CLOB,  P_OPERACION_EXITOSA   OUT VARCHAR2, P_ERROR_CODE   OUT VARCHAR2,  P_ERROR_MSJ   OUT VARCHAR2  ) ;

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, parametros.getCliente_ID());
            stmt.setString(3, strContrato);
            stmt.setString(4, parametros.getTipoOperacion().toUpperCase());
            stmt.setString(5, strUnidades);
            stmt.setString(6, folioCotizaLote);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(9, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(10, java.sql.Types.VARCHAR);
            stmt.execute();


          String datos = stmt.getString(7);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(8).equals("TRUE"))
            {
              System.out.println("Datos "+datos);

	      //StringTokenizer tokens=new StringTokenizer(datos,"|");
              //respuesta.setContrato_ID(Long.parseLong(tokens.nextToken()));
                respuesta.setContrato_ID(Long.parseLong(datos));
                if (parametros.getTipoOperacion().equalsIgnoreCase("C"))
                    respuesta.setCotizacion_Referencia(referencia);
                else
                    respuesta.setCotizacion_Referencia("");
            //  respuesta.setImporte_Contrato(new BigDecimal( tokens.nextToken()));
	     respuesta.setImporte_Contrato(contrato.getImporte_Servicio());

            }

            respuesta.setOperacionExitosa(stmt.getString(8).equals("TRUE")?true:false);
            respuesta.setErrorCode(stmt.getString(9));
            respuesta.setErrorMsg(stmt.getString(10));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }

    }


        public String buscaFolioActualLote() {
        String qry = "select TOU_COTIZACION_LOTE_SEQ.nextval"+DBLINK+" from dual";
        try {
            Vector res=(Vector)em.createNativeQuery(qry).getSingleResult();
            return res.elementAt(0).toString();
        } catch (Exception err) {
           System.err.println("Error fechaServidor: "+err.getMessage());
            err.printStackTrace();
            return "";
        }  
     }


       


//ALGORITMO PARA OBTENER EL DIGITO VERIFICADOR
  private int calculaDV_Alg36(String nr){
  Vector<Integer> inicial= new Vector<Integer>();
  Vector<Integer> segundotmp= new Vector<Integer>();
  Vector<Integer> segundo= new Vector<Integer>();
  Vector<String> letras= new Vector<String>();
  Vector<String> numeros= new Vector<String>();

  int n = 0;
  //int A = 1, B = 2,C = 3,D = 4,E = 5,F= 6, G = 7,H = 8,I = 9
  //   ,J = 1, K = 2,L = 3,M = 4,N = 5,O= 6, P = 7,Q = 8,R = 9
  //   ,S = 1, T = 2,U = 3,V = 4,W = 5,X= 6, Y = 7,Z = 8;
  letras.add("");
  letras.add("AJS");letras.add("BKT");letras.add("CLU");
  letras.add("DMV");letras.add("ENW");letras.add("FOX");
  letras.add("GPY");letras.add("HQZ");letras.add("IR");
  numeros.add("0");numeros.add("1");numeros.add("2");numeros.add("3");numeros.add("4");numeros.add("5");numeros.add("6");numeros.add("7");numeros.add("8");numeros.add("9");
  char[] car = nr.toCharArray();
  for(int i=0; i<car.length; i++)
  {
      char c = car[i];
      int index = -1;
      for(int j=0;j<letras.size();j++)
      {
         index = letras.get(j).toString().indexOf(""+c);
         if(index!=-1)
         {
            index = j;
             break;
         }
      }
      if(index>=0)
          inicial.add(index);
      else
          inicial.add(numeros.indexOf(""+c));
  }
 System.out.println("Paso1: "+inicial);
 int div = 2;
 for(int i = inicial.size()-1;i>=0; i--){
     segundotmp.add(regresaUno(""+(((int)inicial.get(i))*div)));
     div = ((div==2)?1:2);

 }
  for(int i= segundotmp.size()-1;i>=0;i--)
      segundo.add(segundotmp.get(i));
  System.out.println("Paso2: "+segundo);
  int sum = 0;
  for(int i=0; i<segundo.size(); i++)
      sum = sum + ((int) segundo.get(i));
  System.out.println("sumaPaso2: "+sum);
  int prox = regresaProximaDecena(""+sum);
  System.out.println("Resta: "+prox +" - "+sum);
  n = prox - sum;
  return n;
  }

 private int regresaUno(String num){
    //System.out.println("llamo regresaUno con: "+num);
     int unNum = 0;
     if(num.length()==1)
         return Integer.valueOf(num);
     else
     {

         while(num.length()>1)
         {
             String n = ""+num;
             char[] cn = n.toCharArray();
             int nn = 0;
             for(int i=0; i<cn.length; i++)
              nn = nn+ Integer.valueOf(""+cn[i]);
             num = ""+nn;
           // System.out.println("  "+num);
         }
         unNum = Integer.valueOf(num);
     }
     return unNum;
 }

 private int regresaProximaDecena(String num){
    //System.out.println("Decena: "+ num);
     //int decena = 0;
     //char[] cd = num.toCharArray();
     char[] cdp = num.toCharArray();
     char numDecena = cdp[cdp.length -2];
     String s = "";
     //System.out.println("cdp[cdp.length -1] = "+cdp[cdp.length -1]);
     if((""+cdp[cdp.length -1]).equals("0") )
        s = ""+ numDecena;
     else
        s = ""+(Integer.valueOf(""+numDecena) + 1);
     cdp[cdp.length -2] = (s.toCharArray())[0];
     cdp[cdp.length -1] = '0';
     //int decenaProxima = 0;
     String dp = "";
     for(int i=0; i<cdp.length; i++)
         dp = dp +""+cdp[i];
     //System.out.println("Decena Proxima: "+ dp);
     return Integer.valueOf(dp);
 }


     private String getpref(String n) {
       String num = n;
        for(int i=num.length() ; i<6; i++)
           num = "0"+num;
        return num;
    }


     public getMunicipiosResp getMunicipios(getMunicipiosReq parametros)
    {
        getMunicipiosResp respuesta = new getMunicipiosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("getMunicipios SesionId: "+parametros.getSesionId());


        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+paquete+ ".GET_MUNICIPIOS_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2,parametros.getEstado_ID());

            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(4).equals("TRUE"))
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                 List<Municipio> listado = new ArrayList<Municipio>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     listado.add(new Municipio(registro));
                    System.out.println(""+listado);
                }
                 respuesta.setMunicipios(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }


       public getEstadosResp getEstados(getEstadosReq parametros)
    {
        getEstadosResp respuesta = new getEstadosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("getEstados SesionId: "+parametros.getSesionId());


        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+paquete+ ".GET_ESTADOS_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2,"MÉXICO");

            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(4).equals("TRUE"))
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                 List<Estado> listado = new ArrayList<Estado>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     listado.add(new Estado(registro));
                    System.out.println(""+listado);
                }
                 respuesta.setEstados(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }


    // calcula distancia de la ruta
    public Vector getContizacionForanea(List<TramoRuta> Tramos, List<Unidad> Unidades ,
                            String FechaInicio, String FechaFin, String ViajeRedondo)
    {
      Vector result =new Vector();
      double precio   =0;
      double kilometros=0, kilometrosIda=0, kilometrosRegreso=0;
       Vector VdatosUni = null;
        double factor=0, factorestancia =0, estancia=0, tarifa_minima=0;
       TramoRuta tramo;
        for (int i = 0; i < Tramos.size(); i++) {
            tramo = Tramos.get(i);
            String strkilomet =getkilometro(tramo.getOrigen(),tramo.getDestino());
            if (strkilomet != null && !strkilomet.equals("0"))
                kilometrosIda+=Double.parseDouble(strkilomet  );
            else
            {
                result.addElement("0");
                result.addElement("Tramo no valida "+tramo.getOrigen()+'-'+tramo.getDestino());

                return result;
            }
           // System.out.println("Kilometros "+kilometrosIda);
        }
       kilometros=kilometrosIda;
       System.out.println("Kilometros  Ida "+kilometrosIda);
       if (ViajeRedondo.equalsIgnoreCase("S")) {
           int j= Tramos.size();
           String strkilomet;
           for (int i = 0; i < Tramos.size(); i++) {
                tramo = Tramos.get(j-(i+1));
                strkilomet=getkilometro(tramo.getDestino(),tramo.getOrigen());
                 if (strkilomet != null && !strkilomet.equals("0"))
                      kilometrosRegreso+=Double.parseDouble(strkilomet  );
                 else{
                result.addElement("0");
                result.addElement("Tramo no valida "+tramo.getOrigen()+'-'+tramo.getDestino());
               }
                //System.out.println("Kilometros "+kilometrosRegreso);
            }
           System.out.println("Kilometros Regreso "+kilometrosRegreso);
           kilometros+=kilometrosRegreso;
        }
        System.out.println("Kilometros Total  "+kilometros);


         // Optiene el precio por kilometro

        int operadores=0;
        for (int i = 0; i < Unidades.size(); i++) {
              VdatosUni = getTarifasUnidad(Unidades.get(i).getNombre());
             System.out.println("VdatosUni  "+VdatosUni);
              if (VdatosUni == null)
                  {
                   result.addElement("0");
                   result.addElement("Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados.");
                   // result = "0|Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados.|";
                    return result;
                 }
              factor=Double.parseDouble(VdatosUni.elementAt(0).toString());

              factorestancia=Double.parseDouble(VdatosUni.elementAt(1).toString());

              tarifa_minima=Double.parseDouble(VdatosUni.elementAt(2).toString());

             //  factor=getFactor(Unidades.get(i).getNombre());
               //Calcula el precio;
               if (kilometros <250)
               {
                   precio = tarifa_minima* Unidades.get(i).getNo_unidades();
                   Unidades.get(i).setOtros1(Math.round(precio)+"");
               }
               else
               {
                   precio += (kilometros * factor) * Unidades.get(i).getNo_unidades();
                   Unidades.get(i).setOtros1(Math.round(kilometros * factor)+"" );
              }
               
               // Calcula los operadores
               if (kilometros >=1000)
                   operadores += 2* Unidades.get(i).getNo_unidades();
               else
                   operadores +=  Unidades.get(i).getNo_unidades();
        }   // for (int i = 0; i < Unidades.size(); i++) {


         System.out.println("precio por kilometros "+precio);

         SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//         SimpleDateFormat sfh = new SimpleDateFormat("HH");
         long diffDays=1;
         try {
                Date fecha_ini= sf.parse(FechaInicio);
                Date fecha_fin= sf.parse(FechaFin);
                 System.out.println("FechaInicio "+FechaInicio);
                 System.out.println("FechaFin "+FechaFin);
                long diff = fecha_fin.getTime() - fecha_ini.getTime();

                // Calcula Diferencias en dias y horas

                long diffHours = diff / (60 * 60 * 1000);
                // calcular la diferencia en dias

                 diffDays =( diff / (24 * 60 * 60 * 1000))+1;
                 System.out.println("diffDays "+diffDays);

         // Calcula  ESTANCIAS

            if (diffDays >1)
            {
               int estanciadias = (int)diffDays;
               // String HoraIni= sfh.parse(FechaInicio).toString();
               // String HoraFin= sfh.parse(FechaFin).toString();

                String HoraIni= FechaInicio.substring(FechaInicio.indexOf(" ")+1, FechaInicio.indexOf(" ")+3);
                String HoraFin= FechaFin.substring(FechaFin.indexOf(" ")+1, FechaFin.indexOf(" ")+3);

                System.out.println("HoraIni "+HoraIni);
                System.out.println("HoraFin "+HoraFin);

                if ( Integer.parseInt(HoraIni) >19 ||
                     Integer.parseInt(HoraFin) <23.5 ||
                     kilometros >= 1500)
                              estanciadias = estanciadias-1;

                estancia = estanciadias*factorestancia;  //  Verifiacar el factor estancia

                System.out.println("No de dias de estancia a cobrar "+estanciadias);
                System.out.println("Estancia precio "+estancia);

            }
          } catch (Exception e) {
            e.printStackTrace();
             result.addElement("0");
              result.addElement("Error en fechas.");

            return result;
        }

         precio = precio+estancia;
         System.out.println("precio Total por Kilometro  "+precio);

          result.addElement("1");
         result.addElement(precio);
         result.addElement(operadores);
         result.addElement(Unidades);
         //result = "1|"+precio+"|"+operadores|Unidades;  // Operacion Exitosa

      return result;
    }


      // Cotizacion Local
    public Vector getContizcionLocal(List<TramoRuta> Tramos, List<Unidad> Unidades ,
                            String FechaInicio, String FechaFin, String ViajeRedondo)
    {

       SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Vector result =new Vector();
        int operadores =1;
        double precio =0;
        long diffDays=1;
        String strHora ="";
        Vector VdatosUni;
         try {
                Date fecha_ini= sf.parse(FechaInicio);
                Date fecha_fin= sf.parse(FechaFin);
                System.out.println("FechaInicio "+FechaInicio);
                System.out.println("FechaFin "+FechaFin);
                long diff = fecha_fin.getTime() - fecha_ini.getTime();

                // Calcula Diferencias en dias y horas

                long diffHours = diff / (60 * 60 * 1000);
                System.out.println("diffHours "+diffHours);
                if (diffHours <=2)
                    strHora="TARIFA_MIN_2H";
                else if (diffHours >2 && diffHours <=4 )
                    strHora="TARIFA_MIN_4H";
                else if (diffHours >4 && diffHours <=6 )
                    strHora="TARIFA_MIN_6H";
                else if (diffHours >6 && diffHours <=8 )
                    strHora="TARIFA_MIN_8H";
                else if (diffHours >8 && diffHours <=10 )
                    strHora="TARIFA_MIN_10H";
                else if (diffHours >10 && diffHours <=12 )
                    strHora="TARIFA_MIN_12H";
                else
                {
                    result.addElement("0");
                    result.addElement("Los datos de la flotilla no estan configurados, para el no de horas solicitado.");
                    //result = "0|Los datos de la flotilla no estan configurados, para el no de horas solicitado.|";
                    return result;
                }

            for (int i = 0; i < Unidades.size(); i++) {
              VdatosUni = getTarifasLocalUnidad(Unidades.get(i).getNombre(),strHora);
              System.out.println("VdatosUni  "+VdatosUni);
              if (VdatosUni == null)
                  {
                  result.addElement("0");
                  result.addElement("Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados, para el no de horas solicitado.");
                    //result = "0|Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados, para el no de horas solicitado.|";
                    return result;
                 }

               precio=precio+Double.parseDouble(VdatosUni.elementAt(0).toString());
               Unidades.get(i).setOtros1(Math.round(precio)+"");
             } // for Unidades
        }catch(Exception e)
         {
            result.addElement("0");
            result.addElement("Verifique los datos de su contratos Fecha Inicio , Fecha Final.");
              //result = "0|Verifique los datos de su contratos Fecha Inicio , Fecha Final.|";
            e.printStackTrace();
            return result;
         }
        precio=Math.round(precio);
         result.addElement("1");
         result.addElement(precio);
         result.addElement(operadores);
         result.addElement(Unidades);
        //result = "1|"+precio+"|"+operadores;  // Operacion Exitosa

      return result;
    }

    /* private int getFactor  (String  Unidad)
    {
        int Factor =0;
         System.out.println("Unidad "+Unidad);
        if( Unidad.equalsIgnoreCase("EJECTUTIVO"))
             Factor=18;
        else if( Unidad.equalsIgnoreCase("PB"))
             Factor=16;
         else if( Unidad.equalsIgnoreCase("IRIZAR"))
             Factor=15;
         else if( Unidad.equalsIgnoreCase("SPRINTER"))
             Factor=8;
          System.out.println("Factor "+Factor);
        return Factor;

    }*/

 private Vector getTarifasUnidad  (String  Unidad)
    {
        Vector result = null;
        try {


        String query = "select NVL(c.factor_kilometro,0), NVL(c.estancia,0), nvl(c.tarifa_minima,0) "+
                       " from tou_config_flotilla_tbl"+DBLINK+" c  "+
                       " where c.nombre='"+Unidad+"'";
        System.out.println("getTarifasUnidad  : " +  query);
        result= (Vector)((Vector)em.createNativeQuery(query).getResultList()).elementAt(0);
        System.out.println("getTarifasUnidad  : " +  result);
        } catch (Exception e) {
            result=null;
        }
        return result;

    }

private Vector getTarifasLocalUnidad  (String  Unidad, String strTARIF)
    {
        Vector result = null;
        try {


        String query = "select NVL("+strTARIF+",0)"+
                       " from tou_config_flotilla_tbl"+DBLINK+" c  "+
                       " where nombre='"+Unidad+"'";
        System.out.println("getTarifasUnidad  : " +  query);
        result= (Vector)((Vector)em.createNativeQuery(query).getResultList()).elementAt(0);
        System.out.println("getTarifasUnidad  : " +  result);
        } catch (Exception e) {
            result=null;
        }
        return result;

    }


private double getTarifaLunch  (String  lunch)
    {
        double result = 0;
        try {

       Vector vresul;
        String query = " select factor_kilometro"+
                       " from "+
                       " tou_config_flotilla_tbl "+DBLINK+
                       " where linea1 ='"+lunch+"'  "+
                       " union  "+
                       " select tarifa_minima  "+
                       " from  "+
                       " tou_config_flotilla_tbl  "+DBLINK+
                       " where linea2 ='"+lunch+"'  ";

        System.out.println("getTarifaLunch  : " +  query);
        vresul= (Vector)((Vector)em.createNativeQuery(query).getResultList()).elementAt(0);
        System.out.println("getTarifaLunch  vresul: " +  vresul);
        if (vresul != null)
            result= Double.parseDouble(vresul.elementAt(0).toString());

        System.out.println("getTarifaLunch  : " +  result);
        } catch (Exception e) {
            result=0;
        }
        return result;

    }

     // obtiene kilometros entre dos puntos con el api de google map
public   String   getkilometro(String Origen,String Destino)
    {
      String distancia ="";
     Origen=Origen.replaceAll(" ", "&nbsp") ;
     Destino=Destino.replaceAll(" ", "&nbsp") ;
    /*    String Origen ="Mexico,Mexico";
     String Destino="Puebla,Mexico";*/
     //String strurl="http://maps.googleapis.com/maps/api/distancematrix/xml?origins=Mexico+DF|Tlaxcala&destinations=Puebla&language=es&sensor=false";

     System.out.println("ORIGEN : " +  Origen);
     System.out.println("DESTINO : " +  Destino);
     String strurl="http://maps.googleapis.com/maps/api/distancematrix/xml?origins="+Origen.toUpperCase()+"&destinations="+Destino.toUpperCase()+
                   "&mode=driving&language=es&sensor=false";  
     System.out.println("url: " +  strurl);
     try {
      URL url = new URL(strurl);
      URLConnection con = url.openConnection();
      InputStream inputstream  = con.getInputStream();

      Document doc= readXml(inputstream);

      NodeList nodes = doc.getElementsByTagName("distance");
      //    System.out.println("Node hijos: " +  nodes.item(0).getNodeName());
       if (nodes!= null){

           nodes=nodes.item(0).getChildNodes();

          // System.out.println("Node Padre" +  nodes.getLength());
           if (nodes!= null){

              for(int i = 0 ; i<nodes.getLength() ; i++) {
                 // if(nodes.item(i).getNodeName().equalsIgnoreCase("text") && nodes.item(i).getTextContent()!= null && nodes.item(i).getTextContent().length()>0)
                   if(nodes.item(i).getNodeName().equalsIgnoreCase("value") && nodes.item(i).getTextContent()!= null && nodes.item(i).getTextContent().length()>0)
                  {
                     // distancia = nodes.item(i).getTextContent().replace("km", "").trim();
                     //distancia=distancia.replace("m", "").trim()
                       distancia = nodes.item(i).getTextContent();
                       distancia= Math.round(Double.parseDouble(distancia)/1000)+"";
                      System.out.println("Distancia que regresa el api "+"---"+distancia+"--");
                  }
                }
             }
         }

} catch (Exception e) {
    e.printStackTrace();
    return null;
        }
     return distancia;
    }

public Document readXml(InputStream is)throws SAXException, IOException
   {
     DocumentBuilder db = null;
     try {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          dbf.setValidating(false);
          dbf.setIgnoringComments(false);
          dbf.setIgnoringElementContentWhitespace(true);
          dbf.setNamespaceAware(true);
          // dbf.setCoalescing(true);
          // dbf.setExpandEntityReferences(true);


          db = dbf.newDocumentBuilder();
          db.setEntityResolver(new NullResolver());

          // db.setErrorHandler( new MyErrorHandler());
      } catch (Exception e) {
             }
          return db.parse(is);
      }
}

class NullResolver implements EntityResolver {
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
      IOException {
    return new InputSource(new StringReader(""));
  }

}
