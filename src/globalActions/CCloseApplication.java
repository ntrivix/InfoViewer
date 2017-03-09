package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class CCloseApplication extends AbstractAction{
	
	public CCloseApplication() {
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/CloseWindow.png", 20, 20));		putValue(SHORT_DESCRIPTION, "Open File");
		putValue(SHORT_DESCRIPTION, "CloseAplication ");
		putValue(NAME, "CloseAplication ");
		putValue(MNEMONIC_KEY, new Integer('C'));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl C"));
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("CloseApp");
		// TODO Auto-generated method stub
		
	}

}
