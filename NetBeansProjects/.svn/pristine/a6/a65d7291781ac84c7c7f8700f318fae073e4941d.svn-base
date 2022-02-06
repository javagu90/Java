/*
 * desktopShow.java
 *
 * Created on 3 de mayo de 2008, 03:25 PM
 */

package tms_vta_productos_er;

import TMSVtaProductosER.entidad.ProductoCarrito;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author  vgonzalez
 */
public class desktopShow extends javax.swing.JFrame {
    private boolean ventaJuconi= false;
    /** Creates new form desktopShow */
    public desktopShow() {
        initComponents();
        Vector datos = new Vector();
        datos.add("145");//"7");
        datos.add("8670");//"6000");
        datos.add("algun usuario");
        datos.add("1502163");//365 tcentral
        datos.add("309");
        datos.add("192.168.106.22");//192.168.16.153
        datos.add("3700");
                addInternalFrame(new JIFConfigProductosER(datos));
                JdlgVentaProductosER dialog = new JdlgVentaProductosER(new javax.swing.JFrame(), true,datos,"CAPU","CAPUTAQ01",15,1000);
                dialog.setVisible(true);
                JdlgVentaCarritoProductosER dialog2 = new JdlgVentaCarritoProductosER(new javax.swing.JFrame(), true,datos,"CAPU","CAPUTAQ01",15,1000,"465151",new ArrayList<ProductoCarrito>(),490,"F",this.ventaJuconi);
                dialog2.setVisible(true);
                if(dialog2.isVenta())
                {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("---------------------------------------------------------");
                   System.out.println("Si hubo Venta por: "+dialog2.getTotalProductorAgregados());
                   List<ProductoCarrito> productos = dialog2.getProductosAgregados() ;
                   boolean imprime = false;
                   List<ProductoCarrito> pproductosImp = new ArrayList<ProductoCarrito>() ;
                    for(ProductoCarrito product:productos)
                    {
                            System.out.println("****************** 1 ***********************");
                            System.out.println(" ProductoId: "+product.getProductoId());
                            System.out.println(" Clavve: "+product.getClave_producto());
                            System.out.println(" Nombre: "+product.getProducto());
                            System.out.println(" TiposPago: "+product.getTiposPagoPer());
                            System.out.println(" Cantidad: "+product.getCantidad());
                            System.out.println(" Total: "+product.getTotal());
                            System.out.println(" Impresion: "+product.isImprimeTicket());
                            //Separa los productos que si imprimen Ticket
                            if(product.isImprimeTicket())
                            {
                                imprime = true;
                                pproductosImp.add(product);
                            }

                    }
                    //Imprime El Ticket

                    if(imprime)
                        imprimeTicketProductos(pproductosImp);
                   

                }
                else
                    System.out.println("No hubo Venta");
                JdlgVentaCarritoProductosER dialog3 = new JdlgVentaCarritoProductosER(new javax.swing.JFrame(), true,datos,"CAPU","CAPUTAQ01",15,1000,"465151",dialog2.getProductosAgregados(),490,"F",this.ventaJuconi);
                dialog3.setVisible(true);
                if(dialog3.isVenta())
                {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("---------------------------------------------------------");
                   System.out.println("Si hubo Venta por: "+dialog3.getTotalProductorAgregados());
                   List<ProductoCarrito> productos = dialog3.getProductosAgregados() ;

                    for(ProductoCarrito product:productos)
                    {
                            System.out.println("****************** 2 ***********************");
                            System.out.println(" ProductoId: "+product.getProductoId());
                            System.out.println(" Clavve: "+product.getClave_producto());
                            System.out.println(" Nombre: "+product.getProducto());
                            System.out.println(" TiposPago: "+product.getTiposPagoPer());
                            System.out.println(" Cantidad: "+product.getCantidad());
                            System.out.println(" Total: "+product.getTotal());
                    }
                }
                else
                    System.out.println("No hubo Venta");

    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(escritorio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(escritorio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new desktopShow().setVisible(true);
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    // End of variables declaration//GEN-END:variables
    
    public void addInternalFrame(JInternalFrame iFrame){
     escritorio.add(iFrame);
     iFrame.setVisible(true);
    }
    

  public static double roundNum(double num, int ndec)
    throws Exception
  {
    double valor = 0.0D;

    valor = num;

    valor *= ndec;
    valor = Math.round(valor);
    valor /= ndec;

    return valor;
  }

    private String getLPAD(String texto, int lenght)
    {
        String lpad =texto;
        for(int i=texto.length(); i<lenght;i++ )
            lpad = " " + lpad;
        return lpad;
    }

 private void imprimeTicketProductos(List<ProductoCarrito> productosImp)
 {
                         String SalidaImpresion="ARCHIVO";
                         String puerto = "";
                         String nombreFormato = "";
                         String nombreImpresora = "";
                        facadeVtaProductos SeetingTMS;
                        SeetingTMS = new facadeVtaProductos();
                        SeetingTMS.lookupGenerico();

                        PcInfo pc = new PcInfo();
                         System.out.println("Host Name: "+pc.getHostName());
                       Vector res = SeetingTMS.generico_tmsRemote.getImpresora(pc.getHostName());
                       if(res.size()==0)
                           JOptionPane.showMessageDialog(this,  "No existen impresoras configuradas para Tickets en esta caja. \nFavor de contactar al Administrador del Sistema", "Advertencia",JOptionPane.WARNING_MESSAGE);

                       else
                       {
                           Vector v = (Vector)res.get(0);
                           nombreImpresora= (v.get(0).toString());
                           puerto= (v.get(1).toString());
                           nombreFormato=(v.get(2).toString());
                           System.out.println("NombreImpresora: "+nombreImpresora);
                           System.out.println("puerto: "+puerto);
                           System.out.println("NombreFormato: "+nombreFormato);

                           String empresa="";
                            String producto="";
                            long cantidad=0;
                            String folio=SeetingTMS.generico_tmsRemote.getTicketCompra(15);//PrefijoTerminal
                            String clave_producto="";
                            float precio_unitario=0;
                            float subtotal=0;
                            float iva=0;
                            float total=0;
                            float retencion=0;
                            float montoTotalTicket =0;
                            String emp = productosImp.get(0).getEmpresaDescipcion().toUpperCase();
                            if(emp.length()<50)
                                    emp = "    "+emp;
                            String sCad="\n";
                            //sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
                            System.out.println("Empresa Nombre: "+emp);
                            if (emp.length()>23)
                                sCad = sCad+"      "+emp.substring(0,23).trim();
                            sCad = sCad+"\n";
                            //sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
                            if (emp.length()>23)
                                sCad = sCad+"      "+emp.substring(24,emp.length()).trim();
                            sCad = sCad+"\n";
                            sCad = sCad+"        Comprobante de Pago ";
                            sCad = sCad+"\n";
                            SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
                            SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
                            sCad = sCad+"  FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
                            sCad = sCad+"\n";
                            sCad = sCad+"  No.Ticket: "+folio;
                            sCad = sCad+"\n\n";
                            sCad = sCad+"            Productos      "; //750
                            sCad = sCad+"\n";
                            sCad = sCad+"_____________________________________";
                            sCad = sCad+"\n";
                            sCad = sCad+"Cant.\tNombre\t\tPrecio\tImporte";
                            sCad = sCad+"\n";
                            sCad = sCad+"_____________________________________";
                            sCad = sCad+"\n";

                            for(ProductoCarrito product:productosImp)
                            {
                                    System.out.println("*****************************************");
                                    System.out.println(" ProductoId: "+product.getProductoId());
                                    //System.out.println(" Clave: "+product.getProductoClave());
                                    System.out.println(" Nombre: "+product.getProducto());
                                    System.out.println(" TarifaMostrador: "+product.getPrecio_unitario());
                                    System.out.println(" EmpresaNombre: "+product.getEmpresa());
                                    empresa=product.getEmpresa();
                                    producto=product.getProducto();
                                    cantidad=product.getCantidad();
                                    //clave_producto=product.getProductoClave();
                                    precio_unitario=product.getPrecio_unitario();
                                    subtotal=product.getSubtotal();
                                    iva=0;
                                    total=product.getTotal();
                                    retencion=0;

                                        //sCad = sCad+" Catidad   Nombre  Precio  Importe";
                                        montoTotalTicket=montoTotalTicket+total;
                                        sCad = sCad+" "+cantidad+(((""+cantidad).length()>2)?" ":"  ")+((producto.length()<=11)?producto:producto.substring(0,11))+(((""+producto).length()>7)?"\t":"\t\t")+precio_unitario+(((""+precio_unitario).length()>3)?"\t":"\t\t")+total;
                                        sCad = sCad+"\n";
                            }


                               sCad = sCad+"\n";
                                sCad = sCad+"\t\t\t\t\tTotal: $"+montoTotalTicket;
                                sCad = sCad+"\n\n";
                                String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(montoTotalTicket)).longValue()))  + "Pesos 00/M.N. )";
                                sCad = sCad+letras;
                                sCad = sCad+"\n\n";
                                sCad = sCad+"\n";
                                sCad = sCad+"Cajero: "+("USUARIO".length()<25?"USUARIO":"USUARIO".substring(0,25));//+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
                                sCad = sCad+"\n";
                                sCad = sCad+" #Caja: "+"Caja";
                                sCad = sCad+"\n\n   Este comprobante de pago";
                                sCad = sCad+"\n"+"      No es Valido";
                                sCad = sCad+"\n"+" Como cupon de viaje, como";
                                sCad = sCad+"\n"+"boleto de transporte ni como ";
                                sCad = sCad+"\n"+"     comprobante Fiscal";
                                sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
                                sCad = sCad+"         .";
                                String sCadTermico = sCad;
                             if(nombreFormato.equals("TICKET_TERMICO") ) //|| nombreFormato.equals("TICKET"))
                             {
                                 sCadTermico = "";
                                 float impTotalTick = 0;
                                 float subTotalTick = 0;
                                 float ivaTotalTick = 0;
                                 System.out.println(" Entra a generar Ticket Termico...");
                                 for(int j=0; j<productosImp.size();j++)
                                 {
                                     float sub = productosImp.get(j).getSubtotal();
                                    try {
                                         productosImp.get(j).setSubtotal(Float.valueOf("" + roundNum(Double.valueOf(sub / 1.16), 2)));
                                         productosImp.get(j).setIva(sub - (Float.valueOf("" + roundNum(Double.valueOf(sub / 1.16), 2))));
                                    } catch (Exception ex) {
                                        Logger.getLogger(desktopShow.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                     ProductoCarrito prod= productosImp.get(j);
                                     impTotalTick  = impTotalTick + prod.getTotal();
                                     subTotalTick  = subTotalTick + prod.getSubtotal();
                                     ivaTotalTick  = ivaTotalTick + prod.getIva();
                                 }
                                     sCadTermico = "^XA\n";
                                    sCadTermico = sCadTermico + "^MMT\n";
                                    sCadTermico = sCadTermico + "^PW609\n";
                                    sCadTermico = sCadTermico + "^LL0972\n";
                                    sCadTermico = sCadTermico + "^LS0\n";
                                    if(productosImp.get(0).getEmpresa().equals("Terminal Las Torres Puebla 1"))
                                        sCadTermico = sCadTermico + "^FT35,110^XGE:cmpEbus2.GRF,1,1'+'^FS'\n";
                                    else
                                        sCadTermico = sCadTermico + "^FT35,110^XGE:cmpER.GRF,1,1'+'^FS'\n";
                                    sCadTermico = sCadTermico + "^FO410,30\n";
                                    sCadTermico = sCadTermico + "^GS^FDA^FS\n";
                                    sCadTermico = sCadTermico + "^FT512,191^A0N,28,28^FH\\^FD"+formath.format(new Date()) +"^FS\n";
                                    sCadTermico = sCadTermico + "^FT24,738^A0N,28,28^FH\\^FD#Caja:^FS\n";
                                    sCadTermico = sCadTermico + "^FT24,230^A0N,28,28^FH\\^FDNo. Ticket:^FS\n";
                                    sCadTermico = sCadTermico + "^FT110,742^A0N,28,28^FH\\^FD"+"Caja"+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT29,628^A0N,28,28^FH\\^FD"+"( "+new cantidad_a_letras().toLetras((long)((new Double(impTotalTick)).longValue()))  + "Pesos 00/M.N. )"+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT170,232^A0N,28,28^FH\\^FD"+folio+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT434,191^A0N,28,28^FH\\^FDHora:^FS\n";
                                    sCadTermico = sCadTermico + "^FT113,697^A0N,28,28^FH\\^FD"+("USUARIO".length()<25?"USUARIO":"USUARIO".substring(0,25))+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT171,191^A0N,28,28^FH\\^FD"+formatf.format(new Date()) +"^FS\n";
                                    sCadTermico = sCadTermico + "^FT126,329^A0N,23,24^FH\\^FDNombre^FS\n";
                                    sCadTermico = sCadTermico + "^FT480,330^A0N,23,24^FH\\^FDImporte^FS\n";
                                    sCadTermico = sCadTermico + "^FT351,573^A0N,28,28^FH\\^FDTotal:^FS\n";
                                    sCadTermico = sCadTermico + "^FT353,330^A0N,23,24^FH\\^FDPrecio^FS\n";
                                    sCadTermico = sCadTermico + "^FT440,570^A0N,28,28^FH\\^FD"+getLPAD("$"+impTotalTick,10)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT24,329^A0N,23,24^FH\\^FDCant.^FS\n";
                                    sCadTermico = sCadTermico + "^FT452,497^A0N,23,24^FH\\^FD"+getLPAD("$"+subTotalTick,10)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT451,529^A0N,23,24^FH\\^FD"+getLPAD("$"+ivaTotalTick,10)+"^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT468,450^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(2).getSubtotal())*productosImp.get(0).getCantidad()), 8)+"^FS\n";
                                    if(productosImp.size()>=2)
                                    sCadTermico = sCadTermico + "^FT468,413^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(1).getSubtotal())*productosImp.get(0).getCantidad()), 8)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT333,499^A0N,23,24^FH\\^FDSubtotal:^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT121,455^A0N,23,24^FH\\^FD"+productosImp.get(2).getProducto()+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT358,534^A0N,23,24^FH\\^FDI.V.A.:^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT328,453^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(2).getSubtotal(), 8)+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT467,376^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(0).getSubtotal())*productosImp.get(0).getCantidad()), 8)+"^FS\n";
                                    if(productosImp.size()>=2)
                                    sCadTermico = sCadTermico + "^FT121,419^A0N,23,24^FH\\^FD"+productosImp.get(1).getProducto()+"^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT35,455^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(2).getCantidad(), 2)+"^FS\n";
                                    if(productosImp.size()>=2)
                                        sCadTermico = sCadTermico + "^FT328,417^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(1).getSubtotal(), 8)+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT328,380^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(0).getSubtotal(), 8)+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT121,382^A0N,23,24^FH\\^FD"+productosImp.get(0).getProducto()+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT36,418^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(0).getCantidad(), 2)+"^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT35,381^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(2).getCantidad(), 2)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT17,693^A0N,28,28^FH\\^FDCajero:^FS\n";
                                    sCadTermico = sCadTermico + "^FT76,190^A0N,28,28^FH\\^FDFecha:^FS\n";
                                    sCadTermico = sCadTermico + "^FT6,339^A0N,25,24^FH\\^FD__________________________________________________^FS\n";
                                    sCadTermico = sCadTermico + "^FT251,286^A0N,34,33^FH\\^FDProductos^FS\n";
                                    sCadTermico = sCadTermico + "^FT8,293^A0N,25,24^FH\\^FD__________________________________________________^FS\n";
                                    sCadTermico = sCadTermico + "^FT25,827^A0N,23,24^FH\\^FDcomo boleto de transporte ni como comprobante fiscal.^FS\n";
                                    sCadTermico = sCadTermico + "^FT2,797^A0N,23,24^FH\\^FDEste comprobante de pago no es valido como cupon de viaje,^FS\n";
                                    if(productosImp.get(0).getEmpresa().equals("Terminal Las Torres Puebla 1"))
                                        sCadTermico = sCadTermico + "^FT40,965^XGE:Ebusred.GRF,1,1'+'^FS'\n";
                                    else
                                        sCadTermico = sCadTermico + "^FT40,965^XGE:ER redes.GRF,1,1'+'^FS'\n";
                                    sCadTermico = sCadTermico + "^PQ1,0,1,Y^XZ\n";
                                    sCadTermico = sCadTermico + "C\n";

                              }
                            sCad = sCadTermico;
                           if(puerto.equals("LPT1") ||puerto.equals("LPT2") ||puerto.equals("LPT3") || puerto.equals("LPT4") ||  puerto.equals("COM1") || puerto.equals("COM2")  || puerto.equals("ARCHIVO") )
                                SalidaImpresion = puerto;
                           if(puerto.equals("RED") )
                               SalidaImpresion = nombreImpresora;
                            if(puerto.equals("USB"))
                                SalidaImpresion = "\\\\127.0.0.1\\"+ nombreImpresora;
                                try{
                                String UserHome = System.getProperty("user.home");
                                if(SalidaImpresion.equals("ARCHIVO"))
                                    SalidaImpresion = UserHome+"\\TICKET_COMPRA_"+folio+".TXT";

                                //FileDescriptor fd = new FileDescriptor();
                                FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM
                                PrintStream ps = new PrintStream(os);
                                ps.print(sCad); // CADENA A IMPRIMIR
                               // ps.print(sCad); // CADENA A IMPRIMIR(Copia)
                                ps.flush();
                                os.close();
                            }catch(java.io.FileNotFoundException fsctex){
                                fsctex.printStackTrace();
                                String UserHome = System.getProperty("user.home");
                                SalidaImpresion = UserHome+"\\TICKET_COMPRA_"+folio+".TXT";
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
                            }catch(Exception sctex){
                                sctex.printStackTrace();
                            }

                           }
 }

}
