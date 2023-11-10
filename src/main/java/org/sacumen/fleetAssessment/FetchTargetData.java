package org.sacumen.fleetAssessment;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class FetchTargetData {

	JSONObject mResponse;

	public FetchTargetData() {
		mResponse = new JSONObject();
	}

	public JSONObject fetchTargetData(String url) {
		
			try{
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			HttpPost httpPost = new HttpPost(FleetConstants.TARGET_URL);
				
			JSONObject selectedObj = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONArray jsonArray2 = new JSONArray();
			jsonArray2.put(7);

			selectedObj.put("hosts", jsonArray);
			selectedObj.put("labels", jsonArray2);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("query", "172");

			jsonObject.put("selected", selectedObj);
			jsonObject.put("include_observer", true);

			String json = jsonObject.toString();

			// Set the JSON string as the request entity
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("Authorization", "Bearer " + FleetConstants.TOKEN);

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
			return null;
		}
	}
}
