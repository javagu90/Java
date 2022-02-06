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

/**
 *
 * @author ocruz
 */
public class JClsImprimeVoucher {
    
    /** Creates a new instance of JClsImprimeVoucher */
    public JClsImprimeVoucher() {
    }
    
    private String interpretaVoucher(String strVoucher, int noReimpresion){
        strVoucher = strVoucher.replaceAll("@", "|@");
        String cadenaImprimir="";
        System.out.println("getRspVoucher::: "+strVoucher);
        StringTokenizer lineas = new StringTokenizer(strVoucher, "|");
        System.out.println("#lineas::: "+lineas.countTokens());
        String formato="", info="";
        String linea;
        if(noReimpresion!=0) cadenaImprimir += "Reimpresión "+noReimpresion+".\n";
        while(lineas.hasMoreTokens()){
            linea = lineas.nextToken();
            if(linea.contains("@br"))
                cadenaImprimir += "\n";
            else{
                formato = linea.substring(1,4);
                info = linea.substring(5);
                info = info.replaceAll("\n","");
                info = rTrim(info," ");
                if(!info.equals(""))
                    cadenaImprimir += alinearTexto(formato.substring(0,1),45,info+"\n");
            }
        }
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
    
    public boolean ImprimeDatos(String RspVoucher, String SalidaImpresion, int noReimpresion){
        String sCad = interpretaVoucher(RspVoucher,noReimpresion);
        try{
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = "C:\\VOUCHER.TXT";
                
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
