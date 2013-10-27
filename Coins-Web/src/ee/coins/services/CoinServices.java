package ee.coins.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ee.coins.common.Constant;
import ee.coins.entity.Coin;
import ee.coins.entity.Coin.QualityType;
import ee.coins.entity.Transaction.CurrencyType;
import ee.coins.entity.Issue;
import ee.coins.entity.Transaction;
import ee.coins.entity.TransactionDetail;
import ee.coins.entity.Wishlist;

@Stateless
public class CoinServices {
	
	private final Logger log = Logger.getLogger(CoinServices.class);
	
	@PersistenceContext(unitName=Constant.PU_NAME_COINS_PERSISTENCE)
	EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addNewCoin(String description, QualityType qualityType, Issue issue) {
	
		log.info("Start inserting coin!");
		Coin coin = new Coin();
		coin.setQualityType(qualityType);
		coin.setDescription(description);
		coin.setIssue(issue);
		em.persist(coin);
	}
	

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addNewCoinPurchase(Long coinId, Date purchaseDate, Double purchasePrice, String description, CurrencyType currency) {
	
		log.info("Start inserting new coin purchase!");
		Transaction t = new Transaction();
		t.setCurrency(currency);
		t.setDate(purchaseDate);
		t.setDescription(description);
		t.setPrice(purchasePrice);
		t.setAmount(1L);
		
		em.persist(t);
		
		TransactionDetail detail = new TransactionDetail();
		detail.setCurrency(currency);
		detail.setDescription(description);
		detail.setPrice(purchasePrice);
		detail.setCoinId(coinId);
		detail.setAmount(1L);
		detail.setTransactionId(t.getId());
		
		em.persist(detail);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addNewCoinLotPurchase(List<Long> coins, Date purchaseDate, Double purchasePrice, String description, CurrencyType currency) {
	
		log.info("Start inserting new coin lot purchase!");
		Transaction t = new Transaction();
		t.setCurrency(currency);
		t.setDate(purchaseDate);
		t.setDescription(description);
		t.setPrice(purchasePrice);
		t.setAmount(new Long(coins.size()));
		
		em.persist(t);
		
		for (Long coinId : coins) {
			TransactionDetail detail = new TransactionDetail();
			detail.setCurrency(currency);
			detail.setDescription(description);
			detail.setPrice(purchasePrice);
			detail.setCoinId(coinId);
			detail.setAmount(1L);
			detail.setTransactionId(t.getId());
			em.persist(detail);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Coin> getListOfAllCoins() {	
		return em.createQuery("select c from Coin c", Coin.class).getResultList();
	}

}
