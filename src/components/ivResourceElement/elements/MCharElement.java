package components.ivResourceElement.elements;

import components.ivResourceElement.AbstractResourceElement;

public class MCharElement extends AbstractResourceElement<String> {
	
	public MCharElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MCharElement(String data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(AbstractResourceElement<String> o) {
		if (o == null || o.getData() == null)
			return 1;
		return this.getData().compareTo(o.getData());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Char";
	}
	
	

}
