/*
 * JDlgDetalleCorte.java
 *
 * Created on 15 de agosto de 2007, 06:47 PM
 */

package tms_cortetaquilla;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import tms_TextFields.JCuantityTextField;
import tms_TextFields.JNumberTextField;
import tms_cortetaquilla.util.JClsAbreSocketAS;
import tms_cortetaquilla.util.JClsImpresionReporte;
import tms_cortetaquilla.util.JDlgAceptar;
import tms_cortetaquilla.util.JDlgSiNo;
import tms_cortetaquilla.util.Jdlg_Corte;
import xertms.entidad.TmsDesgloceEfectivoTbl;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsRecoleccionesTbl;
import xertms.entidad.TmsSesionActividadesTbl;
import xertms.solicitud.TmsCorteTaqFacadeRemote;

/**
 *
 * @author  ocruz
 */
public class JDlgDetalleCorte extends javax.swing.JDialog{
    
    public JDlgDetalleCorte(java.awt.Frame parent, boolean modal, String titulo,
                            long aCorteId,
                            long aUsuarioId,
                            Object[][] aCtdEntregadas,
                            Object[][] aFrmPago,
                            TmsCorteTaqFacadeRemote aSesionCorteTaqFacate, String pStrFecha,
                            String pPrefijoTerminal,
                            List<TmsEmpresasTbl> pTmsEmpresasTbl, String pEmpresaPrincipal, String pEsquema,
                            JClsImpresionReporte pJClsImpresionReporte,
                            JClsAbreSocketAS pX,
                            String pedoAnteriorCorte){
        super(parent, modal);
        this.x = pX;
        this.setTitle(titulo);
        this.empresaPrincipal = pEmpresaPrincipal;
        System.out.println("empresaPrincipal:  "+empresaPrincipal);
        this.esquema = pEsquema;
        this.tmsEmpresasTbl = pTmsEmpresasTbl;
        this.corteId=aCorteId;
        this.usuarioId=aUsuarioId;
        this.ctdEntregadas = aCtdEntregadas;
        this.frmPago = aFrmPago;
        this.sesionCorteTaqFacate = aSesionCorteTaqFacate;
        this.strFecha=pStrFecha;
        this.PrefijoTerminal = pPrefijoTerminal;
        this.jClsImpresionReporte = pJClsImpresionReporte;
        this.edoAnteriorCorte = pedoAnteriorCorte; 
        initComponents();   
        inhabilitarF10();
        jTblCtdEntregada.setSurrendersFocusOnKeystroke(true);
        jTblFrmPago.setSurrendersFocusOnKeystroke(true);
        jTblFolios.setSurrendersFocusOnKeystroke(true);
        centrar();
        for(int i=0; i<frmPago.length; i++){
            if(frmPago[i][0].toString().equals("EFECTIVO")){
                this.FilaEfectivo = i;
                break;
            }
        }
        cargaEmpresasFolios();
        xListado.setDataVector(ctdEntregadas, xEncabezado);
        yListado.setDataVector(frmPago, yEncabezado);
        zListado.setDataVector(folios, zEncabezado);
        jTblCtdEntregada.getInputMap(jTblCtdEntregada.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                jTblCtdEntregada.getInputMap(jTblCtdEntregada.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).get(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)));
        
        jTblFrmPago.getInputMap(jTblFrmPago.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                jTblFrmPago.getInputMap(jTblFrmPago.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).get(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)));
    }
    
    private void inhabilitarF10() {
        this.jTblCtdEntregada.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblFrmPago.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
        this.jTblFolios.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_FOCUSED);
    }
    
    private void CamposEspeciales(){
        TableColumn Column;
        
        Column = jTblCtdEntregada.getColumnModel().getColumn(1);
        JNumberTextField cantidad = new JNumberTextField();
        Column.setCellEditor(new DefaultCellEditor(cantidad));
        
        Column = jTblCtdEntregada.getColumnModel().getColumn(2);
        JCuantityTextField monto = new JCuantityTextField();
        Column.setCellEditor(new DefaultCellEditor(monto));
        
        Column = jTblFrmPago.getColumnModel().getColumn(1);
        JNumberTextField cantidad2 = new JNumberTextField();
        Column.setCellEditor(new DefaultCellEditor(cantidad2));
        
        Column = jTblFrmPago.getColumnModel().getColumn(2);
        JCuantityTextField monto2 = new JCuantityTextField();
        Column.setCellEditor(new DefaultCellEditor(monto2));
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Column = jTblFolios.getColumnModel().getColumn(1);
        Column.setCellRenderer(renderer);
        Column = jTblFolios.getColumnModel().getColumn(2);
        Column.setCellRenderer(renderer);
        
        jTblFrmPago.setOpaque(true);
        jTblFrmPago.setDefaultRenderer(Object.class, new AttributiveCellRenderer1());
        
        jTblCtdEntregada.setOpaque(true);
        jTblCtdEntregada.setDefaultRenderer(Object.class, new AttributiveCellRendererX());
    }
    
    private void centrar(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
    }
    
    private void cargaEmpresasFolios(){
        Vector Linea = (Vector) sesionCorteTaqFacate.queryFolioFinal(corteId);
        if(Linea.size()==0) return;
        Vector Lineas;
        Vector valor;
        String empresaNombre;
        long empresaId;
        folios = new Object[Linea.size()][7];
        int i;
        for(i=0; i<Linea.size(); i++){
            Lineas = (Vector) Linea.get(i);
            empresaId = Long.valueOf(Lineas.get(0).toString());
            empresaNombre = Lineas.get(1).toString();
            folios[i][0] = Lineas.get(1);
            folios[i][2] = ""; //Lineas.get(3);
            valor = (Vector) sesionCorteTaqFacate.queryUltimoFolioVendido(corteId, empresaNombre);
            if(valor==null || valor.get(0)==null) valor = (Vector) sesionCorteTaqFacate.queryFolioInicial(corteId, empresaId);
            folios[i][1] = ""; //valor.get(0);
            folios[i][3] = empresaId;
            folios[i][4] = corteId;
            folios[i][5] = Lineas.get(4).toString()+"_UV_";
            folios[i][6] = Lineas.get(4).toString()+"_FD_";
        }
    }
    
    private void xAnchoColumnas(){
        TableColumn column = null;
        int anchoContenedor = jScpCtdEntregada.getWidth();
        for (int i = 0; i < 3; i++) {
            column = jTblCtdEntregada.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.60)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
            }
            column.setResizable(false);
        }
    }
    
    private void yAnchoColumnas(){
        TableColumn column = null;
        int anchoContenedor = jScpFrmPago.getWidth();
        for (int i = 0; i < 3; i++) {
            column = jTblFrmPago.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.60)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
            }
            column.setResizable(false);
        }
    }
    
    private void zAnchoColumnas(){
        TableColumn column = null;
        int anchoContenedor = jScpFolios.getWidth();
        for (int i = 0; i < 3; i++) {
            column = jTblFolios.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.60)); break;
                case 1: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
                case 2: column.setPreferredWidth(Math.round(anchoContenedor*(float)0.20)); break;
            }
            column.setResizable(false);
            if(i!=0) column.setCellEditor(new DefaultCellEditor(new JNumberTextField()));
        }
        /*TableColumn Column1;
        Column1 = jTblFolios.getColumnModel().getColumn(1);
        JNumberTextField fini = new JNumberTextField();
        Column1.setCellEditor(new DefaultCellEditor(fini));
        TableColumn Column2;
        Column2 = jTblFolios.getColumnModel().getColumn(2);
        JNumberTextField ffin = new JNumberTextField();
        Column2.setCellEditor(new DefaultCellEditor(ffin));*/
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPnlCtdEntregada = new javax.swing.JPanel();
        jScpCtdEntregada = new javax.swing.JScrollPane();
        jTblCtdEntregada = new javax.swing.JTable();
        jTblCtdEntregada = new javax.swing.JTable()
        {
            public Component prepareEditor(
                TableCellEditor editor, int row, int column)
            {

                Component c = super.prepareEditor(editor, row, column);
                if (c instanceof JTextComponent)
                {
                    ((JTextField)c).selectAll();
                }
                return c;
            }
        };
        jPnlFrmPago = new javax.swing.JPanel();
        jScpFrmPago = new javax.swing.JScrollPane();
        jTblFrmPago = new javax.swing.JTable();
        jTblFrmPago = new javax.swing.JTable()
        {
            public Component prepareEditor(
                TableCellEditor editor, int row, int column)
            {

                Component c = super.prepareEditor(editor, row, column);
                if (c instanceof JTextComponent)
                {
                    ((JTextField)c).selectAll();
                }
                return c;
            }
        };
        jPnlFolios = new javax.swing.JPanel();
        jScpFolios = new javax.swing.JScrollPane();
        jTblFolios = new javax.swing.JTable();
        jTblFolios = new javax.swing.JTable()
        {
            public Component prepareEditor(
                TableCellEditor editor, int row, int column)
            {

                Component c = super.prepareEditor(editor, row, column);
                if (c instanceof JTextComponent)
                {
                    ((JTextField)c).selectAll();
                }
                return c;
            }
        };
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPnlCtdEntregada.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cantidades Entregadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray));
        jTblCtdEntregada.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTblCtdEntregada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblCtdEntregada.setModel(xListado);
        jTblCtdEntregada.getTableHeader().setReorderingAllowed(false);
        jTblCtdEntregada.setFocusTraversalKeysEnabled(true);
        jTblCtdEntregada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblCtdEntregadaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTblCtdEntregadaKeyReleased(evt);
            }
        });

        jScpCtdEntregada.setViewportView(jTblCtdEntregada);

        org.jdesktop.layout.GroupLayout jPnlCtdEntregadaLayout = new org.jdesktop.layout.GroupLayout(jPnlCtdEntregada);
        jPnlCtdEntregada.setLayout(jPnlCtdEntregadaLayout);
        jPnlCtdEntregadaLayout.setHorizontalGroup(
            jPnlCtdEntregadaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpCtdEntregada, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );
        jPnlCtdEntregadaLayout.setVerticalGroup(
            jPnlCtdEntregadaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpCtdEntregada, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        jPnlFrmPago.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formas de Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray));
        jTblFrmPago.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTblFrmPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblFrmPago.setModel(yListado);
        jTblFrmPago.getTableHeader().setReorderingAllowed(false);
        jTblFrmPago.setFocusTraversalKeysEnabled(true);
        jTblFrmPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblFrmPagoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTblFrmPagoKeyReleased(evt);
            }
        });

        jScpFrmPago.setViewportView(jTblFrmPago);

        org.jdesktop.layout.GroupLayout jPnlFrmPagoLayout = new org.jdesktop.layout.GroupLayout(jPnlFrmPago);
        jPnlFrmPago.setLayout(jPnlFrmPagoLayout);
        jPnlFrmPagoLayout.setHorizontalGroup(
            jPnlFrmPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpFrmPago, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
        );
        jPnlFrmPagoLayout.setVerticalGroup(
            jPnlFrmPagoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpFrmPago, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        jPnlFolios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Folios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), java.awt.Color.darkGray));
        jPnlFolios.setFocusable(false);
        jScpFolios.setFocusable(false);
        jTblFolios.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTblFolios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblFolios.setModel(zListado);
        jTblFolios.getTableHeader().setReorderingAllowed(false);
        jTblFolios.setFocusTraversalKeysEnabled(true);
        jTblFolios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTblFoliosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTblFoliosKeyReleased(evt);
            }
        });

        jScpFolios.setViewportView(jTblFolios);

        org.jdesktop.layout.GroupLayout jPnlFoliosLayout = new org.jdesktop.layout.GroupLayout(jPnlFolios);
        jPnlFolios.setLayout(jPnlFoliosLayout);
        jPnlFoliosLayout.setHorizontalGroup(
            jPnlFoliosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpFolios, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );
        jPnlFoliosLayout.setVerticalGroup(
            jPnlFoliosLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScpFolios, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("<html><font color=ff0000>F1</font> Cantidades Entregadas | <font color=ff0000>F2</font> Formas de Pago | <font color=ff0000>F5</font> Folios | <font color=ff0000>F6</font> Desglosar efectivo | <font color=ff0000>F7</font> Venta manual |<br><font color=ff0000>F10</font> Realizar Corte | <font color=ff0000>ESC</font> Cancelar </html>");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPnlFolios, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jPnlCtdEntregada, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPnlFrmPago, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPnlFrmPago, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPnlCtdEntregada, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPnlFolios, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTblFoliosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblFoliosKeyReleased
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                case KeyEvent.VK_F1: 
                    /*jTblFrmPago.setRowSelectionInterval(0,0);
                    jTblFrmPago.setColumnSelectionInterval(1,1);*/
                    jTblCtdEntregada.setRowSelectionInterval(1,1);
                    jTblCtdEntregada.setColumnSelectionInterval(1,1);
                    jTblCtdEntregada.requestFocusInWindow();
                    //if(!noNegativos()) return;
                    break;
                case KeyEvent.VK_F2: 
                    /*jTblCtdEntregada.setRowSelectionInterval(1,1);
                    jTblCtdEntregada.setColumnSelectionInterval(1,1);*/
                    jTblFrmPago.setRowSelectionInterval(0,0);
                    jTblFrmPago.setColumnSelectionInterval(1,1);
                    jTblFrmPago.requestFocusInWindow();
                    //if(!noNegativos()) return;
                    break;
                case KeyEvent.VK_F6:
                    //if(!noNegativos()) return;
                    DesglosarEfectivo();
                    break;
                case KeyEvent.VK_ESCAPE: 
                    CerrarVentana();
                    break;
                case KeyEvent.VK_F7: 
                    //if(!noNegativos()) return;
                    ventaManual();
                    break;
                case KeyEvent.VK_F10: 
                    if(!noNegativos()) return;
                    realizarCorte();
                    break;
            }
    }//GEN-LAST:event_jTblFoliosKeyReleased

    private void jTblCtdEntregadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblCtdEntregadaKeyReleased
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                case KeyEvent.VK_F2: 
                    /*jTblCtdEntregada.setRowSelectionInterval(1,1);
                    jTblCtdEntregada.setColumnSelectionInterval(1,1);*/
                    jTblFrmPago.setRowSelectionInterval(0,0);
                    jTblFrmPago.setColumnSelectionInterval(1,1);
                    jTblFrmPago.requestFocusInWindow();
                    //if(!noNegativos()) return;
                    break;
                case KeyEvent.VK_F5:
                    //if(!noNegativos()) return;
                    jTblFolios.setRowSelectionInterval(0,0);
                    jTblFolios.setColumnSelectionInterval(1,1);
                    jTblFolios.requestFocusInWindow();
                    break;
                case KeyEvent.VK_F6:
                    //if(!noNegativos()) return;
                    DesglosarEfectivo();
                    break;
                case KeyEvent.VK_ESCAPE: 
                    CerrarVentana();
                    break;
                case KeyEvent.VK_F7: 
                    //if(!noNegativos()) return;
                    ventaManual();
                    break;
                case KeyEvent.VK_F10: 
                    if(!noNegativos()) return;
                    realizarCorte();
                    break;
            }
    }//GEN-LAST:event_jTblCtdEntregadaKeyReleased

    private void jTblFrmPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblFrmPagoKeyReleased
// TODO add your handling code here:
        switch(evt.getKeyCode()){
                case KeyEvent.VK_F1: 
                    jTblCtdEntregada.setRowSelectionInterval(1,1);
                    jTblCtdEntregada.setColumnSelectionInterval(1,1);
                    jTblCtdEntregada.requestFocusInWindow();
                    //if(!noNegativos()) return;
                    break;
                case KeyEvent.VK_F5:
                    //if(!noNegativos()) return;
                    jTblFolios.setRowSelectionInterval(0,0);
                    jTblFolios.setColumnSelectionInterval(1,1);
                    jTblFolios.requestFocusInWindow();
                    break;
                case KeyEvent.VK_F6:
                    //if(!noNegativos()) return;
                    DesglosarEfectivo();
                    break;
                case KeyEvent.VK_ESCAPE: 
                    CerrarVentana();
                    break;
                case KeyEvent.VK_F7: 
                    //if(!noNegativos()) return;
                    ventaManual();
                    break;
                case KeyEvent.VK_F10: 
                    if(!noNegativos()) return;
                    realizarCorte();
                    break;
            }
    }//GEN-LAST:event_jTblFrmPagoKeyReleased

    private void jTblFrmPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblFrmPagoKeyPressed
// TODO add your handling code here:
            switch(evt.getKeyCode()){
                case KeyEvent.VK_F1: 
                    jTblFrmPago.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F5:
                    jTblFrmPago.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F6:
                    jTblFrmPago.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_ESCAPE: 
                    jTblFrmPago.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F7: 
                    jTblFrmPago.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F10: 
                    jTblFrmPago.setColumnSelectionInterval(0,0);
                    break;
            }
    }//GEN-LAST:event_jTblFrmPagoKeyPressed

    private void jTblCtdEntregadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblCtdEntregadaKeyPressed
// TODO add your handling code here:
            switch(evt.getKeyCode()){
                case KeyEvent.VK_F2: 
                    jTblCtdEntregada.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F5:
                    jTblCtdEntregada.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F6:
                    jTblCtdEntregada.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_ESCAPE: 
                    jTblCtdEntregada.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F7: 
                    jTblCtdEntregada.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F10: 
                    jTblCtdEntregada.setColumnSelectionInterval(0,0);
                    break;
            }
    }//GEN-LAST:event_jTblCtdEntregadaKeyPressed

    private void jTblFoliosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTblFoliosKeyPressed
// TODO add your handling code here:
            switch(evt.getKeyCode()){
                case KeyEvent.VK_F1: 
                    jTblFolios.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F2: 
                    jTblFolios.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F6:
                    jTblFolios.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_ESCAPE: 
                    jTblFolios.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F7: 
                    jTblFolios.setColumnSelectionInterval(0,0);
                    break;
                case KeyEvent.VK_F10: 
                    jTblFolios.setColumnSelectionInterval(0,0);
                    break;
            }
    }//GEN-LAST:event_jTblFoliosKeyPressed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
        xAnchoColumnas();
        yAnchoColumnas();
        zAnchoColumnas();
        CamposEspeciales();
        jTblFrmPago.setRowSelectionAllowed(false);
        jTblCtdEntregada.setRowSelectionAllowed(false);
        jTblCtdEntregada.setRowSelectionInterval(1,1);
        jTblCtdEntregada.setColumnSelectionInterval(1,1);
        jTblCtdEntregada.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    private void realizarCorte(){
        dialogo = new JDlgSiNo("¡Confirme!", "¿Esta seguro de realizar el corte?");
        if(!dialogo.getResultado()){
            this.transaccion = false;
            jTblCtdEntregada.setRowSelectionInterval(1,1);
            jTblCtdEntregada.setColumnSelectionInterval(1,1);
            jTblCtdEntregada.requestFocusInWindow();
            return;
        }
        // registro
        if(!x.abreSocketAS()){
            DialogoAceptar.mostrarDialogo("¡No existe conexion con la base de datos!","<html>No se realizo el corte.<br>Contacte al administrador del sistema.</html>", Color.RED);
            return;
        }
        //// VAGL 12/04/2012
        //Timestamp fechaAct = new Timestamp(new Date().getTime());
       Timestamp fechaAct = Timestamp.valueOf(sesionCorteTaqFacate._ObtieneFechaHoraBD());
        
//        System.out.println("CORTE ID ("+edoAnteriorCorte+")"+this.corteId);
//        if(!sesionCorteTaqFacate.updateCorteRealizado(this.corteId, this.usuarioId, fechaAct, esquema,edoAnteriorCorte)){
//            transaccion = false;
//            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
//                    "Contacte al administrador del sistema.</html>", Color.RED);
//            this.dispose();
//            return;
//        }
//        System.out.println("CORTE R");
        
        Object SesionActCorteId = ((Vector) sesionCorteTaqFacate.queryActividadSesionCorteGetId()).get(0);
        if(SesionActCorteId == null){
            transaccion = false;
            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            System.out.println("No encontro el ID del Estado de la sesion de CORTE ");
            this.dispose();
            return;
        }
        long sesionDeCorte = Long.valueOf(SesionActCorteId.toString());
        
        System.out.println("SESION DE CORTE "+sesionDeCorte);
        TmsSesionActividadesTbl tmsSesionActividadesTbl = new TmsSesionActividadesTbl();
        tmsSesionActividadesTbl.setCorteId(corteId);
        tmsSesionActividadesTbl.setActividadSesionId(sesionDeCorte);
        System.out.println("empresaPrincipal:  "+empresaPrincipal);
        long empId = getEmpresaId(empresaPrincipal);
        System.out.println("setEmpresaId():  "+empId);
        tmsSesionActividadesTbl.setEmpresaId(empId);
        int sigCorteSeq = sesionCorteTaqFacate.sigCorteSeq();
        if(sigCorteSeq==-1){
            transaccion = false;
            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            System.out.println("el siguiente numero de la secuencia fue -1 ");
            this.dispose();
            return;
        }
        tmsSesionActividadesTbl.setValorActividad(String.valueOf(sigCorteSeq));
        tmsSesionActividadesTbl.setFechaHoraActividad(fechaAct);
        tmsSesionActividadesTbl.setAutorizadoPor(usuarioId);
        tmsSesionActividadesTbl.setCreadoPor(usuarioId);
        tmsSesionActividadesTbl.setFechaCreacion(fechaAct);
        tmsSesionActividadesTbl.setUltimaActualizacionPor(usuarioId);
        tmsSesionActividadesTbl.setUltimaFechaActualizacion(fechaAct);
        Object retorno=sesionCorteTaqFacate.addSesionCorte(PrefijoTerminal, tmsSesionActividadesTbl);
        if(retorno==null){
            transaccion = false;
            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            System.out.println("no se pudo insertar la actividad sesion... ");
            this.dispose();
            return;
        }
        int i;
        /*Object SesionActUltFolioVtaId = ((Vector) sesionCorteTaqFacate.queryActividadSesionUltFolioGetId()).get(0);
        if(SesionActUltFolioVtaId == null){
            transaccion = false;
            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            this.dispose();
            return;
        }
        long ActSesionUltBolVdo = Long.valueOf(SesionActUltFolioVtaId.toString());
        System.out.println("SESION DE ULT FOLIO VENDIDO "+ActSesionUltBolVdo);
        Vector valor;
        Object objUltFolio;
        for(i=0; i<jTblFolios.getRowCount(); i++){
            tmsSesionActividadesTbl = new TmsSesionActividadesTbl();
            tmsSesionActividadesTbl.setCorteId(corteId);
            tmsSesionActividadesTbl.setActividadSesionId(ActSesionUltBolVdo);
            tmsSesionActividadesTbl.setEmpresaId(getEmpresaId(jTblFolios.getValueAt(i,0).toString()));
            valor = (Vector) sesionCorteTaqFacate.queryUltimoFolioVendido(corteId, zListado.getValueAt(i,0).toString());
            if(valor.get(0)==null) valor = (Vector) sesionCorteTaqFacate.queryFolioInicial(corteId, Long.valueOf(folios[i][3].toString()));
            System.out.println("ULT FOLIO VENDIDO (VALOR) ::: "+valor);
            tmsSesionActividadesTbl.setValorActividad(valor.get(0).toString());
            tmsSesionActividadesTbl.setFechaHoraActividad(fechaAct);
            tmsSesionActividadesTbl.setAutorizadoPor(usuarioId);
            tmsSesionActividadesTbl.setCreadoPor(usuarioId);
            tmsSesionActividadesTbl.setFechaCreacion(fechaAct);
            tmsSesionActividadesTbl.setUltimaActualizacionPor(usuarioId);
            tmsSesionActividadesTbl.setUltimaFechaActualizacion(fechaAct);
            objUltFolio=sesionCorteTaqFacate.addSesionCorte(PrefijoTerminal, tmsSesionActividadesTbl);
            
            if(objUltFolio==null){
                transaccion = false;
                DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
                        "Contacte al administrador del sistema.</html>", Color.RED);
                this.dispose();
                return;
            }
        }
        System.out.println("CORTE REALIZADO EN SESION");*/
        
        System.out.println("SESION DE RECOLECCIONES "+sesionDeCorte);
        TmsRecoleccionesTbl tmsRecoleccionesTbl;
        for(i=0; i<jTblCtdEntregada.getRowCount(); i++){
            tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
            tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
            tmsRecoleccionesTbl.setTipoPagoId(null);
            if(ctdEntregadas[i][4].equals("RECOLECCIONES") || ctdEntregadas[i][4].equals("CANCELADOS") || ctdEntregadas[i][4].equals("CAMBIOS DE HORARIO")) tmsRecoleccionesTbl.setTipoPasajeroId(null);
            else tmsRecoleccionesTbl.setTipoPasajeroId(Long.valueOf(ctdEntregadas[i][3].toString()));
            tmsRecoleccionesTbl.setReferencia(ctdEntregadas[i][4].toString());
            tmsRecoleccionesTbl.setCantidad(Long.valueOf(xListado.getValueAt(i,1).toString()));
            tmsRecoleccionesTbl.setMonto(Double.valueOf(xListado.getValueAt(i,2).toString()));
            tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
            tmsRecoleccionesTbl.setCreadoPor(usuarioId);
            tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
            tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
            tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
            sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
        }
        
        System.out.println("TABLA RECOLECCIONES 1");
        float efe=0;
        Object RecoleccionEFE=null;
        for(i=0; i<jTblFrmPago.getRowCount(); i++){
            tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
            tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
            tmsRecoleccionesTbl.setTipoPagoId(Long.valueOf(frmPago[i][3].toString()));
            tmsRecoleccionesTbl.setTipoPasajeroId(null);
            tmsRecoleccionesTbl.setReferencia(frmPago[i][4].toString());
            tmsRecoleccionesTbl.setCantidad(Long.valueOf(yListado.getValueAt(i,1).toString()));
            tmsRecoleccionesTbl.setMonto(Double.valueOf(yListado.getValueAt(i,2).toString()));
            tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
            tmsRecoleccionesTbl.setCreadoPor(usuarioId);
            tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
            tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
            tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
            if(tmsRecoleccionesTbl.getReferencia().equals("EFE")) 
            {
                    RecoleccionEFE=sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
                    efe = Float.valueOf(yListado.getValueAt(i,2).toString());
            }
            else sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
        }

      System.out.println("RECOLECCIONES ADICIONALES "+Long.valueOf(retorno.toString()));
        //TAQUILLA
        float vtaNeta = Float.valueOf(sesionCorteTaqFacate.getVentaNetaCorte(corteId));
                tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
                tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
                tmsRecoleccionesTbl.setTipoPagoId(null);
                tmsRecoleccionesTbl.setTipoPasajeroId(null);
                tmsRecoleccionesTbl.setReferencia("TAQUILLA");
                tmsRecoleccionesTbl.setCantidad(Long.valueOf("1"));
                tmsRecoleccionesTbl.setMonto(Double.valueOf(vtaNeta));
                tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
                tmsRecoleccionesTbl.setCreadoPor(usuarioId);
                tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
                tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
                tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
                sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
          /*
                //TIEMPO AIRE
                tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
                tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
                tmsRecoleccionesTbl.setTipoPagoId(null);
                tmsRecoleccionesTbl.setTipoPasajeroId(null);
                tmsRecoleccionesTbl.setReferencia("TIEMPO AIRE");
                tmsRecoleccionesTbl.setCantidad(Long.valueOf("1"));
                tmsRecoleccionesTbl.setMonto(Double.valueOf(sesionCorteTaqFacate.getTAECorte(corteId)));
                tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
                tmsRecoleccionesTbl.setCreadoPor(usuarioId);
                tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
                tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
                tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
                sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
               */
          //FALTANTE
                System.out.println("Calculando el faltante: ");
                System.out.println("    vtaNeta: "+vtaNeta);
                System.out.println("    efe: "+efe);
                float diferencia = 0;
                if((vtaNeta-efe)>0)
                    diferencia = vtaNeta-efe;
                System.out.println("    diferencia: "+diferencia);
                tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
                tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
                tmsRecoleccionesTbl.setTipoPagoId(null);
                tmsRecoleccionesTbl.setTipoPasajeroId(null);
                tmsRecoleccionesTbl.setReferencia("FALTANTE");
                tmsRecoleccionesTbl.setCantidad(Long.valueOf("1"));
                tmsRecoleccionesTbl.setMonto(Double.valueOf(diferencia));
                tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
                tmsRecoleccionesTbl.setCreadoPor(usuarioId);
                tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
                tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
                tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
                sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
          //SOBRANTE
                System.out.println("Calculando el faltante: ");
                System.out.println("    vtaNeta: "+vtaNeta);
                System.out.println("    efe: "+efe);
                 diferencia = 0;
                if((efe-vtaNeta)>0)
                    diferencia = efe-vtaNeta;
                System.out.println("    diferencia: "+diferencia);
                tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
                tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
                tmsRecoleccionesTbl.setTipoPagoId(null);
                tmsRecoleccionesTbl.setTipoPasajeroId(null);
                tmsRecoleccionesTbl.setReferencia("SOBRANTE");
                tmsRecoleccionesTbl.setCantidad(Long.valueOf("1"));
                tmsRecoleccionesTbl.setMonto(Double.valueOf(diferencia));
                tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
                tmsRecoleccionesTbl.setCreadoPor(usuarioId);
                tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
                tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
                tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
                sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);

        System.out.println("RECOLECIONES 2");
        if(vtaMnl!=null){
            for(i=0; i<vtaMnl.length; i++){
                tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
                tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
                tmsRecoleccionesTbl.setTipoPagoId(null);
                tmsRecoleccionesTbl.setTipoPasajeroId(null);
                tmsRecoleccionesTbl.setReferencia(vtaMnl[i][0].toString());
                tmsRecoleccionesTbl.setCantidad(Long.valueOf(vtaMnl[i][1].toString()));
                tmsRecoleccionesTbl.setMonto(Double.valueOf(vtaMnl[i][2].toString()));
                tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
                tmsRecoleccionesTbl.setCreadoPor(usuarioId);
                tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
                tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
                tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
                sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
            }
        }


        System.out.println("RECOLECCIONES 3 "+Long.valueOf(retorno.toString()));
        for(i=0; i<jTblFolios.getRowCount(); i++){
            tmsRecoleccionesTbl = new TmsRecoleccionesTbl();
            tmsRecoleccionesTbl.setSesionActividadId(Long.valueOf(retorno.toString()));
            tmsRecoleccionesTbl.setTipoPagoId(null);
            tmsRecoleccionesTbl.setTipoPasajeroId(null);
            tmsRecoleccionesTbl.setReferencia(folios[i][5].toString()+jTblFolios.getValueAt(i,1).toString());
            tmsRecoleccionesTbl.setCantidad((long)0);
            tmsRecoleccionesTbl.setMonto((double)0);
            tmsRecoleccionesTbl.setAutorizadoPor(usuarioId);
            tmsRecoleccionesTbl.setCreadoPor(usuarioId);
            tmsRecoleccionesTbl.setUltimaActualizacionPor(usuarioId);
            tmsRecoleccionesTbl.setFechaCreacion(fechaAct);
            tmsRecoleccionesTbl.setUltimaFechaActualizacion(fechaAct);
            sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
            tmsRecoleccionesTbl.setReferencia(folios[i][6].toString()+jTblFolios.getValueAt(i,2).toString());
            sesionCorteTaqFacate.addRecoleccion(PrefijoTerminal, tmsRecoleccionesTbl);
        }
        
        System.out.println("RECOLECCIONES 4");
        
        if(Desgloce!=null){
            long recoleccionId = Long.valueOf(RecoleccionEFE.toString());
            System.out.println("desgloce "+recoleccionId);
            TmsDesgloceEfectivoTbl tmsDesgloceEfectivoTbl;
            for(i=0; i<Desgloce.length; i++){
                tmsDesgloceEfectivoTbl = new TmsDesgloceEfectivoTbl();
                tmsDesgloceEfectivoTbl.setRecoleccionId(recoleccionId);
                tmsDesgloceEfectivoTbl.setCantidad(Long.valueOf(Desgloce[i][0].toString()));
                tmsDesgloceEfectivoTbl.setDenominacion(Double.valueOf(Desgloce[i][1].toString()));
                tmsDesgloceEfectivoTbl.setMonto(Double.valueOf(Desgloce[i][2].toString()));
                tmsDesgloceEfectivoTbl.setReferencia(null);
                tmsDesgloceEfectivoTbl.setCreadoPor(usuarioId);
                tmsDesgloceEfectivoTbl.setFechaCreacion(fechaAct);
                tmsDesgloceEfectivoTbl.setUltimaActualizacionPor(usuarioId);
                tmsDesgloceEfectivoTbl.setUltimaFechaActualizacion(fechaAct);
                sesionCorteTaqFacate.addDesgloce(PrefijoTerminal, tmsDesgloceEfectivoTbl);
            }
        }
        System.out.println("DESGLOSE");
        
        /*int proc=0; boolean finFol=false;
        Vector xNombresx;
        Vector vectorazo = new Vector();
        for(i=0; i<folios.length; i++){
            proc=sesionCorteTaqFacate.finalizaLoteFolios(zListado.getValueAt(i,0).toString(), zListado.getValueAt(i,1).toString(), this.usuarioId, esquema);
            if(proc==1){
                xNombresx = new Vector();
                xNombresx.add(zListado.getValueAt(i,0).toString());
                xNombresx.add(zListado.getValueAt(i,1).toString());
                vectorazo.add(xNombresx);
                finFol=true;
                System.out.println("FINALIZO FOLIOS para "+zListado.getValueAt(i,0).toString());
            }
            else{
                if(proc==-1){
                    transaccion = false;
                    DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
                            "Contacte al administrador del sistema.</html>", Color.RED);
                    this.dispose();
                    return;
                }
            }
        }*/
        
        System.out.println("CORTE ID ("+edoAnteriorCorte+")"+this.corteId);
        if(!sesionCorteTaqFacate.updateCorteRealizado(this.corteId, this.usuarioId, fechaAct, esquema,edoAnteriorCorte)){
            transaccion = false;
            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            System.out.println("No se pudo actualizar el corte... ");
            this.dispose();
            return;
        }
        System.out.println("CORTE R");
        
        this.transaccion = true;
        Jdlg_Corte dlg = new Jdlg_Corte(null, true, "¡Imprimir Corte!");
        dlg.setVisible(true);
        if(!dlg.getDetalle().equals("")){
            //String ArgumentoControl="cmdkey="+paramReportes[1]+"&TMSRPCRT.rdf"+"&"+corteId+"&'"+strFecha+"'&'"+strFecha+"'&'"+dlg.getDetalle()+"'";
            /*String ArgumentoControl="cmdkey="+paramReportes[1]+"&TMSRPCRT.rdf"+"&P_CORTE_ID="+corteId+"&P_DESGLOSADO='"+dlg.getDetalle()+"'";
            BrowserControl bc = new BrowserControl();
            bc.displayURL(paramReportes[2]+ArgumentoControl);
            DialogoAceptar.mostrarDialogo("¡Importante!","Reporte enviado al explorador de Internet predeterminado.", Color.RED);*/
            if(!jClsImpresionReporte.imprimirCorte(String.valueOf(corteId), null, null, "T", dlg.getDetalle(),"CORTE_PRINCIPAL.jasper",String.valueOf(corteId),"","" )){
                DialogoAceptar.mostrarDialogo("¡No fue posible mostrar el reporte!","Contacte al administrador del sistema.", Color.RED);
            }
        }
        this.dispose();
        return;
    }
    
    private void ventaManual(){
        JDlgVtaManual dlgVtaManual = new JDlgVtaManual(this);
        dlgVtaManual.setVisible(true);
        vtaMnl=dlgVtaManual.getVtaManual();
    }
    private void DesglosarEfectivo(){
        JDlgDesglose dlgDesglose = new JDlgDesglose(null, true);
        dlgDesglose.setVisible(true);
        Desgloce=dlgDesglose.getDesglose();
        if(dlgDesglose.getTotalMonto()==0){
            jTblFrmPago.setValueAt("0",FilaEfectivo,1);
            jTblFrmPago.setValueAt("0",FilaEfectivo,2);
        }
        else{
            jTblFrmPago.setValueAt("1",FilaEfectivo,1);
            jTblFrmPago.setValueAt(String.valueOf(dlgDesglose.getTotalMonto()),FilaEfectivo,2);
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
// TODO add your handling code here:
        CerrarVentana();
    }//GEN-LAST:event_formWindowClosing
    
    private void CerrarVentana(){
        transaccion = false;
        this.dispose();
    }
    
    public void keyPressed(KeyEvent e) {
        //if(e.isAltDown()){
            switch(e.getKeyCode()){
                case KeyEvent.VK_F1: 
                    //if(!noNegativos()) return;
                    jTblFrmPago.setRowSelectionInterval(0,0);
                    jTblFrmPago.setColumnSelectionInterval(1,1);
                    jTblCtdEntregada.setRowSelectionInterval(1,1);
                    jTblCtdEntregada.setColumnSelectionInterval(1,1);
                    jTblCtdEntregada.requestFocusInWindow();
                    break;
                case KeyEvent.VK_F2: 
                    //if(!noNegativos()) return;
                    jTblCtdEntregada.setRowSelectionInterval(1,1);
                    jTblCtdEntregada.setColumnSelectionInterval(1,1);
                    jTblFrmPago.setRowSelectionInterval(0,0);
                    jTblFrmPago.setColumnSelectionInterval(1,1);
                    jTblFrmPago.requestFocusInWindow();
                    break;
                case KeyEvent.VK_F5:
                    //if(!noNegativos()) return;
                    jTblFolios.setRowSelectionInterval(1,1);
                    jTblFolios.setColumnSelectionInterval(0,0);
                    jTblFolios.requestFocusInWindow();
                    break;
                case KeyEvent.VK_F6:
                    if(!noNegativos()) return;
                    DesglosarEfectivo();
                    break;
            }
        /*}
        else{*/
            switch(e.getKeyCode()){
                case KeyEvent.VK_ESCAPE: 
                    CerrarVentana();
                    break;
                case KeyEvent.VK_F7: 
                    if(!noNegativos()) return;
                    ventaManual();
                    break;
                case KeyEvent.VK_F10: 
                    if(!noNegativos()) return;
                    realizarCorte();
                    break;
            }
        //}
    }
        
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
    
    public boolean getRespuesta(){
        return transaccion;
    }
    
    class AttributiveCellRendererX extends JLabel  implements TableCellRenderer {
      
        public AttributiveCellRendererX() {
            setOpaque(true);
            //setHorizontalAlignment( RIGHT );
        }
      
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(value==null || value.equals("")){
                if(column!=0){
                    if(column==2){
                        jTblCtdEntregada.setValueAt("0",row,column);
                        setText("0");
                    }
                    else{
                        jTblCtdEntregada.setValueAt("0",row,column);
                        setText("0");
                    }
                }
                else
                    setText(null);
            }
            else
              setText(String.valueOf(value));
            
            if(row == jTblCtdEntregada.getSelectedRow() && column == jTblCtdEntregada.getSelectedColumn())
                this.setBackground(SELECCION);
            else
                this.setBackground(BLANCO);
            
            if(column==0) setHorizontalAlignment( LEFT );
            else setHorizontalAlignment( RIGHT );
          return this;
        }
    }
    
    class AttributiveCellRenderer1 extends JLabel  implements TableCellRenderer {
      
        public AttributiveCellRenderer1() {
            setOpaque(true);
            //setHorizontalAlignment( RIGHT );
        }
      
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(value==null || value.equals("")){
                if(column!=0){
                    if(column==2){
                        jTblFrmPago.setValueAt("0",row,column);
                        setText("0");
                    }
                    else{
                        jTblFrmPago.setValueAt("0",row,column);
                        setText("0");
                    }
                }
                else
                    setText(null);
            }
            else
              setText(String.valueOf(value));
            
            if(row == jTblFrmPago.getSelectedRow() && column == jTblFrmPago.getSelectedColumn())
                this.setBackground(SELECCION);
            else
                this.setBackground(BLANCO);
            
            if(column==0) setHorizontalAlignment( LEFT );
            else setHorizontalAlignment( RIGHT );
          return this;
        }
    }
    
    private boolean noNegativos(){
        if(!noNegativosCtdEntregada()) return false;
        if(!noNegativosFrmPago()) return false;
        if(!noNegativosFolios()) return false;
        return true;
    }
    
    private boolean noNegativosCtdEntregada(){
        if(jTblCtdEntregada.getRowCount()>0){
            int i,j;
            for(i=1; i<jTblCtdEntregada.getRowCount(); i++)
                for(j=1; j<jTblCtdEntregada.getColumnCount(); j++)
                    if(Double.valueOf(jTblCtdEntregada.getValueAt(i,j).toString())<0){
                        jTblCtdEntregada.setColumnSelectionInterval(1,1);
                        jTblCtdEntregada.setRowSelectionInterval(i,i);
                        DialogoAceptar.mostrarDialogo("¡No ingrese valores negativos!","Modifique sus datos correctamente.", Color.RED);
                        jTblCtdEntregada.requestFocusInWindow();
                        return false;
                    }
            return true;
        }
        else return true;
    }
    
    private boolean noNegativosFrmPago(){
        if(jTblFrmPago.getRowCount()>0){
            int i,j;
            for(i=0; i<jTblFrmPago.getRowCount(); i++)
                for(j=1; j<jTblFrmPago.getColumnCount(); j++)
                    if(Double.valueOf(jTblFrmPago.getValueAt(i,j).toString())<0){
                        jTblFrmPago.setColumnSelectionInterval(1,1);
                        jTblFrmPago.setRowSelectionInterval(i,i);
                        DialogoAceptar.mostrarDialogo("¡No ingrese valores negativos!","Modifique sus datos correctamente.", Color.RED);
                        jTblFrmPago.requestFocusInWindow();
                        return false;
                    }
            return true;
        }
        else return true;
    }
    
    private boolean noNegativosFolios(){
        if(jTblFolios.getRowCount()>0){
            int i,j;
            for(i=0; i<jTblFolios.getRowCount(); i++)
                for(j=1; j<jTblFolios.getColumnCount(); j++){
                    if(jTblFolios.getValueAt(i,j).equals("")){
                        jTblFolios.setColumnSelectionInterval(j,j);
                        jTblFolios.setRowSelectionInterval(i,i);
                        DialogoAceptar.mostrarDialogo("¡Ingrese folios!","Modifique sus datos correctamente.", Color.RED);
                        jTblFolios.requestFocusInWindow();
                        return false;
                    }
                    if(Double.valueOf(jTblFolios.getValueAt(i,j).toString())<0){
                        jTblFolios.setColumnSelectionInterval(j,j);
                        jTblFolios.setRowSelectionInterval(i,i);
                        DialogoAceptar.mostrarDialogo("¡No ingrese valores negativos!","Modifique sus datos correctamente.", Color.RED);
                        jTblFolios.requestFocusInWindow();
                        return false;
                    }
                }
            return true;
        }
        else return true;
    }
 
    public long getEmpresaId(String nombreEmpresa){
        for(int i=0; i<tmsEmpresasTbl.size(); i++)
        {
            System.out.println("tmsEmpresasTbl.get("+i+").getEmpresaNombre():  "+tmsEmpresasTbl.get(i).getEmpresaNombre());
            if(tmsEmpresasTbl.get(i).getEmpresaNombre().equals(nombreEmpresa)) return tmsEmpresasTbl.get(i).getEmpresaId();
        }
        return -1;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPnlCtdEntregada;
    private javax.swing.JPanel jPnlFolios;
    private javax.swing.JPanel jPnlFrmPago;
    private javax.swing.JScrollPane jScpCtdEntregada;
    private javax.swing.JScrollPane jScpFolios;
    private javax.swing.JScrollPane jScpFrmPago;
    private javax.swing.JTable jTblCtdEntregada;
    private javax.swing.JTable jTblFolios;
    private javax.swing.JTable jTblFrmPago;
    // End of variables declaration//GEN-END:variables
    private Object[] xEncabezado = {"Nombre Tipo", "Cantidad", "Monto"};
    private DefaultTableModel xListado = new DefaultTableModel(null, xEncabezado){
            public boolean isCellEditable(int row, int col) {
                if(row==0 || col==0) return false;
                return true;
            }
            /*public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }*/
    };
    private Object[] yEncabezado = {"Tipo de Pago", "Cantidad", "Monto"};
    private DefaultTableModel yListado = new DefaultTableModel(null, yEncabezado){
            public boolean isCellEditable(int row, int col) {
                if(row==FilaEfectivo || col==0) return false;
                return true;
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
    };
    private Object[] zEncabezado = {"Empresa", "Ultimo Vendido", "Final Dotacion"};
    private DefaultTableModel zListado = new DefaultTableModel(null, zEncabezado){
            public boolean isCellEditable(int row, int col) {
                if(col==0) return false;
                return true;
            }
    };
    private Object[][] folios;
    private Object[][] ctdEntregadas;
    private Object[][] frmPago;
    private Object[][] vtaMnl;
    private Object[][] Desgloce;
    private long corteId;
    private long usuarioId;
    private TmsCorteTaqFacadeRemote sesionCorteTaqFacate;
    private JDlgSiNo dialogo;
    private boolean transaccion=false;
    private static Color SELECCION = new Color(184, 207, 229);
    private static Color BLANCO = new Color(255, 255, 255);
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");

    private JDlgAceptar DialogoAceptar=new JDlgAceptar();

    private String strFecha;

    private int FilaEfectivo=-1;
    private List<TmsEmpresasTbl> tmsEmpresasTbl = null;
    private String PrefijoTerminal;
    private String empresaPrincipal;
    private String esquema;
    private JClsImpresionReporte jClsImpresionReporte;
    private JClsAbreSocketAS x = null;
    private String edoAnteriorCorte;
}