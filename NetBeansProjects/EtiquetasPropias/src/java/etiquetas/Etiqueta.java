/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etiquetas;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Jav
 */
public class Etiqueta extends SimpleTagSupport
{
    public void doTag()  throws IOException, JspException
    {
        JspWriter out= getJspContext().getOut();
     //con cuerpo   
        getJspBody().invoke(null);
    }
}
