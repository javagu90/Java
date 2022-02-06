/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.solicitudes;

import WS_CONTROL.Ventour.entidades.Contrato;
import WS_CONTROL.Ventour.entidades.CotizacionD;
import WS_CONTROL.Ventour.entidades.Estado;
import WS_CONTROL.Ventour.entidades.Municipio;
import WS_CONTROL.Ventour.entidades.Producto;
import WS_CONTROL.Ventour.entidades.ProductoCotiza;
import WS_CONTROL.Ventour.entidades.SalidaCotiza;
import WS_CONTROL.Ventour.entidades.TarifaFija;
import WS_CONTROL.Ventour.entidades.TramoRuta;
import WS_CONTROL.Ventour.entidades.Unidad;
import WS_CONTROLVentour.getCancelaContratoReq;
import WS_CONTROLVentour.getCancelaContratoResp;
import WS_CONTROLVentour.getEstadosReq;
import WS_CONTROLVentour.getEstadosResp;
import WS_CONTROLVentour.getKilometrosReq;
import WS_CONTROLVentour.getKilometrosResp;
import WS_CONTROLVentour.getMunicipiosReq;
import WS_CONTROLVentour.getMunicipiosResp;
import WS_CONTROLVentour.getOperacionContratoReq;
import WS_CONTROLVentour.getOperacionContratoResp;
import WS_CONTROLVentour.getUnidadesReq;
import WS_CONTROLVentour.getUnidadesResp;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
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
import java.util.logging.Level;
import java.util.logging.Logger;


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
import java.io.StringReader;
import java.math.BigInteger;



import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class wsVentourFacadeBean implements wsVentourFacadeBeanRemote {
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

            if ( !parametros.getTipoOperacion().equalsIgnoreCase("C") && (contrato.getContrato_ID() == null|| contrato.getContrato_ID()<=0) )
          {
              respuesta.setErrorMsg("Contrato ID no valido");
              return respuesta;
          }

          if ( (parametros.getTipoOperacion().equalsIgnoreCase("R") ||
                 parametros.getTipoOperacion().equalsIgnoreCase("A") )
                 && (parametros.getContrato().getContrato_Numero() == null ||
                     parametros.getContrato().getContrato_Numero().length() <2)  )
          {
              respuesta.setErrorMsg("Contrato número no valido");
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
                     contrato.getFecha_Hora_Llegada(),contrato.getViaje_Redondo(), contrato.getTotal_kmts());
           else//cotizacion local
           {
                result = getContizcionLocal(contrato.getTramoRuta(), contrato.getUnidad(), contrato.getFecha_Hora_Salida(),
                     contrato.getFecha_Hora_Llegada(),contrato.getViaje_Redondo());
  
           }
      
         //tringTokenizer stresult = new StringTokenizer(result,"|");
       //    if (result != null )

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
          /* double precio_lunch=0;
            if (contrato.getRefrigerio_Abordo() != null && contrato.getRefrigerio_Abordo().trim().length() > 0)
            {
                double preciolunch= getTarifaLunch(contrato.getRefrigerio_Abordo());
                if (preciolunch == 0)
                {
                    respuesta.setErrorMsg("No se puede obtener el precio del lunch");
                    return respuesta;
                }
                precio_lunch = preciolunch*contrato.getNO_personas_Refrigerio();
                  System.out.println("****     precio_lunch   "+precio_lunch);
            }
            precio=Math.round(precio+precio_lunch);  */
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
    public getKilometrosResp getkilometros(getKilometrosReq parametros)
    {
       double kilometros=0, kilometro=0;
       Vector VdatosUni = null;
       StringTokenizer sf = null;
       String tramousado="";
       getKilometrosResp resp = new getKilometrosResp();

       List<TramoRuta> Tramos= parametros.getTramosRutas();
       TramoRuta tramo;
       try{
        for (int i = 0; i < Tramos.size(); i++) {
            tramo = Tramos.get(i);
            String strkilomet =getkilometro(tramo.getOrigen(),tramo.getDestino());

            if (strkilomet != null && !strkilomet.equals("0"))
            {
                sf = new StringTokenizer(strkilomet,"|");
                kilometro =Double.parseDouble(sf.nextToken());
                kilometros+=kilometro;

            }
            else
            {
              kilometro=0;
            }
            tramo.setKilometros(kilometro);
           // System.out.println("Kilometros "+kilometrosIda);

           // Tramos.add(tramo);
        }

          } catch (Exception e) {
            e.printStackTrace();
            resp.setErrorCode("0");
            resp.setErrorMsg("Error al obtener los kilometros");

            return null;
        }

      resp.setErrorCode("1");
      resp.setTramosRuta(Tramos);
      resp.setTramosRuta(Tramos);
      resp.setTotalKmts(kilometros);  
      return resp;
    }




  public Vector getPrecioCotizacion(Contrato contrato, String TipoCotizacion)
    {   
     Vector result= null;
     try {

 
        List<TramoRuta> tramos    = contrato.getTramoRuta();
        List<Unidad>    unidades  = contrato.getUnidad();
        
        System.out.println("getPrecioCotizacion  Salida "+contrato.getFecha_Hora_Salida());
        System.out.println("getPrecioCotizacion  llegada "+contrato.getFecha_Hora_Llegada());
        System.out.println("getPrecioCotizacion  Origen "+contrato.getLugar_Origen());
        System.out.println("getPrecioCotizacion  Dstino "+contrato.getLugar_Destino());
        System.out.println("getPrecioCotizacion  Refrrigerios "+contrato.getRefrigerio_Abordo());
        System.out.println("getPrecioCotizacion  ViajeRedondo "+contrato.getViaje_Redondo());


        //if ( !contrato.getLugar_Origen().trim().equalsIgnoreCase(contrato.getLugar_Destino()) )
        if (TipoCotizacion.equalsIgnoreCase("F") )
                   //cotizacion foranea
                   result = getContizacionForanea(tramos, unidades, contrato.getFecha_Hora_Salida(),
                         contrato.getFecha_Hora_Llegada(),contrato.getViaje_Redondo(),contrato.getTotal_kmts());
        else//cotizacion local
  
                    result = getContizcionLocal(tramos, unidades, contrato.getFecha_Hora_Salida(),
                         contrato.getFecha_Hora_Llegada(),contrato.getViaje_Redondo());
         System.out.println("getPrecioCotizacion result : "+result );
        return result;
        } catch (Exception e) {
          return null;
        }
    }



    // calcula distancia de la ruta
    public Vector getContizacionForanea(List<TramoRuta> Tramos, List<Unidad> Unidades ,
                            String FechaInicio, String FechaFin, String ViajeRedondo, double total_Kmts)
    {
      Vector result =new Vector();
      double precio   =0, precioconductores=0;
      double kilometros=0, kilometrosIda=0, kilometrosRegreso=0;
       Vector VdatosUni = null;
       StringTokenizer sf = null;
       String tramousado="";
       double factor=0, factorestancia =0, estanciaunidad=0, tarifa_minima=0;
       TramoRuta tramo;

       if (total_Kmts <=0) {  // Busca los kilometros por tramo
        for (int i = 0; i < Tramos.size(); i++) {
            tramo = Tramos.get(i);
            String strkilomet =getkilometro(tramo.getOrigen(),tramo.getDestino());

            if (strkilomet != null && !strkilomet.equals("0"))
            {
                sf = new StringTokenizer(strkilomet,"|");

                //kilometrosIda+=Double.parseDouble(strkilomet  );
                kilometros+=Double.parseDouble(sf.nextToken());
                tramousado+=(tramousado.length() > 0 ? " | ":"")+sf.nextToken();
            }   
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

       // Obtiene lso kilometros de regrerso
       if (ViajeRedondo.equalsIgnoreCase("S")) {
           int j= Tramos.size();
           String strkilomet;
           for (int i = 0; i < Tramos.size(); i++) {
                tramo = Tramos.get(j-(i+1));
                strkilomet=getkilometro(tramo.getDestino(),tramo.getOrigen());
                 if (strkilomet != null && !strkilomet.equals("0"))
                  {
                     //kilometrosRegreso += Double.parseDouble(strkilomet);
                      sf = new StringTokenizer(strkilomet,"|");
                
                    kilometros+=Double.parseDouble(sf.nextToken());  
                    tramousado+=(tramousado.length() > 0 ? " | ":"")+sf.nextToken();
                   }
                 else{
                result.addElement("0");
                result.addElement("Tramo no valida "+tramo.getOrigen()+'-'+tramo.getDestino());
                return result;
               }
                //System.out.println("Kilometros "+kilometrosRegreso);
            }
           System.out.println("Kilometros Regreso "+kilometrosRegreso);
           kilometros+=kilometrosRegreso;
        }
       }
      else
        kilometros=  total_Kmts;

        System.out.println("***   Kilometros Total del vaije "+kilometros);


         // Optiene el precio por kilometro

        int operadores=0, operador_unidad=0;;
        double preciounidad=0,  preciounidades=0 ,estancias=0;
        double estanciaTotal=0, precioConduTotaL=0, precioLunchTotal=0;
        precio=0;
        System.out.println("No de unidades  "+Unidades.size());
        for (int i = 0; i < Unidades.size(); i++) {
             VdatosUni = getTarifasUnidad(Unidades.get(i).getNombre());
             
             preciounidad=0;

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
                System.out.println("No de  Unidades  "+Unidades.get(i).getNo_unidades());
               if (kilometros <250)
               {
                    preciounidad = tarifa_minima;
                     System.out.println("Tarifa Minima   "+tarifa_minima);
                   //Unidades.get(i).setOtros1(Math.round(precio)+"");
               }
               else
               {
                   
                   preciounidad = kilometros * factor;
                   System.out.println("Factor kilometro   "+factor);
                   //Unidades.get(i).setOtros1(Math.round(kilometros * factor)+"" );
              }
                System.out.println("Precio por kilometro Unidad  "+preciounidad);

             //   CALULA LA ESTANCIA;

         SimpleDateFormat sff = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//         SimpleDateFormat sfh = new SimpleDateFormat("HH");
         double diffDays=1;

         try {
                Date fecha_ini= sff.parse(FechaInicio);
                Date fecha_fin= sff.parse(FechaFin);
                System.out.println("FechaInicio "+FechaInicio);
                System.out.println("FechaFin "+FechaFin);
                double diff = (double)(fecha_fin.getTime() - fecha_ini.getTime());

                // Calcula Diferencias en dias y horas

                double diffHours =Math.ceil(diff / (60 * 60 * 1000));
                // calcular la diferencia en dias
                 SimpleDateFormat sf1 = new SimpleDateFormat("dd/MM/yyyy");
                 fecha_ini= sf1.parse(FechaInicio);
                 fecha_fin= sf1.parse(FechaFin);
                 diffDays =   Math.ceil( diff / (24 * 60 * 60 * 1000))   +1;
                 System.out.println("diffDays "+diffDays);

                 // Calcula  ESTANCIAS
                    estanciaunidad =0;
                    if (diffDays >1)
                    {
                       int estanciadias = (int)diffDays;
                       // String HoraIni= sfh.parse(FechaInicio).toString();
                       // String HoraFin= sfh.parse(FechaFin).toString();

                        String HoraIni= FechaInicio.substring(FechaInicio.indexOf(" ")+1, FechaInicio.indexOf(" ")+3);
                        String HoraFin= FechaFin.substring(FechaFin.indexOf(" ")+1, FechaFin.indexOf(" ")+3);

                        System.out.println("HoraIni "+HoraIni);
                        System.out.println("HoraFin "+HoraFin);

                        if (kilometros >= 1500 && diffDays > 2 && ViajeRedondo.equalsIgnoreCase("N"))
                             estanciadias = estanciadias - 2;
                        else
                        {
                         if (diffDays >= 2 && Integer.parseInt(HoraIni) > 19  &&
                                          Integer.parseInt(HoraFin) <=23.3   )
                           {
                               estanciadias = estanciadias - 2;
                                 System.out.println("No de dias de estancia a cobrar -2"+estanciadias);
                            }
                            else{
                                 if(Integer.parseInt(HoraIni) > 19  )
                                   {
                                    estanciadias = estanciadias - 1;
                                    System.out.println("No de dias de estancia a cobrar Hora INICIO"+estanciadias);
                                 }
                                 if (  Integer.parseInt(HoraFin) <=23.3   ){
                                       estanciadias = estanciadias - 1;
                                      System.out.println("No de dias de estancia a cobrar hORA fIN "+estanciadias);
                                  }
                           }
                        }
                        System.out.println("No de dias de estancia a cobrar "+estanciadias);
                        System.out.println("factorestancia "+factorestancia);
                        estanciaunidad = estanciadias*factorestancia ;  //  Verifiacar el factor estancia
                        estanciaTotal +=estanciaunidad;
                    }

                       System.out.println("Estancia precio "+estanciaunidad);
                  } catch (Exception e) {
                    e.printStackTrace();
                      result.addElement("0");
                      result.addElement("Error en fechas.");

                    return result;
                }

                  // precio conducto
             double precioconductor=0;

               // Calcula los operadores
               if (kilometros >=1000)
               {
                   operador_unidad = 2 ;
                   // incrementa el monto
                   VdatosUni = getTarifasUnidad(Unidades.get(i).getNombre());
                   if (VdatosUni == null)
                      {
                       result.addElement("0");
                       result.addElement("El precio del conductor extra No estan configurado.");
                       // result = "0|Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados.|";
                        return result;
                     }

                     double tarifa_conductor=Double.parseDouble(VdatosUni.elementAt(2).toString());
                     System.out.println("diffDays "+diffDays);
                     precioconductor= tarifa_conductor*diffDays ;  
                     precioConduTotaL+=tarifa_conductor*diffDays;
                     System.out.println("precio conductor tarifa_conductor extra "+tarifa_conductor);
                     System.out.println("precioconductor extras "+precioconductor);


               }
               else
                   operador_unidad =1;

               double importerefigerio=get_costorefrigerio(Unidades.get(i).getRefrigerio(), Unidades.get(i).getNo_refrigerios());
               precioLunchTotal+=importerefigerio;
               System.out.println("precio Importe Refrigerio "+importerefigerio);

               preciounidad = Math.round(preciounidad+estanciaunidad+precioconductor+importerefigerio);

               System.out.println("precio Total unidad  "+preciounidad);
                Unidades.get(i).setOtros1(preciounidad+"" );
                Unidades.get(i).setOtros2(Math.round(operador_unidad)+"" );

                precio+=preciounidad * Unidades.get(i).getNo_unidades();
                operadores += operador_unidad* Unidades.get(i).getNo_unidades();
        }   // for (int i = 0; i < Unidades.size(); i++) {
        System.out.println("precio Total por Kilometro  "+precio);
        System.out.println("Total de Operadores  "+operadores);

        precio= Math.round(precio);
         result.addElement("1");
         result.addElement(precio);
         result.addElement(operadores);
         result.addElement(Unidades);
         result.addElement(tramousado);
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
                double diff = fecha_fin.getTime() - fecha_ini.getTime();

                // Calcula Diferencias en dias y horas
   
                double diffHours = Math.ceil( diff / (60 * 60 * 1000));
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
              //  else if (diffHours >10 && diffHours <=12 )
                  else if (diffHours >10  )
                    strHora="TARIFA_MIN_12H";
                else
                {
                    result.addElement("0");
                    result.addElement("Los datos de la flotilla no estan configurados, para el no de horas solicitado.");
                    //result = "0|Los datos de la flotilla no estan configurados, para el no de horas solicitado.|";
                    return result;
                }
            double preciounidad=0;
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
           
               double importerefigerio=get_costorefrigerio(Unidades.get(i).getRefrigerio(), Unidades.get(i).getNo_refrigerios());
           
               preciounidad=Double.parseDouble(VdatosUni.elementAt(0).toString())+importerefigerio;
               precio=precio+ (preciounidad*Unidades.get(i).getNo_unidades());
               Unidades.get(i).setOtros1(Math.round(preciounidad)+"");
               Unidades.get(i).setOtros2(operadores+"");
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

 private double get_costorefrigerio(String tipoRefrigerio, int no_personas)
 {
     double importe=0;
      if ( tipoRefrigerio == null || tipoRefrigerio.length()<=0  || no_personas <=0 )
         {
                    return 0;
         }
       System.out.println("tipoRefrigerio "+tipoRefrigerio);
       String cade = tipoRefrigerio.substring(tipoRefrigerio.indexOf("$")+2, tipoRefrigerio.length());
       System.out.println("cade "+cade);
       System.out.println("cade "+cade);
       cade=cade.replaceAll("ºº", "00");
        if (cade.indexOf(" ")> 0)
            cade=cade.substring(0,tipoRefrigerio.indexOf(" "));
       
       cade=cade.replaceAll(" ","");
       System.out.println("cade "+cade);

       double precio=Double.parseDouble(cade);
       importe = precio*no_personas;
        System.out.println("importe "+importe);

     return importe;
 }


 private Vector getTarifasUnidad  (String  Unidad)
    {
        Vector result = null;
        try {

         System.out.println("datos de la unidad "+Unidad);
        String query = "select NVL(c.factor_kilometro,0), NVL(c.estancia,0), nvl(c.tarifa_minima,0) "+
                      " ,PRECIO_CONDUCTOR  "+
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


        String query = "select NVL("+strTARIF+",0) "+
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

private String eliminacento(String cade)
{
    String resultado=cade;
    resultado = resultado.replace('á','a');
    resultado = resultado.replace('Á','A');
    resultado = resultado.replace('é','e');
    resultado = resultado.replace('É','E');
    resultado = resultado.replace('í','i');
    resultado = resultado.replace('Í','I');
    resultado = resultado.replace('ó','o');
    resultado = resultado.replace('Ó','o');
    resultado = resultado.replace('ú','u');
    resultado = resultado.replace('Ú','u');
  return resultado;
}

     // obtiene kilometros entre dos puntos con el api de google map
public   String    getkilometro(String Origen,String Destino)
    {
      String distancia ="";
      double distanciatotal =0;
      String ruta_utilizada="";

     Origen= eliminacento(Origen);  
     Destino= eliminacento(Destino);

    // Origen=Origen.replaceAll(" ", "&nbsp") ;
     //Destino=Destino.replaceAll(" ", "&nbsp") ;
     Origen=Origen.replaceAll(" ", "+") ;
     Destino=Destino.replaceAll(" ", "+") ;
    /*    String Origen ="Mexico,Mexico";
     String Destino="Puebla,Mexico";*/
     //String strurl="http://maps.googleapis.com/maps/api/distancematrix/xml?origins=Mexico+DF|Tlaxcala&destinations=Puebla&language=es&sensor=false";

     System.out.println("ORIGEN : " +  Origen);
     System.out.println("DESTINO : " +  Destino);

     String strurl="http://maps.googleapis.com/maps/api/distancematrix/xml?origins="+Origen.toUpperCase()+"&destinations="+Destino.toUpperCase()+
                   "&mode=driving&language=es&sensor=false";
     System.out.println(" Obtiene kilometros url: " +  strurl);
     try {

          URL url = new URL(strurl);
          URLConnection con = url.openConnection();
          InputStream inputstream  = con.getInputStream();

          Document doc= readXml(inputstream);

          NodeList nodes = doc.getElementsByTagName("distance");

          NodeList nl1 = doc.getElementsByTagName("origin_address");
         
           if (nodes!= null){
                  nodes=nodes.item(0).getChildNodes();
              
                  for(int i = 0 ; i<nodes.getLength() ; i++) {
                      System.out.println("Node Value: " +  nodes.item(i).getNodeName());
                      String valor =nodes.item(i).getTextContent().trim();
                    
                      // if(nodes.item(i).getNodeName().equalsIgnoreCase("text") && nodes.item(i).getTextContent()!= null && nodes.item(i).getTextContent().length()>0)

                      

                       if(nodes.item(i).getNodeName().equalsIgnoreCase("value"))// si va && nodes.item(i).getTextContent()!= null && nodes.item(i).getTextContent().length()>0)
                      {
                         // distancia = nodes.item(i).getTextContent().replace("km", "").trim();
                         //distancia=distancia.replace("m", "").trim()
                           distancia = nodes.item(i).getTextContent();
                           System.out.println("Distancia que regresa el api "+"---"+distancia+"--");
                           distanciatotal+= Math.round(Double.parseDouble(distancia)/1000);
                           System.out.println("Distancia que regresa el api "+"---"+distancia+"--");

                       }

                    }// for

               // Para verificar que tramo se uso para hacer la busqueda
              String origen="";
              String destino="";
             
               for(int i = 0 ; i<doc.getChildNodes().getLength() ; i++)
               {
                   origen=""; destino="";
                   doc.getChildNodes().item(i);
                   // System.out.println(" eLEMENTO "+doc.getChildNodes().item(i).getNodeName());
                   NodeList nodos =doc.getChildNodes().item(i).getChildNodes();
                    for(int j = 0 ; j<nodos.getLength() ; j++) {
                         //System.out.println(" NODO "+nodos.item(j).getNodeName()+" Valor "+nodos.item(j).getTextContent());
                         //System.out.println(" NODO "+nodos.item(j).getNodeName()+" Valor "+nodos.item(j).getPrefix());
                         if (nodos.item(j).getNodeName().equals("origin_address") )
                             origen=nodos.item(j).getTextContent();
                         if (nodos.item(j).getNodeName().equals("destination_address") )
                             destino=nodos.item(j).getTextContent();
                         if (origen.length() > 0 && destino.length() > 0)
                             ruta_utilizada=origen +"  -  "+destino+ " ; ";
                   }
               }
              if (ruta_utilizada.length() > 0)
                  ruta_utilizada=ruta_utilizada.substring(0, ruta_utilizada.length() - 2);

              System.out.println("SE USO TRAMO: "+ruta_utilizada);

             }
           else
               return null;

        } catch (Exception e) {
                    e.printStackTrace();
                    return null;  }
  System.out.println("distanciatotal "+distanciatotal);
     //return distanciatotal+"|"+ruta_utilizada;
     return distanciatotal+"";
   }



public double getkilometros(List<TramoRuta> Tramos)
    {
       double kilometros=0, kilometro=0;
       Vector VdatosUni = null;
       StringTokenizer sf = null;
       String tramousado="";
       getKilometrosResp resp = new getKilometrosResp();

        TramoRuta tramo;
       try{
        for (int i = 0; i < Tramos.size(); i++) {
            tramo = Tramos.get(i);
            String strkilomet =getkilometro(tramo.getOrigen(),tramo.getDestino());

            if (strkilomet != null && !strkilomet.equals("0"))
            {
                sf = new StringTokenizer(strkilomet,"|");
                kilometro =Double.parseDouble(sf.nextToken());
                kilometros+=kilometro;

            }
            else
            {
              kilometro=0;
            }
            tramo.setKilometros(kilometro);
           // System.out.println("Kilometros "+kilometrosIda);

           // Tramos.add(tramo);
        }

          } catch (Exception e) {
            e.printStackTrace();
            resp.setErrorCode("0");
            resp.setErrorMsg("Error al obtener los kilometros");

            return 0;
        }


      return kilometros;
    }



  // Cotizacion Local
    public Vector get_ContizcionLocal(List<Unidad> Unidades ,
                            String FechaInicio, String FechaFin)
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
                double diff =(double)(fecha_fin.getTime() - fecha_ini.getTime());
                System.out.println("diff "+diff);
               // diff =round( (double)diff,2);
                //System.out.println("diff "+diff);
                // Calcula Diferencias en dias y horas

                double diffHours = Math.ceil(diff / (60 * 60 * 1000));
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
              // else if (diffHours >10 && diffHours <=12 )
                 else if (diffHours >10  )
                    strHora="TARIFA_MIN_12H";

                else
                {
                    result.addElement("0");
                    result.addElement("Los datos de la flotilla no estan configurados, para el no de horas solicitado.");
                    //result = "0|Los datos de la flotilla no estan configurados, para el no de horas solicitado.|";
                    return result;
                }
            double preciounidad=0, precio_flotilla=0;

            for (int i = 0; i < Unidades.size(); i++) {
                 preciounidad=0; precio_flotilla=0;
                 VdatosUni = getTarifasLocalUnidad(Unidades.get(i).getNombre(),strHora);
                  System.out.println("VdatosUni  "+VdatosUni);
                  if (VdatosUni == null)
                      {
                      result.addElement("0");
                      result.addElement("Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados, para el no de horas solicitado.");
                        //result = "0|Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados, para el no de horas solicitado.|";
                        return result;
                     }


               preciounidad=Double.parseDouble(VdatosUni.elementAt(0).toString()); // precio segun la horas de viaje
               precio_flotilla= preciounidad;   // pRECIO POR EL NO DE UNIDADES

               // agrga horas extras
               if (diffHours >12)
               {

                   VdatosUni = getTarifasLocalUnidad(Unidades.get(i).getNombre(),"HORA_EXTRA");
                   double preio_hora_extra=0;
                    if (VdatosUni!= null && VdatosUni.size() > 0)
                         preio_hora_extra=Double.parseDouble(VdatosUni.elementAt(0).toString());
                    int horas_extras= (int)diffHours-12;
                    System.out.println("diffHours "+diffHours+"   Prcio horas_extras  "+preio_hora_extra
                                     +" se cobran "+horas_extras+"  Monto  "+horas_extras*preio_hora_extra*Unidades.get(i).getNo_unidades() );
                    precio_flotilla=precio_flotilla + (horas_extras*preio_hora_extra );
                    precio_flotilla=round(precio_flotilla,2);
               }

               precio=precio+ (precio_flotilla*Unidades.get(i).getNo_unidades());
               System.out.println("get_ContizcionLocal  --  precio "+precio);
               Unidades.get(i).setOtros1(round(precio_flotilla,2)+"");
               Unidades.get(i).setOtros2(operadores+"");

             } // for Unidades
        }catch(Exception e)
         {
            result.addElement("0");
            result.addElement("Verifique los datos de su contratos Fecha Inicio , Fecha Final.");
              //result = "0|Verifique los datos de su contratos Fecha Inicio , Fecha Final.|";
            e.printStackTrace();
            return result;
         }
         precio=round(precio,2);

         result.addElement("1");
         result.addElement(precio);
         result.addElement(operadores);
         result.addElement(Unidades);
        //result = "1|"+precio+"|"+operadores;  // Operacion Exitosa

      return result;
    }
//      VERSIONN 2




 // Este es el que se usa en Ventour
public SalidaCotiza get_Cotizador(CotizacionD cotizacion)
{
    try {


    double tarifa_local=0;
    double tarifa_foranea=0;
    SalidaCotiza salida_final= new SalidaCotiza();
    Vector vresult = new Vector();
    SalidaCotiza  sal_coti = new SalidaCotiza();
    List<Unidad> lista_unidades = new ArrayList<Unidad>();
    List<ProductoCotiza> lista = new ArrayList<ProductoCotiza>();

   cotizacion.setPorceDescuento(cotizacion.getPorceDescuento()/100);
    double kmts = cotizacion.getKilometros();
    String  tipo_cotiza=cotizacion.getTipoCotixacion();
    // obtiene Kmts
    if (tipo_cotiza.equalsIgnoreCase("F") && kmts <=0)
    {
        kmts= getkilometros( cotizacion.getListaTramos());
    }

    //  valida que tipo de cotizador  se hace
    TarifaFija tarifaFija= cotizacion.getTarifaFija();
    ProductoCotiza produtoCotiza= null;

     if (tarifaFija != null)
     {
       System.out.println("Cotiza por tarifa Especial   ");
       sal_coti = CotizadorTarifaEspecial( cotizacion);
       List<ProductoCotiza> listado =sal_coti.getProductos();
       lista_unidades=sal_coti.getLista_unidades();
        for (int i = 0; i < listado.size(); i++) {
             lista.add(listado.get(i));

        }
     }
    else if(tipo_cotiza.equals("L"))
    {
      sal_coti=  CotizadorLocal( cotizacion);
      produtoCotiza= sal_coti.getProductos().get(0);
       lista_unidades=sal_coti.getLista_unidades();   // unidads a cotizar
       System.out.println("produtoCotiza   "+produtoCotiza.getServicio());
       lista.add(produtoCotiza);
    }

    else if(tipo_cotiza.equals("F"))
    {
//       List<ProductoCotiza> listado = CotizadorForaneo( cotizacion,kmts);
        SalidaCotiza salida = CotizadorForaneo( cotizacion,kmts);
         lista_unidades=salida.getLista_unidades();
       List<ProductoCotiza> listado =salida.getProductos();
       List<Unidad> unidades =salida.getLista_unidades();
        for (int i = 0; i < listado.size(); i++) {
             lista.add(listado.get(i));

        }
    }

    if (cotizacion.getListaProductos() != null && cotizacion.getListaProductos().size() >0)
    {
        List<ProductoCotiza> listadop=CotizadorProducto(cotizacion);
          for (int i = 0; i < listadop.size(); i++) {
             lista.add(listadop.get(i));

        }

    }
    System.out.println("Resultado cotizador "+lista.size());
  //return lista;

    salida_final.setProductos(lista);
    salida_final.setLista_unidades(lista_unidades);
     System.out.println("Resultado cotizador Unidades "+lista_unidades.size());

     return salida_final;

     } catch (Exception e) {
         e.printStackTrace();
         return null;
    }
    
}


public SalidaCotiza CotizadorLocal(CotizacionD cotizacion)
 {

    SalidaCotiza  sali_coc= new SalidaCotiza();
    double descuento=cotizacion.getPorceDescuento();
    double iva = cotizacion.getPorceIva();
    List<Unidad> unidades = cotizacion.getListaUnidades();
    String Fecha_ini = cotizacion.getFechaInicio();
    String Fecha_fin = cotizacion.getFechaFin();
    List<Unidad> lista_unidades = cotizacion.getListaUnidades();
    List<Unidad> lista_unidades1 = new ArrayList<Unidad>();

    Vector Vcotizador = get_ContizcionLocal(unidades,Fecha_ini, Fecha_fin);


     System.out.println("Salida    Cotizador Local   "+Vcotizador);
    String doOK= Vcotizador.elementAt(0)+"";
    String msj="";
    double importe =0;
    ProductoCotiza pc= new ProductoCotiza();
    String NombreConcepto= cotizacion.getConceptoLocal();
   // if (doOK.equalsIgnoreCase("0"))
   //     msj= Vcotizador.elementAt(1)+"";
    if (doOK.equalsIgnoreCase("1"))
    {
        importe =Double.parseDouble(Vcotizador.elementAt(1)+"");


        pc =  getProductoCotiza( cotizacion.getConceptoLocal(),  importe, true, cotizacion.getPorceDescuento(),
                           false, cotizacion.getPorceIva());


       /* double montodescuento = importe*descuento;
        double total=importe - montodescuento;
         pc.setServicio(NombreConcepto); //"SERVICIO LOCAL METROPOLITANO");
         pc.setImporte(round(importe,2));
         pc.setDescuento(round(montodescuento,2));
         pc.setSubtotal_Descuento(round(importe - (montodescuento),2));
         pc.setIVA(0);
         pc.setTOTAL( round(total,2));*/

         unidades= (List<Unidad> )Vcotizador.elementAt(3);
         Unidad uni = null;
         double precio_unidad=0;
        for (int i = 0; i < unidades.size(); i++) {
            uni = null;
            uni=(Unidad)unidades.get(i);
            importe =Double.parseDouble(uni.getOtros1());

            System.out.println("Unidad "+uni.getNombre());
            System.out.println("Unidad "+uni.getNo_unidades());
            System.out.println("Unidad "+uni.getOtros1());
            System.out.println("Unidad "+uni.getOtros2());
            importe=getPrecioUnidad(importe, 0, true,cotizacion.getPorceDescuento(),false, cotizacion.getPorceIva());
            uni.setOtros1(importe+"");
            lista_unidades1.add(uni);
        }

    }

     List<ProductoCotiza> listaprodu = new ArrayList<ProductoCotiza>();
     listaprodu.add(pc);
      System.out.println("lOTIZASOR LOCAL "+lista_unidades1.size()+"  unIDADES");
     sali_coc.setProductos(listaprodu);
     sali_coc.setLista_unidades(lista_unidades1);
     return sali_coc;

}



public SalidaCotiza  CotizadorTarifaEspecial(CotizacionD cotizacion)
{
    SalidaCotiza salida_cotiza = new SalidaCotiza();
    List<Unidad> Unidades= cotizacion.getListaUnidades();
    String FechaInicio = cotizacion.getFechaInicio();
    String FechaFin=  cotizacion.getFechaFin();
    double porce_desc = cotizacion.getPorceDescuento();
    TarifaFija tarifa_fija= cotizacion.getTarifaFija();

    List<Unidad> lista_unidades1= new ArrayList<Unidad>();
     List<ProductoCotiza>  lista = new ArrayList<ProductoCotiza>();
    try {
        SimpleDateFormat sff = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        double diffDays=1;
        Date fecha_ini= sff.parse(FechaInicio);
        Date fecha_fin= sff.parse(FechaFin);
        double precio_unidad =0;
        double precio_total =0;
         // System.out.println("FechaInicio "+FechaInicio);
      //  System.out.println("FechaFin "+FechaFin);
        double diff = fecha_fin.getTime() - fecha_ini.getTime();

         // Calcula Diferencias en dias y horas
        double diffHours = Math.ceil(diff / (60 * 60 * 1000));
       System.out.println("Diferencia en horas "+diffHours);

        // calcular la diferencia en dias
         SimpleDateFormat sf1 = new SimpleDateFormat("dd/MM/yyyy");
         fecha_ini= sf1.parse(FechaInicio);
         fecha_fin= sf1.parse(FechaFin);
         diff = fecha_fin.getTime() - fecha_ini.getTime();
         diffDays =Math.ceil ( diff / (24 * 60 * 60 * 1000))+1;
         System.out.println("diffDays "+diffDays);

         double precio_dia=tarifa_fija.getTarifa_dia();   
         double precio_estancia=tarifa_fija.getEstancia();
         double estancia=0,  estanciaTotal=0;
         double precio_6horas=tarifa_fija.getTarifa_6h();
         double precio_12horas=tarifa_fija.getTarifa_12h();
   
        Unidad unidad=null;
         for (int i = 0; i < Unidades.size(); i++) {
              unidad=null;
              unidad = Unidades.get(i);
              // ESTANCIA
              if (tarifa_fija.getAplica_Estancia().equalsIgnoreCase("S") && diffDays > 1  )
              {
                  estancia = (diffDays - 1) * precio_estancia;
                   System.out.println("estancia  "+estancia);
             }

              // HORA EXTRA
              if ( (!tarifa_fija.getAplica_Estancia().equalsIgnoreCase("S") &&
                   tarifa_fija.getAplica_Hora_Extra().equalsIgnoreCase("S") && diffHours > 12 )
                   || (diffDays <=1 && diffHours > 12) )

              {

                  estancia += (diffHours-12) *tarifa_fija.getHora_Extra();
                  System.out.println("hora_extra  "+estancia);
              }
                 

                System.out.println("precio_dia  "+precio_dia);
                System.out.println("estancia  "+estancia);  
  
                if (diffDays <=1){
                 
                    if (diffHours <=2)
                         precio_dia= tarifa_fija.getTarifa_2h();
                    else if (diffHours>2 && diffHours <=4)
                         precio_dia= tarifa_fija.getTarifa_4h();
                    else if (diffHours>4 && diffHours <=6)
                         precio_dia= tarifa_fija.getTarifa_6h();
                    else if (diffHours>6 && diffHours <=8)
                         precio_dia= tarifa_fija.getTarifa_8h();
                    else if (diffHours>8 && diffHours <=10)
                         precio_dia= tarifa_fija.getTarifa_10h();
                    else if (diffHours>10  && diffHours <=12 )
                         precio_dia= tarifa_fija.getTarifa_12h();
                    else
                         precio_dia= tarifa_fija.getTarifa_dia();
                 }
               else
                   precio_dia= tarifa_fija.getTarifa_dia();

                estanciaTotal+=estancia*Unidades.get(i).getNo_unidades();
                precio_total+= precio_dia*Unidades.get(i).getNo_unidades() ;

              //  precio_unidad=precio_dia;
              //  precio_unidad+=estancia;
                precio_unidad=getPrecioUnidad(precio_dia, estancia, true, cotizacion.getPorceDescuento(),
                                             tarifa_fija.getAplica_Iva().equalsIgnoreCase("S"), cotizacion.getPorceIva());

                System.out.println("precio_unidad  "+precio_unidad);
             unidad.setOtros1(precio_unidad+"" );
             unidad.setOtros2("1" );
             lista_unidades1.add(unidad);
         }   // for
       System.out.println("precio_total  "+precio_total);
       System.out.println("estanciaTotal  "+estanciaTotal);
        String nombreConce= cotizacion.getConceptoForaneo();
         double importe = precio_total;
       

         if (tarifa_fija.getAplica_Iva().equalsIgnoreCase("S"))
             {
               importe = precio_total;

             }
            else
               importe = precio_total+estanciaTotal;

          System.out.println("importe  "+importe);

          if (tarifa_fija.getAplica_Iva().equalsIgnoreCase("S") )
         {
             ProductoCotiza pc =  getProductoCotiza( nombreConce,  importe, true, cotizacion.getPorceDescuento(),
                               tarifa_fija.getAplica_Iva().equalsIgnoreCase("S"), cotizacion.getPorceIva());
             lista.add(pc);
             if (estanciaTotal > 0 )
             {
              nombreConce= cotizacion.getConceptoLocal();
             ProductoCotiza pc1 =  getProductoCotiza( nombreConce,  estanciaTotal, true, cotizacion.getPorceDescuento(),
                           false, cotizacion.getPorceIva());
             lista.add(pc1);
             }
        }
       else if (!tarifa_fija.getAplica_Iva().equalsIgnoreCase("S") && importe > 0)
         {
             nombreConce= cotizacion.getConceptoLocal();
             ProductoCotiza pc1 =  getProductoCotiza( nombreConce,  importe, true, cotizacion.getPorceDescuento(),
                           false, cotizacion.getPorceIva());
             lista.add(pc1);
         }

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
     System.out.println("lOTIZADOR Especial "+lista_unidades1.size()+"  unIDADES");

     salida_cotiza.setLista_unidades(lista_unidades1);
     salida_cotiza.setProductos(lista);


    return salida_cotiza;
}

/*public ProductoCotiza getProductoCotiza(String nombreConce, double importe, boolean aplicaDesc, double porcentaje_desc, boolean aplicaIva, double porcentIVA)
{
       ProductoCotiza pc = null;
  System.out.println(" getProductoCotiza importe  "+importe);
    try {
         double  montoiva=0;
         double montoDesc=0;



         if (aplicaDesc)
         montoDesc= round(importe*porcentaje_desc,2);

         double subtotal= round(importe - montoDesc,2) ;



        if (   aplicaIva   )
             montoiva = round(porcentIVA * subtotal, 2);

         double total=round(subtotal+montoiva,2);



         pc = new ProductoCotiza();




         pc.setServicio(nombreConce);//"TRANSPORTE FORANEO");
         pc.setImporte(round(importe,2));
         pc.setDescuento(round(montoDesc,2));
         pc.setSubtotal_Descuento(round(importe - (montoDesc),2));
         pc.setIVA(round(montoiva,2));
         pc.setTOTAL( round(total,2));
         System.out.println(" getProductoCotiza total  "+total);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
       return pc;
}
*/

public ProductoCotiza getProductoCotiza(String nombreConce, double importe, boolean aplicaDesc, double porcentaje_desc, boolean aplicaIva, double porcentIVA)
{
       ProductoCotiza pc = null;
      System.out.println(" getProductoCotiza importe  "+importe);
    try {
         double  montoiva=0;
         double montoDesc=0;
        //NumberFormat formatter = new DecimalFormat("#0.00");


         if (aplicaDesc)
         montoDesc= round(importe*porcentaje_desc,2);


          System.out.println(" getProductoCotiza montoDesc "+montoDesc);
         double subtotal= round(importe - montoDesc,2) ;
         
         System.out.println(" getProductoCotiza subtotal "+subtotal);
         // desde aqui se agrego para ek desgloce
         subtotal = round(subtotal,0);
        
         System.out.println(" getProductoCotiza subtotal "+subtotal);
         System.out.println(" getProductoCotiza subtotal "+Math.floor(subtotal));

        /* if (   aplicaIva   )
             montoiva = round(porcentIVA * subtotal, 2);

         double total=round(subtotal+montoiva,2);*/

         double total=subtotal;
         if (   aplicaIva   )
         {
             System.out.println(" getProductoCotiza desgloce   "+subtotal / (1 + porcentIVA));
             montoiva = round(subtotal - round(subtotal / (1 + porcentIVA), 2), 2);
             
             System.out.println(" getProductoCotiza montoiva  "+montoiva);
             subtotal = round(subtotal - montoiva ,2);
                
              System.out.println(" getProductoCotiza subtotal  "+subtotal);

         }


         pc = new ProductoCotiza();




         pc.setServicio(nombreConce);//"TRANSPORTE FORANEO");
         pc.setImporte(round(importe-montoiva,2));
         pc.setDescuento(round(montoDesc,2));
         pc.setSubtotal_Descuento(round(subtotal,2));
         pc.setIVA(round(montoiva,2));
         pc.setTOTAL( round(total,2));
         System.out.println(" getProductoCotiza total  "+total);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
       return pc;
}

public double getPrecioUnidad(double importe, double estancia, boolean aplicaDesc, double porcentaje_desc, boolean aplicaIva, double porcentIVA)
{
       double total=0;
  System.out.println(" getPrecioUnidad importe  "+importe);
    System.out.println(" getPrecioUnidad estancia  "+estancia);
    try {
         double  montoiva=0;
         double montoDesc=0;
           System.out.println(" getPrecioUnidad aplicaDesc  "+aplicaDesc);
         if (aplicaDesc)
         {
             montoDesc = round(importe * porcentaje_desc, 2);
             estancia=estancia-(estancia * porcentaje_desc);
        }
         double subtotal= round(importe - montoDesc,2) ;

         if (   aplicaIva   )
             //montoiva = round(porcentIVA * subtotal, 2);
             montoiva = round(subtotal - round(subtotal / (1 + porcentIVA), 2), 2);

          total=round(subtotal+estancia,2);


         System.out.println(" getProductoCotiza total  "+total);
    } catch (Exception e) {
        e.printStackTrace();
        return importe;
    }
       return total;
}

   //  **   Este es el que se usa
 public SalidaCotiza CotizadorForaneo(CotizacionD cotizacion, double total_Kmts)
    {
     List<ProductoCotiza>  lista = new ArrayList<ProductoCotiza>();
     SalidaCotiza sal_cot = new SalidaCotiza();
      double precio_unidad=0;
      Vector result =new Vector();
    //  double precio   =0, precioconductores=0;
      double kilometros=0, kilometrosIda=0, kilometrosRegreso=0;
       Vector VdatosUni = null;
       StringTokenizer sf = null;
       String tramousado="";
       double factor=0, factorestancia =0, tarifa_minima=0;
       double descuento = cotizacion.getPorceDescuento();

       List<TramoRuta>  Tramos=cotizacion.getListaTramos();
       TramoRuta tramo;
       String ViajeRedondo=cotizacion.getViajeredondo();
        String NombreLocal= cotizacion.getConceptoLocal();
         String NombreForaneo= cotizacion.getConceptoForaneo();

       if (total_Kmts <=0) {  // Busca los kilometros por tramo
       for (int i = 0; i < Tramos.size(); i++) {
            tramo = Tramos.get(i);
            String strkilomet =getkilometro(tramo.getOrigen(),tramo.getDestino());

            if (strkilomet != null && !strkilomet.equals("0"))
            {
                sf = new StringTokenizer(strkilomet,"|");

                //kilometrosIda+=Double.parseDouble(strkilomet  );
                kilometros+=Double.parseDouble(sf.nextToken());
                tramousado+=(tramousado.length() > 0 ? " | ":"")+sf.nextToken();
            }
            else
            {
                result.addElement("0");
                result.addElement("Tramo no valida "+tramo.getOrigen()+'-'+tramo.getDestino());

                return null;
            }
           // System.out.println("Kilometros "+kilometrosIda);
        }
       kilometros=kilometrosIda;
       System.out.println("Kilometros  Ida "+kilometrosIda);

       // Obtiene lso kilometros de regrerso
       
       if (total_Kmts <=0 && ViajeRedondo.equalsIgnoreCase("S")) {
           int j= Tramos.size();
           String strkilomet;
           for (int i = 0; i < Tramos.size(); i++) {
                tramo = Tramos.get(j-(i+1));
                strkilomet=getkilometro(tramo.getDestino(),tramo.getOrigen());
                 if (strkilomet != null && !strkilomet.equals("0"))
                  {
                     //kilometrosRegreso += Double.parseDouble(strkilomet);
                      sf = new StringTokenizer(strkilomet,"|");

                    kilometros+=Double.parseDouble(sf.nextToken());
                    tramousado+=(tramousado.length() > 0 ? " | ":"")+sf.nextToken();
                   }
                 else{
                result.addElement("0");
                result.addElement("Tramo no valida "+tramo.getOrigen()+'-'+tramo.getDestino());
                return null;
               }
                //System.out.println("Kilometros "+kilometrosRegreso);
            }
           System.out.println("Kilometros Regreso "+kilometrosRegreso);
           kilometros+=kilometrosRegreso;
        }
       }
      else
        kilometros=  total_Kmts;

        System.out.println("***   Kilometros Total del vaije "+kilometros);


         // Optiene el precio por kilometro
        List<Unidad> Unidades= cotizacion.getListaUnidades();
        String FechaInicio = cotizacion.getFechaInicio();
        String FechaFin=  cotizacion.getFechaFin();;
        int operadores=0, operador_unidad=0;;
        double preciounidad=0,  preciounidades=0 ,estancia=0;
        double estanciaTotal=0, precioConduTotaL=0, precioKmtsTotal=0;
//        precio=0;
        System.out.println("*****    Flotillas a cotizar  "+Unidades.size());
        List<Unidad> Unidades_cotiza = new ArrayList<Unidad>();
        Unidad uni_c= null;

         double estancia_unidad =0;
         double preciolocal=0;
         for (int i = 0; i < Unidades.size(); i++) {
            uni_c=Unidades.get(i);
            System.out.println("*****  Unidades.get(i).getNombre() "+Unidades.get(i).getNombre() );
            System.out.println("***** uni_c     "+uni_c.getNombre() );
             //VdatosUni = getTarifasUnidad(Unidades.get(i).getNombre());
            VdatosUni = getTarifasUnidad(uni_c.getNombre());
            precio_unidad =0;   //  preciounidad=0;
            estancia_unidad =0;
            if (VdatosUni == null)
                  {
                   result.addElement("0");
                   result.addElement("Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados.");
                   // result = "0|Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados.|";
                    return null;
                 }
              factor=Double.parseDouble(VdatosUni.elementAt(0).toString());

              factorestancia=Double.parseDouble(VdatosUni.elementAt(1).toString());

              tarifa_minima=Double.parseDouble(VdatosUni.elementAt(2).toString());


             //  factor=getFactor(Unidades.get(i).getNombre());
               //Calcula el precio;
               System.out.println("No de  Unidades  "+Unidades.get(i).getNo_unidades());
               if (kilometros <250)
               {
                    precio_unidad = tarifa_minima;
                     System.out.println("Tarifa Minima   "+tarifa_minima);
                  
               }
               else
               {

                  precio_unidad = kilometros * factor;
                  System.out.println("Factor kilometro   "+factor);
                  
              }

                precioKmtsTotal+= precio_unidad*uni_c.getNo_unidades();
             

             //   ******************* CALULA LA ESTANCIA;

                  SimpleDateFormat sff = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                   double diffDays=1;

                   try {
                        Date fecha_ini= sff.parse(FechaInicio);
                        Date fecha_fin= sff.parse(FechaFin);
                       double diff = fecha_fin.getTime() - fecha_ini.getTime();

                         // Calcula Diferencias en dias y horas

                        double diffHours = Math.ceil(diff / (60 * 60 * 1000));
                        // calcular la diferencia en dias

                        SimpleDateFormat sf1 = new SimpleDateFormat("dd/MM/yyyy");
                         fecha_ini= sf1.parse(FechaInicio);
                         fecha_fin= sf1.parse(FechaFin);
                          diff = fecha_fin.getTime() - fecha_ini.getTime();
                         diffDays =Math.ceil ( diff / (24 * 60 * 60 * 1000))+1;
                         
                         System.out.println("diffDays "+diffDays);

                         // Calcula  ESTANCIAS

                            if (diffDays >1)
                            {
                               int estanciadias = (int)diffDays;
                             
                                /*String HoraIni= FechaInicio.substring(FechaInicio.indexOf(" ")+1, FechaInicio.indexOf(" ")+3);
                                String HoraFin= FechaFin.substring(FechaFin.indexOf(" ")+1, FechaFin.indexOf(" ")+3);

                           
                                 if (kilometros >= 1500 && diffDays > 2 && ViajeRedondo.equalsIgnoreCase("N"))
                                     estanciadias = estanciadias - 2;
                                else {
                                    if(diffDays >= 2 && Integer.parseInt(HoraIni) > 19  &&  Integer.parseInt(HoraFin) <23.5   )
                                     estanciadias = estanciadias - 2;
                                    else{
                                        if(Integer.parseInt(HoraIni) > 19   )
                                           estanciadias = estanciadias - 1;
                                        if(Integer.parseInt(HoraFin) <23.5   )
                                           estanciadias = estanciadias - 1;
                                       
                                    }
                                  }
                                 *
                                 */
//*********************************************

                            String HoraIni=FechaInicio.substring(FechaInicio.indexOf(" ")+1, FechaInicio.indexOf(" ")+6).replaceAll(":", ".");
                               String HoraFin= FechaFin.substring(FechaFin.indexOf(" ")+1, FechaFin.indexOf(" ")+6).replaceAll(":", ".");
                               System.out.println("---HoraIni "+HoraIni);

                               System.out.println("---HoraFin "+HoraFin);


                                 if (kilometros >= 1500 && diffDays > 2 && ViajeRedondo.equalsIgnoreCase("N"))
                                     estanciadias = estanciadias - 2;
                                else {
                                    if(diffDays >= 2 && Double.parseDouble(HoraIni) >= 19  &&  Double.parseDouble(HoraFin) <=23.3   )
                                     estanciadias = estanciadias - 2;
                                    else{
                                        if(Double.parseDouble(HoraIni) >= 19   )
                                           estanciadias = estanciadias - 1;
                                        if(Double.parseDouble(HoraFin) <=23.3   )
                                           estanciadias = estanciadias - 1;

                                    }
                                  }

			       
//*************************************************
                                System.out.println("No de dias de estancia a cobrar "+estanciadias);
                                System.out.println("factorestancia "+factorestancia);

                                estancia_unidad = estanciadias*factorestancia ;  //  Verifiacar el factor estancia
                        

                            }

                      
                          } catch (Exception e) {
                            e.printStackTrace();
                              result.addElement("0");
                              result.addElement("Error en fechas.");

                            return null;
                        }
                     //   ******************* CALULA LA ESTANCIA;

                  // precio conducto
               double precioconductor=0;
              double tarifa_conductor=  Double.parseDouble(VdatosUni.elementAt(3).toString());

               // Calcula los operadores
               if (kilometros >=1000)
               {
                   operador_unidad = 2 ;
                   // incrementa el monto
                  /* VdatosUni = getTarifasUnidad("PRECIO_CONDUCTOR");
                   if (VdatosUni == null)
                      {
                       result.addElement("0");
                       result.addElement("El precio del conductor extra No estan configurado.");
                       // result = "0|Los datos de la flotilla"+Unidades.get(i).getNombre()+"No estan configurados.|";
                        return null;
                     }

                     double tarifa_conductor=Double.parseDouble(VdatosUni.elementAt(2).toString());

                   */
                     System.out.println("diffDays  "+diffDays);
                     precioconductor= tarifa_conductor*diffDays ;
                    // precioConduTotaL+=tarifa_conductor*Unidades.get(i).getNo_unidades()*diffDays;
                     estancia_unidad+=precioconductor;

                     System.out.println("precio conductor tarifa_conductor extra "+tarifa_conductor);
                     System.out.println("precioconductor extras "+precioconductor);


               }
               else
                   operador_unidad =1;

               System.out.println(" -------   precio Total unidad  "+precio_unidad);
                 System.out.println(" -------  estancia_unidad  "+estancia_unidad);


                 preciolocal+=estancia_unidad*uni_c.getNo_unidades();

                 precio_unidad=getPrecioUnidad(precio_unidad, estancia_unidad, true, cotizacion.getPorceDescuento(),
                                             true, cotizacion.getPorceIva());
                 System.out.println("precio_unidad  "+precio_unidad);

           
                operadores += operador_unidad*uni_c.getNo_unidades();
                uni_c.setOtros1(precio_unidad+"" );
                uni_c.setOtros2(Math.round(operador_unidad)+"" );
                Unidades_cotiza.add(uni_c);
        }   // for (int i = 0; i < Unidades.size(); i++) {
        System.out.println("precio Total por Kilometro  "+precioKmtsTotal);
     
     

         precioKmtsTotal= round(precioKmtsTotal,2);
      
       //  double preciolocal=round(precioConduTotaL+estanciaTotal,2);
          System.out.println("preciolocal     "+preciolocal);
          Vector result1= new Vector();

          double importe =precioKmtsTotal;
          double iva= cotizacion.getPorceIva();
          System.out.println("Porcentaje Iva "+iva);

          ProductoCotiza pc = new ProductoCotiza();
          pc =  getProductoCotiza( NombreForaneo,  importe, true, cotizacion.getPorceDescuento(),
                           true, cotizacion.getPorceIva());
          lista.add(pc);

        
         if (preciolocal > 0)
         {
            Vector vresul= new Vector();
              importe =Double.parseDouble(preciolocal+"");
               System.out.println("Local  "+result);
                pc =  getProductoCotiza( cotizacion.getConceptoLocal(),  importe, true, cotizacion.getPorceDescuento(),
                           false, cotizacion.getPorceIva());
                lista.add(pc);
         }

         
      sal_cot.setLista_unidades(Unidades_cotiza);
      sal_cot.setProductos(lista);
   
     return sal_cot;
    }

  

 public List<ProductoCotiza>  CotizadorProducto(CotizacionD cotizacion)
    {
     List<ProductoCotiza>  salida = new ArrayList<ProductoCotiza>();
     ProductoCotiza pc = new ProductoCotiza();
     Vector vresult = new Vector();
     List<Producto> productos =cotizacion.getListaProductos();
   // int  no_personas=cotizacion.getNo_personas();
     int cantidad=1;
    //System.out.println("no_personas "+no_personas);

    
  Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Mensaje informativo...");
     if (productos!= null && productos.size() >0 )
         {
         Vector vrow = new Vector();
         double descuento = cotizacion.getPorceDescuento();
         double iva  = cotizacion.getPorceIva();
         Producto producto = null;;
         System.out.println("productos A cotizar **** "+productos.size());

             for (int i = 0; i < productos.size(); i++) {
                   producto = new Producto();
                   producto= productos.get(i);
                   cantidad = producto.getCantidad();

                  System.out.println("Regresa "+producto.getNombre()+"   "+pc.getCantidad());

                  //  double importe = producto.getImporte()* no_personas;
                    double importe = producto.getImporte()* cantidad;
                    double montoDesc=0;
                 //   System.out.println("productos aplica descuento **** "+producto.getAplica_descuento());
                    if (producto.getAplica_descuento().equalsIgnoreCase("S"))
                        montoDesc=round(importe*descuento,2);

                /*   double subtotal= importe - montoDesc ;

                    double montoiva=0;
                     if (producto.getAplica_iva().equalsIgnoreCase("S") )
                           montoiva= round(iva * subtotal,2);


                    double total=subtotal+montoiva;*/

                  
                    pc= new ProductoCotiza();
                    pc= getProductoCotiza( producto.getNombre(),  importe, producto.getAplica_descuento().equals("S"),
                                    cotizacion.getPorceDescuento(),producto.getAplica_iva().equalsIgnoreCase("S"),cotizacion.getPorceIva());
                    pc.setImporte(producto.getImporte());
                    pc.setCantidad(cantidad);
                   System.out.println("Regresa "+producto.getNombre()+"   "+pc.getCantidad());

                    salida.add(pc);
                
             }
         }
     return salida;

    }


 public  double round(double val, int places) {

  long factor = (long)Math.pow(10,places);
  // Shift the decimal the correct number of places
  // to the right.
  val = val * factor;

  // Round to the nearest integer.
  long tmp = Math.round(val);

  // Shift the decimal the correct number of places
  // back to the left.
   return (double)tmp / factor;
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


