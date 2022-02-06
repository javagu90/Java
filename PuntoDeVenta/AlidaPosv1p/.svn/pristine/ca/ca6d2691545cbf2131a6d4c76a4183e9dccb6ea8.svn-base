package com.alidasoftware.pos.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiscountHelper  implements Serializable {

	private static final long serialVersionUID = -8755227901704268174L;
	
	private int idProduct;
	private float finalPrice;
	private float originalPrice;
	private List<PromotionHelper> promotionList;

	public float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	public List<PromotionHelper> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<PromotionHelper> promotionList) {
		this.promotionList = promotionList;
	}

	public int getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}	
	
	public DiscountHelper(int idProduct, float originalPrice) {
		this.idProduct     = idProduct;
		this.originalPrice = originalPrice;
	}
	
	public void addPromotion(PromotionHelper promotion) {
		if (promotionList == null) {
			promotionList = new ArrayList<PromotionHelper>();			
		}
		promotionList.add(promotion);
	}
	
	public float getTotalDiscount() {
		return this.originalPrice - this.finalPrice;
	}

}
