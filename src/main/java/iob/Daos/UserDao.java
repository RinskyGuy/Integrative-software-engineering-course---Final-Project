package iob.Daos;


//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import iob.data.UserEntity;

public interface UserDao extends PagingAndSortingRepository<UserEntity, String>{
	
}
