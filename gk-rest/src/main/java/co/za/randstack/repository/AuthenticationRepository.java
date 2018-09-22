package co.za.randstack.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.za.randstack.model.entity.Login;


@Repository
public interface AuthenticationRepository extends PagingAndSortingRepository<Login, Long> {
    
}
