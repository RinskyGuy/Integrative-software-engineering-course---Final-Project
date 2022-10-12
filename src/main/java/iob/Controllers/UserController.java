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
import iob.Boundaries.NewUserBoundary;
import iob.Boundaries.UserBoundary;
import iob.Converters.UserConverter;
import iob.logic.UsersService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	private UsersService usersService;
	private UserConverter userConverter;
	
	
	
	@Autowired // get interface implementation and wire it to this controller
	public UserController(UsersService userService, UserConverter userConverter) {
		super();
		this.usersService = userService;
		this.userConverter = userConverter;
	}

	@RequestMapping(
		path = "/iob/users/login/{userDomain}/{userEmail}",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary loginUserDetails (
			@PathVariable("userDomain") String userDomainF, 
			@PathVariable("userEmail") String userEmailF, 
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail) {
		return this.usersService.login(userDomain, userEmail);
	}
	
	@RequestMapping(
			path="/iob/users",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createUser(
			@RequestBody NewUserBoundary input) {
		return this.usersService.createUser(this.userConverter.toUserBoundary(input));
}
	
	@RequestMapping(
			path = "/iob/users/{userDomain}/{userEmail}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser (
			@PathVariable("userDomain") String userDomainF, 
			@PathVariable("userEmail") String userEmailF,
			@RequestParam(name="userDomain", required=true) String userDomain,
			@RequestParam(name="userEmail", required=true) String userEmail,
			@RequestBody UserBoundary update) {
		if (update == null) {
			throw new RuntimeException("update must not be null");
		}else {
			// check if user is existed
			this.usersService.login(userDomain, userEmail);
			// updating user
			this.usersService.updateUser(userDomain, userEmail, update);
		}
	}
}

