package s4.spring.reservations.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Logement logement;

	private String photo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Logement getLogement() {
		return logement;
	}
	public void setLogement(Logement logement) {
		this.logement = logement;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
