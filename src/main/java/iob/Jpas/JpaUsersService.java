package iob.Jpas;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import iob.Boundaries.UserBoundary;
import iob.Converters.UserConverter;
import iob.Daos.UserDao;
import iob.RawInstaces.UserId;
import iob.data.UserEntity;
import iob.data.UserRole;
import iob.logic.AdvancedUsersService;

@Service
public class JpaUsersService implements AdvancedUsersService{
	private UserDao userDao;
	private UserConverter userConverter;
	
	private String domain;
	
	@Autowired
	public JpaUsersService(UserDao userDao, UserConverter userConverter) {
		this.userDao = userDao;
		this.userConverter = userConverter;
	}
	
	@Value("${domain:2022b.Hen.Shiryon}")
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Override
	@Transactional
	public UserBoundary createUser(UserBoundary user) {
		user.getUserId().setDomain(this.domain);
		UserEntity userEntity = this.userConverter.boundaryToEntityConvertion(user);
		userEntity = this.userDao.save(userEntity);	
		return this.userConverter.entityToBoundaryConvertion(userEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary login(String userDomain, String userEmail) {
		Optional<UserEntity> entityOp = this.userDao.findById(userDomain + "@@" + userEmail);
		if(entityOp.isPresent()) {
			UserEntity entity = entityOp.get();
			return this.userConverter.entityToBoundaryConvertion(entity);
		}else {
			throw new UserNotFoundException("No such user, with domain: " + userDomain + " and with email: " + userEmail);
		}
	}


	@Override
	@Transactional//(readOnly = false)
	public UserBoundary updateUser(String userDomain, String email, UserBoundary update) {
		UserEntity entity = this.getUserByIdAsEntity(new UserId(userDomain, email));
		
		if(update.getUserId() != null)
			// do nothing
			
		if(update.getUsername() != null) {
			entity.setUsername(update.getUsername());
		}
		
		if(update.getRole() != null) {
			if(update.getRole().toUpperCase().equals("PLAYER") ||
					update.getRole().toUpperCase().equals("MANAGER") ||
					update.getRole().toUpperCase().equals("ADMIN"))
				entity.setRole(UserRole.valueOf(update.getRole().toUpperCase()));
		}
		
		if(update.getAvatar() != null)
			entity.setAvatar(update.getAvatar());
		
		entity = this.userDao.save(entity); // update in database
		
		return this.userConverter.entityToBoundaryConvertion(entity);
	}


	@Override
	@Deprecated
	public List<UserBoundary> getAllUsers() {
		throw new RuntimeException("deprecated operation, use paginated data retrieval instead");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(int size, int page) {
		return StreamSupport
				.stream(
						this.userDao
						.findAll(PageRequest.of(page, size, Direction.ASC, "username", "userId")).spliterator(),
						false)  				   		   // Stream<UserEntity>
				.map(this.userConverter::entityToBoundaryConvertion)    // Stream<UserBoundary>
				.collect(Collectors.toList()); 		   	 // List<UserBoundary>
	}


	@Override
	@Transactional
	public void deleteAllUsers() {
		this.userDao
		.deleteAll();
	}
	
	@Transactional(readOnly = true) // can be read together 
	public UserBoundary getUserById(UserId id) {
		Optional<UserEntity> entityOp = this.userDao.findById(id.getDomain() + "@@" + id.getEmail());
		if(entityOp.isPresent()) {
			UserEntity entity = entityOp.get();
			return this.userConverter.entityToBoundaryConvertion(entity);
		}else {
			throw new UserNotFoundException("No such user, with id: " + id);
		}
	}
	
	
	@Transactional(readOnly = true) // can be read together 
	public UserEntity getUserByIdAsEntity(String id) {
		Optional<UserEntity> entityOp = this.userDao.findById(id);
		if(entityOp.isPresent()) {
			UserEntity entity = entityOp.get();
			return entity;
		}else {
			throw new UserNotFoundException("No such user, with id: " + id);
		}
	}

	
	@Transactional(readOnly = true) // can be read together 
	public UserEntity getUserByIdAsEntity(UserId id) {
		Optional<UserEntity> entityOp = this.userDao.findById(id.getDomain() + "@@" + id.getEmail());
		if(entityOp.isPresent()) {
			UserEntity entity = entityOp.get();
			return entity;
		}else {
			throw new UserNotFoundException("No such user, with id: " + id);
		}
	}
	
	public boolean checkRole(String id, UserRole expectedRole) {
		UserEntity entity = getUserByIdAsEntity(id);
		return entity.getRole().equals(expectedRole);
	}

	@Override
	public UserBoundary getSpecificUser(String userDomain, String userEmail) {
		Optional<UserEntity> entityOp = this.userDao.findById(userDomain + "@@" + userEmail);
		if(entityOp.isPresent()) {
			UserEntity entity = entityOp.get();
			return this.userConverter.entityToBoundaryConvertion(entity);
		}else {
			throw new InstanceNotFoundException("No such instance, with id: " + userDomain + "@@" + userEmail);
		}
	}
	
}
