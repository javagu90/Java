package com.alidasoftware.pos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.alidasoftware.pos.facade.UsuarioFacade;
import com.alidasoftware.pos.helper.AccesoMenuHelper;
import com.alidasoftware.pos.helper.MenuItemHelper;
import com.alidasoftware.pos.helper.ScoreMenuHelper;
import com.alidasoftware.pos.helper.SubMenuHelper;
import com.alidasoftware.pos.model.Modulo;
import com.alidasoftware.pos.model.Tienda;
import com.alidasoftware.pos.model.Usuario;
import com.alidasoftware.pos.util.Functions;
import com.alidasoftware.pos.util.JpaUtil;
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
	private List<Modulo> userModules = null;
	private DefaultMenuModel menu = new DefaultMenuModel();
	private DefaultMenuModel dockMenu = new DefaultMenuModel();
	private AccesoMenuHelper accesoMenu = new AccesoMenuHelper();
	private Map<Integer, Object> dockMenuItems = new HashMap<Integer, Object>();

	public DefaultMenuModel getDockMenu() {
		return dockMenu;
	}

	public void setDockMenu(DefaultMenuModel dockMenu) {
		this.dockMenu = dockMenu;
	}

	public MenuModel getMenu() {
        return menu;
    }
 
    public void setMenu(DefaultMenuModel menu) {
        this.menu = menu;
    }
    
	public List<Modulo> getUserModules() {
		return userModules;
	}

	public void setUserModules(List<Modulo> userModules) {
		this.userModules = userModules;
	}

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

	public boolean isLogeado() {
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
		
	}
	
	public String login() {
		FacesMessage msg = null;
		try {
			String pwd = Functions.getMD5(clave);
			user = getUsuarioFacade().findUserByNamePwd(nombre, pwd);
			if (user != null) {
				if (user.isActivo()) {
					logeado = true;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nombre);
					try {
						HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
						session.setAttribute("usuario", user.getClave());
						userModules = user.getPerfil().getModulos();
						createUserMenu();
						/*
						String sample = "";
						sample = "28:4";
						sample = "23:5|3:26";
						sample = "6:30|31:50|19:12";
						accesoMenu = new AccesoMenuHelper(userModules, sample);
						*/
						//System.out.println("Init Access: " + user.getAccesoMenu());
						accesoMenu = new AccesoMenuHelper(userModules, user.getAccesoMenu());
						updateDockMenu();
						
					} catch (Exception ex) {
						System.out.println("Error aqui....");						
					}
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
		if (logeado) {
			return "/menu.xhtml?faces-redirect=true";
		}
		return null;
	}
	
	public void logoutActionBtn(ActionEvent actionEvent) {
		logoutAction();
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext.getCurrentInstance().update(":mensajes");
    }
    
    public void cleanUpBeans() {
    	getApartadoBean().cleanUp();
		getVentaBean().cleanUp();
		getCorteCajaBean().cleanUp();
    }

	public String logoutAction() {		
		cleanUpBeans();
		try {			
			//System.out.println("stringAccess: " + accesoMenu.getScoreString());
			user.setAccesoMenu(accesoMenu.getScoreString());
			getUsuarioFacade().updateUsuario(user);
		} catch (Exception ex) {
			System.out.println("Err: " + ex.getMessage());
		}
		logeado = false;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			session.setAttribute("usuario", null);
			session.invalidate();
			session = null;
			user = null;			
		}
		return "/login.xhtml?faces-redirect=true";
	}
	
	public void updateStatistics(String path) {
		Modulo modulo = null;
		for (int i = 0; i < userModules.size(); i++) {
			modulo = userModules.get(i);
			if (modulo.getPath() != null) {
				if (path.startsWith(modulo.getPath())) {
					accesoMenu.updateScore(modulo.getIdModulo());
					updateDockMenu();
					break;
				}
			}
		}		
		
	}
	
	public void updateDockMenu() {
		try {
			dockMenu = new DefaultMenuModel();
			//dockMenu.generateUniqueIds();
			List<ScoreMenuHelper> scoreList = accesoMenu.getScoreList();
			int maxMenuItems = scoreList.size();
			if (scoreList.size() >= Utils.MAX_DOCK_MENUITEMS) {
				maxMenuItems = Utils.MAX_DOCK_MENUITEMS; 
			}
			for (int i = 0; i < maxMenuItems; i++) {	
				if (scoreList.get(i).getScore() > 0) {
					MenuItem item = (MenuItem) dockMenuItems.get(scoreList.get(i).getIdModule());
					if (dockMenu.getElements().size() < Utils.MAX_DOCK_MENUITEMS) {
						if (item != null) {					
							dockMenu.addElement(item);
						}
					}
				}
			}
		} catch (Exception ex) {
			dockMenu = new DefaultMenuModel();
			dockMenu.generateUniqueIds();
		}
	}
	
	private DefaultMenuItem getMenuItem(MenuItemHelper itemHelper) {
		String path = "";
		int idModule = -1;
		DefaultMenuItem item = null;
		DefaultMenuItem dockItem = null;
		if (!itemHelper.getActionListener().equals("")) {
			path = itemHelper.getOutcome();
			if (isRenderedMenuItem(path)) {
				item = new DefaultMenuItem(itemHelper.getTitle());
				item.setCommand(itemHelper.getActionListener());
				item.setParam(itemHelper.getParamName(), itemHelper.getParamValue());
				
				dockItem = new DefaultMenuItem(itemHelper.getTitle());
				dockItem.setCommand(itemHelper.getActionListener());
				dockItem.setParam(itemHelper.getParamName(), itemHelper.getParamValue());
				idModule = getIdModuleByPath(path);
			}
		} else {
			if (!itemHelper.getAction().equals("") && !itemHelper.getOutcome().equals("")) {
				path = itemHelper.getOutcome();
				if (isRenderedMenuItem(path)) {
					item = new DefaultMenuItem(itemHelper.getTitle());
					item.setCommand(itemHelper.getAction());	
					dockItem = new DefaultMenuItem(itemHelper.getTitle());
					dockItem.setCommand(itemHelper.getAction());
					idModule = getIdModuleByPath(path);
				}
			} else {
				if (!itemHelper.getAction().equals("")) {
					item = new DefaultMenuItem(itemHelper.getTitle());
					item.setCommand(itemHelper.getAction());					
					dockItem = new DefaultMenuItem(itemHelper.getTitle());
					dockItem.setCommand(itemHelper.getAction());
				} else {
					path = itemHelper.getOutcome();
					if (isRenderedMenuItem(path)) {
						item = new DefaultMenuItem(itemHelper.getTitle());
						item.setOutcome(path);
						dockItem = new DefaultMenuItem(itemHelper.getTitle());
						dockItem.setOutcome(path);
						idModule = getIdModuleByPath(path);
					}
				}
			}
		}
		if (!itemHelper.getIcon().equals("") && item != null) {
			item.setIcon(itemHelper.getIcon());
			dockItem.setIcon(itemHelper.getIcon());
		}
		if (itemHelper.isShowWaitImage() && item != null) {
			item.setOnclick("PF('blockPage').show().show();");
			item.setOncomplete("PF('blockPage').show().hide();");
			dockItem.setOnclick("PF('blockPage').show().show();");
			dockItem.setOncomplete("PF('blockPage').show().hide();");
		}
		if (item != null && idModule != -1) {
			dockMenuItems.put(idModule, dockItem);
		}
		return item;
	}
	
	private DefaultSubMenu getSubMenu(int submenuOption) {		
		DefaultSubMenu submenu = null;
		SubMenuHelper submenuHelper = null;
		switch (submenuOption) {
		case Utils.SUBMENU_OPTION_CATALOGO :
				submenuHelper = SubMenuHelper.getMenuCatalogos();
				break;
		case Utils.SUBMENU_OPTION_VENTA :
				submenuHelper = SubMenuHelper.getMenuVentas();
				break;
		case Utils.SUBMENU_OPTION_CAJA :
				submenuHelper = SubMenuHelper.getMenuCaja();
				break;	
		case Utils.SUBMENU_OPTION_FACTURA :
			 	submenuHelper = SubMenuHelper.getMenuFacturacion();
				break;	
		default:
			break;
		}
		if (submenuHelper.getIcon().equals("")) {
			submenu = new DefaultSubMenu(submenuHelper.getTitle());
		} else {
			submenu = new DefaultSubMenu(submenuHelper.getTitle(), submenuHelper.getIcon());
		}
		List<MenuItemHelper> itemList = submenuHelper.getItemList();
		if (itemList != null && itemList.size() > 0) {
			DefaultMenuItem item = null;
			for (int i = 0; i < itemList.size(); i++) {
				item = getMenuItem(itemList.get(i));				
				if (item != null) {
					submenu.addElement(item);
				}
			}
		}		
		return submenu;
	}
	
	private void createUserMenu() {
		DefaultSubMenu subMenu = null;
		menu = new DefaultMenuModel();
		
		DefaultMenuItem home = new DefaultMenuItem("Home");
		home.setIcon("ui-icon-home");
		home.setOutcome("/menu.xhtml");		
						
		menu.addElement(home);

		subMenu = getSubMenu(Utils.SUBMENU_OPTION_CATALOGO);
		if (subMenu.getElementsCount() > 0 ) {
			menu.addElement(subMenu);
		}

		subMenu = getSubMenu(Utils.SUBMENU_OPTION_VENTA);
		if (subMenu.getElementsCount() > 0 ) {
			menu.addElement(subMenu);
		}
		subMenu = getSubMenu(Utils.SUBMENU_OPTION_CAJA);
		if (subMenu.getElementsCount() > 0 ) {
			menu.addElement(subMenu);
		}
		
		subMenu = getSubMenu(Utils.SUBMENU_OPTION_FACTURA);
		if (subMenu.getElementsCount() > 0 ) {
			menu.addElement(subMenu);
		}
		
		
		
		menu.addElement(getMenuItem(SubMenuHelper.getMenuConfiguracion()));			
	}
	
	public boolean isRenderedMenuItem(String path) {
		Modulo modulo = null;
		if (userModules == null) {
			return false;
		} else {
			for (int i = 0; i < userModules.size(); i++) {
				modulo = userModules.get(i);
				if (modulo.getPath() != null) {
					if (path.startsWith(modulo.getPath()) && modulo.getActivo()) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public int getIdModuleByPath(String path) {
		Modulo modulo = null;
		if (userModules == null) {
			return -1;
		} else {
			for (int i = 0; i < userModules.size(); i++) {
				modulo = userModules.get(i);
				if (modulo.getPath() != null) {
					if (path.startsWith(modulo.getPath()) && modulo.getActivo()) {
						return modulo.getIdModulo();
					}
				}
			}
			return -1;
		}
	}
	
	public void init() throws IOException {
	    if (!isLogeado()) {
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        try {
	        	externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
	        } catch (Exception ex) {
	        	
	        }
	    }	   
	}

	public void showAccessErrorMessage() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No tiene acceso a este modulo, Verifique con el Administrador del sistema");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update(":mensajes");
	}
	
	/*
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
    */
	
	public void valueChangeTienda() {
		// por si se requiere validar algo al seleccionar la tienda
		System.out.println("tienda valueChange: " + tienda.getNombre());
	}

}
