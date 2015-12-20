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
		
		Class<?>[] signature = SignatureToolkit.getSignature(params);
			
		Method m = null;
		try {
			
			m = target.getClass().getDeclaredMethod(methodName, signature);
	
		} catch (NoSuchMethodException e) {
			
			m = SignatureToolkit.findCompatibleMethod(methodName, signature, target.getClass());
			if (m == null) {
				throw new AccessException(e);
			}
			
		} catch (SecurityException | IllegalArgumentException e) {
			throw new AccessException(e);
		}
		
		try {
			m.setAccessible(true);
			value = (V) m.invoke(target, params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new AccessException(e);
		}
		
		return value;
	}
}
