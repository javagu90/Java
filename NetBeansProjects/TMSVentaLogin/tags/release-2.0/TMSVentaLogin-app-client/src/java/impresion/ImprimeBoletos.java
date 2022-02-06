package impresion;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.print.PrintService;
import tms_venta.SesionVenta;

public class ImprimeBoletos{
    private String CodigoImpresora="";
    private String granCodigoImpresion="";
    private final String txVENTA_BA="VA";
    private final String txVENTA_BR="Vr";
        
    private String Prefijo;
    private long FolioActual;
    private Calendar x = Calendar.getInstance();
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private String dtFecha = "";
    private String dtFechaVig ="";
    private SimpleDateFormat formato = new SimpleDateFormat("dd MMM yy");
    private SimpleDateFormat formatoDia = new SimpleDateFormat("EEEEE");
    private String transaccion;
    private boolean bBA=false;
    private String TipoPago;
    private int diasVigBab;
    private String NombreTaquillero;
    private String precio;
    private String corrida;
    private String contraloria;
    private Object[][] boletos;
    private Object[] boleto;
    private SesionVenta sesionVenta;
    private long empresaId;
    private String marcaImpresora;
    private String FormatoBoleto;
    private boolean sinNoAsiento;
    private String SalidaImpresion;
    private long ServicioId;
    private long EmpresaId_x;
    private String Leyenda1;
    private String Leyenda2;
    private String Leyenda3;
    
    private long fileFolioInicial = 0;
    private long fileFolioFinal = 0;
    
    private long ultFolioBoleto;

    private long ControlActual;
    private SimpleDateFormat formatoDebugFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
    String caESC = new Character((char)27).toString();
    String caLF = new Character((char)10).toString();
    String caCR = new Character((char)13).toString();
    private boolean aplicaPromo = true;

    private String servicioNombre = "";
    
    public boolean ImprimeDatos(String pTipoPago, String pPrefijo, long pFolioActual, Object[][] pBoletos,
                                String pTransaccion, int diasVigBab,
                                String pNombreTaquillero, SesionVenta pSesionVenta, Date FHSys, boolean paplicaPromo) {
        dtFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(FHSys);
        this.sesionVenta = pSesionVenta;
        this.NombreTaquillero = truncaTexto(pNombreTaquillero,15);
        this.diasVigBab=diasVigBab;
        this.TipoPago = pTipoPago;
        this.Prefijo= pPrefijo;
        this.FolioActual=pFolioActual;
        this.fileFolioFinal = this.FolioActual-1;
        this.boletos = pBoletos;
        this.transaccion=pTransaccion;
        this.aplicaPromo = paplicaPromo;
        System.out.println(formatoDebugFecha.format(new Date())+":"+"14.- Entro ImprimeDatos()");
        /****/
        //int nBoletos = boletos.length;
        int nBoletos = 0;
        for(int i=0; i<boletos.length; i++){
            boleto = boletos[i];
         if(boleto[29].toString().equals("N"))
             nBoletos++;
         //for(int y=0; y<=29; y++)   
//            System.out.println("");
//            System.out.println("Boleto["+1+"]= "+boleto[1]);
//            System.out.println("Boleto["+6+"]= "+boleto[6]);
//            System.out.println("Boleto["+31+"]= "+boleto[31]);
        }
        String[] xB = null;
        FolioActual-=nBoletos;
        ControlActual = sesionVenta.getFolioSistema();
        ControlActual-=(nBoletos-1);
        int i;
        boolean resultado=true;
        granCodigoImpresion = "";
        //for(i=0; i<nBoletos; i++){
        for(i=0; i<boletos.length; i++){
            boleto = boletos[i];
         if(boleto[29].toString().equals("N"))
         {
            xB = sesionVenta.getImpresoraBoletos(boleto[7].toString());
            if(xB==null) return false;
            SalidaImpresion = xB[0];
            marcaImpresora = xB[1];
            FormatoBoleto = xB[2];
            if(SalidaImpresion==null || marcaImpresora==null) return false;
            if(Boolean.valueOf(boleto[28].toString())) sinNoAsiento=false;
            else sinNoAsiento=true;
            this.EmpresaId_x = sesionVenta.getEmpresaId(boleto[7].toString());
            this.ServicioId = sesionVenta.getServicioId(boleto[8].toString());
            ////////////VAGL 23/01/2012 Se cambio la leyenda por la empresa del boleto
            for(int j=0; j<this.sesionVenta.getUserCon().getEmpresasOfertantes().length; j++)
            {
                if(boleto[7].toString().equals(this.sesionVenta.getUserCon().getEmpresasOfertantes()[j][1]))
                    this.Leyenda1 =this.sesionVenta.getUserCon().getEmpresasOfertantes()[j][2]; //boleto[7].toString();//sesionVenta.getValorParametro("P_LEYENDA1"+this.ServicioId, -1);
             }
            /////////////////////////////
            if(boleto[36] == null || boleto[36].toString().equals("-1"))
            {
              System.out.println("No imprime Folio Convenio");
                this.Leyenda2 = sesionVenta.getValorParametro("P_LEYENDA2"+this.EmpresaId_x, -1);
             }
            else
            {
              System.out.println("Si imprime Folio Convenio");
                this.Leyenda2 ="Folio Convenio: "+ boleto[36].toString();
             }
            this.Leyenda3 = sesionVenta.getValorParametro("P_LEYENDA3"+this.EmpresaId_x, -1);
            if(boleto[13].toString().equals("SOC"))
                this.Leyenda3 = "BOLETO DE CORTESIA. PROHIBIDA SU VENTA";

            System.out.println("Boleto No. "+i+" enviado a la impresora.");
            if(boleto[6].toString().equals("R")){
                bBA=true;
                resultado=this.setPrint();
            }
            else{
                if(this.transaccion==txVENTA_BA){
                    bBA = true;
                }
                else{
                    bBA=false;
                }
                resultado=this.setPrint();
            }
            
            System.out.println("Boleto No. "+i+" impreso. con folio: "+FolioActual);
            System.out.println("Tipo Pago. "+i+" = "+boleto[13]);
            this.FolioActual++;
            
                if(!resultado) break;
                  granCodigoImpresion = granCodigoImpresion + CodigoImpresora;
//                  granCodigoImpresion = CodigoImpresora;
//                //if(resultado){
//                    this.fileFolioInicial = (this.fileFolioFinal-nBoletos)+1;
//                    if(this.marcaImpresora.contains("SATO")) granCodigoImpresion=granCodigoImpresion+caESC+"A"+"\n"+caESC+"*X"+"\n"+caESC+"Z"+"\n";
//                    else granCodigoImpresion=granCodigoImpresion+"N\n";
//                    try{
//                        if(SalidaImpresion.equals("ARCHIVO"))
//                            SalidaImpresion = "C:\\BOL_"+boleto[19]+".TXT";//this.fileFolioInicial+"_"+this.fileFolioFinal+".TXT";
//                        FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
//                        PrintStream ps = new PrintStream(os); 
//                        ps.print(granCodigoImpresion); // CADENA A IMPRIMIR
//                        ps.flush();
//                        os.close();
//                    }catch(java.io.FileNotFoundException fsctex){
//                        return false;
//                    }catch(Exception sctex){
//                        return false;
//                    }
//                //}            
         }
            
       }
        System.out.println("Leyenda3: "+Leyenda3);
        System.out.println(formatoDebugFecha.format(new Date())+":"+"15.- Entro ImprimeDatos()");
        if(resultado){
//            this.fileFolioInicial = (this.fileFolioFinal-boletos.length)+1;
            this.fileFolioInicial = (this.fileFolioFinal-nBoletos)+1;
            if(this.marcaImpresora.contains("SATO")) granCodigoImpresion=granCodigoImpresion+caESC+"A"+"\n"+caESC+"*X"+"\n"+caESC+"Z"+"\n";
            //else granCodigoImpresion=granCodigoImpresion+"N\n"
            else granCodigoImpresion= "^@"+ ((char)10)+"O"+((char)10)+"o"+((char)10)+"OFf"+((char)10)+ granCodigoImpresion;//+"^default"+((char)10);x
            try{
                if(SalidaImpresion.equals("ARCHIVO"))
                    SalidaImpresion = "C:\\BOL_"+this.fileFolioInicial+"_"+this.fileFolioFinal+".TXT";
                FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
                PrintStream ps = new PrintStream(os); 
                ps.print(granCodigoImpresion); // CADENA A IMPRIMIR
                ps.flush();
                os.close();
            }catch(java.io.FileNotFoundException fsctex){
                return false;
            }catch(Exception sctex){
                return false;
            }
        }
        System.out.println(formatoDebugFecha.format(new Date())+":"+"16.- Final ImprimeDatos() resultado:"+resultado);
        return resultado;
    }
    
    private boolean setPrint(){
        try {
            empresaId = sesionVenta.getEmpresaId(boleto[7].toString());
            CodigoImpresora="";
            if(bBA){ // FORMATOS DE BOLETO ABIERTO
                //if(this.FormatoBoleto.equals("BOLETO_ABIERTO_AMPERSA")){
                    x.setTime(new Date());
                    x.add(Calendar.DATE,this.diasVigBab);
                    dtFechaVig= new SimpleDateFormat("EEE, dd-MMM-yy").format(x.getTime()).toUpperCase();
                    if(this.marcaImpresora.contains("SATO"))
                        CodigoImpresora = this.imprimeBoletoSATO(false, true); // ABIERTO
                    else
                        CodigoImpresora = this.imprimeBoletoZEBRA(false, true); // ABIERTO
                //}
            }
            else{ // FORMATOS DE BOLETO ESTANDAR
                System.out.println("FormatoBoleto: "+this.FormatoBoleto);
                System.out.println("marcaImpresora: "+marcaImpresora);
                if(this.FormatoBoleto.equals("BOLETO_ESTANDAR_AMPERSA")){
                    if(this.marcaImpresora.contains("SATO"))
                        CodigoImpresora = this.imprimeBoletoSATO(sinNoAsiento, false); // ESTANDAR
                    else
                        CodigoImpresora = this.imprimeBoletoZEBRA(sinNoAsiento, false); // ESTANDAR
                }
                else if(this.FormatoBoleto.equals("BOLETO_ESTANDAR_AMPERSA_G")){
                    if(this.marcaImpresora.contains("SATO"))
                        CodigoImpresora = this.imprimeBoletoSATOG(sinNoAsiento, false); // ESTANDAR
                    else
                        CodigoImpresora = this.imprimeBoletoZEBRAG(sinNoAsiento, false); // ESTANDAR
                }
                else if(this.FormatoBoleto.equals("BOLETO_ABIERTO_AMPERSA")){
                    x.setTime(new Date());
                    x.add(Calendar.DATE,this.diasVigBab);
                    dtFechaVig= new SimpleDateFormat("EEE, dd-MMM-yy").format(x.getTime()).toUpperCase();
                    if(this.marcaImpresora.contains("SATO"))
                        CodigoImpresora = this.imprimeBoletoSATO(false, true); // ABIERTO
                    else
                        CodigoImpresora = this.imprimeBoletoZEBRA(false, true); // ABIERTO
                }
                else{
                    if(this.marcaImpresora.contains("SATO"))
                        CodigoImpresora = this.imprimeBoletoSATO(sinNoAsiento, false); // ESTANDAR
                    else
                        CodigoImpresora = this.imprimeBoletoZEBRA(sinNoAsiento, false); // ESTANDAR
                }
            }
            if(CodigoImpresora.equals("")) return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    /******************** FORMATO ZEBRA *************************/
    private String imprimeBoletoZEBRA(boolean conNoAsiento, boolean BoletoAbierto){
        System.out.println("Leyenda3 en imprimeBoletoZEBRA: "+this.Leyenda3);
        System.out.println("Manda a imprimir desde aqui imprimeBoletoZEBRA... ");
         
        String CodigoFormato=""; 
        //CodigoFormato = CodigoFormato+"^@"+((char)10);
        //CodigoFormato = CodigoFormato+"^default"+((char)10);
        CodigoFormato = CodigoFormato+((char)10)+ "N\n";
        //CodigoFormato = CodigoFormato+"UA"+((char)10);--
        CodigoFormato = CodigoFormato+"R112,0\n";
        CodigoFormato = CodigoFormato+"Q538,22\n";
            // LEYENDA 1
            CodigoFormato = CodigoFormato+"A458,168,0,2,2,1,N,\""+truncaTexto(this.Leyenda1,23)+"\"\n";
            
            // TEXTO VARIABLE EN POSICION DEPENDIENDO DE NOMBRE EMPRESA
            CodigoFormato = CodigoFormato+"A205,168,0,2,1,1,N,\"Desde:\"\n";
            CodigoFormato = CodigoFormato+"A205,188,0,3,1,2,N,\""+boleto[21]+"\"\n";
            CodigoFormato = CodigoFormato+"A440,188,0,2,1,1,N,\"Hacia:\"\n";
            CodigoFormato = CodigoFormato+"A440,208,0,3,1,2,N,\""+boleto[22]+"\"\n";
            
            precio = "$ "+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"/"+ this.TipoPago +" -"+boleto[2];
            CodigoFormato = CodigoFormato+"A205,228,0,2,1,1,N,\"Precio:\"\n";
            CodigoFormato = CodigoFormato+"A205,248,0,3,1,2,N,\""+precio+"\"\n";
                servicioNombre = boleto[8].toString();
                System.out.println("servicioNombre : "+servicioNombre);
                if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                if(boleto[8].toString().equals("PULLMAN PLUS"))
                    servicioNombre = "LUJO";
                System.out.println("Ahora servicioNombre : "+servicioNombre);                
            
            if(!BoletoAbierto){
                try {
                    CodigoFormato = CodigoFormato+"A440,248,0,2,1,1,N,\"Fecha:\"\n";
                    if(boleto[26] == null)
                        CodigoFormato = CodigoFormato+"A440,268,0,1,2,2,N,\""+"-"+"\"\n";
                    else
                        CodigoFormato = CodigoFormato+"A440,268,0,1,2,2,N,\""+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\"\n";
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return "";
                }
            }
            else{
                
                // MOD: TAMAÑO DE LETRA MAS PEQUEÑO ::: CodigoFormato = CodigoFormato+"A440,268,0,2,2,1,N,\""+dtFechaVig+"\"\n";
                CodigoFormato = CodigoFormato+"A440,248,0,2,1,1,N,\"Vigencia:\"\n";
                String prom = boleto[8] +"-"+boleto[21]+"-"+boleto[22];
                //VAGL 05/09/2009 Modificacion para que salga impreso en el boleto el servicios abreviado de PULLMAN 
                //if(boleto[8].toString().equals("DIRECTO ECONOMICO") || boleto[8].toString().equals("PULLMAN PRIMERA CLASE") )
                String dia = ""; 
                try {
                        if(boleto[26] == null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ) )
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                    //CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+"Mie y Jue de Junio"+"\"\n";
                    CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+"Mie de Octubre"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+"Mie de May-Jun"+"\"\n";
                else
                    CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+dtFechaVig+"\"\n";
                
            }
            
            CodigoFormato = CodigoFormato+"A205,288,0,2,1,1,N,\"Servicio:\"\n";
            CodigoFormato = CodigoFormato+"A205,308,0,3,1,2,N,\""+servicioNombre+"\"\n";
            if(conNoAsiento){
                CodigoFormato = CodigoFormato+"A440,293,0,2,1,1,N,\"Hora:\"\n";
                CodigoFormato = CodigoFormato+"A440,313,0,1,2,2,N,\""+boleto[27]+"\"\n";
            }
            CodigoFormato = CodigoFormato+"A205,348,0,2,1,1,N,\"Nombre:\"\n";
            CodigoFormato = CodigoFormato+"A205,368,0,2,1,2,N,\""+(boleto[3]==null ? "" : truncaTexto(boleto[3].toString(),20))+"\"\n";
            if(conNoAsiento){
                CodigoFormato = CodigoFormato+"A440,338,0,2,1,1,N,\"Asiento:\"\n";
                CodigoFormato = CodigoFormato+"A440,358,0,1,2,2,N,\""+boleto[1]+"\"\n";
            }
            else{
                if(BoletoAbierto)
                    CodigoFormato = CodigoFormato+"A440,338,0,3,1,2,N,\"BOLETO ABIERTO\"\n";
            }
            
            // LEYENDA 2 Y 3
            CodigoFormato = CodigoFormato+"A185,400,0,1,1,2,N,\""+truncaTexto(this.Leyenda2, 47)+"\"\n";
            CodigoFormato = CodigoFormato+"A185,419,0,1,1,2,N,\""+truncaTexto(this.Leyenda3, 47)+"\"\n";
            
            // TEXTO FIJO
          //CodigoFormato = CodigoFormato+"A185,435,0,1,1,2,N,\"REF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
          //CodigoFormato = CodigoFormato+"A185,435,0,1,1,2,N,\"REF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A185,435,0,1,1,2,N,\""+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A185,456,0,1,1,2,N,\""+truncaTexto(this.NombreTaquillero,23)+"\"\n";
            CodigoFormato = CodigoFormato+"A185,477,0,1,1,2,N,\""+this.dtFecha+"\"\n";
            // MOD: FOLIO PROPORCIONADO POR EL SISTEMA ::: CodigoFormato = CodigoFormato+"A185,499,0,1,1,2,N,\""+this.Prefijo+" "+this.boletoId+"-"+this.folioPreimpreso+"\"\n";
            CodigoFormato = CodigoFormato+"A185,499,0,1,1,2,N,\""+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\"\n";
            
            CodigoFormato = CodigoFormato+"B390,455,0,3,3,1,45,B,\""+this.FolioActual+"\"\n";
            
            // TEXTO VERTICAL
            CodigoFormato = CodigoFormato+"A1,290,3,2,1,1,N,\""+truncaTexto(this.Leyenda1,23)+"\"\n";
            
            CodigoFormato = CodigoFormato+"A1,521,3,2,1,1,N,\"Desde:\"\n";
            CodigoFormato = CodigoFormato+"A1,441,3,1,1,2,N,\""+boleto[21]+"\"\n";
            CodigoFormato = CodigoFormato+"A20,521,3,2,1,1,N,\"Hacia:\"\n";
            CodigoFormato = CodigoFormato+"A20,441,3,1,1,2,N,\""+boleto[22]+"\"\n";
            CodigoFormato = CodigoFormato+"A12,300,3,2,1,1,N,\""+((boleto[31]==null) ?"S" :boleto[31] )+"/"+truncaTexto(servicioNombre.toString(),19)+"\"\n";
            
            if(!BoletoAbierto){
                try {
                    CodigoFormato = CodigoFormato+"A39,521,3,2,1,1,N,\"Fecha:\"\n";
                    if(boleto[26]==null)
                        CodigoFormato = CodigoFormato+"A39,441,3,1,1,2,N,\""+"-"+"\"\n";
                    else
                        CodigoFormato = CodigoFormato+"A39,441,3,1,1,2,N,\""+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\"\n";
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return "";
                }
            }
            else{
                CodigoFormato = CodigoFormato+"A39,521,3,2,1,1,N,\"Vigencia:\"\n";
                String prom = boleto[8]+"-"+boleto[21]+"-"+boleto[22];
                //if(boleto[8].toString().equals("DIRECTO ECONOMICO") || boleto[8].toString().equals("PULLMAN PRIMERA CLASE") )
                String dia = ""; 
                try {
                        if(boleto[26] == null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ) )
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                    //CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+"Mie y Jue de Junio"+"\"\n";
                    CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+"Mie de Octubre"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+"Mie de May-Jun"+"\"\n";
                else
                    CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+dtFechaVig+"\"\n";
            }
            CodigoFormato = CodigoFormato+"A58,521,3,2,1,1,N,\"Precio:\"\n";
            CodigoFormato = CodigoFormato+"A58,441,3,1,1,2,N,\""+precio+"\"\n";
          //VAGL 05/09/2009 cambio para que se vea elnumeor de asiento antes de la corrida
          //CodigoFormato = CodigoFormato+"A77,521,3,2,1,1,N,\"REF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
          //CodigoFormato = CodigoFormato+"A77,521,3,2,1,1,N,\"REF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A77,521,3,1,1,1,N,\""+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2] + "-"+(boleto[1]==null ? "-" : boleto[1])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
//          CodigoFormato = CodigoFormato+"A77,521,3,2,1,1,N,\""+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2] + "-"+(boleto[1]==null ? "-" : boleto[1])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A96,521,3,2,1,1,N,\""+truncaTexto(this.NombreTaquillero,23)+"\"\n";
            CodigoFormato = CodigoFormato+"A115,521,3,2,1,1,N,\""+this.dtFecha+"\"\n";
            CodigoFormato = CodigoFormato+"A134,521,3,1,1,1,N,\""+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\"\n";
            ControlActual++;
 
          //CodigoFormato = CodigoFormato+"B390,455,0,3,3,1,45,B,\""+this.FolioActual+"\"\n";
          //CodigoFormato = CodigoFormato+"B100,248,3,3,1,3,40,N,\""+this.FolioActual+"\"\n";
            CodigoFormato = CodigoFormato+"B100,248,3,3,1,3,40,B,\""+this.FolioActual+"\"\n";
            
            CodigoFormato = CodigoFormato+"P1\n";
            //CodigoFormato = CodigoFormato+"FK\"*\""+((char)10);--
            //CodigoFormato = CodigoFormato+"^default"+((char)10);
        return CodigoFormato;
    }
       
    /******************** FORMATO SATO *************************/
    private String imprimeBoletoSATO(boolean conNoAsiento, boolean BoletoAbierto){
        String CodigoFormato="";
            CodigoFormato = caESC+"A"+"\n";
            CodigoFormato = CodigoFormato+caESC+"A3H0001V0015"+"\n";
            // LEYENDA 1
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0550"+caESC+"V0053"+caESC+"L0201"+caESC+"XS"+truncaTexto(this.Leyenda1,23)+"\n";
            
            // TEXTO VARIABLE EN POSICION DEPENDIENDO DE NOMBRE EMPRESA
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0050"+caESC+"L0101"+caESC+"XSDesde:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0070"+caESC+"L0103"+caESC+"XS"+boleto[21]+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0088"+caESC+"L0101"+caESC+"XSHacia:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0108"+caESC+"L0103"+caESC+"XS"+boleto[22]+"\n";
            
            precio = "$ "+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"/"+ this.TipoPago +" -"+boleto[2];
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0115"+caESC+"L0101"+caESC+"XSPrecio:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0135"+caESC+"L0103"+caESC+"XS"+precio+"\n";
                //VAGL 05/09/2009 Modificacion para que salga impreso en el boleto el servicios abreviado de PULLMAN 
                servicioNombre = boleto[8].toString();
                if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                if(boleto[8].toString().equals("PULLMAN PLUS"))
                    servicioNombre = "LUJO";
            
            if(!BoletoAbierto){
                try {
                    CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0153"+caESC+"L0101"+caESC+"XSFecha:"+"\n";
                    if(boleto[26]==null)
                        CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0202"+caESC+"XS"+"-"+"\n";
                    else
                        CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0202"+caESC+"XS"+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\n";
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return "";
                }
            }
            else{
                
                CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0153"+caESC+"L0101"+caESC+"XSVigencia:"+"\n";
                String prom = boleto[8]+"-"+boleto[21]+"-"+boleto[22];
                String dia = ""; 
                try {
                        if(boleto[26]==null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
              //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ) )
               if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                   // CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+"Mie y Jue de Junio"+"\n";
                    CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+"Mie de Octubre"+"\n";
                    //CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+"Mie de May-Jun"+"\n";
                else
                    CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+dtFechaVig+"\n";
                
                
            }
            
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0185"+caESC+"L0101"+caESC+"XSServicio:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0205"+caESC+"L0103"+caESC+"XS"+servicioNombre+"\n";
            if(conNoAsiento){
                CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0204"+caESC+"L0101"+caESC+"XSHora:"+"\n";
                CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0224"+caESC+"L0202"+caESC+"XS"+boleto[27]+"\n";
            }
            
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0255"+caESC+"L0101"+caESC+"XSNombre:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0350"+caESC+"V0275"+caESC+"L0102"+caESC+"XS"+(boleto[3]==null ? "" : truncaTexto(boleto[3].toString(),20))+"\n";
            
            if(conNoAsiento){
                CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0255"+caESC+"L0101"+caESC+"XSAsiento:"+"\n";
                CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0275"+caESC+"L0202"+caESC+"XS"+boleto[1]+"\n";
            }
            else{
                if(BoletoAbierto)
                    CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0255"+caESC+"L0103"+caESC+"XSBOLETO ABIERTO"+"\n";
            }
            
            // LEYENDA 2 Y 3
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0340"+caESC+"V0303"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.Leyenda2, 47)+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0340"+caESC+"V0322"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.Leyenda3, 47)+"\n";
            
            // TEXTO FIJO
            //CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0340"+caESC+"V0335"+caESC+"L0101"+caESC+"XSREF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[11]==null ? "-" : boleto[11])+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0340"+caESC+"V0335"+caESC+"L0101"+caESC+"XS"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[11]==null ? "-" : boleto[11])+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0340"+caESC+"V0353"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.NombreTaquillero,23)+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0340"+caESC+"V0372"+caESC+"L0101"+caESC+"XS"+this.dtFecha+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0340"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\n";
            
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0550"+caESC+"V0348"+caESC+"BG01045>G"+this.FolioActual+"\n";
            CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0550"+caESC+"V0393"+caESC+"L0101"+caESC+"XS"+this.FolioActual+"\n";
            
            // TEXTO VERTICAL
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0060"+caESC+"V0150"+caESC+"L0201"+caESC+"XS"+truncaTexto(this.Leyenda1,23)+"\n";
            
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0060"+caESC+"V0390"+caESC+"L0103"+caESC+"XSDesde:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0060"+caESC+"V0330"+caESC+"L0203"+caESC+"XS"+boleto[21]+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0110"+caESC+"V0390"+caESC+"L0103"+caESC+"XSHacia:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0110"+caESC+"V0330"+caESC+"L0203"+caESC+"XS"+boleto[22]+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0090"+caESC+"V0205"+caESC+"L0102"+caESC+"XS"+((boleto[31]==null) ?"S" :boleto[31] )+"/"+truncaTexto(servicioNombre.toString(),19)+"\n";
            if(!BoletoAbierto){
                try {
                    CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0390"+caESC+"L0102"+caESC+"XSFecha:"+"\n";
                    if(boleto[26]==null)
                        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"-"+"\n";
                    else
                        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\n";
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return "";
                }
            }
            else{
                CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0390"+caESC+"L0102"+caESC+"XSVigencia Boleto Abierto:"+"\n";
                String prom = boleto[8]+"-"+boleto[21]+"-"+boleto[22];
                //if(boleto[8].toString().equals("DIRECTO ECONOMICO") || boleto[8].toString().equals("PULLMAN PRIMERA CLASE") )
               String dia = ""; 
                try {
                        if(boleto[26] == null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
              //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ) )
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                  //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"Mie y Jue de Junio\n";
                    CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"Mie de Octubre\n";
                    //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"Mie de May-Jun\n";
                else
                    CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+dtFechaVig+"\n";
            }
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0182"+caESC+"V0390"+caESC+"L0102"+caESC+"XSPrecio:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0182"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+precio+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0209"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[11]==null ? "-" : boleto[11])+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0231"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.NombreTaquillero,23)+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0253"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+this.dtFecha+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0275"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\n";
            ControlActual++;
            
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0231"+caESC+"V0200"+caESC+"BG01045>G"+this.FolioActual+"\n";
            CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0278"+caESC+"V0200"+caESC+"L0102"+caESC+"XS"+this.FolioActual+"\n";
            
            CodigoFormato = CodigoFormato+caESC+"Q1"+"\n";
            CodigoFormato = CodigoFormato+caESC+"Z"+"\n";
            
        return CodigoFormato;
    }
 
    private String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }
    
    private String truncaTexto(String texto, int lim) {
        if(texto==null || texto.equals("")) return "";
        if(texto.length()>lim) return texto.substring(0,lim);
        return texto;
    }
    
    /*** folio invertido **/
    /******************** FORMATO ZEBRA *************************/
    private String imprimeBoletoZEBRAG(boolean conNoAsiento, boolean BoletoAbierto){
        String CodigoFormato="";
        CodigoFormato = "N\n";
        CodigoFormato = CodigoFormato+"R112,0\n";
        CodigoFormato = CodigoFormato+"Q538,22\n";
            // LEYENDA 1
            CodigoFormato = CodigoFormato+"A190,460,2,2,2,1,N,\""+truncaTexto(this.Leyenda1,23)+"\"\n";
            
            // TEXTO VARIABLE EN POSICION DEPENDIENDO DE NOMBRE EMPRESA
            CodigoFormato = CodigoFormato+"A430,460,2,2,1,1,N,\"Desde:\"\n";
            CodigoFormato = CodigoFormato+"A430,444,2,3,1,2,N,\""+boleto[21]+"\"\n";
            CodigoFormato = CodigoFormato+"A185,444,2,2,1,1,N,\"Hacia:\"\n";
            CodigoFormato = CodigoFormato+"A185,428,2,3,1,2,N,\""+boleto[22]+"\"\n";
            
            precio = "$ "+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"/"+ this.TipoPago +" -"+boleto[2];
            CodigoFormato = CodigoFormato+"A430,400,2,2,1,1,N,\"Precio:\"\n";
            CodigoFormato = CodigoFormato+"A430,384,2,3,1,2,N,\""+precio+"\"\n";
                //VAGL 05/09/2009 Modificacion para que salga impreso en el boleto el servicios abreviado de PULLMAN 
                servicioNombre = boleto[8].toString();
                if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                if(boleto[8].toString().equals("PULLMAN PLUS"))
                    servicioNombre = "LUJO";
            
            if(!BoletoAbierto){
                try {
                    CodigoFormato = CodigoFormato+"A185,384,2,2,1,1,N,\"Fecha:\"\n";
                    if(boleto[26]==null)
                        CodigoFormato = CodigoFormato+"A185,368,2,1,2,2,N,\""+"-"+"\"\n";
                    else
                        CodigoFormato = CodigoFormato+"A185,368,2,1,2,2,N,\""+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\"\n";
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return "";
                }
            }
            else{
                CodigoFormato = CodigoFormato+"A185,384,2,2,1,1,N,\"Vigencia:\"\n";
                String prom = boleto[8]+"-"+boleto[21]+"-"+boleto[22];
                //if(boleto[8].toString().equals("DIRECTO ECONOMICO") || boleto[8].toString().equals("PULLMAN PRIMERA CLASE") )
               
                String dia = ""; 
                try {
                        if(boleto[26] == null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
              //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ) )
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                  //CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+"Mie y Jue de Junio"+"\"\n";
                    CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+"Mie de Octubre"+"\"\n";
                   // CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+"Mie de May-Jun"+"\"\n";
                else
                    CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+dtFechaVig+"\"\n";
                
            }
            
            CodigoFormato = CodigoFormato+"A430,340,2,2,1,1,N,\"Servicio:\"\n";
            CodigoFormato = CodigoFormato+"A430,324,2,3,1,2,N,\""+servicioNombre+"\"\n";
            if(conNoAsiento){
                CodigoFormato = CodigoFormato+"A185,324,2,2,1,1,N,\"Hora:\"\n";
                CodigoFormato = CodigoFormato+"A185,308,2,1,2,2,N,\""+boleto[27]+"\"\n";
            }
            CodigoFormato = CodigoFormato+"A430,280,2,2,1,1,N,\"Nombre:\"\n";
            CodigoFormato = CodigoFormato+"A430,264,2,2,1,2,N,\""+(boleto[3]==null ? "" : truncaTexto(boleto[3].toString(),20))+"\"\n";
            if(conNoAsiento){
                CodigoFormato = CodigoFormato+"A185,264,2,2,1,1,N,\"Asiento:\"\n";
                CodigoFormato = CodigoFormato+"A185,248,2,1,2,2,N,\""+boleto[1]+"\"\n";
            }
            else{
                if(BoletoAbierto)
                    CodigoFormato = CodigoFormato+"A185,264,2,3,1,2,N,\"BOLETO ABIERTO\"\n";
            }
            
            // LEYENDA 2 Y 3
            CodigoFormato = CodigoFormato+"A430,220,2,1,1,2,N,\""+truncaTexto(this.Leyenda2, 47)+"\"\n";
            CodigoFormato = CodigoFormato+"A430,201,2,1,1,2,N,\""+truncaTexto(this.Leyenda3, 47)+"\"\n";
            
            // TEXTO FIJO
            //CodigoFormato = CodigoFormato+"A430,185,2,1,1,2,N,\"REF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A430,185,2,1,1,2,N,\""+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A430,164,2,1,1,2,N,\""+truncaTexto(this.NombreTaquillero,23)+"\"\n";
            CodigoFormato = CodigoFormato+"A430,143,2,1,1,2,N,\""+this.dtFecha+"\"\n";
            CodigoFormato = CodigoFormato+"A430,122,2,1,1,2,N,\""+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\"\n";
            
            CodigoFormato = CodigoFormato+"B380,165,2,3,3,1,45,B,\""+this.FolioActual+"\"\n";
            
            // TEXTO VERTICAL
            CodigoFormato = CodigoFormato+"A603,200,1,2,1,1,N,\""+truncaTexto(this.Leyenda1,23)+"\"\n";
            
            CodigoFormato = CodigoFormato+"A603,10,1,2,1,1,N,\"Desde:\"\n";
            CodigoFormato = CodigoFormato+"A603,90,1,1,1,2,N,\""+boleto[21]+"\"\n";
            CodigoFormato = CodigoFormato+"A584,10,1,2,1,1,N,\"Hacia:\"\n";
            CodigoFormato = CodigoFormato+"A584,90,1,1,1,2,N,\""+boleto[22]+"\"\n";
            CodigoFormato = CodigoFormato+"A598,190,1,2,1,1,N,\""+((boleto[31]==null) ?"S" :boleto[31] )+"/"+truncaTexto(servicioNombre.toString(),19)+"\"\n";
            
            if(!BoletoAbierto){
                try {
                    CodigoFormato = CodigoFormato+"A565,10,1,2,1,1,N,\"Fecha:\"\n";
                    if(boleto[26]==null)
                        CodigoFormato = CodigoFormato+"A565,90,1,1,1,2,N,\""+"-"+"\"\n";
                    else
                        CodigoFormato = CodigoFormato+"A565,90,1,1,1,2,N,\""+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\"\n";
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return "";
                }
            }
            else{
                CodigoFormato = CodigoFormato+"A565,10,1,2,1,1,N,\"Vigencia:\"\n";
                String prom = boleto[8]+"-"+boleto[21]+"-"+boleto[22];
                //if(boleto[8].toString().equals("DIRECTO ECONOMICO") || boleto[8].toString().equals("PULLMAN PRIMERA CLASE") )
                String dia = ""; 
                try {
                        if(boleto[26] == null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
              //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ))
               if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                  //CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+"Mie y Jue de Junio"+"\"\n";
                    CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+"Mie de Octubre"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+"Mie de May-Jun"+"\"\n";
                else
                    CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+dtFechaVig+"\"\n";
            }
            CodigoFormato = CodigoFormato+"A546,10,1,2,1,1,N,\"Precio:\"\n";
            CodigoFormato = CodigoFormato+"A546,90,1,1,1,2,N,\""+precio+"\"\n";
            //CodigoFormato = CodigoFormato+"A527,10,1,2,1,1,N,\"REF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A527,10,1,1,1,1,N,\""+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
//          CodigoFormato = CodigoFormato+"A527,10,1,2,1,1,N,\""+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[11]==null ? "-" : boleto[11])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+sesionVenta.getUserCon().getTerminalNombre()+"\"\n";
            CodigoFormato = CodigoFormato+"A508,10,1,2,1,1,N,\""+truncaTexto(this.NombreTaquillero,23)+"\"\n";
            CodigoFormato = CodigoFormato+"A489,10,1,2,1,1,N,\""+this.dtFecha+"\"\n";
            CodigoFormato = CodigoFormato+"A470,10,1,1,1,1,N,\""+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\"\n";
            ControlActual++;
            
            CodigoFormato = CodigoFormato+"B508,200,1,3,1,3,40,N,\""+this.FolioActual+"\"\n";
            
            CodigoFormato = CodigoFormato+"P1\n";
        return CodigoFormato;
    }
       
    /******************** FORMATO SATO GIRADO *************************/
    private String imprimeBoletoSATOG(boolean conNoAsiento, boolean BoletoAbierto){
        String CodigoFormato="";
        CodigoFormato = caESC+"A"+"\n";
        CodigoFormato = CodigoFormato+caESC+"A3H0001V0015"+"\n";
        // LEYENDA 1
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0325"+caESC+"V0300"+caESC+"L0201"+caESC+"XS"+truncaTexto(this.Leyenda1,23)+"\n";

        // TEXTO VARIABLE EN POSICION DEPENDIENDO DE NOMBRE EMPRESA
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0335"+caESC+"L0101"+caESC+"XSDesde:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0310"+caESC+"L0103"+caESC+"XS"+boleto[21]+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0320"+caESC+"V0335"+caESC+"L0101"+caESC+"XSHacia:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0320"+caESC+"V0310"+caESC+"L0103"+caESC+"XS"+boleto[22]+"\n";

        precio = "$ "+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"/"+ this.TipoPago +" -"+boleto[2];
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0270"+caESC+"L0101"+caESC+"XSPrecio:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0250"+caESC+"L0103"+caESC+"XS"+precio+"\n";
                //VAGL 05/09/2009 Modificacion para que salga impreso en el boleto el servicios abreviado de PULLMAN 
                servicioNombre = boleto[8].toString();
                if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                if(boleto[8].toString().equals("PULLMAN PLUS"))
                    servicioNombre = "LUJO";

        if(!BoletoAbierto){
            try {
                CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0380"+caESC+"V0210"+caESC+"L0101"+caESC+"XSFecha:"+"\n";
                if(boleto[26]==null)
                    CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0202"+caESC+"XS"+"-"+"\n";
                else
                    CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0202"+caESC+"XS"+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\n";
            } catch (ParseException ex) {
                ex.printStackTrace();
                return "";
            }
        }
        else{
                CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0380"+caESC+"V0210"+caESC+"L0101"+caESC+"XSVigencia:"+"\n";
                String prom = boleto[8]+"-"+boleto[21]+"-"+boleto[22];
                //if(boleto[8].toString().equals("DIRECTO ECONOMICO") || boleto[8].toString().equals("PULLMAN PRIMERA CLASE") )
                String dia = ""; 
                try {
                        if(boleto[26] == null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
              //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ))
              if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                   //CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+"Mie y Jue de Junio"+"\n";
                    CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+"Mie de Octubre "+"\n";
                    //CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+"Mie de May-Jun "+"\n";
                else
                    CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+dtFechaVig+"\n";
            
        }

        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0200"+caESC+"L0101"+caESC+"XSServicio:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0180"+caESC+"L0103"+caESC+"XS"+servicioNombre+"\n";
        if(conNoAsiento){
            CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0290"+caESC+"V0170"+caESC+"L0101"+caESC+"XSHora:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0180"+caESC+"V0180"+caESC+"L0202"+caESC+"XS"+boleto[27]+"\n";
        }

        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0140"+caESC+"L0101"+caESC+"XSNombre:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0120"+caESC+"L0102"+caESC+"XS"+(boleto[3]==null ? "" : truncaTexto(boleto[3].toString(),20))+"\n";

        if(conNoAsiento){
            CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0290"+caESC+"V0120"+caESC+"L0101"+caESC+"XSAsiento:"+"\n";
            CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0120"+caESC+"V0125"+caESC+"L0202"+caESC+"XS"+boleto[1]+"\n";
        }
        else{
            if(BoletoAbierto)
                CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0290"+caESC+"V0120"+caESC+"L0103"+caESC+"XSBOLETO ABIERTO"+"\n";
        }

        // LEYENDA 2 Y 3
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0112"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.Leyenda2, 47)+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0097"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.Leyenda3, 47)+"\n";

        // TEXTO FIJO
        //CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0080"+caESC+"L0101"+caESC+"XSREF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[11]==null ? "-" : boleto[11])+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0080"+caESC+"L0101"+caESC+"XS"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[11]==null ? "-" : boleto[11])+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0061"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.NombreTaquillero,23)+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0042"+caESC+"L0101"+caESC+"XS"+this.dtFecha+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0565"+caESC+"V0023"+caESC+"L0101"+caESC+"XS"+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\n";

        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0320"+caESC+"V0070"+caESC+"BG01045>G"+this.FolioActual+"\n";
        CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0320"+caESC+"V0022"+caESC+"L0102"+caESC+"XS"+this.FolioActual+"\n";

        // TEXTO VERTICAL
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0080"+caESC+"V0150"+caESC+"L0201"+caESC+"XS"+truncaTexto(this.Leyenda1,23)+"\n";

        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0615"+caESC+"V0390"+caESC+"L0103"+caESC+"XSDesde:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0615"+caESC+"V0330"+caESC+"L0203"+caESC+"XS"+boleto[21]+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0653"+caESC+"V0390"+caESC+"L0103"+caESC+"XSHacia:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0653"+caESC+"V0330"+caESC+"L0203"+caESC+"XS"+boleto[22]+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0645"+caESC+"V0200"+caESC+"L0102"+caESC+"XS"+((boleto[31]==null) ?"S" :boleto[31] )+"/"+truncaTexto(servicioNombre.toString(),19)+"\n";
        if(!BoletoAbierto){
            try {
                CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0390"+caESC+"L0102"+caESC+"XSFecha:"+"\n";
                if(boleto[26]==null)
                    CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"-"+"\n";
                else
                    CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+formato.format(formatoFecha.parse(boleto[26].toString())).toUpperCase()+"\n";
            } catch (ParseException ex) {
                ex.printStackTrace();
                return "";
            }
        }
        else{
                CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0390"+caESC+"L0102"+caESC+"XSVigencia Boleto Abierto:"+"\n";
                String prom = boleto[8]+"-"+boleto[21]+"-"+boleto[22];
                //if(boleto[8].toString().equals("DIRECTO ECONOMICO") || boleto[8].toString().equals("PULLMAN PRIMERA CLASE") )
                String dia = ""; 
                try {
                        if(boleto[26] == null)
                            dia = "";
                        else
                            dia = formatoDia.format(formatoFecha.parse(boleto[26].toString())).toUpperCase();
                        System.out.println("Dia del Boleto: "+dia);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
              //if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU")) && aplicaPromo && (dia.equals("MIÉRCOLES") || dia.equals("JUEVES") ) )
               if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-NORTE") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-NORTE-CAPU") || prom.toUpperCase().equals("DIRECTO ECONOMICO-SMA-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-SMA"))  && aplicaPromo && (dia.equals("MIÉRCOLES") )    )
                  //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"Mie y Jue de Junio\n";
                    CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"Mie de Octubre\n";
                    //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"Mie de May-Jun\n";
                else
                    CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+dtFechaVig+"\n";
        }
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0725"+caESC+"V0390"+caESC+"L0102"+caESC+"XSPrecio:"+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0725"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+precio+"\n";
        //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0758"+caESC+"V0390"+caESC+"L0101"+caESC+"XSREF"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[11]==null ? "-" : boleto[11])+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0758"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+(boleto[26]==null ? "-" : boleto[26])+"-"+(boleto[27]==null ? "-" : boleto[27])+"-"+(boleto[1]==null ? "-" : boleto[1])+"-"+customFormat("##,##0.00",Double.valueOf(boleto[5].toString()))+"-"+boleto[2]+"-"+(boleto[11]==null ? "-" : boleto[11])+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0776"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+truncaTexto(this.NombreTaquillero,23)+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0794"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+this.dtFecha+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0812"+caESC+"V0390"+caESC+"L0101"+caESC+"XS"+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\n";
        ControlActual++;

        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0774"+caESC+"V0150"+caESC+"BG01045>G"+this.FolioActual+"\n";
        CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0819"+caESC+"V0150"+caESC+"L0101"+caESC+"XS"+this.FolioActual+"\n";

        CodigoFormato = CodigoFormato+caESC+"Q1"+"\n";
        CodigoFormato = CodigoFormato+caESC+"Z"+"\n";
        
        return CodigoFormato;
    }
    
}