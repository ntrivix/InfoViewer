package components.ivResource.utilities;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import components.FileMetaGenerator.view.VMetaGenerator;
import components.FileMetaGenerator.view.VMetaGenerators.VAbstractGenerator;
import components.FileMetaGenerator.view.VMetaGenerators.VIndexSequentialGenerator;
import components.FileMetaGenerator.view.VMetaGenerators.VSerialGenerator;
import components.ivResource.MWindowsFile;
import components.ivResource.dbResource.DbTableModel;
import components.ivResource.dbResource.VDBResource;
import components.ivResource.sekDat.model.MSekDat;
import components.ivResource.sekDat.view.VsekDat;
import components.ivResource.serialDat.model.MSerialDat;
import components.ivResource.textFile.model.MTextFile;
import components.ivResource.textFile.view.VTextFile;
import components.ivResource.utilities.abstractIvResource.viewer.VivResource;
import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.ivResource.utilities.abstractLocalFile.model.VLocalFile;
import components.ivResource.utilities.interfaces.IivResource;
import components.mainAppFrame.view.VMainAppFrame;

public class ResourceFactoryView {
	private static HashMap<String, VivResource> openViews = new HashMap<>();

	public static VivResource getViwer(IivResource model) {
		if (openViews.containsKey(model.getHashCode()))
			return openViews.get(model.getHashCode());

		VivResource view = null;
		if (model instanceof MWindowsFile) {
			try {
				Desktop.getDesktop().open(((MWindowsFile)model).getLocation());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (model instanceof MTextFile) {
				view = new VTextFile((MTextFile) model);
			} else if (model instanceof ALocalFileModel) {

				if (model.getMetaData().getMetaD().isEmpty()) {

					VAbstractGenerator m;
					if (model instanceof MSerialDat)
						m = new VSerialGenerator(null, "S");
					else
						m = new VIndexSequentialGenerator(null, "I");

					m.setVisible(true);
					if (m.getMetaData().isEmpty())
						return null;
					model.setMetaData(m.getMetaDataObject());
					m = null;
				}
				if (model instanceof MSerialDat)
					view = new VLocalFile(model);
				else if (model instanceof MSekDat)
					view = new VsekDat((MSekDat) model);
			} else 				
			if (model instanceof DbTableModel){
				view = new VDBResource(model);
			}
			else {
			
				view = new VTextFile((MTextFile) model);
			}

			openViews.put(model.getHashCode(), view);
		} 
		// view = new VTextFile((MTextFile) model);
		// openViews.put(model.getLocation().getAbsolutePath(), view);

		return view;
	}
	
	public static VivResource getByHash(String hash){
		if (openViews.containsKey(hash))
			return openViews.get(hash);
		return null;
	}

	public static VivResource isExists(IivResource model) {
		if (openViews.containsKey(model.getHashCode()))
			return openViews.get(model.getHashCode());
		return null;
	}
	

	public static void close(IivResource model) {
		openViews.remove(model.getHashCode());
	}
}
