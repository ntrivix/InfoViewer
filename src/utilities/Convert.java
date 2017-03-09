package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Convert {
    protected static Object invokeMethod(Object obj, String valMethod) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method intValue = obj.getClass().getMethod(valMethod);
        return intValue.invoke(obj);
    }

    protected static Object makeNew(Class type, Object obj) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor constructor = type.getConstructor(obj.getClass());
        return constructor.newInstance(obj);
    }

    public static boolean booleanValue(String str) {
        str = str.toLowerCase();
        return !str.isEmpty() && !str.equals("0") && !str.equals("no") && !str.equals("n") && !str.equals("false");
    }

    public static boolean booleanValue(Object obj) {
        Class srcType = obj.getClass();

        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return (Boolean)obj;
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return ((Byte)obj)!=0;
        else if (srcType.equals(char.class) || srcType.equals(Character.class))
            return ((Character)obj)!='\0';
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return ((Double)obj)!=0.0;
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return ((Float)obj)!=0.0;
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return ((Integer)obj)!=0.0;
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return ((Long)obj)!=0L;
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return ((Short)obj)!=0;
        else if(srcType.equals(String.class))
            return booleanValue(obj.toString());           
        else {
            try {
                return (Boolean)invokeMethod(obj, "booleanValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }

        return booleanValue(obj.toString());
    }

    public static byte byteValue(Object obj) {
        Class srcType = obj.getClass();
        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return ((Boolean)obj)?(byte)1:(byte)0;
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return ((Byte)obj);
        else if (srcType.equals(char.class) || srcType.equals(Character.class))
            return (byte)Character.getNumericValue((Character)obj);
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return ((Double)obj).byteValue();
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return ((Float)obj).byteValue();
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return ((Integer)obj).byteValue();
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return ((Long)obj).byteValue();
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return ((Short)obj).byteValue();
        else if(srcType.equals(String.class))
            return Byte.parseByte((String)obj);
        else {
            try {
                return (Byte)invokeMethod(obj,"byteValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }           

        return Byte.parseByte(obj.toString());
    }

    public static char charValue(Object obj) {
        Class srcType = obj.getClass();

        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return ((Boolean)obj)?'\0':'Y';
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return Character.forDigit((Byte)obj,10);
        else if (srcType.equals(char.class) || srcType.equals(Character.class))
            return (Character)obj;
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return Character.forDigit(((Double)obj).intValue(),10);
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return Character.forDigit(((Float)obj).intValue(),10);
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return Character.forDigit((Integer)obj,10);
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return Character.forDigit(((Long)obj).intValue(),10);
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return Character.forDigit((Short)obj,10);
        else if(srcType.equals(String.class))
            return ((String)obj).charAt(0);
        else {
            try {
                return (Character)invokeMethod(obj, "charValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }           

        return obj.toString().charAt(0);
    }

    public static double doubleValue(Object obj) {
        Class srcType = obj.getClass();
        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return ((Boolean)obj)?1.0:0.0;
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return ((Byte)obj).doubleValue();
        else if (srcType.equals(char.class) || srcType.equals(Character.class))
            return Character.getNumericValue((Character)obj);
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return ((Double)obj).doubleValue();
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return ((Float)obj).doubleValue();
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return ((Integer)obj).doubleValue();
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return ((Long)obj).doubleValue();
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return ((Short)obj).doubleValue();
        else if(srcType.equals(String.class))
            return Double.parseDouble((String)obj);
        else {
            try {
                return (Double)invokeMethod(obj, "doubleValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }       

        return Double.parseDouble(obj.toString());
    }

    public static float floatValue(Object obj) {
        Class srcType = obj.getClass();

        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return ((Boolean)obj)?1.0f:0.0f;
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return ((Byte)obj);
        else if (srcType.equals(char.class) || srcType.equals(Character.class))
            return Character.getNumericValue((Character)obj);
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return ((Double)obj).floatValue();
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return ((Float)obj);
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return ((Integer)obj).floatValue();
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return ((Long)obj);
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return ((Short)obj);
        else if(srcType.equals(String.class))
            return Float.parseFloat((String)obj);
        else {
            try {
                return (Float)invokeMethod(obj, "floatValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }

        return Float.parseFloat(obj.toString());
    }

    public static int intValue(Object obj) {
        Class srcType = obj.getClass();

        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return ((Boolean)obj)?1:0;
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return ((Byte)obj).intValue();
        else if (srcType.equals(char.class) || srcType.equals(Character.class)) {
            return Character.getNumericValue((Character)obj);
        }
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return ((Double)obj).intValue();
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return ((Float)obj).intValue();
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return ((Integer)obj).intValue();
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return ((Long)obj).intValue();
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return ((Short)obj).intValue();
        else if(srcType.equals(String.class))
            return Integer.parseInt((String)obj);
        else {
            try {
                return (Integer)invokeMethod(obj, "intValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }    

        return Integer.parseInt(obj.toString());
    }

    public static long longValue(Object obj) {
        Class srcType = obj.getClass();

        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return ((Boolean)obj)?1:0;
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return ((Byte)obj);
        else if (srcType.equals(char.class) || srcType.equals(Character.class))
            return Character.getNumericValue((Character)obj);
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return ((Double)obj).longValue();
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return ((Float)obj).longValue();
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return ((Integer)obj);
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return ((Long)obj);
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return ((Short)obj);
        else if(srcType.equals(String.class))
            return Long.parseLong((String)obj);
        else {
            try {
                return (Long)invokeMethod(obj, "longValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }       

        return Long.parseLong(obj.toString());
    }

    public static short shortValue(Object obj) {
        Class srcType = obj.getClass();

        if (srcType.equals(boolean.class)||srcType.equals(Boolean.class))
            return ((Boolean)obj)?(short)1:(short)0;
        else if (srcType.equals(byte.class) || srcType.equals(Byte.class))
            return ((Byte)obj).shortValue();
        else if (srcType.equals(char.class) || srcType.equals(Character.class))
            return (short)Character.getNumericValue((Character)obj);
        else if (srcType.equals(double.class) || srcType.equals(Double.class))
            return ((Double)obj).shortValue();
        else if (srcType.equals(float.class) || srcType.equals(Float.class))
            return ((Float)obj).shortValue();
        else if (srcType.equals(int.class) || srcType.equals(Integer.class))
            return ((Integer)obj).shortValue();
        else if (srcType.equals(long.class) || srcType.equals(Long.class))
            return ((Long)obj).shortValue();
        else if (srcType.equals(short.class) || srcType.equals(Short.class))
            return ((Short)obj);
        else if(srcType.equals(String.class))
            return Short.parseShort((String)obj);
        else {
            try {
                return (Short)invokeMethod(obj, "shortValue");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
        }

        return Short.parseShort(obj.toString());
    }    
    
    public static Date dateValue(Object obj){
    	
    	DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
    	Date date = null;
		try {
			java.util.Date d = format.parse(obj.toString());
			date = new Date(d.getTime()) ;
		} catch (ParseException e) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d;
			try {
				d = format.parse(obj.toString());
				date = new Date(d.getTime()) ;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
		}
    	return date;
    }

    public static Object changeType (Object obj, Class type) {
        if (obj.getClass().equals(type)) return obj;

        if (type.equals(boolean.class)||type.equals(Boolean.class))
            return booleanValue(obj);
        else if (type.equals(byte.class) || type.equals(Byte.class))
            return byteValue(obj);
        else if (type.equals(char.class) || type.equals(Character.class))
            return charValue(obj);
        else if (type.equals(double.class) || type.equals(Double.class))
            return doubleValue(obj);
        else if (type.equals(float.class) || type.equals(Float.class))
            return floatValue(obj);
        else if (type.equals(int.class) || type.equals(Integer.class))
            return intValue(obj);
        else if (type.equals(Date.class))
        	return dateValue(obj);
        else if (type.equals(long.class) || type.equals(Long.class))
            return longValue(obj);
        else if (type.equals(short.class) || type.equals(Short.class))
            return shortValue(obj);
        else if(type.equals(String.class))
            return obj.toString();
        else {
            try {
                invokeMethod(obj,type.getName()+"Value");
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }

            try {
                invokeMethod(obj,"to"+type.getName());
            } catch (NoSuchMethodException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }
            try {
                return makeNew(type, obj);
            } catch (NoSuchMethodException ex) {
            } catch (InstantiationException ex) {
            } catch (IllegalAccessException ex) {
            } catch (IllegalArgumentException ex) {
            } catch (InvocationTargetException ex) {
            }            
        }          

        return type.cast(obj);
    }
}
