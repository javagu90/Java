package tmsbloqueoporlotePlantillaPTS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;
import javax.swing.JPanel;
import tmsbloqueoporlotePlantillaPTS.drawAsiento;
import tmsbloqueoporlotePlantillaPTS.drawCF;
import tmsbloqueoporlotePlantillaPTS.drawTV;
import tmsbloqueoporlotePlantillaPTS.drawWC;

public class JCanvasPlantilla extends JPanel {
    private final Color AZUL=new Color(149, 216, 255);
    private final Color GRIS=new Color(224, 224, 224);
    private Font fuenteCompo = new Font("Tahoma",Font.BOLD,15);
    private Font fuentePequeña = new Font("Tahoma",Font.BOLD,9);
    private final int DISPONIBLE = 0;
    private final int OCUPADO = 1;
    private final int RESERVADO = 2;
    private final int RESERVADONOCANCELABLE = 3;
    private Vector<Componente> vVectorUnico;
    private Vector<Componente> vAsientosOcupados;
    private Vector<Componente> vAsientosReservados;
    private Vector<Componente> vAsientosReservadosNoCancelables;
    private int iCapacidad;
    private int j, i;
    private int px, pz;
    private int fx, fz;
    private int xtv, ztv;
    private int iAsientoX, iAsientoZ;
    private boolean bLimpiar;
    private boolean bVisibleOcupado = true;
    private boolean bVisibleReservado = true;
    private boolean bVisibleReservadoNC = true;
    private drawAsiento wAsiento;

    private drawWC wWC;

    private drawCF wCF;
    private drawTV wTV;
    private int Estado;
    private int Estado2=DISPONIBLE;
    private final Color AZULMARINO=new Color(0,0,128);
    private final Color AMARILLO=new Color(128,128,0);

    public JCanvasPlantilla() {
        vVectorUnico = null;
        vAsientosOcupados = null;
        vAsientosReservados = null;
        vAsientosReservadosNoCancelables = null;
    }

    private void Limpiar() {
        this.bLimpiar = true;
    }
    
    public void setDatos(Vector<Componente> pvVectorUnico, int piCapacidad, 
                         int piAsientoX, int piAsientoZ,
                         boolean pbVisibleOcupado, boolean pbVisibleReservado, boolean pbVisibleReservadoNC) {
        this.vVectorUnico = pvVectorUnico;
        this.iCapacidad = piCapacidad;
        this.iAsientoX = piAsientoX;
        this.iAsientoZ = piAsientoZ;
        this.bLimpiar = false;
        this.bVisibleOcupado = pbVisibleOcupado;
        this.bVisibleReservado = pbVisibleReservado;
        this.bVisibleReservadoNC = pbVisibleReservadoNC;
    }

    /**/
    // CONFIGURA SI LOS ASIENTOS NO DISPONIBLES SE MUESTRAN O NO

    public void setVisibleOcupado(boolean pbVisibleOcupado) {
        this.bVisibleOcupado = pbVisibleOcupado;
    }

    public void setVisibleReservado(boolean pbVisibleReservado) {
        this.bVisibleReservado = pbVisibleReservado;
    }

    public void setVisibleReservadoNC(boolean pbVisibleReservadoNC) {
        this.bVisibleReservadoNC = pbVisibleReservadoNC;
    }
    /**/

    public void setVectorAsientos(int pEstado, 
                                  Vector<Componente> pVectorAsientos) {
        this.bLimpiar=false;
        this.Estado=pEstado; 
        boolean bCompoVisible=true;
        switch (Estado) {
        case OCUPADO:
            this.vAsientosOcupados = pVectorAsientos;
            bCompoVisible=this.bVisibleOcupado;
            break;
        case RESERVADO:
            this.vAsientosReservados = pVectorAsientos;
            bCompoVisible=this.bVisibleReservado;
            break;
        case RESERVADONOCANCELABLE:
            this.vAsientosReservadosNoCancelables = pVectorAsientos;
            bCompoVisible=this.bVisibleReservadoNC;
            break;
        }

        if (!bCompoVisible) {
            for (int i = 0; i < this.iCapacidad; i++) {
                if (noEstaMarcado(vVectorUnico.get(i).getUbicacion()))
                    this.vVectorUnico.get(i).setVisible(true);
                else{
                    if(this.Estado==this.Estado2)
                        this.vVectorUnico.get(i).setVisible(false);
                }
            }
        }
    }

    public boolean noEstaMarcado(int pAsiento){
        int i;
        
        if (this.vAsientosOcupados != null){
            for (i = 0; i < this.vAsientosOcupados.size(); i++) {
                if (pAsiento == this.vAsientosOcupados.get(i).getUbicacion()){
                    this.Estado2=this.OCUPADO;
                    return false;
                }
            }
        }

        if (this.vAsientosReservados != null) {
            for (i = 0; i < this.vAsientosReservados.size(); i++) {
                if (pAsiento == this.vAsientosReservados.get(i).getUbicacion())
                {
                    this.Estado2=this.RESERVADO;
                    return false;
                }
            }
        }

        if (this.vAsientosReservadosNoCancelables != null) {
            for (i = 0; i < this.vAsientosReservadosNoCancelables.size(); i++) {
                if (pAsiento == this.vAsientosReservadosNoCancelables.get(i).getUbicacion())
                {
                    this.Estado2=this.RESERVADONOCANCELABLE;
                    return false;
                }
            }
        }

        return true;
    }

    public void paint(Graphics g) {      
        if(this.vVectorUnico == null)
            return;
        // DIBUJAR
        Graphics2D g2=(Graphics2D)g;
        
        if (bLimpiar) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            return;
        }
        // fondo
        g.setColor(Color.WHITE);//new Color(192,192,192));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        // frente
        g.setColor(Color.gray);
        g.fillArc(0, 0, this.getWidth(), 18, 0, 180);
        g.drawRect(0, 9, 2, 18);
        g.drawRect(this.getWidth() - 3, 9, 2, 18);
        g.setFont(fuenteCompo);
        /*url = getClass().getResource("images/conductorX.jpg");
        bmp = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(bmp, 10, 9, iAsientoX, iAsientoZ, this);*/
        // PLANTILLA
        boolean Repetido=false;
        fx = (iAsientoX - 12) / 2;
        fz = (iAsientoZ + 1) / 2;
        for (i = 0; i < this.iCapacidad; i++) {
            if(vVectorUnico.get(i).getUbicacion()==45 && this.iCapacidad==48){
                if(!Repetido) Repetido = true;
                else continue;
            }
            
            if(this.vVectorUnico.get(i).getNombre().equals("AS")) {
                if(this.vVectorUnico.get(i).getVisible()) {
                    px = vVectorUnico.get(i).getX();
                    pz = vVectorUnico.get(i).getZ();
                    wAsiento=new drawAsiento(px, pz, iAsientoX, iAsientoZ,Color.GRAY,g2, false);
                    g.setColor(AZULMARINO);
                    g.drawString(String.valueOf(vVectorUnico.get(i).getUbicacion()), fx + px, fz + pz);
                }
            }
        }
        // ASIENTOS OCUPADOS
        if (this.vAsientosOcupados != null) {
            if (this.bVisibleOcupado) {
                fx = (iAsientoX - 12) / 2;
                fz = (iAsientoZ + 1) / 2;

                for (i = 0; i < this.vAsientosOcupados.size(); i++) {
                    px = vAsientosOcupados.get(i).getX();
                    pz = vAsientosOcupados.get(i).getZ();
                    wAsiento=new drawAsiento(px, pz, iAsientoX, iAsientoZ,Color.WHITE,g2,true);
                    //g.setColor(AZULMARINO);
                    //g.drawString(String.valueOf(vAsientosOcupados.get(i).getUbicacion()), 
                    //             fx + px, fz + pz);
                }
            }
        }
        // ASIENTOS RESERVADOS
        if (this.vAsientosReservados != null) {
            if (this.bVisibleReservado) {
                fx = (iAsientoX - 14) / 2;
                fz = (iAsientoZ + 1) / 2;
                for (i = 0; i < this.vAsientosReservados.size(); i++) {
                    px = vAsientosReservados.get(i).getX();
                    pz = vAsientosReservados.get(i).getZ();
                    wAsiento=new drawAsiento(px, pz, iAsientoX, iAsientoZ,Color.WHITE,g2, true);
                    g.setColor(Color.RED);
                    g.drawString(String.valueOf(vAsientosReservados.get(i).getUbicacion())+"R", 
                                 fx + px, fz + pz);
                }
            }
        }
        // ASIENTOS RESERVADOS NO CANCELABLES
        if (this.vAsientosReservadosNoCancelables != null) {
            if (this.bVisibleReservadoNC) {
                fx = (iAsientoX - 14) / 2;
                fz = (iAsientoZ + 1) / 2;

                for (i = 0; i < this.vAsientosReservadosNoCancelables.size(); i++) {
                    px = vAsientosReservadosNoCancelables.get(i).getX();
                    pz = vAsientosReservadosNoCancelables.get(i).getZ();
                    //g.drawImage(bmp, px, pz, iAsientoX, iAsientoZ, this);
                    wAsiento=new drawAsiento(px, pz, iAsientoX, iAsientoZ,Color.WHITE,g2, true);
                    g.setColor(Color.ORANGE);
                    g.drawString(String.valueOf(vAsientosReservadosNoCancelables.get(i).getUbicacion())+"C", 
                                 fx + px, fz + pz);
                }
            }
        }
    //}

        // COMPONENTES
        xtv = (int)(iAsientoX * 0.5);
        ztv = (int)(iAsientoZ * 0.4);
        fx = iAsientoX;
        fz = iAsientoZ;

        for (i = this.iCapacidad; i < this.vVectorUnico.size(); i++) {
            px = vVectorUnico.get(i).getX();
            pz = vVectorUnico.get(i).getZ();
            if (this.vVectorUnico.get(i).getNombre().equals("WC"))
                wWC=new drawWC(px, pz, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "WC", AZUL);
            if (this.vVectorUnico.get(i).getNombre().equals("WCD"))
                wWC=new drawWC(px, pz, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "WCd", AZUL);
            if (this.vVectorUnico.get(i).getNombre().equals("WCC"))
                wWC=new drawWC(px, pz, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "WCc", AZUL);
            if (this.vVectorUnico.get(i).getNombre().equals("PA"))
                wWC=new drawWC(px, pz, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "PA", GRIS);
            if (this.vVectorUnico.get(i).getNombre().equals("VT"))
                wWC=new drawWC(px, pz, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "VT", Color.WHITE);
            if (this.vVectorUnico.get(i).getNombre().equals("TV"))
                wTV=new drawTV(px, pz, xtv, ztv, g2, false);
            if (this.vVectorUnico.get(i).getNombre().equals("CF"))
                wCF=new drawCF(px, pz, xtv, (int)(iAsientoZ*0.6), g2, false,fuentePequeña, "CF");
        }
    }
}

/*
 for (i = this.iCapacidad; i < this.vVectorUnico.size(); i++) {
            px = vVectorUnico.get(i).getX();
            pz = vVectorUnico.get(i).getZ();
            url = 
getClass().getResource("images/" + this.vVectorUnico.get(i).getNombre() + 
                       ".jpg");
            bmp = Toolkit.getDefaultToolkit().getImage(url);
            if (this.vVectorUnico.get(i).getNombre().equals("TV"))
                g.drawImage(bmp, px, pz, xtv, ztv, this);
            else
                g.drawImage(bmp, px, pz, fx, fz, this);
        }
 */