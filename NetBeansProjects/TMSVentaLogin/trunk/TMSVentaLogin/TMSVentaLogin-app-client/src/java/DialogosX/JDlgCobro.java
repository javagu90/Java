package DialogosX;
 
import AsignacionVenta.jDlgAutorizaSupervisor;
import TMSVtaProductosER.entidad.ProductoCarrito;
import impresion.ImprimeBoletos;
import impresion.imprimir_ticket_redencion;
import impresion.imprimir_ticket_vta_ref;
//import impresion.imprimir_voucher;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;   
import java.util.Vector;
//import javadllapp.EGlobalSckCls;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import subProcesos.JCuantityTextFieldMaxDig;
import subProcesos.JTextTextFieldMaxChar;
import subProcesos.Mensajes;
import subProcesos.RelojVisualVta;
import tms_TextFields.JCuantityTextField;
import tms_venta.SesionVenta;
import tms_venta.JClsColoresInterfaz;
import tms_venta.entidad.TmsTiposPasajeroTbl;
import tms_vta_productos_er.JdlgVentaCarritoProductosER;
import xer_emv_dll.JClsImprimeVoucher;
import xer_emv_dll.JClsPinPadTBRequest;
import xer_emv_dll.JClsPinPadTBResponse;
import xer_emv_dll.JDlgBProgresoLealtadReden;
import xer_emv_dll.JDlgSysCboStandr;

public class JDlgCobro extends javax.swing.JDialog {
    //private EGlobalSckCls eGlobal=null;
    private boolean ventaRegistrada;
    private boolean primeraVezENTER;
    private boolean primeraVezF10;
    private boolean primeraVezF12;
    private boolean excluyeTeclasVtaActual;
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
    private Dimension screenSize;
    private final Color ARENA=new Color(238, 238, 238);
    private static Color SELECCION = new Color(184, 207, 229);
    private static Color BLANCO = new Color(255, 255, 255);
    private boolean unAviso;
    private boolean primerPresion=false;
    private String cajaNumero;
    private String aprobacion="";
    private String valorlectura="";
    private JPanel Contenedor = new JPanel();
    private JLabel Titulo = new JLabel("  Venta e impresion de boletos");
    private Color colorDialogoActivo = new Color(0,83,255);
    private Mensajes jLblMensajes = new Mensajes();
    private JPanel jPnlTipoPagos = new JPanel();
    private JScrollPane jScrTipoPagos = new JScrollPane();
    public JTable jTblTipoPagos = new JTable();
    private boolean noAceptarPagoConTarjeta;
    private int filaSel=0;
    private int columnaSel=3;
    private int iFila=-1, iCol=-1;
    private JPanel tipo_Boletos = new JPanel();
    private JLabel jLblTipoBoleto = new JLabel();
    private JPanel detalle_Boletos = new JPanel();
    private JScrollPane jScrDetalleBoletos = new JScrollPane();
    private JTable jTbl_Boletos = new JTable();
    private String vReferenciaPago;
    /**
     * Aqui se encuentran los elementos de los totales de la venta
     * */
    private JPanel JPanelTotales = new JPanel();
    private JCuantityTextField jTxtTotalVenta = new JCuantityTextField();
    private JLabel jLblTotal = new JLabel();
    private JCuantityTextField jTxtRestan = new JCuantityTextField();
    private JLabel jLblRestan = new JLabel();
    private JCuantityTextField jTxtCambio = new JCuantityTextField();
    private JLabel jLblCambio = new JLabel();
    private JCuantityTextField jTxtRecibo = new JCuantityTextField();
    private JLabel jLblRecibo = new JLabel();
    private static final int MAX_CUANTITY = 8;
    private static final int MAX_CHAR = 120;
    private boolean impresoraErronea=false;
    private String salidaImpresion;
    private String salidaImpresionTVRef;
    private JTextTextFieldMaxChar jtxtDocumento = new JTextTextFieldMaxChar(MAX_CHAR){
        public void paste() {
            // UIManager.getLookAndFeel().provideErrorFeedback(this);
            ;
        }
    };
    private JCuantityTextFieldMaxDig jDblRecibo = new JCuantityTextFieldMaxDig(MAX_CUANTITY); // ACEPTA MAXIMO 8 DIGITOS 99999999 (99999.99)
    private JLabel barraMensaje = new JLabel();
    /**
     * declaraciones de  gridbaglayouts utilizados.
     * */
    private GridBagLayout gridBagLayout_1 = new GridBagLayout();
    private GridBagLayout gridBagLayout_2 = new GridBagLayout();
    private GridBagLayout gridBagLayout0 = new GridBagLayout();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private GridBagLayout gridBagLayout2 = new GridBagLayout();
    private GridBagLayout gridBagLayout3 = new GridBagLayout();
    private GridBagLayout gridBagLayout4 = new GridBagLayout();
    private GridBagLayout gridBagLayout5 = new GridBagLayout();
    
    private JLabel jLblReferencia1 = new JLabel();
    private int ventaOk=1;

    private SesionVenta sesionVenta = null;
    private String doctoRvn;
    private Object[] BA;
    
    private boolean entrada=true;
    private String transaccion;
    
    // formatos de impresion
    private final String fbESTANDAR="BOLETO_ESTANDAR";
    private final String fbABIERTO="BOLETO_ABIERTO";
    
    private String strViaje="SENCILLO";
    private int filaAmerican=-1;
    private int filaBancomer=-1;
    private int filaAeromexico=-1;
    private int filaCuponesViaje=-1;
    private JDlgCupones jDlgCupones;
    private Object[][] CuponesViaje=null;
    
    private String nombreEmpresa;
    //private int digitos=0;
    private JDlgAceptar DialogoAceptar = new JDlgAceptar();
    private JDlgNombreCliente dialogoNomcliente = null;
    
    private boolean campoDocto=false;
    
    private Object[][] FormasPago=null;
        private Object[] encFormasPago = {"Forma de Pago", "Clave", "Total a Cobrar", "Recibo", "Referencia", "Autorizacion","SOCIO","CONTADO"};
    private DefaultTableModel xFormasPago = new DefaultTableModel(null, encFormasPago){
        public boolean isCellEditable(int row, int col) {
            if(col==0 || col==1) return false;
            if(col==iCol && ((iFila!=filaCuponesViaje && iFila!=filaAeromexico) && row==iFila)) return true;
            return false;
        }
    };
    private RelojVisualVta thX=null;
    private Object[][] Boletos;
    private Object[][] BoletosRedondos;
    private Object[] encBoletos = {"Corrida-Hora", "#", "T", "Nombre Pasajero", "Origen-Destino", "Costo"};
    private DefaultTableModel xBoletos = new DefaultTableModel(null, encBoletos){
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    private JDlgSiNo DialogoSiNo;
    
    private SimpleDateFormat formatoFechaTXTC = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private boolean cerrar = false;
    private SimpleDateFormat formatoDebugFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
    private List<TmsTiposPasajeroTbl> listado;
    private boolean respondio = false;
    private boolean cancelado = false;
    private String referenciaRed="";
    private JDlgBProgresoLealtadReden jProgreso;
    private Vector insertadosRed = null;
    private JClsPinPadTBResponse jClsPinPadTBResponse;
    private List<ProductoCarrito> productos = new ArrayList<ProductoCarrito>();
    private float totalVentaProductos =0;
    
    public JDlgCobro(Object[][] pBoletos, SesionVenta pSesionVenta, String pDoctoRvn,
                               String pTransaccion, String pNombreEmpresa, Object[][] pBoletosRedondos, RelojVisualVta pThX) {
        this.thX = pThX;
        this.nombreEmpresa=pNombreEmpresa;
        this.transaccion=pTransaccion;
        this.Boletos = pBoletos;
        this.BoletosRedondos = pBoletosRedondos;
        this.sesionVenta = pSesionVenta;
        if(this.transaccion.equals("CN"))
            System.out.println("Importe cargo: "+this.sesionVenta.getImporteVenta());
        cajaNumeroTerminalPago();
        if(this.transaccion.equals(sesionVenta.ctxVENTA)) strViaje=msgRedondo();
        else if(this.transaccion.equals(sesionVenta.ctxVENTA_RESERVACION)) strViaje="SENCILLO POR CONFIRMACION DE RESERVACION";
             else if(this.transaccion.equals(sesionVenta.ctxCANJE_BA)) strViaje="SENCILLO POR CANJE DE BOLETO ABIERTO";
                  else if(this.transaccion.equals(sesionVenta.ctxVENTA_BA)) strViaje=msgAbierto();//"ABIERTO";
                       else if(this.transaccion.equals(sesionVenta.ctxVENTA_BR)) strViaje="REDONDO";
        if(this.transaccion.equals("CN"))
            strViaje = "COBRO DE CARGO POR CANCELACION DE BOLETOS";
        this.BA = null;
        this.doctoRvn = pDoctoRvn;
        this.unAviso = false;
        System.out.println(" desde JDlgCobro 1...");
//        listado = sesionVenta.getTiposPasajeLealtad();
//        for(int i=0; i<listado.size(); i++)
//            System.out.println("Lealtad("+listado.get(i).getRutaId()+")"+listado.get(i).getLealtad() +" Lunes: "+listado.get(i).getL_LUNES() +" Tipo: "+listado.get(i).getAplicaTipoLealtad()+" Aplica ("+listado.get(i).getLetraTipo()+") = "+listado.get(i).getAplicaLealtad());
       
        inicio();
        if(!this.sesionVenta.getUserCon().getAplicacionVenta())
        {
            cerrar = false;
            System.out.println("Entra a mostrar tarjeta 1");
            if(sesionVenta.getUserCon().getSysCobroBancario())
                sistemaCobroTarjetaBancaria();
        } 
        if(sesionVenta.isVentaJuconi() && this.sesionVenta.getUserCon().getAplicacionVenta() )
           AgregarProductos();
        
    }
    
    public JDlgCobro(Object[][] pBoletos,
                               SesionVenta pSesionVenta, Object[] pBA,
                               String pTransaccion, String pNombreEmpresa, RelojVisualVta pThX) {
        this.thX = pThX;
        this.nombreEmpresa=pNombreEmpresa;
        this.transaccion=pTransaccion;
        this.Boletos = pBoletos;
        this.sesionVenta = pSesionVenta;
        System.out.println("en Cobro sesionVenta.getImporteVenta(): "+sesionVenta.getImporteVenta());
        this.doctoRvn = pBA[4].toString();
        this.BA = pBA;
        cajaNumeroTerminalPago();
        if(this.transaccion.equals(sesionVenta.ctxVENTA)) strViaje=msgRedondo();
        else if(this.transaccion.equals(sesionVenta.ctxVENTA_RESERVACION)) strViaje="SENCILLO POR CONFIRMACION DE RESERVACION";
             else if(this.transaccion.equals(sesionVenta.ctxCANJE_BA)) strViaje="SENCILLO POR CANJE DE BOLETO ABIERTO";
                  else if(this.transaccion.equals(sesionVenta.ctxVENTA_BA)) strViaje=msgAbierto();//"ABIERTO";
                       else if(this.transaccion.equals(sesionVenta.ctxVENTA_BR)) strViaje="REDONDO";
        this.unAviso = false;
        System.out.println(" desde JDlgCobro 2...");
//        listado = sesionVenta.getTiposPasajeLealtad();
//        for(int i=0; i<listado.size(); i++)
//            System.out.println("Lealtad("+listado.get(i).getRutaId()+")"+listado.get(i).getLealtad() +" Lunes: "+listado.get(i).getL_LUNES() +" Tipo: "+listado.get(i).getAplicaTipoLealtad()+" Aplica ("+listado.get(i).getLetraTipo()+") = "+listado.get(i).getAplicaLealtad());
        inicio();
        if(!this.sesionVenta.getUserCon().getAplicacionVenta())
        {
            cerrar = false;
            System.out.println("Entra a mostrar tarjeta 2");
            if(sesionVenta.getUserCon().getSysCobroBancario())
                sistemaCobroTarjetaBancaria();
        }
    }
    
    private void centrarJDialog(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width-24, screenSize.height-66);
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, (( screenSize.height - frameSize.height ) / 2)+22);
    }
    
    private void inicio(){
        excluyeTeclasVtaActual=false;
        primeraVezF10 = false;
        primeraVezF12 = false;
        primeraVezENTER = false;
        ventaRegistrada = false;
        setVentaOk(1);
        try {
            jbInit();
            interfazColor();
            super.setUndecorated(true);
            super.setModal(true);
            super.setResizable(false);
            super.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            super.setFocusTraversalKeysEnabled(false);
            centrarJDialog();
            AnchoColumnasFormasPago();
            AnchoColumnasVtaActual();
            jTblTipoPagos.setFocusTraversalKeysEnabled(false);
            this.jTxtTotalVenta.setText("$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getImporteVenta()));
            this.jTxtRecibo.setText("$0.00");
            if(this.transaccion!=sesionVenta.ctxVENTA){
                if(this.transaccion==sesionVenta.ctxCANJE_BA){ this.xFormasPago.setValueAt(sesionVenta.getImporteVenta(),0,3);
                    this.jTxtRecibo.setText("$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getImporteVenta()));
                    this.xFormasPago.setValueAt(this.doctoRvn,0,4);
                    jTblTipoPagos.setColumnSelectionInterval(4,4);
                    campoDocto=true;
                }
            }
            else{
                if(xFormasPago.getValueAt(0,1).toString().equals("EFE")){ campoDocto=false; jTblTipoPagos.setColumnSelectionInterval(3,3); }
                else{ campoDocto=true; jTblTipoPagos.setColumnSelectionInterval(4,4); }
            }
            this.calcula_Cantidades();
            //System.out.println("sesionVenta.getUserCon().getMtoMinTjt() "+sesionVenta.getUserCon().getMtoMinTjt());
            //sesionVenta.getUserCon().setMtoMinTjt(Double.valueOf(sesionVenta.getValorParametro("P_MTOMINTJT", -1)));
            if(sesionVenta.getImporteVenta()<sesionVenta.getUserCon().getMtoMinTjt()) this.noAceptarPagoConTarjeta = false;
            else this.noAceptarPagoConTarjeta = true;
            String msgTjt = "";
            if(!noAceptarPagoConTarjeta)
                msgTjt="<font size=+1>.<br>El monto minimo a cobrar con tarjeta bancaria es de $"+sesionVenta.getUserCon().getMtoMinTjt()+"</font>";
            jLblTipoBoleto.setText("<html>Tipo de viaje: "+strViaje+msgTjt+"</html>");
            for(int i=0; i<jTblTipoPagos.getRowCount(); i++){
                if(jTblTipoPagos.getValueAt(i,1).toString().contains("BBV")){
                    filaBancomer=i;
                    break;
                }
            }

            /* 04/09/2013 VAGL inicia  el dialogo siempre para que ponga bien si es credito o no
            if(this.transaccion.equals("VR")){
                int mBS=0;
                for(int j=0; j<this.Boletos.length; j++)
                    if(this.Boletos[j][6].toString().equals("S")) mBS++;
                
                jDlgCupones = new JDlgCupones(mBS, this, sesionVenta);
            }
            else
                 jDlgCupones = new JDlgCupones(this.Boletos.length, this, sesionVenta);
            
            for(int i=0; i<jTblTipoPagos.getRowCount(); i++){
                if(jTblTipoPagos.getValueAt(i,1).toString().contains("VJS")){
                    filaCuponesViaje=i;
                    break;
                }
            }
            
            for(int i=0; i<jTblTipoPagos.getRowCount(); i++){
                if(jTblTipoPagos.getValueAt(i,1).toString().contains("AMX")){
                    filaAeromexico=i;
                    jDlgCupones.setColNoEditable(1);
                    break;
                }
            }
            */
            
            for(int i=0; i<jTblTipoPagos.getRowCount(); i++){
                if(jTblTipoPagos.getValueAt(i,1).toString().contains("AME")){
                    filaAmerican=i;
                    break;
                }
            }
            //
            jTblTipoPagos.setDefaultRenderer(Object.class, new AttributiveCellRenderer1());
            this.jTblTipoPagos.setRowHeight(25);
            this.jTblTipoPagos.setRowMargin(10);
            //this.jTblTipoPagos.setSelectionBackground(java.awt.Color.green);
            //rowsel = 0;
            primerPresion=true;
            jTblTipoPagos.setFont(ColoresInterfaz.fuente4);
            jTblTipoPagos.setRowSelectionInterval(0,0);
            int i,j;
            for(i=0; i<jTblTipoPagos.getRowCount(); i++){
                for(j=2; j<4; j++)
                    jTblTipoPagos.setValueAt("0.00", i, j);
            }
            impresoraErronea=false;
            jTblTipoPagos.setValueAt(sesionVenta.getImporteVenta(),0,2);
            jTblTipoPagos.setRowSelectionInterval(0,0);
            jTblTipoPagos.setColumnSelectionInterval(3,3);
            iFila=0; iCol=3;
            jTblTipoPagos.requestFocus();
            jTbl_Boletos.setFont(ColoresInterfaz.fuente5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setLayout(gridBagLayout0);
        Contenedor.setLayout(gridBagLayout1);
        jPnlTipoPagos.setLayout(gridBagLayout3);
        if(this.transaccion==sesionVenta.ctxCANJE_BA)  FormasPago=sesionVenta.getFormaPagoBA();
        else FormasPago=sesionVenta.getFormasPago_BA();
        for(int i=0; i<FormasPago.length; i++){
            FormasPago[i][4]=""; FormasPago[i][5]="";
        }
        xFormasPago.setDataVector(FormasPago, encFormasPago);
        jTblTipoPagos.setModel(xFormasPago);
        jTblTipoPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTblTipoPagos.setRowSelectionAllowed(false);
        jTblTipoPagos.setColumnSelectionAllowed(false);
        jTblTipoPagos.setCellSelectionEnabled(true);
        jTblTipoPagos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTblTipoPagosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTblTipoPagosFocusLost(evt);
            }
        });
        jTblTipoPagos.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        jTblTipoPagos_keyPressed(e);
                    }
                });
                
        jTblTipoPagos.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        jTblTipoPagos_keyReleased(e);
                    }
                });
                               
        jScrTipoPagos.getViewport().add(jTblTipoPagos, null);
               
        jPnlTipoPagos.add(jScrTipoPagos, 
                          new GridBagConstraints(0, 0, 1, 1, 0.5, 1.0, 
                                                 GridBagConstraints.CENTER, 
                                                 GridBagConstraints.BOTH, 
                                                 new Insets(0, 0, 0, 5), 0, 
                                                 0));
        Contenedor.add(jPnlTipoPagos, 
                 new GridBagConstraints(0, 0, 2, 1, 1.0, 0.5, GridBagConstraints.CENTER, 
                                        GridBagConstraints.BOTH, 
                                        new Insets(5, 5, 0, 5), 0, 0));
        /******************/
        jLblTipoBoleto.setHorizontalAlignment(JLabel.CENTER);
        jLblTipoBoleto.setVerticalAlignment(JLabel.CENTER);
        jLblTipoBoleto.setSize(new Dimension(100, 25));
        jLblTipoBoleto.setForeground(ColoresInterfaz.cTexto1);
        jLblTipoBoleto.setFont(ColoresInterfaz.fuente4);
        
        tipo_Boletos.setBorder(BorderFactory.createTitledBorder(null,"Informacion Importante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, ColoresInterfaz.fuente0, java.awt.Color.white));
        tipo_Boletos.setLayout(gridBagLayout5);
        
        tipo_Boletos.add(jLblTipoBoleto, 
                            new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 
                                                   GridBagConstraints.CENTER, 
                                                   GridBagConstraints.HORIZONTAL, 
                                                   new Insets(0, 0, 0, 0), 0, 
                                                   0));

        Contenedor.add(tipo_Boletos, 
                            new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, 
                                        GridBagConstraints.BOTH, 
                                        new Insets(0, 5, 5, 0), 0, 0));

        detalle_Boletos.setBorder(BorderFactory.createTitledBorder(null,"Detalle de Boletos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, ColoresInterfaz.fuente0, java.awt.Color.white));
        detalle_Boletos.setLayout(gridBagLayout2);
        detalle_Boletos.setFocusable(false);

        jScrDetalleBoletos.setFocusable(false);
        if(!this.transaccion.equals("CN"))
        {
            if(BoletosRedondos==null) xBoletos.setDataVector(Boletos, encBoletos);
            else xBoletos.setDataVector(BoletosRedondos, encBoletos);
        }
        jTbl_Boletos.setModel(xBoletos);
        
        jTbl_Boletos.setFocusable(false);
        jScrDetalleBoletos.getViewport().add(jTbl_Boletos, null);
        detalle_Boletos.add(jScrDetalleBoletos, 
                            new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 
                                                   GridBagConstraints.CENTER, 
                                                   GridBagConstraints.BOTH, 
                                                   new Insets(0, 0, 5, 5), 0, 
                                                   0));
 
        Contenedor.add(detalle_Boletos, 
                 new GridBagConstraints(0, 2, 1, 1, 1.0, 0.5, GridBagConstraints.CENTER, 
                                        GridBagConstraints.BOTH, 
                                        new Insets(0, 0, 0, 0), 0, 0));

        JPanelTotales.setSize(new Dimension(320, 250));
        JPanelTotales.setLayout(gridBagLayout4);
        JPanelTotales.setBorder(BorderFactory.createTitledBorder(null, "TOTALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, ColoresInterfaz.fuente0, java.awt.Color.white));
        jTxtTotalVenta.setSize(new Dimension(222, 20));
        jTxtTotalVenta.setPreferredSize(new Dimension(100, 20));
        jTxtTotalVenta.setForeground(Color.BLACK);
        jTxtTotalVenta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jTxtTotalVenta.setFont(ColoresInterfaz.fuente4);
        jTxtTotalVenta.setFocusable(false);
        jLblTotal.setText("Venta:");
        jLblTotal.setSize(new Dimension(100, 25));
        jLblTotal.setFont(ColoresInterfaz.fuente4);
        jTxtRestan.setForeground(Color.BLACK);
        jTxtRestan.setSize(new Dimension(222, 20));
        jTxtRestan.setPreferredSize(new Dimension(100, 20));
        jTxtRestan.setFont(ColoresInterfaz.fuente4);
        jTxtRestan.setFocusable(false);
        jTxtRestan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLblRestan.setText("Restan:");
        jLblRestan.setSize(new Dimension(100, 25));
        jLblRestan.setFont(ColoresInterfaz.fuente4);
        jTxtCambio.setForeground(Color.BLACK);
        jTxtCambio.setSize(new Dimension(222, 20));
        jTxtCambio.setPreferredSize(new Dimension(100, 20));
        jTxtCambio.setFont(ColoresInterfaz.fuente4);
        jTxtCambio.setFocusable(false);
        jTxtCambio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLblCambio.setText("Cambio:");
        jLblCambio.setSize(new Dimension(100, 25));
        jLblCambio.setFont(ColoresInterfaz.fuente4);
        jTxtRecibo.setForeground(Color.BLACK);
        jTxtRecibo.setSize(new Dimension(222, 20));
        jTxtRecibo.setPreferredSize(new Dimension(100, 20));
        jTxtRecibo.setFont(ColoresInterfaz.fuente4);
        jTxtRecibo.setFocusable(false);
        jLblRecibo.setText("Recibo:");
        jLblRecibo.setSize(new Dimension(100, 25));
        jLblRecibo.setFont(ColoresInterfaz.fuente4);
        jTxtRecibo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JPanelTotales.add(jLblRecibo, 
                          new GridBagConstraints(0, 0, 1, 1, 0.0, 0.2, 
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.NONE, 
                                                 new Insets(0, 0, 0, 0), 0, 
                                                 0));
        JPanelTotales.add(jTxtRecibo, 
                          new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, 
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.HORIZONTAL, 
                                                 new Insets(0, 5, 0, 5), 0, 
                                                 10));
        JPanelTotales.add(jLblCambio, 
                          new GridBagConstraints(0, 1, 1, 1, 0.0, 0.2, 
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.NONE, 
                                                 new Insets(0, 0, 0, 0), 0, 
                                                 0));
        JPanelTotales.add(jTxtCambio, 
                          new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, 
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.HORIZONTAL, 
                                                 new Insets(0, 5, 0, 5), 0, 
                                                 10));
        JPanelTotales.add(jLblRestan, 
                          new GridBagConstraints(0, 2, 1, 1, 0.0, 0.2, 
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.NONE, 
                                                 new Insets(0, 0, 0, 0), 0, 
                                                 0));
        JPanelTotales.add(jTxtRestan,
                          new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0,
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.HORIZONTAL, 
                                                 new Insets(0, 5, 0, 5), 0, 
                                                 10));
        JPanelTotales.add(jLblTotal, 
                          new GridBagConstraints(0, 3, 1, 1, 0.0, 0.2, 
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.NONE, 
                                                 new Insets(0, 0, 0, 0), 0, 
                                                 0));
        JPanelTotales.add(jTxtTotalVenta, 
                          new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, 
                                                 GridBagConstraints.WEST, 
                                                 GridBagConstraints.HORIZONTAL, 
                                                 new Insets(0, 5, 0, 5), 0, 
                                                 10));

        Contenedor.add(JPanelTotales, 
                 new GridBagConstraints(1, 1, 1, 2, 0.25, 0.5, GridBagConstraints.CENTER, 
                                        GridBagConstraints.BOTH, 
                                        new Insets(0, 0, 5, 5), 0, 0));
        barraMensaje.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Contenedor.add(barraMensaje, 
                 new GridBagConstraints(0, 4, 2, 1, 1.0, 0.0, GridBagConstraints.CENTER, 
                                        GridBagConstraints.BOTH, 
                                        new Insets(0, 0, 0, 0), 0, 0));
        
        if(sesionVenta.getUserCon().getSysCobroBancario()) barraMensaje.setText(this.jLblMensajes.getMensajeVta(62));
        else barraMensaje.setText(this.jLblMensajes.getMensajeVta(6));
        
        Titulo.setOpaque(false);
        Titulo.setForeground(ColoresInterfaz.cFondoVentana);
        this.add(Titulo, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 
                                                 GridBagConstraints.NORTH, 
                                                 GridBagConstraints.HORIZONTAL, 
                                                 new Insets(0, 0, 0, 0), 0, 
                                                 0));
        this.add(Contenedor, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 
                                                 GridBagConstraints.SOUTH, 
                                                 GridBagConstraints.BOTH, 
                                                 new Insets(0, 5, 0, 5), 0, 
                                                 10));
        
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.setRequestFocusEnabled(false);
        
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(36, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_END, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), JComponent.WHEN_FOCUSED);
        this.jTblTipoPagos.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0), JComponent.WHEN_FOCUSED);
    }
    
    private void AnchoColumnasFormasPago(){
        TableColumn column = null;
        int anchoContenedor = (int)(screenSize.getWidth()-24);//jScrTipoPagos.getWidth();
        for (int i = 0; i < 8; i++) {
            column = jTblTipoPagos.getColumnModel().getColumn(i);
            switch(i){
                //"Forma de Pago", "Clave", "Total a Cobrar", "Recibo", "Referencia", "Autorizacion","SOCIO","CONTADO"
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.26)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.06)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.13)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.21)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.21)); break;
                case 6:
                case 7:
                    column.setPreferredWidth(0);
                        column.setMinWidth(0);
                        column.setMaxWidth(0);
                        column.setWidth(0);
                        break;
            }
            column.setResizable(false);
        }
        TableColumn colTipoPasajero = jTblTipoPagos.getColumnModel().getColumn(3);
        colTipoPasajero.setCellEditor(new DefaultCellEditor(jDblRecibo));
        TableColumn colNombre = jTblTipoPagos.getColumnModel().getColumn(4);
        colNombre.setCellEditor(new DefaultCellEditor(jtxtDocumento));
    }
    
    private void AnchoColumnasVtaActual(){
        TableColumn column = null;
        int anchoContenedor = (int)(screenSize.getWidth()-24);
        anchoContenedor = (int)(anchoContenedor-(anchoContenedor*0.25));
        //jScrDetalleBoletos.getWidth();
        //"Corrida-Hora", "#", "T", "Nombre Pasajero", "Origen-Destino", "Costo"
        for (int i = 0; i < 6; i++) {
            column = jTbl_Boletos.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.27)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.05)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.34)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
                case 5: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
            }
            column.setResizable(false);
        }
    }

    private void calcula_Cantidades(){
        double Total = Double.parseDouble(this.jTxtTotalVenta.getText().replace("$"," ").replace(",",""));
        double Recibo = Double.parseDouble(this.jTxtRecibo.getText().replace("$"," ").replace(",",""));
        System.out.println("Recibo la cantidad de: "+this.jTxtRecibo.getText());
        double Cambio,Restan;
        Restan=0.0;
        Cambio=0.0;
        if (Recibo >= Total){
            Cambio=Recibo-Total;
        }else if(Recibo < Total){
            Restan=Total-Recibo;
        }   
        this.jTxtCambio.setText("$"+sesionVenta.customFormat("##,##0.00",Cambio));
        this.jTxtRestan.setText("$"+sesionVenta.customFormat("##,##0.00",Restan));
    }
  
    private void jTblTipoPagos_keyReleased(KeyEvent e) {
        int rowsel = jTblTipoPagos.getSelectedRow();
        int colsel = jTblTipoPagos.getSelectedColumn();
        int ultimo=jTblTipoPagos.getRowCount()-1;
        int rowsize= jTblTipoPagos.getRowCount();
        if (rowsize > 0) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_0: case KeyEvent.VK_1: case KeyEvent.VK_2: case KeyEvent.VK_3: case KeyEvent.VK_4:
                case KeyEvent.VK_5: case KeyEvent.VK_6: case KeyEvent.VK_7: case KeyEvent.VK_8: case KeyEvent.VK_9:
                case KeyEvent.VK_NUMPAD0: case KeyEvent.VK_NUMPAD1: case KeyEvent.VK_NUMPAD2: case KeyEvent.VK_NUMPAD3: case KeyEvent.VK_NUMPAD4:
                case KeyEvent.VK_NUMPAD5: case KeyEvent.VK_NUMPAD6: case KeyEvent.VK_NUMPAD7: case KeyEvent.VK_NUMPAD8: case KeyEvent.VK_NUMPAD9:
                case KeyEvent.VK_PERIOD:
                        switch(colsel){
                            case 3:
                                if(rowsel==0 && entrada){
                                    if(!jDblRecibo.getText().equals("0.00")){
                                        jDblRecibo.setText(""+e.getKeyChar());
                                        entrada=false;
                                        //digitos=1;    
                                    }
                                }
                                /*else{
                                    if(rowsel==0 && !entrada){
                                        digitos++;
                                        if(digitos>8){
                                            jTblTipoPagos.setValueAt("",rowsel,jTblTipoPagos.getSelectedColumn());
                                            entrada=true;
                                            digitos=0;
                                        }
                                    }
                                }*/
                            break;
                            case 4:
                                if(rowsel!=filaBancomer) return;
                                if(!noAceptarPagoConTarjeta){
                                    this.getToolkit().beep();
                                    DialogoAceptar.mostrarDialogo("¡Cobro con tarjeta bancaria no permitido!",
                                            "El monto minimo a cobrar es de $<<"+sesionVenta.getUserCon().getMtoMinTjt()+">>",Color.RED);
                                    this.jTblTipoPagos.setRowSelectionInterval(filaBancomer,filaBancomer);
                                    this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                                    this.jTblTipoPagos.setValueAt("",filaBancomer,4);
                                    this.jTblTipoPagos.requestFocus();
                                    return;
                                }
                            break;
                        }
                    break;
                    case KeyEvent.VK_UP:
                    primerPresion = true;
                    this.unAviso = false;
                    entrada=true;
                    if(rowsel>-1){
                        if(Math.abs(rowsel-filaSel)>1){
                            this.jTblTipoPagos.setRowSelectionInterval(filaSel, filaSel);
                            rowsel = filaSel;
                            return;
                        }
                        iFila=rowsel; iCol=jTblTipoPagos.getSelectedColumn();
                        if(rowsel == filaBancomer && colsel == 4){ 
                            if(!sesionVenta.getUserCon().getDigitaTB()){
                                jtxtDocumento.setForeground(SELECCION); jtxtDocumento.setBackground(SELECCION);
                            }
                        }
                        else jtxtDocumento.setForeground(Color.BLACK);
                        if(rowsel==0) iCol=3;
                        //if(!xFormasPago.getValueAt(rowsel,2).toString().equals("0.00")) return;
                        if(xFormasPago.getValueAt(rowsel+1,2).equals("0.00")) return;
                        xFormasPago.setValueAt(xFormasPago.getValueAt(rowsel+1,2),rowsel,2);
                        if(rowsel!=0) {
                            xFormasPago.setValueAt(xFormasPago.getValueAt(rowsel,2),rowsel,3);
                            xFormasPago.setValueAt(doctoRvn,rowsel,4);
                        }
                        xFormasPago.setValueAt("0.00",rowsel+1,2);
                        xFormasPago.setValueAt("0.00",rowsel+1,3);
                        xFormasPago.setValueAt("",rowsel+1,4);
                        xFormasPago.setValueAt("",rowsel+1,5);
                        xFormasPago.setValueAt("",rowsel,4);
                        xFormasPago.setValueAt("",rowsel,5);
                        
                        jTxtRecibo.setText("$"+sesionVenta.customFormat("##,##0.00",Double.parseDouble((xFormasPago.getValueAt(rowsel,3)==null || xFormasPago.getValueAt(rowsel,3).equals("")?"0.00":xFormasPago.getValueAt(rowsel,3).toString()))));
                        calcula_Cantidades();
                        if(xFormasPago.getValueAt(rowsel,1).toString().equals("EFE")){
                            this.jTblTipoPagos.setColumnSelectionInterval(3,3);
                        }
                        else{
                            this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                        }    
                        this.jTblTipoPagos.requestFocus();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    primerPresion = true;
                    this.unAviso = false;
                    entrada=true;
                    if(rowsel<rowsize){
                        if(Math.abs(rowsel-filaSel)>1){
                            this.jTblTipoPagos.setRowSelectionInterval(filaSel, filaSel);
                            rowsel = filaSel;
                            return;
                        }
                        iFila=rowsel; iCol=jTblTipoPagos.getSelectedColumn();
                        if(rowsel==1) iCol=4;
                        if(rowsel == filaBancomer && colsel == 4){
                            if(!sesionVenta.getUserCon().getDigitaTB()){
                                jtxtDocumento.setForeground(SELECCION); jtxtDocumento.setBackground(SELECCION);
                            }
                        }
                        else jtxtDocumento.setForeground(Color.BLACK);
                        //if(!xFormasPago.getValueAt(rowsel,2).toString().equals("0.00")) return;
                        if(xFormasPago.getValueAt(rowsel-1,2).equals("0.00")) return;
                        xFormasPago.setValueAt(xFormasPago.getValueAt(rowsel-1,2),rowsel,2);
                        if(rowsel!=0) {
                            xFormasPago.setValueAt(xFormasPago.getValueAt(rowsel,2),rowsel,3);
                            xFormasPago.setValueAt(doctoRvn,rowsel,4);
                        }
                        
                        xFormasPago.setValueAt("0.00",rowsel-1,2);
                        xFormasPago.setValueAt("0.00",rowsel-1,3);
                        xFormasPago.setValueAt("",rowsel-1,4);
                        xFormasPago.setValueAt("",rowsel-1,5);
                        xFormasPago.setValueAt("",rowsel,4);
                        xFormasPago.setValueAt("",rowsel,5);
                        
                        jTxtRecibo.setText("$"+sesionVenta.customFormat("##,##0.00",Double.parseDouble((xFormasPago.getValueAt(rowsel,3)==null || xFormasPago.getValueAt(rowsel,3).equals("")?"0.00":xFormasPago.getValueAt(rowsel,3).toString()))));
                        calcula_Cantidades();
                        if(xFormasPago.getValueAt(rowsel,1).toString().equals("EFE")){
                            this.jTblTipoPagos.setColumnSelectionInterval(3,3);
                        }
                        else{
                            this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                        }
                        this.jTblTipoPagos.requestFocus();
                    }
                    break;
                    case KeyEvent.VK_ENTER:
                        System.out.println("la salidaImpresion es : "+salidaImpresion);
                        primerPresion=true;
                        if(!ValidaSesion()) return;
                        if(Boletos[0][11]!=null && !this.transaccion.equals("CN")){
                            String liga = ((sesionVenta.isBolRedCer())?sesionVenta.getDblinkRedIda():(sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : ""));
                            if(!sesionVenta.existeCorrida(Boletos[0][11].toString(), liga)){
                                this.getToolkit().beep();
                                //DialogoAceptar.mostrarDialogo("¡Imposible vender boleto!","La corrida no existe.",Color.RED);
                                this.setVentaOk(1);
                                this.dispose();
                                DialogoAceptar.mostrarDialogo("¡Imposible vender boleto(1)!","La corrida no existe.",Color.RED);
                                return;
                            }
                        }
                        System.out.println("Paso de : if(Boletos[0][11]!=null)");
                        if(colsel!=3 && colsel!=4){
                            this.jTblTipoPagos.setColumnSelectionInterval(columnaSel, columnaSel);
                            this.jTblTipoPagos.setRowSelectionInterval(filaSel,filaSel);
                            this.jTblTipoPagos.requestFocus();
                            return;
                        }
                        System.out.println("Paso de : if(colsel!=3 && colsel!=4){");
                        entrada=true;
                        if(colsel==3){
                            if(jDblRecibo.getText().equals("")) jTxtRecibo.setText("$0.00");
                            else jTxtRecibo.setText("$"+sesionVenta.customFormat("##,##0.00",Double.parseDouble(jDblRecibo.getText())));
                            calcula_Cantidades();
                            jTblTipoPagos.setRowSelectionInterval(0,0);
                            jTblTipoPagos.setColumnSelectionInterval(3,3);
                            return;
                        }
                        System.out.println("Paso de : if(colsel==3)");
                        if(colsel==4){
                            System.out.println("entro a: if(colsel==4)");
                            rowsel--; //rowselAnt--;
                            jTblTipoPagos.setRowSelectionInterval(rowsel,rowsel);
                            //valorlectura=xFormasPago.getValueAt(rowsel, jTblTipoPagos.getSelectedColumn()).toString();
                            //System.out.println("VALOR LECTURA::: "+valorlectura+" ROWSEL "+rowsel);
                            if(filaBancomer!=rowsel){
                                if(valorlectura.indexOf("%")>=0 && valorlectura.indexOf("&")>=0 && valorlectura.indexOf("_")>=0 && valorlectura.length()>20){
                                    // cuando la Tarjeta es deslizada...
                                    jTblTipoPagos.setValueAt("",rowsel,jTblTipoPagos.getSelectedColumn());
                                    jTblTipoPagos.setColumnSelectionInterval(4,4);
                                    return;
                                }
                                else{
                                    if(valorlectura.indexOf("_")>=0){
                                        // cuando la Tarjeta es digitada...
                                        jTblTipoPagos.setValueAt("",rowsel,jTblTipoPagos.getSelectedColumn());
                                        jTblTipoPagos.setColumnSelectionInterval(4,4);
                                        return;
                                    }
                                }
                                jTblTipoPagos.setColumnSelectionInterval(4,4);
                                return;
                            }
                            jTblTipoPagos.setValueAt("",rowsel,jTblTipoPagos.getSelectedColumn());
                            jTblTipoPagos.setColumnSelectionInterval(4,4);
                            if(!this.unAviso){
                                if(impresoraErronea){
                                    impresoraErronea=false;
                                    return;
                                }
                                if(!sesionVenta.ValidaFuncionUsuario("5015")){
                                    jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5015", "Cobrar con Tarjeta de Credito");
                                    dlg.setVisible(true);
                                    if(!dlg.getRespuesta()) return;
                                    this.unAviso=true;
                                }
                            }
                            
                            if(!aunExisteReservacion()){
                                this.getToolkit().beep();
                                //DialogoAceptar.mostrarDialogo("¡Venta reservacion!","No es posible vender esta reservación.",Color.RED);
                                this.setVentaOk(1);
                                this.dispose();
                                DialogoAceptar.mostrarDialogo("¡Venta reservacion!","No es posible vender esta reservación.",Color.RED);
                                return;
                            }
                            
                            if(!sesionVenta.getUserCon().getSysCobroBancario())
                                cobroTarjetaBancaria_Bancomer();
                        }
                    break;
                    //************* Invoca a laPantalla para Agregar Productos ***************/
                case KeyEvent.VK_F3:
                    if(e.isControlDown()) AgregarProductos();
                    break;
            }
        }
    }

    private void AgregarProductos(){
        System.out.println("Boletos,length: "+Boletos.length);
        for(int i=0;i<36; i++)
            System.out.println("Boletos[0]["+i+"]: "+Boletos[0][i]);
            String tipoBoleto ="N";
            if(Boletos[0][29] != null && Boletos[0][29].toString().equals("F"))
                tipoBoleto = "F";
                JdlgVentaCarritoProductosER dialog2 = new JdlgVentaCarritoProductosER(new javax.swing.JFrame(), true,sesionVenta.getDatosInicales(),sesionVenta.getUserCon().getTerminalNombre(),sesionVenta.getUserCon().getCaja(),sesionVenta.getUserCon().getTerminalId(),sesionVenta.getUserCon().getCorteId(),sesionVenta.obtenerFolioActual(Boletos[0][7].toString()),productos,(float)sesionVenta.getImporteVenta(), tipoBoleto,sesionVenta.isVentaJuconi());
                dialog2.centrarDialogo();
                dialog2.setVisible(true);
                if(dialog2.isVenta())
                {
                    System.out.println("---------------------------------------------------------");
                    totalVentaProductos = dialog2.getTotalProductorAgregados();
                   System.out.println("Si hubo Venta de Productos por: "+totalVentaProductos);
                   productos = dialog2.getProductosAgregados() ;
                   sesionVenta.setProductosCarrito(productos);
                    for(ProductoCarrito product:productos)
                    {
                            System.out.println("****************** 1 ***********************");
                            System.out.println(" Folio: "+product.getFolio());
                            System.out.println(" ProductoId: "+product.getProductoId());
                            System.out.println(" Clave: "+product.getClave_producto());
                            System.out.println(" Nombre: "+product.getProducto());
                            System.out.println(" TiposPago: "+product.getTiposPagoPer());
                            System.out.println(" Cantidad: "+product.getCantidad());
                            System.out.println(" Total: "+product.getTotal());
                    }
                    xFormasPago.setValueAt(""+(sesionVenta.getImporteVenta()+totalVentaProductos),jTblTipoPagos.getSelectedRow() ,2);
                    this.jTxtTotalVenta.setText("$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getImporteVenta()+totalVentaProductos));
                    calcula_Cantidades();
                    jTblTipoPagos.requestFocusInWindow();
                }
                else
                {
                    System.out.println("No hubo Venta de Productos");
                    productos = new ArrayList<ProductoCarrito>();
                    sesionVenta.setProductosCarrito(productos);
                    totalVentaProductos=0;
                    xFormasPago.setValueAt(""+(sesionVenta.getImporteVenta()+totalVentaProductos),jTblTipoPagos.getSelectedRow() ,2);
                    this.jTxtTotalVenta.setText("$"+sesionVenta.customFormat("##,##0.00",sesionVenta.getImporteVenta()+totalVentaProductos));
                    calcula_Cantidades();
                }
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
    
    private void cajaNumeroTerminalPago(){
        cajaNumero=sesionVenta.getUserCon().getCajaNumero();
    }
        
    private void jTblTipoPagos_keyPressed(KeyEvent e) {
        int rowsel = jTblTipoPagos.getSelectedRow();
        int colsel = jTblTipoPagos.getSelectedColumn();
        int ultimo=jTblTipoPagos.getRowCount()-1;
        int rowsize= jTblTipoPagos.getRowCount();
        String mensajeCupon;
        if (rowsize > 0) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_F7:
                    /*---- 04/09/2013 VAGL inicia  el dialogo siempre para que ponga bien si es credito o no */
                    if(this.transaccion.equals("VR")){
                        int mBS=0;
                        double mBStmp=0;
                        for(int j=0; j<this.Boletos.length; j++)
                        {
                            System.out.println("Cuando es reservacion checa que tipo de boleto es(this.Boletos["+j+"][6]): "+this.Boletos[j][6]);
                            if(this.Boletos[j][6].toString().equals("S")) mBStmp++;
                            if(this.Boletos[j][6].toString().equals("R")) mBStmp=mBStmp+(0.5);
                        }
                        System.out.println("mBStmp: "+mBStmp);
                        mBS = (int) mBStmp;
                        System.out.println("mBS: "+mBS);
                        jDlgCupones = new JDlgCupones(mBS, this, sesionVenta);
                    }
                    else
                         jDlgCupones = new JDlgCupones(this.Boletos.length, this, sesionVenta);

                    for(int i=0; i<jTblTipoPagos.getRowCount(); i++){
                        if(jTblTipoPagos.getValueAt(i,1).toString().contains("VJS")){
                            filaCuponesViaje=i;
                            break;
                        }
                    }

                    for(int i=0; i<jTblTipoPagos.getRowCount(); i++){
                        if(jTblTipoPagos.getValueAt(i,1).toString().contains("AMX")){
                            filaAeromexico=i;
                            jDlgCupones.setColNoEditable(1);
                            break;
                        }
                    }
                    /*----*/
                    if(xFormasPago.getValueAt(rowsel,1).toString().equals("AMX"))
                        jDlgCupones.setColNoEditable(-1);
                    else jDlgCupones.setColNoEditable(1);

                    if(xFormasPago.getValueAt(rowsel,7).toString().equals("N")){
                        jDlgCupones.setFormaPago(xFormasPago.getValueAt(rowsel,1).toString());
                        jDlgCupones.setVisible(true);
                        mensajeCupon = jDlgCupones.getMsgCupones();
                        Object[][] xCupon = jDlgCupones.getFoliosCupones();
                        this.sesionVenta.setClienteCreId(new BigDecimal(jDlgCupones.getClienteId()));
                        System.out.println("El cliente Id es: "+this.sesionVenta.getClienteCreId());
                        System.out.println("xCupon.length: "+xCupon.length);
                        //jTblTipoPagos.setValueAt(mensajeCupon,filaCuponesViaje, 4);
                        jTblTipoPagos.setValueAt(mensajeCupon,rowsel, 4);
                        CuponesViaje = new Object[xCupon.length][3];
                        int count = 0;
                        for(int h=0; h<xCupon.length; h++){
                            CuponesViaje[h][0] = xCupon[h][0];
                            CuponesViaje[h][1] = xCupon[h][1];
                            CuponesViaje[h][2] = Boletos[count][6];
                            if(Boletos[count][6].equals("R"))
                               count++;
                            count++;
                        }

                        return;
                    }
                    /*
                    if(xFormasPago.getValueAt(rowsel,1).toString().equals("VJS")){
                        jDlgCupones.setFormaPago("VJS");
                        jDlgCupones.setVisible(true);
                        mensajeCupon = jDlgCupones.getMsgCupones();
                        Object[][] xCupon = jDlgCupones.getFoliosCupones();
                        this.sesionVenta.setClienteCreId(new BigDecimal(jDlgCupones.getClienteId()));
                        System.out.println("El cliente Id es: "+this.sesionVenta.getClienteCreId());
                        jTblTipoPagos.setValueAt(mensajeCupon,filaCuponesViaje, 4);
                        CuponesViaje = new Object[xCupon.length][3];
                        for(int h=0; h<xCupon.length; h++){
                            CuponesViaje[h][0] = xCupon[h][0];
                            CuponesViaje[h][1] = xCupon[h][1];
                            CuponesViaje[h][2] = Boletos[h][6];
                        }

                        return;
                    }
                    if(xFormasPago.getValueAt(rowsel,1).toString().equals("AMX")){
                        jDlgCupones.setFormaPago("AMX");
                        jDlgCupones.setVisible(true);
                        mensajeCupon = jDlgCupones.getMsgCupones();
                        Object[][] xCupon = jDlgCupones.getFoliosCupones();
                        jTblTipoPagos.setValueAt(mensajeCupon,filaAeromexico, 4);
                        CuponesViaje = new Object[xCupon.length][3];
                        for(int h=0; h<xCupon.length; h++){
                            CuponesViaje[h][0] = xCupon[h][0];
                            CuponesViaje[h][1] = xCupon[h][1];
                            CuponesViaje[h][2] = Boletos[h][6];
                        }
                    }
                    */
                    break;
                case KeyEvent.VK_F5:
                    boolean pagar= true;
                    for (ProductoCarrito p:productos)
                    {
                        if(p.getTiposPagoPer().indexOf(",BBV,")==-1)
                        {
                            pagar= false;
                            DialogoAceptar.mostrarDialogo("Venta de Productos ER.","<html>El Producto "+p.getProducto()+" no puede ser cobrado con Tarjeta de Credito.<br>Favor de quitar el producto o intente con otra forma de pago</html>", Color.RED);
                        }
                    }
                    if(!pagar) return;
                    if(sesionVenta.getUserCon().getSysCobroBancario())
                        sistemaCobroTarjetaBancaria();
                    break;
                case KeyEvent.VK_F10:
                    if(!excluyeTeclasVtaActual) excluyeTeclasVtaActual=true;
                    else return;
                    if(primeraVezF10){
                        break;
                    }
                    primeraVezF10 = true;
                    this.unAviso=false;
                    if(!ValidaSesion()){
                        primeraVezF10 = false;
                        return;
                    }
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"1.- Entra a F10");
//                    ////////////
//                    JDlgAcumulaEstrellas acumula = new  JDlgAcumulaEstrellas(this, true, this.Boletos, this.sesionVenta);
//                    acumula.setVisible(true);
//                    acumula.setLocationRelativeTo(null);
//
//                    if(!acumula.isCompletado()){
//                        primeraVezF10 = false;
//                        return;
//                    }
//                    ////////////

                    /*04/09/2013 VAGL Hace la validacion de Referencias de VJS antes que nada...*/
                    if(!xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("EFE") && !xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("SOC"))
                    {
                        vReferenciaPago=xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),4).toString();
                        System.out.println("Referencia: "+(vReferenciaPago==null? "null":(vReferenciaPago.equals("")?"nada":vReferenciaPago)));
                        if(vReferenciaPago==null || vReferenciaPago.equals("")){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("TMS Venta.","Falta la referencia del documento.", Color.RED);
                            //this.jTblTipoPagos.setRowSelectionInterval(this.jTblTipoPagos.getSelectedRow(),this.jTblTipoPagos.getSelectedRow());
                            primeraVezF10 = false;
                            this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                            this.jTblTipoPagos.requestFocus();
                            return;
                        }
                        if(vReferenciaPago.length()<6){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("TMS Venta.","<html>La referencia del documento debe contener<br>al menos 6 caracteres y/o digitos.</html>", Color.RED);
                            //this.jTblTipoPagos.setRowSelectionInterval(this.jTblTipoPagos.getSelectedRow(),this.jTblTipoPagos.getSelectedRow());
                            primeraVezF10 = false;
                            this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                            this.jTblTipoPagos.requestFocus();
                            return;
                        }
                    }
                    /**************************/
                    /************* Valida el Tipo de Pago de los productos **************/
                        boolean pagar2= true;
                        for (ProductoCarrito p:productos)
                        {
                            if(p.getTiposPagoPer().indexOf(","+xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString()+",")==-1)
                             {
                                pagar2= false;
                                DialogoAceptar.mostrarDialogo("Venta de Productos ER.","<html>El Producto "+p.getProducto()+" no puede ser cobrado con "+xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),0).toString()+".<br>Favor de quitar el producto o intente con otra forma de pago</html>", Color.RED);
                            }
                        }
                        if(!pagar2){primeraVezF10 = false; return;}

                    /********************************************************************/

                    /*********** Valida el tipo de pago para cobro de cargo **********/

                        if(sesionVenta.isCobroCargo() && !xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("EFE"))
                             {
                                pagar2= false;
                                DialogoAceptar.mostrarDialogo("Cobro de cargo por Cambio de Horario.","<html>El cargo no puede ser cobrado con "+xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),0).toString()+".<br>Por favor intente con otra forma de pago</html>", Color.RED);
                                primeraVezF10 = false; return;
                            }
                        
                     /**************************************/   
                        
                    if(Boletos[0][11]!=null && !this.transaccion.equals("CN")){
                        //String liga = (sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : "");
                        String liga = ((sesionVenta.isBolRedCer())?sesionVenta.getDblinkRedIda():(sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : ""));
                        if(!sesionVenta.existeCorrida(Boletos[0][11].toString(), liga)){
                            this.getToolkit().beep();
                            //DialogoAceptar.mostrarDialogo("¡Imposible vender boleto!","La corrida no existe.",Color.RED);
                            primeraVezF10 = false;
                            this.setVentaOk(1);
                            this.dispose();
                            DialogoAceptar.mostrarDialogo("¡Imposible vender boleto(2)!","La corrida no existe.",Color.RED);
                            return;
                        }
                    }
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"2.- Despues de checar corrida abierta");
                    if(filaBancomer==rowsel) return;
                    if(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("VJS")){
                        if(!sesionVenta.ValidaFuncionUsuario("5012")){
                            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5012", "Canjear Cupon de Agencia");
                            dlg.setVisible(true);
                            if(!dlg.getRespuesta()){
                                primeraVezF10 = false;
                                return;
                            }
                        }
                        if(!sesionVenta.AuditarFuncion()){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Canje Cupon Agencia!", "<html>Presione <font color="+ColoresInterfaz.cHTML3+">ENTER</font> para continuar<br>con el proceso de venta.<html>.",Color.RED);
                            primeraVezF10 = false;
                            return;
                        }
                    }
                    
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"3.- Despues de checar VJS");
                    if(rowsel == filaBancomer){
                        if(Boletos[0][11]!=null && !this.transaccion.equals("CN")){
                            //String liga = (sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : "");
                            String liga = ((sesionVenta.isBolRedCer())?sesionVenta.getDblinkRedIda():(sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : ""));
                            if(!sesionVenta.existeCorrida(Boletos[0][11].toString(), liga)){
                                this.getToolkit().beep();
                                //DialogoAceptar.mostrarDialogo("¡Imposible vender boleto!","La corrida no existe.",Color.RED);
                                primeraVezF10 = false;
                                this.setVentaOk(1);
                                sesionVenta.detenerWS();
                                this.dispose();
                                DialogoAceptar.mostrarDialogo("¡Imposible vender boleto(3)!","La corrida no existe.",Color.RED);
                                return;
                            }
                        }
                        if(!sesionVenta.ValidaFuncionUsuario("5015")){
                            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5015", "Cobrar con Tarjeta de Credito");
                            dlg.setVisible(true);
                            if(!dlg.getRespuesta()){
                                primeraVezF10 = false;
                                return;
                            }
                        }
                        if(!sesionVenta.AuditarFuncion()){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Cobro Tarjeta de Credito!", "<html>Presione <font color="+ColoresInterfaz.cHTML3+">ENTER</font> para continuar<br>con el proceso de venta.<html>.",Color.RED);
                        }
                    }
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"4.- Antes de calcula_Cantidades()");
                    jTxtRecibo.setText("$"+sesionVenta.customFormat("##,##0.00",Double.parseDouble((xFormasPago.getValueAt(rowsel,3)==null || xFormasPago.getValueAt(rowsel,3).equals("")?"0.00":xFormasPago.getValueAt(rowsel,3).toString()))));
                    calcula_Cantidades();
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"5.- Antes de EjecutaVenta");
                    EjecutaVenta();
                    //System.out.println(formatoDebugFecha.format(new Date())+":"+"5.- Antes de EjecutaVenta");
                    primeraVezF10 = false;
                break;
                case KeyEvent.VK_F12:
                    if(!excluyeTeclasVtaActual) excluyeTeclasVtaActual=true;
                    else return;
                    if(primeraVezF12){
                        break;
                    }
                    primeraVezF12 = true;
                    this.unAviso=false;
                    if(!ValidaSesion()){
                        primeraVezF12 = false;
                        return;
                    }
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"1.- Entra a F12");
                    if(Boletos[0][11]!=null && !this.transaccion.equals("CN")){
                        //String liga = (sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : "");
                        String liga = ((sesionVenta.isBolRedCer())?sesionVenta.getDblinkRedIda():(sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : ""));
                        if(!sesionVenta.existeCorrida(Boletos[0][11].toString(), liga)){
                            this.getToolkit().beep();
                            //DialogoAceptar.mostrarDialogo("¡Imposible vender boleto!","La corrida no existe.",Color.RED);
                            primeraVezF12 = false;
                            this.setVentaOk(1);
                            sesionVenta.detenerWS();
                            this.dispose();
                            DialogoAceptar.mostrarDialogo("¡Imposible vender boleto(4)!","La corrida no existe.",Color.RED);
                            return;
                        }
                    }
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"2.- Despues de checar corrida abierta");
                    if(filaBancomer==rowsel) return;
                    if(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("VJS")){
                        if(!sesionVenta.ValidaFuncionUsuario("5012")){
                            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5012", "Canjear Cupon de Agencia");
                            dlg.setVisible(true);
                            if(!dlg.getRespuesta()){
                                primeraVezF12 = false;
                                return;
                            }
                        }
                        if(!sesionVenta.AuditarFuncion()){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Canje Cupon Agencia!", "<html>Presione <font color="+ColoresInterfaz.cHTML3+">ENTER</font> para continuar<br>con el proceso de venta.<html>.",Color.RED);
                            primeraVezF12 = false;
                            return;
                        }
                    }
                    
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"3.- Despues de checar VJS");
                    if(rowsel == filaBancomer){
                        if(Boletos[0][11]!=null && !this.transaccion.equals("CN")){
                            //String liga = (sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : "");
                            String liga = ((sesionVenta.isBolRedCer())?sesionVenta.getDblinkRedIda():(sesionVenta.getTipoTransaccion().equals("R") ? sesionVenta.getDBLink() : ""));
                            if(!sesionVenta.existeCorrida(Boletos[0][11].toString(), liga)){
                                this.getToolkit().beep();
                                //DialogoAceptar.mostrarDialogo("¡Imposible vender boleto!","La corrida no existe.",Color.RED);
                                primeraVezF12 = false;
                                this.setVentaOk(1);
                                this.dispose();
                                DialogoAceptar.mostrarDialogo("¡Imposible vender boleto(5)!","La corrida no existe.",Color.RED);
                                return;
                            }
                        }
                        if(!sesionVenta.ValidaFuncionUsuario("5015")){
                            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5015", "Cobrar con Tarjeta de Credito");
                            dlg.setVisible(true);
                            if(!dlg.getRespuesta()){
                                primeraVezF12 = false;
                                return;
                            }
                        }
                        if(!sesionVenta.AuditarFuncion()){
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Cobro Tarjeta de Credito!", "<html>Presione <font color="+ColoresInterfaz.cHTML3+">ENTER</font> para continuar<br>con el proceso de venta.<html>.",Color.RED);
                        }
                    }
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"4.- Antes de calcula_Cantidades()");
                    jTxtRecibo.setText("$"+sesionVenta.customFormat("##,##0.00",Double.parseDouble((xFormasPago.getValueAt(rowsel,3)==null || xFormasPago.getValueAt(rowsel,3).equals("")?"0.00":xFormasPago.getValueAt(rowsel,3).toString()))));
                    calcula_Cantidades();
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"5.- Antes de EjecutaVenta");
                    EjecutaVenta();
                    //System.out.println(formatoDebugFecha.format(new Date())+":"+"5.- Antes de EjecutaVenta");
                    primeraVezF12 = false;
                break;
                case KeyEvent.VK_LEFT:
                    entrada=true;
                    jTblTipoPagos.setRowSelectionInterval(rowsel,rowsel);
                    switch(colsel){
                        case 3:
                            jTblTipoPagos.setColumnSelectionInterval(4,4);
                            break;
                        case 4:
                            jTblTipoPagos.setColumnSelectionInterval(5,5);
                            break;
                    }
                    if(rowsel == filaBancomer && colsel == 4){
                        jTblTipoPagos.setValueAt("", rowsel, colsel);
                        jtxtDocumento.setText("");
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                                entrada=true;
                    jTblTipoPagos.setRowSelectionInterval(rowsel,rowsel);
                    switch(colsel){
                        case 3:
                            jTblTipoPagos.setColumnSelectionInterval(2,2);
                            break;
                        case 4:
                            jTblTipoPagos.setColumnSelectionInterval(3,3);
                            break;
                    }
                    if(rowsel == filaBancomer && colsel == 4){
                        jTblTipoPagos.setValueAt("", rowsel, colsel);
                        jtxtDocumento.setText("");
                    }
                    break;
                case KeyEvent.VK_UP: case KeyEvent.VK_DOWN:
                    if((colsel==3 || colsel==4) && primerPresion){
                        primerPresion = false;
                        columnaSel=rowsel;
                        filaSel=rowsel;
                    }
                    if(rowsel == filaBancomer && colsel == 4){
                        jTblTipoPagos.setValueAt("", rowsel, colsel);
                        jtxtDocumento.setText("");
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if(!excluyeTeclasVtaActual) excluyeTeclasVtaActual=true;
                    else return;
                    //if(this.transaccion==sesionVenta.ctxVENTA_BA) return;
                    entrada=true;
                    this.setVentaOk(2);
                    this.dispose();
                    break;
                case KeyEvent.VK_F1:
                    if(!excluyeTeclasVtaActual) excluyeTeclasVtaActual=true;
                    else return;
                    entrada=true;
                    this.setVentaOk(1);
                    this.dispose();
                    break;  
                case KeyEvent.VK_ENTER:
                    if(!ValidaSesion()) return;
                    entrada=true;
                    if(colsel==3 && primerPresion){
                        primerPresion=false;
                        columnaSel=3;
                        filaSel=rowsel;
                        return;
                    }
                    valorlectura = jtxtDocumento.getText();
                    if(rowsel == filaBancomer && colsel == 4){
                        if(primeraVezENTER == true) primeraVezENTER = false;
                        jTblTipoPagos.setValueAt("", rowsel, colsel);
                        jtxtDocumento.setText("");  
                    }
                    if(colsel==4 && primerPresion){
                        primerPresion=false;
                        columnaSel=4;
                        filaSel=rowsel;
                        if(rowsel==filaBancomer){
                            if(!noAceptarPagoConTarjeta){
                                jTblTipoPagos.setValueAt("", this.filaBancomer, 4);
                                this.getToolkit().beep();
                                DialogoAceptar.mostrarDialogo("¡Cobro con tarjeta bancaria no permitido!", "El monto minimo a cobrar es de $<<"+sesionVenta.getUserCon().getMtoMinTjt()+">>",Color.RED);
                                this.jTblTipoPagos.setRowSelectionInterval(filaBancomer, filaBancomer);
                                this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                                this.jTblTipoPagos.requestFocus();
                                return;
                            }

                            if(!sesionVenta.getExisteImpresoraVoucher(this.nombreEmpresa)){
                                DialogoAceptar.mostrarDialogo("No esta disponible la impresora de vouchers.","<html>No es posible realizar la transaccion.<br>Contacte al administrador del sistema.</html>", Color.RED);
                                this.jTblTipoPagos.setRowSelectionInterval(filaBancomer, filaBancomer);
                                this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                                this.jTblTipoPagos.requestFocus();
                                impresoraErronea=true;
                                return;
                            }
                            salidaImpresion = sesionVenta.getImpresoraVoucher(this.nombreEmpresa);
                            sesionVenta.setVoucherBlanco(sesionVenta.isImpresoraVoucherBlanco(this.nombreEmpresa));
                            sesionVenta.setImpresoraNombreVoucherBlanco(sesionVenta.getNombreImpresoraVoucherBlanco(this.nombreEmpresa));

                            if(salidaImpresion==null){
                                jtxtDocumento.setText("");
                                jTblTipoPagos.setValueAt("", this.filaBancomer, 4);
                                jTblTipoPagos.setColumnSelectionInterval(4,4);
                                jTblTipoPagos.setRowSelectionInterval(filaBancomer,filaBancomer);
                                impresoraErronea=true;
                                return;
                            }
                            // PROCESO TARJETA DE CREDITO
                            jTblTipoPagos.setColumnSelectionInterval(4,4);
                            jTblTipoPagos.setRowSelectionInterval(filaBancomer,filaBancomer);
                            return;
                        }
                    }
                 break;
            }
        }
    }

    private void ejecutaVentaTJT(int indice,JClsPinPadTBResponse z){
        try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Entra a ejecutaVentaTJT... \n");} catch (IOException ex) {ex.printStackTrace();}
        try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}            
        
        System.out.println("Entra a ejecutaVentaTJT...");

        if( sesionVenta.isCobroCargo())
        {
            if(indice<0) return;
            sesionVenta.setTipoPagoCargo(FormasPago[indice][1].toString());
            sesionVenta.setAprobacionCargo(this.aprobacion);
            this.setVentaOk(0);
            this.dispose();
            return;
        }

        if(this.transaccion.equals(sesionVenta.ctxVENTA) || this.transaccion.equals(sesionVenta.ctxVENTA_RESERVACION) || this.transaccion.equals(sesionVenta.ctxVENTA_BA))
        {
            if(BoletosRedondos!=null) Boletos = BoletosRedondos;
            
            if(indice<0) return;
            int i, j;
            for(i=0; i<jTblTipoPagos.getRowCount(); i++)
                for(j=0; j<6; j++)
                    FormasPago[i][j]=xFormasPago.getValueAt(i,j);

            if(this.transaccion.equals(sesionVenta.ctxVENTA_RESERVACION)){
                for(i=0; i<Boletos.length; i++){
                    Boletos[i][13] = FormasPago[indice][1];
                    Boletos[i][14] = this.doctoRvn;
                }
            }
            else if(this.transaccion.equals(sesionVenta.ctxVENTA_BA)){
                for(i=0; i<Boletos.length; i++){
                    Boletos[i][13] = FormasPago[indice][1];
                    Boletos[i][18] = sesionVenta.getUserCon().getDiasValBab();
                }
            }
            else{
                for(i=0; i<Boletos.length; i++){
                    Boletos[i][13] = FormasPago[indice][1];
                    if(sesionVenta.getReservacionId()!=0) Boletos[i][16] = sesionVenta.getReservacionId();
                    if(sesionVenta.getBoletoRelacionadoId()!=0) Boletos[i][17] = sesionVenta.getBoletoRelacionadoId();
                }
            }

            for(i=0; i<Boletos.length; i++){
                Boletos[i][14] = this.aprobacion;
            }
            
            if(BoletosRedondos!=null){
                for(i=0; i<Boletos.length; i++){
                    if(Boletos[i][6].toString().equals("R") && Boletos[i][15].toString().equals("VA"))
                        Boletos[i][18] = sesionVenta.getUserCon().getDiasValBab();
                }
            }
            ///LEALTAD
            
                    ////////////
                    listado = sesionVenta.getTiposPasajeLealtad();
                    System.out.println("listado: "+listado.size());
                    System.out.println("SOCIO INTIMO(Tarjeta Credito)... ");
                    boolean pideTarjetaLealtad = false;
                    if(sesionVenta.isEmpresaLealtad(Boletos[0][7].toString())){
                        for(int m=0; m<listado.size(); m++)
                        {
                            if(listado.get(m).getAplicaLealtad()!=null)
                            {
                                if(listado.get(m).getAplicaLealtad().equals("S"))
                                    pideTarjetaLealtad = true;
                            }
                            System.out.println("Lealtad(Tarjeta Credito)("+listado.get(m).getRutaId()+")"+listado.get(m).getLealtad() +" Lunes: "+listado.get(m).getL_LUNES() +" Tipo: "+listado.get(m).getAplicaTipoLealtad()+" Aplica ("+listado.get(m).getLetraTipo()+") = "+listado.get(m).getAplicaLealtad());
                        }
                    }
                    System.out.println("pideTarjetaLealtad(Tarjeta Credito) : "+pideTarjetaLealtad);
                    boolean insertaLealtad = false;
                    Vector insertados = null;
                    if(pideTarjetaLealtad) 
                    {
                        System.out.println("Entra a pedir tarjeta de lealtad(Tarjeta Credito)...");
                                boolean cerrar2 = false;
                                String diaVenta = sesionVenta.getDiaSistemaVentaLealtad().toUpperCase();
                                String fechaArreglo[] = Boletos[0][26].toString().split("/");
                                Timestamp f = null;
                                f = Timestamp.valueOf(""+fechaArreglo[2]+"-"+fechaArreglo[1]+"-"+fechaArreglo[0]+" 00:00:00");
                                String diaCorrida = new SimpleDateFormat("EEEE").format(f.getTime()).toUpperCase();
                                System.out.println("diaCorrida = "+diaCorrida);
                                System.out.println("diaVenta = "+diaVenta);        
                                for(int k = 0; k<Boletos.length; k++)
                                {
                                     for(int h=0; h<listado.size(); h++)
                                     {
                                        if(Boletos[k][2].toString().equals(listado.get(h).getLetraTipo()))
                                        {
                                            String diaAplica = "";
                                            if(listado.get(h).getAplicaTipoLealtad().equals("VENTA"))
                                                diaAplica = diaVenta;
                                            else
                                                diaAplica = diaCorrida;
                                            if(listado.get(h).getAplicaLealtad().equals("S"))
                                            {
                                                if(diaAplica.equals("LUNES") && listado.get(h).getL_LUNES().equals("S"))
                                                {
                                                    cerrar2 = true;
                                                    break;
                                                }
                                                if(diaAplica.equals("MARTES") && listado.get(h).getL_MARTES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("MIÉRCOLES") && listado.get(h).getL_MIERCOLES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("JUEVES") && listado.get(h).getL_JUEVES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("VIERNES") && listado.get(h).getL_VIERNES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("SÁBADO") && listado.get(h).getL_SABADO().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("DOMINGO") && listado.get(h).getL_DOMINGO().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                            }
                                        }
                                     }
                                }                        
                        
                      if(cerrar2){
                              JDlgAcumulaEstrellas acumula = new  JDlgAcumulaEstrellas(this, true, this.Boletos, this.sesionVenta,listado,true,"");
    //                        acumula.setVisible(true);
    //                        acumula.setLocationRelativeTo(null);
    //                        System.out.println("manda a validarMostrar()...");
    //                        acumula.validarMostrar();

                            if(acumula.isCompletado()){
                                 this.Boletos =  acumula.getBoletos(); 
                                 insertaLealtad = true;
                                 insertados = acumula.getRegistros();
                            }
                            else
                                acumula = null;
                      }//if(cerrar2)
                    }//if(pideTarjetaLealtad)               
            
            //this.barraMensaje.setText(this.jLblMensajes.getMensajeVta(61));
            int edo=sesionVenta._RegistroVentaSP(Boletos);
                    //Acumula puntos
                    if(insertaLealtad && edo==0){
                        java.lang.String numeroOperacion = "";
                        for(int k=0;k<insertados.size(); k++)
                        {//String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento
                            Vector v =(Vector) insertados.get(k);
                            //System.out.println("Vector("+k+"):  "+v);
                            numeroOperacion = v.get(3).toString();
                            sesionVenta.insertaRegistroLealtad(Integer.valueOf(v.get(0).toString()),v.get(1).toString(),v.get(2).toString(),v.get(3).toString(),v.get(4).toString(),"A");
                        }
                        if(!sesionVenta.isUsarWSLelatad())
                        {
                            try{
                              String respuestaLealtad = sesionVenta.getTmsVtaFacade().Registra_Transaccion_Lealtad(numeroOperacion,"");
                              String[] arrayrsp = respuestaLealtad.split(",");
                               System.out.println("Respueesta(A0): "+arrayrsp[0]+ " ==> "+Boolean.parseBoolean(arrayrsp[0]));
                                //if(Boolean.parseBoolean(arrayrsp[0)))
                                    System.out.println("Respueesta(A1): "+arrayrsp[1]);
                                    System.out.println("Respueesta(A2): "+arrayrsp[2]);
                                    if(arrayrsp[0].equals("@error"))
                                        DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                                    else
                                        DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+arrayrsp[1].replaceAll("\\\\n\\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);
                                    if(Boolean.parseBoolean(arrayrsp[0]) && Boolean.parseBoolean(arrayrsp[2]))
                                        DialogoAceptar.mostrarDialogo("¡Estrellas de Tarjeta!","<html><Font size=6> ¡ Sus Estrellas estan proximas a vencer!</Font></html>", colorDialogoActivo);              
                                    
                            } catch (Exception ex) {
                                System.out.println("Excepcion al llamar getOperacion(A)");
                                ex.printStackTrace();
                                //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                                DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                            }
                        }
                        else
                        {
                            try { // Call Web Service Operations
                                wsLealtad.OperacionesResponse result = sesionVenta.getWSPort().getOperacion(numeroOperacion);
                                //result.getStatus().isSuccess();
                                System.out.println("Result(getOperacion(A)(Tarjeta Credito)("+numeroOperacion+")) = "+result.getStatus().isSuccess());
                                if(result.getStatus().isSuccess()){
                                   System.out.println("Puntos Otorgados: "+result.getPuntos().getPuntosOtorgados()); 
                                   System.out.println("           Saldo: "+result.getPuntos().getSaldoPuntos()); 
                                   DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas exitosa!","<html>Se acumularon:  <Font size=16>"+result.getPuntos().getPuntosOtorgados()+"</font> Estrellas  <br>        Saldo Actual:  <Font size=16>"+result.getPuntos().getSaldoPuntos()+"</font> Estrellas</html>", colorDialogoActivo);                    
                                }else
                                    DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>"+result.getStatus().getMessage()+"</html>", Color.RED);
                            } catch (Exception ex) {
                                System.out.println("Excepcion al llamar getOperacion del WS (Tarjeta Credito)");
                                ex.printStackTrace();
                                //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                                DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                            }
                         }
                        
                        
                    }  
                    
            if(edo!=0){
                System.out.println("Valor de edo: "+edo);
                switch(edo){
                    case -1: DialogoAceptar.mostrarDialogo("TMS Venta.","Registro ocupado.", Color.RED);
                             break;
                    case -2: DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible terminar venta.<br>Corrida ya fue despachada.</html>", Color.RED);
                             break;
                    case -3: break;
                    default: DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible terminar venta.<br>Contacte al administrador del sistema.</html>", Color.RED);
                             break;
                }
                this.setVentaOk(3);

                if(!sesionVenta.getUserCon().getSysCobroBancario())
                  Ejecuta_RollBack_TarjetaBancaria();
                else
                  Ejecuta_RollBack_SistemaCobroTarjetaBancaria();
                sesionVenta.detenerWS();
                this.dispose();
                return;
            }
            //Manda a registrar la Acumulacion de estrellas
            
               boolean imprime = false;
               for(int ct = 0; ct<Boletos.length; ct++)
               {
                   if(Boletos[ct][29].toString().equals("N"))
                   {
                       imprime = true;
                       break;
                   }
               }
               if(imprime)
               {
                System.out.println("Se imprimiran: "+Boletos.length+", forma de pago: "+xFormasPago.getValueAt(indice,1).toString());
                String ju="";
                boolean comprajuconi= false;
                float suma = 0;
                for (ProductoCarrito p:productos)
                {
                    if(p.getProducto().equals("JUCONI"))
                    {
                        comprajuconi= true;
                        suma = suma + p.getTotal();
                    }
                }
                //if(comprajuconi) ju = "Donativo JUCONI: $"+suma+"     ¡¡ G R A C I A S !!";;
                if(comprajuconi) ju = "Donativo JUCONI: $"+suma+" ¡¡GRACIAS!!";
                ImprimeBoletos x = new ImprimeBoletos();
                if(!x.ImprimeDatos(xFormasPago.getValueAt(indice,1).toString(),
                    sesionVenta.getUserCon().getPrefijo(),Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)),
                    Boletos,this.transaccion, sesionVenta.getUserCon().getDiasValBab(),sesionVenta.getUserCon().getUsuarioNom(), sesionVenta, thX.getFecha(),sesionVenta.getTmsVtaFacade().getPromocionVigente(),((comprajuconi)?ju:"") ) ){
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"17.- Entro IF NOT ImprimeDatos()");
                    this.setVentaOk(0);
                    this.getToolkit().beep();
                    try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
                    try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" ¡Venta registrada!, Boleto no impreso por falla de impresion. \n");} catch (IOException ex) {ex.printStackTrace();}
                    try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}            
                    DialogoAceptar.mostrarDialogo("¡Venta registrada!","Boleto no impreso por falla de impresion.", Color.RED);
                    sesionVenta.detenerWS();
                    this.dispose();
                    //DialogoAceptar.mostrarDialogo("¡Venta registrada!","Boleto no impreso por falla de impresion.", Color.RED);
                    return;
                    }
                }
            this.setVentaOk(0);
            if(imprime)
            {
                try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
                try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" ¡Venta exitosa!, Tome el boleto de la impresora \n");} catch (IOException ex) {ex.printStackTrace();}
                try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}            
                DialogoAceptar.mostrarDialogo("¡Venta exitosa!","Tome el boleto de la impresora", colorDialogoActivo);
            }
           sesionVenta.setImporteVenta(sesionVenta.getImporteVenta()+totalVentaProductos);
           System.out.println("La referencia desde Pago Tarjeta es: "+ sesionVenta.getVtaRef());
           if(!sesionVenta.getVtaRef().trim().equals("|") || sesionVenta.getVtaRef().length()>4)
           {
                    String ref = "";
                    String refRed = "";
                    String ref2 = "";
                    String refRed2 = "";
                    String ref1 = "";
                    String refRed1 = "";
                    StringTokenizer tkn = new StringTokenizer(sesionVenta.getVtaRef(),"|");
                    ref = tkn.nextToken();
                    if(tkn.hasMoreTokens())
                        refRed = tkn.nextToken();
                    StringTokenizer tkn2 = new StringTokenizer(ref,":");
                    ref1 = tkn2.nextToken();
                    if(tkn2.hasMoreTokens())ref2 = tkn2.nextToken();
                    if(!refRed.equals("")){
                        StringTokenizer tkn3 = new StringTokenizer(refRed,":");
                        refRed1 = tkn3.nextToken();
                        if(tkn3.hasMoreTokens())refRed2 = tkn3.nextToken();
                    }

                        //VAGL 06092011 Imprimir el Voucher de Cobro con trajeta despues de obtener las referencias para que imprimir las referncias en el Voucher
                        //VAGL 14092011 Se manda a llamar dos metodos en lugar de unos solo para imprimir el voucher
                        //new JClsImprimeVoucher().ImprimeDatos(z.getRspVoucher(), salidaImpresion, 0,ref2,refRed2);
                        String vou = "";
                        if(sesionVenta.isActivoEMVFull())
                        {
                            try{
                                if(sesionVenta.isImpVouComercio())
                                    vou = z.getRspVoucherComercio()+"\n@br  \n@br  "+z.getRspVoucherCliente();
                                else
                                    vou = z.getRspVoucherCliente()+"\n@br  \n@br  ";
                            } catch (Exception e)
                            {e.printStackTrace(); DialogoAceptar.mostrarDialogo("¡Configuracion EVM Full!", "¡La DLL del EMV Full no esta configurada!\n Contacte al Administrador del Sistema", Color.RED);}
                        }
                        else
                            vou = z.getRspVoucher();

                        new JClsImprimeVoucher().ImprimeDatos(vou, salidaImpresion, 0,ref2,refRed2, sesionVenta.getImpresoraNombreVoucherBlanco(), sesionVenta.isVoucherBlanco());
                        //////////////////////
                    if(this.sesionVenta.getUserCon().getAplicacionVenta())
                    {
                        imprimir_ticket_vta_ref ticket = new imprimir_ticket_vta_ref(sesionVenta.getNombreAutorizado(), ref, refRed, sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom(),Boletos);
                        if(ticket.ImprimeDatos(salidaImpresionTVRef, sesionVenta.isVoucherBlanco(), sesionVenta.getImpresoraNombreVoucherBlanco()))
                        {
                            if(refRed.equals(""))
                                DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente el ticket con la referencia <br> "+ref1+": <Font size=16>"+ref2+"</font></html>", colorDialogoActivo);                    
                            else
//                                DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente el ticket con con los números de referencia, <br>     Ida: <Font size=16>"+ref+" </font><br> " +
//                                        "Regreso: <Font size=16>"+refRed+"</font></html>", colorDialogoActivo);                    
                                DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente el ticket con con los números de referencia, <br>  "+ref1+": <Font size=16>"+ref2+" </font><br> " +
                                        refRed1+": <Font size=16>"+refRed2+"</font></html>", colorDialogoActivo);                    
                        }
                        else
                            DialogoAceptar.mostrarDialogo("¡Error de impresion!","<html>Se genero un archivo con la referencia por error de impresion <br> <Font size=16>"+ref+"</font></html>", colorDialogoActivo);                    
                    try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
                    try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" ¡Venta referenciada exitosa! \n");} catch (IOException ex) {ex.printStackTrace();}
                    try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}            
                    }
                    else
                    {
                        try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
                        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" ¡Venta referenciada exitosa!\n");} catch (IOException ex) {ex.printStackTrace();}
                        try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}  
                        if(refRed.equals(""))
                            DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente el número de referencia: <br>      "+ref1+": <Font size=16>"+ref2+" </font>", colorDialogoActivo);
                        else
                            DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente los números de referencia, <br> "+ref1+"  <Font size=16>"+ref2+" </font><br> " +
                                   refRed1+ ": <Font size=16>"+refRed2+"</font></html>", Color.RED);                        
//                            DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente los números de referencia, <br>     Ida: <Font size=16>"+ref+" </font><br> " +
//                                    "Regreso: <Font size=16>"+refRed+"</font></html>", Color.RED);                        
                    }
           }
            this.barraMensaje.setText(this.jLblMensajes.getMensajeVta(7));
            System.out.println(formatoDebugFecha.format(new Date())+":"+"18.- Antes Dispose");
            sesionVenta.detenerWS();
            dispose();
            //DialogoAceptar.mostrarDialogo("¡Venta exitosa!","Tome  el boleto de la impresora", colorDialogoActivo);
            return;
        }
    }

    // ejecuta venta
    private void EjecutaVenta(){
        double Resta = Double.parseDouble(this.jTxtRestan.getText().replace("$"," ").replace(",",""));
        System.out.println(formatoDebugFecha.format(new Date())+":"+"6.- Antes de Resta");
        String tarjeta = "";
        boolean socioIntimo = false;
        if(Resta==0 && sesionVenta.isCobroCargo())
        {
            int indice=this.jTblTipoPagos.getSelectedRow();
            if(indice<0) return;
            sesionVenta.setTipoPagoCargo(FormasPago[indice][1].toString());
            sesionVenta.setAprobacionCargo("");
            this.setVentaOk(0);
            this.dispose();
            return;
        }
        if(Resta==0){
            System.out.println("Inicia proceso Redencion... "+new Date());
            System.out.println("entra o no? : "+xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString()+" && "+xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString());
            if(BoletosRedondos!=null) Boletos = BoletosRedondos;
            if(!xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("EFE")){// && xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("SOC")){
                if(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString().equals("SOC"))
                    socioIntimo = true;
                else
                    socioIntimo = false;
                
                if(socioIntimo){
                   if(!sesionVenta.isEmpresaLealtad(Boletos[0][7].toString()))
                   {
                     DialogoAceptar.mostrarDialogo("TMS Venta.","<html>Tipo de pago no válido para esta Empresa.<br> Por favor intente otra selección</html>", Color.RED);
                     return;
                   }
                    //Verifica si aplica lelatad para la ruta y boletos vendidos  
                        listado = sesionVenta.getTiposPasajeLealtad();
                        boolean aplica = false;
                        referenciaRed = ""+sesionVenta.getFechaHoraSistemaVentaLealtad();
                        referenciaRed = referenciaRed.replace('[',' ');
                        referenciaRed = referenciaRed.replace(']',' ');
                        referenciaRed = referenciaRed.trim();
                        referenciaRed = sesionVenta.getUserCon().getTerminalId()+""+sesionVenta.getUserCon().getCajaId()+""+referenciaRed;
                        String diaVenta = "";
                        String diaCorrida = "";
                        Timestamp f = null;
                        String fechaArreglo[] = Boletos[0][26].toString().split("/");
                        f = Timestamp.valueOf(""+fechaArreglo[2]+"-"+fechaArreglo[1]+"-"+fechaArreglo[0]+" 00:00:00");
                        diaCorrida = new SimpleDateFormat("EEEE").format(f.getTime()).toUpperCase();
                        diaVenta = sesionVenta.getDiaSistemaVentaLealtad().toUpperCase();
//                        diaVenta = diaVenta.replace('[',' ');
//                        diaVenta = diaVenta.replace(']',' ');
//                        diaVenta = diaVenta.trim();
                        System.out.println("(Redencion)diaCorrida = "+diaCorrida);
                        System.out.println("(Redencion)diaVenta = "+diaVenta);    
                        System.out.println("(Redencion)Listado = "+listado.size()); 
                        insertadosRed = null;
                        for(int i = 0; i<Boletos.length; i++)
                        {
                             for(int j=0; j<listado.size(); j++)
                             {
                                if(Boletos[i][2].toString().equals(listado.get(j).getLetraTipo()))
                                {
                                    System.out.println("listado.get(j).getRutaId(): "+listado.get(j).getRutaId());
                                    System.out.println("listado.get(j).getLetraTipo(): "+listado.get(j).getLetraTipo());
                                    System.out.println("listado.get(j).getAplicaTipoLealtad(): "+listado.get(j).getAplicaTipoLealtad());
                                    String diaAplica = "";
                                    if(listado.get(j).getAplicaTipoLealtad().equals("VENTA"))
                                        diaAplica = diaVenta;
                                    else
                                        diaAplica = diaCorrida;
                                    if(listado.get(j).getAplicaLealtad().equals("S"))
                                    {
                                        if(diaAplica.equals("LUNES") && listado.get(j).getL_LUNES().equals("S"))
                                            aplica = true;
                                        if(diaAplica.equals("MARTES") && listado.get(j).getL_MARTES().equals("S"))
                                            aplica = true;
                                        if(diaAplica.equals("MIÉRCOLES") && listado.get(j).getL_MIERCOLES().equals("S"))
                                            aplica = true;
                                        if(diaAplica.equals("JUEVES") && listado.get(j).getL_JUEVES().equals("S"))
                                            aplica = true;
                                        if(diaAplica.equals("VIERNES") && listado.get(j).getL_VIERNES().equals("S"))
                                            aplica = true;
                                        if(diaAplica.equals("SÁBADO") && listado.get(j).getL_SABADO().equals("S"))
                                            aplica = true;
                                        if(diaAplica.equals("DOMINGO") && listado.get(j).getL_DOMINGO().equals("S"))
                                            aplica = true;
                                    }
                                }
                             }
                        }
                        System.out.println("(Redencdion)Aplica Lelatad : "+aplica);
                        if(!aplica)
                        {
                            DialogoAceptar.mostrarDialogo("TMS Venta.","<html>Tipo de pago no válido para esta Ruta.<br> Por favor intente otra selección</html>", Color.RED);
                            return;
                        }

                        System.out.println("Entra a pedir tarjeta de lealtad...");
                        JDlgRedimir redime = new  JDlgRedimir(this, true,this.Boletos , this.sesionVenta,listado,referenciaRed, diaVenta, diaCorrida, sesionVenta.isPermiteMotoLealtad());
                        System.out.println("manda a pedir tarjeta()...");
                        if(sesionVenta.isPermiteMotoLealtad())
                        {
                            redime.daFoco();
                        }
                        else
                            redime.pedirTarjeta();
                        
                        redime.setVisible(true);
                        tarjeta = redime.getTarjeta();
                        if(redime.isCompletado()){
                             this.Boletos =  redime.getBoletos(); 
                             insertadosRed = redime.getRegistros();
                        }
                        else
                        {
                            redime = null;
                            return;
                        }
                
                }
                else{
                    vReferenciaPago=xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),4).toString();
                    System.out.println("Referencia: "+(vReferenciaPago==null? "null":(vReferenciaPago.equals("")?"nada":vReferenciaPago)));
                    if(vReferenciaPago==null || vReferenciaPago.equals("")){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("TMS Venta.","Falta la referencia del documento.", Color.RED);
                        //this.jTblTipoPagos.setRowSelectionInterval(this.jTblTipoPagos.getSelectedRow(),this.jTblTipoPagos.getSelectedRow());
                        this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                        this.jTblTipoPagos.requestFocus();
                        return;
                    }
                    if(vReferenciaPago.length()<6){
                        this.getToolkit().beep();
                        DialogoAceptar.mostrarDialogo("TMS Venta.","<html>La referencia del documento debe contener<br>al menos 6 caracteres y/o digitos.</html>", Color.RED);
                        //this.jTblTipoPagos.setRowSelectionInterval(this.jTblTipoPagos.getSelectedRow(),this.jTblTipoPagos.getSelectedRow());
                        this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                        this.jTblTipoPagos.requestFocus();
                        return;
                    }     
                }
                
            }//if(equals("EFE"))
            System.out.println("despues de pedir tarjeta Redencion...: "+new Date());
             boolean insertaRedimir = true;
            if(socioIntimo)
                {//Pregunta si puede redimir     
                        boolean resultado = false;
                        boolean err = false;
                                    
                        System.out.println("Verifica Estrellas...");
                        if(!sesionVenta.isUsarWSLelatad())
                        {     
                        try{
                                        System.out.println("---------------------------------------------------");
                                        System.out.println("Llama a importeSuficiente con: "+tarjeta);
                                        System.out.println("Usuario Lealtad: "+Long.valueOf(sesionVenta.getUsuarioLealtad()));
                                        System.out.println("Password Lealtad: "+sesionVenta.getPasswordLealtad());
                                        System.out.println("Monto: "+Double.valueOf(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),2).toString()));
                                        System.out.println("Inicia llamado importeSuficiente... "+new Date());
                                        resultado = sesionVenta.getTmsVtaFacade().importeSuficiente(tarjeta,Long.valueOf(sesionVenta.getUsuarioLealtad()),sesionVenta.getPasswordLealtad(),Double.valueOf(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),2).toString()));
                                        System.out.println("Result(importeSuficiente(RImp)(Tarjeta: "+tarjeta+")) = "+resultado);
                                        System.out.println("Termina llamado importeSuficiente... "+new Date());
                                        System.out.println("---------------------------------------------------");
                                       
                                           // DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+arrayrsp[1).replaceAll("\\\\n\\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);
                        } catch (Exception ex) {
                            System.out.println("Excepcion al llamar getOperacion(V)");
                            ex.printStackTrace();
                            err = true;
                            //DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                        }
                        }
                        else
                        {
                                    try { // Call Web Service Operation
                                        System.out.println("-------------------------WS--------------------------");
                                        System.out.println("Llama a importeSuficiente con: "+tarjeta);
                                        System.out.println("Usuario Lealtad: "+Long.valueOf(sesionVenta.getUsuarioLealtad()));
                                        System.out.println("Password Lealtad: "+sesionVenta.getPasswordLealtad());
                                        System.out.println("Monto: "+Double.valueOf(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),2).toString()));
                                        System.out.println("Inicia llamado importeSuficiente... "+new Date());
                                        resultado = sesionVenta.getWSPort().importeSuficiente(tarjeta,Long.valueOf(sesionVenta.getUsuarioLealtad()),sesionVenta.getPasswordLealtad(),Double.valueOf(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),2).toString()));
                                        System.out.println("Result(importeSuficiente(RImp)(Tarjeta: "+tarjeta+")) = "+resultado);
                                        System.out.println("Termina llamado importeSuficiente... "+new Date());
                                        System.out.println("---------------------------WS------------------------");
                                    } catch (Exception ex) {
                                        System.out.println("Excepcion al llamar importeSuficiente del WS");
                                        ex.printStackTrace();
                                        err = true;
                                    }
                        }
                        System.out.println("err: "+err);
                        if(err)
                        {
                            DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible realizar esta operacion en este momento. <br>Por favor intente mas tarde.</html>", Color.RED);
                            insertaRedimir = false;
                            return;
                        }
                        if(!resultado)
                        {
                            DialogoAceptar.mostrarDialogo("TMS Venta.","Esta tarjeta no tiene estrellas suficientes para realizar esta compra.", Color.RED);
                            insertaRedimir = false;
                            return;
                        }
                }
                                 
           System.out.println(formatoDebugFecha.format(new Date())+":"+"7.- Antes de (ValidaCampos()");
            if(ValidaCampos())
            {
                System.out.println(formatoDebugFecha.format(new Date())+":"+"8.- Entro a ValidaCampos()");
                //if(BoletosRedondos!=null) Boletos = BoletosRedondos;
                    ////////////
                    listado = sesionVenta.getTiposPasajeLealtad();
                    System.out.println("SOCIO INTIMO: "+ xFormasPago.getValueAt(jTblTipoPagos.getSelectedRow(),6).toString());
                    boolean pideTarjetaLealtad = false;
                    
//                    if(xFormasPago.getValueAt(jTblTipoPagos.getSelectedRow(),6).toString().equals("S") && sesionVenta.isEmpresaLealtad(Boletos[0][7].toString()))
                    if(xFormasPago.getValueAt(jTblTipoPagos.getSelectedRow(),6).toString().equals("S") && sesionVenta.isEmpresaRedencion(Boletos[0][7].toString()))
                    {
                        for(int i=0; i<listado.size(); i++)
                        {
                            if(listado.get(i).getAplicaLealtad()!=null)
                            {
                                if(listado.get(i).getAplicaLealtad().equals("S"))
                                    pideTarjetaLealtad = true;
                            }
                            System.out.println("Lealtad("+listado.get(i).getRutaId()+")"+listado.get(i).getLealtad() +" Lunes: "+listado.get(i).getL_LUNES() +" Tipo: "+listado.get(i).getAplicaTipoLealtad()+" Aplica ("+listado.get(i).getLetraTipo()+") = "+listado.get(i).getAplicaLealtad());
                        }
                    }
                    System.out.println("pideTarjetaLealtad : "+pideTarjetaLealtad);
                    boolean insertaLealtad = false;
                    Vector insertados = null;
                    if(pideTarjetaLealtad) 
                    {
                        System.out.println("Entra a pedir tarjeta de lealtad...");
                                boolean cerrar2 = false;
                                String diaVenta = sesionVenta.getDiaSistemaVentaLealtad().toUpperCase();
                                String fechaArreglo[] = Boletos[0][26].toString().split("/");
                                Timestamp f = null;
                                f = Timestamp.valueOf(""+fechaArreglo[2]+"-"+fechaArreglo[1]+"-"+fechaArreglo[0]+" 00:00:00");
                                String diaCorrida = new SimpleDateFormat("EEEE").format(f.getTime()).toUpperCase();
                                System.out.println("diaCorrida = "+diaCorrida);
                                System.out.println("diaVenta = "+diaVenta);        
                                for(int k = 0; k<Boletos.length; k++)
                                {
                                     for(int h=0; h<listado.size(); h++)
                                     {
                                        if(Boletos[k][2].toString().equals(listado.get(h).getLetraTipo()))
                                        {
                                            String diaAplica = "";
                                            if(listado.get(h).getAplicaTipoLealtad().equals("VENTA"))
                                                diaAplica = diaVenta;
                                            else
                                                diaAplica = diaCorrida;
                                            if(listado.get(h).getAplicaLealtad().equals("S"))
                                            {
                                                if(diaAplica.equals("LUNES") && listado.get(h).getL_LUNES().equals("S"))
                                                {
                                                    cerrar2 = true;
                                                    break;
                                                }
                                                if(diaAplica.equals("MARTES") && listado.get(h).getL_MARTES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("MIÉRCOLES") && listado.get(h).getL_MIERCOLES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("JUEVES") && listado.get(h).getL_JUEVES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("VIERNES") && listado.get(h).getL_VIERNES().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("SÁBADO") && listado.get(h).getL_SABADO().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                                if(diaAplica.equals("DOMINGO") && listado.get(h).getL_DOMINGO().equals("S"))
                                                {
                                                    cerrar2= true;break;
                                                }
                                            }
                                        }
                                     }
                                }                        
                        
                      if(cerrar2){
                        JDlgAcumulaEstrellas acumula = new  JDlgAcumulaEstrellas(this, true, this.Boletos, this.sesionVenta,listado,true,"");
//                        acumula.setVisible(true);
//                        acumula.setLocationRelativeTo(null);
//                        System.out.println("manda a validarMostrar()...");
//                        acumula.validarMostrar();
                        System.out.println("acumula.isCompletado()"+acumula.isCompletado());
                        if(acumula.isCompletado()){
                             this.Boletos =  acumula.getBoletos(); 
                             insertaLealtad = true;
                             insertados = acumula.getRegistros();
                        }
                        else
                            acumula = null;
                      }//if(cerrar2)
                    }//if(pideTarjetaLealtad)                         
                //VAGL 09012013 Pedir el CP del cliente
                if(sesionVenta.isSolicitaCP())
                {
                    JDlgCPCliente dcp = new JDlgCPCliente(this);
                    sesionVenta.setCP(dcp.getCP());
                    System.out.println("CP Cliente: "+sesionVenta.getCP());
                }

                int indice=this.jTblTipoPagos.getSelectedRow();
                if(indice<0) return;
                int i, j;
                for(i=0; i<jTblTipoPagos.getRowCount(); i++)
                    for(j=0; j<6; j++)
                        FormasPago[i][j]=xFormasPago.getValueAt(i,j);
                    
                if(this.transaccion.equals(sesionVenta.ctxVENTA_RESERVACION)){
                    for(i=0; i<Boletos.length; i++){
                        Boletos[i][13] = FormasPago[indice][1];
                        Boletos[i][14] = this.doctoRvn;
                    }
                }
                else if(this.transaccion.equals(sesionVenta.ctxVENTA_BA)){
                    for(i=0; i<Boletos.length; i++){
                        Boletos[i][13] = FormasPago[indice][1];
                        Boletos[i][18] = sesionVenta.getUserCon().getDiasValBab();
                    }
                }
                else{
                    for(i=0; i<Boletos.length; i++){
                        Boletos[i][13] = FormasPago[indice][1];
                        if(sesionVenta.getReservacionId()!=0) Boletos[i][16] = sesionVenta.getReservacionId();
                        if(sesionVenta.getBoletoRelacionadoId()!=0) Boletos[i][17] = sesionVenta.getBoletoRelacionadoId();
                    }
                }
                System.out.println(formatoDebugFecha.format(new Date())+":"+"9.- Antes de Efectivo");
                if(!FormasPago[indice][1].toString().equals("EFE")){
                    if(!FormasPago[indice][1].toString().equals("VJS") && !FormasPago[indice][1].toString().equals("AMX")){
                        for(i=0; i<Boletos.length; i++){
                            Boletos[i][14] = this.jTblTipoPagos.getValueAt(jTblTipoPagos.getSelectedRow(),4);
                            if(FormasPago[indice][1].toString().equals("OR1"))
                            {
                                Boletos[i][12] = this.sesionVenta.getClienteSEDENAId();
                                Boletos[i][35] = "CRE";
                            }

                        }
                    }
                }
                System.out.println(formatoDebugFecha.format(new Date())+":"+"10.- Antes de VJS o AMX");
                //if(FormasPago[indice][1].toString().equals("VJS") || FormasPago[indice][1].toString().equals("AMX")){
                System.out.println("FormasPago["+indice+"][7]: "+FormasPago[indice][7]);
                System.out.println("Boletos.length: "+Boletos.length);
                if(FormasPago[indice][7].toString().equals("N")){
                    int contador = 0;
                    String cupones;
                    if(CuponesViaje!=null)
                    {
                        for(i=0; i<CuponesViaje.length; i++)
                            System.out.println("El tipo de boleto en referencia es(CuponesViaje["+i+"][2]) : "+CuponesViaje[i][2]);
                        System.out.println("--------------------------------------------------------------------");
                        for(i=0; i<CuponesViaje.length; i++)
                        {
                            System.out.println("El tipo de boleto en referencia es(CuponesViaje["+i+"][2]) : "+CuponesViaje[i][2]);
                            System.out.println("CuponesViaje["+i+"][0]: "+CuponesViaje[i][0]);
                            System.out.println("CuponesViaje["+i+"][1]: "+CuponesViaje[i][1]);
                            System.out.println("CuponesViaje["+i+"][2]: "+CuponesViaje[i][2]);
                            if(FormasPago[indice][1].toString().equals("AMX")) cupones = CuponesViaje[i][0].toString() + ";" + (CuponesViaje[i][1].toString().equals("") ? "" : CuponesViaje[i][1].toString());
                            else cupones = CuponesViaje[i][0].toString();
                            if(CuponesViaje[i][2].toString().equals("S") || CuponesViaje[i][2].toString().equals("A"))
                            {
                                System.out.println("Boletos["+contador+"][14]...");
                                Boletos[contador][14] = cupones;
                                Boletos[contador][12] = ""+this.sesionVenta.getClienteCreId();
                                Boletos[contador][35] = "CRE";
                            }
                            else{
                                //if(!Boletos[contador-1][6].equals("R"))
                                //{

                                    System.out.println("Boletos["+contador+"][14]...");
                                    Boletos[contador][14] = cupones;
                                    Boletos[contador][12] = ""+this.sesionVenta.getClienteCreId();
                                    Boletos[contador][35] = "CRE";
                                    contador++;
                                    System.out.println("Boletos["+contador+"][14]...");
                                    Boletos[contador][14] = cupones;
                                    Boletos[contador][12] = ""+this.sesionVenta.getClienteCreId();
                                    Boletos[contador][35] = "CRE";
}
                                 //}
                            contador++;
                        }
                    }
                }
                
                System.out.println(formatoDebugFecha.format(new Date())+":"+"11.- Antes de BoletosRedondos!=null");
                if(BoletosRedondos!=null){
                    for(i=0; i<Boletos.length; i++){
                        if(Boletos[i][6].toString().equals("R") && Boletos[i][15].toString().equals("VA"))
                            Boletos[i][18] = sesionVenta.getUserCon().getDiasValBab();
                    }
                }
               
//                    if(!sesionVenta.getExisteImpresoraVoucher(this.nombreEmpresa)){
//                        DialogoAceptar.mostrarDialogo("No esta disponible la impresora de vouchers.","<html>No es posible realizar la transaccion.<br>Contacte al administrador del sistema.</html>", Color.RED);
//                        impresoraErronea=true;
//                        return;
//                    }
//                    salidaImpresionTVRef = sesionVenta.getImpresoraVoucher(this.nombreEmpresa);
//                    if(salidaImpresionTVRef==null){
//                        System.out.println("No encontro impresora de voucher");
//                        impresoraErronea=true;
//                        return;
//                    }
                   String ocupados = "";
                   String saldo="";
                  if(this.transaccion.equals(sesionVenta.ctxVENTA) || this.transaccion.equals(sesionVenta.ctxVENTA_RESERVACION) || this.transaccion.equals(sesionVenta.ctxVENTA_BA))
                  {
                    this.barraMensaje.setText(this.jLblMensajes.getMensajeVta(61));
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"12.- Antes de sesionVenta._ RegistroVentaSP");
                    
                    //ver primero que se hace si _RegistroVentaSP 
                    //redime puntos
                    java.lang.String numeroOperacionRed = "";
                    if(insertaRedimir && socioIntimo){
                            
                               sesionVenta.setBoletosLealtad(Boletos);
                                for(int k=0;k<insertadosRed.size(); k++)
                                {
                                    Vector v =(Vector) insertadosRed.get(k);
                                    System.out.println("Vector("+k+"):  "+v);
                                    numeroOperacionRed = v.get(3).toString();
                                    sesionVenta.insertaRegistroLealtad(Integer.valueOf(v.get(0).toString()),v.get(1).toString(),v.get(2).toString(),v.get(3).toString(),v.get(4).toString(),"R");
                                }
                                    
                            System.out.println("Manda a redimir...");
                            if(!sesionVenta.isUsarWSLelatad())
                            {
                                try{
                                   String respuestaLealtad = sesionVenta.getTmsVtaFacade().Registra_Transaccion_Lealtad(numeroOperacionRed,"");
                                   String[] arrayrsp = respuestaLealtad.split(",");
                                   System.out.println("Respueesta(R0): "+arrayrsp[0]);
                                   System.out.println("Respueesta(R1): "+arrayrsp[1]);
                                   System.out.println("Respueesta(R2): "+arrayrsp[2]);
                                   System.out.println("Respueesta(R3)(utilizados): "+arrayrsp[3]);
                                   System.out.println("Respueesta(R4)(Saldo): "+arrayrsp[4]);
                                   if(!Boolean.parseBoolean(arrayrsp[0]))
                                   {
                                        DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible realizar esta operacion en este momento. <br>Por favor intente mas tarde. <br> "+arrayrsp[1]+"</html>", Color.RED);
                                        insertaRedimir = true;
                                        return;
                                   }
                                        //if(Boolean.parseBoolean(String[] arrayrsp = respuestaLealtad.split(",");0)))
                                   //String mensaje = arrayrsp[1].replaceAll("\\\\n\\\\r"," ");
                                   //int index1= mensaje.indexOf("Puntos Otorgados"), index2 =mensaje.indexOf("Saldo en Puntos"),index3 =mensaje.indexOf("Saldo en moneda") ;
                                  // ocupados = mensaje.substring(index1+19,index2).trim();
                                   //saldo = mensaje.substring(index2+18,index3).trim();
                                   ocupados = arrayrsp[3].trim();
                                   saldo = arrayrsp[4].trim();
                                   DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+arrayrsp[1].replaceAll("\\\\n\\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);
                                    if(Boolean.parseBoolean(arrayrsp[0]) && Boolean.parseBoolean(arrayrsp[2]))
                                        DialogoAceptar.mostrarDialogo("¡Estrellas de Tarjeta!","<html><Font size=6> ¡ Sus Estrellas estan proximas a vencer!</Font></html>", colorDialogoActivo);              
                                } catch (Exception ex) {
                                    System.out.println("Excepcion al llamar getOperacion(R)");
                                    ex.printStackTrace();
                                    //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                                   DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible realizar esta operacion en este momento. <br>Por favor intente mas tarde.</html>", Color.RED);
                                   return;
                                }
                            }
                            else
                            {
                               try { // Call Web Service Operation
                                        wsLealtad.OperacionesResponse result = sesionVenta.getWSPort().getOperacion(numeroOperacionRed);
                                        //result.getStatus().isSuccess();
                                        System.out.println("(WS)Inicia llamado getOperacion(R)... "+new Date());
                                        System.out.println("Result(getOperacion(R)("+numeroOperacionRed+")) = "+result.getStatus().isSuccess());
                                        System.out.println("Termina llamado getOperacion(R)... "+new Date());
                                        if(!result.getStatus().isSuccess())
                                        {
                                            DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible realizar esta operacion en este momento. <br>Por favor intente mas tarde.</html>", Color.RED);
                                            insertaRedimir = true;
                                            return;
                                        }else
                                        {
                                            DialogoAceptar.mostrarDialogo("Redención de Estrellas.","<html>"+result.getStatus().getMessage()+"</html>", Color.RED);
                                            ocupados = result.getPuntos().getPuntosOtorgados();
                                            saldo = result.getPuntos().getSaldoPuntos();
                                            insertaRedimir = true;
                                        }
                                        System.out.println("Puntos ocupado : "+ocupados);
                                        System.out.println("Puntos restantes : "+saldo);
                                    
                                    } catch (Exception ex) {
                                        System.out.println("Excepcion al llamar getOperacion(Redencion) del WS");
                                        ex.printStackTrace();
                                        DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible realizar esta operacion en este momento.<br> Por favor intente mas tarde.</html>", Color.RED);
                                        insertaRedimir = false;
                                        return;
                                    }
                            }
                        
                    }
                    System.out.println("Antes de llamar sesionVenta._RegistroVentaSP(Boletos):");
                    for(int l=0; l<Boletos.length; l++)
                    {
                        System.out.println("****Valor de Boletos["+l+"][6]: "+Boletos[l][6]);
                        System.out.println("    Valor de Boletos["+l+"][14]: "+Boletos[l][14]);
                        System.out.println("    Valor de Boletos["+l+"][12]: "+Boletos[l][12]);
                        System.out.println("    Valor de Boletos["+l+"][35]: "+Boletos[l][35]);
                    }
                    int edo=sesionVenta._RegistroVentaSP(Boletos);
                    for(int k=0; k<sesionVenta.getFoliosBoletos().size(); k++)
                    {
                        Vector v = (Vector)sesionVenta.getFoliosBoletos().get(k);
                        //private Object[][] Boletos;
                        for(int l=0; l<Boletos.length; l++)
                        {
                            if(v.get(0).toString().equals(Boletos[l][1]))
                            {
                                Boletos[l][19]=v.get(1).toString();
                                break;
                            }
                        }
                    }
                    //Acumula puntos
                    if(insertaLealtad && edo==0){
                        java.lang.String numeroOperacion = "";
                        for(int k=0;k<insertados.size(); k++)
                        {//String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento
                            Vector v =(Vector) insertados.get(k);
                            //System.out.println("Vector("+k+"):  "+v);
                            numeroOperacion = v.get(3).toString();
                            sesionVenta.insertaRegistroLealtad(Integer.valueOf(v.get(0).toString()),v.get(1).toString(),v.get(2).toString(),v.get(3).toString(),v.get(4).toString(),"A");
                        }
                        System.out.println("Acumula Estrellas...");
                        if(!sesionVenta.isUsarWSLelatad())
                        {
                            try{
                              String respuestaLealtad = sesionVenta.getTmsVtaFacade().Registra_Transaccion_Lealtad(numeroOperacion,"");
                              String[] arrayrsp = respuestaLealtad.split(",");
                              System.out.println("Respueesta(A0): "+arrayrsp[0]+ " ==> "+Boolean.parseBoolean(arrayrsp[0]));
                                //if(Boolean.parseBoolean(arrayrsp[0)))
                                    System.out.println("Respueesta(A1): "+arrayrsp[1]);
                                    System.out.println("Respueesta(A2): "+arrayrsp[2]);
                                    if(arrayrsp[0].equals("@error"))
                                        DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                                    else
                                        DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+arrayrsp[1].replaceAll("\\\\n\\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);
                                    if(Boolean.parseBoolean(arrayrsp[0]) && Boolean.parseBoolean(arrayrsp[2]))
                                        DialogoAceptar.mostrarDialogo("¡Estrellas de Tarjeta!","<html><Font size=6> ¡ Sus Estrellas estan proximas a vencer!</Font></html>", colorDialogoActivo);              
                            } catch (Exception ex) {
                                System.out.println("Excepcion al llamar getOperacion(A)");
                                ex.printStackTrace();
                                //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                                DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                                
                            }
                        }                        
                        else
                        {
                            try { // Call Web Service Operation
                                wsLealtad.OperacionesResponse result = sesionVenta.getWSPort().getOperacion(numeroOperacion);
                                //result.getStatus().isSuccess();
                                System.out.println("Result(getOperacion(A)("+numeroOperacion+")) = "+result.getStatus().isSuccess());
                                if(result.getStatus().isSuccess()){
                                   System.out.println("Puntos Otorgados: "+result.getPuntos().getPuntosOtorgados()); 
                                   System.out.println("           Saldo: "+result.getPuntos().getSaldoPuntos()); 
                                   DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas exitosa!","<html>Se acumularon:  <Font size=16>"+result.getPuntos().getPuntosOtorgados()+"</font> Estrellas  <br>        Saldo Actual:  <Font size=16>"+result.getPuntos().getSaldoPuntos()+"</font> Estrellas</html>", colorDialogoActivo);                    
                                }else
                                    DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>"+result.getStatus().getMessage()+"</html>", Color.RED);
                            } catch (Exception ex) {
                                System.out.println("Excepcion al llamar getOperacion del WS");
                                ex.printStackTrace();
                                //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                                DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                            }
                        }
                        
                    }
                    
                    System.out.println(formatoDebugFecha.format(new Date())+":"+"13.- Despues de sesionVenta._ RegistroVentaSP");
                    if(edo!=0){
                        System.out.println("Valor de edo: "+edo);
                        switch(edo){
                            case -1: DialogoAceptar.mostrarDialogo("TMS Venta.","Registro ocupado.", Color.RED);
                                     break;
                            case -2: DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible terminar venta.<br>Corrida ya fue despachada.</html>", Color.RED);
                                     break;
                            case -3: break;
                            default: DialogoAceptar.mostrarDialogo("TMS Venta.","<html>No es posible terminar venta.<br>Contacte al administrador del sistema.</html>", Color.RED);
                                     break;
                        }
                        if(insertaRedimir && socioIntimo)
                            DialogoAceptar.mostrarDialogo("TMS Venta.","<html>Se ha realizado la redencion de los puntos("+numeroOperacionRed+")." +
                                                                         "<br>   Pero no fue posible realizar la venta del(os) Boletos" +
                                                                         "<br>          Por favor contacte al supervisor en turno. </html>", Color.RED);
                        this.setVentaOk(3);
                        sesionVenta.detenerWS();
                        this.dispose();
                        return; 
                    }
                }
                else if(this.transaccion.equals(sesionVenta.ctxCANJE_BA)) strViaje="SENCILLO POR CANJE DE BOLETO ABIERTO";
                     else if(this.transaccion.equals(sesionVenta.ctxVENTA_BR)) strViaje="REDONDO";
               boolean imprime = false;
               boolean buscaImpVou = false;
               for(int ct = 0; ct<Boletos.length; ct++)
                   if(Boletos[ct][29].toString().equals("F"))
                       buscaImpVou = true;

               for(int ct = 0; ct<Boletos.length; ct++)
               {
                   if(Boletos[ct][29].toString().equals("N"))
                   {
                       imprime = true;
                       break;
                   }
               }
               if(buscaImpVou)
               {
                    if(!sesionVenta.getExisteImpresoraVoucher(this.nombreEmpresa)){
                        DialogoAceptar.mostrarDialogo("No esta disponible la impresora de vouchers.","<html>No es posible realizar la transaccion.<br>Contacte al administrador del sistema.</html>", Color.RED);
                        impresoraErronea=true;
                        return;
                    }
                    salidaImpresionTVRef = sesionVenta.getImpresoraVoucher(this.nombreEmpresa);
                    sesionVenta.setVoucherBlanco(sesionVenta.isImpresoraVoucherBlanco(this.nombreEmpresa));
                    sesionVenta.setImpresoraNombreVoucherBlanco(sesionVenta.getNombreImpresoraVoucherBlanco(this.nombreEmpresa));
                    if(salidaImpresionTVRef==null){
                        System.out.println("No encontro impresora de voucher");
                        impresoraErronea=true;
                        return;
                    }
                   
               }
               
               if(imprime)
               {
                   System.out.println(formatoDebugFecha.format(new Date())+":"+"Se imprimiran: "+Boletos.length+", forma de pago: "+xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString());
                    String ju="";
                    boolean comprajuconi= false;
                    float suma = 0;
                    for (ProductoCarrito p:productos)
                    {
                        if(p.getProducto().equals("JUCONI"))
                        {
                            comprajuconi= true;
                            suma = suma + p.getTotal();
                        }
                    }
                    if(comprajuconi) ju = "Donativo JUCONI: $"+suma+" ¡¡GRACIAS!!";
                   ImprimeBoletos x = new ImprimeBoletos();
                   System.out.println("Folio Actual: "+Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)));
                        if(!x.ImprimeDatos(xFormasPago.getValueAt(this.jTblTipoPagos.getSelectedRow(),1).toString(),
                            sesionVenta.getUserCon().getPrefijo(),Long.valueOf(sesionVenta.obtenerFolioActual(this.nombreEmpresa)),
                            Boletos,this.transaccion, sesionVenta.getUserCon().getDiasValBab(),sesionVenta.getUserCon().getUsuarioNom(),
                            sesionVenta, thX.getFecha(),sesionVenta.getTmsVtaFacade().getPromocionVigente(),((comprajuconi)?ju:""))){
                            if(FormasPago[indice][1].toString().equals("EFE")){
                                sesionVenta.EFECTIVO_CAJA+=Double.valueOf(this.jTxtTotalVenta.getText().replace("$"," ").replace(",",""))+totalVentaProductos;
                            }
                            this.setVentaOk(0);
                            this.getToolkit().beep();
                            DialogoAceptar.mostrarDialogo("¡Venta registrada!","Boleto no impreso por falla de impresion.", Color.RED);
                            sesionVenta.detenerWS();
                            this.dispose();
                            //DialogoAceptar.mostrarDialogo("¡Venta registrada!","Boleto no impreso por falla de impresion.", Color.RED);
                            return;
                        }
               }
                if(FormasPago[indice][1].toString().equals("EFE")){
                    sesionVenta.EFECTIVO_CAJA+=Double.valueOf(this.jTxtTotalVenta.getText().replace("$"," ").replace(",",""))+totalVentaProductos;
                }
                this.setVentaOk(0);
                this.barraMensaje.setText(this.jLblMensajes.getMensajeVta(7));
                if(imprime)
                    DialogoAceptar.mostrarDialogo("¡Venta exitosa!","Tome el boleto de la impresora", colorDialogoActivo);
                sesionVenta.setImporteVenta(sesionVenta.getImporteVenta()+totalVentaProductos);
                //Imprimir los tickets de las ventas referenciadas
               System.out.println("La referencia es: "+ sesionVenta.getVtaRef());
               if(!sesionVenta.getVtaRef().trim().equals("|") || sesionVenta.getVtaRef().length()>4)
               {

                    String ref = "";
                    String refRed = "";
                    String ref2 = "";
                    String refRed2 = "";
                    String ref1 = "";
                    String refRed1 = "";
                    StringTokenizer tkn = new StringTokenizer(sesionVenta.getVtaRef(),"|");
                    ref = tkn.nextToken();
                    if(tkn.hasMoreTokens())
                        refRed = tkn.nextToken();
                    StringTokenizer tkn2 = new StringTokenizer(ref,":");
                    ref1 = tkn2.nextToken();
                    if(tkn2.hasMoreTokens())ref2 = tkn2.nextToken();
                    if(!refRed.equals("")){
                        StringTokenizer tkn3 = new StringTokenizer(refRed,":");
                        refRed1 = tkn3.nextToken();
                        if(tkn3.hasMoreTokens())refRed2 = tkn3.nextToken();
                    }
                        
                        imprimir_ticket_vta_ref ticket = new imprimir_ticket_vta_ref(sesionVenta.getNombreAutorizado(), ref, refRed, sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom(),Boletos);
                        if(ticket.ImprimeDatos(salidaImpresionTVRef, sesionVenta.isVoucherBlanco(), sesionVenta.getImpresoraNombreVoucherBlanco()))
                        {
                            if(refRed.equals(""))
                                DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente el ticket con la referencia:<br> "+ref1+": <Font size=16>"+ref2+"</font></html>", colorDialogoActivo);
                            else
                                DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente el ticket con la referencia, <br> "+ref1+": <Font size=16>"+ref2+"</font><br> " +
                                       refRed1+ ": <Font size=16>"+refRed2+"</font></html>", colorDialogoActivo);
//                                DialogoAceptar.mostrarDialogo("¡Venta referenciada exitosa!","<html>Entregue al cliente el ticket con la referencia, <br> Ida: <Font size=16>"+ref+"</font><br> " +
//                                        "Regreso: <Font size=16>"+refRed+"</font><html>", colorDialogoActivo);
                        }
                        else
                            DialogoAceptar.mostrarDialogo("¡Error de impresion!","<html>Se genero un archivo con la referencia por error de impresion <br> <Font size=16>"+ref+"</font></html>", colorDialogoActivo);                    
               }
                
               //Impresion del ticket de comprobacion de referencia
               if(insertaRedimir && socioIntimo){
                    if(!sesionVenta.getExisteImpresoraVoucher(this.nombreEmpresa)){
                        DialogoAceptar.mostrarDialogo("No esta disponible la impresora de vouchers.","<html>No es posible realizar la transaccion.<br>Contacte al administrador del sistema.</html>", Color.RED);
                        impresoraErronea=true;
                        return;
                    }
                    String salidaImpresionRedencion = sesionVenta.getImpresoraVoucher(this.nombreEmpresa);
                    if(salidaImpresionRedencion==null){
                        System.out.println("No encontro impresora de voucher");
                        impresoraErronea=true;
                        return;
                    }
                   
                        imprimir_ticket_redencion ticket = new imprimir_ticket_redencion(sesionVenta.getNombreAutorizado(), tarjeta, sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom(),Boletos, ocupados, saldo);
                        if(ticket.ImprimeDatos(salidaImpresionRedencion))
                                DialogoAceptar.mostrarDialogo("¡Redencion de estrellas exitosa!","<html>Entregue al cliente el ticket  de la redencion de estrellas</html>", colorDialogoActivo);
                        else
                                    DialogoAceptar.mostrarDialogo("¡Error de impresion!","<html>Se genero un archivo con el numero de tarjeta("+tarjeta+") por error de impresion </html>", colorDialogoActivo);                    
               }
                System.out.println("Termina proceso Redencion... "+new Date());
                //Limpa la bandera del proceso de boleto Redondo Cerrado
                sesionVenta.setBolRedCer(false);
                if(!this.transaccion.equals(sesionVenta.ctxVENTA_RESERVACION))
                    sesionVenta.setDBLink("");
                sesionVenta.detenerWS();
                dispose();
                return;
            }else{
                this.jTblTipoPagos.setRowSelectionInterval(0,0);
                if(this.transaccion==sesionVenta.ctxCANJE_BA)  this.jTblTipoPagos.setColumnSelectionInterval(4,4);
                else this.jTblTipoPagos.setColumnSelectionInterval(3,3);
                this.jTblTipoPagos.requestFocus();
                this.setVentaOk(1);
            }
           
          }
        else{
            if(sesionVenta.getUserCon().getSysCobroBancario()) this.barraMensaje.setText(this.jLblMensajes.getMensajeVta(62));
            else this.barraMensaje.setText(this.jLblMensajes.getMensajeVta(6));
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Cuidado!", "¡No ha cobrado el importe completo!", Color.RED);
            this.jTblTipoPagos.setRowSelectionInterval(0,0);
            this.jTblTipoPagos.setColumnSelectionInterval(3,3);
            this.jTblTipoPagos.requestFocus();
            this.setVentaOk(1);
        }
    }

    private void detenerProgreso(){
        if(jProgreso!=null){
            jProgreso.finaliza();
            jProgreso = null;
        }
    }     
    
    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
    
    public boolean isRespondio() {
        return respondio;
    }

    public void setRespondio(boolean respondio) {
        this.respondio = respondio;
    }    
    
    
    private boolean ValidaCampos(){
        double TotalPagos = Double.valueOf(xFormasPago.getValueAt(jTblTipoPagos.getSelectedRow(),3).toString());
            if(TotalPagos<sesionVenta.getImporteVenta()){
                return false;
            }   
        return  true;
    } 
    /**
     * Metodo que regresa un boolean que indica si se realizo el cobro
     * @return Boolean que contiene el un indicador si se realizo el cobro o no.
     */
    public int getVentaOk() {
        return ventaOk;
    }

    /**
     * Metodo que regresa el valor del tipo de cobro
     * @param ventaOk indicador de cobro realizado correctamente.
     */
    private void setVentaOk(int pVentaOk) {
        this.ventaOk = pVentaOk;
    }
    
    private Object getValorParametro(String nombreParametro,
                                     String Terminal, long vTerminalId,
                                     String Servicio, long vServicioId,
                                     long vEmpresaId){
        Object valor=null;
        
        if(Terminal!=null && vTerminalId!=-1){
            valor=busqValorParametro(nombreParametro, Terminal, vTerminalId);
            if(valor != null) return valor;
        }
        
        if(Servicio!=null && vServicioId!=-1){
            valor=busqValorParametro(nombreParametro, Servicio, vServicioId);
            if(valor != null) return valor;
        }
        

        valor=busqValorParametro(nombreParametro, "EMPRESA", vEmpresaId);
        if(valor != null) return valor;
        
        if(valor==null){
            /*DialogoAceptar.mostrarDialogo(, Color.RED);null,"PGL-003: No puede continuar la aplicacion.\n" +
                                          "Parametro global <<"+nombreParametro+">> no esta configurado.\n" +
                                          "Contacte al Administrador del Sistema.","¡Importante!",JOptionPane.ERROR_MESSAGE);*/
            this.setVentaOk(1);
            this.dispose(); return null;
        }
        
        return valor;
    }
    
    private Object busqValorParametro(String nombreParametro, String TipoParametro, long ValorParametroId){
        Object[] registro = new Object[5];

        int ind=0, i=0;
/*        do{
            ind = ventaLoginCnx.getListaCodigos().indexOf(nombreParametro,ind);
            if (ind != -1){
                registro = (Object[])ventaLoginCnx.getListaParametros().get(ind);
                if(registro[0].toString().equals(TipoParametro) && Long.valueOf(registro[2].toString())==ValorParametroId) return registro[4];
            }
            ind++;
            i++;
        }while(ind<ventaLoginCnx.getListaCodigos().size() && i<ventaLoginCnx.getListaCodigos().size());*/
        
        return null;
    }

     private String msgRedondo() {
        String resp ="";
        int redondo=0;
        int normal=0;
        int ref=0;
        int nor=0;
        int i;
        for(i=0; i<Boletos.length; i++){
            if(Boletos[i][6].toString().equals("R")){
                redondo++;
            }
            else normal++;
            if(Boletos[i][29].toString().equals("F")){
                ref++;
            }
            else nor++;
        }
        
        if(i==normal) 
        {
            if(ref>0)
            {
                if(ref==i)
                    return "Sencillo     Referenciados";
                {
                    if(ref==1)
                        return "Sencillo     "+ref+" es Referenciado";
                    else
                        return "Sencillo     "+ref+" son Referenciados";
                }
            }
            else
                return "Sencillo";
        }
        if(i==redondo)
        {
            if(ref>0)
            {
                if(ref==i)
                    return "Redondo     Referenciados";
                else
                {
                    if(ref==1)
                        return "Redondo     "+ref+" es Referenciado";
                    else
                        return "Redondo     "+ref+" son Referenciados";
                }
            }
            else
                return "Redondo";
        }
        
        resp = normal+" Sencillo(s) y "+redondo+" Redondo(s)";
        if(ref>0)
        {
            if(ref==1)
                resp = resp + "     "+ref+" es Referenciado";
            else
                resp = resp + "     "+ref+" son Referenciados";
        }
        return resp; 
    }
    
    private String msgAbierto() {
        String resp ="";
        int ref=0;
        int nor=0;
        int i;
        for(i=0; i<Boletos.length; i++){
            if(Boletos[i][29].toString().equals("F")){
                ref++;
            }
            else nor++;
        }
        
        if(i==nor) 
            resp = "Abierto";
        else
        {
            if(ref>0)
            {
                if(ref==i)
                    return "Abiertos     Referenciados";
                {
                    if(ref==1)
                        return "Abiertos     "+ref+" es Referenciado";
                    else
                        return "Abiertos     "+ref+" son Referenciados";
                }
            }
            else
                return "Abierto";
        }

        return resp; 
    }
    
    
    
    class AttributiveCellRenderer1 extends JLabel  implements TableCellRenderer {
      
        public AttributiveCellRenderer1() {
            setOpaque(true);
        }
      
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
            if(value==null || value.equals(""))
                setText(null);
            else
                setText(String.valueOf(value));
            this.setFont(ColoresInterfaz.fuente4);
            if(row == jTblTipoPagos.getSelectedRow() && column == jTblTipoPagos.getSelectedColumn()){
                if(jTblTipoPagos.getSelectedRow() == filaBancomer && jTblTipoPagos.getSelectedColumn() == 4) this.setForeground(SELECCION);
                else this.setForeground(Color.BLACK);
                this.setBackground(SELECCION);
            }
            else{
                this.setBackground(ColoresInterfaz.cFondoVentana);
                this.setForeground(BLANCO);
            }
                
            if(column==2 || column==3) setHorizontalAlignment(RIGHT );
            else setHorizontalAlignment(LEFT);
          return this;
        }
    }
    // MOD: VALIDA SESION VIGENTE
    private boolean ValidaSesion(){
        int edoConsulta;
        if((edoConsulta=sesionVenta.getValidaSesionVenta())!=0){
            if(edoConsulta==-21){
                if(!this.unAviso){
                    this.getToolkit().beep();
                    DialogoSiNo = new JDlgSiNo("En este momento no es posible realizar la operacion.","<html>Por favor intentelo mas tarde o,<br>¿desea salir de TMS Venta?.</html>",false);
                    this.unAviso = true;
                    if(!DialogoSiNo.getResultado()) return false;
                    this.setVentaOk(13);
                    this.dispose();
                    return false;
                }
                return false;
            }
            this.setVentaOk(11);
            this.dispose();
            return false;
        }
        return true;
    }
    
    private boolean aunExisteReservacion(){
        for(int i=0; i<Boletos.length; i++){
            if(Boletos[i][16] != null){
                if(!sesionVenta.aunExisteReservacion(Boletos[i][16].toString())){
                    DialogoAceptar.mostrarDialogo("¡Venta reservacion!","No es posible vender esta reservación.",Color.RED);
                    return false;
                }
            }
        }
        return true;
    }

    private String invierteFecha(String fecha) {
        String fechaResultado;
        fechaResultado = ""+fecha.charAt(2)+""+fecha.charAt(3)+""+fecha.charAt(0)+""+fecha.charAt(1);
        //System.out.println("Fecha invertida"+fechaResultado);
        return fechaResultado;
    }
 
    private void interfazColor(){
        this.setBackground(ColoresInterfaz.cFondoVentana);
        this.barraMensaje.setOpaque(true);
        this.barraMensaje.setBackground(ColoresInterfaz.cFondoPieEncabezado);
        this.barraMensaje.setForeground(ColoresInterfaz.cTexto1);
        this.barraMensaje.setFont(ColoresInterfaz.fuente2);
        Contenedor.setOpaque(true);
        jPnlTipoPagos.setOpaque(true);
        tipo_Boletos.setOpaque(true);
        detalle_Boletos.setOpaque(true);
        this.Contenedor.setBackground(ColoresInterfaz.cFondoVentana);
        this.jPnlTipoPagos.setBackground(ColoresInterfaz.cFondoVentana);
        this.JPanelTotales.setBackground(ColoresInterfaz.cFondoVentana);
        this.detalle_Boletos.setBackground(ColoresInterfaz.cFondoVentana);
        this.jTbl_Boletos.setBackground(ColoresInterfaz.cFondoVentana);
        this.jTbl_Boletos.setForeground(Color.WHITE);
        this.tipo_Boletos.setBackground(ColoresInterfaz.cFondoVentana);
        this.jTxtTotalVenta.setBackground(ARENA);
        this.jTxtRecibo.setBackground(ARENA);
        this.jTxtCambio.setBackground(ARENA);
        this.jTxtRestan.setBackground(ARENA);
        this.jLblRecibo.setForeground(Color.WHITE);
        this.jLblCambio.setForeground(Color.WHITE);
        this.jLblRestan.setForeground(Color.WHITE);
        this.jLblTotal.setForeground(Color.WHITE);
        this.jTblTipoPagos.setBackground(ColoresInterfaz.cFondoVentana);
    }
    
    /***********************************/
    private void jTblTipoPagosFocusGained(java.awt.event.FocusEvent evt) {                                          
    // TODO add your handling code here:
        excluyeTeclasVtaActual = false;
    }
    
    private void jTblTipoPagosFocusLost(java.awt.event.FocusEvent evt) {                                        
    // TODO add your handling code here:
        excluyeTeclasVtaActual=false;
    }
    
    // metodo que implementa el nuevo sistema de cobro con tarjeta bancaria
    private void sistemaCobroTarjetaBancaria(){
        if(!sesionVenta.ValidaFuncionUsuario("5015")){
            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionVenta,"5015", "Cobrar con Tarjeta de Credito");
            dlg.setVisible(true);
            if(!dlg.getRespuesta()) return;
            this.unAviso=true;
        }

        if(!noAceptarPagoConTarjeta){
            jTblTipoPagos.setValueAt("", this.filaBancomer, 4);
            this.getToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Cobro con tarjeta bancaria no permitido!", "El monto minimo a cobrar es de $<<"+sesionVenta.getUserCon().getMtoMinTjt()+">>",Color.RED);
            this.jTblTipoPagos.setRowSelectionInterval(filaBancomer, filaBancomer);
            this.jTblTipoPagos.setColumnSelectionInterval(4,4);
            this.jTblTipoPagos.requestFocus();
            return;
        }
        if(!sesionVenta.getExisteImpresoraVoucher(this.nombreEmpresa)){
            DialogoAceptar.mostrarDialogo("No esta disponible la impresora de vouchers.","<html>No es posible realizar la transaccion.<br>Contacte al administrador del sistema.</html>", Color.RED);
            this.jTblTipoPagos.setRowSelectionInterval(filaBancomer, filaBancomer);
            this.jTblTipoPagos.setColumnSelectionInterval(4,4);
            this.jTblTipoPagos.requestFocus();
            impresoraErronea=true;
            return;
        } 
        System.out.println("nombreEmpresa(sistemaCobroTarjetaBancaria): "+this.nombreEmpresa);
        String empre  = "";
        if(this.nombreEmpresa == null || this.nombreEmpresa.equals(""))
            empre  = sesionVenta.getUserCon().getEmpresaPrincipal();
        else
             empre  = this.nombreEmpresa;
        System.out.println("nombreEmpresa(sistemaCobroTarjetaBancaria): "+empre);
        salidaImpresion = sesionVenta.getImpresoraVoucher(empre);
        sesionVenta.setVoucherBlanco(sesionVenta.isImpresoraVoucherBlanco(empre));
        sesionVenta.setImpresoraNombreVoucherBlanco(sesionVenta.getNombreImpresoraVoucherBlanco(empre));

        if(salidaImpresion==null){
            jtxtDocumento.setText("");
            jTblTipoPagos.setValueAt("", this.filaBancomer, 4);
            jTblTipoPagos.setColumnSelectionInterval(4,4);
            jTblTipoPagos.setRowSelectionInterval(filaBancomer,filaBancomer);
            impresoraErronea=true;
            return;
        }
        boolean[] btx = new boolean[6];
            //btx[0] = true; btx[1] = true; btx[2] = false; btx[3] = false; btx[4] = false; btx[5] = false; //originalmente
        
        if(this.sesionVenta.getUserCon().getAplicacionVenta())
            {btx[0] = true; btx[1] = true; btx[2] = false; btx[3] = false; btx[4] = false; btx[5] = false;}
        else
            {btx[0] = false; btx[1] = false; btx[2] = false; btx[3] = true; btx[4] = false; btx[5] = false;}
//        if(!this.sesionVenta.getUserCon().getAplicacionVenta() || )
//        {
            salidaImpresionTVRef = sesionVenta.getImpresoraVoucher(empre);
            sesionVenta.setVoucherBlanco(sesionVenta.isImpresoraVoucherBlanco(empre));
            sesionVenta.setImpresoraNombreVoucherBlanco(sesionVenta.getNombreImpresoraVoucherBlanco(empre));
            if(salidaImpresionTVRef==null){
                System.out.println("No encontro impresora de voucher");
                impresoraErronea=true;
                return;
            }
//        }f
        try {sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Empieza el cobro con tarjeta... \n");} catch (IOException ex) {ex.printStackTrace();}        
        try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}            
           boolean imprime = true;
           if(!this.transaccion.equals("CN"))
           {
               for(int ct = 0; ct<Boletos.length; ct++)
               {
                   if(Boletos[ct][29].toString().equals("F"))
                   {
                       imprime = false;
                       break;
                   }
               }
            }
        System.out.println("Manda a traer JDlgSysCboStandr...");
        JDlgSysCboStandr x = new JDlgSysCboStandr(btx, sesionVenta, "", salidaImpresion, "", "", false, this.thX,imprime,totalVentaProductos);
        x.setVisible(true);
        //if(!this.sesionVenta.getUserCon().getAplicacionVenta())
          //  x.ventaMoto_F5();
        //x.setModal(true);

        System.out.println("x.getSuccess()= "+x.getSuccess());
        try {sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+"x.getSuccess()= "+x.getSuccess()+" \n");} catch (IOException ex) {ex.printStackTrace();}
        try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}
        if(x.getSuccess()) 
        {
            if(!this.sesionVenta.getUserCon().getAplicacionVenta())
                this.setCerrar(true);
            JClsPinPadTBResponse z = x.getResponse();
            String noTDC = "";
            String tipoTDC = "";
            String tmpt = (z.getCc_Type()==null?"":z.getCc_Type());
            if(!tmpt.equals(""))
            {
                if(tmpt.length()<=23)
                    tipoTDC = tmpt;
                else
                    tipoTDC = tmpt.substring(0,24);
            }
            else
                tipoTDC = "TDC";

            String tmp = (z.getCc_Number()==null?"":z.getCc_Number());
            if(!tmp.equals(""))
            {
                if(tmp.length()<=4)
                {
                    noTDC = tmp;
                    if(noTDC.length()<4)
                        for(int i=noTDC.length();i<4;i++)
                            noTDC = noTDC+"X";
                } 
                else
                    noTDC = tmp.substring(tmp.length()-4);
            }
            else
                noTDC = "0000";
            this.aprobacion = z.getRspAuth()+";"+z.getRspOperationNumber()+";"+x.getReference()+";"+tipoTDC+"~"+noTDC;
            System.out.println("Datos I: "+this.aprobacion);
            try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
            try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Datos I: "+this.aprobacion+"\n");} catch (IOException ex) {ex.printStackTrace();}
            try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}            
            sesionVenta.rback_numOperacion = z.getRspOperationNumber();
            sesionVenta.rback_numAutorizacion = z.getRspAuth();
            ejecutaVentaTJT(filaBancomer,z);
        }
        else  
        {
             if(!this.sesionVenta.getUserCon().getAplicacionVenta())   
             {
                System.out.println("Entra a cobro despues de ESC en cobro tarjeta...");
                excluyeTeclasVtaActual=true;
                entrada=true;
                this.setVentaOk(-1);
                this.dispose();
                this.setCerrar(true);
                return;
             }
        }
    }
    
    // metodo que implementa el sistema de bancomer de cobro con tarjeta bancaria
    private void cobroTarjetaBancaria_Bancomer() {

//        if(!valorlectura.equals("")){ // proceso de tarjeta
//            if(primeraVezENTER == true) return;
//            System.out.println("Lectura TB::: "+valorlectura);
//            this.unAviso=false;
//            jTblTipoPagos.setValueAt("",filaBancomer,jTblTipoPagos.getSelectedColumn());
//            String vIP = sesionVenta.getValorParametro("P_VLREGLBIP", -1);//"192.168.16.12";
//            int    vPort = Integer.valueOf(sesionVenta.getValorParametro("P_VLREGLBPT", -1).toString());//1025;
//            int    vSeconds = Integer.valueOf(sesionVenta.getValorParametro("P_VLREGLBTO", -1).toString());//25;
//            sesionVenta.rback_vIP = vIP;
//            sesionVenta.rback_vPort = vPort;
//            sesionVenta.rback_vSeconds = vSeconds;
//            String vRequest = "";
//            String vResponse = "";
//            try{
//                eGlobal = new EGlobalSckCls();
//            }catch(java.lang.NoClassDefFoundError clsEx){
//                DialogoAceptar.mostrarDialogo("¡Configuracion incorrecta!", "Clase javadllapp/EGlobalSckCls no encontrada.",Color.RED);
//                jTblTipoPagos.setRowSelectionInterval(filaBancomer+1, filaBancomer+1);
//                primeraVezENTER = true;
//                return;
//            }catch(java.lang.UnsatisfiedLinkError clsEx1){
//                DialogoAceptar.mostrarDialogo("¡Configuracion incorrecta!", "Clase javadllapp/EGlobalSckCls no encontrada.",Color.RED);
//                jTblTipoPagos.setRowSelectionInterval(filaBancomer+1, filaBancomer+1);
//                primeraVezENTER = true;
//                return;
//            }
//            //System.out.println("SALI DE CONSTRUCTOR EGlobalSckCls");
//            if(valorlectura.indexOf("%")>=0 && valorlectura.indexOf("&")>=0 && valorlectura.indexOf("_")>=0 && valorlectura.length()>20){
//                // cuando la Tarjeta es deslizada...
//                String numerocuenta;
//                String fecha;
//                String nombre;
//                try{
//                    StringTokenizer tokens=new StringTokenizer(valorlectura,"&");
//                    String toknc = tokens.nextToken();
//                    numerocuenta = toknc.substring(toknc.length() - 16);
//                    nombre = tokens.nextToken();
//                    String tokfv = tokens.nextToken();
//                    StringTokenizer tokensfv=new StringTokenizer(tokfv,"_");
//                    String f1 = tokensfv.nextToken();
//                    f1 = tokensfv.nextToken();
//                    StringTokenizer tokensf2=null;
//                    try{
//                        //System.out.println("::::::::::::::::::::::::::::::: INTERROGACION");
//                        tokensf2=new StringTokenizer(f1,"¿");
//                        fecha=tokensf2.nextToken();
//                        fecha=tokensf2.nextToken();
//                    }
//                    catch(NoSuchElementException nsex){
//                        //System.out.println("::::::::::::::::::::::::::::::: ADMIRACION");
//                        tokensf2=new StringTokenizer(f1,"¡");
//                        fecha=tokensf2.nextToken();
//                        fecha=tokensf2.nextToken();
//                    }
//                }catch(Exception exX){
//                    this.getToolkit().beep();
//                    DialogoAceptar.mostrarDialogo("TMS Venta.", "Cobro con tarjeta bancaria no disponible.", Color.RED);
//                    jTblTipoPagos.setRowSelectionInterval(filaBancomer+1, filaBancomer+1);
//                    primeraVezENTER = true;
//                    return;
//                }
//                String mt = jTblTipoPagos.getValueAt(filaBancomer,2).toString();
//                mt = formatoCantidad(mt);
//                int ceros = 12 - mt.length();
//                String monto ="";
//                for(int i=1;i<=ceros;i++)
//                    monto = monto +"0";
//                monto = monto + mt;
//                String transaccion = "VENTA";
//                String tipotransaccion ="001";
//                String terminal = cajaNumero;//"00000001"; //"3000"+cajaNumero; //"30000049";
//                String valorformado = tipotransaccion+terminal+"00010001"+monto+"000000000000000000000000";
//                String resultado;
//                vRequest = valorformado+"S"+numerocuenta+"="+fecha;
//
//                resultado = eGlobal.EGlobalSck(vIP,vPort,vSeconds,vRequest,vResponse);
//                System.out.println("Resultado::: "+resultado);
//                if(resultado==null || resultado.equals("")){
//                    this.getToolkit().beep();
//                    DialogoAceptar.mostrarDialogo("TMS Venta.", "Cobro con tarjeta bancaria no disponible.", Color.RED);
//                    jTblTipoPagos.setRowSelectionInterval(filaBancomer+1, filaBancomer+1);
//                    primeraVezENTER = true;
//                    return;
//                }
//                /*try{
//                    FileOutputStream os1 = new FileOutputStream("C:\\LOGTARJBANC.TXT",true);
//                    PrintStream ps1 = new PrintStream(os1);
//                    ps1.print(vRequest+"\n"+resultado+" ["+formatoFechaTXTC.format(new Date())+"]\n\n"); // CADENA A IMPRIMIR
//                    ps1.flush();
//                    os1.close();
//                }catch(java.io.FileNotFoundException fsctex){
//                    System.out.println("Archivo log de tarjeta bancaria no actualizado");
//                }catch(Exception sctex){
//                    System.out.println("Archivo log de tarjeta bancaria no actualizado");
//                }*/
//                if(resultado.substring(resultado.length()-1).equals("0") && resultado.length()>25){
//                    if(resultado.substring(16,18).equals("00")){
//                        aprobacion = resultado.substring(32, resultado.length()-1);
//                        jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),filaBancomer,jTblTipoPagos.getSelectedColumn() + 1);
//
//                        //imprime ticket
//                        imprimir_voucher voucher = new imprimir_voucher(numerocuenta, nombre, fecha, mt,transaccion, aprobacion,
//                                sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom());
//                        voucher.ImprimeDatos(salidaImpresion);
//                        sesionVenta.rback_numCuenta = numerocuenta;
//                        sesionVenta.rback_nombre = nombre;
//                        sesionVenta.rback_fecha=fecha;
//                        sesionVenta.rback_monto=monto;
//                        sesionVenta.rback_terminalCaja=terminal;
//                        sesionVenta.rback_aprobacion=aprobacion;
//                        sesionVenta.rback_mt=mt;
//                        sesionVenta.rback_KS="S";
//                        sesionVenta.rback_usuarioNum=sesionVenta.getUserCon().getUsuarioNum();
//                        sesionVenta.rback_usuarioNombre=sesionVenta.getUserCon().getUsuarioNom();
//                        ejecutaVentaTJT(this.jTblTipoPagos.getSelectedRow(), null);
//                        if(!sesionVenta.AuditarFuncion()){
//                            this.getToolkit().beep();
//                            DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Cobro Tarjeta de Credito!", "<html>Presione <font color="+ColoresInterfaz.cHTML3+">ENTER</font> para continuar<br>con el proceso de venta.<html>.",Color.RED);
//                        }
//                    }
//                    else{
//                        jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),filaBancomer,jTblTipoPagos.getSelectedColumn() + 1);
//                        //jTblTipoPagos.setValueAt("",filaBancomer,jTblTipoPagos.getSelectedColumn());
//                    }
//                }
//                else{
//                    String mensaje = MensajeResultado(resultado.trim());
//                    jTblTipoPagos.setValueAt(mensaje,filaBancomer,jTblTipoPagos.getSelectedColumn() + 1);
//                }
//            }
//            // digitada
//            else{
//                if(!sesionVenta.getUserCon().getDigitaTB()) return;
//                boolean digitos = true;
//                char[] valores = valorlectura.toCharArray();
//                for(int i=0; i<valorlectura.length();i++){
//                    char c = valores[i];
//                    if(!Character.isDigit(c))
//                        digitos = false;
//                }
//                if(valorlectura.length()==20 && digitos){
//                    //cuando la Tarjeta es digitada
//                    String numerocuenta = valorlectura.substring(0,16);
//                    String fecha = valorlectura.substring(16);
//                    fecha=invierteFecha(fecha);
//                    String resultado;
//                    String mt = jTblTipoPagos.getValueAt(filaBancomer,2).toString();
//                    mt = formatoCantidad(mt);
//                    int ceros = 12 - mt.length();
//                    String monto ="";
//                    for(int i=1;i<=ceros;i++)
//                        monto = monto +"0";
//                    monto = monto + mt;
//                    String transaccion = "VENTA";
//                    String tipotransaccion ="001";
//                    String terminal = cajaNumero; //"00000001"; //"3000"+cajaNumero; //"30000049";
//                    //System.out.println("TERMINAL DE PAGO "+terminal);
//                    String valorformado = tipotransaccion+terminal+"00010001"+monto+"000000000000000000000000";
//                    vRequest = valorformado+"K000000"+numerocuenta+"="+fecha;
//                    //System.out.println("INFO IMPORTANTE "+vRequest);
//                    resultado = eGlobal.EGlobalSck(vIP,vPort,vSeconds,vRequest,vResponse);
//                    System.out.println("Resultado::: "+resultado);
//                    if(resultado==null || resultado.equals("")){
//                        this.getToolkit().beep();
//                        DialogoAceptar.mostrarDialogo("TMS Venta.", "Cobro con tarjeta bancaria no disponible.", Color.RED);
//                        jTblTipoPagos.setRowSelectionInterval(filaBancomer+1, filaBancomer+1);
//                        primeraVezENTER = true;
//                        return;
//                    }
//                    /*try{
//                        FileOutputStream os1 = new FileOutputStream("C:\\LOGTARJBANC.TXT",true);
//                        PrintStream ps1 = new PrintStream(os1);
//                        ps1.print(vRequest+"\n"+resultado+" ["+formatoFechaTXTC.format(new Date())+"]\n\n"); // CADENA A IMPRIMIR
//                        ps1.flush();
//                        os1.close();
//                    }catch(java.io.FileNotFoundException fsctex){
//                        System.out.println("Archivo log de tarjeta bancaria no actualizado");
//                    }catch(Exception sctex){
//                        System.out.println("Archivo log de tarjeta bancaria no actualizado");
//                    }*/
//
//                    if(resultado.substring(resultado.length()-1).equals("0")  && resultado.length()>25){
//                        if(resultado.substring(16,18).equals("00")){
//                            String aprobacion = resultado.substring(32, resultado.length()-1);
//                            jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),filaBancomer,jTblTipoPagos.getSelectedColumn() + 1);
//                            //imprime ticket
//                            imprimir_voucher voucher = new  imprimir_voucher(numerocuenta, "", fecha, mt,transaccion, aprobacion,
//                                    sesionVenta.getUserCon().getCajaNumero(), sesionVenta.getUserCon().getUsuarioNum(), sesionVenta.getUserCon().getUsuarioNom());
//                            voucher.ImprimeDatos(salidaImpresion);
//                            sesionVenta.rback_numCuenta=numerocuenta;
//                            sesionVenta.rback_nombre="";
//                            sesionVenta.rback_fecha=fecha;
//                            sesionVenta.rback_monto=monto;
//                            sesionVenta.rback_terminalCaja=terminal;
//                            sesionVenta.rback_aprobacion=aprobacion;
//                            sesionVenta.rback_mt=mt;
//                            sesionVenta.rback_KS="K000000";
//                            sesionVenta.rback_usuarioNum=sesionVenta.getUserCon().getUsuarioNum();
//                            sesionVenta.rback_usuarioNombre=sesionVenta.getUserCon().getUsuarioNom();
//                            ejecutaVentaTJT(this.jTblTipoPagos.getSelectedRow(),null);
//                            if(!sesionVenta.AuditarFuncion()){
//                                this.getToolkit().beep();
//                                DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Cobro Tarjeta de Credito!", "<html>Presione <font color="+ColoresInterfaz.cHTML3+">ENTER</font> para continuar<br>con el proceso de venta.<html>.",Color.RED);
//                            }
//                        }
//                        else{
//                            jTblTipoPagos.setValueAt(resultado.substring(24, resultado.length()-1),filaBancomer,jTblTipoPagos.getSelectedColumn() + 1);
//                            //jTblTipoPagos.setValueAt("",filaBancomer,jTblTipoPagos.getSelectedColumn());
//                        }
//                    }
//                    else{
//                        String mensaje = MensajeResultado(resultado.trim());
//                        jTblTipoPagos.setValueAt(mensaje,filaBancomer,jTblTipoPagos.getSelectedColumn() + 1);
//                    }
//                }
//                else{
//                        jTblTipoPagos.setValueAt("Datos incorrectos",filaBancomer,jTblTipoPagos.getSelectedColumn() + 1);
//                }
//            } // digitada
//        }// PROCESO TARJETA
//        valorlectura="";
    }

    // metodo de cancelacion en el nuevo sistema de cobro con tarjeta bancario
    private void Ejecuta_RollBack_SistemaCobroTarjetaBancaria(){
        try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Inicia RollBack: Ejecuta_RollBack_SistemaCobroTarjetaBancaria \n");} catch (IOException ex) {ex.printStackTrace();}
        System.out.println("Inicia RollBack");
        JClsPinPadTBRequest jClsPinPadTBRequest = new JClsPinPadTBRequest();
        JClsPinPadTBResponse jClsPinPadTBResponse = jClsPinPadTBRequest.getCancelacion(sesionVenta.getUserCon().getDbgSetUrl(),String.valueOf(sesionVenta.getImporteVenta()),sesionVenta.getUserCon().getBs_User(),
                                                                                    sesionVenta.getUserCon().getBs_Pwd(),sesionVenta.getUserCon().getUsuarioNum(),sesionVenta.getUserCon().getBs_Company(),
                                                                                    sesionVenta.getUserCon().getBs_Branch(),sesionVenta.getUserCon().getBs_Country(),sesionVenta.rback_numOperacion,sesionVenta.rback_numAutorizacion);
         /*try{
            FileOutputStream os1 = new FileOutputStream("C:\\LOGTARJBANC.TXT",true);
            PrintStream ps1 = new PrintStream(os1); 
            ps1.print("Cancelacion. Numero de operacion: "+sesionVenta.rback_numOperacion+" ["+formatoFechaTXTC.format(new Date())+"]\n\n"); // CADENA A IMPRIMIR
            ps1.flush();
            os1.close();
        }catch(java.io.FileNotFoundException fsctex){
            System.out.println("Archivo log de tarjeta bancaria no actualizado");
        }catch(Exception sctex){
            System.out.println("Archivo log de tarjeta bancaria no actualizado");
        }*/
        String msg = "<html>";
        if(jClsPinPadTBResponse.getRspDsResponse().equals("approved")){
            JClsImprimeVoucher jClsImprimeVoucher = new JClsImprimeVoucher();
            //VAGL 14092011 Se manda a llamar dos metodos en lugar de unos solo para imprimir el voucher
            //jClsImprimeVoucher.ImprimeDatos(jClsPinPadTBResponse.getRspVoucher(), salidaImpresion, 0,"","");
            String vou = "";
            if(sesionVenta.isActivoEMVFull())
            {
                try{
                    if(sesionVenta.isImpVouComercio())
                        vou = jClsPinPadTBResponse.getRspVoucherComercio()+"\n@br  \n@br  "+jClsPinPadTBResponse.getRspVoucherCliente();
                    else
                        vou = jClsPinPadTBResponse.getRspVoucherCliente()+"\n@br  \n@br  ";
                } catch (Exception e)
                {e.printStackTrace(); DialogoAceptar.mostrarDialogo("¡Configuracion EVM Full!", "¡La DLL del EMV Full no esta configurada!\n Contacte al Administrador del Sistema", Color.RED);}

            }
            else
                vou =jClsPinPadTBResponse.getRspVoucher();
            jClsImprimeVoucher.ImprimeDatos(vou, salidaImpresion, 0,"","", sesionVenta.getImpresoraNombreVoucherBlanco(), sesionVenta.isVoucherBlanco());
            this.aprobacion = jClsPinPadTBResponse.getRspAuth()+";"+jClsPinPadTBResponse.getRspOperationNumber();
            msg += "Transaccion APROBADA.<br>Numero de autorizacion: "+jClsPinPadTBResponse.getRspAuth();
            msg += "</html>";
            DialogoAceptar.mostrarDialogo("TMS Venta.", msg, Color.RED);
            return;
        }
        else if(jClsPinPadTBResponse.getRspDsResponse().equals("denied")){
            msg += "Transaccion DENEGADA ("+jClsPinPadTBResponse.getRspCdResponse()+").";
        }
        else if(jClsPinPadTBResponse.getRspDsResponse().equals("error")){
            System.out.println("Error "+jClsPinPadTBResponse.getRspCdError()+" - "+jClsPinPadTBResponse.getRspDsError());
            try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Error "+jClsPinPadTBResponse.getRspCdError()+" - "+jClsPinPadTBResponse.getRspDsError()+"\n");} catch (IOException ex) {ex.printStackTrace();}
            msg += "Transaccion DENEGADA ("+jClsPinPadTBResponse.getRspCdError()+").";
        }
        msg += "</html>";
        System.out.println("Fin RollBack");
        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Fin RollBack \n");} catch (IOException ex) {ex.printStackTrace();}
        DialogoAceptar.mostrarDialogo("TMS Venta.", msg, Color.RED);
        try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}            
    }
    
    // metodo de cancelacion en el anterior sistema de cobro con tarjeta bancario
    private void Ejecuta_RollBack_TarjetaBancaria() {
//        try{sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
//        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Inicia RollBack: Ejecuta_RollBack_TarjetaBancaria\n");} catch (IOException ex) {ex.printStackTrace();}
//        sesionVenta.rback_vRequest = sesionVenta.rback_tipoTx+sesionVenta.rback_terminalCaja+"00010001"+
//                                     sesionVenta.rback_monto+"000000000000000000000000"+sesionVenta.rback_KS+sesionVenta.rback_numCuenta+
//                                     "="+sesionVenta.rback_fecha;
//        System.out.println("sesionVenta.rback_vRequest: "+sesionVenta.rback_vRequest);
//        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" sesionVenta.rback_vRequest: "+sesionVenta.rback_vRequest+"\n");} catch (IOException ex) {ex.printStackTrace();}
//
//        String rback_vResponse="";
//        String rback_resultado = eGlobal.EGlobalSck(sesionVenta.rback_vIP, sesionVenta.rback_vPort, sesionVenta.rback_vSeconds, sesionVenta.rback_vRequest, rback_vResponse);
//        System.out.println("sesionVenta.rback_vRequest: "+rback_resultado);
//        try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" sesionVenta.rback_vRequest: "+rback_resultado+"\n");} catch (IOException ex) {ex.printStackTrace();}
//
//        /*try{
//            FileOutputStream os1 = new FileOutputStream("C:\\LOGTARJBANC.TXT",true);
//            PrintStream ps1 = new PrintStream(os1);
//            ps1.print(sesionVenta.rback_vRequest+"\n"+rback_resultado+" ["+formatoFechaTXTC.format(new Date())+"]\n\n"); // CADENA A IMPRIMIR
//            ps1.flush();
//            os1.close();
//        }catch(java.io.FileNotFoundException fsctex){
//            System.out.println("Archivo log de tarjeta bancaria no actualizado");
//        }catch(Exception sctex){
//            System.out.println("Archivo log de tarjeta bancaria no actualizado");
//        }*/
//        if(rback_resultado.substring(rback_resultado.length()-1).equals("0")  && rback_resultado.length()>25){
//          if(rback_resultado.substring(16,18).equals("00")){
//             String rback_aprobacion = rback_resultado.substring(32, rback_resultado.length()-1);
//             jTblTipoPagos.setValueAt("Reembolso: "+rback_resultado.substring(24, rback_resultado.length()-1),jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//             jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//            //imprime ticket
//            imprimir_voucher voucher = new  imprimir_voucher(sesionVenta.rback_numCuenta, sesionVenta.rback_nombre, sesionVenta.rback_fecha, sesionVenta.rback_mt, sesionVenta.rback_nombreTx, rback_aprobacion,
//                    sesionVenta.rback_terminalCaja, sesionVenta.rback_usuarioNum, sesionVenta.rback_usuarioNombre);
//            voucher.ImprimeDatos(salidaImpresion);
//            try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" Solicitud de reembolso enviada al banco emisor \n");} catch (IOException ex) {ex.printStackTrace();}
//            DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>Solicitud de reembolso enviada al banco emisor.<br>Tome el voucher.</html>", Color.RED);
//          }
//          else{
//               jTblTipoPagos.setValueAt("Reembolso: "+rback_resultado.substring(24, rback_resultado.length()-1),jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//               jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//               try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" La solicitud de reembolso no fue exitosa. \n");} catch (IOException ex) {ex.printStackTrace();}
//               DialogoAceptar.mostrarDialogo("TMS Venta.", "La solicitud de reembolso no fue exitosa.", Color.RED);
//          }
//        }
//        else{
//            String mensaje = MensajeResultado(rback_resultado.trim());
//            try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" "+rback_resultado.trim()+"\n");} catch (IOException ex) {ex.printStackTrace();}
//            jTblTipoPagos.setValueAt("Reembolso: "+mensaje,jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn() + 1);
//            jTblTipoPagos.setValueAt("",jTblTipoPagos.getSelectedRow(),jTblTipoPagos.getSelectedColumn());
//            DialogoAceptar.mostrarDialogo("TMS Venta.", "La solicitud de reembolso no fue exitosa.", Color.RED);
//        }
//        try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}
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

    public boolean isCerrar() {
        return cerrar;
    }

    public void setCerrar(boolean cerrar) {
        this.cerrar = cerrar;
    }


}