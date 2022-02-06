package com.alidasoftware.pos.helper;

import com.alidasoftware.pos.model.Modulo;

public class ModuloPerfilHelper extends Modulo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean selected;
	
	public ModuloPerfilHelper(){
		
	}
	
	public ModuloPerfilHelper(Modulo modulo, boolean selected){
		super(modulo.getIdModulo(), modulo.getNombre(), modulo.getDescripcion(), modulo.getCategoria(), modulo.getPath(), modulo.getActivo());
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
}
