package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

import calendar.Appt;
import calendar.CalendarUtil;

import static org.junit.Assert.*;

/**
 * Random Test Generator for Appt class.
 */

public class ApptRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random) {
		String[] methodArray = new String[] { "setTitle", "setRecurrence", "setValid", "isOn" };// The list of the of methods to be tested
																			// in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and methodArray.length
													// (exclusive)

		return methodArray[n]; // return the method name
	}

	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
	public static int RandomSelectRecur(Random random) {
		int[] RecurArray = new int[] { Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY };// The list
																											// of the of
																											// setting
																											// appointments
																											// to recur
																											// Weekly,Monthly,
																											// or Yearly

		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and RecurArray.length
													// (exclusive)
		return RecurArray[n]; // return the value of the appointments to recur
	}

	/**
	 * Return a randomly selected appointments to recur forever or Never recur !.
	 */
	public static int RandomSelectRecurForEverNever(Random random) {
		int[] RecurArray = new int[] { Appt.RECUR_NUMBER_FOREVER, Appt.RECUR_NUMBER_NEVER };// The list of the of
																							// setting appointments to
																							// recur
																							// RECUR_NUMBER_FOREVER, or
																							// RECUR_NUMBER_NEVER

		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and RecurArray.length
													// (exclusive)
		return RecurArray[n]; // return appointments to recur forever or Never recur
	}

	/**
	 * Generate Random Tests that tests Appt Class.
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

				int startHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
				int startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
				int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 31);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
				int startYear = ValuesGenerator.getRandomIntBetween(random, -10, 10);
				String title = ValuesGenerator.getString(random);
				String description = ValuesGenerator.getString(random);
				String emailAddress = ValuesGenerator.getString(random);

				// Construct a new Appointment object with the initial data
				// Construct a new Appointment object with the initial data
				Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description,
						emailAddress);

				if (!appt.getValid())
					continue;
				for (int i = 0; i < NUM_TESTS; i++) {
					
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					
					if (methodName.equals("setTitle")) {
						
						String newTitle = (String) ValuesGenerator.getString(random);
						appt.setTitle(newTitle);
						assertEquals(newTitle, appt.getTitle() == "" ? null : appt.getTitle());
						
					} else if (methodName.equals("setRecurrence")) {
						
						int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
						int recur = ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						
						assertEquals(appt.getRecurDays(), recurDays);
						assertEquals(appt.getRecurBy(), recur);
						assertEquals(appt.getRecurIncrement(), recurIncrement);
						assertEquals(appt.getRecurNumber(), recurNumber);
						
					} else if (methodName.equals("setValid")) {
						
						//change around the hours and minutes and assert validation
						
						startHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
						startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
						startDay = ValuesGenerator.getRandomIntBetween(random, 0, 31);
						startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
						startYear = ValuesGenerator.getRandomIntBetween(random, -10, 10);
						
						appt.setStartHour(startHour);
						appt.setStartMinute(startMinute);
						appt.setStartDay(startDay);
						appt.setStartMonth(startMonth);
						appt.setStartYear(startYear);
						
						appt.setValid();
						
						boolean isValid = true;
						if (startHour > 23 || startHour < 0 || startMinute > 59 || startMinute < 0
								|| startMonth > 12 || startMonth < 1 || startYear <= 0) {
							isValid = false;
						}
						//the following code will reveal a bug I introduced, uncommenting this will fail the tests
						
						if ((startMonth == 4 || startMonth == 6 || startMonth == 9 || startMonth == 11) && startDay > 30) {
							isValid = false;
						} else if ((startMonth == 2 && startDay > 28)) {
							isValid = false;
						} else if (startDay > 31) {
							isValid = false;
						}
						
						
						assertEquals(appt.getValid(), isValid);
						
					} else if (methodName.equals("isOn")) {
						
						int day = ValuesGenerator.getRandomIntBetween(random, 1, 31);
						int month = ValuesGenerator.getRandomIntBetween(random, 1, 12);
						int year = ValuesGenerator.getRandomIntBetween(random, -10, 10);
						assertEquals(appt.isOn(day, month, year), day==appt.getStartDay() && month == appt.getStartMonth() && year ==appt.getStartYear());
						
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
