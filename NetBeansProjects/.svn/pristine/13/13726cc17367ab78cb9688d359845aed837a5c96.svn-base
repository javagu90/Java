/*
 * imprimir_voucher.java
 *
 * Created on 21 de junio de 2007, 12:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package impresion;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import subProcesos.cantidad_a_letras;
import tms_venta.entidad.TmsBoletosVentaTbl;

/**
 *
 * @author vgonzalez
 */
public class imprimir_ticket_redencion{
    private String ocupado;
    private String saldo;
    private String tarjeta;
    private String nombreCliente;
    private String CajaNumero, ClaveCajero, NombreCajero;

   private Object[][] boletos;
    private String SalidaImpresion;

    public imprimir_ticket_redencion(String pnomCliente, String ptarjeta, String pCajaNumero, String pClaveCajero, String pNombreCajero,Object[][] pboletos, String pocupado,String psaldo){
         this.tarjeta = ptarjeta.substring(0,4)+"****"+ptarjeta.substring(ptarjeta.length()-4);
         this.ocupado = pocupado;
         this.saldo = psaldo;
         this.boletos = pboletos;
         nombreCliente= pnomCliente;
         this.CajaNumero = pCajaNumero;
         this.ClaveCajero = pClaveCajero;
         this.NombreCajero = pNombreCajero;
         
    }

    public boolean ImprimeDatos(String pSalidaImpresion){
        this.SalidaImpresion = pSalidaImpresion;
        return this.setPrint();
    }

    private boolean setPrint(){
        
        String sCad="\n";
        sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
        sCad = sCad+"\n";
        sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
        sCad = sCad+"\n";
        sCad = sCad+"           Comprobante de ";
        sCad = sCad+"\n       Redención de Estrellas ";
        sCad = sCad+"\n";
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
        sCad = sCad+"   FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
        sCad = sCad+"\n\n";
        sCad = sCad+"Num. Tarjeta: "+this.tarjeta+"\n\n";
        Vector oris = new Vector();
        Vector des = new Vector();
        for(int i=0; i<boletos.length; i++)
        {
           //if(boletos[i][29].toString().toString().equals("F"))
           //{
               if(i==0) 
               {
                   oris.add(boletos[i][21].toString());
                   des.add(boletos[i][22].toString());
               }
               else
                  if(oris.indexOf(boletos[i][21].toString())==-1)
                  {
                    oris.add(boletos[i][21].toString());
                    des.add(boletos[i][22].toString());
                  }
           //}
        }

        sCad = sCad+"            PASAJEROS      "; //750
        sCad = sCad+"\n";
        sCad = sCad+" Nombre                # Asiento"; 
        sCad = sCad+"\n";
        for (int i=0; i<boletos.length; i++)
        {
           //if(boletos[i][29].toString().toString().equals("F"))
           //{
                String nom = boletos[i][3].toString();
                if(nom.equals("VALIDO AL PORTADOR"))
                  nom = "NO PROPORCIONO NOMBRE";
                    if(nom.length()>=23)
                        nom = nom.substring(0,23);
                    else
                        for(int j =nom.length(); j<23; j++)
                            nom = nom +" ";
                    sCad = sCad+" "+nom+" "+ (boletos[i][1]==null ?"Abierto" :boletos[i][1])  ; 
                    sCad = sCad+"\n";
           //}
        }
        sCad = sCad+"\n";
        for(int k =0; k<oris.size();k++)   
        {

                sCad = sCad+" Origen: "+oris.get(k).toString(); 
                sCad = sCad+"\n";
                sCad = sCad+" Destino: "+des.get(k).toString(); 
                sCad = sCad+"\n";
                if(k==0)
                {
                    sCad = sCad+" Fecha Corrida: "+boletos[0][26];
                    sCad = sCad+"\n";
                    sCad = sCad+" Hora Corrida:  "+boletos[0][27];
                    sCad = sCad+"\n";
                }
                else
                {
                    sCad = sCad+" Fecha Corrida: Abierta";
                    sCad = sCad+"\n";
                }
                sCad = sCad+" Tipos         Precio      Total"; 
                //sCad = sCad+" Pasajeros     Precio      Total"; 
                sCad = sCad+"\n";
                Vector tpas = new Vector();
                Vector ptpas = new Vector();
                Vector mttpas = new Vector();
                Vector numtpas = new Vector();
                for (int i=0; i<boletos.length; i++)
                {
                    if(boletos[i][21].toString().equals(oris.get(k).toString()))// && boletos[i][29].toString().toString().equals("F"))
                    {
                        if(tpas.size()==0)
                        {
                            tpas.add(boletos[i][2].toString());
                            ptpas.add(boletos[i][5].toString());
                            mttpas.add(boletos[i][5].toString());
                            numtpas.add("1");
                        }
                        else
                        {
                            int index = tpas.indexOf(boletos[i][2].toString());
                            if(index!=-1)
                            {
                                double mt = Double.valueOf(mttpas.get(index).toString());
                                mt = mt + Double.valueOf(boletos[i][5].toString());
                                mttpas.add(index,mt);
                                int num = Integer.valueOf(numtpas.get(index).toString());
                                num ++;
                                numtpas.add(index,num);
                            }
                            else
                            {
                                tpas.add(boletos[i][2].toString());
                                ptpas.add(boletos[i][5].toString());
                                mttpas.add(boletos[i][5].toString());
                                numtpas.add("1");
                                
                            }
                        }
                    }
                }                
                
                for (int i=0; i<tpas.size(); i++)
                {
                        String precio = ptpas.get(i).toString();
                        for(int j=precio.length(); j<11;j++)
                                precio = precio +" ";
                        String pasajeros = "";
                        if(tpas.get(i).toString().equals("A") )
                        {
                            if(Integer.valueOf(numtpas.get(i).toString())>1)
                                pasajeros = "ADULTOS    ";
                            else
                                pasajeros = "ADULTO     ";
                        }
                        if(tpas.get(i).toString().equals("M") )
                        {
                            if(Integer.valueOf(numtpas.get(i).toString())>1)
                                pasajeros = "MENORES    ";
                            else
                                pasajeros = "MENOR      ";
                        }
                        if(tpas.get(i).toString().equals("S") )
                        {
                            if(Integer.valueOf(numtpas.get(i).toString())>1)
                                pasajeros = "SENECTUDES ";
                            else
                                pasajeros = "SENECTUD   ";
                        }
                        if(tpas.get(i).toString().equals("E") )
                        {
                            if(Integer.valueOf(numtpas.get(i).toString())>1)
                               pasajeros = "ESTUDIANTES";
                            else
                                pasajeros = "ESTUDIANTE ";
                        }
                        if(tpas.get(i).toString().equals("P") )
                        {
                            if(Integer.valueOf(numtpas.get(i).toString())>1)
                               pasajeros = "PROFESORES ";
                            else
                                pasajeros = "PROFESOR   ";
                        }
                        if(tpas.get(i).toString().equals("C") )
                        {
                            if(Integer.valueOf(numtpas.get(i).toString())>1)
                               pasajeros = "ESPECIALES ";
                            else
                                pasajeros = "ESPECIAL   ";
                        }
                        if(tpas.get(i).toString().equals("V") )
                        {
                            if(Integer.valueOf(numtpas.get(i).toString())>1)
                                pasajeros = "MENORES VOL";
                            else
                                pasajeros = "MENOR VOLAR";
                        }
                        pasajeros = numtpas.get(i).toString() +" "+ pasajeros;
                        sCad = sCad+" "+pasajeros+" "+precio+" "+mttpas.get(i).toString(); 
                        sCad = sCad+"\n";
                }
                sCad = sCad+"\n";
        }
        double total2 = 0;
        for (int i=0; i<boletos.length; i++)
               //if(boletos[i][29].toString().toString().equals("F"))
                   total2 = total2 + Double.valueOf(boletos[i][5].toString());
        sCad = sCad+"\nEstrellas Utilizadas: "+ocupado;
        sCad = sCad+"\nEstrellas Restantes : "+saldo;
        sCad = sCad+"\n\n        Importe: $"+total2; 
        sCad = sCad+"\n";
        String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(total2)).longValue()))  + "Pesos 00/M.N. )";
        sCad = sCad+letras; 
        sCad = sCad+"\n\n";
        sCad = sCad+" FIRMA _____________________________";
        sCad = sCad+"\n         Acepto y reconozco el ";
        sCad = sCad+"\n contenido literal de este documento";
        sCad = sCad+"\n";
        sCad = sCad+"\n Este comprobante de pago";
        sCad = sCad+"\n No es válido como cupon de viaje,";
        sCad = sCad+"\n ni como boleto de transporte";
        sCad = sCad+"\n\n";
        sCad = sCad+"Cajero: "+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
        sCad = sCad+"\n";
        sCad = sCad+" #Caja: "+CajaNumero;
        sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
        sCad = sCad+"         .";
        try{
            String UserHome = System.getProperty("user.home");
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = UserHome+"\\REDENCION_"+this.tarjeta.replace('*','_')+".TXT";
            System.out.println("Manda el ticket a la consola...");
            System.out.println("\n"+sCad+" \n");                
            //FileDescriptor fd = new FileDescriptor();
            FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
            PrintStream ps = new PrintStream(os); 
            ps.print(sCad); // CADENA A IMPRIMIR
           // ps.print(sCad); // CADENA A IMPRIMIR(Copia)
            ps.flush();
            os.close();
        }catch(java.io.FileNotFoundException fsctex){
            fsctex.printStackTrace();
            String UserHome = System.getProperty("user.home");
            SalidaImpresion = UserHome+"\\REDENCION_"+this.tarjeta.replace('*','_')+".TXT";
            System.out.println("Manda el ticket a la consola...");
            System.out.println("\n"+sCad+" \n");
            FileOutputStream os;
                try {
                    os = new FileOutputStream(SalidaImpresion);
                    PrintStream ps = new PrintStream(os); 
                    ps.print(sCad); // CADENA A IMPRIMIR
                    ps.flush();
                    
                    try {
                        os.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } // LPT / C:\\ARCHIVO.TXT / COM
            return false;
        }catch(Exception sctex){
            sctex.printStackTrace();
            return false;
        }
        return true;
    }

    private String customFormat(String pattern, float value ) {
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      return myFormatter.format(value);
    }
}