package tms_plantillas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import java.net.URL;

import java.util.Vector;

public class JCanvasPlantillaX extends Canvas{
    private Asiento[][] mAsientosIzq;
    private Asiento[][] mAsientosDer;
    private Asiento[] mAsientosRes;
    private Vector<Compo> vCompoAereo;
    private Vector<Compo> vCompoLocal;
    private Image bmp;
    private int j, i;
    private int px, pz;
    private int fx, fz;
    private int iColIzq, iColDer;
    private int iAsientoX, iAsientoZ;
    private String NombreCompo;
    private URL url;
    private boolean bLimpiar;

    public JCanvasPlantillaX() {
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
                    //(new javax.swing.ImageIcon(getClass().getResource("../tms_plantillas/images/as.JPG")));
                    url = getClass().getResource("/tms_plantillas/images/as.JPG");
                    bmp=Toolkit.getDefaultToolkit().getImage(url);
                    fx=(iAsientoX-12)/2;
                    fz=(iAsientoZ+1)/2;
                    g.setColor(Color.yellow);
                    for(i=0; i<this.mAsientosIzq.length; i++)
                        for(j=0; j<iColIzq; j++){
                            px=mAsientosIzq[i][j].getX();
                            pz=mAsientosIzq[i][j].getZ();
                            g.drawImage(bmp,px,pz,iAsientoX,iAsientoZ,this);
                            g.drawString(String.valueOf(mAsientosIzq[i][j].getNumero()),fx+px,fz+pz);
                        }
                    for(i=0; i<this.mAsientosDer.length; i++)
                        for(j=0; j<iColDer; j++){
                            px=mAsientosDer[i][j].getX();
                            pz=mAsientosDer[i][j].getZ();
                            g.drawImage(bmp,px,pz,iAsientoX,iAsientoZ,this);
                            g.drawString(String.valueOf(mAsientosDer[i][j].getNumero()),fx+px,fz+pz);
                        }
                        
                    for(i=0; i<this.mAsientosRes.length; i++){
                        px=mAsientosRes[i].getX();
                        pz=mAsientosRes[i].getZ();
                        g.drawImage(bmp,px,pz,iAsientoX,iAsientoZ,this);
                        g.drawString(String.valueOf(mAsientosRes[i].getNumero()),fx+px,fz+pz);
                    }
                        
        
                    url = getClass().getResource("/tms_plantillas/images/conductorX.jpg");
                    bmp=Toolkit.getDefaultToolkit().getImage(url);
                    g.drawImage(bmp,10,9,iAsientoX,iAsientoZ,this);
            // COMPONENTE AEREO
                if(vCompoAereo!=null){
                    url = getClass().getResource("/tms_plantillas/images/"+this.NombreCompo+".jpg");
                    bmp=Toolkit.getDefaultToolkit().getImage(url);
                    
                    fx=(int)(iAsientoX*0.5);
                    fz=(int)(iAsientoZ*0.4);
   System.out.println("COMPONENTE   "+vCompoAereo.get(i).getNombre());
                    for(i=0; i<vCompoAereo.size(); i++){
                        px=vCompoAereo.get(i).getX();
                        pz=vCompoAereo.get(i).getZ()-2;
                        if(vCompoAereo.get(i).getNombre().equals("TV")) g.drawImage(bmp,px,pz,fx,fz,this);
                        else g.drawImage(bmp,px,pz,fx,(int)(iAsientoZ*0.6),this);
                    }
                }
            // COMPONENTE LOCAL
                if(vCompoLocal==null) return;        
                    fx=iAsientoX;
                    fz=iAsientoZ;

                    for(i=0; i<vCompoLocal.size(); i++){
                        url = getClass().getResource("/tms_plantillas/images/"+vCompoLocal.get(i).getNombre()+".jpg");
                        bmp=Toolkit.getDefaultToolkit().getImage(url);
                        px=vCompoLocal.get(i).getX();
                        pz=vCompoLocal.get(i).getZ();
                        g.drawImage(bmp,px,pz,fx,fz,this);
                    }
    }

}
