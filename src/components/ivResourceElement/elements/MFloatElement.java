package components.ivResourceElement.elements;

import components.ivResourceElement.AbstractResourceElement;

public class MFloatElement extends AbstractResourceElement<Float>{

	public MFloatElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MFloatElement(Float data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(AbstractResourceElement<Float> o) {
		if (o == null || o.getData() == null)
			return 1;
		return this.getData().compareTo(o.getData());
	}

}
