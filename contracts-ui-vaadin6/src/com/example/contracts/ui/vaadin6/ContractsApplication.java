package com.example.contracts.ui.vaadin6;

import com.vaadin.Application;
import com.vaadin.ui.*;

/**
 * Main application class.
 */
public class ContractsApplication extends Application {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
	Window mainWindow = new Window("Contracts Application");
	MainWindowContainer mainContainer = new MainWindowContainer();
	mainWindow.setContent(mainContainer);
	mainContainer.setSizeFull();
	setMainWindow(mainWindow);
	setTheme("chameleon");
	//setTheme("contractstheme");
    }

}
