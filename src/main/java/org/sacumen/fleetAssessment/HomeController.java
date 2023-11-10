package org.sacumen.fleetAssessment;

import org.json.JSONObject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("api")
public class HomeController {

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login() {
		JSONObject jsonObject = new FleetLogin().login();
		FleetConstants.TOKEN = jsonObject.getString("token");
		return jsonObject.toString();
	}

	@GET
	@Path("/host")
	@Produces(MediaType.APPLICATION_JSON)
	public String hostList() {
		return null;
	}

	@GET
	@Path("/software")
	@Produces(MediaType.APPLICATION_JSON)
	public String softwareList() {
		return null;
	}

	@POST
	@Path("/target")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String targetList() {
		return null;
	}
}
