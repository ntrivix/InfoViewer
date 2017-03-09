package components.FileMetaGenerator.view.VColumnElement;

import javax.swing.JCheckBox;

public class VSequentialColumnElement extends VAbstractColumnElement{
	
	JCheckBox primary  = new JCheckBox("P");
	JCheckBox unique  = new JCheckBox("U");
	JCheckBox mandatory  = new JCheckBox("M");
	
	public VSequentialColumnElement() {
		apanel.add(primary);
		apanel.add(unique);
		apanel.add(mandatory);
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
