/*
 * JClsImpresionReporte.java
 *
 * Created on 9 de mayo de 2008, 11:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_cortetaquilla.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import xertms.solicitud.TmsCorteTaqFacadeRemote;

/**
 *
 * @author ocruz
 */
public class JClsImpresionReporte {

    private Map param = null;
    private Connection cnx = null;
    private InputStream archivo = null;
    private String strRutaRelRpt;
    private JasperPrint jasJasperPrint;
    private Context CONTEXTO;
    private  TmsCorteTaqFacadeRemote cortesTaq;
    
    /** Creates a new instance of JClsImpresionReporte */
    public JClsImpresionReporte(Context pCONTEXTO) {
        CONTEXTO = pCONTEXTO;
        jasJasperPrint = new JasperPrint();
        strRutaRelRpt = "tms_cortetaquilla/reportes/";
        
        try {
            cnx = getConexion();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    
    private Connection getConexion() throws NamingException {
        Connection conn = null;
        try {
            DataSource ds = (DataSource)CONTEXTO.lookup("TMS_DB");
            conn = ds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public boolean imprimirCorte(String corteId, Timestamp fi, Timestamp ff,
            String tipoReporte, String corteDetallado, String reporte, String idCortes, String termminal, String folio_corte) {
       String algoritmoVigente="";
        try{
            archivo = getClass().getClassLoader().getResourceAsStream(strRutaRelRpt+reporte);
            
            param = new HashMap();
            if(corteId!=null){
                int cI=Integer.valueOf(corteId);
                param.put("P_CORTE_ID",cI);
            }
            else param.put("P_CORTE_ID", null);
            param.put("P_FECHA_INICIO",fi);
            param.put("P_FECHA_FIN",ff);
            param.put("P_TIPO_REPORTE",tipoReporte);
            param.put("P_DESGLOSADO",corteDetallado);
            param.put("P_CORTE_ID_STR",idCortes);
            
            param.put("SUBREPORT_DIR",strRutaRelRpt);
            System.out.println("Antes de imprimir las referencias: "+tipoReporte);
            if(tipoReporte.equals("D"))
            {
                param.put("P_FOLIO_CORTE",folio_corte);
                SimpleDateFormat formatoFecha= new SimpleDateFormat("ddMMyyyy");
                String ref = formatoFecha.format(fi)+termminal;
                System.out.println("ref: "+ref);
                algoritmoVigente =cortesTaq.algoritmoBancoVigente();
                if (algoritmoVigente.equals("BANCOMER_10")){
                    System.out.println("P_REFERENCIA_T:"+formatoFecha.format(fi)+termminal+"T"+calculaDV_Alg35(ref+"T"));
                    param.put("P_REFERENCIA_T",formatoFecha.format(fi)+termminal+"T"+calculaDV_Alg35(ref+"T"));
                    param.put("P_REFERENCIA_M",formatoFecha.format(fi)+termminal+"M"+calculaDV_Alg35(ref+"M"));
                    param.put("P_REFERENCIA_A",formatoFecha.format(fi)+termminal+"A"+calculaDV_Alg35(ref+"A"));
                    param.put("P_REFERENCIA_F",formatoFecha.format(fi)+termminal+"F"+calculaDV_Alg35(ref+"F"));
                    param.put("P_REFERENCIA_S",formatoFecha.format(fi)+termminal+"S"+calculaDV_Alg35(ref+"S"));
                }else{
                    System.out.println("P_REFERENCIA_T:"+cortesTaq.tmsAlgoritmoBanco(ref+"T", algoritmoVigente));
                    param.put("P_REFERENCIA_T",cortesTaq.tmsAlgoritmoBanco(ref+"T", algoritmoVigente));
                    param.put("P_REFERENCIA_M",cortesTaq.tmsAlgoritmoBanco(ref+"M", algoritmoVigente));
                    param.put("P_REFERENCIA_A",cortesTaq.tmsAlgoritmoBanco(ref+"A", algoritmoVigente));
                    param.put("P_REFERENCIA_F",cortesTaq.tmsAlgoritmoBanco(ref+"F", algoritmoVigente));
                    param.put("P_REFERENCIA_S",cortesTaq.tmsAlgoritmoBanco(ref+"S", algoritmoVigente));
                }

            }
            System.out.println("parametros "+corteId+" "+fi+" "+ff+" "+tipoReporte+" "+corteDetallado+"-"+strRutaRelRpt+reporte+"-("+idCortes+")");
            jasJasperPrint = JasperFillManager.fillReport(archivo, param, cnx);
            JasperViewer.viewReport(jasJasperPrint, false);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


      private int calculaDV_Alg35(String nr){
  Vector<Integer> inicial= new Vector<Integer>();
  Vector<Integer> segundo= new Vector<Integer>();
  Vector<String> letras= new Vector<String>();
  Vector<String> numeros= new Vector<String>();

  int n = 0;
  //int A = 1, B = 2,C = 3,D = 4,E = 5,F= 6, G = 7,H = 8,I = 9,J = 10,K = 11, L = 12, M = 13,N = 14, O = 15, P = 16, Q = 17, R=18,S = 19,T = 20, U = 21, V = 22, W = 23,X = 24, Y = 25,Z = 26;
  letras.add("");
  letras.add("A");letras.add("B");letras.add("C");letras.add("D");letras.add("E");letras.add("F");letras.add("G");letras.add("H");
  letras.add("I");letras.add("J");letras.add("K");letras.add("L");letras.add("M");letras.add("N");letras.add("O");letras.add("P");
  letras.add("Q");letras.add("R");letras.add("S");letras.add("T");letras.add("U");letras.add("V");letras.add("W");letras.add("X");
  letras.add("Z");letras.add("Z");
  numeros.add("0");numeros.add("1");numeros.add("2");numeros.add("3");numeros.add("4");numeros.add("5");numeros.add("6");numeros.add("7");numeros.add("8");numeros.add("9");
  char[] car = nr.toCharArray();
  for(int i=0; i<car.length; i++)
  {
      char c = car[i];
      int index = letras.indexOf(""+c);
      if(index>=0)
          inicial.add(index);
      else
          inicial.add(numeros.indexOf(""+c));
  }
 //System.out.println("Paso1: "+inicial);
  for(int i = 0; i<inicial.size(); i++){
      for(int j = 1; j<=3; j++)
      {
          //System.out.println("i = "+i);
          if(i > (inicial.size() - 1))
              break;
          if(j==1)segundo.add(regresaUno(""+(((int)inicial.get(i))*4)));
          if(j==2)segundo.add(regresaUno(""+(((int)inicial.get(i))*3)));
          if(j==3)segundo.add(regresaUno(""+(((int)inicial.get(i))*8)));
          i++;
      }
      i--;
  }
  //System.out.println("Paso2: "+segundo);
  int sum = 0;
  for(int i=0; i<segundo.size(); i++)
      sum = sum + ((int) segundo.get(i));
  //System.out.println("sumaPaso2: "+sum);
  int prox = regresaProximaDecena(""+sum);
  //System.out.println("Resta: "+prox +" - "+sum);
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

}
