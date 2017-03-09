package components.FileMetaGenerator.view.VColumnElement;

import javax.swing.JCheckBox;

public class VIndexSequentialColumnElement extends VAbstractColumnElement{
	
	JCheckBox index  = new JCheckBox("I");
	JCheckBox primary  = new JCheckBox("P");
	JCheckBox unique  = new JCheckBox("U");
	JCheckBox mandatory  = new JCheckBox("M");
	
	public VIndexSequentialColumnElement() {
		apanel.add(primary);
		apanel.add(index);
		apanel.add(unique);
		apanel.add(mandatory);
	}

	public boolean getIndex() {
		return index.isSelected();
	}

	public boolean getPrimary() {
		return primary.isSelected();
	}

	public boolean getUnique() {
		return unique.isSelected();
	}

	public boolean getMandatory() {
		return mandatory.isSelected();
	}

	

}
