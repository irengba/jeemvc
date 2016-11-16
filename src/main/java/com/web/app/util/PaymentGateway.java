package com.web.app.util;

import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

import com.paytm.merchant.CheckSumServiceHelper;

public class PaymentGateway {

	public String generateChecksum(TreeMap<String, String> parameters) throws Exception {
		CheckSumServiceHelper checkSumServiceHelper = CheckSumServiceHelper.getCheckSumServiceHelper();

		//Note: Above mentioned parameters are not complete list of parameters. Please refer integration document for additional parameters which need to be passed.
		String checkSum = checkSumServiceHelper.genrateCheckSum(this.MERCHANT_KEY, parameters);
		return checkSum;
	}

	public boolean validateChecksum(HttpServletRequest request) throws Exception {
		com.paytm.merchant.CheckSumServiceHelper checkSumServiceHelper = com.paytm.merchant.CheckSumServiceHelper
				.getCheckSumServiceHelper();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		String paytmChecksum = "xxxxxxxxxxxx"; // Sent by Paytm pg
		boolean isValidChecksum = false;
		paytmChecksum = request.getParameter("CHECKSUMHASH");

		parameters.put("MID", this.MID); // Merchant ID (MID) sent by Paytm pg
		parameters.put("TXNID", ""); // Transaction id sent by Paytm pg
		parameters.put("ORDER_ID", "nnnnnnnnn"); // Merchant’s order id
		parameters.put("BANKTXNID", "xxxxxxxxxxxx");  // Bank TXN id sent by Paytm pg
		parameters.put("TXN_AMOUNT", "1");
		parameters.put("STATUS", " TXN_FAILURE"); //sent by Paytm pg
		parameters.put("RESPCODE", "xxxxxxxxxxx"); //sent by Paytm pg
		//Note: Above mentioned parameters are not the complete list of parameters. Please refer integration document for additional parameters which need to be passed.
		isValidChecksum = checkSumServiceHelper.verifycheckSum(this.MERCHANT_KEY, parameters, paytmChecksum);
		return isValidChecksum;
	}

	public final static String MID = "wakaup68222368899510";

	public final static String MERCHANT_KEY = "Tu5RwsYqCqEau3B7";

	public final static String INDUSTRY_TYPE_ID = "Retail";

	public final static String CHANNEL_ID = "WEB";

	public final static String WEBSITE = "localhost";

	public final static String PAYTM_URL = "https://pguat.paytm.com/oltp-web/processTransaction";
}
