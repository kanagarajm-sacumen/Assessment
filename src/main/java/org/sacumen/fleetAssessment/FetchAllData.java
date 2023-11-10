package org.sacumen.fleetAssessment;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FetchAllData {
	JSONObject mResponse;

	public FetchAllData() {
		mResponse = new JSONObject();
	}

	public JSONObject fetchAllData(String url) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			HttpGet httpGet = new HttpGet(url);

			// Request parameters and other properties.
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			httpGet.setHeader("Authorization", "Bearer " + FleetConstants.TOKEN);

			// Execute and get the response.
			CloseableHttpResponse response = httpClient.execute(httpGet);

				HttpEntity responseEntity = response.getEntity();

				if (responseEntity != null) {
					// Read the content of the response
					mResponse = new JSONObject(EntityUtils.toString(responseEntity));
				}
				return mResponse;
		} catch (IOException e) {
			return null;
		}	
	}
}
