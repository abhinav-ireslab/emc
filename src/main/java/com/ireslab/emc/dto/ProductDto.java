package com.ireslab.emc.dto;

import java.util.List;

import com.ireslab.sendx.electra.dto.GSTChapterDto;

public class ProductDto {
	private Integer productId;
	private Integer clientId;
	private String productCode;
	private String productName;
	private String productDescription;
	private String productCost;
	private String availableItem;
	private String productUnitRange;
	private String productUnit;
	private String purchasedQty;
	private String totalItemPrice;
	private String productGroup;
	private Integer productQuantity;
	private Double tax;
	
	
	// --------Producat Configuration -------------
			private String itemCode;
			private String invoiceType;
			private boolean gstInclusive;
			private String paymentTerms;
			private String itemNameOrDesc;
			private String itemTypeOrChapter;
			private String discountPercentage;
			private String itemPrice;
			private String availableQuantity;
			
				//--- total---
			private String subTotal;
			private String discount;
			private String totalTaxInclusive;
			private String total;
				//-------------
			
				//--- gst---
			private String cgst;
			private String sgstUtgst;
			private String igst;
				//-------------
			private String customerNotes;
			private String termsAndConditions;
			private boolean interState;
			
			private List<GSTChapterDto> gSTChapterDto;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCost() {
		return productCost;
	}
	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}
	public String getAvailableItem() {
		return availableItem;
	}
	public void setAvailableItem(String availableItem) {
		this.availableItem = availableItem;
	}
	public String getProductUnitRange() {
		return productUnitRange;
	}
	public void setProductUnitRange(String productUnitRange) {
		this.productUnitRange = productUnitRange;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getTotalItemPrice() {
		return totalItemPrice;
	}
	public void setTotalItemPrice(String totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}
	
	public String getPurchasedQty() {
		return purchasedQty;
	}
	public void setPurchasedQty(String purchasedQty) {
		this.purchasedQty = purchasedQty;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public boolean isGstInclusive() {
		return gstInclusive;
	}
	public void setGstInclusive(boolean gstInclusive) {
		this.gstInclusive = gstInclusive;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getItemNameOrDesc() {
		return itemNameOrDesc;
	}
	public void setItemNameOrDesc(String itemNameOrDesc) {
		this.itemNameOrDesc = itemNameOrDesc;
	}
	public String getItemTypeOrChapter() {
		return itemTypeOrChapter;
	}
	public void setItemTypeOrChapter(String itemTypeOrChapter) {
		this.itemTypeOrChapter = itemTypeOrChapter;
	}
	public String getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(String availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTotalTaxInclusive() {
		return totalTaxInclusive;
	}
	public void setTotalTaxInclusive(String totalTaxInclusive) {
		this.totalTaxInclusive = totalTaxInclusive;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCgst() {
		return cgst;
	}
	public void setCgst(String cgst) {
		this.cgst = cgst;
	}
	
	public String getIgst() {
		return igst;
	}
	public void setIgst(String igst) {
		this.igst = igst;
	}
	public String getCustomerNotes() {
		return customerNotes;
	}
	public void setCustomerNotes(String customerNotes) {
		this.customerNotes = customerNotes;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public List<GSTChapterDto> getgSTChapterDto() {
		return gSTChapterDto;
	}
	public void setgSTChapterDto(List<GSTChapterDto> gSTChapterDto) {
		this.gSTChapterDto = gSTChapterDto;
	}
	public String getSgstUtgst() {
		return sgstUtgst;
	}
	public void setSgstUtgst(String sgstUtgst) {
		this.sgstUtgst = sgstUtgst;
	}
	public boolean getInterState() {
		return interState;
	}
	public void setInterState(boolean interState) {
		this.interState = interState;
	}
	
	 
	 
}
