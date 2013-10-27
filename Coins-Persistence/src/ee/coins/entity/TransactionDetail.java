package ee.coins.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import ee.coins.entity.Transaction.CurrencyType;

/**
 * Entity implementation class for Entity: Transaction
 *
 */
@Entity
@Table(name="tehingu_detail")
@IdClass(value=TransactionDetailPK.class)
public class TransactionDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @Column(name="tehing_id")
	private Long transactionDetailId;
	@Id @Column(name="mynt_id")
	private Long coinId;
	@Column(name="hind")
	private Double price;
	@Enumerated(EnumType.STRING)
	@Column(name="valuuta")
	private CurrencyType currency;
	@Column(name="kogus")
	private Long amount;
	@Column(name="kirjeldus")
	private String description;

	public TransactionDetail() {
		super();
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
