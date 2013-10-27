package ee.coins.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import ee.coins.common.Constant;
import ee.coins.entity.Wishlist;

@Stateless
public class ListServices {
	
	private final Logger log = Logger.getLogger(ListServices.class);
	
	@PersistenceContext(unitName=Constant.PU_NAME_COINS_PERSISTENCE)
	EntityManager em;
	
	
	public List<Wishlist> getMyList() {
		return em.createNamedQuery(Wishlist.SELECT_ALL, Wishlist.class).getResultList();
	}

	public Wishlist createNewList(String name, String description) {
		Wishlist list = new Wishlist();
		list.setName(name);
		list.setDescription(description);
		em.persist(list);		
		return list;
	}
	
	public void updateList(Wishlist list) {

	}

	public void removeList(Long id) {
		em.remove(em.find(Wishlist.class, id));
	}
}
