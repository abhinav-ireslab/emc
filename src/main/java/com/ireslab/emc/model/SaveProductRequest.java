package com.ireslab.emc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.sendx.electra.dto.Gst;
import com.ireslab.sendx.electra.dto.Total;
 
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveProductRequest extends GenericRequest{

	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String clientCorrelationId;
	private String productCode;
	private String productGroup;
 	private String productDescription;
	private String productCost;
	private String availableItem;
	private String productName;
	private String productUnit;
	private String productRange;
	private Double tax;
	
	
	private String itemCode;
	private String invoiceType;
	private boolean gstInclusive;
	private String paymentTerms;
	private String itemNameOrDesc;
	private String itemTypeOrChapter;
	private String discount;
	private String itemPrice;
	private String availableQuantity;
	private Total total;
	private Gst gst;
	private String customerNotes;
	private String termsAndConditions;
	private boolean interState;
	
	
	public boolean isInterState() {
		return interState;
	}
	public void setInterState(boolean interState) {
		this.interState = interState;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}
	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getProductRange() {
		return productRange;
	}
	public void setProductRange(String productRange) {
		this.productRange = productRange;
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
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
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
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
	public Gst getGst() {
		return gst;
	}
	public void setGst(Gst gst) {
		this.gst = gst;
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

	
	
}
