package iob.logic;

import java.util.List;

import iob.Boundaries.UserBoundary;
import iob.data.UserRole;

public interface AdvancedUsersService extends UsersService {
	public List<UserBoundary> getAllUsers(int size, int page);

	public boolean checkRole(String id, UserRole expectedRole);

	public UserBoundary getSpecificUser(String userDomain, String userEmail);
	
}
