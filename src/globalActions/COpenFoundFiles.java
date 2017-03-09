package globalActions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import components.ivResource.serialDat.model.MSerialDat;
import components.ivResource.utilities.ResourceFactory;
import components.ivResource.utilities.ResourceFactoryView;
import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.abstractLocalFile.model.VFindRow;
import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.ResourceRow;
import components.mainAppFrame.model.MMainAppFrame;
import components.mainAppFrame.view.VMainAppFrame;
import components.newFile.CNewFile;
import components.newFile.VFileChooser;
import components.settings.extensionManager.model.MExtensionManager;

public class COpenFoundFiles extends AbstractAction{
	
	private VFindRow parent;
	private MSerialDat file;
	
	public COpenFoundFiles(VFindRow parent) {
		this.parent = parent;
		putValue(NAME, "Find");
		// TODO Auto-generated constructor stub
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
		if (parent.isFromBeg())
			((MSerialDat)parent.getResource()).setFilePointer(0);
		else
			((MSerialDat)parent.getResource()).seekToFilePointer();
		if (limit==0 || parent.saveToFileIsChecked()){
			int pomLimit = limit;
			CNewFile newfile = new CNewFile();
			newfile.setMd(parent.getMd());
			newfile.actionPerformed(null);
			file = (MSerialDat) newfile.getMfile();
			do{
				res = parent.getResource().find(newRow, 1);
				if (res.length != 0){
					file.addNewRow(res[0],false);
					pomLimit--;
				}
				else break;
			}while (true && (limit == 0 || (limit > 0 && pomLimit >0)));
			file.setFilePointer(0);
			file.read();
		}
		else{
			
		}
		parent.getResource().disconnect();
		parent.setVisible(false);
		//new MSerialDat();
		
		
		
		
	}

}
