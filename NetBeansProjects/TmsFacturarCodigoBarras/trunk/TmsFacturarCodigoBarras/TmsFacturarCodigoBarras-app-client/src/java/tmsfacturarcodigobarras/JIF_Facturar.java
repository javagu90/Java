/*
 * JIF_Facturar.java
 *
 * Created on 7 de enero de 2009, 09:41 AM
 */

package tmsfacturarcodigobarras;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterJob;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.PrintService;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import solicitud.FacturaTMSREPCONTROLBeanRemote;
import solicitud.TmsSesionBeanFacturarRemote;
import solicitud.TmsSesionBeanFacturarTMSRemote;
import tmsfacturarcodigobarras.JDLG_BusquedaNombre;


/**
 *
 * @author  asolis
 */

public class JIF_Facturar extends javax.swing.JInternalFrame { //implements KeyListener{
    //String dblink ="";
    String p_nombre = "";
    String rfc_viejito = null;
    DefaultTableModel modelo = null;
    Vector salida[] = new Vector[16];
    String datos [][] = null;
    String encabezados [] = {"Clave Corrida","Nombre Pasajero","Tipo Pasajero","No. Asiento","Fecha/Hora Corrida","Fecha/Hora Venta","Importe","Origen","Destino","Servicio","Método Pago","Folio"};
    Context cont = null;
    TmsSesionBeanFacturarRemote cosa = null;
    TmsSesionBeanFacturarTMSRemote facadeTMS = null;
    FacturaTMSREPCONTROLBeanRemote RepControlFacade = null;
    Cliente addCliente = null;
    double subtotal = 0.0;
    double iva = 0.0;
    double total = 0.0;
    int numeroBoletos = 0;
    int existe = 0; // 0 no existe, 1 si existe, 2 modificar
    int facturas_ini = 0;
    int facturas_fin = 0;
    String facturas_prefijo = "";
    String usuario = null;
    String terminal = null;
    String dblink_terminal = null;
    private KeyEvent eventoTeclado;
    Vector datosIniciales = null;
    PrintService impresoraEncontrada = null;
    boolean menos5boletos = false;
    private String RutaPDF  =  "";
    private String LlaveFact  =  "";
    private Vector Vectciudades=null;
    
      // Var FACTURACION Electroncia 
    
    private boolean FactElecAct   = false;  
    private String  RFCTMS    = "";
    private String  RSTMS     = "";
    private String  RutAbs        = "";
    private String  RutAlias      = "";  
    private String  Ambito        = "";
    private String  UnidadNegocio = "";
    private String PrefijoFolioFact ="2000";
    public final static Cursor busyCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
    public final static Cursor defaultCursor = Cursor.getDefaultCursor();
    private String TerminalFact ="";

    private String MetodoPago="";
    private String NoCuenta ="";
    private String productos ="";//productos = productos+"^|GUIA|"+((Guia)ListGuias.get(i)).getNum_guia();
   
  
    /** Creates new form JIF_Facturar */      
    public JIF_Facturar(Vector datosIniciales) {
        modelo = new DefaultTableModel(datos, encabezados);

        this.usuario = datosIniciales.get(1).toString();
        this.p_nombre = datosIniciales.get(2).toString();
        this.terminal = datosIniciales.get(4).toString();
        this.dblink_terminal = datosIniciales.get(6).toString();
        this.datosIniciales = datosIniciales;
        initComponents();
        
        System.out.println("ID Terminal "+this.terminal);
        
        jLblEtiqueta.setText("Rev. 24052013");  //100811
        this.setTitle("Facturar Boletos Rev0 2.9");
        System.out.println(" FACTURACION Rev 09 05 2013");
        
        
         try {
            cont = new InitialContext(System.getProperties());
            cosa = (TmsSesionBeanFacturarRemote) cont.lookup(TmsSesionBeanFacturarRemote.class.getName());
           facadeTMS = (TmsSesionBeanFacturarTMSRemote) cont.lookup(TmsSesionBeanFacturarTMSRemote.class.getName());
           RepControlFacade = (FacturaTMSREPCONTROLBeanRemote) cont.lookup(FacturaTMSREPCONTROLBeanRemote.class.getName());
            //System.out.println(cosa + " "+cont);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        
       //TerminalFact= facadeTMS.getTerminalID(this.terminal);
       //System.out.println("ID TerminalFact "+this.TerminalFact);
       TerminalFact = this.terminal;
       
       // Llena combo ciudad venta
       
       Vector vciudades=facadeTMS.getCiudadVentaList();
       System.out.println("Ciudad venta "+vciudades);
     
      jCmbCdVenta.removeAllItems();
      //DefaultComboBoxModel defaultModel =new DefaultComboBoxModel(vciudades);
      //jCmbCdVenta.setModel(defaultModel);
      this.Vectciudades=vciudades;
      for (int i = 0; i < vciudades.size(); i++) 
                jCmbCdVenta.addItem(((Vector)vciudades.elementAt(i)).elementAt(1).toString().trim());
      
      // Llenado de combobox
       
      
        //*******   Factura Electronica    Dic 2010  BRA
         Vector VparamRFC = facadeTMS.getParamGlobal("'P_FACT_ELECT_ACT','P_RFCTMS','P_RSTMS','P_RUTABSOLUTAFE','P_RUTALIASFE'");
                                                 
          if (VparamRFC != null && VparamRFC.size() > 0)
          {
              String cade= ((Vector)VparamRFC.elementAt(0) ).elementAt(0).toString().trim();
              FactElecAct =(cade.equalsIgnoreCase("1")? true:false);
              RFCTMS = ((Vector)VparamRFC.elementAt(1) ).elementAt(0).toString().trim();
              RSTMS  = ((Vector)VparamRFC.elementAt(2) ).elementAt(0).toString().trim();
              RutAbs     = ((Vector)VparamRFC.elementAt(3) ).elementAt(0).toString().trim();
              RutAlias   = ((Vector)VparamRFC.elementAt(4) ).elementAt(0).toString().trim();
              
              System.out.println("emisor RFC "+RFCTMS);
              System.out.println("razon social "+RSTMS);
           
              System.out.println("RutAbs "+RutAbs); 
              System.out.println("RutAlias "+RutAlias);
              
              System.out.println("****   FactElecAct ****   "+FactElecAct);
          }
         
        
       
       if (!FactElecAct) {
        JDLG_Refoliar refoliar = new JDLG_Refoliar(null, 0,0, false, this.terminal);
        refoliar.setLocationRelativeTo(this);
        refoliar.setModal(true);
        refoliar.setSize(600,180);
        refoliar.setVisible(true);
        facturas_fin = refoliar.fol_fin;
        facturas_ini = refoliar.fol_ini;
        facturas_prefijo = refoliar.prefijo;
        System.out.println("facturas_prefijo "+facturas_prefijo);
       }
         
         
       
        salida[0] = new Vector();
        salida[1] = new Vector();
        salida[2] = new Vector();
        salida[3] = new Vector();
        salida[4] = new Vector();
        salida[5] = new Vector();
        salida[6] = new Vector();
        salida[7] = new Vector();
        salida[8] = new Vector();
        salida[9] = new Vector();
        salida[10] = new Vector();
        salida[11] = new Vector();
        salida[12] = new Vector();
        salida[13] = new Vector();
        salida[14] = new Vector();
        //barraEstado0(true);
        this.setIconifiable(true);
        
        resizeColumnasCorridas();
        jtbl_boletos.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        //this.setIconifiable(true);
        barraEstado0();
        //jtxt_codigo.requestFocus();
        jCmbCdVenta.setRequestFocusEnabled(true);
        PcInfo pc = new PcInfo();
        boolean existeimp = false;
        impresoraEncontrada = null;
        System.out.println("Buscando impresora  para "+pc.getHostName().toString().toUpperCase());
       // Vector vimp = (Vector) cosa.buscarImpresoraFacturas(pc.getHostName().toString().toUpperCase());
      //  Vector vimp = (Vector) facadeTMS.buscarImpresoraFacturas(pc.getHostName().toString().toUpperCase());
    /*    
        if(vimp.size()==0)
              //enviar mensaje de error indicando que no hay impresoras para facturas configuradas en la caja
              JOptionPane.showMessageDialog( this, "No hay impresoras configuradas para esta caja. Contacte al administrador del sistema",
                                "TMS - FacturaBoletor Boleto", JOptionPane.ERROR_MESSAGE ); 
        else
        {
             String nomImpre = vimp.get(0).toString();
             nomImpre = nomImpre.replace('[', ' ');
             nomImpre = nomImpre.replace(']', ' ');
             nomImpre = nomImpre.trim();
             System.out.println("La impresora configurada para facturas es (nomImpre) es:  " + nomImpre);
             
             PrintService service[] = PrinterJob.lookupPrintServices();
             System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
             for(int i=0; i<service.length;i++)
             {
                System.out.println("        "+service[i]. getName());
                if(service[i].getName().toUpperCase().equals(nomImpre.toUpperCase()))
                {
                    existeimp = true;
                    impresoraEncontrada = service[i];
                    System.out.println("La impresora encontrada es:  " + impresoraEncontrada.getName());
                }
             }
        }

        if(!existeimp)
                //enviar mensaje de error indicando que la impresoras para facturas no esta configurada en Windows  
                JOptionPane.showMessageDialog( this, "La impresora de facturas no esta configurada en Windows. Contacte al administrador del sistema",
                 "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );     
        
        //setSize(1024,768);
        //setVisible(true);
         
        
         if ( FactElecAct)
             jPanelFacAct.setVisible(false);
         else
         jPanelFacAct.setVisible(true);
           menos5boletos = false;        
        menos5Boletos();        
        if(menos5boletos)
                jtxt_fac_act.setForeground(new Color(255,0,0));
        else
            jtxt_fac_act.setForeground(new Color(0,0,0));
        String folio_fac = String.valueOf(facturas_ini);
        while(folio_fac.length() < 5) folio_fac = "0"+folio_fac;
        jtxt_fac_act.setText(facturas_prefijo+" " +folio_fac);
        //System.out.println("ALTO ANCHO"+this.getSize());
       
         
         */

        fillComboMetodoPago();
          
         Vector VparamSuc = facadeTMS.getParamTerminal("'P_FACT_ELEC_AMBITO','P_FACT_ELEC_REF'",terminal);           
         
         if (VparamSuc != null && VparamSuc.size() > 0)
           {
            Ambito        = ((Vector)VparamSuc.elementAt(0) ).elementAt(0).toString().trim();
            UnidadNegocio = ((Vector)VparamSuc.elementAt(1) ).elementAt(0).toString().trim();
            System.out.println("Ambito "+Ambito); 
            System.out.println("UnidadNegocio "+UnidadNegocio);
           }  
        else{
              JOptionPane.showMessageDialog(null, "Verifique los parámetros de configuración sucursal \n Empresa y Unidad de Negocio, con el Administrador del sistema.","Error",JOptionPane.ERROR_MESSAGE);
               return;   }
         
        jPanelFacAct.setVisible(false);
        jCmbCdVenta.requestFocusInWindow();
         
    }


    public void fillComboMetodoPago()
    {
        jCmbMetodoPago.removeAllItems();
        jCmbMetodoPago.addItem("Efectivo");
        jCmbMetodoPago.addItem("Tarjeta de Credito");
        jCmbMetodoPago.addItem("NO IDENTIFICADO");
     }

    public void barraEstado0(){
        //if(ban) {
            jtxt_codigo.setBackground(new Color(192,190,207));
            jlbl_barraEstado.setText("<html>  <font color=FF0000> F4</font> Salir de Facturar Boleto | <font color=FF0000> ARRIBA/ABAJO</font> Moverse entre Campos |   <font color=FF0000> F8 </font> Buscar por Nombre   |   <font color=FF0000> F7 </font> Ver Factura   | <font color=FF0000> F11 </font> Refoliar   | <font color=FF0000> F12 </font> Cancelar Factura | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Siguiente ventana</html>");
        /*}
        else
        {jlbl_barraEstado.setText("<html>  <font color=FF0000>        F4</font> Salir de Facturar Boleto | <font color=FF0000>        F5</font> Introducir Código de Barras |   <font color=FF0000> F11 </font> Refoliar de Facturas   | <font color=FF0000> F12 </font> Cancelar Factura | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
            
        }*/
    }
        
     /**
     * Establece el valor máximo por columna dependiendo de la información almacenada
     */
    public void resizeColumnasCorridas(){
        TableColumnModel columnModel = jtbl_boletos.getColumnModel();

           for (int col=0; col<jtbl_boletos.getColumnCount(); col++) 
           {
                int maxwidth = 0;            
                for (int row=0; row<jtbl_boletos.getRowCount(); row++) {
                    TableCellRenderer rend  = jtbl_boletos.getCellRenderer(row, col); 
                    Object value = jtbl_boletos.getValueAt (row, col); 
                    Component comp = rend.getTableCellRendererComponent (jtbl_boletos, value, false, false, row, col);
                    maxwidth = Math.max (comp.getPreferredSize().width, maxwidth); 
                } // for row

                TableColumn column = columnModel.getColumn (col);
                TableCellRenderer headerRenderer = column.getHeaderRenderer();
                if (headerRenderer == null)
                    headerRenderer = jtbl_boletos.getTableHeader().getDefaultRenderer();

                Object headerValue = column.getHeaderValue();
                Component headerComp = headerRenderer.getTableCellRendererComponent (jtbl_boletos, headerValue, false, false, 0, col);
                maxwidth = Math.max (maxwidth, headerComp.getPreferredSize().width);
                column.setPreferredWidth (maxwidth + 15);
           }
      }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbl_barraEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_boletos = new javax.swing.JTable();
        jlbl_codigo = new javax.swing.JLabel();
        jlbl_subtotal = new javax.swing.JLabel();
        jlbl_iva = new javax.swing.JLabel();
        jlbl_total = new javax.swing.JLabel();
        jtxt_subtotal = new javax.swing.JTextField();
        jtxt_iva = new javax.swing.JTextField();
        jtxt_total = new javax.swing.JTextField();
        jlbl_titulo2 = new javax.swing.JLabel();
        jlbl_titulo1 = new javax.swing.JLabel();
        jlbl_nombre = new javax.swing.JLabel();
        jlbl_calle = new javax.swing.JLabel();
        jlbl_int = new javax.swing.JLabel();
        jlbl_ext = new javax.swing.JLabel();
        jlbl_col = new javax.swing.JLabel();
        jlbl_cp = new javax.swing.JLabel();
        jlbl_mun = new javax.swing.JLabel();
        jlbl_edo = new javax.swing.JLabel();
        jlbl_rfc = new javax.swing.JLabel();
        jtxt_nombre = new tms_TextFields.JTextTextField();
        jtxt_rfc = new tms_TextFields.JTextTextField();
        jtxt_calle = new tms_TextFields.JTextTextField();
        jtxt_ext = new tms_TextFields.JTextTextField();
        jtxt_col = new tms_TextFields.JTextTextField();
        jtxt_cp = new tms_TextFields.JCuantityTextField();
        jtxt_mun = new tms_TextFields.JTextTextField();
        jtxt_edo = new tms_TextFields.JTextTextField();
        jtxt_int = new tms_TextFields.JTextTextField();
        jlbl_ciudad = new javax.swing.JLabel();
        jtxt_ciudad = new tms_TextFields.JTextTextField();
        jPanelFacAct = new javax.swing.JPanel();
        jtxt_fac_act = new javax.swing.JTextField();
        jlbl_fac_act = new javax.swing.JLabel();
        jlbl_folio = new javax.swing.JLabel();
        jntxt_folio = new tms_TextFields.JNumberTextField();
        jlbl_email = new javax.swing.JLabel();
        jtxt_email = new tms_TextFields.JTextTextField();
        jtxt_codigo = new tms_TextFields.JTextTextField();
        jlbl_codigo1 = new javax.swing.JLabel();
        jCmbCdVenta = new javax.swing.JComboBox();
        jLblEtiqueta = new javax.swing.JLabel();
        jlbl_ciudad1 = new javax.swing.JLabel();
        jlbl_ciudad2 = new javax.swing.JLabel();
        jCmbMetodoPago = new javax.swing.JComboBox();
        jtxt_NoCuenta = new tms_TextFields.JNumberTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });

        jlbl_barraEstado.setText("Revision!");

        jtbl_boletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbl_boletos.setModel(modelo);
        jtbl_boletos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_boletosFocusGained(evt);
            }
        });
        jtbl_boletos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_boletosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtbl_boletos);

        jlbl_codigo.setText("Referencia:");

        jlbl_subtotal.setText("Sub-Total:");

        jlbl_iva.setText("I.V.A.");

        jlbl_total.setText("Total: ");

        jtxt_subtotal.setEditable(false);
        jtxt_subtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_subtotal.setText("$");
        jtxt_subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_subtotalActionPerformed(evt);
            }
        });

        jtxt_iva.setEditable(false);
        jtxt_iva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_iva.setText("$");

        jtxt_total.setEditable(false);
        jtxt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_total.setText("$");

        jlbl_titulo2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jlbl_titulo2.setText("Ingreso de Datos del Cliente");
        jlbl_titulo2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jlbl_titulo2.setEnabled(false);
        jlbl_titulo2.setOpaque(true);

        jlbl_titulo1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jlbl_titulo1.setText("Ingreso de Boletos");
        jlbl_titulo1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jlbl_nombre.setText("Nombre Completo: ");
        jlbl_nombre.setEnabled(false);

        jlbl_calle.setText("Calle:");
        jlbl_calle.setEnabled(false);

        jlbl_int.setText("No. Interior:");
        jlbl_int.setEnabled(false);

        jlbl_ext.setText("No. Exterior:");
        jlbl_ext.setEnabled(false);

        jlbl_col.setText("Colonia:");
        jlbl_col.setEnabled(false);

        jlbl_cp.setText("C.P.");
        jlbl_cp.setEnabled(false);

        jlbl_mun.setText("Municipio:");
        jlbl_mun.setEnabled(false);

        jlbl_edo.setText("Estado:");
        jlbl_edo.setEnabled(false);

        jlbl_rfc.setText("R.F.C.");
        jlbl_rfc.setEnabled(false);

        jtxt_nombre.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_nombre.setEditable(false);
        jtxt_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_nombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_nombreFocusLost(evt);
            }
        });
        jtxt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_nombreKeyPressed(evt);
            }
        });

        jtxt_rfc.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_rfc.setEditable(false);
        jtxt_rfc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_rfcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_rfcFocusLost(evt);
            }
        });
        jtxt_rfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_rfcKeyPressed(evt);
            }
        });

        jtxt_calle.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_calle.setEditable(false);
        jtxt_calle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_calleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_calleFocusLost(evt);
            }
        });
        jtxt_calle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_calleKeyPressed(evt);
            }
        });

        jtxt_ext.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_ext.setEditable(false);
        jtxt_ext.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_extFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_extFocusLost(evt);
            }
        });
        jtxt_ext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_extKeyPressed(evt);
            }
        });

        jtxt_col.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_col.setEditable(false);
        jtxt_col.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_colFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_colFocusLost(evt);
            }
        });
        jtxt_col.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_colKeyPressed(evt);
            }
        });

        jtxt_cp.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_cp.setEditable(false);
        jtxt_cp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_cpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_cpFocusLost(evt);
            }
        });
        jtxt_cp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_cpKeyPressed(evt);
            }
        });

        jtxt_mun.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_mun.setEditable(false);
        jtxt_mun.setNextFocusableComponent(jlbl_edo);
        jtxt_mun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_munFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_munFocusLost(evt);
            }
        });
        jtxt_mun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_munKeyPressed(evt);
            }
        });

        jtxt_edo.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_edo.setEditable(false);
        jtxt_edo.setNextFocusableComponent(jtxt_email);
        jtxt_edo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_edoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_edoFocusLost(evt);
            }
        });
        jtxt_edo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_edoKeyPressed(evt);
            }
        });

        jtxt_int.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_int.setEditable(false);
        jtxt_int.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_intFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_intFocusLost(evt);
            }
        });
        jtxt_int.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_intKeyPressed(evt);
            }
        });

        jlbl_ciudad.setText("Ciudad:");
        jlbl_ciudad.setEnabled(false);

        jtxt_ciudad.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_ciudad.setEditable(false);
        jtxt_ciudad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_ciudadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_ciudadFocusLost(evt);
            }
        });
        jtxt_ciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_ciudadKeyPressed(evt);
            }
        });

        jPanelFacAct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtxt_fac_act.setEditable(false);
        jtxt_fac_act.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtxt_fac_act.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxt_fac_act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_fac_actActionPerformed(evt);
            }
        });

        jlbl_fac_act.setText("Factura Actual:");

        org.jdesktop.layout.GroupLayout jPanelFacActLayout = new org.jdesktop.layout.GroupLayout(jPanelFacAct);
        jPanelFacAct.setLayout(jPanelFacActLayout);
        jPanelFacActLayout.setHorizontalGroup(
            jPanelFacActLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelFacActLayout.createSequentialGroup()
                .addContainerGap()
                .add(jlbl_fac_act)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_fac_act, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelFacActLayout.setVerticalGroup(
            jPanelFacActLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelFacActLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelFacActLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlbl_fac_act)
                    .add(jtxt_fac_act, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jlbl_folio.setText("Folio Preimpreso:");

        jntxt_folio.setBackground(new java.awt.Color(255, 255, 204));
        jntxt_folio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jntxt_folio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jntxt_folioActionPerformed(evt);
            }
        });
        jntxt_folio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jntxt_folioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jntxt_folioFocusLost(evt);
            }
        });
        jntxt_folio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jntxt_folioKeyPressed(evt);
            }
        });

        jlbl_email.setText("Email:");
        jlbl_email.setEnabled(false);

        jtxt_email.setEditable(false);
        jtxt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_emailFocusLost(evt);
            }
        });
        jtxt_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_emailKeyPressed(evt);
            }
        });

        jtxt_codigo.setBackground(new java.awt.Color(255, 255, 204));
        jtxt_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_codigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxt_codigoFocusLost(evt);
            }
        });
        jtxt_codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_codigoKeyPressed(evt);
            }
        });

        jlbl_codigo1.setText("Origen Corrida:");

        jCmbCdVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbCdVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCmbCdVentaFocusGained(evt);
            }
        });
        jCmbCdVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCmbCdVentaKeyReleased(evt);
            }
        });

        jLblEtiqueta.setText("jLabel1");

        jlbl_ciudad1.setText("No Cuenta:");
        jlbl_ciudad1.setEnabled(false);

        jlbl_ciudad2.setText("Método Pago:");
        jlbl_ciudad2.setEnabled(false);

        jCmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmbMetodoPago.setToolTipText("Seleccione método de pago");
        jCmbMetodoPago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbMetodoPagoItemStateChanged(evt);
            }
        });
        jCmbMetodoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCmbMetodoPagoKeyPressed(evt);
            }
        });

        jtxt_NoCuenta.setToolTipText("No de cuanta si el Metodo de pago no es efectivo");
        jtxt_NoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_NoCuentaActionPerformed(evt);
            }
        });
        jtxt_NoCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_NoCuentaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(56, 56, 56)
                .add(jlbl_titulo2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 343, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(597, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jlbl_subtotal)
                            .add(jlbl_iva)
                            .add(jlbl_total))
                        .add(14, 14, 14)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jtxt_total)
                            .add(jtxt_iva)
                            .add(jtxt_subtotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jlbl_codigo1)
                            .add(jlbl_codigo))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 756, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(12, 12, 12)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jCmbCdVenta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 496, Short.MAX_VALUE)
                                        .add(jLblEtiqueta)
                                        .add(32, 32, 32))
                                    .add(layout.createSequentialGroup()
                                        .add(jtxt_codigo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(67, 67, 67)
                                        .add(jlbl_folio)
                                        .add(15, 15, 15)
                                        .add(jntxt_folio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 300, Short.MAX_VALUE)))))))
                .add(117, 117, 117))
            .add(layout.createSequentialGroup()
                .add(56, 56, 56)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jlbl_email)
                    .add(jlbl_nombre)
                    .add(jlbl_rfc)
                    .add(jlbl_calle)
                    .add(jlbl_ext)
                    .add(jlbl_mun)
                    .add(jlbl_edo)
                    .add(jlbl_col))
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .add(jtxt_rfc, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .add(jtxt_calle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jtxt_mun, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                    .add(jtxt_col, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                    .add(jtxt_ext, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                    .add(jtxt_edo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                                .add(83, 83, 83)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jlbl_cp)
                                    .add(jlbl_ciudad)
                                    .add(jlbl_int)
                                    .add(jlbl_ciudad1)
                                    .add(jlbl_ciudad2)))
                            .add(layout.createSequentialGroup()
                                .add(jtxt_email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(180, 180, 180)))
                        .add(34, 34, 34)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jCmbMetodoPago, 0, 149, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jtxt_cp, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jtxt_int, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jtxt_ciudad, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jtxt_NoCuenta, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
                .add(137, 137, 137))
            .add(layout.createSequentialGroup()
                .add(43, 43, 43)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                .add(98, 98, 98))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jlbl_titulo1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 351, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 257, Short.MAX_VALUE)
                .add(jPanelFacAct, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelFacAct, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_titulo1))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jlbl_codigo1)
                            .add(jCmbCdVenta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 48, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jntxt_folio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_folio)
                            .add(jlbl_codigo)
                            .add(jtxt_codigo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(22, 22, 22))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLblEtiqueta)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlbl_subtotal)
                    .add(jtxt_subtotal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlbl_iva)
                    .add(jtxt_iva, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlbl_total)
                    .add(jtxt_total, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_titulo2)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlbl_rfc)
                    .add(jtxt_rfc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlbl_nombre)
                    .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jtxt_calle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_calle))
                .add(9, 9, 9)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlbl_ext)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jtxt_int, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_int))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jtxt_cp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_cp))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jtxt_ciudad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_ciudad))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jtxt_NoCuenta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_ciudad1)))
                    .add(layout.createSequentialGroup()
                        .add(jtxt_ext, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jtxt_col, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_col))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jtxt_mun, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_mun))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jtxt_edo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jlbl_edo))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jlbl_email)
                        .add(jtxt_email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jlbl_ciudad2)
                    .add(jCmbMetodoPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCmbCdVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCmbCdVentaFocusGained
// TODO add your handling code here:
      
    }//GEN-LAST:event_jCmbCdVentaFocusGained

    private void jtxt_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_codigoFocusLost
// TODO add your handling code here:
   jtxt_codigo.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_codigoFocusLost

    private void jtxt_codigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_codigoFocusGained
// TODO add your handling code here:
   jtxt_codigo.setBackground(new Color(192,190,207));
    }//GEN-LAST:event_jtxt_codigoFocusGained

    private void jCmbCdVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCmbCdVentaKeyReleased
// TODO add your handling code here:
       
    if (evt.getKeyCode() == evt.VK_RIGHT)
     {  jtxt_codigo.requestFocus();
        barraEstado0();
     }
     else if (evt.getKeyCode() == evt.VK_LEFT)
     { jntxt_folio.requestFocus();
        barraEstado0();
     }
     else  if(evt.getKeyCode() == evt.VK_F4 || evt.getKeyCode() == evt.VK_F8 ||evt.getKeyCode() == evt.VK_F7 || evt.getKeyCode() == evt.VK_F11 || evt.getKeyCode() == evt.VK_F12    )
     {
        System.out.println("Desde combo va a evento");
        evento(evt);
     }
    }//GEN-LAST:event_jCmbCdVentaKeyReleased

    private void jtxt_codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_codigoKeyPressed
// TODO add your handling code here:
      if (evt.getKeyCode()== evt.VK_DOWN  )
      {   jtxt_codigo.setText("");
          jntxt_folio.requestFocusInWindow();
           barraEstado0();
      }
      else if (evt.getKeyCode()== evt.VK_UP )
        {   jCmbCdVenta.requestFocusInWindow();
              barraEstado0();
        }
      else  evento(evt);
    }//GEN-LAST:event_jtxt_codigoKeyPressed

    private void jtxt_fac_actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_fac_actActionPerformed
// TODO add your handling code here:
       
    }//GEN-LAST:event_jtxt_fac_actActionPerformed

    private void jtxt_subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_subtotalActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jtxt_subtotalActionPerformed

    private void jtxt_emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_emailKeyPressed
   if(evt.getKeyCode() == evt.VK_DOWN)
            jCmbMetodoPago.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
               jtxt_NoCuenta.requestFocus();
        otroEvento(evt);
        
    }//GEN-LAST:event_jtxt_emailKeyPressed

    private void jtxt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_emailFocusLost
     jtxt_email.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jtxt_emailFocusLost

    private void jtxt_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_emailFocusGained
       jtxt_email.setBackground(new Color(192,190,207));
          
    }//GEN-LAST:event_jtxt_emailFocusGained

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
// TODO add your handling code here:
    }//GEN-LAST:event_formAncestorResized

    private void jntxt_folioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jntxt_folioKeyPressed
        //barraEstado0();

        if (evt.getKeyCode() == evt.VK_UP )
          { jntxt_folio.setText("");
            jtxt_codigo.requestFocus();
            barraEstado0();
        }
       
       if(  evt.getKeyCode() == evt.VK_DOWN   ){  // || (evt.getKeyCode() == evt.VK_UP)){
            
            //jtxt_codigo.requestFocus();
            jCmbCdVenta.requestFocus();
            barraEstado0();
        }
        if(evt.getKeyCode() == evt.VK_1 && evt.isControlDown()){
                setEventoTeclado(evt);
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }

        if(evt.getKeyCode() == evt.VK_2 && evt.isControlDown()){
                try {this.setIcon(true);} catch (PropertyVetoException ex) { ; }
                return;
        }
        evento(evt);
    }//GEN-LAST:event_jntxt_folioKeyPressed

    private void jntxt_folioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jntxt_folioFocusGained
        jntxt_folio.setBackground(new Color(192,190,207));
        //barraEstado0();
    }//GEN-LAST:event_jntxt_folioFocusGained

    private void jntxt_folioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jntxt_folioFocusLost
        jntxt_folio.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jntxt_folioFocusLost

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        barraEstado0();
        //jtxt_codigo.requestFocusInWindow();
        jCmbCdVenta.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    private void jtxt_ciudadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_ciudadKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_edo.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_mun.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_ciudadKeyPressed

    private void jtxt_ciudadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_ciudadFocusLost
        jtxt_ciudad.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_ciudadFocusLost

    private void jtxt_ciudadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_ciudadFocusGained
        jtxt_ciudad.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_ciudadFocusGained

    private void jtxt_edoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_edoKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_NoCuenta.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_ciudad.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_edoKeyPressed

    private void jtxt_munKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_munKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_ciudad.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_cp.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_munKeyPressed

    private void jtxt_edoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_edoFocusLost
        jtxt_edo.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_edoFocusLost

    private void jtxt_munFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_munFocusLost
        jtxt_mun.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_munFocusLost

    private void jtxt_edoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_edoFocusGained
        jtxt_edo.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_edoFocusGained

    private void jtxt_munFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_munFocusGained
        jtxt_mun.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_munFocusGained

    private void jtxt_cpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_cpKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_mun.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_col.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_cpKeyPressed

    private void jtxt_cpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_cpFocusLost
        jtxt_cp.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_cpFocusLost

    private void jtxt_cpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_cpFocusGained
        jtxt_cp.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_cpFocusGained

    private void jtxt_colKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_colKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_cp.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_int.requestFocus();
    }//GEN-LAST:event_jtxt_colKeyPressed

    private void jtxt_colFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_colFocusLost
        jtxt_col.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_colFocusLost

    private void jtxt_colFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_colFocusGained
        jtxt_col.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_colFocusGained

    private void jtxt_intKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_intKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_col.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_ext.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_intKeyPressed

    private void jtxt_intFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_intFocusLost
        jtxt_int.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_intFocusLost

    private void jtxt_intFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_intFocusGained
        jtxt_int.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_intFocusGained

    private void jtxt_extKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_extKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_int.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_calle.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_extKeyPressed

    private void jtxt_extFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_extFocusLost
        jtxt_ext.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_extFocusLost

    private void jtxt_extFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_extFocusGained
        jtxt_ext.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_extFocusGained

    private void jtxt_calleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_calleKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_ext.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_nombre.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_calleKeyPressed

    private void jtxt_calleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_calleFocusLost
       jtxt_calle.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_calleFocusLost

    private void jtxt_calleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_calleFocusGained
        jtxt_calle.setBackground(new Color(192,190,207));
        barraEstado();
    }//GEN-LAST:event_jtxt_calleFocusGained

    private void jtxt_rfcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_rfcKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_nombre.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jCmbMetodoPago.requestFocus();
        if(evt.getKeyCode() == evt.VK_F3)
        {
            //Buscar el RFC de la persona!
            //System.out.println("Voy a buscar por RFC");
            if(jtxt_rfc.getText().equals(""))
                JOptionPane.showMessageDialog( this, "Debe llenar el campo RFC. Intente de nuevo.",
                        "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
            else {
                Vector cliente = new Vector();
                cliente = RepControlFacade.buscarClienteRFC(jtxt_rfc.getText());
                
                if(cliente != null) 
                    colocarInformacion(cliente, false);
                else {
                   JOptionPane.showMessageDialog( this, "No existe registro con ese RFC. Intente con otro diferente.",
                            "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                   limpiarCajitas();
                   existe = 0;
                   barraEstado();
                   jtxt_rfc.requestFocus();
                }
            }
        }
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_rfcKeyPressed

    private void jtxt_rfcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_rfcFocusLost
        jtxt_rfc.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_rfcFocusLost

    private void jtxt_rfcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_rfcFocusGained
        jtxt_rfc.setBackground(new Color(192,190,207));
        barraEstado2();
    }//GEN-LAST:event_jtxt_rfcFocusGained

    private void jtxt_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_nombreKeyPressed
        if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_calle.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_rfc.requestFocus();
        if(evt.getKeyCode() == evt.VK_F3)
        {
            //Buscar el RFC de la persona!
            //System.out.println("Voy a buscar por RFC");
            if(jtxt_nombre.getText().equals(""))
                JOptionPane.showMessageDialog( this, "Debe llenar el campo Nombre Completo. Intente de nuevo.",
                        "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
            else {
                Vector cliente = new Vector();
                cliente = RepControlFacade.buscarClienteNombre(jtxt_nombre.getText());
                if(cliente != null)
                    colocarInformacion(cliente, true);
                else {
                   JOptionPane.showMessageDialog(this, "No existe registro con ese RFC. Intente con otro diferente.",
                            "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE); 
                   limpiarCajitas();
                }
            }
        }
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_nombreKeyPressed

    private void jtxt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_nombreFocusLost
        jtxt_nombre.setBackground(new Color(255,255,204));
    }//GEN-LAST:event_jtxt_nombreFocusLost

    private void jtxt_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_nombreFocusGained
        jtxt_nombre.setBackground(new Color(192,190,207));
        barraEstado2();
    }//GEN-LAST:event_jtxt_nombreFocusGained

public void barraEstado()
{
    if(existe == 1)
            jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Hacer Otra Busqueda | <font color=FF0000>  ARRIBA/ABAJO </font> Moverse entre Registros | <font color=FF0000> F5 </font> Modificar Información | <font color=FF0000> ENTER </font> Continuar </html>");
    if(existe == 0)
            jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Regresar a Boletos | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Registros | <font color=FF0000> F5 </font> Agregar Información de Cliente | <font color=FF0000> ENTER </font> Continuar </html>");
    if(existe > 1)
           jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Ingresar Otra Busqueda | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Registros | <font color=FF0000> F9 </font> Guardar Información de Cliente | <font color=FF0000> ENTER </font> Continuar </html>");
    }

public void barraEstado2()
{
    if(existe == 1)
                jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Hacer Otra Busqueda | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Registros | <font color=FF0000> F3 </font> Buscar | <font color=FF0000> F5 </font> Modificar Información | <font color=FF0000> ENTER </font> Continuar </html>");
    if(existe == 0)
                jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Regresar a Boletos | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Registros | <font color=FF0000> F3 </font> Buscar | <font color=FF0000> F5 </font> Agregar Información de Cliente | <font color=FF0000> ENTER </font> Continuar </html>");
    if(existe > 1)
               jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Ingresar Otro Boleto | <font color=FF0000> ARRIBA/ABAJO </font> Moverse entre Registros | <font color=FF0000> F3 </font> Buscar | <font color=FF0000> F9 </font> Guardar Información de Cliente | <font color=FF0000> ENTER </font> Continuar </html>");
}
    
    
    private void jtbl_boletosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_boletosFocusGained
        jlbl_barraEstado.setText("<html> <font color=FF0000>ESCAPE</font> Ingresar Otro Boleto | <font color=FF0000>ABAJO/ARRIBA</font> Moverse entre Registros  | <font color=FF0000>Supr</font> Eliminar Boleto | <font color=FF0000> ENTER </font> Facturar</html>");
    }//GEN-LAST:event_jtbl_boletosFocusGained

    private void jtbl_boletosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_boletosKeyPressed
        if(evt.getKeyCode() == evt.VK_DELETE){
            //System.out.println("Si borre un elemento "+jtbl_boletos.getSelectedRow());
            //borrar de todas las columnas
            eliminar();
        }
       
        if(evt.getKeyCode() == evt.VK_ESCAPE  ){
            System.out.println("Escape en tabla");
            jntxt_folio.setText("");
            jntxt_folio.setEditable(true);
            jtxt_codigo.setText("");
            jtxt_codigo.setEditable(true);
            if(salida[0].size() > 0)
                jtbl_boletos.removeRowSelectionInterval(0,0);
            barraEstado0();
            //jtxt_codigo.requestFocus();
            jCmbCdVenta.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_ENTER) {
            if(numeroBoletos > 0) {
                on_offBoleto(false);
                on_offCliente(false);
                jlbl_titulo2.setEnabled(true);
                jlbl_rfc.setEnabled(true);
                jtxt_rfc.setEditable(true);
                jlbl_nombre.setEnabled(true);
                jtxt_nombre.setEditable(true);
                /*jlbl_amaterno.setEnabled(true);
                jtxt_amaterno.setEditable(true);
                jlbl_apaterno.setEnabled(true);
                jtxt_apaterno.setEditable(true);*/
                existe = 0;
                barraEstado();
                jtxt_rfc.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No hay Boletos para Facturar",
                        "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                nuevaCaptura();
            }
                
        }
    }//GEN-LAST:event_jtbl_boletosKeyPressed

    private void jtxt_NoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_NoCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_NoCuentaActionPerformed

    private void jCmbMetodoPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbMetodoPagoItemStateChanged
        // TODO add your handling code here:
        ActivaNoCuenta();
    }//GEN-LAST:event_jCmbMetodoPagoItemStateChanged

    private void jtxt_NoCuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_NoCuentaKeyPressed
      if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_email.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)  
            jtxt_edo.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jtxt_NoCuentaKeyPressed

    private void jCmbMetodoPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCmbMetodoPagoKeyPressed
      if(evt.getKeyCode() == evt.VK_DOWN)
            jtxt_rfc.requestFocus();
        if(evt.getKeyCode() == evt.VK_UP)
            jtxt_email.requestFocus();
        otroEvento(evt);
    }//GEN-LAST:event_jCmbMetodoPagoKeyPressed

    private void jntxt_folioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jntxt_folioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jntxt_folioActionPerformed

    public void ActivaNoCuenta()
    {

        if (jCmbMetodoPago.getItemCount() >0)
         {  
            String metpag=jCmbMetodoPago.getSelectedItem()+"";
            //System.out.println("Metodo Pago "+metpag+"--");
            if (!metpag.equalsIgnoreCase("Efectivo") && !metpag.equalsIgnoreCase("NO IDENTIFICADO"))
            {   
                jtxt_NoCuenta.setEditable(true);
                jtxt_NoCuenta.setRequestFocusEnabled(true);

             }
            else
            {
                
                jtxt_NoCuenta.setText("");
                jtxt_NoCuenta.setEditable(false);
             }
        }
    }
    public void eliminar()
    {
        //double valor = Double.valueOf(salida[jtbl_boletos.getSelectedRow()].get(6).toString());
        total = 0;
        for (int i = 0; i < 14; i++)
                salida[i].removeElementAt(jtbl_boletos.getSelectedRow());
           
            datos = new String[salida[0].size()][12];
            for(int i = 0; i < 11; i++) // Solo se muestran 9 elementros en pantalla
                for(int j =0; j < salida[0].size(); j++) {
                    datos[j][i] = salida[i].get(j).toString();
                    if(i == 6)
                        total = total + Double.parseDouble(salida[i].get(j).toString());
                }

            // Metodo de Pago
           for(int j =0; j < salida[0].size(); j++)  //Agrega metodo de pago
                                        datos[j][10] = salida[13].get(j).toString();

            modelo.setDataVector(datos, encabezados);
            resizeColumnasCorridas();
           /* if (datos.length ==0)
            {
                this.NoCuenta = "";
                this.MetodoPago="NO IDENTIFICADO";

            }
            */
            validaMetpago();

            DecimalFormat df = new DecimalFormat("#,###,###,###.00");
            iva = total * 0.0;
            subtotal = total - iva;
            
            jtxt_subtotal.setText(String.valueOf(df.format(subtotal)));
            jtxt_iva.setText("0.00");
            jtxt_total.setText(String.valueOf(df.format(total)));
            numeroBoletos--;
            productos ="";
    }
        
    public void evento(java.awt.event.KeyEvent evt)
    {        
        //boolean invalido = false;
 
        
        if((evt.getKeyCode() == evt.VK_F12)&&(numeroBoletos == 0)){
            JDLG_CancelacionFactura cancelar = new JDLG_CancelacionFactura(null,"PCENTRAL_LINK.ESTRELLAROJA.COM.MX", this.usuario,
                                                PrefijoFolioFact, TerminalFact,this.RepControlFacade );
            cancelar.setLocationRelativeTo(this);
            cancelar.setModal(true);
            cancelar.setSize(480,150);
            cancelar.setVisible(true); 
        }
        
         if((evt.getKeyCode() == evt.VK_F7)&&(numeroBoletos == 0)){
            JDLG_ViewFactura Vista = new JDLG_ViewFactura(null,"PCENTRAL_LINK.ESTRELLAROJA.COM.MX", this.usuario,
                                                PrefijoFolioFact, TerminalFact, RutAbs, RutAlias,this.RepControlFacade );
            Vista.setLocationRelativeTo(this);
            Vista.setModal(true);
            Vista.setSize(480,150); 
            Vista.setVisible(true); 
        }
        
        if (jtxt_codigo.getText().equals("") &&jntxt_folio.getText().equals("")&&(evt.getKeyCode() == evt.VK_ENTER) && (numeroBoletos > 0)){
            on_offBoleto(true);
            jtbl_boletos.setRowSelectionInterval(0,0);
            jtxt_codigo.setEditable(false);
            jtbl_boletos.requestFocus();
            jntxt_folio.setEditable(false);
        }
        else        
            if (evt.getKeyCode() == evt.VK_ENTER && ( jtxt_codigo.getText().trim().length()>0 || jntxt_folio.getText().trim().length()>0  ) )
            {
             this.setCursor(busyCursor);
             
              buscarBoleto();
             this.setCursor(defaultCursor);
            }                
        
        
   if(evt.getKeyCode() == evt.VK_F8){//&& (numeroBoletos == 0)) {
       JDLG_BusquedaNombre busquedaNombre = new JDLG_BusquedaNombre(null, true, cosa, this);
       busquedaNombre.setLocationRelativeTo(this);
       busquedaNombre.setVisible(true);
   }
        
    if((evt.getKeyCode() == evt.VK_F4) && (numeroBoletos == 0)) {
        int result = JOptionPane.showOptionDialog(this, "¿Desea cerrar Facturar Boletos?",
                "TMS - Facturar Boleto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(result == JOptionPane.YES_OPTION)
            this.dispose();
    }
        
    if(evt.getKeyCode() == evt.VK_ESCAPE  ){
            System.out.println(" Nueva Captura");
            nuevaCaptura();
            //limpiarCajitas();
    }
        
    if((evt.getKeyCode() == evt.VK_F6) && (jtxt_codigo.hasFocus())){
            jntxt_folio.requestFocus();
    }
        
    if((evt.getKeyCode() == evt.VK_F11)&& (numeroBoletos == 0)){
        if (!FactElecAct)
        {
        JDLG_Refoliar refoliar = new JDLG_Refoliar(null, this.facturas_fin,this.facturas_ini, true, this.terminal);
        refoliar.setLocationRelativeTo(this);
        refoliar.setModal(true);
        refoliar.setSize(600,180);
        refoliar.setVisible(true);
        facturas_fin = refoliar.fol_fin;
        facturas_ini = refoliar.fol_ini;
        menos5boletos = false;        
        menos5Boletos();
        if(menos5boletos)
            jtxt_fac_act.setForeground(new Color(255,0,0));
        else
            jtxt_fac_act.setForeground(new Color(0,0,0));
        String folio_fac = String.valueOf(facturas_ini);
        while(folio_fac.length() < 5) folio_fac = "0"+folio_fac;
        jtxt_fac_act.setText(facturas_prefijo+" " +folio_fac);
        }
    }
  }
   
    public Vector DoFactElect(String Folios)
    {
      // Genera Factura Electronica si esta activa


        Vector VdatosFE = GetDatosFacElec(Folios);
          if (VdatosFE == null ||  VdatosFE.elementAt(0) == null || VdatosFE.elementAt(1) == null ||
                                  VdatosFE.elementAt(0).toString().length() <=0 || VdatosFE.elementAt(1).toString().length() <=0 )
          {
              JOptionPane.showMessageDialog(this,"La Factura No pudo ser generada!.","TMS00 - Facturas",JOptionPane.ERROR_MESSAGE);
              return  null;
          }
        productos = "";
       return VdatosFE  ;
       
    }
      
    
       
    public Vector  GetDatosFacElec(String Folios)
    {
        Vector   VFactElec= new Vector();
        String LlaveFactElec = "";
        Hashtable  HTDatosFactura = new Hashtable();
        String RFCReceptor  = jtxt_rfc.getText().trim().replaceAll(" ","");
               RFCReceptor = RFCReceptor.replaceAll("-","");
               RFCReceptor = RFCReceptor.replaceAll("_","");  
       
        String NombreReceptor     = jtxt_nombre.getText().trim(); 
        String Pais               = "México";
        String NoExt = jtxt_ext.getText().trim();   
        String NoInt = (jtxt_int.getText() != null ?jtxt_int.getText().trim():"");   
         
        String  cp = jtxt_cp.getText() == null ||jtxt_cp.getText().length() <= 0?"72000":jtxt_cp.getText().trim(); 

        String  Folioss = Folios.substring(1,Folios.length());

        System.out.println(" Folioss  "+Folioss);
        int longitudNC=jtxt_NoCuenta.getText().trim().length();
        String nc ="";
         if (longitudNC >=4)
             nc = jtxt_NoCuenta.getText().trim().substring(longitudNC - 4, longitudNC);
         else
             nc = "";
        String ReceptorDatos="|"+"||TMS||"+
                             "|" +round(Double.parseDouble(jtxt_total.getText().trim()),2)+
                             "|0" +
                             "|0"+
                             "|0"+
                             "|0"+
                             "|0"+
                             "|"+round(Double.parseDouble(jtxt_total.getText().trim()),2)+
                             "|"+ NombreReceptor+
                             "|" +RFCReceptor+
                             "|" +"Boleto(s) de autobús con folio(s) "+Folioss+
                             "|"+
                             "|"+
                             "|"+ addCliente.calle.trim()+
                             "|"+NoExt+"|"+NoInt+      // Calle, No ext, No Int
                             "|"+ jtxt_col.getText().trim()+
                             "|"+cp+
                             "|"+jtxt_mun.getText().trim()+  //Localidad
                             "|"+jtxt_edo.getText().trim()+
                             "|"+Pais+
                             "|||A"+
                             "|"+jtxt_email.getText().replace("|", "^")+
                             "|||BOLETO"+
                             "|"+jCmbMetodoPago.getSelectedItem().toString()+
                             "|"+NoCuenta+
                             "|No Aplica"+
                             "|MOS"+
                             "|ingreso||1000"+usuario+
                             "|"+this.terminal+
                             "|";
     System.out.println("ReceptorDatos  "+ReceptorDatos);
     HTDatosFactura.put("RECEPTOR",ReceptorDatos);
     HTDatosFactura.put("MODO","T");
     HTDatosFactura.put("TIPOFACTURA","MOS");
     HTDatosFactura.put("CLIENTEID",-1);
     HTDatosFactura.put("MONTO",round(Double.parseDouble(jtxt_total.getText().trim()),2));
     HTDatosFactura.put("USUARIO",("1000"+usuario));

        /*
        String ReceptorDatos="|" +RFCReceptor+"|"+NombreReceptor+
                             "|"+jtxt_calle.getText().trim()+"|"+NoExt+"|"+NoInt+
                             "|"+jtxt_col.getText().trim()+
                             "|"+jtxt_mun.getText().trim()+  // localidad
                             "|"+                               // Referencia
                             "|"+jtxt_mun.getText().trim()+
                             "|"+jtxt_edo.getText().trim()+
                             "|"+Pais+"|"+cp+
                             "|"+jtxt_email.getText().trim();


        
     HTDatosFactura.put("RECEPTOR",ReceptorDatos);
        
     //Date   Fecha            = toufacturasFacade.getSysdate();	 
     double Descuento        = 0;
     
     HTDatosFactura.put("RFC_EMISOR","|" +RFCTMS+"|"+RSTMS+"|"+Ambito+"|"+ UnidadNegocio+"|"+UnidadNegocio+"|||||||");
     
   //  HTDatosFactura.put("SUCURSAL",this.sucursaldesc);
     HTDatosFactura.put("FORMA_PAGO","PAGO EN UNA SOLA EXHIBICION");     //Una Sola Exhibición
     HTDatosFactura.put("CONDICION_PAGO","Contado"); 
    // HTDatosFactura.put("METODO_PAGO","Efectivo");   // Dato para anexo 20
    HTDatosFactura.put("METODO_PAGO",jCmbMetodoPago.getSelectedItem().toString());   // Dato para anexo 20
    int longitudNC=jtxt_NoCuenta.getText().trim().length();  
     if (longitudNC >=4)
         HTDatosFactura.put("NO_CUENTA", jtxt_NoCuenta.getText().trim().substring(longitudNC - 4, longitudNC));   // Dato para anexo 20
     else
         HTDatosFactura.put("NO_CUENTA", "");
     HTDatosFactura.put("TIPO_COMPROBANTE","ingreso");      
     HTDatosFactura.put("MONEDA","MXP"); // 
     HTDatosFactura.put("IVA","16");       
       
     HTDatosFactura.put("SUBTOTAL",round(Double.parseDouble(jtxt_subtotal.getText().trim()),2));
     HTDatosFactura.put("DES_FAC","0");
     HTDatosFactura.put("MOT_DES","Sin Descuento");
     HTDatosFactura.put("TOTAL",round(Double.parseDouble(jtxt_total.getText().trim()),2));
     
    // Impuestos
     
     HTDatosFactura.put("TIMPUESTOS_RET","0");
     HTDatosFactura.put("TIMPUESTOS_TRAS","0");
      
     //Retenciones  
    HTDatosFactura.put("TIPO_IMPUESTO","IVA");
    HTDatosFactura.put("TIMPORTE_BASE_RET","0"); 
    HTDatosFactura.put("TASA_RET","ISR");
    HTDatosFactura.put("IMPORTE_RET","0");
    
    HTDatosFactura.put("RUTABS",RutAbs);
    HTDatosFactura.put("RUTALIAS",RutAlias);
    
    // IVA|10|\nISR|16|\n

   // Traslados
    HTDatosFactura.put("TIMPUESTO_TRAS","");
    HTDatosFactura.put("IMPORTEB_TRAS","0");
    HTDatosFactura.put("TASA_TRAS","");
    HTDatosFactura.put("IMPORTE_TRAS","0");


     
      Vector vlf = new Vector();
     
     String  Folioss = Folios.substring(1,Folios.length());
     
     System.out.println(" Folioss  "+Folioss);
     
     String  Lineas ="10|No Aplica|"+
                           "Boleto(s) de autobús con folio(s) "+Folioss+ //noIdentificacion
                         "|1"+//lista_asociados.get(i).getCotizacionId().getCotizacionLoteId().get CotizacionLoteId(). getNoUnidades()+   // Cantidad
                         "|"+round(Double.parseDouble(jtxt_subtotal.getText().trim()),2)+// Valor Unitario
                         "|0|0"+ //  Descuento  %descuento
                         "|1"+  // no de linea 
                         "|"+round(Double.parseDouble(jtxt_total.getText().trim()),2)+    // Total
                         "|0"+   //porcentajeIVA
                         "|0"+   //montoIVA
                         "|0"+ //porcentajeRetencionIva
                         "|0"+ //montoRetencionIva
                         "|0"+//porcentajeRetencionIsr
                         "|0"+//montoRetencionIva
                         "|\\n"; // importe
      
      
       System.out.println("Agregando la clase para generar la factura electronica");  //lineasfacturas
       if(Lineas != null && Lineas.length() >0)  
       {
         *
         */
          for(int y=0;y<jtbl_boletos.getRowCount();y++)
                productos = productos+"^|BOLETO|"+jtbl_boletos.getValueAt(y,11).toString();

          this.setCursor(busyCursor); 
           FacturaElectronica FactElec = new FacturaElectronica(HTDatosFactura,"",cosa,
                                                            this.getContentPane(),"Facturas - TMS",productos,this.RepControlFacade);
         LlaveFactElec= FactElec.ClaveFactElect;
         String RutPDF= FactElec.RutaPDF;
         
         VFactElec.addElement(LlaveFactElec);
         VFactElec.addElement(RutPDF);
         this.setCursor(defaultCursor); 
         
//       }
//       else
//           JOptionPane.showMessageDialog(this, "Lo datos de la factura no estan completos, falta la descripción de la lineas.","VENTOUR -Facturas",JOptionPane.INFORMATION_MESSAGE);
     return VFactElec;
  }
  
 public  double round(double val, int places) { 
     long factor = (long)Math.pow(10,places); 

     // Shift the decimal the correct number of places 
     // to the right. 
     val = val * factor; 

     // Round to the nearest integer. 
     long tmp = Math.round(val); 

     // Shift the decimal the correct number of places 
     // back to the left. 
     return (double)tmp / factor; 
  } 
    
    private void nuevaCaptura() {
        barraEstado0();
        datos = null;
        salida = null;
        numeroBoletos = 0;
        total = 0.0;
        subtotal = 0.0;
        iva = 0.0;
        jtxt_total.setText("$");
        jtxt_subtotal.setText("$");
        jtxt_iva.setText("$");
        salida = new Vector[15];
        salida[0] = new Vector();
        salida[1] = new Vector();
        salida[2] = new Vector();
        salida[3] = new Vector();
        salida[4] = new Vector();
        salida[5] = new Vector();
        salida[6] = new Vector();
        salida[7] = new Vector();
        salida[8] = new Vector();
        salida[9] = new Vector();
        salida[10] = new Vector();
        salida[11] = new Vector();
        salida[12] = new Vector();
        salida[13] = new Vector();
        salida[14] = new Vector();
        modelo.setDataVector(datos, encabezados);
        resizeColumnasCorridas();
        jtxt_codigo.setText("");
        jntxt_folio.setText("");
        jtxt_codigo.setEditable(true);
        jntxt_folio.setEditable(true);
        barraEstado0();
        //jtxt_codigo.requestFocus();
        jCmbCdVenta.requestFocus();
    }
//  
    private void on_offCliente (boolean status) {      
        jlbl_titulo2.setEnabled(status);
        jlbl_titulo2.setOpaque(!status);
        jlbl_titulo2.setEnabled(status);
        jlbl_nombre.setEnabled(status);
        jtxt_nombre.setEditable(status);
        /*jlbl_apaterno.setEnabled(status);
        jtxt_apaterno.setEditable(status);
        jlbl_amaterno.setEnabled(status);
        jtxt_amaterno.setEditable(status);*/
        jlbl_calle.setEnabled(status);
        jtxt_calle.setEditable(status);
        jlbl_int.setEnabled(status);
        jtxt_int.setEditable(status);
        jlbl_ext.setEnabled(status);
        jtxt_ext.setEditable(status);
        jlbl_col.setEnabled(status);
        jtxt_col.setEditable(status);
        jlbl_cp.setEnabled(status);
        jtxt_cp.setEditable(status);
        jlbl_mun.setEnabled(status);
        jtxt_mun.setEditable(status);
        jlbl_edo.setEnabled(status);
        jtxt_edo.setEditable(status);
        jlbl_rfc.setEnabled(status);
        jtxt_rfc.setEditable(status);     
        jlbl_ciudad.setEnabled(status);
        jtxt_ciudad.setEditable(status);
        
        jlbl_email.setEnabled(status);
        jtxt_email.setEditable(status);
    }
    
    private void on_offBoleto (boolean status) {    
        /*jlbl_total.setEnabled(status);
        jtxt_total.setEditable(status);
        jlbl_subtotal.setEnabled(status);
        jtxt_subtotal.setEditable(status);
        jlbl_iva.setEnabled(status);
        jtxt_iva.setEditable(status);*/
        //jlbl_titulo1.setEnabled(status);
        jtxt_codigo.setEditable(status);
        jlbl_codigo.setEnabled(status);
        jlbl_folio.setEnabled(status);
        jntxt_folio.setEditable(status);
        jtbl_boletos.setEnabled(status);

    }

    private void limpiar()
    {
            //reiniciar todo!
                                limpiarCajitas();
                                on_offCliente(false);
                                on_offBoleto(false);
                                jtxt_codigo.setText("");
                                jntxt_folio.setText("");
                                datos = null;
                                salida = null;
                                barraEstado0();
                                numeroBoletos = 0;
                                existe = 0;
                                total = 0.0;
                                subtotal = 0.0;
                                iva = 0.0;
                                jtxt_total.setText("$");
                                jtxt_subtotal.setText("$");
                                jtxt_iva.setText("$");

                                salida = new Vector[15];
                                salida[0] = new Vector();
                                salida[1] = new Vector();
                                salida[2] = new Vector();
                                salida[3] = new Vector();
                                salida[4] = new Vector();
                                salida[5] = new Vector();
                                salida[6] = new Vector();
                                salida[7] = new Vector();
                                salida[8] = new Vector();
                                salida[9] = new Vector();
                                salida[10] = new Vector();
                                salida[11] = new Vector();
                                salida[12] = new Vector();
                                salida[13] = new Vector();
                                salida[14] = new Vector();
                                modelo.setDataVector(datos, encabezados);
                                datos = new String[salida[0].size()][12];
                                //jtbl_boletos.removeAll();

                                //resizeColumnasCorridas(); 
                                jtxt_codigo.setEditable(true);
                                jntxt_folio.setEditable(true);
                                barraEstado0();
                                jtxt_codigo.requestFocus();
                                jtxt_NoCuenta.setText("");
                                jCmbMetodoPago.setSelectedIndex(0);
    }
    
    private void otroEvento(java.awt.event.KeyEvent evt) {
        if((evt.getKeyCode() == evt.VK_ESCAPE) && (existe == 1)) {
            limpiarCajitas();
            on_offCliente(false);
            jlbl_titulo2.setEnabled(true);
            jlbl_rfc.setEnabled(true);
            jtxt_rfc.setEditable(true);
            jlbl_nombre.setEnabled(true);
            jtxt_nombre.setEditable(true);
            /*jlbl_amaterno.setEnabled(true);
            jtxt_amaterno.setEditable(true);
            jlbl_apaterno.setEnabled(true);
            jtxt_apaterno.setEditable(true);*/
            jtxt_rfc.requestFocus();
            existe = 0;
            rfc_viejito = null;
            barraEstado();
        } else
            if(evt.getKeyCode() == evt.VK_ESCAPE) {
                if(jtxt_rfc.isEnabled()) {
                    on_offCliente(false);
                    on_offBoleto(true);
                    limpiarCajitas();
                    jntxt_folio.setEditable(false);
                    jtxt_codigo.setEditable(false);
                    jtbl_boletos.requestFocus();
                    existe = 0;
                }
                else{
                    //on_offBoleto(false);
                    on_offCliente(false);
                    jlbl_titulo2.setEnabled(true);
                    jlbl_rfc.setEnabled(true);
                    jtxt_rfc.setEditable(true);
                    jlbl_nombre.setEnabled(true);
                    jtxt_nombre.setEditable(true);
                    /*jlbl_amaterno.setEnabled(true);
                    jtxt_amaterno.setEditable(true);
                    jlbl_apaterno.setEnabled(true);
                    jtxt_apaterno.setEditable(true);*/
                    jtxt_rfc.requestFocus();
                }
            }
        
        if(evt.getKeyCode() == evt.VK_ENTER) 
        {
            System.out.println("ENTER");
            if(!FactElecAct && this.facturas_ini > this.facturas_fin) {
                JOptionPane.showMessageDialog( this, "¡Error! Ya no hay facturas. Por favor haga un refoliado",
                        "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                JDLG_Refoliar refoliar = new JDLG_Refoliar(null, this.facturas_fin,this.facturas_ini, false, this.terminal);
                refoliar.setLocationRelativeTo(this);
                refoliar.setModal(true);
                refoliar.setSize(600,180);
                refoliar.setVisible(true);
                facturas_fin = refoliar.fol_fin;
                facturas_ini = refoliar.fol_ini;
                if(menos5boletos)
                    jtxt_fac_act.setForeground(new Color(255,0,0));
                else
                    jtxt_fac_act.setForeground(new Color(0,0,0));
                String folio_fac = String.valueOf(facturas_ini);
                while(folio_fac.length() < 5) folio_fac = "0"+folio_fac;
                jtxt_fac_act.setText(facturas_prefijo+" " +folio_fac);
            }
            System.out.println("Despues de Revisar los folios");
            if(this.facturas_ini <= this.facturas_fin) {
                String respuesta = null;
                String respuesta1 = null;
                //VERIFICAR SESION ABIERTA
                System.out.println("Antes de verificar sesion");
               Vector vestado= (Vector) cosa.buscarEstadoSesion(Long.valueOf(datosIniciales.get(3).toString()), dblink_terminal);
               if(vestado != null) {
                String estadoses = vestado.get(0).toString();
                
                 if(estadoses.equals("CERRADA")){
                     JOptionPane.showMessageDialog( this, "¡La sesion es invaida!El Sistema se cerrará automáticamente",
                                "TMS - Facturar Boleto", JOptionPane.WARNING_MESSAGE ); 
                     System.exit(0);
                 }
               }else{
                   JOptionPane.showMessageDialog( this, "¡La sesion es invaida!El Sistema se cerrará automáticamente",
                                "TMS - Facturar Boleto", JOptionPane.WARNING_MESSAGE ); 
                     System.exit(0);
               }
                   System.out.println("Despues de verificar sesion");
                    String folios = "";
                    String ids = "";
                    respuesta = "";
                    for(int i = 0; i < salida[0].size();i++)
                        folios = folios + ","+salida[10].get(i).toString();

                    for(int i = 0; i < salida[0].size();i++)
                        ids = ids + ","+salida[11].get(i).toString();
                  
                    addCliente = new Cliente(jtxt_rfc.getText().trim(),jtxt_nombre.getText().trim(),jtxt_calle.getText().trim(),
                                             jtxt_int.getText().trim(),jtxt_ext.getText().trim(),jtxt_col.getText().trim(),
                                             jtxt_cp.getText().trim(),jtxt_mun.getText().trim(),jtxt_ciudad.getText().trim(),
                                             jtxt_edo.getText(),jtxt_email.getText().trim());
                    if(addCliente.validarCliente()) {
                        
                        PcInfo pc = new PcInfo();
                        boolean existeimp = false;
                        PrintService impresoraEncontrada = null;
                       // Vector vimp = (Vector) facadeTMS.buscarImpresoraFacturas(pc.getHostName().toString().toUpperCase());
                    /* BRA    if(vimp.size()==0)
                              //enviar mensaje de error indicando que no hay impresoras para facturas configuradas en la caja
                              JOptionPane.showMessageDialog( this, "No hay impresoras configuradas para esta caja. Contacte al administrador del sistema",
                                                "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                        else
                        {
                             String nomImpre = vimp.get(0).toString();
                             nomImpre = nomImpre.replace('[', ' ');
                             nomImpre = nomImpre.replace(']', ' ');
                             nomImpre = nomImpre.trim();
                             //System.out.println("nomImpre " + nomImpre);

                             PrintService service[] = PrinterJob.lookupPrintServices();
                             //System.out.println("El sistema tiene instaladas"+service.length+" impresoras instaladas y son:");
                             for(int i=0; i<service.length;i++)
                             {
                                //System.out.println("        "+service[i]. getName());
                                if(service[i].getName().toUpperCase().equals(nomImpre.toUpperCase()))
                                {
                                    existeimp = true;
                                    impresoraEncontrada = service[i];
                                }
                             }
                        }

                       if(!existeimp)
                       {      //enviar mensaje de error indicando que la impresoras para facturas no esta configurada en Windows  
                                JOptionPane.showMessageDialog( this, "La impresora de facturas no esta configurada en Windows. Contacte al administrador del sistema",
                                 "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                         return; }
                        */
                       this.setCursor(busyCursor); 
                       if (FactElecAct) 
                       {
                         //***********  Facturacion Electroncia
                            System.out.println("Entro Factura Electronica");
                           if (jCmbMetodoPago.getSelectedItem().toString().equalsIgnoreCase("Tarjeta de Credito")
                                  && jtxt_NoCuenta.getText().trim().length()<4 )
                            {
                                  JOptionPane.showMessageDialog( this, "Proporcione el no de cuenta para este método de pago",
                                "TMS - FacturaBoletor Boleto", JOptionPane.ERROR_MESSAGE );
                                                                   jtxt_NoCuenta.requestFocus(true);
                                setCursor(defaultCursor);
                                  return;
                            }
                            if(jCmbMetodoPago.getSelectedItem().toString().equalsIgnoreCase("Efectivo") )
                                jtxt_NoCuenta.setText("");

                      
                           
                           Vector DatosFact= DoFactElect(folios);
                           if (DatosFact !=  null){
                                System.out.println(" Se genero la factura electronica Correctamente ");
                                StringTokenizer fst = new StringTokenizer(DatosFact.elementAt(0).toString().trim(),"|");
                                fst.nextToken(); 
                                //String folio_facElect = PrefijoFolioFact+ TerminalFact + fst.nextToken();

                                RutaPDF=DatosFact.elementAt(1).toString().trim(); 
                                LlaveFact=DatosFact.elementAt(0).toString().trim(); 
                                System.out.println(" RutaPDF "+RutaPDF);
                                System.out.println(" LlaveFact "+LlaveFact);
                                
                                //respuesta = cosa.insertarBoletosFacturados(folio_facElect,folios, ids, jtxt_rfc.getText(), String.valueOf(usuario), "PCENTRAL_LINK.ESTRELLAROJA.COM.MX",RutaPDF,DatosFact.elementAt(0).toString());
                                if (true)//respuesta.equalsIgnoreCase("Todo bien"))
                                {   JOptionPane.showMessageDialog( this, "La factura fué realizada correctamente.",
                                    "TMS - Facturar Boleto", JOptionPane.INFORMATION_MESSAGE);
                                
                                      //String  Rut = RutaPDF.replace(RutAbs,RutAlias);
                                      //System.out.println(" Ruta Final  "+Rut);
                                      //OpenFile(Rut);
                                      
                                }     
                                else
                                { 
                                    // Cancela fact electronica
                                    respuesta = cosa.CancelarFact_Elect(LlaveFact);
                                    JOptionPane.showMessageDialog( this, "La factura no pudo ser generada.",
                                    "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                                  
                                    
                                }
                                    
                           } 
                          
                       }
                    else{
                            System.out.println("Entro Factura Normal");
                            String folio_fac = String.valueOf(facturas_ini);
                             while(folio_fac.length() < 5) folio_fac = "0"+folio_fac;
                            
                            
                            respuesta = cosa.insertarBoletosFacturados(facturas_prefijo+" "+folio_fac,folios, ids, jtxt_rfc.getText(), String.valueOf(usuario), "PCENTRAL_LINK.ESTRELLAROJA.COM.MX",RutaPDF,LlaveFact);
                            if(respuesta == null)
                                respuesta = "Algo no salio bien";
                            if(respuesta.equals("Todo bien")){
                                //System.out.print("Todo bien");                    
                                n2t numero;
                                String parte1 = null;
                                String parte2 = "00";
                                String completo = String.valueOf(total);
                                StringTokenizer token = new StringTokenizer(completo, ".");
                                parte1 = token.nextToken();
                                if(token.hasMoreTokens())
                                    parte2 = token.nextToken();
                                //System.out.print("parte1 "+parte1);
                                //System.out.print("parte2 "+parte2);
                                numero = new n2t(Integer.parseInt(parte1));
                                String res = numero.convertirLetras(Integer.parseInt(parte1));
                               //System.out.print("res"+res);
                                imprimirFactura(parte1, parte2, res, impresoraEncontrada);
                                JOptionPane.showMessageDialog( this, "Tome la factura de la impresora ",
                                    "TMS - Facturar Boleto", JOptionPane.WARNING_MESSAGE ); 
                                int result = JOptionPane.showOptionDialog(this, "¿La Factura es correcta?",
                                    "TMS - Facturar Boleto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                while(result == JOptionPane.NO_OPTION) {                    
                                    imprimirFactura(parte1, parte2, res, impresoraEncontrada);
                                    JOptionPane.showMessageDialog( this, "Tome la factura de la impresora ",
                                    "TMS - Facturar Boleto", JOptionPane.WARNING_MESSAGE );

                                    result = JOptionPane.showOptionDialog(this, "¿La Factura es correcta?",
                                        "TMS - Facturar Boleto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }

                                facturas_ini++;
                                if(menos5boletos)
                                    jtxt_fac_act.setForeground(new Color(255,0,0));
                                else
                                    jtxt_fac_act.setForeground(new Color(0,0,0));
                                folio_fac = String.valueOf(facturas_ini);
                                while(folio_fac.length() < 5) folio_fac = "0"+folio_fac;
                                jtxt_fac_act.setText(facturas_prefijo+" " +folio_fac);
                            }else{
                                boolean fallo = false;
                                StringTokenizer token = new StringTokenizer(respuesta,"|");
                                String quees = token.nextToken().toString();
                                if (quees.equals("factura")) {
                                    JOptionPane.showMessageDialog( this, "La factura "+token.nextToken().toString()+" ya ha sido facturada",
                                    "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                                    fallo = true;
                                }
                                if (quees.toString().equals("folio")) {
                                    JOptionPane.showMessageDialog( this, "El boleto con folio "+token.nextToken().toString()+" ya ha sido facturado",
                                    "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                                    fallo = true;
                                }
                                if (quees.equals("boleto")){
                                    JOptionPane.showMessageDialog( this, "El id de boleto "+token.nextToken().toString()+" ya ha sido facturado",
                                    "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                                    fallo = true;
                                }

                                if (quees.equals("Algo no salio bien")) {
                                    JOptionPane.showMessageDialog( this, "Por el momento no se puede facturar. Por favor intente mas tarde",
                                    "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                                    fallo = true;
                                }
                            }
                      } // else Factura Normal
                        
                       this.setCursor(defaultCursor);
                        limpiar();

                            }
                        
                    }else{
                        JOptionPane.showMessageDialog( this, "Algun campo esta vacio",
                                "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
                        limpiarCajitas();
                        on_offCliente(false);
                        jlbl_titulo2.setEnabled(true);
                        jlbl_rfc.setEnabled(true);
                        jtxt_rfc.setEditable(true);
                        jlbl_nombre.setEnabled(true);
                        jtxt_nombre.setEditable(true);
                        /*jlbl_amaterno.setEnabled(true);
                        jtxt_amaterno.setEditable(true);
                        jlbl_apaterno.setEnabled(true);
                        jtxt_apaterno.setEditable(true);*/
                        barraEstado();
                        jtxt_rfc.requestFocus();
                    }
                    menos5Boletos();
                    if(menos5boletos)
                        jtxt_fac_act.setForeground(new Color(255,0,0));
                    else
                        jtxt_fac_act.setForeground(new Color(0,0,0));
                    String folio_fac = String.valueOf(facturas_ini);
                    while(folio_fac.length() < 5) 
                        folio_fac = "0"+folio_fac;
                    jtxt_fac_act.setText(facturas_prefijo+" " +folio_fac);                    
                    
                }
            
    //************************************** GUARDA CLIENTE
        if(((existe == 2)||(existe == 3))&&(evt.getKeyCode() == evt.VK_F9)) {
            String respuesta = null;
            if((jtxt_rfc.getText().length() <= 0)||
               (jtxt_nombre.getText().length() <= 0) ||
               (jtxt_calle.getText().length() <= 0) ||
               (jtxt_col.getText().length() <= 0) ||
               (jtxt_mun.getText().length() <= 0) ||
               //(jtxt_int.getText().length() <= 0) ||
               (jtxt_ext.getText().length() <= 0) ||
               (jtxt_cp.getText().length() <= 0) ||
               (jtxt_ciudad.getText().length() <= 0) ||     
               (jtxt_edo.getText().length() <= 0))
                    JOptionPane.showMessageDialog( this, "¡Error! Alguno de los campos esta en blanco",
                               "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE );
            else {
                addCliente = new Cliente(jtxt_rfc.getText().trim(), jtxt_nombre.getText().trim(),
                                jtxt_calle.getText().trim(),jtxt_int.getText().trim(),jtxt_ext.getText().trim(),
                                jtxt_col.getText().trim(),jtxt_cp.getText().trim(), jtxt_mun.getText().trim(),
                                jtxt_ciudad.getText().trim(), jtxt_edo.getText().trim(),jtxt_email.getText().trim());
                if(!addCliente.validarCliente())
                    JOptionPane.showMessageDialog( this, "Alguno de los campos esta vacío. Por favor, intente nuevamente",
                            "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                else {
                    if(existe == 3){
                        
                        respuesta = RepControlFacade.insertarcliente(0, jtxt_nombre.getText().trim(),
                                    jtxt_calle.getText().trim(),jtxt_int.getText().trim(),jtxt_ext.getText().trim(),
                                   jtxt_col.getText().trim(),jtxt_cp.getText().trim(), jtxt_mun.getText().trim(),
                                   jtxt_ciudad.getText().trim(), jtxt_edo.getText().trim(), String.valueOf(usuario).trim(), jtxt_rfc.getText().trim(), 
                                    rfc_viejito, jtxt_email.getText().trim());
                    }
                    if(existe == 2) 
                        respuesta = RepControlFacade.insertarcliente(1, jtxt_nombre.getText().trim(),
                                    jtxt_calle.getText().trim(),jtxt_int.getText().trim(),jtxt_ext.getText().trim(),
                                    jtxt_col.getText().trim(),jtxt_cp.getText().trim(),jtxt_mun.getText().trim(),
                                    jtxt_ciudad.getText().trim(), jtxt_edo.getText().trim(), String.valueOf(usuario).trim(),
                                    jtxt_rfc.getText().trim(),  rfc_viejito,jtxt_email.getText().trim().trim());
                    if (respuesta == null )
                    {
                        JOptionPane.showMessageDialog( this, "Los datos del cliente no pudieron ser guardados. Verifique la inf.",
                                "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                        return;
                     
                    }
                    if(respuesta.equals("Esta Repetido") || respuesta.equals("Existen muchos")) {
                        JOptionPane.showMessageDialog( this, "Existe mas de una coincidencia. Contacte a sistemas",
                                "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                        limpiarCajitas();
                        on_offCliente(false);
                        jlbl_titulo2.setEnabled(true);
                        jlbl_rfc.setEnabled(true);
                        jtxt_rfc.setEditable(true);
                        jlbl_nombre.setEnabled(true);
                        jtxt_nombre.setEditable(true);
                        /*jlbl_amaterno.setEnabled(true);
                        jtxt_amaterno.setEditable(true);
                        jlbl_apaterno.setEnabled(true);
                        jtxt_apaterno.setEditable(true);*/
                        barraEstado();
                        jtxt_rfc.requestFocus();

                    }
                    if(respuesta.equals("Todo bien")){
                        on_offCliente(false);
                        existe = 1;
                        barraEstado();
                        barraEstado2();
                        rfc_viejito = jtxt_rfc.getText();
                        JOptionPane.showMessageDialog( this, "La información fue almacenada correctamente",
                                "TMS - Facturar Boleto", JOptionPane.INFORMATION_MESSAGE ); 
                    }
   
                    if(respuesta.equals("No existe")){
                        existe = 0;
                        /*JOptionPane.showMessageDialog( this, "El registro no existe",
                                "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); */
                        int result = JOptionPane.showOptionDialog(this, "El registro no existe.¿ Desea agregar esta información?",
                            "TMS - Facturar Boleto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if(result == JOptionPane.YES_OPTION) {
                            existe = 3;
                            respuesta = RepControlFacade.insertarcliente(0,jtxt_nombre.getText().trim(), jtxt_calle.getText().trim(),
                                                             jtxt_int.getText().trim(),jtxt_ext.getText().trim(),jtxt_col.getText().trim(),
                                                            jtxt_cp.getText().trim(), jtxt_mun.getText().trim(),jtxt_ciudad.getText().trim(),
                                                            jtxt_edo.getText().trim(), String.valueOf(usuario).trim(), jtxt_rfc.getText().trim(), 
                                                            rfc_viejito, jtxt_email.getText().trim());
                            if(respuesta.equals("Todo bien")){
                                on_offCliente(false);
                                existe = 1;
                                barraEstado();
                                barraEstado2();
                                rfc_viejito = jtxt_rfc.getText();
                                JOptionPane.showMessageDialog( this, "La información fue almacenada correctamente",
                                        "TMS - Facturar Boleto", JOptionPane.INFORMATION_MESSAGE ); 
                            }
                            
                            if(respuesta.equals("ALGO NO SALIO BIEN")){
                                existe = 0;
                                JOptionPane.showMessageDialog( this, "En este momento no puede guardar el registro. Continue con la facturación",
                                        "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                                //limpiarCajitas();
                                on_offCliente(false);
                                jlbl_titulo2.setEnabled(true);
                                jlbl_rfc.setEnabled(true);
                                jtxt_rfc.setEditable(true);
                                jlbl_nombre.setEnabled(true);
                                jtxt_nombre.setEditable(true);
                                /*jlbl_amaterno.setEnabled(true);
                                jtxt_amaterno.setEditable(true);
                                jlbl_apaterno.setEnabled(true);
                                jtxt_apaterno.setEditable(true);*/
                                barraEstado();
                                jtxt_rfc.requestFocus();
                            }
                            
                            respuesta = null;
                        } else {
                            limpiarCajitas();
                            on_offCliente(false);
                            jlbl_titulo2.setEnabled(true);
                            jlbl_rfc.setEnabled(true);
                            jtxt_rfc.setEditable(true);
                            jlbl_nombre.setEnabled(true);
                            jtxt_nombre.setEditable(true);
                            /*jlbl_amaterno.setEnabled(true);
                            jtxt_amaterno.setEditable(true);
                            jlbl_apaterno.setEnabled(true);
                            jtxt_apaterno.setEditable(true);*/
                            barraEstado();
                            jtxt_rfc.requestFocus();
                        }
                    }

                    if(respuesta.equals("ALGO NO SALIO BIEN")){
                        existe = 0;
                        JOptionPane.showMessageDialog( this, "En este momento no puede guardar el registro. Continue con la facturación",
                                "TMS - Facturar Boleto", JOptionPane.ERROR_MESSAGE ); 
                        //limpiarCajitas();
                        on_offCliente(false);
                        jlbl_titulo2.setEnabled(true);
                        jlbl_rfc.setEnabled(true);
                        jtxt_rfc.setEditable(true);
                        jlbl_nombre.setEnabled(true);
                        jtxt_nombre.setEditable(true);
                        /*jlbl_amaterno.setEnabled(true);
                        jtxt_amaterno.setEditable(true);
                        jlbl_apaterno.setEnabled(true);
                        jtxt_apaterno.setEditable(true);*/
                        barraEstado();
                        jtxt_rfc.requestFocus();
                    }
                }
            }
        }
        
        if((evt.getKeyCode() == evt.VK_F5) &&(existe == 1)) {
            existe = 2;
            on_offCliente(true);
            barraEstado();
            jtxt_rfc.requestFocus();
        }
        if((evt.getKeyCode() == evt.VK_F5) &&(existe == 0)){
                on_offCliente(true);
                barraEstado();
                jtxt_rfc.requestFocus();
                limpiarCajitas();
                existe = 3;
        }       
    }
    
    public void OpenFile(String Ruta)   {
    File nombre;//archivo pdf o xls o doc 
    System.out.println(" Abriendo rutaPDF "+Ruta);
    
    //Ruta="C:\\Compartir\\archivo_guias_prepagadas_05_02_2010_15_46_42.pdf";
      try {     //try statement
        
          nombre = new File(Ruta);
          Process p = null;
          p=Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+
                                       nombre.getAbsolutePath() ); //+".pdf");
       } catch (Exception e) {
          e.printStackTrace();
      }  
    }

    private void limpiarCajitas() {
        jtxt_nombre.setText("");
        /*jtxt_apaterno.setText("");
        jtxt_amaterno.setText("");*/
        jtxt_calle.setText("");
        jtxt_int.setText("");
        jtxt_ext.setText("");
        jtxt_col.setText("");
        jtxt_cp.setText("");
        jtxt_mun.setText("");
        jtxt_edo.setText("");
        jtxt_rfc.setText("");
        jtxt_ciudad.setText("");
        jtxt_email.setText("");
        jtxt_rfc.requestFocus();
        this.NoCuenta="";
        this.MetodoPago="";
        this.setCursor(defaultCursor);
    }

    private void colocarInformacion(Vector cliente, boolean ban) {
        on_offCliente(false);
        if (ban)
           jtxt_rfc.setText(cliente.get(38)==null?"":cliente.get(38).toString());
        else {
            jtxt_nombre.setText(cliente.get(1)==null?"":cliente.get(1).toString());
            //StringTokenizer token = new StringTokenizer(cliente.get(2).toString()+" ", " ");
            /*jtxt_apaterno.setText(token.nextToken());
            jtxt_amaterno.setText(token.nextToken());*/
        }
        jtxt_calle.setText(cliente.get(4)==null?"":cliente.get(4).toString());
        if(cliente.get(5) != null)
            jtxt_int.setText(cliente.get(5).toString());
        else
            jtxt_int.setText("");
        System.out.println("Cliente "+cliente);
        jtxt_ext.setText(cliente.get(6)==null?"":cliente.get(6).toString());
        jtxt_col.setText(cliente.get(7)==null?"":cliente.get(7).toString());
        jtxt_cp.setText(cliente.get(8)==null?"":cliente.get(8).toString());
        jtxt_mun.setText(cliente.get(9)!= null ?cliente.get(9).toString():"");
        jtxt_edo.setText(cliente.get(11)==null?"":cliente.get(11).toString());
        jtxt_ciudad.setText(cliente.get(10)==null?"":cliente.get(10).toString());
         System.out.println("Cliente(19) "+cliente.get(19));
         System.out.println("Cliente(20) "+cliente.get(20));
        jtxt_email.setText(cliente.get(19)!= null? cliente.get(19).toString().trim():"");
        
         existe = 1;
        rfc_viejito = jtxt_rfc.getText();
        barraEstado();
    }

    private void imprimirFactura(String parte1, String parte2, String res, PrintService impresoraEncontrada) {
        imprimir_recibo_tarjeta imp = new imprimir_recibo_tarjeta(this, res, parte1, parte2, impresoraEncontrada);
        imp.ImprimeDatos();
    }
    
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }

    private void menos5Boletos() {
        String mensaje = "";
        if( !FactElecAct  && ( this.facturas_ini+5 >= this.facturas_fin) && (this.facturas_ini <= this.facturas_fin)) {
            if((this.facturas_fin-this.facturas_ini) > 0)
                mensaje = "Solo quedan "+String.valueOf(this.facturas_fin-this.facturas_ini) +" facturas";            
            else
                mensaje = "<html>Esta es la última Factura<br>Por Favor Realice un Refoliado</html>";            
            JOptionPane.showMessageDialog( this, mensaje,
                    "TMS - Facturar Boleto", JOptionPane.WARNING_MESSAGE);
            menos5boletos = true;
        }
    }

    public void setFolioPreimpreso(String folioPreimpreso){
        jntxt_folio.setText(folioPreimpreso);
    }
    
    public void validaMetpago()
    {
        
        // SI No hay datos no selecciona nada

        System.out.println("  Entrando  validaMetpago");
        System.out.println("  jtbl_boletos.getModel().getRowCount()  "+jtbl_boletos.getModel().getRowCount() );

        if (jtbl_boletos.getModel() == null ||
            jtbl_boletos.getModel().getRowCount() <= 0    )
        {
            this.NoCuenta="";
            jCmbMetodoPago.setSelectedIndex(0);
        }

        String NoCuenta="", MetodoPago="";
        String NoCuentabol="", MetodoPagobol="";
        for (int i = 0; i <  jtbl_boletos.getModel().getRowCount(); i++) {
            NoCuentabol=salida[14].get(i)+"";
            MetodoPagobol=salida[13].get(i)+"";
            System.out.println("  validaMetpago  NoCuenta "+NoCuentabol);
            System.out.println("  validaMetpago  Metodo pago "+MetodoPagobol);
            if (i == 0)
            {
               NoCuenta= NoCuentabol;
               MetodoPago=MetodoPagobol;
            }
           else if (MetodoPagobol.equalsIgnoreCase(MetodoPago) // Toma el primer no de cuenta diferente de vacio
                    &&     NoCuenta.length()== 0    ){
                   NoCuenta= NoCuentabol;
            }
          else  if (!MetodoPagobol.equalsIgnoreCase(MetodoPago)
                    || (MetodoPagobol.equalsIgnoreCase(MetodoPago) && !NoCuenta.equalsIgnoreCase(NoCuentabol)
                        && NoCuenta.length()>0 &&NoCuentabol.length()>0  )  )
                  {
                     MetodoPago = "NO IDENTIFICADO";
                     NoCuenta= "";}
          }  // FOR

        if (MetodoPago.equalsIgnoreCase("Efectivo")
                ||MetodoPago.equalsIgnoreCase("NO IDENTIFICADO") )
        {  
            NoCuenta = "";
            jtxt_NoCuenta.setEditable(false);
        }
        else
        {
            jtxt_NoCuenta.setEditable(true);
            jtxt_NoCuenta.requestFocus();  
        }

         this.NoCuenta=NoCuenta;
         jCmbMetodoPago.setSelectedItem(MetodoPago);
         jtxt_NoCuenta.setText(this.NoCuenta);
        System.out.println("  MetodoPago: "+MetodoPago);
        System.out.println("  NoCuenta: "+NoCuenta);
    }

    public void buscarBoleto() {
        
        this.setCursor(busyCursor);
        boolean invalido = false;
        Vector nombre = null;
        //validacion
                jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Iniciar Otra Consulta");
                String referencia = "";
                String Folio      = "";
                
                referencia = jtxt_codigo.getText().trim().toUpperCase();
                Folio      = jntxt_folio.getText().trim();
              
                
                System.out.println("La Referencia del boleto es "+referencia);
                
                  // Valida Boleto 
                String codeCiudad=((Vector)Vectciudades.elementAt(jCmbCdVenta.getSelectedIndex())).elementAt(0).toString();
                System.out.println("Ciudad seleccionada "+codeCiudad);
               Vector Vvalida= facadeTMS.ValidaBoletoAFacturarPRC(codeCiudad, referencia,Folio);

               System.out.println("Boleto Valido "+Vvalida);
               String strvalido = Vvalida.elementAt(0).toString();
               if (strvalido.equalsIgnoreCase("FALSE"))
               {
                    JOptionPane.showMessageDialog( this, Vvalida.elementAt(1).toString(),
                                        "TMS - Validar Boleto", JOptionPane.ERROR_MESSAGE );   
                   this.setCursor(defaultCursor);
                  return;
               }  
               
                // Existe el boleto 
            // if(referencia != null)
               // if((!invalido) && (!referencia.equals("null")) &&((referencia != null)||(referencia != ""))){
               
                    jtxt_codigo.setEditable(false);
                    jntxt_folio.setEditable(false);
                    nombre = new Vector();
                    try{
                
                       /* if (jtxt_codigo.getText().trim().length() > 0)
                            nombre = facadeTMS.buscarCodigoBarras(jtxt_codigo.getText().trim());
                        else 
                        nombre = facadeTMS.buscarCodigoBarrasReferencia(jntxt_folio.getText().trim());
                        */
                        nombre= new Vector();
                        for (int i = 2; i < Vvalida.size() ; i++) {
                            nombre.addElement(Vvalida.elementAt(i) != null? Vvalida.elementAt(i).toString().trim():"");
                           // System.out.println("*****     nombre "+ Vvalida.elementAt(i).toString());
                        }

                        System.out.println("*****     nombre "+ nombre);
                        
                    //    if (nombre != null  && nombre.get(24) != null){
                            on_offBoleto(true);
                         //    String cosas = cosa.validar(nombre.get(24).toString(), nombre.get(0).toString());
                           // if(cosas.equals("[1]")) {
                                //Agregar Boleto a Tabla
                                    //jtbl_boletos.requestFocus();            
                                    salida[0].add(nombre.get(0).toString()); //clave_corrida
                                    salida[1].add((nombre.get(1)!= null ? nombre.get(1).toString():"")); //nombre_pasajero
                                    salida[2].add(nombre.get(2).toString()); //tipo_pasajero
                                    salida[3].add(nombre.get(3).toString()); //no_asiento
                                    salida[4].add(""); //fecha_hora_corrida
                                    salida[5].add(nombre.get(5).toString()); //fecha_hora_venta
                                    salida[6].add(nombre.get(6).toString()); //importe
                                    salida[7].add(nombre.get(7).toString()); //origen
                                    salida[8].add(nombre.get(8).toString()); //destino
                                    salida[9].add(nombre.get(9).toString());  //servicio
                                    salida[10].add(nombre.get(10).toString()); //folio_preimpreso
                                    salida[11].add(nombre.get(11).toString()); //boleto_id
                                    salida[12].add("PCENTRAL_LINK.ESTRELLAROJA.COM.MX");
                                    
                                   // Valida Tipo Metodo de  pago
                                   String  MetodoPagoBol= nombre.get(12).toString();
                                   String  NoCuentaBol = ( nombre.get(13) != null? nombre.get(13).toString().trim():"" ) ;
                                   salida[13].add(MetodoPagoBol); //Metodo de Pago
                                   salida[14].add(NoCuentaBol); //Metodo de Pago

                                   
                                   // Valida Tipo Metodo de  pago
                                   System.out.println( "sALIDA "+salida[0].size());
                                    datos = new String[salida[0].size()][12];
                                 
                                    for(int i = 0; i < 10; i++)
                                        for(int j =0; j < salida[0].size(); j++)  //Solo se muestran 9 elementos en pantalla
                                           
                                            datos[j][i] = salida[i].get(j).toString();
                                        
                                     for(int j =0; j < salida[0].size(); j++)  //Agrega metodo de pago
                                     {
                                        datos[j][10] = salida[13].get(j).toString();
                                        datos[j][11] = salida[10].get(j).toString();
                                     }

                                    
                                    total += Double.parseDouble(nombre.get(6).toString());
                                    modelo.setDataVector(datos, encabezados);
                                    resizeColumnasCorridas();

                                     validaMetpago();//MetodoPagoBol,NoCuentaBol );
                                  

                                    DecimalFormat df = new DecimalFormat("#########.00");
                                    iva = total * 0.0;
                                    subtotal = total - iva;

                                    jtxt_subtotal.setText(String.valueOf(subtotal));
                                    jtxt_iva.setText("0.00");
                                    jtxt_total.setText(String.valueOf(df.format(total)));
                                
                                   
                                    jntxt_folio.setText("");
                                    jntxt_folio.setEditable(true);
                                    jtxt_codigo.setText("");
                                    jtxt_codigo.setEditable(true);
            
            
                                    numeroBoletos++;
                                    if(numeroBoletos > 24)
                                        JOptionPane.showMessageDialog( this, "¡Este es el número máximo de boletos por factura! Proceda a facturar",
                                                            "TMS - Facturar Boletos", JOptionPane.ERROR_MESSAGE );

                                    //en caso de ser necesario, buscar sobre todos los boletos el boleto repetido y eliminarlo
                                    int ultimo = -1;
                                    for(int j = 0; j < salida[0].size(); j++)
                                        for(int k = 0; k < salida[0].size(); k++)
                                            if((salida[11].get(j).toString().equals(salida[11].get(k).toString())) && (j != k)){
                                                if(j < k)
                                                    ultimo = k;
                                                else
                                                    ultimo = j;
                                                jtbl_boletos.setRowSelectionInterval(ultimo, ultimo);
                                                jtbl_boletos.requestFocus();
                                                JOptionPane.showMessageDialog( this, "¡Error! Esta intentando facturar el mismo boleto mas de una vez",
                                                            "TMS - Facturar Boletos", JOptionPane.ERROR_MESSAGE );
                                                eliminar();
                                            }
                                jtxt_codigo.setEditable(false);
                                jntxt_folio.setEditable(false);
                                jtbl_boletos.requestFocus();
                            /*}
                            if(cosas.equals("[0]")){
                                JOptionPane.showMessageDialog( this, "La fecha de expedición del boleto es anterior al día de hoy",
                                        "TMS - Validar Codigo Barras", JOptionPane.ERROR_MESSAGE );   
                                invalido = true;
                                barraEstado0();
                                jtxt_codigo.requestFocus();
                                jtxt_codigo.setText("");
                                jntxt_folio.setText("");
                            }
                            if(cosas.equals("[2]")){
                                JOptionPane.showMessageDialog( this, "¡Error! No se halló la corrida",
                                        "TMS - Validar Codigo Barras", JOptionPane.ERROR_MESSAGE );
                                invalido = true;
                            }
                            if(cosas.equals("[3]")){
                                JOptionPane.showMessageDialog( this, "La fecha de expedición del boleto es posterior del dia de hoy",
                                        "TMS - Validar Codigo Barras", JOptionPane.ERROR_MESSAGE );
                                invalido = true;
                                barraEstado0();
                                jtxt_codigo.requestFocus();
                                jtxt_codigo.setText("");
                                jntxt_folio.setText("");
                            }*/
                      /*  }else{
                            JOptionPane.showMessageDialog( this, "¡Error! No se halló el boleto o el codigo no se coicide con el origen del boleto",
                                        "TMS - Facturar Boletos", JOptionPane.ERROR_MESSAGE );
                            invalido = true;
                            barraEstado0();
                            jtxt_codigo.requestFocus();
                            jtxt_codigo.setText("");
                            jntxt_folio.setText("");
                        }*/
                        if(numeroBoletos > 0) {
                            jtxt_codigo.setEditable(false);
                            jntxt_folio.setEditable(false);
                            jtbl_boletos.setRowSelectionInterval(0,0);
                            jtbl_boletos.requestFocus();
                        }
                    }catch(javax.ejb.EJBException  e){
                        System.out.println("El boleto no existe!  "+e);
                        JOptionPane.showMessageDialog( this, "¡Error! El boleto no existe",
                                        "TMS - Facturar Boletos", JOptionPane.ERROR_MESSAGE );
                        jntxt_folio.setText("");
                        jtxt_codigo.setText("");
                        on_offBoleto(false);
                        jntxt_folio.setEditable(true);
                        jtxt_codigo.setEditable(true);
                        barraEstado0();
                        jtxt_codigo.requestFocus();
                    }
               //  }  if((!invalido) && (!referencia.equals("null")) &&((referencia != null)||(referencia != ""))){
             /*   else {
                    JOptionPane.showMessageDialog( this, "¡Error! El boleto no existe",
                                        "TMS - Facturar Boletos", JOptionPane.ERROR_MESSAGE );
                    jntxt_folio.setText("");
                    jtxt_codigo.setText("");
                    on_offBoleto(false);
                    jntxt_folio.setEditable(true);
                    jtxt_codigo.setEditable(true);
                    barraEstado0();
                    jtxt_codigo.requestFocus();
                }
             else {
                    JOptionPane.showMessageDialog( this, "¡Error! El boleto no existe. ",
                                        "TMS - Facturar Boletos", JOptionPane.ERROR_MESSAGE );
                    jntxt_folio.setText("");
                    jtxt_codigo.setText("");
                    jntxt_folio.setEditable(true);
                    jtxt_codigo.setEditable(true);
                    barraEstado0();
                    jtxt_codigo.requestFocus();
             }       */ //BRA
               this.setCursor(defaultCursor);
    }
    
    public boolean  validando(Vector VdatosBoleto)
    {
        boolean valido =true;
        String referencia = "";
        String Empresa    = "";
        String SerieBoleto ="";
          if (VdatosBoleto != null  )   
              {
              System.out.println("Validando "+VdatosBoleto);
                  referencia          = VdatosBoleto.get(0).toString().trim();
                  Empresa             = VdatosBoleto.get(1).toString().trim();
                  SerieBoleto         = VdatosBoleto.get(2).toString().trim();
                  String FechaActual  = VdatosBoleto.get(3).toString().trim();
                  String FechaVenta   = VdatosBoleto.get(4).toString().trim();
                  
                  System.out.println("Empresa "+Empresa);
                  if (!Empresa.equalsIgnoreCase("Estrella Roja") &&  !Empresa.equalsIgnoreCase("Terminal Las Torres Puebla"))
                  {JOptionPane.showMessageDialog( this, "El boleto no puede ser factura. No pertenece a la empresa.",
                                        "TMS - Facturar Codigo Barras", JOptionPane.ERROR_MESSAGE );
                   valido=false;
                   return valido;
                  }
                  
                   SerieBoleto= VdatosBoleto.get(6).toString();          
                   if (SerieBoleto.equalsIgnoreCase("1"))
                   { JOptionPane.showMessageDialog( this, "El boleto no puede ser factura. Pudo ser cancelado o hubo un Cambio de horario.",
                                        "TMS - Facturar Codigo Barras", JOptionPane.ERROR_MESSAGE );
                     valido=false;
                     return valido;
                   }
                   
                   // Validando Fechas 
                   System.out.println("FechaActual "+FechaActual);
                    System.out.println("FechaVenta "+FechaVenta);
                   if ( !( FechaActual.equalsIgnoreCase(FechaVenta)) )
                   {   
                       JOptionPane.showMessageDialog( this, "El boleto no puede ser factura. Debido a que no fué expedido en este mes.",
                                        "TMS - Facturar Codigo Barras", JOptionPane.ERROR_MESSAGE );
                     valido=false;
                     return valido;
                   }   
                   
                   // Busca si ya tiene factura
                   String folio="";
                   if (jtxt_codigo.getText().trim().length()>0)   // es por referencia
                       folio=  VdatosBoleto.get(3).toString(); 
                   else
                       folio=  VdatosBoleto.get(0).toString(); // 
                   
                   String facturaId=cosa.TieneFactura(folio);
                   if (facturaId!= null)
                       { JOptionPane.showMessageDialog( this, "El boleto ya fué facturado.",
                                        "TMS - Facturar Codigo Barras", JOptionPane.ERROR_MESSAGE );
                     valido=false;
                     return valido;
                   }
                 // valida por procedure 
                //BRA   
                 //  facadeTMS.ValidaBoletoAFacturarPRC()
               }
               
          else    // if (VdatosBoleto != null  ) 
               JOptionPane.showMessageDialog( this, "El boleto no es válido, verifique sus datos.",
                                        "TMS - Facturar Codigo Barras", JOptionPane.ERROR_MESSAGE );
            return valido;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCmbCdVenta;
    private javax.swing.JComboBox jCmbMetodoPago;
    private javax.swing.JLabel jLblEtiqueta;
    private javax.swing.JPanel jPanelFacAct;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_calle;
    private javax.swing.JLabel jlbl_ciudad;
    private javax.swing.JLabel jlbl_ciudad1;
    private javax.swing.JLabel jlbl_ciudad2;
    private javax.swing.JLabel jlbl_codigo;
    private javax.swing.JLabel jlbl_codigo1;
    private javax.swing.JLabel jlbl_col;
    private javax.swing.JLabel jlbl_cp;
    private javax.swing.JLabel jlbl_edo;
    private javax.swing.JLabel jlbl_email;
    private javax.swing.JLabel jlbl_ext;
    private javax.swing.JLabel jlbl_fac_act;
    private javax.swing.JLabel jlbl_folio;
    private javax.swing.JLabel jlbl_int;
    private javax.swing.JLabel jlbl_iva;
    private javax.swing.JLabel jlbl_mun;
    private javax.swing.JLabel jlbl_nombre;
    private javax.swing.JLabel jlbl_rfc;
    private javax.swing.JLabel jlbl_subtotal;
    private javax.swing.JLabel jlbl_titulo1;
    private javax.swing.JLabel jlbl_titulo2;
    private javax.swing.JLabel jlbl_total;
    private tms_TextFields.JNumberTextField jntxt_folio;
    private javax.swing.JTable jtbl_boletos;
    private tms_TextFields.JNumberTextField jtxt_NoCuenta;
    private tms_TextFields.JTextTextField jtxt_calle;
    private tms_TextFields.JTextTextField jtxt_ciudad;
    private tms_TextFields.JTextTextField jtxt_codigo;
    private tms_TextFields.JTextTextField jtxt_col;
    private tms_TextFields.JCuantityTextField jtxt_cp;
    private tms_TextFields.JTextTextField jtxt_edo;
    private tms_TextFields.JTextTextField jtxt_email;
    private tms_TextFields.JTextTextField jtxt_ext;
    private javax.swing.JTextField jtxt_fac_act;
    private tms_TextFields.JTextTextField jtxt_int;
    private javax.swing.JTextField jtxt_iva;
    private tms_TextFields.JTextTextField jtxt_mun;
    private tms_TextFields.JTextTextField jtxt_nombre;
    private tms_TextFields.JTextTextField jtxt_rfc;
    private javax.swing.JTextField jtxt_subtotal;
    private javax.swing.JTextField jtxt_total;
    // End of variables declaration//GEN-END:variables
    
}

class Cliente {
    String RFC = null; 
    String nombre= null; 
    String aparteno= null; 
    String amaterno= null; 
    String calle= null; 
    String no_int= null; 
    String no_ext= null; 
    String col= null; 
    String cp= null; 
    String mun= null; 
    String ciudad = null;
    String edo = null;
    String email= null;
    
    public Cliente(String RFC, String nombre, String calle, String no_int, String no_ext, String col, String cp, String mun, String ciudad, String edo, String email)
    {
        this.RFC = RFC; 
        this.nombre= nombre; 
        this.calle= calle; 
        this.no_int= no_int; 
        this.no_ext= no_ext; 
        this.col= col; 
        this.cp= cp; 
        this.mun= mun; 
        this.ciudad = ciudad;
        this.edo = edo;
        this.email= email;
    }
    
    public Cliente (Cliente cliente){
        this.RFC = cliente.RFC;
        this.nombre= cliente.nombre; 
        this.calle= cliente.calle; 
        this.no_int= cliente.no_int; 
        this.no_ext= cliente.no_ext; 
        this.col= cliente.col; 
        this.cp= cliente.cp; 
        this.mun= cliente.mun; 
        this.ciudad = cliente.ciudad;
        this.edo = cliente.edo;
    }
    
    public boolean validarCliente()
    {
         if(this.RFC.equals("")||this.RFC.length()<1)
             return false;
         if(this.nombre.equals("")||this.nombre.length()<1)
            return false;         
         if(this.calle.equals("")||this.calle.length()<1)
            return false;
         if(this.col.equals("")||this.col.length()<1)
            return false;
         if(this.cp.equals("")||this.cp.length()<1)
            return false;
         if(this.edo.equals("")||this.edo.length()<1)
            return false;
         if(this.mun.equals("")||this.mun.length()<1)
            return false;
         if(this.no_ext.equals("")||this.no_ext.length()<1)
            return false;
         //if(this.no_int.equals("")||this.no_int.length()<1)
           // return false;
         if(this.ciudad.equals("")||this.ciudad.length()<1)
            return false;
     return true;
    }
 
public  JComboBox fillcombobox(JComboBox jcmb, Vector objectos)
    {
        
        if(objectos != null && objectos.size() > 0)
        {
            jcmb.removeAllItems();
            DefaultComboBoxModel defaultModel =new DefaultComboBoxModel(objectos);
            jcmb.setModel(defaultModel);
            /*for (int i = 0; i < objectos.size(); i++) {
                jcmb.addItem(objectos.elementAt(i));
            }*/
        }
        return jcmb; 
    }     

 

}
