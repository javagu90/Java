/*
 * Carga.java
 *
 * Created on 28 de diciembre de 2009, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslealtad_carga;

/*import Datos.Puntos;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import tmslealtad_carga.DoTransation;
import wsTricomp.Enterprise;
import wsTricomp.EnterpriseSoap;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;*/

/**
 *
 * @author brojasa
 */
public class Carga {

  /*  @WebServiceRef(wsdlLocation = "http://www.tricomp.com.mx/testing/ws/enterprise.asmx?WSDL")
    private static wsTricomp.Enterprise service_1;
    
    
    wsTricomp.Estrellaroja service = new wsTricomp.Estrellaroja();
    wsTricomp.EstrellarojaSoap port = service.getEstrellarojaSoap();
    
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");*/
     
    public Carga() {
    
        
    }
    
    public static void main(String[] args) {
        DoTransation dt = new DoTransation("");
      //  Settings.lookupGenerico(); 
      //  GetOperaciones();
	}
       
   /* 
    public static void GetOperaciones()
    {
       String stroperacion = "";
       Vector operpendientes = Settings.LealtadRemote.TMSOperacionesPendientes();
       Vector voperacion = null;
       System.out.println("Oper Pendientes "+operpendientes);
       if(operpendientes != null  && operpendientes.size() > 0)
       for (int i = 0; i < operpendientes.size(); i++) {
           stroperacion = ((Vector)operpendientes.elementAt(i)).elementAt(0).toString().trim();
           System.out.println("Operacion Pendientes "+stroperacion);
          // voperacion = Settings.LealtadRemote.TMSConsultaPorNumeroOperacion(operacion);
            System.out.println("Datos Operacion  "+stroperacion);
             operacion(stroperacion);
        
       }// for
    }
    
    
     public  Puntos operacion( String numeroOperacion) {        
        Vector response = Settings.LealtadRemote.TMSConsultaPorNumeroOperacion(numeroOperacion);
          Vector unRegistro = null;
        long transaccion = 0;
        String resultadoCierraTransaccionStr;
        Date fecha = null;
        Puntos respuestaPuntos = new Puntos();
        StringTokenizer token = null;
        StringTokenizer token1 = null;
        Vector saldos = null;
        String respuestaStatus = "";
        for(int k = 1; k < response.size(); k++){
            System.out.println("response "+response.get(k));           
            unRegistro = (Vector) response.get(k);
//            cerrarTransaccion = true;
            if(!unRegistro.get(4).toString().equals("C")){
                if(k == 1) {
                    //RegistroTransaccion
                    try {
                        System.out.println("unRegistro.get(9).toString() "+unRegistro.get(9).toString());
                        try{
                            fecha = df.parse(unRegistro.get(9).toString());                                
                            System.out.println("fecha  "+fecha);
                        }catch(Exception e){
                            e.printStackTrace();
                            return respuestaPuntos;
                        }
                         
                        System.out.println(" ---------------- Inicia registraTransaccion ---------------- ");
                        System.out.println("No Tarjeta "+unRegistro.get(3).toString()+"\n"
                                +"Ticket "+numeroOperacion+" \n"
                                +"Sucural "+unRegistro.get(2).toString()+"\n"
                                +"Usuario "+unRegistro.get(5).toString()+"\n"
                                +"Fecha "+fecha+"\n"
                                +"TipoTransaccion "+unRegistro.get(4).toString()+"\n"
                                +"Total "+unRegistro.get(8).toString()+"\n"
                                +"Descuento 0\n"
                                +"Taquilla "+unRegistro.get(10).toString()+"\n"
                                +"UsrKey "+unRegistro.get(6).toString()+"\n"
                                );
                        transaccion = port.registraTransaccion(unRegistro.get(3).toString(), numeroOperacion, Long.parseLong(unRegistro.get(2).toString()), 
                                Long.parseLong(unRegistro.get(5).toString()), getDate(fecha), Long.parseLong(unRegistro.get(4).toString()), Double.parseDouble(unRegistro.get(8).toString()), 0.0,
                                unRegistro.get(10).toString(), unRegistro.get(6).toString());
                        System.out.println("transaccion = "+transaccion);
                        if(transaccion < 0) {
                            System.out.println("El método de Transaccion Falló!");
                            return respuestaPuntos;
                        }
                        if(transaccion == 0){
                            System.out.println("Se intentó duplicar información ");
                            return respuestaPuntos;
                        }
                    } catch (Exception ex) {
                        System.out.println("Registra Transaccion Exception");
                        ex.printStackTrace();
                        return respuestaPuntos;
                    }
                }
                //Registra Detalle Transaccion
                try {
                    System.out.println(" ---------------- Inicia registraDetalleTransaccion ---------------- ");
                    System.out.println("Transaccion "+transaccion+"\n"
                                +"Producto "+unRegistro.get(1).toString()+" \n"
                                +"Cantidad 1\n"
                                +"Precio "+unRegistro.get(7).toString()+"\n"
                                +"Descuento 0\n"
                                +"Folio Boleto "+unRegistro.get(0).toString()+"\n"
                                +"Unidad Negocio "+unRegistro.get(11).toString()+"\n"
                                +"Usuario "+unRegistro.get(5).toString()+"\n"
                                +"UsrKey "+unRegistro.get(6).toString()+"\n");
                    long result = port.registraDetalleTransaccion(transaccion, unRegistro.get(1).toString(), 1, Double.parseDouble(unRegistro.get(7).toString()), 0.0, unRegistro.get(0).toString(), unRegistro.get(11).toString(), Long.parseLong(unRegistro.get(5).toString()), unRegistro.get(6).toString());
                                                                
                    System.out.println("Result = "+result);
                    if(result < 0) {
                        System.out.println("El método de Detalle Transaccion Falló! con el folio "+unRegistro.get(0).toString());                        
                        return respuestaPuntos;
                    }
                } catch (Exception ex) {
                    System.out.println("Registra Detalle Transaccion Exception");
                    ex.printStackTrace();
                    return respuestaPuntos;
                }
                
                //if(cerrarTransaccion && (k == response.size()-1)){
                if(k == response.size()-1){
                    if(unRegistro.get(4).toString().equals("1")) {
                        //Cierra Transaccion Str
                        System.out.println(" ---------------- Inicia cierraTransaccionStr ---------------- ");
                        System.out.println("Transaccion "+transaccion+"\n"
                                    +"Producto "+unRegistro.get(1).toString()+" \n"
                                    +"Total "+unRegistro.get(8).toString()+"\n"                                
                                    +"Descuento 0"
                                    +"Usuario "+unRegistro.get(5).toString()+"\n"
                                    +"UsrKey "+unRegistro.get(6).toString()+"\n");
                        try {
                            resultadoCierraTransaccionStr = port.cierraTransaccionStr(transaccion,  Double.parseDouble(unRegistro.get(8).toString()), 0.0, Long.parseLong(unRegistro.get(5).toString()), unRegistro.get(6).toString());
                            System.out.println("resultadoCierraTransaccionStr = "+resultadoCierraTransaccionStr);                                                        
                            token1 = new StringTokenizer(resultadoCierraTransaccionStr,"\n");
                            saldos = new Vector();
                            while(token1.hasMoreTokens()){
                                token = new StringTokenizer(token1.nextToken(),":");
                                while(token.hasMoreTokens())
                                    saldos.add(token.nextToken());
                            }
                            System.out.println("saldos "+saldos);
                            respuestaPuntos.setPuntosOtorgados(saldos.get(1).toString().substring(1));
                            respuestaPuntos.setSaldoPuntos(saldos.get(3).toString().substring(1));
                            respuestaPuntos.setSaldoMoneda(saldos.get(5).toString().substring(1));
                            if(resultadoCierraTransaccionStr.equals("No se pudo cerrar la transacción")){
                                System.out.println("no se cerro");
                                return respuestaPuntos;
                            }
                        } catch (Exception ex) {
                            System.out.println("Cierra Transaccion Str Exception");
                            ex.printStackTrace();
                            return respuestaPuntos;
                        }
                    }else{
                        System.out.println(" ---------------- Inicia cierraTransaccion ---------------- ");
                        System.out.println("Transaccion "+transaccion+"\n"
                                    +"Producto "+unRegistro.get(1).toString()+" \n"
                                    +"Total "+unRegistro.get(8).toString()+"\n"                                
                                    +"Descuento 0\n"
                                    +"Usuario "+unRegistro.get(5).toString()+"\n"
                                    +"UsrKey "+unRegistro.get(6).toString()+"\n");
                        try {
                            boolean resultadoCierraTransaccion = port.cierraTransaccion(transaccion,Double.parseDouble(unRegistro.get(8).toString()), 0.0, Long.parseLong(unRegistro.get(5).toString()), unRegistro.get(6).toString());
                            System.out.println("Result = "+resultadoCierraTransaccion);
                            if(!resultadoCierraTransaccion)
                                return respuestaPuntos;
                        } catch (Exception ex) {
                           System.out.println("Cierra Transaccion Exception");
                            ex.printStackTrace();
                            return respuestaPuntos;
                        }
                    }  
                }
            }else{
             // Si es una anulacion
                System.out.println("voy a anular");
            }
        }
        //Si llega hasta aca, es xq pudo insertar en las operaciones
        respuestaStatus = Settings.LealtadRemote.TMSLealtadCambiaStatus(numeroOperacion, "R", String.valueOf(transaccion));
        return respuestaPuntos;
    }
    
    protected XMLGregorianCalendar getDate(java.util.Date date) {
	try {
            if (date == null) return null ;
            GregorianCalendar cal = new GregorianCalendar() ;
            cal.setTime(date) ;
     return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal) ;
	} catch (DatatypeConfigurationException e) {
	    throw new java.lang.Error(e);
	}
    }     */
}
