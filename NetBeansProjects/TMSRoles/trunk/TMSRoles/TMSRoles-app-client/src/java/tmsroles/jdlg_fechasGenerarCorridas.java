/*
 * jdlg_fechasGenerarCorridas.java
 *
 * Created on 20 de noviembre de 2007, 12:26 PM
 */

package tmsroles;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JRootPane;

/**
 *
 * @author  vgonzalez
 */
public class jdlg_fechasGenerarCorridas extends javax.swing.JDialog {
    
    /** Creates new form jdlg_fechasGenerarCorridas */
    public jdlg_fechasGenerarCorridas(){
        this.setTitle("Fechas para la Generacion de Corridas");
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
            this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        
        jtxt_fechaicorridas.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechaicorridas_inicialKeyPressed(evt);
            }
        });
        jtxt_fechafcorridas.dateEditor.addKeyListener1(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_fechafcorridas_inicialKeyPressed(evt);
            }
        });
        jlbl_barraEstado.setText("<html>  <font color=FF0000>ESCAPE: </font> Cancelar | <font color=FF0000>ENTER: </font> Aceptar </html>");
        jtxt_fechaicorridas.dateEditor.setFoco();
    }
    
    private void jtxt_fechaicorridas_inicialKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() == evt.VK_ENTER)
            ;//generarCorridas();
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }
    
    private void jtxt_fechafcorridas_inicialKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if(evt.getKeyCode() == evt.VK_ENTER)
            ;//generarCorridas();
        if(evt.getKeyCode() == evt.VK_ESCAPE)
            this.dispose();
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_fechaicorridas = new tms_calendario.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jtxt_fechafcorridas = new tms_calendario.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_rutas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jlbl_barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fechas de Generaci\u00f3n de Corridas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Fecha desde:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("hasta:");

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
        jtbl_rutas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbl_rutasKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(jtbl_rutas);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Rutas");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtxt_fechaicorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(14, 14, 14)
                .add(jLabel2)
                .add(20, 20, 20)
                .add(jtxt_fechafcorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(21, 21, 21)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 337, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(410, Short.MAX_VALUE)
                .add(jLabel3)
                .add(280, 280, 280))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(53, 53, 53)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jtxt_fechafcorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel2)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jLabel1)
                                    .add(jtxt_fechaicorridas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jlbl_barraEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlbl_barraEstado.setText("jLabel3");
        jlbl_barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jlbl_barraEstado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbl_rutasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbl_rutasKeyPressed
// TODO add your handling code here:
    }//GEN-LAST:event_jtbl_rutasKeyPressed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbl_barraEstado;
    private javax.swing.JTable jtbl_rutas;
    private tms_calendario.JDateChooser jtxt_fechafcorridas;
    private tms_calendario.JDateChooser jtxt_fechaicorridas;
    // End of variables declaration//GEN-END:variables
    
}
