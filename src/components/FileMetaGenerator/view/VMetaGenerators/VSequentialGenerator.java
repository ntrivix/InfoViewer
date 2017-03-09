package components.FileMetaGenerator.view.VMetaGenerators;

import java.awt.Frame;

import components.FileMetaGenerator.view.VColumnElement.VSequentialColumnElement;

public class VSequentialGenerator extends VAbstractGenerator{

	public VSequentialGenerator(Frame owner, String md) {
		super(owner,md);
		
	}

	@Override
	void addNewElement() {
		VSequentialColumnElement vce = new VSequentialColumnElement();
		panel.add(vce);
		elements.add(vce);
		this.revalidate();
		
	}

}
