package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static vista.Frame.*;

public class Accion implements ActionListener {
    
    
    	
	
 
	private static class PanelGraphicaEnTiempoReal extends JPanel implements ActionListener {
 
		private DynamicTimeSeriesCollection contenidoSerieDinamica;
		private JFreeChart graphicaDeTiempo;
		private Timer cronometro;
		private Random generadorDeAleatorios;
 
		private int segundo;
		private int minuto;
		private int hora;
		private int dia;
		private int mes;
		private int año;
		private int indiceSerie;
		private String nombreDeLaSerie;
 
 
 
		public PanelGraphicaEnTiempoReal() {
 
			generadorDeAleatorios=new Random();
			cronometro=new Timer(200,this); 
 
 
			contenidoSerieDinamica = new DynamicTimeSeriesCollection(
											getCantidadDeSeries(),
											getCantidadPorSerie(),
											enSegundos());//crea el contenido de la serie dinamica
 
			contenidoSerieDinamica.setTimeBase(new Second(segundo=0,minuto=0,hora=0,dia=1,mes=1,año=2015));//pone el primer instante
			contenidoSerieDinamica.addSeries(valoresDeLaSerie(), indiceSerie=0,nombreDeLaSerie="Grafico 1");//agrega una serie
 
			graphicaDeTiempo = ChartFactory.createTimeSeriesChart(
											getTitulo(),
											getEtiquetaDelasX(),
											getEtiquetaDelasY(),
											contenidoSerieDinamica,
											tieneLeyenda(),
											tienToolTip(),
											tineUrl());//crea una grafica de tiempo
 
			chartpanel1 = new ChartPanel(graphicaDeTiempo);//crea un panel para graficas
                        chartpanel1.setPreferredSize(new Dimension(225,130));
 
 
			panel6.add(chartpanel1);
 
			cronometro.start();
		}
 
                /* Aqui hay que modificar con la formula de caida libre*/
                
		private float[] valoresDeLaSerie() {
			float[] respuesta = new float[getCantidadPorSerie()];
			Random creadorDeAleatorios = new Random();
			for (int i = 0; i < respuesta.length; i++) {
				respuesta[i]=creadorDeAleatorios.nextFloat();//crea un aleatorio;
			}
			return respuesta;
		}
 //fin de la formula de caida libre 
                
		@Override
		public void actionPerformed(ActionEvent e) {
			contenidoSerieDinamica.advanceTime();//avansa el tiempo
                        cronometro.stop();
			//contenidoSerieDinamica.appendData(new float[]{generadorDeAleatorios.nextFloat()});//agrega un aleatorio
                    }
		private boolean tineUrl() {
			return false;
		}
 
		private boolean tienToolTip() {
			return true;
		}
 
		private boolean tieneLeyenda() {
			return true;
		}
 
		private String getEtiquetaDelasY() {
 
			return "ventas";
		}
 
		private String getEtiquetaDelasX() {
			return "hh:mm:ss";
		}
 
		public String getTitulo() {
 
			return "Grafico 1";
		}
 
		private Second enSegundos() {
			return new Second();
		}
 
		public int getCantidadPorSerie() {
 
			return 120;
		}
 
		public int getCantidadDeSeries() {
			return 1;
		}
 
 
	}
 

	public void actionPerformed(ActionEvent e) {
            
           
		// TODO Auto-generated method stub
            if(e.getSource()==boton1)
            {
                JOptionPane.showMessageDialog(null, "Hola");
                
                
                 XYSeries series = null;
                // XYDataset datos;
                 int x=0;

                 series= new XYSeries("Popularidad de Blog");
                 
           
                series.add(0,x);
                x++;
              
                series.add(Integer.parseInt(texto1.getText()),x);
                x++;
                
                series.add(7,x);
                x++;
                
                series.add(10,x);
                x++;
                
                series.add(30,x);
                x++;
                
       
                datos1 = new XYSeriesCollection(series);
                grafica1 = ChartFactory.createXYLineChart("Ejemplo Grafico de Linea","Popularidad","Meses",datos1,PlotOrientation.HORIZONTAL,true,true,true);
                panel6.removeAll();
                panel6.revalidate();
                //chartpanel1 = new ChartPanel(grafica1);
                new PanelGraphicaEnTiempoReal();
                //panel6.add(chartpanel1);
        
                datos2 = new XYSeriesCollection(series);
                grafica2 = ChartFactory.createXYLineChart("Ejemplo Grafico de Linea","Popularidad","Meses",datos2,PlotOrientation.HORIZONTAL,true,true,true);
                panel7.removeAll();
                panel7.revalidate();
                chartpanel2 = new ChartPanel(grafica2);
                chartpanel2.setPreferredSize(new Dimension(225,130));
                panel7.add(chartpanel2);
                
                datos3 = new XYSeriesCollection(series);
                grafica3 = ChartFactory.createXYLineChart("Ejemplo Grafico de Linea","Popularidad","Meses",datos3,PlotOrientation.HORIZONTAL,true,true,true);
                panel8.removeAll();
                panel8.revalidate();
                chartpanel3 = new ChartPanel(grafica3);
                chartpanel3.setPreferredSize(new Dimension(225,130));
                panel8.add(chartpanel3);
            }
		
	}
}
