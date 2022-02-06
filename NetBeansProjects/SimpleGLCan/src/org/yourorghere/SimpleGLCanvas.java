

package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCapabilities;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.media.opengl.GLCanvas;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class SimpleGLCanvas extends JFrame {

    DefaultTableModel tableModel;
    
    static {
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    }
    
    private Animator animator;

    public SimpleGLCanvas() {
        
        tableModel = new DefaultTableModel();             
        initComponents();
        setTitle("Paracaidista");
        GLCanvas canvas=new GLCanvas();
        canvas.addGLEventListener(new GLRenderer());
        animator = new Animator(canvas);

        canvas.setMinimumSize(new Dimension());    
        tableModel.addColumn("n");
        tableModel.addColumn("v(n)");
        tableModel.addColumn("&v(n)-g");
        tableModel.addColumn("v(n+1)");
        tableModel.addColumn("y(n)");
        tableModel.addColumn("y(n+1)*Dt");
        tableModel.addColumn("y(n+1)");
        jTable1.setModel(tableModel);
        
        this.addWindowListener(new WindowAdapter() {
         
           
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
    }

    public void setVisible(boolean show){
        if(!show)
            animator.stop();
        super.setVisible(show);
        if(!show)
            animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLabel label = new JLabel();
        numero = new JTextField();
        delta = new JTextField();
        gama = new JTextField();
        grave = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        panelGraficoTorta = new JPanel();
        lblTorta = new JLabel();
        panelGraficoTorta2 = new JPanel();
        lblTorta2 = new JLabel();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label.setFont(new Font("MS Reference Sans Serif", 1, 12)); // NOI18N
        label.setText("Animacion del Paracaidista");

        jLabel1.setText("N° de N:");

        jLabel2.setText("Delta:");

        jLabel3.setText("Gama:");

        jLabel4.setText("Gravedad:");

        jButton1.setFont(new Font("Vani", 1, 12)); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new Font("Vani", 1, 12)); // NOI18N
        jButton2.setText("Cancelar");

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        panelGraficoTorta.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 153), 3));

        GroupLayout panelGraficoTortaLayout = new GroupLayout(panelGraficoTorta);
        panelGraficoTorta.setLayout(panelGraficoTortaLayout);
        panelGraficoTortaLayout.setHorizontalGroup(panelGraficoTortaLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, panelGraficoTortaLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(lblTorta, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
        );
        panelGraficoTortaLayout.setVerticalGroup(panelGraficoTortaLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(lblTorta, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
        );

        panelGraficoTorta2.setBorder(BorderFactory.createLineBorder(new Color(51, 204, 255), 3));

        GroupLayout panelGraficoTorta2Layout = new GroupLayout(panelGraficoTorta2);
        panelGraficoTorta2.setLayout(panelGraficoTorta2Layout);
        panelGraficoTorta2Layout.setHorizontalGroup(panelGraficoTorta2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, panelGraficoTorta2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTorta2, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
        );
        panelGraficoTorta2Layout.setVerticalGroup(panelGraficoTorta2Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(lblTorta2, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );

        jButton3.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Limpiar");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setText("Grafica");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new Font("Mongolian Baiti", 1, 12)); // NOI18N
        jButton5.setText("Grafica");
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(gama, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(grave, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(numero, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(delta, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)))
                .addGap(388, 388, 388)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(panelGraficoTorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelGraficoTorta2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(numero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(delta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                    .addComponent(gama, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                    .addComponent(grave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jButton3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelGraficoTorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(12, 12, 12)
                        .addComponent(panelGraficoTorta2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        double gama1=0;
    	double delta1=0;
    	int gravedad=0;
        double  n=0;
    	double  v=0;
        double mul=0,mult=0,ye=0,yn=0;
          int x;
        
        gama1=Double.parseDouble(gama.getText());
        delta1=Double.parseDouble(delta.getText());
        gravedad=Integer.parseInt(grave.getText());
        x=Integer.parseInt(numero.getText());
      
        
    	for(int i=0;i<=x;i++){
    	    
            mul=((gama1*n)-(gravedad))*(delta1);
    	    v=n+mul;

            mult=v*delta1;
            ye=mult+yn;
            Vector datos = new Vector();
     
datos.addElement(i);
datos.addElement(n);
datos.addElement(mul);
datos.addElement(v);
datos.addElement(yn);
datos.addElement(mult);
datos.addElement(ye);
    	    
tableModel.addRow(datos);
jTable1.setModel(tableModel);
n=v;
yn=ye;
    	    System.out.println("Valores V(n+1) son: " +v);
             System.out.println("Multiplicados.................!!!!! " +mul);   
    	}        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       tableModel.setRowCount(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
        XYSeries series = null;
        XYDataset datos;
        JFreeChart linea = null;
         int x=0;
         
          double gama1=0;
    	double delta1=0;
    	int gravedad=0,i;
        double  n=0;
    	double  v=0;
        double mul=0;
          
        
        gama1=Double.parseDouble(gama.getText());
        delta1=Double.parseDouble(delta.getText());
        gravedad=Integer.parseInt(grave.getText());
        x=Integer.parseInt(numero.getText());

        series= new XYSeries("Velocidad del objeto");
    
        series.add(0,x);
        
      for( i=0;i<=x;i++){
    	    
            mul=((gama1*n)-(gravedad))*(delta1);
    	    v=n+mul;
              n=v;
              series.add(i,(v*(-1)));
          } 
        series.add(Integer.parseInt(numero.getText()),i);
        
       
        datos = new XYSeriesCollection(series);
        linea = ChartFactory.createXYLineChart("Grafica","y(t)","t",datos,PlotOrientation.HORIZONTAL,true,true,true);
       
        BufferedImage graficoLinea=linea.createBufferedImage(panelGraficoTorta.getWidth(), panelGraficoTorta.getHeight());
       
        lblTorta.setSize(panelGraficoTorta.getSize());
        lblTorta.setIcon(new ImageIcon(graficoLinea));
      
        panelGraficoTorta.updateUI();
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        XYSeries series = null;
        XYDataset datos;
        JFreeChart linea = null;
         int x=0;
         
          double gama1=0;
    	double delta1=0;
    	int gravedad=0,i;
        double  n=0;
    	double  v=0;
        double mul=0;
          
        
        gama1=Double.parseDouble(gama.getText());
        delta1=Double.parseDouble(delta.getText());
        gravedad=Integer.parseInt(grave.getText());
        x=Integer.parseInt(numero.getText());

        series= new XYSeries("Posicion del objeto");
    
        series.add(0,x);
        
      for( i=0;i<=x;i++){
    	    
            mul=((gama1*n)-(gravedad))*(delta1);
    	    v=n+mul;
              n=v;
              series.add(i,(v*(-1)));
          } 
        series.add(Integer.parseInt(numero.getText()),i);
        
       
        datos = new XYSeriesCollection(series);
        linea = ChartFactory.createXYLineChart("Grafica","y(t)","t",datos,PlotOrientation.HORIZONTAL,true,true,true);
       
        BufferedImage graficoLinea=linea.createBufferedImage(panelGraficoTorta2.getWidth(), panelGraficoTorta2.getHeight());
       
        lblTorta2.setSize(panelGraficoTorta2.getSize());
        lblTorta2.setIcon(new ImageIcon(graficoLinea));
      
        panelGraficoTorta2.updateUI();
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private GLCapabilities createGLCapabilites() {
        
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setHardwareAccelerated(true);

        capabilities.setNumSamples(2);
        capabilities.setSampleBuffers(true);
        
        return capabilities;
    }
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch(Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "can not enable system look and feel", ex);
                }
                SimpleGLCanvas frame = new SimpleGLCanvas();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField delta;
    private JTextField gama;
    private JTextField grave;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JLabel lblTorta;
    private JLabel lblTorta2;
    private JTextField numero;
    private JPanel panelGraficoTorta;
    private JPanel panelGraficoTorta2;
    // End of variables declaration//GEN-END:variables

}
