/*
 * jdlg_actualizaOcupacion.java
 *
 * Created on 16 de octubre de 2007, 12:30 PM
 */

package tmspuertas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.print.PrintService;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import tms_TextFields.JCuantityTextField;
import tms_TextFields.JNumberTextField;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_actualizaOcupacion extends javax.swing.JDialog {
    
    /**
     * Creates new form jdlg_actualizaOcupacion
     */
    public jdlg_actualizaOcupacion(Vector tip, Vector oc, Vector dt,TmsPuertasManagedBean pbusquedas, int pcapacidad,boolean pautorizacion,boolean pautorizacion2, long pntarpen,boolean premoto, String pDBLink, String ppuerto,PrintService pimpresora, String pDBLinkCentral, long pedotar, Vector pvEdosAcc,String pipAS,int pportAS,Vector pdatosIniciales) {
        this.setTitle("Actualizacion de Ocuapcion de la corrida");
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.impresora = pimpresora;
        this.puerto = ppuerto;
        this.datosIniciales = pdatosIniciales;
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension DilaogSize = this.getSize();
        if (DilaogSize.height > screenSize.height) {
            DilaogSize.height = screenSize.height;
        }
        if (DilaogSize.width > screenSize.width) {
            DilaogSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - DilaogSize.width ) / 2, ( screenSize.height - DilaogSize.height ) / 2 );            this.setDefaultLookAndFeelDecorated(true);
        this.ocupacion = oc;
        this.tipos = tip;
        this.datosTarjeta = dt;
        this.busquedas = pbusquedas;
        this.capacidad = pcapacidad;
        this.autorizacion = pautorizacion;
        this.autorizacion2 = pautorizacion2;
        this.ntarpen = pntarpen;
        this.remoto = premoto;
        this.DBLink = pDBLink;
        this.DBLinkCentral = pDBLinkCentral;
        this.edotar = pedotar;
        this.vEdosAcc= pvEdosAcc;
        this.ipAS =  pipAS;
        this.portAS = pportAS;
        System.out.println("DatosTarjeta: "+datosTarjeta);
        jtbl_ocupacion.setModel(modeloDetalleAbordo);
        resizeColumnasOcupacion();
        jlbl_barraEstado.setText("<html>  <font color=FF0000>F2: </font> Si, Despachar Tarjeta                 <font color=FF0000>             ESC: </font> No  </html>");
        jlbl_barraEstado.setHorizontalAlignment( JTextField.CENTER );
        ocupacionSistema = new Object[tipos.size()][4];
        for(int i=0; i<tipos.size();i++){
            Vector vtipo = (Vector) tipos.get(i);
            String letraTipo = vtipo.get(1).toString();
             System.out.println("Letra : "+letraTipo);
            int conteo = 0;
            float total = 0;
            for(int j=0; j<ocupacion.size(); j++){
                Vector vocupa = (Vector) ocupacion.get(j);
                if(letraTipo.equals(vocupa.get(4).toString()))
                {
                   conteo++;
                   total = total + Float.valueOf(vocupa.get(5).toString());
                }
            }

         ocupacionSistema[i][0]=vtipo.get(0).toString();   
         ocupacionSistema[i][1]=""+conteo;   
         ocupacionSistema[i][2]=""+total;   
        }
        modeloDetalleAbordo.setDataVector(ocupacionSistema,encabezadoDetalle);
        //jtbl_ocupacion.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
        jtbl_ocupacion.setSurrendersFocusOnKeystroke(true);
        jtbl_ocupacion.setFocusTraversalKeysEnabled(false);
        resizeColumnasOcupacion();
        if(jtbl_ocupacion.getRowCount()>0)
        {
            jtbl_ocupacion.setRowSelectionInterval(0,0);
            jtbl_ocupacion.setColumnSelectionInterval(1,1);
        }
    }
    
    private void resizeColumnasOcupacion(){
        TableColumn columinv = jtbl_ocupacion.getColumnModel().getColumn(0); columinv.setMinWidth( 140 );columinv.setMaxWidth( 140 );columinv.setPreferredWidth(140);                  
                    columinv = jtbl_ocupacion.getColumnModel().getColumn(1); columinv.setMinWidth( 60 );columinv.setMaxWidth( 60 );columinv.setPreferredWidth(60);                  
                    columinv = jtbl_ocupacion.getColumnModel().getColumn(2); columinv.setMinWidth( 80 );columinv.setMaxWidth( 80 );columinv.setPreferredWidth(80);                  
        cantidad.setHorizontalAlignment(JTextField.RIGHT);
        monto.setHorizontalAlignment(JTextField.RIGHT);
        TableColumn cantidadColumn = jtbl_ocupacion.getColumnModel().getColumn(1);
        cantidadColumn.setCellEditor(new DefaultCellEditor(cantidad));
        TableColumn montoColumn = jtbl_ocupacion.getColumnModel().getColumn(2);
        montoColumn.setCellEditor(new DefaultCellEditor(monto));
        TableCellRenderer centerRenderero = new CenterRenderer();
        montoColumn.setCellRenderer( centerRenderero );
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_ocupacion = new javax.swing.JTable();
        jtbl_ocupacion = new JTable()
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
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jtbl_ocupacion.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbl_ocupacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_ocupacionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbl_ocupacionKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_ocupacion);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Actualiza la ocupacion del autobus");

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("esxa");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 284, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(22, 22, 22)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 241, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 47, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbl_ocupacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_ocupacionKeyPressed
        if(evt.getKeyCode() == evt.VK_F2 || evt.getKeyCode() == evt.VK_ESCAPE)
            jtbl_ocupacion.setColumnSelectionInterval(0,0);
    }//GEN-LAST:event_jtbl_ocupacionKeyPressed

    private void jtbl_ocupacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_ocupacionKeyReleased
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
        if(evt.getKeyCode() == evt.VK_F2)
        {
            for(int i=0; i<jtbl_ocupacion.getRowCount();i++)
            {
                if(jtbl_ocupacion.getValueAt(i,1).equals("") || jtbl_ocupacion.getValueAt(i,2).equals(""))
                {
                    new jdlg_error("¡Debes introducir todos los valores!","","Error de datos").setVisible(true);
                    return;
                }
                int nb  = Integer.valueOf(jtbl_ocupacion.getValueAt(i,1).toString());
                double imp = Double.valueOf(jtbl_ocupacion.getValueAt(i,2).toString());
                if(imp>0 && nb<=0)
                {
                    new jdlg_error("¡El importe no puede ser mayor a cero sin tener boletos!","","Error de datos").setVisible(true);
                    return;
                }
                
                if(jtbl_ocupacion.getValueAt(i,1).toString().equals("") || jtbl_ocupacion.getValueAt(i,2).toString().equals(""))
                {
                    new jdlg_error("¡Debes introducior todas las cantidades!","","Datos faltantes").setVisible(true);
                    return;
                }
            }
            this.dispose();
            //VAGL 31102011
            Vector vec = new Vector();
            Vector v = modeloDetalleAbordo.getDataVector();
            for(int i=0; i<v.size(); i++)
            { 
                Vector vtmp = (Vector)v.get(i);
                if(Integer.valueOf(vtmp.get(1).toString())>0 || vtmp.get(0).toString().equals("ESTUDIANTE") || vtmp.get(0).toString().equals("ADULTO") || vtmp.get(0).toString().equals("MENOR") || vtmp.get(0).toString().equals("SENECTUD") || vtmp.get(0).toString().equals("PROFESOR"))
                    vec.add(vtmp);
            }
            new Jdlg_vistaPreviaTarjetaViaje(capacidad,ocupacion,tipos,datosTarjeta,busquedas,vec,new Object[5][5],2,autorizacion,autorizacion2,ntarpen,remoto, DBLink, puerto, impresora, DBLinkCentral, edotar, vEdosAcc, this.ipAS, this.portAS, this.datosIniciales).setVisible(true);  
        }
    }//GEN-LAST:event_jtbl_ocupacionKeyReleased
    
    /**
     * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                Vector tps = new Vector();
//                Vector ocp = new Vector(); 
//                Vector stps = new Vector();
//                stps.add("ADULTO");stps.add("A");tps.add(stps);
//                 stps = new Vector();
//                stps.add("MENOR");stps.add("M");tps.add(stps);
//                 stps = new Vector();
//                stps.add("SENECTUD");stps.add("S");tps.add(stps);
//                 stps = new Vector();
//                stps.add("ESTUDIANTE");stps.add("E");tps.add(stps);
//                 stps = new Vector();
//                stps.add("PROFESOR");stps.add("P");tps.add(stps);
//                 stps = new Vector();
//                stps.add("ESPECIAL");stps.add("L");tps.add(stps);
//                Vector socp = new Vector();
//                socp.add("171");socp.add("3");socp.add("TAPO");socp.add("AA 171");socp.add("E");socp.add("85");ocp.add(socp);
//                socp = new Vector();
//                socp.add("172");socp.add("4");socp.add("TAPO");socp.add("AA 172");socp.add("E");socp.add("85");ocp.add(socp);
//                socp = new Vector();
//                socp.add("173");socp.add("5");socp.add("TAPO");socp.add("AA 173");socp.add("P");socp.add("85");ocp.add(socp);
//                socp = new Vector();
//                socp.add("174");socp.add("6");socp.add("TAPO");socp.add("AA 174");socp.add("S");socp.add("85");ocp.add(socp);
//                System.out.println("Vector tps: "+tps);
//                System.out.println("Vector ocp: "+ocp);
//                new jdlg_actualizaOcupacion(tps,ocp).setVisible(true);
            }
        });
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JTable jtbl_ocupacion;
    // End of variables declaration//GEN-END:variables
    private JNumberTextField cantidad = new JNumberTextField();
    private JCuantityTextField monto = new JCuantityTextField();
    private Vector tipos = new Vector();
    private Vector ocupacion = new Vector();
    private Object[] encabezadoDetalle = {"TIPO PAS","# BOL","TOTAL"};
    private Object[][] ocupacionSistema;
    private DefaultTableModel modeloDetalleAbordo = new DefaultTableModel(null,encabezadoDetalle){
    public boolean isCellEditable (int row, int column)
        {
            // Aquí devolvemos true o false según queramos que una celda
            // identificada por fila,columan (row,column), sea o no editable
            if (column == 0)
               return false;
            return true;
        }   
    };

    private Vector datosTarjeta;
    private TmsPuertasManagedBean busquedas;
    private int capacidad;
    private boolean autorizacion;
    private boolean autorizacion2;
    private long ntarpen;
    private boolean remoto;
    private String DBLink;
    private PrintService impresora;
    private String puerto;
    private String DBLinkCentral;

    private long edotar;

    private Vector vEdosAcc;

    private int portAS;

    private String ipAS;

    private Vector datosIniciales;
   
    
           //************ Clase para Justificar el Texto de las Celdas a la derecha
                class CenterRenderer extends DefaultTableCellRenderer {
                       public CenterRenderer()
                       {
                           setHorizontalAlignment( RIGHT );
                       }
                
                       public Component getTableCellRendererComponent(
                           JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
                       {
                           super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                           return this;
                       }
                   }    
    
}
