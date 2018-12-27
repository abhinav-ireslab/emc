package com.ireslab.emc.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ireslab.emc.controller.ConsoleUiController;
import com.ireslab.emc.model.AccountDetailsResponse;
import com.ireslab.emc.model.LedgerResponse;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.UserLedgerService;
import com.ireslab.emc.serviceImpl.LedgerServiceImpl.JsonKeyEnum;

@Service
public class UserLedgerServiceImpl implements UserLedgerService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserLedgerServiceImpl.class);

	/*
	 * else {
	 * 
	 * keyDetails.setMessage("unknown"); keyDetailsMap.put(key, keyDetails);
	 * 
	 * } signers.setKeyDetails(keyDetailsMap);
	 */

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	enum JsonKeyPaymentEnum {
		id, paging_token, source_account, type, type_i, created_at, transaction_hash, asset_type, asset_code, asset_issuer, from, to, amount, records
	}

	@Override
	public LedgerResponse requestAssetsJson(String accountID) {
		// TODO Auto-generated method stub
		LedgerResponse ledgerResponse = userAssetsJson(accountID);

		return ledgerResponse;
	}

	private LedgerResponse userAssetsJson(String accountID) {

		String URL = "https://horizon-testnet.stellar.org/accounts/" + accountID + "?cursor=&limit=200&order=desc";
        //System.out.println("URL for user asserts json : "+URL);
		ResponseEntity<String> response = null;
		response = restTemplate.getForEntity(URL, String.class);

		String json = response.getBody();

		//System.out.println("JSONNNNN :"+json);

		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		JsonObject jsonObject = je.getAsJsonObject();

		// JsonArray signersJsonArray =
		// jsonObject.getAsJsonArray(JsonKeyEnum.signers.toString());

		JsonArray balancesJsonArray = jsonObject.getAsJsonArray(JsonKeyEnum.balances.toString());

		LedgerResponse ledgerResponse = new LedgerResponse();
		List<LedgerResponse.Assets> ledgerResponseAssets = new ArrayList<LedgerResponse.Assets>();
		for (JsonElement jsonElement : balancesJsonArray) {

			JsonObject records = jsonElement.getAsJsonObject();

			LedgerResponse.Assets assets = ledgerResponse.new Assets();
			String assetCode = getValidJson(records, JsonKeyEnum.asset_code.toString());
			String assetIssuer = getValidJson(records, JsonKeyEnum.asset_issuer.toString());

			if ((assetCode != null && assetCode.length() > 0) && (assetIssuer != null && assetIssuer.length() > 0)) {
				String bal = getValidJson(records, JsonKeyEnum.balance.toString());
				String limit = getValidJson(records, JsonKeyEnum.limit.toString());
				String assetType = getValidJson(records, JsonKeyEnum.asset_type.toString());
				// System.out.println(assetCode);
				assets.setCode(assetCode);
				assets.setIssuer(assetIssuer);
				LedgerResponse.Assets.AccoutDetails issuerccoutDetails = assets.new AccoutDetails();
				Map<String, LedgerResponse.Assets.AccoutDetails> issuerDetails = new HashMap<>();
				AccountDetailsResponse account = clientUserInfoService.getAccountDetailsByPublicKey(assetIssuer);
				if (account != null && account.getFirstName() != null) {
					issuerccoutDetails.setFirstName(account.getFirstName());
					issuerccoutDetails.setLastName(account.getLastName());
					issuerDetails.put(assetIssuer, issuerccoutDetails);
				} else if (account != null && account.getClientName() != null) {
					issuerccoutDetails.setClientName(account.getClientName());
					issuerDetails.put(assetIssuer, issuerccoutDetails);
				} else {

					issuerccoutDetails.setMessage("unknown");
					issuerDetails.put(assetIssuer, issuerccoutDetails);

				}

				assets.setIssuerDetails(issuerDetails);
				assets.setBalance(bal);
				assets.setLimit(limit);

				ledgerResponseAssets.add(assets);
				ledgerResponse.setAssets(ledgerResponseAssets);
			}
		}
		return ledgerResponse;
	}

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

		//System.out.println("index value before logic :[" + index + "]" + "lastIndex value : [" + lastIndex + "]");
		for (JsonElement jsonElement : paymentJsonArray) {
			JsonObject records = jsonElement.getAsJsonObject();
			LedgerResponse.Payment payment = ledgerResponse.new Payment();
			String type = getValidJson(records, JsonKeyPaymentEnum.type.toString());

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

}
