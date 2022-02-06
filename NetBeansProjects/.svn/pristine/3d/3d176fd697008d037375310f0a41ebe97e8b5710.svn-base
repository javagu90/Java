/*
 * drawAsiento.java
 *
 * Created on 1 de mayo de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsOcupacionPlantillaPTS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author ocruz
 */
public class drawWC {
    private final Color ROJO=new Color(96, 0, 0);
    private int iX;
    private int iZ;
    private int iAnchoReal;
    private int iProfundoReal;
    private Graphics2D g2;
    private int fx, fz;
    private Font Fuente;
    private Color Fondo;
    private int nAncho;
    private String texto;
    private int nAlto;

    private int largoT;
    
    /** Creates a new instance of drawAsiento */
    public drawWC(int piX, int piZ, int piAncho, int piProfundo, Graphics2D pg2, boolean bLimpia, Font pFuente, String pTexto, Color pFondo) {
        this.texto = pTexto;
        this.Fondo = pFondo;
        this.Fuente = pFuente;
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
        g2.setColor(Fondo);
        g2.drawRect(iX, iZ, iAnchoReal, iProfundoReal);
        g2.fillRect(iX+1, iZ+1, iAnchoReal-1, iProfundoReal-1);
        fx = (iAnchoReal - 30) / 2;
        fz = (iProfundoReal + 6) / 2;
        g2.setColor(ROJO);
        g2.setFont(Fuente);
        g2.drawString(texto, fx + iX, fz + iZ);
    }
}