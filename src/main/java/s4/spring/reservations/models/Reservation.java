package s4.spring.reservations.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private User rented;
	@ManyToOne
	private Lodgement lodgement;
	
	private Date start;
	private Date end;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public User getRented() {
		return rented;
	}
	public void setRented(User rented) {
		this.rented = rented;
	}
	public Lodgement getLodgement() {
		return lodgement;
	}
	public void setLodgement(Lodgement lodgement) {
		this.lodgement = lodgement;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
}
