package com.alidasoftware.pos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ConfiguracionFacturaFacade;
import com.alidasoftware.pos.facade.EstadoFacade;
import com.alidasoftware.pos.facade.MunicipioFacade;
import com.alidasoftware.pos.model.Configuracionfactura;
import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.model.Municipio;
import com.alidasoftware.pos.util.Functions;

public class ConfiguracionFacturacionBean  extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Configuracionfactura selected;
	
	private ConfiguracionFacturaFacade configuracionFacturaFacade;
	
	private String estadoSeleccionado;
	private String municipioSeleccionado;
	private List<Estado> estados;
	private EstadoFacade estadoFacade;
	private List<Municipio> municipios;
	private MunicipioFacade municipioFacade;
	
	// Manejo de archivo
	private UploadedFile file;
	private String filenameImage;
	private String currentFilenameImage;
	
	private UploadedFile csdFile;
	private byte[] csd;
	private String csdName;
	private UploadedFile keyFile;
	private byte[] key;
	private String keyName;
	
	private byte[] imagen;
	private String imagenB64;
	
	// Temporal en lo que se crea el campo en la base de datos
	private String pwdKey;
	
	public ConfiguracionFacturacionBean(){
		this.estadoSeleccionado = "";
        this.municipioSeleccionado="";
        municipios = new ArrayList<Municipio>();
        List<Configuracionfactura> configuraciones;
		try {
			configuraciones = getConfiguracionFacturaFacade().listAll();
			if(configuraciones.isEmpty()){
				System.out.println("no encontro ninguna configuracion");
	        	selected = new Configuracionfactura();
	        } else {
	        	System.out.println("si encontro configuraciones");
	        	for(Configuracionfactura aux : configuraciones){
	        		selected = aux;
	        		imagenB64 = encodeImage(selected.getLogo());
	        		csd = selected.getCfdi();
	        		csdName = "Ya se cuenta con un .cer";
	        		key = selected.getKey();
	        		keyName = "Ya se cuenta con un .key";
	        	}
	        	estadoSeleccionado = selected.getMunicipio().getEstado().getNombre();
	        	municipioSeleccionado = selected.getMunicipio().getNombre();
	        	loadMunicipios();
	        }
		} catch (AlidaPosException e) {
			System.out.println("Error al obtener las configuraciones : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prueba(){
		System.out.println("Prueba " + Functions.getMD5(pwdKey));
		System.out.println("bytes " + DigestUtils.md5Hex(pwdKey.getBytes()));
	}
	
	public void create(){
		System.out.println("entro al create");
		System.out.println("truena cuando se quiere obtener el id " + selected.getIdconfiguracionfactura());
		Estado currentState;
		try {
			if(csd==null){
				displayInfoMessageToUser("No se ha seleccionado un archivo cer");
				System.out.println("No se ha seleccionado un archivo cer");
				return;
			}
			if(key==null){
				displayInfoMessageToUser("No se ha seleccionado un archivo key");
				System.out.println("No se ha seleccionado un archivo key");
				return;
			}
			if(null!=selected.getIdconfiguracionfactura()){
				System.out.println("Ya existe tiene que ir al update");
				update();
				return;
			}
			currentState = getEstadoFacade().findEstado(estadoSeleccionado);
			Municipio currentMunicipio = getMunicipioFacade().findMunicipiobyNombre(this.municipioSeleccionado, currentState.getIdestado());
			selected.setMunicipio(currentMunicipio);
			selected.setLogo(decodeImage(imagenB64));
			selected.setCfdi(csd);
			selected.setKey(key);
			selected.setPasswordKey(Functions.getMD5(selected.getPasswordKey()));
			System.out.println("Password guardado " + selected.getPasswordKey());
			selected.setLugarexpedicion(selected.getMunicipio().getNombre());
			getConfiguracionFacturaFacade().createConfiguracion(selected);
			displayInfoMessageToUser("La configuración ha sido creada");
		} catch (AlidaPosException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al momento de crear " + e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void update(){
		System.out.println("entro al update");
		Estado currentState;
		try {
			currentState = getEstadoFacade().findEstado(estadoSeleccionado);
			Municipio currentMunicipio = getMunicipioFacade().findMunicipiobyNombre(this.municipioSeleccionado, currentState.getIdestado());
			selected.setMunicipio(currentMunicipio);
			selected.setLogo(decodeImage(imagenB64));
			selected.setCfdi(csd);
			selected.setKey(key);
			selected.setPasswordKey(Functions.getMD5(selected.getPasswordKey()));
			System.out.println("Password guardado " + selected.getPasswordKey());
			selected.setLugarexpedicion(selected.getMunicipio().getNombre());
			getConfiguracionFacturaFacade().update(selected);
			displayInfoMessageToUser("La configuración ha sido actualizada");
		} catch (AlidaPosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void fileUploadListener(FileUploadEvent event){
        this.file = event.getFile();
        System.out.println("Uploaded File Name Is : "+ file.getFileName() +" :: Uploaded File Size :: "+file.getSize());
			try {
				System.out.println("bytes : " + IOUtils.toByteArray(file.getInputstream(), file.getSize()));
				imagen = IOUtils.toByteArray(file.getInputstream());
				imagenB64 = encodeImage(imagen);
			} catch (IOException e) {
				System.out.println("Error al cargar la imagen " + e.getMessage());
			}
			System.out.println("imagenB64: " + imagenB64);
    	this.filenameImage = file.getFileName();
    	this.currentFilenameImage = file.getFileName();
    	System.out.println("archivo generado " + filenameImage);
    }
    
    public void csdUploadListener(FileUploadEvent event){
    	csdFile = event.getFile();
    	try {
    		System.out.println("Entra a obtener los bytes del csd");
    		csd = IOUtils.toByteArray(event.getFile().getInputstream());
    		csdName = csdFile.getFileName();
    	} catch(Exception ex){
    		System.out.println("Error al obtener el csd " + ex.getMessage());
    	}
    }
    
    public void keyUploadListener(FileUploadEvent event){
    	keyFile = event.getFile();
    	try {
    		System.out.println("Entra a obtener los bytes del archivo key");
    		key = IOUtils.toByteArray(event.getFile().getInputstream());
    		keyName = keyFile.getFileName();
    		System.out.println("luego de obtener el nombre  " + keyName);
    	} catch(Exception ex){
    		System.out.println("Error al obtener la key " + ex.getMessage());
    	}
    }
    
	public static String encodeImage(byte[] imageByteArray){
		return Base64.encodeBase64String(imageByteArray);		
	}
	
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
	
	public List<Estado> getEstados() {
		loadEstados();
		return estados;
	}
	
	private void loadEstados() {
		try {
			estados = getEstadoFacade().listAll();
		} catch (AlidaPosException ex) {
            System.out.println("Error: " + ex.getMessage());
		}
    }

	private void loadMunicipios() {
		try {
			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);		
			municipios = getMunicipioFacade().findMunicipiosByEstado(currentState.getIdestado());
		} catch (AlidaPosException ex) {
            System.out.println("Error: " + ex.getMessage());
		}
    }
	
    public void handleStateSelect() {
    	loadMunicipios();
    }
    
    public void handleMunicipioSelect() {

    }
    
    public ConfiguracionFacturaFacade getConfiguracionFacturaFacade(){
    	if(configuracionFacturaFacade == null){
    		configuracionFacturaFacade = new ConfiguracionFacturaFacade();
    	}
    	return configuracionFacturaFacade;
    }
    
    public EstadoFacade getEstadoFacade(){
    	if(estadoFacade == null){
    		estadoFacade = new EstadoFacade();
    	}
    	return estadoFacade;
    }
    
	public MunicipioFacade getMunicipioFacade() {
        if (municipioFacade == null) {
        	municipioFacade = new MunicipioFacade();
        }
		return municipioFacade;
	}

	public Configuracionfactura getSelected() {
		return selected;
	}

	public void setSelected(Configuracionfactura selected) {
		this.selected = selected;
	}

	public String getEstadoSeleccionado() {
		return estadoSeleccionado;
	}

	public void setEstadoSeleccionado(String estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;
	}

	public String getMunicipioSeleccionado() {
		return municipioSeleccionado;
	}

	public void setMunicipioSeleccionado(String municipioSeleccionado) {
		this.municipioSeleccionado = municipioSeleccionado;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	
	// Manejo de archivo
	public UploadedFile getFile() {
        return file;
    }
	
	public void setFile(UploadedFile file) {
        this.file = file;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getImagenB64() {
		return imagenB64;
	}

	public void setImagenB64(String imagenB64) {
		this.imagenB64 = imagenB64;
	}
	
	public UploadedFile getCsdFile() {
		return csdFile;
	}

	public void setCsdFile(UploadedFile csdFile) {
		this.csdFile = csdFile;
	}

	public UploadedFile getKeyFile() {
		return keyFile;
	}

	public void setKeyFile(UploadedFile keyFile) {
		this.keyFile = keyFile;
	}

	public String getCsdName() {
		return csdName;
	}

	public void setCsdName(String csdName) {
		this.csdName = csdName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getPwdKey() {
		return pwdKey;
	}

	public void setPwdKey(String pwdKey) {
		this.pwdKey = pwdKey;
	}

}
