package components.metadata;

import java.util.ArrayList;

public interface IMetaData {
	public int getColumsCount();
	public ArrayList<MSingleMetaD> getAll();
	public MSingleMetaD getMetaD(int index);
	public MSingleMetaD getMetaD(String colName);
}
