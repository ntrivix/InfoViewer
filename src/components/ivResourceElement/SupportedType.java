package components.ivResourceElement;


import java.sql.Date;

import components.ivResourceElement.elements.MBoolElement;
import components.ivResourceElement.elements.MCharElement;
import components.ivResourceElement.elements.MDateElement;
import components.ivResourceElement.elements.MFloatElement;
import components.ivResourceElement.elements.MIntElement;
import components.ivResourceElement.elements.MStringElement;

public enum SupportedType {
	INT("Integer", Integer.class, MIntElement.class, java.sql.Types.INTEGER), 
	SMALL_INT("Small Int", Byte.class, MIntElement.class,java.sql.Types.SMALLINT),
	VARCHAR("String", String.class, MStringElement.class,java.sql.Types.VARCHAR),
	BOOLEAN("Boolean", Boolean.class,MBoolElement.class,java.sql.Types.BOOLEAN),
	DATETIME("DateTime",Date.class,MDateElement.class, java.sql.Types.TIMESTAMP),
	FLOAT("Float",Float.class, MFloatElement.class, java.sql.Types.FLOAT),
	CHAR("Char", String.class, MCharElement.class, java.sql.Types.VARCHAR),
	DATE("Date-time", Date.class, MDateElement.class, java.sql.Types.DATE);
	
	
	private String name;
	private Class realType;
	private Class sClass;
	private int sqlType;
	SupportedType(String name, Class rType, Class sClass, int sql) {
		this.name = name;
		this.realType = rType;
		this.sClass = sClass;
		this.sqlType = sql;
	}
	
	public int sqlType(){
		return sqlType;
	}
	
	public String getName() {
		return name;
	}

	public Class getRealClass() {
		return realType;
	}
	
	public Class getSClass(){
		return sClass;
	}
	
	public boolean checkDataType(Object o){
		if (o.getClass().equals(realType))
			return true;
		return false;
	}
	
	public static SupportedType getStypeByClass(Class c){
		if (c.equals(INT.getRealClass()))
			return INT;
		if (c.equals(SMALL_INT.getRealClass()))
			return SMALL_INT;
		if (c.equals(VARCHAR.getRealClass()))
			return VARCHAR;
		if (c.equals(BOOLEAN.getRealClass()))
			return BOOLEAN;
		if (c.equals(CHAR.getRealClass()))
			return CHAR;
		if (c.equals(FLOAT.getRealClass()))
			return FLOAT;
		if (c.equals(DATE.getRealClass()))
			return DATE;
		return null;
	}
	
	public static SupportedType getStypeByClassName(String c){
		if (c.equals(INT.getName()))
			return INT;
		if (c.equals(SMALL_INT.getName()))
			return SMALL_INT;
		if (c.equals(VARCHAR.getName()))
			return VARCHAR;
		if (c.equals(BOOLEAN.getName()))
			return BOOLEAN;
		if (c.equals(CHAR.getName()))
			return CHAR;
		if (c.equals(FLOAT.getName()))
			return FLOAT;
		if (c.equals(DATE.getName()))
			return DATE;
		return null;
	}

}
