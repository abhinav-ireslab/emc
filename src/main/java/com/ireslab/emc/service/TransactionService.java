package com.ireslab.emc.service;

import com.ireslab.emc.model.LoadTokensRequest;
import com.ireslab.emc.model.LoadTokensResponse;
import com.ireslab.emc.model.ApprovelRequest;
import com.ireslab.emc.model.ApprovelResponse;
import com.ireslab.emc.model.TransactionLimitResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;

public interface TransactionService {
	
	/**
	 * @param loadtokensRequest
	 * @return
	 */
	public LoadTokensResponse handleLoadTokens(LoadTokensRequest loadtokensRequest);

	public TransactionLimitResponse getTransactionLimitData();

	public ApprovelResponse ekycEkybApprovelList();

	public ApprovelResponse ekycEkybApprovedList();

	public ApprovelResponse approveEkycEkyb(ApprovelRequest approvelRequest);

	public SendxElectraResponse getAllSettlementReports(String page, String size);

	public SendxElectraResponse updateSettlementReport(SendxElectraRequest sendxElectraRequest);

}
