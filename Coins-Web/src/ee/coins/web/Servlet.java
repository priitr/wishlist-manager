package ee.coins.web;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import ee.coins.web.view.NavigatorUI;

@SuppressWarnings("serial")
@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = NavigatorUI.class)
public class Servlet extends VaadinServlet {
	
	
}