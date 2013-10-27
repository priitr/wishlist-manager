package ee.coins.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Transaction
 *
 */
@NamedQueries({ @NamedQuery(name = Transaction.SELECT_ALL_TRANSACTIONS , query = "select t from Transaction t", lockMode = LockModeType.NONE) })
@Entity
@Table(name="tehing")
public class Transaction implements Serializable {
	
	public static final String SELECT_ALL_TRANSACTIONS = "SELECT_ALL_TRANSACTIONS";
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
