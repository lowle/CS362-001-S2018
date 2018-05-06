
/** A JUnit test class to test the class DataHandler. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class DataHandlerTest {

	@Test(timeout = 4000)
	public void test00() throws Throwable {
		DataHandler d0 = new DataHandler();
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		ap1.setRecurrence(new int[]{1,3}, 2 , 1, 2);
		
		assertTrue(d0.saveAppt(ap1));
		
		d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		assertTrue(d0.deleteAppt(ap1));
		
		
	}

	@Test(timeout = 4000)
	public void testAltConstructor() throws Throwable {
		
		DataHandler d0 = new DataHandler("data.xml", false);
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		ap1.setRecurrence(new int[]{1,3}, 2 , 1, 2);
		
		assertTrue(d0.saveAppt(ap1));
		
		d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		assertTrue(d0.deleteAppt(ap1));
		assertFalse(d0.deleteAppt(ap1));
		
	}
	
	@Test(timeout = 4000)
	public void testAppts() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		
		Appt ap2 = new Appt(16, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap2.setRecurrence(new int[]{}, 2 , 1, 2);
		ap2.setValid();
		
		Appt ap3 = new Appt(-1, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap3.setRecurrence(new int[]{1,3}, 3 , 3, 3);
		ap3.setValid();
		
		
		Appt ap4 = new Appt(1, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap4.setRecurrence(new int[]{1,3}, 1, 3, 3);
		ap4.setValid();
		
		assertTrue(d0.saveAppt(ap1));
		assertTrue(d0.saveAppt(ap2));
		assertFalse(d0.saveAppt(ap3));
		assertTrue(d0.saveAppt(ap4));
		
		
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,13), new GregorianCalendar(2018,9,15));
		
		assertEquals(2, list.size());
		assertTrue(d0.deleteAppt(ap1));
		assertTrue(d0.deleteAppt(ap2));
		assertFalse(d0.deleteAppt(ap3));
		assertFalse(d0.deleteAppt(ap3));
		assertTrue(d0.deleteAppt(ap4));
	}
		
	@Test (timeout = 4000)
	public void testInvalidDate() throws Throwable {
		DataHandler d0 = new DataHandler();
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		ap1.setRecurrence(new int[]{1,3}, 2 , 1, 2);
		
		assertTrue(d0.saveAppt(ap1));
		try {
			assertEquals(0, d0.getApptRange(new GregorianCalendar(2018,9,16), new GregorianCalendar(2018,9,15)).size());
		} catch (Exception e) {
			assertTrue(d0.deleteAppt(ap1)); //cleanup
		}
		
	}
	
	
	@Test(timeout = 4000)
	public void testAppts2() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		
		Appt ap2 = new Appt(16, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap2.setRecurrence(new int[]{}, 2 , 1, 2);
		ap2.setValid();
		
		Appt ap3 = new Appt(-1, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap3.setRecurrence(new int[]{1,3}, 3 , 3, 3);
		ap3.setValid();
		
		
		Appt ap4 = new Appt(1, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap4.setRecurrence(new int[]{1,3}, 1, 3, 3);
		ap4.setValid();
		
		assertTrue(d0.saveAppt(ap1));
		assertTrue(d0.saveAppt(ap2));
		assertFalse(d0.saveAppt(ap3));
		assertTrue(d0.saveAppt(ap4));
		
		
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,13), new GregorianCalendar(2018,9,15));
		
		assertEquals(2, list.size());
		
		assertTrue(d0.deleteAppt(ap1));
		assertTrue(d0.deleteAppt(ap2));
		assertFalse(d0.deleteAppt(ap3));
		assertFalse(d0.deleteAppt(ap3));
		assertTrue(d0.deleteAppt(ap4));
	}
	
	
	@Test(timeout = 4000)
	public void testAppts3() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setRecurrence(new int[]{}, Appt.RECUR_BY_MONTHLY , 3, 3);
		ap1.setValid();
		assertTrue(d0.saveAppt(ap1));
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		//System.out.println(list.get(0));
		assertEquals(list.get(0).toString(), "	 --- 11/14/2018 --- \n" + 
				" --- -------- Appointments ------------ --- \n" + 
				"	9/14/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n");
		
		assertTrue(d0.deleteAppt(ap1));
	}
	
	@Test(timeout = 4000)
	public void testAppts4() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		Appt ap1 = new Appt(15, 30, 7, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setRecurrence(new int[] {1,2,3,4,5,6,7}, Appt.RECUR_BY_WEEKLY , 1, Appt.RECUR_NUMBER_FOREVER);
		ap1.setValid();
		assertTrue(d0.saveAppt(ap1));
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		//System.out.println(list.get(0));
		assertEquals(list.get(0).toString(), "	 --- 11/14/2018 --- \n" + 
				" --- -------- Appointments ------------ --- \n" + 
				"	9/7/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n");
		
		assertTrue(d0.deleteAppt(ap1));
	}
	
	@Test(timeout = 4000)
	public void testAppts6() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		Appt ap1 = new Appt(15, 30, 7, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setRecurrence(new int[] {}, Appt.RECUR_BY_WEEKLY , 1, Appt.RECUR_NUMBER_FOREVER);
		ap1.setValid();
		assertTrue(d0.saveAppt(ap1));
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		//System.out.println(list.get(0));
		assertEquals(list.get(0).toString(), "	 --- 11/14/2018 --- \n" + 
				" --- -------- Appointments ------------ --- \n\n");
		
		assertTrue(d0.deleteAppt(ap1));
	}
	
	
	@Test(timeout = 4000)
	public void testAppts5() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setRecurrence(new int[] {}, Appt.RECUR_BY_MONTHLY , 3, 3);
		ap1.setValid();
		assertTrue(d0.saveAppt(ap1));
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		//System.out.println(list.get(0));
		assertEquals(list.get(0).toString(), "	 --- 11/14/2018 --- \n" + 
				" --- -------- Appointments ------------ --- \n" + 
				"	9/14/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n");
		
		assertTrue(d0.deleteAppt(ap1));
	}
	
	@Test(timeout = 4000)
	public void testAppts8() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setRecurrence(new int[] {}, Appt.RECUR_BY_YEARLY , 3, 3);
		ap1.setValid();
		assertTrue(d0.saveAppt(ap1));
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		//System.out.println(list.get(0));
		assertEquals(list.get(0).toString(), "	 --- 11/14/2018 --- \n" + 
				" --- -------- Appointments ------------ --- \n" + 
				"	9/14/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n");
		
		assertTrue(d0.deleteAppt(ap1));
	}
	
	@Test(timeout = 4000)
	public void testApptsinvalid() throws Throwable {
		
		DataHandler d0 = new DataHandler();
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setRecurrence(new int[] {}, 4 , 3, 3);
		ap1.setValid();
		assertTrue(d0.saveAppt(ap1));
		List<CalDay> list = d0.getApptRange(new GregorianCalendar(2018,9,14), new GregorianCalendar(2018,9,15));
		
		//System.out.println(list.get(0));
		assertEquals(list.get(0).toString(), "	 --- 11/14/2018 --- \n" + 
				" --- -------- Appointments ------------ --- \n\n");
		
		assertTrue(d0.deleteAppt(ap1));
	}
	
}
