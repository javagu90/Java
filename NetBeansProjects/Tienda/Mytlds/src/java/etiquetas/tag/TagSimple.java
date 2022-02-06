/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jav
 */
package etiquetas.tag;
import javax.servlet.jsp.tagext.TagSupport;

public class TagSimple extends TagSupport 
{
    public int doStartTag()
    {
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag()
    {
        return SKIP_PAGE;
    }
}
