package ee.coins.entity;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Issue
 *
 */
@Entity
@Table(name="erim")
public class Issue implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="tableGenerator_erim")
	private Long id;
	@Column(name="aasta", nullable=false)
	private String year;
	@Column(name="tiraaz")
	private Long mintage;
	@Column(name="kirjeldus")
	private String description;
	@Column(name="krauseId")
	private String krauseId;
	@Column(name="kaal", scale=10, precision=2)
	private Double weight;
	@Column(name="materjal")
	private String material;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="nominaal_id", nullable=false)
	private Nominal nominal;

	public Issue() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}   
	public Long getMintage() {
		return this.mintage;
	}

	public void setMintage(Long mintage) {
		this.mintage = mintage;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getKrauseId() {
		return this.krauseId;
	}

	public void setKrauseId(String krauseId) {
		this.krauseId = krauseId;
	}   
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}   
	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
   
	public void setNominal(Nominal nominal) {
		this.nominal = nominal;
	}
	
	public Nominal getNominal() {
		return nominal;
	}
}
