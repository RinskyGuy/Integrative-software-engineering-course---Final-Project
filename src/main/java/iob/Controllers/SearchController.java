package iob.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iob.Boundaries.InstanceBoundary;
import iob.logic.AdvanceInstancesService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SearchController {
	private AdvanceInstancesService instanceService;

	@Autowired
	public SearchController(AdvanceInstancesService instanceService) {
		super();
		this.instanceService = instanceService;
	}
	
	
	@RequestMapping(
			path="/iob/instances/search/byName/{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public InstanceBoundary[] searchByNameAttribute (
				@PathVariable("name") String name, 
				@RequestParam(name="userDomain", required=true) String userDomain,
				@RequestParam(name="userEmail", required=true) String userEmail,
				@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
				@RequestParam(name="size", required = false, defaultValue = "10") int size) {
			return this.instanceService
				.checkEligibilityAndExecuteAllByName(name, userDomain + "@@" + userEmail, page, size);
		}
	
	@RequestMapping(
			path="/iob/instances/search/byType/{type}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public InstanceBoundary[] searchByTypeAttribute (
				@PathVariable("type") String type, 
				@RequestParam(name="userDomain", required=true) String userDomain,
				@RequestParam(name="userEmail", required=true) String userEmail,
				@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
				@RequestParam(name="size", required = false, defaultValue = "10") int size) {
			return this.instanceService
				.checkEligibilityAndExecuteAllByType(type, userDomain + "@@" + userEmail, page, size);
		}
	
	@RequestMapping(
			path="/iob/instances/search/near/{lat}/{lng}/{distance}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public InstanceBoundary[] searchByLatAndLngAttributes (
				@PathVariable("lat") float lat,
				@PathVariable("lng") float lng,
				@PathVariable("distance") float distance,
				@RequestParam(name="userDomain", required=true) String userDomain,
				@RequestParam(name="userEmail", required=true) String userEmail,
				@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
				@RequestParam(name="size", required = false, defaultValue = "10") int size) {
			return this.instanceService
				.checkEligibilityAndExecuteAllByLatAndLng(lat, lng, distance, userDomain + "@@" + userEmail, page, size);
		}
	
}
