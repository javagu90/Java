/*
 * JIFCFNDatosIniciales.java
 *
 * Created on 29 de octubre de 2007, 11:41 AM
 */

package tmsroles;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import tms_TextFields.JHourTextField;
import tmsroles.entidad.TmsRolesBaseTbl;
import tmsroles.entidad.TmsRolesMaestroTbl;
import tmsroles.entidad.TmsServiciosTbl;

/**
 *
 * @author  vgonzalez
 */
public class JIFCFNDatosIniciales extends javax.swing.JInternalFrame {// javax.swing.JFrame { //
    
    /** Creates new form JIFCFNDatosIniciales */
    public JIFCFNDatosIniciales(Vector pDatosIniciales) {
        this.usuarioId = Long.valueOf(pDatosIniciales.get(0).toString());
        this.sesionId = Long.valueOf(pDatosIniciales.get(3).toString());
        initComponents();
        this.setIconifiable(true);
        this.setTitle("Generacion de corridas y roles de autobuses     Rev06.08.08");
        listaServicios = (List<TmsServiciosTbl>) busquedas.serviciosTblFacadeRemote.findAll();
        System.out.println("TimeZone(Antes)  : " + TimeZone.getDefault());
        TimeZone.setDefault(this.busquedas.serviciosTblFacadeRemote.getTimeZone());
        System.out.println("TimeZone(Despues): " + TimeZone.getDefault());

        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        if(listaServicios.size()==0)
            new jdlg_advertencia("¡No existe ningun servicio dado de alta en el Sistema! ","Favor de contactar al Administrador del Sistema","Error de configuracion").setVisible(true);
        else
        {
            jcmb_servicios.removeAllItems();
            for(int i=0; i<listaServicios.size(); i++){
                TmsServiciosTbl servicio = listaServicios.get(i);
                jcmb_servicios.addItem(servicio.getServicioNombre());
            }
        }
        jcmb_servicios.setFocusTraversalKeysEnabled(false);
        jcmb_ofertas.setFocusTraversalKeysEnabled(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jtbl_rutas.setModel(modeloRutas);
        //jtbl_rutas.setCellSelectionEnabled(false);
        jtbl_rutas.setSurrendersFocusOnKeystroke(true);
        jtbl_rutas.setFocusTraversalKeysEnabled(false);
        jtbl_rutas.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jtbl_rolesBase.setModel(modeloRolesBase);
        jtbl_rolesBase.setSurrendersFocusOnKeystroke(true);
        jtbl_rolesBase.setFocusTraversalKeysEnabled(false);
        jtbl_rolesBase.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jpnl_Roles.setVisible(false);
        jpnl_ofertas.setVisible(false);
        jpnl_fondo.setVisible(true);
        resizeColumnasRutas();
    }

      private void resizeColumnasRoles(){
        TableColumn columinv = jtbl_rolesBase.getColumnModel().getColumn(0); columinv.setMinWidth( 250 );columinv.setMaxWidth( 250 );columinv.setPreferredWidth(250);
        columinv = jtbl_rolesBase.getColumnModel().getColumn(1); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
        columinv = jtbl_rolesBase.getColumnModel().getColumn(2); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
        columinv = jtbl_rolesBase.getColumnModel().getColumn(3); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
        columinv = jtbl_rolesBase.getColumnModel().getColumn(4); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
        columinv = jtbl_rolesBase.getColumnModel().getColumn(5); columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
        columinv = jtbl_rolesBase.getColumnModel().getColumn(6); columinv.setMinWidth( 55 );columinv.setMaxWidth(55);columinv.setPreferredWidth(55);
        columinv = jtbl_rolesBase.getColumnModel().getColumn(7);columinv.setMinWidth(55);columinv.setMaxWidth(55);columinv.setPreferredWidth(55);        
        columinv = jtbl_rolesBase.getColumnModel().getColumn(8);columinv.setMinWidth(90);columinv.setMaxWidth(90);columinv.setPreferredWidth(90);        
        }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcmb_servicios = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcmb_ofertas = new javax.swing.JComboBox();
        jpnl_ofertas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_rutas = new javax.swing.JTable();
        jtbl_rutas = new JTable()
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
        jlbl_barraEstado = new javax.swing.JLabel();
        jpnl_Roles = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneroles = new javax.swing.JScrollPane();
        jtbl_rolesBase = new javax.swing.JTable();
        jtbl_rolesBase = new JTable()
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
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jcmb_rolesMaestro = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jlbl_fechaInicial = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlbl_fechaFinal = new javax.swing.JLabel();
        jpnl_fondo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Servicio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Servicio:");

        jcmb_servicios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Servicios" }));
        jcmb_servicios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_serviciosFocusGained(evt);
            }
        });
        jcmb_servicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_serviciosKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Ofertas:");

        jcmb_ofertas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ofertas de Servicio" }));
        jcmb_ofertas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_ofertasFocusGained(evt);
            }
        });
        jcmb_ofertas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_ofertasKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jcmb_servicios, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 287, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(19, 19, 19)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jcmb_ofertas, 0, 344, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jcmb_servicios, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jcmb_ofertas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jpnl_ofertas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Oferta de Servicio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jtbl_rutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbl_rutas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_rutasFocusGained(evt);
            }
        });
        jtbl_rutas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_rutasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbl_rutasKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_rutas);

        org.jdesktop.layout.GroupLayout jpnl_ofertasLayout = new org.jdesktop.layout.GroupLayout(jpnl_ofertas);
        jpnl_ofertas.setLayout(jpnl_ofertasLayout);
        jpnl_ofertasLayout.setHorizontalGroup(
            jpnl_ofertasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_ofertasLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnl_ofertasLayout.setVerticalGroup(
            jpnl_ofertasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_ofertasLayout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel3");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jpnl_Roles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Roles Guardados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Roles Base", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jtbl_rolesBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbl_rolesBase.setEnabled(false);
        jScrollPaneroles.setViewportView(jtbl_rolesBase);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPaneroles, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 742, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPaneroles, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rol Maestro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Rol Maestro:");

        jcmb_rolesMaestro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Roles Maestro" }));
        jcmb_rolesMaestro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_rolesMaestroFocusGained(evt);
            }
        });
        jcmb_rolesMaestro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_rolesMaestroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcmb_rolesMaestroKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Fecha Inicial:");

        jlbl_fechaInicial.setText("  ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Fecha Final:");

        jlbl_fechaFinal.setText("  ");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jcmb_rolesMaestro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 249, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(28, 28, 28)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_fechaInicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(42, 42, 42)
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_fechaFinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jcmb_rolesMaestro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jlbl_fechaInicial)
                    .add(jLabel6)
                    .add(jLabel5)
                    .add(jlbl_fechaFinal))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jpnl_RolesLayout = new org.jdesktop.layout.GroupLayout(jpnl_Roles);
        jpnl_Roles.setLayout(jpnl_RolesLayout);
        jpnl_RolesLayout.setHorizontalGroup(
            jpnl_RolesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jpnl_RolesLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpnl_RolesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnl_RolesLayout.setVerticalGroup(
            jpnl_RolesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpnl_RolesLayout.createSequentialGroup()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jpnl_fondoLayout = new org.jdesktop.layout.GroupLayout(jpnl_fondo);
        jpnl_fondo.setLayout(jpnl_fondoLayout);
        jpnl_fondoLayout.setHorizontalGroup(
            jpnl_fondoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 794, Short.MAX_VALUE)
        );
        jpnl_fondoLayout.setVerticalGroup(
            jpnl_fondoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 84, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jpnl_Roles, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jpnl_ofertas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jpnl_fondo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnl_fondo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnl_Roles, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpnl_ofertas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .add(15, 15, 15)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmb_rolesMaestroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_rolesMaestroKeyReleased
        if(evt.getKeyCode() == evt.VK_UP || evt.getKeyCode() == evt.VK_DOWN){
            SimpleDateFormat formatfc = new SimpleDateFormat("dd/MM/yyyy");
            TmsRolesMaestroTbl rolmestro = listaRolesMaestro.get(jcmb_rolesMaestro.getSelectedIndex());//busquedas.rolesMaestroTblFacadeRemote.find(rolma.getRolMaestroId());
            jlbl_fechaInicial.setText(""+formatfc.format(rolmestro.getFechaInicial()));
            jlbl_fechaFinal.setText(""+formatfc.format(rolmestro.getFechaFinal()));
            List<TmsRolesBaseTbl> listadoRolesBase = busquedas.rolesBaseTblFacadeRemote.buscaRolBasePorRolMaestroI(rolmestro.getRolMaestroId().longValue());
            Object[][] datos = new Object[listadoRolesBase.size()][9];
            for(int i=0; i<listadoRolesBase.size(); i++){
                TmsRolesBaseTbl rolesbases =listadoRolesBase.get(i);
                datos[i][0] = rolesbases.getRolBaseCategoria();
                if(rolesbases.getAplicaLunes().equals("S"))
                    datos[i][1] = true;
                else
                    datos[i][1] = false;
                if(rolesbases.getAplicaMartes().equals("S"))
                    datos[i][2] = true;
                else
                    datos[i][2] = false;
                if(rolesbases.getAplicaMiercoles().equals("S"))
                    datos[i][3] = true;
                else
                    datos[i][3] = false;
                if(rolesbases.getAplicaJueves().equals("S"))
                    datos[i][4] = true;
                else
                    datos[i][4] = false;
                if(rolesbases.getAplicaViernes().equals("S"))
                    datos[i][5] = true;
                else
                    datos[i][5] = false;
                if(rolesbases.getAplicaSabado().equals("S"))
                    datos[i][6] = true;
                else
                    datos[i][6] = false;
                if(rolesbases.getAplicaDomingo().equals("S"))
                    datos[i][7] = true;
                else
                    datos[i][7] = false;
                datos[i][8] = rolesbases.getCantidadAutobuses().intValue();
                //i++;
            }
            modeloRolesBase.setDataVector(datos,encabezadosRolesBase);
            resizeColumnasRoles();
        }
    }//GEN-LAST:event_jcmb_rolesMaestroKeyReleased

    private void jcmb_rolesMaestroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_rolesMaestroKeyPressed
        if(evt.getKeyCode() == evt.VK_F4){
         jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion", "¿Seguro que desea eliminar el Rol Maestro?");
          psn.setVisible(true);
          if(respuestaSN)
          {
            boolean bien = true;
            rolmestroseleccionado = listaRolesMaestro.get(jcmb_rolesMaestro.getSelectedIndex());//busquedas.rolesMaestroTblFacadeRemote.find(rolma.getRolMaestroId());
            System.out.println("Rol Maestro Seleccionado: "+rolmestroseleccionado.getRolMaestroNombre());
            System.out.println("Rol Maestro Id ="+rolmestroseleccionado.getRolMaestroId().longValue());
            Vector rolesBase=(Vector) busquedas.variosFacadeRemote.buscarRolesBasesPorRolMaestro(rolmestroseleccionado.getRolMaestroId().longValue());
            System.out.println("Rol Base ="+rolesBase);
            if(rolesBase.size()>0)
            {
                for(int i=0; i<rolesBase.size(); i++)
                {
                 //System.out.println("Rol Base Id="+rolesBase.get(i)); 
                 Vector vrb = (Vector)rolesBase.get(i);
                 //System.out.println("Rol Base Lineas ="+busquedas.variosFacadeRemote.buscarLineasBasesPorRolBase(Long.valueOf(vrb.get(0).toString())));
                  busquedas.variosFacadeRemote.eliminarLineasBasePorRolBaseId(Long.valueOf(vrb.get(0).toString()));
//                if(!busquedas.variosFacadeRemote.eliminarLineasBasePorRolBaseId(Long.valueOf(vrb.get(0).toString())));
//                 {
//                     new jdlg_error("¡No fue posible eliminar las lineas del Rol Base!","Favor de contactar al Administrador del Sistema","Error al borrrar").setVisible(true);
//                     bien = false;
//                 }
                }
                  busquedas.variosFacadeRemote.eliminarRolesBasePorRolMaestroId(rolmestroseleccionado.getRolMaestroId().longValue());
//                if(!busquedas.variosFacadeRemote.eliminarRolesBasePorRolMaestroId(rolmestroseleccionado.getRolMaestroId().longValue()));
//                {
//                    new jdlg_error("¡No fue posible eliminar los Roles Base!","Favor de contactar al Administrador del Sistema","Error al borrrar").setVisible(true);
//                    bien = false;
//                }
            }
              busquedas.variosFacadeRemote.eliminarrRolMaestroPorId(rolmestroseleccionado.getRolMaestroId().longValue());
//            if(!busquedas.variosFacadeRemote.eliminarrRolMaestroPorId(rolmestroseleccionado.getRolMaestroId().longValue()));
//            {
//                new jdlg_error("¡No fue posibe eliminar el Rol Mestro!","Favor de contactar al Administrador del Sistema","Error al borrrar").setVisible(true);
//                bien = false;
//            }
          
            //if(bien)
                new jdlg_informacion("¡El Rol Maestro se elimino satisfactoriamente!","","Rol Maestro eliminado").setVisible(true);
                jpnl_Roles.setVisible(false);
                jpnl_ofertas.setVisible(false);
                jpnl_fondo.setVisible(true);
                     jLabel1.setEnabled(true);
                     jcmb_servicios.setEnabled(true);
                     jLabel2.setEnabled(true);
                     jcmb_ofertas.setEnabled(true);
                modeloRolesBase.setDataVector(null,encabezadosRolesBase);
                jcmb_rolesMaestro.removeAllItems();
                jcmb_rolesMaestro.addItem("Roles Maestro");
                jcmb_ofertas.requestFocus();
                
          }
        }
        
        if(evt.getKeyCode() == evt.VK_F5){
            rolmestroseleccionado = listaRolesMaestro.get(jcmb_rolesMaestro.getSelectedIndex());//busquedas.rolesMaestroTblFacadeRemote.find(rolma.getRolMaestroId());
            System.out.println("Rol Maestro Seleccionado: "+rolmestroseleccionado.getRolMaestroNombre());
           jdlg_ModificarFechaFinalRolMaestro jdfg = new jdlg_ModificarFechaFinalRolMaestro(rolmestroseleccionado.getFechaInicial().getTime(),rolmestroseleccionado.getFechaFinal().getTime(),rolmestroseleccionado,busquedas, usuarioId); 
           jdfg.setVisible(true);
           if(jdfg.isAceptar())
           {
                jpnl_Roles.setVisible(false);
                jpnl_ofertas.setVisible(false);
                jpnl_fondo.setVisible(true);
                     jLabel1.setEnabled(true);
                     jcmb_servicios.setEnabled(true);
                     jLabel2.setEnabled(true);
                     jcmb_ofertas.setEnabled(true);
                modeloRolesBase.setDataVector(null,encabezadosRolesBase);
                jcmb_rolesMaestro.removeAllItems();
                jcmb_rolesMaestro.addItem("Roles Maestro");
                jcmb_ofertas.requestFocus();
           }
        }
        
        if(evt.getKeyCode() == evt.VK_F7){
            rolmestroseleccionado = listaRolesMaestro.get(jcmb_rolesMaestro.getSelectedIndex());//busquedas.rolesMaestroTblFacadeRemote.find(rolma.getRolMaestroId());
            System.out.println("Rol Maestro Seleccionado: "+rolmestroseleccionado.getRolMaestroNombre());
              Vector exclu = new Vector();
              for(int i=0; i<jtbl_rutas.getRowCount();i++)
              {
                 Boolean rutb =  (Boolean) jtbl_rutas.getValueAt(i,5);
                  if(!rutb)
                      exclu.add(jtbl_rutas.getValueAt(i,7).toString());
                  else
                      vrutes.add(jtbl_rutas.getValueAt(i,7).toString());
              }
                   Timestamp fechaInicial = new Timestamp(rolmestroseleccionado.getFechaInicial().getTime());
                   Timestamp fechaFial = new Timestamp(rolmestroseleccionado.getFechaFinal().getTime());
                    jdlg_RolesManual corridas = new jdlg_RolesManual(busquedas,rolmestroseleccionado.getServicioId().getServicioId().longValue(),rolmestroseleccionado.getFlotillaId(),rolmestroseleccionado.getOfertaServicioNombre(),exclu,usuarioId, rolmestroseleccionado.getRolMaestroNombre(), fechaInicial, fechaFial,rolmestroseleccionado.getOfertaServicioNombre(),rolmestroseleccionado,true,vrutes);
                     if(corridas.getHaycorridas())
                     {
                        jpnl_Roles.setVisible(false);
                        jpnl_ofertas.setVisible(false);
                        jpnl_fondo.setVisible(true);
                             jLabel1.setEnabled(true);
                             jcmb_servicios.setEnabled(true);
                             jLabel2.setEnabled(true);
                             jcmb_ofertas.setEnabled(true);
                        modeloRolesBase.setDataVector(null,encabezadosRolesBase);
                        jcmb_rolesMaestro.removeAllItems();
                        jcmb_rolesMaestro.addItem("Roles Maestro");
                        jcmb_ofertas.requestFocus();
                        corridas.setVisible(true);
                     }
                     else
                     {
                         new jdlg_error("¡No hay horarios para generar un rol!"," Verifique si seleccionó alguna ruta","No hay horarios agregados").setVisible(true);
                         corridas.dispose();
                     }
                if(corridas.isSalida())
                 corridas.salir();
            // this.dispose();
        }
        
        if(evt.getKeyCode() == evt.VK_F8){
                   jpnl_fondo.setVisible(false);
                   jpnl_ofertas.setVisible(true);
                   jpnl_Roles.setVisible(false);
                     jLabel1.setEnabled(false);
                     jcmb_servicios.setEnabled(false);
                     jLabel2.setEnabled(false);
                     jcmb_ofertas.setEnabled(false);
                   jtbl_rutas.setRowSelectionInterval(0,0);
                   jtbl_rutas.setColumnSelectionInterval(0,0);
                   jtbl_rutas.requestFocus();
        }
        
        
        if(evt.getKeyCode() == evt.VK_ESCAPE) {
            jpnl_Roles.setVisible(false);
            jpnl_ofertas.setVisible(false);
            jpnl_fondo.setVisible(true);
                 jLabel1.setEnabled(true);
                 jcmb_servicios.setEnabled(true);
                 jLabel2.setEnabled(true);
                 jcmb_ofertas.setEnabled(true);
            modeloRolesBase.setDataVector(null,encabezadosRolesBase);
            jcmb_rolesMaestro.removeAllItems();
            jcmb_rolesMaestro.addItem("Roles Maestro");
            jcmb_ofertas.requestFocus();
        }
    }//GEN-LAST:event_jcmb_rolesMaestroKeyPressed

    private void jcmb_rolesMaestroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_rolesMaestroFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a Seleccionar Oferta | <font color=FF0000>ABAJO </font> Desplegar Roles Maestro | <font color=FF0000> F4 </font> Eliminar Rol Maestro   | <font color=FF0000> F5 </font> Modificar Fecha Final  | <font color=FF0000> F7 </font> Cargar Rol Maestro  | <font color=FF0000> F8 </font> Nuevo Rol Maestro o Generar corridas</html>");
    }//GEN-LAST:event_jcmb_rolesMaestroFocusGained

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        jcmb_servicios.requestFocusInWindow();
    }//GEN-LAST:event_formComponentShown

    public void setFoco(){
        jcmb_servicios.requestFocusInWindow();
    }

    private void jtbl_rutasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_rutasKeyPressed
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          modeloRutas.setDataVector(null,encabezadosRutas);
          resizeColumnasRutas();
          jpnl_Roles.setVisible(false);
          jpnl_ofertas.setVisible(false);
          jpnl_fondo.setVisible(true);
         jLabel1.setEnabled(true);
         jcmb_servicios.setEnabled(true);
         jLabel2.setEnabled(true);
         jcmb_ofertas.setEnabled(true);
          jcmb_ofertas.requestFocus();
      }
      
      if(evt.getKeyCode() == evt.VK_F2 || evt.getKeyCode() == evt.VK_F9 || evt.getKeyCode() == evt.VK_F6 || evt.getKeyCode() == evt.VK_F7)
         jtbl_rutas.setColumnSelectionInterval(0,0);     
    }//GEN-LAST:event_jtbl_rutasKeyPressed

    private void jtbl_rutasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_rutasKeyReleased
        if(jtbl_rutas.getSelectedColumn()==6 || jtbl_rutas.getSelectedColumn()==7 || jtbl_rutas.getSelectedColumn()==8)
            jtbl_rutas.setColumnSelectionInterval(5,5);
        if(jtbl_rutas.getSelectedColumn()==5)
          //jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> BARRA ESPACIADORA </font> Seleccionar/Deseleccionar Ruta </html>");  
            jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a Seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> BARRA ESPACIADORA </font> Seleccionar/Deseleccionar Ruta | <font color=FF0000> F2 </font> Ver horarios | <font color=FF0000> F6 </font> Generar Rol Manual  | <font color=FF0000> F9 </font> Generar Corridas  </html>"); 
        else
          //jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> IZQUIERDA/DERECHA </font> Desplazarse entre campos</html>");
            jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a Seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> F2 </font> Ver horarios | <font color=FF0000> F6 </font> Generar Rol Manual  | <font color=FF0000> F9 </font> Generar Corridas  </html>"); 

        if(evt.getKeyCode() == evt.VK_F2)
            new jdlg_verHorarios(busquedas,Long.valueOf(jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),6).toString()),Long.valueOf(jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),7).toString()),jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),8).toString()).setVisible(true);

        if(evt.getKeyCode() == evt.VK_F9)
        {
         jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion", "¿Desea generar corridas sin rol?");
          psn.setVisible(true);
          if(respuestaSN)
          {
              Vector exclu = new Vector();
              vrutes = new Vector();
              for(int i=0; i<jtbl_rutas.getRowCount();i++)
              {
                 //Boolean rutb = true;
                 Boolean rutb =  (Boolean)jtbl_rutas.getValueAt(i,5);
                  if(!rutb)
                      exclu.add(jtbl_rutas.getValueAt(i,7).toString());
                  else
                     vrutes.add(jtbl_rutas.getValueAt(i,7).toString()); 
                   
                  
              }
             System.out.println("la rutas antes de mandar a llamar al rol manual son:"+vrutes); 
             jdlg_generarCorridas corridas = new jdlg_generarCorridas(busquedas,Long.valueOf(jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),6).toString()),jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),8).toString(),exclu,usuarioId, jcmb_servicios.getSelectedItem().toString(),vrutes);
             if(corridas.getHaycorridas())
               corridas.setVisible(true);
             else
             {
                 new jdlg_error("¡No hay horarios para generar coridas!"," Verifique si seleccionó alguna ruta","No hay horarios agregados").setVisible(true);
                 corridas.dispose();
             }
             
                
          }
             
          else
            return;  
        }
        
        
        
        if(evt.getKeyCode() == evt.VK_F6)
        {
         jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion", "¿Desea generar un rol manual?");
          psn.setVisible(true);
          if(respuestaSN)
          {
              Vector exclu = new Vector();
              vrutes = new Vector();
              for(int i=0; i<jtbl_rutas.getRowCount();i++)
              {
                 Boolean rutb =  (Boolean) jtbl_rutas.getValueAt(i,5);
                  if(!rutb)
                      exclu.add(jtbl_rutas.getValueAt(i,7).toString());
                  else
                     vrutes.add(jtbl_rutas.getValueAt(i,7).toString()); 
              }
            System.out.println("Las Rutas en VK_F6 son: "+vrutes);
              new jdlg_nombreRolMaestro(busquedas,Long.valueOf(jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),6).toString()),jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),8).toString(),exclu,usuarioId,jcmb_ofertas.getSelectedItem().toString(),vrutes).setVisible(true);
//             jdlg_RolesManual corridas = new jdlg_RolesManual(busquedas,Long.valueOf(jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),6).toString()),jtbl_rutas.getValueAt(jtbl_rutas.getSelectedRow(),8).toString(),exclu,usuarioId);
//             if(corridas.getHaycorridas())
//               corridas.setVisible(true);
//             else
//             {
//                 new jdlg_error("¡No hay horarios para generar un rol!"," Verifique si seleccionó alguna ruta","No hay horarios agregados").setVisible(true);
//                 corridas.dispose();
//             }
             
        
          }
             
          else
            return;  
        }        
        
//        if(evt.getKeyCode() == evt.VK_F7)
//        {
//         jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion", "¿Desea cargar un rol base guardado previamente?");
//          psn.setVisible(true);
//          if(respuestaSN)
//          {
//              Vector exclu = new Vector();
//              for(int i=0; i<jtbl_rutas.getRowCount();i++)
//              {
//                 Boolean rutb =  (Boolean) jtbl_rutas.getValueAt(i,5);
//                  if(!rutb)
//                      exclu.add(jtbl_rutas.getValueAt(i,7).toString());
//              }
//               listaRolesMaestro = busquedas.rolesMaestroTblFacadeRemote.buscaRolMAestroPorNombreOferta(jcmb_ofertas.getSelectedItem().toString());
//
//              if(listaRolesMaestro.size()==0)
//              {
//                   new jdlg_advertencia("¡No hay Roles guardados para esta oferta!","","No existen datos guardados").setVisible(true);
//                   return;
//              }
//               TmsRolesMaestroTbl rolMaestro = null;
//               if(listaRolesMaestro.size()>1)
//               {
//                  JIFAbrirRolMaestro rolesMaestros = new JIFAbrirRolMaestro(listaRolesMaestro);
//                  rolesMaestros.setVisible(true);
//                  rolMaestro = rolesMaestros.getRolmestroseleccionado();
//                  if(!rolesMaestros.isRespuestaSN())
//                      return;
//                 // System.out.println("Rol Maestro Seleccionado de varios: "+rolMaestro.getRolMaestroNombre());
//               }
//               else
//                   rolMaestro = listaRolesMaestro.get(0);
//               Timestamp fechaInicial = new Timestamp(rolMaestro.getFechaInicial().getTime());
//               Timestamp fechaFial = new Timestamp(rolMaestro.getFechaFinal().getTime());
//                jdlg_RolesManual corridas = new jdlg_RolesManual(busquedas,rolMaestro.getServicioId().getServicioId().longValue(),rolMaestro.getFlotillaId(),rolMaestro.getOfertaServicioNombre(),exclu,usuarioId, rolMaestro.getRolMaestroNombre(), fechaInicial, fechaFial,rolMaestro.getOfertaServicioNombre(),rolMaestro,true,vrutes);
//                 if(corridas.getHaycorridas())
//                 {
//                     corridas.setVisible(true);
//                 }
//                 else
//                 {
//                     new jdlg_error("¡No hay horarios para generar un rol!"," Verifique si seleccionó alguna ruta","No hay horarios agregados").setVisible(true);
//                     corridas.dispose();
//                 }
//            if(corridas.isSalida())
//             corridas.salir();
//
//          }
//             
//          else
//            return;  
//        }
        
    }//GEN-LAST:event_jtbl_rutasKeyReleased

    private void jtbl_rutasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_rutasFocusGained
         //jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a Seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> BARRA ESPACIADORA </font> Seleccionar/Deseleccionar Ruta | <font color=FF0000> F2 </font> Ver horarios | <font color=FF0000> F7 </font> Generar Rol Automatico | <font color=FF0000> F9 </font> Generar Rol Manual  | <font color=FF0000> F11 </font> Generar Corridas  </html>"); 
        jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a Seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> F2 </font> Ver horarios | <font color=FF0000> F6 </font> Generar Rol Manual  | <font color=FF0000> F9 </font> Generar Corridas  </html>"); 
    }//GEN-LAST:event_jtbl_rutasFocusGained

    
      private void resizeColumnasRutas(){
        TableColumn columinv = jtbl_rutas.getColumnModel().getColumn(0); columinv.setMinWidth( 80 );columinv.setMaxWidth( 80 );columinv.setPreferredWidth(80);
        columinv = jtbl_rutas.getColumnModel().getColumn(1); columinv.setMinWidth(220);columinv.setMaxWidth(220);columinv.setPreferredWidth(220);
        columinv = jtbl_rutas.getColumnModel().getColumn(2); columinv.setMinWidth(120);columinv.setMaxWidth(120);columinv.setPreferredWidth(120);
        columinv = jtbl_rutas.getColumnModel().getColumn(3); columinv.setMinWidth(120);columinv.setMaxWidth(120);columinv.setPreferredWidth(120);
        columinv = jtbl_rutas.getColumnModel().getColumn(4); columinv.setMinWidth(120);columinv.setMaxWidth(120);columinv.setPreferredWidth(120);
        columinv = jtbl_rutas.getColumnModel().getColumn(5); columinv.setMinWidth(70);columinv.setMaxWidth(70);columinv.setPreferredWidth(70);
        columinv = jtbl_rutas.getColumnModel().getColumn(6); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
        columinv = jtbl_rutas.getColumnModel().getColumn(7);columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);        
        columinv = jtbl_rutas.getColumnModel().getColumn(8);columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);        
        JHourTextField trecorrido = new JHourTextField();
        JHourTextField testancia = new JHourTextField();
        JHourTextField ttraslado = new JHourTextField();
        TableColumn tiemposColumn = jtbl_rutas.getColumnModel().getColumn(2);
        tiemposColumn.setCellEditor(new DefaultCellEditor(trecorrido));
        tiemposColumn = jtbl_rutas.getColumnModel().getColumn(3);
        tiemposColumn.setCellEditor(new DefaultCellEditor(testancia));        
        tiemposColumn = jtbl_rutas.getColumnModel().getColumn(4);
        tiemposColumn.setCellEditor(new DefaultCellEditor(ttraslado));        
        }
    
    
    
    private void jcmb_ofertasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_ofertasKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER){
             nombreOferta = jcmb_ofertas.getSelectedItem().toString();
             Vector listaRutas = (Vector)busquedas.variosFacadeRemote.buscarRutasPorOfertaServicio(servicioId,nombreOferta);
             Object[][] datos = new Object[listaRutas.size()][9];
             boolean horarios = false;
             for(int i=0; i<listaRutas.size(); i++){
                 Vector ruta = (Vector) listaRutas.get(i);
                 datos[i][0] = ruta.get(2);
                 datos[i][1] = ruta.get(3);
                 datos[i][2] = minutosHoras(ruta.get(4).toString());
                 datos[i][3] = minutosHoras(ruta.get(5).toString());
                 datos[i][4] = "";
                 datos[i][5] = true;
                 datos[i][6] = servicioId;
                 datos[i][7] = ruta.get(1);
                 datos[i][8] = nombreOferta;
                 horarios = true;
             }
             if(horarios)
             {
                 modeloRutas.setDataVector(datos,encabezadosRutas);
                 resizeColumnasRutas();
                 //jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a Seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> BARRA ESPACIADORA </font> Seleccionar/Deseleccionar Ruta | <font color=FF0000> F2 </font> Ver horarios | <font color=FF0000> F7 </font> Generar Rol Automatico | <font color=FF0000> F9 </font> Generar Rol Manual  | <font color=FF0000> F11 </font> Generar Corridas  </html>");                  
                 jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Regresar a Seleccionar Oferta | <font color=FF0000>ARRIBA/ABAJO </font> Desplazarse entre rutas | <font color=FF0000> F2 </font> Ver horarios | <font color=FF0000> F6 </font> Generar Rol Manual  | <font color=FF0000> F9 </font> Generar Corridas  </html>"); 
                 jLabel1.setEnabled(false);
                 jcmb_servicios.setEnabled(false);
                 jLabel2.setEnabled(false);
                 jcmb_ofertas.setEnabled(false);
                 jtbl_rutas.setRowSelectionInterval(0,0);
                 jtbl_rutas.setColumnSelectionInterval(0,0);
                 jtbl_rutas.requestFocus();
             }
             else
             {
                 modeloRutas.setDataVector(null,encabezadosRutas);
                 jcmb_ofertas.requestFocus();
             }  
               listaRolesMaestro = busquedas.rolesMaestroTblFacadeRemote.buscaRolMAestroPorNombreOferta(jcmb_ofertas.getSelectedItem().toString());

              if(listaRolesMaestro.size()==0)
              {
                   //new jdlg_advertencia("¡No hay Roles guardados para esta oferta!","","No existen datos guardados").setVisible(true);
                   //return;
                   jpnl_fondo.setVisible(false);
                   jpnl_ofertas.setVisible(true);
                   jpnl_Roles.setVisible(false);
                     jLabel1.setEnabled(false);
                     jcmb_servicios.setEnabled(false);
                     jLabel2.setEnabled(false);
                     jcmb_ofertas.setEnabled(false);
                   jtbl_rutas.setRowSelectionInterval(0,0);
                   jtbl_rutas.setColumnSelectionInterval(0,0);
                   jtbl_rutas.requestFocus();
              }
              else
              {    
                   TmsRolesMaestroTbl rolMaestro = null;
//                   if(listaRolesMaestro.size()>1)
//                   {
//                      JIFAbrirRolMaestro rolesMaestros = new JIFAbrirRolMaestro(listaRolesMaestro);
//                      rolesMaestros.setVisible(true);
//                      rolMaestro = rolesMaestros.getRolmestroseleccionado();
//                      if(!rolesMaestros.isRespuestaSN())
//                          return;
//                     // System.out.println("Rol Maestro Seleccionado de varios: "+rolMaestro.getRolMaestroNombre());
//                   }
//                   else
//                       rolMaestro = listaRolesMaestro.get(0);

                   jcmb_rolesMaestro.removeAllItems();
                    for(int i=0; i<listaRolesMaestro.size();i++)
                        jcmb_rolesMaestro.addItem(listaRolesMaestro.get(i).getRolMaestroNombre());
                   jpnl_fondo.setVisible(false);
                   jpnl_ofertas.setVisible(false);
                   jpnl_Roles.setVisible(true);
                   jlbl_fechaInicial.setText("");
                   jlbl_fechaFinal.setText("");
                     jLabel1.setEnabled(false);
                     jcmb_servicios.setEnabled(false);
                     jLabel2.setEnabled(false);
                     jcmb_ofertas.setEnabled(false);
                   jcmb_rolesMaestro.requestFocus();
              }
         }
        
         if(evt.getKeyCode() == evt.VK_LEFT)
         {
             jcmb_ofertas.removeAllItems();
             jcmb_ofertas.addItem("Ofertas");
             jcmb_servicios.requestFocus();
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
         
         if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();         
    }//GEN-LAST:event_jcmb_ofertasKeyPressed

    private void jcmb_serviciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_serviciosKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
            int index = jcmb_servicios.getSelectedIndex();
            TmsServiciosTbl serviciosSeleccionado = listaServicios.get(index);
            System.out.println("El servicios seleccionado es: "+serviciosSeleccionado.getServicioNombre()+" Index = "+jcmb_servicios.getSelectedIndex()+" "+jcmb_servicios.getSelectedItem().toString());
            servicioId = serviciosSeleccionado.getServicioId().longValue();
            Vector lofertas = (Vector)busquedas.variosFacadeRemote.buscarOfertasPorServicioId(serviciosSeleccionado.getServicioId().longValue());
            if(lofertas.size()==0)
            {
                new jdlg_advertencia("¡No existen ofertas para el servicio seleccionado!","","No hay ofertas seleccionadas").setVisible(true);
                return;
            }
            else{
                jcmb_ofertas.removeAllItems();
                for(int i=0; i<lofertas.size(); i++)
                {
                    Vector oferta = (Vector)lofertas.get(i);
                    jcmb_ofertas.addItem(oferta.get(0).toString());
                }
                jcmb_ofertas.requestFocus();
            }
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
        
        if(evt.getKeyCode() == evt.VK_F4)
            salirAplicacion();
    }//GEN-LAST:event_jcmb_serviciosKeyPressed

    private void jcmb_ofertasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_ofertasFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000> F4 </font> Salir | <font color=FF0000>ABAJO </font> Desplegar Ofertas de Servicio | <font color=FF0000> ENTER </font> Seleccionar Oferta de Servicio | <font color=FF0000> IZQUIERDA </font> Seleccionar Otro Servicio | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>"); 
    }//GEN-LAST:event_jcmb_ofertasFocusGained

    private void jcmb_serviciosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_serviciosFocusGained
        jlbl_barraEstado.setText("<html>  <font color=FF0000> F4 </font> Salir |  <font color=FF0000>ABAJO </font> Desplegar Servicios | <font color=FF0000> ENTER </font> Seleccionar Servicio | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
    }//GEN-LAST:event_jcmb_serviciosFocusGained
    
    private void salirAplicacion(){
       jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de salida", "¿Seguro que desea salir de Generacion de Corridas?");
          psn.setVisible(true);
          if(respuestaSN)
              this.dispose();
          else
            return;  
    }
    
    private long horaMinutos(String hora){
       long minutos =0;
       StringTokenizer tknz = new  StringTokenizer(hora,":");
       long h = Long.valueOf(tknz.nextToken());
       long m = Long.valueOf(tknz.nextToken());
       minutos = (h*60) + m;
       return minutos;
    }
    
    private String minutosHoras(String minutos){
        int min = Integer.valueOf(minutos);
        String hora = "";
        int h = min/60;
        float m = min%60;
        Float mf = Float.valueOf(m);
        if(h<10)    
            hora = "0"+h+":";
        else
            hora = h+":";
        if(m<10)
            hora = hora +"0"+mf.intValue();
        else
            hora = hora + mf.intValue();
        return hora;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneroles;
    private javax.swing.JComboBox jcmb_ofertas;
    private javax.swing.JComboBox jcmb_rolesMaestro;
    private javax.swing.JComboBox jcmb_servicios;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_fechaFinal;
    private javax.swing.JLabel jlbl_fechaInicial;
    private javax.swing.JPanel jpnl_Roles;
    private javax.swing.JPanel jpnl_fondo;
    private javax.swing.JPanel jpnl_ofertas;
    private javax.swing.JTable jtbl_rolesBase;
    private javax.swing.JTable jtbl_rutas;
    // End of variables declaration//GEN-END:variables
    private TmsRolesManagedBean busquedas = new TmsRolesManagedBean();
    List<TmsServiciosTbl> listaServicios;
    private Object[] encabezadosRutas = {"No Ruta","Nombre Ruta","Tiempo Recorrido","Tiempo Estancia","Tiempo Traslado","Agregar","servicio_id","Ruta_id","Nombre_Oferta"};
    private DefaultTableModel modeloRutas = new DefaultTableModel(null,encabezadosRutas){
            public boolean isCellEditable(int row, int column) {
            if (column == 0 ||column == 1 )
               return false;
            return true;
            }
            public Class getColumnClass(int c) { 
               return getValueAt(0, c).getClass(); }
        };
    private Object[] encabezadosRolesBase = {"Categoria","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo","No Autobuses"};
    private DefaultTableModel modeloRolesBase = new DefaultTableModel(null,encabezadosRolesBase){
            public boolean isCellEditable(int row, int column) {
            if (column == 0 ||column == 1 )
               return false;
            return true;
            }
            public Class getColumnClass(int c) { 
               return getValueAt(0, c).getClass(); }
        };
    private boolean respuestaSN;
    private Timestamp fecha_servidor= null;     
    private long usuarioId;
    private long sesionId;
    private long servicioId=0;
    private String nombreOferta="";
    private Vector vrutes = new Vector();
    private List<TmsRolesMaestroTbl> listaRolesMaestro = null;
    private TmsRolesMaestroTbl rolmestroseleccionado;
        
        
        
        
class jdlg_pregunta_SN extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlg_pregunta_SN
     */
    public jdlg_pregunta_SN(String titulo, String pregunta){
        this.setTitle(titulo);
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
        this.setResizable(false);
        jlbl_mensaje.setText(pregunta);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ENTER: </font> Si                 <font color=FF0000>             ESC: </font> No  </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        int nletras = pregunta.length();
        this.setSize((nletras * 6) + 80,150);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
            //this.setUndecorated(true);
            this.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
            jlbl_mensaje.requestFocus();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();
        jlbl_mensaje = new javax.swing.JLabel();
        jlbl_mensaje.setFocusTraversalKeysEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\NetBeansProyects\\TMSRecaudacion\\TMSRecaudacion-app-client\\src\\java\\tmsrecaudacion\\images\\pregunta.gif"));

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setText(" ENTER: Si                ESC: No");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jlbl_mensaje.setFont(new java.awt.Font("Arial", 1, 12));
        jlbl_mensaje.setText("sdfsdsdfsdfsdfsdfsdf");
        jlbl_mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlbl_mensajeKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(14, 14, 14)
                .add(jlbl_mensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .add(424, 424, 424))
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_mensaje))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 59, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>                           

    private void jlbl_mensajeKeyReleased(java.awt.event.KeyEvent evt) {                                        
      if(evt.getKeyCode() == evt.VK_ESCAPE)
      {
          respuestaSN = false;
          this.dispose();
      }
      if(evt.getKeyCode() == evt.VK_ENTER)
      {
          respuestaSN = true;
          this.dispose();
      }
      
    }                                       
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_mensaje;
    
    // End of variables declaration                   
    
}                  

    private KeyEvent eventoTeclado;

    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
}
