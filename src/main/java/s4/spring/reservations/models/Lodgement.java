package s4.spring.reservations.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Lodgement {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String type;
	private String title;
	private String description;
	private String localisation;
	private int price;
	private int nbr_room;
	
	@ManyToOne
	private User rent;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNbr_room() {
		return nbr_room;
	}
	public void setNbr_room(int nbr_room) {
		this.nbr_room = nbr_room;
	}
	public User getRent() {
		return rent;
	}
	public void setRent(User rent) {
		this.rent = rent;
	}
	
	
	

}
