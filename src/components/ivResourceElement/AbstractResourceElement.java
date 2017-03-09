package components.ivResourceElement;

import components.ivResourceElement.interfaces.IivResourceElement;

public abstract class AbstractResourceElement<E> implements IivResourceElement, Comparable<AbstractResourceElement<E>> {
	protected E data = null;
	
	public AbstractResourceElement(){
		
	}
	
	public AbstractResourceElement(E data) {
		super();
		this.data = data;
		
	}

	public void setData(E data) {
		this.data = data;
	}

	public E getData() {
		return data;
	}	

	@Override
	public SupportedType getSType() {
		return SupportedType.getStypeByClass(data.getClass());
	}

	@Override
	public Class getRealType() {
		return data.getClass();
	}

	@Override
	public String toString() {
		if (data == null)
			return "null";
		return data.toString();
	}
	
	
}
