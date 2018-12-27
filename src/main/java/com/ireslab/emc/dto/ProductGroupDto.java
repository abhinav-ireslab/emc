/**
 * 
 */
package com.ireslab.emc.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ireslab
 *
 */
public class ProductGroupDto {

	private Integer groupId;
	private String productGroupName;
	private String productGroupDescription;
	private Date createdDate;
	private Timestamp modifiedDate;
	private String auditLogonId;
	
	public ProductGroupDto() {
		
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public String getProductGroupDescription() {
		return productGroupDescription;
	}

	public void setProductGroupDescription(String productGroupDescription) {
		this.productGroupDescription = productGroupDescription;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getAuditLogonId() {
		return auditLogonId;
	}

	public void setAuditLogonId(String auditLogonId) {
		this.auditLogonId = auditLogonId;
	}	

}
