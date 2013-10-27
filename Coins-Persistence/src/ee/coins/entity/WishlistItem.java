package ee.coins.entity;

import javax.persistence.EmbeddedId;

public class WishlistItem {

	@EmbeddedId
	private WishlistItemPK id;
	private String priority;
	
	public WishlistItemPK getId() {
		return id;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public void setId(WishlistItemPK id) {
		this.id = id;
	}
}
