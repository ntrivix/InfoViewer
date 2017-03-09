package globalActions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import components.ivResource.serialDat.model.MSerialDat;
import components.ivResource.utilities.abstractLocalFile.model.VFindRow;
import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.ResourceRow;
import components.newFile.CNewFile;

public class COpenFoundInDB extends AbstractAction{
	
	private VFindRow parent;
	private MSerialDat file;

	public COpenFoundInDB(VFindRow parent) {
		this.parent = parent;
		putValue(NAME, "Find");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ArrayList< AbstractResourceElement> elements = new ArrayList<>();
		int i = 0;
		int limit = 0;
		for (String element : parent.getTexts()) {
			
			if (element.equals("")) elements.add(null);
			else
				try {
					elements.add(parent.getMd().getMetaD(i).generateModel(element));
				} catch (Exception e) {
					elements.add(null);
				}
			i++;
		}
		ResourceRow newRow = new ResourceRow(elements);
		String text = parent.getTLimit();
		try{
			limit = Integer.parseInt(text);
		}catch (Exception e){
			limit = 0;
		}
		
		ResourceRow[] res;
		parent.getResource().connect();
		res = parent.getResource().find(newRow, 1);
		
		parent.getResource().disconnect();
		parent.setVisible(false);
		//new MSerialDat();
		
		
	}

}
