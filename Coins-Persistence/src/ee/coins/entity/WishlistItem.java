package ee.coins.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(value=WishlistItemPK.class)
public class WishlistItem {

	@Id
	private Long wishListId;
	@Id
	private Long issueId;
	@Id
	private Long tehingId;
	
	private String priority;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Long getWishListId() {
		return wishListId;
	}

	public Long getIssueId() {
		return issueId;
	}

	public Long getTehingId() {
		return tehingId;
	}
	
	
}
