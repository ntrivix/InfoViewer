package components.metadata;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import components.ivResourceElement.AbstractResourceElement;
import components.ivResourceElement.SupportedType;
import components.ivResourceElement.elements.MCharElement;
import utilities.Convert;

public class MSingleMetaD<T> extends DefaultMutableTreeNode {

	private final Class<T> base_type;
	private SupportedType type;
	private String key;
	private String name;
	private boolean isIndex = false, isUnique = false, isPrimary = false, isMandatory = false;
	private String tableName = null;
	
	private int size=-1;

	private DefaultMutableTreeNode nodeType;
	
	public MSingleMetaD(Class<T> typeParameterClass, String key, String name, boolean isIndex, boolean isUnique,
			boolean isPrimary, boolean isMandatory) {
		this(typeParameterClass, key, name);
		this.isIndex = isIndex;
		this.isUnique = isUnique;
		this.isPrimary = isPrimary;
		this.isMandatory = isMandatory;		
	}
	
	public MSingleMetaD(Class<T> typeParameterClass, String key, String name, boolean isUnique,
			boolean isPrimary,boolean isMandatory) {
		this(typeParameterClass, key, name);
		this.isUnique = isUnique;
		this.isPrimary = isPrimary;
		this.isMandatory = isMandatory;
		
	}

	public MSingleMetaD(Class<T> typeParameterClass, String key, String name) {
		super();
		this.base_type = typeParameterClass;
		this.key = key;
		this.name = name;
		type = SupportedType.getStypeByClass(typeParameterClass);
		if (type == null)
			System.out.println(typeParameterClass);
		nodeType = new DefaultMutableTreeNode("Type: "+type.getName());
		this.add(nodeType);
	}
	
	

	public void setType(SupportedType type) {
		this.type = type;
		this.remove(nodeType);
		nodeType = new DefaultMutableTreeNode("Type: "+type.getName());
		this.add(nodeType);
	}

	public String getName() {
		if (name==null || name=="") return key;
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIndex() {
		return isIndex;
	}

	public void setIndex(boolean isIndex) {
		this.isIndex = isIndex;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
	
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		this.remove(nodeType);
		nodeType = new DefaultMutableTreeNode("Type: "+type.getName()+" ("+size+")");
		this.add(nodeType);
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
		this.isMandatory = true;
	}
	
	

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		if (!isPrimary)
			this.isMandatory = isMandatory;
	}

	public SupportedType getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public Class<T> getBase_type() {
		return base_type;
	}
	
	public String getDescript(){
		String zv = new String("");
		if (this.isPrimary || this.isMandatory) zv = new String("*");
		 return this.toString()+zv+" ("+type.toString()+ "("+this.getSize()+"))";
	}

	private T parseString(String s) {
		return (T) Convert.changeType(s, base_type);
	}

	public AbstractResourceElement generateModel(String value) throws Exception {
		try {

			

			try {
				if (value == null){
					Constructor gConstructor = type.getSClass().getDeclaredConstructor();
					MCharElement m = new MCharElement();
					return (AbstractResourceElement) gConstructor.newInstance();
				}
				Constructor gConstructor = type.getSClass().getDeclaredConstructor(base_type);
				T rval;
				try {
					rval = parseString(value);
				} catch (Exception notConverted) {
					throw new Exception("Konverzija nije uspela");
				}
				return (AbstractResourceElement) gConstructor.newInstance(rval);

			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();
		ArrayList<Character> list = new ArrayList<>();
		if (this.isPrimary()) list.add('P');
		if (this.isMandatory) list.add('M');
		if (this.isIndex()) list.add('I');
		if (this.isUnique()) list.add('U');
		if (name.equals(""))  retString.append(key);
		else retString.append(name);
		if (list.size()>0){
			retString.append("(");
			for (Character character : list) {
				if (character.equals(list.get(list.size()-1)))
					retString.append(character);
				else retString.append(character+",");
			}
			retString.append(")");
		}
		return retString.toString();
	}
	
	public int getSqlType(){
		return type.sqlType();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	

}
