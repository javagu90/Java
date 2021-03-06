package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CajaEfectivoFacade;
import com.alidasoftware.pos.facade.CajaFacade;
import com.alidasoftware.pos.model.Caja;
import com.alidasoftware.pos.model.Cajaefectivo;

public class CorteCajaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -7511752856456833241L;
	
	private CajaFacade cajaFacade;
	private CajaEfectivoFacade cajaEfectivoFacade;
	private Caja caja;
	private String selectedCaja;
	private List<SelectItem> listaCaja;
	private float efectivoInicial;
	private String message;
	private Integer idTienda;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getEfectivoInicial() {
		return efectivoInicial;
	}

	public void setEfectivoInicial(float efectivoInicial) {
		this.efectivoInicial = efectivoInicial;
	}

	public String getSelectedCaja() {
		return selectedCaja;
	}

	public void setSelectedCaja(String selectedCaja) {
		this.selectedCaja = selectedCaja;
	}	

	public List<SelectItem> getListaCaja() {
		return listaCaja;
	}

	public void setListaCaja(List<SelectItem> listaCaja) {
		this.listaCaja = listaCaja;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	private CajaFacade getCajaFacade() {
		if (cajaFacade == null) {
			cajaFacade = new CajaFacade();
		}
		return cajaFacade;
	}
	
	private CajaEfectivoFacade getCajaEfectivoFacade() {
		if (cajaEfectivoFacade == null) {
			cajaEfectivoFacade = new CajaEfectivoFacade();
		}
		return cajaEfectivoFacade;
	}
	
	public CorteCajaBean() {
		efectivoInicial = 0.0f;
		caja = null;
		selectedCaja = null;
		listaCaja =  new ArrayList<SelectItem>();
		//loadCajaSelectItems();
	}
	
	public void showListDialogListener() {
		
	}
	
	public String showListDialogAction() {
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().show();");
		showListDialogListener();
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
		return "/caja/List.xhtml?faces-redirect=true";
	}

	//********************************************************************
	// Metodos para controlar que caja es la que utilizara en la sesion.
	//********************************************************************
	
	public void cajaListener(ActionEvent event) {
		initializeVars();
	}
	
	public void cleanUp() {
		initializeVars();
	}
	
	private void initializeVars() {
		message = "";
		efectivoInicial = 0.0f;
		caja = null;
		selectedCaja = "";
	}
	
	public void cajaChangeListener(ValueChangeEvent event) {
		if (event != null) {
			try {			
				//System.out.println("caja valueChange : " + event.getNewValue().getClass().toString());
				selectedCaja = event.getNewValue().toString();
				message = "";
			} catch (Exception ex) {
				System.out.println("ErrorValueChangeCaja : " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
			}
		}
	}
	
	private void loadCajaSelectItems(Integer idTienda) {
		this.idTienda = idTienda;
		try {
			listaCaja = new ArrayList<SelectItem>();
			List<Caja> list = getCajaFacade().listByTienda(idTienda);
			if (list != null && list.size() > 0) {
				String label = "";
				for (int i = 0; i < list.size(); i++) {
					label = list.get(i).getClave();
					listaCaja.add(new SelectItem( label ));
				}
				selectedCaja = list.get(0).getClave();
			}			
		} catch (Exception ex) {
			System.out.println("ErrorCaja : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: No exiten cajas.. verifique cat?logo");
		}
	}
	
	private void loadCajaSelectItems() {
		try {
			List<Cajaefectivo> listaCajaEfectivo = getCajaEfectivoFacade().listAll();			
			listaCaja = new ArrayList<SelectItem>();
			List<Caja> list = getCajaFacade().listAll();
			if (list != null && list.size() > 0) {
				String label = "";
				for (int i = 0; i < list.size(); i++) {
					label = list.get(i).getNombre();
					//System.out.println("CajaName : " + label);
					if (listaCajaEfectivo != null && listaCajaEfectivo.size() > 0) {
						for (int j = 0; j < listaCajaEfectivo.size(); j++) {
							if (!listaCajaEfectivo.get(j).getCaja().getClave().equals(label)) {
								listaCaja.add(new SelectItem( label ));
							}
						}
					} else {
						listaCaja.add(new SelectItem( label ));
					}
				}
				selectedCaja = list.get(0).getClave();
				//System.out.println("Init Caja seleccionada : " + selectedCaja);				
			}
			
		} catch (Exception ex) {
			System.out.println("ErrorCaja : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: No exiten cajas.. verifique cat?logo");
		}
	}	
	
	private boolean checkCajainUse(Caja auxCaja) throws AlidaPosException {
		Cajaefectivo auxCajaEfectivo = getCajaEfectivoFacade().findByCaja(auxCaja);
		if (auxCajaEfectivo != null) {
			return true;
		}
		return false;
	}
	
	public void checkCashier() {
		try {
			if (Float.compare(efectivoInicial, (float) 0.0) > 0) {
				System.out.println("Datos Caja: " + selectedCaja + " -> " + idTienda);
				caja = getCajaFacade().findByClaveIdTienda(selectedCaja, idTienda);	
				System.out.println("Objeto Caja: " + caja.getClave() + " -> " + caja.getTienda().getIdtienda());
				if (caja != null && !checkCajainUse(caja)) {					
					Cajaefectivo cajaEfectivo = new Cajaefectivo();
					cajaEfectivo.setEfectivo(efectivoInicial);
					cajaEfectivo.setFecha(new Date());
					cajaEfectivo.setCaja(caja);
					getCajaEfectivoFacade().createCajaEfectivo(cajaEfectivo);
					RequestContext.getCurrentInstance().update("menuForm:mainMenuBar");
					message = "";
					RequestContext.getCurrentInstance().execute("PF('cashierDialog').hide();");	
				} else {
					message = "La caja ya esta utilizada, seleccione otra.";
					RequestContext.getCurrentInstance().update("abreCajaForm:panelOpenCaja");				
				}	
			} else {
				message = "La cantidad ingresada debe ser mayor a cero.";
				RequestContext.getCurrentInstance().update("abreCajaForm:panelOpenCaja");
			}
		} catch (Exception ex) {
			System.out.println("ErrorCerrarCaja : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		efectivoInicial = 0.0f;
	}
	
	public String openCajaDialogAction(Integer idTienda) {
		loadCajaSelectItems(idTienda);
		RequestContext.getCurrentInstance().update("abreCajaForm:panelOpenCaja");
		RequestContext.getCurrentInstance().execute("PF('cashierDialog').show();");
		return null;
	}
	
}
