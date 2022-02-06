package tmspases_traslado;

import com.sun.java_cup.internal.terminal;
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
import solicitud.TMSPasesTrasladoFacadeRemote;

class imprimir_recibo_tarjeta implements Printable{
        private double PIXELES_POR_PULGADA = 72.0;
        private double ANCHO = 8.5;//3.625; //DIMENSIONES DEL PAPEL (PULGADAS)
        private double ALTO = 11;
        private String[][] datos_imprimir;
        private int numtarjetas;
        String numeroconletra = null;
        String op = null;
        String aut = null;
        String mot = null;
        Vector ruta = null;
        String usuario = null;
        String folio = null;
        String nombre = null;
        PrintService impresora = null;
        TMSPasesTrasladoFacadeRemote cosa = null;
        String peaje= null;
        
        public imprimir_recibo_tarjeta(String op, String aut, Vector ruta, String mot, String usuario, String nombre, String folio, PrintService impresora, TMSPasesTrasladoFacadeRemote cosa, String peaje){
            this.impresora = impresora;
            this.op = op;
            this.aut = aut;
            this.mot = mot;
            this.usuario = usuario;
            this.ruta = ruta;
            this.folio = folio;
            this.cosa = cosa;
            this.nombre = nombre;
            this.impresora = impresora;
            this.peaje = peaje;
        }

        public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
            if (page > 0) {
                return NO_SUCH_PAGE;
            }

            Calendar calendario = Calendar.getInstance();
            String meses[] = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"}; 
            String servicio = cosa.buscarNombreServicio(ruta.get(1).toString());
            String origen = cosa.buscarNombreEstado(ruta.get(6).toString());
            String destino = cosa.buscarNombreEstado(ruta.get(7).toString());
            DecimalFormat df1 = new DecimalFormat("00");
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            Font fontVar = new Font("Courier",Font.PLAIN,12);
            g.setFont(fontVar);
            int lineaNueva=11, row=3;
            g.drawString("Pase de Traslado ",200,lineaNueva*row);
            row +=4;
            g.drawString("Folio: "+ this.folio,0,lineaNueva*row);
            row++;
            g.drawString(String.valueOf(calendario.get(Calendar.DATE))+" DE "+meses[calendario.get(Calendar.MONTH)]+" DE "+String.valueOf(calendario.get(Calendar.YEAR))+ " "+String.valueOf(df1.format(calendario.get(Calendar.HOUR_OF_DAY))) +":"+ String.valueOf(df1.format(calendario.get(Calendar.MINUTE)))+":"+String.valueOf(df1.format(calendario.get(Calendar.SECOND))),0,lineaNueva*row);
            row++;
            g.drawString(this.usuario+"-"+this.nombre,0,lineaNueva*row);
            row +=5;
            g.drawString("Clave Operador: "+ this.op,30,lineaNueva*row);
            row++;
            g.drawString("Autobús: "+this.aut,30,lineaNueva*row);
            row += 2;
            g.drawString("Número de Ruta: "+this.ruta.get(2).toString(),30,lineaNueva*row);
            row++;
            g.drawString("Nombre de Ruta: "+this.ruta.get(4).toString(),30,lineaNueva*row);
            row++;
            g.drawString("Origen: "+ origen,30,lineaNueva*row);
            row++;
            g.drawString("Destino: "+ destino,30,lineaNueva*row);
            row++;
            g.drawString("Distancia: "+ this.ruta.get(9).toString() +" Kms.",30,lineaNueva*row);
            row++;
            g.drawString("Peaje: $"+ this.peaje,30,lineaNueva*row);
            row +=2;
            g.drawString("Motivo: "+ this.mot,30,lineaNueva*row);
            row +=3;
            g.drawString("_____________________________________" ,310,lineaNueva*row);
            row++;
            g.drawString("              Autorizó                   ",310,lineaNueva*row);
            row +=3;
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

            //PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService(); 
            System.out.println("Impresora "+ impresora);           
            
            /*BUSCAR IMPRESORA CONFIGURADA*/
            /*PrintService service[] = PrinterJob.lookupPrintServices();
            System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
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