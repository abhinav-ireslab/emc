/**
 * 
 */
package com.ireslab.emc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Akhilesh
 *
 */
public class PaymentLedgerBalance {
	String id;
	Map<String, PaymentLedgerBalance.AccoutDetails> iD;
	
	String paging_token;
	String account_id;
	
	Map<String, PaymentLedgerBalance.AccoutDetails> acocuntIdDetails;
	
	String sequence;
	String subentry_count;
	List<PaymentLedgerBalance.Balance> balances = new ArrayList<PaymentLedgerBalance.Balance>();
	List<PaymentLedgerBalance.Signers> signers = new ArrayList<PaymentLedgerBalance.Signers>();
	
	
	

	public Map<String, PaymentLedgerBalance.AccoutDetails> getAcocuntIdDetails() {
		return acocuntIdDetails;
	}

	public void setAcocuntIdDetails(Map<String, PaymentLedgerBalance.AccoutDetails> acocuntIdDetails) {
		this.acocuntIdDetails = acocuntIdDetails;
	}

	public Map<String, PaymentLedgerBalance.AccoutDetails> getiD() {
		return iD;
	}

	public void setiD(Map<String, PaymentLedgerBalance.AccoutDetails> iD) {
		this.iD = iD;
	}

	public void setPaymentLedgerBalanceSigners(List<PaymentLedgerBalance.Signers> signers) {
		this.signers = signers;
	}

	public List<PaymentLedgerBalance.Signers> getSigners() {
		return signers;
	}

	public void setSigners(Signers signers) {
		if (this.signers == null) {
			this.signers = new ArrayList<PaymentLedgerBalance.Signers>();
		}
		addSigners(signers);
	}

	public void setBalances(List<PaymentLedgerBalance.Balance> balances) {
		this.balances = balances;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaging_token() {
		return paging_token;
	}

	public void setPaging_token(String paging_token) {
		this.paging_token = paging_token;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSubentry_count() {
		return subentry_count;
	}

	public void setSubentry_count(String subentry_count) {
		this.subentry_count = subentry_count;
	}

	public List<Balance> getBalences() {
		return balances;
	}

	public void setBalance(Balance balence) {
		if (this.balances == null) {
			this.balances = new ArrayList<PaymentLedgerBalance.Balance>();
		}
		addBalence(balence);
	}

	private void addBalence(Balance balence) {
		this.balances.add(balence);
	}

	private void addSigners(Signers balence) {
		this.signers.add(balence);
	}

	public class Balance {
		String balance;
		String limit;
		String asset_type;
		String asset_code;
		String asset_issuer;
		Map<String, Balance.AccoutDetails> assetIssuerDetails;
		
		public Map<String, Balance.AccoutDetails> getAssetIssuerDetails() {
			return assetIssuerDetails;
		}

		public void setAssetIssuerDetails(Map<String, Balance.AccoutDetails> assetIssuerDetails) {
			this.assetIssuerDetails = assetIssuerDetails;
		}

		public String getBalance() {
			return balance;
		}

		public void setBalance(String balance) {
			this.balance = balance;
		}

		public String getLimit() {
			return limit;
		}

		public void setLimit(String limit) {
			this.limit = limit;
		}

		public String getAsset_type() {
			return asset_type;
		}

		public void setAsset_type(String asset_type) {
			this.asset_type = asset_type;
		}

		public String getAsset_code() {
			return asset_code;
		}

		public void setAsset_code(String asset_code) {
			this.asset_code = asset_code;
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
	}

	public class Signers {
		String public_key;
		Map<String, Signers.AccoutDetails> publicKeyDetails;
		String weight;
		String key;
		Map<String, Signers.AccoutDetails> KeyDetails;
		String type;

		
		
		public Map<String, Signers.AccoutDetails> getPublicKeyDetails() {
			return publicKeyDetails;
		}

		public void setPublicKeyDetails(Map<String, Signers.AccoutDetails> publicKeyDetails) {
			this.publicKeyDetails = publicKeyDetails;
		}

		public Map<String, Signers.AccoutDetails> getKeyDetails() {
			return KeyDetails;
		}

		public void setKeyDetails(Map<String, Signers.AccoutDetails> keyDetails) {
			KeyDetails = keyDetails;
		}

		public String getPublic_key() {
			return public_key;
		}

		public void setPublic_key(String public_key) {
			this.public_key = public_key;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
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
	
}
