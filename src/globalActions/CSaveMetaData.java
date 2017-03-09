package globalActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import components.FileMetaGenerator.view.VColumnElement.VAbstractColumnElement;
import components.FileMetaGenerator.view.VColumnElement.VIndexSequentialColumnElement;
import components.FileMetaGenerator.view.VColumnElement.VSequentialColumnElement;
import components.FileMetaGenerator.view.VMetaGenerators.VAbstractGenerator;
import components.ivResourceElement.SupportedType;
import components.metadata.MSingleMetaD;

public class CSaveMetaData extends AbstractAction{
	
	String md;
	VAbstractGenerator vag;
	
	public CSaveMetaData(String md, VAbstractGenerator vag) {
		this.md = md;
		this.vag = vag;
		putValue(SHORT_DESCRIPTION, "Save Meta Data");
		putValue(NAME, "Save");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		vag.getMetaData().clear();
		
		for (VAbstractColumnElement vColumnElement : vag.getElements()) {
		
			Class type = ((SupportedType)vColumnElement.getComboBox().getSelectedItem()).getRealClass();
			String key = vColumnElement.getKey();
			String textF = vColumnElement.getText();
			
			if (md.equals("S")){
				if (!textF.equals("") && type!=null){
					vag.getMetaData().add(new MSingleMetaD<>(type, key, textF));
				}
			}
			if (md.equals("Q")){
				boolean isPrimary = ((VSequentialColumnElement)vColumnElement).getPrimary();
				boolean isMandatory =  ((VSequentialColumnElement)vColumnElement).getMandatory();
				boolean isUnique =  ((VSequentialColumnElement)vColumnElement).getUnique();
				if (!textF.equals("") && type!=null){
					vag.getMetaData().add(new MSingleMetaD<>(type, key, textF, isUnique, isPrimary, isMandatory));
				}
			}
			if (md.equals("I")){
				boolean isPrimary = ((VIndexSequentialColumnElement)vColumnElement).getPrimary();
				boolean isMandatory =  ((VIndexSequentialColumnElement)vColumnElement).getMandatory();
				boolean isUnique =  ((VIndexSequentialColumnElement)vColumnElement).getUnique();
				boolean isIndex =  ((VIndexSequentialColumnElement)vColumnElement).getIndex();
				if (!textF.equals("") && type!=null){
					vag.getMetaData().add(new MSingleMetaD<>(type, key, textF, isIndex, isUnique, isPrimary, isMandatory));
				}
			}
			
		}
		
		vag.setVisible(false);
	}

}
