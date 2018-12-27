package com.ireslab.emc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.emc.model.GstHsnCode;
import com.ireslab.emc.model.GstHsnCodeLoadResponse;
import com.ireslab.emc.service.ClientUserInfoService;
import com.ireslab.emc.serviceImpl.UiServiceImpl;
import com.ireslab.emc.test.ExchangeDataResponse;
import com.ireslab.emc.test.ExchangeDto;

@RestController
@CrossOrigin(origins = { "*" })
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(ConsoleUiController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private UiServiceImpl uiService;
	
	

	@RequestMapping(value = "/exchangeData", method = RequestMethod.GET)
	public ResponseEntity<ExchangeDataResponse> testMethodForExchange() {
		log.info("Exchange data request recived...!");

		ExchangeDto sgd = new ExchangeDto();
		sgd.setCurrency("SGD");
		sgd.setCurrentRate("10");
		sgd.setExchangeRate("0.1");

		ExchangeDto usd = new ExchangeDto();
		usd.setCurrency("USD");
		usd.setCurrentRate("1");
		usd.setExchangeRate("1");

		ExchangeDto eur = new ExchangeDto();
		eur.setCurrency("EUR");
		eur.setCurrentRate("1");
		eur.setExchangeRate("1");

		ExchangeDto inr = new ExchangeDto();
		inr.setCurrency("INR");
		inr.setCurrentRate("1");
		inr.setExchangeRate("1");

		List<ExchangeDto> list = new ArrayList<>();
		list.add(sgd);
		list.add(usd);
		list.add(inr);
		list.add(eur);

		ExchangeDataResponse exchangeDataResponse = new ExchangeDataResponse();
		exchangeDataResponse.setExchangeDtos(list);

		return new ResponseEntity<>(exchangeDataResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void testMethod(HttpServletResponse res) throws FileNotFoundException {
		/*
		 * try { log.info("Client registration request received " +
		 * objectWriter.writeValueAsString(clientUserInfoService.getClientCredential(
		 * "Y8jAm7LzPH"))); } catch (JsonProcessingException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		//FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\ireslab\\Desktop\\Book1.xlsx"));
		List<ExchangeDto> transactionDtoList = new ArrayList<ExchangeDto>();
		transactionDtoList.add( new ExchangeDto("USD","65","10"));
		transactionDtoList.add(new ExchangeDto("SGD","25","10"));
		transactionDtoList.add(new ExchangeDto("INR","20","10"));
		transactionDtoList.add(new ExchangeDto("Dinar","90","10"));
		transactionDtoList.add(new ExchangeDto("Yen","10","10"));
		transactionDtoList.add(new ExchangeDto("Pound","70","10"));
		transactionDtoList.add(new ExchangeDto("Euro","70","10"));
		transactionDtoList.add(new ExchangeDto("Croner","36","10"));
		
		/*String header = "Currency,CurrencyRate,ExchangeRate";
		String newLine = "\n";
		
		//res.setContentType("application/octet-stream");
       // res.addHeader("Content-Type", "application/octet-stream");
        res.addHeader("Content-Disposition", "attachment; filename=Business_ledger_report.csv");
        try {
			res.getOutputStream().write(header.toString().getBytes());
			res.getOutputStream().write(newLine.toString().getBytes());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(ExchangeDto exchangeDto : transactionDtoList){
        	 try {
				res.getOutputStream().write(exchangeDto.toString().getBytes());
				res.getOutputStream().write(newLine.toString().getBytes());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		  }
        try {
			res.getOutputStream().flush();
			res.getOutputStream().close();;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
		res.addHeader("Content-Disposition", "attachment; filename=business_ledger_report.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Exchange rate");
		
		Row header = sheet.createRow(0);
		  header.createCell(0).setCellValue("SR");
		  header.createCell(1).setCellValue("Currency");
		  header.createCell(2).setCellValue("Currency Rate");
		  header.createCell(3).setCellValue("Exchange Rate");
		  
		  int rowNum = 1;
		  
		  for(ExchangeDto exchangeDto : transactionDtoList){
		   Row row = sheet.createRow(rowNum++);
		   row.createCell(0).setCellValue(rowNum);
		   row.createCell(1).setCellValue(exchangeDto.getCurrency());
		   row.createCell(2).setCellValue(exchangeDto.getCurrentRate());
		   row.createCell(3).setCellValue(exchangeDto.getExchangeRate());
		  }
		  
		  
		  try {
			workbook.write(res.getOutputStream());
			workbook.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
		 
	}
	
	@RequestMapping(value = "/testFormate", method = RequestMethod.GET)
	public void testFormate(HttpServletResponse res) throws FileNotFoundException {
		double num = 0.00;
		DecimalFormat decimalFormat = new DecimalFormat(".##");
		String numberAsString = decimalFormat.format(num);
		System.out.println(String.format("%.2f", num));

		  
		  
		 
	}
	
	@RequestMapping(value = "/readExcellFile", method = RequestMethod.GET)
	public GstHsnCodeLoadResponse readExcellFile() throws FileNotFoundException {
		String filename = "C:\\Users\\Sachin\\Desktop\\GST.xlsx";

		GstHsnCodeLoadResponse productConfigurationResponse = new GstHsnCodeLoadResponse();
		
		
		List<GstHsnCode> hsnCodeList = new ArrayList<>();
		FileInputStream fis = null;
		try {

			fis = new FileInputStream(filename);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				
				GstHsnCode gstHsnCodeDto = new GstHsnCode();
				if(String.valueOf( row.getCell(1).getStringCellValue()) != null && String.valueOf( row.getCell(1).getStringCellValue()) != "") {
				if (row.getCell(0).getCellType() == row.getCell(0).CELL_TYPE_NUMERIC) {

					gstHsnCodeDto.setHsnCode(BigDecimal.valueOf(row.getCell(0).getNumericCellValue()).intValue()+"");
				} else if (row.getCell(0).getCellType() == row.getCell(0).CELL_TYPE_STRING) {
					
					gstHsnCodeDto.setHsnCode(String.valueOf(row.getCell(0).getStringCellValue()));
				}
				
				
				if (row.getCell(1).getCellType() == row.getCell(1).CELL_TYPE_NUMERIC) {

					gstHsnCodeDto.setDescription(String.valueOf( row.getCell(1).getNumericCellValue()));
				} else if (row.getCell(1).getCellType() == row.getCell(1).CELL_TYPE_STRING) {
					gstHsnCodeDto.setDescription(String.valueOf( row.getCell(1).getStringCellValue()));

				}
				
				
				if (row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_NUMERIC) {

					gstHsnCodeDto.setCgst(row.getCell(2).getNumericCellValue()+"");
				} else if (row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_STRING) {

					gstHsnCodeDto.setCgst(row.getCell(2).getStringCellValue());
				} else {

					gstHsnCodeDto.setCgst(row.getCell(2).getRawValue());
				}
				
				
				if (row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_NUMERIC) {
					gstHsnCodeDto.setSgstUtgst(row.getCell(3).getNumericCellValue()+"");
				} else if (row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_STRING) {
					gstHsnCodeDto.setSgstUtgst(row.getCell(3).getStringCellValue());
				} else {
					gstHsnCodeDto.setSgstUtgst(row.getCell(3).getRawValue());
				}
				
				
				if (row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_NUMERIC) {
					
					gstHsnCodeDto.setIgst(row.getCell(4).getNumericCellValue()+"");
				} else if (row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_STRING) {
					
					gstHsnCodeDto.setIgst(row.getCell(4).getStringCellValue());
				}
				
				
				if (row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_NUMERIC) {
					
					//gstHsnCodeDto.setRelatedExportImportHsnCode(new BigInteger(row.getCell(5).getNumericCellValue()+"")+"");
					gstHsnCodeDto.setRelatedExportImportHsnCode(BigDecimal.valueOf(row.getCell(5).getNumericCellValue()).intValue()+"");
				} else if (row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_STRING) {
					
					gstHsnCodeDto.setRelatedExportImportHsnCode(String.valueOf( row.getCell(5).getStringCellValue()));
				}
				hsnCodeList.add(gstHsnCodeDto);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		productConfigurationResponse.setGstHsnCode(hsnCodeList);
		return productConfigurationResponse;

	}
	
	public static void main(String[] args) {
		String filename = "C:\\Users\\Sachin\\Desktop\\GST.xlsx";

		List sheetData = new ArrayList();
		FileInputStream fis = null;
		try {

			fis = new FileInputStream(filename);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				// System.out.print(" Description : "+row.getCell(0).getStringCellValue());
				// System.out.print(" Last cell num : "+row.getLastCellNum());
				// System.out.println(" physical no of cell : "+row.getPhysicalNumberOfCells());
				// int i=0;
				// for(int i=0;i<row.getPhysicalNumberOfCells();i++) {
				if (row.getCell(0).getCellType() == row.getCell(0).CELL_TYPE_NUMERIC) {
					System.out.print(" HSN Code : " + row.getCell(0).getNumericCellValue());
				} else if (row.getCell(0).getCellType() == row.getCell(0).CELL_TYPE_STRING) {
					System.out.print(" HSN Code : " + row.getCell(0).getStringCellValue());
				}
				if (row.getCell(1).getCellType() == row.getCell(1).CELL_TYPE_NUMERIC) {
					System.out.print(" Description : " + row.getCell(1).getNumericCellValue());
				} else if (row.getCell(1).getCellType() == row.getCell(1).CELL_TYPE_STRING) {
					System.out.print(" Description : " + row.getCell(1).getStringCellValue());
				}
				if (row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_NUMERIC) {
					System.out.print(" cgst : ***************");
					System.out.print(" cgst : " + row.getCell(2).getNumericCellValue());
				} else if (row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_STRING) {
					System.out.print(" cgst : ***************");
					System.out.print(" cgst : " + row.getCell(2).getStringCellValue());
				} else {
					System.out.print(" cgst : ***************");
					System.out.print(" cgst : " + row.getCell(3).getRawValue());
				}
				if (row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_NUMERIC) {
					System.out.print(" sgst : ***************");
					System.out.print(" sgst : " + row.getCell(3).getNumericCellValue());
				} else if (row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_STRING) {
					System.out.print(" sgst : ***************");
					System.out.print(" sgst : " + row.getCell(3).getStringCellValue());
				} else {
					System.out.print(" sgst : ***************");
					System.out.print(" sgst : " + row.getCell(3).getRawValue());
				}
				if (row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_NUMERIC) {
					System.out.print(" igst : " + row.getCell(4).getNumericCellValue());
				} else if (row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_STRING) {
					System.out.print(" igst : " + row.getCell(4).getStringCellValue());
				}
				if (row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_NUMERIC) {
					System.out.println(" Export import : " + row.getCell(5).getNumericCellValue() + "");
				} else if (row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_STRING) {
					System.out.println(" Export import : " + row.getCell(5).getStringCellValue());
				}

				/*
				 * System.out.print(" HSN Code : "+row.getCell(0).getRawValue());
				 * System.out.print(" Description : "+row.getCell(1).getRawValue());
				 * System.out.print(" cgst : "+row.getCell(2).getRawValue());
				 * System.out.print(" sgst : "+row.getCell(3).getRawValue());
				 * System.out.print(" igst : "+row.getCell(4).getRawValue());
				 * System.out.println(" Export import : "+row.getCell(5).getRawValue());
				 */
				// }
				// System.out.println("********");

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	
	}
	

}
