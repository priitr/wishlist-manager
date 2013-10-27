package ee.coins.web.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ee.coins.common.ClientServiceLocator;
import ee.coins.services.CoinServices;

public class NavigatorUI extends UI {

	private static final long serialVersionUID = -1502787823608167090L;
	Navigator navigator;
    protected static final String MAINVIEW = "main";
    private CoinServices myBean = (CoinServices)ClientServiceLocator.getInstance().lookupEjb(CoinServices.class.getSimpleName());

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation Example");
        
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        
        // Create and register the views
        navigator.addView("", new StartView());
        navigator.addView(MAINVIEW, new MainView());
    }
    
    class MainView extends VerticalLayout implements View {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Panel panel;

        // Menu navigation button listener
        class ButtonListener implements Button.ClickListener {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String menuitem;
            public ButtonListener(String menuitem) {
                this.menuitem = menuitem;
            }

            @Override
            public void buttonClick(ClickEvent event) {
                // Navigate to a specific state
                navigator.navigateTo(MAINVIEW + "/" + menuitem);
            }
        }

        public MainView() {
            setSizeFull();
            
            // Layout with menu on left and view area on right
            final VerticalLayout vLayout = new VerticalLayout();
            

            // Have a menu on the left side of the screen
            
            final TextField name = new TextField("Name");
            vLayout.addComponent(name);
            
    		
    		final TextField description = new TextField("Description");
    		vLayout.addComponent(description);
    		

    		Button button = new Button("Create new wishlist");
    		button.addClickListener(new Button.ClickListener() {
    			/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
    				//myBean.addNewWishlist(name.getValue(), description.getValue());
    				vLayout.addComponent(new Label("Thank you for clicking"));
    				
    			}
    		});
    		vLayout.addComponent(button);

            addComponent(vLayout);
            vLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
            vLayout.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
            vLayout.setComponentAlignment(description, Alignment.MIDDLE_CENTER);
            
            // Allow going back to the start
            Button logout = new Button("Logout", 
                       new Button.ClickListener() {

						private static final long serialVersionUID = 1L;

				@Override
                public void buttonClick(ClickEvent event) {
                    navigator.navigateTo("");
                }
            });
            addComponent(logout);
        }        
        
        @Override
        public void enter(ViewChangeEvent event) {
            HorizontalLayout panelContent = new HorizontalLayout();
            panelContent.setSizeFull();
            panelContent.setMargin(true);
            Label back = new Label("Watching you");
            back.setSizeUndefined();
            panelContent.addComponent(back);
            panelContent.setComponentAlignment(back,
                Alignment.MIDDLE_CENTER);
        }
    }
    
    class StartView extends VerticalLayout implements View {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public StartView() {
            setSizeFull();

            Button button = new Button("Go to Main View",
                    new Button.ClickListener() {

						private static final long serialVersionUID = 7611036048079031379L;

				@Override
                public void buttonClick(ClickEvent event) {
                    navigator.navigateTo(MAINVIEW);
                }
            });
            addComponent(button);
            setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        }        
            
        @Override
        public void enter(ViewChangeEvent event) {
            Notification.show("Welcome to the Animal Farm");
        }
    }
}