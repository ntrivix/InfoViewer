package components.newFile;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import components.settings.extensionManager.model.MExtension;
import components.settings.extensionManager.model.MExtensionManager;

public class VFileChooser extends JFileChooser {

	private static VFileChooser instance;

	private class CProjectFileFilter extends FileFilter {

		@Override
		public boolean accept(File arg) {
			//return (arg.isDirectory() || arg.getName().toLowerCase().endsWith(".pf"));
			if (arg.isDirectory())
					return true;
			for (MExtension ext : MExtensionManager.getInstance().getExtensions()) {
				if (ext.isSelected() && arg.getName().toLowerCase().endsWith("."+ext.getExtension()))
					return true;
			}
			return false;
		}

		@Override
		public String getDescription() {
			return "New File";
		}

	}

	public VFileChooser() {
		CProjectFileFilter p = new CProjectFileFilter();
		this.setFileFilter(p);
	}

}
