package org.sacumen.fleetAssessment;

import org.junit.jupiter.api.Test;

public class FetchAllDataTest {
	@Test
	public static void fetchAllDataTest() {
		String hostUrl = "http://10.10.17.162:1337/api/v1/fleet/hosts";
		String softwareUrl = "http://10.10.17.162:1337/api/v1/fleet/software";
		String token = " ";
		FetchAllData fetchAllData = new FetchAllData();
		fetchAllData.fetchAllData(hostUrl, token);
		fetchAllData.fetchAllData(softwareUrl, token);
	}
}
