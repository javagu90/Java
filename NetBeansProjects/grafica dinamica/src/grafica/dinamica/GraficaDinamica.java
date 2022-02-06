import java.awt.BorderLayout;
 
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.DynamicTimeSeriesCollection;
 
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Random;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
 
public class FormularioPrincipal extends JFrame {
 
	public FormularioPrincipal() {
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new PanelGraphicaEnTiempoReal());
	}
 
	public static void main(String[] args) {
		FormularioPrincipal frmPrincipal = new FormularioPrincipal();
		frmPrincipal.setVisible(true);
	}
 
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
 
			contenidoSerieDinamica.setTimeBase(new Second(segundo=0,minuto=0,hora=0,dia=1,mes=1,año=2013));//pone el primer instante
			contenidoSerieDinamica.addSeries(valoresDeLaSerie(), indiceSerie=0,nombreDeLaSerie="ventas");//agrega una serie
 
			graphicaDeTiempo = ChartFactory.createTimeSeriesChart(
											getTitulo(),
											getEtiquetaDelasX(),
											getEtiquetaDelasY(),
											contenidoSerieDinamica,
											tieneLeyenda(),
											tienToolTip(),
											tineUrl());//crea una grafica de tiempo
 
			ChartPanel panelDeLaGraphica = new ChartPanel(graphicaDeTiempo);//crea un panel para graficas
 
 
			add(panelDeLaGraphica);
 
			cronometro.start();
		}
 
		private float[] valoresDeLaSerie() {
			float[] respuesta = new float[getCantidadPorSerie()];
			Random creadorDeAleatorios = new Random();
			for (int i = 0; i < respuesta.length; i++) {
				respuesta[i]=creadorDeAleatorios.nextFloat();//crea un aleatorio;
			}
			return respuesta;
		}
 
		@Override
		public void actionPerformed(ActionEvent e) {
			contenidoSerieDinamica.advanceTime();//avansa el tiempo
			contenidoSerieDinamica.appendData(new float[]{generadorDeAleatorios.nextFloat()});//agrega un aleatorio
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
 
			return "ventas";
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
 
}