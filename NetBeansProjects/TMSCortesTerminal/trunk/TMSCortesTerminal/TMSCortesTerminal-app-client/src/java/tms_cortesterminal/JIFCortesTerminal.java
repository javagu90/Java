/*
 * JIFCortesTerminal.java
 *
 * Created on 15 de agosto de 2007, 12:16 PM
 */

package tms_cortesterminal;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import tms_cortesterminal.util.JClsAbreSocketAS;
import tms_cortesterminal.util.JClsImpresionReporte;
import tms_cortesterminal.util.JDlgSiNo;
import tms_cortesterminal.util.PcInfo;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tms_TextFields.JDateTextField;
import tms_cortesterminal.util.JDlgAceptar;
import tms_cortesterminal.util.JDlgOpcionesImpresionCorte;
import tms_cortesterminal.util.JDlgCorteFinDia;
import xertms.entidad.TmsBDConfigTbl;
import xertms.solicitud.TmsCorteTerminalFacadeRemote;

/**
 *
 * @author  ocruz
 */
public class JIFCortesTerminal extends JInternalFrame implements KeyListener{
    
    /**
     * Creates new form JIFCortesTerminal
     */
    public JIFCortesTerminal(Vector datosIniciales) {
        this.datosIniciales = datosIniciales;
        this.pendiente = "";
        this.USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString());
        this.USUARIO_NUMERO = datosIniciales.get(1).toString();
        this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
        this.SESION_ID = Long.valueOf(datosIniciales.get(3).toString());
        this.MENU_ID = Long.valueOf(datosIniciales.get(4).toString());
        tx = new JClsAbreSocketAS(datosIniciales.get(5).toString(),
                Integer.valueOf(datosIniciales.get(6).toString()));
        if(!tx.abreSocketAS()){
            inicioGral=false;
            return;
        }
        sesionCorteTaqFacate = lookupTmsCorteTaqFacade();
        jClsImpresionReporte = new JClsImpresionReporte(c);
        
        if(!ObtenerTerminalPrefijo()){
            inicioGral=false;
            return;
        }
        
        if(!obtieneCaja()){
            inicioGral=false;
            return;
        }
        
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

        this.jTblCortes.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFiltro.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);
        this.jCkbFiltro.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);
        this.jCboTerminal.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        jTblCortes.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
            jTblCortes.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).get(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)));
        inicioGral=true;
    }
    
    public boolean getInicioGral(){ return this.inicioGral; }
    
    private TmsCorteTerminalFacadeRemote lookupTmsCorteTaqFacade() {
        try {
            c = new InitialContext();
            return (TmsCorteTerminalFacadeRemote) c.lookup("xertms.solicitud.TmsCorteTerminalFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    private void AnchoColumnas(){
        TableColumn column = null;
        int anchoContenedor = jScpCortes.getWidth();
        for (int i = 0; i < 5; i++) {
            column = jTblCortes.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.10)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.50)); break;
                case 3: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); break;
                case 4: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.15)); break;
            }
            column.setResizable(false);
        }
    }
    
    private void CerrarIFrame(String mensaje){
        dialogo = new JDlgSiNo("¡Confirme!", mensaje);
        if(!dialogo.getResultado()) return;
        this.dispose();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScpCortes = new javax.swing.JScrollPane();
        jTblCortes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTxtFiltro = jTxtFiltro = new JDateTextField();
        jCkbFiltro = new javax.swing.JCheckBox();
        jCboTerminal = new javax.swing.JComboBox();
        jCboTerminal = new JComboBox(getOrigenesDBLink());
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTblCortes.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTblCortes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblCortes.setModel(xListado);
        jTblCortes.addKeyListener(this);
        jTblCortes.setFocusTraversalKeysEnabled(true);
        jTblCortes.getTableHeader().setReorderingAllowed(false);
        jTblCortes.setDefaultRenderer(Object.class, new colorCorteEstadoRenderer());
        jScpCortes.setViewportView(jTblCortes);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));
        jTxtFiltro.setName("jTxtFiltro");
        jTxtFiltro.addKeyListener(this);
        jTxtFiltro.setFocusTraversalKeysEnabled(true);
        jTxtFiltro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtFiltroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtFiltroFocusLost(evt);
            }
        });

        jCkbFiltro.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbFiltro.setText("Solo Pendientes");
        jCkbFiltro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbFiltro.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbFiltro.setName("Paloma");
        jCkbFiltro.addKeyListener(this);
        jCkbFiltro.setFocusTraversalKeysEnabled(true);
        jCkbFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCkbFiltroItemStateChanged(evt);
            }
        });
        jCkbFiltro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCkbFiltroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCkbFiltroFocusLost(evt);
            }
        });

        jCboTerminal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCboTerminal.setName("Terminal");
        jCboTerminal.addKeyListener(this);
        jCboTerminal.setFocusTraversalKeysEnabled(false);
        jCboTerminal.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"none");
        jCboTerminal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCboTerminalFocusGained(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jTxtFiltro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCkbFiltro)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCboTerminal, 0, 237, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jCkbFiltro)
                    .add(jTxtFiltro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCboTerminal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(3, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("<html><font color=ff0000>F1</font> Ingresar fecha | <font color=ff0000>F2</font> Pendientes/Realizados | <font color=ff0000>F3</font> Terminal | <font color=ff0000>ENTER</font> Buscar corte | <font color=ff0000>F5</font> Reimprimir | <font color=ff0000>F6</font> Corte de dia |<br><font color=ff0000>F4</font> Cerrar aplicacion</html>");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado de corte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0)));
        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText(" Pendiente ");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(255, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText(" En proceso ");
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(0, 255, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText(" Realizado ");
        jLabel5.setOpaque(true);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jLabel4)
                    .add(jLabel5))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Rev.03.09.10");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(667, Short.MAX_VALUE)
                .add(jLabel1)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScpCortes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel1, 0, 55, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpCortes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtFiltroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFiltroFocusLost
// TODO add your handling code here:
        jTxtFiltro.setBackground(Color.WHITE);
    }//GEN-LAST:event_jTxtFiltroFocusLost

    private void jCkbFiltroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCkbFiltroFocusLost
// TODO add your handling code here:
        jCkbFiltro.setOpaque(false);
    }//GEN-LAST:event_jCkbFiltroFocusLost

    private void jCboTerminalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboTerminalFocusGained
// TODO add your handling code here:
        if(jTblCortes.getRowCount()>0){
            xListado.setDataVector(null, encabezado);
            AnchoColumnas();
        }
    }//GEN-LAST:event_jCboTerminalFocusGained

    private void jCkbFiltroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCkbFiltroFocusGained
// TODO add your handling code here:
        jCkbFiltro.setOpaque(true);
        jCkbFiltro.setBackground(xorColorFondo);
        if(jTblCortes.getRowCount()>0){
            xListado.setDataVector(null, encabezado);
            AnchoColumnas();
        }
    }//GEN-LAST:event_jCkbFiltroFocusGained

    private void jTxtFiltroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFiltroFocusGained
// TODO add your handling code here:
        jTxtFiltro.setBackground(xorColorFondo);
        if(jTblCortes.getRowCount()>0){
            xListado.setDataVector(null, encabezado);
            AnchoColumnas();
            jTxtFiltro.setText(formatoFecha.format(new Date()));
            jTxtFiltro.selectAll();
            jTxtFiltro.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTxtFiltroFocusGained
    
    private void CorteDia(){
        JDlgCorteFinDia dlg = new JDlgCorteFinDia(null,true);
        dlg.setVisible(true);
        String strFecha = dlg.getFecha();
        String strFechaInicial = strFecha+" 00:00:00";
        String strFechaFinal = strFecha+" 23:59:59";
        
        if(strFecha.equals("")) return;
        Timestamp fi = null, ff = null;
        try {
//            fi = new Timestamp(formatoFechaHora.parse(strFecha+" 00:00:01").getTime()-86400000);
//            ff = new Timestamp(formatoFechaHora.parse(strFecha+" 23:59:59").getTime()-86400000);
            // 150109 IMM Nueva fecha de corte
            strFechaInicial = sesionCorteTaqFacate.getFechaInicialCorte(strFecha);
            strFechaFinal = sesionCorteTaqFacate.getFechaFinalCorte(strFecha);
            fi = new Timestamp(formatoFechaHora.parse(strFechaInicial).getTime());
            ff = new Timestamp(formatoFechaHora.parse(strFechaFinal).getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        System.out.println(strFecha+" ---fechas "+fi.toString()+" - "+ff.toString());
        if(!tx.abreSocketAS()){
            DialogoAceptar.mostrarDialogo("¡No existe conexion con la base de datos!","Contacte al administrador del sistema.", Color.RED);
            return;
        }
        Object x = sesionCorteTaqFacate.queryTmsCortesPendientesEnDiaLaboral(strFecha);
        System.out.println("PENDIENTES: "+x);
        if(x==null){
            DialogoAceptar.mostrarDialogo("¡No existe conexion con la base de datos!","Contacte al administrador del sistema.", Color.RED);
            /*if(jTblCortes.getRowCount()>0) jTblCortes.requestFocusInWindow();
            else jTxtFiltro.requestFocusInWindow();*/
            return;
        }
        boolean y = Boolean.valueOf(x.toString());
        if(y){
            DialogoAceptar.mostrarDialogo("¡Imposible realizar corte de dia!","Aun existen cortes pendientes para este dia.", Color.RED);
            /*if(jTblCortes.getRowCount()>0) jTblCortes.requestFocusInWindow();
            else jTxtFiltro.requestFocusInWindow();*/
            return;
        }
        String ids = sesionCorteTaqFacate.buscaCortesFinDia(fi,ff);
        if(ids.equals(""))
        {
            DialogoAceptar.mostrarDialogo("¡Imposible realizar corte de dia!","No existen cortes realizados para este dia.", Color.RED);
            return;
        }
        
        // IMPRIMIR CORTE DE FIN DE DIA
        if(!jClsImpresionReporte.imprimirCorte(null, fi, ff, "D", "N", "CORTE_DIA_PRINCIPAL.jasper",ids)){
            DialogoAceptar.mostrarDialogo("¡No fue posible mostrar el reporte!","Contacte al administrador del sistema.", Color.RED);
        }
    }
    
    private void Reimprimir(){
        fila = jTblCortes.getSelectedRow();
        if(fila==-1){
            DialogoAceptar.mostrarDialogo("¡No ha seleccionado ningun corte!","Presione ENTER para continuar...", Color.RED);
            return;
        }
        if(listado[fila][5].equals("P") || listado[fila][5].equals("T") || listado[fila][5].equals("E") || listado[fila][5].equals("B")){
            if(listado[fila][5].equals("P") || listado[fila][5].equals("T"))
                DialogoAceptar.mostrarDialogo("¡Este corte aun esta pendiente!","No es posible realizar la reimpresion.", Color.RED);
            else
                DialogoAceptar.mostrarDialogo("¡Este corte esta en proceso!","Contacte al administrador del sistema.", Color.RED);
            jTblCortes.setRowSelectionInterval(0,0);
            jTblCortes.requestFocusInWindow();
            return;
        }
        // REIMPRIMIR CORTE RECAUDADO
        String strFecha=xListado.getValueAt(fila,3).toString().substring(0,10);
        JDlgOpcionesImpresionCorte dlg = new JDlgOpcionesImpresionCorte(null, true, "¡Reimprimir Corte!");
        dlg.setVisible(true);
        if(dlg.getDetalle().equals("")) return;
        if(!jClsImpresionReporte.imprimirCorte(listado[fila][0].toString(), null, null, "T", dlg.getDetalle(), "CORTE_PRINCIPAL.jasper",listado[fila][0].toString())){
            DialogoAceptar.mostrarDialogo("¡No fue posible mostrar el reporte!","Contacte al administrador del sistema.", Color.RED);
        }
    }
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        xListado.setDataVector(null,encabezado);
        AnchoColumnas();
        this.jTxtFiltro.setText(formatoFecha.format(new Date()));
        jTxtFiltro.selectAll();
        this.jTxtFiltro.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    private void jCkbFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCkbFiltroItemStateChanged
// TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED)
            this.pendiente = "P";
        else
            this.pendiente = "";
    }//GEN-LAST:event_jCkbFiltroItemStateChanged
    
    private void Filtro(){
        String strFecha = jTxtFiltro.getText();
        if(strFecha.equals("") || strFecha.length()==1 || strFecha.length()==4 || (strFecha.length()>6 &&  strFecha.length()<10)){
            DialogoAceptar.mostrarDialogo("¡No es posible buscar corte!","Escriba una fecha válida.", Color.RED);
            xListado.setDataVector(null, encabezado);
            AnchoColumnas();
            this.jTxtFiltro.setText(formatoFecha.format(new Date()));
            jTxtFiltro.selectAll();
            jTxtFiltro.requestFocusInWindow();
            return;
        }
        
        if(strFecha.length()==2 || strFecha.length()==3){
            String dtStrFecha = formatoFecha.format(new Date()).substring(3);
            if(strFecha.length()==2) strFecha = strFecha +"/"+dtStrFecha;
            else strFecha = strFecha+dtStrFecha;
            jTxtFiltro.setText(strFecha);
        }
        
        if(strFecha.length()==5 || strFecha.length()==6){
            String dtStrFecha = formatoFecha.format(new Date()).substring(6);
            if(strFecha.length()==5) strFecha = strFecha +"/"+dtStrFecha;
            else strFecha = strFecha+dtStrFecha;
            jTxtFiltro.setText(strFecha);
        }
        
        getPrefijo(jCboTerminal.getSelectedItem().toString());
        System.out.println("Buscar cortes: "+strFecha+" - "+pendiente+" - "+this.prefijo);
        listado = cargaDatos((Vector) sesionCorteTaqFacate.queryTmsCortesTblForPrint(strFecha, pendiente, this.prefijo));
        
        if(listado==null){
            DialogoAceptar.mostrarDialogo("¡No se encontraron cortes!","Presione ENTER para continuar...", Color.RED);
            xListado.setDataVector(null, encabezado);
            AnchoColumnas();
            jTxtFiltro.setText(formatoFecha.format(new Date()));
            jTxtFiltro.selectAll();
            jTxtFiltro.requestFocusInWindow();
            return;
        }
        xListado.setDataVector(listado, encabezado);
        AnchoColumnas();
        jTblCortes.setRowSelectionInterval(0,0);
        jTblCortes.requestFocusInWindow();
    }
    
    private Object[][] cargaDatos(Vector Lineas){
        if(Lineas==null || Lineas.size()==0) return null;
        Object[][] lista = new Object[Lineas.size()][8];
        String fecha, fecha2;
        Vector subLinea;
        int i, j;
        
        for(i=0; i<Lineas.size(); i++){
            subLinea = (Vector) Lineas.get(i);
            lista[i][0] = subLinea.get(0);
            lista[i][1] = subLinea.get(1);
            lista[i][2] = subLinea.get(2);
            if(subLinea.get(3) == null) fecha = "";
            else fecha = fFH.format(subLinea.get(3));
            lista[i][3] = fecha;
            if(subLinea.get(4) == null) fecha2 = "";
            else{
                fecha2 = fFH.format(subLinea.get(4));
                //System.out.println(fecha+" ---- ---- ---- "+fecha2);
                if(fecha.equals(fecha2)) fecha2="";
            }
            lista[i][4] = fecha2;
            lista[i][5] = subLinea.get(5);
            lista[i][6] = subLinea.get(6);
            lista[i][7] = subLinea.get(7);
        }
        return lista;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_F1:
                    xListado.setDataVector(null, encabezado);
                    AnchoColumnas();
                    jTxtFiltro.setText(formatoFecha.format(new Date()));
                    jTxtFiltro.selectAll();
                    jTxtFiltro.requestFocusInWindow();
                break;
            case KeyEvent.VK_F2:
                    if(jCkbFiltro.isSelected()){
                        jCkbFiltro.setSelected(false);
                        this.pendiente="";
                    }
                    else{
                        jCkbFiltro.setSelected(true);
                        this.pendiente="P";
                    }
                break;
            case KeyEvent.VK_F3:
                    jCboTerminal.requestFocusInWindow();
                break;
            case KeyEvent.VK_F4: CerrarIFrame("¿Desea cerrar la aplicación?");
                break;
            case KeyEvent.VK_F5:
                    Reimprimir();
                break;
            case KeyEvent.VK_F6:
                    CorteDia();
                break;
            case KeyEvent.VK_ENTER:
                    if(e.getComponent().getName()==null) return;
                    if(e.getComponent().getName().equals("jTxtFiltro")||
                            e.getComponent().getName().equals("Paloma")||
                            e.getComponent().getName().equals("Terminal")) Filtro();
                break;
            case KeyEvent.VK_RIGHT:
                if(e.getComponent().getName().equals("jTxtFiltro")) jCkbFiltro.requestFocusInWindow();
                else if(e.getComponent().getName().equals("Paloma")) jCboTerminal.requestFocusInWindow();
                else if(e.getComponent().getName().equals("Terminal")){
                    getPrefijo(jCboTerminal.getSelectedItem().toString());
                    jTxtFiltro.selectAll();
                    jTxtFiltro.requestFocusInWindow();
                }
                break;
            case KeyEvent.VK_LEFT:
                if(e.getComponent().getName().equals("jTxtFiltro")) jCboTerminal.requestFocusInWindow();
                else if(e.getComponent().getName().equals("Paloma")){
                    jTxtFiltro.selectAll();
                    jTxtFiltro.requestFocusInWindow();
                }
                else if(e.getComponent().getName().equals("Terminal")){
                    getPrefijo(jCboTerminal.getSelectedItem().toString());
                    jCkbFiltro.requestFocusInWindow();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void setMenuId(long menuId){ this.MENU_ID=menuId; }

    private boolean obtieneCaja() {
        estaTerminal = new PcInfo();
        cajaId = sesionCorteTaqFacate.obtenerCaja(estaTerminal.getHostName().toUpperCase());
        if(cajaId==-1) return false;
        return true;
    }
        
    private boolean ObtenerTerminalPrefijo(){
        Vector vTerminalPrefijo = sesionCorteTaqFacate.obtenerTerminalPrefijo();
        if(vTerminalPrefijo==null || vTerminalPrefijo.size()==0) return false;
               
        int contador=0, i;
        Vector v=null;
        
        for(i=0; i<vTerminalPrefijo.size(); i++){
            v = (Vector) vTerminalPrefijo.get(i);
            if(!v.get(2).toString().equals("CENTRAL")) contador++;
        }
        
        v=null;
        objTerminalPrefijo = new Object[contador][3];
        OrigenesDBLink=new Object[contador];
        contador=0;
        for(i=0; i<vTerminalPrefijo.size(); i++){
            v = (Vector) vTerminalPrefijo.get(i);
            if(!v.get(2).toString().equals("CENTRAL")){
                objTerminalPrefijo[contador][0] = v.get(0).toString();
                objTerminalPrefijo[contador][1] = v.get(1).toString();
                objTerminalPrefijo[contador][2] = v.get(2).toString();
                OrigenesDBLink[contador] = v.get(2).toString();
                contador++;
            }
        }
        
        return true;
    }
   
    public String getPrefijo(String Terminal){
        for(int i=0; i<objTerminalPrefijo.length; i++)
            if(Terminal.equals(objTerminalPrefijo[i][2].toString())){
                this.prefijo=objTerminalPrefijo[i][1].toString();
                return this.prefijo;
            }
        return "";
    }
    
    private class colorCorteEstadoRenderer extends DefaultTableCellRenderer{
        private Color cFONDO_CORTEPENDIENTE=Color.WHITE;
        private Color cFONDO_CORTEPROCESO=new Color(255,0,0);
        private Color cFONDO_CORTEREALIZADO=new Color(0,255,0);
        private Color cLETRA=new Color(0,0,102);
        private Color cFONDO_SELECCION=new Color(49,106,197);
        private Color bg;
        private Color fg;
        private String cEdoCorte;
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
            boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if(listado==null){
                setBackground(cFONDO_CORTEPENDIENTE);
                setForeground(fg);
                return this;
            }
            
            cEdoCorte = listado[row][5].toString();
            
            if(cEdoCorte.equals("P") || cEdoCorte.equals("T")) bg = cFONDO_CORTEPENDIENTE;
            else if(cEdoCorte.equals("E") || cEdoCorte.equals("B")) bg = cFONDO_CORTEPROCESO;
            else if(cEdoCorte.equals("R") || cEdoCorte.equals("D")) bg = cFONDO_CORTEREALIZADO;
            
            setBackground(bg);
            setForeground(fg);
            
            if(row==jTblCortes.getSelectedRow() && column==0){
                setBackground(cFONDO_SELECCION);
                setForeground(Color.WHITE);
            }
            return this;
        }
    }
    
    private Object[] getOrigenesDBLink() {
        return OrigenesDBLink;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCboTerminal;
    private javax.swing.JCheckBox jCkbFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScpCortes;
    private javax.swing.JTable jTblCortes;
    private javax.swing.JTextField jTxtFiltro;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezado = {"Corte", "Usuario", "Nombre Usuario", "Fecha Asignacion", "Fecha Corte"};
    private DefaultTableModel xListado = new DefaultTableModel(null, encabezado){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
    };
    private TmsCorteTerminalFacadeRemote sesionCorteTaqFacate;
    private Object[] OrigenesDBLink=null;
    private JDlgSiNo dialogo;
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat fFH= new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
    private String pendiente="";
    private int fila;
    private Object[][] listado;
    
    private long usuarioId;
    private Date[] fechasIni = new Date[2];
    private JDlgAceptar DialogoAceptar=new JDlgAceptar();
    private Vector datosIniciales;
    private long USUARIO_ID;
    private String USUARIO_NUMERO;
    private String USUARIO_NOMBRE;
    private long SESION_ID;
    private long MENU_ID;
    private Color colorDialogoActivo = new Color(0,83,255);
    private boolean inicioGral;
    private String strEmpresaPrincipal;
    private long cajaId;
    private PcInfo estaTerminal;
    private JClsImpresionReporte jClsImpresionReporte;
    private Context c;
    private JClsAbreSocketAS tx = null;
    
    private Object[][] objTerminalPrefijo;
    private String prefijo;
    private Color xorColorFondo = new Color(156, 179, 201);
}