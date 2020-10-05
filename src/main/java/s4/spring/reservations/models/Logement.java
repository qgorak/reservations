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
public class Logement {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int idLogement;

	private String titre;
	private String desc;
	private String loca;
	private int prix;
	
	@ManyToOne
	private User proprietaire;
	
	
	
	
	public int getId() {
		return idLogement;
	}
	public void setId(int id) {
		this.idLogement = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLoca() {
		return loca;
	}
	public void setLoca(String loca) {
		this.loca = loca;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	

}
