package components.ivResource.utilities.abstractIvResource.viewer;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import components.ivResource.controllers.CCloseView;
import components.ivResource.utilities.ResourceFactoryView;
import components.ivResource.utilities.interfaces.IivResource;

public abstract class VivResource extends JPanel {
	protected IivResource model;
	protected JTable table;

	public VivResource(IivResource model) {
		super();
		this.model = model;
		this.setLayout(new BorderLayout());
		addBasicControl();
		this.setName(model.toString());
		
	}
	
	private JPanel basicControll;
	private void addBasicControl(){
		basicControll = new JPanel();
		JButton closeB = new JButton(new CCloseView(model));
		basicControll.add(closeB);
		this.toolbar();
		this.add(basicControll,BorderLayout.PAGE_START);
	}
	
	protected void toolbar(){
		
	}
	
	protected void addNewControl(Component comp, int location){
		basicControll.add(comp, location);
	}

	public abstract String toString();

	public void close(){
		ResourceFactoryView.close(model);
	};

	public IivResource getModel() {
		return model;
	}

}
