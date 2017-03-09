package utilities.observer;

public interface Observable {
	public void addObserver(Observer o);
	public void notifyObservers(Object o, String command);
}
