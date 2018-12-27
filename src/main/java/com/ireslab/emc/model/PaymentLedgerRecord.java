/**
 * 
 */
package com.ireslab.emc.model;

import java.util.Map;

/**
 * @author Akhilesh
 *
 */
public class PaymentLedgerRecord {
	private String to;
	private Map<String, PaymentLedgerRecord.AccoutDetails> toDetails;
	private String paging_token;
	private String from;
	private Map<String, PaymentLedgerRecord.AccoutDetails> fromDetails;
	private String type;
	private String transaction_hash;
	private String amount;
	private String id;
	private String source_account;
	private Map<String, PaymentLedgerRecord.AccoutDetails> sourceDetails;
	private String type_i;
	private String asset_code;
	private String asset_type;
	private String created_at;
	private String asset_issuer;
	private Map<String, PaymentLedgerRecord.AccoutDetails> asset_issuerDetails;
	private String trustee;
	private String trustor;
	private String limit;
	
	

	public Map<String, PaymentLedgerRecord.AccoutDetails> getToDetails() {
		return toDetails;
	}

	public void setToDetails(Map<String, PaymentLedgerRecord.AccoutDetails> toDetails) {
		this.toDetails = toDetails;
	}

	public Map<String, PaymentLedgerRecord.AccoutDetails> getFromDetails() {
		return fromDetails;
	}

	public void setFromDetails(Map<String, PaymentLedgerRecord.AccoutDetails> fromDetails) {
		this.fromDetails = fromDetails;
	}

	public Map<String, PaymentLedgerRecord.AccoutDetails> getSourceDetails() {
		return sourceDetails;
	}

	public void setSourceDetails(Map<String, PaymentLedgerRecord.AccoutDetails> sourceDetails) {
		this.sourceDetails = sourceDetails;
	}

	public Map<String, PaymentLedgerRecord.AccoutDetails> getAsset_issuerDetails() {
		return asset_issuerDetails;
	}

	public void setAsset_issuerDetails(Map<String, PaymentLedgerRecord.AccoutDetails> asset_issuerDetails) {
		this.asset_issuerDetails = asset_issuerDetails;
	}

	public String getTrustee() {
		return trustee;
	}

	public void setTrustee(String trustee) {
		this.trustee = trustee;
	}

	public String getTrustor() {
		return trustor;
	}

	public void setTrustor(String trustor) {
		this.trustor = trustor;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPaging_token() {
		return paging_token;
	}

	public void setPaging_token(String paging_token) {
		this.paging_token = paging_token;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransaction_hash() {
		return transaction_hash;
	}

	public void setTransaction_hash(String transaction_hash) {
		this.transaction_hash = transaction_hash;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource_account() {
		return source_account;
	}

	public void setSource_account(String source_account) {
		this.source_account = source_account;
	}

	public String getType_i() {
		return type_i;
	}

	public void setType_i(String type_i) {
		this.type_i = type_i;
	}

	public String getAsset_code() {
		return asset_code;
	}

	public void setAsset_code(String asset_code) {
		this.asset_code = asset_code;
	}

	public String getAsset_type() {
		return asset_type;
	}

	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getAsset_issuer() {
		return asset_issuer;
	}

	public void setAsset_issuer(String asset_issuer) {
		this.asset_issuer = asset_issuer;
	}
	
	
	public class AccoutDetails{
		
		private String firstName;
		private String lastName;
		private String clientName;
		private String message;
		
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getClientName() {
			return clientName;
		}
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		
	}


	@Override
	public String toString() {
		return "PaymentLedgerRecord [to = " + to + ", paging_token = " + paging_token + ", from = " + from + ", type = "
				+ type + ", transaction_hash = " + transaction_hash + ", amount = " + amount + ", id = " + id
				+ ", source_account = " + source_account + ", type_i = " + type_i + ", asset_code = " + asset_code
				+ ", asset_type = " + asset_type + ", created_at = " + created_at + ", asset_issuer = " + asset_issuer
				+ "]";
	}
}
