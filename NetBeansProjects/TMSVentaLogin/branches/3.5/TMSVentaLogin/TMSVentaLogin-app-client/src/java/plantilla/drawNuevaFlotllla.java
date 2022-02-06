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
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ocruz
 */
public class drawNuevaFlotllla {
    private final Color GRIS=new Color(64, 64, 64);
    private final Color AMARILLO=new Color(255, 255, 0);
    private final Color AZUL=new Color(149, 216, 255);
     private final Color VERDE=new Color(123,191,146);
    private final Color BLANCO=new Color(255, 255, 255);
    private final Color NEGRO=new Color(0, 0, 0);

    //private final Color ROJO=new Color(255, 18, 36);
    private final Color ROJO = new Color(255, 0, 0);
     private Font Fuente =new Font("Tahoma",Font.BOLD,25);
      private Color Fondo;
      private int fx, fz;

    private String texto;
    private int iX;
    private int iZ;
    private int iAnchoReal;
    private int iProfundoReal;
    private Graphics2D g2;

    private int nAncho;

    private int nAlto;

    private int largoT;
    
    /** Creates a new instance of drawAsiento */
    public drawNuevaFlotllla(int piX, int piZ, int piAncho, int piProfundo, Graphics2D pg2, boolean bLimpia) {
  //    public drawNuevaFlotllla(int piX, int piZ, int piAncho, int piProfundo, Graphics2D pg2, boolean bLimpia, Font pFuente, String pTexto, Color pFondo) {

        this.iX=piX; this.iZ=piZ;
        this.iAnchoReal=piAncho;
        this.iProfundoReal=piProfundo;
        this.g2 = pg2;
        
        nAlto =  (this.iProfundoReal-8);
        nAncho =  (this.iProfundoReal-4);
        largoT = (nAncho/2);

        System.out.println("ENTRA A  drawNuevaFlotllla ");
        if(bLimpia){
            g2.setColor(Color.WHITE);
            g2.drawRect(iX, iZ, iAnchoReal, iProfundoReal);
            g2.fillRect(iX+1, iZ+1, iAnchoReal-1, iProfundoReal-1);
            return;
        }
        this.construye();
    }
    
    private void construye(){


        int desfase=6;
        int desplaz=37;
        System.out.println("ix: "+iX);
        System.out.println("iz: "+iZ);
        System.out.println("ENTRA A  drawNuevaFlotllla cONSTSRUYE");
        /************************ RECT *****************************/
        g2.setColor(ROJO);
        g2.drawRect(iX, iZ, iAnchoReal+80, iProfundoReal);
        g2.fillRect(iX+1, iZ+1, iAnchoReal+80-1, iProfundoReal-1);

         g2.setColor(BLANCO);
         g2.setFont(Fuente);


       /************************ N ***************************/
        g2.drawLine(desplaz+(iX+0)+(largoT/2), iZ+4, desplaz+(iX+0)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+(iX+0)+(largoT/2), iZ+4, desplaz+(iX+4)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+(iX+4)+(largoT/2), iZ+4, desplaz+(iX+4)+(largoT/2), (iZ+4)+nAlto);

        /************************ U ***************************/
        g2.drawLine(desplaz+(iX+desfase)+(largoT/2), iZ+4, desplaz+(iX+desfase)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+(iX+desfase+4)+(largoT/2), iZ+4, desplaz+(iX+desfase+4)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+(iX+desfase)+(largoT/2), iZ+4+nAlto, desplaz+(iX+desfase+4)+(largoT/2), (iZ+4)+nAlto);

          /************************ E ***************************/
        g2.drawLine(desplaz+iX+(desfase*2)+(largoT/2), iZ+4, desplaz+iX+(desfase*2)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+iX+(desfase*2)+(largoT/2), iZ+4, desplaz+iX+(desfase*2)+4+(largoT/2), (iZ+4));
        g2.drawLine(desplaz+iX+(desfase*2)+(largoT/2), iZ+4+nAlto, desplaz+iX+(desfase*2)+4+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+iX+(desfase*2)+(largoT/2), iZ+4+(nAlto/2), desplaz+iX+(desfase*2)+2+(largoT/2), (iZ+4)+(nAlto/2));

       /************************V ***************************/
       
        g2.drawLine(desplaz+iX+(desfase*3)+largoT/2, iZ+4, desplaz+iX+(desfase*3)+largoT/2+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+iX+(desfase*3)+largoT/2+(largoT/2), (iZ+4)+nAlto,desplaz+ iX+(desfase*3)+largoT/2+largoT, iZ+4);


         /************************ O ***************************/
        g2.drawLine(desplaz+(iX+desfase*4)+(largoT/2), iZ+4, desplaz+(iX+desfase*4)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+(iX+desfase*4+4)+(largoT/2), iZ+4, desplaz+(iX+desfase*4+4)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+(iX+desfase*4)+(largoT/2), iZ+4+nAlto, desplaz+(iX+desfase*4+4)+(largoT/2), (iZ+4)+nAlto);
        g2.drawLine(desplaz+(iX+desfase*4)+(largoT/2), iZ+4, desplaz+(iX+desfase*4+4)+(largoT/2), (iZ+4));

      

       
       
    }

  
}