package plantilla;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Vector;
import javax.swing.JPanel;

public class JCanvasPlantilla extends JPanel {
    /*private URL url;
    private Image bmp;*/
    //private static final int margen = 5;
    private final Color AZUL=new Color(149, 216, 255);
    private final Color GRIS=new Color(224, 224, 224);
    private final Color PLATA=new Color(192, 192, 192);
    private Font fuenteCompo = new Font("Calibri",Font.BOLD,15);
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
    private drawAsientoInvertido wAsientoInvertido;

    private drawWC wWC;

    private drawCF wCF;
    private drawTV wTV;
    private drawNuevaFlotllla  wNF;

    private Vector vND;
    private int Estado;
    private int Estado2=DISPONIBLE;
    private final Color cASIENTO;//new Color(0,0,102);
    private final Color AMARILLO=new Color(128,128,0);
    private final Color cPISO;

    public JCanvasPlantilla(Color pcPISO, Color pcASIENTO) {
        cPISO = pcPISO;
        cASIENTO = pcASIENTO;
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
    
    public void setVND(Vector nD){ this.vND = nD; }

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
        //try{
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
                    if (pAsiento == this.vAsientosReservados.get(i).getUbicacion()){
                        this.Estado2=this.RESERVADO;
                        return false;
                    }
                }
            }

            if (this.vAsientosReservadosNoCancelables != null) {
                for (i = 0; i < this.vAsientosReservadosNoCancelables.size(); i++) {
                    if (pAsiento == this.vAsientosReservadosNoCancelables.get(i).getUbicacion()){
                        this.Estado2=this.RESERVADONOCANCELABLE;
                        return false;
                    }
                }
            }
        return true;
    }

    public void paint(Graphics g){
        try{
        if(this.vVectorUnico == null)
            return;
        // DIBUJAR
        Graphics2D g2=(Graphics2D)g;
        
        if (bLimpiar) {
            g.setColor(cPISO);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            return;
        }
        // fondo
        g.setColor(cPISO);//new Color(192,192,192));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        // frente
        g.setColor(PLATA);
        g.fillArc(0, 0, this.getWidth(), 18, 0, 180);
        g.drawRect(0, 9, 2, 18);
        g.drawRect(this.getWidth() - 3, 9, 2, 18);
        g.setFont(fuenteCompo);

        // PLANTILLA
        boolean Repetido=false;
        fx = (iAsientoX - 18) / 2;
        fz = (iAsientoZ + 1) / 2;
        for (i = 0; i < this.iCapacidad; i++) {
            String strNoAsiento;
            int centra = 0;
            boolean mesa = false;
            if(this.vVectorUnico.get(i).getNombre().equals("AS")) {
                //if(this.vVectorUnico.get(i).getVisible()) {
                if(pintaAsiento(i)) {
                    px = vVectorUnico.get(i).getX();
                    pz = vVectorUnico.get(i).getZ();
                    if(vVectorUnico!=null)
                    {
                        for(int k=0; k<vVectorUnico.size(); k++)
                             if (vVectorUnico.get(k).getNombre().equals("MT"))
                                 mesa = true;
                    }
                    //System.out.println("Hay mesa?: "+mesa);
                    //System.out.println("Asiento Ubicacion?: "+vVectorUnico.get(i).getUbicacion());

                    if(mesa && (vVectorUnico.get(i).getUbicacion()==25 ||vVectorUnico.get(i).getUbicacion()==26 || vVectorUnico.get(i).getUbicacion()==27 || vVectorUnico.get(i).getUbicacion()==28) )
                       wAsientoInvertido=new drawAsientoInvertido(px, pz-9, iAsientoX, iAsientoZ,Color.GRAY,g2, false);
                       //wAsientoInvertido=new drawAsientoInvertido(px, pz, iAsientoX, iAsientoZ,Color.GRAY, (Graphics2D) g, false);
                    else
                        wAsiento=new drawAsiento(px, pz-9, iAsientoX, iAsientoZ,Color.GRAY,g2, false);
                    g.setColor(cASIENTO);
                    strNoAsiento=String.valueOf(vVectorUnico.get(i).getUbicacion());
                    if(strNoAsiento.length()==1) centra = 6;
                    g.drawString(strNoAsiento, fx + px+centra, fz + pz-9);
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
                    wAsiento=new drawAsiento(px, pz-9, iAsientoX, iAsientoZ,Color.BLACK,g2,true);
                }
            }
        }
        // ASIENTOS RESERVADOS
        if (this.vAsientosReservados != null) {
            if (this.bVisibleReservado) {
                fx = (iAsientoX - 21) / 2;
                fz = (iAsientoZ + 1) / 2;
                for (i = 0; i < this.vAsientosReservados.size(); i++) {
                    px = vAsientosReservados.get(i).getX();
                    pz = vAsientosReservados.get(i).getZ();
                    wAsiento=new drawAsiento(px, pz-9, iAsientoX, iAsientoZ,Color.BLACK,g2, true);
                    g.setColor(Color.RED);
                    g.drawString(String.valueOf(vAsientosReservados.get(i).getUbicacion())+"R", 
                                 fx + px, fz + pz-9);
                }
            }
        }
        // ASIENTOS RESERVADOS NO CANCELABLES
        if (this.vAsientosReservadosNoCancelables != null) {
            if (this.bVisibleReservadoNC) {
                fx = (iAsientoX - 21) / 2;
                fz = (iAsientoZ + 1) / 2;

                for (i = 0; i < this.vAsientosReservadosNoCancelables.size(); i++) {
                    px = vAsientosReservadosNoCancelables.get(i).getX();
                    pz = vAsientosReservadosNoCancelables.get(i).getZ();
                    wAsiento=new drawAsiento(px, pz-9, iAsientoX, iAsientoZ,Color.BLACK,g2, true);
                    g.setColor(Color.ORANGE);
                    g.drawString(String.valueOf(vAsientosReservadosNoCancelables.get(i).getUbicacion())+"C", 
                                 fx + px, fz + pz-9);
                }
            }
        }

        // COMPONENTES
        xtv = (int)(iAsientoX * 0.5);
        ztv = (int)(iAsientoZ * 0.4);
        fx = iAsientoX;
        fz = iAsientoZ;

        for (i = this.iCapacidad; i < this.vVectorUnico.size(); i++) {
            System.out.println("Componnete --- >"+this.vVectorUnico.get(i).getNombre());
            px = vVectorUnico.get(i).getX();
            pz = vVectorUnico.get(i).getZ();
            if (this.vVectorUnico.get(i).getNombre().equals("WC"))
                wWC=new drawWC(px, pz-3, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "WC", AZUL);
            if (this.vVectorUnico.get(i).getNombre().equals("WCD"))
                wWC=new drawWC(px, pz-3, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "WCd", AZUL);
            if (this.vVectorUnico.get(i).getNombre().equals("WCC"))
                wWC=new drawWC(px, pz-3, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "WCc", AZUL);
            if (this.vVectorUnico.get(i).getNombre().equals("PA"))
                wWC=new drawWC(px, pz-3, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "PA", GRIS);
            if (this.vVectorUnico.get(i).getNombre().equals("VT"))
                wWC=new drawWC(px, pz-3, iAsientoX, iAsientoZ, g2, false, fuenteCompo, "VT", Color.WHITE);

            if(this.vVectorUnico.get(i).getNombre().equals("TV") && this.vVectorUnico.get(i).getUbicacion()==99)
            {
                int xutv = 0;
                int zutv = 0;
                for(int k=0; k<vVectorUnico.size(); k++)
                {
                    if (vVectorUnico.get(k).getUbicacion()==31)
                    {
                        xutv= this.vVectorUnico.get(k).getX();
                        zutv= this.vVectorUnico.get(k).getZ();
                    }
                }
                wTV=new drawTV(xutv-30, zutv+ztv-2, xtv, ztv, g2, false);
            }
            if(this.vVectorUnico.get(i).getNombre().equals("TV") && this.vVectorUnico.get(i).getUbicacion()!=99)
                wTV=new drawTV(px, pz-3, xtv, ztv, g2, false);

            //  Se agrego por objeto nueva plantilla
             if(this.vVectorUnico.get(i).getNombre().equals("NP") && this.vVectorUnico.get(i).getUbicacion()==99)
            {
                int xutv = 0;
                int zutv = 0;
                for(int k=0; k<vVectorUnico.size(); k++)
                {
                    if (vVectorUnico.get(k).getUbicacion()==31)
                    {
                        xutv= this.vVectorUnico.get(k).getX();
                        zutv= this.vVectorUnico.get(k).getZ();
                    }
                }
                wNF=new drawNuevaFlotllla(xutv-30, zutv+ztv-2, xtv, ztv, g2, false);
            }

             if(this.vVectorUnico.get(i).getNombre().equals("NP") && this.vVectorUnico.get(i).getUbicacion()!=99)
                wNF=new drawNuevaFlotllla(px, pz-3, xtv, ztv, g2, false);

            //if (this.vVectorUnico.get(i).getNombre().equals("TV"))
            //    wTV=new drawTV(px, pz-3, xtv, ztv, g2, false);
            if (this.vVectorUnico.get(i).getNombre().equals("CF"))
                wCF=new drawCF(px, pz-3, xtv, (int)(iAsientoZ*0.6), g2, false,fuentePequeña, "CF");
            if (this.vVectorUnico.get(i).getNombre().equals("MT"))
                wWC=new drawWC((px==0)?px:(px-fx), pz-3, iAsientoX*2, iAsientoZ, g2, false, fuenteCompo, "MT",new Color(185,122,87));//Cafe Calor
                //wWC=new drawWC((px==0)?px:(px-fx), pz-3, iAsientoX*2, iAsientoZ, g2, false, fuenteCompo, "MT",new Color(128,64,64));//Cafe Oscuro
                //wWC=new drawWC((px==0)?px:(px-fx), pz-3, iAsientoX*2, iAsientoZ, g2, false, fuenteCompo, "MT",new Color(232,174,0));//Amarillo


        }
        } catch ( NullPointerException npex ) {
            ;
        } catch ( java.lang.ArrayIndexOutOfBoundsException aiex ) {
            ;
        } catch(java.util.NoSuchElementException ex){
            ;
        } catch(Exception x){
            ;
        }
    }

    private boolean pintaAsiento(int ubi) {
        int asiento = this.vVectorUnico.get(ubi).getUbicacion();
        
        if(this.vND!=null && this.vND.size()>0 && this.vND.contains(asiento)) return false;
        
        return true;
    }
}