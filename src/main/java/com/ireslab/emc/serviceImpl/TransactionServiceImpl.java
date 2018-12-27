package com.ireslab.emc.serviceImpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ireslab.emc.model.ApprovelRequest;
import com.ireslab.emc.model.ApprovelResponse;
import com.ireslab.emc.model.ClientUsersInfoResponse;
import com.ireslab.emc.model.LoadTokensRequest;
import com.ireslab.emc.model.LoadTokensResponse;
import com.ireslab.emc.model.TransactionLimitResponse;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.service.TransactionService;
import com.ireslab.emc.service.TransactionalApiService;
import com.ireslab.emc.util.AppStatusCodes;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;


@Service
public class TransactionServiceImpl implements TransactionService {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionalApiService transactionalApiService;
	
	 @Autowired
	 private ClientUserInfoService clientUserService;
	 
	 @Autowired
	 private  ProductConfigurationImpl productConfigurationImpl;

	@Override
	public LoadTokensResponse handleLoadTokens(LoadTokensRequest loadtokensRequest) {
		LoadTokensResponse loadTokensResponse = null;
		Date currentDate = new Date();

		// Invoke Electra Load Tokens API STELLAR_ACCOUNT_INSUFFICIENT_BALANCE
		
		ClientUsersInfoResponse  clientUsersInfoResponse =clientUserService.getClientUsersList(loadtokensRequest.getClientCorrelationId());
		if (!transactionalApiService.invokeLoadTokensAPI(loadtokensRequest)) {

			// TODO: throw proper exception
			LOG.error("Error occurred while invoking Electra load tokens API");
			
			loadTokensResponse = new LoadTokensResponse(HttpStatus.OK.value(), AppStatusCodes.STELLAR_ACCOUNT_INSUFFICIENT_BALANCE,
					"Insufficient fund to transfer.", clientUsersInfoResponse);
			
			//throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, AppStatusCodes.INTERNAL_SERVER_ERROR,
					//"Internal Server Error");
		}
		else
		{
			ClientUsersInfoResponse  clientUsersInfoAfterLoadToken =clientUserService.getClientUsersList(loadtokensRequest.getClientCorrelationId());
			loadTokensResponse = new LoadTokensResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
					"Amount loaded successfully", clientUsersInfoAfterLoadToken);
		}

		

		// Invoke Electra Account Balance API
		//String accountBalance = null; // stellarTxnManager.getAccountBalance(account.getStellarAccount().getPublicKey(),
										// false);
		//TODO see client correlationID
		
		
		
		return loadTokensResponse;
	}

	@Override
	public TransactionLimitResponse getTransactionLimitData() {
		TransactionLimitResponse transactionLimitResponse =transactionalApiService.getTransactionLimitData();
		return transactionLimitResponse;
	}

	@Override
	public ApprovelResponse ekycEkybApprovelList() {
		ApprovelResponse approvelResponse=	transactionalApiService.ekycEkybApprovelList();
		return approvelResponse;
	}

	@Override
	public ApprovelResponse ekycEkybApprovedList() {
		ApprovelResponse approvelResponse=	transactionalApiService.ekycEkybApprovedList();
		return approvelResponse;
	}

	@Override
	public ApprovelResponse approveEkycEkyb(ApprovelRequest approvelRequest) {
		ApprovelResponse approvelResponse=	transactionalApiService.approveEkycEkyb(approvelRequest);
		return approvelResponse;
	}



	@Override
	public SendxElectraResponse updateSettlementReport(SendxElectraRequest sendxElectraRequest) {
		SendxElectraResponse sendxElectraResponse=	productConfigurationImpl.updateSettlementReport(sendxElectraRequest);
		return sendxElectraResponse;
	}

	@Override
	public SendxElectraResponse getAllSettlementReports(String page, String size) {
		SendxElectraResponse sendxElectraResponse=	productConfigurationImpl.getAllSettlementReports(page,size);
		return sendxElectraResponse;
	}

}
