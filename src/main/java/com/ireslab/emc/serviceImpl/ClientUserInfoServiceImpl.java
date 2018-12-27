package com.ireslab.emc.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ireslab.emc.ElectraApiConfig;
import com.ireslab.emc.dto.ExchangeDto;
import com.ireslab.emc.dto.TransactionDto;
import com.ireslab.emc.model.AccountDetailsResponse;
import com.ireslab.emc.model.ApprovelRequest;
import com.ireslab.emc.model.ApprovelResponse;
import com.ireslab.emc.model.ClentAgentInvitationRequest;
import com.ireslab.emc.model.ClentAgentInvitationResponse;
import com.ireslab.emc.model.ClientAssetTokenRequest;
import com.ireslab.emc.model.ClientAssetTokenResponse;
import com.ireslab.emc.model.ClientCredentials;
import com.ireslab.emc.model.ClientPageRequest;
import com.ireslab.emc.model.ClientPageResponse;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.model.ClientProfileResponse;
import com.ireslab.emc.model.ClientRegistrationRequest;
import com.ireslab.emc.model.ClientRegistrationResponse;
import com.ireslab.emc.model.ClientUsersInfoResponse;
import com.ireslab.emc.model.CountryListResponse;
import com.ireslab.emc.model.Error;
import com.ireslab.emc.model.ExchangeResponse;
import com.ireslab.emc.model.FilterLedgerRequest;
import com.ireslab.emc.model.FilterLedgerResponse;
import com.ireslab.emc.model.GenericRequest;
import com.ireslab.emc.model.GenericResponse;
import com.ireslab.emc.model.LoadTokensRequest;
import com.ireslab.emc.model.OAuth2Dto;
import com.ireslab.emc.model.ProductGroupResponse;
import com.ireslab.emc.model.ProductRequest;
import com.ireslab.emc.model.ProductResponse;
import com.ireslab.emc.model.SaveProductRequest;
import com.ireslab.emc.model.SaveProductResponse;
import com.ireslab.emc.model.SignupRequest;
import com.ireslab.emc.model.SubscriptionPlanRequest;
import com.ireslab.emc.model.SubscriptionPlanResponse;
import com.ireslab.emc.model.TokenLifecycleManagementRequest;
import com.ireslab.emc.model.TokenLifecycleManagementResponse;
import com.ireslab.emc.model.TransactionLimitResponse;
import com.ireslab.emc.model.TransactionPurposeRequest;
import com.ireslab.emc.model.TransactionPurposeResponse;
import com.ireslab.emc.model.UserAgentRegistrationRequest;
import com.ireslab.emc.model.UserAgentRegistrationResponse;
import com.ireslab.emc.model.UserAgentResponse;
import com.ireslab.emc.model.UserRegistrationRequest;
import com.ireslab.emc.model.UserRegistrationResponse;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.TransactionalApiService;


@Service
public class ClientUserInfoServiceImpl implements ClientUserInfoService, TransactionalApiService {

	private static Logger LOG = LoggerFactory.getLogger(ClientUserInfoServiceImpl.class);

	private static final String BLANK = "";
	public static final String FORMAT_SPECIFIER = "%s";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String ACCEPT_HEADER = "Accept";
	private static final String CONTENT_TYPE_HEADER = "Content-Type";
	private static final Integer ACCESS_TOKEN_RETRY_LIMIT = 3;

	private static String accessToken = null;
	private static boolean isAccessTokenExpired = false;
	private static int accessTokenRetryCount = 0;
	private static HttpHeaders httpHeaders = null;

	@Autowired
	private ElectraApiConfig electraApiConfig;

	@Autowired
	private RestTemplate restTemplate;

	public String retrieveApiAccessToken() {

		if (accessToken == null || isAccessTokenExpired) {

			LOG.debug("Requesting access token for Electra API authorization . . . .");
			String endpointUrl = String.format(electraApiConfig.getAuthTokenEndpointUrl(),
					electraApiConfig.getGrantType());

			OAuth2Dto auth2Dto = (OAuth2Dto) invokeApi(endpointUrl, HttpMethod.POST, OAuth2Dto.class,
					new GenericRequest(), true, false);

			if (!auth2Dto.getErrors().isEmpty()) {

				Error error = auth2Dto.getErrors().get(0);
				LOG.error("Error occurred while getting API Access Token from Electra - \n\tError : "
						+ error.getMessage() + "\n\tError Description : " + error.getDescription());
				return null;
			}

			isAccessTokenExpired = false;
			accessToken = auth2Dto.getAccess_token();
		}

		LOG.debug("Access token for accessing Electra APIs - " + accessToken);
		return accessToken;
	}

	/**
	 * @param endpointUrl
	 * @param httpMethod
	 * @param responseClass
	 * @return
	 */
	public GenericResponse invokeApi(String endpointUrl, HttpMethod httpMethod, Class<?> responseClass,
			GenericRequest genericRequest, boolean isAuthRequest, boolean isClientRequest) {

		GenericResponse genericResponse = null;
		HttpEntity<?> apiResponse = null;
		String apiEndpointUrl = null;

		if (isAuthRequest) {
			apiEndpointUrl = String.format(electraApiConfig.getBaseUrl(), BLANK, BLANK) + endpointUrl;
		} else if (isClientRequest) {
			
			/*apiEndpointUrl =	String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
					+ String.format(endpointUrl, retrieveApiAccessToken());*/
			apiEndpointUrl = String.format(endpointUrl, retrieveApiAccessToken());
			

		} else {
			apiEndpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
					electraApiConfig.getClientCorrelationId()) + String.format(endpointUrl, retrieveApiAccessToken());
		}

		LOG.debug("API endpoint URL - " + apiEndpointUrl);

		if (httpHeaders == null) {
			String plainCredentials = electraApiConfig.getClientId() + ":" + electraApiConfig.getClientSecret();
			String base64ClientCredentials = new String(Base64Utils.encodeToString(plainCredentials.getBytes()));

			httpHeaders = new HttpHeaders();
			httpHeaders.add(AUTHORIZATION_HEADER, electraApiConfig.getHeaderAuthorization() + base64ClientCredentials);
			httpHeaders.set(ACCEPT_HEADER, electraApiConfig.getHeaderAccept());
			httpHeaders.set(CONTENT_TYPE_HEADER, electraApiConfig.getHeaderContentType());
		}

		HttpEntity<?> httpEntity = new HttpEntity<>(genericRequest, httpHeaders);

		try {
			/*
			 * apiResponse =
			 * restTemplate.exchange(UriComponentsBuilder.fromHttpUrl(endpointUrl).build().
			 * encode().toUri(), httpMethod, httpEntity, responseClass);
			 */
			// LOG.info(apiEndpointUrl);
			apiResponse = restTemplate.exchange(
					UriComponentsBuilder.fromHttpUrl(apiEndpointUrl).build().encode().toUri(), httpMethod, httpEntity,
					responseClass);

			// LOG.info("API response :" + apiResponse);

			genericResponse = (GenericResponse) apiResponse.getBody();

			if (isAuthRequest) {
				isAccessTokenExpired = false;

			}
			// genericResponse = (ClientUsersInfoResponse) apiResponse.getBody();

		} catch (HttpClientErrorException clientErrorException) {

			LOG.error("Error occurred while calling Electra API - " + "\n\tHttpStatus : "
					+ clientErrorException.getStatusCode().value() + "\n\tResponseBody : "
					+ clientErrorException.getResponseBodyAsString());

			if (clientErrorException.getStatusCode().value() == HttpStatus.UNAUTHORIZED.value()) {

				// Unauthorized - Access Token invalid or expired
				isAccessTokenExpired = true;

			} else {

				// Error occurred due to reason other than Access Token
				OAuth2Dto auth2Dto = null;
				try {
					auth2Dto = new ObjectMapper().readValue(clientErrorException.getResponseBodyAsString(),
							OAuth2Dto.class);

					genericResponse = (GenericResponse) Class.forName(responseClass.getName()).newInstance();
					genericResponse.setStatus(clientErrorException.getStatusCode().value());
					genericResponse.getErrors().add(new Error(auth2Dto.getError(), auth2Dto.getError_description()));

				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}

			if (isAccessTokenExpired && ++accessTokenRetryCount < ACCESS_TOKEN_RETRY_LIMIT) {
				genericResponse = invokeApi(endpointUrl, httpMethod, responseClass, genericRequest, isAuthRequest,
						isClientRequest);
			}

		}
		return genericResponse;
	}

	@Override
	public boolean invokeUserOnboardingApi(SignupRequest signupRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String args[]) {

		String patt = "http://localhost:%s/v1/%s";
		System.out.println(String.format(patt, "8085", "resource"));
		System.out.println(String.format(patt, "", ""));
	}

	@Override
	public ResponseEntity<?> getClientUserBalance(String userCorrelationId) {

		/*
		 * String uri
		 * =getUrl()+"balance/"+userCorrelationId+"?"+config.getAccess_token();
		 */
		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				electraApiConfig.getClientCorrelationId())
				+ String.format(electraApiConfig.getClientUserBalanceApiEndpointUrl(), userCorrelationId,
						retrieveApiAccessToken());

		System.out.println("******** : " + endpointUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(endpointUrl, HttpMethod.GET, entity, String.class);

		return result;
	}

	/*
	 * public ClientUsersInfoResponse getClientUsersList() { accessTokenRetryCount =
	 * 0;
	 * 
	 * String endpointUrl = String.format(electraApiConfig.getBaseUrl(),
	 * electraApiConfig.getApiVersion(), electraApiConfig.getClientCorrelationId())
	 * + String.format(electraApiConfig.getClientUsersApiEndpointUrl(),
	 * retrieveApiAccessToken()); System.out.println("APIndpointUrl :" +
	 * endpointUrl);
	 * 
	 * if (httpHeaders == null) { String plainCredentials =
	 * electraApiConfig.getClientId() + ":" + electraApiConfig.getClientSecret();
	 * String base64ClientCredentials = new
	 * String(Base64Utils.encodeToString(plainCredentials.getBytes()));
	 * 
	 * httpHeaders = new HttpHeaders(); httpHeaders.add(AUTHORIZATION_HEADER,
	 * electraApiConfig.getHeaderAuthorization() + base64ClientCredentials); //
	 * httpHeaders.set(ACCEPT_HEADER, electraApiConfig.getHeaderAccept());
	 * httpHeaders.set(CONTENT_TYPE_HEADER,
	 * electraApiConfig.getHeaderContentType());
	 * httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * 
	 * }
	 * 
	 * // HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
	 * HttpEntity<String> entity = new HttpEntity<String>("parameters",
	 * httpHeaders);
	 * 
	 * ResponseEntity<ClientUsersInfoResponse> result =
	 * restTemplate.exchange(endpointUrl, HttpMethod.GET, entity,
	 * ClientUsersInfoResponse.class); ClientUsersInfoResponse
	 * clientUsersInfoResponse = result.getBody();
	 * 
	 * 
	 * String loadTokensEndpointUrl =
	 * String.format(electraApiConfig.getClientUsersApiEndpointUrl(),
	 * retrieveApiAccessToken());
	 * 
	 * ClientUsersInfoResponse clientUsersInfoResponse = (ClientUsersInfoResponse)
	 * invokeApi( loadTokensEndpointUrl, HttpMethod.GET,
	 * ClientUsersInfoResponse.class, new GenericRequest(), false);
	 * 
	 * System.out.println("CLIENTUSERSINFORESPONSE : "+clientUsersInfoResponse);
	 * 
	 * 
	 * // ClientUsersList creation failed
	 * 
	 * if (clientUsersInfoResponse.getCode().intValue() != HttpStatus.OK.value() &&
	 * !clientUsersInfoResponse.getErrors().isEmpty()) {
	 * 
	 * List<Error> errors = clientUsersInfoResponse.getErrors();
	 * LOG.error("ClientUsersList creation on Electra failed | Error Code - " +
	 * clientUsersInfoResponse.getCode() + ", Error Message - " +
	 * clientUsersInfoResponse.getMessage() + ", Errors - " + errors);
	 * 
	 * return clientUsersInfoResponse; }
	 * 
	 * return clientUsersInfoResponse; }
	 */

	@Override
	public boolean invokeLoadTokensAPI(LoadTokensRequest loadTokensRequest) {
		accessTokenRetryCount = 0;

		String loadTokensEndpointUrl = String.format(electraApiConfig.getLoadTokensApiEndpointUrl(),
				loadTokensRequest.getUserCorrelationId(), retrieveApiAccessToken());

		TokenLifecycleManagementRequest tokenLifecycleManagementRequest = new TokenLifecycleManagementRequest();
		tokenLifecycleManagementRequest.setNoOfTokens(loadTokensRequest.getNoOfTokens());
		tokenLifecycleManagementRequest.setTokenCorrelationId(electraApiConfig.getTokenCorrelationId());

		TokenLifecycleManagementResponse tokenLifecycleManagementResponse = (TokenLifecycleManagementResponse) invokeApi(
				loadTokensEndpointUrl, HttpMethod.POST, TokenLifecycleManagementResponse.class,
				tokenLifecycleManagementRequest, false, false);

		// Account creation failed
		if (tokenLifecycleManagementResponse.getCode().intValue() != HttpStatus.OK.value()) {

			List<Error> errors = tokenLifecycleManagementResponse.getErrors();
			LOG.error("Account creation on Electra failed | Error Code - " + tokenLifecycleManagementResponse.getCode()
					+ ", Error Message - " + tokenLifecycleManagementResponse.getMessage() + ", Errors - " + errors);

			return false;
		}

		return true;
	}

	// TODO use this method with userCorrelationId
	@Override
	public ClientUsersInfoResponse getClientUsersList(String clientCorrelationId) {

		LOG.info("Creating User list response  for client correletionId : " + clientCorrelationId);

		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				clientCorrelationId)
				+ String.format(electraApiConfig.getClientUsersApiEndpointUrl(), FORMAT_SPECIFIER);

		/*
		 * if (httpHeaders == null) { String plainCredentials =
		 * electraApiConfig.getClientId() + ":" + electraApiConfig.getClientSecret();
		 * String base64ClientCredentials = new
		 * String(Base64Utils.encodeToString(plainCredentials.getBytes()));
		 * 
		 * httpHeaders = new HttpHeaders(); httpHeaders.add(AUTHORIZATION_HEADER,
		 * electraApiConfig.getHeaderAuthorization() + base64ClientCredentials); //
		 * httpHeaders.set(ACCEPT_HEADER, electraApiConfig.getHeaderAccept());
		 * httpHeaders.set(CONTENT_TYPE_HEADER,
		 * electraApiConfig.getHeaderContentType());
		 * httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); }
		 * 
		 * HttpEntity<String> entity = new HttpEntity<String>("parameters",
		 * httpHeaders);
		 * 
		 * ResponseEntity<ClientUsersInfoResponse> result =
		 * restTemplate.exchange(endpointUrl, HttpMethod.GET, entity,
		 * ClientUsersInfoResponse.class);
		 */

		ClientUsersInfoResponse clientUsersInfoResponse = (ClientUsersInfoResponse) invokeApi(endpointUrl,
				HttpMethod.GET, ClientUsersInfoResponse.class, null, false, true);
		// ClientUsersInfoResponse clientUsersInfoResponse = result.getBody();
		return clientUsersInfoResponse;
	}

	@Override
	public ClientProfile getClientByUserName(String userName) {

		LOG.info("Request for client using username : " + userName);
		
		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setUserName(userName);
		ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
		List<ClientProfile> profileList = new ArrayList<>();
		profileList.add(clientProfile);
		clientRegistrationRequest.setClientProfile(profileList);

		/*String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientByUsernameApiEndpointUrl(), "");
		//String endpointUrl = String.format(electraApiConfig.getClientByUsernameApiEndpointUrl());*/
		//String endpointUrl = String.format(electraApiConfig.getClientByUsernameApiEndpointUrl(), "%s");
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientByUsernameApiEndpointUrl(), FORMAT_SPECIFIER);

		ClientProfile clientProfiles = (ClientProfile) invokeApi(endpointUrl, HttpMethod.PUT, ClientProfile.class,
				clientRegistrationRequest, false, true);
		// ClientProfile clientProfile = result.getBody();
		return clientProfiles;
	}

	@Override
	public ClientProfile getClientByEmailId(String emailId) {

		LOG.info("Request for client using emailId : " + emailId);
		accessTokenRetryCount = 0;

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setEmailAddress(emailId);
		ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
		List<ClientProfile> profileList = new ArrayList<>();
		profileList.add(clientProfile);
		clientRegistrationRequest.setClientProfile(profileList);

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientByEmailApiEndpointUrl(), FORMAT_SPECIFIER);

		ClientProfile clientProfiles = (ClientProfile) invokeApi(endpointUrl, HttpMethod.PUT, ClientProfile.class,
				clientRegistrationRequest, false, true);
		// ClientProfile clientProfile = result.getBody();
		return clientProfiles;
	}

	@Override
	public ClientProfile getClientByCorrelationId(String correlationId) {

		LOG.info("Request for client using correlationId : " + correlationId);
		accessTokenRetryCount = 0;

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientByCorrelationidApiEndpointUrl(), correlationId,
						FORMAT_SPECIFIER);

		/*
		 * if (httpHeaders == null) { String plainCredentials =
		 * electraApiConfig.getClientId() + ":" + electraApiConfig.getClientSecret();
		 * String base64ClientCredentials = new
		 * String(Base64Utils.encodeToString(plainCredentials.getBytes()));
		 * 
		 * httpHeaders = new HttpHeaders(); httpHeaders.add(AUTHORIZATION_HEADER,
		 * electraApiConfig.getHeaderAuthorization() + base64ClientCredentials); //
		 * httpHeaders.set(ACCEPT_HEADER, electraApiConfig.getHeaderAccept());
		 * httpHeaders.set(CONTENT_TYPE_HEADER,
		 * electraApiConfig.getHeaderContentType());
		 * httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); }
		 * 
		 * HttpEntity<String> entity = new HttpEntity<String>("parameters",
		 * httpHeaders);
		 * 
		 * ResponseEntity<ClientProfile> result = restTemplate.exchange(endpointUrl,
		 * HttpMethod.GET, entity, ClientProfile.class); ClientProfile clientProfile =
		 * result.getBody();
		 */

		ClientProfile clientProfile = (ClientProfile) invokeApi(endpointUrl, HttpMethod.GET, ClientProfile.class, null,
				false, true);
		return clientProfile;
	}

	@Override
	public ClientProfile getClientByResetToken(String token) {

		LOG.info("Request for client using reset token : " + token);
		accessTokenRetryCount = 0;

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientByResetTokenApiEndpointUrl(), token,
						FORMAT_SPECIFIER);

		/*
		 * if (httpHeaders == null) { String plainCredentials =
		 * electraApiConfig.getClientId() + ":" + electraApiConfig.getClientSecret();
		 * String base64ClientCredentials = new
		 * String(Base64Utils.encodeToString(plainCredentials.getBytes()));
		 * 
		 * httpHeaders = new HttpHeaders(); httpHeaders.add(AUTHORIZATION_HEADER,
		 * electraApiConfig.getHeaderAuthorization() + base64ClientCredentials); //
		 * httpHeaders.set(ACCEPT_HEADER, electraApiConfig.getHeaderAccept());
		 * httpHeaders.set(CONTENT_TYPE_HEADER,
		 * electraApiConfig.getHeaderContentType());
		 * httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); }
		 * 
		 * HttpEntity<String> entity = new HttpEntity<String>("parameters",
		 * httpHeaders);
		 * 
		 * ResponseEntity<ClientProfile> result = restTemplate.exchange(endpointUrl,
		 * HttpMethod.GET, entity, ClientProfile.class); ClientProfile clientProfile =
		 * result.getBody();
		 */

		ClientProfile clientProfile = (ClientProfile) invokeApi(endpointUrl, HttpMethod.GET, ClientProfile.class, null,
				false, true);

		return clientProfile;
	}

	@Override
	public ClientRegistrationResponse getAllClientData() {

		LOG.info("Request for get all client data ");
		accessTokenRetryCount = 0;

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getAllClientDataApiEndpointUrl(), FORMAT_SPECIFIER);

		/*
		 * if (httpHeaders == null) { String plainCredentials =
		 * electraApiConfig.getClientId() + ":" + electraApiConfig.getClientSecret();
		 * String base64ClientCredentials = new
		 * String(Base64Utils.encodeToString(plainCredentials.getBytes()));
		 * 
		 * httpHeaders = new HttpHeaders(); httpHeaders.add(AUTHORIZATION_HEADER,
		 * electraApiConfig.getHeaderAuthorization() + base64ClientCredentials); //
		 * httpHeaders.set(ACCEPT_HEADER, electraApiConfig.getHeaderAccept());
		 * httpHeaders.set(CONTENT_TYPE_HEADER,
		 * electraApiConfig.getHeaderContentType());
		 * httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); }
		 * 
		 * HttpEntity<String> entity = new HttpEntity<String>("parameters",
		 * httpHeaders);
		 * 
		 * ResponseEntity<ClientRegistrationResponse> result =
		 * restTemplate.exchange(endpointUrl, HttpMethod.GET, entity,
		 * ClientRegistrationResponse.class); ClientRegistrationResponse
		 * clientRegistrationResponse = result.getBody();
		 */

		ClientRegistrationResponse clientRegistrationResponse = (ClientRegistrationResponse) invokeApi(endpointUrl,
				HttpMethod.GET, ClientRegistrationResponse.class, null, false, true);

		return clientRegistrationResponse;
	}

	@Override
	public ClientPageResponse getAllClientCustom(Pageable pageable, String clientCorrelationId) {

		// TODO Configure client asset token.
		ClientPageRequest clientPageRequest = new ClientPageRequest();
		clientPageRequest.setPageNumber(pageable.getPageNumber());
		clientPageRequest.setPageSize(pageable.getPageSize());
		// clientPageRequest.setPageable(pageable);

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getAllClientCustomApiEndpointUrl(), clientCorrelationId,
						FORMAT_SPECIFIER);
		LOG.info("APIendpointUrl to getAllClientCustom :" + endpointUrl);
		ClientPageResponse clientPageResponse = (ClientPageResponse) invokeApi(endpointUrl, HttpMethod.POST,
				ClientPageResponse.class, clientPageRequest, false, true);

		// Page<ClientProfile> pageList = clientPageResponse.getPageList();
		return clientPageResponse;
	}

	@Override
	public ClientRegistrationResponse createClient(ClientRegistrationRequest clientRegistrationRequest) {

		accessTokenRetryCount = 0;

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientRegistrationApiEndpointUrl(), FORMAT_SPECIFIER);

		ClientRegistrationResponse clientRegistrationResponse = (ClientRegistrationResponse) invokeApi(endpointUrl,
				HttpMethod.POST, ClientRegistrationResponse.class, clientRegistrationRequest, false, true);

		return clientRegistrationResponse;
	}

	@Override
	public ClientRegistrationResponse updateClient(ClientRegistrationRequest clientRegistrationRequest) {

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientUpdationApiEndpointUrl(),
						clientRegistrationRequest.getClientId(), FORMAT_SPECIFIER);
		LOG.info("APIndpointUrl to update client :" + endpointUrl);

		ClientRegistrationResponse clientRegistrationResponse = (ClientRegistrationResponse) invokeApi(endpointUrl,
				HttpMethod.PUT, ClientRegistrationResponse.class, clientRegistrationRequest, false, true);

		return clientRegistrationResponse;
	}

	@Override
	public ClientRegistrationResponse updateClientDetail(ClientRegistrationRequest clientRegistrationRequest) {

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientDetailUpdationApiEndpointUrl(),
						clientRegistrationRequest.getClientId(), FORMAT_SPECIFIER);
		LOG.info("APIndpointUrl to update client :" + endpointUrl);

		ClientRegistrationResponse clientRegistrationResponse = (ClientRegistrationResponse) invokeApi(endpointUrl,
				HttpMethod.PUT, ClientRegistrationResponse.class, clientRegistrationRequest, false, true);

		return clientRegistrationResponse;
	}

	@Override
	public UserRegistrationResponse updateUser(UserRegistrationRequest userRegistrationRequest,
			String userCorrelationId) {

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion()) + "/"
				+ String.format(userRegistrationRequest.getClientId()) + String.format(
						electraApiConfig.getUserUpdationApiEndpointUrl(), userCorrelationId, FORMAT_SPECIFIER);

		LOG.info("API EndpointUrl to update user :" + endpointUrl);

		UserRegistrationResponse clientRegistrationResponse = (UserRegistrationResponse) invokeApi(endpointUrl,
				HttpMethod.PUT, UserRegistrationResponse.class, userRegistrationRequest, false, true);
		return clientRegistrationResponse;
	}

	@Override
	public UserAgentResponse updateAgent(UserAgentRegistrationRequest userAgentRegistrationRequest) {

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion()) + "/"
				+ String.format(electraApiConfig.getClientCorrelationId())
				+ String.format(electraApiConfig.getAgentUpdationApiEndpointUrl(), FORMAT_SPECIFIER);
		LOG.info("APIndpointUrl to update agent :" + endpointUrl);

		UserAgentResponse userAgentResponse = (UserAgentResponse) invokeApi(endpointUrl, HttpMethod.PUT,
				UserAgentResponse.class, userAgentRegistrationRequest, false, true);

		LOG.info("Response for agent updation :" + userAgentResponse.toString());

		return userAgentResponse;
	}

	@Override
	public UserAgentRegistrationResponse getAgentList(String userCorrelationId) {
		LOG.info("Creating agent list response  for user correletionId : " + userCorrelationId);
		accessTokenRetryCount = 0;

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion()) + "/"
				+ String.format(electraApiConfig.getClientCorrelationId()) + String.format(
						electraApiConfig.getAgentListApiEndpointUrl(), userCorrelationId, FORMAT_SPECIFIER);

		/*
		 * if (httpHeaders == null) { String plainCredentials =
		 * electraApiConfig.getClientId() + ":" + electraApiConfig.getClientSecret();
		 * String base64ClientCredentials = new
		 * String(Base64Utils.encodeToString(plainCredentials.getBytes()));
		 * 
		 * httpHeaders = new HttpHeaders(); httpHeaders.add(AUTHORIZATION_HEADER,
		 * electraApiConfig.getHeaderAuthorization() + base64ClientCredentials); //
		 * httpHeaders.set(ACCEPT_HEADER, electraApiConfig.getHeaderAccept());
		 * httpHeaders.set(CONTENT_TYPE_HEADER,
		 * electraApiConfig.getHeaderContentType());
		 * httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); }
		 * 
		 * HttpEntity<String> entity = new HttpEntity<String>("parameters",
		 * httpHeaders);
		 * 
		 * ResponseEntity<UserAgentRegistrationResponse> result =
		 * restTemplate.exchange(endpointUrl, HttpMethod.GET, entity,
		 * UserAgentRegistrationResponse.class); UserAgentRegistrationResponse
		 * userAgentRegistrationResponse = result.getBody();
		 */

		UserAgentRegistrationResponse userAgentRegistrationResponse = (UserAgentRegistrationResponse) invokeApi(
				endpointUrl, HttpMethod.GET, UserAgentRegistrationResponse.class, null, false, true);

		return userAgentRegistrationResponse;
	}

	@Override
	public ClientCredentials getClientCredential(String clientCorrelationId) {
		// TODO Auto-generated method stub
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientCredentialApiEndpointUrl(), clientCorrelationId,
						retrieveApiAccessToken());
		LOG.info("APIndpointUrl to getClientCredential :" + endpointUrl);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
		ResponseEntity<ClientCredentials> result = restTemplate.exchange(endpointUrl, HttpMethod.GET, entity,
				ClientCredentials.class);
		ClientCredentials clientCredentialsReponse = result.getBody();
		// clientCredentialsReponse.getClientApiSecret();

		return clientCredentialsReponse;
	}

	@Override
	public ClientAssetTokenResponse clientAssetTokenConfiguration(ClientAssetTokenRequest clientAssetTokenRequest) {
		// TODO Configure client asset token.
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientAssetTokenApiEndpointUrl(),
						clientAssetTokenRequest.getClientCorrelationId(), FORMAT_SPECIFIER);
		LOG.info("APIendpointUrl to clientAssetTokenConfiguration :" + endpointUrl);
		ClientAssetTokenResponse clientAssetTokenResponse = (ClientAssetTokenResponse) invokeApi(endpointUrl,
				HttpMethod.POST, ClientAssetTokenResponse.class, clientAssetTokenRequest, false, true);
		return clientAssetTokenResponse;
	}

	@Override
	public ClientProfileResponse clientDetails(String clientCorrelationId) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientDetailsApiEndpointUrl(), clientCorrelationId,
						FORMAT_SPECIFIER);

		LOG.info("APIndpointUrl to clientAssetTokenConfiguration :" + endpointUrl);
		ClientProfileResponse clientProfileResponse = (ClientProfileResponse) invokeApi(endpointUrl, HttpMethod.GET,
				ClientProfileResponse.class, null, false, true);
		return clientProfileResponse;
	}

	@Override
	public ClientRegistrationResponse activateClientCredential(ClientRegistrationRequest clientRegistrationRequest) {

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientCredentialActivationApiEndpointUrl(),
						clientRegistrationRequest.getClientId(), FORMAT_SPECIFIER);
		LOG.info("APIndpointUrl to update client :" + endpointUrl);

		ClientRegistrationResponse clientRegistrationResponse = (ClientRegistrationResponse) invokeApi(endpointUrl,
				HttpMethod.PUT, ClientRegistrationResponse.class, clientRegistrationRequest, false, true);

		return clientRegistrationResponse;
	}

	@Override
	public ClientAssetTokenResponse allClientAssetToken(String clientCorrelationId, String page, String size) {
		// TODO Auto-generated method stub

		/*
		 * String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(),
		 * electraApiConfig.getApiVersion()) +
		 * String.format(electraApiConfig.getClientTokenDetailsApiEndpointUrl(),
		 * clientCorrelationId, retrieveApiAccessToken());
		 */

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getClientTokenDetailsPagesApiEndpointUrl(), clientCorrelationId, page,
						size, FORMAT_SPECIFIER);

		LOG.info("APIndpointUrl to clientAssetTokenConfiguration :" + endpointUrl);

		ClientAssetTokenResponse clientAssetTokenResponse = (ClientAssetTokenResponse) invokeApi(endpointUrl,
				HttpMethod.GET, ClientAssetTokenResponse.class, null, false, true);
		return clientAssetTokenResponse;
	}

	@Override
	public AccountDetailsResponse getAccountDetailsByPublicKey(String publicKey) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getAccountDetailsByPublicKeyApiEndpointUrl(), publicKey,
						FORMAT_SPECIFIER);

		AccountDetailsResponse accountDetailsResponse = (AccountDetailsResponse) invokeApi(endpointUrl, HttpMethod.GET,
				AccountDetailsResponse.class, null, false, true);

		// System.out.println("publicKey:::["+publicKey+"]:"+accountDetailsResponse);

		return accountDetailsResponse;
	}

	@Override
	public ExchangeResponse getAllExchangeDetails() {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getExchangeDetailsApiEndpointUrl(), FORMAT_SPECIFIER);
		ExchangeResponse exchangeResponse = (ExchangeResponse) invokeApi(endpointUrl, HttpMethod.GET,
				ExchangeResponse.class, null, false, true);

		return exchangeResponse;
	}

	@Override
	public FilterLedgerResponse getLedger(FilterLedgerRequest filterLedgerRequest, String page, String size) {

		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				filterLedgerRequest.getClientCorrelationId())
				+ String.format(electraApiConfig.getClientLedgerDetailsPagesApiEndpointUrl(), page, size,
						FORMAT_SPECIFIER);

		LOG.info("APIndpointUrl to clientAssetTokenConfiguration :" + endpointUrl);

		FilterLedgerResponse filterLedgerResponse = (FilterLedgerResponse) invokeApi(endpointUrl, HttpMethod.POST,
				FilterLedgerResponse.class, filterLedgerRequest, false, true);

		return filterLedgerResponse;
	}

	@Override
	public CountryListResponse getCountryLists(String clientCorrelationId) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getCountryListApiEndpointUrl(), clientCorrelationId,
						FORMAT_SPECIFIER);

		LOG.info("APIndpointUrl to clientAssetTokenConfiguration :" + endpointUrl);
		CountryListResponse countryListResponse = (CountryListResponse) invokeApi(endpointUrl, HttpMethod.GET,
				CountryListResponse.class, null, false, true);
		return countryListResponse;
	}

	@Override
	public ClentAgentInvitationResponse saveClientAgentInvitationDetails(
			ClentAgentInvitationRequest clentAgentInvitationRequest) {
		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				clentAgentInvitationRequest.getClientId())
				+ String.format(electraApiConfig.getClientAgentInvitationApiEndpointUrl(), FORMAT_SPECIFIER);
		System.out.println(endpointUrl);

		ClentAgentInvitationResponse clentAgentInvitationResponse = (ClentAgentInvitationResponse) invokeApi(
				endpointUrl, HttpMethod.POST, ClentAgentInvitationResponse.class, clentAgentInvitationRequest, false,
				true);

		return clentAgentInvitationResponse;
	}

	@Override
	public ExchangeResponse updateExchangeDetails(ExchangeDto clientExchangeRequest) {
		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				clientExchangeRequest.getClientCorrelationId())
				+ String.format(electraApiConfig.getUpdateExchangeApiEndpointUrl(), FORMAT_SPECIFIER);
		System.out.println(endpointUrl);

		ExchangeResponse exchangeResponse = (ExchangeResponse) invokeApi(endpointUrl, HttpMethod.POST,
				ExchangeResponse.class, clientExchangeRequest, false, true);
		return exchangeResponse;
	}

	@Override
	public void downloadExcelAndCsv(FilterLedgerRequest filterLedgerRequest, HttpServletResponse res) {
		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				filterLedgerRequest.getClientCorrelationId())
				+ String.format(electraApiConfig.getDownloadExcelAndCsvApiEndpointUrl(), FORMAT_SPECIFIER);
		// DecimalFormat decimalFormat = new DecimalFormat("#.00");
		LOG.info("APIndpointUrl to clientAssetTokenConfiguration :" + endpointUrl);

		FilterLedgerResponse filterLedgerResponse = (FilterLedgerResponse) invokeApi(endpointUrl, HttpMethod.POST,
				FilterLedgerResponse.class, filterLedgerRequest, false, true);

		List<TransactionDto> transactionList = filterLedgerResponse.getTransactionList();

		
		if (filterLedgerRequest.getDataFormat().equals("EXCEL")) {
			
			

			//TransactionDto transactionDtoForSymbol = transactionList.get(0);
			// res.setContentType("application/octet-stream");
			res.addHeader("Content-Type", "application/octet-stream");

			res.addHeader("Content-Disposition", "attachment; filename=Business_ledger_report.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Business Ledger");

			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Transaction No.");
			header.createCell(1).setCellValue("Type");
			header.createCell(2).setCellValue("Date");
			header.createCell(3).setCellValue("Sender");
			header.createCell(4).setCellValue("Receiver");
			header.createCell(5).setCellValue("Amount(" + filterLedgerResponse.getCurrencySymbol() + ")");
			// header.createCell(6).setCellValue("Transaction Hash");
			// Transaction hash hide from from the excel file and csv file as per
			// instruction QA team (Abhinav and sumit).

			int rowNum = 1;
			if(!transactionList.isEmpty() || transactionList.size()>0){
			for (TransactionDto transactionDto : transactionList) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(transactionDto.getTransactionSequenceNo());
				row.createCell(1).setCellValue(transactionDto.getOperation().toString());
				row.createCell(2).setCellValue(transactionDto.getTransactionDate());
				row.createCell(3).setCellValue(transactionDto.getSourceAccountName());
				row.createCell(4).setCellValue(transactionDto.getDestinationAccountName());
				if (transactionDto.getTnxData() != null) {
					row.createCell(5).setCellValue(String.format("%.2f", new Double(transactionDto.getTnxData())));
				} else {
					row.createCell(5).setCellValue("0.00");
				}

				// row.createCell(6).setCellValue(transactionDto.getTnxHash());
			}
			}
			try {
				workbook.write(res.getOutputStream());
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//}
		
		if (filterLedgerRequest.getDataFormat().equals("CSV")) {

			// String header = "Transaction No.;Type;Date;Sender;Receiver;Amount;Transaction
			// Hash";
			//TransactionDto transactionDtoForSymbol = transactionList.get(0);
			String header = "Transaction No.;Type;Date;Sender;Receiver;Amount("
					+ filterLedgerResponse.getCurrencySymbol() + ")";
			String newLine = "\n";

			res.setContentType("application/octet-stream");
			res.addHeader("Content-Type", "application/octet-stream");
			res.addHeader("Content-Disposition", "attachment; filename=Business_ledger_report.csv");
			try {
				res.getOutputStream().write(header.toString().getBytes());
				res.getOutputStream().write(newLine.toString().getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!transactionList.isEmpty() || transactionList.size()>0){
			for (TransactionDto transactionDto : transactionList) {
				try {
					res.getOutputStream().write(transactionDto.toString().getBytes());
					// System.out.println(transactionDto.toString());
					res.getOutputStream().write(newLine.toString().getBytes());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			try {
				res.getOutputStream().flush();
				res.getOutputStream().close();
				;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 //}
	}

	@Override
	public TransactionLimitResponse getTransactionLimitData() {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getTransactionLimitDataApiEndpointUrl(), FORMAT_SPECIFIER);
		System.out.println(endpointUrl);
		TransactionLimitResponse transactionLimitResponse = (TransactionLimitResponse) invokeApi(endpointUrl,
				HttpMethod.GET, TransactionLimitResponse.class, null, false, true);
		return transactionLimitResponse;
	}

	@Override
	public ApprovelResponse ekycEkybApprovelList() {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getEkycEkybApprovelListApiEndpointUrl(), FORMAT_SPECIFIER);
		// System.out.println(endpointUrl);
		LOG.info("APIndpointUrl to ekycEkybApprovelList :" + endpointUrl);
		ApprovelResponse approvelResponse = (ApprovelResponse) invokeApi(endpointUrl, HttpMethod.GET,
				ApprovelResponse.class, null, false, true);
		return approvelResponse;
	}

	@Override
	public ApprovelResponse ekycEkybApprovedList() {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getEkycEkybApprovedListApiEndpointUrl(), FORMAT_SPECIFIER);
		System.out.println(endpointUrl);
		ApprovelResponse approvelResponse = (ApprovelResponse) invokeApi(endpointUrl, HttpMethod.GET,
				ApprovelResponse.class, null, false, true);
		return approvelResponse;
	}

	@Override
	public ApprovelResponse approveEkycEkyb(ApprovelRequest approvelRequest) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getEkycEkybApproveApiEndpointUrl(), FORMAT_SPECIFIER);
		System.out.println(endpointUrl);
		ApprovelResponse approvelResponse = (ApprovelResponse) invokeApi(endpointUrl, HttpMethod.POST,
				ApprovelResponse.class, approvelRequest, false, true);
		return approvelResponse;
	}

	@Override
	public ProductGroupResponse getProductGroupList(String clientCorrelationId) {
		ProductGroupResponse productGroupResponse = new ProductGroupResponse();

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getProductGroupsApiName(), clientCorrelationId,
						FORMAT_SPECIFIER);
		LOG.info("API endpointUrl to getProductGroupList :" + endpointUrl);

		productGroupResponse = (ProductGroupResponse) invokeApi(endpointUrl, HttpMethod.GET, ProductGroupResponse.class,
				null, false, true);

		return productGroupResponse;
	}

	@Override
	public SaveProductResponse saveProduct(SaveProductRequest productRequest) {
		SaveProductResponse productResponse = new SaveProductResponse();

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getSaveProductApiName(), productRequest.getClientCorrelationId(),
						FORMAT_SPECIFIER);
		System.out.println(endpointUrl);
		productResponse = (SaveProductResponse) invokeApi(endpointUrl, HttpMethod.POST, SaveProductResponse.class,
				productRequest, false, true);
		return productResponse;
	}

	@Override
	public SaveProductResponse editProduct(SaveProductRequest productRequest) {
		SaveProductResponse productResponse = new SaveProductResponse();

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getEditProductApiName(), productRequest.getClientCorrelationId(),
						FORMAT_SPECIFIER);
		System.out.println(endpointUrl);
		productResponse = (SaveProductResponse) invokeApi(endpointUrl, HttpMethod.POST, SaveProductResponse.class,
				productRequest, false, true);
		return productResponse;
	}

	@Override
	public SaveProductResponse deleteProduct(String productCode) {
		SaveProductResponse productResponse = new SaveProductResponse();

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getDeleteProductApiName(), productCode, FORMAT_SPECIFIER);

		productResponse = (SaveProductResponse) invokeApi(endpointUrl, HttpMethod.GET, SaveProductResponse.class, null,
				false, true);

		return productResponse;
	}

	@Override
	public ProductResponse getProducts(ProductRequest productRequest, String clientCorrelationId) {
		ProductResponse productResponse = new ProductResponse();

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getGetProductApiName(), clientCorrelationId, FORMAT_SPECIFIER);
		System.out.println(endpointUrl);
		productResponse = (ProductResponse) invokeApi(endpointUrl, HttpMethod.POST, ProductResponse.class,
				productRequest, false, true);
		return productResponse;
	}

	@Override
	public TransactionPurposeResponse getAllTransactionPurposeList(String clientCorrelationId) {

		TransactionPurposeResponse transactionPurposeResponse = new TransactionPurposeResponse();

		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				clientCorrelationId)
				+ String.format(electraApiConfig.getAllTransactionPurposeApiEndpointUrl(), FORMAT_SPECIFIER);

		LOG.info("API endpointUrl to getAllTransactionPurposeList :" + endpointUrl);

		transactionPurposeResponse = (TransactionPurposeResponse) invokeApi(endpointUrl, HttpMethod.GET,
				TransactionPurposeResponse.class, null, false, true);
		// TODO Auto-generated method stub
		return transactionPurposeResponse;
	}

	@Override
	public TransactionPurposeResponse addAndUpdateTransactionPurpose(
			TransactionPurposeRequest transactionPurposeRequest) {
		TransactionPurposeResponse transactionPurposeResponse = new TransactionPurposeResponse();

		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				transactionPurposeRequest.getClientId())
				+ String.format(electraApiConfig.getSaveUpdateTransactionPurposeApiEndpointUrl(),
						FORMAT_SPECIFIER);

		LOG.info("API endpointUrl to addAndUpdateTransactionPurpose :" + endpointUrl);

		transactionPurposeResponse = (TransactionPurposeResponse) invokeApi(endpointUrl, HttpMethod.POST,
				TransactionPurposeResponse.class, transactionPurposeRequest, false, true);

		return transactionPurposeResponse;
	}

	@Override
	public TransactionPurposeResponse deleteTransactionPurpose(TransactionPurposeRequest transactionPurposeRequest) {
		TransactionPurposeResponse transactionPurposeResponse = new TransactionPurposeResponse();

		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				transactionPurposeRequest.getClientId())
				+ String.format(electraApiConfig.getDeleteTransactionPurposeApiEndpointUrl(), FORMAT_SPECIFIER);

		LOG.info("API endpointUrl to deleteTransactionPurpose :" + endpointUrl);

		transactionPurposeResponse = (TransactionPurposeResponse) invokeApi(endpointUrl, HttpMethod.POST,
				TransactionPurposeResponse.class, transactionPurposeRequest, false, true);

		return transactionPurposeResponse;
	}

	@Override
	public FilterLedgerResponse getOnlineOfflineLedger(FilterLedgerRequest filterLedgerRequest, String page,
			String size) {
		String endpointUrl = String.format(electraApiConfig.getBaseUrl(), electraApiConfig.getApiVersion(),
				filterLedgerRequest.getClientCorrelationId())
				+ String.format(electraApiConfig.getOnlineOfflineLedgerApiEndpointUrl(), page, size,
						FORMAT_SPECIFIER);

		LOG.info("APIndpointUrl to get online offline ledger :" + endpointUrl);

		FilterLedgerResponse filterLedgerResponse = (FilterLedgerResponse) invokeApi(endpointUrl, HttpMethod.POST,
				FilterLedgerResponse.class, filterLedgerRequest, false, true);
		return filterLedgerResponse;
	}

	@Override
	public SubscriptionPlanResponse addUpdateSubscriptionPlan(SubscriptionPlanRequest subscriptionPlanRequest) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getAddUpdateSubscriptionPlanApiEndpointUrl(),
						FORMAT_SPECIFIER);

		LOG.info("API endpointUrl to add or update subscription plan :" + endpointUrl);

		SubscriptionPlanResponse subscriptionPlanResponse = (SubscriptionPlanResponse) invokeApi(endpointUrl,
				HttpMethod.POST, SubscriptionPlanResponse.class, subscriptionPlanRequest, false, true);
		return subscriptionPlanResponse;
	}

	@Override
	public SubscriptionPlanResponse deleteSubscriptionPlan(SubscriptionPlanRequest subscriptionPlanRequest) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getDeleteSubscriptionPlanApiEndpointUrl(), FORMAT_SPECIFIER);

		LOG.info("API endpointUrl to delete subscription plan :" + endpointUrl);

		SubscriptionPlanResponse subscriptionPlanResponse = (SubscriptionPlanResponse) invokeApi(endpointUrl,
				HttpMethod.POST, SubscriptionPlanResponse.class, subscriptionPlanRequest, false, true);
		return subscriptionPlanResponse;
	}

	@Override
	public SubscriptionPlanResponse getSubscriptionPlanListByCountry(String countryId) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getSubscriptionPlanListApiEndpointUrl(), countryId,
						FORMAT_SPECIFIER);

		LOG.info("API endpointUrl to get subscription plan list :" + endpointUrl);

		SubscriptionPlanResponse subscriptionPlanResponse = (SubscriptionPlanResponse) invokeApi(endpointUrl,
				HttpMethod.GET, SubscriptionPlanResponse.class, null, false, true);
		return subscriptionPlanResponse;
	}

	@Override
	public SubscriptionPlanResponse getSubscriptionPlanListForAdmin(String page, String size) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getSubscriptionPlanListForAdminApiEndpointUrl(), page, size,
						FORMAT_SPECIFIER);

		LOG.info("APIndpointUrl to get subscription plan list for admin :" + endpointUrl);

		SubscriptionPlanResponse subscriptionPlanResponse = (SubscriptionPlanResponse) invokeApi(endpointUrl,
				HttpMethod.POST, SubscriptionPlanResponse.class, null, false, true);
		return subscriptionPlanResponse;
	}

}
