package com.alidasoftware.pos.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator ("floatNumberValidator")
public class FloatNumberValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		try {
			Float.parseFloat(value.toString());
		} catch (NumberFormatException ex) {
			System.out.println("Error : " + ex.getMessage()) ;
			System.out.println("comp : " + comp.getClientId()) ;
			//FacesMessage message = new FacesMessage();
            //message.setSeverity(FacesMessage.SEVERITY_ERROR);
            //message.setSummary("El valor ingresado no es un número válido");
            //System.out.println("comp : " + comp.getClientId()) ;
            //context.addMessage("userForm:Email", message);
			//throw new ValidatorException(setError(comp.getClientId(), "[X]", true));
		}
	}
	
	private FacesMessage setError(String componente, String mensaje, boolean regresa) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary(mensaje);
        context.addMessage(componente, message);
        if (regresa) {
            return message;
        }
        return null;
    }

}
