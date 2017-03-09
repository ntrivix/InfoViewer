package components.ivResourceElement.interfaces;

import components.ivResourceElement.SupportedType;

public interface IivResourceElement {
	public boolean update();
	//public boolean delete();
	public String toString();
	public SupportedType getSType();
	public Class getRealType();
}
