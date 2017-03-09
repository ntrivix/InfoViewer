package components.mainAppFrame.model;

import java.awt.Dimension;
import java.util.ArrayList;

import components.fileNavigator.model.MFileNavigator;
import components.ivResource.utilities.interfaces.IivResource;
import configs.AppConfigs;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class MMainAppFrame implements Observable {

	private static MMainAppFrame instance;
	
	public static MMainAppFrame getInstance(){
		return (instance == null) ? instance = new MMainAppFrame() : instance;
	}
	
	private String appName;
	private Dimension dimension;
	private IivResource active;
	
	private MFileNavigator mFileNavigator;

	private MMainAppFrame() {
		appName = AppConfigs.NAME;
		dimension = new Dimension(AppConfigs.WIDTH,AppConfigs.HEIGHT);
		
		mFileNavigator = new MFileNavigator();
		active = null;
	}

	public String getAppName() {
		return appName;
	}
	
	public Dimension getDimension(){
		return dimension;
	}

	public MFileNavigator getmFileNavigator() {
		return mFileNavigator;
	}

	public IivResource getActive() {
		return active;
	}

	public void setActive(IivResource active) {
		this.active = active;
		System.out.println(active);
		notifyObservers(active, "active_element");
		//ako je null onemoguci neke akcije
	}

	
	ArrayList<Observer> observer = new ArrayList<>();
	@Override
	public void addObserver(Observer o) {
		observer.add(o);		
	}

	@Override
	public void notifyObservers(Object o, String command) {
		for (Observer obs : observer) {
			obs.update(this, o, command);
		}
	}
	
	
	
	
	
}
