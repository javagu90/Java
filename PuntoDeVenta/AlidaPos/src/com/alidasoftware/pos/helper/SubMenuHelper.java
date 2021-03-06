package com.alidasoftware.pos.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alidasoftware.pos.util.Utils;

public class SubMenuHelper implements Serializable {

	private static final long serialVersionUID = 5881811283960830400L;
	
	private String title = "" ;
	private List<MenuItemHelper> itemList = new ArrayList<MenuItemHelper>();
	private String icon = "";
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<MenuItemHelper> getItemList() {
		return itemList;
	}
	
	public void setItemList(List<MenuItemHelper> itemList) {
		this.itemList = itemList;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public SubMenuHelper(String title, String icon) {
		this.title = title;
		this.icon = icon;
	}
	
	public void addMenuItem(MenuItemHelper menuItem) {
		if (itemList == null) {
			itemList = new ArrayList<MenuItemHelper>();
		}
		itemList.add(menuItem);
	}

	public static SubMenuHelper getMenuCatalogos() {
		SubMenuHelper subMenu = new SubMenuHelper("Catálogos", "");
		subMenu.addMenuItem(new MenuItemHelper("Caja", "/catalogos/caja/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Categoria Cliente", "/catalogos/categoriacliente/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Unidad", "/catalogos/unidad/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Forma de Pago", "/catalogos/formapago/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Tienda", "/catalogos/tienda/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Categoria Producto", "/catalogos/categoriaproducto/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Tipo de Vencimiento","/catalogos/tipovencimiento/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Dias Festivos", "/catalogos/diasfestivos/List.xhtml", "", true));	            
		subMenu.addMenuItem(new MenuItemHelper("Usuarios", "/catalogos/usuario/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Empleados", "/catalogos/empleado/List.xhtml", "", true));	            
		subMenu.addMenuItem(new MenuItemHelper("Clientes", "/catalogos/cliente/List.xhtml", "", true));  
		subMenu.addMenuItem(new MenuItemHelper("Proveedores", "/catalogos/proveedor/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Promoción", "/catalogos/promocion/List.xhtml", "", true));
	    subMenu.addMenuItem(new MenuItemHelper("Productos", "/catalogos/producto/List.xhtml", "", true));			            			            
	    subMenu.addMenuItem(new MenuItemHelper("Traspasos", "/catalogos/traspaso/List.xhtml", "", true));
	    subMenu.addMenuItem(new MenuItemHelper("Perfiles", "/catalogos/perfil/List.xhtml", "", true));
		return subMenu;
	}
	
	public static SubMenuHelper getMenuVentas() {
		SubMenuHelper subMenu = new SubMenuHelper("Ventas", "");
		subMenu.addMenuItem(new MenuItemHelper("Venta", "/ventas/venta/List.xhtml", "#{ventaBean.showListDialogAction}", ""));
		subMenu.addMenuItem(new MenuItemHelper("Vista", "/ventas/vista/List.xhtml", "#{vistaBean.showListDialogAction}", ""));
		subMenu.addMenuItem(new MenuItemHelper("Apartado", "/ventas/apartado/List.xhtml", "#{apartadoBean.showListDialogAction}", ""));
		subMenu.addMenuItem(new MenuItemHelper("Pagos", "/adeudos/List.xhtml", "", true));
		subMenu.addMenuItem(
				new MenuItemHelper("Vales", 
								   "/vales/List.xhtml", 
								   "#{valeBean.displaySelectedView}", 
								   Utils.PARAM_MENU_VALES, ""));
		subMenu.addMenuItem(
				new MenuItemHelper("Devolución", 
								   "/devolucion/List.xhtml", 
								   "#{devolucionBean.displaySelectedView}", 
								   Utils.PARAM_MENU_DEVOLUCION, 
								   ""));
		return subMenu;
	}
	
	public static SubMenuHelper getMenuCaja() {
		SubMenuHelper subMenu = new SubMenuHelper("Caja", "");
		subMenu.addMenuItem(new MenuItemHelper("Apertura Caja", "", "#{corteCajaBean.openCajaDialogAction}", ""));
		subMenu.addMenuItem(new MenuItemHelper("Corte Parcial", "/corteParcial/List.xhtml", "#{permisoBean.verificarPermisos('corteparcial')}", ""));
		subMenu.addMenuItem(new MenuItemHelper("Cierre de Caja", "/corteCaja/List.xhtml", "#{permisoBean.verificarPermisos('cortecaja')}", ""));	
		return subMenu;
	}
	
	public static MenuItemHelper getMenuConfiguracion() {
		return new MenuItemHelper("Configuración", "/configuracion/List.xhtml", "", true);
	}
	
	public static SubMenuHelper getMenuFacturacion(){
		SubMenuHelper subMenu = new SubMenuHelper("Facturación", "");
		subMenu.addMenuItem(new MenuItemHelper("Facturar","/facturacion/facturar/List.xhtml", "", true));
		subMenu.addMenuItem(new MenuItemHelper("Configuración", "/facturacion/configuracion/List.xhtml", "", true));	         
		return subMenu;
	}
	
}
