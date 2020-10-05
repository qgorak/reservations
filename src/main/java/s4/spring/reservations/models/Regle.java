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
public class Regle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRegle;

	
    @ManyToMany
    @JoinTable(name = "regle_logement",
	joinColumns = @JoinColumn( name = "idRegle" ),
	inverseJoinColumns = @JoinColumn( name = "idLogement" ) )
	private List<Logement> logement;

    
}
