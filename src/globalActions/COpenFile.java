package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class COpenFile extends AbstractAction{
	
	public COpenFile() {
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/folder.png", 20, 20));		
		putValue(SHORT_DESCRIPTION, "Open File");
		putValue(NAME, "Open File");
		putValue(MNEMONIC_KEY, new Integer('O'));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Open File");
		// TODO Auto-generated method stub
		
	}

}
