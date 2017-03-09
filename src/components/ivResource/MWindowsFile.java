package components.ivResource;

import java.io.File;

import javax.swing.ImageIcon;

import components.ivResource.utilities.abstractIvResource.model.MAbstractResourceAdapter;
import utilities.ResourceLoader;

public class MWindowsFile extends MAbstractResourceAdapter{
	
	private static ImageIcon imageIcon = ResourceLoader.getImageIcon("icon/page.png",20,20);
	private File file;

	public MWindowsFile(File file) {
		super(file);
		this.file = file;
	}

	@Override
	public ImageIcon getIcon() {
		return imageIcon;
	}

	@Override
	public File getLocation() {
		return file;
	}
	
	@Override
	public String toString() {
		return file.getName();
	}

	@Override
	public boolean delete() {
		return this.file.delete();
	}

	
	
}
