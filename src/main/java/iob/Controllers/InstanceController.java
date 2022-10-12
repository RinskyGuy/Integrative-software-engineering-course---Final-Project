package iob.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import iob.Boundaries.InstanceBoundary;
import iob.Jpas.EligibilityException;
import iob.data.UserRole;
import iob.logic.AdvanceInstancesService;
import iob.logic.AdvancedUsersService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class InstanceController {
	private AdvanceInstancesService instancesService;
	private AdvancedUsersService userService;

	@Autowired
	public InstanceController(AdvanceInstancesService instanceService, AdvancedUsersService userService) {
		super();
		this.instancesService = instanceService;
		this.userService = userService;
	}

	@RequestMapping(
			path = "/iob/instances/{instanceDomain}/{instanceId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary instanceDetails (
			@PathVariable("instanceDomain") String instanceDomain, 
			@PathVariable("instanceId") String instanceId,
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {
		return this.instancesService.checkEligibilityAndExecuteSpecific(instanceDomain, instanceId, userDomain + "@@" +userEmail, page, size);
	}

	@RequestMapping(
			path = "/iob/instances",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary[] getManyInstanceDetails(
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {
		return this.instancesService.checkEligibilityAndExecuteAll(userDomain + "@@" + userEmail, page, size);
	}


	@RequestMapping(
			path="/iob/instances",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary storeNewInstance(
			@RequestBody InstanceBoundary input) {
		if(this.userService.checkRole(input.getCreatedBy().getUserId().getDomain() + "@@" + input.getCreatedBy().getUserId().getEmail(), UserRole.MANAGER)){
			return this.instancesService.createInstance(input);			
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}


	@RequestMapping(
			path = "/iob/instances/{instanceDomain}/{instanceId}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateInstance (
			@PathVariable("instanceDomain") String instanceDomain,
			@PathVariable("instanceId") String instanceId,
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail,
			@RequestBody InstanceBoundary update) {
		this.instancesService.checkEligibilityAndExecuteUpdate(userDomain + "@@" + userEmail, update, instanceDomain, instanceId);
	}
}
