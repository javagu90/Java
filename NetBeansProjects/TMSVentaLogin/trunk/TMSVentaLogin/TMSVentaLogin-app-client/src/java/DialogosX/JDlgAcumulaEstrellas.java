/*
 * JDlgAcumulaEstrellas.java
 *
 * Created on 29 de diciembre de 2009, 12:31 PM
 */

package DialogosX;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import subProcesos.JTextCardField;
import tms_venta.JClsColoresInterfaz;
import tms_venta.SesionVenta;
import tms_venta.entidad.TmsTiposPasajeroTbl;
import xer_emv_dll.JClsPinPadTBRequest;
import xer_emv_dll.JClsPinPadTBResponse;
import xer_emv_dll.JDlgBProgresoLealtad;
import xer_emv_dll.JObsBProgreso;
import xer_emv_dll.SwingWorker;

/**
 *
 * @author  vgonzalez
 */
public class JDlgAcumulaEstrellas extends javax.swing.JDialog {
    private Object[] encBoletos = {"#", "T", "Nombre Pasajero", "Promoción"};
    private DefaultTableModel xBoletos = new DefaultTableModel(null, encBoletos){
        public boolean isCellEditable(int row, int col) {
            if(col==8)
                return true;
            else
                return false;
        }
    };    
    private Object[][] Boletos;
    private Object[][] BoletosTabla;
    private boolean completado = true;
    private Dimension screenSize;
    private JClsColoresInterfaz ColoresInterfaz = new JClsColoresInterfaz();
    private final Color ARENA=new Color(238, 238, 238);
    private static Color SELECCION = new Color(184, 207, 229);
    private static Color BLANCO = new Color(255, 255, 255);
    private JDlgBProgresoLealtad jProgreso = null;
    private boolean activoF1 = false;
    private int enProceso;
    private SwingWorker worker = null;
    private SesionVenta sesionVenta;
    private String referencia;
    private String Amount;
    private String AMEXP = "";
    private JClsPinPadTBResponse jClsPinPadTBResponse;
    private JClsPinPadTBRequest jClsPinPadTBRequest;
    private JDlgAceptar DialogoAceptar = new JDlgAceptar();
    private JDlgSiNo DialogoSiNo = null;
    private boolean success;
    private List<TmsTiposPasajeroTbl> listado;
    private String diaVenta = "";
    private String diaCorrida = "";
    private Vector registros = null;
    private boolean cerrar = false;

    private String numeroTarjeta = "";
    
    /** Creates new form JDlgAcumulaEstrellas */
    public JDlgAcumulaEstrellas(javax.swing.JDialog parent, boolean modal, Object[][] pboletos, SesionVenta psesionVenta,List<TmsTiposPasajeroTbl> plistado, boolean pmuestra, String pnumeroTarjeta) {
        super(parent, modal);
        this.setTitle("TMS Venta. Acumulacion de Estrellas");
        this.sesionVenta = psesionVenta;
        this.listado = plistado;
        //this.sesionVenta.fechaHoraBD();
        //this.referencia = sesionVenta.getUserCon().getUsuarioNum()+""+new SimpleDateFormat("ddMMyyHHmmss").format(sesionVenta.getFechaHoraSistemaVenta().getTime());
        //this.referencia = sesionVenta.getUserCon().getTerminalId()+""+sesionVenta.getFechaHoraSistemaVentaLealtad();
        this.referencia = ""+sesionVenta.getFechaHoraSistemaVentaLealtad();
        this.referencia = this.referencia.replace('[',' ');
        this.referencia = this.referencia.replace(']',' ');
        this.referencia = this.referencia.trim();
        this.referencia = sesionVenta.getUserCon().getTerminalId()+""+sesionVenta.getUserCon().getCajaId()+""+this.referencia+"0";
        this.diaVenta = sesionVenta.getDiaSistemaVentaLealtad().toUpperCase();
//        this.diaVenta = this.diaVenta.replace('[',' ');
//        this.diaVenta = this.diaVenta.replace(']',' ');
//        this.diaVenta = this.diaVenta.trim();
        this.numeroTarjeta = pnumeroTarjeta;
        this.Boletos = pboletos;
        String fechaArreglo[] = Boletos[0][26].toString().split("/");
        Timestamp f = null;
        f = Timestamp.valueOf(""+fechaArreglo[2]+"-"+fechaArreglo[1]+"-"+fechaArreglo[0]+" 00:00:00");
        this.diaCorrida = new SimpleDateFormat("EEEE").format(f.getTime()).toUpperCase();
        this.jClsPinPadTBRequest = new JClsPinPadTBRequest();
        this.jClsPinPadTBResponse = new JClsPinPadTBResponse();
        this.Amount = String.valueOf(sesionVenta.getImporteVenta());
        System.out.println("diaCorrida = "+diaCorrida);
        System.out.println("diaVenta = "+diaVenta);        
        System.out.println("isLectorSocioIntimo = "+sesionVenta.isLectorSocioIntimo());
        BoletosTabla = new Object[Boletos.length][4];
        for(int i = 0; i<Boletos.length; i++)
        {
            BoletosTabla[i][0] = Boletos[i][1];
            BoletosTabla[i][1] = Boletos[i][2];
            BoletosTabla[i][2] = Boletos[i][3];
             for(int j=0; j<listado.size(); j++)
             {
                if(Boletos[i][2].toString().equals(listado.get(j).getLetraTipo()))
                {
                    String diaAplica = "";
                    if(listado.get(j).getAplicaTipoLealtad().equals("VENTA"))
                        diaAplica = diaVenta;
                    else
                        diaAplica = diaCorrida;
                    if(listado.get(j).getAplicaLealtad().equals("S"))
                    {
                        BoletosTabla[i][3] = "NO APLICA";
                                if(diaAplica.equals("LUNES") && listado.get(j).getL_LUNES().equals("S"))
                                {
                                    this.cerrar = true;
                                    BoletosTabla[i][3] = "APLICA";
                                }
                                if(diaAplica.equals("MARTES") && listado.get(j).getL_MARTES().equals("S"))
                                {
                                    BoletosTabla[i][3] = "APLICA";
                                    this.cerrar = true;
                                }
                                if(diaAplica.equals("MIÉRCOLES") && listado.get(j).getL_MIERCOLES().equals("S"))
                                {
                                    this.cerrar = true;
                                    BoletosTabla[i][3] = "APLICA";
                                }
                                if(diaAplica.equals("JUEVES") && listado.get(j).getL_JUEVES().equals("S"))
                                {
                                    this.cerrar = true;
                                    BoletosTabla[i][3] = "APLICA";
                                }
                                if(diaAplica.equals("VIERNES") && listado.get(j).getL_VIERNES().equals("S"))
                                {
                                    this.cerrar = true;
                                    BoletosTabla[i][3] = "APLICA";
                                }
                                if(diaAplica.equals("SÁBADO") && listado.get(j).getL_SABADO().equals("S"))
                                {
                                    this.cerrar = true;
                                    BoletosTabla[i][3] = "APLICA";
                                }
                                if(diaAplica.equals("DOMINGO") && listado.get(j).getL_DOMINGO().equals("S"))
                                {
                                    this.cerrar = true;
                                    BoletosTabla[i][3] = "APLICA";
                                }
                    }
                    else
                        BoletosTabla[i][3] = "NO APLICA";
                }
             }
        }
        System.out.println("cerrar: "+cerrar);
        if(cerrar)
        {
            initComponents();
            jLblBarraEstado.setBackground(ColoresInterfaz.cFondoPieEncabezado);
            jLblBarraEstado.setForeground(ColoresInterfaz.cTextoAyuda1);
            jLblBarraEstado.setFont(ColoresInterfaz.fuente3);
            limpiaProgreso();


            jtbl_boletos.setModel(xBoletos);
            xBoletos.setDataVector(BoletosTabla, encBoletos);
            centrarJDialog();
            AnchoColumnasVtaActual();
            jtbl_boletos.setDefaultRenderer(Object.class, new AttributiveCellRenderer1());
            jtbl_boletos.setColumnSelectionInterval(3,3);
            jtbl_boletos.setRowSelectionInterval(0,0);
            jtbl_boletos.requestFocus();
            if(!sesionVenta.getUserCon().getAplicacionVenta())
                jtxt_numTarjeta.requestFocus();
            jtxt_deslizarTarjeta.setVisible(false);
            //this.setVisible(true);
        }
        else
            this.setCompletado(false);
        if(!pmuestra)
             procesoManual(false);
        else
            this.setVisible(true);
        
    }
    
    private void AnchoColumnasVtaActual(){
        TableColumn column = null;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int anchoContenedor = (int)(screenSize.getWidth()-24);
        anchoContenedor = (int)(anchoContenedor-(anchoContenedor*0.25));
        //jScrDetalleBoletos.getWidth();
        //"Corrida-Hora", "#", "T", "Nombre Pasajero", "Origen-Destino", "Costo"
        for (int i = 0; i < 4; i++) {
            column = jtbl_boletos.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.05)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.04)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.51)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
            }
            column.setResizable(false);
            this.setCompletado(false);
        }
    }    
    
    public void validarMostrar(){
        System.out.println("validarMostrar() cerrar: "+cerrar);
        if(!this.cerrar)
        {
            this.setCompletado(false);
            this.dispose();            
        }//else this.setModal(true);
    }
       
    
    
    private void centrarJDialog(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(screenSize.width-24, screenSize.height-66);
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, (( screenSize.height - frameSize.height ) / 2)+22);
    }    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_boletos = new javax.swing.JTable();
        jLblBarraEstado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jtxt_numTarjeta = new javax.swing.JTextField();
        jtxt_numTarjeta = new JTextCardField();
        jtxt_deslizarTarjeta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtbl_boletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbl_boletos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_boletosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbl_boletosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtbl_boletos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Num. de Tarjeta: ");

        jtxt_numTarjeta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_numTarjetaFocusGained(evt);
            }
        });
        jtxt_numTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_numTarjetaKeyPressed(evt);
            }
        });

        jtxt_deslizarTarjeta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxt_deslizarTarjetaFocusGained(evt);
            }
        });
        jtxt_deslizarTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_deslizarTarjetaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jtxt_numTarjeta, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_deslizarTarjeta, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .add(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jtxt_numTarjeta)
                .add(jtxt_deslizarTarjeta))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 172, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_numTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_numTarjetaKeyPressed
        if(evt.getKeyCode() == 10)
        {
            procesoManual(true);
        }
        if(evt.getKeyCode() == 27)
        {
            
            jLabel1.setVisible(false);
            jtxt_numTarjeta.setVisible(false);
            limpiaProgreso();
            jtbl_boletos.requestFocus();
        }
    }//GEN-LAST:event_jtxt_numTarjetaKeyPressed

    private void jtxt_numTarjetaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_numTarjetaFocusGained
        jLabel1.setVisible(true);
        jtxt_numTarjeta.setVisible(true);
        jtxt_deslizarTarjeta.setVisible(false);
            if(!sesionVenta.getUserCon().getAplicacionVenta())
                jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">Enter</font> Aceptar|  <font color="+ColoresInterfaz.cHTML3+">ESC</font> Salir sin acumular estrellas</html>");
            else
            jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">Enter</font> Aceptar|  <font color="+ColoresInterfaz.cHTML3+">ESC</font> Salir del modo teclear tarjeta</html>");
        jtxt_numTarjeta.requestFocus();
    }//GEN-LAST:event_jtxt_numTarjetaFocusGained

    private void jtbl_boletosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_boletosKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_LEFT ){
            if(jtbl_boletos.getSelectedColumn() < 3)
                jtbl_boletos.setColumnSelectionInterval(3,3);
        }
    }//GEN-LAST:event_jtbl_boletosKeyReleased

    private void jtbl_boletosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_boletosKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_F1 && sesionVenta.getUserCon().getAplicacionVenta()){
            if(sesionVenta.isLectorSocioIntimo())
            {
                jtxt_deslizarTarjeta.setForeground(Color.white);
                jLabel1.setVisible(true);
                jtxt_deslizarTarjeta.setVisible(true);
                jLabel1.setText("Deslize la Tarjeta:");
                jtxt_deslizarTarjeta.requestFocus();
            }
            else
            {

                jLblBarraEstado.setText("<html><font color=ff0000>Deslice la Tarjeta</font> <font color="+ColoresInterfaz.cHTML3+">o presione la Tecla <font color=ff0000>CAN</font> de PinPad</font> para Cancelar Lectura</html>");
                if(!activoF1)
                {
                System.out.println("Activa el F1");
                activoF1 = true;
                //if(!realizaOperacion(_VD)) return;
                //this.jLblTitulo.setText("VENTA DIRECTA "+importe);
                //agregarPanel(jPnlVD);
                this.enProceso = 1;
                //jLblBarraEstado.setText("<html><font color="+ColoresInterfaz.cHTML3+">Tecla <font color=ff0000>CAN</font> de PinPad</font> Cancelar Lectura</html>");
                jProgreso = new JDlgBProgresoLealtad(new JObsBProgreso(0, 1000),this);
                if(worker!=null) worker = null;
                this.setRespondio(false);
                worker = new SwingWorker() {
                    public Object construct() {
                        //try {sesionVenta.TextOut = new FileWriter(sesionVenta.TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
                        System.out.println("LECTURA DE TARJETA DE LEALTAD: referencia = "+referencia);
                       // try{sesionVenta.TextOut.write(sesionVenta.formatoFechaHoraBD.format(new Date())+" COBRO VENTA: "+sesionVenta.getUserCon().getCc_Type()+" - "+sesionVenta.getUserCon().getTx_MerchantP()+" - "+referencia+" - "+sesionVenta.getUserCon().getTx_OperationTypeP()+"\n");} catch (IOException ex) {ex.printStackTrace();}
                        //try{sesionVenta.TextOut.close();} catch (IOException ex) {ex.printStackTrace();}
                        jClsPinPadTBResponse=jClsPinPadTBRequest.getLealtad(sesionVenta.getUserCon().getDbgSetUrl(),Amount,sesionVenta.getUserCon().getBs_User(),
                                                                                 sesionVenta.getUserCon().getBs_Pwd(),sesionVenta.getUserCon().getUsuarioNum(),
                                                                                 sesionVenta.getUserCon().getBs_Company(),sesionVenta.getUserCon().getBs_Branch(),
                                                                                 sesionVenta.getUserCon().getBs_Country(), sesionVenta.getUserCon().getCc_Type(),
                                                                                 sesionVenta.getUserCon().getTx_MerchantP(),referencia,sesionVenta.getUserCon().getTx_OperationTypeP(),
                                                                                 sesionVenta.getUserCon().getTx_Currency(),AMEXP);
                        System.out.println("SALI DE FUNCION NATIVA");
                        detenerProgreso();
                        setRespondio(true);
                        //mostrarResultado(true);
                        System.out.println("jClsPinPadTBResponse.getRspDsError(): "+jClsPinPadTBResponse.getRspDsError());
                        System.out.println("jClsPinPadTBResponse.getCc_Name(): "+jClsPinPadTBResponse.getCc_Name());
                        System.out.println("jClsPinPadTBResponse.getCc_Number(): "+jClsPinPadTBResponse.getCc_Number());
                         System.out.println("*******************************************************************");
                        imprimirCamposResultado();
                        limpiaProgreso();
                        System.out.println("Desactiva el F1 (Dentro del hilo)");
                        activoF1 = false;
                        System.out.println("antes de verificar jClsPinPadTBResponse.getCc_Name(): "+jClsPinPadTBResponse.getCc_Name());
                        if(jClsPinPadTBResponse.getCc_Name()!=null)
                        {
                            registros = new Vector();
                            if(jClsPinPadTBResponse.getCc_Name().trim().equals("") || !(jClsPinPadTBResponse.getCc_Name().trim().length()==10))
                            {
                                System.out.println("Se cancelo la operacion desde el Pinpad");
                                if(jClsPinPadTBResponse.getCc_Name().trim().equals(""))
                                    DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>!No se registro la tarjeta correctamente!, <br>Si desea acumular estrellas deslize la tarjeta de Socio Intimo</html>.", Color.RED);
                                else{
                                    if(!(jClsPinPadTBResponse.getCc_Name().trim().length()==10))
                                    DialogoAceptar.mostrarDialogo("TMS Venta.", "<html>!Número de tarjeta Inválido!, <br>Si desea acumular estrellas deslize una tarjeta de Socio Intimo</html>.", Color.RED);                                
                                    }
                                    limpiaProgreso();
                            }    
                            else
                            {
                             for(int i=0; i<jtbl_boletos.getRowCount();i++)
                             {
                                 
                                if(jtbl_boletos.getValueAt(i,3).toString().equals("APLICA")) 
                                {
                                    //System.out.println("jtbl_boletos.getValueAt("+i+",3) = "+jtbl_boletos.getValueAt(i,3));
                                    long tipoId = -1;
                                    for(int j=0; j<listado.size(); j++)
                                    {
                                      //  System.out.println("jtbl_boletos.getValueAt("+i+",1) = "+jtbl_boletos.getValueAt(i,1)+" ==> ("+j+")"+listado.get(j).getLetraTipo());
                                        if(jtbl_boletos.getValueAt(i,1).toString().trim().equals(listado.get(j).getLetraTipo()))
                                            tipoId = listado.get(j).getTipoPasajeroId();
                                    }
                                    String origen  = "";
                                    String destino = "";
                                    if(Boletos[i][31].toString().equals("R"))
                                    {
                                        if(Boletos[i][21].toString().equals(sesionVenta.getTerminalLealtadNombreO()))
                                        {
                                         origen  = ""+sesionVenta.getTerminalLealtadIdO();
                                         destino = ""+sesionVenta.getTerminalLealtadIdD();
                                        }
                                        else
                                        {
                                         destino = ""+sesionVenta.getTerminalLealtadIdO();
                                         origen  = ""+sesionVenta.getTerminalLealtadIdD();
                                        }
                                            
                                    }
                                    else
                                    {
                                         origen  = ""+sesionVenta.getTerminalLealtadIdO();
                                         destino = ""+sesionVenta.getTerminalLealtadIdD();
                                    }
                                        
                                    String producto = sesionVenta.getEmpresaId(Boletos[i][7].toString())+"" +
                                            sesionVenta.getServicioId(Boletos[i][8].toString())+ "" +
                                            origen+"" +
                                            destino+"" + tipoId;
//                                            sesionVenta.getTerminalLealtadId(Boletos[i][21].toString())+"" +
//                                            sesionVenta.getTerminalLealtadId(Boletos[i][22].toString())+"" + tipoId;
                                    if(Boletos[i][31]==null){producto = producto+"0";} else{ if(Boletos[i][31].equals("R")) producto = producto+"1";else producto = producto+"0";}
                                    System.out.println("producto("+i+") = "+producto);
                                    Vector vec = new Vector();
                                    vec.add(i);
                                    vec.add(producto);
                                    vec.add(jClsPinPadTBResponse.getCc_Name().trim());
                                    vec.add(referencia);
                                    String unidad = "";
                                    if(Boletos[i][7].equals("Terminal Las Torres Puebla") || Boletos[i][7].equals("Autobuses Unidos"))
                                        unidad = "TP";
                                    else
                                    {
                                        if(Boletos[i][7].equals("Contactos Terrestres") || Boletos[i][7].equals("Contactos Terrestres 1"))
                                            unidad = "CT";
                                        else
                                            unidad = "AM";
                                    }
                                    vec.add(unidad);
                                    registros.add(vec);
                                    Boletos[i][33] =referencia;
                                    Boletos[i][34] =jClsPinPadTBResponse.getCc_Name().trim();
                                    
                                    
                                } 
                             }
                             setCompletado(true);
                             cerrarVentana();
                            }//else se cancelo por el pinpad
                        }                        
                        return jClsPinPadTBResponse;
                    }
                };

                 //System.out.println("Desactiva el F1");
                 //activoF1 = false;
                }
            }
        }
        
        
        
        if(evt.getKeyCode() == KeyEvent.VK_F2 && sesionVenta.isPermiteMotoLealtad() ){
            jLabel1.setVisible(true);
            jLabel1.setText("Num. de Tarjeta:");
            jtxt_numTarjeta.setVisible(true);
            jtxt_numTarjeta.requestFocus();
            jtxt_deslizarTarjeta.setVisible(false);
        }        
        
        
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE ){
            this.setCompletado(false);
            this.dispose();
        }        
    }//GEN-LAST:event_jtbl_boletosKeyPressed

    private void jtxt_deslizarTarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_deslizarTarjetaKeyPressed
        if(evt.getKeyCode() == 10 && jtxt_deslizarTarjeta.isVisible())
        {
            if(sesionVenta.isLectorSocioIntimo())
            {
                String[] array = jtxt_deslizarTarjeta.getText().trim().split("&");
                if(array.length<2)
                {
                    JDlgAceptar DialogoAceptar = new JDlgAceptar();
                    DialogoAceptar.mostrarDialogo("TMS Venta.","Deslize una Tarjeta de Socio Intimo Válida.", Color.RED);
                    jtxt_deslizarTarjeta.setText("");
                    jtxt_deslizarTarjeta.requestFocus();
                    return;
                }
                jtxt_deslizarTarjeta.setText(array[1]);
            }
            procesoManual(true);
        }
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE ){
            //this.setCompletado(false);
            //this.dispose();
            jLabel1.setVisible(false);
            jtxt_deslizarTarjeta.setVisible(false);
            limpiaProgreso();
            jtbl_boletos.requestFocus();
        }

    }//GEN-LAST:event_jtxt_deslizarTarjetaKeyPressed

    private void jtxt_deslizarTarjetaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxt_deslizarTarjetaFocusGained
        if(sesionVenta.isLectorSocioIntimo())
            jLblBarraEstado.setText("<html> Deslize la Tarjeta de Socio Intimo  |  <font color="+ColoresInterfaz.cHTML3+">ESC</font> Salir del modo deslizar tarjeta</html>");
        jtxt_deslizarTarjeta.requestFocus();
    }//GEN-LAST:event_jtxt_deslizarTarjetaFocusGained
    
    private void imprimirCamposResultado(){
        System.out.println("jClsPinPadTBResponse.getCc_ExpMonth(): "+jClsPinPadTBResponse.getCc_ExpMonth());
        System.out.println("jClsPinPadTBResponse.getCc_ExpYear(): "+jClsPinPadTBResponse.getCc_ExpYear());
        System.out.println("jClsPinPadTBResponse.getCc_Name(): "+jClsPinPadTBResponse.getCc_Name());
        System.out.println("jClsPinPadTBResponse.getCc_Number(): "+jClsPinPadTBResponse.getCc_Number());
        System.out.println("jClsPinPadTBResponse.getCc_Type(): "+jClsPinPadTBResponse.getCc_Type());
        System.out.println("jClsPinPadTBResponse.getRspAuth(): "+jClsPinPadTBResponse.getRspAuth());
        System.out.println("jClsPinPadTBResponse.getRspCdError(): "+jClsPinPadTBResponse.getRspCdError());
        System.out.println("jClsPinPadTBResponse.getRspCdResponse(): "+jClsPinPadTBResponse.getRspCdResponse());
        System.out.println("jClsPinPadTBResponse.getRspDate(): "+jClsPinPadTBResponse.getRspDate());
        System.out.println("jClsPinPadTBResponse.getRspDsAdress(): "+jClsPinPadTBResponse.getRspDsAdress());
        System.out.println("jClsPinPadTBResponse.getRspDsCompany(): "+jClsPinPadTBResponse.getRspDsCompany());
        System.out.println("jClsPinPadTBResponse.getRspDsError(): "+jClsPinPadTBResponse.getRspDsError());
        System.out.println("jClsPinPadTBResponse.getRspDsMerchant(): "+jClsPinPadTBResponse.getRspDsMerchant());
        System.out.println("jClsPinPadTBResponse.getRspDsOperationType(): "+jClsPinPadTBResponse.getRspDsOperationType());
        System.out.println("jClsPinPadTBResponse.getRspDsResponse(): "+jClsPinPadTBResponse.getRspDsResponse());
        System.out.println("jClsPinPadTBResponse.getRspOperationNumber(): "+jClsPinPadTBResponse.getRspOperationNumber());
        System.out.println("jClsPinPadTBResponse.getRspTime(): "+jClsPinPadTBResponse.getRspTime());
        System.out.println("jClsPinPadTBResponse.getRspVoucher(): "+jClsPinPadTBResponse.getRspVoucher());
        System.out.println("jClsPinPadTBResponse.getRspXML(): "+jClsPinPadTBResponse.getRspXML());
        System.out.println("jClsPinPadTBResponse.getTx_Amount(): "+jClsPinPadTBResponse.getTx_Amount());
        System.out.println("jClsPinPadTBResponse.getTx_Reference(): "+jClsPinPadTBResponse.getTx_Reference());
        System.out.println("jClsPinPadTBResponse.getRspQryRePrint(): "+jClsPinPadTBResponse.getRspQryRePrint());
    }
        
    
    public void validarVentana(){
            this.setCompletado(false);
            this.dispose();
    }
    
    public void limpiaProgreso() {
    jLabel1.setVisible(false);
    jtxt_numTarjeta.setVisible(false);
    jtxt_deslizarTarjeta.setVisible(false);
    if(sesionVenta.isPermiteMotoLealtad())
        jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">F1</font> Leer Tarjeta|  <font color="+ColoresInterfaz.cHTML3+">F2</font> Teclear Num Tarjeta|  <font color="+ColoresInterfaz.cHTML3+">ESC</font> Salir sin acumular estrellas</html>");        
    else
    {
        jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">F1</font> Leer Tarjeta|  <font color="+ColoresInterfaz.cHTML3+">ESC</font> Salir sin acumular estrellas</html>");
    }
    if(!sesionVenta.getUserCon().getAplicacionVenta())
    {
            jLabel1.setVisible(true);
            jtxt_numTarjeta.setVisible(true);
            jtxt_deslizarTarjeta.setVisible(false);
            jLblBarraEstado.setText("<html> <font color="+ColoresInterfaz.cHTML3+">Enter</font> Aceptar|  <font color="+ColoresInterfaz.cHTML3+">ESC</font> Salir sin acumular estrellas</html>");
            jtxt_numTarjeta.requestFocus();
    }
//        limpiaCampos();
//        jPnlContenido.removeAll();
//        this.jPnlContenido.setLayout(null);
//        this.getContentPane().repaint();
//        enProceso=0;
//        jLblTitulo.setText("");
//        jLblRespuesta.setText("");
//        jLblBarraEstado.setText(cadenaMenu);
//        this.jPnlContenedorCentral.requestFocusInWindow();
    }    
    
    private void detenerProgreso(){
        if(jProgreso!=null){
            jProgreso.finaliza();
            jProgreso = null;
        }
    }     
    
    private void cerrarVentana(){
        this.dispose();
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new JDlgAcumulaEstrellas(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
    
    private boolean respondio = false;
    
    public boolean isRespondio() {
        return respondio;
    }

    public void setRespondio(boolean respondio) {
        this.respondio = respondio;
    }    
    
    public Object[][] getBoletos() {
        return this.Boletos;
    }     
    
    public void procesoManual(boolean moto){
            String numTar = "";
            if(moto)
            {
                if(sesionVenta.isLectorSocioIntimo() && jtxt_deslizarTarjeta.isVisible())
                    numTar = jtxt_deslizarTarjeta.getText().trim();
                else
                    numTar = jtxt_numTarjeta.getText().trim();
                System.out.println("numTar: "+numTar);
                    if(!(numTar.length() == 10))
                    {

                        JDlgAceptar DialogoAceptar = new JDlgAceptar();
                        if(sesionVenta.isLectorSocioIntimo()  && jtxt_deslizarTarjeta.isVisible())
                        {
                            DialogoAceptar.mostrarDialogo("TMS Venta.", "Deslize una Tarjeta de Socio Intimo Válida.", Color.RED);
                            jtxt_deslizarTarjeta.setText("");
                            jtxt_deslizarTarjeta.requestFocus();
                        }
                        else
                            DialogoAceptar.mostrarDialogo("TMS Venta.","Introduce un Número de Tarjeta Válido.", Color.RED);
                        return;
                    }
                    System.out.println("Entra en modo Moto para lealtad....");
            }
            else
                numTar = this.numeroTarjeta;
            registros = new Vector();
            for(int i=0; i<jtbl_boletos.getRowCount();i++)
             {

                if(jtbl_boletos.getValueAt(i,3).toString().equals("APLICA")) 
                {
                    long tipoId = -1;
                    for(int j=0; j<listado.size(); j++)
                    {
                        if(jtbl_boletos.getValueAt(i,1).toString().trim().equals(listado.get(j).getLetraTipo()))
                            tipoId = listado.get(j).getTipoPasajeroId();
                    }
                    String origen  = "";
                    String destino = "";
                    if(Boletos[i][31].toString().equals("R"))
                    {
                        if(Boletos[i][21].toString().equals(sesionVenta.getTerminalLealtadNombreO()))
                        {
                         origen  = ""+sesionVenta.getTerminalLealtadIdO();
                         destino = ""+sesionVenta.getTerminalLealtadIdD();
                        }
                        else
                        {
                         destino = ""+sesionVenta.getTerminalLealtadIdO();
                         origen  = ""+sesionVenta.getTerminalLealtadIdD();
                        }

                    }
                    else
                    {
                         origen  = ""+sesionVenta.getTerminalLealtadIdO();
                         destino = ""+sesionVenta.getTerminalLealtadIdD();
                    }
                    
                    String producto = sesionVenta.getEmpresaId(Boletos[i][7].toString())+"" +
                            sesionVenta.getServicioId(Boletos[i][8].toString())+ "" +
                            origen+"" +
                            destino+"" + tipoId;
//                            sesionVenta.getTerminalLealtadId(Boletos[i][21].toString())+"" +
//                            sesionVenta.getTerminalLealtadId(Boletos[i][22].toString())+"" + tipoId;
                    if(Boletos[i][31]==null)producto = producto+"0"; else  {if(Boletos[i][31].equals("R")) producto = producto+"1";else producto = producto+"0";}
                    System.out.println("producto("+i+")(Moto) = "+producto);
                    Vector vec = new Vector();
                    vec.add(i);
                    vec.add(producto);
                    vec.add(numTar);
                    vec.add(referencia);
                    String unidad = "";
                    if(Boletos[i][7].equals("Terminal Las Torres Puebla") || Boletos[i][7].equals("Autobuses Unidos"))
                        unidad = "TP";
                    else
                    {
                        if(Boletos[i][7].equals("Contactos Terrestres") || Boletos[i][7].equals("Contactos Terrestres 1"))
                            unidad = "CT";
                        else
                            unidad = "AM";
                    }
                    vec.add(unidad);
                    registros.add(vec);
                    Boletos[i][33] = referencia;
                    Boletos[i][34] = numTar;
                } 
             }
             setCompletado(true);
             cerrarVentana();           
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbl_boletos;
    private javax.swing.JTextField jtxt_deslizarTarjeta;
    private javax.swing.JTextField jtxt_numTarjeta;
    // End of variables declaration//GEN-END:variables
    


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
            if(row == jtbl_boletos.getSelectedRow() && column == jtbl_boletos.getSelectedColumn()){
                //if(jtbl_boletos.getSelectedRow() == filaBancomer && jtbl_boletos.getSelectedColumn() == 4) this.setForeground(SELECCION);
                //else 
                    this.setForeground(Color.BLACK);
                this.setBackground(SELECCION);
            }
            else{
                this.setBackground(ColoresInterfaz.cFondoVentana);
                this.setForeground(BLANCO);
            }
                
            //if(column==2 || column==3) setHorizontalAlignment(RIGHT );
            //else setHorizontalAlignment(LEFT);
          return this;
        }
    }

    public Vector getRegistros() {
        return registros;
    }

    public void setRegistros(Vector registros) {
        this.registros = registros;
    }

}//Class