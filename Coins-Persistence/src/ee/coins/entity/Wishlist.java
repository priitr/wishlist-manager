package ee.coins.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ @NamedQuery(name = Wishlist.SELECT_ALL, query = "select w from Wishlist w order by w.name", lockMode = LockModeType.NONE) })
@Entity
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGenerator_wishlist")
	private Long id;
	@Column(unique = true)
	private String name;
	private String description;
	public static final String SELECT_ALL = "SELECT_ALL";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
