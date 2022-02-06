package tmsfacturarcodigobarras;
//import com.sun.java_cup.internal.terminal;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

class imprimir_recibo_tarjeta implements Printable{
        private double PIXELES_POR_PULGADA = 72.0;
        private double ANCHO = 8.5;//3.625; //DIMENSIONES DEL PAPEL (PULGADAS)
        private double ALTO = 11;
        private String[][] datos_imprimir;
        private int numtarjetas;
        JIF_Facturar datos = null;
        String numeroconletra = null;
        String parte1 = null;
        String parte2 = null;
        PrintService impresora = null;
        
        public imprimir_recibo_tarjeta(JIF_Facturar cosas, String cosa, String parte1, String parte2,PrintService impresora ){
            datos = cosas;
            numeroconletra = cosa;
            this.parte1 = parte1;
            this.parte2 = parte2;
            this.impresora = impresora;
        }

        public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
            if (page > 0) {
                return NO_SUCH_PAGE;
            }

            Calendar calendario = Calendar.getInstance();
            String meses[] = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"}; 
            String todoslosfolios = "";
            int cont= 0, contador = 0;
            DecimalFormat df = new DecimalFormat("##.00");
            DecimalFormat df1 = new DecimalFormat("00");
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            Font fontVar = new Font("Courier",Font.PLAIN,12);
            g.setFont(fontVar);
            int lineaNueva=11, row=9;
            g.drawString(datos.addCliente.nombre, 30,lineaNueva*row);
            row++;
            g.drawString(datos.addCliente.RFC,30,lineaNueva*row);
            row++;                                        
            if(datos.addCliente.no_int == null)
                if(!datos.addCliente.no_int.equals("") && datos.addCliente.no_int.length()>0)
                    g.drawString(datos.addCliente.calle+" "+datos.addCliente.no_ext+"-"+datos.addCliente.no_int,30,lineaNueva*row);
                else
                    g.drawString(datos.addCliente.calle+" "+datos.addCliente.no_ext+"-"+datos.addCliente.no_int,30,lineaNueva*row);
            else
                g.drawString(datos.addCliente.calle+" "+datos.addCliente.no_ext+"-"+datos.addCliente.no_int,30,lineaNueva*row);
            row++;
            g.drawString(datos.addCliente.col,30,lineaNueva*row);
            row++;
            g.drawString(datos.addCliente.mun + "    C.P. "+ datos.addCliente.cp,30,lineaNueva*row);
            g.drawString(String.valueOf(calendario.get(Calendar.DATE))+" DE "+meses[calendario.get(Calendar.MONTH)]+" DE "+String.valueOf(calendario.get(Calendar.YEAR)),365,lineaNueva*row);
            row +=3;
            g.drawString("SERVICIO DE TRANSPORTACION DE "+datos.numeroBoletos+" BOLETOS",30,lineaNueva*row);          
            row++;
            g.drawString("FOLIOS: ",30,lineaNueva*row);
            contador = 10;
            for(int i = 0; i < datos.salida[10].size(); i++) {
                if(cont == 3){
                    cont = 0;
                    contador = 10;
                    row++;
                }
                contador +=65;
                if(i+1 == datos.salida[10].size())
                    g.drawString((String) datos.salida[10].get(i), contador,lineaNueva*row);
                else {
                    g.drawString((String) datos.salida[10].get(i)+",", contador,lineaNueva*row);
                    contador += 10;
                }
                cont ++;
            }
            
            while(row < 28)
                row++;
            g.drawString(String.valueOf(df.format(datos.subtotal)),505,lineaNueva*row);
            row += 3;
            g.drawString("SUB-TOTAL          "+String.valueOf(df.format(datos.subtotal)),380,lineaNueva*row);
            row++;
            g.drawString("I.V.A. EXENTO        -",380,lineaNueva*row);
            row++;
            g.drawString("                  _________",370,lineaNueva*row);
            row++;
            g.drawString("PAGO EN UNA SOLA EXHIBICION    TOTAL              "+String.valueOf(df.format(datos.total)),175,lineaNueva*row);
            row +=6;
            g.drawString(numeroconletra+" PESOS "+parte2+"0/100 M.N",170,lineaNueva*row);
            row++;
            row++;
            g.drawString(datos.usuario+datos.p_nombre+String.valueOf(calendario.get(Calendar.DATE))+String.valueOf(df1.format(calendario.get(Calendar.MONTH)+1))+String.valueOf(calendario.get(Calendar.YEAR))+String.valueOf(df1.format(calendario.get(Calendar.HOUR_OF_DAY))) + String.valueOf(df1.format(calendario.get(Calendar.MINUTE)))+String.valueOf(df1.format(calendario.get(Calendar.SECOND)))+datos.terminal+datos.facturas_ini,0,lineaNueva*row);
            row++;
            row++;
            row++;
            return PAGE_EXISTS;
        }

        public boolean ImprimeDatos(){

            return this.setPrint();
        }

        private boolean setPrint(){    
            PageFormat formatoPagina = new PageFormat();
            double papelAncho  = ANCHO*PIXELES_POR_PULGADA;
            double papelAlto   = ALTO*PIXELES_POR_PULGADA;
            double papelImaginableX      = PIXELES_POR_PULGADA*0.5;
            double papelImaginableY      = PIXELES_POR_PULGADA*0.0625;
            double papelImaginableAncho  = papelAncho  - (PIXELES_POR_PULGADA*0.5);
            double papelImaginableAlto   = papelAlto - PIXELES_POR_PULGADA;

            Paper papel = new Paper();
            papel.setSize(papelAncho,papelAlto);
            papel.setImageableArea(papelImaginableX,papelImaginableY,papelImaginableAncho,papelImaginableAlto);
            formatoPagina.setPaper(papel);
            formatoPagina.setOrientation(PageFormat.PORTRAIT);
            
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this,formatoPagina);

            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService(); 
            
            /*BUSCAR IMPRESORA CONFIGURADA*/
            PrintService service[] = PrinterJob.lookupPrintServices();
            /*System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
            boolean existeimp = false;
            PrintService impresoraEncontrada = null;
            for(int i=0; i<service.length;i++)
            {
                System.out.println("        "+service[i]. getName());
                if(service[i].getName().toUpperCase().equals(impresora))
                {
                     existeimp = true;
                     impresoraEncontrada = service[i];
                 }
             }*/
            try {
                job.setPrintService(impresora);
                job.print();
            } catch (PrinterException ex) {
                return false;
            }
             return true;
        }

        String customFormat(String pattern, float value ) {
              DecimalFormat myFormatter = new DecimalFormat(pattern);
              return myFormatter.format(value);
           }
}