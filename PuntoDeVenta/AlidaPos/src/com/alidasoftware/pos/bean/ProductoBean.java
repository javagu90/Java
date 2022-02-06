package com.alidasoftware.pos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;


import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CategoriaProductoFacade;
import com.alidasoftware.pos.facade.ConfiguracionFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.facade.ProveedorFacade;
import com.alidasoftware.pos.facade.ProveedorProductoFacade;
import com.alidasoftware.pos.facade.UnidadFacade;
import com.alidasoftware.pos.model.Categoriaproducto;
import com.alidasoftware.pos.model.Configuracion;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Proveedor;
import com.alidasoftware.pos.model.Unidad;
import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Debug.User;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
 
public class ProductoBean  extends AbstractBean implements Serializable {	
	private static final long serialVersionUID = -8737907002198953963L;
	
	private ProveedorProductoFacade proveedorProductoFacade;
	private ProductoFacade productoFacade;
	private List<Producto> productos;
	private List<Producto> productosFiltrados;
	private Producto selected;

	//added
	private UnidadFacade unidadFacade;
	private String unidadSeleccionada;
	private List<Unidad> unidades;
	
	private String proveedorSeleccionado;
	private ProveedorFacade proveedorFacade;
	private List<Proveedor> proveedores;
	private float precioProveedorSeleccionado;

	private CategoriaProductoFacade categoriaProductoFacade;	
	private List<Categoriaproducto> categoriaProductos;
	private List<Categoriaproducto> categoriaProductosSeleccionadas;
	
    private DualListModel<Categoriaproducto> categorias;

    private UploadedFile file;
    private String filenameImage;
    private String currentFilenameImage;
    private String currentCategoriasSeleccionadas;
    private String imagenB64;
    
    private boolean ivaExento;
    private boolean edit;
    
    private ConfiguracionFacade configuracionFacade;
    
	public UploadedFile getFile() {
        return file;
    }
 
	public String getFilenameImage() {
    	if (filenameImage == null) {
    		filenameImage = "image_not_found.png";
		}
		return filenameImage;
	}

	public void setFilenameImage(String filenameImage) {
		this.filenameImage = filenameImage;
	}

	public String getCurrentFilenameImage() {
		return currentFilenameImage;
	}

	public void setCurrentFilenameImage(String currentFilenameImage) {
		this.currentFilenameImage = currentFilenameImage;
	}

	public String getCurrentCategoriasSeleccionadas() {
		return currentCategoriasSeleccionadas;
	}

	public void setCurrentCategoriasSeleccionadas(
			String currentCategoriasSeleccionadas) {
		this.currentCategoriasSeleccionadas = currentCategoriasSeleccionadas;
	}

	public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void fileUploadListener(FileUploadEvent event){
//    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Listener...");
//        // Get uploaded file from the FileUploadEvent
//        this.file = event.getFile();
//        // Print out the information of the file
//    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Uploaded File Name Is : "+ file.getFileName() +" :: Uploaded File Size :: "+file.getSize());
//
//    	File imageFile = null;
//    	try {
//			imageFile = File.createTempFile("producto-",".png", new File("C:\\AlidaSoftware\\svnWorkspace\\AlidaPos\\WebContent\\resources\\images"));
//			FileOutputStream fop = new FileOutputStream(imageFile);
//    	    IOUtils.copy(file.getInputstream(), fop);
//    	    fop.flush();
//			fop.close();
//
//
//		} catch (IOException e1) {
//			Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Error al salvar...");
//			e1.printStackTrace();
//		}
//
//    	this.filenameImage = imageFile.getName();
//    	this.currentFilenameImage = file.getFileName();
//    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"File generado: " + this.filenameImage);
        this.file = event.getFile();
        System.out.println("Uploaded File Name Is : "+ file.getFileName() +" :: Uploaded File Size :: "+file.getSize());
			try {
				System.out.println("bytes : " + IOUtils.toByteArray(file.getInputstream(), file.getSize()));
				imagenB64 = encodeImage(IOUtils.toByteArray(file.getInputstream()));
				selected.setImagen(decodeImage(imagenB64));
			} catch (IOException e) {
				System.out.println("Error al cargar la imagen " + e.getMessage());
			}
			System.out.println("imagenB64: " + imagenB64);
    	this.filenameImage = file.getFileName();
    	this.currentFilenameImage = file.getFileName();
    	System.out.println("archivo generado " + filenameImage);

    }
    
    public static String encodeImage(byte[] imageByteArray){
		return Base64.encodeBase64String(imageByteArray);		
	}
	
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}

	public String getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}


	public void setProveedorSeleccionado(String proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}
	
	public ConfiguracionFacade getConfiguracionFacade(){
		if(configuracionFacade == null){
			configuracionFacade = new ConfiguracionFacade();
		}
		return configuracionFacade;
	}


	public ProveedorFacade getProveedorFacade() {
		if (proveedorFacade == null) {
			proveedorFacade = new ProveedorFacade();
		}
		return proveedorFacade;
	}


	public List<Proveedor> getProveedores() {
		if (proveedores == null) {
			loadProveedores();
		}
		return proveedores;
	}


	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}


	public float getPrecioProveedorSeleccionado() {
		return precioProveedorSeleccionado;
	}


	public void setPrecioProveedorSeleccionado(float precioProveedorSeleccionado) {
		this.precioProveedorSeleccionado = precioProveedorSeleccionado;
	}


	public DualListModel<Categoriaproducto> getCategorias() {
		if (categorias == null) {
			getCategoriaProductos();
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


	public void setCategoriaProductos(List<Categoriaproducto> categoriaProductos) {
		this.categoriaProductos = categoriaProductos;
	}

	public UnidadFacade getUnidadFacade() {
		if (unidadFacade == null) {
			unidadFacade = new UnidadFacade();
		}
		return unidadFacade;
	}


	public void setUnidadFacade(UnidadFacade unidadFacade) {
		this.unidadFacade = unidadFacade;
	}


	public List<Unidad> getUnidades() {
		if (unidades == null) {
			loadUnidades();
		}
		return unidades;
	}


	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}


	public String getUnidadSeleccionada() {
		return unidadSeleccionada;
	}


	public void setUnidadSeleccionada(String unidadSeleccionada) {
		this.unidadSeleccionada = unidadSeleccionada;
	}


	public ProductoBean() {	
		loadProductos();
	}


	public ProveedorProductoFacade getProveedorProductoFacade() {
		if (proveedorProductoFacade==null) {
			proveedorProductoFacade = new ProveedorProductoFacade();
		}
		return proveedorProductoFacade;
	}

	public void setProveedorProductoFacade(
			ProveedorProductoFacade proveedorProductoFacade) {
		this.proveedorProductoFacade = proveedorProductoFacade;
	}

	public ProductoFacade getProductoFacade() {
        if (productoFacade == null) {
            productoFacade = new ProductoFacade();
        }
        return productoFacade;
    }

	
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public List<Producto> getProductosFiltradas() {
		return productosFiltrados;
	}
	public void setProductosFiltradas(List<Producto> productosFiltradas) {
		this.productosFiltrados = productosFiltradas;
	}
	public Producto getSelected() {
		return selected;
	}
	
	public void setSelected(Producto selected) {
		this.selected = selected;
	}
	public Producto prepareCreate() {
		//clean vars
		setCategorias(null);
		imagenB64 = "";

		selected = new Producto();
		
		ivaExento = false;
        
		//cargar unidades y mostrar actual
        selected.setUnidad(new Unidad());       
        loadUnidades();
        this.unidadSeleccionada = "";       
        
        //cargar categorias de productos
        selected.setCategoriaproductos(new ArrayList<Categoriaproducto>());
        loadCategoriaProductos();
        //llenar el objeto con categorias seleccionadas
        getCategorias();
        
        //cargar proveedor
        loadProveedores();
        this.proveedorSeleccionado ="";   
        getFilenameImage();
        
        setCurrentCategoriasSeleccionadas("ninguna");
        setCurrentFilenameImage("ninguna");
        setPrecioProveedorSeleccionado(0);
        
        //Busca en la tabla de configuracion si existe un valor para el iva
        try {
        	Configuracion configuracionIva = getConfiguracionFacade().findConfiguracionByAtributo("Iva");
        	if(configuracionIva != null && configuracionIva.getValor1()!=null && !configuracionIva.getValor1().equals("")){
        		selected.setIva(Float.parseFloat(configuracionIva.getValor1()));
        	}
        } catch(Exception ex) {
        	System.out.println("Error al obtener la configuracion del iva");
        }
        return selected;
    }
	
	public void prepareUpdate() {
		//clean vars
		setCategorias(null);
		
        //cargar unidades y mostrar actual
		loadUnidades();
        this.unidadSeleccionada = selected.getUnidad().getNombre();        
        
        //cargar categorias de productos
        loadCategoriaProductos();
        System.out.println("despues de cargar las categorias de productos");
        //llenar el objeto con categorias seleccionadas
        getCategorias().setTarget(selected.getCategoriaproductos());
        System.out.println("luego de hacer lo del target");
        
        //Llenar el label de categorias previamente seleccionadas
        StringBuilder builder = new StringBuilder();           
        for(Categoriaproducto item : selected.getCategoriaproductos()) {
        	if (builder.length()>0) {
        		builder.append(", ");
			}
            //añadir al label
        	builder.append(item.getNombre());
            
            //elimiar la categoria de la lista source
            for (int i = 0; i < getCategorias().getSource().size(); i++) {
            	if (getCategorias().getSource().get(i).getIdcategoriaproducto() == item.getIdcategoriaproducto()) {
            		getCategorias().getSource().remove(i);
            		break;
    			}    			
    		}                              
        }        
        System.out.println("luego del string builder que no se que hace");
        //asignar el string
        if (builder.toString().length() == 0) {
            setCurrentCategoriasSeleccionadas("ninguna");			
		}else{
			setCurrentCategoriasSeleccionadas(builder.toString());
		}
        System.out.println("despues de asignar el string");
        
        //cargar proveedor
        System.out.println("llama a los proveedores");
        loadProveedores();
//		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"proveedor-producto:SIZE: " + selected.getProveedorproductos().size());
        
        System.out.println("antes de asignar el proveedor seleccionado");
        this.proveedorSeleccionado = selected.getProveedor().getClaveproveedor();
        System.out.println("el proveedor seleccionado y toma la clave del proveedor");
//        this.proveedorSeleccionado = selected.getProveedorproductos().get(0).getProveedor().getClaveproveedor();           
        //mostrar precio de proveedor
        precioProveedorSeleccionado = selected.getPrecio();
//        precioProveedorSeleccionado = selected.getProveedorproductos().get(0).getPrecio();
        
                
        setFilenameImage(selected.getPathimagen());        
        setCurrentFilenameImage(selected.getPathimagen());
        if(selected.getImagen()!=null ){
        	imagenB64 = encodeImage(selected.getImagen());
        } else {
        	imagenB64 = "";
        }
        edit = true;
		
     }

	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {	
		try{
			//nombre de producto duplicado
			if(getProductoFacade().findProducto(this.selected.getNombre())!=null){	
				displayErrorMessageToUser("Nombre del producto duplicado");
				return;
			}
			
			//establecer las categorias de productos
			selected.setCategoriaproductos(categorias.getTarget());			
			Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:categoriaproducto:SIZE: " + selected.getCategoriaproductos().size());
			
			//establecer la unidad del producto
			Unidad currentUnidad;
			currentUnidad = getUnidadFacade().findUnidad(this.unidadSeleccionada);
			selected.setUnidad(currentUnidad);
		        
	        //establecer la imagen seleccionada
	        selected.setPathimagen(filenameImage);
			
			//establecer la fecha de ingreso
			selected.setFechaingreso(new Date());
			
			if(selected.isExentoDeIva()){
				selected.setIva(0);
			}
			
			//se guarda el producto
			selected.setActivo(true);
			selected.setProveedor(getProveedorFacade().findProveedor(this.proveedorSeleccionado));
			selected.setPrecio(precioProveedorSeleccionado);
            getProductoFacade().createProducto(selected);
            
            closeDialog("ProductoCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            

            loadProductos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
			Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"updating...");

			//nombre de producto duplicado			
			if(getProductoFacade().findProducto(this.selected.getNombre())!=null){	
				if(getProductoFacade().findProducto(this.selected.getNombre()).getIdproducto()!=this.selected.getIdproducto()){	
					displayErrorMessageToUser("Nombre del producto duplicado");
					return;
				}
			}
			
			//establecer las categorias de productos
			selected.setCategoriaproductos(categorias.getTarget());			
			
			//establecer la unidad del producto
			Unidad currentUnidad;
			currentUnidad = getUnidadFacade().findUnidad(this.unidadSeleccionada);
			selected.setUnidad(currentUnidad);
		        
	        //establecer la imagen seleccionada
	        selected.setPathimagen(filenameImage);	        						
			
            //se guarda el proveedorProducto
			selected.setProveedor(getProveedorFacade().findProveedor(this.proveedorSeleccionado));
//			selected.setProveedor(currentProveedor);
//			currentProveedor = getProveedorFacade().findProveedor(this.proveedorSeleccionado);
//			selected.getProveedorproductos().get(0).setPrecio(precioProveedorSeleccionado);
//			selected.getProveedorproductos().get(0).setProveedor(currentProveedor);

			showBeanData();
            		
			if(selected.isExentoDeIva()){
				selected.setIva(0);
			}
			selected.setImagen(decodeImage(imagenB64));
            getProductoFacade().updateProducto(selected);
//            getProveedorProductoFacade().updateProveedorproducto(selected.getProveedorproductos().get(0));
            
            closeDialog("ProductoEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadProductos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
            getProductoFacade().deleteProducto(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadProductos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void onRowDblClckSelect(SelectEvent event) { 
		prepareUpdate();
		if (event != null && event.getObject() != null) {
			selected = (Producto) event.getObject();
	        viewRecord();	        
		}
		edit = false;
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("ProductoEditViewDlg");
	}
	
	private void loadProductos() {
		try {
			productos = getProductoFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }

    public void handleUnidadSelect() {
    	loadUnidades();
    }
    
	private void loadUnidades() {
		try {
			unidades = getUnidadFacade().listAll();
		} catch (AlidaPosException e) {
			displayErrorMessageToUser("Error: " + e.getMessage());		}
    }	

	private void loadCategoriaProductos() {
		try {
			categoriaProductos = getCategoriaProductoFacade().listAll();
		} catch (AlidaPosException e) {
			displayErrorMessageToUser("Error: " + e.getMessage());		}
    }
	
    public void handleProveedorSelect() {
    	//
    }

    
	private void loadProveedores() {
		try {
			proveedores = getProveedorFacade().listAll();
		} catch (AlidaPosException ex) {
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
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
    public void showBeanData(){
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"----------------------------------------------------");            
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:clave: " + selected.getClave());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:nombre: " + selected.getNombre());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:descripcion: " + selected.getDescripcion());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:fechaIngreso: " + selected.getFechaingreso());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:iva: " + selected.getIva());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:comentarios: " + selected.getComentarios());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:codigo: " + selected.getCodigo());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:pathimagen: " + selected.getPathimagen());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:unidad:nombre: " + selected.getUnidad().getNombre());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:proveedorproducto:precio: " + selected.getPrecio());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:proveedorproducto:proveedor: " + selected.getProveedor().getClaveproveedor());
//		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:proveedorproducto:precio: " + selected.getProveedorproductos().get(0).getPrecio());
//		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:proveedorproducto:proveedor: " + selected.getProveedorproductos().get(0).getProveedor().getClaveproveedor());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"producto:categoriaproducto:SIZE: " + selected.getCategoriaproductos().size());
		Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"----------------------------------------------------");            
    }
    
    public void preProcessPDF(Object document) {
 	   Document doc = (Document) document;
 	   doc.setPageSize(PageSize.A4.rotate());
 	}

	public boolean isIvaExento() {
		return ivaExento;
	}

	public void setIvaExento(boolean ivaExento) {
		this.ivaExento = ivaExento;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String getImagenB64() {
		return imagenB64;
	}

	public void setImagenB64(String imagenB64) {
		this.imagenB64 = imagenB64;
	}    
      
}
