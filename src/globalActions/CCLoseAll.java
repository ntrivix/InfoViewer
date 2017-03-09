package globalActions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.mainAppFrame.view.VMainAppFrame;

public class CCLoseAll extends AbstractAction{
	
	public CCLoseAll() {
		putValue(SMALL_ICON, utilities.ResourceLoader.getImageIcon("icon/closeall.png", 20, 20));		
		putValue(SHORT_DESCRIPTION, "Close All");
		putValue(NAME, "Close All");
		putValue(MNEMONIC_KEY, new Integer('L'));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt L"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (Component comp : VMainAppFrame.getInstance().getFileViewer().getComponents()) {
			if (comp instanceof VivResource){
				((VivResource) comp).close();
			}
		}
	}

}
