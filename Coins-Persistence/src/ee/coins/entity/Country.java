package ee.coins.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Country
 *
 */
@Entity
@Table(name="riik")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="tableGenerator_riik")
	private Long id;
	@Column(name="nimi", nullable=false)
	private String name;
	@OneToMany (mappedBy="country") 
	private List<Nominal> nominals;
	
	public Country() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
	public List<Nominal> getNominals() {
		return nominals;
	}
	
	public void setNominals(List<Nominal> nominals) {
		this.nominals = nominals;
	}
}
