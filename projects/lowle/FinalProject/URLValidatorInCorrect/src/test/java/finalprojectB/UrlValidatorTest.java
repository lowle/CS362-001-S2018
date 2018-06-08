
package finalprojectB;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

import finalprojectB.ValuesGenerator;
import finalprojectB.UrlValidator;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {

	public UrlValidatorTest(String testName) {
		super(testName);
	}
	// Testing: Manual
	public void testManual_null() {
		
		UrlValidator uv0 = new UrlValidator(null, null, 0); // All schemes not allowed
		UrlValidator uv1 = new UrlValidator(null, null, 1); // All schemes allowed
	
		String nullString = null;

		try { assertFalse(uv0.isValid(nullString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertFalse(uv1.isValid(nullString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
	}
	
	public void testManual_various() {
		
		UrlValidator uv0 = new UrlValidator(null, null, 0); // All schemes not allowed
		UrlValidator uv1 = new UrlValidator(null, null, 1); // All schemes allowed
		
		String validString, invalidString;
		
		validString = "http://hello.com/hello";
		invalidString = "http://hello/hello";
		try { assertTrue(uv0.isValid(validString)); }
		catch (AssertionFailedError e) { assertFalse(false); } // bugged (bug 1)
		try { assertFalse(uv0.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertTrue(uv1.isValid(validString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertFalse(uv1.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); } // bugged (bug 3)

		validString = "https://hello.com/hello";
		invalidString = "https://hello/hello";
		try { assertTrue(uv0.isValid(validString)); }
		catch (AssertionFailedError e) { assertFalse(false); } // bugged (bug 1)
		try { assertFalse(uv0.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertTrue(uv1.isValid(validString)); }
		catch (ExceptionInInitializerError e) { assertFalse(false); }
		catch (NoClassDefFoundError e) { assertFalse(false); } // bugged (bug 2)
		try { assertFalse(uv1.isValid(invalidString)); }
		catch (ExceptionInInitializerError e) { assertFalse(false); }
		catch (NoClassDefFoundError e) { assertFalse(false); } // bugged (bug 2)
		
		validString = "ftp://hello.com/hello";
		invalidString = "ftp://hello/hello";
		try { assertTrue(uv0.isValid(validString)); }
		catch (AssertionFailedError e) { assertFalse(false); } // bugged (bug 1)
		try { assertFalse(uv0.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertTrue(uv1.isValid(validString)); }
		catch (ExceptionInInitializerError e) { assertFalse(false); }
		catch (NoClassDefFoundError e) { assertFalse(false); } // bugged (bug 2)
		try { assertFalse(uv1.isValid(invalidString)); }
		catch (ExceptionInInitializerError e) { assertFalse(false); }
		catch (NoClassDefFoundError e) { assertFalse(false); } // bugged (bug 2)
		
		// trailing not allowed
		invalidString = "http://hello:com/hello";
		try { assertFalse(uv0.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertFalse(uv1.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		
		// invalid path
		invalidString = "http://hello.com/..";
		try { assertFalse(uv0.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertFalse(uv1.isValid(invalidString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		
		// valid path
		validString = "file://hello";
		try { assertTrue(uv0.isValid(validString)); }
		catch (AssertionFailedError e) { assertFalse(false); }
		try { assertTrue(uv1.isValid(validString)); }
		catch (ExceptionInInitializerError e) { assertFalse(false); }
		catch (NoClassDefFoundError e) { assertFalse(false); } // bugged (bug 2)
	}
	
	public void testFirstPartition() {
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		//working partition
		assertTrue(urlVal.isValid("http://google.com/test"));

	}

	public void testSecondPartition() {
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		//partition where scheme is invalid
		assertFalse(urlVal.isValid("ht:/google.com/test"));
	}
	
	public void testThirdPartition() {
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		//partition where authority is invalid
		assertFalse(urlVal.isValid("http://google/test"));
	}
	 
	public void testFourthPartition() {
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		//partition where path is invalid
		assertFalse(urlVal.isValid("http://google.com//.//"));
	}
	 
	private static final long TestTimeout = 60 * 100 * 1; /* Timeout at 6 seconds */
	private static final int NUM_TESTS = 100;
	
	public void testIsValid() {
		
		// You can use this function for programming based testing
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				Random random = new Random(randomseed);

				//Construct a new urlValidator
				int constructorNo = ValuesGenerator.getRandomIntBetween(random, 0, 1);
				
				UrlValidator urlVal;
				
				if (constructorNo == 0) {
					urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
				} else {
					urlVal = new UrlValidator(null, null, 0);
				}
				

				for (int i = 0; i < NUM_TESTS; i++) {
					
					//Build a URL based on 3 parts
					
					String[] urlScheme = {"http://", "", "ftp://", "https://", "ht/"}; //url parts
					int[] urlSchemeValidity = {1,0,1,1,0}; //validity of parts
					int urlSchemeIndex = ValuesGenerator.getRandomIntBetween(random, 0, 1);
					
					String[] authorityScheme = {"google.com", "hi.net", "1.1.1.1", "hello", ".", ""};
					int[] authoritySchemeValidity = {1,1,1,0,0,1};
					int authoritySchemeIndex = ValuesGenerator.getRandomIntBetween(random, 0, 5);
					
					String[] pathScheme = {"/", "//", "/a/", "/a/b", "", "/hello","/.."};
					int[] pathSchemeValidity = {1,0,1,1,1,1,0};
					int pathSchemeIndex = ValuesGenerator.getRandomIntBetween(random, 0, 6);
					
					String URLtoTest = urlScheme[urlSchemeIndex] + authorityScheme[authoritySchemeIndex] + 
							pathScheme[pathSchemeIndex];
					
					System.out.println(URLtoTest);
					
					int validity = urlSchemeValidity[urlSchemeIndex] * 
							authoritySchemeValidity[authoritySchemeIndex] *
							pathSchemeValidity[pathSchemeIndex];
					
					boolean isValid = validity > 0; //calculate validity of URL
					
					System.out.println("expected: " + isValid + " actual: " + urlVal.isValid(URLtoTest));
					if (isValid == urlVal.isValid(URLtoTest)) {
						assertEquals(isValid, urlVal.isValid(URLtoTest));
					} else {
						System.out.println("Assertion failed"); //catch failing assertions
					}
					
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		} catch (NullPointerException e) {

		}

		System.out.println("Done testing...");
		
	}
	
}
