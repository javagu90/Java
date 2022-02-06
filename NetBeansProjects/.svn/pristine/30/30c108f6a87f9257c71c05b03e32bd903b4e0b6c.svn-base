/*
 * drawAsiento.java
 *
 * Created on 1 de mayo de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package plantilla;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ocruz
 */
public class drawAsientoInvertido {
    //private final Color ROJO_0=new Color(255,240,240);
    private final Color GRIS_0=new Color(229,229,229);
    private final Color AZUL_0=new Color(0, 0, 192);
    private int iX;
    private int iZ;
    private int iAncho;
    private int iProfundo;
    private int iAnchoReal;
    private int iProfundoReal;
    private int iBrazoZ;
    private int iRespaldo;
    private Color cColor;
    private Graphics2D g2;
    
    /** Creates a new instance of drawAsiento */
    public drawAsientoInvertido(int piX, int piZ, int piAncho, int piProfundo, Color pcColor, Graphics2D pg2, boolean bLimpia) {
        this.cColor=pcColor;
        this.g2=pg2;
        this.iX=piX; this.iZ=piZ;

        this.iAnchoReal=piAncho;
        this.iProfundoReal=piProfundo;
        
        if(bLimpia){
            g2.setColor(Color.WHITE);
            g2.drawRect(iX, iZ, iAnchoReal, iProfundoReal);
            g2.setColor(Color.WHITE);
            g2.fillRect(iX+1, iZ+1, iAnchoReal-1, iProfundoReal-1);
            return;
        }
        this.iAncho=(int)(this.iAnchoReal*0.80);
        this.iProfundo=(int)(this.iProfundoReal*0.85);
        this.iBrazoZ=(int)(this.iProfundoReal*0.15);
        this.iRespaldo=(int)(this.iAnchoReal*0.15);
        this.construye();
    }
    
    private void construye(){
        /***************************/
        int iXC=(this.iAnchoReal-this.iAncho)/2;
        int iDif=(iXC+iX)-iX;
        /***************************/
        g2.setColor(AZUL_0);
        g2.drawRect(iX, iZ, iAnchoReal, iProfundoReal);
        GradientPaint gradiente = new GradientPaint( 75,75,cColor, 95,95,new Color(128,0,0),true);
        g2.setPaint(gradiente);
        g2.fillRect(iX+1, iZ+1, iAnchoReal-1, iProfundoReal-1);
        /************************ RECT *****************************/      
        g2.setColor(AZUL_0);
        g2.drawRect(iX+iXC, iZ+5, iAncho, iProfundo);
        g2.setColor(GRIS_0);
        g2.fillRect(iX+iXC+1, iZ+6, iAncho-1, iProfundo-1);
        /************************ BRAZO IZQ ***************************/
        g2.setColor(GRIS_0);
        g2.drawRect(iX+1, iZ+1+iProfundo-2, iDif-1, iBrazoZ);
        g2.setColor(GRIS_0);
        g2.fillRect(iX+2, iZ+2+iProfundo-2, iDif-2, iBrazoZ-1);
        /************************ BRAZO DER ***************************/
        g2.setColor(GRIS_0);
        g2.drawRect(iX+iXC+iAncho, iZ+1+iProfundo-2, iDif, iBrazoZ);
        g2.setColor(GRIS_0);
        g2.fillRect(iX+iXC+iAncho+1, iZ+2+iProfundo-2, iDif-1, iBrazoZ-1);
        /************************ RESPALDO ***************************/
        
        g2.setColor(GRIS_0);
        g2.drawRect(iX+iXC+1, iZ+2, iRespaldo, iDif-1);
        g2.fillRect(iX+iXC+2, iZ+2, iRespaldo-1, iDif-2);

        g2.drawRect((iX+iXC+iAncho-1)-iRespaldo, iZ+2, iRespaldo, iDif-1);
        g2.fillRect((iX+iXC+iAncho)-iRespaldo, iZ+2, iRespaldo-1, iDif-2);
        
                /************/
       /* int iDiv=iDif/2;
        g2.setColor(AZUL_0);
        g2.drawRect(iX+iXC+1, iZ, iRespaldo, iDiv-1);
        g2.setPaint(gradiente);
        g2.fillRect(iX+iXC+2, iZ, iRespaldo-1, iDiv-2);

        g2.setColor(AZUL_0);
        g2.drawRect((iX+iXC+iAncho-1)-iRespaldo, iZ, iRespaldo, iDiv-1);
        g2.setPaint(gradiente);
        g2.fillRect((iX+iXC+iAncho)-iRespaldo, iZ, iRespaldo-1, iDiv-2);
        * 
        */
    }
}