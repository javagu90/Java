/*
 * JIFCorteTaquilla.java
 *
 * Created on 15 de agosto de 2007, 12:16 PM
 */

package tms_cortetaquilla;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import javax.swing.table.DefaultTableCellRenderer;
import tms_cortetaquilla.util.JClsAbreSocketAS;
import tms_cortetaquilla.util.JClsImpresionReporte;
import tms_cortetaquilla.util.JDlgSiNo;
import tms_cortetaquilla.util.PcInfo;
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
import tms_cortetaquilla.util.JDlgAceptar;
import tms_cortetaquilla.util.Jdlg_Corte;
import tms_cortetaquilla.util.Jdlg_CorteFinDia;
import tms_cortetaquilla.util.jDlgAutorizaSupervisor;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsSesionActividadesTbl;
import xertms.entidad.TmsTiposPasajeroTbl;
import xertms.entidad.TmsVtaTiposPagoTbl;
import xertms.solicitud.TmsCorteTaqFacadeRemote;

/**
 *
 * @author  ocruz
 */
public class JIFCorteTaquilla extends JInternalFrame implements KeyListener{
    
    /** Creates new form JIFCorteTaquilla */
    public JIFCorteTaquilla(Vector datosIniciales) {
        this.datosIniciales = datosIniciales;
        this.pendiente = "P";
        this.USUARIO_ID = Long.valueOf(datosIniciales.get(0).toString());
        this.USUARIO_NUMERO = datosIniciales.get(1).toString();
        this.USUARIO_NOMBRE = datosIniciales.get(2).toString();
        this.SESION_ID = Long.valueOf(datosIniciales.get(3).toString());
        this.MENU_ID = Long.valueOf(datosIniciales.get(4).toString());
        System.out.println("Antes de abrir del socket...");
        tx = new JClsAbreSocketAS(datosIniciales.get(5).toString(),
                Integer.valueOf(datosIniciales.get(6).toString()));
        if(!tx.abreSocketAS()){
            inicioGral=false;
            return;
        }
        System.out.println("Despues de abrir del socket...");
        sesionCorteTaqFacate = lookupTmsCorteTaqFacade();
        jClsImpresionReporte = new JClsImpresionReporte(c);
        System.out.println("antes del initComponents()...");
        initComponents();
        String fecha = sesionCorteTaqFacate._ObtieneFechaHoraBD();
        System.out.println("fecha obtenida: "+fecha);
        Timestamp fechaAct = Timestamp.valueOf(fecha);
        System.out.println("paso del initComponents()...");
        System.out.println("Fecha: "+fechaAct);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        if(!obtieneCaja()){
            inicioGral=false;
            return;
        }
        
        if(!cargaTerminal()){
            inicioGral=false;
            return;
        }
        
        if(!empresaPrincipal()){
            inicioGral=false;
            return;
        }
        
        if(!Empresas()){
            inicioGral=false;
            return;
        }
        System.out.println("Tipos pasaje");
        if(!cargaTiposPasaje()){
            inicioGral=false;
            return;
        }
        if(!cargaFormasPago()){
            inicioGral=false;
            return;
        }
        this.jTblCortes.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);
        this.jTxtFiltro.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);
        this.jCkbFiltro.registerKeyboardAction(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { ; }}, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), JComponent.WHEN_FOCUSED);
        jTblCortes.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
            jTblCortes.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).get(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)));
        inicioGral=true;
    }
    
    public boolean getInicioGral(){ return this.inicioGral; }
    
    private TmsCorteTaqFacadeRemote lookupTmsCorteTaqFacade() {
        try {
            c = new InitialContext();
            return (TmsCorteTaqFacadeRemote) c.lookup("xertms.solicitud.TmsCorteTaqFacadeRemote");
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScpCortes = new javax.swing.JScrollPane();
        jTblCortes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTxtFiltro = jTxtFiltro = new JDateTextField();
        jCkbFiltro = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar fecha de corte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTxtFiltro.setName("jTxtFiltro"); // NOI18N
        jTxtFiltro.addKeyListener(this);
        jTxtFiltro.setFocusTraversalKeysEnabled(true);
        jTxtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFiltroActionPerformed(evt);
            }
        });
        jTxtFiltro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtFiltroFocusGained(evt);
            }
        });

        jCkbFiltro.setFont(new java.awt.Font("Tahoma", 1, 11));
        jCkbFiltro.setSelected(true);
        jCkbFiltro.setText("Solo Pendientes");
        jCkbFiltro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCkbFiltro.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCkbFiltro.setName("Paloma"); // NOI18N
        jCkbFiltro.addKeyListener(this);
        jCkbFiltro.setFocusTraversalKeysEnabled(true);
        jCkbFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCkbFiltroItemStateChanged(evt);
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
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jTxtFiltro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jCkbFiltro))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("<html><font color=ff0000>F1</font> Ingresar fecha | <font color=ff0000>F2</font> Pendientes/Realizados | <font color=ff0000>ENTER</font> Buscar corte | <font color=ff0000>F3</font> Realizar Corte | <font color=ff0000>F5</font> Reimprimir | <font color=ff0000>F6</font> Corte de dia |<br><font color=ff0000>F4</font> Cerrar aplicacion</html>");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Rev.04.07.14");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado de corte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jLabel4)
                    .add(jLabel5))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jScpCortes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                        .add(10, 10, 10))
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 180, Short.MAX_VALUE)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel1))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(11, 11, 11)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScpCortes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtFiltroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtFiltroFocusGained
// TODO add your handling code here:
        xListado.setDataVector(null, encabezado);
        AnchoColumnas();
        jTxtFiltro.setText(formatoFecha.format(new Date()));
        jTxtFiltro.selectAll();
        jTxtFiltro.requestFocusInWindow();
    }//GEN-LAST:event_jTxtFiltroFocusGained
    
    private void CorteDia(){
        Jdlg_CorteFinDia dlg = new Jdlg_CorteFinDia(null,true);
        dlg.setVisible(true);
        String strFecha = dlg.getFecha();
        String strFechaInicial = strFecha+" 00:00:00";
        String strFechaFinal = strFecha+" 23:59:59";
        String algoritmoVigente="",rt="",rm="";
        usuarioId = 1;
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
        /*ArgumentoControl="cmdkey="+paramReportes[1]+"&TMSRPCRT.rdf"+"&P_FECHA_CORTE='"+strFecha+"'&P_TIPO_REPORTE='D'";
        bc = new BrowserControl();
        bc.displayURL(paramReportes[2]+ArgumentoControl);
        DialogoAceptar.mostrarDialogo("¡Importante!","Reporte enviado al explorador de Internet predeterminado.", Color.RED);*/

        //GENERA CORTE DUMMY PARA CORTE FIN DE DIA


        //REGISTRAR REFERENCIAS
        Timestamp fechaAct = Timestamp.valueOf(sesionCorteTaqFacate._ObtieneFechaHoraBD());
        boolean resp = sesionCorteTaqFacate.corteDummyFinDia(usuarioId);
        if(!resp){
            DialogoAceptar.mostrarDialogo("¡El corte de fin de dia no se efectuo!", "<html>Error al generar el Corte de Fin de Dia.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            this.dispose();
            return;
        }

        System.out.println("SESION CORTE FIN DE DIA ");
        Object SesionActCorteId = ((Vector) sesionCorteTaqFacate.queryActividadSesionCorteFinDiaGetId()).get(0);
        if(SesionActCorteId == null){
            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No esta configurada la actividad sesion CORTE FIN DE DIA.<br>" +
                    "Contacte al administrador del sistema.</html>", Color.RED);
            System.out.println("No encontro el ID del Estado de la sesion de CORTE FIN DE DIA ");
            this.dispose();
            return;
        }
        long sesionDeCorteFinDia = Long.valueOf(SesionActCorteId.toString());

        TmsSesionActividadesTbl tmsSesionActividadesTbl = new TmsSesionActividadesTbl();
        tmsSesionActividadesTbl.setCorteId(Long.valueOf("1"));
        tmsSesionActividadesTbl.setActividadSesionId(sesionDeCorteFinDia);
        System.out.println("empresaPrincipal:  "+strEmpresaPrincipal);
        long empId = getEmpresaId(strEmpresaPrincipal);
        System.out.println("setEmpresaId():  "+empId);
        tmsSesionActividadesTbl.setEmpresaId(empId);
        SimpleDateFormat formatoFecha= new SimpleDateFormat("ddMMyyyy");
        String termminal = datosTerminal[1];
        String refer = formatoFecha.format(fi)+termminal;
        tmsSesionActividadesTbl.setValorActividad(refer);
        tmsSesionActividadesTbl.setFechaHoraActividad(fechaAct);
        tmsSesionActividadesTbl.setAutorizadoPor(usuarioId);
        tmsSesionActividadesTbl.setCreadoPor(usuarioId);
        tmsSesionActividadesTbl.setFechaCreacion(fechaAct);
        tmsSesionActividadesTbl.setUltimaActualizacionPor(usuarioId);
        tmsSesionActividadesTbl.setUltimaFechaActualizacion(fechaAct);
         String ref = formatoFecha.format(fi)+termminal;
         System.out.println("ref: "+ref);
         algoritmoVigente = sesionCorteTaqFacate.algoritmoBancoVigente();
         if(algoritmoVigente.equals("BANCOMER_10")){
             rt = formatoFecha.format(fi)+termminal+"T"+calculaDV_Alg35(ref+"T");
             rm= formatoFecha.format(fi)+termminal+"M"+calculaDV_Alg35(ref+"M");
         }else{
             rt = sesionCorteTaqFacate.tmsAlgoritmoBanco(ref+"T",algoritmoVigente);
             rm = sesionCorteTaqFacate.tmsAlgoritmoBanco(ref+"M",algoritmoVigente);
         }
         String ra= "";//formatoFecha.format(fi)+termminal+"A"+calculaDV_Alg35(ref+"A");
         String rf= "";//formatoFecha.format(fi)+termminal+"F"+calculaDV_Alg35(ref+"F");
         String rs= "";//formatoFecha.format(fi)+termminal+"S"+calculaDV_Alg35(ref+"S");
         System.out.println("P_REFERENCIA_T:"+rt);
         sesionCorteTaqFacate.addSesionCorteFinDia(PrefijoTerminal, tmsSesionActividadesTbl, ids,  rt, rm, ra, rf, rs);
//        if(retorno==null){
//            //transaccion = false;
//            DialogoAceptar.mostrarDialogo("¡El corte no se efectuo!", "<html>No existe una conexion a la base de datos.<br>" +
//                    "Contacte al administrador del sistema.</html>", Color.RED);
//            System.out.println("no se pudo insertar la actividad sesion... ");
//            this.dispose();
//            return;
//        }


        if(!jClsImpresionReporte.imprimirCorte(null, fi, ff, "D", "N", "CORTE_DIA_PRINCIPAL.jasper",ids,datosTerminal[1],refer)){
            DialogoAceptar.mostrarDialogo("¡No fue posible mostrar el reporte!","Contacte al administrador del sistema.", Color.RED);
        }
    }
    
    private void Reimprimir(){
        fila = jTblCortes.getSelectedRow();
        if(fila==-1){
            DialogoAceptar.mostrarDialogo("¡No ha seleccionado ningun corte!","Presione ENTER para continuar...", Color.RED);
            return;
        }
        if(listado[fila][5].equals("P") || listado[fila][5].equals("T")  || listado[fila][5].equals("E") || listado[fila][5].equals("B") ){
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
        Jdlg_Corte dlg = new Jdlg_Corte(null, true, "¡Reimprimir Corte!");
        dlg.setVisible(true);
        if(dlg.getDetalle().equals("")) return;
        //ArgumentoControl="cmdkey="+paramReportes[1]+"&TMSRPCRT.rdf"+"&"+listado[fila][0]+"&'"+strFecha+"'&'"+strFecha+"'&'"+dlg.getDetalle()+"'";
        /*ArgumentoControl="cmdkey="+paramReportes[1]+"&TMSRPCRT.rdf"+"&P_CORTE_ID="+listado[fila][0]+"&P_DESGLOSADO='"+dlg.getDetalle()+"'";
        bc = new BrowserControl();
        bc.displayURL(paramReportes[2]+ArgumentoControl);
        DialogoAceptar.mostrarDialogo("¡Importante!","Reporte enviado al explorador de Internet predeterminado.", Color.RED);*/
        System.out.println("listado[fila][0]: "+listado[fila][0]);
        //datosTerminal = new String[3];
        //datosTerminal[1] = "CAPU";
        System.out.println("datosTerminal[1]: "+datosTerminal[1]);
        if(!jClsImpresionReporte.imprimirCorte(listado[fila][0].toString(), null, null, "T", dlg.getDetalle(), "CORTE_PRINCIPAL.jasper",listado[fila][0].toString(),datosTerminal[1],"")){
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

    private void Detallar(){
        fila = jTblCortes.getSelectedRow();
        if(fila==-1){
            DialogoAceptar.mostrarDialogo("¡No ha seleccionado ningun corte!","Presione ENTER para continuar...", Color.RED);
            xListado.setDataVector(null, encabezado);
            AnchoColumnas();
            this.jTxtFiltro.setText(formatoFecha.format(new Date()));
            jTxtFiltro.selectAll();
            jTxtFiltro.requestFocusInWindow();
            return;
        }
        if(listado[fila][5].toString().equals("R") || listado[fila][5].toString().equals("D")){
            DialogoAceptar.mostrarDialogo("¡El corte de turno ya fue realizado!","Presione ENTER para continuar...", Color.RED);
            jTblCortes.setRowSelectionInterval(0,0);
            jTblCortes.requestFocusInWindow();
            return;
        }
        
        if(listado[fila][5].toString().equals("P") || listado[fila][5].toString().equals("T") || listado[fila][5].toString().equals("E") || listado[fila][5].toString().equals("B"))
        {
                usuarioId=Long.valueOf(listado[fila][6].toString());
                edoAnteriorCorte = listado[fila][5].toString();
                System.out.println("edoAnteriorCorte= "+edoAnteriorCorte);
                int edo=validarSesion(listado[fila][5].toString());
                switch(edo){
                    case 0:
                        DialogoAceptar.mostrarDialogo("¡No existe ninguna sesion de venta registrada!","Presione ENTER para continuar...", Color.RED);
                        break;
                    case 1: case 2:
                        long corteId=Long.valueOf(xListado.getValueAt(fila,0).toString());
                        if(edo==2){
                            dialogo = new JDlgSiNo("¡Sesion de Venta Abierta!", "<html>El usuario "+xListado.getValueAt(fila,1).toString()+" sigue asignado a venta.<br>¿Desea terminar la sesión y realizar el corte?</html>");
                            if(!dialogo.getResultado()) return;
                            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(sesionCorteTaqFacate,"5105", "Finalizar Sesion Venta");
                            dlg.setVisible(true);
                            if(!dlg.getRespuesta()) return;
                            if(!tx.abreSocketAS()){
                                DialogoAceptar.mostrarDialogo("¡No existe conexion con la base de datos!","Contacte al administrador del sistema.", Color.RED);
                                return;
                            }
                            sesionCorteTaqFacate.queryFinalizaSesionVenta(PrefijoTerminal, corteId, USUARIO_ID);
                        }
                        String strFecha=xListado.getValueAt(fila,3).toString().substring(0,10);
                        Vector x = new Vector();
                        x=(Vector) sesionCorteTaqFacate.queryRecolMonto(corteId);
                        if(x==null || x.size()==0 || x.get(0)==null || x.get(1)==null){
                            DialogoAceptar.mostrarDialogo("¡No es posible realizar el corte!", "<html>No existe una conexion a la base de datos.<br>Contacte al administrador de la red.</html>", Color.RED);
                            jTblCortes.setRowSelectionInterval(0,0);
                            jTblCortes.requestFocusInWindow();
                            return;
                        }
                        this.ctdEntregadas[0][1] = x.get(0);
                        this.ctdEntregadas[0][2] = x.get(1);
                        //// VAGL 12/04/2012
                         //Timestamp fechaAct = new Timestamp(new Date().getTime());
                         Timestamp fechaAct = Timestamp.valueOf(sesionCorteTaqFacate._ObtieneFechaHoraBD());
                        String estado = "E";
                        if(edoAnteriorCorte.equals("T") || edoAnteriorCorte.equals("B")) estado = "B";
                        if(!sesionCorteTaqFacate.updateCorteEnProceso(corteId, usuarioId, fechaAct, estado)){
                            DialogoAceptar.mostrarDialogo("¡No es posible realizar el corte!", "<html>No existe una conexion a la base de datos.<br>Contacte al administrador de la red.</html>", Color.RED);
                            jTblCortes.setRowSelectionInterval(0,0);
                            jTblCortes.requestFocusInWindow();
                        }
                        else{
                            DialogoAceptar.mostrarDialogo("¡Se inicia proceso de corte!", "Presione ENTER para continuar...", colorDialogoActivo);
                            JDlgDetalleCorte dlgDetalle = new JDlgDetalleCorte(null, true, "", corteId, 
                                    this.USUARIO_ID, this.ctdEntregadas, this.frmPago, sesionCorteTaqFacate,
                                    strFecha, PrefijoTerminal, tmsEmpresasTbl, strEmpresaPrincipal,
                                    datosTerminal[2], jClsImpresionReporte, tx,edoAnteriorCorte);
                            dlgDetalle.setVisible(true);
                            if(!dlgDetalle.getRespuesta()){
                                //// VAGL 12/04/2012
                                 //fechaAct = new Timestamp(new Date().getTime());
                                 fechaAct = Timestamp.valueOf(sesionCorteTaqFacate._ObtieneFechaHoraBD());

                                if(!sesionCorteTaqFacate.updateCortePendiente(corteId, usuarioId, fechaAct,edoAnteriorCorte))
                                    DialogoAceptar.mostrarDialogo("¡Error!", "<html>El corte quedo en proceso.<br>Contacte al administrador de la red.</html>", Color.RED);
                                else
                                    DialogoAceptar.mostrarDialogo("¡Proceso de corte cancelado!", "El corte sigue pendiente.", Color.RED);
                            }
                            xListado.setDataVector(null, encabezado);
                            AnchoColumnas();
                            this.jTxtFiltro.setText(formatoFecha.format(new Date()));
                            jTxtFiltro.selectAll();
                            jTxtFiltro.requestFocusInWindow();
                        }
                    break;
                }
        }
        else
        {
            System.out.println("Status del corte: "+(listado[fila][5].toString()));
            DialogoAceptar.mostrarDialogo("¡El corte no puede ser realizado!. Posible venta en proceso","Presione ENTER para continuar...", Color.RED);
            jTblCortes.setRowSelectionInterval(0,0);
            jTblCortes.requestFocusInWindow();
            return;
        }
    }
    
    private int validarSesion(String edoCorte) {
        Date fechaSesionIni;
        Date fechaSesionFin;
        String estaTerminalNombre;
        String terminalIniSesion;
        String estadoCorte;
        //Verificar si existe sesion abierta
        if(!cargarFechasSesion()) return 0;

        fechaSesionIni = fechasIni[0];
        fechaSesionFin = fechasIni[1];
        
        //Turno Abierto. Recuperar Sesion
        if(fechaSesionIni != null && (edoCorte.equals("P") || edoCorte.equals("T")) ){
            if(fechaSesionFin == null){
                fechaSesionFin = new Date(fechaSesionIni.getTime()-1);
            }
            
            if(fechaSesionIni.getTime() <= fechaSesionFin.getTime()){
                //Sesion Cerrada.
                return 1;
            }else{
                //Sesión Abierta.
                return 2;
            }
        }
        return 1;
    }
    
    private void jCkbFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCkbFiltroItemStateChanged
// TODO add your handling code here:
        if(evt.getStateChange()==ItemEvent.SELECTED)
            this.pendiente = "P";
        else
            this.pendiente = "";
    }//GEN-LAST:event_jCkbFiltroItemStateChanged

    private void jTxtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFiltroActionPerformed
    
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
        
        listado = cargaDatos((Vector) sesionCorteTaqFacate.queryTmsCortesTblForSales(strFecha, pendiente));
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
    
    private boolean cargaTiposPasaje(){
        List<TmsTiposPasajeroTbl> Lineas = sesionCorteTaqFacate.queryTmsTiposPasajeroTblfindAll();
        if(Lineas.size()==0){
            DialogoAceptar.mostrarDialogo("¡Tipos de pasajero incorrectos!","Contacte al administrador del sistema.", Color.RED);
            return false;
        }
        
        
        String fecha;
        int i, j;
        Object[] Entregadas = new Object[5];
        Vector x = new Vector();
        Entregadas[0] = "RECOLECCIONES";
        Entregadas[1] = "0";
        Entregadas[2] = "0";
        Entregadas[3] = "";
        Entregadas[4] = "RECOLECCIONES";
        x.add(Entregadas);
        Entregadas = new Object[5];
        Entregadas[0] = "CANCELADOS";
        Entregadas[1] = "0";
        Entregadas[2] = "0";
        Entregadas[3] = "";
        Entregadas[4] = "CANCELADOS";
        x.add(Entregadas);
        /*Entregadas = new Object[5];
        Entregadas[0] = "CAMBIOS DE HORARIO";
        Entregadas[1] = "0";
        Entregadas[2] = "0";
        Entregadas[3] = "";
        Entregadas[4] = "CAMBIOS DE HORARIO";
        x.add(Entregadas);*/
        for(i=0; i<Lineas.size(); i++){
            if(!Lineas.get(i).getNombreTipo().equals("ADULTO") && !Lineas.get(i).getNombreTipo().equals("MENOR")){
                Entregadas = new Object[5];
                Entregadas[0] = Lineas.get(i).getNombreTipo();
                Entregadas[1] = "0";
                Entregadas[2] = "0";
                Entregadas[3] = Lineas.get(i).getTipoPasajeroId();
                Entregadas[4] = Lineas.get(i).getLetraTipo();
                x.add(Entregadas);
            }
        }
        
        System.out.println("TAMAÑO "+x.size());
        ctdEntregadas = new Object[x.size()][5];
        for(i=0; i<x.size(); i++){
            Entregadas = (Object[]) x.get(i);
            for(j=0; j<5; j++)
                ctdEntregadas[i][j] =  Entregadas[j];
        }
        
        return true;
    }
    
    private boolean cargaFormasPago(){
        List<TmsVtaTiposPagoTbl> Lineas = sesionCorteTaqFacate.queryTmsVtaTiposPagoTblfindAll();
        if(Lineas.size()==0){
            DialogoAceptar.mostrarDialogo("¡Formas de pago incorrectas!","Contacte al administrador del sistema.", Color.RED);
            return false;
        }
        frmPago = new Object[Lineas.size()][5];
        String fecha;
        int i, j;
        for(i=0; i<Lineas.size(); i++){
            frmPago[i][0] = Lineas.get(i).getNombre();
            frmPago[i][1] = "0";
            frmPago[i][2] = "0";
            frmPago[i][3] = Lineas.get(i).getTipoPagoId();
            frmPago[i][4] = Lineas.get(i).getClave();
        }
        return true;
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
                    Detallar();
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
                            e.getComponent().getName().equals("Paloma")) Filtro();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }
    
    //
    public boolean cargarFechasSesion(){
        long[] foliosId = new long[2];
        foliosId[0] = sesionCorteTaqFacate.obtenerFolioActIdIniCor("INISES",usuarioId);
        if(foliosId[0]==-1) return false;
        fechasIni[0] = sesionCorteTaqFacate.obtenerFechaSesion(foliosId[0]);
        foliosId[1] = sesionCorteTaqFacate.obtenerFolioActIdIniCor("FINSES",usuarioId);
        if(foliosId[0]==-1) return false;
        fechasIni[1] = sesionCorteTaqFacate.obtenerFechaSesion(foliosId[1]);
        return true;
    }
    
    public void setMenuId(long menuId){ this.MENU_ID=menuId; }

    private boolean obtieneCaja() {
        estaTerminal = new PcInfo();
        cajaId = sesionCorteTaqFacate.obtenerCaja(estaTerminal.getHostName().toUpperCase());
        //cajaId = sesionCorteTaqFacate.obtenerCaja("TAQPUE4");//estaTerminal.getHostName().toUpperCase());
        System.out.println("cajaId = "+cajaId);
        if(cajaId==-1) return false;
        return true;
    }
    
    private boolean cargaTerminal() {
        datosTerminal = sesionCorteTaqFacate.obtenerTerminal();
        if(datosTerminal==null) return false;
        if(datosTerminal[0].length()>3) PrefijoTerminal  = datosTerminal[0].substring(0,3);
        if(datosTerminal[0].length()==3) PrefijoTerminal = datosTerminal[0];
        if(datosTerminal[0].length()==2) PrefijoTerminal = datosTerminal[0]+"0";
        if(datosTerminal[0].length()==1) PrefijoTerminal = datosTerminal[0]+"00";
        System.out.println("PrefijoTerminal "+PrefijoTerminal);
        return true;
    }
    
    private boolean empresaPrincipal() {
        strEmpresaPrincipal = sesionCorteTaqFacate.obtenerEmpresaPrincipal(cajaId);
        if(strEmpresaPrincipal==null) return false;
        return true;
    }

    private boolean Empresas(){
        tmsEmpresasTbl = sesionCorteTaqFacate.queryTmsEmpresasTblAll();
        if(tmsEmpresasTbl == null) return false;
        for(int i=0; i<tmsEmpresasTbl.size(); i++)
            System.out.println("tmsEmpresasTbl.get("+i+").getEmpresaNombre():  "+tmsEmpresasTbl.get(i).getEmpresaNombre());
        return true;
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

    public long getEmpresaId(String nombreEmpresa){
        for(int i=0; i<tmsEmpresasTbl.size(); i++)
        {
            System.out.println("tmsEmpresasTbl.get("+i+").getEmpresaNombre():  "+tmsEmpresasTbl.get(i).getEmpresaNombre());
            if(tmsEmpresasTbl.get(i).getEmpresaNombre().equals(nombreEmpresa)) return tmsEmpresasTbl.get(i).getEmpresaId();
        }
        return -1;
    }


 private int calculaDV_Alg35(String nr){
  Vector<Integer> inicial= new Vector<Integer>();
  Vector<Integer> segundo= new Vector<Integer>();
  Vector<String> letras= new Vector<String>();
  Vector<String> numeros= new Vector<String>();

  int n = 0;
  //int A = 1, B = 2,C = 3,D = 4,E = 5,F= 6, G = 7,H = 8,I = 9,J = 10,K = 11, L = 12, M = 13,N = 14, O = 15, P = 16, Q = 17, R=18,S = 19,T = 20, U = 21, V = 22, W = 23,X = 24, Y = 25,Z = 26;
  letras.add("");
  letras.add("A");letras.add("B");letras.add("C");letras.add("D");letras.add("E");letras.add("F");letras.add("G");letras.add("H");
  letras.add("I");letras.add("J");letras.add("K");letras.add("L");letras.add("M");letras.add("N");letras.add("O");letras.add("P");
  letras.add("Q");letras.add("R");letras.add("S");letras.add("T");letras.add("U");letras.add("V");letras.add("W");letras.add("X");
  letras.add("Y");letras.add("Z");
  numeros.add("0");numeros.add("1");numeros.add("2");numeros.add("3");numeros.add("4");numeros.add("5");numeros.add("6");numeros.add("7");numeros.add("8");numeros.add("9");
  char[] car = nr.toCharArray();
  for(int i=0; i<car.length; i++)
  {
      char c = car[i];
      int index = letras.indexOf(""+c);
      if(index>=0)
          inicial.add(index);
      else
          inicial.add(numeros.indexOf(""+c));
  }
 //System.out.println("Paso1: "+inicial);
  for(int i = 0; i<inicial.size(); i++){
      for(int j = 1; j<=3; j++)
      {
          //System.out.println("i = "+i);
          if(i > (inicial.size() - 1))
              break;
          if(j==1)segundo.add(regresaUno(""+(((int)inicial.get(i))*4)));
          if(j==2)segundo.add(regresaUno(""+(((int)inicial.get(i))*3)));
          if(j==3)segundo.add(regresaUno(""+(((int)inicial.get(i))*8)));
          i++;
      }
      i--;
  }
  //System.out.println("Paso2: "+segundo);
  int sum = 0;
  for(int i=0; i<segundo.size(); i++)
      sum = sum + ((int) segundo.get(i));
  //System.out.println("sumaPaso2: "+sum);
  int prox = regresaProximaDecena(""+sum);
  //System.out.println("Resta: "+prox +" - "+sum);
  n = prox - sum;
  return n;
  }

 private int regresaUno(String num){
    //System.out.println("llamo regresaUno con: "+num);
     int unNum = 0;
     if(num.length()==1)
         return Integer.valueOf(num);
     else
     {

         while(num.length()>1)
         {
             String n = ""+num;
             char[] cn = n.toCharArray();
             int nn = 0;
             for(int i=0; i<cn.length; i++)
              nn = nn+ Integer.valueOf(""+cn[i]);
             num = ""+nn;
           // System.out.println("  "+num);
         }
         unNum = Integer.valueOf(num);
     }
     return unNum;
 }

 private int regresaProximaDecena(String num){
    //System.out.println("Decena: "+ num);
     //int decena = 0;
     //char[] cd = num.toCharArray();
     char[] cdp = num.toCharArray();
     char numDecena = cdp[cdp.length -2];
     String s = "";
     //System.out.println("cdp[cdp.length -1] = "+cdp[cdp.length -1]);
     if((""+cdp[cdp.length -1]).equals("0") )
        s = ""+ numDecena;
     else
        s = ""+(Integer.valueOf(""+numDecena) + 1);
     cdp[cdp.length -2] = (s.toCharArray())[0];
     cdp[cdp.length -1] = '0';
     //int decenaProxima = 0;
     String dp = "";
     for(int i=0; i<cdp.length; i++)
         dp = dp +""+cdp[i];
     //System.out.println("Decena Proxima: "+ dp);
     return Integer.valueOf(dp);
 }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private TmsCorteTaqFacadeRemote sesionCorteTaqFacate;
    private JDlgSiNo dialogo;
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat fFH= new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
    private String pendiente="";
    private int fila;
    private Object[][] ctdEntregadas;
    private Object[][] frmPago;
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
    private String PrefijoTerminal;
    private String strEmpresaPrincipal;
    private long cajaId;
    private List<TmsEmpresasTbl> tmsEmpresasTbl = null;
    private String ArgumentoControl;
    private String[] datosTerminal=null;
    private PcInfo estaTerminal;
    private JClsImpresionReporte jClsImpresionReporte;
    private Context c;
    private JClsAbreSocketAS tx = null;
    private String edoAnteriorCorte = "";
}