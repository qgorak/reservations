package s4.spring.reservations.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Equipement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEquip;
	private String nom;
	
    @ManyToMany
    @JoinTable(name = "equipement_logement",
    			joinColumns = @JoinColumn( name = "idEquip" ),
    			inverseJoinColumns = @JoinColumn( name = "idLogement" ) )
	private List<Logement> logement = new ArrayList<>();
 
}
