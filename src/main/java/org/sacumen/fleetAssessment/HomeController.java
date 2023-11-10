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
		if(FleetConstants.TOKEN.equals("default"))login();
		JSONObject response = new FetchAllData().fetchAllData(FleetConstants.HOST_URL);
		return response.toString();
	}

	@GET
	@Path("/software")
	@Produces(MediaType.APPLICATION_JSON)
	public String softwareList() {
		if(FleetConstants.TOKEN.equals("default"))login();
		JSONObject response = new FetchAllData().fetchAllData(FleetConstants.SOFTWARE_URL);
		return response.toString();
	}

	@POST
	@Path("/target")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String targetList() {
		if(FleetConstants.TOKEN.equals("default"))login();
		JSONObject jsonObject = new FetchTargetData().fetchTargetData(FleetConstants.TARGET_URL);
		return jsonObject.toString();	
	}
}
