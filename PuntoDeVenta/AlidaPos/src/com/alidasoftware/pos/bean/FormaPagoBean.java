package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.bean.AbstractBean;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.FormaPagoFacade;
import com.alidasoftware.pos.model.Formapago;
 
public class FormaPagoBean  extends AbstractBean implements Serializable {
	private static final long serialVersionUID = -8281099230663193865L;
	
	private FormaPagoFacade formaPagoFacade;
	private List<Formapago> formaPagos;
	private List<Formapago> formaPagosFiltrados;
	private Formapago selected;

	public FormaPagoBean() {
		loadFormapagos();
	}


	public FormaPagoFacade getFormaPagoFacade() {
        if (formaPagoFacade == null) {
            formaPagoFacade = new FormaPagoFacade();
        }
        return formaPagoFacade;
    }

	
	public List<Formapago> getFormaPagos() {
		return formaPagos;
	}
	public void setFormaPagos(List<Formapago> formapagos) {
		this.formaPagos = formapagos;
	}
	public List<Formapago> getFormaPagosFiltrados() {
		return formaPagosFiltrados;
	}
	public void setFormaPagosFiltrados(List<Formapago> formapagosFiltrados) {
		this.formaPagosFiltrados = formapagosFiltrados;
	}
	public Formapago getSelected() {
		return selected;
	}

	
	public void setSelected(Formapago selected) {
		this.selected = selected;
	}
	public Formapago prepareCreate() {
        selected = new Formapago();
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
            getFormaPagoFacade().createFormaPago(selected);
            closeDialog("FormaPagoCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadFormapagos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getFormaPagoFacade().updateFormaPago(selected);
            closeDialog("FormaPagoEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadFormapagos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
            getFormaPagoFacade().deleteFormaPago(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadFormapagos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadFormapagos() {
		try {
			formaPagos = getFormaPagoFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
}
