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
import java.util.Vector;
import subProcesos.cantidad_a_letras;
import tms_venta.SesionVenta;
import tms_venta.entidad.TmsBoletosVentaTbl;

/**
 *
 * @author vgonzalez
 */
public class imprimir_ticket_canjeBA_ref{
    private String referencia;
    private String referenciaRedo;
    private String nombreCliente;
    private String CajaNumero, ClaveCajero, NombreCajero;

   private Object[][] boletos;

    private Vector ids;

    private String tipoCnje;
    private String SalidaImpresion;
    private boolean isVoucherBco;
    private SesionVenta sesionVenta;
    private String nombreImpresora;
    private int nlineas;
    private int nlineasTmp;


    public imprimir_ticket_canjeBA_ref(String pnomCliente, String preferencia, String preferenciaRedo, String pCajaNumero, String pClaveCajero, String pNombreCajero,Object[][] pboletos, Vector pids, String ptipoCnje, SesionVenta psesionVenta){
         this.referencia = preferencia;
         this.referenciaRedo = preferenciaRedo;
         this.boletos = pboletos;
         this.ids = pids;
         this.tipoCnje = ptipoCnje;
         nombreCliente= pnomCliente;
         this.CajaNumero = pCajaNumero;
         this.ClaveCajero = pClaveCajero;
         this.NombreCajero = pNombreCajero;
         this.sesionVenta = psesionVenta;
    }

    public boolean ImprimeDatos(String pSalidaImpresion, boolean pvoucheBco, String pimpresora){
        this.SalidaImpresion = pSalidaImpresion;
        this.isVoucherBco = pvoucheBco;
        this.nombreImpresora = pimpresora;
        return this.setPrint();
    }

    private boolean setPrint(){
        nlineas = 0;
        nlineas++;
        String sCadEnc="";
        String sCadBack="";
        String sCadFoot="";
        String sCad="\n";
        sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"        Comprobante de canje ";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"          de "+tipoCnje;
        sCad = sCad+"\n";nlineas++;
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
        sCad = sCad+"   FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
        sCad = sCad+"\n\n";nlineas++; nlineas++;
        sCadEnc = sCad;
        sCad = "";
        sCad = sCad+"  Referencia "+referencia;
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"         PASAJEROS      "; //750
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+" Nombre                # Asiento"; 
        sCad = sCad+"\n";nlineas++;
        for (int i=0; i<boletos.length; i++)
        {
            String nom = boletos[i][3].toString();
                if(nom.length()>=23)
                    nom = nom.substring(0,23);
                else
                    for(int j =nom.length(); j<23; j++)
                        nom = nom +" ";
                sCad = sCad+" "+nom+"  "+(boletos[i][1]==null?"":boletos[i][1]); 
                sCad = sCad+"\n";nlineas++;
        }
        sCad = sCad+"\n";nlineas++;
        Vector oris = new Vector();
        Vector des = new Vector();
        for(int i=0; i<boletos.length; i++)
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
        
        for(int k =0; k<oris.size();k++)   
        {
                sCad = sCad+" Origen: "+oris.get(k).toString(); 
                sCad = sCad+"\n";nlineas++;
                sCad = sCad+" Destino: "+des.get(k).toString(); 
                sCad = sCad+"\n";nlineas++;
                sCad = sCad+" Fecha Corrida: "+(boletos[0][26]==null?"Abierta":boletos[0][26]);
                sCad = sCad+"\n";nlineas++;
                sCad = sCad+" Hora Corrida:  "+(boletos[0][27]==null?"Abierta":boletos[0][27]);
                sCad = sCad+"\n";nlineas++;
        }
        if(ids.size()>1)
            sCad = sCad+"  Documentos: ";
        else
            sCad = sCad+"  Documento: ";
        sCad = sCad+"\n";nlineas++;
        for(int i=0; i<ids.size(); i++)
        {
            Vector v = (Vector)ids.get(i);
            sCad = sCad+" "+v.get(1).toString();    
            sCad = sCad+"\n";nlineas++;
        }
        sCad = sCad+"\n";nlineas++;
        double total2 = 0;
        for (int i=0; i<boletos.length; i++)
               //if(boletos[i][29].toString().toString().equals("F"))
                   total2 = total2 + Double.valueOf(boletos[i][5].toString());


        sCadFoot =""          ;
        sCadBack = sCad;
        sCad = "";
        nlineasTmp = nlineas;
        sCad = sCad+"        Importe: $"+total2;
        sCad = sCad+"\n";nlineas++;
        String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(total2)).longValue()))  + "Pesos 00/M.N. )";
        sCad = sCad+letras; 
        sCad = sCad+"\n\n";nlineas++; nlineas++;
        sCad = sCad+" FIRMA _____________________________";
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+"\n   Este comprobante de canje";nlineas++;
        sCad = sCad+"\n"+"      No es Valido";nlineas++;
        sCad = sCad+"\n"+"Como cupon de viaje, ni como";nlineas++;
        sCad = sCad+"\n"+"    boleto de transporte";nlineas++;
        sCad = sCad+"\n\n";nlineas++; nlineas++;
        sCad = sCad+"Cajero: "+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
        sCad = sCad+"\n";nlineas++;
        sCad = sCad+" #Caja: "+CajaNumero;
        /*
        sCad = sCad+"\n\n";
        sCad = sCad+"POR ESTE PAGARE ME OBLIGO INCONDI\n";
        sCad = sCad+"CIONALMENTE A PAGAR A LA ORDEN DEL\n";
        sCad = sCad+"BANCO EMISOR EL IMPORTE DE ESTE\n"; 
        sCad = sCad+"TITULO EN LOS TERMINOS DEL CONTRA\n";
        sCad = sCad+"TO SUSCRITO PARA EL USO DE ESTA \n";
        sCad = sCad+"TARJETA DE CREDITO EN EL CASO DE\n"; 
        sCad = sCad+"OPERACIONES CON TARJETA DE DEBITO,\n"; 
        sCad = sCad+"EXPRESAMENTE RECONOZCO Y ACEPTO\n"; 
        sCad = sCad+"ESTE RECIBO ES EL COMPROBANTE DE\n"; 
        sCad = sCad+"LA OPERACION REALIZADA, MISMA QUE\n"; 
        sCad = sCad+"SE CONSIGNA EN EL ANVERSO Y TEN\n";
        sCad = sCad+"DRA PLENO VALOR PROBATORIO Y FUER\n";
        sCad = sCad+"ZA LEGAL, EN VIRTUD DE QUE LO FIR\n";
        sCad = sCad+"ME PERSONALMENTE Y/0 DIGITE MI NU\n";
        sCad = sCad+"MERO DE IDENTIFICACION PERSONAL\n";
        sCad = sCad+"COMO FIRMA ELECTRONICA EL CUAL ES \n";
        sCad = sCad+"EXCLUSIVO DE MI RESPONSABILIDAD\n";
        sCad = sCad+"MANIFESTANDO PLENA CONFORMIDAD \n";
        sCad = sCad+"AL RESPECTO.";*/
        sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
        sCad = sCad+"         .";
        sCadFoot = sCad;

        //if( this.voucheBco)
        //    sCad = this.sesionVenta.getTmsVtaFacade().getCadenaVoucher(sCad,this.impresora,this.tipoCnje);
        try{
            String UserHome = System.getProperty("user.home");
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = UserHome+"\\REFERENCIA_CANJE_"+tipoCnje+"_"+referencia.replace(':','_')+".TXT";
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
            System.out.println("imprimir_ticket_canjeBA_ref para BoletoBlanco: ");
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
            FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM \\CAPUSIS53\HPLaserJ
            PrintStream ps = new PrintStream(os); 
            ps.print(sCad); // CADENA A IMPRIMIR
           // ps.print(sCad); // CADENA A IMPRIMIR(Copia)
            ps.flush();
            os.close();
        }catch(java.io.FileNotFoundException fsctex){
            String UserHome = System.getProperty("user.home");
            fsctex.printStackTrace();
            SalidaImpresion = UserHome+"\\REFERENCIA_CANJE_"+tipoCnje+"_"+referencia.replace(':','_')+".TXT";
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