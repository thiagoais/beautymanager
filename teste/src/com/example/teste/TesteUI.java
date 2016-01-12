package com.example.teste;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("teste")
public class TesteUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = TesteUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		
		// Define a common menu command for all the menu items
		MenuBar.Command mycommand = new MenuBar.Command() {
		    MenuItem previous = null;

		    public void menuSelected(MenuItem selectedItem) {
		    	layout.addComponent(new Label("Ordered a " +
		                selectedItem.getText() +
		                " from menu."));

		        if (previous != null)
		            previous.setStyleName(null);
		        selectedItem.setStyleName("highlight");
		        previous = selectedItem;
		    }  
		};
		
		MenuBar barMenu = new MenuBar();
		barMenu.addItem("Cadastro de clientes", null, mycommand);
		barMenu.addItem("Cadastro de produtos", null, mycommand);
		barMenu.addItem("Cadastro de Serviços", null, mycommand);
		barMenu.addItem("Ordens", null, mycommand);
		layout.setMargin(true);
		setContent(layout);
	    TextField userField = new TextField();
	    userField.setCaption("Usuário");
	    TextField passwordField = new TextField();
	    passwordField.setCaption("Senha");
		Button loginButton = new Button("Login");
		loginButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Operação de login"));
			}
		});
		//layout.addComponent(barMenu);
		layout.addComponent(userField);
		layout.addComponent(passwordField);
		layout.addComponent(loginButton);
	}

}