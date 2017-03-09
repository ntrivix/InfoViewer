package components.ivResourceElement.elements;

import components.ivResourceElement.AbstractResourceElement;

public class MIntElement extends AbstractResourceElement<Integer> {

	public MIntElement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MIntElement(Integer data) {
		super(data);
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int compareTo(AbstractResourceElement<Integer> o) {
		if (o == null || o.getData() == null)
			return 1;
		return this.getData().compareTo(o.getData());
	}

	

}
