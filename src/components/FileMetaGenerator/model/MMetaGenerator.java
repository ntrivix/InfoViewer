package components.FileMetaGenerator.model;

import java.util.ArrayList;

import utilities.observer.Observable;
import utilities.observer.Observer;

public class MMetaGenerator implements Observable{
	
	private ArrayList<MColumnElement> columnElements = new ArrayList<MColumnElement>();
	private ArrayList<Observer> observers = new ArrayList<>();

	public MMetaGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void addElement (MColumnElement e) {
		columnElements.add(e);
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers(Object o, String command) {
		// TODO Auto-generated method stub
		
	}

}
