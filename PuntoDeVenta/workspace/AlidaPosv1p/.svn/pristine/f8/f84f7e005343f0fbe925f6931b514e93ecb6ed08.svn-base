package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.UsuarioFacade;
import com.alidasoftware.pos.model.Tienda;
import com.alidasoftware.pos.model.Usuario;
import com.alidasoftware.pos.util.Utils;

public class LoginBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -3503143108799389351L;
	private String nombre;
	private String clave;
	private boolean logeado = false;
	private UsuarioFacade userFacade;
	private Usuario user;
	private Tienda tienda;
	private List<SelectItem> tiendaList;

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public List<SelectItem> getTiendaList() {
		return tiendaList;
	}

	public void setTiendaList(List<SelectItem> tiendaList) {
		this.tiendaList = tiendaList;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean estaLogeado() {
		return logeado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	private UsuarioFacade getUsuarioFacade() {
		if (userFacade == null) {
			userFacade = new UsuarioFacade();
		}
		return userFacade;
	}
	
	private VentaBean getVentaBean() {
		return (VentaBean) Utils.getManagedBean("ventaBean");
	}
	
	private ApartadoBean getApartadoBean() {
		return (ApartadoBean) Utils.getManagedBean("apartadoBean");
	}
	
	private CorteCajaBean getCorteCajaBean() {
		return (CorteCajaBean) Utils.getManagedBean("corteCajaBean");
	}
	
	
	public LoginBean() {
		loadTiendas();
	}
	
	public String login() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		try {
			user = getUsuarioFacade().findUserByNamePwd(nombre, clave);
			if (user != null) {
				if (user.isActivo()) {
					logeado = true;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nombre);
				} else {
					logeado = false;
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario inhabilitado...");
				}				
			} else {
				logeado = false;
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario o Contraseña no válidas");
			}
		} catch (Exception ex) {
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario o Contraseña no válidas");
		}
		nombre = "";
		clave = "";
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update(":mensajes");
		//context.addCallbackParam("estaLogeado", logeado);
		if (logeado) {
			//context.addCallbackParam("view", "menu.xhtml");
			return "/menu.xhtml?faces-redirect=true";
		}
		return null;
	}

	public String logoutAction() {		
		getApartadoBean().cleanUp();
		getVentaBean().cleanUp();
		getCorteCajaBean().cleanUp();
		logeado = false;
		System.out.println("********************************");
		System.out.println("******** SESION CERRADA ********");
		System.out.println("********************************");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().show();");
		System.out.println("--- EJECUTANDO LOGOUT ACTION ---");
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
		return "/login.xhtml?faces-redirect=true";
	}
	
	private void loadTiendas() {
		try {
			tiendaList = new ArrayList<SelectItem>();
			List<Tienda> list = ApplicationBean.getStoreList();
			if (list != null && list.size() > 0) {
				String label = "";
				for (int i = 0; i < list.size(); i++) {
					label = list.get(i).getNombre();
					tiendaList.add(new SelectItem( list.get(i), label));				
				}			
				//tienda = list.get(0);				
			} else {
				keepDialogOpen();
	            displayErrorMessageToUser("Error: No exiten registros de Tienda.. verifique catálogo");
			}
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void valueChangeTienda() {
		// por si se requiere validar algo al seleccionar la tienda
		System.out.println("tienda valueChange: " + tienda.getNombre());
	}

}
