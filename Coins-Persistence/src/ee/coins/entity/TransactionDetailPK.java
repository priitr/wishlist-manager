package ee.coins.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TransactionDetailPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="tehing_id")
	private Long transactionDetailId;
	@Column(name="mynt_id")
	private Long coinId;
	
	public TransactionDetailPK() {}
	public TransactionDetailPK(Long transactionDetailId, Long coinId) {
		super();
		this.transactionDetailId = transactionDetailId;
		this.coinId = coinId;
	}
	public Long getTransactionDetailId() {
		return transactionDetailId;
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
				+ ((transactionDetailId == null) ? 0 : transactionDetailId
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
		if (transactionDetailId == null) {
			if (other.transactionDetailId != null)
				return false;
		} else if (!transactionDetailId.equals(other.transactionDetailId))
			return false;
		return true;
	}
	
}
