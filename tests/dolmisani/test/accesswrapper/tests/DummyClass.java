package dolmisani.test.accesswrapper.tests;

public class DummyClass {

	private int intField;
	private String stringField;
	
	public DummyClass() {
		// nothing to do
	}

	private void m() {
		return;
	}
	
	private void m1(int p) {
		return;
	}
	
	private void m2(Integer p) {
		return;
	}
	
	
	public int getIntField() {
		return intField;
	}
	
	public void setIntField(int value) {
		this.intField = value;
	}
	
	public String getStringField() {
		return stringField;
	}
	
	public void setStringField(String value) {
		this.stringField = value;
	}

}
