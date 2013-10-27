package ee.coins.components;

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

public class CoinBeanTest {
	
	private EJBContainer container;
	private CoinBean coinBean;
	
	
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
		
		coinBean = (CoinBean)container.getContext().lookup("java:global/Coins-Web/CoinBean");
	}

	@Test
	public void whenEmptyDatabaseThenReturnZeroCoins() {
		
		List<Coin> coins = coinBean.getListOfAllCoins();
		Assert.assertEquals(0, coins.size());

	}
	
	@Test
	public void addNewCoin() throws Exception {
		
		Issue issue = createSoome10Pennia();
		
		List<Coin> coins = coinBean.getListOfAllCoins();
		Assert.assertEquals(0, coins.size());
		coinBean.addNewCoin("10 penni", Coin.QualityType.BU, issue);
		coins = coinBean.getListOfAllCoins();
		Assert.assertEquals(1, coins.size());
		Assert.assertEquals("Soome", coins.get(0).getIssue().getNominal().getCountry().getName());

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
		nominal.setName("10 Penniä");
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
