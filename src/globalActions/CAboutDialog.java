package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import components.menus_toolbars.mainMenu.AboutDialog;

public class CAboutDialog extends AbstractAction{
	
	public CAboutDialog() {
		// TODO Auto-generated constructor stub
		putValue(NAME, "About Authors");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl A"));
		putValue(MNEMONIC_KEY, new Integer('A'));
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/about.png", 20, 20));		putValue(SHORT_DESCRIPTION, "Delete");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AboutDialog aboutDialog = new AboutDialog();	
		aboutDialog.setVisible(true);
		
	}

}
