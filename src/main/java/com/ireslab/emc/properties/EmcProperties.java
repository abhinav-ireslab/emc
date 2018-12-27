package com.ireslab.emc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:emc_config.properties")
@ConfigurationProperties
public class EmcProperties {

public String clientId;

public String resourceIds;

public String scopes;

public String grantTypes;

public String authorities;

public String clientSecret;
	
private String userName;
private String emcUser;

public  String passwordResetLink;
public String baseUrl;
public String passwordResetPage;
public String adminPasswordResetPage;
public String passwordResetSuccessText;
public String loginPage;
public String agentInvitationText;
public String electraAppDownloadLink;
public String adminLoginPage;



	


public String getAgentInvitationText() {
	return agentInvitationText;
}



public void setAgentInvitationText(String agentInvitationText) {
	this.agentInvitationText = agentInvitationText;
}



public String getElectraAppDownloadLink() {
	return electraAppDownloadLink;
}



public void setElectraAppDownloadLink(String electraAppDownloadLink) {
	this.electraAppDownloadLink = electraAppDownloadLink;
}



public String getPasswordResetSuccessText() {
	return passwordResetSuccessText;
}



public void setPasswordResetSuccessText(String passwordResetSuccessText) {
	this.passwordResetSuccessText = passwordResetSuccessText;
}



public String getLoginPage() {
	return loginPage;
}



public void setLoginPage(String loginPage) {
	this.loginPage = loginPage;
}



public String getPasswordResetLink() {
	return passwordResetLink;
}



public String getBaseUrl() {
	return baseUrl;
}



public String getPasswordResetPage() {
	return passwordResetPage;
}



public void setPasswordResetLink(String passwordResetLink) {
	this.passwordResetLink = passwordResetLink;
}



public void setBaseUrl(String baseUrl) {
	this.baseUrl = baseUrl;
}



public void setPasswordResetPage(String passwordResetPage) {
	this.passwordResetPage = passwordResetPage;
}

public String getEmcUser() {
	return emcUser;
}

public void setEmcUser(String emcUser) {
	this.emcUser = emcUser;
}

private	String password;

private	String adminName;

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getAdminName() {
	return adminName;
}

public void setAdminName(String adminName) {
	this.adminName = adminName;
}



public String getAdminPasswordResetPage() {
	return adminPasswordResetPage;
}



public void setAdminPasswordResetPage(String adminPasswordResetPage) {
	this.adminPasswordResetPage = adminPasswordResetPage;
}



public String getAdminLoginPage() {
	return adminLoginPage;
}



public void setAdminLoginPage(String adminLoginPage) {
	this.adminLoginPage = adminLoginPage;
}



public String getClientId() {
	return clientId;
}



public void setClientId(String clientId) {
	this.clientId = clientId;
}



public String getResourceIds() {
	return resourceIds;
}



public void setResourceIds(String resourceIds) {
	this.resourceIds = resourceIds;
}



public String getScopes() {
	return scopes;
}



public void setScopes(String scopes) {
	this.scopes = scopes;
}



public String getGrantTypes() {
	return grantTypes;
}



public void setGrantTypes(String grantTypes) {
	this.grantTypes = grantTypes;
}



public String getAuthorities() {
	return authorities;
}



public void setAuthorities(String authorities) {
	this.authorities = authorities;
}



public String getClientSecret() {
	return clientSecret;
}



public void setClientSecret(String clientSecret) {
	this.clientSecret = clientSecret;
}

}