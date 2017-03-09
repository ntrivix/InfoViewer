package components.FileMetaGenerator.view.VMetaGenerators;

import java.awt.Frame;

import components.FileMetaGenerator.view.VColumnElement.VSerialColumnElement;

public class VSerialGenerator extends VAbstractGenerator{
	
	private String md;

	public VSerialGenerator(Frame owner, String md) {
		super(owner, md);
		// TODO Auto-generated constructor stub
	}

	@Override
	void addNewElement() {
		
		VSerialColumnElement vce = new VSerialColumnElement();
		panel.add(vce);
		elements.add(vce);
		this.revalidate();
		
	}
	
	

}
