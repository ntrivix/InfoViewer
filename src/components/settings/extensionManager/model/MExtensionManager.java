package components.settings.extensionManager.model;

import java.io.Serializable;
import java.util.ArrayList;

import components.settings.settingsManager.SettingsManager;
import configs.AppConfigs;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class MExtensionManager implements Serializable,Observable{
	
	private ArrayList<MExtension> extensions = new ArrayList<>();
	private static MExtensionManager instance;

	
	private MExtensionManager() {
		super();
		for (String ext : AppConfigs.predefinedExt) {
			addExtension(new MExtension(true, ext));
		}
	}

	public static MExtensionManager getInstance(){
		//if (SettingsManager.isInstanciated())
		//	return SettingsManager.getInstance().getExtensionManager();
		if (!SettingsManager.isInstanciated())
			instance = SettingsManager.getInstance().getExtensionManager();
		if (instance == null) {
			instance = new MExtensionManager();
		}
		return instance;
	}
	
	public void addExtension(MExtension e){
		if (e.getExtension().isEmpty() || contains(e)) return;
		extensions.add(e);
		notifyObservers(e, "AddExtension");
	}
	
	public void removeExtension(MExtension e){
		extensions.remove(e);
		notifyObservers(e, "RemoveExtension");
	}

	public ArrayList<MExtension> getExtensions() {
		return extensions;
	}

	public void disableExtension(MExtension e){
		e.setSelected(false);
	}
	
	public void enableExtension(MExtension e){
		e.setSelected(true);
	}
	
	public boolean contains(MExtension e){
		for (MExtension mExtension : extensions) {
			if (mExtension.getExtension().equalsIgnoreCase(e.getExtension())) return true;
		}
		return false;
	}
	
	public boolean containsSelected(String e){
		MExtension tmp = new MExtension(true, e);
		for (MExtension mExtension : extensions) {
			if (mExtension.getExtension().equalsIgnoreCase(tmp.getExtension())) return mExtension.isSelected();
		}
		return false;
	}

	@Override
	public void addObserver(Observer o) {
		try{
			observers.add(o);
		}catch (Exception e ){
			observers = new ArrayList<>();
			observers.add(o);
		}		
	}
	
	transient private ArrayList<Observer> observers = new ArrayList<>();

	@Override
	public void notifyObservers(Object o, String command) {
		try{
			for (Observer observer : observers) {
				observer.update(this, o, command);
			}
		}catch (Exception e ){
			observers = new ArrayList<>();
		}
		
	}

}
