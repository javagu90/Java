/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etiquetas.tag;

import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Jav
 */
public class TagSimple2 extends TagSupport
{
    public int doStartTag()
    {
        return SKIP_BODY;
    }
    
    public int doEndTag()
    {
        return EVAL_PAGE;
    }
}
