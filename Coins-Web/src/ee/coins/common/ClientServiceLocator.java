package ee.coins.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class ClientServiceLocator {

	private final Logger log = Logger.getLogger(ClientServiceLocator.class);

	private Context initialContext;
	private Map<String, Object> cache;
	private static ClientServiceLocator ourInstance = new ClientServiceLocator();

	public static ClientServiceLocator getInstance() {
		return ourInstance;
	}

	private ClientServiceLocator() {
		try {

			Properties p = new Properties();
			p.put("java.naming.factory.initial",
					"org.apache.openejb.client.LocalInitialContextFactory");
			p.setProperty("log4j.category.OpenEJB.options ", "debug");
			p.setProperty("log4j.category.OpenEJB.startup ", "debug");
			p.setProperty("log4j.category.OpenEJB.startup.config ", "debug");
			p.setProperty("openejb.jndiname.format ",
					"comp/env/ejb/{interfaceClass}");

			this.initialContext = new InitialContext(p);
			this.cache = Collections
					.synchronizedMap(new HashMap<String, Object>());
		} catch (NamingException ne) {
			System.err.printf(
					"Error in CTX looking up %s because of %s while %s",
					ne.getRemainingName(), ne.getCause(), ne.getExplanation());
		}
	}

	public Object lookupEjb(String ejbName) {
		return lookup(ejbName + "LocalBean");
	}

	public Object lookup(String ejbName) {
		log.info("lookupEjb " + ejbName);
		if (this.cache.containsKey(ejbName)) {
			return this.cache.get(ejbName);
		} else {
			try {
				Object ejbRef = initialContext.lookup(ejbName);
				this.cache.put(ejbName, ejbRef);
				return ejbRef;
			} catch (NamingException ne) {
				throw new RuntimeException(ne);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}