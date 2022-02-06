package com.alidasoftware.pos.bean;

 
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
 


import org.primefaces.context.RequestContext;

import com.alidasoftware.pos.facade.UsuarioFacade;
import com.alidasoftware.pos.model.Modulo;
import com.alidasoftware.pos.model.Usuario;
import com.alidasoftware.pos.util.Utils;
 
public class PermisoBean extends AbstractBean implements Serializable {
     
	private Usuario user;
	private UsuarioFacade usuarioFacade;
	private boolean permisoCorteParcial;
	private boolean permisoCierreCaja;
	private String url;
	private String request;
		
	
	private static final long serialVersionUID = -1465582035400274003L;

	private String username;
     
    private String password;
 

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
    
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public String login() {
    	System.out.println("Logiin permisos");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage mnsg = null;
		try {
			user = getUsuarioFacade().findUserByNamePwd(username, password);
			if (user != null) 
			{
				if (user.isActivo())
				{
					
					for(Modulo modulo: user.getPerfil().getModulos())
						{
							System.out.println(modulo.getNombre());
							if(modulo.getNombre().equals("Corte de Caja")) 
							{
								permisoCierreCaja=true;
							}
							if(modulo.getNombre().equals("Corte Parcial"))
							{
								permisoCorteParcial=true;
							}
						}
					verificarPermisos(request);
				}
			} 
		} 
		catch (Exception ex) 
		{
			
			mnsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario o Contraseña no válidas");
		}
		username = "";
		password = "";
		FacesContext.getCurrentInstance().addMessage(null, mnsg);
		RequestContext.getCurrentInstance().update(":growl");
		return null;
	}
    
    private boolean isAccesoPermitido(String request)
    {
		for(Modulo modulo: user.getPerfil().getModulos())
		{
			System.out.println(modulo.getNombre());
			if(modulo.getNombre().toLowerCase().equals(Utils.CAJA_CORTE_PARCIAL.toLowerCase()) && request.equals("corteparcial"))
			{
				System.out.println("IF DE CORTE PARCIAL Y CORTE PARCIAL");
				//permisoCorteParcial=true;
				url="/corteParcial/List.xhtml?faces-redirect=true";
				return true;
			}
			else
			{
				if(modulo.getNombre().toLowerCase().equals(Utils.CAJA_CORTE.toLowerCase()) && request.equals("cortecaja"))
				{
					System.out.println("IF DE CORTE DE CAJA Y CORTE DE CAJA");
					//permisoCierreCaja=true;
					url="/corteCaja/List.xhtml?faces-redirect=true";
					return true;
				}
			}
		}
    	return false;
    }
    
    
    public String verificarPermisos(String request)
    {
		System.out.println("LLAME A LA FUNCION");
    	user=getLoginBean().getUser();
    	this.request=request;
    	if(user!=null)
    	{
    		if (user.isActivo() && isAccesoPermitido(request))
			{
    			return url;
			}
    		else
    		{	
    			if(user.isActivo() && permisoCierreCaja && request.equals("cortecaja"))
    			{
    				url="/corteCaja/List.xhtml?faces-redirect=true";
    				return url;
    			}
    			else
    			{
    				if(user.isActivo() && permisoCorteParcial && request.equals("corteparcial"))
    				{
    					url="/corteParcial/List.xhtml?faces-redirect=true";
    					return url;
    				}
    				else
    				{
    	    			permisoDialogAction();
    	    			return null;   
    				}
    			}			
			} 	
    	}
    	else
    	{
    		
    	}
    	return url;	
    }

    
	public String permisoDialogAction() {
		RequestContext.getCurrentInstance().update("permisoForm:panelpermiso");
		RequestContext.getCurrentInstance().execute("PF('permisoDialog').show();");
		return null;
	}
    
	private LoginBean getLoginBean() {
		  return (LoginBean) Utils.getManagedBean("loginBean");
		 }
	
    public UsuarioFacade getUsuarioFacade() {
		if (usuarioFacade == null) {
			usuarioFacade = new UsuarioFacade();
		}
		return usuarioFacade;
	}
    
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}
	

	public boolean isPermisoCorteParcial() {
		return permisoCorteParcial;
	}

	public void setPermisoCorteParcial(boolean permisoCorteParcial) {
		this.permisoCorteParcial = permisoCorteParcial;
	}

	public boolean isPermisoCierreCaja() {
		return permisoCierreCaja;
	}

	public void setPermisoCierreCaja(boolean permisoCierreCaja) {
		this.permisoCierreCaja = permisoCierreCaja;
	}
    
}