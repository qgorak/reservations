package s4.spring.reservations.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SalleDeBain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSalleDeBain;

	
    @ManyToMany
    @JoinTable(name = "salle_de_bain_logement",
	joinColumns = @JoinColumn( name = "idSalleDeBain" ),
	inverseJoinColumns = @JoinColumn( name = "idLogement" ) )
	private List<Logement> logement;

    
}
