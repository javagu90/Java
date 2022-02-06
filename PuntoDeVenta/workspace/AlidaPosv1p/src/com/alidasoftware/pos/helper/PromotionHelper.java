package com.alidasoftware.pos.helper;

import java.io.Serializable;

public class PromotionHelper implements Serializable {

	private static final long serialVersionUID = 3350453831540712602L;
	
	private int idPromotion;
	private float discount;
	
	public int getIdPromotion() {
		return idPromotion;
	}
	
	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
	}
	
	public float getDiscount() {
		return discount;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public PromotionHelper(int idPromotion, float discount) {
		this.discount = discount;
		this.idPromotion = idPromotion;
	}

}
