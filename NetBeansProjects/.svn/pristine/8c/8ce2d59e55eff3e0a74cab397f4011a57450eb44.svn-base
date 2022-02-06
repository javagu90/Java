 /*
 * TMSSesionCodBarBean.java
 *
 * Created on 26 de diciembre de 2008, 12:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package solicitud;

import entidad.TmsBoletosVentaTbl;
import entidad.TmsCorridasVenta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
/**
 *
 * @author asolis
 */
@Stateless
public class TMSSesionCodBarBean implements solicitud.TMSSesionCodBarRemote {
   @PersistenceContext(unitName = "TmsCodigoBarras-ejbPU")
   private EntityManager manager;
   @Resource(name = "TMS_DB")
   private DataSource dataSource;
   
   private String tableCorridasVenta="TMS_CORRIDAS_VENTA_TBL";
   private String tableAutobusesPlantilla="TMS_AUTOBUS_PLANTILLAS_ENC_TBL";
   /**
     * Creates a new instance of TMSSesionCodBarBean
     */
    public TMSSesionCodBarBean() {
    }
    

    public String[] validarEstadoNormal(String claveCorrida, String folioP){
        String fecha_hora = "nada";
        String valido;
        try{
            fecha_hora = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+claveCorrida+"'").getSingleResult().toString();
            valido =manager.createNativeQuery("select count(1) from tms_boletos_venta_tbl where folio_preimpreso='"+folioP+"'" +
                        " OR (referencia_pago='"+folioP+"' and tipo_operacion!='VT') " +
                        "ORDER by fecha_creacion DESC").getSingleResult().toString();
            int res=Integer.parseInt(valido.substring(1,valido.length()-1));
            System.out.println("valor entero "+res);
            if(res>1) //Existen cambio de horario sobre el boleto, o una cancelacion
                valido="[4]";
            valido="[1]";
         }catch(Exception e){
             System.out.println(e + "No halle Datos");
             e.printStackTrace();
             fecha_hora = "";
             valido = "[2]";
         }
          String regreso[] = new String[2];
          System.out.println("fecha hora validarEstadoNormal "+fecha_hora);
          regreso[0] = fecha_hora.substring(1,fecha_hora.length()-1);;
          regreso[1] = valido;
          return regreso;     
    }
    
    public String[] validarEstado(String claveCorrida, String referencia){
        String fecha_hora = "nada";        
        String valido;
        try {
            fecha_hora = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+claveCorrida+"'").getSingleResult().toString();
            valido = manager.createNativeQuery("SELECT COUNT(1)  FROM TMS_BOLETOS_VENTA_TBL tmsBolIn "+
                                    "WHERE tmsBolIn.TIPO_OPERACION NOT IN ('CN','HO','FT','FO') "+
                                    "AND DECODE(tmsBolIn.TIPO_OPERACION,'HO',tmsBolIn.ADICIONAL4,'FO',tmsBolIn.ADICIONAL4,'FT',tmsBolIn.ADICIONAL4,'AC',tmsBolIn.ADICIONAL4,tmsBolIn.ADICIONAL6) = '"+referencia+"'").getSingleResult().toString();
System.out.println("validarEstado: "+valido);
            if(valido.equals("[0]"))  //Existen cambio de horario sobre el boleto
                valido="[4]";
            valido="[1]";
         }catch(Exception e){
             System.out.println(e + "No halle Datos");
             fecha_hora = "";
             valido = "[2]";
         }
          String regreso[] = new String[2];
          System.out.println("fecha hora validarEstado "+fecha_hora);
          regreso[0] = fecha_hora.substring(1,fecha_hora.length()-1);
          regreso[1] = valido;
          return regreso;        
    }    
    
    
    public String[] validarNormal(String corrida, TmsBoletosVentaTbl nombre){
        String fecha_hora = "nada";
        String valido = null;
        Double numero = 0.0;
         try {
            fecha_hora = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+nombre.getClaveCorrida()+"'").getSingleResult().toString();
            String hora_act  = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+corrida+"'").getSingleResult().toString();
            //String hora_act = manager.createNativeQuery("select to_char(sysdate ,'DD/MM/RRRR HH24:MI') from dual").getSingleResult().toString();
            char array[] = new char[fecha_hora.length()-2];
            for(int i = 1; i < fecha_hora.length()-1; i++)
                array[i-1] = fecha_hora.charAt(i);
            fecha_hora = new String(array);
            System.out.println(fecha_hora);
            array = new char[hora_act.length()-2];
            for(int i = 1; i < hora_act.length()-1; i++)
                array[i-1] = hora_act.charAt(i);
            hora_act = new String(array);
            System.out.println(hora_act);
            valido = manager.createNativeQuery("select (to_date('"+fecha_hora+"','DD/MM/RRRR HH24:MI') + 30/1440) - to_date('"+ hora_act +"','DD/MM/RRRR HH24:MI') from dual").getSingleResult().toString();
            array = new char[valido.length()-2];
            for(int i = 1; i < valido.length()-1; i++)
                array[i-1] = valido.charAt(i);
            valido = new String(array);
            numero = Double.parseDouble(valido);
            System.out.println("numero  "+numero);
            if (numero > 0.0) {
                valido = "[3]";
            }
            //if ((numero < 0.5)&&(numero >= 0.0)) {
            if (numero == 0.0) {
                valido = "[1]";
            }
            if (numero < 0.0) {
                valido = "[0]";
            }

            String valido_ant = valido;
            valido =manager.createNativeQuery("select count(1) from tms_boletos_venta_tbl where folio_preimpreso='"+nombre.getFolioPreimpreso()+"'" +
                        " OR (referencia_pago='"+nombre.getFolioPreimpreso()+"' and tipo_operacion!='VT') " +
                        "ORDER by fecha_creacion DESC").getSingleResult().toString();
            int res=Integer.parseInt(valido.substring(1,valido.length()-1));
            System.out.println("valor entero "+res);
            if(res>1)  //Existen cambio de horario sobre el boleto, o una cancelacion
                valido = "[4]";
            if(res==1 || res==0)
                valido = valido_ant;
         }catch(Exception e){
             System.out.println(e + "No halle Datos");
             fecha_hora = "";
             valido = "[2]";
         }
          String regreso[] = new String[2];
          regreso[0] = new String(fecha_hora);
          regreso[1] = new String(valido);
          return regreso;
    }    
 
    
    public String[] validar(String claveCorrida, String corrida, String referencia){
        String fecha_hora = "nada";
        String valido = null;
        Double numero = 0.0;
         /*String link1 = manager.createNativeQuery("SELECT NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE NOMBRE_TERMINAL = '"+Origen+"'").getSingleResult().toString();
         char array[] = new char[link1.length()-2];
         for(int i = 1; i < link1.length()-1; i++)
             array[i-1] = link1.charAt(i);
         String link = new String(array);
          //System.out.println(link);*/
         try {
            fecha_hora = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+claveCorrida+"'").getSingleResult().toString();
            String hora_act  = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+corrida+"'").getSingleResult().toString();
            //String hora_act = manager.createNativeQuery("select to_char(sysdate ,'DD/MM/RRRR HH24:MI') from dual").getSingleResult().toString();
            char array[] = new char[fecha_hora.length()-2];
            for(int i = 1; i < fecha_hora.length()-1; i++)
                array[i-1] = fecha_hora.charAt(i);
            fecha_hora = new String(array);
            System.out.println(fecha_hora);
            array = new char[hora_act.length()-2];
            for(int i = 1; i < hora_act.length()-1; i++)
                array[i-1] = hora_act.charAt(i);
            hora_act = new String(array);
            System.out.println(hora_act);
            valido = manager.createNativeQuery("select (to_date('"+fecha_hora+"','DD/MM/RRRR HH24:MI') + 30/1440) - to_date('"+ hora_act +"','DD/MM/RRRR HH24:MI') from dual").getSingleResult().toString();
            array = new char[valido.length()-2];
            for(int i = 1; i < valido.length()-1; i++)
                array[i-1] = valido.charAt(i);
            valido = new String(array);
            numero = Double.parseDouble(valido);
            System.out.println("numero  "+numero);
/*            if (numero >= .5) {
                valido = "[3]";
            }
            if ((numero < 0.5)&&(numero >= 0.0)) {
                valido = "[1]";
            }
            if (numero < 0.0) {
                valido = "[0]";
            }
 */

            if (numero > 0.0) {
                valido = "[3]";
            }
            if (numero == 0.0) {
                valido = "[1]";
            }
            if (numero < 0.0) {
                valido = "[0]";
            }
            String valido_ant = valido;
            valido = manager.createNativeQuery("SELECT COUNT(1)  FROM TMS_BOLETOS_VENTA_TBL tmsBolIn "+
                                    "WHERE tmsBolIn.TIPO_OPERACION NOT IN ('CN','HO','FT','FO') "+
                                    "AND DECODE(tmsBolIn.TIPO_OPERACION,'HO',tmsBolIn.ADICIONAL4,'FO',tmsBolIn.ADICIONAL4,'FT',tmsBolIn.ADICIONAL4,'AC',tmsBolIn.ADICIONAL4,tmsBolIn.ADICIONAL6) = '"+referencia+"'").getSingleResult().toString();
System.out.println("validar: "+valido);
            if(valido.equals("[0]"))  //Existen cambio de horario sobre el boleto
                valido = "[4]";
            if(valido.equals("[1]"))
                valido = valido_ant;
         }catch(Exception e){
             System.out.println(e + "No halle Datos");
             fecha_hora = "";
             valido = "[2]";
         }
          String regreso[] = new String[2];
          regreso[0] = new String(fecha_hora);
          regreso[1] = new String(valido);
          return regreso;
    }
    
    public String[] validarBolInternet(String claveCorrida, String referencia){
        String fecha_hora = "nada";
        String valido = null;
        Double numero = 0.0;
         try {
            fecha_hora = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+claveCorrida+"'").getSingleResult().toString();
            //String hora_act  = manager.createNativeQuery("SELECT to_char(corrida.FECHA_HORA_CORRIDA,'DD/MM/RRRR HH24:MI') FROM TMS_CORRIDAS_VENTA_TBL corrida WHERE clave_corrida = '"+corrida+"'").getSingleResult().toString();
            String hora_act = manager.createNativeQuery("select to_char(sysdate ,'DD/MM/RRRR HH24:MI') from dual").getSingleResult().toString();
            char array[] = new char[fecha_hora.length()-2];
            for(int i = 1; i < fecha_hora.length()-1; i++)
                array[i-1] = fecha_hora.charAt(i);
            fecha_hora = new String(array);
            System.out.println(fecha_hora);
            array = new char[hora_act.length()-2];
            for(int i = 1; i < hora_act.length()-1; i++)
                array[i-1] = hora_act.charAt(i);
            hora_act = new String(array);
            System.out.println(hora_act);
            valido = manager.createNativeQuery("select (to_date('"+fecha_hora+"','DD/MM/RRRR HH24:MI') + 30/1440) - to_date('"+ hora_act +"','DD/MM/RRRR HH24:MI') from dual").getSingleResult().toString();
            array = new char[valido.length()-2];
            for(int i = 1; i < valido.length()-1; i++)
                array[i-1] = valido.charAt(i);
            valido = new String(array);
            numero = Double.parseDouble(valido);
            System.out.println("numero  "+numero);
            if (numero >= .5) {
                valido = "[3]";
            }
            if ((numero < 0.5)&&(numero >= 0.0)) {
                valido = "[1]";
            }
            if (numero < 0.0) {
                valido = "[0]";
            }
 

            if (numero > 0.0) {
                valido = "[3]";
            }
            if (numero == 0.0) {
                valido = "[1]";
            }
            if (numero < 0.0) {
                valido = "[0]";
            }
            String valido_ant = valido;
            valido = manager.createNativeQuery("SELECT COUNT(1)  FROM TMS_BOLETOS_VENTA_TBL tmsBolIn "+
                                    "WHERE tmsBolIn.TIPO_OPERACION NOT IN ('CN','HO','FT','FO') "+
                                    "AND DECODE(tmsBolIn.TIPO_OPERACION,'HO',tmsBolIn.ADICIONAL4,'FO',tmsBolIn.ADICIONAL4,'FT',tmsBolIn.ADICIONAL4,'AC',tmsBolIn.ADICIONAL4,tmsBolIn.ADICIONAL6) = '"+referencia+"'").getSingleResult().toString();
System.out.println("validar: "+valido);
            if(valido.equals("[0]"))  //Existen cambio de horario sobre el boleto
                valido = "[4]";
            if(valido.equals("[1]"))
                valido = valido_ant;
         }catch(Exception e){
             System.out.println(e + "No halle Datos");
             fecha_hora = "";
             valido = "[2]";
         }
          String regreso[] = new String[2];
          regreso[0] = new String(fecha_hora);
          regreso[1] = new String(valido);
          return regreso;
    }    
    
   public TmsBoletosVentaTbl buscarCodigoBarras(String referencia) {
         System.out.println("referencia "+referencia);
         List<TmsBoletosVentaTbl> temp = new ArrayList<TmsBoletosVentaTbl>();
         try{
            temp = manager.createNamedQuery("TmsBoletosVentaTbl.findByAdicional6desc").setParameter("adicional6",referencia).getResultList();
            if((temp.size() == 0)||(temp == null)){
                temp = manager.createNativeQuery("select * from tms_boletos_venta_tbl where folio_preimpreso='"+referencia+"'" +
                        " OR (referencia_pago='"+referencia+"' and tipo_operacion!='VT') " +
                        "ORDER by fecha_creacion DESC", TmsBoletosVentaTbl.class).getResultList();
                if((temp.size() == 0)||(temp == null))
                                return null;
                return temp.get(0);
            }
         }catch(Exception e)
         {
             System.out.println(e);
             return null;
         }
         TmsBoletosVentaTbl unico = new TmsBoletosVentaTbl();
         unico = temp.get(0);
         Vector valido = (Vector) manager.createNativeQuery("SELECT ESTADO_NOMBRE FROM TMS_ESTADOS_TBL WHERE estado_id = (SELECT Xer_Dbms_Numsystem.hex2dec('"+referencia.substring(0,2)+"') from dual)").getSingleResult();
         System.out.println("valido "+valido.get(0).toString() + " "+unico.getOrigen());
         Vector miterminal = (Vector) manager.createNativeQuery("SELECT NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL WHERE ESQUEMA_PROPIO = 'S'").getSingleResult();
         if(!unico.getOrigen().equals(valido.get(0).toString()) || (!unico.getOrigen().equals(miterminal.get(0).toString())) || (!valido.get(0).toString().equals(miterminal.get(0).toString())))
             return null;         
         return unico;
    }
   
   public TmsBoletosVentaTbl buscarFolioPreimpreso(String foliopreimpreso) {
       TmsBoletosVentaTbl bv = new TmsBoletosVentaTbl();
       bv = null;
       try{
         System.out.println("foliopreimpreso "+foliopreimpreso);
         //bv = (TmsBoletosVentaTbl) manager.createNamedQuery("TmsBoletosVentaTbl.findByFolioPreimpreso").setParameter("folioPreimpreso",foliopreimpreso).getSingleResult();

            bv = (TmsBoletosVentaTbl) ((Vector)manager.createNativeQuery("select * from tms_boletos_venta_tbl where folio_preimpreso='"+foliopreimpreso+"'" +
                    " OR (referencia_pago='"+foliopreimpreso+"' and tipo_operacion!='VT') " +
                    "ORDER by fecha_creacion DESC", TmsBoletosVentaTbl.class).getResultList()).get(0);
            System.out.println("bv "+bv);
            if(bv == null) return null;
            return bv;
       }catch(Exception e){
            e.printStackTrace();
       }
       return bv;
    }
   
   public String buscardblink(String folio_preimpreso, boolean ban){
       Vector temp = new Vector();
       System.out.println(" folio_preimpreso "+folio_preimpreso);
       if(ban)  //No es una venta por interntet
         temp =   (Vector) manager.createNativeQuery("SELECT NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE TERMINAL_ID = SUBSTR('"+folio_preimpreso+"',1,3)").getSingleResult();
       else {
            String web_link = manager.createNativeQuery("select nombre_dblink from TMS_BASE_DATOS_CONFIG_TBL where nombre_terminal = 'WEB'").getSingleResult().toString();
            web_link = web_link.replace('[', ' ');
            web_link = web_link.replace(']', ' ');
            web_link = web_link.trim();
            temp = (Vector) manager.createNativeQuery("SELECT NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE nombre_terminal = (SELECT origen FROM TMS_BOLETOS_VENTA_TBL@"+web_link+" WHERE folio_preimpreso = '"+folio_preimpreso+"')").getSingleResult();
       }
       return (String) temp.get(0);
   }
   
   public Vector buscarBoletoNombre(String nombre, String fechaHoraCorrida, String origen, String destino, String servicio, String fecha){              
       System.out.println("nombre "+nombre+" fechaHoraCorrida "+fechaHoraCorrida+" origen "+origen+" destino "+destino+ " servicio "+servicio +" fecha "+fecha);
       String central_link = manager.createNativeQuery("select nombre_dblink from TMS_BASE_DATOS_CONFIG_TBL where nombre_terminal = 'CENTRAL'").getSingleResult().toString();
       central_link = central_link.replace('[', ' ');
       central_link = central_link.replace(']', ' ');
       central_link = central_link.trim();
       Vector resultado = null;
       try {
           String query="SELECT bv.adicional6, bv.FOLIO_PREIMPRESO, bv.NOMBRE_PASAJERO, "+
                                   "bv.NO_ASIENTO, bv.TIPO_PAGO, " +
                                   "CASE bv.TIPO_PASAJERO WHEN 'A' THEN 'ADULTO' WHEN 'M' THEN 'MENOR' WHEN 'P' THEN 'PROFESOR' WHEN 'E' THEN 'ESTUDIANTE' WHEN 'S' THEN 'SENECTUD' WHEN 'V' THEN 'MENOR VOLARIS' WHEN 'C' THEN 'ESPECIAL' WHEN 'U' THEN 'ESTUDIANTE SMA' END tipoPasajero , " +                                   
                                   "to_char(bv.FECHA_HORA_VENTA, 'dd/mm/yyyy hh24:mi'), " +
                                   "bv.clave_corrida,"+
                                   "to_char(cv.FECHA_HORA_CORRIDA, 'dd/mm/yyyy hh24:mi'), "+
                                   "bv.ORIGEN, " +
                                   "bv.DESTINO, " +
                                   "bv.CAJA, " +
                                   "CASE bv.TIPO_OPERACION WHEN 'VT' THEN 'NORMAL' WHEN 'VA' THEN 'BOL ABIERTO' WHEN 'FO' THEN 'CAMBIO HORARIO' END "+
                                   "FROM "+
                                   //"TMS_BOLETOS_VENTA_TBL@"+central_link+" bv "+
                                   //",TMS_CORRIDAS_VENTA_TBL@"+central_link+" CV "+
                                   "TMS_BOLETOS_VENTA_TBL bv "+
                                   ",TMS_CORRIDAS_VENTA_TBL CV "+
                                   "where " +
                                   //"bv.caja = 'CAJAWEB' AND " +
                                   "UPPER(REPLACE(bv.nombre_pasajero, ' ')) LIKE UPPER('%"+nombre+"%') "+
                                   "AND bv.clave_corrida = CV.clave_corrida "+
                                   "AND (TRUNC(bv.fecha_creacion) = NVL("+fecha+",TRUNC(SYSDATE)) OR TRUNC(CV.fecha_hora_corrida) =  NVL("+fechaHoraCorrida+", TRUNC(SYSDATE))) "+
                                   "AND CV.ORIGEN LIKE '%"+origen+"%' "+
                                   "AND CV.destino LIKE '%"+destino+"%' "+
                                   "AND CV.servicio LIKE '%"+servicio+"%' ";
            System.out.println("Query: "+query);
            resultado = (Vector) manager.createNativeQuery(query).getResultList();
            System.out.println("resultado buscarBoletoNombre "+resultado);
       }catch(Exception ex) {
           ex.printStackTrace();
           resultado = null;
       }
       return resultado;
   }
   
    public Vector buscarTerminal(){
       Vector resultado = null;
       try {
        System.out.println("SELECT NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL where esquema_propio = 'S'");
        resultado = (Vector) manager.createNativeQuery("SELECT NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL where esquema_propio = 'S'").getSingleResult();
       }catch(Exception e){
           e.printStackTrace();
           resultado = null;
       }
       return resultado;
   }
   
   public Vector buscarOrigenesDestinos(){
       Vector resultado = null;
       try {
        System.out.println("select estado_nombre from tms_estados_tbl where tipo_componente = 'TERMINAL' and estado_nombre <> 'CENTRAL' ORDER BY estado_nombre");
        resultado = (Vector) manager.createNativeQuery("select estado_nombre from tms_estados_tbl where tipo_componente = 'TERMINAL' and estado_nombre <> 'CENTRAL' ORDER BY estado_nombre").getResultList();        
        Vector vectorsote = new Vector();
        for(int j = 0; j < resultado.size(); j++)     
            vectorsote.add(((Vector)resultado.get(j)).get(0).toString());        
        resultado = vectorsote;
        vectorsote.add(0, "TODOS...");
       }catch(Exception e){
           e.printStackTrace();
           resultado = null;
       }       
       return resultado;
   }
   
   public Vector buscarServicios(){
       Vector resultado = null;
       try {
           System.out.println("SELECT servicio_nombre FROM TMS_SERVICIOS_TBL");
        resultado = (Vector) manager.createNativeQuery("SELECT servicio_nombre FROM TMS_SERVICIOS_TBL ORDER BY servicio_nombre").getResultList();
       Vector vectorsote = new Vector();
        for(int j = 0; j < resultado.size(); j++)        
            vectorsote.add(((Vector)resultado.get(j)).get(0).toString());
        resultado = vectorsote;
        vectorsote.add(0, "TODOS...");
        }catch(Exception e){
           e.printStackTrace();
           resultado = null;
       }
       return resultado;
   }
   
   public String fecha(){
        String fecha = manager.createNativeQuery("select to_char(sysdate, 'dd/mm/yyyy') from dual").getSingleResult().toString();
        return fecha.subSequence(1, fecha.length()-1).toString();
    }

   public String getCorridaFH(String corrida){
           
       System.out.println(" getCorridaFH = select to_char(fecha_hora_corrida, 'dd/mm/yyyy hh24:mi') from tms_corridas_venta_tbl where clave_corrida='"+corrida+"'");
        String fh = manager.createNativeQuery("select to_char(fecha_hora_corrida, 'dd/mm/yyyy hh24:mi') from tms_corridas_venta_tbl" +
                " where clave_corrida='"+corrida+"'").getSingleResult().toString();
        return fh.subSequence(1, fh.length()-1).toString();
    }   
   
   public Vector  getFuncionesUsuario(String UsuarioID)
   {
       Vector VFunciones= new Vector();
       String query = " select distinct fun.FUNCION_NUMERO from " +
                  " tms_funciones_tbl 	  fun, " +
                  " tms_menus_lineas_tbl men, " +
                  " tms_usuario_perfiles_tbl	  usr, " +
                  " tms_perfiles_tbl   per  " +
                  " where   usr.usuario_id= '"+UsuarioID+"' " +
                  " and usr.perfil_id= per.perfil_id " +
                  " and     per.PERFIL_ID = per.PERFIL_ID  " +
                  " and     men.MENU_ID = per.MENU_ID  " +
                  " and 	  fun.FUNCION_ID = men.FUNCION_ID " +
               " order by fun.FUNCION_NUMERO" ;
                //" and     fun.FUNCION_NUMERO = '"+funcion"+' " ;   
       System.out.println("Query   " +query);
     VFunciones= (Vector) manager.createNativeQuery(query).getResultList();
     System.out.println("VFunciones   " +VFunciones);
    return  VFunciones;
   }

    public String esUsuarioSupervisor(String usuarioNumero,String pwdEnc, String funcion){
        String respuesta = "";
        Vector resultado;
        Vector usrfuncion;
        String consultaUsuario;
        String consultaUsuarioFuncion;
        consultaUsuario = "select usr.USUARIO_ID from tms_usuarios_tbl usr where usr.USUARIO_NUMERO = '"+usuarioNumero+"' and usr.CONTRASENA_ENCRIPTADA = '"+pwdEnc+"' AND (usr.FECHA_FINAL IS NULL OR usr.FECHA_FINAL > SYSDATE) ";

        usuarioNumero="8607";
        funcion="5001";
        consultaUsuarioFuncion = "select distinct usr.USUARIO_ID from " +
                "tms_funciones_tbl 		  fun," +
                "tms_menus_lineas_tbl             men, " +
                "tms_usuario_perfiles_tbl         usrper, " +
                "tms_perfiles_tbl		  per, " +
                "tms_usuarios_tbl                 usr  " +
                "where   usr.USUARIO_NUMERO = '"+usuarioNumero+"' AND usrper.USUARIO_ID = usr.USUARIO_ID " +
                "and     per.PERFIL_ID = usrper.PERFIL_ID " +
                "and     men.MENU_ID = per.MENU_ID  " +
                "and 	fun.FUNCION_ID = men.FUNCION_ID " +
                "and     fun.FUNCION_NUMERO = '"+funcion+"'";

        
        /*consultaUsuarioFuncion = "select usr.USUARIO_ID from " +
                "tms_funciones_tbl 		  fun  " +
                ",tms_menus_encabezado_tbl men  " +
                ",tms_usuarios_tbl		  usr  " +
                ",tms_perfiles_tbl		  per  " +
                ",tms_usuarios_perfiles_tbl usrper  " +
                //",tms_perfiles_funciones_tbl perfun  " +//revisar
                "where   usr.USUARIO_NUMERO = '"+usuarioNumero+"'  " +
                "and     usrper.USUARIO_ID = usr.USUARIO_ID  " +
                "and     per.PERFIL_ID = usrper.PERFIL_ID  " +
                "and     men.MENU_ID = per.MENU_ID  " +
               // "and     perfun.PERFIL_ID = per.PERFIL_ID " +
                "and 	fun.FUNCION_ID = perfun.FUNCION_ID " +
                "and     fun.FUNCION_NUMERO = '"+funcion+"' ";
        
        */

            resultado =(Vector) manager.createNativeQuery(consultaUsuario).getResultList();
        if(resultado.size()==0)
            respuesta = "no encontrado";
        else
        {
             usrfuncion = (Vector) manager.createNativeQuery(consultaUsuarioFuncion).getResultList();
             if(usrfuncion.size()==0)
               respuesta = "invalido";
             else
             {
                 Vector vre = (Vector)usrfuncion.get(0);
                 respuesta = vre.get(0).toString();
             }
        }
    return respuesta;
    }        
       
    public int registra(BigDecimal idBoleto, String corridaCve, String usuarioID, String estado, String fecha){
        try{
String consulta="select count(1) from tms_boleto_abordado_tbl where boleto_id="+idBoleto;
System.out.println("Consulta de registro: "+consulta);
String l=((List)manager.createNativeQuery(consulta).getSingleResult()).get(0).toString();
        
System.out.println("lista: "+l);
if(l.equals("0")){
System.out.println("Se procede a registrar ABORDO!!!");            
String update="INSERT INTO TMS_BOLETO_ABORDADO_TBL " +
        "VALUES(TMS_BOLETO_ABORDADO_SEQ.NEXTVAL, "+idBoleto.toString()+", " +
        "(select corrida_id from tms_corridas_venta_tbl where clave_corrida='"+corridaCve+"'), " +
        "'"+estado+"', NULL, to_date('"+fecha+"', 'dd/MM/RRRR HH24:mi'), NULL, NULL, NULL, NULL, NULL, NULL, " +
        "SYSDATE, "+usuarioID+", SYSDATE, "+usuarioID+")";
        
        int resultado =manager.createNativeQuery(update).executeUpdate();
    }
else if(l==null || l.equals("null")) return -1; //Error
    else return 1;      //Elemento registrado

        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
    
    public int getPersonasAbordo(String corridaCve){
    try{
    String consulta="select count(1) from tms_boleto_abordado_tbl "+
	   "where corrida_id=(select corrida_id from tms_corridas_venta_tbl where clave_corrida='"+corridaCve+"')";
    
    System.out.println("Pasajeros Abordo: "+consulta);
    String l=((List)manager.createNativeQuery(consulta).getSingleResult()).get(0).toString();
    return Integer.parseInt(l);
    
    }catch(Exception e){
        e.printStackTrace();
        return -1;
    }
   }

 

   
public Vector getAsientosDisponibles(String IDCorrida)
{// 
    Vector VAsientosD = new Vector();
    try {  // 
    
     String query="Select P.CAPACIDAD_ASIENTOS  "+
                " FROM "+tableCorridasVenta+ " CORRIDAS ,"+tableAutobusesPlantilla+" P "+
                " WHERE CORRIDAS.CORRIDA_ID='"+IDCorrida+"' AND  P.PLANTILLA_ENC_ID = CORRIDAS.PLANTILLA_ID  ";
     System.out.println("query "+query);
     String nasientos= ((Vector)manager.createNativeQuery(query).getSingleResult()).elementAt(0).toString();
    
     System.out.println("nasientos "+nasientos);
     
    if(nasientos != null && nasientos.length()>0)
    {
       int nasien = Integer.parseInt(nasientos);
       query ="Select *  "+
              " FROM "+tableCorridasVenta+ 
              " WHERE CORRIDA_ID='"+IDCorrida+"'";
       System.out.println("query "+query);
       List<Vector<ResultSet>>  lres=manager.createNativeQuery(query).getResultList();
       System.out.println("query "+lres.get(0));
         
         Vector vresul=(Vector)lres.get(0);
         for (int i = 21; i < 19+(nasien-1); i++) {
         //  System.out.println("Asiento   "+i+"  "+vresul.elementAt(i));     
           if(vresul.elementAt(i).toString().trim().equalsIgnoreCase("D"))
               VAsientosD.addElement(i);
         }
   
       //
    } // if
      System.out.println("Asientos Disponibles "+VAsientosD);
    return VAsientosD;  
    } catch (Exception e) {
      return null;  
    }
    //
}  // funcion


    public Vector getCortePendiente(String usuario_id){
        Vector corte = new Vector();
        try {
        Vector v = new Vector();    
         String query="SELECT C.CORTE_ID, DECODE(C.ESTADO_CORTE,'P',1,'E',0) "+
                    "FROM TMS_CORTES_TBL C " +
                    "WHERE C.USUARIO_ID = " +usuario_id+
                    " AND C.ESTADO_CORTE IN ('P','E') " +
                        "ORDER BY C.CORTE_ID ASC";
         System.out.println("query "+query);
         v= (Vector)manager.createNativeQuery(query).getResultList();

        if(v==null || v.size()==0)
            return null;

         corte=(Vector) v.get(0);
         System.out.println("Vector corte "+corte);
         return corte;

        } catch (Exception e) {
            e.printStackTrace();
          return null;  
        }
      }

    

    
}
