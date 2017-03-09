package components.settings.settingsManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import components.settings.extensionManager.model.MExtensionManager;
import configs.AppConfigs;

public class SettingsManager implements Serializable {
	private String location;
	private MExtensionManager extensionManager;
	private static SettingsManager file;
	private static boolean instancieted = false;
	
	private int rows_to_fetch = AppConfigs.DEFAULT_ROW_FETCH;
	
	private SettingsManager(String location) {
		this.location = location;
		instancieted = true;
		this.extensionManager = MExtensionManager.getInstance();
	}
	
	public static SettingsManager getInstance(){
		if (file == null) return open();
		return file;
	}

	public void save() {
		try {
			try {

			} catch (Exception t) {

			}
			ObjectOutputStream stream = new ObjectOutputStream(
					new FileOutputStream(new File(location + "/" + ".infoviewer")));
			stream.writeObject(this);
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static SettingsManager open() {
		file = null;
		String path = System.getProperty("user.home");
		ObjectInputStream stream;
		try {
			stream = new ObjectInputStream(new FileInputStream(path + "/" + ".infoviewer"));
			try {
				file = (SettingsManager) stream.readObject();
			} catch (ClassNotFoundException e) {
			}
			stream.close();
		} catch (FileNotFoundException e1) {
			file = new SettingsManager(path);
			file.save();
		} catch (IOException e1) {
			file = new SettingsManager(path);
			file.save();
		}
		return file;
	}

	public MExtensionManager getExtensionManager() {
		return extensionManager;
	}
	
	public int getRows_to_fetch() {
		return rows_to_fetch;
	}

	public static boolean isInstanciated(){
		if (!instancieted) return false;
		return true;
	}
}
