package components.metadata;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.json.JSONArray;
import org.json.JSONObject;

import components.ivResourceElement.SupportedType;

public class MMetaData extends DefaultMutableTreeNode implements IMetaData {

	private ArrayList<MSingleMetaD> md = new ArrayList<>();
	private String name = "";
	private String readableName;
	
	private String primaryKeyName;

	public String getReadableName() {
		return readableName;
	}

	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}

	@Override
	public int getColumsCount() {
		return md.size();
	}

	@Override
	public ArrayList<MSingleMetaD> getAll() {
		return md;
	}

	public int getColPosition(MSingleMetaD col) {
		return md.indexOf(col);
	}
	
	public int getMsingleMdPosition(MSingleMetaD md){
		int i = 0;
		for (MSingleMetaD m : this.md) {
			if (m.equals(md))
				return i;
			i++;
		}
		return -1;
	}

	public Class getColSType(int index) {
		if (md.size() > index) {
			return md.get(index).getType().getSClass();
		}
		return null;
	}

	public Class getColRealType(int index) {
		if (md.size() > index) {
			return md.get(index).getType().getRealClass();
		}
		return null;
	}

	public MMetaData(String json) {
		JSONObject obj = new JSONObject(json);
		JSONArray arr = obj.getJSONArray("meta"); // unutar mete se nalazi niz
													// sa parametrima
		for (int i = 0; i < arr.length(); i++) {
			String key = arr.getJSONObject(i).getString("key");
			String name = arr.getJSONObject(i).getString("name");
			String type = arr.getJSONObject(i).getString("type");
			Boolean isIndex = arr.getJSONObject(i).getBoolean("isIndex");
			Boolean isUnique = arr.getJSONObject(i).getBoolean("isUnique");
			Boolean isPrimary = arr.getJSONObject(i).getBoolean("isPrimary");
			Boolean isMandatory = arr.getJSONObject(i).getBoolean("isMandatory");
			this.addNewMetaD(new MSingleMetaD(SupportedType.getStypeByClassName(type).getRealClass(), key, name,
					isUnique, isPrimary, isMandatory));

		}

		this.setName(obj.getString("table_name"));

	}

	public MMetaData(ArrayList<MSingleMetaD> mSingleMetaData) {
		this.md = mSingleMetaData;
		for (MSingleMetaD mSingleMetaD : mSingleMetaData) {
			this.add(mSingleMetaD);
		}
	}

	public MMetaData() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addNewMetaD(MSingleMetaD<Object> meta) {
		this.md.add(meta);
		this.add(meta);
	}

	public ArrayList<MSingleMetaD> getMetaD() {
		return md;
	}

	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("table_name", name);
		JSONArray objects = new JSONArray();
		for (MSingleMetaD md : getMetaD()) {
			JSONObject tmd = new JSONObject();
			tmd.put("key", md.getKey());
			tmd.put("name", md.getName());
			tmd.put("type", md.getType().getName());
			tmd.put("isIndex", md.isIndex());
			tmd.put("isUnique", md.isUnique());
			tmd.put("isPrimary", md.isPrimary());
			tmd.put("isMandatory", md.isMandatory());

			objects.put(tmd);
		}
		obj.put("meta", objects);
		return obj.toString();
	}

	@Override
	public MSingleMetaD getMetaD(int index) {
		if (md.size() > index) {
			return md.get(index);
		}
		return null;
	}

	public int[] getUniqueCols() {
		ArrayList<Integer> unq = new ArrayList<>();
		int i = 0;
		for (MSingleMetaD mSingleMetaD : md) {
			if (mSingleMetaD.isUnique()) {
				unq.add(i);
			}
			i++;
		}
		int[] res = new int[unq.size()];
		i = 0;
		for (Integer uk : unq) {
			res[i++] = uk;
		}
		return res;
	}

	public ArrayList<MSingleMetaD> getPrimaryKeys() {
		ArrayList<MSingleMetaD> keys = new ArrayList<>();
		for (MSingleMetaD mSingleMetaD : this.md) {
			if (mSingleMetaD.isPrimary())
				keys.add(mSingleMetaD);
		}
		return keys;
	}

	public ArrayList<MSingleMetaD> getIndexes() {
		ArrayList<MSingleMetaD> keys = new ArrayList<>();
		for (MSingleMetaD mSingleMetaD : this.md) {
			if (mSingleMetaD.isIndex())
				keys.add(mSingleMetaD);
		}
		return keys;
	}
	
	

	public String getPrimaryKeyName() {
		return primaryKeyName;
	}
	
	private DefaultMutableTreeNode pkNode;

	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
		if (pkNode != null)
			this.remove(pkNode);
		pkNode = new DefaultMutableTreeNode("Primary key: "+primaryKeyName);
		for (MSingleMetaD pk : getPrimaryKeys()) {
			pkNode.add(new DefaultMutableTreeNode(pk.getName()+" ("+pk.getKey()+")"));
		}
		this.add(pkNode);
	}

	@Override
	public String toString() {
		if (readableName == null || readableName.equals(""))
			return name;
		return readableName;
	}

	@Override
	public MSingleMetaD getMetaD(String colName) {
		// TODO Auto-generated method stub
		for (MSingleMetaD object : this.md) {
			if (object.getKey().equals(colName)) 
				return object;
		}
		return null;
	}

}
