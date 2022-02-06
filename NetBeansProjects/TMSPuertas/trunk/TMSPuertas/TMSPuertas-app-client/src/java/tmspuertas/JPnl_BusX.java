/*
 * JPnl_BusX.java
 *
 * Created on 11 de octubre de 2007, 11:56 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas;

/**
 *
 * @author vgonzalez
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import plantillaPTS.JCanvasPlantilla;
import plantillaPTS.cssConsultasPTS;
import plantillaPTS.drawAsiento;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import java.util.Vector;

import javax.swing.JPanel;
import tmspuertas.entidad.TmsAutobusPlantLineasTbl;
import tmspuertas.entidad.TmsAutobusPlantillasEncTbl;
import tmspuertas.entidad.TmsComponenteBusTbl;


/**
 * panel autobuses
 */
public class JPnl_BusX extends JPanel {
    private final int OCUPADO = 1;
    private final int RESERVADO = 2;
    private final int RESERVADONC = 3;

    /*false - no pinta asientos no disponibles
      true  - muestra la imagen asociada a los asientos no disponibles*/
    private boolean bVisibleOcupado = true;
    private boolean bVisibleReservado = true;
    private boolean bVisibleReservadoNC = true;
    
    private drawAsiento dAsiento;

    private JCanvasPlantilla jcnvBusPlantilla = new JCanvasPlantilla();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private cssConsultasPTS busVirtual;
    private int idplantilla;
    
    List<TmsComponenteBusTbl> componentes;
    TmsAutobusPlantillasEncTbl encabezado;
    List<TmsAutobusPlantLineasTbl> lineas;
    
    public JPnl_BusX(List<TmsComponenteBusTbl> acomponentes, TmsAutobusPlantillasEncTbl aencabezado, List<TmsAutobusPlantLineasTbl> alineas) {
        try {
            busVirtual = new cssConsultasPTS();
            this.componentes = acomponentes;
            this.encabezado = aencabezado;
            this.lineas = alineas;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPlantilla(List<TmsComponenteBusTbl> acomponentes,
                             TmsAutobusPlantillasEncTbl aencabezado,
                             List<TmsAutobusPlantLineasTbl> alineas){
        this.componentes = acomponentes;
        this.encabezado = aencabezado;
        this.lineas = alineas;
    }
            
    private void jbInit() throws Exception {
        this.setBackground(Color.WHITE);
        this.setLayout(gridBagLayout1);
        Dimension xDn=Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(new Dimension(225,514));//(int) (xDn.getHeight()-142))); //185,430
        this.setSize(new Dimension(227,515));//(int) (xDn.getHeight()-169)));
        this.getPlantillaDefault();
        this.add(jcnvBusPlantilla, 
                              new GridBagConstraints(0, 0, 1,1,//GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 
                              1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                              new Insets(0, 0, 0, 0), 0, 0));
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                this_componentResized(e);
            }
        });
    }
    // PLANTILLA DEFAULT
    public void getPlantillaDefault() {
        System.out.println("ancho alto"+this.getWidth()+"   "+this.getHeight());
        busVirtual.DefaultPlantilla(this.getWidth(), this.getHeight(), componentes, encabezado, lineas);
        jcnvBusPlantilla.setDatos(busVirtual.getPlantilla(), 
                       busVirtual.getCapacidad(), 
                       busVirtual.getAnchoAsiento(), 
                       busVirtual.getLargoAsiento(),
                this.bVisibleOcupado,this.bVisibleReservado,this.bVisibleReservadoNC);
        this.repaint();
    }
    // CONFIGURA SI LOS ASIENTOS NO DISPONIBLES SE MUESTRAN O NO

    public void setVisibleOcupado(boolean pbVisibleOcupado) {
        this.bVisibleOcupado = pbVisibleOcupado;
        jcnvBusPlantilla.setVisibleOcupado(this.bVisibleOcupado);
    }

    public void setVisibleReservado(boolean pbVisibleReservado) {
        this.bVisibleReservado = pbVisibleReservado;
        jcnvBusPlantilla.setVisibleReservado(this.bVisibleReservado);
    }

    public void setVisibleReservadoNC(boolean pbVisibleReservadoNC) {
        this.bVisibleReservadoNC = pbVisibleReservadoNC;
        jcnvBusPlantilla.setVisibleReservadoNC(this.bVisibleReservadoNC);
    }
    // DEFAULT PLANTILLA
    public void pintaPlantilla(long plantillaId) {
        idplantilla = (int)plantillaId;
        busVirtual.crearPlantilla(this.getWidth(), 
                                  this.getHeight()
                                  , componentes, encabezado, lineas);
        jcnvBusPlantilla.setDatos(busVirtual.getPlantilla(), 
                                  busVirtual.getCapacidad(), 
                                  busVirtual.getAnchoAsiento(), 
                                  busVirtual.getLargoAsiento(),
                                  this.bVisibleOcupado,this.bVisibleReservado,
                                  this.bVisibleReservadoNC);
            jcnvBusPlantilla.setVectorAsientos(busVirtual.OCUPADO, 
                                               busVirtual.getVectorAsientos(busVirtual.OCUPADO));
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADO, 
                                               busVirtual.getVectorAsientos(busVirtual.RESERVADO));
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADONC, 
                                               busVirtual.getVectorAsientos(busVirtual.RESERVADONC));
        this.repaint();
    }

    public void PlantillaCompleta(long plantillaId, Vector Ocupados, Vector Reservados, Vector ReservadosNC){      
        idplantilla = (int)plantillaId;
        busVirtual.crearPlantilla(this.getWidth(), 
                                  this.getHeight()
                                  , componentes, encabezado, lineas);
        jcnvBusPlantilla.setDatos(busVirtual.getPlantilla(), 
                                  busVirtual.getCapacidad(), 
                                  busVirtual.getAnchoAsiento(), 
                                  busVirtual.getLargoAsiento(),
        this.bVisibleOcupado,this.bVisibleReservado,this.bVisibleReservadoNC);
        this.setOcupados(Ocupados);
        this.setReservados(Reservados);
        this.setReservadNC(ReservadosNC);
    }
/*************** ASIENTO ************/
    public void setOcupados(Vector Ocupados) {
        int Size = Ocupados.size();
        int asiento = 0;
        for (int iasiento = 0; iasiento < Size; iasiento++) {
            asiento = Integer.parseInt(Ocupados.get(iasiento).toString());
            this.ocupaAsiento(asiento);
        }
        /*jcnvBusPlantilla.setVectorAsientos(busVirtual.OCUPADO, 
                                           busVirtual.getVectorAsientos(busVirtual.OCUPADO));*/

        this.repaint();
    }

    public void setReservados(Vector Reservados) {
        int Size = Reservados.size();
        int asiento = 0;
        for (int iasiento = 0; iasiento < Size; iasiento++) {
            asiento = Integer.parseInt(Reservados.get(iasiento).toString());
            this.reservaAsiento(asiento);
        }
        jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADO, 
                                           busVirtual.getVectorAsientos(busVirtual.RESERVADO));
        this.repaint();
    }

    public void setReservadNC(Vector ReservadosNC) {
        int Size = ReservadosNC.size();
        int asiento = 0;
        for (int iasiento = 0; iasiento < Size; iasiento++) {
            asiento = Integer.parseInt(ReservadosNC.get(iasiento).toString());
            this.reservaNCAsiento(asiento);
        }
        jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADONC, 
                                           busVirtual.getVectorAsientos(busVirtual.RESERVADONC));
        this.repaint();
    }

    public int getMaxOcupacion() {
        return busVirtual.getCapacidad();
    }

    /*****************************************/
    // PROCESO DE OCUPACION DE ASIENTOS
    public int ocupaAsiento(int asiento) {
        int r = busVirtual.setAsiento(asiento, OCUPADO);
        if (r == 0) {
            jcnvBusPlantilla.setVectorAsientos(busVirtual.OCUPADO, 
                                               busVirtual.getVectorAsientos(busVirtual.OCUPADO));
            //this.repaint();
        }
        return r;
    }

    public int reservaAsiento(int asiento) {
        int r=busVirtual.setAsiento(asiento, RESERVADO);
        if (r == 0) {
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADO, 
                                           busVirtual.getVectorAsientos(busVirtual.RESERVADO));
            this.repaint();
        }
        return r;
    }

    public int reservaNCAsiento(int asiento) {
        int r=busVirtual.setAsiento(asiento, RESERVADONC);
        if (r == 0) {
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADONC, 
                                           busVirtual.getVectorAsientos(busVirtual.RESERVADONC));
            //this.repaint();
        }
        return r;
    }

    // PROCESO DE DESOCUPACION DE ASIENTOS
    public int desOcupaAsiento(int asiento) {
        int r = busVirtual.LiberarAsiento(asiento, OCUPADO);
        if (r == 0) {
            jcnvBusPlantilla.setVectorAsientos(busVirtual.OCUPADO, 
                                               busVirtual.getVectorAsientos(busVirtual.OCUPADO));
            this.repaint();
        }
        return r;
    }

    public int noReservaAsiento(int asiento) {
        int r = busVirtual.LiberarAsiento(asiento, RESERVADO);
        if (r == 0) {
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADO, 
                                               busVirtual.getVectorAsientos(busVirtual.RESERVADO));

            this.repaint();
        }
        return r;
    }

    public int noReservaNCAsiento(int asiento) {
        int r = busVirtual.LiberarAsiento(asiento, RESERVADONC);
        if (r == 0) {
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADONC, 
                                           busVirtual.getVectorAsientos(busVirtual.RESERVADONC));
            this.repaint();
        }
        return r;
     }
    /************************************/
    // REDIMENSIONAR
private void Redimensionar(){
        try {
            busVirtual.RedimensionarPlantilla(this.getWidth(), 
                                              this.getHeight()
                                              , componentes, encabezado, lineas);
            jcnvBusPlantilla.setDatos(busVirtual.getPlantilla(), 
                                      busVirtual.getCapacidad(), 
                                      busVirtual.getAnchoAsiento(), 
                                      busVirtual.getLargoAsiento(),
                                      this.bVisibleOcupado,this.bVisibleReservado,this.bVisibleReservadoNC);
            jcnvBusPlantilla.setVectorAsientos(busVirtual.OCUPADO, 
                                               busVirtual.getVectorAsientos(busVirtual.OCUPADO));
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADO, 
                                               busVirtual.getVectorAsientos(busVirtual.RESERVADO));
            jcnvBusPlantilla.setVectorAsientos(busVirtual.RESERVADONC, 
                                               busVirtual.getVectorAsientos(busVirtual.RESERVADONC));
            this.repaint();
            
        } catch (NullPointerException npe) {
            npe = null;
        }    
}
    private void this_componentResized(ComponentEvent e) {
        Redimensionar();
    }
}