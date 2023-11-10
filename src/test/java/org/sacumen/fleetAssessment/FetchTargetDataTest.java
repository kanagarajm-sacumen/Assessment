package org.sacumen.fleetAssessment;

import org.junit.jupiter.api.Test;

public class FetchTargetDataTest {
	@Test
	public static void fetchTargetDataTest() {
		String url = "http://10.10.17.162:1337/api/v1/fleet/targets";
		String token = "";
		FetchTargetData fetchTargetData = new FetchTargetData();
		fetchTargetData.fetchTargetData(url, token);
	}
}
