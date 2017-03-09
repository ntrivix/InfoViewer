package components.ivResource.utilities.abstractLocalFile.model;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import components.ivResource.utilities.interfaces.IivResource;

public class CReadNextBlock extends AbstractAction {

	private IivResource model;
	public CReadNextBlock(IivResource model) {
		super();
		this.model = model;
		this.putValue(NAME, "Read");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.read();
	}
	
}
