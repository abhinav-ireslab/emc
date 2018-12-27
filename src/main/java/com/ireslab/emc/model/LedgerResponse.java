package com.ireslab.emc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LedgerResponse extends GenericResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<LedgerResponse.Assets> assets = new ArrayList<LedgerResponse.Assets>();
	List<LedgerResponse.Payment> payment = new ArrayList<LedgerResponse.Payment>();
	
	private String next;
	
	private String previous;
	
	
	
	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<LedgerResponse.Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<LedgerResponse.Payment> payment) {
		this.payment = payment;
	}

	public List<LedgerResponse.Assets> getAssets() {
		return assets;
	}

    public void setAssets(List<LedgerResponse.Assets> assets) {
		this.assets = assets;
	}





	public class Assets {
		
		private String code;
		private String limit;
		private String issuer;
		private String balance;
		private Map<String, Assets.AccoutDetails> issuerDetails;
		
		
		
		public Map<String, Assets.AccoutDetails> getIssuerDetails() {
			return issuerDetails;
		}
		public void setIssuerDetails(Map<String, Assets.AccoutDetails> issuerDetails) {
			this.issuerDetails = issuerDetails;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getLimit() {
			return limit;
		}
		public void setLimit(String limit) {
			this.limit = limit;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public String getBalance() {
			return balance;
		}
		public void setBalance(String balance) {
			this.balance = balance;
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
	
	public class Payment{
		private String transactionNo;
		private String id;
		private String pagingToken;
		private String sourceAccount;
		private String type;
		private String createdAt;
		private String transactionHash;
		private String assetCode;
		private String assetIssuer;
		private String from;
		private String to;
		private String amount;
		private Map<String, Payment.AccoutDetails> toDetails;
		private Map<String, Payment.AccoutDetails> fromDetails;
		
				
		
		
		public String getTransactionNo() {
			return transactionNo;
		}
		public void setTransactionNo(String transactionNo) {
			this.transactionNo = transactionNo;
		}
		public Map<String, Payment.AccoutDetails> getToDetails() {
			return toDetails;
		}
		public void setToDetails(Map<String, Payment.AccoutDetails> toDetails) {
			this.toDetails = toDetails;
		}
		public Map<String, Payment.AccoutDetails> getFromDetails() {
			return fromDetails;
		}
		public void setFromDetails(Map<String, Payment.AccoutDetails> fromDetails) {
			this.fromDetails = fromDetails;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPagingToken() {
			return pagingToken;
		}
		public void setPagingToken(String pagingToken) {
			this.pagingToken = pagingToken;
		}
		public String getSourceAccount() {
			return sourceAccount;
		}
		public void setSourceAccount(String sourceAccount) {
			this.sourceAccount = sourceAccount;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		public String getTransactionHash() {
			return transactionHash;
		}
		public void setTransactionHash(String transactionHash) {
			this.transactionHash = transactionHash;
		}
		public String getAssetCode() {
			return assetCode;
		}
		public void setAssetCode(String assetCode) {
			this.assetCode = assetCode;
		}
		public String getAssetIssuer() {
			return assetIssuer;
		}
		public void setAssetIssuer(String assetIssuer) {
			this.assetIssuer = assetIssuer;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
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
	
	

}
