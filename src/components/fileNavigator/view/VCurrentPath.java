package components.fileNavigator.view;

import javax.swing.JTextField;

import components.fileNavigator.model.MFileNavigator;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class VCurrentPath extends JTextField implements Observer {
	private MFileNavigator fileNavigator;

	public VCurrentPath(MFileNavigator fileNavigator) {
		super();
		this.fileNavigator = fileNavigator;
		this.setEditable(false);
		fileNavigator.addObserver(this);
		setText(fileNavigator.toString());
	}

	@Override
	public void update(Observable o, Object obj, String command) {
		if (command.equals("update_path")){
			this.setText(fileNavigator.toString());
		}
	}
	
	
}
