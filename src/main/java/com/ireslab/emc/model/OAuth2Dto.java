package com.ireslab.emc.model;

//import com.ireslab.sendx.electra.model.GenericResponse;

/**
 * @author Nitin
 *
 */
public class OAuth2Dto extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String access_token;
	private String token_type;
	private String expires_in;
	private String scope;

	private String error;
	private String error_description;

	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * @param access_token
	 *            the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}

	/**
	 * @param token_type
	 *            the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	/**
	 * @return the expires_in
	 */
	public String getExpires_in() {
		return expires_in;
	}

	/**
	 * @param expires_in
	 *            the expires_in to set
	 */
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the error_description
	 */
	public String getError_description() {
		return error_description;
	}

	/**
	 * @param error_description
	 *            the error_description to set
	 */
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
}
