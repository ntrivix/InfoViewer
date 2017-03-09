package components.ivResource.sekDat.model;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import components.ivResource.serialDat.model.MSerialDat;
import components.ivResource.utilities.abstractLocalFile.model.ALocalFileModel;
import components.ivResourceElement.ResourceRow;
import components.ivResourceElement.interfaces.IivResourceRow;
import components.metadata.MMetaData;
import utilities.MergeArranged;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class MSekDat extends ALocalFileModel implements Observable {

	MSerialDat toAdd = null;
	
	public MSekDat(File file) {
		super(file);
	}

	@Override
	public boolean save() {
		if (toAdd != null){
			this.notifyObservers(null, "hideBufferTable");
			toAdd.toSekDat(this.getMetaData());
			MergeArranged merge = new MergeArranged(this.getMetaData(), this.getCritByPrimaryKey(this.getMetaData()), this.file);
			merge.setIgnoreEqual(true);
			merge.setIgnoreEqual(true);
			merge.addToMerge(new MSerialDat(this.file));
			merge.addToMerge(toAdd);
			merge.merge();
			this.setMetaData(this.getMetaData());
			toAdd.delete();
			toAdd = null;
		}
		return true;
	}
	
	

	public MSerialDat getToAdd() {
		return toAdd;
	}

	@Override
	public boolean addNewRow(IivResourceRow r) {
		if (toAdd == null){
			
			toAdd = new MSerialDat(new File(this.file.getParent()+this.file.getName()+".tmp.ser"));
			toAdd.setMetaData(this.getMetaData());
			this.notifyObservers(toAdd, "showBufferTable");
			toAdd.read();
		}
		toAdd.addNewRow(r);
		return false;
	}

	@Override
	public ResourceRow[] find(ResourceRow r, int returnLimit) {
		
		return null;
	}

	@Override
	public ImageIcon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private ArrayList<Observer> observer = new ArrayList<>();
	@Override
	public void addObserver(Observer o) {
		observer.add(o);
	}

	@Override
	public void notifyObservers(Object o, String command) {
		for (Observer obs : this.observer) {
			obs.update(this, o, command);
		}
	}

}
