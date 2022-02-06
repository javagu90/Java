/*
 * JIFRaglasSustitucion.java
 *
 * Created on 21 de octubre de 2007, 04:48 PM
 */

package tmsreglassustitucion;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tmsreglassustit.entidad.TmsEstadosTbl;
import tmsreglassustit.entidad.TmsFlotillasTbl;
import tmsreglassustit.entidad.TmsReglasSustTbl;

/**
 *
 * @author  vgonzalez
 */
public class JIFRaglasSustitucion extends javax.swing.JInternalFrame {//javax.swing.JFrame {// 
    /** Creates new form JIFRaglasSustitucion */
    public JIFRaglasSustitucion(Vector pDatosIniciales) {
        this.usuarioId = pDatosIniciales.get(0).toString();
        this.sesionId = pDatosIniciales.get(3).toString();
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	this.setTitle("Reglas de Sustitucion   Rev29.05.08");
        //((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        listadoFlotillas = (List<TmsFlotillasTbl>)busquedas.flotillasTblFacadeRemote.findAll();
        jcmb_flotilla.removeAllItems();
        for(int i=0; i<listadoFlotillas.size(); i++)
        {
            TmsFlotillasTbl flotilla = (TmsFlotillasTbl)listadoFlotillas.get(i);
            jcmb_flotilla.addItem(flotilla.getNombreFlotilla());
            //System.out.println("La Flotilla: "+flotilla.getNombreFlotilla());
            //System.out.println("La Coleccion de flotillas de sustitucion  es de: "+flotilla.getTmsReglasSustTblCollection().size());
        }
        listaEstado = busquedas.estadosTblFacadeRemote.buscarPorTipComponente("ACTIVIDAD");
        jtbl_sustituciones.setModel(modeloSusticiones);
        llenarCombosBoxs();
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Regla |  <font color=FF0000>ABAJO</font> Desplegar Flotillas | <font color=FF0000>ENTER</font> Seleccionar Flotilla  </html>");
        jcmb_flotilla.setFocusTraversalKeysEnabled(false);
        jtbl_sustituciones.setFocusTraversalKeysEnabled(false);
        jtbl_sustituciones.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jcmb_flotilla.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        this.registerKeyboardAction( new ActionListener() { public void actionPerformed(ActionEvent e) { ; }},KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),JComponent.WHEN_FOCUSED);
        jcmb_flotilla.requestFocus();
    }

    private void llenarCombosBoxs(){
        JComboBox comboBox = new JComboBox();
        JComboBox comboBox2 = new JComboBox();
        for(int i=0; i<listadoFlotillas.size(); i++)
        {
            TmsFlotillasTbl flotilla = (TmsFlotillasTbl)listadoFlotillas.get(i);
            comboBox.addItem(flotilla.getNombreFlotilla());
            indiceFlotillas.add(flotilla.getNombreFlotilla());
        }
        comboBox.setSelectedIndex(0);
        for(int i=0; i<listaEstado.size(); i++)
        {
            TmsEstadosTbl estado = (TmsEstadosTbl)listaEstado.get(i);
            comboBox2.addItem(estado.getEstadoNombre());
            indiceEstados.add(estado.getEstadoNombre());
        }
        TableColumn flotillasColumn = jtbl_sustituciones.getColumnModel().getColumn(0);
        flotillasColumn.setCellEditor(new DefaultCellEditor(comboBox));
        TableColumn actividadesColumn = jtbl_sustituciones.getColumnModel().getColumn(2);
        actividadesColumn.setCellEditor(new DefaultCellEditor(comboBox2));
    }
    
    private void guardarDatos(){
       //System.out.println("el valor seleccionado es: "+jtbl_sustituciones.getValueAt(0,0));
       guardar = false;
       int index = jcmb_flotilla.getSelectedIndex();
       TmsFlotillasTbl flotillaSeleccionada = listadoFlotillas.get(index);
       //Collection<TmsReglasSustTbl> coleccion = flotillaSeleccionada.getTmsReglasSustTblCollection();
       //List<TmsReglasSustTbl> listaReglas = new ArrayList<TmsReglasSustTbl>();
       List<TmsReglasSustTbl> listaReglas = busquedas.reglasSustTblFacadeRemote.buscaReglas(flotillaSeleccionada.getFlotillaId().longValue());
       Vector x = (Vector) busquedas.reglasSustTblFacadeRemote.fechaServidor();
       fecha_servidor = fecha_servidor.valueOf(x.get(0).toString());
//       if(coleccion.size()>0)
//       {
//       Iterator iprs = coleccion.iterator();
//        while(iprs.hasNext()){
//           TmsReglasSustTbl reglaSustitucion = (TmsReglasSustTbl)iprs.next();
//           listaReglas.add(reglaSustitucion);
//        }            
//       }
       boolean correcto = true;
       for(int i=0; i<jtbl_sustituciones.getRowCount();i++)
       {
           String flotilla = jtbl_sustituciones.getValueAt(i,0).toString();
           String actividad = jtbl_sustituciones.getValueAt(i,2).toString();
           for(int j=(i+1);j<jtbl_sustituciones.getRowCount();j++)
           {
               if(flotilla.equals(jtbl_sustituciones.getValueAt(j,0).toString()) && actividad.equals(jtbl_sustituciones.getValueAt(j,2).toString()))
               {
                   correcto = false;
                   break;
               }
           }
           if(!correcto)
               break;
       }
      if(correcto)
      {
       //Caso 0
        if(listaReglas.size() == jtbl_sustituciones.getRowCount()){
         System.out.println("Entro al caso 0");   
         for(int i=0; i<listaReglas.size(); i++)
         {
           TmsReglasSustTbl reglaOriginal = listaReglas.get(i);
           int indice = indiceFlotillas.indexOf(jtbl_sustituciones.getValueAt(i,0).toString()); 
           reglaOriginal.setFlotillaSustId(listadoFlotillas.get(indice));
           reglaOriginal.setPrioridad(BigInteger.valueOf(Long.valueOf(jtbl_sustituciones.getValueAt(i,1).toString())));
           int indiceedo = indiceEstados.indexOf(jtbl_sustituciones.getValueAt(i,2).toString()); 
           reglaOriginal.setActividadId(listaEstado.get(indiceedo));
           reglaOriginal.setUltimaActualizacionPor(Integer.valueOf(usuarioId));
           reglaOriginal.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
           busquedas.reglasSustTblFacadeRemote.edit(reglaOriginal);
         }
       }//caso 0
       //Caso 1
       if(listaReglas.size() < jtbl_sustituciones.getRowCount() && listaReglas.size()>0){
         System.out.println("Entro al caso 1");   
         for(int i=0; i<listaReglas.size(); i++)
         {
           TmsReglasSustTbl reglaOriginal = listaReglas.get(i);
           int indice = indiceFlotillas.indexOf(jtbl_sustituciones.getValueAt(i,0).toString()); 
           reglaOriginal.setFlotillaSustId(listadoFlotillas.get(indice));
           reglaOriginal.setPrioridad(BigInteger.valueOf(Long.valueOf(jtbl_sustituciones.getValueAt(i,1).toString())));
           int indiceedo = indiceEstados.indexOf(jtbl_sustituciones.getValueAt(i,2).toString()); 
           reglaOriginal.setActividadId(listaEstado.get(indiceedo));
           reglaOriginal.setUltimaActualizacionPor(Integer.valueOf(usuarioId));
           reglaOriginal.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
           busquedas.reglasSustTblFacadeRemote.edit(reglaOriginal);
         }
         for(int i=listaReglas.size();i<jtbl_sustituciones.getRowCount();i++){
           TmsReglasSustTbl reglaNueva = new TmsReglasSustTbl();
           int indiceFlotillaPrincipal = jcmb_flotilla.getSelectedIndex();
           TmsFlotillasTbl flotillaPrincipal = listadoFlotillas.get(indiceFlotillaPrincipal);
           reglaNueva.setFlotillaId(flotillaPrincipal);
           int indice = indiceFlotillas.indexOf(jtbl_sustituciones.getValueAt(i,0).toString()); 
           reglaNueva.setFlotillaSustId(listadoFlotillas.get(indice));
           reglaNueva.setPrioridad(BigInteger.valueOf(Long.valueOf(jtbl_sustituciones.getValueAt(i,1).toString())));
           int indiceedo = indiceEstados.indexOf(jtbl_sustituciones.getValueAt(i,2).toString()); 
           reglaNueva.setActividadId(listaEstado.get(indiceedo));
           reglaNueva.setCreadoPor(Integer.valueOf(usuarioId));
           reglaNueva.setFechaCreacion(new Date(fecha_servidor.getTime()));
           reglaNueva.setUltimaActualizacionPor(Integer.valueOf(usuarioId));
           reglaNueva.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
           busquedas.reglasSustTblFacadeRemote.create(reglaNueva);
         }
       }//caso1
       //Caso 2 
       if(listaReglas.size() > jtbl_sustituciones.getRowCount() && jtbl_sustituciones.getRowCount()>0){
         System.out.println("Entro al caso 2");   
         for(int i=0; i<jtbl_sustituciones.getRowCount(); i++)
         {
           TmsReglasSustTbl reglaOriginal = listaReglas.get(i);
           int indice = indiceFlotillas.indexOf(jtbl_sustituciones.getValueAt(i,0).toString()); 
           reglaOriginal.setFlotillaSustId(listadoFlotillas.get(indice));
           reglaOriginal.setPrioridad(BigInteger.valueOf(Long.valueOf(jtbl_sustituciones.getValueAt(i,1).toString())));
           int indiceedo = indiceEstados.indexOf(jtbl_sustituciones.getValueAt(i,2).toString()); 
           reglaOriginal.setActividadId(listaEstado.get(indiceedo));
           reglaOriginal.setUltimaActualizacionPor(Integer.valueOf(usuarioId));
           reglaOriginal.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
           busquedas.reglasSustTblFacadeRemote.edit(reglaOriginal);
         }
         for(int i=jtbl_sustituciones.getRowCount(); i<listaReglas.size();i++)
         {
           TmsReglasSustTbl reglaBorrar = listaReglas.get(i);
           busquedas.reglasSustTblFacadeRemote.destroy(reglaBorrar);
         }
       }//caso2       
        //caso 3
        if(listaReglas.size() > jtbl_sustituciones.getRowCount() && jtbl_sustituciones.getRowCount()==0){
         System.out.println("Entro al caso 3");   
         for(int i=0; i<listaReglas.size();i++)
         {
           TmsReglasSustTbl reglaBorrar = listaReglas.get(i);
           busquedas.reglasSustTblFacadeRemote.destroy(reglaBorrar);
         }
        }//caso3
       //Caso 4
       if(listaReglas.size() < jtbl_sustituciones.getRowCount() && listaReglas.size()==0){
         System.out.println("Entro al caso 4");   
         for(int i=0;i<jtbl_sustituciones.getRowCount();i++){
           TmsReglasSustTbl reglaNueva = new TmsReglasSustTbl();
           int indiceFlotillaPrincipal = jcmb_flotilla.getSelectedIndex();
           TmsFlotillasTbl flotillaPrincipal = listadoFlotillas.get(indiceFlotillaPrincipal);
           reglaNueva.setFlotillaId(flotillaPrincipal);
           int indice = indiceFlotillas.indexOf(jtbl_sustituciones.getValueAt(i,0).toString()); 
           reglaNueva.setFlotillaSustId(listadoFlotillas.get(indice));
           reglaNueva.setPrioridad(BigInteger.valueOf(Long.valueOf(jtbl_sustituciones.getValueAt(i,1).toString())));
           int indiceedo = indiceEstados.indexOf(jtbl_sustituciones.getValueAt(i,2).toString()); 
           reglaNueva.setActividadId(listaEstado.get(indiceedo));
           reglaNueva.setCreadoPor(Integer.valueOf(usuarioId));
           reglaNueva.setFechaCreacion(new Date(fecha_servidor.getTime()));
           reglaNueva.setUltimaActualizacionPor(Integer.valueOf(usuarioId));
           reglaNueva.setUltimaFechaActualizacion(new Date(fecha_servidor.getTime()));
           busquedas.reglasSustTblFacadeRemote.create(reglaNueva);
         }            
       }//caso4        
        
        new jdlg_informacion("¡Los datos se guardaron satisfactoriamente!","","Datos guadados").setVisible(true);
           //modeloSusticiones.setDataVector(null,encabezadosSustituciones);
            listadoFlotillas = (List<TmsFlotillasTbl>)busquedas.flotillasTblFacadeRemote.findAll();
            //jcmb_flotilla.removeAllItems();
            String flot = jcmb_flotilla.getSelectedItem().toString();
            jcmb_flotilla.removeAllItems();
            for(int i=0; i<listadoFlotillas.size(); i++)
            {
                TmsFlotillasTbl flotilla = (TmsFlotillasTbl)listadoFlotillas.get(i);
                jcmb_flotilla.addItem(flotilla.getNombreFlotilla());
            }
            jcmb_flotilla.setSelectedItem(flot);
           //llenarCombosBoxs();
           //jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Regla |  <font color=FF0000>ABAJO</font> Desplegar Flotillas | <font color=FF0000>ENTER</font> Seleccionar Flotilla  </html>");
           //jcmb_flotilla.setEnabled(true);
           //jtxt_descripcion.setText("");          
           //jcmb_flotilla.requestFocus();
     }
      else
          new jdlg_error("¡No puede repetirse la actividad con la misma flotilla!","","Error de datos").setVisible(true);

    }
    
    private void salirAplicacion(){
       jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de salida", "¿Seguro que desea salir de Reglas de Sustitución?");
          psn.setVisible(true);
          if(respuestaSN)
              this.dispose();
          else
            return;  

        this.dispose();
        
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
        jcmb_flotilla = new javax.swing.JComboBox();
        jtxt_descripcion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_sustituciones = new javax.swing.JTable();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Reglas de Sustitucion de Autobuses");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Flotillas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Flotilla: ");

        jcmb_flotilla.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmb_flotilla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcmb_flotillaFocusGained(evt);
            }
        });
        jcmb_flotilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcmb_flotillaKeyReleased(evt);
            }
        });

        jtxt_descripcion.setEditable(false);
        jtxt_descripcion.setFocusable(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jcmb_flotilla, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_descripcion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jcmb_flotilla, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jtxt_descripcion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reglas de Sustitucion de Autobuses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jtbl_sustituciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Flotilla", "Prioridad", "Actividad"
            }
        ));
        jtbl_sustituciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_sustitucionesFocusGained(evt);
            }
        });
        jtbl_sustituciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_sustitucionesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbl_sustitucionesKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_sustituciones);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(102, 102, 102)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 507, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_barraEstado.setForeground(new java.awt.Color(153, 153, 153));
        jlbl_barraEstado.setText("jLabel2");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 22, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmb_flotillaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcmb_flotillaFocusGained
        jlbl_barraEstado.setText("<html><font color=FF0000>F4</font> Salir de Regla | <font color=FF0000>ABAJO</font> Desplegar Flotillas | <font color=FF0000>ENTER</font> Seleccionar Flotilla | <font color=FF0000>CTRL+2</font> Minimizar ventana | <font color=FF0000>CTRL+1</font> Mostrar siguiente ventana</html>");
    }//GEN-LAST:event_jcmb_flotillaFocusGained

    private void jtbl_sustitucionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_sustitucionesKeyReleased
     if(evt.getKeyCode()== evt.VK_ESCAPE)
     {
       if(guardar){
         jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("No ha guardado cambios", "¿Desea salir sin guardar cambios?");
         psn.setVisible(true);
          if(respuestaSN)
          {
               modeloSusticiones.setDataVector(null,encabezadosSustituciones);
               llenarCombosBoxs();
               jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Regla |  <font color=FF0000>ABAJO</font> Desplegar Flotillas | <font color=FF0000>ENTER</font> Seleccionar Flotilla  </html>");
               jcmb_flotilla.setEnabled(true);
               jtxt_descripcion.setText("");          
               jcmb_flotilla.requestFocus();
               guardar = false;
           //guardar = false;
           //guardarDatos();
          }
          else
            return;
       }
       else
       {
           modeloSusticiones.setDataVector(null,encabezadosSustituciones);
           llenarCombosBoxs();
           jlbl_barraEstado.setText("<html>  <font color=FF0000>   F4</font> Salir de Regla |  <font color=FF0000>ABAJO</font> Desplegar Flotillas | <font color=FF0000>ENTER</font> Seleccionar Flotilla  </html>");
           jcmb_flotilla.setEnabled(true);
           jtxt_descripcion.setText("");          
           jcmb_flotilla.requestFocus();
       }
     }        
    }//GEN-LAST:event_jtbl_sustitucionesKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
       this.setFoco();
    }//GEN-LAST:event_formComponentShown

    public void setFoco(){
        if(jtbl_sustituciones.getRowCount()>0){
            jtbl_sustituciones.requestFocusInWindow();
            return;
        }
        jcmb_flotilla.requestFocusInWindow();
    }
    private void jtbl_sustitucionesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtbl_sustitucionesFocusGained
           jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Regresar a Flotillas | <font color=FF0000>F2</font> Cambiar Flotilla o Actividad | <font color=FF0000>INSERT</font> Insertar Regla | <font color=FF0000>DELETE</font> Eliminar Regla | <font color=FF0000>F11</font> Guardar</html>");
    }//GEN-LAST:event_jtbl_sustitucionesFocusGained

    private void jcmb_flotillaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmb_flotillaKeyReleased
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
            jcmb_flotilla.setEnabled(true);
            int index = jcmb_flotilla.getSelectedIndex();
            TmsFlotillasTbl flotillaSeleccionada = listadoFlotillas.get(index);
            jtxt_descripcion.setText(flotillaSeleccionada.getDescripcionFlotilla());
            //System.out.println("La flotilla seleccionada es: "+flotillaSeleccionada.getNombreFlotilla());
//            Collection<TmsReglasSustTbl> coleccion = flotillaSeleccionada.getTmsReglasSustTblCollection();
              List<TmsReglasSustTbl> listaReglas = busquedas.reglasSustTblFacadeRemote.buscaReglas(flotillaSeleccionada.getFlotillaId().longValue());
//            Iterator iprs = coleccion.iterator();
//            int nr = 0;
//            while(iprs.hasNext()){
               for(int i=0; i<listaReglas.size(); i++){
                //TmsReglasSustTbl reglaSustitucion = (TmsReglasSustTbl)iprs.next();
                  TmsReglasSustTbl reglaSustitucion  = listaReglas.get(i);
                modeloSusticiones.addRow(new Object[3]);
                jtbl_sustituciones.setValueAt(reglaSustitucion.getFlotillaSustId().getNombreFlotilla(),i,0);
                jtbl_sustituciones.setValueAt(reglaSustitucion.getActividadId().getEstadoNombre(),i,2);
                jtbl_sustituciones.setValueAt(reglaSustitucion.getPrioridad().toString(),i,1);
                //jtbl_sustituciones.setRowSelectionInterval(jtbl_sustituciones.getRowCount()-1,jtbl_sustituciones.getRowCount()-1);
                //nr++;
            }            
           if(jtbl_sustituciones.getRowCount()>0)
           {
             jtbl_sustituciones.setRowSelectionInterval(0,0);
             jtbl_sustituciones.setColumnSelectionInterval(1,1);
           }
           jtbl_sustituciones.requestFocus();
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
        
        if(evt.getKeyCode() == evt.VK_UP || evt.getKeyCode() == evt.VK_DOWN)
        {
            int index = jcmb_flotilla.getSelectedIndex();
            TmsFlotillasTbl flotillaSeleccionada = listadoFlotillas.get(index);
            jtxt_descripcion.setText(flotillaSeleccionada.getDescripcionFlotilla());
        }
        
    }//GEN-LAST:event_jcmb_flotillaKeyReleased

    private void jtbl_sustitucionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_sustitucionesKeyPressed
     if(evt.getKeyCode()== evt.VK_F11)
     {
         for(int i=0; i<jtbl_sustituciones.getRowCount(); i++)
         {
            if(jtbl_sustituciones.getValueAt(i,0).toString().equals("Seleccione una Flotilla") || jtbl_sustituciones.getValueAt(i,2).toString().equals("Seleccione una Actividad"))
            {
              new jdlg_error("¡Desbes seleccionar una Flotilla y una Actividad!","","").setVisible(true);
              return;
            }
         }
        jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de Guardado de datos", "¿Seguro que desea guardar los datos actuales?");
         psn.setVisible(true);
          if(respuestaSN)
            guardarDatos();
          else
            return;  
     }
     if(evt.getKeyCode()== evt.VK_INSERT)
     {
        guardar = true;
        modeloSusticiones.addRow(new Object[3]);
        jtbl_sustituciones.setValueAt("Seleccione una Flotilla",jtbl_sustituciones.getRowCount()-1,0);
        jtbl_sustituciones.setValueAt("Seleccione una Actividad",jtbl_sustituciones.getRowCount()-1,2);
        jtbl_sustituciones.setValueAt(""+(jtbl_sustituciones.getRowCount()),jtbl_sustituciones.getRowCount()-1,1);
        if(jtbl_sustituciones.getRowCount()==1)
            jtbl_sustituciones.setRowSelectionInterval(0,0);
        jtbl_sustituciones.setColumnSelectionInterval(1,1);
     }
     
     if(evt.getKeyCode()== evt.VK_DELETE)
     {
       if(jtbl_sustituciones.getSelectedRow()>=0)
       {
         jdlg_pregunta_SN psn =  new jdlg_pregunta_SN("Confirmacion de eliminacion", "¿Seguro que desea eliminar la regla de prioridad "+jtbl_sustituciones.getValueAt(jtbl_sustituciones.getSelectedRow(),1)+"?");
         psn.setVisible(true);
          if(respuestaSN)
          {
             guardar = true;
             modeloSusticiones.removeRow(jtbl_sustituciones.getSelectedRow());
             for(int i=0;i<jtbl_sustituciones.getRowCount(); i++)
                 jtbl_sustituciones.setValueAt(""+(i + 1),i,1);
          }
          if(jtbl_sustituciones.getRowCount()>0)
          {
             jtbl_sustituciones.setRowSelectionInterval(0,0);
             jtbl_sustituciones.setColumnSelectionInterval(1,1);
          }
          else
            return;  
       } 
     }
     

     
     if(evt.getKeyCode()== evt.VK_F2)
        guardar = true;
    }//GEN-LAST:event_jtbl_sustitucionesKeyPressed
    
    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JIFRaglasSustitucion().setVisible(true);
            }
        });
    }
    */
    public KeyEvent getEventoTeclado(){ return this.eventoTeclado; }
    public void setEventoTeclado(KeyEvent evento){ this.eventoTeclado=evento; }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmb_flotilla;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JTable jtbl_sustituciones;
    private javax.swing.JTextField jtxt_descripcion;
    // End of variables declaration//GEN-END:variables
    private boolean respuestaSN;
    private long idUsuario =0;
    private long idSesion=0;
    private Timestamp fecha_servidor= null; 
    private boolean guardar=false;
    private TmsRegSustManagedBean busquedas = new TmsRegSustManagedBean();
    private List<TmsFlotillasTbl> listadoFlotillas;
    private Vector indiceFlotillas = new Vector();
    private Vector indiceEstados = new Vector();
    private List<TmsEstadosTbl> listaEstado;
    private Object[] encabezadosSustituciones = {"Flotilla","Prioridad","Actividad"};
    private DefaultTableModel modeloSusticiones = new DefaultTableModel(null,encabezadosSustituciones){
            public boolean isCellEditable(int row, int column) {
            if (column == 1)
               return false;
            return true;
            }
//            public Class getColumnClass(int c) { 
//               return getValueAt(0, c).getClass(); }
        };

    private String usuarioId;
    private String sesionId;

    private KeyEvent eventoTeclado;

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
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
    private KeyEvent eventoTeclado;
    // End of variables declaration                   
    
}          
        
}