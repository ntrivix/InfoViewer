package components.settings.settingsManager;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class COpenSettingsManager extends AbstractAction{

	public COpenSettingsManager() {
		super();
		putValue(NAME, "Settings Manager");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl M"));
		putValue(MNEMONIC_KEY, new Integer('M'));
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/settings.png", 20, 20));		


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SettingsManager.getInstance();
		VGeneralSettings.getInstance().setVisible(true);
	}
			
	
}
