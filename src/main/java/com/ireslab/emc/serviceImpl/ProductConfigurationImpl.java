package com.ireslab.emc.serviceImpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ireslab.emc.ElectraApiConfig;
import com.ireslab.emc.model.SubscriptionPlanResponse;
import com.ireslab.sendx.electra.model.GenericRequest;
import com.ireslab.sendx.electra.model.GenericResponse;
import com.ireslab.sendx.electra.model.GstHsnSacLoadRequest;
import com.ireslab.sendx.electra.model.OAuth2Dto;
import com.ireslab.sendx.electra.model.ProductConfigurationRequest;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.ProductConfiguration;
import com.ireslab.sendx.electra.model.ProductConfigurationResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;

@Component
@PropertySource(value = "classpath:electra_api_config.properties")
@ConfigurationProperties
public class ProductConfigurationImpl implements ProductConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ProductConfigurationImpl.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	// -------------------------------------------------------------------------------------

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

				com.ireslab.sendx.electra.model.Error error = auth2Dto.getErrors().get(0);
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
			//apiEndpointUrl = endpointUrl;
			apiEndpointUrl = String.format(endpointUrl, retrieveApiAccessToken());
			/*System.out.println("electra url :::: ----> "+endpointUrl);
			System.out.println("Endpoint url ::: ----> "+apiEndpointUrl);*/
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
					genericResponse.getErrors().add(new com.ireslab.sendx.electra.model.Error(auth2Dto.getError(),
							auth2Dto.getError_description()));

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

	// -------------------------------------------------------------------------------------

	@Override
	public ProductConfigurationResponse getAllPaymentTerms() {

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getGetAllPaymentTermsApiEndpointUrl(), FORMAT_SPECIFIER);
		LOG.info("Payment Term Request End poin url :" + endpointUrl);
		ProductConfigurationResponse productConfigurationResponse = (ProductConfigurationResponse) invokeApi(
				endpointUrl, HttpMethod.GET, ProductConfigurationResponse.class, null, false, true);
		return productConfigurationResponse;
	}

	@Override
	public ProductConfigurationResponse getChaptersLsit() {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getGetChaptersListApiEndpointUrl(), FORMAT_SPECIFIER);
		LOG.info("Chapter Request End poin url :" + endpointUrl);
		ProductConfigurationResponse productConfigurationResponse = (ProductConfigurationResponse) invokeApi(
				endpointUrl, HttpMethod.GET, ProductConfigurationResponse.class, null, false, true);
		return productConfigurationResponse;
	}

	@Override
	public ProductConfigurationResponse getAllHsnListBasedOnChapter(String chapterNo, String page, String size) {

		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getAllHsnListBasedOnChapterApiEndpointUrl(), page, size,
						FORMAT_SPECIFIER);
		LOG.info("get All Hsn List Based On Chapter request End point url :" + endpointUrl);

		ProductConfigurationResponse productConfigurationResponse = (ProductConfigurationResponse) invokeApi(
				endpointUrl, HttpMethod.POST, ProductConfigurationResponse.class,
				new ProductConfigurationRequest().setChapterNo(chapterNo), false, true);
		return productConfigurationResponse;
	}

	@Override
	public ProductConfigurationResponse getAllSacListBasedOnSearch(String serarchData, String page, String size) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getGetAllSacListBasedOnSearchApiEndpointUrl(), page, size,
						FORMAT_SPECIFIER);
		LOG.info("get All Sac List Based On Chapter request End point url :" + endpointUrl);

		ProductConfigurationResponse productConfigurationResponse = (ProductConfigurationResponse) invokeApi(
				endpointUrl, HttpMethod.POST, ProductConfigurationResponse.class,
				new ProductConfigurationRequest().setSerarchData(serarchData), false, true);
		return productConfigurationResponse;
	}

	
	@Override
	public ProductConfigurationResponse ExcelDataLoad(GstHsnSacLoadRequest gstHsnSacLoadRequest) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getExcelLoadDataApiEndpointUrl(),
						FORMAT_SPECIFIER);
		LOG.info("data load from excel file request End point url : " + endpointUrl);

		ProductConfigurationResponse productConfigurationResponse = (ProductConfigurationResponse) invokeApi(
				endpointUrl, HttpMethod.POST, ProductConfigurationResponse.class,
				gstHsnSacLoadRequest, false, true);
		return productConfigurationResponse;
	}
	
	
	@Override
	public SendxElectraResponse getAllSettlementReports(String page, String size) {
		
		/*electra.settlement_report_list_api_endpoint_url=/settlement/getAllSettlementReports?access_token=%s
		electra.settlement_report_update_api_endpoint_url=/settlement/updateSettlementReport?access_token=%s*/
		
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getSettlementReportListApiEndpointUrl(),page,size, FORMAT_SPECIFIER);
		System.out.println(endpointUrl);
		SendxElectraResponse SendxElectraResponse = (SendxElectraResponse) invokeApi(endpointUrl, HttpMethod.GET,
				SendxElectraResponse.class, null, false, true);
		return SendxElectraResponse;
	}

	@Override
	public SendxElectraResponse updateSettlementReport(SendxElectraRequest sendxElectraRequest) {
		String endpointUrl = String.format(electraApiConfig.getClientBaseUrl(), electraApiConfig.getApiVersion())
				+ String.format(electraApiConfig.getSettlementReportUpdateApiEndpointUrl(),
						FORMAT_SPECIFIER);
		
		
		LOG.info("API endpointUrl to add or update subscription plan :" + endpointUrl);

		SendxElectraResponse SendxElectraResponse = (SendxElectraResponse) invokeApi(endpointUrl, HttpMethod.POST, SendxElectraResponse.class,
				sendxElectraRequest, false, true);
		return SendxElectraResponse;
	}

}
