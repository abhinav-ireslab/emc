package com.ireslab.emc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.service.ProductConfiguration;
import com.ireslab.sendx.electra.model.ProductConfigurationRequest;
import com.ireslab.sendx.electra.model.ProductConfigurationResponse;

@RestController
@RequestMapping(value = "/v1/product/**")

public class ProductConfigurationController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductConfigurationController.class);
	
	@Autowired
	private ProductConfiguration productConfiguration;
	
	@Autowired
	private ObjectWriter objectWriter;
	
	/*@RequestMapping(value = "getPaymentTerm", method = RequestMethod.POST)
	public ResponseEntity<ProductResponse> getProducetList(@RequestBody ProductRequest productRequest)
			throws JsonProcessingException {
		LOG.info("Product Request received : " + objectWriter.writeValueAsString(productRequest));
		ProductResponse productResponse = commonService.getProductList(productRequest);
		LOG.info("Product Response send : " + objectWriter.writeValueAsString(productResponse));
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "getPaymentTerm", method = RequestMethod.GET)
	public ResponseEntity<ProductConfigurationResponse> getPaymentTermList()
			throws JsonProcessingException {
		LOG.info("Payment Term Request received");
		ProductConfigurationResponse productConfigurationResponse = productConfiguration.getAllPaymentTerms();
		LOG.info("Payment Term Response json  send : "+objectWriter.writeValueAsString(productConfigurationResponse));
		return new ResponseEntity<>(productConfigurationResponse, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "getChaptersList", method = RequestMethod.GET)
	public ResponseEntity<ProductConfigurationResponse> getChaptersList()
			throws JsonProcessingException {
		LOG.info("Chapters Request received");
		ProductConfigurationResponse productConfigurationResponse = productConfiguration.getChaptersLsit();
		//LOG.info("Response for chapter llist send : "+objectWriter.writeValueAsString(productConfigurationResponse));
		return new ResponseEntity<>(productConfigurationResponse, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "getAllHsnListBasedOnChapter", method = RequestMethod.POST)
	public ResponseEntity<ProductConfigurationResponse> getAllHsnListBasedOnChapter(
			@RequestBody ProductConfigurationRequest productConfigurationRequest,
			Pageable pageable,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "size", required = false) String size)
			throws JsonProcessingException {
		LOG.info("HSN list Request received");
		ProductConfigurationResponse productConfigurationResponse = productConfiguration.getAllHsnListBasedOnChapter(productConfigurationRequest.getChapterNo(),page,size);
		LOG.info("HSN list Response send");
		return new ResponseEntity<>(productConfigurationResponse, HttpStatus.OK);
	}

	
	@RequestMapping(value = "getAllSacListBasedOnSearch", method = RequestMethod.POST)
	public ResponseEntity<ProductConfigurationResponse> getAllSacListBasedOnSearch(
			@RequestBody ProductConfigurationRequest productConfigurationRequest,
			Pageable pageable,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "size", required = false) String size)
			throws JsonProcessingException {
		LOG.info("SAC list Request received");
		ProductConfigurationResponse productConfigurationResponse = productConfiguration.getAllSacListBasedOnSearch(productConfigurationRequest.getSerarchData(),page,size);
		LOG.info("SAC list Response send");
		return new ResponseEntity<>(productConfigurationResponse, HttpStatus.OK);
	}
	

}
