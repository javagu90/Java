/*
 * imprimir_ticket_vta_ref.java
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
public class imprimir_ticket_vta_ref{
    private String referencia;
    private String referenciaRedo;
    private String nombreCliente;
    private String CajaNumero, ClaveCajero, NombreCajero;

   private Object[][] boletos;
    private String SalidaImpresion;
    private boolean isVoucherBco;
    private String nombreImpresora;
    private int nlineas;
    private int nlineasTmp;

    public imprimir_ticket_vta_ref(String pnomCliente, String preferencia, String preferenciaRedo, String pCajaNumero, String pClaveCajero, String pNombreCajero,Object[][] pboletos){
         this.referencia = preferencia;
         System.out.println("Recibe la referencia: "+ this.referencia);
         this.referenciaRedo = preferenciaRedo;
         this.boletos = pboletos;
         nombreCliente= pnomCliente;
         this.CajaNumero = pCajaNumero;
         this.ClaveCajero = pClaveCajero;
         this.NombreCajero = pNombreCajero;
    }

    public boolean ImprimeDatos(String pSalidaImpresion, boolean pisVoucherBco, String pnombreImpresora){
        this.SalidaImpresion = pSalidaImpresion;
        this.isVoucherBco = pisVoucherBco;
        this.nombreImpresora = pnombreImpresora;
        return this.setPrint();
    }

    private boolean setPrint(){
        nlineas = 0;
        nlineas++;
        String sCadEnc="";
        String sCadFoot="";
        String sCadBack="";
        String sCad="\n";
        sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"     Comprobante de Referencia ";
        sCad = sCad+"\n";nlineas++;
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
        sCad = sCad+"   FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
        sCad = sCad+"\n\n";nlineas++;nlineas++;
        Vector oris = new Vector();
        Vector des = new Vector();
        for(int i=0; i<boletos.length; i++)
        {
           if(boletos[i][29].toString().toString().equals("F"))
           {
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
           }
        }
        String reftmp = referencia;
        String reftmpredo = referenciaRedo;
        System.out.println("oris: "+ oris.toString());
        if(referencia.indexOf(oris.get(0).toString())==-1 && oris.size()>1)
        {
            referencia = reftmpredo;
            referenciaRedo = reftmp;
        }
                    String ref2 = "";
                    String refRed2 = "";
                    String ref1 = "";
                    String refRed1 = "";
                    StringTokenizer tkn2 = new StringTokenizer(referencia,":");
                    ref1 = tkn2.nextToken();
                    if(tkn2.hasMoreTokens())ref2 = tkn2.nextToken();
                    if(!referenciaRedo.equals("")){
                        StringTokenizer tkn3 = new StringTokenizer(referenciaRedo,":");
                        refRed1 = tkn3.nextToken();
                        if(tkn3.hasMoreTokens())refRed2 = tkn3.nextToken();
                    }
//        sCad = sCad+"      __________________";
//        sCad = sCad+"\n";
//        sCad = sCad+"     |    PASAJEROS     | "+referencia; //750
//        sCad = sCad+"\n";                     
//        sCad = sCad+"      ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯ "; 
        sCad = sCad+"            PASAJEROS      "; //750
        sCadEnc = sCad;
        sCad = "";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+" Nombre                # Asiento"; 
        sCad = sCad+"\n";nlineas++;
        for (int i=0; i<boletos.length; i++)
        {
           if(boletos[i][29].toString().toString().equals("F"))
           {
                String nom = boletos[i][3].toString();
                if(nom.equals("VALIDO AL PORTADOR"))
                  nom = "NO PROPORCIONO NOMBRE";
                    if(nom.length()>=23)
                        nom = nom.substring(0,23);
                    else
                        for(int j =nom.length(); j<23; j++)
                            nom = nom +" ";
                    sCad = sCad+" "+nom+" "+ (boletos[i][1]==null ?"Abierto" :boletos[i][1])  ; 
                    sCad = sCad+"\n";nlineas++;
           }
        }
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+" Referencia: "+ref2;
        sCad = sCad+"\n";nlineas++;
        for(int k =0; k<oris.size();k++)   
        {
                if(k==1)
                {
                    if(!referenciaRedo.equals(""))
                    {
                        sCad = sCad+" Referencia: "+refRed2;
//                        sCad = sCad+"  Referencia Regreso: "+referenciaRedo;
                        sCad = sCad+"\n";nlineas++;
                    }
                }
                sCad = sCad+" Origen: "+oris.get(k).toString(); 
                sCad = sCad+"\n";nlineas++;
                sCad = sCad+" Destino: "+des.get(k).toString(); 
                sCad = sCad+"\n";nlineas++;
                if(k==0)
                {
                    sCad = sCad+" Fecha Corrida: "+boletos[0][26];
                    sCad = sCad+"\n";nlineas++;
                    sCad = sCad+" Hora Corrida:  "+boletos[0][27];
                    sCad = sCad+"\n";nlineas++;
                }
                else
                {
                    sCad = sCad+" Fecha Corrida: Abierta";
                    sCad = sCad+"\n";nlineas++;
                }
                sCad = sCad+" Tipos         Precio      Total"; 
                //sCad = sCad+" Pasajeros     Precio      Total"; 
                sCad = sCad+"\n";nlineas++;
                Vector tpas = new Vector();
                Vector ptpas = new Vector();
                Vector mttpas = new Vector();
                Vector numtpas = new Vector();
                for (int i=0; i<boletos.length; i++)
                {
                    if(boletos[i][21].toString().equals(oris.get(k).toString()) && boletos[i][29].toString().toString().equals("F"))
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
                        sCad = sCad+"\n";nlineas++;
                }
                sCad = sCad+"\n";nlineas++;
        }
        double total2 = 0;
        for (int i=0; i<boletos.length; i++)
               if(boletos[i][29].toString().toString().equals("F"))
                   total2 = total2 + Double.valueOf(boletos[i][5].toString());
        sCadFoot =""          ;
        sCadBack = sCad;
        sCad = "";
        nlineasTmp = nlineas;
        sCad = sCad+"        Importe: $"+total2;
        sCad = sCad+"\n";nlineas++;
        String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(total2)).longValue()))  + "Pesos 00/M.N. )";
        sCad = sCad+letras; 
        sCad = sCad+"\n\n";nlineas++;nlineas++;
        sCad = sCad+"  Persona adicional autorizada \n  para canjear los boletos: ";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+" "+nombreCliente;
        sCad = sCad+"\n\n";nlineas++;nlineas++;
        sCad = sCad+" FIRMA _____________________________";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"\n   Este comprobante de pago";nlineas++;
        sCad = sCad+"\n"+"      No es Valido";nlineas++;
        //sCad = sCad+"\n"+"    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
        sCad = sCad+"\n"+"Como cupon de viaje, ni como";nlineas++;
        sCad = sCad+"\n"+"    boleto de transporte";nlineas++;
        sCad = sCad+"\n\n";nlineas++;nlineas++;
        sCad = sCad+"Cajero: "+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+" #Caja: "+CajaNumero;
        sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
        sCad = sCad+"         .";
        sCadFoot = sCad;
        try{
            String UserHome = System.getProperty("user.home");
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = UserHome+"\\REFERENCIA_VENTA_"+referencia.replace(':','_')+".TXT";

          if (isVoucherBco)
          {
            String enc= "^XA\n";
                   enc = enc + "^MMT\n";
                   enc = enc + "^PW609\n";
                   enc = enc + "^^LL0"+((nlineas*30)+50)+"\n";
                   enc = enc + "^LS0\n";
                   enc = enc + "^A0N,30,30\n";
                   enc = enc + "^FO10,020\n";
                   enc = enc + "^FB609,10,0,C,0^FD"+sCadEnc.replace("\n","\\&")+"^FS\n";
                   enc = enc + "^A0N,28,28\n";
                   enc = enc + "^FO10,210\n";
                   sCad   = enc +"^FB609,500,0,L,0^FD"+ sCadBack.replace("\n","\\&")+"^FS\n";
            String foot = "";
                   foot = foot + "^A0N,28,28\n";
                   foot = foot + "^FO10,"+(nlineasTmp*30)+"\n";
                   foot = foot + "^FB609,50,0,C,0^FD"+sCadFoot.replace("\n","\\&")+"^FS\n";
                   sCad = sCad + foot.replace("\n","\\&")+"^XZ\n"+"C\n";
            System.out.println("imprimir_ticket_vta_ref para BoletoBlanco: ");
            System.out.println(""+sCad);
          }
            else
              sCad = sCadEnc +sCadBack+sCadFoot;

            if(SalidaImpresion.equals("USB"))
            {
                SalidaImpresion = "\\\\127.0.0.1\\"+nombreImpresora;
                 System.out.println("(imprimir_ticket_canjeBA_ref)Impresora USB: "+SalidaImpresion);
                 //granCodigoImpresion = granCodigoImpresion.replace("^@", "${")+"}$";
                 //System.out.println("granCodigoImpresion: "+granCodigoImpresion);
            }
            System.out.println("(imprimir_ticket_canjeBA_ref)SalidaImpresion antes (RED): "+SalidaImpresion);
            if(SalidaImpresion.equals("RED"))
            {
                  SalidaImpresion =  nombreImpresora;
                  //granCodigoImpresion = granCodigoImpresion.replace("^@", "${")+"}$";
            }


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
            SalidaImpresion = UserHome+"\\REFERENCIA_VENTA_"+referencia.replace(':','_')+".TXT";
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