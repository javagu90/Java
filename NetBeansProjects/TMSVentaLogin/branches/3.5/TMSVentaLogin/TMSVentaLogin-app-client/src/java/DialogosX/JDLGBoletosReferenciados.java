/*
 * JDLGBoletosReferenciados.java
 *
 * Created on 14 de noviembre de 2008, 11:21 AM
 */

package DialogosX;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.naming.Context;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import tms_venta.entidad.TmsEstadosTbl;
import tms_venta.solicitud.TmsVtaFacadeRemote;

/**
 *
 * @author  asolis
 */
public class JDLGBoletosReferenciados extends javax.swing.JDialog {
     Context cont = null;
     String datos [][] = null;
     String encabezados [] = {"Referencia","Nombre Pasajero","Nombre Autorizado","Origen", "Destino","Fecha","Tipo Operaci�n", "Estado Referencia"};
     DefaultTableModel modelo = new DefaultTableModel(datos, encabezados);
     int registros = -1;     
     private KeyEvent eventoTeclado;
     String salida  = "";
     static int num = 500;
     private TmsVtaFacadeRemote cosa;
     /**
     * Creates new form JDLGBoletosReferenciados
     */
    public JDLGBoletosReferenciados(TmsVtaFacadeRemote pcosa) {
        //super(parent, modal);
//         try {
//            cont = new InitialContext(System.getProperties());
//            cosa = (TMSSesionBoletosReferenciadosRemote) cont.lookup(TMSSesionBoletosReferenciadosRemote.class.getName());
//        } catch (NamingException ex) {
//            ex.printStackTrace();
//        }
        cosa = pcosa;
        initComponents();
        this.setTitle("Boletos Referenciados Rev19.05.14");
        resizeColumnasCorridas();
        jtbl_tabla.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );        
        jdch_fecha.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                //System.out.println("jdch_fecha.dateEditor "+ evt);
            if(evt.getKeyCode() == evt.VK_RIGHT){
                jtxt_referencia.requestFocus();
            }
            if(evt.getKeyCode() == evt.VK_LEFT){
                jcmb_origen.requestFocus();
            }
            Evento_Tecla(evt);
        }
        });
        Inicializar();
        jcmb_origen.requestFocus();
        this.setSize(600,420);
        centrarJDialog();
        this.setDefaultLookAndFeelDecorated(true);
//        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        this.setVisible(true);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jlbl_origen = new javax.swing.JLabel();
        jcmb_origen = new javax.swing.JComboBox();
        jlbl_fecha = new javax.swing.JLabel();
        jdch_fecha = new tms_calendario.JDateChooser();
        jlbl_referencia = new javax.swing.JLabel();
        jlbl_nombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_tabla = new javax.swing.JTable();
        jlbl_barraEstado = new javax.swing.JLabel();
        jlbl_registros = new javax.swing.JLabel();
        jtxt_registros = new javax.swing.JTextField();
        jtxt_nombre = new tms_TextFields.JTextTextField();
        jtxt_referencia = new tms_TextFields.JTextTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setName("Boletos Referenciados");
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jlbl_origen.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_origen.setText("Origen");

        jcmb_origen.setBackground(new java.awt.Color(255, 255, 204));
        jcmb_origen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcmb_origenKeyPressed(evt);
            }
        });

        jlbl_fecha.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_fecha.setText("Fecha");

        jlbl_referencia.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_referencia.setText("Referencia");

        jlbl_nombre.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_nombre.setText("Nombre");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyPressed(evt);
            }
        });

        jtbl_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbl_tabla.setModel(modelo);
        jtbl_tabla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtbl_tablaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtbl_tablaFocusLost(evt);
            }
        });
        jtbl_tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_tablaKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_tabla);

        jlbl_registros.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlbl_registros.setText("N\u00famero de Registros");

        jtxt_registros.setEditable(false);

        jtxt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_nombreKeyPressed(evt);
            }
        });

        jtxt_referencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_referenciaKeyPressed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jlbl_nombre)
                                .add(17, 17, 17)
                                .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(jlbl_origen)
                                .add(24, 24, 24)
                                .add(jcmb_origen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(36, 36, 36)
                                .add(jlbl_fecha)
                                .add(27, 27, 27)
                                .add(jdch_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 47, Short.MAX_VALUE)
                                .add(jlbl_referencia)
                                .add(18, 18, 18)
                                .add(jtxt_referencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(jlbl_registros)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jtxt_registros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlbl_origen)
                    .add(jcmb_origen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_fecha)
                    .add(jdch_fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jlbl_referencia)
                    .add(jtxt_referencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jlbl_nombre)
                    .add(jtxt_nombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(15, 15, 15)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 247, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jlbl_registros)
                    .add(jtxt_registros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>                        

    private void jtxt_referenciaKeyPressed(java.awt.event.KeyEvent evt) {                                           
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos");  
        if(evt.getKeyCode() == evt.VK_RIGHT){
            jtxt_nombre.requestFocus();
        }
        if(evt.getKeyCode() == evt.VK_LEFT){
            jdch_fecha.dateEditor.setFoco();
        }
        Evento_Tecla(evt);
    }                                          

    private void jtxt_nombreKeyPressed(java.awt.event.KeyEvent evt) {                                       
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos");  
        if(evt.getKeyCode() == evt.VK_RIGHT){
            jcmb_origen.requestFocus();
        }
        if(evt.getKeyCode() == evt.VK_LEFT){
            jtxt_referencia.requestFocus();
        }
        Evento_Tecla(evt);
    }                                      

    private void jtbl_tablaFocusLost(java.awt.event.FocusEvent evt) {                                     
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos"); 
        jcmb_origen.requestFocus();
    }                                    

    private void jScrollPane1KeyPressed(java.awt.event.KeyEvent evt) {                                        
        if(evt.getKeyCode() == evt.VK_ESCAPE) {
            System.out.println("Entre a ScrollPane keyPressed");
            registros = 0;
            numeroRegistros();
            datos = null;
            modelo.setDataVector(datos, encabezados);
            resizeColumnasCorridas();
            jcmb_origen.requestFocus();
        }
    }                                       

    private void jtbl_tablaKeyPressed(java.awt.event.KeyEvent evt) {                                      
        //jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos");  

        if(evt.getKeyCode() == evt.VK_ESCAPE) {
            System.out.println("Entre a tabla keyPressed");
            registros = 0;
            numeroRegistros();
            datos = null;
            modelo.setDataVector(datos, encabezados);
            resizeColumnasCorridas();
            jcmb_origen.requestFocus();
        }
    }                                     

    private void jtbl_tablaFocusGained(java.awt.event.FocusEvent evt) {                                       
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Realizar Nueva B�squeda");  
        if(jtbl_tabla.getRowCount()>0)
            jtbl_tabla.setRowSelectionInterval(0,0);
    }                                      

    private void formFocusGained(java.awt.event.FocusEvent evt) {                                 
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos");  
    }                                

    private void jcmb_origenKeyPressed(java.awt.event.KeyEvent evt) {                                       
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos");  
        if(evt.getKeyCode() == evt.VK_RIGHT){
            jdch_fecha.dateEditor.setFoco();
        }
        if(evt.getKeyCode() == evt.VK_LEFT){
            jtxt_nombre.requestFocus();
        }
        Evento_Tecla(evt);
    }                                      

    private void Evento_Tecla(KeyEvent e) {
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos");  
         try {
            if(e.getKeyCode() == e.VK_ESCAPE) {
            //System.out.println("Adiiiooos =)");
//                int result = JOptionPane.showOptionDialog(this, "�Desea cerrar Boletos Referenciados?",
//                            "TMS-Boletos Referenciados", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//                if(result == JOptionPane.YES_OPTION)
                    this.dispose();
            }
            if (e.getKeyCode() == e.VK_ENTER) {
                jtbl_tabla.requestFocus();
                String fec, nom, ori, ref;

                fec = jdch_fecha.dateEditor.getTexto();

                ori =(String) jcmb_origen.getSelectedItem();
                nom = jtxt_nombre.getText();
                ref = jtxt_referencia.getText();
                
                salida = "";
                if (ori.equals("")) {
                    JOptionPane.showMessageDialog( this, "Origen es campo obligatorios. \nPor favor, llene estos campos e intente de nuevo","TMS-Lista de Pasajeros", 
                            JOptionPane.ERROR_MESSAGE );   
                    datos = null;
                    registros = 0;
                    numeroRegistros();
                }else if((nom.length() != 0)&&((nom.length() < 3)||(nom.length() > 50))) {
                            JOptionPane.showMessageDialog( this, "La longitud del nombre debe ser mayor de 3 pero menor de 50\nIntente de nuevo, por favor","TMS-Lista de Pasajeros", 
                                JOptionPane.ERROR_MESSAGE );
                        datos = null;
                        registros = 0;
                        numeroRegistros();
                    } else {
                        Vector lineas = null;
                        System.out.println(ori + " " +fec+ " " + ref+ " "+ nom);
                        lineas = cosa.getBoletosReferenciados(ori,fec,nom,ref);
                        System.out.println("Lineas "+lineas);
                        
                        if((lineas!= null) && (lineas.size() >0)) {
                            Vector ll = (Vector) lineas.get(0);
                            if(!(ll.get(0).toString().equals("No puedo"))) {
                                int limite = Math.min(num,lineas.size());
                                datos = new String[limite][8];
                                int cont = 0;
                                Vector campos = null;
                                for (cont = 0; cont < limite; cont++) {
                                    campos = (Vector) lineas.get(cont);
                                    registros ++;
                                   for(int j = 0; j < campos.size(); j++) {
                                        if(campos.get(j) == null) {
                                            datos[cont][j] = "";
                                            salida = "" + salida + "\t";
                                        }else {
                                            datos[cont][j] = campos.get(j).toString();
                                            salida = "" + salida + datos[cont][j];
                                        }
                                        salida = "" + salida + "\t";
                                    }
                                    salida = "" +salida + "\n";
                                        
                                }
                            }else {
                                JOptionPane.showMessageDialog(this, "La b�squeda contiene demasiados registros\nPor favor, limite la busqueda e intente de nuevo ", "TMS-Lista de Pasajeros", JOptionPane.WARNING_MESSAGE);
                                datos = null;
                                registros = 0;
                                numeroRegistros();
                            }
                                
                        }else{
                            JOptionPane.showMessageDialog(this, "No existe registro alguno. \nPor favor, intente de nuevo", "TMS-Lista de Pasajeros", JOptionPane.WARNING_MESSAGE);
                            datos = null;
                            registros = 0;
                            numeroRegistros();
                        }
                    }
                
                numeroRegistros();
                modelo.setDataVector(datos, encabezados);
                resizeColumnasCorridas();
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

     public void resizeColumnasCorridas(){
        TableColumnModel columnModel = jtbl_tabla.getColumnModel();

           for (int col=0; col<jtbl_tabla.getColumnCount(); col++) 
           {
                int maxwidth = 0;            
                for (int row=0; row<jtbl_tabla.getRowCount(); row++) {
                    TableCellRenderer rend  = jtbl_tabla.getCellRenderer(row, col); 
                    Object value = jtbl_tabla.getValueAt (row, col); 
                    Component comp = rend.getTableCellRendererComponent (jtbl_tabla, value, false, false, row, col);
                    maxwidth = Math.max (comp.getPreferredSize().width, maxwidth); 
                } // for row

                TableColumn column = columnModel.getColumn (col);
                TableCellRenderer headerRenderer = column.getHeaderRenderer();
                if (headerRenderer == null)
                    headerRenderer = jtbl_tabla.getTableHeader().getDefaultRenderer();

                Object headerValue = column.getHeaderValue();
                Component headerComp = headerRenderer.getTableCellRendererComponent (jtbl_tabla, headerValue, false, false, 0, col);
                maxwidth = Math.max (maxwidth, headerComp.getPreferredSize().width);
                column.setPreferredWidth (maxwidth + 15);
           }
      }
    
    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmb_origen;
    private tms_calendario.JDateChooser jdch_fecha;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JLabel jlbl_fecha;
    private javax.swing.JLabel jlbl_nombre;
    private javax.swing.JLabel jlbl_origen;
    private javax.swing.JLabel jlbl_referencia;
    private javax.swing.JLabel jlbl_registros;
    private javax.swing.JTable jtbl_tabla;
    private tms_TextFields.JTextTextField jtxt_nombre;
    private tms_TextFields.JTextTextField jtxt_referencia;
    private javax.swing.JTextField jtxt_registros;
    // End of variables declaration                   
    
    
     public void Inicializar() {
        //Carga Estados
        registros = 0;
        numeroRegistros();
        
        System.out.println("Estados");
        
        List<TmsEstadosTbl> edos = new ArrayList<TmsEstadosTbl>();
        edos = cosa.cargarEstadosTBL();
                
        TmsEstadosTbl origen = new TmsEstadosTbl();
        
        for (int i = 0; i < edos.size(); i++) {
            origen = edos.get(i);
            jcmb_origen.insertItemAt(origen.getEstadoNombre(),i);
        }
        jcmb_origen.setSelectedIndex(0);
        jlbl_barraEstado.setText("<html>  <font color=FF0000>   ESC</font> Salir de Boletos Referenciados | <font color=FF0000>ENTER</font> Realizar Consulta | <font color=FF0000> � � </font> Moverse entre Campos");  
    }
     
     public void numeroRegistros() {
        jtxt_registros .setText(String.valueOf(registros));
    }
     
    private void centrarJDialog(){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension DilaogSize = this.getSize();
            if (DilaogSize.height > screenSize.height) {
                DilaogSize.height = screenSize.height;
            }
            if (DilaogSize.width > screenSize.width) {
                DilaogSize.width = screenSize.width;
            }
            this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
       
    }
}
