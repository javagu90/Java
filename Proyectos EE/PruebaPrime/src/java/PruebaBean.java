/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Javy
 */
@ManagedBean
@RequestScoped
public class PruebaBean {

    /**
     * Creates a new instance of PruebaBean
     */
    public PruebaBean() {
    }
    
    public void refresh()
    {
          RequestContext context = RequestContext.getCurrentInstance();
     context.update("data");
    }
}
