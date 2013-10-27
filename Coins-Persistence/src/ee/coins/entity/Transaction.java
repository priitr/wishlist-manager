package ee.coins.entity;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Transaction
 *
 */
@Entity
@Table(name="tehing")
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum CurrencyType {
		EUR, EEK
	}

	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="tableGenerator_tehing")
	private Long id;
	@Temporal(TemporalType.DATE)
	@Column(name="kuupaev")
	private Date date;
	@Column(name="hind_kokku")
	private Double price;
	@Enumerated(EnumType.STRING)
	@Column(name="valuuta")
	private CurrencyType currency;
	@Column(name="kirjeldus")
	private String description;
	@Column(name="kogus_kokku")
	private Long amount;
	
	

	public Transaction() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}   
	public CurrencyType getCurrency() {
		return this.currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
