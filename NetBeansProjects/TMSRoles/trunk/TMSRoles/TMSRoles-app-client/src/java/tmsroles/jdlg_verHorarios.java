/*
 * jdlg_verHorarios.java
 *
 * Created on 7 de noviembre de 2007, 09:02 PM
 */

package tmsroles;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_verHorarios extends javax.swing.JDialog {
    
    /** Creates new form jdlg_verHorarios */
    public jdlg_verHorarios(TmsRolesManagedBean pbusquedas,long idSer, long idRuta, String oferta){
        this.setTitle("Horarios de la Oferta por Ruta");
        this.busquedas = pbusquedas;
        this.setModal(true);
        this.setDefaultLookAndFeelDecorated(true);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
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
        jtbl_horarios.setModel(modeloHorarios);
        Vector  vHorarios = (Vector)busquedas.variosFacadeRemote.buscaHorarios(idSer,idRuta,oferta);
        Object[][] datos = new Object[vHorarios.size()][11];
        for(int i=0; i<vHorarios.size();i++){
            Vector  horario = (Vector)vHorarios.get(i);
            datos[i][0] = horario.get(0).toString();
            datos[i][1] = horario.get(1).toString();
            datos[i][2] = horario.get(2).toString();
            datos[i][3] = horario.get(3).toString();
            if(horario.get(4).toString().equals("S")) datos[i][4] = true; else datos[i][4] = false;
            if(horario.get(5).toString().equals("S")) datos[i][5] = true; else datos[i][5] = false;
            if(horario.get(6).toString().equals("S")) datos[i][6] = true; else datos[i][6] = false;
            if(horario.get(7).toString().equals("S")) datos[i][7] = true; else datos[i][7] = false;
            if(horario.get(8).toString().equals("S")) datos[i][8] = true; else datos[i][8] = false;
            if(horario.get(9).toString().equals("S")) datos[i][9] = true; else datos[i][9] = false;
            if(horario.get(10).toString().equals("S")) datos[i][10] = true; else datos[i][10] = false;
            
        }
jlbl_barraEstado.setText("<html>  <font color=FF0000> ESC </font> Cerrar </html>");          
        modeloHorarios.setDataVector(datos, encabezadosHorarios);
        resizeColumnasRutas();
        jtbl_horarios.setColumnSelectionInterval(0,0);
        jtbl_horarios.setRowSelectionInterval(0,0);
        jtbl_horarios.requestFocus();
    }
    
    
      private void resizeColumnasRutas(){
        TableColumn columinv = jtbl_horarios.getColumnModel().getColumn(0); columinv.setMinWidth( 55 );columinv.setMaxWidth( 55 );columinv.setPreferredWidth(55);
        columinv = jtbl_horarios.getColumnModel().getColumn(1); columinv.setMinWidth(105);columinv.setMaxWidth(105);columinv.setPreferredWidth(105);
        columinv = jtbl_horarios.getColumnModel().getColumn(2); columinv.setMinWidth(105);columinv.setMaxWidth(105);columinv.setPreferredWidth(105);
        columinv = jtbl_horarios.getColumnModel().getColumn(3); columinv.setMinWidth(122);columinv.setMaxWidth(122);columinv.setPreferredWidth(122);
        columinv = jtbl_horarios.getColumnModel().getColumn(4); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(5); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(6); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(7); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(8); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(9); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
        columinv = jtbl_horarios.getColumnModel().getColumn(10); columinv.setMinWidth(35);columinv.setMaxWidth(35);columinv.setPreferredWidth(35);
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
        jtbl_horarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_horariosKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_horarios);

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel1");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 265, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 19, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbl_horariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_horariosKeyPressed
        
        
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }//GEN-LAST:event_jtbl_horariosKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JTable jtbl_horarios;
    // End of variables declaration//GEN-END:variables
    private Object[] encabezadosHorarios = {"Hora","Origen","Destino","Empresa","Lun","Mar","Mie","Jue","Vie","Sab","Dom"};
    private DefaultTableModel modeloHorarios = new DefaultTableModel(null,encabezadosHorarios){
            public boolean isCellEditable(int row, int column) {
            if (column == 30  )
               return true;
            return false;
            }
            public Class getColumnClass(int c) { 
               return getValueAt(0, c).getClass(); }
        };
    private TmsRolesManagedBean busquedas;
}
