package s4.spring.reservations.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.reservations.models.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Integer>{
    List<Destination> findAll();
    List<Destination> deleteById(int id);

}
