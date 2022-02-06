/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jav
 */
package etiquetas;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EtiquetaSTS extends SimpleTagSupport
{
  public void doTag() throws IOException
  {
      JspWriter out= getJspContext().getOut();
      out.println("Hola desde mi STS de un TLD");
  }
}
