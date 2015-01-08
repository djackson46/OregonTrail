package edu.gatech.cs2340.model;

/**
 * The date
 * 
 * @author Jon
 */
public final class Date {
	private static int year, month, day;
	private static boolean setup = false;

	private Date() {
	}

	/**
	 * Use this to initialize the date class. There are no instances of Date
	 * 
	 * @param y
	 *            - the year to start at
	 * @param m
	 *            - the month to start at
	 * @param d
	 *            - the day to start at
	 */
	public static void setup(int y, int m, int d)
			throws IllegalArgumentException {
		if (setup == true) {
			System.err.println("Date has already been setup");
		}
		if (m < 13 && m > 0 && d < 32 && d > 0) {
			year = y;
			month = m;
			day = d;
		} else {
			throw new IllegalArgumentException("Months, days are not correct");
		}
	}

	/**
	 * Goes to next day
	 */
	public static void increment() {
		day++;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day > 31) {
				month++;
				day = 1;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day > 30) {
				month++;
				day = 1;
			}
			break;
		case 2:
			if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
				if (day > 29) {
					month++;
					day = 1;
				}
			} else {
				if (day > 28) {
					month++;
					day = 1;
				}
			}
			break;
		default:
			System.out.println("Invalid month.");
			break;
		}
		if (month > 12) {
			month = 1;
			year++;
		}
	}

	/**
	 * Goes numdays number of days forward
	 * 
	 * @param numdays
	 *            - number of days to increase the calendar by
	 */
	public static void increment(int numdays) {
		for (int i = 0; i < numdays; i++) {
			increment();
		}
	}

	/**
	 * This getter gets the year.
	 * 
	 * @return the year
	 */
	public static int getYear() {
		return year;
	}

	/**
	 * This getter gets the month
	 * 
	 * @return the month
	 */
	public static int getMonth() {
		return month;
	}

	/**
	 * This getter gets the day
	 * 
	 * @return the day
	 */
	public static int getDay() {
		return day;
	}
}
