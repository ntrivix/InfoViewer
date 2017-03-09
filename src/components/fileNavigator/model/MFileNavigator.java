package components.fileNavigator.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import components.ivResource.ivFolder.model.MivFolder;
import components.ivResource.utilities.ResourceFactory;
import components.ivResource.utilities.abstractIvResource.model.AbstractMivResource;
import components.ivResource.utilities.interfaces.IivResource;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class MFileNavigator extends AbstractListModel<IivResource> implements Observable {
	
	/**
	 * Model navigatora za fajlove
	 */
	private static final long serialVersionUID = 2693256389999782578L;
	
	private ArrayList<AbstractMivResource> files = new ArrayList<>();
	private File tLocation;
	
	private ArrayList<Observer> observers = new ArrayList<>();
	
	public MFileNavigator(){
		super();
		goToRoot();
	}
	
	public MFileNavigator(String[] paths) {
		super();
		this.addPaths(paths);
	}
	
	public MFileNavigator(File[] files){
		this.addFiles(files);
	}
	
	public void setLocation(File file){
		if (file != null && file.isDirectory() && file.exists() && file.canRead()){
			this.tLocation = file;			
			this.clearAll();
			this.addBackPath();	
			this.addFiles(file.listFiles());
		} else 
			if (file == null || file.getPath().contains("root")){
				this.tLocation = null;
				this.clearAll();
				this.goToRoot();
			}
		notifyObservers(this, "update_path");
	}
	
	private void addFiles(File[] f){
		if (f == null)
			return;
		for (File file : f) {
			this.addFile(file);
		}
	}
	
	private void addPaths(String[] p){
		if (p == null)
			return;
		for (String fi : p) {
			this.addPath(fi);
		}
	}
	
	private boolean addPath(String p){
		File f = new File(this.tLocation.getAbsolutePath().concat(File.separator+p));
		return this.addFile(f);
	}
	
	private boolean addFile(File f){
		if ((!f.exists() || !f.canRead() || f.isHidden() || !f.canWrite() || !f.canExecute()))
			return false;
		AbstractMivResource tmpFile = ResourceFactory.create(f);
		if (tmpFile != null) {
			files.add(tmpFile);
			fireIntervalAdded(this, files.size()-1, files.size()-1);
		}
		return true;
	}
	
	public void goToRoot(){
		File[] roots = File.listRoots();
		for (File file : roots) {
			files.add(ResourceFactory.create(file));
			fireIntervalAdded(this, files.size()-1, files.size()-1);
		}
	}
	
	private void addBackPath(){
		if (tLocation.getParentFile() == null)
			try {
				files.add(new MivFolder("root"+File.separator,"../"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				files.add(new MivFolder(tLocation.getPath()+File.separator+".."+File.separator,"../"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		fireIntervalAdded(this, files.size()-1, files.size()-1);
	}
	
	private void clearAll(){
		files = new ArrayList<>(); //resetuje se sadrzaj
		fireIntervalRemoved(this, 0, getSize());
	}

	@Override
	public IivResource getElementAt(int arg0) {
		return files.get(arg0);
	}

	@Override
	public int getSize() {
		return files.size();
	}

	@Override
	public String toString() {
		if (tLocation == null)
			return "";
		return tLocation.getAbsolutePath();
	}

	@Override
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	
	public void refresh(){
		this.setLocation(this.tLocation);
	}

	@Override
	public void notifyObservers(Object o, String command) {
		for (Observer observer : observers) {
			observer.update(this, o, command);
		}
	}

	public File gettLocation() {
		return tLocation;
	}
	
	
	

}
