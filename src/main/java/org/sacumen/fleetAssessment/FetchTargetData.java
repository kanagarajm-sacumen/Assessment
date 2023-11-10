package org.sacumen.fleetAssessment;

import org.json.JSONObject;

public class FetchTargetData {
	
	JSONObject mResponse;

	public FetchTargetData() {
		mResponse = new JSONObject();
	}
	
	public JSONObject fetchTargetData(String url) {
		return mResponse;
	}
}
