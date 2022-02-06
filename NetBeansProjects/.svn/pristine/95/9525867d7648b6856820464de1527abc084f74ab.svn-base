/*
 * drawAsiento.java
 *
 * Created on 1 de mayo de 2007, 10:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_plantillas.images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author ocruz
 */
public class drawCF {
    private final Color CAFE=new Color(92, 65, 67);
    private int iX;
    private int iZ;
    private int iAnchoReal;
    private int iProfundoReal;
    private Graphics2D g2;
    private int fx, fz;
    private Font Fuente;
    private int nAncho;
    private String texto;
    private int nAlto;
    private String titulo="NUEVO";

    private int largoT;
    
    /** Creates a new instance of drawAsiento */
    public drawCF(int piX, int piZ, int piAncho, int piProfundo, Graphics2D pg2, boolean bLimpia, Font pFuente, String pTexto) {
        this.texto = pTexto;
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
        g2.setColor(CAFE);
        g2.drawRect(iX, iZ, iAnchoReal, iProfundoReal);
        g2.fillRect(iX+1, iZ+1, iAnchoReal-1, iProfundoReal-1);
        fx = (iAnchoReal - 18) / 2;
        fz = (iProfundoReal + 3) / 2;
        g2.setColor(Color.WHITE);
        g2.setFont(Fuente);
        g2.drawString(texto, fx + iX, fz + iZ);
    }
}