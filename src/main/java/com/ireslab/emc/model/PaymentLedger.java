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
public class PaymentLedger {
	private String id;

	private String funder;

	private String starting_balance;

	private String source_account;

	private String type_i;

	private String paging_token;
	// account detail.
	private String account;
	private Map<String, PaymentLedger.AccoutDetails> accoutDetails;
	
	public Map<String, PaymentLedger.AccoutDetails> getAccoutDetails() {
		return accoutDetails;
	}

	public void setAccoutDetails(Map<String, PaymentLedger.AccoutDetails> accoutDetails) {
		this.accoutDetails = accoutDetails;
	}

	private String created_at;

	private List<PaymentLedgerRecord> records = new ArrayList<>();

	private String type;

	private String transaction_hash;

	private PaymentLedgerBalance ledgerBalance;
	
	private String nextLink;

	public PaymentLedgerBalance getLedgerBalance() {
		return ledgerBalance;
	}

	public void setLedgerBalance(PaymentLedgerBalance ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}

	public void setRecords(List<PaymentLedgerRecord> records) {
		this.records = records;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunder() {
		return funder;
	}

	public void setFunder(String funder) {
		this.funder = funder;
	}

	public String getStarting_balance() {
		return starting_balance;
	}

	public void setStarting_balance(String starting_balance) {
		this.starting_balance = starting_balance;
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

	public String getPaging_token() {
		return paging_token;
	}

	public void setPaging_token(String paging_token) {
		this.paging_token = paging_token;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public List<PaymentLedgerRecord> getRecords() {
		return records;
	}

	public void setPaymentLedgerRecord(PaymentLedgerRecord records) {
		this.records.add(records);
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

	
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}
	
	public class AccoutDetails{
		
		private String firstName;
		private String lastName;
		private String clientName;
		
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
		return "PaymentLedger [id = " + id + ", funder = " + funder + ", starting_balance = " + starting_balance
				+ ", source_account = " + source_account + ", type_i = " + type_i + ", paging_token = " + paging_token
				+ ", account = " + account + ", created_at = " + created_at + ", records = " + records + ", type = "
				+ type + ", transaction_hash = " + transaction_hash + "]";
	}
}
