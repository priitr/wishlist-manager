package ee.coins.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientServiceLocator {
   private Context initialContext;
   private Map cache;
   private static ClientServiceLocator 
           ourInstance = new ClientServiceLocator();

   public static ClientServiceLocator getInstance() {
      return ourInstance;
   }

   private ClientServiceLocator() {
      try {

    	  Properties p = new Properties();
    	  p.setProperty("log4j.category.OpenEJB.options ", "debug");
    	  p.setProperty("log4j.category.OpenEJB.startup ", "debug");
    	  p.setProperty("log4j.category.OpenEJB.startup.config ", "debug");
    	  p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
    	  p.setProperty("openejb.jndiname.format ", "comp/env/ejb/{interfaceClass}");
          //p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
          //p.put(Context.PROVIDER_URL, "ejbd://localhost:4201/");
    

         this.initialContext = new InitialContext(p);
         this.cache = Collections.synchronizedMap(new HashMap());
      }
      catch(NamingException ne) { 
         System.err.printf(
            "Error in CTX looking up %s because of %s while %s",
            ne.getRemainingName(),
            ne.getCause(),
            ne.getExplanation());
      }
   }

   public Object lookupEjb(String ejbName) {
      if(this.cache.containsKey(ejbName)) {
         return this.cache.get(ejbName);
      }
      else {
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