package ee.coins.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ee.coins.entity.Coin;
import ee.coins.entity.Country;
import ee.coins.entity.Issue;
import ee.coins.entity.Nominal;
import ee.coins.entity.Transaction.CurrencyType;
import ee.coins.services.CoinServices;

public class CoinServicesTest {
	
	private EJBContainer container;
	private CoinServices coinServices;
	
	
	@Before
	public void setUp() throws Exception {
		
		
		Properties map = new Properties();
		map.put("java.naming.factory.initial ", "org.apache.openejb.client.LocalInitialContextFactory");
        map.put("coins-postgres", "new://Resource?type=DataSource");
		map.put("coins-postgres.JdbcUrl", "jdbc:postgresql://localhost:5432/jpa-coins");
		map.put("coins-postgres.JdbcDriver", "org.postgresql.Driver");
		map.put("coins-postgres.UserName", "postgres");
		map.put("coins-postgres.Password", "midaIganes123");
		container = EJBContainer.createEJBContainer(map);
		
		coinServices = (CoinServices)container.getContext().lookup("java:global/Coins-Web/" + CoinServices.class.getSimpleName());
	}

	@Test
	public void whenEmptyDatabaseThenReturnZeroCoins() {
		
		List<Coin> coins = coinServices.getListOfAllCoins();
		Assert.assertEquals(0, coins.size());

	}
	
	@Test
	public void addNewCoin() throws Exception {
		
		Issue issue = createSoome10Pennia();
		
		List<Coin> coins = coinServices.getListOfAllCoins();
		Assert.assertEquals(0, coins.size());
		coinServices.addNewCoin("10 penni", Coin.QualityType.BU, issue);
		coins = coinServices.getListOfAllCoins();
		Assert.assertEquals(1, coins.size());
		Assert.assertEquals("Soome", coins.get(0).getIssue().getNominal().getCountry().getName());
	}
	
	
	@Test
	public void addNewCoinPurchase() throws Exception {
		
		Issue issue = createSoome10Pennia();
		
		List<Coin> coins = coinServices.getListOfAllCoins();
		Assert.assertEquals(0, coins.size());
		coinServices.addNewCoin("10 penni", Coin.QualityType.BU, issue);
		coins = coinServices.getListOfAllCoins();
		Assert.assertEquals(1, coins.size());
		Assert.assertEquals("Soome", coins.get(0).getIssue().getNominal().getCountry().getName());
		
		coinServices.addNewCoinPurchase(coins.get(0).getId(), new Date(), 10.99, "Kalamaja kirbukalt", CurrencyType.EUR);
	}
	
	
	@Test
	public void addNewCoinLotPurchase() throws Exception {
		
		List<Coin> coins = coinServices.getListOfAllCoins();
		Assert.assertEquals(0, coins.size());
		coinServices.addNewCoin("10 penniä", Coin.QualityType.BU, createSoome10Pennia());
		coinServices.addNewCoin("5 penniä", Coin.QualityType.PRF, createSoome10Pennia());
		coinServices.addNewCoin("25 penniä", Coin.QualityType.UNC, createSoome10Pennia());
		coins = coinServices.getListOfAllCoins();
		Assert.assertEquals(3, coins.size());
		Assert.assertEquals("Soome", coins.get(0).getIssue().getNominal().getCountry().getName());
		
		List<Long> list = new ArrayList<Long>();
		for(Coin coin:coins) {
			list.add(coin.getId());
		}
		
		coinServices.addNewCoinLotPurchase(list, new Date(), 100.59, "Osta.ee", CurrencyType.EUR);
	}
	
	

	private Issue createSoome10Pennia() {
		Issue issue = new Issue();
		issue.setKrauseId("KM1.1");
		issue.setMaterial("Ag999");
		issue.setMintage(10000L);
		issue.setWeight(29.9D);
		issue.setYear("2009");
		
		Country country = new Country();
		country.setName("Soome");
		
		Nominal nominal = new Nominal();
		nominal.setName("10 Pennia");
		nominal.setValue("10");
		nominal.setCountry(country);
		issue.setNominal(nominal);
		return issue;
	}

	@After
	public void tearDown() {
		container.close();
	}
}
