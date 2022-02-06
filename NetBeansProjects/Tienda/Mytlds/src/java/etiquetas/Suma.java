/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etiquetas;

/**
 *
 * @author Jav
 */

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.*;
import java.io.IOException;

public class Suma extends SimpleTagSupport
{
    private int sumando1;
    private int sumando2;
    
        public Suma(){}
        
        public void setSumando1(int sumando1)
        {
            this.sumando1=sumando1;
        }        
        public void setSumando2(int sumando2)
        {
            this.sumando2=sumando2;
        }
        
        public void doTag() throws IOException
        {
            JspWriter out= getJspContext().getOut();
            int resultado=sumando1+sumando2;
            out.println(sumando1+" + "+sumando2+" = "+resultado);
        }
}
