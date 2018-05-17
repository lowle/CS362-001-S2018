package calendar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Random Test Generator for CalDay class.
 */

public class CalDayRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;
	
	public Appt getRandomAppointment(Random random, int day, int month, int year) throws Throwable {
		


		int startHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
		int startDay = day;
		int startMonth = month;
		int startYear = year;
		String title = ValuesGenerator.getString(random);
		String description = ValuesGenerator.getString(random);
		String emailAddress = ValuesGenerator.getString(random);

		// Construct a new Appointment object with the initial data
		// Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description,
				emailAddress);
		
		return appt;
		
	}

	/**
	 * Generate Random Tests that tests CalDay Class.
	 */
	@Test
	public void randomtest() throws Throwable {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");
		
		

		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				
				long randomseed = System.currentTimeMillis(); // 10
				// System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				
				int day = ValuesGenerator.getRandomIntBetween(random, 0, 31);
				int month = ValuesGenerator.getRandomIntBetween(random, 0, 12);
				int year = ValuesGenerator.getRandomIntBetween(random, 2012, 2020);
				
				GregorianCalendar cal = new GregorianCalendar(year, month, day);
				
				CalDay cday = new CalDay(cal);

				if (!cday.isValid())
					continue;
				
				List<Appt> apptList = new ArrayList<Appt>();
				
				for (int i = 0; i < NUM_TESTS; i++) {

					Appt appt = getRandomAppointment(random, day, month, year);
					appt.setValid();
					
					cday.addAppt(appt);
					
					if (appt.getValid()) {
						apptList.add(appt);
					}
					apptList.sort(new ApptComparator());
					
					assertEquals(cday.getSizeAppts(), apptList.size());
					
					List<Appt> calDayList = cday.getAppts();
					
					//check contents and order of elements inside apptlist
					for (int j=0; j < apptList.size(); j++) {
						assertEquals(calDayList.get(j), apptList.get(j));
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
	
	class ApptComparator implements Comparator<Appt> {

		@Override
		public int compare(Appt o1, Appt o2) {
			return (o1.getStartHour() - o2.getStartHour());
		}
		
	}

}
