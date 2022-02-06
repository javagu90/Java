/*
 * JClsImprimeVoucher.java
 *
 * Created on 23 de septiembre de 2008, 01:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xer_emv_dll;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author ocruz
 */
public class JClsImprimeVoucher {
    private int nlineas;
    
    /** Creates a new instance of JClsImprimeVoucher */
    public JClsImprimeVoucher() {
    }
    
    private String interpretaVoucher(String strVoucher, int noReimpresion){
        strVoucher = strVoucher.replaceAll("@", "|@");
        String cadenaImprimir="";
        System.out.println("getRspVoucher::: "+strVoucher);
        StringTokenizer lineas = new StringTokenizer(strVoucher, "|");
        nlineas = lineas.countTokens();
        System.out.println("#lineas::: "+lineas.countTokens());
        String formato="", info="";
        String linea;
        if(noReimpresion!=0) cadenaImprimir += "Reimpresión "+noReimpresion+".\n";
        while(lineas.hasMoreTokens()){
            linea = lineas.nextToken();
            if(linea.contains("@br"))
                cadenaImprimir += "\n";
            else{
               if(linea.length()>=5)
                {
                    try {
                    formato = linea.substring(1,4);
                    info = linea.substring(5);
                    info = info.replaceAll("\n","");
                    info = rTrim(info," ");
                    if(!info.equals(""))
                        cadenaImprimir += alinearTexto(formato.substring(0,1),45,info+"\n");
                    }catch(java.lang.StringIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        cadenaImprimir += "\n\n\n\n\n\n\n\n\n";
        System.out.println("cadena a imprimir:::\n"+cadenaImprimir);
        return cadenaImprimir;
    }
    
    private String alinearTexto(String at, int AnchoTexto, String texto){
        if(AnchoTexto<=texto.length()) return texto;
        
        String textoAlineado = "";
        if(at.equals("l")){
            textoAlineado = texto;
        }
        else{
            int espDisp, i;
            if(at.equals("c")){
               espDisp = (AnchoTexto - texto.length())/2;
               for(i=0; i<espDisp; i++)
                   textoAlineado += " ";
               textoAlineado = textoAlineado + texto;
            }
            else{
                if(at.equals("r")){
                    espDisp = AnchoTexto - texto.length();
                    for(i=0; i<espDisp; i++)
                        textoAlineado += " ";
                    textoAlineado = textoAlineado + texto;
                }
            }
        }
        
        if(textoAlineado.equals("")) textoAlineado = texto;
        
        return textoAlineado;
    }
    
    private String rTrim(String string, String busq){
        for(int i=string.length()-1; i>=0; i--)
            if(string.substring(i,i+1).equals(busq))
                string = string.substring(0,i);
            else return string;
        return string;
    }
    
    public boolean ImprimeDatos(String RspVoucher, String SalidaImpresion, int noReimpresion, String ref1, String ref2, String nombreImpresora, boolean isVoucherBco){
        String sCad = interpretaVoucher(RspVoucher,noReimpresion);
        try{
            String UserHome = System.getProperty("user.home");
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = UserHome+"\\VOUCHER.TXT";
             sCad = sCad.replaceAll("</voucher_comercio>", "");
             sCad = sCad.replaceAll("<voucher_cliente>", "");
             sCad = sCad.replaceAll("</voucher_cliente>", "");
             sCad = sCad.replaceAll("her_comercio>", "");

             String cadref = "AL RESPECTO.";
             if(!ref1.equals(""))   cadref = cadref+"\n\n"+"Ref: "+ref1;
             if(!ref2.equals(""))   cadref = cadref+"\n"+"Ref: "+ref2;
             if(!cadref.equals("AL RESPECTO.")) cadref =cadref+ "\n\n";
             sCad = sCad.replaceAll("AL RESPECTO.", cadref);
          if (isVoucherBco)
          {
            String enc= "^XA\n";
                   enc = enc + "^MMT\n";
                   enc = enc + "^PW609\n";
                   enc = enc + "^^LL0"+((nlineas*30)+20)+"\n";
                   enc = enc + "^LS0\n";
                   enc = enc + "^A0N,28,28\n";
                   enc = enc + "^FO10,020\n";
            sCad   = enc +"^FB609,500,0,C,0^FD"+sCad.replace("\n","\\&")+"^FS\n^XZ\n"+"C\n";
            System.out.println("(Impresion Voucher)Voucher para BoletoBlanco: ");
            System.out.println(""+sCad);
          }

            if(SalidaImpresion.equals("USB"))
            {
                SalidaImpresion = "\\\\127.0.0.1\\"+nombreImpresora;
                 System.out.println("Impresora USB: "+SalidaImpresion);
                 //granCodigoImpresion = granCodigoImpresion.replace("^@", "${")+"}$";
                 //System.out.println("granCodigoImpresion: "+granCodigoImpresion);
            }
            System.out.println("(Impresion Voucher)SalidaImpresion antes (RED): "+SalidaImpresion);
            if(SalidaImpresion.equals("RED"))
            {
                  SalidaImpresion =  nombreImpresora;
                  //SalidaImpresion = SalidaImpresion.replace("^@", "${")+"}$";
            }
            System.out.println("(Impresion Voucher)SalidaImpresion despues (RED): "+SalidaImpresion);
            //System.out.println("cadena a imprimir(Termico):::\n"+sCad);
            FileDescriptor fd = new FileDescriptor();
            FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
            PrintStream ps = new PrintStream(os); 
            ps.print(sCad); // CADENA A IMPRIMIR
            ps.flush();
            os.close();
        }catch(java.io.FileNotFoundException fsctex){
            fsctex.printStackTrace();
            return false;
        }catch(Exception sctex){
            sctex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean ReimprimeDatos(String RspVoucher, String SalidaImpresion, int noReimpresion, String ref1, String ref2, String TipoVenta,Object[][] pboletos,Vector pids, String nombreImpresora, boolean isVoucherBco){
         Object[][] boletos = pboletos;
         Vector ids = pids;

        String sCad = interpretaVoucher(RspVoucher,noReimpresion);
        try{
            String UserHome = System.getProperty("user.home");
            System.out.println("TipoVenta: "+TipoVenta);
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = UserHome+"\\VOUCHER.TXT";
             sCad = sCad.replaceAll("</voucher_comercio>", "");
             sCad = sCad.replaceAll("<voucher_cliente>", "");
             sCad = sCad.replaceAll("</voucher_cliente>", "");
             sCad = sCad.replaceAll("her_comercio>", "");
             sCad = sCad.replaceAll("AUTOBUSES MEXICO PUEBLA ESTRELLA ROJA", "AUTOBUSES MEXICO PUEBLA ESTRELLA ROJA \n  BLVD NTE 4222 COL. LAS CUARTILLAS PUE ");
             //sCad = sCad.replaceAll("-C-O-M-E-R-C-I-O-",banco+"\n-C-O-M-E-R-C-I-O-");
             String cadTipo = TipoVenta +"\n\nCP-D";
             sCad = sCad.replaceAll("CP-D", cadTipo);

             String cadref = "AL RESPECTO.";
             cadref = cadref+"\n\n El Servicio sera prestado en las "
                             + "\n  Instalaciones de la Terminal"
                             + "\n         Estrella Roja"
                           + "\n\n Servicio: Viaje en Autobus\n";
             if(!ref1.equals(""))   cadref = cadref+"\n\n"+"Referencia: "+ref1;
             if(!ref2.equals(""))   cadref = cadref+"\n"+"Referencia: "+ref2;
             if(!cadref.equals("AL RESPECTO.")) cadref =cadref+ "\n\n";
                    cadref = cadref+"\n";
                    cadref = cadref+"         PASAJEROS      ";
                    cadref = cadref+"\n";
                    cadref = cadref+" Nombre                # Asiento";
                    cadref = cadref+"\n";
                    int nr = 44;
                    for (int i=0; i<boletos.length; i++)
                    {
                        String nom = boletos[i][3].toString();
                            if(nom.length()>=23)
                                nom = nom.substring(0,23);
                            else
                                for(int j =nom.length(); j<23; j++)
                                    nom = nom +" ";
                            cadref = cadref+" "+nom+"  "+(boletos[i][1]==null?"":boletos[i][1]);
                            cadref = cadref+"\n";
                            nr++;
                    }
                    cadref = cadref+"\n";
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
                            cadref = cadref+" Origen: "+oris.get(k).toString();
                            cadref = cadref+"\n";
                            cadref = cadref+" Destino: "+des.get(k).toString();
                            cadref = cadref+"\n";
                            cadref = cadref+" Fecha Corrida: "+(boletos[0][26]==null?"Abierta":boletos[0][26]);
                            cadref = cadref+"\n";
                            cadref = cadref+" Hora Corrida:  "+(boletos[0][27]==null?"Abierta":boletos[0][27]);
                            cadref = cadref+"\n";
                    }
                    if(ids.size()>1)
                        cadref = cadref+"  Documentos: ";
                    else
                        cadref = cadref+"  Documento: ";
                    cadref = cadref+"\n";
                    for(int i=0; i<ids.size(); i++)
                    {
                        Vector v = (Vector)ids.get(i);
                        cadref = cadref+" "+v.get(1).toString();
                        cadref = cadref+"\n";
                    }
                    cadref = cadref+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

            sCad = sCad.replaceAll("AL RESPECTO.", cadref);

          if (isVoucherBco)
          {
            String enc= "^XA\n";
                   enc = enc + "^MMT\n";
                   enc = enc + "^PW609\n";
                   enc = enc + "^^LL0"+(((nlineas+nr)*30)+20)+"\n";
                   enc = enc + "^LS0\n";
                   enc = enc + "^A0N,28,28\n";
                   enc = enc + "^FO10,020\n";
            sCad   = enc +"^FB609,500,0,C,0^FD"+ sCad.replace("\n","\\&")+"^FS\n^XZ\n"+"C\n";
            System.out.println("Reimpresion Voucher para BoletoBlanco: ");
            System.out.println(""+sCad);
          }

            if(SalidaImpresion.equals("USB"))
            {
                SalidaImpresion = "\\\\127.0.0.1\\"+nombreImpresora;
                 System.out.println("(Reimpresion Voucher)Impresora USB: "+SalidaImpresion);
                 //granCodigoImpresion = granCodigoImpresion.replace("^@", "${")+"}$";
                 //System.out.println("granCodigoImpresion: "+granCodigoImpresion);
            }
            System.out.println("(Reimpresion Voucher)SalidaImpresion antes (RED): "+SalidaImpresion);
            if(SalidaImpresion.equals("RED"))
            {
                  SalidaImpresion =  nombreImpresora;
                  //granCodigoImpresion = granCodigoImpresion.replace("^@", "${")+"}$";
            }
            System.out.println("(Reimpresion Voucher)SalidaImpresion despues (RED): "+SalidaImpresion);

            FileDescriptor fd = new FileDescriptor();
            FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
            PrintStream ps = new PrintStream(os);
            ps.print(sCad); // CADENA A IMPRIMIR
            ps.flush();
            os.close();
        }catch(java.io.FileNotFoundException fsctex){
            fsctex.printStackTrace();
            return false;
        }catch(Exception sctex){
            sctex.printStackTrace();
            return false;
        }
        return true;
    }

}
