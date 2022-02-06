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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;

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
    String fechaBolBco= "";
    private String dtFechaVig ="";
    private String dtFechaVigBco ="";
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
    private String nombreImpresora;
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
    private String juconi = "";
    
    public boolean ImprimeDatos(String pTipoPago, String pPrefijo, long pFolioActual, Object[][] pBoletos,
                                String pTransaccion, int diasVigBab,
                                String pNombreTaquillero, SesionVenta pSesionVenta, Date FHSys, boolean paplicaPromo, String pjuconi) {
        dtFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(FHSys);
        fechaBolBco = new SimpleDateFormat("MMMM dd - HH:mm").format(FHSys).toUpperCase();        
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
        this.juconi= pjuconi;
        System.out.println(formatoDebugFecha.format(new Date())+":"+"14.- Entro ImprimeDatos()");
        System.out.println("Texto Juconi: "+juconi);
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
        Object[] boletoTmp =  boletos[0];
        if(!sesionVenta.isVtaBolBco(boletoTmp[7].toString()))
        {
            FolioActual-=nBoletos;
        ControlActual = sesionVenta.getFolioSistema();
        ControlActual-=(nBoletos-1);
        }
        int i;
        boolean resultado=true;
        granCodigoImpresion = "";
        //for(i=0; i<nBoletos; i++){
        int n=0;
        for(i=0; i<boletos.length; i++){
            boleto = boletos[i];
         if(boleto[29].toString().equals("N"))
         {
            xB = sesionVenta.getImpresoraBoletos(boleto[7].toString());
            if(xB==null) return false;
            SalidaImpresion = xB[0];
            marcaImpresora = xB[1];
            FormatoBoleto = xB[2];
            nombreImpresora = xB[3];
            if(SalidaImpresion==null || marcaImpresora==null) return false;
            if(Boolean.valueOf(boleto[28].toString())) sinNoAsiento=false;
            else sinNoAsiento=true;
            this.EmpresaId_x = sesionVenta.getEmpresaId(boleto[7].toString());
            this.ServicioId = sesionVenta.getServicioId(boleto[8].toString());
            this.Leyenda1 = sesionVenta.getValorParametro("P_LEYENDA1"+this.ServicioId, -1);
            this.Leyenda2 = sesionVenta.getValorParametro("P_LEYENDA2"+this.EmpresaId_x, -1);
            this.Leyenda3 = sesionVenta.getValorParametro("P_LEYENDA3"+this.EmpresaId_x, -1);
            if(boleto[13].toString().equals("SOC"))
                this.Leyenda3 = "BOLETO DE CORTESIA. PROHIBIDA SU VENTA";
            if(n==0 && !juconi.equals(""))
            {
                Leyenda2=juconi;
                n++;
            }
            System.out.println("Boleto No. "+i+" enviado a la impresora.");
            if(boleto[6].toString().equals("R") && !sesionVenta.isBolRedCer()){
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
            if(!sesionVenta.isVtaBolBco(boleto[7].toString()))
                System.out.println("Boleto No. "+i+" impreso. con folio: "+FolioActual);
            else
                System.out.println("Boleto No. "+i+" impreso. con folio: "+boleto[19]);
            System.out.println("Tipo Pago. "+i+" = "+boleto[13]);
            if(!sesionVenta.isVtaBolBco(boleto[7].toString()))
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
        System.out.println(formatoDebugFecha.format(new Date())+":"+"15.- 0. ImprimeDatos()");
        if(resultado){
//            this.fileFolioInicial = (this.fileFolioFinal-boletos.length)+1;
            this.fileFolioInicial = (this.fileFolioFinal-nBoletos)+1;
            if(this.marcaImpresora.contains("SATO")) granCodigoImpresion=granCodigoImpresion+caESC+"A"+"\n"+caESC+"*X"+"\n"+caESC+"Z"+"\n";
            //else granCodigoImpresion=granCodigoImpresion+"N\n"
            else granCodigoImpresion= "^@"+ ((char)10)+"O"+((char)10)+"o"+((char)10)+"OFf"+((char)10)+ granCodigoImpresion;//+"^default"+((char)10);x
            try{
                String UserHome = System.getProperty("user.home");
                if(SalidaImpresion.equals("ARCHIVO"))
                    SalidaImpresion = UserHome+"\\BOL_"+this.fileFolioInicial+"_"+this.fileFolioFinal+".TXT";
                if(SalidaImpresion.equals("USB"))
                {
                    SalidaImpresion = "\\\\"+sesionVenta.getUserCon().getCaja()+"\\"+nombreImpresora;
                     System.out.println("Impresora USB: "+SalidaImpresion);
                     granCodigoImpresion = granCodigoImpresion.replace("^@", "${")+"}$";
                     //System.out.println("granCodigoImpresion: "+granCodigoImpresion);
                }
                System.out.println("SalidaImpresion antes (RED): "+SalidaImpresion);
                if(SalidaImpresion.equals("RED"))
                {
                      SalidaImpresion =  nombreImpresora;
                      granCodigoImpresion = granCodigoImpresion.replace("^@", "${")+"}$";
                }
                System.out.println("SalidaImpresion antes de imprimir: "+SalidaImpresion);
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
                    {
                        if(this.FormatoBoleto.equals("BOLETO_ESTANDAR_BLANCO"))// ABIERTO BLANCO
                        {
                            x.setTime(new Date());
                            System.out.println("x: "+x);
                            x.add(Calendar.DATE,this.diasVigBab);
                            System.out.println("x2: "+x);
                            Timestamp fechaHoraViaje  = new Timestamp(x.getTime().getTime());
                            System.out.println("fechaHoraViaje: "+fechaHoraViaje);
                            DateFormat df5 = DateFormat.getDateInstance(DateFormat.FULL);
                            String dtFechaVigBco = df5.format(fechaHoraViaje);
                            System.out.println("dtFechaVigBco: "+dtFechaVigBco);
                            dtFechaVigBco= dtFechaVigBco.replaceFirst(" ", ", ").toUpperCase().replace(" DE ", " ");
                            System.out.println("dtFechaVigBco2: "+dtFechaVigBco);
                            CodigoImpresora = this.imprimeBoletoBlancoZEBRA(false, true,dtFechaVigBco); // ABIERTO
                        }
                        else
                            CodigoImpresora = this.imprimeBoletoZEBRA(false, true); // ABIERTO
                    }
                //}
            }
            else{ // FORMATOS DE BOLETO ESTANDAR
                    System.out.println("FormatoBoleto: "+this.FormatoBoleto);
                    System.out.println("marcaImpresora: "+marcaImpresora);
                    System.out.println("SalidaImpresion: "+SalidaImpresion);
                    System.out.println("nombreImpresora: "+nombreImpresora);
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
                    else if(this.FormatoBoleto.equals("BOLETO_ESTANDAR_BLANCO")){
                            CodigoImpresora = this.imprimeBoletoBlancoZEBRA(sinNoAsiento, false,""); // ESTANDAR
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
        boolean inter = false;
        System.out.println("Leyenda3 en imprimeBoletoZEBRA: "+this.Leyenda3);
        System.out.println("Manda a imprimir desde aqui imprimeBoletoZEBRA... ");
        servicioNombre = boleto[8].toString();
//        12   Dígitos de folio
//        3     Origen
//        3      Destino
//        2      Tipo pasajero
//        6       Importe (2 decimales)
//        10    Fecha Viaje
//        3      Ciudad Origen Corrida (Están por ser agregados)
//        3      Servicio  (Están por ser agregados)        
        //tipo pasajero
        String folioPreinpreso = ""+boleto[19];
        for(int j=folioPreinpreso.length(); j<12; j++) folioPreinpreso = "0"+folioPreinpreso;
        String origen = sesionVenta.getEstadoId(""+boleto[21]);
        String destino = sesionVenta.getEstadoId(""+boleto[22]);
        String tipoPasajero = sesionVenta.getTmsTiposPasajeroId(""+boleto[2]);
        String importe = customFormat("##,##0.00",Double.valueOf(boleto[5].toString())).replace(".","");
        for(int j=importe.length(); j<6; j++) importe = "0"+importe;
        String fechaViaje = (boleto[26]==null ? "00/00/0000" : boleto[26].toString());
          String f[] = fechaViaje.split("/");
         fechaViaje = f[0]+f[1]+f[2].substring(2)+""+(boleto[27]==null ? "0000" : boleto[27].toString().replace(":",""));

        //String ciudadVenta = sesionVenta.getEstadoId(sesionVenta.getUserCon().getTerminalNombre());
        //String OrigenCorrida = sesionVenta.getOrigenCorrida(boleto[21].toString(), (boleto[22]==null?"":boleto[22].toString()), servicioNombre);
        //String ciudadOriogenCorrida = sesionVenta.getEstadoId(OrigenCorrida);
        String ciudadOriogenCorrida = "";
        if(sesionVenta.isBolRedCer())
            ciudadOriogenCorrida = sesionVenta.getEstadoId((boleto[21].toString().equals(sesionVenta.getOrigenRedIda())?sesionVenta.getOrigenCorridaRedIda():sesionVenta.getOrigenCorridaRedReg()));
        else
            ciudadOriogenCorrida =  sesionVenta.getEstadoId(sesionVenta.getOrigenCorrida(boleto[21].toString(), (boleto[22]==null?"":boleto[22].toString()), servicioNombre));
        String servicio = ""+sesionVenta.getServicioId(servicioNombre);
        for(int j=servicio.length(); j<3; j++) servicio = "0"+servicio;
        String codigoBarrasCorto = folioPreinpreso+origen+destino+tipoPasajero+importe+fechaViaje;
        String codigoBarrasLargo = codigoBarrasCorto+ciudadOriogenCorrida+servicio;
        if(servicioNombre.indexOf("INTERMEDIO")>=0)
            inter = true;
        System.out.println("codigoBarrasCorto: "+codigoBarrasCorto);
        System.out.println("codigoBarrasLargo: "+codigoBarrasLargo);
        System.out.println("inter: "+inter);

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
                for(int ju=0; ju<sesionVenta.getNombresServicios().size(); ju++)
                {
                    if(sesionVenta.getNombresServicios().get(ju).toString().equals(boleto[8].toString()))
                        servicioNombre = sesionVenta.getImpresionNombreBoleto().get(ju).toString();
                }
                System.out.println("servicioNombre : "+servicioNombre);
                //if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                if(servicioNombre.equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                //if(boleto[8].toString().equals("PULLMAN PLUS"))
                if(servicioNombre.equals("PULLMAN PLUS"))
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
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                    //CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+"Mie y Jue de Junio"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+"Mie de Octubre"+"\"\n";
                   // CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+"Feb26/Mar5y12"+"\"\n";
                      CodigoFormato = CodigoFormato+"A440,268,0,3,1,2,N,\""+"Feb26/Mar5y12"+"\"\n";

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
            if(inter)
            {
                //CodigoFormato = CodigoFormato+"B185,455,0,3,3,1,45,B,\""+codigoBarrasCorto+"\"\n";
                //CodigoFormato = CodigoFormato+"B170,455,0,3,1,3,40,B,\""+codigoBarrasCorto+"\"\n";
                //CodigoFormato = CodigoFormato+"B185,457,0,1,2,5,40,B,\""+codigoBarrasCorto+"\"\n";
                CodigoFormato = CodigoFormato+"B185,457,0,1,1,5,40,B,\""+codigoBarrasLargo+"\"\n";
                CodigoFormato = CodigoFormato+"A440,15,0,1,1,2,N,\""+truncaTexto(this.NombreTaquillero,23)+"\"\n";
                CodigoFormato = CodigoFormato+"A440,39,0,1,1,2,N,\""+this.dtFecha+"\"\n";
                // MOD: FOLIO PROPORCIONADO POR EL SISTEMA ::: CodigoFormato = CodigoFormato+"A185,499,0,1,1,2,N,\""+this.Prefijo+" "+this.boletoId+"-"+this.folioPreimpreso+"\"\n";
                CodigoFormato = CodigoFormato+"A440,61,0,1,1,2,N,\"Folio: "+(sesionVenta.isVtaBolBco(boleto[7].toString())?(""+boleto[19]):this.FolioActual)+"\"\n";
            }
            else
            {
                //CodigoFormato = CodigoFormato+"A185,456,0,1,1,2,N,\""+truncaTexto(this.NombreTaquillero,23)+"\"\n";
                CodigoFormato = CodigoFormato+"A185,456,0,1,1,2,N,\"Folio: "+(sesionVenta.isVtaBolBco(boleto[7].toString())?(""+boleto[19]):this.FolioActual)+"\"\n";
                CodigoFormato = CodigoFormato+"A185,477,0,1,1,2,N,\""+this.dtFecha+"\"\n";
                // MOD: FOLIO PROPORCIONADO POR EL SISTEMA ::: CodigoFormato = CodigoFormato+"A185,499,0,1,1,2,N,\""+this.Prefijo+" "+this.boletoId+"-"+this.folioPreimpreso+"\"\n";
                //CodigoFormato = CodigoFormato+"A185,499,0,1,1,2,N,\"Folio: "+(sesionVenta.isVtaBolBco()?(""+boleto[19]):this.FolioActual)+"\"\n";
                CodigoFormato = CodigoFormato+"A185,499,0,1,1,2,N,\""+truncaTexto(this.NombreTaquillero,23)+"\"\n";


                //CodigoFormato = CodigoFormato+"A185,499,0,1,1,2,N,\""+this.Prefijo+""+this.empresaId+""+sesionVenta.getFormatoFolioSistema(ControlActual)+"\"\n";
                //CodigoFormato = CodigoFormato+"B390,455,0,3,3,1,45,B,\""+(sesionVenta.isVtaBolBco()?(""+boleto[19]):this.FolioActual)+"\"\n";
                //CodigoFormato = CodigoFormato+"B390,457,0,1,3,1,45,B,\""+(sesionVenta.isVtaBolBco()?(""+boleto[19]):this.FolioActual)+"\"\n";
                //codigo de barras lineal
                //CodigoFormato = CodigoFormato+"B190,457,0,1,1,5,40,B,\""+codigoBarrasLargo+"\"\n";
                //1 => Codigo 128
            }
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
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                    //CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+"Mie y Jue de Junio"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+"Mie de Octubre"+"\"\n";
                   // CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+"Feb 26 y Marzo 5 y 12"+"\"\n";
                      CodigoFormato = CodigoFormato+"A39,441,3,2,1,1,N,\""+"Feb26/Mar5y12"+"\"\n";

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
            CodigoFormato = CodigoFormato+"B100,248,3,3,1,3,40,B,\""+(sesionVenta.isVtaBolBco(boleto[7].toString())?(""+boleto[19]):this.FolioActual)+"\"\n";
            if(!inter)
                CodigoFormato = CodigoFormato+"b545,293,D,h6,\""+codigoBarrasLargo+"\"\n";
            
            CodigoFormato = CodigoFormato+"P1\n";
            //CodigoFormato = CodigoFormato+"FK\"*\""+((char)10);--
            //CodigoFormato = CodigoFormato+"^default"+((char)10);
        return CodigoFormato;
    }
     
     /******************** FORMATO ZEBRA *************************/
    private String imprimeBoletoBlancoZEBRA(boolean conNoAsiento, boolean pBoletoAbierto,String fechaAbierto){
        boolean inter = false;
        System.out.println("Leyenda3 en imprimeBoletoZEBRA: "+this.Leyenda3);
        System.out.println("Manda a imprimir desde aqui imprimeBoletoBlancoZEBRA... ");
        servicioNombre = boleto[8].toString();
        if(servicioNombre.indexOf("INTERMEDIO")>=0 || servicioNombre.indexOf("SUBURBANO")>=0)
            inter = true;
        for(int ju=0; ju<sesionVenta.getNombresServicios().size(); ju++)
        {
            if(sesionVenta.getNombresServicios().get(ju).toString().equals(boleto[8].toString()))
                servicioNombre = sesionVenta.getImpresionNombreBoleto().get(ju).toString();
        }
        System.out.println("servicioNombre : "+servicioNombre);
        System.out.println("boleto[25] : "+boleto[25]);
        System.out.println("boleto[26] : "+boleto[26]);
        System.out.println("boleto[27] : "+boleto[27]);

//        12   Dígitos de folio
//        3     Origen
//        3      Destino
//        2      Tipo pasajero
//        6       Importe (2 decimales)
//        10    Fecha Viaje
//        3      Ciudad de Venta (Están por ser agregados)
//        3      Servicio  (Están por ser agregados)        
        //tipo pasajero
        String folioPreinpreso = ""+boleto[19];
        for(int j=folioPreinpreso.length(); j<12; j++) folioPreinpreso = "0"+folioPreinpreso;
        String origen = sesionVenta.getEstadoId(""+boleto[21]);
        String destino = sesionVenta.getEstadoId(""+boleto[22]);
        String ori = sesionVenta.getEstadoDescripcion(""+boleto[21]);
        String des = sesionVenta.getEstadoDescripcion(""+boleto[22]);
        String origenDescripcion = ((ori.indexOf(" ")>=0)?getTitulo(ori).replaceFirst(" ", "\\\\&"):ori);
        String destinoDescripcion = ((des.indexOf(" ")>=0)?getTitulo(des).replaceFirst(" ", "\\\\&"):des);
        String tipoPasajero = sesionVenta.getTmsTiposPasajeroId(""+boleto[2]);
          double importe = Double.valueOf(boleto[5].toString());
          String val = (importe/1.16)+"";
          BigDecimal nsubtotal = new BigDecimal(val);
          nsubtotal = nsubtotal.setScale(2, RoundingMode.HALF_UP);
          System.out.println("Subtotal : "+nsubtotal);
          BigDecimal niva = new BigDecimal((importe-nsubtotal.doubleValue()));
          niva = niva.setScale(2, RoundingMode.HALF_UP);
          System.out.println("Iva: "+niva);
        

        String importeCompleto = customFormat("##,##0.00",Double.valueOf(boleto[5].toString()));
        String psubtotal = customFormat("##,##0.00",Double.valueOf(nsubtotal.toString()));
        String piva = customFormat("##,##0.00",Double.valueOf(niva.toString()));
        String pimporte = customFormat("##,##0.00",Double.valueOf(boleto[5].toString())).replace(".","");
        for(int j=pimporte.length(); j<6; j++) pimporte = "0"+pimporte;
        String fechaViaje = (pBoletoAbierto ? "00/00/0000" : boleto[26].toString());
          String f[] = fechaViaje.split("/");
         fechaViaje = f[0]+f[1]+f[2].substring(2)+""+(pBoletoAbierto ? "0000" : boleto[27].toString().replace(":",""));
        Timestamp fechaHoraViaje  = null;
        System.out.println("cadena a convertir: "+f[2]+"-"+f[1]+"-"+f[0]+" "+((boleto[27].toString().length()>5)?(boleto[27]):(boleto[27]+":00")));
        fechaHoraViaje = fechaHoraViaje.valueOf(f[2]+"-"+f[1]+"-"+f[0]+" "+((boleto[27].toString().length()>5)?(boleto[27]):(boleto[27]+":00")));
        DateFormat df6 = DateFormat.getTimeInstance(DateFormat.SHORT);
        String horaBoleto = df6.format(fechaHoraViaje);
        horaBoleto = boleto[27]+horaBoleto.replace("PM",("Hr.(pm)")).replace("AM","Hr.(am)").substring(5);
        DateFormat df5 = DateFormat.getDateInstance(DateFormat.FULL);
        String fechaBoleto = df5.format(fechaHoraViaje);
        fechaBoleto= fechaBoleto.replaceFirst(" ", ", ").toUpperCase().replace(" DE ", " ");
        //String ciudadVenta = sesionVenta.getEstadoId(sesionVenta.getUserCon().getTerminalNombre());
        //String OrigenCorrida = sesionVenta.getOrigenCorrida(boleto[21].toString(), (boleto[22]==null?"":boleto[22].toString()), servicioNombre);
         System.out.println("sesionVenta.getOrigenRedIda(): "+sesionVenta.getOrigenRedIda());
         System.out.println("sesionVenta.getOrigenRedReg(): "+sesionVenta.getOrigenRedReg());
         System.out.println("sesionVenta.getOrigenCorridaRedIda(): "+sesionVenta.getOrigenCorridaRedIda());
         System.out.println("sesionVenta.getOrigenCorridaRedReg(): "+sesionVenta.getOrigenCorridaRedReg());
        String ciudadOriogenCorrida = "";
        if(sesionVenta.isBolRedCer())
            ciudadOriogenCorrida = sesionVenta.getEstadoId((boleto[21].toString().equals(sesionVenta.getOrigenRedIda())?sesionVenta.getOrigenCorridaRedIda():sesionVenta.getOrigenCorridaRedReg()));
        else
            ciudadOriogenCorrida =  sesionVenta.getEstadoId(sesionVenta.getOrigenCorrida(boleto[21].toString(), (boleto[22]==null?"":boleto[22].toString()), servicioNombre));
        String servicio = ""+sesionVenta.getServicioId(servicioNombre);
        for(int j=servicio.length(); j<3; j++) servicio = "0"+servicio;
        String codigoBarrasCorto = folioPreinpreso+origen+destino+tipoPasajero+pimporte+fechaViaje;
        String codigoBarrasLargo = codigoBarrasCorto+ciudadOriogenCorrida+servicio;
        System.out.println("codigoBarrasCorto: "+codigoBarrasCorto);
        System.out.println("codigoBarrasLargo: "+codigoBarrasLargo);
        System.out.println("inter: "+inter);
        System.out.println("fechaAbierto: "+fechaAbierto);
        System.out.println("boleto[6]: "+boleto[6].toString());
        System.out.println("boleto[31]: "+boleto[31].toString());
        System.out.println("Leyenda2: "+Leyenda2);
        System.out.println("juconi: "+juconi);
        String CodigoFormato=""; 
CodigoFormato = CodigoFormato+"^XA\n";
CodigoFormato = CodigoFormato+"^MMT\n";
CodigoFormato = CodigoFormato+"^PW609\n";
CodigoFormato = CodigoFormato+"^LL1300\n";
if((!inter && juconi.equals("")) || (inter && juconi.equals("")))
    CodigoFormato = CodigoFormato+"^LL1220\n";
if(inter && !juconi.equals(""))
    CodigoFormato = CodigoFormato+"^LL1300\n";
if(inter && !juconi.equals(""))
    CodigoFormato = CodigoFormato+"^LL1380\n";
//if(inter)
//    CodigoFormato = CodigoFormato+"^LL1300\n";
//else
//    CodigoFormato = CodigoFormato+"^LL1185\n";
CodigoFormato = CodigoFormato+"^LS0\n";
if(boleto[31].toString().equals("R"))
    CodigoFormato = CodigoFormato+"^FT35,195^XGE:EbER.GRF,1,1'+'^FS'\n";
else
    CodigoFormato = CodigoFormato+"^FT35,195^XGE:EbES.GRF,1,1'+'^FS'\n";
CodigoFormato = CodigoFormato+"^FO410,60\n";
CodigoFormato = CodigoFormato+"^GS^FDA^FS\n";
String tiipoPasajero = sesionVenta.getTmsTiposPasajeroNombre(""+boleto[2]);
if(boleto[2].toString().equals("A")) tiipoPasajero = tiipoPasajero+"(-65 a\\A4os)";
if(boleto[2].toString().equals("M")) tiipoPasajero = tiipoPasajero+"(4-12 a\\A4os)";
if(boleto[2].toString().equals("S")) tiipoPasajero = tiipoPasajero+"(+65 a\\A4os)";
CodigoFormato = CodigoFormato+"^FT171,215^A0N,28,28^FH\\^FD1x - "+getTitulo(tiipoPasajero)+"^FS\n";
CodigoFormato = CodigoFormato+"^A@N,50,50,E:TIM000.FNT\n";
CodigoFormato = CodigoFormato+"^FO50,260\n";
CodigoFormato = CodigoFormato+"^FD"+(boleto[3]==null ? "" : centrarNombre(getTitulo(truncaTexto(boleto[3].toString(),24))))+"^FS\n";
CodigoFormato = CodigoFormato+"^FT150,365^A0N,28,28^FH\\^FDDesde:^FS\n";
CodigoFormato = CodigoFormato+"^FT440,365^A0N,28,28^FH\\^FDHacia:^FS\n";
CodigoFormato = CodigoFormato+"^CF0,40,30^CF0,40,30^FO70,380\n";
CodigoFormato = CodigoFormato+"^FB250,4,,C,\n";
CodigoFormato = CodigoFormato+"^FD"+origenDescripcion+"^FS\n";
CodigoFormato = CodigoFormato+"^FT285,435^XGE:Flechas.GRF,1,1'+'^FS'\n";
CodigoFormato = CodigoFormato+"^CF0,40,30^FO340,380,2\n";
CodigoFormato = CodigoFormato+"^FB250,4,,C,\n";
CodigoFormato = CodigoFormato+"^FD"+destinoDescripcion+"^FS\n";
CodigoFormato = CodigoFormato+"^FT150,490^A0N,18,20^FH\\^FD("+boleto[21]+")^FS\n";
CodigoFormato = CodigoFormato+"^FT430,490^A0N,18,20^FH\\^FD("+boleto[22]+")^FS\n";
if(pBoletoAbierto)
{
    CodigoFormato = CodigoFormato+"^FT200,550^A0N,45,32^FH\\^FDBOLETO ABIERTO^FS\n";
    CodigoFormato = CodigoFormato+"^FT90,610^A0N,55,40^FH\\^FD"+getTitulo(fechaAbierto)+"^FS\n";
    CodigoFormato = CodigoFormato+"^FT235,640^A0N,20,22^FH\\^FD(Vigencia)^FS\n";
    CodigoFormato = CodigoFormato+"^FT50,700^A0N,48,35^FH\\^FDPase a Taquillas a Canjear su Boleto^FS\n";
    CodigoFormato = CodigoFormato+"^FT270,700^A0N,48,35^FH\\^FD  ^FS\n"; //-
    CodigoFormato = CodigoFormato+"^FT340,700^A0N,48,35^FH\\^FD ^FS\n";//Ventana
}
 else// BOLETO ABIERTO
{
    CodigoFormato = CodigoFormato+"^FT130,550^A0N,45,32^FH\\^FD"+getTitulo(fechaBoleto)+"^FS\n";
    CodigoFormato = CodigoFormato+"^FT175,610^A0N,55,40^FH\\^FD"+horaBoleto+"^FS\n";
    CodigoFormato = CodigoFormato+"^FT235,640^A0N,20,22^FH\\^FD(24 Horas)^FS\n";
    CodigoFormato = CodigoFormato+"^FT237,700^A0N,48,35^FH\\^FDAsiento "+boleto[1]+"^FS\n";
    CodigoFormato = CodigoFormato+"^FT270,700^A0N,48,35^FH\\^FD  ^FS\n"; //-
    CodigoFormato = CodigoFormato+"^FT340,700^A0N,48,35^FH\\^FD ^FS\n";//Ventana
}
CodigoFormato = CodigoFormato+"^A@N,45,45,E:TIM000.FNT\\n";
CodigoFormato = CodigoFormato+"^FO85,730\n";
CodigoFormato = CodigoFormato+"^FDServicio tipo:^FS\n";
CodigoFormato = CodigoFormato+"^A@N,45,45,E:TIM000.FNT\n";
CodigoFormato = CodigoFormato+"^FO360,730\n";
CodigoFormato = CodigoFormato+"^FD"+getTitulo(servicioNombre)+"^FS\n";
if(servicioNombre.equals("EBUS"))
    CodigoFormato = CodigoFormato+"^FT500,760^XGE:estre.GRF,1,1'+'^FS\n";
CodigoFormato = CodigoFormato+"^A@N,25,25,E:TIM001.FNT\n";
CodigoFormato = CodigoFormato+"^FO85,775,2\n";
CodigoFormato = CodigoFormato+"^FDEste servicio esta operado por "+boleto[7].toString()+"^FS\n";
CodigoFormato = CodigoFormato+"^FT400,830^A0N,28,28^FH\\^FDCosto: ^FS\n";
CodigoFormato = CodigoFormato+"^FT490,830^A0N,28,28^FH\\^FD$"+psubtotal+"^FS\n";
CodigoFormato = CodigoFormato+"^FT400,860^A0N,28,28^FH\\^FDI.V.A.: ^FS\n";
CodigoFormato = CodigoFormato+"^FT500,860^A0N,28,28^FH\\^FD$"+piva+"^FS\n";
CodigoFormato = CodigoFormato+"^FT320,890^A0N,28,28^FH\\^FD   Total:^FS\n";
CodigoFormato = CodigoFormato+"^FT430,890^A0N,28,28^FH\\^FD$"+importeCompleto+"^FS\n";
CodigoFormato = CodigoFormato+"^FT520,890^A0N,28,28^FH\\^FDMXN^FS\n";
/*CodigoFormato = CodigoFormato+"^FT300,920^A0N,28,28^FH\\^FD-80 Estrellas canjeadas^FS\n";
CodigoFormato = CodigoFormato+"^FT320,955^A0N,28,28^FH\\^FDSubtotal:^FS\n";
CodigoFormato = CodigoFormato+"^FT430,955^A0N,28,28^FH\\^FD$200.00^FS\n";
CodigoFormato = CodigoFormato+"^FT520,955^A0N,28,28^FH\\^FDMXN^FS\n";
 *
 */
CodigoFormato = CodigoFormato+"^FT90,975^A0N,28,28^FH\\^FDFolio No^FS\n";
CodigoFormato = CodigoFormato+"^FT90,1000^A0N,28,28^FH\\^FD"+boleto[19]+"^FS\n";
//CodigoFormato = CodigoFormato+"^FT320,985^A0N,28,28^FH\\^FDsaldo ^FS\n";
//CodigoFormato = CodigoFormato+"^FT450,985^A0N,28,28^FH\\^FD20 estrellas^FS\n";
if(!inter)
{
    CodigoFormato = CodigoFormato+"^FO90,805\n";
    //CodigoFormato = CodigoFormato+"^BQN,2,4\n";
    CodigoFormato = CodigoFormato+"^^BXN,7,200\n";
    CodigoFormato = CodigoFormato+"^FD"+codigoBarrasLargo+"^FS\n";
}
else
{
    CodigoFormato = CodigoFormato+"^FT50,1070^BY1\n";
    CodigoFormato = CodigoFormato+"^BCN,60,Y,N,N,N\n";
    CodigoFormato = CodigoFormato+"^FD"+codigoBarrasLargo+"^FS\n";
}

//if(inter)
//{
//    CodigoFormato = CodigoFormato+"^FT110,1070^BY1\n";
//    CodigoFormato = CodigoFormato+"^BCN,60,Y,N,N,N\n";
//    CodigoFormato = CodigoFormato+"^FD"+codigoBarrasCorto+"^FS\n";
if(!inter && !juconi.equals(""))
{
    CodigoFormato = CodigoFormato+"^FT50,1070^A0N,48,35^FH\\^FD"+juconi+"^FS\n";
    CodigoFormato = CodigoFormato+"^FT40,1100^XGE:Linpunt.GRF,1,1'+'^FS'\n";
    CodigoFormato = CodigoFormato+"^FT60,1125^A0N,18,20^FH\\^FD"+getTitulo(fechaBolBco)+" - "+getTitulo(truncaTexto(this.NombreTaquillero,14))+" - "+sesionVenta.getUserCon().getTerminalNombre()+" - "+this.TipoPago+" - "+boletos.length+"Tk's^FS\n";
    CodigoFormato = CodigoFormato+"^FT40,1250^XGE:Ebusred.GRF,1,1'+'^FS'\n";
}

if(!inter && juconi.equals(""))
{
    CodigoFormato = CodigoFormato+"^FT40,1020^XGE:Linpunt.GRF,1,1'+'^FS'\n";
    CodigoFormato = CodigoFormato+"^FT60,1045^A0N,18,20^FH\\^FD"+getTitulo(fechaBolBco)+" - "+getTitulo(truncaTexto(this.NombreTaquillero,14))+" - "+sesionVenta.getUserCon().getTerminalNombre()+" - "+this.TipoPago+" - "+boletos.length+"Tk's^FS\n";
    CodigoFormato = CodigoFormato+"^FT40,1170^XGE:Ebusred.GRF,1,1'+'^FS'\n";

}

if(inter && juconi.equals(""))
{
    CodigoFormato = CodigoFormato+"^FT40,1100^XGE:Linpunt.GRF,1,1'+'^FS'\n";
    CodigoFormato = CodigoFormato+"^FT60,1125^A0N,18,20^FH\\^FD"+getTitulo(fechaBolBco)+" - "+getTitulo(truncaTexto(this.NombreTaquillero,14))+" - "+sesionVenta.getUserCon().getTerminalNombre()+" - "+this.TipoPago+" - "+boletos.length+"Tk's^FS\n";
    CodigoFormato = CodigoFormato+"^FT40,1250^XGE:Ebusred.GRF,1,1'+'^FS'\n";
}
if(inter && !juconi.equals(""))
{
    CodigoFormato = CodigoFormato+"^FT50,1150^A0N,48,35^FH\\^FD"+juconi+"^FS\n";
    CodigoFormato = CodigoFormato+"^FT40,1180^XGE:Linpunt.GRF,1,1'+'^FS'\n";
    CodigoFormato = CodigoFormato+"^FT60,1205^A0N,18,20^FH\\^FD"+getTitulo(fechaBolBco)+" - "+getTitulo(truncaTexto(this.NombreTaquillero,14))+" - "+sesionVenta.getUserCon().getTerminalNombre()+" - "+this.TipoPago+" - "+boletos.length+"Tk's^FS\n";
    CodigoFormato = CodigoFormato+"^FT40,1330^XGE:Ebusred.GRF,1,1'+'^FS'\n";
 }

    CodigoFormato = CodigoFormato+"^PQ1,0,1,Y\n";
    CodigoFormato = CodigoFormato+"^XZ\n";
    CodigoFormato = CodigoFormato+"C\n";
    //System.out.println("CodigoFormato: "+CodigoFormato);
//}
/* else
{

    CodigoFormato = CodigoFormato+"^FT40,1015^XGE:Linpunt.GRF,1,1'+'^FS'\n";
    CodigoFormato = CodigoFormato+"^FT60,1040^A0N,18,20^FH\\^FD"+getTitulo(fechaBolBco)+" - "+getTitulo(truncaTexto(this.NombreTaquillero,14))+" - "+sesionVenta.getUserCon().getTerminalNombre()+" - "+this.TipoPago+" - "+boletos.length+"Tk's^FS\n";
    CodigoFormato = CodigoFormato+"^FT40,1165^XGE:Ebusred.GRF,1,1'+'^FS'\n";
    CodigoFormato = CodigoFormato+"^PQ1,0,1,Y\n";
    CodigoFormato = CodigoFormato+"^XZ\n";
}*/
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
                for(int ju=0; ju<sesionVenta.getNombresServicios().size(); ju++)
                {
                    if(sesionVenta.getNombresServicios().get(ju).toString().equals(boleto[8].toString()))
                        servicioNombre = sesionVenta.getImpresionNombreBoleto().get(ju).toString();
                }
                //if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                if(servicioNombre.equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                //if(boleto[8].toString().equals("PULLMAN PLUS"))
                if(servicioNombre.equals("PULLMAN PLUS"))
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
               if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                   // CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+"Mie y Jue de Junio"+"\n";
                    //CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+"Mie de Octubre"+"\n";
                    //CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+"Feb 26 y Marzo 5 y 12"+"\n";
                      CodigoFormato = CodigoFormato+caESC+"%0"+caESC+"H0600"+caESC+"V0173"+caESC+"L0103"+caESC+"XS"+"Feb26/Mar5y12"+"\n";
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
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                  //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"Mie y Jue de Junio\n";
                    //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"Mie de Octubre\n";
                    //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"Feb 26 y Marzo 5 y 12\n";
                      CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0150"+caESC+"V0310"+caESC+"L0102"+caESC+"XS"+"Feb26/Mar5y12\n";
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
                for(int ju=0; ju<sesionVenta.getNombresServicios().size(); ju++)
                {
                    if(sesionVenta.getNombresServicios().get(ju).toString().equals(boleto[8].toString()))
                        servicioNombre = sesionVenta.getImpresionNombreBoleto().get(ju).toString();
                }
                //if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                if(servicioNombre.equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                //if(boleto[8].toString().equals("PULLMAN PLUS"))
                if(servicioNombre.equals("PULLMAN PLUS"))
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
                if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                  //CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+"Mie y Jue de Junio"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+"Mie de Octubre"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+"Feb 26 y Marzo 5 y 12"+"\"\n";
                      CodigoFormato = CodigoFormato+"A185,368,2,3,1,2,N,\""+"Feb26/Mar5y12"+"\"\n";
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
               if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                  //CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+"Mie y Jue de Junio"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+"Mie de Octubre"+"\"\n";
                    //CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+"Feb 26 y Marzo 5 y 12"+"\"\n";
                      CodigoFormato = CodigoFormato+"A565,90,1,2,1,1,N,\""+"Feb26/Mar5y12"+"\"\n";
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
                for(int ju=0; ju<sesionVenta.getNombresServicios().size(); ju++)
                {
                    if(sesionVenta.getNombresServicios().get(ju).toString().equals(boleto[8].toString()))
                        servicioNombre = sesionVenta.getImpresionNombreBoleto().get(ju).toString();
                }
                //if(boleto[8].toString().equals("PULLMAN PRIMERA CLASE"))
                if(servicioNombre.equals("PULLMAN PRIMERA CLASE"))
                    servicioNombre = "PRIMERA CLASE";
                //if(boleto[8].toString().equals("PULLMAN PLUS"))
                if(servicioNombre.equals("PULLMAN PLUS"))
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
              if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                   //CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+"Mie y Jue de Junio"+"\n";
                    //CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+"Mie de Octubre "+"\n";
                    //CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+"Feb 26 y Marzo 5 y 12 "+"\n";
                      CodigoFormato = CodigoFormato+caESC+"%2"+caESC+"H0260"+caESC+"V0220"+caESC+"L0103"+caESC+"XS"+"Feb26/Mar5y12"+"\n";
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
               if((prom.toUpperCase().equals("DIRECTO ECONOMICO-CAPU-TAPO") || prom.toUpperCase().equals("DIRECTO ECONOMICO-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PRIMERA CLASE-TAPO-CAPU") || prom.toUpperCase().equals("PULLMAN PLUS-CAPU-TAPO") || prom.toUpperCase().equals("PULLMAN PLUS-TAPO-CAPU")) && aplicaPromo && (dia.equals("MARTES") ) )
                  //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"Mie y Jue de Junio\n";
                    //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"Mie de Octubre\n";
                    //CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"Feb 26 y Marzo 5 y 12\n";
                      CodigoFormato = CodigoFormato+caESC+"%1"+caESC+"H0693"+caESC+"V0320"+caESC+"L0102"+caESC+"XS"+"Feb26/Mar5y12\n";
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


    public String getTitulo(String cadena){
        if(cadena.toUpperCase().equals("EBUS"))
            return cadena.toUpperCase();
        cadena = cadena.toLowerCase();
        cadena = cadena.replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").replace("á", "a");
        char[] caracteres = cadena.toCharArray();
        for (int i = 0; i < cadena.length()- 2; i++)
        {
            if (i==0)        // Reemplazamos la primera letra
                caracteres[i] = Character.toUpperCase(caracteres[i]);
            // Es 'palabra'
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
            // Reemplazamos
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
        }
        return new String(caracteres).replace(" De ", " de ").replace(" En ", " en ").replace(" Por ", " por ").replace(" A ", " a ").replace(" O ", " o ").replace(" Para ", " para ").replace(" Con ", " con ").replace(" Al ", " al ").replace(" El ", " el ").replace(" Los ", " los ");
    }

    public String centrarNombre(String cadena){
        String texto=cadena;
        if(cadena.length()==24)
            return texto;
        BigDecimal nesp = new BigDecimal(""+((24-cadena.length())/2));
        for(int i=0; i<nesp.intValue(); i++)
            texto = " "+texto+" ";
        return texto;
    }
}