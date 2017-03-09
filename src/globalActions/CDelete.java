package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import components.mainAppFrame.model.MMainAppFrame;
import components.mainAppFrame.view.VMainAppFrame;


public class CDelete extends AbstractAction {

	
	
	public CDelete() {
		super();
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/delete.png", 20, 20));		putValue(SHORT_DESCRIPTION, "Delete");
		putValue(NAME, "Delete");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
		putValue(MNEMONIC_KEY, new Integer('E'));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Delete");
		try {
			MMainAppFrame.getInstance().getActive().delete();
			VMainAppFrame.getInstance().getvFileNavigator().refresh();
		} catch (Exception nullex){
			System.out.println("Nothing selected!");
		}
	}

}
