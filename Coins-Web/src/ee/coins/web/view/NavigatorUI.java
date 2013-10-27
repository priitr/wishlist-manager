package ee.coins.web.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ee.coins.components.ClientServiceLocator;
import ee.coins.components.CoinBean;

public class NavigatorUI extends UI {
    Navigator navigator;
    protected static final String MAINVIEW = "main";
    private CoinBean myBean = (CoinBean)ClientServiceLocator.getInstance().lookupEjb("CoinBeanLocalBean");

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
        Panel panel;

        // Menu navigation button listener
        class ButtonListener implements Button.ClickListener {

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
    			public void buttonClick(ClickEvent event) {
    				myBean.addNewWishlist(name.getValue(), description.getValue());
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
        public StartView() {
            setSizeFull();

            Button button = new Button("Go to Main View",
                    new Button.ClickListener() {
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