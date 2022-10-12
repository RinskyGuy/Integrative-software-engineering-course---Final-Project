package iob.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import iob.Boundaries.ActivityBoundary;
import iob.Jpas.EligibilityException;
import iob.data.UserRole;
import iob.logic.AdvancedActivitiesService;
import iob.logic.AdvancedUsersService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ActivityController {
	private AdvancedActivitiesService advancedActivitiesService;
	private AdvancedUsersService advancedUsersService;
	
	@Autowired
	public ActivityController(AdvancedActivitiesService advancedActivitiesService, AdvancedUsersService advancedUsersService) {
		super();
		this.advancedActivitiesService = advancedActivitiesService;
		this.advancedUsersService = advancedUsersService;
	}
	@RequestMapping(
			path="/iob/activities",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object storeNewActivity(
			@RequestBody ActivityBoundary input) {
		if(this.advancedUsersService.checkRole(input.getInvokedBy().getUserId().getDomain() + "@@" + input.getInvokedBy().getUserId().getEmail(), UserRole.PLAYER)) {
			return this.advancedActivitiesService.invokeActivity(input);			
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}
}
