/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JdlgVistaPagoImpresion.java
 *
 * Created on 14/03/2014, 11:29:24 AM
 */
package com.estrellaroja.confirmacionviajes.forms;

import com.estrellaroja.confirmacionviajes.entidades.TmsImpresora;
import com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsImpresoraFacadeRemote;
import com.estrellaroja.confirmacionviajes.solicitudes.TmsTarjetasViajeFacadeRemote;
import com.estrellaroja.confirmacionviajes.util.CantidadLetras;
import com.estrellaroja.confirmacionviajes.util.PcInfo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author EKS Victor
 */
public class JdlgVistaPagoImpresion extends javax.swing.JDialog {

  private boolean accept = false;
  private JTextArea jTextArea1 = new JTextArea();
  private JLabel jlbl_mensajeVPI = new JLabel();
  private JLabel jlbl_barraEstadoVPI = new JLabel();
  private ImageIcon imageHelp = new ImageIcon(JdlgVistaPagoImpresion.class.getResource("/com/estrellaroja/confirmacionviajes/forms/images/pregunta.gif"));
  private JLabel jLabel2 = new JLabel();
  private TmsTarjetasViaje tarjeta;
  private String usuario;
  private SimpleDateFormat formatf = new SimpleDateFormat("dd/MM/yyyy");
  private SimpleDateFormat formath = new SimpleDateFormat("HH:mm");
  private Timestamp serverTime;
  private int alto = 0;
  private static TmsTarjetasViajeFacadeRemote tarjetasViajeFacade;
  private static TmsImpresoraFacadeRemote impresoraFacade;
  private double sueldo = 0.0D;
  private double retencion = 0.0D;
  private double totaltarjeta = 0.0D;
  private double vta_manual = 0.0D;
  private double pagoOperador = 0.0D;
  private double saldo = 0.0D;
  private boolean reimpresion= false;

  private TmsTarjetasViajeFacadeRemote lookupTmsTarjetasViajeFacade() {
    try {
      Context c = new InitialContext();
      return (TmsTarjetasViajeFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.TmsTarjetasViajeFacadeRemote");
    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
  }

  private TmsImpresoraFacadeRemote lookupTmsImpresoraFacade() {
    try {
      Context c = new InitialContext();
      return (TmsImpresoraFacadeRemote) c.lookup("com.estrellaroja.confirmacionviajes.solicitudes.TmsImpresoraFacadeRemote");
    } catch (NamingException ne) {
      // Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
      throw new RuntimeException(ne);
    }
  }

  /** Creates new form JdlgVistaPagoImpresion */
  public JdlgVistaPagoImpresion(TmsTarjetasViaje tarjeta, String usuario,boolean preimpresion) {
    this.reimpresion = preimpresion;
    this.tarjeta = tarjeta;
    this.usuario = usuario;
    if(this.reimpresion)
        setTitle("Vista de ReImpresion de Ticket");
    else
        setTitle("Vista de Impresion de Ticket");
    setDefaultLookAndFeelDecorated(true);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(7);
    setAlwaysOnTop(true);
    setModal(true);
    this.alto = 40;
    jbInit();
    this.jTextArea1.requestFocus();
    tarjetasViajeFacade = lookupTmsTarjetasViajeFacade();
    impresoraFacade = lookupTmsImpresoraFacade();
    try {
      fillData();
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }
  }

  private void jbInit() {
    setSize(new Dimension(400, 450 + this.alto));
    setLayout(null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension DilaogSize = getSize();
    if (DilaogSize.height > screenSize.height) {
      DilaogSize.height = screenSize.height;
    }
    if (DilaogSize.width > screenSize.width) {
      DilaogSize.width = screenSize.width;
    }
    setLocation((screenSize.width - DilaogSize.width) / 2, (screenSize.height - DilaogSize.height) / 2);
    setResizable(false);
    this.jlbl_barraEstadoVPI.setFont(new Font("Tahoma", 1, 12));
    this.jlbl_barraEstadoVPI.setForeground(new Color(0, 0, 0));
    this.jlbl_barraEstadoVPI.setBorder(BorderFactory.createBevelBorder(1));
    this.jlbl_barraEstadoVPI.setBounds(new Rectangle(0, 395 + this.alto, 400, 25));
    this.jlbl_barraEstadoVPI.setText("<html>  <font color=FF0000>ENTER: </font> Si        |         <font color=FF0000>             ESC: </font> No  </html>");
    this.jlbl_barraEstadoVPI.setHorizontalAlignment(0);
    this.jTextArea1.setBounds(new Rectangle(10, 10, 370, 300 + this.alto));
    this.jTextArea1.setEditable(false);
    this.jTextArea1.setFont(new Font("Dialog", 0, 12));
    this.jTextArea1.setSize(new Dimension(375, 335 + this.alto));
    if(this.reimpresion)
        this.jlbl_mensajeVPI.setText("¿Seguro que desea Reimprimir el Ticket?");
    else
        this.jlbl_mensajeVPI.setText("¿Seguro que desea confirmar la tarjeta?");
    this.jlbl_mensajeVPI.setBounds(new Rectangle(105, 365 + this.alto, 250, 15));
    this.jlbl_mensajeVPI.setFont(new Font("Arial", 1, 12));
    this.jTextArea1.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent evt) {
        JdlgVistaPagoImpresion.this.jTextArea1_KeyPressed(evt);
      }
    });
    this.jTextArea1.setFocusTraversalKeysEnabled(false);
    this.jLabel2.setBounds(new Rectangle(60, 350 + this.alto, 35, 45));
    this.jLabel2.setIcon(this.imageHelp);
    this.jLabel2.setFocusable(false);
    this.jlbl_mensajeVPI.setFocusable(false);
    add(this.jLabel2, null);
    add(this.jTextArea1, null);
    add(this.jlbl_barraEstadoVPI, null);
    add(this.jlbl_mensajeVPI, null);
    this.jTextArea1.requestFocus();

  }

  private void fillData() {
    serverTime = fechaServidor();
    try {
      vta_manual = Long.parseLong(tarjeta.getImporteVentaManual());
    } catch (NumberFormatException e) {
      vta_manual = 0;
    }

    ArrayList values = tarjetasViajeFacade.getValues(tarjeta.getTarjetaViajeId(), tarjeta.getClaveOperador());
    if (values != null) {
      sueldo = ((Double) values.get(0)).doubleValue();
      retencion = ((Double) values.get(1)).doubleValue();
      totaltarjeta = sueldo - retencion;
      pagoOperador = ((Double) values.get(2)).doubleValue();
      saldo = ((Double) values.get(3)).doubleValue();
    }

    this.jTextArea1.append(" Autobus : " + tarjeta.getAutobus() + "\n");
    this.jTextArea1.append(" Folio de Tarjeta :   " + tarjeta.getFolioTarjeta() + "\n");
    this.jTextArea1.append(" Servicio               :   " + tarjeta.getServicio() + "\n");
    this.jTextArea1.append(" Fecha de Viaje  :   " + formatf.format(tarjeta.getFechaHoraCorrida()) + "\n");
    this.jTextArea1.append(" Hora de Viaje    :   " + formath.format(tarjeta.getFechaHoraCorrida()) + "\n");

    this.jTextArea1.append(" Operador             :   " + tarjeta.getClaveOperador() + " -" + tarjeta.getNombreOperador() + "\n");
    this.jTextArea1.append(" Sueldo                :   " + Round(sueldo, 2) + "\n");
    this.jTextArea1.append(" Retencion          :   " + Round(retencion, 2) + "\n");
    this.jTextArea1.append(" Total                   :   " + Round(totaltarjeta, 2) + "\n");
//    if (pagoOperador > 0.0D) {
//      this.jTextArea1.append(" Monto Pagado      :   " + pagoOperador + "\n");
//    }
    if (saldo != 0.0D) {
      this.jTextArea1.append(" Saldo Operador   :   " + saldo + "\n");
    }
    if ((this.totaltarjeta == 0.0D) && (pagoOperador > 0.0D)) {
      String centavos = getCentavos(pagoOperador);
      this.jTextArea1.append("              ( " + CantidadLetras.toLetras((long) pagoOperador) + " Pesos "+centavos+"/M.N.)\n");
    } else {
      String centavos = getCentavos(totaltarjeta);
      this.jTextArea1.append("              ( " + CantidadLetras.toLetras((long) Round(this.totaltarjeta, 0)) + " Pesos "+centavos+"/M.N.)\n");
    }
    if (this.vta_manual > 0.0D) {
      this.jTextArea1.append(" Venta Manual    :   " + this.vta_manual + "\n");
    }
    this.jTextArea1.append(" Recaudador      :   " + this.usuario + "\n");

    this.jTextArea1.append(" Fecha de Recaudacion:  " + formatf.format(serverTime) + "\n");
    this.jTextArea1.append(" Hora de Recaudacion  : " + formath.format(serverTime) + "\n");
    this.jTextArea1.append(" Firma: ________________________ \n");
  }

  private void jTextArea1_KeyPressed(KeyEvent evt) {
    if (evt.getKeyCode() == 27) {
      accept = false;
      dispose();
    }
    if (evt.getKeyCode() == 10) {
      imprimir_recibo();
      accept = true;
      dispose();
    }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  public void imprimir_recibo() {
    System.out.println("Host Name: "+(new PcInfo()).getHostName());
    List<TmsImpresora> printers = impresoraFacade.findByCaja((new PcInfo()).getHostName());
    if (printers != null && printers.size() > 0) {
      TmsImpresora printer = printers.get(0);
      System.out.println("Puerto: "+ printer.getPuerto());
      System.out.println("NombreImpresora: "+ printer.getImpresoraNombre());
      System.out.println("NomberFormato: "+ printer.getFormatoNombre());
      String puerto = printer.getPuerto();
      if ((puerto.equals("LPT1")) || (puerto.equals("LPT2")) || (puerto.equals("LPT3")) || (puerto.equals("LPT4")) || (puerto.equals("COM1")) || (puerto.equals("COM2")) || (puerto.equals("ARCHIVO"))) {
        if (!imprimir_recibo_LPT(puerto,printer.getFormatoNombre())) {
          new JdlgError("¡No se pudo imprimir el ticket de pago por error de la impresora!", "", "Error de impresión").setVisible(true);
        }
      }
      if ((puerto.equals("RED")) || (puerto.equals("USB"))) {
        if (!imprimir_recibo_LPT(printer.getImpresoraNombre(),printer.getFormatoNombre())) {
          new JdlgError("¡No se pudo imprimir el ticket por error de la impresora!", "", "Error de impresión").setVisible(true);
        }
      }
    } else {
      new JdlgAdvertencia("¡La impresora no esta instalada!. El ticket no será impreso", " El folio de la tarjeta es: " + tarjeta.getFolioTarjeta(), "Impresora no encontrada").setVisible(true);
      return;
    }
    //TODO: ASK about this
//    Jdlg_Pregunta p = new Jdlg_Pregunta("Reimpresion de comprobante", nref);
//    p.setVisible(true);

  }

  public boolean imprimir_recibo_LPT(String pSalidaImpresion,String formato) {
    String SalidaImpresion = pSalidaImpresion;
    String nombreFormato = formato;
    String sCad = "\n";
    sCad = sCad + "    AUTOBUSES MEX-PUE ESTRELLA ROJA";
    sCad = sCad + "\n\n";
    sCad = sCad + "\n";
    sCad = sCad + "\n";
    sCad = sCad + "AUTOBUS:       " + tarjeta.getAutobus();
    sCad = sCad + "\n";
    sCad = sCad + "FOLIO TARJETA: ";
    String folioCompleto = tarjeta.getFolioTarjeta();
    String inicio = folioCompleto.substring(0, 10);
    String fin = folioCompleto.substring(10);
    sCad = sCad + "" + inicio;
    sCad = sCad + "\n";
    sCad = sCad + "               " + fin;
    sCad = sCad + "\n";
    sCad = sCad + "SERVICIO:       " + tarjeta.getServicio();
    sCad = sCad + "\n";
    sCad = sCad + "FECHA VIAJE:    " + formatf.format(tarjeta.getFechaHoraCorrida());
    sCad = sCad + "\n";
    sCad = sCad + "HORA VIAJE:     " + formath.format(tarjeta.getFechaHoraCorrida());
    if (vta_manual > 0.0D) {
      sCad = sCad + "\n";
      sCad = sCad + "VTA MANUAL:     " + vta_manual;
    }
    sCad = sCad + "\n";
    sCad = sCad + "OPERADOR:   " + tarjeta.getClaveOperador() + " - " + tarjeta.getNombreOperador();
    sCad = sCad + "\n";
    sCad = sCad + "SUELDO:     " + Round(sueldo, 2);
    sCad = sCad + "\n";
    sCad = sCad + "RETENCION:  " + Round(retencion, 2);
    sCad = sCad + "\n";
    sCad = sCad + "TOTAL:      " + Round(totaltarjeta, 2);
    sCad = sCad + "\n";
    sCad = sCad + "\n";

    if ((totaltarjeta == 0.0D) && (pagoOperador > 0.0D)) {
      String centavos = getCentavos(pagoOperador);
      sCad = sCad + "( " + CantidadLetras.toLetras((long) pagoOperador) + "Pesos "+centavos+"/M.N. )";
    } else {
      String centavos = getCentavos(totaltarjeta);

      sCad = sCad + "( " + CantidadLetras.toLetras((long) Round(totaltarjeta, 2)) + "Pesos "+centavos+"/M.N. )";
    }
    sCad = sCad + "\n";
    sCad = sCad + "\n";
    if (pagoOperador > 0.0D) {
      sCad = sCad + "MONTO PAGADO: " + pagoOperador;
      sCad = sCad + "\n";
    }
    if (saldo != 0.0D) {
      sCad = sCad + "SALDO OPERADOR: " + saldo;
      sCad = sCad + "\n";
    }


    sCad = sCad + "RECAUDADOR: " + this.usuario;
    sCad = sCad + "\n";
    sCad = sCad + "FECHA DE RECAUDACION: " + formatf.format(serverTime);
    sCad = sCad + "\n";
    sCad = sCad + "HORA DE RECAUDACION : " + formath.format(serverTime);
    sCad = sCad + "\n";
    sCad = sCad + "\n";
    sCad = sCad + "FIRMA ________________________";
    sCad = sCad + "\n\n\n\n\n\n\n\n\n\n\n\n";
    sCad = sCad + "         .";

    String letras ="";
    if ((totaltarjeta == 0.0D) && (pagoOperador > 0.0D)) {
      String centavos = getCentavos(pagoOperador);
      letras =  "( " + CantidadLetras.toLetras((long) pagoOperador) + "Pesos "+centavos+"/M.N. )";
    } else {
      String centavos = getCentavos(totaltarjeta);

      letras = "( " + CantidadLetras.toLetras((long) Round(totaltarjeta, 2)) + "Pesos "+centavos+"/M.N. )";
    }

    String  ticketTermico = "Q200,0\r\n"
+ "q831\r\n"
+ "rN\r\n"
+ "S4\r\n"
+ "D7\r\n"
+ "ZT\r\n"
+ "JB\r\n"
+ "O\r\n"
+ "R124,0\r\n"
+ "f100\r\n"
+ "N\r\n"
+ "A15,546,0,4,1,1,N,\"Fecha Recaudacion:\"\r\n"
+ "A9,244,0,4,1,1,N,\"Fecha Viaje:\"\r\n"
+ "A31,581,0,4,1,1,N,\"Hora Recaudacion:\"\r\n"
+ "A24,274,0,4,1,1,N,\"Hora Viaje:\"\r\n"
+ "A56,216,0,4,1,1,N,\"Servicio:\"\r\n"
+ "A210,216,0,3,1,1,N,\""+tarjeta.getServicio()+"\"\r\n"
+ "A40,366,0,4,1,1,N,\"Retencion:\"\r\n"
+ "A315,546,0,4,1,1,N,\""+formatf.format(serverTime)+"\"\r\n"
+ "A210,244,0,4,1,1,N,\""+formatf.format(tarjeta.getFechaHoraCorrida())+"\"\r\n"
+ "A404,581,0,3,1,1,N,\"Horas\"\r\n"
+ "A316,581,0,4,1,1,N,\""+formath.format(serverTime)+"\"\r\n"
+ "A296,275,0,3,1,1,N,\"Horas\"\r\n"
+ "A208,275,0,4,1,1,N,\""+formath.format(tarjeta.getFechaHoraCorrida())+"\"\r\n"
+ "A215,520,0,2,1,1,N,\""+this.usuario+"\"\r\n"
+ "A210,304,0,4,1,1,N,\""+tarjeta.getClaveOperador() + " - " + tarjeta.getNombreOperador()+"\"\r\n"
+ "A54,304,0,4,1,1,N,\"Operador:\"\r\n"
+ "A249,698,0,4,1,1,N,\"Firma\"\r\n"
+ "A30,516,0,4,1,1,N,\"Recaudador:\"\r\n"
+ "A211,397,0,4,1,1,N,\""+Round(totaltarjeta, 2)+"\"\r\n"
+ "A26,460,0,2,1,1,N,\""+letras+"\"\r\n"
+ "A210,366,0,4,1,1,N,\""+Round(retencion, 2)+"\"\r\n"
+ "A210,336,0,4,1,1,N,\""+Round(sueldo, 2)+"\"\r\n"
+ "A104,397,0,4,1,1,N,\"Total:\"\r\n"
+ "A86,337,0,4,1,1,N,\"Sueldo:\"\r\n"
+ "A210,152,0,4,1,1,N,\""+tarjeta.getAutobus()+"\"\r\n"
+ "A212,182,0,4,1,1,N,\""+tarjeta.getFolioTarjeta()+"\"\r\n"
+ "A23,152,0,4,1,1,N,\"No.Autobus:\"\r\n"
+ "A72,182,0,4,1,1,N,\"Tarjeta:\"\r\n"
+ "A39,77,0,4,1,1,N,\"AUTOBUSES MEX-PUE ESTRELLA ROJA\"\r\n"
+ "LO157,690,256,1\r\n"
+ "LO90,104,408,2\r\n"
+ "P1\r\n";




     try {
      if (SalidaImpresion.equals("ARCHIVO")) {
        SalidaImpresion = System.getProperty("user.home")+"\\TICKET_PAGO_" + tarjeta.getFolioTarjeta() + ".TXT";
        System.out.println("Salida Impresion en: "+ SalidaImpresion);
      }

      FileDescriptor fd = new FileDescriptor();
      FileOutputStream os = new FileOutputStream(SalidaImpresion);
      PrintStream ps = new PrintStream(os);
      if(nombreFormato.equals("TICKET_TERMICO"))
        ps.print(ticketTermico);
      else
        ps.print(sCad);
      ps.flush();
      os.close();
    } catch (FileNotFoundException fsctex) {
      System.out.println("FileNotFoundException: "+fsctex.getMessage() );
      return false;
    } catch (Exception sctex) {
      System.out.println("Exception: "+sctex.getMessage() );
      return false;
    }
    return true;
  }

  private static double Round(double Rval, int Rpl) {
    double p = Math.pow(10.0D, Rpl);
    Rval *= p;
    double tmp = Math.round(Rval);
    return tmp / p;
  }

  private Timestamp fechaServidor() {
    return tarjetasViajeFacade.getFechaServidor();
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

  public static boolean showTicket(TmsTarjetasViaje tarjeta, String usuario, boolean ppreimpresion) {
    JdlgVistaPagoImpresion dialog = new JdlgVistaPagoImpresion(tarjeta, usuario,ppreimpresion);
    dialog.setVisible(true);
    return dialog.accept;
  }

  private String getCentavos(double cantidad) {
    String total = cantidad+"";
    int idx = total.lastIndexOf(".");
    if(idx < 0){
      return "00";
    }else{
      int c = total.length()-idx;
      if(c>2){
        return total.substring(idx+1,idx+3);
      }else
        return total.substring(idx+1)+"0";
    }
  }
}
