/*
 * jdlg_verHorarios.java
 *
 * Created on 7 de noviembre de 2007, 09:02 PM
 */

package tmsroles;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tmsroles.entidad.TmsCorridasTbl;
import tmsroles.entidad.TmsCorridasVentaTbl;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_generarCorridas extends javax.swing.JDialog { 
    
    /** Creates new form jdlg_verHorarios */
    public jdlg_generarCorridas(TmsRolesManagedBean pbusquedas,long idSer,  String oferta, Vector pexcluir, long pusuarioId, String pservnom, Vector pvrutes){
        this.setTitle("Horarios de la Oferta para generar Corridas");
        this.busquedas = pbusquedas;
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.setResizable(false);
        this.idrservicio = idSer;
        this.vrutes = pvrutes;
        this.servicioNombre = pservnom;
        this.excluir = pexcluir;
        this.usuarioId = pusuarioId;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
        jtxt_fechainicial.setFocusTraversalKeysEnabled(false);
        jtxt_fechafinal.setFocusTraversalKeysEnabled(false);
        jtbl_horarios.setFocusTraversalKeysEnabled(false);
        jtbl_horarios.setModel(modeloHorarios);
        jtbl_rutas.setFocusTraversalKeysEnabled(false);
        jtbl_rutas.setBackground(new java.awt.Color(238, 238, 239));
        jtbl_rutas.setGridColor(new java.awt.Color(238, 238, 239));
        jtbl_rutas.setCellSelectionEnabled(false);
        jtbl_rutas.setModel(modeloRutas);
        vHorarios = (Vector)busquedas.variosFacadeRemote.buscaHorariosDeLaOfertadeServicios(idSer,oferta);
        Vector vhorariosSolos = new Vector();
        for(int i=0; i<vHorarios.size();i++){
           Object[] unHorario = new Object[23];
            Vector  horario = (Vector)vHorarios.get(i);
            boolean ex = false;
            for(int j=0; j<excluir.size(); j++)
            {
              if(horario.get(11).toString().equals(excluir.get(j).toString()))
              {
                  ex = true;
                  break;
              }
                  
            }
            if(!ex)
            {
                unHorario[0] = horario.get(0).toString();
                unHorario[1] = horario.get(1).toString();
                unHorario[2] = horario.get(2).toString();
                unHorario[3] = horario.get(3).toString();
                if(horario.get(4).toString().equals("S"))  unHorario[4] = true; else unHorario[4] = false;
                if(horario.get(5).toString().equals("S"))  unHorario[5] = true; else unHorario[5] = false;
                if(horario.get(6).toString().equals("S"))  unHorario[6] = true; else unHorario[6] = false;
                if(horario.get(7).toString().equals("S"))  unHorario[7] = true; else unHorario[7] = false;
                if(horario.get(8).toString().equals("S"))  unHorario[8] = true; else unHorario[8] = false;
                if(horario.get(9).toString().equals("S"))  unHorario[9] = true; else unHorario[9] = false;
                if(horario.get(10).toString().equals("S")) unHorario[10] = true; else unHorario[10] = false;
                unHorario[11] = horario.get(11).toString();
                unHorario[12] = horario.get(12).toString();
                unHorario[13] = horario.get(13).toString();
                unHorario[14] = horario.get(14).toString();
                unHorario[15] = horario.get(15).toString();
                unHorario[16] = horario.get(16).toString();
                unHorario[17] = horario.get(17).toString();
                unHorario[18] = horario.get(18).toString();
                unHorario[19] = horario.get(19).toString();
                unHorario[20] = horario.get(20).toString();
                unHorario[21] = horario.get(21).toString();
                unHorario[22] = horario.get(22).toString();      
                vhorariosSolos.add(unHorario);
                haycorridas = true;
            }
        }

        
        if(haycorridas)
        {
            Vector TER = (Vector)busquedas.variosFacadeRemote.queryBuscaNombreTerminal();
            nombreTerminal = TER.get(0).toString();
            
            Vector TER2 = (Vector)busquedas.variosFacadeRemote.queryBuscaIdTerminal();
            idTerminal = TER2.get(0).toString();
            String te = idTerminal;
            if(idTerminal.length()<3)
            {
               for(int i=idTerminal.length(); i<3;i++)
                   te = te+"0";
            }
            if(idTerminal.length()>=3)
             te = idTerminal.substring(0,2);
                
            idTerminal = te;
            
            Vector vest =(Vector) busquedas.variosFacadeRemote.queryBuscaIdEstadoCorriaAbierta();
            if(vest.size()==0)
            {
                new jdlg_error("¡Los estados de la corrida no estan configurados! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
                haycorridas = false;
            }
            else
            {
                Vector edo = (Vector)vest.get(0);
                estadoAbiertaId = Long.valueOf(edo.get(0).toString());
            }
            Vector vestc =(Vector) busquedas.variosFacadeRemote.queryBuscaIdEstadoCorridaCerrada();
            if(vestc.size()==0)
            {
                new jdlg_error("¡Los estados de la corrida no estan configurados! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
                haycorridas = false;
            }
            else
            {
                Vector edoc = (Vector)vestc.get(0);
                estadoCerradaId = Long.valueOf(edoc.get(0).toString());
            }
            
            /*System.out.println("El servicio es: "+servicioNombre);
            Vector vplan = (Vector)busquedas.variosFacadeRemote.buscaPlantllaefaultPorServicio(servicioNombre);
            if(vplan.size()==0)
            {
                new jdlg_error("¡La plantilla por defecto del servicio no esta configurada! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
                haycorridas = false;
            }
            else
            {
                Vector plan = (Vector)vplan.get(0);
                plantillaDefaultId = Long.valueOf(plan.get(0).toString());
            }*/
            System.out.println("El servicio es: "+servicioNombre);
            System.out.println("el id de las Rutas son : "+vrutes+ " ==> "+vrutes.toString());
            rutcadena = vrutes.toString();
            rutcadena = rutcadena.replace('[','(');
            rutcadena = rutcadena.replace(']',')');
            rutcadena = rutcadena.trim();
            Vector infoRutas = (Vector)busquedas.variosFacadeRemote.buscarDatosRutas(rutcadena);
            Object[][] datosInfo = new Object[infoRutas.size()][5];
            for(int i=0; i<infoRutas.size();i++)
            {
                Vector unaInfo= (Vector)infoRutas.get(i);
                datosInfo[i][0] = unaInfo.get(0).toString();
                datosInfo[i][1] = unaInfo.get(1).toString();
                datosInfo[i][2] = unaInfo.get(2).toString();
                datosInfo[i][3] = (boolean)true;
            }
            modeloRutas.setDataVector(datosInfo,encabezadosRutas);
            System.out.println("Encabezado rutas: "+encabezadosRutas);
            System.out.println("Info rutas: "+infoRutas);
            Vector vplan = (Vector)busquedas.variosFacadeRemote.buscaPlantllasDefaultPorRuta();
            if(vplan.size()==0)
            {
                new jdlg_error("¡La plantilla por defecto de las rutas no esta configurada! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
                haycorridas = false;
            }
            else
            {
                for(int i=0; i<vplan.size(); i++)
                {
                    Vector vr = (Vector) vplan.get(i);
                    rutasId.add(vr.get(0).toString());
                    plantillaRuta.add(vr.get(1).toString());
                }
                for(int j=0; j<vrutes.size(); j++)
                {
                    if(rutasId.indexOf(vrutes.get(j).toString())==-1)
                        haycorridas = false;
                }
                    
                if(!haycorridas)
                   new jdlg_error("¡La plantilla por defecto de las rutas no esta configurada! "," Favor de contactar al Administrador del Sitema","Error de configuracion").setVisible(true);
           }
            
            Object[][] datos = new Object[vhorariosSolos.size()][23];
            for(int i=0; i<vhorariosSolos.size();i++){
               Object[] unHorario = new Object[23];
               unHorario = (Object[])vhorariosSolos.get(i);
               datos[i][0] = unHorario[0];
               datos[i][1] = unHorario[1];
               datos[i][2] = unHorario[2];
               datos[i][3] = unHorario[3];
               datos[i][4] = unHorario[4];
               datos[i][5] = unHorario[5];
               datos[i][6] = unHorario[6];
               datos[i][7] = unHorario[7];
               datos[i][8] = unHorario[8];
               datos[i][9] = unHorario[9];
               datos[i][10] = unHorario[10];
               datos[i][11] = unHorario[11];
               datos[i][12] = unHorario[12];
               datos[i][13] = unHorario[13];
               datos[i][14] = unHorario[14];
               datos[i][15] = unHorario[15];
               datos[i][16] = unHorario[16];
               datos[i][17] = unHorario[17];
               datos[i][18] = unHorario[18];
               datos[i][19] = unHorario[19];
               datos[i][20] = unHorario[20];
               datos[i][21] = unHorario[21];
               datos[i][22] = unHorario[22];
            }    
            
            modeloHorarios.setDataVector(datos, encabezadosHorarios);
            resizeColumnasRutas();
            resizeColumnas();
            jtbl_horarios.setColumnSelectionInterval(0,0);
            jtbl_horarios.setRowSelectionInterval(0,0);
            jtbl_horarios.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
            jtxt_fechafinal.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
            jtxt_fechainicial.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
            jtbl_horarios.requestFocus();
        }
    }
    
    
    
      private void resizeColumnasRutas(){
        TableColumn columinv = jtbl_horarios.getColumnModel().getColumn(0); columinv.setMinWidth( 70 );columinv.setMaxWidth( 70 );columinv.setPreferredWidth(70);
        columinv = jtbl_horarios.getColumnModel().getColumn(1); columinv.setMinWidth(120);columinv.setMaxWidth(120);columinv.setPreferredWidth(120);
        columinv = jtbl_horarios.getColumnModel().getColumn(2); columinv.setMinWidth(120);columinv.setMaxWidth(120);columinv.setPreferredWidth(120);
        columinv = jtbl_horarios.getColumnModel().getColumn(3); columinv.setMinWidth(143);columinv.setMaxWidth(143);columinv.setPreferredWidth(143);
        columinv = jtbl_horarios.getColumnModel().getColumn(4); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(5); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(6); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(7); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(8); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(9); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(10); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(11); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(12); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(13); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(14); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(15); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(16); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(17); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);

        columinv = jtbl_horarios.getColumnModel().getColumn(18); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(19); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(20); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(21); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        columinv = jtbl_horarios.getColumnModel().getColumn(22); columinv.setMinWidth(0);columinv.setMaxWidth(0);columinv.setPreferredWidth(0);
        
      }    
      private void resizeColumnas(){
        TableColumn columinv = jtbl_rutas.getColumnModel().getColumn(0); columinv.setMinWidth( 0 );columinv.setMaxWidth( 0 );columinv.setPreferredWidth(0);
        columinv = jtbl_rutas.getColumnModel().getColumn(1); columinv.setMinWidth(90);columinv.setMaxWidth(90);columinv.setPreferredWidth(90);
        columinv = jtbl_rutas.getColumnModel().getColumn(2); columinv.setMinWidth(190);columinv.setMaxWidth(190);columinv.setPreferredWidth(190);
        columinv = jtbl_rutas.getColumnModel().getColumn(3); columinv.setMinWidth(60);columinv.setMaxWidth(60);columinv.setPreferredWidth(60);
      }      
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_horarios = new javax.swing.JTable();
        jlbl_barraEstado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxt_fechainicial = new tms_TextFields.JDateTextField();
        jtxt_fechafinal = new tms_TextFields.JDateTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbl_rutas = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jtbl_horarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbl_horarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_horariosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtbl_horariosFocusLost(evt);
            }
        });
        jtbl_horarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_horariosKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_horarios);

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rango de fechas para generar corridas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Fecha inicial:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Fecha Final:");

        jtxt_fechainicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechainicialKeyPressed(evt);
            }
        });

        jtxt_fechafinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechafinalKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Rutas");

        jtbl_rutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jtbl_rutas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_rutasKeyPressed(evt);
            }
        });

        jScrollPane2.setViewportView(jtbl_rutas);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(13, 13, 13)
                        .add(jtxt_fechainicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(17, 17, 17)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtxt_fechafinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(24, 24, 24)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(129, 129, 129))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(42, 42, 42)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .add(jtxt_fechainicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jtxt_fechafinal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(169, 169, 169))
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(94, 94, 94))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
            .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 329, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 167, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbl_rutasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_rutasKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER  || evt.getKeyCode() == evt.VK_RIGHT) {
            String campoFecha = jtxt_fechainicial.getText();
            if(campoFecha.length()==10) {
                jtxt_fechafinal.selectAll();
                jtxt_fechafinal.requestFocus();
            } else {
                new jdlg_error("¡La fecha es invalida! ","Favor de introducir una fecha valida","Fecha Invalida").setVisible(true);
                jlbl_barraEstado.setText("<html>  <font color=FF0000>ESCAPE</font> Cerrar |  <font color=FF0000> « »  </font> Moverse entre Campos | <font color=FF0000> F10 </font>  Generar Corridas | <font color=FF0000> F2 </font> ir tabla de horarios</html>");
                jtxt_fechainicial.setText("");
                jtxt_fechainicial.requestFocus();
            }
        }

        if(evt.getKeyCode() == evt.VK_F2) {
            jtbl_horarios.requestFocus();
            jtbl_horarios.setRowSelectionInterval(0,0);
            jtbl_horarios.setColumnSelectionInterval(0,0);
        }
       
        if(evt.getKeyCode() ==  evt.VK_LEFT)
        {
            jtxt_fechafinal.selectAll();
            jtxt_fechafinal.requestFocus();
        }

        if(evt.getKeyCode() ==  evt.VK_LEFT)
        {
            jtxt_fechainicial.selectAll();
            jtxt_fechainicial.requestFocus();
        }
        
        if(evt.getKeyCode() == evt.VK_F10)
            generarCorridas();
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    
    }//GEN-LAST:event_jtbl_rutasKeyPressed

    private void jtxt_fechainicialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_fechainicialKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER  || evt.getKeyCode() == evt.VK_RIGHT) {
            String campoFecha = jtxt_fechainicial.getText();
            if(campoFecha.length()==10) {
                jtxt_fechafinal.selectAll();
                jtxt_fechafinal.requestFocus();
            } else {
                new jdlg_error("¡La fecha es invalida! ","Favor de introducir una fecha valida","Fecha Invalida").setVisible(true);
                jlbl_barraEstado.setText("<html>  <font color=FF0000>ESCAPE</font> Cerrar |  <font color=FF0000> « »  </font> Moverse entre Campos | <font color=FF0000> F10 </font>  Generar Corridas | <font color=FF0000> F2 </font> ir tabla de horarios</html>");
                jtxt_fechainicial.setText("");
                jtxt_fechainicial.requestFocus();
            }
        }

        if(evt.getKeyCode() ==  evt.VK_LEFT)
        {
            jtbl_rutas.requestFocus();
            jtbl_rutas.setRowSelectionInterval(0,0);
            jtbl_rutas.setColumnSelectionInterval(3,3);
        }   
        
        
        if(evt.getKeyCode() == evt.VK_F2) {
            jtbl_horarios.requestFocus();
            jtbl_horarios.setRowSelectionInterval(0,0);
            jtbl_horarios.setColumnSelectionInterval(0,0);
        }
        
        if(evt.getKeyCode() == evt.VK_F10)
            generarCorridas();
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }//GEN-LAST:event_jtxt_fechainicialKeyPressed

    private void jtxt_fechafinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_fechafinalKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER || evt.getKeyCode() == evt.VK_LEFT ) {
            String campoFecha = jtxt_fechafinal.getText();
            if(campoFecha.length()==10) {
                jtxt_fechainicial.selectAll();
                jtxt_fechainicial.requestFocus();
            } else {
                new jdlg_error("¡La fecha es invalida! ","Favor de introducir una fecha valida","Fecha Invalida").setVisible(true);
                jlbl_barraEstado.setText("<html>  <font color=FF0000>ESCAPE</font> Cerrar |  <font color=FF0000> « »  </font> Moverse entre Campos | <font color=FF0000> F10 </font>  Generar Corridas | <font color=FF0000> F2 </font> ir tabla de horarios</html>");
                jtxt_fechafinal.setText("");
                jtxt_fechafinal.requestFocus();
            }
        }
        
        if(evt.getKeyCode() ==  evt.VK_RIGHT)
        {
            jtbl_rutas.requestFocus();
            jtbl_rutas.setRowSelectionInterval(0,0);
            jtbl_rutas.setColumnSelectionInterval(3,3);
        }   
        
        if(evt.getKeyCode() == evt.VK_F2) {
            jtbl_horarios.requestFocus();
            jtbl_horarios.setRowSelectionInterval(0,0);
            jtbl_horarios.setColumnSelectionInterval(0,0);
        }
        
        
        if(evt.getKeyCode() == evt.VK_F10)
            generarCorridas();
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }//GEN-LAST:event_jtxt_fechafinalKeyPressed

    private void jtbl_horariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_horariosFocusGained
        jlbl_barraEstado.setText("<html> <font color=FF0000> ESC </font> Cerrar | <font color=FF0000> F2 </font> Ingresar Fechas | <font color=FF0000> F10 </font> Genrar Corridas </html>");          
    }//GEN-LAST:event_jtbl_horariosFocusGained

    private void jtbl_horariosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_horariosFocusLost
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESCAPE</font> Cerrar |  <font color=FF0000> « »  </font> Moverse entre Campos | <font color=FF0000> F10 </font>  Generar Corridas | <font color=FF0000> F2 </font> ir tabla de horarios</html>");     
    }//GEN-LAST:event_jtbl_horariosFocusLost

    
    private void jtbl_horariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_horariosKeyPressed
        if(evt.getKeyCode() == evt.VK_F10)
         generarCorridas();
        
        if(evt.getKeyCode() == evt.VK_F2)
        {
            jtxt_fechainicial.requestFocus();
            jtxt_fechainicial.selectAll();
        }
            
            
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }//GEN-LAST:event_jtbl_horariosKeyPressed

    
    private void generarCorridas() {
        System.out.println("Fecha inicial: "+jtxt_fechainicial.getText());
        System.out.println("Fecha Final: "+jtxt_fechafinal.getText());
        if(jtxt_fechafinal.getText().length()<10 || jtxt_fechainicial.getText().length()<10)
           new jdlg_error("¡Debes introducir fechas validas!","","Error de formato de fecha").setVisible(true);   
          else
          {
            Vector idRutasInfo = new Vector();
            for(int i=0; i<jtbl_rutas.getRowCount(); i++)
                if(jtbl_rutas.getValueAt(i,3).toString().equals("true"))
                 idRutasInfo.add(jtbl_rutas.getValueAt(i,0).toString());
            System.out.println("Rutas Abiertas: "+idRutasInfo);
            salidastotal=1;
            Vector x = (Vector) busquedas.variosFacadeRemote.fechaServidor();
            fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
           String sfcorridasi = jtxt_fechainicial.getText();
           String sfcorridasf = jtxt_fechafinal.getText();
           StringTokenizer tokenfi = new StringTokenizer(sfcorridasi,"/");  
           String di= tokenfi.nextToken();
           String mi= tokenfi.nextToken(); 
           String ai= tokenfi.nextToken();        
           StringTokenizer tokenff = new StringTokenizer(sfcorridasf,"/");  
           String df= tokenff.nextToken();
           String mf= tokenff.nextToken(); 
           String af= tokenff.nextToken();        
           Timestamp fcorridasi = null;
           fcorridasi = fcorridasi.valueOf(ai+"-"+mi+"-"+di+" 00:00:00");
           Timestamp fcorridasf = null;
           fcorridasf = fcorridasf.valueOf(af+"-"+mf+"-"+df+" 00:00:00");
           String sfhoy = formatf.format(fecha_servidor.getTime());
           StringTokenizer tokenfh = new StringTokenizer(sfhoy,"/");  
           String dh= tokenfh.nextToken();
           String mh= tokenfh.nextToken(); 
           String ah= tokenfh.nextToken();    
           Timestamp fh = null;
           fh = fh.valueOf(ah+"-"+mh+"-"+dh+" 00:00:00");
           if(fcorridasi.getTime() < fh.getTime())
            new jdlg_error("¡La fecha inicial de generación de corridas debe ser mayor ","o igual a la fecha actual!","Error de fechas").setVisible(true);   
           else
           {
            if(fcorridasf.getTime() <  fcorridasi.getTime())
             new jdlg_error("¡La fecha final de generacion de corridas debe ser ","mayor o igual a la fecha de inicio de generación de corridas","Error de formato de fecha").setVisible(true);   
            else
            {
             jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmación de generar corridas", "¿Esta seguro que quiere generar corridas?");
              psn.setVisible(true);
              if(respuestaSN)
              {
        ////////  Borra las corridas que ya existan en la base de datos entre las fechas >=sfcorridasi <= sfcorridasf 

            List<TmsCorridasTbl> lcorrinbor = new ArrayList<TmsCorridasTbl>();
             Vector indicecorrinbor = new Vector();
             String coris = "";
             String cdes = "";
             String cemp = "";
             for(int i=0; i<jtbl_horarios.getRowCount(); i++)
             {
               String tor = jtbl_horarios.getValueAt(i,1).toString();
               String tde = jtbl_horarios.getValueAt(i,2).toString();
               String temp = jtbl_horarios.getValueAt(i,3).toString();
               if(coris.indexOf(tor)==-1)
               {
                   if(coris.equals(""))
                       coris="'"+tor+"'";
                   else
                       coris=coris+",'"+tor+"'";
               }
               if(cdes.indexOf(tde)==-1)
               {
                   if(cdes.equals(""))
                       cdes="'"+tde+"'";
                   else
                       cdes=cdes+",'"+tde+"'";
               }
               if(cemp.indexOf(temp)==-1)
               {
                   if(cemp.equals(""))
                       cemp="'"+temp+"'";
                   else
                       cemp=cemp+",'"+temp+"'";
               }
             }
             System.out.println("Cadena de Origenes: "+coris);
             System.out.println("Cadena de Destinos: "+cdes);
             System.out.println("Cadena de Empresas: "+cemp);
             System.out.println("Cadena de Rutas: "+rutcadena);
             Vector lcexbd = (Vector)busquedas.variosFacadeRemote.queryBuscaCR(sfcorridasi,sfcorridasf,idrservicio, coris, cdes, cemp, rutcadena);  //queryTmsCorridasTblFindRangoFechas(fcorridasi,fcorridasf);
             System.out.println("Entre "+sfcorridasi+" y "+sfcorridasf+" existen "+ lcexbd.size()+" corridas en la BD");       
             jProgressBar1.setValue( 0 );
             jProgressBar1.setMinimum( 0 );
             if(lcexbd.size()>0)
             {
              //if(lcexbd.size()>0)
                jProgressBar1.setMaximum(lcexbd.size() - 1);
             //else
               //  jProgressBar1.setMaximum(0);
             for(int i=0; i<lcexbd.size(); i++)
             {
               BigDecimal ic =(BigDecimal)((Vector)(lcexbd.get(i))).get(0);
               long idcorrida = Long.valueOf(ic.toString());
               List<TmsCorridasTbl> lcid= busquedas.variosFacadeRemote.queryTmsCorridasTblFindById(ic);
               TmsCorridasTbl corridab = lcid.get(0);
               //List<TmsBoletosNoDisponibleTbl> lbnd= consultaRolesbase.queryTmsBoletosNoDisponibleTblFindByIdCorrida(idcorrida);
               //Vector vReservados = (Vector) busquedas.variosFacadeRemote.queryBuscarBoletosReservados(idcorrida);
               //Vector vVendidos = (Vector) busquedas.variosFacadeRemote.queryBuscarBoletosVendidos(idcorrida);
               Vector vVPasajeros = (Vector) busquedas.variosFacadeRemote.queryBuscarPasajeros(idcorrida);
               Vector vPasajeros = (Vector)vVPasajeros.get(0);
               long pasajeros = Long.valueOf(vPasajeros.get(0).toString());
               //if(vVendidos.size()>0 || vReservados.size()>0)
               if(pasajeros>0)
               {
                  lcorrinbor.add(corridab); 
                  String fcor = formatf.format((corridab.getFechaHoraCorrida().getTime()));
                  String hcor = formath.format((corridab.getFechaHoraCorrida().getTime()));
                  String or = ""+corridab.getOrigenId();
                  String des = ""+corridab.getDestinoId();
                  String emp =""+corridab.getEmpresaId();
                  String indicecb = fcor+"-"+hcor+"-"+or+"-"+des+"-"+emp;
                  indicecorrinbor.add(indicecb);
                  System.out.println("     La corrida "+idcorrida+" no se puede eliminar");
               }
               else
               {
                   System.out.println("La corrida "+idcorrida+" se eliminó");
                   busquedas.variosFacadeRemote.removeTmsCorridasTbl(corridab);
                   busquedas.variosFacadeRemote.removeTmsCorridasVentaTbl(corridab.getCorridaId());
               }
               jProgressBar1.setValue(i);
               jProgressBar1.setStringPainted(true);
               jProgressBar1.paint(jProgressBar1.getGraphics());
             }
             for(int i=0; i<lcorrinbor.size(); i++)
             {
                 TmsCorridasTbl corrida = lcorrinbor.get(i);
                 System.out.println("id: "+corrida.getCorridaId()+"     Fecha: "+formatf.format(corrida.getFechaHoraCorrida().getTime())+"    Hora:"+formath.format(corrida.getFechaHoraCorrida()) + "    Servicio id: "+corrida.getServicioId());
             }
            }//if(lcexbd.size()>0) 
                  
     System.out.println("El servicio de las corridas nuevas es: "+idrservicio);
     System.out.println(" Creando corridas nuevas...");     
///Inicio de corridas nuevas     
     jProgressBar1.setValue(0);
     //System.exit(0);
    jProgressBar1.setValue( 0 );
    jProgressBar1.setMinimum( 0 );
    jProgressBar1.setMaximum(300 );//numcolfinal - numcolinial);
    int corridasact = 0;
    int nprogress = 0;
    Vector NSC = new Vector();
    String ServicioComercial = "";
    boolean serv = true;
     SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
     SimpleDateFormat formatd = new SimpleDateFormat ("EEEE");
     Timestamp t = fcorridasi;
     //long tt=(t.getTime())+86400000;
     long tt=t.getTime();
     long diasduracion=(fcorridasf.getTime()-fcorridasi.getTime())/86400000;
     //int ni = Integer.valueOf(""+diasduracion);
    jProgressBar1.setMaximum(Integer.valueOf(""+diasduracion) );//numcolfinal - numcolinial);
    for(int i=0; i<=diasduracion; i++)
    {
       for(int j=0; j<jtbl_horarios.getRowCount(); j++)
        {
            Timestamp fecha = new Timestamp(tt);
            String dia = formatd.format(tt);
            Boolean lunb = (Boolean)jtbl_horarios.getValueAt(j,4);
            Boolean marb = (Boolean)jtbl_horarios.getValueAt(j,5);
            Boolean mieb = (Boolean)jtbl_horarios.getValueAt(j,6);
            Boolean jueb = (Boolean)jtbl_horarios.getValueAt(j,7);
            Boolean vieb = (Boolean)jtbl_horarios.getValueAt(j,8);
            Boolean sabb = (Boolean)jtbl_horarios.getValueAt(j,9);
            Boolean domb = (Boolean)jtbl_horarios.getValueAt(j,10);
            boolean aplica = false;
            if(dia.equals("lunes") && lunb)
                aplica = true;
            if(dia.equals("martes") && marb)
                aplica = true;
            if(dia.equals("miércoles") && mieb)
                aplica = true;
            if(dia.equals("jueves") && jueb)
                aplica = true;
            if(dia.equals("viernes") && vieb)
                aplica = true;
            if(dia.equals("sábado") && sabb)
                aplica = true;
            if(dia.equals("domingo") && domb)
                aplica = true;
            if(aplica)
            {
                //System.out.println("se genera "+jtbl_horarios.getValueAt(j,0).toString()+" de la fecha: "+fecha+"   que es el dia: "+dia);
                TmsCorridasTbl corrida = new TmsCorridasTbl();
                TmsCorridasVentaTbl corridaVenta = new TmsCorridasVentaTbl();
                 StringTokenizer ht = new StringTokenizer(jtbl_horarios.getValueAt(j,0).toString(),":");
                 String hora = ht.nextToken();
                 hora = hora+ht.nextToken();
                 String nor = jtbl_horarios.getValueAt(j,1).toString();
                 String oclv = "";
                 if(nor.length()>=4)
                  oclv = nor.substring(0,4);
                 if(nor.length()<4)
                 {
                     oclv = nor;
                     for(int jk=nor.length(); jk<4;jk++)
                        oclv = oclv+"_";
                 }
                corrida.setClaveCorrida(oclv+jtbl_horarios.getValueAt(j,16).toString()+hora+"N");
                corrida.setServicioId(BigInteger.valueOf(idrservicio));
                corrida.setEmpresaId(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,14).toString())));
                corrida.setRutaId(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,11).toString())));
                Timestamp fechaHora= new Timestamp(tt + milis(jtbl_horarios.getValueAt(j,0).toString()));
                corrida.setFechaHoraCorrida(new Date(fechaHora.getTime()));
                corrida.setOrigenId(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,12).toString())));
                corrida.setDestinoId(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,13).toString())));
                corrida.setTipoCorrida("N");
                if(idRutasInfo.indexOf(jtbl_horarios.getValueAt(j,11).toString())>=0)//jcbx_abiertas.isSelected())
                  corrida.setEstadoCorridaId(BigInteger.valueOf(estadoAbiertaId));
                else
                  corrida.setEstadoCorridaId(BigInteger.valueOf(estadoCerradaId));  
                int index = rutasId.indexOf(jtbl_horarios.getValueAt(j,11).toString());
                plantillaDefaultId = Long.valueOf(plantillaRuta.get(index).toString());
                corrida.setPlantillaId(BigInteger.valueOf(plantillaDefaultId));
                corrida.setAdicional4("ROL");
                corrida.setCreadoPor(BigInteger.valueOf(usuarioId));
                corrida.setFechaCreacion(new Date(fecha_servidor.getTime()));
                corrida.setUltimaActualizacionPor(BigInteger.valueOf(usuarioId));
                corrida.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
                corrida.setReplicacionEstado("P");
                corrida.setReplicacionOrigen("PCENTRAL");
                        
                        
                corridaVenta.setEmpresa(jtbl_horarios.getValueAt(j,3).toString());
                corridaVenta.setServicio(jtbl_horarios.getValueAt(j,15).toString());
                corridaVenta.setFechaHoraCorrida(corrida.getFechaHoraCorrida());
                corridaVenta.setOrigen(jtbl_horarios.getValueAt(j,1).toString());
                corridaVenta.setDestino(jtbl_horarios.getValueAt(j,2).toString());
                 corridaVenta.setTipoCorrida("N");
                if(idRutasInfo.indexOf(jtbl_horarios.getValueAt(j,11).toString())>=0)//jcbx_abiertas.isSelected())
                 corridaVenta.setEstadoCorrida("A");
                else 
                 corridaVenta.setEstadoCorrida("E");   
                corridaVenta.setPlantillaId(BigInteger.valueOf(plantillaDefaultId));
                corridaVenta.setAsiento1("D");corridaVenta.setAsiento2("D");corridaVenta.setAsiento3("D");corridaVenta.setAsiento4("D");corridaVenta.setAsiento5("D");
                corridaVenta.setAsiento6("D");corridaVenta.setAsiento7("D");corridaVenta.setAsiento8("D");corridaVenta.setAsiento9("D");corridaVenta.setAsiento10("D");
                corridaVenta.setAsiento11("D");corridaVenta.setAsiento12("D");corridaVenta.setAsiento13("D");corridaVenta.setAsiento14("D");corridaVenta.setAsiento15("D");
                corridaVenta.setAsiento16("D");corridaVenta.setAsiento17("D");corridaVenta.setAsiento18("D");corridaVenta.setAsiento19("D");corridaVenta.setAsiento20("D");
                corridaVenta.setAsiento21("D");corridaVenta.setAsiento22("D");corridaVenta.setAsiento23("D");corridaVenta.setAsiento24("D");corridaVenta.setAsiento25("D");
                corridaVenta.setAsiento26("D");corridaVenta.setAsiento27("D");corridaVenta.setAsiento28("D");corridaVenta.setAsiento29("D");corridaVenta.setAsiento30("D");
                corridaVenta.setAsiento31("D");corridaVenta.setAsiento32("D");corridaVenta.setAsiento33("D");corridaVenta.setAsiento34("D");corridaVenta.setAsiento35("D");
                corridaVenta.setAsiento36("D");corridaVenta.setAsiento37("D");corridaVenta.setAsiento38("D");corridaVenta.setAsiento39("D");corridaVenta.setAsiento40("D");
                corridaVenta.setAsiento41("D");corridaVenta.setAsiento42("D");corridaVenta.setAsiento43("D");corridaVenta.setAsiento44("D");corridaVenta.setAsiento45("D");
                corridaVenta.setAsiento46("D");corridaVenta.setAsiento47("D");corridaVenta.setAsiento48("D");corridaVenta.setAsiento49("D");corridaVenta.setAsiento50("D");
                corridaVenta.setAsiento51("D");corridaVenta.setAsiento52("D");corridaVenta.setAsiento53("D");corridaVenta.setAsiento54("D");corridaVenta.setAsiento55("D");
                corridaVenta.setAsiento56("D");corridaVenta.setAsiento57("D");corridaVenta.setAsiento58("D");corridaVenta.setAsiento59("D");corridaVenta.setAsiento60("D");
                corridaVenta.setAsiento61("D");corridaVenta.setAsiento62("D");corridaVenta.setAsiento63("D");corridaVenta.setAsiento64("D");corridaVenta.setAsiento65("D");
                corridaVenta.setAsiento66("D");corridaVenta.setAsiento67("D");corridaVenta.setAsiento68("D");corridaVenta.setAsiento69("D");corridaVenta.setAsiento70("D");
                corridaVenta.setAsiento71("D");corridaVenta.setAsiento72("D");corridaVenta.setAsiento73("D");corridaVenta.setAsiento74("D");corridaVenta.setAsiento75("D");
                corridaVenta.setAsiento76("D");corridaVenta.setAsiento77("D");corridaVenta.setAsiento78("D");corridaVenta.setAsiento79("D");corridaVenta.setAsiento80("D");
                corridaVenta.setAsiento81("D");corridaVenta.setAsiento82("D");corridaVenta.setAsiento83("D");corridaVenta.setAsiento84("D");corridaVenta.setAsiento85("D");
                corridaVenta.setMenoresCorrida(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,18).toString())));
                corridaVenta.setCortesiasCorrida(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,22).toString())));
                corridaVenta.setSenectudCorrida(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,19).toString())));
                corridaVenta.setProfesoresCorrida(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,21).toString())));
                corridaVenta.setEstudiantesCorrida(BigInteger.valueOf(Long.valueOf(jtbl_horarios.getValueAt(j,20).toString())));
                corridaVenta.setAdicional2(jtbl_horarios.getValueAt(j,19).toString()+"-"+jtbl_horarios.getValueAt(j,21).toString()+"-"+jtbl_horarios.getValueAt(j,20).toString()+"-"+jtbl_horarios.getValueAt(j,22).toString());//S-P-E-Cor 
                                                                                                                                                                               //"N_Men","N_Sen","N_Est","N_Prof","N_Cort"      
                                                                                                                                                                                    //           19      20       21      22
                corridaVenta.setReplicacionEstado("P");
                corridaVenta.setReplicacionOrigen("PCENTRAL");
                
                
                TmsCorridasTbl corridaNueva;

                
                  String fcor = formatf.format((corrida.getFechaHoraCorrida().getTime()));
                  String hcor = formath.format((corrida.getFechaHoraCorrida().getTime()));
                  String or = ""+corrida.getOrigenId();
                  String des = ""+corrida.getDestinoId();
                  String emp =""+corrida.getEmpresaId();
                  String indicecb = fcor+"-"+hcor+"-"+or+"-"+des+"-"+emp;
                 if(indicecorrinbor.indexOf(indicecb)!=-1)
                 {
                   TmsCorridasTbl corridaupdate = lcorrinbor.get(indicecorrinbor.indexOf(indicecb));
                   busquedas.variosFacadeRemote.updateCorrida(corrida,corridaupdate.getCorridaId(),corridaVenta); 
                   corridasact++;
                   System.out.println("*********** Se actualiza la corrida: "+indicecb);
                 }
                 else
                 {
                      System.out.println("se genera "+jtbl_horarios.getValueAt(j,0).toString()+" de la fecha: "+fecha+"   que es el dia: "+dia); 
                      corridaNueva = busquedas.corridasTblFacadeRemote.create(corrida,idTerminal);
                       corridaVenta.setCorridaId(corridaNueva.getCorridaId());
                       corridaVenta.setClaveCorrida(corridaNueva.getClaveCorrida());
                       busquedas.corridasVentaTblFacadeRemote.create(corridaVenta);
                       salidastotal = salidastotal+1;  
                 }

            }//if(aplica)             
        }//j==NRenglones mTable
      nprogress++;  
      jProgressBar1.setValue(nprogress);
      System.out.println("aumenta la barra de estado a: "+i);
      jProgressBar1.setStringPainted(true);
      jProgressBar1.paint(jProgressBar1.getGraphics());   
      tt = tt+ 86400000;
      
    }//i==diasduracion
        //SimpleDateFormat formatd = new SimpleDateFormat ("dd/MM/yyyy");
        if(corridasact==0)
            JOptionPane.showMessageDialog(this,"Se han generado "+(salidastotal - 1)+" corridas exitosamente \n              del  "+ sfcorridasi +"  al  "+ sfcorridasf +"","¡Corridas Generadas!", 1);
        else
            JOptionPane.showMessageDialog(this,"Se han generado "+(salidastotal - 1)+" nuevas corridas \ny actualizado "+corridasact+" corridas exitosamente \n       del  "+ sfcorridasi +"  al  "+ sfcorridasf +"","¡Corridas Generadas!", 1);
        
        jProgressBar1.setValue(0);
        jtxt_fechafinal.setText("");
        jtxt_fechainicial.setText("");
///termina la generacion de corridas     
              }
              else
                return;  
           }
           }
        
          }
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JTable jtbl_horarios;
    private javax.swing.JTable jtbl_rutas;
    private tms_TextFields.JDateTextField jtxt_fechafinal;
    private tms_TextFields.JDateTextField jtxt_fechainicial;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezadosHorarios = {"Hora","Origen","Destino","Empresa","Lun","Mar","Mie","Jue","Vie","Sab","Dom","ruta_id","origen_id","destino_id","empresa_id","servicio_nombre","servicio_clave","empresa_nombre","N_Men","N_Sen","N_Est","N_Prof","N_Cort"};
    private DefaultTableModel modeloHorarios = new DefaultTableModel(null,encabezadosHorarios){
            public boolean isCellEditable(int row, int column) {
            if (column == 30  )
               return true;
            return false;
            }
            public Class getColumnClass(int c) { 
               return getValueAt(0, c).getClass(); }
        };

    private Object[] encabezadosRutas = {"ruta_id","Numero","Nombre","Abierta"};
    private DefaultTableModel modeloRutas = new DefaultTableModel(null,encabezadosRutas){
            public boolean isCellEditable(int row, int column) {
            if (column == 3  )
               return true;
            return false;
            }
            public Class getColumnClass(int c) { 
               return getValueAt(0, c).getClass(); }
        };
    private TmsRolesManagedBean busquedas;

    private Vector vHorarios;
    private Timestamp fecha_servidor= null; 
    private SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
    private SimpleDateFormat formath = new SimpleDateFormat ("HH:mm");
    private boolean respuestaSN;
    private long idrservicio;
    private Vector excluir;
    private boolean haycorridas = false;
    private int salidastotal;
    private String nombreTerminal;
    private long estadoAbiertaId= 0;
    private long plantillaDefaultId=0;
    private long usuarioId;
    private Vector rutasId = new Vector();
    private Vector plantillaRuta = new Vector();
    private String servicioNombre;
    private String idTerminal;
    private Vector vrutes;
    private long estadoCerradaId =0;

    private String rutcadena = "()";


    
     private long milis(String hora){
        long m= 0;
        String[] h = hora.split(":");
        if(h[0].equals("00") || h[0].equals("0"))
         m= Integer.parseInt(h[1]) * 60000;
        else 
          m = (Integer.parseInt(h[0]) * 3600000) + (Integer.parseInt(h[1]) * 60000);
        return m;
       }
     
    public boolean getHaycorridas() {
        return haycorridas;
    }
   
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

} 
