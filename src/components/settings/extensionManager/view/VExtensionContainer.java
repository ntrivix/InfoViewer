package components.settings.extensionManager.view;

import java.awt.GridLayout;

import javax.swing.JLabel;

import components.settings.extensionManager.model.MExtension;
import components.settings.extensionManager.model.MExtensionManager;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class VExtensionContainer extends JLabel implements Observer{

	public VExtensionContainer() {
		super();
		setLayout(new GridLayout(0, 2));
		for (MExtension me : MExtensionManager.getInstance().getExtensions()) {
			this.add(new VExtension(me));
		}
		MExtensionManager.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object obj, String command) {
		if (command.equals("AddExtension")){
			this.add(new VExtension((MExtension)obj));
			revalidate();
			//repaint();
		}
		
	}
	
	

}
