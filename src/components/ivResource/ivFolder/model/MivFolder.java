package components.ivResource.ivFolder.model;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import components.ivResource.utilities.ResourceFactory;
import components.ivResource.utilities.abstractIvResource.model.MAbstractResourceAdapter;
import configs.AppConfigs;
import utilities.ResourceLoader;

public class MivFolder extends MAbstractResourceAdapter {

	private static ImageIcon icon = ResourceLoader.getImageIcon("icon/folder.png",AppConfigs.NAV_ICON_w, AppConfigs.NAV_ICON_H);
	
	private String name;
	private String path;
	private File file;
	
	public MivFolder(File file) {
		super(file);
		this.file = file;
		this.name = file.getName();
		if (name.equals(""))
			name = file.getPath();
		try {
			this.path = file.getCanonicalPath();
		} catch (IOException e) {
			this.path = file.getAbsolutePath();
			e.printStackTrace();
		}
	}
	
	public MivFolder(String path){
		super(new File(path));
		this.file = new File(path);
		this.path = file.getPath();
		this.name = file.getName();
		if (name.equals(""))
			name = file.getPath();
	}
	
	public MivFolder(String path, String name) throws IOException{
		super(new File(new File(path).getCanonicalPath()));
		try {
			
			this.file = new File(new File(path).getCanonicalPath());
			this.path = this.file.getPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.name = name;
	}
	
	

	public String getName() {
		return name;
	}



	public String getPath() {
		return path;
	}



	public File getFile() {
		return file;
	}


	@Override
	public boolean rename(String newName) {
		//this.getFile().renameTo();
		return false;
	}




	@Override
	public String toString() {
		return name;
	}



	@Override
	public File getLocation() {
		return file;
	}


	@Override
	public boolean delete() {
		File[] files = this.file.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	                ResourceFactory.create(f).delete();
	        }
	    }
	    return file.delete();
	}

	@Override
	public ImageIcon getIcon() {
		return icon;
	}
	
}
