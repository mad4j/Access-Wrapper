package dolmisani.test.accesswrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AccessWrapper<T> {

  private T target;

  public AccessWrapper(T target) {

    this.target = target;
  }

  /**
   * @param clazz
   * @param params
   * @return
   */
  public static <T> T create(Class<T> clazz, Object... params) {

    T instance = null;

    Class<?>[] signature = SignatureToolkit.getSignature(params);

    Constructor<T> field = null;
    try {

      field = clazz.getDeclaredConstructor(signature);

    } catch (NoSuchMethodException e) {

      field = SignatureToolkit.findCompatibleConstructor(clazz, signature);
      if (field == null) {
        throw new AccessException(e);
      }
    }

    try {

      field.setAccessible(true);
      instance = field.newInstance(params);

    } catch (SecurityException | InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      throw new AccessException(e);
    }

    return instance;
  }

  public static <T> AccessWrapper<T> createWithWrapper(Class<T> clazz, Object... params) {

    return new AccessWrapper<T>(create(clazz, params));
  }

  /**
   * @param fieldName
   * @return
   */
  @SuppressWarnings("unchecked")
  public <V> V get(String fieldName) {

    V value = null;

    Field field = null;
    try {

      field = target.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      value = (V) field.get(target);

    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      throw new AccessException(e);
    }

    return value;
  }

  /**
   * @param fieldName
   * @param value
   */
  public <V> void set(String fieldName, V value) {

    Field field = null;
    try {

      field = target.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(target, value);

    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      throw new AccessException(e);
    }
  }

  /**
   * @param methodName
   * @param params
   * @return
   */
  @SuppressWarnings({ "unchecked" })
  public <V> V invoke(String methodName, Object... params) {

    V value = null;

    Class<?>[] signature = SignatureToolkit.getSignature(params);

    Method method = null;
    try {

      method = target.getClass().getDeclaredMethod(methodName, signature);

    } catch (NoSuchMethodException e) {

      method = SignatureToolkit.findCompatibleMethod(target.getClass(), methodName, signature);
      if (method == null) {
        throw new AccessException(e);
      }

    } catch (SecurityException | IllegalArgumentException e) {
      throw new AccessException(e);
    }

    try {
      method.setAccessible(true);
      value = (V) method.invoke(target, params);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new AccessException(e);
    }

    return value;
  }
}
