package ee.coins.components;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ee.coins.entity.Coin;
import ee.coins.entity.Coin.QualityType;
import ee.coins.entity.Issue;
import ee.coins.entity.Wishlist;

@Stateless
@Local
@EJB(name="myCoinBean", beanName="myCoinBean", mappedName="myCoinBean", lookup="myCoinBean", beanInterface=CoinBean.class)
public class CoinBean {
	
	public static final String PU_NAME_COINS_PERSISTENCE = "Coins-Portal-Persistence";
	
	@PersistenceContext(unitName=PU_NAME_COINS_PERSISTENCE)
	EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addNewCoin(String description, QualityType qualityType, Issue issue) {
	
		System.out.println("Start inserting coin!");
		Coin coin = new Coin();
		coin.setQualityType(qualityType);
		coin.setDescription(description);
		coin.setIssue(issue);
		em.persist(coin);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addNewWishlist(String name, String description) {
	
		System.out.println("Start inserting new wishlist!");
		Wishlist list = new Wishlist();
		list.setName(name);
		list.setDescription(description);
		em.persist(list);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Coin> getListOfAllCoins() {
		
		return em.createQuery("select c from Coin c", Coin.class).getResultList();
		
	}

}
