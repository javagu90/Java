/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etiquetas;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

/**
 *
 * @author Jav
 */
public class Mayus extends SimpleTagSupport
{
    private String nombre;
    public StringWriter writer;
    public Mayus(){}

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void doTag() throws IOException, JspException
    {
        writer= new StringWriter();
        JspWriter out= getJspContext().getOut();
        getJspBody().invoke(null); // no tiene accesso al cuerpo
       getJspBody().invoke(writer); //accesso al cuerpo
       out.println(writer.toString().toUpperCase());

    }
    
}
