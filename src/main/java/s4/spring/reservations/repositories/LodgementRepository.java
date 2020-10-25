package s4.spring.reservations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import s4.spring.reservations.models.Lodgement;
import s4.spring.reservations.models.User;
 
public interface LodgementRepository extends JpaRepository<Lodgement, Integer> {
 
  
    List<Lodgement> findAll();
    List<Lodgement> deleteById(int id);
	Lodgement findById(int id);

    @Query(value = "SELECT * FROM LODGEMENT l WHERE l.RENT_ID = ?1", nativeQuery = true)
    public List<Lodgement> findByRentId(int id);
	@Query(value = "SELECT * FROM lodgement WHERE (lodgement.lat BETWEEN  ?2 AND ?1) AND (lodgement.lon BETWEEN ?4 and ?3) AND (lodgement.status = 'ONLINE')", nativeQuery = true)
	public List<Lodgement> findByParamater(double latMax,double latMin,double lonMax,double lonMin);

}
    

