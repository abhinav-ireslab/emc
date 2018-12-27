package com.ireslab.emc.service;

import com.ireslab.emc.model.LoadTokensRequest;
import com.ireslab.emc.model.ApprovelRequest;
import com.ireslab.emc.model.ApprovelResponse;
import com.ireslab.emc.model.TransactionLimitResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;

public interface TransactionalApiService {
	public boolean invokeLoadTokensAPI(LoadTokensRequest loadTokensRequest);

	public TransactionLimitResponse getTransactionLimitData();

	public ApprovelResponse ekycEkybApprovelList();

	public ApprovelResponse ekycEkybApprovedList();

	public ApprovelResponse approveEkycEkyb(ApprovelRequest approvelRequest);

	

	

}
