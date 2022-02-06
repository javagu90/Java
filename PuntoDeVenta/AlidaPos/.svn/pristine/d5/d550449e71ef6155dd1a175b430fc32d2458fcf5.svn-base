package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CategoriaProductoFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.facade.PromocionFacade;
import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.model.Categoriaproducto;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Promocion;
import com.alidasoftware.pos.model.Tienda;
import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Debug.User;
import com.lowagie.text.pdf.AcroFields.Item;
 
public class PromocionBean  extends AbstractBean implements Serializable {	
	private static final long serialVersionUID = -8737907002198953963L;
	
	private PromocionFacade promocionFacade;
	private List<Promocion> promociones;
	private List<Promocion> promocionesFiltradas;
	private Promocion selected;
	private Producto producto;
	private ProductoFacade productoFacade;
	
	// Manejo de tiendas
	private TiendaFacade tiendaFacade;
	private List<Tienda> tiendas;
	private List<SelectItem> itemsTiendas;
	private Integer tiendaSelected;

	//added
	private CategoriaProductoFacade categoriaProductoFacade;
	private String categoriaProductosSeleccionada;
	private List<Categoriaproducto> categoriaProductos;
	private List<Categoriaproducto> categoriaProductosSeleccionadas;
	
    private DualListModel<Categoriaproducto> categorias;
    private String currentCategoriasSeleccionadas;
    private String currentProductoSeleccionado;
    private String currentElementosSeleccionados;
    private String currentTipoPromocion;
    private Boolean modoProducto;
    private Boolean modoCategorias;
    
    private String searchNombre;
    private String searchDescuento;
    private String searchEstatus;
    
    private Integer tipoPromocion;
    
	private ProductoFacade getProductoFacade() {
		if (productoFacade == null) {
			productoFacade = new ProductoFacade();
		}
		return productoFacade;
	}
	
	private TiendaFacade getTiendaFacade(){
		if(tiendaFacade == null){
			tiendaFacade = new TiendaFacade();
		}
		return tiendaFacade;
	}

    
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getCurrentTipoPromocion() {
		if (modoProducto==true && modoCategorias==false) {
			currentTipoPromocion="Promoción por producto";
		}
		if (modoProducto==false && modoCategorias==true) {
			currentTipoPromocion="Promoción por categorías";
		}
		if (modoProducto==false && modoCategorias==false) {
			currentTipoPromocion="Ninguno";
		}
		return currentTipoPromocion;
	}

	public void setCurrentTipoPromocion(String currentTipoPromocion) {
		this.currentTipoPromocion = currentTipoPromocion;
	}

	public String getCurrentProductoSeleccionado() {
		return currentProductoSeleccionado;
	}

	public void setCurrentProductoSeleccionado(String currentProductoSeleccionado) {
		this.currentProductoSeleccionado = currentProductoSeleccionado;
	}

	public String getCurrentElementosSeleccionados() {
		if (modoProducto==true && modoCategorias==false) {
			currentElementosSeleccionados = currentProductoSeleccionado;
		}
		if (modoProducto==false && modoCategorias==true) {
			currentElementosSeleccionados = currentCategoriasSeleccionadas;
		}
		if (modoProducto==false && modoCategorias==false) {
			currentElementosSeleccionados="Ninguno";
		}
		
		return currentElementosSeleccionados;
	}

	public void setCurrentElementosSeleccionados(
			String currentElementosSeleccionados) {
		this.currentElementosSeleccionados = currentElementosSeleccionados;
	}

	public Integer getTipoPromocion() {
		return tipoPromocion;
	}

	public void setTipoPromocion(Integer tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	public String getCurrentCategoriasSeleccionadas() {
		return currentCategoriasSeleccionadas;
	}

	public void setCurrentCategoriasSeleccionadas(
			String currentCategoriasSeleccionadas) {
		this.currentCategoriasSeleccionadas = currentCategoriasSeleccionadas;
	}


	public DualListModel<Categoriaproducto> getCategorias() {
		if (categorias == null) {
			categoriaProductosSeleccionadas = new ArrayList<Categoriaproducto>();
			categorias = new DualListModel<Categoriaproducto>(getCategoriaProductos(), categoriaProductosSeleccionadas);
		}
		return categorias;
	}


	public void setCategorias(DualListModel<Categoriaproducto> categorias) {
		this.categorias = categorias;
	}


	public CategoriaProductoFacade getCategoriaProductoFacade() {
		if (categoriaProductoFacade ==null) {
			categoriaProductoFacade = new CategoriaProductoFacade();
		}
		return categoriaProductoFacade;
	}


	public void setCategoriaProductoFacade(
			CategoriaProductoFacade categoriaProductoFacade) {
		this.categoriaProductoFacade = categoriaProductoFacade;
	}


	public List<Categoriaproducto> getCategoriaProductos() {
		if (categoriaProductos == null) {
			loadCategoriaProductos();
		}
		return categoriaProductos;
	}


	public void setCategoriaProductos(List<Categoriaproducto> categoriaClientes) {
		this.categoriaProductos = categoriaClientes;
	}


	public String getCategoriaProductosSeleccionada() {
		return categoriaProductosSeleccionada;
	}


	public void setCategoriaProductosSeleccionada(String categoriaProductosSeleccionada) {
		this.categoriaProductosSeleccionada = categoriaProductosSeleccionada;
	}


	public PromocionBean() {
		prepareCreate();
		loadPromociones();
	}


	public PromocionFacade getPromocionFacade() {
        if (promocionFacade == null) {
            promocionFacade = new PromocionFacade();
        }
        return promocionFacade;
    }

	
	public List<Promocion> getPromociones() {
		if (promociones==null) {
			loadPromociones();
		}		
		return promociones;
	}
	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}
	public List<Promocion> getPromocionesFiltradas() {
		return promocionesFiltradas;
	}
	public void setPromocionesFiltradas(List<Promocion> promocionesFiltradas) {
		if (promocionesFiltradas==null) {
			loadPromociones();
		}		
		this.promocionesFiltradas = promocionesFiltradas;
	}
	public Promocion getSelected() {
		return selected;
	}

	
	public void setSelected(Promocion selected) {
		this.selected = selected;
	}
	public Promocion prepareCreate() {
		//crear selected
		selected = new Promocion();
		
		//iniciar vars
		setCategorias(null);
		setCategoriaProductos(null);
        //llenar el objeto con categorias seleccionadas
        getCategorias();
				
        //cargar categorias de productos
        selected.setCategoriaproductos(new ArrayList<Categoriaproducto>());
        loadCategoriaProductos();

        
        setCurrentCategoriasSeleccionadas("Ninguno");                     
        setCurrentProductoSeleccionado("Ninguno");
        setCurrentElementosSeleccionados("Ninguno");
        selected.setFechainicio(new Date());
        selected.setFechafin(new Date());
        selected.setActiva(true);
        
        modoProducto =false;
        modoCategorias=false;        
       
        getCurrentTipoPromocion();
        
        // Obtener las lista
        itemsTiendas = new ArrayList<SelectItem>();
        try{
        	tiendas = getTiendaFacade().listAllActive();
        	for(Tienda aux : tiendas){
        		itemsTiendas.add(new SelectItem(aux.getIdtienda(), aux.getNombre()));
        	}
        } catch (Exception ex){
        	System.out.println("Error obtener tiendas " + ex.getMessage());
        }
        
        return selected;
    }
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {
			
			if(checkDuplicatedCode()){
				return;
			}
			
			if (modoProducto==true && modoCategorias==false) {
				if (currentElementosSeleccionados.equals("Ninguno")) {
					displayErrorMessageToUser("Se debe seleccionar un producto");
					return;					
				}else{
					Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"set modo producto...");
					
					//establecer las categorias de productos
					selected.setCategoriaproductos(null);			
					//producto 
					selected.setIdproducto(producto.getIdproducto());
					
				}
			}
			if (modoProducto==false && modoCategorias==true) {
				if (currentElementosSeleccionados.equals("Ninguno")) {
					displayErrorMessageToUser("Se debe seleccionar al menos una categoría");
					return;					
				}else{
					Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"set modo categorias...");
					
					//establecer las categorias de productos
					selected.setCategoriaproductos(categorias.getTarget());			
					Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:categoriaproducto:SIZE: " + selected.getCategoriaproductos().size());
					//producto 
					selected.setIdproducto(-1);
				}
			}
			if (modoProducto==false && modoCategorias==false) {
				displayErrorMessageToUser("Se debe seleccionar un tipo de promoción");
				return;					
			}
			
			selected.setTienda(getTiendaFacade().findTienda(tiendaSelected));
			
	        selected.setActiva(true);
	        selected.setDescuento(selected.getDescuento()/100);

            getPromocionFacade().createPromocion(selected);
            closeDialog("PromocionCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadPromociones();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {			
			selected.setActiva(false);
			
            getPromocionFacade().updatePromocion(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadPromociones();
            //update from Bean
            RequestContext.getCurrentInstance().update("PromocionListForm:datalist");
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void doSearch(){
		selected = null;	
		try {
			promocionesFiltradas = getPromocionFacade().findByNombreDescuentoEstatus(searchNombre, searchDescuento, searchEstatus);
			System.out.println("el numero de promociones es : " + promociones.size());
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchDescuento = "";
    	searchEstatus = "";
		RequestContext.getCurrentInstance().update("EmpleadoListForm:empleadoListPanel");
	}
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("el nombre a buscar es : "
					+ selected.getNombre());
			Promocion auxPromo = getPromocionFacade().findPromocionByNombre(selected.getNombre());
			if (auxPromo != null) {
				selected.setNombre(null);
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_INFO,
										"El nombre de la promocion ya existe. Debe seleccionar otro nombre",
										"El nombre de la promocion ya existe. Debe seleccionar otro nombre"));
				result = true;
				displayErrorMessageToUser("Debe seleccionar otro nombre");
			}
		} catch (AlidaPosException ex) {
			System.out.println("Error : " + ex.getMessage());
			keepDialogOpen();
			displayErrorMessageToUser(ex.getMessage());
		}
		return result;
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Promocion) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("EmpleadoViewDlg");
	}
	
	private void loadPromociones() {
		try {
			promociones = getPromocionFacade().listAll();
			promocionesFiltradas = new ArrayList<Promocion>();
//			for (Promocion item : promociones) {
//				if (item.getActiva()) {
//					promocionesFiltradas.add(item);
//				}
//			}
			promocionesFiltradas = promociones;
			
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	private void loadCategoriaProductos() {
		try {
			categoriaProductos = getCategoriaProductoFacade().listAll();
		} catch (AlidaPosException e) {
			displayErrorMessageToUser("Error: " + e.getMessage());		}
    }	
	
    public void onTransfer(TransferEvent event) {
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"target Size: "+ categorias.getTarget().size());
        StringBuilder builder = new StringBuilder();   
        
        for(Object item : categorias.getTarget()) {
        	if (builder.length()>0) {
        		builder.append(", ");
			}
            builder.append(((Categoriaproducto) item).getNombre());
        }        
        if (builder.toString().length() == 0) {
            setCurrentCategoriasSeleccionadas("ninguna");			
		}else{
			setCurrentCategoriasSeleccionadas(builder.toString());
		}
        Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Items Transferred: "+ getCurrentCategoriasSeleccionadas());
    }      
    public void onTabChange(TabChangeEvent event) {
    	//si se cambió a modo categoria
    	if (event.getTab().getTitle().equals("Categorías en promoción")) {
    		modoCategorias =true;
    		modoProducto =false;
    		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Modo Categorias");    		
		}else{
    		modoCategorias =false;
    		modoProducto =true;
    		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Modo producto");
			
		}    		  	
	}
	       
	public void onTabClose(TabCloseEvent event) {
    	//si se cambió a modo categoria
    	if (event.getTab().getTitle().equals("Categorías en promoción")) {
    		modoCategorias =false;
    		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Cerrado Categorias");
		}else{
    		modoProducto =false;
    		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Cerrado producto");
			
		}    		  	
	}

	public List<Producto> completeProducto(String query) {
		List<Producto> aux = new ArrayList<Producto>();
		try {
			aux = getProductoFacade().FindProductLikeName(query);
		} catch (AlidaPosException ex) {			
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }			
		return aux; 
	}
	public void handleSelectProduct(SelectEvent event) {
		producto = (Producto) event.getObject();
		setCurrentProductoSeleccionado(producto.getNombre());
	}


	public String getSearchNombre() {
		return searchNombre;
	}


	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}


	public String getSearchDescuento() {
		return searchDescuento;
	}


	public void setSearchDescuento(String searchDescuento) {
		this.searchDescuento = searchDescuento;
	}


	public String getSearchEstatus() {
		return searchEstatus;
	}


	public void setSearchEstatus(String searchEstatus) {
		this.searchEstatus = searchEstatus;
	}

	public List<SelectItem> getItemsTiendas() {
		return itemsTiendas;
	}

	public void setItemsTiendas(List<SelectItem> itemsTiendas) {
		this.itemsTiendas = itemsTiendas;
	}

	public Integer getTiendaSelected() {
		return tiendaSelected;
	}

	public void setTiendaSelected(Integer tiendaSelected) {
		this.tiendaSelected = tiendaSelected;
	}
	
	
}
