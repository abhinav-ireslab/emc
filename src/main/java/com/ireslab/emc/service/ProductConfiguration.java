package com.ireslab.emc.service;

import com.ireslab.sendx.electra.model.GstHsnSacLoadRequest;
import com.ireslab.sendx.electra.model.ProductConfigurationResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;

public interface ProductConfiguration {

	public ProductConfigurationResponse getAllPaymentTerms();

	public ProductConfigurationResponse getChaptersLsit();

	public ProductConfigurationResponse getAllHsnListBasedOnChapter(String chapterNo, String page, String size);

	public ProductConfigurationResponse getAllSacListBasedOnSearch(String string,String page, String size);
	public ProductConfigurationResponse ExcelDataLoad(GstHsnSacLoadRequest gstHsnSacLoadRequest);


	SendxElectraResponse updateSettlementReport(SendxElectraRequest sendxElectraRequest);

	SendxElectraResponse getAllSettlementReports(String page, String size);


}
