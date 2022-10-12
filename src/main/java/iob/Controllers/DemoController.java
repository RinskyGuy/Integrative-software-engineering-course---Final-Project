package iob.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
//package demo;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import iob.Boundaries.UserBoundary;
import iob.logic.UsersService;

@RestController
public class DemoController {
	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
//	@RequestMapping(
//		path = "/hello",
//		method = RequestMethod.GET,
//		produces = MediaType.APPLICATION_JSON_VALUE)
//	public UserBoundary hello () {
//		// MOCKUP implementation
//		UserBoundary message = new UserBoundary("Hello World");
//		
//		message.setImportant(true);
//		message.setMessageTimestamp(new Date());
//		message.setName(new NameBoundary("Jane", "Smith", new String[] {"JS", "Jannie"}));
//		
//		message.setComplex(new ComplexBoundary(9.0F, 42, new NameBoundary("Jack", "James", new String[] {})));
//		
//		Map<String, Object> details = new HashMap<>();
//		details.put("x", "string");
//		details.put("y", 12);
//		details.put("z", true);
//		details.put("z1", false);
//		details.put("t", new NameBoundary("Jill", "James", new String[] {"JJ"}));
//		message.setDetails(details);
//		
//		message.setStatus(InterestEnum.unknown);
//		return message;
//	}
	
//	@RequestMapping(
//			path = "/hello/{id}",
//			method = RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public UserBoundary getUsersById(
//			@PathVariable("id") String id) {
//		
//		// get user by id from database
//		return this.usersService.getUserById(id);
//	}

}
