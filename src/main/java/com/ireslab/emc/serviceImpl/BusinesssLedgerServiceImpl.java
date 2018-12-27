package com.ireslab.emc.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ireslab.emc.ElectraApiConfig;
import com.ireslab.emc.model.AccountDetailsResponse;
import com.ireslab.emc.model.LedgerResponse;
import com.ireslab.emc.model.PaymentLedgerBalance;
import com.ireslab.emc.service.BusinessLedgerService;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.serviceImpl.LedgerServiceImpl.JsonKeyEnum;
import com.ireslab.emc.serviceImpl.UserLedgerServiceImpl.JsonKeyPaymentEnum;

@Service
public class BusinesssLedgerServiceImpl implements BusinessLedgerService {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BusinesssLedgerServiceImpl.class);
	
	@Autowired
	private ElectraApiConfig electraApiConfig;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private ClientUserInfoService clientUserInfoService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	

	@Override
	public LedgerResponse requestPaymentJson(String accountID, LedgerResponse ledgerResponse, String next,
			String limit) {
		// TODO Auto-generated method stub
		// String URL = "https://horizon-testnet.stellar.org/accounts/" +
		// accountID+"/payments";

		String URL = "https://horizon-testnet.stellar.org/accounts/" + accountID + "/payments?cursor=&limit=" + limit
				+ "&order=desc";
		if (!next.equals("0")) {
			URL = "https://horizon-testnet.stellar.org/accounts/" + accountID + "/payments?cursor=" + next + "&limit="
					+ limit + "&order=desc";
		}

		log.info("url for cursor :[" + next + "] :" + URL);
		// String URL = "https://horizon-testnet.stellar.org/accounts/" +
		// accountID+"/payments?cursor=&limit=200&order=asc";
		ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
		String json = response.getBody();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		JsonObject jsonObject = je.getAsJsonObject(); 
		JsonObject jsonObjecte = jsonObject.getAsJsonObject(JsonKeyEnum._embedded.toString());
		// JsonObject jsonObjectR =
		// jsonObject.getAsJsonObject(JsonKeyEnum.records.toString());

		JsonArray paymentJsonArray = jsonObjecte.getAsJsonArray(JsonKeyEnum.records.toString());
		// System.out.println("paymentJsonArray :"+paymentJsonArray);

		// LedgerResponse ledgerResponse = new LedgerResponse();
		List<LedgerResponse.Payment> paymentList = new ArrayList<LedgerResponse.Payment>();
		int index = 0;
		int lastIndex = paymentJsonArray.size() - 1;
		int transaction =1;
		//System.out.println("index value before logic :[" + index + "]" + "lastIndex value : [" + lastIndex + "]");
		for (JsonElement jsonElement : paymentJsonArray) {
			JsonObject records = jsonElement.getAsJsonObject();
			LedgerResponse.Payment payment = ledgerResponse.new Payment();
			String type = getValidJson(records, JsonKeyPaymentEnum.type.toString());
			String transactionNo =null;

			if (type != null) {

				String id = getValidJson(records, JsonKeyPaymentEnum.id.toString());
				String pagingToken = getValidJson(records, JsonKeyPaymentEnum.paging_token.toString());
				String sourceAccount = getValidJson(records, JsonKeyPaymentEnum.source_account.toString());
				String createdAt = getValidJson(records, JsonKeyPaymentEnum.created_at.toString());
				String transactionHash = getValidJson(records, JsonKeyPaymentEnum.transaction_hash.toString());
				String assetCode = getValidJson(records, JsonKeyPaymentEnum.asset_code.toString());
				String assetIssuer = getValidJson(records, JsonKeyPaymentEnum.asset_issuer.toString());
				String from = getValidJson(records, JsonKeyPaymentEnum.from.toString());
				String to = getValidJson(records, JsonKeyPaymentEnum.to.toString());
				String amount = getValidJson(records, JsonKeyPaymentEnum.amount.toString());

				if (amount != null && amount.length() > 0) {

					if (index == 1) {

						ledgerResponse.setPrevious(id);
					} else if (lastIndex == index) {

						ledgerResponse.setNext(id);
					}
					payment.setType(type);
					payment.setTransactionNo("STX-"+transaction);
					payment.setId(id);
					payment.setTransactionHash(transactionHash);
					payment.setAssetIssuer(assetIssuer);
					// payment.setAssetIssuer(assetIssuer);
					payment.setAssetCode(assetCode);
					payment.setFrom(from);
					LedgerResponse.Payment.AccoutDetails fromAccoutDetails = payment.new AccoutDetails();

					AccountDetailsResponse account = clientUserInfoService.getAccountDetailsByPublicKey(from);
					Map<String, LedgerResponse.Payment.AccoutDetails> accoutDetailsData = new HashMap<>();
					if (account != null && account.getFirstName() != null) {
						fromAccoutDetails.setFirstName(account.getFirstName());
						fromAccoutDetails.setLastName(account.getLastName());
						accoutDetailsData.put(from, fromAccoutDetails);
					} else if (account != null && account.getClientName() != null) {
						fromAccoutDetails.setClientName(account.getClientName());
						accoutDetailsData.put(from, fromAccoutDetails);
					} else {

						fromAccoutDetails.setMessage("unknown");
						accoutDetailsData.put(from, fromAccoutDetails);

					}
					payment.setFromDetails(accoutDetailsData);

					payment.setTo(to);
					LedgerResponse.Payment.AccoutDetails toAccoutDetails = payment.new AccoutDetails();

					AccountDetailsResponse accountDetailsObject = clientUserInfoService.getAccountDetailsByPublicKey(to);
					// System.out.println("publicKey:::["+to+"]:"+account);
					Map<String, LedgerResponse.Payment.AccoutDetails> toAccoutDetailsData = new HashMap<>();
					if (accountDetailsObject != null && accountDetailsObject.getFirstName() != null) {
						toAccoutDetails.setFirstName(accountDetailsObject.getFirstName());
						toAccoutDetails.setLastName(accountDetailsObject.getLastName());
						toAccoutDetailsData.put(to, toAccoutDetails);
					} else if (accountDetailsObject != null && accountDetailsObject.getClientName() != null) {
						toAccoutDetails.setClientName(accountDetailsObject.getClientName());
						toAccoutDetailsData.put(to, toAccoutDetails);
					} else {
						toAccoutDetails.setMessage("unknown");
						toAccoutDetailsData.put(to, toAccoutDetails);

					}
					payment.setToDetails(toAccoutDetailsData);

					payment.setCreatedAt(createdAt);
					payment.setAmount(amount);
					paymentList.add(payment);
				}

			}
			index++;
			transaction++;
		}
		ledgerResponse.setPayment(paymentList);
		return ledgerResponse;
	}

	private String getValidJson(JsonObject jsonObject, String key) {
		if (!jsonObject.has(key)) {
			return "";
		} else {
			return jsonObject.get(key).getAsString();
		}
	}



	@Override
	public PaymentLedgerBalance requestPaymentBalence(String accountID) {
		// TODO Auto-generated method stub
		return null;
	}

}
