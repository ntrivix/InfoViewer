package components.ivResourceElement.elements;

import components.ivResourceElement.AbstractResourceElement;

public class MBoolElement extends AbstractResourceElement<Boolean> {
	
	public MBoolElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MBoolElement(Boolean data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(AbstractResourceElement<Boolean> o) {
		if (o == null || o.getData() == null)
			return 1;
		return this.getData().compareTo(o.getData());
	}

}
