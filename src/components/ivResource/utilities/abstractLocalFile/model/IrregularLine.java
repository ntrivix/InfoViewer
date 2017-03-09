package components.ivResource.utilities.abstractLocalFile.model;

public class IrregularLine extends Exception {

	public IrregularLine(String line) {
		super();
		System.out.println("Linija nije validna: "+line);
	}
	
}
