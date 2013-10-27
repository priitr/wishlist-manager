package ee.coins.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TransactionDetailPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="tehing_id")
	private Long transactionId;
	@Column(name="mynt_id")
	private Long coinId;
	
	public TransactionDetailPK() {}
	public TransactionDetailPK(Long transactionId, Long coinId) {
		super();
		this.transactionId = transactionId;
		this.coinId = coinId;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public Long getCoinId() {
		return coinId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coinId == null) ? 0 : coinId.hashCode());
		result = prime
				* result
				+ ((transactionId == null) ? 0 : transactionId
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionDetailPK other = (TransactionDetailPK) obj;
		if (coinId == null) {
			if (other.coinId != null)
				return false;
		} else if (!coinId.equals(other.coinId))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}
	
}
