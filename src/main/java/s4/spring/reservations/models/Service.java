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
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String label;
	
	

	
    @ManyToMany
    @JoinTable(name = "serviceLodgement",
	joinColumns = @JoinColumn( name = "idService" ),
	inverseJoinColumns = @JoinColumn( name = "idLogement" ) )
	private List<Lodgement> logement;
    
    @ManyToMany
    @JoinTable(name = "serviceReservation",
	joinColumns = @JoinColumn( name = "idService" ),
	inverseJoinColumns = @JoinColumn( name = "idReservation" ) )
	private List<Reservation> reservation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Lodgement> getLogement() {
		return logement;
	}

	public void setLogement(List<Lodgement> logement) {
		this.logement = logement;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

    
}
