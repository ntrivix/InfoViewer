package components.ivResource.controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import components.ivResource.utilities.ResourceFactoryView;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.interfaces.IivResource;

public class CCloseView extends AbstractAction {
	
	private IivResource model;
	public CCloseView(IivResource model) {
		super();
		this.model = model;
		this.putValue(NAME, "Close");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		VivResource view = ResourceFactoryView.getViwer(model);		
		view.close();
	}

}
