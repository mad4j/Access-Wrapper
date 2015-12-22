package dolmisani.test.accesswrapper.examples;

import java.lang.reflect.Field;

import dolmisani.test.accesswrapper.tests.DummyClass;

public class CounterExample {

	private DummyClass d;
	
	
	private void getValues() {
		
		int intValue = -1;
		String stringValue = null;
		
		Field f = null;
		try {
			
			f = d.getClass().getDeclaredField("intField");
			f.setAccessible(true);
			
			intValue = (int) f.get(d);
			
			f = d.getClass().getDeclaredField("stringField");
			f.setAccessible(true);
			
			intValue = (int) f.get(d);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		System.out.println(intValue);
		System.out.println(stringValue);
	}
	
	public CounterExample() {
		new CounterExample().getValues();
	}

}
