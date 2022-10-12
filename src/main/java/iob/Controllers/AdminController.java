package iob.Controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import iob.Boundaries.ActivityBoundary;
import iob.Boundaries.UserBoundary;
import iob.data.UserRole;
import iob.logic.AdvanceInstancesService;
import iob.logic.AdvancedActivitiesService;
import iob.logic.AdvancedUsersService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {
	private AdvancedUsersService userService;
	private AdvanceInstancesService instancesService;
	private AdvancedActivitiesService advancedActivitiesService;
	

	@Autowired
	public void setUserService(AdvancedUsersService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setInstancesService(AdvanceInstancesService instancesService) {
		this.instancesService = instancesService;
	}
	
	@Autowired
	public void SetActivitiesService(AdvancedActivitiesService advancedActivitiesService) {
		this.advancedActivitiesService = advancedActivitiesService;
	}

	@RequestMapping(
			path = "/iob/admin/users",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] exportUsers(
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {
		if(this.userService.checkRole(userDomain + "@@" + userEmail, UserRole.ADMIN)) {
			List<UserBoundary> list =
					this.userService
					.getAllUsers(size, page);
			
			return list
					.toArray(new UserBoundary[0]);			
		}else {
			throw new RuntimeException("You don't have authority to execute the function"); 
		}
	}

	@RequestMapping(
			path = "/iob/admin/activities",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ActivityBoundary[] getManyActivityBoundaries(
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail,
			@RequestParam(name="size", required = false, defaultValue = "10") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page){
		if(this.userService.checkRole(userDomain + "@@" + userEmail, UserRole.ADMIN)) {
			return this.advancedActivitiesService.getAllActivities(size, page).toArray(new ActivityBoundary[0]);			
		}else {
			throw new RuntimeException("You don't have authority to execute the function"); 
		}
	}

	@RequestMapping(
			path="/iob/admin/users",
			method = RequestMethod.DELETE)
	public void deleteAllUsers(
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail){
		if(this.userService.checkRole(userDomain + "@@" + userEmail, UserRole.ADMIN)) {
			this.userService
			.deleteAllUsers();			
		}else {
			throw new RuntimeException("You don't have authority to execute the function"); 
		}
	}

	@RequestMapping(
			path="/iob/admin/instances",
			method = RequestMethod.DELETE)
	public void deleteAllInstances(
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail){
		if(this.userService.checkRole(userDomain + "@@" + userEmail, UserRole.ADMIN)) {
			this.instancesService
			.deleteAllInstances();		
		}else {
			throw new RuntimeException("You don't have authority to execute the function"); 
		}
	}

	@RequestMapping(
			path="/iob/admin/activities",
			method = RequestMethod.DELETE)
	public void deleteAllActivities(
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail){
		if(this.userService.checkRole(userDomain + "@@" + userEmail, UserRole.ADMIN)) {
			this.advancedActivitiesService.deleteAllActivities();			
		}else {
			throw new RuntimeException("You don't have authority to execute the function"); 
		}
	}
}
