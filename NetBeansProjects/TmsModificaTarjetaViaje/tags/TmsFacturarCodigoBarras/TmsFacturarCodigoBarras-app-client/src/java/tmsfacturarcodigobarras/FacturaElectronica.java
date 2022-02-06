/*
 * FacturaElectronica.java
 *
 * Created on 2 de diciembre de 2010, 10:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsfacturarcodigobarras;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.Vector;
import javax.swing.JOptionPane;
import solicitud.FacturaTMSREPCONTROLBeanRemote;
import solicitud.TmsSesionBeanFacturarRemote;

/**
 *
 * @author brojasa 
 */
public class FacturaElectronica {
    
    /** Creates a new instance of FacturaElectronica */
    public FacturaElectronica() {
    }
     public String ClaveFactElect="";
    public String ResultCanFacElect="";
    public String RutaPDF = "";
    private FacturaTMSREPCONTROLBeanRemote repControlFacade;
    private String productos;

       
    /** Creates a new instance of FacturaElectronica */
    // ,TouFacturasTblFacadeRemote  FacadeRemote Container parent
  
      
    public FacturaElectronica(String LlaveFactura, TmsSesionBeanFacturarRemote FacadeRemote, Container parent, String TituloApp )
    {
      ResultCanFacElect=FacadeRemote.CancelarFact_Elect(LlaveFactura);   
      /*if (ResultCanFacElect != null && ResultCanFacElect.equalsIgnoreCase("APROBADA") ) 
          JOptionPane.showMessageDialog((Component) parent, "La factura Electronica ha sido cancelada. ",TituloApp, JOptionPane.INFORMATION_MESSAGE); 
      else
          JOptionPane.showMessageDialog((Component) parent, "No se pudo cancelar la factura Electronica. ",TituloApp, JOptionPane.ERROR_MESSAGE); 
        */    
    }  
        
   
   public   FacturaElectronica( java.util.Hashtable htDatosFactura, String slineasfacturas,TmsSesionBeanFacturarRemote FacadeRemote,
                               Container parent,String TituloApp, String pproductos,FacturaTMSREPCONTROLBeanRemote prepControlFacade  ) {
    repControlFacade = prepControlFacade;
    productos = pproductos;
    String Receptor        = htDatosFactura.get("RECEPTOR") != null ? htDatosFactura.get("RECEPTOR").toString():"";
    if (Receptor == null  || Receptor.length()<=0 )
        return ;
      
    /*
    
    String RfcEmisor       = htDatosFactura.get("RFC_EMISOR")!= null ? htDatosFactura.get("RFC_EMISOR").toString():"";
    
    String FormaPago       = htDatosFactura.get("FORMA_PAGO") != null ? htDatosFactura.get("FORMA_PAGO").toString():"";
    String CondicPago      = htDatosFactura.get("CONDICION_PAGO") != null ? htDatosFactura.get("CONDICION_PAGO").toString():"";
    String MetPago         = htDatosFactura.get("METODO_PAGO") != null ? htDatosFactura.get("METODO_PAGO").toString():"";
    String TipoComprobante = htDatosFactura.get("TIPO_COMPROBANTE") != null ? htDatosFactura.get("TIPO_COMPROBANTE").toString():"";
    String NoCuenta = htDatosFactura.get("NO_CUENTA") != null ? htDatosFactura.get("NO_CUENTA").toString():"";
      
    String DatosFacturas = "|"+""+     //ID_FACTURA
                           "|"+MetPago+
                           "|"+NoCuenta+  // Nuevo
                           "|"+TipoComprobante+
                           "|"+FormaPago+
                           "|"+CondicPago+
                           "|"+htDatosFactura.get("DES_FAC") + 
                           "|"+htDatosFactura.get("MOT_DES") + 
                           "|"+htDatosFactura.get("MONEDA") +"|" + htDatosFactura.get("IVA") ;
    System.out.println(" DatosFacturas  "+DatosFacturas);


    
      
    // Totales
    String Subtotal=htDatosFactura.get("SUBTOTAL") != null ? htDatosFactura.get("SUBTOTAL").toString():"0";
  //  String DescFact=htDatosFactura.get("DES_FAC") != null ? htDatosFactura.get("DES_FAC").toString():"";  
   // String MotDesc=htDatosFactura.get("MOT_DES") != null ? htDatosFactura.get("MOT_DES").toString():"0";  
    String Total=htDatosFactura.get("TOTAL") != null ? htDatosFactura.get("TOTAL").toString():"0"; 
    
    String Totales = "|"+Subtotal+"|"+Total;//+"|"+MotDesc+"|"+DescFact;
    
// impuestos
   String TImpRet=htDatosFactura.get("TIMPUESTOS_RET") != null ? htDatosFactura.get("TIMPUESTOS_RET").toString():"0";  
   String TImpBase=htDatosFactura.get("TIMPUESTOS_TRAS") != null? htDatosFactura.get("TIMPUESTOS_TRAS").toString():"0"; 
   
   String  Impuestos ="|"+TImpRet+"|"+TImpBase;
 
  //  RETENCION

    String TipoImp      = htDatosFactura.get("TIPO_IMPUESTO") != null ? htDatosFactura.get("TIPO_IMPUESTO").toString():"";  
    String ImporBaseRet = htDatosFactura.get("TIMPORTE_BASE_RET") != null? htDatosFactura.get("TIMPORTE_BASE_RET").toString():"0"; 
    String TasaRet      = htDatosFactura.get("TASA_RET") != null ? htDatosFactura.get("TASA_RET").toString():""; 
    String ImporteRet   = htDatosFactura.get("IMPORTE_RET") != null ? htDatosFactura.get("IMPORTE_RET").toString():"0"; 
      
    String  Retencion  =TipoImp+"|"+ImporBaseRet+"|\\n"+TasaRet+"|"+ImporteRet;
     System.out.println("Retenciones "+ Retencion);    
    // IVA|10|\nISR|16|\n
    // TRASLADO  
    String TipoImpTras = htDatosFactura.get("TIMPUESTO_TRAS") != null ? htDatosFactura.get("TIMPUESTO_TRAS").toString():"";
    String ImporTras   = htDatosFactura.get("IMPORTEB_TRAS")  != null ? htDatosFactura.get("IMPORTEB_TRAS").toString():"0";
    String TasaTrans   = htDatosFactura.get("TASA_TRAS")      != null ? htDatosFactura.get("TASA_TRAS").toString():"";
    String ImporteTras = htDatosFactura.get("IMPORTE_TRAS")   != null ? htDatosFactura.get("IMPORTE_TRAS").toString():"0";

    String  Traslado   =TipoImpTras+"|"+ImporTras+"|"+TasaTrans+"|"+ImporteTras;

    

    
    
    Vector VResultFacElect=FacadeRemote.GeneraFactura(Receptor,
                            RfcEmisor,
                            DatosFacturas,
                            slineasfacturas,//lineasfacturas,
                            Totales,Impuestos,Retencion,Traslado);
    */
    //ctor VResultFacElect=new Vector();
    //ResultFacElect.addElement("ACEPTADA");
    String P_PARAMETROS_FACTURA = Receptor;
    String P_PARAMETROS_PRODUCTOS = productos;
    Vector VResultFacElect=repControlFacade.GeneraFactura(P_PARAMETROS_FACTURA, P_PARAMETROS_PRODUCTOS,htDatosFactura.get("MODO").toString(),htDatosFactura.get("TIPOFACTURA").toString(),Long.valueOf(htDatosFactura.get("CLIENTEID").toString()),Float.valueOf(htDatosFactura.get("MONTO").toString()),htDatosFactura.get("USUARIO").toString());
    System.out.println(" Resultado al generar la factura "+VResultFacElect);
//    if (VResultFacElect != null && VResultFacElect.size() > 0 &&  VResultFacElect.elementAt(0) != null)
    if (VResultFacElect!=null && VResultFacElect.get(0).toString().equals("TRUE"))
    {
           String status    = (VResultFacElect.elementAt(0) != null ? VResultFacElect.elementAt(0).toString():"");
           String error     = ( VResultFacElect.elementAt(1)!= null ? VResultFacElect.elementAt(1).toString():"");
           this.ClaveFactElect = ( VResultFacElect.elementAt(2)!= null ? VResultFacElect.elementAt(2).toString():"");
           String rutaPDF   = ( VResultFacElect.elementAt(3)!= null ? VResultFacElect.elementAt(3).toString():"");
           String rutaXML =   ( VResultFacElect.elementAt(4)!= null ? VResultFacElect.elementAt(4).toString():"");
           String facturaId =   ( VResultFacElect.elementAt(5)!= null ? VResultFacElect.elementAt(5).toString():"-1");
           System.out.println("LlaveFact FacturaElectronica  "+ this.ClaveFactElect );
           System.out.println(" Resultado al generar la factura status "+status);
           System.out.println(" Resultado al generar la factura rutaPDF "+rutaPDF);
           System.out.println(" Resultado al generar la factura rutaXML "+rutaXML);
    /*
           String status    = VResultFacElect.elementAt(0).toString();
           String error     = ( VResultFacElect.elementAt(1)!= null ? VResultFacElect.elementAt(1).toString():"");
           String Llavefact = ( VResultFacElect.elementAt(2)!= null ? VResultFacElect.elementAt(2).toString():"");
           String rutaPDF   = ( VResultFacElect.elementAt(3)!= null ? VResultFacElect.elementAt(3).toString():"");
           String rutaXML =   ( VResultFacElect.elementAt(4)!= null ? VResultFacElect.elementAt(4).toString():"");
      */
           RutaPDF = rutaPDF;
           
           //ClaveFactElect = Llavefact;
           System.out.println(" Resultado al generar la factura status "+status);
           System.out.println(" Resultado al generar la factura rutaPDF "+rutaPDF);
   
               
           // rutaPDF="C:\\_tomcat626\\webapps\\EfacturaT\\certificados\\584\\A-GOLV800830TJ9-AMP-350.pdf";
    
           //if( status.equalsIgnoreCase("ACEPTADA")  && rutaPDF != null &&  rutaPDF.length() > 0)
           if( status!= null && status.equalsIgnoreCase("TRUE")  && rutaPDF != null &&  rutaPDF.length() > 0)
           { /*
              String RutAbs  =  htDatosFactura.get("RUTABS").toString();
              System.out.println(" RutAbsArchivo rutaPDF "+RutAbs);
              String RutAlias  =  htDatosFactura.get("RUTALIAS").toString();
              System.out.println(" Rut Alias "+RutAlias);
              System.out.println(" rutaPDF "+rutaPDF);
              String  rut = rutaPDF.replace(RutAbs,RutAlias);
               
              System.out.println(" Abrir Archivo rutaPDF "+rut);
              *
              */
              OpenFile(rutaPDF);  //Antes anhora se abre en el el jframeFactura
               System.out.println(" Abrir Archivo rutaPDF "+rutaPDF);
           }
           else
           
               JOptionPane.showMessageDialog((Component) parent, "No se pudo generar la factura Electronica .\n "+error,TituloApp, JOptionPane.ERROR_MESSAGE); 
           
            }// IF 
   
       }
   
      
    
  public void OpenFile(String Ruta)   {
    File nombre;//archivo pdf o xls o doc 
    System.out.println(" rutaPDF "+Ruta);
    
    //Ruta="C:\\Compartir\\archivo_guias_prepagadas_05_02_2010_15_46_42.pdf";
      try {     //try statement
        
          nombre = new File(Ruta);
          Process p = null;
          p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+
                                       nombre.getAbsolutePath() ); //+".pdf");
       } catch (Exception e) {
          e.printStackTrace();
      }  
       
   }// OpenFile
}
