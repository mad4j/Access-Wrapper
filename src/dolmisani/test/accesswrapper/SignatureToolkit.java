package dolmisani.test.accesswrapper;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


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
		
		if (!primitiveType.isPrimitive()) {
			throw new InvalidParameterException(String.format("%s", primitiveType.getName()));
		}
		
		return WRAPPER_TYPES.get(primitiveType);
	}
	
	
	public static Class<?>[] getSignature(Object... params) {
		
		Class<?>[] signature = new Class<?>[params.length];
		for (int i=0; i<signature.length; i++) {
			signature[i] = params[i].getClass();
		}
		
		return signature;
	}
	
	
	public static boolean isBoxedSignature(Class<?>[] signature, Method m) {
		
		return isBoxedSignature(signature, m.getParameterTypes());
	}
	
	
	public static boolean isBoxedSignature(Class<?>[] s1, Class<?>[] s2) {
		
		if (s1.length != s2.length) {
			return false;
		}
		
		for (int i=0; i<s1.length; i++) {
			
			if ((s1[i].equals(s2[i])) || (s1[i].equals(SignatureToolkit.getWrapperType(s2[i])))) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Method findCompatibleMethod(String methodName, Class<?>[] signature, Class<?> c) {
		
		for (Method m : c.getDeclaredMethods()) {
			if ((m.getName().equals(methodName)) && isBoxedSignature(signature, m)) {
				return m;
			}
		}
		
		return null;
	}
}
