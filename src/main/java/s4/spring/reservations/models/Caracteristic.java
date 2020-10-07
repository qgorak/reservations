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
import javax.persistence.ManyToOne;

@Entity
public class Caracteristic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String label;
	
    @ManyToMany
    @JoinTable(name = "caracteristicLodgement",
    			joinColumns = @JoinColumn( name = "idCaracteristic" ),
    			inverseJoinColumns = @JoinColumn( name = "idLodgement" ) )
	private List<Lodgement> lodgement = new ArrayList<>();
    
	@ManyToOne
	private Type typeCara;

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

	

	public List<Lodgement> getLodgement() {
		return lodgement;
	}

	public void setLodgement(List<Lodgement> lodgement) {
		this.lodgement = lodgement;
	}

	public Type getTypeCara() {
		return typeCara;
	}

	public void setTypeCara(Type typeCara) {
		this.typeCara = typeCara;
	}

	
 
	
}
