package vista;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import controlador.Accion;
import controlador.Change;
import java.awt.Color;
import javafx.beans.value.ChangeListener;
import javax.swing.SwingConstants;

public class Frame extends JFrame 
{
public Container contenedor;
public static JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
public static JButton boton1, boton2;
public static JLabel etiqueta1, etiqueta2, etiqueta3, etiqueta4, etiqueta5, etiqueta6;
public static JTextField texto1;
public static JSlider barra;
public Border bordePanel;
public static JFreeChart grafica1, grafica2, grafica3;
public static XYDataset datos1, datos2, datos3;
public XYSeries serie1, serie2, serie3;
public int x;
private Accion ac;
//public DefaultCategoryDataset datos1, datos2, datos3;
public static ChartPanel chartpanel1, chartpanel2, chartpanel3; 

public Frame()
{
	super("Caida");
	
	contenedor=getContentPane();
	contenedor.setLayout(new BorderLayout());
	
	crearPanel1();
	crearPanel2();
	crearPanel3();

        
        contenedor.add(panel1, BorderLayout.WEST);
        contenedor.add(panel2, BorderLayout.CENTER);
        contenedor.add(panel3, BorderLayout.EAST);
        
	
        setSize(700,450);
	//pack();
	setResizable(false);
	setVisible(true);
	this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
}

public void crearPanel1()
{
	panel1= new JPanel();
        panel1.setLayout(new BorderLayout());
        crearPanel4();
	crearPanel5();
        panel1.add(panel4, BorderLayout.NORTH);
        panel1.add(panel5, BorderLayout.CENTER);
}

public void crearPanel2()
{
	panel2= new JPanel();
        panel2.setBorder(BorderFactory.createRaisedBevelBorder());
}

public void crearPanel3()
{
	panel3= new JPanel();
        panel3.setLayout(new GridLayout(3,1));
        panel3.setBorder(BorderFactory.createRaisedBevelBorder());
        crearPanel6();
	crearPanel7();
	crearPanel8();
        panel3.add(panel6);
        panel3.add(panel7);
        panel3.add(panel8);
        
}

public void crearPanel4()
{
	panel4= new JPanel();
        panel4.setLayout(new GridLayout(5,1));
        panel4.setBorder(BorderFactory.createRaisedBevelBorder());
        ac=new Accion();
        boton1= new JButton("Soltar");
        boton1.addActionListener(ac);
        boton2= new JButton("Reiniciar");
        etiqueta1= new JLabel("Posición (Metros)");
        texto1= new JTextField(0);
        texto1.setEnabled(false);
        texto1.setDisabledTextColor(Color.BLACK);
        
        barra= new JSlider(SwingConstants.HORIZONTAL, 50, 350, 50);
        barra.setMajorTickSpacing(50);
        barra.setPaintTicks(true);
        barra.setPaintLabels(true);
        Change change=new Change();
        barra.addChangeListener(change);
        
        
        panel4.add(boton1);
        panel4.add(boton2);
        panel4.add(etiqueta1);
        panel4.add(texto1);
        panel4.add(barra);
}

public void crearPanel5()
{
	panel5= new JPanel();
        panel5.setLayout(new GridLayout(5,1));
        panel5.setBorder(BorderFactory.createRaisedBevelBorder());
        etiqueta2= new JLabel("Valores");
        etiqueta3= new JLabel("e="+0+"m");
        etiqueta4= new JLabel("y=50m");
        etiqueta5= new JLabel("v="+0+"m/s");
        etiqueta6= new JLabel("g=-9.8m/s^2");
        panel5.add(etiqueta2);
        panel5.add(etiqueta3);
        panel5.add(etiqueta4);
        panel5.add(etiqueta5);
        panel5.add(etiqueta6);
}

public void crearPanel6()
{
	panel6= new JPanel();
        panel6.setLayout(new FlowLayout());
        panel6.setBorder(BorderFactory.createRaisedBevelBorder());
        grafica1= ChartFactory.createXYLineChart("Ejemplo", "v2", "v3", datos1, PlotOrientation.HORIZONTAL,true, true, false);
        chartpanel1= new ChartPanel(grafica1);
        chartpanel1.setPreferredSize(new Dimension(225,130));
        panel6.add(chartpanel1);
}

public void crearPanel7()
{
	panel7= new JPanel();
        panel7.setLayout(new FlowLayout());
        panel7.setBorder(BorderFactory.createRaisedBevelBorder());
        grafica2= ChartFactory.createXYLineChart("Ejemplo", "v2", "v3", datos2, PlotOrientation.HORIZONTAL,true, true, false);
        chartpanel2= new ChartPanel(grafica2);
        chartpanel2.setPreferredSize(new Dimension(225,130));
        panel7.add(chartpanel2);
}

public void crearPanel8()
{
	panel8= new JPanel();
        panel8.setLayout(new FlowLayout());
        panel8.setBorder(BorderFactory.createRaisedBevelBorder());
        grafica3= ChartFactory.createXYLineChart("Ejemplo", "v2", "v3", datos3, PlotOrientation.HORIZONTAL,true, true, false);
        chartpanel3= new ChartPanel(grafica3);
        chartpanel3.setPreferredSize(new Dimension(225,130));
        panel8.add(chartpanel3);
}

}
