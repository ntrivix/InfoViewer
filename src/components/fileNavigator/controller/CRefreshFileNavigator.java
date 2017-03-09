package components.fileNavigator.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import components.mainAppFrame.model.MMainAppFrame;
import utilities.ResourceLoader;

public class CRefreshFileNavigator extends AbstractAction {

	public CRefreshFileNavigator() {
		super();
		putValue(SMALL_ICON, ResourceLoader.getImageIcon("icon/refresh.png", 15, 15));
		putValue(SHORT_DESCRIPTION, "Refresh folder content ");
		//putValue(NAME, "Refresh");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl R"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MMainAppFrame.getInstance().getmFileNavigator().refresh();
		System.out.println("Refresh");
	}

}
