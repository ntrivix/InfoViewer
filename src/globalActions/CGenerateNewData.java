package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import components.FileMetaGenerator.VChooser;
import components.FileMetaGenerator.view.VMetaGenerators.VAbstractGenerator;
import components.FileMetaGenerator.view.VMetaGenerators.VIndexSequentialGenerator;
import components.FileMetaGenerator.view.VMetaGenerators.VSequentialGenerator;
import components.FileMetaGenerator.view.VMetaGenerators.VSerialGenerator;

public class CGenerateNewData extends AbstractAction{
	
	private VChooser parent;
	
	public CGenerateNewData(VChooser parent) {
		this.parent = parent;
		putValue(SHORT_DESCRIPTION, "Generate Data");
		putValue(NAME, "Go");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (parent.getComboB().getSelectedItem().equals("Serial")){
			VSerialGenerator gen = new VSerialGenerator(null,"S");
			parent.setMetaD(gen.getMetaData());
		}
		if (parent.getComboB().getSelectedItem().equals("Sequential")){
			VSequentialGenerator gen = new VSequentialGenerator(null,"Q");
			parent.setMetaD(gen.getMetaData());
		}
		if (parent.getComboB().getSelectedItem().equals("Index-sequential")){
			VIndexSequentialGenerator gen = new VIndexSequentialGenerator(null,"I");
			parent.setMetaD(gen.getMetaData());
		}
		
		parent.setVisible(false);		
	}

}
