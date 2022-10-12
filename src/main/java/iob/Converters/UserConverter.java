package iob.Converters;


import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import iob.Boundaries.NewUserBoundary;
import iob.Boundaries.UserBoundary;
import iob.RawInstaces.UserId;
import iob.data.UserEntity;
import iob.data.UserRole;

@Component
public class UserConverter {
	
	
	public UserEntity boundaryToEntityConvertion(UserBoundary boundary) {
		UserEntity userEntity = new UserEntity();
		if(boundary.getUserId() != null) {
			String uniqeId = "";
			if(boundary.getUserId().getDomain() != null) {
				uniqeId = boundary.getUserId().getDomain();
			}
			else {
				throw new RuntimeException("User domain not found");
			}
			if(boundary.getUserId().getEmail() != null && emailValidation(boundary.getUserId().getEmail())) {
				uniqeId += "@@" + boundary.getUserId().getEmail();
			}else {
				throw new RuntimeException("User email not found");
			}
			
			userEntity.setUserId(uniqeId);
		}else {
			throw new RuntimeException("User not found");
		}
		if(boundary.getAvatar() != null && !boundary.getAvatar().isEmpty())
				userEntity.setAvatar(boundary.getAvatar());
		else {
			throw new RuntimeException("Avatar not found");
		}
		if(boundary.getRole() != null && this.checkUserRole(boundary.getRole()))
			userEntity.setRole(UserRole.valueOf(boundary.getRole().toUpperCase()));
		else {
			throw new RuntimeException("Role not found");
		}
		if(boundary.getUsername() != null && !boundary.getUsername().isEmpty())
			userEntity.setUsername(boundary.getUsername());
		else {
			throw new RuntimeException("User name not found");
		}
		return userEntity;
	}

	
	public UserBoundary entityToBoundaryConvertion(UserEntity entity) {
		UserBoundary boundary = new UserBoundary();
		boundary.setId(new UserId(entity.getUserId()));
		boundary.setUsername(entity.getUsername());
		boundary.setAvatar(entity.getAvatar());
		boundary.setRole(entity.getRole() + "");
		return boundary;
	}
	
	
	public UserBoundary toUserBoundary(NewUserBoundary newUserBoundary) {
		UserBoundary userBoundary = new UserBoundary();
		UserId userId = new UserId();
		userId.setEmail(newUserBoundary.getEmail());
		userBoundary.setId(userId);
		userBoundary.setRole(newUserBoundary.getRole());
		userBoundary.setAvatar(newUserBoundary.getAvatar());
		userBoundary.setUsername(newUserBoundary.getUsername());
		return userBoundary;
	}
	
	public boolean emailValidation(String email) {
		String regexPattern = "^(.+)@(\\S+)$";
		return Pattern.compile(regexPattern)
				.matcher(email)
				.matches();
	}
	
	public boolean checkUserRole(String str) {
		for (UserRole me : UserRole.values()) {
			if (me.name().equalsIgnoreCase(str))
				return true;
		}
		return false;
	}
}
