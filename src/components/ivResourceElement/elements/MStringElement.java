package components.ivResourceElement.elements;

import components.ivResourceElement.AbstractResourceElement;

public class MStringElement extends AbstractResourceElement<String> {

	public MStringElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MStringElement(String data) {
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

}
