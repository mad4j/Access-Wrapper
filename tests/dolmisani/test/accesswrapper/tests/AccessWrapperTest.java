package dolmisani.test.accesswrapper.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dolmisani.test.accesswrapper.AccessException;
import dolmisani.test.accesswrapper.AccessWrapper;


public class AccessWrapperTest {

	
	private AccessWrapper<DummyClass> w;
	private DummyClass d;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Before
	public void setUp() throws Exception {
		
		d = DummyClass.create();
		w = new AccessWrapper<DummyClass>(d);
	}

	
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testConstructorNoParams() {
		
		DummyClass d = AccessWrapper.create(DummyClass.class);
		assertNotNull(d);
	}
	
	
	@Test
	public void testContructionIntParam() {
		
		DummyClass d = AccessWrapper.create(DummyClass.class, 100);
		assertNotNull(d);
		assertTrue(d.getIntField() == 100);
	}
	
	
	@Test
	public void testSetInt() {
		
		w.set("intField", 10);
		assertEquals(10, d.getIntField());
	}

	
	@Test
	public void testGetInt() {
		
		d.setIntField(10);
		assertEquals(10, (int) w.get("intField"));
	}
	
	
	@Test
	public void testSetString() {
		
		w.set("stringField", "TEST");
		assertEquals("TEST", d.getStringField());
	}
	
	
	@Test
	public void testGetString() {
		
		d.setStringField("TEST");
		assertEquals("TEST", w.get("stringField"));
	}
	
	
	@Test
	public void testSetStringNullValue() {
		
		w.set("stringField", null);
		assertEquals(null, d.getStringField());
	}

	
	@Test
	public void testGetStringNullValue() {
		
		d.setStringField(null);
		assertEquals(null, (String) w.get("stringField"));
	}
	
	
	@Test(expected=AccessException.class)
	public void testSetWrongField() {
		
		w.set("XXX", null);
	}
	
	
	@Test(expected=AccessException.class)
	public void testGetWrongField() {
		
		w.get("XXX");
	}
	
	
	@Test
	public void testMethodCallM0() {
		
		w.invoke("m");
	}
	
	
	@Test
	public void testMethodCallM1() {
		
		w.invoke("m1", Integer.valueOf(10));
	}
	
	
	@Test
	public void testMethodCallM2() {
		
		w.invoke("m2", 10);
	}
}
