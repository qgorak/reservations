package s4.spring.reservations.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Lodgement implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String type;
	private String title;
	private String description;
	private int price;
	private int nbr_room;
	private double lat;
	private double lon;
	private int nbr_place;
	private String status;
	
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
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public int getNbr_place() {
		return nbr_place;
	}
	public void setNbr_place(int nbr_place) {
		this.nbr_place = nbr_place;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
