package dolmisani.test.accesswrapper;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.type.PrimitiveType;

public class SignatureToolkit {

	private static final Map<Class<?>, Class<?>> WRAPPER_TYPES;
	
	static {
		
		HashMap<Class<?>, Class<?>> temp = new HashMap<>(9);
		
		temp.put(boolean.class, Boolean.class);
		temp.put(byte.class, Byte.class);
		temp.put(char.class, Character.class);
		temp.put(double.class, Double.class);
		temp.put(float.class, Float.class);
		temp.put(int.class, Integer.class);
		temp.put(long.class, Long.class);
		temp.put(short.class, Short.class);
		temp.put(void.class, Void.class);
		
		WRAPPER_TYPES = Collections.unmodifiableMap(temp);
	}
	
	
	public SignatureToolkit() {
		// nothing to do
	}
	
	
	public static Class<?> getWrapperType(Class<?> primitiveType) {
		
		if (primitiveType == null) {
			throw new NullPointerException();
		}
		
		if (!primitiveType.isPrimitive()) {
			throw new InvalidParameterException();
		}
		
		return WRAPPER_TYPES.get(primitiveType);
	}

}
