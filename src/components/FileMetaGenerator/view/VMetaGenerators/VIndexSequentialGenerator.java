package components.FileMetaGenerator.view.VMetaGenerators;

import java.awt.Frame;

import components.FileMetaGenerator.view.VColumnElement.VIndexSequentialColumnElement;
import components.FileMetaGenerator.view.VColumnElement.VSerialColumnElement;

public class VIndexSequentialGenerator extends VAbstractGenerator{

	public VIndexSequentialGenerator(Frame owner, String md) {
		super(owner,md);
		// TODO Auto-generated constructor stub
	}

	@Override
	void addNewElement() {
		
		VIndexSequentialColumnElement vce = new VIndexSequentialColumnElement();
		panel.add(vce);
		elements.add(vce);
		this.revalidate();
		
	}



}
