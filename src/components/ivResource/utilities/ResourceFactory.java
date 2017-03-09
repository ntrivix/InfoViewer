package components.ivResource.utilities;

import java.io.File;

import components.ivResource.MWindowsFile;
import components.ivResource.ivFolder.model.MivFolder;
import components.ivResource.sekDat.model.MSekDat;
import components.ivResource.serialDat.model.MSerialDat;
import components.ivResource.textFile.model.MTextFile;
import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.settings.extensionManager.model.MExtensionManager;

public class ResourceFactory {

	public static AbstractMivResource create(File file){
		if (file.exists() && file.canRead() && file.canWrite()){
			if (file.isDirectory()){
					return new MivFolder(file);
			} else {
				String extension = "";
				String fileName = file.getName();
				int i = fileName.lastIndexOf('.');
				if (i > 0) {
				    extension = fileName.substring(i+1);
				}
				if (MExtensionManager.getInstance().containsSelected(extension)){
					if (extension.equalsIgnoreCase("txt") || extension.equalsIgnoreCase("html") || extension.equalsIgnoreCase("xml") || extension.equalsIgnoreCase("dat") ){
						return new MTextFile(file);
					}
					if (extension.equalsIgnoreCase("ser")){
						return new MSerialDat(file);
					}
					if (extension.equalsIgnoreCase("sek")){
						return new MSekDat(file);
					}
					
					//OVDE PITAJ ZA DRUGE EKSTENZIJE
					return new MWindowsFile(file);
				}
				
			}
		}
		return null;
	}
	
}
