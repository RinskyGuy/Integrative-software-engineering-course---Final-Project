package iob.logic;

import java.util.List;
import iob.Boundaries.ActivityBoundary;

public interface AdvancedActivitiesService extends ActivitiesService{
	
	public List<ActivityBoundary> getAllActivities(int size, int page);
	
}
