package org.sacumen.fleetAssessment;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FetchAllData {

	private static final Logger logger = Logger.getLogger(FetchAllData.class.getName());

	JSONObject mResponse;

	public FetchAllData() {
		mResponse = new JSONObject();
	}

	public JSONObject fetchAllData(String url, String token) {
		try {
			logger.info("Fetching Data Started");

			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpGet httpGet = new HttpGet(url);

			// Request parameters and other properties.
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			httpGet.setHeader("Authorization", "Bearer " + token);

			// Execute and get the response.
			CloseableHttpResponse response = httpClient.execute(httpGet);

			logger.info("Status Code		-> " + response.getStatusLine());

			HttpEntity responseEntity = response.getEntity();

			if (responseEntity != null) {
				// Read the content of the response
				mResponse = new JSONObject(EntityUtils.toString(responseEntity));
			}

			logger.info("End of Fetching Data");

			return mResponse;

		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
			logger.log(Level.WARNING, e.getCause().toString());
			logger.log(Level.WARNING, e.getStackTrace().toString());
			return null;
		}
	}
}
