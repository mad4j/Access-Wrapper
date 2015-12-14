/**
 * 
 */
package dolmisani.test.accesswrapper.tests;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dolmisani.test.accesswrapper.SignatureToolkit;


public class SignatureToolkitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWrapperTypeBoolean() {

		assertEquals(Boolean.class, SignatureToolkit.getWrapperType(boolean.class));
	}

	@Test
	public void testGetWrapperTypeByte() {

		assertEquals(Byte.class, SignatureToolkit.getWrapperType(byte.class));
	}
	
	@Test
	public void testGetWrapperTypeChar() {

		assertEquals(Character.class, SignatureToolkit.getWrapperType(char.class));
	}
	
	@Test
	public void testGetWrapperTypeDouble() {

		assertEquals(Double.class, SignatureToolkit.getWrapperType(double.class));
	}
	
	@Test
	public void testGetWrapperTypeFloat() {

		assertEquals(Float.class, SignatureToolkit.getWrapperType(float.class));
	}
	
	@Test
	public void testGetWrapperTypeInt() {

		assertEquals(Integer.class, SignatureToolkit.getWrapperType(int.class));
	}
	
	@Test
	public void testGetWrapperTypeLong() {

		assertEquals(Long.class, SignatureToolkit.getWrapperType(long.class));
	}
	
	@Test
	public void testGetWrapperTypeShort() {

		assertEquals(Short.class, SignatureToolkit.getWrapperType(short.class));
	}
	
	@Test
	public void testGetWrapperTypeVoid() {

		assertEquals(Void.class, SignatureToolkit.getWrapperType(void.class));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetWrapperTypeNullValue() {

		SignatureToolkit.getWrapperType(null);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testGetWrapperTypeNotPrimitiveValue() {

		SignatureToolkit.getWrapperType(Integer.class);
	}
	
	@Test
	public void testGetSignatureSinglePrimitiveParam() {

		int p1 = 10;
		Class<?>[] s = SignatureToolkit.getSignature(p1);
		
		assertEquals(1, s.length);
		assertArrayEquals(new Class<?>[] { Integer.class }, s);
	}
	
	@Test
	public void testGetSignatureSingleWrapperParam() {

		Integer p1 = 10;
		Class<?>[] s = SignatureToolkit.getSignature(p1);
		
		assertEquals(1, s.length);
		assertArrayEquals(new Class<?>[] { Integer.class }, s);
	}
	
	@Test
	public void testGetSignatureSingleObjectParam() {

		String p1 = "TEST";
		Class<?>[] s = SignatureToolkit.getSignature(p1);
		
		assertEquals(1, s.length);
		assertArrayEquals(new Class<?>[] { String.class }, s);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetSignatureNullValue() {
		
		SignatureToolkit.getSignature((Object[]) null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsBoxedSignatureNullValue() {
	
		Class<?>[] s = null;
		Method m = null;
		
		SignatureToolkit.isBoxedSignature(s, m);
	}
}
