package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.DetalleInventarioFacade;
import com.alidasoftware.pos.facade.InventarioFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.facade.TraspasoFacade;
import com.alidasoftware.pos.model.Detalleinventario;
import com.alidasoftware.pos.model.Detalletraspaso;
import com.alidasoftware.pos.model.Inventario;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Tienda;
import com.alidasoftware.pos.model.Traspaso;
import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Debug.User;

public class TraspasoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 6275591448719976691L;

	private TraspasoFacade traspasoFacade;
	private List<Traspaso> traspasos;
	private List<Traspaso> traspasosFiltrados;
	private Traspaso selected;
	private Producto producto;
	
	private TiendaFacade tiendaFacade;
	private String tiendaSeleccionada1;
	private String tiendaSeleccionada2;
	private List<Tienda> tiendas;
	private List<Tienda> tiendasDestino;

	private ProductoFacade productoFacade;
	private List<Producto> productosOrigen;
	private List<Producto> productosDestino;
    private DualListModel<Producto> productosTraspaso;

    private float cantidadProducto;
    private float precioUnitarioProducto;
	
    private List<Detalletraspaso> detalleTraspasos;
	private Detalletraspaso productoSeleccionado;
    private float importeTotal;
    
    // PARTE DE INVENTARIOS 
    
    private DetalleInventarioFacade detalleInventarioFacade;
    private InventarioFacade inventarioFacade;
    
    public InventarioFacade getInventarioFacade(){
    	if(inventarioFacade == null) {
    		this.inventarioFacade = new InventarioFacade();
    	}
    	return inventarioFacade;
    }
    
    public DetalleInventarioFacade getDetalleInventarioFacade(){
    	if(detalleInventarioFacade == null){
    		this.detalleInventarioFacade = new DetalleInventarioFacade();
    	}
    	return this.detalleInventarioFacade;
    }
    
    
	public float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Detalletraspaso getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Detalletraspaso productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<Detalletraspaso> getDetalleTraspasos() {
		if (detalleTraspasos ==null) {
			detalleTraspasos = new ArrayList<Detalletraspaso>();			
		}
		return detalleTraspasos;
	}

	public void setDetalleTraspasos(List<Detalletraspaso> detalleTraspasos) {
		this.detalleTraspasos = detalleTraspasos;
	}

	public float getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(float cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public float getPrecioUnitarioProducto() {
		return precioUnitarioProducto;
	}

	public void setPrecioUnitarioProducto(float precioUnitarioProducto) {
		this.precioUnitarioProducto = precioUnitarioProducto;
	}

	public ProductoFacade getProductoFacade() {
	       if (productoFacade == null) {
	    	   productoFacade = new ProductoFacade();
	        }
		return productoFacade;
	}

	public void setProductoFacade(ProductoFacade productoFacade) {
		this.productoFacade = productoFacade;
	}

	public List<Producto> getProductosOrigen() {
		return productosOrigen;
	}

	public void setProductosOrigen(List<Producto> productosOrigen) {
		this.productosOrigen = productosOrigen;
	}

	public List<Producto> getProductosDestino() {
		return productosDestino;
	}

	public void setProductosDestino(List<Producto> productosDestino) {
		this.productosDestino = productosDestino;
	}

	public DualListModel<Producto> getProductosTraspaso() {
		if (productosTraspaso == null) {
			loadProductos();
			productosDestino = new ArrayList<Producto>();
			productosTraspaso = new DualListModel<Producto>(productosOrigen, productosDestino);
		}
		
		return productosTraspaso;
	}

	public void setProductosTraspaso(DualListModel<Producto> productosTraspaso) {
		this.productosTraspaso = productosTraspaso;
	}

	public TiendaFacade getTiendaFacade() {
	       if (tiendaFacade == null) {
	    	   tiendaFacade = new TiendaFacade();
	        }
		return tiendaFacade;
	}

	public void setTiendaFacade(TiendaFacade tiendaFacade) {
		this.tiendaFacade = tiendaFacade;
	}

	public String getTiendaSeleccionada1() {
		return tiendaSeleccionada1;
	}

	public void setTiendaSeleccionada1(String tiendaSeleccionada1) {
		this.tiendaSeleccionada1 = tiendaSeleccionada1;
	}

	public String getTiendaSeleccionada2() {
		return tiendaSeleccionada2;
	}

	public void setTiendaSeleccionada2(String tiendaSeleccionada2) {
		this.tiendaSeleccionada2 = tiendaSeleccionada2;
	}

	public List<Tienda> getTiendasDestino() {
		return tiendasDestino;
	}

	public void setTiendasDestino(List<Tienda> tiendasDestino) {
		this.tiendasDestino = tiendasDestino;
	}

	public List<Tienda> getTiendas() {
		if (tiendas==null) {
			loadTiendas();
		}
		return tiendas;
	}

	public void setTiendas(List<Tienda> tiendas) {
		this.tiendas = tiendas;
	}


	
	public TraspasoBean() {
		loadTraspasos();
		tiendasDestino = new ArrayList<Tienda>();
	}
	
	public TraspasoFacade getTraspasoFacade() {
        if (traspasoFacade == null) {
            traspasoFacade = new TraspasoFacade();
        }
        return traspasoFacade;
    }

	public Traspaso getSelected() {
		return selected;
	}

	public void setSelected(Traspaso selected) {
		this.selected = selected;
	}

	public List<Traspaso> getTraspasos() {
		return traspasos;
	}

	public List<Traspaso> getTraspasosFiltrados() {
		return traspasosFiltrados;
	}

	public void setTraspasosFiltrados(List<Traspaso> traspasosFiltrados) {
		this.traspasosFiltrados = traspasosFiltrados;
	}
	
	public Traspaso prepareCreate() {
        selected = new Traspaso();
        setImporteTotal(0f);
        return selected;
    }
	
	public void actionView(){
		float totalAcumulado = 0f;
		System.out.println("prueba " + selected.getIdtraspaso() + " tienda " + selected.getTienda1().getNombre());
		for (int i = 0; i < selected.getDetalletraspasos().size(); i++) {
			totalAcumulado = totalAcumulado + selected.getDetalletraspasos().get(i).getCantidad()*selected.getDetalletraspasos().get(i).getPreciounitario();
		}
		importeTotal = totalAcumulado;
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Traspaso) event.getObject();
			actionView();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("EmpleadoViewDlg");
	}
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {
			if(detalleTraspasos.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede crear un traspaso sin ningun producto seleccionado.") );
				return;
			}
			selected.setTienda1(getTiendaFacade().findTiendaByNombre(tiendaSeleccionada1));
			selected.setTienda2(getTiendaFacade().findTiendaByNombre(tiendaSeleccionada2));
			selected.setDetalletraspasos(detalleTraspasos);
			selected.setActivo(true);
			selected.setFecha(new Date());
			System.out.println("antes de mandar a crear " + selected.getTienda1().getNombre());
            String result = getTraspasoFacade().insertTransactionTraspaso(selected);
            if(result.equals("Se agrego el Traspaso correctamente.")){
            	closeDialog("TraspasoCreateDialog");
            	displayInfoMessageToUser(result);
                loadTraspasos();
            }            
            displayInfoMessageToUser(result);
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getTraspasoFacade().updateTraspaso(selected);
            closeDialog("TraspasosEditeDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadTraspasos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getTraspasoFacade().updateTraspaso(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadTraspasos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadTraspasos() {
		try {
			traspasos = getTraspasoFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
    public void handleTiendaSelect() {
    	// Se deberia de llenar la lista de tiendas destino 
    	// con las tiendas sobrantes que no esten en la tienda origen seleccionada.
    	loadTiendasDestino();
    	//loadTiendas();
    }
    
	private void loadTiendas() {
		try {
			tiendas = getTiendaFacade().listAllActive();
		} catch (AlidaPosException e) {
			displayErrorMessageToUser("Error: " + e.getMessage());		}
    }
	
	private void loadTiendasDestino(){
		try{
			tiendasDestino = getTiendaFacade().findTiendasDestino(tiendaSeleccionada1);
		} catch(AlidaPosException ex){ 
			System.out.println("Error al cargar tiendas destino: " + ex.getMessage());
		}
	}
	
	private void loadProductos() {
		try {
			productosOrigen =  getProductoFacade().listAll();
		} catch (AlidaPosException e) {
			displayErrorMessageToUser("Error: " + e.getMessage());		}
    }		
    
    public void onCellEdit(CellEditEvent event) {
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"source   : " + event.getSource().getClass().toString());
    	
    	int index = event.getRowIndex();
        Float oldValue = (Float) event.getOldValue();
        Float newValue = (Float) event.getNewValue();
        
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"row index: " + index);
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"col header: " + event.getColumn().getHeaderText());
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"old: " + oldValue);
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"new: " + newValue);

    	//si el neuvo valor es <= 0 conservar antiguo valor
        if (Float.compare(newValue, 0.0f) < 0) {
        	newValue = oldValue;
        	displayErrorMessageToUser("El valor debe ser mayor que cero.");
        } 
        //si la cantidad es 0 se elimina la fila
        if (detalleTraspasos.get(index).getCantidad() == 0) {
        	deleteProductoFromList(detalleTraspasos.get(index).getProducto().getIdproducto());
		}

        if (detalleTraspasos.size()>0) {
        	System.out.println("Si contiene una cantidad " + detalleTraspasos.get(index).getCantidad());
        	System.out.println("se revisa el producto " + detalleTraspasos.get(index).getProducto().getNombre());
        	System.out.println("se revisa el id del producto " + detalleTraspasos.get(index).getProducto().getIdproducto());
        	if(!checkProducto(detalleTraspasos.get(index).getProducto().getIdproducto(), detalleTraspasos.get(index).getCantidad())){
        		detalleTraspasos.get(index).setCantidad(oldValue);
        		return;
        	}
            Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"detalleTraspasos:cantidad: " + detalleTraspasos.get(index).getCantidad());
        	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"detalleTraspasos:precio: " + detalleTraspasos.get(index).getPreciounitario());    	
        	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"importeCalculado: " + detalleTraspasos.get(index).getCantidad()*detalleTraspasos.get(index).getPreciounitario());			
		}

    	calcularImporteTotal();
    }
    
    private void deleteProductoFromList(int id){
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Eliminar:producto2...");    	
		for (int i = 0; i < getDetalleTraspasos().size(); i++) {	
			if (detalleTraspasos.get(i).getProducto().getIdproducto() == id) {				
				getDetalleTraspasos().remove(i);
				break;
			}
		}    	
    }
   
    public void deleteProduct() {
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Eliminar:producto...");

		if (productoSeleccionado != null) {
	    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Eliminar:producto: "+productoSeleccionado.getProducto().getNombre());
	    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Eliminar:producto:id: "+productoSeleccionado.getProducto().getIdproducto());
	    	deleteProductoFromList(productoSeleccionado.getProducto().getIdproducto());					
	    	calcularImporteTotal();
		}else{
	    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Eliminar:producto: null");
		
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
		if (producto != null && producto.getIdproducto() != 0) {
			Detalletraspaso currentProductoEnTraspaso;
			int index = existsProduct(producto.getIdproducto());
			if (index == -1) {
				if(!checkProducto(producto.getIdproducto(), 1)){
					return;
				}
				currentProductoEnTraspaso = new Detalletraspaso();
				currentProductoEnTraspaso.setProducto(producto);
				currentProductoEnTraspaso.setCantidad(1);
				currentProductoEnTraspaso.setPreciounitario(producto.getPrecio());
				currentProductoEnTraspaso.setTraspaso(selected);
				detalleTraspasos.add(currentProductoEnTraspaso);
			}else{
				if(!checkProducto(producto.getIdproducto(), detalleTraspasos.get(index).getCantidad() + 1)){
					return;
				}
				detalleTraspasos.get(index).setCantidad(detalleTraspasos.get(index).getCantidad() + 1);
			}
			calcularImporteTotal();
			producto = new Producto();
			producto = null;
		}		
	}
	
	private int existsProduct(int idProduct) {		
		for (int i = 0; i < getDetalleTraspasos().size(); i++) {
			if (detalleTraspasos.get(i).getProducto().getIdproducto() == idProduct) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean checkProducto(Integer idProducto, float cantidad) {
		boolean result = false;
		System.out.println("entro a checar el producto");
		try {
			Inventario auxInv = getInventarioFacade().findInventarioByTienda(getTiendaFacade().findTiendaByNombre(tiendaSeleccionada1));
			Detalleinventario aux = getDetalleInventarioFacade().findDetalleInventarioByProductTienda(idProducto, auxInv.getIdinventario());
			
			if(aux!=null){
				if(cantidad<=aux.getExistenciaventa()) {
					System.out.println("la cantidad en existencia es mayor a la cantidad a traspasar");
					result = true;
				} else {
					System.out.println("la cantidad a traspasar es mayor a la cantidad en existencia");
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Solo se tienen " + aux.getExistenciaventa() + " productos en existencia en la tienda de Origen.") );
				}
			} else {
				System.out.println("no se encontro un detalle de inventario con el producto");
			}
		} catch (AlidaPosException e) {
			System.out.println("Error checkProducto " + e.getMessage());
		}
		return result;
	}
	
	private void calcularImporteTotal(){
		float totalAcumulado = 0f;
		for (int i = 0; i < getDetalleTraspasos().size(); i++) {
			totalAcumulado = totalAcumulado + detalleTraspasos.get(i).getCantidad()*detalleTraspasos.get(i).getPreciounitario();
		}
		setImporteTotal(totalAcumulado);
	}
}

