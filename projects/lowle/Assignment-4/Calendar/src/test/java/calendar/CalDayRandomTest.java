package calendar;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Random;

/**
 * Random Test Generator for CalDay class.
 */

public class CalDayRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;
	
	public Appt getRandomAppointment(Random random) throws Throwable {
		


		int startHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
		int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 31);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
		int startYear = ValuesGenerator.getRandomIntBetween(random, -10, 10);
		String title = "Birthday Party";
		String description = "This is my birthday party.";
		String emailAddress = "xyz@gmail.com";

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
				
				Appt appt = getRandomAppointment(random);

				if (!appt.getValid())
					continue;
				for (int i = 0; i < NUM_TESTS; i++) {

					

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
