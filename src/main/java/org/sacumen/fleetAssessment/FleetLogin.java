package org.sacumen.fleetAssessment;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class FleetLogin {

	private static final Logger logger = Logger.getLogger(FleetLogin.class.getName());

	private JSONObject mResponse;

	public FleetLogin() {
		mResponse = new JSONObject();
	}

	public JSONObject login(String url, String email, String password) {
		try {

			logger.info("Login Started");
			logger.info("URL "+url);
			logger.info("Email "+email);
			logger.info("Password "+password);

			CloseableHttpClient httpClient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(url);

			// Request parameters and other properties.
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", email);
			jsonObject.put("password", password);

			String json = jsonObject.toString();

			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			// Execute and get the response.
			CloseableHttpResponse response = httpClient.execute(httpPost);

			logger.info("Status Code		-> " + response.getStatusLine());

			HttpEntity responseEntity = response.getEntity();

			if (responseEntity != null) {
				mResponse = new JSONObject(EntityUtils.toString(responseEntity));
			}

			logger.info("End of Login");

			return mResponse;

		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage());
			logger.log(Level.WARNING, e.getCause().toString());
			logger.log(Level.WARNING, e.getStackTrace().toString());
			return null;
		}
	}
}
