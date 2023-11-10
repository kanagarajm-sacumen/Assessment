package org.sacumen.fleetAssessment;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FleetLogin {
	
	private JSONObject mResponse;
	
	public FleetLogin() {
		mResponse = new JSONObject();
	}
	
	public JSONObject login() {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			
			HttpPost httpPost = new HttpPost(FleetConstants.LOGIN_URL);

			// Request parameters and other properties.
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", FleetConstants.EMAIL_ID);
			jsonObject.put("password", FleetConstants.PASSWORD);
			
			String json = jsonObject.toString();
			
			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// Execute and get the response.
			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {

				HttpEntity responseEntity = response.getEntity();

				if (responseEntity != null) {

					// Read the content of the response
					mResponse = new JSONObject(EntityUtils.toString(responseEntity));

				}
				return mResponse;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
