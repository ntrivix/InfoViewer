package components.settings.extensionManager.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import components.mainAppFrame.model.MMainAppFrame;
import components.settings.extensionManager.model.MExtension;
import components.settings.extensionManager.view.VExtensionManager;
import components.settings.settingsManager.SettingsManager;

public class CAddNewExtension extends AbstractAction{
	
	
	public CAddNewExtension() {
		super();
		//putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/folder.png", 20, 20));		putValue(SHORT_DESCRIPTION, "Open File");
		putValue(NAME, "Add New Extension");
		putValue(SHORT_DESCRIPTION, "Add Extension without dot");
		//putValue(MNEMONIC_KEY, new Integer('O'));
		//putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JTextField textField = VExtensionManager.getInstance().getTextField();
		String newExtension = textField.getText();
		SettingsManager.getInstance().getExtensionManager().addExtension(new MExtension(true, newExtension));
		textField.setText("");
		SettingsManager.getInstance().save();
		MMainAppFrame.getInstance().getmFileNavigator().refresh();
	}
	
	

}
