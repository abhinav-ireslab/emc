/**
 * 
 */
package com.ireslab.emc.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ireslab.emc.ElectraApiConfig;
import com.ireslab.emc.model.AccountDetailsResponse;
import com.ireslab.emc.model.LedgerRequest;
import com.ireslab.emc.model.PaymentLedger;
import com.ireslab.emc.model.PaymentLedgerBalance;
import com.ireslab.emc.model.PaymentLedgerRecord;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.LedgerService;

/**
 * @author Akhilesh
 *
 */
@Service
public class LedgerServiceImpl implements LedgerService {
	@Autowired
	private ElectraApiConfig electraApiConfig;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private ClientUserInfoService clientUserInfoService;
	
	@Autowired
	private RestTemplate restTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.emc.service.LedgerService#requestLedger()
	 */

	/*
	 * @Override public Page<LedgerResponse> requestLedger(String url) throws
	 * URISyntaxException, IOException { // String endpointUrl =
	 * String.format(electraApiConfig.getClientBaseUrl(), //
	 * electraApiConfig.getApiVersion()) // +
	 * String.format(electraApiConfig.getClientAssetTokenApiEndpointUrl(), //
	 * clientAssetTokenRequest.getClientCorrelationId(), retrieveApiAccessToken());
	 * 
	 * String URL = "https://horizon-testnet.stellar.org/accounts/" + url +
	 * "/payments"; URI serverURI = new URI(URL); // LedgersRequestBuilder
	 * ledgerRequestBuilder = new // LedgersRequestBuilder(serverURI);
	 * Page<LedgerResponse> pages = LedgersRequestBuilder.execute(serverURI); //
	 * Consumer<? super LedgerResponse> action = null;
	 * pages.getRecords().forEach(page -> { System.out.println("base fees======>" +
	 * page.getBaseFee()); });
	 * 
	 * // ledgerRequestBuilder.cursor(token) // LedgerResponse ledgerResponse =
	 * ledgerRequestBuilder.ledger(0); // ledgerResponse.getBaseFee();
	 * 
	 * return pages; }
	 */

	enum JsonKeyEnum {
		_links, next, href, _embedded, records, id, funder, starting_balance, source_account, type_i, paging_token, account, created_at, type, transaction_hash, to, from, amount, asset_code, asset_type, asset_issuer, balances, limit, balance, subentry_count, sequence, account_id, signers, public_key, weight, key, trustee, trustor
	}

	/**
	 * Parse Json and create PaymentLedger object.
	 * 
	 * @param json
	 * @return PaymentLedger
	 * @throws ParseException
	 */
	private PaymentLedger parsePaymentLedgerData(String funderData,String json, boolean isfirstCall) throws ParseException {
		//System.out.println("Json===>" + json);
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		JsonObject jsonObject = je.getAsJsonObject();

		JsonObject nextLinkJson = jsonObject.getAsJsonObject(JsonKeyEnum._links.toString())
				.getAsJsonObject(JsonKeyEnum.next.toString());
		String nextLink = getValidJson(nextLinkJson, JsonKeyEnum.href.toString());
		
		JsonArray recordsJsonArray = jsonObject.getAsJsonObject(JsonKeyEnum._embedded.toString())
				.getAsJsonArray(JsonKeyEnum.records.toString());
		
		// To get funder details
		
		JsonParser jpFunder = new JsonParser();
		JsonElement jeFunder = jpFunder.parse(funderData);
		JsonObject jsonObjectFunder = jeFunder.getAsJsonObject();
		JsonArray recordsJsonArrayFunder = jsonObjectFunder.getAsJsonObject(JsonKeyEnum._embedded.toString())
				.getAsJsonArray(JsonKeyEnum.records.toString());
		
		//----
		
		

		PaymentLedger paymentLedger = new PaymentLedger();
		boolean firstRecord = true;
		
		int i =0;
		
		for (JsonElement jsonElement : recordsJsonArray) {
			JsonObject records = jsonElement.getAsJsonObject();
			PaymentLedgerRecord ledgerRecord = new PaymentLedgerRecord();
			if (firstRecord && isfirstCall) {
				
				
				String funder=null;
				String starting_balance=null;
				String funderAccount =null;
				/*paymentLedger.setId(getValidJson(records, JsonKeyEnum.id.toString()));
				paymentLedger.setFunder(getValidJson(records, JsonKeyEnum.funder.toString()));
				paymentLedger.setStarting_balance(getValidJson(records, JsonKeyEnum.starting_balance.toString()));
				String account =getValidJson(records, JsonKeyEnum.account.toString());*/
				for (JsonElement jsonElementFunder : recordsJsonArrayFunder) {
					JsonObject funderRecords = jsonElementFunder.getAsJsonObject();
					funder=getValidJson(funderRecords, JsonKeyEnum.funder.toString());
					starting_balance=getValidJson(funderRecords, JsonKeyEnum.starting_balance.toString());
					funderAccount=getValidJson(funderRecords, JsonKeyEnum.account.toString());
				}
				
				
				firstRecord = false;
				/*paymentLedger.setId(getValidJson(records, JsonKeyEnum.id.toString()));
				paymentLedger.setFunder(getValidJson(records, JsonKeyEnum.funder.toString()));
				paymentLedger.setStarting_balance(getValidJson(records, JsonKeyEnum.starting_balance.toString()));*/

				paymentLedger.setId(getValidJson(records, JsonKeyEnum.id.toString()));
				paymentLedger.setFunder(funder);
				paymentLedger.setStarting_balance(starting_balance);
				
				
				
				
				String sourceAccount =getValidJson(records, JsonKeyEnum.source_account.toString());
				paymentLedger.setSource_account(sourceAccount);
			
			    //----
				/*PaymentLedger.AccoutDetails accoutDetails = paymentLedger.new AccoutDetails();
				Map<String, PaymentLedger.AccoutDetails> issuerDetails =new HashMap<>();
				AccountDetailsResponse account= clientUserInfoService.getAccountDetailsByPublicKey(sourceAccount);
				
				if(account!=null && account.getFirstName()!=null) {
					accoutDetails.setFirstName(account.getFirstName());
					accoutDetails.setLastName(account.getLastName());
					issuerDetails.put(sourceAccount, accoutDetails);
				}else if(account.getClientName()!=null) {
					accoutDetails.setClientName(account.getClientName());
					issuerDetails.put(sourceAccount, accoutDetails);
				}
				paymentLedger.setAccoutDetails(issuerDetails);*/
				//----
				
				paymentLedger.setType_i(getValidJson(records, JsonKeyEnum.type_i.toString()));
				paymentLedger.setPaging_token(getValidJson(records, JsonKeyEnum.paging_token.toString()));
				/*String account =getValidJson(records, JsonKeyEnum.account.toString());*/
				String account =funderAccount;
				paymentLedger.setAccount(account);
				
				
				
				//----account Details
				if(!"".equals(account) && account!=null && account.length()>1) {
				
		    	Map<String, PaymentLedger.AccoutDetails> accountDetails =new HashMap<>();
					PaymentLedger.AccoutDetails accoutDetails = paymentLedger.new AccoutDetails();
				AccountDetailsResponse account_details= clientUserInfoService.getAccountDetailsByPublicKey(account);
				
				if(account_details!=null) {
				if(account_details!=null && account_details.getFirstName()!=null) {
					accoutDetails.setFirstName(account_details.getFirstName());
					accoutDetails.setLastName(account_details.getLastName());
					accountDetails.put(sourceAccount, accoutDetails);
				}else if(account_details.getClientName()!=null) {
					accoutDetails.setClientName(account_details.getClientName());
					accountDetails.put(account, accoutDetails);
				}
				 }else {
					 accoutDetails.setClientName("UNKNOWN");
					 accountDetails.put(account, accoutDetails);
					
				}
				paymentLedger.setAccoutDetails(accountDetails);
				
				}
				//----
				
				
				
				paymentLedger.setCreated_at((getValidJson(records, JsonKeyEnum.created_at.toString())));
				paymentLedger.setType(getValidJson(records, JsonKeyEnum.type.toString()));
				paymentLedger.setNextLink(nextLink);
			
			//=== First Record==
				

				ledgerRecord.setId(getValidJson(records, JsonKeyEnum.id.toString()));
				String to =getValidJson(records, JsonKeyEnum.to.toString());
				
				
				ledgerRecord.setTo(to);
				// To detalis
				// Check account TO is not null or ""
				
				if(!"".equals(to) && to!=null && to.length()>1) {
					
					PaymentLedgerRecord.AccoutDetails toDetails = ledgerRecord.new AccoutDetails();
					Map<String, PaymentLedgerRecord.AccoutDetails> toDetailsMap =new HashMap<>();
					AccountDetailsResponse to_details= clientUserInfoService.getAccountDetailsByPublicKey(to);
					if(to_details!=null) {
					
						if(to_details!=null && to_details.getFirstName()!=null) {
							toDetails.setFirstName(to_details.getFirstName());
							toDetails.setLastName(to_details.getLastName());
							
							toDetailsMap.put(to, toDetails);
						}else if(to_details.getClientName()!=null) {
							
							toDetails.setClientName(to_details.getClientName());
							toDetailsMap.put(to, toDetails);
						}
						
					
					}else {
						toDetails.setMessage("unknown");
						toDetailsMap.put(to, toDetails);
						
					}
					ledgerRecord.setToDetails(toDetailsMap);
					
					
				}
				
				
				
				
				
				ledgerRecord.setPaging_token(getValidJson(records, JsonKeyEnum.paging_token.toString()));
				String from =getValidJson(records, JsonKeyEnum.from.toString());
				ledgerRecord.setFrom(from);
				// -From details
				
				if(!"".equals(from) && from!=null && from.length()>1) {
				
				PaymentLedgerRecord.AccoutDetails fromDetails = ledgerRecord.new AccoutDetails();
				Map<String, PaymentLedgerRecord.AccoutDetails> fromDetailsMap =new HashMap<>();
				AccountDetailsResponse from_details= clientUserInfoService.getAccountDetailsByPublicKey(from);
				if(from_details!=null) {
					if(from_details!=null && from_details.getFirstName()!=null) {
						fromDetails.setFirstName(from_details.getFirstName());
						fromDetails.setLastName(from_details.getLastName());
						fromDetailsMap.put(from, fromDetails);
					}else if(from_details.getClientName()!=null) {
						fromDetails.setClientName(from_details.getClientName());
						fromDetailsMap.put(from, fromDetails);
					}
					
				}else {
					fromDetails.setMessage("unknown");
					fromDetailsMap.put(from, fromDetails);
					
				}
				
				
				ledgerRecord.setFromDetails(fromDetailsMap);
				}
				
				
				ledgerRecord.setType(getValidJson(records, JsonKeyEnum.type.toString()));
				ledgerRecord.setTransaction_hash(getValidJson(records, JsonKeyEnum.transaction_hash.toString()));
				//System.out.println("SETTING AMOUNT :"+getValidJson(records, JsonKeyEnum.amount.toString()));
				ledgerRecord.setAmount(getValidJson(records, JsonKeyEnum.amount.toString()));
				
				String source =getValidJson(records, JsonKeyEnum.source_account.toString());
				ledgerRecord.setSource_account(source);
				
				// -Source Details
				
				if(!"".equals(source) && source!=null && source.length()>1) {
				
				PaymentLedgerRecord.AccoutDetails sourceDetails = ledgerRecord.new AccoutDetails();
				Map<String, PaymentLedgerRecord.AccoutDetails> sourceDetailsMap =new HashMap<>();
				AccountDetailsResponse source_details= clientUserInfoService.getAccountDetailsByPublicKey(source);
				
				if (source_details!=null) {
					if(source_details!=null && source_details.getFirstName()!=null) {
						sourceDetails.setFirstName(source_details.getFirstName());
						sourceDetails.setLastName(source_details.getLastName());
						sourceDetailsMap.put(source, sourceDetails);
					}else if(source_details.getClientName()!=null) {
						sourceDetails.setClientName(source_details.getClientName());
						sourceDetailsMap.put(source, sourceDetails);
					}
				} else {
					
					sourceDetails.setMessage("unknown");
					sourceDetailsMap.put(source, sourceDetails);

				}
				
				
				ledgerRecord.setSourceDetails(sourceDetailsMap);
				
				}
				
				ledgerRecord.setType_i(getValidJson(records, JsonKeyEnum.type_i.toString()));
				ledgerRecord.setAsset_code(getValidJson(records, JsonKeyEnum.asset_code.toString()));
				ledgerRecord.setType(getValidJson(records, JsonKeyEnum.type.toString()));
				ledgerRecord.setCreated_at(getValidJson(records, JsonKeyEnum.created_at.toString()));
				ledgerRecord.setAsset_type(getValidJson(records, JsonKeyEnum.asset_type.toString()));
				String asset_issuer = getValidJson(records, JsonKeyEnum.asset_issuer.toString());
				ledgerRecord.setAsset_issuer(asset_issuer);
				// -AssetIssure Details
				
				if(!"".equals(source) && source!=null && source.length()>1) {
				
				PaymentLedgerRecord.AccoutDetails assetIssureDetails = ledgerRecord.new AccoutDetails();
				Map<String, PaymentLedgerRecord.AccoutDetails> assetIssureDetailsMap =new HashMap<>();
				AccountDetailsResponse assetIssure_details= clientUserInfoService.getAccountDetailsByPublicKey(asset_issuer);
				if (assetIssure_details!=null) {
					if(assetIssure_details!=null && assetIssure_details.getFirstName()!=null) {
						assetIssureDetails.setFirstName(assetIssure_details.getFirstName());
						assetIssureDetails.setLastName(assetIssure_details.getLastName());
						assetIssureDetailsMap.put(asset_issuer, assetIssureDetails);
					}else if(assetIssure_details.getClientName()!=null) {
						assetIssureDetails.setClientName(assetIssure_details.getClientName());
						assetIssureDetailsMap.put(asset_issuer, assetIssureDetails);
					}
				} else {
					
					assetIssureDetails.setMessage("unknown");
					assetIssureDetailsMap.put(asset_issuer, assetIssureDetails);

				}
				
				
				ledgerRecord.setAsset_issuerDetails(assetIssureDetailsMap);
				}
				
				ledgerRecord.setLimit(getValidJson(records, JsonKeyEnum.limit.toString()));
				ledgerRecord.setTrustee(getValidJson(records, JsonKeyEnum.trustee.toString()));
				ledgerRecord.setTrustor(getValidJson(records, JsonKeyEnum.trustor.toString()));
				paymentLedger.setPaymentLedgerRecord(ledgerRecord);
				paymentLedger.setNextLink(nextLink);
				
			//====== end  First Record==	
			
			
			
			} else {
				
				ledgerRecord.setId(getValidJson(records, JsonKeyEnum.id.toString()));
				String to =getValidJson(records, JsonKeyEnum.to.toString());
				
				
				ledgerRecord.setTo(to);
				// To detalis
				// Check account TO is not null or ""
				
				if(!"".equals(to) && to!=null && to.length()>1) {
					
					PaymentLedgerRecord.AccoutDetails toDetails = ledgerRecord.new AccoutDetails();
					Map<String, PaymentLedgerRecord.AccoutDetails> toDetailsMap =new HashMap<>();
					AccountDetailsResponse to_details= clientUserInfoService.getAccountDetailsByPublicKey(to);
					if(to_details!=null) {
					
						if(to_details!=null && to_details.getFirstName()!=null) {
							toDetails.setFirstName(to_details.getFirstName());
							toDetails.setLastName(to_details.getLastName());
							
							toDetailsMap.put(to, toDetails);
						}else if(to_details.getClientName()!=null) {
							
							toDetails.setClientName(to_details.getClientName());
							toDetailsMap.put(to, toDetails);
						}
						
					
					}else {
						toDetails.setMessage("unknown");
						toDetailsMap.put(to, toDetails);
						
					}
					ledgerRecord.setToDetails(toDetailsMap);
					
					
				}
				
				
				
				
				
				ledgerRecord.setPaging_token(getValidJson(records, JsonKeyEnum.paging_token.toString()));
				String from =getValidJson(records, JsonKeyEnum.from.toString());
				ledgerRecord.setFrom(from);
				// -From details
				
				if(!"".equals(from) && from!=null && from.length()>1) {
				
				PaymentLedgerRecord.AccoutDetails fromDetails = ledgerRecord.new AccoutDetails();
				Map<String, PaymentLedgerRecord.AccoutDetails> fromDetailsMap =new HashMap<>();
				AccountDetailsResponse from_details= clientUserInfoService.getAccountDetailsByPublicKey(from);
				if(from_details!=null) {
					if(from_details!=null && from_details.getFirstName()!=null) {
						fromDetails.setFirstName(from_details.getFirstName());
						fromDetails.setLastName(from_details.getLastName());
						fromDetailsMap.put(from, fromDetails);
					}else if(from_details.getClientName()!=null) {
						fromDetails.setClientName(from_details.getClientName());
						fromDetailsMap.put(from, fromDetails);
					}
					
				}else {
					fromDetails.setMessage("unknown");
					fromDetailsMap.put(from, fromDetails);
					
				}
				
				
				ledgerRecord.setFromDetails(fromDetailsMap);
				}
				
				
				ledgerRecord.setType(getValidJson(records, JsonKeyEnum.type.toString()));
				ledgerRecord.setTransaction_hash(getValidJson(records, JsonKeyEnum.transaction_hash.toString()));
				//System.out.println("SETTING AMOUNT :"+getValidJson(records, JsonKeyEnum.amount.toString()));
				ledgerRecord.setAmount(getValidJson(records, JsonKeyEnum.amount.toString()));
				
				String source =getValidJson(records, JsonKeyEnum.source_account.toString());
				ledgerRecord.setSource_account(source);
				
				// -Source Details
				
				if(!"".equals(source) && source!=null && source.length()>1) {
				
				PaymentLedgerRecord.AccoutDetails sourceDetails = ledgerRecord.new AccoutDetails();
				Map<String, PaymentLedgerRecord.AccoutDetails> sourceDetailsMap =new HashMap<>();
				AccountDetailsResponse source_details= clientUserInfoService.getAccountDetailsByPublicKey(source);
				
				if (source_details!=null) {
					if(source_details!=null && source_details.getFirstName()!=null) {
						sourceDetails.setFirstName(source_details.getFirstName());
						sourceDetails.setLastName(source_details.getLastName());
						sourceDetailsMap.put(source, sourceDetails);
					}else if(source_details.getClientName()!=null) {
						sourceDetails.setClientName(source_details.getClientName());
						sourceDetailsMap.put(source, sourceDetails);
					}
				} else {
					
					sourceDetails.setMessage("unknown");
					sourceDetailsMap.put(source, sourceDetails);

				}
				
				
				ledgerRecord.setSourceDetails(sourceDetailsMap);
				
				}
				
				ledgerRecord.setType_i(getValidJson(records, JsonKeyEnum.type_i.toString()));
				ledgerRecord.setAsset_code(getValidJson(records, JsonKeyEnum.asset_code.toString()));
				ledgerRecord.setType(getValidJson(records, JsonKeyEnum.type.toString()));
				ledgerRecord.setCreated_at(getValidJson(records, JsonKeyEnum.created_at.toString()));
				ledgerRecord.setAsset_type(getValidJson(records, JsonKeyEnum.asset_type.toString()));
				String asset_issuer = getValidJson(records, JsonKeyEnum.asset_issuer.toString());
				ledgerRecord.setAsset_issuer(asset_issuer);
				// -AssetIssure Details
				
				if(!"".equals(source) && source!=null && source.length()>1) {
				
				PaymentLedgerRecord.AccoutDetails assetIssureDetails = ledgerRecord.new AccoutDetails();
				Map<String, PaymentLedgerRecord.AccoutDetails> assetIssureDetailsMap =new HashMap<>();
				AccountDetailsResponse assetIssure_details= clientUserInfoService.getAccountDetailsByPublicKey(asset_issuer);
				if (assetIssure_details!=null) {
					if(assetIssure_details!=null && assetIssure_details.getFirstName()!=null) {
						assetIssureDetails.setFirstName(assetIssure_details.getFirstName());
						assetIssureDetails.setLastName(assetIssure_details.getLastName());
						assetIssureDetailsMap.put(asset_issuer, assetIssureDetails);
					}else if(assetIssure_details.getClientName()!=null) {
						assetIssureDetails.setClientName(assetIssure_details.getClientName());
						assetIssureDetailsMap.put(asset_issuer, assetIssureDetails);
					}
				} else {
					
					assetIssureDetails.setMessage("unknown");
					assetIssureDetailsMap.put(asset_issuer, assetIssureDetails);

				}
				
				
				ledgerRecord.setAsset_issuerDetails(assetIssureDetailsMap);
				}
				
				ledgerRecord.setLimit(getValidJson(records, JsonKeyEnum.limit.toString()));
				ledgerRecord.setTrustee(getValidJson(records, JsonKeyEnum.trustee.toString()));
				ledgerRecord.setTrustor(getValidJson(records, JsonKeyEnum.trustor.toString()));
				paymentLedger.setPaymentLedgerRecord(ledgerRecord);
				paymentLedger.setNextLink(nextLink);
			}
		}
		return paymentLedger;
	}

	private String getValidJson(JsonObject jsonObject, String key) {
		if (!jsonObject.has(key)) {
			return "";
		} else {
			return jsonObject.get(key).getAsString();
		}
	}

	/**
	 * Parse Json and create PaymentLedgerBalance object.
	 * 
	 * @param json
	 * @return PaymentLedgerBalance
	 * @throws ParseException
	 */
	private PaymentLedgerBalance parseLedgerBalanceData(String json) throws ParseException {
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		JsonObject jsonObject = je.getAsJsonObject();

		JsonArray balancesJsonArray = jsonObject.getAsJsonArray(JsonKeyEnum.balances.toString());
		JsonArray signersJsonArray = jsonObject.getAsJsonArray(JsonKeyEnum.signers.toString());

		PaymentLedgerBalance ledgerBalance = new PaymentLedgerBalance();

		ledgerBalance.setId(jsonObject.get(JsonKeyEnum.id.toString()).getAsString());
		ledgerBalance.setPaging_token(getValidJson(jsonObject, JsonKeyEnum.paging_token.toString()));
		
		String accountId =getValidJson(jsonObject, JsonKeyEnum.account_id.toString());
		ledgerBalance.setAccount_id(accountId);
		
		if(!"".equals(accountId) && accountId!=null && accountId.length()>1) {
			
			PaymentLedgerBalance.AccoutDetails accountIdDetails = ledgerBalance.new AccoutDetails();
			Map<String, PaymentLedgerBalance.AccoutDetails> accountIdDetailsMap =new HashMap<>();
			AccountDetailsResponse accountId_details= clientUserInfoService.getAccountDetailsByPublicKey(accountId);
			if (accountId_details!=null) {
				if(accountId_details!=null && accountId_details.getFirstName()!=null) {
					accountIdDetails.setFirstName(accountId_details.getFirstName());
					accountIdDetails.setLastName(accountId_details.getLastName());
					accountIdDetailsMap.put(accountId, accountIdDetails);
				}else if(accountId_details.getClientName()!=null) {
					accountIdDetails.setClientName(accountId_details.getClientName());
					accountIdDetailsMap.put(accountId, accountIdDetails);
				}
			} else {
				
				accountIdDetails.setMessage("unknown");
				accountIdDetailsMap.put(accountId, accountIdDetails);

			}
			ledgerBalance.setAcocuntIdDetails(accountIdDetailsMap);
			}
		
		
		ledgerBalance.setSequence(getValidJson(jsonObject, JsonKeyEnum.sequence.toString()));
		ledgerBalance.setSubentry_count(getValidJson(jsonObject, JsonKeyEnum.subentry_count.toString()));

		for (JsonElement jsonElement : balancesJsonArray) {
			JsonObject records = jsonElement.getAsJsonObject();
			PaymentLedgerBalance.Balance balence = ledgerBalance.new Balance();
			String assetCode = getValidJson(records, JsonKeyEnum.asset_code.toString());
			String assetIssuer = getValidJson(records, JsonKeyEnum.asset_issuer.toString());

			if ((assetCode != null && assetCode.length() > 0) && (assetIssuer != null && assetIssuer.length() > 0)) {
				String bal = getValidJson(records, JsonKeyEnum.balance.toString());
				String limit = getValidJson(records, JsonKeyEnum.limit.toString());
				String assetType = getValidJson(records, JsonKeyEnum.asset_type.toString());
				balence.setAsset_code(assetCode);
				balence.setAsset_issuer(assetIssuer);
				
				if(!"".equals(assetIssuer) && assetIssuer!=null && assetIssuer.length()>1) {
					
					
					PaymentLedgerBalance.Balance.AccoutDetails assetIssuerDetails = balence.new AccoutDetails();
					Map<String, PaymentLedgerBalance.Balance.AccoutDetails> assetIssuerDetailsMap =new HashMap<>();
					AccountDetailsResponse assetIssuer_details= clientUserInfoService.getAccountDetailsByPublicKey(assetIssuer);
					
					if (assetIssuer_details!=null) {
						if(assetIssuer_details!=null && assetIssuer_details.getFirstName()!=null) {
							assetIssuerDetails.setFirstName(assetIssuer_details.getFirstName());
							assetIssuerDetails.setLastName(assetIssuer_details.getLastName());
							assetIssuerDetailsMap.put(accountId, assetIssuerDetails);
						}else if(assetIssuer_details.getClientName()!=null) {
							assetIssuerDetails.setClientName(assetIssuer_details.getClientName());
							assetIssuerDetailsMap.put(assetIssuer, assetIssuerDetails);
						}
					} else {
						
						assetIssuerDetails.setMessage("unknown");
						assetIssuerDetailsMap.put(assetIssuer, assetIssuerDetails);

					}
					balence.setAssetIssuerDetails(assetIssuerDetailsMap);

					
					
				}
				
				
				balence.setAsset_type(assetType);
				balence.setBalance(bal);
				balence.setLimit(limit);
				ledgerBalance.setBalance(balence);
			}
		}

		for (JsonElement jsonElement : signersJsonArray) {
			JsonObject records = jsonElement.getAsJsonObject();
			PaymentLedgerBalance.Signers signers = ledgerBalance.new Signers();
			
			
			String key=getValidJson(records, JsonKeyEnum.key.toString());
			signers.setKey(key);
			
			if(!"".equals(key) && key!=null && key.length()>1) {
				
				PaymentLedgerBalance.Signers.AccoutDetails keyDetails = signers.new AccoutDetails();
				Map<String, PaymentLedgerBalance.Signers.AccoutDetails> keyDetailsMap =new HashMap<>();
				AccountDetailsResponse key_details= clientUserInfoService.getAccountDetailsByPublicKey(key);
				
				if (key_details!=null) {
					if(key_details!=null && key_details.getFirstName()!=null) {
						keyDetails.setFirstName(key_details.getFirstName());
						keyDetails.setLastName(key_details.getLastName());
						keyDetailsMap.put(key, keyDetails);
					}else if(key_details.getClientName()!=null) {
						keyDetails.setClientName(key_details.getClientName());
						keyDetailsMap.put(key, keyDetails);
					}
				} else {
					
					keyDetails.setMessage("unknown");
					keyDetailsMap.put(key, keyDetails);

				}
				signers.setKeyDetails(keyDetailsMap);
			}
			
			
			String publicKey=getValidJson(records, JsonKeyEnum.public_key.toString());
			signers.setPublic_key(publicKey);
			
			
               if(!"".equals(publicKey) && publicKey!=null && publicKey.length()>1) {
				
				PaymentLedgerBalance.Signers.AccoutDetails publicKeyDetails = signers.new AccoutDetails();
				Map<String, PaymentLedgerBalance.Signers.AccoutDetails> publicKeyDetailsMap =new HashMap<>();
				AccountDetailsResponse publicKey_details= clientUserInfoService.getAccountDetailsByPublicKey(publicKey);
				
				if (publicKey_details!=null) {
					if(publicKey_details!=null && publicKey_details.getFirstName()!=null) {
						publicKeyDetails.setFirstName(publicKey_details.getFirstName());
						publicKeyDetails.setLastName(publicKey_details.getLastName());
						publicKeyDetailsMap.put(publicKey, publicKeyDetails);
					}else if(publicKey_details.getClientName()!=null) {
						publicKeyDetails.setClientName(publicKey_details.getClientName());
						publicKeyDetailsMap.put(publicKey, publicKeyDetails);
					}
				} else {
					
					publicKeyDetails.setMessage("unknown");
					publicKeyDetailsMap.put(publicKey, publicKeyDetails);

				}
				signers.setPublicKeyDetails(publicKeyDetailsMap);
			}
			
			signers.setType(getValidJson(records, JsonKeyEnum.type.toString()));
			signers.setWeight(getValidJson(records, JsonKeyEnum.weight.toString()));
			ledgerBalance.setSigners(signers);
		}
		return ledgerBalance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.emc.service.LedgerService#requestLedgerJson(java.lang.String)
	 */
	@Override
	public PaymentLedger requestLedgerJson(LedgerRequest ledgerRequest) {
		boolean isFirstCall=false;
		//RestTemplate restTemplate = new RestTemplate();
		// String URL = "https://horizon-testnet.stellar.org/accounts/" + accountID +
		// "/payments";
		String accountID = ledgerRequest.getPublicKey();
		String limit = ledgerRequest.getLimit();
		String nextLink = ledgerRequest.getNextLink();
		String URL;
		if (limit != null && limit.length() > 0) {
			isFirstCall=true;
			URL = "https://horizon-testnet.stellar.org/accounts/" + accountID + "/operations?cursor=&limit=" + limit
					+ "&order=desc";
		} else {
			URL = nextLink;
		}
       // System.out.println("NEXTLINK: "+URL);
		ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
		//System.out.println("DATA :"+response.getBody());
		try {
			return parsePaymentLedgerData(getFunder(accountID, "1", "asc"),response.getBody(), isFirstCall);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.emc.service.LedgerService#requestLedgerJson(java.lang.String)
	 */
	/*@Override
	public PaymentLedger requestLedgerJson(String accountID) {
		boolean isFirstCall=false;
		RestTemplate restTemplate = new RestTemplate();
		 
		String	URL = "https://horizon-testnet.stellar.org/accounts/" + accountID + "/operations?cursor=&limit=100" 
					+ "&order=asc";
	 
		ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

		try {
			//return parsePaymentLedgerData(response.getBody(), isFirstCall);
			return parsePaymentLedgerData(response.getBody());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.emc.service.LedgerService#requestPaymentBusiness(java.lang.
	 * String)
	 */
	@Override
	public PaymentLedgerBalance requestPaymentBalence(String accountID) {
		//RestTemplate restTemplate = new RestTemplate();
		String URL = "https://horizon-testnet.stellar.org/accounts/" + accountID;
		ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
		
		//System.out.println("DATA :"+response.getBody());

		try {
			return parseLedgerBalanceData(response.getBody());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	private String getFunder(String accountID,String limit,String order) {
		
	String urlForFunderData="https://horizon-testnet.stellar.org/accounts/" + accountID + "/operations?cursor=&limit=" + limit
		+ "&order="+order;
	//RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response = restTemplate.getForEntity(urlForFunderData, String.class);
		
		return response.getBody();
	}

}


