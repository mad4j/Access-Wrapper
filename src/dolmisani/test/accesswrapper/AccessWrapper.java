package dolmisani.test.accesswrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class  AccessWrapper<T> {

	
	private T target;
	
	
	public AccessWrapper(T target) {
		
		this.target = target;
	}
	
	
	@SuppressWarnings("unchecked")
	public <V> V get(String fieldName) {
		
		V value = null;
		
		Field f = null;
		try {
			
			f = target.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			value = (V) f.get(target);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new AccessException(e);
		}
		
		return value;
	}
	
	
	public <V> void set(String fieldName, V value) {

		Field f = null;
		try {
			
			f = target.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			f.set(target, value);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new AccessException(e);
		}
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public <V> V invoke(String methodName, Object... params) {
		
		V value = null;
		
		Class<?>[] types = new Class[params.length];
		for (int i=0; i<types.length; i++) {
			types[i] = params[i].getClass();
		}
		
		Method m = null;
		try {
			
			m = target.getClass().getDeclaredMethod(methodName, types);
			m.setAccessible(true);
			value = (V) m.invoke(target, params);
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new AccessException(e);
		}
		
		return value;
	}
	

	private Method findMethodHeuristic(Class<?> c, Class<?>... types) {
		
		Method[] methods = c.getDeclaredMethods();
		
		for (Method m : methods) {
			
			Class<?>[] p = m.getParameterTypes();
		}
		
		
		return null;
	}
	
	private boolean isCompatibleSignature(Class<?>[] s1, Class<?>[] s2) {
		
		if (s1.length != s2.length) {
			return false;
		}
		
		for (int i=0; i<s1.length; i++) {
			
			//if (s1[i].equals(s2[i])) || (s2[i].isPrimitive() && s1[i].)
		}
		
		return true;
		
	}
}
