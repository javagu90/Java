package tms_plantillas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Vector;
import tms_plantillas.images.drawAsiento;
import tms_plantillas.images.drawAsientoInvertido;
import tms_plantillas.images.drawCF;
import tms_plantillas.images.drawNuevaFlotllla;
import tms_plantillas.images.drawTV;
import tms_plantillas.images.drawWC;

public class JCanvasPlantilla extends Canvas{
    private final Color AZUL=new Color(149, 216, 255);
    private final Color GRIS=new Color(224, 224, 224);
    private Font fuenteCompo = new Font("Tahoma",Font.BOLD,15);
    private Font fuentePequeña = new Font("Tahoma",Font.BOLD,9);
    private final Color AZULMARINO=new Color(0,0,128);
    private drawAsiento wAsiento;
    private drawAsientoInvertido wAsientoInvertido;
    private drawWC wWC;
    private drawCF wCF;
    private drawTV wTV;
    private drawNuevaFlotllla wNF;
    
    private Asiento[][] mAsientosIzq;
    private Asiento[][] mAsientosDer;
    private Asiento[] mAsientosRes;
    private Vector<Compo> vCompoAereo;
    private Vector<Compo> vCompoLocal;
    private int j, i;
    private int px, pz;
    private int fx, fz;
    private int iColIzq, iColDer;
    private int iAsientoX, iAsientoZ;
    private String NombreCompo;
    private boolean bLimpiar;

    public JCanvasPlantilla() {
    }

    public void Limpiar(){ this.bLimpiar=true; }
    
    public void setDatos(Asiento[][] pvDatos0, Asiento[][] pvDatos2,  Asiento[] pvDatos1,
                        int piAsientoX, int piAsientoZ, int piColIzq, int piColDer){
        this.mAsientosIzq=pvDatos0;
        this.mAsientosDer=pvDatos2;
        this.mAsientosRes=pvDatos1;
        this.iAsientoX=piAsientoX;
        this.iAsientoZ=piAsientoZ;
        this.iColIzq=piColIzq;
        this.iColDer=piColDer;
        this.bLimpiar=false;
    }
    
    public void setDato(Asiento[][] pvDatos0, Asiento[][] pvDatos2,
                        Asiento[] pvDatos1){
        this.mAsientosIzq=pvDatos0;
        this.mAsientosDer=pvDatos2;
        this.mAsientosRes=pvDatos1;
    }
    
    public void setCompoAero(Vector<Compo> pvCompo, String nombre){
        this.vCompoAereo=pvCompo;
        this.NombreCompo=nombre;
        this.bLimpiar=false;
    }
    
    public void setCompoLocal(Vector<Compo> pvCompoLocal){
        this.vCompoLocal=pvCompoLocal;
        this.bLimpiar=false;
    }
    
    public void paint( Graphics g ) {
        if(bLimpiar){
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            return;
        }
        if(this.mAsientosIzq==null) return;
            g.setColor(Color.gray);
            g.fillArc(0,0,this.getWidth(),18,0,180);
            g.drawRect(0,9,2,18);
            g.drawRect(this.getWidth()-3,9,2,18);
                    fx=(iAsientoX-12)/2;
                    fz=(iAsientoZ+1)/2;
                    g.setColor(Color.yellow);
                    boolean mesa = false;
                    if(vCompoLocal!=null)
                    {
                        for(i=0; i<vCompoLocal.size(); i++)
                             if (vCompoLocal.get(i).getNombre().equals("MT"))
                                 mesa = true;
                    }
                    for(i=0; i<this.mAsientosIzq.length; i++)
                        for(j=0; j<iColIzq; j++){
                            px=mAsientosIzq[i][j].getX();
                            pz=mAsientosIzq[i][j].getZ();
                            if(mesa && (mAsientosIzq[i][j].getNumero()==25 || mAsientosIzq[i][j].getNumero()==26 || mAsientosIzq[i][j].getNumero()==27 || mAsientosIzq[i][j].getNumero()==28) )
                                    wAsientoInvertido=new drawAsientoInvertido(px, pz, iAsientoX, iAsientoZ,Color.GRAY, (Graphics2D) g, false);
                            else
                                wAsiento=new drawAsiento(px, pz, iAsientoX, iAsientoZ,Color.GRAY, (Graphics2D) g, false);
                            g.setColor(AZULMARINO);
                            g.drawString(String.valueOf(mAsientosIzq[i][j].getNumero()),fx+px,fz+pz);
                        }
                    for(i=0; i<this.mAsientosDer.length; i++)
                        for(j=0; j<iColDer; j++){
                            px=mAsientosDer[i][j].getX();
                            pz=mAsientosDer[i][j].getZ();
                            if(mesa && (mAsientosDer[i][j].getNumero()==25 || mAsientosDer[i][j].getNumero()==26 || mAsientosDer[i][j].getNumero()==27 || mAsientosDer[i][j].getNumero()==28) )
                                 wAsientoInvertido=new drawAsientoInvertido(px, pz, iAsientoX, iAsientoZ,Color.GRAY, (Graphics2D) g, false);
                            else
                                wAsiento=new drawAsiento(px, pz, iAsientoX, iAsientoZ,Color.GRAY, (Graphics2D) g, false);
                            g.setColor(AZULMARINO);
                            g.drawString(String.valueOf(mAsientosDer[i][j].getNumero()),fx+px,fz+pz);
                        }
                        
                    for(i=0; i<this.mAsientosRes.length; i++){
                        px=mAsientosRes[i].getX();
                        pz=mAsientosRes[i].getZ();
                        wAsiento=new drawAsiento(px, pz, iAsientoX, iAsientoZ,Color.GRAY, (Graphics2D) g, false);
                        g.setColor(AZULMARINO);
                        g.drawString(String.valueOf(mAsientosRes[i].getNumero()),fx+px,fz+pz);
                    }
                    
            // COMPONENTE AEREO
                if(vCompoAereo!=null){                    
                    fx=(int)(iAsientoX*0.5);
                    fz=(int)(iAsientoZ*0.4);

                    for(i=0; i<vCompoAereo.size(); i++){
                        px=vCompoAereo.get(i).getX();
                        pz=vCompoAereo.get(i).getZ()-2;
                        System.out.println("COMPONENTE  "+vCompoAereo.get(i).getNombre());
                        if (vCompoAereo.get(i).getNombre().equals("TV"))
                        {

                            System.out.println("TV Ubicacion: "+vCompoAereo.get(i).getUbicacion());
                            System.out.println("px: "+px);
                            System.out.println("pz: "+pz);
                            System.out.println("fx: "+fx);
                            System.out.println("fz: "+fz);
                            if(vCompoAereo.get(i).getUbicacion()==99)
                                wTV=new drawTV(122, 505, fx, fz, (Graphics2D) g, false);
                            else
                                wTV=new drawTV(px, pz, fx, fz, (Graphics2D) g, false);


                        }
                         if (vCompoAereo.get(i).getNombre().equals("NP"))
                        {

                            System.out.println("TV Ubicacion: "+vCompoAereo.get(i).getUbicacion());
                            System.out.println("px: "+px);
                            System.out.println("pz: "+pz);
                            System.out.println("fx: "+fx);
                            System.out.println("fz: "+fz);
                            if(vCompoAereo.get(i).getUbicacion()==99)
                                wNF=new drawNuevaFlotllla(122, 505, fx, fz, (Graphics2D) g, false);
                               // wNF=new drawNuevaFlotllla(px, pz, fx, fz, (Graphics2D) g, false, fuenteCompo, "WC", AZUL);
                            else
                                wNF=new drawNuevaFlotllla(px, pz-8, fx, fz, (Graphics2D) g, false);
                                // wNF=new drawNuevaFlotllla(px, pz, fx, fz, (Graphics2D) g, false, fuenteCompo, "WC", AZUL);


                        }
                    }
                }
            // COMPONENTE LOCAL
                if(vCompoLocal==null) return;        
                    fx=iAsientoX;
                    fz=iAsientoZ;

                    for(i=0; i<vCompoLocal.size(); i++){
                        px=vCompoLocal.get(i).getX();
                        pz=vCompoLocal.get(i).getZ();
                        if (vCompoLocal.get(i).getNombre().equals("WC"))
                            wWC=new drawWC(px, pz, fx, fz, (Graphics2D) g, false, fuenteCompo, "WC", AZUL);
                        if (vCompoLocal.get(i).getNombre().equals("WCD"))
                            wWC=new drawWC(px, pz, fx, fz, (Graphics2D) g, false, fuenteCompo, "WCd", AZUL);
                        if (vCompoLocal.get(i).getNombre().equals("WCC"))
                            wWC=new drawWC(px, pz, fx, fz, (Graphics2D) g, false, fuenteCompo, "WCc", AZUL);
                        if (vCompoLocal.get(i).getNombre().equals("PA"))
                            wWC=new drawWC(px, pz, fx, fz, (Graphics2D) g, false, fuenteCompo, "PA", GRIS);
                        if (vCompoLocal.get(i).getNombre().equals("VT"))
                            wWC=new drawWC(px, pz, fx, fz, (Graphics2D) g, false, fuenteCompo, "VT", Color.WHITE);
                        if (vCompoLocal.get(i).getNombre().equals("CF"))
                            wCF=new drawCF(px, pz,fx,(int)(iAsientoZ*0.6), (Graphics2D) g, false,fuentePequeña, "CF");
                        if (vCompoLocal.get(i).getNombre().equals("MT"))
                            wWC=new drawWC((px==0)?px:(px-fx), pz, fx*2, fz, (Graphics2D) g, false, fuenteCompo, "MT", new Color(232,174,0));
                            //wWC=new drawWC((px==0)?px:(px-fx), pz, fx*2, fz, (Graphics2D) g, false, fuenteCompo, "MT", new Color(255,192,0));
                    }
    }

}
