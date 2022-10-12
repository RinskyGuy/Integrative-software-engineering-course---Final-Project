package iob.logic;

import java.util.List;

import iob.Boundaries.UserBoundary;

public interface UsersService {
	public UserBoundary createUser(UserBoundary user);
	public UserBoundary login(String userDomain, String userEmail);
	public UserBoundary updateUser(String userDomain, String email, UserBoundary update);
	@Deprecated
	public List<UserBoundary> getAllUsers();
	public void deleteAllUsers();
}
