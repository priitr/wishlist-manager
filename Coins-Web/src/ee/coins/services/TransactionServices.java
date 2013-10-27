package ee.coins.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ee.coins.common.Constant;
import ee.coins.entity.Transaction;
import ee.coins.entity.Transaction.CurrencyType;

@Stateless
public class TransactionServices {
	
	private final Logger log = Logger.getLogger(TransactionServices.class);
	
	@PersistenceContext(unitName=Constant.PU_NAME_COINS_PERSISTENCE)
	EntityManager em;
	
	
	public List<Transaction> getMyTransactions() {
		return em.createNamedQuery(Transaction.SELECT_ALL_TRANSACTIONS, Transaction.class).getResultList();
	}

	public void createNewTransaction(String description, Date date, Double price, CurrencyType currency) {

		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setPrice(price);
		transaction.setCurrency(currency);
		transaction.setDescription(description);
		em.persist(transaction);
	}
	
	public void updateTransaction() {

	}

	public void removeTransaction(Long id) {
		em.remove(em.find(Transaction.class, id));
	}
}
