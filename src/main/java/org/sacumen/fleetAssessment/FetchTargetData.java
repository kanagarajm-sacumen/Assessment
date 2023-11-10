package org.sacumen.fleetAssessment;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private static final Logger logger = Logger.getLogger(FetchTargetData.class.getName());

	JSONObject mResponse;

	public FetchTargetData() {
		mResponse = new JSONObject();
	}

	public JSONObject fetchTargetData(String url , String token) {

		try {

			logger.info("Fetch Target Data Started");

			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(url);

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
			httpPost.setHeader("Authorization", "Bearer " + token);

			// Execute and get the response.
			CloseableHttpResponse response = httpClient.execute(httpPost);

			logger.info("Status Code		-> " + response.getStatusLine());
			
			HttpEntity responseEntity = response.getEntity();

			if (responseEntity != null) {
				// Read the content of the response
				mResponse = new JSONObject(EntityUtils.toString(responseEntity));
			}
			
			logger.info("End of Fetch Target Data");
			return mResponse;
			
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
			logger.log(Level.WARNING, e.getCause().toString());
			logger.log(Level.WARNING, e.getStackTrace().toString());
			return null;
		}
	}
}
