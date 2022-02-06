/*
 * DoTransation.java
 *
 * Created on 29 de diciembre de 2009, 10:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslealtad_carga;

import Datos.Puntos;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class DoTransation   implements Runnable{
    //@WebServiceRef(wsdlLocation = "http://www.tricomp.com.mx/testing/ws/enterprise.asmx?WSDL")
    
    
    wsTricomp.Estrellaroja service = new wsTricomp.Estrellaroja();
    wsTricomp.EstrellarojaSoap port = service.getEstrellarojaSoap();
    
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Thread hilocarga = null;
    int timePeriod =0;
    int minutos    =0;
    boolean ftransaccion =false;
    public  String strContenedor = "";
    public boolean ftomainf =false;
    private String  RegTrans="REGISTRA TRANSACCION";
    private String  RegTransBatch="REGISTRA TRANSACCION_BATCH";
    private String  RegDetTrans="REGISTRA DETALLE TRANSACCION";
    private String  RegDetTransBatch="REGISTRA DETALLE TRANSACCION_BATCH";
    private String  BusqInf="BUSQUEDA INFORMACION";
    private String  BusqInfBatch="BUSQUEDA INFORMACION_BATCH";
    private String  CierraTrans ="CIERRA TRANSACCION";
    private String  CierraTransBatch ="CIERRA TRANSACCION_BATCH";
    private String  NumTraExc="NUMERO TRANSACCIONES EXCEDIDAS_BATCH";
    private String  NumTarNoexiste = "NO. TARJETA NO EXISTE_BATCH";
    private boolean LEALTADWSBD = false; //es un parametro global, si es ta en S entonces usa el WS y si esta en N entonces usa el procedieminto del chucho, 
                                      //si no esta configurado el parametro  entonces se toma por default S
    
    
    /** Creates a new instance of DoTransation */
    public DoTransation(String  contenedor) {
        Settings.lookupGenerico(); 
        String time=Settings.LealtadRemote.find_Parametro("P_LEALTAD_TIMEBATCH");
        
       // Settings.LealtadRemote.UpdateBloqueados();
        System.out.println("Desbloquear, bloqueados");
        if (time== null || time.length()==0)  {
            System.out.println("No existe el parametro P_LEALTAD_TIMEBATCH ");
            return;                           }
        else
           minutos=Integer.parseInt( time );
        
        String INVLEALTADWS =Settings.LealtadRemote.find_Parametro("P_INVLEALTADWS");
        
        System.out.println("Minutos Thread "+minutos);
        
        if (INVLEALTADWS.equalsIgnoreCase("N"))
            LEALTADWSBD=true;  
        
        System.out.println("LEALTADWSBD -- >>   "+ LEALTADWSBD);
    }
    
    public void  termina()
    {
       if (hilocarga != null)
        {
           System.out.println("termina el hilo");
           hilocarga = null;
        }
    }
    
    
    // Trae todas las operaciones pendientes por procesar 
     public  void GetOperaciones()
    {
       strContenedor =""; 
       String stroperacion = "";
       Vector AcumulPendientes = Settings.LealtadRemote.TMSAcumulacionesPendientes(); //TMSOperacionesPendientes();
       Vector voperacion = null;
       System.out.println("Acumulaciones Pendientes "+AcumulPendientes);
       ftomainf =true;
       if(AcumulPendientes != null  && AcumulPendientes.size() > 0){
           ftomainf = false;
           for (int i = 0; i < AcumulPendientes.size(); i++) {
               stroperacion = ((Vector)AcumulPendientes.elementAt(i)).elementAt(0).toString().trim();
               System.out.println(i+"              Oper Pendientes "+stroperacion);
              // SentAcumulaciones(stroperacion);    // Realiza el detalle para una operacion con todos sus registros 
              if (!LEALTADWSBD)
                 { 
                   System.out.println("Proceso Normal");  
                   SentAcumulaXUnaFunc(stroperacion);}  
              else
              {
               System.out.println("Llama Web services desde la BD");
               List<String> respuestaLealtad = Settings.LealtadRemote.REGISTRA_TRANSACCION_XM(stroperacion);
               strContenedor=strContenedor+ "Operación   "+stroperacion+"\n"+respuestaLealtad.get(1).replaceAll("\\\\n\\\\r","  ") ;
               System.out.println("respuestaLealtad -- >  "+respuestaLealtad.get(1).toString());
                 
               
              }
              }// for
        System.out.println("  ******** Termino Todos las Acumulaciones Pendientes *******  ");
        EnviaCancelaciones();   
        System.out.println("  ******** Termino Todos las Cancelaciones Pendientes *******  ");  
           
        try {
               
               hilocarga.sleep(timePeriod);
               
           } catch (Exception e) {
             e.printStackTrace();   
           }
            
       }
       else { ftomainf =false;  
              try {
               hilocarga.sleep(timePeriod);
           } catch (Exception e) {
             e.printStackTrace();   
           }
       }
       ftomainf =false;
    }
     
     
     //********************************  Lamado a Una sola funcion
     
    public void SentAcumulaXUnaFunc(String numeroOperacion)
    {
      Vector VRegsOperacion = Settings.LealtadRemote.TMSConsultaPorNumeroOperacion(numeroOperacion);
     
      System.out.println(" VRegsOperacion " + VRegsOperacion);
      Vector VRegistro = null;
      Date fecha = null;  
      String tramaRequest = "";
      String notarjeta="", sucursal ="", usuario="", TipoTransaccion="", total="", totalrendimiento="0", taquilla="",  UsrKey="", producto="", precio ="";
      String folio ="",  UnidadNeg = "";
      javax.xml.datatype.XMLGregorianCalendar fechaa = null;
      System.out.println(" ---------------- Inicia Acumulacion de  operacion ---------------- "+numeroOperacion);
      
      if (VRegsOperacion != null && VRegsOperacion.size() > 0)
      {
        if(!VerificaTarjetas( VRegsOperacion, numeroOperacion))
                  return;  
            
         for(int k = 0; k < VRegsOperacion.size(); k++){  
             VRegistro = (Vector) VRegsOperacion.get(k);
              if(k == 0){
                    try{
                        fecha = df.parse(VRegistro.get(9).toString());
                        fechaa = getDate(fecha);
                        System.out.println("fechita  "+fechaa);                        
                    }catch(Exception e){
                        System.out.println("Al intentar hacer el cambio de fecha gregoriana");
                        e.printStackTrace();
                        //status.setSuccess(false);
                        //status.setMessage(status.getMsg(1,e.getMessage()));
                        //status.setCode(1);
                       // operacionesResponse.setStatus(status);
                       // operacionesResponse.setPuntos(respuestaPuntos);
                        //return operacionesResponse;
                        return ;
                    }  
                    notarjeta = VRegistro.get(3).toString();     //Numeto Tarjeta
                    sucursal  = VRegistro.get(2).toString();     //  ciudad venta
                    usuario   = VRegistro.get(5).toString();     //Usuario
                    TipoTransaccion = VRegistro.get(4).toString();   //Tipo Operacion
                    total     = VRegistro.get(8).toString();
                    taquilla  = VRegistro.get(10).toString();  // Caja
                    UsrKey    = VRegistro.get(6).toString();
                    
                    System.out.println("No Tarjeta "+notarjeta+"\n"
                                +"Ticket "+numeroOperacion+" \n"
                                +"Sucural "+sucursal+"\n"
                                +"Usuario "+usuario+"\n"
                                +"Fecha "+fecha+"\n"
                                +"TipoTransaccion "+TipoTransaccion+"\n"
                                +"Total "+total+"\n"
                                +"Descuento 0\n"
                                +"Taquilla "+taquilla+"\n"
                                +"UsrKey "+UsrKey+"\n"
                                );
                    tramaRequest = "<?xml version="+"\""+"1.0"+"\""+"?> \n"+
                                   "<Transaccion notarjeta="+"\""+notarjeta+"\""+
                                   " ticket="+"\""+numeroOperacion+"\""+
                                   " sucursal="+"\""+sucursal+"\""+
                                   " usuario="+"\""+usuario+"\""+
                                   " fecha="+"\""+fechaa.toString()+"\""+
                                   " tipotransaccion="+"\""+TipoTransaccion+"\""+
                                   " total="+"\""+total+"\""+
                                   " totalrendimiento="+"\""+totalrendimiento+"\""+  
                                   " descuento="+"\""+"0"+"\""+
                                   " taquilla="+"\""+taquilla+"\""+
                                   " usrkey="+"\""+UsrKey+"\""+">";
                }
                    //
                  producto = VRegistro.get(1).toString();
                  precio   = VRegistro.get(7).toString();
                  folio    = VRegistro.get(0).toString();
                  UnidadNeg= VRegistro.get(11).toString();
                  System.out.println("Producto "+producto+" \n"                                
                                +"Cantidad 1\n"
                                +"Precio "+precio+"\n"
                                +"Descuento 0\n"
                                +"Folio Boleto "+folio+"\n"
                                +"Unidad Negocio "+UnidadNeg);
                
                tramaRequest = tramaRequest+"\n <ProductoXTransaccion producto="+"\""+producto+"\""+
                               " cantidad="+"\"1"+"\""+
                               " precio="+"\""+precio+"\""+
                               " descuento="+"\"0"+"\""+
                               " folio="+"\""+folio+"\""+
                               " unidad_negocio="+"\""+UnidadNeg+"\" />";
         }// for
            
        
            tramaRequest = tramaRequest +"\n </Transaccion>";
            System.out.println("tramaRequest "+tramaRequest);
            String resultRegistraTransaccionXMLStr = "";
            Vector vecDesJSon = null;
            Puntos respuestaPuntos=null;
            String respuestaStatus = "";
            int  status= 0;
           try {
               System.out.print("tramaRequest  que se envia "+tramaRequest);
                resultRegistraTransaccionXMLStr = port.registraTransaccionXMLStr(tramaRequest);
                System.out.println("------------> resultRegistraTransaccionXMLStr = "+resultRegistraTransaccionXMLStr);                
                vecDesJSon = desJSonearString(resultRegistraTransaccionXMLStr, numeroOperacion);
                System.out.println("VecDesJSon "+vecDesJSon);
              //  status.setSuccess(Boolean.parseBoolean(vecDesJSon.get(0).toString()));
               // status.setMessage(status.getMsg(0,""));
                boolean statuss = Boolean.parseBoolean(vecDesJSon.get(0).toString());
                if(statuss){
                    respuestaPuntos = (Puntos) vecDesJSon.get(1);
                    respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "R", "0", CierraTransBatch);
                    System.out.println("respuestaStatus "+respuestaStatus);
                   
                    strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+" La Acumulacion de la Operacion operación "+ numeroOperacion+ "  Se ha realizado correctamente " + " Para No. Tarjeta "+notarjeta;
                    strContenedor=strContenedor+"\n  Puntos Otorgados "+respuestaPuntos.getPuntosOtorgados()+ " Saldo Moneda  "+respuestaPuntos.getSaldoMoneda() +"Saldo Puntos  "+respuestaPuntos.getSaldoPuntos() ;
                    System.out.println("Puntos Otorgados "+respuestaPuntos.getPuntosOtorgados() );
                    System.out.println("Saldo Moneda  "+respuestaPuntos.getSaldoMoneda() );
                    System.out.println("Saldo Puntos  "+respuestaPuntos.getSaldoPuntos() );
                }
                else{                    
                 //   status.setMessage(status.getMsg(Integer.parseInt(vecDesJSon.get(1).toString()), vecDesJSon.get(2).toString()));   
                    status = Integer.parseInt( vecDesJSon.get(1).toString() );
                    if(status == 15) {
                        respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion,"P", "0", NumTarNoexiste);
                        strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+" No se pudo hacer La Acumulacion de la  operación NO. TARJETA NO EXISTE para la operacion  "+ numeroOperacion+ " Y No. Tarjeta "+notarjeta;
                        System.out.println("respuestaStatus "+respuestaStatus);
                    }
                   else if(status  == 14) {
                            respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion,"F", "0", NumTraExc);
                             strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+" No se pudo hacer La Acumulacion debido a  NUMERO TRANSACCIONES EXCEDIDAS para la operacion  "+ numeroOperacion + " Y No. Tarjeta "+notarjeta;;
                      
                            System.out.println("respuestaStatus "+respuestaStatus);  }
                   else{
                            respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion,"P", "0", BusqInfBatch);
                            System.out.println("respuestaStatus "+respuestaStatus);
                            strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+" No se pudo hacer La Acumulacion de la  operación "+ numeroOperacion+ " Y No. Tarjeta "+notarjeta;
                       }
                  
               System.out.println(" ---------------- Termina Acumulaciom de operacion ---------------- "+numeroOperacion);
                      //  return operacionesResponse; 
               }   
             
            
        } catch (Exception ex) {
                System.out.println("Excepcion en registraTransaccionXMLStr");
                ex.printStackTrace();
                        
              //  mensajeError = ex.getMessage();
            //    status.setMessage(status.getMsg(1,""));
            }       
       
       
     
      } // if  VRegsOperacion!= null
    }
     
    //*******************************
     
     
     
 public Vector UpdateStatus(Vector vdataOperacion, String statusnew, String transacion )
 {
       vdataOperacion.setElementAt(statusnew,12);
       if (!transacion.equalsIgnoreCase("0"))
              vdataOperacion.setElementAt(transacion,13);
    
    System.out.println("Saliendo de UpdateStatus "+vdataOperacion);
    return vdataOperacion;
 }
    
 protected XMLGregorianCalendar getDate(java.util.Date date) {
  try {
        if (date == null) return null ;
        GregorianCalendar cal = new GregorianCalendar() ;
        cal.setTime(date) ;
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal) ;
        
	} catch (DatatypeConfigurationException e) {            
            System.out.println("al hacer el XMLGregorianCalendar");
	    throw new java.lang.Error(e);
	}
  }    
 
    // Registra la transacccion de para los registros con dicha operacion 
  public long  RegistraTransaccion(String numOperacion, Vector Registro )
   {  // 
       Date fecha = null;
       long transaccion = 0;
       double totalTransaccion=0;
       ftransaccion= false;
        try {
                try{
                    fecha = df.parse(Registro.get(9).toString());
                }catch(Exception e){
                    System.out.println("Al intentar hacer el cambio de fecha gregoriana");
                    e.printStackTrace();
                    return 0;
                }
                System.out.println(" ---------------- Inicia registraTransaccion ---------------- numOperacion "+numOperacion);
              /*  System.out.println("No Tarjeta "+Registro.get(3).toString()+"\n"
                        +"Ticket "+numOperacion+" \n"
                        +"Sucural "+Registro.get(2).toString()+"\n"
                        +"Usuario "+Registro.get(5).toString()+"\n"
                        +"Fecha "+fecha+"\n"
                        +"TipoTransaccion "+Registro.get(4).toString()+"\n"
                        +"Total "+Registro.get(8).toString()+"\n"
                        +"Descuento 0\n"
                        +"Taquilla "+Registro.get(10).toString()+"\n"
                        +"UsrKey "+Registro.get(6).toString()+"\n"
                        );*/
                totalTransaccion = Double.parseDouble(Registro.get(8).toString());
                
                transaccion = port.registraTransaccion(Registro.get(3).toString(), numOperacion, Long.parseLong(Registro.get(2).toString()), 
                        Long.parseLong(Registro.get(5).toString()), getDate(fecha), Long.parseLong(Registro.get(4).toString()), Double.parseDouble(Registro.get(8).toString()), 0.0,
                        Registro.get(10).toString(), Registro.get(6).toString());
                // Bloquea la operacion para iniciar el registro detalle de cada una de ellas
                Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "B", String.valueOf(transaccion), RegTransBatch);
                System.out.println(" Despues de Registrar  ----- transaccion = "+transaccion);
           
                if(transaccion == -2) {
                    System.out.println("El método de Transaccion Falló!");
                    Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "P", String.valueOf(transaccion), BusqInfBatch);
                    strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"   No se pudo hacer el registro de la transaccción para la operación "+ numOperacion;
                  
                    return transaccion;
                }
                else if(transaccion == -1){
                    System.out.println("El método de Transaccion Falló por NUMERO TRANSACCIONES EXCEDIDAS!");
                    Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "F", String.valueOf(transaccion), "NUMERO TRANSACCIONES EXCEDIDAS_BATCH");
                    strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"   No se pudo hacer el registro de la transaccción por exceso de operacion "+ numOperacion;
                  
                    return transaccion;
                }
                else if(transaccion == 0){
                        System.out.println("Se intentó duplicar información ");
                        Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "P", String.valueOf(transaccion), BusqInfBatch);
                        strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"   Se intento duplicar el registro de la transaccción para la operación ";
                        return transaccion;
                 }  else if (transaccion > 0) {
                          ftransaccion = true;
                          System.out.println("Se creo la transaccion correctamente ");
                          }
            } catch (Exception ex) {  
                System.out.println("Registra Transaccion al realizar registraTransaccion");
                ex.printStackTrace();
                Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "P", String.valueOf(transaccion), BusqInfBatch);
                return transaccion;
            }
     return transaccion;
   }
   
  // Cierra la transaacion con la que se hacen la operacion
  public Puntos CierraTransccion(Vector Registro, long transaccion, String numOperacion, Puntos respPuntos)
  {
     String resultadoCierraTransaccionStr;
     StringTokenizer token = null;
     StringTokenizer token1 = null;
     String   respuestaStatus = "";
     Vector saldos = null; 
     String operacion  = Registro.get(4).toString().trim();
     double totalTransaccion = 0;
      if(operacion.equals("1")) {
          totalTransaccion =  Double.parseDouble(Registro.get(8).toString());
           System.out.println(" ---------------- Inicia cierraTransaccionStr ---------------- "+transaccion);
           System.out.println("Transaccion "+transaccion+"\n"                                    
                        +"Total        "+totalTransaccion+"\n"                                
                        +"Descuento 0\n"
                        +"Usuario      "+Registro.get(5).toString()+"\n"
                        +"UsrKey       "+Registro.get(6).toString()+"\n");
            try {
                resultadoCierraTransaccionStr = port.cierraTransaccionStr(transaccion,  totalTransaccion, 0.0, Long.parseLong(Registro.get(5).toString()), Registro.get(6).toString());
                System.out.println("resultadoCierraTransaccionStr = "+resultadoCierraTransaccionStr);                                                        
                
                if(!resultadoCierraTransaccionStr.equals("No se pudo cerrar la transacción") ){
                    Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "R", String.valueOf(transaccion),CierraTransBatch);            
                     strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"   Se hizo el otorgamiento de la operación "+ numOperacion;
                   // 
                   token1 = new StringTokenizer(resultadoCierraTransaccionStr,"\n");
                   saldos = new Vector();
                    while(token1.hasMoreTokens()){
                        token = new StringTokenizer(token1.nextToken(),":");
                        while(token.hasMoreTokens())
                            saldos.add(token.nextToken());
                    }
                    respPuntos.setPuntosOtorgados(saldos.get(1).toString().substring(1));
                    respPuntos.setSaldoPuntos(saldos.get(3).toString().substring(1));
                    respPuntos.setSaldoMoneda(saldos.get(5).toString().substring(1));
                    
                   strContenedor = strContenedor+ "\n CON: Puntos Otorgados "+respPuntos.getPuntosOtorgados() ;
                   strContenedor = strContenedor+ "\n Saldo Moneda  "+respPuntos.getSaldoMoneda() ;
                   strContenedor = strContenedor+ "\n Saldo Puntos  "+respPuntos.getSaldoPuntos() ;
                  }
                else if(resultadoCierraTransaccionStr.equals("No se pudo cerrar la transacción")){
                     respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "P", String.valueOf(transaccion), RegDetTransBatch);
                     strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"No se pudo cerrar la transacción para la operación "+ numOperacion;
                       
                     return respPuntos;
                }
              /*  else if (resultadoCierraTransaccionStr.equals("La tarjeta ha excedido")){
                       respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "F", "0", "NUMERO TRANSACCIONES EXCEDIDAS");
                     return respPuntos;
                }  */
            } catch (Exception ex) {
                System.out.println("Cierra Transaccion Str Exception");  
                ex.printStackTrace();
                respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "P", String.valueOf(transaccion),RegTransBatch);
                return respPuntos;
            }
        } /*else if(operacion.equals("C")){  // Cierre de transaciion cuando es una cancelacion
            System.out.println(" ---------------- Inicia Cancelacion ---------------- ");
            System.out.println("Transaccion "+transaccion+"\n"                                   
                        +"Total "+Registro.get(8).toString()+"\n"                                
                        +"Descuento 0\n"
                        +"Usuario "+Registro.get(5).toString()+"\n"
                        +"UsrKey "+Registro.get(6).toString()+"\n");
            try {
                boolean resultadoCierraTransaccion = port.cierraTransaccion(transaccion,totalTransaccion, 0.0, Long.parseLong(Registro.get(5).toString()), Registro.get(6).toString());
                System.out.println("resultadoCierraTransaccion = "+resultadoCierraTransaccion);
                  if(!resultadoCierraTransaccion) {
                      respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "P", String.valueOf(transaccion), RegDetTransBatch);
                      return respPuntos;
                }                            
                  else
                          respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "R", String.valueOf(transaccion),CierraTransBatch);
            } catch (Exception ex) {
               System.out.println("Cierra Transaccion Exception");
                ex.printStackTrace();
                respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numOperacion, "P", String.valueOf(transaccion), RegDetTransBatch);
                 return respPuntos;
            }
          }*/
               return respPuntos;
       }
  
  public boolean VerificaTarjetas(Vector VAcumu, String NumOper)
  {
    boolean ftarjetaok =false;
    String  tarjeta = "";
  //  System.out.println(" DAtos de operacion "+VAcumu);
    Vector  Voper = null;
      for (int t = 0; t < VAcumu.size(); t++) {
        Voper= (Vector)VAcumu.elementAt(t);
          tarjeta = Voper.elementAt(3).toString().trim();   
          if (tarjeta.length() == 10  ) 
               ftarjetaok =true;
      }
    if (!ftarjetaok){
          System.out.println("El numero de tarjeta no contienen 10 digitos "+tarjeta);
          Settings.LealtadRemote.TMSLealtadCambiaStatus(NumOper, "F","", "No tarjeta Erroneo_Batch");
          strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"   No se pudo hacer la Acumulacion de la  operación "+ NumOper+"  por No. Tarjeta Incorrecto "+tarjeta;
          
    }
     return ftarjetaok;
  }
  
    public void SentAcumulaciones(String numeroOperacion)
    {
     long transaccion = 0;
     Date fecha = null;
     Puntos respuestaPuntos = new Puntos(); 
     Vector saldos = null;
     String respuestaStatus = "";
     String  ProcesoRealizado ="";
     String  TipoOperacion  = "";  // Anular  = A  ,  Otorgar = 1 
     Vector VAcumulacion = null;  
     try {
         // Trae los registros de la operacion (la coleccion correspondiente a ese numero de operacion)
          Vector VRegsOperacion = Settings.LealtadRemote.TMSConsultaPorNumeroOperacion(numeroOperacion);
       
         // Registra transaccion
        // ftransaccion=true;    
         
         if (VRegsOperacion != null && VRegsOperacion.size() > 0)
         {
              //  Verifica Tarjetas
              if(!VerificaTarjetas( VRegsOperacion, numeroOperacion))
                  return;   // Las tarjetas no son validas para esa operacion
              
            VAcumulacion = (Vector)VRegsOperacion.elementAt(0);    
            System.out.println("*******  Registrando Operacion transaccion************ ");
           
            transaccion = Long.parseLong( VAcumulacion.get(13).toString().trim() );  // Transaccion
            TipoOperacion       = VAcumulacion.get(4).toString().trim();                // operacion
            ProcesoRealizado   =  VAcumulacion.get(12).toString().trim();            // Proceso Realizado
                      
            //   REGISTRA Transacccion
            System.out.println("*******  Registrando Operacion transaccion  "+transaccion+"   operacion "+numeroOperacion);
            if(  transaccion <= 0  && TipoOperacion.equals("1") ){
                 transaccion=RegistraTransaccion(numeroOperacion,VAcumulacion);  
                 
                  if( transaccion > 0)
                      ProcesoRealizado=RegTransBatch;
                  System.out.println(" *** END  Registra Operacion  "+transaccion+"   operacion "+numeroOperacion+" statusoperacion"+ProcesoRealizado);
                
                    /*for(int k = 0; k < VRegsOperacion.size(); k++){  
                      if ( ((Vector)VRegsOperacion.elementAt(k)).elementAt(4).toString().trim().equals("1"))
                     VRegsOperacion.setElementAt(UpdateStatus((Vector)VRegsOperacion.elementAt(k),RegTransBatch,transaccion+""),k);
                    }*/
                }           //   END REGISTRA Transacccion
       } // END 
        
         // Realiza detalle de todos los registros de la operacion 
     long result = 0;   
     String Producto ="", Cantidad ="1" , Precio ="";
     String Descuento ="0", Folio ="", UnidadNegoc="", Usuario="", UserKey="";
     
     for(int k = 0; k < VRegsOperacion.size(); k++){  
            VAcumulacion = (Vector) VRegsOperacion.get(k);
            //  Toma los datos  asignado a cada uno de los registros    
            ProcesoRealizado = VAcumulacion.get(12).toString().trim();  // get status de operacion
            TipoOperacion    = VAcumulacion.get(4).toString().trim();
           // CHECAR  transaccion = !ftransaccion ? Long.parseLong( unRegistro.get(13).toString().trim() ) : transaccion;
            //System.out.println("transaccion "+transaccion +" Operacion "+operacion+"  numeroOperacion "+numeroOperacion+"  statusoperacion "+statusoperacion);
            System.out.println(" *** Transacion "+transaccion);
            System.out.println("Status  "+ProcesoRealizado+"  transaccion "+transaccion);
            if (transaccion > 0  &&  (   ProcesoRealizado.equalsIgnoreCase(RegTransBatch) 
                                              || ProcesoRealizado.equalsIgnoreCase(RegTrans)
                                              || ProcesoRealizado.trim().length() == 0 )   ){
             try {
                 Producto = VAcumulacion.get(1).toString().trim();
                 Precio   = VAcumulacion.get(7).toString().trim();
                 Folio    = VAcumulacion.get(0).toString().trim();
                 UnidadNegoc=VAcumulacion.get(11).toString().trim();
                 Usuario  =VAcumulacion.get(5).toString().trim();
                 UserKey  =VAcumulacion.get(6).toString().trim();
                 
                  System.out.println("---  Entrando al Detalle de Operacion   Transaccion "+transaccion+"\n"
                                +"Producto "+ Producto+" \n"     +"Cantidad 1\n"
                                +"Precio "+Precio+"\n"   +"Descuento "+Descuento +"\n"
                                +"Folio Boleto "+Folio+"\n"   +"Unidad Negocio "+UnidadNegoc+"\n"
                                +"Usuario "+Usuario+"\n"      +"UsrKey "+UserKey+"\n");
                 // result = port.registraDetalleTransaccion(transaccion, unRegistro.get(1).toString(), 1, Double.parseDouble(unRegistro.get(7).toString()), 0.0, unRegistro.get(0).toString(), unRegistro.get(11).toString(), Long.parseLong(unRegistro.get(5).toString()), unRegistro.get(6).toString());
                  result = port.registraDetalleTransaccion(transaccion, Producto, 1, Double.parseDouble(Precio), 0.0, 
                                Folio, UnidadNegoc, Long.parseLong(Usuario), UserKey);
                  System.out.println("*****  Result del Registra Detalle  ---->   "+result + "   Para operacion  " +numeroOperacion  );

                 if(result < 0) {
                        System.out.println("El método de Detalle Transaccion Falló! con el folio "+Folio);                        
                        Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "P", String.valueOf(transaccion), RegTransBatch);
                        strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"   No se pudo hacer el Detalle Transacción de la operación "+ numeroOperacion+ "  Para el folio de boleto "+ Folio;
                                }
                 else{ Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "B", String.valueOf(transaccion), RegDetTransBatch);
                       //VRegsOperacion=UpdateStatus(VRegsOperacion,RegDetTransBatch,"0");
                        System.out.println("Antes de Update vector "+VRegsOperacion);
                        VRegsOperacion.setElementAt(UpdateStatus((Vector)VRegsOperacion.elementAt(k),RegDetTransBatch,"0"),k);
                        System.out.println("Despues  de Update vector "+VRegsOperacion);
                        ProcesoRealizado=RegDetTransBatch;
                 }
                              
                       } catch (Exception ex) {
                                System.out.println("Registra Detalle Transaccion al realizar registraDetalleTransaccion");
                               ex.printStackTrace();
                               Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "P", String.valueOf(transaccion), RegTransBatch);
                            }  
             } // if (transation > 0 )
              
         } // for     
   
       // Cierra Transaccion si  se abrio y hubo registros a guardar
          
        if ( transaccion > 0 && VRegsOperacion != null && VRegsOperacion.size()  > 0  )
           // statusoperacion =((Vector) VRegsOperacion.get(0)).elementAt(12).toString().trim();
             
           if(ProcesoRealizado.equalsIgnoreCase(RegDetTransBatch) || ProcesoRealizado.equalsIgnoreCase(RegDetTrans) )  {
            System.out.println("****************  Cerrando Transaccion **************** Con Procso Realizado  "+ProcesoRealizado);
            respuestaPuntos = CierraTransccion( (Vector)VRegsOperacion.lastElement(),
                                            transaccion, numeroOperacion,  respuestaPuntos);
         
            System.out.println("Puntos Otorgados "+respuestaPuntos.getPuntosOtorgados() );
            System.out.println("Saldo Moneda  "+respuestaPuntos.getSaldoMoneda() );
            System.out.println("Saldo Puntos  "+respuestaPuntos.getSaldoPuntos() );
        
            }  
           
       } catch (Exception e) {
         Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "P", String.valueOf(transaccion), "");
         e.printStackTrace();
     }    
      
    }
     
 
  public void EnviaCancelaciones()
  {
       Vector Vcancelaciones = Settings.LealtadRemote.TMSCancelacionesPendientes();
       Vector Vcancelacion = null;
       String numeroOperacion ="";
       String FolioBoleto = "";
       String Usuario ="";
       String UsrKey ="";
       for (int i = 0; i < Vcancelaciones.size(); i++) {
           Vcancelacion = (Vector)Vcancelaciones.elementAt(i);   
           numeroOperacion =Vcancelacion.get(0).toString().trim();
           FolioBoleto = Vcancelacion.get(1).toString().trim();
           Usuario =Vcancelacion.get(2).toString().toString();
           UsrKey =Vcancelacion.get(3).toString().trim();
           System.out.println("****** Anulando ******  \n"  +"Folio Boleto "+FolioBoleto +"\n"                                
                            +"Usuario "+Usuario+"\n"+"UsrKey "+UsrKey+"\n");
           try {   //  unRegistro.get(3).toString(),
                  boolean resultAnularFolio = port.anularFolio(FolioBoleto, Long.parseLong(Usuario), UsrKey );                    
                  System.out.println("**   resultAnularFolio ---> "+ resultAnularFolio);                    
                  if(resultAnularFolio)
                       {  
                          Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "R","no hay","Anula Folio_Batch");     
                           strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+"   Se hizo la anulacion de la operación "+ numeroOperacion;
                        }              
                   else{  
                        Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "P", "0", BusqInfBatch);
                         strContenedor=strContenedor+"\n"+Settings.LealtadRemote.getToDate()+" No se pudo hacer la anulacion de la operación "+ numeroOperacion;
                      }
                 } catch (Exception ex) {
                       Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "P", "0", BusqInfBatch);
                       System.out.println("Cancelacion Transaccion Exception  para Folio "+FolioBoleto);
                       ex.printStackTrace();
                      
                }            
       
       }//  for
  }
    

 
 
   public   void inicio() {
    try {
        System.out.println("INICIO    HILO  ");
        if (hilocarga == null) { 
            timePeriod = 60000*minutos;   
            System.out.println(" Time period "+ timePeriod);
            hilocarga = new Thread(this);
            hilocarga.start();
       }       
    
       } catch (Exception e) {
              e.printStackTrace();   
           }
   } 

public Vector desJSonearString(String jSonString, String numeroOperacion){
        JSONTokener jSonToken = new JSONTokener(jSonString);
        JSONObject jSonObject = null;
        Vector retorno = null;
        StringTokenizer token = null;
        StringTokenizer token1 = null;
        Vector saldos = null;
        Puntos respuestaPuntos = null;
        try {
            jSonObject = new JSONObject(jSonToken);
            System.out.println("---  desJSonearString Recibe  "+jSonObject.toString());
            retorno = new Vector();
            System.out.println("--desJSonearString.REGISTRA TRANSACCION STATUS "+(jSonObject.get("Valida").toString()));
            System.out.println("--desJSonearString.REGISTRA TRANSACCION MENSAJE "+(jSonObject.get("Mensaje").toString()));
            System.out.println("--desJSonearString.Valida "+jSonObject.get("Valida").toString());
            retorno.add(jSonObject.get("Valida").toString());
            
            if(retorno.get(0).toString().toUpperCase().equals("TRUE")){
                token1 = new StringTokenizer(jSonObject.get("Mensaje").toString(),"\n");
                saldos = new Vector();
                
                while(token1.hasMoreTokens()){
                    token = new StringTokenizer(token1.nextToken(),":");
                    while(token.hasMoreTokens())
                        saldos.add(token.nextToken());
                }
                
                System.out.println("--desJSonearString.saldos "+saldos);
                respuestaPuntos = new Puntos();
                respuestaPuntos.setPuntosOtorgados(saldos.get(1).toString().substring(1));
                respuestaPuntos.setSaldoPuntos(saldos.get(3).toString().substring(1));
                respuestaPuntos.setSaldoMoneda(saldos.get(5).toString().substring(1));
                retorno.add(respuestaPuntos);
            }else{
                String mensaje = "";
                //retorno.add(jSonObject.get("Mensaje").toString());
                if(jSonObject.get("Mensaje").toString().equals("This SqlTransaction has completed; it is no longer usable."))
                  mensaje = "1";
                else  
                if(jSonObject.get("Mensaje").toString().equals("La transacción ya existe, por lo que no se puede insertar duplicada."))
                    mensaje = "3";
                else
                if(jSonObject.get("Mensaje").toString().equals("La tarjeta que esta intentando realizar la transacción esta bloqueada en el sistema."))
                    mensaje = "9";
                else
                if(jSonObject.get("Mensaje").toString().equals("La tarjeta que esta intentando realizar la transacción no ha sido personalizada."))
                    mensaje = "10";
                else
                if(jSonObject.get("Mensaje").toString().equals("La tarjeta que esta intentando realizar la transacción está vencida."))
                    mensaje = "11";
                else
                if(jSonObject.get("Mensaje").toString().equals("La transacción que se está intentando realizar no es válida."))
                    mensaje = "13";
                else
                if(jSonObject.get("Mensaje").toString().equals("La transacción no se puede hacer con el usuario indicado ya que no pertenece a la sucursal o al comercio indicado."))
                    mensaje = "12";
                else
                if(jSonObject.get("Mensaje").toString().equals("Usuario y/o contraseña invalida"))
                    mensaje = "12";                
                else
                if(jSonObject.get("Mensaje").toString().contains("La tarjeta ha excedido "))
                    mensaje = "14";
                else
                if(jSonObject.get("Mensaje").toString().equals("La tarjeta con la que está tratando de realizar la transacción no existe"))
                    mensaje = "15";
                else
                    mensaje = "13";
                retorno.add(mensaje);
                if(mensaje == "3")
                    retorno.add(numeroOperacion);
                else
                    retorno.add(jSonObject.get("Mensaje").toString());
            }
        } catch (JSONException ex) {
            System.out.println("JSONTokener Fallo en el constructor de InformacionCliente con la cadena "+jSonString);
            ex.printStackTrace();
        }
        catch(Exception ee){  
            ee.printStackTrace();
        }
        return retorno;
    }


public String getMensaje (int  mensaje)
{
    String msj ="";
   switch (mensaje){
          case 1: msj = "This SqlTransaction has completed; it is no longer usable.";  break;
          case 3: msj = "La transacción ya existe, por lo que no se puede insertar duplicada.";  break;
          case 9: msj = "La tarjeta que esta intentando realizar la transacción esta bloqueada en el sistema.";  break;
          case 10: msj = "La tarjeta que esta intentando realizar la transacción no ha sido personalizada.";  break;
          case 11: msj = "La tarjeta que esta intentando realizar la transacción está vencida.";  break;
          case 12: msj = "La transacción no se puede hacer con el usuario indicado ya que no pertenece a la sucursal o al comercio indicado.";  break;
          case 13: msj = "La transacción que se está intentando realizar no es válida.";  break;
          case 14: msj = "La tarjeta ha excedido ";  break;
          case 15: msj = "La tarjeta con la que está tratando de realizar la transacción no existe";  break;
        
   }      
          return msj;
}



    public void run() {
    try {
       
         while( hilocarga != null   )
        
         {
             System.out.println("Busca informacion Pendiente");
             strContenedor="";
             GetOperaciones();  // Proceso que hace la transaccion de las operaciones
           //  hilocarga.sleep( timePeriod );    
          
        }   // while
      } catch (Exception e) {
          hilocarga = null;  
         e.printStackTrace();    
        }
     }   // run

    
}
