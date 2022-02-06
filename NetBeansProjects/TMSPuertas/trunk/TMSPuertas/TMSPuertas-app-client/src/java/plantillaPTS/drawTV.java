/*
 * drawAsiento.java
 *
 * Created on 1 de mayo de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package plantillaPTS;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ocruz
 */
public class drawTV {
    private final Color GRIS=new Color(64, 64, 64);
    private final Color AMARILLO=new Color(255, 255, 0);
    private int iX;
    private int iZ;
    private int iAnchoReal;
    private int iProfundoReal;
    private Graphics2D g2;

    private int nAncho;

    private int nAlto;

    private int largoT;
    
    /** Creates a new instance of drawAsiento */
    public drawTV(int piX, int piZ, int piAncho, int piProfundo, Graphics2D pg2, boolean bLimpia) {
        this.iX=piX; this.iZ=piZ;
        this.iAnchoReal=piAncho;
        this.iProfundoReal=piProfundo;
        this.g2 = pg2;
        
        nAlto =  (this.iProfundoReal-8);
        nAncho =  (this.iProfundoReal-4);
        largoT = (nAncho/2);
        
        if(bLimpia){
            g2.setColor(Color.WHITE);
            g2.drawRect(iX, iZ, iAnchoReal, iProfundoReal);
            g2.fillRect(iX+1, iZ+1, iAnchoReal-1, iProfundoReal-1);
            return;
        }
        this.construye();
    }
    
    private void construye(){
        /************************ RECT *****************************/
        g2.setColor(GRIS);
        g2.drawRect(iX, iZ, iAnchoReal, iProfundoReal);
        g2.fillRect(iX+1, iZ+1, iAnchoReal-1, iProfundoReal-1);
        /************************ T ***************************/
        g2.setColor(AMARILLO);
        g2.drawLine(iX+2, iZ+4, (iX+2)+largoT, iZ+4);
        g2.drawLine((iX+2)+(largoT/2), iZ+4, (iX+2)+(largoT/2), (iZ+4)+nAlto);
        /************************ V ***************************/
        g2.setColor(AMARILLO);
        g2.drawLine((iX+6)+largoT, iZ+4, (iX+6)+largoT+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine((iX+6)+largoT+(largoT/2), (iZ+4)+nAlto, (iX+6)+largoT+largoT, iZ+4);
    }
}