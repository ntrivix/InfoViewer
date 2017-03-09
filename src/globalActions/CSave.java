package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import components.ivResource.utilities.interfaces.IivResource;
import components.mainAppFrame.model.MMainAppFrame;

public class CSave extends AbstractAction{
	
	

	public CSave() {
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/Save.png", 20, 20));
		putValue(SHORT_DESCRIPTION, "Save");
		putValue(NAME, "Save");
		putValue(MNEMONIC_KEY, new Integer('S'));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Save");
		IivResource d = MMainAppFrame.getInstance().getActive();
		d.save();
	}

}
