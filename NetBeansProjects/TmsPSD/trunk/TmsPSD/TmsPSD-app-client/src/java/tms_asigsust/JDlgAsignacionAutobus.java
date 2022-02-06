/*
 * JDlgSustitucion.java
 *
 * Created on 9 de octubre de 2007, 06:15 PM
 */

package tms_asigsust;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_psd.*;
import tms_psd.util.BarraMensajes;
import tms_psd.util.JDlgAceptar;
import tms_psd.util.JDlgSiNo;
import xertms.entidad.TmsAutobusesXTbl;

/**
 *
 * @author  ocruz
 */
public class JDlgAsignacionAutobus extends javax.swing.JDialog{

    /**
     * Creates new form JDlgSustitucion
     */
    public JDlgAsignacionAutobus(SesionVenta pSesionVenta, int pFilaCorrida, Object pOperSust,String pfecha) {
        this.filaCorrida = pFilaCorrida;
        this.sesionVenta = pSesionVenta;
        this.operSust = pOperSust;
        this.fecha = pfecha;
        initComponents();
        this.setModal(true);
        centrarJDialog();
        this.jTxtNumEco.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblAutobuses.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLblBarraEstado = new javax.swing.JLabel();
        jScpAutobuses = new javax.swing.JScrollPane();
        jTblAutobuses = new javax.swing.JTable();
        jTblAutobuses.setFocusTraversalKeysEnabled(false);
        jLabel1 = new javax.swing.JLabel();
        jTxtNumEco = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Asignacion de Autobus");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLblBarraEstado.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLblBarraEstado.setText("<html><font color=ff0000>ENTER</font> Buscar Autobuses | <font color=ff0000>ESC</font> Cancelar</html>");

        jScpAutobuses.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autobuses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.black));
        jTblAutobuses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblAutobuses.setModel(xAutobuses);
        jTblAutobuses.setName("3");
        jTblAutobuses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblAutobusesKeyPressed(evt);
            }
        });

        jScpAutobuses.setViewportView(jTblAutobuses);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Numero Economico:");

        jTxtNumEco.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTxtNumEco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTxtNumEco.setText("%");
        jTxtNumEco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtNumEcoKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLblBarraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTxtNumEco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(557, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScpAutobuses, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTxtNumEco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpAutobuses, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLblBarraEstado))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTblAutobusesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblAutobusesKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                xAutobuses.setDataVector(null, autobuses);
                AnchoColumnasDetalle();
                this.operacionRealizada = 0;
                jLblBarraEstado.setText("<html><font color=ff0000>ENTER</font> Buscar Autobuses | <font color=ff0000>ESC</font> Cancelar</html>");
                jTxtNumEco.setText("%");
                jTxtNumEco.requestFocusInWindow();
            break;
            case KeyEvent.VK_F11:
                // validar ocupacion - disponibilidad
                int res=validaCapacidadAsientosOcupadosDisponibles(filaCorrida, listaAutobuses.get(jTblAutobuses.getSelectedRow()).getNumeroEconomico(), sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getClaveCorrida());
                if(res==-1){ this.operacionRealizada = 0; this.dispose(); return; }
                if(res==0) return;
                //
                DialogoSiNo = new JDlgSiNo("�Confirme...!", "<html>�Realmente desea asignar el autobus <font color=ff0000>"+jTblAutobuses.getValueAt(jTblAutobuses.getSelectedRow(),0).toString()+"</font><br>" +
                        "a la corrida <font color=ff0000>"+sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getClaveCorrida()+"</font>?</html>");
                if(!DialogoSiNo.getResultado()) return;
                //System.out.println(jTblAutobuses.getSelectedRow()+"<fila   "+listaAutobuses.get(jTblAutobuses.getSelectedRow()).getNumeroEconomico()+" "+listaAutobuses.get(jTblAutobuses.getSelectedRow()).getEmpresaId());
                if(listaAutobuses.get(jTblAutobuses.getSelectedRow()).getEmpresaId() == null){
                    this.empresaId = sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getEmpresaId();
                    this.empresa = sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getEmpresa();
                }
                else{
                    this.empresaId = listaAutobuses.get(jTblAutobuses.getSelectedRow()).getEmpresaId();
                    this.empresa = sesionVenta.getEmpresa(this.empresaId);
                    if(!sesionVenta.validaServicioEmpresa(sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getServicio(), sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getRutaId(), this.empresa)){
                        DialogoAceptar = new JDlgAceptar("Asignacion de autobus.","El servicio, ruta y empresa de esta corrida no corresponden.", Color.RED);
                        xAutobuses.setDataVector(null, autobuses);
                        AnchoColumnasDetalle();
                        this.operacionRealizada = 0;
                        jTxtNumEco.setText("%");
                        jTxtNumEco.requestFocusInWindow();
                        return;
                    }
                }
                if(!sesionVenta.asignaAutobus(sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getCorridaId(),
                        listaAutobuses.get(jTblAutobuses.getSelectedRow()).getAutobusId(), listaAutobuses.get(jTblAutobuses.getSelectedRow()).getNumeroEconomico(),
                        listaAutobuses.get(jTblAutobuses.getSelectedRow()).getPlantillaEncId(),
                        this.empresaId, this.empresa)){
                    DialogoAceptar = new JDlgAceptar("�Asignacion incorrecta!","Contacte al administrador del sistema.", Color.RED);
                    xAutobuses.setDataVector(null, autobuses);
                    AnchoColumnasDetalle();
                    this.operacionRealizada = 0;
                    jTxtNumEco.setText("%");
                    jTxtNumEco.requestFocusInWindow();
                    return;
                }
                else{
                    if(!sesionVenta.registraEnRolAutobusSust(listaAutobuses.get(jTblAutobuses.getSelectedRow()).getAutobusId())){
                            DialogoAceptar = new JDlgAceptar("�No fue posible cambiar el estado del autobus asignado!", "Contacte al administrador del sistema.", Color.RED);
                            xAutobuses.setDataVector(null, autobuses);
                        AnchoColumnasDetalle();
                        this.operacionRealizada = 0;
                        jTxtNumEco.setText("%");
                        jTxtNumEco.requestFocusInWindow();
                        return;
                    }
                    this.NumeroEconomicoAsig = listaAutobuses.get(jTblAutobuses.getSelectedRow()).getNumeroEconomico();
                    this.NumeroEconomicoAsigId =  listaAutobuses.get(jTblAutobuses.getSelectedRow()).getAutobusId();
                    this.flotillaId =  (listaAutobuses.get(jTblAutobuses.getSelectedRow()).getFlotillaId()==null ? "" : String.valueOf(listaAutobuses.get(jTblAutobuses.getSelectedRow()).getFlotillaId()));
                    this.flotilla = (this.flotillaId.equals("") ? "" : sesionVenta.getFlotilla(Long.valueOf(this.flotillaId)));
                    if(!datos[jTblAutobuses.getSelectedRow()][2].toString().equals("")){
                        if(!sesionVenta.asignaOperador(sesionVenta.getTmsMonitorCorridaV().get(filaCorrida).getCorridaId(),
                        listaAutobuses.get(jTblAutobuses.getSelectedRow()).getOperadorIdPlanta(), datos[jTblAutobuses.getSelectedRow()][3].toString())){
                            DialogoAceptar = new JDlgAceptar("�Asignacion incorrecta!","Contacte al administrador del sistema.", Color.RED);
                            this.operacionRealizada = 1;
                            this.dispose();
                            return;
                        }
                        else{
                            if(!sesionVenta.registraEnRolOperadorSust(listaAutobuses.get(jTblAutobuses.getSelectedRow()).getOperadorIdPlanta())){
                                DialogoAceptar = new JDlgAceptar("�No fue posible cambiar el estado del operador asignado!", "Contacte al administrador del sistema.", Color.RED);
                                this.operacionRealizada = 1;
                                this.dispose();
                                return;
                            }
                            this.NumeroAsig = datos[jTblAutobuses.getSelectedRow()][3].toString();
                            this.AsigId =  listaAutobuses.get(jTblAutobuses.getSelectedRow()).getOperadorIdPlanta();
                            this.NombreAsig = datos[jTblAutobuses.getSelectedRow()][2].toString();
                            this.operacionRealizada = 2;
                            this.dispose();
                            return;
                        }
                    }
                    else{
                        if(this.operSust!=null && !this.operSust.equals("")){
                            this.operacionRealizada = 1;
                            DialogoAceptar = new JDlgAceptar("�Autobus sin operador de planta!", "Sin embargo, la corrida tiene operador sustituto.", Color.RED);
                            this.dispose();
                            return;
                        }
                        DialogoSiNo = new JDlgSiNo("�Autobus sin operador de planta!", "�Desea Asignar un operador?");
                        if(!DialogoSiNo.getResultado()){
                            this.operacionRealizada = 1;
                            this.dispose();
                            return;
                        }
                        JDlgAsignacionOperador jDlgAsigOper = new JDlgAsignacionOperador(sesionVenta, filaCorrida,this.fecha);
                        jDlgAsigOper.setVisible(true);
                        if(jDlgAsigOper.getResultado()){
                            this.NumeroAsig = jDlgAsigOper.getNumeroOperadorAsig();
                            this.AsigId =  jDlgAsigOper.getNumeroOperadorAsigId();
                            this.NombreAsig = jDlgAsigOper.getNombreOperadorAsig();
                            this.operacionRealizada = 2;
                            this.dispose();
                            return;
                        }
                        this.operacionRealizada = 1;
                        this.dispose();
                        return;
                    }
                }
        }
    }//GEN-LAST:event_jTblAutobusesKeyPressed

    private void jTxtNumEcoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtNumEcoKeyPressed
// TODO add your handling code here:
        switch(evt.getKeyCode()){
            case KeyEvent.VK_ENTER:
                if(jTxtNumEco.getText().equals("")){
                    DialogoAceptar = new JDlgAceptar("�Criterio invalido!","Ingrese un criterio de busqueda (% para buscar todo).", Color.RED);
                    return;
                }
                listaAutobuses = sesionVenta.queryAutobusesByNumEco(jTxtNumEco.getText());
                
                if(listaAutobuses==null || listaAutobuses.size()==0){
                    jTxtNumEco.selectAll();
                    DialogoAceptar = new JDlgAceptar("�La consulta no genero resultados!","Ingrese un criterio de busqueda (% para buscar todo).", Color.RED);
                    return;
                }
                datos = new Object[listaAutobuses.size()][4];
                for(int i=0; i<listaAutobuses.size(); i++){
                    datos[i][0] = listaAutobuses.get(i).getNumeroEconomico();
                    datos[i][1] = "Estado: "+sesionVenta.getEstadoNombre(listaAutobuses.get(i).getComponente1Id())+
                                  " - Ubicacion: "+sesionVenta.getEstadoNombre(listaAutobuses.get(i).getComponente2Id())+
                                  " - Actividad: "+sesionVenta.getEstadoNombre(listaAutobuses.get(i).getComponente3Id());
                    datos[i][2] = (listaAutobuses.get(i).getOperadorIdPlanta()== null ? "" : sesionVenta.getNombreOperador(listaAutobuses.get(i).getOperadorIdPlanta()));
                    datos[i][3] = (listaAutobuses.get(i).getOperadorIdPlanta()== null ? "" : sesionVenta.getClaveOperador(listaAutobuses.get(i).getOperadorIdPlanta()));
                }
                xAutobuses.setDataVector(datos, autobuses);
                AnchoColumnasDetalle();
                jLblBarraEstado.setText("<html><font color=ff0000>F11</font> Asignar Autobus | <font color=ff0000>ESC</font> Nueva Busqueda</html>");
                jTblAutobuses.setColumnSelectionInterval(0,0);
                jTblAutobuses.setRowSelectionInterval(0,0);
                jTblAutobuses.requestFocusInWindow();
            break;
            case KeyEvent.VK_ESCAPE:
                this.dispose();
            break;
        }
    }//GEN-LAST:event_jTxtNumEcoKeyPressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        jTxtNumEco.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    private void centrarJDialog(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
    }
    
    private void AnchoColumnasDetalle(){
        TableColumn column = null;
        int anchoContenedor = jScpAutobuses.getWidth();
        for (int i = 0; i < jTblAutobuses.getColumnCount(); i++) {
            column = jTblAutobuses.getColumnModel().getColumn(i);
            switch(i){
                //"Autobus", "Flotilla", "Estado", "Proxima Asignacion"
                case 0:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 1:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.55)); break;
                case 2:  column.setPreferredWidth(Math.round(anchoContenedor*(float)0.35)); break;
            }
            column.setResizable(false);
        }
    }
    
    public int getResultado(){ return this.operacionRealizada; }
    
    public String getEmpresa(){ return this.empresa; }
    
    public String getNumeroEconomicoAsig(){ return this.NumeroEconomicoAsig; }
    
    public long getNumeroEconomicoAsigId(){ return this.NumeroEconomicoAsigId; }
    
    public String getFlotillaId(){ return this.flotillaId; }
    
    public String getFlotilla(){ return this.flotilla; }
    
    public String getNumeroOperadorAsig(){ return this.NumeroAsig; }
    
    public long getNumeroOperadorAsigId(){ return this.AsigId; }
    
    public String getNombreOperadorAsig(){ return this.NombreAsig; }
    
    private int validaCapacidadAsientosOcupadosDisponibles(int fSel, String numeroEco, String claveCorrida) {
        int ocuCorrida = sesionVenta.getOcupacion(fSel);
        if(ocuCorrida==-1){
            DialogoAceptar = new JDlgAceptar("�Esta corrida no es valida!", "Posiblemente la corrida ya no esta abierta.", Color.RED);
            return -1;
        }
        int ocuAutobusNuevo = sesionVenta.getCapacidadAsientosAutobus(numeroEco);
        if(ocuAutobusNuevo==-1){
            DialogoAceptar = new JDlgAceptar("�Configuracion de autobus incorrecta!", "No es posible determinar su capacidad de asientos.", Color.RED);
            return 0;
        }
        
        if(ocuCorrida>ocuAutobusNuevo){
            DialogoAceptar = new JDlgAceptar("�Asientos insuficientes!", "El autobus seleccionado no cuenta con suficientes asientos.", Color.RED);
            return 0;
        }
        
        int maxAsientoOcu = sesionVenta.maxAsientoOcupado(claveCorrida);
        
        if(maxAsientoOcu>ocuAutobusNuevo){
            DialogoAceptar = new JDlgAceptar("�Asientos finales ocupados!", "<html>La corrida cuenta con asientos finales ocupados y la numeracion<br>de asientos del autobus seleccionado no los contiene.</html>", Color.RED);
            return 0;
        }
        
        return 1;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblBarraEstado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScpAutobuses;
    private javax.swing.JTable jTblAutobuses;
    private javax.swing.JTextField jTxtNumEco;
    // End of variables declaration//GEN-END:variables
    private SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    private JDlgAceptar DialogoAceptar;
    private Object[] autobuses = {"Autobus", "Estado", "Operador de Planta"};
    private DefaultTableModel xAutobuses = new DefaultTableModel(null, autobuses){
            public boolean isCellEditable(int row, int col) {
                return false;
            }   
    };
    private int operacionRealizada = 0; // 0-nada 1-bus 2-bus y operador
    private SesionVenta sesionVenta = null;
    private int filaCorrida;
    private BarraMensajes barraMensajes = new BarraMensajes();
    private Color colorDialogoActivo = new Color(0,83,255);
    private long empresaId;
    private String empresa="";
    private String NumeroEconomicoAsig="";
    private long NumeroEconomicoAsigId;
    private String flotilla;
    private String flotillaId;   
    private String NumeroAsig="";
    private String NombreAsig="";
    private long AsigId;
    private List<TmsAutobusesXTbl> listaAutobuses=null;
    private Object[][] datos = null;
    private JDlgSiNo DialogoSiNo;
    private Object operSust=null;
    private final String fecha;
}