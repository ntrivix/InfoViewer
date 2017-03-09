package components.ivResourceElement.interfaces;

import components.ivResourceElement.AbstractResourceElement;

public interface IivResourceRow {
	public AbstractResourceElement getElement(String key);
	public AbstractResourceElement getElement(int i);
	public int getColCount();
	public String toIvString();
}
