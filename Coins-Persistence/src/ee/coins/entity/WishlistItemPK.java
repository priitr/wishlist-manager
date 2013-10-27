package ee.coins.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WishlistItemPK implements Serializable {

	private static final long serialVersionUID = 2330813474568928084L;
	
	private Long wishListId;
	@Column(name="erim_id")
	private Long issueId;
	@Column(name="tehing_id")
	private Long tehingId;
	
	public WishlistItemPK() {}

	public WishlistItemPK(Long wishListId, Long issueId, Long tehingId) {
		super();
		this.wishListId = wishListId;
		this.issueId = issueId;
		this.tehingId = tehingId;
	}
 
	public Long getIssueId() {
		return issueId;
	}
	
	public Long getTehingId() {
		return tehingId;
	}
	
	public Long getWishListId() {
		return wishListId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
		result = prime * result
				+ ((tehingId == null) ? 0 : tehingId.hashCode());
		result = prime * result
				+ ((wishListId == null) ? 0 : wishListId.hashCode());
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
		WishlistItemPK other = (WishlistItemPK) obj;
		if (issueId == null) {
			if (other.issueId != null)
				return false;
		} else if (!issueId.equals(other.issueId))
			return false;
		if (tehingId == null) {
			if (other.tehingId != null)
				return false;
		} else if (!tehingId.equals(other.tehingId))
			return false;
		if (wishListId == null) {
			if (other.wishListId != null)
				return false;
		} else if (!wishListId.equals(other.wishListId))
			return false;
		return true;
	}
	
	
}
