package s4.spring.reservations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.models.User;
 
public interface UserRepository extends JpaRepository<User, Integer> {
 
  
    List<User> findAll();
    List<User> deleteById(int id);
	public User findById(int id);
    
    @Query("SELECT u FROM User u WHERE u.login = :login")
    public User getUserByLogin(@Param("login") String login);

}