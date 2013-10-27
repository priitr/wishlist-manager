package ee.coins.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Nominal
 *
 */
@Entity
@Table(name="nominaal")
public class Nominal implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="tableGenerator_nominaal")
	private Long id;
	@Column(name="nimi", nullable=false)
	private String name;
	@Column(name="vaartus", nullable=false)
	private String value;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="riik_id", nullable=false)
	private Country country;
	@OneToMany(mappedBy="nominal")
	private List<Issue> listOfIssues;

	public Nominal() {
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
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public List<Issue> getListOfIssues() {
		return listOfIssues;
	}
	public void setListOfIssues(List<Issue> listOfIssues) {
		this.listOfIssues = listOfIssues;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}
