package s4.spring.reservations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import s4.spring.reservations.models.Lodgement;
 
public interface LodgementRepository extends JpaRepository<Lodgement, Integer> {
 
  
    List<Lodgement> findAll();
    List<Lodgement> deleteById(int id);
	Lodgement findById(int id);
	@Query(value = "SELECT * FROM lodgement WHERE (lodgement.lat BETWEEN  ?2 AND ?1) AND (lodgement.lon BETWEEN ?4 and ?3)", nativeQuery = true)
	public List<Lodgement> findByParamater(double latMax,double latMin,double lonMax,double lonMin);

}
    

