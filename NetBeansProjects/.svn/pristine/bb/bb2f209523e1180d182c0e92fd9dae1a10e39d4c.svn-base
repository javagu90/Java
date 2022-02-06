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
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import subProcesos.cantidad_a_letras;

/**
 *
 * @author vgonzalez
 */
public class imprimir_voucher{
    private String numcta;
    private String nombre;
    private String fechavenc;
    private String monto;
    private String transaccion;
    private String aprobacion;
    private String CajaNumero, ClaveCajero, NombreCajero;
    private String SalidaImpresion;

    public imprimir_voucher(String nc, String n, String fv, String m, String t, String a,
            String pCajaNumero, String pClaveCajero, String pNombreCajero){
         numcta = nc;
         nombre= n;
         fechavenc = fv;
         monto = m.substring(0,m.length()-2) +"."+m.substring(m.length()-2);
         transaccion = t;
         aprobacion = a;
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
        sCad = sCad+"            BBVA BANCOMER";
        sCad = sCad+"\n\n";
        sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
        sCad = sCad+"\n";
        sCad = sCad+"     ESTRELLA ROJA S A DE C V";
        sCad = sCad+"\n";
        sCad = sCad+"      AFILIACION:  0185256409 ";
        sCad = sCad+"\n";
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
        sCad = sCad+"   FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
        sCad = sCad+"\n\n";
        sCad = sCad+"    "+nombre;
        sCad = sCad+"\n";
        sCad = sCad+"        "+"XXXXXXXXXXXX"+numcta.substring(numcta.length()-4);
        sCad = sCad+"\n";
        sCad = sCad+"             VENCE "+fechavenc.substring(0,2)+"/"+fechavenc.substring(2,4);
        sCad = sCad+"\n\n";
        sCad = sCad+"           "+transaccion;
        sCad = sCad+"\n\n";
        sCad = sCad+"       TOTAL  $ "+monto;
        sCad = sCad+"\n";
        String letras= "   ( "+new cantidad_a_letras().toLetras((long)((new Double(monto)).longValue()))  + "Pesos 00/M.N. )";
        sCad = sCad+letras;
        sCad = sCad+"\n\n";
        sCad = sCad+"        APROBACION: "+aprobacion;
        sCad = sCad+"\n\n";
        sCad = sCad+" FIRMA _____________________________";
        sCad = sCad+"\n";
        sCad = sCad+"    "+nombre;
        sCad = sCad+"\n";
        sCad = sCad+"\nPor este pagare me obligo incondicio";
        sCad = sCad+"\n"+"nalmente a pagar a la orden del banco";
        sCad = sCad+"\n"+"acreditante el importe de este titulo";
        sCad = sCad+"\n"+" Este pagare procede del contrato de";
        sCad = sCad+"\n"+"  apertura de credito que el banco";
        sCad = sCad+"\n"+"  acreditante y el tarjetahabiente";
        sCad = sCad+"\n"+"         tienen celebrado";
        sCad = sCad+"\n"+"   Pagare negociable unicamente";
        sCad = sCad+"\n"+"   en instituciones de credito";
        sCad = sCad+"\n\n";
        sCad = sCad+"Cajero: "+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
        sCad = sCad+"\n";
        sCad = sCad+" #Caja: "+CajaNumero;
        sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
        sCad = sCad+"         .";
        try{
            System.out.println(""+sCad);
            String UserHome = System.getProperty("user.home");
            if(SalidaImpresion.equals("ARCHIVO"))
                SalidaImpresion = UserHome+"\\VOUCHER.TXT";
                
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

    private String customFormat(String pattern, float value ) {
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      return myFormatter.format(value);
    }
}