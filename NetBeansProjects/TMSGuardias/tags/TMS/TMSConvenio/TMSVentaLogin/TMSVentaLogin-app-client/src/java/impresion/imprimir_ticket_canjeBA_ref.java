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

    public imprimir_ticket_canjeBA_ref(String pnomCliente, String preferencia, String preferenciaRedo, String pCajaNumero, String pClaveCajero, String pNombreCajero,Object[][] pboletos, Vector pids, String ptipoCnje){
         this.referencia = preferencia;
         this.referenciaRedo = preferenciaRedo;
         this.boletos = pboletos;
         this.ids = pids;
         this.tipoCnje = ptipoCnje;
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
        sCad = sCad+"        Comprobante de canje ";
        sCad = sCad+"\n";
        sCad = sCad+"          de "+tipoCnje;
        sCad = sCad+"\n";
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
        sCad = sCad+"   FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
        sCad = sCad+"\n\n";
        sCad = sCad+"  Referencia "+referencia;
        sCad = sCad+"\n";
        sCad = sCad+"         PASAJEROS      "; //750
        sCad = sCad+"\n";
        sCad = sCad+" Nombre                # Asiento"; 
        sCad = sCad+"\n";
        for (int i=0; i<boletos.length; i++)
        {
            String nom = boletos[i][3].toString();
                if(nom.length()>=23)
                    nom = nom.substring(0,23);
                else
                    for(int j =nom.length(); j<23; j++)
                        nom = nom +" ";
                sCad = sCad+" "+nom+"  "+(boletos[i][1]==null?"":boletos[i][1]); 
                sCad = sCad+"\n";
        }
        sCad = sCad+"\n";
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
                sCad = sCad+"\n";
                sCad = sCad+" Destino: "+des.get(k).toString(); 
                sCad = sCad+"\n";
                sCad = sCad+" Fecha Corrida: "+(boletos[0][26]==null?"Abierta":boletos[0][26]);
                sCad = sCad+"\n";
                sCad = sCad+" Hora Corrida:  "+(boletos[0][27]==null?"Abierta":boletos[0][27]);
                sCad = sCad+"\n";
        }
        if(ids.size()>1)
            sCad = sCad+"  Documentos: ";
        else
            sCad = sCad+"  Documento: ";
        sCad = sCad+"\n";
        for(int i=0; i<ids.size(); i++)
        {
            Vector v = (Vector)ids.get(i);
            sCad = sCad+" "+v.get(1).toString();    
            sCad = sCad+"\n";
        }
        sCad = sCad+"\n";
        double total2 = 0;
        for (int i=0; i<boletos.length; i++)
               //if(boletos[i][29].toString().toString().equals("F"))
                   total2 = total2 + Double.valueOf(boletos[i][5].toString());
        sCad = sCad+"        Importe: $"+total2; 
        sCad = sCad+"\n";
        String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(total2)).longValue()))  + "Pesos 00/M.N. )";
        sCad = sCad+letras; 
        sCad = sCad+"\n\n";
        sCad = sCad+" FIRMA _____________________________";
        sCad = sCad+"\n";
        sCad = sCad+"\n   Este comprobante de canje";
        sCad = sCad+"\n"+"      No es Valido";
        sCad = sCad+"\n"+"Como cupon de viaje, ni como";
        sCad = sCad+"\n"+"    boleto de transporte";
        sCad = sCad+"\n\n";
        sCad = sCad+"Cajero: "+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
        sCad = sCad+"\n";
        sCad = sCad+" #Caja: "+CajaNumero;
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
        sCad = sCad+"AL RESPECTO.";
        sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
        sCad = sCad+"         .";
        try{
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = "C:\\REFERENCIA_CANJE_"+tipoCnje+"_"+referencia.replace(':','_')+".TXT";
                
            //FileDescriptor fd = new FileDescriptor();
            FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM \\CAPUSIS53\HPLaserJ
            PrintStream ps = new PrintStream(os); 
            ps.print(sCad); // CADENA A IMPRIMIR
           // ps.print(sCad); // CADENA A IMPRIMIR(Copia)
            ps.flush();
            os.close();
        }catch(java.io.FileNotFoundException fsctex){
            fsctex.printStackTrace();
            SalidaImpresion = "C:\\REFERENCIA_CANJE_"+tipoCnje+"_"+referencia.replace(':','_')+".TXT";
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