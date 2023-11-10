package org.sacumen.fleetAssessment;

import org.junit.jupiter.api.Test;

public class FleetLoginTest {
	@Test
	public static void loginTest() {
		String url = "http://10.10.17.162:1337/api/latest/fleet/login";
		String email = "admin@example.com";
		String password = "preview1337#";
		FleetLogin fleetLogin = new FleetLogin();
		fleetLogin.login(url, email, password);
	}
}
