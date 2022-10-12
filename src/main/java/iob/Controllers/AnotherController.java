package iob.Controllers;
//package demo;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
////@RestController
////public class AnotherController {
////	@RequestMapping(
////		method=RequestMethod.GET,
////		path="/hello/{firstName}/{lastName}",
////		produces = MediaType.APPLICATION_JSON_VALUE)
////	public UserBoundary greet (
////			@PathVariable("firstName") String firstName, 
////			@PathVariable("lastName") String lastName) {
////		UserBoundary rv = new UserBoundary("Hello");
////		rv.setName(new NameBoundary(firstName, lastName, new String[] {}));
////				
////		return rv;
////	}
//	
//	@RequestMapping(
//			method = RequestMethod.GET,
//			path = "/many",
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public UserBoundary[] getManyMessages () {
////		return new MessageBoundary[] {
////			new MessageBoundary("hello"),
////			new MessageBoundary("RESTful"),
////			new MessageBoundary("world")
////		};
//		List<UserBoundary> many = new ArrayList<>();
//		
//		many.add(new UserBoundary("hello"));
//		many.add(new UserBoundary("RESTful"));
////		many.add(new MessageBoundary("world"));
//		
//		float f = 9.1F;
//		int i = 99;
//		Random random = new Random(System.currentTimeMillis());
//		for (UserBoundary msg : many) {
//			msg.setComplex(new ComplexBoundary(f++, i--, new NameBoundary("x" + i, "dummy", new String[] {})));
//			msg.setMessageTimestamp(new Date());
//			msg.setImportant(random.nextBoolean());
//			msg.setDetails(Collections.singletonMap("dummy", i));
//			msg.setName(new NameBoundary("Jill", "James", null));
//			msg.setStatus(InterestEnum.values()[random.nextInt(InterestEnum.values().length)]);
//		}
//		
//		return many
//			.toArray(new UserBoundary[0]);
//		
//	}
//	
//	
////	@RequestMapping(path="/hello/{id}",
////		method = RequestMethod.POST,
////		consumes = MediaType.APPLICATION_JSON_VALUE,
////		produces = MediaType.APPLICATION_JSON_VALUE)
////	public MessageBoundary storeMessage(
////			@PathVariable("id") Long id,
////			@RequestBody MessageBoundary input) {
//////		Random random = new Random(System.currentTimeMillis());
//////		input.setId(random.nextLong());
////		input.setId(id);
////		return input;
////	}
//	
//	@RequestMapping(path="/hello",
//			method = RequestMethod.POST,
//			consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public UserBoundary storeMessage(
//			@RequestBody UserBoundary input) {
//		Random random = new Random(System.currentTimeMillis());
//		input.setId(random.nextLong());
//		return input;
//	}
//	
//	@RequestMapping(path = "/hello/{id}",
//			method = RequestMethod.PUT,
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void updateMessage (
//		@PathVariable("id") Long id, 
//		@RequestBody UserBoundary update) {
//		System.err.println(update);
//		// do nothing
//
//		// do minimal validation
////		if (update == null) {
////			throw new RuntimeException("update must not be null");
////		}else {
//			// do nothing
////		}
//	}
//	
//	@RequestMapping(path="/hello",
//		method = RequestMethod.DELETE)
//	public void deleteAllMessage() {
//		// do nothing
//	}
//	
//}
//
//
//
//
//
