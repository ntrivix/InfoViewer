package utilities.observer;

public interface Observer {
	public void update(Observable o, Object obj, String command);
}
