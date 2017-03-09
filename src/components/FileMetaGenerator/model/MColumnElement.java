package components.FileMetaGenerator.model;

import java.util.ArrayList;

import components.ivResourceElement.SupportedType;
import utilities.observer.Observable;
import utilities.observer.Observer;

public class MColumnElement implements Observable{
	
	private String key;
	private String title;
	private SupportedType type;
	private ArrayList<Observer> observers = new ArrayList<>();

	public MColumnElement(String title, SupportedType type) {
		super();
		this.title = title;
		this.type = type;
	}
	
	public void editTitle (String newTitle){
		this.title = newTitle;
	}

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers(Object o, String command) {
		// TODO Auto-generated method stub
		
	}

}
