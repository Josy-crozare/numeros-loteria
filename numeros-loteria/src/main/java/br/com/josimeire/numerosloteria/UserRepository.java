package br.com.josimeire.numerosloteria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
	List<User> getUser(@Param("email") String email);
}
