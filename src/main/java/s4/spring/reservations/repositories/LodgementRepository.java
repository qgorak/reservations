package s4.spring.reservations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import s4.spring.reservations.models.Lodgement;
 
public interface LodgementRepository extends JpaRepository<Lodgement, Integer> {
 
  
    List<Lodgement> findAll();
    List<Lodgement> deleteById(int id);
	public Lodgement findById(int id);
	List<Lodgement> findByLocalisation(String localisation);
	
	@Query("SELECT DISTINCT l FROM Lodgement l  ")
    	public List<Lodgement> search(String keyword);
	
	  }
    

