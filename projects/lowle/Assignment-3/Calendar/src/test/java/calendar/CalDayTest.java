/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;

public class CalDayTest {

	@Test(timeout = 4000)
	public void testAddAppt() throws Throwable {
		CalDay c0 = new CalDay(new GregorianCalendar(2018, 4, 16));
		
		//make 1 appointments
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		
		c0.addAppt(ap1);
		
		assertEquals(1, c0.getSizeAppts());
		assertEquals(2018, c0.getYear());
		assertEquals(5, c0.getMonth());
		assertEquals(16, c0.getDay());
		
		assertTrue(c0.isValid());
		

	}

	@Test(timeout = 4000)
	public void testAddMultiAppt() throws Throwable {
		
		CalDay c0 = new CalDay(new GregorianCalendar(2018, 4, 16));
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		c0.addAppt(ap1);
		
		assertEquals(1,c0.getSizeAppts());
		
		Appt ap2 = new Appt(-1, 00, 14, 9, 2018, "Dinner", "Sandwiches", "xyz@gmail.com");
		ap2.setValid();
		c0.addAppt(ap2);
		
		assertEquals(1,c0.getSizeAppts());
		
		Appt ap3 = new Appt(18, 00, 14, 9, 2018, "Dinner", "Sandwiches", "xyz@gmail.com");
		ap3.setValid();
		c0.addAppt(ap3);
		
		assertEquals(2,c0.getSizeAppts());
		
		Appt ap4 = new Appt(13, 00, 14, 9, 2018, "Dinner", "Sandwiches", "xyz@gmail.com");
		ap4.setValid();
		c0.addAppt(ap4);
		
		assertEquals(3,c0.getSizeAppts());

	}
	
	@Test(timeout = 4000)
	public void testInvalidCalDay() throws Throwable {
		
		CalDay c0 = new CalDay();
		assertFalse(c0.isValid());
		
		assertNull(c0.iterator());
		
		assertEquals("", c0.toString());
		
	}
	
	@Test(timeout = 4000)
	public void testCalDayString() throws Throwable {
		
		CalDay c0 = new CalDay(new GregorianCalendar(2018, 4, 16));
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		c0.addAppt(ap1);

		assertEquals("	 --- 6/16/2018 --- \n" + 
				" --- -------- Appointments ------------ --- \n" + 
				"	9/14/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n", c0.toString());
	}
	
	@Test(timeout = 4000)
	public void testFullInfoAppCoverage() throws Throwable {
		
		CalDay c0 = new CalDay(new GregorianCalendar(2018, 4, 16));
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		c0.addAppt(ap1);
		
		Appt ap2 = new Appt(06, 9, 14, 9, 2018, "Dinner", "Sandwiches", "xyz@gmail.com");
		ap2.setValid();
		c0.addAppt(ap2);
		
		Appt ap3 = new Appt(00, 10, 14, 9, 2018, "Dinner", "Sandwiches", "xyz@gmail.com");
		ap3.setValid();
		c0.addAppt(ap3);
		
		Appt ap4 = new Appt(1,1, 2018, "Birthday", "Whole Day", "xyz@gmail.com");
		System.out.println(ap4.getValid());
		c0.addAppt(ap4);
		
		System.out.println(c0.getFullInfomrationApp(c0));
		
		assertEquals("5-16-2018 \n" + 
				"	Birthday Whole Day \n" + 
				"	12:010AM Dinner Sandwiches \n" + 
				"	6:9AM Dinner Sandwiches \n" + 
				"	3:30PM Birthday Party This is my birthday party ", c0.getFullInfomrationApp(c0));
				
	}
	
	@Test(timeout = 4000)
	public void testFullInfoAppCoverage2() throws Throwable {
		
		CalDay c0 = new CalDay(new GregorianCalendar(2018, 4, 16));
		
		Appt ap1 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		ap1.setValid();
		c0.addAppt(ap1);
		
		Appt ap5 = new Appt(15, 30, 14, 9, 2018, "Birthday Party 2", "This is my birthday party", "xyz@gmail.com");
		ap5.setValid();
		c0.addAppt(ap5);
		
		Appt ap2 = new Appt(06, 9, 14, 9, 2018, "Dinner", "Sandwiches", "xyz@gmail.com");
		ap2.setValid();
		c0.addAppt(ap2);
		
		Appt ap3 = new Appt(12, 10, 14, 9, 2018, "Dinner", "Sandwiches", "xyz@gmail.com");
		ap3.setValid();
		c0.addAppt(ap3);
		
		Appt ap4 = new Appt(1,1, 2018, "Birthday", "Whole Day", "xyz@gmail.com");
		System.out.println(ap4.getValid());
		c0.addAppt(ap4);
		
		System.out.println(c0.getFullInfomrationApp(c0));
		
		assertEquals("5-16-2018 \n" + 
				"	Birthday Whole Day \n" + 
				"	6:9AM Dinner Sandwiches \n" + 
				"	0:010AM Dinner Sandwiches \n" + 
				"	3:30PM Birthday Party This is my birthday party \n" +
				"	3:30PM Birthday Party 2 This is my birthday party ", c0.getFullInfomrationApp(c0));
				
	}
}
