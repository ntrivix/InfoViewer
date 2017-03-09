package components.ivResourceElement.elements;



import java.sql.Date;

import components.ivResourceElement.AbstractResourceElement;

public class MDateElement extends AbstractResourceElement<Date>{

	public MDateElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MDateElement(Date data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(AbstractResourceElement<Date> o) {
		if (o == null || o.getData() == null)
			return 1;
		return this.getData().compareTo(o.getData());
	}

}
