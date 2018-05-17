package calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Random Test Generator for DataHandler class.
 */

public class DataHandlerRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;
	
	public static String RandomSelectMethod(Random random) {
		String[] methodArray = new String[] { "saveAppt", "deleteAppt", "getApptRange" };// The list of the of methods to be tested
																			// in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and methodArray.length
													// (exclusive)

		return methodArray[n]; // return the method name
	}
	
	public Appt getRandomAppointment(Random random) throws Throwable {

		int startHour = ValuesGenerator.getRandomIntBetween(random, -1, 24);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 60);
		int startDay = ValuesGenerator.getRandomIntBetween(random, -1, 31);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, -1, 12);
		int startYear = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
		String title = ValuesGenerator.getString(random);
		String description = ValuesGenerator.getString(random);
		String emailAddress = ValuesGenerator.getString(random);

		// Construct a new Appointment object with the initial data
		// Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description,
				emailAddress);
		
		int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
		int[] recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
		int recur = DataHandlerRandomTest.RandomSelectRecur(random);
		int recurIncrement = ValuesGenerator.RandInt(random);
		int recurNumber = DataHandlerRandomTest.RandomSelectRecurForEverNever(random);
		appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
		
		appt.setValid();
		return appt;
		
	}
	
	public static int RandomSelectRecur(Random random) {
		int[] RecurArray = new int[] { Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY };
		// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and RecurArray.length
													// (exclusive)
		return RecurArray[n]; // return the value of the appointments to recur
	}
	
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
	 * Generate Random Tests that tests DataHandler Class.
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
				
				DataHandler datahandler;
				
				int constructor = ValuesGenerator.getRandomIntBetween(random, 0, 3);
				
				if (constructor == 0) {
					datahandler = new DataHandler();
				} else if (constructor == 1) {
					datahandler = new DataHandler("calendar.xml");
				} else if (constructor == 2) {
					datahandler = new DataHandler("calendar.xml", false);
				} else if (constructor == 3) {
					datahandler = new DataHandler("calendar.xml", true);
				} else {
					datahandler = null;
				}
				if (datahandler == null) continue; //this should not happen
				
				ArrayList<Appt> allAppt = new ArrayList<Appt>();
				ArrayList<Appt> validAppt = new ArrayList<Appt>();

				for (int i = 0; i < NUM_TESTS; i++) {

					String methodName = DataHandlerRandomTest.RandomSelectMethod(random);

					if (methodName.equals("saveAppt")) {
						
						Appt ap = getRandomAppointment(random);
						assertEquals(datahandler.saveAppt(ap), ap.getValid());
						
						
						allAppt.add(ap);
						if (ap.getValid()) {
							validAppt.add(ap);
						}
						

					} else if (methodName.equals("deleteAppt")) {
						Appt ap;
						
						//Randomise which appointment to remove
						
						if (allAppt.isEmpty()) {
							//nothing to delete
							continue;
							
						} else {
							int index = ValuesGenerator.getRandomIntBetween(random, 0, allAppt.size() - 1);
							ap = allAppt.get(index); //randomly pick an appointment to remove
							
						}
						
						//see if appointment can be removed, and assert result
						boolean successful = datahandler.deleteAppt(ap);
						assertEquals(successful, ap.getValid());
						
						allAppt.remove(ap);
						if (successful) {
							validAppt.remove(ap);
						}
						
						
						
					} else if (methodName.equals("getApptRange")) {
						
						int firstday = ValuesGenerator.getRandomIntBetween(random, 0, 31);
						int firstmonth = ValuesGenerator.getRandomIntBetween(random, 0, 12);
						int firstyear = ValuesGenerator.getRandomIntBetween(random, 2017, 2018);
						
						int lastday = ValuesGenerator.getRandomIntBetween(random, 0, 31);
						int lastmonth = ValuesGenerator.getRandomIntBetween(random, 0, 12);
						int lastyear = ValuesGenerator.getRandomIntBetween(random, 2017, 2018);

						GregorianCalendar firstDay = new GregorianCalendar(firstyear, firstmonth, firstday);
						GregorianCalendar lastDay = new GregorianCalendar(lastyear, lastmonth, lastday);
						
						List<CalDay> days;
						try {
							days = datahandler.getApptRange(firstDay, lastDay);
							
						} catch (DateOutOfRangeException e) {
							continue;
						}
						
						for (CalDay calday : days) {
							for (Appt apt : calday.getAppts()) {
								assertTrue(listContainsAppt(validAppt, apt));
							}
						}
						

					}
				}
				
				//time for cleanup
				for (Appt appt : validAppt) {
					if (appt.getValid() ) {
						datahandler.deleteAppt(appt);
					}
					
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		System.out.println("Done testing...");
	}
	
	//helper method for testing
	private boolean listContainsAppt(ArrayList<Appt> validAppt, Appt testappt) {
		
		for (Appt appt : validAppt) {
			
			if (appt.getStartDay() != testappt.getStartDay() || appt.getStartMonth() != testappt.getStartMonth() ||
					appt.getStartYear() != testappt.getStartYear()) {
				continue;
			}
			
			if (appt.getStartHour() != testappt.getStartHour() || appt.getStartMinute() != testappt.getStartMinute()) {
				continue;
			}

			return true;
		}
		return false;
	}

}
