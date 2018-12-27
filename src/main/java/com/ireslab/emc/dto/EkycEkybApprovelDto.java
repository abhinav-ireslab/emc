package com.ireslab.emc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EkycEkybApprovelDto {
	private String corellationId;
	private boolean isClient;
	private String name;
	private String date;
	private String status;
	private String comment;
	
	private String mobileNumber;
	private String idNo;
	private String documentFrontPart;
	private String documentBackPart;
	private String businessId;
	private String address;
	private String profileImage;
	private String uploadedDocument;
	private String dob;
	private String businessDocument;
	
	
	
	/**
	 * @return the uploadedDocument
	 */
	public String getUploadedDocument() {
		return uploadedDocument;
	}
	/**
	 * @param uploadedDocument the uploadedDocument to set
	 */
	public void setUploadedDocument(String uploadedDocument) {
		this.uploadedDocument = uploadedDocument;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCorellationId() {
		return corellationId;
	}
	public void setCorellationId(String corellationId) {
		this.corellationId = corellationId;
	}
	public boolean isClient() {
		return isClient;
	}
	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}
	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/**
	 * @return the documentFrontPart
	 */
	public String getDocumentFrontPart() {
		return documentFrontPart;
	}
	/**
	 * @param documentFrontPart the documentFrontPart to set
	 */
	public void setDocumentFrontPart(String documentFrontPart) {
		this.documentFrontPart = documentFrontPart;
	}
	/**
	 * @return the documentBackPart
	 */
	public String getDocumentBackPart() {
		return documentBackPart;
	}
	/**
	 * @param documentBackPart the documentBackPart to set
	 */
	public void setDocumentBackPart(String documentBackPart) {
		this.documentBackPart = documentBackPart;
	}
	/**
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}
	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EkycEkybApprovelDto [corellationId=" + corellationId + ", isClient=" + isClient + ", name=" + name
				+ ", date=" + date + ", status=" + status + ", comment=" + comment + ", mobileNumber=" + mobileNumber
				+ ", idNo=" + idNo + ", documentFrontPart=" + documentFrontPart + ", documentBackPart="
				+ documentBackPart + ", businessId=" + businessId + ", address=" + address + "]";
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getBusinessDocument() {
		return businessDocument;
	}
	public void setBusinessDocument(String businessDocument) {
		this.businessDocument = businessDocument;
	}
	
	
}
