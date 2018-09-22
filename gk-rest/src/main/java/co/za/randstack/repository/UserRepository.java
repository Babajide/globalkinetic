package co.za.randstack.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.za.randstack.model.entity.User;


@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
   User findByUserName(String username);
}
