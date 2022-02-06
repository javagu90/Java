/*
 * cancelarTarjetaCredito.java
 *
 * Created on 21 de junio de 2007, 07:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package DialogosX;

/**
 *
 * @author vgonzalez
 */
//import impresion.imprimir_voucher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

import java.util.StringTokenizer;
//import javadllapp.EGlobalSckCls;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import tms_venta.JClsColoresInterfaz;
import tms_venta.SesionVenta;

public class cancelarTarjetaCredito extends JDialog {
     private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
     public String aprobacion="";
     private JScrollPane jScrollPane1 = new JScrollPane();
     private DefaultTableModel modelo = new DefaultTableModel(){
     public boolean isCellEditable (int row, int column){
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columan (row,column), sea o no editable
            if (column == 1)
               return true;
            return false;
        }};
    private JTable jTblTipoPagos = new JTable(modelo);
    private String monto;
    private String nombreEmpresa;
    private JLabel jLabel1 = new JLabel();
    private boolean exito=false;
    private SesionVenta sesionVenta;
    private boolean unSoloAviso = true;
    private String salidaImpresion=null;

    private JDlgAceptar DialogoAceptar = new JDlgAceptar();

    private String cajaNumero;
    private SimpleDateFormat formatoFechaTXTC = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public cancelarTarjetaCredito(SesionVenta pSesionVenta, String m) {
        this.setTitle("Cancelacion de venta con tarjeta bancaria");
        this.setModal(true);
        this.sesionVenta = pSesionVenta;
        this.monto = m;
        this.nombreEmpresa = sesionVenta.getTmsBoletosVentaTbl().getEmpresa();
        cajaNumero=sesionVenta.getUserCon().getCajaNumero();
        //System.out.println("Caja numero - cancelacion ::: "+cajaNumero);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getExito(){ return exito; }
    
    private void jbInit() throws Exception {
        this.setSize(new Dimension(594, 160));
        this.getContentPane().setLayout( null );
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );
        modelo.addColumn("Importe");
        modelo.addColumn("Tarjeta");
        modelo.addColumn("Autorizacion");
        String[] row = new String[5];
        modelo.addRow(row);
        jTblTipoPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTblTipoPagos.setCellSelectionEnabled(false);
        jScrollPane1.setBounds(new Rectangle(5, 15, 575, 65));
        jTblTipoPagos.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        jTable1_keyReleased(e);
                    }
                });
        jLabel1.setText("  DESLIZE LA TARJETA DE CREDITO  O  DIGITE EL NUMERO DE TARJETA Y LA FECHA DE VENCIMIENTO");
        jLabel1.setBounds(new Rectangle(-5, 105, 600, 25));
        jLabel1.setSize(new Dimension(595, 25));
        jLabel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jLabel1.setToolTipText("null");
        jScrollPane1.getViewport().add(jTblTipoPagos, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jScrollPane1, null);
        jTblTipoPagos.setValueAt(monto,0,0);
        jTblTipoPagos.setRowSelectionInterval(0,0);
        jTblTipoPagos.setColumnSelectionInterval(1,1);
    }

    private String formatoCantidad(String mt){
	if(!mt.contains(".")) return mt+"00";
	int pos=mt.indexOf(".");
        String entero=mt.substring(0, pos);
        String decimal=mt.substring(pos+1);
        if(decimal.length()==0) return entero+"00";
        if(decimal.length()==1) return entero+decimal+"0";
        if(decimal.length()==2) return entero+decimal;
        return entero+decimal.substring(0,2);
    }

    private void jTable1_keyReleased(KeyEvent e) {
        if(jTblTipoPagos.getSelectedRow() == 0 && jTblTipoPagos.getSelectedColumn()==2 && e.getKeyCode() == e.VK_ENTER){
            if(!sesionVenta.aunExisteBoletoVendido(sesionVenta.getTmsBoletosVentaTbl().getOrigen(), 
                sesionVenta.getTmsBoletosVentaTbl().getFolioPreimpreso(), sesionVenta.getTmsBoletosVentaTbl().getNoAsiento().toString(), 
                sesionVenta.getTmsBoletosVentaTbl().getEmpresa(),sesionVenta.getTmsBoletosVentaTbl().getOrigenCorrida())){
                DialogoAceptar.mostrarDialogo("Cancelación de boleto.","No es posible realizar la cancelación de este boleto.",Color.RED);
                exito=false;
                this.dispose();
                return;
            }
            
            salidaImpresion = sesionVenta.getImpresoraVoucher(sesionVenta.getTmsBoletosVentaTbl().getEmpresa());
            if(salidaImpresion==null){
                exito=false;
                this.dispose();
                return;
            }

            jTblTipoPagos.setRowSelectionInterval(0,0);
            jTblTipoPagos.setColumnSelectionInterval(1,1);
        
            cancelacionTarjetaBancaria_Bancomer();
        }
        if(e.getKeyCode() == e.VK_ESCAPE) this.dispose();
    }

    private String invierteFecha(String fecha) {
        String fechaResultado;
        fechaResultado = ""+fecha.charAt(2)+""+fecha.charAt(3)+""+fecha.charAt(0)+""+fecha.charAt(1);
        //System.out.println("Fecha invertida"+fechaResultado);
        return fechaResultado;
    }
    
    private void interfazColor(){
        setBackground(ColoresInterfaz.cFondoDialogo);
        jTblTipoPagos.setBackground(ColoresInterfaz.cFondoDialogo);
        jLabel1.setFont(ColoresInterfaz.fuente0);
    }

    private void cancelacionTarjetaBancaria_Bancomer() {
//
//        if(jTblTipoPagos.getValueAt(jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn())!=null){
//            String valorlectura="";
//            if(jTblTipoPagos.getValueAt(jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn()).toString().equals("")) ;
//            else { // proceso tarjeta
//                String vIP = sesionVenta.getValorParametro("P_VLREGLBIP", -1);//"192.168.16.12";
//                int    vPort = Integer.valueOf(sesionVenta.getValorParametro("P_VLREGLBPT", -1).toString());//1025;
//                int    vSeconds = Integer.valueOf(sesionVenta.getValorParametro("P_VLREGLBTO", -1).toString());//25;
//                String vRequest = "";
//                String vResponse = "";
//                EGlobalSckCls eGlobal=null;
//                try{
//                    eGlobal = new EGlobalSckCls();
//                }catch(Exception clsEx){
//                    DialogoAceptar.mostrarDialogo("¡Configuracion incorrecta!", "Clase javadllapp/EGlobalSckCls no encontrada.",Color.RED);
//                    return;
//                }
//                valorlectura =(String)jTblTipoPagos.getValueAt(jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                if(valorlectura.indexOf("%")>=0 && valorlectura.indexOf("&")>=0 && valorlectura.indexOf("_")>=0 && valorlectura.length()>20) {
//                    // cuando la Tarjeta es deslizada...
//                    StringTokenizer tokens=new StringTokenizer(valorlectura,"&");
//                    String toknc = tokens.nextToken();
//                    String numerocuenta = toknc.substring(toknc.length() - 16);
//                    String nombre = tokens.nextToken();
//                    String tokfv = tokens.nextToken();
//                    StringTokenizer tokensfv=new StringTokenizer(tokfv,"_");
//                    String f1 = tokensfv.nextToken();
//                    f1 = tokensfv.nextToken();
//                    StringTokenizer tokensf2=null;
//                    String fecha;
//                    try{
//                       System.out.println("::::::::::::::::::::::::::::::: INTERROGACION");
//                       tokensf2=new StringTokenizer(f1,"¿");
//                       fecha=tokensf2.nextToken();
//                       fecha=tokensf2.nextToken();
//                    }
//                    catch(NoSuchElementException nsex){
//                       System.out.println("::::::::::::::::::::::::::::::: ADMIRACION");
//                       tokensf2=new StringTokenizer(f1,"¡");
//                       fecha=tokensf2.nextToken();
//                       fecha=tokensf2.nextToken();
//                    }
//                    String mt =  jTblTipoPagos.getValueAt(jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() - 1).toString();
//                    mt = formatoCantidad(mt);
//                    int ceros = 12 - mt.length();
//                    String monto ="";
//                    for(int i=1;i<=ceros;i++)
//                       monto = monto +"0";
//                    monto = monto + mt;
//                    String transaccion = "CANCELACION";
//                    String tipotransaccion ="002";
//                    String terminal = cajaNumero; //"00000001"; //"30000049";
//                    String valorformado = tipotransaccion+terminal+"00010001"+monto+"000000000000000000000000";
//                    String resultado;
//                    vRequest = valorformado+"S"+numerocuenta+"="+fecha;
//
//                    resultado = eGlobal.EGlobalSck(vIP,vPort,vSeconds,vRequest,vResponse);
//                    /*try{
//                        FileOutputStream os1 = new FileOutputStream("C:\\LOGTARJBANC.TXT",true);
//                        PrintStream ps1 = new PrintStream(os1);
//                        ps1.print(vRequest+"\n"+resultado+" ["+formatoFechaTXTC.format(new Date())+"]\n\n"); // CADENA A IMPRIMIR
//                        ps1.flush();
//                        os1.close();
//                    }catch(java.io.FileNotFoundException fsctex){
//                        return;
//                    }catch(Exception sctex){
//                        return;
//                    }*/
//                     if(resultado.substring(resultado.length()-1).equals("0")  && resultado.length()>25){
//                      if(resultado.substring(16,18).equals("00")){
//                         aprobacion = resultado.substring(32, resultado.length()-1);
//                         jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//                         jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                        //imprime ticket
//                        imprimir_voucher voucher = new  imprimir_voucher(numerocuenta, nombre, fecha, mt,transaccion, aprobacion,
//                                sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom());
//                        voucher.ImprimeDatos(salidaImpresion);
//                        exito=true;
//                        this.dispose();
//                        return;
//                      }
//                      else{
//                           jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//                           jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                      }
//                     }
//                     else{
//                         String mensaje = MensajeResultado(resultado.trim());
//                         jTblTipoPagos.setValueAt(mensaje,jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//                         jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                     }
//                }// digitada
//                else{
//                     if(!sesionVenta.getUserCon().getDigitaTB()) return;
//                     boolean digitos = true;
//                     char[] valores = valorlectura.toCharArray();
//                     for(int i=0; i<valorlectura.length();i++)
//                       {
//                        char c = valores[i];
//                        if(!Character.isDigit(c))
//                         digitos = false;
//                       }
//                     if(valorlectura.length()==20 && digitos)
//                       {
//                          //cuando la Tarjeta es digitada
//                           String numerocuenta = valorlectura.substring(0,16);
//                           String fecha = valorlectura.substring(16);
//                           fecha=invierteFecha(fecha);
//                           String resultado;
//                           String mt =  jTblTipoPagos.getValueAt(jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() - 1).toString();
//                           mt = formatoCantidad(mt);
//                           int ceros = 12 - mt.length();
//                           String monto ="";
//                           for(int i=1;i<=ceros;i++)
//                               monto = monto +"0";
//                           monto = monto + mt;
//                           String transaccion = "CANCELACION";
//                           String tipotransaccion ="002";
//                           String terminal= cajaNumero; //"00000001"; //"30000049";
//                           String valorformado = tipotransaccion+terminal+"00010001"+monto+"000000000000000000000000";
//                           vRequest = valorformado+"K000000"+numerocuenta+"="+fecha;
//                           //System.out.println("INFO IMPORTANTE "+vRequest);
//                           resultado = eGlobal.EGlobalSck(vIP,vPort,vSeconds,vRequest,vResponse);
//                           /*try{
//                                FileOutputStream os1 = new FileOutputStream("C:\\LOGTARJBANC.TXT",true);
//                                PrintStream ps1 = new PrintStream(os1);
//                                ps1.print(vRequest+"\n"+resultado+" ["+formatoFechaTXTC.format(new Date())+"]\n\n"); // CADENA A IMPRIMIR
//                                ps1.flush();
//                                os1.close();
//                            }catch(java.io.FileNotFoundException fsctex){
//                                return;
//                            }catch(Exception sctex){
//                                return;
//                            }*/
//                           if(resultado.substring(resultado.length()-1).equals("0")  && resultado.length()>25)
//                           {
//                             if(resultado.substring(16,18).equals("00"))
//                             {
//                               aprobacion = resultado.substring(32, resultado.length()-1);
//                               jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//                               jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                               //imprime ticket
//                               imprimir_voucher voucher = new  imprimir_voucher(numerocuenta, "", fecha, mt,transaccion, aprobacion,
//                                       sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom());
//                               voucher.ImprimeDatos(salidaImpresion);
//                               exito=true;
//                               this.dispose();
//                               return;
//                              }
//                               else
//                               {
//                                    jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//                                    jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                                }
//                           }
//                           else{
//                                String mensaje = MensajeResultado(resultado.trim());
//                                jTblTipoPagos.setValueAt(mensaje,jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//                                jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                           }
//                       }
//                     else{
//                           jTblTipoPagos.setValueAt("Datos incorrectos",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//                           jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//                         }
//                }
//            } // proceso tarjeta
//        }
    }
    
    private String MensajeResultado(String string) {
        String mensaje="";
        int numero = Integer.parseInt(string);
        switch(numero){
            case 1: mensaje = "Error en wosa (sockets)"; break;
            case 2: mensaje = "Socket Invalido"; break;
            case 3: mensaje = "El Servidor SebeNT esta abajo!"; break;
            case 4: mensaje = "Timeout!"; break;
            case 5: mensaje = "El requerimiento esta vacio"; break;
            case 6: mensaje = "Cuenta inválida"; break;
            case 7: mensaje = "Digito verificador inválido"; break;
            case 8: mensaje = "El DLL no puede enviar!"; break;
            default: mensaje = "Numero de tarjeta invalida!"; break;
        }
        return mensaje;
    }
}