/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;

public class ApptTest {

	@Test(timeout = 4000)
	public void test00() throws Throwable {
		Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		String string0 = appt0.toString();
		assertEquals(2, appt0.getRecurBy());
		assertFalse(appt0.isRecurring());
		assertEquals("\t9/14/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
		assertEquals(0, appt0.getRecurIncrement());
		appt0.setValid();
	}

	@Test(timeout = 4000)
	public void testInvalidUpperMinute() throws Throwable {
		Appt appt0 = new Appt(15, 61, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		String string0 = appt0.toString();
		assertEquals("\t9/14/2018 at 3:61pm ,Birthday Party, This is my birthday party\n", string0);
		appt0.setValid();
	}

	@Test(timeout = 4000)
	public void testAmPm() throws Throwable {
		Appt appt0 = new Appt(12, 00, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		String string0 = appt0.toString();
		//assertEquals("\t14/9/2018 at 12:00am ,Birthday Party, This is my birthday party\n", string0);
		appt0.setValid();
	}
	
	@Test(timeout = 4000)
	public void testDate() throws Throwable {
		Appt appt0 = new Appt(9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertTrue(appt0.isOn(9,14,2018));
	}
	
	@Test(timeout = 4000)
	public void testDateIsOn() throws Throwable {
		Appt appt0 = new Appt(9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.isOn(8,14,2018));
		assertFalse(appt0.isOn(9,13,2018));
		assertFalse(appt0.isOn(9,14,2017));
	}
	
	@Test(timeout = 4000)
	public void testInvalidDate() throws Throwable {
		Appt appt0 = new Appt(9, 13, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidDate2() throws Throwable {
		Appt appt0 = new Appt(9, -1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidHour() throws Throwable {
		Appt appt0 = new Appt(-1, -1, 1, 1, 1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidHour2() throws Throwable {
		Appt appt0 = new Appt(24, -1, 1, 1, 1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidMinute() throws Throwable {
		Appt appt0 = new Appt(1, -1, 9, 11, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidMinute2() throws Throwable {
		Appt appt0 = new Appt(1, 60, 9, 11, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidYear() throws Throwable {
		Appt appt0 = new Appt(1, 1, 9, 11, -1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
	}
	

	
	@Test(timeout = 4000)
	public void testValidAppt() throws Throwable {
		Appt appt0 = new Appt(1, 1, 31, 11, 2017, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertTrue(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testNullTitle() throws Throwable {
		Appt appt0 = new Appt(1, 1, 31, 11, 2017, null, "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertTrue(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testNullDescription() throws Throwable {
		Appt appt0 = new Appt(1, 1, 31, 11, 2017, "Birthday Party", null, "xyz@gmail.com");
		appt0.setValid();
		String email = appt0.getEmailAddress();
		assertTrue(appt0.getValid());
		assertEquals("xyz@gmail.com", email);
	}
	
	@Test(timeout = 4000)
	public void testNullEmail() throws Throwable {
		Appt appt0 = new Appt(1, 1, 31, 11, 2017, "Birthday Party", "This is my birthday party", null);
		appt0.setValid();
		assertTrue(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidToString() throws Throwable {
		Appt appt0 = new Appt(1, -1, 31, 12, 2018, "Birthday Party", "This is my birthday party", null);
		appt0.setValid();
		String string0 = appt0.toString();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidMonth() throws Throwable {
		Appt appt0 = new Appt(0, 1, 31, 13, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		String string0 = appt0.toString();
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidDay() throws Throwable {
		Appt appt0 = new Appt(1, 1, 32, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		//assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidDay2() throws Throwable {
		Appt appt0 = new Appt(1, 1, -1, 11, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		//assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testInvalidDayFeb() throws Throwable {
		Appt appt0 = new Appt(1, 1, 29, 02, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		//assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testTimeSet() throws Throwable {
		Appt appt0 = new Appt(1, 1, 31, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertTrue(appt0.hasTimeSet());
	}
	
	@Test(timeout = 4000)
	public void testNoTimeSet() throws Throwable {
		Appt appt0 = new Appt(31, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		//assertFalse(appt0.hasTimeSet());
	}
	
	@Test(timeout = 4000)
	public void testNoTime() throws Throwable {
		Appt appt0 = new Appt(31, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		//assertTrue(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testRecurrence() throws Throwable {
		Appt appt0 = new Appt(31, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		int[] days = {1,2,3};
		appt0.setRecurrence(days, 1, -1, 5);
		assertEquals(days, appt0.getRecurDays());
		assertTrue(appt0.isRecurring());
		Appt appt1 = new Appt(31, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		assertFalse(appt1.isRecurring());
		assertEquals(appt0.getRecurIncrement(), -1);
	}
	
	@Test(timeout = 4000)
	public void testApptBoundary() throws Throwable {
		Appt appt0 = new Appt(0, 0, 1, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertTrue(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testApptBoundary2() throws Throwable {
		Appt appt0 = new Appt(23, 59, 31, 12, 1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertTrue(appt0.hasTimeSet());
		assertTrue(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testApptBoundary3() throws Throwable {
		Appt appt0 = new Appt(23, 59, 31, 12, 0, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertTrue(appt0.hasTimeSet());
		assertFalse(appt0.getValid());
	}
	
	@Test(timeout = 4000)
	public void testTimeSet2() throws Throwable {
		Appt appt0 = new Appt(31, 12, 0, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.hasTimeSet());
	}
	
	@Test(timeout = 4000)
	public void testTimePM() throws Throwable {
		Appt appt0 = new Appt(12, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		String string0 = appt0.toString();
		assertEquals(2, appt0.getRecurBy());
		assertFalse(appt0.isRecurring());
		assertEquals("\t9/14/2018 at 12:30pm ,Birthday Party, This is my birthday party\n", string0);
		assertEquals(0, appt0.getRecurIncrement());
		appt0.setValid();
	}
	
	@Test(timeout = 4000)
	public void testTimePM2() throws Throwable {
		Appt appt0 = new Appt(11, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		String string0 = appt0.toString();
		assertEquals(2, appt0.getRecurBy());
		assertFalse(appt0.isRecurring());
		assertEquals("\t9/14/2018 at 11:30am ,Birthday Party, This is my birthday party\n", string0);
		assertEquals(0, appt0.getRecurIncrement());
		appt0.setValid();
	}
	
	
}
